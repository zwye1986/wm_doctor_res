package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class PubUserGenericContentKey extends MybatisObject {
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