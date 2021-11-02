package com.goodmeal.adapters;

import com.goodmeal.repositories.IRepository;
import io.crnk.core.queryspec.QuerySpec;

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
