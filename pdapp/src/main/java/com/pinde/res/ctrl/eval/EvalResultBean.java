package com.pinde.res.ctrl.eval;

import java.util.List;

/**
 * Created by Administrator on 2017/8/8.
 */

public class EvalResultBean {

    /**
     * baseScore : 100
     * cfgFlow : cecdcc0515e1433dacc65532bc2aebb8
     * evalYear : 2016
     * orgFlow : 02ba52a1ce1f47febf0ca4630a21908e
     * scores : [{"baseScore":"3","ordinal":1,"reason":"","score":"","title":"1.1.1总床位数"},{"baseScore":"1","ordinal":2,"reason":"","score":"","title":"1.1.2年收治病人数"},{"baseScore":"1","ordinal":3,"reason":"","score":"","title":"1.1.3年门诊量"},{"baseScore":"1","ordinal":4,"reason":"","score":"","title":"1.1.4年急诊量"},{"baseScore":"1","ordinal":5,"reason":"","score":"","title":"1.1.5科室和实验室"},{"baseScore":"2","ordinal":6,"reason":"","score":"","title":"1.1.6轮转科室"},{"baseScore":"3","ordinal":7,"reason":"","score":"","title":"1.1.7疾病种类及数量★"},{"baseScore":"3","ordinal":8,"reason":"","score":"","title":"1.1.8临床技能操作种类及数量★"},{"baseScore":"1","ordinal":9,"reason":"","score":"","title":"1.1.9医院设备"},{"baseScore":"1","ordinal":10,"reason":"","score":"","title":"1.1.10专业基地设备"},{"baseScore":"1","ordinal":11,"reason":"","score":"","title":"1.2.1协同数"},{"baseScore":"1","ordinal":12,"reason":"","score":"","title":"1.2.2协同床位数"},{"baseScore":"1","ordinal":13,"reason":"","score":"","title":"1.2.3轮转时间"},{"baseScore":"3","ordinal":14,"reason":"","score":"","title":"2.1.1带教医师与培训对象比例★"},{"baseScore":"1","ordinal":15,"reason":"","score":"","title":"2.1.2带教医师条件"},{"baseScore":"1","ordinal":16,"reason":"","score":"","title":"2.1.3带教医师组成"},{"baseScore":"1","ordinal":17,"reason":"","score":"","title":"2.1.4专业基地负责人条件"},{"baseScore":"3","ordinal":18,"reason":"","score":"","title":"2.2.1师资培训★"},{"baseScore":"3","ordinal":19,"reason":"","score":"","title":"2.2.2师资评价★"},{"baseScore":"3","ordinal":20,"reason":"","score":"","title":"2.2.3激励制度★"},{"baseScore":"1","ordinal":21,"reason":"","score":"","title":"3.1.1主任职责"},{"baseScore":"1","ordinal":22,"reason":"","score":"","title":"3.1.2教学主任"},{"baseScore":"1","ordinal":23,"reason":"","score":"","title":"3.1.3教学秘书"},{"baseScore":"1","ordinal":24,"reason":"","score":"","title":"3.1.4教学小组"},{"baseScore":"3","ordinal":25,"reason":"","score":"","title":"3.1.5轮转计划★"},{"baseScore":"2","ordinal":26,"reason":"","score":"","title":"3.1.6考勤制度"},{"baseScore":"1","ordinal":27,"reason":"","score":"","title":"3.2.1入科教育"},{"baseScore":"2","ordinal":28,"reason":"","score":"","title":"3.2.2教学查房"},{"baseScore":"2","ordinal":29,"reason":"","score":"","title":"3.2.3小讲课"},{"baseScore":"2","ordinal":30,"reason":"","score":"","title":"3.2.4疑难病例讨论"},{"baseScore":"7","ordinal":31,"reason":"","score":"","title":"3.3.1出科考核★"},{"baseScore":"4","ordinal":32,"reason":"","score":"","title":"3.4.1管理病床数★"},{"baseScore":"3","ordinal":33,"reason":"","score":"","title":"3.4.2门急诊工作量"},{"baseScore":"6","ordinal":34,"reason":"","score":"","title":"4.1.1查房质量★"},{"baseScore":"4","ordinal":35,"reason":"","score":"","title":"4.1.2技能操作安排情况★"},{"baseScore":"5","ordinal":36,"reason":"","score":"","title":"4.1.3技能操作带教情况★"},{"baseScore":"5","ordinal":37,"reason":"","score":"","title":"4.2.1病历书写★"},{"baseScore":"7","ordinal":38,"reason":"","score":"","title":"4.2.2技能操作★"},{"baseScore":"8","ordinal":39,"reason":"","score":"","title":"4.2.3完成培训内容与要求★"}]
     * siginDate : 2017-08-08
     * siginName : 专家001
     * userFlow : a8b9bdca522b4de1b1d18c4f9d98947e
     */

    private String baseScore;
    private String type;
    private String evalScore;
    private String evalScore1;
    private String evalScore2;
    private String cfgFlow;
    private String problems;
    private String evalYear;
    private String orgFlow;
    private String siginDate;
    private String siginName;
    private String userFlow;
    private List<ScoresBean> scores;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEvalScore1() {
        return evalScore1;
    }

    public void setEvalScore1(String evalScore1) {
        this.evalScore1 = evalScore1;
    }

    public String getEvalScore2() {
        return evalScore2;
    }

    public void setEvalScore2(String evalScore2) {
        this.evalScore2 = evalScore2;
    }

    public String getProblems() {
        return problems;
    }

    public void setProblems(String problems) {
        this.problems = problems;
    }

    public String getEvalScore() {
        return evalScore;
    }

    public void setEvalScore(String evalScore) {
        this.evalScore = evalScore;
    }

    public String getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(String baseScore) {
        this.baseScore = baseScore;
    }

    public String getCfgFlow() {
        return cfgFlow;
    }

    public void setCfgFlow(String cfgFlow) {
        this.cfgFlow = cfgFlow;
    }

    public String getEvalYear() {
        return evalYear;
    }

    public void setEvalYear(String evalYear) {
        this.evalYear = evalYear;
    }

    public String getOrgFlow() {
        return orgFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
    }

    public String getSiginDate() {
        return siginDate;
    }

    public void setSiginDate(String siginDate) {
        this.siginDate = siginDate;
    }

    public String getSiginName() {
        return siginName;
    }

    public void setSiginName(String siginName) {
        this.siginName = siginName;
    }

    public String getUserFlow() {
        return userFlow;
    }

    public void setUserFlow(String userFlow) {
        this.userFlow = userFlow;
    }

    public List<ScoresBean> getScores() {
        return scores;
    }

    public void setScores(List<ScoresBean> scores) {
        this.scores = scores;
    }

    public static class ScoresBean {
        /**
         * baseScore : 3
         * ordinal : 1
         * reason :
         * score :
         * title : 1.1.1总床位数
         */

        private String baseScore;
        private String ordinal;
        private String reason;
        private String score;
        private String title;

        public String getBaseScore() {
            return baseScore;
        }

        public void setBaseScore(String baseScore) {
            this.baseScore = baseScore;
        }

        public String getOrdinal() {
            return ordinal;
        }

        public void setOrdinal(String ordinal) {
            this.ordinal = ordinal;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
