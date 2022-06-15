package com.zekademi.strongprettyhomes.controller;

import com.zekademi.strongprettyhomes.service.TourRequestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/tour")
public class TourRequestController {

    public TourRequestService tourRequestService;


}
