package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class PubImportRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PubImportRecordExample() {
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

        public Criteria andImpFlowIsNull() {
            addCriterion("IMP_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andImpFlowIsNotNull() {
            addCriterion("IMP_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andImpFlowEqualTo(String value) {
            addCriterion("IMP_FLOW =", value, "impFlow");
            return (Criteria) this;
        }

        public Criteria andImpFlowNotEqualTo(String value) {
            addCriterion("IMP_FLOW <>", value, "impFlow");
            return (Criteria) this;
        }

        public Criteria andImpFlowGreaterThan(String value) {
            addCriterion("IMP_FLOW >", value, "impFlow");
            return (Criteria) this;
        }

        public Criteria andImpFlowGreaterThanOrEqualTo(String value) {
            addCriterion("IMP_FLOW >=", value, "impFlow");
            return (Criteria) this;
        }

        public Criteria andImpFlowLessThan(String value) {
            addCriterion("IMP_FLOW <", value, "impFlow");
            return (Criteria) this;
        }

        public Criteria andImpFlowLessThanOrEqualTo(String value) {
            addCriterion("IMP_FLOW <=", value, "impFlow");
            return (Criteria) this;
        }

        public Criteria andImpFlowLike(String value) {
            addCriterion("IMP_FLOW like", value, "impFlow");
            return (Criteria) this;
        }

        public Criteria andImpFlowNotLike(String value) {
            addCriterion("IMP_FLOW not like", value, "impFlow");
            return (Criteria) this;
        }

        public Criteria andImpFlowIn(List<String> values) {
            addCriterion("IMP_FLOW in", values, "impFlow");
            return (Criteria) this;
        }

        public Criteria andImpFlowNotIn(List<String> values) {
            addCriterion("IMP_FLOW not in", values, "impFlow");
            return (Criteria) this;
        }

        public Criteria andImpFlowBetween(String value1, String value2) {
            addCriterion("IMP_FLOW between", value1, value2, "impFlow");
            return (Criteria) this;
        }

        public Criteria andImpFlowNotBetween(String value1, String value2) {
            addCriterion("IMP_FLOW not between", value1, value2, "impFlow");
            return (Criteria) this;
        }

        public Criteria andImpTypeIdIsNull() {
            addCriterion("IMP_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andImpTypeIdIsNotNull() {
            addCriterion("IMP_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andImpTypeIdEqualTo(String value) {
            addCriterion("IMP_TYPE_ID =", value, "impTypeId");
            return (Criteria) this;
        }

        public Criteria andImpTypeIdNotEqualTo(String value) {
            addCriterion("IMP_TYPE_ID <>", value, "impTypeId");
            return (Criteria) this;
        }

        public Criteria andImpTypeIdGreaterThan(String value) {
            addCriterion("IMP_TYPE_ID >", value, "impTypeId");
            return (Criteria) this;
        }

        public Criteria andImpTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("IMP_TYPE_ID >=", value, "impTypeId");
            return (Criteria) this;
        }

        public Criteria andImpTypeIdLessThan(String value) {
            addCriterion("IMP_TYPE_ID <", value, "impTypeId");
            return (Criteria) this;
        }

        public Criteria andImpTypeIdLessThanOrEqualTo(String value) {
            addCriterion("IMP_TYPE_ID <=", value, "impTypeId");
            return (Criteria) this;
        }

        public Criteria andImpTypeIdLike(String value) {
            addCriterion("IMP_TYPE_ID like", value, "impTypeId");
            return (Criteria) this;
        }

        public Criteria andImpTypeIdNotLike(String value) {
            addCriterion("IMP_TYPE_ID not like", value, "impTypeId");
            return (Criteria) this;
        }

        public Criteria andImpTypeIdIn(List<String> values) {
            addCriterion("IMP_TYPE_ID in", values, "impTypeId");
            return (Criteria) this;
        }

        public Criteria andImpTypeIdNotIn(List<String> values) {
            addCriterion("IMP_TYPE_ID not in", values, "impTypeId");
            return (Criteria) this;
        }

        public Criteria andImpTypeIdBetween(String value1, String value2) {
            addCriterion("IMP_TYPE_ID between", value1, value2, "impTypeId");
            return (Criteria) this;
        }

        public Criteria andImpTypeIdNotBetween(String value1, String value2) {
            addCriterion("IMP_TYPE_ID not between", value1, value2, "impTypeId");
            return (Criteria) this;
        }

        public Criteria andImpTypeNameIsNull() {
            addCriterion("IMP_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andImpTypeNameIsNotNull() {
            addCriterion("IMP_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andImpTypeNameEqualTo(String value) {
            addCriterion("IMP_TYPE_NAME =", value, "impTypeName");
            return (Criteria) this;
        }

        public Criteria andImpTypeNameNotEqualTo(String value) {
            addCriterion("IMP_TYPE_NAME <>", value, "impTypeName");
            return (Criteria) this;
        }

        public Criteria andImpTypeNameGreaterThan(String value) {
            addCriterion("IMP_TYPE_NAME >", value, "impTypeName");
            return (Criteria) this;
        }

        public Criteria andImpTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("IMP_TYPE_NAME >=", value, "impTypeName");
            return (Criteria) this;
        }

        public Criteria andImpTypeNameLessThan(String value) {
            addCriterion("IMP_TYPE_NAME <", value, "impTypeName");
            return (Criteria) this;
        }

        public Criteria andImpTypeNameLessThanOrEqualTo(String value) {
            addCriterion("IMP_TYPE_NAME <=", value, "impTypeName");
            return (Criteria) this;
        }

        public Criteria andImpTypeNameLike(String value) {
            addCriterion("IMP_TYPE_NAME like", value, "impTypeName");
            return (Criteria) this;
        }

        public Criteria andImpTypeNameNotLike(String value) {
            addCriterion("IMP_TYPE_NAME not like", value, "impTypeName");
            return (Criteria) this;
        }

        public Criteria andImpTypeNameIn(List<String> values) {
            addCriterion("IMP_TYPE_NAME in", values, "impTypeName");
            return (Criteria) this;
        }

        public Criteria andImpTypeNameNotIn(List<String> values) {
            addCriterion("IMP_TYPE_NAME not in", values, "impTypeName");
            return (Criteria) this;
        }

        public Criteria andImpTypeNameBetween(String value1, String value2) {
            addCriterion("IMP_TYPE_NAME between", value1, value2, "impTypeName");
            return (Criteria) this;
        }

        public Criteria andImpTypeNameNotBetween(String value1, String value2) {
            addCriterion("IMP_TYPE_NAME not between", value1, value2, "impTypeName");
            return (Criteria) this;
        }

        public Criteria andImpTimeIsNull() {
            addCriterion("IMP_TIME is null");
            return (Criteria) this;
        }

        public Criteria andImpTimeIsNotNull() {
            addCriterion("IMP_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andImpTimeEqualTo(String value) {
            addCriterion("IMP_TIME =", value, "impTime");
            return (Criteria) this;
        }

        public Criteria andImpTimeNotEqualTo(String value) {
            addCriterion("IMP_TIME <>", value, "impTime");
            return (Criteria) this;
        }

        public Criteria andImpTimeGreaterThan(String value) {
            addCriterion("IMP_TIME >", value, "impTime");
            return (Criteria) this;
        }

        public Criteria andImpTimeGreaterThanOrEqualTo(String value) {
            addCriterion("IMP_TIME >=", value, "impTime");
            return (Criteria) this;
        }

        public Criteria andImpTimeLessThan(String value) {
            addCriterion("IMP_TIME <", value, "impTime");
            return (Criteria) this;
        }

        public Criteria andImpTimeLessThanOrEqualTo(String value) {
            addCriterion("IMP_TIME <=", value, "impTime");
            return (Criteria) this;
        }

        public Criteria andImpTimeLike(String value) {
            addCriterion("IMP_TIME like", value, "impTime");
            return (Criteria) this;
        }

        public Criteria andImpTimeNotLike(String value) {
            addCriterion("IMP_TIME not like", value, "impTime");
            return (Criteria) this;
        }

        public Criteria andImpTimeIn(List<String> values) {
            addCriterion("IMP_TIME in", values, "impTime");
            return (Criteria) this;
        }

        public Criteria andImpTimeNotIn(List<String> values) {
            addCriterion("IMP_TIME not in", values, "impTime");
            return (Criteria) this;
        }

        public Criteria andImpTimeBetween(String value1, String value2) {
            addCriterion("IMP_TIME between", value1, value2, "impTime");
            return (Criteria) this;
        }

        public Criteria andImpTimeNotBetween(String value1, String value2) {
            addCriterion("IMP_TIME not between", value1, value2, "impTime");
            return (Criteria) this;
        }

        public Criteria andImpNumIsNull() {
            addCriterion("IMP_NUM is null");
            return (Criteria) this;
        }

        public Criteria andImpNumIsNotNull() {
            addCriterion("IMP_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andImpNumEqualTo(String value) {
            addCriterion("IMP_NUM =", value, "impNum");
            return (Criteria) this;
        }

        public Criteria andImpNumNotEqualTo(String value) {
            addCriterion("IMP_NUM <>", value, "impNum");
            return (Criteria) this;
        }

        public Criteria andImpNumGreaterThan(String value) {
            addCriterion("IMP_NUM >", value, "impNum");
            return (Criteria) this;
        }

        public Criteria andImpNumGreaterThanOrEqualTo(String value) {
            addCriterion("IMP_NUM >=", value, "impNum");
            return (Criteria) this;
        }

        public Criteria andImpNumLessThan(String value) {
            addCriterion("IMP_NUM <", value, "impNum");
            return (Criteria) this;
        }

        public Criteria andImpNumLessThanOrEqualTo(String value) {
            addCriterion("IMP_NUM <=", value, "impNum");
            return (Criteria) this;
        }

        public Criteria andImpNumLike(String value) {
            addCriterion("IMP_NUM like", value, "impNum");
            return (Criteria) this;
        }

        public Criteria andImpNumNotLike(String value) {
            addCriterion("IMP_NUM not like", value, "impNum");
            return (Criteria) this;
        }

        public Criteria andImpNumIn(List<String> values) {
            addCriterion("IMP_NUM in", values, "impNum");
            return (Criteria) this;
        }

        public Criteria andImpNumNotIn(List<String> values) {
            addCriterion("IMP_NUM not in", values, "impNum");
            return (Criteria) this;
        }

        public Criteria andImpNumBetween(String value1, String value2) {
            addCriterion("IMP_NUM between", value1, value2, "impNum");
            return (Criteria) this;
        }

        public Criteria andImpNumNotBetween(String value1, String value2) {
            addCriterion("IMP_NUM not between", value1, value2, "impNum");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowIsNull() {
            addCriterion("IMP_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowIsNotNull() {
            addCriterion("IMP_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowEqualTo(String value) {
            addCriterion("IMP_USER_FLOW =", value, "impUserFlow");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowNotEqualTo(String value) {
            addCriterion("IMP_USER_FLOW <>", value, "impUserFlow");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowGreaterThan(String value) {
            addCriterion("IMP_USER_FLOW >", value, "impUserFlow");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("IMP_USER_FLOW >=", value, "impUserFlow");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowLessThan(String value) {
            addCriterion("IMP_USER_FLOW <", value, "impUserFlow");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowLessThanOrEqualTo(String value) {
            addCriterion("IMP_USER_FLOW <=", value, "impUserFlow");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowLike(String value) {
            addCriterion("IMP_USER_FLOW like", value, "impUserFlow");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowNotLike(String value) {
            addCriterion("IMP_USER_FLOW not like", value, "impUserFlow");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowIn(List<String> values) {
            addCriterion("IMP_USER_FLOW in", values, "impUserFlow");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowNotIn(List<String> values) {
            addCriterion("IMP_USER_FLOW not in", values, "impUserFlow");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowBetween(String value1, String value2) {
            addCriterion("IMP_USER_FLOW between", value1, value2, "impUserFlow");
            return (Criteria) this;
        }

        public Criteria andImpUserFlowNotBetween(String value1, String value2) {
            addCriterion("IMP_USER_FLOW not between", value1, value2, "impUserFlow");
            return (Criteria) this;
        }

        public Criteria andImpUserNameIsNull() {
            addCriterion("IMP_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andImpUserNameIsNotNull() {
            addCriterion("IMP_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andImpUserNameEqualTo(String value) {
            addCriterion("IMP_USER_NAME =", value, "impUserName");
            return (Criteria) this;
        }

        public Criteria andImpUserNameNotEqualTo(String value) {
            addCriterion("IMP_USER_NAME <>", value, "impUserName");
            return (Criteria) this;
        }

        public Criteria andImpUserNameGreaterThan(String value) {
            addCriterion("IMP_USER_NAME >", value, "impUserName");
            return (Criteria) this;
        }

        public Criteria andImpUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("IMP_USER_NAME >=", value, "impUserName");
            return (Criteria) this;
        }

        public Criteria andImpUserNameLessThan(String value) {
            addCriterion("IMP_USER_NAME <", value, "impUserName");
            return (Criteria) this;
        }

        public Criteria andImpUserNameLessThanOrEqualTo(String value) {
            addCriterion("IMP_USER_NAME <=", value, "impUserName");
            return (Criteria) this;
        }

        public Criteria andImpUserNameLike(String value) {
            addCriterion("IMP_USER_NAME like", value, "impUserName");
            return (Criteria) this;
        }

        public Criteria andImpUserNameNotLike(String value) {
            addCriterion("IMP_USER_NAME not like", value, "impUserName");
            return (Criteria) this;
        }

        public Criteria andImpUserNameIn(List<String> values) {
            addCriterion("IMP_USER_NAME in", values, "impUserName");
            return (Criteria) this;
        }

        public Criteria andImpUserNameNotIn(List<String> values) {
            addCriterion("IMP_USER_NAME not in", values, "impUserName");
            return (Criteria) this;
        }

        public Criteria andImpUserNameBetween(String value1, String value2) {
            addCriterion("IMP_USER_NAME between", value1, value2, "impUserName");
            return (Criteria) this;
        }

        public Criteria andImpUserNameNotBetween(String value1, String value2) {
            addCriterion("IMP_USER_NAME not between", value1, value2, "impUserName");
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

        public Criteria andRoleFlagIsNull() {
            addCriterion("ROLE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andRoleFlagIsNotNull() {
            addCriterion("ROLE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andRoleFlagEqualTo(String value) {
            addCriterion("ROLE_FLAG =", value, "roleFlag");
            return (Criteria) this;
        }

        public Criteria andRoleFlagNotEqualTo(String value) {
            addCriterion("ROLE_FLAG <>", value, "roleFlag");
            return (Criteria) this;
        }

        public Criteria andRoleFlagGreaterThan(String value) {
            addCriterion("ROLE_FLAG >", value, "roleFlag");
            return (Criteria) this;
        }

        public Criteria andRoleFlagGreaterThanOrEqualTo(String value) {
            addCriterion("ROLE_FLAG >=", value, "roleFlag");
            return (Criteria) this;
        }

        public Criteria andRoleFlagLessThan(String value) {
            addCriterion("ROLE_FLAG <", value, "roleFlag");
            return (Criteria) this;
        }

        public Criteria andRoleFlagLessThanOrEqualTo(String value) {
            addCriterion("ROLE_FLAG <=", value, "roleFlag");
            return (Criteria) this;
        }

        public Criteria andRoleFlagLike(String value) {
            addCriterion("ROLE_FLAG like", value, "roleFlag");
            return (Criteria) this;
        }

        public Criteria andRoleFlagNotLike(String value) {
            addCriterion("ROLE_FLAG not like", value, "roleFlag");
            return (Criteria) this;
        }

        public Criteria andRoleFlagIn(List<String> values) {
            addCriterion("ROLE_FLAG in", values, "roleFlag");
            return (Criteria) this;
        }

        public Criteria andRoleFlagNotIn(List<String> values) {
            addCriterion("ROLE_FLAG not in", values, "roleFlag");
            return (Criteria) this;
        }

        public Criteria andRoleFlagBetween(String value1, String value2) {
            addCriterion("ROLE_FLAG between", value1, value2, "roleFlag");
            return (Criteria) this;
        }

        public Criteria andRoleFlagNotBetween(String value1, String value2) {
            addCriterion("ROLE_FLAG not between", value1, value2, "roleFlag");
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