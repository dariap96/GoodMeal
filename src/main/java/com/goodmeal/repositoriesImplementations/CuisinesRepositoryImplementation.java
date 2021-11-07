package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Cuisine;
import com.goodmeal.repositories.IRepository;
import io.crnk.core.engine.registry.RegistryEntry;
import io.crnk.core.engine.transaction.TransactionRunner;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.resource.annotations.JsonApiResource;
import io.crnk.core.resource.list.ResourceList;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import io.crnk.core.engine.registry.*;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.parser.Entity;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

@Component

@JsonApiResource(type = "cuisine")

public class CuisinesRepositoryImplementation extends JpaEntityRepositoryBase<Cuisine, Long> implements IRepository<Cuisine,Long> {
  // RegistryEntry.addEntry(Cuisine, RegistryEntry registryEntry);
    public CuisinesRepositoryImplementation() {
        super(Cuisine.class);
    }
}
