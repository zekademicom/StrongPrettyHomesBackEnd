package com.zekademi.strongprettyhomes.repository;

import com.zekademi.strongprettyhomes.domain.Property;
import com.zekademi.strongprettyhomes.dto.PropertyDTO;
import com.zekademi.strongprettyhomes.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Transactional
    @Query("SELECT new com.zekademi.strongprettyhomes.dto.PropertyDTO(c) FROM Property c")
    List<PropertyDTO> findAllProperty();

    @Transactional
    @Query("SELECT new com.zekademi.strongprettyhomes.dto.PropertyDTO(c) FROM Property c WHERE c.id = ?1")
    Optional<PropertyDTO> findPropertyByIdx(Long id) throws ResourceNotFoundException;

    @Transactional
    @Query("SELECT c FROM Property c " +
            "LEFT JOIN fetch c.image img WHERE c.id = ?1")
    Optional<Property> findPropertyById(Long id) throws ResourceNotFoundException;

//    @Query("SELECT p FROM Property p WHERE CONCAT(p.title, ' ', p.type, ' ', p.status," +
//            " ' ', p.bedrooms, ' ', p.bathrooms, ' ', p.country, ' ', p.city," +
//            " ' ', p.district, ' ') LIKE %?1%")
//    List<Property> search(String keyword);

    List<PropertyDTO> findByTitleAndTypeAndStatusAndBedroomsAndBathroomsAndCountryAndCityAndDistrictAAndPriceBetween
            (String title, String type, String status, String bedrooms, String bathrooms,
             String country, String city, String district, double price1, double price2);

//    List<Property> findByPriceBetween(double price1, double price2);
//    List<Property> findByPriceLessThanEqual(double price2);
//    List<Property> findByPriceGreaterThanEqual(double price1);



}
