package com.example.traveling.controller.webcontroller;

import com.example.traveling.entity.Traveler;
import com.example.traveling.service.TravelerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/travelers")
public class TravelerWebController {

    private final TravelerService travelerService;

    @GetMapping("/newtraveler")
    public String newTraveler(Model model) {
        log.info("create traveler (Thymeleaf)");
        model.addAttribute("newtraveler", new Traveler());
        return "traveler-create";
    }

    @PostMapping
    public String createTraveler(@ModelAttribute("newtraveler") Traveler traveler) {
        travelerService.save(traveler);
        return "redirect:/travelers";
    }

    @PostMapping("/update/{id}")
    public String updateTravelerById(@PathVariable Long id, @ModelAttribute("newtraveler") Traveler traveler) {
        log.info("update traveler (Thymeleaf)");
        travelerService.updateById(id, traveler);
        return "redirect:/travelers";
    }

    @GetMapping("/edit/{id}")
    public String editTraveler(@PathVariable Long id, Model model) {
        log.info("edit traveler (Thymeleaf)");
        Traveler existingTraveler = travelerService.getEntityById(id);
        model.addAttribute("newtraveler", existingTraveler);
        return "traveler-create";
    }

    @GetMapping("/{id}")
    public String getTravelerById(@PathVariable Long id, Model model) {
        log.info("GET request Traveler by Id (Thymeleaf)");
        model.addAttribute("traveler", travelerService.getEntityById(id));
        return "traveler-details";
    }

    @GetMapping
    public String findAllTravelers(Model model) {
        log.info("GET request of Travelers list (Thymeleaf)");
        List<Traveler> travelerList = travelerService.findAllEntities();
        model.addAttribute("travelers", travelerList);
        return "traveler-list";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable long id) {
        log.info("delete traveler (Thymeleaf)");
        travelerService.deleteEntityById(id);
        return "redirect:/travelers";
    }
}