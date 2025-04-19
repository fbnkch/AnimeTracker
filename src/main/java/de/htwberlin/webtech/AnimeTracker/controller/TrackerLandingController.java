package de.htwberlin.webtech.AnimeTracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TrackerLandingController {
    @GetMapping(path = "/")
    public ModelAndView showLandingPage(){
        return new ModelAndView("trackerlanding");
    }
}
