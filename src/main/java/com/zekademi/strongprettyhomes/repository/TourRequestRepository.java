package com.zekademi.strongprettyhomes.repository;

import com.zekademi.strongprettyhomes.domain.TourRequest;
import com.zekademi.strongprettyhomes.dto.TourRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRequestRepository extends JpaRepository <TourRequest,Long> {

    List<TourRequestDTO> findAllBy();
}
