package com.birds.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.birds.service.ObservationService;

@Controller 
public class ObservationsMap {

  @Autowired
  ObservationService observationsService;
  
  @GetMapping ("/map")
  public String getRecentObservations(Model model) {
    model.addAttribute("observations", observationsService.getAllObservationsOrderedByDate());
    return "observations-map";
  }

}
