package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ExamSubjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamSubjectExample() {
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

        public Criteria andSubjectCodeIsNull() {
            addCriterion("SUBJECT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeIsNotNull() {
            addCriterion("SUBJECT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeEqualTo(String value) {
            addCriterion("SUBJECT_CODE =", value, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeNotEqualTo(String value) {
            addCriterion("SUBJECT_CODE <>", value, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeGreaterThan(String value) {
            addCriterion("SUBJECT_CODE >", value, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_CODE >=", value, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeLessThan(String value) {
            addCriterion("SUBJECT_CODE <", value, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_CODE <=", value, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeLike(String value) {
            addCriterion("SUBJECT_CODE like", value, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeNotLike(String value) {
            addCriterion("SUBJECT_CODE not like", value, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeIn(List<String> values) {
            addCriterion("SUBJECT_CODE in", values, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeNotIn(List<String> values) {
            addCriterion("SUBJECT_CODE not in", values, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeBetween(String value1, String value2) {
            addCriterion("SUBJECT_CODE between", value1, value2, "subjectCode");
            return (Criteria) this;
        }

        public Criteria andSubjectCodeNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_CODE not between", value1, value2, "subjectCode");
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

        public Criteria andSubjectParentFlowIsNull() {
            addCriterion("SUBJECT_PARENT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSubjectParentFlowIsNotNull() {
            addCriterion("SUBJECT_PARENT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectParentFlowEqualTo(String value) {
            addCriterion("SUBJECT_PARENT_FLOW =", value, "subjectParentFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectParentFlowNotEqualTo(String value) {
            addCriterion("SUBJECT_PARENT_FLOW <>", value, "subjectParentFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectParentFlowGreaterThan(String value) {
            addCriterion("SUBJECT_PARENT_FLOW >", value, "subjectParentFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectParentFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_PARENT_FLOW >=", value, "subjectParentFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectParentFlowLessThan(String value) {
            addCriterion("SUBJECT_PARENT_FLOW <", value, "subjectParentFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectParentFlowLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_PARENT_FLOW <=", value, "subjectParentFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectParentFlowLike(String value) {
            addCriterion("SUBJECT_PARENT_FLOW like", value, "subjectParentFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectParentFlowNotLike(String value) {
            addCriterion("SUBJECT_PARENT_FLOW not like", value, "subjectParentFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectParentFlowIn(List<String> values) {
            addCriterion("SUBJECT_PARENT_FLOW in", values, "subjectParentFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectParentFlowNotIn(List<String> values) {
            addCriterion("SUBJECT_PARENT_FLOW not in", values, "subjectParentFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectParentFlowBetween(String value1, String value2) {
            addCriterion("SUBJECT_PARENT_FLOW between", value1, value2, "subjectParentFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectParentFlowNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_PARENT_FLOW not between", value1, value2, "subjectParentFlow");
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

        public Criteria andBankTypeIdIsNull() {
            addCriterion("BANK_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andBankTypeIdIsNotNull() {
            addCriterion("BANK_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBankTypeIdEqualTo(String value) {
            addCriterion("BANK_TYPE_ID =", value, "bankTypeId");
            return (Criteria) this;
        }

        public Criteria andBankTypeIdNotEqualTo(String value) {
            addCriterion("BANK_TYPE_ID <>", value, "bankTypeId");
            return (Criteria) this;
        }

        public Criteria andBankTypeIdGreaterThan(String value) {
            addCriterion("BANK_TYPE_ID >", value, "bankTypeId");
            return (Criteria) this;
        }

        public Criteria andBankTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_TYPE_ID >=", value, "bankTypeId");
            return (Criteria) this;
        }

        public Criteria andBankTypeIdLessThan(String value) {
            addCriterion("BANK_TYPE_ID <", value, "bankTypeId");
            return (Criteria) this;
        }

        public Criteria andBankTypeIdLessThanOrEqualTo(String value) {
            addCriterion("BANK_TYPE_ID <=", value, "bankTypeId");
            return (Criteria) this;
        }

        public Criteria andBankTypeIdLike(String value) {
            addCriterion("BANK_TYPE_ID like", value, "bankTypeId");
            return (Criteria) this;
        }

        public Criteria andBankTypeIdNotLike(String value) {
            addCriterion("BANK_TYPE_ID not like", value, "bankTypeId");
            return (Criteria) this;
        }

        public Criteria andBankTypeIdIn(List<String> values) {
            addCriterion("BANK_TYPE_ID in", values, "bankTypeId");
            return (Criteria) this;
        }

        public Criteria andBankTypeIdNotIn(List<String> values) {
            addCriterion("BANK_TYPE_ID not in", values, "bankTypeId");
            return (Criteria) this;
        }

        public Criteria andBankTypeIdBetween(String value1, String value2) {
            addCriterion("BANK_TYPE_ID between", value1, value2, "bankTypeId");
            return (Criteria) this;
        }

        public Criteria andBankTypeIdNotBetween(String value1, String value2) {
            addCriterion("BANK_TYPE_ID not between", value1, value2, "bankTypeId");
            return (Criteria) this;
        }

        public Criteria andBankTypeNameIsNull() {
            addCriterion("BANK_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBankTypeNameIsNotNull() {
            addCriterion("BANK_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBankTypeNameEqualTo(String value) {
            addCriterion("BANK_TYPE_NAME =", value, "bankTypeName");
            return (Criteria) this;
        }

        public Criteria andBankTypeNameNotEqualTo(String value) {
            addCriterion("BANK_TYPE_NAME <>", value, "bankTypeName");
            return (Criteria) this;
        }

        public Criteria andBankTypeNameGreaterThan(String value) {
            addCriterion("BANK_TYPE_NAME >", value, "bankTypeName");
            return (Criteria) this;
        }

        public Criteria andBankTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_TYPE_NAME >=", value, "bankTypeName");
            return (Criteria) this;
        }

        public Criteria andBankTypeNameLessThan(String value) {
            addCriterion("BANK_TYPE_NAME <", value, "bankTypeName");
            return (Criteria) this;
        }

        public Criteria andBankTypeNameLessThanOrEqualTo(String value) {
            addCriterion("BANK_TYPE_NAME <=", value, "bankTypeName");
            return (Criteria) this;
        }

        public Criteria andBankTypeNameLike(String value) {
            addCriterion("BANK_TYPE_NAME like", value, "bankTypeName");
            return (Criteria) this;
        }

        public Criteria andBankTypeNameNotLike(String value) {
            addCriterion("BANK_TYPE_NAME not like", value, "bankTypeName");
            return (Criteria) this;
        }

        public Criteria andBankTypeNameIn(List<String> values) {
            addCriterion("BANK_TYPE_NAME in", values, "bankTypeName");
            return (Criteria) this;
        }

        public Criteria andBankTypeNameNotIn(List<String> values) {
            addCriterion("BANK_TYPE_NAME not in", values, "bankTypeName");
            return (Criteria) this;
        }

        public Criteria andBankTypeNameBetween(String value1, String value2) {
            addCriterion("BANK_TYPE_NAME between", value1, value2, "bankTypeName");
            return (Criteria) this;
        }

        public Criteria andBankTypeNameNotBetween(String value1, String value2) {
            addCriterion("BANK_TYPE_NAME not between", value1, value2, "bankTypeName");
            return (Criteria) this;
        }

        public Criteria andOrdinalIsNull() {
            addCriterion("ORDINAL is null");
            return (Criteria) this;
        }

        public Criteria andOrdinalIsNotNull() {
            addCriterion("ORDINAL is not null");
            return (Criteria) this;
        }

        public Criteria andOrdinalEqualTo(Integer value) {
            addCriterion("ORDINAL =", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotEqualTo(Integer value) {
            addCriterion("ORDINAL <>", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalGreaterThan(Integer value) {
            addCriterion("ORDINAL >", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalGreaterThanOrEqualTo(Integer value) {
            addCriterion("ORDINAL >=", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalLessThan(Integer value) {
            addCriterion("ORDINAL <", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalLessThanOrEqualTo(Integer value) {
            addCriterion("ORDINAL <=", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalIn(List<Integer> values) {
            addCriterion("ORDINAL in", values, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotIn(List<Integer> values) {
            addCriterion("ORDINAL not in", values, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalBetween(Integer value1, Integer value2) {
            addCriterion("ORDINAL between", value1, value2, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotBetween(Integer value1, Integer value2) {
            addCriterion("ORDINAL not between", value1, value2, "ordinal");
            return (Criteria) this;
        }

        public Criteria andDepthIsNull() {
            addCriterion("DEPTH is null");
            return (Criteria) this;
        }

        public Criteria andDepthIsNotNull() {
            addCriterion("DEPTH is not null");
            return (Criteria) this;
        }

        public Criteria andDepthEqualTo(String value) {
            addCriterion("DEPTH =", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotEqualTo(String value) {
            addCriterion("DEPTH <>", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthGreaterThan(String value) {
            addCriterion("DEPTH >", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthGreaterThanOrEqualTo(String value) {
            addCriterion("DEPTH >=", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthLessThan(String value) {
            addCriterion("DEPTH <", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthLessThanOrEqualTo(String value) {
            addCriterion("DEPTH <=", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthLike(String value) {
            addCriterion("DEPTH like", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotLike(String value) {
            addCriterion("DEPTH not like", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthIn(List<String> values) {
            addCriterion("DEPTH in", values, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotIn(List<String> values) {
            addCriterion("DEPTH not in", values, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthBetween(String value1, String value2) {
            addCriterion("DEPTH between", value1, value2, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotBetween(String value1, String value2) {
            addCriterion("DEPTH not between", value1, value2, "depth");
            return (Criteria) this;
        }

        public Criteria andLeafFlagIsNull() {
            addCriterion("LEAF_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andLeafFlagIsNotNull() {
            addCriterion("LEAF_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andLeafFlagEqualTo(String value) {
            addCriterion("LEAF_FLAG =", value, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagNotEqualTo(String value) {
            addCriterion("LEAF_FLAG <>", value, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagGreaterThan(String value) {
            addCriterion("LEAF_FLAG >", value, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagGreaterThanOrEqualTo(String value) {
            addCriterion("LEAF_FLAG >=", value, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagLessThan(String value) {
            addCriterion("LEAF_FLAG <", value, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagLessThanOrEqualTo(String value) {
            addCriterion("LEAF_FLAG <=", value, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagLike(String value) {
            addCriterion("LEAF_FLAG like", value, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagNotLike(String value) {
            addCriterion("LEAF_FLAG not like", value, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagIn(List<String> values) {
            addCriterion("LEAF_FLAG in", values, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagNotIn(List<String> values) {
            addCriterion("LEAF_FLAG not in", values, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagBetween(String value1, String value2) {
            addCriterion("LEAF_FLAG between", value1, value2, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagNotBetween(String value1, String value2) {
            addCriterion("LEAF_FLAG not between", value1, value2, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("MEMO is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("MEMO =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("MEMO <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("MEMO >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("MEMO >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("MEMO <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("MEMO <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("MEMO like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("MEMO not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("MEMO in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("MEMO not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("MEMO between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("MEMO not between", value1, value2, "memo");
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

        public Criteria andSourceSubjectFlowIsNull() {
            addCriterion("SOURCE_SUBJECT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSourceSubjectFlowIsNotNull() {
            addCriterion("SOURCE_SUBJECT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSourceSubjectFlowEqualTo(String value) {
            addCriterion("SOURCE_SUBJECT_FLOW =", value, "sourceSubjectFlow");
            return (Criteria) this;
        }

        public Criteria andSourceSubjectFlowNotEqualTo(String value) {
            addCriterion("SOURCE_SUBJECT_FLOW <>", value, "sourceSubjectFlow");
            return (Criteria) this;
        }

        public Criteria andSourceSubjectFlowGreaterThan(String value) {
            addCriterion("SOURCE_SUBJECT_FLOW >", value, "sourceSubjectFlow");
            return (Criteria) this;
        }

        public Criteria andSourceSubjectFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SOURCE_SUBJECT_FLOW >=", value, "sourceSubjectFlow");
            return (Criteria) this;
        }

        public Criteria andSourceSubjectFlowLessThan(String value) {
            addCriterion("SOURCE_SUBJECT_FLOW <", value, "sourceSubjectFlow");
            return (Criteria) this;
        }

        public Criteria andSourceSubjectFlowLessThanOrEqualTo(String value) {
            addCriterion("SOURCE_SUBJECT_FLOW <=", value, "sourceSubjectFlow");
            return (Criteria) this;
        }

        public Criteria andSourceSubjectFlowLike(String value) {
            addCriterion("SOURCE_SUBJECT_FLOW like", value, "sourceSubjectFlow");
            return (Criteria) this;
        }

        public Criteria andSourceSubjectFlowNotLike(String value) {
            addCriterion("SOURCE_SUBJECT_FLOW not like", value, "sourceSubjectFlow");
            return (Criteria) this;
        }

        public Criteria andSourceSubjectFlowIn(List<String> values) {
            addCriterion("SOURCE_SUBJECT_FLOW in", values, "sourceSubjectFlow");
            return (Criteria) this;
        }

        public Criteria andSourceSubjectFlowNotIn(List<String> values) {
            addCriterion("SOURCE_SUBJECT_FLOW not in", values, "sourceSubjectFlow");
            return (Criteria) this;
        }

        public Criteria andSourceSubjectFlowBetween(String value1, String value2) {
            addCriterion("SOURCE_SUBJECT_FLOW between", value1, value2, "sourceSubjectFlow");
            return (Criteria) this;
        }

        public Criteria andSourceSubjectFlowNotBetween(String value1, String value2) {
            addCriterion("SOURCE_SUBJECT_FLOW not between", value1, value2, "sourceSubjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectIsenabledIsNull() {
            addCriterion("SUBJECT_ISENABLED is null");
            return (Criteria) this;
        }

        public Criteria andSubjectIsenabledIsNotNull() {
            addCriterion("SUBJECT_ISENABLED is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectIsenabledEqualTo(String value) {
            addCriterion("SUBJECT_ISENABLED =", value, "subjectIsenabled");
            return (Criteria) this;
        }

        public Criteria andSubjectIsenabledNotEqualTo(String value) {
            addCriterion("SUBJECT_ISENABLED <>", value, "subjectIsenabled");
            return (Criteria) this;
        }

        public Criteria andSubjectIsenabledGreaterThan(String value) {
            addCriterion("SUBJECT_ISENABLED >", value, "subjectIsenabled");
            return (Criteria) this;
        }

        public Criteria andSubjectIsenabledGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_ISENABLED >=", value, "subjectIsenabled");
            return (Criteria) this;
        }

        public Criteria andSubjectIsenabledLessThan(String value) {
            addCriterion("SUBJECT_ISENABLED <", value, "subjectIsenabled");
            return (Criteria) this;
        }

        public Criteria andSubjectIsenabledLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_ISENABLED <=", value, "subjectIsenabled");
            return (Criteria) this;
        }

        public Criteria andSubjectIsenabledLike(String value) {
            addCriterion("SUBJECT_ISENABLED like", value, "subjectIsenabled");
            return (Criteria) this;
        }

        public Criteria andSubjectIsenabledNotLike(String value) {
            addCriterion("SUBJECT_ISENABLED not like", value, "subjectIsenabled");
            return (Criteria) this;
        }

        public Criteria andSubjectIsenabledIn(List<String> values) {
            addCriterion("SUBJECT_ISENABLED in", values, "subjectIsenabled");
            return (Criteria) this;
        }

        public Criteria andSubjectIsenabledNotIn(List<String> values) {
            addCriterion("SUBJECT_ISENABLED not in", values, "subjectIsenabled");
            return (Criteria) this;
        }

        public Criteria andSubjectIsenabledBetween(String value1, String value2) {
            addCriterion("SUBJECT_ISENABLED between", value1, value2, "subjectIsenabled");
            return (Criteria) this;
        }

        public Criteria andSubjectIsenabledNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_ISENABLED not between", value1, value2, "subjectIsenabled");
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