package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class SchRotationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SchRotationExample() {
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

        public Criteria andPublishFlagIsNull() {
            addCriterion("PUBLISH_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andPublishFlagIsNotNull() {
            addCriterion("PUBLISH_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andPublishFlagEqualTo(String value) {
            addCriterion("PUBLISH_FLAG =", value, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagNotEqualTo(String value) {
            addCriterion("PUBLISH_FLAG <>", value, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagGreaterThan(String value) {
            addCriterion("PUBLISH_FLAG >", value, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLISH_FLAG >=", value, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagLessThan(String value) {
            addCriterion("PUBLISH_FLAG <", value, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagLessThanOrEqualTo(String value) {
            addCriterion("PUBLISH_FLAG <=", value, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagLike(String value) {
            addCriterion("PUBLISH_FLAG like", value, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagNotLike(String value) {
            addCriterion("PUBLISH_FLAG not like", value, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagIn(List<String> values) {
            addCriterion("PUBLISH_FLAG in", values, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagNotIn(List<String> values) {
            addCriterion("PUBLISH_FLAG not in", values, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagBetween(String value1, String value2) {
            addCriterion("PUBLISH_FLAG between", value1, value2, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagNotBetween(String value1, String value2) {
            addCriterion("PUBLISH_FLAG not between", value1, value2, "publishFlag");
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

        public Criteria andRotationYearIsNull() {
            addCriterion("ROTATION_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andRotationYearIsNotNull() {
            addCriterion("ROTATION_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andRotationYearEqualTo(String value) {
            addCriterion("ROTATION_YEAR =", value, "rotationYear");
            return (Criteria) this;
        }

        public Criteria andRotationYearNotEqualTo(String value) {
            addCriterion("ROTATION_YEAR <>", value, "rotationYear");
            return (Criteria) this;
        }

        public Criteria andRotationYearGreaterThan(String value) {
            addCriterion("ROTATION_YEAR >", value, "rotationYear");
            return (Criteria) this;
        }

        public Criteria andRotationYearGreaterThanOrEqualTo(String value) {
            addCriterion("ROTATION_YEAR >=", value, "rotationYear");
            return (Criteria) this;
        }

        public Criteria andRotationYearLessThan(String value) {
            addCriterion("ROTATION_YEAR <", value, "rotationYear");
            return (Criteria) this;
        }

        public Criteria andRotationYearLessThanOrEqualTo(String value) {
            addCriterion("ROTATION_YEAR <=", value, "rotationYear");
            return (Criteria) this;
        }

        public Criteria andRotationYearLike(String value) {
            addCriterion("ROTATION_YEAR like", value, "rotationYear");
            return (Criteria) this;
        }

        public Criteria andRotationYearNotLike(String value) {
            addCriterion("ROTATION_YEAR not like", value, "rotationYear");
            return (Criteria) this;
        }

        public Criteria andRotationYearIn(List<String> values) {
            addCriterion("ROTATION_YEAR in", values, "rotationYear");
            return (Criteria) this;
        }

        public Criteria andRotationYearNotIn(List<String> values) {
            addCriterion("ROTATION_YEAR not in", values, "rotationYear");
            return (Criteria) this;
        }

        public Criteria andRotationYearBetween(String value1, String value2) {
            addCriterion("ROTATION_YEAR between", value1, value2, "rotationYear");
            return (Criteria) this;
        }

        public Criteria andRotationYearNotBetween(String value1, String value2) {
            addCriterion("ROTATION_YEAR not between", value1, value2, "rotationYear");
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

        public Criteria andOrdinalIsNull() {
            addCriterion("ORDINAL is null");
            return (Criteria) this;
        }

        public Criteria andOrdinalIsNotNull() {
            addCriterion("ORDINAL is not null");
            return (Criteria) this;
        }

        public Criteria andOrdinalEqualTo(Integer value) {
            addCriterion("ORDINAL =", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotEqualTo(Integer value) {
            addCriterion("ORDINAL <>", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalGreaterThan(Integer value) {
            addCriterion("ORDINAL >", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalGreaterThanOrEqualTo(Integer value) {
            addCriterion("ORDINAL >=", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalLessThan(Integer value) {
            addCriterion("ORDINAL <", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalLessThanOrEqualTo(Integer value) {
            addCriterion("ORDINAL <=", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalIn(List<Integer> values) {
            addCriterion("ORDINAL in", values, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotIn(List<Integer> values) {
            addCriterion("ORDINAL not in", values, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalBetween(Integer value1, Integer value2) {
            addCriterion("ORDINAL between", value1, value2, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotBetween(Integer value1, Integer value2) {
            addCriterion("ORDINAL not between", value1, value2, "ordinal");
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

        public Criteria andIsCrisscrossFlagIsNull() {
            addCriterion("IS_CRISSCROSS_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andIsCrisscrossFlagIsNotNull() {
            addCriterion("IS_CRISSCROSS_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andIsCrisscrossFlagEqualTo(String value) {
            addCriterion("IS_CRISSCROSS_FLAG =", value, "isCrisscrossFlag");
            return (Criteria) this;
        }

        public Criteria andIsCrisscrossFlagNotEqualTo(String value) {
            addCriterion("IS_CRISSCROSS_FLAG <>", value, "isCrisscrossFlag");
            return (Criteria) this;
        }

        public Criteria andIsCrisscrossFlagGreaterThan(String value) {
            addCriterion("IS_CRISSCROSS_FLAG >", value, "isCrisscrossFlag");
            return (Criteria) this;
        }

        public Criteria andIsCrisscrossFlagGreaterThanOrEqualTo(String value) {
            addCriterion("IS_CRISSCROSS_FLAG >=", value, "isCrisscrossFlag");
            return (Criteria) this;
        }

        public Criteria andIsCrisscrossFlagLessThan(String value) {
            addCriterion("IS_CRISSCROSS_FLAG <", value, "isCrisscrossFlag");
            return (Criteria) this;
        }

        public Criteria andIsCrisscrossFlagLessThanOrEqualTo(String value) {
            addCriterion("IS_CRISSCROSS_FLAG <=", value, "isCrisscrossFlag");
            return (Criteria) this;
        }

        public Criteria andIsCrisscrossFlagLike(String value) {
            addCriterion("IS_CRISSCROSS_FLAG like", value, "isCrisscrossFlag");
            return (Criteria) this;
        }

        public Criteria andIsCrisscrossFlagNotLike(String value) {
            addCriterion("IS_CRISSCROSS_FLAG not like", value, "isCrisscrossFlag");
            return (Criteria) this;
        }

        public Criteria andIsCrisscrossFlagIn(List<String> values) {
            addCriterion("IS_CRISSCROSS_FLAG in", values, "isCrisscrossFlag");
            return (Criteria) this;
        }

        public Criteria andIsCrisscrossFlagNotIn(List<String> values) {
            addCriterion("IS_CRISSCROSS_FLAG not in", values, "isCrisscrossFlag");
            return (Criteria) this;
        }

        public Criteria andIsCrisscrossFlagBetween(String value1, String value2) {
            addCriterion("IS_CRISSCROSS_FLAG between", value1, value2, "isCrisscrossFlag");
            return (Criteria) this;
        }

        public Criteria andIsCrisscrossFlagNotBetween(String value1, String value2) {
            addCriterion("IS_CRISSCROSS_FLAG not between", value1, value2, "isCrisscrossFlag");
            return (Criteria) this;
        }

        public Criteria andIsStageIsNull() {
            addCriterion("IS_STAGE is null");
            return (Criteria) this;
        }

        public Criteria andIsStageIsNotNull() {
            addCriterion("IS_STAGE is not null");
            return (Criteria) this;
        }

        public Criteria andIsStageEqualTo(String value) {
            addCriterion("IS_STAGE =", value, "isStage");
            return (Criteria) this;
        }

        public Criteria andIsStageNotEqualTo(String value) {
            addCriterion("IS_STAGE <>", value, "isStage");
            return (Criteria) this;
        }

        public Criteria andIsStageGreaterThan(String value) {
            addCriterion("IS_STAGE >", value, "isStage");
            return (Criteria) this;
        }

        public Criteria andIsStageGreaterThanOrEqualTo(String value) {
            addCriterion("IS_STAGE >=", value, "isStage");
            return (Criteria) this;
        }

        public Criteria andIsStageLessThan(String value) {
            addCriterion("IS_STAGE <", value, "isStage");
            return (Criteria) this;
        }

        public Criteria andIsStageLessThanOrEqualTo(String value) {
            addCriterion("IS_STAGE <=", value, "isStage");
            return (Criteria) this;
        }

        public Criteria andIsStageLike(String value) {
            addCriterion("IS_STAGE like", value, "isStage");
            return (Criteria) this;
        }

        public Criteria andIsStageNotLike(String value) {
            addCriterion("IS_STAGE not like", value, "isStage");
            return (Criteria) this;
        }

        public Criteria andIsStageIn(List<String> values) {
            addCriterion("IS_STAGE in", values, "isStage");
            return (Criteria) this;
        }

        public Criteria andIsStageNotIn(List<String> values) {
            addCriterion("IS_STAGE not in", values, "isStage");
            return (Criteria) this;
        }

        public Criteria andIsStageBetween(String value1, String value2) {
            addCriterion("IS_STAGE between", value1, value2, "isStage");
            return (Criteria) this;
        }

        public Criteria andIsStageNotBetween(String value1, String value2) {
            addCriterion("IS_STAGE not between", value1, value2, "isStage");
            return (Criteria) this;
        }

        public Criteria andRotationTypeIdIsNull() {
            addCriterion("ROTATION_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andRotationTypeIdIsNotNull() {
            addCriterion("ROTATION_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRotationTypeIdEqualTo(String value) {
            addCriterion("ROTATION_TYPE_ID =", value, "rotationTypeId");
            return (Criteria) this;
        }

        public Criteria andRotationTypeIdNotEqualTo(String value) {
            addCriterion("ROTATION_TYPE_ID <>", value, "rotationTypeId");
            return (Criteria) this;
        }

        public Criteria andRotationTypeIdGreaterThan(String value) {
            addCriterion("ROTATION_TYPE_ID >", value, "rotationTypeId");
            return (Criteria) this;
        }

        public Criteria andRotationTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("ROTATION_TYPE_ID >=", value, "rotationTypeId");
            return (Criteria) this;
        }

        public Criteria andRotationTypeIdLessThan(String value) {
            addCriterion("ROTATION_TYPE_ID <", value, "rotationTypeId");
            return (Criteria) this;
        }

        public Criteria andRotationTypeIdLessThanOrEqualTo(String value) {
            addCriterion("ROTATION_TYPE_ID <=", value, "rotationTypeId");
            return (Criteria) this;
        }

        public Criteria andRotationTypeIdLike(String value) {
            addCriterion("ROTATION_TYPE_ID like", value, "rotationTypeId");
            return (Criteria) this;
        }

        public Criteria andRotationTypeIdNotLike(String value) {
            addCriterion("ROTATION_TYPE_ID not like", value, "rotationTypeId");
            return (Criteria) this;
        }

        public Criteria andRotationTypeIdIn(List<String> values) {
            addCriterion("ROTATION_TYPE_ID in", values, "rotationTypeId");
            return (Criteria) this;
        }

        public Criteria andRotationTypeIdNotIn(List<String> values) {
            addCriterion("ROTATION_TYPE_ID not in", values, "rotationTypeId");
            return (Criteria) this;
        }

        public Criteria andRotationTypeIdBetween(String value1, String value2) {
            addCriterion("ROTATION_TYPE_ID between", value1, value2, "rotationTypeId");
            return (Criteria) this;
        }

        public Criteria andRotationTypeIdNotBetween(String value1, String value2) {
            addCriterion("ROTATION_TYPE_ID not between", value1, value2, "rotationTypeId");
            return (Criteria) this;
        }

        public Criteria andRotationTypeNameIsNull() {
            addCriterion("ROTATION_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRotationTypeNameIsNotNull() {
            addCriterion("ROTATION_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRotationTypeNameEqualTo(String value) {
            addCriterion("ROTATION_TYPE_NAME =", value, "rotationTypeName");
            return (Criteria) this;
        }

        public Criteria andRotationTypeNameNotEqualTo(String value) {
            addCriterion("ROTATION_TYPE_NAME <>", value, "rotationTypeName");
            return (Criteria) this;
        }

        public Criteria andRotationTypeNameGreaterThan(String value) {
            addCriterion("ROTATION_TYPE_NAME >", value, "rotationTypeName");
            return (Criteria) this;
        }

        public Criteria andRotationTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("ROTATION_TYPE_NAME >=", value, "rotationTypeName");
            return (Criteria) this;
        }

        public Criteria andRotationTypeNameLessThan(String value) {
            addCriterion("ROTATION_TYPE_NAME <", value, "rotationTypeName");
            return (Criteria) this;
        }

        public Criteria andRotationTypeNameLessThanOrEqualTo(String value) {
            addCriterion("ROTATION_TYPE_NAME <=", value, "rotationTypeName");
            return (Criteria) this;
        }

        public Criteria andRotationTypeNameLike(String value) {
            addCriterion("ROTATION_TYPE_NAME like", value, "rotationTypeName");
            return (Criteria) this;
        }

        public Criteria andRotationTypeNameNotLike(String value) {
            addCriterion("ROTATION_TYPE_NAME not like", value, "rotationTypeName");
            return (Criteria) this;
        }

        public Criteria andRotationTypeNameIn(List<String> values) {
            addCriterion("ROTATION_TYPE_NAME in", values, "rotationTypeName");
            return (Criteria) this;
        }

        public Criteria andRotationTypeNameNotIn(List<String> values) {
            addCriterion("ROTATION_TYPE_NAME not in", values, "rotationTypeName");
            return (Criteria) this;
        }

        public Criteria andRotationTypeNameBetween(String value1, String value2) {
            addCriterion("ROTATION_TYPE_NAME between", value1, value2, "rotationTypeName");
            return (Criteria) this;
        }

        public Criteria andRotationTypeNameNotBetween(String value1, String value2) {
            addCriterion("ROTATION_TYPE_NAME not between", value1, value2, "rotationTypeName");
            return (Criteria) this;
        }

        public Criteria andRotationVersionIsNull() {
            addCriterion("ROTATION_VERSION is null");
            return (Criteria) this;
        }

        public Criteria andRotationVersionIsNotNull() {
            addCriterion("ROTATION_VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andRotationVersionEqualTo(String value) {
            addCriterion("ROTATION_VERSION =", value, "rotationVersion");
            return (Criteria) this;
        }

        public Criteria andRotationVersionNotEqualTo(String value) {
            addCriterion("ROTATION_VERSION <>", value, "rotationVersion");
            return (Criteria) this;
        }

        public Criteria andRotationVersionGreaterThan(String value) {
            addCriterion("ROTATION_VERSION >", value, "rotationVersion");
            return (Criteria) this;
        }

        public Criteria andRotationVersionGreaterThanOrEqualTo(String value) {
            addCriterion("ROTATION_VERSION >=", value, "rotationVersion");
            return (Criteria) this;
        }

        public Criteria andRotationVersionLessThan(String value) {
            addCriterion("ROTATION_VERSION <", value, "rotationVersion");
            return (Criteria) this;
        }

        public Criteria andRotationVersionLessThanOrEqualTo(String value) {
            addCriterion("ROTATION_VERSION <=", value, "rotationVersion");
            return (Criteria) this;
        }

        public Criteria andRotationVersionLike(String value) {
            addCriterion("ROTATION_VERSION like", value, "rotationVersion");
            return (Criteria) this;
        }

        public Criteria andRotationVersionNotLike(String value) {
            addCriterion("ROTATION_VERSION not like", value, "rotationVersion");
            return (Criteria) this;
        }

        public Criteria andRotationVersionIn(List<String> values) {
            addCriterion("ROTATION_VERSION in", values, "rotationVersion");
            return (Criteria) this;
        }

        public Criteria andRotationVersionNotIn(List<String> values) {
            addCriterion("ROTATION_VERSION not in", values, "rotationVersion");
            return (Criteria) this;
        }

        public Criteria andRotationVersionBetween(String value1, String value2) {
            addCriterion("ROTATION_VERSION between", value1, value2, "rotationVersion");
            return (Criteria) this;
        }

        public Criteria andRotationVersionNotBetween(String value1, String value2) {
            addCriterion("ROTATION_VERSION not between", value1, value2, "rotationVersion");
            return (Criteria) this;
        }

        public Criteria andIsOrgViewIsNull() {
            addCriterion("IS_ORG_VIEW is null");
            return (Criteria) this;
        }

        public Criteria andIsOrgViewIsNotNull() {
            addCriterion("IS_ORG_VIEW is not null");
            return (Criteria) this;
        }

        public Criteria andIsOrgViewEqualTo(String value) {
            addCriterion("IS_ORG_VIEW =", value, "isOrgView");
            return (Criteria) this;
        }

        public Criteria andIsOrgViewNotEqualTo(String value) {
            addCriterion("IS_ORG_VIEW <>", value, "isOrgView");
            return (Criteria) this;
        }

        public Criteria andIsOrgViewGreaterThan(String value) {
            addCriterion("IS_ORG_VIEW >", value, "isOrgView");
            return (Criteria) this;
        }

        public Criteria andIsOrgViewGreaterThanOrEqualTo(String value) {
            addCriterion("IS_ORG_VIEW >=", value, "isOrgView");
            return (Criteria) this;
        }

        public Criteria andIsOrgViewLessThan(String value) {
            addCriterion("IS_ORG_VIEW <", value, "isOrgView");
            return (Criteria) this;
        }

        public Criteria andIsOrgViewLessThanOrEqualTo(String value) {
            addCriterion("IS_ORG_VIEW <=", value, "isOrgView");
            return (Criteria) this;
        }

        public Criteria andIsOrgViewLike(String value) {
            addCriterion("IS_ORG_VIEW like", value, "isOrgView");
            return (Criteria) this;
        }

        public Criteria andIsOrgViewNotLike(String value) {
            addCriterion("IS_ORG_VIEW not like", value, "isOrgView");
            return (Criteria) this;
        }

        public Criteria andIsOrgViewIn(List<String> values) {
            addCriterion("IS_ORG_VIEW in", values, "isOrgView");
            return (Criteria) this;
        }

        public Criteria andIsOrgViewNotIn(List<String> values) {
            addCriterion("IS_ORG_VIEW not in", values, "isOrgView");
            return (Criteria) this;
        }

        public Criteria andIsOrgViewBetween(String value1, String value2) {
            addCriterion("IS_ORG_VIEW between", value1, value2, "isOrgView");
            return (Criteria) this;
        }

        public Criteria andIsOrgViewNotBetween(String value1, String value2) {
            addCriterion("IS_ORG_VIEW not between", value1, value2, "isOrgView");
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

        public Criteria andIsDoubleIsNull() {
            addCriterion("IS_DOUBLE is null");
            return (Criteria) this;
        }

        public Criteria andIsDoubleIsNotNull() {
            addCriterion("IS_DOUBLE is not null");
            return (Criteria) this;
        }

        public Criteria andIsDoubleEqualTo(String value) {
            addCriterion("IS_DOUBLE =", value, "isDouble");
            return (Criteria) this;
        }

        public Criteria andIsDoubleNotEqualTo(String value) {
            addCriterion("IS_DOUBLE <>", value, "isDouble");
            return (Criteria) this;
        }

        public Criteria andIsDoubleGreaterThan(String value) {
            addCriterion("IS_DOUBLE >", value, "isDouble");
            return (Criteria) this;
        }

        public Criteria andIsDoubleGreaterThanOrEqualTo(String value) {
            addCriterion("IS_DOUBLE >=", value, "isDouble");
            return (Criteria) this;
        }

        public Criteria andIsDoubleLessThan(String value) {
            addCriterion("IS_DOUBLE <", value, "isDouble");
            return (Criteria) this;
        }

        public Criteria andIsDoubleLessThanOrEqualTo(String value) {
            addCriterion("IS_DOUBLE <=", value, "isDouble");
            return (Criteria) this;
        }

        public Criteria andIsDoubleLike(String value) {
            addCriterion("IS_DOUBLE like", value, "isDouble");
            return (Criteria) this;
        }

        public Criteria andIsDoubleNotLike(String value) {
            addCriterion("IS_DOUBLE not like", value, "isDouble");
            return (Criteria) this;
        }

        public Criteria andIsDoubleIn(List<String> values) {
            addCriterion("IS_DOUBLE in", values, "isDouble");
            return (Criteria) this;
        }

        public Criteria andIsDoubleNotIn(List<String> values) {
            addCriterion("IS_DOUBLE not in", values, "isDouble");
            return (Criteria) this;
        }

        public Criteria andIsDoubleBetween(String value1, String value2) {
            addCriterion("IS_DOUBLE between", value1, value2, "isDouble");
            return (Criteria) this;
        }

        public Criteria andIsDoubleNotBetween(String value1, String value2) {
            addCriterion("IS_DOUBLE not between", value1, value2, "isDouble");
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