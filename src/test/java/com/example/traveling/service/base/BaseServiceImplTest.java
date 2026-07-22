package com.example.traveling.service.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BaseServiceImplTest {

    static class TestEntity {
        private Long id;
        private String name;

        public TestEntity(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() { return id;}
        public String getName() { return name; }
        public void setId(Long id) { this.id = id; }
        public void setName(String name) { this.name = name; }
    }

    interface TestRepository extends JpaRepository<TestEntity, Long> {

    }

    static class TestServiceImpl extends BaseServiceImpl<TestEntity, Long, TestRepository> {

        public TestServiceImpl(TestRepository repository) {
            super(repository);
        }

        @Override
        public void updatemapper(TestEntity existingEntity, TestEntity update) {
            existingEntity.setName(update.getName());
        }
    }

    @Mock
    private TestRepository repository;

    private TestServiceImpl testService;

    @BeforeEach
    void setUp() {
        testService = new TestServiceImpl(repository);
    }

    @Test
    @DisplayName("save - The save is successed and it is returned Entity")
    void save_ShouldSaveAndReturnEntity() {

        TestEntity entityToSave = new TestEntity(null, "Új Laci");
        TestEntity savedEntity = new TestEntity(1L, "Új Laci");

        when(repository.save(entityToSave)).thenReturn(savedEntity);

        // ACT
        TestEntity result = testService.save(entityToSave);

        // ASSERT

        assertNotNull(result, "The Entity is not null");
        assertEquals(1L, result.getId(), "The Id = 1L");
        assertEquals("Új Laci", result.getName(), "The mame is correct");


        verify(repository, times(1)).save(entityToSave);
    }
}
