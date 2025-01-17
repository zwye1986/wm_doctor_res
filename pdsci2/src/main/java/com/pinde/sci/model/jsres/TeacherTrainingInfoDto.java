package com.pinde.sci.model.jsres;

import com.pinde.core.model.*;
import lombok.Data;

@Data
public class TeacherTrainingInfoDto extends SysUser {
    private String professionalFlow;

    private String professionalTitleId;

    private String professionalTitleName;

    private String technicalPositionId;

    private String technicalPositionName;

    private String technicalPositionTime;

    private String clinicalTeachingTime;

    private String educationData;

    private String trainingData;

    private String letterData;
}
