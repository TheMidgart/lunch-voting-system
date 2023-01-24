package com.github.themidgart.service;

import com.github.themidgart.model.Menu;
import com.github.themidgart.model.VotingResult;
import com.github.themidgart.repository.MenuRepository;
import com.github.themidgart.repository.UserRepository;
import com.github.themidgart.repository.VotingResultRepository;
import com.github.themidgart.to.VotingResultTo;
import com.github.themidgart.util.VotingResultsUtil;
import com.github.themidgart.util.exception.IllegalVotingException;
import com.github.themidgart.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.github.themidgart.util.exception.ExceptionMessages.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VotingService {
    private final VotingResultRepository votingResultRepository;
    private final MenuRepository menuRepository;
    private final UserRepository userRepository;

    @Transactional
    public void vote(int restaurantId, int userId, LocalDate date) {
        Menu menu = menuRepository.getMenuByRestaurantIdAndDate(restaurantId, date)
                .orElseThrow(() -> new NotFoundException(MENU_NOT_FOUND_ON_DATE_WITH_RESTAURANT_ID + restaurantId));
        VotingResult votingResult = votingResultRepository.getByDateAndUserId(menu.getDate(), userId)
                .orElse(null);
        if (votingResult == null) {
            votingResultRepository.save(new VotingResult(null, userRepository.getReferenceById(userId), menu));
        } else if (!votingResult.getMenu().getId().equals(menu.getId())) {
            VotingResultsUtil.checkVotingPossibility(menu);
            votingResult.setMenu(menu);
            votingResultRepository.save(votingResult);
        } else {
            throw new IllegalVotingException(DOUBLE_VOTING_DENIED);
        }
    }

    public VotingResultTo getResultsByDate(LocalDate date) {
        return VotingResultsUtil.toSummaryResults(votingResultRepository.getResultsByDate(date)
                .orElseThrow(() -> new NotFoundException(VOTING_NOT_FOUND_ON_DATE + date)));
    }
}
