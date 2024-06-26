package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class JsresDeptConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JsresDeptConfigExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andCfgFlowIsNull() {
            addCriterion("CFG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andCfgFlowIsNotNull() {
            addCriterion("CFG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andCfgFlowEqualTo(String value) {
            addCriterion("CFG_FLOW =", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowNotEqualTo(String value) {
            addCriterion("CFG_FLOW <>", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowGreaterThan(String value) {
            addCriterion("CFG_FLOW >", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CFG_FLOW >=", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowLessThan(String value) {
            addCriterion("CFG_FLOW <", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowLessThanOrEqualTo(String value) {
            addCriterion("CFG_FLOW <=", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowLike(String value) {
            addCriterion("CFG_FLOW like", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowNotLike(String value) {
            addCriterion("CFG_FLOW not like", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowIn(List<String> values) {
            addCriterion("CFG_FLOW in", values, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowNotIn(List<String> values) {
            addCriterion("CFG_FLOW not in", values, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowBetween(String value1, String value2) {
            addCriterion("CFG_FLOW between", value1, value2, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowNotBetween(String value1, String value2) {
            addCriterion("CFG_FLOW not between", value1, value2, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowIsNull() {
            addCriterion("ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andOrgFlowIsNotNull() {
            addCriterion("ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andOrgFlowEqualTo(String value) {
            addCriterion("ORG_FLOW =", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotEqualTo(String value) {
            addCriterion("ORG_FLOW <>", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGreaterThan(String value) {
            addCriterion("ORG_FLOW >", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_FLOW >=", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowLessThan(String value) {
            addCriterion("ORG_FLOW <", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("ORG_FLOW <=", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowLike(String value) {
            addCriterion("ORG_FLOW like", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotLike(String value) {
            addCriterion("ORG_FLOW not like", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowIn(List<String> values) {
            addCriterion("ORG_FLOW in", values, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotIn(List<String> values) {
            addCriterion("ORG_FLOW not in", values, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowBetween(String value1, String value2) {
            addCriterion("ORG_FLOW between", value1, value2, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotBetween(String value1, String value2) {
            addCriterion("ORG_FLOW not between", value1, value2, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIsNull() {
            addCriterion("DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIsNotNull() {
            addCriterion("DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDeptFlowEqualTo(String value) {
            addCriterion("DEPT_FLOW =", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotEqualTo(String value) {
            addCriterion("DEPT_FLOW <>", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowGreaterThan(String value) {
            addCriterion("DEPT_FLOW >", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_FLOW >=", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLessThan(String value) {
            addCriterion("DEPT_FLOW <", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("DEPT_FLOW <=", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLike(String value) {
            addCriterion("DEPT_FLOW like", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotLike(String value) {
            addCriterion("DEPT_FLOW not like", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIn(List<String> values) {
            addCriterion("DEPT_FLOW in", values, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotIn(List<String> values) {
            addCriterion("DEPT_FLOW not in", values, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowBetween(String value1, String value2) {
            addCriterion("DEPT_FLOW between", value1, value2, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotBetween(String value1, String value2) {
            addCriterion("DEPT_FLOW not between", value1, value2, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNull() {
            addCriterion("DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNotNull() {
            addCriterion("DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDeptNameEqualTo(String value) {
            addCriterion("DEPT_NAME =", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotEqualTo(String value) {
            addCriterion("DEPT_NAME <>", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThan(String value) {
            addCriterion("DEPT_NAME >", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_NAME >=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThan(String value) {
            addCriterion("DEPT_NAME <", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThanOrEqualTo(String value) {
            addCriterion("DEPT_NAME <=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLike(String value) {
            addCriterion("DEPT_NAME like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotLike(String value) {
            addCriterion("DEPT_NAME not like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameIn(List<String> values) {
            addCriterion("DEPT_NAME in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotIn(List<String> values) {
            addCriterion("DEPT_NAME not in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameBetween(String value1, String value2) {
            addCriterion("DEPT_NAME between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotBetween(String value1, String value2) {
            addCriterion("DEPT_NAME not between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andTestNumIsNull() {
            addCriterion("TEST_NUM is null");
            return (Criteria) this;
        }

        public Criteria andTestNumIsNotNull() {
            addCriterion("TEST_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andTestNumEqualTo(String value) {
            addCriterion("TEST_NUM =", value, "testNum");
            return (Criteria) this;
        }

        public Criteria andTestNumNotEqualTo(String value) {
            addCriterion("TEST_NUM <>", value, "testNum");
            return (Criteria) this;
        }

        public Criteria andTestNumGreaterThan(String value) {
            addCriterion("TEST_NUM >", value, "testNum");
            return (Criteria) this;
        }

        public Criteria andTestNumGreaterThanOrEqualTo(String value) {
            addCriterion("TEST_NUM >=", value, "testNum");
            return (Criteria) this;
        }

        public Criteria andTestNumLessThan(String value) {
            addCriterion("TEST_NUM <", value, "testNum");
            return (Criteria) this;
        }

        public Criteria andTestNumLessThanOrEqualTo(String value) {
            addCriterion("TEST_NUM <=", value, "testNum");
            return (Criteria) this;
        }

        public Criteria andTestNumLike(String value) {
            addCriterion("TEST_NUM like", value, "testNum");
            return (Criteria) this;
        }

        public Criteria andTestNumNotLike(String value) {
            addCriterion("TEST_NUM not like", value, "testNum");
            return (Criteria) this;
        }

        public Criteria andTestNumIn(List<String> values) {
            addCriterion("TEST_NUM in", values, "testNum");
            return (Criteria) this;
        }

        public Criteria andTestNumNotIn(List<String> values) {
            addCriterion("TEST_NUM not in", values, "testNum");
            return (Criteria) this;
        }

        public Criteria andTestNumBetween(String value1, String value2) {
            addCriterion("TEST_NUM between", value1, value2, "testNum");
            return (Criteria) this;
        }

        public Criteria andTestNumNotBetween(String value1, String value2) {
            addCriterion("TEST_NUM not between", value1, value2, "testNum");
            return (Criteria) this;
        }

        public Criteria andScorePassIsNull() {
            addCriterion("SCORE_PASS is null");
            return (Criteria) this;
        }

        public Criteria andScorePassIsNotNull() {
            addCriterion("SCORE_PASS is not null");
            return (Criteria) this;
        }

        public Criteria andScorePassEqualTo(String value) {
            addCriterion("SCORE_PASS =", value, "scorePass");
            return (Criteria) this;
        }

        public Criteria andScorePassNotEqualTo(String value) {
            addCriterion("SCORE_PASS <>", value, "scorePass");
            return (Criteria) this;
        }

        public Criteria andScorePassGreaterThan(String value) {
            addCriterion("SCORE_PASS >", value, "scorePass");
            return (Criteria) this;
        }

        public Criteria andScorePassGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_PASS >=", value, "scorePass");
            return (Criteria) this;
        }

        public Criteria andScorePassLessThan(String value) {
            addCriterion("SCORE_PASS <", value, "scorePass");
            return (Criteria) this;
        }

        public Criteria andScorePassLessThanOrEqualTo(String value) {
            addCriterion("SCORE_PASS <=", value, "scorePass");
            return (Criteria) this;
        }

        public Criteria andScorePassLike(String value) {
            addCriterion("SCORE_PASS like", value, "scorePass");
            return (Criteria) this;
        }

        public Criteria andScorePassNotLike(String value) {
            addCriterion("SCORE_PASS not like", value, "scorePass");
            return (Criteria) this;
        }

        public Criteria andScorePassIn(List<String> values) {
            addCriterion("SCORE_PASS in", values, "scorePass");
            return (Criteria) this;
        }

        public Criteria andScorePassNotIn(List<String> values) {
            addCriterion("SCORE_PASS not in", values, "scorePass");
            return (Criteria) this;
        }

        public Criteria andScorePassBetween(String value1, String value2) {
            addCriterion("SCORE_PASS between", value1, value2, "scorePass");
            return (Criteria) this;
        }

        public Criteria andScorePassNotBetween(String value1, String value2) {
            addCriterion("SCORE_PASS not between", value1, value2, "scorePass");
            return (Criteria) this;
        }

        public Criteria andIsTestOutIsNull() {
            addCriterion("IS_TEST_OUT is null");
            return (Criteria) this;
        }

        public Criteria andIsTestOutIsNotNull() {
            addCriterion("IS_TEST_OUT is not null");
            return (Criteria) this;
        }

        public Criteria andIsTestOutEqualTo(String value) {
            addCriterion("IS_TEST_OUT =", value, "isTestOut");
            return (Criteria) this;
        }

        public Criteria andIsTestOutNotEqualTo(String value) {
            addCriterion("IS_TEST_OUT <>", value, "isTestOut");
            return (Criteria) this;
        }

        public Criteria andIsTestOutGreaterThan(String value) {
            addCriterion("IS_TEST_OUT >", value, "isTestOut");
            return (Criteria) this;
        }

        public Criteria andIsTestOutGreaterThanOrEqualTo(String value) {
            addCriterion("IS_TEST_OUT >=", value, "isTestOut");
            return (Criteria) this;
        }

        public Criteria andIsTestOutLessThan(String value) {
            addCriterion("IS_TEST_OUT <", value, "isTestOut");
            return (Criteria) this;
        }

        public Criteria andIsTestOutLessThanOrEqualTo(String value) {
            addCriterion("IS_TEST_OUT <=", value, "isTestOut");
            return (Criteria) this;
        }

        public Criteria andIsTestOutLike(String value) {
            addCriterion("IS_TEST_OUT like", value, "isTestOut");
            return (Criteria) this;
        }

        public Criteria andIsTestOutNotLike(String value) {
            addCriterion("IS_TEST_OUT not like", value, "isTestOut");
            return (Criteria) this;
        }

        public Criteria andIsTestOutIn(List<String> values) {
            addCriterion("IS_TEST_OUT in", values, "isTestOut");
            return (Criteria) this;
        }

        public Criteria andIsTestOutNotIn(List<String> values) {
            addCriterion("IS_TEST_OUT not in", values, "isTestOut");
            return (Criteria) this;
        }

        public Criteria andIsTestOutBetween(String value1, String value2) {
            addCriterion("IS_TEST_OUT between", value1, value2, "isTestOut");
            return (Criteria) this;
        }

        public Criteria andIsTestOutNotBetween(String value1, String value2) {
            addCriterion("IS_TEST_OUT not between", value1, value2, "isTestOut");
            return (Criteria) this;
        }

        public Criteria andTestOutTimeIsNull() {
            addCriterion("TEST_OUT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTestOutTimeIsNotNull() {
            addCriterion("TEST_OUT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTestOutTimeEqualTo(String value) {
            addCriterion("TEST_OUT_TIME =", value, "testOutTime");
            return (Criteria) this;
        }

        public Criteria andTestOutTimeNotEqualTo(String value) {
            addCriterion("TEST_OUT_TIME <>", value, "testOutTime");
            return (Criteria) this;
        }

        public Criteria andTestOutTimeGreaterThan(String value) {
            addCriterion("TEST_OUT_TIME >", value, "testOutTime");
            return (Criteria) this;
        }

        public Criteria andTestOutTimeGreaterThanOrEqualTo(String value) {
            addCriterion("TEST_OUT_TIME >=", value, "testOutTime");
            return (Criteria) this;
        }

        public Criteria andTestOutTimeLessThan(String value) {
            addCriterion("TEST_OUT_TIME <", value, "testOutTime");
            return (Criteria) this;
        }

        public Criteria andTestOutTimeLessThanOrEqualTo(String value) {
            addCriterion("TEST_OUT_TIME <=", value, "testOutTime");
            return (Criteria) this;
        }

        public Criteria andTestOutTimeLike(String value) {
            addCriterion("TEST_OUT_TIME like", value, "testOutTime");
            return (Criteria) this;
        }

        public Criteria andTestOutTimeNotLike(String value) {
            addCriterion("TEST_OUT_TIME not like", value, "testOutTime");
            return (Criteria) this;
        }

        public Criteria andTestOutTimeIn(List<String> values) {
            addCriterion("TEST_OUT_TIME in", values, "testOutTime");
            return (Criteria) this;
        }

        public Criteria andTestOutTimeNotIn(List<String> values) {
            addCriterion("TEST_OUT_TIME not in", values, "testOutTime");
            return (Criteria) this;
        }

        public Criteria andTestOutTimeBetween(String value1, String value2) {
            addCriterion("TEST_OUT_TIME between", value1, value2, "testOutTime");
            return (Criteria) this;
        }

        public Criteria andTestOutTimeNotBetween(String value1, String value2) {
            addCriterion("TEST_OUT_TIME not between", value1, value2, "testOutTime");
            return (Criteria) this;
        }

        public Criteria andTeacherWriteIsNull() {
            addCriterion("TEACHER_WRITE is null");
            return (Criteria) this;
        }

        public Criteria andTeacherWriteIsNotNull() {
            addCriterion("TEACHER_WRITE is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherWriteEqualTo(String value) {
            addCriterion("TEACHER_WRITE =", value, "teacherWrite");
            return (Criteria) this;
        }

        public Criteria andTeacherWriteNotEqualTo(String value) {
            addCriterion("TEACHER_WRITE <>", value, "teacherWrite");
            return (Criteria) this;
        }

        public Criteria andTeacherWriteGreaterThan(String value) {
            addCriterion("TEACHER_WRITE >", value, "teacherWrite");
            return (Criteria) this;
        }

        public Criteria andTeacherWriteGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_WRITE >=", value, "teacherWrite");
            return (Criteria) this;
        }

        public Criteria andTeacherWriteLessThan(String value) {
            addCriterion("TEACHER_WRITE <", value, "teacherWrite");
            return (Criteria) this;
        }

        public Criteria andTeacherWriteLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_WRITE <=", value, "teacherWrite");
            return (Criteria) this;
        }

        public Criteria andTeacherWriteLike(String value) {
            addCriterion("TEACHER_WRITE like", value, "teacherWrite");
            return (Criteria) this;
        }

        public Criteria andTeacherWriteNotLike(String value) {
            addCriterion("TEACHER_WRITE not like", value, "teacherWrite");
            return (Criteria) this;
        }

        public Criteria andTeacherWriteIn(List<String> values) {
            addCriterion("TEACHER_WRITE in", values, "teacherWrite");
            return (Criteria) this;
        }

        public Criteria andTeacherWriteNotIn(List<String> values) {
            addCriterion("TEACHER_WRITE not in", values, "teacherWrite");
            return (Criteria) this;
        }

        public Criteria andTeacherWriteBetween(String value1, String value2) {
            addCriterion("TEACHER_WRITE between", value1, value2, "teacherWrite");
            return (Criteria) this;
        }

        public Criteria andTeacherWriteNotBetween(String value1, String value2) {
            addCriterion("TEACHER_WRITE not between", value1, value2, "teacherWrite");
            return (Criteria) this;
        }

        public Criteria andRecordStatusIsNull() {
            addCriterion("RECORD_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andRecordStatusIsNotNull() {
            addCriterion("RECORD_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andRecordStatusEqualTo(String value) {
            addCriterion("RECORD_STATUS =", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusNotEqualTo(String value) {
            addCriterion("RECORD_STATUS <>", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusGreaterThan(String value) {
            addCriterion("RECORD_STATUS >", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusGreaterThanOrEqualTo(String value) {
            addCriterion("RECORD_STATUS >=", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusLessThan(String value) {
            addCriterion("RECORD_STATUS <", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusLessThanOrEqualTo(String value) {
            addCriterion("RECORD_STATUS <=", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusLike(String value) {
            addCriterion("RECORD_STATUS like", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusNotLike(String value) {
            addCriterion("RECORD_STATUS not like", value, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusIn(List<String> values) {
            addCriterion("RECORD_STATUS in", values, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusNotIn(List<String> values) {
            addCriterion("RECORD_STATUS not in", values, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusBetween(String value1, String value2) {
            addCriterion("RECORD_STATUS between", value1, value2, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andRecordStatusNotBetween(String value1, String value2) {
            addCriterion("RECORD_STATUS not between", value1, value2, "recordStatus");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(String value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(String value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(String value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(String value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLike(String value) {
            addCriterion("CREATE_TIME like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotLike(String value) {
            addCriterion("CREATE_TIME not like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<String> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<String> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(String value1, String value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(String value1, String value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowIsNull() {
            addCriterion("CREATE_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowIsNotNull() {
            addCriterion("CREATE_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowEqualTo(String value) {
            addCriterion("CREATE_USER_FLOW =", value, "createUserFlow");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowNotEqualTo(String value) {
            addCriterion("CREATE_USER_FLOW <>", value, "createUserFlow");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowGreaterThan(String value) {
            addCriterion("CREATE_USER_FLOW >", value, "createUserFlow");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_USER_FLOW >=", value, "createUserFlow");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowLessThan(String value) {
            addCriterion("CREATE_USER_FLOW <", value, "createUserFlow");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowLessThanOrEqualTo(String value) {
            addCriterion("CREATE_USER_FLOW <=", value, "createUserFlow");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowLike(String value) {
            addCriterion("CREATE_USER_FLOW like", value, "createUserFlow");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowNotLike(String value) {
            addCriterion("CREATE_USER_FLOW not like", value, "createUserFlow");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowIn(List<String> values) {
            addCriterion("CREATE_USER_FLOW in", values, "createUserFlow");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowNotIn(List<String> values) {
            addCriterion("CREATE_USER_FLOW not in", values, "createUserFlow");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowBetween(String value1, String value2) {
            addCriterion("CREATE_USER_FLOW between", value1, value2, "createUserFlow");
            return (Criteria) this;
        }

        public Criteria andCreateUserFlowNotBetween(String value1, String value2) {
            addCriterion("CREATE_USER_FLOW not between", value1, value2, "createUserFlow");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("MODIFY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("MODIFY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(String value) {
            addCriterion("MODIFY_TIME =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(String value) {
            addCriterion("MODIFY_TIME <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(String value) {
            addCriterion("MODIFY_TIME >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(String value) {
            addCriterion("MODIFY_TIME >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(String value) {
            addCriterion("MODIFY_TIME <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(String value) {
            addCriterion("MODIFY_TIME <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLike(String value) {
            addCriterion("MODIFY_TIME like", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotLike(String value) {
            addCriterion("MODIFY_TIME not like", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<String> values) {
            addCriterion("MODIFY_TIME in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<String> values) {
            addCriterion("MODIFY_TIME not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(String value1, String value2) {
            addCriterion("MODIFY_TIME between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(String value1, String value2) {
            addCriterion("MODIFY_TIME not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowIsNull() {
            addCriterion("MODIFY_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowIsNotNull() {
            addCriterion("MODIFY_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowEqualTo(String value) {
            addCriterion("MODIFY_USER_FLOW =", value, "modifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowNotEqualTo(String value) {
            addCriterion("MODIFY_USER_FLOW <>", value, "modifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowGreaterThan(String value) {
            addCriterion("MODIFY_USER_FLOW >", value, "modifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("MODIFY_USER_FLOW >=", value, "modifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowLessThan(String value) {
            addCriterion("MODIFY_USER_FLOW <", value, "modifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowLessThanOrEqualTo(String value) {
            addCriterion("MODIFY_USER_FLOW <=", value, "modifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowLike(String value) {
            addCriterion("MODIFY_USER_FLOW like", value, "modifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowNotLike(String value) {
            addCriterion("MODIFY_USER_FLOW not like", value, "modifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowIn(List<String> values) {
            addCriterion("MODIFY_USER_FLOW in", values, "modifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowNotIn(List<String> values) {
            addCriterion("MODIFY_USER_FLOW not in", values, "modifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowBetween(String value1, String value2) {
            addCriterion("MODIFY_USER_FLOW between", value1, value2, "modifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andModifyUserFlowNotBetween(String value1, String value2) {
            addCriterion("MODIFY_USER_FLOW not between", value1, value2, "modifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andScoreToplimitIsNull() {
            addCriterion("SCORE_TOPLIMIT is null");
            return (Criteria) this;
        }

        public Criteria andScoreToplimitIsNotNull() {
            addCriterion("SCORE_TOPLIMIT is not null");
            return (Criteria) this;
        }

        public Criteria andScoreToplimitEqualTo(String value) {
            addCriterion("SCORE_TOPLIMIT =", value, "scoreToplimit");
            return (Criteria) this;
        }

        public Criteria andScoreToplimitNotEqualTo(String value) {
            addCriterion("SCORE_TOPLIMIT <>", value, "scoreToplimit");
            return (Criteria) this;
        }

        public Criteria andScoreToplimitGreaterThan(String value) {
            addCriterion("SCORE_TOPLIMIT >", value, "scoreToplimit");
            return (Criteria) this;
        }

        public Criteria andScoreToplimitGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_TOPLIMIT >=", value, "scoreToplimit");
            return (Criteria) this;
        }

        public Criteria andScoreToplimitLessThan(String value) {
            addCriterion("SCORE_TOPLIMIT <", value, "scoreToplimit");
            return (Criteria) this;
        }

        public Criteria andScoreToplimitLessThanOrEqualTo(String value) {
            addCriterion("SCORE_TOPLIMIT <=", value, "scoreToplimit");
            return (Criteria) this;
        }

        public Criteria andScoreToplimitLike(String value) {
            addCriterion("SCORE_TOPLIMIT like", value, "scoreToplimit");
            return (Criteria) this;
        }

        public Criteria andScoreToplimitNotLike(String value) {
            addCriterion("SCORE_TOPLIMIT not like", value, "scoreToplimit");
            return (Criteria) this;
        }

        public Criteria andScoreToplimitIn(List<String> values) {
            addCriterion("SCORE_TOPLIMIT in", values, "scoreToplimit");
            return (Criteria) this;
        }

        public Criteria andScoreToplimitNotIn(List<String> values) {
            addCriterion("SCORE_TOPLIMIT not in", values, "scoreToplimit");
            return (Criteria) this;
        }

        public Criteria andScoreToplimitBetween(String value1, String value2) {
            addCriterion("SCORE_TOPLIMIT between", value1, value2, "scoreToplimit");
            return (Criteria) this;
        }

        public Criteria andScoreToplimitNotBetween(String value1, String value2) {
            addCriterion("SCORE_TOPLIMIT not between", value1, value2, "scoreToplimit");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}