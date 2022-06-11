package com.zekademi.strongprettyhomes.controller;

import com.zekademi.strongprettyhomes.domain.Agent;
import com.zekademi.strongprettyhomes.service.AgentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@RequestMapping(path="/agent")
public class AgentController {
    public AgentService agentService;

    /*@PostMapping("/agent/register")
    public ResponseEntity<Map<String, Boolean>> registerAgent(@Valid @RequestBody Agent agent){
        agentService.registerAgent(agent);
        Map<String, Boolean> map = new HashMap<>();
        map.put("Agent registered successfully!", true);

        return new ResponseEntity<>(map, HttpStatus.CREATED);

    }*/




}
