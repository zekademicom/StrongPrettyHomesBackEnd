package com.zekademi.strongprettyhomes.repository;

import com.zekademi.strongprettyhomes.domain.PropertyDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyDetailRepository extends JpaRepository<PropertyDetail, Long> {
}
