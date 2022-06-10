package com.zekademi.strongprettyhomes.dto;

import com.zekademi.strongprettyhomes.domain.ImageDB;
import com.zekademi.strongprettyhomes.domain.Property;
import com.zekademi.strongprettyhomes.domain.enumeration.PropertyCategory;
import com.zekademi.strongprettyhomes.domain.enumeration.PropertyStatus;
import com.zekademi.strongprettyhomes.domain.enumeration.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

public class PropertyDTO {

    private Long id;

    private String title;

    private String description;

    private PropertyCategory category;

    private PropertyType type;

    private String bedrooms;

    private String bathrooms;

    private String garages;

    private Double area;

    private Double price;

    private String location;

    private String address;

    private String country;

    private String city;

    private String district;

    private Date createdDate;

    private Long likes;

    private Long visitCount;

    private PropertyStatus status;

    public PropertyDTO(Property property) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.type = type;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.garages = garages;
        this.area = area;
        this.price = price;
        this.location = location;
        this.address = address;
        this.country = country;
        this.city = city;
        this.district = district;
        this.createdDate = createdDate;
        this.likes = likes;
        this.visitCount = visitCount;
        this.status = status;
    }

    public Set<String> getImageId(Set<ImageDB> images) {
        Set<String> img = new HashSet<>();
        ImageDB[] imageDBs = images.toArray(new ImageDB[images.size()]);

        for (int i = 0; i < images.size(); i++) {
            img.add(imageDBs[i].getId());
        }
        return img;
    }
}
