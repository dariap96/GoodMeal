package com.goodmeal.adapters;

import java.util.function.Function;

public interface SiteToEntityAdapter<Site, Entity> {

    public Entity transform(Site siteEntity);

    public static <RepoKeyEntity, IdClass, SourceEntity> RepoKeyEntity findOrCreate(
            IdClass id,
            Function<IdClass, RepoKeyEntity> finder,
            Function<SourceEntity, RepoKeyEntity> creator,
            SourceEntity source
    ) {
        RepoKeyEntity repoKeyEntity = finder.apply(id);
        if (repoKeyEntity == null) {
            repoKeyEntity = creator.apply(source);
        }
        return repoKeyEntity;
    }
}
