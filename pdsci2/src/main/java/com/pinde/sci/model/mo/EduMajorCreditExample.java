package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class EduMajorCreditExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EduMajorCreditExample() {
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

        public Criteria andCreditIsNull() {
            addCriterion("CREDIT is null");
            return (Criteria) this;
        }

        public Criteria andCreditIsNotNull() {
            addCriterion("CREDIT is not null");
            return (Criteria) this;
        }

        public Criteria andCreditEqualTo(String value) {
            addCriterion("CREDIT =", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotEqualTo(String value) {
            addCriterion("CREDIT <>", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThan(String value) {
            addCriterion("CREDIT >", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThanOrEqualTo(String value) {
            addCriterion("CREDIT >=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThan(String value) {
            addCriterion("CREDIT <", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThanOrEqualTo(String value) {
            addCriterion("CREDIT <=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLike(String value) {
            addCriterion("CREDIT like", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotLike(String value) {
            addCriterion("CREDIT not like", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditIn(List<String> values) {
            addCriterion("CREDIT in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotIn(List<String> values) {
            addCriterion("CREDIT not in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditBetween(String value1, String value2) {
            addCriterion("CREDIT between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotBetween(String value1, String value2) {
            addCriterion("CREDIT not between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andDegreeCreditIsNull() {
            addCriterion("DEGREE_CREDIT is null");
            return (Criteria) this;
        }

        public Criteria andDegreeCreditIsNotNull() {
            addCriterion("DEGREE_CREDIT is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeCreditEqualTo(String value) {
            addCriterion("DEGREE_CREDIT =", value, "degreeCredit");
            return (Criteria) this;
        }

        public Criteria andDegreeCreditNotEqualTo(String value) {
            addCriterion("DEGREE_CREDIT <>", value, "degreeCredit");
            return (Criteria) this;
        }

        public Criteria andDegreeCreditGreaterThan(String value) {
            addCriterion("DEGREE_CREDIT >", value, "degreeCredit");
            return (Criteria) this;
        }

        public Criteria andDegreeCreditGreaterThanOrEqualTo(String value) {
            addCriterion("DEGREE_CREDIT >=", value, "degreeCredit");
            return (Criteria) this;
        }

        public Criteria andDegreeCreditLessThan(String value) {
            addCriterion("DEGREE_CREDIT <", value, "degreeCredit");
            return (Criteria) this;
        }

        public Criteria andDegreeCreditLessThanOrEqualTo(String value) {
            addCriterion("DEGREE_CREDIT <=", value, "degreeCredit");
            return (Criteria) this;
        }

        public Criteria andDegreeCreditLike(String value) {
            addCriterion("DEGREE_CREDIT like", value, "degreeCredit");
            return (Criteria) this;
        }

        public Criteria andDegreeCreditNotLike(String value) {
            addCriterion("DEGREE_CREDIT not like", value, "degreeCredit");
            return (Criteria) this;
        }

        public Criteria andDegreeCreditIn(List<String> values) {
            addCriterion("DEGREE_CREDIT in", values, "degreeCredit");
            return (Criteria) this;
        }

        public Criteria andDegreeCreditNotIn(List<String> values) {
            addCriterion("DEGREE_CREDIT not in", values, "degreeCredit");
            return (Criteria) this;
        }

        public Criteria andDegreeCreditBetween(String value1, String value2) {
            addCriterion("DEGREE_CREDIT between", value1, value2, "degreeCredit");
            return (Criteria) this;
        }

        public Criteria andDegreeCreditNotBetween(String value1, String value2) {
            addCriterion("DEGREE_CREDIT not between", value1, value2, "degreeCredit");
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