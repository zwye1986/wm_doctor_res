package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class TeacherTargetApplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TeacherTargetApplyExample() {
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

        public Criteria andApplyFlowIsNull() {
            addCriterion("APPLY_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApplyFlowIsNotNull() {
            addCriterion("APPLY_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApplyFlowEqualTo(String value) {
            addCriterion("APPLY_FLOW =", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowNotEqualTo(String value) {
            addCriterion("APPLY_FLOW <>", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowGreaterThan(String value) {
            addCriterion("APPLY_FLOW >", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_FLOW >=", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowLessThan(String value) {
            addCriterion("APPLY_FLOW <", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowLessThanOrEqualTo(String value) {
            addCriterion("APPLY_FLOW <=", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowLike(String value) {
            addCriterion("APPLY_FLOW like", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowNotLike(String value) {
            addCriterion("APPLY_FLOW not like", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowIn(List<String> values) {
            addCriterion("APPLY_FLOW in", values, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowNotIn(List<String> values) {
            addCriterion("APPLY_FLOW not in", values, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowBetween(String value1, String value2) {
            addCriterion("APPLY_FLOW between", value1, value2, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowNotBetween(String value1, String value2) {
            addCriterion("APPLY_FLOW not between", value1, value2, "applyFlow");
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

        public Criteria andIsAcademicIsNull() {
            addCriterion("IS_ACADEMIC is null");
            return (Criteria) this;
        }

        public Criteria andIsAcademicIsNotNull() {
            addCriterion("IS_ACADEMIC is not null");
            return (Criteria) this;
        }

        public Criteria andIsAcademicEqualTo(String value) {
            addCriterion("IS_ACADEMIC =", value, "isAcademic");
            return (Criteria) this;
        }

        public Criteria andIsAcademicNotEqualTo(String value) {
            addCriterion("IS_ACADEMIC <>", value, "isAcademic");
            return (Criteria) this;
        }

        public Criteria andIsAcademicGreaterThan(String value) {
            addCriterion("IS_ACADEMIC >", value, "isAcademic");
            return (Criteria) this;
        }

        public Criteria andIsAcademicGreaterThanOrEqualTo(String value) {
            addCriterion("IS_ACADEMIC >=", value, "isAcademic");
            return (Criteria) this;
        }

        public Criteria andIsAcademicLessThan(String value) {
            addCriterion("IS_ACADEMIC <", value, "isAcademic");
            return (Criteria) this;
        }

        public Criteria andIsAcademicLessThanOrEqualTo(String value) {
            addCriterion("IS_ACADEMIC <=", value, "isAcademic");
            return (Criteria) this;
        }

        public Criteria andIsAcademicLike(String value) {
            addCriterion("IS_ACADEMIC like", value, "isAcademic");
            return (Criteria) this;
        }

        public Criteria andIsAcademicNotLike(String value) {
            addCriterion("IS_ACADEMIC not like", value, "isAcademic");
            return (Criteria) this;
        }

        public Criteria andIsAcademicIn(List<String> values) {
            addCriterion("IS_ACADEMIC in", values, "isAcademic");
            return (Criteria) this;
        }

        public Criteria andIsAcademicNotIn(List<String> values) {
            addCriterion("IS_ACADEMIC not in", values, "isAcademic");
            return (Criteria) this;
        }

        public Criteria andIsAcademicBetween(String value1, String value2) {
            addCriterion("IS_ACADEMIC between", value1, value2, "isAcademic");
            return (Criteria) this;
        }

        public Criteria andIsAcademicNotBetween(String value1, String value2) {
            addCriterion("IS_ACADEMIC not between", value1, value2, "isAcademic");
            return (Criteria) this;
        }

        public Criteria andIsSpecializedIsNull() {
            addCriterion("IS_SPECIALIZED is null");
            return (Criteria) this;
        }

        public Criteria andIsSpecializedIsNotNull() {
            addCriterion("IS_SPECIALIZED is not null");
            return (Criteria) this;
        }

        public Criteria andIsSpecializedEqualTo(String value) {
            addCriterion("IS_SPECIALIZED =", value, "isSpecialized");
            return (Criteria) this;
        }

        public Criteria andIsSpecializedNotEqualTo(String value) {
            addCriterion("IS_SPECIALIZED <>", value, "isSpecialized");
            return (Criteria) this;
        }

        public Criteria andIsSpecializedGreaterThan(String value) {
            addCriterion("IS_SPECIALIZED >", value, "isSpecialized");
            return (Criteria) this;
        }

        public Criteria andIsSpecializedGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SPECIALIZED >=", value, "isSpecialized");
            return (Criteria) this;
        }

        public Criteria andIsSpecializedLessThan(String value) {
            addCriterion("IS_SPECIALIZED <", value, "isSpecialized");
            return (Criteria) this;
        }

        public Criteria andIsSpecializedLessThanOrEqualTo(String value) {
            addCriterion("IS_SPECIALIZED <=", value, "isSpecialized");
            return (Criteria) this;
        }

        public Criteria andIsSpecializedLike(String value) {
            addCriterion("IS_SPECIALIZED like", value, "isSpecialized");
            return (Criteria) this;
        }

        public Criteria andIsSpecializedNotLike(String value) {
            addCriterion("IS_SPECIALIZED not like", value, "isSpecialized");
            return (Criteria) this;
        }

        public Criteria andIsSpecializedIn(List<String> values) {
            addCriterion("IS_SPECIALIZED in", values, "isSpecialized");
            return (Criteria) this;
        }

        public Criteria andIsSpecializedNotIn(List<String> values) {
            addCriterion("IS_SPECIALIZED not in", values, "isSpecialized");
            return (Criteria) this;
        }

        public Criteria andIsSpecializedBetween(String value1, String value2) {
            addCriterion("IS_SPECIALIZED between", value1, value2, "isSpecialized");
            return (Criteria) this;
        }

        public Criteria andIsSpecializedNotBetween(String value1, String value2) {
            addCriterion("IS_SPECIALIZED not between", value1, value2, "isSpecialized");
            return (Criteria) this;
        }

        public Criteria andAcademicNumIsNull() {
            addCriterion("ACADEMIC_NUM is null");
            return (Criteria) this;
        }

        public Criteria andAcademicNumIsNotNull() {
            addCriterion("ACADEMIC_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andAcademicNumEqualTo(String value) {
            addCriterion("ACADEMIC_NUM =", value, "academicNum");
            return (Criteria) this;
        }

        public Criteria andAcademicNumNotEqualTo(String value) {
            addCriterion("ACADEMIC_NUM <>", value, "academicNum");
            return (Criteria) this;
        }

        public Criteria andAcademicNumGreaterThan(String value) {
            addCriterion("ACADEMIC_NUM >", value, "academicNum");
            return (Criteria) this;
        }

        public Criteria andAcademicNumGreaterThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_NUM >=", value, "academicNum");
            return (Criteria) this;
        }

        public Criteria andAcademicNumLessThan(String value) {
            addCriterion("ACADEMIC_NUM <", value, "academicNum");
            return (Criteria) this;
        }

        public Criteria andAcademicNumLessThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_NUM <=", value, "academicNum");
            return (Criteria) this;
        }

        public Criteria andAcademicNumLike(String value) {
            addCriterion("ACADEMIC_NUM like", value, "academicNum");
            return (Criteria) this;
        }

        public Criteria andAcademicNumNotLike(String value) {
            addCriterion("ACADEMIC_NUM not like", value, "academicNum");
            return (Criteria) this;
        }

        public Criteria andAcademicNumIn(List<String> values) {
            addCriterion("ACADEMIC_NUM in", values, "academicNum");
            return (Criteria) this;
        }

        public Criteria andAcademicNumNotIn(List<String> values) {
            addCriterion("ACADEMIC_NUM not in", values, "academicNum");
            return (Criteria) this;
        }

        public Criteria andAcademicNumBetween(String value1, String value2) {
            addCriterion("ACADEMIC_NUM between", value1, value2, "academicNum");
            return (Criteria) this;
        }

        public Criteria andAcademicNumNotBetween(String value1, String value2) {
            addCriterion("ACADEMIC_NUM not between", value1, value2, "academicNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumIsNull() {
            addCriterion("SPECIALIZED_NUM is null");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumIsNotNull() {
            addCriterion("SPECIALIZED_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumEqualTo(String value) {
            addCriterion("SPECIALIZED_NUM =", value, "specializedNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumNotEqualTo(String value) {
            addCriterion("SPECIALIZED_NUM <>", value, "specializedNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumGreaterThan(String value) {
            addCriterion("SPECIALIZED_NUM >", value, "specializedNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumGreaterThanOrEqualTo(String value) {
            addCriterion("SPECIALIZED_NUM >=", value, "specializedNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumLessThan(String value) {
            addCriterion("SPECIALIZED_NUM <", value, "specializedNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumLessThanOrEqualTo(String value) {
            addCriterion("SPECIALIZED_NUM <=", value, "specializedNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumLike(String value) {
            addCriterion("SPECIALIZED_NUM like", value, "specializedNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumNotLike(String value) {
            addCriterion("SPECIALIZED_NUM not like", value, "specializedNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumIn(List<String> values) {
            addCriterion("SPECIALIZED_NUM in", values, "specializedNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumNotIn(List<String> values) {
            addCriterion("SPECIALIZED_NUM not in", values, "specializedNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumBetween(String value1, String value2) {
            addCriterion("SPECIALIZED_NUM between", value1, value2, "specializedNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumNotBetween(String value1, String value2) {
            addCriterion("SPECIALIZED_NUM not between", value1, value2, "specializedNum");
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

        public Criteria andIsSubmitIsNull() {
            addCriterion("IS_SUBMIT is null");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIsNotNull() {
            addCriterion("IS_SUBMIT is not null");
            return (Criteria) this;
        }

        public Criteria andIsSubmitEqualTo(String value) {
            addCriterion("IS_SUBMIT =", value, "isSubmit");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNotEqualTo(String value) {
            addCriterion("IS_SUBMIT <>", value, "isSubmit");
            return (Criteria) this;
        }

        public Criteria andIsSubmitGreaterThan(String value) {
            addCriterion("IS_SUBMIT >", value, "isSubmit");
            return (Criteria) this;
        }

        public Criteria andIsSubmitGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SUBMIT >=", value, "isSubmit");
            return (Criteria) this;
        }

        public Criteria andIsSubmitLessThan(String value) {
            addCriterion("IS_SUBMIT <", value, "isSubmit");
            return (Criteria) this;
        }

        public Criteria andIsSubmitLessThanOrEqualTo(String value) {
            addCriterion("IS_SUBMIT <=", value, "isSubmit");
            return (Criteria) this;
        }

        public Criteria andIsSubmitLike(String value) {
            addCriterion("IS_SUBMIT like", value, "isSubmit");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNotLike(String value) {
            addCriterion("IS_SUBMIT not like", value, "isSubmit");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIn(List<String> values) {
            addCriterion("IS_SUBMIT in", values, "isSubmit");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNotIn(List<String> values) {
            addCriterion("IS_SUBMIT not in", values, "isSubmit");
            return (Criteria) this;
        }

        public Criteria andIsSubmitBetween(String value1, String value2) {
            addCriterion("IS_SUBMIT between", value1, value2, "isSubmit");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNotBetween(String value1, String value2) {
            addCriterion("IS_SUBMIT not between", value1, value2, "isSubmit");
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

        public Criteria andResearchDirectionIdIsNull() {
            addCriterion("RESEARCH_DIRECTION_ID is null");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionIdIsNotNull() {
            addCriterion("RESEARCH_DIRECTION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionIdEqualTo(String value) {
            addCriterion("RESEARCH_DIRECTION_ID =", value, "researchDirectionId");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionIdNotEqualTo(String value) {
            addCriterion("RESEARCH_DIRECTION_ID <>", value, "researchDirectionId");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionIdGreaterThan(String value) {
            addCriterion("RESEARCH_DIRECTION_ID >", value, "researchDirectionId");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionIdGreaterThanOrEqualTo(String value) {
            addCriterion("RESEARCH_DIRECTION_ID >=", value, "researchDirectionId");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionIdLessThan(String value) {
            addCriterion("RESEARCH_DIRECTION_ID <", value, "researchDirectionId");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionIdLessThanOrEqualTo(String value) {
            addCriterion("RESEARCH_DIRECTION_ID <=", value, "researchDirectionId");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionIdLike(String value) {
            addCriterion("RESEARCH_DIRECTION_ID like", value, "researchDirectionId");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionIdNotLike(String value) {
            addCriterion("RESEARCH_DIRECTION_ID not like", value, "researchDirectionId");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionIdIn(List<String> values) {
            addCriterion("RESEARCH_DIRECTION_ID in", values, "researchDirectionId");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionIdNotIn(List<String> values) {
            addCriterion("RESEARCH_DIRECTION_ID not in", values, "researchDirectionId");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionIdBetween(String value1, String value2) {
            addCriterion("RESEARCH_DIRECTION_ID between", value1, value2, "researchDirectionId");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionIdNotBetween(String value1, String value2) {
            addCriterion("RESEARCH_DIRECTION_ID not between", value1, value2, "researchDirectionId");
            return (Criteria) this;
        }

        public Criteria andOrgAuditAdviceIsNull() {
            addCriterion("ORG_AUDIT_ADVICE is null");
            return (Criteria) this;
        }

        public Criteria andOrgAuditAdviceIsNotNull() {
            addCriterion("ORG_AUDIT_ADVICE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgAuditAdviceEqualTo(String value) {
            addCriterion("ORG_AUDIT_ADVICE =", value, "orgAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andOrgAuditAdviceNotEqualTo(String value) {
            addCriterion("ORG_AUDIT_ADVICE <>", value, "orgAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andOrgAuditAdviceGreaterThan(String value) {
            addCriterion("ORG_AUDIT_ADVICE >", value, "orgAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andOrgAuditAdviceGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_AUDIT_ADVICE >=", value, "orgAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andOrgAuditAdviceLessThan(String value) {
            addCriterion("ORG_AUDIT_ADVICE <", value, "orgAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andOrgAuditAdviceLessThanOrEqualTo(String value) {
            addCriterion("ORG_AUDIT_ADVICE <=", value, "orgAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andOrgAuditAdviceLike(String value) {
            addCriterion("ORG_AUDIT_ADVICE like", value, "orgAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andOrgAuditAdviceNotLike(String value) {
            addCriterion("ORG_AUDIT_ADVICE not like", value, "orgAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andOrgAuditAdviceIn(List<String> values) {
            addCriterion("ORG_AUDIT_ADVICE in", values, "orgAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andOrgAuditAdviceNotIn(List<String> values) {
            addCriterion("ORG_AUDIT_ADVICE not in", values, "orgAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andOrgAuditAdviceBetween(String value1, String value2) {
            addCriterion("ORG_AUDIT_ADVICE between", value1, value2, "orgAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andOrgAuditAdviceNotBetween(String value1, String value2) {
            addCriterion("ORG_AUDIT_ADVICE not between", value1, value2, "orgAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditAdviceIsNull() {
            addCriterion("SCHOOL_AUDIT_ADVICE is null");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditAdviceIsNotNull() {
            addCriterion("SCHOOL_AUDIT_ADVICE is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditAdviceEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_ADVICE =", value, "schoolAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditAdviceNotEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_ADVICE <>", value, "schoolAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditAdviceGreaterThan(String value) {
            addCriterion("SCHOOL_AUDIT_ADVICE >", value, "schoolAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditAdviceGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_ADVICE >=", value, "schoolAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditAdviceLessThan(String value) {
            addCriterion("SCHOOL_AUDIT_ADVICE <", value, "schoolAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditAdviceLessThanOrEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_ADVICE <=", value, "schoolAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditAdviceLike(String value) {
            addCriterion("SCHOOL_AUDIT_ADVICE like", value, "schoolAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditAdviceNotLike(String value) {
            addCriterion("SCHOOL_AUDIT_ADVICE not like", value, "schoolAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditAdviceIn(List<String> values) {
            addCriterion("SCHOOL_AUDIT_ADVICE in", values, "schoolAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditAdviceNotIn(List<String> values) {
            addCriterion("SCHOOL_AUDIT_ADVICE not in", values, "schoolAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditAdviceBetween(String value1, String value2) {
            addCriterion("SCHOOL_AUDIT_ADVICE between", value1, value2, "schoolAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditAdviceNotBetween(String value1, String value2) {
            addCriterion("SCHOOL_AUDIT_ADVICE not between", value1, value2, "schoolAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andAcademicRecruitNumIsNull() {
            addCriterion("ACADEMIC_RECRUIT_NUM is null");
            return (Criteria) this;
        }

        public Criteria andAcademicRecruitNumIsNotNull() {
            addCriterion("ACADEMIC_RECRUIT_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andAcademicRecruitNumEqualTo(String value) {
            addCriterion("ACADEMIC_RECRUIT_NUM =", value, "academicRecruitNum");
            return (Criteria) this;
        }

        public Criteria andAcademicRecruitNumNotEqualTo(String value) {
            addCriterion("ACADEMIC_RECRUIT_NUM <>", value, "academicRecruitNum");
            return (Criteria) this;
        }

        public Criteria andAcademicRecruitNumGreaterThan(String value) {
            addCriterion("ACADEMIC_RECRUIT_NUM >", value, "academicRecruitNum");
            return (Criteria) this;
        }

        public Criteria andAcademicRecruitNumGreaterThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_RECRUIT_NUM >=", value, "academicRecruitNum");
            return (Criteria) this;
        }

        public Criteria andAcademicRecruitNumLessThan(String value) {
            addCriterion("ACADEMIC_RECRUIT_NUM <", value, "academicRecruitNum");
            return (Criteria) this;
        }

        public Criteria andAcademicRecruitNumLessThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_RECRUIT_NUM <=", value, "academicRecruitNum");
            return (Criteria) this;
        }

        public Criteria andAcademicRecruitNumLike(String value) {
            addCriterion("ACADEMIC_RECRUIT_NUM like", value, "academicRecruitNum");
            return (Criteria) this;
        }

        public Criteria andAcademicRecruitNumNotLike(String value) {
            addCriterion("ACADEMIC_RECRUIT_NUM not like", value, "academicRecruitNum");
            return (Criteria) this;
        }

        public Criteria andAcademicRecruitNumIn(List<String> values) {
            addCriterion("ACADEMIC_RECRUIT_NUM in", values, "academicRecruitNum");
            return (Criteria) this;
        }

        public Criteria andAcademicRecruitNumNotIn(List<String> values) {
            addCriterion("ACADEMIC_RECRUIT_NUM not in", values, "academicRecruitNum");
            return (Criteria) this;
        }

        public Criteria andAcademicRecruitNumBetween(String value1, String value2) {
            addCriterion("ACADEMIC_RECRUIT_NUM between", value1, value2, "academicRecruitNum");
            return (Criteria) this;
        }

        public Criteria andAcademicRecruitNumNotBetween(String value1, String value2) {
            addCriterion("ACADEMIC_RECRUIT_NUM not between", value1, value2, "academicRecruitNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedRecruitNumIsNull() {
            addCriterion("SPECIALIZED_RECRUIT_NUM is null");
            return (Criteria) this;
        }

        public Criteria andSpecializedRecruitNumIsNotNull() {
            addCriterion("SPECIALIZED_RECRUIT_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andSpecializedRecruitNumEqualTo(String value) {
            addCriterion("SPECIALIZED_RECRUIT_NUM =", value, "specializedRecruitNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedRecruitNumNotEqualTo(String value) {
            addCriterion("SPECIALIZED_RECRUIT_NUM <>", value, "specializedRecruitNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedRecruitNumGreaterThan(String value) {
            addCriterion("SPECIALIZED_RECRUIT_NUM >", value, "specializedRecruitNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedRecruitNumGreaterThanOrEqualTo(String value) {
            addCriterion("SPECIALIZED_RECRUIT_NUM >=", value, "specializedRecruitNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedRecruitNumLessThan(String value) {
            addCriterion("SPECIALIZED_RECRUIT_NUM <", value, "specializedRecruitNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedRecruitNumLessThanOrEqualTo(String value) {
            addCriterion("SPECIALIZED_RECRUIT_NUM <=", value, "specializedRecruitNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedRecruitNumLike(String value) {
            addCriterion("SPECIALIZED_RECRUIT_NUM like", value, "specializedRecruitNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedRecruitNumNotLike(String value) {
            addCriterion("SPECIALIZED_RECRUIT_NUM not like", value, "specializedRecruitNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedRecruitNumIn(List<String> values) {
            addCriterion("SPECIALIZED_RECRUIT_NUM in", values, "specializedRecruitNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedRecruitNumNotIn(List<String> values) {
            addCriterion("SPECIALIZED_RECRUIT_NUM not in", values, "specializedRecruitNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedRecruitNumBetween(String value1, String value2) {
            addCriterion("SPECIALIZED_RECRUIT_NUM between", value1, value2, "specializedRecruitNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedRecruitNumNotBetween(String value1, String value2) {
            addCriterion("SPECIALIZED_RECRUIT_NUM not between", value1, value2, "specializedRecruitNum");
            return (Criteria) this;
        }

        public Criteria andOrgUserFlowIsNull() {
            addCriterion("ORG_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andOrgUserFlowIsNotNull() {
            addCriterion("ORG_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andOrgUserFlowEqualTo(String value) {
            addCriterion("ORG_USER_FLOW =", value, "orgUserFlow");
            return (Criteria) this;
        }

        public Criteria andOrgUserFlowNotEqualTo(String value) {
            addCriterion("ORG_USER_FLOW <>", value, "orgUserFlow");
            return (Criteria) this;
        }

        public Criteria andOrgUserFlowGreaterThan(String value) {
            addCriterion("ORG_USER_FLOW >", value, "orgUserFlow");
            return (Criteria) this;
        }

        public Criteria andOrgUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_USER_FLOW >=", value, "orgUserFlow");
            return (Criteria) this;
        }

        public Criteria andOrgUserFlowLessThan(String value) {
            addCriterion("ORG_USER_FLOW <", value, "orgUserFlow");
            return (Criteria) this;
        }

        public Criteria andOrgUserFlowLessThanOrEqualTo(String value) {
            addCriterion("ORG_USER_FLOW <=", value, "orgUserFlow");
            return (Criteria) this;
        }

        public Criteria andOrgUserFlowLike(String value) {
            addCriterion("ORG_USER_FLOW like", value, "orgUserFlow");
            return (Criteria) this;
        }

        public Criteria andOrgUserFlowNotLike(String value) {
            addCriterion("ORG_USER_FLOW not like", value, "orgUserFlow");
            return (Criteria) this;
        }

        public Criteria andOrgUserFlowIn(List<String> values) {
            addCriterion("ORG_USER_FLOW in", values, "orgUserFlow");
            return (Criteria) this;
        }

        public Criteria andOrgUserFlowNotIn(List<String> values) {
            addCriterion("ORG_USER_FLOW not in", values, "orgUserFlow");
            return (Criteria) this;
        }

        public Criteria andOrgUserFlowBetween(String value1, String value2) {
            addCriterion("ORG_USER_FLOW between", value1, value2, "orgUserFlow");
            return (Criteria) this;
        }

        public Criteria andOrgUserFlowNotBetween(String value1, String value2) {
            addCriterion("ORG_USER_FLOW not between", value1, value2, "orgUserFlow");
            return (Criteria) this;
        }

        public Criteria andOrgUserNameIsNull() {
            addCriterion("ORG_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgUserNameIsNotNull() {
            addCriterion("ORG_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgUserNameEqualTo(String value) {
            addCriterion("ORG_USER_NAME =", value, "orgUserName");
            return (Criteria) this;
        }

        public Criteria andOrgUserNameNotEqualTo(String value) {
            addCriterion("ORG_USER_NAME <>", value, "orgUserName");
            return (Criteria) this;
        }

        public Criteria andOrgUserNameGreaterThan(String value) {
            addCriterion("ORG_USER_NAME >", value, "orgUserName");
            return (Criteria) this;
        }

        public Criteria andOrgUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_USER_NAME >=", value, "orgUserName");
            return (Criteria) this;
        }

        public Criteria andOrgUserNameLessThan(String value) {
            addCriterion("ORG_USER_NAME <", value, "orgUserName");
            return (Criteria) this;
        }

        public Criteria andOrgUserNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_USER_NAME <=", value, "orgUserName");
            return (Criteria) this;
        }

        public Criteria andOrgUserNameLike(String value) {
            addCriterion("ORG_USER_NAME like", value, "orgUserName");
            return (Criteria) this;
        }

        public Criteria andOrgUserNameNotLike(String value) {
            addCriterion("ORG_USER_NAME not like", value, "orgUserName");
            return (Criteria) this;
        }

        public Criteria andOrgUserNameIn(List<String> values) {
            addCriterion("ORG_USER_NAME in", values, "orgUserName");
            return (Criteria) this;
        }

        public Criteria andOrgUserNameNotIn(List<String> values) {
            addCriterion("ORG_USER_NAME not in", values, "orgUserName");
            return (Criteria) this;
        }

        public Criteria andOrgUserNameBetween(String value1, String value2) {
            addCriterion("ORG_USER_NAME between", value1, value2, "orgUserName");
            return (Criteria) this;
        }

        public Criteria andOrgUserNameNotBetween(String value1, String value2) {
            addCriterion("ORG_USER_NAME not between", value1, value2, "orgUserName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeIsNull() {
            addCriterion("ORG_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeIsNotNull() {
            addCriterion("ORG_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeEqualTo(String value) {
            addCriterion("ORG_AUDIT_TIME =", value, "orgAuditTime");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeNotEqualTo(String value) {
            addCriterion("ORG_AUDIT_TIME <>", value, "orgAuditTime");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeGreaterThan(String value) {
            addCriterion("ORG_AUDIT_TIME >", value, "orgAuditTime");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_AUDIT_TIME >=", value, "orgAuditTime");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeLessThan(String value) {
            addCriterion("ORG_AUDIT_TIME <", value, "orgAuditTime");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("ORG_AUDIT_TIME <=", value, "orgAuditTime");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeLike(String value) {
            addCriterion("ORG_AUDIT_TIME like", value, "orgAuditTime");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeNotLike(String value) {
            addCriterion("ORG_AUDIT_TIME not like", value, "orgAuditTime");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeIn(List<String> values) {
            addCriterion("ORG_AUDIT_TIME in", values, "orgAuditTime");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeNotIn(List<String> values) {
            addCriterion("ORG_AUDIT_TIME not in", values, "orgAuditTime");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeBetween(String value1, String value2) {
            addCriterion("ORG_AUDIT_TIME between", value1, value2, "orgAuditTime");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeNotBetween(String value1, String value2) {
            addCriterion("ORG_AUDIT_TIME not between", value1, value2, "orgAuditTime");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeIsNull() {
            addCriterion("SCHOOL_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeIsNotNull() {
            addCriterion("SCHOOL_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_TIME =", value, "schoolAuditTime");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeNotEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_TIME <>", value, "schoolAuditTime");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeGreaterThan(String value) {
            addCriterion("SCHOOL_AUDIT_TIME >", value, "schoolAuditTime");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_TIME >=", value, "schoolAuditTime");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeLessThan(String value) {
            addCriterion("SCHOOL_AUDIT_TIME <", value, "schoolAuditTime");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_TIME <=", value, "schoolAuditTime");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeLike(String value) {
            addCriterion("SCHOOL_AUDIT_TIME like", value, "schoolAuditTime");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeNotLike(String value) {
            addCriterion("SCHOOL_AUDIT_TIME not like", value, "schoolAuditTime");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeIn(List<String> values) {
            addCriterion("SCHOOL_AUDIT_TIME in", values, "schoolAuditTime");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeNotIn(List<String> values) {
            addCriterion("SCHOOL_AUDIT_TIME not in", values, "schoolAuditTime");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeBetween(String value1, String value2) {
            addCriterion("SCHOOL_AUDIT_TIME between", value1, value2, "schoolAuditTime");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeNotBetween(String value1, String value2) {
            addCriterion("SCHOOL_AUDIT_TIME not between", value1, value2, "schoolAuditTime");
            return (Criteria) this;
        }

        public Criteria andTeacherApplyTimeIsNull() {
            addCriterion("TEACHER_APPLY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTeacherApplyTimeIsNotNull() {
            addCriterion("TEACHER_APPLY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherApplyTimeEqualTo(String value) {
            addCriterion("TEACHER_APPLY_TIME =", value, "teacherApplyTime");
            return (Criteria) this;
        }

        public Criteria andTeacherApplyTimeNotEqualTo(String value) {
            addCriterion("TEACHER_APPLY_TIME <>", value, "teacherApplyTime");
            return (Criteria) this;
        }

        public Criteria andTeacherApplyTimeGreaterThan(String value) {
            addCriterion("TEACHER_APPLY_TIME >", value, "teacherApplyTime");
            return (Criteria) this;
        }

        public Criteria andTeacherApplyTimeGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_APPLY_TIME >=", value, "teacherApplyTime");
            return (Criteria) this;
        }

        public Criteria andTeacherApplyTimeLessThan(String value) {
            addCriterion("TEACHER_APPLY_TIME <", value, "teacherApplyTime");
            return (Criteria) this;
        }

        public Criteria andTeacherApplyTimeLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_APPLY_TIME <=", value, "teacherApplyTime");
            return (Criteria) this;
        }

        public Criteria andTeacherApplyTimeLike(String value) {
            addCriterion("TEACHER_APPLY_TIME like", value, "teacherApplyTime");
            return (Criteria) this;
        }

        public Criteria andTeacherApplyTimeNotLike(String value) {
            addCriterion("TEACHER_APPLY_TIME not like", value, "teacherApplyTime");
            return (Criteria) this;
        }

        public Criteria andTeacherApplyTimeIn(List<String> values) {
            addCriterion("TEACHER_APPLY_TIME in", values, "teacherApplyTime");
            return (Criteria) this;
        }

        public Criteria andTeacherApplyTimeNotIn(List<String> values) {
            addCriterion("TEACHER_APPLY_TIME not in", values, "teacherApplyTime");
            return (Criteria) this;
        }

        public Criteria andTeacherApplyTimeBetween(String value1, String value2) {
            addCriterion("TEACHER_APPLY_TIME between", value1, value2, "teacherApplyTime");
            return (Criteria) this;
        }

        public Criteria andTeacherApplyTimeNotBetween(String value1, String value2) {
            addCriterion("TEACHER_APPLY_TIME not between", value1, value2, "teacherApplyTime");
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