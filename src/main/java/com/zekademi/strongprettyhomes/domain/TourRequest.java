package com.zekademi.strongprettyhomes.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Entity
public class TourRequest {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
