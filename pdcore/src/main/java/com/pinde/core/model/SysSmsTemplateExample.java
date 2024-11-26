package com.pinde.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SysSmsTemplateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysSmsTemplateExample() {
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

        public Criteria andSmsTempFlowIsNull() {
            addCriterion("SMS_TEMP_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowIsNotNull() {
            addCriterion("SMS_TEMP_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowEqualTo(String value) {
            addCriterion("SMS_TEMP_FLOW =", value, "smsTempFlow");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowNotEqualTo(String value) {
            addCriterion("SMS_TEMP_FLOW <>", value, "smsTempFlow");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowGreaterThan(String value) {
            addCriterion("SMS_TEMP_FLOW >", value, "smsTempFlow");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SMS_TEMP_FLOW >=", value, "smsTempFlow");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowLessThan(String value) {
            addCriterion("SMS_TEMP_FLOW <", value, "smsTempFlow");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowLessThanOrEqualTo(String value) {
            addCriterion("SMS_TEMP_FLOW <=", value, "smsTempFlow");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowLike(String value) {
            addCriterion("SMS_TEMP_FLOW like", value, "smsTempFlow");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowNotLike(String value) {
            addCriterion("SMS_TEMP_FLOW not like", value, "smsTempFlow");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowIn(List<String> values) {
            addCriterion("SMS_TEMP_FLOW in", values, "smsTempFlow");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowNotIn(List<String> values) {
            addCriterion("SMS_TEMP_FLOW not in", values, "smsTempFlow");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowBetween(String value1, String value2) {
            addCriterion("SMS_TEMP_FLOW between", value1, value2, "smsTempFlow");
            return (Criteria) this;
        }

        public Criteria andSmsTempFlowNotBetween(String value1, String value2) {
            addCriterion("SMS_TEMP_FLOW not between", value1, value2, "smsTempFlow");
            return (Criteria) this;
        }

        public Criteria andWsIdIsNull() {
            addCriterion("WS_ID is null");
            return (Criteria) this;
        }

        public Criteria andWsIdIsNotNull() {
            addCriterion("WS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andWsIdEqualTo(String value) {
            addCriterion("WS_ID =", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdNotEqualTo(String value) {
            addCriterion("WS_ID <>", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdGreaterThan(String value) {
            addCriterion("WS_ID >", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdGreaterThanOrEqualTo(String value) {
            addCriterion("WS_ID >=", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdLessThan(String value) {
            addCriterion("WS_ID <", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdLessThanOrEqualTo(String value) {
            addCriterion("WS_ID <=", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdLike(String value) {
            addCriterion("WS_ID like", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdNotLike(String value) {
            addCriterion("WS_ID not like", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdIn(List<String> values) {
            addCriterion("WS_ID in", values, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdNotIn(List<String> values) {
            addCriterion("WS_ID not in", values, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdBetween(String value1, String value2) {
            addCriterion("WS_ID between", value1, value2, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdNotBetween(String value1, String value2) {
            addCriterion("WS_ID not between", value1, value2, "wsId");
            return (Criteria) this;
        }

        public Criteria andTempNameIsNull() {
            addCriterion("TEMP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTempNameIsNotNull() {
            addCriterion("TEMP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTempNameEqualTo(String value) {
            addCriterion("TEMP_NAME =", value, "tempName");
            return (Criteria) this;
        }

        public Criteria andTempNameNotEqualTo(String value) {
            addCriterion("TEMP_NAME <>", value, "tempName");
            return (Criteria) this;
        }

        public Criteria andTempNameGreaterThan(String value) {
            addCriterion("TEMP_NAME >", value, "tempName");
            return (Criteria) this;
        }

        public Criteria andTempNameGreaterThanOrEqualTo(String value) {
            addCriterion("TEMP_NAME >=", value, "tempName");
            return (Criteria) this;
        }

        public Criteria andTempNameLessThan(String value) {
            addCriterion("TEMP_NAME <", value, "tempName");
            return (Criteria) this;
        }

        public Criteria andTempNameLessThanOrEqualTo(String value) {
            addCriterion("TEMP_NAME <=", value, "tempName");
            return (Criteria) this;
        }

        public Criteria andTempNameLike(String value) {
            addCriterion("TEMP_NAME like", value, "tempName");
            return (Criteria) this;
        }

        public Criteria andTempNameNotLike(String value) {
            addCriterion("TEMP_NAME not like", value, "tempName");
            return (Criteria) this;
        }

        public Criteria andTempNameIn(List<String> values) {
            addCriterion("TEMP_NAME in", values, "tempName");
            return (Criteria) this;
        }

        public Criteria andTempNameNotIn(List<String> values) {
            addCriterion("TEMP_NAME not in", values, "tempName");
            return (Criteria) this;
        }

        public Criteria andTempNameBetween(String value1, String value2) {
            addCriterion("TEMP_NAME between", value1, value2, "tempName");
            return (Criteria) this;
        }

        public Criteria andTempNameNotBetween(String value1, String value2) {
            addCriterion("TEMP_NAME not between", value1, value2, "tempName");
            return (Criteria) this;
        }

        public Criteria andTempContentIsNull() {
            addCriterion("TEMP_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andTempContentIsNotNull() {
            addCriterion("TEMP_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andTempContentEqualTo(String value) {
            addCriterion("TEMP_CONTENT =", value, "tempContent");
            return (Criteria) this;
        }

        public Criteria andTempContentNotEqualTo(String value) {
            addCriterion("TEMP_CONTENT <>", value, "tempContent");
            return (Criteria) this;
        }

        public Criteria andTempContentGreaterThan(String value) {
            addCriterion("TEMP_CONTENT >", value, "tempContent");
            return (Criteria) this;
        }

        public Criteria andTempContentGreaterThanOrEqualTo(String value) {
            addCriterion("TEMP_CONTENT >=", value, "tempContent");
            return (Criteria) this;
        }

        public Criteria andTempContentLessThan(String value) {
            addCriterion("TEMP_CONTENT <", value, "tempContent");
            return (Criteria) this;
        }

        public Criteria andTempContentLessThanOrEqualTo(String value) {
            addCriterion("TEMP_CONTENT <=", value, "tempContent");
            return (Criteria) this;
        }

        public Criteria andTempContentLike(String value) {
            addCriterion("TEMP_CONTENT like", value, "tempContent");
            return (Criteria) this;
        }

        public Criteria andTempContentNotLike(String value) {
            addCriterion("TEMP_CONTENT not like", value, "tempContent");
            return (Criteria) this;
        }

        public Criteria andTempContentIn(List<String> values) {
            addCriterion("TEMP_CONTENT in", values, "tempContent");
            return (Criteria) this;
        }

        public Criteria andTempContentNotIn(List<String> values) {
            addCriterion("TEMP_CONTENT not in", values, "tempContent");
            return (Criteria) this;
        }

        public Criteria andTempContentBetween(String value1, String value2) {
            addCriterion("TEMP_CONTENT between", value1, value2, "tempContent");
            return (Criteria) this;
        }

        public Criteria andTempContentNotBetween(String value1, String value2) {
            addCriterion("TEMP_CONTENT not between", value1, value2, "tempContent");
            return (Criteria) this;
        }

        public Criteria andTempIdIsNull() {
            addCriterion("TEMP_ID is null");
            return (Criteria) this;
        }

        public Criteria andTempIdIsNotNull() {
            addCriterion("TEMP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTempIdEqualTo(BigDecimal value) {
            addCriterion("TEMP_ID =", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdNotEqualTo(BigDecimal value) {
            addCriterion("TEMP_ID <>", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdGreaterThan(BigDecimal value) {
            addCriterion("TEMP_ID >", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TEMP_ID >=", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdLessThan(BigDecimal value) {
            addCriterion("TEMP_ID <", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TEMP_ID <=", value, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdIn(List<BigDecimal> values) {
            addCriterion("TEMP_ID in", values, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdNotIn(List<BigDecimal> values) {
            addCriterion("TEMP_ID not in", values, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TEMP_ID between", value1, value2, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TEMP_ID not between", value1, value2, "tempId");
            return (Criteria) this;
        }

        public Criteria andTempFormartIsNull() {
            addCriterion("TEMP_FORMART is null");
            return (Criteria) this;
        }

        public Criteria andTempFormartIsNotNull() {
            addCriterion("TEMP_FORMART is not null");
            return (Criteria) this;
        }

        public Criteria andTempFormartEqualTo(String value) {
            addCriterion("TEMP_FORMART =", value, "tempFormart");
            return (Criteria) this;
        }

        public Criteria andTempFormartNotEqualTo(String value) {
            addCriterion("TEMP_FORMART <>", value, "tempFormart");
            return (Criteria) this;
        }

        public Criteria andTempFormartGreaterThan(String value) {
            addCriterion("TEMP_FORMART >", value, "tempFormart");
            return (Criteria) this;
        }

        public Criteria andTempFormartGreaterThanOrEqualTo(String value) {
            addCriterion("TEMP_FORMART >=", value, "tempFormart");
            return (Criteria) this;
        }

        public Criteria andTempFormartLessThan(String value) {
            addCriterion("TEMP_FORMART <", value, "tempFormart");
            return (Criteria) this;
        }

        public Criteria andTempFormartLessThanOrEqualTo(String value) {
            addCriterion("TEMP_FORMART <=", value, "tempFormart");
            return (Criteria) this;
        }

        public Criteria andTempFormartLike(String value) {
            addCriterion("TEMP_FORMART like", value, "tempFormart");
            return (Criteria) this;
        }

        public Criteria andTempFormartNotLike(String value) {
            addCriterion("TEMP_FORMART not like", value, "tempFormart");
            return (Criteria) this;
        }

        public Criteria andTempFormartIn(List<String> values) {
            addCriterion("TEMP_FORMART in", values, "tempFormart");
            return (Criteria) this;
        }

        public Criteria andTempFormartNotIn(List<String> values) {
            addCriterion("TEMP_FORMART not in", values, "tempFormart");
            return (Criteria) this;
        }

        public Criteria andTempFormartBetween(String value1, String value2) {
            addCriterion("TEMP_FORMART between", value1, value2, "tempFormart");
            return (Criteria) this;
        }

        public Criteria andTempFormartNotBetween(String value1, String value2) {
            addCriterion("TEMP_FORMART not between", value1, value2, "tempFormart");
            return (Criteria) this;
        }

        public Criteria andTempTypeIsNull() {
            addCriterion("TEMP_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTempTypeIsNotNull() {
            addCriterion("TEMP_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTempTypeEqualTo(String value) {
            addCriterion("TEMP_TYPE =", value, "tempType");
            return (Criteria) this;
        }

        public Criteria andTempTypeNotEqualTo(String value) {
            addCriterion("TEMP_TYPE <>", value, "tempType");
            return (Criteria) this;
        }

        public Criteria andTempTypeGreaterThan(String value) {
            addCriterion("TEMP_TYPE >", value, "tempType");
            return (Criteria) this;
        }

        public Criteria andTempTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TEMP_TYPE >=", value, "tempType");
            return (Criteria) this;
        }

        public Criteria andTempTypeLessThan(String value) {
            addCriterion("TEMP_TYPE <", value, "tempType");
            return (Criteria) this;
        }

        public Criteria andTempTypeLessThanOrEqualTo(String value) {
            addCriterion("TEMP_TYPE <=", value, "tempType");
            return (Criteria) this;
        }

        public Criteria andTempTypeLike(String value) {
            addCriterion("TEMP_TYPE like", value, "tempType");
            return (Criteria) this;
        }

        public Criteria andTempTypeNotLike(String value) {
            addCriterion("TEMP_TYPE not like", value, "tempType");
            return (Criteria) this;
        }

        public Criteria andTempTypeIn(List<String> values) {
            addCriterion("TEMP_TYPE in", values, "tempType");
            return (Criteria) this;
        }

        public Criteria andTempTypeNotIn(List<String> values) {
            addCriterion("TEMP_TYPE not in", values, "tempType");
            return (Criteria) this;
        }

        public Criteria andTempTypeBetween(String value1, String value2) {
            addCriterion("TEMP_TYPE between", value1, value2, "tempType");
            return (Criteria) this;
        }

        public Criteria andTempTypeNotBetween(String value1, String value2) {
            addCriterion("TEMP_TYPE not between", value1, value2, "tempType");
            return (Criteria) this;
        }

        public Criteria andIsEnabledIsNull() {
            addCriterion("IS_ENABLED is null");
            return (Criteria) this;
        }

        public Criteria andIsEnabledIsNotNull() {
            addCriterion("IS_ENABLED is not null");
            return (Criteria) this;
        }

        public Criteria andIsEnabledEqualTo(String value) {
            addCriterion("IS_ENABLED =", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledNotEqualTo(String value) {
            addCriterion("IS_ENABLED <>", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledGreaterThan(String value) {
            addCriterion("IS_ENABLED >", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledGreaterThanOrEqualTo(String value) {
            addCriterion("IS_ENABLED >=", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledLessThan(String value) {
            addCriterion("IS_ENABLED <", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledLessThanOrEqualTo(String value) {
            addCriterion("IS_ENABLED <=", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledLike(String value) {
            addCriterion("IS_ENABLED like", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledNotLike(String value) {
            addCriterion("IS_ENABLED not like", value, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledIn(List<String> values) {
            addCriterion("IS_ENABLED in", values, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledNotIn(List<String> values) {
            addCriterion("IS_ENABLED not in", values, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledBetween(String value1, String value2) {
            addCriterion("IS_ENABLED between", value1, value2, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andIsEnabledNotBetween(String value1, String value2) {
            addCriterion("IS_ENABLED not between", value1, value2, "isEnabled");
            return (Criteria) this;
        }

        public Criteria andTempRemarkIsNull() {
            addCriterion("TEMP_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andTempRemarkIsNotNull() {
            addCriterion("TEMP_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andTempRemarkEqualTo(String value) {
            addCriterion("TEMP_REMARK =", value, "tempRemark");
            return (Criteria) this;
        }

        public Criteria andTempRemarkNotEqualTo(String value) {
            addCriterion("TEMP_REMARK <>", value, "tempRemark");
            return (Criteria) this;
        }

        public Criteria andTempRemarkGreaterThan(String value) {
            addCriterion("TEMP_REMARK >", value, "tempRemark");
            return (Criteria) this;
        }

        public Criteria andTempRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("TEMP_REMARK >=", value, "tempRemark");
            return (Criteria) this;
        }

        public Criteria andTempRemarkLessThan(String value) {
            addCriterion("TEMP_REMARK <", value, "tempRemark");
            return (Criteria) this;
        }

        public Criteria andTempRemarkLessThanOrEqualTo(String value) {
            addCriterion("TEMP_REMARK <=", value, "tempRemark");
            return (Criteria) this;
        }

        public Criteria andTempRemarkLike(String value) {
            addCriterion("TEMP_REMARK like", value, "tempRemark");
            return (Criteria) this;
        }

        public Criteria andTempRemarkNotLike(String value) {
            addCriterion("TEMP_REMARK not like", value, "tempRemark");
            return (Criteria) this;
        }

        public Criteria andTempRemarkIn(List<String> values) {
            addCriterion("TEMP_REMARK in", values, "tempRemark");
            return (Criteria) this;
        }

        public Criteria andTempRemarkNotIn(List<String> values) {
            addCriterion("TEMP_REMARK not in", values, "tempRemark");
            return (Criteria) this;
        }

        public Criteria andTempRemarkBetween(String value1, String value2) {
            addCriterion("TEMP_REMARK between", value1, value2, "tempRemark");
            return (Criteria) this;
        }

        public Criteria andTempRemarkNotBetween(String value1, String value2) {
            addCriterion("TEMP_REMARK not between", value1, value2, "tempRemark");
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