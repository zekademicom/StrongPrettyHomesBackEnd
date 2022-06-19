package com.zekademi.strongprettyhomes.controller;

import com.zekademi.strongprettyhomes.domain.Property;
import com.zekademi.strongprettyhomes.domain.TourRequest;
import com.zekademi.strongprettyhomes.domain.enumeration.TourRequestStatus;
import com.zekademi.strongprettyhomes.dto.TourRequestDTO;
import com.zekademi.strongprettyhomes.service.TourRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@RestController
@RequestMapping("/tour")
public class TourRequestController {

    public TourRequestService tourRequestService;

    @GetMapping("/{id}/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TourRequestDTO> getTourRequestById(@PathVariable Long id) {
        TourRequestDTO reservation = tourRequestService.findById(id);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @GetMapping("/{id}/auth")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<TourRequestDTO> getUserTourRequestById(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("id");
        TourRequestDTO reservation = tourRequestService.findByIdAndUserId(id, userId);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> makeTourRequest(HttpServletRequest request,
                                                                @RequestParam(value = "propertyId") Property propertyId,
                                                                @Valid @RequestBody TourRequest tourRequest) {

        Long userId = (Long) request.getAttribute("id");
        tourRequestService.addTourRequest(tourRequest, userId, propertyId);

        Map<String, Boolean> map = new HashMap<>();
        map.put("Reservation added successfully!", true);

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PostMapping("/add/auth")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> addTourRequest(@RequestParam(value = "userId") Long userId,
                                                               @RequestParam(value = "propertyId") Property propertyId,
                                                               @Valid @RequestBody TourRequest tourRequest) {
        tourRequestService.addTourRequest(tourRequest, userId, propertyId);

        Map<String, Boolean> map = new HashMap<>();
        map.put("Reservation added successfully!", true);

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PutMapping("/admin/auth")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> updateReservation(@RequestParam(value = "propertyId") Property propertyId,
                                                                  @RequestParam(value = "tourId") Long id,
                                                                  @Valid @RequestBody TourRequest requestTour) {
        tourRequestService.updateTourRequest(propertyId, id, requestTour);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PatchMapping("/admin/{id}/check_status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> checkStatusByAdmin(@PathVariable Long id,
                                                                   @RequestParam("status")TourRequestStatus status) {
        tourRequestService.checkRequestByAdmin(id, status);
        Map<String, Boolean> map = new HashMap<>();
        map.put("Request status updated successfully", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
    @PatchMapping("/{id}/check_status")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Map<String, Boolean>> checkStatusByUser(@PathVariable Long id,
                                                                   @RequestParam("status")TourRequestStatus status) {
        tourRequestService.checkRequestByUser(id, status);
        Map<String, Boolean> map = new HashMap<>();
        map.put("Request status updated successfully", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
    @DeleteMapping("/admin/{id}/auth")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> deleteReservation(@PathVariable Long id){
        tourRequestService.removeById(id);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    } 

    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TourRequestDTO>> getAllTourRequest() {
        List<TourRequestDTO> tourrequest = tourRequestService.fetchAllTourRequest();

        return new ResponseEntity<>(tourrequest, HttpStatus.OK);

    }
}
