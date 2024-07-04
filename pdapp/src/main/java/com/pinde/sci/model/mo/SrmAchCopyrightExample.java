package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class SrmAchCopyrightExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SrmAchCopyrightExample() {
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

        public Criteria andCopyrightFlowIsNull() {
            addCriterion("COPYRIGHT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andCopyrightFlowIsNotNull() {
            addCriterion("COPYRIGHT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andCopyrightFlowEqualTo(String value) {
            addCriterion("COPYRIGHT_FLOW =", value, "copyrightFlow");
            return (Criteria) this;
        }

        public Criteria andCopyrightFlowNotEqualTo(String value) {
            addCriterion("COPYRIGHT_FLOW <>", value, "copyrightFlow");
            return (Criteria) this;
        }

        public Criteria andCopyrightFlowGreaterThan(String value) {
            addCriterion("COPYRIGHT_FLOW >", value, "copyrightFlow");
            return (Criteria) this;
        }

        public Criteria andCopyrightFlowGreaterThanOrEqualTo(String value) {
            addCriterion("COPYRIGHT_FLOW >=", value, "copyrightFlow");
            return (Criteria) this;
        }

        public Criteria andCopyrightFlowLessThan(String value) {
            addCriterion("COPYRIGHT_FLOW <", value, "copyrightFlow");
            return (Criteria) this;
        }

        public Criteria andCopyrightFlowLessThanOrEqualTo(String value) {
            addCriterion("COPYRIGHT_FLOW <=", value, "copyrightFlow");
            return (Criteria) this;
        }

        public Criteria andCopyrightFlowLike(String value) {
            addCriterion("COPYRIGHT_FLOW like", value, "copyrightFlow");
            return (Criteria) this;
        }

        public Criteria andCopyrightFlowNotLike(String value) {
            addCriterion("COPYRIGHT_FLOW not like", value, "copyrightFlow");
            return (Criteria) this;
        }

        public Criteria andCopyrightFlowIn(List<String> values) {
            addCriterion("COPYRIGHT_FLOW in", values, "copyrightFlow");
            return (Criteria) this;
        }

        public Criteria andCopyrightFlowNotIn(List<String> values) {
            addCriterion("COPYRIGHT_FLOW not in", values, "copyrightFlow");
            return (Criteria) this;
        }

        public Criteria andCopyrightFlowBetween(String value1, String value2) {
            addCriterion("COPYRIGHT_FLOW between", value1, value2, "copyrightFlow");
            return (Criteria) this;
        }

        public Criteria andCopyrightFlowNotBetween(String value1, String value2) {
            addCriterion("COPYRIGHT_FLOW not between", value1, value2, "copyrightFlow");
            return (Criteria) this;
        }

        public Criteria andCopyrightCodeIsNull() {
            addCriterion("COPYRIGHT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCopyrightCodeIsNotNull() {
            addCriterion("COPYRIGHT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCopyrightCodeEqualTo(String value) {
            addCriterion("COPYRIGHT_CODE =", value, "copyrightCode");
            return (Criteria) this;
        }

        public Criteria andCopyrightCodeNotEqualTo(String value) {
            addCriterion("COPYRIGHT_CODE <>", value, "copyrightCode");
            return (Criteria) this;
        }

        public Criteria andCopyrightCodeGreaterThan(String value) {
            addCriterion("COPYRIGHT_CODE >", value, "copyrightCode");
            return (Criteria) this;
        }

        public Criteria andCopyrightCodeGreaterThanOrEqualTo(String value) {
            addCriterion("COPYRIGHT_CODE >=", value, "copyrightCode");
            return (Criteria) this;
        }

        public Criteria andCopyrightCodeLessThan(String value) {
            addCriterion("COPYRIGHT_CODE <", value, "copyrightCode");
            return (Criteria) this;
        }

        public Criteria andCopyrightCodeLessThanOrEqualTo(String value) {
            addCriterion("COPYRIGHT_CODE <=", value, "copyrightCode");
            return (Criteria) this;
        }

        public Criteria andCopyrightCodeLike(String value) {
            addCriterion("COPYRIGHT_CODE like", value, "copyrightCode");
            return (Criteria) this;
        }

        public Criteria andCopyrightCodeNotLike(String value) {
            addCriterion("COPYRIGHT_CODE not like", value, "copyrightCode");
            return (Criteria) this;
        }

        public Criteria andCopyrightCodeIn(List<String> values) {
            addCriterion("COPYRIGHT_CODE in", values, "copyrightCode");
            return (Criteria) this;
        }

        public Criteria andCopyrightCodeNotIn(List<String> values) {
            addCriterion("COPYRIGHT_CODE not in", values, "copyrightCode");
            return (Criteria) this;
        }

        public Criteria andCopyrightCodeBetween(String value1, String value2) {
            addCriterion("COPYRIGHT_CODE between", value1, value2, "copyrightCode");
            return (Criteria) this;
        }

        public Criteria andCopyrightCodeNotBetween(String value1, String value2) {
            addCriterion("COPYRIGHT_CODE not between", value1, value2, "copyrightCode");
            return (Criteria) this;
        }

        public Criteria andCopyrightNameIsNull() {
            addCriterion("COPYRIGHT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCopyrightNameIsNotNull() {
            addCriterion("COPYRIGHT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCopyrightNameEqualTo(String value) {
            addCriterion("COPYRIGHT_NAME =", value, "copyrightName");
            return (Criteria) this;
        }

        public Criteria andCopyrightNameNotEqualTo(String value) {
            addCriterion("COPYRIGHT_NAME <>", value, "copyrightName");
            return (Criteria) this;
        }

        public Criteria andCopyrightNameGreaterThan(String value) {
            addCriterion("COPYRIGHT_NAME >", value, "copyrightName");
            return (Criteria) this;
        }

        public Criteria andCopyrightNameGreaterThanOrEqualTo(String value) {
            addCriterion("COPYRIGHT_NAME >=", value, "copyrightName");
            return (Criteria) this;
        }

        public Criteria andCopyrightNameLessThan(String value) {
            addCriterion("COPYRIGHT_NAME <", value, "copyrightName");
            return (Criteria) this;
        }

        public Criteria andCopyrightNameLessThanOrEqualTo(String value) {
            addCriterion("COPYRIGHT_NAME <=", value, "copyrightName");
            return (Criteria) this;
        }

        public Criteria andCopyrightNameLike(String value) {
            addCriterion("COPYRIGHT_NAME like", value, "copyrightName");
            return (Criteria) this;
        }

        public Criteria andCopyrightNameNotLike(String value) {
            addCriterion("COPYRIGHT_NAME not like", value, "copyrightName");
            return (Criteria) this;
        }

        public Criteria andCopyrightNameIn(List<String> values) {
            addCriterion("COPYRIGHT_NAME in", values, "copyrightName");
            return (Criteria) this;
        }

        public Criteria andCopyrightNameNotIn(List<String> values) {
            addCriterion("COPYRIGHT_NAME not in", values, "copyrightName");
            return (Criteria) this;
        }

        public Criteria andCopyrightNameBetween(String value1, String value2) {
            addCriterion("COPYRIGHT_NAME between", value1, value2, "copyrightName");
            return (Criteria) this;
        }

        public Criteria andCopyrightNameNotBetween(String value1, String value2) {
            addCriterion("COPYRIGHT_NAME not between", value1, value2, "copyrightName");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdIsNull() {
            addCriterion("BELONG_ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdIsNotNull() {
            addCriterion("BELONG_ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdEqualTo(String value) {
            addCriterion("BELONG_ORG_ID =", value, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdNotEqualTo(String value) {
            addCriterion("BELONG_ORG_ID <>", value, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdGreaterThan(String value) {
            addCriterion("BELONG_ORG_ID >", value, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("BELONG_ORG_ID >=", value, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdLessThan(String value) {
            addCriterion("BELONG_ORG_ID <", value, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdLessThanOrEqualTo(String value) {
            addCriterion("BELONG_ORG_ID <=", value, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdLike(String value) {
            addCriterion("BELONG_ORG_ID like", value, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdNotLike(String value) {
            addCriterion("BELONG_ORG_ID not like", value, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdIn(List<String> values) {
            addCriterion("BELONG_ORG_ID in", values, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdNotIn(List<String> values) {
            addCriterion("BELONG_ORG_ID not in", values, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdBetween(String value1, String value2) {
            addCriterion("BELONG_ORG_ID between", value1, value2, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgIdNotBetween(String value1, String value2) {
            addCriterion("BELONG_ORG_ID not between", value1, value2, "belongOrgId");
            return (Criteria) this;
        }

        public Criteria andBelongOrgNameIsNull() {
            addCriterion("BELONG_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBelongOrgNameIsNotNull() {
            addCriterion("BELONG_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBelongOrgNameEqualTo(String value) {
            addCriterion("BELONG_ORG_NAME =", value, "belongOrgName");
            return (Criteria) this;
        }

        public Criteria andBelongOrgNameNotEqualTo(String value) {
            addCriterion("BELONG_ORG_NAME <>", value, "belongOrgName");
            return (Criteria) this;
        }

        public Criteria andBelongOrgNameGreaterThan(String value) {
            addCriterion("BELONG_ORG_NAME >", value, "belongOrgName");
            return (Criteria) this;
        }

        public Criteria andBelongOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("BELONG_ORG_NAME >=", value, "belongOrgName");
            return (Criteria) this;
        }

        public Criteria andBelongOrgNameLessThan(String value) {
            addCriterion("BELONG_ORG_NAME <", value, "belongOrgName");
            return (Criteria) this;
        }

        public Criteria andBelongOrgNameLessThanOrEqualTo(String value) {
            addCriterion("BELONG_ORG_NAME <=", value, "belongOrgName");
            return (Criteria) this;
        }

        public Criteria andBelongOrgNameLike(String value) {
            addCriterion("BELONG_ORG_NAME like", value, "belongOrgName");
            return (Criteria) this;
        }

        public Criteria andBelongOrgNameNotLike(String value) {
            addCriterion("BELONG_ORG_NAME not like", value, "belongOrgName");
            return (Criteria) this;
        }

        public Criteria andBelongOrgNameIn(List<String> values) {
            addCriterion("BELONG_ORG_NAME in", values, "belongOrgName");
            return (Criteria) this;
        }

        public Criteria andBelongOrgNameNotIn(List<String> values) {
            addCriterion("BELONG_ORG_NAME not in", values, "belongOrgName");
            return (Criteria) this;
        }

        public Criteria andBelongOrgNameBetween(String value1, String value2) {
            addCriterion("BELONG_ORG_NAME between", value1, value2, "belongOrgName");
            return (Criteria) this;
        }

        public Criteria andBelongOrgNameNotBetween(String value1, String value2) {
            addCriterion("BELONG_ORG_NAME not between", value1, value2, "belongOrgName");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeIsNull() {
            addCriterion("REGISTER_CODE is null");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeIsNotNull() {
            addCriterion("REGISTER_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeEqualTo(String value) {
            addCriterion("REGISTER_CODE =", value, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeNotEqualTo(String value) {
            addCriterion("REGISTER_CODE <>", value, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeGreaterThan(String value) {
            addCriterion("REGISTER_CODE >", value, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeGreaterThanOrEqualTo(String value) {
            addCriterion("REGISTER_CODE >=", value, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeLessThan(String value) {
            addCriterion("REGISTER_CODE <", value, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeLessThanOrEqualTo(String value) {
            addCriterion("REGISTER_CODE <=", value, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeLike(String value) {
            addCriterion("REGISTER_CODE like", value, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeNotLike(String value) {
            addCriterion("REGISTER_CODE not like", value, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeIn(List<String> values) {
            addCriterion("REGISTER_CODE in", values, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeNotIn(List<String> values) {
            addCriterion("REGISTER_CODE not in", values, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeBetween(String value1, String value2) {
            addCriterion("REGISTER_CODE between", value1, value2, "registerCode");
            return (Criteria) this;
        }

        public Criteria andRegisterCodeNotBetween(String value1, String value2) {
            addCriterion("REGISTER_CODE not between", value1, value2, "registerCode");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeIdIsNull() {
            addCriterion("COPYRIGHT_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeIdIsNotNull() {
            addCriterion("COPYRIGHT_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeIdEqualTo(String value) {
            addCriterion("COPYRIGHT_TYPE_ID =", value, "copyrightTypeId");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeIdNotEqualTo(String value) {
            addCriterion("COPYRIGHT_TYPE_ID <>", value, "copyrightTypeId");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeIdGreaterThan(String value) {
            addCriterion("COPYRIGHT_TYPE_ID >", value, "copyrightTypeId");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("COPYRIGHT_TYPE_ID >=", value, "copyrightTypeId");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeIdLessThan(String value) {
            addCriterion("COPYRIGHT_TYPE_ID <", value, "copyrightTypeId");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeIdLessThanOrEqualTo(String value) {
            addCriterion("COPYRIGHT_TYPE_ID <=", value, "copyrightTypeId");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeIdLike(String value) {
            addCriterion("COPYRIGHT_TYPE_ID like", value, "copyrightTypeId");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeIdNotLike(String value) {
            addCriterion("COPYRIGHT_TYPE_ID not like", value, "copyrightTypeId");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeIdIn(List<String> values) {
            addCriterion("COPYRIGHT_TYPE_ID in", values, "copyrightTypeId");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeIdNotIn(List<String> values) {
            addCriterion("COPYRIGHT_TYPE_ID not in", values, "copyrightTypeId");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeIdBetween(String value1, String value2) {
            addCriterion("COPYRIGHT_TYPE_ID between", value1, value2, "copyrightTypeId");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeIdNotBetween(String value1, String value2) {
            addCriterion("COPYRIGHT_TYPE_ID not between", value1, value2, "copyrightTypeId");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeNameIsNull() {
            addCriterion("COPYRIGHT_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeNameIsNotNull() {
            addCriterion("COPYRIGHT_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeNameEqualTo(String value) {
            addCriterion("COPYRIGHT_TYPE_NAME =", value, "copyrightTypeName");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeNameNotEqualTo(String value) {
            addCriterion("COPYRIGHT_TYPE_NAME <>", value, "copyrightTypeName");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeNameGreaterThan(String value) {
            addCriterion("COPYRIGHT_TYPE_NAME >", value, "copyrightTypeName");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("COPYRIGHT_TYPE_NAME >=", value, "copyrightTypeName");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeNameLessThan(String value) {
            addCriterion("COPYRIGHT_TYPE_NAME <", value, "copyrightTypeName");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeNameLessThanOrEqualTo(String value) {
            addCriterion("COPYRIGHT_TYPE_NAME <=", value, "copyrightTypeName");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeNameLike(String value) {
            addCriterion("COPYRIGHT_TYPE_NAME like", value, "copyrightTypeName");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeNameNotLike(String value) {
            addCriterion("COPYRIGHT_TYPE_NAME not like", value, "copyrightTypeName");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeNameIn(List<String> values) {
            addCriterion("COPYRIGHT_TYPE_NAME in", values, "copyrightTypeName");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeNameNotIn(List<String> values) {
            addCriterion("COPYRIGHT_TYPE_NAME not in", values, "copyrightTypeName");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeNameBetween(String value1, String value2) {
            addCriterion("COPYRIGHT_TYPE_NAME between", value1, value2, "copyrightTypeName");
            return (Criteria) this;
        }

        public Criteria andCopyrightTypeNameNotBetween(String value1, String value2) {
            addCriterion("COPYRIGHT_TYPE_NAME not between", value1, value2, "copyrightTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdIsNull() {
            addCriterion("SUBJECT_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdIsNotNull() {
            addCriterion("SUBJECT_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdEqualTo(String value) {
            addCriterion("SUBJECT_TYPE_ID =", value, "subjectTypeId");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdNotEqualTo(String value) {
            addCriterion("SUBJECT_TYPE_ID <>", value, "subjectTypeId");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdGreaterThan(String value) {
            addCriterion("SUBJECT_TYPE_ID >", value, "subjectTypeId");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_TYPE_ID >=", value, "subjectTypeId");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdLessThan(String value) {
            addCriterion("SUBJECT_TYPE_ID <", value, "subjectTypeId");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_TYPE_ID <=", value, "subjectTypeId");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdLike(String value) {
            addCriterion("SUBJECT_TYPE_ID like", value, "subjectTypeId");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdNotLike(String value) {
            addCriterion("SUBJECT_TYPE_ID not like", value, "subjectTypeId");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdIn(List<String> values) {
            addCriterion("SUBJECT_TYPE_ID in", values, "subjectTypeId");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdNotIn(List<String> values) {
            addCriterion("SUBJECT_TYPE_ID not in", values, "subjectTypeId");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdBetween(String value1, String value2) {
            addCriterion("SUBJECT_TYPE_ID between", value1, value2, "subjectTypeId");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_TYPE_ID not between", value1, value2, "subjectTypeId");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameIsNull() {
            addCriterion("SUBJECT_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameIsNotNull() {
            addCriterion("SUBJECT_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameEqualTo(String value) {
            addCriterion("SUBJECT_TYPE_NAME =", value, "subjectTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameNotEqualTo(String value) {
            addCriterion("SUBJECT_TYPE_NAME <>", value, "subjectTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameGreaterThan(String value) {
            addCriterion("SUBJECT_TYPE_NAME >", value, "subjectTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_TYPE_NAME >=", value, "subjectTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameLessThan(String value) {
            addCriterion("SUBJECT_TYPE_NAME <", value, "subjectTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_TYPE_NAME <=", value, "subjectTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameLike(String value) {
            addCriterion("SUBJECT_TYPE_NAME like", value, "subjectTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameNotLike(String value) {
            addCriterion("SUBJECT_TYPE_NAME not like", value, "subjectTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameIn(List<String> values) {
            addCriterion("SUBJECT_TYPE_NAME in", values, "subjectTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameNotIn(List<String> values) {
            addCriterion("SUBJECT_TYPE_NAME not in", values, "subjectTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameBetween(String value1, String value2) {
            addCriterion("SUBJECT_TYPE_NAME between", value1, value2, "subjectTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_TYPE_NAME not between", value1, value2, "subjectTypeName");
            return (Criteria) this;
        }

        public Criteria andPublishDateIsNull() {
            addCriterion("PUBLISH_DATE is null");
            return (Criteria) this;
        }

        public Criteria andPublishDateIsNotNull() {
            addCriterion("PUBLISH_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andPublishDateEqualTo(String value) {
            addCriterion("PUBLISH_DATE =", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateNotEqualTo(String value) {
            addCriterion("PUBLISH_DATE <>", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateGreaterThan(String value) {
            addCriterion("PUBLISH_DATE >", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLISH_DATE >=", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateLessThan(String value) {
            addCriterion("PUBLISH_DATE <", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateLessThanOrEqualTo(String value) {
            addCriterion("PUBLISH_DATE <=", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateLike(String value) {
            addCriterion("PUBLISH_DATE like", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateNotLike(String value) {
            addCriterion("PUBLISH_DATE not like", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateIn(List<String> values) {
            addCriterion("PUBLISH_DATE in", values, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateNotIn(List<String> values) {
            addCriterion("PUBLISH_DATE not in", values, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateBetween(String value1, String value2) {
            addCriterion("PUBLISH_DATE between", value1, value2, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateNotBetween(String value1, String value2) {
            addCriterion("PUBLISH_DATE not between", value1, value2, "publishDate");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowIsNull() {
            addCriterion("APPLY_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowIsNotNull() {
            addCriterion("APPLY_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW =", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW <>", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowGreaterThan(String value) {
            addCriterion("APPLY_USER_FLOW >", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW >=", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowLessThan(String value) {
            addCriterion("APPLY_USER_FLOW <", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowLessThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW <=", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowLike(String value) {
            addCriterion("APPLY_USER_FLOW like", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotLike(String value) {
            addCriterion("APPLY_USER_FLOW not like", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowIn(List<String> values) {
            addCriterion("APPLY_USER_FLOW in", values, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotIn(List<String> values) {
            addCriterion("APPLY_USER_FLOW not in", values, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowBetween(String value1, String value2) {
            addCriterion("APPLY_USER_FLOW between", value1, value2, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotBetween(String value1, String value2) {
            addCriterion("APPLY_USER_FLOW not between", value1, value2, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameIsNull() {
            addCriterion("APPLY_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameIsNotNull() {
            addCriterion("APPLY_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameEqualTo(String value) {
            addCriterion("APPLY_USER_NAME =", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotEqualTo(String value) {
            addCriterion("APPLY_USER_NAME <>", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameGreaterThan(String value) {
            addCriterion("APPLY_USER_NAME >", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_NAME >=", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameLessThan(String value) {
            addCriterion("APPLY_USER_NAME <", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameLessThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_NAME <=", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameLike(String value) {
            addCriterion("APPLY_USER_NAME like", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotLike(String value) {
            addCriterion("APPLY_USER_NAME not like", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameIn(List<String> values) {
            addCriterion("APPLY_USER_NAME in", values, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotIn(List<String> values) {
            addCriterion("APPLY_USER_NAME not in", values, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameBetween(String value1, String value2) {
            addCriterion("APPLY_USER_NAME between", value1, value2, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotBetween(String value1, String value2) {
            addCriterion("APPLY_USER_NAME not between", value1, value2, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowIsNull() {
            addCriterion("APPLY_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowIsNotNull() {
            addCriterion("APPLY_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowEqualTo(String value) {
            addCriterion("APPLY_ORG_FLOW =", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowNotEqualTo(String value) {
            addCriterion("APPLY_ORG_FLOW <>", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowGreaterThan(String value) {
            addCriterion("APPLY_ORG_FLOW >", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_ORG_FLOW >=", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowLessThan(String value) {
            addCriterion("APPLY_ORG_FLOW <", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("APPLY_ORG_FLOW <=", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowLike(String value) {
            addCriterion("APPLY_ORG_FLOW like", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowNotLike(String value) {
            addCriterion("APPLY_ORG_FLOW not like", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowIn(List<String> values) {
            addCriterion("APPLY_ORG_FLOW in", values, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowNotIn(List<String> values) {
            addCriterion("APPLY_ORG_FLOW not in", values, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowBetween(String value1, String value2) {
            addCriterion("APPLY_ORG_FLOW between", value1, value2, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowNotBetween(String value1, String value2) {
            addCriterion("APPLY_ORG_FLOW not between", value1, value2, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameIsNull() {
            addCriterion("APPLY_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameIsNotNull() {
            addCriterion("APPLY_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameEqualTo(String value) {
            addCriterion("APPLY_ORG_NAME =", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameNotEqualTo(String value) {
            addCriterion("APPLY_ORG_NAME <>", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameGreaterThan(String value) {
            addCriterion("APPLY_ORG_NAME >", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_ORG_NAME >=", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameLessThan(String value) {
            addCriterion("APPLY_ORG_NAME <", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameLessThanOrEqualTo(String value) {
            addCriterion("APPLY_ORG_NAME <=", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameLike(String value) {
            addCriterion("APPLY_ORG_NAME like", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameNotLike(String value) {
            addCriterion("APPLY_ORG_NAME not like", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameIn(List<String> values) {
            addCriterion("APPLY_ORG_NAME in", values, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameNotIn(List<String> values) {
            addCriterion("APPLY_ORG_NAME not in", values, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameBetween(String value1, String value2) {
            addCriterion("APPLY_ORG_NAME between", value1, value2, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameNotBetween(String value1, String value2) {
            addCriterion("APPLY_ORG_NAME not between", value1, value2, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdIsNull() {
            addCriterion("OPER_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdIsNotNull() {
            addCriterion("OPER_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdEqualTo(String value) {
            addCriterion("OPER_STATUS_ID =", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotEqualTo(String value) {
            addCriterion("OPER_STATUS_ID <>", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdGreaterThan(String value) {
            addCriterion("OPER_STATUS_ID >", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_ID >=", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdLessThan(String value) {
            addCriterion("OPER_STATUS_ID <", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdLessThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_ID <=", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdLike(String value) {
            addCriterion("OPER_STATUS_ID like", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotLike(String value) {
            addCriterion("OPER_STATUS_ID not like", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdIn(List<String> values) {
            addCriterion("OPER_STATUS_ID in", values, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotIn(List<String> values) {
            addCriterion("OPER_STATUS_ID not in", values, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_ID between", value1, value2, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_ID not between", value1, value2, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameIsNull() {
            addCriterion("OPER_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameIsNotNull() {
            addCriterion("OPER_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME =", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME <>", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameGreaterThan(String value) {
            addCriterion("OPER_STATUS_NAME >", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME >=", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameLessThan(String value) {
            addCriterion("OPER_STATUS_NAME <", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameLessThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME <=", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameLike(String value) {
            addCriterion("OPER_STATUS_NAME like", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotLike(String value) {
            addCriterion("OPER_STATUS_NAME not like", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameIn(List<String> values) {
            addCriterion("OPER_STATUS_NAME in", values, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotIn(List<String> values) {
            addCriterion("OPER_STATUS_NAME not in", values, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_NAME between", value1, value2, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_NAME not between", value1, value2, "operStatusName");
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

        public Criteria andDeptFlowIsNull() {
            addCriterion("DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIsNotNull() {
            addCriterion("DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDeptFlowEqualTo(String value) {
            addCriterion("DEPT_FLOW =", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotEqualTo(String value) {
            addCriterion("DEPT_FLOW <>", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowGreaterThan(String value) {
            addCriterion("DEPT_FLOW >", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_FLOW >=", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLessThan(String value) {
            addCriterion("DEPT_FLOW <", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("DEPT_FLOW <=", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLike(String value) {
            addCriterion("DEPT_FLOW like", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotLike(String value) {
            addCriterion("DEPT_FLOW not like", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIn(List<String> values) {
            addCriterion("DEPT_FLOW in", values, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotIn(List<String> values) {
            addCriterion("DEPT_FLOW not in", values, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowBetween(String value1, String value2) {
            addCriterion("DEPT_FLOW between", value1, value2, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotBetween(String value1, String value2) {
            addCriterion("DEPT_FLOW not between", value1, value2, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNull() {
            addCriterion("DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNotNull() {
            addCriterion("DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDeptNameEqualTo(String value) {
            addCriterion("DEPT_NAME =", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotEqualTo(String value) {
            addCriterion("DEPT_NAME <>", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThan(String value) {
            addCriterion("DEPT_NAME >", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_NAME >=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThan(String value) {
            addCriterion("DEPT_NAME <", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThanOrEqualTo(String value) {
            addCriterion("DEPT_NAME <=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLike(String value) {
            addCriterion("DEPT_NAME like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotLike(String value) {
            addCriterion("DEPT_NAME not like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameIn(List<String> values) {
            addCriterion("DEPT_NAME in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotIn(List<String> values) {
            addCriterion("DEPT_NAME not in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameBetween(String value1, String value2) {
            addCriterion("DEPT_NAME between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotBetween(String value1, String value2) {
            addCriterion("DEPT_NAME not between", value1, value2, "deptName");
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