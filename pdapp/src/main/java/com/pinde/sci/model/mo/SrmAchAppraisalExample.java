package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class SrmAchAppraisalExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SrmAchAppraisalExample() {
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

        public Criteria andAppraisalFlowIsNull() {
            addCriterion("APPRAISAL_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAppraisalFlowIsNotNull() {
            addCriterion("APPRAISAL_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAppraisalFlowEqualTo(String value) {
            addCriterion("APPRAISAL_FLOW =", value, "appraisalFlow");
            return (Criteria) this;
        }

        public Criteria andAppraisalFlowNotEqualTo(String value) {
            addCriterion("APPRAISAL_FLOW <>", value, "appraisalFlow");
            return (Criteria) this;
        }

        public Criteria andAppraisalFlowGreaterThan(String value) {
            addCriterion("APPRAISAL_FLOW >", value, "appraisalFlow");
            return (Criteria) this;
        }

        public Criteria andAppraisalFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPRAISAL_FLOW >=", value, "appraisalFlow");
            return (Criteria) this;
        }

        public Criteria andAppraisalFlowLessThan(String value) {
            addCriterion("APPRAISAL_FLOW <", value, "appraisalFlow");
            return (Criteria) this;
        }

        public Criteria andAppraisalFlowLessThanOrEqualTo(String value) {
            addCriterion("APPRAISAL_FLOW <=", value, "appraisalFlow");
            return (Criteria) this;
        }

        public Criteria andAppraisalFlowLike(String value) {
            addCriterion("APPRAISAL_FLOW like", value, "appraisalFlow");
            return (Criteria) this;
        }

        public Criteria andAppraisalFlowNotLike(String value) {
            addCriterion("APPRAISAL_FLOW not like", value, "appraisalFlow");
            return (Criteria) this;
        }

        public Criteria andAppraisalFlowIn(List<String> values) {
            addCriterion("APPRAISAL_FLOW in", values, "appraisalFlow");
            return (Criteria) this;
        }

        public Criteria andAppraisalFlowNotIn(List<String> values) {
            addCriterion("APPRAISAL_FLOW not in", values, "appraisalFlow");
            return (Criteria) this;
        }

        public Criteria andAppraisalFlowBetween(String value1, String value2) {
            addCriterion("APPRAISAL_FLOW between", value1, value2, "appraisalFlow");
            return (Criteria) this;
        }

        public Criteria andAppraisalFlowNotBetween(String value1, String value2) {
            addCriterion("APPRAISAL_FLOW not between", value1, value2, "appraisalFlow");
            return (Criteria) this;
        }

        public Criteria andAppraisalNameIsNull() {
            addCriterion("APPRAISAL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAppraisalNameIsNotNull() {
            addCriterion("APPRAISAL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAppraisalNameEqualTo(String value) {
            addCriterion("APPRAISAL_NAME =", value, "appraisalName");
            return (Criteria) this;
        }

        public Criteria andAppraisalNameNotEqualTo(String value) {
            addCriterion("APPRAISAL_NAME <>", value, "appraisalName");
            return (Criteria) this;
        }

        public Criteria andAppraisalNameGreaterThan(String value) {
            addCriterion("APPRAISAL_NAME >", value, "appraisalName");
            return (Criteria) this;
        }

        public Criteria andAppraisalNameGreaterThanOrEqualTo(String value) {
            addCriterion("APPRAISAL_NAME >=", value, "appraisalName");
            return (Criteria) this;
        }

        public Criteria andAppraisalNameLessThan(String value) {
            addCriterion("APPRAISAL_NAME <", value, "appraisalName");
            return (Criteria) this;
        }

        public Criteria andAppraisalNameLessThanOrEqualTo(String value) {
            addCriterion("APPRAISAL_NAME <=", value, "appraisalName");
            return (Criteria) this;
        }

        public Criteria andAppraisalNameLike(String value) {
            addCriterion("APPRAISAL_NAME like", value, "appraisalName");
            return (Criteria) this;
        }

        public Criteria andAppraisalNameNotLike(String value) {
            addCriterion("APPRAISAL_NAME not like", value, "appraisalName");
            return (Criteria) this;
        }

        public Criteria andAppraisalNameIn(List<String> values) {
            addCriterion("APPRAISAL_NAME in", values, "appraisalName");
            return (Criteria) this;
        }

        public Criteria andAppraisalNameNotIn(List<String> values) {
            addCriterion("APPRAISAL_NAME not in", values, "appraisalName");
            return (Criteria) this;
        }

        public Criteria andAppraisalNameBetween(String value1, String value2) {
            addCriterion("APPRAISAL_NAME between", value1, value2, "appraisalName");
            return (Criteria) this;
        }

        public Criteria andAppraisalNameNotBetween(String value1, String value2) {
            addCriterion("APPRAISAL_NAME not between", value1, value2, "appraisalName");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdIsNull() {
            addCriterion("ORG_BELONG_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdIsNotNull() {
            addCriterion("ORG_BELONG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdEqualTo(String value) {
            addCriterion("ORG_BELONG_ID =", value, "orgBelongId");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdNotEqualTo(String value) {
            addCriterion("ORG_BELONG_ID <>", value, "orgBelongId");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdGreaterThan(String value) {
            addCriterion("ORG_BELONG_ID >", value, "orgBelongId");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_BELONG_ID >=", value, "orgBelongId");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdLessThan(String value) {
            addCriterion("ORG_BELONG_ID <", value, "orgBelongId");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_BELONG_ID <=", value, "orgBelongId");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdLike(String value) {
            addCriterion("ORG_BELONG_ID like", value, "orgBelongId");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdNotLike(String value) {
            addCriterion("ORG_BELONG_ID not like", value, "orgBelongId");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdIn(List<String> values) {
            addCriterion("ORG_BELONG_ID in", values, "orgBelongId");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdNotIn(List<String> values) {
            addCriterion("ORG_BELONG_ID not in", values, "orgBelongId");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdBetween(String value1, String value2) {
            addCriterion("ORG_BELONG_ID between", value1, value2, "orgBelongId");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdNotBetween(String value1, String value2) {
            addCriterion("ORG_BELONG_ID not between", value1, value2, "orgBelongId");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameIsNull() {
            addCriterion("ORG_BELONG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameIsNotNull() {
            addCriterion("ORG_BELONG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameEqualTo(String value) {
            addCriterion("ORG_BELONG_NAME =", value, "orgBelongName");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameNotEqualTo(String value) {
            addCriterion("ORG_BELONG_NAME <>", value, "orgBelongName");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameGreaterThan(String value) {
            addCriterion("ORG_BELONG_NAME >", value, "orgBelongName");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_BELONG_NAME >=", value, "orgBelongName");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameLessThan(String value) {
            addCriterion("ORG_BELONG_NAME <", value, "orgBelongName");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_BELONG_NAME <=", value, "orgBelongName");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameLike(String value) {
            addCriterion("ORG_BELONG_NAME like", value, "orgBelongName");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameNotLike(String value) {
            addCriterion("ORG_BELONG_NAME not like", value, "orgBelongName");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameIn(List<String> values) {
            addCriterion("ORG_BELONG_NAME in", values, "orgBelongName");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameNotIn(List<String> values) {
            addCriterion("ORG_BELONG_NAME not in", values, "orgBelongName");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameBetween(String value1, String value2) {
            addCriterion("ORG_BELONG_NAME between", value1, value2, "orgBelongName");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameNotBetween(String value1, String value2) {
            addCriterion("ORG_BELONG_NAME not between", value1, value2, "orgBelongName");
            return (Criteria) this;
        }

        public Criteria andAppraisalDeptIsNull() {
            addCriterion("APPRAISAL_DEPT is null");
            return (Criteria) this;
        }

        public Criteria andAppraisalDeptIsNotNull() {
            addCriterion("APPRAISAL_DEPT is not null");
            return (Criteria) this;
        }

        public Criteria andAppraisalDeptEqualTo(String value) {
            addCriterion("APPRAISAL_DEPT =", value, "appraisalDept");
            return (Criteria) this;
        }

        public Criteria andAppraisalDeptNotEqualTo(String value) {
            addCriterion("APPRAISAL_DEPT <>", value, "appraisalDept");
            return (Criteria) this;
        }

        public Criteria andAppraisalDeptGreaterThan(String value) {
            addCriterion("APPRAISAL_DEPT >", value, "appraisalDept");
            return (Criteria) this;
        }

        public Criteria andAppraisalDeptGreaterThanOrEqualTo(String value) {
            addCriterion("APPRAISAL_DEPT >=", value, "appraisalDept");
            return (Criteria) this;
        }

        public Criteria andAppraisalDeptLessThan(String value) {
            addCriterion("APPRAISAL_DEPT <", value, "appraisalDept");
            return (Criteria) this;
        }

        public Criteria andAppraisalDeptLessThanOrEqualTo(String value) {
            addCriterion("APPRAISAL_DEPT <=", value, "appraisalDept");
            return (Criteria) this;
        }

        public Criteria andAppraisalDeptLike(String value) {
            addCriterion("APPRAISAL_DEPT like", value, "appraisalDept");
            return (Criteria) this;
        }

        public Criteria andAppraisalDeptNotLike(String value) {
            addCriterion("APPRAISAL_DEPT not like", value, "appraisalDept");
            return (Criteria) this;
        }

        public Criteria andAppraisalDeptIn(List<String> values) {
            addCriterion("APPRAISAL_DEPT in", values, "appraisalDept");
            return (Criteria) this;
        }

        public Criteria andAppraisalDeptNotIn(List<String> values) {
            addCriterion("APPRAISAL_DEPT not in", values, "appraisalDept");
            return (Criteria) this;
        }

        public Criteria andAppraisalDeptBetween(String value1, String value2) {
            addCriterion("APPRAISAL_DEPT between", value1, value2, "appraisalDept");
            return (Criteria) this;
        }

        public Criteria andAppraisalDeptNotBetween(String value1, String value2) {
            addCriterion("APPRAISAL_DEPT not between", value1, value2, "appraisalDept");
            return (Criteria) this;
        }

        public Criteria andAppraisalDateIsNull() {
            addCriterion("APPRAISAL_DATE is null");
            return (Criteria) this;
        }

        public Criteria andAppraisalDateIsNotNull() {
            addCriterion("APPRAISAL_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andAppraisalDateEqualTo(String value) {
            addCriterion("APPRAISAL_DATE =", value, "appraisalDate");
            return (Criteria) this;
        }

        public Criteria andAppraisalDateNotEqualTo(String value) {
            addCriterion("APPRAISAL_DATE <>", value, "appraisalDate");
            return (Criteria) this;
        }

        public Criteria andAppraisalDateGreaterThan(String value) {
            addCriterion("APPRAISAL_DATE >", value, "appraisalDate");
            return (Criteria) this;
        }

        public Criteria andAppraisalDateGreaterThanOrEqualTo(String value) {
            addCriterion("APPRAISAL_DATE >=", value, "appraisalDate");
            return (Criteria) this;
        }

        public Criteria andAppraisalDateLessThan(String value) {
            addCriterion("APPRAISAL_DATE <", value, "appraisalDate");
            return (Criteria) this;
        }

        public Criteria andAppraisalDateLessThanOrEqualTo(String value) {
            addCriterion("APPRAISAL_DATE <=", value, "appraisalDate");
            return (Criteria) this;
        }

        public Criteria andAppraisalDateLike(String value) {
            addCriterion("APPRAISAL_DATE like", value, "appraisalDate");
            return (Criteria) this;
        }

        public Criteria andAppraisalDateNotLike(String value) {
            addCriterion("APPRAISAL_DATE not like", value, "appraisalDate");
            return (Criteria) this;
        }

        public Criteria andAppraisalDateIn(List<String> values) {
            addCriterion("APPRAISAL_DATE in", values, "appraisalDate");
            return (Criteria) this;
        }

        public Criteria andAppraisalDateNotIn(List<String> values) {
            addCriterion("APPRAISAL_DATE not in", values, "appraisalDate");
            return (Criteria) this;
        }

        public Criteria andAppraisalDateBetween(String value1, String value2) {
            addCriterion("APPRAISAL_DATE between", value1, value2, "appraisalDate");
            return (Criteria) this;
        }

        public Criteria andAppraisalDateNotBetween(String value1, String value2) {
            addCriterion("APPRAISAL_DATE not between", value1, value2, "appraisalDate");
            return (Criteria) this;
        }

        public Criteria andAppraisalCodeIsNull() {
            addCriterion("APPRAISAL_CODE is null");
            return (Criteria) this;
        }

        public Criteria andAppraisalCodeIsNotNull() {
            addCriterion("APPRAISAL_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andAppraisalCodeEqualTo(String value) {
            addCriterion("APPRAISAL_CODE =", value, "appraisalCode");
            return (Criteria) this;
        }

        public Criteria andAppraisalCodeNotEqualTo(String value) {
            addCriterion("APPRAISAL_CODE <>", value, "appraisalCode");
            return (Criteria) this;
        }

        public Criteria andAppraisalCodeGreaterThan(String value) {
            addCriterion("APPRAISAL_CODE >", value, "appraisalCode");
            return (Criteria) this;
        }

        public Criteria andAppraisalCodeGreaterThanOrEqualTo(String value) {
            addCriterion("APPRAISAL_CODE >=", value, "appraisalCode");
            return (Criteria) this;
        }

        public Criteria andAppraisalCodeLessThan(String value) {
            addCriterion("APPRAISAL_CODE <", value, "appraisalCode");
            return (Criteria) this;
        }

        public Criteria andAppraisalCodeLessThanOrEqualTo(String value) {
            addCriterion("APPRAISAL_CODE <=", value, "appraisalCode");
            return (Criteria) this;
        }

        public Criteria andAppraisalCodeLike(String value) {
            addCriterion("APPRAISAL_CODE like", value, "appraisalCode");
            return (Criteria) this;
        }

        public Criteria andAppraisalCodeNotLike(String value) {
            addCriterion("APPRAISAL_CODE not like", value, "appraisalCode");
            return (Criteria) this;
        }

        public Criteria andAppraisalCodeIn(List<String> values) {
            addCriterion("APPRAISAL_CODE in", values, "appraisalCode");
            return (Criteria) this;
        }

        public Criteria andAppraisalCodeNotIn(List<String> values) {
            addCriterion("APPRAISAL_CODE not in", values, "appraisalCode");
            return (Criteria) this;
        }

        public Criteria andAppraisalCodeBetween(String value1, String value2) {
            addCriterion("APPRAISAL_CODE between", value1, value2, "appraisalCode");
            return (Criteria) this;
        }

        public Criteria andAppraisalCodeNotBetween(String value1, String value2) {
            addCriterion("APPRAISAL_CODE not between", value1, value2, "appraisalCode");
            return (Criteria) this;
        }

        public Criteria andApprovalDateIsNull() {
            addCriterion("APPROVAL_DATE is null");
            return (Criteria) this;
        }

        public Criteria andApprovalDateIsNotNull() {
            addCriterion("APPROVAL_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalDateEqualTo(String value) {
            addCriterion("APPROVAL_DATE =", value, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateNotEqualTo(String value) {
            addCriterion("APPROVAL_DATE <>", value, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateGreaterThan(String value) {
            addCriterion("APPROVAL_DATE >", value, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateGreaterThanOrEqualTo(String value) {
            addCriterion("APPROVAL_DATE >=", value, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateLessThan(String value) {
            addCriterion("APPROVAL_DATE <", value, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateLessThanOrEqualTo(String value) {
            addCriterion("APPROVAL_DATE <=", value, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateLike(String value) {
            addCriterion("APPROVAL_DATE like", value, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateNotLike(String value) {
            addCriterion("APPROVAL_DATE not like", value, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateIn(List<String> values) {
            addCriterion("APPROVAL_DATE in", values, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateNotIn(List<String> values) {
            addCriterion("APPROVAL_DATE not in", values, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateBetween(String value1, String value2) {
            addCriterion("APPROVAL_DATE between", value1, value2, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andApprovalDateNotBetween(String value1, String value2) {
            addCriterion("APPROVAL_DATE not between", value1, value2, "approvalDate");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeIdIsNull() {
            addCriterion("APPRAISAL_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeIdIsNotNull() {
            addCriterion("APPRAISAL_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeIdEqualTo(String value) {
            addCriterion("APPRAISAL_TYPE_ID =", value, "appraisalTypeId");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeIdNotEqualTo(String value) {
            addCriterion("APPRAISAL_TYPE_ID <>", value, "appraisalTypeId");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeIdGreaterThan(String value) {
            addCriterion("APPRAISAL_TYPE_ID >", value, "appraisalTypeId");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("APPRAISAL_TYPE_ID >=", value, "appraisalTypeId");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeIdLessThan(String value) {
            addCriterion("APPRAISAL_TYPE_ID <", value, "appraisalTypeId");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeIdLessThanOrEqualTo(String value) {
            addCriterion("APPRAISAL_TYPE_ID <=", value, "appraisalTypeId");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeIdLike(String value) {
            addCriterion("APPRAISAL_TYPE_ID like", value, "appraisalTypeId");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeIdNotLike(String value) {
            addCriterion("APPRAISAL_TYPE_ID not like", value, "appraisalTypeId");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeIdIn(List<String> values) {
            addCriterion("APPRAISAL_TYPE_ID in", values, "appraisalTypeId");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeIdNotIn(List<String> values) {
            addCriterion("APPRAISAL_TYPE_ID not in", values, "appraisalTypeId");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeIdBetween(String value1, String value2) {
            addCriterion("APPRAISAL_TYPE_ID between", value1, value2, "appraisalTypeId");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeIdNotBetween(String value1, String value2) {
            addCriterion("APPRAISAL_TYPE_ID not between", value1, value2, "appraisalTypeId");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeNameIsNull() {
            addCriterion("APPRAISAL_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeNameIsNotNull() {
            addCriterion("APPRAISAL_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeNameEqualTo(String value) {
            addCriterion("APPRAISAL_TYPE_NAME =", value, "appraisalTypeName");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeNameNotEqualTo(String value) {
            addCriterion("APPRAISAL_TYPE_NAME <>", value, "appraisalTypeName");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeNameGreaterThan(String value) {
            addCriterion("APPRAISAL_TYPE_NAME >", value, "appraisalTypeName");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("APPRAISAL_TYPE_NAME >=", value, "appraisalTypeName");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeNameLessThan(String value) {
            addCriterion("APPRAISAL_TYPE_NAME <", value, "appraisalTypeName");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeNameLessThanOrEqualTo(String value) {
            addCriterion("APPRAISAL_TYPE_NAME <=", value, "appraisalTypeName");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeNameLike(String value) {
            addCriterion("APPRAISAL_TYPE_NAME like", value, "appraisalTypeName");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeNameNotLike(String value) {
            addCriterion("APPRAISAL_TYPE_NAME not like", value, "appraisalTypeName");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeNameIn(List<String> values) {
            addCriterion("APPRAISAL_TYPE_NAME in", values, "appraisalTypeName");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeNameNotIn(List<String> values) {
            addCriterion("APPRAISAL_TYPE_NAME not in", values, "appraisalTypeName");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeNameBetween(String value1, String value2) {
            addCriterion("APPRAISAL_TYPE_NAME between", value1, value2, "appraisalTypeName");
            return (Criteria) this;
        }

        public Criteria andAppraisalTypeNameNotBetween(String value1, String value2) {
            addCriterion("APPRAISAL_TYPE_NAME not between", value1, value2, "appraisalTypeName");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultIdIsNull() {
            addCriterion("APPRAISAL_RESULT_ID is null");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultIdIsNotNull() {
            addCriterion("APPRAISAL_RESULT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultIdEqualTo(String value) {
            addCriterion("APPRAISAL_RESULT_ID =", value, "appraisalResultId");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultIdNotEqualTo(String value) {
            addCriterion("APPRAISAL_RESULT_ID <>", value, "appraisalResultId");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultIdGreaterThan(String value) {
            addCriterion("APPRAISAL_RESULT_ID >", value, "appraisalResultId");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultIdGreaterThanOrEqualTo(String value) {
            addCriterion("APPRAISAL_RESULT_ID >=", value, "appraisalResultId");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultIdLessThan(String value) {
            addCriterion("APPRAISAL_RESULT_ID <", value, "appraisalResultId");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultIdLessThanOrEqualTo(String value) {
            addCriterion("APPRAISAL_RESULT_ID <=", value, "appraisalResultId");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultIdLike(String value) {
            addCriterion("APPRAISAL_RESULT_ID like", value, "appraisalResultId");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultIdNotLike(String value) {
            addCriterion("APPRAISAL_RESULT_ID not like", value, "appraisalResultId");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultIdIn(List<String> values) {
            addCriterion("APPRAISAL_RESULT_ID in", values, "appraisalResultId");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultIdNotIn(List<String> values) {
            addCriterion("APPRAISAL_RESULT_ID not in", values, "appraisalResultId");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultIdBetween(String value1, String value2) {
            addCriterion("APPRAISAL_RESULT_ID between", value1, value2, "appraisalResultId");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultIdNotBetween(String value1, String value2) {
            addCriterion("APPRAISAL_RESULT_ID not between", value1, value2, "appraisalResultId");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultNameIsNull() {
            addCriterion("APPRAISAL_RESULT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultNameIsNotNull() {
            addCriterion("APPRAISAL_RESULT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultNameEqualTo(String value) {
            addCriterion("APPRAISAL_RESULT_NAME =", value, "appraisalResultName");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultNameNotEqualTo(String value) {
            addCriterion("APPRAISAL_RESULT_NAME <>", value, "appraisalResultName");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultNameGreaterThan(String value) {
            addCriterion("APPRAISAL_RESULT_NAME >", value, "appraisalResultName");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultNameGreaterThanOrEqualTo(String value) {
            addCriterion("APPRAISAL_RESULT_NAME >=", value, "appraisalResultName");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultNameLessThan(String value) {
            addCriterion("APPRAISAL_RESULT_NAME <", value, "appraisalResultName");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultNameLessThanOrEqualTo(String value) {
            addCriterion("APPRAISAL_RESULT_NAME <=", value, "appraisalResultName");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultNameLike(String value) {
            addCriterion("APPRAISAL_RESULT_NAME like", value, "appraisalResultName");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultNameNotLike(String value) {
            addCriterion("APPRAISAL_RESULT_NAME not like", value, "appraisalResultName");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultNameIn(List<String> values) {
            addCriterion("APPRAISAL_RESULT_NAME in", values, "appraisalResultName");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultNameNotIn(List<String> values) {
            addCriterion("APPRAISAL_RESULT_NAME not in", values, "appraisalResultName");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultNameBetween(String value1, String value2) {
            addCriterion("APPRAISAL_RESULT_NAME between", value1, value2, "appraisalResultName");
            return (Criteria) this;
        }

        public Criteria andAppraisalResultNameNotBetween(String value1, String value2) {
            addCriterion("APPRAISAL_RESULT_NAME not between", value1, value2, "appraisalResultName");
            return (Criteria) this;
        }

        public Criteria andFinishTypeIdIsNull() {
            addCriterion("FINISH_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andFinishTypeIdIsNotNull() {
            addCriterion("FINISH_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFinishTypeIdEqualTo(String value) {
            addCriterion("FINISH_TYPE_ID =", value, "finishTypeId");
            return (Criteria) this;
        }

        public Criteria andFinishTypeIdNotEqualTo(String value) {
            addCriterion("FINISH_TYPE_ID <>", value, "finishTypeId");
            return (Criteria) this;
        }

        public Criteria andFinishTypeIdGreaterThan(String value) {
            addCriterion("FINISH_TYPE_ID >", value, "finishTypeId");
            return (Criteria) this;
        }

        public Criteria andFinishTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("FINISH_TYPE_ID >=", value, "finishTypeId");
            return (Criteria) this;
        }

        public Criteria andFinishTypeIdLessThan(String value) {
            addCriterion("FINISH_TYPE_ID <", value, "finishTypeId");
            return (Criteria) this;
        }

        public Criteria andFinishTypeIdLessThanOrEqualTo(String value) {
            addCriterion("FINISH_TYPE_ID <=", value, "finishTypeId");
            return (Criteria) this;
        }

        public Criteria andFinishTypeIdLike(String value) {
            addCriterion("FINISH_TYPE_ID like", value, "finishTypeId");
            return (Criteria) this;
        }

        public Criteria andFinishTypeIdNotLike(String value) {
            addCriterion("FINISH_TYPE_ID not like", value, "finishTypeId");
            return (Criteria) this;
        }

        public Criteria andFinishTypeIdIn(List<String> values) {
            addCriterion("FINISH_TYPE_ID in", values, "finishTypeId");
            return (Criteria) this;
        }

        public Criteria andFinishTypeIdNotIn(List<String> values) {
            addCriterion("FINISH_TYPE_ID not in", values, "finishTypeId");
            return (Criteria) this;
        }

        public Criteria andFinishTypeIdBetween(String value1, String value2) {
            addCriterion("FINISH_TYPE_ID between", value1, value2, "finishTypeId");
            return (Criteria) this;
        }

        public Criteria andFinishTypeIdNotBetween(String value1, String value2) {
            addCriterion("FINISH_TYPE_ID not between", value1, value2, "finishTypeId");
            return (Criteria) this;
        }

        public Criteria andFinishTypeNameIsNull() {
            addCriterion("FINISH_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFinishTypeNameIsNotNull() {
            addCriterion("FINISH_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFinishTypeNameEqualTo(String value) {
            addCriterion("FINISH_TYPE_NAME =", value, "finishTypeName");
            return (Criteria) this;
        }

        public Criteria andFinishTypeNameNotEqualTo(String value) {
            addCriterion("FINISH_TYPE_NAME <>", value, "finishTypeName");
            return (Criteria) this;
        }

        public Criteria andFinishTypeNameGreaterThan(String value) {
            addCriterion("FINISH_TYPE_NAME >", value, "finishTypeName");
            return (Criteria) this;
        }

        public Criteria andFinishTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("FINISH_TYPE_NAME >=", value, "finishTypeName");
            return (Criteria) this;
        }

        public Criteria andFinishTypeNameLessThan(String value) {
            addCriterion("FINISH_TYPE_NAME <", value, "finishTypeName");
            return (Criteria) this;
        }

        public Criteria andFinishTypeNameLessThanOrEqualTo(String value) {
            addCriterion("FINISH_TYPE_NAME <=", value, "finishTypeName");
            return (Criteria) this;
        }

        public Criteria andFinishTypeNameLike(String value) {
            addCriterion("FINISH_TYPE_NAME like", value, "finishTypeName");
            return (Criteria) this;
        }

        public Criteria andFinishTypeNameNotLike(String value) {
            addCriterion("FINISH_TYPE_NAME not like", value, "finishTypeName");
            return (Criteria) this;
        }

        public Criteria andFinishTypeNameIn(List<String> values) {
            addCriterion("FINISH_TYPE_NAME in", values, "finishTypeName");
            return (Criteria) this;
        }

        public Criteria andFinishTypeNameNotIn(List<String> values) {
            addCriterion("FINISH_TYPE_NAME not in", values, "finishTypeName");
            return (Criteria) this;
        }

        public Criteria andFinishTypeNameBetween(String value1, String value2) {
            addCriterion("FINISH_TYPE_NAME between", value1, value2, "finishTypeName");
            return (Criteria) this;
        }

        public Criteria andFinishTypeNameNotBetween(String value1, String value2) {
            addCriterion("FINISH_TYPE_NAME not between", value1, value2, "finishTypeName");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNull() {
            addCriterion("CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(String value) {
            addCriterion("CATEGORY_ID =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(String value) {
            addCriterion("CATEGORY_ID <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(String value) {
            addCriterion("CATEGORY_ID >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("CATEGORY_ID >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(String value) {
            addCriterion("CATEGORY_ID <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("CATEGORY_ID <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLike(String value) {
            addCriterion("CATEGORY_ID like", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotLike(String value) {
            addCriterion("CATEGORY_ID not like", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<String> values) {
            addCriterion("CATEGORY_ID in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<String> values) {
            addCriterion("CATEGORY_ID not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(String value1, String value2) {
            addCriterion("CATEGORY_ID between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(String value1, String value2) {
            addCriterion("CATEGORY_ID not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryNameIsNull() {
            addCriterion("CATEGORY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCategoryNameIsNotNull() {
            addCriterion("CATEGORY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryNameEqualTo(String value) {
            addCriterion("CATEGORY_NAME =", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameNotEqualTo(String value) {
            addCriterion("CATEGORY_NAME <>", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameGreaterThan(String value) {
            addCriterion("CATEGORY_NAME >", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("CATEGORY_NAME >=", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameLessThan(String value) {
            addCriterion("CATEGORY_NAME <", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("CATEGORY_NAME <=", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameLike(String value) {
            addCriterion("CATEGORY_NAME like", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameNotLike(String value) {
            addCriterion("CATEGORY_NAME not like", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameIn(List<String> values) {
            addCriterion("CATEGORY_NAME in", values, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameNotIn(List<String> values) {
            addCriterion("CATEGORY_NAME not in", values, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameBetween(String value1, String value2) {
            addCriterion("CATEGORY_NAME between", value1, value2, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameNotBetween(String value1, String value2) {
            addCriterion("CATEGORY_NAME not between", value1, value2, "categoryName");
            return (Criteria) this;
        }

        public Criteria andProjStartDateIsNull() {
            addCriterion("PROJ_START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andProjStartDateIsNotNull() {
            addCriterion("PROJ_START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andProjStartDateEqualTo(String value) {
            addCriterion("PROJ_START_DATE =", value, "projStartDate");
            return (Criteria) this;
        }

        public Criteria andProjStartDateNotEqualTo(String value) {
            addCriterion("PROJ_START_DATE <>", value, "projStartDate");
            return (Criteria) this;
        }

        public Criteria andProjStartDateGreaterThan(String value) {
            addCriterion("PROJ_START_DATE >", value, "projStartDate");
            return (Criteria) this;
        }

        public Criteria andProjStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_START_DATE >=", value, "projStartDate");
            return (Criteria) this;
        }

        public Criteria andProjStartDateLessThan(String value) {
            addCriterion("PROJ_START_DATE <", value, "projStartDate");
            return (Criteria) this;
        }

        public Criteria andProjStartDateLessThanOrEqualTo(String value) {
            addCriterion("PROJ_START_DATE <=", value, "projStartDate");
            return (Criteria) this;
        }

        public Criteria andProjStartDateLike(String value) {
            addCriterion("PROJ_START_DATE like", value, "projStartDate");
            return (Criteria) this;
        }

        public Criteria andProjStartDateNotLike(String value) {
            addCriterion("PROJ_START_DATE not like", value, "projStartDate");
            return (Criteria) this;
        }

        public Criteria andProjStartDateIn(List<String> values) {
            addCriterion("PROJ_START_DATE in", values, "projStartDate");
            return (Criteria) this;
        }

        public Criteria andProjStartDateNotIn(List<String> values) {
            addCriterion("PROJ_START_DATE not in", values, "projStartDate");
            return (Criteria) this;
        }

        public Criteria andProjStartDateBetween(String value1, String value2) {
            addCriterion("PROJ_START_DATE between", value1, value2, "projStartDate");
            return (Criteria) this;
        }

        public Criteria andProjStartDateNotBetween(String value1, String value2) {
            addCriterion("PROJ_START_DATE not between", value1, value2, "projStartDate");
            return (Criteria) this;
        }

        public Criteria andProjEndDateIsNull() {
            addCriterion("PROJ_END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andProjEndDateIsNotNull() {
            addCriterion("PROJ_END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andProjEndDateEqualTo(String value) {
            addCriterion("PROJ_END_DATE =", value, "projEndDate");
            return (Criteria) this;
        }

        public Criteria andProjEndDateNotEqualTo(String value) {
            addCriterion("PROJ_END_DATE <>", value, "projEndDate");
            return (Criteria) this;
        }

        public Criteria andProjEndDateGreaterThan(String value) {
            addCriterion("PROJ_END_DATE >", value, "projEndDate");
            return (Criteria) this;
        }

        public Criteria andProjEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_END_DATE >=", value, "projEndDate");
            return (Criteria) this;
        }

        public Criteria andProjEndDateLessThan(String value) {
            addCriterion("PROJ_END_DATE <", value, "projEndDate");
            return (Criteria) this;
        }

        public Criteria andProjEndDateLessThanOrEqualTo(String value) {
            addCriterion("PROJ_END_DATE <=", value, "projEndDate");
            return (Criteria) this;
        }

        public Criteria andProjEndDateLike(String value) {
            addCriterion("PROJ_END_DATE like", value, "projEndDate");
            return (Criteria) this;
        }

        public Criteria andProjEndDateNotLike(String value) {
            addCriterion("PROJ_END_DATE not like", value, "projEndDate");
            return (Criteria) this;
        }

        public Criteria andProjEndDateIn(List<String> values) {
            addCriterion("PROJ_END_DATE in", values, "projEndDate");
            return (Criteria) this;
        }

        public Criteria andProjEndDateNotIn(List<String> values) {
            addCriterion("PROJ_END_DATE not in", values, "projEndDate");
            return (Criteria) this;
        }

        public Criteria andProjEndDateBetween(String value1, String value2) {
            addCriterion("PROJ_END_DATE between", value1, value2, "projEndDate");
            return (Criteria) this;
        }

        public Criteria andProjEndDateNotBetween(String value1, String value2) {
            addCriterion("PROJ_END_DATE not between", value1, value2, "projEndDate");
            return (Criteria) this;
        }

        public Criteria andFinishOrgIsNull() {
            addCriterion("FINISH_ORG is null");
            return (Criteria) this;
        }

        public Criteria andFinishOrgIsNotNull() {
            addCriterion("FINISH_ORG is not null");
            return (Criteria) this;
        }

        public Criteria andFinishOrgEqualTo(String value) {
            addCriterion("FINISH_ORG =", value, "finishOrg");
            return (Criteria) this;
        }

        public Criteria andFinishOrgNotEqualTo(String value) {
            addCriterion("FINISH_ORG <>", value, "finishOrg");
            return (Criteria) this;
        }

        public Criteria andFinishOrgGreaterThan(String value) {
            addCriterion("FINISH_ORG >", value, "finishOrg");
            return (Criteria) this;
        }

        public Criteria andFinishOrgGreaterThanOrEqualTo(String value) {
            addCriterion("FINISH_ORG >=", value, "finishOrg");
            return (Criteria) this;
        }

        public Criteria andFinishOrgLessThan(String value) {
            addCriterion("FINISH_ORG <", value, "finishOrg");
            return (Criteria) this;
        }

        public Criteria andFinishOrgLessThanOrEqualTo(String value) {
            addCriterion("FINISH_ORG <=", value, "finishOrg");
            return (Criteria) this;
        }

        public Criteria andFinishOrgLike(String value) {
            addCriterion("FINISH_ORG like", value, "finishOrg");
            return (Criteria) this;
        }

        public Criteria andFinishOrgNotLike(String value) {
            addCriterion("FINISH_ORG not like", value, "finishOrg");
            return (Criteria) this;
        }

        public Criteria andFinishOrgIn(List<String> values) {
            addCriterion("FINISH_ORG in", values, "finishOrg");
            return (Criteria) this;
        }

        public Criteria andFinishOrgNotIn(List<String> values) {
            addCriterion("FINISH_ORG not in", values, "finishOrg");
            return (Criteria) this;
        }

        public Criteria andFinishOrgBetween(String value1, String value2) {
            addCriterion("FINISH_ORG between", value1, value2, "finishOrg");
            return (Criteria) this;
        }

        public Criteria andFinishOrgNotBetween(String value1, String value2) {
            addCriterion("FINISH_ORG not between", value1, value2, "finishOrg");
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