package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ResBaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResBaseExample() {
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

        public Criteria andEmailIsNull() {
            addCriterion("EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("EMAIL =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("EMAIL <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("EMAIL >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("EMAIL <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("EMAIL <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("EMAIL like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("EMAIL not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("EMAIL in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("EMAIL not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("EMAIL between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("EMAIL not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andBaseGradeIdIsNull() {
            addCriterion("BASE_GRADE_ID is null");
            return (Criteria) this;
        }

        public Criteria andBaseGradeIdIsNotNull() {
            addCriterion("BASE_GRADE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBaseGradeIdEqualTo(String value) {
            addCriterion("BASE_GRADE_ID =", value, "baseGradeId");
            return (Criteria) this;
        }

        public Criteria andBaseGradeIdNotEqualTo(String value) {
            addCriterion("BASE_GRADE_ID <>", value, "baseGradeId");
            return (Criteria) this;
        }

        public Criteria andBaseGradeIdGreaterThan(String value) {
            addCriterion("BASE_GRADE_ID >", value, "baseGradeId");
            return (Criteria) this;
        }

        public Criteria andBaseGradeIdGreaterThanOrEqualTo(String value) {
            addCriterion("BASE_GRADE_ID >=", value, "baseGradeId");
            return (Criteria) this;
        }

        public Criteria andBaseGradeIdLessThan(String value) {
            addCriterion("BASE_GRADE_ID <", value, "baseGradeId");
            return (Criteria) this;
        }

        public Criteria andBaseGradeIdLessThanOrEqualTo(String value) {
            addCriterion("BASE_GRADE_ID <=", value, "baseGradeId");
            return (Criteria) this;
        }

        public Criteria andBaseGradeIdLike(String value) {
            addCriterion("BASE_GRADE_ID like", value, "baseGradeId");
            return (Criteria) this;
        }

        public Criteria andBaseGradeIdNotLike(String value) {
            addCriterion("BASE_GRADE_ID not like", value, "baseGradeId");
            return (Criteria) this;
        }

        public Criteria andBaseGradeIdIn(List<String> values) {
            addCriterion("BASE_GRADE_ID in", values, "baseGradeId");
            return (Criteria) this;
        }

        public Criteria andBaseGradeIdNotIn(List<String> values) {
            addCriterion("BASE_GRADE_ID not in", values, "baseGradeId");
            return (Criteria) this;
        }

        public Criteria andBaseGradeIdBetween(String value1, String value2) {
            addCriterion("BASE_GRADE_ID between", value1, value2, "baseGradeId");
            return (Criteria) this;
        }

        public Criteria andBaseGradeIdNotBetween(String value1, String value2) {
            addCriterion("BASE_GRADE_ID not between", value1, value2, "baseGradeId");
            return (Criteria) this;
        }

        public Criteria andBaseGradeNameIsNull() {
            addCriterion("BASE_GRADE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBaseGradeNameIsNotNull() {
            addCriterion("BASE_GRADE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBaseGradeNameEqualTo(String value) {
            addCriterion("BASE_GRADE_NAME =", value, "baseGradeName");
            return (Criteria) this;
        }

        public Criteria andBaseGradeNameNotEqualTo(String value) {
            addCriterion("BASE_GRADE_NAME <>", value, "baseGradeName");
            return (Criteria) this;
        }

        public Criteria andBaseGradeNameGreaterThan(String value) {
            addCriterion("BASE_GRADE_NAME >", value, "baseGradeName");
            return (Criteria) this;
        }

        public Criteria andBaseGradeNameGreaterThanOrEqualTo(String value) {
            addCriterion("BASE_GRADE_NAME >=", value, "baseGradeName");
            return (Criteria) this;
        }

        public Criteria andBaseGradeNameLessThan(String value) {
            addCriterion("BASE_GRADE_NAME <", value, "baseGradeName");
            return (Criteria) this;
        }

        public Criteria andBaseGradeNameLessThanOrEqualTo(String value) {
            addCriterion("BASE_GRADE_NAME <=", value, "baseGradeName");
            return (Criteria) this;
        }

        public Criteria andBaseGradeNameLike(String value) {
            addCriterion("BASE_GRADE_NAME like", value, "baseGradeName");
            return (Criteria) this;
        }

        public Criteria andBaseGradeNameNotLike(String value) {
            addCriterion("BASE_GRADE_NAME not like", value, "baseGradeName");
            return (Criteria) this;
        }

        public Criteria andBaseGradeNameIn(List<String> values) {
            addCriterion("BASE_GRADE_NAME in", values, "baseGradeName");
            return (Criteria) this;
        }

        public Criteria andBaseGradeNameNotIn(List<String> values) {
            addCriterion("BASE_GRADE_NAME not in", values, "baseGradeName");
            return (Criteria) this;
        }

        public Criteria andBaseGradeNameBetween(String value1, String value2) {
            addCriterion("BASE_GRADE_NAME between", value1, value2, "baseGradeName");
            return (Criteria) this;
        }

        public Criteria andBaseGradeNameNotBetween(String value1, String value2) {
            addCriterion("BASE_GRADE_NAME not between", value1, value2, "baseGradeName");
            return (Criteria) this;
        }

        public Criteria andBaseTypeIdIsNull() {
            addCriterion("BASE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andBaseTypeIdIsNotNull() {
            addCriterion("BASE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBaseTypeIdEqualTo(String value) {
            addCriterion("BASE_TYPE_ID =", value, "baseTypeId");
            return (Criteria) this;
        }

        public Criteria andBaseTypeIdNotEqualTo(String value) {
            addCriterion("BASE_TYPE_ID <>", value, "baseTypeId");
            return (Criteria) this;
        }

        public Criteria andBaseTypeIdGreaterThan(String value) {
            addCriterion("BASE_TYPE_ID >", value, "baseTypeId");
            return (Criteria) this;
        }

        public Criteria andBaseTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("BASE_TYPE_ID >=", value, "baseTypeId");
            return (Criteria) this;
        }

        public Criteria andBaseTypeIdLessThan(String value) {
            addCriterion("BASE_TYPE_ID <", value, "baseTypeId");
            return (Criteria) this;
        }

        public Criteria andBaseTypeIdLessThanOrEqualTo(String value) {
            addCriterion("BASE_TYPE_ID <=", value, "baseTypeId");
            return (Criteria) this;
        }

        public Criteria andBaseTypeIdLike(String value) {
            addCriterion("BASE_TYPE_ID like", value, "baseTypeId");
            return (Criteria) this;
        }

        public Criteria andBaseTypeIdNotLike(String value) {
            addCriterion("BASE_TYPE_ID not like", value, "baseTypeId");
            return (Criteria) this;
        }

        public Criteria andBaseTypeIdIn(List<String> values) {
            addCriterion("BASE_TYPE_ID in", values, "baseTypeId");
            return (Criteria) this;
        }

        public Criteria andBaseTypeIdNotIn(List<String> values) {
            addCriterion("BASE_TYPE_ID not in", values, "baseTypeId");
            return (Criteria) this;
        }

        public Criteria andBaseTypeIdBetween(String value1, String value2) {
            addCriterion("BASE_TYPE_ID between", value1, value2, "baseTypeId");
            return (Criteria) this;
        }

        public Criteria andBaseTypeIdNotBetween(String value1, String value2) {
            addCriterion("BASE_TYPE_ID not between", value1, value2, "baseTypeId");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNameIsNull() {
            addCriterion("BASE_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNameIsNotNull() {
            addCriterion("BASE_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNameEqualTo(String value) {
            addCriterion("BASE_TYPE_NAME =", value, "baseTypeName");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNameNotEqualTo(String value) {
            addCriterion("BASE_TYPE_NAME <>", value, "baseTypeName");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNameGreaterThan(String value) {
            addCriterion("BASE_TYPE_NAME >", value, "baseTypeName");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("BASE_TYPE_NAME >=", value, "baseTypeName");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNameLessThan(String value) {
            addCriterion("BASE_TYPE_NAME <", value, "baseTypeName");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNameLessThanOrEqualTo(String value) {
            addCriterion("BASE_TYPE_NAME <=", value, "baseTypeName");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNameLike(String value) {
            addCriterion("BASE_TYPE_NAME like", value, "baseTypeName");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNameNotLike(String value) {
            addCriterion("BASE_TYPE_NAME not like", value, "baseTypeName");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNameIn(List<String> values) {
            addCriterion("BASE_TYPE_NAME in", values, "baseTypeName");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNameNotIn(List<String> values) {
            addCriterion("BASE_TYPE_NAME not in", values, "baseTypeName");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNameBetween(String value1, String value2) {
            addCriterion("BASE_TYPE_NAME between", value1, value2, "baseTypeName");
            return (Criteria) this;
        }

        public Criteria andBaseTypeNameNotBetween(String value1, String value2) {
            addCriterion("BASE_TYPE_NAME not between", value1, value2, "baseTypeName");
            return (Criteria) this;
        }

        public Criteria andBasePropertyIdIsNull() {
            addCriterion("BASE_PROPERTY_ID is null");
            return (Criteria) this;
        }

        public Criteria andBasePropertyIdIsNotNull() {
            addCriterion("BASE_PROPERTY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBasePropertyIdEqualTo(String value) {
            addCriterion("BASE_PROPERTY_ID =", value, "basePropertyId");
            return (Criteria) this;
        }

        public Criteria andBasePropertyIdNotEqualTo(String value) {
            addCriterion("BASE_PROPERTY_ID <>", value, "basePropertyId");
            return (Criteria) this;
        }

        public Criteria andBasePropertyIdGreaterThan(String value) {
            addCriterion("BASE_PROPERTY_ID >", value, "basePropertyId");
            return (Criteria) this;
        }

        public Criteria andBasePropertyIdGreaterThanOrEqualTo(String value) {
            addCriterion("BASE_PROPERTY_ID >=", value, "basePropertyId");
            return (Criteria) this;
        }

        public Criteria andBasePropertyIdLessThan(String value) {
            addCriterion("BASE_PROPERTY_ID <", value, "basePropertyId");
            return (Criteria) this;
        }

        public Criteria andBasePropertyIdLessThanOrEqualTo(String value) {
            addCriterion("BASE_PROPERTY_ID <=", value, "basePropertyId");
            return (Criteria) this;
        }

        public Criteria andBasePropertyIdLike(String value) {
            addCriterion("BASE_PROPERTY_ID like", value, "basePropertyId");
            return (Criteria) this;
        }

        public Criteria andBasePropertyIdNotLike(String value) {
            addCriterion("BASE_PROPERTY_ID not like", value, "basePropertyId");
            return (Criteria) this;
        }

        public Criteria andBasePropertyIdIn(List<String> values) {
            addCriterion("BASE_PROPERTY_ID in", values, "basePropertyId");
            return (Criteria) this;
        }

        public Criteria andBasePropertyIdNotIn(List<String> values) {
            addCriterion("BASE_PROPERTY_ID not in", values, "basePropertyId");
            return (Criteria) this;
        }

        public Criteria andBasePropertyIdBetween(String value1, String value2) {
            addCriterion("BASE_PROPERTY_ID between", value1, value2, "basePropertyId");
            return (Criteria) this;
        }

        public Criteria andBasePropertyIdNotBetween(String value1, String value2) {
            addCriterion("BASE_PROPERTY_ID not between", value1, value2, "basePropertyId");
            return (Criteria) this;
        }

        public Criteria andBasePropertyNameIsNull() {
            addCriterion("BASE_PROPERTY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBasePropertyNameIsNotNull() {
            addCriterion("BASE_PROPERTY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBasePropertyNameEqualTo(String value) {
            addCriterion("BASE_PROPERTY_NAME =", value, "basePropertyName");
            return (Criteria) this;
        }

        public Criteria andBasePropertyNameNotEqualTo(String value) {
            addCriterion("BASE_PROPERTY_NAME <>", value, "basePropertyName");
            return (Criteria) this;
        }

        public Criteria andBasePropertyNameGreaterThan(String value) {
            addCriterion("BASE_PROPERTY_NAME >", value, "basePropertyName");
            return (Criteria) this;
        }

        public Criteria andBasePropertyNameGreaterThanOrEqualTo(String value) {
            addCriterion("BASE_PROPERTY_NAME >=", value, "basePropertyName");
            return (Criteria) this;
        }

        public Criteria andBasePropertyNameLessThan(String value) {
            addCriterion("BASE_PROPERTY_NAME <", value, "basePropertyName");
            return (Criteria) this;
        }

        public Criteria andBasePropertyNameLessThanOrEqualTo(String value) {
            addCriterion("BASE_PROPERTY_NAME <=", value, "basePropertyName");
            return (Criteria) this;
        }

        public Criteria andBasePropertyNameLike(String value) {
            addCriterion("BASE_PROPERTY_NAME like", value, "basePropertyName");
            return (Criteria) this;
        }

        public Criteria andBasePropertyNameNotLike(String value) {
            addCriterion("BASE_PROPERTY_NAME not like", value, "basePropertyName");
            return (Criteria) this;
        }

        public Criteria andBasePropertyNameIn(List<String> values) {
            addCriterion("BASE_PROPERTY_NAME in", values, "basePropertyName");
            return (Criteria) this;
        }

        public Criteria andBasePropertyNameNotIn(List<String> values) {
            addCriterion("BASE_PROPERTY_NAME not in", values, "basePropertyName");
            return (Criteria) this;
        }

        public Criteria andBasePropertyNameBetween(String value1, String value2) {
            addCriterion("BASE_PROPERTY_NAME between", value1, value2, "basePropertyName");
            return (Criteria) this;
        }

        public Criteria andBasePropertyNameNotBetween(String value1, String value2) {
            addCriterion("BASE_PROPERTY_NAME not between", value1, value2, "basePropertyName");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberIdIsNull() {
            addCriterion("RES_APPROVAL_NUMBER_ID is null");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberIdIsNotNull() {
            addCriterion("RES_APPROVAL_NUMBER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberIdEqualTo(String value) {
            addCriterion("RES_APPROVAL_NUMBER_ID =", value, "resApprovalNumberId");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberIdNotEqualTo(String value) {
            addCriterion("RES_APPROVAL_NUMBER_ID <>", value, "resApprovalNumberId");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberIdGreaterThan(String value) {
            addCriterion("RES_APPROVAL_NUMBER_ID >", value, "resApprovalNumberId");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberIdGreaterThanOrEqualTo(String value) {
            addCriterion("RES_APPROVAL_NUMBER_ID >=", value, "resApprovalNumberId");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberIdLessThan(String value) {
            addCriterion("RES_APPROVAL_NUMBER_ID <", value, "resApprovalNumberId");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberIdLessThanOrEqualTo(String value) {
            addCriterion("RES_APPROVAL_NUMBER_ID <=", value, "resApprovalNumberId");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberIdLike(String value) {
            addCriterion("RES_APPROVAL_NUMBER_ID like", value, "resApprovalNumberId");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberIdNotLike(String value) {
            addCriterion("RES_APPROVAL_NUMBER_ID not like", value, "resApprovalNumberId");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberIdIn(List<String> values) {
            addCriterion("RES_APPROVAL_NUMBER_ID in", values, "resApprovalNumberId");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberIdNotIn(List<String> values) {
            addCriterion("RES_APPROVAL_NUMBER_ID not in", values, "resApprovalNumberId");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberIdBetween(String value1, String value2) {
            addCriterion("RES_APPROVAL_NUMBER_ID between", value1, value2, "resApprovalNumberId");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberIdNotBetween(String value1, String value2) {
            addCriterion("RES_APPROVAL_NUMBER_ID not between", value1, value2, "resApprovalNumberId");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberNameIsNull() {
            addCriterion("RES_APPROVAL_NUMBER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberNameIsNotNull() {
            addCriterion("RES_APPROVAL_NUMBER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberNameEqualTo(String value) {
            addCriterion("RES_APPROVAL_NUMBER_NAME =", value, "resApprovalNumberName");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberNameNotEqualTo(String value) {
            addCriterion("RES_APPROVAL_NUMBER_NAME <>", value, "resApprovalNumberName");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberNameGreaterThan(String value) {
            addCriterion("RES_APPROVAL_NUMBER_NAME >", value, "resApprovalNumberName");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberNameGreaterThanOrEqualTo(String value) {
            addCriterion("RES_APPROVAL_NUMBER_NAME >=", value, "resApprovalNumberName");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberNameLessThan(String value) {
            addCriterion("RES_APPROVAL_NUMBER_NAME <", value, "resApprovalNumberName");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberNameLessThanOrEqualTo(String value) {
            addCriterion("RES_APPROVAL_NUMBER_NAME <=", value, "resApprovalNumberName");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberNameLike(String value) {
            addCriterion("RES_APPROVAL_NUMBER_NAME like", value, "resApprovalNumberName");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberNameNotLike(String value) {
            addCriterion("RES_APPROVAL_NUMBER_NAME not like", value, "resApprovalNumberName");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberNameIn(List<String> values) {
            addCriterion("RES_APPROVAL_NUMBER_NAME in", values, "resApprovalNumberName");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberNameNotIn(List<String> values) {
            addCriterion("RES_APPROVAL_NUMBER_NAME not in", values, "resApprovalNumberName");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberNameBetween(String value1, String value2) {
            addCriterion("RES_APPROVAL_NUMBER_NAME between", value1, value2, "resApprovalNumberName");
            return (Criteria) this;
        }

        public Criteria andResApprovalNumberNameNotBetween(String value1, String value2) {
            addCriterion("RES_APPROVAL_NUMBER_NAME not between", value1, value2, "resApprovalNumberName");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberIdIsNull() {
            addCriterion("GPS_APPROVAL_NUMBER_ID is null");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberIdIsNotNull() {
            addCriterion("GPS_APPROVAL_NUMBER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberIdEqualTo(String value) {
            addCriterion("GPS_APPROVAL_NUMBER_ID =", value, "gpsApprovalNumberId");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberIdNotEqualTo(String value) {
            addCriterion("GPS_APPROVAL_NUMBER_ID <>", value, "gpsApprovalNumberId");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberIdGreaterThan(String value) {
            addCriterion("GPS_APPROVAL_NUMBER_ID >", value, "gpsApprovalNumberId");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberIdGreaterThanOrEqualTo(String value) {
            addCriterion("GPS_APPROVAL_NUMBER_ID >=", value, "gpsApprovalNumberId");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberIdLessThan(String value) {
            addCriterion("GPS_APPROVAL_NUMBER_ID <", value, "gpsApprovalNumberId");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberIdLessThanOrEqualTo(String value) {
            addCriterion("GPS_APPROVAL_NUMBER_ID <=", value, "gpsApprovalNumberId");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberIdLike(String value) {
            addCriterion("GPS_APPROVAL_NUMBER_ID like", value, "gpsApprovalNumberId");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberIdNotLike(String value) {
            addCriterion("GPS_APPROVAL_NUMBER_ID not like", value, "gpsApprovalNumberId");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberIdIn(List<String> values) {
            addCriterion("GPS_APPROVAL_NUMBER_ID in", values, "gpsApprovalNumberId");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberIdNotIn(List<String> values) {
            addCriterion("GPS_APPROVAL_NUMBER_ID not in", values, "gpsApprovalNumberId");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberIdBetween(String value1, String value2) {
            addCriterion("GPS_APPROVAL_NUMBER_ID between", value1, value2, "gpsApprovalNumberId");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberIdNotBetween(String value1, String value2) {
            addCriterion("GPS_APPROVAL_NUMBER_ID not between", value1, value2, "gpsApprovalNumberId");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberNameIsNull() {
            addCriterion("GPS_APPROVAL_NUMBER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberNameIsNotNull() {
            addCriterion("GPS_APPROVAL_NUMBER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberNameEqualTo(String value) {
            addCriterion("GPS_APPROVAL_NUMBER_NAME =", value, "gpsApprovalNumberName");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberNameNotEqualTo(String value) {
            addCriterion("GPS_APPROVAL_NUMBER_NAME <>", value, "gpsApprovalNumberName");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberNameGreaterThan(String value) {
            addCriterion("GPS_APPROVAL_NUMBER_NAME >", value, "gpsApprovalNumberName");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberNameGreaterThanOrEqualTo(String value) {
            addCriterion("GPS_APPROVAL_NUMBER_NAME >=", value, "gpsApprovalNumberName");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberNameLessThan(String value) {
            addCriterion("GPS_APPROVAL_NUMBER_NAME <", value, "gpsApprovalNumberName");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberNameLessThanOrEqualTo(String value) {
            addCriterion("GPS_APPROVAL_NUMBER_NAME <=", value, "gpsApprovalNumberName");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberNameLike(String value) {
            addCriterion("GPS_APPROVAL_NUMBER_NAME like", value, "gpsApprovalNumberName");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberNameNotLike(String value) {
            addCriterion("GPS_APPROVAL_NUMBER_NAME not like", value, "gpsApprovalNumberName");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberNameIn(List<String> values) {
            addCriterion("GPS_APPROVAL_NUMBER_NAME in", values, "gpsApprovalNumberName");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberNameNotIn(List<String> values) {
            addCriterion("GPS_APPROVAL_NUMBER_NAME not in", values, "gpsApprovalNumberName");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberNameBetween(String value1, String value2) {
            addCriterion("GPS_APPROVAL_NUMBER_NAME between", value1, value2, "gpsApprovalNumberName");
            return (Criteria) this;
        }

        public Criteria andGpsApprovalNumberNameNotBetween(String value1, String value2) {
            addCriterion("GPS_APPROVAL_NUMBER_NAME not between", value1, value2, "gpsApprovalNumberName");
            return (Criteria) this;
        }

        public Criteria andBaseStatusIdIsNull() {
            addCriterion("BASE_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andBaseStatusIdIsNotNull() {
            addCriterion("BASE_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBaseStatusIdEqualTo(String value) {
            addCriterion("BASE_STATUS_ID =", value, "baseStatusId");
            return (Criteria) this;
        }

        public Criteria andBaseStatusIdNotEqualTo(String value) {
            addCriterion("BASE_STATUS_ID <>", value, "baseStatusId");
            return (Criteria) this;
        }

        public Criteria andBaseStatusIdGreaterThan(String value) {
            addCriterion("BASE_STATUS_ID >", value, "baseStatusId");
            return (Criteria) this;
        }

        public Criteria andBaseStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("BASE_STATUS_ID >=", value, "baseStatusId");
            return (Criteria) this;
        }

        public Criteria andBaseStatusIdLessThan(String value) {
            addCriterion("BASE_STATUS_ID <", value, "baseStatusId");
            return (Criteria) this;
        }

        public Criteria andBaseStatusIdLessThanOrEqualTo(String value) {
            addCriterion("BASE_STATUS_ID <=", value, "baseStatusId");
            return (Criteria) this;
        }

        public Criteria andBaseStatusIdLike(String value) {
            addCriterion("BASE_STATUS_ID like", value, "baseStatusId");
            return (Criteria) this;
        }

        public Criteria andBaseStatusIdNotLike(String value) {
            addCriterion("BASE_STATUS_ID not like", value, "baseStatusId");
            return (Criteria) this;
        }

        public Criteria andBaseStatusIdIn(List<String> values) {
            addCriterion("BASE_STATUS_ID in", values, "baseStatusId");
            return (Criteria) this;
        }

        public Criteria andBaseStatusIdNotIn(List<String> values) {
            addCriterion("BASE_STATUS_ID not in", values, "baseStatusId");
            return (Criteria) this;
        }

        public Criteria andBaseStatusIdBetween(String value1, String value2) {
            addCriterion("BASE_STATUS_ID between", value1, value2, "baseStatusId");
            return (Criteria) this;
        }

        public Criteria andBaseStatusIdNotBetween(String value1, String value2) {
            addCriterion("BASE_STATUS_ID not between", value1, value2, "baseStatusId");
            return (Criteria) this;
        }

        public Criteria andBaseStatusNameIsNull() {
            addCriterion("BASE_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBaseStatusNameIsNotNull() {
            addCriterion("BASE_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBaseStatusNameEqualTo(String value) {
            addCriterion("BASE_STATUS_NAME =", value, "baseStatusName");
            return (Criteria) this;
        }

        public Criteria andBaseStatusNameNotEqualTo(String value) {
            addCriterion("BASE_STATUS_NAME <>", value, "baseStatusName");
            return (Criteria) this;
        }

        public Criteria andBaseStatusNameGreaterThan(String value) {
            addCriterion("BASE_STATUS_NAME >", value, "baseStatusName");
            return (Criteria) this;
        }

        public Criteria andBaseStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("BASE_STATUS_NAME >=", value, "baseStatusName");
            return (Criteria) this;
        }

        public Criteria andBaseStatusNameLessThan(String value) {
            addCriterion("BASE_STATUS_NAME <", value, "baseStatusName");
            return (Criteria) this;
        }

        public Criteria andBaseStatusNameLessThanOrEqualTo(String value) {
            addCriterion("BASE_STATUS_NAME <=", value, "baseStatusName");
            return (Criteria) this;
        }

        public Criteria andBaseStatusNameLike(String value) {
            addCriterion("BASE_STATUS_NAME like", value, "baseStatusName");
            return (Criteria) this;
        }

        public Criteria andBaseStatusNameNotLike(String value) {
            addCriterion("BASE_STATUS_NAME not like", value, "baseStatusName");
            return (Criteria) this;
        }

        public Criteria andBaseStatusNameIn(List<String> values) {
            addCriterion("BASE_STATUS_NAME in", values, "baseStatusName");
            return (Criteria) this;
        }

        public Criteria andBaseStatusNameNotIn(List<String> values) {
            addCriterion("BASE_STATUS_NAME not in", values, "baseStatusName");
            return (Criteria) this;
        }

        public Criteria andBaseStatusNameBetween(String value1, String value2) {
            addCriterion("BASE_STATUS_NAME between", value1, value2, "baseStatusName");
            return (Criteria) this;
        }

        public Criteria andBaseStatusNameNotBetween(String value1, String value2) {
            addCriterion("BASE_STATUS_NAME not between", value1, value2, "baseStatusName");
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

        public Criteria andSessionNumberIsNull() {
            addCriterion("SESSION_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIsNotNull() {
            addCriterion("SESSION_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andSessionNumberEqualTo(String value) {
            addCriterion("SESSION_NUMBER =", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotEqualTo(String value) {
            addCriterion("SESSION_NUMBER <>", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberGreaterThan(String value) {
            addCriterion("SESSION_NUMBER >", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberGreaterThanOrEqualTo(String value) {
            addCriterion("SESSION_NUMBER >=", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLessThan(String value) {
            addCriterion("SESSION_NUMBER <", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLessThanOrEqualTo(String value) {
            addCriterion("SESSION_NUMBER <=", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLike(String value) {
            addCriterion("SESSION_NUMBER like", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotLike(String value) {
            addCriterion("SESSION_NUMBER not like", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIn(List<String> values) {
            addCriterion("SESSION_NUMBER in", values, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotIn(List<String> values) {
            addCriterion("SESSION_NUMBER not in", values, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberBetween(String value1, String value2) {
            addCriterion("SESSION_NUMBER between", value1, value2, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotBetween(String value1, String value2) {
            addCriterion("SESSION_NUMBER not between", value1, value2, "sessionNumber");
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