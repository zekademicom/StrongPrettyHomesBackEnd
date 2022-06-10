package com.zekademi.strongprettyhomes.domain;


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
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 30, message = "Size is exceeded")
    @NotNull(message = "Please enter the homes title")
    @Column(length = 30, nullable = false)
    private String title;

    @NotNull(message = "Please enter the homes description")
    @Column(length = 30, nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Please enter the homes description")
    @Column(length = 30, nullable = false)
    private PropertyCategory category;

    @Enumerated(EnumType.STRING)
    @Size(max = 30, message = "Size is exceeded")
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
    private Double price;

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

    @NotNull(message = "Please enter the homes likes")
    @Column(nullable = false)
    private Integer likes;

    @NotNull(message = "Please enter the homes visitCount")
    @Column(nullable = false)
    private Long visitCount;

    @Enumerated(EnumType.STRING)
    @Size(max = 30, message = "Size is exceeded")
    @NotNull(message = "Please enter the homes status")
    @Column(length = 30, nullable = false)
    private PropertyStatus status;

    @OneToMany(mappedBy = "property")
    private Set<ImageDB> image;




}
