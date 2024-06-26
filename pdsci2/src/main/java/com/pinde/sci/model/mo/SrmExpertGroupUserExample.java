package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class SrmExpertGroupUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SrmExpertGroupUserExample() {
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

        public Criteria andExpertStatusIdIsNull() {
            addCriterion("EXPERT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andExpertStatusIdIsNotNull() {
            addCriterion("EXPERT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExpertStatusIdEqualTo(String value) {
            addCriterion("EXPERT_STATUS_ID =", value, "expertStatusId");
            return (Criteria) this;
        }

        public Criteria andExpertStatusIdNotEqualTo(String value) {
            addCriterion("EXPERT_STATUS_ID <>", value, "expertStatusId");
            return (Criteria) this;
        }

        public Criteria andExpertStatusIdGreaterThan(String value) {
            addCriterion("EXPERT_STATUS_ID >", value, "expertStatusId");
            return (Criteria) this;
        }

        public Criteria andExpertStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("EXPERT_STATUS_ID >=", value, "expertStatusId");
            return (Criteria) this;
        }

        public Criteria andExpertStatusIdLessThan(String value) {
            addCriterion("EXPERT_STATUS_ID <", value, "expertStatusId");
            return (Criteria) this;
        }

        public Criteria andExpertStatusIdLessThanOrEqualTo(String value) {
            addCriterion("EXPERT_STATUS_ID <=", value, "expertStatusId");
            return (Criteria) this;
        }

        public Criteria andExpertStatusIdLike(String value) {
            addCriterion("EXPERT_STATUS_ID like", value, "expertStatusId");
            return (Criteria) this;
        }

        public Criteria andExpertStatusIdNotLike(String value) {
            addCriterion("EXPERT_STATUS_ID not like", value, "expertStatusId");
            return (Criteria) this;
        }

        public Criteria andExpertStatusIdIn(List<String> values) {
            addCriterion("EXPERT_STATUS_ID in", values, "expertStatusId");
            return (Criteria) this;
        }

        public Criteria andExpertStatusIdNotIn(List<String> values) {
            addCriterion("EXPERT_STATUS_ID not in", values, "expertStatusId");
            return (Criteria) this;
        }

        public Criteria andExpertStatusIdBetween(String value1, String value2) {
            addCriterion("EXPERT_STATUS_ID between", value1, value2, "expertStatusId");
            return (Criteria) this;
        }

        public Criteria andExpertStatusIdNotBetween(String value1, String value2) {
            addCriterion("EXPERT_STATUS_ID not between", value1, value2, "expertStatusId");
            return (Criteria) this;
        }

        public Criteria andExpertStatusNameIsNull() {
            addCriterion("EXPERT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andExpertStatusNameIsNotNull() {
            addCriterion("EXPERT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andExpertStatusNameEqualTo(String value) {
            addCriterion("EXPERT_STATUS_NAME =", value, "expertStatusName");
            return (Criteria) this;
        }

        public Criteria andExpertStatusNameNotEqualTo(String value) {
            addCriterion("EXPERT_STATUS_NAME <>", value, "expertStatusName");
            return (Criteria) this;
        }

        public Criteria andExpertStatusNameGreaterThan(String value) {
            addCriterion("EXPERT_STATUS_NAME >", value, "expertStatusName");
            return (Criteria) this;
        }

        public Criteria andExpertStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("EXPERT_STATUS_NAME >=", value, "expertStatusName");
            return (Criteria) this;
        }

        public Criteria andExpertStatusNameLessThan(String value) {
            addCriterion("EXPERT_STATUS_NAME <", value, "expertStatusName");
            return (Criteria) this;
        }

        public Criteria andExpertStatusNameLessThanOrEqualTo(String value) {
            addCriterion("EXPERT_STATUS_NAME <=", value, "expertStatusName");
            return (Criteria) this;
        }

        public Criteria andExpertStatusNameLike(String value) {
            addCriterion("EXPERT_STATUS_NAME like", value, "expertStatusName");
            return (Criteria) this;
        }

        public Criteria andExpertStatusNameNotLike(String value) {
            addCriterion("EXPERT_STATUS_NAME not like", value, "expertStatusName");
            return (Criteria) this;
        }

        public Criteria andExpertStatusNameIn(List<String> values) {
            addCriterion("EXPERT_STATUS_NAME in", values, "expertStatusName");
            return (Criteria) this;
        }

        public Criteria andExpertStatusNameNotIn(List<String> values) {
            addCriterion("EXPERT_STATUS_NAME not in", values, "expertStatusName");
            return (Criteria) this;
        }

        public Criteria andExpertStatusNameBetween(String value1, String value2) {
            addCriterion("EXPERT_STATUS_NAME between", value1, value2, "expertStatusName");
            return (Criteria) this;
        }

        public Criteria andExpertStatusNameNotBetween(String value1, String value2) {
            addCriterion("EXPERT_STATUS_NAME not between", value1, value2, "expertStatusName");
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

        public Criteria andSignFlagIsNull() {
            addCriterion("SIGN_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andSignFlagIsNotNull() {
            addCriterion("SIGN_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andSignFlagEqualTo(String value) {
            addCriterion("SIGN_FLAG =", value, "signFlag");
            return (Criteria) this;
        }

        public Criteria andSignFlagNotEqualTo(String value) {
            addCriterion("SIGN_FLAG <>", value, "signFlag");
            return (Criteria) this;
        }

        public Criteria andSignFlagGreaterThan(String value) {
            addCriterion("SIGN_FLAG >", value, "signFlag");
            return (Criteria) this;
        }

        public Criteria andSignFlagGreaterThanOrEqualTo(String value) {
            addCriterion("SIGN_FLAG >=", value, "signFlag");
            return (Criteria) this;
        }

        public Criteria andSignFlagLessThan(String value) {
            addCriterion("SIGN_FLAG <", value, "signFlag");
            return (Criteria) this;
        }

        public Criteria andSignFlagLessThanOrEqualTo(String value) {
            addCriterion("SIGN_FLAG <=", value, "signFlag");
            return (Criteria) this;
        }

        public Criteria andSignFlagLike(String value) {
            addCriterion("SIGN_FLAG like", value, "signFlag");
            return (Criteria) this;
        }

        public Criteria andSignFlagNotLike(String value) {
            addCriterion("SIGN_FLAG not like", value, "signFlag");
            return (Criteria) this;
        }

        public Criteria andSignFlagIn(List<String> values) {
            addCriterion("SIGN_FLAG in", values, "signFlag");
            return (Criteria) this;
        }

        public Criteria andSignFlagNotIn(List<String> values) {
            addCriterion("SIGN_FLAG not in", values, "signFlag");
            return (Criteria) this;
        }

        public Criteria andSignFlagBetween(String value1, String value2) {
            addCriterion("SIGN_FLAG between", value1, value2, "signFlag");
            return (Criteria) this;
        }

        public Criteria andSignFlagNotBetween(String value1, String value2) {
            addCriterion("SIGN_FLAG not between", value1, value2, "signFlag");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagIsNull() {
            addCriterion("EMAIL_NOTIFY_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagIsNotNull() {
            addCriterion("EMAIL_NOTIFY_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagEqualTo(String value) {
            addCriterion("EMAIL_NOTIFY_FLAG =", value, "emailNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagNotEqualTo(String value) {
            addCriterion("EMAIL_NOTIFY_FLAG <>", value, "emailNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagGreaterThan(String value) {
            addCriterion("EMAIL_NOTIFY_FLAG >", value, "emailNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL_NOTIFY_FLAG >=", value, "emailNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagLessThan(String value) {
            addCriterion("EMAIL_NOTIFY_FLAG <", value, "emailNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagLessThanOrEqualTo(String value) {
            addCriterion("EMAIL_NOTIFY_FLAG <=", value, "emailNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagLike(String value) {
            addCriterion("EMAIL_NOTIFY_FLAG like", value, "emailNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagNotLike(String value) {
            addCriterion("EMAIL_NOTIFY_FLAG not like", value, "emailNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagIn(List<String> values) {
            addCriterion("EMAIL_NOTIFY_FLAG in", values, "emailNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagNotIn(List<String> values) {
            addCriterion("EMAIL_NOTIFY_FLAG not in", values, "emailNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagBetween(String value1, String value2) {
            addCriterion("EMAIL_NOTIFY_FLAG between", value1, value2, "emailNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagNotBetween(String value1, String value2) {
            addCriterion("EMAIL_NOTIFY_FLAG not between", value1, value2, "emailNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagIsNull() {
            addCriterion("PHONE_NOTIFY_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagIsNotNull() {
            addCriterion("PHONE_NOTIFY_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagEqualTo(String value) {
            addCriterion("PHONE_NOTIFY_FLAG =", value, "phoneNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagNotEqualTo(String value) {
            addCriterion("PHONE_NOTIFY_FLAG <>", value, "phoneNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagGreaterThan(String value) {
            addCriterion("PHONE_NOTIFY_FLAG >", value, "phoneNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagGreaterThanOrEqualTo(String value) {
            addCriterion("PHONE_NOTIFY_FLAG >=", value, "phoneNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagLessThan(String value) {
            addCriterion("PHONE_NOTIFY_FLAG <", value, "phoneNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagLessThanOrEqualTo(String value) {
            addCriterion("PHONE_NOTIFY_FLAG <=", value, "phoneNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagLike(String value) {
            addCriterion("PHONE_NOTIFY_FLAG like", value, "phoneNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagNotLike(String value) {
            addCriterion("PHONE_NOTIFY_FLAG not like", value, "phoneNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagIn(List<String> values) {
            addCriterion("PHONE_NOTIFY_FLAG in", values, "phoneNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagNotIn(List<String> values) {
            addCriterion("PHONE_NOTIFY_FLAG not in", values, "phoneNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagBetween(String value1, String value2) {
            addCriterion("PHONE_NOTIFY_FLAG between", value1, value2, "phoneNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagNotBetween(String value1, String value2) {
            addCriterion("PHONE_NOTIFY_FLAG not between", value1, value2, "phoneNotifyFlag");
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