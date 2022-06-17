package com.zekademi.strongprettyhomes.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zekademi.strongprettyhomes.domain.Property;
import com.zekademi.strongprettyhomes.domain.TourRequest;
import com.zekademi.strongprettyhomes.domain.enumeration.TourRequestStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TourRequestDTO {

    private Long id;

    private LocalDateTime tourRequestTime;

    private String adult;

    private String child;

    private Long userId;

    private PropertyDTO property;

    private TourRequestStatus status;

    public TourRequestDTO(TourRequest tourRequest) {
        this.id = tourRequest.getId();
        this.tourRequestTime = tourRequest.getTourRequestTime();
        this.adult = tourRequest.getAdult();
        this.child = tourRequest.getChild();
        this.property = new PropertyDTO(tourRequest.getProperty());
        this.userId = tourRequest.getUser().getId();
        this.status = tourRequest.getStatus();
    }
}