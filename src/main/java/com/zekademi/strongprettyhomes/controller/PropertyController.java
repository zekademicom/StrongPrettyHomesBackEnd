package com.zekademi.strongprettyhomes.controller;


import com.zekademi.strongprettyhomes.domain.Agent;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/property")
public class PropertyController {

    public PropertyService propertyService;

    @GetMapping("/visitors/all")
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        List<PropertyDTO> properties = propertyService.fetchAllProperties();
        return new ResponseEntity<List<PropertyDTO>>(properties, HttpStatus.OK);
    }

    @PostMapping("/admin/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> addProperty(@RequestParam(value = "agentId") Agent agentId,
                                                            @Valid @RequestBody Property property) {

        propertyService.add(property, agentId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("Property created successfully!", true);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PutMapping("/admin/auth")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> updateProperty(@RequestParam("id") Long id,
                                                               @Valid @RequestBody Property property,
                                                               @RequestParam("agentId")  Long agentId,
                                                               @RequestParam("detailId") Long detailId
                                                          ) {
        propertyService.updateProperty(id, property, agentId, detailId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);

    }

      @DeleteMapping("/admin/{id}/auth")
      @PreAuthorize("hasRole('ADMIN')")
      public ResponseEntity<Map<String, Boolean>> deleteProperty(@PathVariable Long id){
        propertyService.removeById(id);
        Map<String, Boolean> map = new HashMap<>();
       map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
   }

    @GetMapping("/visitors/{id}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable Long id){
        PropertyDTO properties = propertyService.findById(id);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }



}
