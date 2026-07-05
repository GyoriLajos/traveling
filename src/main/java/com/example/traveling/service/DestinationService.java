package com.example.traveling.service;

import com.example.traveling.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j

public class DestinationService {

    private final DestinationRepository destinationRepository;
}
