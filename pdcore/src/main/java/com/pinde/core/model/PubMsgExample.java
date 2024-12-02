package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class PubMsgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PubMsgExample() {
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

        public Criteria andMsgFlowIsNull() {
            addCriterion("MSG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andMsgFlowIsNotNull() {
            addCriterion("MSG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andMsgFlowEqualTo(String value) {
            addCriterion("MSG_FLOW =", value, "msgFlow");
            return (Criteria) this;
        }

        public Criteria andMsgFlowNotEqualTo(String value) {
            addCriterion("MSG_FLOW <>", value, "msgFlow");
            return (Criteria) this;
        }

        public Criteria andMsgFlowGreaterThan(String value) {
            addCriterion("MSG_FLOW >", value, "msgFlow");
            return (Criteria) this;
        }

        public Criteria andMsgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("MSG_FLOW >=", value, "msgFlow");
            return (Criteria) this;
        }

        public Criteria andMsgFlowLessThan(String value) {
            addCriterion("MSG_FLOW <", value, "msgFlow");
            return (Criteria) this;
        }

        public Criteria andMsgFlowLessThanOrEqualTo(String value) {
            addCriterion("MSG_FLOW <=", value, "msgFlow");
            return (Criteria) this;
        }

        public Criteria andMsgFlowLike(String value) {
            addCriterion("MSG_FLOW like", value, "msgFlow");
            return (Criteria) this;
        }

        public Criteria andMsgFlowNotLike(String value) {
            addCriterion("MSG_FLOW not like", value, "msgFlow");
            return (Criteria) this;
        }

        public Criteria andMsgFlowIn(List<String> values) {
            addCriterion("MSG_FLOW in", values, "msgFlow");
            return (Criteria) this;
        }

        public Criteria andMsgFlowNotIn(List<String> values) {
            addCriterion("MSG_FLOW not in", values, "msgFlow");
            return (Criteria) this;
        }

        public Criteria andMsgFlowBetween(String value1, String value2) {
            addCriterion("MSG_FLOW between", value1, value2, "msgFlow");
            return (Criteria) this;
        }

        public Criteria andMsgFlowNotBetween(String value1, String value2) {
            addCriterion("MSG_FLOW not between", value1, value2, "msgFlow");
            return (Criteria) this;
        }

        public Criteria andMsgCodeIsNull() {
            addCriterion("MSG_CODE is null");
            return (Criteria) this;
        }

        public Criteria andMsgCodeIsNotNull() {
            addCriterion("MSG_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andMsgCodeEqualTo(String value) {
            addCriterion("MSG_CODE =", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeNotEqualTo(String value) {
            addCriterion("MSG_CODE <>", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeGreaterThan(String value) {
            addCriterion("MSG_CODE >", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("MSG_CODE >=", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeLessThan(String value) {
            addCriterion("MSG_CODE <", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeLessThanOrEqualTo(String value) {
            addCriterion("MSG_CODE <=", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeLike(String value) {
            addCriterion("MSG_CODE like", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeNotLike(String value) {
            addCriterion("MSG_CODE not like", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeIn(List<String> values) {
            addCriterion("MSG_CODE in", values, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeNotIn(List<String> values) {
            addCriterion("MSG_CODE not in", values, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeBetween(String value1, String value2) {
            addCriterion("MSG_CODE between", value1, value2, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeNotBetween(String value1, String value2) {
            addCriterion("MSG_CODE not between", value1, value2, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIdIsNull() {
            addCriterion("MSG_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIdIsNotNull() {
            addCriterion("MSG_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIdEqualTo(String value) {
            addCriterion("MSG_TYPE_ID =", value, "msgTypeId");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIdNotEqualTo(String value) {
            addCriterion("MSG_TYPE_ID <>", value, "msgTypeId");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIdGreaterThan(String value) {
            addCriterion("MSG_TYPE_ID >", value, "msgTypeId");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("MSG_TYPE_ID >=", value, "msgTypeId");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIdLessThan(String value) {
            addCriterion("MSG_TYPE_ID <", value, "msgTypeId");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIdLessThanOrEqualTo(String value) {
            addCriterion("MSG_TYPE_ID <=", value, "msgTypeId");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIdLike(String value) {
            addCriterion("MSG_TYPE_ID like", value, "msgTypeId");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIdNotLike(String value) {
            addCriterion("MSG_TYPE_ID not like", value, "msgTypeId");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIdIn(List<String> values) {
            addCriterion("MSG_TYPE_ID in", values, "msgTypeId");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIdNotIn(List<String> values) {
            addCriterion("MSG_TYPE_ID not in", values, "msgTypeId");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIdBetween(String value1, String value2) {
            addCriterion("MSG_TYPE_ID between", value1, value2, "msgTypeId");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIdNotBetween(String value1, String value2) {
            addCriterion("MSG_TYPE_ID not between", value1, value2, "msgTypeId");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNameIsNull() {
            addCriterion("MSG_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNameIsNotNull() {
            addCriterion("MSG_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNameEqualTo(String value) {
            addCriterion("MSG_TYPE_NAME =", value, "msgTypeName");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNameNotEqualTo(String value) {
            addCriterion("MSG_TYPE_NAME <>", value, "msgTypeName");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNameGreaterThan(String value) {
            addCriterion("MSG_TYPE_NAME >", value, "msgTypeName");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("MSG_TYPE_NAME >=", value, "msgTypeName");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNameLessThan(String value) {
            addCriterion("MSG_TYPE_NAME <", value, "msgTypeName");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNameLessThanOrEqualTo(String value) {
            addCriterion("MSG_TYPE_NAME <=", value, "msgTypeName");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNameLike(String value) {
            addCriterion("MSG_TYPE_NAME like", value, "msgTypeName");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNameNotLike(String value) {
            addCriterion("MSG_TYPE_NAME not like", value, "msgTypeName");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNameIn(List<String> values) {
            addCriterion("MSG_TYPE_NAME in", values, "msgTypeName");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNameNotIn(List<String> values) {
            addCriterion("MSG_TYPE_NAME not in", values, "msgTypeName");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNameBetween(String value1, String value2) {
            addCriterion("MSG_TYPE_NAME between", value1, value2, "msgTypeName");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNameNotBetween(String value1, String value2) {
            addCriterion("MSG_TYPE_NAME not between", value1, value2, "msgTypeName");
            return (Criteria) this;
        }

        public Criteria andMsgTimeIsNull() {
            addCriterion("MSG_TIME is null");
            return (Criteria) this;
        }

        public Criteria andMsgTimeIsNotNull() {
            addCriterion("MSG_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andMsgTimeEqualTo(String value) {
            addCriterion("MSG_TIME =", value, "msgTime");
            return (Criteria) this;
        }

        public Criteria andMsgTimeNotEqualTo(String value) {
            addCriterion("MSG_TIME <>", value, "msgTime");
            return (Criteria) this;
        }

        public Criteria andMsgTimeGreaterThan(String value) {
            addCriterion("MSG_TIME >", value, "msgTime");
            return (Criteria) this;
        }

        public Criteria andMsgTimeGreaterThanOrEqualTo(String value) {
            addCriterion("MSG_TIME >=", value, "msgTime");
            return (Criteria) this;
        }

        public Criteria andMsgTimeLessThan(String value) {
            addCriterion("MSG_TIME <", value, "msgTime");
            return (Criteria) this;
        }

        public Criteria andMsgTimeLessThanOrEqualTo(String value) {
            addCriterion("MSG_TIME <=", value, "msgTime");
            return (Criteria) this;
        }

        public Criteria andMsgTimeLike(String value) {
            addCriterion("MSG_TIME like", value, "msgTime");
            return (Criteria) this;
        }

        public Criteria andMsgTimeNotLike(String value) {
            addCriterion("MSG_TIME not like", value, "msgTime");
            return (Criteria) this;
        }

        public Criteria andMsgTimeIn(List<String> values) {
            addCriterion("MSG_TIME in", values, "msgTime");
            return (Criteria) this;
        }

        public Criteria andMsgTimeNotIn(List<String> values) {
            addCriterion("MSG_TIME not in", values, "msgTime");
            return (Criteria) this;
        }

        public Criteria andMsgTimeBetween(String value1, String value2) {
            addCriterion("MSG_TIME between", value1, value2, "msgTime");
            return (Criteria) this;
        }

        public Criteria andMsgTimeNotBetween(String value1, String value2) {
            addCriterion("MSG_TIME not between", value1, value2, "msgTime");
            return (Criteria) this;
        }

        public Criteria andMsgTitleIsNull() {
            addCriterion("MSG_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andMsgTitleIsNotNull() {
            addCriterion("MSG_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andMsgTitleEqualTo(String value) {
            addCriterion("MSG_TITLE =", value, "msgTitle");
            return (Criteria) this;
        }

        public Criteria andMsgTitleNotEqualTo(String value) {
            addCriterion("MSG_TITLE <>", value, "msgTitle");
            return (Criteria) this;
        }

        public Criteria andMsgTitleGreaterThan(String value) {
            addCriterion("MSG_TITLE >", value, "msgTitle");
            return (Criteria) this;
        }

        public Criteria andMsgTitleGreaterThanOrEqualTo(String value) {
            addCriterion("MSG_TITLE >=", value, "msgTitle");
            return (Criteria) this;
        }

        public Criteria andMsgTitleLessThan(String value) {
            addCriterion("MSG_TITLE <", value, "msgTitle");
            return (Criteria) this;
        }

        public Criteria andMsgTitleLessThanOrEqualTo(String value) {
            addCriterion("MSG_TITLE <=", value, "msgTitle");
            return (Criteria) this;
        }

        public Criteria andMsgTitleLike(String value) {
            addCriterion("MSG_TITLE like", value, "msgTitle");
            return (Criteria) this;
        }

        public Criteria andMsgTitleNotLike(String value) {
            addCriterion("MSG_TITLE not like", value, "msgTitle");
            return (Criteria) this;
        }

        public Criteria andMsgTitleIn(List<String> values) {
            addCriterion("MSG_TITLE in", values, "msgTitle");
            return (Criteria) this;
        }

        public Criteria andMsgTitleNotIn(List<String> values) {
            addCriterion("MSG_TITLE not in", values, "msgTitle");
            return (Criteria) this;
        }

        public Criteria andMsgTitleBetween(String value1, String value2) {
            addCriterion("MSG_TITLE between", value1, value2, "msgTitle");
            return (Criteria) this;
        }

        public Criteria andMsgTitleNotBetween(String value1, String value2) {
            addCriterion("MSG_TITLE not between", value1, value2, "msgTitle");
            return (Criteria) this;
        }

        public Criteria andSenderIsNull() {
            addCriterion("SENDER is null");
            return (Criteria) this;
        }

        public Criteria andSenderIsNotNull() {
            addCriterion("SENDER is not null");
            return (Criteria) this;
        }

        public Criteria andSenderEqualTo(String value) {
            addCriterion("SENDER =", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderNotEqualTo(String value) {
            addCriterion("SENDER <>", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderGreaterThan(String value) {
            addCriterion("SENDER >", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderGreaterThanOrEqualTo(String value) {
            addCriterion("SENDER >=", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderLessThan(String value) {
            addCriterion("SENDER <", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderLessThanOrEqualTo(String value) {
            addCriterion("SENDER <=", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderLike(String value) {
            addCriterion("SENDER like", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderNotLike(String value) {
            addCriterion("SENDER not like", value, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderIn(List<String> values) {
            addCriterion("SENDER in", values, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderNotIn(List<String> values) {
            addCriterion("SENDER not in", values, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderBetween(String value1, String value2) {
            addCriterion("SENDER between", value1, value2, "sender");
            return (Criteria) this;
        }

        public Criteria andSenderNotBetween(String value1, String value2) {
            addCriterion("SENDER not between", value1, value2, "sender");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("SEND_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("SEND_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(String value) {
            addCriterion("SEND_TIME =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(String value) {
            addCriterion("SEND_TIME <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(String value) {
            addCriterion("SEND_TIME >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_TIME >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(String value) {
            addCriterion("SEND_TIME <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(String value) {
            addCriterion("SEND_TIME <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLike(String value) {
            addCriterion("SEND_TIME like", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotLike(String value) {
            addCriterion("SEND_TIME not like", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<String> values) {
            addCriterion("SEND_TIME in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<String> values) {
            addCriterion("SEND_TIME not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(String value1, String value2) {
            addCriterion("SEND_TIME between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(String value1, String value2) {
            addCriterion("SEND_TIME not between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendFlagIsNull() {
            addCriterion("SEND_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andSendFlagIsNotNull() {
            addCriterion("SEND_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andSendFlagEqualTo(String value) {
            addCriterion("SEND_FLAG =", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagNotEqualTo(String value) {
            addCriterion("SEND_FLAG <>", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagGreaterThan(String value) {
            addCriterion("SEND_FLAG >", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_FLAG >=", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagLessThan(String value) {
            addCriterion("SEND_FLAG <", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagLessThanOrEqualTo(String value) {
            addCriterion("SEND_FLAG <=", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagLike(String value) {
            addCriterion("SEND_FLAG like", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagNotLike(String value) {
            addCriterion("SEND_FLAG not like", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagIn(List<String> values) {
            addCriterion("SEND_FLAG in", values, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagNotIn(List<String> values) {
            addCriterion("SEND_FLAG not in", values, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagBetween(String value1, String value2) {
            addCriterion("SEND_FLAG between", value1, value2, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagNotBetween(String value1, String value2) {
            addCriterion("SEND_FLAG not between", value1, value2, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendResultIsNull() {
            addCriterion("SEND_RESULT is null");
            return (Criteria) this;
        }

        public Criteria andSendResultIsNotNull() {
            addCriterion("SEND_RESULT is not null");
            return (Criteria) this;
        }

        public Criteria andSendResultEqualTo(String value) {
            addCriterion("SEND_RESULT =", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultNotEqualTo(String value) {
            addCriterion("SEND_RESULT <>", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultGreaterThan(String value) {
            addCriterion("SEND_RESULT >", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultGreaterThanOrEqualTo(String value) {
            addCriterion("SEND_RESULT >=", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultLessThan(String value) {
            addCriterion("SEND_RESULT <", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultLessThanOrEqualTo(String value) {
            addCriterion("SEND_RESULT <=", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultLike(String value) {
            addCriterion("SEND_RESULT like", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultNotLike(String value) {
            addCriterion("SEND_RESULT not like", value, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultIn(List<String> values) {
            addCriterion("SEND_RESULT in", values, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultNotIn(List<String> values) {
            addCriterion("SEND_RESULT not in", values, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultBetween(String value1, String value2) {
            addCriterion("SEND_RESULT between", value1, value2, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendResultNotBetween(String value1, String value2) {
            addCriterion("SEND_RESULT not between", value1, value2, "sendResult");
            return (Criteria) this;
        }

        public Criteria andSendErrorTimesIsNull() {
            addCriterion("SEND_ERROR_TIMES is null");
            return (Criteria) this;
        }

        public Criteria andSendErrorTimesIsNotNull() {
            addCriterion("SEND_ERROR_TIMES is not null");
            return (Criteria) this;
        }

        public Criteria andSendErrorTimesEqualTo(Integer value) {
            addCriterion("SEND_ERROR_TIMES =", value, "sendErrorTimes");
            return (Criteria) this;
        }

        public Criteria andSendErrorTimesNotEqualTo(Integer value) {
            addCriterion("SEND_ERROR_TIMES <>", value, "sendErrorTimes");
            return (Criteria) this;
        }

        public Criteria andSendErrorTimesGreaterThan(Integer value) {
            addCriterion("SEND_ERROR_TIMES >", value, "sendErrorTimes");
            return (Criteria) this;
        }

        public Criteria andSendErrorTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("SEND_ERROR_TIMES >=", value, "sendErrorTimes");
            return (Criteria) this;
        }

        public Criteria andSendErrorTimesLessThan(Integer value) {
            addCriterion("SEND_ERROR_TIMES <", value, "sendErrorTimes");
            return (Criteria) this;
        }

        public Criteria andSendErrorTimesLessThanOrEqualTo(Integer value) {
            addCriterion("SEND_ERROR_TIMES <=", value, "sendErrorTimes");
            return (Criteria) this;
        }

        public Criteria andSendErrorTimesIn(List<Integer> values) {
            addCriterion("SEND_ERROR_TIMES in", values, "sendErrorTimes");
            return (Criteria) this;
        }

        public Criteria andSendErrorTimesNotIn(List<Integer> values) {
            addCriterion("SEND_ERROR_TIMES not in", values, "sendErrorTimes");
            return (Criteria) this;
        }

        public Criteria andSendErrorTimesBetween(Integer value1, Integer value2) {
            addCriterion("SEND_ERROR_TIMES between", value1, value2, "sendErrorTimes");
            return (Criteria) this;
        }

        public Criteria andSendErrorTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("SEND_ERROR_TIMES not between", value1, value2, "sendErrorTimes");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNull() {
            addCriterion("RECEIVER is null");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNotNull() {
            addCriterion("RECEIVER is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverEqualTo(String value) {
            addCriterion("RECEIVER =", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotEqualTo(String value) {
            addCriterion("RECEIVER <>", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThan(String value) {
            addCriterion("RECEIVER >", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVER >=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThan(String value) {
            addCriterion("RECEIVER <", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThanOrEqualTo(String value) {
            addCriterion("RECEIVER <=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLike(String value) {
            addCriterion("RECEIVER like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotLike(String value) {
            addCriterion("RECEIVER not like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverIn(List<String> values) {
            addCriterion("RECEIVER in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotIn(List<String> values) {
            addCriterion("RECEIVER not in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverBetween(String value1, String value2) {
            addCriterion("RECEIVER between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotBetween(String value1, String value2) {
            addCriterion("RECEIVER not between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIsNull() {
            addCriterion("RECEIVE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIsNotNull() {
            addCriterion("RECEIVE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeEqualTo(String value) {
            addCriterion("RECEIVE_TIME =", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotEqualTo(String value) {
            addCriterion("RECEIVE_TIME <>", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeGreaterThan(String value) {
            addCriterion("RECEIVE_TIME >", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVE_TIME >=", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeLessThan(String value) {
            addCriterion("RECEIVE_TIME <", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeLessThanOrEqualTo(String value) {
            addCriterion("RECEIVE_TIME <=", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeLike(String value) {
            addCriterion("RECEIVE_TIME like", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotLike(String value) {
            addCriterion("RECEIVE_TIME not like", value, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeIn(List<String> values) {
            addCriterion("RECEIVE_TIME in", values, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotIn(List<String> values) {
            addCriterion("RECEIVE_TIME not in", values, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeBetween(String value1, String value2) {
            addCriterion("RECEIVE_TIME between", value1, value2, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveTimeNotBetween(String value1, String value2) {
            addCriterion("RECEIVE_TIME not between", value1, value2, "receiveTime");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagIsNull() {
            addCriterion("RECEIVE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagIsNotNull() {
            addCriterion("RECEIVE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagEqualTo(String value) {
            addCriterion("RECEIVE_FLAG =", value, "receiveFlag");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagNotEqualTo(String value) {
            addCriterion("RECEIVE_FLAG <>", value, "receiveFlag");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagGreaterThan(String value) {
            addCriterion("RECEIVE_FLAG >", value, "receiveFlag");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVE_FLAG >=", value, "receiveFlag");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagLessThan(String value) {
            addCriterion("RECEIVE_FLAG <", value, "receiveFlag");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagLessThanOrEqualTo(String value) {
            addCriterion("RECEIVE_FLAG <=", value, "receiveFlag");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagLike(String value) {
            addCriterion("RECEIVE_FLAG like", value, "receiveFlag");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagNotLike(String value) {
            addCriterion("RECEIVE_FLAG not like", value, "receiveFlag");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagIn(List<String> values) {
            addCriterion("RECEIVE_FLAG in", values, "receiveFlag");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagNotIn(List<String> values) {
            addCriterion("RECEIVE_FLAG not in", values, "receiveFlag");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagBetween(String value1, String value2) {
            addCriterion("RECEIVE_FLAG between", value1, value2, "receiveFlag");
            return (Criteria) this;
        }

        public Criteria andReceiveFlagNotBetween(String value1, String value2) {
            addCriterion("RECEIVE_FLAG not between", value1, value2, "receiveFlag");
            return (Criteria) this;
        }

        public Criteria andReceiveResultIsNull() {
            addCriterion("RECEIVE_RESULT is null");
            return (Criteria) this;
        }

        public Criteria andReceiveResultIsNotNull() {
            addCriterion("RECEIVE_RESULT is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveResultEqualTo(String value) {
            addCriterion("RECEIVE_RESULT =", value, "receiveResult");
            return (Criteria) this;
        }

        public Criteria andReceiveResultNotEqualTo(String value) {
            addCriterion("RECEIVE_RESULT <>", value, "receiveResult");
            return (Criteria) this;
        }

        public Criteria andReceiveResultGreaterThan(String value) {
            addCriterion("RECEIVE_RESULT >", value, "receiveResult");
            return (Criteria) this;
        }

        public Criteria andReceiveResultGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVE_RESULT >=", value, "receiveResult");
            return (Criteria) this;
        }

        public Criteria andReceiveResultLessThan(String value) {
            addCriterion("RECEIVE_RESULT <", value, "receiveResult");
            return (Criteria) this;
        }

        public Criteria andReceiveResultLessThanOrEqualTo(String value) {
            addCriterion("RECEIVE_RESULT <=", value, "receiveResult");
            return (Criteria) this;
        }

        public Criteria andReceiveResultLike(String value) {
            addCriterion("RECEIVE_RESULT like", value, "receiveResult");
            return (Criteria) this;
        }

        public Criteria andReceiveResultNotLike(String value) {
            addCriterion("RECEIVE_RESULT not like", value, "receiveResult");
            return (Criteria) this;
        }

        public Criteria andReceiveResultIn(List<String> values) {
            addCriterion("RECEIVE_RESULT in", values, "receiveResult");
            return (Criteria) this;
        }

        public Criteria andReceiveResultNotIn(List<String> values) {
            addCriterion("RECEIVE_RESULT not in", values, "receiveResult");
            return (Criteria) this;
        }

        public Criteria andReceiveResultBetween(String value1, String value2) {
            addCriterion("RECEIVE_RESULT between", value1, value2, "receiveResult");
            return (Criteria) this;
        }

        public Criteria andReceiveResultNotBetween(String value1, String value2) {
            addCriterion("RECEIVE_RESULT not between", value1, value2, "receiveResult");
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