package com.zekademi.strongprettyhomes.repository;

import com.zekademi.strongprettyhomes.domain.User;
import com.zekademi.strongprettyhomes.exception.BadRequestException;
import com.zekademi.strongprettyhomes.exception.ResourceNotFoundException;
import com.zekademi.strongprettyhomes.projection.ProjectUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository <User,Long>{

    Boolean existsByEmail(String email) throws ResourceNotFoundException;


    Optional<User> findByEmail(String email) throws ResourceNotFoundException;

    List<ProjectUser> findAllBy();

    @Modifying //kendimiz yazdık Jpa dan hazır da alınabilirdi
    @Query("UPDATE User u SET u.firstName=?2,u.lastName=?3,u.phoneNumber=?4,u.email=?5,u.address=?6,u.zipCode=?7 where u.id=?1")
    void update(Long id, String firstName, String lastName, String phoneNumber, String email, String address,
                String zipCode) throws BadRequestException;


}
