package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class SrmExpertGroupExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SrmExpertGroupExample() {
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

        public Criteria andGroupTypeIdIsNull() {
            addCriterion("GROUP_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdIsNotNull() {
            addCriterion("GROUP_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdEqualTo(String value) {
            addCriterion("GROUP_TYPE_ID =", value, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdNotEqualTo(String value) {
            addCriterion("GROUP_TYPE_ID <>", value, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdGreaterThan(String value) {
            addCriterion("GROUP_TYPE_ID >", value, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_TYPE_ID >=", value, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdLessThan(String value) {
            addCriterion("GROUP_TYPE_ID <", value, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdLessThanOrEqualTo(String value) {
            addCriterion("GROUP_TYPE_ID <=", value, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdLike(String value) {
            addCriterion("GROUP_TYPE_ID like", value, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdNotLike(String value) {
            addCriterion("GROUP_TYPE_ID not like", value, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdIn(List<String> values) {
            addCriterion("GROUP_TYPE_ID in", values, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdNotIn(List<String> values) {
            addCriterion("GROUP_TYPE_ID not in", values, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdBetween(String value1, String value2) {
            addCriterion("GROUP_TYPE_ID between", value1, value2, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIdNotBetween(String value1, String value2) {
            addCriterion("GROUP_TYPE_ID not between", value1, value2, "groupTypeId");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameIsNull() {
            addCriterion("GROUP_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameIsNotNull() {
            addCriterion("GROUP_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameEqualTo(String value) {
            addCriterion("GROUP_TYPE_NAME =", value, "groupTypeName");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameNotEqualTo(String value) {
            addCriterion("GROUP_TYPE_NAME <>", value, "groupTypeName");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameGreaterThan(String value) {
            addCriterion("GROUP_TYPE_NAME >", value, "groupTypeName");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_TYPE_NAME >=", value, "groupTypeName");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameLessThan(String value) {
            addCriterion("GROUP_TYPE_NAME <", value, "groupTypeName");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameLessThanOrEqualTo(String value) {
            addCriterion("GROUP_TYPE_NAME <=", value, "groupTypeName");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameLike(String value) {
            addCriterion("GROUP_TYPE_NAME like", value, "groupTypeName");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameNotLike(String value) {
            addCriterion("GROUP_TYPE_NAME not like", value, "groupTypeName");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameIn(List<String> values) {
            addCriterion("GROUP_TYPE_NAME in", values, "groupTypeName");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameNotIn(List<String> values) {
            addCriterion("GROUP_TYPE_NAME not in", values, "groupTypeName");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameBetween(String value1, String value2) {
            addCriterion("GROUP_TYPE_NAME between", value1, value2, "groupTypeName");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNameNotBetween(String value1, String value2) {
            addCriterion("GROUP_TYPE_NAME not between", value1, value2, "groupTypeName");
            return (Criteria) this;
        }

        public Criteria andGroupNoteIsNull() {
            addCriterion("GROUP_NOTE is null");
            return (Criteria) this;
        }

        public Criteria andGroupNoteIsNotNull() {
            addCriterion("GROUP_NOTE is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNoteEqualTo(String value) {
            addCriterion("GROUP_NOTE =", value, "groupNote");
            return (Criteria) this;
        }

        public Criteria andGroupNoteNotEqualTo(String value) {
            addCriterion("GROUP_NOTE <>", value, "groupNote");
            return (Criteria) this;
        }

        public Criteria andGroupNoteGreaterThan(String value) {
            addCriterion("GROUP_NOTE >", value, "groupNote");
            return (Criteria) this;
        }

        public Criteria andGroupNoteGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_NOTE >=", value, "groupNote");
            return (Criteria) this;
        }

        public Criteria andGroupNoteLessThan(String value) {
            addCriterion("GROUP_NOTE <", value, "groupNote");
            return (Criteria) this;
        }

        public Criteria andGroupNoteLessThanOrEqualTo(String value) {
            addCriterion("GROUP_NOTE <=", value, "groupNote");
            return (Criteria) this;
        }

        public Criteria andGroupNoteLike(String value) {
            addCriterion("GROUP_NOTE like", value, "groupNote");
            return (Criteria) this;
        }

        public Criteria andGroupNoteNotLike(String value) {
            addCriterion("GROUP_NOTE not like", value, "groupNote");
            return (Criteria) this;
        }

        public Criteria andGroupNoteIn(List<String> values) {
            addCriterion("GROUP_NOTE in", values, "groupNote");
            return (Criteria) this;
        }

        public Criteria andGroupNoteNotIn(List<String> values) {
            addCriterion("GROUP_NOTE not in", values, "groupNote");
            return (Criteria) this;
        }

        public Criteria andGroupNoteBetween(String value1, String value2) {
            addCriterion("GROUP_NOTE between", value1, value2, "groupNote");
            return (Criteria) this;
        }

        public Criteria andGroupNoteNotBetween(String value1, String value2) {
            addCriterion("GROUP_NOTE not between", value1, value2, "groupNote");
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

        public Criteria andMeetingStartTimeIsNull() {
            addCriterion("MEETING_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andMeetingStartTimeIsNotNull() {
            addCriterion("MEETING_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andMeetingStartTimeEqualTo(String value) {
            addCriterion("MEETING_START_TIME =", value, "meetingStartTime");
            return (Criteria) this;
        }

        public Criteria andMeetingStartTimeNotEqualTo(String value) {
            addCriterion("MEETING_START_TIME <>", value, "meetingStartTime");
            return (Criteria) this;
        }

        public Criteria andMeetingStartTimeGreaterThan(String value) {
            addCriterion("MEETING_START_TIME >", value, "meetingStartTime");
            return (Criteria) this;
        }

        public Criteria andMeetingStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("MEETING_START_TIME >=", value, "meetingStartTime");
            return (Criteria) this;
        }

        public Criteria andMeetingStartTimeLessThan(String value) {
            addCriterion("MEETING_START_TIME <", value, "meetingStartTime");
            return (Criteria) this;
        }

        public Criteria andMeetingStartTimeLessThanOrEqualTo(String value) {
            addCriterion("MEETING_START_TIME <=", value, "meetingStartTime");
            return (Criteria) this;
        }

        public Criteria andMeetingStartTimeLike(String value) {
            addCriterion("MEETING_START_TIME like", value, "meetingStartTime");
            return (Criteria) this;
        }

        public Criteria andMeetingStartTimeNotLike(String value) {
            addCriterion("MEETING_START_TIME not like", value, "meetingStartTime");
            return (Criteria) this;
        }

        public Criteria andMeetingStartTimeIn(List<String> values) {
            addCriterion("MEETING_START_TIME in", values, "meetingStartTime");
            return (Criteria) this;
        }

        public Criteria andMeetingStartTimeNotIn(List<String> values) {
            addCriterion("MEETING_START_TIME not in", values, "meetingStartTime");
            return (Criteria) this;
        }

        public Criteria andMeetingStartTimeBetween(String value1, String value2) {
            addCriterion("MEETING_START_TIME between", value1, value2, "meetingStartTime");
            return (Criteria) this;
        }

        public Criteria andMeetingStartTimeNotBetween(String value1, String value2) {
            addCriterion("MEETING_START_TIME not between", value1, value2, "meetingStartTime");
            return (Criteria) this;
        }

        public Criteria andMeetingEndTimeIsNull() {
            addCriterion("MEETING_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andMeetingEndTimeIsNotNull() {
            addCriterion("MEETING_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andMeetingEndTimeEqualTo(String value) {
            addCriterion("MEETING_END_TIME =", value, "meetingEndTime");
            return (Criteria) this;
        }

        public Criteria andMeetingEndTimeNotEqualTo(String value) {
            addCriterion("MEETING_END_TIME <>", value, "meetingEndTime");
            return (Criteria) this;
        }

        public Criteria andMeetingEndTimeGreaterThan(String value) {
            addCriterion("MEETING_END_TIME >", value, "meetingEndTime");
            return (Criteria) this;
        }

        public Criteria andMeetingEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("MEETING_END_TIME >=", value, "meetingEndTime");
            return (Criteria) this;
        }

        public Criteria andMeetingEndTimeLessThan(String value) {
            addCriterion("MEETING_END_TIME <", value, "meetingEndTime");
            return (Criteria) this;
        }

        public Criteria andMeetingEndTimeLessThanOrEqualTo(String value) {
            addCriterion("MEETING_END_TIME <=", value, "meetingEndTime");
            return (Criteria) this;
        }

        public Criteria andMeetingEndTimeLike(String value) {
            addCriterion("MEETING_END_TIME like", value, "meetingEndTime");
            return (Criteria) this;
        }

        public Criteria andMeetingEndTimeNotLike(String value) {
            addCriterion("MEETING_END_TIME not like", value, "meetingEndTime");
            return (Criteria) this;
        }

        public Criteria andMeetingEndTimeIn(List<String> values) {
            addCriterion("MEETING_END_TIME in", values, "meetingEndTime");
            return (Criteria) this;
        }

        public Criteria andMeetingEndTimeNotIn(List<String> values) {
            addCriterion("MEETING_END_TIME not in", values, "meetingEndTime");
            return (Criteria) this;
        }

        public Criteria andMeetingEndTimeBetween(String value1, String value2) {
            addCriterion("MEETING_END_TIME between", value1, value2, "meetingEndTime");
            return (Criteria) this;
        }

        public Criteria andMeetingEndTimeNotBetween(String value1, String value2) {
            addCriterion("MEETING_END_TIME not between", value1, value2, "meetingEndTime");
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

        public Criteria andEvaluationWayIdIsNull() {
            addCriterion("EVALUATION_WAY_ID is null");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdIsNotNull() {
            addCriterion("EVALUATION_WAY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdEqualTo(String value) {
            addCriterion("EVALUATION_WAY_ID =", value, "evaluationWayId");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdNotEqualTo(String value) {
            addCriterion("EVALUATION_WAY_ID <>", value, "evaluationWayId");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdGreaterThan(String value) {
            addCriterion("EVALUATION_WAY_ID >", value, "evaluationWayId");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdGreaterThanOrEqualTo(String value) {
            addCriterion("EVALUATION_WAY_ID >=", value, "evaluationWayId");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdLessThan(String value) {
            addCriterion("EVALUATION_WAY_ID <", value, "evaluationWayId");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdLessThanOrEqualTo(String value) {
            addCriterion("EVALUATION_WAY_ID <=", value, "evaluationWayId");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdLike(String value) {
            addCriterion("EVALUATION_WAY_ID like", value, "evaluationWayId");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdNotLike(String value) {
            addCriterion("EVALUATION_WAY_ID not like", value, "evaluationWayId");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdIn(List<String> values) {
            addCriterion("EVALUATION_WAY_ID in", values, "evaluationWayId");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdNotIn(List<String> values) {
            addCriterion("EVALUATION_WAY_ID not in", values, "evaluationWayId");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdBetween(String value1, String value2) {
            addCriterion("EVALUATION_WAY_ID between", value1, value2, "evaluationWayId");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayIdNotBetween(String value1, String value2) {
            addCriterion("EVALUATION_WAY_ID not between", value1, value2, "evaluationWayId");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameIsNull() {
            addCriterion("EVALUATION_WAY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameIsNotNull() {
            addCriterion("EVALUATION_WAY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameEqualTo(String value) {
            addCriterion("EVALUATION_WAY_NAME =", value, "evaluationWayName");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameNotEqualTo(String value) {
            addCriterion("EVALUATION_WAY_NAME <>", value, "evaluationWayName");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameGreaterThan(String value) {
            addCriterion("EVALUATION_WAY_NAME >", value, "evaluationWayName");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameGreaterThanOrEqualTo(String value) {
            addCriterion("EVALUATION_WAY_NAME >=", value, "evaluationWayName");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameLessThan(String value) {
            addCriterion("EVALUATION_WAY_NAME <", value, "evaluationWayName");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameLessThanOrEqualTo(String value) {
            addCriterion("EVALUATION_WAY_NAME <=", value, "evaluationWayName");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameLike(String value) {
            addCriterion("EVALUATION_WAY_NAME like", value, "evaluationWayName");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameNotLike(String value) {
            addCriterion("EVALUATION_WAY_NAME not like", value, "evaluationWayName");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameIn(List<String> values) {
            addCriterion("EVALUATION_WAY_NAME in", values, "evaluationWayName");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameNotIn(List<String> values) {
            addCriterion("EVALUATION_WAY_NAME not in", values, "evaluationWayName");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameBetween(String value1, String value2) {
            addCriterion("EVALUATION_WAY_NAME between", value1, value2, "evaluationWayName");
            return (Criteria) this;
        }

        public Criteria andEvaluationWayNameNotBetween(String value1, String value2) {
            addCriterion("EVALUATION_WAY_NAME not between", value1, value2, "evaluationWayName");
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