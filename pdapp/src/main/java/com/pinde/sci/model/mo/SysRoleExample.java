package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class SysRoleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysRoleExample() {
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

        public Criteria andRoleFlowIsNull() {
            addCriterion("ROLE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRoleFlowIsNotNull() {
            addCriterion("ROLE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRoleFlowEqualTo(String value) {
            addCriterion("ROLE_FLOW =", value, "roleFlow");
            return (Criteria) this;
        }

        public Criteria andRoleFlowNotEqualTo(String value) {
            addCriterion("ROLE_FLOW <>", value, "roleFlow");
            return (Criteria) this;
        }

        public Criteria andRoleFlowGreaterThan(String value) {
            addCriterion("ROLE_FLOW >", value, "roleFlow");
            return (Criteria) this;
        }

        public Criteria andRoleFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ROLE_FLOW >=", value, "roleFlow");
            return (Criteria) this;
        }

        public Criteria andRoleFlowLessThan(String value) {
            addCriterion("ROLE_FLOW <", value, "roleFlow");
            return (Criteria) this;
        }

        public Criteria andRoleFlowLessThanOrEqualTo(String value) {
            addCriterion("ROLE_FLOW <=", value, "roleFlow");
            return (Criteria) this;
        }

        public Criteria andRoleFlowLike(String value) {
            addCriterion("ROLE_FLOW like", value, "roleFlow");
            return (Criteria) this;
        }

        public Criteria andRoleFlowNotLike(String value) {
            addCriterion("ROLE_FLOW not like", value, "roleFlow");
            return (Criteria) this;
        }

        public Criteria andRoleFlowIn(List<String> values) {
            addCriterion("ROLE_FLOW in", values, "roleFlow");
            return (Criteria) this;
        }

        public Criteria andRoleFlowNotIn(List<String> values) {
            addCriterion("ROLE_FLOW not in", values, "roleFlow");
            return (Criteria) this;
        }

        public Criteria andRoleFlowBetween(String value1, String value2) {
            addCriterion("ROLE_FLOW between", value1, value2, "roleFlow");
            return (Criteria) this;
        }

        public Criteria andRoleFlowNotBetween(String value1, String value2) {
            addCriterion("ROLE_FLOW not between", value1, value2, "roleFlow");
            return (Criteria) this;
        }

        public Criteria andRoleNameIsNull() {
            addCriterion("ROLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRoleNameIsNotNull() {
            addCriterion("ROLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRoleNameEqualTo(String value) {
            addCriterion("ROLE_NAME =", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotEqualTo(String value) {
            addCriterion("ROLE_NAME <>", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThan(String value) {
            addCriterion("ROLE_NAME >", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThanOrEqualTo(String value) {
            addCriterion("ROLE_NAME >=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThan(String value) {
            addCriterion("ROLE_NAME <", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThanOrEqualTo(String value) {
            addCriterion("ROLE_NAME <=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLike(String value) {
            addCriterion("ROLE_NAME like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotLike(String value) {
            addCriterion("ROLE_NAME not like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameIn(List<String> values) {
            addCriterion("ROLE_NAME in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotIn(List<String> values) {
            addCriterion("ROLE_NAME not in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameBetween(String value1, String value2) {
            addCriterion("ROLE_NAME between", value1, value2, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotBetween(String value1, String value2) {
            addCriterion("ROLE_NAME not between", value1, value2, "roleName");
            return (Criteria) this;
        }

        public Criteria andWsIdIsNull() {
            addCriterion("WS_ID is null");
            return (Criteria) this;
        }

        public Criteria andWsIdIsNotNull() {
            addCriterion("WS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andWsIdEqualTo(String value) {
            addCriterion("WS_ID =", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdNotEqualTo(String value) {
            addCriterion("WS_ID <>", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdGreaterThan(String value) {
            addCriterion("WS_ID >", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdGreaterThanOrEqualTo(String value) {
            addCriterion("WS_ID >=", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdLessThan(String value) {
            addCriterion("WS_ID <", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdLessThanOrEqualTo(String value) {
            addCriterion("WS_ID <=", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdLike(String value) {
            addCriterion("WS_ID like", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdNotLike(String value) {
            addCriterion("WS_ID not like", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdIn(List<String> values) {
            addCriterion("WS_ID in", values, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdNotIn(List<String> values) {
            addCriterion("WS_ID not in", values, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdBetween(String value1, String value2) {
            addCriterion("WS_ID between", value1, value2, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdNotBetween(String value1, String value2) {
            addCriterion("WS_ID not between", value1, value2, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsNameIsNull() {
            addCriterion("WS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andWsNameIsNotNull() {
            addCriterion("WS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andWsNameEqualTo(String value) {
            addCriterion("WS_NAME =", value, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameNotEqualTo(String value) {
            addCriterion("WS_NAME <>", value, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameGreaterThan(String value) {
            addCriterion("WS_NAME >", value, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameGreaterThanOrEqualTo(String value) {
            addCriterion("WS_NAME >=", value, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameLessThan(String value) {
            addCriterion("WS_NAME <", value, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameLessThanOrEqualTo(String value) {
            addCriterion("WS_NAME <=", value, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameLike(String value) {
            addCriterion("WS_NAME like", value, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameNotLike(String value) {
            addCriterion("WS_NAME not like", value, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameIn(List<String> values) {
            addCriterion("WS_NAME in", values, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameNotIn(List<String> values) {
            addCriterion("WS_NAME not in", values, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameBetween(String value1, String value2) {
            addCriterion("WS_NAME between", value1, value2, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameNotBetween(String value1, String value2) {
            addCriterion("WS_NAME not between", value1, value2, "wsName");
            return (Criteria) this;
        }

        public Criteria andRoleLevelIdIsNull() {
            addCriterion("ROLE_LEVEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andRoleLevelIdIsNotNull() {
            addCriterion("ROLE_LEVEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRoleLevelIdEqualTo(String value) {
            addCriterion("ROLE_LEVEL_ID =", value, "roleLevelId");
            return (Criteria) this;
        }

        public Criteria andRoleLevelIdNotEqualTo(String value) {
            addCriterion("ROLE_LEVEL_ID <>", value, "roleLevelId");
            return (Criteria) this;
        }

        public Criteria andRoleLevelIdGreaterThan(String value) {
            addCriterion("ROLE_LEVEL_ID >", value, "roleLevelId");
            return (Criteria) this;
        }

        public Criteria andRoleLevelIdGreaterThanOrEqualTo(String value) {
            addCriterion("ROLE_LEVEL_ID >=", value, "roleLevelId");
            return (Criteria) this;
        }

        public Criteria andRoleLevelIdLessThan(String value) {
            addCriterion("ROLE_LEVEL_ID <", value, "roleLevelId");
            return (Criteria) this;
        }

        public Criteria andRoleLevelIdLessThanOrEqualTo(String value) {
            addCriterion("ROLE_LEVEL_ID <=", value, "roleLevelId");
            return (Criteria) this;
        }

        public Criteria andRoleLevelIdLike(String value) {
            addCriterion("ROLE_LEVEL_ID like", value, "roleLevelId");
            return (Criteria) this;
        }

        public Criteria andRoleLevelIdNotLike(String value) {
            addCriterion("ROLE_LEVEL_ID not like", value, "roleLevelId");
            return (Criteria) this;
        }

        public Criteria andRoleLevelIdIn(List<String> values) {
            addCriterion("ROLE_LEVEL_ID in", values, "roleLevelId");
            return (Criteria) this;
        }

        public Criteria andRoleLevelIdNotIn(List<String> values) {
            addCriterion("ROLE_LEVEL_ID not in", values, "roleLevelId");
            return (Criteria) this;
        }

        public Criteria andRoleLevelIdBetween(String value1, String value2) {
            addCriterion("ROLE_LEVEL_ID between", value1, value2, "roleLevelId");
            return (Criteria) this;
        }

        public Criteria andRoleLevelIdNotBetween(String value1, String value2) {
            addCriterion("ROLE_LEVEL_ID not between", value1, value2, "roleLevelId");
            return (Criteria) this;
        }

        public Criteria andRoleLevelNameIsNull() {
            addCriterion("ROLE_LEVEL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRoleLevelNameIsNotNull() {
            addCriterion("ROLE_LEVEL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRoleLevelNameEqualTo(String value) {
            addCriterion("ROLE_LEVEL_NAME =", value, "roleLevelName");
            return (Criteria) this;
        }

        public Criteria andRoleLevelNameNotEqualTo(String value) {
            addCriterion("ROLE_LEVEL_NAME <>", value, "roleLevelName");
            return (Criteria) this;
        }

        public Criteria andRoleLevelNameGreaterThan(String value) {
            addCriterion("ROLE_LEVEL_NAME >", value, "roleLevelName");
            return (Criteria) this;
        }

        public Criteria andRoleLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("ROLE_LEVEL_NAME >=", value, "roleLevelName");
            return (Criteria) this;
        }

        public Criteria andRoleLevelNameLessThan(String value) {
            addCriterion("ROLE_LEVEL_NAME <", value, "roleLevelName");
            return (Criteria) this;
        }

        public Criteria andRoleLevelNameLessThanOrEqualTo(String value) {
            addCriterion("ROLE_LEVEL_NAME <=", value, "roleLevelName");
            return (Criteria) this;
        }

        public Criteria andRoleLevelNameLike(String value) {
            addCriterion("ROLE_LEVEL_NAME like", value, "roleLevelName");
            return (Criteria) this;
        }

        public Criteria andRoleLevelNameNotLike(String value) {
            addCriterion("ROLE_LEVEL_NAME not like", value, "roleLevelName");
            return (Criteria) this;
        }

        public Criteria andRoleLevelNameIn(List<String> values) {
            addCriterion("ROLE_LEVEL_NAME in", values, "roleLevelName");
            return (Criteria) this;
        }

        public Criteria andRoleLevelNameNotIn(List<String> values) {
            addCriterion("ROLE_LEVEL_NAME not in", values, "roleLevelName");
            return (Criteria) this;
        }

        public Criteria andRoleLevelNameBetween(String value1, String value2) {
            addCriterion("ROLE_LEVEL_NAME between", value1, value2, "roleLevelName");
            return (Criteria) this;
        }

        public Criteria andRoleLevelNameNotBetween(String value1, String value2) {
            addCriterion("ROLE_LEVEL_NAME not between", value1, value2, "roleLevelName");
            return (Criteria) this;
        }

        public Criteria andParentRoleFlowIsNull() {
            addCriterion("PARENT_ROLE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andParentRoleFlowIsNotNull() {
            addCriterion("PARENT_ROLE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andParentRoleFlowEqualTo(String value) {
            addCriterion("PARENT_ROLE_FLOW =", value, "parentRoleFlow");
            return (Criteria) this;
        }

        public Criteria andParentRoleFlowNotEqualTo(String value) {
            addCriterion("PARENT_ROLE_FLOW <>", value, "parentRoleFlow");
            return (Criteria) this;
        }

        public Criteria andParentRoleFlowGreaterThan(String value) {
            addCriterion("PARENT_ROLE_FLOW >", value, "parentRoleFlow");
            return (Criteria) this;
        }

        public Criteria andParentRoleFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_ROLE_FLOW >=", value, "parentRoleFlow");
            return (Criteria) this;
        }

        public Criteria andParentRoleFlowLessThan(String value) {
            addCriterion("PARENT_ROLE_FLOW <", value, "parentRoleFlow");
            return (Criteria) this;
        }

        public Criteria andParentRoleFlowLessThanOrEqualTo(String value) {
            addCriterion("PARENT_ROLE_FLOW <=", value, "parentRoleFlow");
            return (Criteria) this;
        }

        public Criteria andParentRoleFlowLike(String value) {
            addCriterion("PARENT_ROLE_FLOW like", value, "parentRoleFlow");
            return (Criteria) this;
        }

        public Criteria andParentRoleFlowNotLike(String value) {
            addCriterion("PARENT_ROLE_FLOW not like", value, "parentRoleFlow");
            return (Criteria) this;
        }

        public Criteria andParentRoleFlowIn(List<String> values) {
            addCriterion("PARENT_ROLE_FLOW in", values, "parentRoleFlow");
            return (Criteria) this;
        }

        public Criteria andParentRoleFlowNotIn(List<String> values) {
            addCriterion("PARENT_ROLE_FLOW not in", values, "parentRoleFlow");
            return (Criteria) this;
        }

        public Criteria andParentRoleFlowBetween(String value1, String value2) {
            addCriterion("PARENT_ROLE_FLOW between", value1, value2, "parentRoleFlow");
            return (Criteria) this;
        }

        public Criteria andParentRoleFlowNotBetween(String value1, String value2) {
            addCriterion("PARENT_ROLE_FLOW not between", value1, value2, "parentRoleFlow");
            return (Criteria) this;
        }

        public Criteria andParentRoleNameIsNull() {
            addCriterion("PARENT_ROLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andParentRoleNameIsNotNull() {
            addCriterion("PARENT_ROLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andParentRoleNameEqualTo(String value) {
            addCriterion("PARENT_ROLE_NAME =", value, "parentRoleName");
            return (Criteria) this;
        }

        public Criteria andParentRoleNameNotEqualTo(String value) {
            addCriterion("PARENT_ROLE_NAME <>", value, "parentRoleName");
            return (Criteria) this;
        }

        public Criteria andParentRoleNameGreaterThan(String value) {
            addCriterion("PARENT_ROLE_NAME >", value, "parentRoleName");
            return (Criteria) this;
        }

        public Criteria andParentRoleNameGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_ROLE_NAME >=", value, "parentRoleName");
            return (Criteria) this;
        }

        public Criteria andParentRoleNameLessThan(String value) {
            addCriterion("PARENT_ROLE_NAME <", value, "parentRoleName");
            return (Criteria) this;
        }

        public Criteria andParentRoleNameLessThanOrEqualTo(String value) {
            addCriterion("PARENT_ROLE_NAME <=", value, "parentRoleName");
            return (Criteria) this;
        }

        public Criteria andParentRoleNameLike(String value) {
            addCriterion("PARENT_ROLE_NAME like", value, "parentRoleName");
            return (Criteria) this;
        }

        public Criteria andParentRoleNameNotLike(String value) {
            addCriterion("PARENT_ROLE_NAME not like", value, "parentRoleName");
            return (Criteria) this;
        }

        public Criteria andParentRoleNameIn(List<String> values) {
            addCriterion("PARENT_ROLE_NAME in", values, "parentRoleName");
            return (Criteria) this;
        }

        public Criteria andParentRoleNameNotIn(List<String> values) {
            addCriterion("PARENT_ROLE_NAME not in", values, "parentRoleName");
            return (Criteria) this;
        }

        public Criteria andParentRoleNameBetween(String value1, String value2) {
            addCriterion("PARENT_ROLE_NAME between", value1, value2, "parentRoleName");
            return (Criteria) this;
        }

        public Criteria andParentRoleNameNotBetween(String value1, String value2) {
            addCriterion("PARENT_ROLE_NAME not between", value1, value2, "parentRoleName");
            return (Criteria) this;
        }

        public Criteria andAllowRegFlagIsNull() {
            addCriterion("ALLOW_REG_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andAllowRegFlagIsNotNull() {
            addCriterion("ALLOW_REG_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andAllowRegFlagEqualTo(String value) {
            addCriterion("ALLOW_REG_FLAG =", value, "allowRegFlag");
            return (Criteria) this;
        }

        public Criteria andAllowRegFlagNotEqualTo(String value) {
            addCriterion("ALLOW_REG_FLAG <>", value, "allowRegFlag");
            return (Criteria) this;
        }

        public Criteria andAllowRegFlagGreaterThan(String value) {
            addCriterion("ALLOW_REG_FLAG >", value, "allowRegFlag");
            return (Criteria) this;
        }

        public Criteria andAllowRegFlagGreaterThanOrEqualTo(String value) {
            addCriterion("ALLOW_REG_FLAG >=", value, "allowRegFlag");
            return (Criteria) this;
        }

        public Criteria andAllowRegFlagLessThan(String value) {
            addCriterion("ALLOW_REG_FLAG <", value, "allowRegFlag");
            return (Criteria) this;
        }

        public Criteria andAllowRegFlagLessThanOrEqualTo(String value) {
            addCriterion("ALLOW_REG_FLAG <=", value, "allowRegFlag");
            return (Criteria) this;
        }

        public Criteria andAllowRegFlagLike(String value) {
            addCriterion("ALLOW_REG_FLAG like", value, "allowRegFlag");
            return (Criteria) this;
        }

        public Criteria andAllowRegFlagNotLike(String value) {
            addCriterion("ALLOW_REG_FLAG not like", value, "allowRegFlag");
            return (Criteria) this;
        }

        public Criteria andAllowRegFlagIn(List<String> values) {
            addCriterion("ALLOW_REG_FLAG in", values, "allowRegFlag");
            return (Criteria) this;
        }

        public Criteria andAllowRegFlagNotIn(List<String> values) {
            addCriterion("ALLOW_REG_FLAG not in", values, "allowRegFlag");
            return (Criteria) this;
        }

        public Criteria andAllowRegFlagBetween(String value1, String value2) {
            addCriterion("ALLOW_REG_FLAG between", value1, value2, "allowRegFlag");
            return (Criteria) this;
        }

        public Criteria andAllowRegFlagNotBetween(String value1, String value2) {
            addCriterion("ALLOW_REG_FLAG not between", value1, value2, "allowRegFlag");
            return (Criteria) this;
        }

        public Criteria andRegPageIdIsNull() {
            addCriterion("REG_PAGE_ID is null");
            return (Criteria) this;
        }

        public Criteria andRegPageIdIsNotNull() {
            addCriterion("REG_PAGE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRegPageIdEqualTo(String value) {
            addCriterion("REG_PAGE_ID =", value, "regPageId");
            return (Criteria) this;
        }

        public Criteria andRegPageIdNotEqualTo(String value) {
            addCriterion("REG_PAGE_ID <>", value, "regPageId");
            return (Criteria) this;
        }

        public Criteria andRegPageIdGreaterThan(String value) {
            addCriterion("REG_PAGE_ID >", value, "regPageId");
            return (Criteria) this;
        }

        public Criteria andRegPageIdGreaterThanOrEqualTo(String value) {
            addCriterion("REG_PAGE_ID >=", value, "regPageId");
            return (Criteria) this;
        }

        public Criteria andRegPageIdLessThan(String value) {
            addCriterion("REG_PAGE_ID <", value, "regPageId");
            return (Criteria) this;
        }

        public Criteria andRegPageIdLessThanOrEqualTo(String value) {
            addCriterion("REG_PAGE_ID <=", value, "regPageId");
            return (Criteria) this;
        }

        public Criteria andRegPageIdLike(String value) {
            addCriterion("REG_PAGE_ID like", value, "regPageId");
            return (Criteria) this;
        }

        public Criteria andRegPageIdNotLike(String value) {
            addCriterion("REG_PAGE_ID not like", value, "regPageId");
            return (Criteria) this;
        }

        public Criteria andRegPageIdIn(List<String> values) {
            addCriterion("REG_PAGE_ID in", values, "regPageId");
            return (Criteria) this;
        }

        public Criteria andRegPageIdNotIn(List<String> values) {
            addCriterion("REG_PAGE_ID not in", values, "regPageId");
            return (Criteria) this;
        }

        public Criteria andRegPageIdBetween(String value1, String value2) {
            addCriterion("REG_PAGE_ID between", value1, value2, "regPageId");
            return (Criteria) this;
        }

        public Criteria andRegPageIdNotBetween(String value1, String value2) {
            addCriterion("REG_PAGE_ID not between", value1, value2, "regPageId");
            return (Criteria) this;
        }

        public Criteria andRegPageNameIsNull() {
            addCriterion("REG_PAGE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRegPageNameIsNotNull() {
            addCriterion("REG_PAGE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRegPageNameEqualTo(String value) {
            addCriterion("REG_PAGE_NAME =", value, "regPageName");
            return (Criteria) this;
        }

        public Criteria andRegPageNameNotEqualTo(String value) {
            addCriterion("REG_PAGE_NAME <>", value, "regPageName");
            return (Criteria) this;
        }

        public Criteria andRegPageNameGreaterThan(String value) {
            addCriterion("REG_PAGE_NAME >", value, "regPageName");
            return (Criteria) this;
        }

        public Criteria andRegPageNameGreaterThanOrEqualTo(String value) {
            addCriterion("REG_PAGE_NAME >=", value, "regPageName");
            return (Criteria) this;
        }

        public Criteria andRegPageNameLessThan(String value) {
            addCriterion("REG_PAGE_NAME <", value, "regPageName");
            return (Criteria) this;
        }

        public Criteria andRegPageNameLessThanOrEqualTo(String value) {
            addCriterion("REG_PAGE_NAME <=", value, "regPageName");
            return (Criteria) this;
        }

        public Criteria andRegPageNameLike(String value) {
            addCriterion("REG_PAGE_NAME like", value, "regPageName");
            return (Criteria) this;
        }

        public Criteria andRegPageNameNotLike(String value) {
            addCriterion("REG_PAGE_NAME not like", value, "regPageName");
            return (Criteria) this;
        }

        public Criteria andRegPageNameIn(List<String> values) {
            addCriterion("REG_PAGE_NAME in", values, "regPageName");
            return (Criteria) this;
        }

        public Criteria andRegPageNameNotIn(List<String> values) {
            addCriterion("REG_PAGE_NAME not in", values, "regPageName");
            return (Criteria) this;
        }

        public Criteria andRegPageNameBetween(String value1, String value2) {
            addCriterion("REG_PAGE_NAME between", value1, value2, "regPageName");
            return (Criteria) this;
        }

        public Criteria andRegPageNameNotBetween(String value1, String value2) {
            addCriterion("REG_PAGE_NAME not between", value1, value2, "regPageName");
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