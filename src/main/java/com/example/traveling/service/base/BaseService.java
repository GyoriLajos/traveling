package com.example.traveling.service.base;

import java.util.List;

public interface BaseService<T,ID> {

    T save(T entity);
    T updateById(ID id,T entity);
    T getEntityById(ID id);
    List<T> findAllEntities();
    void deleteEntityById(ID id);
}
