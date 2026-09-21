package com.birds.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.birds.service.ObservationService;

@Controller 
public class RecentObservations {

  @Autowired
  ObservationService observationsService;
  
  @GetMapping ("/recent")
  public String getRecentObservations(Model model) {
    model.addAttribute("observations", observationsService.getAllObservationsOrderedByDate());
    return "recent-observations";
  }

}
