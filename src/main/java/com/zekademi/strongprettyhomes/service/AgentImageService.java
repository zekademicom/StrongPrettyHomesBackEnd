package com.zekademi.strongprettyhomes.service;

import com.zekademi.strongprettyhomes.domain.AgentImage;
import com.zekademi.strongprettyhomes.repository.AgentImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@AllArgsConstructor
@Service
public class AgentImageService {
    private final AgentImageRepository agentImageRepository;

    public AgentImage store(MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        AgentImage agentImg = new AgentImage(fileName, file.getContentType(), file.getBytes());
        agentImageRepository.save(agentImg);
        return agentImg;
    }

    public AgentImage getFileById(String id) {
        return agentImageRepository.findById(id).get();
    }
}
