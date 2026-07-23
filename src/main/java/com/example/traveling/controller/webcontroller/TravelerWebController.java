package com.example.traveling.controller.webcontroller;

import com.example.traveling.entity.Traveler;
import com.example.traveling.model.TravelerCreateModel;
import com.example.traveling.service.TravelerService;
import com.example.traveling.util.Mapper;
import com.example.traveling.util.TravelerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("newtraveler", new TravelerCreateModel());
        return "traveler-create";
    }

    @PostMapping
    public String createTraveler(@ModelAttribute("newtraveler")@Valid TravelerCreateModel travelerCreateModel,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "traveler-create";
        }
        Traveler traveler = TravelerMapper.mapCreateTravelerModelToTravelerEntity(travelerCreateModel);
        travelerService.save(traveler);
        return "redirect:/travelers";
    }

    @PostMapping("/update/{id}")
    public String updateTravelerById(@PathVariable Long id,
                                     @ModelAttribute("newtraveler")@Valid TravelerCreateModel travelerCreateModel,
                                     BindingResult bindingResult) {
        log.info("update traveler (Thymeleaf)");
        if (bindingResult.hasErrors()) {
            return "traveler-create";
        }
        Traveler traveler = TravelerMapper.mapCreateTravelerModelToTravelerEntity(travelerCreateModel);
        travelerService.updateById(id, traveler);
        return "redirect:/travelers";
    }

    @GetMapping("/edit/{id}")
    public String editTraveler(@PathVariable Long id, Model model) {
        log.info("edit traveler (Thymeleaf)");
        Traveler existingTraveler = travelerService.getEntityById(id);
        TravelerCreateModel createModel = TravelerCreateModel.builder()
                .firstName(existingTraveler.getFirstName())
                .lastName(existingTraveler.getLastName())
                .email(existingTraveler.getEmail())
                .phoneNumber(existingTraveler.getPhoneNumber())
                .categoryPreference(existingTraveler.getCategoryPreference())
                .budget(existingTraveler.getBudget())
                .build();
        model.addAttribute("newtraveler", createModel);
        return "traveler-create";
    }

    @GetMapping("/{id}")
    public String getTravelerById(@PathVariable Long id, Model model) {
        log.info("GET request Traveler by Id (Thymeleaf)");
        Traveler traveler = travelerService.getEntityById(id);
        model.addAttribute("traveler", TravelerMapper.mapTravelerEntityToTravelerModel(traveler));
        return "traveler-details";
    }

    @GetMapping
    public String findAllTravelers(Model model) {
        log.info("GET request of Travelers list (Thymeleaf)");
        List<Traveler> travelerList = travelerService.findAllEntities();
        model.addAttribute("travelers", Mapper.map(travelerList, TravelerMapper::mapTravelerEntityToTravelerModel));
        return "traveler-list";
    }

    @PostMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        log.info("delete traveler (Thymeleaf)");
        travelerService.deleteEntityById(id);
        return "redirect:/travelers";
    }
}