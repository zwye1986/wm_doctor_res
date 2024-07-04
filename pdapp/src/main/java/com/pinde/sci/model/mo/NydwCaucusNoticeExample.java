package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class NydwCaucusNoticeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NydwCaucusNoticeExample() {
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

        public Criteria andNoticeNameIsNull() {
            addCriterion("NOTICE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andNoticeNameIsNotNull() {
            addCriterion("NOTICE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeNameEqualTo(String value) {
            addCriterion("NOTICE_NAME =", value, "noticeName");
            return (Criteria) this;
        }

        public Criteria andNoticeNameNotEqualTo(String value) {
            addCriterion("NOTICE_NAME <>", value, "noticeName");
            return (Criteria) this;
        }

        public Criteria andNoticeNameGreaterThan(String value) {
            addCriterion("NOTICE_NAME >", value, "noticeName");
            return (Criteria) this;
        }

        public Criteria andNoticeNameGreaterThanOrEqualTo(String value) {
            addCriterion("NOTICE_NAME >=", value, "noticeName");
            return (Criteria) this;
        }

        public Criteria andNoticeNameLessThan(String value) {
            addCriterion("NOTICE_NAME <", value, "noticeName");
            return (Criteria) this;
        }

        public Criteria andNoticeNameLessThanOrEqualTo(String value) {
            addCriterion("NOTICE_NAME <=", value, "noticeName");
            return (Criteria) this;
        }

        public Criteria andNoticeNameLike(String value) {
            addCriterion("NOTICE_NAME like", value, "noticeName");
            return (Criteria) this;
        }

        public Criteria andNoticeNameNotLike(String value) {
            addCriterion("NOTICE_NAME not like", value, "noticeName");
            return (Criteria) this;
        }

        public Criteria andNoticeNameIn(List<String> values) {
            addCriterion("NOTICE_NAME in", values, "noticeName");
            return (Criteria) this;
        }

        public Criteria andNoticeNameNotIn(List<String> values) {
            addCriterion("NOTICE_NAME not in", values, "noticeName");
            return (Criteria) this;
        }

        public Criteria andNoticeNameBetween(String value1, String value2) {
            addCriterion("NOTICE_NAME between", value1, value2, "noticeName");
            return (Criteria) this;
        }

        public Criteria andNoticeNameNotBetween(String value1, String value2) {
            addCriterion("NOTICE_NAME not between", value1, value2, "noticeName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdIsNull() {
            addCriterion("PARTY_BRANCH_ID is null");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdIsNotNull() {
            addCriterion("PARTY_BRANCH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdEqualTo(String value) {
            addCriterion("PARTY_BRANCH_ID =", value, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdNotEqualTo(String value) {
            addCriterion("PARTY_BRANCH_ID <>", value, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdGreaterThan(String value) {
            addCriterion("PARTY_BRANCH_ID >", value, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdGreaterThanOrEqualTo(String value) {
            addCriterion("PARTY_BRANCH_ID >=", value, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdLessThan(String value) {
            addCriterion("PARTY_BRANCH_ID <", value, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdLessThanOrEqualTo(String value) {
            addCriterion("PARTY_BRANCH_ID <=", value, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdLike(String value) {
            addCriterion("PARTY_BRANCH_ID like", value, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdNotLike(String value) {
            addCriterion("PARTY_BRANCH_ID not like", value, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdIn(List<String> values) {
            addCriterion("PARTY_BRANCH_ID in", values, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdNotIn(List<String> values) {
            addCriterion("PARTY_BRANCH_ID not in", values, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdBetween(String value1, String value2) {
            addCriterion("PARTY_BRANCH_ID between", value1, value2, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdNotBetween(String value1, String value2) {
            addCriterion("PARTY_BRANCH_ID not between", value1, value2, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameIsNull() {
            addCriterion("PARTY_BRANCH_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameIsNotNull() {
            addCriterion("PARTY_BRANCH_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameEqualTo(String value) {
            addCriterion("PARTY_BRANCH_NAME =", value, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameNotEqualTo(String value) {
            addCriterion("PARTY_BRANCH_NAME <>", value, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameGreaterThan(String value) {
            addCriterion("PARTY_BRANCH_NAME >", value, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameGreaterThanOrEqualTo(String value) {
            addCriterion("PARTY_BRANCH_NAME >=", value, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameLessThan(String value) {
            addCriterion("PARTY_BRANCH_NAME <", value, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameLessThanOrEqualTo(String value) {
            addCriterion("PARTY_BRANCH_NAME <=", value, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameLike(String value) {
            addCriterion("PARTY_BRANCH_NAME like", value, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameNotLike(String value) {
            addCriterion("PARTY_BRANCH_NAME not like", value, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameIn(List<String> values) {
            addCriterion("PARTY_BRANCH_NAME in", values, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameNotIn(List<String> values) {
            addCriterion("PARTY_BRANCH_NAME not in", values, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameBetween(String value1, String value2) {
            addCriterion("PARTY_BRANCH_NAME between", value1, value2, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameNotBetween(String value1, String value2) {
            addCriterion("PARTY_BRANCH_NAME not between", value1, value2, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeIdIsNull() {
            addCriterion("NOTICE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeIdIsNotNull() {
            addCriterion("NOTICE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeIdEqualTo(String value) {
            addCriterion("NOTICE_TYPE_ID =", value, "noticeTypeId");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeIdNotEqualTo(String value) {
            addCriterion("NOTICE_TYPE_ID <>", value, "noticeTypeId");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeIdGreaterThan(String value) {
            addCriterion("NOTICE_TYPE_ID >", value, "noticeTypeId");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("NOTICE_TYPE_ID >=", value, "noticeTypeId");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeIdLessThan(String value) {
            addCriterion("NOTICE_TYPE_ID <", value, "noticeTypeId");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeIdLessThanOrEqualTo(String value) {
            addCriterion("NOTICE_TYPE_ID <=", value, "noticeTypeId");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeIdLike(String value) {
            addCriterion("NOTICE_TYPE_ID like", value, "noticeTypeId");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeIdNotLike(String value) {
            addCriterion("NOTICE_TYPE_ID not like", value, "noticeTypeId");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeIdIn(List<String> values) {
            addCriterion("NOTICE_TYPE_ID in", values, "noticeTypeId");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeIdNotIn(List<String> values) {
            addCriterion("NOTICE_TYPE_ID not in", values, "noticeTypeId");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeIdBetween(String value1, String value2) {
            addCriterion("NOTICE_TYPE_ID between", value1, value2, "noticeTypeId");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeIdNotBetween(String value1, String value2) {
            addCriterion("NOTICE_TYPE_ID not between", value1, value2, "noticeTypeId");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeNameIsNull() {
            addCriterion("NOTICE_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeNameIsNotNull() {
            addCriterion("NOTICE_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeNameEqualTo(String value) {
            addCriterion("NOTICE_TYPE_NAME =", value, "noticeTypeName");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeNameNotEqualTo(String value) {
            addCriterion("NOTICE_TYPE_NAME <>", value, "noticeTypeName");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeNameGreaterThan(String value) {
            addCriterion("NOTICE_TYPE_NAME >", value, "noticeTypeName");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("NOTICE_TYPE_NAME >=", value, "noticeTypeName");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeNameLessThan(String value) {
            addCriterion("NOTICE_TYPE_NAME <", value, "noticeTypeName");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeNameLessThanOrEqualTo(String value) {
            addCriterion("NOTICE_TYPE_NAME <=", value, "noticeTypeName");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeNameLike(String value) {
            addCriterion("NOTICE_TYPE_NAME like", value, "noticeTypeName");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeNameNotLike(String value) {
            addCriterion("NOTICE_TYPE_NAME not like", value, "noticeTypeName");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeNameIn(List<String> values) {
            addCriterion("NOTICE_TYPE_NAME in", values, "noticeTypeName");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeNameNotIn(List<String> values) {
            addCriterion("NOTICE_TYPE_NAME not in", values, "noticeTypeName");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeNameBetween(String value1, String value2) {
            addCriterion("NOTICE_TYPE_NAME between", value1, value2, "noticeTypeName");
            return (Criteria) this;
        }

        public Criteria andNoticeTypeNameNotBetween(String value1, String value2) {
            addCriterion("NOTICE_TYPE_NAME not between", value1, value2, "noticeTypeName");
            return (Criteria) this;
        }

        public Criteria andPublishFlagIsNull() {
            addCriterion("PUBLISH_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andPublishFlagIsNotNull() {
            addCriterion("PUBLISH_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andPublishFlagEqualTo(String value) {
            addCriterion("PUBLISH_FLAG =", value, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagNotEqualTo(String value) {
            addCriterion("PUBLISH_FLAG <>", value, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagGreaterThan(String value) {
            addCriterion("PUBLISH_FLAG >", value, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLISH_FLAG >=", value, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagLessThan(String value) {
            addCriterion("PUBLISH_FLAG <", value, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagLessThanOrEqualTo(String value) {
            addCriterion("PUBLISH_FLAG <=", value, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagLike(String value) {
            addCriterion("PUBLISH_FLAG like", value, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagNotLike(String value) {
            addCriterion("PUBLISH_FLAG not like", value, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagIn(List<String> values) {
            addCriterion("PUBLISH_FLAG in", values, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagNotIn(List<String> values) {
            addCriterion("PUBLISH_FLAG not in", values, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagBetween(String value1, String value2) {
            addCriterion("PUBLISH_FLAG between", value1, value2, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishFlagNotBetween(String value1, String value2) {
            addCriterion("PUBLISH_FLAG not between", value1, value2, "publishFlag");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIsNull() {
            addCriterion("PUBLISH_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIsNotNull() {
            addCriterion("PUBLISH_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPublishTimeEqualTo(String value) {
            addCriterion("PUBLISH_TIME =", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotEqualTo(String value) {
            addCriterion("PUBLISH_TIME <>", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeGreaterThan(String value) {
            addCriterion("PUBLISH_TIME >", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLISH_TIME >=", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeLessThan(String value) {
            addCriterion("PUBLISH_TIME <", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeLessThanOrEqualTo(String value) {
            addCriterion("PUBLISH_TIME <=", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeLike(String value) {
            addCriterion("PUBLISH_TIME like", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotLike(String value) {
            addCriterion("PUBLISH_TIME not like", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIn(List<String> values) {
            addCriterion("PUBLISH_TIME in", values, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotIn(List<String> values) {
            addCriterion("PUBLISH_TIME not in", values, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeBetween(String value1, String value2) {
            addCriterion("PUBLISH_TIME between", value1, value2, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotBetween(String value1, String value2) {
            addCriterion("PUBLISH_TIME not between", value1, value2, "publishTime");
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

        public Criteria andClicksIsNull() {
            addCriterion("CLICKS is null");
            return (Criteria) this;
        }

        public Criteria andClicksIsNotNull() {
            addCriterion("CLICKS is not null");
            return (Criteria) this;
        }

        public Criteria andClicksEqualTo(String value) {
            addCriterion("CLICKS =", value, "clicks");
            return (Criteria) this;
        }

        public Criteria andClicksNotEqualTo(String value) {
            addCriterion("CLICKS <>", value, "clicks");
            return (Criteria) this;
        }

        public Criteria andClicksGreaterThan(String value) {
            addCriterion("CLICKS >", value, "clicks");
            return (Criteria) this;
        }

        public Criteria andClicksGreaterThanOrEqualTo(String value) {
            addCriterion("CLICKS >=", value, "clicks");
            return (Criteria) this;
        }

        public Criteria andClicksLessThan(String value) {
            addCriterion("CLICKS <", value, "clicks");
            return (Criteria) this;
        }

        public Criteria andClicksLessThanOrEqualTo(String value) {
            addCriterion("CLICKS <=", value, "clicks");
            return (Criteria) this;
        }

        public Criteria andClicksLike(String value) {
            addCriterion("CLICKS like", value, "clicks");
            return (Criteria) this;
        }

        public Criteria andClicksNotLike(String value) {
            addCriterion("CLICKS not like", value, "clicks");
            return (Criteria) this;
        }

        public Criteria andClicksIn(List<String> values) {
            addCriterion("CLICKS in", values, "clicks");
            return (Criteria) this;
        }

        public Criteria andClicksNotIn(List<String> values) {
            addCriterion("CLICKS not in", values, "clicks");
            return (Criteria) this;
        }

        public Criteria andClicksBetween(String value1, String value2) {
            addCriterion("CLICKS between", value1, value2, "clicks");
            return (Criteria) this;
        }

        public Criteria andClicksNotBetween(String value1, String value2) {
            addCriterion("CLICKS not between", value1, value2, "clicks");
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