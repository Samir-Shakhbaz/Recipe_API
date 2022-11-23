package com.example.Recipe.API.models;

import lombok.*;
//import org.springframework.data.annotation.Id;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private int rating;

    private String description;

    public void setRating(int rating) {
        if (rating <= 0 || rating > 10) {
            throw new IllegalStateException("Rating must be between 0 and 10.");
        }
        this.rating = rating;
    }

    public void ratingAverage() {

        int ratingTotal = rating;

    }

//    public void setAverageRating(){
//        int ticker = 0;
//        for(int i = 0; i > 0; i++){
//            int sum = i + rating;
//            double average = sum / ticker;
//
//        }
//
//    }

}
