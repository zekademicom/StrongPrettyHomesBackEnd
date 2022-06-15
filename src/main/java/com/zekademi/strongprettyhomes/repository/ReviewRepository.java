package com.zekademi.strongprettyhomes.repository;


import com.zekademi.strongprettyhomes.domain.Property;
import com.zekademi.strongprettyhomes.domain.Review;
import com.zekademi.strongprettyhomes.domain.User;
import com.zekademi.strongprettyhomes.dto.ReviewDTO;
import com.zekademi.strongprettyhomes.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<ReviewDTO> findAllByUserId(Property propertyId);

    Optional<ReviewDTO> findByIdAndUserId(Long id, User user);

    Optional<ReviewDTO> findByIdOrderById(Long id) throws ResourceNotFoundException;

}
