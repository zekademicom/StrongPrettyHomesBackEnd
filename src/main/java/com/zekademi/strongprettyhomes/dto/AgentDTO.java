package com.zekademi.strongprettyhomes.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zekademi.strongprettyhomes.domain.Agent;
import com.zekademi.strongprettyhomes.domain.Property;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AgentDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "This field cannot be left blank.")
    @Size(max = 20)
    @NotNull(message="Please enter agent's first name")
    private String firstName;

    @NotEmpty(message = "This field cannot be left blank.")
    @Size(max = 20)
    @NotNull(message="Please enter agent's last name")
    private String lastName;

    @NotEmpty(message = "This field cannot be left blank.")
    @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",
            message = "Please enter valid phone number")
    @Size(min = 14, max = 14)
    @NotNull(message = "Please enter your phone number")
    private String phoneNumber;

    @NotEmpty(message = "This field cannot be left blank.")
    @Email
    @Size(min=8, max=120)
    @NotNull(message="Please enter agent's email")
    private String email;

    @JsonIgnore
    @Lob
    private byte[] image;

    private List<Property> properties;

    public AgentDTO(Agent agent) {
        this.id = agent.getId();
        this.firstName = agent.getFirstName();
        this.lastName = agent.getLastName();
        this.phoneNumber = agent.getPhoneNumber();
        this.email = agent.getEmail();
        this.image = getImage();
        this.properties = agent.getProperties();
    }
}
