package com.zekademi.strongprettyhomes.controller;

import com.zekademi.strongprettyhomes.domain.AgentImage;
import com.zekademi.strongprettyhomes.service.AgentImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:8081")
@RequestMapping(path = "/agentImg")
public class AgentImageController {

    private final AgentImageService agentImageService;

    @PostMapping("/upload")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            AgentImage agentImage = agentImageService.store(file);
            Map<String, String> map = new HashMap<>();
            map.put("imageId", agentImage.getId());
            return ResponseEntity.status(HttpStatus.OK).body(map);
        } catch (IOException e) {
            Map<String, String> map = new HashMap<>();
            map.put("message", "Could not upload the file: " + file.getOriginalFilename() + "!");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(map);
        }
    }
    @GetMapping("/display/{id}")
    public ResponseEntity<byte[]> displayImage(@PathVariable String id) {
        AgentImage agentImage = agentImageService.getFileById(id);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(agentImage.getData(), headers, HttpStatus.CREATED);
    }

}
