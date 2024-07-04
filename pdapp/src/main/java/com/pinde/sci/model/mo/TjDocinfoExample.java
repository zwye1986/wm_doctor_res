package com.pinde.sci.model.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TjDocinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TjDocinfoExample() {
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

        public Criteria andOrgCodeIsNull() {
            addCriterion("ORG_CODE is null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNotNull() {
            addCriterion("ORG_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeEqualTo(String value) {
            addCriterion("ORG_CODE =", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotEqualTo(String value) {
            addCriterion("ORG_CODE <>", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThan(String value) {
            addCriterion("ORG_CODE >", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CODE >=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThan(String value) {
            addCriterion("ORG_CODE <", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("ORG_CODE <=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLike(String value) {
            addCriterion("ORG_CODE like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotLike(String value) {
            addCriterion("ORG_CODE not like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIn(List<String> values) {
            addCriterion("ORG_CODE in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotIn(List<String> values) {
            addCriterion("ORG_CODE not in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeBetween(String value1, String value2) {
            addCriterion("ORG_CODE between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotBetween(String value1, String value2) {
            addCriterion("ORG_CODE not between", value1, value2, "orgCode");
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

        public Criteria andDocroleIsNull() {
            addCriterion("DOCROLE is null");
            return (Criteria) this;
        }

        public Criteria andDocroleIsNotNull() {
            addCriterion("DOCROLE is not null");
            return (Criteria) this;
        }

        public Criteria andDocroleEqualTo(String value) {
            addCriterion("DOCROLE =", value, "docrole");
            return (Criteria) this;
        }

        public Criteria andDocroleNotEqualTo(String value) {
            addCriterion("DOCROLE <>", value, "docrole");
            return (Criteria) this;
        }

        public Criteria andDocroleGreaterThan(String value) {
            addCriterion("DOCROLE >", value, "docrole");
            return (Criteria) this;
        }

        public Criteria andDocroleGreaterThanOrEqualTo(String value) {
            addCriterion("DOCROLE >=", value, "docrole");
            return (Criteria) this;
        }

        public Criteria andDocroleLessThan(String value) {
            addCriterion("DOCROLE <", value, "docrole");
            return (Criteria) this;
        }

        public Criteria andDocroleLessThanOrEqualTo(String value) {
            addCriterion("DOCROLE <=", value, "docrole");
            return (Criteria) this;
        }

        public Criteria andDocroleLike(String value) {
            addCriterion("DOCROLE like", value, "docrole");
            return (Criteria) this;
        }

        public Criteria andDocroleNotLike(String value) {
            addCriterion("DOCROLE not like", value, "docrole");
            return (Criteria) this;
        }

        public Criteria andDocroleIn(List<String> values) {
            addCriterion("DOCROLE in", values, "docrole");
            return (Criteria) this;
        }

        public Criteria andDocroleNotIn(List<String> values) {
            addCriterion("DOCROLE not in", values, "docrole");
            return (Criteria) this;
        }

        public Criteria andDocroleBetween(String value1, String value2) {
            addCriterion("DOCROLE between", value1, value2, "docrole");
            return (Criteria) this;
        }

        public Criteria andDocroleNotBetween(String value1, String value2) {
            addCriterion("DOCROLE not between", value1, value2, "docrole");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("USER_NAME =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("USER_NAME <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("USER_NAME >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_NAME >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("USER_NAME <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("USER_NAME <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("USER_NAME like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("USER_NAME not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("USER_NAME in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("USER_NAME not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("USER_NAME between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("USER_NAME not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserPasswordIsNull() {
            addCriterion("USER_PASSWORD is null");
            return (Criteria) this;
        }

        public Criteria andUserPasswordIsNotNull() {
            addCriterion("USER_PASSWORD is not null");
            return (Criteria) this;
        }

        public Criteria andUserPasswordEqualTo(String value) {
            addCriterion("USER_PASSWORD =", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordNotEqualTo(String value) {
            addCriterion("USER_PASSWORD <>", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordGreaterThan(String value) {
            addCriterion("USER_PASSWORD >", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("USER_PASSWORD >=", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordLessThan(String value) {
            addCriterion("USER_PASSWORD <", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordLessThanOrEqualTo(String value) {
            addCriterion("USER_PASSWORD <=", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordLike(String value) {
            addCriterion("USER_PASSWORD like", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordNotLike(String value) {
            addCriterion("USER_PASSWORD not like", value, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordIn(List<String> values) {
            addCriterion("USER_PASSWORD in", values, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordNotIn(List<String> values) {
            addCriterion("USER_PASSWORD not in", values, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordBetween(String value1, String value2) {
            addCriterion("USER_PASSWORD between", value1, value2, "userPassword");
            return (Criteria) this;
        }

        public Criteria andUserPasswordNotBetween(String value1, String value2) {
            addCriterion("USER_PASSWORD not between", value1, value2, "userPassword");
            return (Criteria) this;
        }

        public Criteria andIdNoIsNull() {
            addCriterion("ID_NO is null");
            return (Criteria) this;
        }

        public Criteria andIdNoIsNotNull() {
            addCriterion("ID_NO is not null");
            return (Criteria) this;
        }

        public Criteria andIdNoEqualTo(String value) {
            addCriterion("ID_NO =", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotEqualTo(String value) {
            addCriterion("ID_NO <>", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoGreaterThan(String value) {
            addCriterion("ID_NO >", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoGreaterThanOrEqualTo(String value) {
            addCriterion("ID_NO >=", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLessThan(String value) {
            addCriterion("ID_NO <", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLessThanOrEqualTo(String value) {
            addCriterion("ID_NO <=", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLike(String value) {
            addCriterion("ID_NO like", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotLike(String value) {
            addCriterion("ID_NO not like", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoIn(List<String> values) {
            addCriterion("ID_NO in", values, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotIn(List<String> values) {
            addCriterion("ID_NO not in", values, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoBetween(String value1, String value2) {
            addCriterion("ID_NO between", value1, value2, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotBetween(String value1, String value2) {
            addCriterion("ID_NO not between", value1, value2, "idNo");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoIsNull() {
            addCriterion("QUALIFIED_NO is null");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoIsNotNull() {
            addCriterion("QUALIFIED_NO is not null");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoEqualTo(String value) {
            addCriterion("QUALIFIED_NO =", value, "qualifiedNo");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoNotEqualTo(String value) {
            addCriterion("QUALIFIED_NO <>", value, "qualifiedNo");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoGreaterThan(String value) {
            addCriterion("QUALIFIED_NO >", value, "qualifiedNo");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoGreaterThanOrEqualTo(String value) {
            addCriterion("QUALIFIED_NO >=", value, "qualifiedNo");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoLessThan(String value) {
            addCriterion("QUALIFIED_NO <", value, "qualifiedNo");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoLessThanOrEqualTo(String value) {
            addCriterion("QUALIFIED_NO <=", value, "qualifiedNo");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoLike(String value) {
            addCriterion("QUALIFIED_NO like", value, "qualifiedNo");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoNotLike(String value) {
            addCriterion("QUALIFIED_NO not like", value, "qualifiedNo");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoIn(List<String> values) {
            addCriterion("QUALIFIED_NO in", values, "qualifiedNo");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoNotIn(List<String> values) {
            addCriterion("QUALIFIED_NO not in", values, "qualifiedNo");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoBetween(String value1, String value2) {
            addCriterion("QUALIFIED_NO between", value1, value2, "qualifiedNo");
            return (Criteria) this;
        }

        public Criteria andQualifiedNoNotBetween(String value1, String value2) {
            addCriterion("QUALIFIED_NO not between", value1, value2, "qualifiedNo");
            return (Criteria) this;
        }

        public Criteria andSpeCodeIsNull() {
            addCriterion("SPE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andSpeCodeIsNotNull() {
            addCriterion("SPE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andSpeCodeEqualTo(String value) {
            addCriterion("SPE_CODE =", value, "speCode");
            return (Criteria) this;
        }

        public Criteria andSpeCodeNotEqualTo(String value) {
            addCriterion("SPE_CODE <>", value, "speCode");
            return (Criteria) this;
        }

        public Criteria andSpeCodeGreaterThan(String value) {
            addCriterion("SPE_CODE >", value, "speCode");
            return (Criteria) this;
        }

        public Criteria andSpeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_CODE >=", value, "speCode");
            return (Criteria) this;
        }

        public Criteria andSpeCodeLessThan(String value) {
            addCriterion("SPE_CODE <", value, "speCode");
            return (Criteria) this;
        }

        public Criteria andSpeCodeLessThanOrEqualTo(String value) {
            addCriterion("SPE_CODE <=", value, "speCode");
            return (Criteria) this;
        }

        public Criteria andSpeCodeLike(String value) {
            addCriterion("SPE_CODE like", value, "speCode");
            return (Criteria) this;
        }

        public Criteria andSpeCodeNotLike(String value) {
            addCriterion("SPE_CODE not like", value, "speCode");
            return (Criteria) this;
        }

        public Criteria andSpeCodeIn(List<String> values) {
            addCriterion("SPE_CODE in", values, "speCode");
            return (Criteria) this;
        }

        public Criteria andSpeCodeNotIn(List<String> values) {
            addCriterion("SPE_CODE not in", values, "speCode");
            return (Criteria) this;
        }

        public Criteria andSpeCodeBetween(String value1, String value2) {
            addCriterion("SPE_CODE between", value1, value2, "speCode");
            return (Criteria) this;
        }

        public Criteria andSpeCodeNotBetween(String value1, String value2) {
            addCriterion("SPE_CODE not between", value1, value2, "speCode");
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

        public Criteria andTicketNumIsNull() {
            addCriterion("TICKET_NUM is null");
            return (Criteria) this;
        }

        public Criteria andTicketNumIsNotNull() {
            addCriterion("TICKET_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andTicketNumEqualTo(String value) {
            addCriterion("TICKET_NUM =", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumNotEqualTo(String value) {
            addCriterion("TICKET_NUM <>", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumGreaterThan(String value) {
            addCriterion("TICKET_NUM >", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumGreaterThanOrEqualTo(String value) {
            addCriterion("TICKET_NUM >=", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumLessThan(String value) {
            addCriterion("TICKET_NUM <", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumLessThanOrEqualTo(String value) {
            addCriterion("TICKET_NUM <=", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumLike(String value) {
            addCriterion("TICKET_NUM like", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumNotLike(String value) {
            addCriterion("TICKET_NUM not like", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumIn(List<String> values) {
            addCriterion("TICKET_NUM in", values, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumNotIn(List<String> values) {
            addCriterion("TICKET_NUM not in", values, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumBetween(String value1, String value2) {
            addCriterion("TICKET_NUM between", value1, value2, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumNotBetween(String value1, String value2) {
            addCriterion("TICKET_NUM not between", value1, value2, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andUserSexIsNull() {
            addCriterion("USER_SEX is null");
            return (Criteria) this;
        }

        public Criteria andUserSexIsNotNull() {
            addCriterion("USER_SEX is not null");
            return (Criteria) this;
        }

        public Criteria andUserSexEqualTo(String value) {
            addCriterion("USER_SEX =", value, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexNotEqualTo(String value) {
            addCriterion("USER_SEX <>", value, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexGreaterThan(String value) {
            addCriterion("USER_SEX >", value, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexGreaterThanOrEqualTo(String value) {
            addCriterion("USER_SEX >=", value, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexLessThan(String value) {
            addCriterion("USER_SEX <", value, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexLessThanOrEqualTo(String value) {
            addCriterion("USER_SEX <=", value, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexLike(String value) {
            addCriterion("USER_SEX like", value, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexNotLike(String value) {
            addCriterion("USER_SEX not like", value, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexIn(List<String> values) {
            addCriterion("USER_SEX in", values, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexNotIn(List<String> values) {
            addCriterion("USER_SEX not in", values, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexBetween(String value1, String value2) {
            addCriterion("USER_SEX between", value1, value2, "userSex");
            return (Criteria) this;
        }

        public Criteria andUserSexNotBetween(String value1, String value2) {
            addCriterion("USER_SEX not between", value1, value2, "userSex");
            return (Criteria) this;
        }

        public Criteria andDegreeIsNull() {
            addCriterion("DEGREE is null");
            return (Criteria) this;
        }

        public Criteria andDegreeIsNotNull() {
            addCriterion("DEGREE is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeEqualTo(String value) {
            addCriterion("DEGREE =", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeNotEqualTo(String value) {
            addCriterion("DEGREE <>", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeGreaterThan(String value) {
            addCriterion("DEGREE >", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeGreaterThanOrEqualTo(String value) {
            addCriterion("DEGREE >=", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeLessThan(String value) {
            addCriterion("DEGREE <", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeLessThanOrEqualTo(String value) {
            addCriterion("DEGREE <=", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeLike(String value) {
            addCriterion("DEGREE like", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeNotLike(String value) {
            addCriterion("DEGREE not like", value, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeIn(List<String> values) {
            addCriterion("DEGREE in", values, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeNotIn(List<String> values) {
            addCriterion("DEGREE not in", values, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeBetween(String value1, String value2) {
            addCriterion("DEGREE between", value1, value2, "degree");
            return (Criteria) this;
        }

        public Criteria andDegreeNotBetween(String value1, String value2) {
            addCriterion("DEGREE not between", value1, value2, "degree");
            return (Criteria) this;
        }

        public Criteria andBefOrgNameIsNull() {
            addCriterion("BEF_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBefOrgNameIsNotNull() {
            addCriterion("BEF_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBefOrgNameEqualTo(String value) {
            addCriterion("BEF_ORG_NAME =", value, "befOrgName");
            return (Criteria) this;
        }

        public Criteria andBefOrgNameNotEqualTo(String value) {
            addCriterion("BEF_ORG_NAME <>", value, "befOrgName");
            return (Criteria) this;
        }

        public Criteria andBefOrgNameGreaterThan(String value) {
            addCriterion("BEF_ORG_NAME >", value, "befOrgName");
            return (Criteria) this;
        }

        public Criteria andBefOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("BEF_ORG_NAME >=", value, "befOrgName");
            return (Criteria) this;
        }

        public Criteria andBefOrgNameLessThan(String value) {
            addCriterion("BEF_ORG_NAME <", value, "befOrgName");
            return (Criteria) this;
        }

        public Criteria andBefOrgNameLessThanOrEqualTo(String value) {
            addCriterion("BEF_ORG_NAME <=", value, "befOrgName");
            return (Criteria) this;
        }

        public Criteria andBefOrgNameLike(String value) {
            addCriterion("BEF_ORG_NAME like", value, "befOrgName");
            return (Criteria) this;
        }

        public Criteria andBefOrgNameNotLike(String value) {
            addCriterion("BEF_ORG_NAME not like", value, "befOrgName");
            return (Criteria) this;
        }

        public Criteria andBefOrgNameIn(List<String> values) {
            addCriterion("BEF_ORG_NAME in", values, "befOrgName");
            return (Criteria) this;
        }

        public Criteria andBefOrgNameNotIn(List<String> values) {
            addCriterion("BEF_ORG_NAME not in", values, "befOrgName");
            return (Criteria) this;
        }

        public Criteria andBefOrgNameBetween(String value1, String value2) {
            addCriterion("BEF_ORG_NAME between", value1, value2, "befOrgName");
            return (Criteria) this;
        }

        public Criteria andBefOrgNameNotBetween(String value1, String value2) {
            addCriterion("BEF_ORG_NAME not between", value1, value2, "befOrgName");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNull() {
            addCriterion("USER_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNotNull() {
            addCriterion("USER_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneEqualTo(String value) {
            addCriterion("USER_PHONE =", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotEqualTo(String value) {
            addCriterion("USER_PHONE <>", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThan(String value) {
            addCriterion("USER_PHONE >", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("USER_PHONE >=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThan(String value) {
            addCriterion("USER_PHONE <", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThanOrEqualTo(String value) {
            addCriterion("USER_PHONE <=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLike(String value) {
            addCriterion("USER_PHONE like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotLike(String value) {
            addCriterion("USER_PHONE not like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIn(List<String> values) {
            addCriterion("USER_PHONE in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotIn(List<String> values) {
            addCriterion("USER_PHONE not in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneBetween(String value1, String value2) {
            addCriterion("USER_PHONE between", value1, value2, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotBetween(String value1, String value2) {
            addCriterion("USER_PHONE not between", value1, value2, "userPhone");
            return (Criteria) this;
        }

        public Criteria andPhonePathIsNull() {
            addCriterion("PHONE_PATH is null");
            return (Criteria) this;
        }

        public Criteria andPhonePathIsNotNull() {
            addCriterion("PHONE_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andPhonePathEqualTo(String value) {
            addCriterion("PHONE_PATH =", value, "phonePath");
            return (Criteria) this;
        }

        public Criteria andPhonePathNotEqualTo(String value) {
            addCriterion("PHONE_PATH <>", value, "phonePath");
            return (Criteria) this;
        }

        public Criteria andPhonePathGreaterThan(String value) {
            addCriterion("PHONE_PATH >", value, "phonePath");
            return (Criteria) this;
        }

        public Criteria andPhonePathGreaterThanOrEqualTo(String value) {
            addCriterion("PHONE_PATH >=", value, "phonePath");
            return (Criteria) this;
        }

        public Criteria andPhonePathLessThan(String value) {
            addCriterion("PHONE_PATH <", value, "phonePath");
            return (Criteria) this;
        }

        public Criteria andPhonePathLessThanOrEqualTo(String value) {
            addCriterion("PHONE_PATH <=", value, "phonePath");
            return (Criteria) this;
        }

        public Criteria andPhonePathLike(String value) {
            addCriterion("PHONE_PATH like", value, "phonePath");
            return (Criteria) this;
        }

        public Criteria andPhonePathNotLike(String value) {
            addCriterion("PHONE_PATH not like", value, "phonePath");
            return (Criteria) this;
        }

        public Criteria andPhonePathIn(List<String> values) {
            addCriterion("PHONE_PATH in", values, "phonePath");
            return (Criteria) this;
        }

        public Criteria andPhonePathNotIn(List<String> values) {
            addCriterion("PHONE_PATH not in", values, "phonePath");
            return (Criteria) this;
        }

        public Criteria andPhonePathBetween(String value1, String value2) {
            addCriterion("PHONE_PATH between", value1, value2, "phonePath");
            return (Criteria) this;
        }

        public Criteria andPhonePathNotBetween(String value1, String value2) {
            addCriterion("PHONE_PATH not between", value1, value2, "phonePath");
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

        public Criteria andSiteOneIsNull() {
            addCriterion("SITE_ONE is null");
            return (Criteria) this;
        }

        public Criteria andSiteOneIsNotNull() {
            addCriterion("SITE_ONE is not null");
            return (Criteria) this;
        }

        public Criteria andSiteOneEqualTo(String value) {
            addCriterion("SITE_ONE =", value, "siteOne");
            return (Criteria) this;
        }

        public Criteria andSiteOneNotEqualTo(String value) {
            addCriterion("SITE_ONE <>", value, "siteOne");
            return (Criteria) this;
        }

        public Criteria andSiteOneGreaterThan(String value) {
            addCriterion("SITE_ONE >", value, "siteOne");
            return (Criteria) this;
        }

        public Criteria andSiteOneGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_ONE >=", value, "siteOne");
            return (Criteria) this;
        }

        public Criteria andSiteOneLessThan(String value) {
            addCriterion("SITE_ONE <", value, "siteOne");
            return (Criteria) this;
        }

        public Criteria andSiteOneLessThanOrEqualTo(String value) {
            addCriterion("SITE_ONE <=", value, "siteOne");
            return (Criteria) this;
        }

        public Criteria andSiteOneLike(String value) {
            addCriterion("SITE_ONE like", value, "siteOne");
            return (Criteria) this;
        }

        public Criteria andSiteOneNotLike(String value) {
            addCriterion("SITE_ONE not like", value, "siteOne");
            return (Criteria) this;
        }

        public Criteria andSiteOneIn(List<String> values) {
            addCriterion("SITE_ONE in", values, "siteOne");
            return (Criteria) this;
        }

        public Criteria andSiteOneNotIn(List<String> values) {
            addCriterion("SITE_ONE not in", values, "siteOne");
            return (Criteria) this;
        }

        public Criteria andSiteOneBetween(String value1, String value2) {
            addCriterion("SITE_ONE between", value1, value2, "siteOne");
            return (Criteria) this;
        }

        public Criteria andSiteOneNotBetween(String value1, String value2) {
            addCriterion("SITE_ONE not between", value1, value2, "siteOne");
            return (Criteria) this;
        }

        public Criteria andSiteOneTimeIsNull() {
            addCriterion("SITE_ONE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSiteOneTimeIsNotNull() {
            addCriterion("SITE_ONE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSiteOneTimeEqualTo(String value) {
            addCriterion("SITE_ONE_TIME =", value, "siteOneTime");
            return (Criteria) this;
        }

        public Criteria andSiteOneTimeNotEqualTo(String value) {
            addCriterion("SITE_ONE_TIME <>", value, "siteOneTime");
            return (Criteria) this;
        }

        public Criteria andSiteOneTimeGreaterThan(String value) {
            addCriterion("SITE_ONE_TIME >", value, "siteOneTime");
            return (Criteria) this;
        }

        public Criteria andSiteOneTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_ONE_TIME >=", value, "siteOneTime");
            return (Criteria) this;
        }

        public Criteria andSiteOneTimeLessThan(String value) {
            addCriterion("SITE_ONE_TIME <", value, "siteOneTime");
            return (Criteria) this;
        }

        public Criteria andSiteOneTimeLessThanOrEqualTo(String value) {
            addCriterion("SITE_ONE_TIME <=", value, "siteOneTime");
            return (Criteria) this;
        }

        public Criteria andSiteOneTimeLike(String value) {
            addCriterion("SITE_ONE_TIME like", value, "siteOneTime");
            return (Criteria) this;
        }

        public Criteria andSiteOneTimeNotLike(String value) {
            addCriterion("SITE_ONE_TIME not like", value, "siteOneTime");
            return (Criteria) this;
        }

        public Criteria andSiteOneTimeIn(List<String> values) {
            addCriterion("SITE_ONE_TIME in", values, "siteOneTime");
            return (Criteria) this;
        }

        public Criteria andSiteOneTimeNotIn(List<String> values) {
            addCriterion("SITE_ONE_TIME not in", values, "siteOneTime");
            return (Criteria) this;
        }

        public Criteria andSiteOneTimeBetween(String value1, String value2) {
            addCriterion("SITE_ONE_TIME between", value1, value2, "siteOneTime");
            return (Criteria) this;
        }

        public Criteria andSiteOneTimeNotBetween(String value1, String value2) {
            addCriterion("SITE_ONE_TIME not between", value1, value2, "siteOneTime");
            return (Criteria) this;
        }

        public Criteria andSiteOneScoreIsNull() {
            addCriterion("SITE_ONE_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andSiteOneScoreIsNotNull() {
            addCriterion("SITE_ONE_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andSiteOneScoreEqualTo(String value) {
            addCriterion("SITE_ONE_SCORE =", value, "siteOneScore");
            return (Criteria) this;
        }

        public Criteria andSiteOneScoreNotEqualTo(String value) {
            addCriterion("SITE_ONE_SCORE <>", value, "siteOneScore");
            return (Criteria) this;
        }

        public Criteria andSiteOneScoreGreaterThan(String value) {
            addCriterion("SITE_ONE_SCORE >", value, "siteOneScore");
            return (Criteria) this;
        }

        public Criteria andSiteOneScoreGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_ONE_SCORE >=", value, "siteOneScore");
            return (Criteria) this;
        }

        public Criteria andSiteOneScoreLessThan(String value) {
            addCriterion("SITE_ONE_SCORE <", value, "siteOneScore");
            return (Criteria) this;
        }

        public Criteria andSiteOneScoreLessThanOrEqualTo(String value) {
            addCriterion("SITE_ONE_SCORE <=", value, "siteOneScore");
            return (Criteria) this;
        }

        public Criteria andSiteOneScoreLike(String value) {
            addCriterion("SITE_ONE_SCORE like", value, "siteOneScore");
            return (Criteria) this;
        }

        public Criteria andSiteOneScoreNotLike(String value) {
            addCriterion("SITE_ONE_SCORE not like", value, "siteOneScore");
            return (Criteria) this;
        }

        public Criteria andSiteOneScoreIn(List<String> values) {
            addCriterion("SITE_ONE_SCORE in", values, "siteOneScore");
            return (Criteria) this;
        }

        public Criteria andSiteOneScoreNotIn(List<String> values) {
            addCriterion("SITE_ONE_SCORE not in", values, "siteOneScore");
            return (Criteria) this;
        }

        public Criteria andSiteOneScoreBetween(String value1, String value2) {
            addCriterion("SITE_ONE_SCORE between", value1, value2, "siteOneScore");
            return (Criteria) this;
        }

        public Criteria andSiteOneScoreNotBetween(String value1, String value2) {
            addCriterion("SITE_ONE_SCORE not between", value1, value2, "siteOneScore");
            return (Criteria) this;
        }

        public Criteria andSiteOneStateIsNull() {
            addCriterion("SITE_ONE_STATE is null");
            return (Criteria) this;
        }

        public Criteria andSiteOneStateIsNotNull() {
            addCriterion("SITE_ONE_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andSiteOneStateEqualTo(String value) {
            addCriterion("SITE_ONE_STATE =", value, "siteOneState");
            return (Criteria) this;
        }

        public Criteria andSiteOneStateNotEqualTo(String value) {
            addCriterion("SITE_ONE_STATE <>", value, "siteOneState");
            return (Criteria) this;
        }

        public Criteria andSiteOneStateGreaterThan(String value) {
            addCriterion("SITE_ONE_STATE >", value, "siteOneState");
            return (Criteria) this;
        }

        public Criteria andSiteOneStateGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_ONE_STATE >=", value, "siteOneState");
            return (Criteria) this;
        }

        public Criteria andSiteOneStateLessThan(String value) {
            addCriterion("SITE_ONE_STATE <", value, "siteOneState");
            return (Criteria) this;
        }

        public Criteria andSiteOneStateLessThanOrEqualTo(String value) {
            addCriterion("SITE_ONE_STATE <=", value, "siteOneState");
            return (Criteria) this;
        }

        public Criteria andSiteOneStateLike(String value) {
            addCriterion("SITE_ONE_STATE like", value, "siteOneState");
            return (Criteria) this;
        }

        public Criteria andSiteOneStateNotLike(String value) {
            addCriterion("SITE_ONE_STATE not like", value, "siteOneState");
            return (Criteria) this;
        }

        public Criteria andSiteOneStateIn(List<String> values) {
            addCriterion("SITE_ONE_STATE in", values, "siteOneState");
            return (Criteria) this;
        }

        public Criteria andSiteOneStateNotIn(List<String> values) {
            addCriterion("SITE_ONE_STATE not in", values, "siteOneState");
            return (Criteria) this;
        }

        public Criteria andSiteOneStateBetween(String value1, String value2) {
            addCriterion("SITE_ONE_STATE between", value1, value2, "siteOneState");
            return (Criteria) this;
        }

        public Criteria andSiteOneStateNotBetween(String value1, String value2) {
            addCriterion("SITE_ONE_STATE not between", value1, value2, "siteOneState");
            return (Criteria) this;
        }

        public Criteria andSiteTwoIsNull() {
            addCriterion("SITE_TWO is null");
            return (Criteria) this;
        }

        public Criteria andSiteTwoIsNotNull() {
            addCriterion("SITE_TWO is not null");
            return (Criteria) this;
        }

        public Criteria andSiteTwoEqualTo(String value) {
            addCriterion("SITE_TWO =", value, "siteTwo");
            return (Criteria) this;
        }

        public Criteria andSiteTwoNotEqualTo(String value) {
            addCriterion("SITE_TWO <>", value, "siteTwo");
            return (Criteria) this;
        }

        public Criteria andSiteTwoGreaterThan(String value) {
            addCriterion("SITE_TWO >", value, "siteTwo");
            return (Criteria) this;
        }

        public Criteria andSiteTwoGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_TWO >=", value, "siteTwo");
            return (Criteria) this;
        }

        public Criteria andSiteTwoLessThan(String value) {
            addCriterion("SITE_TWO <", value, "siteTwo");
            return (Criteria) this;
        }

        public Criteria andSiteTwoLessThanOrEqualTo(String value) {
            addCriterion("SITE_TWO <=", value, "siteTwo");
            return (Criteria) this;
        }

        public Criteria andSiteTwoLike(String value) {
            addCriterion("SITE_TWO like", value, "siteTwo");
            return (Criteria) this;
        }

        public Criteria andSiteTwoNotLike(String value) {
            addCriterion("SITE_TWO not like", value, "siteTwo");
            return (Criteria) this;
        }

        public Criteria andSiteTwoIn(List<String> values) {
            addCriterion("SITE_TWO in", values, "siteTwo");
            return (Criteria) this;
        }

        public Criteria andSiteTwoNotIn(List<String> values) {
            addCriterion("SITE_TWO not in", values, "siteTwo");
            return (Criteria) this;
        }

        public Criteria andSiteTwoBetween(String value1, String value2) {
            addCriterion("SITE_TWO between", value1, value2, "siteTwo");
            return (Criteria) this;
        }

        public Criteria andSiteTwoNotBetween(String value1, String value2) {
            addCriterion("SITE_TWO not between", value1, value2, "siteTwo");
            return (Criteria) this;
        }

        public Criteria andSiteTwoTimeIsNull() {
            addCriterion("SITE_TWO_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSiteTwoTimeIsNotNull() {
            addCriterion("SITE_TWO_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSiteTwoTimeEqualTo(String value) {
            addCriterion("SITE_TWO_TIME =", value, "siteTwoTime");
            return (Criteria) this;
        }

        public Criteria andSiteTwoTimeNotEqualTo(String value) {
            addCriterion("SITE_TWO_TIME <>", value, "siteTwoTime");
            return (Criteria) this;
        }

        public Criteria andSiteTwoTimeGreaterThan(String value) {
            addCriterion("SITE_TWO_TIME >", value, "siteTwoTime");
            return (Criteria) this;
        }

        public Criteria andSiteTwoTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_TWO_TIME >=", value, "siteTwoTime");
            return (Criteria) this;
        }

        public Criteria andSiteTwoTimeLessThan(String value) {
            addCriterion("SITE_TWO_TIME <", value, "siteTwoTime");
            return (Criteria) this;
        }

        public Criteria andSiteTwoTimeLessThanOrEqualTo(String value) {
            addCriterion("SITE_TWO_TIME <=", value, "siteTwoTime");
            return (Criteria) this;
        }

        public Criteria andSiteTwoTimeLike(String value) {
            addCriterion("SITE_TWO_TIME like", value, "siteTwoTime");
            return (Criteria) this;
        }

        public Criteria andSiteTwoTimeNotLike(String value) {
            addCriterion("SITE_TWO_TIME not like", value, "siteTwoTime");
            return (Criteria) this;
        }

        public Criteria andSiteTwoTimeIn(List<String> values) {
            addCriterion("SITE_TWO_TIME in", values, "siteTwoTime");
            return (Criteria) this;
        }

        public Criteria andSiteTwoTimeNotIn(List<String> values) {
            addCriterion("SITE_TWO_TIME not in", values, "siteTwoTime");
            return (Criteria) this;
        }

        public Criteria andSiteTwoTimeBetween(String value1, String value2) {
            addCriterion("SITE_TWO_TIME between", value1, value2, "siteTwoTime");
            return (Criteria) this;
        }

        public Criteria andSiteTwoTimeNotBetween(String value1, String value2) {
            addCriterion("SITE_TWO_TIME not between", value1, value2, "siteTwoTime");
            return (Criteria) this;
        }

        public Criteria andSiteTwoScoreIsNull() {
            addCriterion("SITE_TWO_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andSiteTwoScoreIsNotNull() {
            addCriterion("SITE_TWO_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andSiteTwoScoreEqualTo(String value) {
            addCriterion("SITE_TWO_SCORE =", value, "siteTwoScore");
            return (Criteria) this;
        }

        public Criteria andSiteTwoScoreNotEqualTo(String value) {
            addCriterion("SITE_TWO_SCORE <>", value, "siteTwoScore");
            return (Criteria) this;
        }

        public Criteria andSiteTwoScoreGreaterThan(String value) {
            addCriterion("SITE_TWO_SCORE >", value, "siteTwoScore");
            return (Criteria) this;
        }

        public Criteria andSiteTwoScoreGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_TWO_SCORE >=", value, "siteTwoScore");
            return (Criteria) this;
        }

        public Criteria andSiteTwoScoreLessThan(String value) {
            addCriterion("SITE_TWO_SCORE <", value, "siteTwoScore");
            return (Criteria) this;
        }

        public Criteria andSiteTwoScoreLessThanOrEqualTo(String value) {
            addCriterion("SITE_TWO_SCORE <=", value, "siteTwoScore");
            return (Criteria) this;
        }

        public Criteria andSiteTwoScoreLike(String value) {
            addCriterion("SITE_TWO_SCORE like", value, "siteTwoScore");
            return (Criteria) this;
        }

        public Criteria andSiteTwoScoreNotLike(String value) {
            addCriterion("SITE_TWO_SCORE not like", value, "siteTwoScore");
            return (Criteria) this;
        }

        public Criteria andSiteTwoScoreIn(List<String> values) {
            addCriterion("SITE_TWO_SCORE in", values, "siteTwoScore");
            return (Criteria) this;
        }

        public Criteria andSiteTwoScoreNotIn(List<String> values) {
            addCriterion("SITE_TWO_SCORE not in", values, "siteTwoScore");
            return (Criteria) this;
        }

        public Criteria andSiteTwoScoreBetween(String value1, String value2) {
            addCriterion("SITE_TWO_SCORE between", value1, value2, "siteTwoScore");
            return (Criteria) this;
        }

        public Criteria andSiteTwoScoreNotBetween(String value1, String value2) {
            addCriterion("SITE_TWO_SCORE not between", value1, value2, "siteTwoScore");
            return (Criteria) this;
        }

        public Criteria andSiteTwoStateIsNull() {
            addCriterion("SITE_TWO_STATE is null");
            return (Criteria) this;
        }

        public Criteria andSiteTwoStateIsNotNull() {
            addCriterion("SITE_TWO_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andSiteTwoStateEqualTo(String value) {
            addCriterion("SITE_TWO_STATE =", value, "siteTwoState");
            return (Criteria) this;
        }

        public Criteria andSiteTwoStateNotEqualTo(String value) {
            addCriterion("SITE_TWO_STATE <>", value, "siteTwoState");
            return (Criteria) this;
        }

        public Criteria andSiteTwoStateGreaterThan(String value) {
            addCriterion("SITE_TWO_STATE >", value, "siteTwoState");
            return (Criteria) this;
        }

        public Criteria andSiteTwoStateGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_TWO_STATE >=", value, "siteTwoState");
            return (Criteria) this;
        }

        public Criteria andSiteTwoStateLessThan(String value) {
            addCriterion("SITE_TWO_STATE <", value, "siteTwoState");
            return (Criteria) this;
        }

        public Criteria andSiteTwoStateLessThanOrEqualTo(String value) {
            addCriterion("SITE_TWO_STATE <=", value, "siteTwoState");
            return (Criteria) this;
        }

        public Criteria andSiteTwoStateLike(String value) {
            addCriterion("SITE_TWO_STATE like", value, "siteTwoState");
            return (Criteria) this;
        }

        public Criteria andSiteTwoStateNotLike(String value) {
            addCriterion("SITE_TWO_STATE not like", value, "siteTwoState");
            return (Criteria) this;
        }

        public Criteria andSiteTwoStateIn(List<String> values) {
            addCriterion("SITE_TWO_STATE in", values, "siteTwoState");
            return (Criteria) this;
        }

        public Criteria andSiteTwoStateNotIn(List<String> values) {
            addCriterion("SITE_TWO_STATE not in", values, "siteTwoState");
            return (Criteria) this;
        }

        public Criteria andSiteTwoStateBetween(String value1, String value2) {
            addCriterion("SITE_TWO_STATE between", value1, value2, "siteTwoState");
            return (Criteria) this;
        }

        public Criteria andSiteTwoStateNotBetween(String value1, String value2) {
            addCriterion("SITE_TWO_STATE not between", value1, value2, "siteTwoState");
            return (Criteria) this;
        }

        public Criteria andSiteThreeIsNull() {
            addCriterion("SITE_THREE is null");
            return (Criteria) this;
        }

        public Criteria andSiteThreeIsNotNull() {
            addCriterion("SITE_THREE is not null");
            return (Criteria) this;
        }

        public Criteria andSiteThreeEqualTo(String value) {
            addCriterion("SITE_THREE =", value, "siteThree");
            return (Criteria) this;
        }

        public Criteria andSiteThreeNotEqualTo(String value) {
            addCriterion("SITE_THREE <>", value, "siteThree");
            return (Criteria) this;
        }

        public Criteria andSiteThreeGreaterThan(String value) {
            addCriterion("SITE_THREE >", value, "siteThree");
            return (Criteria) this;
        }

        public Criteria andSiteThreeGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_THREE >=", value, "siteThree");
            return (Criteria) this;
        }

        public Criteria andSiteThreeLessThan(String value) {
            addCriterion("SITE_THREE <", value, "siteThree");
            return (Criteria) this;
        }

        public Criteria andSiteThreeLessThanOrEqualTo(String value) {
            addCriterion("SITE_THREE <=", value, "siteThree");
            return (Criteria) this;
        }

        public Criteria andSiteThreeLike(String value) {
            addCriterion("SITE_THREE like", value, "siteThree");
            return (Criteria) this;
        }

        public Criteria andSiteThreeNotLike(String value) {
            addCriterion("SITE_THREE not like", value, "siteThree");
            return (Criteria) this;
        }

        public Criteria andSiteThreeIn(List<String> values) {
            addCriterion("SITE_THREE in", values, "siteThree");
            return (Criteria) this;
        }

        public Criteria andSiteThreeNotIn(List<String> values) {
            addCriterion("SITE_THREE not in", values, "siteThree");
            return (Criteria) this;
        }

        public Criteria andSiteThreeBetween(String value1, String value2) {
            addCriterion("SITE_THREE between", value1, value2, "siteThree");
            return (Criteria) this;
        }

        public Criteria andSiteThreeNotBetween(String value1, String value2) {
            addCriterion("SITE_THREE not between", value1, value2, "siteThree");
            return (Criteria) this;
        }

        public Criteria andSiteThreeTimeIsNull() {
            addCriterion("SITE_THREE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSiteThreeTimeIsNotNull() {
            addCriterion("SITE_THREE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSiteThreeTimeEqualTo(String value) {
            addCriterion("SITE_THREE_TIME =", value, "siteThreeTime");
            return (Criteria) this;
        }

        public Criteria andSiteThreeTimeNotEqualTo(String value) {
            addCriterion("SITE_THREE_TIME <>", value, "siteThreeTime");
            return (Criteria) this;
        }

        public Criteria andSiteThreeTimeGreaterThan(String value) {
            addCriterion("SITE_THREE_TIME >", value, "siteThreeTime");
            return (Criteria) this;
        }

        public Criteria andSiteThreeTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_THREE_TIME >=", value, "siteThreeTime");
            return (Criteria) this;
        }

        public Criteria andSiteThreeTimeLessThan(String value) {
            addCriterion("SITE_THREE_TIME <", value, "siteThreeTime");
            return (Criteria) this;
        }

        public Criteria andSiteThreeTimeLessThanOrEqualTo(String value) {
            addCriterion("SITE_THREE_TIME <=", value, "siteThreeTime");
            return (Criteria) this;
        }

        public Criteria andSiteThreeTimeLike(String value) {
            addCriterion("SITE_THREE_TIME like", value, "siteThreeTime");
            return (Criteria) this;
        }

        public Criteria andSiteThreeTimeNotLike(String value) {
            addCriterion("SITE_THREE_TIME not like", value, "siteThreeTime");
            return (Criteria) this;
        }

        public Criteria andSiteThreeTimeIn(List<String> values) {
            addCriterion("SITE_THREE_TIME in", values, "siteThreeTime");
            return (Criteria) this;
        }

        public Criteria andSiteThreeTimeNotIn(List<String> values) {
            addCriterion("SITE_THREE_TIME not in", values, "siteThreeTime");
            return (Criteria) this;
        }

        public Criteria andSiteThreeTimeBetween(String value1, String value2) {
            addCriterion("SITE_THREE_TIME between", value1, value2, "siteThreeTime");
            return (Criteria) this;
        }

        public Criteria andSiteThreeTimeNotBetween(String value1, String value2) {
            addCriterion("SITE_THREE_TIME not between", value1, value2, "siteThreeTime");
            return (Criteria) this;
        }

        public Criteria andSiteThreeScoreIsNull() {
            addCriterion("SITE_THREE_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andSiteThreeScoreIsNotNull() {
            addCriterion("SITE_THREE_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andSiteThreeScoreEqualTo(String value) {
            addCriterion("SITE_THREE_SCORE =", value, "siteThreeScore");
            return (Criteria) this;
        }

        public Criteria andSiteThreeScoreNotEqualTo(String value) {
            addCriterion("SITE_THREE_SCORE <>", value, "siteThreeScore");
            return (Criteria) this;
        }

        public Criteria andSiteThreeScoreGreaterThan(String value) {
            addCriterion("SITE_THREE_SCORE >", value, "siteThreeScore");
            return (Criteria) this;
        }

        public Criteria andSiteThreeScoreGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_THREE_SCORE >=", value, "siteThreeScore");
            return (Criteria) this;
        }

        public Criteria andSiteThreeScoreLessThan(String value) {
            addCriterion("SITE_THREE_SCORE <", value, "siteThreeScore");
            return (Criteria) this;
        }

        public Criteria andSiteThreeScoreLessThanOrEqualTo(String value) {
            addCriterion("SITE_THREE_SCORE <=", value, "siteThreeScore");
            return (Criteria) this;
        }

        public Criteria andSiteThreeScoreLike(String value) {
            addCriterion("SITE_THREE_SCORE like", value, "siteThreeScore");
            return (Criteria) this;
        }

        public Criteria andSiteThreeScoreNotLike(String value) {
            addCriterion("SITE_THREE_SCORE not like", value, "siteThreeScore");
            return (Criteria) this;
        }

        public Criteria andSiteThreeScoreIn(List<String> values) {
            addCriterion("SITE_THREE_SCORE in", values, "siteThreeScore");
            return (Criteria) this;
        }

        public Criteria andSiteThreeScoreNotIn(List<String> values) {
            addCriterion("SITE_THREE_SCORE not in", values, "siteThreeScore");
            return (Criteria) this;
        }

        public Criteria andSiteThreeScoreBetween(String value1, String value2) {
            addCriterion("SITE_THREE_SCORE between", value1, value2, "siteThreeScore");
            return (Criteria) this;
        }

        public Criteria andSiteThreeScoreNotBetween(String value1, String value2) {
            addCriterion("SITE_THREE_SCORE not between", value1, value2, "siteThreeScore");
            return (Criteria) this;
        }

        public Criteria andSiteThreeStateIsNull() {
            addCriterion("SITE_THREE_STATE is null");
            return (Criteria) this;
        }

        public Criteria andSiteThreeStateIsNotNull() {
            addCriterion("SITE_THREE_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andSiteThreeStateEqualTo(String value) {
            addCriterion("SITE_THREE_STATE =", value, "siteThreeState");
            return (Criteria) this;
        }

        public Criteria andSiteThreeStateNotEqualTo(String value) {
            addCriterion("SITE_THREE_STATE <>", value, "siteThreeState");
            return (Criteria) this;
        }

        public Criteria andSiteThreeStateGreaterThan(String value) {
            addCriterion("SITE_THREE_STATE >", value, "siteThreeState");
            return (Criteria) this;
        }

        public Criteria andSiteThreeStateGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_THREE_STATE >=", value, "siteThreeState");
            return (Criteria) this;
        }

        public Criteria andSiteThreeStateLessThan(String value) {
            addCriterion("SITE_THREE_STATE <", value, "siteThreeState");
            return (Criteria) this;
        }

        public Criteria andSiteThreeStateLessThanOrEqualTo(String value) {
            addCriterion("SITE_THREE_STATE <=", value, "siteThreeState");
            return (Criteria) this;
        }

        public Criteria andSiteThreeStateLike(String value) {
            addCriterion("SITE_THREE_STATE like", value, "siteThreeState");
            return (Criteria) this;
        }

        public Criteria andSiteThreeStateNotLike(String value) {
            addCriterion("SITE_THREE_STATE not like", value, "siteThreeState");
            return (Criteria) this;
        }

        public Criteria andSiteThreeStateIn(List<String> values) {
            addCriterion("SITE_THREE_STATE in", values, "siteThreeState");
            return (Criteria) this;
        }

        public Criteria andSiteThreeStateNotIn(List<String> values) {
            addCriterion("SITE_THREE_STATE not in", values, "siteThreeState");
            return (Criteria) this;
        }

        public Criteria andSiteThreeStateBetween(String value1, String value2) {
            addCriterion("SITE_THREE_STATE between", value1, value2, "siteThreeState");
            return (Criteria) this;
        }

        public Criteria andSiteThreeStateNotBetween(String value1, String value2) {
            addCriterion("SITE_THREE_STATE not between", value1, value2, "siteThreeState");
            return (Criteria) this;
        }

        public Criteria andTotalScoreStateIsNull() {
            addCriterion("TOTAL_SCORE_STATE is null");
            return (Criteria) this;
        }

        public Criteria andTotalScoreStateIsNotNull() {
            addCriterion("TOTAL_SCORE_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andTotalScoreStateEqualTo(String value) {
            addCriterion("TOTAL_SCORE_STATE =", value, "totalScoreState");
            return (Criteria) this;
        }

        public Criteria andTotalScoreStateNotEqualTo(String value) {
            addCriterion("TOTAL_SCORE_STATE <>", value, "totalScoreState");
            return (Criteria) this;
        }

        public Criteria andTotalScoreStateGreaterThan(String value) {
            addCriterion("TOTAL_SCORE_STATE >", value, "totalScoreState");
            return (Criteria) this;
        }

        public Criteria andTotalScoreStateGreaterThanOrEqualTo(String value) {
            addCriterion("TOTAL_SCORE_STATE >=", value, "totalScoreState");
            return (Criteria) this;
        }

        public Criteria andTotalScoreStateLessThan(String value) {
            addCriterion("TOTAL_SCORE_STATE <", value, "totalScoreState");
            return (Criteria) this;
        }

        public Criteria andTotalScoreStateLessThanOrEqualTo(String value) {
            addCriterion("TOTAL_SCORE_STATE <=", value, "totalScoreState");
            return (Criteria) this;
        }

        public Criteria andTotalScoreStateLike(String value) {
            addCriterion("TOTAL_SCORE_STATE like", value, "totalScoreState");
            return (Criteria) this;
        }

        public Criteria andTotalScoreStateNotLike(String value) {
            addCriterion("TOTAL_SCORE_STATE not like", value, "totalScoreState");
            return (Criteria) this;
        }

        public Criteria andTotalScoreStateIn(List<String> values) {
            addCriterion("TOTAL_SCORE_STATE in", values, "totalScoreState");
            return (Criteria) this;
        }

        public Criteria andTotalScoreStateNotIn(List<String> values) {
            addCriterion("TOTAL_SCORE_STATE not in", values, "totalScoreState");
            return (Criteria) this;
        }

        public Criteria andTotalScoreStateBetween(String value1, String value2) {
            addCriterion("TOTAL_SCORE_STATE between", value1, value2, "totalScoreState");
            return (Criteria) this;
        }

        public Criteria andTotalScoreStateNotBetween(String value1, String value2) {
            addCriterion("TOTAL_SCORE_STATE not between", value1, value2, "totalScoreState");
            return (Criteria) this;
        }

        public Criteria andCompleteNoIsNull() {
            addCriterion("COMPLETE_NO is null");
            return (Criteria) this;
        }

        public Criteria andCompleteNoIsNotNull() {
            addCriterion("COMPLETE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteNoEqualTo(String value) {
            addCriterion("COMPLETE_NO =", value, "completeNo");
            return (Criteria) this;
        }

        public Criteria andCompleteNoNotEqualTo(String value) {
            addCriterion("COMPLETE_NO <>", value, "completeNo");
            return (Criteria) this;
        }

        public Criteria andCompleteNoGreaterThan(String value) {
            addCriterion("COMPLETE_NO >", value, "completeNo");
            return (Criteria) this;
        }

        public Criteria andCompleteNoGreaterThanOrEqualTo(String value) {
            addCriterion("COMPLETE_NO >=", value, "completeNo");
            return (Criteria) this;
        }

        public Criteria andCompleteNoLessThan(String value) {
            addCriterion("COMPLETE_NO <", value, "completeNo");
            return (Criteria) this;
        }

        public Criteria andCompleteNoLessThanOrEqualTo(String value) {
            addCriterion("COMPLETE_NO <=", value, "completeNo");
            return (Criteria) this;
        }

        public Criteria andCompleteNoLike(String value) {
            addCriterion("COMPLETE_NO like", value, "completeNo");
            return (Criteria) this;
        }

        public Criteria andCompleteNoNotLike(String value) {
            addCriterion("COMPLETE_NO not like", value, "completeNo");
            return (Criteria) this;
        }

        public Criteria andCompleteNoIn(List<String> values) {
            addCriterion("COMPLETE_NO in", values, "completeNo");
            return (Criteria) this;
        }

        public Criteria andCompleteNoNotIn(List<String> values) {
            addCriterion("COMPLETE_NO not in", values, "completeNo");
            return (Criteria) this;
        }

        public Criteria andCompleteNoBetween(String value1, String value2) {
            addCriterion("COMPLETE_NO between", value1, value2, "completeNo");
            return (Criteria) this;
        }

        public Criteria andCompleteNoNotBetween(String value1, String value2) {
            addCriterion("COMPLETE_NO not between", value1, value2, "completeNo");
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

        public Criteria andOrgCode2IsNull() {
            addCriterion("ORG_CODE2 is null");
            return (Criteria) this;
        }

        public Criteria andOrgCode2IsNotNull() {
            addCriterion("ORG_CODE2 is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCode2EqualTo(String value) {
            addCriterion("ORG_CODE2 =", value, "orgCode2");
            return (Criteria) this;
        }

        public Criteria andOrgCode2NotEqualTo(String value) {
            addCriterion("ORG_CODE2 <>", value, "orgCode2");
            return (Criteria) this;
        }

        public Criteria andOrgCode2GreaterThan(String value) {
            addCriterion("ORG_CODE2 >", value, "orgCode2");
            return (Criteria) this;
        }

        public Criteria andOrgCode2GreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CODE2 >=", value, "orgCode2");
            return (Criteria) this;
        }

        public Criteria andOrgCode2LessThan(String value) {
            addCriterion("ORG_CODE2 <", value, "orgCode2");
            return (Criteria) this;
        }

        public Criteria andOrgCode2LessThanOrEqualTo(String value) {
            addCriterion("ORG_CODE2 <=", value, "orgCode2");
            return (Criteria) this;
        }

        public Criteria andOrgCode2Like(String value) {
            addCriterion("ORG_CODE2 like", value, "orgCode2");
            return (Criteria) this;
        }

        public Criteria andOrgCode2NotLike(String value) {
            addCriterion("ORG_CODE2 not like", value, "orgCode2");
            return (Criteria) this;
        }

        public Criteria andOrgCode2In(List<String> values) {
            addCriterion("ORG_CODE2 in", values, "orgCode2");
            return (Criteria) this;
        }

        public Criteria andOrgCode2NotIn(List<String> values) {
            addCriterion("ORG_CODE2 not in", values, "orgCode2");
            return (Criteria) this;
        }

        public Criteria andOrgCode2Between(String value1, String value2) {
            addCriterion("ORG_CODE2 between", value1, value2, "orgCode2");
            return (Criteria) this;
        }

        public Criteria andOrgCode2NotBetween(String value1, String value2) {
            addCriterion("ORG_CODE2 not between", value1, value2, "orgCode2");
            return (Criteria) this;
        }

        public Criteria andBegindateIsNull() {
            addCriterion("BEGINDATE is null");
            return (Criteria) this;
        }

        public Criteria andBegindateIsNotNull() {
            addCriterion("BEGINDATE is not null");
            return (Criteria) this;
        }

        public Criteria andBegindateEqualTo(String value) {
            addCriterion("BEGINDATE =", value, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateNotEqualTo(String value) {
            addCriterion("BEGINDATE <>", value, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateGreaterThan(String value) {
            addCriterion("BEGINDATE >", value, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateGreaterThanOrEqualTo(String value) {
            addCriterion("BEGINDATE >=", value, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateLessThan(String value) {
            addCriterion("BEGINDATE <", value, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateLessThanOrEqualTo(String value) {
            addCriterion("BEGINDATE <=", value, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateLike(String value) {
            addCriterion("BEGINDATE like", value, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateNotLike(String value) {
            addCriterion("BEGINDATE not like", value, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateIn(List<String> values) {
            addCriterion("BEGINDATE in", values, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateNotIn(List<String> values) {
            addCriterion("BEGINDATE not in", values, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateBetween(String value1, String value2) {
            addCriterion("BEGINDATE between", value1, value2, "begindate");
            return (Criteria) this;
        }

        public Criteria andBegindateNotBetween(String value1, String value2) {
            addCriterion("BEGINDATE not between", value1, value2, "begindate");
            return (Criteria) this;
        }

        public Criteria andEnddateIsNull() {
            addCriterion("ENDDATE is null");
            return (Criteria) this;
        }

        public Criteria andEnddateIsNotNull() {
            addCriterion("ENDDATE is not null");
            return (Criteria) this;
        }

        public Criteria andEnddateEqualTo(String value) {
            addCriterion("ENDDATE =", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateNotEqualTo(String value) {
            addCriterion("ENDDATE <>", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateGreaterThan(String value) {
            addCriterion("ENDDATE >", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateGreaterThanOrEqualTo(String value) {
            addCriterion("ENDDATE >=", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateLessThan(String value) {
            addCriterion("ENDDATE <", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateLessThanOrEqualTo(String value) {
            addCriterion("ENDDATE <=", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateLike(String value) {
            addCriterion("ENDDATE like", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateNotLike(String value) {
            addCriterion("ENDDATE not like", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateIn(List<String> values) {
            addCriterion("ENDDATE in", values, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateNotIn(List<String> values) {
            addCriterion("ENDDATE not in", values, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateBetween(String value1, String value2) {
            addCriterion("ENDDATE between", value1, value2, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateNotBetween(String value1, String value2) {
            addCriterion("ENDDATE not between", value1, value2, "enddate");
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

        public Criteria andSessionNumberEqualTo(BigDecimal value) {
            addCriterion("SESSION_NUMBER =", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotEqualTo(BigDecimal value) {
            addCriterion("SESSION_NUMBER <>", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberGreaterThan(BigDecimal value) {
            addCriterion("SESSION_NUMBER >", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SESSION_NUMBER >=", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLessThan(BigDecimal value) {
            addCriterion("SESSION_NUMBER <", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SESSION_NUMBER <=", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIn(List<BigDecimal> values) {
            addCriterion("SESSION_NUMBER in", values, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotIn(List<BigDecimal> values) {
            addCriterion("SESSION_NUMBER not in", values, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SESSION_NUMBER between", value1, value2, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SESSION_NUMBER not between", value1, value2, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("ADDRESS =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("ADDRESS <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("ADDRESS >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ADDRESS >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("ADDRESS <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("ADDRESS <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("ADDRESS like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("ADDRESS not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("ADDRESS in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("ADDRESS not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("ADDRESS between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("ADDRESS not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andExamtimeIsNull() {
            addCriterion("EXAMTIME is null");
            return (Criteria) this;
        }

        public Criteria andExamtimeIsNotNull() {
            addCriterion("EXAMTIME is not null");
            return (Criteria) this;
        }

        public Criteria andExamtimeEqualTo(String value) {
            addCriterion("EXAMTIME =", value, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeNotEqualTo(String value) {
            addCriterion("EXAMTIME <>", value, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeGreaterThan(String value) {
            addCriterion("EXAMTIME >", value, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeGreaterThanOrEqualTo(String value) {
            addCriterion("EXAMTIME >=", value, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeLessThan(String value) {
            addCriterion("EXAMTIME <", value, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeLessThanOrEqualTo(String value) {
            addCriterion("EXAMTIME <=", value, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeLike(String value) {
            addCriterion("EXAMTIME like", value, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeNotLike(String value) {
            addCriterion("EXAMTIME not like", value, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeIn(List<String> values) {
            addCriterion("EXAMTIME in", values, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeNotIn(List<String> values) {
            addCriterion("EXAMTIME not in", values, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeBetween(String value1, String value2) {
            addCriterion("EXAMTIME between", value1, value2, "examtime");
            return (Criteria) this;
        }

        public Criteria andExamtimeNotBetween(String value1, String value2) {
            addCriterion("EXAMTIME not between", value1, value2, "examtime");
            return (Criteria) this;
        }

        public Criteria andSitephoneIsNull() {
            addCriterion("SITEPHONE is null");
            return (Criteria) this;
        }

        public Criteria andSitephoneIsNotNull() {
            addCriterion("SITEPHONE is not null");
            return (Criteria) this;
        }

        public Criteria andSitephoneEqualTo(String value) {
            addCriterion("SITEPHONE =", value, "sitephone");
            return (Criteria) this;
        }

        public Criteria andSitephoneNotEqualTo(String value) {
            addCriterion("SITEPHONE <>", value, "sitephone");
            return (Criteria) this;
        }

        public Criteria andSitephoneGreaterThan(String value) {
            addCriterion("SITEPHONE >", value, "sitephone");
            return (Criteria) this;
        }

        public Criteria andSitephoneGreaterThanOrEqualTo(String value) {
            addCriterion("SITEPHONE >=", value, "sitephone");
            return (Criteria) this;
        }

        public Criteria andSitephoneLessThan(String value) {
            addCriterion("SITEPHONE <", value, "sitephone");
            return (Criteria) this;
        }

        public Criteria andSitephoneLessThanOrEqualTo(String value) {
            addCriterion("SITEPHONE <=", value, "sitephone");
            return (Criteria) this;
        }

        public Criteria andSitephoneLike(String value) {
            addCriterion("SITEPHONE like", value, "sitephone");
            return (Criteria) this;
        }

        public Criteria andSitephoneNotLike(String value) {
            addCriterion("SITEPHONE not like", value, "sitephone");
            return (Criteria) this;
        }

        public Criteria andSitephoneIn(List<String> values) {
            addCriterion("SITEPHONE in", values, "sitephone");
            return (Criteria) this;
        }

        public Criteria andSitephoneNotIn(List<String> values) {
            addCriterion("SITEPHONE not in", values, "sitephone");
            return (Criteria) this;
        }

        public Criteria andSitephoneBetween(String value1, String value2) {
            addCriterion("SITEPHONE between", value1, value2, "sitephone");
            return (Criteria) this;
        }

        public Criteria andSitephoneNotBetween(String value1, String value2) {
            addCriterion("SITEPHONE not between", value1, value2, "sitephone");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("TITLE is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("TITLE =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("TITLE <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("TITLE >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("TITLE <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("TITLE <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("TITLE like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("TITLE not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("TITLE in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("TITLE not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("TITLE between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("TITLE not between", value1, value2, "title");
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