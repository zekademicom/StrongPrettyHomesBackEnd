package com.zekademi.strongprettyhomes.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zekademi.strongprettyhomes.domain.enumeration.ReviewStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String review;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy", timezone = "Turkey")
    @NotNull(message = "Please enter the pick up time of the reservation")
    @Column(name = "activation_date", nullable = false)
    private LocalDate activationDate;  //=LocalDate.now();

    @Column
    private Integer score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    private Property property;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private ReviewStatus status;

    public Review(String review, Integer score, User user, Property property, ReviewStatus status) {
        this.review = review;
        this.score = score;
        this.property = property;
        this.user = user;
        this.status = status;
    }
}
