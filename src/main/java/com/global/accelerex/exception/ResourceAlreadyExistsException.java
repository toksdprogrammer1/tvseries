package com.global.accelerex.exception;



import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by Jideobi.Onuora on 1/17/2018.
 */
public class ResourceAlreadyExistsException extends RuntimeException {

    private String resource;
    private String resourceId;

    public ResourceAlreadyExistsException(String resource, String resourceId) {
        super();
        this.resource = resource;
        this.resourceId = resourceId;
    }

    public ResourceAlreadyExistsException(String resource, List<String> params) {
        super();
        this.resource = resource;
        this.resourceId = StringUtils.join(params, ", ");
    }

    public String getResource() {
        return resource;
    }

    public String getResourceId() {
        return resourceId;
    }

    public String getMessageCode() {
        return "resource.already.exists";
    }

    public Object[] getMessageArguments() {
        return new Object[]{resource, resourceId};
    }

    @Override
    public String getMessage() {
        return String.format("%s already exists %s", resource, resourceId);
    }
}
