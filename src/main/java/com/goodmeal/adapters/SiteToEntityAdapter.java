package com.goodmeal.adapters;

import org.springframework.data.repository.CrudRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface SiteToEntityAdapter<Site, Entity> {
    public Entity transform(Site siteEntity);


    public static <RepoKeyEntity, RepoValueEntity, IdClass> RepoKeyEntity find(
            Class<RepoKeyEntity> entityClass,
            IdClass id,
            CrudRepository<RepoKeyEntity, RepoValueEntity> repository,
            Function<RepoKeyEntity, IdClass> idFunction
    ){
        List<RepoKeyEntity> entities = new LinkedList<>();
        repository.findAll().forEach(entities::add);

        entities = entities.stream()
                .filter(entity -> id.equals(idFunction.apply(entity)))
                .collect(Collectors.toList());

        if(entities.size() != 0) {
            return entities.get(0);
        }

        return null;
    }


    public static <RepoKeyEntity, RepoValueEntity, IdClass, SourceEntity> RepoKeyEntity findOrCreate(
            Class<RepoKeyEntity> entityClass,
            IdClass id,
            CrudRepository<RepoKeyEntity, RepoValueEntity> repository,
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
