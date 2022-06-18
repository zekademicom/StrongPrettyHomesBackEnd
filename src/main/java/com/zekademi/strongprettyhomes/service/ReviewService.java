package com.zekademi.strongprettyhomes.service;

import com.zekademi.strongprettyhomes.domain.Property;
import com.zekademi.strongprettyhomes.domain.Review;
import com.zekademi.strongprettyhomes.domain.User;
import com.zekademi.strongprettyhomes.dto.ReviewDTO;
import com.zekademi.strongprettyhomes.exception.BadRequestException;
import com.zekademi.strongprettyhomes.exception.ResourceNotFoundException;
import com.zekademi.strongprettyhomes.repository.PropertyRepository;
import com.zekademi.strongprettyhomes.repository.ReviewRepository;
import com.zekademi.strongprettyhomes.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class ReviewService {

    private final PropertyRepository propertyRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final static String PROPERTY_NOT_FOUND_MSG = "Property with id %d not found";
    private final static String USER_NOT_FOUND_MSG = "User with id %d not found";
    private final static String REVIEW_NOT_FOUND_MSG = "Review with id %d not found";

    public List<ReviewDTO> findAllByPropertyId(Long propertyId) {

        Property property = propertyRepository.findById(propertyId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(PROPERTY_NOT_FOUND_MSG, propertyId)));
        return reviewRepository.findAllByUserId(property);
    }

    public ReviewDTO findById(Long id) {
        return reviewRepository.findByIdOrderById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(REVIEW_NOT_FOUND_MSG, id)));
    }

    public ReviewDTO findByIdAndUserId(Long id, Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(USER_NOT_FOUND_MSG, userId)));
        return reviewRepository.findByIdAndUserId(id, user).orElseThrow(() ->
                new ResourceNotFoundException(String.format(REVIEW_NOT_FOUND_MSG, id)));
    }

  public void updateReview(Long reviewId,Review review,Property propertyId,Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(USER_NOT_FOUND_MSG, userId)));
        Review reviewExist=reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ResourceNotFoundException(String.format(REVIEW_NOT_FOUND_MSG,reviewId)));
        if(user.getRole().equals(UserRole.ROLE_ADMIN)){
            throw new BadRequestException("You dont have permission to update status") ;
        }else {
            reviewExist.setStatus(review.getStatus());
        }

        reviewExist.setUser(user);
        reviewExist.setReview(review.getReview());
        reviewExist.setScore(review.getScore());
        reviewExist.setActivationDate(review.getActivationDate());
        reviewExist.setProperty(propertyId);
        reviewRepository.save(reviewExist);
    }
        public void updateReviewStatus(String status,Long reviewId) {

        Review review = reviewRepository.findById(reviewId).orElseThrow(()-> new ResourceNotFoundException(String.format(REVIEW_NOT_FOUND_MSG,reviewId)));
        if(status.equalsIgnoreCase("PUBLISHED"))
        review.setStatus(ReviewStatus.PUBLISHED);
        else if(status.equalsIgnoreCase("REJECTED"))
            review.setStatus(ReviewStatus.REJECTED);
        reviewRepository.save(review);

    }

    public void removeById(Long id) throws ResourceNotFoundException {
        reviewRepository.deleteById(id);
    }
    
    public void removeUserReviewById(Long userId, Long id) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(USER_NOT_FOUND_MSG, userId)));
        reviewRepository.deleteReviewByIdAndUser(id,user);

    }

    public void add(Review review, Property propertyId, Long userId) throws BadRequestException {

        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(USER_NOT_FOUND_MSG, userId)));

        review.setProperty(propertyId);
        review.setUser(user);
        reviewRepository.save(review);
    }
}
