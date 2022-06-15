package com.zekademi.strongprettyhomes.controller;


import com.zekademi.strongprettyhomes.dto.TourRequestDTO;
import com.zekademi.strongprettyhomes.service.TourRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@AllArgsConstructor
@RestController
@Produces(MediaType.APPLICATION_JSON)
@RequestMapping("/tourrequests")
public class TourRequestController {

    public TourRequestService tourrequestService;

    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TourRequestDTO>> getAllTourRequest() {
        List<TourRequestDTO> tourrequest = tourrequestService.fetchAllReservations();

        return new ResponseEntity<>(tourrequest, HttpStatus.OK);
    }

}
