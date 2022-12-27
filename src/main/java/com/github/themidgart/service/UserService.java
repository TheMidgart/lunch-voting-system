package com.github.themidgart.service;

import com.github.themidgart.exception.NotFoundException;
import com.github.themidgart.model.User;
import com.github.themidgart.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> getAll(){
        return repository.findAll();
    }

    public User get(int id){
        return repository.findById(id).orElse(null);
    }

    public User save(User user){
        return repository.save(user);
    }

    @Transactional
    public User update(int id, User user){
        if (repository.existsById(id)){
            return repository.save(user);
        }
        else {
            throw new NotFoundException("User with id "+id+ " not found");
        }
    }

    public void delete(int id){
        repository.deleteById(id);
    }
}
