package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class EdcModuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EdcModuleExample() {
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

        public Criteria andModuleFlowIsNull() {
            addCriterion("MODULE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andModuleFlowIsNotNull() {
            addCriterion("MODULE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andModuleFlowEqualTo(String value) {
            addCriterion("MODULE_FLOW =", value, "moduleFlow");
            return (Criteria) this;
        }

        public Criteria andModuleFlowNotEqualTo(String value) {
            addCriterion("MODULE_FLOW <>", value, "moduleFlow");
            return (Criteria) this;
        }

        public Criteria andModuleFlowGreaterThan(String value) {
            addCriterion("MODULE_FLOW >", value, "moduleFlow");
            return (Criteria) this;
        }

        public Criteria andModuleFlowGreaterThanOrEqualTo(String value) {
            addCriterion("MODULE_FLOW >=", value, "moduleFlow");
            return (Criteria) this;
        }

        public Criteria andModuleFlowLessThan(String value) {
            addCriterion("MODULE_FLOW <", value, "moduleFlow");
            return (Criteria) this;
        }

        public Criteria andModuleFlowLessThanOrEqualTo(String value) {
            addCriterion("MODULE_FLOW <=", value, "moduleFlow");
            return (Criteria) this;
        }

        public Criteria andModuleFlowLike(String value) {
            addCriterion("MODULE_FLOW like", value, "moduleFlow");
            return (Criteria) this;
        }

        public Criteria andModuleFlowNotLike(String value) {
            addCriterion("MODULE_FLOW not like", value, "moduleFlow");
            return (Criteria) this;
        }

        public Criteria andModuleFlowIn(List<String> values) {
            addCriterion("MODULE_FLOW in", values, "moduleFlow");
            return (Criteria) this;
        }

        public Criteria andModuleFlowNotIn(List<String> values) {
            addCriterion("MODULE_FLOW not in", values, "moduleFlow");
            return (Criteria) this;
        }

        public Criteria andModuleFlowBetween(String value1, String value2) {
            addCriterion("MODULE_FLOW between", value1, value2, "moduleFlow");
            return (Criteria) this;
        }

        public Criteria andModuleFlowNotBetween(String value1, String value2) {
            addCriterion("MODULE_FLOW not between", value1, value2, "moduleFlow");
            return (Criteria) this;
        }

        public Criteria andModuleNameIsNull() {
            addCriterion("MODULE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andModuleNameIsNotNull() {
            addCriterion("MODULE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andModuleNameEqualTo(String value) {
            addCriterion("MODULE_NAME =", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotEqualTo(String value) {
            addCriterion("MODULE_NAME <>", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameGreaterThan(String value) {
            addCriterion("MODULE_NAME >", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameGreaterThanOrEqualTo(String value) {
            addCriterion("MODULE_NAME >=", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLessThan(String value) {
            addCriterion("MODULE_NAME <", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLessThanOrEqualTo(String value) {
            addCriterion("MODULE_NAME <=", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLike(String value) {
            addCriterion("MODULE_NAME like", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotLike(String value) {
            addCriterion("MODULE_NAME not like", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameIn(List<String> values) {
            addCriterion("MODULE_NAME in", values, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotIn(List<String> values) {
            addCriterion("MODULE_NAME not in", values, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameBetween(String value1, String value2) {
            addCriterion("MODULE_NAME between", value1, value2, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotBetween(String value1, String value2) {
            addCriterion("MODULE_NAME not between", value1, value2, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleCodeIsNull() {
            addCriterion("MODULE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andModuleCodeIsNotNull() {
            addCriterion("MODULE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andModuleCodeEqualTo(String value) {
            addCriterion("MODULE_CODE =", value, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeNotEqualTo(String value) {
            addCriterion("MODULE_CODE <>", value, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeGreaterThan(String value) {
            addCriterion("MODULE_CODE >", value, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeGreaterThanOrEqualTo(String value) {
            addCriterion("MODULE_CODE >=", value, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeLessThan(String value) {
            addCriterion("MODULE_CODE <", value, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeLessThanOrEqualTo(String value) {
            addCriterion("MODULE_CODE <=", value, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeLike(String value) {
            addCriterion("MODULE_CODE like", value, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeNotLike(String value) {
            addCriterion("MODULE_CODE not like", value, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeIn(List<String> values) {
            addCriterion("MODULE_CODE in", values, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeNotIn(List<String> values) {
            addCriterion("MODULE_CODE not in", values, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeBetween(String value1, String value2) {
            addCriterion("MODULE_CODE between", value1, value2, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeNotBetween(String value1, String value2) {
            addCriterion("MODULE_CODE not between", value1, value2, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleTypeIdIsNull() {
            addCriterion("MODULE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andModuleTypeIdIsNotNull() {
            addCriterion("MODULE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andModuleTypeIdEqualTo(String value) {
            addCriterion("MODULE_TYPE_ID =", value, "moduleTypeId");
            return (Criteria) this;
        }

        public Criteria andModuleTypeIdNotEqualTo(String value) {
            addCriterion("MODULE_TYPE_ID <>", value, "moduleTypeId");
            return (Criteria) this;
        }

        public Criteria andModuleTypeIdGreaterThan(String value) {
            addCriterion("MODULE_TYPE_ID >", value, "moduleTypeId");
            return (Criteria) this;
        }

        public Criteria andModuleTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("MODULE_TYPE_ID >=", value, "moduleTypeId");
            return (Criteria) this;
        }

        public Criteria andModuleTypeIdLessThan(String value) {
            addCriterion("MODULE_TYPE_ID <", value, "moduleTypeId");
            return (Criteria) this;
        }

        public Criteria andModuleTypeIdLessThanOrEqualTo(String value) {
            addCriterion("MODULE_TYPE_ID <=", value, "moduleTypeId");
            return (Criteria) this;
        }

        public Criteria andModuleTypeIdLike(String value) {
            addCriterion("MODULE_TYPE_ID like", value, "moduleTypeId");
            return (Criteria) this;
        }

        public Criteria andModuleTypeIdNotLike(String value) {
            addCriterion("MODULE_TYPE_ID not like", value, "moduleTypeId");
            return (Criteria) this;
        }

        public Criteria andModuleTypeIdIn(List<String> values) {
            addCriterion("MODULE_TYPE_ID in", values, "moduleTypeId");
            return (Criteria) this;
        }

        public Criteria andModuleTypeIdNotIn(List<String> values) {
            addCriterion("MODULE_TYPE_ID not in", values, "moduleTypeId");
            return (Criteria) this;
        }

        public Criteria andModuleTypeIdBetween(String value1, String value2) {
            addCriterion("MODULE_TYPE_ID between", value1, value2, "moduleTypeId");
            return (Criteria) this;
        }

        public Criteria andModuleTypeIdNotBetween(String value1, String value2) {
            addCriterion("MODULE_TYPE_ID not between", value1, value2, "moduleTypeId");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNameIsNull() {
            addCriterion("MODULE_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNameIsNotNull() {
            addCriterion("MODULE_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNameEqualTo(String value) {
            addCriterion("MODULE_TYPE_NAME =", value, "moduleTypeName");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNameNotEqualTo(String value) {
            addCriterion("MODULE_TYPE_NAME <>", value, "moduleTypeName");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNameGreaterThan(String value) {
            addCriterion("MODULE_TYPE_NAME >", value, "moduleTypeName");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("MODULE_TYPE_NAME >=", value, "moduleTypeName");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNameLessThan(String value) {
            addCriterion("MODULE_TYPE_NAME <", value, "moduleTypeName");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNameLessThanOrEqualTo(String value) {
            addCriterion("MODULE_TYPE_NAME <=", value, "moduleTypeName");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNameLike(String value) {
            addCriterion("MODULE_TYPE_NAME like", value, "moduleTypeName");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNameNotLike(String value) {
            addCriterion("MODULE_TYPE_NAME not like", value, "moduleTypeName");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNameIn(List<String> values) {
            addCriterion("MODULE_TYPE_NAME in", values, "moduleTypeName");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNameNotIn(List<String> values) {
            addCriterion("MODULE_TYPE_NAME not in", values, "moduleTypeName");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNameBetween(String value1, String value2) {
            addCriterion("MODULE_TYPE_NAME between", value1, value2, "moduleTypeName");
            return (Criteria) this;
        }

        public Criteria andModuleTypeNameNotBetween(String value1, String value2) {
            addCriterion("MODULE_TYPE_NAME not between", value1, value2, "moduleTypeName");
            return (Criteria) this;
        }

        public Criteria andModuleShortNameIsNull() {
            addCriterion("MODULE_SHORT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andModuleShortNameIsNotNull() {
            addCriterion("MODULE_SHORT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andModuleShortNameEqualTo(String value) {
            addCriterion("MODULE_SHORT_NAME =", value, "moduleShortName");
            return (Criteria) this;
        }

        public Criteria andModuleShortNameNotEqualTo(String value) {
            addCriterion("MODULE_SHORT_NAME <>", value, "moduleShortName");
            return (Criteria) this;
        }

        public Criteria andModuleShortNameGreaterThan(String value) {
            addCriterion("MODULE_SHORT_NAME >", value, "moduleShortName");
            return (Criteria) this;
        }

        public Criteria andModuleShortNameGreaterThanOrEqualTo(String value) {
            addCriterion("MODULE_SHORT_NAME >=", value, "moduleShortName");
            return (Criteria) this;
        }

        public Criteria andModuleShortNameLessThan(String value) {
            addCriterion("MODULE_SHORT_NAME <", value, "moduleShortName");
            return (Criteria) this;
        }

        public Criteria andModuleShortNameLessThanOrEqualTo(String value) {
            addCriterion("MODULE_SHORT_NAME <=", value, "moduleShortName");
            return (Criteria) this;
        }

        public Criteria andModuleShortNameLike(String value) {
            addCriterion("MODULE_SHORT_NAME like", value, "moduleShortName");
            return (Criteria) this;
        }

        public Criteria andModuleShortNameNotLike(String value) {
            addCriterion("MODULE_SHORT_NAME not like", value, "moduleShortName");
            return (Criteria) this;
        }

        public Criteria andModuleShortNameIn(List<String> values) {
            addCriterion("MODULE_SHORT_NAME in", values, "moduleShortName");
            return (Criteria) this;
        }

        public Criteria andModuleShortNameNotIn(List<String> values) {
            addCriterion("MODULE_SHORT_NAME not in", values, "moduleShortName");
            return (Criteria) this;
        }

        public Criteria andModuleShortNameBetween(String value1, String value2) {
            addCriterion("MODULE_SHORT_NAME between", value1, value2, "moduleShortName");
            return (Criteria) this;
        }

        public Criteria andModuleShortNameNotBetween(String value1, String value2) {
            addCriterion("MODULE_SHORT_NAME not between", value1, value2, "moduleShortName");
            return (Criteria) this;
        }

        public Criteria andModuleSearchIsNull() {
            addCriterion("MODULE_SEARCH is null");
            return (Criteria) this;
        }

        public Criteria andModuleSearchIsNotNull() {
            addCriterion("MODULE_SEARCH is not null");
            return (Criteria) this;
        }

        public Criteria andModuleSearchEqualTo(String value) {
            addCriterion("MODULE_SEARCH =", value, "moduleSearch");
            return (Criteria) this;
        }

        public Criteria andModuleSearchNotEqualTo(String value) {
            addCriterion("MODULE_SEARCH <>", value, "moduleSearch");
            return (Criteria) this;
        }

        public Criteria andModuleSearchGreaterThan(String value) {
            addCriterion("MODULE_SEARCH >", value, "moduleSearch");
            return (Criteria) this;
        }

        public Criteria andModuleSearchGreaterThanOrEqualTo(String value) {
            addCriterion("MODULE_SEARCH >=", value, "moduleSearch");
            return (Criteria) this;
        }

        public Criteria andModuleSearchLessThan(String value) {
            addCriterion("MODULE_SEARCH <", value, "moduleSearch");
            return (Criteria) this;
        }

        public Criteria andModuleSearchLessThanOrEqualTo(String value) {
            addCriterion("MODULE_SEARCH <=", value, "moduleSearch");
            return (Criteria) this;
        }

        public Criteria andModuleSearchLike(String value) {
            addCriterion("MODULE_SEARCH like", value, "moduleSearch");
            return (Criteria) this;
        }

        public Criteria andModuleSearchNotLike(String value) {
            addCriterion("MODULE_SEARCH not like", value, "moduleSearch");
            return (Criteria) this;
        }

        public Criteria andModuleSearchIn(List<String> values) {
            addCriterion("MODULE_SEARCH in", values, "moduleSearch");
            return (Criteria) this;
        }

        public Criteria andModuleSearchNotIn(List<String> values) {
            addCriterion("MODULE_SEARCH not in", values, "moduleSearch");
            return (Criteria) this;
        }

        public Criteria andModuleSearchBetween(String value1, String value2) {
            addCriterion("MODULE_SEARCH between", value1, value2, "moduleSearch");
            return (Criteria) this;
        }

        public Criteria andModuleSearchNotBetween(String value1, String value2) {
            addCriterion("MODULE_SEARCH not between", value1, value2, "moduleSearch");
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

        public Criteria andModuleStyleIdIsNull() {
            addCriterion("MODULE_STYLE_ID is null");
            return (Criteria) this;
        }

        public Criteria andModuleStyleIdIsNotNull() {
            addCriterion("MODULE_STYLE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andModuleStyleIdEqualTo(String value) {
            addCriterion("MODULE_STYLE_ID =", value, "moduleStyleId");
            return (Criteria) this;
        }

        public Criteria andModuleStyleIdNotEqualTo(String value) {
            addCriterion("MODULE_STYLE_ID <>", value, "moduleStyleId");
            return (Criteria) this;
        }

        public Criteria andModuleStyleIdGreaterThan(String value) {
            addCriterion("MODULE_STYLE_ID >", value, "moduleStyleId");
            return (Criteria) this;
        }

        public Criteria andModuleStyleIdGreaterThanOrEqualTo(String value) {
            addCriterion("MODULE_STYLE_ID >=", value, "moduleStyleId");
            return (Criteria) this;
        }

        public Criteria andModuleStyleIdLessThan(String value) {
            addCriterion("MODULE_STYLE_ID <", value, "moduleStyleId");
            return (Criteria) this;
        }

        public Criteria andModuleStyleIdLessThanOrEqualTo(String value) {
            addCriterion("MODULE_STYLE_ID <=", value, "moduleStyleId");
            return (Criteria) this;
        }

        public Criteria andModuleStyleIdLike(String value) {
            addCriterion("MODULE_STYLE_ID like", value, "moduleStyleId");
            return (Criteria) this;
        }

        public Criteria andModuleStyleIdNotLike(String value) {
            addCriterion("MODULE_STYLE_ID not like", value, "moduleStyleId");
            return (Criteria) this;
        }

        public Criteria andModuleStyleIdIn(List<String> values) {
            addCriterion("MODULE_STYLE_ID in", values, "moduleStyleId");
            return (Criteria) this;
        }

        public Criteria andModuleStyleIdNotIn(List<String> values) {
            addCriterion("MODULE_STYLE_ID not in", values, "moduleStyleId");
            return (Criteria) this;
        }

        public Criteria andModuleStyleIdBetween(String value1, String value2) {
            addCriterion("MODULE_STYLE_ID between", value1, value2, "moduleStyleId");
            return (Criteria) this;
        }

        public Criteria andModuleStyleIdNotBetween(String value1, String value2) {
            addCriterion("MODULE_STYLE_ID not between", value1, value2, "moduleStyleId");
            return (Criteria) this;
        }

        public Criteria andModuleStyleNameIsNull() {
            addCriterion("MODULE_STYLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andModuleStyleNameIsNotNull() {
            addCriterion("MODULE_STYLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andModuleStyleNameEqualTo(String value) {
            addCriterion("MODULE_STYLE_NAME =", value, "moduleStyleName");
            return (Criteria) this;
        }

        public Criteria andModuleStyleNameNotEqualTo(String value) {
            addCriterion("MODULE_STYLE_NAME <>", value, "moduleStyleName");
            return (Criteria) this;
        }

        public Criteria andModuleStyleNameGreaterThan(String value) {
            addCriterion("MODULE_STYLE_NAME >", value, "moduleStyleName");
            return (Criteria) this;
        }

        public Criteria andModuleStyleNameGreaterThanOrEqualTo(String value) {
            addCriterion("MODULE_STYLE_NAME >=", value, "moduleStyleName");
            return (Criteria) this;
        }

        public Criteria andModuleStyleNameLessThan(String value) {
            addCriterion("MODULE_STYLE_NAME <", value, "moduleStyleName");
            return (Criteria) this;
        }

        public Criteria andModuleStyleNameLessThanOrEqualTo(String value) {
            addCriterion("MODULE_STYLE_NAME <=", value, "moduleStyleName");
            return (Criteria) this;
        }

        public Criteria andModuleStyleNameLike(String value) {
            addCriterion("MODULE_STYLE_NAME like", value, "moduleStyleName");
            return (Criteria) this;
        }

        public Criteria andModuleStyleNameNotLike(String value) {
            addCriterion("MODULE_STYLE_NAME not like", value, "moduleStyleName");
            return (Criteria) this;
        }

        public Criteria andModuleStyleNameIn(List<String> values) {
            addCriterion("MODULE_STYLE_NAME in", values, "moduleStyleName");
            return (Criteria) this;
        }

        public Criteria andModuleStyleNameNotIn(List<String> values) {
            addCriterion("MODULE_STYLE_NAME not in", values, "moduleStyleName");
            return (Criteria) this;
        }

        public Criteria andModuleStyleNameBetween(String value1, String value2) {
            addCriterion("MODULE_STYLE_NAME between", value1, value2, "moduleStyleName");
            return (Criteria) this;
        }

        public Criteria andModuleStyleNameNotBetween(String value1, String value2) {
            addCriterion("MODULE_STYLE_NAME not between", value1, value2, "moduleStyleName");
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