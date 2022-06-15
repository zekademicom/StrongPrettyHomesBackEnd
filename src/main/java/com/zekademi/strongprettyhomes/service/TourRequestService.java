package com.zekademi.strongprettyhomes.service;


import com.zekademi.strongprettyhomes.dto.TourRequestDTO;
import com.zekademi.strongprettyhomes.repository.TourRequestRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TourRequestService {


    private final TourRequestRepository tourRequestRepository;

    public List<TourRequestDTO> fetchAllTourRequest() {
        return tourRequestRepository.findAllBy();
    }


}


