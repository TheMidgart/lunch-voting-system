package com.github.themidgart.service;

import com.github.themidgart.model.Menu;
import com.github.themidgart.model.VotingResult;
import com.github.themidgart.repository.MenuRepository;
import com.github.themidgart.repository.UserRepository;
import com.github.themidgart.repository.VotingResultRepository;
import com.github.themidgart.to.VotingResultTo;
import com.github.themidgart.util.VotingResultUtil;
import com.github.themidgart.util.exception.IllegalVotingException;
import com.github.themidgart.util.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.github.themidgart.util.exception.ExceptionMessages.*;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class VotingService {
    @Autowired
    private VotingResultRepository votingResultRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public void vote(int menuId, int userId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new NotFoundException(MENU_NOT_FOUND_WITH_ID + menuId));
        VotingResult votingResult = votingResultRepository.getByDateAndUserId(menu.getDateMenu(), userId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_WITH_ID + userId));
        if ((votingResult == null) || (votingResult.getMenu().getId() != menuId)) {
            save(votingResult, menu, userId);
        } else {
            throw new IllegalVotingException(DOUBLE_VOTING_DENIED);
        }
    }

    public VotingResultTo getResultsByDate(LocalDate date) {
        return VotingResultUtil.toSummaryResults(votingResultRepository.getResultsByDate(date)
                .orElseThrow(() -> new NotFoundException(VOTING_NOT_FOUND_ON_DATE + date)));
    }

    private void save(@Nullable VotingResult result, Menu menu, int userId) {
        VotingResultUtil.checkVotingPossibility(menu);
        if (result == null) {
            votingResultRepository.save(new VotingResult(null, userRepository.findById(userId).
                    orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_WITH_ID + userId)), menu));
        } else {
            result.setMenu(menu);
            votingResultRepository.save(result);
        }
    }
}
