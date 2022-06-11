package com.zekademi.strongprettyhomes.dto;

import com.zekademi.strongprettyhomes.domain.Agent;
import com.zekademi.strongprettyhomes.domain.ImageDB;
import com.zekademi.strongprettyhomes.domain.Property;
import com.zekademi.strongprettyhomes.domain.PropertyDetail;
import com.zekademi.strongprettyhomes.domain.enumeration.PropertyCategory;
import com.zekademi.strongprettyhomes.domain.enumeration.PropertyStatus;
import com.zekademi.strongprettyhomes.domain.enumeration.PropertyType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    private Set<String> image;

    private Agent agent;

    private Set<Long> propertyDetails;



    public Set<String> getImageId(Set<ImageDB> images) {
        Set<String> img = new HashSet<>();
        ImageDB[] imageDBs = images.toArray(new ImageDB[images.size()]);

        for (int i = 0; i < images.size(); i++) {
            img.add(imageDBs[i].getId());
        }
        return img;
    }

    public PropertyDTO(Property property) {
        this.id = property.getId();
        this.title = property.getTitle();
        this.description = property.getDescription() ;
        this.category = property.getCategory();
        this.type = property.getType();
        this.bedrooms = property.getBedrooms();
        this.bathrooms = property.getBathrooms();
        this.garages = property.getGarages();
        this.area = property.getArea();
        this.price = property.getPrice();
        this.location = property.getLocation();
        this.address = property.getAddress();
        this.country = property.getCountry();
        this.city = property.getCity();
        this.district = property.getDistrict();
        this.createdDate = property.getCreateDate();
        this.likes = property.getLikes();
        this.visitCount = property.getVisitCount();
        this.status = property.getStatus();
        this.image = getImageId(property.getImage());
        this.agent = property.getAgent();
        this.propertyDetails = getDetailId(property.getPropertyDetails());
    }

    public Set<Long> getDetailId(Set<PropertyDetail> details) {
        Set<Long> det = new HashSet<>();
        PropertyDetail[] details1 = details.toArray(new PropertyDetail[details.size()]);

        for (int i = 0; i < details.size(); i++) {
            det.add(details1[i].getId());
        }
        return det;
    }
}
