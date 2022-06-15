<<<<<<< HEAD
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


=======
package com.zekademi.strongprettyhomes.service;

import com.zekademi.strongprettyhomes.domain.User;
import com.zekademi.strongprettyhomes.dto.TourRequestDTO;
import com.zekademi.strongprettyhomes.exception.ResourceNotFoundException;
import com.zekademi.strongprettyhomes.repository.TourRequestRepository;
import com.zekademi.strongprettyhomes.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TourRequestService {

    private final TourRequestRepository tourRequestRepository;
    private final UserRepository userRepository;

    private final static String TOUR_REQUEST_NOT_FOUND_MSG = "tour request with id %d not found";
    private final static String USER_NOT_FOUND_MSG = "user with id %d not found";

    public TourRequestDTO findById(Long id) {
        return  tourRequestRepository.findByIdOrderById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(TOUR_REQUEST_NOT_FOUND_MSG, id)));
    }

    public TourRequestDTO findByIdAndUserId(Long id, Long userId) throws ResourceNotFoundException{
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(USER_NOT_FOUND_MSG, userId)));

        return tourRequestRepository.findByIdAndUserId(id, user).orElseThrow(() ->
                new ResourceNotFoundException(String.format(TOUR_REQUEST_NOT_FOUND_MSG, id)));
    }

}
>>>>>>> main
