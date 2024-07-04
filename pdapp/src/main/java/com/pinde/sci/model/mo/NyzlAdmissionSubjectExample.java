package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class NyzlAdmissionSubjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NyzlAdmissionSubjectExample() {
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

        public Criteria andTkAcademicNumIsNull() {
            addCriterion("TK_ACADEMIC_NUM is null");
            return (Criteria) this;
        }

        public Criteria andTkAcademicNumIsNotNull() {
            addCriterion("TK_ACADEMIC_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andTkAcademicNumEqualTo(String value) {
            addCriterion("TK_ACADEMIC_NUM =", value, "tkAcademicNum");
            return (Criteria) this;
        }

        public Criteria andTkAcademicNumNotEqualTo(String value) {
            addCriterion("TK_ACADEMIC_NUM <>", value, "tkAcademicNum");
            return (Criteria) this;
        }

        public Criteria andTkAcademicNumGreaterThan(String value) {
            addCriterion("TK_ACADEMIC_NUM >", value, "tkAcademicNum");
            return (Criteria) this;
        }

        public Criteria andTkAcademicNumGreaterThanOrEqualTo(String value) {
            addCriterion("TK_ACADEMIC_NUM >=", value, "tkAcademicNum");
            return (Criteria) this;
        }

        public Criteria andTkAcademicNumLessThan(String value) {
            addCriterion("TK_ACADEMIC_NUM <", value, "tkAcademicNum");
            return (Criteria) this;
        }

        public Criteria andTkAcademicNumLessThanOrEqualTo(String value) {
            addCriterion("TK_ACADEMIC_NUM <=", value, "tkAcademicNum");
            return (Criteria) this;
        }

        public Criteria andTkAcademicNumLike(String value) {
            addCriterion("TK_ACADEMIC_NUM like", value, "tkAcademicNum");
            return (Criteria) this;
        }

        public Criteria andTkAcademicNumNotLike(String value) {
            addCriterion("TK_ACADEMIC_NUM not like", value, "tkAcademicNum");
            return (Criteria) this;
        }

        public Criteria andTkAcademicNumIn(List<String> values) {
            addCriterion("TK_ACADEMIC_NUM in", values, "tkAcademicNum");
            return (Criteria) this;
        }

        public Criteria andTkAcademicNumNotIn(List<String> values) {
            addCriterion("TK_ACADEMIC_NUM not in", values, "tkAcademicNum");
            return (Criteria) this;
        }

        public Criteria andTkAcademicNumBetween(String value1, String value2) {
            addCriterion("TK_ACADEMIC_NUM between", value1, value2, "tkAcademicNum");
            return (Criteria) this;
        }

        public Criteria andTkAcademicNumNotBetween(String value1, String value2) {
            addCriterion("TK_ACADEMIC_NUM not between", value1, value2, "tkAcademicNum");
            return (Criteria) this;
        }

        public Criteria andAcademicAdmissionNumIsNull() {
            addCriterion("ACADEMIC_ADMISSION_NUM is null");
            return (Criteria) this;
        }

        public Criteria andAcademicAdmissionNumIsNotNull() {
            addCriterion("ACADEMIC_ADMISSION_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andAcademicAdmissionNumEqualTo(String value) {
            addCriterion("ACADEMIC_ADMISSION_NUM =", value, "academicAdmissionNum");
            return (Criteria) this;
        }

        public Criteria andAcademicAdmissionNumNotEqualTo(String value) {
            addCriterion("ACADEMIC_ADMISSION_NUM <>", value, "academicAdmissionNum");
            return (Criteria) this;
        }

        public Criteria andAcademicAdmissionNumGreaterThan(String value) {
            addCriterion("ACADEMIC_ADMISSION_NUM >", value, "academicAdmissionNum");
            return (Criteria) this;
        }

        public Criteria andAcademicAdmissionNumGreaterThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_ADMISSION_NUM >=", value, "academicAdmissionNum");
            return (Criteria) this;
        }

        public Criteria andAcademicAdmissionNumLessThan(String value) {
            addCriterion("ACADEMIC_ADMISSION_NUM <", value, "academicAdmissionNum");
            return (Criteria) this;
        }

        public Criteria andAcademicAdmissionNumLessThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_ADMISSION_NUM <=", value, "academicAdmissionNum");
            return (Criteria) this;
        }

        public Criteria andAcademicAdmissionNumLike(String value) {
            addCriterion("ACADEMIC_ADMISSION_NUM like", value, "academicAdmissionNum");
            return (Criteria) this;
        }

        public Criteria andAcademicAdmissionNumNotLike(String value) {
            addCriterion("ACADEMIC_ADMISSION_NUM not like", value, "academicAdmissionNum");
            return (Criteria) this;
        }

        public Criteria andAcademicAdmissionNumIn(List<String> values) {
            addCriterion("ACADEMIC_ADMISSION_NUM in", values, "academicAdmissionNum");
            return (Criteria) this;
        }

        public Criteria andAcademicAdmissionNumNotIn(List<String> values) {
            addCriterion("ACADEMIC_ADMISSION_NUM not in", values, "academicAdmissionNum");
            return (Criteria) this;
        }

        public Criteria andAcademicAdmissionNumBetween(String value1, String value2) {
            addCriterion("ACADEMIC_ADMISSION_NUM between", value1, value2, "academicAdmissionNum");
            return (Criteria) this;
        }

        public Criteria andAcademicAdmissionNumNotBetween(String value1, String value2) {
            addCriterion("ACADEMIC_ADMISSION_NUM not between", value1, value2, "academicAdmissionNum");
            return (Criteria) this;
        }

        public Criteria andAcademicSurplusNumIsNull() {
            addCriterion("ACADEMIC_SURPLUS_NUM is null");
            return (Criteria) this;
        }

        public Criteria andAcademicSurplusNumIsNotNull() {
            addCriterion("ACADEMIC_SURPLUS_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andAcademicSurplusNumEqualTo(String value) {
            addCriterion("ACADEMIC_SURPLUS_NUM =", value, "academicSurplusNum");
            return (Criteria) this;
        }

        public Criteria andAcademicSurplusNumNotEqualTo(String value) {
            addCriterion("ACADEMIC_SURPLUS_NUM <>", value, "academicSurplusNum");
            return (Criteria) this;
        }

        public Criteria andAcademicSurplusNumGreaterThan(String value) {
            addCriterion("ACADEMIC_SURPLUS_NUM >", value, "academicSurplusNum");
            return (Criteria) this;
        }

        public Criteria andAcademicSurplusNumGreaterThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_SURPLUS_NUM >=", value, "academicSurplusNum");
            return (Criteria) this;
        }

        public Criteria andAcademicSurplusNumLessThan(String value) {
            addCriterion("ACADEMIC_SURPLUS_NUM <", value, "academicSurplusNum");
            return (Criteria) this;
        }

        public Criteria andAcademicSurplusNumLessThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_SURPLUS_NUM <=", value, "academicSurplusNum");
            return (Criteria) this;
        }

        public Criteria andAcademicSurplusNumLike(String value) {
            addCriterion("ACADEMIC_SURPLUS_NUM like", value, "academicSurplusNum");
            return (Criteria) this;
        }

        public Criteria andAcademicSurplusNumNotLike(String value) {
            addCriterion("ACADEMIC_SURPLUS_NUM not like", value, "academicSurplusNum");
            return (Criteria) this;
        }

        public Criteria andAcademicSurplusNumIn(List<String> values) {
            addCriterion("ACADEMIC_SURPLUS_NUM in", values, "academicSurplusNum");
            return (Criteria) this;
        }

        public Criteria andAcademicSurplusNumNotIn(List<String> values) {
            addCriterion("ACADEMIC_SURPLUS_NUM not in", values, "academicSurplusNum");
            return (Criteria) this;
        }

        public Criteria andAcademicSurplusNumBetween(String value1, String value2) {
            addCriterion("ACADEMIC_SURPLUS_NUM between", value1, value2, "academicSurplusNum");
            return (Criteria) this;
        }

        public Criteria andAcademicSurplusNumNotBetween(String value1, String value2) {
            addCriterion("ACADEMIC_SURPLUS_NUM not between", value1, value2, "academicSurplusNum");
            return (Criteria) this;
        }

        public Criteria andTkSpecialNumIsNull() {
            addCriterion("TK_SPECIAL_NUM is null");
            return (Criteria) this;
        }

        public Criteria andTkSpecialNumIsNotNull() {
            addCriterion("TK_SPECIAL_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andTkSpecialNumEqualTo(String value) {
            addCriterion("TK_SPECIAL_NUM =", value, "tkSpecialNum");
            return (Criteria) this;
        }

        public Criteria andTkSpecialNumNotEqualTo(String value) {
            addCriterion("TK_SPECIAL_NUM <>", value, "tkSpecialNum");
            return (Criteria) this;
        }

        public Criteria andTkSpecialNumGreaterThan(String value) {
            addCriterion("TK_SPECIAL_NUM >", value, "tkSpecialNum");
            return (Criteria) this;
        }

        public Criteria andTkSpecialNumGreaterThanOrEqualTo(String value) {
            addCriterion("TK_SPECIAL_NUM >=", value, "tkSpecialNum");
            return (Criteria) this;
        }

        public Criteria andTkSpecialNumLessThan(String value) {
            addCriterion("TK_SPECIAL_NUM <", value, "tkSpecialNum");
            return (Criteria) this;
        }

        public Criteria andTkSpecialNumLessThanOrEqualTo(String value) {
            addCriterion("TK_SPECIAL_NUM <=", value, "tkSpecialNum");
            return (Criteria) this;
        }

        public Criteria andTkSpecialNumLike(String value) {
            addCriterion("TK_SPECIAL_NUM like", value, "tkSpecialNum");
            return (Criteria) this;
        }

        public Criteria andTkSpecialNumNotLike(String value) {
            addCriterion("TK_SPECIAL_NUM not like", value, "tkSpecialNum");
            return (Criteria) this;
        }

        public Criteria andTkSpecialNumIn(List<String> values) {
            addCriterion("TK_SPECIAL_NUM in", values, "tkSpecialNum");
            return (Criteria) this;
        }

        public Criteria andTkSpecialNumNotIn(List<String> values) {
            addCriterion("TK_SPECIAL_NUM not in", values, "tkSpecialNum");
            return (Criteria) this;
        }

        public Criteria andTkSpecialNumBetween(String value1, String value2) {
            addCriterion("TK_SPECIAL_NUM between", value1, value2, "tkSpecialNum");
            return (Criteria) this;
        }

        public Criteria andTkSpecialNumNotBetween(String value1, String value2) {
            addCriterion("TK_SPECIAL_NUM not between", value1, value2, "tkSpecialNum");
            return (Criteria) this;
        }

        public Criteria andSpecialAdmissionNumIsNull() {
            addCriterion("SPECIAL_ADMISSION_NUM is null");
            return (Criteria) this;
        }

        public Criteria andSpecialAdmissionNumIsNotNull() {
            addCriterion("SPECIAL_ADMISSION_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialAdmissionNumEqualTo(String value) {
            addCriterion("SPECIAL_ADMISSION_NUM =", value, "specialAdmissionNum");
            return (Criteria) this;
        }

        public Criteria andSpecialAdmissionNumNotEqualTo(String value) {
            addCriterion("SPECIAL_ADMISSION_NUM <>", value, "specialAdmissionNum");
            return (Criteria) this;
        }

        public Criteria andSpecialAdmissionNumGreaterThan(String value) {
            addCriterion("SPECIAL_ADMISSION_NUM >", value, "specialAdmissionNum");
            return (Criteria) this;
        }

        public Criteria andSpecialAdmissionNumGreaterThanOrEqualTo(String value) {
            addCriterion("SPECIAL_ADMISSION_NUM >=", value, "specialAdmissionNum");
            return (Criteria) this;
        }

        public Criteria andSpecialAdmissionNumLessThan(String value) {
            addCriterion("SPECIAL_ADMISSION_NUM <", value, "specialAdmissionNum");
            return (Criteria) this;
        }

        public Criteria andSpecialAdmissionNumLessThanOrEqualTo(String value) {
            addCriterion("SPECIAL_ADMISSION_NUM <=", value, "specialAdmissionNum");
            return (Criteria) this;
        }

        public Criteria andSpecialAdmissionNumLike(String value) {
            addCriterion("SPECIAL_ADMISSION_NUM like", value, "specialAdmissionNum");
            return (Criteria) this;
        }

        public Criteria andSpecialAdmissionNumNotLike(String value) {
            addCriterion("SPECIAL_ADMISSION_NUM not like", value, "specialAdmissionNum");
            return (Criteria) this;
        }

        public Criteria andSpecialAdmissionNumIn(List<String> values) {
            addCriterion("SPECIAL_ADMISSION_NUM in", values, "specialAdmissionNum");
            return (Criteria) this;
        }

        public Criteria andSpecialAdmissionNumNotIn(List<String> values) {
            addCriterion("SPECIAL_ADMISSION_NUM not in", values, "specialAdmissionNum");
            return (Criteria) this;
        }

        public Criteria andSpecialAdmissionNumBetween(String value1, String value2) {
            addCriterion("SPECIAL_ADMISSION_NUM between", value1, value2, "specialAdmissionNum");
            return (Criteria) this;
        }

        public Criteria andSpecialAdmissionNumNotBetween(String value1, String value2) {
            addCriterion("SPECIAL_ADMISSION_NUM not between", value1, value2, "specialAdmissionNum");
            return (Criteria) this;
        }

        public Criteria andSpecialSurplusNumIsNull() {
            addCriterion("SPECIAL_SURPLUS_NUM is null");
            return (Criteria) this;
        }

        public Criteria andSpecialSurplusNumIsNotNull() {
            addCriterion("SPECIAL_SURPLUS_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialSurplusNumEqualTo(String value) {
            addCriterion("SPECIAL_SURPLUS_NUM =", value, "specialSurplusNum");
            return (Criteria) this;
        }

        public Criteria andSpecialSurplusNumNotEqualTo(String value) {
            addCriterion("SPECIAL_SURPLUS_NUM <>", value, "specialSurplusNum");
            return (Criteria) this;
        }

        public Criteria andSpecialSurplusNumGreaterThan(String value) {
            addCriterion("SPECIAL_SURPLUS_NUM >", value, "specialSurplusNum");
            return (Criteria) this;
        }

        public Criteria andSpecialSurplusNumGreaterThanOrEqualTo(String value) {
            addCriterion("SPECIAL_SURPLUS_NUM >=", value, "specialSurplusNum");
            return (Criteria) this;
        }

        public Criteria andSpecialSurplusNumLessThan(String value) {
            addCriterion("SPECIAL_SURPLUS_NUM <", value, "specialSurplusNum");
            return (Criteria) this;
        }

        public Criteria andSpecialSurplusNumLessThanOrEqualTo(String value) {
            addCriterion("SPECIAL_SURPLUS_NUM <=", value, "specialSurplusNum");
            return (Criteria) this;
        }

        public Criteria andSpecialSurplusNumLike(String value) {
            addCriterion("SPECIAL_SURPLUS_NUM like", value, "specialSurplusNum");
            return (Criteria) this;
        }

        public Criteria andSpecialSurplusNumNotLike(String value) {
            addCriterion("SPECIAL_SURPLUS_NUM not like", value, "specialSurplusNum");
            return (Criteria) this;
        }

        public Criteria andSpecialSurplusNumIn(List<String> values) {
            addCriterion("SPECIAL_SURPLUS_NUM in", values, "specialSurplusNum");
            return (Criteria) this;
        }

        public Criteria andSpecialSurplusNumNotIn(List<String> values) {
            addCriterion("SPECIAL_SURPLUS_NUM not in", values, "specialSurplusNum");
            return (Criteria) this;
        }

        public Criteria andSpecialSurplusNumBetween(String value1, String value2) {
            addCriterion("SPECIAL_SURPLUS_NUM between", value1, value2, "specialSurplusNum");
            return (Criteria) this;
        }

        public Criteria andSpecialSurplusNumNotBetween(String value1, String value2) {
            addCriterion("SPECIAL_SURPLUS_NUM not between", value1, value2, "specialSurplusNum");
            return (Criteria) this;
        }

        public Criteria andTmsAcademicNumIsNull() {
            addCriterion("TMS_ACADEMIC_NUM is null");
            return (Criteria) this;
        }

        public Criteria andTmsAcademicNumIsNotNull() {
            addCriterion("TMS_ACADEMIC_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andTmsAcademicNumEqualTo(String value) {
            addCriterion("TMS_ACADEMIC_NUM =", value, "tmsAcademicNum");
            return (Criteria) this;
        }

        public Criteria andTmsAcademicNumNotEqualTo(String value) {
            addCriterion("TMS_ACADEMIC_NUM <>", value, "tmsAcademicNum");
            return (Criteria) this;
        }

        public Criteria andTmsAcademicNumGreaterThan(String value) {
            addCriterion("TMS_ACADEMIC_NUM >", value, "tmsAcademicNum");
            return (Criteria) this;
        }

        public Criteria andTmsAcademicNumGreaterThanOrEqualTo(String value) {
            addCriterion("TMS_ACADEMIC_NUM >=", value, "tmsAcademicNum");
            return (Criteria) this;
        }

        public Criteria andTmsAcademicNumLessThan(String value) {
            addCriterion("TMS_ACADEMIC_NUM <", value, "tmsAcademicNum");
            return (Criteria) this;
        }

        public Criteria andTmsAcademicNumLessThanOrEqualTo(String value) {
            addCriterion("TMS_ACADEMIC_NUM <=", value, "tmsAcademicNum");
            return (Criteria) this;
        }

        public Criteria andTmsAcademicNumLike(String value) {
            addCriterion("TMS_ACADEMIC_NUM like", value, "tmsAcademicNum");
            return (Criteria) this;
        }

        public Criteria andTmsAcademicNumNotLike(String value) {
            addCriterion("TMS_ACADEMIC_NUM not like", value, "tmsAcademicNum");
            return (Criteria) this;
        }

        public Criteria andTmsAcademicNumIn(List<String> values) {
            addCriterion("TMS_ACADEMIC_NUM in", values, "tmsAcademicNum");
            return (Criteria) this;
        }

        public Criteria andTmsAcademicNumNotIn(List<String> values) {
            addCriterion("TMS_ACADEMIC_NUM not in", values, "tmsAcademicNum");
            return (Criteria) this;
        }

        public Criteria andTmsAcademicNumBetween(String value1, String value2) {
            addCriterion("TMS_ACADEMIC_NUM between", value1, value2, "tmsAcademicNum");
            return (Criteria) this;
        }

        public Criteria andTmsAcademicNumNotBetween(String value1, String value2) {
            addCriterion("TMS_ACADEMIC_NUM not between", value1, value2, "tmsAcademicNum");
            return (Criteria) this;
        }

        public Criteria andTmsSpecialNumIsNull() {
            addCriterion("TMS_SPECIAL_NUM is null");
            return (Criteria) this;
        }

        public Criteria andTmsSpecialNumIsNotNull() {
            addCriterion("TMS_SPECIAL_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andTmsSpecialNumEqualTo(String value) {
            addCriterion("TMS_SPECIAL_NUM =", value, "tmsSpecialNum");
            return (Criteria) this;
        }

        public Criteria andTmsSpecialNumNotEqualTo(String value) {
            addCriterion("TMS_SPECIAL_NUM <>", value, "tmsSpecialNum");
            return (Criteria) this;
        }

        public Criteria andTmsSpecialNumGreaterThan(String value) {
            addCriterion("TMS_SPECIAL_NUM >", value, "tmsSpecialNum");
            return (Criteria) this;
        }

        public Criteria andTmsSpecialNumGreaterThanOrEqualTo(String value) {
            addCriterion("TMS_SPECIAL_NUM >=", value, "tmsSpecialNum");
            return (Criteria) this;
        }

        public Criteria andTmsSpecialNumLessThan(String value) {
            addCriterion("TMS_SPECIAL_NUM <", value, "tmsSpecialNum");
            return (Criteria) this;
        }

        public Criteria andTmsSpecialNumLessThanOrEqualTo(String value) {
            addCriterion("TMS_SPECIAL_NUM <=", value, "tmsSpecialNum");
            return (Criteria) this;
        }

        public Criteria andTmsSpecialNumLike(String value) {
            addCriterion("TMS_SPECIAL_NUM like", value, "tmsSpecialNum");
            return (Criteria) this;
        }

        public Criteria andTmsSpecialNumNotLike(String value) {
            addCriterion("TMS_SPECIAL_NUM not like", value, "tmsSpecialNum");
            return (Criteria) this;
        }

        public Criteria andTmsSpecialNumIn(List<String> values) {
            addCriterion("TMS_SPECIAL_NUM in", values, "tmsSpecialNum");
            return (Criteria) this;
        }

        public Criteria andTmsSpecialNumNotIn(List<String> values) {
            addCriterion("TMS_SPECIAL_NUM not in", values, "tmsSpecialNum");
            return (Criteria) this;
        }

        public Criteria andTmsSpecialNumBetween(String value1, String value2) {
            addCriterion("TMS_SPECIAL_NUM between", value1, value2, "tmsSpecialNum");
            return (Criteria) this;
        }

        public Criteria andTmsSpecialNumNotBetween(String value1, String value2) {
            addCriterion("TMS_SPECIAL_NUM not between", value1, value2, "tmsSpecialNum");
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