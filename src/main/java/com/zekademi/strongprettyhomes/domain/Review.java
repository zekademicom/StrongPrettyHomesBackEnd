package com.zekademi.strongprettyhomes.domain;


import com.zekademi.strongprettyhomes.domain.enumeration.ReviewStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    @Column(name="text", columnDefinition="LONGTEXT", length = 2000)
    private String review;

    @Column
    private Integer score;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private ReviewStatus status;

    public Review(String review, Integer score, Property property, User user, ReviewStatus status) {
        this.review = review;
        this.score = score;
        this.property = property;
        this.user = user;
        this.status = status;
    }
}
