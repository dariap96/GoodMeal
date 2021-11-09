package com.goodmeal.adapters;

import org.springframework.data.repository.CrudRepository;

import java.util.function.Function;

public interface SiteToEntityAdapter<Site, Entity> {
    public Entity transform(Site siteEntity);


    public static <RepoKeyEntity, RepoValueEntity, IdClass> RepoKeyEntity find(
            Class<RepoKeyEntity> entityClass,
            IdClass id,
            CrudRepository<RepoKeyEntity, RepoValueEntity> repository,
            Function<RepoKeyEntity, IdClass> function
    ){
        Iterable<RepoKeyEntity> entities = repository
                .findAll();
        entities.forEach(entity -> id.equals(function.apply(entity)));

        System.out.println("++++++++++++++++++++++++++++++++++++++++");
        for(RepoKeyEntity entity : repository.findAll()) {
            System.out.println(id.toString());
            System.out.println(function.apply(entity));
            System.out.println(id.equals(function.apply(entity)));
            System.out.println("=====================================");
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++\n\n");

        if(entities.iterator().hasNext()) {
            return entities.iterator().next();
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
