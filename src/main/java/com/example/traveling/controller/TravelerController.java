package com.example.traveling.controller;

import com.example.traveling.entity.Traveler;
import com.example.traveling.service.TravelerService;
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
    public ResponseEntity<Traveler> createTraveler(@RequestBody Traveler traveler) {
        log.info("Request new Traveler create");
        return ResponseEntity.status(201).body(travelerService.save(traveler));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Traveler> updateTravelerById(@PathVariable Long id, @RequestBody Traveler updatedTraveler) {
        log.info("Request exist Traveler update");
        return ResponseEntity.ok().body(travelerService.updateById(id, updatedTraveler));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Traveler> getTravelerById(@PathVariable Long id) {
        log.info("GET request Traveler by Id");
        return ResponseEntity.ok(travelerService.getEntityById(id));
    }

    @GetMapping
    public ResponseEntity<List<Traveler>> findAllTravelers() {
        log.info("GET request of Travelers list");
        return ResponseEntity.ok(travelerService.findAllEntities());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("Delete request of Traveler by Id");
        travelerService.deleteEntityById(id);
        return ResponseEntity.status(204).build();
    }

}
