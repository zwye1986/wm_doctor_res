package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResSupervisioSubjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResSupervisioSubjectExample() {
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

        public Criteria andOpenTimeIsNull() {
            addCriterion("OPEN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIsNotNull() {
            addCriterion("OPEN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andOpenTimeEqualTo(String value) {
            addCriterion("OPEN_TIME =", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotEqualTo(String value) {
            addCriterion("OPEN_TIME <>", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeGreaterThan(String value) {
            addCriterion("OPEN_TIME >", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeGreaterThanOrEqualTo(String value) {
            addCriterion("OPEN_TIME >=", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeLessThan(String value) {
            addCriterion("OPEN_TIME <", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeLessThanOrEqualTo(String value) {
            addCriterion("OPEN_TIME <=", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeLike(String value) {
            addCriterion("OPEN_TIME like", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotLike(String value) {
            addCriterion("OPEN_TIME not like", value, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeIn(List<String> values) {
            addCriterion("OPEN_TIME in", values, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotIn(List<String> values) {
            addCriterion("OPEN_TIME not in", values, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeBetween(String value1, String value2) {
            addCriterion("OPEN_TIME between", value1, value2, "openTime");
            return (Criteria) this;
        }

        public Criteria andOpenTimeNotBetween(String value1, String value2) {
            addCriterion("OPEN_TIME not between", value1, value2, "openTime");
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

        public Criteria andSpeIdIsNull() {
            addCriterion("SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSpeIdIsNotNull() {
            addCriterion("SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSpeIdEqualTo(String value) {
            addCriterion("SPE_ID =", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotEqualTo(String value) {
            addCriterion("SPE_ID <>", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdGreaterThan(String value) {
            addCriterion("SPE_ID >", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_ID >=", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLessThan(String value) {
            addCriterion("SPE_ID <", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLessThanOrEqualTo(String value) {
            addCriterion("SPE_ID <=", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLike(String value) {
            addCriterion("SPE_ID like", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotLike(String value) {
            addCriterion("SPE_ID not like", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdIn(List<String> values) {
            addCriterion("SPE_ID in", values, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotIn(List<String> values) {
            addCriterion("SPE_ID not in", values, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdBetween(String value1, String value2) {
            addCriterion("SPE_ID between", value1, value2, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotBetween(String value1, String value2) {
            addCriterion("SPE_ID not between", value1, value2, "speId");
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

        public Criteria andSupervisioSummaryIsNull() {
            addCriterion("SUPERVISIO_SUMMARY is null");
            return (Criteria) this;
        }

        public Criteria andSupervisioSummaryIsNotNull() {
            addCriterion("SUPERVISIO_SUMMARY is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisioSummaryEqualTo(String value) {
            addCriterion("SUPERVISIO_SUMMARY =", value, "supervisioSummary");
            return (Criteria) this;
        }

        public Criteria andSupervisioSummaryNotEqualTo(String value) {
            addCriterion("SUPERVISIO_SUMMARY <>", value, "supervisioSummary");
            return (Criteria) this;
        }

        public Criteria andSupervisioSummaryGreaterThan(String value) {
            addCriterion("SUPERVISIO_SUMMARY >", value, "supervisioSummary");
            return (Criteria) this;
        }

        public Criteria andSupervisioSummaryGreaterThanOrEqualTo(String value) {
            addCriterion("SUPERVISIO_SUMMARY >=", value, "supervisioSummary");
            return (Criteria) this;
        }

        public Criteria andSupervisioSummaryLessThan(String value) {
            addCriterion("SUPERVISIO_SUMMARY <", value, "supervisioSummary");
            return (Criteria) this;
        }

        public Criteria andSupervisioSummaryLessThanOrEqualTo(String value) {
            addCriterion("SUPERVISIO_SUMMARY <=", value, "supervisioSummary");
            return (Criteria) this;
        }

        public Criteria andSupervisioSummaryLike(String value) {
            addCriterion("SUPERVISIO_SUMMARY like", value, "supervisioSummary");
            return (Criteria) this;
        }

        public Criteria andSupervisioSummaryNotLike(String value) {
            addCriterion("SUPERVISIO_SUMMARY not like", value, "supervisioSummary");
            return (Criteria) this;
        }

        public Criteria andSupervisioSummaryIn(List<String> values) {
            addCriterion("SUPERVISIO_SUMMARY in", values, "supervisioSummary");
            return (Criteria) this;
        }

        public Criteria andSupervisioSummaryNotIn(List<String> values) {
            addCriterion("SUPERVISIO_SUMMARY not in", values, "supervisioSummary");
            return (Criteria) this;
        }

        public Criteria andSupervisioSummaryBetween(String value1, String value2) {
            addCriterion("SUPERVISIO_SUMMARY between", value1, value2, "supervisioSummary");
            return (Criteria) this;
        }

        public Criteria andSupervisioSummaryNotBetween(String value1, String value2) {
            addCriterion("SUPERVISIO_SUMMARY not between", value1, value2, "supervisioSummary");
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

        public Criteria andSubjectYearIsNull() {
            addCriterion("SUBJECT_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andSubjectYearIsNotNull() {
            addCriterion("SUBJECT_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectYearEqualTo(String value) {
            addCriterion("SUBJECT_YEAR =", value, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearNotEqualTo(String value) {
            addCriterion("SUBJECT_YEAR <>", value, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearGreaterThan(String value) {
            addCriterion("SUBJECT_YEAR >", value, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_YEAR >=", value, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearLessThan(String value) {
            addCriterion("SUBJECT_YEAR <", value, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_YEAR <=", value, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearLike(String value) {
            addCriterion("SUBJECT_YEAR like", value, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearNotLike(String value) {
            addCriterion("SUBJECT_YEAR not like", value, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearIn(List<String> values) {
            addCriterion("SUBJECT_YEAR in", values, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearNotIn(List<String> values) {
            addCriterion("SUBJECT_YEAR not in", values, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearBetween(String value1, String value2) {
            addCriterion("SUBJECT_YEAR between", value1, value2, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andSubjectYearNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_YEAR not between", value1, value2, "subjectYear");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumIsNull() {
            addCriterion("EVALUATION_NUM is null");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumIsNotNull() {
            addCriterion("EVALUATION_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumEqualTo(String value) {
            addCriterion("EVALUATION_NUM =", value, "evaluationNum");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumNotEqualTo(String value) {
            addCriterion("EVALUATION_NUM <>", value, "evaluationNum");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumGreaterThan(String value) {
            addCriterion("EVALUATION_NUM >", value, "evaluationNum");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumGreaterThanOrEqualTo(String value) {
            addCriterion("EVALUATION_NUM >=", value, "evaluationNum");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumLessThan(String value) {
            addCriterion("EVALUATION_NUM <", value, "evaluationNum");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumLessThanOrEqualTo(String value) {
            addCriterion("EVALUATION_NUM <=", value, "evaluationNum");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumLike(String value) {
            addCriterion("EVALUATION_NUM like", value, "evaluationNum");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumNotLike(String value) {
            addCriterion("EVALUATION_NUM not like", value, "evaluationNum");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumIn(List<String> values) {
            addCriterion("EVALUATION_NUM in", values, "evaluationNum");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumNotIn(List<String> values) {
            addCriterion("EVALUATION_NUM not in", values, "evaluationNum");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumBetween(String value1, String value2) {
            addCriterion("EVALUATION_NUM between", value1, value2, "evaluationNum");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumNotBetween(String value1, String value2) {
            addCriterion("EVALUATION_NUM not between", value1, value2, "evaluationNum");
            return (Criteria) this;
        }

        public Criteria andAvgScoreIsNull() {
            addCriterion("AVG_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andAvgScoreIsNotNull() {
            addCriterion("AVG_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andAvgScoreEqualTo(String value) {
            addCriterion("AVG_SCORE =", value, "avgScore");
            return (Criteria) this;
        }

        public Criteria andAvgScoreNotEqualTo(String value) {
            addCriterion("AVG_SCORE <>", value, "avgScore");
            return (Criteria) this;
        }

        public Criteria andAvgScoreGreaterThan(String value) {
            addCriterion("AVG_SCORE >", value, "avgScore");
            return (Criteria) this;
        }

        public Criteria andAvgScoreGreaterThanOrEqualTo(String value) {
            addCriterion("AVG_SCORE >=", value, "avgScore");
            return (Criteria) this;
        }

        public Criteria andAvgScoreLessThan(String value) {
            addCriterion("AVG_SCORE <", value, "avgScore");
            return (Criteria) this;
        }

        public Criteria andAvgScoreLessThanOrEqualTo(String value) {
            addCriterion("AVG_SCORE <=", value, "avgScore");
            return (Criteria) this;
        }

        public Criteria andAvgScoreLike(String value) {
            addCriterion("AVG_SCORE like", value, "avgScore");
            return (Criteria) this;
        }

        public Criteria andAvgScoreNotLike(String value) {
            addCriterion("AVG_SCORE not like", value, "avgScore");
            return (Criteria) this;
        }

        public Criteria andAvgScoreIn(List<String> values) {
            addCriterion("AVG_SCORE in", values, "avgScore");
            return (Criteria) this;
        }

        public Criteria andAvgScoreNotIn(List<String> values) {
            addCriterion("AVG_SCORE not in", values, "avgScore");
            return (Criteria) this;
        }

        public Criteria andAvgScoreBetween(String value1, String value2) {
            addCriterion("AVG_SCORE between", value1, value2, "avgScore");
            return (Criteria) this;
        }

        public Criteria andAvgScoreNotBetween(String value1, String value2) {
            addCriterion("AVG_SCORE not between", value1, value2, "avgScore");
            return (Criteria) this;
        }

        public Criteria andDevTimeIsNull() {
            addCriterion("DEV_TIME is null");
            return (Criteria) this;
        }

        public Criteria andDevTimeIsNotNull() {
            addCriterion("DEV_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andDevTimeEqualTo(String value) {
            addCriterion("DEV_TIME =", value, "devTime");
            return (Criteria) this;
        }

        public Criteria andDevTimeNotEqualTo(String value) {
            addCriterion("DEV_TIME <>", value, "devTime");
            return (Criteria) this;
        }

        public Criteria andDevTimeGreaterThan(String value) {
            addCriterion("DEV_TIME >", value, "devTime");
            return (Criteria) this;
        }

        public Criteria andDevTimeGreaterThanOrEqualTo(String value) {
            addCriterion("DEV_TIME >=", value, "devTime");
            return (Criteria) this;
        }

        public Criteria andDevTimeLessThan(String value) {
            addCriterion("DEV_TIME <", value, "devTime");
            return (Criteria) this;
        }

        public Criteria andDevTimeLessThanOrEqualTo(String value) {
            addCriterion("DEV_TIME <=", value, "devTime");
            return (Criteria) this;
        }

        public Criteria andDevTimeLike(String value) {
            addCriterion("DEV_TIME like", value, "devTime");
            return (Criteria) this;
        }

        public Criteria andDevTimeNotLike(String value) {
            addCriterion("DEV_TIME not like", value, "devTime");
            return (Criteria) this;
        }

        public Criteria andDevTimeIn(List<String> values) {
            addCriterion("DEV_TIME in", values, "devTime");
            return (Criteria) this;
        }

        public Criteria andDevTimeNotIn(List<String> values) {
            addCriterion("DEV_TIME not in", values, "devTime");
            return (Criteria) this;
        }

        public Criteria andDevTimeBetween(String value1, String value2) {
            addCriterion("DEV_TIME between", value1, value2, "devTime");
            return (Criteria) this;
        }

        public Criteria andDevTimeNotBetween(String value1, String value2) {
            addCriterion("DEV_TIME not between", value1, value2, "devTime");
            return (Criteria) this;
        }

        public Criteria andClosedTimeIsNull() {
            addCriterion("CLOSED_TIME is null");
            return (Criteria) this;
        }

        public Criteria andClosedTimeIsNotNull() {
            addCriterion("CLOSED_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andClosedTimeEqualTo(String value) {
            addCriterion("CLOSED_TIME =", value, "closedTime");
            return (Criteria) this;
        }

        public Criteria andClosedTimeNotEqualTo(String value) {
            addCriterion("CLOSED_TIME <>", value, "closedTime");
            return (Criteria) this;
        }

        public Criteria andClosedTimeGreaterThan(String value) {
            addCriterion("CLOSED_TIME >", value, "closedTime");
            return (Criteria) this;
        }

        public Criteria andClosedTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CLOSED_TIME >=", value, "closedTime");
            return (Criteria) this;
        }

        public Criteria andClosedTimeLessThan(String value) {
            addCriterion("CLOSED_TIME <", value, "closedTime");
            return (Criteria) this;
        }

        public Criteria andClosedTimeLessThanOrEqualTo(String value) {
            addCriterion("CLOSED_TIME <=", value, "closedTime");
            return (Criteria) this;
        }

        public Criteria andClosedTimeLike(String value) {
            addCriterion("CLOSED_TIME like", value, "closedTime");
            return (Criteria) this;
        }

        public Criteria andClosedTimeNotLike(String value) {
            addCriterion("CLOSED_TIME not like", value, "closedTime");
            return (Criteria) this;
        }

        public Criteria andClosedTimeIn(List<String> values) {
            addCriterion("CLOSED_TIME in", values, "closedTime");
            return (Criteria) this;
        }

        public Criteria andClosedTimeNotIn(List<String> values) {
            addCriterion("CLOSED_TIME not in", values, "closedTime");
            return (Criteria) this;
        }

        public Criteria andClosedTimeBetween(String value1, String value2) {
            addCriterion("CLOSED_TIME between", value1, value2, "closedTime");
            return (Criteria) this;
        }

        public Criteria andClosedTimeNotBetween(String value1, String value2) {
            addCriterion("CLOSED_TIME not between", value1, value2, "closedTime");
            return (Criteria) this;
        }

        public Criteria andSubjectEditIsNull() {
            addCriterion("SUBJECT_EDIT is null");
            return (Criteria) this;
        }

        public Criteria andSubjectEditIsNotNull() {
            addCriterion("SUBJECT_EDIT is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectEditEqualTo(String value) {
            addCriterion("SUBJECT_EDIT =", value, "subjectEdit");
            return (Criteria) this;
        }

        public Criteria andSubjectEditNotEqualTo(String value) {
            addCriterion("SUBJECT_EDIT <>", value, "subjectEdit");
            return (Criteria) this;
        }

        public Criteria andSubjectEditGreaterThan(String value) {
            addCriterion("SUBJECT_EDIT >", value, "subjectEdit");
            return (Criteria) this;
        }

        public Criteria andSubjectEditGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_EDIT >=", value, "subjectEdit");
            return (Criteria) this;
        }

        public Criteria andSubjectEditLessThan(String value) {
            addCriterion("SUBJECT_EDIT <", value, "subjectEdit");
            return (Criteria) this;
        }

        public Criteria andSubjectEditLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_EDIT <=", value, "subjectEdit");
            return (Criteria) this;
        }

        public Criteria andSubjectEditLike(String value) {
            addCriterion("SUBJECT_EDIT like", value, "subjectEdit");
            return (Criteria) this;
        }

        public Criteria andSubjectEditNotLike(String value) {
            addCriterion("SUBJECT_EDIT not like", value, "subjectEdit");
            return (Criteria) this;
        }

        public Criteria andSubjectEditIn(List<String> values) {
            addCriterion("SUBJECT_EDIT in", values, "subjectEdit");
            return (Criteria) this;
        }

        public Criteria andSubjectEditNotIn(List<String> values) {
            addCriterion("SUBJECT_EDIT not in", values, "subjectEdit");
            return (Criteria) this;
        }

        public Criteria andSubjectEditBetween(String value1, String value2) {
            addCriterion("SUBJECT_EDIT between", value1, value2, "subjectEdit");
            return (Criteria) this;
        }

        public Criteria andSubjectEditNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_EDIT not between", value1, value2, "subjectEdit");
            return (Criteria) this;
        }

        public Criteria andDevTimeCloseIsNull() {
            addCriterion("DEV_TIME_CLOSE is null");
            return (Criteria) this;
        }

        public Criteria andDevTimeCloseIsNotNull() {
            addCriterion("DEV_TIME_CLOSE is not null");
            return (Criteria) this;
        }

        public Criteria andDevTimeCloseEqualTo(String value) {
            addCriterion("DEV_TIME_CLOSE =", value, "devTimeClose");
            return (Criteria) this;
        }

        public Criteria andDevTimeCloseNotEqualTo(String value) {
            addCriterion("DEV_TIME_CLOSE <>", value, "devTimeClose");
            return (Criteria) this;
        }

        public Criteria andDevTimeCloseGreaterThan(String value) {
            addCriterion("DEV_TIME_CLOSE >", value, "devTimeClose");
            return (Criteria) this;
        }

        public Criteria andDevTimeCloseGreaterThanOrEqualTo(String value) {
            addCriterion("DEV_TIME_CLOSE >=", value, "devTimeClose");
            return (Criteria) this;
        }

        public Criteria andDevTimeCloseLessThan(String value) {
            addCriterion("DEV_TIME_CLOSE <", value, "devTimeClose");
            return (Criteria) this;
        }

        public Criteria andDevTimeCloseLessThanOrEqualTo(String value) {
            addCriterion("DEV_TIME_CLOSE <=", value, "devTimeClose");
            return (Criteria) this;
        }

        public Criteria andDevTimeCloseLike(String value) {
            addCriterion("DEV_TIME_CLOSE like", value, "devTimeClose");
            return (Criteria) this;
        }

        public Criteria andDevTimeCloseNotLike(String value) {
            addCriterion("DEV_TIME_CLOSE not like", value, "devTimeClose");
            return (Criteria) this;
        }

        public Criteria andDevTimeCloseIn(List<String> values) {
            addCriterion("DEV_TIME_CLOSE in", values, "devTimeClose");
            return (Criteria) this;
        }

        public Criteria andDevTimeCloseNotIn(List<String> values) {
            addCriterion("DEV_TIME_CLOSE not in", values, "devTimeClose");
            return (Criteria) this;
        }

        public Criteria andDevTimeCloseBetween(String value1, String value2) {
            addCriterion("DEV_TIME_CLOSE between", value1, value2, "devTimeClose");
            return (Criteria) this;
        }

        public Criteria andDevTimeCloseNotBetween(String value1, String value2) {
            addCriterion("DEV_TIME_CLOSE not between", value1, value2, "devTimeClose");
            return (Criteria) this;
        }

        public Criteria andBaseCodeIsNull() {
            addCriterion("BASE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andBaseCodeIsNotNull() {
            addCriterion("BASE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andBaseCodeEqualTo(String value) {
            addCriterion("BASE_CODE =", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeNotEqualTo(String value) {
            addCriterion("BASE_CODE <>", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeGreaterThan(String value) {
            addCriterion("BASE_CODE >", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeGreaterThanOrEqualTo(String value) {
            addCriterion("BASE_CODE >=", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeLessThan(String value) {
            addCriterion("BASE_CODE <", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeLessThanOrEqualTo(String value) {
            addCriterion("BASE_CODE <=", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeLike(String value) {
            addCriterion("BASE_CODE like", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeNotLike(String value) {
            addCriterion("BASE_CODE not like", value, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeIn(List<String> values) {
            addCriterion("BASE_CODE in", values, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeNotIn(List<String> values) {
            addCriterion("BASE_CODE not in", values, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeBetween(String value1, String value2) {
            addCriterion("BASE_CODE between", value1, value2, "baseCode");
            return (Criteria) this;
        }

        public Criteria andBaseCodeNotBetween(String value1, String value2) {
            addCriterion("BASE_CODE not between", value1, value2, "baseCode");
            return (Criteria) this;
        }

        public Criteria andSubjectActivitiFlowsIsNull() {
            addCriterion("SUBJECT_ACTIVITI_FLOWS is null");
            return (Criteria) this;
        }

        public Criteria andSubjectActivitiFlowsIsNotNull() {
            addCriterion("SUBJECT_ACTIVITI_FLOWS is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectActivitiFlowsEqualTo(String value) {
            addCriterion("SUBJECT_ACTIVITI_FLOWS =", value, "subjectActivitiFlows");
            return (Criteria) this;
        }

        public Criteria andSubjectActivitiFlowsNotEqualTo(String value) {
            addCriterion("SUBJECT_ACTIVITI_FLOWS <>", value, "subjectActivitiFlows");
            return (Criteria) this;
        }

        public Criteria andSubjectActivitiFlowsGreaterThan(String value) {
            addCriterion("SUBJECT_ACTIVITI_FLOWS >", value, "subjectActivitiFlows");
            return (Criteria) this;
        }

        public Criteria andSubjectActivitiFlowsGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_ACTIVITI_FLOWS >=", value, "subjectActivitiFlows");
            return (Criteria) this;
        }

        public Criteria andSubjectActivitiFlowsLessThan(String value) {
            addCriterion("SUBJECT_ACTIVITI_FLOWS <", value, "subjectActivitiFlows");
            return (Criteria) this;
        }

        public Criteria andSubjectActivitiFlowsLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_ACTIVITI_FLOWS <=", value, "subjectActivitiFlows");
            return (Criteria) this;
        }

        public Criteria andSubjectActivitiFlowsLike(String value) {
            addCriterion("SUBJECT_ACTIVITI_FLOWS like", value, "subjectActivitiFlows");
            return (Criteria) this;
        }

        public Criteria andSubjectActivitiFlowsNotLike(String value) {
            addCriterion("SUBJECT_ACTIVITI_FLOWS not like", value, "subjectActivitiFlows");
            return (Criteria) this;
        }

        public Criteria andSubjectActivitiFlowsIn(List<String> values) {
            addCriterion("SUBJECT_ACTIVITI_FLOWS in", values, "subjectActivitiFlows");
            return (Criteria) this;
        }

        public Criteria andSubjectActivitiFlowsNotIn(List<String> values) {
            addCriterion("SUBJECT_ACTIVITI_FLOWS not in", values, "subjectActivitiFlows");
            return (Criteria) this;
        }

        public Criteria andSubjectActivitiFlowsBetween(String value1, String value2) {
            addCriterion("SUBJECT_ACTIVITI_FLOWS between", value1, value2, "subjectActivitiFlows");
            return (Criteria) this;
        }

        public Criteria andSubjectActivitiFlowsNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_ACTIVITI_FLOWS not between", value1, value2, "subjectActivitiFlows");
            return (Criteria) this;
        }

        public Criteria andSupervisioResultsIsNull() {
            addCriterion("SUPERVISIO_RESULTS is null");
            return (Criteria) this;
        }

        public Criteria andSupervisioResultsIsNotNull() {
            addCriterion("SUPERVISIO_RESULTS is not null");
            return (Criteria) this;
        }

        public Criteria andSupervisioResultsEqualTo(String value) {
            addCriterion("SUPERVISIO_RESULTS =", value, "supervisioResults");
            return (Criteria) this;
        }

        public Criteria andSupervisioResultsNotEqualTo(String value) {
            addCriterion("SUPERVISIO_RESULTS <>", value, "supervisioResults");
            return (Criteria) this;
        }

        public Criteria andSupervisioResultsGreaterThan(String value) {
            addCriterion("SUPERVISIO_RESULTS >", value, "supervisioResults");
            return (Criteria) this;
        }

        public Criteria andSupervisioResultsGreaterThanOrEqualTo(String value) {
            addCriterion("SUPERVISIO_RESULTS >=", value, "supervisioResults");
            return (Criteria) this;
        }

        public Criteria andSupervisioResultsLessThan(String value) {
            addCriterion("SUPERVISIO_RESULTS <", value, "supervisioResults");
            return (Criteria) this;
        }

        public Criteria andSupervisioResultsLessThanOrEqualTo(String value) {
            addCriterion("SUPERVISIO_RESULTS <=", value, "supervisioResults");
            return (Criteria) this;
        }

        public Criteria andSupervisioResultsLike(String value) {
            addCriterion("SUPERVISIO_RESULTS like", value, "supervisioResults");
            return (Criteria) this;
        }

        public Criteria andSupervisioResultsNotLike(String value) {
            addCriterion("SUPERVISIO_RESULTS not like", value, "supervisioResults");
            return (Criteria) this;
        }

        public Criteria andSupervisioResultsIn(List<String> values) {
            addCriterion("SUPERVISIO_RESULTS in", values, "supervisioResults");
            return (Criteria) this;
        }

        public Criteria andSupervisioResultsNotIn(List<String> values) {
            addCriterion("SUPERVISIO_RESULTS not in", values, "supervisioResults");
            return (Criteria) this;
        }

        public Criteria andSupervisioResultsBetween(String value1, String value2) {
            addCriterion("SUPERVISIO_RESULTS between", value1, value2, "supervisioResults");
            return (Criteria) this;
        }

        public Criteria andSupervisioResultsNotBetween(String value1, String value2) {
            addCriterion("SUPERVISIO_RESULTS not between", value1, value2, "supervisioResults");
            return (Criteria) this;
        }

        public Criteria andManagementUserFlowIsNull() {
            addCriterion("MANAGEMENT_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andManagementUserFlowIsNotNull() {
            addCriterion("MANAGEMENT_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andManagementUserFlowEqualTo(String value) {
            addCriterion("MANAGEMENT_USER_FLOW =", value, "managementUserFlow");
            return (Criteria) this;
        }

        public Criteria andManagementUserFlowNotEqualTo(String value) {
            addCriterion("MANAGEMENT_USER_FLOW <>", value, "managementUserFlow");
            return (Criteria) this;
        }

        public Criteria andManagementUserFlowGreaterThan(String value) {
            addCriterion("MANAGEMENT_USER_FLOW >", value, "managementUserFlow");
            return (Criteria) this;
        }

        public Criteria andManagementUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGEMENT_USER_FLOW >=", value, "managementUserFlow");
            return (Criteria) this;
        }

        public Criteria andManagementUserFlowLessThan(String value) {
            addCriterion("MANAGEMENT_USER_FLOW <", value, "managementUserFlow");
            return (Criteria) this;
        }

        public Criteria andManagementUserFlowLessThanOrEqualTo(String value) {
            addCriterion("MANAGEMENT_USER_FLOW <=", value, "managementUserFlow");
            return (Criteria) this;
        }

        public Criteria andManagementUserFlowLike(String value) {
            addCriterion("MANAGEMENT_USER_FLOW like", value, "managementUserFlow");
            return (Criteria) this;
        }

        public Criteria andManagementUserFlowNotLike(String value) {
            addCriterion("MANAGEMENT_USER_FLOW not like", value, "managementUserFlow");
            return (Criteria) this;
        }

        public Criteria andManagementUserFlowIn(List<String> values) {
            addCriterion("MANAGEMENT_USER_FLOW in", values, "managementUserFlow");
            return (Criteria) this;
        }

        public Criteria andManagementUserFlowNotIn(List<String> values) {
            addCriterion("MANAGEMENT_USER_FLOW not in", values, "managementUserFlow");
            return (Criteria) this;
        }

        public Criteria andManagementUserFlowBetween(String value1, String value2) {
            addCriterion("MANAGEMENT_USER_FLOW between", value1, value2, "managementUserFlow");
            return (Criteria) this;
        }

        public Criteria andManagementUserFlowNotBetween(String value1, String value2) {
            addCriterion("MANAGEMENT_USER_FLOW not between", value1, value2, "managementUserFlow");
            return (Criteria) this;
        }

        public Criteria andManagementUserNameIsNull() {
            addCriterion("MANAGEMENT_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andManagementUserNameIsNotNull() {
            addCriterion("MANAGEMENT_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andManagementUserNameEqualTo(String value) {
            addCriterion("MANAGEMENT_USER_NAME =", value, "managementUserName");
            return (Criteria) this;
        }

        public Criteria andManagementUserNameNotEqualTo(String value) {
            addCriterion("MANAGEMENT_USER_NAME <>", value, "managementUserName");
            return (Criteria) this;
        }

        public Criteria andManagementUserNameGreaterThan(String value) {
            addCriterion("MANAGEMENT_USER_NAME >", value, "managementUserName");
            return (Criteria) this;
        }

        public Criteria andManagementUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGEMENT_USER_NAME >=", value, "managementUserName");
            return (Criteria) this;
        }

        public Criteria andManagementUserNameLessThan(String value) {
            addCriterion("MANAGEMENT_USER_NAME <", value, "managementUserName");
            return (Criteria) this;
        }

        public Criteria andManagementUserNameLessThanOrEqualTo(String value) {
            addCriterion("MANAGEMENT_USER_NAME <=", value, "managementUserName");
            return (Criteria) this;
        }

        public Criteria andManagementUserNameLike(String value) {
            addCriterion("MANAGEMENT_USER_NAME like", value, "managementUserName");
            return (Criteria) this;
        }

        public Criteria andManagementUserNameNotLike(String value) {
            addCriterion("MANAGEMENT_USER_NAME not like", value, "managementUserName");
            return (Criteria) this;
        }

        public Criteria andManagementUserNameIn(List<String> values) {
            addCriterion("MANAGEMENT_USER_NAME in", values, "managementUserName");
            return (Criteria) this;
        }

        public Criteria andManagementUserNameNotIn(List<String> values) {
            addCriterion("MANAGEMENT_USER_NAME not in", values, "managementUserName");
            return (Criteria) this;
        }

        public Criteria andManagementUserNameBetween(String value1, String value2) {
            addCriterion("MANAGEMENT_USER_NAME between", value1, value2, "managementUserName");
            return (Criteria) this;
        }

        public Criteria andManagementUserNameNotBetween(String value1, String value2) {
            addCriterion("MANAGEMENT_USER_NAME not between", value1, value2, "managementUserName");
            return (Criteria) this;
        }

        public Criteria andReqeditIsNull() {
            addCriterion("REQEDIT is null");
            return (Criteria) this;
        }

        public Criteria andReqeditIsNotNull() {
            addCriterion("REQEDIT is not null");
            return (Criteria) this;
        }

        public Criteria andReqeditEqualTo(String value) {
            addCriterion("REQEDIT =", value, "reqedit");
            return (Criteria) this;
        }

        public Criteria andReqeditNotEqualTo(String value) {
            addCriterion("REQEDIT <>", value, "reqedit");
            return (Criteria) this;
        }

        public Criteria andReqeditGreaterThan(String value) {
            addCriterion("REQEDIT >", value, "reqedit");
            return (Criteria) this;
        }

        public Criteria andReqeditGreaterThanOrEqualTo(String value) {
            addCriterion("REQEDIT >=", value, "reqedit");
            return (Criteria) this;
        }

        public Criteria andReqeditLessThan(String value) {
            addCriterion("REQEDIT <", value, "reqedit");
            return (Criteria) this;
        }

        public Criteria andReqeditLessThanOrEqualTo(String value) {
            addCriterion("REQEDIT <=", value, "reqedit");
            return (Criteria) this;
        }

        public Criteria andReqeditLike(String value) {
            addCriterion("REQEDIT like", value, "reqedit");
            return (Criteria) this;
        }

        public Criteria andReqeditNotLike(String value) {
            addCriterion("REQEDIT not like", value, "reqedit");
            return (Criteria) this;
        }

        public Criteria andReqeditIn(List<String> values) {
            addCriterion("REQEDIT in", values, "reqedit");
            return (Criteria) this;
        }

        public Criteria andReqeditNotIn(List<String> values) {
            addCriterion("REQEDIT not in", values, "reqedit");
            return (Criteria) this;
        }

        public Criteria andReqeditBetween(String value1, String value2) {
            addCriterion("REQEDIT between", value1, value2, "reqedit");
            return (Criteria) this;
        }

        public Criteria andReqeditNotBetween(String value1, String value2) {
            addCriterion("REQEDIT not between", value1, value2, "reqedit");
            return (Criteria) this;
        }

        public Criteria andSupFeedbackIsNull() {
            addCriterion("SUP_FEEDBACK is null");
            return (Criteria) this;
        }

        public Criteria andSupFeedbackIsNotNull() {
            addCriterion("SUP_FEEDBACK is not null");
            return (Criteria) this;
        }

        public Criteria andSupFeedbackEqualTo(String value) {
            addCriterion("SUP_FEEDBACK =", value, "supFeedback");
            return (Criteria) this;
        }

        public Criteria andSupFeedbackNotEqualTo(String value) {
            addCriterion("SUP_FEEDBACK <>", value, "supFeedback");
            return (Criteria) this;
        }

        public Criteria andSupFeedbackGreaterThan(String value) {
            addCriterion("SUP_FEEDBACK >", value, "supFeedback");
            return (Criteria) this;
        }

        public Criteria andSupFeedbackGreaterThanOrEqualTo(String value) {
            addCriterion("SUP_FEEDBACK >=", value, "supFeedback");
            return (Criteria) this;
        }

        public Criteria andSupFeedbackLessThan(String value) {
            addCriterion("SUP_FEEDBACK <", value, "supFeedback");
            return (Criteria) this;
        }

        public Criteria andSupFeedbackLessThanOrEqualTo(String value) {
            addCriterion("SUP_FEEDBACK <=", value, "supFeedback");
            return (Criteria) this;
        }

        public Criteria andSupFeedbackLike(String value) {
            addCriterion("SUP_FEEDBACK like", value, "supFeedback");
            return (Criteria) this;
        }

        public Criteria andSupFeedbackNotLike(String value) {
            addCriterion("SUP_FEEDBACK not like", value, "supFeedback");
            return (Criteria) this;
        }

        public Criteria andSupFeedbackIn(List<String> values) {
            addCriterion("SUP_FEEDBACK in", values, "supFeedback");
            return (Criteria) this;
        }

        public Criteria andSupFeedbackNotIn(List<String> values) {
            addCriterion("SUP_FEEDBACK not in", values, "supFeedback");
            return (Criteria) this;
        }

        public Criteria andSupFeedbackBetween(String value1, String value2) {
            addCriterion("SUP_FEEDBACK between", value1, value2, "supFeedback");
            return (Criteria) this;
        }

        public Criteria andSupFeedbackNotBetween(String value1, String value2) {
            addCriterion("SUP_FEEDBACK not between", value1, value2, "supFeedback");
            return (Criteria) this;
        }

        public Criteria andSpeFeedbackIsNull() {
            addCriterion("SPE_FEEDBACK is null");
            return (Criteria) this;
        }

        public Criteria andSpeFeedbackIsNotNull() {
            addCriterion("SPE_FEEDBACK is not null");
            return (Criteria) this;
        }

        public Criteria andSpeFeedbackEqualTo(String value) {
            addCriterion("SPE_FEEDBACK =", value, "speFeedback");
            return (Criteria) this;
        }

        public Criteria andSpeFeedbackNotEqualTo(String value) {
            addCriterion("SPE_FEEDBACK <>", value, "speFeedback");
            return (Criteria) this;
        }

        public Criteria andSpeFeedbackGreaterThan(String value) {
            addCriterion("SPE_FEEDBACK >", value, "speFeedback");
            return (Criteria) this;
        }

        public Criteria andSpeFeedbackGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_FEEDBACK >=", value, "speFeedback");
            return (Criteria) this;
        }

        public Criteria andSpeFeedbackLessThan(String value) {
            addCriterion("SPE_FEEDBACK <", value, "speFeedback");
            return (Criteria) this;
        }

        public Criteria andSpeFeedbackLessThanOrEqualTo(String value) {
            addCriterion("SPE_FEEDBACK <=", value, "speFeedback");
            return (Criteria) this;
        }

        public Criteria andSpeFeedbackLike(String value) {
            addCriterion("SPE_FEEDBACK like", value, "speFeedback");
            return (Criteria) this;
        }

        public Criteria andSpeFeedbackNotLike(String value) {
            addCriterion("SPE_FEEDBACK not like", value, "speFeedback");
            return (Criteria) this;
        }

        public Criteria andSpeFeedbackIn(List<String> values) {
            addCriterion("SPE_FEEDBACK in", values, "speFeedback");
            return (Criteria) this;
        }

        public Criteria andSpeFeedbackNotIn(List<String> values) {
            addCriterion("SPE_FEEDBACK not in", values, "speFeedback");
            return (Criteria) this;
        }

        public Criteria andSpeFeedbackBetween(String value1, String value2) {
            addCriterion("SPE_FEEDBACK between", value1, value2, "speFeedback");
            return (Criteria) this;
        }

        public Criteria andSpeFeedbackNotBetween(String value1, String value2) {
            addCriterion("SPE_FEEDBACK not between", value1, value2, "speFeedback");
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