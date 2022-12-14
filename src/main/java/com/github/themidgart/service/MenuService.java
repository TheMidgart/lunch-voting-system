package com.github.themidgart.service;

import com.github.themidgart.exception.NotFoundException;
import com.github.themidgart.model.Menu;
import com.github.themidgart.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
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
    @Transactional
    public Menu update(int id, Menu menu){
        if (repository.existsById(id)){
            return repository.save(menu);
        }
        else {
            throw new NotFoundException("Menu with id "+id+ " not found");
        }
    }

    public void delete(int id){
        repository.deleteById(id);
    }
}
