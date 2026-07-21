package com.example.traveling.service.base;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Slf4j
public abstract class BaseServiceImpl<T, ID, R extends JpaRepository<T, ID>> implements BaseService<T, ID> {

    protected final R repository;

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public T updateById(ID id, T update) {
        T existingEntity = repository.findById(id).orElseThrow(() -> {
            String message = String.format("Entity with id %s not found", id);
            log.info(message);
            return new NoSuchElementException(message);
        });

        updatemapper(existingEntity, update);
        return repository.save(existingEntity);
    }

    @Override
    public T getEntityById(ID id) {
        return repository.findById(id).orElseThrow(() -> {
            String message = String.format("Entity with id %s not found", id);
            log.info(message);
            return new NoSuchElementException(message);
        });
    }

    @Override
    public List<T> findAllEntities() {
        return repository.findAll();
    }

    @Override
    public void deleteEntityById(ID id) {
        if (!repository.existsById(id)) {
            String message = String.format("Entity with id %s not found", id);
            log.info(message);
            throw new NoSuchElementException(message);
        }
        repository.deleteById(id);
    }

    public abstract void updatemapper(T updatedEntity, T update);
}