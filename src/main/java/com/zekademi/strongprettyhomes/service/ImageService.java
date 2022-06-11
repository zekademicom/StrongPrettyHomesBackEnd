package com.zekademi.strongprettyhomes.service;

import com.zekademi.strongprettyhomes.domain.ImageDB;
import com.zekademi.strongprettyhomes.domain.Property;
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

    public ImageDB store(MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        ImageDB imageDB = new ImageDB(fileName, file.getContentType(), file.getBytes());

        imageRepository.save(imageDB);

        return imageDB;
    }

    public ImageDB getFile(String id) {
        return imageRepository.findById(id).get();
    }

    public Stream<ImageDB> getAllFiles() {
        return imageRepository.findAll().stream();
    }
}
