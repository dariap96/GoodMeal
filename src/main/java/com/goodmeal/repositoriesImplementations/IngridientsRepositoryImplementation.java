package com.goodmeal.repositoriesImplementations;

import com.goodmeal.entities.Ingridient;
import com.goodmeal.repositories.IngridientsRepository;
import io.crnk.data.jpa.JpaEntityRepositoryBase;
import org.springframework.stereotype.Component;

@Component
public class IngridientsRepositoryImplementation extends JpaEntityRepositoryBase<Ingridient, Long> implements IngridientsRepository {

    public IngridientsRepositoryImplementation() { super(Ingridient.class); }

}
