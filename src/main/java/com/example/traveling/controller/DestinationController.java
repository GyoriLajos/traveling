package com.example.traveling.controller;

import com.example.traveling.entity.Destination;
import com.example.traveling.service.DestinationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/destinations")
@Slf4j

public class DestinationController {

    private final DestinationService destinationService;

    @PostMapping
    public ResponseEntity<Destination> createDestination(@RequestBody Destination destination) {
        log.info("Request new Destination create");
        return ResponseEntity.status(201).body(destinationService.save(destination));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Destination> updateDestinationById(@PathVariable Long id, @RequestBody Destination updatedDestination) {
        log.info("Request exist Destination update");
        return ResponseEntity.ok().body(destinationService.updateById(id, updatedDestination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destination> getDestinationById(@PathVariable Long id) {
        log.info("GET request Destination by Id");
        return ResponseEntity.ok(destinationService.getEntityById(id));
    }

    @GetMapping
    public ResponseEntity<List<Destination>> findAllDestinations() {
        log.info("GET request of Destinations list");
        return ResponseEntity.ok(destinationService.findAllEntities());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("Delete request of Destination by Id");
        destinationService.deleteEntityById(id);
        return ResponseEntity.status(204).build();
    }
}
