//package com.zekademi.strongprettyhomes.service;
//
//import com.zekademi.strongprettyhomes.domain.ImageDB;
//import com.zekademi.strongprettyhomes.domain.Property;
//import com.zekademi.strongprettyhomes.dto.PropertyDTO;
//import com.zekademi.strongprettyhomes.exception.BadRequestException;
//import com.zekademi.strongprettyhomes.exception.ResourceNotFoundException;
//import com.zekademi.strongprettyhomes.repository.ImageRepository;
//import com.zekademi.strongprettyhomes.repository.PropertyRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@AllArgsConstructor
//@Service
//public class PropertyService {
//
//    private PropertyRepository propertyRepository;
//
//    private ImageRepository imageRepository;
//
//    private final static String PROPERTY_NOT_FOUND_MSG = "property with id %d not found";
//
//    private final static String IMAGE_NOT_FOUND_MSG = "property with id %s not found";
//
//    public List<PropertyDTO> fetchAllProperties() {
//        return propertyRepository.findAllProperty();
//    }
//
//    public PropertyDTO findById(Long id) throws ResourceNotFoundException {
//        return propertyRepository.findPropertyByIdx(id).orElseThrow(() ->
//                new ResourceNotFoundException(String.format(PROPERTY_NOT_FOUND_MSG, id)));
//    }
//
//    // TODO: 09/06/2022 agentid ve detailid ye gore degisecek
//    public void add(Property property, Long agentId, Long detailId) throws BadRequestException {
////        ImageDB imageDB = imageRepository.findById(imageId).orElseThrow(() ->
////                new ResourceNotFoundException(String.format(IMAGE_NOT_FOUND_MSG, imageId)));
//
//        Set<ImageDB> imageDBs = new HashSet<>();
//     //   imageDBs.add(imageDB);
//
//        property.setImage(imageDBs);
//        propertyRepository.save(property);
//    }
//
//    // TODO: 09/06/2022 agentid ve detailid ye gore degisecek
//    public void updateProperty(Long id, Property property, Long agentId, Long detailId) throws BadRequestException {
//        property.setId(id);
//      //  ImageDB imageDB = imageRepository.findById(imageId).get();
//
//        Property property1 = propertyRepository.getById(id);
//
//      //  if (property1.getBuiltIn())
//      //      throw new BadRequestException("You dont have permission to update property!");
//
//     //   property.setBuiltIn(false);
//
//        Set<ImageDB> imageDBs = new HashSet<>();
//      //  imageDBs.add(imageDB);
//
//        property.setImage(imageDBs);
//
//        propertyRepository.save(property);
//    }
//
//    // TODO: 09/06/2022   TourRequest olusturulacak
////    public void removeById(Long id) throws ResourceNotFoundException {
////        Property property = propertyRepository.findById(id).orElseThrow(() ->
////                new ResourceNotFoundException(String.format(PROPERTY_NOT_FOUND_MSG, id)));
////
////        if (property.getBuiltIn())
////            throw new BadRequestException("You dont have permission to delete car!");
////
////        boolean reservationExist = reservationRepository.existsByCarId(car);
////
////        if (reservationExist){
////            throw new ResourceNotFoundException("Reservation(s) exist for car!");
////        }
////
////        carRepository.deleteById(id);
////    }
//
//
//
//
//}
