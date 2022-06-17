package com.zekademi.strongprettyhomes.repository;

import com.zekademi.strongprettyhomes.domain.ImageDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageDB, String> {

}
