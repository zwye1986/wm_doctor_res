package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class OscaFromExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OscaFromExample() {
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

        public Criteria andFromFlowIsNull() {
            addCriterion("FROM_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andFromFlowIsNotNull() {
            addCriterion("FROM_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andFromFlowEqualTo(String value) {
            addCriterion("FROM_FLOW =", value, "fromFlow");
            return (Criteria) this;
        }

        public Criteria andFromFlowNotEqualTo(String value) {
            addCriterion("FROM_FLOW <>", value, "fromFlow");
            return (Criteria) this;
        }

        public Criteria andFromFlowGreaterThan(String value) {
            addCriterion("FROM_FLOW >", value, "fromFlow");
            return (Criteria) this;
        }

        public Criteria andFromFlowGreaterThanOrEqualTo(String value) {
            addCriterion("FROM_FLOW >=", value, "fromFlow");
            return (Criteria) this;
        }

        public Criteria andFromFlowLessThan(String value) {
            addCriterion("FROM_FLOW <", value, "fromFlow");
            return (Criteria) this;
        }

        public Criteria andFromFlowLessThanOrEqualTo(String value) {
            addCriterion("FROM_FLOW <=", value, "fromFlow");
            return (Criteria) this;
        }

        public Criteria andFromFlowLike(String value) {
            addCriterion("FROM_FLOW like", value, "fromFlow");
            return (Criteria) this;
        }

        public Criteria andFromFlowNotLike(String value) {
            addCriterion("FROM_FLOW not like", value, "fromFlow");
            return (Criteria) this;
        }

        public Criteria andFromFlowIn(List<String> values) {
            addCriterion("FROM_FLOW in", values, "fromFlow");
            return (Criteria) this;
        }

        public Criteria andFromFlowNotIn(List<String> values) {
            addCriterion("FROM_FLOW not in", values, "fromFlow");
            return (Criteria) this;
        }

        public Criteria andFromFlowBetween(String value1, String value2) {
            addCriterion("FROM_FLOW between", value1, value2, "fromFlow");
            return (Criteria) this;
        }

        public Criteria andFromFlowNotBetween(String value1, String value2) {
            addCriterion("FROM_FLOW not between", value1, value2, "fromFlow");
            return (Criteria) this;
        }

        public Criteria andFromNameIsNull() {
            addCriterion("FROM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFromNameIsNotNull() {
            addCriterion("FROM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFromNameEqualTo(String value) {
            addCriterion("FROM_NAME =", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameNotEqualTo(String value) {
            addCriterion("FROM_NAME <>", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameGreaterThan(String value) {
            addCriterion("FROM_NAME >", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameGreaterThanOrEqualTo(String value) {
            addCriterion("FROM_NAME >=", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameLessThan(String value) {
            addCriterion("FROM_NAME <", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameLessThanOrEqualTo(String value) {
            addCriterion("FROM_NAME <=", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameLike(String value) {
            addCriterion("FROM_NAME like", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameNotLike(String value) {
            addCriterion("FROM_NAME not like", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameIn(List<String> values) {
            addCriterion("FROM_NAME in", values, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameNotIn(List<String> values) {
            addCriterion("FROM_NAME not in", values, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameBetween(String value1, String value2) {
            addCriterion("FROM_NAME between", value1, value2, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameNotBetween(String value1, String value2) {
            addCriterion("FROM_NAME not between", value1, value2, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdIsNull() {
            addCriterion("FROM_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdIsNotNull() {
            addCriterion("FROM_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdEqualTo(String value) {
            addCriterion("FROM_TYPE_ID =", value, "fromTypeId");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdNotEqualTo(String value) {
            addCriterion("FROM_TYPE_ID <>", value, "fromTypeId");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdGreaterThan(String value) {
            addCriterion("FROM_TYPE_ID >", value, "fromTypeId");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("FROM_TYPE_ID >=", value, "fromTypeId");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdLessThan(String value) {
            addCriterion("FROM_TYPE_ID <", value, "fromTypeId");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdLessThanOrEqualTo(String value) {
            addCriterion("FROM_TYPE_ID <=", value, "fromTypeId");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdLike(String value) {
            addCriterion("FROM_TYPE_ID like", value, "fromTypeId");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdNotLike(String value) {
            addCriterion("FROM_TYPE_ID not like", value, "fromTypeId");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdIn(List<String> values) {
            addCriterion("FROM_TYPE_ID in", values, "fromTypeId");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdNotIn(List<String> values) {
            addCriterion("FROM_TYPE_ID not in", values, "fromTypeId");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdBetween(String value1, String value2) {
            addCriterion("FROM_TYPE_ID between", value1, value2, "fromTypeId");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdNotBetween(String value1, String value2) {
            addCriterion("FROM_TYPE_ID not between", value1, value2, "fromTypeId");
            return (Criteria) this;
        }

        public Criteria andFromTypeNameIsNull() {
            addCriterion("FROM_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFromTypeNameIsNotNull() {
            addCriterion("FROM_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFromTypeNameEqualTo(String value) {
            addCriterion("FROM_TYPE_NAME =", value, "fromTypeName");
            return (Criteria) this;
        }

        public Criteria andFromTypeNameNotEqualTo(String value) {
            addCriterion("FROM_TYPE_NAME <>", value, "fromTypeName");
            return (Criteria) this;
        }

        public Criteria andFromTypeNameGreaterThan(String value) {
            addCriterion("FROM_TYPE_NAME >", value, "fromTypeName");
            return (Criteria) this;
        }

        public Criteria andFromTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("FROM_TYPE_NAME >=", value, "fromTypeName");
            return (Criteria) this;
        }

        public Criteria andFromTypeNameLessThan(String value) {
            addCriterion("FROM_TYPE_NAME <", value, "fromTypeName");
            return (Criteria) this;
        }

        public Criteria andFromTypeNameLessThanOrEqualTo(String value) {
            addCriterion("FROM_TYPE_NAME <=", value, "fromTypeName");
            return (Criteria) this;
        }

        public Criteria andFromTypeNameLike(String value) {
            addCriterion("FROM_TYPE_NAME like", value, "fromTypeName");
            return (Criteria) this;
        }

        public Criteria andFromTypeNameNotLike(String value) {
            addCriterion("FROM_TYPE_NAME not like", value, "fromTypeName");
            return (Criteria) this;
        }

        public Criteria andFromTypeNameIn(List<String> values) {
            addCriterion("FROM_TYPE_NAME in", values, "fromTypeName");
            return (Criteria) this;
        }

        public Criteria andFromTypeNameNotIn(List<String> values) {
            addCriterion("FROM_TYPE_NAME not in", values, "fromTypeName");
            return (Criteria) this;
        }

        public Criteria andFromTypeNameBetween(String value1, String value2) {
            addCriterion("FROM_TYPE_NAME between", value1, value2, "fromTypeName");
            return (Criteria) this;
        }

        public Criteria andFromTypeNameNotBetween(String value1, String value2) {
            addCriterion("FROM_TYPE_NAME not between", value1, value2, "fromTypeName");
            return (Criteria) this;
        }

        public Criteria andFromUrlIsNull() {
            addCriterion("FROM_URL is null");
            return (Criteria) this;
        }

        public Criteria andFromUrlIsNotNull() {
            addCriterion("FROM_URL is not null");
            return (Criteria) this;
        }

        public Criteria andFromUrlEqualTo(String value) {
            addCriterion("FROM_URL =", value, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlNotEqualTo(String value) {
            addCriterion("FROM_URL <>", value, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlGreaterThan(String value) {
            addCriterion("FROM_URL >", value, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlGreaterThanOrEqualTo(String value) {
            addCriterion("FROM_URL >=", value, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlLessThan(String value) {
            addCriterion("FROM_URL <", value, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlLessThanOrEqualTo(String value) {
            addCriterion("FROM_URL <=", value, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlLike(String value) {
            addCriterion("FROM_URL like", value, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlNotLike(String value) {
            addCriterion("FROM_URL not like", value, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlIn(List<String> values) {
            addCriterion("FROM_URL in", values, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlNotIn(List<String> values) {
            addCriterion("FROM_URL not in", values, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlBetween(String value1, String value2) {
            addCriterion("FROM_URL between", value1, value2, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlNotBetween(String value1, String value2) {
            addCriterion("FROM_URL not between", value1, value2, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andIsReleasedIsNull() {
            addCriterion("IS_RELEASED is null");
            return (Criteria) this;
        }

        public Criteria andIsReleasedIsNotNull() {
            addCriterion("IS_RELEASED is not null");
            return (Criteria) this;
        }

        public Criteria andIsReleasedEqualTo(String value) {
            addCriterion("IS_RELEASED =", value, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedNotEqualTo(String value) {
            addCriterion("IS_RELEASED <>", value, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedGreaterThan(String value) {
            addCriterion("IS_RELEASED >", value, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedGreaterThanOrEqualTo(String value) {
            addCriterion("IS_RELEASED >=", value, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedLessThan(String value) {
            addCriterion("IS_RELEASED <", value, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedLessThanOrEqualTo(String value) {
            addCriterion("IS_RELEASED <=", value, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedLike(String value) {
            addCriterion("IS_RELEASED like", value, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedNotLike(String value) {
            addCriterion("IS_RELEASED not like", value, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedIn(List<String> values) {
            addCriterion("IS_RELEASED in", values, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedNotIn(List<String> values) {
            addCriterion("IS_RELEASED not in", values, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedBetween(String value1, String value2) {
            addCriterion("IS_RELEASED between", value1, value2, "isReleased");
            return (Criteria) this;
        }

        public Criteria andIsReleasedNotBetween(String value1, String value2) {
            addCriterion("IS_RELEASED not between", value1, value2, "isReleased");
            return (Criteria) this;
        }

        public Criteria andFromScoreIsNull() {
            addCriterion("FROM_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andFromScoreIsNotNull() {
            addCriterion("FROM_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andFromScoreEqualTo(String value) {
            addCriterion("FROM_SCORE =", value, "fromScore");
            return (Criteria) this;
        }

        public Criteria andFromScoreNotEqualTo(String value) {
            addCriterion("FROM_SCORE <>", value, "fromScore");
            return (Criteria) this;
        }

        public Criteria andFromScoreGreaterThan(String value) {
            addCriterion("FROM_SCORE >", value, "fromScore");
            return (Criteria) this;
        }

        public Criteria andFromScoreGreaterThanOrEqualTo(String value) {
            addCriterion("FROM_SCORE >=", value, "fromScore");
            return (Criteria) this;
        }

        public Criteria andFromScoreLessThan(String value) {
            addCriterion("FROM_SCORE <", value, "fromScore");
            return (Criteria) this;
        }

        public Criteria andFromScoreLessThanOrEqualTo(String value) {
            addCriterion("FROM_SCORE <=", value, "fromScore");
            return (Criteria) this;
        }

        public Criteria andFromScoreLike(String value) {
            addCriterion("FROM_SCORE like", value, "fromScore");
            return (Criteria) this;
        }

        public Criteria andFromScoreNotLike(String value) {
            addCriterion("FROM_SCORE not like", value, "fromScore");
            return (Criteria) this;
        }

        public Criteria andFromScoreIn(List<String> values) {
            addCriterion("FROM_SCORE in", values, "fromScore");
            return (Criteria) this;
        }

        public Criteria andFromScoreNotIn(List<String> values) {
            addCriterion("FROM_SCORE not in", values, "fromScore");
            return (Criteria) this;
        }

        public Criteria andFromScoreBetween(String value1, String value2) {
            addCriterion("FROM_SCORE between", value1, value2, "fromScore");
            return (Criteria) this;
        }

        public Criteria andFromScoreNotBetween(String value1, String value2) {
            addCriterion("FROM_SCORE not between", value1, value2, "fromScore");
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

        public Criteria andOrgFlowIsNull() {
            addCriterion("ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andOrgFlowIsNotNull() {
            addCriterion("ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andOrgFlowEqualTo(String value) {
            addCriterion("ORG_FLOW =", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotEqualTo(String value) {
            addCriterion("ORG_FLOW <>", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGreaterThan(String value) {
            addCriterion("ORG_FLOW >", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_FLOW >=", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowLessThan(String value) {
            addCriterion("ORG_FLOW <", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("ORG_FLOW <=", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowLike(String value) {
            addCriterion("ORG_FLOW like", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotLike(String value) {
            addCriterion("ORG_FLOW not like", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowIn(List<String> values) {
            addCriterion("ORG_FLOW in", values, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotIn(List<String> values) {
            addCriterion("ORG_FLOW not in", values, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowBetween(String value1, String value2) {
            addCriterion("ORG_FLOW between", value1, value2, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotBetween(String value1, String value2) {
            addCriterion("ORG_FLOW not between", value1, value2, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNull() {
            addCriterion("ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNotNull() {
            addCriterion("ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEqualTo(String value) {
            addCriterion("ORG_NAME =", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotEqualTo(String value) {
            addCriterion("ORG_NAME <>", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThan(String value) {
            addCriterion("ORG_NAME >", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_NAME >=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThan(String value) {
            addCriterion("ORG_NAME <", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_NAME <=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLike(String value) {
            addCriterion("ORG_NAME like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotLike(String value) {
            addCriterion("ORG_NAME not like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIn(List<String> values) {
            addCriterion("ORG_NAME in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotIn(List<String> values) {
            addCriterion("ORG_NAME not in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameBetween(String value1, String value2) {
            addCriterion("ORG_NAME between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotBetween(String value1, String value2) {
            addCriterion("ORG_NAME not between", value1, value2, "orgName");
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