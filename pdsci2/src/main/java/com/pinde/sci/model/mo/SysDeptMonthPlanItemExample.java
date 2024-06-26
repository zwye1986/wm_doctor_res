package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class SysDeptMonthPlanItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysDeptMonthPlanItemExample() {
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

        public Criteria andPlanFlowIsNull() {
            addCriterion("PLAN_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andPlanFlowIsNotNull() {
            addCriterion("PLAN_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andPlanFlowEqualTo(String value) {
            addCriterion("PLAN_FLOW =", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowNotEqualTo(String value) {
            addCriterion("PLAN_FLOW <>", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowGreaterThan(String value) {
            addCriterion("PLAN_FLOW >", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_FLOW >=", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowLessThan(String value) {
            addCriterion("PLAN_FLOW <", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowLessThanOrEqualTo(String value) {
            addCriterion("PLAN_FLOW <=", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowLike(String value) {
            addCriterion("PLAN_FLOW like", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowNotLike(String value) {
            addCriterion("PLAN_FLOW not like", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowIn(List<String> values) {
            addCriterion("PLAN_FLOW in", values, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowNotIn(List<String> values) {
            addCriterion("PLAN_FLOW not in", values, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowBetween(String value1, String value2) {
            addCriterion("PLAN_FLOW between", value1, value2, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowNotBetween(String value1, String value2) {
            addCriterion("PLAN_FLOW not between", value1, value2, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanTimeIsNull() {
            addCriterion("PLAN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPlanTimeIsNotNull() {
            addCriterion("PLAN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPlanTimeEqualTo(String value) {
            addCriterion("PLAN_TIME =", value, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeNotEqualTo(String value) {
            addCriterion("PLAN_TIME <>", value, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeGreaterThan(String value) {
            addCriterion("PLAN_TIME >", value, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_TIME >=", value, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeLessThan(String value) {
            addCriterion("PLAN_TIME <", value, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeLessThanOrEqualTo(String value) {
            addCriterion("PLAN_TIME <=", value, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeLike(String value) {
            addCriterion("PLAN_TIME like", value, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeNotLike(String value) {
            addCriterion("PLAN_TIME not like", value, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeIn(List<String> values) {
            addCriterion("PLAN_TIME in", values, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeNotIn(List<String> values) {
            addCriterion("PLAN_TIME not in", values, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeBetween(String value1, String value2) {
            addCriterion("PLAN_TIME between", value1, value2, "planTime");
            return (Criteria) this;
        }

        public Criteria andPlanTimeNotBetween(String value1, String value2) {
            addCriterion("PLAN_TIME not between", value1, value2, "planTime");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("ADDRESS =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("ADDRESS <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("ADDRESS >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ADDRESS >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("ADDRESS <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("ADDRESS <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("ADDRESS like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("ADDRESS not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("ADDRESS in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("ADDRESS not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("ADDRESS between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("ADDRESS not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("CONTENT =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("CONTENT <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("CONTENT >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("CONTENT >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("CONTENT <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("CONTENT <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("CONTENT like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("CONTENT not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("CONTENT in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("CONTENT not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("CONTENT between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("CONTENT not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("TITLE is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("TITLE =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("TITLE <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("TITLE >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("TITLE <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("TITLE <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("TITLE like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("TITLE not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("TITLE in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("TITLE not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("TITLE between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("TITLE not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andItemTypeIdIsNull() {
            addCriterion("ITEM_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andItemTypeIdIsNotNull() {
            addCriterion("ITEM_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andItemTypeIdEqualTo(String value) {
            addCriterion("ITEM_TYPE_ID =", value, "itemTypeId");
            return (Criteria) this;
        }

        public Criteria andItemTypeIdNotEqualTo(String value) {
            addCriterion("ITEM_TYPE_ID <>", value, "itemTypeId");
            return (Criteria) this;
        }

        public Criteria andItemTypeIdGreaterThan(String value) {
            addCriterion("ITEM_TYPE_ID >", value, "itemTypeId");
            return (Criteria) this;
        }

        public Criteria andItemTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_TYPE_ID >=", value, "itemTypeId");
            return (Criteria) this;
        }

        public Criteria andItemTypeIdLessThan(String value) {
            addCriterion("ITEM_TYPE_ID <", value, "itemTypeId");
            return (Criteria) this;
        }

        public Criteria andItemTypeIdLessThanOrEqualTo(String value) {
            addCriterion("ITEM_TYPE_ID <=", value, "itemTypeId");
            return (Criteria) this;
        }

        public Criteria andItemTypeIdLike(String value) {
            addCriterion("ITEM_TYPE_ID like", value, "itemTypeId");
            return (Criteria) this;
        }

        public Criteria andItemTypeIdNotLike(String value) {
            addCriterion("ITEM_TYPE_ID not like", value, "itemTypeId");
            return (Criteria) this;
        }

        public Criteria andItemTypeIdIn(List<String> values) {
            addCriterion("ITEM_TYPE_ID in", values, "itemTypeId");
            return (Criteria) this;
        }

        public Criteria andItemTypeIdNotIn(List<String> values) {
            addCriterion("ITEM_TYPE_ID not in", values, "itemTypeId");
            return (Criteria) this;
        }

        public Criteria andItemTypeIdBetween(String value1, String value2) {
            addCriterion("ITEM_TYPE_ID between", value1, value2, "itemTypeId");
            return (Criteria) this;
        }

        public Criteria andItemTypeIdNotBetween(String value1, String value2) {
            addCriterion("ITEM_TYPE_ID not between", value1, value2, "itemTypeId");
            return (Criteria) this;
        }

        public Criteria andItemTypeNameIsNull() {
            addCriterion("ITEM_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andItemTypeNameIsNotNull() {
            addCriterion("ITEM_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andItemTypeNameEqualTo(String value) {
            addCriterion("ITEM_TYPE_NAME =", value, "itemTypeName");
            return (Criteria) this;
        }

        public Criteria andItemTypeNameNotEqualTo(String value) {
            addCriterion("ITEM_TYPE_NAME <>", value, "itemTypeName");
            return (Criteria) this;
        }

        public Criteria andItemTypeNameGreaterThan(String value) {
            addCriterion("ITEM_TYPE_NAME >", value, "itemTypeName");
            return (Criteria) this;
        }

        public Criteria andItemTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("ITEM_TYPE_NAME >=", value, "itemTypeName");
            return (Criteria) this;
        }

        public Criteria andItemTypeNameLessThan(String value) {
            addCriterion("ITEM_TYPE_NAME <", value, "itemTypeName");
            return (Criteria) this;
        }

        public Criteria andItemTypeNameLessThanOrEqualTo(String value) {
            addCriterion("ITEM_TYPE_NAME <=", value, "itemTypeName");
            return (Criteria) this;
        }

        public Criteria andItemTypeNameLike(String value) {
            addCriterion("ITEM_TYPE_NAME like", value, "itemTypeName");
            return (Criteria) this;
        }

        public Criteria andItemTypeNameNotLike(String value) {
            addCriterion("ITEM_TYPE_NAME not like", value, "itemTypeName");
            return (Criteria) this;
        }

        public Criteria andItemTypeNameIn(List<String> values) {
            addCriterion("ITEM_TYPE_NAME in", values, "itemTypeName");
            return (Criteria) this;
        }

        public Criteria andItemTypeNameNotIn(List<String> values) {
            addCriterion("ITEM_TYPE_NAME not in", values, "itemTypeName");
            return (Criteria) this;
        }

        public Criteria andItemTypeNameBetween(String value1, String value2) {
            addCriterion("ITEM_TYPE_NAME between", value1, value2, "itemTypeName");
            return (Criteria) this;
        }

        public Criteria andItemTypeNameNotBetween(String value1, String value2) {
            addCriterion("ITEM_TYPE_NAME not between", value1, value2, "itemTypeName");
            return (Criteria) this;
        }

        public Criteria andJoinUserFlowIsNull() {
            addCriterion("JOIN_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andJoinUserFlowIsNotNull() {
            addCriterion("JOIN_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andJoinUserFlowEqualTo(String value) {
            addCriterion("JOIN_USER_FLOW =", value, "joinUserFlow");
            return (Criteria) this;
        }

        public Criteria andJoinUserFlowNotEqualTo(String value) {
            addCriterion("JOIN_USER_FLOW <>", value, "joinUserFlow");
            return (Criteria) this;
        }

        public Criteria andJoinUserFlowGreaterThan(String value) {
            addCriterion("JOIN_USER_FLOW >", value, "joinUserFlow");
            return (Criteria) this;
        }

        public Criteria andJoinUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("JOIN_USER_FLOW >=", value, "joinUserFlow");
            return (Criteria) this;
        }

        public Criteria andJoinUserFlowLessThan(String value) {
            addCriterion("JOIN_USER_FLOW <", value, "joinUserFlow");
            return (Criteria) this;
        }

        public Criteria andJoinUserFlowLessThanOrEqualTo(String value) {
            addCriterion("JOIN_USER_FLOW <=", value, "joinUserFlow");
            return (Criteria) this;
        }

        public Criteria andJoinUserFlowLike(String value) {
            addCriterion("JOIN_USER_FLOW like", value, "joinUserFlow");
            return (Criteria) this;
        }

        public Criteria andJoinUserFlowNotLike(String value) {
            addCriterion("JOIN_USER_FLOW not like", value, "joinUserFlow");
            return (Criteria) this;
        }

        public Criteria andJoinUserFlowIn(List<String> values) {
            addCriterion("JOIN_USER_FLOW in", values, "joinUserFlow");
            return (Criteria) this;
        }

        public Criteria andJoinUserFlowNotIn(List<String> values) {
            addCriterion("JOIN_USER_FLOW not in", values, "joinUserFlow");
            return (Criteria) this;
        }

        public Criteria andJoinUserFlowBetween(String value1, String value2) {
            addCriterion("JOIN_USER_FLOW between", value1, value2, "joinUserFlow");
            return (Criteria) this;
        }

        public Criteria andJoinUserFlowNotBetween(String value1, String value2) {
            addCriterion("JOIN_USER_FLOW not between", value1, value2, "joinUserFlow");
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