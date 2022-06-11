package com.zekademi.strongprettyhomes.repository;

import com.zekademi.strongprettyhomes.domain.Agent;
import com.zekademi.strongprettyhomes.dto.AgentDTO;
import com.zekademi.strongprettyhomes.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional


public interface AgentRepository extends JpaRepository<Agent, Long> {

    Optional<AgentDTO> findByIdOrderById(Long id) throws ResourceNotFoundException;

    //@Query("SELECT new com.zekademi.strongprettyhomes.dto.AgentDTO(agent) FROM Agent agent")
    List<AgentDTO> findAllAgent();
}
