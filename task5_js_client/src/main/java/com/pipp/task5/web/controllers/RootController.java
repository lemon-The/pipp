package com.pipp.task5.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RootController {

  @GetMapping
  public String rootGetMethod() {
    return "redirect:/battle_members";
  }

}
