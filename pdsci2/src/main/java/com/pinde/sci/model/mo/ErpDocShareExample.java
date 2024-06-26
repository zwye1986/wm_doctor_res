package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ErpDocShareExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ErpDocShareExample() {
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

        public Criteria andDocFlowIsNull() {
            addCriterion("DOC_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDocFlowIsNotNull() {
            addCriterion("DOC_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDocFlowEqualTo(String value) {
            addCriterion("DOC_FLOW =", value, "docFlow");
            return (Criteria) this;
        }

        public Criteria andDocFlowNotEqualTo(String value) {
            addCriterion("DOC_FLOW <>", value, "docFlow");
            return (Criteria) this;
        }

        public Criteria andDocFlowGreaterThan(String value) {
            addCriterion("DOC_FLOW >", value, "docFlow");
            return (Criteria) this;
        }

        public Criteria andDocFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DOC_FLOW >=", value, "docFlow");
            return (Criteria) this;
        }

        public Criteria andDocFlowLessThan(String value) {
            addCriterion("DOC_FLOW <", value, "docFlow");
            return (Criteria) this;
        }

        public Criteria andDocFlowLessThanOrEqualTo(String value) {
            addCriterion("DOC_FLOW <=", value, "docFlow");
            return (Criteria) this;
        }

        public Criteria andDocFlowLike(String value) {
            addCriterion("DOC_FLOW like", value, "docFlow");
            return (Criteria) this;
        }

        public Criteria andDocFlowNotLike(String value) {
            addCriterion("DOC_FLOW not like", value, "docFlow");
            return (Criteria) this;
        }

        public Criteria andDocFlowIn(List<String> values) {
            addCriterion("DOC_FLOW in", values, "docFlow");
            return (Criteria) this;
        }

        public Criteria andDocFlowNotIn(List<String> values) {
            addCriterion("DOC_FLOW not in", values, "docFlow");
            return (Criteria) this;
        }

        public Criteria andDocFlowBetween(String value1, String value2) {
            addCriterion("DOC_FLOW between", value1, value2, "docFlow");
            return (Criteria) this;
        }

        public Criteria andDocFlowNotBetween(String value1, String value2) {
            addCriterion("DOC_FLOW not between", value1, value2, "docFlow");
            return (Criteria) this;
        }

        public Criteria andShareTypeIdIsNull() {
            addCriterion("SHARE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andShareTypeIdIsNotNull() {
            addCriterion("SHARE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andShareTypeIdEqualTo(String value) {
            addCriterion("SHARE_TYPE_ID =", value, "shareTypeId");
            return (Criteria) this;
        }

        public Criteria andShareTypeIdNotEqualTo(String value) {
            addCriterion("SHARE_TYPE_ID <>", value, "shareTypeId");
            return (Criteria) this;
        }

        public Criteria andShareTypeIdGreaterThan(String value) {
            addCriterion("SHARE_TYPE_ID >", value, "shareTypeId");
            return (Criteria) this;
        }

        public Criteria andShareTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SHARE_TYPE_ID >=", value, "shareTypeId");
            return (Criteria) this;
        }

        public Criteria andShareTypeIdLessThan(String value) {
            addCriterion("SHARE_TYPE_ID <", value, "shareTypeId");
            return (Criteria) this;
        }

        public Criteria andShareTypeIdLessThanOrEqualTo(String value) {
            addCriterion("SHARE_TYPE_ID <=", value, "shareTypeId");
            return (Criteria) this;
        }

        public Criteria andShareTypeIdLike(String value) {
            addCriterion("SHARE_TYPE_ID like", value, "shareTypeId");
            return (Criteria) this;
        }

        public Criteria andShareTypeIdNotLike(String value) {
            addCriterion("SHARE_TYPE_ID not like", value, "shareTypeId");
            return (Criteria) this;
        }

        public Criteria andShareTypeIdIn(List<String> values) {
            addCriterion("SHARE_TYPE_ID in", values, "shareTypeId");
            return (Criteria) this;
        }

        public Criteria andShareTypeIdNotIn(List<String> values) {
            addCriterion("SHARE_TYPE_ID not in", values, "shareTypeId");
            return (Criteria) this;
        }

        public Criteria andShareTypeIdBetween(String value1, String value2) {
            addCriterion("SHARE_TYPE_ID between", value1, value2, "shareTypeId");
            return (Criteria) this;
        }

        public Criteria andShareTypeIdNotBetween(String value1, String value2) {
            addCriterion("SHARE_TYPE_ID not between", value1, value2, "shareTypeId");
            return (Criteria) this;
        }

        public Criteria andShareTypeNameIsNull() {
            addCriterion("SHARE_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andShareTypeNameIsNotNull() {
            addCriterion("SHARE_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andShareTypeNameEqualTo(String value) {
            addCriterion("SHARE_TYPE_NAME =", value, "shareTypeName");
            return (Criteria) this;
        }

        public Criteria andShareTypeNameNotEqualTo(String value) {
            addCriterion("SHARE_TYPE_NAME <>", value, "shareTypeName");
            return (Criteria) this;
        }

        public Criteria andShareTypeNameGreaterThan(String value) {
            addCriterion("SHARE_TYPE_NAME >", value, "shareTypeName");
            return (Criteria) this;
        }

        public Criteria andShareTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SHARE_TYPE_NAME >=", value, "shareTypeName");
            return (Criteria) this;
        }

        public Criteria andShareTypeNameLessThan(String value) {
            addCriterion("SHARE_TYPE_NAME <", value, "shareTypeName");
            return (Criteria) this;
        }

        public Criteria andShareTypeNameLessThanOrEqualTo(String value) {
            addCriterion("SHARE_TYPE_NAME <=", value, "shareTypeName");
            return (Criteria) this;
        }

        public Criteria andShareTypeNameLike(String value) {
            addCriterion("SHARE_TYPE_NAME like", value, "shareTypeName");
            return (Criteria) this;
        }

        public Criteria andShareTypeNameNotLike(String value) {
            addCriterion("SHARE_TYPE_NAME not like", value, "shareTypeName");
            return (Criteria) this;
        }

        public Criteria andShareTypeNameIn(List<String> values) {
            addCriterion("SHARE_TYPE_NAME in", values, "shareTypeName");
            return (Criteria) this;
        }

        public Criteria andShareTypeNameNotIn(List<String> values) {
            addCriterion("SHARE_TYPE_NAME not in", values, "shareTypeName");
            return (Criteria) this;
        }

        public Criteria andShareTypeNameBetween(String value1, String value2) {
            addCriterion("SHARE_TYPE_NAME between", value1, value2, "shareTypeName");
            return (Criteria) this;
        }

        public Criteria andShareTypeNameNotBetween(String value1, String value2) {
            addCriterion("SHARE_TYPE_NAME not between", value1, value2, "shareTypeName");
            return (Criteria) this;
        }

        public Criteria andShareRecordFlowIsNull() {
            addCriterion("SHARE_RECORD_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andShareRecordFlowIsNotNull() {
            addCriterion("SHARE_RECORD_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andShareRecordFlowEqualTo(String value) {
            addCriterion("SHARE_RECORD_FLOW =", value, "shareRecordFlow");
            return (Criteria) this;
        }

        public Criteria andShareRecordFlowNotEqualTo(String value) {
            addCriterion("SHARE_RECORD_FLOW <>", value, "shareRecordFlow");
            return (Criteria) this;
        }

        public Criteria andShareRecordFlowGreaterThan(String value) {
            addCriterion("SHARE_RECORD_FLOW >", value, "shareRecordFlow");
            return (Criteria) this;
        }

        public Criteria andShareRecordFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SHARE_RECORD_FLOW >=", value, "shareRecordFlow");
            return (Criteria) this;
        }

        public Criteria andShareRecordFlowLessThan(String value) {
            addCriterion("SHARE_RECORD_FLOW <", value, "shareRecordFlow");
            return (Criteria) this;
        }

        public Criteria andShareRecordFlowLessThanOrEqualTo(String value) {
            addCriterion("SHARE_RECORD_FLOW <=", value, "shareRecordFlow");
            return (Criteria) this;
        }

        public Criteria andShareRecordFlowLike(String value) {
            addCriterion("SHARE_RECORD_FLOW like", value, "shareRecordFlow");
            return (Criteria) this;
        }

        public Criteria andShareRecordFlowNotLike(String value) {
            addCriterion("SHARE_RECORD_FLOW not like", value, "shareRecordFlow");
            return (Criteria) this;
        }

        public Criteria andShareRecordFlowIn(List<String> values) {
            addCriterion("SHARE_RECORD_FLOW in", values, "shareRecordFlow");
            return (Criteria) this;
        }

        public Criteria andShareRecordFlowNotIn(List<String> values) {
            addCriterion("SHARE_RECORD_FLOW not in", values, "shareRecordFlow");
            return (Criteria) this;
        }

        public Criteria andShareRecordFlowBetween(String value1, String value2) {
            addCriterion("SHARE_RECORD_FLOW between", value1, value2, "shareRecordFlow");
            return (Criteria) this;
        }

        public Criteria andShareRecordFlowNotBetween(String value1, String value2) {
            addCriterion("SHARE_RECORD_FLOW not between", value1, value2, "shareRecordFlow");
            return (Criteria) this;
        }

        public Criteria andShareRecordNameIsNull() {
            addCriterion("SHARE_RECORD_NAME is null");
            return (Criteria) this;
        }

        public Criteria andShareRecordNameIsNotNull() {
            addCriterion("SHARE_RECORD_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andShareRecordNameEqualTo(String value) {
            addCriterion("SHARE_RECORD_NAME =", value, "shareRecordName");
            return (Criteria) this;
        }

        public Criteria andShareRecordNameNotEqualTo(String value) {
            addCriterion("SHARE_RECORD_NAME <>", value, "shareRecordName");
            return (Criteria) this;
        }

        public Criteria andShareRecordNameGreaterThan(String value) {
            addCriterion("SHARE_RECORD_NAME >", value, "shareRecordName");
            return (Criteria) this;
        }

        public Criteria andShareRecordNameGreaterThanOrEqualTo(String value) {
            addCriterion("SHARE_RECORD_NAME >=", value, "shareRecordName");
            return (Criteria) this;
        }

        public Criteria andShareRecordNameLessThan(String value) {
            addCriterion("SHARE_RECORD_NAME <", value, "shareRecordName");
            return (Criteria) this;
        }

        public Criteria andShareRecordNameLessThanOrEqualTo(String value) {
            addCriterion("SHARE_RECORD_NAME <=", value, "shareRecordName");
            return (Criteria) this;
        }

        public Criteria andShareRecordNameLike(String value) {
            addCriterion("SHARE_RECORD_NAME like", value, "shareRecordName");
            return (Criteria) this;
        }

        public Criteria andShareRecordNameNotLike(String value) {
            addCriterion("SHARE_RECORD_NAME not like", value, "shareRecordName");
            return (Criteria) this;
        }

        public Criteria andShareRecordNameIn(List<String> values) {
            addCriterion("SHARE_RECORD_NAME in", values, "shareRecordName");
            return (Criteria) this;
        }

        public Criteria andShareRecordNameNotIn(List<String> values) {
            addCriterion("SHARE_RECORD_NAME not in", values, "shareRecordName");
            return (Criteria) this;
        }

        public Criteria andShareRecordNameBetween(String value1, String value2) {
            addCriterion("SHARE_RECORD_NAME between", value1, value2, "shareRecordName");
            return (Criteria) this;
        }

        public Criteria andShareRecordNameNotBetween(String value1, String value2) {
            addCriterion("SHARE_RECORD_NAME not between", value1, value2, "shareRecordName");
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