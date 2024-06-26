package com.pinde.sci.form.jsres.BaseSpeDept;

import java.io.Serializable;

public class TrainingActivityForm implements Serializable {
    /**
     * 培训活动记录名称
     */
    private static final long serialVersionUID = -3524388431912961659L;

    private String id;

    private String activityName;

    private String appendix;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppendix() {
        return appendix;
    }

    public void setAppendix(String appendix) {
        this.appendix = appendix;
    }
}
