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

@Table(name = "imgs")
public class ImageDB {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String type;

    @JsonIgnore
    @Lob
    private byte[] image;

    private Boolean featured;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    public ImageDB(String name, String type, byte[] image) {
        this.name = name;
        this.type = type;
        this.image = image;
    }

}