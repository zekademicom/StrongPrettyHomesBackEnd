package com.zekademi.strongprettyhomes.dto;

import com.zekademi.strongprettyhomes.domain.Property;
import com.zekademi.strongprettyhomes.domain.Review;
import com.zekademi.strongprettyhomes.domain.User;
import com.zekademi.strongprettyhomes.domain.enumeration.ReviewStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ReviewDTO {
    private Long id;
    @Lob
    private String review;
    private LocalDate localDate;
    private Integer score;
    private Long propertyId;
    private Long userId;
    private ReviewStatus status;


    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.review = review.getReview();
        this.score = review.getScore();
        this.propertyId = review.getProperty().getId();
        this.userId = review.getUser().getId();
        this.status = review.getStatus();
    }
    public String convertDateToString(Review review){//date string e donusturuldu
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String date=df.format(review.getActivationDate());
        return date;

    }

}

