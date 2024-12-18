package com.pinde.sci.model.jsres;

/**
 * ~~~~~~~~~溺水的鱼~~~~~~~~
 *
 * @Author: 吴强
 * @Date: 2024/09/23/8:33
 * @Description: 排班导入到db的接受类
 */
public class PbImportDataItem implements java.io.Serializable {

    private String schDeptFlow;

    private String schDeptName;

    private String startDate;

    private String endDate;

    public String getSchDeptFlow() {
        return schDeptFlow;
    }

    public void setSchDeptFlow(String schDeptFlow) {
        this.schDeptFlow = schDeptFlow;
    }

    public String getSchDeptName() {
        return schDeptName;
    }

    public void setSchDeptName(String schDeptName) {
        this.schDeptName = schDeptName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
