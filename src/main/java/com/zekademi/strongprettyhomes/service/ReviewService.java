package com.zekademi.strongprettyhomes.service;


import com.zekademi.strongprettyhomes.exception.ResourceNotFoundException;
import com.zekademi.strongprettyhomes.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReviewService {
     
    private final PropertyRepository propertyRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final static String PROPERTY_NOT_FOUND_MSG = "property with id %d not found";
    private final static String USER_NOT_FOUND_MSG = "user with id %d not found";
    private final static String REVIEW_NOT_FOUND_MSG = "Review with id %d not found";

    public List<ReviewDTO> findAllByPropertyId(Long propertyId) {

        Property property = propertyRepository.findById(propertyId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(PROPERTY_NOT_FOUND_MSG, propertyId)));
        return reviewRepository.findAllByUserId(property);
    }

//deneme





























    public void removeById(Long id) throws ResourceNotFoundException {
        reviewRepository.deleteById(id);



    }




}
