package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class IrbRegulationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public IrbRegulationExample() {
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

        public Criteria andRegFlowIsNull() {
            addCriterion("REG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRegFlowIsNotNull() {
            addCriterion("REG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRegFlowEqualTo(String value) {
            addCriterion("REG_FLOW =", value, "regFlow");
            return (Criteria) this;
        }

        public Criteria andRegFlowNotEqualTo(String value) {
            addCriterion("REG_FLOW <>", value, "regFlow");
            return (Criteria) this;
        }

        public Criteria andRegFlowGreaterThan(String value) {
            addCriterion("REG_FLOW >", value, "regFlow");
            return (Criteria) this;
        }

        public Criteria andRegFlowGreaterThanOrEqualTo(String value) {
            addCriterion("REG_FLOW >=", value, "regFlow");
            return (Criteria) this;
        }

        public Criteria andRegFlowLessThan(String value) {
            addCriterion("REG_FLOW <", value, "regFlow");
            return (Criteria) this;
        }

        public Criteria andRegFlowLessThanOrEqualTo(String value) {
            addCriterion("REG_FLOW <=", value, "regFlow");
            return (Criteria) this;
        }

        public Criteria andRegFlowLike(String value) {
            addCriterion("REG_FLOW like", value, "regFlow");
            return (Criteria) this;
        }

        public Criteria andRegFlowNotLike(String value) {
            addCriterion("REG_FLOW not like", value, "regFlow");
            return (Criteria) this;
        }

        public Criteria andRegFlowIn(List<String> values) {
            addCriterion("REG_FLOW in", values, "regFlow");
            return (Criteria) this;
        }

        public Criteria andRegFlowNotIn(List<String> values) {
            addCriterion("REG_FLOW not in", values, "regFlow");
            return (Criteria) this;
        }

        public Criteria andRegFlowBetween(String value1, String value2) {
            addCriterion("REG_FLOW between", value1, value2, "regFlow");
            return (Criteria) this;
        }

        public Criteria andRegFlowNotBetween(String value1, String value2) {
            addCriterion("REG_FLOW not between", value1, value2, "regFlow");
            return (Criteria) this;
        }

        public Criteria andRegNameIsNull() {
            addCriterion("REG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRegNameIsNotNull() {
            addCriterion("REG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRegNameEqualTo(String value) {
            addCriterion("REG_NAME =", value, "regName");
            return (Criteria) this;
        }

        public Criteria andRegNameNotEqualTo(String value) {
            addCriterion("REG_NAME <>", value, "regName");
            return (Criteria) this;
        }

        public Criteria andRegNameGreaterThan(String value) {
            addCriterion("REG_NAME >", value, "regName");
            return (Criteria) this;
        }

        public Criteria andRegNameGreaterThanOrEqualTo(String value) {
            addCriterion("REG_NAME >=", value, "regName");
            return (Criteria) this;
        }

        public Criteria andRegNameLessThan(String value) {
            addCriterion("REG_NAME <", value, "regName");
            return (Criteria) this;
        }

        public Criteria andRegNameLessThanOrEqualTo(String value) {
            addCriterion("REG_NAME <=", value, "regName");
            return (Criteria) this;
        }

        public Criteria andRegNameLike(String value) {
            addCriterion("REG_NAME like", value, "regName");
            return (Criteria) this;
        }

        public Criteria andRegNameNotLike(String value) {
            addCriterion("REG_NAME not like", value, "regName");
            return (Criteria) this;
        }

        public Criteria andRegNameIn(List<String> values) {
            addCriterion("REG_NAME in", values, "regName");
            return (Criteria) this;
        }

        public Criteria andRegNameNotIn(List<String> values) {
            addCriterion("REG_NAME not in", values, "regName");
            return (Criteria) this;
        }

        public Criteria andRegNameBetween(String value1, String value2) {
            addCriterion("REG_NAME between", value1, value2, "regName");
            return (Criteria) this;
        }

        public Criteria andRegNameNotBetween(String value1, String value2) {
            addCriterion("REG_NAME not between", value1, value2, "regName");
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

        public Criteria andRegTypeIdIsNull() {
            addCriterion("REG_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andRegTypeIdIsNotNull() {
            addCriterion("REG_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRegTypeIdEqualTo(String value) {
            addCriterion("REG_TYPE_ID =", value, "regTypeId");
            return (Criteria) this;
        }

        public Criteria andRegTypeIdNotEqualTo(String value) {
            addCriterion("REG_TYPE_ID <>", value, "regTypeId");
            return (Criteria) this;
        }

        public Criteria andRegTypeIdGreaterThan(String value) {
            addCriterion("REG_TYPE_ID >", value, "regTypeId");
            return (Criteria) this;
        }

        public Criteria andRegTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("REG_TYPE_ID >=", value, "regTypeId");
            return (Criteria) this;
        }

        public Criteria andRegTypeIdLessThan(String value) {
            addCriterion("REG_TYPE_ID <", value, "regTypeId");
            return (Criteria) this;
        }

        public Criteria andRegTypeIdLessThanOrEqualTo(String value) {
            addCriterion("REG_TYPE_ID <=", value, "regTypeId");
            return (Criteria) this;
        }

        public Criteria andRegTypeIdLike(String value) {
            addCriterion("REG_TYPE_ID like", value, "regTypeId");
            return (Criteria) this;
        }

        public Criteria andRegTypeIdNotLike(String value) {
            addCriterion("REG_TYPE_ID not like", value, "regTypeId");
            return (Criteria) this;
        }

        public Criteria andRegTypeIdIn(List<String> values) {
            addCriterion("REG_TYPE_ID in", values, "regTypeId");
            return (Criteria) this;
        }

        public Criteria andRegTypeIdNotIn(List<String> values) {
            addCriterion("REG_TYPE_ID not in", values, "regTypeId");
            return (Criteria) this;
        }

        public Criteria andRegTypeIdBetween(String value1, String value2) {
            addCriterion("REG_TYPE_ID between", value1, value2, "regTypeId");
            return (Criteria) this;
        }

        public Criteria andRegTypeIdNotBetween(String value1, String value2) {
            addCriterion("REG_TYPE_ID not between", value1, value2, "regTypeId");
            return (Criteria) this;
        }

        public Criteria andRegTypeNameIsNull() {
            addCriterion("REG_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRegTypeNameIsNotNull() {
            addCriterion("REG_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRegTypeNameEqualTo(String value) {
            addCriterion("REG_TYPE_NAME =", value, "regTypeName");
            return (Criteria) this;
        }

        public Criteria andRegTypeNameNotEqualTo(String value) {
            addCriterion("REG_TYPE_NAME <>", value, "regTypeName");
            return (Criteria) this;
        }

        public Criteria andRegTypeNameGreaterThan(String value) {
            addCriterion("REG_TYPE_NAME >", value, "regTypeName");
            return (Criteria) this;
        }

        public Criteria andRegTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("REG_TYPE_NAME >=", value, "regTypeName");
            return (Criteria) this;
        }

        public Criteria andRegTypeNameLessThan(String value) {
            addCriterion("REG_TYPE_NAME <", value, "regTypeName");
            return (Criteria) this;
        }

        public Criteria andRegTypeNameLessThanOrEqualTo(String value) {
            addCriterion("REG_TYPE_NAME <=", value, "regTypeName");
            return (Criteria) this;
        }

        public Criteria andRegTypeNameLike(String value) {
            addCriterion("REG_TYPE_NAME like", value, "regTypeName");
            return (Criteria) this;
        }

        public Criteria andRegTypeNameNotLike(String value) {
            addCriterion("REG_TYPE_NAME not like", value, "regTypeName");
            return (Criteria) this;
        }

        public Criteria andRegTypeNameIn(List<String> values) {
            addCriterion("REG_TYPE_NAME in", values, "regTypeName");
            return (Criteria) this;
        }

        public Criteria andRegTypeNameNotIn(List<String> values) {
            addCriterion("REG_TYPE_NAME not in", values, "regTypeName");
            return (Criteria) this;
        }

        public Criteria andRegTypeNameBetween(String value1, String value2) {
            addCriterion("REG_TYPE_NAME between", value1, value2, "regTypeName");
            return (Criteria) this;
        }

        public Criteria andRegTypeNameNotBetween(String value1, String value2) {
            addCriterion("REG_TYPE_NAME not between", value1, value2, "regTypeName");
            return (Criteria) this;
        }

        public Criteria andOrdinalIsNull() {
            addCriterion("ORDINAL is null");
            return (Criteria) this;
        }

        public Criteria andOrdinalIsNotNull() {
            addCriterion("ORDINAL is not null");
            return (Criteria) this;
        }

        public Criteria andOrdinalEqualTo(Integer value) {
            addCriterion("ORDINAL =", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotEqualTo(Integer value) {
            addCriterion("ORDINAL <>", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalGreaterThan(Integer value) {
            addCriterion("ORDINAL >", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalGreaterThanOrEqualTo(Integer value) {
            addCriterion("ORDINAL >=", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalLessThan(Integer value) {
            addCriterion("ORDINAL <", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalLessThanOrEqualTo(Integer value) {
            addCriterion("ORDINAL <=", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalIn(List<Integer> values) {
            addCriterion("ORDINAL in", values, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotIn(List<Integer> values) {
            addCriterion("ORDINAL not in", values, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalBetween(Integer value1, Integer value2) {
            addCriterion("ORDINAL between", value1, value2, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotBetween(Integer value1, Integer value2) {
            addCriterion("ORDINAL not between", value1, value2, "ordinal");
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