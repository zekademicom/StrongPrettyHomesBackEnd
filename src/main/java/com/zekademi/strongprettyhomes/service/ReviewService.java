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
     
    public void add(Review review,Property propertyId,Long userId) throws BadRequestException {

        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(USER_NOT_FOUND_MSG, userId)));

        review.setProperty(propertyId);
        review.setUser(user);
        reviewRepository.save(review);
    }

 public void updateReview(Review review) {

        Review reviewExist=reviewRepository.findById(review.getId())
                .orElseThrow(()-> new ResourceNotFoundException(String.format(REVIEW_NOT_FOUND_MSG,review)));
        if(!review.getUser().getRole().equals(UserRole.ROLE_ADMIN)){
           throw new BadRequestException("You dont have permission to update status") ;
        }else {
            reviewExist.setStatus(review.getStatus());
        }
        if(!reviewExist.getReview().equals(review.getReview())){
            reviewExist.setReview(review.getReview());
        }
        reviewRepository.save(reviewExist);
    }


    public void removeById(Long id) throws ResourceNotFoundException {
        reviewRepository.deleteById(id);

    }




}
