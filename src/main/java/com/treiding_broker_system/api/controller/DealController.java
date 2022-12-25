package com.treiding_broker_system.api.controller;

import com.treiding_broker_system.service.deal.DealService;
import com.treiding_broker_system.service.mapper.deal.DealResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/deals")
@RequiredArgsConstructor
public class DealController {
    private final DealService dealService;
    private final DealResponseMapper dealResponseMapper;

    @GetMapping("/all")
    public ModelAndView allDeals() {
        var allDeals = dealService.getAll();
        var allDealsResponse = dealResponseMapper.map(allDeals);

        var mav = new ModelAndView("/deal/all-deals-view");
        mav.addObject("deals", allDealsResponse);
        return mav;
    }
}
