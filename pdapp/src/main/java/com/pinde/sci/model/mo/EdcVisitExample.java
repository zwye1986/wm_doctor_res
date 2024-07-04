package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class EdcVisitExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EdcVisitExample() {
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

        public Criteria andVisitFlowIsNull() {
            addCriterion("VISIT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andVisitFlowIsNotNull() {
            addCriterion("VISIT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andVisitFlowEqualTo(String value) {
            addCriterion("VISIT_FLOW =", value, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowNotEqualTo(String value) {
            addCriterion("VISIT_FLOW <>", value, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowGreaterThan(String value) {
            addCriterion("VISIT_FLOW >", value, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowGreaterThanOrEqualTo(String value) {
            addCriterion("VISIT_FLOW >=", value, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowLessThan(String value) {
            addCriterion("VISIT_FLOW <", value, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowLessThanOrEqualTo(String value) {
            addCriterion("VISIT_FLOW <=", value, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowLike(String value) {
            addCriterion("VISIT_FLOW like", value, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowNotLike(String value) {
            addCriterion("VISIT_FLOW not like", value, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowIn(List<String> values) {
            addCriterion("VISIT_FLOW in", values, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowNotIn(List<String> values) {
            addCriterion("VISIT_FLOW not in", values, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowBetween(String value1, String value2) {
            addCriterion("VISIT_FLOW between", value1, value2, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowNotBetween(String value1, String value2) {
            addCriterion("VISIT_FLOW not between", value1, value2, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitNameIsNull() {
            addCriterion("VISIT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andVisitNameIsNotNull() {
            addCriterion("VISIT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andVisitNameEqualTo(String value) {
            addCriterion("VISIT_NAME =", value, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameNotEqualTo(String value) {
            addCriterion("VISIT_NAME <>", value, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameGreaterThan(String value) {
            addCriterion("VISIT_NAME >", value, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameGreaterThanOrEqualTo(String value) {
            addCriterion("VISIT_NAME >=", value, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameLessThan(String value) {
            addCriterion("VISIT_NAME <", value, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameLessThanOrEqualTo(String value) {
            addCriterion("VISIT_NAME <=", value, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameLike(String value) {
            addCriterion("VISIT_NAME like", value, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameNotLike(String value) {
            addCriterion("VISIT_NAME not like", value, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameIn(List<String> values) {
            addCriterion("VISIT_NAME in", values, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameNotIn(List<String> values) {
            addCriterion("VISIT_NAME not in", values, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameBetween(String value1, String value2) {
            addCriterion("VISIT_NAME between", value1, value2, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameNotBetween(String value1, String value2) {
            addCriterion("VISIT_NAME not between", value1, value2, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIdIsNull() {
            addCriterion("VISIT_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIdIsNotNull() {
            addCriterion("VISIT_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIdEqualTo(String value) {
            addCriterion("VISIT_TYPE_ID =", value, "visitTypeId");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIdNotEqualTo(String value) {
            addCriterion("VISIT_TYPE_ID <>", value, "visitTypeId");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIdGreaterThan(String value) {
            addCriterion("VISIT_TYPE_ID >", value, "visitTypeId");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("VISIT_TYPE_ID >=", value, "visitTypeId");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIdLessThan(String value) {
            addCriterion("VISIT_TYPE_ID <", value, "visitTypeId");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIdLessThanOrEqualTo(String value) {
            addCriterion("VISIT_TYPE_ID <=", value, "visitTypeId");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIdLike(String value) {
            addCriterion("VISIT_TYPE_ID like", value, "visitTypeId");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIdNotLike(String value) {
            addCriterion("VISIT_TYPE_ID not like", value, "visitTypeId");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIdIn(List<String> values) {
            addCriterion("VISIT_TYPE_ID in", values, "visitTypeId");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIdNotIn(List<String> values) {
            addCriterion("VISIT_TYPE_ID not in", values, "visitTypeId");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIdBetween(String value1, String value2) {
            addCriterion("VISIT_TYPE_ID between", value1, value2, "visitTypeId");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIdNotBetween(String value1, String value2) {
            addCriterion("VISIT_TYPE_ID not between", value1, value2, "visitTypeId");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNameIsNull() {
            addCriterion("VISIT_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNameIsNotNull() {
            addCriterion("VISIT_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNameEqualTo(String value) {
            addCriterion("VISIT_TYPE_NAME =", value, "visitTypeName");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNameNotEqualTo(String value) {
            addCriterion("VISIT_TYPE_NAME <>", value, "visitTypeName");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNameGreaterThan(String value) {
            addCriterion("VISIT_TYPE_NAME >", value, "visitTypeName");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("VISIT_TYPE_NAME >=", value, "visitTypeName");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNameLessThan(String value) {
            addCriterion("VISIT_TYPE_NAME <", value, "visitTypeName");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNameLessThanOrEqualTo(String value) {
            addCriterion("VISIT_TYPE_NAME <=", value, "visitTypeName");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNameLike(String value) {
            addCriterion("VISIT_TYPE_NAME like", value, "visitTypeName");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNameNotLike(String value) {
            addCriterion("VISIT_TYPE_NAME not like", value, "visitTypeName");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNameIn(List<String> values) {
            addCriterion("VISIT_TYPE_NAME in", values, "visitTypeName");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNameNotIn(List<String> values) {
            addCriterion("VISIT_TYPE_NAME not in", values, "visitTypeName");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNameBetween(String value1, String value2) {
            addCriterion("VISIT_TYPE_NAME between", value1, value2, "visitTypeName");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNameNotBetween(String value1, String value2) {
            addCriterion("VISIT_TYPE_NAME not between", value1, value2, "visitTypeName");
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

        public Criteria andProjFlowIsNull() {
            addCriterion("PROJ_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andProjFlowIsNotNull() {
            addCriterion("PROJ_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andProjFlowEqualTo(String value) {
            addCriterion("PROJ_FLOW =", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowNotEqualTo(String value) {
            addCriterion("PROJ_FLOW <>", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowGreaterThan(String value) {
            addCriterion("PROJ_FLOW >", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_FLOW >=", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowLessThan(String value) {
            addCriterion("PROJ_FLOW <", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowLessThanOrEqualTo(String value) {
            addCriterion("PROJ_FLOW <=", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowLike(String value) {
            addCriterion("PROJ_FLOW like", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowNotLike(String value) {
            addCriterion("PROJ_FLOW not like", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowIn(List<String> values) {
            addCriterion("PROJ_FLOW in", values, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowNotIn(List<String> values) {
            addCriterion("PROJ_FLOW not in", values, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowBetween(String value1, String value2) {
            addCriterion("PROJ_FLOW between", value1, value2, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowNotBetween(String value1, String value2) {
            addCriterion("PROJ_FLOW not between", value1, value2, "projFlow");
            return (Criteria) this;
        }

        public Criteria andVisitWindowIsNull() {
            addCriterion("VISIT_WINDOW is null");
            return (Criteria) this;
        }

        public Criteria andVisitWindowIsNotNull() {
            addCriterion("VISIT_WINDOW is not null");
            return (Criteria) this;
        }

        public Criteria andVisitWindowEqualTo(String value) {
            addCriterion("VISIT_WINDOW =", value, "visitWindow");
            return (Criteria) this;
        }

        public Criteria andVisitWindowNotEqualTo(String value) {
            addCriterion("VISIT_WINDOW <>", value, "visitWindow");
            return (Criteria) this;
        }

        public Criteria andVisitWindowGreaterThan(String value) {
            addCriterion("VISIT_WINDOW >", value, "visitWindow");
            return (Criteria) this;
        }

        public Criteria andVisitWindowGreaterThanOrEqualTo(String value) {
            addCriterion("VISIT_WINDOW >=", value, "visitWindow");
            return (Criteria) this;
        }

        public Criteria andVisitWindowLessThan(String value) {
            addCriterion("VISIT_WINDOW <", value, "visitWindow");
            return (Criteria) this;
        }

        public Criteria andVisitWindowLessThanOrEqualTo(String value) {
            addCriterion("VISIT_WINDOW <=", value, "visitWindow");
            return (Criteria) this;
        }

        public Criteria andVisitWindowLike(String value) {
            addCriterion("VISIT_WINDOW like", value, "visitWindow");
            return (Criteria) this;
        }

        public Criteria andVisitWindowNotLike(String value) {
            addCriterion("VISIT_WINDOW not like", value, "visitWindow");
            return (Criteria) this;
        }

        public Criteria andVisitWindowIn(List<String> values) {
            addCriterion("VISIT_WINDOW in", values, "visitWindow");
            return (Criteria) this;
        }

        public Criteria andVisitWindowNotIn(List<String> values) {
            addCriterion("VISIT_WINDOW not in", values, "visitWindow");
            return (Criteria) this;
        }

        public Criteria andVisitWindowBetween(String value1, String value2) {
            addCriterion("VISIT_WINDOW between", value1, value2, "visitWindow");
            return (Criteria) this;
        }

        public Criteria andVisitWindowNotBetween(String value1, String value2) {
            addCriterion("VISIT_WINDOW not between", value1, value2, "visitWindow");
            return (Criteria) this;
        }

        public Criteria andIsVisitIsNull() {
            addCriterion("IS_VISIT is null");
            return (Criteria) this;
        }

        public Criteria andIsVisitIsNotNull() {
            addCriterion("IS_VISIT is not null");
            return (Criteria) this;
        }

        public Criteria andIsVisitEqualTo(String value) {
            addCriterion("IS_VISIT =", value, "isVisit");
            return (Criteria) this;
        }

        public Criteria andIsVisitNotEqualTo(String value) {
            addCriterion("IS_VISIT <>", value, "isVisit");
            return (Criteria) this;
        }

        public Criteria andIsVisitGreaterThan(String value) {
            addCriterion("IS_VISIT >", value, "isVisit");
            return (Criteria) this;
        }

        public Criteria andIsVisitGreaterThanOrEqualTo(String value) {
            addCriterion("IS_VISIT >=", value, "isVisit");
            return (Criteria) this;
        }

        public Criteria andIsVisitLessThan(String value) {
            addCriterion("IS_VISIT <", value, "isVisit");
            return (Criteria) this;
        }

        public Criteria andIsVisitLessThanOrEqualTo(String value) {
            addCriterion("IS_VISIT <=", value, "isVisit");
            return (Criteria) this;
        }

        public Criteria andIsVisitLike(String value) {
            addCriterion("IS_VISIT like", value, "isVisit");
            return (Criteria) this;
        }

        public Criteria andIsVisitNotLike(String value) {
            addCriterion("IS_VISIT not like", value, "isVisit");
            return (Criteria) this;
        }

        public Criteria andIsVisitIn(List<String> values) {
            addCriterion("IS_VISIT in", values, "isVisit");
            return (Criteria) this;
        }

        public Criteria andIsVisitNotIn(List<String> values) {
            addCriterion("IS_VISIT not in", values, "isVisit");
            return (Criteria) this;
        }

        public Criteria andIsVisitBetween(String value1, String value2) {
            addCriterion("IS_VISIT between", value1, value2, "isVisit");
            return (Criteria) this;
        }

        public Criteria andIsVisitNotBetween(String value1, String value2) {
            addCriterion("IS_VISIT not between", value1, value2, "isVisit");
            return (Criteria) this;
        }

        public Criteria andIsBaselineIsNull() {
            addCriterion("IS_BASELINE is null");
            return (Criteria) this;
        }

        public Criteria andIsBaselineIsNotNull() {
            addCriterion("IS_BASELINE is not null");
            return (Criteria) this;
        }

        public Criteria andIsBaselineEqualTo(String value) {
            addCriterion("IS_BASELINE =", value, "isBaseline");
            return (Criteria) this;
        }

        public Criteria andIsBaselineNotEqualTo(String value) {
            addCriterion("IS_BASELINE <>", value, "isBaseline");
            return (Criteria) this;
        }

        public Criteria andIsBaselineGreaterThan(String value) {
            addCriterion("IS_BASELINE >", value, "isBaseline");
            return (Criteria) this;
        }

        public Criteria andIsBaselineGreaterThanOrEqualTo(String value) {
            addCriterion("IS_BASELINE >=", value, "isBaseline");
            return (Criteria) this;
        }

        public Criteria andIsBaselineLessThan(String value) {
            addCriterion("IS_BASELINE <", value, "isBaseline");
            return (Criteria) this;
        }

        public Criteria andIsBaselineLessThanOrEqualTo(String value) {
            addCriterion("IS_BASELINE <=", value, "isBaseline");
            return (Criteria) this;
        }

        public Criteria andIsBaselineLike(String value) {
            addCriterion("IS_BASELINE like", value, "isBaseline");
            return (Criteria) this;
        }

        public Criteria andIsBaselineNotLike(String value) {
            addCriterion("IS_BASELINE not like", value, "isBaseline");
            return (Criteria) this;
        }

        public Criteria andIsBaselineIn(List<String> values) {
            addCriterion("IS_BASELINE in", values, "isBaseline");
            return (Criteria) this;
        }

        public Criteria andIsBaselineNotIn(List<String> values) {
            addCriterion("IS_BASELINE not in", values, "isBaseline");
            return (Criteria) this;
        }

        public Criteria andIsBaselineBetween(String value1, String value2) {
            addCriterion("IS_BASELINE between", value1, value2, "isBaseline");
            return (Criteria) this;
        }

        public Criteria andIsBaselineNotBetween(String value1, String value2) {
            addCriterion("IS_BASELINE not between", value1, value2, "isBaseline");
            return (Criteria) this;
        }

        public Criteria andGroupFlowIsNull() {
            addCriterion("GROUP_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andGroupFlowIsNotNull() {
            addCriterion("GROUP_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andGroupFlowEqualTo(String value) {
            addCriterion("GROUP_FLOW =", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowNotEqualTo(String value) {
            addCriterion("GROUP_FLOW <>", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowGreaterThan(String value) {
            addCriterion("GROUP_FLOW >", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_FLOW >=", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowLessThan(String value) {
            addCriterion("GROUP_FLOW <", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowLessThanOrEqualTo(String value) {
            addCriterion("GROUP_FLOW <=", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowLike(String value) {
            addCriterion("GROUP_FLOW like", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowNotLike(String value) {
            addCriterion("GROUP_FLOW not like", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowIn(List<String> values) {
            addCriterion("GROUP_FLOW in", values, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowNotIn(List<String> values) {
            addCriterion("GROUP_FLOW not in", values, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowBetween(String value1, String value2) {
            addCriterion("GROUP_FLOW between", value1, value2, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowNotBetween(String value1, String value2) {
            addCriterion("GROUP_FLOW not between", value1, value2, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNull() {
            addCriterion("GROUP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("GROUP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("GROUP_NAME =", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("GROUP_NAME <>", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("GROUP_NAME >", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_NAME >=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("GROUP_NAME <", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("GROUP_NAME <=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("GROUP_NAME like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("GROUP_NAME not like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIn(List<String> values) {
            addCriterion("GROUP_NAME in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotIn(List<String> values) {
            addCriterion("GROUP_NAME not in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("GROUP_NAME between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("GROUP_NAME not between", value1, value2, "groupName");
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