package com.zekademi.strongprettyhomes.service;


import com.zekademi.strongprettyhomes.exception.ResourceNotFoundException;
import com.zekademi.strongprettyhomes.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReviewService {
     private ReviewRepository reviewRepository;

     private final static String REVIEW_NOT_FOUND_MSG = "Review with id %d not found";







    public void removeById(Long id) throws ResourceNotFoundException {


        reviewRepository.deleteById(id);



    }




}
