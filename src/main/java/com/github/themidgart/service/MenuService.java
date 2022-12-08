package com.github.themidgart.service;

import com.github.themidgart.model.Menu;
import com.github.themidgart.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MenuService {
    @Autowired
    private MenuRepository repository;

    public List<Menu> getAll(){
        return repository.findAll();
    }

    public Menu get(int id){
        return repository.findById(id).orElse(null);
    }

    public Menu save(Menu menu){
        return repository.save(menu);
    }

    public void delete(int id){
        repository.deleteById(id);
    }
}
