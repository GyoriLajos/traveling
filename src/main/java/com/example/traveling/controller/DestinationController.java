package com.example.traveling.controller;

import com.example.traveling.entity.Destination;
import com.example.traveling.model.DestinationModel;
import com.example.traveling.service.DestinationService;
import com.example.traveling.util.DestinationMapper;
import com.example.traveling.util.Mapper;
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
    public ResponseEntity<DestinationModel> createDestination(@RequestBody Destination destination) {
        log.info("Request new Destination create");
        Destination savedDestination = destinationService.save(destination);
        return ResponseEntity.status(201).body(DestinationMapper.mapDestinationEntityToDestinationModel(savedDestination));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DestinationModel> updateDestinationById(@PathVariable Long id, @RequestBody Destination updatedDestination) {
        log.info("Request exist Destination update");
        Destination savedDestination = destinationService.updateById(id, updatedDestination);
        return ResponseEntity.ok(DestinationMapper.mapDestinationEntityToDestinationModel(savedDestination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationModel> getDestinationById(@PathVariable Long id) {
        log.info("GET request Destination by Id");
        Destination destination = destinationService.getEntityById(id);
        return ResponseEntity.ok(DestinationMapper.mapDestinationEntityToDestinationModel(destination));
    }

    @GetMapping
    public ResponseEntity<List<DestinationModel>> findAllDestinations() {
        log.info("GET request of Destinations list");
        List<Destination> destinations = destinationService.findAllEntities();
        return ResponseEntity.ok(Mapper.map(destinations, DestinationMapper::mapDestinationEntityToDestinationModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("Delete request of Destination by Id");
        destinationService.deleteEntityById(id);
        return ResponseEntity.status(204).build();
    }
}