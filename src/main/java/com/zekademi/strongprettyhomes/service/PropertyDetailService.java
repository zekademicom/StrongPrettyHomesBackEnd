package com.zekademi.strongprettyhomes.service;

import com.zekademi.strongprettyhomes.domain.PropertyDetail;
import com.zekademi.strongprettyhomes.repository.PropertyDetailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PropertyDetailService {

    private final PropertyDetailRepository propertyDetailRepository;

    public void createPropertyDetail(PropertyDetail propertyDetail){
        propertyDetailRepository.save(propertyDetail);
    }

    public void updatePropertyDetail(PropertyDetail propertyDetail, String title){
        propertyDetail.setTitle(title);
        propertyDetailRepository.save(propertyDetail);
    }

    public void deletePropertyDetail(Long id){
        propertyDetailRepository.deleteById(id);
    }
}
