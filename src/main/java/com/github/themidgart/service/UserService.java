package com.github.themidgart.service;

import com.github.themidgart.model.User;
import com.github.themidgart.repository.UserRepository;
import com.github.themidgart.to.UserTo;
import com.github.themidgart.util.UsersUtil;
import com.github.themidgart.util.exception.NotFoundException;
import com.github.themidgart.web.security.AuthUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.github.themidgart.util.ValidationUtil.checkNotFound;
import static com.github.themidgart.util.exception.ExceptionMessages.USER_NOT_FOUND_WITH_EMAIL;
import static com.github.themidgart.util.exception.ExceptionMessages.USER_NOT_FOUND_WITH_ID;

@Primary
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository repository;

    public List<User> getAll() {
        return repository.findAll();
    }

    public User get(int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_WITH_ID + id));
    }

    @Transactional
    public User save(UserTo userTo) {
        User user = UsersUtil.createNewFromTo(userTo);
        return repository.save(UsersUtil.prepareToSave(user));
    }

    @Transactional
    public User update(int id, UserTo userTo) {

            User user = repository.findById(id).orElseThrow(()->new NotFoundException(USER_NOT_FOUND_WITH_ID+id));
            UsersUtil.updateFromTo(user,userTo);
            UsersUtil.prepareToSave(user);
            return repository.save(user);

    }

    public void delete(int id) {
        checkNotFound(repository.deleteById(id));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("trying authorize with email {}", email);
        User user = repository.findByEmailIgnoreCase(email).orElseThrow(
                () -> new UsernameNotFoundException(USER_NOT_FOUND_WITH_EMAIL + email));
        return new AuthUser(user);
    }
}
