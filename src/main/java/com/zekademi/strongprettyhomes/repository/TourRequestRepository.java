package com.zekademi.strongprettyhomes.repository;

import com.zekademi.strongprettyhomes.domain.TourRequest;
import com.zekademi.strongprettyhomes.domain.User;
import com.zekademi.strongprettyhomes.dto.TourRequestDTO;
import com.zekademi.strongprettyhomes.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

public interface TourRequestRepository extends JpaRepository<TourRequest, Long> {
    Optional<TourRequestDTO> findByIdOrderById(Long id) throws ResourceNotFoundException;

    Optional<TourRequestDTO> findByIdAndUserId(Long id, User user) throws ResourceNotFoundException;


    List<TourRequestDTO> findAllBy();
}
