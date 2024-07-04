package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class SrmFundSchemeDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SrmFundSchemeDetailExample() {
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

        public Criteria andItemFlowIsNull() {
            addCriterion("ITEM_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andItemFlowIsNotNull() {
            addCriterion("ITEM_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andItemFlowEqualTo(String value) {
            addCriterion("ITEM_FLOW =", value, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowNotEqualTo(String value) {
            addCriterion("ITEM_FLOW <>", value, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowGreaterThan(String value) {
            addCriterion("ITEM_FLOW >", value, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_FLOW >=", value, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowLessThan(String value) {
            addCriterion("ITEM_FLOW <", value, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowLessThanOrEqualTo(String value) {
            addCriterion("ITEM_FLOW <=", value, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowLike(String value) {
            addCriterion("ITEM_FLOW like", value, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowNotLike(String value) {
            addCriterion("ITEM_FLOW not like", value, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowIn(List<String> values) {
            addCriterion("ITEM_FLOW in", values, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowNotIn(List<String> values) {
            addCriterion("ITEM_FLOW not in", values, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowBetween(String value1, String value2) {
            addCriterion("ITEM_FLOW between", value1, value2, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andItemFlowNotBetween(String value1, String value2) {
            addCriterion("ITEM_FLOW not between", value1, value2, "itemFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowIsNull() {
            addCriterion("SCHEME_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowIsNotNull() {
            addCriterion("SCHEME_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowEqualTo(String value) {
            addCriterion("SCHEME_FLOW =", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowNotEqualTo(String value) {
            addCriterion("SCHEME_FLOW <>", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowGreaterThan(String value) {
            addCriterion("SCHEME_FLOW >", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SCHEME_FLOW >=", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowLessThan(String value) {
            addCriterion("SCHEME_FLOW <", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowLessThanOrEqualTo(String value) {
            addCriterion("SCHEME_FLOW <=", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowLike(String value) {
            addCriterion("SCHEME_FLOW like", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowNotLike(String value) {
            addCriterion("SCHEME_FLOW not like", value, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowIn(List<String> values) {
            addCriterion("SCHEME_FLOW in", values, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowNotIn(List<String> values) {
            addCriterion("SCHEME_FLOW not in", values, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowBetween(String value1, String value2) {
            addCriterion("SCHEME_FLOW between", value1, value2, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeFlowNotBetween(String value1, String value2) {
            addCriterion("SCHEME_FLOW not between", value1, value2, "schemeFlow");
            return (Criteria) this;
        }

        public Criteria andSchemeNameIsNull() {
            addCriterion("SCHEME_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSchemeNameIsNotNull() {
            addCriterion("SCHEME_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSchemeNameEqualTo(String value) {
            addCriterion("SCHEME_NAME =", value, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameNotEqualTo(String value) {
            addCriterion("SCHEME_NAME <>", value, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameGreaterThan(String value) {
            addCriterion("SCHEME_NAME >", value, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCHEME_NAME >=", value, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameLessThan(String value) {
            addCriterion("SCHEME_NAME <", value, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameLessThanOrEqualTo(String value) {
            addCriterion("SCHEME_NAME <=", value, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameLike(String value) {
            addCriterion("SCHEME_NAME like", value, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameNotLike(String value) {
            addCriterion("SCHEME_NAME not like", value, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameIn(List<String> values) {
            addCriterion("SCHEME_NAME in", values, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameNotIn(List<String> values) {
            addCriterion("SCHEME_NAME not in", values, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameBetween(String value1, String value2) {
            addCriterion("SCHEME_NAME between", value1, value2, "schemeName");
            return (Criteria) this;
        }

        public Criteria andSchemeNameNotBetween(String value1, String value2) {
            addCriterion("SCHEME_NAME not between", value1, value2, "schemeName");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNull() {
            addCriterion("ITEM_ID is null");
            return (Criteria) this;
        }

        public Criteria andItemIdIsNotNull() {
            addCriterion("ITEM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andItemIdEqualTo(String value) {
            addCriterion("ITEM_ID =", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotEqualTo(String value) {
            addCriterion("ITEM_ID <>", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThan(String value) {
            addCriterion("ITEM_ID >", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_ID >=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThan(String value) {
            addCriterion("ITEM_ID <", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLessThanOrEqualTo(String value) {
            addCriterion("ITEM_ID <=", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdLike(String value) {
            addCriterion("ITEM_ID like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotLike(String value) {
            addCriterion("ITEM_ID not like", value, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdIn(List<String> values) {
            addCriterion("ITEM_ID in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotIn(List<String> values) {
            addCriterion("ITEM_ID not in", values, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdBetween(String value1, String value2) {
            addCriterion("ITEM_ID between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemIdNotBetween(String value1, String value2) {
            addCriterion("ITEM_ID not between", value1, value2, "itemId");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNull() {
            addCriterion("ITEM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNotNull() {
            addCriterion("ITEM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andItemNameEqualTo(String value) {
            addCriterion("ITEM_NAME =", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotEqualTo(String value) {
            addCriterion("ITEM_NAME <>", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThan(String value) {
            addCriterion("ITEM_NAME >", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_NAME >=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThan(String value) {
            addCriterion("ITEM_NAME <", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThanOrEqualTo(String value) {
            addCriterion("ITEM_NAME <=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLike(String value) {
            addCriterion("ITEM_NAME like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotLike(String value) {
            addCriterion("ITEM_NAME not like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameIn(List<String> values) {
            addCriterion("ITEM_NAME in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotIn(List<String> values) {
            addCriterion("ITEM_NAME not in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameBetween(String value1, String value2) {
            addCriterion("ITEM_NAME between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotBetween(String value1, String value2) {
            addCriterion("ITEM_NAME not between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andMaxLimitIsNull() {
            addCriterion("MAX_LIMIT is null");
            return (Criteria) this;
        }

        public Criteria andMaxLimitIsNotNull() {
            addCriterion("MAX_LIMIT is not null");
            return (Criteria) this;
        }

        public Criteria andMaxLimitEqualTo(String value) {
            addCriterion("MAX_LIMIT =", value, "maxLimit");
            return (Criteria) this;
        }

        public Criteria andMaxLimitNotEqualTo(String value) {
            addCriterion("MAX_LIMIT <>", value, "maxLimit");
            return (Criteria) this;
        }

        public Criteria andMaxLimitGreaterThan(String value) {
            addCriterion("MAX_LIMIT >", value, "maxLimit");
            return (Criteria) this;
        }

        public Criteria andMaxLimitGreaterThanOrEqualTo(String value) {
            addCriterion("MAX_LIMIT >=", value, "maxLimit");
            return (Criteria) this;
        }

        public Criteria andMaxLimitLessThan(String value) {
            addCriterion("MAX_LIMIT <", value, "maxLimit");
            return (Criteria) this;
        }

        public Criteria andMaxLimitLessThanOrEqualTo(String value) {
            addCriterion("MAX_LIMIT <=", value, "maxLimit");
            return (Criteria) this;
        }

        public Criteria andMaxLimitLike(String value) {
            addCriterion("MAX_LIMIT like", value, "maxLimit");
            return (Criteria) this;
        }

        public Criteria andMaxLimitNotLike(String value) {
            addCriterion("MAX_LIMIT not like", value, "maxLimit");
            return (Criteria) this;
        }

        public Criteria andMaxLimitIn(List<String> values) {
            addCriterion("MAX_LIMIT in", values, "maxLimit");
            return (Criteria) this;
        }

        public Criteria andMaxLimitNotIn(List<String> values) {
            addCriterion("MAX_LIMIT not in", values, "maxLimit");
            return (Criteria) this;
        }

        public Criteria andMaxLimitBetween(String value1, String value2) {
            addCriterion("MAX_LIMIT between", value1, value2, "maxLimit");
            return (Criteria) this;
        }

        public Criteria andMaxLimitNotBetween(String value1, String value2) {
            addCriterion("MAX_LIMIT not between", value1, value2, "maxLimit");
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

        public Criteria andItemPidIsNull() {
            addCriterion("ITEM_PID is null");
            return (Criteria) this;
        }

        public Criteria andItemPidIsNotNull() {
            addCriterion("ITEM_PID is not null");
            return (Criteria) this;
        }

        public Criteria andItemPidEqualTo(String value) {
            addCriterion("ITEM_PID =", value, "itemPid");
            return (Criteria) this;
        }

        public Criteria andItemPidNotEqualTo(String value) {
            addCriterion("ITEM_PID <>", value, "itemPid");
            return (Criteria) this;
        }

        public Criteria andItemPidGreaterThan(String value) {
            addCriterion("ITEM_PID >", value, "itemPid");
            return (Criteria) this;
        }

        public Criteria andItemPidGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_PID >=", value, "itemPid");
            return (Criteria) this;
        }

        public Criteria andItemPidLessThan(String value) {
            addCriterion("ITEM_PID <", value, "itemPid");
            return (Criteria) this;
        }

        public Criteria andItemPidLessThanOrEqualTo(String value) {
            addCriterion("ITEM_PID <=", value, "itemPid");
            return (Criteria) this;
        }

        public Criteria andItemPidLike(String value) {
            addCriterion("ITEM_PID like", value, "itemPid");
            return (Criteria) this;
        }

        public Criteria andItemPidNotLike(String value) {
            addCriterion("ITEM_PID not like", value, "itemPid");
            return (Criteria) this;
        }

        public Criteria andItemPidIn(List<String> values) {
            addCriterion("ITEM_PID in", values, "itemPid");
            return (Criteria) this;
        }

        public Criteria andItemPidNotIn(List<String> values) {
            addCriterion("ITEM_PID not in", values, "itemPid");
            return (Criteria) this;
        }

        public Criteria andItemPidBetween(String value1, String value2) {
            addCriterion("ITEM_PID between", value1, value2, "itemPid");
            return (Criteria) this;
        }

        public Criteria andItemPidNotBetween(String value1, String value2) {
            addCriterion("ITEM_PID not between", value1, value2, "itemPid");
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