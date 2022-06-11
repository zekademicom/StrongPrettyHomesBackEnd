package com.zekademi.strongprettyhomes.service;
import com.zekademi.strongprettyhomes.domain.Agent;
import com.zekademi.strongprettyhomes.domain.AgentImage;
import com.zekademi.strongprettyhomes.dto.AgentDTO;
import com.zekademi.strongprettyhomes.exception.BadRequestException;
import com.zekademi.strongprettyhomes.exception.ConflictException;
import com.zekademi.strongprettyhomes.exception.ResourceNotFoundException;
import com.zekademi.strongprettyhomes.repository.AgentImageRepository;
import com.zekademi.strongprettyhomes.repository.AgentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class AgentService {

    static public AgentRepository agentRepository;
    static public AgentImageRepository agentImageRepository;
    private final static String IMAGE_NOT_FOUND_MSG = "image with id %s not found";
    private final static String AGENT_NOT_FOUND_MSG = "agent with id %d not found";

    public void createAgent(Agent agent, String imageId) throws BadRequestException {
       AgentImage agentImage = agentImageRepository.findById(imageId).orElseThrow(
                () -> new ResourceNotFoundException(String.format(IMAGE_NOT_FOUND_MSG, imageId)));
        agent.setAgentImage(agentImage);
        agentRepository.save(agent);
    }
    public AgentDTO findById(Long id) {
        return agentRepository.findByIdOrderById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(AGENT_NOT_FOUND_MSG, id)));
    }
    public static List<AgentDTO> fetchAllAgents(){
        return agentRepository.findAllAgent();
    }

}
