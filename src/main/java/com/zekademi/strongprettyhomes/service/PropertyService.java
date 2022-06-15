package com.zekademi.strongprettyhomes.service;

import com.zekademi.strongprettyhomes.domain.Agent;
import com.zekademi.strongprettyhomes.domain.ImageDB;
import com.zekademi.strongprettyhomes.domain.Property;
import com.zekademi.strongprettyhomes.domain.PropertyDetail;
import com.zekademi.strongprettyhomes.dto.PropertyDTO;
import com.zekademi.strongprettyhomes.exception.BadRequestException;
import com.zekademi.strongprettyhomes.exception.ResourceNotFoundException;
import com.zekademi.strongprettyhomes.repository.AgentRepository;
import com.zekademi.strongprettyhomes.repository.ImageRepository;
import com.zekademi.strongprettyhomes.repository.PropertyDetailRepository;
import com.zekademi.strongprettyhomes.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class PropertyService {

    private PropertyRepository propertyRepository;
    private ImageRepository imageRepository;
    private AgentRepository agentRepository;
    private PropertyDetailRepository propertyDetailRepository;

    private final static String PROPERTY_NOT_FOUND_MSG = "property with id %d not found";
    private final static String PROPERTY_DETAILS_NOT_FOUND_MSG = "property details with id %d not found";
    private final static String AGENT_NOT_FOUND_MSG = "agent with id %d not found";
    private final static String IMAGE_NOT_FOUND_MSG = "property with id %s not found";
    private final static String PRICE_DOESNT_MATCH = "It doesnt must price 1 %d grater than price 2 %d";


    public List<PropertyDTO> fetchAllProperties(){
        return propertyRepository.findAllProperty();
    }

    public void add(Property property, Agent agentId) throws BadRequestException {

        Agent agent = agentRepository.findById(agentId.getId()).orElseThrow(() ->
                new ResourceNotFoundException(String.format(AGENT_NOT_FOUND_MSG, agentId.getId())));
        property.setAgent(agent);

//        PropertyDetail propertyDetail = propertyDetailRepository.findById(detailId).orElseThrow(() ->
//                new ResourceNotFoundException(String.format(AGENT_NOT_FOUND_MSG, detailId)));
//        Set<PropertyDetail> details = new HashSet<>();
//        details.add(propertyDetail);
//        property.setPropertyDetails(details);
 //       property.setBuiltIn(false);
        propertyRepository.save(property);
    }

    public void updateProperty(Long id, Property property, Long agentId, Long detailId) throws BadRequestException {
        property.setId(id);
        Agent agent = agentRepository.findById(agentId).get();
        PropertyDetail propertyDetail = propertyDetailRepository.findById(detailId).get();

//        Property property1 = propertyRepository.getById(id);
//        if (property1.getBuiltIn())
//            throw new BadRequestException("You dont have permission to update property!");
//        property.setBuiltIn(false);

        Set<PropertyDetail> propertyDetails = new HashSet<PropertyDetail>();
        propertyDetails.add(propertyDetail);

        property.setPropertyDetails(propertyDetails);
        property.setAgent(agent);

        propertyRepository.save(property);
    }

     public void removeById(Long id) throws ResourceNotFoundException {
       // Property property = propertyRepository.findById(id).orElseThrow(() ->
              // new ResourceNotFoundException(String.format(PROPERTY_NOT_FOUND_MSG, id)));

      //  if (property.getBuiltIn()) {
       //     throw new BadRequestException("You dont have permission to delete property!");
      //  }

      // boolean reservationExist = propertyRepository.existsByProperty(property);

//       if (reservationExist){
//          throw new ResourceNotFoundException("Reservation(s) exist for property!"); }
        propertyRepository.deleteById(id);
   }

//    // TODO: 11/06/2022 querylerle olusturuldu. birkac farkli yontem var. deneyip gorecegiz :)
//    public List<PropertyDTO> searchList(String title, String type, String status, String bedrooms, String bathrooms,
//                                        String country, String city, String district, double price1, double price2) {
//
//        if (title != null || type != null || status != null || bedrooms != null ||
//                bathrooms != null || country != null || city != null || district != null
//                || price1 != 0 || price2 != 0)
//            return propertyRepository.findByTitleAndTypeAndStatusAndBedroomsAndBathroomsAndCountryAndCityAndDistrictAAndPriceBetween
//                    (title, type, status, bedrooms, bathrooms, country, city, district, price1, price2);
//
//        /** String keyword, double price1, double price2*/
////        if (keyword != null && price1 == 0 && price2 == 0) {
////            return propertyRepository.search(keyword);
////        }
////        if (price1 != 0 && price2 != 0) {
////            if (price2 >= price1) return propertyRepository.findByPriceBetween(price1, price2);
////            else throw new BadRequestException(String.format(PRICE_DOESNT_MATCH, price1, price2));
////        }
////        if (price1 != 0 && price2 == 0) {
////            return propertyRepository.findByPriceGreaterThanEqual(price1);
////        }
////        if (price1 == 0 && price2 != 0) {
////            return propertyRepository.findByPriceLessThanEqual(price2);
////        }
//        return propertyRepository.findAllProperty();
//    }

}
