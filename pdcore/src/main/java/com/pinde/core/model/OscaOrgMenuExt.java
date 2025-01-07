package com.pinde.core.model;

public class OscaOrgMenuExt extends SysOrg {
    //是否开通考核项目管理
    private String projectMenu;
    //是否开通评分表单配置
    private String scoreMenu;
    //是否开通考官信息管理
    private String examMenu;

    public String getProjectMenu() {
        return projectMenu;
    }

    public void setProjectMenu(String projectMenu) {
        this.projectMenu = projectMenu;
    }

    public String getScoreMenu() {
        return scoreMenu;
    }

    public void setScoreMenu(String scoreMenu) {
        this.scoreMenu = scoreMenu;
    }

    public String getExamMenu() {
        return examMenu;
    }

    public void setExamMenu(String examMenu) {
        this.examMenu = examMenu;
    }
}
