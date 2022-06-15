package com.zekademi.strongprettyhomes.service;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TourRequestService {

    @Autowired
    private final TourRequestRepository tourRequestRepository;

}
