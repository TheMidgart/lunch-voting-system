package com.github.themidgart.service;

import com.github.themidgart.exception.NotFoundException;
import com.github.themidgart.model.User;
import com.github.themidgart.repository.UserRepository;
import com.github.themidgart.security.AuthUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Primary
@Service
@AllArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("trying authorize with email {}",email);
        User user = repository.findByEmailIgnoreCase(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format("Email %s not found", email)));
        return new AuthUser(user);
    }
}
