package com.example.traveling;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // <-- Ezt adja hozzá!
class TravelingApplicationTests {

    @Test
    void contextLoads() {
    }
}