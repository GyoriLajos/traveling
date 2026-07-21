package com.example.traveling.controller.webcontroller;

import com.example.traveling.entity.Activity;
import com.example.traveling.model.ActivityCreateModel;
import com.example.traveling.service.ActivityService;
import com.example.traveling.util.ActivityMapper;
import com.example.traveling.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

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
        model.addAttribute("newactivity",new ActivityCreateModel());
        return "activity-create";
    }

    @PostMapping
    public String createActivity(@ModelAttribute("newactivity") ActivityCreateModel activityCreateModel) {
        log.info("create activity (Thymeleaf)");
        Activity activity = ActivityMapper.mapCreateActivityModelToActivityEntity(activityCreateModel);
        activityService.save(activity);
        return "redirect:/activities";
    }

    @PostMapping("/update/{id}")
    public String updateActivityById(@PathVariable Long id,@ModelAttribute("newactivity") ActivityCreateModel activityCreateModel) {
        log.info("update activity (Thymeleaf)");
        Activity activity = ActivityMapper.mapCreateActivityModelToActivityEntity(activityCreateModel);
        activityService.updateById(id,activity);
        return "redirect:/activities";
    }

    @GetMapping("/edit/{id}")
    public String editActivity(@PathVariable Long id,Model model) {
        log.info("edit activity (Thymeleaf)");
        Activity existingActivity = activityService.getEntityById(id);
        ActivityCreateModel activityCreateModel = ActivityMapper.mapActivityEntityToActivityCreateModel(existingActivity);
        model.addAttribute("newactivity",existingActivity);
        return "activity-create";
    }

    @GetMapping("/{id}")
    public String getActivityById(@PathVariable Long id, Model model) {
        log.info("GET request Activity by Id (Thymeleaf)");
        Activity activity = activityService.getEntityById(id);
        ActivityCreateModel activityCreateModel = ActivityMapper.mapActivityEntityToActivityCreateModel(activity);
        model.addAttribute("activity",activityCreateModel);
        return "activity-details";
    }

    @GetMapping
    public String findAllActivities(Model model) {
        log.info("GET request of Activities list (Thymeleaf)");
        List<Activity> activityList = activityService.findAllEntities();
        List<ActivityCreateModel> activityCreateModelList = Mapper
                .map(activityList,ActivityMapper::mapActivityEntityToActivityCreateModel);
        model.addAttribute("activities",activityCreateModelList);
        return "activity-list";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable long id) {
        log.info("delete activity (Thymeleaf)");
        activityService.deleteEntityById(id);
        return "redirect:/activities";
    }
}
