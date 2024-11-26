package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class PubMeetingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PubMeetingExample() {
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

        public Criteria andMeetingFlowIsNull() {
            addCriterion("MEETING_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowIsNotNull() {
            addCriterion("MEETING_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowEqualTo(String value) {
            addCriterion("MEETING_FLOW =", value, "meetingFlow");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowNotEqualTo(String value) {
            addCriterion("MEETING_FLOW <>", value, "meetingFlow");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowGreaterThan(String value) {
            addCriterion("MEETING_FLOW >", value, "meetingFlow");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowGreaterThanOrEqualTo(String value) {
            addCriterion("MEETING_FLOW >=", value, "meetingFlow");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowLessThan(String value) {
            addCriterion("MEETING_FLOW <", value, "meetingFlow");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowLessThanOrEqualTo(String value) {
            addCriterion("MEETING_FLOW <=", value, "meetingFlow");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowLike(String value) {
            addCriterion("MEETING_FLOW like", value, "meetingFlow");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowNotLike(String value) {
            addCriterion("MEETING_FLOW not like", value, "meetingFlow");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowIn(List<String> values) {
            addCriterion("MEETING_FLOW in", values, "meetingFlow");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowNotIn(List<String> values) {
            addCriterion("MEETING_FLOW not in", values, "meetingFlow");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowBetween(String value1, String value2) {
            addCriterion("MEETING_FLOW between", value1, value2, "meetingFlow");
            return (Criteria) this;
        }

        public Criteria andMeetingFlowNotBetween(String value1, String value2) {
            addCriterion("MEETING_FLOW not between", value1, value2, "meetingFlow");
            return (Criteria) this;
        }

        public Criteria andMeetingNameIsNull() {
            addCriterion("MEETING_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMeetingNameIsNotNull() {
            addCriterion("MEETING_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMeetingNameEqualTo(String value) {
            addCriterion("MEETING_NAME =", value, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameNotEqualTo(String value) {
            addCriterion("MEETING_NAME <>", value, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameGreaterThan(String value) {
            addCriterion("MEETING_NAME >", value, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameGreaterThanOrEqualTo(String value) {
            addCriterion("MEETING_NAME >=", value, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameLessThan(String value) {
            addCriterion("MEETING_NAME <", value, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameLessThanOrEqualTo(String value) {
            addCriterion("MEETING_NAME <=", value, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameLike(String value) {
            addCriterion("MEETING_NAME like", value, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameNotLike(String value) {
            addCriterion("MEETING_NAME not like", value, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameIn(List<String> values) {
            addCriterion("MEETING_NAME in", values, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameNotIn(List<String> values) {
            addCriterion("MEETING_NAME not in", values, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameBetween(String value1, String value2) {
            addCriterion("MEETING_NAME between", value1, value2, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameNotBetween(String value1, String value2) {
            addCriterion("MEETING_NAME not between", value1, value2, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingDateIsNull() {
            addCriterion("MEETING_DATE is null");
            return (Criteria) this;
        }

        public Criteria andMeetingDateIsNotNull() {
            addCriterion("MEETING_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andMeetingDateEqualTo(String value) {
            addCriterion("MEETING_DATE =", value, "meetingDate");
            return (Criteria) this;
        }

        public Criteria andMeetingDateNotEqualTo(String value) {
            addCriterion("MEETING_DATE <>", value, "meetingDate");
            return (Criteria) this;
        }

        public Criteria andMeetingDateGreaterThan(String value) {
            addCriterion("MEETING_DATE >", value, "meetingDate");
            return (Criteria) this;
        }

        public Criteria andMeetingDateGreaterThanOrEqualTo(String value) {
            addCriterion("MEETING_DATE >=", value, "meetingDate");
            return (Criteria) this;
        }

        public Criteria andMeetingDateLessThan(String value) {
            addCriterion("MEETING_DATE <", value, "meetingDate");
            return (Criteria) this;
        }

        public Criteria andMeetingDateLessThanOrEqualTo(String value) {
            addCriterion("MEETING_DATE <=", value, "meetingDate");
            return (Criteria) this;
        }

        public Criteria andMeetingDateLike(String value) {
            addCriterion("MEETING_DATE like", value, "meetingDate");
            return (Criteria) this;
        }

        public Criteria andMeetingDateNotLike(String value) {
            addCriterion("MEETING_DATE not like", value, "meetingDate");
            return (Criteria) this;
        }

        public Criteria andMeetingDateIn(List<String> values) {
            addCriterion("MEETING_DATE in", values, "meetingDate");
            return (Criteria) this;
        }

        public Criteria andMeetingDateNotIn(List<String> values) {
            addCriterion("MEETING_DATE not in", values, "meetingDate");
            return (Criteria) this;
        }

        public Criteria andMeetingDateBetween(String value1, String value2) {
            addCriterion("MEETING_DATE between", value1, value2, "meetingDate");
            return (Criteria) this;
        }

        public Criteria andMeetingDateNotBetween(String value1, String value2) {
            addCriterion("MEETING_DATE not between", value1, value2, "meetingDate");
            return (Criteria) this;
        }

        public Criteria andMeetingAddressIsNull() {
            addCriterion("MEETING_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andMeetingAddressIsNotNull() {
            addCriterion("MEETING_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andMeetingAddressEqualTo(String value) {
            addCriterion("MEETING_ADDRESS =", value, "meetingAddress");
            return (Criteria) this;
        }

        public Criteria andMeetingAddressNotEqualTo(String value) {
            addCriterion("MEETING_ADDRESS <>", value, "meetingAddress");
            return (Criteria) this;
        }

        public Criteria andMeetingAddressGreaterThan(String value) {
            addCriterion("MEETING_ADDRESS >", value, "meetingAddress");
            return (Criteria) this;
        }

        public Criteria andMeetingAddressGreaterThanOrEqualTo(String value) {
            addCriterion("MEETING_ADDRESS >=", value, "meetingAddress");
            return (Criteria) this;
        }

        public Criteria andMeetingAddressLessThan(String value) {
            addCriterion("MEETING_ADDRESS <", value, "meetingAddress");
            return (Criteria) this;
        }

        public Criteria andMeetingAddressLessThanOrEqualTo(String value) {
            addCriterion("MEETING_ADDRESS <=", value, "meetingAddress");
            return (Criteria) this;
        }

        public Criteria andMeetingAddressLike(String value) {
            addCriterion("MEETING_ADDRESS like", value, "meetingAddress");
            return (Criteria) this;
        }

        public Criteria andMeetingAddressNotLike(String value) {
            addCriterion("MEETING_ADDRESS not like", value, "meetingAddress");
            return (Criteria) this;
        }

        public Criteria andMeetingAddressIn(List<String> values) {
            addCriterion("MEETING_ADDRESS in", values, "meetingAddress");
            return (Criteria) this;
        }

        public Criteria andMeetingAddressNotIn(List<String> values) {
            addCriterion("MEETING_ADDRESS not in", values, "meetingAddress");
            return (Criteria) this;
        }

        public Criteria andMeetingAddressBetween(String value1, String value2) {
            addCriterion("MEETING_ADDRESS between", value1, value2, "meetingAddress");
            return (Criteria) this;
        }

        public Criteria andMeetingAddressNotBetween(String value1, String value2) {
            addCriterion("MEETING_ADDRESS not between", value1, value2, "meetingAddress");
            return (Criteria) this;
        }

        public Criteria andMeetingHostIsNull() {
            addCriterion("MEETING_HOST is null");
            return (Criteria) this;
        }

        public Criteria andMeetingHostIsNotNull() {
            addCriterion("MEETING_HOST is not null");
            return (Criteria) this;
        }

        public Criteria andMeetingHostEqualTo(String value) {
            addCriterion("MEETING_HOST =", value, "meetingHost");
            return (Criteria) this;
        }

        public Criteria andMeetingHostNotEqualTo(String value) {
            addCriterion("MEETING_HOST <>", value, "meetingHost");
            return (Criteria) this;
        }

        public Criteria andMeetingHostGreaterThan(String value) {
            addCriterion("MEETING_HOST >", value, "meetingHost");
            return (Criteria) this;
        }

        public Criteria andMeetingHostGreaterThanOrEqualTo(String value) {
            addCriterion("MEETING_HOST >=", value, "meetingHost");
            return (Criteria) this;
        }

        public Criteria andMeetingHostLessThan(String value) {
            addCriterion("MEETING_HOST <", value, "meetingHost");
            return (Criteria) this;
        }

        public Criteria andMeetingHostLessThanOrEqualTo(String value) {
            addCriterion("MEETING_HOST <=", value, "meetingHost");
            return (Criteria) this;
        }

        public Criteria andMeetingHostLike(String value) {
            addCriterion("MEETING_HOST like", value, "meetingHost");
            return (Criteria) this;
        }

        public Criteria andMeetingHostNotLike(String value) {
            addCriterion("MEETING_HOST not like", value, "meetingHost");
            return (Criteria) this;
        }

        public Criteria andMeetingHostIn(List<String> values) {
            addCriterion("MEETING_HOST in", values, "meetingHost");
            return (Criteria) this;
        }

        public Criteria andMeetingHostNotIn(List<String> values) {
            addCriterion("MEETING_HOST not in", values, "meetingHost");
            return (Criteria) this;
        }

        public Criteria andMeetingHostBetween(String value1, String value2) {
            addCriterion("MEETING_HOST between", value1, value2, "meetingHost");
            return (Criteria) this;
        }

        public Criteria andMeetingHostNotBetween(String value1, String value2) {
            addCriterion("MEETING_HOST not between", value1, value2, "meetingHost");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeIdIsNull() {
            addCriterion("MEETING_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeIdIsNotNull() {
            addCriterion("MEETING_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeIdEqualTo(String value) {
            addCriterion("MEETING_TYPE_ID =", value, "meetingTypeId");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeIdNotEqualTo(String value) {
            addCriterion("MEETING_TYPE_ID <>", value, "meetingTypeId");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeIdGreaterThan(String value) {
            addCriterion("MEETING_TYPE_ID >", value, "meetingTypeId");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("MEETING_TYPE_ID >=", value, "meetingTypeId");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeIdLessThan(String value) {
            addCriterion("MEETING_TYPE_ID <", value, "meetingTypeId");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeIdLessThanOrEqualTo(String value) {
            addCriterion("MEETING_TYPE_ID <=", value, "meetingTypeId");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeIdLike(String value) {
            addCriterion("MEETING_TYPE_ID like", value, "meetingTypeId");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeIdNotLike(String value) {
            addCriterion("MEETING_TYPE_ID not like", value, "meetingTypeId");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeIdIn(List<String> values) {
            addCriterion("MEETING_TYPE_ID in", values, "meetingTypeId");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeIdNotIn(List<String> values) {
            addCriterion("MEETING_TYPE_ID not in", values, "meetingTypeId");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeIdBetween(String value1, String value2) {
            addCriterion("MEETING_TYPE_ID between", value1, value2, "meetingTypeId");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeIdNotBetween(String value1, String value2) {
            addCriterion("MEETING_TYPE_ID not between", value1, value2, "meetingTypeId");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeNameIsNull() {
            addCriterion("MEETING_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeNameIsNotNull() {
            addCriterion("MEETING_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeNameEqualTo(String value) {
            addCriterion("MEETING_TYPE_NAME =", value, "meetingTypeName");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeNameNotEqualTo(String value) {
            addCriterion("MEETING_TYPE_NAME <>", value, "meetingTypeName");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeNameGreaterThan(String value) {
            addCriterion("MEETING_TYPE_NAME >", value, "meetingTypeName");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("MEETING_TYPE_NAME >=", value, "meetingTypeName");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeNameLessThan(String value) {
            addCriterion("MEETING_TYPE_NAME <", value, "meetingTypeName");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeNameLessThanOrEqualTo(String value) {
            addCriterion("MEETING_TYPE_NAME <=", value, "meetingTypeName");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeNameLike(String value) {
            addCriterion("MEETING_TYPE_NAME like", value, "meetingTypeName");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeNameNotLike(String value) {
            addCriterion("MEETING_TYPE_NAME not like", value, "meetingTypeName");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeNameIn(List<String> values) {
            addCriterion("MEETING_TYPE_NAME in", values, "meetingTypeName");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeNameNotIn(List<String> values) {
            addCriterion("MEETING_TYPE_NAME not in", values, "meetingTypeName");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeNameBetween(String value1, String value2) {
            addCriterion("MEETING_TYPE_NAME between", value1, value2, "meetingTypeName");
            return (Criteria) this;
        }

        public Criteria andMeetingTypeNameNotBetween(String value1, String value2) {
            addCriterion("MEETING_TYPE_NAME not between", value1, value2, "meetingTypeName");
            return (Criteria) this;
        }

        public Criteria andMeetingSummaryIsNull() {
            addCriterion("MEETING_SUMMARY is null");
            return (Criteria) this;
        }

        public Criteria andMeetingSummaryIsNotNull() {
            addCriterion("MEETING_SUMMARY is not null");
            return (Criteria) this;
        }

        public Criteria andMeetingSummaryEqualTo(String value) {
            addCriterion("MEETING_SUMMARY =", value, "meetingSummary");
            return (Criteria) this;
        }

        public Criteria andMeetingSummaryNotEqualTo(String value) {
            addCriterion("MEETING_SUMMARY <>", value, "meetingSummary");
            return (Criteria) this;
        }

        public Criteria andMeetingSummaryGreaterThan(String value) {
            addCriterion("MEETING_SUMMARY >", value, "meetingSummary");
            return (Criteria) this;
        }

        public Criteria andMeetingSummaryGreaterThanOrEqualTo(String value) {
            addCriterion("MEETING_SUMMARY >=", value, "meetingSummary");
            return (Criteria) this;
        }

        public Criteria andMeetingSummaryLessThan(String value) {
            addCriterion("MEETING_SUMMARY <", value, "meetingSummary");
            return (Criteria) this;
        }

        public Criteria andMeetingSummaryLessThanOrEqualTo(String value) {
            addCriterion("MEETING_SUMMARY <=", value, "meetingSummary");
            return (Criteria) this;
        }

        public Criteria andMeetingSummaryLike(String value) {
            addCriterion("MEETING_SUMMARY like", value, "meetingSummary");
            return (Criteria) this;
        }

        public Criteria andMeetingSummaryNotLike(String value) {
            addCriterion("MEETING_SUMMARY not like", value, "meetingSummary");
            return (Criteria) this;
        }

        public Criteria andMeetingSummaryIn(List<String> values) {
            addCriterion("MEETING_SUMMARY in", values, "meetingSummary");
            return (Criteria) this;
        }

        public Criteria andMeetingSummaryNotIn(List<String> values) {
            addCriterion("MEETING_SUMMARY not in", values, "meetingSummary");
            return (Criteria) this;
        }

        public Criteria andMeetingSummaryBetween(String value1, String value2) {
            addCriterion("MEETING_SUMMARY between", value1, value2, "meetingSummary");
            return (Criteria) this;
        }

        public Criteria andMeetingSummaryNotBetween(String value1, String value2) {
            addCriterion("MEETING_SUMMARY not between", value1, value2, "meetingSummary");
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