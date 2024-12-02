package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class JsresLoginInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JsresLoginInfoExample() {
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

        public Criteria andReocrdFlowIsNull() {
            addCriterion("REOCRD_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andReocrdFlowIsNotNull() {
            addCriterion("REOCRD_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andReocrdFlowEqualTo(String value) {
            addCriterion("REOCRD_FLOW =", value, "reocrdFlow");
            return (Criteria) this;
        }

        public Criteria andReocrdFlowNotEqualTo(String value) {
            addCriterion("REOCRD_FLOW <>", value, "reocrdFlow");
            return (Criteria) this;
        }

        public Criteria andReocrdFlowGreaterThan(String value) {
            addCriterion("REOCRD_FLOW >", value, "reocrdFlow");
            return (Criteria) this;
        }

        public Criteria andReocrdFlowGreaterThanOrEqualTo(String value) {
            addCriterion("REOCRD_FLOW >=", value, "reocrdFlow");
            return (Criteria) this;
        }

        public Criteria andReocrdFlowLessThan(String value) {
            addCriterion("REOCRD_FLOW <", value, "reocrdFlow");
            return (Criteria) this;
        }

        public Criteria andReocrdFlowLessThanOrEqualTo(String value) {
            addCriterion("REOCRD_FLOW <=", value, "reocrdFlow");
            return (Criteria) this;
        }

        public Criteria andReocrdFlowLike(String value) {
            addCriterion("REOCRD_FLOW like", value, "reocrdFlow");
            return (Criteria) this;
        }

        public Criteria andReocrdFlowNotLike(String value) {
            addCriterion("REOCRD_FLOW not like", value, "reocrdFlow");
            return (Criteria) this;
        }

        public Criteria andReocrdFlowIn(List<String> values) {
            addCriterion("REOCRD_FLOW in", values, "reocrdFlow");
            return (Criteria) this;
        }

        public Criteria andReocrdFlowNotIn(List<String> values) {
            addCriterion("REOCRD_FLOW not in", values, "reocrdFlow");
            return (Criteria) this;
        }

        public Criteria andReocrdFlowBetween(String value1, String value2) {
            addCriterion("REOCRD_FLOW between", value1, value2, "reocrdFlow");
            return (Criteria) this;
        }

        public Criteria andReocrdFlowNotBetween(String value1, String value2) {
            addCriterion("REOCRD_FLOW not between", value1, value2, "reocrdFlow");
            return (Criteria) this;
        }

        public Criteria andLoginDomainIsNull() {
            addCriterion("LOGIN_DOMAIN is null");
            return (Criteria) this;
        }

        public Criteria andLoginDomainIsNotNull() {
            addCriterion("LOGIN_DOMAIN is not null");
            return (Criteria) this;
        }

        public Criteria andLoginDomainEqualTo(String value) {
            addCriterion("LOGIN_DOMAIN =", value, "loginDomain");
            return (Criteria) this;
        }

        public Criteria andLoginDomainNotEqualTo(String value) {
            addCriterion("LOGIN_DOMAIN <>", value, "loginDomain");
            return (Criteria) this;
        }

        public Criteria andLoginDomainGreaterThan(String value) {
            addCriterion("LOGIN_DOMAIN >", value, "loginDomain");
            return (Criteria) this;
        }

        public Criteria andLoginDomainGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_DOMAIN >=", value, "loginDomain");
            return (Criteria) this;
        }

        public Criteria andLoginDomainLessThan(String value) {
            addCriterion("LOGIN_DOMAIN <", value, "loginDomain");
            return (Criteria) this;
        }

        public Criteria andLoginDomainLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_DOMAIN <=", value, "loginDomain");
            return (Criteria) this;
        }

        public Criteria andLoginDomainLike(String value) {
            addCriterion("LOGIN_DOMAIN like", value, "loginDomain");
            return (Criteria) this;
        }

        public Criteria andLoginDomainNotLike(String value) {
            addCriterion("LOGIN_DOMAIN not like", value, "loginDomain");
            return (Criteria) this;
        }

        public Criteria andLoginDomainIn(List<String> values) {
            addCriterion("LOGIN_DOMAIN in", values, "loginDomain");
            return (Criteria) this;
        }

        public Criteria andLoginDomainNotIn(List<String> values) {
            addCriterion("LOGIN_DOMAIN not in", values, "loginDomain");
            return (Criteria) this;
        }

        public Criteria andLoginDomainBetween(String value1, String value2) {
            addCriterion("LOGIN_DOMAIN between", value1, value2, "loginDomain");
            return (Criteria) this;
        }

        public Criteria andLoginDomainNotBetween(String value1, String value2) {
            addCriterion("LOGIN_DOMAIN not between", value1, value2, "loginDomain");
            return (Criteria) this;
        }

        public Criteria andLoginDomainSuffixIsNull() {
            addCriterion("LOGIN_DOMAIN_SUFFIX is null");
            return (Criteria) this;
        }

        public Criteria andLoginDomainSuffixIsNotNull() {
            addCriterion("LOGIN_DOMAIN_SUFFIX is not null");
            return (Criteria) this;
        }

        public Criteria andLoginDomainSuffixEqualTo(String value) {
            addCriterion("LOGIN_DOMAIN_SUFFIX =", value, "loginDomainSuffix");
            return (Criteria) this;
        }

        public Criteria andLoginDomainSuffixNotEqualTo(String value) {
            addCriterion("LOGIN_DOMAIN_SUFFIX <>", value, "loginDomainSuffix");
            return (Criteria) this;
        }

        public Criteria andLoginDomainSuffixGreaterThan(String value) {
            addCriterion("LOGIN_DOMAIN_SUFFIX >", value, "loginDomainSuffix");
            return (Criteria) this;
        }

        public Criteria andLoginDomainSuffixGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_DOMAIN_SUFFIX >=", value, "loginDomainSuffix");
            return (Criteria) this;
        }

        public Criteria andLoginDomainSuffixLessThan(String value) {
            addCriterion("LOGIN_DOMAIN_SUFFIX <", value, "loginDomainSuffix");
            return (Criteria) this;
        }

        public Criteria andLoginDomainSuffixLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_DOMAIN_SUFFIX <=", value, "loginDomainSuffix");
            return (Criteria) this;
        }

        public Criteria andLoginDomainSuffixLike(String value) {
            addCriterion("LOGIN_DOMAIN_SUFFIX like", value, "loginDomainSuffix");
            return (Criteria) this;
        }

        public Criteria andLoginDomainSuffixNotLike(String value) {
            addCriterion("LOGIN_DOMAIN_SUFFIX not like", value, "loginDomainSuffix");
            return (Criteria) this;
        }

        public Criteria andLoginDomainSuffixIn(List<String> values) {
            addCriterion("LOGIN_DOMAIN_SUFFIX in", values, "loginDomainSuffix");
            return (Criteria) this;
        }

        public Criteria andLoginDomainSuffixNotIn(List<String> values) {
            addCriterion("LOGIN_DOMAIN_SUFFIX not in", values, "loginDomainSuffix");
            return (Criteria) this;
        }

        public Criteria andLoginDomainSuffixBetween(String value1, String value2) {
            addCriterion("LOGIN_DOMAIN_SUFFIX between", value1, value2, "loginDomainSuffix");
            return (Criteria) this;
        }

        public Criteria andLoginDomainSuffixNotBetween(String value1, String value2) {
            addCriterion("LOGIN_DOMAIN_SUFFIX not between", value1, value2, "loginDomainSuffix");
            return (Criteria) this;
        }

        public Criteria andLoginUrlIsNull() {
            addCriterion("LOGIN_URL is null");
            return (Criteria) this;
        }

        public Criteria andLoginUrlIsNotNull() {
            addCriterion("LOGIN_URL is not null");
            return (Criteria) this;
        }

        public Criteria andLoginUrlEqualTo(String value) {
            addCriterion("LOGIN_URL =", value, "loginUrl");
            return (Criteria) this;
        }

        public Criteria andLoginUrlNotEqualTo(String value) {
            addCriterion("LOGIN_URL <>", value, "loginUrl");
            return (Criteria) this;
        }

        public Criteria andLoginUrlGreaterThan(String value) {
            addCriterion("LOGIN_URL >", value, "loginUrl");
            return (Criteria) this;
        }

        public Criteria andLoginUrlGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_URL >=", value, "loginUrl");
            return (Criteria) this;
        }

        public Criteria andLoginUrlLessThan(String value) {
            addCriterion("LOGIN_URL <", value, "loginUrl");
            return (Criteria) this;
        }

        public Criteria andLoginUrlLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_URL <=", value, "loginUrl");
            return (Criteria) this;
        }

        public Criteria andLoginUrlLike(String value) {
            addCriterion("LOGIN_URL like", value, "loginUrl");
            return (Criteria) this;
        }

        public Criteria andLoginUrlNotLike(String value) {
            addCriterion("LOGIN_URL not like", value, "loginUrl");
            return (Criteria) this;
        }

        public Criteria andLoginUrlIn(List<String> values) {
            addCriterion("LOGIN_URL in", values, "loginUrl");
            return (Criteria) this;
        }

        public Criteria andLoginUrlNotIn(List<String> values) {
            addCriterion("LOGIN_URL not in", values, "loginUrl");
            return (Criteria) this;
        }

        public Criteria andLoginUrlBetween(String value1, String value2) {
            addCriterion("LOGIN_URL between", value1, value2, "loginUrl");
            return (Criteria) this;
        }

        public Criteria andLoginUrlNotBetween(String value1, String value2) {
            addCriterion("LOGIN_URL not between", value1, value2, "loginUrl");
            return (Criteria) this;
        }

        public Criteria andLoginTitleIsNull() {
            addCriterion("LOGIN_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andLoginTitleIsNotNull() {
            addCriterion("LOGIN_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andLoginTitleEqualTo(String value) {
            addCriterion("LOGIN_TITLE =", value, "loginTitle");
            return (Criteria) this;
        }

        public Criteria andLoginTitleNotEqualTo(String value) {
            addCriterion("LOGIN_TITLE <>", value, "loginTitle");
            return (Criteria) this;
        }

        public Criteria andLoginTitleGreaterThan(String value) {
            addCriterion("LOGIN_TITLE >", value, "loginTitle");
            return (Criteria) this;
        }

        public Criteria andLoginTitleGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_TITLE >=", value, "loginTitle");
            return (Criteria) this;
        }

        public Criteria andLoginTitleLessThan(String value) {
            addCriterion("LOGIN_TITLE <", value, "loginTitle");
            return (Criteria) this;
        }

        public Criteria andLoginTitleLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_TITLE <=", value, "loginTitle");
            return (Criteria) this;
        }

        public Criteria andLoginTitleLike(String value) {
            addCriterion("LOGIN_TITLE like", value, "loginTitle");
            return (Criteria) this;
        }

        public Criteria andLoginTitleNotLike(String value) {
            addCriterion("LOGIN_TITLE not like", value, "loginTitle");
            return (Criteria) this;
        }

        public Criteria andLoginTitleIn(List<String> values) {
            addCriterion("LOGIN_TITLE in", values, "loginTitle");
            return (Criteria) this;
        }

        public Criteria andLoginTitleNotIn(List<String> values) {
            addCriterion("LOGIN_TITLE not in", values, "loginTitle");
            return (Criteria) this;
        }

        public Criteria andLoginTitleBetween(String value1, String value2) {
            addCriterion("LOGIN_TITLE between", value1, value2, "loginTitle");
            return (Criteria) this;
        }

        public Criteria andLoginTitleNotBetween(String value1, String value2) {
            addCriterion("LOGIN_TITLE not between", value1, value2, "loginTitle");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNull() {
            addCriterion("TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(String value) {
            addCriterion("TYPE_ID =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(String value) {
            addCriterion("TYPE_ID <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(String value) {
            addCriterion("TYPE_ID >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE_ID >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(String value) {
            addCriterion("TYPE_ID <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(String value) {
            addCriterion("TYPE_ID <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLike(String value) {
            addCriterion("TYPE_ID like", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotLike(String value) {
            addCriterion("TYPE_ID not like", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<String> values) {
            addCriterion("TYPE_ID in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<String> values) {
            addCriterion("TYPE_ID not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(String value1, String value2) {
            addCriterion("TYPE_ID between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(String value1, String value2) {
            addCriterion("TYPE_ID not between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNull() {
            addCriterion("TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNotNull() {
            addCriterion("TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTypeNameEqualTo(String value) {
            addCriterion("TYPE_NAME =", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotEqualTo(String value) {
            addCriterion("TYPE_NAME <>", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThan(String value) {
            addCriterion("TYPE_NAME >", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE_NAME >=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThan(String value) {
            addCriterion("TYPE_NAME <", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThanOrEqualTo(String value) {
            addCriterion("TYPE_NAME <=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLike(String value) {
            addCriterion("TYPE_NAME like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotLike(String value) {
            addCriterion("TYPE_NAME not like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameIn(List<String> values) {
            addCriterion("TYPE_NAME in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotIn(List<String> values) {
            addCriterion("TYPE_NAME not in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameBetween(String value1, String value2) {
            addCriterion("TYPE_NAME between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotBetween(String value1, String value2) {
            addCriterion("TYPE_NAME not between", value1, value2, "typeName");
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