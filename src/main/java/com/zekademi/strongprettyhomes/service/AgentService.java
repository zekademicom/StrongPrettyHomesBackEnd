package com.zekademi.strongprettyhomes.service;
import com.zekademi.strongprettyhomes.domain.Agent;
import com.zekademi.strongprettyhomes.exception.BadRequestException;
import com.zekademi.strongprettyhomes.exception.ConflictException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AgentService {


    /*public void agentRegister(Agent agent) throws BadRequestException {
        if(agentRepository.existsByEmail(agent.getEmail())){
            throw new ConflictException("Error : Email is already in use!");
        }

    }*/

}
