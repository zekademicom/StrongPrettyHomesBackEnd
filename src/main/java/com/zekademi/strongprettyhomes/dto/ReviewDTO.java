package com.zekademi.strongprettyhomes.dto;

import com.zekademi.strongprettyhomes.domain.Property;
import com.zekademi.strongprettyhomes.domain.Review;
import com.zekademi.strongprettyhomes.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Setter
@Getter
public class ReviewDTO {


    private Long id;


    private String review;


    private Integer score;


    private Long property;


    private User user;

    public ReviewDTO(Review review){
        this.id = review.getId();
        this.review = review.getReview();
        this.score = review.getScore();
        this.property = review.getProperty().getId();
        this.user = review.getUser();
    }



}
