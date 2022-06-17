package com.zekademi.strongprettyhomes.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zekademi.strongprettyhomes.domain.enumeration.PropertyCategory;
import com.zekademi.strongprettyhomes.domain.enumeration.PropertyStatus;
import com.zekademi.strongprettyhomes.domain.enumeration.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100, message = "Size is exceeded")
    @NotNull(message = "Please enter the homes title")
    @Column(length = 100, nullable = false)
    private String title;

    @NotNull(message = "Please enter the homes description")
    @Column(length = 200, nullable = true)
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Please enter the homes description")
    @Column(length = 30, nullable = false)
    private PropertyCategory category;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Please enter the homes type")
    @Column(length = 30, nullable = false)
    private PropertyType type;

    @NotNull(message = "Please enter the homes bedrooms")
    @Column(nullable = false)
    private String bedrooms;

    @NotNull(message = "Please enter the homes bathrooms")
    @Column(nullable = false)
    private String bathrooms;

    @NotNull(message = "Please enter the homes garages")
    @Column(nullable = false)
    private String garages;

    @NotNull(message = "Please enter the homes area")
    @Column(nullable = false)
    private Double area;

    @NotNull(message = "Please enter the  homes price")
    @Column(nullable = false)
    private Integer price;

    @NotNull(message = "Please enter the homes location")
    @Column(nullable = false)
    private String location;

    @NotNull(message = "Please enter the homes address")
    @Column(nullable = false)
    private String address;

    @NotNull(message = "Please enter the homes country")
    @Column(nullable = false)
    private String country;

    @NotNull(message = "Please enter the homes city")
    @Column(nullable = false)
    private String city;

    @NotNull(message = "Please enter the homes district")
    @Column(nullable = false)
    private String district;

    @NotNull(message = "Please enter the homes createDate")
    @Column(nullable = false)
    private Date createDate;

    @NotNull(message = "Please enter the homes visitCount")
    @Column(nullable = false)
    private Long visitCount;

//    @NotNull(message = "Please enter the homes likes")
//    @Column(nullable = false)
//    private Long countLike;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Please enter the homes status")
    @Column(length = 30, nullable = false)
    private PropertyStatus status;


    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<ImageDB> image;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private Agent agent;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name = "property_id"),
            inverseJoinColumns = @JoinColumn(name = "detail_id"))
    private Set<PropertyDetail> propertyDetails;

    @JsonIgnore
    @OneToMany(mappedBy = "property")
    private Set<TourRequest> tourRequests;

    @JsonIgnore
    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Review> reviews= new HashSet<>();

    public Property(String title, String description, PropertyCategory category, PropertyType type,
                    String bedrooms, String bathrooms, String garages, Double area, Integer price,
                    String location, String address, String country, String city, String district,
                    Date createDate, PropertyStatus status) {
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
        this.createDate = createDate;
        this.status = status;
    }

}
