package com.example.traveling.controller;

import com.example.traveling.entity.Traveler;
import com.example.traveling.model.TravelerModel;
import com.example.traveling.service.TravelerService;
import com.example.traveling.util.Mapper;
import com.example.traveling.util.TravelerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/travelers")
@Slf4j

public class TravelerController {

    private final TravelerService travelerService;

    @PostMapping
    public ResponseEntity<TravelerModel> createTraveler(@RequestBody Traveler traveler) {
        log.info("Request new Traveler create");
        Traveler savedTraveler = travelerService.save(traveler);
        return ResponseEntity.status(201).body(TravelerMapper.mapTravelerEntityToTravelerModel(savedTraveler));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TravelerModel> updateTravelerById(@PathVariable Long id, @RequestBody Traveler updatedTraveler) {
        log.info("Request exist Traveler update");
        Traveler savedTraveler = travelerService.updateById(id, updatedTraveler);
        return ResponseEntity.ok(TravelerMapper.mapTravelerEntityToTravelerModel(savedTraveler));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelerModel> getTravelerById(@PathVariable Long id) {
        log.info("GET request Traveler by Id");
        Traveler traveler = travelerService.getEntityById(id);
        return ResponseEntity.ok(TravelerMapper.mapTravelerEntityToTravelerModel(traveler));
    }

    @GetMapping
    public ResponseEntity<List<TravelerModel>> findAllTravelers() {
        log.info("GET request of Travelers list");
        List<Traveler> travelers = travelerService.findAllEntities();
        return ResponseEntity.ok(Mapper.map(travelers, TravelerMapper::mapTravelerEntityToTravelerModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("Delete request of Traveler by Id");
        travelerService.deleteEntityById(id);
        return ResponseEntity.status(204).build();
    }

}