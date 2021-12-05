package com.srcsite.edamrequest;

import lombok.Getter;
import org.springframework.web.client.RestTemplate;

@Getter
public abstract class EdamRequest {
    public static final String SEP = "&";

    protected final String base;
    private final String appId;
    private final String appKey;

    protected EdamRequest(String appId, String appKey, String base) {
        this.appId = appId;
        this.appKey = appKey;
        this.base = base;
    }

    protected abstract String getURI();

    protected <SiteBase> SiteBase sendRequest(Class<SiteBase> siteBaseClass) {
        try
        {
            Thread.sleep(5);
        }
        catch(InterruptedException ex)
        {
            System.out.println("sendingRequest interruption");
            Thread.currentThread().interrupt();
        }
        return new RestTemplate().getForObject(getURI(), siteBaseClass);
    }
}