package com.project.common;

public class ResourceCreationResponse {
    
    private String resourceId;

    public ResourceCreationResponse(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "ResourceCreationResponse{" +
                "resourceId='" + resourceId + '\'' +
                '}';
    }
}
