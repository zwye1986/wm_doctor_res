package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResKgCfgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResKgCfgExample() {
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

        public Criteria andDoctorTypeIdIsNull() {
            addCriterion("DOCTOR_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdIsNotNull() {
            addCriterion("DOCTOR_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_ID =", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdNotEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_ID <>", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdGreaterThan(String value) {
            addCriterion("DOCTOR_TYPE_ID >", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_ID >=", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdLessThan(String value) {
            addCriterion("DOCTOR_TYPE_ID <", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_ID <=", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdLike(String value) {
            addCriterion("DOCTOR_TYPE_ID like", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdNotLike(String value) {
            addCriterion("DOCTOR_TYPE_ID not like", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdIn(List<String> values) {
            addCriterion("DOCTOR_TYPE_ID in", values, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdNotIn(List<String> values) {
            addCriterion("DOCTOR_TYPE_ID not in", values, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdBetween(String value1, String value2) {
            addCriterion("DOCTOR_TYPE_ID between", value1, value2, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_TYPE_ID not between", value1, value2, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andLessOrGreaterIsNull() {
            addCriterion("LESS_OR_GREATER is null");
            return (Criteria) this;
        }

        public Criteria andLessOrGreaterIsNotNull() {
            addCriterion("LESS_OR_GREATER is not null");
            return (Criteria) this;
        }

        public Criteria andLessOrGreaterEqualTo(String value) {
            addCriterion("LESS_OR_GREATER =", value, "lessOrGreater");
            return (Criteria) this;
        }

        public Criteria andLessOrGreaterNotEqualTo(String value) {
            addCriterion("LESS_OR_GREATER <>", value, "lessOrGreater");
            return (Criteria) this;
        }

        public Criteria andLessOrGreaterGreaterThan(String value) {
            addCriterion("LESS_OR_GREATER >", value, "lessOrGreater");
            return (Criteria) this;
        }

        public Criteria andLessOrGreaterGreaterThanOrEqualTo(String value) {
            addCriterion("LESS_OR_GREATER >=", value, "lessOrGreater");
            return (Criteria) this;
        }

        public Criteria andLessOrGreaterLessThan(String value) {
            addCriterion("LESS_OR_GREATER <", value, "lessOrGreater");
            return (Criteria) this;
        }

        public Criteria andLessOrGreaterLessThanOrEqualTo(String value) {
            addCriterion("LESS_OR_GREATER <=", value, "lessOrGreater");
            return (Criteria) this;
        }

        public Criteria andLessOrGreaterLike(String value) {
            addCriterion("LESS_OR_GREATER like", value, "lessOrGreater");
            return (Criteria) this;
        }

        public Criteria andLessOrGreaterNotLike(String value) {
            addCriterion("LESS_OR_GREATER not like", value, "lessOrGreater");
            return (Criteria) this;
        }

        public Criteria andLessOrGreaterIn(List<String> values) {
            addCriterion("LESS_OR_GREATER in", values, "lessOrGreater");
            return (Criteria) this;
        }

        public Criteria andLessOrGreaterNotIn(List<String> values) {
            addCriterion("LESS_OR_GREATER not in", values, "lessOrGreater");
            return (Criteria) this;
        }

        public Criteria andLessOrGreaterBetween(String value1, String value2) {
            addCriterion("LESS_OR_GREATER between", value1, value2, "lessOrGreater");
            return (Criteria) this;
        }

        public Criteria andLessOrGreaterNotBetween(String value1, String value2) {
            addCriterion("LESS_OR_GREATER not between", value1, value2, "lessOrGreater");
            return (Criteria) this;
        }

        public Criteria andAllDaysIsNull() {
            addCriterion("ALL_DAYS is null");
            return (Criteria) this;
        }

        public Criteria andAllDaysIsNotNull() {
            addCriterion("ALL_DAYS is not null");
            return (Criteria) this;
        }

        public Criteria andAllDaysEqualTo(String value) {
            addCriterion("ALL_DAYS =", value, "allDays");
            return (Criteria) this;
        }

        public Criteria andAllDaysNotEqualTo(String value) {
            addCriterion("ALL_DAYS <>", value, "allDays");
            return (Criteria) this;
        }

        public Criteria andAllDaysGreaterThan(String value) {
            addCriterion("ALL_DAYS >", value, "allDays");
            return (Criteria) this;
        }

        public Criteria andAllDaysGreaterThanOrEqualTo(String value) {
            addCriterion("ALL_DAYS >=", value, "allDays");
            return (Criteria) this;
        }

        public Criteria andAllDaysLessThan(String value) {
            addCriterion("ALL_DAYS <", value, "allDays");
            return (Criteria) this;
        }

        public Criteria andAllDaysLessThanOrEqualTo(String value) {
            addCriterion("ALL_DAYS <=", value, "allDays");
            return (Criteria) this;
        }

        public Criteria andAllDaysLike(String value) {
            addCriterion("ALL_DAYS like", value, "allDays");
            return (Criteria) this;
        }

        public Criteria andAllDaysNotLike(String value) {
            addCriterion("ALL_DAYS not like", value, "allDays");
            return (Criteria) this;
        }

        public Criteria andAllDaysIn(List<String> values) {
            addCriterion("ALL_DAYS in", values, "allDays");
            return (Criteria) this;
        }

        public Criteria andAllDaysNotIn(List<String> values) {
            addCriterion("ALL_DAYS not in", values, "allDays");
            return (Criteria) this;
        }

        public Criteria andAllDaysBetween(String value1, String value2) {
            addCriterion("ALL_DAYS between", value1, value2, "allDays");
            return (Criteria) this;
        }

        public Criteria andAllDaysNotBetween(String value1, String value2) {
            addCriterion("ALL_DAYS not between", value1, value2, "allDays");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysIsNull() {
            addCriterion("INTERVAL_DAYS is null");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysIsNotNull() {
            addCriterion("INTERVAL_DAYS is not null");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysEqualTo(String value) {
            addCriterion("INTERVAL_DAYS =", value, "intervalDays");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysNotEqualTo(String value) {
            addCriterion("INTERVAL_DAYS <>", value, "intervalDays");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysGreaterThan(String value) {
            addCriterion("INTERVAL_DAYS >", value, "intervalDays");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysGreaterThanOrEqualTo(String value) {
            addCriterion("INTERVAL_DAYS >=", value, "intervalDays");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysLessThan(String value) {
            addCriterion("INTERVAL_DAYS <", value, "intervalDays");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysLessThanOrEqualTo(String value) {
            addCriterion("INTERVAL_DAYS <=", value, "intervalDays");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysLike(String value) {
            addCriterion("INTERVAL_DAYS like", value, "intervalDays");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysNotLike(String value) {
            addCriterion("INTERVAL_DAYS not like", value, "intervalDays");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysIn(List<String> values) {
            addCriterion("INTERVAL_DAYS in", values, "intervalDays");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysNotIn(List<String> values) {
            addCriterion("INTERVAL_DAYS not in", values, "intervalDays");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysBetween(String value1, String value2) {
            addCriterion("INTERVAL_DAYS between", value1, value2, "intervalDays");
            return (Criteria) this;
        }

        public Criteria andIntervalDaysNotBetween(String value1, String value2) {
            addCriterion("INTERVAL_DAYS not between", value1, value2, "intervalDays");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagIsNull() {
            addCriterion("TEACHER_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagIsNotNull() {
            addCriterion("TEACHER_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagEqualTo(String value) {
            addCriterion("TEACHER_FLAG =", value, "teacherFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagNotEqualTo(String value) {
            addCriterion("TEACHER_FLAG <>", value, "teacherFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagGreaterThan(String value) {
            addCriterion("TEACHER_FLAG >", value, "teacherFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_FLAG >=", value, "teacherFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagLessThan(String value) {
            addCriterion("TEACHER_FLAG <", value, "teacherFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_FLAG <=", value, "teacherFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagLike(String value) {
            addCriterion("TEACHER_FLAG like", value, "teacherFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagNotLike(String value) {
            addCriterion("TEACHER_FLAG not like", value, "teacherFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagIn(List<String> values) {
            addCriterion("TEACHER_FLAG in", values, "teacherFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagNotIn(List<String> values) {
            addCriterion("TEACHER_FLAG not in", values, "teacherFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagBetween(String value1, String value2) {
            addCriterion("TEACHER_FLAG between", value1, value2, "teacherFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherFlagNotBetween(String value1, String value2) {
            addCriterion("TEACHER_FLAG not between", value1, value2, "teacherFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherNameIsNull() {
            addCriterion("TEACHER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTeacherNameIsNotNull() {
            addCriterion("TEACHER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherNameEqualTo(String value) {
            addCriterion("TEACHER_NAME =", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotEqualTo(String value) {
            addCriterion("TEACHER_NAME <>", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameGreaterThan(String value) {
            addCriterion("TEACHER_NAME >", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_NAME >=", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameLessThan(String value) {
            addCriterion("TEACHER_NAME <", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_NAME <=", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameLike(String value) {
            addCriterion("TEACHER_NAME like", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotLike(String value) {
            addCriterion("TEACHER_NAME not like", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameIn(List<String> values) {
            addCriterion("TEACHER_NAME in", values, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotIn(List<String> values) {
            addCriterion("TEACHER_NAME not in", values, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameBetween(String value1, String value2) {
            addCriterion("TEACHER_NAME between", value1, value2, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotBetween(String value1, String value2) {
            addCriterion("TEACHER_NAME not between", value1, value2, "teacherName");
            return (Criteria) this;
        }

        public Criteria andHeadFlagIsNull() {
            addCriterion("HEAD_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andHeadFlagIsNotNull() {
            addCriterion("HEAD_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andHeadFlagEqualTo(String value) {
            addCriterion("HEAD_FLAG =", value, "headFlag");
            return (Criteria) this;
        }

        public Criteria andHeadFlagNotEqualTo(String value) {
            addCriterion("HEAD_FLAG <>", value, "headFlag");
            return (Criteria) this;
        }

        public Criteria andHeadFlagGreaterThan(String value) {
            addCriterion("HEAD_FLAG >", value, "headFlag");
            return (Criteria) this;
        }

        public Criteria andHeadFlagGreaterThanOrEqualTo(String value) {
            addCriterion("HEAD_FLAG >=", value, "headFlag");
            return (Criteria) this;
        }

        public Criteria andHeadFlagLessThan(String value) {
            addCriterion("HEAD_FLAG <", value, "headFlag");
            return (Criteria) this;
        }

        public Criteria andHeadFlagLessThanOrEqualTo(String value) {
            addCriterion("HEAD_FLAG <=", value, "headFlag");
            return (Criteria) this;
        }

        public Criteria andHeadFlagLike(String value) {
            addCriterion("HEAD_FLAG like", value, "headFlag");
            return (Criteria) this;
        }

        public Criteria andHeadFlagNotLike(String value) {
            addCriterion("HEAD_FLAG not like", value, "headFlag");
            return (Criteria) this;
        }

        public Criteria andHeadFlagIn(List<String> values) {
            addCriterion("HEAD_FLAG in", values, "headFlag");
            return (Criteria) this;
        }

        public Criteria andHeadFlagNotIn(List<String> values) {
            addCriterion("HEAD_FLAG not in", values, "headFlag");
            return (Criteria) this;
        }

        public Criteria andHeadFlagBetween(String value1, String value2) {
            addCriterion("HEAD_FLAG between", value1, value2, "headFlag");
            return (Criteria) this;
        }

        public Criteria andHeadFlagNotBetween(String value1, String value2) {
            addCriterion("HEAD_FLAG not between", value1, value2, "headFlag");
            return (Criteria) this;
        }

        public Criteria andHeadNameIsNull() {
            addCriterion("HEAD_NAME is null");
            return (Criteria) this;
        }

        public Criteria andHeadNameIsNotNull() {
            addCriterion("HEAD_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andHeadNameEqualTo(String value) {
            addCriterion("HEAD_NAME =", value, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameNotEqualTo(String value) {
            addCriterion("HEAD_NAME <>", value, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameGreaterThan(String value) {
            addCriterion("HEAD_NAME >", value, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameGreaterThanOrEqualTo(String value) {
            addCriterion("HEAD_NAME >=", value, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameLessThan(String value) {
            addCriterion("HEAD_NAME <", value, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameLessThanOrEqualTo(String value) {
            addCriterion("HEAD_NAME <=", value, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameLike(String value) {
            addCriterion("HEAD_NAME like", value, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameNotLike(String value) {
            addCriterion("HEAD_NAME not like", value, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameIn(List<String> values) {
            addCriterion("HEAD_NAME in", values, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameNotIn(List<String> values) {
            addCriterion("HEAD_NAME not in", values, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameBetween(String value1, String value2) {
            addCriterion("HEAD_NAME between", value1, value2, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameNotBetween(String value1, String value2) {
            addCriterion("HEAD_NAME not between", value1, value2, "headName");
            return (Criteria) this;
        }

        public Criteria andTutorFlagIsNull() {
            addCriterion("TUTOR_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andTutorFlagIsNotNull() {
            addCriterion("TUTOR_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andTutorFlagEqualTo(String value) {
            addCriterion("TUTOR_FLAG =", value, "tutorFlag");
            return (Criteria) this;
        }

        public Criteria andTutorFlagNotEqualTo(String value) {
            addCriterion("TUTOR_FLAG <>", value, "tutorFlag");
            return (Criteria) this;
        }

        public Criteria andTutorFlagGreaterThan(String value) {
            addCriterion("TUTOR_FLAG >", value, "tutorFlag");
            return (Criteria) this;
        }

        public Criteria andTutorFlagGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_FLAG >=", value, "tutorFlag");
            return (Criteria) this;
        }

        public Criteria andTutorFlagLessThan(String value) {
            addCriterion("TUTOR_FLAG <", value, "tutorFlag");
            return (Criteria) this;
        }

        public Criteria andTutorFlagLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_FLAG <=", value, "tutorFlag");
            return (Criteria) this;
        }

        public Criteria andTutorFlagLike(String value) {
            addCriterion("TUTOR_FLAG like", value, "tutorFlag");
            return (Criteria) this;
        }

        public Criteria andTutorFlagNotLike(String value) {
            addCriterion("TUTOR_FLAG not like", value, "tutorFlag");
            return (Criteria) this;
        }

        public Criteria andTutorFlagIn(List<String> values) {
            addCriterion("TUTOR_FLAG in", values, "tutorFlag");
            return (Criteria) this;
        }

        public Criteria andTutorFlagNotIn(List<String> values) {
            addCriterion("TUTOR_FLAG not in", values, "tutorFlag");
            return (Criteria) this;
        }

        public Criteria andTutorFlagBetween(String value1, String value2) {
            addCriterion("TUTOR_FLAG between", value1, value2, "tutorFlag");
            return (Criteria) this;
        }

        public Criteria andTutorFlagNotBetween(String value1, String value2) {
            addCriterion("TUTOR_FLAG not between", value1, value2, "tutorFlag");
            return (Criteria) this;
        }

        public Criteria andTutorNameIsNull() {
            addCriterion("TUTOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTutorNameIsNotNull() {
            addCriterion("TUTOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTutorNameEqualTo(String value) {
            addCriterion("TUTOR_NAME =", value, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameNotEqualTo(String value) {
            addCriterion("TUTOR_NAME <>", value, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameGreaterThan(String value) {
            addCriterion("TUTOR_NAME >", value, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_NAME >=", value, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameLessThan(String value) {
            addCriterion("TUTOR_NAME <", value, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_NAME <=", value, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameLike(String value) {
            addCriterion("TUTOR_NAME like", value, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameNotLike(String value) {
            addCriterion("TUTOR_NAME not like", value, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameIn(List<String> values) {
            addCriterion("TUTOR_NAME in", values, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameNotIn(List<String> values) {
            addCriterion("TUTOR_NAME not in", values, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameBetween(String value1, String value2) {
            addCriterion("TUTOR_NAME between", value1, value2, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameNotBetween(String value1, String value2) {
            addCriterion("TUTOR_NAME not between", value1, value2, "tutorName");
            return (Criteria) this;
        }

        public Criteria andManagerFlagIsNull() {
            addCriterion("MANAGER_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andManagerFlagIsNotNull() {
            addCriterion("MANAGER_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andManagerFlagEqualTo(String value) {
            addCriterion("MANAGER_FLAG =", value, "managerFlag");
            return (Criteria) this;
        }

        public Criteria andManagerFlagNotEqualTo(String value) {
            addCriterion("MANAGER_FLAG <>", value, "managerFlag");
            return (Criteria) this;
        }

        public Criteria andManagerFlagGreaterThan(String value) {
            addCriterion("MANAGER_FLAG >", value, "managerFlag");
            return (Criteria) this;
        }

        public Criteria andManagerFlagGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGER_FLAG >=", value, "managerFlag");
            return (Criteria) this;
        }

        public Criteria andManagerFlagLessThan(String value) {
            addCriterion("MANAGER_FLAG <", value, "managerFlag");
            return (Criteria) this;
        }

        public Criteria andManagerFlagLessThanOrEqualTo(String value) {
            addCriterion("MANAGER_FLAG <=", value, "managerFlag");
            return (Criteria) this;
        }

        public Criteria andManagerFlagLike(String value) {
            addCriterion("MANAGER_FLAG like", value, "managerFlag");
            return (Criteria) this;
        }

        public Criteria andManagerFlagNotLike(String value) {
            addCriterion("MANAGER_FLAG not like", value, "managerFlag");
            return (Criteria) this;
        }

        public Criteria andManagerFlagIn(List<String> values) {
            addCriterion("MANAGER_FLAG in", values, "managerFlag");
            return (Criteria) this;
        }

        public Criteria andManagerFlagNotIn(List<String> values) {
            addCriterion("MANAGER_FLAG not in", values, "managerFlag");
            return (Criteria) this;
        }

        public Criteria andManagerFlagBetween(String value1, String value2) {
            addCriterion("MANAGER_FLAG between", value1, value2, "managerFlag");
            return (Criteria) this;
        }

        public Criteria andManagerFlagNotBetween(String value1, String value2) {
            addCriterion("MANAGER_FLAG not between", value1, value2, "managerFlag");
            return (Criteria) this;
        }

        public Criteria andManagerNameIsNull() {
            addCriterion("MANAGER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andManagerNameIsNotNull() {
            addCriterion("MANAGER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andManagerNameEqualTo(String value) {
            addCriterion("MANAGER_NAME =", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameNotEqualTo(String value) {
            addCriterion("MANAGER_NAME <>", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameGreaterThan(String value) {
            addCriterion("MANAGER_NAME >", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGER_NAME >=", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameLessThan(String value) {
            addCriterion("MANAGER_NAME <", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameLessThanOrEqualTo(String value) {
            addCriterion("MANAGER_NAME <=", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameLike(String value) {
            addCriterion("MANAGER_NAME like", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameNotLike(String value) {
            addCriterion("MANAGER_NAME not like", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameIn(List<String> values) {
            addCriterion("MANAGER_NAME in", values, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameNotIn(List<String> values) {
            addCriterion("MANAGER_NAME not in", values, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameBetween(String value1, String value2) {
            addCriterion("MANAGER_NAME between", value1, value2, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameNotBetween(String value1, String value2) {
            addCriterion("MANAGER_NAME not between", value1, value2, "managerName");
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

        public Criteria andIntervalDays2IsNull() {
            addCriterion("INTERVAL_DAYS2 is null");
            return (Criteria) this;
        }

        public Criteria andIntervalDays2IsNotNull() {
            addCriterion("INTERVAL_DAYS2 is not null");
            return (Criteria) this;
        }

        public Criteria andIntervalDays2EqualTo(String value) {
            addCriterion("INTERVAL_DAYS2 =", value, "intervalDays2");
            return (Criteria) this;
        }

        public Criteria andIntervalDays2NotEqualTo(String value) {
            addCriterion("INTERVAL_DAYS2 <>", value, "intervalDays2");
            return (Criteria) this;
        }

        public Criteria andIntervalDays2GreaterThan(String value) {
            addCriterion("INTERVAL_DAYS2 >", value, "intervalDays2");
            return (Criteria) this;
        }

        public Criteria andIntervalDays2GreaterThanOrEqualTo(String value) {
            addCriterion("INTERVAL_DAYS2 >=", value, "intervalDays2");
            return (Criteria) this;
        }

        public Criteria andIntervalDays2LessThan(String value) {
            addCriterion("INTERVAL_DAYS2 <", value, "intervalDays2");
            return (Criteria) this;
        }

        public Criteria andIntervalDays2LessThanOrEqualTo(String value) {
            addCriterion("INTERVAL_DAYS2 <=", value, "intervalDays2");
            return (Criteria) this;
        }

        public Criteria andIntervalDays2Like(String value) {
            addCriterion("INTERVAL_DAYS2 like", value, "intervalDays2");
            return (Criteria) this;
        }

        public Criteria andIntervalDays2NotLike(String value) {
            addCriterion("INTERVAL_DAYS2 not like", value, "intervalDays2");
            return (Criteria) this;
        }

        public Criteria andIntervalDays2In(List<String> values) {
            addCriterion("INTERVAL_DAYS2 in", values, "intervalDays2");
            return (Criteria) this;
        }

        public Criteria andIntervalDays2NotIn(List<String> values) {
            addCriterion("INTERVAL_DAYS2 not in", values, "intervalDays2");
            return (Criteria) this;
        }

        public Criteria andIntervalDays2Between(String value1, String value2) {
            addCriterion("INTERVAL_DAYS2 between", value1, value2, "intervalDays2");
            return (Criteria) this;
        }

        public Criteria andIntervalDays2NotBetween(String value1, String value2) {
            addCriterion("INTERVAL_DAYS2 not between", value1, value2, "intervalDays2");
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