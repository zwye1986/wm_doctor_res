package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ResEvaluationScoreExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResEvaluationScoreExample() {
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

        public Criteria andSubjectFlowIsNull() {
            addCriterion("SUBJECT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowIsNotNull() {
            addCriterion("SUBJECT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowEqualTo(String value) {
            addCriterion("SUBJECT_FLOW =", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowNotEqualTo(String value) {
            addCriterion("SUBJECT_FLOW <>", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowGreaterThan(String value) {
            addCriterion("SUBJECT_FLOW >", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_FLOW >=", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowLessThan(String value) {
            addCriterion("SUBJECT_FLOW <", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_FLOW <=", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowLike(String value) {
            addCriterion("SUBJECT_FLOW like", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowNotLike(String value) {
            addCriterion("SUBJECT_FLOW not like", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowIn(List<String> values) {
            addCriterion("SUBJECT_FLOW in", values, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowNotIn(List<String> values) {
            addCriterion("SUBJECT_FLOW not in", values, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowBetween(String value1, String value2) {
            addCriterion("SUBJECT_FLOW between", value1, value2, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_FLOW not between", value1, value2, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectNameIsNull() {
            addCriterion("SUBJECT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSubjectNameIsNotNull() {
            addCriterion("SUBJECT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectNameEqualTo(String value) {
            addCriterion("SUBJECT_NAME =", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotEqualTo(String value) {
            addCriterion("SUBJECT_NAME <>", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameGreaterThan(String value) {
            addCriterion("SUBJECT_NAME >", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_NAME >=", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameLessThan(String value) {
            addCriterion("SUBJECT_NAME <", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_NAME <=", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameLike(String value) {
            addCriterion("SUBJECT_NAME like", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotLike(String value) {
            addCriterion("SUBJECT_NAME not like", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameIn(List<String> values) {
            addCriterion("SUBJECT_NAME in", values, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotIn(List<String> values) {
            addCriterion("SUBJECT_NAME not in", values, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameBetween(String value1, String value2) {
            addCriterion("SUBJECT_NAME between", value1, value2, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_NAME not between", value1, value2, "subjectName");
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

        public Criteria andItemIdIsNull() {
            addCriterion("ITEM_ID is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("ITEM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(String value) {
            addCriterion("ITEM_ID =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(String value) {
            addCriterion("ITEM_ID <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(String value) {
            addCriterion("ITEM_ID >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_ID >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(String value) {
            addCriterion("ITEM_ID <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(String value) {
            addCriterion("ITEM_ID <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLike(String value) {
            addCriterion("ITEM_ID like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotLike(String value) {
            addCriterion("ITEM_ID not like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<String> values) {
            addCriterion("ITEM_ID in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<String> values) {
            addCriterion("ITEM_ID not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(String value1, String value2) {
            addCriterion("ITEM_ID between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(String value1, String value2) {
            addCriterion("ITEM_ID not between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNull() {
            addCriterion("ITEM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNotNull() {
            addCriterion("ITEM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andItemNameEqualTo(String value) {
            addCriterion("ITEM_NAME =", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotEqualTo(String value) {
            addCriterion("ITEM_NAME <>", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThan(String value) {
            addCriterion("ITEM_NAME >", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_NAME >=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThan(String value) {
            addCriterion("ITEM_NAME <", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThanOrEqualTo(String value) {
            addCriterion("ITEM_NAME <=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLike(String value) {
            addCriterion("ITEM_NAME like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotLike(String value) {
            addCriterion("ITEM_NAME not like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameIn(List<String> values) {
            addCriterion("ITEM_NAME in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotIn(List<String> values) {
            addCriterion("ITEM_NAME not in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameBetween(String value1, String value2) {
            addCriterion("ITEM_NAME between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotBetween(String value1, String value2) {
            addCriterion("ITEM_NAME not between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andEvaluationYearIsNull() {
            addCriterion("EVALUATION_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andEvaluationYearIsNotNull() {
            addCriterion("EVALUATION_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluationYearEqualTo(String value) {
            addCriterion("EVALUATION_YEAR =", value, "evaluationYear");
            return (Criteria) this;
        }

        public Criteria andEvaluationYearNotEqualTo(String value) {
            addCriterion("EVALUATION_YEAR <>", value, "evaluationYear");
            return (Criteria) this;
        }

        public Criteria andEvaluationYearGreaterThan(String value) {
            addCriterion("EVALUATION_YEAR >", value, "evaluationYear");
            return (Criteria) this;
        }

        public Criteria andEvaluationYearGreaterThanOrEqualTo(String value) {
            addCriterion("EVALUATION_YEAR >=", value, "evaluationYear");
            return (Criteria) this;
        }

        public Criteria andEvaluationYearLessThan(String value) {
            addCriterion("EVALUATION_YEAR <", value, "evaluationYear");
            return (Criteria) this;
        }

        public Criteria andEvaluationYearLessThanOrEqualTo(String value) {
            addCriterion("EVALUATION_YEAR <=", value, "evaluationYear");
            return (Criteria) this;
        }

        public Criteria andEvaluationYearLike(String value) {
            addCriterion("EVALUATION_YEAR like", value, "evaluationYear");
            return (Criteria) this;
        }

        public Criteria andEvaluationYearNotLike(String value) {
            addCriterion("EVALUATION_YEAR not like", value, "evaluationYear");
            return (Criteria) this;
        }

        public Criteria andEvaluationYearIn(List<String> values) {
            addCriterion("EVALUATION_YEAR in", values, "evaluationYear");
            return (Criteria) this;
        }

        public Criteria andEvaluationYearNotIn(List<String> values) {
            addCriterion("EVALUATION_YEAR not in", values, "evaluationYear");
            return (Criteria) this;
        }

        public Criteria andEvaluationYearBetween(String value1, String value2) {
            addCriterion("EVALUATION_YEAR between", value1, value2, "evaluationYear");
            return (Criteria) this;
        }

        public Criteria andEvaluationYearNotBetween(String value1, String value2) {
            addCriterion("EVALUATION_YEAR not between", value1, value2, "evaluationYear");
            return (Criteria) this;
        }

        public Criteria andOwnerScoreIsNull() {
            addCriterion("OWNER_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andOwnerScoreIsNotNull() {
            addCriterion("OWNER_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerScoreEqualTo(String value) {
            addCriterion("OWNER_SCORE =", value, "ownerScore");
            return (Criteria) this;
        }

        public Criteria andOwnerScoreNotEqualTo(String value) {
            addCriterion("OWNER_SCORE <>", value, "ownerScore");
            return (Criteria) this;
        }

        public Criteria andOwnerScoreGreaterThan(String value) {
            addCriterion("OWNER_SCORE >", value, "ownerScore");
            return (Criteria) this;
        }

        public Criteria andOwnerScoreGreaterThanOrEqualTo(String value) {
            addCriterion("OWNER_SCORE >=", value, "ownerScore");
            return (Criteria) this;
        }

        public Criteria andOwnerScoreLessThan(String value) {
            addCriterion("OWNER_SCORE <", value, "ownerScore");
            return (Criteria) this;
        }

        public Criteria andOwnerScoreLessThanOrEqualTo(String value) {
            addCriterion("OWNER_SCORE <=", value, "ownerScore");
            return (Criteria) this;
        }

        public Criteria andOwnerScoreLike(String value) {
            addCriterion("OWNER_SCORE like", value, "ownerScore");
            return (Criteria) this;
        }

        public Criteria andOwnerScoreNotLike(String value) {
            addCriterion("OWNER_SCORE not like", value, "ownerScore");
            return (Criteria) this;
        }

        public Criteria andOwnerScoreIn(List<String> values) {
            addCriterion("OWNER_SCORE in", values, "ownerScore");
            return (Criteria) this;
        }

        public Criteria andOwnerScoreNotIn(List<String> values) {
            addCriterion("OWNER_SCORE not in", values, "ownerScore");
            return (Criteria) this;
        }

        public Criteria andOwnerScoreBetween(String value1, String value2) {
            addCriterion("OWNER_SCORE between", value1, value2, "ownerScore");
            return (Criteria) this;
        }

        public Criteria andOwnerScoreNotBetween(String value1, String value2) {
            addCriterion("OWNER_SCORE not between", value1, value2, "ownerScore");
            return (Criteria) this;
        }

        public Criteria andSpeScoreIsNull() {
            addCriterion("SPE_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andSpeScoreIsNotNull() {
            addCriterion("SPE_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andSpeScoreEqualTo(String value) {
            addCriterion("SPE_SCORE =", value, "speScore");
            return (Criteria) this;
        }

        public Criteria andSpeScoreNotEqualTo(String value) {
            addCriterion("SPE_SCORE <>", value, "speScore");
            return (Criteria) this;
        }

        public Criteria andSpeScoreGreaterThan(String value) {
            addCriterion("SPE_SCORE >", value, "speScore");
            return (Criteria) this;
        }

        public Criteria andSpeScoreGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_SCORE >=", value, "speScore");
            return (Criteria) this;
        }

        public Criteria andSpeScoreLessThan(String value) {
            addCriterion("SPE_SCORE <", value, "speScore");
            return (Criteria) this;
        }

        public Criteria andSpeScoreLessThanOrEqualTo(String value) {
            addCriterion("SPE_SCORE <=", value, "speScore");
            return (Criteria) this;
        }

        public Criteria andSpeScoreLike(String value) {
            addCriterion("SPE_SCORE like", value, "speScore");
            return (Criteria) this;
        }

        public Criteria andSpeScoreNotLike(String value) {
            addCriterion("SPE_SCORE not like", value, "speScore");
            return (Criteria) this;
        }

        public Criteria andSpeScoreIn(List<String> values) {
            addCriterion("SPE_SCORE in", values, "speScore");
            return (Criteria) this;
        }

        public Criteria andSpeScoreNotIn(List<String> values) {
            addCriterion("SPE_SCORE not in", values, "speScore");
            return (Criteria) this;
        }

        public Criteria andSpeScoreBetween(String value1, String value2) {
            addCriterion("SPE_SCORE between", value1, value2, "speScore");
            return (Criteria) this;
        }

        public Criteria andSpeScoreNotBetween(String value1, String value2) {
            addCriterion("SPE_SCORE not between", value1, value2, "speScore");
            return (Criteria) this;
        }

        public Criteria andSpeReasonIsNull() {
            addCriterion("SPE_REASON is null");
            return (Criteria) this;
        }

        public Criteria andSpeReasonIsNotNull() {
            addCriterion("SPE_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andSpeReasonEqualTo(String value) {
            addCriterion("SPE_REASON =", value, "speReason");
            return (Criteria) this;
        }

        public Criteria andSpeReasonNotEqualTo(String value) {
            addCriterion("SPE_REASON <>", value, "speReason");
            return (Criteria) this;
        }

        public Criteria andSpeReasonGreaterThan(String value) {
            addCriterion("SPE_REASON >", value, "speReason");
            return (Criteria) this;
        }

        public Criteria andSpeReasonGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_REASON >=", value, "speReason");
            return (Criteria) this;
        }

        public Criteria andSpeReasonLessThan(String value) {
            addCriterion("SPE_REASON <", value, "speReason");
            return (Criteria) this;
        }

        public Criteria andSpeReasonLessThanOrEqualTo(String value) {
            addCriterion("SPE_REASON <=", value, "speReason");
            return (Criteria) this;
        }

        public Criteria andSpeReasonLike(String value) {
            addCriterion("SPE_REASON like", value, "speReason");
            return (Criteria) this;
        }

        public Criteria andSpeReasonNotLike(String value) {
            addCriterion("SPE_REASON not like", value, "speReason");
            return (Criteria) this;
        }

        public Criteria andSpeReasonIn(List<String> values) {
            addCriterion("SPE_REASON in", values, "speReason");
            return (Criteria) this;
        }

        public Criteria andSpeReasonNotIn(List<String> values) {
            addCriterion("SPE_REASON not in", values, "speReason");
            return (Criteria) this;
        }

        public Criteria andSpeReasonBetween(String value1, String value2) {
            addCriterion("SPE_REASON between", value1, value2, "speReason");
            return (Criteria) this;
        }

        public Criteria andSpeReasonNotBetween(String value1, String value2) {
            addCriterion("SPE_REASON not between", value1, value2, "speReason");
            return (Criteria) this;
        }

        public Criteria andSpeUserFlowIsNull() {
            addCriterion("SPE_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSpeUserFlowIsNotNull() {
            addCriterion("SPE_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSpeUserFlowEqualTo(String value) {
            addCriterion("SPE_USER_FLOW =", value, "speUserFlow");
            return (Criteria) this;
        }

        public Criteria andSpeUserFlowNotEqualTo(String value) {
            addCriterion("SPE_USER_FLOW <>", value, "speUserFlow");
            return (Criteria) this;
        }

        public Criteria andSpeUserFlowGreaterThan(String value) {
            addCriterion("SPE_USER_FLOW >", value, "speUserFlow");
            return (Criteria) this;
        }

        public Criteria andSpeUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_USER_FLOW >=", value, "speUserFlow");
            return (Criteria) this;
        }

        public Criteria andSpeUserFlowLessThan(String value) {
            addCriterion("SPE_USER_FLOW <", value, "speUserFlow");
            return (Criteria) this;
        }

        public Criteria andSpeUserFlowLessThanOrEqualTo(String value) {
            addCriterion("SPE_USER_FLOW <=", value, "speUserFlow");
            return (Criteria) this;
        }

        public Criteria andSpeUserFlowLike(String value) {
            addCriterion("SPE_USER_FLOW like", value, "speUserFlow");
            return (Criteria) this;
        }

        public Criteria andSpeUserFlowNotLike(String value) {
            addCriterion("SPE_USER_FLOW not like", value, "speUserFlow");
            return (Criteria) this;
        }

        public Criteria andSpeUserFlowIn(List<String> values) {
            addCriterion("SPE_USER_FLOW in", values, "speUserFlow");
            return (Criteria) this;
        }

        public Criteria andSpeUserFlowNotIn(List<String> values) {
            addCriterion("SPE_USER_FLOW not in", values, "speUserFlow");
            return (Criteria) this;
        }

        public Criteria andSpeUserFlowBetween(String value1, String value2) {
            addCriterion("SPE_USER_FLOW between", value1, value2, "speUserFlow");
            return (Criteria) this;
        }

        public Criteria andSpeUserFlowNotBetween(String value1, String value2) {
            addCriterion("SPE_USER_FLOW not between", value1, value2, "speUserFlow");
            return (Criteria) this;
        }

        public Criteria andSpeUserNameIsNull() {
            addCriterion("SPE_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSpeUserNameIsNotNull() {
            addCriterion("SPE_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSpeUserNameEqualTo(String value) {
            addCriterion("SPE_USER_NAME =", value, "speUserName");
            return (Criteria) this;
        }

        public Criteria andSpeUserNameNotEqualTo(String value) {
            addCriterion("SPE_USER_NAME <>", value, "speUserName");
            return (Criteria) this;
        }

        public Criteria andSpeUserNameGreaterThan(String value) {
            addCriterion("SPE_USER_NAME >", value, "speUserName");
            return (Criteria) this;
        }

        public Criteria andSpeUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_USER_NAME >=", value, "speUserName");
            return (Criteria) this;
        }

        public Criteria andSpeUserNameLessThan(String value) {
            addCriterion("SPE_USER_NAME <", value, "speUserName");
            return (Criteria) this;
        }

        public Criteria andSpeUserNameLessThanOrEqualTo(String value) {
            addCriterion("SPE_USER_NAME <=", value, "speUserName");
            return (Criteria) this;
        }

        public Criteria andSpeUserNameLike(String value) {
            addCriterion("SPE_USER_NAME like", value, "speUserName");
            return (Criteria) this;
        }

        public Criteria andSpeUserNameNotLike(String value) {
            addCriterion("SPE_USER_NAME not like", value, "speUserName");
            return (Criteria) this;
        }

        public Criteria andSpeUserNameIn(List<String> values) {
            addCriterion("SPE_USER_NAME in", values, "speUserName");
            return (Criteria) this;
        }

        public Criteria andSpeUserNameNotIn(List<String> values) {
            addCriterion("SPE_USER_NAME not in", values, "speUserName");
            return (Criteria) this;
        }

        public Criteria andSpeUserNameBetween(String value1, String value2) {
            addCriterion("SPE_USER_NAME between", value1, value2, "speUserName");
            return (Criteria) this;
        }

        public Criteria andSpeUserNameNotBetween(String value1, String value2) {
            addCriterion("SPE_USER_NAME not between", value1, value2, "speUserName");
            return (Criteria) this;
        }

        public Criteria andSpeContentIsNull() {
            addCriterion("SPE_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andSpeContentIsNotNull() {
            addCriterion("SPE_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andSpeContentEqualTo(String value) {
            addCriterion("SPE_CONTENT =", value, "speContent");
            return (Criteria) this;
        }

        public Criteria andSpeContentNotEqualTo(String value) {
            addCriterion("SPE_CONTENT <>", value, "speContent");
            return (Criteria) this;
        }

        public Criteria andSpeContentGreaterThan(String value) {
            addCriterion("SPE_CONTENT >", value, "speContent");
            return (Criteria) this;
        }

        public Criteria andSpeContentGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_CONTENT >=", value, "speContent");
            return (Criteria) this;
        }

        public Criteria andSpeContentLessThan(String value) {
            addCriterion("SPE_CONTENT <", value, "speContent");
            return (Criteria) this;
        }

        public Criteria andSpeContentLessThanOrEqualTo(String value) {
            addCriterion("SPE_CONTENT <=", value, "speContent");
            return (Criteria) this;
        }

        public Criteria andSpeContentLike(String value) {
            addCriterion("SPE_CONTENT like", value, "speContent");
            return (Criteria) this;
        }

        public Criteria andSpeContentNotLike(String value) {
            addCriterion("SPE_CONTENT not like", value, "speContent");
            return (Criteria) this;
        }

        public Criteria andSpeContentIn(List<String> values) {
            addCriterion("SPE_CONTENT in", values, "speContent");
            return (Criteria) this;
        }

        public Criteria andSpeContentNotIn(List<String> values) {
            addCriterion("SPE_CONTENT not in", values, "speContent");
            return (Criteria) this;
        }

        public Criteria andSpeContentBetween(String value1, String value2) {
            addCriterion("SPE_CONTENT between", value1, value2, "speContent");
            return (Criteria) this;
        }

        public Criteria andSpeContentNotBetween(String value1, String value2) {
            addCriterion("SPE_CONTENT not between", value1, value2, "speContent");
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

        public Criteria andCoreIndicatorsIsNull() {
            addCriterion("CORE_INDICATORS is null");
            return (Criteria) this;
        }

        public Criteria andCoreIndicatorsIsNotNull() {
            addCriterion("CORE_INDICATORS is not null");
            return (Criteria) this;
        }

        public Criteria andCoreIndicatorsEqualTo(String value) {
            addCriterion("CORE_INDICATORS =", value, "coreIndicators");
            return (Criteria) this;
        }

        public Criteria andCoreIndicatorsNotEqualTo(String value) {
            addCriterion("CORE_INDICATORS <>", value, "coreIndicators");
            return (Criteria) this;
        }

        public Criteria andCoreIndicatorsGreaterThan(String value) {
            addCriterion("CORE_INDICATORS >", value, "coreIndicators");
            return (Criteria) this;
        }

        public Criteria andCoreIndicatorsGreaterThanOrEqualTo(String value) {
            addCriterion("CORE_INDICATORS >=", value, "coreIndicators");
            return (Criteria) this;
        }

        public Criteria andCoreIndicatorsLessThan(String value) {
            addCriterion("CORE_INDICATORS <", value, "coreIndicators");
            return (Criteria) this;
        }

        public Criteria andCoreIndicatorsLessThanOrEqualTo(String value) {
            addCriterion("CORE_INDICATORS <=", value, "coreIndicators");
            return (Criteria) this;
        }

        public Criteria andCoreIndicatorsLike(String value) {
            addCriterion("CORE_INDICATORS like", value, "coreIndicators");
            return (Criteria) this;
        }

        public Criteria andCoreIndicatorsNotLike(String value) {
            addCriterion("CORE_INDICATORS not like", value, "coreIndicators");
            return (Criteria) this;
        }

        public Criteria andCoreIndicatorsIn(List<String> values) {
            addCriterion("CORE_INDICATORS in", values, "coreIndicators");
            return (Criteria) this;
        }

        public Criteria andCoreIndicatorsNotIn(List<String> values) {
            addCriterion("CORE_INDICATORS not in", values, "coreIndicators");
            return (Criteria) this;
        }

        public Criteria andCoreIndicatorsBetween(String value1, String value2) {
            addCriterion("CORE_INDICATORS between", value1, value2, "coreIndicators");
            return (Criteria) this;
        }

        public Criteria andCoreIndicatorsNotBetween(String value1, String value2) {
            addCriterion("CORE_INDICATORS not between", value1, value2, "coreIndicators");
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