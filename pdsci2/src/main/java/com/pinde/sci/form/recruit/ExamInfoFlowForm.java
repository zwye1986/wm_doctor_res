package com.pinde.sci.form.recruit;

import com.pinde.sci.model.mo.RecruitExamInfo;
import com.pinde.sci.model.mo.RecruitExamRoomInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @BelongsProject: 18svn
 * @BelongsPackage: com.pinde.sci.form.recruit
 * @Author: yex
 * @CreateTime: 2019-05-07 14:28
 * @Description: ${Description}
 */
public class ExamInfoFlowForm  implements Serializable {

    private static final long serialVersionUID = -7930841955002365083L;

    private String examFlow;

    private String mainFlow;

    private String recordFlow;

    private String examNum;

    public String getExamFlow() {
        return examFlow;
    }

    public void setExamFlow(String examFlow) {
        this.examFlow = examFlow;
    }

    public String getMainFlow() {
        return mainFlow;
    }

    public void setMainFlow(String mainFlow) {
        this.mainFlow = mainFlow;
    }

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public String getExamNum() {
        return examNum;
    }

    public void setExamNum(String examNum) {
        this.examNum = examNum;
    }
}
