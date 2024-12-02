package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class PubTrainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PubTrainExample() {
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

        public Criteria andTrainFlowIsNull() {
            addCriterion("TRAIN_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andTrainFlowIsNotNull() {
            addCriterion("TRAIN_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andTrainFlowEqualTo(String value) {
            addCriterion("TRAIN_FLOW =", value, "trainFlow");
            return (Criteria) this;
        }

        public Criteria andTrainFlowNotEqualTo(String value) {
            addCriterion("TRAIN_FLOW <>", value, "trainFlow");
            return (Criteria) this;
        }

        public Criteria andTrainFlowGreaterThan(String value) {
            addCriterion("TRAIN_FLOW >", value, "trainFlow");
            return (Criteria) this;
        }

        public Criteria andTrainFlowGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_FLOW >=", value, "trainFlow");
            return (Criteria) this;
        }

        public Criteria andTrainFlowLessThan(String value) {
            addCriterion("TRAIN_FLOW <", value, "trainFlow");
            return (Criteria) this;
        }

        public Criteria andTrainFlowLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_FLOW <=", value, "trainFlow");
            return (Criteria) this;
        }

        public Criteria andTrainFlowLike(String value) {
            addCriterion("TRAIN_FLOW like", value, "trainFlow");
            return (Criteria) this;
        }

        public Criteria andTrainFlowNotLike(String value) {
            addCriterion("TRAIN_FLOW not like", value, "trainFlow");
            return (Criteria) this;
        }

        public Criteria andTrainFlowIn(List<String> values) {
            addCriterion("TRAIN_FLOW in", values, "trainFlow");
            return (Criteria) this;
        }

        public Criteria andTrainFlowNotIn(List<String> values) {
            addCriterion("TRAIN_FLOW not in", values, "trainFlow");
            return (Criteria) this;
        }

        public Criteria andTrainFlowBetween(String value1, String value2) {
            addCriterion("TRAIN_FLOW between", value1, value2, "trainFlow");
            return (Criteria) this;
        }

        public Criteria andTrainFlowNotBetween(String value1, String value2) {
            addCriterion("TRAIN_FLOW not between", value1, value2, "trainFlow");
            return (Criteria) this;
        }

        public Criteria andTrainNameIsNull() {
            addCriterion("TRAIN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTrainNameIsNotNull() {
            addCriterion("TRAIN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTrainNameEqualTo(String value) {
            addCriterion("TRAIN_NAME =", value, "trainName");
            return (Criteria) this;
        }

        public Criteria andTrainNameNotEqualTo(String value) {
            addCriterion("TRAIN_NAME <>", value, "trainName");
            return (Criteria) this;
        }

        public Criteria andTrainNameGreaterThan(String value) {
            addCriterion("TRAIN_NAME >", value, "trainName");
            return (Criteria) this;
        }

        public Criteria andTrainNameGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_NAME >=", value, "trainName");
            return (Criteria) this;
        }

        public Criteria andTrainNameLessThan(String value) {
            addCriterion("TRAIN_NAME <", value, "trainName");
            return (Criteria) this;
        }

        public Criteria andTrainNameLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_NAME <=", value, "trainName");
            return (Criteria) this;
        }

        public Criteria andTrainNameLike(String value) {
            addCriterion("TRAIN_NAME like", value, "trainName");
            return (Criteria) this;
        }

        public Criteria andTrainNameNotLike(String value) {
            addCriterion("TRAIN_NAME not like", value, "trainName");
            return (Criteria) this;
        }

        public Criteria andTrainNameIn(List<String> values) {
            addCriterion("TRAIN_NAME in", values, "trainName");
            return (Criteria) this;
        }

        public Criteria andTrainNameNotIn(List<String> values) {
            addCriterion("TRAIN_NAME not in", values, "trainName");
            return (Criteria) this;
        }

        public Criteria andTrainNameBetween(String value1, String value2) {
            addCriterion("TRAIN_NAME between", value1, value2, "trainName");
            return (Criteria) this;
        }

        public Criteria andTrainNameNotBetween(String value1, String value2) {
            addCriterion("TRAIN_NAME not between", value1, value2, "trainName");
            return (Criteria) this;
        }

        public Criteria andTrainOrgIsNull() {
            addCriterion("TRAIN_ORG is null");
            return (Criteria) this;
        }

        public Criteria andTrainOrgIsNotNull() {
            addCriterion("TRAIN_ORG is not null");
            return (Criteria) this;
        }

        public Criteria andTrainOrgEqualTo(String value) {
            addCriterion("TRAIN_ORG =", value, "trainOrg");
            return (Criteria) this;
        }

        public Criteria andTrainOrgNotEqualTo(String value) {
            addCriterion("TRAIN_ORG <>", value, "trainOrg");
            return (Criteria) this;
        }

        public Criteria andTrainOrgGreaterThan(String value) {
            addCriterion("TRAIN_ORG >", value, "trainOrg");
            return (Criteria) this;
        }

        public Criteria andTrainOrgGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_ORG >=", value, "trainOrg");
            return (Criteria) this;
        }

        public Criteria andTrainOrgLessThan(String value) {
            addCriterion("TRAIN_ORG <", value, "trainOrg");
            return (Criteria) this;
        }

        public Criteria andTrainOrgLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_ORG <=", value, "trainOrg");
            return (Criteria) this;
        }

        public Criteria andTrainOrgLike(String value) {
            addCriterion("TRAIN_ORG like", value, "trainOrg");
            return (Criteria) this;
        }

        public Criteria andTrainOrgNotLike(String value) {
            addCriterion("TRAIN_ORG not like", value, "trainOrg");
            return (Criteria) this;
        }

        public Criteria andTrainOrgIn(List<String> values) {
            addCriterion("TRAIN_ORG in", values, "trainOrg");
            return (Criteria) this;
        }

        public Criteria andTrainOrgNotIn(List<String> values) {
            addCriterion("TRAIN_ORG not in", values, "trainOrg");
            return (Criteria) this;
        }

        public Criteria andTrainOrgBetween(String value1, String value2) {
            addCriterion("TRAIN_ORG between", value1, value2, "trainOrg");
            return (Criteria) this;
        }

        public Criteria andTrainOrgNotBetween(String value1, String value2) {
            addCriterion("TRAIN_ORG not between", value1, value2, "trainOrg");
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

        public Criteria andTrainLecturerIsNull() {
            addCriterion("TRAIN_LECTURER is null");
            return (Criteria) this;
        }

        public Criteria andTrainLecturerIsNotNull() {
            addCriterion("TRAIN_LECTURER is not null");
            return (Criteria) this;
        }

        public Criteria andTrainLecturerEqualTo(String value) {
            addCriterion("TRAIN_LECTURER =", value, "trainLecturer");
            return (Criteria) this;
        }

        public Criteria andTrainLecturerNotEqualTo(String value) {
            addCriterion("TRAIN_LECTURER <>", value, "trainLecturer");
            return (Criteria) this;
        }

        public Criteria andTrainLecturerGreaterThan(String value) {
            addCriterion("TRAIN_LECTURER >", value, "trainLecturer");
            return (Criteria) this;
        }

        public Criteria andTrainLecturerGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_LECTURER >=", value, "trainLecturer");
            return (Criteria) this;
        }

        public Criteria andTrainLecturerLessThan(String value) {
            addCriterion("TRAIN_LECTURER <", value, "trainLecturer");
            return (Criteria) this;
        }

        public Criteria andTrainLecturerLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_LECTURER <=", value, "trainLecturer");
            return (Criteria) this;
        }

        public Criteria andTrainLecturerLike(String value) {
            addCriterion("TRAIN_LECTURER like", value, "trainLecturer");
            return (Criteria) this;
        }

        public Criteria andTrainLecturerNotLike(String value) {
            addCriterion("TRAIN_LECTURER not like", value, "trainLecturer");
            return (Criteria) this;
        }

        public Criteria andTrainLecturerIn(List<String> values) {
            addCriterion("TRAIN_LECTURER in", values, "trainLecturer");
            return (Criteria) this;
        }

        public Criteria andTrainLecturerNotIn(List<String> values) {
            addCriterion("TRAIN_LECTURER not in", values, "trainLecturer");
            return (Criteria) this;
        }

        public Criteria andTrainLecturerBetween(String value1, String value2) {
            addCriterion("TRAIN_LECTURER between", value1, value2, "trainLecturer");
            return (Criteria) this;
        }

        public Criteria andTrainLecturerNotBetween(String value1, String value2) {
            addCriterion("TRAIN_LECTURER not between", value1, value2, "trainLecturer");
            return (Criteria) this;
        }

        public Criteria andTrainAddressIsNull() {
            addCriterion("TRAIN_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andTrainAddressIsNotNull() {
            addCriterion("TRAIN_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andTrainAddressEqualTo(String value) {
            addCriterion("TRAIN_ADDRESS =", value, "trainAddress");
            return (Criteria) this;
        }

        public Criteria andTrainAddressNotEqualTo(String value) {
            addCriterion("TRAIN_ADDRESS <>", value, "trainAddress");
            return (Criteria) this;
        }

        public Criteria andTrainAddressGreaterThan(String value) {
            addCriterion("TRAIN_ADDRESS >", value, "trainAddress");
            return (Criteria) this;
        }

        public Criteria andTrainAddressGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_ADDRESS >=", value, "trainAddress");
            return (Criteria) this;
        }

        public Criteria andTrainAddressLessThan(String value) {
            addCriterion("TRAIN_ADDRESS <", value, "trainAddress");
            return (Criteria) this;
        }

        public Criteria andTrainAddressLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_ADDRESS <=", value, "trainAddress");
            return (Criteria) this;
        }

        public Criteria andTrainAddressLike(String value) {
            addCriterion("TRAIN_ADDRESS like", value, "trainAddress");
            return (Criteria) this;
        }

        public Criteria andTrainAddressNotLike(String value) {
            addCriterion("TRAIN_ADDRESS not like", value, "trainAddress");
            return (Criteria) this;
        }

        public Criteria andTrainAddressIn(List<String> values) {
            addCriterion("TRAIN_ADDRESS in", values, "trainAddress");
            return (Criteria) this;
        }

        public Criteria andTrainAddressNotIn(List<String> values) {
            addCriterion("TRAIN_ADDRESS not in", values, "trainAddress");
            return (Criteria) this;
        }

        public Criteria andTrainAddressBetween(String value1, String value2) {
            addCriterion("TRAIN_ADDRESS between", value1, value2, "trainAddress");
            return (Criteria) this;
        }

        public Criteria andTrainAddressNotBetween(String value1, String value2) {
            addCriterion("TRAIN_ADDRESS not between", value1, value2, "trainAddress");
            return (Criteria) this;
        }

        public Criteria andTrainDateIsNull() {
            addCriterion("TRAIN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTrainDateIsNotNull() {
            addCriterion("TRAIN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTrainDateEqualTo(String value) {
            addCriterion("TRAIN_DATE =", value, "trainDate");
            return (Criteria) this;
        }

        public Criteria andTrainDateNotEqualTo(String value) {
            addCriterion("TRAIN_DATE <>", value, "trainDate");
            return (Criteria) this;
        }

        public Criteria andTrainDateGreaterThan(String value) {
            addCriterion("TRAIN_DATE >", value, "trainDate");
            return (Criteria) this;
        }

        public Criteria andTrainDateGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_DATE >=", value, "trainDate");
            return (Criteria) this;
        }

        public Criteria andTrainDateLessThan(String value) {
            addCriterion("TRAIN_DATE <", value, "trainDate");
            return (Criteria) this;
        }

        public Criteria andTrainDateLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_DATE <=", value, "trainDate");
            return (Criteria) this;
        }

        public Criteria andTrainDateLike(String value) {
            addCriterion("TRAIN_DATE like", value, "trainDate");
            return (Criteria) this;
        }

        public Criteria andTrainDateNotLike(String value) {
            addCriterion("TRAIN_DATE not like", value, "trainDate");
            return (Criteria) this;
        }

        public Criteria andTrainDateIn(List<String> values) {
            addCriterion("TRAIN_DATE in", values, "trainDate");
            return (Criteria) this;
        }

        public Criteria andTrainDateNotIn(List<String> values) {
            addCriterion("TRAIN_DATE not in", values, "trainDate");
            return (Criteria) this;
        }

        public Criteria andTrainDateBetween(String value1, String value2) {
            addCriterion("TRAIN_DATE between", value1, value2, "trainDate");
            return (Criteria) this;
        }

        public Criteria andTrainDateNotBetween(String value1, String value2) {
            addCriterion("TRAIN_DATE not between", value1, value2, "trainDate");
            return (Criteria) this;
        }

        public Criteria andTrainDaysIsNull() {
            addCriterion("TRAIN_DAYS is null");
            return (Criteria) this;
        }

        public Criteria andTrainDaysIsNotNull() {
            addCriterion("TRAIN_DAYS is not null");
            return (Criteria) this;
        }

        public Criteria andTrainDaysEqualTo(String value) {
            addCriterion("TRAIN_DAYS =", value, "trainDays");
            return (Criteria) this;
        }

        public Criteria andTrainDaysNotEqualTo(String value) {
            addCriterion("TRAIN_DAYS <>", value, "trainDays");
            return (Criteria) this;
        }

        public Criteria andTrainDaysGreaterThan(String value) {
            addCriterion("TRAIN_DAYS >", value, "trainDays");
            return (Criteria) this;
        }

        public Criteria andTrainDaysGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_DAYS >=", value, "trainDays");
            return (Criteria) this;
        }

        public Criteria andTrainDaysLessThan(String value) {
            addCriterion("TRAIN_DAYS <", value, "trainDays");
            return (Criteria) this;
        }

        public Criteria andTrainDaysLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_DAYS <=", value, "trainDays");
            return (Criteria) this;
        }

        public Criteria andTrainDaysLike(String value) {
            addCriterion("TRAIN_DAYS like", value, "trainDays");
            return (Criteria) this;
        }

        public Criteria andTrainDaysNotLike(String value) {
            addCriterion("TRAIN_DAYS not like", value, "trainDays");
            return (Criteria) this;
        }

        public Criteria andTrainDaysIn(List<String> values) {
            addCriterion("TRAIN_DAYS in", values, "trainDays");
            return (Criteria) this;
        }

        public Criteria andTrainDaysNotIn(List<String> values) {
            addCriterion("TRAIN_DAYS not in", values, "trainDays");
            return (Criteria) this;
        }

        public Criteria andTrainDaysBetween(String value1, String value2) {
            addCriterion("TRAIN_DAYS between", value1, value2, "trainDays");
            return (Criteria) this;
        }

        public Criteria andTrainDaysNotBetween(String value1, String value2) {
            addCriterion("TRAIN_DAYS not between", value1, value2, "trainDays");
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