package com.zekademi.strongprettyhomes.repository;

import com.zekademi.strongprettyhomes.domain.Agent;
import com.zekademi.strongprettyhomes.dto.AgentDTO;
import com.zekademi.strongprettyhomes.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    Optional<AgentDTO> findByIdOrderById(Long id) throws ResourceNotFoundException;
}
