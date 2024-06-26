package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ExamBookExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExamBookExample() {
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

        public Criteria andBookFlowIsNull() {
            addCriterion("BOOK_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andBookFlowIsNotNull() {
            addCriterion("BOOK_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andBookFlowEqualTo(String value) {
            addCriterion("BOOK_FLOW =", value, "bookFlow");
            return (Criteria) this;
        }

        public Criteria andBookFlowNotEqualTo(String value) {
            addCriterion("BOOK_FLOW <>", value, "bookFlow");
            return (Criteria) this;
        }

        public Criteria andBookFlowGreaterThan(String value) {
            addCriterion("BOOK_FLOW >", value, "bookFlow");
            return (Criteria) this;
        }

        public Criteria andBookFlowGreaterThanOrEqualTo(String value) {
            addCriterion("BOOK_FLOW >=", value, "bookFlow");
            return (Criteria) this;
        }

        public Criteria andBookFlowLessThan(String value) {
            addCriterion("BOOK_FLOW <", value, "bookFlow");
            return (Criteria) this;
        }

        public Criteria andBookFlowLessThanOrEqualTo(String value) {
            addCriterion("BOOK_FLOW <=", value, "bookFlow");
            return (Criteria) this;
        }

        public Criteria andBookFlowLike(String value) {
            addCriterion("BOOK_FLOW like", value, "bookFlow");
            return (Criteria) this;
        }

        public Criteria andBookFlowNotLike(String value) {
            addCriterion("BOOK_FLOW not like", value, "bookFlow");
            return (Criteria) this;
        }

        public Criteria andBookFlowIn(List<String> values) {
            addCriterion("BOOK_FLOW in", values, "bookFlow");
            return (Criteria) this;
        }

        public Criteria andBookFlowNotIn(List<String> values) {
            addCriterion("BOOK_FLOW not in", values, "bookFlow");
            return (Criteria) this;
        }

        public Criteria andBookFlowBetween(String value1, String value2) {
            addCriterion("BOOK_FLOW between", value1, value2, "bookFlow");
            return (Criteria) this;
        }

        public Criteria andBookFlowNotBetween(String value1, String value2) {
            addCriterion("BOOK_FLOW not between", value1, value2, "bookFlow");
            return (Criteria) this;
        }

        public Criteria andBookNameIsNull() {
            addCriterion("BOOK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBookNameIsNotNull() {
            addCriterion("BOOK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBookNameEqualTo(String value) {
            addCriterion("BOOK_NAME =", value, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameNotEqualTo(String value) {
            addCriterion("BOOK_NAME <>", value, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameGreaterThan(String value) {
            addCriterion("BOOK_NAME >", value, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameGreaterThanOrEqualTo(String value) {
            addCriterion("BOOK_NAME >=", value, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameLessThan(String value) {
            addCriterion("BOOK_NAME <", value, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameLessThanOrEqualTo(String value) {
            addCriterion("BOOK_NAME <=", value, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameLike(String value) {
            addCriterion("BOOK_NAME like", value, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameNotLike(String value) {
            addCriterion("BOOK_NAME not like", value, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameIn(List<String> values) {
            addCriterion("BOOK_NAME in", values, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameNotIn(List<String> values) {
            addCriterion("BOOK_NAME not in", values, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameBetween(String value1, String value2) {
            addCriterion("BOOK_NAME between", value1, value2, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookNameNotBetween(String value1, String value2) {
            addCriterion("BOOK_NAME not between", value1, value2, "bookName");
            return (Criteria) this;
        }

        public Criteria andBookParentFlowIsNull() {
            addCriterion("BOOK_PARENT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andBookParentFlowIsNotNull() {
            addCriterion("BOOK_PARENT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andBookParentFlowEqualTo(String value) {
            addCriterion("BOOK_PARENT_FLOW =", value, "bookParentFlow");
            return (Criteria) this;
        }

        public Criteria andBookParentFlowNotEqualTo(String value) {
            addCriterion("BOOK_PARENT_FLOW <>", value, "bookParentFlow");
            return (Criteria) this;
        }

        public Criteria andBookParentFlowGreaterThan(String value) {
            addCriterion("BOOK_PARENT_FLOW >", value, "bookParentFlow");
            return (Criteria) this;
        }

        public Criteria andBookParentFlowGreaterThanOrEqualTo(String value) {
            addCriterion("BOOK_PARENT_FLOW >=", value, "bookParentFlow");
            return (Criteria) this;
        }

        public Criteria andBookParentFlowLessThan(String value) {
            addCriterion("BOOK_PARENT_FLOW <", value, "bookParentFlow");
            return (Criteria) this;
        }

        public Criteria andBookParentFlowLessThanOrEqualTo(String value) {
            addCriterion("BOOK_PARENT_FLOW <=", value, "bookParentFlow");
            return (Criteria) this;
        }

        public Criteria andBookParentFlowLike(String value) {
            addCriterion("BOOK_PARENT_FLOW like", value, "bookParentFlow");
            return (Criteria) this;
        }

        public Criteria andBookParentFlowNotLike(String value) {
            addCriterion("BOOK_PARENT_FLOW not like", value, "bookParentFlow");
            return (Criteria) this;
        }

        public Criteria andBookParentFlowIn(List<String> values) {
            addCriterion("BOOK_PARENT_FLOW in", values, "bookParentFlow");
            return (Criteria) this;
        }

        public Criteria andBookParentFlowNotIn(List<String> values) {
            addCriterion("BOOK_PARENT_FLOW not in", values, "bookParentFlow");
            return (Criteria) this;
        }

        public Criteria andBookParentFlowBetween(String value1, String value2) {
            addCriterion("BOOK_PARENT_FLOW between", value1, value2, "bookParentFlow");
            return (Criteria) this;
        }

        public Criteria andBookParentFlowNotBetween(String value1, String value2) {
            addCriterion("BOOK_PARENT_FLOW not between", value1, value2, "bookParentFlow");
            return (Criteria) this;
        }

        public Criteria andBookTypeIdIsNull() {
            addCriterion("BOOK_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andBookTypeIdIsNotNull() {
            addCriterion("BOOK_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBookTypeIdEqualTo(String value) {
            addCriterion("BOOK_TYPE_ID =", value, "bookTypeId");
            return (Criteria) this;
        }

        public Criteria andBookTypeIdNotEqualTo(String value) {
            addCriterion("BOOK_TYPE_ID <>", value, "bookTypeId");
            return (Criteria) this;
        }

        public Criteria andBookTypeIdGreaterThan(String value) {
            addCriterion("BOOK_TYPE_ID >", value, "bookTypeId");
            return (Criteria) this;
        }

        public Criteria andBookTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("BOOK_TYPE_ID >=", value, "bookTypeId");
            return (Criteria) this;
        }

        public Criteria andBookTypeIdLessThan(String value) {
            addCriterion("BOOK_TYPE_ID <", value, "bookTypeId");
            return (Criteria) this;
        }

        public Criteria andBookTypeIdLessThanOrEqualTo(String value) {
            addCriterion("BOOK_TYPE_ID <=", value, "bookTypeId");
            return (Criteria) this;
        }

        public Criteria andBookTypeIdLike(String value) {
            addCriterion("BOOK_TYPE_ID like", value, "bookTypeId");
            return (Criteria) this;
        }

        public Criteria andBookTypeIdNotLike(String value) {
            addCriterion("BOOK_TYPE_ID not like", value, "bookTypeId");
            return (Criteria) this;
        }

        public Criteria andBookTypeIdIn(List<String> values) {
            addCriterion("BOOK_TYPE_ID in", values, "bookTypeId");
            return (Criteria) this;
        }

        public Criteria andBookTypeIdNotIn(List<String> values) {
            addCriterion("BOOK_TYPE_ID not in", values, "bookTypeId");
            return (Criteria) this;
        }

        public Criteria andBookTypeIdBetween(String value1, String value2) {
            addCriterion("BOOK_TYPE_ID between", value1, value2, "bookTypeId");
            return (Criteria) this;
        }

        public Criteria andBookTypeIdNotBetween(String value1, String value2) {
            addCriterion("BOOK_TYPE_ID not between", value1, value2, "bookTypeId");
            return (Criteria) this;
        }

        public Criteria andBookTypeNameIsNull() {
            addCriterion("BOOK_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBookTypeNameIsNotNull() {
            addCriterion("BOOK_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBookTypeNameEqualTo(String value) {
            addCriterion("BOOK_TYPE_NAME =", value, "bookTypeName");
            return (Criteria) this;
        }

        public Criteria andBookTypeNameNotEqualTo(String value) {
            addCriterion("BOOK_TYPE_NAME <>", value, "bookTypeName");
            return (Criteria) this;
        }

        public Criteria andBookTypeNameGreaterThan(String value) {
            addCriterion("BOOK_TYPE_NAME >", value, "bookTypeName");
            return (Criteria) this;
        }

        public Criteria andBookTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("BOOK_TYPE_NAME >=", value, "bookTypeName");
            return (Criteria) this;
        }

        public Criteria andBookTypeNameLessThan(String value) {
            addCriterion("BOOK_TYPE_NAME <", value, "bookTypeName");
            return (Criteria) this;
        }

        public Criteria andBookTypeNameLessThanOrEqualTo(String value) {
            addCriterion("BOOK_TYPE_NAME <=", value, "bookTypeName");
            return (Criteria) this;
        }

        public Criteria andBookTypeNameLike(String value) {
            addCriterion("BOOK_TYPE_NAME like", value, "bookTypeName");
            return (Criteria) this;
        }

        public Criteria andBookTypeNameNotLike(String value) {
            addCriterion("BOOK_TYPE_NAME not like", value, "bookTypeName");
            return (Criteria) this;
        }

        public Criteria andBookTypeNameIn(List<String> values) {
            addCriterion("BOOK_TYPE_NAME in", values, "bookTypeName");
            return (Criteria) this;
        }

        public Criteria andBookTypeNameNotIn(List<String> values) {
            addCriterion("BOOK_TYPE_NAME not in", values, "bookTypeName");
            return (Criteria) this;
        }

        public Criteria andBookTypeNameBetween(String value1, String value2) {
            addCriterion("BOOK_TYPE_NAME between", value1, value2, "bookTypeName");
            return (Criteria) this;
        }

        public Criteria andBookTypeNameNotBetween(String value1, String value2) {
            addCriterion("BOOK_TYPE_NAME not between", value1, value2, "bookTypeName");
            return (Criteria) this;
        }

        public Criteria andOrdinalIsNull() {
            addCriterion("ORDINAL is null");
            return (Criteria) this;
        }

        public Criteria andOrdinalIsNotNull() {
            addCriterion("ORDINAL is not null");
            return (Criteria) this;
        }

        public Criteria andOrdinalEqualTo(Integer value) {
            addCriterion("ORDINAL =", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotEqualTo(Integer value) {
            addCriterion("ORDINAL <>", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalGreaterThan(Integer value) {
            addCriterion("ORDINAL >", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalGreaterThanOrEqualTo(Integer value) {
            addCriterion("ORDINAL >=", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalLessThan(Integer value) {
            addCriterion("ORDINAL <", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalLessThanOrEqualTo(Integer value) {
            addCriterion("ORDINAL <=", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalIn(List<Integer> values) {
            addCriterion("ORDINAL in", values, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotIn(List<Integer> values) {
            addCriterion("ORDINAL not in", values, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalBetween(Integer value1, Integer value2) {
            addCriterion("ORDINAL between", value1, value2, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotBetween(Integer value1, Integer value2) {
            addCriterion("ORDINAL not between", value1, value2, "ordinal");
            return (Criteria) this;
        }

        public Criteria andBookNumIsNull() {
            addCriterion("BOOK_NUM is null");
            return (Criteria) this;
        }

        public Criteria andBookNumIsNotNull() {
            addCriterion("BOOK_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andBookNumEqualTo(String value) {
            addCriterion("BOOK_NUM =", value, "bookNum");
            return (Criteria) this;
        }

        public Criteria andBookNumNotEqualTo(String value) {
            addCriterion("BOOK_NUM <>", value, "bookNum");
            return (Criteria) this;
        }

        public Criteria andBookNumGreaterThan(String value) {
            addCriterion("BOOK_NUM >", value, "bookNum");
            return (Criteria) this;
        }

        public Criteria andBookNumGreaterThanOrEqualTo(String value) {
            addCriterion("BOOK_NUM >=", value, "bookNum");
            return (Criteria) this;
        }

        public Criteria andBookNumLessThan(String value) {
            addCriterion("BOOK_NUM <", value, "bookNum");
            return (Criteria) this;
        }

        public Criteria andBookNumLessThanOrEqualTo(String value) {
            addCriterion("BOOK_NUM <=", value, "bookNum");
            return (Criteria) this;
        }

        public Criteria andBookNumLike(String value) {
            addCriterion("BOOK_NUM like", value, "bookNum");
            return (Criteria) this;
        }

        public Criteria andBookNumNotLike(String value) {
            addCriterion("BOOK_NUM not like", value, "bookNum");
            return (Criteria) this;
        }

        public Criteria andBookNumIn(List<String> values) {
            addCriterion("BOOK_NUM in", values, "bookNum");
            return (Criteria) this;
        }

        public Criteria andBookNumNotIn(List<String> values) {
            addCriterion("BOOK_NUM not in", values, "bookNum");
            return (Criteria) this;
        }

        public Criteria andBookNumBetween(String value1, String value2) {
            addCriterion("BOOK_NUM between", value1, value2, "bookNum");
            return (Criteria) this;
        }

        public Criteria andBookNumNotBetween(String value1, String value2) {
            addCriterion("BOOK_NUM not between", value1, value2, "bookNum");
            return (Criteria) this;
        }

        public Criteria andPublishYearIsNull() {
            addCriterion("PUBLISH_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andPublishYearIsNotNull() {
            addCriterion("PUBLISH_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andPublishYearEqualTo(String value) {
            addCriterion("PUBLISH_YEAR =", value, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearNotEqualTo(String value) {
            addCriterion("PUBLISH_YEAR <>", value, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearGreaterThan(String value) {
            addCriterion("PUBLISH_YEAR >", value, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLISH_YEAR >=", value, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearLessThan(String value) {
            addCriterion("PUBLISH_YEAR <", value, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearLessThanOrEqualTo(String value) {
            addCriterion("PUBLISH_YEAR <=", value, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearLike(String value) {
            addCriterion("PUBLISH_YEAR like", value, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearNotLike(String value) {
            addCriterion("PUBLISH_YEAR not like", value, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearIn(List<String> values) {
            addCriterion("PUBLISH_YEAR in", values, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearNotIn(List<String> values) {
            addCriterion("PUBLISH_YEAR not in", values, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearBetween(String value1, String value2) {
            addCriterion("PUBLISH_YEAR between", value1, value2, "publishYear");
            return (Criteria) this;
        }

        public Criteria andPublishYearNotBetween(String value1, String value2) {
            addCriterion("PUBLISH_YEAR not between", value1, value2, "publishYear");
            return (Criteria) this;
        }

        public Criteria andBookPressIsNull() {
            addCriterion("BOOK_PRESS is null");
            return (Criteria) this;
        }

        public Criteria andBookPressIsNotNull() {
            addCriterion("BOOK_PRESS is not null");
            return (Criteria) this;
        }

        public Criteria andBookPressEqualTo(String value) {
            addCriterion("BOOK_PRESS =", value, "bookPress");
            return (Criteria) this;
        }

        public Criteria andBookPressNotEqualTo(String value) {
            addCriterion("BOOK_PRESS <>", value, "bookPress");
            return (Criteria) this;
        }

        public Criteria andBookPressGreaterThan(String value) {
            addCriterion("BOOK_PRESS >", value, "bookPress");
            return (Criteria) this;
        }

        public Criteria andBookPressGreaterThanOrEqualTo(String value) {
            addCriterion("BOOK_PRESS >=", value, "bookPress");
            return (Criteria) this;
        }

        public Criteria andBookPressLessThan(String value) {
            addCriterion("BOOK_PRESS <", value, "bookPress");
            return (Criteria) this;
        }

        public Criteria andBookPressLessThanOrEqualTo(String value) {
            addCriterion("BOOK_PRESS <=", value, "bookPress");
            return (Criteria) this;
        }

        public Criteria andBookPressLike(String value) {
            addCriterion("BOOK_PRESS like", value, "bookPress");
            return (Criteria) this;
        }

        public Criteria andBookPressNotLike(String value) {
            addCriterion("BOOK_PRESS not like", value, "bookPress");
            return (Criteria) this;
        }

        public Criteria andBookPressIn(List<String> values) {
            addCriterion("BOOK_PRESS in", values, "bookPress");
            return (Criteria) this;
        }

        public Criteria andBookPressNotIn(List<String> values) {
            addCriterion("BOOK_PRESS not in", values, "bookPress");
            return (Criteria) this;
        }

        public Criteria andBookPressBetween(String value1, String value2) {
            addCriterion("BOOK_PRESS between", value1, value2, "bookPress");
            return (Criteria) this;
        }

        public Criteria andBookPressNotBetween(String value1, String value2) {
            addCriterion("BOOK_PRESS not between", value1, value2, "bookPress");
            return (Criteria) this;
        }

        public Criteria andDepthIsNull() {
            addCriterion("DEPTH is null");
            return (Criteria) this;
        }

        public Criteria andDepthIsNotNull() {
            addCriterion("DEPTH is not null");
            return (Criteria) this;
        }

        public Criteria andDepthEqualTo(String value) {
            addCriterion("DEPTH =", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotEqualTo(String value) {
            addCriterion("DEPTH <>", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthGreaterThan(String value) {
            addCriterion("DEPTH >", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthGreaterThanOrEqualTo(String value) {
            addCriterion("DEPTH >=", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthLessThan(String value) {
            addCriterion("DEPTH <", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthLessThanOrEqualTo(String value) {
            addCriterion("DEPTH <=", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthLike(String value) {
            addCriterion("DEPTH like", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotLike(String value) {
            addCriterion("DEPTH not like", value, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthIn(List<String> values) {
            addCriterion("DEPTH in", values, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotIn(List<String> values) {
            addCriterion("DEPTH not in", values, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthBetween(String value1, String value2) {
            addCriterion("DEPTH between", value1, value2, "depth");
            return (Criteria) this;
        }

        public Criteria andDepthNotBetween(String value1, String value2) {
            addCriterion("DEPTH not between", value1, value2, "depth");
            return (Criteria) this;
        }

        public Criteria andLeafFlagIsNull() {
            addCriterion("LEAF_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andLeafFlagIsNotNull() {
            addCriterion("LEAF_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andLeafFlagEqualTo(String value) {
            addCriterion("LEAF_FLAG =", value, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagNotEqualTo(String value) {
            addCriterion("LEAF_FLAG <>", value, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagGreaterThan(String value) {
            addCriterion("LEAF_FLAG >", value, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagGreaterThanOrEqualTo(String value) {
            addCriterion("LEAF_FLAG >=", value, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagLessThan(String value) {
            addCriterion("LEAF_FLAG <", value, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagLessThanOrEqualTo(String value) {
            addCriterion("LEAF_FLAG <=", value, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagLike(String value) {
            addCriterion("LEAF_FLAG like", value, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagNotLike(String value) {
            addCriterion("LEAF_FLAG not like", value, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagIn(List<String> values) {
            addCriterion("LEAF_FLAG in", values, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagNotIn(List<String> values) {
            addCriterion("LEAF_FLAG not in", values, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagBetween(String value1, String value2) {
            addCriterion("LEAF_FLAG between", value1, value2, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andLeafFlagNotBetween(String value1, String value2) {
            addCriterion("LEAF_FLAG not between", value1, value2, "leafFlag");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("MEMO is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("MEMO =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("MEMO <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("MEMO >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("MEMO >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("MEMO <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("MEMO <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("MEMO like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("MEMO not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("MEMO in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("MEMO not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("MEMO between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("MEMO not between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andBookPageNumIsNull() {
            addCriterion("BOOK_PAGE_NUM is null");
            return (Criteria) this;
        }

        public Criteria andBookPageNumIsNotNull() {
            addCriterion("BOOK_PAGE_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andBookPageNumEqualTo(Integer value) {
            addCriterion("BOOK_PAGE_NUM =", value, "bookPageNum");
            return (Criteria) this;
        }

        public Criteria andBookPageNumNotEqualTo(Integer value) {
            addCriterion("BOOK_PAGE_NUM <>", value, "bookPageNum");
            return (Criteria) this;
        }

        public Criteria andBookPageNumGreaterThan(Integer value) {
            addCriterion("BOOK_PAGE_NUM >", value, "bookPageNum");
            return (Criteria) this;
        }

        public Criteria andBookPageNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("BOOK_PAGE_NUM >=", value, "bookPageNum");
            return (Criteria) this;
        }

        public Criteria andBookPageNumLessThan(Integer value) {
            addCriterion("BOOK_PAGE_NUM <", value, "bookPageNum");
            return (Criteria) this;
        }

        public Criteria andBookPageNumLessThanOrEqualTo(Integer value) {
            addCriterion("BOOK_PAGE_NUM <=", value, "bookPageNum");
            return (Criteria) this;
        }

        public Criteria andBookPageNumIn(List<Integer> values) {
            addCriterion("BOOK_PAGE_NUM in", values, "bookPageNum");
            return (Criteria) this;
        }

        public Criteria andBookPageNumNotIn(List<Integer> values) {
            addCriterion("BOOK_PAGE_NUM not in", values, "bookPageNum");
            return (Criteria) this;
        }

        public Criteria andBookPageNumBetween(Integer value1, Integer value2) {
            addCriterion("BOOK_PAGE_NUM between", value1, value2, "bookPageNum");
            return (Criteria) this;
        }

        public Criteria andBookPageNumNotBetween(Integer value1, Integer value2) {
            addCriterion("BOOK_PAGE_NUM not between", value1, value2, "bookPageNum");
            return (Criteria) this;
        }

        public Criteria andBookSourceIsNull() {
            addCriterion("BOOK_SOURCE is null");
            return (Criteria) this;
        }

        public Criteria andBookSourceIsNotNull() {
            addCriterion("BOOK_SOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andBookSourceEqualTo(String value) {
            addCriterion("BOOK_SOURCE =", value, "bookSource");
            return (Criteria) this;
        }

        public Criteria andBookSourceNotEqualTo(String value) {
            addCriterion("BOOK_SOURCE <>", value, "bookSource");
            return (Criteria) this;
        }

        public Criteria andBookSourceGreaterThan(String value) {
            addCriterion("BOOK_SOURCE >", value, "bookSource");
            return (Criteria) this;
        }

        public Criteria andBookSourceGreaterThanOrEqualTo(String value) {
            addCriterion("BOOK_SOURCE >=", value, "bookSource");
            return (Criteria) this;
        }

        public Criteria andBookSourceLessThan(String value) {
            addCriterion("BOOK_SOURCE <", value, "bookSource");
            return (Criteria) this;
        }

        public Criteria andBookSourceLessThanOrEqualTo(String value) {
            addCriterion("BOOK_SOURCE <=", value, "bookSource");
            return (Criteria) this;
        }

        public Criteria andBookSourceLike(String value) {
            addCriterion("BOOK_SOURCE like", value, "bookSource");
            return (Criteria) this;
        }

        public Criteria andBookSourceNotLike(String value) {
            addCriterion("BOOK_SOURCE not like", value, "bookSource");
            return (Criteria) this;
        }

        public Criteria andBookSourceIn(List<String> values) {
            addCriterion("BOOK_SOURCE in", values, "bookSource");
            return (Criteria) this;
        }

        public Criteria andBookSourceNotIn(List<String> values) {
            addCriterion("BOOK_SOURCE not in", values, "bookSource");
            return (Criteria) this;
        }

        public Criteria andBookSourceBetween(String value1, String value2) {
            addCriterion("BOOK_SOURCE between", value1, value2, "bookSource");
            return (Criteria) this;
        }

        public Criteria andBookSourceNotBetween(String value1, String value2) {
            addCriterion("BOOK_SOURCE not between", value1, value2, "bookSource");
            return (Criteria) this;
        }

        public Criteria andRegistStatusIdIsNull() {
            addCriterion("REGIST_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andRegistStatusIdIsNotNull() {
            addCriterion("REGIST_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRegistStatusIdEqualTo(String value) {
            addCriterion("REGIST_STATUS_ID =", value, "registStatusId");
            return (Criteria) this;
        }

        public Criteria andRegistStatusIdNotEqualTo(String value) {
            addCriterion("REGIST_STATUS_ID <>", value, "registStatusId");
            return (Criteria) this;
        }

        public Criteria andRegistStatusIdGreaterThan(String value) {
            addCriterion("REGIST_STATUS_ID >", value, "registStatusId");
            return (Criteria) this;
        }

        public Criteria andRegistStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("REGIST_STATUS_ID >=", value, "registStatusId");
            return (Criteria) this;
        }

        public Criteria andRegistStatusIdLessThan(String value) {
            addCriterion("REGIST_STATUS_ID <", value, "registStatusId");
            return (Criteria) this;
        }

        public Criteria andRegistStatusIdLessThanOrEqualTo(String value) {
            addCriterion("REGIST_STATUS_ID <=", value, "registStatusId");
            return (Criteria) this;
        }

        public Criteria andRegistStatusIdLike(String value) {
            addCriterion("REGIST_STATUS_ID like", value, "registStatusId");
            return (Criteria) this;
        }

        public Criteria andRegistStatusIdNotLike(String value) {
            addCriterion("REGIST_STATUS_ID not like", value, "registStatusId");
            return (Criteria) this;
        }

        public Criteria andRegistStatusIdIn(List<String> values) {
            addCriterion("REGIST_STATUS_ID in", values, "registStatusId");
            return (Criteria) this;
        }

        public Criteria andRegistStatusIdNotIn(List<String> values) {
            addCriterion("REGIST_STATUS_ID not in", values, "registStatusId");
            return (Criteria) this;
        }

        public Criteria andRegistStatusIdBetween(String value1, String value2) {
            addCriterion("REGIST_STATUS_ID between", value1, value2, "registStatusId");
            return (Criteria) this;
        }

        public Criteria andRegistStatusIdNotBetween(String value1, String value2) {
            addCriterion("REGIST_STATUS_ID not between", value1, value2, "registStatusId");
            return (Criteria) this;
        }

        public Criteria andRegistStatusNameIsNull() {
            addCriterion("REGIST_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRegistStatusNameIsNotNull() {
            addCriterion("REGIST_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRegistStatusNameEqualTo(String value) {
            addCriterion("REGIST_STATUS_NAME =", value, "registStatusName");
            return (Criteria) this;
        }

        public Criteria andRegistStatusNameNotEqualTo(String value) {
            addCriterion("REGIST_STATUS_NAME <>", value, "registStatusName");
            return (Criteria) this;
        }

        public Criteria andRegistStatusNameGreaterThan(String value) {
            addCriterion("REGIST_STATUS_NAME >", value, "registStatusName");
            return (Criteria) this;
        }

        public Criteria andRegistStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("REGIST_STATUS_NAME >=", value, "registStatusName");
            return (Criteria) this;
        }

        public Criteria andRegistStatusNameLessThan(String value) {
            addCriterion("REGIST_STATUS_NAME <", value, "registStatusName");
            return (Criteria) this;
        }

        public Criteria andRegistStatusNameLessThanOrEqualTo(String value) {
            addCriterion("REGIST_STATUS_NAME <=", value, "registStatusName");
            return (Criteria) this;
        }

        public Criteria andRegistStatusNameLike(String value) {
            addCriterion("REGIST_STATUS_NAME like", value, "registStatusName");
            return (Criteria) this;
        }

        public Criteria andRegistStatusNameNotLike(String value) {
            addCriterion("REGIST_STATUS_NAME not like", value, "registStatusName");
            return (Criteria) this;
        }

        public Criteria andRegistStatusNameIn(List<String> values) {
            addCriterion("REGIST_STATUS_NAME in", values, "registStatusName");
            return (Criteria) this;
        }

        public Criteria andRegistStatusNameNotIn(List<String> values) {
            addCriterion("REGIST_STATUS_NAME not in", values, "registStatusName");
            return (Criteria) this;
        }

        public Criteria andRegistStatusNameBetween(String value1, String value2) {
            addCriterion("REGIST_STATUS_NAME between", value1, value2, "registStatusName");
            return (Criteria) this;
        }

        public Criteria andRegistStatusNameNotBetween(String value1, String value2) {
            addCriterion("REGIST_STATUS_NAME not between", value1, value2, "registStatusName");
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