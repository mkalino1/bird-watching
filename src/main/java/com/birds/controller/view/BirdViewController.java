package com.birds.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.birds.service.BirdService;

@Controller
public class BirdViewController {

  @Autowired
  private BirdService birdService;

  @GetMapping("/bird/{id}")
  public String getBird(Model model, @PathVariable Long id) {
    model.addAttribute("bird", birdService.getBirdById(id).orElse(null));
    return "bird";
  }
}
