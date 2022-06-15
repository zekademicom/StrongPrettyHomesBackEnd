package com.zekademi.strongprettyhomes.repository;

import com.zekademi.strongprettyhomes.domain.AgentImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AgentImageRepository extends JpaRepository<AgentImage,String> {

}
