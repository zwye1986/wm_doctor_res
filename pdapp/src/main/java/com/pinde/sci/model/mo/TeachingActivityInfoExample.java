package com.pinde.sci.model.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TeachingActivityInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TeachingActivityInfoExample() {
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

        public Criteria andActivityFlowIsNull() {
            addCriterion("ACTIVITY_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andActivityFlowIsNotNull() {
            addCriterion("ACTIVITY_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andActivityFlowEqualTo(String value) {
            addCriterion("ACTIVITY_FLOW =", value, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowNotEqualTo(String value) {
            addCriterion("ACTIVITY_FLOW <>", value, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowGreaterThan(String value) {
            addCriterion("ACTIVITY_FLOW >", value, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_FLOW >=", value, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowLessThan(String value) {
            addCriterion("ACTIVITY_FLOW <", value, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowLessThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_FLOW <=", value, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowLike(String value) {
            addCriterion("ACTIVITY_FLOW like", value, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowNotLike(String value) {
            addCriterion("ACTIVITY_FLOW not like", value, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowIn(List<String> values) {
            addCriterion("ACTIVITY_FLOW in", values, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowNotIn(List<String> values) {
            addCriterion("ACTIVITY_FLOW not in", values, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowBetween(String value1, String value2) {
            addCriterion("ACTIVITY_FLOW between", value1, value2, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityFlowNotBetween(String value1, String value2) {
            addCriterion("ACTIVITY_FLOW not between", value1, value2, "activityFlow");
            return (Criteria) this;
        }

        public Criteria andActivityNameIsNull() {
            addCriterion("ACTIVITY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andActivityNameIsNotNull() {
            addCriterion("ACTIVITY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andActivityNameEqualTo(String value) {
            addCriterion("ACTIVITY_NAME =", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotEqualTo(String value) {
            addCriterion("ACTIVITY_NAME <>", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameGreaterThan(String value) {
            addCriterion("ACTIVITY_NAME >", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_NAME >=", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLessThan(String value) {
            addCriterion("ACTIVITY_NAME <", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLessThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_NAME <=", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLike(String value) {
            addCriterion("ACTIVITY_NAME like", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotLike(String value) {
            addCriterion("ACTIVITY_NAME not like", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameIn(List<String> values) {
            addCriterion("ACTIVITY_NAME in", values, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotIn(List<String> values) {
            addCriterion("ACTIVITY_NAME not in", values, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameBetween(String value1, String value2) {
            addCriterion("ACTIVITY_NAME between", value1, value2, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotBetween(String value1, String value2) {
            addCriterion("ACTIVITY_NAME not between", value1, value2, "activityName");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIsNull() {
            addCriterion("DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIsNotNull() {
            addCriterion("DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDeptFlowEqualTo(String value) {
            addCriterion("DEPT_FLOW =", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotEqualTo(String value) {
            addCriterion("DEPT_FLOW <>", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowGreaterThan(String value) {
            addCriterion("DEPT_FLOW >", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_FLOW >=", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLessThan(String value) {
            addCriterion("DEPT_FLOW <", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("DEPT_FLOW <=", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLike(String value) {
            addCriterion("DEPT_FLOW like", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotLike(String value) {
            addCriterion("DEPT_FLOW not like", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIn(List<String> values) {
            addCriterion("DEPT_FLOW in", values, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotIn(List<String> values) {
            addCriterion("DEPT_FLOW not in", values, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowBetween(String value1, String value2) {
            addCriterion("DEPT_FLOW between", value1, value2, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotBetween(String value1, String value2) {
            addCriterion("DEPT_FLOW not between", value1, value2, "deptFlow");
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

        public Criteria andSpeakerFlowIsNull() {
            addCriterion("SPEAKER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSpeakerFlowIsNotNull() {
            addCriterion("SPEAKER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSpeakerFlowEqualTo(String value) {
            addCriterion("SPEAKER_FLOW =", value, "speakerFlow");
            return (Criteria) this;
        }

        public Criteria andSpeakerFlowNotEqualTo(String value) {
            addCriterion("SPEAKER_FLOW <>", value, "speakerFlow");
            return (Criteria) this;
        }

        public Criteria andSpeakerFlowGreaterThan(String value) {
            addCriterion("SPEAKER_FLOW >", value, "speakerFlow");
            return (Criteria) this;
        }

        public Criteria andSpeakerFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SPEAKER_FLOW >=", value, "speakerFlow");
            return (Criteria) this;
        }

        public Criteria andSpeakerFlowLessThan(String value) {
            addCriterion("SPEAKER_FLOW <", value, "speakerFlow");
            return (Criteria) this;
        }

        public Criteria andSpeakerFlowLessThanOrEqualTo(String value) {
            addCriterion("SPEAKER_FLOW <=", value, "speakerFlow");
            return (Criteria) this;
        }

        public Criteria andSpeakerFlowLike(String value) {
            addCriterion("SPEAKER_FLOW like", value, "speakerFlow");
            return (Criteria) this;
        }

        public Criteria andSpeakerFlowNotLike(String value) {
            addCriterion("SPEAKER_FLOW not like", value, "speakerFlow");
            return (Criteria) this;
        }

        public Criteria andSpeakerFlowIn(List<String> values) {
            addCriterion("SPEAKER_FLOW in", values, "speakerFlow");
            return (Criteria) this;
        }

        public Criteria andSpeakerFlowNotIn(List<String> values) {
            addCriterion("SPEAKER_FLOW not in", values, "speakerFlow");
            return (Criteria) this;
        }

        public Criteria andSpeakerFlowBetween(String value1, String value2) {
            addCriterion("SPEAKER_FLOW between", value1, value2, "speakerFlow");
            return (Criteria) this;
        }

        public Criteria andSpeakerFlowNotBetween(String value1, String value2) {
            addCriterion("SPEAKER_FLOW not between", value1, value2, "speakerFlow");
            return (Criteria) this;
        }

        public Criteria andSpeakerPhoneIsNull() {
            addCriterion("SPEAKER_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andSpeakerPhoneIsNotNull() {
            addCriterion("SPEAKER_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andSpeakerPhoneEqualTo(String value) {
            addCriterion("SPEAKER_PHONE =", value, "speakerPhone");
            return (Criteria) this;
        }

        public Criteria andSpeakerPhoneNotEqualTo(String value) {
            addCriterion("SPEAKER_PHONE <>", value, "speakerPhone");
            return (Criteria) this;
        }

        public Criteria andSpeakerPhoneGreaterThan(String value) {
            addCriterion("SPEAKER_PHONE >", value, "speakerPhone");
            return (Criteria) this;
        }

        public Criteria andSpeakerPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("SPEAKER_PHONE >=", value, "speakerPhone");
            return (Criteria) this;
        }

        public Criteria andSpeakerPhoneLessThan(String value) {
            addCriterion("SPEAKER_PHONE <", value, "speakerPhone");
            return (Criteria) this;
        }

        public Criteria andSpeakerPhoneLessThanOrEqualTo(String value) {
            addCriterion("SPEAKER_PHONE <=", value, "speakerPhone");
            return (Criteria) this;
        }

        public Criteria andSpeakerPhoneLike(String value) {
            addCriterion("SPEAKER_PHONE like", value, "speakerPhone");
            return (Criteria) this;
        }

        public Criteria andSpeakerPhoneNotLike(String value) {
            addCriterion("SPEAKER_PHONE not like", value, "speakerPhone");
            return (Criteria) this;
        }

        public Criteria andSpeakerPhoneIn(List<String> values) {
            addCriterion("SPEAKER_PHONE in", values, "speakerPhone");
            return (Criteria) this;
        }

        public Criteria andSpeakerPhoneNotIn(List<String> values) {
            addCriterion("SPEAKER_PHONE not in", values, "speakerPhone");
            return (Criteria) this;
        }

        public Criteria andSpeakerPhoneBetween(String value1, String value2) {
            addCriterion("SPEAKER_PHONE between", value1, value2, "speakerPhone");
            return (Criteria) this;
        }

        public Criteria andSpeakerPhoneNotBetween(String value1, String value2) {
            addCriterion("SPEAKER_PHONE not between", value1, value2, "speakerPhone");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdIsNull() {
            addCriterion("ACTIVITY_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdIsNotNull() {
            addCriterion("ACTIVITY_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdEqualTo(String value) {
            addCriterion("ACTIVITY_TYPE_ID =", value, "activityTypeId");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdNotEqualTo(String value) {
            addCriterion("ACTIVITY_TYPE_ID <>", value, "activityTypeId");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdGreaterThan(String value) {
            addCriterion("ACTIVITY_TYPE_ID >", value, "activityTypeId");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_TYPE_ID >=", value, "activityTypeId");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdLessThan(String value) {
            addCriterion("ACTIVITY_TYPE_ID <", value, "activityTypeId");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdLessThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_TYPE_ID <=", value, "activityTypeId");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdLike(String value) {
            addCriterion("ACTIVITY_TYPE_ID like", value, "activityTypeId");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdNotLike(String value) {
            addCriterion("ACTIVITY_TYPE_ID not like", value, "activityTypeId");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdIn(List<String> values) {
            addCriterion("ACTIVITY_TYPE_ID in", values, "activityTypeId");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdNotIn(List<String> values) {
            addCriterion("ACTIVITY_TYPE_ID not in", values, "activityTypeId");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdBetween(String value1, String value2) {
            addCriterion("ACTIVITY_TYPE_ID between", value1, value2, "activityTypeId");
            return (Criteria) this;
        }

        public Criteria andActivityTypeIdNotBetween(String value1, String value2) {
            addCriterion("ACTIVITY_TYPE_ID not between", value1, value2, "activityTypeId");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameIsNull() {
            addCriterion("ACTIVITY_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameIsNotNull() {
            addCriterion("ACTIVITY_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameEqualTo(String value) {
            addCriterion("ACTIVITY_TYPE_NAME =", value, "activityTypeName");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameNotEqualTo(String value) {
            addCriterion("ACTIVITY_TYPE_NAME <>", value, "activityTypeName");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameGreaterThan(String value) {
            addCriterion("ACTIVITY_TYPE_NAME >", value, "activityTypeName");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_TYPE_NAME >=", value, "activityTypeName");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameLessThan(String value) {
            addCriterion("ACTIVITY_TYPE_NAME <", value, "activityTypeName");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameLessThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_TYPE_NAME <=", value, "activityTypeName");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameLike(String value) {
            addCriterion("ACTIVITY_TYPE_NAME like", value, "activityTypeName");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameNotLike(String value) {
            addCriterion("ACTIVITY_TYPE_NAME not like", value, "activityTypeName");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameIn(List<String> values) {
            addCriterion("ACTIVITY_TYPE_NAME in", values, "activityTypeName");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameNotIn(List<String> values) {
            addCriterion("ACTIVITY_TYPE_NAME not in", values, "activityTypeName");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameBetween(String value1, String value2) {
            addCriterion("ACTIVITY_TYPE_NAME between", value1, value2, "activityTypeName");
            return (Criteria) this;
        }

        public Criteria andActivityTypeNameNotBetween(String value1, String value2) {
            addCriterion("ACTIVITY_TYPE_NAME not between", value1, value2, "activityTypeName");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(String value) {
            addCriterion("START_TIME =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(String value) {
            addCriterion("START_TIME <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(String value) {
            addCriterion("START_TIME >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("START_TIME >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(String value) {
            addCriterion("START_TIME <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(String value) {
            addCriterion("START_TIME <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLike(String value) {
            addCriterion("START_TIME like", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotLike(String value) {
            addCriterion("START_TIME not like", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<String> values) {
            addCriterion("START_TIME in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<String> values) {
            addCriterion("START_TIME not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(String value1, String value2) {
            addCriterion("START_TIME between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(String value1, String value2) {
            addCriterion("START_TIME not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(String value) {
            addCriterion("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(String value) {
            addCriterion("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(String value) {
            addCriterion("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(String value) {
            addCriterion("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(String value) {
            addCriterion("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLike(String value) {
            addCriterion("END_TIME like", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotLike(String value) {
            addCriterion("END_TIME not like", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<String> values) {
            addCriterion("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<String> values) {
            addCriterion("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(String value1, String value2) {
            addCriterion("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(String value1, String value2) {
            addCriterion("END_TIME not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andActivityAddressIsNull() {
            addCriterion("ACTIVITY_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andActivityAddressIsNotNull() {
            addCriterion("ACTIVITY_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andActivityAddressEqualTo(String value) {
            addCriterion("ACTIVITY_ADDRESS =", value, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressNotEqualTo(String value) {
            addCriterion("ACTIVITY_ADDRESS <>", value, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressGreaterThan(String value) {
            addCriterion("ACTIVITY_ADDRESS >", value, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_ADDRESS >=", value, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressLessThan(String value) {
            addCriterion("ACTIVITY_ADDRESS <", value, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressLessThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_ADDRESS <=", value, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressLike(String value) {
            addCriterion("ACTIVITY_ADDRESS like", value, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressNotLike(String value) {
            addCriterion("ACTIVITY_ADDRESS not like", value, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressIn(List<String> values) {
            addCriterion("ACTIVITY_ADDRESS in", values, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressNotIn(List<String> values) {
            addCriterion("ACTIVITY_ADDRESS not in", values, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressBetween(String value1, String value2) {
            addCriterion("ACTIVITY_ADDRESS between", value1, value2, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityAddressNotBetween(String value1, String value2) {
            addCriterion("ACTIVITY_ADDRESS not between", value1, value2, "activityAddress");
            return (Criteria) this;
        }

        public Criteria andActivityRemarkIsNull() {
            addCriterion("ACTIVITY_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andActivityRemarkIsNotNull() {
            addCriterion("ACTIVITY_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andActivityRemarkEqualTo(String value) {
            addCriterion("ACTIVITY_REMARK =", value, "activityRemark");
            return (Criteria) this;
        }

        public Criteria andActivityRemarkNotEqualTo(String value) {
            addCriterion("ACTIVITY_REMARK <>", value, "activityRemark");
            return (Criteria) this;
        }

        public Criteria andActivityRemarkGreaterThan(String value) {
            addCriterion("ACTIVITY_REMARK >", value, "activityRemark");
            return (Criteria) this;
        }

        public Criteria andActivityRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_REMARK >=", value, "activityRemark");
            return (Criteria) this;
        }

        public Criteria andActivityRemarkLessThan(String value) {
            addCriterion("ACTIVITY_REMARK <", value, "activityRemark");
            return (Criteria) this;
        }

        public Criteria andActivityRemarkLessThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_REMARK <=", value, "activityRemark");
            return (Criteria) this;
        }

        public Criteria andActivityRemarkLike(String value) {
            addCriterion("ACTIVITY_REMARK like", value, "activityRemark");
            return (Criteria) this;
        }

        public Criteria andActivityRemarkNotLike(String value) {
            addCriterion("ACTIVITY_REMARK not like", value, "activityRemark");
            return (Criteria) this;
        }

        public Criteria andActivityRemarkIn(List<String> values) {
            addCriterion("ACTIVITY_REMARK in", values, "activityRemark");
            return (Criteria) this;
        }

        public Criteria andActivityRemarkNotIn(List<String> values) {
            addCriterion("ACTIVITY_REMARK not in", values, "activityRemark");
            return (Criteria) this;
        }

        public Criteria andActivityRemarkBetween(String value1, String value2) {
            addCriterion("ACTIVITY_REMARK between", value1, value2, "activityRemark");
            return (Criteria) this;
        }

        public Criteria andActivityRemarkNotBetween(String value1, String value2) {
            addCriterion("ACTIVITY_REMARK not between", value1, value2, "activityRemark");
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

        public Criteria andIsEffectiveIsNull() {
            addCriterion("IS_EFFECTIVE is null");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveIsNotNull() {
            addCriterion("IS_EFFECTIVE is not null");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveEqualTo(String value) {
            addCriterion("IS_EFFECTIVE =", value, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveNotEqualTo(String value) {
            addCriterion("IS_EFFECTIVE <>", value, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveGreaterThan(String value) {
            addCriterion("IS_EFFECTIVE >", value, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveGreaterThanOrEqualTo(String value) {
            addCriterion("IS_EFFECTIVE >=", value, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveLessThan(String value) {
            addCriterion("IS_EFFECTIVE <", value, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveLessThanOrEqualTo(String value) {
            addCriterion("IS_EFFECTIVE <=", value, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveLike(String value) {
            addCriterion("IS_EFFECTIVE like", value, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveNotLike(String value) {
            addCriterion("IS_EFFECTIVE not like", value, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveIn(List<String> values) {
            addCriterion("IS_EFFECTIVE in", values, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveNotIn(List<String> values) {
            addCriterion("IS_EFFECTIVE not in", values, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveBetween(String value1, String value2) {
            addCriterion("IS_EFFECTIVE between", value1, value2, "isEffective");
            return (Criteria) this;
        }

        public Criteria andIsEffectiveNotBetween(String value1, String value2) {
            addCriterion("IS_EFFECTIVE not between", value1, value2, "isEffective");
            return (Criteria) this;
        }

        public Criteria andRealitySpeakerIsNull() {
            addCriterion("REALITY_SPEAKER is null");
            return (Criteria) this;
        }

        public Criteria andRealitySpeakerIsNotNull() {
            addCriterion("REALITY_SPEAKER is not null");
            return (Criteria) this;
        }

        public Criteria andRealitySpeakerEqualTo(String value) {
            addCriterion("REALITY_SPEAKER =", value, "realitySpeaker");
            return (Criteria) this;
        }

        public Criteria andRealitySpeakerNotEqualTo(String value) {
            addCriterion("REALITY_SPEAKER <>", value, "realitySpeaker");
            return (Criteria) this;
        }

        public Criteria andRealitySpeakerGreaterThan(String value) {
            addCriterion("REALITY_SPEAKER >", value, "realitySpeaker");
            return (Criteria) this;
        }

        public Criteria andRealitySpeakerGreaterThanOrEqualTo(String value) {
            addCriterion("REALITY_SPEAKER >=", value, "realitySpeaker");
            return (Criteria) this;
        }

        public Criteria andRealitySpeakerLessThan(String value) {
            addCriterion("REALITY_SPEAKER <", value, "realitySpeaker");
            return (Criteria) this;
        }

        public Criteria andRealitySpeakerLessThanOrEqualTo(String value) {
            addCriterion("REALITY_SPEAKER <=", value, "realitySpeaker");
            return (Criteria) this;
        }

        public Criteria andRealitySpeakerLike(String value) {
            addCriterion("REALITY_SPEAKER like", value, "realitySpeaker");
            return (Criteria) this;
        }

        public Criteria andRealitySpeakerNotLike(String value) {
            addCriterion("REALITY_SPEAKER not like", value, "realitySpeaker");
            return (Criteria) this;
        }

        public Criteria andRealitySpeakerIn(List<String> values) {
            addCriterion("REALITY_SPEAKER in", values, "realitySpeaker");
            return (Criteria) this;
        }

        public Criteria andRealitySpeakerNotIn(List<String> values) {
            addCriterion("REALITY_SPEAKER not in", values, "realitySpeaker");
            return (Criteria) this;
        }

        public Criteria andRealitySpeakerBetween(String value1, String value2) {
            addCriterion("REALITY_SPEAKER between", value1, value2, "realitySpeaker");
            return (Criteria) this;
        }

        public Criteria andRealitySpeakerNotBetween(String value1, String value2) {
            addCriterion("REALITY_SPEAKER not between", value1, value2, "realitySpeaker");
            return (Criteria) this;
        }

        public Criteria andActivityStatusIsNull() {
            addCriterion("ACTIVITY_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andActivityStatusIsNotNull() {
            addCriterion("ACTIVITY_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andActivityStatusEqualTo(String value) {
            addCriterion("ACTIVITY_STATUS =", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusNotEqualTo(String value) {
            addCriterion("ACTIVITY_STATUS <>", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusGreaterThan(String value) {
            addCriterion("ACTIVITY_STATUS >", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusGreaterThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_STATUS >=", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusLessThan(String value) {
            addCriterion("ACTIVITY_STATUS <", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusLessThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_STATUS <=", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusLike(String value) {
            addCriterion("ACTIVITY_STATUS like", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusNotLike(String value) {
            addCriterion("ACTIVITY_STATUS not like", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusIn(List<String> values) {
            addCriterion("ACTIVITY_STATUS in", values, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusNotIn(List<String> values) {
            addCriterion("ACTIVITY_STATUS not in", values, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusBetween(String value1, String value2) {
            addCriterion("ACTIVITY_STATUS between", value1, value2, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusNotBetween(String value1, String value2) {
            addCriterion("ACTIVITY_STATUS not between", value1, value2, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleIsNull() {
            addCriterion("SUBMIT_ROLE is null");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleIsNotNull() {
            addCriterion("SUBMIT_ROLE is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleEqualTo(String value) {
            addCriterion("SUBMIT_ROLE =", value, "submitRole");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleNotEqualTo(String value) {
            addCriterion("SUBMIT_ROLE <>", value, "submitRole");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleGreaterThan(String value) {
            addCriterion("SUBMIT_ROLE >", value, "submitRole");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleGreaterThanOrEqualTo(String value) {
            addCriterion("SUBMIT_ROLE >=", value, "submitRole");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleLessThan(String value) {
            addCriterion("SUBMIT_ROLE <", value, "submitRole");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleLessThanOrEqualTo(String value) {
            addCriterion("SUBMIT_ROLE <=", value, "submitRole");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleLike(String value) {
            addCriterion("SUBMIT_ROLE like", value, "submitRole");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleNotLike(String value) {
            addCriterion("SUBMIT_ROLE not like", value, "submitRole");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleIn(List<String> values) {
            addCriterion("SUBMIT_ROLE in", values, "submitRole");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleNotIn(List<String> values) {
            addCriterion("SUBMIT_ROLE not in", values, "submitRole");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleBetween(String value1, String value2) {
            addCriterion("SUBMIT_ROLE between", value1, value2, "submitRole");
            return (Criteria) this;
        }

        public Criteria andSubmitRoleNotBetween(String value1, String value2) {
            addCriterion("SUBMIT_ROLE not between", value1, value2, "submitRole");
            return (Criteria) this;
        }

        public Criteria andAuditRoleIsNull() {
            addCriterion("AUDIT_ROLE is null");
            return (Criteria) this;
        }

        public Criteria andAuditRoleIsNotNull() {
            addCriterion("AUDIT_ROLE is not null");
            return (Criteria) this;
        }

        public Criteria andAuditRoleEqualTo(String value) {
            addCriterion("AUDIT_ROLE =", value, "auditRole");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNotEqualTo(String value) {
            addCriterion("AUDIT_ROLE <>", value, "auditRole");
            return (Criteria) this;
        }

        public Criteria andAuditRoleGreaterThan(String value) {
            addCriterion("AUDIT_ROLE >", value, "auditRole");
            return (Criteria) this;
        }

        public Criteria andAuditRoleGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_ROLE >=", value, "auditRole");
            return (Criteria) this;
        }

        public Criteria andAuditRoleLessThan(String value) {
            addCriterion("AUDIT_ROLE <", value, "auditRole");
            return (Criteria) this;
        }

        public Criteria andAuditRoleLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_ROLE <=", value, "auditRole");
            return (Criteria) this;
        }

        public Criteria andAuditRoleLike(String value) {
            addCriterion("AUDIT_ROLE like", value, "auditRole");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNotLike(String value) {
            addCriterion("AUDIT_ROLE not like", value, "auditRole");
            return (Criteria) this;
        }

        public Criteria andAuditRoleIn(List<String> values) {
            addCriterion("AUDIT_ROLE in", values, "auditRole");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNotIn(List<String> values) {
            addCriterion("AUDIT_ROLE not in", values, "auditRole");
            return (Criteria) this;
        }

        public Criteria andAuditRoleBetween(String value1, String value2) {
            addCriterion("AUDIT_ROLE between", value1, value2, "auditRole");
            return (Criteria) this;
        }

        public Criteria andAuditRoleNotBetween(String value1, String value2) {
            addCriterion("AUDIT_ROLE not between", value1, value2, "auditRole");
            return (Criteria) this;
        }

        public Criteria andOpinionIsNull() {
            addCriterion("OPINION is null");
            return (Criteria) this;
        }

        public Criteria andOpinionIsNotNull() {
            addCriterion("OPINION is not null");
            return (Criteria) this;
        }

        public Criteria andOpinionEqualTo(String value) {
            addCriterion("OPINION =", value, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionNotEqualTo(String value) {
            addCriterion("OPINION <>", value, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionGreaterThan(String value) {
            addCriterion("OPINION >", value, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionGreaterThanOrEqualTo(String value) {
            addCriterion("OPINION >=", value, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionLessThan(String value) {
            addCriterion("OPINION <", value, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionLessThanOrEqualTo(String value) {
            addCriterion("OPINION <=", value, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionLike(String value) {
            addCriterion("OPINION like", value, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionNotLike(String value) {
            addCriterion("OPINION not like", value, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionIn(List<String> values) {
            addCriterion("OPINION in", values, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionNotIn(List<String> values) {
            addCriterion("OPINION not in", values, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionBetween(String value1, String value2) {
            addCriterion("OPINION between", value1, value2, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionNotBetween(String value1, String value2) {
            addCriterion("OPINION not between", value1, value2, "opinion");
            return (Criteria) this;
        }

        public Criteria andManualEndStatusIsNull() {
            addCriterion("MANUAL_END_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andManualEndStatusIsNotNull() {
            addCriterion("MANUAL_END_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andManualEndStatusEqualTo(String value) {
            addCriterion("MANUAL_END_STATUS =", value, "manualEndStatus");
            return (Criteria) this;
        }

        public Criteria andManualEndStatusNotEqualTo(String value) {
            addCriterion("MANUAL_END_STATUS <>", value, "manualEndStatus");
            return (Criteria) this;
        }

        public Criteria andManualEndStatusGreaterThan(String value) {
            addCriterion("MANUAL_END_STATUS >", value, "manualEndStatus");
            return (Criteria) this;
        }

        public Criteria andManualEndStatusGreaterThanOrEqualTo(String value) {
            addCriterion("MANUAL_END_STATUS >=", value, "manualEndStatus");
            return (Criteria) this;
        }

        public Criteria andManualEndStatusLessThan(String value) {
            addCriterion("MANUAL_END_STATUS <", value, "manualEndStatus");
            return (Criteria) this;
        }

        public Criteria andManualEndStatusLessThanOrEqualTo(String value) {
            addCriterion("MANUAL_END_STATUS <=", value, "manualEndStatus");
            return (Criteria) this;
        }

        public Criteria andManualEndStatusLike(String value) {
            addCriterion("MANUAL_END_STATUS like", value, "manualEndStatus");
            return (Criteria) this;
        }

        public Criteria andManualEndStatusNotLike(String value) {
            addCriterion("MANUAL_END_STATUS not like", value, "manualEndStatus");
            return (Criteria) this;
        }

        public Criteria andManualEndStatusIn(List<String> values) {
            addCriterion("MANUAL_END_STATUS in", values, "manualEndStatus");
            return (Criteria) this;
        }

        public Criteria andManualEndStatusNotIn(List<String> values) {
            addCriterion("MANUAL_END_STATUS not in", values, "manualEndStatus");
            return (Criteria) this;
        }

        public Criteria andManualEndStatusBetween(String value1, String value2) {
            addCriterion("MANUAL_END_STATUS between", value1, value2, "manualEndStatus");
            return (Criteria) this;
        }

        public Criteria andManualEndStatusNotBetween(String value1, String value2) {
            addCriterion("MANUAL_END_STATUS not between", value1, value2, "manualEndStatus");
            return (Criteria) this;
        }

        public Criteria andManualEndTiemIsNull() {
            addCriterion("MANUAL_END_TIEM is null");
            return (Criteria) this;
        }

        public Criteria andManualEndTiemIsNotNull() {
            addCriterion("MANUAL_END_TIEM is not null");
            return (Criteria) this;
        }

        public Criteria andManualEndTiemEqualTo(String value) {
            addCriterion("MANUAL_END_TIEM =", value, "manualEndTiem");
            return (Criteria) this;
        }

        public Criteria andManualEndTiemNotEqualTo(String value) {
            addCriterion("MANUAL_END_TIEM <>", value, "manualEndTiem");
            return (Criteria) this;
        }

        public Criteria andManualEndTiemGreaterThan(String value) {
            addCriterion("MANUAL_END_TIEM >", value, "manualEndTiem");
            return (Criteria) this;
        }

        public Criteria andManualEndTiemGreaterThanOrEqualTo(String value) {
            addCriterion("MANUAL_END_TIEM >=", value, "manualEndTiem");
            return (Criteria) this;
        }

        public Criteria andManualEndTiemLessThan(String value) {
            addCriterion("MANUAL_END_TIEM <", value, "manualEndTiem");
            return (Criteria) this;
        }

        public Criteria andManualEndTiemLessThanOrEqualTo(String value) {
            addCriterion("MANUAL_END_TIEM <=", value, "manualEndTiem");
            return (Criteria) this;
        }

        public Criteria andManualEndTiemLike(String value) {
            addCriterion("MANUAL_END_TIEM like", value, "manualEndTiem");
            return (Criteria) this;
        }

        public Criteria andManualEndTiemNotLike(String value) {
            addCriterion("MANUAL_END_TIEM not like", value, "manualEndTiem");
            return (Criteria) this;
        }

        public Criteria andManualEndTiemIn(List<String> values) {
            addCriterion("MANUAL_END_TIEM in", values, "manualEndTiem");
            return (Criteria) this;
        }

        public Criteria andManualEndTiemNotIn(List<String> values) {
            addCriterion("MANUAL_END_TIEM not in", values, "manualEndTiem");
            return (Criteria) this;
        }

        public Criteria andManualEndTiemBetween(String value1, String value2) {
            addCriterion("MANUAL_END_TIEM between", value1, value2, "manualEndTiem");
            return (Criteria) this;
        }

        public Criteria andManualEndTiemNotBetween(String value1, String value2) {
            addCriterion("MANUAL_END_TIEM not between", value1, value2, "manualEndTiem");
            return (Criteria) this;
        }

        public Criteria andJoinNumIsNull() {
            addCriterion("JOIN_NUM is null");
            return (Criteria) this;
        }

        public Criteria andJoinNumIsNotNull() {
            addCriterion("JOIN_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andJoinNumEqualTo(BigDecimal value) {
            addCriterion("JOIN_NUM =", value, "joinNum");
            return (Criteria) this;
        }

        public Criteria andJoinNumNotEqualTo(BigDecimal value) {
            addCriterion("JOIN_NUM <>", value, "joinNum");
            return (Criteria) this;
        }

        public Criteria andJoinNumGreaterThan(BigDecimal value) {
            addCriterion("JOIN_NUM >", value, "joinNum");
            return (Criteria) this;
        }

        public Criteria andJoinNumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("JOIN_NUM >=", value, "joinNum");
            return (Criteria) this;
        }

        public Criteria andJoinNumLessThan(BigDecimal value) {
            addCriterion("JOIN_NUM <", value, "joinNum");
            return (Criteria) this;
        }

        public Criteria andJoinNumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("JOIN_NUM <=", value, "joinNum");
            return (Criteria) this;
        }

        public Criteria andJoinNumIn(List<BigDecimal> values) {
            addCriterion("JOIN_NUM in", values, "joinNum");
            return (Criteria) this;
        }

        public Criteria andJoinNumNotIn(List<BigDecimal> values) {
            addCriterion("JOIN_NUM not in", values, "joinNum");
            return (Criteria) this;
        }

        public Criteria andJoinNumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("JOIN_NUM between", value1, value2, "joinNum");
            return (Criteria) this;
        }

        public Criteria andJoinNumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("JOIN_NUM not between", value1, value2, "joinNum");
            return (Criteria) this;
        }

        public Criteria andAddRoleFlowIsNull() {
            addCriterion("ADD_ROLE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAddRoleFlowIsNotNull() {
            addCriterion("ADD_ROLE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAddRoleFlowEqualTo(String value) {
            addCriterion("ADD_ROLE_FLOW =", value, "addRoleFlow");
            return (Criteria) this;
        }

        public Criteria andAddRoleFlowNotEqualTo(String value) {
            addCriterion("ADD_ROLE_FLOW <>", value, "addRoleFlow");
            return (Criteria) this;
        }

        public Criteria andAddRoleFlowGreaterThan(String value) {
            addCriterion("ADD_ROLE_FLOW >", value, "addRoleFlow");
            return (Criteria) this;
        }

        public Criteria andAddRoleFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ADD_ROLE_FLOW >=", value, "addRoleFlow");
            return (Criteria) this;
        }

        public Criteria andAddRoleFlowLessThan(String value) {
            addCriterion("ADD_ROLE_FLOW <", value, "addRoleFlow");
            return (Criteria) this;
        }

        public Criteria andAddRoleFlowLessThanOrEqualTo(String value) {
            addCriterion("ADD_ROLE_FLOW <=", value, "addRoleFlow");
            return (Criteria) this;
        }

        public Criteria andAddRoleFlowLike(String value) {
            addCriterion("ADD_ROLE_FLOW like", value, "addRoleFlow");
            return (Criteria) this;
        }

        public Criteria andAddRoleFlowNotLike(String value) {
            addCriterion("ADD_ROLE_FLOW not like", value, "addRoleFlow");
            return (Criteria) this;
        }

        public Criteria andAddRoleFlowIn(List<String> values) {
            addCriterion("ADD_ROLE_FLOW in", values, "addRoleFlow");
            return (Criteria) this;
        }

        public Criteria andAddRoleFlowNotIn(List<String> values) {
            addCriterion("ADD_ROLE_FLOW not in", values, "addRoleFlow");
            return (Criteria) this;
        }

        public Criteria andAddRoleFlowBetween(String value1, String value2) {
            addCriterion("ADD_ROLE_FLOW between", value1, value2, "addRoleFlow");
            return (Criteria) this;
        }

        public Criteria andAddRoleFlowNotBetween(String value1, String value2) {
            addCriterion("ADD_ROLE_FLOW not between", value1, value2, "addRoleFlow");
            return (Criteria) this;
        }

        public Criteria andAuditRoleFlowIsNull() {
            addCriterion("AUDIT_ROLE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAuditRoleFlowIsNotNull() {
            addCriterion("AUDIT_ROLE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAuditRoleFlowEqualTo(String value) {
            addCriterion("AUDIT_ROLE_FLOW =", value, "auditRoleFlow");
            return (Criteria) this;
        }

        public Criteria andAuditRoleFlowNotEqualTo(String value) {
            addCriterion("AUDIT_ROLE_FLOW <>", value, "auditRoleFlow");
            return (Criteria) this;
        }

        public Criteria andAuditRoleFlowGreaterThan(String value) {
            addCriterion("AUDIT_ROLE_FLOW >", value, "auditRoleFlow");
            return (Criteria) this;
        }

        public Criteria andAuditRoleFlowGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_ROLE_FLOW >=", value, "auditRoleFlow");
            return (Criteria) this;
        }

        public Criteria andAuditRoleFlowLessThan(String value) {
            addCriterion("AUDIT_ROLE_FLOW <", value, "auditRoleFlow");
            return (Criteria) this;
        }

        public Criteria andAuditRoleFlowLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_ROLE_FLOW <=", value, "auditRoleFlow");
            return (Criteria) this;
        }

        public Criteria andAuditRoleFlowLike(String value) {
            addCriterion("AUDIT_ROLE_FLOW like", value, "auditRoleFlow");
            return (Criteria) this;
        }

        public Criteria andAuditRoleFlowNotLike(String value) {
            addCriterion("AUDIT_ROLE_FLOW not like", value, "auditRoleFlow");
            return (Criteria) this;
        }

        public Criteria andAuditRoleFlowIn(List<String> values) {
            addCriterion("AUDIT_ROLE_FLOW in", values, "auditRoleFlow");
            return (Criteria) this;
        }

        public Criteria andAuditRoleFlowNotIn(List<String> values) {
            addCriterion("AUDIT_ROLE_FLOW not in", values, "auditRoleFlow");
            return (Criteria) this;
        }

        public Criteria andAuditRoleFlowBetween(String value1, String value2) {
            addCriterion("AUDIT_ROLE_FLOW between", value1, value2, "auditRoleFlow");
            return (Criteria) this;
        }

        public Criteria andAuditRoleFlowNotBetween(String value1, String value2) {
            addCriterion("AUDIT_ROLE_FLOW not between", value1, value2, "auditRoleFlow");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNull() {
            addCriterion("ROLE_ID is null");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("ROLE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRoleIdEqualTo(String value) {
            addCriterion("ROLE_ID =", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotEqualTo(String value) {
            addCriterion("ROLE_ID <>", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThan(String value) {
            addCriterion("ROLE_ID >", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(String value) {
            addCriterion("ROLE_ID >=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThan(String value) {
            addCriterion("ROLE_ID <", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(String value) {
            addCriterion("ROLE_ID <=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLike(String value) {
            addCriterion("ROLE_ID like", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotLike(String value) {
            addCriterion("ROLE_ID not like", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIn(List<String> values) {
            addCriterion("ROLE_ID in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotIn(List<String> values) {
            addCriterion("ROLE_ID not in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdBetween(String value1, String value2) {
            addCriterion("ROLE_ID between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotBetween(String value1, String value2) {
            addCriterion("ROLE_ID not between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andLimitNumIsNull() {
            addCriterion("LIMIT_NUM is null");
            return (Criteria) this;
        }

        public Criteria andLimitNumIsNotNull() {
            addCriterion("LIMIT_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andLimitNumEqualTo(String value) {
            addCriterion("LIMIT_NUM =", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumNotEqualTo(String value) {
            addCriterion("LIMIT_NUM <>", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumGreaterThan(String value) {
            addCriterion("LIMIT_NUM >", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumGreaterThanOrEqualTo(String value) {
            addCriterion("LIMIT_NUM >=", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumLessThan(String value) {
            addCriterion("LIMIT_NUM <", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumLessThanOrEqualTo(String value) {
            addCriterion("LIMIT_NUM <=", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumLike(String value) {
            addCriterion("LIMIT_NUM like", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumNotLike(String value) {
            addCriterion("LIMIT_NUM not like", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumIn(List<String> values) {
            addCriterion("LIMIT_NUM in", values, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumNotIn(List<String> values) {
            addCriterion("LIMIT_NUM not in", values, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumBetween(String value1, String value2) {
            addCriterion("LIMIT_NUM between", value1, value2, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumNotBetween(String value1, String value2) {
            addCriterion("LIMIT_NUM not between", value1, value2, "limitNum");
            return (Criteria) this;
        }

        public Criteria andReasonForDisagreementIsNull() {
            addCriterion("REASON_FOR_DISAGREEMENT is null");
            return (Criteria) this;
        }

        public Criteria andReasonForDisagreementIsNotNull() {
            addCriterion("REASON_FOR_DISAGREEMENT is not null");
            return (Criteria) this;
        }

        public Criteria andReasonForDisagreementEqualTo(String value) {
            addCriterion("REASON_FOR_DISAGREEMENT =", value, "reasonForDisagreement");
            return (Criteria) this;
        }

        public Criteria andReasonForDisagreementNotEqualTo(String value) {
            addCriterion("REASON_FOR_DISAGREEMENT <>", value, "reasonForDisagreement");
            return (Criteria) this;
        }

        public Criteria andReasonForDisagreementGreaterThan(String value) {
            addCriterion("REASON_FOR_DISAGREEMENT >", value, "reasonForDisagreement");
            return (Criteria) this;
        }

        public Criteria andReasonForDisagreementGreaterThanOrEqualTo(String value) {
            addCriterion("REASON_FOR_DISAGREEMENT >=", value, "reasonForDisagreement");
            return (Criteria) this;
        }

        public Criteria andReasonForDisagreementLessThan(String value) {
            addCriterion("REASON_FOR_DISAGREEMENT <", value, "reasonForDisagreement");
            return (Criteria) this;
        }

        public Criteria andReasonForDisagreementLessThanOrEqualTo(String value) {
            addCriterion("REASON_FOR_DISAGREEMENT <=", value, "reasonForDisagreement");
            return (Criteria) this;
        }

        public Criteria andReasonForDisagreementLike(String value) {
            addCriterion("REASON_FOR_DISAGREEMENT like", value, "reasonForDisagreement");
            return (Criteria) this;
        }

        public Criteria andReasonForDisagreementNotLike(String value) {
            addCriterion("REASON_FOR_DISAGREEMENT not like", value, "reasonForDisagreement");
            return (Criteria) this;
        }

        public Criteria andReasonForDisagreementIn(List<String> values) {
            addCriterion("REASON_FOR_DISAGREEMENT in", values, "reasonForDisagreement");
            return (Criteria) this;
        }

        public Criteria andReasonForDisagreementNotIn(List<String> values) {
            addCriterion("REASON_FOR_DISAGREEMENT not in", values, "reasonForDisagreement");
            return (Criteria) this;
        }

        public Criteria andReasonForDisagreementBetween(String value1, String value2) {
            addCriterion("REASON_FOR_DISAGREEMENT between", value1, value2, "reasonForDisagreement");
            return (Criteria) this;
        }

        public Criteria andReasonForDisagreementNotBetween(String value1, String value2) {
            addCriterion("REASON_FOR_DISAGREEMENT not between", value1, value2, "reasonForDisagreement");
            return (Criteria) this;
        }

        public Criteria andIsLookIsNull() {
            addCriterion("IS_LOOK is null");
            return (Criteria) this;
        }

        public Criteria andIsLookIsNotNull() {
            addCriterion("IS_LOOK is not null");
            return (Criteria) this;
        }

        public Criteria andIsLookEqualTo(String value) {
            addCriterion("IS_LOOK =", value, "isLook");
            return (Criteria) this;
        }

        public Criteria andIsLookNotEqualTo(String value) {
            addCriterion("IS_LOOK <>", value, "isLook");
            return (Criteria) this;
        }

        public Criteria andIsLookGreaterThan(String value) {
            addCriterion("IS_LOOK >", value, "isLook");
            return (Criteria) this;
        }

        public Criteria andIsLookGreaterThanOrEqualTo(String value) {
            addCriterion("IS_LOOK >=", value, "isLook");
            return (Criteria) this;
        }

        public Criteria andIsLookLessThan(String value) {
            addCriterion("IS_LOOK <", value, "isLook");
            return (Criteria) this;
        }

        public Criteria andIsLookLessThanOrEqualTo(String value) {
            addCriterion("IS_LOOK <=", value, "isLook");
            return (Criteria) this;
        }

        public Criteria andIsLookLike(String value) {
            addCriterion("IS_LOOK like", value, "isLook");
            return (Criteria) this;
        }

        public Criteria andIsLookNotLike(String value) {
            addCriterion("IS_LOOK not like", value, "isLook");
            return (Criteria) this;
        }

        public Criteria andIsLookIn(List<String> values) {
            addCriterion("IS_LOOK in", values, "isLook");
            return (Criteria) this;
        }

        public Criteria andIsLookNotIn(List<String> values) {
            addCriterion("IS_LOOK not in", values, "isLook");
            return (Criteria) this;
        }

        public Criteria andIsLookBetween(String value1, String value2) {
            addCriterion("IS_LOOK between", value1, value2, "isLook");
            return (Criteria) this;
        }

        public Criteria andIsLookNotBetween(String value1, String value2) {
            addCriterion("IS_LOOK not between", value1, value2, "isLook");
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