package com.zekademi.strongprettyhomes.repository;

import com.zekademi.strongprettyhomes.domain.Property;
import com.zekademi.strongprettyhomes.domain.TourRequest;
import com.zekademi.strongprettyhomes.domain.User;
import com.zekademi.strongprettyhomes.domain.enumeration.TourRequestStatus;
import com.zekademi.strongprettyhomes.dto.TourRequestDTO;
import com.zekademi.strongprettyhomes.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface TourRequestRepository extends JpaRepository<TourRequest, Long> {

    List<TourRequestDTO> findAllByUserId(Long userId);

    @Transactional
    @Query("SELECT new com.zekademi.strongprettyhomes.dto.TourRequestDTO(r) FROM TourRequest r  WHERE r.id = ?1 and r.user.id = ?2")
    TourRequestDTO findUserTourRequestById(Long id, Long userId) throws ResourceNotFoundException;

    List<TourRequestDTO> findAllBy();

    Optional<TourRequestDTO> findByIdOrderById(Long id) throws ResourceNotFoundException;

    @Transactional
    @Query("SELECT r FROM TourRequest r " +
            "LEFT JOIN fetch r.property cd " +
            "LEFT JOIN fetch cd.image img " +
            "LEFT JOIN fetch r.user uid WHERE " +
            "(cd.id = ?1 and r.status <> ?3 and r.status <> ?4 and r.status <> ?5 and r.tourRequestTime = ?2)")
    List<TourRequest> checkStatus(Long carId, LocalDateTime requestTime, TourRequestStatus rejected,
                                  TourRequestStatus done, TourRequestStatus canceled);
    
    
    
    void deleteTourRequestByIdAndUser(Long id,User userId);
    
    @Query("SELECT new com.zekademi.strongprettyhomes.dto.TourRequestDTO(t) FROM TourRequest t WHERE t.user.id = ?1 and t.id=?2")
    Optional<TourRequestDTO> findTour(Long userId, Long id);
    
    
}
