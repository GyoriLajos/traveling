package com.example.traveling.controller.webcontroller;

import com.example.traveling.entity.Destination;
import com.example.traveling.model.DestinationCreateModel;
import com.example.traveling.service.DestinationService;
import com.example.traveling.util.DestinationMapper;
import com.example.traveling.util.Mapper;
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
@RequestMapping("/destinations")
public class DestinationWebController {

    private final DestinationService destinationService;

    @GetMapping("/newdestination")
    public String newDestination(Model model) {
        log.info("create destination (Thymeleaf)");
        model.addAttribute("newdestination", new DestinationCreateModel());
        return "destination-create";
    }

    @PostMapping
    public String createDestination(@Valid @ModelAttribute("newdestination") DestinationCreateModel destinationCreateModel,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "destination-create";
        }
        Destination destination = DestinationMapper.mapCreateDestinationModelToDestinationEntity(destinationCreateModel);
        destinationService.save(destination);
        return "redirect:/destinations";
    }

    @PostMapping("/update/{id}")
    public String updateDestinationById(@PathVariable Long id,
                                        @Valid @ModelAttribute("newdestination") DestinationCreateModel destinationCreateModel,
                                        BindingResult bindingResult) {
        log.info("update destination (Thymeleaf)");
        if (bindingResult.hasErrors()) {
            return "destination-create";
        }
        Destination destination = DestinationMapper.mapCreateDestinationModelToDestinationEntity(destinationCreateModel);
        destinationService.updateById(id, destination);
        return "redirect:/destinations";
    }

    @GetMapping("/edit/{id}")
    public String editDestination(@PathVariable Long id, Model model) {
        log.info("edit destination (Thymeleaf)");
        Destination existingDestination = destinationService.getEntityById(id);
        DestinationCreateModel createModel = DestinationCreateModel.builder()
                .cityName(existingDestination.getCityName())
                .region(existingDestination.getRegion())
                .description(existingDestination.getDescription())
                .popularity(existingDestination.getPopularity())
                .build();
        model.addAttribute("newdestination", createModel);
        return "destination-create";
    }

    @GetMapping("/{id}")
    public String getDestinationById(@PathVariable Long id, Model model) {
        log.info("GET request Destination by Id (Thymeleaf)");
        Destination destination = destinationService.getEntityById(id);
        model.addAttribute("destination", DestinationMapper.mapDestinationEntityToDestinationModel(destination));
        return "destination-details";
    }

    @GetMapping
    public String findAllDestinations(Model model) {
        log.info("GET request of Destinations list (Thymeleaf)");
        List<Destination> destinationList = destinationService.findAllEntities();
        model.addAttribute("destinations", Mapper.map(destinationList, DestinationMapper::mapDestinationEntityToDestinationModel));
        return "destination-list";
    }

    @PostMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        log.info("delete destination (Thymeleaf)");
        destinationService.deleteEntityById(id);
        return "redirect:/destinations";
    }
}