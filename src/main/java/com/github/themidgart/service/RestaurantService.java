package com.github.themidgart.service;

import com.github.themidgart.util.RestaurantsUtil;
import com.github.themidgart.model.Restaurant;
import com.github.themidgart.repository.RestaurantRepository;
import com.github.themidgart.to.RestaurantTo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public Restaurant create(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Transactional
    public Restaurant update(int id, RestaurantTo restaurantTo) {
        return repository.save(RestaurantsUtil.updateFromTo(Objects.requireNonNull(repository.findById(id).get()), restaurantTo));
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
