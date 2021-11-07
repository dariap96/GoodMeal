package com.goodmeal.services;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IService <T> {
    public Iterable<T> findAll();
}
