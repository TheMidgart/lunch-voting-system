package com.github.themidgart.service;

import com.github.themidgart.model.Dish;
import com.github.themidgart.model.User;
import com.github.themidgart.repository.DishRepository;
import com.github.themidgart.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DishService {
    @Autowired
    private DishRepository repository;

    public List<Dish> getAll(){
        return repository.findAll();
    }

    public Dish get(int id){
        return repository.findById(id).orElse(null);
    }

    public Dish save(Dish dish){
        return repository.save(dish);
    }

    public void delete(int id){
        repository.deleteById(id);
    }
}
