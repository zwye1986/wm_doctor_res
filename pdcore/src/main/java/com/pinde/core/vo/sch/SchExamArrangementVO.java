package com.pinde.core.vo.sch;

import com.pinde.core.model.SchExamArrangement;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SchExamArrangementVO extends SchExamArrangement{
    private String userName;

    private String trainingSpeName;

    private String userCode;

    private String userPhone;

    private String idNo;

    private String deptName;

    private String schStartDate;

    private String schEndDate;

    private String trainingSpeId;

    private String sessionNumber;

    /** 所有轮的标准科室，最多十二个 */
    private String schMonth01StdDept;
    private String schMonth02StdDept;
    private String schMonth03StdDept;
    private String schMonth04StdDept;
    private String schMonth05StdDept;
    private String schMonth06StdDept;
    private String schMonth07StdDept;
    private String schMonth08StdDept;
    private String schMonth09StdDept;
    private String schMonth10StdDept;
    private String schMonth11StdDept;
    private String schMonth12StdDept;
}
