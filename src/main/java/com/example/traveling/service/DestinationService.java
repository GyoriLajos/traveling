package com.example.traveling.service;

import com.example.traveling.entity.Destination;
import com.example.traveling.repository.DestinationRepository;
import com.example.traveling.service.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j

public class DestinationService extends BaseServiceImpl<Destination, Long, DestinationRepository> {

    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository repository) {
        super(repository);
        this.destinationRepository = repository;
    }

    @Override
    public void updatemapper(Destination updatedEntity, Destination update) {
        updatedEntity.setCityName(update.getCityName());
        updatedEntity.setRegion(update.getRegion());
        updatedEntity.setDescription(update.getDescription());
        updatedEntity.setPopularity(update.getPopularity());
    }
}
