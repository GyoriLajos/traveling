package com.example.traveling.controller.webcontroller;

import com.example.traveling.entity.Activity;
import com.example.traveling.model.ActivityCreateModel;
import com.example.traveling.model.ActivityModel;
import com.example.traveling.model.ActivityModelWithTraveler;
import com.example.traveling.service.ActivityService;
import com.example.traveling.util.ActivityMapper;
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
@RequestMapping("/activities")
public class ActivityWebController {

    private final ActivityService activityService;

    @GetMapping("/newactivity")
    public String newActivity(Model model) {
        log.info("new activity (Thymeleaf)");
        model.addAttribute("newactivity", new ActivityCreateModel());
        return "activity-create";
    }

    @PostMapping
    public String createActivity(@Valid @ModelAttribute("newactivity") ActivityCreateModel activityCreateModel,
                                 BindingResult bindingResult) {
        log.info("create activity (Thymeleaf)");
        if (bindingResult.hasErrors()) {
            return "activity-create";
        }
        Activity activity = ActivityMapper.mapCreateActivityModelToActivityEntity(activityCreateModel);
        activityService.saveWithDestination(activity, activityCreateModel.getDestinationId());
        return "redirect:/activities";
    }

    @PostMapping("/update/{id}")
    public String updateActivityById(@PathVariable Long id,
                                     @Valid @ModelAttribute("newactivity") ActivityCreateModel activityCreateModel,
                                     BindingResult bindingResult) {
        log.info("update activity (Thymeleaf)");
        if (bindingResult.hasErrors()) {
            return "activity-create";
        }
        Activity activity = ActivityMapper.mapCreateActivityModelToActivityEntity(activityCreateModel);
        activityService.updateById(id, activity);
        return "redirect:/activities";
    }

    @GetMapping("/edit/{id}")
    public String editActivity(@PathVariable Long id, Model model) {
        log.info("edit activity (Thymeleaf)");
        Activity existingActivity = activityService.getEntityById(id);
        ActivityCreateModel activityCreateModel = ActivityMapper.mapActivityEntityToActivityCreateModel(existingActivity);
        model.addAttribute("newactivity", activityCreateModel);
        return "activity-create";
    }

    @GetMapping("/{id}")
    public String getActivityById(@PathVariable Long id, Model model) {
        log.info("GET request Activity by Id (Thymeleaf)");
        Activity activity = activityService.getEntityById(id);
        ActivityModel activityModel = ActivityMapper.mapActivityEntityToActivityModel(activity);
        model.addAttribute("activity", activityModel);
        return "activity-details";
    }

    @GetMapping("/{id}/travelers")
    public String getActivityByIdWithTraveler(@PathVariable Long id, Model model) {
        log.info("GET request Activity by Id (Thymeleaf)");
        Activity activity = activityService.getEntityById(id);
        ActivityModelWithTraveler activityModelWithTraveler = ActivityMapper.mapActivityEntityToActivityModelWithTraveler(activity);
        model.addAttribute("activity", activityModelWithTraveler);
        return "activity-details";
    }

    @GetMapping
    public String findAllActivities(Model model) {
        log.info("GET request of Activities list (Thymeleaf)");
        List<Activity> activityList = activityService.findAllEntities();
        List<ActivityModel> activityModelList = Mapper
                .map(activityList, ActivityMapper::mapActivityEntityToActivityModel);
        model.addAttribute("activities", activityModelList);
        return "activity-list";
    }

    @PostMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        log.info("delete activity (Thymeleaf)");
        activityService.deleteEntityById(id);
        return "redirect:/activities";
    }
}