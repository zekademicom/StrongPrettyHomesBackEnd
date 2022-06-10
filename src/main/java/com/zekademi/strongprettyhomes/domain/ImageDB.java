package com.zekademi.strongprettyhomes.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "images")

public class ImageDB {

    @Id
    @GeneratedValue(generator= "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @JsonIgnore
    @Lob
    private byte[] image;

    private Boolean featured;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;


<<<<<<< HEAD:src/main/java/com/zekademi/strongprettyhomes/domain/ImageDB.java
=======
    public ImageDB(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
>>>>>>> main:src/main/java/com/zekademi/strongprettyhomes/domain/FileDB.java
}
