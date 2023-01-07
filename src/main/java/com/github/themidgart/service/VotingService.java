package com.github.themidgart.service;

import com.github.themidgart.exception.IllegalVotingException;
import com.github.themidgart.exception.NotFoundException;
import com.github.themidgart.model.Menu;
import com.github.themidgart.model.VotingResult;
import com.github.themidgart.repository.MenuRepository;
import com.github.themidgart.repository.UserRepository;
import com.github.themidgart.repository.VotingResultRepository;
import com.github.themidgart.to.VotingResultTo;
import com.github.themidgart.util.VotingResultUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

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

    public List<Menu> getVotingOptionsForDate(LocalDate date) {
        return menuRepository.getAllByDate(date)
                .orElseThrow(() -> new NotFoundException("Not found menus for date : " + date));
    }

    @Transactional
    public void vote(int menuId, int userId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new NotFoundException("Menu with id " + menuId + " not found"));
        VotingResult votingResult = votingResultRepository.getByDateAndUserId(menu.getDateMenu(), userId).orElse(null);
        if ((votingResult == null) || (votingResult.getMenu().getId() != menuId)) {
            save(votingResult, menu, userId);
        } else {
            throw new IllegalVotingException("Double voting denied");
        }
    }

    public VotingResultTo getResultsByDate(LocalDate date) {
        return VotingResultUtil.toSummaryResults(votingResultRepository.getResultsByDate(date)
                .orElseThrow(() -> new NotFoundException("")));
    }

    private void save(@Nullable VotingResult result, Menu menu, int userId) {
        VotingResultUtil.checkVotingPossibility(menu);
        if (result == null) {
            votingResultRepository.save(new VotingResult(null, userRepository.findById(userId).
                    orElseThrow(() -> new NotFoundException("User with id " + userId + " not found")), menu));
        } else {
            result.setMenu(menu);
            votingResultRepository.save(result);
        }
    }
}
