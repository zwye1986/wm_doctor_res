package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class NyzlRetestStudentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NyzlRetestStudentExample() {
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

        public Criteria andStuNoIsNull() {
            addCriterion("STU_NO is null");
            return (Criteria) this;
        }

        public Criteria andStuNoIsNotNull() {
            addCriterion("STU_NO is not null");
            return (Criteria) this;
        }

        public Criteria andStuNoEqualTo(String value) {
            addCriterion("STU_NO =", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoNotEqualTo(String value) {
            addCriterion("STU_NO <>", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoGreaterThan(String value) {
            addCriterion("STU_NO >", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoGreaterThanOrEqualTo(String value) {
            addCriterion("STU_NO >=", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoLessThan(String value) {
            addCriterion("STU_NO <", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoLessThanOrEqualTo(String value) {
            addCriterion("STU_NO <=", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoLike(String value) {
            addCriterion("STU_NO like", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoNotLike(String value) {
            addCriterion("STU_NO not like", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoIn(List<String> values) {
            addCriterion("STU_NO in", values, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoNotIn(List<String> values) {
            addCriterion("STU_NO not in", values, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoBetween(String value1, String value2) {
            addCriterion("STU_NO between", value1, value2, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoNotBetween(String value1, String value2) {
            addCriterion("STU_NO not between", value1, value2, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNameIsNull() {
            addCriterion("STU_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStuNameIsNotNull() {
            addCriterion("STU_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStuNameEqualTo(String value) {
            addCriterion("STU_NAME =", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameNotEqualTo(String value) {
            addCriterion("STU_NAME <>", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameGreaterThan(String value) {
            addCriterion("STU_NAME >", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameGreaterThanOrEqualTo(String value) {
            addCriterion("STU_NAME >=", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameLessThan(String value) {
            addCriterion("STU_NAME <", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameLessThanOrEqualTo(String value) {
            addCriterion("STU_NAME <=", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameLike(String value) {
            addCriterion("STU_NAME like", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameNotLike(String value) {
            addCriterion("STU_NAME not like", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameIn(List<String> values) {
            addCriterion("STU_NAME in", values, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameNotIn(List<String> values) {
            addCriterion("STU_NAME not in", values, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameBetween(String value1, String value2) {
            addCriterion("STU_NAME between", value1, value2, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameNotBetween(String value1, String value2) {
            addCriterion("STU_NAME not between", value1, value2, "stuName");
            return (Criteria) this;
        }

        public Criteria andCardNoIsNull() {
            addCriterion("CARD_NO is null");
            return (Criteria) this;
        }

        public Criteria andCardNoIsNotNull() {
            addCriterion("CARD_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCardNoEqualTo(String value) {
            addCriterion("CARD_NO =", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotEqualTo(String value) {
            addCriterion("CARD_NO <>", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoGreaterThan(String value) {
            addCriterion("CARD_NO >", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("CARD_NO >=", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLessThan(String value) {
            addCriterion("CARD_NO <", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLessThanOrEqualTo(String value) {
            addCriterion("CARD_NO <=", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLike(String value) {
            addCriterion("CARD_NO like", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotLike(String value) {
            addCriterion("CARD_NO not like", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoIn(List<String> values) {
            addCriterion("CARD_NO in", values, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotIn(List<String> values) {
            addCriterion("CARD_NO not in", values, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoBetween(String value1, String value2) {
            addCriterion("CARD_NO between", value1, value2, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotBetween(String value1, String value2) {
            addCriterion("CARD_NO not between", value1, value2, "cardNo");
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

        public Criteria andEducationTypeIdIsNull() {
            addCriterion("EDUCATION_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdIsNotNull() {
            addCriterion("EDUCATION_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdEqualTo(String value) {
            addCriterion("EDUCATION_TYPE_ID =", value, "educationTypeId");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdNotEqualTo(String value) {
            addCriterion("EDUCATION_TYPE_ID <>", value, "educationTypeId");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdGreaterThan(String value) {
            addCriterion("EDUCATION_TYPE_ID >", value, "educationTypeId");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("EDUCATION_TYPE_ID >=", value, "educationTypeId");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdLessThan(String value) {
            addCriterion("EDUCATION_TYPE_ID <", value, "educationTypeId");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdLessThanOrEqualTo(String value) {
            addCriterion("EDUCATION_TYPE_ID <=", value, "educationTypeId");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdLike(String value) {
            addCriterion("EDUCATION_TYPE_ID like", value, "educationTypeId");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdNotLike(String value) {
            addCriterion("EDUCATION_TYPE_ID not like", value, "educationTypeId");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdIn(List<String> values) {
            addCriterion("EDUCATION_TYPE_ID in", values, "educationTypeId");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdNotIn(List<String> values) {
            addCriterion("EDUCATION_TYPE_ID not in", values, "educationTypeId");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdBetween(String value1, String value2) {
            addCriterion("EDUCATION_TYPE_ID between", value1, value2, "educationTypeId");
            return (Criteria) this;
        }

        public Criteria andEducationTypeIdNotBetween(String value1, String value2) {
            addCriterion("EDUCATION_TYPE_ID not between", value1, value2, "educationTypeId");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameIsNull() {
            addCriterion("EDUCATION_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameIsNotNull() {
            addCriterion("EDUCATION_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameEqualTo(String value) {
            addCriterion("EDUCATION_TYPE_NAME =", value, "educationTypeName");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameNotEqualTo(String value) {
            addCriterion("EDUCATION_TYPE_NAME <>", value, "educationTypeName");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameGreaterThan(String value) {
            addCriterion("EDUCATION_TYPE_NAME >", value, "educationTypeName");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("EDUCATION_TYPE_NAME >=", value, "educationTypeName");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameLessThan(String value) {
            addCriterion("EDUCATION_TYPE_NAME <", value, "educationTypeName");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameLessThanOrEqualTo(String value) {
            addCriterion("EDUCATION_TYPE_NAME <=", value, "educationTypeName");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameLike(String value) {
            addCriterion("EDUCATION_TYPE_NAME like", value, "educationTypeName");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameNotLike(String value) {
            addCriterion("EDUCATION_TYPE_NAME not like", value, "educationTypeName");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameIn(List<String> values) {
            addCriterion("EDUCATION_TYPE_NAME in", values, "educationTypeName");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameNotIn(List<String> values) {
            addCriterion("EDUCATION_TYPE_NAME not in", values, "educationTypeName");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameBetween(String value1, String value2) {
            addCriterion("EDUCATION_TYPE_NAME between", value1, value2, "educationTypeName");
            return (Criteria) this;
        }

        public Criteria andEducationTypeNameNotBetween(String value1, String value2) {
            addCriterion("EDUCATION_TYPE_NAME not between", value1, value2, "educationTypeName");
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

        public Criteria andIntentionTeacherNameIsNull() {
            addCriterion("INTENTION_TEACHER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andIntentionTeacherNameIsNotNull() {
            addCriterion("INTENTION_TEACHER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andIntentionTeacherNameEqualTo(String value) {
            addCriterion("INTENTION_TEACHER_NAME =", value, "intentionTeacherName");
            return (Criteria) this;
        }

        public Criteria andIntentionTeacherNameNotEqualTo(String value) {
            addCriterion("INTENTION_TEACHER_NAME <>", value, "intentionTeacherName");
            return (Criteria) this;
        }

        public Criteria andIntentionTeacherNameGreaterThan(String value) {
            addCriterion("INTENTION_TEACHER_NAME >", value, "intentionTeacherName");
            return (Criteria) this;
        }

        public Criteria andIntentionTeacherNameGreaterThanOrEqualTo(String value) {
            addCriterion("INTENTION_TEACHER_NAME >=", value, "intentionTeacherName");
            return (Criteria) this;
        }

        public Criteria andIntentionTeacherNameLessThan(String value) {
            addCriterion("INTENTION_TEACHER_NAME <", value, "intentionTeacherName");
            return (Criteria) this;
        }

        public Criteria andIntentionTeacherNameLessThanOrEqualTo(String value) {
            addCriterion("INTENTION_TEACHER_NAME <=", value, "intentionTeacherName");
            return (Criteria) this;
        }

        public Criteria andIntentionTeacherNameLike(String value) {
            addCriterion("INTENTION_TEACHER_NAME like", value, "intentionTeacherName");
            return (Criteria) this;
        }

        public Criteria andIntentionTeacherNameNotLike(String value) {
            addCriterion("INTENTION_TEACHER_NAME not like", value, "intentionTeacherName");
            return (Criteria) this;
        }

        public Criteria andIntentionTeacherNameIn(List<String> values) {
            addCriterion("INTENTION_TEACHER_NAME in", values, "intentionTeacherName");
            return (Criteria) this;
        }

        public Criteria andIntentionTeacherNameNotIn(List<String> values) {
            addCriterion("INTENTION_TEACHER_NAME not in", values, "intentionTeacherName");
            return (Criteria) this;
        }

        public Criteria andIntentionTeacherNameBetween(String value1, String value2) {
            addCriterion("INTENTION_TEACHER_NAME between", value1, value2, "intentionTeacherName");
            return (Criteria) this;
        }

        public Criteria andIntentionTeacherNameNotBetween(String value1, String value2) {
            addCriterion("INTENTION_TEACHER_NAME not between", value1, value2, "intentionTeacherName");
            return (Criteria) this;
        }

        public Criteria andSexIdIsNull() {
            addCriterion("SEX_ID is null");
            return (Criteria) this;
        }

        public Criteria andSexIdIsNotNull() {
            addCriterion("SEX_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSexIdEqualTo(String value) {
            addCriterion("SEX_ID =", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotEqualTo(String value) {
            addCriterion("SEX_ID <>", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdGreaterThan(String value) {
            addCriterion("SEX_ID >", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdGreaterThanOrEqualTo(String value) {
            addCriterion("SEX_ID >=", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdLessThan(String value) {
            addCriterion("SEX_ID <", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdLessThanOrEqualTo(String value) {
            addCriterion("SEX_ID <=", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdLike(String value) {
            addCriterion("SEX_ID like", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotLike(String value) {
            addCriterion("SEX_ID not like", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdIn(List<String> values) {
            addCriterion("SEX_ID in", values, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotIn(List<String> values) {
            addCriterion("SEX_ID not in", values, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdBetween(String value1, String value2) {
            addCriterion("SEX_ID between", value1, value2, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotBetween(String value1, String value2) {
            addCriterion("SEX_ID not between", value1, value2, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexNameIsNull() {
            addCriterion("SEX_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSexNameIsNotNull() {
            addCriterion("SEX_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSexNameEqualTo(String value) {
            addCriterion("SEX_NAME =", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotEqualTo(String value) {
            addCriterion("SEX_NAME <>", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameGreaterThan(String value) {
            addCriterion("SEX_NAME >", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameGreaterThanOrEqualTo(String value) {
            addCriterion("SEX_NAME >=", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameLessThan(String value) {
            addCriterion("SEX_NAME <", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameLessThanOrEqualTo(String value) {
            addCriterion("SEX_NAME <=", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameLike(String value) {
            addCriterion("SEX_NAME like", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotLike(String value) {
            addCriterion("SEX_NAME not like", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameIn(List<String> values) {
            addCriterion("SEX_NAME in", values, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotIn(List<String> values) {
            addCriterion("SEX_NAME not in", values, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameBetween(String value1, String value2) {
            addCriterion("SEX_NAME between", value1, value2, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotBetween(String value1, String value2) {
            addCriterion("SEX_NAME not between", value1, value2, "sexName");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeNameIsNull() {
            addCriterion("GRADUATION_SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeNameIsNotNull() {
            addCriterion("GRADUATION_SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeNameEqualTo(String value) {
            addCriterion("GRADUATION_SPE_NAME =", value, "graduationSpeName");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeNameNotEqualTo(String value) {
            addCriterion("GRADUATION_SPE_NAME <>", value, "graduationSpeName");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeNameGreaterThan(String value) {
            addCriterion("GRADUATION_SPE_NAME >", value, "graduationSpeName");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATION_SPE_NAME >=", value, "graduationSpeName");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeNameLessThan(String value) {
            addCriterion("GRADUATION_SPE_NAME <", value, "graduationSpeName");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeNameLessThanOrEqualTo(String value) {
            addCriterion("GRADUATION_SPE_NAME <=", value, "graduationSpeName");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeNameLike(String value) {
            addCriterion("GRADUATION_SPE_NAME like", value, "graduationSpeName");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeNameNotLike(String value) {
            addCriterion("GRADUATION_SPE_NAME not like", value, "graduationSpeName");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeNameIn(List<String> values) {
            addCriterion("GRADUATION_SPE_NAME in", values, "graduationSpeName");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeNameNotIn(List<String> values) {
            addCriterion("GRADUATION_SPE_NAME not in", values, "graduationSpeName");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeNameBetween(String value1, String value2) {
            addCriterion("GRADUATION_SPE_NAME between", value1, value2, "graduationSpeName");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeNameNotBetween(String value1, String value2) {
            addCriterion("GRADUATION_SPE_NAME not between", value1, value2, "graduationSpeName");
            return (Criteria) this;
        }

        public Criteria andGraduationUnitIsNull() {
            addCriterion("GRADUATION_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andGraduationUnitIsNotNull() {
            addCriterion("GRADUATION_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationUnitEqualTo(String value) {
            addCriterion("GRADUATION_UNIT =", value, "graduationUnit");
            return (Criteria) this;
        }

        public Criteria andGraduationUnitNotEqualTo(String value) {
            addCriterion("GRADUATION_UNIT <>", value, "graduationUnit");
            return (Criteria) this;
        }

        public Criteria andGraduationUnitGreaterThan(String value) {
            addCriterion("GRADUATION_UNIT >", value, "graduationUnit");
            return (Criteria) this;
        }

        public Criteria andGraduationUnitGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATION_UNIT >=", value, "graduationUnit");
            return (Criteria) this;
        }

        public Criteria andGraduationUnitLessThan(String value) {
            addCriterion("GRADUATION_UNIT <", value, "graduationUnit");
            return (Criteria) this;
        }

        public Criteria andGraduationUnitLessThanOrEqualTo(String value) {
            addCriterion("GRADUATION_UNIT <=", value, "graduationUnit");
            return (Criteria) this;
        }

        public Criteria andGraduationUnitLike(String value) {
            addCriterion("GRADUATION_UNIT like", value, "graduationUnit");
            return (Criteria) this;
        }

        public Criteria andGraduationUnitNotLike(String value) {
            addCriterion("GRADUATION_UNIT not like", value, "graduationUnit");
            return (Criteria) this;
        }

        public Criteria andGraduationUnitIn(List<String> values) {
            addCriterion("GRADUATION_UNIT in", values, "graduationUnit");
            return (Criteria) this;
        }

        public Criteria andGraduationUnitNotIn(List<String> values) {
            addCriterion("GRADUATION_UNIT not in", values, "graduationUnit");
            return (Criteria) this;
        }

        public Criteria andGraduationUnitBetween(String value1, String value2) {
            addCriterion("GRADUATION_UNIT between", value1, value2, "graduationUnit");
            return (Criteria) this;
        }

        public Criteria andGraduationUnitNotBetween(String value1, String value2) {
            addCriterion("GRADUATION_UNIT not between", value1, value2, "graduationUnit");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNull() {
            addCriterion("CONTACT_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIsNotNull() {
            addCriterion("CONTACT_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andContactPhoneEqualTo(String value) {
            addCriterion("CONTACT_PHONE =", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotEqualTo(String value) {
            addCriterion("CONTACT_PHONE <>", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThan(String value) {
            addCriterion("CONTACT_PHONE >", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_PHONE >=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThan(String value) {
            addCriterion("CONTACT_PHONE <", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_PHONE <=", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneLike(String value) {
            addCriterion("CONTACT_PHONE like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotLike(String value) {
            addCriterion("CONTACT_PHONE not like", value, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneIn(List<String> values) {
            addCriterion("CONTACT_PHONE in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotIn(List<String> values) {
            addCriterion("CONTACT_PHONE not in", values, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneBetween(String value1, String value2) {
            addCriterion("CONTACT_PHONE between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactPhoneNotBetween(String value1, String value2) {
            addCriterion("CONTACT_PHONE not between", value1, value2, "contactPhone");
            return (Criteria) this;
        }

        public Criteria andContactEmailIsNull() {
            addCriterion("CONTACT_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andContactEmailIsNotNull() {
            addCriterion("CONTACT_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andContactEmailEqualTo(String value) {
            addCriterion("CONTACT_EMAIL =", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailNotEqualTo(String value) {
            addCriterion("CONTACT_EMAIL <>", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailGreaterThan(String value) {
            addCriterion("CONTACT_EMAIL >", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_EMAIL >=", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailLessThan(String value) {
            addCriterion("CONTACT_EMAIL <", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_EMAIL <=", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailLike(String value) {
            addCriterion("CONTACT_EMAIL like", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailNotLike(String value) {
            addCriterion("CONTACT_EMAIL not like", value, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailIn(List<String> values) {
            addCriterion("CONTACT_EMAIL in", values, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailNotIn(List<String> values) {
            addCriterion("CONTACT_EMAIL not in", values, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailBetween(String value1, String value2) {
            addCriterion("CONTACT_EMAIL between", value1, value2, "contactEmail");
            return (Criteria) this;
        }

        public Criteria andContactEmailNotBetween(String value1, String value2) {
            addCriterion("CONTACT_EMAIL not between", value1, value2, "contactEmail");
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

        public Criteria andApplicationCategoryIdIsNull() {
            addCriterion("APPLICATION_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryIdIsNotNull() {
            addCriterion("APPLICATION_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryIdEqualTo(String value) {
            addCriterion("APPLICATION_CATEGORY_ID =", value, "applicationCategoryId");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryIdNotEqualTo(String value) {
            addCriterion("APPLICATION_CATEGORY_ID <>", value, "applicationCategoryId");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryIdGreaterThan(String value) {
            addCriterion("APPLICATION_CATEGORY_ID >", value, "applicationCategoryId");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("APPLICATION_CATEGORY_ID >=", value, "applicationCategoryId");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryIdLessThan(String value) {
            addCriterion("APPLICATION_CATEGORY_ID <", value, "applicationCategoryId");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("APPLICATION_CATEGORY_ID <=", value, "applicationCategoryId");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryIdLike(String value) {
            addCriterion("APPLICATION_CATEGORY_ID like", value, "applicationCategoryId");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryIdNotLike(String value) {
            addCriterion("APPLICATION_CATEGORY_ID not like", value, "applicationCategoryId");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryIdIn(List<String> values) {
            addCriterion("APPLICATION_CATEGORY_ID in", values, "applicationCategoryId");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryIdNotIn(List<String> values) {
            addCriterion("APPLICATION_CATEGORY_ID not in", values, "applicationCategoryId");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryIdBetween(String value1, String value2) {
            addCriterion("APPLICATION_CATEGORY_ID between", value1, value2, "applicationCategoryId");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryIdNotBetween(String value1, String value2) {
            addCriterion("APPLICATION_CATEGORY_ID not between", value1, value2, "applicationCategoryId");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryNameIsNull() {
            addCriterion("APPLICATION_CATEGORY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryNameIsNotNull() {
            addCriterion("APPLICATION_CATEGORY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryNameEqualTo(String value) {
            addCriterion("APPLICATION_CATEGORY_NAME =", value, "applicationCategoryName");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryNameNotEqualTo(String value) {
            addCriterion("APPLICATION_CATEGORY_NAME <>", value, "applicationCategoryName");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryNameGreaterThan(String value) {
            addCriterion("APPLICATION_CATEGORY_NAME >", value, "applicationCategoryName");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("APPLICATION_CATEGORY_NAME >=", value, "applicationCategoryName");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryNameLessThan(String value) {
            addCriterion("APPLICATION_CATEGORY_NAME <", value, "applicationCategoryName");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("APPLICATION_CATEGORY_NAME <=", value, "applicationCategoryName");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryNameLike(String value) {
            addCriterion("APPLICATION_CATEGORY_NAME like", value, "applicationCategoryName");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryNameNotLike(String value) {
            addCriterion("APPLICATION_CATEGORY_NAME not like", value, "applicationCategoryName");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryNameIn(List<String> values) {
            addCriterion("APPLICATION_CATEGORY_NAME in", values, "applicationCategoryName");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryNameNotIn(List<String> values) {
            addCriterion("APPLICATION_CATEGORY_NAME not in", values, "applicationCategoryName");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryNameBetween(String value1, String value2) {
            addCriterion("APPLICATION_CATEGORY_NAME between", value1, value2, "applicationCategoryName");
            return (Criteria) this;
        }

        public Criteria andApplicationCategoryNameNotBetween(String value1, String value2) {
            addCriterion("APPLICATION_CATEGORY_NAME not between", value1, value2, "applicationCategoryName");
            return (Criteria) this;
        }

        public Criteria andStuGradeIsNull() {
            addCriterion("STU_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andStuGradeIsNotNull() {
            addCriterion("STU_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andStuGradeEqualTo(String value) {
            addCriterion("STU_GRADE =", value, "stuGrade");
            return (Criteria) this;
        }

        public Criteria andStuGradeNotEqualTo(String value) {
            addCriterion("STU_GRADE <>", value, "stuGrade");
            return (Criteria) this;
        }

        public Criteria andStuGradeGreaterThan(String value) {
            addCriterion("STU_GRADE >", value, "stuGrade");
            return (Criteria) this;
        }

        public Criteria andStuGradeGreaterThanOrEqualTo(String value) {
            addCriterion("STU_GRADE >=", value, "stuGrade");
            return (Criteria) this;
        }

        public Criteria andStuGradeLessThan(String value) {
            addCriterion("STU_GRADE <", value, "stuGrade");
            return (Criteria) this;
        }

        public Criteria andStuGradeLessThanOrEqualTo(String value) {
            addCriterion("STU_GRADE <=", value, "stuGrade");
            return (Criteria) this;
        }

        public Criteria andStuGradeLike(String value) {
            addCriterion("STU_GRADE like", value, "stuGrade");
            return (Criteria) this;
        }

        public Criteria andStuGradeNotLike(String value) {
            addCriterion("STU_GRADE not like", value, "stuGrade");
            return (Criteria) this;
        }

        public Criteria andStuGradeIn(List<String> values) {
            addCriterion("STU_GRADE in", values, "stuGrade");
            return (Criteria) this;
        }

        public Criteria andStuGradeNotIn(List<String> values) {
            addCriterion("STU_GRADE not in", values, "stuGrade");
            return (Criteria) this;
        }

        public Criteria andStuGradeBetween(String value1, String value2) {
            addCriterion("STU_GRADE between", value1, value2, "stuGrade");
            return (Criteria) this;
        }

        public Criteria andStuGradeNotBetween(String value1, String value2) {
            addCriterion("STU_GRADE not between", value1, value2, "stuGrade");
            return (Criteria) this;
        }

        public Criteria andSchoolRankingIsNull() {
            addCriterion("SCHOOL_RANKING is null");
            return (Criteria) this;
        }

        public Criteria andSchoolRankingIsNotNull() {
            addCriterion("SCHOOL_RANKING is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolRankingEqualTo(String value) {
            addCriterion("SCHOOL_RANKING =", value, "schoolRanking");
            return (Criteria) this;
        }

        public Criteria andSchoolRankingNotEqualTo(String value) {
            addCriterion("SCHOOL_RANKING <>", value, "schoolRanking");
            return (Criteria) this;
        }

        public Criteria andSchoolRankingGreaterThan(String value) {
            addCriterion("SCHOOL_RANKING >", value, "schoolRanking");
            return (Criteria) this;
        }

        public Criteria andSchoolRankingGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOL_RANKING >=", value, "schoolRanking");
            return (Criteria) this;
        }

        public Criteria andSchoolRankingLessThan(String value) {
            addCriterion("SCHOOL_RANKING <", value, "schoolRanking");
            return (Criteria) this;
        }

        public Criteria andSchoolRankingLessThanOrEqualTo(String value) {
            addCriterion("SCHOOL_RANKING <=", value, "schoolRanking");
            return (Criteria) this;
        }

        public Criteria andSchoolRankingLike(String value) {
            addCriterion("SCHOOL_RANKING like", value, "schoolRanking");
            return (Criteria) this;
        }

        public Criteria andSchoolRankingNotLike(String value) {
            addCriterion("SCHOOL_RANKING not like", value, "schoolRanking");
            return (Criteria) this;
        }

        public Criteria andSchoolRankingIn(List<String> values) {
            addCriterion("SCHOOL_RANKING in", values, "schoolRanking");
            return (Criteria) this;
        }

        public Criteria andSchoolRankingNotIn(List<String> values) {
            addCriterion("SCHOOL_RANKING not in", values, "schoolRanking");
            return (Criteria) this;
        }

        public Criteria andSchoolRankingBetween(String value1, String value2) {
            addCriterion("SCHOOL_RANKING between", value1, value2, "schoolRanking");
            return (Criteria) this;
        }

        public Criteria andSchoolRankingNotBetween(String value1, String value2) {
            addCriterion("SCHOOL_RANKING not between", value1, value2, "schoolRanking");
            return (Criteria) this;
        }

        public Criteria andCollegeRankgingIsNull() {
            addCriterion("COLLEGE_RANKGING is null");
            return (Criteria) this;
        }

        public Criteria andCollegeRankgingIsNotNull() {
            addCriterion("COLLEGE_RANKGING is not null");
            return (Criteria) this;
        }

        public Criteria andCollegeRankgingEqualTo(String value) {
            addCriterion("COLLEGE_RANKGING =", value, "collegeRankging");
            return (Criteria) this;
        }

        public Criteria andCollegeRankgingNotEqualTo(String value) {
            addCriterion("COLLEGE_RANKGING <>", value, "collegeRankging");
            return (Criteria) this;
        }

        public Criteria andCollegeRankgingGreaterThan(String value) {
            addCriterion("COLLEGE_RANKGING >", value, "collegeRankging");
            return (Criteria) this;
        }

        public Criteria andCollegeRankgingGreaterThanOrEqualTo(String value) {
            addCriterion("COLLEGE_RANKGING >=", value, "collegeRankging");
            return (Criteria) this;
        }

        public Criteria andCollegeRankgingLessThan(String value) {
            addCriterion("COLLEGE_RANKGING <", value, "collegeRankging");
            return (Criteria) this;
        }

        public Criteria andCollegeRankgingLessThanOrEqualTo(String value) {
            addCriterion("COLLEGE_RANKGING <=", value, "collegeRankging");
            return (Criteria) this;
        }

        public Criteria andCollegeRankgingLike(String value) {
            addCriterion("COLLEGE_RANKGING like", value, "collegeRankging");
            return (Criteria) this;
        }

        public Criteria andCollegeRankgingNotLike(String value) {
            addCriterion("COLLEGE_RANKGING not like", value, "collegeRankging");
            return (Criteria) this;
        }

        public Criteria andCollegeRankgingIn(List<String> values) {
            addCriterion("COLLEGE_RANKGING in", values, "collegeRankging");
            return (Criteria) this;
        }

        public Criteria andCollegeRankgingNotIn(List<String> values) {
            addCriterion("COLLEGE_RANKGING not in", values, "collegeRankging");
            return (Criteria) this;
        }

        public Criteria andCollegeRankgingBetween(String value1, String value2) {
            addCriterion("COLLEGE_RANKGING between", value1, value2, "collegeRankging");
            return (Criteria) this;
        }

        public Criteria andCollegeRankgingNotBetween(String value1, String value2) {
            addCriterion("COLLEGE_RANKGING not between", value1, value2, "collegeRankging");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusIdIsNull() {
            addCriterion("REGISTER_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusIdIsNotNull() {
            addCriterion("REGISTER_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusIdEqualTo(String value) {
            addCriterion("REGISTER_STATUS_ID =", value, "registerStatusId");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusIdNotEqualTo(String value) {
            addCriterion("REGISTER_STATUS_ID <>", value, "registerStatusId");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusIdGreaterThan(String value) {
            addCriterion("REGISTER_STATUS_ID >", value, "registerStatusId");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("REGISTER_STATUS_ID >=", value, "registerStatusId");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusIdLessThan(String value) {
            addCriterion("REGISTER_STATUS_ID <", value, "registerStatusId");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusIdLessThanOrEqualTo(String value) {
            addCriterion("REGISTER_STATUS_ID <=", value, "registerStatusId");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusIdLike(String value) {
            addCriterion("REGISTER_STATUS_ID like", value, "registerStatusId");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusIdNotLike(String value) {
            addCriterion("REGISTER_STATUS_ID not like", value, "registerStatusId");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusIdIn(List<String> values) {
            addCriterion("REGISTER_STATUS_ID in", values, "registerStatusId");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusIdNotIn(List<String> values) {
            addCriterion("REGISTER_STATUS_ID not in", values, "registerStatusId");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusIdBetween(String value1, String value2) {
            addCriterion("REGISTER_STATUS_ID between", value1, value2, "registerStatusId");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusIdNotBetween(String value1, String value2) {
            addCriterion("REGISTER_STATUS_ID not between", value1, value2, "registerStatusId");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusNameIsNull() {
            addCriterion("REGISTER_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusNameIsNotNull() {
            addCriterion("REGISTER_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusNameEqualTo(String value) {
            addCriterion("REGISTER_STATUS_NAME =", value, "registerStatusName");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusNameNotEqualTo(String value) {
            addCriterion("REGISTER_STATUS_NAME <>", value, "registerStatusName");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusNameGreaterThan(String value) {
            addCriterion("REGISTER_STATUS_NAME >", value, "registerStatusName");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("REGISTER_STATUS_NAME >=", value, "registerStatusName");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusNameLessThan(String value) {
            addCriterion("REGISTER_STATUS_NAME <", value, "registerStatusName");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusNameLessThanOrEqualTo(String value) {
            addCriterion("REGISTER_STATUS_NAME <=", value, "registerStatusName");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusNameLike(String value) {
            addCriterion("REGISTER_STATUS_NAME like", value, "registerStatusName");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusNameNotLike(String value) {
            addCriterion("REGISTER_STATUS_NAME not like", value, "registerStatusName");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusNameIn(List<String> values) {
            addCriterion("REGISTER_STATUS_NAME in", values, "registerStatusName");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusNameNotIn(List<String> values) {
            addCriterion("REGISTER_STATUS_NAME not in", values, "registerStatusName");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusNameBetween(String value1, String value2) {
            addCriterion("REGISTER_STATUS_NAME between", value1, value2, "registerStatusName");
            return (Criteria) this;
        }

        public Criteria andRegisterStatusNameNotBetween(String value1, String value2) {
            addCriterion("REGISTER_STATUS_NAME not between", value1, value2, "registerStatusName");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdIsNull() {
            addCriterion("SWAP_BATCH_ID is null");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdIsNotNull() {
            addCriterion("SWAP_BATCH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdEqualTo(String value) {
            addCriterion("SWAP_BATCH_ID =", value, "swapBatchId");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdNotEqualTo(String value) {
            addCriterion("SWAP_BATCH_ID <>", value, "swapBatchId");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdGreaterThan(String value) {
            addCriterion("SWAP_BATCH_ID >", value, "swapBatchId");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdGreaterThanOrEqualTo(String value) {
            addCriterion("SWAP_BATCH_ID >=", value, "swapBatchId");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdLessThan(String value) {
            addCriterion("SWAP_BATCH_ID <", value, "swapBatchId");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdLessThanOrEqualTo(String value) {
            addCriterion("SWAP_BATCH_ID <=", value, "swapBatchId");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdLike(String value) {
            addCriterion("SWAP_BATCH_ID like", value, "swapBatchId");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdNotLike(String value) {
            addCriterion("SWAP_BATCH_ID not like", value, "swapBatchId");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdIn(List<String> values) {
            addCriterion("SWAP_BATCH_ID in", values, "swapBatchId");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdNotIn(List<String> values) {
            addCriterion("SWAP_BATCH_ID not in", values, "swapBatchId");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdBetween(String value1, String value2) {
            addCriterion("SWAP_BATCH_ID between", value1, value2, "swapBatchId");
            return (Criteria) this;
        }

        public Criteria andSwapBatchIdNotBetween(String value1, String value2) {
            addCriterion("SWAP_BATCH_ID not between", value1, value2, "swapBatchId");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameIsNull() {
            addCriterion("SWAP_BATCH_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameIsNotNull() {
            addCriterion("SWAP_BATCH_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameEqualTo(String value) {
            addCriterion("SWAP_BATCH_NAME =", value, "swapBatchName");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameNotEqualTo(String value) {
            addCriterion("SWAP_BATCH_NAME <>", value, "swapBatchName");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameGreaterThan(String value) {
            addCriterion("SWAP_BATCH_NAME >", value, "swapBatchName");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameGreaterThanOrEqualTo(String value) {
            addCriterion("SWAP_BATCH_NAME >=", value, "swapBatchName");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameLessThan(String value) {
            addCriterion("SWAP_BATCH_NAME <", value, "swapBatchName");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameLessThanOrEqualTo(String value) {
            addCriterion("SWAP_BATCH_NAME <=", value, "swapBatchName");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameLike(String value) {
            addCriterion("SWAP_BATCH_NAME like", value, "swapBatchName");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameNotLike(String value) {
            addCriterion("SWAP_BATCH_NAME not like", value, "swapBatchName");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameIn(List<String> values) {
            addCriterion("SWAP_BATCH_NAME in", values, "swapBatchName");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameNotIn(List<String> values) {
            addCriterion("SWAP_BATCH_NAME not in", values, "swapBatchName");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameBetween(String value1, String value2) {
            addCriterion("SWAP_BATCH_NAME between", value1, value2, "swapBatchName");
            return (Criteria) this;
        }

        public Criteria andSwapBatchNameNotBetween(String value1, String value2) {
            addCriterion("SWAP_BATCH_NAME not between", value1, value2, "swapBatchName");
            return (Criteria) this;
        }

        public Criteria andGradeRankingPercentageIsNull() {
            addCriterion("GRADE_RANKING_PERCENTAGE is null");
            return (Criteria) this;
        }

        public Criteria andGradeRankingPercentageIsNotNull() {
            addCriterion("GRADE_RANKING_PERCENTAGE is not null");
            return (Criteria) this;
        }

        public Criteria andGradeRankingPercentageEqualTo(String value) {
            addCriterion("GRADE_RANKING_PERCENTAGE =", value, "gradeRankingPercentage");
            return (Criteria) this;
        }

        public Criteria andGradeRankingPercentageNotEqualTo(String value) {
            addCriterion("GRADE_RANKING_PERCENTAGE <>", value, "gradeRankingPercentage");
            return (Criteria) this;
        }

        public Criteria andGradeRankingPercentageGreaterThan(String value) {
            addCriterion("GRADE_RANKING_PERCENTAGE >", value, "gradeRankingPercentage");
            return (Criteria) this;
        }

        public Criteria andGradeRankingPercentageGreaterThanOrEqualTo(String value) {
            addCriterion("GRADE_RANKING_PERCENTAGE >=", value, "gradeRankingPercentage");
            return (Criteria) this;
        }

        public Criteria andGradeRankingPercentageLessThan(String value) {
            addCriterion("GRADE_RANKING_PERCENTAGE <", value, "gradeRankingPercentage");
            return (Criteria) this;
        }

        public Criteria andGradeRankingPercentageLessThanOrEqualTo(String value) {
            addCriterion("GRADE_RANKING_PERCENTAGE <=", value, "gradeRankingPercentage");
            return (Criteria) this;
        }

        public Criteria andGradeRankingPercentageLike(String value) {
            addCriterion("GRADE_RANKING_PERCENTAGE like", value, "gradeRankingPercentage");
            return (Criteria) this;
        }

        public Criteria andGradeRankingPercentageNotLike(String value) {
            addCriterion("GRADE_RANKING_PERCENTAGE not like", value, "gradeRankingPercentage");
            return (Criteria) this;
        }

        public Criteria andGradeRankingPercentageIn(List<String> values) {
            addCriterion("GRADE_RANKING_PERCENTAGE in", values, "gradeRankingPercentage");
            return (Criteria) this;
        }

        public Criteria andGradeRankingPercentageNotIn(List<String> values) {
            addCriterion("GRADE_RANKING_PERCENTAGE not in", values, "gradeRankingPercentage");
            return (Criteria) this;
        }

        public Criteria andGradeRankingPercentageBetween(String value1, String value2) {
            addCriterion("GRADE_RANKING_PERCENTAGE between", value1, value2, "gradeRankingPercentage");
            return (Criteria) this;
        }

        public Criteria andGradeRankingPercentageNotBetween(String value1, String value2) {
            addCriterion("GRADE_RANKING_PERCENTAGE not between", value1, value2, "gradeRankingPercentage");
            return (Criteria) this;
        }

        public Criteria andSixGradeIsNull() {
            addCriterion("SIX_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andSixGradeIsNotNull() {
            addCriterion("SIX_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andSixGradeEqualTo(String value) {
            addCriterion("SIX_GRADE =", value, "sixGrade");
            return (Criteria) this;
        }

        public Criteria andSixGradeNotEqualTo(String value) {
            addCriterion("SIX_GRADE <>", value, "sixGrade");
            return (Criteria) this;
        }

        public Criteria andSixGradeGreaterThan(String value) {
            addCriterion("SIX_GRADE >", value, "sixGrade");
            return (Criteria) this;
        }

        public Criteria andSixGradeGreaterThanOrEqualTo(String value) {
            addCriterion("SIX_GRADE >=", value, "sixGrade");
            return (Criteria) this;
        }

        public Criteria andSixGradeLessThan(String value) {
            addCriterion("SIX_GRADE <", value, "sixGrade");
            return (Criteria) this;
        }

        public Criteria andSixGradeLessThanOrEqualTo(String value) {
            addCriterion("SIX_GRADE <=", value, "sixGrade");
            return (Criteria) this;
        }

        public Criteria andSixGradeLike(String value) {
            addCriterion("SIX_GRADE like", value, "sixGrade");
            return (Criteria) this;
        }

        public Criteria andSixGradeNotLike(String value) {
            addCriterion("SIX_GRADE not like", value, "sixGrade");
            return (Criteria) this;
        }

        public Criteria andSixGradeIn(List<String> values) {
            addCriterion("SIX_GRADE in", values, "sixGrade");
            return (Criteria) this;
        }

        public Criteria andSixGradeNotIn(List<String> values) {
            addCriterion("SIX_GRADE not in", values, "sixGrade");
            return (Criteria) this;
        }

        public Criteria andSixGradeBetween(String value1, String value2) {
            addCriterion("SIX_GRADE between", value1, value2, "sixGrade");
            return (Criteria) this;
        }

        public Criteria andSixGradeNotBetween(String value1, String value2) {
            addCriterion("SIX_GRADE not between", value1, value2, "sixGrade");
            return (Criteria) this;
        }

        public Criteria andInstitutionTypeNameIsNull() {
            addCriterion("INSTITUTION_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andInstitutionTypeNameIsNotNull() {
            addCriterion("INSTITUTION_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andInstitutionTypeNameEqualTo(String value) {
            addCriterion("INSTITUTION_TYPE_NAME =", value, "institutionTypeName");
            return (Criteria) this;
        }

        public Criteria andInstitutionTypeNameNotEqualTo(String value) {
            addCriterion("INSTITUTION_TYPE_NAME <>", value, "institutionTypeName");
            return (Criteria) this;
        }

        public Criteria andInstitutionTypeNameGreaterThan(String value) {
            addCriterion("INSTITUTION_TYPE_NAME >", value, "institutionTypeName");
            return (Criteria) this;
        }

        public Criteria andInstitutionTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("INSTITUTION_TYPE_NAME >=", value, "institutionTypeName");
            return (Criteria) this;
        }

        public Criteria andInstitutionTypeNameLessThan(String value) {
            addCriterion("INSTITUTION_TYPE_NAME <", value, "institutionTypeName");
            return (Criteria) this;
        }

        public Criteria andInstitutionTypeNameLessThanOrEqualTo(String value) {
            addCriterion("INSTITUTION_TYPE_NAME <=", value, "institutionTypeName");
            return (Criteria) this;
        }

        public Criteria andInstitutionTypeNameLike(String value) {
            addCriterion("INSTITUTION_TYPE_NAME like", value, "institutionTypeName");
            return (Criteria) this;
        }

        public Criteria andInstitutionTypeNameNotLike(String value) {
            addCriterion("INSTITUTION_TYPE_NAME not like", value, "institutionTypeName");
            return (Criteria) this;
        }

        public Criteria andInstitutionTypeNameIn(List<String> values) {
            addCriterion("INSTITUTION_TYPE_NAME in", values, "institutionTypeName");
            return (Criteria) this;
        }

        public Criteria andInstitutionTypeNameNotIn(List<String> values) {
            addCriterion("INSTITUTION_TYPE_NAME not in", values, "institutionTypeName");
            return (Criteria) this;
        }

        public Criteria andInstitutionTypeNameBetween(String value1, String value2) {
            addCriterion("INSTITUTION_TYPE_NAME between", value1, value2, "institutionTypeName");
            return (Criteria) this;
        }

        public Criteria andInstitutionTypeNameNotBetween(String value1, String value2) {
            addCriterion("INSTITUTION_TYPE_NAME not between", value1, value2, "institutionTypeName");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryIsNull() {
            addCriterion("POLITICAL_THEORY is null");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryIsNotNull() {
            addCriterion("POLITICAL_THEORY is not null");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryEqualTo(String value) {
            addCriterion("POLITICAL_THEORY =", value, "politicalTheory");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryNotEqualTo(String value) {
            addCriterion("POLITICAL_THEORY <>", value, "politicalTheory");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryGreaterThan(String value) {
            addCriterion("POLITICAL_THEORY >", value, "politicalTheory");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryGreaterThanOrEqualTo(String value) {
            addCriterion("POLITICAL_THEORY >=", value, "politicalTheory");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryLessThan(String value) {
            addCriterion("POLITICAL_THEORY <", value, "politicalTheory");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryLessThanOrEqualTo(String value) {
            addCriterion("POLITICAL_THEORY <=", value, "politicalTheory");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryLike(String value) {
            addCriterion("POLITICAL_THEORY like", value, "politicalTheory");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryNotLike(String value) {
            addCriterion("POLITICAL_THEORY not like", value, "politicalTheory");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryIn(List<String> values) {
            addCriterion("POLITICAL_THEORY in", values, "politicalTheory");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryNotIn(List<String> values) {
            addCriterion("POLITICAL_THEORY not in", values, "politicalTheory");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryBetween(String value1, String value2) {
            addCriterion("POLITICAL_THEORY between", value1, value2, "politicalTheory");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryNotBetween(String value1, String value2) {
            addCriterion("POLITICAL_THEORY not between", value1, value2, "politicalTheory");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryGradeIsNull() {
            addCriterion("POLITICAL_THEORY_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryGradeIsNotNull() {
            addCriterion("POLITICAL_THEORY_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryGradeEqualTo(String value) {
            addCriterion("POLITICAL_THEORY_GRADE =", value, "politicalTheoryGrade");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryGradeNotEqualTo(String value) {
            addCriterion("POLITICAL_THEORY_GRADE <>", value, "politicalTheoryGrade");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryGradeGreaterThan(String value) {
            addCriterion("POLITICAL_THEORY_GRADE >", value, "politicalTheoryGrade");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryGradeGreaterThanOrEqualTo(String value) {
            addCriterion("POLITICAL_THEORY_GRADE >=", value, "politicalTheoryGrade");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryGradeLessThan(String value) {
            addCriterion("POLITICAL_THEORY_GRADE <", value, "politicalTheoryGrade");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryGradeLessThanOrEqualTo(String value) {
            addCriterion("POLITICAL_THEORY_GRADE <=", value, "politicalTheoryGrade");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryGradeLike(String value) {
            addCriterion("POLITICAL_THEORY_GRADE like", value, "politicalTheoryGrade");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryGradeNotLike(String value) {
            addCriterion("POLITICAL_THEORY_GRADE not like", value, "politicalTheoryGrade");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryGradeIn(List<String> values) {
            addCriterion("POLITICAL_THEORY_GRADE in", values, "politicalTheoryGrade");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryGradeNotIn(List<String> values) {
            addCriterion("POLITICAL_THEORY_GRADE not in", values, "politicalTheoryGrade");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryGradeBetween(String value1, String value2) {
            addCriterion("POLITICAL_THEORY_GRADE between", value1, value2, "politicalTheoryGrade");
            return (Criteria) this;
        }

        public Criteria andPoliticalTheoryGradeNotBetween(String value1, String value2) {
            addCriterion("POLITICAL_THEORY_GRADE not between", value1, value2, "politicalTheoryGrade");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageIsNull() {
            addCriterion("FOREIGN_LANGUAGE is null");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageIsNotNull() {
            addCriterion("FOREIGN_LANGUAGE is not null");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageEqualTo(String value) {
            addCriterion("FOREIGN_LANGUAGE =", value, "foreignLanguage");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageNotEqualTo(String value) {
            addCriterion("FOREIGN_LANGUAGE <>", value, "foreignLanguage");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageGreaterThan(String value) {
            addCriterion("FOREIGN_LANGUAGE >", value, "foreignLanguage");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageGreaterThanOrEqualTo(String value) {
            addCriterion("FOREIGN_LANGUAGE >=", value, "foreignLanguage");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageLessThan(String value) {
            addCriterion("FOREIGN_LANGUAGE <", value, "foreignLanguage");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageLessThanOrEqualTo(String value) {
            addCriterion("FOREIGN_LANGUAGE <=", value, "foreignLanguage");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageLike(String value) {
            addCriterion("FOREIGN_LANGUAGE like", value, "foreignLanguage");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageNotLike(String value) {
            addCriterion("FOREIGN_LANGUAGE not like", value, "foreignLanguage");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageIn(List<String> values) {
            addCriterion("FOREIGN_LANGUAGE in", values, "foreignLanguage");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageNotIn(List<String> values) {
            addCriterion("FOREIGN_LANGUAGE not in", values, "foreignLanguage");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageBetween(String value1, String value2) {
            addCriterion("FOREIGN_LANGUAGE between", value1, value2, "foreignLanguage");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageNotBetween(String value1, String value2) {
            addCriterion("FOREIGN_LANGUAGE not between", value1, value2, "foreignLanguage");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageGradeIsNull() {
            addCriterion("FOREIGN_LANGUAGE_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageGradeIsNotNull() {
            addCriterion("FOREIGN_LANGUAGE_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageGradeEqualTo(String value) {
            addCriterion("FOREIGN_LANGUAGE_GRADE =", value, "foreignLanguageGrade");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageGradeNotEqualTo(String value) {
            addCriterion("FOREIGN_LANGUAGE_GRADE <>", value, "foreignLanguageGrade");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageGradeGreaterThan(String value) {
            addCriterion("FOREIGN_LANGUAGE_GRADE >", value, "foreignLanguageGrade");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageGradeGreaterThanOrEqualTo(String value) {
            addCriterion("FOREIGN_LANGUAGE_GRADE >=", value, "foreignLanguageGrade");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageGradeLessThan(String value) {
            addCriterion("FOREIGN_LANGUAGE_GRADE <", value, "foreignLanguageGrade");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageGradeLessThanOrEqualTo(String value) {
            addCriterion("FOREIGN_LANGUAGE_GRADE <=", value, "foreignLanguageGrade");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageGradeLike(String value) {
            addCriterion("FOREIGN_LANGUAGE_GRADE like", value, "foreignLanguageGrade");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageGradeNotLike(String value) {
            addCriterion("FOREIGN_LANGUAGE_GRADE not like", value, "foreignLanguageGrade");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageGradeIn(List<String> values) {
            addCriterion("FOREIGN_LANGUAGE_GRADE in", values, "foreignLanguageGrade");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageGradeNotIn(List<String> values) {
            addCriterion("FOREIGN_LANGUAGE_GRADE not in", values, "foreignLanguageGrade");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageGradeBetween(String value1, String value2) {
            addCriterion("FOREIGN_LANGUAGE_GRADE between", value1, value2, "foreignLanguageGrade");
            return (Criteria) this;
        }

        public Criteria andForeignLanguageGradeNotBetween(String value1, String value2) {
            addCriterion("FOREIGN_LANGUAGE_GRADE not between", value1, value2, "foreignLanguageGrade");
            return (Criteria) this;
        }

        public Criteria andBusinessOneIsNull() {
            addCriterion("BUSINESS_ONE is null");
            return (Criteria) this;
        }

        public Criteria andBusinessOneIsNotNull() {
            addCriterion("BUSINESS_ONE is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessOneEqualTo(String value) {
            addCriterion("BUSINESS_ONE =", value, "businessOne");
            return (Criteria) this;
        }

        public Criteria andBusinessOneNotEqualTo(String value) {
            addCriterion("BUSINESS_ONE <>", value, "businessOne");
            return (Criteria) this;
        }

        public Criteria andBusinessOneGreaterThan(String value) {
            addCriterion("BUSINESS_ONE >", value, "businessOne");
            return (Criteria) this;
        }

        public Criteria andBusinessOneGreaterThanOrEqualTo(String value) {
            addCriterion("BUSINESS_ONE >=", value, "businessOne");
            return (Criteria) this;
        }

        public Criteria andBusinessOneLessThan(String value) {
            addCriterion("BUSINESS_ONE <", value, "businessOne");
            return (Criteria) this;
        }

        public Criteria andBusinessOneLessThanOrEqualTo(String value) {
            addCriterion("BUSINESS_ONE <=", value, "businessOne");
            return (Criteria) this;
        }

        public Criteria andBusinessOneLike(String value) {
            addCriterion("BUSINESS_ONE like", value, "businessOne");
            return (Criteria) this;
        }

        public Criteria andBusinessOneNotLike(String value) {
            addCriterion("BUSINESS_ONE not like", value, "businessOne");
            return (Criteria) this;
        }

        public Criteria andBusinessOneIn(List<String> values) {
            addCriterion("BUSINESS_ONE in", values, "businessOne");
            return (Criteria) this;
        }

        public Criteria andBusinessOneNotIn(List<String> values) {
            addCriterion("BUSINESS_ONE not in", values, "businessOne");
            return (Criteria) this;
        }

        public Criteria andBusinessOneBetween(String value1, String value2) {
            addCriterion("BUSINESS_ONE between", value1, value2, "businessOne");
            return (Criteria) this;
        }

        public Criteria andBusinessOneNotBetween(String value1, String value2) {
            addCriterion("BUSINESS_ONE not between", value1, value2, "businessOne");
            return (Criteria) this;
        }

        public Criteria andBusinessOneGradeIsNull() {
            addCriterion("BUSINESS_ONE_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andBusinessOneGradeIsNotNull() {
            addCriterion("BUSINESS_ONE_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessOneGradeEqualTo(String value) {
            addCriterion("BUSINESS_ONE_GRADE =", value, "businessOneGrade");
            return (Criteria) this;
        }

        public Criteria andBusinessOneGradeNotEqualTo(String value) {
            addCriterion("BUSINESS_ONE_GRADE <>", value, "businessOneGrade");
            return (Criteria) this;
        }

        public Criteria andBusinessOneGradeGreaterThan(String value) {
            addCriterion("BUSINESS_ONE_GRADE >", value, "businessOneGrade");
            return (Criteria) this;
        }

        public Criteria andBusinessOneGradeGreaterThanOrEqualTo(String value) {
            addCriterion("BUSINESS_ONE_GRADE >=", value, "businessOneGrade");
            return (Criteria) this;
        }

        public Criteria andBusinessOneGradeLessThan(String value) {
            addCriterion("BUSINESS_ONE_GRADE <", value, "businessOneGrade");
            return (Criteria) this;
        }

        public Criteria andBusinessOneGradeLessThanOrEqualTo(String value) {
            addCriterion("BUSINESS_ONE_GRADE <=", value, "businessOneGrade");
            return (Criteria) this;
        }

        public Criteria andBusinessOneGradeLike(String value) {
            addCriterion("BUSINESS_ONE_GRADE like", value, "businessOneGrade");
            return (Criteria) this;
        }

        public Criteria andBusinessOneGradeNotLike(String value) {
            addCriterion("BUSINESS_ONE_GRADE not like", value, "businessOneGrade");
            return (Criteria) this;
        }

        public Criteria andBusinessOneGradeIn(List<String> values) {
            addCriterion("BUSINESS_ONE_GRADE in", values, "businessOneGrade");
            return (Criteria) this;
        }

        public Criteria andBusinessOneGradeNotIn(List<String> values) {
            addCriterion("BUSINESS_ONE_GRADE not in", values, "businessOneGrade");
            return (Criteria) this;
        }

        public Criteria andBusinessOneGradeBetween(String value1, String value2) {
            addCriterion("BUSINESS_ONE_GRADE between", value1, value2, "businessOneGrade");
            return (Criteria) this;
        }

        public Criteria andBusinessOneGradeNotBetween(String value1, String value2) {
            addCriterion("BUSINESS_ONE_GRADE not between", value1, value2, "businessOneGrade");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoIsNull() {
            addCriterion("BUSINESS_TWO is null");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoIsNotNull() {
            addCriterion("BUSINESS_TWO is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoEqualTo(String value) {
            addCriterion("BUSINESS_TWO =", value, "businessTwo");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoNotEqualTo(String value) {
            addCriterion("BUSINESS_TWO <>", value, "businessTwo");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoGreaterThan(String value) {
            addCriterion("BUSINESS_TWO >", value, "businessTwo");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoGreaterThanOrEqualTo(String value) {
            addCriterion("BUSINESS_TWO >=", value, "businessTwo");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoLessThan(String value) {
            addCriterion("BUSINESS_TWO <", value, "businessTwo");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoLessThanOrEqualTo(String value) {
            addCriterion("BUSINESS_TWO <=", value, "businessTwo");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoLike(String value) {
            addCriterion("BUSINESS_TWO like", value, "businessTwo");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoNotLike(String value) {
            addCriterion("BUSINESS_TWO not like", value, "businessTwo");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoIn(List<String> values) {
            addCriterion("BUSINESS_TWO in", values, "businessTwo");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoNotIn(List<String> values) {
            addCriterion("BUSINESS_TWO not in", values, "businessTwo");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoBetween(String value1, String value2) {
            addCriterion("BUSINESS_TWO between", value1, value2, "businessTwo");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoNotBetween(String value1, String value2) {
            addCriterion("BUSINESS_TWO not between", value1, value2, "businessTwo");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoGradeIsNull() {
            addCriterion("BUSINESS_TWO_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoGradeIsNotNull() {
            addCriterion("BUSINESS_TWO_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoGradeEqualTo(String value) {
            addCriterion("BUSINESS_TWO_GRADE =", value, "businessTwoGrade");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoGradeNotEqualTo(String value) {
            addCriterion("BUSINESS_TWO_GRADE <>", value, "businessTwoGrade");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoGradeGreaterThan(String value) {
            addCriterion("BUSINESS_TWO_GRADE >", value, "businessTwoGrade");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoGradeGreaterThanOrEqualTo(String value) {
            addCriterion("BUSINESS_TWO_GRADE >=", value, "businessTwoGrade");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoGradeLessThan(String value) {
            addCriterion("BUSINESS_TWO_GRADE <", value, "businessTwoGrade");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoGradeLessThanOrEqualTo(String value) {
            addCriterion("BUSINESS_TWO_GRADE <=", value, "businessTwoGrade");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoGradeLike(String value) {
            addCriterion("BUSINESS_TWO_GRADE like", value, "businessTwoGrade");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoGradeNotLike(String value) {
            addCriterion("BUSINESS_TWO_GRADE not like", value, "businessTwoGrade");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoGradeIn(List<String> values) {
            addCriterion("BUSINESS_TWO_GRADE in", values, "businessTwoGrade");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoGradeNotIn(List<String> values) {
            addCriterion("BUSINESS_TWO_GRADE not in", values, "businessTwoGrade");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoGradeBetween(String value1, String value2) {
            addCriterion("BUSINESS_TWO_GRADE between", value1, value2, "businessTwoGrade");
            return (Criteria) this;
        }

        public Criteria andBusinessTwoGradeNotBetween(String value1, String value2) {
            addCriterion("BUSINESS_TWO_GRADE not between", value1, value2, "businessTwoGrade");
            return (Criteria) this;
        }

        public Criteria andSubjectOneIsNull() {
            addCriterion("SUBJECT_ONE is null");
            return (Criteria) this;
        }

        public Criteria andSubjectOneIsNotNull() {
            addCriterion("SUBJECT_ONE is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectOneEqualTo(String value) {
            addCriterion("SUBJECT_ONE =", value, "subjectOne");
            return (Criteria) this;
        }

        public Criteria andSubjectOneNotEqualTo(String value) {
            addCriterion("SUBJECT_ONE <>", value, "subjectOne");
            return (Criteria) this;
        }

        public Criteria andSubjectOneGreaterThan(String value) {
            addCriterion("SUBJECT_ONE >", value, "subjectOne");
            return (Criteria) this;
        }

        public Criteria andSubjectOneGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_ONE >=", value, "subjectOne");
            return (Criteria) this;
        }

        public Criteria andSubjectOneLessThan(String value) {
            addCriterion("SUBJECT_ONE <", value, "subjectOne");
            return (Criteria) this;
        }

        public Criteria andSubjectOneLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_ONE <=", value, "subjectOne");
            return (Criteria) this;
        }

        public Criteria andSubjectOneLike(String value) {
            addCriterion("SUBJECT_ONE like", value, "subjectOne");
            return (Criteria) this;
        }

        public Criteria andSubjectOneNotLike(String value) {
            addCriterion("SUBJECT_ONE not like", value, "subjectOne");
            return (Criteria) this;
        }

        public Criteria andSubjectOneIn(List<String> values) {
            addCriterion("SUBJECT_ONE in", values, "subjectOne");
            return (Criteria) this;
        }

        public Criteria andSubjectOneNotIn(List<String> values) {
            addCriterion("SUBJECT_ONE not in", values, "subjectOne");
            return (Criteria) this;
        }

        public Criteria andSubjectOneBetween(String value1, String value2) {
            addCriterion("SUBJECT_ONE between", value1, value2, "subjectOne");
            return (Criteria) this;
        }

        public Criteria andSubjectOneNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_ONE not between", value1, value2, "subjectOne");
            return (Criteria) this;
        }

        public Criteria andEnglishStandardGradeIsNull() {
            addCriterion("ENGLISH_STANDARD_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andEnglishStandardGradeIsNotNull() {
            addCriterion("ENGLISH_STANDARD_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andEnglishStandardGradeEqualTo(String value) {
            addCriterion("ENGLISH_STANDARD_GRADE =", value, "englishStandardGrade");
            return (Criteria) this;
        }

        public Criteria andEnglishStandardGradeNotEqualTo(String value) {
            addCriterion("ENGLISH_STANDARD_GRADE <>", value, "englishStandardGrade");
            return (Criteria) this;
        }

        public Criteria andEnglishStandardGradeGreaterThan(String value) {
            addCriterion("ENGLISH_STANDARD_GRADE >", value, "englishStandardGrade");
            return (Criteria) this;
        }

        public Criteria andEnglishStandardGradeGreaterThanOrEqualTo(String value) {
            addCriterion("ENGLISH_STANDARD_GRADE >=", value, "englishStandardGrade");
            return (Criteria) this;
        }

        public Criteria andEnglishStandardGradeLessThan(String value) {
            addCriterion("ENGLISH_STANDARD_GRADE <", value, "englishStandardGrade");
            return (Criteria) this;
        }

        public Criteria andEnglishStandardGradeLessThanOrEqualTo(String value) {
            addCriterion("ENGLISH_STANDARD_GRADE <=", value, "englishStandardGrade");
            return (Criteria) this;
        }

        public Criteria andEnglishStandardGradeLike(String value) {
            addCriterion("ENGLISH_STANDARD_GRADE like", value, "englishStandardGrade");
            return (Criteria) this;
        }

        public Criteria andEnglishStandardGradeNotLike(String value) {
            addCriterion("ENGLISH_STANDARD_GRADE not like", value, "englishStandardGrade");
            return (Criteria) this;
        }

        public Criteria andEnglishStandardGradeIn(List<String> values) {
            addCriterion("ENGLISH_STANDARD_GRADE in", values, "englishStandardGrade");
            return (Criteria) this;
        }

        public Criteria andEnglishStandardGradeNotIn(List<String> values) {
            addCriterion("ENGLISH_STANDARD_GRADE not in", values, "englishStandardGrade");
            return (Criteria) this;
        }

        public Criteria andEnglishStandardGradeBetween(String value1, String value2) {
            addCriterion("ENGLISH_STANDARD_GRADE between", value1, value2, "englishStandardGrade");
            return (Criteria) this;
        }

        public Criteria andEnglishStandardGradeNotBetween(String value1, String value2) {
            addCriterion("ENGLISH_STANDARD_GRADE not between", value1, value2, "englishStandardGrade");
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

        public Criteria andStuBirthdayIsNull() {
            addCriterion("STU_BIRTHDAY is null");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayIsNotNull() {
            addCriterion("STU_BIRTHDAY is not null");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayEqualTo(String value) {
            addCriterion("STU_BIRTHDAY =", value, "stuBirthday");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayNotEqualTo(String value) {
            addCriterion("STU_BIRTHDAY <>", value, "stuBirthday");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayGreaterThan(String value) {
            addCriterion("STU_BIRTHDAY >", value, "stuBirthday");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayGreaterThanOrEqualTo(String value) {
            addCriterion("STU_BIRTHDAY >=", value, "stuBirthday");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayLessThan(String value) {
            addCriterion("STU_BIRTHDAY <", value, "stuBirthday");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayLessThanOrEqualTo(String value) {
            addCriterion("STU_BIRTHDAY <=", value, "stuBirthday");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayLike(String value) {
            addCriterion("STU_BIRTHDAY like", value, "stuBirthday");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayNotLike(String value) {
            addCriterion("STU_BIRTHDAY not like", value, "stuBirthday");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayIn(List<String> values) {
            addCriterion("STU_BIRTHDAY in", values, "stuBirthday");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayNotIn(List<String> values) {
            addCriterion("STU_BIRTHDAY not in", values, "stuBirthday");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayBetween(String value1, String value2) {
            addCriterion("STU_BIRTHDAY between", value1, value2, "stuBirthday");
            return (Criteria) this;
        }

        public Criteria andStuBirthdayNotBetween(String value1, String value2) {
            addCriterion("STU_BIRTHDAY not between", value1, value2, "stuBirthday");
            return (Criteria) this;
        }

        public Criteria andFwhFlowIsNull() {
            addCriterion("FWH_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andFwhFlowIsNotNull() {
            addCriterion("FWH_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andFwhFlowEqualTo(String value) {
            addCriterion("FWH_FLOW =", value, "fwhFlow");
            return (Criteria) this;
        }

        public Criteria andFwhFlowNotEqualTo(String value) {
            addCriterion("FWH_FLOW <>", value, "fwhFlow");
            return (Criteria) this;
        }

        public Criteria andFwhFlowGreaterThan(String value) {
            addCriterion("FWH_FLOW >", value, "fwhFlow");
            return (Criteria) this;
        }

        public Criteria andFwhFlowGreaterThanOrEqualTo(String value) {
            addCriterion("FWH_FLOW >=", value, "fwhFlow");
            return (Criteria) this;
        }

        public Criteria andFwhFlowLessThan(String value) {
            addCriterion("FWH_FLOW <", value, "fwhFlow");
            return (Criteria) this;
        }

        public Criteria andFwhFlowLessThanOrEqualTo(String value) {
            addCriterion("FWH_FLOW <=", value, "fwhFlow");
            return (Criteria) this;
        }

        public Criteria andFwhFlowLike(String value) {
            addCriterion("FWH_FLOW like", value, "fwhFlow");
            return (Criteria) this;
        }

        public Criteria andFwhFlowNotLike(String value) {
            addCriterion("FWH_FLOW not like", value, "fwhFlow");
            return (Criteria) this;
        }

        public Criteria andFwhFlowIn(List<String> values) {
            addCriterion("FWH_FLOW in", values, "fwhFlow");
            return (Criteria) this;
        }

        public Criteria andFwhFlowNotIn(List<String> values) {
            addCriterion("FWH_FLOW not in", values, "fwhFlow");
            return (Criteria) this;
        }

        public Criteria andFwhFlowBetween(String value1, String value2) {
            addCriterion("FWH_FLOW between", value1, value2, "fwhFlow");
            return (Criteria) this;
        }

        public Criteria andFwhFlowNotBetween(String value1, String value2) {
            addCriterion("FWH_FLOW not between", value1, value2, "fwhFlow");
            return (Criteria) this;
        }

        public Criteria andFwhNameIsNull() {
            addCriterion("FWH_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFwhNameIsNotNull() {
            addCriterion("FWH_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFwhNameEqualTo(String value) {
            addCriterion("FWH_NAME =", value, "fwhName");
            return (Criteria) this;
        }

        public Criteria andFwhNameNotEqualTo(String value) {
            addCriterion("FWH_NAME <>", value, "fwhName");
            return (Criteria) this;
        }

        public Criteria andFwhNameGreaterThan(String value) {
            addCriterion("FWH_NAME >", value, "fwhName");
            return (Criteria) this;
        }

        public Criteria andFwhNameGreaterThanOrEqualTo(String value) {
            addCriterion("FWH_NAME >=", value, "fwhName");
            return (Criteria) this;
        }

        public Criteria andFwhNameLessThan(String value) {
            addCriterion("FWH_NAME <", value, "fwhName");
            return (Criteria) this;
        }

        public Criteria andFwhNameLessThanOrEqualTo(String value) {
            addCriterion("FWH_NAME <=", value, "fwhName");
            return (Criteria) this;
        }

        public Criteria andFwhNameLike(String value) {
            addCriterion("FWH_NAME like", value, "fwhName");
            return (Criteria) this;
        }

        public Criteria andFwhNameNotLike(String value) {
            addCriterion("FWH_NAME not like", value, "fwhName");
            return (Criteria) this;
        }

        public Criteria andFwhNameIn(List<String> values) {
            addCriterion("FWH_NAME in", values, "fwhName");
            return (Criteria) this;
        }

        public Criteria andFwhNameNotIn(List<String> values) {
            addCriterion("FWH_NAME not in", values, "fwhName");
            return (Criteria) this;
        }

        public Criteria andFwhNameBetween(String value1, String value2) {
            addCriterion("FWH_NAME between", value1, value2, "fwhName");
            return (Criteria) this;
        }

        public Criteria andFwhNameNotBetween(String value1, String value2) {
            addCriterion("FWH_NAME not between", value1, value2, "fwhName");
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