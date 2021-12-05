package com.goodmeal.services;

import java.util.Optional;

public interface IService <T> {

    public abstract Iterable<T> findAll();

    public abstract void create(T t);

    public Optional<T> findById(Long id);

    public boolean existsById(Long id);

}
