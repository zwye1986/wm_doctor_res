package com.pinde.sci.model.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ResScoreExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResScoreExample() {
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

        public Criteria andScoreFlowIsNull() {
            addCriterion("SCORE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andScoreFlowIsNotNull() {
            addCriterion("SCORE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andScoreFlowEqualTo(String value) {
            addCriterion("SCORE_FLOW =", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowNotEqualTo(String value) {
            addCriterion("SCORE_FLOW <>", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowGreaterThan(String value) {
            addCriterion("SCORE_FLOW >", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_FLOW >=", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowLessThan(String value) {
            addCriterion("SCORE_FLOW <", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowLessThanOrEqualTo(String value) {
            addCriterion("SCORE_FLOW <=", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowLike(String value) {
            addCriterion("SCORE_FLOW like", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowNotLike(String value) {
            addCriterion("SCORE_FLOW not like", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowIn(List<String> values) {
            addCriterion("SCORE_FLOW in", values, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowNotIn(List<String> values) {
            addCriterion("SCORE_FLOW not in", values, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowBetween(String value1, String value2) {
            addCriterion("SCORE_FLOW between", value1, value2, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowNotBetween(String value1, String value2) {
            addCriterion("SCORE_FLOW not between", value1, value2, "scoreFlow");
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

        public Criteria andScoreTypeIdIsNull() {
            addCriterion("SCORE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdIsNotNull() {
            addCriterion("SCORE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdEqualTo(String value) {
            addCriterion("SCORE_TYPE_ID =", value, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdNotEqualTo(String value) {
            addCriterion("SCORE_TYPE_ID <>", value, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdGreaterThan(String value) {
            addCriterion("SCORE_TYPE_ID >", value, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_TYPE_ID >=", value, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdLessThan(String value) {
            addCriterion("SCORE_TYPE_ID <", value, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdLessThanOrEqualTo(String value) {
            addCriterion("SCORE_TYPE_ID <=", value, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdLike(String value) {
            addCriterion("SCORE_TYPE_ID like", value, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdNotLike(String value) {
            addCriterion("SCORE_TYPE_ID not like", value, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdIn(List<String> values) {
            addCriterion("SCORE_TYPE_ID in", values, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdNotIn(List<String> values) {
            addCriterion("SCORE_TYPE_ID not in", values, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdBetween(String value1, String value2) {
            addCriterion("SCORE_TYPE_ID between", value1, value2, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdNotBetween(String value1, String value2) {
            addCriterion("SCORE_TYPE_ID not between", value1, value2, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameIsNull() {
            addCriterion("SCORE_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameIsNotNull() {
            addCriterion("SCORE_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameEqualTo(String value) {
            addCriterion("SCORE_TYPE_NAME =", value, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameNotEqualTo(String value) {
            addCriterion("SCORE_TYPE_NAME <>", value, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameGreaterThan(String value) {
            addCriterion("SCORE_TYPE_NAME >", value, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_TYPE_NAME >=", value, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameLessThan(String value) {
            addCriterion("SCORE_TYPE_NAME <", value, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameLessThanOrEqualTo(String value) {
            addCriterion("SCORE_TYPE_NAME <=", value, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameLike(String value) {
            addCriterion("SCORE_TYPE_NAME like", value, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameNotLike(String value) {
            addCriterion("SCORE_TYPE_NAME not like", value, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameIn(List<String> values) {
            addCriterion("SCORE_TYPE_NAME in", values, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameNotIn(List<String> values) {
            addCriterion("SCORE_TYPE_NAME not in", values, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameBetween(String value1, String value2) {
            addCriterion("SCORE_TYPE_NAME between", value1, value2, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameNotBetween(String value1, String value2) {
            addCriterion("SCORE_TYPE_NAME not between", value1, value2, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andResultFlowIsNull() {
            addCriterion("RESULT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andResultFlowIsNotNull() {
            addCriterion("RESULT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andResultFlowEqualTo(String value) {
            addCriterion("RESULT_FLOW =", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowNotEqualTo(String value) {
            addCriterion("RESULT_FLOW <>", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowGreaterThan(String value) {
            addCriterion("RESULT_FLOW >", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RESULT_FLOW >=", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowLessThan(String value) {
            addCriterion("RESULT_FLOW <", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowLessThanOrEqualTo(String value) {
            addCriterion("RESULT_FLOW <=", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowLike(String value) {
            addCriterion("RESULT_FLOW like", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowNotLike(String value) {
            addCriterion("RESULT_FLOW not like", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowIn(List<String> values) {
            addCriterion("RESULT_FLOW in", values, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowNotIn(List<String> values) {
            addCriterion("RESULT_FLOW not in", values, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowBetween(String value1, String value2) {
            addCriterion("RESULT_FLOW between", value1, value2, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowNotBetween(String value1, String value2) {
            addCriterion("RESULT_FLOW not between", value1, value2, "resultFlow");
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

        public Criteria andScorePhaseIdIsNull() {
            addCriterion("SCORE_PHASE_ID is null");
            return (Criteria) this;
        }

        public Criteria andScorePhaseIdIsNotNull() {
            addCriterion("SCORE_PHASE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andScorePhaseIdEqualTo(String value) {
            addCriterion("SCORE_PHASE_ID =", value, "scorePhaseId");
            return (Criteria) this;
        }

        public Criteria andScorePhaseIdNotEqualTo(String value) {
            addCriterion("SCORE_PHASE_ID <>", value, "scorePhaseId");
            return (Criteria) this;
        }

        public Criteria andScorePhaseIdGreaterThan(String value) {
            addCriterion("SCORE_PHASE_ID >", value, "scorePhaseId");
            return (Criteria) this;
        }

        public Criteria andScorePhaseIdGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_PHASE_ID >=", value, "scorePhaseId");
            return (Criteria) this;
        }

        public Criteria andScorePhaseIdLessThan(String value) {
            addCriterion("SCORE_PHASE_ID <", value, "scorePhaseId");
            return (Criteria) this;
        }

        public Criteria andScorePhaseIdLessThanOrEqualTo(String value) {
            addCriterion("SCORE_PHASE_ID <=", value, "scorePhaseId");
            return (Criteria) this;
        }

        public Criteria andScorePhaseIdLike(String value) {
            addCriterion("SCORE_PHASE_ID like", value, "scorePhaseId");
            return (Criteria) this;
        }

        public Criteria andScorePhaseIdNotLike(String value) {
            addCriterion("SCORE_PHASE_ID not like", value, "scorePhaseId");
            return (Criteria) this;
        }

        public Criteria andScorePhaseIdIn(List<String> values) {
            addCriterion("SCORE_PHASE_ID in", values, "scorePhaseId");
            return (Criteria) this;
        }

        public Criteria andScorePhaseIdNotIn(List<String> values) {
            addCriterion("SCORE_PHASE_ID not in", values, "scorePhaseId");
            return (Criteria) this;
        }

        public Criteria andScorePhaseIdBetween(String value1, String value2) {
            addCriterion("SCORE_PHASE_ID between", value1, value2, "scorePhaseId");
            return (Criteria) this;
        }

        public Criteria andScorePhaseIdNotBetween(String value1, String value2) {
            addCriterion("SCORE_PHASE_ID not between", value1, value2, "scorePhaseId");
            return (Criteria) this;
        }

        public Criteria andScorePhaseNameIsNull() {
            addCriterion("SCORE_PHASE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andScorePhaseNameIsNotNull() {
            addCriterion("SCORE_PHASE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andScorePhaseNameEqualTo(String value) {
            addCriterion("SCORE_PHASE_NAME =", value, "scorePhaseName");
            return (Criteria) this;
        }

        public Criteria andScorePhaseNameNotEqualTo(String value) {
            addCriterion("SCORE_PHASE_NAME <>", value, "scorePhaseName");
            return (Criteria) this;
        }

        public Criteria andScorePhaseNameGreaterThan(String value) {
            addCriterion("SCORE_PHASE_NAME >", value, "scorePhaseName");
            return (Criteria) this;
        }

        public Criteria andScorePhaseNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_PHASE_NAME >=", value, "scorePhaseName");
            return (Criteria) this;
        }

        public Criteria andScorePhaseNameLessThan(String value) {
            addCriterion("SCORE_PHASE_NAME <", value, "scorePhaseName");
            return (Criteria) this;
        }

        public Criteria andScorePhaseNameLessThanOrEqualTo(String value) {
            addCriterion("SCORE_PHASE_NAME <=", value, "scorePhaseName");
            return (Criteria) this;
        }

        public Criteria andScorePhaseNameLike(String value) {
            addCriterion("SCORE_PHASE_NAME like", value, "scorePhaseName");
            return (Criteria) this;
        }

        public Criteria andScorePhaseNameNotLike(String value) {
            addCriterion("SCORE_PHASE_NAME not like", value, "scorePhaseName");
            return (Criteria) this;
        }

        public Criteria andScorePhaseNameIn(List<String> values) {
            addCriterion("SCORE_PHASE_NAME in", values, "scorePhaseName");
            return (Criteria) this;
        }

        public Criteria andScorePhaseNameNotIn(List<String> values) {
            addCriterion("SCORE_PHASE_NAME not in", values, "scorePhaseName");
            return (Criteria) this;
        }

        public Criteria andScorePhaseNameBetween(String value1, String value2) {
            addCriterion("SCORE_PHASE_NAME between", value1, value2, "scorePhaseName");
            return (Criteria) this;
        }

        public Criteria andScorePhaseNameNotBetween(String value1, String value2) {
            addCriterion("SCORE_PHASE_NAME not between", value1, value2, "scorePhaseName");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreIsNull() {
            addCriterion("THEORY_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreIsNotNull() {
            addCriterion("THEORY_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreEqualTo(BigDecimal value) {
            addCriterion("THEORY_SCORE =", value, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreNotEqualTo(BigDecimal value) {
            addCriterion("THEORY_SCORE <>", value, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreGreaterThan(BigDecimal value) {
            addCriterion("THEORY_SCORE >", value, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("THEORY_SCORE >=", value, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreLessThan(BigDecimal value) {
            addCriterion("THEORY_SCORE <", value, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("THEORY_SCORE <=", value, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreIn(List<BigDecimal> values) {
            addCriterion("THEORY_SCORE in", values, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreNotIn(List<BigDecimal> values) {
            addCriterion("THEORY_SCORE not in", values, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("THEORY_SCORE between", value1, value2, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("THEORY_SCORE not between", value1, value2, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreIsNull() {
            addCriterion("SKILL_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andSkillScoreIsNotNull() {
            addCriterion("SKILL_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andSkillScoreEqualTo(BigDecimal value) {
            addCriterion("SKILL_SCORE =", value, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreNotEqualTo(BigDecimal value) {
            addCriterion("SKILL_SCORE <>", value, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreGreaterThan(BigDecimal value) {
            addCriterion("SKILL_SCORE >", value, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SKILL_SCORE >=", value, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreLessThan(BigDecimal value) {
            addCriterion("SKILL_SCORE <", value, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SKILL_SCORE <=", value, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreIn(List<BigDecimal> values) {
            addCriterion("SKILL_SCORE in", values, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreNotIn(List<BigDecimal> values) {
            addCriterion("SKILL_SCORE not in", values, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SKILL_SCORE between", value1, value2, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SKILL_SCORE not between", value1, value2, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSchResultIdIsNull() {
            addCriterion("SCH_RESULT_ID is null");
            return (Criteria) this;
        }

        public Criteria andSchResultIdIsNotNull() {
            addCriterion("SCH_RESULT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSchResultIdEqualTo(String value) {
            addCriterion("SCH_RESULT_ID =", value, "schResultId");
            return (Criteria) this;
        }

        public Criteria andSchResultIdNotEqualTo(String value) {
            addCriterion("SCH_RESULT_ID <>", value, "schResultId");
            return (Criteria) this;
        }

        public Criteria andSchResultIdGreaterThan(String value) {
            addCriterion("SCH_RESULT_ID >", value, "schResultId");
            return (Criteria) this;
        }

        public Criteria andSchResultIdGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_RESULT_ID >=", value, "schResultId");
            return (Criteria) this;
        }

        public Criteria andSchResultIdLessThan(String value) {
            addCriterion("SCH_RESULT_ID <", value, "schResultId");
            return (Criteria) this;
        }

        public Criteria andSchResultIdLessThanOrEqualTo(String value) {
            addCriterion("SCH_RESULT_ID <=", value, "schResultId");
            return (Criteria) this;
        }

        public Criteria andSchResultIdLike(String value) {
            addCriterion("SCH_RESULT_ID like", value, "schResultId");
            return (Criteria) this;
        }

        public Criteria andSchResultIdNotLike(String value) {
            addCriterion("SCH_RESULT_ID not like", value, "schResultId");
            return (Criteria) this;
        }

        public Criteria andSchResultIdIn(List<String> values) {
            addCriterion("SCH_RESULT_ID in", values, "schResultId");
            return (Criteria) this;
        }

        public Criteria andSchResultIdNotIn(List<String> values) {
            addCriterion("SCH_RESULT_ID not in", values, "schResultId");
            return (Criteria) this;
        }

        public Criteria andSchResultIdBetween(String value1, String value2) {
            addCriterion("SCH_RESULT_ID between", value1, value2, "schResultId");
            return (Criteria) this;
        }

        public Criteria andSchResultIdNotBetween(String value1, String value2) {
            addCriterion("SCH_RESULT_ID not between", value1, value2, "schResultId");
            return (Criteria) this;
        }

        public Criteria andSchResultNameIsNull() {
            addCriterion("SCH_RESULT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSchResultNameIsNotNull() {
            addCriterion("SCH_RESULT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSchResultNameEqualTo(String value) {
            addCriterion("SCH_RESULT_NAME =", value, "schResultName");
            return (Criteria) this;
        }

        public Criteria andSchResultNameNotEqualTo(String value) {
            addCriterion("SCH_RESULT_NAME <>", value, "schResultName");
            return (Criteria) this;
        }

        public Criteria andSchResultNameGreaterThan(String value) {
            addCriterion("SCH_RESULT_NAME >", value, "schResultName");
            return (Criteria) this;
        }

        public Criteria andSchResultNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_RESULT_NAME >=", value, "schResultName");
            return (Criteria) this;
        }

        public Criteria andSchResultNameLessThan(String value) {
            addCriterion("SCH_RESULT_NAME <", value, "schResultName");
            return (Criteria) this;
        }

        public Criteria andSchResultNameLessThanOrEqualTo(String value) {
            addCriterion("SCH_RESULT_NAME <=", value, "schResultName");
            return (Criteria) this;
        }

        public Criteria andSchResultNameLike(String value) {
            addCriterion("SCH_RESULT_NAME like", value, "schResultName");
            return (Criteria) this;
        }

        public Criteria andSchResultNameNotLike(String value) {
            addCriterion("SCH_RESULT_NAME not like", value, "schResultName");
            return (Criteria) this;
        }

        public Criteria andSchResultNameIn(List<String> values) {
            addCriterion("SCH_RESULT_NAME in", values, "schResultName");
            return (Criteria) this;
        }

        public Criteria andSchResultNameNotIn(List<String> values) {
            addCriterion("SCH_RESULT_NAME not in", values, "schResultName");
            return (Criteria) this;
        }

        public Criteria andSchResultNameBetween(String value1, String value2) {
            addCriterion("SCH_RESULT_NAME between", value1, value2, "schResultName");
            return (Criteria) this;
        }

        public Criteria andSchResultNameNotBetween(String value1, String value2) {
            addCriterion("SCH_RESULT_NAME not between", value1, value2, "schResultName");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdIsNull() {
            addCriterion("SCORE_RESULT_ID is null");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdIsNotNull() {
            addCriterion("SCORE_RESULT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdEqualTo(String value) {
            addCriterion("SCORE_RESULT_ID =", value, "scoreResultId");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdNotEqualTo(String value) {
            addCriterion("SCORE_RESULT_ID <>", value, "scoreResultId");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdGreaterThan(String value) {
            addCriterion("SCORE_RESULT_ID >", value, "scoreResultId");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_RESULT_ID >=", value, "scoreResultId");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdLessThan(String value) {
            addCriterion("SCORE_RESULT_ID <", value, "scoreResultId");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdLessThanOrEqualTo(String value) {
            addCriterion("SCORE_RESULT_ID <=", value, "scoreResultId");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdLike(String value) {
            addCriterion("SCORE_RESULT_ID like", value, "scoreResultId");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdNotLike(String value) {
            addCriterion("SCORE_RESULT_ID not like", value, "scoreResultId");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdIn(List<String> values) {
            addCriterion("SCORE_RESULT_ID in", values, "scoreResultId");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdNotIn(List<String> values) {
            addCriterion("SCORE_RESULT_ID not in", values, "scoreResultId");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdBetween(String value1, String value2) {
            addCriterion("SCORE_RESULT_ID between", value1, value2, "scoreResultId");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdNotBetween(String value1, String value2) {
            addCriterion("SCORE_RESULT_ID not between", value1, value2, "scoreResultId");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameIsNull() {
            addCriterion("SCORE_RESULT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameIsNotNull() {
            addCriterion("SCORE_RESULT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameEqualTo(String value) {
            addCriterion("SCORE_RESULT_NAME =", value, "scoreResultName");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameNotEqualTo(String value) {
            addCriterion("SCORE_RESULT_NAME <>", value, "scoreResultName");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameGreaterThan(String value) {
            addCriterion("SCORE_RESULT_NAME >", value, "scoreResultName");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_RESULT_NAME >=", value, "scoreResultName");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameLessThan(String value) {
            addCriterion("SCORE_RESULT_NAME <", value, "scoreResultName");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameLessThanOrEqualTo(String value) {
            addCriterion("SCORE_RESULT_NAME <=", value, "scoreResultName");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameLike(String value) {
            addCriterion("SCORE_RESULT_NAME like", value, "scoreResultName");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameNotLike(String value) {
            addCriterion("SCORE_RESULT_NAME not like", value, "scoreResultName");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameIn(List<String> values) {
            addCriterion("SCORE_RESULT_NAME in", values, "scoreResultName");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameNotIn(List<String> values) {
            addCriterion("SCORE_RESULT_NAME not in", values, "scoreResultName");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameBetween(String value1, String value2) {
            addCriterion("SCORE_RESULT_NAME between", value1, value2, "scoreResultName");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameNotBetween(String value1, String value2) {
            addCriterion("SCORE_RESULT_NAME not between", value1, value2, "scoreResultName");
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

        public Criteria andPaperFlowIsNull() {
            addCriterion("PAPER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andPaperFlowIsNotNull() {
            addCriterion("PAPER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andPaperFlowEqualTo(String value) {
            addCriterion("PAPER_FLOW =", value, "paperFlow");
            return (Criteria) this;
        }

        public Criteria andPaperFlowNotEqualTo(String value) {
            addCriterion("PAPER_FLOW <>", value, "paperFlow");
            return (Criteria) this;
        }

        public Criteria andPaperFlowGreaterThan(String value) {
            addCriterion("PAPER_FLOW >", value, "paperFlow");
            return (Criteria) this;
        }

        public Criteria andPaperFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER_FLOW >=", value, "paperFlow");
            return (Criteria) this;
        }

        public Criteria andPaperFlowLessThan(String value) {
            addCriterion("PAPER_FLOW <", value, "paperFlow");
            return (Criteria) this;
        }

        public Criteria andPaperFlowLessThanOrEqualTo(String value) {
            addCriterion("PAPER_FLOW <=", value, "paperFlow");
            return (Criteria) this;
        }

        public Criteria andPaperFlowLike(String value) {
            addCriterion("PAPER_FLOW like", value, "paperFlow");
            return (Criteria) this;
        }

        public Criteria andPaperFlowNotLike(String value) {
            addCriterion("PAPER_FLOW not like", value, "paperFlow");
            return (Criteria) this;
        }

        public Criteria andPaperFlowIn(List<String> values) {
            addCriterion("PAPER_FLOW in", values, "paperFlow");
            return (Criteria) this;
        }

        public Criteria andPaperFlowNotIn(List<String> values) {
            addCriterion("PAPER_FLOW not in", values, "paperFlow");
            return (Criteria) this;
        }

        public Criteria andPaperFlowBetween(String value1, String value2) {
            addCriterion("PAPER_FLOW between", value1, value2, "paperFlow");
            return (Criteria) this;
        }

        public Criteria andPaperFlowNotBetween(String value1, String value2) {
            addCriterion("PAPER_FLOW not between", value1, value2, "paperFlow");
            return (Criteria) this;
        }

        public Criteria andMarScoreIsNull() {
            addCriterion("MAR_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andMarScoreIsNotNull() {
            addCriterion("MAR_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andMarScoreEqualTo(BigDecimal value) {
            addCriterion("MAR_SCORE =", value, "marScore");
            return (Criteria) this;
        }

        public Criteria andMarScoreNotEqualTo(BigDecimal value) {
            addCriterion("MAR_SCORE <>", value, "marScore");
            return (Criteria) this;
        }

        public Criteria andMarScoreGreaterThan(BigDecimal value) {
            addCriterion("MAR_SCORE >", value, "marScore");
            return (Criteria) this;
        }

        public Criteria andMarScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MAR_SCORE >=", value, "marScore");
            return (Criteria) this;
        }

        public Criteria andMarScoreLessThan(BigDecimal value) {
            addCriterion("MAR_SCORE <", value, "marScore");
            return (Criteria) this;
        }

        public Criteria andMarScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MAR_SCORE <=", value, "marScore");
            return (Criteria) this;
        }

        public Criteria andMarScoreIn(List<BigDecimal> values) {
            addCriterion("MAR_SCORE in", values, "marScore");
            return (Criteria) this;
        }

        public Criteria andMarScoreNotIn(List<BigDecimal> values) {
            addCriterion("MAR_SCORE not in", values, "marScore");
            return (Criteria) this;
        }

        public Criteria andMarScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MAR_SCORE between", value1, value2, "marScore");
            return (Criteria) this;
        }

        public Criteria andMarScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MAR_SCORE not between", value1, value2, "marScore");
            return (Criteria) this;
        }

        public Criteria andJunScoreIsNull() {
            addCriterion("JUN_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andJunScoreIsNotNull() {
            addCriterion("JUN_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andJunScoreEqualTo(BigDecimal value) {
            addCriterion("JUN_SCORE =", value, "junScore");
            return (Criteria) this;
        }

        public Criteria andJunScoreNotEqualTo(BigDecimal value) {
            addCriterion("JUN_SCORE <>", value, "junScore");
            return (Criteria) this;
        }

        public Criteria andJunScoreGreaterThan(BigDecimal value) {
            addCriterion("JUN_SCORE >", value, "junScore");
            return (Criteria) this;
        }

        public Criteria andJunScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("JUN_SCORE >=", value, "junScore");
            return (Criteria) this;
        }

        public Criteria andJunScoreLessThan(BigDecimal value) {
            addCriterion("JUN_SCORE <", value, "junScore");
            return (Criteria) this;
        }

        public Criteria andJunScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("JUN_SCORE <=", value, "junScore");
            return (Criteria) this;
        }

        public Criteria andJunScoreIn(List<BigDecimal> values) {
            addCriterion("JUN_SCORE in", values, "junScore");
            return (Criteria) this;
        }

        public Criteria andJunScoreNotIn(List<BigDecimal> values) {
            addCriterion("JUN_SCORE not in", values, "junScore");
            return (Criteria) this;
        }

        public Criteria andJunScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("JUN_SCORE between", value1, value2, "junScore");
            return (Criteria) this;
        }

        public Criteria andJunScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("JUN_SCORE not between", value1, value2, "junScore");
            return (Criteria) this;
        }

        public Criteria andOctScoreIsNull() {
            addCriterion("OCT_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andOctScoreIsNotNull() {
            addCriterion("OCT_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andOctScoreEqualTo(BigDecimal value) {
            addCriterion("OCT_SCORE =", value, "octScore");
            return (Criteria) this;
        }

        public Criteria andOctScoreNotEqualTo(BigDecimal value) {
            addCriterion("OCT_SCORE <>", value, "octScore");
            return (Criteria) this;
        }

        public Criteria andOctScoreGreaterThan(BigDecimal value) {
            addCriterion("OCT_SCORE >", value, "octScore");
            return (Criteria) this;
        }

        public Criteria andOctScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("OCT_SCORE >=", value, "octScore");
            return (Criteria) this;
        }

        public Criteria andOctScoreLessThan(BigDecimal value) {
            addCriterion("OCT_SCORE <", value, "octScore");
            return (Criteria) this;
        }

        public Criteria andOctScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("OCT_SCORE <=", value, "octScore");
            return (Criteria) this;
        }

        public Criteria andOctScoreIn(List<BigDecimal> values) {
            addCriterion("OCT_SCORE in", values, "octScore");
            return (Criteria) this;
        }

        public Criteria andOctScoreNotIn(List<BigDecimal> values) {
            addCriterion("OCT_SCORE not in", values, "octScore");
            return (Criteria) this;
        }

        public Criteria andOctScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OCT_SCORE between", value1, value2, "octScore");
            return (Criteria) this;
        }

        public Criteria andOctScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("OCT_SCORE not between", value1, value2, "octScore");
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

        public Criteria andIsSendIsNull() {
            addCriterion("IS_SEND is null");
            return (Criteria) this;
        }

        public Criteria andIsSendIsNotNull() {
            addCriterion("IS_SEND is not null");
            return (Criteria) this;
        }

        public Criteria andIsSendEqualTo(String value) {
            addCriterion("IS_SEND =", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendNotEqualTo(String value) {
            addCriterion("IS_SEND <>", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendGreaterThan(String value) {
            addCriterion("IS_SEND >", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SEND >=", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendLessThan(String value) {
            addCriterion("IS_SEND <", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendLessThanOrEqualTo(String value) {
            addCriterion("IS_SEND <=", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendLike(String value) {
            addCriterion("IS_SEND like", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendNotLike(String value) {
            addCriterion("IS_SEND not like", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendIn(List<String> values) {
            addCriterion("IS_SEND in", values, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendNotIn(List<String> values) {
            addCriterion("IS_SEND not in", values, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendBetween(String value1, String value2) {
            addCriterion("IS_SEND between", value1, value2, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendNotBetween(String value1, String value2) {
            addCriterion("IS_SEND not between", value1, value2, "isSend");
            return (Criteria) this;
        }

        public Criteria andTestIdIsNull() {
            addCriterion("TEST_ID is null");
            return (Criteria) this;
        }

        public Criteria andTestIdIsNotNull() {
            addCriterion("TEST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTestIdEqualTo(String value) {
            addCriterion("TEST_ID =", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotEqualTo(String value) {
            addCriterion("TEST_ID <>", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdGreaterThan(String value) {
            addCriterion("TEST_ID >", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdGreaterThanOrEqualTo(String value) {
            addCriterion("TEST_ID >=", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdLessThan(String value) {
            addCriterion("TEST_ID <", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdLessThanOrEqualTo(String value) {
            addCriterion("TEST_ID <=", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdLike(String value) {
            addCriterion("TEST_ID like", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotLike(String value) {
            addCriterion("TEST_ID not like", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdIn(List<String> values) {
            addCriterion("TEST_ID in", values, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotIn(List<String> values) {
            addCriterion("TEST_ID not in", values, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdBetween(String value1, String value2) {
            addCriterion("TEST_ID between", value1, value2, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotBetween(String value1, String value2) {
            addCriterion("TEST_ID not between", value1, value2, "testId");
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

        public Criteria andIsAffirmIdIsNull() {
            addCriterion("IS_AFFIRM_ID is null");
            return (Criteria) this;
        }

        public Criteria andIsAffirmIdIsNotNull() {
            addCriterion("IS_AFFIRM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andIsAffirmIdEqualTo(String value) {
            addCriterion("IS_AFFIRM_ID =", value, "isAffirmId");
            return (Criteria) this;
        }

        public Criteria andIsAffirmIdNotEqualTo(String value) {
            addCriterion("IS_AFFIRM_ID <>", value, "isAffirmId");
            return (Criteria) this;
        }

        public Criteria andIsAffirmIdGreaterThan(String value) {
            addCriterion("IS_AFFIRM_ID >", value, "isAffirmId");
            return (Criteria) this;
        }

        public Criteria andIsAffirmIdGreaterThanOrEqualTo(String value) {
            addCriterion("IS_AFFIRM_ID >=", value, "isAffirmId");
            return (Criteria) this;
        }

        public Criteria andIsAffirmIdLessThan(String value) {
            addCriterion("IS_AFFIRM_ID <", value, "isAffirmId");
            return (Criteria) this;
        }

        public Criteria andIsAffirmIdLessThanOrEqualTo(String value) {
            addCriterion("IS_AFFIRM_ID <=", value, "isAffirmId");
            return (Criteria) this;
        }

        public Criteria andIsAffirmIdLike(String value) {
            addCriterion("IS_AFFIRM_ID like", value, "isAffirmId");
            return (Criteria) this;
        }

        public Criteria andIsAffirmIdNotLike(String value) {
            addCriterion("IS_AFFIRM_ID not like", value, "isAffirmId");
            return (Criteria) this;
        }

        public Criteria andIsAffirmIdIn(List<String> values) {
            addCriterion("IS_AFFIRM_ID in", values, "isAffirmId");
            return (Criteria) this;
        }

        public Criteria andIsAffirmIdNotIn(List<String> values) {
            addCriterion("IS_AFFIRM_ID not in", values, "isAffirmId");
            return (Criteria) this;
        }

        public Criteria andIsAffirmIdBetween(String value1, String value2) {
            addCriterion("IS_AFFIRM_ID between", value1, value2, "isAffirmId");
            return (Criteria) this;
        }

        public Criteria andIsAffirmIdNotBetween(String value1, String value2) {
            addCriterion("IS_AFFIRM_ID not between", value1, value2, "isAffirmId");
            return (Criteria) this;
        }

        public Criteria andIsAffirmNameIsNull() {
            addCriterion("IS_AFFIRM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andIsAffirmNameIsNotNull() {
            addCriterion("IS_AFFIRM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andIsAffirmNameEqualTo(String value) {
            addCriterion("IS_AFFIRM_NAME =", value, "isAffirmName");
            return (Criteria) this;
        }

        public Criteria andIsAffirmNameNotEqualTo(String value) {
            addCriterion("IS_AFFIRM_NAME <>", value, "isAffirmName");
            return (Criteria) this;
        }

        public Criteria andIsAffirmNameGreaterThan(String value) {
            addCriterion("IS_AFFIRM_NAME >", value, "isAffirmName");
            return (Criteria) this;
        }

        public Criteria andIsAffirmNameGreaterThanOrEqualTo(String value) {
            addCriterion("IS_AFFIRM_NAME >=", value, "isAffirmName");
            return (Criteria) this;
        }

        public Criteria andIsAffirmNameLessThan(String value) {
            addCriterion("IS_AFFIRM_NAME <", value, "isAffirmName");
            return (Criteria) this;
        }

        public Criteria andIsAffirmNameLessThanOrEqualTo(String value) {
            addCriterion("IS_AFFIRM_NAME <=", value, "isAffirmName");
            return (Criteria) this;
        }

        public Criteria andIsAffirmNameLike(String value) {
            addCriterion("IS_AFFIRM_NAME like", value, "isAffirmName");
            return (Criteria) this;
        }

        public Criteria andIsAffirmNameNotLike(String value) {
            addCriterion("IS_AFFIRM_NAME not like", value, "isAffirmName");
            return (Criteria) this;
        }

        public Criteria andIsAffirmNameIn(List<String> values) {
            addCriterion("IS_AFFIRM_NAME in", values, "isAffirmName");
            return (Criteria) this;
        }

        public Criteria andIsAffirmNameNotIn(List<String> values) {
            addCriterion("IS_AFFIRM_NAME not in", values, "isAffirmName");
            return (Criteria) this;
        }

        public Criteria andIsAffirmNameBetween(String value1, String value2) {
            addCriterion("IS_AFFIRM_NAME between", value1, value2, "isAffirmName");
            return (Criteria) this;
        }

        public Criteria andIsAffirmNameNotBetween(String value1, String value2) {
            addCriterion("IS_AFFIRM_NAME not between", value1, value2, "isAffirmName");
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