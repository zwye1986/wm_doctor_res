package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class NyzlVacancySwapExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NyzlVacancySwapExample() {
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

        public Criteria andRecruitYearIsNull() {
            addCriterion("RECRUIT_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andRecruitYearIsNotNull() {
            addCriterion("RECRUIT_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitYearEqualTo(String value) {
            addCriterion("RECRUIT_YEAR =", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearNotEqualTo(String value) {
            addCriterion("RECRUIT_YEAR <>", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearGreaterThan(String value) {
            addCriterion("RECRUIT_YEAR >", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearGreaterThanOrEqualTo(String value) {
            addCriterion("RECRUIT_YEAR >=", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearLessThan(String value) {
            addCriterion("RECRUIT_YEAR <", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearLessThanOrEqualTo(String value) {
            addCriterion("RECRUIT_YEAR <=", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearLike(String value) {
            addCriterion("RECRUIT_YEAR like", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearNotLike(String value) {
            addCriterion("RECRUIT_YEAR not like", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearIn(List<String> values) {
            addCriterion("RECRUIT_YEAR in", values, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearNotIn(List<String> values) {
            addCriterion("RECRUIT_YEAR not in", values, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearBetween(String value1, String value2) {
            addCriterion("RECRUIT_YEAR between", value1, value2, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearNotBetween(String value1, String value2) {
            addCriterion("RECRUIT_YEAR not between", value1, value2, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andStuSignIsNull() {
            addCriterion("STU_SIGN is null");
            return (Criteria) this;
        }

        public Criteria andStuSignIsNotNull() {
            addCriterion("STU_SIGN is not null");
            return (Criteria) this;
        }

        public Criteria andStuSignEqualTo(String value) {
            addCriterion("STU_SIGN =", value, "stuSign");
            return (Criteria) this;
        }

        public Criteria andStuSignNotEqualTo(String value) {
            addCriterion("STU_SIGN <>", value, "stuSign");
            return (Criteria) this;
        }

        public Criteria andStuSignGreaterThan(String value) {
            addCriterion("STU_SIGN >", value, "stuSign");
            return (Criteria) this;
        }

        public Criteria andStuSignGreaterThanOrEqualTo(String value) {
            addCriterion("STU_SIGN >=", value, "stuSign");
            return (Criteria) this;
        }

        public Criteria andStuSignLessThan(String value) {
            addCriterion("STU_SIGN <", value, "stuSign");
            return (Criteria) this;
        }

        public Criteria andStuSignLessThanOrEqualTo(String value) {
            addCriterion("STU_SIGN <=", value, "stuSign");
            return (Criteria) this;
        }

        public Criteria andStuSignLike(String value) {
            addCriterion("STU_SIGN like", value, "stuSign");
            return (Criteria) this;
        }

        public Criteria andStuSignNotLike(String value) {
            addCriterion("STU_SIGN not like", value, "stuSign");
            return (Criteria) this;
        }

        public Criteria andStuSignIn(List<String> values) {
            addCriterion("STU_SIGN in", values, "stuSign");
            return (Criteria) this;
        }

        public Criteria andStuSignNotIn(List<String> values) {
            addCriterion("STU_SIGN not in", values, "stuSign");
            return (Criteria) this;
        }

        public Criteria andStuSignBetween(String value1, String value2) {
            addCriterion("STU_SIGN between", value1, value2, "stuSign");
            return (Criteria) this;
        }

        public Criteria andStuSignNotBetween(String value1, String value2) {
            addCriterion("STU_SIGN not between", value1, value2, "stuSign");
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

        public Criteria andDegreeTypeIdIsNull() {
            addCriterion("DEGREE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdIsNotNull() {
            addCriterion("DEGREE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdEqualTo(String value) {
            addCriterion("DEGREE_TYPE_ID =", value, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdNotEqualTo(String value) {
            addCriterion("DEGREE_TYPE_ID <>", value, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdGreaterThan(String value) {
            addCriterion("DEGREE_TYPE_ID >", value, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("DEGREE_TYPE_ID >=", value, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdLessThan(String value) {
            addCriterion("DEGREE_TYPE_ID <", value, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdLessThanOrEqualTo(String value) {
            addCriterion("DEGREE_TYPE_ID <=", value, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdLike(String value) {
            addCriterion("DEGREE_TYPE_ID like", value, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdNotLike(String value) {
            addCriterion("DEGREE_TYPE_ID not like", value, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdIn(List<String> values) {
            addCriterion("DEGREE_TYPE_ID in", values, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdNotIn(List<String> values) {
            addCriterion("DEGREE_TYPE_ID not in", values, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdBetween(String value1, String value2) {
            addCriterion("DEGREE_TYPE_ID between", value1, value2, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIdNotBetween(String value1, String value2) {
            addCriterion("DEGREE_TYPE_ID not between", value1, value2, "degreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameIsNull() {
            addCriterion("DEGREE_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameIsNotNull() {
            addCriterion("DEGREE_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameEqualTo(String value) {
            addCriterion("DEGREE_TYPE_NAME =", value, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameNotEqualTo(String value) {
            addCriterion("DEGREE_TYPE_NAME <>", value, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameGreaterThan(String value) {
            addCriterion("DEGREE_TYPE_NAME >", value, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEGREE_TYPE_NAME >=", value, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameLessThan(String value) {
            addCriterion("DEGREE_TYPE_NAME <", value, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameLessThanOrEqualTo(String value) {
            addCriterion("DEGREE_TYPE_NAME <=", value, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameLike(String value) {
            addCriterion("DEGREE_TYPE_NAME like", value, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameNotLike(String value) {
            addCriterion("DEGREE_TYPE_NAME not like", value, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameIn(List<String> values) {
            addCriterion("DEGREE_TYPE_NAME in", values, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameNotIn(List<String> values) {
            addCriterion("DEGREE_TYPE_NAME not in", values, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameBetween(String value1, String value2) {
            addCriterion("DEGREE_TYPE_NAME between", value1, value2, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNameNotBetween(String value1, String value2) {
            addCriterion("DEGREE_TYPE_NAME not between", value1, value2, "degreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDirectionIdIsNull() {
            addCriterion("DIRECTION_ID is null");
            return (Criteria) this;
        }

        public Criteria andDirectionIdIsNotNull() {
            addCriterion("DIRECTION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDirectionIdEqualTo(String value) {
            addCriterion("DIRECTION_ID =", value, "directionId");
            return (Criteria) this;
        }

        public Criteria andDirectionIdNotEqualTo(String value) {
            addCriterion("DIRECTION_ID <>", value, "directionId");
            return (Criteria) this;
        }

        public Criteria andDirectionIdGreaterThan(String value) {
            addCriterion("DIRECTION_ID >", value, "directionId");
            return (Criteria) this;
        }

        public Criteria andDirectionIdGreaterThanOrEqualTo(String value) {
            addCriterion("DIRECTION_ID >=", value, "directionId");
            return (Criteria) this;
        }

        public Criteria andDirectionIdLessThan(String value) {
            addCriterion("DIRECTION_ID <", value, "directionId");
            return (Criteria) this;
        }

        public Criteria andDirectionIdLessThanOrEqualTo(String value) {
            addCriterion("DIRECTION_ID <=", value, "directionId");
            return (Criteria) this;
        }

        public Criteria andDirectionIdLike(String value) {
            addCriterion("DIRECTION_ID like", value, "directionId");
            return (Criteria) this;
        }

        public Criteria andDirectionIdNotLike(String value) {
            addCriterion("DIRECTION_ID not like", value, "directionId");
            return (Criteria) this;
        }

        public Criteria andDirectionIdIn(List<String> values) {
            addCriterion("DIRECTION_ID in", values, "directionId");
            return (Criteria) this;
        }

        public Criteria andDirectionIdNotIn(List<String> values) {
            addCriterion("DIRECTION_ID not in", values, "directionId");
            return (Criteria) this;
        }

        public Criteria andDirectionIdBetween(String value1, String value2) {
            addCriterion("DIRECTION_ID between", value1, value2, "directionId");
            return (Criteria) this;
        }

        public Criteria andDirectionIdNotBetween(String value1, String value2) {
            addCriterion("DIRECTION_ID not between", value1, value2, "directionId");
            return (Criteria) this;
        }

        public Criteria andDirectionNameIsNull() {
            addCriterion("DIRECTION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDirectionNameIsNotNull() {
            addCriterion("DIRECTION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDirectionNameEqualTo(String value) {
            addCriterion("DIRECTION_NAME =", value, "directionName");
            return (Criteria) this;
        }

        public Criteria andDirectionNameNotEqualTo(String value) {
            addCriterion("DIRECTION_NAME <>", value, "directionName");
            return (Criteria) this;
        }

        public Criteria andDirectionNameGreaterThan(String value) {
            addCriterion("DIRECTION_NAME >", value, "directionName");
            return (Criteria) this;
        }

        public Criteria andDirectionNameGreaterThanOrEqualTo(String value) {
            addCriterion("DIRECTION_NAME >=", value, "directionName");
            return (Criteria) this;
        }

        public Criteria andDirectionNameLessThan(String value) {
            addCriterion("DIRECTION_NAME <", value, "directionName");
            return (Criteria) this;
        }

        public Criteria andDirectionNameLessThanOrEqualTo(String value) {
            addCriterion("DIRECTION_NAME <=", value, "directionName");
            return (Criteria) this;
        }

        public Criteria andDirectionNameLike(String value) {
            addCriterion("DIRECTION_NAME like", value, "directionName");
            return (Criteria) this;
        }

        public Criteria andDirectionNameNotLike(String value) {
            addCriterion("DIRECTION_NAME not like", value, "directionName");
            return (Criteria) this;
        }

        public Criteria andDirectionNameIn(List<String> values) {
            addCriterion("DIRECTION_NAME in", values, "directionName");
            return (Criteria) this;
        }

        public Criteria andDirectionNameNotIn(List<String> values) {
            addCriterion("DIRECTION_NAME not in", values, "directionName");
            return (Criteria) this;
        }

        public Criteria andDirectionNameBetween(String value1, String value2) {
            addCriterion("DIRECTION_NAME between", value1, value2, "directionName");
            return (Criteria) this;
        }

        public Criteria andDirectionNameNotBetween(String value1, String value2) {
            addCriterion("DIRECTION_NAME not between", value1, value2, "directionName");
            return (Criteria) this;
        }

        public Criteria andFullNumIsNull() {
            addCriterion("FULL_NUM is null");
            return (Criteria) this;
        }

        public Criteria andFullNumIsNotNull() {
            addCriterion("FULL_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andFullNumEqualTo(String value) {
            addCriterion("FULL_NUM =", value, "fullNum");
            return (Criteria) this;
        }

        public Criteria andFullNumNotEqualTo(String value) {
            addCriterion("FULL_NUM <>", value, "fullNum");
            return (Criteria) this;
        }

        public Criteria andFullNumGreaterThan(String value) {
            addCriterion("FULL_NUM >", value, "fullNum");
            return (Criteria) this;
        }

        public Criteria andFullNumGreaterThanOrEqualTo(String value) {
            addCriterion("FULL_NUM >=", value, "fullNum");
            return (Criteria) this;
        }

        public Criteria andFullNumLessThan(String value) {
            addCriterion("FULL_NUM <", value, "fullNum");
            return (Criteria) this;
        }

        public Criteria andFullNumLessThanOrEqualTo(String value) {
            addCriterion("FULL_NUM <=", value, "fullNum");
            return (Criteria) this;
        }

        public Criteria andFullNumLike(String value) {
            addCriterion("FULL_NUM like", value, "fullNum");
            return (Criteria) this;
        }

        public Criteria andFullNumNotLike(String value) {
            addCriterion("FULL_NUM not like", value, "fullNum");
            return (Criteria) this;
        }

        public Criteria andFullNumIn(List<String> values) {
            addCriterion("FULL_NUM in", values, "fullNum");
            return (Criteria) this;
        }

        public Criteria andFullNumNotIn(List<String> values) {
            addCriterion("FULL_NUM not in", values, "fullNum");
            return (Criteria) this;
        }

        public Criteria andFullNumBetween(String value1, String value2) {
            addCriterion("FULL_NUM between", value1, value2, "fullNum");
            return (Criteria) this;
        }

        public Criteria andFullNumNotBetween(String value1, String value2) {
            addCriterion("FULL_NUM not between", value1, value2, "fullNum");
            return (Criteria) this;
        }

        public Criteria andVacancyNumIsNull() {
            addCriterion("VACANCY_NUM is null");
            return (Criteria) this;
        }

        public Criteria andVacancyNumIsNotNull() {
            addCriterion("VACANCY_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andVacancyNumEqualTo(String value) {
            addCriterion("VACANCY_NUM =", value, "vacancyNum");
            return (Criteria) this;
        }

        public Criteria andVacancyNumNotEqualTo(String value) {
            addCriterion("VACANCY_NUM <>", value, "vacancyNum");
            return (Criteria) this;
        }

        public Criteria andVacancyNumGreaterThan(String value) {
            addCriterion("VACANCY_NUM >", value, "vacancyNum");
            return (Criteria) this;
        }

        public Criteria andVacancyNumGreaterThanOrEqualTo(String value) {
            addCriterion("VACANCY_NUM >=", value, "vacancyNum");
            return (Criteria) this;
        }

        public Criteria andVacancyNumLessThan(String value) {
            addCriterion("VACANCY_NUM <", value, "vacancyNum");
            return (Criteria) this;
        }

        public Criteria andVacancyNumLessThanOrEqualTo(String value) {
            addCriterion("VACANCY_NUM <=", value, "vacancyNum");
            return (Criteria) this;
        }

        public Criteria andVacancyNumLike(String value) {
            addCriterion("VACANCY_NUM like", value, "vacancyNum");
            return (Criteria) this;
        }

        public Criteria andVacancyNumNotLike(String value) {
            addCriterion("VACANCY_NUM not like", value, "vacancyNum");
            return (Criteria) this;
        }

        public Criteria andVacancyNumIn(List<String> values) {
            addCriterion("VACANCY_NUM in", values, "vacancyNum");
            return (Criteria) this;
        }

        public Criteria andVacancyNumNotIn(List<String> values) {
            addCriterion("VACANCY_NUM not in", values, "vacancyNum");
            return (Criteria) this;
        }

        public Criteria andVacancyNumBetween(String value1, String value2) {
            addCriterion("VACANCY_NUM between", value1, value2, "vacancyNum");
            return (Criteria) this;
        }

        public Criteria andVacancyNumNotBetween(String value1, String value2) {
            addCriterion("VACANCY_NUM not between", value1, value2, "vacancyNum");
            return (Criteria) this;
        }

        public Criteria andBasciConditionsIsNull() {
            addCriterion("BASCI_CONDITIONS is null");
            return (Criteria) this;
        }

        public Criteria andBasciConditionsIsNotNull() {
            addCriterion("BASCI_CONDITIONS is not null");
            return (Criteria) this;
        }

        public Criteria andBasciConditionsEqualTo(String value) {
            addCriterion("BASCI_CONDITIONS =", value, "basciConditions");
            return (Criteria) this;
        }

        public Criteria andBasciConditionsNotEqualTo(String value) {
            addCriterion("BASCI_CONDITIONS <>", value, "basciConditions");
            return (Criteria) this;
        }

        public Criteria andBasciConditionsGreaterThan(String value) {
            addCriterion("BASCI_CONDITIONS >", value, "basciConditions");
            return (Criteria) this;
        }

        public Criteria andBasciConditionsGreaterThanOrEqualTo(String value) {
            addCriterion("BASCI_CONDITIONS >=", value, "basciConditions");
            return (Criteria) this;
        }

        public Criteria andBasciConditionsLessThan(String value) {
            addCriterion("BASCI_CONDITIONS <", value, "basciConditions");
            return (Criteria) this;
        }

        public Criteria andBasciConditionsLessThanOrEqualTo(String value) {
            addCriterion("BASCI_CONDITIONS <=", value, "basciConditions");
            return (Criteria) this;
        }

        public Criteria andBasciConditionsLike(String value) {
            addCriterion("BASCI_CONDITIONS like", value, "basciConditions");
            return (Criteria) this;
        }

        public Criteria andBasciConditionsNotLike(String value) {
            addCriterion("BASCI_CONDITIONS not like", value, "basciConditions");
            return (Criteria) this;
        }

        public Criteria andBasciConditionsIn(List<String> values) {
            addCriterion("BASCI_CONDITIONS in", values, "basciConditions");
            return (Criteria) this;
        }

        public Criteria andBasciConditionsNotIn(List<String> values) {
            addCriterion("BASCI_CONDITIONS not in", values, "basciConditions");
            return (Criteria) this;
        }

        public Criteria andBasciConditionsBetween(String value1, String value2) {
            addCriterion("BASCI_CONDITIONS between", value1, value2, "basciConditions");
            return (Criteria) this;
        }

        public Criteria andBasciConditionsNotBetween(String value1, String value2) {
            addCriterion("BASCI_CONDITIONS not between", value1, value2, "basciConditions");
            return (Criteria) this;
        }

        public Criteria andOtherConditionsIsNull() {
            addCriterion("OTHER_CONDITIONS is null");
            return (Criteria) this;
        }

        public Criteria andOtherConditionsIsNotNull() {
            addCriterion("OTHER_CONDITIONS is not null");
            return (Criteria) this;
        }

        public Criteria andOtherConditionsEqualTo(String value) {
            addCriterion("OTHER_CONDITIONS =", value, "otherConditions");
            return (Criteria) this;
        }

        public Criteria andOtherConditionsNotEqualTo(String value) {
            addCriterion("OTHER_CONDITIONS <>", value, "otherConditions");
            return (Criteria) this;
        }

        public Criteria andOtherConditionsGreaterThan(String value) {
            addCriterion("OTHER_CONDITIONS >", value, "otherConditions");
            return (Criteria) this;
        }

        public Criteria andOtherConditionsGreaterThanOrEqualTo(String value) {
            addCriterion("OTHER_CONDITIONS >=", value, "otherConditions");
            return (Criteria) this;
        }

        public Criteria andOtherConditionsLessThan(String value) {
            addCriterion("OTHER_CONDITIONS <", value, "otherConditions");
            return (Criteria) this;
        }

        public Criteria andOtherConditionsLessThanOrEqualTo(String value) {
            addCriterion("OTHER_CONDITIONS <=", value, "otherConditions");
            return (Criteria) this;
        }

        public Criteria andOtherConditionsLike(String value) {
            addCriterion("OTHER_CONDITIONS like", value, "otherConditions");
            return (Criteria) this;
        }

        public Criteria andOtherConditionsNotLike(String value) {
            addCriterion("OTHER_CONDITIONS not like", value, "otherConditions");
            return (Criteria) this;
        }

        public Criteria andOtherConditionsIn(List<String> values) {
            addCriterion("OTHER_CONDITIONS in", values, "otherConditions");
            return (Criteria) this;
        }

        public Criteria andOtherConditionsNotIn(List<String> values) {
            addCriterion("OTHER_CONDITIONS not in", values, "otherConditions");
            return (Criteria) this;
        }

        public Criteria andOtherConditionsBetween(String value1, String value2) {
            addCriterion("OTHER_CONDITIONS between", value1, value2, "otherConditions");
            return (Criteria) this;
        }

        public Criteria andOtherConditionsNotBetween(String value1, String value2) {
            addCriterion("OTHER_CONDITIONS not between", value1, value2, "otherConditions");
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