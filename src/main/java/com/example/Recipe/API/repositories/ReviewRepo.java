package com.example.Recipe.API.repositories;

import com.example.Recipe.API.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ReviewRepo extends JpaRepository<Review, Long> {

    ArrayList<Review> findByUsername(String username);
    ArrayList<Review> findByRating(Integer rating);

}
