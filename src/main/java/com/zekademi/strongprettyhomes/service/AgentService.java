package com.zekademi.strongprettyhomes.service;
import com.zekademi.strongprettyhomes.domain.Agent;
import com.zekademi.strongprettyhomes.domain.AgentImage;
import com.zekademi.strongprettyhomes.exception.BadRequestException;
import com.zekademi.strongprettyhomes.exception.ConflictException;
import com.zekademi.strongprettyhomes.repository.AgentImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AgentService {

    private final AgentRepository agentRepository;
    private final AgentImageRepository agentImageRepository;
    private final static String IMAGE_NOT_FOUND_MSG = "image with id %s not found";
    private final static String CAR_NOT_FOUND_MSG = "car with id %d not found";






























    public void updateAgent(Long id, String agentImageId, Agent agent) throws BadRequestException {
        agent.setId(id);

        AgentImage agentImage = agentImageRepository.findById(agentImageId).get();

        Agent agentInfo = agentRepository.getById(id);

        if (agentInfo.getBuiltIn()) {
            throw new BadRequestException("You dont have permission to update any Agent!");
        }

        agent.setAgentImage(agentImage);
        agentRepository.save(agent);
    }

}
