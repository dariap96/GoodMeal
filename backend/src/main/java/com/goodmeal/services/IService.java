package com.goodmeal.services;

import java.util.Optional;

public interface IService <T> {

    Iterable<T> findAll();

    void create(T t);

    Optional<T> findById(Long id);

    boolean existsById(Long id);

}
