package com.zekademi.strongprettyhomes.repository;

import com.zekademi.strongprettyhomes.domain.Like;
import com.zekademi.strongprettyhomes.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
       Boolean existsByPropertyIdAndUserId(Long propertyId, Long userId);
       Like findByPropertyIdAndUserId(Long propertyId, Long userId);
}
