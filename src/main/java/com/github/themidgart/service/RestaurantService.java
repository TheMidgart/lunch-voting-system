package com.github.themidgart.service;

import com.github.themidgart.model.Restaurant;
import com.github.themidgart.repository.RestaurantRepository;
import com.github.themidgart.to.RestaurantTo;
import com.github.themidgart.util.RestaurantsUtil;
import com.github.themidgart.util.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.github.themidgart.util.exception.ExceptionMessages.RESTAURANT_NOT_FOUND_WITH_ID;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {
    @Autowired
    private RestaurantRepository repository;

    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    public Restaurant get(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(RESTAURANT_NOT_FOUND_WITH_ID + id));
    }

    @Transactional
    public Restaurant create(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Transactional
    public Restaurant update(int id, RestaurantTo restaurantTo) {
        return repository.save(RestaurantsUtil.updateFromTo(Objects.requireNonNull(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(RESTAURANT_NOT_FOUND_WITH_ID + id))), restaurantTo));
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
