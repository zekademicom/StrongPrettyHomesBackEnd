package com.zekademi.strongprettyhomes.controller;

import com.zekademi.strongprettyhomes.service.ExcelService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:8081")
@AllArgsConstructor
@RestController
@RequestMapping("/excel")
public class ExcelController {

    ExcelService excelService;

    @GetMapping("/download/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Resource> getUserFile() {
        String fileName = "customers.xlsx";
        InputStreamResource file = new InputStreamResource(excelService.loadUser());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
    }

    @GetMapping("/download/properties")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Resource> getPropertyFile() {
        String fileName = "properties.xlsx";
        InputStreamResource file = new InputStreamResource(excelService.loadProperty());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
    }

    @GetMapping("/download/agents")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Resource> getAgentFile() {
        String fileName = "agents.xlsx";
        InputStreamResource file = new InputStreamResource(excelService.loadAgent());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
    }

    @GetMapping("/download/reviews")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Resource> getReviewFile() {
        String fileName = "reviews.xlsx";
        InputStreamResource file = new InputStreamResource(excelService.loadReview());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
    }
    @GetMapping("/download/tourrequests")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Resource> getTourRequestFile() {
        String fileName = "tourrequests.xlsx";
        InputStreamResource file = new InputStreamResource(excelService.loadTourRequest());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
    }
}
