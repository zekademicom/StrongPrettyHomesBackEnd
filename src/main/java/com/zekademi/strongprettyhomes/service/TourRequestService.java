package com.zekademi.strongprettyhomes.service;

import com.zekademi.strongprettyhomes.domain.Property;
import com.zekademi.strongprettyhomes.domain.TourRequest;
import com.zekademi.strongprettyhomes.domain.User;
import com.zekademi.strongprettyhomes.domain.enumeration.TourRequestStatus;
import com.zekademi.strongprettyhomes.dto.TourRequestDTO;
import com.zekademi.strongprettyhomes.exception.BadRequestException;
import com.zekademi.strongprettyhomes.exception.ConflictException;
import com.zekademi.strongprettyhomes.exception.ResourceNotFoundException;
import com.zekademi.strongprettyhomes.repository.PropertyRepository;
import com.zekademi.strongprettyhomes.repository.TourRequestRepository;
import com.zekademi.strongprettyhomes.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TourRequestService {

    private final TourRequestRepository tourRequestRepository;
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;

    private final static String TOUR_REQUEST_NOT_FOUND_MSG = "tour request with id %d not found";
    private final static String USER_NOT_FOUND_MSG = "user with id %d not found";

    public TourRequestDTO findById(Long id) {
        return tourRequestRepository.findByIdOrderById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(TOUR_REQUEST_NOT_FOUND_MSG, id)));
    }

    public TourRequestDTO findByIdAndUserId(Long id, Long userId) throws ResourceNotFoundException {
        return tourRequestRepository.findUserTourRequestById(id, userId);
    }

    public void addTourRequest(TourRequest tourRequest, Long userId, Property propertyId) throws BadRequestException {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(USER_NOT_FOUND_MSG, userId)));

        tourRequest.setStatus(TourRequestStatus.PENDING);
        tourRequest.setProperty(propertyId);
        tourRequest.setUser(user);
        tourRequestRepository.save(tourRequest);
    }

    public void updateTourRequest(Property propertyId, Long id, TourRequest tourRequest) throws BadRequestException {

        boolean checkStatus = homeAvailability(propertyId.getId(), tourRequest.getTourRequestTime());

        Optional<TourRequest> reservationExist = tourRequestRepository.findById(id);

        if (!(reservationExist.isPresent())) {
            throw new ConflictException("Error: Reservation does not exist!");
        }

        if (tourRequest.getTourRequestTime().compareTo(reservationExist.get().getTourRequestTime()) == 0)
            System.out.println();
        else if (checkStatus)
            throw new BadRequestException("This home is already reserved! Please choose another");


        reservationExist.get().setProperty(propertyId);
        reservationExist.get().setTourRequestTime(tourRequest.getTourRequestTime());
        reservationExist.get().setChild(tourRequest.getChild());
        reservationExist.get().setAdult(tourRequest.getAdult());
        reservationExist.get().setStatus(tourRequest.getStatus());

        tourRequestRepository.save(reservationExist.get());
    }


    public boolean homeAvailability(Long propertyId, LocalDateTime tourTime) {
        List<TourRequest> checkStatus = tourRequestRepository.checkStatus(propertyId, tourTime,
                TourRequestStatus.REJECTED, TourRequestStatus.DONE, TourRequestStatus.CANCELED);
        return checkStatus.size() > 0;
    }

    public void checkRequestByAdmin(Long id, TourRequestStatus status){
        TourRequest existRequest = tourRequestRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Tour request not found"));

        if (existRequest.getStatus().equals(TourRequestStatus.PENDING)){
            existRequest.setStatus(status);
            if (status.equals(TourRequestStatus.APPROVED))tourRequestRepository.save(existRequest);
            else throw new BadRequestException("Only adjust approved");
        }else throw new BadRequestException("User request not pending");
    }

    public List<TourRequestDTO> fetchAllTourRequest() {
        return tourRequestRepository.findAllBy();
    }
}