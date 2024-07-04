package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class NydbPaperTitleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NydbPaperTitleExample() {
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

        public Criteria andDefenceFlowIsNull() {
            addCriterion("DEFENCE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDefenceFlowIsNotNull() {
            addCriterion("DEFENCE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDefenceFlowEqualTo(String value) {
            addCriterion("DEFENCE_FLOW =", value, "defenceFlow");
            return (Criteria) this;
        }

        public Criteria andDefenceFlowNotEqualTo(String value) {
            addCriterion("DEFENCE_FLOW <>", value, "defenceFlow");
            return (Criteria) this;
        }

        public Criteria andDefenceFlowGreaterThan(String value) {
            addCriterion("DEFENCE_FLOW >", value, "defenceFlow");
            return (Criteria) this;
        }

        public Criteria andDefenceFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DEFENCE_FLOW >=", value, "defenceFlow");
            return (Criteria) this;
        }

        public Criteria andDefenceFlowLessThan(String value) {
            addCriterion("DEFENCE_FLOW <", value, "defenceFlow");
            return (Criteria) this;
        }

        public Criteria andDefenceFlowLessThanOrEqualTo(String value) {
            addCriterion("DEFENCE_FLOW <=", value, "defenceFlow");
            return (Criteria) this;
        }

        public Criteria andDefenceFlowLike(String value) {
            addCriterion("DEFENCE_FLOW like", value, "defenceFlow");
            return (Criteria) this;
        }

        public Criteria andDefenceFlowNotLike(String value) {
            addCriterion("DEFENCE_FLOW not like", value, "defenceFlow");
            return (Criteria) this;
        }

        public Criteria andDefenceFlowIn(List<String> values) {
            addCriterion("DEFENCE_FLOW in", values, "defenceFlow");
            return (Criteria) this;
        }

        public Criteria andDefenceFlowNotIn(List<String> values) {
            addCriterion("DEFENCE_FLOW not in", values, "defenceFlow");
            return (Criteria) this;
        }

        public Criteria andDefenceFlowBetween(String value1, String value2) {
            addCriterion("DEFENCE_FLOW between", value1, value2, "defenceFlow");
            return (Criteria) this;
        }

        public Criteria andDefenceFlowNotBetween(String value1, String value2) {
            addCriterion("DEFENCE_FLOW not between", value1, value2, "defenceFlow");
            return (Criteria) this;
        }

        public Criteria andPaperTitleIsNull() {
            addCriterion("PAPER_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andPaperTitleIsNotNull() {
            addCriterion("PAPER_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andPaperTitleEqualTo(String value) {
            addCriterion("PAPER_TITLE =", value, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleNotEqualTo(String value) {
            addCriterion("PAPER_TITLE <>", value, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleGreaterThan(String value) {
            addCriterion("PAPER_TITLE >", value, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER_TITLE >=", value, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleLessThan(String value) {
            addCriterion("PAPER_TITLE <", value, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleLessThanOrEqualTo(String value) {
            addCriterion("PAPER_TITLE <=", value, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleLike(String value) {
            addCriterion("PAPER_TITLE like", value, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleNotLike(String value) {
            addCriterion("PAPER_TITLE not like", value, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleIn(List<String> values) {
            addCriterion("PAPER_TITLE in", values, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleNotIn(List<String> values) {
            addCriterion("PAPER_TITLE not in", values, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleBetween(String value1, String value2) {
            addCriterion("PAPER_TITLE between", value1, value2, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleNotBetween(String value1, String value2) {
            addCriterion("PAPER_TITLE not between", value1, value2, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andJournalNameIsNull() {
            addCriterion("JOURNAL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andJournalNameIsNotNull() {
            addCriterion("JOURNAL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andJournalNameEqualTo(String value) {
            addCriterion("JOURNAL_NAME =", value, "journalName");
            return (Criteria) this;
        }

        public Criteria andJournalNameNotEqualTo(String value) {
            addCriterion("JOURNAL_NAME <>", value, "journalName");
            return (Criteria) this;
        }

        public Criteria andJournalNameGreaterThan(String value) {
            addCriterion("JOURNAL_NAME >", value, "journalName");
            return (Criteria) this;
        }

        public Criteria andJournalNameGreaterThanOrEqualTo(String value) {
            addCriterion("JOURNAL_NAME >=", value, "journalName");
            return (Criteria) this;
        }

        public Criteria andJournalNameLessThan(String value) {
            addCriterion("JOURNAL_NAME <", value, "journalName");
            return (Criteria) this;
        }

        public Criteria andJournalNameLessThanOrEqualTo(String value) {
            addCriterion("JOURNAL_NAME <=", value, "journalName");
            return (Criteria) this;
        }

        public Criteria andJournalNameLike(String value) {
            addCriterion("JOURNAL_NAME like", value, "journalName");
            return (Criteria) this;
        }

        public Criteria andJournalNameNotLike(String value) {
            addCriterion("JOURNAL_NAME not like", value, "journalName");
            return (Criteria) this;
        }

        public Criteria andJournalNameIn(List<String> values) {
            addCriterion("JOURNAL_NAME in", values, "journalName");
            return (Criteria) this;
        }

        public Criteria andJournalNameNotIn(List<String> values) {
            addCriterion("JOURNAL_NAME not in", values, "journalName");
            return (Criteria) this;
        }

        public Criteria andJournalNameBetween(String value1, String value2) {
            addCriterion("JOURNAL_NAME between", value1, value2, "journalName");
            return (Criteria) this;
        }

        public Criteria andJournalNameNotBetween(String value1, String value2) {
            addCriterion("JOURNAL_NAME not between", value1, value2, "journalName");
            return (Criteria) this;
        }

        public Criteria andPublishYearIsNull() {
            addCriterion("PUBLISH_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andPublishYearIsNotNull() {
            addCriterion("PUBLISH_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andPublishYearEqualTo(String value) {
            addCriterion("PUBLISH_YEAR =", value, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearNotEqualTo(String value) {
            addCriterion("PUBLISH_YEAR <>", value, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearGreaterThan(String value) {
            addCriterion("PUBLISH_YEAR >", value, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLISH_YEAR >=", value, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearLessThan(String value) {
            addCriterion("PUBLISH_YEAR <", value, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearLessThanOrEqualTo(String value) {
            addCriterion("PUBLISH_YEAR <=", value, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearLike(String value) {
            addCriterion("PUBLISH_YEAR like", value, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearNotLike(String value) {
            addCriterion("PUBLISH_YEAR not like", value, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearIn(List<String> values) {
            addCriterion("PUBLISH_YEAR in", values, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearNotIn(List<String> values) {
            addCriterion("PUBLISH_YEAR not in", values, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearBetween(String value1, String value2) {
            addCriterion("PUBLISH_YEAR between", value1, value2, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearNotBetween(String value1, String value2) {
            addCriterion("PUBLISH_YEAR not between", value1, value2, "publishYear");
            return (Criteria) this;
        }

        public Criteria andVolumeIsNull() {
            addCriterion("VOLUME is null");
            return (Criteria) this;
        }

        public Criteria andVolumeIsNotNull() {
            addCriterion("VOLUME is not null");
            return (Criteria) this;
        }

        public Criteria andVolumeEqualTo(String value) {
            addCriterion("VOLUME =", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotEqualTo(String value) {
            addCriterion("VOLUME <>", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThan(String value) {
            addCriterion("VOLUME >", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeGreaterThanOrEqualTo(String value) {
            addCriterion("VOLUME >=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThan(String value) {
            addCriterion("VOLUME <", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLessThanOrEqualTo(String value) {
            addCriterion("VOLUME <=", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeLike(String value) {
            addCriterion("VOLUME like", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotLike(String value) {
            addCriterion("VOLUME not like", value, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeIn(List<String> values) {
            addCriterion("VOLUME in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotIn(List<String> values) {
            addCriterion("VOLUME not in", values, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeBetween(String value1, String value2) {
            addCriterion("VOLUME between", value1, value2, "volume");
            return (Criteria) this;
        }

        public Criteria andVolumeNotBetween(String value1, String value2) {
            addCriterion("VOLUME not between", value1, value2, "volume");
            return (Criteria) this;
        }

        public Criteria andPageNumberIsNull() {
            addCriterion("PAGE_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andPageNumberIsNotNull() {
            addCriterion("PAGE_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andPageNumberEqualTo(String value) {
            addCriterion("PAGE_NUMBER =", value, "pageNumber");
            return (Criteria) this;
        }

        public Criteria andPageNumberNotEqualTo(String value) {
            addCriterion("PAGE_NUMBER <>", value, "pageNumber");
            return (Criteria) this;
        }

        public Criteria andPageNumberGreaterThan(String value) {
            addCriterion("PAGE_NUMBER >", value, "pageNumber");
            return (Criteria) this;
        }

        public Criteria andPageNumberGreaterThanOrEqualTo(String value) {
            addCriterion("PAGE_NUMBER >=", value, "pageNumber");
            return (Criteria) this;
        }

        public Criteria andPageNumberLessThan(String value) {
            addCriterion("PAGE_NUMBER <", value, "pageNumber");
            return (Criteria) this;
        }

        public Criteria andPageNumberLessThanOrEqualTo(String value) {
            addCriterion("PAGE_NUMBER <=", value, "pageNumber");
            return (Criteria) this;
        }

        public Criteria andPageNumberLike(String value) {
            addCriterion("PAGE_NUMBER like", value, "pageNumber");
            return (Criteria) this;
        }

        public Criteria andPageNumberNotLike(String value) {
            addCriterion("PAGE_NUMBER not like", value, "pageNumber");
            return (Criteria) this;
        }

        public Criteria andPageNumberIn(List<String> values) {
            addCriterion("PAGE_NUMBER in", values, "pageNumber");
            return (Criteria) this;
        }

        public Criteria andPageNumberNotIn(List<String> values) {
            addCriterion("PAGE_NUMBER not in", values, "pageNumber");
            return (Criteria) this;
        }

        public Criteria andPageNumberBetween(String value1, String value2) {
            addCriterion("PAGE_NUMBER between", value1, value2, "pageNumber");
            return (Criteria) this;
        }

        public Criteria andPageNumberNotBetween(String value1, String value2) {
            addCriterion("PAGE_NUMBER not between", value1, value2, "pageNumber");
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

        public Criteria andDegreeReqFlagIsNull() {
            addCriterion("DEGREE_REQ_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andDegreeReqFlagIsNotNull() {
            addCriterion("DEGREE_REQ_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeReqFlagEqualTo(String value) {
            addCriterion("DEGREE_REQ_FLAG =", value, "degreeReqFlag");
            return (Criteria) this;
        }

        public Criteria andDegreeReqFlagNotEqualTo(String value) {
            addCriterion("DEGREE_REQ_FLAG <>", value, "degreeReqFlag");
            return (Criteria) this;
        }

        public Criteria andDegreeReqFlagGreaterThan(String value) {
            addCriterion("DEGREE_REQ_FLAG >", value, "degreeReqFlag");
            return (Criteria) this;
        }

        public Criteria andDegreeReqFlagGreaterThanOrEqualTo(String value) {
            addCriterion("DEGREE_REQ_FLAG >=", value, "degreeReqFlag");
            return (Criteria) this;
        }

        public Criteria andDegreeReqFlagLessThan(String value) {
            addCriterion("DEGREE_REQ_FLAG <", value, "degreeReqFlag");
            return (Criteria) this;
        }

        public Criteria andDegreeReqFlagLessThanOrEqualTo(String value) {
            addCriterion("DEGREE_REQ_FLAG <=", value, "degreeReqFlag");
            return (Criteria) this;
        }

        public Criteria andDegreeReqFlagLike(String value) {
            addCriterion("DEGREE_REQ_FLAG like", value, "degreeReqFlag");
            return (Criteria) this;
        }

        public Criteria andDegreeReqFlagNotLike(String value) {
            addCriterion("DEGREE_REQ_FLAG not like", value, "degreeReqFlag");
            return (Criteria) this;
        }

        public Criteria andDegreeReqFlagIn(List<String> values) {
            addCriterion("DEGREE_REQ_FLAG in", values, "degreeReqFlag");
            return (Criteria) this;
        }

        public Criteria andDegreeReqFlagNotIn(List<String> values) {
            addCriterion("DEGREE_REQ_FLAG not in", values, "degreeReqFlag");
            return (Criteria) this;
        }

        public Criteria andDegreeReqFlagBetween(String value1, String value2) {
            addCriterion("DEGREE_REQ_FLAG between", value1, value2, "degreeReqFlag");
            return (Criteria) this;
        }

        public Criteria andDegreeReqFlagNotBetween(String value1, String value2) {
            addCriterion("DEGREE_REQ_FLAG not between", value1, value2, "degreeReqFlag");
            return (Criteria) this;
        }

        public Criteria andJournalTypeIsNull() {
            addCriterion("JOURNAL_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andJournalTypeIsNotNull() {
            addCriterion("JOURNAL_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andJournalTypeEqualTo(String value) {
            addCriterion("JOURNAL_TYPE =", value, "journalType");
            return (Criteria) this;
        }

        public Criteria andJournalTypeNotEqualTo(String value) {
            addCriterion("JOURNAL_TYPE <>", value, "journalType");
            return (Criteria) this;
        }

        public Criteria andJournalTypeGreaterThan(String value) {
            addCriterion("JOURNAL_TYPE >", value, "journalType");
            return (Criteria) this;
        }

        public Criteria andJournalTypeGreaterThanOrEqualTo(String value) {
            addCriterion("JOURNAL_TYPE >=", value, "journalType");
            return (Criteria) this;
        }

        public Criteria andJournalTypeLessThan(String value) {
            addCriterion("JOURNAL_TYPE <", value, "journalType");
            return (Criteria) this;
        }

        public Criteria andJournalTypeLessThanOrEqualTo(String value) {
            addCriterion("JOURNAL_TYPE <=", value, "journalType");
            return (Criteria) this;
        }

        public Criteria andJournalTypeLike(String value) {
            addCriterion("JOURNAL_TYPE like", value, "journalType");
            return (Criteria) this;
        }

        public Criteria andJournalTypeNotLike(String value) {
            addCriterion("JOURNAL_TYPE not like", value, "journalType");
            return (Criteria) this;
        }

        public Criteria andJournalTypeIn(List<String> values) {
            addCriterion("JOURNAL_TYPE in", values, "journalType");
            return (Criteria) this;
        }

        public Criteria andJournalTypeNotIn(List<String> values) {
            addCriterion("JOURNAL_TYPE not in", values, "journalType");
            return (Criteria) this;
        }

        public Criteria andJournalTypeBetween(String value1, String value2) {
            addCriterion("JOURNAL_TYPE between", value1, value2, "journalType");
            return (Criteria) this;
        }

        public Criteria andJournalTypeNotBetween(String value1, String value2) {
            addCriterion("JOURNAL_TYPE not between", value1, value2, "journalType");
            return (Criteria) this;
        }

        public Criteria andArticleStatusIsNull() {
            addCriterion("ARTICLE_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andArticleStatusIsNotNull() {
            addCriterion("ARTICLE_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andArticleStatusEqualTo(String value) {
            addCriterion("ARTICLE_STATUS =", value, "articleStatus");
            return (Criteria) this;
        }

        public Criteria andArticleStatusNotEqualTo(String value) {
            addCriterion("ARTICLE_STATUS <>", value, "articleStatus");
            return (Criteria) this;
        }

        public Criteria andArticleStatusGreaterThan(String value) {
            addCriterion("ARTICLE_STATUS >", value, "articleStatus");
            return (Criteria) this;
        }

        public Criteria andArticleStatusGreaterThanOrEqualTo(String value) {
            addCriterion("ARTICLE_STATUS >=", value, "articleStatus");
            return (Criteria) this;
        }

        public Criteria andArticleStatusLessThan(String value) {
            addCriterion("ARTICLE_STATUS <", value, "articleStatus");
            return (Criteria) this;
        }

        public Criteria andArticleStatusLessThanOrEqualTo(String value) {
            addCriterion("ARTICLE_STATUS <=", value, "articleStatus");
            return (Criteria) this;
        }

        public Criteria andArticleStatusLike(String value) {
            addCriterion("ARTICLE_STATUS like", value, "articleStatus");
            return (Criteria) this;
        }

        public Criteria andArticleStatusNotLike(String value) {
            addCriterion("ARTICLE_STATUS not like", value, "articleStatus");
            return (Criteria) this;
        }

        public Criteria andArticleStatusIn(List<String> values) {
            addCriterion("ARTICLE_STATUS in", values, "articleStatus");
            return (Criteria) this;
        }

        public Criteria andArticleStatusNotIn(List<String> values) {
            addCriterion("ARTICLE_STATUS not in", values, "articleStatus");
            return (Criteria) this;
        }

        public Criteria andArticleStatusBetween(String value1, String value2) {
            addCriterion("ARTICLE_STATUS between", value1, value2, "articleStatus");
            return (Criteria) this;
        }

        public Criteria andArticleStatusNotBetween(String value1, String value2) {
            addCriterion("ARTICLE_STATUS not between", value1, value2, "articleStatus");
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