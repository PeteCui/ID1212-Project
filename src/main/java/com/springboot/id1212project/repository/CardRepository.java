package com.springboot.id1212project.repository;

import com.springboot.id1212project.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.JoinColumn;
import java.util.List;
import java.util.Optional;

//Spring Data JPA internally provides @Repository annotation, so we don't need to add annotation to this interface
//@Repository
public interface CardRepository extends JpaRepository <Card, Long>{


    @Query(
            value = "SELECT * FROM card WHERE user_id = ?1",
            nativeQuery = true
    )
    List<Card> findByUserId(long id);

    @Query(
            value = "SELECT * FROM card WHERE status = ?1",
            nativeQuery = true
    )
    List<Card> findByState(String state);
}
