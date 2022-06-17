package com.zekademi.strongprettyhomes.service;

import com.zekademi.strongprettyhomes.domain.ImageDB;
import com.zekademi.strongprettyhomes.domain.Property;
import com.zekademi.strongprettyhomes.exception.ResourceNotFoundException;
import com.zekademi.strongprettyhomes.repository.ImageRepository;
import com.zekademi.strongprettyhomes.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class ImageService {

    private ImageRepository imageRepository;
    private PropertyRepository propertyRepository;

    private final static String PROPERTY_NOT_FOUND_MSG = "property with id %d not found";
    private final static String IMAGE_NOT_FOUND_MSG = "image with id %d not found";

    public ImageDB store(MultipartFile file, Long id) throws IOException {

        Property property = propertyRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(PROPERTY_NOT_FOUND_MSG, id)));

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        ImageDB imageDB = new ImageDB(fileName, file.getContentType(), file.getBytes(), property);

        imageRepository.save(imageDB);

        return imageDB;
    }

    public ImageDB getFile(String id) {
        return imageRepository.findById(id).get();
    }

    public Stream<ImageDB> getAllFiles() {
        return imageRepository.findAll().stream();
    }

    public void setFeatured(String id){
        ImageDB imageDB = imageRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(IMAGE_NOT_FOUND_MSG, id)));

        if (!imageDB.getFeatured()) imageDB.setFeatured(false);
        else imageDB.setFeatured(true);

        imageRepository.save(imageDB);
    }
}