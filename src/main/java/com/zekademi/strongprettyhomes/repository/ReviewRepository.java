package com.zekademi.strongprettyhomes.repository;

import com.zekademi.strongprettyhomes.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
@Transactional
public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<ReviewDTO> findAllByUserId(Property propertyId);

    Optional<ReviewDTO> findByIdAndUserId(Long id, User user);

    Optional<ReviewDTO> findByIdOrderById(Long id) throws ResourceNotFoundException;







}
