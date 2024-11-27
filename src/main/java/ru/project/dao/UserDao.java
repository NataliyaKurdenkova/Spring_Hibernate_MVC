package ru.project.dao;

import org.springframework.stereotype.Repository;
import ru.project.model.User;

import java.util.List;
import java.util.Optional;


public interface UserDao {


    List<User> findAll();

    Optional<User> findById(long id);

    void save(User user);

    void update(User user);

    void delete(User user);
}
