package com.zekademi.strongprettyhomes.service;
import com.zekademi.strongprettyhomes.domain.Agent;
import com.zekademi.strongprettyhomes.domain.AgentImage;
import com.zekademi.strongprettyhomes.dto.AgentDTO;
import com.zekademi.strongprettyhomes.exception.BadRequestException;
import com.zekademi.strongprettyhomes.exception.ResourceNotFoundException;
import com.zekademi.strongprettyhomes.repository.AgentImageRepository;
import com.zekademi.strongprettyhomes.repository.AgentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;



@AllArgsConstructor
@Service
public class AgentService {

    
    
    private  final AgentRepository agentRepository;
    private final AgentImageRepository agentImageRepository;
    private final static String IMAGE_NOT_FOUND_MSG = "image with id %s not found";
    private final static String AGENT_NOT_FOUND_MSG = "Agent with id %d not found";

    
    
    
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
    public List<AgentDTO> fetchAllAgents(){
        return agentRepository.findAllAgent();
    }


    public void removeById(Long id) throws ResourceNotFoundException {
//     Agent agent =agentRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException(String.format(Agent_NOT_FOUND_MSG, id)));
//
//        boolean tourRequestExist = tourRequestExistRepository.existsBytourRequestId(tourRequest);
//
//        if (tourRequestExist){
//            throw new ResourceNotFoundException("Tour request exist for this agent!");
//        }

        agentRepository.deleteById(id);
    }
    
    
    
}
