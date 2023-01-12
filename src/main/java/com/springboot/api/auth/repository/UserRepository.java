package com.springboot.api.auth.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.api.auth.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    /*
     * https://www.baeldung.com/spring-data-derived-queries
     */

    Optional<User> findByEmail(String email); //method that brings the data
}
