package com.zekademi.strongprettyhomes.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="propertys")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Size(max=30, message = "Size is exceeded")
    @NotNull(message = "Please enter the homes title")
    @Column(length = 30, nullable = false)
    private  String title;

    @NotNull(message = "Please enter the homes description")
    @Column(length = 30, nullable = false)
    private  String description;

    @NotNull(message = "Please enter the homes description")
    @Column(length = 30, nullable = false)
    private  String category;

    @Size(max=30, message = "Size is exceeded")
    @NotNull(message = "Please enter the homes type")
    @Column(length = 30, nullable = false)
    private  String type;

    @NotNull(message = "Please enter the homes bedrooms")
    @Column(nullable = false)
    private  String bedrooms;

    @NotNull(message = "Please enter the homes bathrooms")
    @Column(nullable = false)
    private  String bathrooms;

    @NotNull(message = "Please enter the homes garages")
    @Column(nullable = false)
    private  String garages;

    @NotNull(message = "Please enter the homes area")
    @Column(nullable = false)
    private  Integer area;

    @NotNull(message = "Please enter the  homes price")
    @Column(nullable = false)
    private  Double price ;


    @NotNull(message = "Please enter the homes location")
    @Column(nullable = false)
    private  String location;

    @NotNull(message = "Please enter the homes address")
    @Column(nullable = false)
    private  String address;

    @NotNull(message = "Please enter the homes country")
    @Column(nullable = false)
    private  String country;

    @NotNull(message = "Please enter the homes city")
    @Column(nullable = false)
    private  String city;

    @NotNull(message = "Please enter the homes district")
    @Column(nullable = false)
    private  String district;

    @NotNull(message = "Please enter the homes createDate")
    @Column(nullable = false)
    private  String createDate;

    @NotNull(message = "Please enter the homes likes")
    @Column( nullable = false)
    private  String likes;

    @NotNull(message = "Please enter the homes visitCount")
    @Column(nullable = false)
    private  Integer visitCount;




    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "homes_image",
            joinColumns = @JoinColumn(name="homes_id"),
    inverseJoinColumns = @JoinColumn(name = "file:id"))
    private Set<FileDB> image;



}
