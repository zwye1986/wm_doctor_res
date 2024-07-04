package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class DegreeInfoMainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DegreeInfoMainExample() {
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

        public Criteria andSidIsNull() {
            addCriterion("SID is null");
            return (Criteria) this;
        }

        public Criteria andSidIsNotNull() {
            addCriterion("SID is not null");
            return (Criteria) this;
        }

        public Criteria andSidEqualTo(String value) {
            addCriterion("SID =", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotEqualTo(String value) {
            addCriterion("SID <>", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThan(String value) {
            addCriterion("SID >", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThanOrEqualTo(String value) {
            addCriterion("SID >=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThan(String value) {
            addCriterion("SID <", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThanOrEqualTo(String value) {
            addCriterion("SID <=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLike(String value) {
            addCriterion("SID like", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotLike(String value) {
            addCriterion("SID not like", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidIn(List<String> values) {
            addCriterion("SID in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotIn(List<String> values) {
            addCriterion("SID not in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidBetween(String value1, String value2) {
            addCriterion("SID between", value1, value2, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotBetween(String value1, String value2) {
            addCriterion("SID not between", value1, value2, "sid");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("USER_NAME =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("USER_NAME <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("USER_NAME >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_NAME >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("USER_NAME <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("USER_NAME <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("USER_NAME like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("USER_NAME not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("USER_NAME in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("USER_NAME not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("USER_NAME between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("USER_NAME not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andSexIdIsNull() {
            addCriterion("SEX_ID is null");
            return (Criteria) this;
        }

        public Criteria andSexIdIsNotNull() {
            addCriterion("SEX_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSexIdEqualTo(String value) {
            addCriterion("SEX_ID =", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotEqualTo(String value) {
            addCriterion("SEX_ID <>", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdGreaterThan(String value) {
            addCriterion("SEX_ID >", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdGreaterThanOrEqualTo(String value) {
            addCriterion("SEX_ID >=", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdLessThan(String value) {
            addCriterion("SEX_ID <", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdLessThanOrEqualTo(String value) {
            addCriterion("SEX_ID <=", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdLike(String value) {
            addCriterion("SEX_ID like", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotLike(String value) {
            addCriterion("SEX_ID not like", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdIn(List<String> values) {
            addCriterion("SEX_ID in", values, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotIn(List<String> values) {
            addCriterion("SEX_ID not in", values, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdBetween(String value1, String value2) {
            addCriterion("SEX_ID between", value1, value2, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotBetween(String value1, String value2) {
            addCriterion("SEX_ID not between", value1, value2, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexNameIsNull() {
            addCriterion("SEX_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSexNameIsNotNull() {
            addCriterion("SEX_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSexNameEqualTo(String value) {
            addCriterion("SEX_NAME =", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotEqualTo(String value) {
            addCriterion("SEX_NAME <>", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameGreaterThan(String value) {
            addCriterion("SEX_NAME >", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameGreaterThanOrEqualTo(String value) {
            addCriterion("SEX_NAME >=", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameLessThan(String value) {
            addCriterion("SEX_NAME <", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameLessThanOrEqualTo(String value) {
            addCriterion("SEX_NAME <=", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameLike(String value) {
            addCriterion("SEX_NAME like", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotLike(String value) {
            addCriterion("SEX_NAME not like", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameIn(List<String> values) {
            addCriterion("SEX_NAME in", values, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotIn(List<String> values) {
            addCriterion("SEX_NAME not in", values, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameBetween(String value1, String value2) {
            addCriterion("SEX_NAME between", value1, value2, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotBetween(String value1, String value2) {
            addCriterion("SEX_NAME not between", value1, value2, "sexName");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdIsNull() {
            addCriterion("TRAIN_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdIsNotNull() {
            addCriterion("TRAIN_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdEqualTo(String value) {
            addCriterion("TRAIN_CATEGORY_ID =", value, "trainCategoryId");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdNotEqualTo(String value) {
            addCriterion("TRAIN_CATEGORY_ID <>", value, "trainCategoryId");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdGreaterThan(String value) {
            addCriterion("TRAIN_CATEGORY_ID >", value, "trainCategoryId");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_CATEGORY_ID >=", value, "trainCategoryId");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdLessThan(String value) {
            addCriterion("TRAIN_CATEGORY_ID <", value, "trainCategoryId");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_CATEGORY_ID <=", value, "trainCategoryId");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdLike(String value) {
            addCriterion("TRAIN_CATEGORY_ID like", value, "trainCategoryId");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdNotLike(String value) {
            addCriterion("TRAIN_CATEGORY_ID not like", value, "trainCategoryId");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdIn(List<String> values) {
            addCriterion("TRAIN_CATEGORY_ID in", values, "trainCategoryId");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdNotIn(List<String> values) {
            addCriterion("TRAIN_CATEGORY_ID not in", values, "trainCategoryId");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdBetween(String value1, String value2) {
            addCriterion("TRAIN_CATEGORY_ID between", value1, value2, "trainCategoryId");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryIdNotBetween(String value1, String value2) {
            addCriterion("TRAIN_CATEGORY_ID not between", value1, value2, "trainCategoryId");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameIsNull() {
            addCriterion("TRAIN_CATEGORY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameIsNotNull() {
            addCriterion("TRAIN_CATEGORY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameEqualTo(String value) {
            addCriterion("TRAIN_CATEGORY_NAME =", value, "trainCategoryName");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameNotEqualTo(String value) {
            addCriterion("TRAIN_CATEGORY_NAME <>", value, "trainCategoryName");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameGreaterThan(String value) {
            addCriterion("TRAIN_CATEGORY_NAME >", value, "trainCategoryName");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_CATEGORY_NAME >=", value, "trainCategoryName");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameLessThan(String value) {
            addCriterion("TRAIN_CATEGORY_NAME <", value, "trainCategoryName");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_CATEGORY_NAME <=", value, "trainCategoryName");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameLike(String value) {
            addCriterion("TRAIN_CATEGORY_NAME like", value, "trainCategoryName");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameNotLike(String value) {
            addCriterion("TRAIN_CATEGORY_NAME not like", value, "trainCategoryName");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameIn(List<String> values) {
            addCriterion("TRAIN_CATEGORY_NAME in", values, "trainCategoryName");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameNotIn(List<String> values) {
            addCriterion("TRAIN_CATEGORY_NAME not in", values, "trainCategoryName");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameBetween(String value1, String value2) {
            addCriterion("TRAIN_CATEGORY_NAME between", value1, value2, "trainCategoryName");
            return (Criteria) this;
        }

        public Criteria andTrainCategoryNameNotBetween(String value1, String value2) {
            addCriterion("TRAIN_CATEGORY_NAME not between", value1, value2, "trainCategoryName");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdIsNull() {
            addCriterion("TRAIN_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdIsNotNull() {
            addCriterion("TRAIN_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdEqualTo(String value) {
            addCriterion("TRAIN_TYPE_ID =", value, "trainTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdNotEqualTo(String value) {
            addCriterion("TRAIN_TYPE_ID <>", value, "trainTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdGreaterThan(String value) {
            addCriterion("TRAIN_TYPE_ID >", value, "trainTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_TYPE_ID >=", value, "trainTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdLessThan(String value) {
            addCriterion("TRAIN_TYPE_ID <", value, "trainTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_TYPE_ID <=", value, "trainTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdLike(String value) {
            addCriterion("TRAIN_TYPE_ID like", value, "trainTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdNotLike(String value) {
            addCriterion("TRAIN_TYPE_ID not like", value, "trainTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdIn(List<String> values) {
            addCriterion("TRAIN_TYPE_ID in", values, "trainTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdNotIn(List<String> values) {
            addCriterion("TRAIN_TYPE_ID not in", values, "trainTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdBetween(String value1, String value2) {
            addCriterion("TRAIN_TYPE_ID between", value1, value2, "trainTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIdNotBetween(String value1, String value2) {
            addCriterion("TRAIN_TYPE_ID not between", value1, value2, "trainTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameIsNull() {
            addCriterion("TRAIN_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameIsNotNull() {
            addCriterion("TRAIN_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameEqualTo(String value) {
            addCriterion("TRAIN_TYPE_NAME =", value, "trainTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameNotEqualTo(String value) {
            addCriterion("TRAIN_TYPE_NAME <>", value, "trainTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameGreaterThan(String value) {
            addCriterion("TRAIN_TYPE_NAME >", value, "trainTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_TYPE_NAME >=", value, "trainTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameLessThan(String value) {
            addCriterion("TRAIN_TYPE_NAME <", value, "trainTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_TYPE_NAME <=", value, "trainTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameLike(String value) {
            addCriterion("TRAIN_TYPE_NAME like", value, "trainTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameNotLike(String value) {
            addCriterion("TRAIN_TYPE_NAME not like", value, "trainTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameIn(List<String> values) {
            addCriterion("TRAIN_TYPE_NAME in", values, "trainTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameNotIn(List<String> values) {
            addCriterion("TRAIN_TYPE_NAME not in", values, "trainTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameBetween(String value1, String value2) {
            addCriterion("TRAIN_TYPE_NAME between", value1, value2, "trainTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNameNotBetween(String value1, String value2) {
            addCriterion("TRAIN_TYPE_NAME not between", value1, value2, "trainTypeName");
            return (Criteria) this;
        }

        public Criteria andNationIdIsNull() {
            addCriterion("NATION_ID is null");
            return (Criteria) this;
        }

        public Criteria andNationIdIsNotNull() {
            addCriterion("NATION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andNationIdEqualTo(String value) {
            addCriterion("NATION_ID =", value, "nationId");
            return (Criteria) this;
        }

        public Criteria andNationIdNotEqualTo(String value) {
            addCriterion("NATION_ID <>", value, "nationId");
            return (Criteria) this;
        }

        public Criteria andNationIdGreaterThan(String value) {
            addCriterion("NATION_ID >", value, "nationId");
            return (Criteria) this;
        }

        public Criteria andNationIdGreaterThanOrEqualTo(String value) {
            addCriterion("NATION_ID >=", value, "nationId");
            return (Criteria) this;
        }

        public Criteria andNationIdLessThan(String value) {
            addCriterion("NATION_ID <", value, "nationId");
            return (Criteria) this;
        }

        public Criteria andNationIdLessThanOrEqualTo(String value) {
            addCriterion("NATION_ID <=", value, "nationId");
            return (Criteria) this;
        }

        public Criteria andNationIdLike(String value) {
            addCriterion("NATION_ID like", value, "nationId");
            return (Criteria) this;
        }

        public Criteria andNationIdNotLike(String value) {
            addCriterion("NATION_ID not like", value, "nationId");
            return (Criteria) this;
        }

        public Criteria andNationIdIn(List<String> values) {
            addCriterion("NATION_ID in", values, "nationId");
            return (Criteria) this;
        }

        public Criteria andNationIdNotIn(List<String> values) {
            addCriterion("NATION_ID not in", values, "nationId");
            return (Criteria) this;
        }

        public Criteria andNationIdBetween(String value1, String value2) {
            addCriterion("NATION_ID between", value1, value2, "nationId");
            return (Criteria) this;
        }

        public Criteria andNationIdNotBetween(String value1, String value2) {
            addCriterion("NATION_ID not between", value1, value2, "nationId");
            return (Criteria) this;
        }

        public Criteria andNationNameIsNull() {
            addCriterion("NATION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andNationNameIsNotNull() {
            addCriterion("NATION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNationNameEqualTo(String value) {
            addCriterion("NATION_NAME =", value, "nationName");
            return (Criteria) this;
        }

        public Criteria andNationNameNotEqualTo(String value) {
            addCriterion("NATION_NAME <>", value, "nationName");
            return (Criteria) this;
        }

        public Criteria andNationNameGreaterThan(String value) {
            addCriterion("NATION_NAME >", value, "nationName");
            return (Criteria) this;
        }

        public Criteria andNationNameGreaterThanOrEqualTo(String value) {
            addCriterion("NATION_NAME >=", value, "nationName");
            return (Criteria) this;
        }

        public Criteria andNationNameLessThan(String value) {
            addCriterion("NATION_NAME <", value, "nationName");
            return (Criteria) this;
        }

        public Criteria andNationNameLessThanOrEqualTo(String value) {
            addCriterion("NATION_NAME <=", value, "nationName");
            return (Criteria) this;
        }

        public Criteria andNationNameLike(String value) {
            addCriterion("NATION_NAME like", value, "nationName");
            return (Criteria) this;
        }

        public Criteria andNationNameNotLike(String value) {
            addCriterion("NATION_NAME not like", value, "nationName");
            return (Criteria) this;
        }

        public Criteria andNationNameIn(List<String> values) {
            addCriterion("NATION_NAME in", values, "nationName");
            return (Criteria) this;
        }

        public Criteria andNationNameNotIn(List<String> values) {
            addCriterion("NATION_NAME not in", values, "nationName");
            return (Criteria) this;
        }

        public Criteria andNationNameBetween(String value1, String value2) {
            addCriterion("NATION_NAME between", value1, value2, "nationName");
            return (Criteria) this;
        }

        public Criteria andNationNameNotBetween(String value1, String value2) {
            addCriterion("NATION_NAME not between", value1, value2, "nationName");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdIsNull() {
            addCriterion("POLITICS_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdIsNotNull() {
            addCriterion("POLITICS_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdEqualTo(String value) {
            addCriterion("POLITICS_STATUS_ID =", value, "politicsStatusId");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdNotEqualTo(String value) {
            addCriterion("POLITICS_STATUS_ID <>", value, "politicsStatusId");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdGreaterThan(String value) {
            addCriterion("POLITICS_STATUS_ID >", value, "politicsStatusId");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("POLITICS_STATUS_ID >=", value, "politicsStatusId");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdLessThan(String value) {
            addCriterion("POLITICS_STATUS_ID <", value, "politicsStatusId");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdLessThanOrEqualTo(String value) {
            addCriterion("POLITICS_STATUS_ID <=", value, "politicsStatusId");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdLike(String value) {
            addCriterion("POLITICS_STATUS_ID like", value, "politicsStatusId");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdNotLike(String value) {
            addCriterion("POLITICS_STATUS_ID not like", value, "politicsStatusId");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdIn(List<String> values) {
            addCriterion("POLITICS_STATUS_ID in", values, "politicsStatusId");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdNotIn(List<String> values) {
            addCriterion("POLITICS_STATUS_ID not in", values, "politicsStatusId");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdBetween(String value1, String value2) {
            addCriterion("POLITICS_STATUS_ID between", value1, value2, "politicsStatusId");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdNotBetween(String value1, String value2) {
            addCriterion("POLITICS_STATUS_ID not between", value1, value2, "politicsStatusId");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameIsNull() {
            addCriterion("POLITICS_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameIsNotNull() {
            addCriterion("POLITICS_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameEqualTo(String value) {
            addCriterion("POLITICS_STATUS_NAME =", value, "politicsStatusName");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameNotEqualTo(String value) {
            addCriterion("POLITICS_STATUS_NAME <>", value, "politicsStatusName");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameGreaterThan(String value) {
            addCriterion("POLITICS_STATUS_NAME >", value, "politicsStatusName");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("POLITICS_STATUS_NAME >=", value, "politicsStatusName");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameLessThan(String value) {
            addCriterion("POLITICS_STATUS_NAME <", value, "politicsStatusName");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameLessThanOrEqualTo(String value) {
            addCriterion("POLITICS_STATUS_NAME <=", value, "politicsStatusName");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameLike(String value) {
            addCriterion("POLITICS_STATUS_NAME like", value, "politicsStatusName");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameNotLike(String value) {
            addCriterion("POLITICS_STATUS_NAME not like", value, "politicsStatusName");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameIn(List<String> values) {
            addCriterion("POLITICS_STATUS_NAME in", values, "politicsStatusName");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameNotIn(List<String> values) {
            addCriterion("POLITICS_STATUS_NAME not in", values, "politicsStatusName");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameBetween(String value1, String value2) {
            addCriterion("POLITICS_STATUS_NAME between", value1, value2, "politicsStatusName");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameNotBetween(String value1, String value2) {
            addCriterion("POLITICS_STATUS_NAME not between", value1, value2, "politicsStatusName");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayIsNull() {
            addCriterion("USER_BIRTHDAY is null");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayIsNotNull() {
            addCriterion("USER_BIRTHDAY is not null");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayEqualTo(String value) {
            addCriterion("USER_BIRTHDAY =", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayNotEqualTo(String value) {
            addCriterion("USER_BIRTHDAY <>", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayGreaterThan(String value) {
            addCriterion("USER_BIRTHDAY >", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayGreaterThanOrEqualTo(String value) {
            addCriterion("USER_BIRTHDAY >=", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayLessThan(String value) {
            addCriterion("USER_BIRTHDAY <", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayLessThanOrEqualTo(String value) {
            addCriterion("USER_BIRTHDAY <=", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayLike(String value) {
            addCriterion("USER_BIRTHDAY like", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayNotLike(String value) {
            addCriterion("USER_BIRTHDAY not like", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayIn(List<String> values) {
            addCriterion("USER_BIRTHDAY in", values, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayNotIn(List<String> values) {
            addCriterion("USER_BIRTHDAY not in", values, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayBetween(String value1, String value2) {
            addCriterion("USER_BIRTHDAY between", value1, value2, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayNotBetween(String value1, String value2) {
            addCriterion("USER_BIRTHDAY not between", value1, value2, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdIsNull() {
            addCriterion("DOMICILE_PLACE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdIsNotNull() {
            addCriterion("DOMICILE_PLACE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdEqualTo(String value) {
            addCriterion("DOMICILE_PLACE_ID =", value, "domicilePlaceId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdNotEqualTo(String value) {
            addCriterion("DOMICILE_PLACE_ID <>", value, "domicilePlaceId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdGreaterThan(String value) {
            addCriterion("DOMICILE_PLACE_ID >", value, "domicilePlaceId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdGreaterThanOrEqualTo(String value) {
            addCriterion("DOMICILE_PLACE_ID >=", value, "domicilePlaceId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdLessThan(String value) {
            addCriterion("DOMICILE_PLACE_ID <", value, "domicilePlaceId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdLessThanOrEqualTo(String value) {
            addCriterion("DOMICILE_PLACE_ID <=", value, "domicilePlaceId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdLike(String value) {
            addCriterion("DOMICILE_PLACE_ID like", value, "domicilePlaceId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdNotLike(String value) {
            addCriterion("DOMICILE_PLACE_ID not like", value, "domicilePlaceId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdIn(List<String> values) {
            addCriterion("DOMICILE_PLACE_ID in", values, "domicilePlaceId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdNotIn(List<String> values) {
            addCriterion("DOMICILE_PLACE_ID not in", values, "domicilePlaceId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdBetween(String value1, String value2) {
            addCriterion("DOMICILE_PLACE_ID between", value1, value2, "domicilePlaceId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdNotBetween(String value1, String value2) {
            addCriterion("DOMICILE_PLACE_ID not between", value1, value2, "domicilePlaceId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameIsNull() {
            addCriterion("DOMICILE_PLACE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameIsNotNull() {
            addCriterion("DOMICILE_PLACE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameEqualTo(String value) {
            addCriterion("DOMICILE_PLACE_NAME =", value, "domicilePlaceName");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameNotEqualTo(String value) {
            addCriterion("DOMICILE_PLACE_NAME <>", value, "domicilePlaceName");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameGreaterThan(String value) {
            addCriterion("DOMICILE_PLACE_NAME >", value, "domicilePlaceName");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameGreaterThanOrEqualTo(String value) {
            addCriterion("DOMICILE_PLACE_NAME >=", value, "domicilePlaceName");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameLessThan(String value) {
            addCriterion("DOMICILE_PLACE_NAME <", value, "domicilePlaceName");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameLessThanOrEqualTo(String value) {
            addCriterion("DOMICILE_PLACE_NAME <=", value, "domicilePlaceName");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameLike(String value) {
            addCriterion("DOMICILE_PLACE_NAME like", value, "domicilePlaceName");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameNotLike(String value) {
            addCriterion("DOMICILE_PLACE_NAME not like", value, "domicilePlaceName");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameIn(List<String> values) {
            addCriterion("DOMICILE_PLACE_NAME in", values, "domicilePlaceName");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameNotIn(List<String> values) {
            addCriterion("DOMICILE_PLACE_NAME not in", values, "domicilePlaceName");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameBetween(String value1, String value2) {
            addCriterion("DOMICILE_PLACE_NAME between", value1, value2, "domicilePlaceName");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameNotBetween(String value1, String value2) {
            addCriterion("DOMICILE_PLACE_NAME not between", value1, value2, "domicilePlaceName");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdIsNull() {
            addCriterion("CRET_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdIsNotNull() {
            addCriterion("CRET_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdEqualTo(String value) {
            addCriterion("CRET_TYPE_ID =", value, "cretTypeId");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdNotEqualTo(String value) {
            addCriterion("CRET_TYPE_ID <>", value, "cretTypeId");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdGreaterThan(String value) {
            addCriterion("CRET_TYPE_ID >", value, "cretTypeId");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("CRET_TYPE_ID >=", value, "cretTypeId");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdLessThan(String value) {
            addCriterion("CRET_TYPE_ID <", value, "cretTypeId");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdLessThanOrEqualTo(String value) {
            addCriterion("CRET_TYPE_ID <=", value, "cretTypeId");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdLike(String value) {
            addCriterion("CRET_TYPE_ID like", value, "cretTypeId");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdNotLike(String value) {
            addCriterion("CRET_TYPE_ID not like", value, "cretTypeId");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdIn(List<String> values) {
            addCriterion("CRET_TYPE_ID in", values, "cretTypeId");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdNotIn(List<String> values) {
            addCriterion("CRET_TYPE_ID not in", values, "cretTypeId");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdBetween(String value1, String value2) {
            addCriterion("CRET_TYPE_ID between", value1, value2, "cretTypeId");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdNotBetween(String value1, String value2) {
            addCriterion("CRET_TYPE_ID not between", value1, value2, "cretTypeId");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameIsNull() {
            addCriterion("CRET_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameIsNotNull() {
            addCriterion("CRET_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameEqualTo(String value) {
            addCriterion("CRET_TYPE_NAME =", value, "cretTypeName");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameNotEqualTo(String value) {
            addCriterion("CRET_TYPE_NAME <>", value, "cretTypeName");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameGreaterThan(String value) {
            addCriterion("CRET_TYPE_NAME >", value, "cretTypeName");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("CRET_TYPE_NAME >=", value, "cretTypeName");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameLessThan(String value) {
            addCriterion("CRET_TYPE_NAME <", value, "cretTypeName");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameLessThanOrEqualTo(String value) {
            addCriterion("CRET_TYPE_NAME <=", value, "cretTypeName");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameLike(String value) {
            addCriterion("CRET_TYPE_NAME like", value, "cretTypeName");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameNotLike(String value) {
            addCriterion("CRET_TYPE_NAME not like", value, "cretTypeName");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameIn(List<String> values) {
            addCriterion("CRET_TYPE_NAME in", values, "cretTypeName");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameNotIn(List<String> values) {
            addCriterion("CRET_TYPE_NAME not in", values, "cretTypeName");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameBetween(String value1, String value2) {
            addCriterion("CRET_TYPE_NAME between", value1, value2, "cretTypeName");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameNotBetween(String value1, String value2) {
            addCriterion("CRET_TYPE_NAME not between", value1, value2, "cretTypeName");
            return (Criteria) this;
        }

        public Criteria andIdNoIsNull() {
            addCriterion("ID_NO is null");
            return (Criteria) this;
        }

        public Criteria andIdNoIsNotNull() {
            addCriterion("ID_NO is not null");
            return (Criteria) this;
        }

        public Criteria andIdNoEqualTo(String value) {
            addCriterion("ID_NO =", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotEqualTo(String value) {
            addCriterion("ID_NO <>", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoGreaterThan(String value) {
            addCriterion("ID_NO >", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoGreaterThanOrEqualTo(String value) {
            addCriterion("ID_NO >=", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLessThan(String value) {
            addCriterion("ID_NO <", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLessThanOrEqualTo(String value) {
            addCriterion("ID_NO <=", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLike(String value) {
            addCriterion("ID_NO like", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotLike(String value) {
            addCriterion("ID_NO not like", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoIn(List<String> values) {
            addCriterion("ID_NO in", values, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotIn(List<String> values) {
            addCriterion("ID_NO not in", values, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoBetween(String value1, String value2) {
            addCriterion("ID_NO between", value1, value2, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotBetween(String value1, String value2) {
            addCriterion("ID_NO not between", value1, value2, "idNo");
            return (Criteria) this;
        }

        public Criteria andCountryAreaIsNull() {
            addCriterion("COUNTRY_AREA is null");
            return (Criteria) this;
        }

        public Criteria andCountryAreaIsNotNull() {
            addCriterion("COUNTRY_AREA is not null");
            return (Criteria) this;
        }

        public Criteria andCountryAreaEqualTo(String value) {
            addCriterion("COUNTRY_AREA =", value, "countryArea");
            return (Criteria) this;
        }

        public Criteria andCountryAreaNotEqualTo(String value) {
            addCriterion("COUNTRY_AREA <>", value, "countryArea");
            return (Criteria) this;
        }

        public Criteria andCountryAreaGreaterThan(String value) {
            addCriterion("COUNTRY_AREA >", value, "countryArea");
            return (Criteria) this;
        }

        public Criteria andCountryAreaGreaterThanOrEqualTo(String value) {
            addCriterion("COUNTRY_AREA >=", value, "countryArea");
            return (Criteria) this;
        }

        public Criteria andCountryAreaLessThan(String value) {
            addCriterion("COUNTRY_AREA <", value, "countryArea");
            return (Criteria) this;
        }

        public Criteria andCountryAreaLessThanOrEqualTo(String value) {
            addCriterion("COUNTRY_AREA <=", value, "countryArea");
            return (Criteria) this;
        }

        public Criteria andCountryAreaLike(String value) {
            addCriterion("COUNTRY_AREA like", value, "countryArea");
            return (Criteria) this;
        }

        public Criteria andCountryAreaNotLike(String value) {
            addCriterion("COUNTRY_AREA not like", value, "countryArea");
            return (Criteria) this;
        }

        public Criteria andCountryAreaIn(List<String> values) {
            addCriterion("COUNTRY_AREA in", values, "countryArea");
            return (Criteria) this;
        }

        public Criteria andCountryAreaNotIn(List<String> values) {
            addCriterion("COUNTRY_AREA not in", values, "countryArea");
            return (Criteria) this;
        }

        public Criteria andCountryAreaBetween(String value1, String value2) {
            addCriterion("COUNTRY_AREA between", value1, value2, "countryArea");
            return (Criteria) this;
        }

        public Criteria andCountryAreaNotBetween(String value1, String value2) {
            addCriterion("COUNTRY_AREA not between", value1, value2, "countryArea");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNull() {
            addCriterion("PERIOD is null");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNotNull() {
            addCriterion("PERIOD is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodEqualTo(String value) {
            addCriterion("PERIOD =", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotEqualTo(String value) {
            addCriterion("PERIOD <>", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThan(String value) {
            addCriterion("PERIOD >", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThanOrEqualTo(String value) {
            addCriterion("PERIOD >=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThan(String value) {
            addCriterion("PERIOD <", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThanOrEqualTo(String value) {
            addCriterion("PERIOD <=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLike(String value) {
            addCriterion("PERIOD like", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotLike(String value) {
            addCriterion("PERIOD not like", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodIn(List<String> values) {
            addCriterion("PERIOD in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotIn(List<String> values) {
            addCriterion("PERIOD not in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodBetween(String value1, String value2) {
            addCriterion("PERIOD between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotBetween(String value1, String value2) {
            addCriterion("PERIOD not between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeIsNull() {
            addCriterion("GRADUATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeIsNotNull() {
            addCriterion("GRADUATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeEqualTo(String value) {
            addCriterion("GRADUATE_TIME =", value, "graduateTime");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeNotEqualTo(String value) {
            addCriterion("GRADUATE_TIME <>", value, "graduateTime");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeGreaterThan(String value) {
            addCriterion("GRADUATE_TIME >", value, "graduateTime");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATE_TIME >=", value, "graduateTime");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeLessThan(String value) {
            addCriterion("GRADUATE_TIME <", value, "graduateTime");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeLessThanOrEqualTo(String value) {
            addCriterion("GRADUATE_TIME <=", value, "graduateTime");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeLike(String value) {
            addCriterion("GRADUATE_TIME like", value, "graduateTime");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeNotLike(String value) {
            addCriterion("GRADUATE_TIME not like", value, "graduateTime");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeIn(List<String> values) {
            addCriterion("GRADUATE_TIME in", values, "graduateTime");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeNotIn(List<String> values) {
            addCriterion("GRADUATE_TIME not in", values, "graduateTime");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeBetween(String value1, String value2) {
            addCriterion("GRADUATE_TIME between", value1, value2, "graduateTime");
            return (Criteria) this;
        }

        public Criteria andGraduateTimeNotBetween(String value1, String value2) {
            addCriterion("GRADUATE_TIME not between", value1, value2, "graduateTime");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowIsNull() {
            addCriterion("FIRST_TEACHER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowIsNotNull() {
            addCriterion("FIRST_TEACHER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowEqualTo(String value) {
            addCriterion("FIRST_TEACHER_FLOW =", value, "firstTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowNotEqualTo(String value) {
            addCriterion("FIRST_TEACHER_FLOW <>", value, "firstTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowGreaterThan(String value) {
            addCriterion("FIRST_TEACHER_FLOW >", value, "firstTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowGreaterThanOrEqualTo(String value) {
            addCriterion("FIRST_TEACHER_FLOW >=", value, "firstTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowLessThan(String value) {
            addCriterion("FIRST_TEACHER_FLOW <", value, "firstTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowLessThanOrEqualTo(String value) {
            addCriterion("FIRST_TEACHER_FLOW <=", value, "firstTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowLike(String value) {
            addCriterion("FIRST_TEACHER_FLOW like", value, "firstTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowNotLike(String value) {
            addCriterion("FIRST_TEACHER_FLOW not like", value, "firstTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowIn(List<String> values) {
            addCriterion("FIRST_TEACHER_FLOW in", values, "firstTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowNotIn(List<String> values) {
            addCriterion("FIRST_TEACHER_FLOW not in", values, "firstTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowBetween(String value1, String value2) {
            addCriterion("FIRST_TEACHER_FLOW between", value1, value2, "firstTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherFlowNotBetween(String value1, String value2) {
            addCriterion("FIRST_TEACHER_FLOW not between", value1, value2, "firstTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherIsNull() {
            addCriterion("FIRST_TEACHER is null");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherIsNotNull() {
            addCriterion("FIRST_TEACHER is not null");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherEqualTo(String value) {
            addCriterion("FIRST_TEACHER =", value, "firstTeacher");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherNotEqualTo(String value) {
            addCriterion("FIRST_TEACHER <>", value, "firstTeacher");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherGreaterThan(String value) {
            addCriterion("FIRST_TEACHER >", value, "firstTeacher");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherGreaterThanOrEqualTo(String value) {
            addCriterion("FIRST_TEACHER >=", value, "firstTeacher");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherLessThan(String value) {
            addCriterion("FIRST_TEACHER <", value, "firstTeacher");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherLessThanOrEqualTo(String value) {
            addCriterion("FIRST_TEACHER <=", value, "firstTeacher");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherLike(String value) {
            addCriterion("FIRST_TEACHER like", value, "firstTeacher");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherNotLike(String value) {
            addCriterion("FIRST_TEACHER not like", value, "firstTeacher");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherIn(List<String> values) {
            addCriterion("FIRST_TEACHER in", values, "firstTeacher");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherNotIn(List<String> values) {
            addCriterion("FIRST_TEACHER not in", values, "firstTeacher");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherBetween(String value1, String value2) {
            addCriterion("FIRST_TEACHER between", value1, value2, "firstTeacher");
            return (Criteria) this;
        }

        public Criteria andFirstTeacherNotBetween(String value1, String value2) {
            addCriterion("FIRST_TEACHER not between", value1, value2, "firstTeacher");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowIsNull() {
            addCriterion("SECOND_TEACHER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowIsNotNull() {
            addCriterion("SECOND_TEACHER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowEqualTo(String value) {
            addCriterion("SECOND_TEACHER_FLOW =", value, "secondTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowNotEqualTo(String value) {
            addCriterion("SECOND_TEACHER_FLOW <>", value, "secondTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowGreaterThan(String value) {
            addCriterion("SECOND_TEACHER_FLOW >", value, "secondTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SECOND_TEACHER_FLOW >=", value, "secondTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowLessThan(String value) {
            addCriterion("SECOND_TEACHER_FLOW <", value, "secondTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowLessThanOrEqualTo(String value) {
            addCriterion("SECOND_TEACHER_FLOW <=", value, "secondTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowLike(String value) {
            addCriterion("SECOND_TEACHER_FLOW like", value, "secondTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowNotLike(String value) {
            addCriterion("SECOND_TEACHER_FLOW not like", value, "secondTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowIn(List<String> values) {
            addCriterion("SECOND_TEACHER_FLOW in", values, "secondTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowNotIn(List<String> values) {
            addCriterion("SECOND_TEACHER_FLOW not in", values, "secondTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowBetween(String value1, String value2) {
            addCriterion("SECOND_TEACHER_FLOW between", value1, value2, "secondTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherFlowNotBetween(String value1, String value2) {
            addCriterion("SECOND_TEACHER_FLOW not between", value1, value2, "secondTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherIsNull() {
            addCriterion("SECOND_TEACHER is null");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherIsNotNull() {
            addCriterion("SECOND_TEACHER is not null");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherEqualTo(String value) {
            addCriterion("SECOND_TEACHER =", value, "secondTeacher");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherNotEqualTo(String value) {
            addCriterion("SECOND_TEACHER <>", value, "secondTeacher");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherGreaterThan(String value) {
            addCriterion("SECOND_TEACHER >", value, "secondTeacher");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherGreaterThanOrEqualTo(String value) {
            addCriterion("SECOND_TEACHER >=", value, "secondTeacher");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherLessThan(String value) {
            addCriterion("SECOND_TEACHER <", value, "secondTeacher");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherLessThanOrEqualTo(String value) {
            addCriterion("SECOND_TEACHER <=", value, "secondTeacher");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherLike(String value) {
            addCriterion("SECOND_TEACHER like", value, "secondTeacher");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherNotLike(String value) {
            addCriterion("SECOND_TEACHER not like", value, "secondTeacher");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherIn(List<String> values) {
            addCriterion("SECOND_TEACHER in", values, "secondTeacher");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherNotIn(List<String> values) {
            addCriterion("SECOND_TEACHER not in", values, "secondTeacher");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherBetween(String value1, String value2) {
            addCriterion("SECOND_TEACHER between", value1, value2, "secondTeacher");
            return (Criteria) this;
        }

        public Criteria andSecondTeacherNotBetween(String value1, String value2) {
            addCriterion("SECOND_TEACHER not between", value1, value2, "secondTeacher");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeIsNull() {
            addCriterion("RECRUIT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeIsNotNull() {
            addCriterion("RECRUIT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeEqualTo(String value) {
            addCriterion("RECRUIT_TYPE =", value, "recruitType");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeNotEqualTo(String value) {
            addCriterion("RECRUIT_TYPE <>", value, "recruitType");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeGreaterThan(String value) {
            addCriterion("RECRUIT_TYPE >", value, "recruitType");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeGreaterThanOrEqualTo(String value) {
            addCriterion("RECRUIT_TYPE >=", value, "recruitType");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeLessThan(String value) {
            addCriterion("RECRUIT_TYPE <", value, "recruitType");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeLessThanOrEqualTo(String value) {
            addCriterion("RECRUIT_TYPE <=", value, "recruitType");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeLike(String value) {
            addCriterion("RECRUIT_TYPE like", value, "recruitType");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeNotLike(String value) {
            addCriterion("RECRUIT_TYPE not like", value, "recruitType");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeIn(List<String> values) {
            addCriterion("RECRUIT_TYPE in", values, "recruitType");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeNotIn(List<String> values) {
            addCriterion("RECRUIT_TYPE not in", values, "recruitType");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeBetween(String value1, String value2) {
            addCriterion("RECRUIT_TYPE between", value1, value2, "recruitType");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeNotBetween(String value1, String value2) {
            addCriterion("RECRUIT_TYPE not between", value1, value2, "recruitType");
            return (Criteria) this;
        }

        public Criteria andStudentCodeIsNull() {
            addCriterion("STUDENT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andStudentCodeIsNotNull() {
            addCriterion("STUDENT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andStudentCodeEqualTo(String value) {
            addCriterion("STUDENT_CODE =", value, "studentCode");
            return (Criteria) this;
        }

        public Criteria andStudentCodeNotEqualTo(String value) {
            addCriterion("STUDENT_CODE <>", value, "studentCode");
            return (Criteria) this;
        }

        public Criteria andStudentCodeGreaterThan(String value) {
            addCriterion("STUDENT_CODE >", value, "studentCode");
            return (Criteria) this;
        }

        public Criteria andStudentCodeGreaterThanOrEqualTo(String value) {
            addCriterion("STUDENT_CODE >=", value, "studentCode");
            return (Criteria) this;
        }

        public Criteria andStudentCodeLessThan(String value) {
            addCriterion("STUDENT_CODE <", value, "studentCode");
            return (Criteria) this;
        }

        public Criteria andStudentCodeLessThanOrEqualTo(String value) {
            addCriterion("STUDENT_CODE <=", value, "studentCode");
            return (Criteria) this;
        }

        public Criteria andStudentCodeLike(String value) {
            addCriterion("STUDENT_CODE like", value, "studentCode");
            return (Criteria) this;
        }

        public Criteria andStudentCodeNotLike(String value) {
            addCriterion("STUDENT_CODE not like", value, "studentCode");
            return (Criteria) this;
        }

        public Criteria andStudentCodeIn(List<String> values) {
            addCriterion("STUDENT_CODE in", values, "studentCode");
            return (Criteria) this;
        }

        public Criteria andStudentCodeNotIn(List<String> values) {
            addCriterion("STUDENT_CODE not in", values, "studentCode");
            return (Criteria) this;
        }

        public Criteria andStudentCodeBetween(String value1, String value2) {
            addCriterion("STUDENT_CODE between", value1, value2, "studentCode");
            return (Criteria) this;
        }

        public Criteria andStudentCodeNotBetween(String value1, String value2) {
            addCriterion("STUDENT_CODE not between", value1, value2, "studentCode");
            return (Criteria) this;
        }

        public Criteria andStudyTypeIsNull() {
            addCriterion("STUDY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andStudyTypeIsNotNull() {
            addCriterion("STUDY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andStudyTypeEqualTo(String value) {
            addCriterion("STUDY_TYPE =", value, "studyType");
            return (Criteria) this;
        }

        public Criteria andStudyTypeNotEqualTo(String value) {
            addCriterion("STUDY_TYPE <>", value, "studyType");
            return (Criteria) this;
        }

        public Criteria andStudyTypeGreaterThan(String value) {
            addCriterion("STUDY_TYPE >", value, "studyType");
            return (Criteria) this;
        }

        public Criteria andStudyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("STUDY_TYPE >=", value, "studyType");
            return (Criteria) this;
        }

        public Criteria andStudyTypeLessThan(String value) {
            addCriterion("STUDY_TYPE <", value, "studyType");
            return (Criteria) this;
        }

        public Criteria andStudyTypeLessThanOrEqualTo(String value) {
            addCriterion("STUDY_TYPE <=", value, "studyType");
            return (Criteria) this;
        }

        public Criteria andStudyTypeLike(String value) {
            addCriterion("STUDY_TYPE like", value, "studyType");
            return (Criteria) this;
        }

        public Criteria andStudyTypeNotLike(String value) {
            addCriterion("STUDY_TYPE not like", value, "studyType");
            return (Criteria) this;
        }

        public Criteria andStudyTypeIn(List<String> values) {
            addCriterion("STUDY_TYPE in", values, "studyType");
            return (Criteria) this;
        }

        public Criteria andStudyTypeNotIn(List<String> values) {
            addCriterion("STUDY_TYPE not in", values, "studyType");
            return (Criteria) this;
        }

        public Criteria andStudyTypeBetween(String value1, String value2) {
            addCriterion("STUDY_TYPE between", value1, value2, "studyType");
            return (Criteria) this;
        }

        public Criteria andStudyTypeNotBetween(String value1, String value2) {
            addCriterion("STUDY_TYPE not between", value1, value2, "studyType");
            return (Criteria) this;
        }

        public Criteria andTestTypeIsNull() {
            addCriterion("TEST_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTestTypeIsNotNull() {
            addCriterion("TEST_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTestTypeEqualTo(String value) {
            addCriterion("TEST_TYPE =", value, "testType");
            return (Criteria) this;
        }

        public Criteria andTestTypeNotEqualTo(String value) {
            addCriterion("TEST_TYPE <>", value, "testType");
            return (Criteria) this;
        }

        public Criteria andTestTypeGreaterThan(String value) {
            addCriterion("TEST_TYPE >", value, "testType");
            return (Criteria) this;
        }

        public Criteria andTestTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TEST_TYPE >=", value, "testType");
            return (Criteria) this;
        }

        public Criteria andTestTypeLessThan(String value) {
            addCriterion("TEST_TYPE <", value, "testType");
            return (Criteria) this;
        }

        public Criteria andTestTypeLessThanOrEqualTo(String value) {
            addCriterion("TEST_TYPE <=", value, "testType");
            return (Criteria) this;
        }

        public Criteria andTestTypeLike(String value) {
            addCriterion("TEST_TYPE like", value, "testType");
            return (Criteria) this;
        }

        public Criteria andTestTypeNotLike(String value) {
            addCriterion("TEST_TYPE not like", value, "testType");
            return (Criteria) this;
        }

        public Criteria andTestTypeIn(List<String> values) {
            addCriterion("TEST_TYPE in", values, "testType");
            return (Criteria) this;
        }

        public Criteria andTestTypeNotIn(List<String> values) {
            addCriterion("TEST_TYPE not in", values, "testType");
            return (Criteria) this;
        }

        public Criteria andTestTypeBetween(String value1, String value2) {
            addCriterion("TEST_TYPE between", value1, value2, "testType");
            return (Criteria) this;
        }

        public Criteria andTestTypeNotBetween(String value1, String value2) {
            addCriterion("TEST_TYPE not between", value1, value2, "testType");
            return (Criteria) this;
        }

        public Criteria andTestQualifiedNoIsNull() {
            addCriterion("TEST_QUALIFIED_NO is null");
            return (Criteria) this;
        }

        public Criteria andTestQualifiedNoIsNotNull() {
            addCriterion("TEST_QUALIFIED_NO is not null");
            return (Criteria) this;
        }

        public Criteria andTestQualifiedNoEqualTo(String value) {
            addCriterion("TEST_QUALIFIED_NO =", value, "testQualifiedNo");
            return (Criteria) this;
        }

        public Criteria andTestQualifiedNoNotEqualTo(String value) {
            addCriterion("TEST_QUALIFIED_NO <>", value, "testQualifiedNo");
            return (Criteria) this;
        }

        public Criteria andTestQualifiedNoGreaterThan(String value) {
            addCriterion("TEST_QUALIFIED_NO >", value, "testQualifiedNo");
            return (Criteria) this;
        }

        public Criteria andTestQualifiedNoGreaterThanOrEqualTo(String value) {
            addCriterion("TEST_QUALIFIED_NO >=", value, "testQualifiedNo");
            return (Criteria) this;
        }

        public Criteria andTestQualifiedNoLessThan(String value) {
            addCriterion("TEST_QUALIFIED_NO <", value, "testQualifiedNo");
            return (Criteria) this;
        }

        public Criteria andTestQualifiedNoLessThanOrEqualTo(String value) {
            addCriterion("TEST_QUALIFIED_NO <=", value, "testQualifiedNo");
            return (Criteria) this;
        }

        public Criteria andTestQualifiedNoLike(String value) {
            addCriterion("TEST_QUALIFIED_NO like", value, "testQualifiedNo");
            return (Criteria) this;
        }

        public Criteria andTestQualifiedNoNotLike(String value) {
            addCriterion("TEST_QUALIFIED_NO not like", value, "testQualifiedNo");
            return (Criteria) this;
        }

        public Criteria andTestQualifiedNoIn(List<String> values) {
            addCriterion("TEST_QUALIFIED_NO in", values, "testQualifiedNo");
            return (Criteria) this;
        }

        public Criteria andTestQualifiedNoNotIn(List<String> values) {
            addCriterion("TEST_QUALIFIED_NO not in", values, "testQualifiedNo");
            return (Criteria) this;
        }

        public Criteria andTestQualifiedNoBetween(String value1, String value2) {
            addCriterion("TEST_QUALIFIED_NO between", value1, value2, "testQualifiedNo");
            return (Criteria) this;
        }

        public Criteria andTestQualifiedNoNotBetween(String value1, String value2) {
            addCriterion("TEST_QUALIFIED_NO not between", value1, value2, "testQualifiedNo");
            return (Criteria) this;
        }

        public Criteria andGainPreDegreeIsNull() {
            addCriterion("GAIN_PRE_DEGREE is null");
            return (Criteria) this;
        }

        public Criteria andGainPreDegreeIsNotNull() {
            addCriterion("GAIN_PRE_DEGREE is not null");
            return (Criteria) this;
        }

        public Criteria andGainPreDegreeEqualTo(String value) {
            addCriterion("GAIN_PRE_DEGREE =", value, "gainPreDegree");
            return (Criteria) this;
        }

        public Criteria andGainPreDegreeNotEqualTo(String value) {
            addCriterion("GAIN_PRE_DEGREE <>", value, "gainPreDegree");
            return (Criteria) this;
        }

        public Criteria andGainPreDegreeGreaterThan(String value) {
            addCriterion("GAIN_PRE_DEGREE >", value, "gainPreDegree");
            return (Criteria) this;
        }

        public Criteria andGainPreDegreeGreaterThanOrEqualTo(String value) {
            addCriterion("GAIN_PRE_DEGREE >=", value, "gainPreDegree");
            return (Criteria) this;
        }

        public Criteria andGainPreDegreeLessThan(String value) {
            addCriterion("GAIN_PRE_DEGREE <", value, "gainPreDegree");
            return (Criteria) this;
        }

        public Criteria andGainPreDegreeLessThanOrEqualTo(String value) {
            addCriterion("GAIN_PRE_DEGREE <=", value, "gainPreDegree");
            return (Criteria) this;
        }

        public Criteria andGainPreDegreeLike(String value) {
            addCriterion("GAIN_PRE_DEGREE like", value, "gainPreDegree");
            return (Criteria) this;
        }

        public Criteria andGainPreDegreeNotLike(String value) {
            addCriterion("GAIN_PRE_DEGREE not like", value, "gainPreDegree");
            return (Criteria) this;
        }

        public Criteria andGainPreDegreeIn(List<String> values) {
            addCriterion("GAIN_PRE_DEGREE in", values, "gainPreDegree");
            return (Criteria) this;
        }

        public Criteria andGainPreDegreeNotIn(List<String> values) {
            addCriterion("GAIN_PRE_DEGREE not in", values, "gainPreDegree");
            return (Criteria) this;
        }

        public Criteria andGainPreDegreeBetween(String value1, String value2) {
            addCriterion("GAIN_PRE_DEGREE between", value1, value2, "gainPreDegree");
            return (Criteria) this;
        }

        public Criteria andGainPreDegreeNotBetween(String value1, String value2) {
            addCriterion("GAIN_PRE_DEGREE not between", value1, value2, "gainPreDegree");
            return (Criteria) this;
        }

        public Criteria andOneSubjectFlagIsNull() {
            addCriterion("ONE_SUBJECT_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andOneSubjectFlagIsNotNull() {
            addCriterion("ONE_SUBJECT_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andOneSubjectFlagEqualTo(String value) {
            addCriterion("ONE_SUBJECT_FLAG =", value, "oneSubjectFlag");
            return (Criteria) this;
        }

        public Criteria andOneSubjectFlagNotEqualTo(String value) {
            addCriterion("ONE_SUBJECT_FLAG <>", value, "oneSubjectFlag");
            return (Criteria) this;
        }

        public Criteria andOneSubjectFlagGreaterThan(String value) {
            addCriterion("ONE_SUBJECT_FLAG >", value, "oneSubjectFlag");
            return (Criteria) this;
        }

        public Criteria andOneSubjectFlagGreaterThanOrEqualTo(String value) {
            addCriterion("ONE_SUBJECT_FLAG >=", value, "oneSubjectFlag");
            return (Criteria) this;
        }

        public Criteria andOneSubjectFlagLessThan(String value) {
            addCriterion("ONE_SUBJECT_FLAG <", value, "oneSubjectFlag");
            return (Criteria) this;
        }

        public Criteria andOneSubjectFlagLessThanOrEqualTo(String value) {
            addCriterion("ONE_SUBJECT_FLAG <=", value, "oneSubjectFlag");
            return (Criteria) this;
        }

        public Criteria andOneSubjectFlagLike(String value) {
            addCriterion("ONE_SUBJECT_FLAG like", value, "oneSubjectFlag");
            return (Criteria) this;
        }

        public Criteria andOneSubjectFlagNotLike(String value) {
            addCriterion("ONE_SUBJECT_FLAG not like", value, "oneSubjectFlag");
            return (Criteria) this;
        }

        public Criteria andOneSubjectFlagIn(List<String> values) {
            addCriterion("ONE_SUBJECT_FLAG in", values, "oneSubjectFlag");
            return (Criteria) this;
        }

        public Criteria andOneSubjectFlagNotIn(List<String> values) {
            addCriterion("ONE_SUBJECT_FLAG not in", values, "oneSubjectFlag");
            return (Criteria) this;
        }

        public Criteria andOneSubjectFlagBetween(String value1, String value2) {
            addCriterion("ONE_SUBJECT_FLAG between", value1, value2, "oneSubjectFlag");
            return (Criteria) this;
        }

        public Criteria andOneSubjectFlagNotBetween(String value1, String value2) {
            addCriterion("ONE_SUBJECT_FLAG not between", value1, value2, "oneSubjectFlag");
            return (Criteria) this;
        }

        public Criteria andMajorIdIsNull() {
            addCriterion("MAJOR_ID is null");
            return (Criteria) this;
        }

        public Criteria andMajorIdIsNotNull() {
            addCriterion("MAJOR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMajorIdEqualTo(String value) {
            addCriterion("MAJOR_ID =", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotEqualTo(String value) {
            addCriterion("MAJOR_ID <>", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdGreaterThan(String value) {
            addCriterion("MAJOR_ID >", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdGreaterThanOrEqualTo(String value) {
            addCriterion("MAJOR_ID >=", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdLessThan(String value) {
            addCriterion("MAJOR_ID <", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdLessThanOrEqualTo(String value) {
            addCriterion("MAJOR_ID <=", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdLike(String value) {
            addCriterion("MAJOR_ID like", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotLike(String value) {
            addCriterion("MAJOR_ID not like", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdIn(List<String> values) {
            addCriterion("MAJOR_ID in", values, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotIn(List<String> values) {
            addCriterion("MAJOR_ID not in", values, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdBetween(String value1, String value2) {
            addCriterion("MAJOR_ID between", value1, value2, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotBetween(String value1, String value2) {
            addCriterion("MAJOR_ID not between", value1, value2, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorNameIsNull() {
            addCriterion("MAJOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMajorNameIsNotNull() {
            addCriterion("MAJOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMajorNameEqualTo(String value) {
            addCriterion("MAJOR_NAME =", value, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameNotEqualTo(String value) {
            addCriterion("MAJOR_NAME <>", value, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameGreaterThan(String value) {
            addCriterion("MAJOR_NAME >", value, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameGreaterThanOrEqualTo(String value) {
            addCriterion("MAJOR_NAME >=", value, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameLessThan(String value) {
            addCriterion("MAJOR_NAME <", value, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameLessThanOrEqualTo(String value) {
            addCriterion("MAJOR_NAME <=", value, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameLike(String value) {
            addCriterion("MAJOR_NAME like", value, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameNotLike(String value) {
            addCriterion("MAJOR_NAME not like", value, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameIn(List<String> values) {
            addCriterion("MAJOR_NAME in", values, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameNotIn(List<String> values) {
            addCriterion("MAJOR_NAME not in", values, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameBetween(String value1, String value2) {
            addCriterion("MAJOR_NAME between", value1, value2, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameNotBetween(String value1, String value2) {
            addCriterion("MAJOR_NAME not between", value1, value2, "majorName");
            return (Criteria) this;
        }

        public Criteria andPreGraduationIsNull() {
            addCriterion("PRE_GRADUATION is null");
            return (Criteria) this;
        }

        public Criteria andPreGraduationIsNotNull() {
            addCriterion("PRE_GRADUATION is not null");
            return (Criteria) this;
        }

        public Criteria andPreGraduationEqualTo(String value) {
            addCriterion("PRE_GRADUATION =", value, "preGraduation");
            return (Criteria) this;
        }

        public Criteria andPreGraduationNotEqualTo(String value) {
            addCriterion("PRE_GRADUATION <>", value, "preGraduation");
            return (Criteria) this;
        }

        public Criteria andPreGraduationGreaterThan(String value) {
            addCriterion("PRE_GRADUATION >", value, "preGraduation");
            return (Criteria) this;
        }

        public Criteria andPreGraduationGreaterThanOrEqualTo(String value) {
            addCriterion("PRE_GRADUATION >=", value, "preGraduation");
            return (Criteria) this;
        }

        public Criteria andPreGraduationLessThan(String value) {
            addCriterion("PRE_GRADUATION <", value, "preGraduation");
            return (Criteria) this;
        }

        public Criteria andPreGraduationLessThanOrEqualTo(String value) {
            addCriterion("PRE_GRADUATION <=", value, "preGraduation");
            return (Criteria) this;
        }

        public Criteria andPreGraduationLike(String value) {
            addCriterion("PRE_GRADUATION like", value, "preGraduation");
            return (Criteria) this;
        }

        public Criteria andPreGraduationNotLike(String value) {
            addCriterion("PRE_GRADUATION not like", value, "preGraduation");
            return (Criteria) this;
        }

        public Criteria andPreGraduationIn(List<String> values) {
            addCriterion("PRE_GRADUATION in", values, "preGraduation");
            return (Criteria) this;
        }

        public Criteria andPreGraduationNotIn(List<String> values) {
            addCriterion("PRE_GRADUATION not in", values, "preGraduation");
            return (Criteria) this;
        }

        public Criteria andPreGraduationBetween(String value1, String value2) {
            addCriterion("PRE_GRADUATION between", value1, value2, "preGraduation");
            return (Criteria) this;
        }

        public Criteria andPreGraduationNotBetween(String value1, String value2) {
            addCriterion("PRE_GRADUATION not between", value1, value2, "preGraduation");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeIsNull() {
            addCriterion("AWARD_DEGREE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeIsNotNull() {
            addCriterion("AWARD_DEGREE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeEqualTo(String value) {
            addCriterion("AWARD_DEGREE_TIME =", value, "awardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeNotEqualTo(String value) {
            addCriterion("AWARD_DEGREE_TIME <>", value, "awardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeGreaterThan(String value) {
            addCriterion("AWARD_DEGREE_TIME >", value, "awardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeGreaterThanOrEqualTo(String value) {
            addCriterion("AWARD_DEGREE_TIME >=", value, "awardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeLessThan(String value) {
            addCriterion("AWARD_DEGREE_TIME <", value, "awardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeLessThanOrEqualTo(String value) {
            addCriterion("AWARD_DEGREE_TIME <=", value, "awardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeLike(String value) {
            addCriterion("AWARD_DEGREE_TIME like", value, "awardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeNotLike(String value) {
            addCriterion("AWARD_DEGREE_TIME not like", value, "awardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeIn(List<String> values) {
            addCriterion("AWARD_DEGREE_TIME in", values, "awardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeNotIn(List<String> values) {
            addCriterion("AWARD_DEGREE_TIME not in", values, "awardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeBetween(String value1, String value2) {
            addCriterion("AWARD_DEGREE_TIME between", value1, value2, "awardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeTimeNotBetween(String value1, String value2) {
            addCriterion("AWARD_DEGREE_TIME not between", value1, value2, "awardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeIsNull() {
            addCriterion("AWARD_DEGREE_CERT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeIsNotNull() {
            addCriterion("AWARD_DEGREE_CERT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeEqualTo(String value) {
            addCriterion("AWARD_DEGREE_CERT_CODE =", value, "awardDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeNotEqualTo(String value) {
            addCriterion("AWARD_DEGREE_CERT_CODE <>", value, "awardDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeGreaterThan(String value) {
            addCriterion("AWARD_DEGREE_CERT_CODE >", value, "awardDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeGreaterThanOrEqualTo(String value) {
            addCriterion("AWARD_DEGREE_CERT_CODE >=", value, "awardDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeLessThan(String value) {
            addCriterion("AWARD_DEGREE_CERT_CODE <", value, "awardDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeLessThanOrEqualTo(String value) {
            addCriterion("AWARD_DEGREE_CERT_CODE <=", value, "awardDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeLike(String value) {
            addCriterion("AWARD_DEGREE_CERT_CODE like", value, "awardDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeNotLike(String value) {
            addCriterion("AWARD_DEGREE_CERT_CODE not like", value, "awardDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeIn(List<String> values) {
            addCriterion("AWARD_DEGREE_CERT_CODE in", values, "awardDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeNotIn(List<String> values) {
            addCriterion("AWARD_DEGREE_CERT_CODE not in", values, "awardDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeBetween(String value1, String value2) {
            addCriterion("AWARD_DEGREE_CERT_CODE between", value1, value2, "awardDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andAwardDegreeCertCodeNotBetween(String value1, String value2) {
            addCriterion("AWARD_DEGREE_CERT_CODE not between", value1, value2, "awardDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andPaperTitleIsNull() {
            addCriterion("PAPER_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andPaperTitleIsNotNull() {
            addCriterion("PAPER_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andPaperTitleEqualTo(String value) {
            addCriterion("PAPER_TITLE =", value, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleNotEqualTo(String value) {
            addCriterion("PAPER_TITLE <>", value, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleGreaterThan(String value) {
            addCriterion("PAPER_TITLE >", value, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER_TITLE >=", value, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleLessThan(String value) {
            addCriterion("PAPER_TITLE <", value, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleLessThanOrEqualTo(String value) {
            addCriterion("PAPER_TITLE <=", value, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleLike(String value) {
            addCriterion("PAPER_TITLE like", value, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleNotLike(String value) {
            addCriterion("PAPER_TITLE not like", value, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleIn(List<String> values) {
            addCriterion("PAPER_TITLE in", values, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleNotIn(List<String> values) {
            addCriterion("PAPER_TITLE not in", values, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleBetween(String value1, String value2) {
            addCriterion("PAPER_TITLE between", value1, value2, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleNotBetween(String value1, String value2) {
            addCriterion("PAPER_TITLE not between", value1, value2, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperKeyIsNull() {
            addCriterion("PAPER_KEY is null");
            return (Criteria) this;
        }

        public Criteria andPaperKeyIsNotNull() {
            addCriterion("PAPER_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andPaperKeyEqualTo(String value) {
            addCriterion("PAPER_KEY =", value, "paperKey");
            return (Criteria) this;
        }

        public Criteria andPaperKeyNotEqualTo(String value) {
            addCriterion("PAPER_KEY <>", value, "paperKey");
            return (Criteria) this;
        }

        public Criteria andPaperKeyGreaterThan(String value) {
            addCriterion("PAPER_KEY >", value, "paperKey");
            return (Criteria) this;
        }

        public Criteria andPaperKeyGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER_KEY >=", value, "paperKey");
            return (Criteria) this;
        }

        public Criteria andPaperKeyLessThan(String value) {
            addCriterion("PAPER_KEY <", value, "paperKey");
            return (Criteria) this;
        }

        public Criteria andPaperKeyLessThanOrEqualTo(String value) {
            addCriterion("PAPER_KEY <=", value, "paperKey");
            return (Criteria) this;
        }

        public Criteria andPaperKeyLike(String value) {
            addCriterion("PAPER_KEY like", value, "paperKey");
            return (Criteria) this;
        }

        public Criteria andPaperKeyNotLike(String value) {
            addCriterion("PAPER_KEY not like", value, "paperKey");
            return (Criteria) this;
        }

        public Criteria andPaperKeyIn(List<String> values) {
            addCriterion("PAPER_KEY in", values, "paperKey");
            return (Criteria) this;
        }

        public Criteria andPaperKeyNotIn(List<String> values) {
            addCriterion("PAPER_KEY not in", values, "paperKey");
            return (Criteria) this;
        }

        public Criteria andPaperKeyBetween(String value1, String value2) {
            addCriterion("PAPER_KEY between", value1, value2, "paperKey");
            return (Criteria) this;
        }

        public Criteria andPaperKeyNotBetween(String value1, String value2) {
            addCriterion("PAPER_KEY not between", value1, value2, "paperKey");
            return (Criteria) this;
        }

        public Criteria andPaperTypeIsNull() {
            addCriterion("PAPER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPaperTypeIsNotNull() {
            addCriterion("PAPER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPaperTypeEqualTo(String value) {
            addCriterion("PAPER_TYPE =", value, "paperType");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNotEqualTo(String value) {
            addCriterion("PAPER_TYPE <>", value, "paperType");
            return (Criteria) this;
        }

        public Criteria andPaperTypeGreaterThan(String value) {
            addCriterion("PAPER_TYPE >", value, "paperType");
            return (Criteria) this;
        }

        public Criteria andPaperTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER_TYPE >=", value, "paperType");
            return (Criteria) this;
        }

        public Criteria andPaperTypeLessThan(String value) {
            addCriterion("PAPER_TYPE <", value, "paperType");
            return (Criteria) this;
        }

        public Criteria andPaperTypeLessThanOrEqualTo(String value) {
            addCriterion("PAPER_TYPE <=", value, "paperType");
            return (Criteria) this;
        }

        public Criteria andPaperTypeLike(String value) {
            addCriterion("PAPER_TYPE like", value, "paperType");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNotLike(String value) {
            addCriterion("PAPER_TYPE not like", value, "paperType");
            return (Criteria) this;
        }

        public Criteria andPaperTypeIn(List<String> values) {
            addCriterion("PAPER_TYPE in", values, "paperType");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNotIn(List<String> values) {
            addCriterion("PAPER_TYPE not in", values, "paperType");
            return (Criteria) this;
        }

        public Criteria andPaperTypeBetween(String value1, String value2) {
            addCriterion("PAPER_TYPE between", value1, value2, "paperType");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNotBetween(String value1, String value2) {
            addCriterion("PAPER_TYPE not between", value1, value2, "paperType");
            return (Criteria) this;
        }

        public Criteria andPaperSourceIsNull() {
            addCriterion("PAPER_SOURCE is null");
            return (Criteria) this;
        }

        public Criteria andPaperSourceIsNotNull() {
            addCriterion("PAPER_SOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andPaperSourceEqualTo(String value) {
            addCriterion("PAPER_SOURCE =", value, "paperSource");
            return (Criteria) this;
        }

        public Criteria andPaperSourceNotEqualTo(String value) {
            addCriterion("PAPER_SOURCE <>", value, "paperSource");
            return (Criteria) this;
        }

        public Criteria andPaperSourceGreaterThan(String value) {
            addCriterion("PAPER_SOURCE >", value, "paperSource");
            return (Criteria) this;
        }

        public Criteria andPaperSourceGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER_SOURCE >=", value, "paperSource");
            return (Criteria) this;
        }

        public Criteria andPaperSourceLessThan(String value) {
            addCriterion("PAPER_SOURCE <", value, "paperSource");
            return (Criteria) this;
        }

        public Criteria andPaperSourceLessThanOrEqualTo(String value) {
            addCriterion("PAPER_SOURCE <=", value, "paperSource");
            return (Criteria) this;
        }

        public Criteria andPaperSourceLike(String value) {
            addCriterion("PAPER_SOURCE like", value, "paperSource");
            return (Criteria) this;
        }

        public Criteria andPaperSourceNotLike(String value) {
            addCriterion("PAPER_SOURCE not like", value, "paperSource");
            return (Criteria) this;
        }

        public Criteria andPaperSourceIn(List<String> values) {
            addCriterion("PAPER_SOURCE in", values, "paperSource");
            return (Criteria) this;
        }

        public Criteria andPaperSourceNotIn(List<String> values) {
            addCriterion("PAPER_SOURCE not in", values, "paperSource");
            return (Criteria) this;
        }

        public Criteria andPaperSourceBetween(String value1, String value2) {
            addCriterion("PAPER_SOURCE between", value1, value2, "paperSource");
            return (Criteria) this;
        }

        public Criteria andPaperSourceNotBetween(String value1, String value2) {
            addCriterion("PAPER_SOURCE not between", value1, value2, "paperSource");
            return (Criteria) this;
        }

        public Criteria andUnderMajorIsNull() {
            addCriterion("UNDER_MAJOR is null");
            return (Criteria) this;
        }

        public Criteria andUnderMajorIsNotNull() {
            addCriterion("UNDER_MAJOR is not null");
            return (Criteria) this;
        }

        public Criteria andUnderMajorEqualTo(String value) {
            addCriterion("UNDER_MAJOR =", value, "underMajor");
            return (Criteria) this;
        }

        public Criteria andUnderMajorNotEqualTo(String value) {
            addCriterion("UNDER_MAJOR <>", value, "underMajor");
            return (Criteria) this;
        }

        public Criteria andUnderMajorGreaterThan(String value) {
            addCriterion("UNDER_MAJOR >", value, "underMajor");
            return (Criteria) this;
        }

        public Criteria andUnderMajorGreaterThanOrEqualTo(String value) {
            addCriterion("UNDER_MAJOR >=", value, "underMajor");
            return (Criteria) this;
        }

        public Criteria andUnderMajorLessThan(String value) {
            addCriterion("UNDER_MAJOR <", value, "underMajor");
            return (Criteria) this;
        }

        public Criteria andUnderMajorLessThanOrEqualTo(String value) {
            addCriterion("UNDER_MAJOR <=", value, "underMajor");
            return (Criteria) this;
        }

        public Criteria andUnderMajorLike(String value) {
            addCriterion("UNDER_MAJOR like", value, "underMajor");
            return (Criteria) this;
        }

        public Criteria andUnderMajorNotLike(String value) {
            addCriterion("UNDER_MAJOR not like", value, "underMajor");
            return (Criteria) this;
        }

        public Criteria andUnderMajorIn(List<String> values) {
            addCriterion("UNDER_MAJOR in", values, "underMajor");
            return (Criteria) this;
        }

        public Criteria andUnderMajorNotIn(List<String> values) {
            addCriterion("UNDER_MAJOR not in", values, "underMajor");
            return (Criteria) this;
        }

        public Criteria andUnderMajorBetween(String value1, String value2) {
            addCriterion("UNDER_MAJOR between", value1, value2, "underMajor");
            return (Criteria) this;
        }

        public Criteria andUnderMajorNotBetween(String value1, String value2) {
            addCriterion("UNDER_MAJOR not between", value1, value2, "underMajor");
            return (Criteria) this;
        }

        public Criteria andGotBachelorCertSubjectIsNull() {
            addCriterion("GOT_BACHELOR_CERT_SUBJECT is null");
            return (Criteria) this;
        }

        public Criteria andGotBachelorCertSubjectIsNotNull() {
            addCriterion("GOT_BACHELOR_CERT_SUBJECT is not null");
            return (Criteria) this;
        }

        public Criteria andGotBachelorCertSubjectEqualTo(String value) {
            addCriterion("GOT_BACHELOR_CERT_SUBJECT =", value, "gotBachelorCertSubject");
            return (Criteria) this;
        }

        public Criteria andGotBachelorCertSubjectNotEqualTo(String value) {
            addCriterion("GOT_BACHELOR_CERT_SUBJECT <>", value, "gotBachelorCertSubject");
            return (Criteria) this;
        }

        public Criteria andGotBachelorCertSubjectGreaterThan(String value) {
            addCriterion("GOT_BACHELOR_CERT_SUBJECT >", value, "gotBachelorCertSubject");
            return (Criteria) this;
        }

        public Criteria andGotBachelorCertSubjectGreaterThanOrEqualTo(String value) {
            addCriterion("GOT_BACHELOR_CERT_SUBJECT >=", value, "gotBachelorCertSubject");
            return (Criteria) this;
        }

        public Criteria andGotBachelorCertSubjectLessThan(String value) {
            addCriterion("GOT_BACHELOR_CERT_SUBJECT <", value, "gotBachelorCertSubject");
            return (Criteria) this;
        }

        public Criteria andGotBachelorCertSubjectLessThanOrEqualTo(String value) {
            addCriterion("GOT_BACHELOR_CERT_SUBJECT <=", value, "gotBachelorCertSubject");
            return (Criteria) this;
        }

        public Criteria andGotBachelorCertSubjectLike(String value) {
            addCriterion("GOT_BACHELOR_CERT_SUBJECT like", value, "gotBachelorCertSubject");
            return (Criteria) this;
        }

        public Criteria andGotBachelorCertSubjectNotLike(String value) {
            addCriterion("GOT_BACHELOR_CERT_SUBJECT not like", value, "gotBachelorCertSubject");
            return (Criteria) this;
        }

        public Criteria andGotBachelorCertSubjectIn(List<String> values) {
            addCriterion("GOT_BACHELOR_CERT_SUBJECT in", values, "gotBachelorCertSubject");
            return (Criteria) this;
        }

        public Criteria andGotBachelorCertSubjectNotIn(List<String> values) {
            addCriterion("GOT_BACHELOR_CERT_SUBJECT not in", values, "gotBachelorCertSubject");
            return (Criteria) this;
        }

        public Criteria andGotBachelorCertSubjectBetween(String value1, String value2) {
            addCriterion("GOT_BACHELOR_CERT_SUBJECT between", value1, value2, "gotBachelorCertSubject");
            return (Criteria) this;
        }

        public Criteria andGotBachelorCertSubjectNotBetween(String value1, String value2) {
            addCriterion("GOT_BACHELOR_CERT_SUBJECT not between", value1, value2, "gotBachelorCertSubject");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeTimeIsNull() {
            addCriterion("UNDER_AWARD_DEGREE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeTimeIsNotNull() {
            addCriterion("UNDER_AWARD_DEGREE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeTimeEqualTo(String value) {
            addCriterion("UNDER_AWARD_DEGREE_TIME =", value, "underAwardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeTimeNotEqualTo(String value) {
            addCriterion("UNDER_AWARD_DEGREE_TIME <>", value, "underAwardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeTimeGreaterThan(String value) {
            addCriterion("UNDER_AWARD_DEGREE_TIME >", value, "underAwardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeTimeGreaterThanOrEqualTo(String value) {
            addCriterion("UNDER_AWARD_DEGREE_TIME >=", value, "underAwardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeTimeLessThan(String value) {
            addCriterion("UNDER_AWARD_DEGREE_TIME <", value, "underAwardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeTimeLessThanOrEqualTo(String value) {
            addCriterion("UNDER_AWARD_DEGREE_TIME <=", value, "underAwardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeTimeLike(String value) {
            addCriterion("UNDER_AWARD_DEGREE_TIME like", value, "underAwardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeTimeNotLike(String value) {
            addCriterion("UNDER_AWARD_DEGREE_TIME not like", value, "underAwardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeTimeIn(List<String> values) {
            addCriterion("UNDER_AWARD_DEGREE_TIME in", values, "underAwardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeTimeNotIn(List<String> values) {
            addCriterion("UNDER_AWARD_DEGREE_TIME not in", values, "underAwardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeTimeBetween(String value1, String value2) {
            addCriterion("UNDER_AWARD_DEGREE_TIME between", value1, value2, "underAwardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeTimeNotBetween(String value1, String value2) {
            addCriterion("UNDER_AWARD_DEGREE_TIME not between", value1, value2, "underAwardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andUnderDegreeCertCodeIsNull() {
            addCriterion("UNDER_DEGREE_CERT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andUnderDegreeCertCodeIsNotNull() {
            addCriterion("UNDER_DEGREE_CERT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andUnderDegreeCertCodeEqualTo(String value) {
            addCriterion("UNDER_DEGREE_CERT_CODE =", value, "underDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andUnderDegreeCertCodeNotEqualTo(String value) {
            addCriterion("UNDER_DEGREE_CERT_CODE <>", value, "underDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andUnderDegreeCertCodeGreaterThan(String value) {
            addCriterion("UNDER_DEGREE_CERT_CODE >", value, "underDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andUnderDegreeCertCodeGreaterThanOrEqualTo(String value) {
            addCriterion("UNDER_DEGREE_CERT_CODE >=", value, "underDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andUnderDegreeCertCodeLessThan(String value) {
            addCriterion("UNDER_DEGREE_CERT_CODE <", value, "underDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andUnderDegreeCertCodeLessThanOrEqualTo(String value) {
            addCriterion("UNDER_DEGREE_CERT_CODE <=", value, "underDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andUnderDegreeCertCodeLike(String value) {
            addCriterion("UNDER_DEGREE_CERT_CODE like", value, "underDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andUnderDegreeCertCodeNotLike(String value) {
            addCriterion("UNDER_DEGREE_CERT_CODE not like", value, "underDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andUnderDegreeCertCodeIn(List<String> values) {
            addCriterion("UNDER_DEGREE_CERT_CODE in", values, "underDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andUnderDegreeCertCodeNotIn(List<String> values) {
            addCriterion("UNDER_DEGREE_CERT_CODE not in", values, "underDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andUnderDegreeCertCodeBetween(String value1, String value2) {
            addCriterion("UNDER_DEGREE_CERT_CODE between", value1, value2, "underDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andUnderDegreeCertCodeNotBetween(String value1, String value2) {
            addCriterion("UNDER_DEGREE_CERT_CODE not between", value1, value2, "underDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeOrgIsNull() {
            addCriterion("UNDER_AWARD_DEGREE_ORG is null");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeOrgIsNotNull() {
            addCriterion("UNDER_AWARD_DEGREE_ORG is not null");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeOrgEqualTo(String value) {
            addCriterion("UNDER_AWARD_DEGREE_ORG =", value, "underAwardDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeOrgNotEqualTo(String value) {
            addCriterion("UNDER_AWARD_DEGREE_ORG <>", value, "underAwardDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeOrgGreaterThan(String value) {
            addCriterion("UNDER_AWARD_DEGREE_ORG >", value, "underAwardDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeOrgGreaterThanOrEqualTo(String value) {
            addCriterion("UNDER_AWARD_DEGREE_ORG >=", value, "underAwardDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeOrgLessThan(String value) {
            addCriterion("UNDER_AWARD_DEGREE_ORG <", value, "underAwardDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeOrgLessThanOrEqualTo(String value) {
            addCriterion("UNDER_AWARD_DEGREE_ORG <=", value, "underAwardDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeOrgLike(String value) {
            addCriterion("UNDER_AWARD_DEGREE_ORG like", value, "underAwardDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeOrgNotLike(String value) {
            addCriterion("UNDER_AWARD_DEGREE_ORG not like", value, "underAwardDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeOrgIn(List<String> values) {
            addCriterion("UNDER_AWARD_DEGREE_ORG in", values, "underAwardDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeOrgNotIn(List<String> values) {
            addCriterion("UNDER_AWARD_DEGREE_ORG not in", values, "underAwardDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeOrgBetween(String value1, String value2) {
            addCriterion("UNDER_AWARD_DEGREE_ORG between", value1, value2, "underAwardDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andUnderAwardDegreeOrgNotBetween(String value1, String value2) {
            addCriterion("UNDER_AWARD_DEGREE_ORG not between", value1, value2, "underAwardDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andGotMasterCertSpeIsNull() {
            addCriterion("GOT_MASTER_CERT_SPE is null");
            return (Criteria) this;
        }

        public Criteria andGotMasterCertSpeIsNotNull() {
            addCriterion("GOT_MASTER_CERT_SPE is not null");
            return (Criteria) this;
        }

        public Criteria andGotMasterCertSpeEqualTo(String value) {
            addCriterion("GOT_MASTER_CERT_SPE =", value, "gotMasterCertSpe");
            return (Criteria) this;
        }

        public Criteria andGotMasterCertSpeNotEqualTo(String value) {
            addCriterion("GOT_MASTER_CERT_SPE <>", value, "gotMasterCertSpe");
            return (Criteria) this;
        }

        public Criteria andGotMasterCertSpeGreaterThan(String value) {
            addCriterion("GOT_MASTER_CERT_SPE >", value, "gotMasterCertSpe");
            return (Criteria) this;
        }

        public Criteria andGotMasterCertSpeGreaterThanOrEqualTo(String value) {
            addCriterion("GOT_MASTER_CERT_SPE >=", value, "gotMasterCertSpe");
            return (Criteria) this;
        }

        public Criteria andGotMasterCertSpeLessThan(String value) {
            addCriterion("GOT_MASTER_CERT_SPE <", value, "gotMasterCertSpe");
            return (Criteria) this;
        }

        public Criteria andGotMasterCertSpeLessThanOrEqualTo(String value) {
            addCriterion("GOT_MASTER_CERT_SPE <=", value, "gotMasterCertSpe");
            return (Criteria) this;
        }

        public Criteria andGotMasterCertSpeLike(String value) {
            addCriterion("GOT_MASTER_CERT_SPE like", value, "gotMasterCertSpe");
            return (Criteria) this;
        }

        public Criteria andGotMasterCertSpeNotLike(String value) {
            addCriterion("GOT_MASTER_CERT_SPE not like", value, "gotMasterCertSpe");
            return (Criteria) this;
        }

        public Criteria andGotMasterCertSpeIn(List<String> values) {
            addCriterion("GOT_MASTER_CERT_SPE in", values, "gotMasterCertSpe");
            return (Criteria) this;
        }

        public Criteria andGotMasterCertSpeNotIn(List<String> values) {
            addCriterion("GOT_MASTER_CERT_SPE not in", values, "gotMasterCertSpe");
            return (Criteria) this;
        }

        public Criteria andGotMasterCertSpeBetween(String value1, String value2) {
            addCriterion("GOT_MASTER_CERT_SPE between", value1, value2, "gotMasterCertSpe");
            return (Criteria) this;
        }

        public Criteria andGotMasterCertSpeNotBetween(String value1, String value2) {
            addCriterion("GOT_MASTER_CERT_SPE not between", value1, value2, "gotMasterCertSpe");
            return (Criteria) this;
        }

        public Criteria andMasterSubjectIsNull() {
            addCriterion("MASTER_SUBJECT is null");
            return (Criteria) this;
        }

        public Criteria andMasterSubjectIsNotNull() {
            addCriterion("MASTER_SUBJECT is not null");
            return (Criteria) this;
        }

        public Criteria andMasterSubjectEqualTo(String value) {
            addCriterion("MASTER_SUBJECT =", value, "masterSubject");
            return (Criteria) this;
        }

        public Criteria andMasterSubjectNotEqualTo(String value) {
            addCriterion("MASTER_SUBJECT <>", value, "masterSubject");
            return (Criteria) this;
        }

        public Criteria andMasterSubjectGreaterThan(String value) {
            addCriterion("MASTER_SUBJECT >", value, "masterSubject");
            return (Criteria) this;
        }

        public Criteria andMasterSubjectGreaterThanOrEqualTo(String value) {
            addCriterion("MASTER_SUBJECT >=", value, "masterSubject");
            return (Criteria) this;
        }

        public Criteria andMasterSubjectLessThan(String value) {
            addCriterion("MASTER_SUBJECT <", value, "masterSubject");
            return (Criteria) this;
        }

        public Criteria andMasterSubjectLessThanOrEqualTo(String value) {
            addCriterion("MASTER_SUBJECT <=", value, "masterSubject");
            return (Criteria) this;
        }

        public Criteria andMasterSubjectLike(String value) {
            addCriterion("MASTER_SUBJECT like", value, "masterSubject");
            return (Criteria) this;
        }

        public Criteria andMasterSubjectNotLike(String value) {
            addCriterion("MASTER_SUBJECT not like", value, "masterSubject");
            return (Criteria) this;
        }

        public Criteria andMasterSubjectIn(List<String> values) {
            addCriterion("MASTER_SUBJECT in", values, "masterSubject");
            return (Criteria) this;
        }

        public Criteria andMasterSubjectNotIn(List<String> values) {
            addCriterion("MASTER_SUBJECT not in", values, "masterSubject");
            return (Criteria) this;
        }

        public Criteria andMasterSubjectBetween(String value1, String value2) {
            addCriterion("MASTER_SUBJECT between", value1, value2, "masterSubject");
            return (Criteria) this;
        }

        public Criteria andMasterSubjectNotBetween(String value1, String value2) {
            addCriterion("MASTER_SUBJECT not between", value1, value2, "masterSubject");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeTimeIsNull() {
            addCriterion("MASTER_AWARD_DEGREE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeTimeIsNotNull() {
            addCriterion("MASTER_AWARD_DEGREE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeTimeEqualTo(String value) {
            addCriterion("MASTER_AWARD_DEGREE_TIME =", value, "masterAwardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeTimeNotEqualTo(String value) {
            addCriterion("MASTER_AWARD_DEGREE_TIME <>", value, "masterAwardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeTimeGreaterThan(String value) {
            addCriterion("MASTER_AWARD_DEGREE_TIME >", value, "masterAwardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeTimeGreaterThanOrEqualTo(String value) {
            addCriterion("MASTER_AWARD_DEGREE_TIME >=", value, "masterAwardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeTimeLessThan(String value) {
            addCriterion("MASTER_AWARD_DEGREE_TIME <", value, "masterAwardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeTimeLessThanOrEqualTo(String value) {
            addCriterion("MASTER_AWARD_DEGREE_TIME <=", value, "masterAwardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeTimeLike(String value) {
            addCriterion("MASTER_AWARD_DEGREE_TIME like", value, "masterAwardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeTimeNotLike(String value) {
            addCriterion("MASTER_AWARD_DEGREE_TIME not like", value, "masterAwardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeTimeIn(List<String> values) {
            addCriterion("MASTER_AWARD_DEGREE_TIME in", values, "masterAwardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeTimeNotIn(List<String> values) {
            addCriterion("MASTER_AWARD_DEGREE_TIME not in", values, "masterAwardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeTimeBetween(String value1, String value2) {
            addCriterion("MASTER_AWARD_DEGREE_TIME between", value1, value2, "masterAwardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeTimeNotBetween(String value1, String value2) {
            addCriterion("MASTER_AWARD_DEGREE_TIME not between", value1, value2, "masterAwardDegreeTime");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeCertCodeIsNull() {
            addCriterion("MASTER_DEGREE_CERT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeCertCodeIsNotNull() {
            addCriterion("MASTER_DEGREE_CERT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeCertCodeEqualTo(String value) {
            addCriterion("MASTER_DEGREE_CERT_CODE =", value, "masterDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeCertCodeNotEqualTo(String value) {
            addCriterion("MASTER_DEGREE_CERT_CODE <>", value, "masterDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeCertCodeGreaterThan(String value) {
            addCriterion("MASTER_DEGREE_CERT_CODE >", value, "masterDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeCertCodeGreaterThanOrEqualTo(String value) {
            addCriterion("MASTER_DEGREE_CERT_CODE >=", value, "masterDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeCertCodeLessThan(String value) {
            addCriterion("MASTER_DEGREE_CERT_CODE <", value, "masterDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeCertCodeLessThanOrEqualTo(String value) {
            addCriterion("MASTER_DEGREE_CERT_CODE <=", value, "masterDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeCertCodeLike(String value) {
            addCriterion("MASTER_DEGREE_CERT_CODE like", value, "masterDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeCertCodeNotLike(String value) {
            addCriterion("MASTER_DEGREE_CERT_CODE not like", value, "masterDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeCertCodeIn(List<String> values) {
            addCriterion("MASTER_DEGREE_CERT_CODE in", values, "masterDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeCertCodeNotIn(List<String> values) {
            addCriterion("MASTER_DEGREE_CERT_CODE not in", values, "masterDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeCertCodeBetween(String value1, String value2) {
            addCriterion("MASTER_DEGREE_CERT_CODE between", value1, value2, "masterDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeCertCodeNotBetween(String value1, String value2) {
            addCriterion("MASTER_DEGREE_CERT_CODE not between", value1, value2, "masterDegreeCertCode");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeOrgIsNull() {
            addCriterion("MASTER_AWARD_DEGREE_ORG is null");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeOrgIsNotNull() {
            addCriterion("MASTER_AWARD_DEGREE_ORG is not null");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeOrgEqualTo(String value) {
            addCriterion("MASTER_AWARD_DEGREE_ORG =", value, "masterAwardDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeOrgNotEqualTo(String value) {
            addCriterion("MASTER_AWARD_DEGREE_ORG <>", value, "masterAwardDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeOrgGreaterThan(String value) {
            addCriterion("MASTER_AWARD_DEGREE_ORG >", value, "masterAwardDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeOrgGreaterThanOrEqualTo(String value) {
            addCriterion("MASTER_AWARD_DEGREE_ORG >=", value, "masterAwardDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeOrgLessThan(String value) {
            addCriterion("MASTER_AWARD_DEGREE_ORG <", value, "masterAwardDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeOrgLessThanOrEqualTo(String value) {
            addCriterion("MASTER_AWARD_DEGREE_ORG <=", value, "masterAwardDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeOrgLike(String value) {
            addCriterion("MASTER_AWARD_DEGREE_ORG like", value, "masterAwardDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeOrgNotLike(String value) {
            addCriterion("MASTER_AWARD_DEGREE_ORG not like", value, "masterAwardDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeOrgIn(List<String> values) {
            addCriterion("MASTER_AWARD_DEGREE_ORG in", values, "masterAwardDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeOrgNotIn(List<String> values) {
            addCriterion("MASTER_AWARD_DEGREE_ORG not in", values, "masterAwardDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeOrgBetween(String value1, String value2) {
            addCriterion("MASTER_AWARD_DEGREE_ORG between", value1, value2, "masterAwardDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andMasterAwardDegreeOrgNotBetween(String value1, String value2) {
            addCriterion("MASTER_AWARD_DEGREE_ORG not between", value1, value2, "masterAwardDegreeOrg");
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

        public Criteria andDegreeDirectionIsNull() {
            addCriterion("DEGREE_DIRECTION is null");
            return (Criteria) this;
        }

        public Criteria andDegreeDirectionIsNotNull() {
            addCriterion("DEGREE_DIRECTION is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeDirectionEqualTo(String value) {
            addCriterion("DEGREE_DIRECTION =", value, "degreeDirection");
            return (Criteria) this;
        }

        public Criteria andDegreeDirectionNotEqualTo(String value) {
            addCriterion("DEGREE_DIRECTION <>", value, "degreeDirection");
            return (Criteria) this;
        }

        public Criteria andDegreeDirectionGreaterThan(String value) {
            addCriterion("DEGREE_DIRECTION >", value, "degreeDirection");
            return (Criteria) this;
        }

        public Criteria andDegreeDirectionGreaterThanOrEqualTo(String value) {
            addCriterion("DEGREE_DIRECTION >=", value, "degreeDirection");
            return (Criteria) this;
        }

        public Criteria andDegreeDirectionLessThan(String value) {
            addCriterion("DEGREE_DIRECTION <", value, "degreeDirection");
            return (Criteria) this;
        }

        public Criteria andDegreeDirectionLessThanOrEqualTo(String value) {
            addCriterion("DEGREE_DIRECTION <=", value, "degreeDirection");
            return (Criteria) this;
        }

        public Criteria andDegreeDirectionLike(String value) {
            addCriterion("DEGREE_DIRECTION like", value, "degreeDirection");
            return (Criteria) this;
        }

        public Criteria andDegreeDirectionNotLike(String value) {
            addCriterion("DEGREE_DIRECTION not like", value, "degreeDirection");
            return (Criteria) this;
        }

        public Criteria andDegreeDirectionIn(List<String> values) {
            addCriterion("DEGREE_DIRECTION in", values, "degreeDirection");
            return (Criteria) this;
        }

        public Criteria andDegreeDirectionNotIn(List<String> values) {
            addCriterion("DEGREE_DIRECTION not in", values, "degreeDirection");
            return (Criteria) this;
        }

        public Criteria andDegreeDirectionBetween(String value1, String value2) {
            addCriterion("DEGREE_DIRECTION between", value1, value2, "degreeDirection");
            return (Criteria) this;
        }

        public Criteria andDegreeDirectionNotBetween(String value1, String value2) {
            addCriterion("DEGREE_DIRECTION not between", value1, value2, "degreeDirection");
            return (Criteria) this;
        }

        public Criteria andWorkNatureIsNull() {
            addCriterion("WORK_NATURE is null");
            return (Criteria) this;
        }

        public Criteria andWorkNatureIsNotNull() {
            addCriterion("WORK_NATURE is not null");
            return (Criteria) this;
        }

        public Criteria andWorkNatureEqualTo(String value) {
            addCriterion("WORK_NATURE =", value, "workNature");
            return (Criteria) this;
        }

        public Criteria andWorkNatureNotEqualTo(String value) {
            addCriterion("WORK_NATURE <>", value, "workNature");
            return (Criteria) this;
        }

        public Criteria andWorkNatureGreaterThan(String value) {
            addCriterion("WORK_NATURE >", value, "workNature");
            return (Criteria) this;
        }

        public Criteria andWorkNatureGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_NATURE >=", value, "workNature");
            return (Criteria) this;
        }

        public Criteria andWorkNatureLessThan(String value) {
            addCriterion("WORK_NATURE <", value, "workNature");
            return (Criteria) this;
        }

        public Criteria andWorkNatureLessThanOrEqualTo(String value) {
            addCriterion("WORK_NATURE <=", value, "workNature");
            return (Criteria) this;
        }

        public Criteria andWorkNatureLike(String value) {
            addCriterion("WORK_NATURE like", value, "workNature");
            return (Criteria) this;
        }

        public Criteria andWorkNatureNotLike(String value) {
            addCriterion("WORK_NATURE not like", value, "workNature");
            return (Criteria) this;
        }

        public Criteria andWorkNatureIn(List<String> values) {
            addCriterion("WORK_NATURE in", values, "workNature");
            return (Criteria) this;
        }

        public Criteria andWorkNatureNotIn(List<String> values) {
            addCriterion("WORK_NATURE not in", values, "workNature");
            return (Criteria) this;
        }

        public Criteria andWorkNatureBetween(String value1, String value2) {
            addCriterion("WORK_NATURE between", value1, value2, "workNature");
            return (Criteria) this;
        }

        public Criteria andWorkNatureNotBetween(String value1, String value2) {
            addCriterion("WORK_NATURE not between", value1, value2, "workNature");
            return (Criteria) this;
        }

        public Criteria andUnitNameIsNull() {
            addCriterion("UNIT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUnitNameIsNotNull() {
            addCriterion("UNIT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUnitNameEqualTo(String value) {
            addCriterion("UNIT_NAME =", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameNotEqualTo(String value) {
            addCriterion("UNIT_NAME <>", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameGreaterThan(String value) {
            addCriterion("UNIT_NAME >", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameGreaterThanOrEqualTo(String value) {
            addCriterion("UNIT_NAME >=", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameLessThan(String value) {
            addCriterion("UNIT_NAME <", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameLessThanOrEqualTo(String value) {
            addCriterion("UNIT_NAME <=", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameLike(String value) {
            addCriterion("UNIT_NAME like", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameNotLike(String value) {
            addCriterion("UNIT_NAME not like", value, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameIn(List<String> values) {
            addCriterion("UNIT_NAME in", values, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameNotIn(List<String> values) {
            addCriterion("UNIT_NAME not in", values, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameBetween(String value1, String value2) {
            addCriterion("UNIT_NAME between", value1, value2, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNameNotBetween(String value1, String value2) {
            addCriterion("UNIT_NAME not between", value1, value2, "unitName");
            return (Criteria) this;
        }

        public Criteria andUnitNatureIsNull() {
            addCriterion("UNIT_NATURE is null");
            return (Criteria) this;
        }

        public Criteria andUnitNatureIsNotNull() {
            addCriterion("UNIT_NATURE is not null");
            return (Criteria) this;
        }

        public Criteria andUnitNatureEqualTo(String value) {
            addCriterion("UNIT_NATURE =", value, "unitNature");
            return (Criteria) this;
        }

        public Criteria andUnitNatureNotEqualTo(String value) {
            addCriterion("UNIT_NATURE <>", value, "unitNature");
            return (Criteria) this;
        }

        public Criteria andUnitNatureGreaterThan(String value) {
            addCriterion("UNIT_NATURE >", value, "unitNature");
            return (Criteria) this;
        }

        public Criteria andUnitNatureGreaterThanOrEqualTo(String value) {
            addCriterion("UNIT_NATURE >=", value, "unitNature");
            return (Criteria) this;
        }

        public Criteria andUnitNatureLessThan(String value) {
            addCriterion("UNIT_NATURE <", value, "unitNature");
            return (Criteria) this;
        }

        public Criteria andUnitNatureLessThanOrEqualTo(String value) {
            addCriterion("UNIT_NATURE <=", value, "unitNature");
            return (Criteria) this;
        }

        public Criteria andUnitNatureLike(String value) {
            addCriterion("UNIT_NATURE like", value, "unitNature");
            return (Criteria) this;
        }

        public Criteria andUnitNatureNotLike(String value) {
            addCriterion("UNIT_NATURE not like", value, "unitNature");
            return (Criteria) this;
        }

        public Criteria andUnitNatureIn(List<String> values) {
            addCriterion("UNIT_NATURE in", values, "unitNature");
            return (Criteria) this;
        }

        public Criteria andUnitNatureNotIn(List<String> values) {
            addCriterion("UNIT_NATURE not in", values, "unitNature");
            return (Criteria) this;
        }

        public Criteria andUnitNatureBetween(String value1, String value2) {
            addCriterion("UNIT_NATURE between", value1, value2, "unitNature");
            return (Criteria) this;
        }

        public Criteria andUnitNatureNotBetween(String value1, String value2) {
            addCriterion("UNIT_NATURE not between", value1, value2, "unitNature");
            return (Criteria) this;
        }

        public Criteria andUnitAddressIsNull() {
            addCriterion("UNIT_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andUnitAddressIsNotNull() {
            addCriterion("UNIT_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andUnitAddressEqualTo(String value) {
            addCriterion("UNIT_ADDRESS =", value, "unitAddress");
            return (Criteria) this;
        }

        public Criteria andUnitAddressNotEqualTo(String value) {
            addCriterion("UNIT_ADDRESS <>", value, "unitAddress");
            return (Criteria) this;
        }

        public Criteria andUnitAddressGreaterThan(String value) {
            addCriterion("UNIT_ADDRESS >", value, "unitAddress");
            return (Criteria) this;
        }

        public Criteria andUnitAddressGreaterThanOrEqualTo(String value) {
            addCriterion("UNIT_ADDRESS >=", value, "unitAddress");
            return (Criteria) this;
        }

        public Criteria andUnitAddressLessThan(String value) {
            addCriterion("UNIT_ADDRESS <", value, "unitAddress");
            return (Criteria) this;
        }

        public Criteria andUnitAddressLessThanOrEqualTo(String value) {
            addCriterion("UNIT_ADDRESS <=", value, "unitAddress");
            return (Criteria) this;
        }

        public Criteria andUnitAddressLike(String value) {
            addCriterion("UNIT_ADDRESS like", value, "unitAddress");
            return (Criteria) this;
        }

        public Criteria andUnitAddressNotLike(String value) {
            addCriterion("UNIT_ADDRESS not like", value, "unitAddress");
            return (Criteria) this;
        }

        public Criteria andUnitAddressIn(List<String> values) {
            addCriterion("UNIT_ADDRESS in", values, "unitAddress");
            return (Criteria) this;
        }

        public Criteria andUnitAddressNotIn(List<String> values) {
            addCriterion("UNIT_ADDRESS not in", values, "unitAddress");
            return (Criteria) this;
        }

        public Criteria andUnitAddressBetween(String value1, String value2) {
            addCriterion("UNIT_ADDRESS between", value1, value2, "unitAddress");
            return (Criteria) this;
        }

        public Criteria andUnitAddressNotBetween(String value1, String value2) {
            addCriterion("UNIT_ADDRESS not between", value1, value2, "unitAddress");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNull() {
            addCriterion("USER_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNotNull() {
            addCriterion("USER_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneEqualTo(String value) {
            addCriterion("USER_PHONE =", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotEqualTo(String value) {
            addCriterion("USER_PHONE <>", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThan(String value) {
            addCriterion("USER_PHONE >", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("USER_PHONE >=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThan(String value) {
            addCriterion("USER_PHONE <", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThanOrEqualTo(String value) {
            addCriterion("USER_PHONE <=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLike(String value) {
            addCriterion("USER_PHONE like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotLike(String value) {
            addCriterion("USER_PHONE not like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIn(List<String> values) {
            addCriterion("USER_PHONE in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotIn(List<String> values) {
            addCriterion("USER_PHONE not in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneBetween(String value1, String value2) {
            addCriterion("USER_PHONE between", value1, value2, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotBetween(String value1, String value2) {
            addCriterion("USER_PHONE not between", value1, value2, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserEmailIsNull() {
            addCriterion("USER_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andUserEmailIsNotNull() {
            addCriterion("USER_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andUserEmailEqualTo(String value) {
            addCriterion("USER_EMAIL =", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotEqualTo(String value) {
            addCriterion("USER_EMAIL <>", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailGreaterThan(String value) {
            addCriterion("USER_EMAIL >", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailGreaterThanOrEqualTo(String value) {
            addCriterion("USER_EMAIL >=", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLessThan(String value) {
            addCriterion("USER_EMAIL <", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLessThanOrEqualTo(String value) {
            addCriterion("USER_EMAIL <=", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLike(String value) {
            addCriterion("USER_EMAIL like", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotLike(String value) {
            addCriterion("USER_EMAIL not like", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailIn(List<String> values) {
            addCriterion("USER_EMAIL in", values, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotIn(List<String> values) {
            addCriterion("USER_EMAIL not in", values, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailBetween(String value1, String value2) {
            addCriterion("USER_EMAIL between", value1, value2, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotBetween(String value1, String value2) {
            addCriterion("USER_EMAIL not between", value1, value2, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserQqIsNull() {
            addCriterion("USER_QQ is null");
            return (Criteria) this;
        }

        public Criteria andUserQqIsNotNull() {
            addCriterion("USER_QQ is not null");
            return (Criteria) this;
        }

        public Criteria andUserQqEqualTo(String value) {
            addCriterion("USER_QQ =", value, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqNotEqualTo(String value) {
            addCriterion("USER_QQ <>", value, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqGreaterThan(String value) {
            addCriterion("USER_QQ >", value, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqGreaterThanOrEqualTo(String value) {
            addCriterion("USER_QQ >=", value, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqLessThan(String value) {
            addCriterion("USER_QQ <", value, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqLessThanOrEqualTo(String value) {
            addCriterion("USER_QQ <=", value, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqLike(String value) {
            addCriterion("USER_QQ like", value, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqNotLike(String value) {
            addCriterion("USER_QQ not like", value, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqIn(List<String> values) {
            addCriterion("USER_QQ in", values, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqNotIn(List<String> values) {
            addCriterion("USER_QQ not in", values, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqBetween(String value1, String value2) {
            addCriterion("USER_QQ between", value1, value2, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqNotBetween(String value1, String value2) {
            addCriterion("USER_QQ not between", value1, value2, "userQq");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameIsNull() {
            addCriterion("WEI_XIN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameIsNotNull() {
            addCriterion("WEI_XIN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameEqualTo(String value) {
            addCriterion("WEI_XIN_NAME =", value, "weiXinName");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameNotEqualTo(String value) {
            addCriterion("WEI_XIN_NAME <>", value, "weiXinName");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameGreaterThan(String value) {
            addCriterion("WEI_XIN_NAME >", value, "weiXinName");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameGreaterThanOrEqualTo(String value) {
            addCriterion("WEI_XIN_NAME >=", value, "weiXinName");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameLessThan(String value) {
            addCriterion("WEI_XIN_NAME <", value, "weiXinName");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameLessThanOrEqualTo(String value) {
            addCriterion("WEI_XIN_NAME <=", value, "weiXinName");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameLike(String value) {
            addCriterion("WEI_XIN_NAME like", value, "weiXinName");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameNotLike(String value) {
            addCriterion("WEI_XIN_NAME not like", value, "weiXinName");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameIn(List<String> values) {
            addCriterion("WEI_XIN_NAME in", values, "weiXinName");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameNotIn(List<String> values) {
            addCriterion("WEI_XIN_NAME not in", values, "weiXinName");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameBetween(String value1, String value2) {
            addCriterion("WEI_XIN_NAME between", value1, value2, "weiXinName");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameNotBetween(String value1, String value2) {
            addCriterion("WEI_XIN_NAME not between", value1, value2, "weiXinName");
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

        public Criteria andDeptFlowIsNull() {
            addCriterion("DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIsNotNull() {
            addCriterion("DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDeptFlowEqualTo(String value) {
            addCriterion("DEPT_FLOW =", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotEqualTo(String value) {
            addCriterion("DEPT_FLOW <>", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowGreaterThan(String value) {
            addCriterion("DEPT_FLOW >", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_FLOW >=", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLessThan(String value) {
            addCriterion("DEPT_FLOW <", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("DEPT_FLOW <=", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLike(String value) {
            addCriterion("DEPT_FLOW like", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotLike(String value) {
            addCriterion("DEPT_FLOW not like", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIn(List<String> values) {
            addCriterion("DEPT_FLOW in", values, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotIn(List<String> values) {
            addCriterion("DEPT_FLOW not in", values, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowBetween(String value1, String value2) {
            addCriterion("DEPT_FLOW between", value1, value2, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotBetween(String value1, String value2) {
            addCriterion("DEPT_FLOW not between", value1, value2, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNull() {
            addCriterion("DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNotNull() {
            addCriterion("DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDeptNameEqualTo(String value) {
            addCriterion("DEPT_NAME =", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotEqualTo(String value) {
            addCriterion("DEPT_NAME <>", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThan(String value) {
            addCriterion("DEPT_NAME >", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_NAME >=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThan(String value) {
            addCriterion("DEPT_NAME <", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThanOrEqualTo(String value) {
            addCriterion("DEPT_NAME <=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLike(String value) {
            addCriterion("DEPT_NAME like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotLike(String value) {
            addCriterion("DEPT_NAME not like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameIn(List<String> values) {
            addCriterion("DEPT_NAME in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotIn(List<String> values) {
            addCriterion("DEPT_NAME not in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameBetween(String value1, String value2) {
            addCriterion("DEPT_NAME between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotBetween(String value1, String value2) {
            addCriterion("DEPT_NAME not between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andPreDegreeIdIsNull() {
            addCriterion("PRE_DEGREE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPreDegreeIdIsNotNull() {
            addCriterion("PRE_DEGREE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPreDegreeIdEqualTo(String value) {
            addCriterion("PRE_DEGREE_ID =", value, "preDegreeId");
            return (Criteria) this;
        }

        public Criteria andPreDegreeIdNotEqualTo(String value) {
            addCriterion("PRE_DEGREE_ID <>", value, "preDegreeId");
            return (Criteria) this;
        }

        public Criteria andPreDegreeIdGreaterThan(String value) {
            addCriterion("PRE_DEGREE_ID >", value, "preDegreeId");
            return (Criteria) this;
        }

        public Criteria andPreDegreeIdGreaterThanOrEqualTo(String value) {
            addCriterion("PRE_DEGREE_ID >=", value, "preDegreeId");
            return (Criteria) this;
        }

        public Criteria andPreDegreeIdLessThan(String value) {
            addCriterion("PRE_DEGREE_ID <", value, "preDegreeId");
            return (Criteria) this;
        }

        public Criteria andPreDegreeIdLessThanOrEqualTo(String value) {
            addCriterion("PRE_DEGREE_ID <=", value, "preDegreeId");
            return (Criteria) this;
        }

        public Criteria andPreDegreeIdLike(String value) {
            addCriterion("PRE_DEGREE_ID like", value, "preDegreeId");
            return (Criteria) this;
        }

        public Criteria andPreDegreeIdNotLike(String value) {
            addCriterion("PRE_DEGREE_ID not like", value, "preDegreeId");
            return (Criteria) this;
        }

        public Criteria andPreDegreeIdIn(List<String> values) {
            addCriterion("PRE_DEGREE_ID in", values, "preDegreeId");
            return (Criteria) this;
        }

        public Criteria andPreDegreeIdNotIn(List<String> values) {
            addCriterion("PRE_DEGREE_ID not in", values, "preDegreeId");
            return (Criteria) this;
        }

        public Criteria andPreDegreeIdBetween(String value1, String value2) {
            addCriterion("PRE_DEGREE_ID between", value1, value2, "preDegreeId");
            return (Criteria) this;
        }

        public Criteria andPreDegreeIdNotBetween(String value1, String value2) {
            addCriterion("PRE_DEGREE_ID not between", value1, value2, "preDegreeId");
            return (Criteria) this;
        }

        public Criteria andPreDegreeNameIsNull() {
            addCriterion("PRE_DEGREE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPreDegreeNameIsNotNull() {
            addCriterion("PRE_DEGREE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPreDegreeNameEqualTo(String value) {
            addCriterion("PRE_DEGREE_NAME =", value, "preDegreeName");
            return (Criteria) this;
        }

        public Criteria andPreDegreeNameNotEqualTo(String value) {
            addCriterion("PRE_DEGREE_NAME <>", value, "preDegreeName");
            return (Criteria) this;
        }

        public Criteria andPreDegreeNameGreaterThan(String value) {
            addCriterion("PRE_DEGREE_NAME >", value, "preDegreeName");
            return (Criteria) this;
        }

        public Criteria andPreDegreeNameGreaterThanOrEqualTo(String value) {
            addCriterion("PRE_DEGREE_NAME >=", value, "preDegreeName");
            return (Criteria) this;
        }

        public Criteria andPreDegreeNameLessThan(String value) {
            addCriterion("PRE_DEGREE_NAME <", value, "preDegreeName");
            return (Criteria) this;
        }

        public Criteria andPreDegreeNameLessThanOrEqualTo(String value) {
            addCriterion("PRE_DEGREE_NAME <=", value, "preDegreeName");
            return (Criteria) this;
        }

        public Criteria andPreDegreeNameLike(String value) {
            addCriterion("PRE_DEGREE_NAME like", value, "preDegreeName");
            return (Criteria) this;
        }

        public Criteria andPreDegreeNameNotLike(String value) {
            addCriterion("PRE_DEGREE_NAME not like", value, "preDegreeName");
            return (Criteria) this;
        }

        public Criteria andPreDegreeNameIn(List<String> values) {
            addCriterion("PRE_DEGREE_NAME in", values, "preDegreeName");
            return (Criteria) this;
        }

        public Criteria andPreDegreeNameNotIn(List<String> values) {
            addCriterion("PRE_DEGREE_NAME not in", values, "preDegreeName");
            return (Criteria) this;
        }

        public Criteria andPreDegreeNameBetween(String value1, String value2) {
            addCriterion("PRE_DEGREE_NAME between", value1, value2, "preDegreeName");
            return (Criteria) this;
        }

        public Criteria andPreDegreeNameNotBetween(String value1, String value2) {
            addCriterion("PRE_DEGREE_NAME not between", value1, value2, "preDegreeName");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeTimeIsNull() {
            addCriterion("UNDER_PRE_DEGREE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeTimeIsNotNull() {
            addCriterion("UNDER_PRE_DEGREE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeTimeEqualTo(String value) {
            addCriterion("UNDER_PRE_DEGREE_TIME =", value, "underPreDegreeTime");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeTimeNotEqualTo(String value) {
            addCriterion("UNDER_PRE_DEGREE_TIME <>", value, "underPreDegreeTime");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeTimeGreaterThan(String value) {
            addCriterion("UNDER_PRE_DEGREE_TIME >", value, "underPreDegreeTime");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeTimeGreaterThanOrEqualTo(String value) {
            addCriterion("UNDER_PRE_DEGREE_TIME >=", value, "underPreDegreeTime");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeTimeLessThan(String value) {
            addCriterion("UNDER_PRE_DEGREE_TIME <", value, "underPreDegreeTime");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeTimeLessThanOrEqualTo(String value) {
            addCriterion("UNDER_PRE_DEGREE_TIME <=", value, "underPreDegreeTime");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeTimeLike(String value) {
            addCriterion("UNDER_PRE_DEGREE_TIME like", value, "underPreDegreeTime");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeTimeNotLike(String value) {
            addCriterion("UNDER_PRE_DEGREE_TIME not like", value, "underPreDegreeTime");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeTimeIn(List<String> values) {
            addCriterion("UNDER_PRE_DEGREE_TIME in", values, "underPreDegreeTime");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeTimeNotIn(List<String> values) {
            addCriterion("UNDER_PRE_DEGREE_TIME not in", values, "underPreDegreeTime");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeTimeBetween(String value1, String value2) {
            addCriterion("UNDER_PRE_DEGREE_TIME between", value1, value2, "underPreDegreeTime");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeTimeNotBetween(String value1, String value2) {
            addCriterion("UNDER_PRE_DEGREE_TIME not between", value1, value2, "underPreDegreeTime");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeOrgIsNull() {
            addCriterion("UNDER_PRE_DEGREE_ORG is null");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeOrgIsNotNull() {
            addCriterion("UNDER_PRE_DEGREE_ORG is not null");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeOrgEqualTo(String value) {
            addCriterion("UNDER_PRE_DEGREE_ORG =", value, "underPreDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeOrgNotEqualTo(String value) {
            addCriterion("UNDER_PRE_DEGREE_ORG <>", value, "underPreDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeOrgGreaterThan(String value) {
            addCriterion("UNDER_PRE_DEGREE_ORG >", value, "underPreDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeOrgGreaterThanOrEqualTo(String value) {
            addCriterion("UNDER_PRE_DEGREE_ORG >=", value, "underPreDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeOrgLessThan(String value) {
            addCriterion("UNDER_PRE_DEGREE_ORG <", value, "underPreDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeOrgLessThanOrEqualTo(String value) {
            addCriterion("UNDER_PRE_DEGREE_ORG <=", value, "underPreDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeOrgLike(String value) {
            addCriterion("UNDER_PRE_DEGREE_ORG like", value, "underPreDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeOrgNotLike(String value) {
            addCriterion("UNDER_PRE_DEGREE_ORG not like", value, "underPreDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeOrgIn(List<String> values) {
            addCriterion("UNDER_PRE_DEGREE_ORG in", values, "underPreDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeOrgNotIn(List<String> values) {
            addCriterion("UNDER_PRE_DEGREE_ORG not in", values, "underPreDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeOrgBetween(String value1, String value2) {
            addCriterion("UNDER_PRE_DEGREE_ORG between", value1, value2, "underPreDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andUnderPreDegreeOrgNotBetween(String value1, String value2) {
            addCriterion("UNDER_PRE_DEGREE_ORG not between", value1, value2, "underPreDegreeOrg");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectIdIsNull() {
            addCriterion("FIRST_LEVEL_SUBJECT_ID is null");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectIdIsNotNull() {
            addCriterion("FIRST_LEVEL_SUBJECT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectIdEqualTo(String value) {
            addCriterion("FIRST_LEVEL_SUBJECT_ID =", value, "firstLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectIdNotEqualTo(String value) {
            addCriterion("FIRST_LEVEL_SUBJECT_ID <>", value, "firstLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectIdGreaterThan(String value) {
            addCriterion("FIRST_LEVEL_SUBJECT_ID >", value, "firstLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("FIRST_LEVEL_SUBJECT_ID >=", value, "firstLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectIdLessThan(String value) {
            addCriterion("FIRST_LEVEL_SUBJECT_ID <", value, "firstLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectIdLessThanOrEqualTo(String value) {
            addCriterion("FIRST_LEVEL_SUBJECT_ID <=", value, "firstLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectIdLike(String value) {
            addCriterion("FIRST_LEVEL_SUBJECT_ID like", value, "firstLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectIdNotLike(String value) {
            addCriterion("FIRST_LEVEL_SUBJECT_ID not like", value, "firstLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectIdIn(List<String> values) {
            addCriterion("FIRST_LEVEL_SUBJECT_ID in", values, "firstLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectIdNotIn(List<String> values) {
            addCriterion("FIRST_LEVEL_SUBJECT_ID not in", values, "firstLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectIdBetween(String value1, String value2) {
            addCriterion("FIRST_LEVEL_SUBJECT_ID between", value1, value2, "firstLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectIdNotBetween(String value1, String value2) {
            addCriterion("FIRST_LEVEL_SUBJECT_ID not between", value1, value2, "firstLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectNameIsNull() {
            addCriterion("FIRST_LEVEL_SUBJECT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectNameIsNotNull() {
            addCriterion("FIRST_LEVEL_SUBJECT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectNameEqualTo(String value) {
            addCriterion("FIRST_LEVEL_SUBJECT_NAME =", value, "firstLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectNameNotEqualTo(String value) {
            addCriterion("FIRST_LEVEL_SUBJECT_NAME <>", value, "firstLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectNameGreaterThan(String value) {
            addCriterion("FIRST_LEVEL_SUBJECT_NAME >", value, "firstLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("FIRST_LEVEL_SUBJECT_NAME >=", value, "firstLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectNameLessThan(String value) {
            addCriterion("FIRST_LEVEL_SUBJECT_NAME <", value, "firstLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectNameLessThanOrEqualTo(String value) {
            addCriterion("FIRST_LEVEL_SUBJECT_NAME <=", value, "firstLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectNameLike(String value) {
            addCriterion("FIRST_LEVEL_SUBJECT_NAME like", value, "firstLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectNameNotLike(String value) {
            addCriterion("FIRST_LEVEL_SUBJECT_NAME not like", value, "firstLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectNameIn(List<String> values) {
            addCriterion("FIRST_LEVEL_SUBJECT_NAME in", values, "firstLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectNameNotIn(List<String> values) {
            addCriterion("FIRST_LEVEL_SUBJECT_NAME not in", values, "firstLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectNameBetween(String value1, String value2) {
            addCriterion("FIRST_LEVEL_SUBJECT_NAME between", value1, value2, "firstLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andFirstLevelSubjectNameNotBetween(String value1, String value2) {
            addCriterion("FIRST_LEVEL_SUBJECT_NAME not between", value1, value2, "firstLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andPreDegreeCertificateNoIsNull() {
            addCriterion("PRE_DEGREE_CERTIFICATE_NO is null");
            return (Criteria) this;
        }

        public Criteria andPreDegreeCertificateNoIsNotNull() {
            addCriterion("PRE_DEGREE_CERTIFICATE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andPreDegreeCertificateNoEqualTo(String value) {
            addCriterion("PRE_DEGREE_CERTIFICATE_NO =", value, "preDegreeCertificateNo");
            return (Criteria) this;
        }

        public Criteria andPreDegreeCertificateNoNotEqualTo(String value) {
            addCriterion("PRE_DEGREE_CERTIFICATE_NO <>", value, "preDegreeCertificateNo");
            return (Criteria) this;
        }

        public Criteria andPreDegreeCertificateNoGreaterThan(String value) {
            addCriterion("PRE_DEGREE_CERTIFICATE_NO >", value, "preDegreeCertificateNo");
            return (Criteria) this;
        }

        public Criteria andPreDegreeCertificateNoGreaterThanOrEqualTo(String value) {
            addCriterion("PRE_DEGREE_CERTIFICATE_NO >=", value, "preDegreeCertificateNo");
            return (Criteria) this;
        }

        public Criteria andPreDegreeCertificateNoLessThan(String value) {
            addCriterion("PRE_DEGREE_CERTIFICATE_NO <", value, "preDegreeCertificateNo");
            return (Criteria) this;
        }

        public Criteria andPreDegreeCertificateNoLessThanOrEqualTo(String value) {
            addCriterion("PRE_DEGREE_CERTIFICATE_NO <=", value, "preDegreeCertificateNo");
            return (Criteria) this;
        }

        public Criteria andPreDegreeCertificateNoLike(String value) {
            addCriterion("PRE_DEGREE_CERTIFICATE_NO like", value, "preDegreeCertificateNo");
            return (Criteria) this;
        }

        public Criteria andPreDegreeCertificateNoNotLike(String value) {
            addCriterion("PRE_DEGREE_CERTIFICATE_NO not like", value, "preDegreeCertificateNo");
            return (Criteria) this;
        }

        public Criteria andPreDegreeCertificateNoIn(List<String> values) {
            addCriterion("PRE_DEGREE_CERTIFICATE_NO in", values, "preDegreeCertificateNo");
            return (Criteria) this;
        }

        public Criteria andPreDegreeCertificateNoNotIn(List<String> values) {
            addCriterion("PRE_DEGREE_CERTIFICATE_NO not in", values, "preDegreeCertificateNo");
            return (Criteria) this;
        }

        public Criteria andPreDegreeCertificateNoBetween(String value1, String value2) {
            addCriterion("PRE_DEGREE_CERTIFICATE_NO between", value1, value2, "preDegreeCertificateNo");
            return (Criteria) this;
        }

        public Criteria andPreDegreeCertificateNoNotBetween(String value1, String value2) {
            addCriterion("PRE_DEGREE_CERTIFICATE_NO not between", value1, value2, "preDegreeCertificateNo");
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