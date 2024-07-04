package com.pinde.sci.model.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SysSmsLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysSmsLogExample() {
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

        public Criteria andSmsLogFlowIsNull() {
            addCriterion("SMS_LOG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSmsLogFlowIsNotNull() {
            addCriterion("SMS_LOG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSmsLogFlowEqualTo(String value) {
            addCriterion("SMS_LOG_FLOW =", value, "smsLogFlow");
            return (Criteria) this;
        }

        public Criteria andSmsLogFlowNotEqualTo(String value) {
            addCriterion("SMS_LOG_FLOW <>", value, "smsLogFlow");
            return (Criteria) this;
        }

        public Criteria andSmsLogFlowGreaterThan(String value) {
            addCriterion("SMS_LOG_FLOW >", value, "smsLogFlow");
            return (Criteria) this;
        }

        public Criteria andSmsLogFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SMS_LOG_FLOW >=", value, "smsLogFlow");
            return (Criteria) this;
        }

        public Criteria andSmsLogFlowLessThan(String value) {
            addCriterion("SMS_LOG_FLOW <", value, "smsLogFlow");
            return (Criteria) this;
        }

        public Criteria andSmsLogFlowLessThanOrEqualTo(String value) {
            addCriterion("SMS_LOG_FLOW <=", value, "smsLogFlow");
            return (Criteria) this;
        }

        public Criteria andSmsLogFlowLike(String value) {
            addCriterion("SMS_LOG_FLOW like", value, "smsLogFlow");
            return (Criteria) this;
        }

        public Criteria andSmsLogFlowNotLike(String value) {
            addCriterion("SMS_LOG_FLOW not like", value, "smsLogFlow");
            return (Criteria) this;
        }

        public Criteria andSmsLogFlowIn(List<String> values) {
            addCriterion("SMS_LOG_FLOW in", values, "smsLogFlow");
            return (Criteria) this;
        }

        public Criteria andSmsLogFlowNotIn(List<String> values) {
            addCriterion("SMS_LOG_FLOW not in", values, "smsLogFlow");
            return (Criteria) this;
        }

        public Criteria andSmsLogFlowBetween(String value1, String value2) {
            addCriterion("SMS_LOG_FLOW between", value1, value2, "smsLogFlow");
            return (Criteria) this;
        }

        public Criteria andSmsLogFlowNotBetween(String value1, String value2) {
            addCriterion("SMS_LOG_FLOW not between", value1, value2, "smsLogFlow");
            return (Criteria) this;
        }

        public Criteria andSmsUserNameIsNull() {
            addCriterion("SMS_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSmsUserNameIsNotNull() {
            addCriterion("SMS_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSmsUserNameEqualTo(String value) {
            addCriterion("SMS_USER_NAME =", value, "smsUserName");
            return (Criteria) this;
        }

        public Criteria andSmsUserNameNotEqualTo(String value) {
            addCriterion("SMS_USER_NAME <>", value, "smsUserName");
            return (Criteria) this;
        }

        public Criteria andSmsUserNameGreaterThan(String value) {
            addCriterion("SMS_USER_NAME >", value, "smsUserName");
            return (Criteria) this;
        }

        public Criteria andSmsUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("SMS_USER_NAME >=", value, "smsUserName");
            return (Criteria) this;
        }

        public Criteria andSmsUserNameLessThan(String value) {
            addCriterion("SMS_USER_NAME <", value, "smsUserName");
            return (Criteria) this;
        }

        public Criteria andSmsUserNameLessThanOrEqualTo(String value) {
            addCriterion("SMS_USER_NAME <=", value, "smsUserName");
            return (Criteria) this;
        }

        public Criteria andSmsUserNameLike(String value) {
            addCriterion("SMS_USER_NAME like", value, "smsUserName");
            return (Criteria) this;
        }

        public Criteria andSmsUserNameNotLike(String value) {
            addCriterion("SMS_USER_NAME not like", value, "smsUserName");
            return (Criteria) this;
        }

        public Criteria andSmsUserNameIn(List<String> values) {
            addCriterion("SMS_USER_NAME in", values, "smsUserName");
            return (Criteria) this;
        }

        public Criteria andSmsUserNameNotIn(List<String> values) {
            addCriterion("SMS_USER_NAME not in", values, "smsUserName");
            return (Criteria) this;
        }

        public Criteria andSmsUserNameBetween(String value1, String value2) {
            addCriterion("SMS_USER_NAME between", value1, value2, "smsUserName");
            return (Criteria) this;
        }

        public Criteria andSmsUserNameNotBetween(String value1, String value2) {
            addCriterion("SMS_USER_NAME not between", value1, value2, "smsUserName");
            return (Criteria) this;
        }

        public Criteria andSmsSendTimeIsNull() {
            addCriterion("SMS_SEND_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSmsSendTimeIsNotNull() {
            addCriterion("SMS_SEND_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSmsSendTimeEqualTo(String value) {
            addCriterion("SMS_SEND_TIME =", value, "smsSendTime");
            return (Criteria) this;
        }

        public Criteria andSmsSendTimeNotEqualTo(String value) {
            addCriterion("SMS_SEND_TIME <>", value, "smsSendTime");
            return (Criteria) this;
        }

        public Criteria andSmsSendTimeGreaterThan(String value) {
            addCriterion("SMS_SEND_TIME >", value, "smsSendTime");
            return (Criteria) this;
        }

        public Criteria andSmsSendTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SMS_SEND_TIME >=", value, "smsSendTime");
            return (Criteria) this;
        }

        public Criteria andSmsSendTimeLessThan(String value) {
            addCriterion("SMS_SEND_TIME <", value, "smsSendTime");
            return (Criteria) this;
        }

        public Criteria andSmsSendTimeLessThanOrEqualTo(String value) {
            addCriterion("SMS_SEND_TIME <=", value, "smsSendTime");
            return (Criteria) this;
        }

        public Criteria andSmsSendTimeLike(String value) {
            addCriterion("SMS_SEND_TIME like", value, "smsSendTime");
            return (Criteria) this;
        }

        public Criteria andSmsSendTimeNotLike(String value) {
            addCriterion("SMS_SEND_TIME not like", value, "smsSendTime");
            return (Criteria) this;
        }

        public Criteria andSmsSendTimeIn(List<String> values) {
            addCriterion("SMS_SEND_TIME in", values, "smsSendTime");
            return (Criteria) this;
        }

        public Criteria andSmsSendTimeNotIn(List<String> values) {
            addCriterion("SMS_SEND_TIME not in", values, "smsSendTime");
            return (Criteria) this;
        }

        public Criteria andSmsSendTimeBetween(String value1, String value2) {
            addCriterion("SMS_SEND_TIME between", value1, value2, "smsSendTime");
            return (Criteria) this;
        }

        public Criteria andSmsSendTimeNotBetween(String value1, String value2) {
            addCriterion("SMS_SEND_TIME not between", value1, value2, "smsSendTime");
            return (Criteria) this;
        }

        public Criteria andSmsMobileIsNull() {
            addCriterion("SMS_MOBILE is null");
            return (Criteria) this;
        }

        public Criteria andSmsMobileIsNotNull() {
            addCriterion("SMS_MOBILE is not null");
            return (Criteria) this;
        }

        public Criteria andSmsMobileEqualTo(String value) {
            addCriterion("SMS_MOBILE =", value, "smsMobile");
            return (Criteria) this;
        }

        public Criteria andSmsMobileNotEqualTo(String value) {
            addCriterion("SMS_MOBILE <>", value, "smsMobile");
            return (Criteria) this;
        }

        public Criteria andSmsMobileGreaterThan(String value) {
            addCriterion("SMS_MOBILE >", value, "smsMobile");
            return (Criteria) this;
        }

        public Criteria andSmsMobileGreaterThanOrEqualTo(String value) {
            addCriterion("SMS_MOBILE >=", value, "smsMobile");
            return (Criteria) this;
        }

        public Criteria andSmsMobileLessThan(String value) {
            addCriterion("SMS_MOBILE <", value, "smsMobile");
            return (Criteria) this;
        }

        public Criteria andSmsMobileLessThanOrEqualTo(String value) {
            addCriterion("SMS_MOBILE <=", value, "smsMobile");
            return (Criteria) this;
        }

        public Criteria andSmsMobileLike(String value) {
            addCriterion("SMS_MOBILE like", value, "smsMobile");
            return (Criteria) this;
        }

        public Criteria andSmsMobileNotLike(String value) {
            addCriterion("SMS_MOBILE not like", value, "smsMobile");
            return (Criteria) this;
        }

        public Criteria andSmsMobileIn(List<String> values) {
            addCriterion("SMS_MOBILE in", values, "smsMobile");
            return (Criteria) this;
        }

        public Criteria andSmsMobileNotIn(List<String> values) {
            addCriterion("SMS_MOBILE not in", values, "smsMobile");
            return (Criteria) this;
        }

        public Criteria andSmsMobileBetween(String value1, String value2) {
            addCriterion("SMS_MOBILE between", value1, value2, "smsMobile");
            return (Criteria) this;
        }

        public Criteria andSmsMobileNotBetween(String value1, String value2) {
            addCriterion("SMS_MOBILE not between", value1, value2, "smsMobile");
            return (Criteria) this;
        }

        public Criteria andSmsContentIsNull() {
            addCriterion("SMS_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andSmsContentIsNotNull() {
            addCriterion("SMS_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andSmsContentEqualTo(String value) {
            addCriterion("SMS_CONTENT =", value, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentNotEqualTo(String value) {
            addCriterion("SMS_CONTENT <>", value, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentGreaterThan(String value) {
            addCriterion("SMS_CONTENT >", value, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentGreaterThanOrEqualTo(String value) {
            addCriterion("SMS_CONTENT >=", value, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentLessThan(String value) {
            addCriterion("SMS_CONTENT <", value, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentLessThanOrEqualTo(String value) {
            addCriterion("SMS_CONTENT <=", value, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentLike(String value) {
            addCriterion("SMS_CONTENT like", value, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentNotLike(String value) {
            addCriterion("SMS_CONTENT not like", value, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentIn(List<String> values) {
            addCriterion("SMS_CONTENT in", values, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentNotIn(List<String> values) {
            addCriterion("SMS_CONTENT not in", values, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentBetween(String value1, String value2) {
            addCriterion("SMS_CONTENT between", value1, value2, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsContentNotBetween(String value1, String value2) {
            addCriterion("SMS_CONTENT not between", value1, value2, "smsContent");
            return (Criteria) this;
        }

        public Criteria andSmsReceiverCountIsNull() {
            addCriterion("SMS_RECEIVER_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andSmsReceiverCountIsNotNull() {
            addCriterion("SMS_RECEIVER_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andSmsReceiverCountEqualTo(BigDecimal value) {
            addCriterion("SMS_RECEIVER_COUNT =", value, "smsReceiverCount");
            return (Criteria) this;
        }

        public Criteria andSmsReceiverCountNotEqualTo(BigDecimal value) {
            addCriterion("SMS_RECEIVER_COUNT <>", value, "smsReceiverCount");
            return (Criteria) this;
        }

        public Criteria andSmsReceiverCountGreaterThan(BigDecimal value) {
            addCriterion("SMS_RECEIVER_COUNT >", value, "smsReceiverCount");
            return (Criteria) this;
        }

        public Criteria andSmsReceiverCountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SMS_RECEIVER_COUNT >=", value, "smsReceiverCount");
            return (Criteria) this;
        }

        public Criteria andSmsReceiverCountLessThan(BigDecimal value) {
            addCriterion("SMS_RECEIVER_COUNT <", value, "smsReceiverCount");
            return (Criteria) this;
        }

        public Criteria andSmsReceiverCountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SMS_RECEIVER_COUNT <=", value, "smsReceiverCount");
            return (Criteria) this;
        }

        public Criteria andSmsReceiverCountIn(List<BigDecimal> values) {
            addCriterion("SMS_RECEIVER_COUNT in", values, "smsReceiverCount");
            return (Criteria) this;
        }

        public Criteria andSmsReceiverCountNotIn(List<BigDecimal> values) {
            addCriterion("SMS_RECEIVER_COUNT not in", values, "smsReceiverCount");
            return (Criteria) this;
        }

        public Criteria andSmsReceiverCountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SMS_RECEIVER_COUNT between", value1, value2, "smsReceiverCount");
            return (Criteria) this;
        }

        public Criteria andSmsReceiverCountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SMS_RECEIVER_COUNT not between", value1, value2, "smsReceiverCount");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowIsNull() {
            addCriterion("SMS_TEMP_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowIsNotNull() {
            addCriterion("SMS_TEMP_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowEqualTo(String value) {
            addCriterion("SMS_TEMP_FLOW =", value, "smsTempFlow");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowNotEqualTo(String value) {
            addCriterion("SMS_TEMP_FLOW <>", value, "smsTempFlow");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowGreaterThan(String value) {
            addCriterion("SMS_TEMP_FLOW >", value, "smsTempFlow");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SMS_TEMP_FLOW >=", value, "smsTempFlow");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowLessThan(String value) {
            addCriterion("SMS_TEMP_FLOW <", value, "smsTempFlow");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowLessThanOrEqualTo(String value) {
            addCriterion("SMS_TEMP_FLOW <=", value, "smsTempFlow");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowLike(String value) {
            addCriterion("SMS_TEMP_FLOW like", value, "smsTempFlow");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowNotLike(String value) {
            addCriterion("SMS_TEMP_FLOW not like", value, "smsTempFlow");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowIn(List<String> values) {
            addCriterion("SMS_TEMP_FLOW in", values, "smsTempFlow");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowNotIn(List<String> values) {
            addCriterion("SMS_TEMP_FLOW not in", values, "smsTempFlow");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowBetween(String value1, String value2) {
            addCriterion("SMS_TEMP_FLOW between", value1, value2, "smsTempFlow");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowNotBetween(String value1, String value2) {
            addCriterion("SMS_TEMP_FLOW not between", value1, value2, "smsTempFlow");
            return (Criteria) this;
        }

        public Criteria andTempIdIsNull() {
            addCriterion("TEMP_ID is null");
            return (Criteria) this;
        }

        public Criteria andTempIdIsNotNull() {
            addCriterion("TEMP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTempIdEqualTo(BigDecimal value) {
            addCriterion("TEMP_ID =", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdNotEqualTo(BigDecimal value) {
            addCriterion("TEMP_ID <>", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdGreaterThan(BigDecimal value) {
            addCriterion("TEMP_ID >", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TEMP_ID >=", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdLessThan(BigDecimal value) {
            addCriterion("TEMP_ID <", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TEMP_ID <=", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdIn(List<BigDecimal> values) {
            addCriterion("TEMP_ID in", values, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdNotIn(List<BigDecimal> values) {
            addCriterion("TEMP_ID not in", values, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TEMP_ID between", value1, value2, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TEMP_ID not between", value1, value2, "tempId");
            return (Criteria) this;
        }

        public Criteria andStatusCodeIsNull() {
            addCriterion("STATUS_CODE is null");
            return (Criteria) this;
        }

        public Criteria andStatusCodeIsNotNull() {
            addCriterion("STATUS_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andStatusCodeEqualTo(String value) {
            addCriterion("STATUS_CODE =", value, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeNotEqualTo(String value) {
            addCriterion("STATUS_CODE <>", value, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeGreaterThan(String value) {
            addCriterion("STATUS_CODE >", value, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS_CODE >=", value, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeLessThan(String value) {
            addCriterion("STATUS_CODE <", value, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeLessThanOrEqualTo(String value) {
            addCriterion("STATUS_CODE <=", value, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeLike(String value) {
            addCriterion("STATUS_CODE like", value, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeNotLike(String value) {
            addCriterion("STATUS_CODE not like", value, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeIn(List<String> values) {
            addCriterion("STATUS_CODE in", values, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeNotIn(List<String> values) {
            addCriterion("STATUS_CODE not in", values, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeBetween(String value1, String value2) {
            addCriterion("STATUS_CODE between", value1, value2, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeNotBetween(String value1, String value2) {
            addCriterion("STATUS_CODE not between", value1, value2, "statusCode");
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

        public Criteria andSmsResponseMsgIsNull() {
            addCriterion("SMS_RESPONSE_MSG is null");
            return (Criteria) this;
        }

        public Criteria andSmsResponseMsgIsNotNull() {
            addCriterion("SMS_RESPONSE_MSG is not null");
            return (Criteria) this;
        }

        public Criteria andSmsResponseMsgEqualTo(String value) {
            addCriterion("SMS_RESPONSE_MSG =", value, "smsResponseMsg");
            return (Criteria) this;
        }

        public Criteria andSmsResponseMsgNotEqualTo(String value) {
            addCriterion("SMS_RESPONSE_MSG <>", value, "smsResponseMsg");
            return (Criteria) this;
        }

        public Criteria andSmsResponseMsgGreaterThan(String value) {
            addCriterion("SMS_RESPONSE_MSG >", value, "smsResponseMsg");
            return (Criteria) this;
        }

        public Criteria andSmsResponseMsgGreaterThanOrEqualTo(String value) {
            addCriterion("SMS_RESPONSE_MSG >=", value, "smsResponseMsg");
            return (Criteria) this;
        }

        public Criteria andSmsResponseMsgLessThan(String value) {
            addCriterion("SMS_RESPONSE_MSG <", value, "smsResponseMsg");
            return (Criteria) this;
        }

        public Criteria andSmsResponseMsgLessThanOrEqualTo(String value) {
            addCriterion("SMS_RESPONSE_MSG <=", value, "smsResponseMsg");
            return (Criteria) this;
        }

        public Criteria andSmsResponseMsgLike(String value) {
            addCriterion("SMS_RESPONSE_MSG like", value, "smsResponseMsg");
            return (Criteria) this;
        }

        public Criteria andSmsResponseMsgNotLike(String value) {
            addCriterion("SMS_RESPONSE_MSG not like", value, "smsResponseMsg");
            return (Criteria) this;
        }

        public Criteria andSmsResponseMsgIn(List<String> values) {
            addCriterion("SMS_RESPONSE_MSG in", values, "smsResponseMsg");
            return (Criteria) this;
        }

        public Criteria andSmsResponseMsgNotIn(List<String> values) {
            addCriterion("SMS_RESPONSE_MSG not in", values, "smsResponseMsg");
            return (Criteria) this;
        }

        public Criteria andSmsResponseMsgBetween(String value1, String value2) {
            addCriterion("SMS_RESPONSE_MSG between", value1, value2, "smsResponseMsg");
            return (Criteria) this;
        }

        public Criteria andSmsResponseMsgNotBetween(String value1, String value2) {
            addCriterion("SMS_RESPONSE_MSG not between", value1, value2, "smsResponseMsg");
            return (Criteria) this;
        }

        public Criteria andRelIdIsNull() {
            addCriterion("REL_ID is null");
            return (Criteria) this;
        }

        public Criteria andRelIdIsNotNull() {
            addCriterion("REL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRelIdEqualTo(String value) {
            addCriterion("REL_ID =", value, "relId");
            return (Criteria) this;
        }

        public Criteria andRelIdNotEqualTo(String value) {
            addCriterion("REL_ID <>", value, "relId");
            return (Criteria) this;
        }

        public Criteria andRelIdGreaterThan(String value) {
            addCriterion("REL_ID >", value, "relId");
            return (Criteria) this;
        }

        public Criteria andRelIdGreaterThanOrEqualTo(String value) {
            addCriterion("REL_ID >=", value, "relId");
            return (Criteria) this;
        }

        public Criteria andRelIdLessThan(String value) {
            addCriterion("REL_ID <", value, "relId");
            return (Criteria) this;
        }

        public Criteria andRelIdLessThanOrEqualTo(String value) {
            addCriterion("REL_ID <=", value, "relId");
            return (Criteria) this;
        }

        public Criteria andRelIdLike(String value) {
            addCriterion("REL_ID like", value, "relId");
            return (Criteria) this;
        }

        public Criteria andRelIdNotLike(String value) {
            addCriterion("REL_ID not like", value, "relId");
            return (Criteria) this;
        }

        public Criteria andRelIdIn(List<String> values) {
            addCriterion("REL_ID in", values, "relId");
            return (Criteria) this;
        }

        public Criteria andRelIdNotIn(List<String> values) {
            addCriterion("REL_ID not in", values, "relId");
            return (Criteria) this;
        }

        public Criteria andRelIdBetween(String value1, String value2) {
            addCriterion("REL_ID between", value1, value2, "relId");
            return (Criteria) this;
        }

        public Criteria andRelIdNotBetween(String value1, String value2) {
            addCriterion("REL_ID not between", value1, value2, "relId");
            return (Criteria) this;
        }

        public Criteria andRelTypeIsNull() {
            addCriterion("REL_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andRelTypeIsNotNull() {
            addCriterion("REL_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRelTypeEqualTo(String value) {
            addCriterion("REL_TYPE =", value, "relType");
            return (Criteria) this;
        }

        public Criteria andRelTypeNotEqualTo(String value) {
            addCriterion("REL_TYPE <>", value, "relType");
            return (Criteria) this;
        }

        public Criteria andRelTypeGreaterThan(String value) {
            addCriterion("REL_TYPE >", value, "relType");
            return (Criteria) this;
        }

        public Criteria andRelTypeGreaterThanOrEqualTo(String value) {
            addCriterion("REL_TYPE >=", value, "relType");
            return (Criteria) this;
        }

        public Criteria andRelTypeLessThan(String value) {
            addCriterion("REL_TYPE <", value, "relType");
            return (Criteria) this;
        }

        public Criteria andRelTypeLessThanOrEqualTo(String value) {
            addCriterion("REL_TYPE <=", value, "relType");
            return (Criteria) this;
        }

        public Criteria andRelTypeLike(String value) {
            addCriterion("REL_TYPE like", value, "relType");
            return (Criteria) this;
        }

        public Criteria andRelTypeNotLike(String value) {
            addCriterion("REL_TYPE not like", value, "relType");
            return (Criteria) this;
        }

        public Criteria andRelTypeIn(List<String> values) {
            addCriterion("REL_TYPE in", values, "relType");
            return (Criteria) this;
        }

        public Criteria andRelTypeNotIn(List<String> values) {
            addCriterion("REL_TYPE not in", values, "relType");
            return (Criteria) this;
        }

        public Criteria andRelTypeBetween(String value1, String value2) {
            addCriterion("REL_TYPE between", value1, value2, "relType");
            return (Criteria) this;
        }

        public Criteria andRelTypeNotBetween(String value1, String value2) {
            addCriterion("REL_TYPE not between", value1, value2, "relType");
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