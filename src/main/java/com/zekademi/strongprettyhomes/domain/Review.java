package com.zekademi.strongprettyhomes.domain;


import com.zekademi.strongprettyhomes.domain.enumeration.ReviewStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Date;

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

//    @DateTimeFormat(pattern = "dd/MM/yyyy")
//    Date createDate;//const ekle


    private ZonedDateTime activation_date = ZonedDateTime.now();

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
