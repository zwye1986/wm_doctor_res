package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class AidProjExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AidProjExample() {
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

        public Criteria andProjYearIsNull() {
            addCriterion("PROJ_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andProjYearIsNotNull() {
            addCriterion("PROJ_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andProjYearEqualTo(String value) {
            addCriterion("PROJ_YEAR =", value, "projYear");
            return (Criteria) this;
        }

        public Criteria andProjYearNotEqualTo(String value) {
            addCriterion("PROJ_YEAR <>", value, "projYear");
            return (Criteria) this;
        }

        public Criteria andProjYearGreaterThan(String value) {
            addCriterion("PROJ_YEAR >", value, "projYear");
            return (Criteria) this;
        }

        public Criteria andProjYearGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_YEAR >=", value, "projYear");
            return (Criteria) this;
        }

        public Criteria andProjYearLessThan(String value) {
            addCriterion("PROJ_YEAR <", value, "projYear");
            return (Criteria) this;
        }

        public Criteria andProjYearLessThanOrEqualTo(String value) {
            addCriterion("PROJ_YEAR <=", value, "projYear");
            return (Criteria) this;
        }

        public Criteria andProjYearLike(String value) {
            addCriterion("PROJ_YEAR like", value, "projYear");
            return (Criteria) this;
        }

        public Criteria andProjYearNotLike(String value) {
            addCriterion("PROJ_YEAR not like", value, "projYear");
            return (Criteria) this;
        }

        public Criteria andProjYearIn(List<String> values) {
            addCriterion("PROJ_YEAR in", values, "projYear");
            return (Criteria) this;
        }

        public Criteria andProjYearNotIn(List<String> values) {
            addCriterion("PROJ_YEAR not in", values, "projYear");
            return (Criteria) this;
        }

        public Criteria andProjYearBetween(String value1, String value2) {
            addCriterion("PROJ_YEAR between", value1, value2, "projYear");
            return (Criteria) this;
        }

        public Criteria andProjYearNotBetween(String value1, String value2) {
            addCriterion("PROJ_YEAR not between", value1, value2, "projYear");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerIsNull() {
            addCriterion("PROJ_DECLARER is null");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerIsNotNull() {
            addCriterion("PROJ_DECLARER is not null");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerEqualTo(String value) {
            addCriterion("PROJ_DECLARER =", value, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerNotEqualTo(String value) {
            addCriterion("PROJ_DECLARER <>", value, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerGreaterThan(String value) {
            addCriterion("PROJ_DECLARER >", value, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_DECLARER >=", value, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerLessThan(String value) {
            addCriterion("PROJ_DECLARER <", value, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerLessThanOrEqualTo(String value) {
            addCriterion("PROJ_DECLARER <=", value, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerLike(String value) {
            addCriterion("PROJ_DECLARER like", value, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerNotLike(String value) {
            addCriterion("PROJ_DECLARER not like", value, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerIn(List<String> values) {
            addCriterion("PROJ_DECLARER in", values, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerNotIn(List<String> values) {
            addCriterion("PROJ_DECLARER not in", values, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerBetween(String value1, String value2) {
            addCriterion("PROJ_DECLARER between", value1, value2, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjDeclarerNotBetween(String value1, String value2) {
            addCriterion("PROJ_DECLARER not between", value1, value2, "projDeclarer");
            return (Criteria) this;
        }

        public Criteria andProjNameIsNull() {
            addCriterion("PROJ_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjNameIsNotNull() {
            addCriterion("PROJ_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjNameEqualTo(String value) {
            addCriterion("PROJ_NAME =", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameNotEqualTo(String value) {
            addCriterion("PROJ_NAME <>", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameGreaterThan(String value) {
            addCriterion("PROJ_NAME >", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_NAME >=", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameLessThan(String value) {
            addCriterion("PROJ_NAME <", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameLessThanOrEqualTo(String value) {
            addCriterion("PROJ_NAME <=", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameLike(String value) {
            addCriterion("PROJ_NAME like", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameNotLike(String value) {
            addCriterion("PROJ_NAME not like", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameIn(List<String> values) {
            addCriterion("PROJ_NAME in", values, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameNotIn(List<String> values) {
            addCriterion("PROJ_NAME not in", values, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameBetween(String value1, String value2) {
            addCriterion("PROJ_NAME between", value1, value2, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameNotBetween(String value1, String value2) {
            addCriterion("PROJ_NAME not between", value1, value2, "projName");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeIsNull() {
            addCriterion("PROJ_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeIsNotNull() {
            addCriterion("PROJ_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeEqualTo(String value) {
            addCriterion("PROJ_START_TIME =", value, "projStartTime");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeNotEqualTo(String value) {
            addCriterion("PROJ_START_TIME <>", value, "projStartTime");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeGreaterThan(String value) {
            addCriterion("PROJ_START_TIME >", value, "projStartTime");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_START_TIME >=", value, "projStartTime");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeLessThan(String value) {
            addCriterion("PROJ_START_TIME <", value, "projStartTime");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeLessThanOrEqualTo(String value) {
            addCriterion("PROJ_START_TIME <=", value, "projStartTime");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeLike(String value) {
            addCriterion("PROJ_START_TIME like", value, "projStartTime");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeNotLike(String value) {
            addCriterion("PROJ_START_TIME not like", value, "projStartTime");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeIn(List<String> values) {
            addCriterion("PROJ_START_TIME in", values, "projStartTime");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeNotIn(List<String> values) {
            addCriterion("PROJ_START_TIME not in", values, "projStartTime");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeBetween(String value1, String value2) {
            addCriterion("PROJ_START_TIME between", value1, value2, "projStartTime");
            return (Criteria) this;
        }

        public Criteria andProjStartTimeNotBetween(String value1, String value2) {
            addCriterion("PROJ_START_TIME not between", value1, value2, "projStartTime");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeIsNull() {
            addCriterion("PROJ_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeIsNotNull() {
            addCriterion("PROJ_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeEqualTo(String value) {
            addCriterion("PROJ_END_TIME =", value, "projEndTime");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeNotEqualTo(String value) {
            addCriterion("PROJ_END_TIME <>", value, "projEndTime");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeGreaterThan(String value) {
            addCriterion("PROJ_END_TIME >", value, "projEndTime");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_END_TIME >=", value, "projEndTime");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeLessThan(String value) {
            addCriterion("PROJ_END_TIME <", value, "projEndTime");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeLessThanOrEqualTo(String value) {
            addCriterion("PROJ_END_TIME <=", value, "projEndTime");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeLike(String value) {
            addCriterion("PROJ_END_TIME like", value, "projEndTime");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeNotLike(String value) {
            addCriterion("PROJ_END_TIME not like", value, "projEndTime");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeIn(List<String> values) {
            addCriterion("PROJ_END_TIME in", values, "projEndTime");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeNotIn(List<String> values) {
            addCriterion("PROJ_END_TIME not in", values, "projEndTime");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeBetween(String value1, String value2) {
            addCriterion("PROJ_END_TIME between", value1, value2, "projEndTime");
            return (Criteria) this;
        }

        public Criteria andProjEndTimeNotBetween(String value1, String value2) {
            addCriterion("PROJ_END_TIME not between", value1, value2, "projEndTime");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdIsNull() {
            addCriterion("PROJ_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdIsNotNull() {
            addCriterion("PROJ_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdEqualTo(String value) {
            addCriterion("PROJ_CATEGORY_ID =", value, "projCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdNotEqualTo(String value) {
            addCriterion("PROJ_CATEGORY_ID <>", value, "projCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdGreaterThan(String value) {
            addCriterion("PROJ_CATEGORY_ID >", value, "projCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_CATEGORY_ID >=", value, "projCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdLessThan(String value) {
            addCriterion("PROJ_CATEGORY_ID <", value, "projCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("PROJ_CATEGORY_ID <=", value, "projCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdLike(String value) {
            addCriterion("PROJ_CATEGORY_ID like", value, "projCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdNotLike(String value) {
            addCriterion("PROJ_CATEGORY_ID not like", value, "projCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdIn(List<String> values) {
            addCriterion("PROJ_CATEGORY_ID in", values, "projCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdNotIn(List<String> values) {
            addCriterion("PROJ_CATEGORY_ID not in", values, "projCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdBetween(String value1, String value2) {
            addCriterion("PROJ_CATEGORY_ID between", value1, value2, "projCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjCategoryIdNotBetween(String value1, String value2) {
            addCriterion("PROJ_CATEGORY_ID not between", value1, value2, "projCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameIsNull() {
            addCriterion("PROJ_CATEGORY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameIsNotNull() {
            addCriterion("PROJ_CATEGORY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameEqualTo(String value) {
            addCriterion("PROJ_CATEGORY_NAME =", value, "projCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameNotEqualTo(String value) {
            addCriterion("PROJ_CATEGORY_NAME <>", value, "projCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameGreaterThan(String value) {
            addCriterion("PROJ_CATEGORY_NAME >", value, "projCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_CATEGORY_NAME >=", value, "projCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameLessThan(String value) {
            addCriterion("PROJ_CATEGORY_NAME <", value, "projCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("PROJ_CATEGORY_NAME <=", value, "projCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameLike(String value) {
            addCriterion("PROJ_CATEGORY_NAME like", value, "projCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameNotLike(String value) {
            addCriterion("PROJ_CATEGORY_NAME not like", value, "projCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameIn(List<String> values) {
            addCriterion("PROJ_CATEGORY_NAME in", values, "projCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameNotIn(List<String> values) {
            addCriterion("PROJ_CATEGORY_NAME not in", values, "projCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameBetween(String value1, String value2) {
            addCriterion("PROJ_CATEGORY_NAME between", value1, value2, "projCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjCategoryNameNotBetween(String value1, String value2) {
            addCriterion("PROJ_CATEGORY_NAME not between", value1, value2, "projCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjNoIsNull() {
            addCriterion("PROJ_NO is null");
            return (Criteria) this;
        }

        public Criteria andProjNoIsNotNull() {
            addCriterion("PROJ_NO is not null");
            return (Criteria) this;
        }

        public Criteria andProjNoEqualTo(String value) {
            addCriterion("PROJ_NO =", value, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoNotEqualTo(String value) {
            addCriterion("PROJ_NO <>", value, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoGreaterThan(String value) {
            addCriterion("PROJ_NO >", value, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_NO >=", value, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoLessThan(String value) {
            addCriterion("PROJ_NO <", value, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoLessThanOrEqualTo(String value) {
            addCriterion("PROJ_NO <=", value, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoLike(String value) {
            addCriterion("PROJ_NO like", value, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoNotLike(String value) {
            addCriterion("PROJ_NO not like", value, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoIn(List<String> values) {
            addCriterion("PROJ_NO in", values, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoNotIn(List<String> values) {
            addCriterion("PROJ_NO not in", values, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoBetween(String value1, String value2) {
            addCriterion("PROJ_NO between", value1, value2, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjNoNotBetween(String value1, String value2) {
            addCriterion("PROJ_NO not between", value1, value2, "projNo");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdIsNull() {
            addCriterion("PROJ_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdIsNotNull() {
            addCriterion("PROJ_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdEqualTo(String value) {
            addCriterion("PROJ_TYPE_ID =", value, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdNotEqualTo(String value) {
            addCriterion("PROJ_TYPE_ID <>", value, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdGreaterThan(String value) {
            addCriterion("PROJ_TYPE_ID >", value, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_TYPE_ID >=", value, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdLessThan(String value) {
            addCriterion("PROJ_TYPE_ID <", value, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdLessThanOrEqualTo(String value) {
            addCriterion("PROJ_TYPE_ID <=", value, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdLike(String value) {
            addCriterion("PROJ_TYPE_ID like", value, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdNotLike(String value) {
            addCriterion("PROJ_TYPE_ID not like", value, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdIn(List<String> values) {
            addCriterion("PROJ_TYPE_ID in", values, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdNotIn(List<String> values) {
            addCriterion("PROJ_TYPE_ID not in", values, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdBetween(String value1, String value2) {
            addCriterion("PROJ_TYPE_ID between", value1, value2, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdNotBetween(String value1, String value2) {
            addCriterion("PROJ_TYPE_ID not between", value1, value2, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameIsNull() {
            addCriterion("PROJ_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameIsNotNull() {
            addCriterion("PROJ_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameEqualTo(String value) {
            addCriterion("PROJ_TYPE_NAME =", value, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameNotEqualTo(String value) {
            addCriterion("PROJ_TYPE_NAME <>", value, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameGreaterThan(String value) {
            addCriterion("PROJ_TYPE_NAME >", value, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_TYPE_NAME >=", value, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameLessThan(String value) {
            addCriterion("PROJ_TYPE_NAME <", value, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameLessThanOrEqualTo(String value) {
            addCriterion("PROJ_TYPE_NAME <=", value, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameLike(String value) {
            addCriterion("PROJ_TYPE_NAME like", value, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameNotLike(String value) {
            addCriterion("PROJ_TYPE_NAME not like", value, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameIn(List<String> values) {
            addCriterion("PROJ_TYPE_NAME in", values, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameNotIn(List<String> values) {
            addCriterion("PROJ_TYPE_NAME not in", values, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameBetween(String value1, String value2) {
            addCriterion("PROJ_TYPE_NAME between", value1, value2, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameNotBetween(String value1, String value2) {
            addCriterion("PROJ_TYPE_NAME not between", value1, value2, "projTypeName");
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

        public Criteria andApplyUserPhoneIsNull() {
            addCriterion("APPLY_USER_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneIsNotNull() {
            addCriterion("APPLY_USER_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneEqualTo(String value) {
            addCriterion("APPLY_USER_PHONE =", value, "applyUserPhone");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneNotEqualTo(String value) {
            addCriterion("APPLY_USER_PHONE <>", value, "applyUserPhone");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneGreaterThan(String value) {
            addCriterion("APPLY_USER_PHONE >", value, "applyUserPhone");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_PHONE >=", value, "applyUserPhone");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneLessThan(String value) {
            addCriterion("APPLY_USER_PHONE <", value, "applyUserPhone");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneLessThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_PHONE <=", value, "applyUserPhone");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneLike(String value) {
            addCriterion("APPLY_USER_PHONE like", value, "applyUserPhone");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneNotLike(String value) {
            addCriterion("APPLY_USER_PHONE not like", value, "applyUserPhone");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneIn(List<String> values) {
            addCriterion("APPLY_USER_PHONE in", values, "applyUserPhone");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneNotIn(List<String> values) {
            addCriterion("APPLY_USER_PHONE not in", values, "applyUserPhone");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneBetween(String value1, String value2) {
            addCriterion("APPLY_USER_PHONE between", value1, value2, "applyUserPhone");
            return (Criteria) this;
        }

        public Criteria andApplyUserPhoneNotBetween(String value1, String value2) {
            addCriterion("APPLY_USER_PHONE not between", value1, value2, "applyUserPhone");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmailIsNull() {
            addCriterion("APPLY_USER_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmailIsNotNull() {
            addCriterion("APPLY_USER_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmailEqualTo(String value) {
            addCriterion("APPLY_USER_EMAIL =", value, "applyUserEmail");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmailNotEqualTo(String value) {
            addCriterion("APPLY_USER_EMAIL <>", value, "applyUserEmail");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmailGreaterThan(String value) {
            addCriterion("APPLY_USER_EMAIL >", value, "applyUserEmail");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmailGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_EMAIL >=", value, "applyUserEmail");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmailLessThan(String value) {
            addCriterion("APPLY_USER_EMAIL <", value, "applyUserEmail");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmailLessThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_EMAIL <=", value, "applyUserEmail");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmailLike(String value) {
            addCriterion("APPLY_USER_EMAIL like", value, "applyUserEmail");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmailNotLike(String value) {
            addCriterion("APPLY_USER_EMAIL not like", value, "applyUserEmail");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmailIn(List<String> values) {
            addCriterion("APPLY_USER_EMAIL in", values, "applyUserEmail");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmailNotIn(List<String> values) {
            addCriterion("APPLY_USER_EMAIL not in", values, "applyUserEmail");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmailBetween(String value1, String value2) {
            addCriterion("APPLY_USER_EMAIL between", value1, value2, "applyUserEmail");
            return (Criteria) this;
        }

        public Criteria andApplyUserEmailNotBetween(String value1, String value2) {
            addCriterion("APPLY_USER_EMAIL not between", value1, value2, "applyUserEmail");
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

        public Criteria andChargeOrgNameIsNull() {
            addCriterion("CHARGE_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameIsNotNull() {
            addCriterion("CHARGE_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameEqualTo(String value) {
            addCriterion("CHARGE_ORG_NAME =", value, "chargeOrgName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameNotEqualTo(String value) {
            addCriterion("CHARGE_ORG_NAME <>", value, "chargeOrgName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameGreaterThan(String value) {
            addCriterion("CHARGE_ORG_NAME >", value, "chargeOrgName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("CHARGE_ORG_NAME >=", value, "chargeOrgName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameLessThan(String value) {
            addCriterion("CHARGE_ORG_NAME <", value, "chargeOrgName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameLessThanOrEqualTo(String value) {
            addCriterion("CHARGE_ORG_NAME <=", value, "chargeOrgName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameLike(String value) {
            addCriterion("CHARGE_ORG_NAME like", value, "chargeOrgName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameNotLike(String value) {
            addCriterion("CHARGE_ORG_NAME not like", value, "chargeOrgName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameIn(List<String> values) {
            addCriterion("CHARGE_ORG_NAME in", values, "chargeOrgName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameNotIn(List<String> values) {
            addCriterion("CHARGE_ORG_NAME not in", values, "chargeOrgName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameBetween(String value1, String value2) {
            addCriterion("CHARGE_ORG_NAME between", value1, value2, "chargeOrgName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgNameNotBetween(String value1, String value2) {
            addCriterion("CHARGE_ORG_NAME not between", value1, value2, "chargeOrgName");
            return (Criteria) this;
        }

        public Criteria andTotalInvestmentIsNull() {
            addCriterion("TOTAL_INVESTMENT is null");
            return (Criteria) this;
        }

        public Criteria andTotalInvestmentIsNotNull() {
            addCriterion("TOTAL_INVESTMENT is not null");
            return (Criteria) this;
        }

        public Criteria andTotalInvestmentEqualTo(String value) {
            addCriterion("TOTAL_INVESTMENT =", value, "totalInvestment");
            return (Criteria) this;
        }

        public Criteria andTotalInvestmentNotEqualTo(String value) {
            addCriterion("TOTAL_INVESTMENT <>", value, "totalInvestment");
            return (Criteria) this;
        }

        public Criteria andTotalInvestmentGreaterThan(String value) {
            addCriterion("TOTAL_INVESTMENT >", value, "totalInvestment");
            return (Criteria) this;
        }

        public Criteria andTotalInvestmentGreaterThanOrEqualTo(String value) {
            addCriterion("TOTAL_INVESTMENT >=", value, "totalInvestment");
            return (Criteria) this;
        }

        public Criteria andTotalInvestmentLessThan(String value) {
            addCriterion("TOTAL_INVESTMENT <", value, "totalInvestment");
            return (Criteria) this;
        }

        public Criteria andTotalInvestmentLessThanOrEqualTo(String value) {
            addCriterion("TOTAL_INVESTMENT <=", value, "totalInvestment");
            return (Criteria) this;
        }

        public Criteria andTotalInvestmentLike(String value) {
            addCriterion("TOTAL_INVESTMENT like", value, "totalInvestment");
            return (Criteria) this;
        }

        public Criteria andTotalInvestmentNotLike(String value) {
            addCriterion("TOTAL_INVESTMENT not like", value, "totalInvestment");
            return (Criteria) this;
        }

        public Criteria andTotalInvestmentIn(List<String> values) {
            addCriterion("TOTAL_INVESTMENT in", values, "totalInvestment");
            return (Criteria) this;
        }

        public Criteria andTotalInvestmentNotIn(List<String> values) {
            addCriterion("TOTAL_INVESTMENT not in", values, "totalInvestment");
            return (Criteria) this;
        }

        public Criteria andTotalInvestmentBetween(String value1, String value2) {
            addCriterion("TOTAL_INVESTMENT between", value1, value2, "totalInvestment");
            return (Criteria) this;
        }

        public Criteria andTotalInvestmentNotBetween(String value1, String value2) {
            addCriterion("TOTAL_INVESTMENT not between", value1, value2, "totalInvestment");
            return (Criteria) this;
        }

        public Criteria andCountryInvestmentIsNull() {
            addCriterion("COUNTRY_INVESTMENT is null");
            return (Criteria) this;
        }

        public Criteria andCountryInvestmentIsNotNull() {
            addCriterion("COUNTRY_INVESTMENT is not null");
            return (Criteria) this;
        }

        public Criteria andCountryInvestmentEqualTo(String value) {
            addCriterion("COUNTRY_INVESTMENT =", value, "countryInvestment");
            return (Criteria) this;
        }

        public Criteria andCountryInvestmentNotEqualTo(String value) {
            addCriterion("COUNTRY_INVESTMENT <>", value, "countryInvestment");
            return (Criteria) this;
        }

        public Criteria andCountryInvestmentGreaterThan(String value) {
            addCriterion("COUNTRY_INVESTMENT >", value, "countryInvestment");
            return (Criteria) this;
        }

        public Criteria andCountryInvestmentGreaterThanOrEqualTo(String value) {
            addCriterion("COUNTRY_INVESTMENT >=", value, "countryInvestment");
            return (Criteria) this;
        }

        public Criteria andCountryInvestmentLessThan(String value) {
            addCriterion("COUNTRY_INVESTMENT <", value, "countryInvestment");
            return (Criteria) this;
        }

        public Criteria andCountryInvestmentLessThanOrEqualTo(String value) {
            addCriterion("COUNTRY_INVESTMENT <=", value, "countryInvestment");
            return (Criteria) this;
        }

        public Criteria andCountryInvestmentLike(String value) {
            addCriterion("COUNTRY_INVESTMENT like", value, "countryInvestment");
            return (Criteria) this;
        }

        public Criteria andCountryInvestmentNotLike(String value) {
            addCriterion("COUNTRY_INVESTMENT not like", value, "countryInvestment");
            return (Criteria) this;
        }

        public Criteria andCountryInvestmentIn(List<String> values) {
            addCriterion("COUNTRY_INVESTMENT in", values, "countryInvestment");
            return (Criteria) this;
        }

        public Criteria andCountryInvestmentNotIn(List<String> values) {
            addCriterion("COUNTRY_INVESTMENT not in", values, "countryInvestment");
            return (Criteria) this;
        }

        public Criteria andCountryInvestmentBetween(String value1, String value2) {
            addCriterion("COUNTRY_INVESTMENT between", value1, value2, "countryInvestment");
            return (Criteria) this;
        }

        public Criteria andCountryInvestmentNotBetween(String value1, String value2) {
            addCriterion("COUNTRY_INVESTMENT not between", value1, value2, "countryInvestment");
            return (Criteria) this;
        }

        public Criteria andChargeInvestmentIsNull() {
            addCriterion("CHARGE_INVESTMENT is null");
            return (Criteria) this;
        }

        public Criteria andChargeInvestmentIsNotNull() {
            addCriterion("CHARGE_INVESTMENT is not null");
            return (Criteria) this;
        }

        public Criteria andChargeInvestmentEqualTo(String value) {
            addCriterion("CHARGE_INVESTMENT =", value, "chargeInvestment");
            return (Criteria) this;
        }

        public Criteria andChargeInvestmentNotEqualTo(String value) {
            addCriterion("CHARGE_INVESTMENT <>", value, "chargeInvestment");
            return (Criteria) this;
        }

        public Criteria andChargeInvestmentGreaterThan(String value) {
            addCriterion("CHARGE_INVESTMENT >", value, "chargeInvestment");
            return (Criteria) this;
        }

        public Criteria andChargeInvestmentGreaterThanOrEqualTo(String value) {
            addCriterion("CHARGE_INVESTMENT >=", value, "chargeInvestment");
            return (Criteria) this;
        }

        public Criteria andChargeInvestmentLessThan(String value) {
            addCriterion("CHARGE_INVESTMENT <", value, "chargeInvestment");
            return (Criteria) this;
        }

        public Criteria andChargeInvestmentLessThanOrEqualTo(String value) {
            addCriterion("CHARGE_INVESTMENT <=", value, "chargeInvestment");
            return (Criteria) this;
        }

        public Criteria andChargeInvestmentLike(String value) {
            addCriterion("CHARGE_INVESTMENT like", value, "chargeInvestment");
            return (Criteria) this;
        }

        public Criteria andChargeInvestmentNotLike(String value) {
            addCriterion("CHARGE_INVESTMENT not like", value, "chargeInvestment");
            return (Criteria) this;
        }

        public Criteria andChargeInvestmentIn(List<String> values) {
            addCriterion("CHARGE_INVESTMENT in", values, "chargeInvestment");
            return (Criteria) this;
        }

        public Criteria andChargeInvestmentNotIn(List<String> values) {
            addCriterion("CHARGE_INVESTMENT not in", values, "chargeInvestment");
            return (Criteria) this;
        }

        public Criteria andChargeInvestmentBetween(String value1, String value2) {
            addCriterion("CHARGE_INVESTMENT between", value1, value2, "chargeInvestment");
            return (Criteria) this;
        }

        public Criteria andChargeInvestmentNotBetween(String value1, String value2) {
            addCriterion("CHARGE_INVESTMENT not between", value1, value2, "chargeInvestment");
            return (Criteria) this;
        }

        public Criteria andOrgInvestmentIsNull() {
            addCriterion("ORG_INVESTMENT is null");
            return (Criteria) this;
        }

        public Criteria andOrgInvestmentIsNotNull() {
            addCriterion("ORG_INVESTMENT is not null");
            return (Criteria) this;
        }

        public Criteria andOrgInvestmentEqualTo(String value) {
            addCriterion("ORG_INVESTMENT =", value, "orgInvestment");
            return (Criteria) this;
        }

        public Criteria andOrgInvestmentNotEqualTo(String value) {
            addCriterion("ORG_INVESTMENT <>", value, "orgInvestment");
            return (Criteria) this;
        }

        public Criteria andOrgInvestmentGreaterThan(String value) {
            addCriterion("ORG_INVESTMENT >", value, "orgInvestment");
            return (Criteria) this;
        }

        public Criteria andOrgInvestmentGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_INVESTMENT >=", value, "orgInvestment");
            return (Criteria) this;
        }

        public Criteria andOrgInvestmentLessThan(String value) {
            addCriterion("ORG_INVESTMENT <", value, "orgInvestment");
            return (Criteria) this;
        }

        public Criteria andOrgInvestmentLessThanOrEqualTo(String value) {
            addCriterion("ORG_INVESTMENT <=", value, "orgInvestment");
            return (Criteria) this;
        }

        public Criteria andOrgInvestmentLike(String value) {
            addCriterion("ORG_INVESTMENT like", value, "orgInvestment");
            return (Criteria) this;
        }

        public Criteria andOrgInvestmentNotLike(String value) {
            addCriterion("ORG_INVESTMENT not like", value, "orgInvestment");
            return (Criteria) this;
        }

        public Criteria andOrgInvestmentIn(List<String> values) {
            addCriterion("ORG_INVESTMENT in", values, "orgInvestment");
            return (Criteria) this;
        }

        public Criteria andOrgInvestmentNotIn(List<String> values) {
            addCriterion("ORG_INVESTMENT not in", values, "orgInvestment");
            return (Criteria) this;
        }

        public Criteria andOrgInvestmentBetween(String value1, String value2) {
            addCriterion("ORG_INVESTMENT between", value1, value2, "orgInvestment");
            return (Criteria) this;
        }

        public Criteria andOrgInvestmentNotBetween(String value1, String value2) {
            addCriterion("ORG_INVESTMENT not between", value1, value2, "orgInvestment");
            return (Criteria) this;
        }

        public Criteria andStatusIdIsNull() {
            addCriterion("STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andStatusIdIsNotNull() {
            addCriterion("STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStatusIdEqualTo(String value) {
            addCriterion("STATUS_ID =", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotEqualTo(String value) {
            addCriterion("STATUS_ID <>", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdGreaterThan(String value) {
            addCriterion("STATUS_ID >", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS_ID >=", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLessThan(String value) {
            addCriterion("STATUS_ID <", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLessThanOrEqualTo(String value) {
            addCriterion("STATUS_ID <=", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLike(String value) {
            addCriterion("STATUS_ID like", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotLike(String value) {
            addCriterion("STATUS_ID not like", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdIn(List<String> values) {
            addCriterion("STATUS_ID in", values, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotIn(List<String> values) {
            addCriterion("STATUS_ID not in", values, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdBetween(String value1, String value2) {
            addCriterion("STATUS_ID between", value1, value2, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotBetween(String value1, String value2) {
            addCriterion("STATUS_ID not between", value1, value2, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusNameIsNull() {
            addCriterion("STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStatusNameIsNotNull() {
            addCriterion("STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStatusNameEqualTo(String value) {
            addCriterion("STATUS_NAME =", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameNotEqualTo(String value) {
            addCriterion("STATUS_NAME <>", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameGreaterThan(String value) {
            addCriterion("STATUS_NAME >", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS_NAME >=", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameLessThan(String value) {
            addCriterion("STATUS_NAME <", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameLessThanOrEqualTo(String value) {
            addCriterion("STATUS_NAME <=", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameLike(String value) {
            addCriterion("STATUS_NAME like", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameNotLike(String value) {
            addCriterion("STATUS_NAME not like", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameIn(List<String> values) {
            addCriterion("STATUS_NAME in", values, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameNotIn(List<String> values) {
            addCriterion("STATUS_NAME not in", values, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameBetween(String value1, String value2) {
            addCriterion("STATUS_NAME between", value1, value2, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameNotBetween(String value1, String value2) {
            addCriterion("STATUS_NAME not between", value1, value2, "statusName");
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

        public Criteria andProjSubCategoryIdIsNull() {
            addCriterion("PROJ_SUB_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryIdIsNotNull() {
            addCriterion("PROJ_SUB_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryIdEqualTo(String value) {
            addCriterion("PROJ_SUB_CATEGORY_ID =", value, "projSubCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryIdNotEqualTo(String value) {
            addCriterion("PROJ_SUB_CATEGORY_ID <>", value, "projSubCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryIdGreaterThan(String value) {
            addCriterion("PROJ_SUB_CATEGORY_ID >", value, "projSubCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_SUB_CATEGORY_ID >=", value, "projSubCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryIdLessThan(String value) {
            addCriterion("PROJ_SUB_CATEGORY_ID <", value, "projSubCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("PROJ_SUB_CATEGORY_ID <=", value, "projSubCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryIdLike(String value) {
            addCriterion("PROJ_SUB_CATEGORY_ID like", value, "projSubCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryIdNotLike(String value) {
            addCriterion("PROJ_SUB_CATEGORY_ID not like", value, "projSubCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryIdIn(List<String> values) {
            addCriterion("PROJ_SUB_CATEGORY_ID in", values, "projSubCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryIdNotIn(List<String> values) {
            addCriterion("PROJ_SUB_CATEGORY_ID not in", values, "projSubCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryIdBetween(String value1, String value2) {
            addCriterion("PROJ_SUB_CATEGORY_ID between", value1, value2, "projSubCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryIdNotBetween(String value1, String value2) {
            addCriterion("PROJ_SUB_CATEGORY_ID not between", value1, value2, "projSubCategoryId");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryNameIsNull() {
            addCriterion("PROJ_SUB_CATEGORY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryNameIsNotNull() {
            addCriterion("PROJ_SUB_CATEGORY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryNameEqualTo(String value) {
            addCriterion("PROJ_SUB_CATEGORY_NAME =", value, "projSubCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryNameNotEqualTo(String value) {
            addCriterion("PROJ_SUB_CATEGORY_NAME <>", value, "projSubCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryNameGreaterThan(String value) {
            addCriterion("PROJ_SUB_CATEGORY_NAME >", value, "projSubCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_SUB_CATEGORY_NAME >=", value, "projSubCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryNameLessThan(String value) {
            addCriterion("PROJ_SUB_CATEGORY_NAME <", value, "projSubCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("PROJ_SUB_CATEGORY_NAME <=", value, "projSubCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryNameLike(String value) {
            addCriterion("PROJ_SUB_CATEGORY_NAME like", value, "projSubCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryNameNotLike(String value) {
            addCriterion("PROJ_SUB_CATEGORY_NAME not like", value, "projSubCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryNameIn(List<String> values) {
            addCriterion("PROJ_SUB_CATEGORY_NAME in", values, "projSubCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryNameNotIn(List<String> values) {
            addCriterion("PROJ_SUB_CATEGORY_NAME not in", values, "projSubCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryNameBetween(String value1, String value2) {
            addCriterion("PROJ_SUB_CATEGORY_NAME between", value1, value2, "projSubCategoryName");
            return (Criteria) this;
        }

        public Criteria andProjSubCategoryNameNotBetween(String value1, String value2) {
            addCriterion("PROJ_SUB_CATEGORY_NAME not between", value1, value2, "projSubCategoryName");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowIsNull() {
            addCriterion("CHARGE_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowIsNotNull() {
            addCriterion("CHARGE_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowEqualTo(String value) {
            addCriterion("CHARGE_ORG_FLOW =", value, "chargeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowNotEqualTo(String value) {
            addCriterion("CHARGE_ORG_FLOW <>", value, "chargeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowGreaterThan(String value) {
            addCriterion("CHARGE_ORG_FLOW >", value, "chargeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CHARGE_ORG_FLOW >=", value, "chargeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowLessThan(String value) {
            addCriterion("CHARGE_ORG_FLOW <", value, "chargeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("CHARGE_ORG_FLOW <=", value, "chargeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowLike(String value) {
            addCriterion("CHARGE_ORG_FLOW like", value, "chargeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowNotLike(String value) {
            addCriterion("CHARGE_ORG_FLOW not like", value, "chargeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowIn(List<String> values) {
            addCriterion("CHARGE_ORG_FLOW in", values, "chargeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowNotIn(List<String> values) {
            addCriterion("CHARGE_ORG_FLOW not in", values, "chargeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowBetween(String value1, String value2) {
            addCriterion("CHARGE_ORG_FLOW between", value1, value2, "chargeOrgFlow");
            return (Criteria) this;
        }

        public Criteria andChargeOrgFlowNotBetween(String value1, String value2) {
            addCriterion("CHARGE_ORG_FLOW not between", value1, value2, "chargeOrgFlow");
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