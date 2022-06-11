package com.zekademi.strongprettyhomes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
}
