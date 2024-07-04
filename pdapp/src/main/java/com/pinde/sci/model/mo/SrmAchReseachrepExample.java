package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class SrmAchReseachrepExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SrmAchReseachrepExample() {
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

        public Criteria andReseachrepFlowIsNull() {
            addCriterion("RESEACHREP_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andReseachrepFlowIsNotNull() {
            addCriterion("RESEACHREP_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andReseachrepFlowEqualTo(String value) {
            addCriterion("RESEACHREP_FLOW =", value, "reseachrepFlow");
            return (Criteria) this;
        }

        public Criteria andReseachrepFlowNotEqualTo(String value) {
            addCriterion("RESEACHREP_FLOW <>", value, "reseachrepFlow");
            return (Criteria) this;
        }

        public Criteria andReseachrepFlowGreaterThan(String value) {
            addCriterion("RESEACHREP_FLOW >", value, "reseachrepFlow");
            return (Criteria) this;
        }

        public Criteria andReseachrepFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RESEACHREP_FLOW >=", value, "reseachrepFlow");
            return (Criteria) this;
        }

        public Criteria andReseachrepFlowLessThan(String value) {
            addCriterion("RESEACHREP_FLOW <", value, "reseachrepFlow");
            return (Criteria) this;
        }

        public Criteria andReseachrepFlowLessThanOrEqualTo(String value) {
            addCriterion("RESEACHREP_FLOW <=", value, "reseachrepFlow");
            return (Criteria) this;
        }

        public Criteria andReseachrepFlowLike(String value) {
            addCriterion("RESEACHREP_FLOW like", value, "reseachrepFlow");
            return (Criteria) this;
        }

        public Criteria andReseachrepFlowNotLike(String value) {
            addCriterion("RESEACHREP_FLOW not like", value, "reseachrepFlow");
            return (Criteria) this;
        }

        public Criteria andReseachrepFlowIn(List<String> values) {
            addCriterion("RESEACHREP_FLOW in", values, "reseachrepFlow");
            return (Criteria) this;
        }

        public Criteria andReseachrepFlowNotIn(List<String> values) {
            addCriterion("RESEACHREP_FLOW not in", values, "reseachrepFlow");
            return (Criteria) this;
        }

        public Criteria andReseachrepFlowBetween(String value1, String value2) {
            addCriterion("RESEACHREP_FLOW between", value1, value2, "reseachrepFlow");
            return (Criteria) this;
        }

        public Criteria andReseachrepFlowNotBetween(String value1, String value2) {
            addCriterion("RESEACHREP_FLOW not between", value1, value2, "reseachrepFlow");
            return (Criteria) this;
        }

        public Criteria andRepTitleIsNull() {
            addCriterion("REP_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andRepTitleIsNotNull() {
            addCriterion("REP_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andRepTitleEqualTo(String value) {
            addCriterion("REP_TITLE =", value, "repTitle");
            return (Criteria) this;
        }

        public Criteria andRepTitleNotEqualTo(String value) {
            addCriterion("REP_TITLE <>", value, "repTitle");
            return (Criteria) this;
        }

        public Criteria andRepTitleGreaterThan(String value) {
            addCriterion("REP_TITLE >", value, "repTitle");
            return (Criteria) this;
        }

        public Criteria andRepTitleGreaterThanOrEqualTo(String value) {
            addCriterion("REP_TITLE >=", value, "repTitle");
            return (Criteria) this;
        }

        public Criteria andRepTitleLessThan(String value) {
            addCriterion("REP_TITLE <", value, "repTitle");
            return (Criteria) this;
        }

        public Criteria andRepTitleLessThanOrEqualTo(String value) {
            addCriterion("REP_TITLE <=", value, "repTitle");
            return (Criteria) this;
        }

        public Criteria andRepTitleLike(String value) {
            addCriterion("REP_TITLE like", value, "repTitle");
            return (Criteria) this;
        }

        public Criteria andRepTitleNotLike(String value) {
            addCriterion("REP_TITLE not like", value, "repTitle");
            return (Criteria) this;
        }

        public Criteria andRepTitleIn(List<String> values) {
            addCriterion("REP_TITLE in", values, "repTitle");
            return (Criteria) this;
        }

        public Criteria andRepTitleNotIn(List<String> values) {
            addCriterion("REP_TITLE not in", values, "repTitle");
            return (Criteria) this;
        }

        public Criteria andRepTitleBetween(String value1, String value2) {
            addCriterion("REP_TITLE between", value1, value2, "repTitle");
            return (Criteria) this;
        }

        public Criteria andRepTitleNotBetween(String value1, String value2) {
            addCriterion("REP_TITLE not between", value1, value2, "repTitle");
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

        public Criteria andSubmitOrgIsNull() {
            addCriterion("SUBMIT_ORG is null");
            return (Criteria) this;
        }

        public Criteria andSubmitOrgIsNotNull() {
            addCriterion("SUBMIT_ORG is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitOrgEqualTo(String value) {
            addCriterion("SUBMIT_ORG =", value, "submitOrg");
            return (Criteria) this;
        }

        public Criteria andSubmitOrgNotEqualTo(String value) {
            addCriterion("SUBMIT_ORG <>", value, "submitOrg");
            return (Criteria) this;
        }

        public Criteria andSubmitOrgGreaterThan(String value) {
            addCriterion("SUBMIT_ORG >", value, "submitOrg");
            return (Criteria) this;
        }

        public Criteria andSubmitOrgGreaterThanOrEqualTo(String value) {
            addCriterion("SUBMIT_ORG >=", value, "submitOrg");
            return (Criteria) this;
        }

        public Criteria andSubmitOrgLessThan(String value) {
            addCriterion("SUBMIT_ORG <", value, "submitOrg");
            return (Criteria) this;
        }

        public Criteria andSubmitOrgLessThanOrEqualTo(String value) {
            addCriterion("SUBMIT_ORG <=", value, "submitOrg");
            return (Criteria) this;
        }

        public Criteria andSubmitOrgLike(String value) {
            addCriterion("SUBMIT_ORG like", value, "submitOrg");
            return (Criteria) this;
        }

        public Criteria andSubmitOrgNotLike(String value) {
            addCriterion("SUBMIT_ORG not like", value, "submitOrg");
            return (Criteria) this;
        }

        public Criteria andSubmitOrgIn(List<String> values) {
            addCriterion("SUBMIT_ORG in", values, "submitOrg");
            return (Criteria) this;
        }

        public Criteria andSubmitOrgNotIn(List<String> values) {
            addCriterion("SUBMIT_ORG not in", values, "submitOrg");
            return (Criteria) this;
        }

        public Criteria andSubmitOrgBetween(String value1, String value2) {
            addCriterion("SUBMIT_ORG between", value1, value2, "submitOrg");
            return (Criteria) this;
        }

        public Criteria andSubmitOrgNotBetween(String value1, String value2) {
            addCriterion("SUBMIT_ORG not between", value1, value2, "submitOrg");
            return (Criteria) this;
        }

        public Criteria andSubmitDateIsNull() {
            addCriterion("SUBMIT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSubmitDateIsNotNull() {
            addCriterion("SUBMIT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitDateEqualTo(String value) {
            addCriterion("SUBMIT_DATE =", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateNotEqualTo(String value) {
            addCriterion("SUBMIT_DATE <>", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateGreaterThan(String value) {
            addCriterion("SUBMIT_DATE >", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateGreaterThanOrEqualTo(String value) {
            addCriterion("SUBMIT_DATE >=", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateLessThan(String value) {
            addCriterion("SUBMIT_DATE <", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateLessThanOrEqualTo(String value) {
            addCriterion("SUBMIT_DATE <=", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateLike(String value) {
            addCriterion("SUBMIT_DATE like", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateNotLike(String value) {
            addCriterion("SUBMIT_DATE not like", value, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateIn(List<String> values) {
            addCriterion("SUBMIT_DATE in", values, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateNotIn(List<String> values) {
            addCriterion("SUBMIT_DATE not in", values, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateBetween(String value1, String value2) {
            addCriterion("SUBMIT_DATE between", value1, value2, "submitDate");
            return (Criteria) this;
        }

        public Criteria andSubmitDateNotBetween(String value1, String value2) {
            addCriterion("SUBMIT_DATE not between", value1, value2, "submitDate");
            return (Criteria) this;
        }

        public Criteria andAcceptFlagIsNull() {
            addCriterion("ACCEPT_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andAcceptFlagIsNotNull() {
            addCriterion("ACCEPT_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptFlagEqualTo(String value) {
            addCriterion("ACCEPT_FLAG =", value, "acceptFlag");
            return (Criteria) this;
        }

        public Criteria andAcceptFlagNotEqualTo(String value) {
            addCriterion("ACCEPT_FLAG <>", value, "acceptFlag");
            return (Criteria) this;
        }

        public Criteria andAcceptFlagGreaterThan(String value) {
            addCriterion("ACCEPT_FLAG >", value, "acceptFlag");
            return (Criteria) this;
        }

        public Criteria andAcceptFlagGreaterThanOrEqualTo(String value) {
            addCriterion("ACCEPT_FLAG >=", value, "acceptFlag");
            return (Criteria) this;
        }

        public Criteria andAcceptFlagLessThan(String value) {
            addCriterion("ACCEPT_FLAG <", value, "acceptFlag");
            return (Criteria) this;
        }

        public Criteria andAcceptFlagLessThanOrEqualTo(String value) {
            addCriterion("ACCEPT_FLAG <=", value, "acceptFlag");
            return (Criteria) this;
        }

        public Criteria andAcceptFlagLike(String value) {
            addCriterion("ACCEPT_FLAG like", value, "acceptFlag");
            return (Criteria) this;
        }

        public Criteria andAcceptFlagNotLike(String value) {
            addCriterion("ACCEPT_FLAG not like", value, "acceptFlag");
            return (Criteria) this;
        }

        public Criteria andAcceptFlagIn(List<String> values) {
            addCriterion("ACCEPT_FLAG in", values, "acceptFlag");
            return (Criteria) this;
        }

        public Criteria andAcceptFlagNotIn(List<String> values) {
            addCriterion("ACCEPT_FLAG not in", values, "acceptFlag");
            return (Criteria) this;
        }

        public Criteria andAcceptFlagBetween(String value1, String value2) {
            addCriterion("ACCEPT_FLAG between", value1, value2, "acceptFlag");
            return (Criteria) this;
        }

        public Criteria andAcceptFlagNotBetween(String value1, String value2) {
            addCriterion("ACCEPT_FLAG not between", value1, value2, "acceptFlag");
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

        public Criteria andAcceptObjectIdIsNull() {
            addCriterion("ACCEPT_OBJECT_ID is null");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectIdIsNotNull() {
            addCriterion("ACCEPT_OBJECT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectIdEqualTo(String value) {
            addCriterion("ACCEPT_OBJECT_ID =", value, "acceptObjectId");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectIdNotEqualTo(String value) {
            addCriterion("ACCEPT_OBJECT_ID <>", value, "acceptObjectId");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectIdGreaterThan(String value) {
            addCriterion("ACCEPT_OBJECT_ID >", value, "acceptObjectId");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("ACCEPT_OBJECT_ID >=", value, "acceptObjectId");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectIdLessThan(String value) {
            addCriterion("ACCEPT_OBJECT_ID <", value, "acceptObjectId");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectIdLessThanOrEqualTo(String value) {
            addCriterion("ACCEPT_OBJECT_ID <=", value, "acceptObjectId");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectIdLike(String value) {
            addCriterion("ACCEPT_OBJECT_ID like", value, "acceptObjectId");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectIdNotLike(String value) {
            addCriterion("ACCEPT_OBJECT_ID not like", value, "acceptObjectId");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectIdIn(List<String> values) {
            addCriterion("ACCEPT_OBJECT_ID in", values, "acceptObjectId");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectIdNotIn(List<String> values) {
            addCriterion("ACCEPT_OBJECT_ID not in", values, "acceptObjectId");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectIdBetween(String value1, String value2) {
            addCriterion("ACCEPT_OBJECT_ID between", value1, value2, "acceptObjectId");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectIdNotBetween(String value1, String value2) {
            addCriterion("ACCEPT_OBJECT_ID not between", value1, value2, "acceptObjectId");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectNameIsNull() {
            addCriterion("ACCEPT_OBJECT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectNameIsNotNull() {
            addCriterion("ACCEPT_OBJECT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectNameEqualTo(String value) {
            addCriterion("ACCEPT_OBJECT_NAME =", value, "acceptObjectName");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectNameNotEqualTo(String value) {
            addCriterion("ACCEPT_OBJECT_NAME <>", value, "acceptObjectName");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectNameGreaterThan(String value) {
            addCriterion("ACCEPT_OBJECT_NAME >", value, "acceptObjectName");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACCEPT_OBJECT_NAME >=", value, "acceptObjectName");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectNameLessThan(String value) {
            addCriterion("ACCEPT_OBJECT_NAME <", value, "acceptObjectName");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectNameLessThanOrEqualTo(String value) {
            addCriterion("ACCEPT_OBJECT_NAME <=", value, "acceptObjectName");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectNameLike(String value) {
            addCriterion("ACCEPT_OBJECT_NAME like", value, "acceptObjectName");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectNameNotLike(String value) {
            addCriterion("ACCEPT_OBJECT_NAME not like", value, "acceptObjectName");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectNameIn(List<String> values) {
            addCriterion("ACCEPT_OBJECT_NAME in", values, "acceptObjectName");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectNameNotIn(List<String> values) {
            addCriterion("ACCEPT_OBJECT_NAME not in", values, "acceptObjectName");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectNameBetween(String value1, String value2) {
            addCriterion("ACCEPT_OBJECT_NAME between", value1, value2, "acceptObjectName");
            return (Criteria) this;
        }

        public Criteria andAcceptObjectNameNotBetween(String value1, String value2) {
            addCriterion("ACCEPT_OBJECT_NAME not between", value1, value2, "acceptObjectName");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdIsNull() {
            addCriterion("PROJ_SOURCE_ID is null");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdIsNotNull() {
            addCriterion("PROJ_SOURCE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdEqualTo(String value) {
            addCriterion("PROJ_SOURCE_ID =", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdNotEqualTo(String value) {
            addCriterion("PROJ_SOURCE_ID <>", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdGreaterThan(String value) {
            addCriterion("PROJ_SOURCE_ID >", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_SOURCE_ID >=", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdLessThan(String value) {
            addCriterion("PROJ_SOURCE_ID <", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdLessThanOrEqualTo(String value) {
            addCriterion("PROJ_SOURCE_ID <=", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdLike(String value) {
            addCriterion("PROJ_SOURCE_ID like", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdNotLike(String value) {
            addCriterion("PROJ_SOURCE_ID not like", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdIn(List<String> values) {
            addCriterion("PROJ_SOURCE_ID in", values, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdNotIn(List<String> values) {
            addCriterion("PROJ_SOURCE_ID not in", values, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdBetween(String value1, String value2) {
            addCriterion("PROJ_SOURCE_ID between", value1, value2, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdNotBetween(String value1, String value2) {
            addCriterion("PROJ_SOURCE_ID not between", value1, value2, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameIsNull() {
            addCriterion("PROJ_SOURCE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameIsNotNull() {
            addCriterion("PROJ_SOURCE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameEqualTo(String value) {
            addCriterion("PROJ_SOURCE_NAME =", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameNotEqualTo(String value) {
            addCriterion("PROJ_SOURCE_NAME <>", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameGreaterThan(String value) {
            addCriterion("PROJ_SOURCE_NAME >", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_SOURCE_NAME >=", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameLessThan(String value) {
            addCriterion("PROJ_SOURCE_NAME <", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameLessThanOrEqualTo(String value) {
            addCriterion("PROJ_SOURCE_NAME <=", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameLike(String value) {
            addCriterion("PROJ_SOURCE_NAME like", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameNotLike(String value) {
            addCriterion("PROJ_SOURCE_NAME not like", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameIn(List<String> values) {
            addCriterion("PROJ_SOURCE_NAME in", values, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameNotIn(List<String> values) {
            addCriterion("PROJ_SOURCE_NAME not in", values, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameBetween(String value1, String value2) {
            addCriterion("PROJ_SOURCE_NAME between", value1, value2, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameNotBetween(String value1, String value2) {
            addCriterion("PROJ_SOURCE_NAME not between", value1, value2, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andRepWordCountIsNull() {
            addCriterion("REP_WORD_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andRepWordCountIsNotNull() {
            addCriterion("REP_WORD_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andRepWordCountEqualTo(String value) {
            addCriterion("REP_WORD_COUNT =", value, "repWordCount");
            return (Criteria) this;
        }

        public Criteria andRepWordCountNotEqualTo(String value) {
            addCriterion("REP_WORD_COUNT <>", value, "repWordCount");
            return (Criteria) this;
        }

        public Criteria andRepWordCountGreaterThan(String value) {
            addCriterion("REP_WORD_COUNT >", value, "repWordCount");
            return (Criteria) this;
        }

        public Criteria andRepWordCountGreaterThanOrEqualTo(String value) {
            addCriterion("REP_WORD_COUNT >=", value, "repWordCount");
            return (Criteria) this;
        }

        public Criteria andRepWordCountLessThan(String value) {
            addCriterion("REP_WORD_COUNT <", value, "repWordCount");
            return (Criteria) this;
        }

        public Criteria andRepWordCountLessThanOrEqualTo(String value) {
            addCriterion("REP_WORD_COUNT <=", value, "repWordCount");
            return (Criteria) this;
        }

        public Criteria andRepWordCountLike(String value) {
            addCriterion("REP_WORD_COUNT like", value, "repWordCount");
            return (Criteria) this;
        }

        public Criteria andRepWordCountNotLike(String value) {
            addCriterion("REP_WORD_COUNT not like", value, "repWordCount");
            return (Criteria) this;
        }

        public Criteria andRepWordCountIn(List<String> values) {
            addCriterion("REP_WORD_COUNT in", values, "repWordCount");
            return (Criteria) this;
        }

        public Criteria andRepWordCountNotIn(List<String> values) {
            addCriterion("REP_WORD_COUNT not in", values, "repWordCount");
            return (Criteria) this;
        }

        public Criteria andRepWordCountBetween(String value1, String value2) {
            addCriterion("REP_WORD_COUNT between", value1, value2, "repWordCount");
            return (Criteria) this;
        }

        public Criteria andRepWordCountNotBetween(String value1, String value2) {
            addCriterion("REP_WORD_COUNT not between", value1, value2, "repWordCount");
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