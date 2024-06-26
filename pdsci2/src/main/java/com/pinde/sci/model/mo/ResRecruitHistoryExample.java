package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ResRecruitHistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResRecruitHistoryExample() {
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

        public Criteria andHistoryFlowIsNull() {
            addCriterion("HISTORY_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andHistoryFlowIsNotNull() {
            addCriterion("HISTORY_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andHistoryFlowEqualTo(String value) {
            addCriterion("HISTORY_FLOW =", value, "historyFlow");
            return (Criteria) this;
        }

        public Criteria andHistoryFlowNotEqualTo(String value) {
            addCriterion("HISTORY_FLOW <>", value, "historyFlow");
            return (Criteria) this;
        }

        public Criteria andHistoryFlowGreaterThan(String value) {
            addCriterion("HISTORY_FLOW >", value, "historyFlow");
            return (Criteria) this;
        }

        public Criteria andHistoryFlowGreaterThanOrEqualTo(String value) {
            addCriterion("HISTORY_FLOW >=", value, "historyFlow");
            return (Criteria) this;
        }

        public Criteria andHistoryFlowLessThan(String value) {
            addCriterion("HISTORY_FLOW <", value, "historyFlow");
            return (Criteria) this;
        }

        public Criteria andHistoryFlowLessThanOrEqualTo(String value) {
            addCriterion("HISTORY_FLOW <=", value, "historyFlow");
            return (Criteria) this;
        }

        public Criteria andHistoryFlowLike(String value) {
            addCriterion("HISTORY_FLOW like", value, "historyFlow");
            return (Criteria) this;
        }

        public Criteria andHistoryFlowNotLike(String value) {
            addCriterion("HISTORY_FLOW not like", value, "historyFlow");
            return (Criteria) this;
        }

        public Criteria andHistoryFlowIn(List<String> values) {
            addCriterion("HISTORY_FLOW in", values, "historyFlow");
            return (Criteria) this;
        }

        public Criteria andHistoryFlowNotIn(List<String> values) {
            addCriterion("HISTORY_FLOW not in", values, "historyFlow");
            return (Criteria) this;
        }

        public Criteria andHistoryFlowBetween(String value1, String value2) {
            addCriterion("HISTORY_FLOW between", value1, value2, "historyFlow");
            return (Criteria) this;
        }

        public Criteria andHistoryFlowNotBetween(String value1, String value2) {
            addCriterion("HISTORY_FLOW not between", value1, value2, "historyFlow");
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

        public Criteria andCompanyNumberIsNull() {
            addCriterion("COMPANY_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNumberIsNotNull() {
            addCriterion("COMPANY_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNumberEqualTo(String value) {
            addCriterion("COMPANY_NUMBER =", value, "companyNumber");
            return (Criteria) this;
        }

        public Criteria andCompanyNumberNotEqualTo(String value) {
            addCriterion("COMPANY_NUMBER <>", value, "companyNumber");
            return (Criteria) this;
        }

        public Criteria andCompanyNumberGreaterThan(String value) {
            addCriterion("COMPANY_NUMBER >", value, "companyNumber");
            return (Criteria) this;
        }

        public Criteria andCompanyNumberGreaterThanOrEqualTo(String value) {
            addCriterion("COMPANY_NUMBER >=", value, "companyNumber");
            return (Criteria) this;
        }

        public Criteria andCompanyNumberLessThan(String value) {
            addCriterion("COMPANY_NUMBER <", value, "companyNumber");
            return (Criteria) this;
        }

        public Criteria andCompanyNumberLessThanOrEqualTo(String value) {
            addCriterion("COMPANY_NUMBER <=", value, "companyNumber");
            return (Criteria) this;
        }

        public Criteria andCompanyNumberLike(String value) {
            addCriterion("COMPANY_NUMBER like", value, "companyNumber");
            return (Criteria) this;
        }

        public Criteria andCompanyNumberNotLike(String value) {
            addCriterion("COMPANY_NUMBER not like", value, "companyNumber");
            return (Criteria) this;
        }

        public Criteria andCompanyNumberIn(List<String> values) {
            addCriterion("COMPANY_NUMBER in", values, "companyNumber");
            return (Criteria) this;
        }

        public Criteria andCompanyNumberNotIn(List<String> values) {
            addCriterion("COMPANY_NUMBER not in", values, "companyNumber");
            return (Criteria) this;
        }

        public Criteria andCompanyNumberBetween(String value1, String value2) {
            addCriterion("COMPANY_NUMBER between", value1, value2, "companyNumber");
            return (Criteria) this;
        }

        public Criteria andCompanyNumberNotBetween(String value1, String value2) {
            addCriterion("COMPANY_NUMBER not between", value1, value2, "companyNumber");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberIsNull() {
            addCriterion("GRADUATE_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberIsNotNull() {
            addCriterion("GRADUATE_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberEqualTo(String value) {
            addCriterion("GRADUATE_NUMBER =", value, "graduateNumber");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberNotEqualTo(String value) {
            addCriterion("GRADUATE_NUMBER <>", value, "graduateNumber");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberGreaterThan(String value) {
            addCriterion("GRADUATE_NUMBER >", value, "graduateNumber");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATE_NUMBER >=", value, "graduateNumber");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberLessThan(String value) {
            addCriterion("GRADUATE_NUMBER <", value, "graduateNumber");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberLessThanOrEqualTo(String value) {
            addCriterion("GRADUATE_NUMBER <=", value, "graduateNumber");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberLike(String value) {
            addCriterion("GRADUATE_NUMBER like", value, "graduateNumber");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberNotLike(String value) {
            addCriterion("GRADUATE_NUMBER not like", value, "graduateNumber");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberIn(List<String> values) {
            addCriterion("GRADUATE_NUMBER in", values, "graduateNumber");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberNotIn(List<String> values) {
            addCriterion("GRADUATE_NUMBER not in", values, "graduateNumber");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberBetween(String value1, String value2) {
            addCriterion("GRADUATE_NUMBER between", value1, value2, "graduateNumber");
            return (Criteria) this;
        }

        public Criteria andGraduateNumberNotBetween(String value1, String value2) {
            addCriterion("GRADUATE_NUMBER not between", value1, value2, "graduateNumber");
            return (Criteria) this;
        }

        public Criteria andRecruitNumberIsNull() {
            addCriterion("RECRUIT_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andRecruitNumberIsNotNull() {
            addCriterion("RECRUIT_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitNumberEqualTo(String value) {
            addCriterion("RECRUIT_NUMBER =", value, "recruitNumber");
            return (Criteria) this;
        }

        public Criteria andRecruitNumberNotEqualTo(String value) {
            addCriterion("RECRUIT_NUMBER <>", value, "recruitNumber");
            return (Criteria) this;
        }

        public Criteria andRecruitNumberGreaterThan(String value) {
            addCriterion("RECRUIT_NUMBER >", value, "recruitNumber");
            return (Criteria) this;
        }

        public Criteria andRecruitNumberGreaterThanOrEqualTo(String value) {
            addCriterion("RECRUIT_NUMBER >=", value, "recruitNumber");
            return (Criteria) this;
        }

        public Criteria andRecruitNumberLessThan(String value) {
            addCriterion("RECRUIT_NUMBER <", value, "recruitNumber");
            return (Criteria) this;
        }

        public Criteria andRecruitNumberLessThanOrEqualTo(String value) {
            addCriterion("RECRUIT_NUMBER <=", value, "recruitNumber");
            return (Criteria) this;
        }

        public Criteria andRecruitNumberLike(String value) {
            addCriterion("RECRUIT_NUMBER like", value, "recruitNumber");
            return (Criteria) this;
        }

        public Criteria andRecruitNumberNotLike(String value) {
            addCriterion("RECRUIT_NUMBER not like", value, "recruitNumber");
            return (Criteria) this;
        }

        public Criteria andRecruitNumberIn(List<String> values) {
            addCriterion("RECRUIT_NUMBER in", values, "recruitNumber");
            return (Criteria) this;
        }

        public Criteria andRecruitNumberNotIn(List<String> values) {
            addCriterion("RECRUIT_NUMBER not in", values, "recruitNumber");
            return (Criteria) this;
        }

        public Criteria andRecruitNumberBetween(String value1, String value2) {
            addCriterion("RECRUIT_NUMBER between", value1, value2, "recruitNumber");
            return (Criteria) this;
        }

        public Criteria andRecruitNumberNotBetween(String value1, String value2) {
            addCriterion("RECRUIT_NUMBER not between", value1, value2, "recruitNumber");
            return (Criteria) this;
        }

        public Criteria andPhotoTimeIsNull() {
            addCriterion("PHOTO_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPhotoTimeIsNotNull() {
            addCriterion("PHOTO_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPhotoTimeEqualTo(String value) {
            addCriterion("PHOTO_TIME =", value, "photoTime");
            return (Criteria) this;
        }

        public Criteria andPhotoTimeNotEqualTo(String value) {
            addCriterion("PHOTO_TIME <>", value, "photoTime");
            return (Criteria) this;
        }

        public Criteria andPhotoTimeGreaterThan(String value) {
            addCriterion("PHOTO_TIME >", value, "photoTime");
            return (Criteria) this;
        }

        public Criteria andPhotoTimeGreaterThanOrEqualTo(String value) {
            addCriterion("PHOTO_TIME >=", value, "photoTime");
            return (Criteria) this;
        }

        public Criteria andPhotoTimeLessThan(String value) {
            addCriterion("PHOTO_TIME <", value, "photoTime");
            return (Criteria) this;
        }

        public Criteria andPhotoTimeLessThanOrEqualTo(String value) {
            addCriterion("PHOTO_TIME <=", value, "photoTime");
            return (Criteria) this;
        }

        public Criteria andPhotoTimeLike(String value) {
            addCriterion("PHOTO_TIME like", value, "photoTime");
            return (Criteria) this;
        }

        public Criteria andPhotoTimeNotLike(String value) {
            addCriterion("PHOTO_TIME not like", value, "photoTime");
            return (Criteria) this;
        }

        public Criteria andPhotoTimeIn(List<String> values) {
            addCriterion("PHOTO_TIME in", values, "photoTime");
            return (Criteria) this;
        }

        public Criteria andPhotoTimeNotIn(List<String> values) {
            addCriterion("PHOTO_TIME not in", values, "photoTime");
            return (Criteria) this;
        }

        public Criteria andPhotoTimeBetween(String value1, String value2) {
            addCriterion("PHOTO_TIME between", value1, value2, "photoTime");
            return (Criteria) this;
        }

        public Criteria andPhotoTimeNotBetween(String value1, String value2) {
            addCriterion("PHOTO_TIME not between", value1, value2, "photoTime");
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

        public Criteria andCompanyEntrustNumberIsNull() {
            addCriterion("COMPANY_ENTRUST_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andCompanyEntrustNumberIsNotNull() {
            addCriterion("COMPANY_ENTRUST_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEntrustNumberEqualTo(String value) {
            addCriterion("COMPANY_ENTRUST_NUMBER =", value, "companyEntrustNumber");
            return (Criteria) this;
        }

        public Criteria andCompanyEntrustNumberNotEqualTo(String value) {
            addCriterion("COMPANY_ENTRUST_NUMBER <>", value, "companyEntrustNumber");
            return (Criteria) this;
        }

        public Criteria andCompanyEntrustNumberGreaterThan(String value) {
            addCriterion("COMPANY_ENTRUST_NUMBER >", value, "companyEntrustNumber");
            return (Criteria) this;
        }

        public Criteria andCompanyEntrustNumberGreaterThanOrEqualTo(String value) {
            addCriterion("COMPANY_ENTRUST_NUMBER >=", value, "companyEntrustNumber");
            return (Criteria) this;
        }

        public Criteria andCompanyEntrustNumberLessThan(String value) {
            addCriterion("COMPANY_ENTRUST_NUMBER <", value, "companyEntrustNumber");
            return (Criteria) this;
        }

        public Criteria andCompanyEntrustNumberLessThanOrEqualTo(String value) {
            addCriterion("COMPANY_ENTRUST_NUMBER <=", value, "companyEntrustNumber");
            return (Criteria) this;
        }

        public Criteria andCompanyEntrustNumberLike(String value) {
            addCriterion("COMPANY_ENTRUST_NUMBER like", value, "companyEntrustNumber");
            return (Criteria) this;
        }

        public Criteria andCompanyEntrustNumberNotLike(String value) {
            addCriterion("COMPANY_ENTRUST_NUMBER not like", value, "companyEntrustNumber");
            return (Criteria) this;
        }

        public Criteria andCompanyEntrustNumberIn(List<String> values) {
            addCriterion("COMPANY_ENTRUST_NUMBER in", values, "companyEntrustNumber");
            return (Criteria) this;
        }

        public Criteria andCompanyEntrustNumberNotIn(List<String> values) {
            addCriterion("COMPANY_ENTRUST_NUMBER not in", values, "companyEntrustNumber");
            return (Criteria) this;
        }

        public Criteria andCompanyEntrustNumberBetween(String value1, String value2) {
            addCriterion("COMPANY_ENTRUST_NUMBER between", value1, value2, "companyEntrustNumber");
            return (Criteria) this;
        }

        public Criteria andCompanyEntrustNumberNotBetween(String value1, String value2) {
            addCriterion("COMPANY_ENTRUST_NUMBER not between", value1, value2, "companyEntrustNumber");
            return (Criteria) this;
        }

        public Criteria andSocialNumberIsNull() {
            addCriterion("SOCIAL_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andSocialNumberIsNotNull() {
            addCriterion("SOCIAL_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andSocialNumberEqualTo(String value) {
            addCriterion("SOCIAL_NUMBER =", value, "socialNumber");
            return (Criteria) this;
        }

        public Criteria andSocialNumberNotEqualTo(String value) {
            addCriterion("SOCIAL_NUMBER <>", value, "socialNumber");
            return (Criteria) this;
        }

        public Criteria andSocialNumberGreaterThan(String value) {
            addCriterion("SOCIAL_NUMBER >", value, "socialNumber");
            return (Criteria) this;
        }

        public Criteria andSocialNumberGreaterThanOrEqualTo(String value) {
            addCriterion("SOCIAL_NUMBER >=", value, "socialNumber");
            return (Criteria) this;
        }

        public Criteria andSocialNumberLessThan(String value) {
            addCriterion("SOCIAL_NUMBER <", value, "socialNumber");
            return (Criteria) this;
        }

        public Criteria andSocialNumberLessThanOrEqualTo(String value) {
            addCriterion("SOCIAL_NUMBER <=", value, "socialNumber");
            return (Criteria) this;
        }

        public Criteria andSocialNumberLike(String value) {
            addCriterion("SOCIAL_NUMBER like", value, "socialNumber");
            return (Criteria) this;
        }

        public Criteria andSocialNumberNotLike(String value) {
            addCriterion("SOCIAL_NUMBER not like", value, "socialNumber");
            return (Criteria) this;
        }

        public Criteria andSocialNumberIn(List<String> values) {
            addCriterion("SOCIAL_NUMBER in", values, "socialNumber");
            return (Criteria) this;
        }

        public Criteria andSocialNumberNotIn(List<String> values) {
            addCriterion("SOCIAL_NUMBER not in", values, "socialNumber");
            return (Criteria) this;
        }

        public Criteria andSocialNumberBetween(String value1, String value2) {
            addCriterion("SOCIAL_NUMBER between", value1, value2, "socialNumber");
            return (Criteria) this;
        }

        public Criteria andSocialNumberNotBetween(String value1, String value2) {
            addCriterion("SOCIAL_NUMBER not between", value1, value2, "socialNumber");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("REMARKS is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("REMARKS is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("REMARKS =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("REMARKS <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("REMARKS >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("REMARKS >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("REMARKS <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("REMARKS <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("REMARKS like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("REMARKS not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("REMARKS in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("REMARKS not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("REMARKS between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("REMARKS not between", value1, value2, "remarks");
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