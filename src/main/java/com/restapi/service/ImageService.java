package com.restapi.service;

import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.CarDetail;
import com.restapi.repository.CarDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import javax.xml.stream.Location;
import java.io.File;
import java.io.IOException;

@Service
public class ImageService {
    @Autowired
    StorageService storageService;
    @Autowired
    CarDetailRepository carDetailRepository;
    public File getFile(Integer id) throws IOException {
        CarDetail carDetail = carDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id", "id", id));

        Resource resource = storageService.loadFileAsResource(carDetail.getPhoto());

        return resource.getFile();
    }
}
