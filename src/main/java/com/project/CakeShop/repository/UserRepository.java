package com.project.CakeShop.repository;

import com.project.CakeShop.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    User save(User user);

    User getById(Long id);
}
