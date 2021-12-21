package com.srcsite.request;

public interface SiteRequest {
    <SiteBase> SiteBase sendRequest(Class<SiteBase> siteBaseClass);
}
