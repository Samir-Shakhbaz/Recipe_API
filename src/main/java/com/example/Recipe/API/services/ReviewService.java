package com.example.Recipe.API.services;


import com.example.Recipe.API.exceptions.NoSuchRatingException;
import com.example.Recipe.API.exceptions.NoSuchRecipeException;
import com.example.Recipe.API.exceptions.NoSuchReviewException;
import com.example.Recipe.API.models.Recipe;
import com.example.Recipe.API.models.Review;
import com.example.Recipe.API.repositories.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class ReviewService {

    @Autowired
    ReviewRepo reviewRepo;

    @Autowired
    RecipeService recipeService;

    public ArrayList <Review> getReviewByRating(int rating) throws NoSuchRatingException {
        ArrayList<Review> review = reviewRepo.findByRating(rating);
        if (review.isEmpty()) {
            throw new NoSuchRatingException("The review with rating " + rating + " could not be found.");
        }
        return review;
   }

    public Review getReviewById(Long id) throws NoSuchReviewException {
        Optional<Review> review = reviewRepo.findById(id);

        if (review.isEmpty()) {
            throw new NoSuchReviewException("The review with ID " + id + " could not be found.");
        }
        return review.get();
    }

    public ArrayList<Review> getReviewByRecipeId(Long recipeId) throws NoSuchRecipeException, NoSuchReviewException {
        Recipe recipe = recipeService.getRecipeById(recipeId);

        ArrayList<Review> reviews = new ArrayList<>(recipe.getReviews());

        if (reviews.isEmpty()) {
            throw new NoSuchReviewException("There are no reviews for this recipe.");
        }
        return reviews;
    }

    public ArrayList<Review> getReviewByUsername(String username) throws NoSuchReviewException {
        ArrayList<Review> reviews = reviewRepo.findByUsername(username);

        if (reviews.isEmpty()) {
            throw new NoSuchReviewException("No reviews could be found for username " + username);
        }

        return reviews;
    }

    public Recipe postNewReview(Review review, Long recipeId) throws NoSuchRecipeException {
        Recipe recipe = recipeService.getRecipeById(recipeId);
        recipe.getReviews().add(review);
        long count = 0;
        for(Review review1: recipe.getReviews())
        {
            count +=review1.getRating();
        }
        recipe.setAverageRating(count/recipe.getReviews().size());
        recipeService.updateRecipe(recipe, false);
        return recipe;
    }

    public Review deleteReviewById(Long id) throws NoSuchReviewException {
        Review review = getReviewById(id);

        if (null == review) {
            throw new NoSuchReviewException("The review you are trying to delete does not exist.");
        }
        reviewRepo.deleteById(id);
        return review;
    }

    public Review updateReviewById(Review reviewToUpdate, Long recipeId) throws NoSuchReviewException {
        try {
            Review review = getReviewById(reviewToUpdate.getId());
        } catch (NoSuchReviewException e) {
            throw new NoSuchReviewException("The review you are trying to update. Maybe you meant to create one? If not," +
                    "please double check the ID you passed in.");
        }
        reviewRepo.save(reviewToUpdate);
        Recipe recipe = recipeService.getRecipeById(recipeId);
        long count = 0;
        for(Review review1: recipe.getReviews())
        {
            count += review1.getRating();
        }
        recipe.setAverageRating(count/recipe.getReviews().size());
        recipeService.updateRecipe(recipe, false);
        return reviewToUpdate;
    }

}
