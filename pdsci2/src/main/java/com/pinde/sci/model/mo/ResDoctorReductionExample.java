package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ResDoctorReductionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResDoctorReductionExample() {
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

        public Criteria andRecruitFlowIsNull() {
            addCriterion("RECRUIT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowIsNotNull() {
            addCriterion("RECRUIT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowEqualTo(String value) {
            addCriterion("RECRUIT_FLOW =", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotEqualTo(String value) {
            addCriterion("RECRUIT_FLOW <>", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowGreaterThan(String value) {
            addCriterion("RECRUIT_FLOW >", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RECRUIT_FLOW >=", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowLessThan(String value) {
            addCriterion("RECRUIT_FLOW <", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowLessThanOrEqualTo(String value) {
            addCriterion("RECRUIT_FLOW <=", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowLike(String value) {
            addCriterion("RECRUIT_FLOW like", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotLike(String value) {
            addCriterion("RECRUIT_FLOW not like", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowIn(List<String> values) {
            addCriterion("RECRUIT_FLOW in", values, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotIn(List<String> values) {
            addCriterion("RECRUIT_FLOW not in", values, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowBetween(String value1, String value2) {
            addCriterion("RECRUIT_FLOW between", value1, value2, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotBetween(String value1, String value2) {
            addCriterion("RECRUIT_FLOW not between", value1, value2, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andDefaultTrainYearIsNull() {
            addCriterion("DEFAULT_TRAIN_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andDefaultTrainYearIsNotNull() {
            addCriterion("DEFAULT_TRAIN_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andDefaultTrainYearEqualTo(String value) {
            addCriterion("DEFAULT_TRAIN_YEAR =", value, "defaultTrainYear");
            return (Criteria) this;
        }

        public Criteria andDefaultTrainYearNotEqualTo(String value) {
            addCriterion("DEFAULT_TRAIN_YEAR <>", value, "defaultTrainYear");
            return (Criteria) this;
        }

        public Criteria andDefaultTrainYearGreaterThan(String value) {
            addCriterion("DEFAULT_TRAIN_YEAR >", value, "defaultTrainYear");
            return (Criteria) this;
        }

        public Criteria andDefaultTrainYearGreaterThanOrEqualTo(String value) {
            addCriterion("DEFAULT_TRAIN_YEAR >=", value, "defaultTrainYear");
            return (Criteria) this;
        }

        public Criteria andDefaultTrainYearLessThan(String value) {
            addCriterion("DEFAULT_TRAIN_YEAR <", value, "defaultTrainYear");
            return (Criteria) this;
        }

        public Criteria andDefaultTrainYearLessThanOrEqualTo(String value) {
            addCriterion("DEFAULT_TRAIN_YEAR <=", value, "defaultTrainYear");
            return (Criteria) this;
        }

        public Criteria andDefaultTrainYearLike(String value) {
            addCriterion("DEFAULT_TRAIN_YEAR like", value, "defaultTrainYear");
            return (Criteria) this;
        }

        public Criteria andDefaultTrainYearNotLike(String value) {
            addCriterion("DEFAULT_TRAIN_YEAR not like", value, "defaultTrainYear");
            return (Criteria) this;
        }

        public Criteria andDefaultTrainYearIn(List<String> values) {
            addCriterion("DEFAULT_TRAIN_YEAR in", values, "defaultTrainYear");
            return (Criteria) this;
        }

        public Criteria andDefaultTrainYearNotIn(List<String> values) {
            addCriterion("DEFAULT_TRAIN_YEAR not in", values, "defaultTrainYear");
            return (Criteria) this;
        }

        public Criteria andDefaultTrainYearBetween(String value1, String value2) {
            addCriterion("DEFAULT_TRAIN_YEAR between", value1, value2, "defaultTrainYear");
            return (Criteria) this;
        }

        public Criteria andDefaultTrainYearNotBetween(String value1, String value2) {
            addCriterion("DEFAULT_TRAIN_YEAR not between", value1, value2, "defaultTrainYear");
            return (Criteria) this;
        }

        public Criteria andReduceYearIsNull() {
            addCriterion("REDUCE_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andReduceYearIsNotNull() {
            addCriterion("REDUCE_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andReduceYearEqualTo(String value) {
            addCriterion("REDUCE_YEAR =", value, "reduceYear");
            return (Criteria) this;
        }

        public Criteria andReduceYearNotEqualTo(String value) {
            addCriterion("REDUCE_YEAR <>", value, "reduceYear");
            return (Criteria) this;
        }

        public Criteria andReduceYearGreaterThan(String value) {
            addCriterion("REDUCE_YEAR >", value, "reduceYear");
            return (Criteria) this;
        }

        public Criteria andReduceYearGreaterThanOrEqualTo(String value) {
            addCriterion("REDUCE_YEAR >=", value, "reduceYear");
            return (Criteria) this;
        }

        public Criteria andReduceYearLessThan(String value) {
            addCriterion("REDUCE_YEAR <", value, "reduceYear");
            return (Criteria) this;
        }

        public Criteria andReduceYearLessThanOrEqualTo(String value) {
            addCriterion("REDUCE_YEAR <=", value, "reduceYear");
            return (Criteria) this;
        }

        public Criteria andReduceYearLike(String value) {
            addCriterion("REDUCE_YEAR like", value, "reduceYear");
            return (Criteria) this;
        }

        public Criteria andReduceYearNotLike(String value) {
            addCriterion("REDUCE_YEAR not like", value, "reduceYear");
            return (Criteria) this;
        }

        public Criteria andReduceYearIn(List<String> values) {
            addCriterion("REDUCE_YEAR in", values, "reduceYear");
            return (Criteria) this;
        }

        public Criteria andReduceYearNotIn(List<String> values) {
            addCriterion("REDUCE_YEAR not in", values, "reduceYear");
            return (Criteria) this;
        }

        public Criteria andReduceYearBetween(String value1, String value2) {
            addCriterion("REDUCE_YEAR between", value1, value2, "reduceYear");
            return (Criteria) this;
        }

        public Criteria andReduceYearNotBetween(String value1, String value2) {
            addCriterion("REDUCE_YEAR not between", value1, value2, "reduceYear");
            return (Criteria) this;
        }

        public Criteria andAfterReduceTrainYearIsNull() {
            addCriterion("AFTER_REDUCE_TRAIN_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andAfterReduceTrainYearIsNotNull() {
            addCriterion("AFTER_REDUCE_TRAIN_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andAfterReduceTrainYearEqualTo(String value) {
            addCriterion("AFTER_REDUCE_TRAIN_YEAR =", value, "afterReduceTrainYear");
            return (Criteria) this;
        }

        public Criteria andAfterReduceTrainYearNotEqualTo(String value) {
            addCriterion("AFTER_REDUCE_TRAIN_YEAR <>", value, "afterReduceTrainYear");
            return (Criteria) this;
        }

        public Criteria andAfterReduceTrainYearGreaterThan(String value) {
            addCriterion("AFTER_REDUCE_TRAIN_YEAR >", value, "afterReduceTrainYear");
            return (Criteria) this;
        }

        public Criteria andAfterReduceTrainYearGreaterThanOrEqualTo(String value) {
            addCriterion("AFTER_REDUCE_TRAIN_YEAR >=", value, "afterReduceTrainYear");
            return (Criteria) this;
        }

        public Criteria andAfterReduceTrainYearLessThan(String value) {
            addCriterion("AFTER_REDUCE_TRAIN_YEAR <", value, "afterReduceTrainYear");
            return (Criteria) this;
        }

        public Criteria andAfterReduceTrainYearLessThanOrEqualTo(String value) {
            addCriterion("AFTER_REDUCE_TRAIN_YEAR <=", value, "afterReduceTrainYear");
            return (Criteria) this;
        }

        public Criteria andAfterReduceTrainYearLike(String value) {
            addCriterion("AFTER_REDUCE_TRAIN_YEAR like", value, "afterReduceTrainYear");
            return (Criteria) this;
        }

        public Criteria andAfterReduceTrainYearNotLike(String value) {
            addCriterion("AFTER_REDUCE_TRAIN_YEAR not like", value, "afterReduceTrainYear");
            return (Criteria) this;
        }

        public Criteria andAfterReduceTrainYearIn(List<String> values) {
            addCriterion("AFTER_REDUCE_TRAIN_YEAR in", values, "afterReduceTrainYear");
            return (Criteria) this;
        }

        public Criteria andAfterReduceTrainYearNotIn(List<String> values) {
            addCriterion("AFTER_REDUCE_TRAIN_YEAR not in", values, "afterReduceTrainYear");
            return (Criteria) this;
        }

        public Criteria andAfterReduceTrainYearBetween(String value1, String value2) {
            addCriterion("AFTER_REDUCE_TRAIN_YEAR between", value1, value2, "afterReduceTrainYear");
            return (Criteria) this;
        }

        public Criteria andAfterReduceTrainYearNotBetween(String value1, String value2) {
            addCriterion("AFTER_REDUCE_TRAIN_YEAR not between", value1, value2, "afterReduceTrainYear");
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

        public Criteria andLocalAuditOpinionIsNull() {
            addCriterion("LOCAL_AUDIT_OPINION is null");
            return (Criteria) this;
        }

        public Criteria andLocalAuditOpinionIsNotNull() {
            addCriterion("LOCAL_AUDIT_OPINION is not null");
            return (Criteria) this;
        }

        public Criteria andLocalAuditOpinionEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_OPINION =", value, "localAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andLocalAuditOpinionNotEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_OPINION <>", value, "localAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andLocalAuditOpinionGreaterThan(String value) {
            addCriterion("LOCAL_AUDIT_OPINION >", value, "localAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andLocalAuditOpinionGreaterThanOrEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_OPINION >=", value, "localAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andLocalAuditOpinionLessThan(String value) {
            addCriterion("LOCAL_AUDIT_OPINION <", value, "localAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andLocalAuditOpinionLessThanOrEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_OPINION <=", value, "localAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andLocalAuditOpinionLike(String value) {
            addCriterion("LOCAL_AUDIT_OPINION like", value, "localAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andLocalAuditOpinionNotLike(String value) {
            addCriterion("LOCAL_AUDIT_OPINION not like", value, "localAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andLocalAuditOpinionIn(List<String> values) {
            addCriterion("LOCAL_AUDIT_OPINION in", values, "localAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andLocalAuditOpinionNotIn(List<String> values) {
            addCriterion("LOCAL_AUDIT_OPINION not in", values, "localAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andLocalAuditOpinionBetween(String value1, String value2) {
            addCriterion("LOCAL_AUDIT_OPINION between", value1, value2, "localAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andLocalAuditOpinionNotBetween(String value1, String value2) {
            addCriterion("LOCAL_AUDIT_OPINION not between", value1, value2, "localAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andLocalAuditTimeIsNull() {
            addCriterion("LOCAL_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLocalAuditTimeIsNotNull() {
            addCriterion("LOCAL_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLocalAuditTimeEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_TIME =", value, "localAuditTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditTimeNotEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_TIME <>", value, "localAuditTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditTimeGreaterThan(String value) {
            addCriterion("LOCAL_AUDIT_TIME >", value, "localAuditTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_TIME >=", value, "localAuditTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditTimeLessThan(String value) {
            addCriterion("LOCAL_AUDIT_TIME <", value, "localAuditTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_TIME <=", value, "localAuditTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditTimeLike(String value) {
            addCriterion("LOCAL_AUDIT_TIME like", value, "localAuditTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditTimeNotLike(String value) {
            addCriterion("LOCAL_AUDIT_TIME not like", value, "localAuditTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditTimeIn(List<String> values) {
            addCriterion("LOCAL_AUDIT_TIME in", values, "localAuditTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditTimeNotIn(List<String> values) {
            addCriterion("LOCAL_AUDIT_TIME not in", values, "localAuditTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditTimeBetween(String value1, String value2) {
            addCriterion("LOCAL_AUDIT_TIME between", value1, value2, "localAuditTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditTimeNotBetween(String value1, String value2) {
            addCriterion("LOCAL_AUDIT_TIME not between", value1, value2, "localAuditTime");
            return (Criteria) this;
        }

        public Criteria andLocalAuditUserFlowIsNull() {
            addCriterion("LOCAL_AUDIT_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andLocalAuditUserFlowIsNotNull() {
            addCriterion("LOCAL_AUDIT_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andLocalAuditUserFlowEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_USER_FLOW =", value, "localAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andLocalAuditUserFlowNotEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_USER_FLOW <>", value, "localAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andLocalAuditUserFlowGreaterThan(String value) {
            addCriterion("LOCAL_AUDIT_USER_FLOW >", value, "localAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andLocalAuditUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_USER_FLOW >=", value, "localAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andLocalAuditUserFlowLessThan(String value) {
            addCriterion("LOCAL_AUDIT_USER_FLOW <", value, "localAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andLocalAuditUserFlowLessThanOrEqualTo(String value) {
            addCriterion("LOCAL_AUDIT_USER_FLOW <=", value, "localAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andLocalAuditUserFlowLike(String value) {
            addCriterion("LOCAL_AUDIT_USER_FLOW like", value, "localAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andLocalAuditUserFlowNotLike(String value) {
            addCriterion("LOCAL_AUDIT_USER_FLOW not like", value, "localAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andLocalAuditUserFlowIn(List<String> values) {
            addCriterion("LOCAL_AUDIT_USER_FLOW in", values, "localAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andLocalAuditUserFlowNotIn(List<String> values) {
            addCriterion("LOCAL_AUDIT_USER_FLOW not in", values, "localAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andLocalAuditUserFlowBetween(String value1, String value2) {
            addCriterion("LOCAL_AUDIT_USER_FLOW between", value1, value2, "localAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andLocalAuditUserFlowNotBetween(String value1, String value2) {
            addCriterion("LOCAL_AUDIT_USER_FLOW not between", value1, value2, "localAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditOpinionIsNull() {
            addCriterion("GLOBAL_AUDIT_OPINION is null");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditOpinionIsNotNull() {
            addCriterion("GLOBAL_AUDIT_OPINION is not null");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditOpinionEqualTo(String value) {
            addCriterion("GLOBAL_AUDIT_OPINION =", value, "globalAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditOpinionNotEqualTo(String value) {
            addCriterion("GLOBAL_AUDIT_OPINION <>", value, "globalAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditOpinionGreaterThan(String value) {
            addCriterion("GLOBAL_AUDIT_OPINION >", value, "globalAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditOpinionGreaterThanOrEqualTo(String value) {
            addCriterion("GLOBAL_AUDIT_OPINION >=", value, "globalAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditOpinionLessThan(String value) {
            addCriterion("GLOBAL_AUDIT_OPINION <", value, "globalAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditOpinionLessThanOrEqualTo(String value) {
            addCriterion("GLOBAL_AUDIT_OPINION <=", value, "globalAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditOpinionLike(String value) {
            addCriterion("GLOBAL_AUDIT_OPINION like", value, "globalAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditOpinionNotLike(String value) {
            addCriterion("GLOBAL_AUDIT_OPINION not like", value, "globalAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditOpinionIn(List<String> values) {
            addCriterion("GLOBAL_AUDIT_OPINION in", values, "globalAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditOpinionNotIn(List<String> values) {
            addCriterion("GLOBAL_AUDIT_OPINION not in", values, "globalAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditOpinionBetween(String value1, String value2) {
            addCriterion("GLOBAL_AUDIT_OPINION between", value1, value2, "globalAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditOpinionNotBetween(String value1, String value2) {
            addCriterion("GLOBAL_AUDIT_OPINION not between", value1, value2, "globalAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditTimeIsNull() {
            addCriterion("GLOBAL_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditTimeIsNotNull() {
            addCriterion("GLOBAL_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditTimeEqualTo(String value) {
            addCriterion("GLOBAL_AUDIT_TIME =", value, "globalAuditTime");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditTimeNotEqualTo(String value) {
            addCriterion("GLOBAL_AUDIT_TIME <>", value, "globalAuditTime");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditTimeGreaterThan(String value) {
            addCriterion("GLOBAL_AUDIT_TIME >", value, "globalAuditTime");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("GLOBAL_AUDIT_TIME >=", value, "globalAuditTime");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditTimeLessThan(String value) {
            addCriterion("GLOBAL_AUDIT_TIME <", value, "globalAuditTime");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("GLOBAL_AUDIT_TIME <=", value, "globalAuditTime");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditTimeLike(String value) {
            addCriterion("GLOBAL_AUDIT_TIME like", value, "globalAuditTime");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditTimeNotLike(String value) {
            addCriterion("GLOBAL_AUDIT_TIME not like", value, "globalAuditTime");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditTimeIn(List<String> values) {
            addCriterion("GLOBAL_AUDIT_TIME in", values, "globalAuditTime");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditTimeNotIn(List<String> values) {
            addCriterion("GLOBAL_AUDIT_TIME not in", values, "globalAuditTime");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditTimeBetween(String value1, String value2) {
            addCriterion("GLOBAL_AUDIT_TIME between", value1, value2, "globalAuditTime");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditTimeNotBetween(String value1, String value2) {
            addCriterion("GLOBAL_AUDIT_TIME not between", value1, value2, "globalAuditTime");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditUserFlowIsNull() {
            addCriterion("GLOBAL_AUDIT_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditUserFlowIsNotNull() {
            addCriterion("GLOBAL_AUDIT_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditUserFlowEqualTo(String value) {
            addCriterion("GLOBAL_AUDIT_USER_FLOW =", value, "globalAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditUserFlowNotEqualTo(String value) {
            addCriterion("GLOBAL_AUDIT_USER_FLOW <>", value, "globalAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditUserFlowGreaterThan(String value) {
            addCriterion("GLOBAL_AUDIT_USER_FLOW >", value, "globalAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("GLOBAL_AUDIT_USER_FLOW >=", value, "globalAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditUserFlowLessThan(String value) {
            addCriterion("GLOBAL_AUDIT_USER_FLOW <", value, "globalAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditUserFlowLessThanOrEqualTo(String value) {
            addCriterion("GLOBAL_AUDIT_USER_FLOW <=", value, "globalAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditUserFlowLike(String value) {
            addCriterion("GLOBAL_AUDIT_USER_FLOW like", value, "globalAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditUserFlowNotLike(String value) {
            addCriterion("GLOBAL_AUDIT_USER_FLOW not like", value, "globalAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditUserFlowIn(List<String> values) {
            addCriterion("GLOBAL_AUDIT_USER_FLOW in", values, "globalAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditUserFlowNotIn(List<String> values) {
            addCriterion("GLOBAL_AUDIT_USER_FLOW not in", values, "globalAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditUserFlowBetween(String value1, String value2) {
            addCriterion("GLOBAL_AUDIT_USER_FLOW between", value1, value2, "globalAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andGlobalAuditUserFlowNotBetween(String value1, String value2) {
            addCriterion("GLOBAL_AUDIT_USER_FLOW not between", value1, value2, "globalAuditUserFlow");
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

        public Criteria andXtLocalAuditOpinionIsNull() {
            addCriterion("XT_LOCAL_AUDIT_OPINION is null");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditOpinionIsNotNull() {
            addCriterion("XT_LOCAL_AUDIT_OPINION is not null");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditOpinionEqualTo(String value) {
            addCriterion("XT_LOCAL_AUDIT_OPINION =", value, "xtLocalAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditOpinionNotEqualTo(String value) {
            addCriterion("XT_LOCAL_AUDIT_OPINION <>", value, "xtLocalAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditOpinionGreaterThan(String value) {
            addCriterion("XT_LOCAL_AUDIT_OPINION >", value, "xtLocalAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditOpinionGreaterThanOrEqualTo(String value) {
            addCriterion("XT_LOCAL_AUDIT_OPINION >=", value, "xtLocalAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditOpinionLessThan(String value) {
            addCriterion("XT_LOCAL_AUDIT_OPINION <", value, "xtLocalAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditOpinionLessThanOrEqualTo(String value) {
            addCriterion("XT_LOCAL_AUDIT_OPINION <=", value, "xtLocalAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditOpinionLike(String value) {
            addCriterion("XT_LOCAL_AUDIT_OPINION like", value, "xtLocalAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditOpinionNotLike(String value) {
            addCriterion("XT_LOCAL_AUDIT_OPINION not like", value, "xtLocalAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditOpinionIn(List<String> values) {
            addCriterion("XT_LOCAL_AUDIT_OPINION in", values, "xtLocalAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditOpinionNotIn(List<String> values) {
            addCriterion("XT_LOCAL_AUDIT_OPINION not in", values, "xtLocalAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditOpinionBetween(String value1, String value2) {
            addCriterion("XT_LOCAL_AUDIT_OPINION between", value1, value2, "xtLocalAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditOpinionNotBetween(String value1, String value2) {
            addCriterion("XT_LOCAL_AUDIT_OPINION not between", value1, value2, "xtLocalAuditOpinion");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditTimeIsNull() {
            addCriterion("XT_LOCAL_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditTimeIsNotNull() {
            addCriterion("XT_LOCAL_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditTimeEqualTo(String value) {
            addCriterion("XT_LOCAL_AUDIT_TIME =", value, "xtLocalAuditTime");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditTimeNotEqualTo(String value) {
            addCriterion("XT_LOCAL_AUDIT_TIME <>", value, "xtLocalAuditTime");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditTimeGreaterThan(String value) {
            addCriterion("XT_LOCAL_AUDIT_TIME >", value, "xtLocalAuditTime");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("XT_LOCAL_AUDIT_TIME >=", value, "xtLocalAuditTime");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditTimeLessThan(String value) {
            addCriterion("XT_LOCAL_AUDIT_TIME <", value, "xtLocalAuditTime");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("XT_LOCAL_AUDIT_TIME <=", value, "xtLocalAuditTime");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditTimeLike(String value) {
            addCriterion("XT_LOCAL_AUDIT_TIME like", value, "xtLocalAuditTime");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditTimeNotLike(String value) {
            addCriterion("XT_LOCAL_AUDIT_TIME not like", value, "xtLocalAuditTime");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditTimeIn(List<String> values) {
            addCriterion("XT_LOCAL_AUDIT_TIME in", values, "xtLocalAuditTime");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditTimeNotIn(List<String> values) {
            addCriterion("XT_LOCAL_AUDIT_TIME not in", values, "xtLocalAuditTime");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditTimeBetween(String value1, String value2) {
            addCriterion("XT_LOCAL_AUDIT_TIME between", value1, value2, "xtLocalAuditTime");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditTimeNotBetween(String value1, String value2) {
            addCriterion("XT_LOCAL_AUDIT_TIME not between", value1, value2, "xtLocalAuditTime");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditUserFlowIsNull() {
            addCriterion("XT_LOCAL_AUDIT_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditUserFlowIsNotNull() {
            addCriterion("XT_LOCAL_AUDIT_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditUserFlowEqualTo(String value) {
            addCriterion("XT_LOCAL_AUDIT_USER_FLOW =", value, "xtLocalAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditUserFlowNotEqualTo(String value) {
            addCriterion("XT_LOCAL_AUDIT_USER_FLOW <>", value, "xtLocalAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditUserFlowGreaterThan(String value) {
            addCriterion("XT_LOCAL_AUDIT_USER_FLOW >", value, "xtLocalAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("XT_LOCAL_AUDIT_USER_FLOW >=", value, "xtLocalAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditUserFlowLessThan(String value) {
            addCriterion("XT_LOCAL_AUDIT_USER_FLOW <", value, "xtLocalAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditUserFlowLessThanOrEqualTo(String value) {
            addCriterion("XT_LOCAL_AUDIT_USER_FLOW <=", value, "xtLocalAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditUserFlowLike(String value) {
            addCriterion("XT_LOCAL_AUDIT_USER_FLOW like", value, "xtLocalAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditUserFlowNotLike(String value) {
            addCriterion("XT_LOCAL_AUDIT_USER_FLOW not like", value, "xtLocalAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditUserFlowIn(List<String> values) {
            addCriterion("XT_LOCAL_AUDIT_USER_FLOW in", values, "xtLocalAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditUserFlowNotIn(List<String> values) {
            addCriterion("XT_LOCAL_AUDIT_USER_FLOW not in", values, "xtLocalAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditUserFlowBetween(String value1, String value2) {
            addCriterion("XT_LOCAL_AUDIT_USER_FLOW between", value1, value2, "xtLocalAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andXtLocalAuditUserFlowNotBetween(String value1, String value2) {
            addCriterion("XT_LOCAL_AUDIT_USER_FLOW not between", value1, value2, "xtLocalAuditUserFlow");
            return (Criteria) this;
        }

        public Criteria andRequireXtAduitIsNull() {
            addCriterion("REQUIRE_XT_ADUIT is null");
            return (Criteria) this;
        }

        public Criteria andRequireXtAduitIsNotNull() {
            addCriterion("REQUIRE_XT_ADUIT is not null");
            return (Criteria) this;
        }

        public Criteria andRequireXtAduitEqualTo(String value) {
            addCriterion("REQUIRE_XT_ADUIT =", value, "requireXtAduit");
            return (Criteria) this;
        }

        public Criteria andRequireXtAduitNotEqualTo(String value) {
            addCriterion("REQUIRE_XT_ADUIT <>", value, "requireXtAduit");
            return (Criteria) this;
        }

        public Criteria andRequireXtAduitGreaterThan(String value) {
            addCriterion("REQUIRE_XT_ADUIT >", value, "requireXtAduit");
            return (Criteria) this;
        }

        public Criteria andRequireXtAduitGreaterThanOrEqualTo(String value) {
            addCriterion("REQUIRE_XT_ADUIT >=", value, "requireXtAduit");
            return (Criteria) this;
        }

        public Criteria andRequireXtAduitLessThan(String value) {
            addCriterion("REQUIRE_XT_ADUIT <", value, "requireXtAduit");
            return (Criteria) this;
        }

        public Criteria andRequireXtAduitLessThanOrEqualTo(String value) {
            addCriterion("REQUIRE_XT_ADUIT <=", value, "requireXtAduit");
            return (Criteria) this;
        }

        public Criteria andRequireXtAduitLike(String value) {
            addCriterion("REQUIRE_XT_ADUIT like", value, "requireXtAduit");
            return (Criteria) this;
        }

        public Criteria andRequireXtAduitNotLike(String value) {
            addCriterion("REQUIRE_XT_ADUIT not like", value, "requireXtAduit");
            return (Criteria) this;
        }

        public Criteria andRequireXtAduitIn(List<String> values) {
            addCriterion("REQUIRE_XT_ADUIT in", values, "requireXtAduit");
            return (Criteria) this;
        }

        public Criteria andRequireXtAduitNotIn(List<String> values) {
            addCriterion("REQUIRE_XT_ADUIT not in", values, "requireXtAduit");
            return (Criteria) this;
        }

        public Criteria andRequireXtAduitBetween(String value1, String value2) {
            addCriterion("REQUIRE_XT_ADUIT between", value1, value2, "requireXtAduit");
            return (Criteria) this;
        }

        public Criteria andRequireXtAduitNotBetween(String value1, String value2) {
            addCriterion("REQUIRE_XT_ADUIT not between", value1, value2, "requireXtAduit");
            return (Criteria) this;
        }

        public Criteria andProveFilePathIsNull() {
            addCriterion("PROVE_FILE_PATH is null");
            return (Criteria) this;
        }

        public Criteria andProveFilePathIsNotNull() {
            addCriterion("PROVE_FILE_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andProveFilePathEqualTo(String value) {
            addCriterion("PROVE_FILE_PATH =", value, "proveFilePath");
            return (Criteria) this;
        }

        public Criteria andProveFilePathNotEqualTo(String value) {
            addCriterion("PROVE_FILE_PATH <>", value, "proveFilePath");
            return (Criteria) this;
        }

        public Criteria andProveFilePathGreaterThan(String value) {
            addCriterion("PROVE_FILE_PATH >", value, "proveFilePath");
            return (Criteria) this;
        }

        public Criteria andProveFilePathGreaterThanOrEqualTo(String value) {
            addCriterion("PROVE_FILE_PATH >=", value, "proveFilePath");
            return (Criteria) this;
        }

        public Criteria andProveFilePathLessThan(String value) {
            addCriterion("PROVE_FILE_PATH <", value, "proveFilePath");
            return (Criteria) this;
        }

        public Criteria andProveFilePathLessThanOrEqualTo(String value) {
            addCriterion("PROVE_FILE_PATH <=", value, "proveFilePath");
            return (Criteria) this;
        }

        public Criteria andProveFilePathLike(String value) {
            addCriterion("PROVE_FILE_PATH like", value, "proveFilePath");
            return (Criteria) this;
        }

        public Criteria andProveFilePathNotLike(String value) {
            addCriterion("PROVE_FILE_PATH not like", value, "proveFilePath");
            return (Criteria) this;
        }

        public Criteria andProveFilePathIn(List<String> values) {
            addCriterion("PROVE_FILE_PATH in", values, "proveFilePath");
            return (Criteria) this;
        }

        public Criteria andProveFilePathNotIn(List<String> values) {
            addCriterion("PROVE_FILE_PATH not in", values, "proveFilePath");
            return (Criteria) this;
        }

        public Criteria andProveFilePathBetween(String value1, String value2) {
            addCriterion("PROVE_FILE_PATH between", value1, value2, "proveFilePath");
            return (Criteria) this;
        }

        public Criteria andProveFilePathNotBetween(String value1, String value2) {
            addCriterion("PROVE_FILE_PATH not between", value1, value2, "proveFilePath");
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