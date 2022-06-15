package com.zekademi.strongprettyhomes.controller;

import com.zekademi.strongprettyhomes.domain.Agent;
import com.zekademi.strongprettyhomes.dto.AgentDTO;
import com.zekademi.strongprettyhomes.service.AgentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/agent")
public class AgentController {
    public AgentService agentService;

    @PostMapping("/admin/{imageId}/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> create(@PathVariable String imageId,
                                                       @Valid @RequestBody Agent agent) {
        agentService.createAgent(agent, imageId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("Agent added successfully!", true);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AgentDTO> getAgent(@PathVariable Long id) {
        AgentDTO agent = agentService.findById(id);

        return new ResponseEntity<>(agent, HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AgentDTO>> getAllAgents() {
        List<AgentDTO> agents = agentService.fetchAllAgents();
        return new ResponseEntity<>(agents, HttpStatus.OK);
    }

    @PutMapping("/admin/auth")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> updateAgent(@RequestParam("id") Long id,
                                                            @RequestParam("agentImageId") String agentImageId,
                                                            @Valid @RequestBody Agent agent) {
        agentService.updateAgent(id, agentImageId, agent);

        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("admin/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> deleteAgent(@PathVariable Long id) {
        agentService.removeById(id);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
