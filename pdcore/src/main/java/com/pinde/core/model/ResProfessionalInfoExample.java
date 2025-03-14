package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResProfessionalInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResProfessionalInfoExample() {
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

        public Criteria andProfessionalFlowIsNull() {
            addCriterion("PROFESSIONAL_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andProfessionalFlowIsNotNull() {
            addCriterion("PROFESSIONAL_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andProfessionalFlowEqualTo(String value) {
            addCriterion("PROFESSIONAL_FLOW =", value, "professionalFlow");
            return (Criteria) this;
        }

        public Criteria andProfessionalFlowNotEqualTo(String value) {
            addCriterion("PROFESSIONAL_FLOW <>", value, "professionalFlow");
            return (Criteria) this;
        }

        public Criteria andProfessionalFlowGreaterThan(String value) {
            addCriterion("PROFESSIONAL_FLOW >", value, "professionalFlow");
            return (Criteria) this;
        }

        public Criteria andProfessionalFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PROFESSIONAL_FLOW >=", value, "professionalFlow");
            return (Criteria) this;
        }

        public Criteria andProfessionalFlowLessThan(String value) {
            addCriterion("PROFESSIONAL_FLOW <", value, "professionalFlow");
            return (Criteria) this;
        }

        public Criteria andProfessionalFlowLessThanOrEqualTo(String value) {
            addCriterion("PROFESSIONAL_FLOW <=", value, "professionalFlow");
            return (Criteria) this;
        }

        public Criteria andProfessionalFlowLike(String value) {
            addCriterion("PROFESSIONAL_FLOW like", value, "professionalFlow");
            return (Criteria) this;
        }

        public Criteria andProfessionalFlowNotLike(String value) {
            addCriterion("PROFESSIONAL_FLOW not like", value, "professionalFlow");
            return (Criteria) this;
        }

        public Criteria andProfessionalFlowIn(List<String> values) {
            addCriterion("PROFESSIONAL_FLOW in", values, "professionalFlow");
            return (Criteria) this;
        }

        public Criteria andProfessionalFlowNotIn(List<String> values) {
            addCriterion("PROFESSIONAL_FLOW not in", values, "professionalFlow");
            return (Criteria) this;
        }

        public Criteria andProfessionalFlowBetween(String value1, String value2) {
            addCriterion("PROFESSIONAL_FLOW between", value1, value2, "professionalFlow");
            return (Criteria) this;
        }

        public Criteria andProfessionalFlowNotBetween(String value1, String value2) {
            addCriterion("PROFESSIONAL_FLOW not between", value1, value2, "professionalFlow");
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

        public Criteria andProfessionalTitleIdIsNull() {
            addCriterion("PROFESSIONAL_TITLE_ID is null");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleIdIsNotNull() {
            addCriterion("PROFESSIONAL_TITLE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleIdEqualTo(String value) {
            addCriterion("PROFESSIONAL_TITLE_ID =", value, "professionalTitleId");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleIdNotEqualTo(String value) {
            addCriterion("PROFESSIONAL_TITLE_ID <>", value, "professionalTitleId");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleIdGreaterThan(String value) {
            addCriterion("PROFESSIONAL_TITLE_ID >", value, "professionalTitleId");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROFESSIONAL_TITLE_ID >=", value, "professionalTitleId");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleIdLessThan(String value) {
            addCriterion("PROFESSIONAL_TITLE_ID <", value, "professionalTitleId");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleIdLessThanOrEqualTo(String value) {
            addCriterion("PROFESSIONAL_TITLE_ID <=", value, "professionalTitleId");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleIdLike(String value) {
            addCriterion("PROFESSIONAL_TITLE_ID like", value, "professionalTitleId");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleIdNotLike(String value) {
            addCriterion("PROFESSIONAL_TITLE_ID not like", value, "professionalTitleId");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleIdIn(List<String> values) {
            addCriterion("PROFESSIONAL_TITLE_ID in", values, "professionalTitleId");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleIdNotIn(List<String> values) {
            addCriterion("PROFESSIONAL_TITLE_ID not in", values, "professionalTitleId");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleIdBetween(String value1, String value2) {
            addCriterion("PROFESSIONAL_TITLE_ID between", value1, value2, "professionalTitleId");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleIdNotBetween(String value1, String value2) {
            addCriterion("PROFESSIONAL_TITLE_ID not between", value1, value2, "professionalTitleId");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleNameIsNull() {
            addCriterion("PROFESSIONAL_TITLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleNameIsNotNull() {
            addCriterion("PROFESSIONAL_TITLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleNameEqualTo(String value) {
            addCriterion("PROFESSIONAL_TITLE_NAME =", value, "professionalTitleName");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleNameNotEqualTo(String value) {
            addCriterion("PROFESSIONAL_TITLE_NAME <>", value, "professionalTitleName");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleNameGreaterThan(String value) {
            addCriterion("PROFESSIONAL_TITLE_NAME >", value, "professionalTitleName");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROFESSIONAL_TITLE_NAME >=", value, "professionalTitleName");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleNameLessThan(String value) {
            addCriterion("PROFESSIONAL_TITLE_NAME <", value, "professionalTitleName");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleNameLessThanOrEqualTo(String value) {
            addCriterion("PROFESSIONAL_TITLE_NAME <=", value, "professionalTitleName");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleNameLike(String value) {
            addCriterion("PROFESSIONAL_TITLE_NAME like", value, "professionalTitleName");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleNameNotLike(String value) {
            addCriterion("PROFESSIONAL_TITLE_NAME not like", value, "professionalTitleName");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleNameIn(List<String> values) {
            addCriterion("PROFESSIONAL_TITLE_NAME in", values, "professionalTitleName");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleNameNotIn(List<String> values) {
            addCriterion("PROFESSIONAL_TITLE_NAME not in", values, "professionalTitleName");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleNameBetween(String value1, String value2) {
            addCriterion("PROFESSIONAL_TITLE_NAME between", value1, value2, "professionalTitleName");
            return (Criteria) this;
        }

        public Criteria andProfessionalTitleNameNotBetween(String value1, String value2) {
            addCriterion("PROFESSIONAL_TITLE_NAME not between", value1, value2, "professionalTitleName");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionIdIsNull() {
            addCriterion("TECHNICAL_POSITION_ID is null");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionIdIsNotNull() {
            addCriterion("TECHNICAL_POSITION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionIdEqualTo(String value) {
            addCriterion("TECHNICAL_POSITION_ID =", value, "technicalPositionId");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionIdNotEqualTo(String value) {
            addCriterion("TECHNICAL_POSITION_ID <>", value, "technicalPositionId");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionIdGreaterThan(String value) {
            addCriterion("TECHNICAL_POSITION_ID >", value, "technicalPositionId");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionIdGreaterThanOrEqualTo(String value) {
            addCriterion("TECHNICAL_POSITION_ID >=", value, "technicalPositionId");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionIdLessThan(String value) {
            addCriterion("TECHNICAL_POSITION_ID <", value, "technicalPositionId");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionIdLessThanOrEqualTo(String value) {
            addCriterion("TECHNICAL_POSITION_ID <=", value, "technicalPositionId");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionIdLike(String value) {
            addCriterion("TECHNICAL_POSITION_ID like", value, "technicalPositionId");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionIdNotLike(String value) {
            addCriterion("TECHNICAL_POSITION_ID not like", value, "technicalPositionId");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionIdIn(List<String> values) {
            addCriterion("TECHNICAL_POSITION_ID in", values, "technicalPositionId");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionIdNotIn(List<String> values) {
            addCriterion("TECHNICAL_POSITION_ID not in", values, "technicalPositionId");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionIdBetween(String value1, String value2) {
            addCriterion("TECHNICAL_POSITION_ID between", value1, value2, "technicalPositionId");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionIdNotBetween(String value1, String value2) {
            addCriterion("TECHNICAL_POSITION_ID not between", value1, value2, "technicalPositionId");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionNameIsNull() {
            addCriterion("TECHNICAL_POSITION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionNameIsNotNull() {
            addCriterion("TECHNICAL_POSITION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionNameEqualTo(String value) {
            addCriterion("TECHNICAL_POSITION_NAME =", value, "technicalPositionName");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionNameNotEqualTo(String value) {
            addCriterion("TECHNICAL_POSITION_NAME <>", value, "technicalPositionName");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionNameGreaterThan(String value) {
            addCriterion("TECHNICAL_POSITION_NAME >", value, "technicalPositionName");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionNameGreaterThanOrEqualTo(String value) {
            addCriterion("TECHNICAL_POSITION_NAME >=", value, "technicalPositionName");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionNameLessThan(String value) {
            addCriterion("TECHNICAL_POSITION_NAME <", value, "technicalPositionName");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionNameLessThanOrEqualTo(String value) {
            addCriterion("TECHNICAL_POSITION_NAME <=", value, "technicalPositionName");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionNameLike(String value) {
            addCriterion("TECHNICAL_POSITION_NAME like", value, "technicalPositionName");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionNameNotLike(String value) {
            addCriterion("TECHNICAL_POSITION_NAME not like", value, "technicalPositionName");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionNameIn(List<String> values) {
            addCriterion("TECHNICAL_POSITION_NAME in", values, "technicalPositionName");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionNameNotIn(List<String> values) {
            addCriterion("TECHNICAL_POSITION_NAME not in", values, "technicalPositionName");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionNameBetween(String value1, String value2) {
            addCriterion("TECHNICAL_POSITION_NAME between", value1, value2, "technicalPositionName");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionNameNotBetween(String value1, String value2) {
            addCriterion("TECHNICAL_POSITION_NAME not between", value1, value2, "technicalPositionName");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionTimeIsNull() {
            addCriterion("TECHNICAL_POSITION_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionTimeIsNotNull() {
            addCriterion("TECHNICAL_POSITION_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionTimeEqualTo(String value) {
            addCriterion("TECHNICAL_POSITION_TIME =", value, "technicalPositionTime");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionTimeNotEqualTo(String value) {
            addCriterion("TECHNICAL_POSITION_TIME <>", value, "technicalPositionTime");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionTimeGreaterThan(String value) {
            addCriterion("TECHNICAL_POSITION_TIME >", value, "technicalPositionTime");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionTimeGreaterThanOrEqualTo(String value) {
            addCriterion("TECHNICAL_POSITION_TIME >=", value, "technicalPositionTime");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionTimeLessThan(String value) {
            addCriterion("TECHNICAL_POSITION_TIME <", value, "technicalPositionTime");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionTimeLessThanOrEqualTo(String value) {
            addCriterion("TECHNICAL_POSITION_TIME <=", value, "technicalPositionTime");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionTimeLike(String value) {
            addCriterion("TECHNICAL_POSITION_TIME like", value, "technicalPositionTime");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionTimeNotLike(String value) {
            addCriterion("TECHNICAL_POSITION_TIME not like", value, "technicalPositionTime");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionTimeIn(List<String> values) {
            addCriterion("TECHNICAL_POSITION_TIME in", values, "technicalPositionTime");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionTimeNotIn(List<String> values) {
            addCriterion("TECHNICAL_POSITION_TIME not in", values, "technicalPositionTime");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionTimeBetween(String value1, String value2) {
            addCriterion("TECHNICAL_POSITION_TIME between", value1, value2, "technicalPositionTime");
            return (Criteria) this;
        }

        public Criteria andTechnicalPositionTimeNotBetween(String value1, String value2) {
            addCriterion("TECHNICAL_POSITION_TIME not between", value1, value2, "technicalPositionTime");
            return (Criteria) this;
        }

        public Criteria andClinicalTeachingTimeIsNull() {
            addCriterion("CLINICAL_TEACHING_TIME is null");
            return (Criteria) this;
        }

        public Criteria andClinicalTeachingTimeIsNotNull() {
            addCriterion("CLINICAL_TEACHING_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andClinicalTeachingTimeEqualTo(String value) {
            addCriterion("CLINICAL_TEACHING_TIME =", value, "clinicalTeachingTime");
            return (Criteria) this;
        }

        public Criteria andClinicalTeachingTimeNotEqualTo(String value) {
            addCriterion("CLINICAL_TEACHING_TIME <>", value, "clinicalTeachingTime");
            return (Criteria) this;
        }

        public Criteria andClinicalTeachingTimeGreaterThan(String value) {
            addCriterion("CLINICAL_TEACHING_TIME >", value, "clinicalTeachingTime");
            return (Criteria) this;
        }

        public Criteria andClinicalTeachingTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CLINICAL_TEACHING_TIME >=", value, "clinicalTeachingTime");
            return (Criteria) this;
        }

        public Criteria andClinicalTeachingTimeLessThan(String value) {
            addCriterion("CLINICAL_TEACHING_TIME <", value, "clinicalTeachingTime");
            return (Criteria) this;
        }

        public Criteria andClinicalTeachingTimeLessThanOrEqualTo(String value) {
            addCriterion("CLINICAL_TEACHING_TIME <=", value, "clinicalTeachingTime");
            return (Criteria) this;
        }

        public Criteria andClinicalTeachingTimeLike(String value) {
            addCriterion("CLINICAL_TEACHING_TIME like", value, "clinicalTeachingTime");
            return (Criteria) this;
        }

        public Criteria andClinicalTeachingTimeNotLike(String value) {
            addCriterion("CLINICAL_TEACHING_TIME not like", value, "clinicalTeachingTime");
            return (Criteria) this;
        }

        public Criteria andClinicalTeachingTimeIn(List<String> values) {
            addCriterion("CLINICAL_TEACHING_TIME in", values, "clinicalTeachingTime");
            return (Criteria) this;
        }

        public Criteria andClinicalTeachingTimeNotIn(List<String> values) {
            addCriterion("CLINICAL_TEACHING_TIME not in", values, "clinicalTeachingTime");
            return (Criteria) this;
        }

        public Criteria andClinicalTeachingTimeBetween(String value1, String value2) {
            addCriterion("CLINICAL_TEACHING_TIME between", value1, value2, "clinicalTeachingTime");
            return (Criteria) this;
        }

        public Criteria andClinicalTeachingTimeNotBetween(String value1, String value2) {
            addCriterion("CLINICAL_TEACHING_TIME not between", value1, value2, "clinicalTeachingTime");
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