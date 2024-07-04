package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ScholarSchArrangeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ScholarSchArrangeExample() {
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

        public Criteria andArrangeFlowIsNull() {
            addCriterion("ARRANGE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowIsNotNull() {
            addCriterion("ARRANGE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowEqualTo(String value) {
            addCriterion("ARRANGE_FLOW =", value, "arrangeFlow");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowNotEqualTo(String value) {
            addCriterion("ARRANGE_FLOW <>", value, "arrangeFlow");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowGreaterThan(String value) {
            addCriterion("ARRANGE_FLOW >", value, "arrangeFlow");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ARRANGE_FLOW >=", value, "arrangeFlow");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowLessThan(String value) {
            addCriterion("ARRANGE_FLOW <", value, "arrangeFlow");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowLessThanOrEqualTo(String value) {
            addCriterion("ARRANGE_FLOW <=", value, "arrangeFlow");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowLike(String value) {
            addCriterion("ARRANGE_FLOW like", value, "arrangeFlow");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowNotLike(String value) {
            addCriterion("ARRANGE_FLOW not like", value, "arrangeFlow");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowIn(List<String> values) {
            addCriterion("ARRANGE_FLOW in", values, "arrangeFlow");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowNotIn(List<String> values) {
            addCriterion("ARRANGE_FLOW not in", values, "arrangeFlow");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowBetween(String value1, String value2) {
            addCriterion("ARRANGE_FLOW between", value1, value2, "arrangeFlow");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowNotBetween(String value1, String value2) {
            addCriterion("ARRANGE_FLOW not between", value1, value2, "arrangeFlow");
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

        public Criteria andSecretaryFlowIsNull() {
            addCriterion("SECRETARY_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSecretaryFlowIsNotNull() {
            addCriterion("SECRETARY_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSecretaryFlowEqualTo(String value) {
            addCriterion("SECRETARY_FLOW =", value, "secretaryFlow");
            return (Criteria) this;
        }

        public Criteria andSecretaryFlowNotEqualTo(String value) {
            addCriterion("SECRETARY_FLOW <>", value, "secretaryFlow");
            return (Criteria) this;
        }

        public Criteria andSecretaryFlowGreaterThan(String value) {
            addCriterion("SECRETARY_FLOW >", value, "secretaryFlow");
            return (Criteria) this;
        }

        public Criteria andSecretaryFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SECRETARY_FLOW >=", value, "secretaryFlow");
            return (Criteria) this;
        }

        public Criteria andSecretaryFlowLessThan(String value) {
            addCriterion("SECRETARY_FLOW <", value, "secretaryFlow");
            return (Criteria) this;
        }

        public Criteria andSecretaryFlowLessThanOrEqualTo(String value) {
            addCriterion("SECRETARY_FLOW <=", value, "secretaryFlow");
            return (Criteria) this;
        }

        public Criteria andSecretaryFlowLike(String value) {
            addCriterion("SECRETARY_FLOW like", value, "secretaryFlow");
            return (Criteria) this;
        }

        public Criteria andSecretaryFlowNotLike(String value) {
            addCriterion("SECRETARY_FLOW not like", value, "secretaryFlow");
            return (Criteria) this;
        }

        public Criteria andSecretaryFlowIn(List<String> values) {
            addCriterion("SECRETARY_FLOW in", values, "secretaryFlow");
            return (Criteria) this;
        }

        public Criteria andSecretaryFlowNotIn(List<String> values) {
            addCriterion("SECRETARY_FLOW not in", values, "secretaryFlow");
            return (Criteria) this;
        }

        public Criteria andSecretaryFlowBetween(String value1, String value2) {
            addCriterion("SECRETARY_FLOW between", value1, value2, "secretaryFlow");
            return (Criteria) this;
        }

        public Criteria andSecretaryFlowNotBetween(String value1, String value2) {
            addCriterion("SECRETARY_FLOW not between", value1, value2, "secretaryFlow");
            return (Criteria) this;
        }

        public Criteria andSecretaryNameIsNull() {
            addCriterion("SECRETARY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSecretaryNameIsNotNull() {
            addCriterion("SECRETARY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSecretaryNameEqualTo(String value) {
            addCriterion("SECRETARY_NAME =", value, "secretaryName");
            return (Criteria) this;
        }

        public Criteria andSecretaryNameNotEqualTo(String value) {
            addCriterion("SECRETARY_NAME <>", value, "secretaryName");
            return (Criteria) this;
        }

        public Criteria andSecretaryNameGreaterThan(String value) {
            addCriterion("SECRETARY_NAME >", value, "secretaryName");
            return (Criteria) this;
        }

        public Criteria andSecretaryNameGreaterThanOrEqualTo(String value) {
            addCriterion("SECRETARY_NAME >=", value, "secretaryName");
            return (Criteria) this;
        }

        public Criteria andSecretaryNameLessThan(String value) {
            addCriterion("SECRETARY_NAME <", value, "secretaryName");
            return (Criteria) this;
        }

        public Criteria andSecretaryNameLessThanOrEqualTo(String value) {
            addCriterion("SECRETARY_NAME <=", value, "secretaryName");
            return (Criteria) this;
        }

        public Criteria andSecretaryNameLike(String value) {
            addCriterion("SECRETARY_NAME like", value, "secretaryName");
            return (Criteria) this;
        }

        public Criteria andSecretaryNameNotLike(String value) {
            addCriterion("SECRETARY_NAME not like", value, "secretaryName");
            return (Criteria) this;
        }

        public Criteria andSecretaryNameIn(List<String> values) {
            addCriterion("SECRETARY_NAME in", values, "secretaryName");
            return (Criteria) this;
        }

        public Criteria andSecretaryNameNotIn(List<String> values) {
            addCriterion("SECRETARY_NAME not in", values, "secretaryName");
            return (Criteria) this;
        }

        public Criteria andSecretaryNameBetween(String value1, String value2) {
            addCriterion("SECRETARY_NAME between", value1, value2, "secretaryName");
            return (Criteria) this;
        }

        public Criteria andSecretaryNameNotBetween(String value1, String value2) {
            addCriterion("SECRETARY_NAME not between", value1, value2, "secretaryName");
            return (Criteria) this;
        }

        public Criteria andSchStartDateIsNull() {
            addCriterion("SCH_START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSchStartDateIsNotNull() {
            addCriterion("SCH_START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSchStartDateEqualTo(String value) {
            addCriterion("SCH_START_DATE =", value, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateNotEqualTo(String value) {
            addCriterion("SCH_START_DATE <>", value, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateGreaterThan(String value) {
            addCriterion("SCH_START_DATE >", value, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_START_DATE >=", value, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateLessThan(String value) {
            addCriterion("SCH_START_DATE <", value, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateLessThanOrEqualTo(String value) {
            addCriterion("SCH_START_DATE <=", value, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateLike(String value) {
            addCriterion("SCH_START_DATE like", value, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateNotLike(String value) {
            addCriterion("SCH_START_DATE not like", value, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateIn(List<String> values) {
            addCriterion("SCH_START_DATE in", values, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateNotIn(List<String> values) {
            addCriterion("SCH_START_DATE not in", values, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateBetween(String value1, String value2) {
            addCriterion("SCH_START_DATE between", value1, value2, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateNotBetween(String value1, String value2) {
            addCriterion("SCH_START_DATE not between", value1, value2, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateIsNull() {
            addCriterion("SCH_END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSchEndDateIsNotNull() {
            addCriterion("SCH_END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSchEndDateEqualTo(String value) {
            addCriterion("SCH_END_DATE =", value, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateNotEqualTo(String value) {
            addCriterion("SCH_END_DATE <>", value, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateGreaterThan(String value) {
            addCriterion("SCH_END_DATE >", value, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_END_DATE >=", value, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateLessThan(String value) {
            addCriterion("SCH_END_DATE <", value, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateLessThanOrEqualTo(String value) {
            addCriterion("SCH_END_DATE <=", value, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateLike(String value) {
            addCriterion("SCH_END_DATE like", value, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateNotLike(String value) {
            addCriterion("SCH_END_DATE not like", value, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateIn(List<String> values) {
            addCriterion("SCH_END_DATE in", values, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateNotIn(List<String> values) {
            addCriterion("SCH_END_DATE not in", values, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateBetween(String value1, String value2) {
            addCriterion("SCH_END_DATE between", value1, value2, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateNotBetween(String value1, String value2) {
            addCriterion("SCH_END_DATE not between", value1, value2, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("MEMO is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("MEMO =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("MEMO <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("MEMO >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("MEMO >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("MEMO <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("MEMO <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("MEMO like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("MEMO not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("MEMO in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("MEMO not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("MEMO between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("MEMO not between", value1, value2, "memo");
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