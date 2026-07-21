package com.example.traveling.service;

import com.example.traveling.entity.Traveler;
import com.example.traveling.repository.TravelerRepository;
import com.example.traveling.service.base.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j

public class TravelerService extends BaseServiceImpl<Traveler, Long, TravelerRepository> {

    private final TravelerRepository travelerRepository;

    public TravelerService(TravelerRepository repository) {
        super(repository);
        this.travelerRepository = repository;
    }

    @Override
    public void updatemapper(Traveler updatedEntity, Traveler update) {
        updatedEntity.setFirstName(update.getFirstName());
        updatedEntity.setLastName(update.getLastName());
        updatedEntity.setEmail(update.getEmail());
        updatedEntity.setPhoneNumber(update.getPhoneNumber());
        updatedEntity.setCategoryPreference(update.getCategoryPreference());
        updatedEntity.setBudget(update.getBudget());
    }
}
