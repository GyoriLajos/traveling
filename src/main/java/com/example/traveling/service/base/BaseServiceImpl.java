package com.example.traveling.service.base;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public abstract class BaseServiceImpl<T,ID,R extends JpaRepository<T,ID>> implements BaseService<T,ID> {

    protected final R repository;

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public T updateById(ID id, T update) {
        Optional<T> existingEntity = repository.findById(id);
        if (existingEntity.isEmpty()) {
            String message = String.format("Entity with id %s not found",id);
            logAndThrowException(message,new NoSuchElementException(message));
        }
        T updatedEntity = existingEntity.get();
        updatemapper(updatedEntity,update);

        return repository.save(updatedEntity);
    }

    @Override
    public T getEntityById(ID id) {
        return repository.findById(id).orElseThrow(()-> {
            String message = String.format("Entity with id %s not found",id);
            return logAndReturnException(new NoSuchElementException(message),message);
        });
    }

    @Override
    public List<T> findAllEntities() {
        List<T> activities = repository.findAll();
        if (activities.isEmpty()) {
            String message = "Entity database is empty";
            logAndThrowException(message,new RuntimeException(message));
        }
        return activities;
    }

    @Override
    public void deleteEntityById(ID id) {
        if (!repository.existsById(id)) {
            String message = String.format("Activity with id %s not found",id);
            logAndThrowException(message,new NoSuchElementException(message));
        }
        repository.deleteById(id);
    }

    private void logAndThrowException(String message, RuntimeException exception) {
        log.info(message);
        throw exception;
    }

    private RuntimeException logAndReturnException(RuntimeException exception, String message) {
        log.info(message);
        return exception;
    }

    public abstract void updatemapper(T updatedEntity,T update);
}
