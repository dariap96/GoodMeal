package com.srcsite.edamrequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.client.RestTemplate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class EdamRequest {

    public static final String SEP = "&";

    protected String base;
    protected String appId;
    protected String appKey;

    protected abstract String getURI();


    protected <SiteBase> SiteBase sendRequest(Class<SiteBase> siteBaseClass) {
        return new RestTemplate().getForObject(getURI(), siteBaseClass);
    }
}