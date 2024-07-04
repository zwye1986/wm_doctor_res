package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class PubProjSearchExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PubProjSearchExample() {
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

        public Criteria andProjSearchFlowIsNull() {
            addCriterion("PROJ_SEARCH_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andProjSearchFlowIsNotNull() {
            addCriterion("PROJ_SEARCH_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andProjSearchFlowEqualTo(String value) {
            addCriterion("PROJ_SEARCH_FLOW =", value, "projSearchFlow");
            return (Criteria) this;
        }

        public Criteria andProjSearchFlowNotEqualTo(String value) {
            addCriterion("PROJ_SEARCH_FLOW <>", value, "projSearchFlow");
            return (Criteria) this;
        }

        public Criteria andProjSearchFlowGreaterThan(String value) {
            addCriterion("PROJ_SEARCH_FLOW >", value, "projSearchFlow");
            return (Criteria) this;
        }

        public Criteria andProjSearchFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_SEARCH_FLOW >=", value, "projSearchFlow");
            return (Criteria) this;
        }

        public Criteria andProjSearchFlowLessThan(String value) {
            addCriterion("PROJ_SEARCH_FLOW <", value, "projSearchFlow");
            return (Criteria) this;
        }

        public Criteria andProjSearchFlowLessThanOrEqualTo(String value) {
            addCriterion("PROJ_SEARCH_FLOW <=", value, "projSearchFlow");
            return (Criteria) this;
        }

        public Criteria andProjSearchFlowLike(String value) {
            addCriterion("PROJ_SEARCH_FLOW like", value, "projSearchFlow");
            return (Criteria) this;
        }

        public Criteria andProjSearchFlowNotLike(String value) {
            addCriterion("PROJ_SEARCH_FLOW not like", value, "projSearchFlow");
            return (Criteria) this;
        }

        public Criteria andProjSearchFlowIn(List<String> values) {
            addCriterion("PROJ_SEARCH_FLOW in", values, "projSearchFlow");
            return (Criteria) this;
        }

        public Criteria andProjSearchFlowNotIn(List<String> values) {
            addCriterion("PROJ_SEARCH_FLOW not in", values, "projSearchFlow");
            return (Criteria) this;
        }

        public Criteria andProjSearchFlowBetween(String value1, String value2) {
            addCriterion("PROJ_SEARCH_FLOW between", value1, value2, "projSearchFlow");
            return (Criteria) this;
        }

        public Criteria andProjSearchFlowNotBetween(String value1, String value2) {
            addCriterion("PROJ_SEARCH_FLOW not between", value1, value2, "projSearchFlow");
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

        public Criteria andSearchColumnIsNull() {
            addCriterion("SEARCH_COLUMN is null");
            return (Criteria) this;
        }

        public Criteria andSearchColumnIsNotNull() {
            addCriterion("SEARCH_COLUMN is not null");
            return (Criteria) this;
        }

        public Criteria andSearchColumnEqualTo(String value) {
            addCriterion("SEARCH_COLUMN =", value, "searchColumn");
            return (Criteria) this;
        }

        public Criteria andSearchColumnNotEqualTo(String value) {
            addCriterion("SEARCH_COLUMN <>", value, "searchColumn");
            return (Criteria) this;
        }

        public Criteria andSearchColumnGreaterThan(String value) {
            addCriterion("SEARCH_COLUMN >", value, "searchColumn");
            return (Criteria) this;
        }

        public Criteria andSearchColumnGreaterThanOrEqualTo(String value) {
            addCriterion("SEARCH_COLUMN >=", value, "searchColumn");
            return (Criteria) this;
        }

        public Criteria andSearchColumnLessThan(String value) {
            addCriterion("SEARCH_COLUMN <", value, "searchColumn");
            return (Criteria) this;
        }

        public Criteria andSearchColumnLessThanOrEqualTo(String value) {
            addCriterion("SEARCH_COLUMN <=", value, "searchColumn");
            return (Criteria) this;
        }

        public Criteria andSearchColumnLike(String value) {
            addCriterion("SEARCH_COLUMN like", value, "searchColumn");
            return (Criteria) this;
        }

        public Criteria andSearchColumnNotLike(String value) {
            addCriterion("SEARCH_COLUMN not like", value, "searchColumn");
            return (Criteria) this;
        }

        public Criteria andSearchColumnIn(List<String> values) {
            addCriterion("SEARCH_COLUMN in", values, "searchColumn");
            return (Criteria) this;
        }

        public Criteria andSearchColumnNotIn(List<String> values) {
            addCriterion("SEARCH_COLUMN not in", values, "searchColumn");
            return (Criteria) this;
        }

        public Criteria andSearchColumnBetween(String value1, String value2) {
            addCriterion("SEARCH_COLUMN between", value1, value2, "searchColumn");
            return (Criteria) this;
        }

        public Criteria andSearchColumnNotBetween(String value1, String value2) {
            addCriterion("SEARCH_COLUMN not between", value1, value2, "searchColumn");
            return (Criteria) this;
        }

        public Criteria andSearchValueIsNull() {
            addCriterion("SEARCH_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andSearchValueIsNotNull() {
            addCriterion("SEARCH_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andSearchValueEqualTo(String value) {
            addCriterion("SEARCH_VALUE =", value, "searchValue");
            return (Criteria) this;
        }

        public Criteria andSearchValueNotEqualTo(String value) {
            addCriterion("SEARCH_VALUE <>", value, "searchValue");
            return (Criteria) this;
        }

        public Criteria andSearchValueGreaterThan(String value) {
            addCriterion("SEARCH_VALUE >", value, "searchValue");
            return (Criteria) this;
        }

        public Criteria andSearchValueGreaterThanOrEqualTo(String value) {
            addCriterion("SEARCH_VALUE >=", value, "searchValue");
            return (Criteria) this;
        }

        public Criteria andSearchValueLessThan(String value) {
            addCriterion("SEARCH_VALUE <", value, "searchValue");
            return (Criteria) this;
        }

        public Criteria andSearchValueLessThanOrEqualTo(String value) {
            addCriterion("SEARCH_VALUE <=", value, "searchValue");
            return (Criteria) this;
        }

        public Criteria andSearchValueLike(String value) {
            addCriterion("SEARCH_VALUE like", value, "searchValue");
            return (Criteria) this;
        }

        public Criteria andSearchValueNotLike(String value) {
            addCriterion("SEARCH_VALUE not like", value, "searchValue");
            return (Criteria) this;
        }

        public Criteria andSearchValueIn(List<String> values) {
            addCriterion("SEARCH_VALUE in", values, "searchValue");
            return (Criteria) this;
        }

        public Criteria andSearchValueNotIn(List<String> values) {
            addCriterion("SEARCH_VALUE not in", values, "searchValue");
            return (Criteria) this;
        }

        public Criteria andSearchValueBetween(String value1, String value2) {
            addCriterion("SEARCH_VALUE between", value1, value2, "searchValue");
            return (Criteria) this;
        }

        public Criteria andSearchValueNotBetween(String value1, String value2) {
            addCriterion("SEARCH_VALUE not between", value1, value2, "searchValue");
            return (Criteria) this;
        }

        public Criteria andSearchRemarkIsNull() {
            addCriterion("SEARCH_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andSearchRemarkIsNotNull() {
            addCriterion("SEARCH_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andSearchRemarkEqualTo(String value) {
            addCriterion("SEARCH_REMARK =", value, "searchRemark");
            return (Criteria) this;
        }

        public Criteria andSearchRemarkNotEqualTo(String value) {
            addCriterion("SEARCH_REMARK <>", value, "searchRemark");
            return (Criteria) this;
        }

        public Criteria andSearchRemarkGreaterThan(String value) {
            addCriterion("SEARCH_REMARK >", value, "searchRemark");
            return (Criteria) this;
        }

        public Criteria andSearchRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("SEARCH_REMARK >=", value, "searchRemark");
            return (Criteria) this;
        }

        public Criteria andSearchRemarkLessThan(String value) {
            addCriterion("SEARCH_REMARK <", value, "searchRemark");
            return (Criteria) this;
        }

        public Criteria andSearchRemarkLessThanOrEqualTo(String value) {
            addCriterion("SEARCH_REMARK <=", value, "searchRemark");
            return (Criteria) this;
        }

        public Criteria andSearchRemarkLike(String value) {
            addCriterion("SEARCH_REMARK like", value, "searchRemark");
            return (Criteria) this;
        }

        public Criteria andSearchRemarkNotLike(String value) {
            addCriterion("SEARCH_REMARK not like", value, "searchRemark");
            return (Criteria) this;
        }

        public Criteria andSearchRemarkIn(List<String> values) {
            addCriterion("SEARCH_REMARK in", values, "searchRemark");
            return (Criteria) this;
        }

        public Criteria andSearchRemarkNotIn(List<String> values) {
            addCriterion("SEARCH_REMARK not in", values, "searchRemark");
            return (Criteria) this;
        }

        public Criteria andSearchRemarkBetween(String value1, String value2) {
            addCriterion("SEARCH_REMARK between", value1, value2, "searchRemark");
            return (Criteria) this;
        }

        public Criteria andSearchRemarkNotBetween(String value1, String value2) {
            addCriterion("SEARCH_REMARK not between", value1, value2, "searchRemark");
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