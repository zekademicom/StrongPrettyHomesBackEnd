package com.zekademi.strongprettyhomes.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table(name= "agents")
    public class Agent {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @NotEmpty(message = "This field cannot be left blank.")
        @Size(max = 20)
        @NotNull(message="Please enter agent's first name")
        @Column(nullable= false, length= 20)
        private String firstName;
        @NotEmpty(message = "This field cannot be left blank.")
        @Size(max = 20)
        @NotNull(message="Please enter agent's last name")
        @Column(nullable= false, length=20)
        private String lastName;
        @NotEmpty(message = "This field cannot be left blank.")
        @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",
                message = "Please enter valid phone number")
        @Size(min = 14, max = 14)
        @NotNull(message = "Please enter your phone number")
        @Column(nullable = false, length = 14)
        private String phoneNumber;
        @NotEmpty(message = "This field cannot be left blank.")
        @Email
        @Size(min=8, max=120)
        @NotNull(message="Please enter agent's email")
        @Column(nullable = false, unique = true, length= 120)
        private String email;

//        @JsonIgnore
//        @Lob
//        private byte[] image;
        /*
        @OneToOne
        private Image imgAgent;
        */
        /*@OneToMany(
                fetch = FetchType.LAZY,
                mappedBy="agent",
                orphanRemoval= true)
        private List<Property> properties = new ArrayList<>();*/





        public boolean getBuiltIn() {
            return false;
        }





        @OneToOne(cascade = CascadeType.PERSIST)
        @JoinColumn(name = "agent_id", referencedColumnName = "id")
        private AgentImage agentImage;

        public Agent(String firstName, String lastName, String phoneNumber, String email, byte[] image) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.email = email;
            //this.properties = properties;
            this.image = image;
        }
    }


