package com.example.Recipe.API.repositories;

import com.example.Recipe.API.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RecipeRepo extends JpaRepository<Recipe, Long> {

    ArrayList<Recipe> findByNameContaining(String name);

    ArrayList<Recipe> findByUserNameContaining(String name);

}


