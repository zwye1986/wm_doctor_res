package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResDoctorLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResDoctorLogExample() {
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

        public Criteria andDoctorCodeIsNull() {
            addCriterion("DOCTOR_CODE is null");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeIsNotNull() {
            addCriterion("DOCTOR_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeEqualTo(String value) {
            addCriterion("DOCTOR_CODE =", value, "doctorCode");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeNotEqualTo(String value) {
            addCriterion("DOCTOR_CODE <>", value, "doctorCode");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeGreaterThan(String value) {
            addCriterion("DOCTOR_CODE >", value, "doctorCode");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_CODE >=", value, "doctorCode");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeLessThan(String value) {
            addCriterion("DOCTOR_CODE <", value, "doctorCode");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_CODE <=", value, "doctorCode");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeLike(String value) {
            addCriterion("DOCTOR_CODE like", value, "doctorCode");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeNotLike(String value) {
            addCriterion("DOCTOR_CODE not like", value, "doctorCode");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeIn(List<String> values) {
            addCriterion("DOCTOR_CODE in", values, "doctorCode");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeNotIn(List<String> values) {
            addCriterion("DOCTOR_CODE not in", values, "doctorCode");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeBetween(String value1, String value2) {
            addCriterion("DOCTOR_CODE between", value1, value2, "doctorCode");
            return (Criteria) this;
        }

        public Criteria andDoctorCodeNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_CODE not between", value1, value2, "doctorCode");
            return (Criteria) this;
        }

        public Criteria andDoctorHeadImgIsNull() {
            addCriterion("DOCTOR_HEAD_IMG is null");
            return (Criteria) this;
        }

        public Criteria andDoctorHeadImgIsNotNull() {
            addCriterion("DOCTOR_HEAD_IMG is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorHeadImgEqualTo(String value) {
            addCriterion("DOCTOR_HEAD_IMG =", value, "doctorHeadImg");
            return (Criteria) this;
        }

        public Criteria andDoctorHeadImgNotEqualTo(String value) {
            addCriterion("DOCTOR_HEAD_IMG <>", value, "doctorHeadImg");
            return (Criteria) this;
        }

        public Criteria andDoctorHeadImgGreaterThan(String value) {
            addCriterion("DOCTOR_HEAD_IMG >", value, "doctorHeadImg");
            return (Criteria) this;
        }

        public Criteria andDoctorHeadImgGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_HEAD_IMG >=", value, "doctorHeadImg");
            return (Criteria) this;
        }

        public Criteria andDoctorHeadImgLessThan(String value) {
            addCriterion("DOCTOR_HEAD_IMG <", value, "doctorHeadImg");
            return (Criteria) this;
        }

        public Criteria andDoctorHeadImgLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_HEAD_IMG <=", value, "doctorHeadImg");
            return (Criteria) this;
        }

        public Criteria andDoctorHeadImgLike(String value) {
            addCriterion("DOCTOR_HEAD_IMG like", value, "doctorHeadImg");
            return (Criteria) this;
        }

        public Criteria andDoctorHeadImgNotLike(String value) {
            addCriterion("DOCTOR_HEAD_IMG not like", value, "doctorHeadImg");
            return (Criteria) this;
        }

        public Criteria andDoctorHeadImgIn(List<String> values) {
            addCriterion("DOCTOR_HEAD_IMG in", values, "doctorHeadImg");
            return (Criteria) this;
        }

        public Criteria andDoctorHeadImgNotIn(List<String> values) {
            addCriterion("DOCTOR_HEAD_IMG not in", values, "doctorHeadImg");
            return (Criteria) this;
        }

        public Criteria andDoctorHeadImgBetween(String value1, String value2) {
            addCriterion("DOCTOR_HEAD_IMG between", value1, value2, "doctorHeadImg");
            return (Criteria) this;
        }

        public Criteria andDoctorHeadImgNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_HEAD_IMG not between", value1, value2, "doctorHeadImg");
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

        public Criteria andDoctorTypeNameIsNull() {
            addCriterion("DOCTOR_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameIsNotNull() {
            addCriterion("DOCTOR_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_NAME =", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameNotEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_NAME <>", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameGreaterThan(String value) {
            addCriterion("DOCTOR_TYPE_NAME >", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_NAME >=", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameLessThan(String value) {
            addCriterion("DOCTOR_TYPE_NAME <", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_NAME <=", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameLike(String value) {
            addCriterion("DOCTOR_TYPE_NAME like", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameNotLike(String value) {
            addCriterion("DOCTOR_TYPE_NAME not like", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameIn(List<String> values) {
            addCriterion("DOCTOR_TYPE_NAME in", values, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameNotIn(List<String> values) {
            addCriterion("DOCTOR_TYPE_NAME not in", values, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameBetween(String value1, String value2) {
            addCriterion("DOCTOR_TYPE_NAME between", value1, value2, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_TYPE_NAME not between", value1, value2, "doctorTypeName");
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

        public Criteria andWorkOrgNameIsNull() {
            addCriterion("WORK_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameIsNotNull() {
            addCriterion("WORK_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameEqualTo(String value) {
            addCriterion("WORK_ORG_NAME =", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameNotEqualTo(String value) {
            addCriterion("WORK_ORG_NAME <>", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameGreaterThan(String value) {
            addCriterion("WORK_ORG_NAME >", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_ORG_NAME >=", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameLessThan(String value) {
            addCriterion("WORK_ORG_NAME <", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameLessThanOrEqualTo(String value) {
            addCriterion("WORK_ORG_NAME <=", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameLike(String value) {
            addCriterion("WORK_ORG_NAME like", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameNotLike(String value) {
            addCriterion("WORK_ORG_NAME not like", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameIn(List<String> values) {
            addCriterion("WORK_ORG_NAME in", values, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameNotIn(List<String> values) {
            addCriterion("WORK_ORG_NAME not in", values, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameBetween(String value1, String value2) {
            addCriterion("WORK_ORG_NAME between", value1, value2, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameNotBetween(String value1, String value2) {
            addCriterion("WORK_ORG_NAME not between", value1, value2, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andDispatchFileIsNull() {
            addCriterion("DISPATCH_FILE is null");
            return (Criteria) this;
        }

        public Criteria andDispatchFileIsNotNull() {
            addCriterion("DISPATCH_FILE is not null");
            return (Criteria) this;
        }

        public Criteria andDispatchFileEqualTo(String value) {
            addCriterion("DISPATCH_FILE =", value, "dispatchFile");
            return (Criteria) this;
        }

        public Criteria andDispatchFileNotEqualTo(String value) {
            addCriterion("DISPATCH_FILE <>", value, "dispatchFile");
            return (Criteria) this;
        }

        public Criteria andDispatchFileGreaterThan(String value) {
            addCriterion("DISPATCH_FILE >", value, "dispatchFile");
            return (Criteria) this;
        }

        public Criteria andDispatchFileGreaterThanOrEqualTo(String value) {
            addCriterion("DISPATCH_FILE >=", value, "dispatchFile");
            return (Criteria) this;
        }

        public Criteria andDispatchFileLessThan(String value) {
            addCriterion("DISPATCH_FILE <", value, "dispatchFile");
            return (Criteria) this;
        }

        public Criteria andDispatchFileLessThanOrEqualTo(String value) {
            addCriterion("DISPATCH_FILE <=", value, "dispatchFile");
            return (Criteria) this;
        }

        public Criteria andDispatchFileLike(String value) {
            addCriterion("DISPATCH_FILE like", value, "dispatchFile");
            return (Criteria) this;
        }

        public Criteria andDispatchFileNotLike(String value) {
            addCriterion("DISPATCH_FILE not like", value, "dispatchFile");
            return (Criteria) this;
        }

        public Criteria andDispatchFileIn(List<String> values) {
            addCriterion("DISPATCH_FILE in", values, "dispatchFile");
            return (Criteria) this;
        }

        public Criteria andDispatchFileNotIn(List<String> values) {
            addCriterion("DISPATCH_FILE not in", values, "dispatchFile");
            return (Criteria) this;
        }

        public Criteria andDispatchFileBetween(String value1, String value2) {
            addCriterion("DISPATCH_FILE between", value1, value2, "dispatchFile");
            return (Criteria) this;
        }

        public Criteria andDispatchFileNotBetween(String value1, String value2) {
            addCriterion("DISPATCH_FILE not between", value1, value2, "dispatchFile");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdIsNull() {
            addCriterion("GRADUATED_ID is null");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdIsNotNull() {
            addCriterion("GRADUATED_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdEqualTo(String value) {
            addCriterion("GRADUATED_ID =", value, "graduatedId");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdNotEqualTo(String value) {
            addCriterion("GRADUATED_ID <>", value, "graduatedId");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdGreaterThan(String value) {
            addCriterion("GRADUATED_ID >", value, "graduatedId");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATED_ID >=", value, "graduatedId");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdLessThan(String value) {
            addCriterion("GRADUATED_ID <", value, "graduatedId");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdLessThanOrEqualTo(String value) {
            addCriterion("GRADUATED_ID <=", value, "graduatedId");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdLike(String value) {
            addCriterion("GRADUATED_ID like", value, "graduatedId");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdNotLike(String value) {
            addCriterion("GRADUATED_ID not like", value, "graduatedId");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdIn(List<String> values) {
            addCriterion("GRADUATED_ID in", values, "graduatedId");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdNotIn(List<String> values) {
            addCriterion("GRADUATED_ID not in", values, "graduatedId");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdBetween(String value1, String value2) {
            addCriterion("GRADUATED_ID between", value1, value2, "graduatedId");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdNotBetween(String value1, String value2) {
            addCriterion("GRADUATED_ID not between", value1, value2, "graduatedId");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameIsNull() {
            addCriterion("GRADUATED_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameIsNotNull() {
            addCriterion("GRADUATED_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameEqualTo(String value) {
            addCriterion("GRADUATED_NAME =", value, "graduatedName");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameNotEqualTo(String value) {
            addCriterion("GRADUATED_NAME <>", value, "graduatedName");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameGreaterThan(String value) {
            addCriterion("GRADUATED_NAME >", value, "graduatedName");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATED_NAME >=", value, "graduatedName");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameLessThan(String value) {
            addCriterion("GRADUATED_NAME <", value, "graduatedName");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameLessThanOrEqualTo(String value) {
            addCriterion("GRADUATED_NAME <=", value, "graduatedName");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameLike(String value) {
            addCriterion("GRADUATED_NAME like", value, "graduatedName");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameNotLike(String value) {
            addCriterion("GRADUATED_NAME not like", value, "graduatedName");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameIn(List<String> values) {
            addCriterion("GRADUATED_NAME in", values, "graduatedName");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameNotIn(List<String> values) {
            addCriterion("GRADUATED_NAME not in", values, "graduatedName");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameBetween(String value1, String value2) {
            addCriterion("GRADUATED_NAME between", value1, value2, "graduatedName");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameNotBetween(String value1, String value2) {
            addCriterion("GRADUATED_NAME not between", value1, value2, "graduatedName");
            return (Criteria) this;
        }

        public Criteria andSpecializedIsNull() {
            addCriterion("SPECIALIZED is null");
            return (Criteria) this;
        }

        public Criteria andSpecializedIsNotNull() {
            addCriterion("SPECIALIZED is not null");
            return (Criteria) this;
        }

        public Criteria andSpecializedEqualTo(String value) {
            addCriterion("SPECIALIZED =", value, "specialized");
            return (Criteria) this;
        }

        public Criteria andSpecializedNotEqualTo(String value) {
            addCriterion("SPECIALIZED <>", value, "specialized");
            return (Criteria) this;
        }

        public Criteria andSpecializedGreaterThan(String value) {
            addCriterion("SPECIALIZED >", value, "specialized");
            return (Criteria) this;
        }

        public Criteria andSpecializedGreaterThanOrEqualTo(String value) {
            addCriterion("SPECIALIZED >=", value, "specialized");
            return (Criteria) this;
        }

        public Criteria andSpecializedLessThan(String value) {
            addCriterion("SPECIALIZED <", value, "specialized");
            return (Criteria) this;
        }

        public Criteria andSpecializedLessThanOrEqualTo(String value) {
            addCriterion("SPECIALIZED <=", value, "specialized");
            return (Criteria) this;
        }

        public Criteria andSpecializedLike(String value) {
            addCriterion("SPECIALIZED like", value, "specialized");
            return (Criteria) this;
        }

        public Criteria andSpecializedNotLike(String value) {
            addCriterion("SPECIALIZED not like", value, "specialized");
            return (Criteria) this;
        }

        public Criteria andSpecializedIn(List<String> values) {
            addCriterion("SPECIALIZED in", values, "specialized");
            return (Criteria) this;
        }

        public Criteria andSpecializedNotIn(List<String> values) {
            addCriterion("SPECIALIZED not in", values, "specialized");
            return (Criteria) this;
        }

        public Criteria andSpecializedBetween(String value1, String value2) {
            addCriterion("SPECIALIZED between", value1, value2, "specialized");
            return (Criteria) this;
        }

        public Criteria andSpecializedNotBetween(String value1, String value2) {
            addCriterion("SPECIALIZED not between", value1, value2, "specialized");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeIsNull() {
            addCriterion("GRADUATION_TIME is null");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeIsNotNull() {
            addCriterion("GRADUATION_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeEqualTo(String value) {
            addCriterion("GRADUATION_TIME =", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeNotEqualTo(String value) {
            addCriterion("GRADUATION_TIME <>", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeGreaterThan(String value) {
            addCriterion("GRADUATION_TIME >", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATION_TIME >=", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeLessThan(String value) {
            addCriterion("GRADUATION_TIME <", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeLessThanOrEqualTo(String value) {
            addCriterion("GRADUATION_TIME <=", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeLike(String value) {
            addCriterion("GRADUATION_TIME like", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeNotLike(String value) {
            addCriterion("GRADUATION_TIME not like", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeIn(List<String> values) {
            addCriterion("GRADUATION_TIME in", values, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeNotIn(List<String> values) {
            addCriterion("GRADUATION_TIME not in", values, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeBetween(String value1, String value2) {
            addCriterion("GRADUATION_TIME between", value1, value2, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeNotBetween(String value1, String value2) {
            addCriterion("GRADUATION_TIME not between", value1, value2, "graduationTime");
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

        public Criteria andCertificateNoIsNull() {
            addCriterion("CERTIFICATE_NO is null");
            return (Criteria) this;
        }

        public Criteria andCertificateNoIsNotNull() {
            addCriterion("CERTIFICATE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateNoEqualTo(String value) {
            addCriterion("CERTIFICATE_NO =", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoNotEqualTo(String value) {
            addCriterion("CERTIFICATE_NO <>", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoGreaterThan(String value) {
            addCriterion("CERTIFICATE_NO >", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_NO >=", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoLessThan(String value) {
            addCriterion("CERTIFICATE_NO <", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_NO <=", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoLike(String value) {
            addCriterion("CERTIFICATE_NO like", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoNotLike(String value) {
            addCriterion("CERTIFICATE_NO not like", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoIn(List<String> values) {
            addCriterion("CERTIFICATE_NO in", values, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoNotIn(List<String> values) {
            addCriterion("CERTIFICATE_NO not in", values, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_NO between", value1, value2, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_NO not between", value1, value2, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andDegreeNoIsNull() {
            addCriterion("DEGREE_NO is null");
            return (Criteria) this;
        }

        public Criteria andDegreeNoIsNotNull() {
            addCriterion("DEGREE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeNoEqualTo(String value) {
            addCriterion("DEGREE_NO =", value, "degreeNo");
            return (Criteria) this;
        }

        public Criteria andDegreeNoNotEqualTo(String value) {
            addCriterion("DEGREE_NO <>", value, "degreeNo");
            return (Criteria) this;
        }

        public Criteria andDegreeNoGreaterThan(String value) {
            addCriterion("DEGREE_NO >", value, "degreeNo");
            return (Criteria) this;
        }

        public Criteria andDegreeNoGreaterThanOrEqualTo(String value) {
            addCriterion("DEGREE_NO >=", value, "degreeNo");
            return (Criteria) this;
        }

        public Criteria andDegreeNoLessThan(String value) {
            addCriterion("DEGREE_NO <", value, "degreeNo");
            return (Criteria) this;
        }

        public Criteria andDegreeNoLessThanOrEqualTo(String value) {
            addCriterion("DEGREE_NO <=", value, "degreeNo");
            return (Criteria) this;
        }

        public Criteria andDegreeNoLike(String value) {
            addCriterion("DEGREE_NO like", value, "degreeNo");
            return (Criteria) this;
        }

        public Criteria andDegreeNoNotLike(String value) {
            addCriterion("DEGREE_NO not like", value, "degreeNo");
            return (Criteria) this;
        }

        public Criteria andDegreeNoIn(List<String> values) {
            addCriterion("DEGREE_NO in", values, "degreeNo");
            return (Criteria) this;
        }

        public Criteria andDegreeNoNotIn(List<String> values) {
            addCriterion("DEGREE_NO not in", values, "degreeNo");
            return (Criteria) this;
        }

        public Criteria andDegreeNoBetween(String value1, String value2) {
            addCriterion("DEGREE_NO between", value1, value2, "degreeNo");
            return (Criteria) this;
        }

        public Criteria andDegreeNoNotBetween(String value1, String value2) {
            addCriterion("DEGREE_NO not between", value1, value2, "degreeNo");
            return (Criteria) this;
        }

        public Criteria andInHosDateIsNull() {
            addCriterion("IN_HOS_DATE is null");
            return (Criteria) this;
        }

        public Criteria andInHosDateIsNotNull() {
            addCriterion("IN_HOS_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andInHosDateEqualTo(String value) {
            addCriterion("IN_HOS_DATE =", value, "inHosDate");
            return (Criteria) this;
        }

        public Criteria andInHosDateNotEqualTo(String value) {
            addCriterion("IN_HOS_DATE <>", value, "inHosDate");
            return (Criteria) this;
        }

        public Criteria andInHosDateGreaterThan(String value) {
            addCriterion("IN_HOS_DATE >", value, "inHosDate");
            return (Criteria) this;
        }

        public Criteria andInHosDateGreaterThanOrEqualTo(String value) {
            addCriterion("IN_HOS_DATE >=", value, "inHosDate");
            return (Criteria) this;
        }

        public Criteria andInHosDateLessThan(String value) {
            addCriterion("IN_HOS_DATE <", value, "inHosDate");
            return (Criteria) this;
        }

        public Criteria andInHosDateLessThanOrEqualTo(String value) {
            addCriterion("IN_HOS_DATE <=", value, "inHosDate");
            return (Criteria) this;
        }

        public Criteria andInHosDateLike(String value) {
            addCriterion("IN_HOS_DATE like", value, "inHosDate");
            return (Criteria) this;
        }

        public Criteria andInHosDateNotLike(String value) {
            addCriterion("IN_HOS_DATE not like", value, "inHosDate");
            return (Criteria) this;
        }

        public Criteria andInHosDateIn(List<String> values) {
            addCriterion("IN_HOS_DATE in", values, "inHosDate");
            return (Criteria) this;
        }

        public Criteria andInHosDateNotIn(List<String> values) {
            addCriterion("IN_HOS_DATE not in", values, "inHosDate");
            return (Criteria) this;
        }

        public Criteria andInHosDateBetween(String value1, String value2) {
            addCriterion("IN_HOS_DATE between", value1, value2, "inHosDate");
            return (Criteria) this;
        }

        public Criteria andInHosDateNotBetween(String value1, String value2) {
            addCriterion("IN_HOS_DATE not between", value1, value2, "inHosDate");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsIsNull() {
            addCriterion("TRAINING_YEARS is null");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsIsNotNull() {
            addCriterion("TRAINING_YEARS is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsEqualTo(String value) {
            addCriterion("TRAINING_YEARS =", value, "trainingYears");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsNotEqualTo(String value) {
            addCriterion("TRAINING_YEARS <>", value, "trainingYears");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsGreaterThan(String value) {
            addCriterion("TRAINING_YEARS >", value, "trainingYears");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_YEARS >=", value, "trainingYears");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsLessThan(String value) {
            addCriterion("TRAINING_YEARS <", value, "trainingYears");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_YEARS <=", value, "trainingYears");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsLike(String value) {
            addCriterion("TRAINING_YEARS like", value, "trainingYears");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsNotLike(String value) {
            addCriterion("TRAINING_YEARS not like", value, "trainingYears");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsIn(List<String> values) {
            addCriterion("TRAINING_YEARS in", values, "trainingYears");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsNotIn(List<String> values) {
            addCriterion("TRAINING_YEARS not in", values, "trainingYears");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsBetween(String value1, String value2) {
            addCriterion("TRAINING_YEARS between", value1, value2, "trainingYears");
            return (Criteria) this;
        }

        public Criteria andTrainingYearsNotBetween(String value1, String value2) {
            addCriterion("TRAINING_YEARS not between", value1, value2, "trainingYears");
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

        public Criteria andRotationFlowIsNull() {
            addCriterion("ROTATION_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRotationFlowIsNotNull() {
            addCriterion("ROTATION_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRotationFlowEqualTo(String value) {
            addCriterion("ROTATION_FLOW =", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowNotEqualTo(String value) {
            addCriterion("ROTATION_FLOW <>", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowGreaterThan(String value) {
            addCriterion("ROTATION_FLOW >", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ROTATION_FLOW >=", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowLessThan(String value) {
            addCriterion("ROTATION_FLOW <", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowLessThanOrEqualTo(String value) {
            addCriterion("ROTATION_FLOW <=", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowLike(String value) {
            addCriterion("ROTATION_FLOW like", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowNotLike(String value) {
            addCriterion("ROTATION_FLOW not like", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowIn(List<String> values) {
            addCriterion("ROTATION_FLOW in", values, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowNotIn(List<String> values) {
            addCriterion("ROTATION_FLOW not in", values, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowBetween(String value1, String value2) {
            addCriterion("ROTATION_FLOW between", value1, value2, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowNotBetween(String value1, String value2) {
            addCriterion("ROTATION_FLOW not between", value1, value2, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationNameIsNull() {
            addCriterion("ROTATION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRotationNameIsNotNull() {
            addCriterion("ROTATION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRotationNameEqualTo(String value) {
            addCriterion("ROTATION_NAME =", value, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameNotEqualTo(String value) {
            addCriterion("ROTATION_NAME <>", value, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameGreaterThan(String value) {
            addCriterion("ROTATION_NAME >", value, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameGreaterThanOrEqualTo(String value) {
            addCriterion("ROTATION_NAME >=", value, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameLessThan(String value) {
            addCriterion("ROTATION_NAME <", value, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameLessThanOrEqualTo(String value) {
            addCriterion("ROTATION_NAME <=", value, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameLike(String value) {
            addCriterion("ROTATION_NAME like", value, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameNotLike(String value) {
            addCriterion("ROTATION_NAME not like", value, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameIn(List<String> values) {
            addCriterion("ROTATION_NAME in", values, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameNotIn(List<String> values) {
            addCriterion("ROTATION_NAME not in", values, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameBetween(String value1, String value2) {
            addCriterion("ROTATION_NAME between", value1, value2, "rotationName");
            return (Criteria) this;
        }

        public Criteria andRotationNameNotBetween(String value1, String value2) {
            addCriterion("ROTATION_NAME not between", value1, value2, "rotationName");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdIsNull() {
            addCriterion("DOCTOR_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdIsNotNull() {
            addCriterion("DOCTOR_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdEqualTo(String value) {
            addCriterion("DOCTOR_STATUS_ID =", value, "doctorStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdNotEqualTo(String value) {
            addCriterion("DOCTOR_STATUS_ID <>", value, "doctorStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdGreaterThan(String value) {
            addCriterion("DOCTOR_STATUS_ID >", value, "doctorStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_STATUS_ID >=", value, "doctorStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdLessThan(String value) {
            addCriterion("DOCTOR_STATUS_ID <", value, "doctorStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_STATUS_ID <=", value, "doctorStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdLike(String value) {
            addCriterion("DOCTOR_STATUS_ID like", value, "doctorStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdNotLike(String value) {
            addCriterion("DOCTOR_STATUS_ID not like", value, "doctorStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdIn(List<String> values) {
            addCriterion("DOCTOR_STATUS_ID in", values, "doctorStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdNotIn(List<String> values) {
            addCriterion("DOCTOR_STATUS_ID not in", values, "doctorStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdBetween(String value1, String value2) {
            addCriterion("DOCTOR_STATUS_ID between", value1, value2, "doctorStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusIdNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_STATUS_ID not between", value1, value2, "doctorStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameIsNull() {
            addCriterion("DOCTOR_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameIsNotNull() {
            addCriterion("DOCTOR_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameEqualTo(String value) {
            addCriterion("DOCTOR_STATUS_NAME =", value, "doctorStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameNotEqualTo(String value) {
            addCriterion("DOCTOR_STATUS_NAME <>", value, "doctorStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameGreaterThan(String value) {
            addCriterion("DOCTOR_STATUS_NAME >", value, "doctorStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_STATUS_NAME >=", value, "doctorStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameLessThan(String value) {
            addCriterion("DOCTOR_STATUS_NAME <", value, "doctorStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_STATUS_NAME <=", value, "doctorStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameLike(String value) {
            addCriterion("DOCTOR_STATUS_NAME like", value, "doctorStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameNotLike(String value) {
            addCriterion("DOCTOR_STATUS_NAME not like", value, "doctorStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameIn(List<String> values) {
            addCriterion("DOCTOR_STATUS_NAME in", values, "doctorStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameNotIn(List<String> values) {
            addCriterion("DOCTOR_STATUS_NAME not in", values, "doctorStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameBetween(String value1, String value2) {
            addCriterion("DOCTOR_STATUS_NAME between", value1, value2, "doctorStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorStatusNameNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_STATUS_NAME not between", value1, value2, "doctorStatusName");
            return (Criteria) this;
        }

        public Criteria andCompleteDateIsNull() {
            addCriterion("COMPLETE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCompleteDateIsNotNull() {
            addCriterion("COMPLETE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteDateEqualTo(String value) {
            addCriterion("COMPLETE_DATE =", value, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateNotEqualTo(String value) {
            addCriterion("COMPLETE_DATE <>", value, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateGreaterThan(String value) {
            addCriterion("COMPLETE_DATE >", value, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateGreaterThanOrEqualTo(String value) {
            addCriterion("COMPLETE_DATE >=", value, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateLessThan(String value) {
            addCriterion("COMPLETE_DATE <", value, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateLessThanOrEqualTo(String value) {
            addCriterion("COMPLETE_DATE <=", value, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateLike(String value) {
            addCriterion("COMPLETE_DATE like", value, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateNotLike(String value) {
            addCriterion("COMPLETE_DATE not like", value, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateIn(List<String> values) {
            addCriterion("COMPLETE_DATE in", values, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateNotIn(List<String> values) {
            addCriterion("COMPLETE_DATE not in", values, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateBetween(String value1, String value2) {
            addCriterion("COMPLETE_DATE between", value1, value2, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteDateNotBetween(String value1, String value2) {
            addCriterion("COMPLETE_DATE not between", value1, value2, "completeDate");
            return (Criteria) this;
        }

        public Criteria andCompleteNoIsNull() {
            addCriterion("COMPLETE_NO is null");
            return (Criteria) this;
        }

        public Criteria andCompleteNoIsNotNull() {
            addCriterion("COMPLETE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteNoEqualTo(String value) {
            addCriterion("COMPLETE_NO =", value, "completeNo");
            return (Criteria) this;
        }

        public Criteria andCompleteNoNotEqualTo(String value) {
            addCriterion("COMPLETE_NO <>", value, "completeNo");
            return (Criteria) this;
        }

        public Criteria andCompleteNoGreaterThan(String value) {
            addCriterion("COMPLETE_NO >", value, "completeNo");
            return (Criteria) this;
        }

        public Criteria andCompleteNoGreaterThanOrEqualTo(String value) {
            addCriterion("COMPLETE_NO >=", value, "completeNo");
            return (Criteria) this;
        }

        public Criteria andCompleteNoLessThan(String value) {
            addCriterion("COMPLETE_NO <", value, "completeNo");
            return (Criteria) this;
        }

        public Criteria andCompleteNoLessThanOrEqualTo(String value) {
            addCriterion("COMPLETE_NO <=", value, "completeNo");
            return (Criteria) this;
        }

        public Criteria andCompleteNoLike(String value) {
            addCriterion("COMPLETE_NO like", value, "completeNo");
            return (Criteria) this;
        }

        public Criteria andCompleteNoNotLike(String value) {
            addCriterion("COMPLETE_NO not like", value, "completeNo");
            return (Criteria) this;
        }

        public Criteria andCompleteNoIn(List<String> values) {
            addCriterion("COMPLETE_NO in", values, "completeNo");
            return (Criteria) this;
        }

        public Criteria andCompleteNoNotIn(List<String> values) {
            addCriterion("COMPLETE_NO not in", values, "completeNo");
            return (Criteria) this;
        }

        public Criteria andCompleteNoBetween(String value1, String value2) {
            addCriterion("COMPLETE_NO between", value1, value2, "completeNo");
            return (Criteria) this;
        }

        public Criteria andCompleteNoNotBetween(String value1, String value2) {
            addCriterion("COMPLETE_NO not between", value1, value2, "completeNo");
            return (Criteria) this;
        }

        public Criteria andTerminatReasonIsNull() {
            addCriterion("TERMINAT_REASON is null");
            return (Criteria) this;
        }

        public Criteria andTerminatReasonIsNotNull() {
            addCriterion("TERMINAT_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andTerminatReasonEqualTo(String value) {
            addCriterion("TERMINAT_REASON =", value, "terminatReason");
            return (Criteria) this;
        }

        public Criteria andTerminatReasonNotEqualTo(String value) {
            addCriterion("TERMINAT_REASON <>", value, "terminatReason");
            return (Criteria) this;
        }

        public Criteria andTerminatReasonGreaterThan(String value) {
            addCriterion("TERMINAT_REASON >", value, "terminatReason");
            return (Criteria) this;
        }

        public Criteria andTerminatReasonGreaterThanOrEqualTo(String value) {
            addCriterion("TERMINAT_REASON >=", value, "terminatReason");
            return (Criteria) this;
        }

        public Criteria andTerminatReasonLessThan(String value) {
            addCriterion("TERMINAT_REASON <", value, "terminatReason");
            return (Criteria) this;
        }

        public Criteria andTerminatReasonLessThanOrEqualTo(String value) {
            addCriterion("TERMINAT_REASON <=", value, "terminatReason");
            return (Criteria) this;
        }

        public Criteria andTerminatReasonLike(String value) {
            addCriterion("TERMINAT_REASON like", value, "terminatReason");
            return (Criteria) this;
        }

        public Criteria andTerminatReasonNotLike(String value) {
            addCriterion("TERMINAT_REASON not like", value, "terminatReason");
            return (Criteria) this;
        }

        public Criteria andTerminatReasonIn(List<String> values) {
            addCriterion("TERMINAT_REASON in", values, "terminatReason");
            return (Criteria) this;
        }

        public Criteria andTerminatReasonNotIn(List<String> values) {
            addCriterion("TERMINAT_REASON not in", values, "terminatReason");
            return (Criteria) this;
        }

        public Criteria andTerminatReasonBetween(String value1, String value2) {
            addCriterion("TERMINAT_REASON between", value1, value2, "terminatReason");
            return (Criteria) this;
        }

        public Criteria andTerminatReasonNotBetween(String value1, String value2) {
            addCriterion("TERMINAT_REASON not between", value1, value2, "terminatReason");
            return (Criteria) this;
        }

        public Criteria andTerminatDateIsNull() {
            addCriterion("TERMINAT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTerminatDateIsNotNull() {
            addCriterion("TERMINAT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTerminatDateEqualTo(String value) {
            addCriterion("TERMINAT_DATE =", value, "terminatDate");
            return (Criteria) this;
        }

        public Criteria andTerminatDateNotEqualTo(String value) {
            addCriterion("TERMINAT_DATE <>", value, "terminatDate");
            return (Criteria) this;
        }

        public Criteria andTerminatDateGreaterThan(String value) {
            addCriterion("TERMINAT_DATE >", value, "terminatDate");
            return (Criteria) this;
        }

        public Criteria andTerminatDateGreaterThanOrEqualTo(String value) {
            addCriterion("TERMINAT_DATE >=", value, "terminatDate");
            return (Criteria) this;
        }

        public Criteria andTerminatDateLessThan(String value) {
            addCriterion("TERMINAT_DATE <", value, "terminatDate");
            return (Criteria) this;
        }

        public Criteria andTerminatDateLessThanOrEqualTo(String value) {
            addCriterion("TERMINAT_DATE <=", value, "terminatDate");
            return (Criteria) this;
        }

        public Criteria andTerminatDateLike(String value) {
            addCriterion("TERMINAT_DATE like", value, "terminatDate");
            return (Criteria) this;
        }

        public Criteria andTerminatDateNotLike(String value) {
            addCriterion("TERMINAT_DATE not like", value, "terminatDate");
            return (Criteria) this;
        }

        public Criteria andTerminatDateIn(List<String> values) {
            addCriterion("TERMINAT_DATE in", values, "terminatDate");
            return (Criteria) this;
        }

        public Criteria andTerminatDateNotIn(List<String> values) {
            addCriterion("TERMINAT_DATE not in", values, "terminatDate");
            return (Criteria) this;
        }

        public Criteria andTerminatDateBetween(String value1, String value2) {
            addCriterion("TERMINAT_DATE between", value1, value2, "terminatDate");
            return (Criteria) this;
        }

        public Criteria andTerminatDateNotBetween(String value1, String value2) {
            addCriterion("TERMINAT_DATE not between", value1, value2, "terminatDate");
            return (Criteria) this;
        }

        public Criteria andSelDeptFlagIsNull() {
            addCriterion("SEL_DEPT_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andSelDeptFlagIsNotNull() {
            addCriterion("SEL_DEPT_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andSelDeptFlagEqualTo(String value) {
            addCriterion("SEL_DEPT_FLAG =", value, "selDeptFlag");
            return (Criteria) this;
        }

        public Criteria andSelDeptFlagNotEqualTo(String value) {
            addCriterion("SEL_DEPT_FLAG <>", value, "selDeptFlag");
            return (Criteria) this;
        }

        public Criteria andSelDeptFlagGreaterThan(String value) {
            addCriterion("SEL_DEPT_FLAG >", value, "selDeptFlag");
            return (Criteria) this;
        }

        public Criteria andSelDeptFlagGreaterThanOrEqualTo(String value) {
            addCriterion("SEL_DEPT_FLAG >=", value, "selDeptFlag");
            return (Criteria) this;
        }

        public Criteria andSelDeptFlagLessThan(String value) {
            addCriterion("SEL_DEPT_FLAG <", value, "selDeptFlag");
            return (Criteria) this;
        }

        public Criteria andSelDeptFlagLessThanOrEqualTo(String value) {
            addCriterion("SEL_DEPT_FLAG <=", value, "selDeptFlag");
            return (Criteria) this;
        }

        public Criteria andSelDeptFlagLike(String value) {
            addCriterion("SEL_DEPT_FLAG like", value, "selDeptFlag");
            return (Criteria) this;
        }

        public Criteria andSelDeptFlagNotLike(String value) {
            addCriterion("SEL_DEPT_FLAG not like", value, "selDeptFlag");
            return (Criteria) this;
        }

        public Criteria andSelDeptFlagIn(List<String> values) {
            addCriterion("SEL_DEPT_FLAG in", values, "selDeptFlag");
            return (Criteria) this;
        }

        public Criteria andSelDeptFlagNotIn(List<String> values) {
            addCriterion("SEL_DEPT_FLAG not in", values, "selDeptFlag");
            return (Criteria) this;
        }

        public Criteria andSelDeptFlagBetween(String value1, String value2) {
            addCriterion("SEL_DEPT_FLAG between", value1, value2, "selDeptFlag");
            return (Criteria) this;
        }

        public Criteria andSelDeptFlagNotBetween(String value1, String value2) {
            addCriterion("SEL_DEPT_FLAG not between", value1, value2, "selDeptFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagIsNull() {
            addCriterion("SCH_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andSchFlagIsNotNull() {
            addCriterion("SCH_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andSchFlagEqualTo(String value) {
            addCriterion("SCH_FLAG =", value, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagNotEqualTo(String value) {
            addCriterion("SCH_FLAG <>", value, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagGreaterThan(String value) {
            addCriterion("SCH_FLAG >", value, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_FLAG >=", value, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagLessThan(String value) {
            addCriterion("SCH_FLAG <", value, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagLessThanOrEqualTo(String value) {
            addCriterion("SCH_FLAG <=", value, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagLike(String value) {
            addCriterion("SCH_FLAG like", value, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagNotLike(String value) {
            addCriterion("SCH_FLAG not like", value, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagIn(List<String> values) {
            addCriterion("SCH_FLAG in", values, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagNotIn(List<String> values) {
            addCriterion("SCH_FLAG not in", values, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagBetween(String value1, String value2) {
            addCriterion("SCH_FLAG between", value1, value2, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagNotBetween(String value1, String value2) {
            addCriterion("SCH_FLAG not between", value1, value2, "schFlag");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoIsNull() {
            addCriterion("QUALIFIED_NO is null");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoIsNotNull() {
            addCriterion("QUALIFIED_NO is not null");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoEqualTo(String value) {
            addCriterion("QUALIFIED_NO =", value, "qualifiedNo");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoNotEqualTo(String value) {
            addCriterion("QUALIFIED_NO <>", value, "qualifiedNo");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoGreaterThan(String value) {
            addCriterion("QUALIFIED_NO >", value, "qualifiedNo");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoGreaterThanOrEqualTo(String value) {
            addCriterion("QUALIFIED_NO >=", value, "qualifiedNo");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoLessThan(String value) {
            addCriterion("QUALIFIED_NO <", value, "qualifiedNo");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoLessThanOrEqualTo(String value) {
            addCriterion("QUALIFIED_NO <=", value, "qualifiedNo");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoLike(String value) {
            addCriterion("QUALIFIED_NO like", value, "qualifiedNo");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoNotLike(String value) {
            addCriterion("QUALIFIED_NO not like", value, "qualifiedNo");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoIn(List<String> values) {
            addCriterion("QUALIFIED_NO in", values, "qualifiedNo");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoNotIn(List<String> values) {
            addCriterion("QUALIFIED_NO not in", values, "qualifiedNo");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoBetween(String value1, String value2) {
            addCriterion("QUALIFIED_NO between", value1, value2, "qualifiedNo");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoNotBetween(String value1, String value2) {
            addCriterion("QUALIFIED_NO not between", value1, value2, "qualifiedNo");
            return (Criteria) this;
        }

        public Criteria andQualifiedDateIsNull() {
            addCriterion("QUALIFIED_DATE is null");
            return (Criteria) this;
        }

        public Criteria andQualifiedDateIsNotNull() {
            addCriterion("QUALIFIED_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andQualifiedDateEqualTo(String value) {
            addCriterion("QUALIFIED_DATE =", value, "qualifiedDate");
            return (Criteria) this;
        }

        public Criteria andQualifiedDateNotEqualTo(String value) {
            addCriterion("QUALIFIED_DATE <>", value, "qualifiedDate");
            return (Criteria) this;
        }

        public Criteria andQualifiedDateGreaterThan(String value) {
            addCriterion("QUALIFIED_DATE >", value, "qualifiedDate");
            return (Criteria) this;
        }

        public Criteria andQualifiedDateGreaterThanOrEqualTo(String value) {
            addCriterion("QUALIFIED_DATE >=", value, "qualifiedDate");
            return (Criteria) this;
        }

        public Criteria andQualifiedDateLessThan(String value) {
            addCriterion("QUALIFIED_DATE <", value, "qualifiedDate");
            return (Criteria) this;
        }

        public Criteria andQualifiedDateLessThanOrEqualTo(String value) {
            addCriterion("QUALIFIED_DATE <=", value, "qualifiedDate");
            return (Criteria) this;
        }

        public Criteria andQualifiedDateLike(String value) {
            addCriterion("QUALIFIED_DATE like", value, "qualifiedDate");
            return (Criteria) this;
        }

        public Criteria andQualifiedDateNotLike(String value) {
            addCriterion("QUALIFIED_DATE not like", value, "qualifiedDate");
            return (Criteria) this;
        }

        public Criteria andQualifiedDateIn(List<String> values) {
            addCriterion("QUALIFIED_DATE in", values, "qualifiedDate");
            return (Criteria) this;
        }

        public Criteria andQualifiedDateNotIn(List<String> values) {
            addCriterion("QUALIFIED_DATE not in", values, "qualifiedDate");
            return (Criteria) this;
        }

        public Criteria andQualifiedDateBetween(String value1, String value2) {
            addCriterion("QUALIFIED_DATE between", value1, value2, "qualifiedDate");
            return (Criteria) this;
        }

        public Criteria andQualifiedDateNotBetween(String value1, String value2) {
            addCriterion("QUALIFIED_DATE not between", value1, value2, "qualifiedDate");
            return (Criteria) this;
        }

        public Criteria andQualifiedFileIsNull() {
            addCriterion("QUALIFIED_FILE is null");
            return (Criteria) this;
        }

        public Criteria andQualifiedFileIsNotNull() {
            addCriterion("QUALIFIED_FILE is not null");
            return (Criteria) this;
        }

        public Criteria andQualifiedFileEqualTo(String value) {
            addCriterion("QUALIFIED_FILE =", value, "qualifiedFile");
            return (Criteria) this;
        }

        public Criteria andQualifiedFileNotEqualTo(String value) {
            addCriterion("QUALIFIED_FILE <>", value, "qualifiedFile");
            return (Criteria) this;
        }

        public Criteria andQualifiedFileGreaterThan(String value) {
            addCriterion("QUALIFIED_FILE >", value, "qualifiedFile");
            return (Criteria) this;
        }

        public Criteria andQualifiedFileGreaterThanOrEqualTo(String value) {
            addCriterion("QUALIFIED_FILE >=", value, "qualifiedFile");
            return (Criteria) this;
        }

        public Criteria andQualifiedFileLessThan(String value) {
            addCriterion("QUALIFIED_FILE <", value, "qualifiedFile");
            return (Criteria) this;
        }

        public Criteria andQualifiedFileLessThanOrEqualTo(String value) {
            addCriterion("QUALIFIED_FILE <=", value, "qualifiedFile");
            return (Criteria) this;
        }

        public Criteria andQualifiedFileLike(String value) {
            addCriterion("QUALIFIED_FILE like", value, "qualifiedFile");
            return (Criteria) this;
        }

        public Criteria andQualifiedFileNotLike(String value) {
            addCriterion("QUALIFIED_FILE not like", value, "qualifiedFile");
            return (Criteria) this;
        }

        public Criteria andQualifiedFileIn(List<String> values) {
            addCriterion("QUALIFIED_FILE in", values, "qualifiedFile");
            return (Criteria) this;
        }

        public Criteria andQualifiedFileNotIn(List<String> values) {
            addCriterion("QUALIFIED_FILE not in", values, "qualifiedFile");
            return (Criteria) this;
        }

        public Criteria andQualifiedFileBetween(String value1, String value2) {
            addCriterion("QUALIFIED_FILE between", value1, value2, "qualifiedFile");
            return (Criteria) this;
        }

        public Criteria andQualifiedFileNotBetween(String value1, String value2) {
            addCriterion("QUALIFIED_FILE not between", value1, value2, "qualifiedFile");
            return (Criteria) this;
        }

        public Criteria andRegNoIsNull() {
            addCriterion("REG_NO is null");
            return (Criteria) this;
        }

        public Criteria andRegNoIsNotNull() {
            addCriterion("REG_NO is not null");
            return (Criteria) this;
        }

        public Criteria andRegNoEqualTo(String value) {
            addCriterion("REG_NO =", value, "regNo");
            return (Criteria) this;
        }

        public Criteria andRegNoNotEqualTo(String value) {
            addCriterion("REG_NO <>", value, "regNo");
            return (Criteria) this;
        }

        public Criteria andRegNoGreaterThan(String value) {
            addCriterion("REG_NO >", value, "regNo");
            return (Criteria) this;
        }

        public Criteria andRegNoGreaterThanOrEqualTo(String value) {
            addCriterion("REG_NO >=", value, "regNo");
            return (Criteria) this;
        }

        public Criteria andRegNoLessThan(String value) {
            addCriterion("REG_NO <", value, "regNo");
            return (Criteria) this;
        }

        public Criteria andRegNoLessThanOrEqualTo(String value) {
            addCriterion("REG_NO <=", value, "regNo");
            return (Criteria) this;
        }

        public Criteria andRegNoLike(String value) {
            addCriterion("REG_NO like", value, "regNo");
            return (Criteria) this;
        }

        public Criteria andRegNoNotLike(String value) {
            addCriterion("REG_NO not like", value, "regNo");
            return (Criteria) this;
        }

        public Criteria andRegNoIn(List<String> values) {
            addCriterion("REG_NO in", values, "regNo");
            return (Criteria) this;
        }

        public Criteria andRegNoNotIn(List<String> values) {
            addCriterion("REG_NO not in", values, "regNo");
            return (Criteria) this;
        }

        public Criteria andRegNoBetween(String value1, String value2) {
            addCriterion("REG_NO between", value1, value2, "regNo");
            return (Criteria) this;
        }

        public Criteria andRegNoNotBetween(String value1, String value2) {
            addCriterion("REG_NO not between", value1, value2, "regNo");
            return (Criteria) this;
        }

        public Criteria andRegAddressIsNull() {
            addCriterion("REG_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andRegAddressIsNotNull() {
            addCriterion("REG_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andRegAddressEqualTo(String value) {
            addCriterion("REG_ADDRESS =", value, "regAddress");
            return (Criteria) this;
        }

        public Criteria andRegAddressNotEqualTo(String value) {
            addCriterion("REG_ADDRESS <>", value, "regAddress");
            return (Criteria) this;
        }

        public Criteria andRegAddressGreaterThan(String value) {
            addCriterion("REG_ADDRESS >", value, "regAddress");
            return (Criteria) this;
        }

        public Criteria andRegAddressGreaterThanOrEqualTo(String value) {
            addCriterion("REG_ADDRESS >=", value, "regAddress");
            return (Criteria) this;
        }

        public Criteria andRegAddressLessThan(String value) {
            addCriterion("REG_ADDRESS <", value, "regAddress");
            return (Criteria) this;
        }

        public Criteria andRegAddressLessThanOrEqualTo(String value) {
            addCriterion("REG_ADDRESS <=", value, "regAddress");
            return (Criteria) this;
        }

        public Criteria andRegAddressLike(String value) {
            addCriterion("REG_ADDRESS like", value, "regAddress");
            return (Criteria) this;
        }

        public Criteria andRegAddressNotLike(String value) {
            addCriterion("REG_ADDRESS not like", value, "regAddress");
            return (Criteria) this;
        }

        public Criteria andRegAddressIn(List<String> values) {
            addCriterion("REG_ADDRESS in", values, "regAddress");
            return (Criteria) this;
        }

        public Criteria andRegAddressNotIn(List<String> values) {
            addCriterion("REG_ADDRESS not in", values, "regAddress");
            return (Criteria) this;
        }

        public Criteria andRegAddressBetween(String value1, String value2) {
            addCriterion("REG_ADDRESS between", value1, value2, "regAddress");
            return (Criteria) this;
        }

        public Criteria andRegAddressNotBetween(String value1, String value2) {
            addCriterion("REG_ADDRESS not between", value1, value2, "regAddress");
            return (Criteria) this;
        }

        public Criteria andComputerSkillsIsNull() {
            addCriterion("COMPUTER_SKILLS is null");
            return (Criteria) this;
        }

        public Criteria andComputerSkillsIsNotNull() {
            addCriterion("COMPUTER_SKILLS is not null");
            return (Criteria) this;
        }

        public Criteria andComputerSkillsEqualTo(String value) {
            addCriterion("COMPUTER_SKILLS =", value, "computerSkills");
            return (Criteria) this;
        }

        public Criteria andComputerSkillsNotEqualTo(String value) {
            addCriterion("COMPUTER_SKILLS <>", value, "computerSkills");
            return (Criteria) this;
        }

        public Criteria andComputerSkillsGreaterThan(String value) {
            addCriterion("COMPUTER_SKILLS >", value, "computerSkills");
            return (Criteria) this;
        }

        public Criteria andComputerSkillsGreaterThanOrEqualTo(String value) {
            addCriterion("COMPUTER_SKILLS >=", value, "computerSkills");
            return (Criteria) this;
        }

        public Criteria andComputerSkillsLessThan(String value) {
            addCriterion("COMPUTER_SKILLS <", value, "computerSkills");
            return (Criteria) this;
        }

        public Criteria andComputerSkillsLessThanOrEqualTo(String value) {
            addCriterion("COMPUTER_SKILLS <=", value, "computerSkills");
            return (Criteria) this;
        }

        public Criteria andComputerSkillsLike(String value) {
            addCriterion("COMPUTER_SKILLS like", value, "computerSkills");
            return (Criteria) this;
        }

        public Criteria andComputerSkillsNotLike(String value) {
            addCriterion("COMPUTER_SKILLS not like", value, "computerSkills");
            return (Criteria) this;
        }

        public Criteria andComputerSkillsIn(List<String> values) {
            addCriterion("COMPUTER_SKILLS in", values, "computerSkills");
            return (Criteria) this;
        }

        public Criteria andComputerSkillsNotIn(List<String> values) {
            addCriterion("COMPUTER_SKILLS not in", values, "computerSkills");
            return (Criteria) this;
        }

        public Criteria andComputerSkillsBetween(String value1, String value2) {
            addCriterion("COMPUTER_SKILLS between", value1, value2, "computerSkills");
            return (Criteria) this;
        }

        public Criteria andComputerSkillsNotBetween(String value1, String value2) {
            addCriterion("COMPUTER_SKILLS not between", value1, value2, "computerSkills");
            return (Criteria) this;
        }

        public Criteria andForeignSkillsIsNull() {
            addCriterion("FOREIGN_SKILLS is null");
            return (Criteria) this;
        }

        public Criteria andForeignSkillsIsNotNull() {
            addCriterion("FOREIGN_SKILLS is not null");
            return (Criteria) this;
        }

        public Criteria andForeignSkillsEqualTo(String value) {
            addCriterion("FOREIGN_SKILLS =", value, "foreignSkills");
            return (Criteria) this;
        }

        public Criteria andForeignSkillsNotEqualTo(String value) {
            addCriterion("FOREIGN_SKILLS <>", value, "foreignSkills");
            return (Criteria) this;
        }

        public Criteria andForeignSkillsGreaterThan(String value) {
            addCriterion("FOREIGN_SKILLS >", value, "foreignSkills");
            return (Criteria) this;
        }

        public Criteria andForeignSkillsGreaterThanOrEqualTo(String value) {
            addCriterion("FOREIGN_SKILLS >=", value, "foreignSkills");
            return (Criteria) this;
        }

        public Criteria andForeignSkillsLessThan(String value) {
            addCriterion("FOREIGN_SKILLS <", value, "foreignSkills");
            return (Criteria) this;
        }

        public Criteria andForeignSkillsLessThanOrEqualTo(String value) {
            addCriterion("FOREIGN_SKILLS <=", value, "foreignSkills");
            return (Criteria) this;
        }

        public Criteria andForeignSkillsLike(String value) {
            addCriterion("FOREIGN_SKILLS like", value, "foreignSkills");
            return (Criteria) this;
        }

        public Criteria andForeignSkillsNotLike(String value) {
            addCriterion("FOREIGN_SKILLS not like", value, "foreignSkills");
            return (Criteria) this;
        }

        public Criteria andForeignSkillsIn(List<String> values) {
            addCriterion("FOREIGN_SKILLS in", values, "foreignSkills");
            return (Criteria) this;
        }

        public Criteria andForeignSkillsNotIn(List<String> values) {
            addCriterion("FOREIGN_SKILLS not in", values, "foreignSkills");
            return (Criteria) this;
        }

        public Criteria andForeignSkillsBetween(String value1, String value2) {
            addCriterion("FOREIGN_SKILLS between", value1, value2, "foreignSkills");
            return (Criteria) this;
        }

        public Criteria andForeignSkillsNotBetween(String value1, String value2) {
            addCriterion("FOREIGN_SKILLS not between", value1, value2, "foreignSkills");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameIsNull() {
            addCriterion("EMERGENCY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameIsNotNull() {
            addCriterion("EMERGENCY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameEqualTo(String value) {
            addCriterion("EMERGENCY_NAME =", value, "emergencyName");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameNotEqualTo(String value) {
            addCriterion("EMERGENCY_NAME <>", value, "emergencyName");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameGreaterThan(String value) {
            addCriterion("EMERGENCY_NAME >", value, "emergencyName");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameGreaterThanOrEqualTo(String value) {
            addCriterion("EMERGENCY_NAME >=", value, "emergencyName");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameLessThan(String value) {
            addCriterion("EMERGENCY_NAME <", value, "emergencyName");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameLessThanOrEqualTo(String value) {
            addCriterion("EMERGENCY_NAME <=", value, "emergencyName");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameLike(String value) {
            addCriterion("EMERGENCY_NAME like", value, "emergencyName");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameNotLike(String value) {
            addCriterion("EMERGENCY_NAME not like", value, "emergencyName");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameIn(List<String> values) {
            addCriterion("EMERGENCY_NAME in", values, "emergencyName");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameNotIn(List<String> values) {
            addCriterion("EMERGENCY_NAME not in", values, "emergencyName");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameBetween(String value1, String value2) {
            addCriterion("EMERGENCY_NAME between", value1, value2, "emergencyName");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameNotBetween(String value1, String value2) {
            addCriterion("EMERGENCY_NAME not between", value1, value2, "emergencyName");
            return (Criteria) this;
        }

        public Criteria andEmergencyPhoneIsNull() {
            addCriterion("EMERGENCY_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andEmergencyPhoneIsNotNull() {
            addCriterion("EMERGENCY_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andEmergencyPhoneEqualTo(String value) {
            addCriterion("EMERGENCY_PHONE =", value, "emergencyPhone");
            return (Criteria) this;
        }

        public Criteria andEmergencyPhoneNotEqualTo(String value) {
            addCriterion("EMERGENCY_PHONE <>", value, "emergencyPhone");
            return (Criteria) this;
        }

        public Criteria andEmergencyPhoneGreaterThan(String value) {
            addCriterion("EMERGENCY_PHONE >", value, "emergencyPhone");
            return (Criteria) this;
        }

        public Criteria andEmergencyPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("EMERGENCY_PHONE >=", value, "emergencyPhone");
            return (Criteria) this;
        }

        public Criteria andEmergencyPhoneLessThan(String value) {
            addCriterion("EMERGENCY_PHONE <", value, "emergencyPhone");
            return (Criteria) this;
        }

        public Criteria andEmergencyPhoneLessThanOrEqualTo(String value) {
            addCriterion("EMERGENCY_PHONE <=", value, "emergencyPhone");
            return (Criteria) this;
        }

        public Criteria andEmergencyPhoneLike(String value) {
            addCriterion("EMERGENCY_PHONE like", value, "emergencyPhone");
            return (Criteria) this;
        }

        public Criteria andEmergencyPhoneNotLike(String value) {
            addCriterion("EMERGENCY_PHONE not like", value, "emergencyPhone");
            return (Criteria) this;
        }

        public Criteria andEmergencyPhoneIn(List<String> values) {
            addCriterion("EMERGENCY_PHONE in", values, "emergencyPhone");
            return (Criteria) this;
        }

        public Criteria andEmergencyPhoneNotIn(List<String> values) {
            addCriterion("EMERGENCY_PHONE not in", values, "emergencyPhone");
            return (Criteria) this;
        }

        public Criteria andEmergencyPhoneBetween(String value1, String value2) {
            addCriterion("EMERGENCY_PHONE between", value1, value2, "emergencyPhone");
            return (Criteria) this;
        }

        public Criteria andEmergencyPhoneNotBetween(String value1, String value2) {
            addCriterion("EMERGENCY_PHONE not between", value1, value2, "emergencyPhone");
            return (Criteria) this;
        }

        public Criteria andEmergencyRelationIsNull() {
            addCriterion("EMERGENCY_RELATION is null");
            return (Criteria) this;
        }

        public Criteria andEmergencyRelationIsNotNull() {
            addCriterion("EMERGENCY_RELATION is not null");
            return (Criteria) this;
        }

        public Criteria andEmergencyRelationEqualTo(String value) {
            addCriterion("EMERGENCY_RELATION =", value, "emergencyRelation");
            return (Criteria) this;
        }

        public Criteria andEmergencyRelationNotEqualTo(String value) {
            addCriterion("EMERGENCY_RELATION <>", value, "emergencyRelation");
            return (Criteria) this;
        }

        public Criteria andEmergencyRelationGreaterThan(String value) {
            addCriterion("EMERGENCY_RELATION >", value, "emergencyRelation");
            return (Criteria) this;
        }

        public Criteria andEmergencyRelationGreaterThanOrEqualTo(String value) {
            addCriterion("EMERGENCY_RELATION >=", value, "emergencyRelation");
            return (Criteria) this;
        }

        public Criteria andEmergencyRelationLessThan(String value) {
            addCriterion("EMERGENCY_RELATION <", value, "emergencyRelation");
            return (Criteria) this;
        }

        public Criteria andEmergencyRelationLessThanOrEqualTo(String value) {
            addCriterion("EMERGENCY_RELATION <=", value, "emergencyRelation");
            return (Criteria) this;
        }

        public Criteria andEmergencyRelationLike(String value) {
            addCriterion("EMERGENCY_RELATION like", value, "emergencyRelation");
            return (Criteria) this;
        }

        public Criteria andEmergencyRelationNotLike(String value) {
            addCriterion("EMERGENCY_RELATION not like", value, "emergencyRelation");
            return (Criteria) this;
        }

        public Criteria andEmergencyRelationIn(List<String> values) {
            addCriterion("EMERGENCY_RELATION in", values, "emergencyRelation");
            return (Criteria) this;
        }

        public Criteria andEmergencyRelationNotIn(List<String> values) {
            addCriterion("EMERGENCY_RELATION not in", values, "emergencyRelation");
            return (Criteria) this;
        }

        public Criteria andEmergencyRelationBetween(String value1, String value2) {
            addCriterion("EMERGENCY_RELATION between", value1, value2, "emergencyRelation");
            return (Criteria) this;
        }

        public Criteria andEmergencyRelationNotBetween(String value1, String value2) {
            addCriterion("EMERGENCY_RELATION not between", value1, value2, "emergencyRelation");
            return (Criteria) this;
        }

        public Criteria andDisactiveReasonIsNull() {
            addCriterion("DISACTIVE_REASON is null");
            return (Criteria) this;
        }

        public Criteria andDisactiveReasonIsNotNull() {
            addCriterion("DISACTIVE_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andDisactiveReasonEqualTo(String value) {
            addCriterion("DISACTIVE_REASON =", value, "disactiveReason");
            return (Criteria) this;
        }

        public Criteria andDisactiveReasonNotEqualTo(String value) {
            addCriterion("DISACTIVE_REASON <>", value, "disactiveReason");
            return (Criteria) this;
        }

        public Criteria andDisactiveReasonGreaterThan(String value) {
            addCriterion("DISACTIVE_REASON >", value, "disactiveReason");
            return (Criteria) this;
        }

        public Criteria andDisactiveReasonGreaterThanOrEqualTo(String value) {
            addCriterion("DISACTIVE_REASON >=", value, "disactiveReason");
            return (Criteria) this;
        }

        public Criteria andDisactiveReasonLessThan(String value) {
            addCriterion("DISACTIVE_REASON <", value, "disactiveReason");
            return (Criteria) this;
        }

        public Criteria andDisactiveReasonLessThanOrEqualTo(String value) {
            addCriterion("DISACTIVE_REASON <=", value, "disactiveReason");
            return (Criteria) this;
        }

        public Criteria andDisactiveReasonLike(String value) {
            addCriterion("DISACTIVE_REASON like", value, "disactiveReason");
            return (Criteria) this;
        }

        public Criteria andDisactiveReasonNotLike(String value) {
            addCriterion("DISACTIVE_REASON not like", value, "disactiveReason");
            return (Criteria) this;
        }

        public Criteria andDisactiveReasonIn(List<String> values) {
            addCriterion("DISACTIVE_REASON in", values, "disactiveReason");
            return (Criteria) this;
        }

        public Criteria andDisactiveReasonNotIn(List<String> values) {
            addCriterion("DISACTIVE_REASON not in", values, "disactiveReason");
            return (Criteria) this;
        }

        public Criteria andDisactiveReasonBetween(String value1, String value2) {
            addCriterion("DISACTIVE_REASON between", value1, value2, "disactiveReason");
            return (Criteria) this;
        }

        public Criteria andDisactiveReasonNotBetween(String value1, String value2) {
            addCriterion("DISACTIVE_REASON not between", value1, value2, "disactiveReason");
            return (Criteria) this;
        }

        public Criteria andReeditFlagIsNull() {
            addCriterion("REEDIT_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andReeditFlagIsNotNull() {
            addCriterion("REEDIT_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andReeditFlagEqualTo(String value) {
            addCriterion("REEDIT_FLAG =", value, "reeditFlag");
            return (Criteria) this;
        }

        public Criteria andReeditFlagNotEqualTo(String value) {
            addCriterion("REEDIT_FLAG <>", value, "reeditFlag");
            return (Criteria) this;
        }

        public Criteria andReeditFlagGreaterThan(String value) {
            addCriterion("REEDIT_FLAG >", value, "reeditFlag");
            return (Criteria) this;
        }

        public Criteria andReeditFlagGreaterThanOrEqualTo(String value) {
            addCriterion("REEDIT_FLAG >=", value, "reeditFlag");
            return (Criteria) this;
        }

        public Criteria andReeditFlagLessThan(String value) {
            addCriterion("REEDIT_FLAG <", value, "reeditFlag");
            return (Criteria) this;
        }

        public Criteria andReeditFlagLessThanOrEqualTo(String value) {
            addCriterion("REEDIT_FLAG <=", value, "reeditFlag");
            return (Criteria) this;
        }

        public Criteria andReeditFlagLike(String value) {
            addCriterion("REEDIT_FLAG like", value, "reeditFlag");
            return (Criteria) this;
        }

        public Criteria andReeditFlagNotLike(String value) {
            addCriterion("REEDIT_FLAG not like", value, "reeditFlag");
            return (Criteria) this;
        }

        public Criteria andReeditFlagIn(List<String> values) {
            addCriterion("REEDIT_FLAG in", values, "reeditFlag");
            return (Criteria) this;
        }

        public Criteria andReeditFlagNotIn(List<String> values) {
            addCriterion("REEDIT_FLAG not in", values, "reeditFlag");
            return (Criteria) this;
        }

        public Criteria andReeditFlagBetween(String value1, String value2) {
            addCriterion("REEDIT_FLAG between", value1, value2, "reeditFlag");
            return (Criteria) this;
        }

        public Criteria andReeditFlagNotBetween(String value1, String value2) {
            addCriterion("REEDIT_FLAG not between", value1, value2, "reeditFlag");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdIsNull() {
            addCriterion("DOCTOR_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdIsNotNull() {
            addCriterion("DOCTOR_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdEqualTo(String value) {
            addCriterion("DOCTOR_CATEGORY_ID =", value, "doctorCategoryId");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdNotEqualTo(String value) {
            addCriterion("DOCTOR_CATEGORY_ID <>", value, "doctorCategoryId");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdGreaterThan(String value) {
            addCriterion("DOCTOR_CATEGORY_ID >", value, "doctorCategoryId");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_CATEGORY_ID >=", value, "doctorCategoryId");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdLessThan(String value) {
            addCriterion("DOCTOR_CATEGORY_ID <", value, "doctorCategoryId");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_CATEGORY_ID <=", value, "doctorCategoryId");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdLike(String value) {
            addCriterion("DOCTOR_CATEGORY_ID like", value, "doctorCategoryId");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdNotLike(String value) {
            addCriterion("DOCTOR_CATEGORY_ID not like", value, "doctorCategoryId");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdIn(List<String> values) {
            addCriterion("DOCTOR_CATEGORY_ID in", values, "doctorCategoryId");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdNotIn(List<String> values) {
            addCriterion("DOCTOR_CATEGORY_ID not in", values, "doctorCategoryId");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdBetween(String value1, String value2) {
            addCriterion("DOCTOR_CATEGORY_ID between", value1, value2, "doctorCategoryId");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_CATEGORY_ID not between", value1, value2, "doctorCategoryId");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameIsNull() {
            addCriterion("DOCTOR_CATEGORY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameIsNotNull() {
            addCriterion("DOCTOR_CATEGORY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameEqualTo(String value) {
            addCriterion("DOCTOR_CATEGORY_NAME =", value, "doctorCategoryName");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameNotEqualTo(String value) {
            addCriterion("DOCTOR_CATEGORY_NAME <>", value, "doctorCategoryName");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameGreaterThan(String value) {
            addCriterion("DOCTOR_CATEGORY_NAME >", value, "doctorCategoryName");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_CATEGORY_NAME >=", value, "doctorCategoryName");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameLessThan(String value) {
            addCriterion("DOCTOR_CATEGORY_NAME <", value, "doctorCategoryName");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_CATEGORY_NAME <=", value, "doctorCategoryName");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameLike(String value) {
            addCriterion("DOCTOR_CATEGORY_NAME like", value, "doctorCategoryName");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameNotLike(String value) {
            addCriterion("DOCTOR_CATEGORY_NAME not like", value, "doctorCategoryName");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameIn(List<String> values) {
            addCriterion("DOCTOR_CATEGORY_NAME in", values, "doctorCategoryName");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameNotIn(List<String> values) {
            addCriterion("DOCTOR_CATEGORY_NAME not in", values, "doctorCategoryName");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameBetween(String value1, String value2) {
            addCriterion("DOCTOR_CATEGORY_NAME between", value1, value2, "doctorCategoryName");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_CATEGORY_NAME not between", value1, value2, "doctorCategoryName");
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

        public Criteria andSchStatusIdIsNull() {
            addCriterion("SCH_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andSchStatusIdIsNotNull() {
            addCriterion("SCH_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSchStatusIdEqualTo(String value) {
            addCriterion("SCH_STATUS_ID =", value, "schStatusId");
            return (Criteria) this;
        }

        public Criteria andSchStatusIdNotEqualTo(String value) {
            addCriterion("SCH_STATUS_ID <>", value, "schStatusId");
            return (Criteria) this;
        }

        public Criteria andSchStatusIdGreaterThan(String value) {
            addCriterion("SCH_STATUS_ID >", value, "schStatusId");
            return (Criteria) this;
        }

        public Criteria andSchStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_STATUS_ID >=", value, "schStatusId");
            return (Criteria) this;
        }

        public Criteria andSchStatusIdLessThan(String value) {
            addCriterion("SCH_STATUS_ID <", value, "schStatusId");
            return (Criteria) this;
        }

        public Criteria andSchStatusIdLessThanOrEqualTo(String value) {
            addCriterion("SCH_STATUS_ID <=", value, "schStatusId");
            return (Criteria) this;
        }

        public Criteria andSchStatusIdLike(String value) {
            addCriterion("SCH_STATUS_ID like", value, "schStatusId");
            return (Criteria) this;
        }

        public Criteria andSchStatusIdNotLike(String value) {
            addCriterion("SCH_STATUS_ID not like", value, "schStatusId");
            return (Criteria) this;
        }

        public Criteria andSchStatusIdIn(List<String> values) {
            addCriterion("SCH_STATUS_ID in", values, "schStatusId");
            return (Criteria) this;
        }

        public Criteria andSchStatusIdNotIn(List<String> values) {
            addCriterion("SCH_STATUS_ID not in", values, "schStatusId");
            return (Criteria) this;
        }

        public Criteria andSchStatusIdBetween(String value1, String value2) {
            addCriterion("SCH_STATUS_ID between", value1, value2, "schStatusId");
            return (Criteria) this;
        }

        public Criteria andSchStatusIdNotBetween(String value1, String value2) {
            addCriterion("SCH_STATUS_ID not between", value1, value2, "schStatusId");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameIsNull() {
            addCriterion("SCH_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameIsNotNull() {
            addCriterion("SCH_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameEqualTo(String value) {
            addCriterion("SCH_STATUS_NAME =", value, "schStatusName");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameNotEqualTo(String value) {
            addCriterion("SCH_STATUS_NAME <>", value, "schStatusName");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameGreaterThan(String value) {
            addCriterion("SCH_STATUS_NAME >", value, "schStatusName");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_STATUS_NAME >=", value, "schStatusName");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameLessThan(String value) {
            addCriterion("SCH_STATUS_NAME <", value, "schStatusName");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameLessThanOrEqualTo(String value) {
            addCriterion("SCH_STATUS_NAME <=", value, "schStatusName");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameLike(String value) {
            addCriterion("SCH_STATUS_NAME like", value, "schStatusName");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameNotLike(String value) {
            addCriterion("SCH_STATUS_NAME not like", value, "schStatusName");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameIn(List<String> values) {
            addCriterion("SCH_STATUS_NAME in", values, "schStatusName");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameNotIn(List<String> values) {
            addCriterion("SCH_STATUS_NAME not in", values, "schStatusName");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameBetween(String value1, String value2) {
            addCriterion("SCH_STATUS_NAME between", value1, value2, "schStatusName");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameNotBetween(String value1, String value2) {
            addCriterion("SCH_STATUS_NAME not between", value1, value2, "schStatusName");
            return (Criteria) this;
        }

        public Criteria andRegDateIsNull() {
            addCriterion("REG_DATE is null");
            return (Criteria) this;
        }

        public Criteria andRegDateIsNotNull() {
            addCriterion("REG_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andRegDateEqualTo(String value) {
            addCriterion("REG_DATE =", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateNotEqualTo(String value) {
            addCriterion("REG_DATE <>", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateGreaterThan(String value) {
            addCriterion("REG_DATE >", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateGreaterThanOrEqualTo(String value) {
            addCriterion("REG_DATE >=", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateLessThan(String value) {
            addCriterion("REG_DATE <", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateLessThanOrEqualTo(String value) {
            addCriterion("REG_DATE <=", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateLike(String value) {
            addCriterion("REG_DATE like", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateNotLike(String value) {
            addCriterion("REG_DATE not like", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateIn(List<String> values) {
            addCriterion("REG_DATE in", values, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateNotIn(List<String> values) {
            addCriterion("REG_DATE not in", values, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateBetween(String value1, String value2) {
            addCriterion("REG_DATE between", value1, value2, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateNotBetween(String value1, String value2) {
            addCriterion("REG_DATE not between", value1, value2, "regDate");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNull() {
            addCriterion("GROUP_ID is null");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNotNull() {
            addCriterion("GROUP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(String value) {
            addCriterion("GROUP_ID =", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotEqualTo(String value) {
            addCriterion("GROUP_ID <>", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThan(String value) {
            addCriterion("GROUP_ID >", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_ID >=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThan(String value) {
            addCriterion("GROUP_ID <", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThanOrEqualTo(String value) {
            addCriterion("GROUP_ID <=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLike(String value) {
            addCriterion("GROUP_ID like", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotLike(String value) {
            addCriterion("GROUP_ID not like", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIn(List<String> values) {
            addCriterion("GROUP_ID in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotIn(List<String> values) {
            addCriterion("GROUP_ID not in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdBetween(String value1, String value2) {
            addCriterion("GROUP_ID between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotBetween(String value1, String value2) {
            addCriterion("GROUP_ID not between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNull() {
            addCriterion("GROUP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("GROUP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("GROUP_NAME =", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("GROUP_NAME <>", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("GROUP_NAME >", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_NAME >=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("GROUP_NAME <", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("GROUP_NAME <=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("GROUP_NAME like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("GROUP_NAME not like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIn(List<String> values) {
            addCriterion("GROUP_NAME in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotIn(List<String> values) {
            addCriterion("GROUP_NAME not in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("GROUP_NAME between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("GROUP_NAME not between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupRoleIdIsNull() {
            addCriterion("GROUP_ROLE_ID is null");
            return (Criteria) this;
        }

        public Criteria andGroupRoleIdIsNotNull() {
            addCriterion("GROUP_ROLE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGroupRoleIdEqualTo(String value) {
            addCriterion("GROUP_ROLE_ID =", value, "groupRoleId");
            return (Criteria) this;
        }

        public Criteria andGroupRoleIdNotEqualTo(String value) {
            addCriterion("GROUP_ROLE_ID <>", value, "groupRoleId");
            return (Criteria) this;
        }

        public Criteria andGroupRoleIdGreaterThan(String value) {
            addCriterion("GROUP_ROLE_ID >", value, "groupRoleId");
            return (Criteria) this;
        }

        public Criteria andGroupRoleIdGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_ROLE_ID >=", value, "groupRoleId");
            return (Criteria) this;
        }

        public Criteria andGroupRoleIdLessThan(String value) {
            addCriterion("GROUP_ROLE_ID <", value, "groupRoleId");
            return (Criteria) this;
        }

        public Criteria andGroupRoleIdLessThanOrEqualTo(String value) {
            addCriterion("GROUP_ROLE_ID <=", value, "groupRoleId");
            return (Criteria) this;
        }

        public Criteria andGroupRoleIdLike(String value) {
            addCriterion("GROUP_ROLE_ID like", value, "groupRoleId");
            return (Criteria) this;
        }

        public Criteria andGroupRoleIdNotLike(String value) {
            addCriterion("GROUP_ROLE_ID not like", value, "groupRoleId");
            return (Criteria) this;
        }

        public Criteria andGroupRoleIdIn(List<String> values) {
            addCriterion("GROUP_ROLE_ID in", values, "groupRoleId");
            return (Criteria) this;
        }

        public Criteria andGroupRoleIdNotIn(List<String> values) {
            addCriterion("GROUP_ROLE_ID not in", values, "groupRoleId");
            return (Criteria) this;
        }

        public Criteria andGroupRoleIdBetween(String value1, String value2) {
            addCriterion("GROUP_ROLE_ID between", value1, value2, "groupRoleId");
            return (Criteria) this;
        }

        public Criteria andGroupRoleIdNotBetween(String value1, String value2) {
            addCriterion("GROUP_ROLE_ID not between", value1, value2, "groupRoleId");
            return (Criteria) this;
        }

        public Criteria andGroupRoleNameIsNull() {
            addCriterion("GROUP_ROLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGroupRoleNameIsNotNull() {
            addCriterion("GROUP_ROLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGroupRoleNameEqualTo(String value) {
            addCriterion("GROUP_ROLE_NAME =", value, "groupRoleName");
            return (Criteria) this;
        }

        public Criteria andGroupRoleNameNotEqualTo(String value) {
            addCriterion("GROUP_ROLE_NAME <>", value, "groupRoleName");
            return (Criteria) this;
        }

        public Criteria andGroupRoleNameGreaterThan(String value) {
            addCriterion("GROUP_ROLE_NAME >", value, "groupRoleName");
            return (Criteria) this;
        }

        public Criteria andGroupRoleNameGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_ROLE_NAME >=", value, "groupRoleName");
            return (Criteria) this;
        }

        public Criteria andGroupRoleNameLessThan(String value) {
            addCriterion("GROUP_ROLE_NAME <", value, "groupRoleName");
            return (Criteria) this;
        }

        public Criteria andGroupRoleNameLessThanOrEqualTo(String value) {
            addCriterion("GROUP_ROLE_NAME <=", value, "groupRoleName");
            return (Criteria) this;
        }

        public Criteria andGroupRoleNameLike(String value) {
            addCriterion("GROUP_ROLE_NAME like", value, "groupRoleName");
            return (Criteria) this;
        }

        public Criteria andGroupRoleNameNotLike(String value) {
            addCriterion("GROUP_ROLE_NAME not like", value, "groupRoleName");
            return (Criteria) this;
        }

        public Criteria andGroupRoleNameIn(List<String> values) {
            addCriterion("GROUP_ROLE_NAME in", values, "groupRoleName");
            return (Criteria) this;
        }

        public Criteria andGroupRoleNameNotIn(List<String> values) {
            addCriterion("GROUP_ROLE_NAME not in", values, "groupRoleName");
            return (Criteria) this;
        }

        public Criteria andGroupRoleNameBetween(String value1, String value2) {
            addCriterion("GROUP_ROLE_NAME between", value1, value2, "groupRoleName");
            return (Criteria) this;
        }

        public Criteria andGroupRoleNameNotBetween(String value1, String value2) {
            addCriterion("GROUP_ROLE_NAME not between", value1, value2, "groupRoleName");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseFlagIsNull() {
            addCriterion("DOCTOR_LICENSE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseFlagIsNotNull() {
            addCriterion("DOCTOR_LICENSE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseFlagEqualTo(String value) {
            addCriterion("DOCTOR_LICENSE_FLAG =", value, "doctorLicenseFlag");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseFlagNotEqualTo(String value) {
            addCriterion("DOCTOR_LICENSE_FLAG <>", value, "doctorLicenseFlag");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseFlagGreaterThan(String value) {
            addCriterion("DOCTOR_LICENSE_FLAG >", value, "doctorLicenseFlag");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseFlagGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_LICENSE_FLAG >=", value, "doctorLicenseFlag");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseFlagLessThan(String value) {
            addCriterion("DOCTOR_LICENSE_FLAG <", value, "doctorLicenseFlag");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseFlagLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_LICENSE_FLAG <=", value, "doctorLicenseFlag");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseFlagLike(String value) {
            addCriterion("DOCTOR_LICENSE_FLAG like", value, "doctorLicenseFlag");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseFlagNotLike(String value) {
            addCriterion("DOCTOR_LICENSE_FLAG not like", value, "doctorLicenseFlag");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseFlagIn(List<String> values) {
            addCriterion("DOCTOR_LICENSE_FLAG in", values, "doctorLicenseFlag");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseFlagNotIn(List<String> values) {
            addCriterion("DOCTOR_LICENSE_FLAG not in", values, "doctorLicenseFlag");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseFlagBetween(String value1, String value2) {
            addCriterion("DOCTOR_LICENSE_FLAG between", value1, value2, "doctorLicenseFlag");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseFlagNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_LICENSE_FLAG not between", value1, value2, "doctorLicenseFlag");
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

        public Criteria andResearchDirectionIsNull() {
            addCriterion("RESEARCH_DIRECTION is null");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionIsNotNull() {
            addCriterion("RESEARCH_DIRECTION is not null");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionEqualTo(String value) {
            addCriterion("RESEARCH_DIRECTION =", value, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionNotEqualTo(String value) {
            addCriterion("RESEARCH_DIRECTION <>", value, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionGreaterThan(String value) {
            addCriterion("RESEARCH_DIRECTION >", value, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionGreaterThanOrEqualTo(String value) {
            addCriterion("RESEARCH_DIRECTION >=", value, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionLessThan(String value) {
            addCriterion("RESEARCH_DIRECTION <", value, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionLessThanOrEqualTo(String value) {
            addCriterion("RESEARCH_DIRECTION <=", value, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionLike(String value) {
            addCriterion("RESEARCH_DIRECTION like", value, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionNotLike(String value) {
            addCriterion("RESEARCH_DIRECTION not like", value, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionIn(List<String> values) {
            addCriterion("RESEARCH_DIRECTION in", values, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionNotIn(List<String> values) {
            addCriterion("RESEARCH_DIRECTION not in", values, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionBetween(String value1, String value2) {
            addCriterion("RESEARCH_DIRECTION between", value1, value2, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionNotBetween(String value1, String value2) {
            addCriterion("RESEARCH_DIRECTION not between", value1, value2, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andSpecializedIdIsNull() {
            addCriterion("SPECIALIZED_ID is null");
            return (Criteria) this;
        }

        public Criteria andSpecializedIdIsNotNull() {
            addCriterion("SPECIALIZED_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSpecializedIdEqualTo(String value) {
            addCriterion("SPECIALIZED_ID =", value, "specializedId");
            return (Criteria) this;
        }

        public Criteria andSpecializedIdNotEqualTo(String value) {
            addCriterion("SPECIALIZED_ID <>", value, "specializedId");
            return (Criteria) this;
        }

        public Criteria andSpecializedIdGreaterThan(String value) {
            addCriterion("SPECIALIZED_ID >", value, "specializedId");
            return (Criteria) this;
        }

        public Criteria andSpecializedIdGreaterThanOrEqualTo(String value) {
            addCriterion("SPECIALIZED_ID >=", value, "specializedId");
            return (Criteria) this;
        }

        public Criteria andSpecializedIdLessThan(String value) {
            addCriterion("SPECIALIZED_ID <", value, "specializedId");
            return (Criteria) this;
        }

        public Criteria andSpecializedIdLessThanOrEqualTo(String value) {
            addCriterion("SPECIALIZED_ID <=", value, "specializedId");
            return (Criteria) this;
        }

        public Criteria andSpecializedIdLike(String value) {
            addCriterion("SPECIALIZED_ID like", value, "specializedId");
            return (Criteria) this;
        }

        public Criteria andSpecializedIdNotLike(String value) {
            addCriterion("SPECIALIZED_ID not like", value, "specializedId");
            return (Criteria) this;
        }

        public Criteria andSpecializedIdIn(List<String> values) {
            addCriterion("SPECIALIZED_ID in", values, "specializedId");
            return (Criteria) this;
        }

        public Criteria andSpecializedIdNotIn(List<String> values) {
            addCriterion("SPECIALIZED_ID not in", values, "specializedId");
            return (Criteria) this;
        }

        public Criteria andSpecializedIdBetween(String value1, String value2) {
            addCriterion("SPECIALIZED_ID between", value1, value2, "specializedId");
            return (Criteria) this;
        }

        public Criteria andSpecializedIdNotBetween(String value1, String value2) {
            addCriterion("SPECIALIZED_ID not between", value1, value2, "specializedId");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoIsNull() {
            addCriterion("DOCTOR_LICENSE_NO is null");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoIsNotNull() {
            addCriterion("DOCTOR_LICENSE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoEqualTo(String value) {
            addCriterion("DOCTOR_LICENSE_NO =", value, "doctorLicenseNo");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoNotEqualTo(String value) {
            addCriterion("DOCTOR_LICENSE_NO <>", value, "doctorLicenseNo");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoGreaterThan(String value) {
            addCriterion("DOCTOR_LICENSE_NO >", value, "doctorLicenseNo");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_LICENSE_NO >=", value, "doctorLicenseNo");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoLessThan(String value) {
            addCriterion("DOCTOR_LICENSE_NO <", value, "doctorLicenseNo");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_LICENSE_NO <=", value, "doctorLicenseNo");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoLike(String value) {
            addCriterion("DOCTOR_LICENSE_NO like", value, "doctorLicenseNo");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoNotLike(String value) {
            addCriterion("DOCTOR_LICENSE_NO not like", value, "doctorLicenseNo");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoIn(List<String> values) {
            addCriterion("DOCTOR_LICENSE_NO in", values, "doctorLicenseNo");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoNotIn(List<String> values) {
            addCriterion("DOCTOR_LICENSE_NO not in", values, "doctorLicenseNo");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoBetween(String value1, String value2) {
            addCriterion("DOCTOR_LICENSE_NO between", value1, value2, "doctorLicenseNo");
            return (Criteria) this;
        }

        public Criteria andDoctorLicenseNoNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_LICENSE_NO not between", value1, value2, "doctorLicenseNo");
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

        public Criteria andDegreeCategoryIdIsNull() {
            addCriterion("DEGREE_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryIdIsNotNull() {
            addCriterion("DEGREE_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryIdEqualTo(String value) {
            addCriterion("DEGREE_CATEGORY_ID =", value, "degreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryIdNotEqualTo(String value) {
            addCriterion("DEGREE_CATEGORY_ID <>", value, "degreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryIdGreaterThan(String value) {
            addCriterion("DEGREE_CATEGORY_ID >", value, "degreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("DEGREE_CATEGORY_ID >=", value, "degreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryIdLessThan(String value) {
            addCriterion("DEGREE_CATEGORY_ID <", value, "degreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("DEGREE_CATEGORY_ID <=", value, "degreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryIdLike(String value) {
            addCriterion("DEGREE_CATEGORY_ID like", value, "degreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryIdNotLike(String value) {
            addCriterion("DEGREE_CATEGORY_ID not like", value, "degreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryIdIn(List<String> values) {
            addCriterion("DEGREE_CATEGORY_ID in", values, "degreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryIdNotIn(List<String> values) {
            addCriterion("DEGREE_CATEGORY_ID not in", values, "degreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryIdBetween(String value1, String value2) {
            addCriterion("DEGREE_CATEGORY_ID between", value1, value2, "degreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryIdNotBetween(String value1, String value2) {
            addCriterion("DEGREE_CATEGORY_ID not between", value1, value2, "degreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryNameIsNull() {
            addCriterion("DEGREE_CATEGORY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryNameIsNotNull() {
            addCriterion("DEGREE_CATEGORY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryNameEqualTo(String value) {
            addCriterion("DEGREE_CATEGORY_NAME =", value, "degreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryNameNotEqualTo(String value) {
            addCriterion("DEGREE_CATEGORY_NAME <>", value, "degreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryNameGreaterThan(String value) {
            addCriterion("DEGREE_CATEGORY_NAME >", value, "degreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEGREE_CATEGORY_NAME >=", value, "degreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryNameLessThan(String value) {
            addCriterion("DEGREE_CATEGORY_NAME <", value, "degreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("DEGREE_CATEGORY_NAME <=", value, "degreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryNameLike(String value) {
            addCriterion("DEGREE_CATEGORY_NAME like", value, "degreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryNameNotLike(String value) {
            addCriterion("DEGREE_CATEGORY_NAME not like", value, "degreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryNameIn(List<String> values) {
            addCriterion("DEGREE_CATEGORY_NAME in", values, "degreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryNameNotIn(List<String> values) {
            addCriterion("DEGREE_CATEGORY_NAME not in", values, "degreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryNameBetween(String value1, String value2) {
            addCriterion("DEGREE_CATEGORY_NAME between", value1, value2, "degreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andDegreeCategoryNameNotBetween(String value1, String value2) {
            addCriterion("DEGREE_CATEGORY_NAME not between", value1, value2, "degreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdIsNull() {
            addCriterion("WORK_ORG_LEVEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdIsNotNull() {
            addCriterion("WORK_ORG_LEVEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdEqualTo(String value) {
            addCriterion("WORK_ORG_LEVEL_ID =", value, "workOrgLevelId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdNotEqualTo(String value) {
            addCriterion("WORK_ORG_LEVEL_ID <>", value, "workOrgLevelId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdGreaterThan(String value) {
            addCriterion("WORK_ORG_LEVEL_ID >", value, "workOrgLevelId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_ORG_LEVEL_ID >=", value, "workOrgLevelId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdLessThan(String value) {
            addCriterion("WORK_ORG_LEVEL_ID <", value, "workOrgLevelId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdLessThanOrEqualTo(String value) {
            addCriterion("WORK_ORG_LEVEL_ID <=", value, "workOrgLevelId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdLike(String value) {
            addCriterion("WORK_ORG_LEVEL_ID like", value, "workOrgLevelId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdNotLike(String value) {
            addCriterion("WORK_ORG_LEVEL_ID not like", value, "workOrgLevelId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdIn(List<String> values) {
            addCriterion("WORK_ORG_LEVEL_ID in", values, "workOrgLevelId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdNotIn(List<String> values) {
            addCriterion("WORK_ORG_LEVEL_ID not in", values, "workOrgLevelId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdBetween(String value1, String value2) {
            addCriterion("WORK_ORG_LEVEL_ID between", value1, value2, "workOrgLevelId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdNotBetween(String value1, String value2) {
            addCriterion("WORK_ORG_LEVEL_ID not between", value1, value2, "workOrgLevelId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameIsNull() {
            addCriterion("WORK_ORG_LEVEL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameIsNotNull() {
            addCriterion("WORK_ORG_LEVEL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameEqualTo(String value) {
            addCriterion("WORK_ORG_LEVEL_NAME =", value, "workOrgLevelName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameNotEqualTo(String value) {
            addCriterion("WORK_ORG_LEVEL_NAME <>", value, "workOrgLevelName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameGreaterThan(String value) {
            addCriterion("WORK_ORG_LEVEL_NAME >", value, "workOrgLevelName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_ORG_LEVEL_NAME >=", value, "workOrgLevelName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameLessThan(String value) {
            addCriterion("WORK_ORG_LEVEL_NAME <", value, "workOrgLevelName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameLessThanOrEqualTo(String value) {
            addCriterion("WORK_ORG_LEVEL_NAME <=", value, "workOrgLevelName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameLike(String value) {
            addCriterion("WORK_ORG_LEVEL_NAME like", value, "workOrgLevelName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameNotLike(String value) {
            addCriterion("WORK_ORG_LEVEL_NAME not like", value, "workOrgLevelName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameIn(List<String> values) {
            addCriterion("WORK_ORG_LEVEL_NAME in", values, "workOrgLevelName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameNotIn(List<String> values) {
            addCriterion("WORK_ORG_LEVEL_NAME not in", values, "workOrgLevelName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameBetween(String value1, String value2) {
            addCriterion("WORK_ORG_LEVEL_NAME between", value1, value2, "workOrgLevelName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameNotBetween(String value1, String value2) {
            addCriterion("WORK_ORG_LEVEL_NAME not between", value1, value2, "workOrgLevelName");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateIsNull() {
            addCriterion("IS_YEAR_GRADUATE is null");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateIsNotNull() {
            addCriterion("IS_YEAR_GRADUATE is not null");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateEqualTo(String value) {
            addCriterion("IS_YEAR_GRADUATE =", value, "isYearGraduate");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateNotEqualTo(String value) {
            addCriterion("IS_YEAR_GRADUATE <>", value, "isYearGraduate");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateGreaterThan(String value) {
            addCriterion("IS_YEAR_GRADUATE >", value, "isYearGraduate");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateGreaterThanOrEqualTo(String value) {
            addCriterion("IS_YEAR_GRADUATE >=", value, "isYearGraduate");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateLessThan(String value) {
            addCriterion("IS_YEAR_GRADUATE <", value, "isYearGraduate");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateLessThanOrEqualTo(String value) {
            addCriterion("IS_YEAR_GRADUATE <=", value, "isYearGraduate");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateLike(String value) {
            addCriterion("IS_YEAR_GRADUATE like", value, "isYearGraduate");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateNotLike(String value) {
            addCriterion("IS_YEAR_GRADUATE not like", value, "isYearGraduate");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateIn(List<String> values) {
            addCriterion("IS_YEAR_GRADUATE in", values, "isYearGraduate");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateNotIn(List<String> values) {
            addCriterion("IS_YEAR_GRADUATE not in", values, "isYearGraduate");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateBetween(String value1, String value2) {
            addCriterion("IS_YEAR_GRADUATE between", value1, value2, "isYearGraduate");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateNotBetween(String value1, String value2) {
            addCriterion("IS_YEAR_GRADUATE not between", value1, value2, "isYearGraduate");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdIsNull() {
            addCriterion("WORK_ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdIsNotNull() {
            addCriterion("WORK_ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdEqualTo(String value) {
            addCriterion("WORK_ORG_ID =", value, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdNotEqualTo(String value) {
            addCriterion("WORK_ORG_ID <>", value, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdGreaterThan(String value) {
            addCriterion("WORK_ORG_ID >", value, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_ORG_ID >=", value, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdLessThan(String value) {
            addCriterion("WORK_ORG_ID <", value, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdLessThanOrEqualTo(String value) {
            addCriterion("WORK_ORG_ID <=", value, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdLike(String value) {
            addCriterion("WORK_ORG_ID like", value, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdNotLike(String value) {
            addCriterion("WORK_ORG_ID not like", value, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdIn(List<String> values) {
            addCriterion("WORK_ORG_ID in", values, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdNotIn(List<String> values) {
            addCriterion("WORK_ORG_ID not in", values, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdBetween(String value1, String value2) {
            addCriterion("WORK_ORG_ID between", value1, value2, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgIdNotBetween(String value1, String value2) {
            addCriterion("WORK_ORG_ID not between", value1, value2, "workOrgId");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusIdIsNull() {
            addCriterion("GRADUATION_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusIdIsNotNull() {
            addCriterion("GRADUATION_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusIdEqualTo(String value) {
            addCriterion("GRADUATION_STATUS_ID =", value, "graduationStatusId");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusIdNotEqualTo(String value) {
            addCriterion("GRADUATION_STATUS_ID <>", value, "graduationStatusId");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusIdGreaterThan(String value) {
            addCriterion("GRADUATION_STATUS_ID >", value, "graduationStatusId");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATION_STATUS_ID >=", value, "graduationStatusId");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusIdLessThan(String value) {
            addCriterion("GRADUATION_STATUS_ID <", value, "graduationStatusId");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusIdLessThanOrEqualTo(String value) {
            addCriterion("GRADUATION_STATUS_ID <=", value, "graduationStatusId");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusIdLike(String value) {
            addCriterion("GRADUATION_STATUS_ID like", value, "graduationStatusId");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusIdNotLike(String value) {
            addCriterion("GRADUATION_STATUS_ID not like", value, "graduationStatusId");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusIdIn(List<String> values) {
            addCriterion("GRADUATION_STATUS_ID in", values, "graduationStatusId");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusIdNotIn(List<String> values) {
            addCriterion("GRADUATION_STATUS_ID not in", values, "graduationStatusId");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusIdBetween(String value1, String value2) {
            addCriterion("GRADUATION_STATUS_ID between", value1, value2, "graduationStatusId");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusIdNotBetween(String value1, String value2) {
            addCriterion("GRADUATION_STATUS_ID not between", value1, value2, "graduationStatusId");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusNameIsNull() {
            addCriterion("GRADUATION_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusNameIsNotNull() {
            addCriterion("GRADUATION_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusNameEqualTo(String value) {
            addCriterion("GRADUATION_STATUS_NAME =", value, "graduationStatusName");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusNameNotEqualTo(String value) {
            addCriterion("GRADUATION_STATUS_NAME <>", value, "graduationStatusName");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusNameGreaterThan(String value) {
            addCriterion("GRADUATION_STATUS_NAME >", value, "graduationStatusName");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATION_STATUS_NAME >=", value, "graduationStatusName");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusNameLessThan(String value) {
            addCriterion("GRADUATION_STATUS_NAME <", value, "graduationStatusName");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusNameLessThanOrEqualTo(String value) {
            addCriterion("GRADUATION_STATUS_NAME <=", value, "graduationStatusName");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusNameLike(String value) {
            addCriterion("GRADUATION_STATUS_NAME like", value, "graduationStatusName");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusNameNotLike(String value) {
            addCriterion("GRADUATION_STATUS_NAME not like", value, "graduationStatusName");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusNameIn(List<String> values) {
            addCriterion("GRADUATION_STATUS_NAME in", values, "graduationStatusName");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusNameNotIn(List<String> values) {
            addCriterion("GRADUATION_STATUS_NAME not in", values, "graduationStatusName");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusNameBetween(String value1, String value2) {
            addCriterion("GRADUATION_STATUS_NAME between", value1, value2, "graduationStatusName");
            return (Criteria) this;
        }

        public Criteria andGraduationStatusNameNotBetween(String value1, String value2) {
            addCriterion("GRADUATION_STATUS_NAME not between", value1, value2, "graduationStatusName");
            return (Criteria) this;
        }

        public Criteria andDisagreeReasonIsNull() {
            addCriterion("DISAGREE_REASON is null");
            return (Criteria) this;
        }

        public Criteria andDisagreeReasonIsNotNull() {
            addCriterion("DISAGREE_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andDisagreeReasonEqualTo(String value) {
            addCriterion("DISAGREE_REASON =", value, "disagreeReason");
            return (Criteria) this;
        }

        public Criteria andDisagreeReasonNotEqualTo(String value) {
            addCriterion("DISAGREE_REASON <>", value, "disagreeReason");
            return (Criteria) this;
        }

        public Criteria andDisagreeReasonGreaterThan(String value) {
            addCriterion("DISAGREE_REASON >", value, "disagreeReason");
            return (Criteria) this;
        }

        public Criteria andDisagreeReasonGreaterThanOrEqualTo(String value) {
            addCriterion("DISAGREE_REASON >=", value, "disagreeReason");
            return (Criteria) this;
        }

        public Criteria andDisagreeReasonLessThan(String value) {
            addCriterion("DISAGREE_REASON <", value, "disagreeReason");
            return (Criteria) this;
        }

        public Criteria andDisagreeReasonLessThanOrEqualTo(String value) {
            addCriterion("DISAGREE_REASON <=", value, "disagreeReason");
            return (Criteria) this;
        }

        public Criteria andDisagreeReasonLike(String value) {
            addCriterion("DISAGREE_REASON like", value, "disagreeReason");
            return (Criteria) this;
        }

        public Criteria andDisagreeReasonNotLike(String value) {
            addCriterion("DISAGREE_REASON not like", value, "disagreeReason");
            return (Criteria) this;
        }

        public Criteria andDisagreeReasonIn(List<String> values) {
            addCriterion("DISAGREE_REASON in", values, "disagreeReason");
            return (Criteria) this;
        }

        public Criteria andDisagreeReasonNotIn(List<String> values) {
            addCriterion("DISAGREE_REASON not in", values, "disagreeReason");
            return (Criteria) this;
        }

        public Criteria andDisagreeReasonBetween(String value1, String value2) {
            addCriterion("DISAGREE_REASON between", value1, value2, "disagreeReason");
            return (Criteria) this;
        }

        public Criteria andDisagreeReasonNotBetween(String value1, String value2) {
            addCriterion("DISAGREE_REASON not between", value1, value2, "disagreeReason");
            return (Criteria) this;
        }

        public Criteria andCompleteStartDateIsNull() {
            addCriterion("COMPLETE_START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCompleteStartDateIsNotNull() {
            addCriterion("COMPLETE_START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteStartDateEqualTo(String value) {
            addCriterion("COMPLETE_START_DATE =", value, "completeStartDate");
            return (Criteria) this;
        }

        public Criteria andCompleteStartDateNotEqualTo(String value) {
            addCriterion("COMPLETE_START_DATE <>", value, "completeStartDate");
            return (Criteria) this;
        }

        public Criteria andCompleteStartDateGreaterThan(String value) {
            addCriterion("COMPLETE_START_DATE >", value, "completeStartDate");
            return (Criteria) this;
        }

        public Criteria andCompleteStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("COMPLETE_START_DATE >=", value, "completeStartDate");
            return (Criteria) this;
        }

        public Criteria andCompleteStartDateLessThan(String value) {
            addCriterion("COMPLETE_START_DATE <", value, "completeStartDate");
            return (Criteria) this;
        }

        public Criteria andCompleteStartDateLessThanOrEqualTo(String value) {
            addCriterion("COMPLETE_START_DATE <=", value, "completeStartDate");
            return (Criteria) this;
        }

        public Criteria andCompleteStartDateLike(String value) {
            addCriterion("COMPLETE_START_DATE like", value, "completeStartDate");
            return (Criteria) this;
        }

        public Criteria andCompleteStartDateNotLike(String value) {
            addCriterion("COMPLETE_START_DATE not like", value, "completeStartDate");
            return (Criteria) this;
        }

        public Criteria andCompleteStartDateIn(List<String> values) {
            addCriterion("COMPLETE_START_DATE in", values, "completeStartDate");
            return (Criteria) this;
        }

        public Criteria andCompleteStartDateNotIn(List<String> values) {
            addCriterion("COMPLETE_START_DATE not in", values, "completeStartDate");
            return (Criteria) this;
        }

        public Criteria andCompleteStartDateBetween(String value1, String value2) {
            addCriterion("COMPLETE_START_DATE between", value1, value2, "completeStartDate");
            return (Criteria) this;
        }

        public Criteria andCompleteStartDateNotBetween(String value1, String value2) {
            addCriterion("COMPLETE_START_DATE not between", value1, value2, "completeStartDate");
            return (Criteria) this;
        }

        public Criteria andCompleteEndDateIsNull() {
            addCriterion("COMPLETE_END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCompleteEndDateIsNotNull() {
            addCriterion("COMPLETE_END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteEndDateEqualTo(String value) {
            addCriterion("COMPLETE_END_DATE =", value, "completeEndDate");
            return (Criteria) this;
        }

        public Criteria andCompleteEndDateNotEqualTo(String value) {
            addCriterion("COMPLETE_END_DATE <>", value, "completeEndDate");
            return (Criteria) this;
        }

        public Criteria andCompleteEndDateGreaterThan(String value) {
            addCriterion("COMPLETE_END_DATE >", value, "completeEndDate");
            return (Criteria) this;
        }

        public Criteria andCompleteEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("COMPLETE_END_DATE >=", value, "completeEndDate");
            return (Criteria) this;
        }

        public Criteria andCompleteEndDateLessThan(String value) {
            addCriterion("COMPLETE_END_DATE <", value, "completeEndDate");
            return (Criteria) this;
        }

        public Criteria andCompleteEndDateLessThanOrEqualTo(String value) {
            addCriterion("COMPLETE_END_DATE <=", value, "completeEndDate");
            return (Criteria) this;
        }

        public Criteria andCompleteEndDateLike(String value) {
            addCriterion("COMPLETE_END_DATE like", value, "completeEndDate");
            return (Criteria) this;
        }

        public Criteria andCompleteEndDateNotLike(String value) {
            addCriterion("COMPLETE_END_DATE not like", value, "completeEndDate");
            return (Criteria) this;
        }

        public Criteria andCompleteEndDateIn(List<String> values) {
            addCriterion("COMPLETE_END_DATE in", values, "completeEndDate");
            return (Criteria) this;
        }

        public Criteria andCompleteEndDateNotIn(List<String> values) {
            addCriterion("COMPLETE_END_DATE not in", values, "completeEndDate");
            return (Criteria) this;
        }

        public Criteria andCompleteEndDateBetween(String value1, String value2) {
            addCriterion("COMPLETE_END_DATE between", value1, value2, "completeEndDate");
            return (Criteria) this;
        }

        public Criteria andCompleteEndDateNotBetween(String value1, String value2) {
            addCriterion("COMPLETE_END_DATE not between", value1, value2, "completeEndDate");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherFlowIsNull() {
            addCriterion("DISCIPLE_TEACHER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherFlowIsNotNull() {
            addCriterion("DISCIPLE_TEACHER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherFlowEqualTo(String value) {
            addCriterion("DISCIPLE_TEACHER_FLOW =", value, "discipleTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherFlowNotEqualTo(String value) {
            addCriterion("DISCIPLE_TEACHER_FLOW <>", value, "discipleTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherFlowGreaterThan(String value) {
            addCriterion("DISCIPLE_TEACHER_FLOW >", value, "discipleTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DISCIPLE_TEACHER_FLOW >=", value, "discipleTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherFlowLessThan(String value) {
            addCriterion("DISCIPLE_TEACHER_FLOW <", value, "discipleTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherFlowLessThanOrEqualTo(String value) {
            addCriterion("DISCIPLE_TEACHER_FLOW <=", value, "discipleTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherFlowLike(String value) {
            addCriterion("DISCIPLE_TEACHER_FLOW like", value, "discipleTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherFlowNotLike(String value) {
            addCriterion("DISCIPLE_TEACHER_FLOW not like", value, "discipleTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherFlowIn(List<String> values) {
            addCriterion("DISCIPLE_TEACHER_FLOW in", values, "discipleTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherFlowNotIn(List<String> values) {
            addCriterion("DISCIPLE_TEACHER_FLOW not in", values, "discipleTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherFlowBetween(String value1, String value2) {
            addCriterion("DISCIPLE_TEACHER_FLOW between", value1, value2, "discipleTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherFlowNotBetween(String value1, String value2) {
            addCriterion("DISCIPLE_TEACHER_FLOW not between", value1, value2, "discipleTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherNameIsNull() {
            addCriterion("DISCIPLE_TEACHER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherNameIsNotNull() {
            addCriterion("DISCIPLE_TEACHER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherNameEqualTo(String value) {
            addCriterion("DISCIPLE_TEACHER_NAME =", value, "discipleTeacherName");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherNameNotEqualTo(String value) {
            addCriterion("DISCIPLE_TEACHER_NAME <>", value, "discipleTeacherName");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherNameGreaterThan(String value) {
            addCriterion("DISCIPLE_TEACHER_NAME >", value, "discipleTeacherName");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherNameGreaterThanOrEqualTo(String value) {
            addCriterion("DISCIPLE_TEACHER_NAME >=", value, "discipleTeacherName");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherNameLessThan(String value) {
            addCriterion("DISCIPLE_TEACHER_NAME <", value, "discipleTeacherName");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherNameLessThanOrEqualTo(String value) {
            addCriterion("DISCIPLE_TEACHER_NAME <=", value, "discipleTeacherName");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherNameLike(String value) {
            addCriterion("DISCIPLE_TEACHER_NAME like", value, "discipleTeacherName");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherNameNotLike(String value) {
            addCriterion("DISCIPLE_TEACHER_NAME not like", value, "discipleTeacherName");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherNameIn(List<String> values) {
            addCriterion("DISCIPLE_TEACHER_NAME in", values, "discipleTeacherName");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherNameNotIn(List<String> values) {
            addCriterion("DISCIPLE_TEACHER_NAME not in", values, "discipleTeacherName");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherNameBetween(String value1, String value2) {
            addCriterion("DISCIPLE_TEACHER_NAME between", value1, value2, "discipleTeacherName");
            return (Criteria) this;
        }

        public Criteria andDiscipleTeacherNameNotBetween(String value1, String value2) {
            addCriterion("DISCIPLE_TEACHER_NAME not between", value1, value2, "discipleTeacherName");
            return (Criteria) this;
        }

        public Criteria andGraduationYearIsNull() {
            addCriterion("GRADUATION_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andGraduationYearIsNotNull() {
            addCriterion("GRADUATION_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationYearEqualTo(String value) {
            addCriterion("GRADUATION_YEAR =", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearNotEqualTo(String value) {
            addCriterion("GRADUATION_YEAR <>", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearGreaterThan(String value) {
            addCriterion("GRADUATION_YEAR >", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATION_YEAR >=", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearLessThan(String value) {
            addCriterion("GRADUATION_YEAR <", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearLessThanOrEqualTo(String value) {
            addCriterion("GRADUATION_YEAR <=", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearLike(String value) {
            addCriterion("GRADUATION_YEAR like", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearNotLike(String value) {
            addCriterion("GRADUATION_YEAR not like", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearIn(List<String> values) {
            addCriterion("GRADUATION_YEAR in", values, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearNotIn(List<String> values) {
            addCriterion("GRADUATION_YEAR not in", values, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearBetween(String value1, String value2) {
            addCriterion("GRADUATION_YEAR between", value1, value2, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearNotBetween(String value1, String value2) {
            addCriterion("GRADUATION_YEAR not between", value1, value2, "graduationYear");
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

        public Criteria andCompanyTypeIsNull() {
            addCriterion("COMPANY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeIsNotNull() {
            addCriterion("COMPANY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeEqualTo(String value) {
            addCriterion("COMPANY_TYPE =", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeNotEqualTo(String value) {
            addCriterion("COMPANY_TYPE <>", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeGreaterThan(String value) {
            addCriterion("COMPANY_TYPE >", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("COMPANY_TYPE >=", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeLessThan(String value) {
            addCriterion("COMPANY_TYPE <", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeLessThanOrEqualTo(String value) {
            addCriterion("COMPANY_TYPE <=", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeLike(String value) {
            addCriterion("COMPANY_TYPE like", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeNotLike(String value) {
            addCriterion("COMPANY_TYPE not like", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeIn(List<String> values) {
            addCriterion("COMPANY_TYPE in", values, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeNotIn(List<String> values) {
            addCriterion("COMPANY_TYPE not in", values, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeBetween(String value1, String value2) {
            addCriterion("COMPANY_TYPE between", value1, value2, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeNotBetween(String value1, String value2) {
            addCriterion("COMPANY_TYPE not between", value1, value2, "companyType");
            return (Criteria) this;
        }

        public Criteria andIsPartnerIsNull() {
            addCriterion("IS_PARTNER is null");
            return (Criteria) this;
        }

        public Criteria andIsPartnerIsNotNull() {
            addCriterion("IS_PARTNER is not null");
            return (Criteria) this;
        }

        public Criteria andIsPartnerEqualTo(String value) {
            addCriterion("IS_PARTNER =", value, "isPartner");
            return (Criteria) this;
        }

        public Criteria andIsPartnerNotEqualTo(String value) {
            addCriterion("IS_PARTNER <>", value, "isPartner");
            return (Criteria) this;
        }

        public Criteria andIsPartnerGreaterThan(String value) {
            addCriterion("IS_PARTNER >", value, "isPartner");
            return (Criteria) this;
        }

        public Criteria andIsPartnerGreaterThanOrEqualTo(String value) {
            addCriterion("IS_PARTNER >=", value, "isPartner");
            return (Criteria) this;
        }

        public Criteria andIsPartnerLessThan(String value) {
            addCriterion("IS_PARTNER <", value, "isPartner");
            return (Criteria) this;
        }

        public Criteria andIsPartnerLessThanOrEqualTo(String value) {
            addCriterion("IS_PARTNER <=", value, "isPartner");
            return (Criteria) this;
        }

        public Criteria andIsPartnerLike(String value) {
            addCriterion("IS_PARTNER like", value, "isPartner");
            return (Criteria) this;
        }

        public Criteria andIsPartnerNotLike(String value) {
            addCriterion("IS_PARTNER not like", value, "isPartner");
            return (Criteria) this;
        }

        public Criteria andIsPartnerIn(List<String> values) {
            addCriterion("IS_PARTNER in", values, "isPartner");
            return (Criteria) this;
        }

        public Criteria andIsPartnerNotIn(List<String> values) {
            addCriterion("IS_PARTNER not in", values, "isPartner");
            return (Criteria) this;
        }

        public Criteria andIsPartnerBetween(String value1, String value2) {
            addCriterion("IS_PARTNER between", value1, value2, "isPartner");
            return (Criteria) this;
        }

        public Criteria andIsPartnerNotBetween(String value1, String value2) {
            addCriterion("IS_PARTNER not between", value1, value2, "isPartner");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesIdIsNull() {
            addCriterion("SOURCE_PROVINCES_ID is null");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesIdIsNotNull() {
            addCriterion("SOURCE_PROVINCES_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesIdEqualTo(String value) {
            addCriterion("SOURCE_PROVINCES_ID =", value, "sourceProvincesId");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesIdNotEqualTo(String value) {
            addCriterion("SOURCE_PROVINCES_ID <>", value, "sourceProvincesId");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesIdGreaterThan(String value) {
            addCriterion("SOURCE_PROVINCES_ID >", value, "sourceProvincesId");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesIdGreaterThanOrEqualTo(String value) {
            addCriterion("SOURCE_PROVINCES_ID >=", value, "sourceProvincesId");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesIdLessThan(String value) {
            addCriterion("SOURCE_PROVINCES_ID <", value, "sourceProvincesId");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesIdLessThanOrEqualTo(String value) {
            addCriterion("SOURCE_PROVINCES_ID <=", value, "sourceProvincesId");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesIdLike(String value) {
            addCriterion("SOURCE_PROVINCES_ID like", value, "sourceProvincesId");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesIdNotLike(String value) {
            addCriterion("SOURCE_PROVINCES_ID not like", value, "sourceProvincesId");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesIdIn(List<String> values) {
            addCriterion("SOURCE_PROVINCES_ID in", values, "sourceProvincesId");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesIdNotIn(List<String> values) {
            addCriterion("SOURCE_PROVINCES_ID not in", values, "sourceProvincesId");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesIdBetween(String value1, String value2) {
            addCriterion("SOURCE_PROVINCES_ID between", value1, value2, "sourceProvincesId");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesIdNotBetween(String value1, String value2) {
            addCriterion("SOURCE_PROVINCES_ID not between", value1, value2, "sourceProvincesId");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesNameIsNull() {
            addCriterion("SOURCE_PROVINCES_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesNameIsNotNull() {
            addCriterion("SOURCE_PROVINCES_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesNameEqualTo(String value) {
            addCriterion("SOURCE_PROVINCES_NAME =", value, "sourceProvincesName");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesNameNotEqualTo(String value) {
            addCriterion("SOURCE_PROVINCES_NAME <>", value, "sourceProvincesName");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesNameGreaterThan(String value) {
            addCriterion("SOURCE_PROVINCES_NAME >", value, "sourceProvincesName");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesNameGreaterThanOrEqualTo(String value) {
            addCriterion("SOURCE_PROVINCES_NAME >=", value, "sourceProvincesName");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesNameLessThan(String value) {
            addCriterion("SOURCE_PROVINCES_NAME <", value, "sourceProvincesName");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesNameLessThanOrEqualTo(String value) {
            addCriterion("SOURCE_PROVINCES_NAME <=", value, "sourceProvincesName");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesNameLike(String value) {
            addCriterion("SOURCE_PROVINCES_NAME like", value, "sourceProvincesName");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesNameNotLike(String value) {
            addCriterion("SOURCE_PROVINCES_NAME not like", value, "sourceProvincesName");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesNameIn(List<String> values) {
            addCriterion("SOURCE_PROVINCES_NAME in", values, "sourceProvincesName");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesNameNotIn(List<String> values) {
            addCriterion("SOURCE_PROVINCES_NAME not in", values, "sourceProvincesName");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesNameBetween(String value1, String value2) {
            addCriterion("SOURCE_PROVINCES_NAME between", value1, value2, "sourceProvincesName");
            return (Criteria) this;
        }

        public Criteria andSourceProvincesNameNotBetween(String value1, String value2) {
            addCriterion("SOURCE_PROVINCES_NAME not between", value1, value2, "sourceProvincesName");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowIsNull() {
            addCriterion("ARCHIVE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowIsNotNull() {
            addCriterion("ARCHIVE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowEqualTo(String value) {
            addCriterion("ARCHIVE_FLOW =", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowNotEqualTo(String value) {
            addCriterion("ARCHIVE_FLOW <>", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowGreaterThan(String value) {
            addCriterion("ARCHIVE_FLOW >", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ARCHIVE_FLOW >=", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowLessThan(String value) {
            addCriterion("ARCHIVE_FLOW <", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowLessThanOrEqualTo(String value) {
            addCriterion("ARCHIVE_FLOW <=", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowLike(String value) {
            addCriterion("ARCHIVE_FLOW like", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowNotLike(String value) {
            addCriterion("ARCHIVE_FLOW not like", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowIn(List<String> values) {
            addCriterion("ARCHIVE_FLOW in", values, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowNotIn(List<String> values) {
            addCriterion("ARCHIVE_FLOW not in", values, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowBetween(String value1, String value2) {
            addCriterion("ARCHIVE_FLOW between", value1, value2, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowNotBetween(String value1, String value2) {
            addCriterion("ARCHIVE_FLOW not between", value1, value2, "archiveFlow");
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