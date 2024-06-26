package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ResDoctorKqExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResDoctorKqExample() {
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

        public Criteria andDoctorFlowIsNull() {
            addCriterion("DOCTOR_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowIsNotNull() {
            addCriterion("DOCTOR_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowEqualTo(String value) {
            addCriterion("DOCTOR_FLOW =", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowNotEqualTo(String value) {
            addCriterion("DOCTOR_FLOW <>", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowGreaterThan(String value) {
            addCriterion("DOCTOR_FLOW >", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_FLOW >=", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowLessThan(String value) {
            addCriterion("DOCTOR_FLOW <", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_FLOW <=", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowLike(String value) {
            addCriterion("DOCTOR_FLOW like", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowNotLike(String value) {
            addCriterion("DOCTOR_FLOW not like", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowIn(List<String> values) {
            addCriterion("DOCTOR_FLOW in", values, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowNotIn(List<String> values) {
            addCriterion("DOCTOR_FLOW not in", values, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowBetween(String value1, String value2) {
            addCriterion("DOCTOR_FLOW between", value1, value2, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_FLOW not between", value1, value2, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorNameIsNull() {
            addCriterion("DOCTOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDoctorNameIsNotNull() {
            addCriterion("DOCTOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorNameEqualTo(String value) {
            addCriterion("DOCTOR_NAME =", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotEqualTo(String value) {
            addCriterion("DOCTOR_NAME <>", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameGreaterThan(String value) {
            addCriterion("DOCTOR_NAME >", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_NAME >=", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLessThan(String value) {
            addCriterion("DOCTOR_NAME <", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_NAME <=", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLike(String value) {
            addCriterion("DOCTOR_NAME like", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotLike(String value) {
            addCriterion("DOCTOR_NAME not like", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameIn(List<String> values) {
            addCriterion("DOCTOR_NAME in", values, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotIn(List<String> values) {
            addCriterion("DOCTOR_NAME not in", values, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameBetween(String value1, String value2) {
            addCriterion("DOCTOR_NAME between", value1, value2, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_NAME not between", value1, value2, "doctorName");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(String value) {
            addCriterion("START_DATE =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(String value) {
            addCriterion("START_DATE <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(String value) {
            addCriterion("START_DATE >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("START_DATE >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(String value) {
            addCriterion("START_DATE <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(String value) {
            addCriterion("START_DATE <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLike(String value) {
            addCriterion("START_DATE like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotLike(String value) {
            addCriterion("START_DATE not like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<String> values) {
            addCriterion("START_DATE in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<String> values) {
            addCriterion("START_DATE not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(String value1, String value2) {
            addCriterion("START_DATE between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(String value1, String value2) {
            addCriterion("START_DATE not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(String value) {
            addCriterion("END_DATE =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(String value) {
            addCriterion("END_DATE <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(String value) {
            addCriterion("END_DATE >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("END_DATE >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(String value) {
            addCriterion("END_DATE <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(String value) {
            addCriterion("END_DATE <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLike(String value) {
            addCriterion("END_DATE like", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotLike(String value) {
            addCriterion("END_DATE not like", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<String> values) {
            addCriterion("END_DATE in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<String> values) {
            addCriterion("END_DATE not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(String value1, String value2) {
            addCriterion("END_DATE between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(String value1, String value2) {
            addCriterion("END_DATE not between", value1, value2, "endDate");
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

        public Criteria andSchDeptFlowIsNull() {
            addCriterion("SCH_DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowIsNotNull() {
            addCriterion("SCH_DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowEqualTo(String value) {
            addCriterion("SCH_DEPT_FLOW =", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowNotEqualTo(String value) {
            addCriterion("SCH_DEPT_FLOW <>", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowGreaterThan(String value) {
            addCriterion("SCH_DEPT_FLOW >", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_DEPT_FLOW >=", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowLessThan(String value) {
            addCriterion("SCH_DEPT_FLOW <", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("SCH_DEPT_FLOW <=", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowLike(String value) {
            addCriterion("SCH_DEPT_FLOW like", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowNotLike(String value) {
            addCriterion("SCH_DEPT_FLOW not like", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowIn(List<String> values) {
            addCriterion("SCH_DEPT_FLOW in", values, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowNotIn(List<String> values) {
            addCriterion("SCH_DEPT_FLOW not in", values, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowBetween(String value1, String value2) {
            addCriterion("SCH_DEPT_FLOW between", value1, value2, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowNotBetween(String value1, String value2) {
            addCriterion("SCH_DEPT_FLOW not between", value1, value2, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameIsNull() {
            addCriterion("SCH_DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameIsNotNull() {
            addCriterion("SCH_DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameEqualTo(String value) {
            addCriterion("SCH_DEPT_NAME =", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameNotEqualTo(String value) {
            addCriterion("SCH_DEPT_NAME <>", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameGreaterThan(String value) {
            addCriterion("SCH_DEPT_NAME >", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_DEPT_NAME >=", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameLessThan(String value) {
            addCriterion("SCH_DEPT_NAME <", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameLessThanOrEqualTo(String value) {
            addCriterion("SCH_DEPT_NAME <=", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameLike(String value) {
            addCriterion("SCH_DEPT_NAME like", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameNotLike(String value) {
            addCriterion("SCH_DEPT_NAME not like", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameIn(List<String> values) {
            addCriterion("SCH_DEPT_NAME in", values, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameNotIn(List<String> values) {
            addCriterion("SCH_DEPT_NAME not in", values, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameBetween(String value1, String value2) {
            addCriterion("SCH_DEPT_NAME between", value1, value2, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameNotBetween(String value1, String value2) {
            addCriterion("SCH_DEPT_NAME not between", value1, value2, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptStartDateIsNull() {
            addCriterion("SCH_DEPT_START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSchDeptStartDateIsNotNull() {
            addCriterion("SCH_DEPT_START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSchDeptStartDateEqualTo(String value) {
            addCriterion("SCH_DEPT_START_DATE =", value, "schDeptStartDate");
            return (Criteria) this;
        }

        public Criteria andSchDeptStartDateNotEqualTo(String value) {
            addCriterion("SCH_DEPT_START_DATE <>", value, "schDeptStartDate");
            return (Criteria) this;
        }

        public Criteria andSchDeptStartDateGreaterThan(String value) {
            addCriterion("SCH_DEPT_START_DATE >", value, "schDeptStartDate");
            return (Criteria) this;
        }

        public Criteria andSchDeptStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_DEPT_START_DATE >=", value, "schDeptStartDate");
            return (Criteria) this;
        }

        public Criteria andSchDeptStartDateLessThan(String value) {
            addCriterion("SCH_DEPT_START_DATE <", value, "schDeptStartDate");
            return (Criteria) this;
        }

        public Criteria andSchDeptStartDateLessThanOrEqualTo(String value) {
            addCriterion("SCH_DEPT_START_DATE <=", value, "schDeptStartDate");
            return (Criteria) this;
        }

        public Criteria andSchDeptStartDateLike(String value) {
            addCriterion("SCH_DEPT_START_DATE like", value, "schDeptStartDate");
            return (Criteria) this;
        }

        public Criteria andSchDeptStartDateNotLike(String value) {
            addCriterion("SCH_DEPT_START_DATE not like", value, "schDeptStartDate");
            return (Criteria) this;
        }

        public Criteria andSchDeptStartDateIn(List<String> values) {
            addCriterion("SCH_DEPT_START_DATE in", values, "schDeptStartDate");
            return (Criteria) this;
        }

        public Criteria andSchDeptStartDateNotIn(List<String> values) {
            addCriterion("SCH_DEPT_START_DATE not in", values, "schDeptStartDate");
            return (Criteria) this;
        }

        public Criteria andSchDeptStartDateBetween(String value1, String value2) {
            addCriterion("SCH_DEPT_START_DATE between", value1, value2, "schDeptStartDate");
            return (Criteria) this;
        }

        public Criteria andSchDeptStartDateNotBetween(String value1, String value2) {
            addCriterion("SCH_DEPT_START_DATE not between", value1, value2, "schDeptStartDate");
            return (Criteria) this;
        }

        public Criteria andSchDeptEndDateIsNull() {
            addCriterion("SCH_DEPT_END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSchDeptEndDateIsNotNull() {
            addCriterion("SCH_DEPT_END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSchDeptEndDateEqualTo(String value) {
            addCriterion("SCH_DEPT_END_DATE =", value, "schDeptEndDate");
            return (Criteria) this;
        }

        public Criteria andSchDeptEndDateNotEqualTo(String value) {
            addCriterion("SCH_DEPT_END_DATE <>", value, "schDeptEndDate");
            return (Criteria) this;
        }

        public Criteria andSchDeptEndDateGreaterThan(String value) {
            addCriterion("SCH_DEPT_END_DATE >", value, "schDeptEndDate");
            return (Criteria) this;
        }

        public Criteria andSchDeptEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_DEPT_END_DATE >=", value, "schDeptEndDate");
            return (Criteria) this;
        }

        public Criteria andSchDeptEndDateLessThan(String value) {
            addCriterion("SCH_DEPT_END_DATE <", value, "schDeptEndDate");
            return (Criteria) this;
        }

        public Criteria andSchDeptEndDateLessThanOrEqualTo(String value) {
            addCriterion("SCH_DEPT_END_DATE <=", value, "schDeptEndDate");
            return (Criteria) this;
        }

        public Criteria andSchDeptEndDateLike(String value) {
            addCriterion("SCH_DEPT_END_DATE like", value, "schDeptEndDate");
            return (Criteria) this;
        }

        public Criteria andSchDeptEndDateNotLike(String value) {
            addCriterion("SCH_DEPT_END_DATE not like", value, "schDeptEndDate");
            return (Criteria) this;
        }

        public Criteria andSchDeptEndDateIn(List<String> values) {
            addCriterion("SCH_DEPT_END_DATE in", values, "schDeptEndDate");
            return (Criteria) this;
        }

        public Criteria andSchDeptEndDateNotIn(List<String> values) {
            addCriterion("SCH_DEPT_END_DATE not in", values, "schDeptEndDate");
            return (Criteria) this;
        }

        public Criteria andSchDeptEndDateBetween(String value1, String value2) {
            addCriterion("SCH_DEPT_END_DATE between", value1, value2, "schDeptEndDate");
            return (Criteria) this;
        }

        public Criteria andSchDeptEndDateNotBetween(String value1, String value2) {
            addCriterion("SCH_DEPT_END_DATE not between", value1, value2, "schDeptEndDate");
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

        public Criteria andOrgNameIsNull() {
            addCriterion("ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNotNull() {
            addCriterion("ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEqualTo(String value) {
            addCriterion("ORG_NAME =", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotEqualTo(String value) {
            addCriterion("ORG_NAME <>", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThan(String value) {
            addCriterion("ORG_NAME >", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_NAME >=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThan(String value) {
            addCriterion("ORG_NAME <", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_NAME <=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLike(String value) {
            addCriterion("ORG_NAME like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotLike(String value) {
            addCriterion("ORG_NAME not like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIn(List<String> values) {
            addCriterion("ORG_NAME in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotIn(List<String> values) {
            addCriterion("ORG_NAME not in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameBetween(String value1, String value2) {
            addCriterion("ORG_NAME between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotBetween(String value1, String value2) {
            addCriterion("ORG_NAME not between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andKqTypeIdIsNull() {
            addCriterion("KQ_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andKqTypeIdIsNotNull() {
            addCriterion("KQ_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andKqTypeIdEqualTo(String value) {
            addCriterion("KQ_TYPE_ID =", value, "kqTypeId");
            return (Criteria) this;
        }

        public Criteria andKqTypeIdNotEqualTo(String value) {
            addCriterion("KQ_TYPE_ID <>", value, "kqTypeId");
            return (Criteria) this;
        }

        public Criteria andKqTypeIdGreaterThan(String value) {
            addCriterion("KQ_TYPE_ID >", value, "kqTypeId");
            return (Criteria) this;
        }

        public Criteria andKqTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("KQ_TYPE_ID >=", value, "kqTypeId");
            return (Criteria) this;
        }

        public Criteria andKqTypeIdLessThan(String value) {
            addCriterion("KQ_TYPE_ID <", value, "kqTypeId");
            return (Criteria) this;
        }

        public Criteria andKqTypeIdLessThanOrEqualTo(String value) {
            addCriterion("KQ_TYPE_ID <=", value, "kqTypeId");
            return (Criteria) this;
        }

        public Criteria andKqTypeIdLike(String value) {
            addCriterion("KQ_TYPE_ID like", value, "kqTypeId");
            return (Criteria) this;
        }

        public Criteria andKqTypeIdNotLike(String value) {
            addCriterion("KQ_TYPE_ID not like", value, "kqTypeId");
            return (Criteria) this;
        }

        public Criteria andKqTypeIdIn(List<String> values) {
            addCriterion("KQ_TYPE_ID in", values, "kqTypeId");
            return (Criteria) this;
        }

        public Criteria andKqTypeIdNotIn(List<String> values) {
            addCriterion("KQ_TYPE_ID not in", values, "kqTypeId");
            return (Criteria) this;
        }

        public Criteria andKqTypeIdBetween(String value1, String value2) {
            addCriterion("KQ_TYPE_ID between", value1, value2, "kqTypeId");
            return (Criteria) this;
        }

        public Criteria andKqTypeIdNotBetween(String value1, String value2) {
            addCriterion("KQ_TYPE_ID not between", value1, value2, "kqTypeId");
            return (Criteria) this;
        }

        public Criteria andKqTypeNameIsNull() {
            addCriterion("KQ_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andKqTypeNameIsNotNull() {
            addCriterion("KQ_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andKqTypeNameEqualTo(String value) {
            addCriterion("KQ_TYPE_NAME =", value, "kqTypeName");
            return (Criteria) this;
        }

        public Criteria andKqTypeNameNotEqualTo(String value) {
            addCriterion("KQ_TYPE_NAME <>", value, "kqTypeName");
            return (Criteria) this;
        }

        public Criteria andKqTypeNameGreaterThan(String value) {
            addCriterion("KQ_TYPE_NAME >", value, "kqTypeName");
            return (Criteria) this;
        }

        public Criteria andKqTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("KQ_TYPE_NAME >=", value, "kqTypeName");
            return (Criteria) this;
        }

        public Criteria andKqTypeNameLessThan(String value) {
            addCriterion("KQ_TYPE_NAME <", value, "kqTypeName");
            return (Criteria) this;
        }

        public Criteria andKqTypeNameLessThanOrEqualTo(String value) {
            addCriterion("KQ_TYPE_NAME <=", value, "kqTypeName");
            return (Criteria) this;
        }

        public Criteria andKqTypeNameLike(String value) {
            addCriterion("KQ_TYPE_NAME like", value, "kqTypeName");
            return (Criteria) this;
        }

        public Criteria andKqTypeNameNotLike(String value) {
            addCriterion("KQ_TYPE_NAME not like", value, "kqTypeName");
            return (Criteria) this;
        }

        public Criteria andKqTypeNameIn(List<String> values) {
            addCriterion("KQ_TYPE_NAME in", values, "kqTypeName");
            return (Criteria) this;
        }

        public Criteria andKqTypeNameNotIn(List<String> values) {
            addCriterion("KQ_TYPE_NAME not in", values, "kqTypeName");
            return (Criteria) this;
        }

        public Criteria andKqTypeNameBetween(String value1, String value2) {
            addCriterion("KQ_TYPE_NAME between", value1, value2, "kqTypeName");
            return (Criteria) this;
        }

        public Criteria andKqTypeNameNotBetween(String value1, String value2) {
            addCriterion("KQ_TYPE_NAME not between", value1, value2, "kqTypeName");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNull() {
            addCriterion("TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(String value) {
            addCriterion("TYPE_ID =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(String value) {
            addCriterion("TYPE_ID <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(String value) {
            addCriterion("TYPE_ID >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE_ID >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(String value) {
            addCriterion("TYPE_ID <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(String value) {
            addCriterion("TYPE_ID <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLike(String value) {
            addCriterion("TYPE_ID like", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotLike(String value) {
            addCriterion("TYPE_ID not like", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<String> values) {
            addCriterion("TYPE_ID in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<String> values) {
            addCriterion("TYPE_ID not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(String value1, String value2) {
            addCriterion("TYPE_ID between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(String value1, String value2) {
            addCriterion("TYPE_ID not between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNull() {
            addCriterion("TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNotNull() {
            addCriterion("TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTypeNameEqualTo(String value) {
            addCriterion("TYPE_NAME =", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotEqualTo(String value) {
            addCriterion("TYPE_NAME <>", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThan(String value) {
            addCriterion("TYPE_NAME >", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE_NAME >=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThan(String value) {
            addCriterion("TYPE_NAME <", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThanOrEqualTo(String value) {
            addCriterion("TYPE_NAME <=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLike(String value) {
            addCriterion("TYPE_NAME like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotLike(String value) {
            addCriterion("TYPE_NAME not like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameIn(List<String> values) {
            addCriterion("TYPE_NAME in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotIn(List<String> values) {
            addCriterion("TYPE_NAME not in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameBetween(String value1, String value2) {
            addCriterion("TYPE_NAME between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotBetween(String value1, String value2) {
            addCriterion("TYPE_NAME not between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andDoctorRemarksIsNull() {
            addCriterion("DOCTOR_REMARKS is null");
            return (Criteria) this;
        }

        public Criteria andDoctorRemarksIsNotNull() {
            addCriterion("DOCTOR_REMARKS is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorRemarksEqualTo(String value) {
            addCriterion("DOCTOR_REMARKS =", value, "doctorRemarks");
            return (Criteria) this;
        }

        public Criteria andDoctorRemarksNotEqualTo(String value) {
            addCriterion("DOCTOR_REMARKS <>", value, "doctorRemarks");
            return (Criteria) this;
        }

        public Criteria andDoctorRemarksGreaterThan(String value) {
            addCriterion("DOCTOR_REMARKS >", value, "doctorRemarks");
            return (Criteria) this;
        }

        public Criteria andDoctorRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_REMARKS >=", value, "doctorRemarks");
            return (Criteria) this;
        }

        public Criteria andDoctorRemarksLessThan(String value) {
            addCriterion("DOCTOR_REMARKS <", value, "doctorRemarks");
            return (Criteria) this;
        }

        public Criteria andDoctorRemarksLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_REMARKS <=", value, "doctorRemarks");
            return (Criteria) this;
        }

        public Criteria andDoctorRemarksLike(String value) {
            addCriterion("DOCTOR_REMARKS like", value, "doctorRemarks");
            return (Criteria) this;
        }

        public Criteria andDoctorRemarksNotLike(String value) {
            addCriterion("DOCTOR_REMARKS not like", value, "doctorRemarks");
            return (Criteria) this;
        }

        public Criteria andDoctorRemarksIn(List<String> values) {
            addCriterion("DOCTOR_REMARKS in", values, "doctorRemarks");
            return (Criteria) this;
        }

        public Criteria andDoctorRemarksNotIn(List<String> values) {
            addCriterion("DOCTOR_REMARKS not in", values, "doctorRemarks");
            return (Criteria) this;
        }

        public Criteria andDoctorRemarksBetween(String value1, String value2) {
            addCriterion("DOCTOR_REMARKS between", value1, value2, "doctorRemarks");
            return (Criteria) this;
        }

        public Criteria andDoctorRemarksNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_REMARKS not between", value1, value2, "doctorRemarks");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdIsNull() {
            addCriterion("AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdIsNotNull() {
            addCriterion("AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID =", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID <>", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdGreaterThan(String value) {
            addCriterion("AUDIT_STATUS_ID >", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID >=", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdLessThan(String value) {
            addCriterion("AUDIT_STATUS_ID <", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID <=", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdLike(String value) {
            addCriterion("AUDIT_STATUS_ID like", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotLike(String value) {
            addCriterion("AUDIT_STATUS_ID not like", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdIn(List<String> values) {
            addCriterion("AUDIT_STATUS_ID in", values, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotIn(List<String> values) {
            addCriterion("AUDIT_STATUS_ID not in", values, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_ID between", value1, value2, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_ID not between", value1, value2, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameIsNull() {
            addCriterion("AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameIsNotNull() {
            addCriterion("AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME =", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME <>", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameGreaterThan(String value) {
            addCriterion("AUDIT_STATUS_NAME >", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME >=", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameLessThan(String value) {
            addCriterion("AUDIT_STATUS_NAME <", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME <=", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameLike(String value) {
            addCriterion("AUDIT_STATUS_NAME like", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotLike(String value) {
            addCriterion("AUDIT_STATUS_NAME not like", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameIn(List<String> values) {
            addCriterion("AUDIT_STATUS_NAME in", values, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotIn(List<String> values) {
            addCriterion("AUDIT_STATUS_NAME not in", values, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_NAME between", value1, value2, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_NAME not between", value1, value2, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowIsNull() {
            addCriterion("TEACHER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowIsNotNull() {
            addCriterion("TEACHER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowEqualTo(String value) {
            addCriterion("TEACHER_FLOW =", value, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowNotEqualTo(String value) {
            addCriterion("TEACHER_FLOW <>", value, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowGreaterThan(String value) {
            addCriterion("TEACHER_FLOW >", value, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_FLOW >=", value, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowLessThan(String value) {
            addCriterion("TEACHER_FLOW <", value, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_FLOW <=", value, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowLike(String value) {
            addCriterion("TEACHER_FLOW like", value, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowNotLike(String value) {
            addCriterion("TEACHER_FLOW not like", value, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowIn(List<String> values) {
            addCriterion("TEACHER_FLOW in", values, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowNotIn(List<String> values) {
            addCriterion("TEACHER_FLOW not in", values, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowBetween(String value1, String value2) {
            addCriterion("TEACHER_FLOW between", value1, value2, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowNotBetween(String value1, String value2) {
            addCriterion("TEACHER_FLOW not between", value1, value2, "teacherFlow");
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

        public Criteria andHeadFlowIsNull() {
            addCriterion("HEAD_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andHeadFlowIsNotNull() {
            addCriterion("HEAD_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andHeadFlowEqualTo(String value) {
            addCriterion("HEAD_FLOW =", value, "headFlow");
            return (Criteria) this;
        }

        public Criteria andHeadFlowNotEqualTo(String value) {
            addCriterion("HEAD_FLOW <>", value, "headFlow");
            return (Criteria) this;
        }

        public Criteria andHeadFlowGreaterThan(String value) {
            addCriterion("HEAD_FLOW >", value, "headFlow");
            return (Criteria) this;
        }

        public Criteria andHeadFlowGreaterThanOrEqualTo(String value) {
            addCriterion("HEAD_FLOW >=", value, "headFlow");
            return (Criteria) this;
        }

        public Criteria andHeadFlowLessThan(String value) {
            addCriterion("HEAD_FLOW <", value, "headFlow");
            return (Criteria) this;
        }

        public Criteria andHeadFlowLessThanOrEqualTo(String value) {
            addCriterion("HEAD_FLOW <=", value, "headFlow");
            return (Criteria) this;
        }

        public Criteria andHeadFlowLike(String value) {
            addCriterion("HEAD_FLOW like", value, "headFlow");
            return (Criteria) this;
        }

        public Criteria andHeadFlowNotLike(String value) {
            addCriterion("HEAD_FLOW not like", value, "headFlow");
            return (Criteria) this;
        }

        public Criteria andHeadFlowIn(List<String> values) {
            addCriterion("HEAD_FLOW in", values, "headFlow");
            return (Criteria) this;
        }

        public Criteria andHeadFlowNotIn(List<String> values) {
            addCriterion("HEAD_FLOW not in", values, "headFlow");
            return (Criteria) this;
        }

        public Criteria andHeadFlowBetween(String value1, String value2) {
            addCriterion("HEAD_FLOW between", value1, value2, "headFlow");
            return (Criteria) this;
        }

        public Criteria andHeadFlowNotBetween(String value1, String value2) {
            addCriterion("HEAD_FLOW not between", value1, value2, "headFlow");
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

        public Criteria andTutorFlowIsNull() {
            addCriterion("TUTOR_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andTutorFlowIsNotNull() {
            addCriterion("TUTOR_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andTutorFlowEqualTo(String value) {
            addCriterion("TUTOR_FLOW =", value, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowNotEqualTo(String value) {
            addCriterion("TUTOR_FLOW <>", value, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowGreaterThan(String value) {
            addCriterion("TUTOR_FLOW >", value, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_FLOW >=", value, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowLessThan(String value) {
            addCriterion("TUTOR_FLOW <", value, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_FLOW <=", value, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowLike(String value) {
            addCriterion("TUTOR_FLOW like", value, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowNotLike(String value) {
            addCriterion("TUTOR_FLOW not like", value, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowIn(List<String> values) {
            addCriterion("TUTOR_FLOW in", values, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowNotIn(List<String> values) {
            addCriterion("TUTOR_FLOW not in", values, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowBetween(String value1, String value2) {
            addCriterion("TUTOR_FLOW between", value1, value2, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowNotBetween(String value1, String value2) {
            addCriterion("TUTOR_FLOW not between", value1, value2, "tutorFlow");
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

        public Criteria andManagerFlowIsNull() {
            addCriterion("MANAGER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andManagerFlowIsNotNull() {
            addCriterion("MANAGER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andManagerFlowEqualTo(String value) {
            addCriterion("MANAGER_FLOW =", value, "managerFlow");
            return (Criteria) this;
        }

        public Criteria andManagerFlowNotEqualTo(String value) {
            addCriterion("MANAGER_FLOW <>", value, "managerFlow");
            return (Criteria) this;
        }

        public Criteria andManagerFlowGreaterThan(String value) {
            addCriterion("MANAGER_FLOW >", value, "managerFlow");
            return (Criteria) this;
        }

        public Criteria andManagerFlowGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGER_FLOW >=", value, "managerFlow");
            return (Criteria) this;
        }

        public Criteria andManagerFlowLessThan(String value) {
            addCriterion("MANAGER_FLOW <", value, "managerFlow");
            return (Criteria) this;
        }

        public Criteria andManagerFlowLessThanOrEqualTo(String value) {
            addCriterion("MANAGER_FLOW <=", value, "managerFlow");
            return (Criteria) this;
        }

        public Criteria andManagerFlowLike(String value) {
            addCriterion("MANAGER_FLOW like", value, "managerFlow");
            return (Criteria) this;
        }

        public Criteria andManagerFlowNotLike(String value) {
            addCriterion("MANAGER_FLOW not like", value, "managerFlow");
            return (Criteria) this;
        }

        public Criteria andManagerFlowIn(List<String> values) {
            addCriterion("MANAGER_FLOW in", values, "managerFlow");
            return (Criteria) this;
        }

        public Criteria andManagerFlowNotIn(List<String> values) {
            addCriterion("MANAGER_FLOW not in", values, "managerFlow");
            return (Criteria) this;
        }

        public Criteria andManagerFlowBetween(String value1, String value2) {
            addCriterion("MANAGER_FLOW between", value1, value2, "managerFlow");
            return (Criteria) this;
        }

        public Criteria andManagerFlowNotBetween(String value1, String value2) {
            addCriterion("MANAGER_FLOW not between", value1, value2, "managerFlow");
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

        public Criteria andTeacherAgreeFlagIsNull() {
            addCriterion("TEACHER_AGREE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagIsNotNull() {
            addCriterion("TEACHER_AGREE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagEqualTo(String value) {
            addCriterion("TEACHER_AGREE_FLAG =", value, "teacherAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagNotEqualTo(String value) {
            addCriterion("TEACHER_AGREE_FLAG <>", value, "teacherAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagGreaterThan(String value) {
            addCriterion("TEACHER_AGREE_FLAG >", value, "teacherAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_AGREE_FLAG >=", value, "teacherAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagLessThan(String value) {
            addCriterion("TEACHER_AGREE_FLAG <", value, "teacherAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_AGREE_FLAG <=", value, "teacherAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagLike(String value) {
            addCriterion("TEACHER_AGREE_FLAG like", value, "teacherAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagNotLike(String value) {
            addCriterion("TEACHER_AGREE_FLAG not like", value, "teacherAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagIn(List<String> values) {
            addCriterion("TEACHER_AGREE_FLAG in", values, "teacherAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagNotIn(List<String> values) {
            addCriterion("TEACHER_AGREE_FLAG not in", values, "teacherAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagBetween(String value1, String value2) {
            addCriterion("TEACHER_AGREE_FLAG between", value1, value2, "teacherAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagNotBetween(String value1, String value2) {
            addCriterion("TEACHER_AGREE_FLAG not between", value1, value2, "teacherAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeIsNull() {
            addCriterion("TEACHER_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeIsNotNull() {
            addCriterion("TEACHER_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeEqualTo(String value) {
            addCriterion("TEACHER_AUDIT_TIME =", value, "teacherAuditTime");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeNotEqualTo(String value) {
            addCriterion("TEACHER_AUDIT_TIME <>", value, "teacherAuditTime");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeGreaterThan(String value) {
            addCriterion("TEACHER_AUDIT_TIME >", value, "teacherAuditTime");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_AUDIT_TIME >=", value, "teacherAuditTime");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeLessThan(String value) {
            addCriterion("TEACHER_AUDIT_TIME <", value, "teacherAuditTime");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_AUDIT_TIME <=", value, "teacherAuditTime");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeLike(String value) {
            addCriterion("TEACHER_AUDIT_TIME like", value, "teacherAuditTime");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeNotLike(String value) {
            addCriterion("TEACHER_AUDIT_TIME not like", value, "teacherAuditTime");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeIn(List<String> values) {
            addCriterion("TEACHER_AUDIT_TIME in", values, "teacherAuditTime");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeNotIn(List<String> values) {
            addCriterion("TEACHER_AUDIT_TIME not in", values, "teacherAuditTime");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeBetween(String value1, String value2) {
            addCriterion("TEACHER_AUDIT_TIME between", value1, value2, "teacherAuditTime");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeNotBetween(String value1, String value2) {
            addCriterion("TEACHER_AUDIT_TIME not between", value1, value2, "teacherAuditTime");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoIsNull() {
            addCriterion("TEACHER_AUDIT_INFO is null");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoIsNotNull() {
            addCriterion("TEACHER_AUDIT_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoEqualTo(String value) {
            addCriterion("TEACHER_AUDIT_INFO =", value, "teacherAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoNotEqualTo(String value) {
            addCriterion("TEACHER_AUDIT_INFO <>", value, "teacherAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoGreaterThan(String value) {
            addCriterion("TEACHER_AUDIT_INFO >", value, "teacherAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_AUDIT_INFO >=", value, "teacherAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoLessThan(String value) {
            addCriterion("TEACHER_AUDIT_INFO <", value, "teacherAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_AUDIT_INFO <=", value, "teacherAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoLike(String value) {
            addCriterion("TEACHER_AUDIT_INFO like", value, "teacherAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoNotLike(String value) {
            addCriterion("TEACHER_AUDIT_INFO not like", value, "teacherAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoIn(List<String> values) {
            addCriterion("TEACHER_AUDIT_INFO in", values, "teacherAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoNotIn(List<String> values) {
            addCriterion("TEACHER_AUDIT_INFO not in", values, "teacherAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoBetween(String value1, String value2) {
            addCriterion("TEACHER_AUDIT_INFO between", value1, value2, "teacherAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoNotBetween(String value1, String value2) {
            addCriterion("TEACHER_AUDIT_INFO not between", value1, value2, "teacherAuditInfo");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagIsNull() {
            addCriterion("HEAD_AGREE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagIsNotNull() {
            addCriterion("HEAD_AGREE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagEqualTo(String value) {
            addCriterion("HEAD_AGREE_FLAG =", value, "headAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagNotEqualTo(String value) {
            addCriterion("HEAD_AGREE_FLAG <>", value, "headAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagGreaterThan(String value) {
            addCriterion("HEAD_AGREE_FLAG >", value, "headAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagGreaterThanOrEqualTo(String value) {
            addCriterion("HEAD_AGREE_FLAG >=", value, "headAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagLessThan(String value) {
            addCriterion("HEAD_AGREE_FLAG <", value, "headAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagLessThanOrEqualTo(String value) {
            addCriterion("HEAD_AGREE_FLAG <=", value, "headAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagLike(String value) {
            addCriterion("HEAD_AGREE_FLAG like", value, "headAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagNotLike(String value) {
            addCriterion("HEAD_AGREE_FLAG not like", value, "headAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagIn(List<String> values) {
            addCriterion("HEAD_AGREE_FLAG in", values, "headAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagNotIn(List<String> values) {
            addCriterion("HEAD_AGREE_FLAG not in", values, "headAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagBetween(String value1, String value2) {
            addCriterion("HEAD_AGREE_FLAG between", value1, value2, "headAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagNotBetween(String value1, String value2) {
            addCriterion("HEAD_AGREE_FLAG not between", value1, value2, "headAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeIsNull() {
            addCriterion("HEAD_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeIsNotNull() {
            addCriterion("HEAD_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeEqualTo(String value) {
            addCriterion("HEAD_AUDIT_TIME =", value, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeNotEqualTo(String value) {
            addCriterion("HEAD_AUDIT_TIME <>", value, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeGreaterThan(String value) {
            addCriterion("HEAD_AUDIT_TIME >", value, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("HEAD_AUDIT_TIME >=", value, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeLessThan(String value) {
            addCriterion("HEAD_AUDIT_TIME <", value, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("HEAD_AUDIT_TIME <=", value, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeLike(String value) {
            addCriterion("HEAD_AUDIT_TIME like", value, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeNotLike(String value) {
            addCriterion("HEAD_AUDIT_TIME not like", value, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeIn(List<String> values) {
            addCriterion("HEAD_AUDIT_TIME in", values, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeNotIn(List<String> values) {
            addCriterion("HEAD_AUDIT_TIME not in", values, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeBetween(String value1, String value2) {
            addCriterion("HEAD_AUDIT_TIME between", value1, value2, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeNotBetween(String value1, String value2) {
            addCriterion("HEAD_AUDIT_TIME not between", value1, value2, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoIsNull() {
            addCriterion("HEAD_AUDIT_INFO is null");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoIsNotNull() {
            addCriterion("HEAD_AUDIT_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoEqualTo(String value) {
            addCriterion("HEAD_AUDIT_INFO =", value, "headAuditInfo");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoNotEqualTo(String value) {
            addCriterion("HEAD_AUDIT_INFO <>", value, "headAuditInfo");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoGreaterThan(String value) {
            addCriterion("HEAD_AUDIT_INFO >", value, "headAuditInfo");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoGreaterThanOrEqualTo(String value) {
            addCriterion("HEAD_AUDIT_INFO >=", value, "headAuditInfo");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoLessThan(String value) {
            addCriterion("HEAD_AUDIT_INFO <", value, "headAuditInfo");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoLessThanOrEqualTo(String value) {
            addCriterion("HEAD_AUDIT_INFO <=", value, "headAuditInfo");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoLike(String value) {
            addCriterion("HEAD_AUDIT_INFO like", value, "headAuditInfo");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoNotLike(String value) {
            addCriterion("HEAD_AUDIT_INFO not like", value, "headAuditInfo");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoIn(List<String> values) {
            addCriterion("HEAD_AUDIT_INFO in", values, "headAuditInfo");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoNotIn(List<String> values) {
            addCriterion("HEAD_AUDIT_INFO not in", values, "headAuditInfo");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoBetween(String value1, String value2) {
            addCriterion("HEAD_AUDIT_INFO between", value1, value2, "headAuditInfo");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoNotBetween(String value1, String value2) {
            addCriterion("HEAD_AUDIT_INFO not between", value1, value2, "headAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTutorAgreeFlagIsNull() {
            addCriterion("TUTOR_AGREE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andTutorAgreeFlagIsNotNull() {
            addCriterion("TUTOR_AGREE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andTutorAgreeFlagEqualTo(String value) {
            addCriterion("TUTOR_AGREE_FLAG =", value, "tutorAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTutorAgreeFlagNotEqualTo(String value) {
            addCriterion("TUTOR_AGREE_FLAG <>", value, "tutorAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTutorAgreeFlagGreaterThan(String value) {
            addCriterion("TUTOR_AGREE_FLAG >", value, "tutorAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTutorAgreeFlagGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_AGREE_FLAG >=", value, "tutorAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTutorAgreeFlagLessThan(String value) {
            addCriterion("TUTOR_AGREE_FLAG <", value, "tutorAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTutorAgreeFlagLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_AGREE_FLAG <=", value, "tutorAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTutorAgreeFlagLike(String value) {
            addCriterion("TUTOR_AGREE_FLAG like", value, "tutorAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTutorAgreeFlagNotLike(String value) {
            addCriterion("TUTOR_AGREE_FLAG not like", value, "tutorAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTutorAgreeFlagIn(List<String> values) {
            addCriterion("TUTOR_AGREE_FLAG in", values, "tutorAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTutorAgreeFlagNotIn(List<String> values) {
            addCriterion("TUTOR_AGREE_FLAG not in", values, "tutorAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTutorAgreeFlagBetween(String value1, String value2) {
            addCriterion("TUTOR_AGREE_FLAG between", value1, value2, "tutorAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTutorAgreeFlagNotBetween(String value1, String value2) {
            addCriterion("TUTOR_AGREE_FLAG not between", value1, value2, "tutorAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeIsNull() {
            addCriterion("TUTOR_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeIsNotNull() {
            addCriterion("TUTOR_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_TIME =", value, "tutorAuditTime");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeNotEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_TIME <>", value, "tutorAuditTime");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeGreaterThan(String value) {
            addCriterion("TUTOR_AUDIT_TIME >", value, "tutorAuditTime");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_TIME >=", value, "tutorAuditTime");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeLessThan(String value) {
            addCriterion("TUTOR_AUDIT_TIME <", value, "tutorAuditTime");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_TIME <=", value, "tutorAuditTime");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeLike(String value) {
            addCriterion("TUTOR_AUDIT_TIME like", value, "tutorAuditTime");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeNotLike(String value) {
            addCriterion("TUTOR_AUDIT_TIME not like", value, "tutorAuditTime");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeIn(List<String> values) {
            addCriterion("TUTOR_AUDIT_TIME in", values, "tutorAuditTime");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeNotIn(List<String> values) {
            addCriterion("TUTOR_AUDIT_TIME not in", values, "tutorAuditTime");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeBetween(String value1, String value2) {
            addCriterion("TUTOR_AUDIT_TIME between", value1, value2, "tutorAuditTime");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeNotBetween(String value1, String value2) {
            addCriterion("TUTOR_AUDIT_TIME not between", value1, value2, "tutorAuditTime");
            return (Criteria) this;
        }

        public Criteria andTutorAuditInfoIsNull() {
            addCriterion("TUTOR_AUDIT_INFO is null");
            return (Criteria) this;
        }

        public Criteria andTutorAuditInfoIsNotNull() {
            addCriterion("TUTOR_AUDIT_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andTutorAuditInfoEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_INFO =", value, "tutorAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTutorAuditInfoNotEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_INFO <>", value, "tutorAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTutorAuditInfoGreaterThan(String value) {
            addCriterion("TUTOR_AUDIT_INFO >", value, "tutorAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTutorAuditInfoGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_INFO >=", value, "tutorAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTutorAuditInfoLessThan(String value) {
            addCriterion("TUTOR_AUDIT_INFO <", value, "tutorAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTutorAuditInfoLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_INFO <=", value, "tutorAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTutorAuditInfoLike(String value) {
            addCriterion("TUTOR_AUDIT_INFO like", value, "tutorAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTutorAuditInfoNotLike(String value) {
            addCriterion("TUTOR_AUDIT_INFO not like", value, "tutorAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTutorAuditInfoIn(List<String> values) {
            addCriterion("TUTOR_AUDIT_INFO in", values, "tutorAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTutorAuditInfoNotIn(List<String> values) {
            addCriterion("TUTOR_AUDIT_INFO not in", values, "tutorAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTutorAuditInfoBetween(String value1, String value2) {
            addCriterion("TUTOR_AUDIT_INFO between", value1, value2, "tutorAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTutorAuditInfoNotBetween(String value1, String value2) {
            addCriterion("TUTOR_AUDIT_INFO not between", value1, value2, "tutorAuditInfo");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagIsNull() {
            addCriterion("MANAGER_AGREE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagIsNotNull() {
            addCriterion("MANAGER_AGREE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagEqualTo(String value) {
            addCriterion("MANAGER_AGREE_FLAG =", value, "managerAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagNotEqualTo(String value) {
            addCriterion("MANAGER_AGREE_FLAG <>", value, "managerAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagGreaterThan(String value) {
            addCriterion("MANAGER_AGREE_FLAG >", value, "managerAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGER_AGREE_FLAG >=", value, "managerAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagLessThan(String value) {
            addCriterion("MANAGER_AGREE_FLAG <", value, "managerAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagLessThanOrEqualTo(String value) {
            addCriterion("MANAGER_AGREE_FLAG <=", value, "managerAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagLike(String value) {
            addCriterion("MANAGER_AGREE_FLAG like", value, "managerAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagNotLike(String value) {
            addCriterion("MANAGER_AGREE_FLAG not like", value, "managerAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagIn(List<String> values) {
            addCriterion("MANAGER_AGREE_FLAG in", values, "managerAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagNotIn(List<String> values) {
            addCriterion("MANAGER_AGREE_FLAG not in", values, "managerAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagBetween(String value1, String value2) {
            addCriterion("MANAGER_AGREE_FLAG between", value1, value2, "managerAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagNotBetween(String value1, String value2) {
            addCriterion("MANAGER_AGREE_FLAG not between", value1, value2, "managerAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeIsNull() {
            addCriterion("MANAGER_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeIsNotNull() {
            addCriterion("MANAGER_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_TIME =", value, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeNotEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_TIME <>", value, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeGreaterThan(String value) {
            addCriterion("MANAGER_AUDIT_TIME >", value, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_TIME >=", value, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeLessThan(String value) {
            addCriterion("MANAGER_AUDIT_TIME <", value, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_TIME <=", value, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeLike(String value) {
            addCriterion("MANAGER_AUDIT_TIME like", value, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeNotLike(String value) {
            addCriterion("MANAGER_AUDIT_TIME not like", value, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeIn(List<String> values) {
            addCriterion("MANAGER_AUDIT_TIME in", values, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeNotIn(List<String> values) {
            addCriterion("MANAGER_AUDIT_TIME not in", values, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeBetween(String value1, String value2) {
            addCriterion("MANAGER_AUDIT_TIME between", value1, value2, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeNotBetween(String value1, String value2) {
            addCriterion("MANAGER_AUDIT_TIME not between", value1, value2, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoIsNull() {
            addCriterion("MANAGER_AUDIT_INFO is null");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoIsNotNull() {
            addCriterion("MANAGER_AUDIT_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_INFO =", value, "managerAuditInfo");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoNotEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_INFO <>", value, "managerAuditInfo");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoGreaterThan(String value) {
            addCriterion("MANAGER_AUDIT_INFO >", value, "managerAuditInfo");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_INFO >=", value, "managerAuditInfo");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoLessThan(String value) {
            addCriterion("MANAGER_AUDIT_INFO <", value, "managerAuditInfo");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoLessThanOrEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_INFO <=", value, "managerAuditInfo");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoLike(String value) {
            addCriterion("MANAGER_AUDIT_INFO like", value, "managerAuditInfo");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoNotLike(String value) {
            addCriterion("MANAGER_AUDIT_INFO not like", value, "managerAuditInfo");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoIn(List<String> values) {
            addCriterion("MANAGER_AUDIT_INFO in", values, "managerAuditInfo");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoNotIn(List<String> values) {
            addCriterion("MANAGER_AUDIT_INFO not in", values, "managerAuditInfo");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoBetween(String value1, String value2) {
            addCriterion("MANAGER_AUDIT_INFO between", value1, value2, "managerAuditInfo");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoNotBetween(String value1, String value2) {
            addCriterion("MANAGER_AUDIT_INFO not between", value1, value2, "managerAuditInfo");
            return (Criteria) this;
        }

        public Criteria andProcessFlowIsNull() {
            addCriterion("PROCESS_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andProcessFlowIsNotNull() {
            addCriterion("PROCESS_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andProcessFlowEqualTo(String value) {
            addCriterion("PROCESS_FLOW =", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowNotEqualTo(String value) {
            addCriterion("PROCESS_FLOW <>", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowGreaterThan(String value) {
            addCriterion("PROCESS_FLOW >", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PROCESS_FLOW >=", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowLessThan(String value) {
            addCriterion("PROCESS_FLOW <", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowLessThanOrEqualTo(String value) {
            addCriterion("PROCESS_FLOW <=", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowLike(String value) {
            addCriterion("PROCESS_FLOW like", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowNotLike(String value) {
            addCriterion("PROCESS_FLOW not like", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowIn(List<String> values) {
            addCriterion("PROCESS_FLOW in", values, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowNotIn(List<String> values) {
            addCriterion("PROCESS_FLOW not in", values, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowBetween(String value1, String value2) {
            addCriterion("PROCESS_FLOW between", value1, value2, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowNotBetween(String value1, String value2) {
            addCriterion("PROCESS_FLOW not between", value1, value2, "processFlow");
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

        public Criteria andSessionNumberNowIsNull() {
            addCriterion("SESSION_NUMBER_NOW is null");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNowIsNotNull() {
            addCriterion("SESSION_NUMBER_NOW is not null");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNowEqualTo(String value) {
            addCriterion("SESSION_NUMBER_NOW =", value, "sessionNumberNow");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNowNotEqualTo(String value) {
            addCriterion("SESSION_NUMBER_NOW <>", value, "sessionNumberNow");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNowGreaterThan(String value) {
            addCriterion("SESSION_NUMBER_NOW >", value, "sessionNumberNow");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNowGreaterThanOrEqualTo(String value) {
            addCriterion("SESSION_NUMBER_NOW >=", value, "sessionNumberNow");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNowLessThan(String value) {
            addCriterion("SESSION_NUMBER_NOW <", value, "sessionNumberNow");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNowLessThanOrEqualTo(String value) {
            addCriterion("SESSION_NUMBER_NOW <=", value, "sessionNumberNow");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNowLike(String value) {
            addCriterion("SESSION_NUMBER_NOW like", value, "sessionNumberNow");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNowNotLike(String value) {
            addCriterion("SESSION_NUMBER_NOW not like", value, "sessionNumberNow");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNowIn(List<String> values) {
            addCriterion("SESSION_NUMBER_NOW in", values, "sessionNumberNow");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNowNotIn(List<String> values) {
            addCriterion("SESSION_NUMBER_NOW not in", values, "sessionNumberNow");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNowBetween(String value1, String value2) {
            addCriterion("SESSION_NUMBER_NOW between", value1, value2, "sessionNumberNow");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNowNotBetween(String value1, String value2) {
            addCriterion("SESSION_NUMBER_NOW not between", value1, value2, "sessionNumberNow");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNowIsNull() {
            addCriterion("AUDIT_ROLE_NOW is null");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNowIsNotNull() {
            addCriterion("AUDIT_ROLE_NOW is not null");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNowEqualTo(String value) {
            addCriterion("AUDIT_ROLE_NOW =", value, "auditRoleNow");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNowNotEqualTo(String value) {
            addCriterion("AUDIT_ROLE_NOW <>", value, "auditRoleNow");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNowGreaterThan(String value) {
            addCriterion("AUDIT_ROLE_NOW >", value, "auditRoleNow");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNowGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_ROLE_NOW >=", value, "auditRoleNow");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNowLessThan(String value) {
            addCriterion("AUDIT_ROLE_NOW <", value, "auditRoleNow");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNowLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_ROLE_NOW <=", value, "auditRoleNow");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNowLike(String value) {
            addCriterion("AUDIT_ROLE_NOW like", value, "auditRoleNow");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNowNotLike(String value) {
            addCriterion("AUDIT_ROLE_NOW not like", value, "auditRoleNow");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNowIn(List<String> values) {
            addCriterion("AUDIT_ROLE_NOW in", values, "auditRoleNow");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNowNotIn(List<String> values) {
            addCriterion("AUDIT_ROLE_NOW not in", values, "auditRoleNow");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNowBetween(String value1, String value2) {
            addCriterion("AUDIT_ROLE_NOW between", value1, value2, "auditRoleNow");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNowNotBetween(String value1, String value2) {
            addCriterion("AUDIT_ROLE_NOW not between", value1, value2, "auditRoleNow");
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