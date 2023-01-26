package com.github.themidgart.service;

import com.github.themidgart.model.Menu;
import com.github.themidgart.model.Vote;
import com.github.themidgart.repository.MenuRepository;
import com.github.themidgart.repository.UserRepository;
import com.github.themidgart.repository.VoteRepository;
import com.github.themidgart.to.VoteTo;
import com.github.themidgart.util.VoteUtil;
import com.github.themidgart.util.exception.IllegalVoteException;
import com.github.themidgart.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.github.themidgart.util.exception.ExceptionMessages.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VoteService {
    private final VoteRepository voteRepository;
    private final MenuRepository menuRepository;
    private final UserRepository userRepository;

    @Transactional
    public void vote(int restaurantId, int userId, LocalDate date) {
        Menu menu = menuRepository.getMenuByRestaurantIdAndDate(restaurantId, date)
                .orElseThrow(() -> new NotFoundException(MENU_NOT_FOUND_ON_DATE_WITH_RESTAURANT_ID + restaurantId));
        Vote vote = voteRepository.getByDateAndUserId(menu.getDate(), userId)
                .orElse(null);
        if (vote == null) {
            voteRepository.save(new Vote(null, userRepository.getReferenceById(userId), menu));
        } else if (!vote.getMenu().getId().equals(menu.getId())) {
            VoteUtil.checkVotePossibility(menu);
            vote.setMenu(menu);
            voteRepository.save(vote);
        } else {
            throw new IllegalVoteException(DOUBLE_VOTE_DENIED);
        }
    }

    public VoteTo getResultsByDate(LocalDate date) {
        return VoteUtil.toSummaryResults(voteRepository.getResultsByDate(date)
                .orElseThrow(() -> new NotFoundException(VOTE_NOT_FOUND_ON_DATE + date)));
    }
}
