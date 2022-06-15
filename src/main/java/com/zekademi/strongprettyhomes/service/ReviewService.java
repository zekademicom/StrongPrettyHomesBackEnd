package com.zekademi.strongprettyhomes.service;


import com.zekademi.strongprettyhomes.domain.Review;
import com.zekademi.strongprettyhomes.domain.User;
import com.zekademi.strongprettyhomes.domain.enumeration.UserRole;
import com.zekademi.strongprettyhomes.dto.ReviewDTO;
import com.zekademi.strongprettyhomes.exception.BadRequestException;
import com.zekademi.strongprettyhomes.exception.ResourceNotFoundException;
import com.zekademi.strongprettyhomes.repository.ReviewRepository;
import com.zekademi.strongprettyhomes.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final static String REVIEW_NOT_FOUND_MSG = "Review with id %d not found";
    private final UserRepository userRepository;
    private final static String USER_NOT_FOUND_MSG = "user with id %d not found";


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

    public void updateReview(Review review) throws BadRequestException {

        Review reviewExist = reviewRepository.findById(review.getId()).orElseThrow(() ->
                new ResourceNotFoundException(String.format(REVIEW_NOT_FOUND_MSG, review)));

        if (reviewExist.getUser().getRole().getName().equals(UserRole.ROLE_ADMIN)) {
            reviewExist.setStatus(review.getStatus());
        } else {
            throw new BadRequestException("You dont have permission to update status");
        }
        if (!review.getReview().equals(reviewExist.getReview()))
            reviewExist.setReview(review.getReview());//create de string deger problem cikartabilir
            reviewExist.setLocalDate(review.getLocalDate());

        reviewRepository.save(reviewExist);
    }


//        public void removeById (Long id) throws ResourceNotFoundException {
//            reviewRepository.deleteById(id);
//
//
//        }


}
