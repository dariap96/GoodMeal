package com.goodmeal.services.impl;

import com.goodmeal.entities.APIFoodKey;
import com.goodmeal.repositoriesImplementations.APIFoodKeysRepositoryImplementation;
import com.goodmeal.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("jpaAPIFoodKeysService")
@Transactional
public class APIFoodKeysService implements IService<APIFoodKey> {

    private final APIFoodKeysRepositoryImplementation apiFoodKeysRepository;

    @Autowired
    public APIFoodKeysService(APIFoodKeysRepositoryImplementation apiFoodKeysRepository) {
        this.apiFoodKeysRepository = apiFoodKeysRepository;
    }

    @Override
    public Iterable<APIFoodKey> findAll() {
        return apiFoodKeysRepository.findAll();
    }

    @Override
    public void create(APIFoodKey apiFoodKey) {
        apiFoodKeysRepository.save(apiFoodKey);
    }

    @Override
    public Optional<APIFoodKey> findById(Long id) {
        return Optional.of(apiFoodKeysRepository.getAPIFoodKeyById(id));
    }

    @Override
    public boolean existsById(Long id) {
        return apiFoodKeysRepository.existsById(id);
    }
}
