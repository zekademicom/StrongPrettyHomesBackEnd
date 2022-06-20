package com.zekademi.strongprettyhomes.service;

import com.zekademi.strongprettyhomes.helper.ExcelHelper;
import com.zekademi.strongprettyhomes.domain.*;
import com.zekademi.strongprettyhomes.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@AllArgsConstructor
@Service
public class ExcelService {

    UserRepository userRepository;
    PropertyRepository propertyRepository;
    AgentRepository agentRepository;
    ReviewRepository reviewRepository;
    TourRequestRepository tourRequestRepository;


    public ByteArrayInputStream loadUser() {
        List<User> users = userRepository.findAll();

        return ExcelHelper.usersExcel(users);
    }

    public ByteArrayInputStream loadProperty() {
        List<Property> properties = propertyRepository.findAll();

        return ExcelHelper.propertiesExcel(properties);
    }

    public ByteArrayInputStream loadAgent() {
        List<Agent> agents = agentRepository.findAll();

        return ExcelHelper.agentsExcel(agents);
    }
    public ByteArrayInputStream loadReview() {
        List<Review> reviews = reviewRepository.findAll();

        return ExcelHelper.reviewsExcel(reviews);
    }
    public ByteArrayInputStream loadTourRequest() {
        List<TourRequest> tourRequests = tourRequestRepository.findAll();

        return ExcelHelper.tourRequestsExcel(tourRequests);
    }

}
