package com.example.traveling.controller.webcontroller;

import com.example.traveling.entity.Destination;
import com.example.traveling.service.DestinationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/destinations")
public class DestinationWebController {

    private final DestinationService destinationService;

    @GetMapping("/newdestination")
    public String newDestination(Model model) {
        log.info("create destination (Thymeleaf)");
        model.addAttribute("newdestination", new Destination());
        return "destination-create";
    }

    @PostMapping
    public String createDestination(@ModelAttribute("newdestination") Destination destination) {
        destinationService.save(destination);
        return "redirect:/destinations";
    }

    @PostMapping("/update/{id}")
    public String updateDestinationById(@PathVariable Long id, @ModelAttribute("newdestination") Destination destination) {
        log.info("update destination (Thymeleaf)");
        destinationService.updateById(id, destination);
        return "redirect:/destinations";
    }

    @GetMapping("/edit/{id}")
    public String editDestination(@PathVariable Long id, Model model) {
        log.info("edit destination (Thymeleaf)");
        Destination existingDestination = destinationService.getEntityById(id);
        model.addAttribute("newdestination", existingDestination);
        return "destination-create";
    }

    @GetMapping("/{id}")
    public String getDestinationById(@PathVariable Long id, Model model) {
        log.info("GET request Destination by Id (Thymeleaf)");
        model.addAttribute("destination", destinationService.getEntityById(id));
        return "destination-details";
    }

    @GetMapping
    public String findAllDestinations(Model model) {
        log.info("GET request of Destinations list (Thymeleaf)");
        List<Destination> destinationList = destinationService.findAllEntities();
        model.addAttribute("destinations", destinationList);
        return "destination-list";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable long id) {
        log.info("delete destination (Thymeleaf)");
        destinationService.deleteEntityById(id);
        return "redirect:/destinations";
    }
}