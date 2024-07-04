package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class StuFurtherStudyRegistExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StuFurtherStudyRegistExample() {
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

        public Criteria andRecordFlowIsNull() {
            addCriterion("RECORD_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRecordFlowIsNotNull() {
            addCriterion("RECORD_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRecordFlowEqualTo(String value) {
            addCriterion("RECORD_FLOW =", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowNotEqualTo(String value) {
            addCriterion("RECORD_FLOW <>", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowGreaterThan(String value) {
            addCriterion("RECORD_FLOW >", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RECORD_FLOW >=", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowLessThan(String value) {
            addCriterion("RECORD_FLOW <", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowLessThanOrEqualTo(String value) {
            addCriterion("RECORD_FLOW <=", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowLike(String value) {
            addCriterion("RECORD_FLOW like", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowNotLike(String value) {
            addCriterion("RECORD_FLOW not like", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowIn(List<String> values) {
            addCriterion("RECORD_FLOW in", values, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowNotIn(List<String> values) {
            addCriterion("RECORD_FLOW not in", values, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowBetween(String value1, String value2) {
            addCriterion("RECORD_FLOW between", value1, value2, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowNotBetween(String value1, String value2) {
            addCriterion("RECORD_FLOW not between", value1, value2, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowIsNull() {
            addCriterion("USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andUserFlowIsNotNull() {
            addCriterion("USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andUserFlowEqualTo(String value) {
            addCriterion("USER_FLOW =", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotEqualTo(String value) {
            addCriterion("USER_FLOW <>", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowGreaterThan(String value) {
            addCriterion("USER_FLOW >", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("USER_FLOW >=", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowLessThan(String value) {
            addCriterion("USER_FLOW <", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowLessThanOrEqualTo(String value) {
            addCriterion("USER_FLOW <=", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowLike(String value) {
            addCriterion("USER_FLOW like", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotLike(String value) {
            addCriterion("USER_FLOW not like", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowIn(List<String> values) {
            addCriterion("USER_FLOW in", values, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotIn(List<String> values) {
            addCriterion("USER_FLOW not in", values, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowBetween(String value1, String value2) {
            addCriterion("USER_FLOW between", value1, value2, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotBetween(String value1, String value2) {
            addCriterion("USER_FLOW not between", value1, value2, "userFlow");
            return (Criteria) this;
        }

        public Criteria andDeptIdIsNull() {
            addCriterion("DEPT_ID is null");
            return (Criteria) this;
        }

        public Criteria andDeptIdIsNotNull() {
            addCriterion("DEPT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDeptIdEqualTo(String value) {
            addCriterion("DEPT_ID =", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdNotEqualTo(String value) {
            addCriterion("DEPT_ID <>", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdGreaterThan(String value) {
            addCriterion("DEPT_ID >", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_ID >=", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdLessThan(String value) {
            addCriterion("DEPT_ID <", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdLessThanOrEqualTo(String value) {
            addCriterion("DEPT_ID <=", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdLike(String value) {
            addCriterion("DEPT_ID like", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdNotLike(String value) {
            addCriterion("DEPT_ID not like", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdIn(List<String> values) {
            addCriterion("DEPT_ID in", values, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdNotIn(List<String> values) {
            addCriterion("DEPT_ID not in", values, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdBetween(String value1, String value2) {
            addCriterion("DEPT_ID between", value1, value2, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdNotBetween(String value1, String value2) {
            addCriterion("DEPT_ID not between", value1, value2, "deptId");
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

        public Criteria andUserAgeIsNull() {
            addCriterion("USER_AGE is null");
            return (Criteria) this;
        }

        public Criteria andUserAgeIsNotNull() {
            addCriterion("USER_AGE is not null");
            return (Criteria) this;
        }

        public Criteria andUserAgeEqualTo(String value) {
            addCriterion("USER_AGE =", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeNotEqualTo(String value) {
            addCriterion("USER_AGE <>", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeGreaterThan(String value) {
            addCriterion("USER_AGE >", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeGreaterThanOrEqualTo(String value) {
            addCriterion("USER_AGE >=", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeLessThan(String value) {
            addCriterion("USER_AGE <", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeLessThanOrEqualTo(String value) {
            addCriterion("USER_AGE <=", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeLike(String value) {
            addCriterion("USER_AGE like", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeNotLike(String value) {
            addCriterion("USER_AGE not like", value, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeIn(List<String> values) {
            addCriterion("USER_AGE in", values, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeNotIn(List<String> values) {
            addCriterion("USER_AGE not in", values, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeBetween(String value1, String value2) {
            addCriterion("USER_AGE between", value1, value2, "userAge");
            return (Criteria) this;
        }

        public Criteria andUserAgeNotBetween(String value1, String value2) {
            addCriterion("USER_AGE not between", value1, value2, "userAge");
            return (Criteria) this;
        }

        public Criteria andEngagedProfessionIsNull() {
            addCriterion("ENGAGED_PROFESSION is null");
            return (Criteria) this;
        }

        public Criteria andEngagedProfessionIsNotNull() {
            addCriterion("ENGAGED_PROFESSION is not null");
            return (Criteria) this;
        }

        public Criteria andEngagedProfessionEqualTo(String value) {
            addCriterion("ENGAGED_PROFESSION =", value, "engagedProfession");
            return (Criteria) this;
        }

        public Criteria andEngagedProfessionNotEqualTo(String value) {
            addCriterion("ENGAGED_PROFESSION <>", value, "engagedProfession");
            return (Criteria) this;
        }

        public Criteria andEngagedProfessionGreaterThan(String value) {
            addCriterion("ENGAGED_PROFESSION >", value, "engagedProfession");
            return (Criteria) this;
        }

        public Criteria andEngagedProfessionGreaterThanOrEqualTo(String value) {
            addCriterion("ENGAGED_PROFESSION >=", value, "engagedProfession");
            return (Criteria) this;
        }

        public Criteria andEngagedProfessionLessThan(String value) {
            addCriterion("ENGAGED_PROFESSION <", value, "engagedProfession");
            return (Criteria) this;
        }

        public Criteria andEngagedProfessionLessThanOrEqualTo(String value) {
            addCriterion("ENGAGED_PROFESSION <=", value, "engagedProfession");
            return (Criteria) this;
        }

        public Criteria andEngagedProfessionLike(String value) {
            addCriterion("ENGAGED_PROFESSION like", value, "engagedProfession");
            return (Criteria) this;
        }

        public Criteria andEngagedProfessionNotLike(String value) {
            addCriterion("ENGAGED_PROFESSION not like", value, "engagedProfession");
            return (Criteria) this;
        }

        public Criteria andEngagedProfessionIn(List<String> values) {
            addCriterion("ENGAGED_PROFESSION in", values, "engagedProfession");
            return (Criteria) this;
        }

        public Criteria andEngagedProfessionNotIn(List<String> values) {
            addCriterion("ENGAGED_PROFESSION not in", values, "engagedProfession");
            return (Criteria) this;
        }

        public Criteria andEngagedProfessionBetween(String value1, String value2) {
            addCriterion("ENGAGED_PROFESSION between", value1, value2, "engagedProfession");
            return (Criteria) this;
        }

        public Criteria andEngagedProfessionNotBetween(String value1, String value2) {
            addCriterion("ENGAGED_PROFESSION not between", value1, value2, "engagedProfession");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeIsNull() {
            addCriterion("ADMISSION_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeIsNotNull() {
            addCriterion("ADMISSION_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeEqualTo(String value) {
            addCriterion("ADMISSION_TIME =", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeNotEqualTo(String value) {
            addCriterion("ADMISSION_TIME <>", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeGreaterThan(String value) {
            addCriterion("ADMISSION_TIME >", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeGreaterThanOrEqualTo(String value) {
            addCriterion("ADMISSION_TIME >=", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeLessThan(String value) {
            addCriterion("ADMISSION_TIME <", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeLessThanOrEqualTo(String value) {
            addCriterion("ADMISSION_TIME <=", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeLike(String value) {
            addCriterion("ADMISSION_TIME like", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeNotLike(String value) {
            addCriterion("ADMISSION_TIME not like", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeIn(List<String> values) {
            addCriterion("ADMISSION_TIME in", values, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeNotIn(List<String> values) {
            addCriterion("ADMISSION_TIME not in", values, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeBetween(String value1, String value2) {
            addCriterion("ADMISSION_TIME between", value1, value2, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeNotBetween(String value1, String value2) {
            addCriterion("ADMISSION_TIME not between", value1, value2, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andTitleIdIsNull() {
            addCriterion("TITLE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTitleIdIsNotNull() {
            addCriterion("TITLE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTitleIdEqualTo(String value) {
            addCriterion("TITLE_ID =", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdNotEqualTo(String value) {
            addCriterion("TITLE_ID <>", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdGreaterThan(String value) {
            addCriterion("TITLE_ID >", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE_ID >=", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdLessThan(String value) {
            addCriterion("TITLE_ID <", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdLessThanOrEqualTo(String value) {
            addCriterion("TITLE_ID <=", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdLike(String value) {
            addCriterion("TITLE_ID like", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdNotLike(String value) {
            addCriterion("TITLE_ID not like", value, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdIn(List<String> values) {
            addCriterion("TITLE_ID in", values, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdNotIn(List<String> values) {
            addCriterion("TITLE_ID not in", values, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdBetween(String value1, String value2) {
            addCriterion("TITLE_ID between", value1, value2, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleIdNotBetween(String value1, String value2) {
            addCriterion("TITLE_ID not between", value1, value2, "titleId");
            return (Criteria) this;
        }

        public Criteria andTitleNameIsNull() {
            addCriterion("TITLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTitleNameIsNotNull() {
            addCriterion("TITLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTitleNameEqualTo(String value) {
            addCriterion("TITLE_NAME =", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameNotEqualTo(String value) {
            addCriterion("TITLE_NAME <>", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameGreaterThan(String value) {
            addCriterion("TITLE_NAME >", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE_NAME >=", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameLessThan(String value) {
            addCriterion("TITLE_NAME <", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameLessThanOrEqualTo(String value) {
            addCriterion("TITLE_NAME <=", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameLike(String value) {
            addCriterion("TITLE_NAME like", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameNotLike(String value) {
            addCriterion("TITLE_NAME not like", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameIn(List<String> values) {
            addCriterion("TITLE_NAME in", values, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameNotIn(List<String> values) {
            addCriterion("TITLE_NAME not in", values, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameBetween(String value1, String value2) {
            addCriterion("TITLE_NAME between", value1, value2, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameNotBetween(String value1, String value2) {
            addCriterion("TITLE_NAME not between", value1, value2, "titleName");
            return (Criteria) this;
        }

        public Criteria andStationPropertyIdIsNull() {
            addCriterion("STATION_PROPERTY_ID is null");
            return (Criteria) this;
        }

        public Criteria andStationPropertyIdIsNotNull() {
            addCriterion("STATION_PROPERTY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStationPropertyIdEqualTo(String value) {
            addCriterion("STATION_PROPERTY_ID =", value, "stationPropertyId");
            return (Criteria) this;
        }

        public Criteria andStationPropertyIdNotEqualTo(String value) {
            addCriterion("STATION_PROPERTY_ID <>", value, "stationPropertyId");
            return (Criteria) this;
        }

        public Criteria andStationPropertyIdGreaterThan(String value) {
            addCriterion("STATION_PROPERTY_ID >", value, "stationPropertyId");
            return (Criteria) this;
        }

        public Criteria andStationPropertyIdGreaterThanOrEqualTo(String value) {
            addCriterion("STATION_PROPERTY_ID >=", value, "stationPropertyId");
            return (Criteria) this;
        }

        public Criteria andStationPropertyIdLessThan(String value) {
            addCriterion("STATION_PROPERTY_ID <", value, "stationPropertyId");
            return (Criteria) this;
        }

        public Criteria andStationPropertyIdLessThanOrEqualTo(String value) {
            addCriterion("STATION_PROPERTY_ID <=", value, "stationPropertyId");
            return (Criteria) this;
        }

        public Criteria andStationPropertyIdLike(String value) {
            addCriterion("STATION_PROPERTY_ID like", value, "stationPropertyId");
            return (Criteria) this;
        }

        public Criteria andStationPropertyIdNotLike(String value) {
            addCriterion("STATION_PROPERTY_ID not like", value, "stationPropertyId");
            return (Criteria) this;
        }

        public Criteria andStationPropertyIdIn(List<String> values) {
            addCriterion("STATION_PROPERTY_ID in", values, "stationPropertyId");
            return (Criteria) this;
        }

        public Criteria andStationPropertyIdNotIn(List<String> values) {
            addCriterion("STATION_PROPERTY_ID not in", values, "stationPropertyId");
            return (Criteria) this;
        }

        public Criteria andStationPropertyIdBetween(String value1, String value2) {
            addCriterion("STATION_PROPERTY_ID between", value1, value2, "stationPropertyId");
            return (Criteria) this;
        }

        public Criteria andStationPropertyIdNotBetween(String value1, String value2) {
            addCriterion("STATION_PROPERTY_ID not between", value1, value2, "stationPropertyId");
            return (Criteria) this;
        }

        public Criteria andStationPropertyNameIsNull() {
            addCriterion("STATION_PROPERTY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStationPropertyNameIsNotNull() {
            addCriterion("STATION_PROPERTY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStationPropertyNameEqualTo(String value) {
            addCriterion("STATION_PROPERTY_NAME =", value, "stationPropertyName");
            return (Criteria) this;
        }

        public Criteria andStationPropertyNameNotEqualTo(String value) {
            addCriterion("STATION_PROPERTY_NAME <>", value, "stationPropertyName");
            return (Criteria) this;
        }

        public Criteria andStationPropertyNameGreaterThan(String value) {
            addCriterion("STATION_PROPERTY_NAME >", value, "stationPropertyName");
            return (Criteria) this;
        }

        public Criteria andStationPropertyNameGreaterThanOrEqualTo(String value) {
            addCriterion("STATION_PROPERTY_NAME >=", value, "stationPropertyName");
            return (Criteria) this;
        }

        public Criteria andStationPropertyNameLessThan(String value) {
            addCriterion("STATION_PROPERTY_NAME <", value, "stationPropertyName");
            return (Criteria) this;
        }

        public Criteria andStationPropertyNameLessThanOrEqualTo(String value) {
            addCriterion("STATION_PROPERTY_NAME <=", value, "stationPropertyName");
            return (Criteria) this;
        }

        public Criteria andStationPropertyNameLike(String value) {
            addCriterion("STATION_PROPERTY_NAME like", value, "stationPropertyName");
            return (Criteria) this;
        }

        public Criteria andStationPropertyNameNotLike(String value) {
            addCriterion("STATION_PROPERTY_NAME not like", value, "stationPropertyName");
            return (Criteria) this;
        }

        public Criteria andStationPropertyNameIn(List<String> values) {
            addCriterion("STATION_PROPERTY_NAME in", values, "stationPropertyName");
            return (Criteria) this;
        }

        public Criteria andStationPropertyNameNotIn(List<String> values) {
            addCriterion("STATION_PROPERTY_NAME not in", values, "stationPropertyName");
            return (Criteria) this;
        }

        public Criteria andStationPropertyNameBetween(String value1, String value2) {
            addCriterion("STATION_PROPERTY_NAME between", value1, value2, "stationPropertyName");
            return (Criteria) this;
        }

        public Criteria andStationPropertyNameNotBetween(String value1, String value2) {
            addCriterion("STATION_PROPERTY_NAME not between", value1, value2, "stationPropertyName");
            return (Criteria) this;
        }

        public Criteria andSpe1NameIsNull() {
            addCriterion("SPE_1_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSpe1NameIsNotNull() {
            addCriterion("SPE_1_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSpe1NameEqualTo(String value) {
            addCriterion("SPE_1_NAME =", value, "spe1Name");
            return (Criteria) this;
        }

        public Criteria andSpe1NameNotEqualTo(String value) {
            addCriterion("SPE_1_NAME <>", value, "spe1Name");
            return (Criteria) this;
        }

        public Criteria andSpe1NameGreaterThan(String value) {
            addCriterion("SPE_1_NAME >", value, "spe1Name");
            return (Criteria) this;
        }

        public Criteria andSpe1NameGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_1_NAME >=", value, "spe1Name");
            return (Criteria) this;
        }

        public Criteria andSpe1NameLessThan(String value) {
            addCriterion("SPE_1_NAME <", value, "spe1Name");
            return (Criteria) this;
        }

        public Criteria andSpe1NameLessThanOrEqualTo(String value) {
            addCriterion("SPE_1_NAME <=", value, "spe1Name");
            return (Criteria) this;
        }

        public Criteria andSpe1NameLike(String value) {
            addCriterion("SPE_1_NAME like", value, "spe1Name");
            return (Criteria) this;
        }

        public Criteria andSpe1NameNotLike(String value) {
            addCriterion("SPE_1_NAME not like", value, "spe1Name");
            return (Criteria) this;
        }

        public Criteria andSpe1NameIn(List<String> values) {
            addCriterion("SPE_1_NAME in", values, "spe1Name");
            return (Criteria) this;
        }

        public Criteria andSpe1NameNotIn(List<String> values) {
            addCriterion("SPE_1_NAME not in", values, "spe1Name");
            return (Criteria) this;
        }

        public Criteria andSpe1NameBetween(String value1, String value2) {
            addCriterion("SPE_1_NAME between", value1, value2, "spe1Name");
            return (Criteria) this;
        }

        public Criteria andSpe1NameNotBetween(String value1, String value2) {
            addCriterion("SPE_1_NAME not between", value1, value2, "spe1Name");
            return (Criteria) this;
        }

        public Criteria andSpe2NameIsNull() {
            addCriterion("SPE_2_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSpe2NameIsNotNull() {
            addCriterion("SPE_2_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSpe2NameEqualTo(String value) {
            addCriterion("SPE_2_NAME =", value, "spe2Name");
            return (Criteria) this;
        }

        public Criteria andSpe2NameNotEqualTo(String value) {
            addCriterion("SPE_2_NAME <>", value, "spe2Name");
            return (Criteria) this;
        }

        public Criteria andSpe2NameGreaterThan(String value) {
            addCriterion("SPE_2_NAME >", value, "spe2Name");
            return (Criteria) this;
        }

        public Criteria andSpe2NameGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_2_NAME >=", value, "spe2Name");
            return (Criteria) this;
        }

        public Criteria andSpe2NameLessThan(String value) {
            addCriterion("SPE_2_NAME <", value, "spe2Name");
            return (Criteria) this;
        }

        public Criteria andSpe2NameLessThanOrEqualTo(String value) {
            addCriterion("SPE_2_NAME <=", value, "spe2Name");
            return (Criteria) this;
        }

        public Criteria andSpe2NameLike(String value) {
            addCriterion("SPE_2_NAME like", value, "spe2Name");
            return (Criteria) this;
        }

        public Criteria andSpe2NameNotLike(String value) {
            addCriterion("SPE_2_NAME not like", value, "spe2Name");
            return (Criteria) this;
        }

        public Criteria andSpe2NameIn(List<String> values) {
            addCriterion("SPE_2_NAME in", values, "spe2Name");
            return (Criteria) this;
        }

        public Criteria andSpe2NameNotIn(List<String> values) {
            addCriterion("SPE_2_NAME not in", values, "spe2Name");
            return (Criteria) this;
        }

        public Criteria andSpe2NameBetween(String value1, String value2) {
            addCriterion("SPE_2_NAME between", value1, value2, "spe2Name");
            return (Criteria) this;
        }

        public Criteria andSpe2NameNotBetween(String value1, String value2) {
            addCriterion("SPE_2_NAME not between", value1, value2, "spe2Name");
            return (Criteria) this;
        }

        public Criteria andStudyUnitIsNull() {
            addCriterion("STUDY_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andStudyUnitIsNotNull() {
            addCriterion("STUDY_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andStudyUnitEqualTo(String value) {
            addCriterion("STUDY_UNIT =", value, "studyUnit");
            return (Criteria) this;
        }

        public Criteria andStudyUnitNotEqualTo(String value) {
            addCriterion("STUDY_UNIT <>", value, "studyUnit");
            return (Criteria) this;
        }

        public Criteria andStudyUnitGreaterThan(String value) {
            addCriterion("STUDY_UNIT >", value, "studyUnit");
            return (Criteria) this;
        }

        public Criteria andStudyUnitGreaterThanOrEqualTo(String value) {
            addCriterion("STUDY_UNIT >=", value, "studyUnit");
            return (Criteria) this;
        }

        public Criteria andStudyUnitLessThan(String value) {
            addCriterion("STUDY_UNIT <", value, "studyUnit");
            return (Criteria) this;
        }

        public Criteria andStudyUnitLessThanOrEqualTo(String value) {
            addCriterion("STUDY_UNIT <=", value, "studyUnit");
            return (Criteria) this;
        }

        public Criteria andStudyUnitLike(String value) {
            addCriterion("STUDY_UNIT like", value, "studyUnit");
            return (Criteria) this;
        }

        public Criteria andStudyUnitNotLike(String value) {
            addCriterion("STUDY_UNIT not like", value, "studyUnit");
            return (Criteria) this;
        }

        public Criteria andStudyUnitIn(List<String> values) {
            addCriterion("STUDY_UNIT in", values, "studyUnit");
            return (Criteria) this;
        }

        public Criteria andStudyUnitNotIn(List<String> values) {
            addCriterion("STUDY_UNIT not in", values, "studyUnit");
            return (Criteria) this;
        }

        public Criteria andStudyUnitBetween(String value1, String value2) {
            addCriterion("STUDY_UNIT between", value1, value2, "studyUnit");
            return (Criteria) this;
        }

        public Criteria andStudyUnitNotBetween(String value1, String value2) {
            addCriterion("STUDY_UNIT not between", value1, value2, "studyUnit");
            return (Criteria) this;
        }

        public Criteria andStuStartTimeIsNull() {
            addCriterion("STU_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andStuStartTimeIsNotNull() {
            addCriterion("STU_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andStuStartTimeEqualTo(String value) {
            addCriterion("STU_START_TIME =", value, "stuStartTime");
            return (Criteria) this;
        }

        public Criteria andStuStartTimeNotEqualTo(String value) {
            addCriterion("STU_START_TIME <>", value, "stuStartTime");
            return (Criteria) this;
        }

        public Criteria andStuStartTimeGreaterThan(String value) {
            addCriterion("STU_START_TIME >", value, "stuStartTime");
            return (Criteria) this;
        }

        public Criteria andStuStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("STU_START_TIME >=", value, "stuStartTime");
            return (Criteria) this;
        }

        public Criteria andStuStartTimeLessThan(String value) {
            addCriterion("STU_START_TIME <", value, "stuStartTime");
            return (Criteria) this;
        }

        public Criteria andStuStartTimeLessThanOrEqualTo(String value) {
            addCriterion("STU_START_TIME <=", value, "stuStartTime");
            return (Criteria) this;
        }

        public Criteria andStuStartTimeLike(String value) {
            addCriterion("STU_START_TIME like", value, "stuStartTime");
            return (Criteria) this;
        }

        public Criteria andStuStartTimeNotLike(String value) {
            addCriterion("STU_START_TIME not like", value, "stuStartTime");
            return (Criteria) this;
        }

        public Criteria andStuStartTimeIn(List<String> values) {
            addCriterion("STU_START_TIME in", values, "stuStartTime");
            return (Criteria) this;
        }

        public Criteria andStuStartTimeNotIn(List<String> values) {
            addCriterion("STU_START_TIME not in", values, "stuStartTime");
            return (Criteria) this;
        }

        public Criteria andStuStartTimeBetween(String value1, String value2) {
            addCriterion("STU_START_TIME between", value1, value2, "stuStartTime");
            return (Criteria) this;
        }

        public Criteria andStuStartTimeNotBetween(String value1, String value2) {
            addCriterion("STU_START_TIME not between", value1, value2, "stuStartTime");
            return (Criteria) this;
        }

        public Criteria andStuEndTimeIsNull() {
            addCriterion("STU_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andStuEndTimeIsNotNull() {
            addCriterion("STU_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andStuEndTimeEqualTo(String value) {
            addCriterion("STU_END_TIME =", value, "stuEndTime");
            return (Criteria) this;
        }

        public Criteria andStuEndTimeNotEqualTo(String value) {
            addCriterion("STU_END_TIME <>", value, "stuEndTime");
            return (Criteria) this;
        }

        public Criteria andStuEndTimeGreaterThan(String value) {
            addCriterion("STU_END_TIME >", value, "stuEndTime");
            return (Criteria) this;
        }

        public Criteria andStuEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("STU_END_TIME >=", value, "stuEndTime");
            return (Criteria) this;
        }

        public Criteria andStuEndTimeLessThan(String value) {
            addCriterion("STU_END_TIME <", value, "stuEndTime");
            return (Criteria) this;
        }

        public Criteria andStuEndTimeLessThanOrEqualTo(String value) {
            addCriterion("STU_END_TIME <=", value, "stuEndTime");
            return (Criteria) this;
        }

        public Criteria andStuEndTimeLike(String value) {
            addCriterion("STU_END_TIME like", value, "stuEndTime");
            return (Criteria) this;
        }

        public Criteria andStuEndTimeNotLike(String value) {
            addCriterion("STU_END_TIME not like", value, "stuEndTime");
            return (Criteria) this;
        }

        public Criteria andStuEndTimeIn(List<String> values) {
            addCriterion("STU_END_TIME in", values, "stuEndTime");
            return (Criteria) this;
        }

        public Criteria andStuEndTimeNotIn(List<String> values) {
            addCriterion("STU_END_TIME not in", values, "stuEndTime");
            return (Criteria) this;
        }

        public Criteria andStuEndTimeBetween(String value1, String value2) {
            addCriterion("STU_END_TIME between", value1, value2, "stuEndTime");
            return (Criteria) this;
        }

        public Criteria andStuEndTimeNotBetween(String value1, String value2) {
            addCriterion("STU_END_TIME not between", value1, value2, "stuEndTime");
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

        public Criteria andUserNameIsNull() {
            addCriterion("USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("USER_NAME =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("USER_NAME <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("USER_NAME >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_NAME >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("USER_NAME <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("USER_NAME <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("USER_NAME like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("USER_NAME not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("USER_NAME in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("USER_NAME not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("USER_NAME between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("USER_NAME not between", value1, value2, "userName");
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