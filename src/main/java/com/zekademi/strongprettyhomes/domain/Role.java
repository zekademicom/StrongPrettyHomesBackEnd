package com.zekademi.strongprettyhomes.domain;


import com.zekademi.strongprettyhomes.domain.enumeration.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private UserRole name;


//    @OneToMany(targetEntity=User.class, mappedBy="Role",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<User> user = new ArrayList<>();


    @Override
    public String toString() {
        return "" + name + '}';
    }
}
