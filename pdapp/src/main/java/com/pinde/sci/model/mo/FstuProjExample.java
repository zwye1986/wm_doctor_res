package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class FstuProjExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FstuProjExample() {
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

        public Criteria andProjLevelIdIsNull() {
            addCriterion("PROJ_LEVEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andProjLevelIdIsNotNull() {
            addCriterion("PROJ_LEVEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProjLevelIdEqualTo(String value) {
            addCriterion("PROJ_LEVEL_ID =", value, "projLevelId");
            return (Criteria) this;
        }

        public Criteria andProjLevelIdNotEqualTo(String value) {
            addCriterion("PROJ_LEVEL_ID <>", value, "projLevelId");
            return (Criteria) this;
        }

        public Criteria andProjLevelIdGreaterThan(String value) {
            addCriterion("PROJ_LEVEL_ID >", value, "projLevelId");
            return (Criteria) this;
        }

        public Criteria andProjLevelIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_LEVEL_ID >=", value, "projLevelId");
            return (Criteria) this;
        }

        public Criteria andProjLevelIdLessThan(String value) {
            addCriterion("PROJ_LEVEL_ID <", value, "projLevelId");
            return (Criteria) this;
        }

        public Criteria andProjLevelIdLessThanOrEqualTo(String value) {
            addCriterion("PROJ_LEVEL_ID <=", value, "projLevelId");
            return (Criteria) this;
        }

        public Criteria andProjLevelIdLike(String value) {
            addCriterion("PROJ_LEVEL_ID like", value, "projLevelId");
            return (Criteria) this;
        }

        public Criteria andProjLevelIdNotLike(String value) {
            addCriterion("PROJ_LEVEL_ID not like", value, "projLevelId");
            return (Criteria) this;
        }

        public Criteria andProjLevelIdIn(List<String> values) {
            addCriterion("PROJ_LEVEL_ID in", values, "projLevelId");
            return (Criteria) this;
        }

        public Criteria andProjLevelIdNotIn(List<String> values) {
            addCriterion("PROJ_LEVEL_ID not in", values, "projLevelId");
            return (Criteria) this;
        }

        public Criteria andProjLevelIdBetween(String value1, String value2) {
            addCriterion("PROJ_LEVEL_ID between", value1, value2, "projLevelId");
            return (Criteria) this;
        }

        public Criteria andProjLevelIdNotBetween(String value1, String value2) {
            addCriterion("PROJ_LEVEL_ID not between", value1, value2, "projLevelId");
            return (Criteria) this;
        }

        public Criteria andProjLevelNameIsNull() {
            addCriterion("PROJ_LEVEL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjLevelNameIsNotNull() {
            addCriterion("PROJ_LEVEL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjLevelNameEqualTo(String value) {
            addCriterion("PROJ_LEVEL_NAME =", value, "projLevelName");
            return (Criteria) this;
        }

        public Criteria andProjLevelNameNotEqualTo(String value) {
            addCriterion("PROJ_LEVEL_NAME <>", value, "projLevelName");
            return (Criteria) this;
        }

        public Criteria andProjLevelNameGreaterThan(String value) {
            addCriterion("PROJ_LEVEL_NAME >", value, "projLevelName");
            return (Criteria) this;
        }

        public Criteria andProjLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_LEVEL_NAME >=", value, "projLevelName");
            return (Criteria) this;
        }

        public Criteria andProjLevelNameLessThan(String value) {
            addCriterion("PROJ_LEVEL_NAME <", value, "projLevelName");
            return (Criteria) this;
        }

        public Criteria andProjLevelNameLessThanOrEqualTo(String value) {
            addCriterion("PROJ_LEVEL_NAME <=", value, "projLevelName");
            return (Criteria) this;
        }

        public Criteria andProjLevelNameLike(String value) {
            addCriterion("PROJ_LEVEL_NAME like", value, "projLevelName");
            return (Criteria) this;
        }

        public Criteria andProjLevelNameNotLike(String value) {
            addCriterion("PROJ_LEVEL_NAME not like", value, "projLevelName");
            return (Criteria) this;
        }

        public Criteria andProjLevelNameIn(List<String> values) {
            addCriterion("PROJ_LEVEL_NAME in", values, "projLevelName");
            return (Criteria) this;
        }

        public Criteria andProjLevelNameNotIn(List<String> values) {
            addCriterion("PROJ_LEVEL_NAME not in", values, "projLevelName");
            return (Criteria) this;
        }

        public Criteria andProjLevelNameBetween(String value1, String value2) {
            addCriterion("PROJ_LEVEL_NAME between", value1, value2, "projLevelName");
            return (Criteria) this;
        }

        public Criteria andProjLevelNameNotBetween(String value1, String value2) {
            addCriterion("PROJ_LEVEL_NAME not between", value1, value2, "projLevelName");
            return (Criteria) this;
        }

        public Criteria andProjSubjectIsNull() {
            addCriterion("PROJ_SUBJECT is null");
            return (Criteria) this;
        }

        public Criteria andProjSubjectIsNotNull() {
            addCriterion("PROJ_SUBJECT is not null");
            return (Criteria) this;
        }

        public Criteria andProjSubjectEqualTo(String value) {
            addCriterion("PROJ_SUBJECT =", value, "projSubject");
            return (Criteria) this;
        }

        public Criteria andProjSubjectNotEqualTo(String value) {
            addCriterion("PROJ_SUBJECT <>", value, "projSubject");
            return (Criteria) this;
        }

        public Criteria andProjSubjectGreaterThan(String value) {
            addCriterion("PROJ_SUBJECT >", value, "projSubject");
            return (Criteria) this;
        }

        public Criteria andProjSubjectGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_SUBJECT >=", value, "projSubject");
            return (Criteria) this;
        }

        public Criteria andProjSubjectLessThan(String value) {
            addCriterion("PROJ_SUBJECT <", value, "projSubject");
            return (Criteria) this;
        }

        public Criteria andProjSubjectLessThanOrEqualTo(String value) {
            addCriterion("PROJ_SUBJECT <=", value, "projSubject");
            return (Criteria) this;
        }

        public Criteria andProjSubjectLike(String value) {
            addCriterion("PROJ_SUBJECT like", value, "projSubject");
            return (Criteria) this;
        }

        public Criteria andProjSubjectNotLike(String value) {
            addCriterion("PROJ_SUBJECT not like", value, "projSubject");
            return (Criteria) this;
        }

        public Criteria andProjSubjectIn(List<String> values) {
            addCriterion("PROJ_SUBJECT in", values, "projSubject");
            return (Criteria) this;
        }

        public Criteria andProjSubjectNotIn(List<String> values) {
            addCriterion("PROJ_SUBJECT not in", values, "projSubject");
            return (Criteria) this;
        }

        public Criteria andProjSubjectBetween(String value1, String value2) {
            addCriterion("PROJ_SUBJECT between", value1, value2, "projSubject");
            return (Criteria) this;
        }

        public Criteria andProjSubjectNotBetween(String value1, String value2) {
            addCriterion("PROJ_SUBJECT not between", value1, value2, "projSubject");
            return (Criteria) this;
        }

        public Criteria andProjSubjectIdIsNull() {
            addCriterion("PROJ_SUBJECT_ID is null");
            return (Criteria) this;
        }

        public Criteria andProjSubjectIdIsNotNull() {
            addCriterion("PROJ_SUBJECT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProjSubjectIdEqualTo(String value) {
            addCriterion("PROJ_SUBJECT_ID =", value, "projSubjectId");
            return (Criteria) this;
        }

        public Criteria andProjSubjectIdNotEqualTo(String value) {
            addCriterion("PROJ_SUBJECT_ID <>", value, "projSubjectId");
            return (Criteria) this;
        }

        public Criteria andProjSubjectIdGreaterThan(String value) {
            addCriterion("PROJ_SUBJECT_ID >", value, "projSubjectId");
            return (Criteria) this;
        }

        public Criteria andProjSubjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_SUBJECT_ID >=", value, "projSubjectId");
            return (Criteria) this;
        }

        public Criteria andProjSubjectIdLessThan(String value) {
            addCriterion("PROJ_SUBJECT_ID <", value, "projSubjectId");
            return (Criteria) this;
        }

        public Criteria andProjSubjectIdLessThanOrEqualTo(String value) {
            addCriterion("PROJ_SUBJECT_ID <=", value, "projSubjectId");
            return (Criteria) this;
        }

        public Criteria andProjSubjectIdLike(String value) {
            addCriterion("PROJ_SUBJECT_ID like", value, "projSubjectId");
            return (Criteria) this;
        }

        public Criteria andProjSubjectIdNotLike(String value) {
            addCriterion("PROJ_SUBJECT_ID not like", value, "projSubjectId");
            return (Criteria) this;
        }

        public Criteria andProjSubjectIdIn(List<String> values) {
            addCriterion("PROJ_SUBJECT_ID in", values, "projSubjectId");
            return (Criteria) this;
        }

        public Criteria andProjSubjectIdNotIn(List<String> values) {
            addCriterion("PROJ_SUBJECT_ID not in", values, "projSubjectId");
            return (Criteria) this;
        }

        public Criteria andProjSubjectIdBetween(String value1, String value2) {
            addCriterion("PROJ_SUBJECT_ID between", value1, value2, "projSubjectId");
            return (Criteria) this;
        }

        public Criteria andProjSubjectIdNotBetween(String value1, String value2) {
            addCriterion("PROJ_SUBJECT_ID not between", value1, value2, "projSubjectId");
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

        public Criteria andProjPhoneIsNull() {
            addCriterion("PROJ_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andProjPhoneIsNotNull() {
            addCriterion("PROJ_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andProjPhoneEqualTo(String value) {
            addCriterion("PROJ_PHONE =", value, "projPhone");
            return (Criteria) this;
        }

        public Criteria andProjPhoneNotEqualTo(String value) {
            addCriterion("PROJ_PHONE <>", value, "projPhone");
            return (Criteria) this;
        }

        public Criteria andProjPhoneGreaterThan(String value) {
            addCriterion("PROJ_PHONE >", value, "projPhone");
            return (Criteria) this;
        }

        public Criteria andProjPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_PHONE >=", value, "projPhone");
            return (Criteria) this;
        }

        public Criteria andProjPhoneLessThan(String value) {
            addCriterion("PROJ_PHONE <", value, "projPhone");
            return (Criteria) this;
        }

        public Criteria andProjPhoneLessThanOrEqualTo(String value) {
            addCriterion("PROJ_PHONE <=", value, "projPhone");
            return (Criteria) this;
        }

        public Criteria andProjPhoneLike(String value) {
            addCriterion("PROJ_PHONE like", value, "projPhone");
            return (Criteria) this;
        }

        public Criteria andProjPhoneNotLike(String value) {
            addCriterion("PROJ_PHONE not like", value, "projPhone");
            return (Criteria) this;
        }

        public Criteria andProjPhoneIn(List<String> values) {
            addCriterion("PROJ_PHONE in", values, "projPhone");
            return (Criteria) this;
        }

        public Criteria andProjPhoneNotIn(List<String> values) {
            addCriterion("PROJ_PHONE not in", values, "projPhone");
            return (Criteria) this;
        }

        public Criteria andProjPhoneBetween(String value1, String value2) {
            addCriterion("PROJ_PHONE between", value1, value2, "projPhone");
            return (Criteria) this;
        }

        public Criteria andProjPhoneNotBetween(String value1, String value2) {
            addCriterion("PROJ_PHONE not between", value1, value2, "projPhone");
            return (Criteria) this;
        }

        public Criteria andProjHoldAddressIsNull() {
            addCriterion("PROJ_HOLD_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andProjHoldAddressIsNotNull() {
            addCriterion("PROJ_HOLD_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andProjHoldAddressEqualTo(String value) {
            addCriterion("PROJ_HOLD_ADDRESS =", value, "projHoldAddress");
            return (Criteria) this;
        }

        public Criteria andProjHoldAddressNotEqualTo(String value) {
            addCriterion("PROJ_HOLD_ADDRESS <>", value, "projHoldAddress");
            return (Criteria) this;
        }

        public Criteria andProjHoldAddressGreaterThan(String value) {
            addCriterion("PROJ_HOLD_ADDRESS >", value, "projHoldAddress");
            return (Criteria) this;
        }

        public Criteria andProjHoldAddressGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_HOLD_ADDRESS >=", value, "projHoldAddress");
            return (Criteria) this;
        }

        public Criteria andProjHoldAddressLessThan(String value) {
            addCriterion("PROJ_HOLD_ADDRESS <", value, "projHoldAddress");
            return (Criteria) this;
        }

        public Criteria andProjHoldAddressLessThanOrEqualTo(String value) {
            addCriterion("PROJ_HOLD_ADDRESS <=", value, "projHoldAddress");
            return (Criteria) this;
        }

        public Criteria andProjHoldAddressLike(String value) {
            addCriterion("PROJ_HOLD_ADDRESS like", value, "projHoldAddress");
            return (Criteria) this;
        }

        public Criteria andProjHoldAddressNotLike(String value) {
            addCriterion("PROJ_HOLD_ADDRESS not like", value, "projHoldAddress");
            return (Criteria) this;
        }

        public Criteria andProjHoldAddressIn(List<String> values) {
            addCriterion("PROJ_HOLD_ADDRESS in", values, "projHoldAddress");
            return (Criteria) this;
        }

        public Criteria andProjHoldAddressNotIn(List<String> values) {
            addCriterion("PROJ_HOLD_ADDRESS not in", values, "projHoldAddress");
            return (Criteria) this;
        }

        public Criteria andProjHoldAddressBetween(String value1, String value2) {
            addCriterion("PROJ_HOLD_ADDRESS between", value1, value2, "projHoldAddress");
            return (Criteria) this;
        }

        public Criteria andProjHoldAddressNotBetween(String value1, String value2) {
            addCriterion("PROJ_HOLD_ADDRESS not between", value1, value2, "projHoldAddress");
            return (Criteria) this;
        }

        public Criteria andApplyScoreIsNull() {
            addCriterion("APPLY_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andApplyScoreIsNotNull() {
            addCriterion("APPLY_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andApplyScoreEqualTo(String value) {
            addCriterion("APPLY_SCORE =", value, "applyScore");
            return (Criteria) this;
        }

        public Criteria andApplyScoreNotEqualTo(String value) {
            addCriterion("APPLY_SCORE <>", value, "applyScore");
            return (Criteria) this;
        }

        public Criteria andApplyScoreGreaterThan(String value) {
            addCriterion("APPLY_SCORE >", value, "applyScore");
            return (Criteria) this;
        }

        public Criteria andApplyScoreGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_SCORE >=", value, "applyScore");
            return (Criteria) this;
        }

        public Criteria andApplyScoreLessThan(String value) {
            addCriterion("APPLY_SCORE <", value, "applyScore");
            return (Criteria) this;
        }

        public Criteria andApplyScoreLessThanOrEqualTo(String value) {
            addCriterion("APPLY_SCORE <=", value, "applyScore");
            return (Criteria) this;
        }

        public Criteria andApplyScoreLike(String value) {
            addCriterion("APPLY_SCORE like", value, "applyScore");
            return (Criteria) this;
        }

        public Criteria andApplyScoreNotLike(String value) {
            addCriterion("APPLY_SCORE not like", value, "applyScore");
            return (Criteria) this;
        }

        public Criteria andApplyScoreIn(List<String> values) {
            addCriterion("APPLY_SCORE in", values, "applyScore");
            return (Criteria) this;
        }

        public Criteria andApplyScoreNotIn(List<String> values) {
            addCriterion("APPLY_SCORE not in", values, "applyScore");
            return (Criteria) this;
        }

        public Criteria andApplyScoreBetween(String value1, String value2) {
            addCriterion("APPLY_SCORE between", value1, value2, "applyScore");
            return (Criteria) this;
        }

        public Criteria andApplyScoreNotBetween(String value1, String value2) {
            addCriterion("APPLY_SCORE not between", value1, value2, "applyScore");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectIsNull() {
            addCriterion("TEACHING_OBJECT is null");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectIsNotNull() {
            addCriterion("TEACHING_OBJECT is not null");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectEqualTo(String value) {
            addCriterion("TEACHING_OBJECT =", value, "teachingObject");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectNotEqualTo(String value) {
            addCriterion("TEACHING_OBJECT <>", value, "teachingObject");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectGreaterThan(String value) {
            addCriterion("TEACHING_OBJECT >", value, "teachingObject");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHING_OBJECT >=", value, "teachingObject");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectLessThan(String value) {
            addCriterion("TEACHING_OBJECT <", value, "teachingObject");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectLessThanOrEqualTo(String value) {
            addCriterion("TEACHING_OBJECT <=", value, "teachingObject");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectLike(String value) {
            addCriterion("TEACHING_OBJECT like", value, "teachingObject");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectNotLike(String value) {
            addCriterion("TEACHING_OBJECT not like", value, "teachingObject");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectIn(List<String> values) {
            addCriterion("TEACHING_OBJECT in", values, "teachingObject");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectNotIn(List<String> values) {
            addCriterion("TEACHING_OBJECT not in", values, "teachingObject");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectBetween(String value1, String value2) {
            addCriterion("TEACHING_OBJECT between", value1, value2, "teachingObject");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectNotBetween(String value1, String value2) {
            addCriterion("TEACHING_OBJECT not between", value1, value2, "teachingObject");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectIdIsNull() {
            addCriterion("TEACHING_OBJECT_ID is null");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectIdIsNotNull() {
            addCriterion("TEACHING_OBJECT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectIdEqualTo(String value) {
            addCriterion("TEACHING_OBJECT_ID =", value, "teachingObjectId");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectIdNotEqualTo(String value) {
            addCriterion("TEACHING_OBJECT_ID <>", value, "teachingObjectId");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectIdGreaterThan(String value) {
            addCriterion("TEACHING_OBJECT_ID >", value, "teachingObjectId");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHING_OBJECT_ID >=", value, "teachingObjectId");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectIdLessThan(String value) {
            addCriterion("TEACHING_OBJECT_ID <", value, "teachingObjectId");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectIdLessThanOrEqualTo(String value) {
            addCriterion("TEACHING_OBJECT_ID <=", value, "teachingObjectId");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectIdLike(String value) {
            addCriterion("TEACHING_OBJECT_ID like", value, "teachingObjectId");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectIdNotLike(String value) {
            addCriterion("TEACHING_OBJECT_ID not like", value, "teachingObjectId");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectIdIn(List<String> values) {
            addCriterion("TEACHING_OBJECT_ID in", values, "teachingObjectId");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectIdNotIn(List<String> values) {
            addCriterion("TEACHING_OBJECT_ID not in", values, "teachingObjectId");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectIdBetween(String value1, String value2) {
            addCriterion("TEACHING_OBJECT_ID between", value1, value2, "teachingObjectId");
            return (Criteria) this;
        }

        public Criteria andTeachingObjectIdNotBetween(String value1, String value2) {
            addCriterion("TEACHING_OBJECT_ID not between", value1, value2, "teachingObjectId");
            return (Criteria) this;
        }

        public Criteria andRecruitNumIsNull() {
            addCriterion("RECRUIT_NUM is null");
            return (Criteria) this;
        }

        public Criteria andRecruitNumIsNotNull() {
            addCriterion("RECRUIT_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitNumEqualTo(Integer value) {
            addCriterion("RECRUIT_NUM =", value, "recruitNum");
            return (Criteria) this;
        }

        public Criteria andRecruitNumNotEqualTo(Integer value) {
            addCriterion("RECRUIT_NUM <>", value, "recruitNum");
            return (Criteria) this;
        }

        public Criteria andRecruitNumGreaterThan(Integer value) {
            addCriterion("RECRUIT_NUM >", value, "recruitNum");
            return (Criteria) this;
        }

        public Criteria andRecruitNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("RECRUIT_NUM >=", value, "recruitNum");
            return (Criteria) this;
        }

        public Criteria andRecruitNumLessThan(Integer value) {
            addCriterion("RECRUIT_NUM <", value, "recruitNum");
            return (Criteria) this;
        }

        public Criteria andRecruitNumLessThanOrEqualTo(Integer value) {
            addCriterion("RECRUIT_NUM <=", value, "recruitNum");
            return (Criteria) this;
        }

        public Criteria andRecruitNumIn(List<Integer> values) {
            addCriterion("RECRUIT_NUM in", values, "recruitNum");
            return (Criteria) this;
        }

        public Criteria andRecruitNumNotIn(List<Integer> values) {
            addCriterion("RECRUIT_NUM not in", values, "recruitNum");
            return (Criteria) this;
        }

        public Criteria andRecruitNumBetween(Integer value1, Integer value2) {
            addCriterion("RECRUIT_NUM between", value1, value2, "recruitNum");
            return (Criteria) this;
        }

        public Criteria andRecruitNumNotBetween(Integer value1, Integer value2) {
            addCriterion("RECRUIT_NUM not between", value1, value2, "recruitNum");
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

        public Criteria andIsclosedIsNull() {
            addCriterion("ISCLOSED is null");
            return (Criteria) this;
        }

        public Criteria andIsclosedIsNotNull() {
            addCriterion("ISCLOSED is not null");
            return (Criteria) this;
        }

        public Criteria andIsclosedEqualTo(String value) {
            addCriterion("ISCLOSED =", value, "isclosed");
            return (Criteria) this;
        }

        public Criteria andIsclosedNotEqualTo(String value) {
            addCriterion("ISCLOSED <>", value, "isclosed");
            return (Criteria) this;
        }

        public Criteria andIsclosedGreaterThan(String value) {
            addCriterion("ISCLOSED >", value, "isclosed");
            return (Criteria) this;
        }

        public Criteria andIsclosedGreaterThanOrEqualTo(String value) {
            addCriterion("ISCLOSED >=", value, "isclosed");
            return (Criteria) this;
        }

        public Criteria andIsclosedLessThan(String value) {
            addCriterion("ISCLOSED <", value, "isclosed");
            return (Criteria) this;
        }

        public Criteria andIsclosedLessThanOrEqualTo(String value) {
            addCriterion("ISCLOSED <=", value, "isclosed");
            return (Criteria) this;
        }

        public Criteria andIsclosedLike(String value) {
            addCriterion("ISCLOSED like", value, "isclosed");
            return (Criteria) this;
        }

        public Criteria andIsclosedNotLike(String value) {
            addCriterion("ISCLOSED not like", value, "isclosed");
            return (Criteria) this;
        }

        public Criteria andIsclosedIn(List<String> values) {
            addCriterion("ISCLOSED in", values, "isclosed");
            return (Criteria) this;
        }

        public Criteria andIsclosedNotIn(List<String> values) {
            addCriterion("ISCLOSED not in", values, "isclosed");
            return (Criteria) this;
        }

        public Criteria andIsclosedBetween(String value1, String value2) {
            addCriterion("ISCLOSED between", value1, value2, "isclosed");
            return (Criteria) this;
        }

        public Criteria andIsclosedNotBetween(String value1, String value2) {
            addCriterion("ISCLOSED not between", value1, value2, "isclosed");
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

        public Criteria andProjEmailIsNull() {
            addCriterion("PROJ_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andProjEmailIsNotNull() {
            addCriterion("PROJ_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andProjEmailEqualTo(String value) {
            addCriterion("PROJ_EMAIL =", value, "projEmail");
            return (Criteria) this;
        }

        public Criteria andProjEmailNotEqualTo(String value) {
            addCriterion("PROJ_EMAIL <>", value, "projEmail");
            return (Criteria) this;
        }

        public Criteria andProjEmailGreaterThan(String value) {
            addCriterion("PROJ_EMAIL >", value, "projEmail");
            return (Criteria) this;
        }

        public Criteria andProjEmailGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_EMAIL >=", value, "projEmail");
            return (Criteria) this;
        }

        public Criteria andProjEmailLessThan(String value) {
            addCriterion("PROJ_EMAIL <", value, "projEmail");
            return (Criteria) this;
        }

        public Criteria andProjEmailLessThanOrEqualTo(String value) {
            addCriterion("PROJ_EMAIL <=", value, "projEmail");
            return (Criteria) this;
        }

        public Criteria andProjEmailLike(String value) {
            addCriterion("PROJ_EMAIL like", value, "projEmail");
            return (Criteria) this;
        }

        public Criteria andProjEmailNotLike(String value) {
            addCriterion("PROJ_EMAIL not like", value, "projEmail");
            return (Criteria) this;
        }

        public Criteria andProjEmailIn(List<String> values) {
            addCriterion("PROJ_EMAIL in", values, "projEmail");
            return (Criteria) this;
        }

        public Criteria andProjEmailNotIn(List<String> values) {
            addCriterion("PROJ_EMAIL not in", values, "projEmail");
            return (Criteria) this;
        }

        public Criteria andProjEmailBetween(String value1, String value2) {
            addCriterion("PROJ_EMAIL between", value1, value2, "projEmail");
            return (Criteria) this;
        }

        public Criteria andProjEmailNotBetween(String value1, String value2) {
            addCriterion("PROJ_EMAIL not between", value1, value2, "projEmail");
            return (Criteria) this;
        }

        public Criteria andAttenNameIsNull() {
            addCriterion("ATTEN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAttenNameIsNotNull() {
            addCriterion("ATTEN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAttenNameEqualTo(String value) {
            addCriterion("ATTEN_NAME =", value, "attenName");
            return (Criteria) this;
        }

        public Criteria andAttenNameNotEqualTo(String value) {
            addCriterion("ATTEN_NAME <>", value, "attenName");
            return (Criteria) this;
        }

        public Criteria andAttenNameGreaterThan(String value) {
            addCriterion("ATTEN_NAME >", value, "attenName");
            return (Criteria) this;
        }

        public Criteria andAttenNameGreaterThanOrEqualTo(String value) {
            addCriterion("ATTEN_NAME >=", value, "attenName");
            return (Criteria) this;
        }

        public Criteria andAttenNameLessThan(String value) {
            addCriterion("ATTEN_NAME <", value, "attenName");
            return (Criteria) this;
        }

        public Criteria andAttenNameLessThanOrEqualTo(String value) {
            addCriterion("ATTEN_NAME <=", value, "attenName");
            return (Criteria) this;
        }

        public Criteria andAttenNameLike(String value) {
            addCriterion("ATTEN_NAME like", value, "attenName");
            return (Criteria) this;
        }

        public Criteria andAttenNameNotLike(String value) {
            addCriterion("ATTEN_NAME not like", value, "attenName");
            return (Criteria) this;
        }

        public Criteria andAttenNameIn(List<String> values) {
            addCriterion("ATTEN_NAME in", values, "attenName");
            return (Criteria) this;
        }

        public Criteria andAttenNameNotIn(List<String> values) {
            addCriterion("ATTEN_NAME not in", values, "attenName");
            return (Criteria) this;
        }

        public Criteria andAttenNameBetween(String value1, String value2) {
            addCriterion("ATTEN_NAME between", value1, value2, "attenName");
            return (Criteria) this;
        }

        public Criteria andAttenNameNotBetween(String value1, String value2) {
            addCriterion("ATTEN_NAME not between", value1, value2, "attenName");
            return (Criteria) this;
        }

        public Criteria andAttenPhoneIsNull() {
            addCriterion("ATTEN_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andAttenPhoneIsNotNull() {
            addCriterion("ATTEN_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andAttenPhoneEqualTo(String value) {
            addCriterion("ATTEN_PHONE =", value, "attenPhone");
            return (Criteria) this;
        }

        public Criteria andAttenPhoneNotEqualTo(String value) {
            addCriterion("ATTEN_PHONE <>", value, "attenPhone");
            return (Criteria) this;
        }

        public Criteria andAttenPhoneGreaterThan(String value) {
            addCriterion("ATTEN_PHONE >", value, "attenPhone");
            return (Criteria) this;
        }

        public Criteria andAttenPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("ATTEN_PHONE >=", value, "attenPhone");
            return (Criteria) this;
        }

        public Criteria andAttenPhoneLessThan(String value) {
            addCriterion("ATTEN_PHONE <", value, "attenPhone");
            return (Criteria) this;
        }

        public Criteria andAttenPhoneLessThanOrEqualTo(String value) {
            addCriterion("ATTEN_PHONE <=", value, "attenPhone");
            return (Criteria) this;
        }

        public Criteria andAttenPhoneLike(String value) {
            addCriterion("ATTEN_PHONE like", value, "attenPhone");
            return (Criteria) this;
        }

        public Criteria andAttenPhoneNotLike(String value) {
            addCriterion("ATTEN_PHONE not like", value, "attenPhone");
            return (Criteria) this;
        }

        public Criteria andAttenPhoneIn(List<String> values) {
            addCriterion("ATTEN_PHONE in", values, "attenPhone");
            return (Criteria) this;
        }

        public Criteria andAttenPhoneNotIn(List<String> values) {
            addCriterion("ATTEN_PHONE not in", values, "attenPhone");
            return (Criteria) this;
        }

        public Criteria andAttenPhoneBetween(String value1, String value2) {
            addCriterion("ATTEN_PHONE between", value1, value2, "attenPhone");
            return (Criteria) this;
        }

        public Criteria andAttenPhoneNotBetween(String value1, String value2) {
            addCriterion("ATTEN_PHONE not between", value1, value2, "attenPhone");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeIdIsNull() {
            addCriterion("ACASCORE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeIdIsNotNull() {
            addCriterion("ACASCORE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeIdEqualTo(String value) {
            addCriterion("ACASCORE_TYPE_ID =", value, "acascoreTypeId");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeIdNotEqualTo(String value) {
            addCriterion("ACASCORE_TYPE_ID <>", value, "acascoreTypeId");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeIdGreaterThan(String value) {
            addCriterion("ACASCORE_TYPE_ID >", value, "acascoreTypeId");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("ACASCORE_TYPE_ID >=", value, "acascoreTypeId");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeIdLessThan(String value) {
            addCriterion("ACASCORE_TYPE_ID <", value, "acascoreTypeId");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeIdLessThanOrEqualTo(String value) {
            addCriterion("ACASCORE_TYPE_ID <=", value, "acascoreTypeId");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeIdLike(String value) {
            addCriterion("ACASCORE_TYPE_ID like", value, "acascoreTypeId");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeIdNotLike(String value) {
            addCriterion("ACASCORE_TYPE_ID not like", value, "acascoreTypeId");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeIdIn(List<String> values) {
            addCriterion("ACASCORE_TYPE_ID in", values, "acascoreTypeId");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeIdNotIn(List<String> values) {
            addCriterion("ACASCORE_TYPE_ID not in", values, "acascoreTypeId");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeIdBetween(String value1, String value2) {
            addCriterion("ACASCORE_TYPE_ID between", value1, value2, "acascoreTypeId");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeIdNotBetween(String value1, String value2) {
            addCriterion("ACASCORE_TYPE_ID not between", value1, value2, "acascoreTypeId");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeNameIsNull() {
            addCriterion("ACASCORE_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeNameIsNotNull() {
            addCriterion("ACASCORE_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeNameEqualTo(String value) {
            addCriterion("ACASCORE_TYPE_NAME =", value, "acascoreTypeName");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeNameNotEqualTo(String value) {
            addCriterion("ACASCORE_TYPE_NAME <>", value, "acascoreTypeName");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeNameGreaterThan(String value) {
            addCriterion("ACASCORE_TYPE_NAME >", value, "acascoreTypeName");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACASCORE_TYPE_NAME >=", value, "acascoreTypeName");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeNameLessThan(String value) {
            addCriterion("ACASCORE_TYPE_NAME <", value, "acascoreTypeName");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeNameLessThanOrEqualTo(String value) {
            addCriterion("ACASCORE_TYPE_NAME <=", value, "acascoreTypeName");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeNameLike(String value) {
            addCriterion("ACASCORE_TYPE_NAME like", value, "acascoreTypeName");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeNameNotLike(String value) {
            addCriterion("ACASCORE_TYPE_NAME not like", value, "acascoreTypeName");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeNameIn(List<String> values) {
            addCriterion("ACASCORE_TYPE_NAME in", values, "acascoreTypeName");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeNameNotIn(List<String> values) {
            addCriterion("ACASCORE_TYPE_NAME not in", values, "acascoreTypeName");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeNameBetween(String value1, String value2) {
            addCriterion("ACASCORE_TYPE_NAME between", value1, value2, "acascoreTypeName");
            return (Criteria) this;
        }

        public Criteria andAcascoreTypeNameNotBetween(String value1, String value2) {
            addCriterion("ACASCORE_TYPE_NAME not between", value1, value2, "acascoreTypeName");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgFlowIsNull() {
            addCriterion("ACCEPT_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgFlowIsNotNull() {
            addCriterion("ACCEPT_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgFlowEqualTo(String value) {
            addCriterion("ACCEPT_ORG_FLOW =", value, "acceptOrgFlow");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgFlowNotEqualTo(String value) {
            addCriterion("ACCEPT_ORG_FLOW <>", value, "acceptOrgFlow");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgFlowGreaterThan(String value) {
            addCriterion("ACCEPT_ORG_FLOW >", value, "acceptOrgFlow");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ACCEPT_ORG_FLOW >=", value, "acceptOrgFlow");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgFlowLessThan(String value) {
            addCriterion("ACCEPT_ORG_FLOW <", value, "acceptOrgFlow");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("ACCEPT_ORG_FLOW <=", value, "acceptOrgFlow");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgFlowLike(String value) {
            addCriterion("ACCEPT_ORG_FLOW like", value, "acceptOrgFlow");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgFlowNotLike(String value) {
            addCriterion("ACCEPT_ORG_FLOW not like", value, "acceptOrgFlow");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgFlowIn(List<String> values) {
            addCriterion("ACCEPT_ORG_FLOW in", values, "acceptOrgFlow");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgFlowNotIn(List<String> values) {
            addCriterion("ACCEPT_ORG_FLOW not in", values, "acceptOrgFlow");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgFlowBetween(String value1, String value2) {
            addCriterion("ACCEPT_ORG_FLOW between", value1, value2, "acceptOrgFlow");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgFlowNotBetween(String value1, String value2) {
            addCriterion("ACCEPT_ORG_FLOW not between", value1, value2, "acceptOrgFlow");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgNameIsNull() {
            addCriterion("ACCEPT_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgNameIsNotNull() {
            addCriterion("ACCEPT_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgNameEqualTo(String value) {
            addCriterion("ACCEPT_ORG_NAME =", value, "acceptOrgName");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgNameNotEqualTo(String value) {
            addCriterion("ACCEPT_ORG_NAME <>", value, "acceptOrgName");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgNameGreaterThan(String value) {
            addCriterion("ACCEPT_ORG_NAME >", value, "acceptOrgName");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACCEPT_ORG_NAME >=", value, "acceptOrgName");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgNameLessThan(String value) {
            addCriterion("ACCEPT_ORG_NAME <", value, "acceptOrgName");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgNameLessThanOrEqualTo(String value) {
            addCriterion("ACCEPT_ORG_NAME <=", value, "acceptOrgName");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgNameLike(String value) {
            addCriterion("ACCEPT_ORG_NAME like", value, "acceptOrgName");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgNameNotLike(String value) {
            addCriterion("ACCEPT_ORG_NAME not like", value, "acceptOrgName");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgNameIn(List<String> values) {
            addCriterion("ACCEPT_ORG_NAME in", values, "acceptOrgName");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgNameNotIn(List<String> values) {
            addCriterion("ACCEPT_ORG_NAME not in", values, "acceptOrgName");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgNameBetween(String value1, String value2) {
            addCriterion("ACCEPT_ORG_NAME between", value1, value2, "acceptOrgName");
            return (Criteria) this;
        }

        public Criteria andAcceptOrgNameNotBetween(String value1, String value2) {
            addCriterion("ACCEPT_ORG_NAME not between", value1, value2, "acceptOrgName");
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

        public Criteria andPracticeModeIsNull() {
            addCriterion("PRACTICE_MODE is null");
            return (Criteria) this;
        }

        public Criteria andPracticeModeIsNotNull() {
            addCriterion("PRACTICE_MODE is not null");
            return (Criteria) this;
        }

        public Criteria andPracticeModeEqualTo(String value) {
            addCriterion("PRACTICE_MODE =", value, "practiceMode");
            return (Criteria) this;
        }

        public Criteria andPracticeModeNotEqualTo(String value) {
            addCriterion("PRACTICE_MODE <>", value, "practiceMode");
            return (Criteria) this;
        }

        public Criteria andPracticeModeGreaterThan(String value) {
            addCriterion("PRACTICE_MODE >", value, "practiceMode");
            return (Criteria) this;
        }

        public Criteria andPracticeModeGreaterThanOrEqualTo(String value) {
            addCriterion("PRACTICE_MODE >=", value, "practiceMode");
            return (Criteria) this;
        }

        public Criteria andPracticeModeLessThan(String value) {
            addCriterion("PRACTICE_MODE <", value, "practiceMode");
            return (Criteria) this;
        }

        public Criteria andPracticeModeLessThanOrEqualTo(String value) {
            addCriterion("PRACTICE_MODE <=", value, "practiceMode");
            return (Criteria) this;
        }

        public Criteria andPracticeModeLike(String value) {
            addCriterion("PRACTICE_MODE like", value, "practiceMode");
            return (Criteria) this;
        }

        public Criteria andPracticeModeNotLike(String value) {
            addCriterion("PRACTICE_MODE not like", value, "practiceMode");
            return (Criteria) this;
        }

        public Criteria andPracticeModeIn(List<String> values) {
            addCriterion("PRACTICE_MODE in", values, "practiceMode");
            return (Criteria) this;
        }

        public Criteria andPracticeModeNotIn(List<String> values) {
            addCriterion("PRACTICE_MODE not in", values, "practiceMode");
            return (Criteria) this;
        }

        public Criteria andPracticeModeBetween(String value1, String value2) {
            addCriterion("PRACTICE_MODE between", value1, value2, "practiceMode");
            return (Criteria) this;
        }

        public Criteria andPracticeModeNotBetween(String value1, String value2) {
            addCriterion("PRACTICE_MODE not between", value1, value2, "practiceMode");
            return (Criteria) this;
        }

        public Criteria andDeclareResultIsNull() {
            addCriterion("DECLARE_RESULT is null");
            return (Criteria) this;
        }

        public Criteria andDeclareResultIsNotNull() {
            addCriterion("DECLARE_RESULT is not null");
            return (Criteria) this;
        }

        public Criteria andDeclareResultEqualTo(String value) {
            addCriterion("DECLARE_RESULT =", value, "declareResult");
            return (Criteria) this;
        }

        public Criteria andDeclareResultNotEqualTo(String value) {
            addCriterion("DECLARE_RESULT <>", value, "declareResult");
            return (Criteria) this;
        }

        public Criteria andDeclareResultGreaterThan(String value) {
            addCriterion("DECLARE_RESULT >", value, "declareResult");
            return (Criteria) this;
        }

        public Criteria andDeclareResultGreaterThanOrEqualTo(String value) {
            addCriterion("DECLARE_RESULT >=", value, "declareResult");
            return (Criteria) this;
        }

        public Criteria andDeclareResultLessThan(String value) {
            addCriterion("DECLARE_RESULT <", value, "declareResult");
            return (Criteria) this;
        }

        public Criteria andDeclareResultLessThanOrEqualTo(String value) {
            addCriterion("DECLARE_RESULT <=", value, "declareResult");
            return (Criteria) this;
        }

        public Criteria andDeclareResultLike(String value) {
            addCriterion("DECLARE_RESULT like", value, "declareResult");
            return (Criteria) this;
        }

        public Criteria andDeclareResultNotLike(String value) {
            addCriterion("DECLARE_RESULT not like", value, "declareResult");
            return (Criteria) this;
        }

        public Criteria andDeclareResultIn(List<String> values) {
            addCriterion("DECLARE_RESULT in", values, "declareResult");
            return (Criteria) this;
        }

        public Criteria andDeclareResultNotIn(List<String> values) {
            addCriterion("DECLARE_RESULT not in", values, "declareResult");
            return (Criteria) this;
        }

        public Criteria andDeclareResultBetween(String value1, String value2) {
            addCriterion("DECLARE_RESULT between", value1, value2, "declareResult");
            return (Criteria) this;
        }

        public Criteria andDeclareResultNotBetween(String value1, String value2) {
            addCriterion("DECLARE_RESULT not between", value1, value2, "declareResult");
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