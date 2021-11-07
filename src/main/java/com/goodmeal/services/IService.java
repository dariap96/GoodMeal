package com.goodmeal.services;

import java.util.List;

public interface IService <T>{
    public List<T> findAll();
}
