package com.zekademi.strongprettyhomes.repository;

import com.zekademi.strongprettyhomes.domain.Review;
import com.zekademi.strongprettyhomes.domain.User;
import com.zekademi.strongprettyhomes.dto.ReviewDTO;
import com.zekademi.strongprettyhomes.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ReviewRepository extends JpaRepository<Review,Long> {


    Optional<ReviewDTO> findByIdAndUserId(Long id, User user);

    Optional<ReviewDTO> findByIdOrderById(Long id) throws ResourceNotFoundException;






}
