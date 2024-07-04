package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class SrmFundProcessExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SrmFundProcessExample() {
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

        public Criteria andFundProcessFlowIsNull() {
            addCriterion("FUND_PROCESS_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andFundProcessFlowIsNotNull() {
            addCriterion("FUND_PROCESS_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andFundProcessFlowEqualTo(String value) {
            addCriterion("FUND_PROCESS_FLOW =", value, "fundProcessFlow");
            return (Criteria) this;
        }

        public Criteria andFundProcessFlowNotEqualTo(String value) {
            addCriterion("FUND_PROCESS_FLOW <>", value, "fundProcessFlow");
            return (Criteria) this;
        }

        public Criteria andFundProcessFlowGreaterThan(String value) {
            addCriterion("FUND_PROCESS_FLOW >", value, "fundProcessFlow");
            return (Criteria) this;
        }

        public Criteria andFundProcessFlowGreaterThanOrEqualTo(String value) {
            addCriterion("FUND_PROCESS_FLOW >=", value, "fundProcessFlow");
            return (Criteria) this;
        }

        public Criteria andFundProcessFlowLessThan(String value) {
            addCriterion("FUND_PROCESS_FLOW <", value, "fundProcessFlow");
            return (Criteria) this;
        }

        public Criteria andFundProcessFlowLessThanOrEqualTo(String value) {
            addCriterion("FUND_PROCESS_FLOW <=", value, "fundProcessFlow");
            return (Criteria) this;
        }

        public Criteria andFundProcessFlowLike(String value) {
            addCriterion("FUND_PROCESS_FLOW like", value, "fundProcessFlow");
            return (Criteria) this;
        }

        public Criteria andFundProcessFlowNotLike(String value) {
            addCriterion("FUND_PROCESS_FLOW not like", value, "fundProcessFlow");
            return (Criteria) this;
        }

        public Criteria andFundProcessFlowIn(List<String> values) {
            addCriterion("FUND_PROCESS_FLOW in", values, "fundProcessFlow");
            return (Criteria) this;
        }

        public Criteria andFundProcessFlowNotIn(List<String> values) {
            addCriterion("FUND_PROCESS_FLOW not in", values, "fundProcessFlow");
            return (Criteria) this;
        }

        public Criteria andFundProcessFlowBetween(String value1, String value2) {
            addCriterion("FUND_PROCESS_FLOW between", value1, value2, "fundProcessFlow");
            return (Criteria) this;
        }

        public Criteria andFundProcessFlowNotBetween(String value1, String value2) {
            addCriterion("FUND_PROCESS_FLOW not between", value1, value2, "fundProcessFlow");
            return (Criteria) this;
        }

        public Criteria andFundFlowIsNull() {
            addCriterion("FUND_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andFundFlowIsNotNull() {
            addCriterion("FUND_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andFundFlowEqualTo(String value) {
            addCriterion("FUND_FLOW =", value, "fundFlow");
            return (Criteria) this;
        }

        public Criteria andFundFlowNotEqualTo(String value) {
            addCriterion("FUND_FLOW <>", value, "fundFlow");
            return (Criteria) this;
        }

        public Criteria andFundFlowGreaterThan(String value) {
            addCriterion("FUND_FLOW >", value, "fundFlow");
            return (Criteria) this;
        }

        public Criteria andFundFlowGreaterThanOrEqualTo(String value) {
            addCriterion("FUND_FLOW >=", value, "fundFlow");
            return (Criteria) this;
        }

        public Criteria andFundFlowLessThan(String value) {
            addCriterion("FUND_FLOW <", value, "fundFlow");
            return (Criteria) this;
        }

        public Criteria andFundFlowLessThanOrEqualTo(String value) {
            addCriterion("FUND_FLOW <=", value, "fundFlow");
            return (Criteria) this;
        }

        public Criteria andFundFlowLike(String value) {
            addCriterion("FUND_FLOW like", value, "fundFlow");
            return (Criteria) this;
        }

        public Criteria andFundFlowNotLike(String value) {
            addCriterion("FUND_FLOW not like", value, "fundFlow");
            return (Criteria) this;
        }

        public Criteria andFundFlowIn(List<String> values) {
            addCriterion("FUND_FLOW in", values, "fundFlow");
            return (Criteria) this;
        }

        public Criteria andFundFlowNotIn(List<String> values) {
            addCriterion("FUND_FLOW not in", values, "fundFlow");
            return (Criteria) this;
        }

        public Criteria andFundFlowBetween(String value1, String value2) {
            addCriterion("FUND_FLOW between", value1, value2, "fundFlow");
            return (Criteria) this;
        }

        public Criteria andFundFlowNotBetween(String value1, String value2) {
            addCriterion("FUND_FLOW not between", value1, value2, "fundFlow");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowIsNull() {
            addCriterion("FUND_DETAIL_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowIsNotNull() {
            addCriterion("FUND_DETAIL_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowEqualTo(String value) {
            addCriterion("FUND_DETAIL_FLOW =", value, "fundDetailFlow");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowNotEqualTo(String value) {
            addCriterion("FUND_DETAIL_FLOW <>", value, "fundDetailFlow");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowGreaterThan(String value) {
            addCriterion("FUND_DETAIL_FLOW >", value, "fundDetailFlow");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowGreaterThanOrEqualTo(String value) {
            addCriterion("FUND_DETAIL_FLOW >=", value, "fundDetailFlow");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowLessThan(String value) {
            addCriterion("FUND_DETAIL_FLOW <", value, "fundDetailFlow");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowLessThanOrEqualTo(String value) {
            addCriterion("FUND_DETAIL_FLOW <=", value, "fundDetailFlow");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowLike(String value) {
            addCriterion("FUND_DETAIL_FLOW like", value, "fundDetailFlow");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowNotLike(String value) {
            addCriterion("FUND_DETAIL_FLOW not like", value, "fundDetailFlow");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowIn(List<String> values) {
            addCriterion("FUND_DETAIL_FLOW in", values, "fundDetailFlow");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowNotIn(List<String> values) {
            addCriterion("FUND_DETAIL_FLOW not in", values, "fundDetailFlow");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowBetween(String value1, String value2) {
            addCriterion("FUND_DETAIL_FLOW between", value1, value2, "fundDetailFlow");
            return (Criteria) this;
        }

        public Criteria andFundDetailFlowNotBetween(String value1, String value2) {
            addCriterion("FUND_DETAIL_FLOW not between", value1, value2, "fundDetailFlow");
            return (Criteria) this;
        }

        public Criteria andOperateUserFlowIsNull() {
            addCriterion("OPERATE_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andOperateUserFlowIsNotNull() {
            addCriterion("OPERATE_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andOperateUserFlowEqualTo(String value) {
            addCriterion("OPERATE_USER_FLOW =", value, "operateUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperateUserFlowNotEqualTo(String value) {
            addCriterion("OPERATE_USER_FLOW <>", value, "operateUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperateUserFlowGreaterThan(String value) {
            addCriterion("OPERATE_USER_FLOW >", value, "operateUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperateUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("OPERATE_USER_FLOW >=", value, "operateUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperateUserFlowLessThan(String value) {
            addCriterion("OPERATE_USER_FLOW <", value, "operateUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperateUserFlowLessThanOrEqualTo(String value) {
            addCriterion("OPERATE_USER_FLOW <=", value, "operateUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperateUserFlowLike(String value) {
            addCriterion("OPERATE_USER_FLOW like", value, "operateUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperateUserFlowNotLike(String value) {
            addCriterion("OPERATE_USER_FLOW not like", value, "operateUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperateUserFlowIn(List<String> values) {
            addCriterion("OPERATE_USER_FLOW in", values, "operateUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperateUserFlowNotIn(List<String> values) {
            addCriterion("OPERATE_USER_FLOW not in", values, "operateUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperateUserFlowBetween(String value1, String value2) {
            addCriterion("OPERATE_USER_FLOW between", value1, value2, "operateUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperateUserFlowNotBetween(String value1, String value2) {
            addCriterion("OPERATE_USER_FLOW not between", value1, value2, "operateUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperateUserNameIsNull() {
            addCriterion("OPERATE_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOperateUserNameIsNotNull() {
            addCriterion("OPERATE_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOperateUserNameEqualTo(String value) {
            addCriterion("OPERATE_USER_NAME =", value, "operateUserName");
            return (Criteria) this;
        }

        public Criteria andOperateUserNameNotEqualTo(String value) {
            addCriterion("OPERATE_USER_NAME <>", value, "operateUserName");
            return (Criteria) this;
        }

        public Criteria andOperateUserNameGreaterThan(String value) {
            addCriterion("OPERATE_USER_NAME >", value, "operateUserName");
            return (Criteria) this;
        }

        public Criteria andOperateUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("OPERATE_USER_NAME >=", value, "operateUserName");
            return (Criteria) this;
        }

        public Criteria andOperateUserNameLessThan(String value) {
            addCriterion("OPERATE_USER_NAME <", value, "operateUserName");
            return (Criteria) this;
        }

        public Criteria andOperateUserNameLessThanOrEqualTo(String value) {
            addCriterion("OPERATE_USER_NAME <=", value, "operateUserName");
            return (Criteria) this;
        }

        public Criteria andOperateUserNameLike(String value) {
            addCriterion("OPERATE_USER_NAME like", value, "operateUserName");
            return (Criteria) this;
        }

        public Criteria andOperateUserNameNotLike(String value) {
            addCriterion("OPERATE_USER_NAME not like", value, "operateUserName");
            return (Criteria) this;
        }

        public Criteria andOperateUserNameIn(List<String> values) {
            addCriterion("OPERATE_USER_NAME in", values, "operateUserName");
            return (Criteria) this;
        }

        public Criteria andOperateUserNameNotIn(List<String> values) {
            addCriterion("OPERATE_USER_NAME not in", values, "operateUserName");
            return (Criteria) this;
        }

        public Criteria andOperateUserNameBetween(String value1, String value2) {
            addCriterion("OPERATE_USER_NAME between", value1, value2, "operateUserName");
            return (Criteria) this;
        }

        public Criteria andOperateUserNameNotBetween(String value1, String value2) {
            addCriterion("OPERATE_USER_NAME not between", value1, value2, "operateUserName");
            return (Criteria) this;
        }

        public Criteria andOperateTimeIsNull() {
            addCriterion("OPERATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andOperateTimeIsNotNull() {
            addCriterion("OPERATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andOperateTimeEqualTo(String value) {
            addCriterion("OPERATE_TIME =", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeNotEqualTo(String value) {
            addCriterion("OPERATE_TIME <>", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeGreaterThan(String value) {
            addCriterion("OPERATE_TIME >", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("OPERATE_TIME >=", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeLessThan(String value) {
            addCriterion("OPERATE_TIME <", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeLessThanOrEqualTo(String value) {
            addCriterion("OPERATE_TIME <=", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeLike(String value) {
            addCriterion("OPERATE_TIME like", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeNotLike(String value) {
            addCriterion("OPERATE_TIME not like", value, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeIn(List<String> values) {
            addCriterion("OPERATE_TIME in", values, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeNotIn(List<String> values) {
            addCriterion("OPERATE_TIME not in", values, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeBetween(String value1, String value2) {
            addCriterion("OPERATE_TIME between", value1, value2, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperateTimeNotBetween(String value1, String value2) {
            addCriterion("OPERATE_TIME not between", value1, value2, "operateTime");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdIsNull() {
            addCriterion("OPER_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdIsNotNull() {
            addCriterion("OPER_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdEqualTo(String value) {
            addCriterion("OPER_STATUS_ID =", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotEqualTo(String value) {
            addCriterion("OPER_STATUS_ID <>", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdGreaterThan(String value) {
            addCriterion("OPER_STATUS_ID >", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_ID >=", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdLessThan(String value) {
            addCriterion("OPER_STATUS_ID <", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdLessThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_ID <=", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdLike(String value) {
            addCriterion("OPER_STATUS_ID like", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotLike(String value) {
            addCriterion("OPER_STATUS_ID not like", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdIn(List<String> values) {
            addCriterion("OPER_STATUS_ID in", values, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotIn(List<String> values) {
            addCriterion("OPER_STATUS_ID not in", values, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_ID between", value1, value2, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_ID not between", value1, value2, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameIsNull() {
            addCriterion("OPER_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameIsNotNull() {
            addCriterion("OPER_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME =", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME <>", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameGreaterThan(String value) {
            addCriterion("OPER_STATUS_NAME >", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME >=", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameLessThan(String value) {
            addCriterion("OPER_STATUS_NAME <", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameLessThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME <=", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameLike(String value) {
            addCriterion("OPER_STATUS_NAME like", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotLike(String value) {
            addCriterion("OPER_STATUS_NAME not like", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameIn(List<String> values) {
            addCriterion("OPER_STATUS_NAME in", values, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotIn(List<String> values) {
            addCriterion("OPER_STATUS_NAME not in", values, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_NAME between", value1, value2, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_NAME not between", value1, value2, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("CONTENT =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("CONTENT <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("CONTENT >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("CONTENT >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("CONTENT <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("CONTENT <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("CONTENT like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("CONTENT not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("CONTENT in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("CONTENT not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("CONTENT between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("CONTENT not between", value1, value2, "content");
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