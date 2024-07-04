package com.pinde.sci.model.mo;

import com.pinde.core.model.MybatisObject;

public class ExamBookComposInfo extends MybatisObject {
    private String composFlow;

    private String bookFlow;

    private String composUserFlow;

    private String composUserName;

    private String composTime;

    private String signUserFlow;

    private String signUserName;

    private String problem;

    private String solution;

    private String recordStatus;

    private String createTime;

    private String createUserFlow;

    private String modifyTime;

    private String modifyUserFlow;

    public String getComposFlow() {
        return composFlow;
    }

    public void setComposFlow(String composFlow) {
        this.composFlow = composFlow;
    }

    public String getBookFlow() {
        return bookFlow;
    }

    public void setBookFlow(String bookFlow) {
        this.bookFlow = bookFlow;
    }

    public String getComposUserFlow() {
        return composUserFlow;
    }

    public void setComposUserFlow(String composUserFlow) {
        this.composUserFlow = composUserFlow;
    }

    public String getComposUserName() {
        return composUserName;
    }

    public void setComposUserName(String composUserName) {
        this.composUserName = composUserName;
    }

    public String getComposTime() {
        return composTime;
    }

    public void setComposTime(String composTime) {
        this.composTime = composTime;
    }

    public String getSignUserFlow() {
        return signUserFlow;
    }

    public void setSignUserFlow(String signUserFlow) {
        this.signUserFlow = signUserFlow;
    }

    public String getSignUserName() {
        return signUserName;
    }

    public void setSignUserName(String signUserName) {
        this.signUserName = signUserName;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserFlow() {
        return createUserFlow;
    }

    public void setCreateUserFlow(String createUserFlow) {
        this.createUserFlow = createUserFlow;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyUserFlow() {
        return modifyUserFlow;
    }

    public void setModifyUserFlow(String modifyUserFlow) {
        this.modifyUserFlow = modifyUserFlow;
    }
}