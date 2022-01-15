package com.springboot.id1212project.repository;

import com.springboot.id1212project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Spring Data JPA internally provides @Repository annotation, so we don't need to add annotation to this interface
//@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByFirstNameIsContaining(String firstname);

    @Query(
            value = "SELECT count(*) FROM users WHERE email = ?1 AND password=MD5( ?2 )",
            nativeQuery = true
    )
    int validateUser(String email, String password);

    @Query(
            value = "SELECT * FROM users WHERE email = ?1",
            nativeQuery = true
    )
    Optional<User> findByEmail(String email);
}
