package com.goodmeal.services.impl;

import com.goodmeal.entities.APIRecipeKey;
import com.goodmeal.repositoriesImplementations.APIRecipeKeysRepositoryImplementation;
import com.goodmeal.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("jpaAPIRecipeKeysService")
@Transactional
public class APIRecipeKeysService implements IService<APIRecipeKey> {

    private final APIRecipeKeysRepositoryImplementation apiRecipeKeysRepository;

    @Autowired
    public APIRecipeKeysService(APIRecipeKeysRepositoryImplementation apiRecipeKeysRepository) {
        this.apiRecipeKeysRepository = apiRecipeKeysRepository;
    }

    @Override
    public Iterable<APIRecipeKey> findAll() {
        return apiRecipeKeysRepository.findAll();
    }

    @Override
    public void create(APIRecipeKey apiRecipeKey) {
        apiRecipeKeysRepository.save(apiRecipeKey);
    }

    @Override
    public Optional<APIRecipeKey> findById(Long id) {
        return Optional.of(apiRecipeKeysRepository.getAPIRecipeKeyById(id));
    }

    @Override
    public boolean existsById(Long id) {
        return apiRecipeKeysRepository.existsById(id);
    }
}
