package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class EduUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EduUserExample() {
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

        public Criteria andUserFlowIsNull() {
            addCriterion("USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andUserFlowIsNotNull() {
            addCriterion("USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andUserFlowEqualTo(String value) {
            addCriterion("USER_FLOW =", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotEqualTo(String value) {
            addCriterion("USER_FLOW <>", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowGreaterThan(String value) {
            addCriterion("USER_FLOW >", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("USER_FLOW >=", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowLessThan(String value) {
            addCriterion("USER_FLOW <", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowLessThanOrEqualTo(String value) {
            addCriterion("USER_FLOW <=", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowLike(String value) {
            addCriterion("USER_FLOW like", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotLike(String value) {
            addCriterion("USER_FLOW not like", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowIn(List<String> values) {
            addCriterion("USER_FLOW in", values, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotIn(List<String> values) {
            addCriterion("USER_FLOW not in", values, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowBetween(String value1, String value2) {
            addCriterion("USER_FLOW between", value1, value2, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotBetween(String value1, String value2) {
            addCriterion("USER_FLOW not between", value1, value2, "userFlow");
            return (Criteria) this;
        }

        public Criteria andMajorIdIsNull() {
            addCriterion("MAJOR_ID is null");
            return (Criteria) this;
        }

        public Criteria andMajorIdIsNotNull() {
            addCriterion("MAJOR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMajorIdEqualTo(String value) {
            addCriterion("MAJOR_ID =", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotEqualTo(String value) {
            addCriterion("MAJOR_ID <>", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdGreaterThan(String value) {
            addCriterion("MAJOR_ID >", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdGreaterThanOrEqualTo(String value) {
            addCriterion("MAJOR_ID >=", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdLessThan(String value) {
            addCriterion("MAJOR_ID <", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdLessThanOrEqualTo(String value) {
            addCriterion("MAJOR_ID <=", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdLike(String value) {
            addCriterion("MAJOR_ID like", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotLike(String value) {
            addCriterion("MAJOR_ID not like", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdIn(List<String> values) {
            addCriterion("MAJOR_ID in", values, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotIn(List<String> values) {
            addCriterion("MAJOR_ID not in", values, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdBetween(String value1, String value2) {
            addCriterion("MAJOR_ID between", value1, value2, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotBetween(String value1, String value2) {
            addCriterion("MAJOR_ID not between", value1, value2, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorNameIsNull() {
            addCriterion("MAJOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMajorNameIsNotNull() {
            addCriterion("MAJOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMajorNameEqualTo(String value) {
            addCriterion("MAJOR_NAME =", value, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameNotEqualTo(String value) {
            addCriterion("MAJOR_NAME <>", value, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameGreaterThan(String value) {
            addCriterion("MAJOR_NAME >", value, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameGreaterThanOrEqualTo(String value) {
            addCriterion("MAJOR_NAME >=", value, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameLessThan(String value) {
            addCriterion("MAJOR_NAME <", value, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameLessThanOrEqualTo(String value) {
            addCriterion("MAJOR_NAME <=", value, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameLike(String value) {
            addCriterion("MAJOR_NAME like", value, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameNotLike(String value) {
            addCriterion("MAJOR_NAME not like", value, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameIn(List<String> values) {
            addCriterion("MAJOR_NAME in", values, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameNotIn(List<String> values) {
            addCriterion("MAJOR_NAME not in", values, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameBetween(String value1, String value2) {
            addCriterion("MAJOR_NAME between", value1, value2, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameNotBetween(String value1, String value2) {
            addCriterion("MAJOR_NAME not between", value1, value2, "majorName");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNull() {
            addCriterion("PERIOD is null");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNotNull() {
            addCriterion("PERIOD is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodEqualTo(String value) {
            addCriterion("PERIOD =", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotEqualTo(String value) {
            addCriterion("PERIOD <>", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThan(String value) {
            addCriterion("PERIOD >", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThanOrEqualTo(String value) {
            addCriterion("PERIOD >=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThan(String value) {
            addCriterion("PERIOD <", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThanOrEqualTo(String value) {
            addCriterion("PERIOD <=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLike(String value) {
            addCriterion("PERIOD like", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotLike(String value) {
            addCriterion("PERIOD not like", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodIn(List<String> values) {
            addCriterion("PERIOD in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotIn(List<String> values) {
            addCriterion("PERIOD not in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodBetween(String value1, String value2) {
            addCriterion("PERIOD between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotBetween(String value1, String value2) {
            addCriterion("PERIOD not between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andSidIsNull() {
            addCriterion("SID is null");
            return (Criteria) this;
        }

        public Criteria andSidIsNotNull() {
            addCriterion("SID is not null");
            return (Criteria) this;
        }

        public Criteria andSidEqualTo(String value) {
            addCriterion("SID =", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotEqualTo(String value) {
            addCriterion("SID <>", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThan(String value) {
            addCriterion("SID >", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThanOrEqualTo(String value) {
            addCriterion("SID >=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThan(String value) {
            addCriterion("SID <", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThanOrEqualTo(String value) {
            addCriterion("SID <=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLike(String value) {
            addCriterion("SID like", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotLike(String value) {
            addCriterion("SID not like", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidIn(List<String> values) {
            addCriterion("SID in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotIn(List<String> values) {
            addCriterion("SID not in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidBetween(String value1, String value2) {
            addCriterion("SID between", value1, value2, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotBetween(String value1, String value2) {
            addCriterion("SID not between", value1, value2, "sid");
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

        public Criteria andCreditIsNull() {
            addCriterion("CREDIT is null");
            return (Criteria) this;
        }

        public Criteria andCreditIsNotNull() {
            addCriterion("CREDIT is not null");
            return (Criteria) this;
        }

        public Criteria andCreditEqualTo(String value) {
            addCriterion("CREDIT =", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotEqualTo(String value) {
            addCriterion("CREDIT <>", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThan(String value) {
            addCriterion("CREDIT >", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThanOrEqualTo(String value) {
            addCriterion("CREDIT >=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThan(String value) {
            addCriterion("CREDIT <", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThanOrEqualTo(String value) {
            addCriterion("CREDIT <=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLike(String value) {
            addCriterion("CREDIT like", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotLike(String value) {
            addCriterion("CREDIT not like", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditIn(List<String> values) {
            addCriterion("CREDIT in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotIn(List<String> values) {
            addCriterion("CREDIT not in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditBetween(String value1, String value2) {
            addCriterion("CREDIT between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotBetween(String value1, String value2) {
            addCriterion("CREDIT not between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andIntroIsNull() {
            addCriterion("INTRO is null");
            return (Criteria) this;
        }

        public Criteria andIntroIsNotNull() {
            addCriterion("INTRO is not null");
            return (Criteria) this;
        }

        public Criteria andIntroEqualTo(String value) {
            addCriterion("INTRO =", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotEqualTo(String value) {
            addCriterion("INTRO <>", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroGreaterThan(String value) {
            addCriterion("INTRO >", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroGreaterThanOrEqualTo(String value) {
            addCriterion("INTRO >=", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroLessThan(String value) {
            addCriterion("INTRO <", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroLessThanOrEqualTo(String value) {
            addCriterion("INTRO <=", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroLike(String value) {
            addCriterion("INTRO like", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotLike(String value) {
            addCriterion("INTRO not like", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroIn(List<String> values) {
            addCriterion("INTRO in", values, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotIn(List<String> values) {
            addCriterion("INTRO not in", values, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroBetween(String value1, String value2) {
            addCriterion("INTRO between", value1, value2, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotBetween(String value1, String value2) {
            addCriterion("INTRO not between", value1, value2, "intro");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNull() {
            addCriterion("CLASS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNotNull() {
            addCriterion("CLASS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andClassNameEqualTo(String value) {
            addCriterion("CLASS_NAME =", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotEqualTo(String value) {
            addCriterion("CLASS_NAME <>", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThan(String value) {
            addCriterion("CLASS_NAME >", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("CLASS_NAME >=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThan(String value) {
            addCriterion("CLASS_NAME <", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThanOrEqualTo(String value) {
            addCriterion("CLASS_NAME <=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLike(String value) {
            addCriterion("CLASS_NAME like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotLike(String value) {
            addCriterion("CLASS_NAME not like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameIn(List<String> values) {
            addCriterion("CLASS_NAME in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotIn(List<String> values) {
            addCriterion("CLASS_NAME not in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameBetween(String value1, String value2) {
            addCriterion("CLASS_NAME between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotBetween(String value1, String value2) {
            addCriterion("CLASS_NAME not between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andNameSpellIsNull() {
            addCriterion("NAME_SPELL is null");
            return (Criteria) this;
        }

        public Criteria andNameSpellIsNotNull() {
            addCriterion("NAME_SPELL is not null");
            return (Criteria) this;
        }

        public Criteria andNameSpellEqualTo(String value) {
            addCriterion("NAME_SPELL =", value, "nameSpell");
            return (Criteria) this;
        }

        public Criteria andNameSpellNotEqualTo(String value) {
            addCriterion("NAME_SPELL <>", value, "nameSpell");
            return (Criteria) this;
        }

        public Criteria andNameSpellGreaterThan(String value) {
            addCriterion("NAME_SPELL >", value, "nameSpell");
            return (Criteria) this;
        }

        public Criteria andNameSpellGreaterThanOrEqualTo(String value) {
            addCriterion("NAME_SPELL >=", value, "nameSpell");
            return (Criteria) this;
        }

        public Criteria andNameSpellLessThan(String value) {
            addCriterion("NAME_SPELL <", value, "nameSpell");
            return (Criteria) this;
        }

        public Criteria andNameSpellLessThanOrEqualTo(String value) {
            addCriterion("NAME_SPELL <=", value, "nameSpell");
            return (Criteria) this;
        }

        public Criteria andNameSpellLike(String value) {
            addCriterion("NAME_SPELL like", value, "nameSpell");
            return (Criteria) this;
        }

        public Criteria andNameSpellNotLike(String value) {
            addCriterion("NAME_SPELL not like", value, "nameSpell");
            return (Criteria) this;
        }

        public Criteria andNameSpellIn(List<String> values) {
            addCriterion("NAME_SPELL in", values, "nameSpell");
            return (Criteria) this;
        }

        public Criteria andNameSpellNotIn(List<String> values) {
            addCriterion("NAME_SPELL not in", values, "nameSpell");
            return (Criteria) this;
        }

        public Criteria andNameSpellBetween(String value1, String value2) {
            addCriterion("NAME_SPELL between", value1, value2, "nameSpell");
            return (Criteria) this;
        }

        public Criteria andNameSpellNotBetween(String value1, String value2) {
            addCriterion("NAME_SPELL not between", value1, value2, "nameSpell");
            return (Criteria) this;
        }

        public Criteria andStudentCodeIsNull() {
            addCriterion("STUDENT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andStudentCodeIsNotNull() {
            addCriterion("STUDENT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andStudentCodeEqualTo(String value) {
            addCriterion("STUDENT_CODE =", value, "studentCode");
            return (Criteria) this;
        }

        public Criteria andStudentCodeNotEqualTo(String value) {
            addCriterion("STUDENT_CODE <>", value, "studentCode");
            return (Criteria) this;
        }

        public Criteria andStudentCodeGreaterThan(String value) {
            addCriterion("STUDENT_CODE >", value, "studentCode");
            return (Criteria) this;
        }

        public Criteria andStudentCodeGreaterThanOrEqualTo(String value) {
            addCriterion("STUDENT_CODE >=", value, "studentCode");
            return (Criteria) this;
        }

        public Criteria andStudentCodeLessThan(String value) {
            addCriterion("STUDENT_CODE <", value, "studentCode");
            return (Criteria) this;
        }

        public Criteria andStudentCodeLessThanOrEqualTo(String value) {
            addCriterion("STUDENT_CODE <=", value, "studentCode");
            return (Criteria) this;
        }

        public Criteria andStudentCodeLike(String value) {
            addCriterion("STUDENT_CODE like", value, "studentCode");
            return (Criteria) this;
        }

        public Criteria andStudentCodeNotLike(String value) {
            addCriterion("STUDENT_CODE not like", value, "studentCode");
            return (Criteria) this;
        }

        public Criteria andStudentCodeIn(List<String> values) {
            addCriterion("STUDENT_CODE in", values, "studentCode");
            return (Criteria) this;
        }

        public Criteria andStudentCodeNotIn(List<String> values) {
            addCriterion("STUDENT_CODE not in", values, "studentCode");
            return (Criteria) this;
        }

        public Criteria andStudentCodeBetween(String value1, String value2) {
            addCriterion("STUDENT_CODE between", value1, value2, "studentCode");
            return (Criteria) this;
        }

        public Criteria andStudentCodeNotBetween(String value1, String value2) {
            addCriterion("STUDENT_CODE not between", value1, value2, "studentCode");
            return (Criteria) this;
        }

        public Criteria andRecordLocationIdIsNull() {
            addCriterion("RECORD_LOCATION_ID is null");
            return (Criteria) this;
        }

        public Criteria andRecordLocationIdIsNotNull() {
            addCriterion("RECORD_LOCATION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRecordLocationIdEqualTo(String value) {
            addCriterion("RECORD_LOCATION_ID =", value, "recordLocationId");
            return (Criteria) this;
        }

        public Criteria andRecordLocationIdNotEqualTo(String value) {
            addCriterion("RECORD_LOCATION_ID <>", value, "recordLocationId");
            return (Criteria) this;
        }

        public Criteria andRecordLocationIdGreaterThan(String value) {
            addCriterion("RECORD_LOCATION_ID >", value, "recordLocationId");
            return (Criteria) this;
        }

        public Criteria andRecordLocationIdGreaterThanOrEqualTo(String value) {
            addCriterion("RECORD_LOCATION_ID >=", value, "recordLocationId");
            return (Criteria) this;
        }

        public Criteria andRecordLocationIdLessThan(String value) {
            addCriterion("RECORD_LOCATION_ID <", value, "recordLocationId");
            return (Criteria) this;
        }

        public Criteria andRecordLocationIdLessThanOrEqualTo(String value) {
            addCriterion("RECORD_LOCATION_ID <=", value, "recordLocationId");
            return (Criteria) this;
        }

        public Criteria andRecordLocationIdLike(String value) {
            addCriterion("RECORD_LOCATION_ID like", value, "recordLocationId");
            return (Criteria) this;
        }

        public Criteria andRecordLocationIdNotLike(String value) {
            addCriterion("RECORD_LOCATION_ID not like", value, "recordLocationId");
            return (Criteria) this;
        }

        public Criteria andRecordLocationIdIn(List<String> values) {
            addCriterion("RECORD_LOCATION_ID in", values, "recordLocationId");
            return (Criteria) this;
        }

        public Criteria andRecordLocationIdNotIn(List<String> values) {
            addCriterion("RECORD_LOCATION_ID not in", values, "recordLocationId");
            return (Criteria) this;
        }

        public Criteria andRecordLocationIdBetween(String value1, String value2) {
            addCriterion("RECORD_LOCATION_ID between", value1, value2, "recordLocationId");
            return (Criteria) this;
        }

        public Criteria andRecordLocationIdNotBetween(String value1, String value2) {
            addCriterion("RECORD_LOCATION_ID not between", value1, value2, "recordLocationId");
            return (Criteria) this;
        }

        public Criteria andRecordLocationNameIsNull() {
            addCriterion("RECORD_LOCATION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRecordLocationNameIsNotNull() {
            addCriterion("RECORD_LOCATION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRecordLocationNameEqualTo(String value) {
            addCriterion("RECORD_LOCATION_NAME =", value, "recordLocationName");
            return (Criteria) this;
        }

        public Criteria andRecordLocationNameNotEqualTo(String value) {
            addCriterion("RECORD_LOCATION_NAME <>", value, "recordLocationName");
            return (Criteria) this;
        }

        public Criteria andRecordLocationNameGreaterThan(String value) {
            addCriterion("RECORD_LOCATION_NAME >", value, "recordLocationName");
            return (Criteria) this;
        }

        public Criteria andRecordLocationNameGreaterThanOrEqualTo(String value) {
            addCriterion("RECORD_LOCATION_NAME >=", value, "recordLocationName");
            return (Criteria) this;
        }

        public Criteria andRecordLocationNameLessThan(String value) {
            addCriterion("RECORD_LOCATION_NAME <", value, "recordLocationName");
            return (Criteria) this;
        }

        public Criteria andRecordLocationNameLessThanOrEqualTo(String value) {
            addCriterion("RECORD_LOCATION_NAME <=", value, "recordLocationName");
            return (Criteria) this;
        }

        public Criteria andRecordLocationNameLike(String value) {
            addCriterion("RECORD_LOCATION_NAME like", value, "recordLocationName");
            return (Criteria) this;
        }

        public Criteria andRecordLocationNameNotLike(String value) {
            addCriterion("RECORD_LOCATION_NAME not like", value, "recordLocationName");
            return (Criteria) this;
        }

        public Criteria andRecordLocationNameIn(List<String> values) {
            addCriterion("RECORD_LOCATION_NAME in", values, "recordLocationName");
            return (Criteria) this;
        }

        public Criteria andRecordLocationNameNotIn(List<String> values) {
            addCriterion("RECORD_LOCATION_NAME not in", values, "recordLocationName");
            return (Criteria) this;
        }

        public Criteria andRecordLocationNameBetween(String value1, String value2) {
            addCriterion("RECORD_LOCATION_NAME between", value1, value2, "recordLocationName");
            return (Criteria) this;
        }

        public Criteria andRecordLocationNameNotBetween(String value1, String value2) {
            addCriterion("RECORD_LOCATION_NAME not between", value1, value2, "recordLocationName");
            return (Criteria) this;
        }

        public Criteria andStudentSourceIdIsNull() {
            addCriterion("STUDENT_SOURCE_ID is null");
            return (Criteria) this;
        }

        public Criteria andStudentSourceIdIsNotNull() {
            addCriterion("STUDENT_SOURCE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStudentSourceIdEqualTo(String value) {
            addCriterion("STUDENT_SOURCE_ID =", value, "studentSourceId");
            return (Criteria) this;
        }

        public Criteria andStudentSourceIdNotEqualTo(String value) {
            addCriterion("STUDENT_SOURCE_ID <>", value, "studentSourceId");
            return (Criteria) this;
        }

        public Criteria andStudentSourceIdGreaterThan(String value) {
            addCriterion("STUDENT_SOURCE_ID >", value, "studentSourceId");
            return (Criteria) this;
        }

        public Criteria andStudentSourceIdGreaterThanOrEqualTo(String value) {
            addCriterion("STUDENT_SOURCE_ID >=", value, "studentSourceId");
            return (Criteria) this;
        }

        public Criteria andStudentSourceIdLessThan(String value) {
            addCriterion("STUDENT_SOURCE_ID <", value, "studentSourceId");
            return (Criteria) this;
        }

        public Criteria andStudentSourceIdLessThanOrEqualTo(String value) {
            addCriterion("STUDENT_SOURCE_ID <=", value, "studentSourceId");
            return (Criteria) this;
        }

        public Criteria andStudentSourceIdLike(String value) {
            addCriterion("STUDENT_SOURCE_ID like", value, "studentSourceId");
            return (Criteria) this;
        }

        public Criteria andStudentSourceIdNotLike(String value) {
            addCriterion("STUDENT_SOURCE_ID not like", value, "studentSourceId");
            return (Criteria) this;
        }

        public Criteria andStudentSourceIdIn(List<String> values) {
            addCriterion("STUDENT_SOURCE_ID in", values, "studentSourceId");
            return (Criteria) this;
        }

        public Criteria andStudentSourceIdNotIn(List<String> values) {
            addCriterion("STUDENT_SOURCE_ID not in", values, "studentSourceId");
            return (Criteria) this;
        }

        public Criteria andStudentSourceIdBetween(String value1, String value2) {
            addCriterion("STUDENT_SOURCE_ID between", value1, value2, "studentSourceId");
            return (Criteria) this;
        }

        public Criteria andStudentSourceIdNotBetween(String value1, String value2) {
            addCriterion("STUDENT_SOURCE_ID not between", value1, value2, "studentSourceId");
            return (Criteria) this;
        }

        public Criteria andStudentSourceNameIsNull() {
            addCriterion("STUDENT_SOURCE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStudentSourceNameIsNotNull() {
            addCriterion("STUDENT_SOURCE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStudentSourceNameEqualTo(String value) {
            addCriterion("STUDENT_SOURCE_NAME =", value, "studentSourceName");
            return (Criteria) this;
        }

        public Criteria andStudentSourceNameNotEqualTo(String value) {
            addCriterion("STUDENT_SOURCE_NAME <>", value, "studentSourceName");
            return (Criteria) this;
        }

        public Criteria andStudentSourceNameGreaterThan(String value) {
            addCriterion("STUDENT_SOURCE_NAME >", value, "studentSourceName");
            return (Criteria) this;
        }

        public Criteria andStudentSourceNameGreaterThanOrEqualTo(String value) {
            addCriterion("STUDENT_SOURCE_NAME >=", value, "studentSourceName");
            return (Criteria) this;
        }

        public Criteria andStudentSourceNameLessThan(String value) {
            addCriterion("STUDENT_SOURCE_NAME <", value, "studentSourceName");
            return (Criteria) this;
        }

        public Criteria andStudentSourceNameLessThanOrEqualTo(String value) {
            addCriterion("STUDENT_SOURCE_NAME <=", value, "studentSourceName");
            return (Criteria) this;
        }

        public Criteria andStudentSourceNameLike(String value) {
            addCriterion("STUDENT_SOURCE_NAME like", value, "studentSourceName");
            return (Criteria) this;
        }

        public Criteria andStudentSourceNameNotLike(String value) {
            addCriterion("STUDENT_SOURCE_NAME not like", value, "studentSourceName");
            return (Criteria) this;
        }

        public Criteria andStudentSourceNameIn(List<String> values) {
            addCriterion("STUDENT_SOURCE_NAME in", values, "studentSourceName");
            return (Criteria) this;
        }

        public Criteria andStudentSourceNameNotIn(List<String> values) {
            addCriterion("STUDENT_SOURCE_NAME not in", values, "studentSourceName");
            return (Criteria) this;
        }

        public Criteria andStudentSourceNameBetween(String value1, String value2) {
            addCriterion("STUDENT_SOURCE_NAME between", value1, value2, "studentSourceName");
            return (Criteria) this;
        }

        public Criteria andStudentSourceNameNotBetween(String value1, String value2) {
            addCriterion("STUDENT_SOURCE_NAME not between", value1, value2, "studentSourceName");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeIdIsNull() {
            addCriterion("ADMIT_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeIdIsNotNull() {
            addCriterion("ADMIT_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeIdEqualTo(String value) {
            addCriterion("ADMIT_TYPE_ID =", value, "admitTypeId");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeIdNotEqualTo(String value) {
            addCriterion("ADMIT_TYPE_ID <>", value, "admitTypeId");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeIdGreaterThan(String value) {
            addCriterion("ADMIT_TYPE_ID >", value, "admitTypeId");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("ADMIT_TYPE_ID >=", value, "admitTypeId");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeIdLessThan(String value) {
            addCriterion("ADMIT_TYPE_ID <", value, "admitTypeId");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeIdLessThanOrEqualTo(String value) {
            addCriterion("ADMIT_TYPE_ID <=", value, "admitTypeId");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeIdLike(String value) {
            addCriterion("ADMIT_TYPE_ID like", value, "admitTypeId");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeIdNotLike(String value) {
            addCriterion("ADMIT_TYPE_ID not like", value, "admitTypeId");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeIdIn(List<String> values) {
            addCriterion("ADMIT_TYPE_ID in", values, "admitTypeId");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeIdNotIn(List<String> values) {
            addCriterion("ADMIT_TYPE_ID not in", values, "admitTypeId");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeIdBetween(String value1, String value2) {
            addCriterion("ADMIT_TYPE_ID between", value1, value2, "admitTypeId");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeIdNotBetween(String value1, String value2) {
            addCriterion("ADMIT_TYPE_ID not between", value1, value2, "admitTypeId");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeNameIsNull() {
            addCriterion("ADMIT_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeNameIsNotNull() {
            addCriterion("ADMIT_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeNameEqualTo(String value) {
            addCriterion("ADMIT_TYPE_NAME =", value, "admitTypeName");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeNameNotEqualTo(String value) {
            addCriterion("ADMIT_TYPE_NAME <>", value, "admitTypeName");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeNameGreaterThan(String value) {
            addCriterion("ADMIT_TYPE_NAME >", value, "admitTypeName");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("ADMIT_TYPE_NAME >=", value, "admitTypeName");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeNameLessThan(String value) {
            addCriterion("ADMIT_TYPE_NAME <", value, "admitTypeName");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeNameLessThanOrEqualTo(String value) {
            addCriterion("ADMIT_TYPE_NAME <=", value, "admitTypeName");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeNameLike(String value) {
            addCriterion("ADMIT_TYPE_NAME like", value, "admitTypeName");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeNameNotLike(String value) {
            addCriterion("ADMIT_TYPE_NAME not like", value, "admitTypeName");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeNameIn(List<String> values) {
            addCriterion("ADMIT_TYPE_NAME in", values, "admitTypeName");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeNameNotIn(List<String> values) {
            addCriterion("ADMIT_TYPE_NAME not in", values, "admitTypeName");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeNameBetween(String value1, String value2) {
            addCriterion("ADMIT_TYPE_NAME between", value1, value2, "admitTypeName");
            return (Criteria) this;
        }

        public Criteria andAdmitTypeNameNotBetween(String value1, String value2) {
            addCriterion("ADMIT_TYPE_NAME not between", value1, value2, "admitTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdIsNull() {
            addCriterion("TRAIN_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdIsNotNull() {
            addCriterion("TRAIN_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdEqualTo(String value) {
            addCriterion("TRAIN_TYPE_ID =", value, "trainTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdNotEqualTo(String value) {
            addCriterion("TRAIN_TYPE_ID <>", value, "trainTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdGreaterThan(String value) {
            addCriterion("TRAIN_TYPE_ID >", value, "trainTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_TYPE_ID >=", value, "trainTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdLessThan(String value) {
            addCriterion("TRAIN_TYPE_ID <", value, "trainTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_TYPE_ID <=", value, "trainTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdLike(String value) {
            addCriterion("TRAIN_TYPE_ID like", value, "trainTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdNotLike(String value) {
            addCriterion("TRAIN_TYPE_ID not like", value, "trainTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdIn(List<String> values) {
            addCriterion("TRAIN_TYPE_ID in", values, "trainTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdNotIn(List<String> values) {
            addCriterion("TRAIN_TYPE_ID not in", values, "trainTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdBetween(String value1, String value2) {
            addCriterion("TRAIN_TYPE_ID between", value1, value2, "trainTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdNotBetween(String value1, String value2) {
            addCriterion("TRAIN_TYPE_ID not between", value1, value2, "trainTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameIsNull() {
            addCriterion("TRAIN_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameIsNotNull() {
            addCriterion("TRAIN_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameEqualTo(String value) {
            addCriterion("TRAIN_TYPE_NAME =", value, "trainTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameNotEqualTo(String value) {
            addCriterion("TRAIN_TYPE_NAME <>", value, "trainTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameGreaterThan(String value) {
            addCriterion("TRAIN_TYPE_NAME >", value, "trainTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_TYPE_NAME >=", value, "trainTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameLessThan(String value) {
            addCriterion("TRAIN_TYPE_NAME <", value, "trainTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_TYPE_NAME <=", value, "trainTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameLike(String value) {
            addCriterion("TRAIN_TYPE_NAME like", value, "trainTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameNotLike(String value) {
            addCriterion("TRAIN_TYPE_NAME not like", value, "trainTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameIn(List<String> values) {
            addCriterion("TRAIN_TYPE_NAME in", values, "trainTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameNotIn(List<String> values) {
            addCriterion("TRAIN_TYPE_NAME not in", values, "trainTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameBetween(String value1, String value2) {
            addCriterion("TRAIN_TYPE_NAME between", value1, value2, "trainTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameNotBetween(String value1, String value2) {
            addCriterion("TRAIN_TYPE_NAME not between", value1, value2, "trainTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainOrgIdIsNull() {
            addCriterion("TRAIN_ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andTrainOrgIdIsNotNull() {
            addCriterion("TRAIN_ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTrainOrgIdEqualTo(String value) {
            addCriterion("TRAIN_ORG_ID =", value, "trainOrgId");
            return (Criteria) this;
        }

        public Criteria andTrainOrgIdNotEqualTo(String value) {
            addCriterion("TRAIN_ORG_ID <>", value, "trainOrgId");
            return (Criteria) this;
        }

        public Criteria andTrainOrgIdGreaterThan(String value) {
            addCriterion("TRAIN_ORG_ID >", value, "trainOrgId");
            return (Criteria) this;
        }

        public Criteria andTrainOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_ORG_ID >=", value, "trainOrgId");
            return (Criteria) this;
        }

        public Criteria andTrainOrgIdLessThan(String value) {
            addCriterion("TRAIN_ORG_ID <", value, "trainOrgId");
            return (Criteria) this;
        }

        public Criteria andTrainOrgIdLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_ORG_ID <=", value, "trainOrgId");
            return (Criteria) this;
        }

        public Criteria andTrainOrgIdLike(String value) {
            addCriterion("TRAIN_ORG_ID like", value, "trainOrgId");
            return (Criteria) this;
        }

        public Criteria andTrainOrgIdNotLike(String value) {
            addCriterion("TRAIN_ORG_ID not like", value, "trainOrgId");
            return (Criteria) this;
        }

        public Criteria andTrainOrgIdIn(List<String> values) {
            addCriterion("TRAIN_ORG_ID in", values, "trainOrgId");
            return (Criteria) this;
        }

        public Criteria andTrainOrgIdNotIn(List<String> values) {
            addCriterion("TRAIN_ORG_ID not in", values, "trainOrgId");
            return (Criteria) this;
        }

        public Criteria andTrainOrgIdBetween(String value1, String value2) {
            addCriterion("TRAIN_ORG_ID between", value1, value2, "trainOrgId");
            return (Criteria) this;
        }

        public Criteria andTrainOrgIdNotBetween(String value1, String value2) {
            addCriterion("TRAIN_ORG_ID not between", value1, value2, "trainOrgId");
            return (Criteria) this;
        }

        public Criteria andTrainOrgNameIsNull() {
            addCriterion("TRAIN_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTrainOrgNameIsNotNull() {
            addCriterion("TRAIN_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTrainOrgNameEqualTo(String value) {
            addCriterion("TRAIN_ORG_NAME =", value, "trainOrgName");
            return (Criteria) this;
        }

        public Criteria andTrainOrgNameNotEqualTo(String value) {
            addCriterion("TRAIN_ORG_NAME <>", value, "trainOrgName");
            return (Criteria) this;
        }

        public Criteria andTrainOrgNameGreaterThan(String value) {
            addCriterion("TRAIN_ORG_NAME >", value, "trainOrgName");
            return (Criteria) this;
        }

        public Criteria andTrainOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_ORG_NAME >=", value, "trainOrgName");
            return (Criteria) this;
        }

        public Criteria andTrainOrgNameLessThan(String value) {
            addCriterion("TRAIN_ORG_NAME <", value, "trainOrgName");
            return (Criteria) this;
        }

        public Criteria andTrainOrgNameLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_ORG_NAME <=", value, "trainOrgName");
            return (Criteria) this;
        }

        public Criteria andTrainOrgNameLike(String value) {
            addCriterion("TRAIN_ORG_NAME like", value, "trainOrgName");
            return (Criteria) this;
        }

        public Criteria andTrainOrgNameNotLike(String value) {
            addCriterion("TRAIN_ORG_NAME not like", value, "trainOrgName");
            return (Criteria) this;
        }

        public Criteria andTrainOrgNameIn(List<String> values) {
            addCriterion("TRAIN_ORG_NAME in", values, "trainOrgName");
            return (Criteria) this;
        }

        public Criteria andTrainOrgNameNotIn(List<String> values) {
            addCriterion("TRAIN_ORG_NAME not in", values, "trainOrgName");
            return (Criteria) this;
        }

        public Criteria andTrainOrgNameBetween(String value1, String value2) {
            addCriterion("TRAIN_ORG_NAME between", value1, value2, "trainOrgName");
            return (Criteria) this;
        }

        public Criteria andTrainOrgNameNotBetween(String value1, String value2) {
            addCriterion("TRAIN_ORG_NAME not between", value1, value2, "trainOrgName");
            return (Criteria) this;
        }

        public Criteria andIs5plus3IsNull() {
            addCriterion("IS5PLUS3 is null");
            return (Criteria) this;
        }

        public Criteria andIs5plus3IsNotNull() {
            addCriterion("IS5PLUS3 is not null");
            return (Criteria) this;
        }

        public Criteria andIs5plus3EqualTo(String value) {
            addCriterion("IS5PLUS3 =", value, "is5plus3");
            return (Criteria) this;
        }

        public Criteria andIs5plus3NotEqualTo(String value) {
            addCriterion("IS5PLUS3 <>", value, "is5plus3");
            return (Criteria) this;
        }

        public Criteria andIs5plus3GreaterThan(String value) {
            addCriterion("IS5PLUS3 >", value, "is5plus3");
            return (Criteria) this;
        }

        public Criteria andIs5plus3GreaterThanOrEqualTo(String value) {
            addCriterion("IS5PLUS3 >=", value, "is5plus3");
            return (Criteria) this;
        }

        public Criteria andIs5plus3LessThan(String value) {
            addCriterion("IS5PLUS3 <", value, "is5plus3");
            return (Criteria) this;
        }

        public Criteria andIs5plus3LessThanOrEqualTo(String value) {
            addCriterion("IS5PLUS3 <=", value, "is5plus3");
            return (Criteria) this;
        }

        public Criteria andIs5plus3Like(String value) {
            addCriterion("IS5PLUS3 like", value, "is5plus3");
            return (Criteria) this;
        }

        public Criteria andIs5plus3NotLike(String value) {
            addCriterion("IS5PLUS3 not like", value, "is5plus3");
            return (Criteria) this;
        }

        public Criteria andIs5plus3In(List<String> values) {
            addCriterion("IS5PLUS3 in", values, "is5plus3");
            return (Criteria) this;
        }

        public Criteria andIs5plus3NotIn(List<String> values) {
            addCriterion("IS5PLUS3 not in", values, "is5plus3");
            return (Criteria) this;
        }

        public Criteria andIs5plus3Between(String value1, String value2) {
            addCriterion("IS5PLUS3 between", value1, value2, "is5plus3");
            return (Criteria) this;
        }

        public Criteria andIs5plus3NotBetween(String value1, String value2) {
            addCriterion("IS5PLUS3 not between", value1, value2, "is5plus3");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherIsNull() {
            addCriterion("FIRST_TEACHER is null");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherIsNotNull() {
            addCriterion("FIRST_TEACHER is not null");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherEqualTo(String value) {
            addCriterion("FIRST_TEACHER =", value, "firstTeacher");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherNotEqualTo(String value) {
            addCriterion("FIRST_TEACHER <>", value, "firstTeacher");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherGreaterThan(String value) {
            addCriterion("FIRST_TEACHER >", value, "firstTeacher");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherGreaterThanOrEqualTo(String value) {
            addCriterion("FIRST_TEACHER >=", value, "firstTeacher");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherLessThan(String value) {
            addCriterion("FIRST_TEACHER <", value, "firstTeacher");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherLessThanOrEqualTo(String value) {
            addCriterion("FIRST_TEACHER <=", value, "firstTeacher");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherLike(String value) {
            addCriterion("FIRST_TEACHER like", value, "firstTeacher");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherNotLike(String value) {
            addCriterion("FIRST_TEACHER not like", value, "firstTeacher");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherIn(List<String> values) {
            addCriterion("FIRST_TEACHER in", values, "firstTeacher");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherNotIn(List<String> values) {
            addCriterion("FIRST_TEACHER not in", values, "firstTeacher");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherBetween(String value1, String value2) {
            addCriterion("FIRST_TEACHER between", value1, value2, "firstTeacher");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherNotBetween(String value1, String value2) {
            addCriterion("FIRST_TEACHER not between", value1, value2, "firstTeacher");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherIsNull() {
            addCriterion("SECOND_TEACHER is null");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherIsNotNull() {
            addCriterion("SECOND_TEACHER is not null");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherEqualTo(String value) {
            addCriterion("SECOND_TEACHER =", value, "secondTeacher");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherNotEqualTo(String value) {
            addCriterion("SECOND_TEACHER <>", value, "secondTeacher");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherGreaterThan(String value) {
            addCriterion("SECOND_TEACHER >", value, "secondTeacher");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherGreaterThanOrEqualTo(String value) {
            addCriterion("SECOND_TEACHER >=", value, "secondTeacher");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherLessThan(String value) {
            addCriterion("SECOND_TEACHER <", value, "secondTeacher");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherLessThanOrEqualTo(String value) {
            addCriterion("SECOND_TEACHER <=", value, "secondTeacher");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherLike(String value) {
            addCriterion("SECOND_TEACHER like", value, "secondTeacher");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherNotLike(String value) {
            addCriterion("SECOND_TEACHER not like", value, "secondTeacher");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherIn(List<String> values) {
            addCriterion("SECOND_TEACHER in", values, "secondTeacher");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherNotIn(List<String> values) {
            addCriterion("SECOND_TEACHER not in", values, "secondTeacher");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherBetween(String value1, String value2) {
            addCriterion("SECOND_TEACHER between", value1, value2, "secondTeacher");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherNotBetween(String value1, String value2) {
            addCriterion("SECOND_TEACHER not between", value1, value2, "secondTeacher");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdIsNull() {
            addCriterion("STUDY_FORM_ID is null");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdIsNotNull() {
            addCriterion("STUDY_FORM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdEqualTo(String value) {
            addCriterion("STUDY_FORM_ID =", value, "studyFormId");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdNotEqualTo(String value) {
            addCriterion("STUDY_FORM_ID <>", value, "studyFormId");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdGreaterThan(String value) {
            addCriterion("STUDY_FORM_ID >", value, "studyFormId");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdGreaterThanOrEqualTo(String value) {
            addCriterion("STUDY_FORM_ID >=", value, "studyFormId");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdLessThan(String value) {
            addCriterion("STUDY_FORM_ID <", value, "studyFormId");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdLessThanOrEqualTo(String value) {
            addCriterion("STUDY_FORM_ID <=", value, "studyFormId");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdLike(String value) {
            addCriterion("STUDY_FORM_ID like", value, "studyFormId");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdNotLike(String value) {
            addCriterion("STUDY_FORM_ID not like", value, "studyFormId");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdIn(List<String> values) {
            addCriterion("STUDY_FORM_ID in", values, "studyFormId");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdNotIn(List<String> values) {
            addCriterion("STUDY_FORM_ID not in", values, "studyFormId");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdBetween(String value1, String value2) {
            addCriterion("STUDY_FORM_ID between", value1, value2, "studyFormId");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdNotBetween(String value1, String value2) {
            addCriterion("STUDY_FORM_ID not between", value1, value2, "studyFormId");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameIsNull() {
            addCriterion("STUDY_FORM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameIsNotNull() {
            addCriterion("STUDY_FORM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameEqualTo(String value) {
            addCriterion("STUDY_FORM_NAME =", value, "studyFormName");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameNotEqualTo(String value) {
            addCriterion("STUDY_FORM_NAME <>", value, "studyFormName");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameGreaterThan(String value) {
            addCriterion("STUDY_FORM_NAME >", value, "studyFormName");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameGreaterThanOrEqualTo(String value) {
            addCriterion("STUDY_FORM_NAME >=", value, "studyFormName");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameLessThan(String value) {
            addCriterion("STUDY_FORM_NAME <", value, "studyFormName");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameLessThanOrEqualTo(String value) {
            addCriterion("STUDY_FORM_NAME <=", value, "studyFormName");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameLike(String value) {
            addCriterion("STUDY_FORM_NAME like", value, "studyFormName");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameNotLike(String value) {
            addCriterion("STUDY_FORM_NAME not like", value, "studyFormName");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameIn(List<String> values) {
            addCriterion("STUDY_FORM_NAME in", values, "studyFormName");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameNotIn(List<String> values) {
            addCriterion("STUDY_FORM_NAME not in", values, "studyFormName");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameBetween(String value1, String value2) {
            addCriterion("STUDY_FORM_NAME between", value1, value2, "studyFormName");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameNotBetween(String value1, String value2) {
            addCriterion("STUDY_FORM_NAME not between", value1, value2, "studyFormName");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusIdIsNull() {
            addCriterion("AT_SCHOOL_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusIdIsNotNull() {
            addCriterion("AT_SCHOOL_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusIdEqualTo(String value) {
            addCriterion("AT_SCHOOL_STATUS_ID =", value, "atSchoolStatusId");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusIdNotEqualTo(String value) {
            addCriterion("AT_SCHOOL_STATUS_ID <>", value, "atSchoolStatusId");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusIdGreaterThan(String value) {
            addCriterion("AT_SCHOOL_STATUS_ID >", value, "atSchoolStatusId");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("AT_SCHOOL_STATUS_ID >=", value, "atSchoolStatusId");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusIdLessThan(String value) {
            addCriterion("AT_SCHOOL_STATUS_ID <", value, "atSchoolStatusId");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusIdLessThanOrEqualTo(String value) {
            addCriterion("AT_SCHOOL_STATUS_ID <=", value, "atSchoolStatusId");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusIdLike(String value) {
            addCriterion("AT_SCHOOL_STATUS_ID like", value, "atSchoolStatusId");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusIdNotLike(String value) {
            addCriterion("AT_SCHOOL_STATUS_ID not like", value, "atSchoolStatusId");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusIdIn(List<String> values) {
            addCriterion("AT_SCHOOL_STATUS_ID in", values, "atSchoolStatusId");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusIdNotIn(List<String> values) {
            addCriterion("AT_SCHOOL_STATUS_ID not in", values, "atSchoolStatusId");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusIdBetween(String value1, String value2) {
            addCriterion("AT_SCHOOL_STATUS_ID between", value1, value2, "atSchoolStatusId");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusIdNotBetween(String value1, String value2) {
            addCriterion("AT_SCHOOL_STATUS_ID not between", value1, value2, "atSchoolStatusId");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusNameIsNull() {
            addCriterion("AT_SCHOOL_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusNameIsNotNull() {
            addCriterion("AT_SCHOOL_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusNameEqualTo(String value) {
            addCriterion("AT_SCHOOL_STATUS_NAME =", value, "atSchoolStatusName");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusNameNotEqualTo(String value) {
            addCriterion("AT_SCHOOL_STATUS_NAME <>", value, "atSchoolStatusName");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusNameGreaterThan(String value) {
            addCriterion("AT_SCHOOL_STATUS_NAME >", value, "atSchoolStatusName");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("AT_SCHOOL_STATUS_NAME >=", value, "atSchoolStatusName");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusNameLessThan(String value) {
            addCriterion("AT_SCHOOL_STATUS_NAME <", value, "atSchoolStatusName");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusNameLessThanOrEqualTo(String value) {
            addCriterion("AT_SCHOOL_STATUS_NAME <=", value, "atSchoolStatusName");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusNameLike(String value) {
            addCriterion("AT_SCHOOL_STATUS_NAME like", value, "atSchoolStatusName");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusNameNotLike(String value) {
            addCriterion("AT_SCHOOL_STATUS_NAME not like", value, "atSchoolStatusName");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusNameIn(List<String> values) {
            addCriterion("AT_SCHOOL_STATUS_NAME in", values, "atSchoolStatusName");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusNameNotIn(List<String> values) {
            addCriterion("AT_SCHOOL_STATUS_NAME not in", values, "atSchoolStatusName");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusNameBetween(String value1, String value2) {
            addCriterion("AT_SCHOOL_STATUS_NAME between", value1, value2, "atSchoolStatusName");
            return (Criteria) this;
        }

        public Criteria andAtSchoolStatusNameNotBetween(String value1, String value2) {
            addCriterion("AT_SCHOOL_STATUS_NAME not between", value1, value2, "atSchoolStatusName");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeIsNull() {
            addCriterion("GRADUATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeIsNotNull() {
            addCriterion("GRADUATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeEqualTo(String value) {
            addCriterion("GRADUATE_TIME =", value, "graduateTime");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeNotEqualTo(String value) {
            addCriterion("GRADUATE_TIME <>", value, "graduateTime");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeGreaterThan(String value) {
            addCriterion("GRADUATE_TIME >", value, "graduateTime");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATE_TIME >=", value, "graduateTime");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeLessThan(String value) {
            addCriterion("GRADUATE_TIME <", value, "graduateTime");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeLessThanOrEqualTo(String value) {
            addCriterion("GRADUATE_TIME <=", value, "graduateTime");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeLike(String value) {
            addCriterion("GRADUATE_TIME like", value, "graduateTime");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeNotLike(String value) {
            addCriterion("GRADUATE_TIME not like", value, "graduateTime");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeIn(List<String> values) {
            addCriterion("GRADUATE_TIME in", values, "graduateTime");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeNotIn(List<String> values) {
            addCriterion("GRADUATE_TIME not in", values, "graduateTime");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeBetween(String value1, String value2) {
            addCriterion("GRADUATE_TIME between", value1, value2, "graduateTime");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeNotBetween(String value1, String value2) {
            addCriterion("GRADUATE_TIME not between", value1, value2, "graduateTime");
            return (Criteria) this;
        }

        public Criteria andDiplomaCodeIsNull() {
            addCriterion("DIPLOMA_CODE is null");
            return (Criteria) this;
        }

        public Criteria andDiplomaCodeIsNotNull() {
            addCriterion("DIPLOMA_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andDiplomaCodeEqualTo(String value) {
            addCriterion("DIPLOMA_CODE =", value, "diplomaCode");
            return (Criteria) this;
        }

        public Criteria andDiplomaCodeNotEqualTo(String value) {
            addCriterion("DIPLOMA_CODE <>", value, "diplomaCode");
            return (Criteria) this;
        }

        public Criteria andDiplomaCodeGreaterThan(String value) {
            addCriterion("DIPLOMA_CODE >", value, "diplomaCode");
            return (Criteria) this;
        }

        public Criteria andDiplomaCodeGreaterThanOrEqualTo(String value) {
            addCriterion("DIPLOMA_CODE >=", value, "diplomaCode");
            return (Criteria) this;
        }

        public Criteria andDiplomaCodeLessThan(String value) {
            addCriterion("DIPLOMA_CODE <", value, "diplomaCode");
            return (Criteria) this;
        }

        public Criteria andDiplomaCodeLessThanOrEqualTo(String value) {
            addCriterion("DIPLOMA_CODE <=", value, "diplomaCode");
            return (Criteria) this;
        }

        public Criteria andDiplomaCodeLike(String value) {
            addCriterion("DIPLOMA_CODE like", value, "diplomaCode");
            return (Criteria) this;
        }

        public Criteria andDiplomaCodeNotLike(String value) {
            addCriterion("DIPLOMA_CODE not like", value, "diplomaCode");
            return (Criteria) this;
        }

        public Criteria andDiplomaCodeIn(List<String> values) {
            addCriterion("DIPLOMA_CODE in", values, "diplomaCode");
            return (Criteria) this;
        }

        public Criteria andDiplomaCodeNotIn(List<String> values) {
            addCriterion("DIPLOMA_CODE not in", values, "diplomaCode");
            return (Criteria) this;
        }

        public Criteria andDiplomaCodeBetween(String value1, String value2) {
            addCriterion("DIPLOMA_CODE between", value1, value2, "diplomaCode");
            return (Criteria) this;
        }

        public Criteria andDiplomaCodeNotBetween(String value1, String value2) {
            addCriterion("DIPLOMA_CODE not between", value1, value2, "diplomaCode");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryIdIsNull() {
            addCriterion("AWARD_DEGREE_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryIdIsNotNull() {
            addCriterion("AWARD_DEGREE_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryIdEqualTo(String value) {
            addCriterion("AWARD_DEGREE_CATEGORY_ID =", value, "awardDegreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryIdNotEqualTo(String value) {
            addCriterion("AWARD_DEGREE_CATEGORY_ID <>", value, "awardDegreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryIdGreaterThan(String value) {
            addCriterion("AWARD_DEGREE_CATEGORY_ID >", value, "awardDegreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("AWARD_DEGREE_CATEGORY_ID >=", value, "awardDegreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryIdLessThan(String value) {
            addCriterion("AWARD_DEGREE_CATEGORY_ID <", value, "awardDegreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("AWARD_DEGREE_CATEGORY_ID <=", value, "awardDegreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryIdLike(String value) {
            addCriterion("AWARD_DEGREE_CATEGORY_ID like", value, "awardDegreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryIdNotLike(String value) {
            addCriterion("AWARD_DEGREE_CATEGORY_ID not like", value, "awardDegreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryIdIn(List<String> values) {
            addCriterion("AWARD_DEGREE_CATEGORY_ID in", values, "awardDegreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryIdNotIn(List<String> values) {
            addCriterion("AWARD_DEGREE_CATEGORY_ID not in", values, "awardDegreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryIdBetween(String value1, String value2) {
            addCriterion("AWARD_DEGREE_CATEGORY_ID between", value1, value2, "awardDegreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryIdNotBetween(String value1, String value2) {
            addCriterion("AWARD_DEGREE_CATEGORY_ID not between", value1, value2, "awardDegreeCategoryId");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryNameIsNull() {
            addCriterion("AWARD_DEGREE_CATEGORY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryNameIsNotNull() {
            addCriterion("AWARD_DEGREE_CATEGORY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryNameEqualTo(String value) {
            addCriterion("AWARD_DEGREE_CATEGORY_NAME =", value, "awardDegreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryNameNotEqualTo(String value) {
            addCriterion("AWARD_DEGREE_CATEGORY_NAME <>", value, "awardDegreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryNameGreaterThan(String value) {
            addCriterion("AWARD_DEGREE_CATEGORY_NAME >", value, "awardDegreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("AWARD_DEGREE_CATEGORY_NAME >=", value, "awardDegreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryNameLessThan(String value) {
            addCriterion("AWARD_DEGREE_CATEGORY_NAME <", value, "awardDegreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("AWARD_DEGREE_CATEGORY_NAME <=", value, "awardDegreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryNameLike(String value) {
            addCriterion("AWARD_DEGREE_CATEGORY_NAME like", value, "awardDegreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryNameNotLike(String value) {
            addCriterion("AWARD_DEGREE_CATEGORY_NAME not like", value, "awardDegreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryNameIn(List<String> values) {
            addCriterion("AWARD_DEGREE_CATEGORY_NAME in", values, "awardDegreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryNameNotIn(List<String> values) {
            addCriterion("AWARD_DEGREE_CATEGORY_NAME not in", values, "awardDegreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryNameBetween(String value1, String value2) {
            addCriterion("AWARD_DEGREE_CATEGORY_NAME between", value1, value2, "awardDegreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCategoryNameNotBetween(String value1, String value2) {
            addCriterion("AWARD_DEGREE_CATEGORY_NAME not between", value1, value2, "awardDegreeCategoryName");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeIsNull() {
            addCriterion("AWARD_DEGREE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeIsNotNull() {
            addCriterion("AWARD_DEGREE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeEqualTo(String value) {
            addCriterion("AWARD_DEGREE_TIME =", value, "awardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeNotEqualTo(String value) {
            addCriterion("AWARD_DEGREE_TIME <>", value, "awardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeGreaterThan(String value) {
            addCriterion("AWARD_DEGREE_TIME >", value, "awardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeGreaterThanOrEqualTo(String value) {
            addCriterion("AWARD_DEGREE_TIME >=", value, "awardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeLessThan(String value) {
            addCriterion("AWARD_DEGREE_TIME <", value, "awardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeLessThanOrEqualTo(String value) {
            addCriterion("AWARD_DEGREE_TIME <=", value, "awardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeLike(String value) {
            addCriterion("AWARD_DEGREE_TIME like", value, "awardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeNotLike(String value) {
            addCriterion("AWARD_DEGREE_TIME not like", value, "awardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeIn(List<String> values) {
            addCriterion("AWARD_DEGREE_TIME in", values, "awardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeNotIn(List<String> values) {
            addCriterion("AWARD_DEGREE_TIME not in", values, "awardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeBetween(String value1, String value2) {
            addCriterion("AWARD_DEGREE_TIME between", value1, value2, "awardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeNotBetween(String value1, String value2) {
            addCriterion("AWARD_DEGREE_TIME not between", value1, value2, "awardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeIsNull() {
            addCriterion("AWARD_DEGREE_CERT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeIsNotNull() {
            addCriterion("AWARD_DEGREE_CERT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeEqualTo(String value) {
            addCriterion("AWARD_DEGREE_CERT_CODE =", value, "awardDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeNotEqualTo(String value) {
            addCriterion("AWARD_DEGREE_CERT_CODE <>", value, "awardDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeGreaterThan(String value) {
            addCriterion("AWARD_DEGREE_CERT_CODE >", value, "awardDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeGreaterThanOrEqualTo(String value) {
            addCriterion("AWARD_DEGREE_CERT_CODE >=", value, "awardDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeLessThan(String value) {
            addCriterion("AWARD_DEGREE_CERT_CODE <", value, "awardDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeLessThanOrEqualTo(String value) {
            addCriterion("AWARD_DEGREE_CERT_CODE <=", value, "awardDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeLike(String value) {
            addCriterion("AWARD_DEGREE_CERT_CODE like", value, "awardDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeNotLike(String value) {
            addCriterion("AWARD_DEGREE_CERT_CODE not like", value, "awardDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeIn(List<String> values) {
            addCriterion("AWARD_DEGREE_CERT_CODE in", values, "awardDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeNotIn(List<String> values) {
            addCriterion("AWARD_DEGREE_CERT_CODE not in", values, "awardDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeBetween(String value1, String value2) {
            addCriterion("AWARD_DEGREE_CERT_CODE between", value1, value2, "awardDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeNotBetween(String value1, String value2) {
            addCriterion("AWARD_DEGREE_CERT_CODE not between", value1, value2, "awardDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusIdIsNull() {
            addCriterion("SCHOOL_ROLL_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusIdIsNotNull() {
            addCriterion("SCHOOL_ROLL_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusIdEqualTo(String value) {
            addCriterion("SCHOOL_ROLL_STATUS_ID =", value, "schoolRollStatusId");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusIdNotEqualTo(String value) {
            addCriterion("SCHOOL_ROLL_STATUS_ID <>", value, "schoolRollStatusId");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusIdGreaterThan(String value) {
            addCriterion("SCHOOL_ROLL_STATUS_ID >", value, "schoolRollStatusId");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOL_ROLL_STATUS_ID >=", value, "schoolRollStatusId");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusIdLessThan(String value) {
            addCriterion("SCHOOL_ROLL_STATUS_ID <", value, "schoolRollStatusId");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusIdLessThanOrEqualTo(String value) {
            addCriterion("SCHOOL_ROLL_STATUS_ID <=", value, "schoolRollStatusId");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusIdLike(String value) {
            addCriterion("SCHOOL_ROLL_STATUS_ID like", value, "schoolRollStatusId");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusIdNotLike(String value) {
            addCriterion("SCHOOL_ROLL_STATUS_ID not like", value, "schoolRollStatusId");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusIdIn(List<String> values) {
            addCriterion("SCHOOL_ROLL_STATUS_ID in", values, "schoolRollStatusId");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusIdNotIn(List<String> values) {
            addCriterion("SCHOOL_ROLL_STATUS_ID not in", values, "schoolRollStatusId");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusIdBetween(String value1, String value2) {
            addCriterion("SCHOOL_ROLL_STATUS_ID between", value1, value2, "schoolRollStatusId");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusIdNotBetween(String value1, String value2) {
            addCriterion("SCHOOL_ROLL_STATUS_ID not between", value1, value2, "schoolRollStatusId");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusNameIsNull() {
            addCriterion("SCHOOL_ROLL_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusNameIsNotNull() {
            addCriterion("SCHOOL_ROLL_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusNameEqualTo(String value) {
            addCriterion("SCHOOL_ROLL_STATUS_NAME =", value, "schoolRollStatusName");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusNameNotEqualTo(String value) {
            addCriterion("SCHOOL_ROLL_STATUS_NAME <>", value, "schoolRollStatusName");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusNameGreaterThan(String value) {
            addCriterion("SCHOOL_ROLL_STATUS_NAME >", value, "schoolRollStatusName");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOL_ROLL_STATUS_NAME >=", value, "schoolRollStatusName");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusNameLessThan(String value) {
            addCriterion("SCHOOL_ROLL_STATUS_NAME <", value, "schoolRollStatusName");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusNameLessThanOrEqualTo(String value) {
            addCriterion("SCHOOL_ROLL_STATUS_NAME <=", value, "schoolRollStatusName");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusNameLike(String value) {
            addCriterion("SCHOOL_ROLL_STATUS_NAME like", value, "schoolRollStatusName");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusNameNotLike(String value) {
            addCriterion("SCHOOL_ROLL_STATUS_NAME not like", value, "schoolRollStatusName");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusNameIn(List<String> values) {
            addCriterion("SCHOOL_ROLL_STATUS_NAME in", values, "schoolRollStatusName");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusNameNotIn(List<String> values) {
            addCriterion("SCHOOL_ROLL_STATUS_NAME not in", values, "schoolRollStatusName");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusNameBetween(String value1, String value2) {
            addCriterion("SCHOOL_ROLL_STATUS_NAME between", value1, value2, "schoolRollStatusName");
            return (Criteria) this;
        }

        public Criteria andSchoolRollStatusNameNotBetween(String value1, String value2) {
            addCriterion("SCHOOL_ROLL_STATUS_NAME not between", value1, value2, "schoolRollStatusName");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonIdIsNull() {
            addCriterion("RECRUIT_SEASON_ID is null");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonIdIsNotNull() {
            addCriterion("RECRUIT_SEASON_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonIdEqualTo(String value) {
            addCriterion("RECRUIT_SEASON_ID =", value, "recruitSeasonId");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonIdNotEqualTo(String value) {
            addCriterion("RECRUIT_SEASON_ID <>", value, "recruitSeasonId");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonIdGreaterThan(String value) {
            addCriterion("RECRUIT_SEASON_ID >", value, "recruitSeasonId");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonIdGreaterThanOrEqualTo(String value) {
            addCriterion("RECRUIT_SEASON_ID >=", value, "recruitSeasonId");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonIdLessThan(String value) {
            addCriterion("RECRUIT_SEASON_ID <", value, "recruitSeasonId");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonIdLessThanOrEqualTo(String value) {
            addCriterion("RECRUIT_SEASON_ID <=", value, "recruitSeasonId");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonIdLike(String value) {
            addCriterion("RECRUIT_SEASON_ID like", value, "recruitSeasonId");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonIdNotLike(String value) {
            addCriterion("RECRUIT_SEASON_ID not like", value, "recruitSeasonId");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonIdIn(List<String> values) {
            addCriterion("RECRUIT_SEASON_ID in", values, "recruitSeasonId");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonIdNotIn(List<String> values) {
            addCriterion("RECRUIT_SEASON_ID not in", values, "recruitSeasonId");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonIdBetween(String value1, String value2) {
            addCriterion("RECRUIT_SEASON_ID between", value1, value2, "recruitSeasonId");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonIdNotBetween(String value1, String value2) {
            addCriterion("RECRUIT_SEASON_ID not between", value1, value2, "recruitSeasonId");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonNameIsNull() {
            addCriterion("RECRUIT_SEASON_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonNameIsNotNull() {
            addCriterion("RECRUIT_SEASON_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonNameEqualTo(String value) {
            addCriterion("RECRUIT_SEASON_NAME =", value, "recruitSeasonName");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonNameNotEqualTo(String value) {
            addCriterion("RECRUIT_SEASON_NAME <>", value, "recruitSeasonName");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonNameGreaterThan(String value) {
            addCriterion("RECRUIT_SEASON_NAME >", value, "recruitSeasonName");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonNameGreaterThanOrEqualTo(String value) {
            addCriterion("RECRUIT_SEASON_NAME >=", value, "recruitSeasonName");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonNameLessThan(String value) {
            addCriterion("RECRUIT_SEASON_NAME <", value, "recruitSeasonName");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonNameLessThanOrEqualTo(String value) {
            addCriterion("RECRUIT_SEASON_NAME <=", value, "recruitSeasonName");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonNameLike(String value) {
            addCriterion("RECRUIT_SEASON_NAME like", value, "recruitSeasonName");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonNameNotLike(String value) {
            addCriterion("RECRUIT_SEASON_NAME not like", value, "recruitSeasonName");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonNameIn(List<String> values) {
            addCriterion("RECRUIT_SEASON_NAME in", values, "recruitSeasonName");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonNameNotIn(List<String> values) {
            addCriterion("RECRUIT_SEASON_NAME not in", values, "recruitSeasonName");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonNameBetween(String value1, String value2) {
            addCriterion("RECRUIT_SEASON_NAME between", value1, value2, "recruitSeasonName");
            return (Criteria) this;
        }

        public Criteria andRecruitSeasonNameNotBetween(String value1, String value2) {
            addCriterion("RECRUIT_SEASON_NAME not between", value1, value2, "recruitSeasonName");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdIsNull() {
            addCriterion("TRAIN_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdIsNotNull() {
            addCriterion("TRAIN_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdEqualTo(String value) {
            addCriterion("TRAIN_CATEGORY_ID =", value, "trainCategoryId");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdNotEqualTo(String value) {
            addCriterion("TRAIN_CATEGORY_ID <>", value, "trainCategoryId");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdGreaterThan(String value) {
            addCriterion("TRAIN_CATEGORY_ID >", value, "trainCategoryId");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_CATEGORY_ID >=", value, "trainCategoryId");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdLessThan(String value) {
            addCriterion("TRAIN_CATEGORY_ID <", value, "trainCategoryId");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_CATEGORY_ID <=", value, "trainCategoryId");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdLike(String value) {
            addCriterion("TRAIN_CATEGORY_ID like", value, "trainCategoryId");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdNotLike(String value) {
            addCriterion("TRAIN_CATEGORY_ID not like", value, "trainCategoryId");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdIn(List<String> values) {
            addCriterion("TRAIN_CATEGORY_ID in", values, "trainCategoryId");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdNotIn(List<String> values) {
            addCriterion("TRAIN_CATEGORY_ID not in", values, "trainCategoryId");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdBetween(String value1, String value2) {
            addCriterion("TRAIN_CATEGORY_ID between", value1, value2, "trainCategoryId");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdNotBetween(String value1, String value2) {
            addCriterion("TRAIN_CATEGORY_ID not between", value1, value2, "trainCategoryId");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameIsNull() {
            addCriterion("TRAIN_CATEGORY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameIsNotNull() {
            addCriterion("TRAIN_CATEGORY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameEqualTo(String value) {
            addCriterion("TRAIN_CATEGORY_NAME =", value, "trainCategoryName");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameNotEqualTo(String value) {
            addCriterion("TRAIN_CATEGORY_NAME <>", value, "trainCategoryName");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameGreaterThan(String value) {
            addCriterion("TRAIN_CATEGORY_NAME >", value, "trainCategoryName");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_CATEGORY_NAME >=", value, "trainCategoryName");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameLessThan(String value) {
            addCriterion("TRAIN_CATEGORY_NAME <", value, "trainCategoryName");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_CATEGORY_NAME <=", value, "trainCategoryName");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameLike(String value) {
            addCriterion("TRAIN_CATEGORY_NAME like", value, "trainCategoryName");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameNotLike(String value) {
            addCriterion("TRAIN_CATEGORY_NAME not like", value, "trainCategoryName");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameIn(List<String> values) {
            addCriterion("TRAIN_CATEGORY_NAME in", values, "trainCategoryName");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameNotIn(List<String> values) {
            addCriterion("TRAIN_CATEGORY_NAME not in", values, "trainCategoryName");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameBetween(String value1, String value2) {
            addCriterion("TRAIN_CATEGORY_NAME between", value1, value2, "trainCategoryName");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameNotBetween(String value1, String value2) {
            addCriterion("TRAIN_CATEGORY_NAME not between", value1, value2, "trainCategoryName");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalIdIsNull() {
            addCriterion("IS_EXCEPTIONAL_ID is null");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalIdIsNotNull() {
            addCriterion("IS_EXCEPTIONAL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalIdEqualTo(String value) {
            addCriterion("IS_EXCEPTIONAL_ID =", value, "isExceptionalId");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalIdNotEqualTo(String value) {
            addCriterion("IS_EXCEPTIONAL_ID <>", value, "isExceptionalId");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalIdGreaterThan(String value) {
            addCriterion("IS_EXCEPTIONAL_ID >", value, "isExceptionalId");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalIdGreaterThanOrEqualTo(String value) {
            addCriterion("IS_EXCEPTIONAL_ID >=", value, "isExceptionalId");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalIdLessThan(String value) {
            addCriterion("IS_EXCEPTIONAL_ID <", value, "isExceptionalId");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalIdLessThanOrEqualTo(String value) {
            addCriterion("IS_EXCEPTIONAL_ID <=", value, "isExceptionalId");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalIdLike(String value) {
            addCriterion("IS_EXCEPTIONAL_ID like", value, "isExceptionalId");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalIdNotLike(String value) {
            addCriterion("IS_EXCEPTIONAL_ID not like", value, "isExceptionalId");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalIdIn(List<String> values) {
            addCriterion("IS_EXCEPTIONAL_ID in", values, "isExceptionalId");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalIdNotIn(List<String> values) {
            addCriterion("IS_EXCEPTIONAL_ID not in", values, "isExceptionalId");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalIdBetween(String value1, String value2) {
            addCriterion("IS_EXCEPTIONAL_ID between", value1, value2, "isExceptionalId");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalIdNotBetween(String value1, String value2) {
            addCriterion("IS_EXCEPTIONAL_ID not between", value1, value2, "isExceptionalId");
            return (Criteria) this;
        }

        public Criteria andIsRecommendIdIsNull() {
            addCriterion("IS_RECOMMEND_ID is null");
            return (Criteria) this;
        }

        public Criteria andIsRecommendIdIsNotNull() {
            addCriterion("IS_RECOMMEND_ID is not null");
            return (Criteria) this;
        }

        public Criteria andIsRecommendIdEqualTo(String value) {
            addCriterion("IS_RECOMMEND_ID =", value, "isRecommendId");
            return (Criteria) this;
        }

        public Criteria andIsRecommendIdNotEqualTo(String value) {
            addCriterion("IS_RECOMMEND_ID <>", value, "isRecommendId");
            return (Criteria) this;
        }

        public Criteria andIsRecommendIdGreaterThan(String value) {
            addCriterion("IS_RECOMMEND_ID >", value, "isRecommendId");
            return (Criteria) this;
        }

        public Criteria andIsRecommendIdGreaterThanOrEqualTo(String value) {
            addCriterion("IS_RECOMMEND_ID >=", value, "isRecommendId");
            return (Criteria) this;
        }

        public Criteria andIsRecommendIdLessThan(String value) {
            addCriterion("IS_RECOMMEND_ID <", value, "isRecommendId");
            return (Criteria) this;
        }

        public Criteria andIsRecommendIdLessThanOrEqualTo(String value) {
            addCriterion("IS_RECOMMEND_ID <=", value, "isRecommendId");
            return (Criteria) this;
        }

        public Criteria andIsRecommendIdLike(String value) {
            addCriterion("IS_RECOMMEND_ID like", value, "isRecommendId");
            return (Criteria) this;
        }

        public Criteria andIsRecommendIdNotLike(String value) {
            addCriterion("IS_RECOMMEND_ID not like", value, "isRecommendId");
            return (Criteria) this;
        }

        public Criteria andIsRecommendIdIn(List<String> values) {
            addCriterion("IS_RECOMMEND_ID in", values, "isRecommendId");
            return (Criteria) this;
        }

        public Criteria andIsRecommendIdNotIn(List<String> values) {
            addCriterion("IS_RECOMMEND_ID not in", values, "isRecommendId");
            return (Criteria) this;
        }

        public Criteria andIsRecommendIdBetween(String value1, String value2) {
            addCriterion("IS_RECOMMEND_ID between", value1, value2, "isRecommendId");
            return (Criteria) this;
        }

        public Criteria andIsRecommendIdNotBetween(String value1, String value2) {
            addCriterion("IS_RECOMMEND_ID not between", value1, value2, "isRecommendId");
            return (Criteria) this;
        }

        public Criteria andResearchDirIdIsNull() {
            addCriterion("RESEARCH_DIR_ID is null");
            return (Criteria) this;
        }

        public Criteria andResearchDirIdIsNotNull() {
            addCriterion("RESEARCH_DIR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andResearchDirIdEqualTo(String value) {
            addCriterion("RESEARCH_DIR_ID =", value, "researchDirId");
            return (Criteria) this;
        }

        public Criteria andResearchDirIdNotEqualTo(String value) {
            addCriterion("RESEARCH_DIR_ID <>", value, "researchDirId");
            return (Criteria) this;
        }

        public Criteria andResearchDirIdGreaterThan(String value) {
            addCriterion("RESEARCH_DIR_ID >", value, "researchDirId");
            return (Criteria) this;
        }

        public Criteria andResearchDirIdGreaterThanOrEqualTo(String value) {
            addCriterion("RESEARCH_DIR_ID >=", value, "researchDirId");
            return (Criteria) this;
        }

        public Criteria andResearchDirIdLessThan(String value) {
            addCriterion("RESEARCH_DIR_ID <", value, "researchDirId");
            return (Criteria) this;
        }

        public Criteria andResearchDirIdLessThanOrEqualTo(String value) {
            addCriterion("RESEARCH_DIR_ID <=", value, "researchDirId");
            return (Criteria) this;
        }

        public Criteria andResearchDirIdLike(String value) {
            addCriterion("RESEARCH_DIR_ID like", value, "researchDirId");
            return (Criteria) this;
        }

        public Criteria andResearchDirIdNotLike(String value) {
            addCriterion("RESEARCH_DIR_ID not like", value, "researchDirId");
            return (Criteria) this;
        }

        public Criteria andResearchDirIdIn(List<String> values) {
            addCriterion("RESEARCH_DIR_ID in", values, "researchDirId");
            return (Criteria) this;
        }

        public Criteria andResearchDirIdNotIn(List<String> values) {
            addCriterion("RESEARCH_DIR_ID not in", values, "researchDirId");
            return (Criteria) this;
        }

        public Criteria andResearchDirIdBetween(String value1, String value2) {
            addCriterion("RESEARCH_DIR_ID between", value1, value2, "researchDirId");
            return (Criteria) this;
        }

        public Criteria andResearchDirIdNotBetween(String value1, String value2) {
            addCriterion("RESEARCH_DIR_ID not between", value1, value2, "researchDirId");
            return (Criteria) this;
        }

        public Criteria andResearchDirNameIsNull() {
            addCriterion("RESEARCH_DIR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andResearchDirNameIsNotNull() {
            addCriterion("RESEARCH_DIR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andResearchDirNameEqualTo(String value) {
            addCriterion("RESEARCH_DIR_NAME =", value, "researchDirName");
            return (Criteria) this;
        }

        public Criteria andResearchDirNameNotEqualTo(String value) {
            addCriterion("RESEARCH_DIR_NAME <>", value, "researchDirName");
            return (Criteria) this;
        }

        public Criteria andResearchDirNameGreaterThan(String value) {
            addCriterion("RESEARCH_DIR_NAME >", value, "researchDirName");
            return (Criteria) this;
        }

        public Criteria andResearchDirNameGreaterThanOrEqualTo(String value) {
            addCriterion("RESEARCH_DIR_NAME >=", value, "researchDirName");
            return (Criteria) this;
        }

        public Criteria andResearchDirNameLessThan(String value) {
            addCriterion("RESEARCH_DIR_NAME <", value, "researchDirName");
            return (Criteria) this;
        }

        public Criteria andResearchDirNameLessThanOrEqualTo(String value) {
            addCriterion("RESEARCH_DIR_NAME <=", value, "researchDirName");
            return (Criteria) this;
        }

        public Criteria andResearchDirNameLike(String value) {
            addCriterion("RESEARCH_DIR_NAME like", value, "researchDirName");
            return (Criteria) this;
        }

        public Criteria andResearchDirNameNotLike(String value) {
            addCriterion("RESEARCH_DIR_NAME not like", value, "researchDirName");
            return (Criteria) this;
        }

        public Criteria andResearchDirNameIn(List<String> values) {
            addCriterion("RESEARCH_DIR_NAME in", values, "researchDirName");
            return (Criteria) this;
        }

        public Criteria andResearchDirNameNotIn(List<String> values) {
            addCriterion("RESEARCH_DIR_NAME not in", values, "researchDirName");
            return (Criteria) this;
        }

        public Criteria andResearchDirNameBetween(String value1, String value2) {
            addCriterion("RESEARCH_DIR_NAME between", value1, value2, "researchDirName");
            return (Criteria) this;
        }

        public Criteria andResearchDirNameNotBetween(String value1, String value2) {
            addCriterion("RESEARCH_DIR_NAME not between", value1, value2, "researchDirName");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusIdIsNull() {
            addCriterion("CHOOSE_COURSE_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusIdIsNotNull() {
            addCriterion("CHOOSE_COURSE_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusIdEqualTo(String value) {
            addCriterion("CHOOSE_COURSE_STATUS_ID =", value, "chooseCourseStatusId");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusIdNotEqualTo(String value) {
            addCriterion("CHOOSE_COURSE_STATUS_ID <>", value, "chooseCourseStatusId");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusIdGreaterThan(String value) {
            addCriterion("CHOOSE_COURSE_STATUS_ID >", value, "chooseCourseStatusId");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("CHOOSE_COURSE_STATUS_ID >=", value, "chooseCourseStatusId");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusIdLessThan(String value) {
            addCriterion("CHOOSE_COURSE_STATUS_ID <", value, "chooseCourseStatusId");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusIdLessThanOrEqualTo(String value) {
            addCriterion("CHOOSE_COURSE_STATUS_ID <=", value, "chooseCourseStatusId");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusIdLike(String value) {
            addCriterion("CHOOSE_COURSE_STATUS_ID like", value, "chooseCourseStatusId");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusIdNotLike(String value) {
            addCriterion("CHOOSE_COURSE_STATUS_ID not like", value, "chooseCourseStatusId");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusIdIn(List<String> values) {
            addCriterion("CHOOSE_COURSE_STATUS_ID in", values, "chooseCourseStatusId");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusIdNotIn(List<String> values) {
            addCriterion("CHOOSE_COURSE_STATUS_ID not in", values, "chooseCourseStatusId");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusIdBetween(String value1, String value2) {
            addCriterion("CHOOSE_COURSE_STATUS_ID between", value1, value2, "chooseCourseStatusId");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusIdNotBetween(String value1, String value2) {
            addCriterion("CHOOSE_COURSE_STATUS_ID not between", value1, value2, "chooseCourseStatusId");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusNameIsNull() {
            addCriterion("CHOOSE_COURSE_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusNameIsNotNull() {
            addCriterion("CHOOSE_COURSE_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusNameEqualTo(String value) {
            addCriterion("CHOOSE_COURSE_STATUS_NAME =", value, "chooseCourseStatusName");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusNameNotEqualTo(String value) {
            addCriterion("CHOOSE_COURSE_STATUS_NAME <>", value, "chooseCourseStatusName");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusNameGreaterThan(String value) {
            addCriterion("CHOOSE_COURSE_STATUS_NAME >", value, "chooseCourseStatusName");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("CHOOSE_COURSE_STATUS_NAME >=", value, "chooseCourseStatusName");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusNameLessThan(String value) {
            addCriterion("CHOOSE_COURSE_STATUS_NAME <", value, "chooseCourseStatusName");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusNameLessThanOrEqualTo(String value) {
            addCriterion("CHOOSE_COURSE_STATUS_NAME <=", value, "chooseCourseStatusName");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusNameLike(String value) {
            addCriterion("CHOOSE_COURSE_STATUS_NAME like", value, "chooseCourseStatusName");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusNameNotLike(String value) {
            addCriterion("CHOOSE_COURSE_STATUS_NAME not like", value, "chooseCourseStatusName");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusNameIn(List<String> values) {
            addCriterion("CHOOSE_COURSE_STATUS_NAME in", values, "chooseCourseStatusName");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusNameNotIn(List<String> values) {
            addCriterion("CHOOSE_COURSE_STATUS_NAME not in", values, "chooseCourseStatusName");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusNameBetween(String value1, String value2) {
            addCriterion("CHOOSE_COURSE_STATUS_NAME between", value1, value2, "chooseCourseStatusName");
            return (Criteria) this;
        }

        public Criteria andChooseCourseStatusNameNotBetween(String value1, String value2) {
            addCriterion("CHOOSE_COURSE_STATUS_NAME not between", value1, value2, "chooseCourseStatusName");
            return (Criteria) this;
        }

        public Criteria andChooseCourseTimeIsNull() {
            addCriterion("CHOOSE_COURSE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andChooseCourseTimeIsNotNull() {
            addCriterion("CHOOSE_COURSE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andChooseCourseTimeEqualTo(String value) {
            addCriterion("CHOOSE_COURSE_TIME =", value, "chooseCourseTime");
            return (Criteria) this;
        }

        public Criteria andChooseCourseTimeNotEqualTo(String value) {
            addCriterion("CHOOSE_COURSE_TIME <>", value, "chooseCourseTime");
            return (Criteria) this;
        }

        public Criteria andChooseCourseTimeGreaterThan(String value) {
            addCriterion("CHOOSE_COURSE_TIME >", value, "chooseCourseTime");
            return (Criteria) this;
        }

        public Criteria andChooseCourseTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CHOOSE_COURSE_TIME >=", value, "chooseCourseTime");
            return (Criteria) this;
        }

        public Criteria andChooseCourseTimeLessThan(String value) {
            addCriterion("CHOOSE_COURSE_TIME <", value, "chooseCourseTime");
            return (Criteria) this;
        }

        public Criteria andChooseCourseTimeLessThanOrEqualTo(String value) {
            addCriterion("CHOOSE_COURSE_TIME <=", value, "chooseCourseTime");
            return (Criteria) this;
        }

        public Criteria andChooseCourseTimeLike(String value) {
            addCriterion("CHOOSE_COURSE_TIME like", value, "chooseCourseTime");
            return (Criteria) this;
        }

        public Criteria andChooseCourseTimeNotLike(String value) {
            addCriterion("CHOOSE_COURSE_TIME not like", value, "chooseCourseTime");
            return (Criteria) this;
        }

        public Criteria andChooseCourseTimeIn(List<String> values) {
            addCriterion("CHOOSE_COURSE_TIME in", values, "chooseCourseTime");
            return (Criteria) this;
        }

        public Criteria andChooseCourseTimeNotIn(List<String> values) {
            addCriterion("CHOOSE_COURSE_TIME not in", values, "chooseCourseTime");
            return (Criteria) this;
        }

        public Criteria andChooseCourseTimeBetween(String value1, String value2) {
            addCriterion("CHOOSE_COURSE_TIME between", value1, value2, "chooseCourseTime");
            return (Criteria) this;
        }

        public Criteria andChooseCourseTimeNotBetween(String value1, String value2) {
            addCriterion("CHOOSE_COURSE_TIME not between", value1, value2, "chooseCourseTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeFlagIsNull() {
            addCriterion("AWARD_DEGREE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeFlagIsNotNull() {
            addCriterion("AWARD_DEGREE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeFlagEqualTo(String value) {
            addCriterion("AWARD_DEGREE_FLAG =", value, "awardDegreeFlag");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeFlagNotEqualTo(String value) {
            addCriterion("AWARD_DEGREE_FLAG <>", value, "awardDegreeFlag");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeFlagGreaterThan(String value) {
            addCriterion("AWARD_DEGREE_FLAG >", value, "awardDegreeFlag");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeFlagGreaterThanOrEqualTo(String value) {
            addCriterion("AWARD_DEGREE_FLAG >=", value, "awardDegreeFlag");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeFlagLessThan(String value) {
            addCriterion("AWARD_DEGREE_FLAG <", value, "awardDegreeFlag");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeFlagLessThanOrEqualTo(String value) {
            addCriterion("AWARD_DEGREE_FLAG <=", value, "awardDegreeFlag");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeFlagLike(String value) {
            addCriterion("AWARD_DEGREE_FLAG like", value, "awardDegreeFlag");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeFlagNotLike(String value) {
            addCriterion("AWARD_DEGREE_FLAG not like", value, "awardDegreeFlag");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeFlagIn(List<String> values) {
            addCriterion("AWARD_DEGREE_FLAG in", values, "awardDegreeFlag");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeFlagNotIn(List<String> values) {
            addCriterion("AWARD_DEGREE_FLAG not in", values, "awardDegreeFlag");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeFlagBetween(String value1, String value2) {
            addCriterion("AWARD_DEGREE_FLAG between", value1, value2, "awardDegreeFlag");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeFlagNotBetween(String value1, String value2) {
            addCriterion("AWARD_DEGREE_FLAG not between", value1, value2, "awardDegreeFlag");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNull() {
            addCriterion("CLASS_ID is null");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNotNull() {
            addCriterion("CLASS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andClassIdEqualTo(String value) {
            addCriterion("CLASS_ID =", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotEqualTo(String value) {
            addCriterion("CLASS_ID <>", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThan(String value) {
            addCriterion("CLASS_ID >", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThanOrEqualTo(String value) {
            addCriterion("CLASS_ID >=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThan(String value) {
            addCriterion("CLASS_ID <", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThanOrEqualTo(String value) {
            addCriterion("CLASS_ID <=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLike(String value) {
            addCriterion("CLASS_ID like", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotLike(String value) {
            addCriterion("CLASS_ID not like", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdIn(List<String> values) {
            addCriterion("CLASS_ID in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotIn(List<String> values) {
            addCriterion("CLASS_ID not in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdBetween(String value1, String value2) {
            addCriterion("CLASS_ID between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotBetween(String value1, String value2) {
            addCriterion("CLASS_ID not between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalNameIsNull() {
            addCriterion("IS_EXCEPTIONAL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalNameIsNotNull() {
            addCriterion("IS_EXCEPTIONAL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalNameEqualTo(String value) {
            addCriterion("IS_EXCEPTIONAL_NAME =", value, "isExceptionalName");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalNameNotEqualTo(String value) {
            addCriterion("IS_EXCEPTIONAL_NAME <>", value, "isExceptionalName");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalNameGreaterThan(String value) {
            addCriterion("IS_EXCEPTIONAL_NAME >", value, "isExceptionalName");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalNameGreaterThanOrEqualTo(String value) {
            addCriterion("IS_EXCEPTIONAL_NAME >=", value, "isExceptionalName");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalNameLessThan(String value) {
            addCriterion("IS_EXCEPTIONAL_NAME <", value, "isExceptionalName");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalNameLessThanOrEqualTo(String value) {
            addCriterion("IS_EXCEPTIONAL_NAME <=", value, "isExceptionalName");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalNameLike(String value) {
            addCriterion("IS_EXCEPTIONAL_NAME like", value, "isExceptionalName");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalNameNotLike(String value) {
            addCriterion("IS_EXCEPTIONAL_NAME not like", value, "isExceptionalName");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalNameIn(List<String> values) {
            addCriterion("IS_EXCEPTIONAL_NAME in", values, "isExceptionalName");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalNameNotIn(List<String> values) {
            addCriterion("IS_EXCEPTIONAL_NAME not in", values, "isExceptionalName");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalNameBetween(String value1, String value2) {
            addCriterion("IS_EXCEPTIONAL_NAME between", value1, value2, "isExceptionalName");
            return (Criteria) this;
        }

        public Criteria andIsExceptionalNameNotBetween(String value1, String value2) {
            addCriterion("IS_EXCEPTIONAL_NAME not between", value1, value2, "isExceptionalName");
            return (Criteria) this;
        }

        public Criteria andIsRecommendNameIsNull() {
            addCriterion("IS_RECOMMEND_NAME is null");
            return (Criteria) this;
        }

        public Criteria andIsRecommendNameIsNotNull() {
            addCriterion("IS_RECOMMEND_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andIsRecommendNameEqualTo(String value) {
            addCriterion("IS_RECOMMEND_NAME =", value, "isRecommendName");
            return (Criteria) this;
        }

        public Criteria andIsRecommendNameNotEqualTo(String value) {
            addCriterion("IS_RECOMMEND_NAME <>", value, "isRecommendName");
            return (Criteria) this;
        }

        public Criteria andIsRecommendNameGreaterThan(String value) {
            addCriterion("IS_RECOMMEND_NAME >", value, "isRecommendName");
            return (Criteria) this;
        }

        public Criteria andIsRecommendNameGreaterThanOrEqualTo(String value) {
            addCriterion("IS_RECOMMEND_NAME >=", value, "isRecommendName");
            return (Criteria) this;
        }

        public Criteria andIsRecommendNameLessThan(String value) {
            addCriterion("IS_RECOMMEND_NAME <", value, "isRecommendName");
            return (Criteria) this;
        }

        public Criteria andIsRecommendNameLessThanOrEqualTo(String value) {
            addCriterion("IS_RECOMMEND_NAME <=", value, "isRecommendName");
            return (Criteria) this;
        }

        public Criteria andIsRecommendNameLike(String value) {
            addCriterion("IS_RECOMMEND_NAME like", value, "isRecommendName");
            return (Criteria) this;
        }

        public Criteria andIsRecommendNameNotLike(String value) {
            addCriterion("IS_RECOMMEND_NAME not like", value, "isRecommendName");
            return (Criteria) this;
        }

        public Criteria andIsRecommendNameIn(List<String> values) {
            addCriterion("IS_RECOMMEND_NAME in", values, "isRecommendName");
            return (Criteria) this;
        }

        public Criteria andIsRecommendNameNotIn(List<String> values) {
            addCriterion("IS_RECOMMEND_NAME not in", values, "isRecommendName");
            return (Criteria) this;
        }

        public Criteria andIsRecommendNameBetween(String value1, String value2) {
            addCriterion("IS_RECOMMEND_NAME between", value1, value2, "isRecommendName");
            return (Criteria) this;
        }

        public Criteria andIsRecommendNameNotBetween(String value1, String value2) {
            addCriterion("IS_RECOMMEND_NAME not between", value1, value2, "isRecommendName");
            return (Criteria) this;
        }

        public Criteria andIsOutOfSchoolIsNull() {
            addCriterion("IS_OUT_OF_SCHOOL is null");
            return (Criteria) this;
        }

        public Criteria andIsOutOfSchoolIsNotNull() {
            addCriterion("IS_OUT_OF_SCHOOL is not null");
            return (Criteria) this;
        }

        public Criteria andIsOutOfSchoolEqualTo(String value) {
            addCriterion("IS_OUT_OF_SCHOOL =", value, "isOutOfSchool");
            return (Criteria) this;
        }

        public Criteria andIsOutOfSchoolNotEqualTo(String value) {
            addCriterion("IS_OUT_OF_SCHOOL <>", value, "isOutOfSchool");
            return (Criteria) this;
        }

        public Criteria andIsOutOfSchoolGreaterThan(String value) {
            addCriterion("IS_OUT_OF_SCHOOL >", value, "isOutOfSchool");
            return (Criteria) this;
        }

        public Criteria andIsOutOfSchoolGreaterThanOrEqualTo(String value) {
            addCriterion("IS_OUT_OF_SCHOOL >=", value, "isOutOfSchool");
            return (Criteria) this;
        }

        public Criteria andIsOutOfSchoolLessThan(String value) {
            addCriterion("IS_OUT_OF_SCHOOL <", value, "isOutOfSchool");
            return (Criteria) this;
        }

        public Criteria andIsOutOfSchoolLessThanOrEqualTo(String value) {
            addCriterion("IS_OUT_OF_SCHOOL <=", value, "isOutOfSchool");
            return (Criteria) this;
        }

        public Criteria andIsOutOfSchoolLike(String value) {
            addCriterion("IS_OUT_OF_SCHOOL like", value, "isOutOfSchool");
            return (Criteria) this;
        }

        public Criteria andIsOutOfSchoolNotLike(String value) {
            addCriterion("IS_OUT_OF_SCHOOL not like", value, "isOutOfSchool");
            return (Criteria) this;
        }

        public Criteria andIsOutOfSchoolIn(List<String> values) {
            addCriterion("IS_OUT_OF_SCHOOL in", values, "isOutOfSchool");
            return (Criteria) this;
        }

        public Criteria andIsOutOfSchoolNotIn(List<String> values) {
            addCriterion("IS_OUT_OF_SCHOOL not in", values, "isOutOfSchool");
            return (Criteria) this;
        }

        public Criteria andIsOutOfSchoolBetween(String value1, String value2) {
            addCriterion("IS_OUT_OF_SCHOOL between", value1, value2, "isOutOfSchool");
            return (Criteria) this;
        }

        public Criteria andIsOutOfSchoolNotBetween(String value1, String value2) {
            addCriterion("IS_OUT_OF_SCHOOL not between", value1, value2, "isOutOfSchool");
            return (Criteria) this;
        }

        public Criteria andIsBackToSchoolIsNull() {
            addCriterion("IS_BACK_TO_SCHOOL is null");
            return (Criteria) this;
        }

        public Criteria andIsBackToSchoolIsNotNull() {
            addCriterion("IS_BACK_TO_SCHOOL is not null");
            return (Criteria) this;
        }

        public Criteria andIsBackToSchoolEqualTo(String value) {
            addCriterion("IS_BACK_TO_SCHOOL =", value, "isBackToSchool");
            return (Criteria) this;
        }

        public Criteria andIsBackToSchoolNotEqualTo(String value) {
            addCriterion("IS_BACK_TO_SCHOOL <>", value, "isBackToSchool");
            return (Criteria) this;
        }

        public Criteria andIsBackToSchoolGreaterThan(String value) {
            addCriterion("IS_BACK_TO_SCHOOL >", value, "isBackToSchool");
            return (Criteria) this;
        }

        public Criteria andIsBackToSchoolGreaterThanOrEqualTo(String value) {
            addCriterion("IS_BACK_TO_SCHOOL >=", value, "isBackToSchool");
            return (Criteria) this;
        }

        public Criteria andIsBackToSchoolLessThan(String value) {
            addCriterion("IS_BACK_TO_SCHOOL <", value, "isBackToSchool");
            return (Criteria) this;
        }

        public Criteria andIsBackToSchoolLessThanOrEqualTo(String value) {
            addCriterion("IS_BACK_TO_SCHOOL <=", value, "isBackToSchool");
            return (Criteria) this;
        }

        public Criteria andIsBackToSchoolLike(String value) {
            addCriterion("IS_BACK_TO_SCHOOL like", value, "isBackToSchool");
            return (Criteria) this;
        }

        public Criteria andIsBackToSchoolNotLike(String value) {
            addCriterion("IS_BACK_TO_SCHOOL not like", value, "isBackToSchool");
            return (Criteria) this;
        }

        public Criteria andIsBackToSchoolIn(List<String> values) {
            addCriterion("IS_BACK_TO_SCHOOL in", values, "isBackToSchool");
            return (Criteria) this;
        }

        public Criteria andIsBackToSchoolNotIn(List<String> values) {
            addCriterion("IS_BACK_TO_SCHOOL not in", values, "isBackToSchool");
            return (Criteria) this;
        }

        public Criteria andIsBackToSchoolBetween(String value1, String value2) {
            addCriterion("IS_BACK_TO_SCHOOL between", value1, value2, "isBackToSchool");
            return (Criteria) this;
        }

        public Criteria andIsBackToSchoolNotBetween(String value1, String value2) {
            addCriterion("IS_BACK_TO_SCHOOL not between", value1, value2, "isBackToSchool");
            return (Criteria) this;
        }

        public Criteria andIsMbaDbaIsNull() {
            addCriterion("IS_MBA_DBA is null");
            return (Criteria) this;
        }

        public Criteria andIsMbaDbaIsNotNull() {
            addCriterion("IS_MBA_DBA is not null");
            return (Criteria) this;
        }

        public Criteria andIsMbaDbaEqualTo(String value) {
            addCriterion("IS_MBA_DBA =", value, "isMbaDba");
            return (Criteria) this;
        }

        public Criteria andIsMbaDbaNotEqualTo(String value) {
            addCriterion("IS_MBA_DBA <>", value, "isMbaDba");
            return (Criteria) this;
        }

        public Criteria andIsMbaDbaGreaterThan(String value) {
            addCriterion("IS_MBA_DBA >", value, "isMbaDba");
            return (Criteria) this;
        }

        public Criteria andIsMbaDbaGreaterThanOrEqualTo(String value) {
            addCriterion("IS_MBA_DBA >=", value, "isMbaDba");
            return (Criteria) this;
        }

        public Criteria andIsMbaDbaLessThan(String value) {
            addCriterion("IS_MBA_DBA <", value, "isMbaDba");
            return (Criteria) this;
        }

        public Criteria andIsMbaDbaLessThanOrEqualTo(String value) {
            addCriterion("IS_MBA_DBA <=", value, "isMbaDba");
            return (Criteria) this;
        }

        public Criteria andIsMbaDbaLike(String value) {
            addCriterion("IS_MBA_DBA like", value, "isMbaDba");
            return (Criteria) this;
        }

        public Criteria andIsMbaDbaNotLike(String value) {
            addCriterion("IS_MBA_DBA not like", value, "isMbaDba");
            return (Criteria) this;
        }

        public Criteria andIsMbaDbaIn(List<String> values) {
            addCriterion("IS_MBA_DBA in", values, "isMbaDba");
            return (Criteria) this;
        }

        public Criteria andIsMbaDbaNotIn(List<String> values) {
            addCriterion("IS_MBA_DBA not in", values, "isMbaDba");
            return (Criteria) this;
        }

        public Criteria andIsMbaDbaBetween(String value1, String value2) {
            addCriterion("IS_MBA_DBA between", value1, value2, "isMbaDba");
            return (Criteria) this;
        }

        public Criteria andIsMbaDbaNotBetween(String value1, String value2) {
            addCriterion("IS_MBA_DBA not between", value1, value2, "isMbaDba");
            return (Criteria) this;
        }

        public Criteria andIsMdfInfoIsNull() {
            addCriterion("IS_MDF_INFO is null");
            return (Criteria) this;
        }

        public Criteria andIsMdfInfoIsNotNull() {
            addCriterion("IS_MDF_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andIsMdfInfoEqualTo(String value) {
            addCriterion("IS_MDF_INFO =", value, "isMdfInfo");
            return (Criteria) this;
        }

        public Criteria andIsMdfInfoNotEqualTo(String value) {
            addCriterion("IS_MDF_INFO <>", value, "isMdfInfo");
            return (Criteria) this;
        }

        public Criteria andIsMdfInfoGreaterThan(String value) {
            addCriterion("IS_MDF_INFO >", value, "isMdfInfo");
            return (Criteria) this;
        }

        public Criteria andIsMdfInfoGreaterThanOrEqualTo(String value) {
            addCriterion("IS_MDF_INFO >=", value, "isMdfInfo");
            return (Criteria) this;
        }

        public Criteria andIsMdfInfoLessThan(String value) {
            addCriterion("IS_MDF_INFO <", value, "isMdfInfo");
            return (Criteria) this;
        }

        public Criteria andIsMdfInfoLessThanOrEqualTo(String value) {
            addCriterion("IS_MDF_INFO <=", value, "isMdfInfo");
            return (Criteria) this;
        }

        public Criteria andIsMdfInfoLike(String value) {
            addCriterion("IS_MDF_INFO like", value, "isMdfInfo");
            return (Criteria) this;
        }

        public Criteria andIsMdfInfoNotLike(String value) {
            addCriterion("IS_MDF_INFO not like", value, "isMdfInfo");
            return (Criteria) this;
        }

        public Criteria andIsMdfInfoIn(List<String> values) {
            addCriterion("IS_MDF_INFO in", values, "isMdfInfo");
            return (Criteria) this;
        }

        public Criteria andIsMdfInfoNotIn(List<String> values) {
            addCriterion("IS_MDF_INFO not in", values, "isMdfInfo");
            return (Criteria) this;
        }

        public Criteria andIsMdfInfoBetween(String value1, String value2) {
            addCriterion("IS_MDF_INFO between", value1, value2, "isMdfInfo");
            return (Criteria) this;
        }

        public Criteria andIsMdfInfoNotBetween(String value1, String value2) {
            addCriterion("IS_MDF_INFO not between", value1, value2, "isMdfInfo");
            return (Criteria) this;
        }

        public Criteria andIsReportedIsNull() {
            addCriterion("IS_REPORTED is null");
            return (Criteria) this;
        }

        public Criteria andIsReportedIsNotNull() {
            addCriterion("IS_REPORTED is not null");
            return (Criteria) this;
        }

        public Criteria andIsReportedEqualTo(String value) {
            addCriterion("IS_REPORTED =", value, "isReported");
            return (Criteria) this;
        }

        public Criteria andIsReportedNotEqualTo(String value) {
            addCriterion("IS_REPORTED <>", value, "isReported");
            return (Criteria) this;
        }

        public Criteria andIsReportedGreaterThan(String value) {
            addCriterion("IS_REPORTED >", value, "isReported");
            return (Criteria) this;
        }

        public Criteria andIsReportedGreaterThanOrEqualTo(String value) {
            addCriterion("IS_REPORTED >=", value, "isReported");
            return (Criteria) this;
        }

        public Criteria andIsReportedLessThan(String value) {
            addCriterion("IS_REPORTED <", value, "isReported");
            return (Criteria) this;
        }

        public Criteria andIsReportedLessThanOrEqualTo(String value) {
            addCriterion("IS_REPORTED <=", value, "isReported");
            return (Criteria) this;
        }

        public Criteria andIsReportedLike(String value) {
            addCriterion("IS_REPORTED like", value, "isReported");
            return (Criteria) this;
        }

        public Criteria andIsReportedNotLike(String value) {
            addCriterion("IS_REPORTED not like", value, "isReported");
            return (Criteria) this;
        }

        public Criteria andIsReportedIn(List<String> values) {
            addCriterion("IS_REPORTED in", values, "isReported");
            return (Criteria) this;
        }

        public Criteria andIsReportedNotIn(List<String> values) {
            addCriterion("IS_REPORTED not in", values, "isReported");
            return (Criteria) this;
        }

        public Criteria andIsReportedBetween(String value1, String value2) {
            addCriterion("IS_REPORTED between", value1, value2, "isReported");
            return (Criteria) this;
        }

        public Criteria andIsReportedNotBetween(String value1, String value2) {
            addCriterion("IS_REPORTED not between", value1, value2, "isReported");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowIsNull() {
            addCriterion("FIRST_TEACHER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowIsNotNull() {
            addCriterion("FIRST_TEACHER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowEqualTo(String value) {
            addCriterion("FIRST_TEACHER_FLOW =", value, "firstTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowNotEqualTo(String value) {
            addCriterion("FIRST_TEACHER_FLOW <>", value, "firstTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowGreaterThan(String value) {
            addCriterion("FIRST_TEACHER_FLOW >", value, "firstTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowGreaterThanOrEqualTo(String value) {
            addCriterion("FIRST_TEACHER_FLOW >=", value, "firstTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowLessThan(String value) {
            addCriterion("FIRST_TEACHER_FLOW <", value, "firstTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowLessThanOrEqualTo(String value) {
            addCriterion("FIRST_TEACHER_FLOW <=", value, "firstTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowLike(String value) {
            addCriterion("FIRST_TEACHER_FLOW like", value, "firstTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowNotLike(String value) {
            addCriterion("FIRST_TEACHER_FLOW not like", value, "firstTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowIn(List<String> values) {
            addCriterion("FIRST_TEACHER_FLOW in", values, "firstTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowNotIn(List<String> values) {
            addCriterion("FIRST_TEACHER_FLOW not in", values, "firstTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowBetween(String value1, String value2) {
            addCriterion("FIRST_TEACHER_FLOW between", value1, value2, "firstTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowNotBetween(String value1, String value2) {
            addCriterion("FIRST_TEACHER_FLOW not between", value1, value2, "firstTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowIsNull() {
            addCriterion("SECOND_TEACHER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowIsNotNull() {
            addCriterion("SECOND_TEACHER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowEqualTo(String value) {
            addCriterion("SECOND_TEACHER_FLOW =", value, "secondTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowNotEqualTo(String value) {
            addCriterion("SECOND_TEACHER_FLOW <>", value, "secondTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowGreaterThan(String value) {
            addCriterion("SECOND_TEACHER_FLOW >", value, "secondTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SECOND_TEACHER_FLOW >=", value, "secondTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowLessThan(String value) {
            addCriterion("SECOND_TEACHER_FLOW <", value, "secondTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowLessThanOrEqualTo(String value) {
            addCriterion("SECOND_TEACHER_FLOW <=", value, "secondTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowLike(String value) {
            addCriterion("SECOND_TEACHER_FLOW like", value, "secondTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowNotLike(String value) {
            addCriterion("SECOND_TEACHER_FLOW not like", value, "secondTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowIn(List<String> values) {
            addCriterion("SECOND_TEACHER_FLOW in", values, "secondTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowNotIn(List<String> values) {
            addCriterion("SECOND_TEACHER_FLOW not in", values, "secondTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowBetween(String value1, String value2) {
            addCriterion("SECOND_TEACHER_FLOW between", value1, value2, "secondTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowNotBetween(String value1, String value2) {
            addCriterion("SECOND_TEACHER_FLOW not between", value1, value2, "secondTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andPreGraduateDateIsNull() {
            addCriterion("PRE_GRADUATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andPreGraduateDateIsNotNull() {
            addCriterion("PRE_GRADUATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andPreGraduateDateEqualTo(String value) {
            addCriterion("PRE_GRADUATE_DATE =", value, "preGraduateDate");
            return (Criteria) this;
        }

        public Criteria andPreGraduateDateNotEqualTo(String value) {
            addCriterion("PRE_GRADUATE_DATE <>", value, "preGraduateDate");
            return (Criteria) this;
        }

        public Criteria andPreGraduateDateGreaterThan(String value) {
            addCriterion("PRE_GRADUATE_DATE >", value, "preGraduateDate");
            return (Criteria) this;
        }

        public Criteria andPreGraduateDateGreaterThanOrEqualTo(String value) {
            addCriterion("PRE_GRADUATE_DATE >=", value, "preGraduateDate");
            return (Criteria) this;
        }

        public Criteria andPreGraduateDateLessThan(String value) {
            addCriterion("PRE_GRADUATE_DATE <", value, "preGraduateDate");
            return (Criteria) this;
        }

        public Criteria andPreGraduateDateLessThanOrEqualTo(String value) {
            addCriterion("PRE_GRADUATE_DATE <=", value, "preGraduateDate");
            return (Criteria) this;
        }

        public Criteria andPreGraduateDateLike(String value) {
            addCriterion("PRE_GRADUATE_DATE like", value, "preGraduateDate");
            return (Criteria) this;
        }

        public Criteria andPreGraduateDateNotLike(String value) {
            addCriterion("PRE_GRADUATE_DATE not like", value, "preGraduateDate");
            return (Criteria) this;
        }

        public Criteria andPreGraduateDateIn(List<String> values) {
            addCriterion("PRE_GRADUATE_DATE in", values, "preGraduateDate");
            return (Criteria) this;
        }

        public Criteria andPreGraduateDateNotIn(List<String> values) {
            addCriterion("PRE_GRADUATE_DATE not in", values, "preGraduateDate");
            return (Criteria) this;
        }

        public Criteria andPreGraduateDateBetween(String value1, String value2) {
            addCriterion("PRE_GRADUATE_DATE between", value1, value2, "preGraduateDate");
            return (Criteria) this;
        }

        public Criteria andPreGraduateDateNotBetween(String value1, String value2) {
            addCriterion("PRE_GRADUATE_DATE not between", value1, value2, "preGraduateDate");
            return (Criteria) this;
        }

        public Criteria andBarCodeIsNull() {
            addCriterion("BAR_CODE is null");
            return (Criteria) this;
        }

        public Criteria andBarCodeIsNotNull() {
            addCriterion("BAR_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andBarCodeEqualTo(String value) {
            addCriterion("BAR_CODE =", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeNotEqualTo(String value) {
            addCriterion("BAR_CODE <>", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeGreaterThan(String value) {
            addCriterion("BAR_CODE >", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeGreaterThanOrEqualTo(String value) {
            addCriterion("BAR_CODE >=", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeLessThan(String value) {
            addCriterion("BAR_CODE <", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeLessThanOrEqualTo(String value) {
            addCriterion("BAR_CODE <=", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeLike(String value) {
            addCriterion("BAR_CODE like", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeNotLike(String value) {
            addCriterion("BAR_CODE not like", value, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeIn(List<String> values) {
            addCriterion("BAR_CODE in", values, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeNotIn(List<String> values) {
            addCriterion("BAR_CODE not in", values, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeBetween(String value1, String value2) {
            addCriterion("BAR_CODE between", value1, value2, "barCode");
            return (Criteria) this;
        }

        public Criteria andBarCodeNotBetween(String value1, String value2) {
            addCriterion("BAR_CODE not between", value1, value2, "barCode");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIsNull() {
            addCriterion("DEGREE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIsNotNull() {
            addCriterion("DEGREE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeEqualTo(String value) {
            addCriterion("DEGREE_TYPE =", value, "degreeType");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNotEqualTo(String value) {
            addCriterion("DEGREE_TYPE <>", value, "degreeType");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeGreaterThan(String value) {
            addCriterion("DEGREE_TYPE >", value, "degreeType");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("DEGREE_TYPE >=", value, "degreeType");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeLessThan(String value) {
            addCriterion("DEGREE_TYPE <", value, "degreeType");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeLessThanOrEqualTo(String value) {
            addCriterion("DEGREE_TYPE <=", value, "degreeType");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeLike(String value) {
            addCriterion("DEGREE_TYPE like", value, "degreeType");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNotLike(String value) {
            addCriterion("DEGREE_TYPE not like", value, "degreeType");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIn(List<String> values) {
            addCriterion("DEGREE_TYPE in", values, "degreeType");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNotIn(List<String> values) {
            addCriterion("DEGREE_TYPE not in", values, "degreeType");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeBetween(String value1, String value2) {
            addCriterion("DEGREE_TYPE between", value1, value2, "degreeType");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNotBetween(String value1, String value2) {
            addCriterion("DEGREE_TYPE not between", value1, value2, "degreeType");
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

        public Criteria andDegreeLevelIdIsNull() {
            addCriterion("DEGREE_LEVEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelIdIsNotNull() {
            addCriterion("DEGREE_LEVEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelIdEqualTo(String value) {
            addCriterion("DEGREE_LEVEL_ID =", value, "degreeLevelId");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelIdNotEqualTo(String value) {
            addCriterion("DEGREE_LEVEL_ID <>", value, "degreeLevelId");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelIdGreaterThan(String value) {
            addCriterion("DEGREE_LEVEL_ID >", value, "degreeLevelId");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelIdGreaterThanOrEqualTo(String value) {
            addCriterion("DEGREE_LEVEL_ID >=", value, "degreeLevelId");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelIdLessThan(String value) {
            addCriterion("DEGREE_LEVEL_ID <", value, "degreeLevelId");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelIdLessThanOrEqualTo(String value) {
            addCriterion("DEGREE_LEVEL_ID <=", value, "degreeLevelId");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelIdLike(String value) {
            addCriterion("DEGREE_LEVEL_ID like", value, "degreeLevelId");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelIdNotLike(String value) {
            addCriterion("DEGREE_LEVEL_ID not like", value, "degreeLevelId");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelIdIn(List<String> values) {
            addCriterion("DEGREE_LEVEL_ID in", values, "degreeLevelId");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelIdNotIn(List<String> values) {
            addCriterion("DEGREE_LEVEL_ID not in", values, "degreeLevelId");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelIdBetween(String value1, String value2) {
            addCriterion("DEGREE_LEVEL_ID between", value1, value2, "degreeLevelId");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelIdNotBetween(String value1, String value2) {
            addCriterion("DEGREE_LEVEL_ID not between", value1, value2, "degreeLevelId");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelNameIsNull() {
            addCriterion("DEGREE_LEVEL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelNameIsNotNull() {
            addCriterion("DEGREE_LEVEL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelNameEqualTo(String value) {
            addCriterion("DEGREE_LEVEL_NAME =", value, "degreeLevelName");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelNameNotEqualTo(String value) {
            addCriterion("DEGREE_LEVEL_NAME <>", value, "degreeLevelName");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelNameGreaterThan(String value) {
            addCriterion("DEGREE_LEVEL_NAME >", value, "degreeLevelName");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEGREE_LEVEL_NAME >=", value, "degreeLevelName");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelNameLessThan(String value) {
            addCriterion("DEGREE_LEVEL_NAME <", value, "degreeLevelName");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelNameLessThanOrEqualTo(String value) {
            addCriterion("DEGREE_LEVEL_NAME <=", value, "degreeLevelName");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelNameLike(String value) {
            addCriterion("DEGREE_LEVEL_NAME like", value, "degreeLevelName");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelNameNotLike(String value) {
            addCriterion("DEGREE_LEVEL_NAME not like", value, "degreeLevelName");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelNameIn(List<String> values) {
            addCriterion("DEGREE_LEVEL_NAME in", values, "degreeLevelName");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelNameNotIn(List<String> values) {
            addCriterion("DEGREE_LEVEL_NAME not in", values, "degreeLevelName");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelNameBetween(String value1, String value2) {
            addCriterion("DEGREE_LEVEL_NAME between", value1, value2, "degreeLevelName");
            return (Criteria) this;
        }

        public Criteria andDegreeLevelNameNotBetween(String value1, String value2) {
            addCriterion("DEGREE_LEVEL_NAME not between", value1, value2, "degreeLevelName");
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