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
        
        boolean checkStatus = homeAvailability(propertyId.getId(), tourRequest.getTourRequestTime());

        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(USER_NOT_FOUND_MSG, userId)));

        if (!checkStatus) tourRequest.setStatus(TourRequestStatus.PENDING);
        else throw new BadRequestException("Property is already reserved! Please choose another Time");
        
        LocalDateTime today = LocalDateTime.now();
        if (today.compareTo(tourRequest.getTourRequestTime()) > 0)
            throw new BadRequestException("Invalid time and date !!!");

        tourRequest.setProperty(propertyId);
        tourRequest.setUser(user);

        tourRequestRepository.save(tourRequest);
    }

    public void updateTourRequest(Property propertyId, Long id, TourRequest tourRequest) throws BadRequestException {

        boolean checkStatus = homeAvailability(propertyId.getId(), tourRequest.getTourRequestTime());

        Optional<TourRequest> reservationExist = tourRequestRepository.findById(id);

        if (reservationExist.isEmpty()) {
            throw new ConflictException("Error: Reservation does not exist!");
        }

        if (tourRequest.getTourRequestTime().compareTo(reservationExist.get().getTourRequestTime()) == 0)
            System.out.println();
        else if (checkStatus)
            throw new BadRequestException("This home is already reserved! Please choose another");
        
        LocalDateTime today = LocalDateTime.now();
        if (today.compareTo(tourRequest.getTourRequestTime()) > 0)
            throw new BadRequestException("Invalid time and date !!!");

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

    public void checkRequestByAdmin(Long id, TourRequestStatus status) {
        TourRequest existRequest = tourRequestRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Tour request not found"));

        LocalDateTime today = LocalDateTime.now();
        if (existRequest.getStatus().equals(TourRequestStatus.PENDING) &&
                today.plusHours(1).isBefore(existRequest.getTourRequestTime()) &&
                (status.equals(TourRequestStatus.APPROVED) ||
                        status.equals(TourRequestStatus.REJECTED))) {

            existRequest.setStatus(status);

        } else if (existRequest.getStatus().equals(TourRequestStatus.PENDING) &&
                today.plusHours(1).isAfter(existRequest.getTourRequestTime())) {

            existRequest.setStatus(TourRequestStatus.APPROVED);

        } else {
            throw new BadRequestException("Check cannot be made");
        }
        tourRequestRepository.save(existRequest);
    }

    public void checkRequestByUser(Long id, TourRequestStatus status, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User not found!"));
        TourRequest existRequest = tourRequestRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Tour request not found"));
        Optional<TourRequest> userRequest = user.getTourRequests().stream().filter(t -> t.getId() ==
                existRequest.getId()).findFirst();

        if (userRequest.isEmpty()) throw new ResourceNotFoundException("not found request");

        LocalDateTime today = LocalDateTime.now();
        if ((userRequest.get().getStatus().equals(TourRequestStatus.PENDING) ||
                userRequest.get().getStatus().equals(TourRequestStatus.APPROVED)) &&
                today.plusHours(1).isBefore(existRequest.getTourRequestTime()) &&
                status.equals(TourRequestStatus.CANCELED)) {
            existRequest.setStatus(status);
            tourRequestRepository.save(existRequest);
        } else {
            throw new BadRequestException("Tour request cannot cancel");
        }
    }
    
    public void removeById(Long id) throws ResourceNotFoundException {
        boolean reservationExists = tourRequestRepository.existsById(id);
        if (!reservationExists) throw new ResourceNotFoundException("reservation does not exist");
        tourRequestRepository.deleteById(id);
    }
    
     public void removeUserTourRequestById(Long userId, Long id) {
        User user = userRepository.findById(userId).orElseThrow(() ->
               new ResourceNotFoundException(String.format(USER_NOT_FOUND_MSG, userId)));
        Optional<TourRequestDTO> tours = tourRequestRepository.findTour(userId,id);
    //      if(reviews.isEmpty()){
    //        throw new BadRequestException("You are unauthorized to delete this review!");
    //     }
        tourRequestRepository.deleteUserTourRequestById(id,user);

    }

    public List<TourRequestDTO> fetchAllTourRequest() {
        return tourRequestRepository.findAllBy();
    }
    
    public List<TourRequestDTO> fetchAllTourRequestByUser(Long userId) {
        return tourRequestRepository.findAllByUserId(userId);
    }
}

