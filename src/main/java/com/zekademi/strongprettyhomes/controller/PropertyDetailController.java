package com.zekademi.strongprettyhomes.controller;

import com.zekademi.strongprettyhomes.domain.PropertyDetail;
import com.zekademi.strongprettyhomes.service.PropertyDetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/detail")
public class PropertyDetailController {

    private final PropertyDetailService propertyDetailService;

    @PostMapping("/auth/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> createDetail(@Valid @RequestBody PropertyDetail propertyDetail) {
        propertyDetailService.createPropertyDetail(propertyDetail);

        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PatchMapping("/auth/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> updateDetail(@Valid @RequestBody PropertyDetail propertyDetail,
                                                             @RequestParam("title") String title) {
        propertyDetailService.updatePropertyDetail(propertyDetail, title);

        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @DeleteMapping("/auth/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> deleteDetail(@PathVariable Long id) {
        propertyDetailService.deletePropertyDetail(id);

        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
}
