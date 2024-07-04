package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ResDoctorOrgHistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResDoctorOrgHistoryExample() {
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

        public Criteria andHistoryOrgFlowIsNull() {
            addCriterion("HISTORY_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgFlowIsNotNull() {
            addCriterion("HISTORY_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgFlowEqualTo(String value) {
            addCriterion("HISTORY_ORG_FLOW =", value, "historyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgFlowNotEqualTo(String value) {
            addCriterion("HISTORY_ORG_FLOW <>", value, "historyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgFlowGreaterThan(String value) {
            addCriterion("HISTORY_ORG_FLOW >", value, "historyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("HISTORY_ORG_FLOW >=", value, "historyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgFlowLessThan(String value) {
            addCriterion("HISTORY_ORG_FLOW <", value, "historyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("HISTORY_ORG_FLOW <=", value, "historyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgFlowLike(String value) {
            addCriterion("HISTORY_ORG_FLOW like", value, "historyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgFlowNotLike(String value) {
            addCriterion("HISTORY_ORG_FLOW not like", value, "historyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgFlowIn(List<String> values) {
            addCriterion("HISTORY_ORG_FLOW in", values, "historyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgFlowNotIn(List<String> values) {
            addCriterion("HISTORY_ORG_FLOW not in", values, "historyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgFlowBetween(String value1, String value2) {
            addCriterion("HISTORY_ORG_FLOW between", value1, value2, "historyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgFlowNotBetween(String value1, String value2) {
            addCriterion("HISTORY_ORG_FLOW not between", value1, value2, "historyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgNameIsNull() {
            addCriterion("HISTORY_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgNameIsNotNull() {
            addCriterion("HISTORY_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgNameEqualTo(String value) {
            addCriterion("HISTORY_ORG_NAME =", value, "historyOrgName");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgNameNotEqualTo(String value) {
            addCriterion("HISTORY_ORG_NAME <>", value, "historyOrgName");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgNameGreaterThan(String value) {
            addCriterion("HISTORY_ORG_NAME >", value, "historyOrgName");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("HISTORY_ORG_NAME >=", value, "historyOrgName");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgNameLessThan(String value) {
            addCriterion("HISTORY_ORG_NAME <", value, "historyOrgName");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgNameLessThanOrEqualTo(String value) {
            addCriterion("HISTORY_ORG_NAME <=", value, "historyOrgName");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgNameLike(String value) {
            addCriterion("HISTORY_ORG_NAME like", value, "historyOrgName");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgNameNotLike(String value) {
            addCriterion("HISTORY_ORG_NAME not like", value, "historyOrgName");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgNameIn(List<String> values) {
            addCriterion("HISTORY_ORG_NAME in", values, "historyOrgName");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgNameNotIn(List<String> values) {
            addCriterion("HISTORY_ORG_NAME not in", values, "historyOrgName");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgNameBetween(String value1, String value2) {
            addCriterion("HISTORY_ORG_NAME between", value1, value2, "historyOrgName");
            return (Criteria) this;
        }

        public Criteria andHistoryOrgNameNotBetween(String value1, String value2) {
            addCriterion("HISTORY_ORG_NAME not between", value1, value2, "historyOrgName");
            return (Criteria) this;
        }

        public Criteria andOutDateIsNull() {
            addCriterion("OUT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andOutDateIsNotNull() {
            addCriterion("OUT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andOutDateEqualTo(String value) {
            addCriterion("OUT_DATE =", value, "outDate");
            return (Criteria) this;
        }

        public Criteria andOutDateNotEqualTo(String value) {
            addCriterion("OUT_DATE <>", value, "outDate");
            return (Criteria) this;
        }

        public Criteria andOutDateGreaterThan(String value) {
            addCriterion("OUT_DATE >", value, "outDate");
            return (Criteria) this;
        }

        public Criteria andOutDateGreaterThanOrEqualTo(String value) {
            addCriterion("OUT_DATE >=", value, "outDate");
            return (Criteria) this;
        }

        public Criteria andOutDateLessThan(String value) {
            addCriterion("OUT_DATE <", value, "outDate");
            return (Criteria) this;
        }

        public Criteria andOutDateLessThanOrEqualTo(String value) {
            addCriterion("OUT_DATE <=", value, "outDate");
            return (Criteria) this;
        }

        public Criteria andOutDateLike(String value) {
            addCriterion("OUT_DATE like", value, "outDate");
            return (Criteria) this;
        }

        public Criteria andOutDateNotLike(String value) {
            addCriterion("OUT_DATE not like", value, "outDate");
            return (Criteria) this;
        }

        public Criteria andOutDateIn(List<String> values) {
            addCriterion("OUT_DATE in", values, "outDate");
            return (Criteria) this;
        }

        public Criteria andOutDateNotIn(List<String> values) {
            addCriterion("OUT_DATE not in", values, "outDate");
            return (Criteria) this;
        }

        public Criteria andOutDateBetween(String value1, String value2) {
            addCriterion("OUT_DATE between", value1, value2, "outDate");
            return (Criteria) this;
        }

        public Criteria andOutDateNotBetween(String value1, String value2) {
            addCriterion("OUT_DATE not between", value1, value2, "outDate");
            return (Criteria) this;
        }

        public Criteria andInDateIsNull() {
            addCriterion("IN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andInDateIsNotNull() {
            addCriterion("IN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andInDateEqualTo(String value) {
            addCriterion("IN_DATE =", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateNotEqualTo(String value) {
            addCriterion("IN_DATE <>", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateGreaterThan(String value) {
            addCriterion("IN_DATE >", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateGreaterThanOrEqualTo(String value) {
            addCriterion("IN_DATE >=", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateLessThan(String value) {
            addCriterion("IN_DATE <", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateLessThanOrEqualTo(String value) {
            addCriterion("IN_DATE <=", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateLike(String value) {
            addCriterion("IN_DATE like", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateNotLike(String value) {
            addCriterion("IN_DATE not like", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateIn(List<String> values) {
            addCriterion("IN_DATE in", values, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateNotIn(List<String> values) {
            addCriterion("IN_DATE not in", values, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateBetween(String value1, String value2) {
            addCriterion("IN_DATE between", value1, value2, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateNotBetween(String value1, String value2) {
            addCriterion("IN_DATE not between", value1, value2, "inDate");
            return (Criteria) this;
        }

        public Criteria andChangeStatusIdIsNull() {
            addCriterion("CHANGE_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andChangeStatusIdIsNotNull() {
            addCriterion("CHANGE_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andChangeStatusIdEqualTo(String value) {
            addCriterion("CHANGE_STATUS_ID =", value, "changeStatusId");
            return (Criteria) this;
        }

        public Criteria andChangeStatusIdNotEqualTo(String value) {
            addCriterion("CHANGE_STATUS_ID <>", value, "changeStatusId");
            return (Criteria) this;
        }

        public Criteria andChangeStatusIdGreaterThan(String value) {
            addCriterion("CHANGE_STATUS_ID >", value, "changeStatusId");
            return (Criteria) this;
        }

        public Criteria andChangeStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("CHANGE_STATUS_ID >=", value, "changeStatusId");
            return (Criteria) this;
        }

        public Criteria andChangeStatusIdLessThan(String value) {
            addCriterion("CHANGE_STATUS_ID <", value, "changeStatusId");
            return (Criteria) this;
        }

        public Criteria andChangeStatusIdLessThanOrEqualTo(String value) {
            addCriterion("CHANGE_STATUS_ID <=", value, "changeStatusId");
            return (Criteria) this;
        }

        public Criteria andChangeStatusIdLike(String value) {
            addCriterion("CHANGE_STATUS_ID like", value, "changeStatusId");
            return (Criteria) this;
        }

        public Criteria andChangeStatusIdNotLike(String value) {
            addCriterion("CHANGE_STATUS_ID not like", value, "changeStatusId");
            return (Criteria) this;
        }

        public Criteria andChangeStatusIdIn(List<String> values) {
            addCriterion("CHANGE_STATUS_ID in", values, "changeStatusId");
            return (Criteria) this;
        }

        public Criteria andChangeStatusIdNotIn(List<String> values) {
            addCriterion("CHANGE_STATUS_ID not in", values, "changeStatusId");
            return (Criteria) this;
        }

        public Criteria andChangeStatusIdBetween(String value1, String value2) {
            addCriterion("CHANGE_STATUS_ID between", value1, value2, "changeStatusId");
            return (Criteria) this;
        }

        public Criteria andChangeStatusIdNotBetween(String value1, String value2) {
            addCriterion("CHANGE_STATUS_ID not between", value1, value2, "changeStatusId");
            return (Criteria) this;
        }

        public Criteria andChangeStatusNameIsNull() {
            addCriterion("CHANGE_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andChangeStatusNameIsNotNull() {
            addCriterion("CHANGE_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andChangeStatusNameEqualTo(String value) {
            addCriterion("CHANGE_STATUS_NAME =", value, "changeStatusName");
            return (Criteria) this;
        }

        public Criteria andChangeStatusNameNotEqualTo(String value) {
            addCriterion("CHANGE_STATUS_NAME <>", value, "changeStatusName");
            return (Criteria) this;
        }

        public Criteria andChangeStatusNameGreaterThan(String value) {
            addCriterion("CHANGE_STATUS_NAME >", value, "changeStatusName");
            return (Criteria) this;
        }

        public Criteria andChangeStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("CHANGE_STATUS_NAME >=", value, "changeStatusName");
            return (Criteria) this;
        }

        public Criteria andChangeStatusNameLessThan(String value) {
            addCriterion("CHANGE_STATUS_NAME <", value, "changeStatusName");
            return (Criteria) this;
        }

        public Criteria andChangeStatusNameLessThanOrEqualTo(String value) {
            addCriterion("CHANGE_STATUS_NAME <=", value, "changeStatusName");
            return (Criteria) this;
        }

        public Criteria andChangeStatusNameLike(String value) {
            addCriterion("CHANGE_STATUS_NAME like", value, "changeStatusName");
            return (Criteria) this;
        }

        public Criteria andChangeStatusNameNotLike(String value) {
            addCriterion("CHANGE_STATUS_NAME not like", value, "changeStatusName");
            return (Criteria) this;
        }

        public Criteria andChangeStatusNameIn(List<String> values) {
            addCriterion("CHANGE_STATUS_NAME in", values, "changeStatusName");
            return (Criteria) this;
        }

        public Criteria andChangeStatusNameNotIn(List<String> values) {
            addCriterion("CHANGE_STATUS_NAME not in", values, "changeStatusName");
            return (Criteria) this;
        }

        public Criteria andChangeStatusNameBetween(String value1, String value2) {
            addCriterion("CHANGE_STATUS_NAME between", value1, value2, "changeStatusName");
            return (Criteria) this;
        }

        public Criteria andChangeStatusNameNotBetween(String value1, String value2) {
            addCriterion("CHANGE_STATUS_NAME not between", value1, value2, "changeStatusName");
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

        public Criteria andTrainingSpeIdIsNull() {
            addCriterion("TRAINING_SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdIsNotNull() {
            addCriterion("TRAINING_SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdEqualTo(String value) {
            addCriterion("TRAINING_SPE_ID =", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdNotEqualTo(String value) {
            addCriterion("TRAINING_SPE_ID <>", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdGreaterThan(String value) {
            addCriterion("TRAINING_SPE_ID >", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_SPE_ID >=", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdLessThan(String value) {
            addCriterion("TRAINING_SPE_ID <", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_SPE_ID <=", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdLike(String value) {
            addCriterion("TRAINING_SPE_ID like", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdNotLike(String value) {
            addCriterion("TRAINING_SPE_ID not like", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdIn(List<String> values) {
            addCriterion("TRAINING_SPE_ID in", values, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdNotIn(List<String> values) {
            addCriterion("TRAINING_SPE_ID not in", values, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdBetween(String value1, String value2) {
            addCriterion("TRAINING_SPE_ID between", value1, value2, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdNotBetween(String value1, String value2) {
            addCriterion("TRAINING_SPE_ID not between", value1, value2, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameIsNull() {
            addCriterion("TRAINING_SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameIsNotNull() {
            addCriterion("TRAINING_SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameEqualTo(String value) {
            addCriterion("TRAINING_SPE_NAME =", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameNotEqualTo(String value) {
            addCriterion("TRAINING_SPE_NAME <>", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameGreaterThan(String value) {
            addCriterion("TRAINING_SPE_NAME >", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_SPE_NAME >=", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameLessThan(String value) {
            addCriterion("TRAINING_SPE_NAME <", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_SPE_NAME <=", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameLike(String value) {
            addCriterion("TRAINING_SPE_NAME like", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameNotLike(String value) {
            addCriterion("TRAINING_SPE_NAME not like", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameIn(List<String> values) {
            addCriterion("TRAINING_SPE_NAME in", values, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameNotIn(List<String> values) {
            addCriterion("TRAINING_SPE_NAME not in", values, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameBetween(String value1, String value2) {
            addCriterion("TRAINING_SPE_NAME between", value1, value2, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameNotBetween(String value1, String value2) {
            addCriterion("TRAINING_SPE_NAME not between", value1, value2, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeIdIsNull() {
            addCriterion("HISTORY_TRAINING_SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeIdIsNotNull() {
            addCriterion("HISTORY_TRAINING_SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeIdEqualTo(String value) {
            addCriterion("HISTORY_TRAINING_SPE_ID =", value, "historyTrainingSpeId");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeIdNotEqualTo(String value) {
            addCriterion("HISTORY_TRAINING_SPE_ID <>", value, "historyTrainingSpeId");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeIdGreaterThan(String value) {
            addCriterion("HISTORY_TRAINING_SPE_ID >", value, "historyTrainingSpeId");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("HISTORY_TRAINING_SPE_ID >=", value, "historyTrainingSpeId");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeIdLessThan(String value) {
            addCriterion("HISTORY_TRAINING_SPE_ID <", value, "historyTrainingSpeId");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeIdLessThanOrEqualTo(String value) {
            addCriterion("HISTORY_TRAINING_SPE_ID <=", value, "historyTrainingSpeId");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeIdLike(String value) {
            addCriterion("HISTORY_TRAINING_SPE_ID like", value, "historyTrainingSpeId");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeIdNotLike(String value) {
            addCriterion("HISTORY_TRAINING_SPE_ID not like", value, "historyTrainingSpeId");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeIdIn(List<String> values) {
            addCriterion("HISTORY_TRAINING_SPE_ID in", values, "historyTrainingSpeId");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeIdNotIn(List<String> values) {
            addCriterion("HISTORY_TRAINING_SPE_ID not in", values, "historyTrainingSpeId");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeIdBetween(String value1, String value2) {
            addCriterion("HISTORY_TRAINING_SPE_ID between", value1, value2, "historyTrainingSpeId");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeIdNotBetween(String value1, String value2) {
            addCriterion("HISTORY_TRAINING_SPE_ID not between", value1, value2, "historyTrainingSpeId");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeNameIsNull() {
            addCriterion("HISTORY_TRAINING_SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeNameIsNotNull() {
            addCriterion("HISTORY_TRAINING_SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeNameEqualTo(String value) {
            addCriterion("HISTORY_TRAINING_SPE_NAME =", value, "historyTrainingSpeName");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeNameNotEqualTo(String value) {
            addCriterion("HISTORY_TRAINING_SPE_NAME <>", value, "historyTrainingSpeName");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeNameGreaterThan(String value) {
            addCriterion("HISTORY_TRAINING_SPE_NAME >", value, "historyTrainingSpeName");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("HISTORY_TRAINING_SPE_NAME >=", value, "historyTrainingSpeName");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeNameLessThan(String value) {
            addCriterion("HISTORY_TRAINING_SPE_NAME <", value, "historyTrainingSpeName");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeNameLessThanOrEqualTo(String value) {
            addCriterion("HISTORY_TRAINING_SPE_NAME <=", value, "historyTrainingSpeName");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeNameLike(String value) {
            addCriterion("HISTORY_TRAINING_SPE_NAME like", value, "historyTrainingSpeName");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeNameNotLike(String value) {
            addCriterion("HISTORY_TRAINING_SPE_NAME not like", value, "historyTrainingSpeName");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeNameIn(List<String> values) {
            addCriterion("HISTORY_TRAINING_SPE_NAME in", values, "historyTrainingSpeName");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeNameNotIn(List<String> values) {
            addCriterion("HISTORY_TRAINING_SPE_NAME not in", values, "historyTrainingSpeName");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeNameBetween(String value1, String value2) {
            addCriterion("HISTORY_TRAINING_SPE_NAME between", value1, value2, "historyTrainingSpeName");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingSpeNameNotBetween(String value1, String value2) {
            addCriterion("HISTORY_TRAINING_SPE_NAME not between", value1, value2, "historyTrainingSpeName");
            return (Criteria) this;
        }

        public Criteria andSpeChangeApplyFileIsNull() {
            addCriterion("SPE_CHANGE_APPLY_FILE is null");
            return (Criteria) this;
        }

        public Criteria andSpeChangeApplyFileIsNotNull() {
            addCriterion("SPE_CHANGE_APPLY_FILE is not null");
            return (Criteria) this;
        }

        public Criteria andSpeChangeApplyFileEqualTo(String value) {
            addCriterion("SPE_CHANGE_APPLY_FILE =", value, "speChangeApplyFile");
            return (Criteria) this;
        }

        public Criteria andSpeChangeApplyFileNotEqualTo(String value) {
            addCriterion("SPE_CHANGE_APPLY_FILE <>", value, "speChangeApplyFile");
            return (Criteria) this;
        }

        public Criteria andSpeChangeApplyFileGreaterThan(String value) {
            addCriterion("SPE_CHANGE_APPLY_FILE >", value, "speChangeApplyFile");
            return (Criteria) this;
        }

        public Criteria andSpeChangeApplyFileGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_CHANGE_APPLY_FILE >=", value, "speChangeApplyFile");
            return (Criteria) this;
        }

        public Criteria andSpeChangeApplyFileLessThan(String value) {
            addCriterion("SPE_CHANGE_APPLY_FILE <", value, "speChangeApplyFile");
            return (Criteria) this;
        }

        public Criteria andSpeChangeApplyFileLessThanOrEqualTo(String value) {
            addCriterion("SPE_CHANGE_APPLY_FILE <=", value, "speChangeApplyFile");
            return (Criteria) this;
        }

        public Criteria andSpeChangeApplyFileLike(String value) {
            addCriterion("SPE_CHANGE_APPLY_FILE like", value, "speChangeApplyFile");
            return (Criteria) this;
        }

        public Criteria andSpeChangeApplyFileNotLike(String value) {
            addCriterion("SPE_CHANGE_APPLY_FILE not like", value, "speChangeApplyFile");
            return (Criteria) this;
        }

        public Criteria andSpeChangeApplyFileIn(List<String> values) {
            addCriterion("SPE_CHANGE_APPLY_FILE in", values, "speChangeApplyFile");
            return (Criteria) this;
        }

        public Criteria andSpeChangeApplyFileNotIn(List<String> values) {
            addCriterion("SPE_CHANGE_APPLY_FILE not in", values, "speChangeApplyFile");
            return (Criteria) this;
        }

        public Criteria andSpeChangeApplyFileBetween(String value1, String value2) {
            addCriterion("SPE_CHANGE_APPLY_FILE between", value1, value2, "speChangeApplyFile");
            return (Criteria) this;
        }

        public Criteria andSpeChangeApplyFileNotBetween(String value1, String value2) {
            addCriterion("SPE_CHANGE_APPLY_FILE not between", value1, value2, "speChangeApplyFile");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIdIsNull() {
            addCriterion("CHANGE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIdIsNotNull() {
            addCriterion("CHANGE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIdEqualTo(String value) {
            addCriterion("CHANGE_TYPE_ID =", value, "changeTypeId");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIdNotEqualTo(String value) {
            addCriterion("CHANGE_TYPE_ID <>", value, "changeTypeId");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIdGreaterThan(String value) {
            addCriterion("CHANGE_TYPE_ID >", value, "changeTypeId");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("CHANGE_TYPE_ID >=", value, "changeTypeId");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIdLessThan(String value) {
            addCriterion("CHANGE_TYPE_ID <", value, "changeTypeId");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIdLessThanOrEqualTo(String value) {
            addCriterion("CHANGE_TYPE_ID <=", value, "changeTypeId");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIdLike(String value) {
            addCriterion("CHANGE_TYPE_ID like", value, "changeTypeId");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIdNotLike(String value) {
            addCriterion("CHANGE_TYPE_ID not like", value, "changeTypeId");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIdIn(List<String> values) {
            addCriterion("CHANGE_TYPE_ID in", values, "changeTypeId");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIdNotIn(List<String> values) {
            addCriterion("CHANGE_TYPE_ID not in", values, "changeTypeId");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIdBetween(String value1, String value2) {
            addCriterion("CHANGE_TYPE_ID between", value1, value2, "changeTypeId");
            return (Criteria) this;
        }

        public Criteria andChangeTypeIdNotBetween(String value1, String value2) {
            addCriterion("CHANGE_TYPE_ID not between", value1, value2, "changeTypeId");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNameIsNull() {
            addCriterion("CHANGE_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNameIsNotNull() {
            addCriterion("CHANGE_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNameEqualTo(String value) {
            addCriterion("CHANGE_TYPE_NAME =", value, "changeTypeName");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNameNotEqualTo(String value) {
            addCriterion("CHANGE_TYPE_NAME <>", value, "changeTypeName");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNameGreaterThan(String value) {
            addCriterion("CHANGE_TYPE_NAME >", value, "changeTypeName");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("CHANGE_TYPE_NAME >=", value, "changeTypeName");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNameLessThan(String value) {
            addCriterion("CHANGE_TYPE_NAME <", value, "changeTypeName");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNameLessThanOrEqualTo(String value) {
            addCriterion("CHANGE_TYPE_NAME <=", value, "changeTypeName");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNameLike(String value) {
            addCriterion("CHANGE_TYPE_NAME like", value, "changeTypeName");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNameNotLike(String value) {
            addCriterion("CHANGE_TYPE_NAME not like", value, "changeTypeName");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNameIn(List<String> values) {
            addCriterion("CHANGE_TYPE_NAME in", values, "changeTypeName");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNameNotIn(List<String> values) {
            addCriterion("CHANGE_TYPE_NAME not in", values, "changeTypeName");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNameBetween(String value1, String value2) {
            addCriterion("CHANGE_TYPE_NAME between", value1, value2, "changeTypeName");
            return (Criteria) this;
        }

        public Criteria andChangeTypeNameNotBetween(String value1, String value2) {
            addCriterion("CHANGE_TYPE_NAME not between", value1, value2, "changeTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdIsNull() {
            addCriterion("TRAINING_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdIsNotNull() {
            addCriterion("TRAINING_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdEqualTo(String value) {
            addCriterion("TRAINING_TYPE_ID =", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdNotEqualTo(String value) {
            addCriterion("TRAINING_TYPE_ID <>", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdGreaterThan(String value) {
            addCriterion("TRAINING_TYPE_ID >", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_TYPE_ID >=", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdLessThan(String value) {
            addCriterion("TRAINING_TYPE_ID <", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_TYPE_ID <=", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdLike(String value) {
            addCriterion("TRAINING_TYPE_ID like", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdNotLike(String value) {
            addCriterion("TRAINING_TYPE_ID not like", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdIn(List<String> values) {
            addCriterion("TRAINING_TYPE_ID in", values, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdNotIn(List<String> values) {
            addCriterion("TRAINING_TYPE_ID not in", values, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdBetween(String value1, String value2) {
            addCriterion("TRAINING_TYPE_ID between", value1, value2, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdNotBetween(String value1, String value2) {
            addCriterion("TRAINING_TYPE_ID not between", value1, value2, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameIsNull() {
            addCriterion("TRAINING_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameIsNotNull() {
            addCriterion("TRAINING_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameEqualTo(String value) {
            addCriterion("TRAINING_TYPE_NAME =", value, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameNotEqualTo(String value) {
            addCriterion("TRAINING_TYPE_NAME <>", value, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameGreaterThan(String value) {
            addCriterion("TRAINING_TYPE_NAME >", value, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_TYPE_NAME >=", value, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameLessThan(String value) {
            addCriterion("TRAINING_TYPE_NAME <", value, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_TYPE_NAME <=", value, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameLike(String value) {
            addCriterion("TRAINING_TYPE_NAME like", value, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameNotLike(String value) {
            addCriterion("TRAINING_TYPE_NAME not like", value, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameIn(List<String> values) {
            addCriterion("TRAINING_TYPE_NAME in", values, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameNotIn(List<String> values) {
            addCriterion("TRAINING_TYPE_NAME not in", values, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameBetween(String value1, String value2) {
            addCriterion("TRAINING_TYPE_NAME between", value1, value2, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameNotBetween(String value1, String value2) {
            addCriterion("TRAINING_TYPE_NAME not between", value1, value2, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeIdIsNull() {
            addCriterion("HISTORY_TRAINING_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeIdIsNotNull() {
            addCriterion("HISTORY_TRAINING_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeIdEqualTo(String value) {
            addCriterion("HISTORY_TRAINING_TYPE_ID =", value, "historyTrainingTypeId");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeIdNotEqualTo(String value) {
            addCriterion("HISTORY_TRAINING_TYPE_ID <>", value, "historyTrainingTypeId");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeIdGreaterThan(String value) {
            addCriterion("HISTORY_TRAINING_TYPE_ID >", value, "historyTrainingTypeId");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("HISTORY_TRAINING_TYPE_ID >=", value, "historyTrainingTypeId");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeIdLessThan(String value) {
            addCriterion("HISTORY_TRAINING_TYPE_ID <", value, "historyTrainingTypeId");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeIdLessThanOrEqualTo(String value) {
            addCriterion("HISTORY_TRAINING_TYPE_ID <=", value, "historyTrainingTypeId");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeIdLike(String value) {
            addCriterion("HISTORY_TRAINING_TYPE_ID like", value, "historyTrainingTypeId");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeIdNotLike(String value) {
            addCriterion("HISTORY_TRAINING_TYPE_ID not like", value, "historyTrainingTypeId");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeIdIn(List<String> values) {
            addCriterion("HISTORY_TRAINING_TYPE_ID in", values, "historyTrainingTypeId");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeIdNotIn(List<String> values) {
            addCriterion("HISTORY_TRAINING_TYPE_ID not in", values, "historyTrainingTypeId");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeIdBetween(String value1, String value2) {
            addCriterion("HISTORY_TRAINING_TYPE_ID between", value1, value2, "historyTrainingTypeId");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeIdNotBetween(String value1, String value2) {
            addCriterion("HISTORY_TRAINING_TYPE_ID not between", value1, value2, "historyTrainingTypeId");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeNameIsNull() {
            addCriterion("HISTORY_TRAINING_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeNameIsNotNull() {
            addCriterion("HISTORY_TRAINING_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeNameEqualTo(String value) {
            addCriterion("HISTORY_TRAINING_TYPE_NAME =", value, "historyTrainingTypeName");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeNameNotEqualTo(String value) {
            addCriterion("HISTORY_TRAINING_TYPE_NAME <>", value, "historyTrainingTypeName");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeNameGreaterThan(String value) {
            addCriterion("HISTORY_TRAINING_TYPE_NAME >", value, "historyTrainingTypeName");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("HISTORY_TRAINING_TYPE_NAME >=", value, "historyTrainingTypeName");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeNameLessThan(String value) {
            addCriterion("HISTORY_TRAINING_TYPE_NAME <", value, "historyTrainingTypeName");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeNameLessThanOrEqualTo(String value) {
            addCriterion("HISTORY_TRAINING_TYPE_NAME <=", value, "historyTrainingTypeName");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeNameLike(String value) {
            addCriterion("HISTORY_TRAINING_TYPE_NAME like", value, "historyTrainingTypeName");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeNameNotLike(String value) {
            addCriterion("HISTORY_TRAINING_TYPE_NAME not like", value, "historyTrainingTypeName");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeNameIn(List<String> values) {
            addCriterion("HISTORY_TRAINING_TYPE_NAME in", values, "historyTrainingTypeName");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeNameNotIn(List<String> values) {
            addCriterion("HISTORY_TRAINING_TYPE_NAME not in", values, "historyTrainingTypeName");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeNameBetween(String value1, String value2) {
            addCriterion("HISTORY_TRAINING_TYPE_NAME between", value1, value2, "historyTrainingTypeName");
            return (Criteria) this;
        }

        public Criteria andHistoryTrainingTypeNameNotBetween(String value1, String value2) {
            addCriterion("HISTORY_TRAINING_TYPE_NAME not between", value1, value2, "historyTrainingTypeName");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowIsNull() {
            addCriterion("AUDIT_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowIsNotNull() {
            addCriterion("AUDIT_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowEqualTo(String value) {
            addCriterion("AUDIT_USER_FLOW =", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowNotEqualTo(String value) {
            addCriterion("AUDIT_USER_FLOW <>", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowGreaterThan(String value) {
            addCriterion("AUDIT_USER_FLOW >", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_USER_FLOW >=", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowLessThan(String value) {
            addCriterion("AUDIT_USER_FLOW <", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_USER_FLOW <=", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowLike(String value) {
            addCriterion("AUDIT_USER_FLOW like", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowNotLike(String value) {
            addCriterion("AUDIT_USER_FLOW not like", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowIn(List<String> values) {
            addCriterion("AUDIT_USER_FLOW in", values, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowNotIn(List<String> values) {
            addCriterion("AUDIT_USER_FLOW not in", values, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowBetween(String value1, String value2) {
            addCriterion("AUDIT_USER_FLOW between", value1, value2, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowNotBetween(String value1, String value2) {
            addCriterion("AUDIT_USER_FLOW not between", value1, value2, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameIsNull() {
            addCriterion("AUDIT_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameIsNotNull() {
            addCriterion("AUDIT_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameEqualTo(String value) {
            addCriterion("AUDIT_USER_NAME =", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotEqualTo(String value) {
            addCriterion("AUDIT_USER_NAME <>", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameGreaterThan(String value) {
            addCriterion("AUDIT_USER_NAME >", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_USER_NAME >=", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameLessThan(String value) {
            addCriterion("AUDIT_USER_NAME <", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_USER_NAME <=", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameLike(String value) {
            addCriterion("AUDIT_USER_NAME like", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotLike(String value) {
            addCriterion("AUDIT_USER_NAME not like", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameIn(List<String> values) {
            addCriterion("AUDIT_USER_NAME in", values, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotIn(List<String> values) {
            addCriterion("AUDIT_USER_NAME not in", values, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameBetween(String value1, String value2) {
            addCriterion("AUDIT_USER_NAME between", value1, value2, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotBetween(String value1, String value2) {
            addCriterion("AUDIT_USER_NAME not between", value1, value2, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIsNull() {
            addCriterion("SESSION_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIsNotNull() {
            addCriterion("SESSION_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andSessionNumberEqualTo(String value) {
            addCriterion("SESSION_NUMBER =", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotEqualTo(String value) {
            addCriterion("SESSION_NUMBER <>", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberGreaterThan(String value) {
            addCriterion("SESSION_NUMBER >", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberGreaterThanOrEqualTo(String value) {
            addCriterion("SESSION_NUMBER >=", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLessThan(String value) {
            addCriterion("SESSION_NUMBER <", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLessThanOrEqualTo(String value) {
            addCriterion("SESSION_NUMBER <=", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLike(String value) {
            addCriterion("SESSION_NUMBER like", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotLike(String value) {
            addCriterion("SESSION_NUMBER not like", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIn(List<String> values) {
            addCriterion("SESSION_NUMBER in", values, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotIn(List<String> values) {
            addCriterion("SESSION_NUMBER not in", values, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberBetween(String value1, String value2) {
            addCriterion("SESSION_NUMBER between", value1, value2, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotBetween(String value1, String value2) {
            addCriterion("SESSION_NUMBER not between", value1, value2, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andHistorySessionNumberIsNull() {
            addCriterion("HISTORY_SESSION_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andHistorySessionNumberIsNotNull() {
            addCriterion("HISTORY_SESSION_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andHistorySessionNumberEqualTo(String value) {
            addCriterion("HISTORY_SESSION_NUMBER =", value, "historySessionNumber");
            return (Criteria) this;
        }

        public Criteria andHistorySessionNumberNotEqualTo(String value) {
            addCriterion("HISTORY_SESSION_NUMBER <>", value, "historySessionNumber");
            return (Criteria) this;
        }

        public Criteria andHistorySessionNumberGreaterThan(String value) {
            addCriterion("HISTORY_SESSION_NUMBER >", value, "historySessionNumber");
            return (Criteria) this;
        }

        public Criteria andHistorySessionNumberGreaterThanOrEqualTo(String value) {
            addCriterion("HISTORY_SESSION_NUMBER >=", value, "historySessionNumber");
            return (Criteria) this;
        }

        public Criteria andHistorySessionNumberLessThan(String value) {
            addCriterion("HISTORY_SESSION_NUMBER <", value, "historySessionNumber");
            return (Criteria) this;
        }

        public Criteria andHistorySessionNumberLessThanOrEqualTo(String value) {
            addCriterion("HISTORY_SESSION_NUMBER <=", value, "historySessionNumber");
            return (Criteria) this;
        }

        public Criteria andHistorySessionNumberLike(String value) {
            addCriterion("HISTORY_SESSION_NUMBER like", value, "historySessionNumber");
            return (Criteria) this;
        }

        public Criteria andHistorySessionNumberNotLike(String value) {
            addCriterion("HISTORY_SESSION_NUMBER not like", value, "historySessionNumber");
            return (Criteria) this;
        }

        public Criteria andHistorySessionNumberIn(List<String> values) {
            addCriterion("HISTORY_SESSION_NUMBER in", values, "historySessionNumber");
            return (Criteria) this;
        }

        public Criteria andHistorySessionNumberNotIn(List<String> values) {
            addCriterion("HISTORY_SESSION_NUMBER not in", values, "historySessionNumber");
            return (Criteria) this;
        }

        public Criteria andHistorySessionNumberBetween(String value1, String value2) {
            addCriterion("HISTORY_SESSION_NUMBER between", value1, value2, "historySessionNumber");
            return (Criteria) this;
        }

        public Criteria andHistorySessionNumberNotBetween(String value1, String value2) {
            addCriterion("HISTORY_SESSION_NUMBER not between", value1, value2, "historySessionNumber");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowIsNull() {
            addCriterion("RECRUIT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowIsNotNull() {
            addCriterion("RECRUIT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowEqualTo(String value) {
            addCriterion("RECRUIT_FLOW =", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotEqualTo(String value) {
            addCriterion("RECRUIT_FLOW <>", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowGreaterThan(String value) {
            addCriterion("RECRUIT_FLOW >", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RECRUIT_FLOW >=", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowLessThan(String value) {
            addCriterion("RECRUIT_FLOW <", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowLessThanOrEqualTo(String value) {
            addCriterion("RECRUIT_FLOW <=", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowLike(String value) {
            addCriterion("RECRUIT_FLOW like", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotLike(String value) {
            addCriterion("RECRUIT_FLOW not like", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowIn(List<String> values) {
            addCriterion("RECRUIT_FLOW in", values, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotIn(List<String> values) {
            addCriterion("RECRUIT_FLOW not in", values, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowBetween(String value1, String value2) {
            addCriterion("RECRUIT_FLOW between", value1, value2, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotBetween(String value1, String value2) {
            addCriterion("RECRUIT_FLOW not between", value1, value2, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andOutCheckChangeBaseFileIsNull() {
            addCriterion("OUT_CHECK_CHANGE_BASE_FILE is null");
            return (Criteria) this;
        }

        public Criteria andOutCheckChangeBaseFileIsNotNull() {
            addCriterion("OUT_CHECK_CHANGE_BASE_FILE is not null");
            return (Criteria) this;
        }

        public Criteria andOutCheckChangeBaseFileEqualTo(String value) {
            addCriterion("OUT_CHECK_CHANGE_BASE_FILE =", value, "outCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andOutCheckChangeBaseFileNotEqualTo(String value) {
            addCriterion("OUT_CHECK_CHANGE_BASE_FILE <>", value, "outCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andOutCheckChangeBaseFileGreaterThan(String value) {
            addCriterion("OUT_CHECK_CHANGE_BASE_FILE >", value, "outCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andOutCheckChangeBaseFileGreaterThanOrEqualTo(String value) {
            addCriterion("OUT_CHECK_CHANGE_BASE_FILE >=", value, "outCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andOutCheckChangeBaseFileLessThan(String value) {
            addCriterion("OUT_CHECK_CHANGE_BASE_FILE <", value, "outCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andOutCheckChangeBaseFileLessThanOrEqualTo(String value) {
            addCriterion("OUT_CHECK_CHANGE_BASE_FILE <=", value, "outCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andOutCheckChangeBaseFileLike(String value) {
            addCriterion("OUT_CHECK_CHANGE_BASE_FILE like", value, "outCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andOutCheckChangeBaseFileNotLike(String value) {
            addCriterion("OUT_CHECK_CHANGE_BASE_FILE not like", value, "outCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andOutCheckChangeBaseFileIn(List<String> values) {
            addCriterion("OUT_CHECK_CHANGE_BASE_FILE in", values, "outCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andOutCheckChangeBaseFileNotIn(List<String> values) {
            addCriterion("OUT_CHECK_CHANGE_BASE_FILE not in", values, "outCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andOutCheckChangeBaseFileBetween(String value1, String value2) {
            addCriterion("OUT_CHECK_CHANGE_BASE_FILE between", value1, value2, "outCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andOutCheckChangeBaseFileNotBetween(String value1, String value2) {
            addCriterion("OUT_CHECK_CHANGE_BASE_FILE not between", value1, value2, "outCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andInCheckChangeBaseFileIsNull() {
            addCriterion("IN_CHECK_CHANGE_BASE_FILE is null");
            return (Criteria) this;
        }

        public Criteria andInCheckChangeBaseFileIsNotNull() {
            addCriterion("IN_CHECK_CHANGE_BASE_FILE is not null");
            return (Criteria) this;
        }

        public Criteria andInCheckChangeBaseFileEqualTo(String value) {
            addCriterion("IN_CHECK_CHANGE_BASE_FILE =", value, "inCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andInCheckChangeBaseFileNotEqualTo(String value) {
            addCriterion("IN_CHECK_CHANGE_BASE_FILE <>", value, "inCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andInCheckChangeBaseFileGreaterThan(String value) {
            addCriterion("IN_CHECK_CHANGE_BASE_FILE >", value, "inCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andInCheckChangeBaseFileGreaterThanOrEqualTo(String value) {
            addCriterion("IN_CHECK_CHANGE_BASE_FILE >=", value, "inCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andInCheckChangeBaseFileLessThan(String value) {
            addCriterion("IN_CHECK_CHANGE_BASE_FILE <", value, "inCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andInCheckChangeBaseFileLessThanOrEqualTo(String value) {
            addCriterion("IN_CHECK_CHANGE_BASE_FILE <=", value, "inCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andInCheckChangeBaseFileLike(String value) {
            addCriterion("IN_CHECK_CHANGE_BASE_FILE like", value, "inCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andInCheckChangeBaseFileNotLike(String value) {
            addCriterion("IN_CHECK_CHANGE_BASE_FILE not like", value, "inCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andInCheckChangeBaseFileIn(List<String> values) {
            addCriterion("IN_CHECK_CHANGE_BASE_FILE in", values, "inCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andInCheckChangeBaseFileNotIn(List<String> values) {
            addCriterion("IN_CHECK_CHANGE_BASE_FILE not in", values, "inCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andInCheckChangeBaseFileBetween(String value1, String value2) {
            addCriterion("IN_CHECK_CHANGE_BASE_FILE between", value1, value2, "inCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andInCheckChangeBaseFileNotBetween(String value1, String value2) {
            addCriterion("IN_CHECK_CHANGE_BASE_FILE not between", value1, value2, "inCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andGlobalCheckChangeBaseFileIsNull() {
            addCriterion("GLOBAL_CHECK_CHANGE_BASE_FILE is null");
            return (Criteria) this;
        }

        public Criteria andGlobalCheckChangeBaseFileIsNotNull() {
            addCriterion("GLOBAL_CHECK_CHANGE_BASE_FILE is not null");
            return (Criteria) this;
        }

        public Criteria andGlobalCheckChangeBaseFileEqualTo(String value) {
            addCriterion("GLOBAL_CHECK_CHANGE_BASE_FILE =", value, "globalCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andGlobalCheckChangeBaseFileNotEqualTo(String value) {
            addCriterion("GLOBAL_CHECK_CHANGE_BASE_FILE <>", value, "globalCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andGlobalCheckChangeBaseFileGreaterThan(String value) {
            addCriterion("GLOBAL_CHECK_CHANGE_BASE_FILE >", value, "globalCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andGlobalCheckChangeBaseFileGreaterThanOrEqualTo(String value) {
            addCriterion("GLOBAL_CHECK_CHANGE_BASE_FILE >=", value, "globalCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andGlobalCheckChangeBaseFileLessThan(String value) {
            addCriterion("GLOBAL_CHECK_CHANGE_BASE_FILE <", value, "globalCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andGlobalCheckChangeBaseFileLessThanOrEqualTo(String value) {
            addCriterion("GLOBAL_CHECK_CHANGE_BASE_FILE <=", value, "globalCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andGlobalCheckChangeBaseFileLike(String value) {
            addCriterion("GLOBAL_CHECK_CHANGE_BASE_FILE like", value, "globalCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andGlobalCheckChangeBaseFileNotLike(String value) {
            addCriterion("GLOBAL_CHECK_CHANGE_BASE_FILE not like", value, "globalCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andGlobalCheckChangeBaseFileIn(List<String> values) {
            addCriterion("GLOBAL_CHECK_CHANGE_BASE_FILE in", values, "globalCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andGlobalCheckChangeBaseFileNotIn(List<String> values) {
            addCriterion("GLOBAL_CHECK_CHANGE_BASE_FILE not in", values, "globalCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andGlobalCheckChangeBaseFileBetween(String value1, String value2) {
            addCriterion("GLOBAL_CHECK_CHANGE_BASE_FILE between", value1, value2, "globalCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andGlobalCheckChangeBaseFileNotBetween(String value1, String value2) {
            addCriterion("GLOBAL_CHECK_CHANGE_BASE_FILE not between", value1, value2, "globalCheckChangeBaseFile");
            return (Criteria) this;
        }

        public Criteria andLocalCheckChangeSpeFileIsNull() {
            addCriterion("LOCAL_CHECK_CHANGE_SPE_FILE is null");
            return (Criteria) this;
        }

        public Criteria andLocalCheckChangeSpeFileIsNotNull() {
            addCriterion("LOCAL_CHECK_CHANGE_SPE_FILE is not null");
            return (Criteria) this;
        }

        public Criteria andLocalCheckChangeSpeFileEqualTo(String value) {
            addCriterion("LOCAL_CHECK_CHANGE_SPE_FILE =", value, "localCheckChangeSpeFile");
            return (Criteria) this;
        }

        public Criteria andLocalCheckChangeSpeFileNotEqualTo(String value) {
            addCriterion("LOCAL_CHECK_CHANGE_SPE_FILE <>", value, "localCheckChangeSpeFile");
            return (Criteria) this;
        }

        public Criteria andLocalCheckChangeSpeFileGreaterThan(String value) {
            addCriterion("LOCAL_CHECK_CHANGE_SPE_FILE >", value, "localCheckChangeSpeFile");
            return (Criteria) this;
        }

        public Criteria andLocalCheckChangeSpeFileGreaterThanOrEqualTo(String value) {
            addCriterion("LOCAL_CHECK_CHANGE_SPE_FILE >=", value, "localCheckChangeSpeFile");
            return (Criteria) this;
        }

        public Criteria andLocalCheckChangeSpeFileLessThan(String value) {
            addCriterion("LOCAL_CHECK_CHANGE_SPE_FILE <", value, "localCheckChangeSpeFile");
            return (Criteria) this;
        }

        public Criteria andLocalCheckChangeSpeFileLessThanOrEqualTo(String value) {
            addCriterion("LOCAL_CHECK_CHANGE_SPE_FILE <=", value, "localCheckChangeSpeFile");
            return (Criteria) this;
        }

        public Criteria andLocalCheckChangeSpeFileLike(String value) {
            addCriterion("LOCAL_CHECK_CHANGE_SPE_FILE like", value, "localCheckChangeSpeFile");
            return (Criteria) this;
        }

        public Criteria andLocalCheckChangeSpeFileNotLike(String value) {
            addCriterion("LOCAL_CHECK_CHANGE_SPE_FILE not like", value, "localCheckChangeSpeFile");
            return (Criteria) this;
        }

        public Criteria andLocalCheckChangeSpeFileIn(List<String> values) {
            addCriterion("LOCAL_CHECK_CHANGE_SPE_FILE in", values, "localCheckChangeSpeFile");
            return (Criteria) this;
        }

        public Criteria andLocalCheckChangeSpeFileNotIn(List<String> values) {
            addCriterion("LOCAL_CHECK_CHANGE_SPE_FILE not in", values, "localCheckChangeSpeFile");
            return (Criteria) this;
        }

        public Criteria andLocalCheckChangeSpeFileBetween(String value1, String value2) {
            addCriterion("LOCAL_CHECK_CHANGE_SPE_FILE between", value1, value2, "localCheckChangeSpeFile");
            return (Criteria) this;
        }

        public Criteria andLocalCheckChangeSpeFileNotBetween(String value1, String value2) {
            addCriterion("LOCAL_CHECK_CHANGE_SPE_FILE not between", value1, value2, "localCheckChangeSpeFile");
            return (Criteria) this;
        }

        public Criteria andChangeInGraduationYearIsNull() {
            addCriterion("CHANGE_IN_GRADUATION_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andChangeInGraduationYearIsNotNull() {
            addCriterion("CHANGE_IN_GRADUATION_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andChangeInGraduationYearEqualTo(String value) {
            addCriterion("CHANGE_IN_GRADUATION_YEAR =", value, "changeInGraduationYear");
            return (Criteria) this;
        }

        public Criteria andChangeInGraduationYearNotEqualTo(String value) {
            addCriterion("CHANGE_IN_GRADUATION_YEAR <>", value, "changeInGraduationYear");
            return (Criteria) this;
        }

        public Criteria andChangeInGraduationYearGreaterThan(String value) {
            addCriterion("CHANGE_IN_GRADUATION_YEAR >", value, "changeInGraduationYear");
            return (Criteria) this;
        }

        public Criteria andChangeInGraduationYearGreaterThanOrEqualTo(String value) {
            addCriterion("CHANGE_IN_GRADUATION_YEAR >=", value, "changeInGraduationYear");
            return (Criteria) this;
        }

        public Criteria andChangeInGraduationYearLessThan(String value) {
            addCriterion("CHANGE_IN_GRADUATION_YEAR <", value, "changeInGraduationYear");
            return (Criteria) this;
        }

        public Criteria andChangeInGraduationYearLessThanOrEqualTo(String value) {
            addCriterion("CHANGE_IN_GRADUATION_YEAR <=", value, "changeInGraduationYear");
            return (Criteria) this;
        }

        public Criteria andChangeInGraduationYearLike(String value) {
            addCriterion("CHANGE_IN_GRADUATION_YEAR like", value, "changeInGraduationYear");
            return (Criteria) this;
        }

        public Criteria andChangeInGraduationYearNotLike(String value) {
            addCriterion("CHANGE_IN_GRADUATION_YEAR not like", value, "changeInGraduationYear");
            return (Criteria) this;
        }

        public Criteria andChangeInGraduationYearIn(List<String> values) {
            addCriterion("CHANGE_IN_GRADUATION_YEAR in", values, "changeInGraduationYear");
            return (Criteria) this;
        }

        public Criteria andChangeInGraduationYearNotIn(List<String> values) {
            addCriterion("CHANGE_IN_GRADUATION_YEAR not in", values, "changeInGraduationYear");
            return (Criteria) this;
        }

        public Criteria andChangeInGraduationYearBetween(String value1, String value2) {
            addCriterion("CHANGE_IN_GRADUATION_YEAR between", value1, value2, "changeInGraduationYear");
            return (Criteria) this;
        }

        public Criteria andChangeInGraduationYearNotBetween(String value1, String value2) {
            addCriterion("CHANGE_IN_GRADUATION_YEAR not between", value1, value2, "changeInGraduationYear");
            return (Criteria) this;
        }

        public Criteria andIsAgreeOldTrainIsNull() {
            addCriterion("IS_AGREE_OLD_TRAIN is null");
            return (Criteria) this;
        }

        public Criteria andIsAgreeOldTrainIsNotNull() {
            addCriterion("IS_AGREE_OLD_TRAIN is not null");
            return (Criteria) this;
        }

        public Criteria andIsAgreeOldTrainEqualTo(String value) {
            addCriterion("IS_AGREE_OLD_TRAIN =", value, "isAgreeOldTrain");
            return (Criteria) this;
        }

        public Criteria andIsAgreeOldTrainNotEqualTo(String value) {
            addCriterion("IS_AGREE_OLD_TRAIN <>", value, "isAgreeOldTrain");
            return (Criteria) this;
        }

        public Criteria andIsAgreeOldTrainGreaterThan(String value) {
            addCriterion("IS_AGREE_OLD_TRAIN >", value, "isAgreeOldTrain");
            return (Criteria) this;
        }

        public Criteria andIsAgreeOldTrainGreaterThanOrEqualTo(String value) {
            addCriterion("IS_AGREE_OLD_TRAIN >=", value, "isAgreeOldTrain");
            return (Criteria) this;
        }

        public Criteria andIsAgreeOldTrainLessThan(String value) {
            addCriterion("IS_AGREE_OLD_TRAIN <", value, "isAgreeOldTrain");
            return (Criteria) this;
        }

        public Criteria andIsAgreeOldTrainLessThanOrEqualTo(String value) {
            addCriterion("IS_AGREE_OLD_TRAIN <=", value, "isAgreeOldTrain");
            return (Criteria) this;
        }

        public Criteria andIsAgreeOldTrainLike(String value) {
            addCriterion("IS_AGREE_OLD_TRAIN like", value, "isAgreeOldTrain");
            return (Criteria) this;
        }

        public Criteria andIsAgreeOldTrainNotLike(String value) {
            addCriterion("IS_AGREE_OLD_TRAIN not like", value, "isAgreeOldTrain");
            return (Criteria) this;
        }

        public Criteria andIsAgreeOldTrainIn(List<String> values) {
            addCriterion("IS_AGREE_OLD_TRAIN in", values, "isAgreeOldTrain");
            return (Criteria) this;
        }

        public Criteria andIsAgreeOldTrainNotIn(List<String> values) {
            addCriterion("IS_AGREE_OLD_TRAIN not in", values, "isAgreeOldTrain");
            return (Criteria) this;
        }

        public Criteria andIsAgreeOldTrainBetween(String value1, String value2) {
            addCriterion("IS_AGREE_OLD_TRAIN between", value1, value2, "isAgreeOldTrain");
            return (Criteria) this;
        }

        public Criteria andIsAgreeOldTrainNotBetween(String value1, String value2) {
            addCriterion("IS_AGREE_OLD_TRAIN not between", value1, value2, "isAgreeOldTrain");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdIsNull() {
            addCriterion("SECOND_SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdIsNotNull() {
            addCriterion("SECOND_SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdEqualTo(String value) {
            addCriterion("SECOND_SPE_ID =", value, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdNotEqualTo(String value) {
            addCriterion("SECOND_SPE_ID <>", value, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdGreaterThan(String value) {
            addCriterion("SECOND_SPE_ID >", value, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SECOND_SPE_ID >=", value, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdLessThan(String value) {
            addCriterion("SECOND_SPE_ID <", value, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdLessThanOrEqualTo(String value) {
            addCriterion("SECOND_SPE_ID <=", value, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdLike(String value) {
            addCriterion("SECOND_SPE_ID like", value, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdNotLike(String value) {
            addCriterion("SECOND_SPE_ID not like", value, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdIn(List<String> values) {
            addCriterion("SECOND_SPE_ID in", values, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdNotIn(List<String> values) {
            addCriterion("SECOND_SPE_ID not in", values, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdBetween(String value1, String value2) {
            addCriterion("SECOND_SPE_ID between", value1, value2, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdNotBetween(String value1, String value2) {
            addCriterion("SECOND_SPE_ID not between", value1, value2, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameIsNull() {
            addCriterion("SECOND_SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameIsNotNull() {
            addCriterion("SECOND_SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameEqualTo(String value) {
            addCriterion("SECOND_SPE_NAME =", value, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameNotEqualTo(String value) {
            addCriterion("SECOND_SPE_NAME <>", value, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameGreaterThan(String value) {
            addCriterion("SECOND_SPE_NAME >", value, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SECOND_SPE_NAME >=", value, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameLessThan(String value) {
            addCriterion("SECOND_SPE_NAME <", value, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameLessThanOrEqualTo(String value) {
            addCriterion("SECOND_SPE_NAME <=", value, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameLike(String value) {
            addCriterion("SECOND_SPE_NAME like", value, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameNotLike(String value) {
            addCriterion("SECOND_SPE_NAME not like", value, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameIn(List<String> values) {
            addCriterion("SECOND_SPE_NAME in", values, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameNotIn(List<String> values) {
            addCriterion("SECOND_SPE_NAME not in", values, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameBetween(String value1, String value2) {
            addCriterion("SECOND_SPE_NAME between", value1, value2, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameNotBetween(String value1, String value2) {
            addCriterion("SECOND_SPE_NAME not between", value1, value2, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeIdIsNull() {
            addCriterion("HISTORY_SECOND_SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeIdIsNotNull() {
            addCriterion("HISTORY_SECOND_SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeIdEqualTo(String value) {
            addCriterion("HISTORY_SECOND_SPE_ID =", value, "historySecondSpeId");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeIdNotEqualTo(String value) {
            addCriterion("HISTORY_SECOND_SPE_ID <>", value, "historySecondSpeId");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeIdGreaterThan(String value) {
            addCriterion("HISTORY_SECOND_SPE_ID >", value, "historySecondSpeId");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("HISTORY_SECOND_SPE_ID >=", value, "historySecondSpeId");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeIdLessThan(String value) {
            addCriterion("HISTORY_SECOND_SPE_ID <", value, "historySecondSpeId");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeIdLessThanOrEqualTo(String value) {
            addCriterion("HISTORY_SECOND_SPE_ID <=", value, "historySecondSpeId");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeIdLike(String value) {
            addCriterion("HISTORY_SECOND_SPE_ID like", value, "historySecondSpeId");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeIdNotLike(String value) {
            addCriterion("HISTORY_SECOND_SPE_ID not like", value, "historySecondSpeId");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeIdIn(List<String> values) {
            addCriterion("HISTORY_SECOND_SPE_ID in", values, "historySecondSpeId");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeIdNotIn(List<String> values) {
            addCriterion("HISTORY_SECOND_SPE_ID not in", values, "historySecondSpeId");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeIdBetween(String value1, String value2) {
            addCriterion("HISTORY_SECOND_SPE_ID between", value1, value2, "historySecondSpeId");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeIdNotBetween(String value1, String value2) {
            addCriterion("HISTORY_SECOND_SPE_ID not between", value1, value2, "historySecondSpeId");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeNameIsNull() {
            addCriterion("HISTORY_SECOND_SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeNameIsNotNull() {
            addCriterion("HISTORY_SECOND_SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeNameEqualTo(String value) {
            addCriterion("HISTORY_SECOND_SPE_NAME =", value, "historySecondSpeName");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeNameNotEqualTo(String value) {
            addCriterion("HISTORY_SECOND_SPE_NAME <>", value, "historySecondSpeName");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeNameGreaterThan(String value) {
            addCriterion("HISTORY_SECOND_SPE_NAME >", value, "historySecondSpeName");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("HISTORY_SECOND_SPE_NAME >=", value, "historySecondSpeName");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeNameLessThan(String value) {
            addCriterion("HISTORY_SECOND_SPE_NAME <", value, "historySecondSpeName");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeNameLessThanOrEqualTo(String value) {
            addCriterion("HISTORY_SECOND_SPE_NAME <=", value, "historySecondSpeName");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeNameLike(String value) {
            addCriterion("HISTORY_SECOND_SPE_NAME like", value, "historySecondSpeName");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeNameNotLike(String value) {
            addCriterion("HISTORY_SECOND_SPE_NAME not like", value, "historySecondSpeName");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeNameIn(List<String> values) {
            addCriterion("HISTORY_SECOND_SPE_NAME in", values, "historySecondSpeName");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeNameNotIn(List<String> values) {
            addCriterion("HISTORY_SECOND_SPE_NAME not in", values, "historySecondSpeName");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeNameBetween(String value1, String value2) {
            addCriterion("HISTORY_SECOND_SPE_NAME between", value1, value2, "historySecondSpeName");
            return (Criteria) this;
        }

        public Criteria andHistorySecondSpeNameNotBetween(String value1, String value2) {
            addCriterion("HISTORY_SECOND_SPE_NAME not between", value1, value2, "historySecondSpeName");
            return (Criteria) this;
        }

        public Criteria andIsOnlySecondIsNull() {
            addCriterion("IS_ONLY_SECOND is null");
            return (Criteria) this;
        }

        public Criteria andIsOnlySecondIsNotNull() {
            addCriterion("IS_ONLY_SECOND is not null");
            return (Criteria) this;
        }

        public Criteria andIsOnlySecondEqualTo(String value) {
            addCriterion("IS_ONLY_SECOND =", value, "isOnlySecond");
            return (Criteria) this;
        }

        public Criteria andIsOnlySecondNotEqualTo(String value) {
            addCriterion("IS_ONLY_SECOND <>", value, "isOnlySecond");
            return (Criteria) this;
        }

        public Criteria andIsOnlySecondGreaterThan(String value) {
            addCriterion("IS_ONLY_SECOND >", value, "isOnlySecond");
            return (Criteria) this;
        }

        public Criteria andIsOnlySecondGreaterThanOrEqualTo(String value) {
            addCriterion("IS_ONLY_SECOND >=", value, "isOnlySecond");
            return (Criteria) this;
        }

        public Criteria andIsOnlySecondLessThan(String value) {
            addCriterion("IS_ONLY_SECOND <", value, "isOnlySecond");
            return (Criteria) this;
        }

        public Criteria andIsOnlySecondLessThanOrEqualTo(String value) {
            addCriterion("IS_ONLY_SECOND <=", value, "isOnlySecond");
            return (Criteria) this;
        }

        public Criteria andIsOnlySecondLike(String value) {
            addCriterion("IS_ONLY_SECOND like", value, "isOnlySecond");
            return (Criteria) this;
        }

        public Criteria andIsOnlySecondNotLike(String value) {
            addCriterion("IS_ONLY_SECOND not like", value, "isOnlySecond");
            return (Criteria) this;
        }

        public Criteria andIsOnlySecondIn(List<String> values) {
            addCriterion("IS_ONLY_SECOND in", values, "isOnlySecond");
            return (Criteria) this;
        }

        public Criteria andIsOnlySecondNotIn(List<String> values) {
            addCriterion("IS_ONLY_SECOND not in", values, "isOnlySecond");
            return (Criteria) this;
        }

        public Criteria andIsOnlySecondBetween(String value1, String value2) {
            addCriterion("IS_ONLY_SECOND between", value1, value2, "isOnlySecond");
            return (Criteria) this;
        }

        public Criteria andIsOnlySecondNotBetween(String value1, String value2) {
            addCriterion("IS_ONLY_SECOND not between", value1, value2, "isOnlySecond");
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