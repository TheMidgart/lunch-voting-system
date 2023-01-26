package com.github.themidgart.service;

import com.github.themidgart.model.Restaurant;
import com.github.themidgart.repository.RestaurantRepository;
import com.github.themidgart.to.RestaurantTo;
import com.github.themidgart.util.RestaurantsUtil;
import com.github.themidgart.util.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.github.themidgart.util.ValidationUtil.checkNotFound;
import static com.github.themidgart.util.exception.ExceptionMessages.RESTAURANT_NOT_FOUND_WITH_ID;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository repository;

    public List<RestaurantTo> getAll() {
        return repository.findAll().stream().map(RestaurantsUtil::createToFromModel).toList();
    }

    public RestaurantTo get(int id) {
        return RestaurantsUtil.createToFromModel(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(RESTAURANT_NOT_FOUND_WITH_ID + id)));
    }

    @Transactional
    public Restaurant create(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Transactional
    public void update(int id, RestaurantTo restaurantTo) {
        checkNotFound(repository.updateById(restaurantTo.getName(), id));
    }

    public void delete(int id) {
        checkNotFound(repository.deleteById(id));
    }
}
