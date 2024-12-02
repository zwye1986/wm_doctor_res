package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResPaperExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResPaperExample() {
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

        public Criteria andPaperNameIsNull() {
            addCriterion("PAPER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPaperNameIsNotNull() {
            addCriterion("PAPER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPaperNameEqualTo(String value) {
            addCriterion("PAPER_NAME =", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameNotEqualTo(String value) {
            addCriterion("PAPER_NAME <>", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameGreaterThan(String value) {
            addCriterion("PAPER_NAME >", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER_NAME >=", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameLessThan(String value) {
            addCriterion("PAPER_NAME <", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameLessThanOrEqualTo(String value) {
            addCriterion("PAPER_NAME <=", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameLike(String value) {
            addCriterion("PAPER_NAME like", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameNotLike(String value) {
            addCriterion("PAPER_NAME not like", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameIn(List<String> values) {
            addCriterion("PAPER_NAME in", values, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameNotIn(List<String> values) {
            addCriterion("PAPER_NAME not in", values, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameBetween(String value1, String value2) {
            addCriterion("PAPER_NAME between", value1, value2, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameNotBetween(String value1, String value2) {
            addCriterion("PAPER_NAME not between", value1, value2, "paperName");
            return (Criteria) this;
        }

        public Criteria andSpeIdIsNull() {
            addCriterion("SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSpeIdIsNotNull() {
            addCriterion("SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSpeIdEqualTo(String value) {
            addCriterion("SPE_ID =", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotEqualTo(String value) {
            addCriterion("SPE_ID <>", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdGreaterThan(String value) {
            addCriterion("SPE_ID >", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_ID >=", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLessThan(String value) {
            addCriterion("SPE_ID <", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLessThanOrEqualTo(String value) {
            addCriterion("SPE_ID <=", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLike(String value) {
            addCriterion("SPE_ID like", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotLike(String value) {
            addCriterion("SPE_ID not like", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdIn(List<String> values) {
            addCriterion("SPE_ID in", values, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotIn(List<String> values) {
            addCriterion("SPE_ID not in", values, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdBetween(String value1, String value2) {
            addCriterion("SPE_ID between", value1, value2, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotBetween(String value1, String value2) {
            addCriterion("SPE_ID not between", value1, value2, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeNameIsNull() {
            addCriterion("SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSpeNameIsNotNull() {
            addCriterion("SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSpeNameEqualTo(String value) {
            addCriterion("SPE_NAME =", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotEqualTo(String value) {
            addCriterion("SPE_NAME <>", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameGreaterThan(String value) {
            addCriterion("SPE_NAME >", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_NAME >=", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameLessThan(String value) {
            addCriterion("SPE_NAME <", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameLessThanOrEqualTo(String value) {
            addCriterion("SPE_NAME <=", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameLike(String value) {
            addCriterion("SPE_NAME like", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotLike(String value) {
            addCriterion("SPE_NAME not like", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameIn(List<String> values) {
            addCriterion("SPE_NAME in", values, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotIn(List<String> values) {
            addCriterion("SPE_NAME not in", values, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameBetween(String value1, String value2) {
            addCriterion("SPE_NAME between", value1, value2, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotBetween(String value1, String value2) {
            addCriterion("SPE_NAME not between", value1, value2, "speName");
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

        public Criteria andOrgIdIsNull() {
            addCriterion("ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdEqualTo(String value) {
            addCriterion("ORG_ID =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(String value) {
            addCriterion("ORG_ID <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(String value) {
            addCriterion("ORG_ID >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_ID >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(String value) {
            addCriterion("ORG_ID <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_ID <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLike(String value) {
            addCriterion("ORG_ID like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotLike(String value) {
            addCriterion("ORG_ID not like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<String> values) {
            addCriterion("ORG_ID in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<String> values) {
            addCriterion("ORG_ID not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(String value1, String value2) {
            addCriterion("ORG_ID between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(String value1, String value2) {
            addCriterion("ORG_ID not between", value1, value2, "orgId");
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