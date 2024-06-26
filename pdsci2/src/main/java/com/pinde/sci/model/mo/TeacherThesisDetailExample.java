package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class TeacherThesisDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TeacherThesisDetailExample() {
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

        public Criteria andRecordFlowIsNull() {
            addCriterion("RECORD_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRecordFlowIsNotNull() {
            addCriterion("RECORD_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRecordFlowEqualTo(String value) {
            addCriterion("RECORD_FLOW =", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowNotEqualTo(String value) {
            addCriterion("RECORD_FLOW <>", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowGreaterThan(String value) {
            addCriterion("RECORD_FLOW >", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RECORD_FLOW >=", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowLessThan(String value) {
            addCriterion("RECORD_FLOW <", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowLessThanOrEqualTo(String value) {
            addCriterion("RECORD_FLOW <=", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowLike(String value) {
            addCriterion("RECORD_FLOW like", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowNotLike(String value) {
            addCriterion("RECORD_FLOW not like", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowIn(List<String> values) {
            addCriterion("RECORD_FLOW in", values, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowNotIn(List<String> values) {
            addCriterion("RECORD_FLOW not in", values, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowBetween(String value1, String value2) {
            addCriterion("RECORD_FLOW between", value1, value2, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowNotBetween(String value1, String value2) {
            addCriterion("RECORD_FLOW not between", value1, value2, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowIsNull() {
            addCriterion("APPLY_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApplyFlowIsNotNull() {
            addCriterion("APPLY_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApplyFlowEqualTo(String value) {
            addCriterion("APPLY_FLOW =", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowNotEqualTo(String value) {
            addCriterion("APPLY_FLOW <>", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowGreaterThan(String value) {
            addCriterion("APPLY_FLOW >", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_FLOW >=", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowLessThan(String value) {
            addCriterion("APPLY_FLOW <", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowLessThanOrEqualTo(String value) {
            addCriterion("APPLY_FLOW <=", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowLike(String value) {
            addCriterion("APPLY_FLOW like", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowNotLike(String value) {
            addCriterion("APPLY_FLOW not like", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowIn(List<String> values) {
            addCriterion("APPLY_FLOW in", values, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowNotIn(List<String> values) {
            addCriterion("APPLY_FLOW not in", values, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowBetween(String value1, String value2) {
            addCriterion("APPLY_FLOW between", value1, value2, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowNotBetween(String value1, String value2) {
            addCriterion("APPLY_FLOW not between", value1, value2, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andThesisTypeIdIsNull() {
            addCriterion("THESIS_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andThesisTypeIdIsNotNull() {
            addCriterion("THESIS_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andThesisTypeIdEqualTo(String value) {
            addCriterion("THESIS_TYPE_ID =", value, "thesisTypeId");
            return (Criteria) this;
        }

        public Criteria andThesisTypeIdNotEqualTo(String value) {
            addCriterion("THESIS_TYPE_ID <>", value, "thesisTypeId");
            return (Criteria) this;
        }

        public Criteria andThesisTypeIdGreaterThan(String value) {
            addCriterion("THESIS_TYPE_ID >", value, "thesisTypeId");
            return (Criteria) this;
        }

        public Criteria andThesisTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("THESIS_TYPE_ID >=", value, "thesisTypeId");
            return (Criteria) this;
        }

        public Criteria andThesisTypeIdLessThan(String value) {
            addCriterion("THESIS_TYPE_ID <", value, "thesisTypeId");
            return (Criteria) this;
        }

        public Criteria andThesisTypeIdLessThanOrEqualTo(String value) {
            addCriterion("THESIS_TYPE_ID <=", value, "thesisTypeId");
            return (Criteria) this;
        }

        public Criteria andThesisTypeIdLike(String value) {
            addCriterion("THESIS_TYPE_ID like", value, "thesisTypeId");
            return (Criteria) this;
        }

        public Criteria andThesisTypeIdNotLike(String value) {
            addCriterion("THESIS_TYPE_ID not like", value, "thesisTypeId");
            return (Criteria) this;
        }

        public Criteria andThesisTypeIdIn(List<String> values) {
            addCriterion("THESIS_TYPE_ID in", values, "thesisTypeId");
            return (Criteria) this;
        }

        public Criteria andThesisTypeIdNotIn(List<String> values) {
            addCriterion("THESIS_TYPE_ID not in", values, "thesisTypeId");
            return (Criteria) this;
        }

        public Criteria andThesisTypeIdBetween(String value1, String value2) {
            addCriterion("THESIS_TYPE_ID between", value1, value2, "thesisTypeId");
            return (Criteria) this;
        }

        public Criteria andThesisTypeIdNotBetween(String value1, String value2) {
            addCriterion("THESIS_TYPE_ID not between", value1, value2, "thesisTypeId");
            return (Criteria) this;
        }

        public Criteria andThesisTypeNameIsNull() {
            addCriterion("THESIS_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andThesisTypeNameIsNotNull() {
            addCriterion("THESIS_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andThesisTypeNameEqualTo(String value) {
            addCriterion("THESIS_TYPE_NAME =", value, "thesisTypeName");
            return (Criteria) this;
        }

        public Criteria andThesisTypeNameNotEqualTo(String value) {
            addCriterion("THESIS_TYPE_NAME <>", value, "thesisTypeName");
            return (Criteria) this;
        }

        public Criteria andThesisTypeNameGreaterThan(String value) {
            addCriterion("THESIS_TYPE_NAME >", value, "thesisTypeName");
            return (Criteria) this;
        }

        public Criteria andThesisTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("THESIS_TYPE_NAME >=", value, "thesisTypeName");
            return (Criteria) this;
        }

        public Criteria andThesisTypeNameLessThan(String value) {
            addCriterion("THESIS_TYPE_NAME <", value, "thesisTypeName");
            return (Criteria) this;
        }

        public Criteria andThesisTypeNameLessThanOrEqualTo(String value) {
            addCriterion("THESIS_TYPE_NAME <=", value, "thesisTypeName");
            return (Criteria) this;
        }

        public Criteria andThesisTypeNameLike(String value) {
            addCriterion("THESIS_TYPE_NAME like", value, "thesisTypeName");
            return (Criteria) this;
        }

        public Criteria andThesisTypeNameNotLike(String value) {
            addCriterion("THESIS_TYPE_NAME not like", value, "thesisTypeName");
            return (Criteria) this;
        }

        public Criteria andThesisTypeNameIn(List<String> values) {
            addCriterion("THESIS_TYPE_NAME in", values, "thesisTypeName");
            return (Criteria) this;
        }

        public Criteria andThesisTypeNameNotIn(List<String> values) {
            addCriterion("THESIS_TYPE_NAME not in", values, "thesisTypeName");
            return (Criteria) this;
        }

        public Criteria andThesisTypeNameBetween(String value1, String value2) {
            addCriterion("THESIS_TYPE_NAME between", value1, value2, "thesisTypeName");
            return (Criteria) this;
        }

        public Criteria andThesisTypeNameNotBetween(String value1, String value2) {
            addCriterion("THESIS_TYPE_NAME not between", value1, value2, "thesisTypeName");
            return (Criteria) this;
        }

        public Criteria andThesisNameIsNull() {
            addCriterion("THESIS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andThesisNameIsNotNull() {
            addCriterion("THESIS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andThesisNameEqualTo(String value) {
            addCriterion("THESIS_NAME =", value, "thesisName");
            return (Criteria) this;
        }

        public Criteria andThesisNameNotEqualTo(String value) {
            addCriterion("THESIS_NAME <>", value, "thesisName");
            return (Criteria) this;
        }

        public Criteria andThesisNameGreaterThan(String value) {
            addCriterion("THESIS_NAME >", value, "thesisName");
            return (Criteria) this;
        }

        public Criteria andThesisNameGreaterThanOrEqualTo(String value) {
            addCriterion("THESIS_NAME >=", value, "thesisName");
            return (Criteria) this;
        }

        public Criteria andThesisNameLessThan(String value) {
            addCriterion("THESIS_NAME <", value, "thesisName");
            return (Criteria) this;
        }

        public Criteria andThesisNameLessThanOrEqualTo(String value) {
            addCriterion("THESIS_NAME <=", value, "thesisName");
            return (Criteria) this;
        }

        public Criteria andThesisNameLike(String value) {
            addCriterion("THESIS_NAME like", value, "thesisName");
            return (Criteria) this;
        }

        public Criteria andThesisNameNotLike(String value) {
            addCriterion("THESIS_NAME not like", value, "thesisName");
            return (Criteria) this;
        }

        public Criteria andThesisNameIn(List<String> values) {
            addCriterion("THESIS_NAME in", values, "thesisName");
            return (Criteria) this;
        }

        public Criteria andThesisNameNotIn(List<String> values) {
            addCriterion("THESIS_NAME not in", values, "thesisName");
            return (Criteria) this;
        }

        public Criteria andThesisNameBetween(String value1, String value2) {
            addCriterion("THESIS_NAME between", value1, value2, "thesisName");
            return (Criteria) this;
        }

        public Criteria andThesisNameNotBetween(String value1, String value2) {
            addCriterion("THESIS_NAME not between", value1, value2, "thesisName");
            return (Criteria) this;
        }

        public Criteria andThesisTitleIsNull() {
            addCriterion("THESIS_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andThesisTitleIsNotNull() {
            addCriterion("THESIS_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andThesisTitleEqualTo(String value) {
            addCriterion("THESIS_TITLE =", value, "thesisTitle");
            return (Criteria) this;
        }

        public Criteria andThesisTitleNotEqualTo(String value) {
            addCriterion("THESIS_TITLE <>", value, "thesisTitle");
            return (Criteria) this;
        }

        public Criteria andThesisTitleGreaterThan(String value) {
            addCriterion("THESIS_TITLE >", value, "thesisTitle");
            return (Criteria) this;
        }

        public Criteria andThesisTitleGreaterThanOrEqualTo(String value) {
            addCriterion("THESIS_TITLE >=", value, "thesisTitle");
            return (Criteria) this;
        }

        public Criteria andThesisTitleLessThan(String value) {
            addCriterion("THESIS_TITLE <", value, "thesisTitle");
            return (Criteria) this;
        }

        public Criteria andThesisTitleLessThanOrEqualTo(String value) {
            addCriterion("THESIS_TITLE <=", value, "thesisTitle");
            return (Criteria) this;
        }

        public Criteria andThesisTitleLike(String value) {
            addCriterion("THESIS_TITLE like", value, "thesisTitle");
            return (Criteria) this;
        }

        public Criteria andThesisTitleNotLike(String value) {
            addCriterion("THESIS_TITLE not like", value, "thesisTitle");
            return (Criteria) this;
        }

        public Criteria andThesisTitleIn(List<String> values) {
            addCriterion("THESIS_TITLE in", values, "thesisTitle");
            return (Criteria) this;
        }

        public Criteria andThesisTitleNotIn(List<String> values) {
            addCriterion("THESIS_TITLE not in", values, "thesisTitle");
            return (Criteria) this;
        }

        public Criteria andThesisTitleBetween(String value1, String value2) {
            addCriterion("THESIS_TITLE between", value1, value2, "thesisTitle");
            return (Criteria) this;
        }

        public Criteria andThesisTitleNotBetween(String value1, String value2) {
            addCriterion("THESIS_TITLE not between", value1, value2, "thesisTitle");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingIdIsNull() {
            addCriterion("AUTHOR_RANKING_ID is null");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingIdIsNotNull() {
            addCriterion("AUTHOR_RANKING_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingIdEqualTo(String value) {
            addCriterion("AUTHOR_RANKING_ID =", value, "authorRankingId");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingIdNotEqualTo(String value) {
            addCriterion("AUTHOR_RANKING_ID <>", value, "authorRankingId");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingIdGreaterThan(String value) {
            addCriterion("AUTHOR_RANKING_ID >", value, "authorRankingId");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingIdGreaterThanOrEqualTo(String value) {
            addCriterion("AUTHOR_RANKING_ID >=", value, "authorRankingId");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingIdLessThan(String value) {
            addCriterion("AUTHOR_RANKING_ID <", value, "authorRankingId");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingIdLessThanOrEqualTo(String value) {
            addCriterion("AUTHOR_RANKING_ID <=", value, "authorRankingId");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingIdLike(String value) {
            addCriterion("AUTHOR_RANKING_ID like", value, "authorRankingId");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingIdNotLike(String value) {
            addCriterion("AUTHOR_RANKING_ID not like", value, "authorRankingId");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingIdIn(List<String> values) {
            addCriterion("AUTHOR_RANKING_ID in", values, "authorRankingId");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingIdNotIn(List<String> values) {
            addCriterion("AUTHOR_RANKING_ID not in", values, "authorRankingId");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingIdBetween(String value1, String value2) {
            addCriterion("AUTHOR_RANKING_ID between", value1, value2, "authorRankingId");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingIdNotBetween(String value1, String value2) {
            addCriterion("AUTHOR_RANKING_ID not between", value1, value2, "authorRankingId");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingNameIsNull() {
            addCriterion("AUTHOR_RANKING_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingNameIsNotNull() {
            addCriterion("AUTHOR_RANKING_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingNameEqualTo(String value) {
            addCriterion("AUTHOR_RANKING_NAME =", value, "authorRankingName");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingNameNotEqualTo(String value) {
            addCriterion("AUTHOR_RANKING_NAME <>", value, "authorRankingName");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingNameGreaterThan(String value) {
            addCriterion("AUTHOR_RANKING_NAME >", value, "authorRankingName");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingNameGreaterThanOrEqualTo(String value) {
            addCriterion("AUTHOR_RANKING_NAME >=", value, "authorRankingName");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingNameLessThan(String value) {
            addCriterion("AUTHOR_RANKING_NAME <", value, "authorRankingName");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingNameLessThanOrEqualTo(String value) {
            addCriterion("AUTHOR_RANKING_NAME <=", value, "authorRankingName");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingNameLike(String value) {
            addCriterion("AUTHOR_RANKING_NAME like", value, "authorRankingName");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingNameNotLike(String value) {
            addCriterion("AUTHOR_RANKING_NAME not like", value, "authorRankingName");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingNameIn(List<String> values) {
            addCriterion("AUTHOR_RANKING_NAME in", values, "authorRankingName");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingNameNotIn(List<String> values) {
            addCriterion("AUTHOR_RANKING_NAME not in", values, "authorRankingName");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingNameBetween(String value1, String value2) {
            addCriterion("AUTHOR_RANKING_NAME between", value1, value2, "authorRankingName");
            return (Criteria) this;
        }

        public Criteria andAuthorRankingNameNotBetween(String value1, String value2) {
            addCriterion("AUTHOR_RANKING_NAME not between", value1, value2, "authorRankingName");
            return (Criteria) this;
        }

        public Criteria andReportTimeIsNull() {
            addCriterion("REPORT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andReportTimeIsNotNull() {
            addCriterion("REPORT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andReportTimeEqualTo(String value) {
            addCriterion("REPORT_TIME =", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeNotEqualTo(String value) {
            addCriterion("REPORT_TIME <>", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeGreaterThan(String value) {
            addCriterion("REPORT_TIME >", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeGreaterThanOrEqualTo(String value) {
            addCriterion("REPORT_TIME >=", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeLessThan(String value) {
            addCriterion("REPORT_TIME <", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeLessThanOrEqualTo(String value) {
            addCriterion("REPORT_TIME <=", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeLike(String value) {
            addCriterion("REPORT_TIME like", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeNotLike(String value) {
            addCriterion("REPORT_TIME not like", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeIn(List<String> values) {
            addCriterion("REPORT_TIME in", values, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeNotIn(List<String> values) {
            addCriterion("REPORT_TIME not in", values, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeBetween(String value1, String value2) {
            addCriterion("REPORT_TIME between", value1, value2, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeNotBetween(String value1, String value2) {
            addCriterion("REPORT_TIME not between", value1, value2, "reportTime");
            return (Criteria) this;
        }

        public Criteria andSciPointIsNull() {
            addCriterion("SCI_POINT is null");
            return (Criteria) this;
        }

        public Criteria andSciPointIsNotNull() {
            addCriterion("SCI_POINT is not null");
            return (Criteria) this;
        }

        public Criteria andSciPointEqualTo(String value) {
            addCriterion("SCI_POINT =", value, "sciPoint");
            return (Criteria) this;
        }

        public Criteria andSciPointNotEqualTo(String value) {
            addCriterion("SCI_POINT <>", value, "sciPoint");
            return (Criteria) this;
        }

        public Criteria andSciPointGreaterThan(String value) {
            addCriterion("SCI_POINT >", value, "sciPoint");
            return (Criteria) this;
        }

        public Criteria andSciPointGreaterThanOrEqualTo(String value) {
            addCriterion("SCI_POINT >=", value, "sciPoint");
            return (Criteria) this;
        }

        public Criteria andSciPointLessThan(String value) {
            addCriterion("SCI_POINT <", value, "sciPoint");
            return (Criteria) this;
        }

        public Criteria andSciPointLessThanOrEqualTo(String value) {
            addCriterion("SCI_POINT <=", value, "sciPoint");
            return (Criteria) this;
        }

        public Criteria andSciPointLike(String value) {
            addCriterion("SCI_POINT like", value, "sciPoint");
            return (Criteria) this;
        }

        public Criteria andSciPointNotLike(String value) {
            addCriterion("SCI_POINT not like", value, "sciPoint");
            return (Criteria) this;
        }

        public Criteria andSciPointIn(List<String> values) {
            addCriterion("SCI_POINT in", values, "sciPoint");
            return (Criteria) this;
        }

        public Criteria andSciPointNotIn(List<String> values) {
            addCriterion("SCI_POINT not in", values, "sciPoint");
            return (Criteria) this;
        }

        public Criteria andSciPointBetween(String value1, String value2) {
            addCriterion("SCI_POINT between", value1, value2, "sciPoint");
            return (Criteria) this;
        }

        public Criteria andSciPointNotBetween(String value1, String value2) {
            addCriterion("SCI_POINT not between", value1, value2, "sciPoint");
            return (Criteria) this;
        }

        public Criteria andFileFlowIsNull() {
            addCriterion("FILE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andFileFlowIsNotNull() {
            addCriterion("FILE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andFileFlowEqualTo(String value) {
            addCriterion("FILE_FLOW =", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowNotEqualTo(String value) {
            addCriterion("FILE_FLOW <>", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowGreaterThan(String value) {
            addCriterion("FILE_FLOW >", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowGreaterThanOrEqualTo(String value) {
            addCriterion("FILE_FLOW >=", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowLessThan(String value) {
            addCriterion("FILE_FLOW <", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowLessThanOrEqualTo(String value) {
            addCriterion("FILE_FLOW <=", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowLike(String value) {
            addCriterion("FILE_FLOW like", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowNotLike(String value) {
            addCriterion("FILE_FLOW not like", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowIn(List<String> values) {
            addCriterion("FILE_FLOW in", values, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowNotIn(List<String> values) {
            addCriterion("FILE_FLOW not in", values, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowBetween(String value1, String value2) {
            addCriterion("FILE_FLOW between", value1, value2, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowNotBetween(String value1, String value2) {
            addCriterion("FILE_FLOW not between", value1, value2, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andProductWorkNameIsNull() {
            addCriterion("PRODUCT_WORK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProductWorkNameIsNotNull() {
            addCriterion("PRODUCT_WORK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProductWorkNameEqualTo(String value) {
            addCriterion("PRODUCT_WORK_NAME =", value, "productWorkName");
            return (Criteria) this;
        }

        public Criteria andProductWorkNameNotEqualTo(String value) {
            addCriterion("PRODUCT_WORK_NAME <>", value, "productWorkName");
            return (Criteria) this;
        }

        public Criteria andProductWorkNameGreaterThan(String value) {
            addCriterion("PRODUCT_WORK_NAME >", value, "productWorkName");
            return (Criteria) this;
        }

        public Criteria andProductWorkNameGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_WORK_NAME >=", value, "productWorkName");
            return (Criteria) this;
        }

        public Criteria andProductWorkNameLessThan(String value) {
            addCriterion("PRODUCT_WORK_NAME <", value, "productWorkName");
            return (Criteria) this;
        }

        public Criteria andProductWorkNameLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_WORK_NAME <=", value, "productWorkName");
            return (Criteria) this;
        }

        public Criteria andProductWorkNameLike(String value) {
            addCriterion("PRODUCT_WORK_NAME like", value, "productWorkName");
            return (Criteria) this;
        }

        public Criteria andProductWorkNameNotLike(String value) {
            addCriterion("PRODUCT_WORK_NAME not like", value, "productWorkName");
            return (Criteria) this;
        }

        public Criteria andProductWorkNameIn(List<String> values) {
            addCriterion("PRODUCT_WORK_NAME in", values, "productWorkName");
            return (Criteria) this;
        }

        public Criteria andProductWorkNameNotIn(List<String> values) {
            addCriterion("PRODUCT_WORK_NAME not in", values, "productWorkName");
            return (Criteria) this;
        }

        public Criteria andProductWorkNameBetween(String value1, String value2) {
            addCriterion("PRODUCT_WORK_NAME between", value1, value2, "productWorkName");
            return (Criteria) this;
        }

        public Criteria andProductWorkNameNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_WORK_NAME not between", value1, value2, "productWorkName");
            return (Criteria) this;
        }

        public Criteria andProductCodeIsNull() {
            addCriterion("PRODUCT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andProductCodeIsNotNull() {
            addCriterion("PRODUCT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andProductCodeEqualTo(String value) {
            addCriterion("PRODUCT_CODE =", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotEqualTo(String value) {
            addCriterion("PRODUCT_CODE <>", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeGreaterThan(String value) {
            addCriterion("PRODUCT_CODE >", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_CODE >=", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLessThan(String value) {
            addCriterion("PRODUCT_CODE <", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_CODE <=", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLike(String value) {
            addCriterion("PRODUCT_CODE like", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotLike(String value) {
            addCriterion("PRODUCT_CODE not like", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeIn(List<String> values) {
            addCriterion("PRODUCT_CODE in", values, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotIn(List<String> values) {
            addCriterion("PRODUCT_CODE not in", values, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeBetween(String value1, String value2) {
            addCriterion("PRODUCT_CODE between", value1, value2, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_CODE not between", value1, value2, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductAmountIsNull() {
            addCriterion("PRODUCT_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andProductAmountIsNotNull() {
            addCriterion("PRODUCT_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andProductAmountEqualTo(String value) {
            addCriterion("PRODUCT_AMOUNT =", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountNotEqualTo(String value) {
            addCriterion("PRODUCT_AMOUNT <>", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountGreaterThan(String value) {
            addCriterion("PRODUCT_AMOUNT >", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_AMOUNT >=", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountLessThan(String value) {
            addCriterion("PRODUCT_AMOUNT <", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_AMOUNT <=", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountLike(String value) {
            addCriterion("PRODUCT_AMOUNT like", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountNotLike(String value) {
            addCriterion("PRODUCT_AMOUNT not like", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountIn(List<String> values) {
            addCriterion("PRODUCT_AMOUNT in", values, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountNotIn(List<String> values) {
            addCriterion("PRODUCT_AMOUNT not in", values, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountBetween(String value1, String value2) {
            addCriterion("PRODUCT_AMOUNT between", value1, value2, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_AMOUNT not between", value1, value2, "productAmount");
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