package com.goodmeal.adapters;

import com.goodmeal.repositories.IRepository;
import io.crnk.core.queryspec.QuerySpec;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface SiteToEntityAdapter<Site, Entity> {
    public Entity transform(Site siteEntity);


    public static <RepoKeyEntity, RepoValueEntity, IdClass> RepoKeyEntity find(
            Class<RepoKeyEntity> entityClass,
            IdClass id,
            IRepository<RepoKeyEntity, RepoValueEntity> repository,
            Function<RepoKeyEntity, IdClass> function
    ){
        List<RepoKeyEntity> entities = repository
                .findAll(new QuerySpec(entityClass))
                .stream()
                .filter(entity -> id.equals(function.apply(entity)))
                .collect(Collectors.toList());
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
        for(RepoKeyEntity entity : repository.findAll(new QuerySpec(entityClass))) {
            System.out.println(id.toString());
            System.out.println(function.apply(entity));
            System.out.println(id.equals(function.apply(entity)));
            System.out.println("=====================================");
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++\n\n");

        if(entities.size() != 0) {
            return entities.get(0);
        }

        return null;
    }


    public static <RepoKeyEntity, RepoValueEntity, IdClass, SourceEntity> RepoKeyEntity findOrCreate(
            Class<RepoKeyEntity> entityClass,
            IdClass id,
            IRepository<RepoKeyEntity, RepoValueEntity> repository,
            Function<RepoKeyEntity, IdClass> function,
            Function<SourceEntity, RepoKeyEntity> creator,
            SourceEntity source
    ) {
        RepoKeyEntity repoKeyEntity = find(entityClass, id, repository, function);
        if (repoKeyEntity == null) {
            repoKeyEntity = creator.apply(source);
        }
        return repoKeyEntity;
    }
}
