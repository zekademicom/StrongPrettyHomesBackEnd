package com.zekademi.strongprettyhomes.controller;

import com.zekademi.strongprettyhomes.domain.Property;
import com.zekademi.strongprettyhomes.domain.Review;
import com.zekademi.strongprettyhomes.dto.ReviewDTO;
import com.zekademi.strongprettyhomes.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

    @PostMapping("/add")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> addReview(  HttpServletRequest request,
                                                            @Valid @RequestBody Review review,
                                                            @RequestParam(value = "propertyId") Property propertyId){

        Long id = (Long) request.getAttribute("id");
        reviewService.add(review,propertyId,id);
        Map<String, Boolean> map = new HashMap<>();
        map.put("Review added successfully!", true);
        return new ResponseEntity<>(map, HttpStatus.CREATED);

    }

    @GetMapping("/{id}/auth")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<ReviewDTO> getUserReviewById(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("id");
        ReviewDTO review = reviewService.findByIdAndUserId(id, userId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }
    @PutMapping("/update/auth")//?????
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> updateReview(@RequestParam(value = "reviewId") Long reviewId,
                                                             @Valid @RequestBody Review review,
                                                             @RequestParam(value = "propertyId") Property propertyId,
                                                             HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("id");
        reviewService.updateReview(reviewId,review,propertyId,userId);

        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @PatchMapping("/{id}/updateStatus")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> updateStatus(@RequestParam(value = "status") String status,
                                                             @PathVariable Long id) {

        reviewService.updateReviewStatus(status,id);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
  
    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> deleteReview(@PathVariable Long id) {
        reviewService.removeById(id);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);

    }
    @DeleteMapping("/{id}/auth")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> deleteUserReviewById(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("id");
        reviewService.removeUserReviewById(userId,id);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

 }
