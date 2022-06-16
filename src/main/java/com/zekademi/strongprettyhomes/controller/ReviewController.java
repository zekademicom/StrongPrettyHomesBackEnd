package com.zekademi.strongprettyhomes.controller;

import com.zekademi.strongprettyhomes.dto.ReviewDTO;
import com.zekademi.strongprettyhomes.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

import java.util.HashMap;


@AllArgsConstructor
@RestController

@Produces(MediaType.APPLICATION_JSON)
@RequestMapping("/reviews")
public class ReviewController {


    public ReviewService reviewService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
    public ResponseEntity<List<ReviewDTO>> getReviews(@RequestParam(value = "propertyId") Long propertyId) {
        List<ReviewDTO> reviews = reviewService.findAllByPropertyId(propertyId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);


    }

    @GetMapping("/{id}/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long id) {
        ReviewDTO review = reviewService.findById(id);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @GetMapping("/{id}/auth")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<ReviewDTO> getUserReviewById(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("id");
        ReviewDTO review = reviewService.findByIdAndUserId(id, userId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }
    @PatchMapping("/admin/auth")//?????
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> updateReview(@RequestParam(value = "reviewId") Review review) {

        reviewService.updateReview(review);

        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
    
    
     @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
    public ResponseEntity<Map<String, Boolean>> deleteReview(@PathVariable Long id) {
        reviewService.removeById(id);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);


    }
    
    
    
    
    
    
    
    
    
    
}

