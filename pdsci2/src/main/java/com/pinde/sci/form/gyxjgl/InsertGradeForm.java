package com.pinde.sci.form.gyxjgl;

import com.pinde.sci.model.mo.GydxjInsertDetail;
import com.pinde.sci.model.mo.GydxjInsertGrade;
import java.util.List;

public class InsertGradeForm extends GydxjInsertGrade {

    private String delFlow;
    private List<GydxjInsertDetail> gradeList;

    public String getDelFlow() {
        return delFlow;
    }

    public void setDelFlow(String delFlow) {
        this.delFlow = delFlow;
    }

    public List<GydxjInsertDetail> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<GydxjInsertDetail> gradeList) {
        this.gradeList = gradeList;
    }
}
