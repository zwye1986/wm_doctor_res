package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResTeacherTrainingInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResTeacherTrainingInfoExample() {
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

        public Criteria andTrainingFlowIsNull() {
            addCriterion("TRAINING_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andTrainingFlowIsNotNull() {
            addCriterion("TRAINING_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingFlowEqualTo(String value) {
            addCriterion("TRAINING_FLOW =", value, "trainingFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingFlowNotEqualTo(String value) {
            addCriterion("TRAINING_FLOW <>", value, "trainingFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingFlowGreaterThan(String value) {
            addCriterion("TRAINING_FLOW >", value, "trainingFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingFlowGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_FLOW >=", value, "trainingFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingFlowLessThan(String value) {
            addCriterion("TRAINING_FLOW <", value, "trainingFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingFlowLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_FLOW <=", value, "trainingFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingFlowLike(String value) {
            addCriterion("TRAINING_FLOW like", value, "trainingFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingFlowNotLike(String value) {
            addCriterion("TRAINING_FLOW not like", value, "trainingFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingFlowIn(List<String> values) {
            addCriterion("TRAINING_FLOW in", values, "trainingFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingFlowNotIn(List<String> values) {
            addCriterion("TRAINING_FLOW not in", values, "trainingFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingFlowBetween(String value1, String value2) {
            addCriterion("TRAINING_FLOW between", value1, value2, "trainingFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingFlowNotBetween(String value1, String value2) {
            addCriterion("TRAINING_FLOW not between", value1, value2, "trainingFlow");
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

        public Criteria andTrainingYearIsNull() {
            addCriterion("TRAINING_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andTrainingYearIsNotNull() {
            addCriterion("TRAINING_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingYearEqualTo(String value) {
            addCriterion("TRAINING_YEAR =", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearNotEqualTo(String value) {
            addCriterion("TRAINING_YEAR <>", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearGreaterThan(String value) {
            addCriterion("TRAINING_YEAR >", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_YEAR >=", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearLessThan(String value) {
            addCriterion("TRAINING_YEAR <", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_YEAR <=", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearLike(String value) {
            addCriterion("TRAINING_YEAR like", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearNotLike(String value) {
            addCriterion("TRAINING_YEAR not like", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearIn(List<String> values) {
            addCriterion("TRAINING_YEAR in", values, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearNotIn(List<String> values) {
            addCriterion("TRAINING_YEAR not in", values, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearBetween(String value1, String value2) {
            addCriterion("TRAINING_YEAR between", value1, value2, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearNotBetween(String value1, String value2) {
            addCriterion("TRAINING_YEAR not between", value1, value2, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingUnitIsNull() {
            addCriterion("TRAINING_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andTrainingUnitIsNotNull() {
            addCriterion("TRAINING_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingUnitEqualTo(String value) {
            addCriterion("TRAINING_UNIT =", value, "trainingUnit");
            return (Criteria) this;
        }

        public Criteria andTrainingUnitNotEqualTo(String value) {
            addCriterion("TRAINING_UNIT <>", value, "trainingUnit");
            return (Criteria) this;
        }

        public Criteria andTrainingUnitGreaterThan(String value) {
            addCriterion("TRAINING_UNIT >", value, "trainingUnit");
            return (Criteria) this;
        }

        public Criteria andTrainingUnitGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_UNIT >=", value, "trainingUnit");
            return (Criteria) this;
        }

        public Criteria andTrainingUnitLessThan(String value) {
            addCriterion("TRAINING_UNIT <", value, "trainingUnit");
            return (Criteria) this;
        }

        public Criteria andTrainingUnitLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_UNIT <=", value, "trainingUnit");
            return (Criteria) this;
        }

        public Criteria andTrainingUnitLike(String value) {
            addCriterion("TRAINING_UNIT like", value, "trainingUnit");
            return (Criteria) this;
        }

        public Criteria andTrainingUnitNotLike(String value) {
            addCriterion("TRAINING_UNIT not like", value, "trainingUnit");
            return (Criteria) this;
        }

        public Criteria andTrainingUnitIn(List<String> values) {
            addCriterion("TRAINING_UNIT in", values, "trainingUnit");
            return (Criteria) this;
        }

        public Criteria andTrainingUnitNotIn(List<String> values) {
            addCriterion("TRAINING_UNIT not in", values, "trainingUnit");
            return (Criteria) this;
        }

        public Criteria andTrainingUnitBetween(String value1, String value2) {
            addCriterion("TRAINING_UNIT between", value1, value2, "trainingUnit");
            return (Criteria) this;
        }

        public Criteria andTrainingUnitNotBetween(String value1, String value2) {
            addCriterion("TRAINING_UNIT not between", value1, value2, "trainingUnit");
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

        public Criteria andCertificateLevelIdIsNull() {
            addCriterion("CERTIFICATE_LEVEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdIsNotNull() {
            addCriterion("CERTIFICATE_LEVEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdEqualTo(String value) {
            addCriterion("CERTIFICATE_LEVEL_ID =", value, "certificateLevelId");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdNotEqualTo(String value) {
            addCriterion("CERTIFICATE_LEVEL_ID <>", value, "certificateLevelId");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdGreaterThan(String value) {
            addCriterion("CERTIFICATE_LEVEL_ID >", value, "certificateLevelId");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_LEVEL_ID >=", value, "certificateLevelId");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdLessThan(String value) {
            addCriterion("CERTIFICATE_LEVEL_ID <", value, "certificateLevelId");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_LEVEL_ID <=", value, "certificateLevelId");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdLike(String value) {
            addCriterion("CERTIFICATE_LEVEL_ID like", value, "certificateLevelId");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdNotLike(String value) {
            addCriterion("CERTIFICATE_LEVEL_ID not like", value, "certificateLevelId");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdIn(List<String> values) {
            addCriterion("CERTIFICATE_LEVEL_ID in", values, "certificateLevelId");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdNotIn(List<String> values) {
            addCriterion("CERTIFICATE_LEVEL_ID not in", values, "certificateLevelId");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_LEVEL_ID between", value1, value2, "certificateLevelId");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_LEVEL_ID not between", value1, value2, "certificateLevelId");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameIsNull() {
            addCriterion("CERTIFICATE_LEVEL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameIsNotNull() {
            addCriterion("CERTIFICATE_LEVEL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameEqualTo(String value) {
            addCriterion("CERTIFICATE_LEVEL_NAME =", value, "certificateLevelName");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameNotEqualTo(String value) {
            addCriterion("CERTIFICATE_LEVEL_NAME <>", value, "certificateLevelName");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameGreaterThan(String value) {
            addCriterion("CERTIFICATE_LEVEL_NAME >", value, "certificateLevelName");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_LEVEL_NAME >=", value, "certificateLevelName");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameLessThan(String value) {
            addCriterion("CERTIFICATE_LEVEL_NAME <", value, "certificateLevelName");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_LEVEL_NAME <=", value, "certificateLevelName");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameLike(String value) {
            addCriterion("CERTIFICATE_LEVEL_NAME like", value, "certificateLevelName");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameNotLike(String value) {
            addCriterion("CERTIFICATE_LEVEL_NAME not like", value, "certificateLevelName");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameIn(List<String> values) {
            addCriterion("CERTIFICATE_LEVEL_NAME in", values, "certificateLevelName");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameNotIn(List<String> values) {
            addCriterion("CERTIFICATE_LEVEL_NAME not in", values, "certificateLevelName");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_LEVEL_NAME between", value1, value2, "certificateLevelName");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_LEVEL_NAME not between", value1, value2, "certificateLevelName");
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

        public Criteria andCertificateTimeIsNull() {
            addCriterion("CERTIFICATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeIsNotNull() {
            addCriterion("CERTIFICATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeEqualTo(String value) {
            addCriterion("CERTIFICATE_TIME =", value, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeNotEqualTo(String value) {
            addCriterion("CERTIFICATE_TIME <>", value, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeGreaterThan(String value) {
            addCriterion("CERTIFICATE_TIME >", value, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_TIME >=", value, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeLessThan(String value) {
            addCriterion("CERTIFICATE_TIME <", value, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_TIME <=", value, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeLike(String value) {
            addCriterion("CERTIFICATE_TIME like", value, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeNotLike(String value) {
            addCriterion("CERTIFICATE_TIME not like", value, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeIn(List<String> values) {
            addCriterion("CERTIFICATE_TIME in", values, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeNotIn(List<String> values) {
            addCriterion("CERTIFICATE_TIME not in", values, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_TIME between", value1, value2, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_TIME not between", value1, value2, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateFileIsNull() {
            addCriterion("CERTIFICATE_FILE is null");
            return (Criteria) this;
        }

        public Criteria andCertificateFileIsNotNull() {
            addCriterion("CERTIFICATE_FILE is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateFileEqualTo(String value) {
            addCriterion("CERTIFICATE_FILE =", value, "certificateFile");
            return (Criteria) this;
        }

        public Criteria andCertificateFileNotEqualTo(String value) {
            addCriterion("CERTIFICATE_FILE <>", value, "certificateFile");
            return (Criteria) this;
        }

        public Criteria andCertificateFileGreaterThan(String value) {
            addCriterion("CERTIFICATE_FILE >", value, "certificateFile");
            return (Criteria) this;
        }

        public Criteria andCertificateFileGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_FILE >=", value, "certificateFile");
            return (Criteria) this;
        }

        public Criteria andCertificateFileLessThan(String value) {
            addCriterion("CERTIFICATE_FILE <", value, "certificateFile");
            return (Criteria) this;
        }

        public Criteria andCertificateFileLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_FILE <=", value, "certificateFile");
            return (Criteria) this;
        }

        public Criteria andCertificateFileLike(String value) {
            addCriterion("CERTIFICATE_FILE like", value, "certificateFile");
            return (Criteria) this;
        }

        public Criteria andCertificateFileNotLike(String value) {
            addCriterion("CERTIFICATE_FILE not like", value, "certificateFile");
            return (Criteria) this;
        }

        public Criteria andCertificateFileIn(List<String> values) {
            addCriterion("CERTIFICATE_FILE in", values, "certificateFile");
            return (Criteria) this;
        }

        public Criteria andCertificateFileNotIn(List<String> values) {
            addCriterion("CERTIFICATE_FILE not in", values, "certificateFile");
            return (Criteria) this;
        }

        public Criteria andCertificateFileBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_FILE between", value1, value2, "certificateFile");
            return (Criteria) this;
        }

        public Criteria andCertificateFileNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_FILE not between", value1, value2, "certificateFile");
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