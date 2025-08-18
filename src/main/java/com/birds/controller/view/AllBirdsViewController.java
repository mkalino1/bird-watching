package com.birds.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.birds.service.BirdService;

@Controller
public class AllBirdsViewController {

  @Autowired
  private BirdService birdService;

  @GetMapping("/")
  public String allBirds(Model model) {
    model.addAttribute("birds", birdService.getAllBirds());
    return "all-birds";
  }
}
