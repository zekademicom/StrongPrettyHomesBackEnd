package com.zekademi.strongprettyhomes.controller;

import com.zekademi.strongprettyhomes.domain.Property;
import com.zekademi.strongprettyhomes.dto.PropertyDTO;
import com.zekademi.strongprettyhomes.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@RequestMapping("/property")

 public class PropertyController {

  public PropertyService propertyService;
//
//    @GetMapping("/visitors/all")
//    public ResponseEntity<List<PropertyDTO>> getAllCars(){
//        List<PropertyDTO> properties = propertyService.fetchAllProperties();
//        return new ResponseEntity<>(properties, HttpStatus.OK);
//    }
//
//    @GetMapping("/visitors/{id}")
//    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable Long id){
//        PropertyDTO properties = propertyService.findById(id);
//        return new ResponseEntity<>(properties, HttpStatus.OK);
//    }
//
//    @PostMapping("/admin/{id}/add")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Map<String, Boolean>> addProperty(@PathVariable String id,
//                                                       @Valid @RequestBody Property property) {
//      //  propertyService.add(property, id);
//        Map<String, Boolean> map = new HashMap<>();
//        map.put("User registered successfully!", true);
//        return new ResponseEntity<>(map, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/admin/auth")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Map<String, Boolean>> updateProperty(@RequestParam("id") Long id,
//                                                          @RequestParam("imageId") String imageId,
//                                                          @Valid @RequestBody Property property) {
//      //  propertyService.updateProperty(id, property, imageId);
//        Map<String, Boolean> map = new HashMap<>();
//        map.put("success", true);
//        return new ResponseEntity<>(map, HttpStatus.OK);
//    }



      @DeleteMapping("/admin/{id}/auth")
      @PreAuthorize("hasRole('ADMIN')")
      public ResponseEntity<Map<String, Boolean>> deleteProperty(@PathVariable Long id){
        propertyService.removeById(id);
        Map<String, Boolean> map = new HashMap<>();
       map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
   }

}
