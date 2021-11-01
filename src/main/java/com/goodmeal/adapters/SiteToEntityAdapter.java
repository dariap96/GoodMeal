package com.goodmeal.adapters;

public interface SiteToEntityAdapter<Site, Entity> {
    public Entity transform(Site siteEntity);
}
