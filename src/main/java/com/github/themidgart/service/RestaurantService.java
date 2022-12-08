package com.github.themidgart.service;

import com.github.themidgart.model.Restaurant;
import com.github.themidgart.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService {
    @Autowired
    private RestaurantRepository repository;

    public List<Restaurant> getAll(){
        return repository.findAll();
    }

    public Restaurant get(int id){
        return repository.findById(id).orElse(null);
    }

    public Restaurant save(Restaurant restaurant){
        return repository.save(restaurant);
    }

    public void delete(int id){
        repository.deleteById(id);
    }
}
