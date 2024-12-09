package com.pinde.core.model;

public class PubUserGenericContentKey implements java.io.Serializable {
    private String userFlow;

    private String contentType;

    public String getUserFlow() {
        return userFlow;
    }

    public void setUserFlow(String userFlow) {
        this.userFlow = userFlow;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}