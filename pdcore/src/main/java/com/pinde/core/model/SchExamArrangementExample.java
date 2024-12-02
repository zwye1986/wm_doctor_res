package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class SchExamArrangementExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SchExamArrangementExample() {
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

        public Criteria andAssessmentYearIsNull() {
            addCriterion("ASSESSMENT_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearIsNotNull() {
            addCriterion("ASSESSMENT_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearEqualTo(String value) {
            addCriterion("ASSESSMENT_YEAR =", value, "assessmentYear");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearNotEqualTo(String value) {
            addCriterion("ASSESSMENT_YEAR <>", value, "assessmentYear");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearGreaterThan(String value) {
            addCriterion("ASSESSMENT_YEAR >", value, "assessmentYear");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearGreaterThanOrEqualTo(String value) {
            addCriterion("ASSESSMENT_YEAR >=", value, "assessmentYear");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearLessThan(String value) {
            addCriterion("ASSESSMENT_YEAR <", value, "assessmentYear");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearLessThanOrEqualTo(String value) {
            addCriterion("ASSESSMENT_YEAR <=", value, "assessmentYear");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearLike(String value) {
            addCriterion("ASSESSMENT_YEAR like", value, "assessmentYear");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearNotLike(String value) {
            addCriterion("ASSESSMENT_YEAR not like", value, "assessmentYear");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearIn(List<String> values) {
            addCriterion("ASSESSMENT_YEAR in", values, "assessmentYear");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearNotIn(List<String> values) {
            addCriterion("ASSESSMENT_YEAR not in", values, "assessmentYear");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearBetween(String value1, String value2) {
            addCriterion("ASSESSMENT_YEAR between", value1, value2, "assessmentYear");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearNotBetween(String value1, String value2) {
            addCriterion("ASSESSMENT_YEAR not between", value1, value2, "assessmentYear");
            return (Criteria) this;
        }

        public Criteria andExampaperTypeIsNull() {
            addCriterion("EXAMPAPER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andExampaperTypeIsNotNull() {
            addCriterion("EXAMPAPER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andExampaperTypeEqualTo(String value) {
            addCriterion("EXAMPAPER_TYPE =", value, "exampaperType");
            return (Criteria) this;
        }

        public Criteria andExampaperTypeNotEqualTo(String value) {
            addCriterion("EXAMPAPER_TYPE <>", value, "exampaperType");
            return (Criteria) this;
        }

        public Criteria andExampaperTypeGreaterThan(String value) {
            addCriterion("EXAMPAPER_TYPE >", value, "exampaperType");
            return (Criteria) this;
        }

        public Criteria andExampaperTypeGreaterThanOrEqualTo(String value) {
            addCriterion("EXAMPAPER_TYPE >=", value, "exampaperType");
            return (Criteria) this;
        }

        public Criteria andExampaperTypeLessThan(String value) {
            addCriterion("EXAMPAPER_TYPE <", value, "exampaperType");
            return (Criteria) this;
        }

        public Criteria andExampaperTypeLessThanOrEqualTo(String value) {
            addCriterion("EXAMPAPER_TYPE <=", value, "exampaperType");
            return (Criteria) this;
        }

        public Criteria andExampaperTypeLike(String value) {
            addCriterion("EXAMPAPER_TYPE like", value, "exampaperType");
            return (Criteria) this;
        }

        public Criteria andExampaperTypeNotLike(String value) {
            addCriterion("EXAMPAPER_TYPE not like", value, "exampaperType");
            return (Criteria) this;
        }

        public Criteria andExampaperTypeIn(List<String> values) {
            addCriterion("EXAMPAPER_TYPE in", values, "exampaperType");
            return (Criteria) this;
        }

        public Criteria andExampaperTypeNotIn(List<String> values) {
            addCriterion("EXAMPAPER_TYPE not in", values, "exampaperType");
            return (Criteria) this;
        }

        public Criteria andExampaperTypeBetween(String value1, String value2) {
            addCriterion("EXAMPAPER_TYPE between", value1, value2, "exampaperType");
            return (Criteria) this;
        }

        public Criteria andExampaperTypeNotBetween(String value1, String value2) {
            addCriterion("EXAMPAPER_TYPE not between", value1, value2, "exampaperType");
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

        public Criteria andAssessmentStartTimeIsNull() {
            addCriterion("ASSESSMENT_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAssessmentStartTimeIsNotNull() {
            addCriterion("ASSESSMENT_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAssessmentStartTimeEqualTo(String value) {
            addCriterion("ASSESSMENT_START_TIME =", value, "assessmentStartTime");
            return (Criteria) this;
        }

        public Criteria andAssessmentStartTimeNotEqualTo(String value) {
            addCriterion("ASSESSMENT_START_TIME <>", value, "assessmentStartTime");
            return (Criteria) this;
        }

        public Criteria andAssessmentStartTimeGreaterThan(String value) {
            addCriterion("ASSESSMENT_START_TIME >", value, "assessmentStartTime");
            return (Criteria) this;
        }

        public Criteria andAssessmentStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("ASSESSMENT_START_TIME >=", value, "assessmentStartTime");
            return (Criteria) this;
        }

        public Criteria andAssessmentStartTimeLessThan(String value) {
            addCriterion("ASSESSMENT_START_TIME <", value, "assessmentStartTime");
            return (Criteria) this;
        }

        public Criteria andAssessmentStartTimeLessThanOrEqualTo(String value) {
            addCriterion("ASSESSMENT_START_TIME <=", value, "assessmentStartTime");
            return (Criteria) this;
        }

        public Criteria andAssessmentStartTimeLike(String value) {
            addCriterion("ASSESSMENT_START_TIME like", value, "assessmentStartTime");
            return (Criteria) this;
        }

        public Criteria andAssessmentStartTimeNotLike(String value) {
            addCriterion("ASSESSMENT_START_TIME not like", value, "assessmentStartTime");
            return (Criteria) this;
        }

        public Criteria andAssessmentStartTimeIn(List<String> values) {
            addCriterion("ASSESSMENT_START_TIME in", values, "assessmentStartTime");
            return (Criteria) this;
        }

        public Criteria andAssessmentStartTimeNotIn(List<String> values) {
            addCriterion("ASSESSMENT_START_TIME not in", values, "assessmentStartTime");
            return (Criteria) this;
        }

        public Criteria andAssessmentStartTimeBetween(String value1, String value2) {
            addCriterion("ASSESSMENT_START_TIME between", value1, value2, "assessmentStartTime");
            return (Criteria) this;
        }

        public Criteria andAssessmentStartTimeNotBetween(String value1, String value2) {
            addCriterion("ASSESSMENT_START_TIME not between", value1, value2, "assessmentStartTime");
            return (Criteria) this;
        }

        public Criteria andAssessmentEndTimeIsNull() {
            addCriterion("ASSESSMENT_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAssessmentEndTimeIsNotNull() {
            addCriterion("ASSESSMENT_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAssessmentEndTimeEqualTo(String value) {
            addCriterion("ASSESSMENT_END_TIME =", value, "assessmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAssessmentEndTimeNotEqualTo(String value) {
            addCriterion("ASSESSMENT_END_TIME <>", value, "assessmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAssessmentEndTimeGreaterThan(String value) {
            addCriterion("ASSESSMENT_END_TIME >", value, "assessmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAssessmentEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("ASSESSMENT_END_TIME >=", value, "assessmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAssessmentEndTimeLessThan(String value) {
            addCriterion("ASSESSMENT_END_TIME <", value, "assessmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAssessmentEndTimeLessThanOrEqualTo(String value) {
            addCriterion("ASSESSMENT_END_TIME <=", value, "assessmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAssessmentEndTimeLike(String value) {
            addCriterion("ASSESSMENT_END_TIME like", value, "assessmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAssessmentEndTimeNotLike(String value) {
            addCriterion("ASSESSMENT_END_TIME not like", value, "assessmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAssessmentEndTimeIn(List<String> values) {
            addCriterion("ASSESSMENT_END_TIME in", values, "assessmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAssessmentEndTimeNotIn(List<String> values) {
            addCriterion("ASSESSMENT_END_TIME not in", values, "assessmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAssessmentEndTimeBetween(String value1, String value2) {
            addCriterion("ASSESSMENT_END_TIME between", value1, value2, "assessmentEndTime");
            return (Criteria) this;
        }

        public Criteria andAssessmentEndTimeNotBetween(String value1, String value2) {
            addCriterion("ASSESSMENT_END_TIME not between", value1, value2, "assessmentEndTime");
            return (Criteria) this;
        }

        public Criteria andIsOpenIsNull() {
            addCriterion("IS_OPEN is null");
            return (Criteria) this;
        }

        public Criteria andIsOpenIsNotNull() {
            addCriterion("IS_OPEN is not null");
            return (Criteria) this;
        }

        public Criteria andIsOpenEqualTo(String value) {
            addCriterion("IS_OPEN =", value, "isOpen");
            return (Criteria) this;
        }

        public Criteria andIsOpenNotEqualTo(String value) {
            addCriterion("IS_OPEN <>", value, "isOpen");
            return (Criteria) this;
        }

        public Criteria andIsOpenGreaterThan(String value) {
            addCriterion("IS_OPEN >", value, "isOpen");
            return (Criteria) this;
        }

        public Criteria andIsOpenGreaterThanOrEqualTo(String value) {
            addCriterion("IS_OPEN >=", value, "isOpen");
            return (Criteria) this;
        }

        public Criteria andIsOpenLessThan(String value) {
            addCriterion("IS_OPEN <", value, "isOpen");
            return (Criteria) this;
        }

        public Criteria andIsOpenLessThanOrEqualTo(String value) {
            addCriterion("IS_OPEN <=", value, "isOpen");
            return (Criteria) this;
        }

        public Criteria andIsOpenLike(String value) {
            addCriterion("IS_OPEN like", value, "isOpen");
            return (Criteria) this;
        }

        public Criteria andIsOpenNotLike(String value) {
            addCriterion("IS_OPEN not like", value, "isOpen");
            return (Criteria) this;
        }

        public Criteria andIsOpenIn(List<String> values) {
            addCriterion("IS_OPEN in", values, "isOpen");
            return (Criteria) this;
        }

        public Criteria andIsOpenNotIn(List<String> values) {
            addCriterion("IS_OPEN not in", values, "isOpen");
            return (Criteria) this;
        }

        public Criteria andIsOpenBetween(String value1, String value2) {
            addCriterion("IS_OPEN between", value1, value2, "isOpen");
            return (Criteria) this;
        }

        public Criteria andIsOpenNotBetween(String value1, String value2) {
            addCriterion("IS_OPEN not between", value1, value2, "isOpen");
            return (Criteria) this;
        }

        public Criteria andExamNumberIsNull() {
            addCriterion("EXAM_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andExamNumberIsNotNull() {
            addCriterion("EXAM_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andExamNumberEqualTo(String value) {
            addCriterion("EXAM_NUMBER =", value, "examNumber");
            return (Criteria) this;
        }

        public Criteria andExamNumberNotEqualTo(String value) {
            addCriterion("EXAM_NUMBER <>", value, "examNumber");
            return (Criteria) this;
        }

        public Criteria andExamNumberGreaterThan(String value) {
            addCriterion("EXAM_NUMBER >", value, "examNumber");
            return (Criteria) this;
        }

        public Criteria andExamNumberGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_NUMBER >=", value, "examNumber");
            return (Criteria) this;
        }

        public Criteria andExamNumberLessThan(String value) {
            addCriterion("EXAM_NUMBER <", value, "examNumber");
            return (Criteria) this;
        }

        public Criteria andExamNumberLessThanOrEqualTo(String value) {
            addCriterion("EXAM_NUMBER <=", value, "examNumber");
            return (Criteria) this;
        }

        public Criteria andExamNumberLike(String value) {
            addCriterion("EXAM_NUMBER like", value, "examNumber");
            return (Criteria) this;
        }

        public Criteria andExamNumberNotLike(String value) {
            addCriterion("EXAM_NUMBER not like", value, "examNumber");
            return (Criteria) this;
        }

        public Criteria andExamNumberIn(List<String> values) {
            addCriterion("EXAM_NUMBER in", values, "examNumber");
            return (Criteria) this;
        }

        public Criteria andExamNumberNotIn(List<String> values) {
            addCriterion("EXAM_NUMBER not in", values, "examNumber");
            return (Criteria) this;
        }

        public Criteria andExamNumberBetween(String value1, String value2) {
            addCriterion("EXAM_NUMBER between", value1, value2, "examNumber");
            return (Criteria) this;
        }

        public Criteria andExamNumberNotBetween(String value1, String value2) {
            addCriterion("EXAM_NUMBER not between", value1, value2, "examNumber");
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

        public Criteria andExamDurationIsNull() {
            addCriterion("EXAM_DURATION is null");
            return (Criteria) this;
        }

        public Criteria andExamDurationIsNotNull() {
            addCriterion("EXAM_DURATION is not null");
            return (Criteria) this;
        }

        public Criteria andExamDurationEqualTo(String value) {
            addCriterion("EXAM_DURATION =", value, "examDuration");
            return (Criteria) this;
        }

        public Criteria andExamDurationNotEqualTo(String value) {
            addCriterion("EXAM_DURATION <>", value, "examDuration");
            return (Criteria) this;
        }

        public Criteria andExamDurationGreaterThan(String value) {
            addCriterion("EXAM_DURATION >", value, "examDuration");
            return (Criteria) this;
        }

        public Criteria andExamDurationGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_DURATION >=", value, "examDuration");
            return (Criteria) this;
        }

        public Criteria andExamDurationLessThan(String value) {
            addCriterion("EXAM_DURATION <", value, "examDuration");
            return (Criteria) this;
        }

        public Criteria andExamDurationLessThanOrEqualTo(String value) {
            addCriterion("EXAM_DURATION <=", value, "examDuration");
            return (Criteria) this;
        }

        public Criteria andExamDurationLike(String value) {
            addCriterion("EXAM_DURATION like", value, "examDuration");
            return (Criteria) this;
        }

        public Criteria andExamDurationNotLike(String value) {
            addCriterion("EXAM_DURATION not like", value, "examDuration");
            return (Criteria) this;
        }

        public Criteria andExamDurationIn(List<String> values) {
            addCriterion("EXAM_DURATION in", values, "examDuration");
            return (Criteria) this;
        }

        public Criteria andExamDurationNotIn(List<String> values) {
            addCriterion("EXAM_DURATION not in", values, "examDuration");
            return (Criteria) this;
        }

        public Criteria andExamDurationBetween(String value1, String value2) {
            addCriterion("EXAM_DURATION between", value1, value2, "examDuration");
            return (Criteria) this;
        }

        public Criteria andExamDurationNotBetween(String value1, String value2) {
            addCriterion("EXAM_DURATION not between", value1, value2, "examDuration");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeIsNull() {
            addCriterion("EXAM_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeIsNotNull() {
            addCriterion("EXAM_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeEqualTo(String value) {
            addCriterion("EXAM_START_TIME =", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeNotEqualTo(String value) {
            addCriterion("EXAM_START_TIME <>", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeGreaterThan(String value) {
            addCriterion("EXAM_START_TIME >", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_START_TIME >=", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeLessThan(String value) {
            addCriterion("EXAM_START_TIME <", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeLessThanOrEqualTo(String value) {
            addCriterion("EXAM_START_TIME <=", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeLike(String value) {
            addCriterion("EXAM_START_TIME like", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeNotLike(String value) {
            addCriterion("EXAM_START_TIME not like", value, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeIn(List<String> values) {
            addCriterion("EXAM_START_TIME in", values, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeNotIn(List<String> values) {
            addCriterion("EXAM_START_TIME not in", values, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeBetween(String value1, String value2) {
            addCriterion("EXAM_START_TIME between", value1, value2, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamStartTimeNotBetween(String value1, String value2) {
            addCriterion("EXAM_START_TIME not between", value1, value2, "examStartTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeIsNull() {
            addCriterion("EXAM_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeIsNotNull() {
            addCriterion("EXAM_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeEqualTo(String value) {
            addCriterion("EXAM_END_TIME =", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeNotEqualTo(String value) {
            addCriterion("EXAM_END_TIME <>", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeGreaterThan(String value) {
            addCriterion("EXAM_END_TIME >", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_END_TIME >=", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeLessThan(String value) {
            addCriterion("EXAM_END_TIME <", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeLessThanOrEqualTo(String value) {
            addCriterion("EXAM_END_TIME <=", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeLike(String value) {
            addCriterion("EXAM_END_TIME like", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeNotLike(String value) {
            addCriterion("EXAM_END_TIME not like", value, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeIn(List<String> values) {
            addCriterion("EXAM_END_TIME in", values, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeNotIn(List<String> values) {
            addCriterion("EXAM_END_TIME not in", values, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeBetween(String value1, String value2) {
            addCriterion("EXAM_END_TIME between", value1, value2, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andExamEndTimeNotBetween(String value1, String value2) {
            addCriterion("EXAM_END_TIME not between", value1, value2, "examEndTime");
            return (Criteria) this;
        }

        public Criteria andIsAppIsNull() {
            addCriterion("IS_APP is null");
            return (Criteria) this;
        }

        public Criteria andIsAppIsNotNull() {
            addCriterion("IS_APP is not null");
            return (Criteria) this;
        }

        public Criteria andIsAppEqualTo(String value) {
            addCriterion("IS_APP =", value, "isApp");
            return (Criteria) this;
        }

        public Criteria andIsAppNotEqualTo(String value) {
            addCriterion("IS_APP <>", value, "isApp");
            return (Criteria) this;
        }

        public Criteria andIsAppGreaterThan(String value) {
            addCriterion("IS_APP >", value, "isApp");
            return (Criteria) this;
        }

        public Criteria andIsAppGreaterThanOrEqualTo(String value) {
            addCriterion("IS_APP >=", value, "isApp");
            return (Criteria) this;
        }

        public Criteria andIsAppLessThan(String value) {
            addCriterion("IS_APP <", value, "isApp");
            return (Criteria) this;
        }

        public Criteria andIsAppLessThanOrEqualTo(String value) {
            addCriterion("IS_APP <=", value, "isApp");
            return (Criteria) this;
        }

        public Criteria andIsAppLike(String value) {
            addCriterion("IS_APP like", value, "isApp");
            return (Criteria) this;
        }

        public Criteria andIsAppNotLike(String value) {
            addCriterion("IS_APP not like", value, "isApp");
            return (Criteria) this;
        }

        public Criteria andIsAppIn(List<String> values) {
            addCriterion("IS_APP in", values, "isApp");
            return (Criteria) this;
        }

        public Criteria andIsAppNotIn(List<String> values) {
            addCriterion("IS_APP not in", values, "isApp");
            return (Criteria) this;
        }

        public Criteria andIsAppBetween(String value1, String value2) {
            addCriterion("IS_APP between", value1, value2, "isApp");
            return (Criteria) this;
        }

        public Criteria andIsAppNotBetween(String value1, String value2) {
            addCriterion("IS_APP not between", value1, value2, "isApp");
            return (Criteria) this;
        }

        public Criteria andIsWebIsNull() {
            addCriterion("IS_WEB is null");
            return (Criteria) this;
        }

        public Criteria andIsWebIsNotNull() {
            addCriterion("IS_WEB is not null");
            return (Criteria) this;
        }

        public Criteria andIsWebEqualTo(String value) {
            addCriterion("IS_WEB =", value, "isWeb");
            return (Criteria) this;
        }

        public Criteria andIsWebNotEqualTo(String value) {
            addCriterion("IS_WEB <>", value, "isWeb");
            return (Criteria) this;
        }

        public Criteria andIsWebGreaterThan(String value) {
            addCriterion("IS_WEB >", value, "isWeb");
            return (Criteria) this;
        }

        public Criteria andIsWebGreaterThanOrEqualTo(String value) {
            addCriterion("IS_WEB >=", value, "isWeb");
            return (Criteria) this;
        }

        public Criteria andIsWebLessThan(String value) {
            addCriterion("IS_WEB <", value, "isWeb");
            return (Criteria) this;
        }

        public Criteria andIsWebLessThanOrEqualTo(String value) {
            addCriterion("IS_WEB <=", value, "isWeb");
            return (Criteria) this;
        }

        public Criteria andIsWebLike(String value) {
            addCriterion("IS_WEB like", value, "isWeb");
            return (Criteria) this;
        }

        public Criteria andIsWebNotLike(String value) {
            addCriterion("IS_WEB not like", value, "isWeb");
            return (Criteria) this;
        }

        public Criteria andIsWebIn(List<String> values) {
            addCriterion("IS_WEB in", values, "isWeb");
            return (Criteria) this;
        }

        public Criteria andIsWebNotIn(List<String> values) {
            addCriterion("IS_WEB not in", values, "isWeb");
            return (Criteria) this;
        }

        public Criteria andIsWebBetween(String value1, String value2) {
            addCriterion("IS_WEB between", value1, value2, "isWeb");
            return (Criteria) this;
        }

        public Criteria andIsWebNotBetween(String value1, String value2) {
            addCriterion("IS_WEB not between", value1, value2, "isWeb");
            return (Criteria) this;
        }

        public Criteria andIsOpenResultIsNull() {
            addCriterion("IS_OPEN_RESULT is null");
            return (Criteria) this;
        }

        public Criteria andIsOpenResultIsNotNull() {
            addCriterion("IS_OPEN_RESULT is not null");
            return (Criteria) this;
        }

        public Criteria andIsOpenResultEqualTo(String value) {
            addCriterion("IS_OPEN_RESULT =", value, "isOpenResult");
            return (Criteria) this;
        }

        public Criteria andIsOpenResultNotEqualTo(String value) {
            addCriterion("IS_OPEN_RESULT <>", value, "isOpenResult");
            return (Criteria) this;
        }

        public Criteria andIsOpenResultGreaterThan(String value) {
            addCriterion("IS_OPEN_RESULT >", value, "isOpenResult");
            return (Criteria) this;
        }

        public Criteria andIsOpenResultGreaterThanOrEqualTo(String value) {
            addCriterion("IS_OPEN_RESULT >=", value, "isOpenResult");
            return (Criteria) this;
        }

        public Criteria andIsOpenResultLessThan(String value) {
            addCriterion("IS_OPEN_RESULT <", value, "isOpenResult");
            return (Criteria) this;
        }

        public Criteria andIsOpenResultLessThanOrEqualTo(String value) {
            addCriterion("IS_OPEN_RESULT <=", value, "isOpenResult");
            return (Criteria) this;
        }

        public Criteria andIsOpenResultLike(String value) {
            addCriterion("IS_OPEN_RESULT like", value, "isOpenResult");
            return (Criteria) this;
        }

        public Criteria andIsOpenResultNotLike(String value) {
            addCriterion("IS_OPEN_RESULT not like", value, "isOpenResult");
            return (Criteria) this;
        }

        public Criteria andIsOpenResultIn(List<String> values) {
            addCriterion("IS_OPEN_RESULT in", values, "isOpenResult");
            return (Criteria) this;
        }

        public Criteria andIsOpenResultNotIn(List<String> values) {
            addCriterion("IS_OPEN_RESULT not in", values, "isOpenResult");
            return (Criteria) this;
        }

        public Criteria andIsOpenResultBetween(String value1, String value2) {
            addCriterion("IS_OPEN_RESULT between", value1, value2, "isOpenResult");
            return (Criteria) this;
        }

        public Criteria andIsOpenResultNotBetween(String value1, String value2) {
            addCriterion("IS_OPEN_RESULT not between", value1, value2, "isOpenResult");
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