package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class IrbStampExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public IrbStampExample() {
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

        public Criteria andStampFlowIsNull() {
            addCriterion("STAMP_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andStampFlowIsNotNull() {
            addCriterion("STAMP_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andStampFlowEqualTo(String value) {
            addCriterion("STAMP_FLOW =", value, "stampFlow");
            return (Criteria) this;
        }

        public Criteria andStampFlowNotEqualTo(String value) {
            addCriterion("STAMP_FLOW <>", value, "stampFlow");
            return (Criteria) this;
        }

        public Criteria andStampFlowGreaterThan(String value) {
            addCriterion("STAMP_FLOW >", value, "stampFlow");
            return (Criteria) this;
        }

        public Criteria andStampFlowGreaterThanOrEqualTo(String value) {
            addCriterion("STAMP_FLOW >=", value, "stampFlow");
            return (Criteria) this;
        }

        public Criteria andStampFlowLessThan(String value) {
            addCriterion("STAMP_FLOW <", value, "stampFlow");
            return (Criteria) this;
        }

        public Criteria andStampFlowLessThanOrEqualTo(String value) {
            addCriterion("STAMP_FLOW <=", value, "stampFlow");
            return (Criteria) this;
        }

        public Criteria andStampFlowLike(String value) {
            addCriterion("STAMP_FLOW like", value, "stampFlow");
            return (Criteria) this;
        }

        public Criteria andStampFlowNotLike(String value) {
            addCriterion("STAMP_FLOW not like", value, "stampFlow");
            return (Criteria) this;
        }

        public Criteria andStampFlowIn(List<String> values) {
            addCriterion("STAMP_FLOW in", values, "stampFlow");
            return (Criteria) this;
        }

        public Criteria andStampFlowNotIn(List<String> values) {
            addCriterion("STAMP_FLOW not in", values, "stampFlow");
            return (Criteria) this;
        }

        public Criteria andStampFlowBetween(String value1, String value2) {
            addCriterion("STAMP_FLOW between", value1, value2, "stampFlow");
            return (Criteria) this;
        }

        public Criteria andStampFlowNotBetween(String value1, String value2) {
            addCriterion("STAMP_FLOW not between", value1, value2, "stampFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowIsNull() {
            addCriterion("PROJ_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andProjFlowIsNotNull() {
            addCriterion("PROJ_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andProjFlowEqualTo(String value) {
            addCriterion("PROJ_FLOW =", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowNotEqualTo(String value) {
            addCriterion("PROJ_FLOW <>", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowGreaterThan(String value) {
            addCriterion("PROJ_FLOW >", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_FLOW >=", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowLessThan(String value) {
            addCriterion("PROJ_FLOW <", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowLessThanOrEqualTo(String value) {
            addCriterion("PROJ_FLOW <=", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowLike(String value) {
            addCriterion("PROJ_FLOW like", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowNotLike(String value) {
            addCriterion("PROJ_FLOW not like", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowIn(List<String> values) {
            addCriterion("PROJ_FLOW in", values, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowNotIn(List<String> values) {
            addCriterion("PROJ_FLOW not in", values, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowBetween(String value1, String value2) {
            addCriterion("PROJ_FLOW between", value1, value2, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowNotBetween(String value1, String value2) {
            addCriterion("PROJ_FLOW not between", value1, value2, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjNameIsNull() {
            addCriterion("PROJ_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjNameIsNotNull() {
            addCriterion("PROJ_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjNameEqualTo(String value) {
            addCriterion("PROJ_NAME =", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameNotEqualTo(String value) {
            addCriterion("PROJ_NAME <>", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameGreaterThan(String value) {
            addCriterion("PROJ_NAME >", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_NAME >=", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameLessThan(String value) {
            addCriterion("PROJ_NAME <", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameLessThanOrEqualTo(String value) {
            addCriterion("PROJ_NAME <=", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameLike(String value) {
            addCriterion("PROJ_NAME like", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameNotLike(String value) {
            addCriterion("PROJ_NAME not like", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameIn(List<String> values) {
            addCriterion("PROJ_NAME in", values, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameNotIn(List<String> values) {
            addCriterion("PROJ_NAME not in", values, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameBetween(String value1, String value2) {
            addCriterion("PROJ_NAME between", value1, value2, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameNotBetween(String value1, String value2) {
            addCriterion("PROJ_NAME not between", value1, value2, "projName");
            return (Criteria) this;
        }

        public Criteria andStampDateIsNull() {
            addCriterion("STAMP_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStampDateIsNotNull() {
            addCriterion("STAMP_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStampDateEqualTo(String value) {
            addCriterion("STAMP_DATE =", value, "stampDate");
            return (Criteria) this;
        }

        public Criteria andStampDateNotEqualTo(String value) {
            addCriterion("STAMP_DATE <>", value, "stampDate");
            return (Criteria) this;
        }

        public Criteria andStampDateGreaterThan(String value) {
            addCriterion("STAMP_DATE >", value, "stampDate");
            return (Criteria) this;
        }

        public Criteria andStampDateGreaterThanOrEqualTo(String value) {
            addCriterion("STAMP_DATE >=", value, "stampDate");
            return (Criteria) this;
        }

        public Criteria andStampDateLessThan(String value) {
            addCriterion("STAMP_DATE <", value, "stampDate");
            return (Criteria) this;
        }

        public Criteria andStampDateLessThanOrEqualTo(String value) {
            addCriterion("STAMP_DATE <=", value, "stampDate");
            return (Criteria) this;
        }

        public Criteria andStampDateLike(String value) {
            addCriterion("STAMP_DATE like", value, "stampDate");
            return (Criteria) this;
        }

        public Criteria andStampDateNotLike(String value) {
            addCriterion("STAMP_DATE not like", value, "stampDate");
            return (Criteria) this;
        }

        public Criteria andStampDateIn(List<String> values) {
            addCriterion("STAMP_DATE in", values, "stampDate");
            return (Criteria) this;
        }

        public Criteria andStampDateNotIn(List<String> values) {
            addCriterion("STAMP_DATE not in", values, "stampDate");
            return (Criteria) this;
        }

        public Criteria andStampDateBetween(String value1, String value2) {
            addCriterion("STAMP_DATE between", value1, value2, "stampDate");
            return (Criteria) this;
        }

        public Criteria andStampDateNotBetween(String value1, String value2) {
            addCriterion("STAMP_DATE not between", value1, value2, "stampDate");
            return (Criteria) this;
        }

        public Criteria andStampNameIsNull() {
            addCriterion("STAMP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStampNameIsNotNull() {
            addCriterion("STAMP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStampNameEqualTo(String value) {
            addCriterion("STAMP_NAME =", value, "stampName");
            return (Criteria) this;
        }

        public Criteria andStampNameNotEqualTo(String value) {
            addCriterion("STAMP_NAME <>", value, "stampName");
            return (Criteria) this;
        }

        public Criteria andStampNameGreaterThan(String value) {
            addCriterion("STAMP_NAME >", value, "stampName");
            return (Criteria) this;
        }

        public Criteria andStampNameGreaterThanOrEqualTo(String value) {
            addCriterion("STAMP_NAME >=", value, "stampName");
            return (Criteria) this;
        }

        public Criteria andStampNameLessThan(String value) {
            addCriterion("STAMP_NAME <", value, "stampName");
            return (Criteria) this;
        }

        public Criteria andStampNameLessThanOrEqualTo(String value) {
            addCriterion("STAMP_NAME <=", value, "stampName");
            return (Criteria) this;
        }

        public Criteria andStampNameLike(String value) {
            addCriterion("STAMP_NAME like", value, "stampName");
            return (Criteria) this;
        }

        public Criteria andStampNameNotLike(String value) {
            addCriterion("STAMP_NAME not like", value, "stampName");
            return (Criteria) this;
        }

        public Criteria andStampNameIn(List<String> values) {
            addCriterion("STAMP_NAME in", values, "stampName");
            return (Criteria) this;
        }

        public Criteria andStampNameNotIn(List<String> values) {
            addCriterion("STAMP_NAME not in", values, "stampName");
            return (Criteria) this;
        }

        public Criteria andStampNameBetween(String value1, String value2) {
            addCriterion("STAMP_NAME between", value1, value2, "stampName");
            return (Criteria) this;
        }

        public Criteria andStampNameNotBetween(String value1, String value2) {
            addCriterion("STAMP_NAME not between", value1, value2, "stampName");
            return (Criteria) this;
        }

        public Criteria andStampNumIsNull() {
            addCriterion("STAMP_NUM is null");
            return (Criteria) this;
        }

        public Criteria andStampNumIsNotNull() {
            addCriterion("STAMP_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andStampNumEqualTo(Integer value) {
            addCriterion("STAMP_NUM =", value, "stampNum");
            return (Criteria) this;
        }

        public Criteria andStampNumNotEqualTo(Integer value) {
            addCriterion("STAMP_NUM <>", value, "stampNum");
            return (Criteria) this;
        }

        public Criteria andStampNumGreaterThan(Integer value) {
            addCriterion("STAMP_NUM >", value, "stampNum");
            return (Criteria) this;
        }

        public Criteria andStampNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("STAMP_NUM >=", value, "stampNum");
            return (Criteria) this;
        }

        public Criteria andStampNumLessThan(Integer value) {
            addCriterion("STAMP_NUM <", value, "stampNum");
            return (Criteria) this;
        }

        public Criteria andStampNumLessThanOrEqualTo(Integer value) {
            addCriterion("STAMP_NUM <=", value, "stampNum");
            return (Criteria) this;
        }

        public Criteria andStampNumIn(List<Integer> values) {
            addCriterion("STAMP_NUM in", values, "stampNum");
            return (Criteria) this;
        }

        public Criteria andStampNumNotIn(List<Integer> values) {
            addCriterion("STAMP_NUM not in", values, "stampNum");
            return (Criteria) this;
        }

        public Criteria andStampNumBetween(Integer value1, Integer value2) {
            addCriterion("STAMP_NUM between", value1, value2, "stampNum");
            return (Criteria) this;
        }

        public Criteria andStampNumNotBetween(Integer value1, Integer value2) {
            addCriterion("STAMP_NUM not between", value1, value2, "stampNum");
            return (Criteria) this;
        }

        public Criteria andStampUserNameIsNull() {
            addCriterion("STAMP_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStampUserNameIsNotNull() {
            addCriterion("STAMP_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStampUserNameEqualTo(String value) {
            addCriterion("STAMP_USER_NAME =", value, "stampUserName");
            return (Criteria) this;
        }

        public Criteria andStampUserNameNotEqualTo(String value) {
            addCriterion("STAMP_USER_NAME <>", value, "stampUserName");
            return (Criteria) this;
        }

        public Criteria andStampUserNameGreaterThan(String value) {
            addCriterion("STAMP_USER_NAME >", value, "stampUserName");
            return (Criteria) this;
        }

        public Criteria andStampUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("STAMP_USER_NAME >=", value, "stampUserName");
            return (Criteria) this;
        }

        public Criteria andStampUserNameLessThan(String value) {
            addCriterion("STAMP_USER_NAME <", value, "stampUserName");
            return (Criteria) this;
        }

        public Criteria andStampUserNameLessThanOrEqualTo(String value) {
            addCriterion("STAMP_USER_NAME <=", value, "stampUserName");
            return (Criteria) this;
        }

        public Criteria andStampUserNameLike(String value) {
            addCriterion("STAMP_USER_NAME like", value, "stampUserName");
            return (Criteria) this;
        }

        public Criteria andStampUserNameNotLike(String value) {
            addCriterion("STAMP_USER_NAME not like", value, "stampUserName");
            return (Criteria) this;
        }

        public Criteria andStampUserNameIn(List<String> values) {
            addCriterion("STAMP_USER_NAME in", values, "stampUserName");
            return (Criteria) this;
        }

        public Criteria andStampUserNameNotIn(List<String> values) {
            addCriterion("STAMP_USER_NAME not in", values, "stampUserName");
            return (Criteria) this;
        }

        public Criteria andStampUserNameBetween(String value1, String value2) {
            addCriterion("STAMP_USER_NAME between", value1, value2, "stampUserName");
            return (Criteria) this;
        }

        public Criteria andStampUserNameNotBetween(String value1, String value2) {
            addCriterion("STAMP_USER_NAME not between", value1, value2, "stampUserName");
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