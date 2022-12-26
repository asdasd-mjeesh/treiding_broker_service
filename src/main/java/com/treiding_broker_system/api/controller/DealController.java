package com.treiding_broker_system.api.controller;

import com.treiding_broker_system.service.deal.DealService;
import com.treiding_broker_system.service.mapper.deal.DealResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     *  It's possible do through method above, but it's just quick demo, so I didn't complicate solution
     * */
    @GetMapping("/userDeals/{userId}")
    public ModelAndView userDeals(@PathVariable(name = "userId") Long userId) {
        var allDeals = dealService.getAllUserDeals(userId);
        var allDealsResponse = dealResponseMapper.map(allDeals);

        var mav = new ModelAndView("/deal/all-deals-view");
        mav.addObject("deals", allDealsResponse);
        return mav;
    }
}
