package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ErpProductManageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ErpProductManageExample() {
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

        public Criteria andManageFlowIsNull() {
            addCriterion("MANAGE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andManageFlowIsNotNull() {
            addCriterion("MANAGE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andManageFlowEqualTo(String value) {
            addCriterion("MANAGE_FLOW =", value, "manageFlow");
            return (Criteria) this;
        }

        public Criteria andManageFlowNotEqualTo(String value) {
            addCriterion("MANAGE_FLOW <>", value, "manageFlow");
            return (Criteria) this;
        }

        public Criteria andManageFlowGreaterThan(String value) {
            addCriterion("MANAGE_FLOW >", value, "manageFlow");
            return (Criteria) this;
        }

        public Criteria andManageFlowGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGE_FLOW >=", value, "manageFlow");
            return (Criteria) this;
        }

        public Criteria andManageFlowLessThan(String value) {
            addCriterion("MANAGE_FLOW <", value, "manageFlow");
            return (Criteria) this;
        }

        public Criteria andManageFlowLessThanOrEqualTo(String value) {
            addCriterion("MANAGE_FLOW <=", value, "manageFlow");
            return (Criteria) this;
        }

        public Criteria andManageFlowLike(String value) {
            addCriterion("MANAGE_FLOW like", value, "manageFlow");
            return (Criteria) this;
        }

        public Criteria andManageFlowNotLike(String value) {
            addCriterion("MANAGE_FLOW not like", value, "manageFlow");
            return (Criteria) this;
        }

        public Criteria andManageFlowIn(List<String> values) {
            addCriterion("MANAGE_FLOW in", values, "manageFlow");
            return (Criteria) this;
        }

        public Criteria andManageFlowNotIn(List<String> values) {
            addCriterion("MANAGE_FLOW not in", values, "manageFlow");
            return (Criteria) this;
        }

        public Criteria andManageFlowBetween(String value1, String value2) {
            addCriterion("MANAGE_FLOW between", value1, value2, "manageFlow");
            return (Criteria) this;
        }

        public Criteria andManageFlowNotBetween(String value1, String value2) {
            addCriterion("MANAGE_FLOW not between", value1, value2, "manageFlow");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("PRODUCT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("PRODUCT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("PRODUCT_NAME =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("PRODUCT_NAME <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("PRODUCT_NAME >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_NAME >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("PRODUCT_NAME <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_NAME <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("PRODUCT_NAME like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("PRODUCT_NAME not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("PRODUCT_NAME in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("PRODUCT_NAME not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("PRODUCT_NAME between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_NAME not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowIsNull() {
            addCriterion("CONSUMER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowIsNotNull() {
            addCriterion("CONSUMER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowEqualTo(String value) {
            addCriterion("CONSUMER_FLOW =", value, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowNotEqualTo(String value) {
            addCriterion("CONSUMER_FLOW <>", value, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowGreaterThan(String value) {
            addCriterion("CONSUMER_FLOW >", value, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CONSUMER_FLOW >=", value, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowLessThan(String value) {
            addCriterion("CONSUMER_FLOW <", value, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowLessThanOrEqualTo(String value) {
            addCriterion("CONSUMER_FLOW <=", value, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowLike(String value) {
            addCriterion("CONSUMER_FLOW like", value, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowNotLike(String value) {
            addCriterion("CONSUMER_FLOW not like", value, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowIn(List<String> values) {
            addCriterion("CONSUMER_FLOW in", values, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowNotIn(List<String> values) {
            addCriterion("CONSUMER_FLOW not in", values, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowBetween(String value1, String value2) {
            addCriterion("CONSUMER_FLOW between", value1, value2, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowNotBetween(String value1, String value2) {
            addCriterion("CONSUMER_FLOW not between", value1, value2, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerNameIsNull() {
            addCriterion("CONSUMER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andConsumerNameIsNotNull() {
            addCriterion("CONSUMER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andConsumerNameEqualTo(String value) {
            addCriterion("CONSUMER_NAME =", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameNotEqualTo(String value) {
            addCriterion("CONSUMER_NAME <>", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameGreaterThan(String value) {
            addCriterion("CONSUMER_NAME >", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONSUMER_NAME >=", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameLessThan(String value) {
            addCriterion("CONSUMER_NAME <", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameLessThanOrEqualTo(String value) {
            addCriterion("CONSUMER_NAME <=", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameLike(String value) {
            addCriterion("CONSUMER_NAME like", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameNotLike(String value) {
            addCriterion("CONSUMER_NAME not like", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameIn(List<String> values) {
            addCriterion("CONSUMER_NAME in", values, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameNotIn(List<String> values) {
            addCriterion("CONSUMER_NAME not in", values, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameBetween(String value1, String value2) {
            addCriterion("CONSUMER_NAME between", value1, value2, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameNotBetween(String value1, String value2) {
            addCriterion("CONSUMER_NAME not between", value1, value2, "consumerName");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeIsNull() {
            addCriterion("APPROVAL_TIME is null");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeIsNotNull() {
            addCriterion("APPROVAL_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeEqualTo(String value) {
            addCriterion("APPROVAL_TIME =", value, "approvalTime");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeNotEqualTo(String value) {
            addCriterion("APPROVAL_TIME <>", value, "approvalTime");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeGreaterThan(String value) {
            addCriterion("APPROVAL_TIME >", value, "approvalTime");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeGreaterThanOrEqualTo(String value) {
            addCriterion("APPROVAL_TIME >=", value, "approvalTime");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeLessThan(String value) {
            addCriterion("APPROVAL_TIME <", value, "approvalTime");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeLessThanOrEqualTo(String value) {
            addCriterion("APPROVAL_TIME <=", value, "approvalTime");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeLike(String value) {
            addCriterion("APPROVAL_TIME like", value, "approvalTime");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeNotLike(String value) {
            addCriterion("APPROVAL_TIME not like", value, "approvalTime");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeIn(List<String> values) {
            addCriterion("APPROVAL_TIME in", values, "approvalTime");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeNotIn(List<String> values) {
            addCriterion("APPROVAL_TIME not in", values, "approvalTime");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeBetween(String value1, String value2) {
            addCriterion("APPROVAL_TIME between", value1, value2, "approvalTime");
            return (Criteria) this;
        }

        public Criteria andApprovalTimeNotBetween(String value1, String value2) {
            addCriterion("APPROVAL_TIME not between", value1, value2, "approvalTime");
            return (Criteria) this;
        }

        public Criteria andApprovalUserFlowIsNull() {
            addCriterion("APPROVAL_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApprovalUserFlowIsNotNull() {
            addCriterion("APPROVAL_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalUserFlowEqualTo(String value) {
            addCriterion("APPROVAL_USER_FLOW =", value, "approvalUserFlow");
            return (Criteria) this;
        }

        public Criteria andApprovalUserFlowNotEqualTo(String value) {
            addCriterion("APPROVAL_USER_FLOW <>", value, "approvalUserFlow");
            return (Criteria) this;
        }

        public Criteria andApprovalUserFlowGreaterThan(String value) {
            addCriterion("APPROVAL_USER_FLOW >", value, "approvalUserFlow");
            return (Criteria) this;
        }

        public Criteria andApprovalUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPROVAL_USER_FLOW >=", value, "approvalUserFlow");
            return (Criteria) this;
        }

        public Criteria andApprovalUserFlowLessThan(String value) {
            addCriterion("APPROVAL_USER_FLOW <", value, "approvalUserFlow");
            return (Criteria) this;
        }

        public Criteria andApprovalUserFlowLessThanOrEqualTo(String value) {
            addCriterion("APPROVAL_USER_FLOW <=", value, "approvalUserFlow");
            return (Criteria) this;
        }

        public Criteria andApprovalUserFlowLike(String value) {
            addCriterion("APPROVAL_USER_FLOW like", value, "approvalUserFlow");
            return (Criteria) this;
        }

        public Criteria andApprovalUserFlowNotLike(String value) {
            addCriterion("APPROVAL_USER_FLOW not like", value, "approvalUserFlow");
            return (Criteria) this;
        }

        public Criteria andApprovalUserFlowIn(List<String> values) {
            addCriterion("APPROVAL_USER_FLOW in", values, "approvalUserFlow");
            return (Criteria) this;
        }

        public Criteria andApprovalUserFlowNotIn(List<String> values) {
            addCriterion("APPROVAL_USER_FLOW not in", values, "approvalUserFlow");
            return (Criteria) this;
        }

        public Criteria andApprovalUserFlowBetween(String value1, String value2) {
            addCriterion("APPROVAL_USER_FLOW between", value1, value2, "approvalUserFlow");
            return (Criteria) this;
        }

        public Criteria andApprovalUserFlowNotBetween(String value1, String value2) {
            addCriterion("APPROVAL_USER_FLOW not between", value1, value2, "approvalUserFlow");
            return (Criteria) this;
        }

        public Criteria andApprovalUserNameIsNull() {
            addCriterion("APPROVAL_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andApprovalUserNameIsNotNull() {
            addCriterion("APPROVAL_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalUserNameEqualTo(String value) {
            addCriterion("APPROVAL_USER_NAME =", value, "approvalUserName");
            return (Criteria) this;
        }

        public Criteria andApprovalUserNameNotEqualTo(String value) {
            addCriterion("APPROVAL_USER_NAME <>", value, "approvalUserName");
            return (Criteria) this;
        }

        public Criteria andApprovalUserNameGreaterThan(String value) {
            addCriterion("APPROVAL_USER_NAME >", value, "approvalUserName");
            return (Criteria) this;
        }

        public Criteria andApprovalUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("APPROVAL_USER_NAME >=", value, "approvalUserName");
            return (Criteria) this;
        }

        public Criteria andApprovalUserNameLessThan(String value) {
            addCriterion("APPROVAL_USER_NAME <", value, "approvalUserName");
            return (Criteria) this;
        }

        public Criteria andApprovalUserNameLessThanOrEqualTo(String value) {
            addCriterion("APPROVAL_USER_NAME <=", value, "approvalUserName");
            return (Criteria) this;
        }

        public Criteria andApprovalUserNameLike(String value) {
            addCriterion("APPROVAL_USER_NAME like", value, "approvalUserName");
            return (Criteria) this;
        }

        public Criteria andApprovalUserNameNotLike(String value) {
            addCriterion("APPROVAL_USER_NAME not like", value, "approvalUserName");
            return (Criteria) this;
        }

        public Criteria andApprovalUserNameIn(List<String> values) {
            addCriterion("APPROVAL_USER_NAME in", values, "approvalUserName");
            return (Criteria) this;
        }

        public Criteria andApprovalUserNameNotIn(List<String> values) {
            addCriterion("APPROVAL_USER_NAME not in", values, "approvalUserName");
            return (Criteria) this;
        }

        public Criteria andApprovalUserNameBetween(String value1, String value2) {
            addCriterion("APPROVAL_USER_NAME between", value1, value2, "approvalUserName");
            return (Criteria) this;
        }

        public Criteria andApprovalUserNameNotBetween(String value1, String value2) {
            addCriterion("APPROVAL_USER_NAME not between", value1, value2, "approvalUserName");
            return (Criteria) this;
        }

        public Criteria andEtcTimeIsNull() {
            addCriterion("ETC_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEtcTimeIsNotNull() {
            addCriterion("ETC_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEtcTimeEqualTo(String value) {
            addCriterion("ETC_TIME =", value, "etcTime");
            return (Criteria) this;
        }

        public Criteria andEtcTimeNotEqualTo(String value) {
            addCriterion("ETC_TIME <>", value, "etcTime");
            return (Criteria) this;
        }

        public Criteria andEtcTimeGreaterThan(String value) {
            addCriterion("ETC_TIME >", value, "etcTime");
            return (Criteria) this;
        }

        public Criteria andEtcTimeGreaterThanOrEqualTo(String value) {
            addCriterion("ETC_TIME >=", value, "etcTime");
            return (Criteria) this;
        }

        public Criteria andEtcTimeLessThan(String value) {
            addCriterion("ETC_TIME <", value, "etcTime");
            return (Criteria) this;
        }

        public Criteria andEtcTimeLessThanOrEqualTo(String value) {
            addCriterion("ETC_TIME <=", value, "etcTime");
            return (Criteria) this;
        }

        public Criteria andEtcTimeLike(String value) {
            addCriterion("ETC_TIME like", value, "etcTime");
            return (Criteria) this;
        }

        public Criteria andEtcTimeNotLike(String value) {
            addCriterion("ETC_TIME not like", value, "etcTime");
            return (Criteria) this;
        }

        public Criteria andEtcTimeIn(List<String> values) {
            addCriterion("ETC_TIME in", values, "etcTime");
            return (Criteria) this;
        }

        public Criteria andEtcTimeNotIn(List<String> values) {
            addCriterion("ETC_TIME not in", values, "etcTime");
            return (Criteria) this;
        }

        public Criteria andEtcTimeBetween(String value1, String value2) {
            addCriterion("ETC_TIME between", value1, value2, "etcTime");
            return (Criteria) this;
        }

        public Criteria andEtcTimeNotBetween(String value1, String value2) {
            addCriterion("ETC_TIME not between", value1, value2, "etcTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeIsNull() {
            addCriterion("COMPLETE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeIsNotNull() {
            addCriterion("COMPLETE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeEqualTo(String value) {
            addCriterion("COMPLETE_TIME =", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeNotEqualTo(String value) {
            addCriterion("COMPLETE_TIME <>", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeGreaterThan(String value) {
            addCriterion("COMPLETE_TIME >", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeGreaterThanOrEqualTo(String value) {
            addCriterion("COMPLETE_TIME >=", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeLessThan(String value) {
            addCriterion("COMPLETE_TIME <", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeLessThanOrEqualTo(String value) {
            addCriterion("COMPLETE_TIME <=", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeLike(String value) {
            addCriterion("COMPLETE_TIME like", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeNotLike(String value) {
            addCriterion("COMPLETE_TIME not like", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeIn(List<String> values) {
            addCriterion("COMPLETE_TIME in", values, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeNotIn(List<String> values) {
            addCriterion("COMPLETE_TIME not in", values, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeBetween(String value1, String value2) {
            addCriterion("COMPLETE_TIME between", value1, value2, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeNotBetween(String value1, String value2) {
            addCriterion("COMPLETE_TIME not between", value1, value2, "completeTime");
            return (Criteria) this;
        }

        public Criteria andStatusIdIsNull() {
            addCriterion("STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andStatusIdIsNotNull() {
            addCriterion("STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStatusIdEqualTo(String value) {
            addCriterion("STATUS_ID =", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotEqualTo(String value) {
            addCriterion("STATUS_ID <>", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdGreaterThan(String value) {
            addCriterion("STATUS_ID >", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS_ID >=", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLessThan(String value) {
            addCriterion("STATUS_ID <", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLessThanOrEqualTo(String value) {
            addCriterion("STATUS_ID <=", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLike(String value) {
            addCriterion("STATUS_ID like", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotLike(String value) {
            addCriterion("STATUS_ID not like", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdIn(List<String> values) {
            addCriterion("STATUS_ID in", values, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotIn(List<String> values) {
            addCriterion("STATUS_ID not in", values, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdBetween(String value1, String value2) {
            addCriterion("STATUS_ID between", value1, value2, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotBetween(String value1, String value2) {
            addCriterion("STATUS_ID not between", value1, value2, "statusId");
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