package com.amiaka.malvin.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amiaka.malvin.Model.User;

public interface UserRepo  extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String email);

    User findByRole(String string);
}
