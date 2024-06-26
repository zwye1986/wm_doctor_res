package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class EduTeachingnoticeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EduTeachingnoticeExample() {
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

        public Criteria andInfoFlowIsNull() {
            addCriterion("INFO_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andInfoFlowIsNotNull() {
            addCriterion("INFO_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andInfoFlowEqualTo(String value) {
            addCriterion("INFO_FLOW =", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowNotEqualTo(String value) {
            addCriterion("INFO_FLOW <>", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowGreaterThan(String value) {
            addCriterion("INFO_FLOW >", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowGreaterThanOrEqualTo(String value) {
            addCriterion("INFO_FLOW >=", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowLessThan(String value) {
            addCriterion("INFO_FLOW <", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowLessThanOrEqualTo(String value) {
            addCriterion("INFO_FLOW <=", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowLike(String value) {
            addCriterion("INFO_FLOW like", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowNotLike(String value) {
            addCriterion("INFO_FLOW not like", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowIn(List<String> values) {
            addCriterion("INFO_FLOW in", values, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowNotIn(List<String> values) {
            addCriterion("INFO_FLOW not in", values, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowBetween(String value1, String value2) {
            addCriterion("INFO_FLOW between", value1, value2, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowNotBetween(String value1, String value2) {
            addCriterion("INFO_FLOW not between", value1, value2, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoTitleIsNull() {
            addCriterion("INFO_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andInfoTitleIsNotNull() {
            addCriterion("INFO_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andInfoTitleEqualTo(String value) {
            addCriterion("INFO_TITLE =", value, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleNotEqualTo(String value) {
            addCriterion("INFO_TITLE <>", value, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleGreaterThan(String value) {
            addCriterion("INFO_TITLE >", value, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleGreaterThanOrEqualTo(String value) {
            addCriterion("INFO_TITLE >=", value, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleLessThan(String value) {
            addCriterion("INFO_TITLE <", value, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleLessThanOrEqualTo(String value) {
            addCriterion("INFO_TITLE <=", value, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleLike(String value) {
            addCriterion("INFO_TITLE like", value, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleNotLike(String value) {
            addCriterion("INFO_TITLE not like", value, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleIn(List<String> values) {
            addCriterion("INFO_TITLE in", values, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleNotIn(List<String> values) {
            addCriterion("INFO_TITLE not in", values, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleBetween(String value1, String value2) {
            addCriterion("INFO_TITLE between", value1, value2, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleNotBetween(String value1, String value2) {
            addCriterion("INFO_TITLE not between", value1, value2, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTargetNameIsNull() {
            addCriterion("INFO_TARGET_NAME is null");
            return (Criteria) this;
        }

        public Criteria andInfoTargetNameIsNotNull() {
            addCriterion("INFO_TARGET_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andInfoTargetNameEqualTo(String value) {
            addCriterion("INFO_TARGET_NAME =", value, "infoTargetName");
            return (Criteria) this;
        }

        public Criteria andInfoTargetNameNotEqualTo(String value) {
            addCriterion("INFO_TARGET_NAME <>", value, "infoTargetName");
            return (Criteria) this;
        }

        public Criteria andInfoTargetNameGreaterThan(String value) {
            addCriterion("INFO_TARGET_NAME >", value, "infoTargetName");
            return (Criteria) this;
        }

        public Criteria andInfoTargetNameGreaterThanOrEqualTo(String value) {
            addCriterion("INFO_TARGET_NAME >=", value, "infoTargetName");
            return (Criteria) this;
        }

        public Criteria andInfoTargetNameLessThan(String value) {
            addCriterion("INFO_TARGET_NAME <", value, "infoTargetName");
            return (Criteria) this;
        }

        public Criteria andInfoTargetNameLessThanOrEqualTo(String value) {
            addCriterion("INFO_TARGET_NAME <=", value, "infoTargetName");
            return (Criteria) this;
        }

        public Criteria andInfoTargetNameLike(String value) {
            addCriterion("INFO_TARGET_NAME like", value, "infoTargetName");
            return (Criteria) this;
        }

        public Criteria andInfoTargetNameNotLike(String value) {
            addCriterion("INFO_TARGET_NAME not like", value, "infoTargetName");
            return (Criteria) this;
        }

        public Criteria andInfoTargetNameIn(List<String> values) {
            addCriterion("INFO_TARGET_NAME in", values, "infoTargetName");
            return (Criteria) this;
        }

        public Criteria andInfoTargetNameNotIn(List<String> values) {
            addCriterion("INFO_TARGET_NAME not in", values, "infoTargetName");
            return (Criteria) this;
        }

        public Criteria andInfoTargetNameBetween(String value1, String value2) {
            addCriterion("INFO_TARGET_NAME between", value1, value2, "infoTargetName");
            return (Criteria) this;
        }

        public Criteria andInfoTargetNameNotBetween(String value1, String value2) {
            addCriterion("INFO_TARGET_NAME not between", value1, value2, "infoTargetName");
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

        public Criteria andInfoTargetFlowIsNull() {
            addCriterion("INFO_TARGET_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andInfoTargetFlowIsNotNull() {
            addCriterion("INFO_TARGET_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andInfoTargetFlowEqualTo(String value) {
            addCriterion("INFO_TARGET_FLOW =", value, "infoTargetFlow");
            return (Criteria) this;
        }

        public Criteria andInfoTargetFlowNotEqualTo(String value) {
            addCriterion("INFO_TARGET_FLOW <>", value, "infoTargetFlow");
            return (Criteria) this;
        }

        public Criteria andInfoTargetFlowGreaterThan(String value) {
            addCriterion("INFO_TARGET_FLOW >", value, "infoTargetFlow");
            return (Criteria) this;
        }

        public Criteria andInfoTargetFlowGreaterThanOrEqualTo(String value) {
            addCriterion("INFO_TARGET_FLOW >=", value, "infoTargetFlow");
            return (Criteria) this;
        }

        public Criteria andInfoTargetFlowLessThan(String value) {
            addCriterion("INFO_TARGET_FLOW <", value, "infoTargetFlow");
            return (Criteria) this;
        }

        public Criteria andInfoTargetFlowLessThanOrEqualTo(String value) {
            addCriterion("INFO_TARGET_FLOW <=", value, "infoTargetFlow");
            return (Criteria) this;
        }

        public Criteria andInfoTargetFlowLike(String value) {
            addCriterion("INFO_TARGET_FLOW like", value, "infoTargetFlow");
            return (Criteria) this;
        }

        public Criteria andInfoTargetFlowNotLike(String value) {
            addCriterion("INFO_TARGET_FLOW not like", value, "infoTargetFlow");
            return (Criteria) this;
        }

        public Criteria andInfoTargetFlowIn(List<String> values) {
            addCriterion("INFO_TARGET_FLOW in", values, "infoTargetFlow");
            return (Criteria) this;
        }

        public Criteria andInfoTargetFlowNotIn(List<String> values) {
            addCriterion("INFO_TARGET_FLOW not in", values, "infoTargetFlow");
            return (Criteria) this;
        }

        public Criteria andInfoTargetFlowBetween(String value1, String value2) {
            addCriterion("INFO_TARGET_FLOW between", value1, value2, "infoTargetFlow");
            return (Criteria) this;
        }

        public Criteria andInfoTargetFlowNotBetween(String value1, String value2) {
            addCriterion("INFO_TARGET_FLOW not between", value1, value2, "infoTargetFlow");
            return (Criteria) this;
        }

        public Criteria andInfoTypeIdIsNull() {
            addCriterion("INFO_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andInfoTypeIdIsNotNull() {
            addCriterion("INFO_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInfoTypeIdEqualTo(String value) {
            addCriterion("INFO_TYPE_ID =", value, "infoTypeId");
            return (Criteria) this;
        }

        public Criteria andInfoTypeIdNotEqualTo(String value) {
            addCriterion("INFO_TYPE_ID <>", value, "infoTypeId");
            return (Criteria) this;
        }

        public Criteria andInfoTypeIdGreaterThan(String value) {
            addCriterion("INFO_TYPE_ID >", value, "infoTypeId");
            return (Criteria) this;
        }

        public Criteria andInfoTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("INFO_TYPE_ID >=", value, "infoTypeId");
            return (Criteria) this;
        }

        public Criteria andInfoTypeIdLessThan(String value) {
            addCriterion("INFO_TYPE_ID <", value, "infoTypeId");
            return (Criteria) this;
        }

        public Criteria andInfoTypeIdLessThanOrEqualTo(String value) {
            addCriterion("INFO_TYPE_ID <=", value, "infoTypeId");
            return (Criteria) this;
        }

        public Criteria andInfoTypeIdLike(String value) {
            addCriterion("INFO_TYPE_ID like", value, "infoTypeId");
            return (Criteria) this;
        }

        public Criteria andInfoTypeIdNotLike(String value) {
            addCriterion("INFO_TYPE_ID not like", value, "infoTypeId");
            return (Criteria) this;
        }

        public Criteria andInfoTypeIdIn(List<String> values) {
            addCriterion("INFO_TYPE_ID in", values, "infoTypeId");
            return (Criteria) this;
        }

        public Criteria andInfoTypeIdNotIn(List<String> values) {
            addCriterion("INFO_TYPE_ID not in", values, "infoTypeId");
            return (Criteria) this;
        }

        public Criteria andInfoTypeIdBetween(String value1, String value2) {
            addCriterion("INFO_TYPE_ID between", value1, value2, "infoTypeId");
            return (Criteria) this;
        }

        public Criteria andInfoTypeIdNotBetween(String value1, String value2) {
            addCriterion("INFO_TYPE_ID not between", value1, value2, "infoTypeId");
            return (Criteria) this;
        }

        public Criteria andInfoTypeNameIsNull() {
            addCriterion("INFO_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andInfoTypeNameIsNotNull() {
            addCriterion("INFO_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andInfoTypeNameEqualTo(String value) {
            addCriterion("INFO_TYPE_NAME =", value, "infoTypeName");
            return (Criteria) this;
        }

        public Criteria andInfoTypeNameNotEqualTo(String value) {
            addCriterion("INFO_TYPE_NAME <>", value, "infoTypeName");
            return (Criteria) this;
        }

        public Criteria andInfoTypeNameGreaterThan(String value) {
            addCriterion("INFO_TYPE_NAME >", value, "infoTypeName");
            return (Criteria) this;
        }

        public Criteria andInfoTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("INFO_TYPE_NAME >=", value, "infoTypeName");
            return (Criteria) this;
        }

        public Criteria andInfoTypeNameLessThan(String value) {
            addCriterion("INFO_TYPE_NAME <", value, "infoTypeName");
            return (Criteria) this;
        }

        public Criteria andInfoTypeNameLessThanOrEqualTo(String value) {
            addCriterion("INFO_TYPE_NAME <=", value, "infoTypeName");
            return (Criteria) this;
        }

        public Criteria andInfoTypeNameLike(String value) {
            addCriterion("INFO_TYPE_NAME like", value, "infoTypeName");
            return (Criteria) this;
        }

        public Criteria andInfoTypeNameNotLike(String value) {
            addCriterion("INFO_TYPE_NAME not like", value, "infoTypeName");
            return (Criteria) this;
        }

        public Criteria andInfoTypeNameIn(List<String> values) {
            addCriterion("INFO_TYPE_NAME in", values, "infoTypeName");
            return (Criteria) this;
        }

        public Criteria andInfoTypeNameNotIn(List<String> values) {
            addCriterion("INFO_TYPE_NAME not in", values, "infoTypeName");
            return (Criteria) this;
        }

        public Criteria andInfoTypeNameBetween(String value1, String value2) {
            addCriterion("INFO_TYPE_NAME between", value1, value2, "infoTypeName");
            return (Criteria) this;
        }

        public Criteria andInfoTypeNameNotBetween(String value1, String value2) {
            addCriterion("INFO_TYPE_NAME not between", value1, value2, "infoTypeName");
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