package com.srcsite.request.edamamrequest;

import com.srcsite.request.SiteRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.client.RestTemplate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class EdamamRequest implements SiteRequest {

    public static final String SEP = "&";

    protected String base;
    protected String appId;
    protected String appKey;

    protected abstract String getURI();

    @Override
    public <SiteBase> SiteBase sendRequest(Class<SiteBase> siteBaseClass) {
        return new RestTemplate().getForObject(getURI(), siteBaseClass);
    }
}