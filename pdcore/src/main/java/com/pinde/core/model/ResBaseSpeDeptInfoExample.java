package com.pinde.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ResBaseSpeDeptInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResBaseSpeDeptInfoExample() {
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

        public Criteria andSpeFlowIsNull() {
            addCriterion("SPE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSpeFlowIsNotNull() {
            addCriterion("SPE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSpeFlowEqualTo(String value) {
            addCriterion("SPE_FLOW =", value, "speFlow");
            return (Criteria) this;
        }

        public Criteria andSpeFlowNotEqualTo(String value) {
            addCriterion("SPE_FLOW <>", value, "speFlow");
            return (Criteria) this;
        }

        public Criteria andSpeFlowGreaterThan(String value) {
            addCriterion("SPE_FLOW >", value, "speFlow");
            return (Criteria) this;
        }

        public Criteria andSpeFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_FLOW >=", value, "speFlow");
            return (Criteria) this;
        }

        public Criteria andSpeFlowLessThan(String value) {
            addCriterion("SPE_FLOW <", value, "speFlow");
            return (Criteria) this;
        }

        public Criteria andSpeFlowLessThanOrEqualTo(String value) {
            addCriterion("SPE_FLOW <=", value, "speFlow");
            return (Criteria) this;
        }

        public Criteria andSpeFlowLike(String value) {
            addCriterion("SPE_FLOW like", value, "speFlow");
            return (Criteria) this;
        }

        public Criteria andSpeFlowNotLike(String value) {
            addCriterion("SPE_FLOW not like", value, "speFlow");
            return (Criteria) this;
        }

        public Criteria andSpeFlowIn(List<String> values) {
            addCriterion("SPE_FLOW in", values, "speFlow");
            return (Criteria) this;
        }

        public Criteria andSpeFlowNotIn(List<String> values) {
            addCriterion("SPE_FLOW not in", values, "speFlow");
            return (Criteria) this;
        }

        public Criteria andSpeFlowBetween(String value1, String value2) {
            addCriterion("SPE_FLOW between", value1, value2, "speFlow");
            return (Criteria) this;
        }

        public Criteria andSpeFlowNotBetween(String value1, String value2) {
            addCriterion("SPE_FLOW not between", value1, value2, "speFlow");
            return (Criteria) this;
        }

        public Criteria andSpeNameIsNull() {
            addCriterion("SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSpeNameIsNotNull() {
            addCriterion("SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSpeNameEqualTo(String value) {
            addCriterion("SPE_NAME =", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotEqualTo(String value) {
            addCriterion("SPE_NAME <>", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameGreaterThan(String value) {
            addCriterion("SPE_NAME >", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_NAME >=", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameLessThan(String value) {
            addCriterion("SPE_NAME <", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameLessThanOrEqualTo(String value) {
            addCriterion("SPE_NAME <=", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameLike(String value) {
            addCriterion("SPE_NAME like", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotLike(String value) {
            addCriterion("SPE_NAME not like", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameIn(List<String> values) {
            addCriterion("SPE_NAME in", values, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotIn(List<String> values) {
            addCriterion("SPE_NAME not in", values, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameBetween(String value1, String value2) {
            addCriterion("SPE_NAME between", value1, value2, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotBetween(String value1, String value2) {
            addCriterion("SPE_NAME not between", value1, value2, "speName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameIsNull() {
            addCriterion("STANDARD_DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameIsNotNull() {
            addCriterion("STANDARD_DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameEqualTo(String value) {
            addCriterion("STANDARD_DEPT_NAME =", value, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameNotEqualTo(String value) {
            addCriterion("STANDARD_DEPT_NAME <>", value, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameGreaterThan(String value) {
            addCriterion("STANDARD_DEPT_NAME >", value, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("STANDARD_DEPT_NAME >=", value, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameLessThan(String value) {
            addCriterion("STANDARD_DEPT_NAME <", value, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameLessThanOrEqualTo(String value) {
            addCriterion("STANDARD_DEPT_NAME <=", value, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameLike(String value) {
            addCriterion("STANDARD_DEPT_NAME like", value, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameNotLike(String value) {
            addCriterion("STANDARD_DEPT_NAME not like", value, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameIn(List<String> values) {
            addCriterion("STANDARD_DEPT_NAME in", values, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameNotIn(List<String> values) {
            addCriterion("STANDARD_DEPT_NAME not in", values, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameBetween(String value1, String value2) {
            addCriterion("STANDARD_DEPT_NAME between", value1, value2, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameNotBetween(String value1, String value2) {
            addCriterion("STANDARD_DEPT_NAME not between", value1, value2, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdIsNull() {
            addCriterion("STANDARD_DEPT_ID is null");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdIsNotNull() {
            addCriterion("STANDARD_DEPT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdEqualTo(String value) {
            addCriterion("STANDARD_DEPT_ID =", value, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdNotEqualTo(String value) {
            addCriterion("STANDARD_DEPT_ID <>", value, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdGreaterThan(String value) {
            addCriterion("STANDARD_DEPT_ID >", value, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdGreaterThanOrEqualTo(String value) {
            addCriterion("STANDARD_DEPT_ID >=", value, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdLessThan(String value) {
            addCriterion("STANDARD_DEPT_ID <", value, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdLessThanOrEqualTo(String value) {
            addCriterion("STANDARD_DEPT_ID <=", value, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdLike(String value) {
            addCriterion("STANDARD_DEPT_ID like", value, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdNotLike(String value) {
            addCriterion("STANDARD_DEPT_ID not like", value, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdIn(List<String> values) {
            addCriterion("STANDARD_DEPT_ID in", values, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdNotIn(List<String> values) {
            addCriterion("STANDARD_DEPT_ID not in", values, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdBetween(String value1, String value2) {
            addCriterion("STANDARD_DEPT_ID between", value1, value2, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdNotBetween(String value1, String value2) {
            addCriterion("STANDARD_DEPT_ID not between", value1, value2, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("TYPE like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("TYPE not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("TYPE not between", value1, value2, "type");
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

        public Criteria andOrdinalEqualTo(BigDecimal value) {
            addCriterion("ORDINAL =", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotEqualTo(BigDecimal value) {
            addCriterion("ORDINAL <>", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalGreaterThan(BigDecimal value) {
            addCriterion("ORDINAL >", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ORDINAL >=", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalLessThan(BigDecimal value) {
            addCriterion("ORDINAL <", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ORDINAL <=", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalIn(List<BigDecimal> values) {
            addCriterion("ORDINAL in", values, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotIn(List<BigDecimal> values) {
            addCriterion("ORDINAL not in", values, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ORDINAL between", value1, value2, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ORDINAL not between", value1, value2, "ordinal");
            return (Criteria) this;
        }

        public Criteria andArrNumIsNull() {
            addCriterion("ARR_NUM is null");
            return (Criteria) this;
        }

        public Criteria andArrNumIsNotNull() {
            addCriterion("ARR_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andArrNumEqualTo(String value) {
            addCriterion("ARR_NUM =", value, "arrNum");
            return (Criteria) this;
        }

        public Criteria andArrNumNotEqualTo(String value) {
            addCriterion("ARR_NUM <>", value, "arrNum");
            return (Criteria) this;
        }

        public Criteria andArrNumGreaterThan(String value) {
            addCriterion("ARR_NUM >", value, "arrNum");
            return (Criteria) this;
        }

        public Criteria andArrNumGreaterThanOrEqualTo(String value) {
            addCriterion("ARR_NUM >=", value, "arrNum");
            return (Criteria) this;
        }

        public Criteria andArrNumLessThan(String value) {
            addCriterion("ARR_NUM <", value, "arrNum");
            return (Criteria) this;
        }

        public Criteria andArrNumLessThanOrEqualTo(String value) {
            addCriterion("ARR_NUM <=", value, "arrNum");
            return (Criteria) this;
        }

        public Criteria andArrNumLike(String value) {
            addCriterion("ARR_NUM like", value, "arrNum");
            return (Criteria) this;
        }

        public Criteria andArrNumNotLike(String value) {
            addCriterion("ARR_NUM not like", value, "arrNum");
            return (Criteria) this;
        }

        public Criteria andArrNumIn(List<String> values) {
            addCriterion("ARR_NUM in", values, "arrNum");
            return (Criteria) this;
        }

        public Criteria andArrNumNotIn(List<String> values) {
            addCriterion("ARR_NUM not in", values, "arrNum");
            return (Criteria) this;
        }

        public Criteria andArrNumBetween(String value1, String value2) {
            addCriterion("ARR_NUM between", value1, value2, "arrNum");
            return (Criteria) this;
        }

        public Criteria andArrNumNotBetween(String value1, String value2) {
            addCriterion("ARR_NUM not between", value1, value2, "arrNum");
            return (Criteria) this;
        }

        public Criteria andArrOneIsNull() {
            addCriterion("ARR_ONE is null");
            return (Criteria) this;
        }

        public Criteria andArrOneIsNotNull() {
            addCriterion("ARR_ONE is not null");
            return (Criteria) this;
        }

        public Criteria andArrOneEqualTo(String value) {
            addCriterion("ARR_ONE =", value, "arrOne");
            return (Criteria) this;
        }

        public Criteria andArrOneNotEqualTo(String value) {
            addCriterion("ARR_ONE <>", value, "arrOne");
            return (Criteria) this;
        }

        public Criteria andArrOneGreaterThan(String value) {
            addCriterion("ARR_ONE >", value, "arrOne");
            return (Criteria) this;
        }

        public Criteria andArrOneGreaterThanOrEqualTo(String value) {
            addCriterion("ARR_ONE >=", value, "arrOne");
            return (Criteria) this;
        }

        public Criteria andArrOneLessThan(String value) {
            addCriterion("ARR_ONE <", value, "arrOne");
            return (Criteria) this;
        }

        public Criteria andArrOneLessThanOrEqualTo(String value) {
            addCriterion("ARR_ONE <=", value, "arrOne");
            return (Criteria) this;
        }

        public Criteria andArrOneLike(String value) {
            addCriterion("ARR_ONE like", value, "arrOne");
            return (Criteria) this;
        }

        public Criteria andArrOneNotLike(String value) {
            addCriterion("ARR_ONE not like", value, "arrOne");
            return (Criteria) this;
        }

        public Criteria andArrOneIn(List<String> values) {
            addCriterion("ARR_ONE in", values, "arrOne");
            return (Criteria) this;
        }

        public Criteria andArrOneNotIn(List<String> values) {
            addCriterion("ARR_ONE not in", values, "arrOne");
            return (Criteria) this;
        }

        public Criteria andArrOneBetween(String value1, String value2) {
            addCriterion("ARR_ONE between", value1, value2, "arrOne");
            return (Criteria) this;
        }

        public Criteria andArrOneNotBetween(String value1, String value2) {
            addCriterion("ARR_ONE not between", value1, value2, "arrOne");
            return (Criteria) this;
        }

        public Criteria andArrTwoIsNull() {
            addCriterion("ARR_TWO is null");
            return (Criteria) this;
        }

        public Criteria andArrTwoIsNotNull() {
            addCriterion("ARR_TWO is not null");
            return (Criteria) this;
        }

        public Criteria andArrTwoEqualTo(String value) {
            addCriterion("ARR_TWO =", value, "arrTwo");
            return (Criteria) this;
        }

        public Criteria andArrTwoNotEqualTo(String value) {
            addCriterion("ARR_TWO <>", value, "arrTwo");
            return (Criteria) this;
        }

        public Criteria andArrTwoGreaterThan(String value) {
            addCriterion("ARR_TWO >", value, "arrTwo");
            return (Criteria) this;
        }

        public Criteria andArrTwoGreaterThanOrEqualTo(String value) {
            addCriterion("ARR_TWO >=", value, "arrTwo");
            return (Criteria) this;
        }

        public Criteria andArrTwoLessThan(String value) {
            addCriterion("ARR_TWO <", value, "arrTwo");
            return (Criteria) this;
        }

        public Criteria andArrTwoLessThanOrEqualTo(String value) {
            addCriterion("ARR_TWO <=", value, "arrTwo");
            return (Criteria) this;
        }

        public Criteria andArrTwoLike(String value) {
            addCriterion("ARR_TWO like", value, "arrTwo");
            return (Criteria) this;
        }

        public Criteria andArrTwoNotLike(String value) {
            addCriterion("ARR_TWO not like", value, "arrTwo");
            return (Criteria) this;
        }

        public Criteria andArrTwoIn(List<String> values) {
            addCriterion("ARR_TWO in", values, "arrTwo");
            return (Criteria) this;
        }

        public Criteria andArrTwoNotIn(List<String> values) {
            addCriterion("ARR_TWO not in", values, "arrTwo");
            return (Criteria) this;
        }

        public Criteria andArrTwoBetween(String value1, String value2) {
            addCriterion("ARR_TWO between", value1, value2, "arrTwo");
            return (Criteria) this;
        }

        public Criteria andArrTwoNotBetween(String value1, String value2) {
            addCriterion("ARR_TWO not between", value1, value2, "arrTwo");
            return (Criteria) this;
        }

        public Criteria andArrThreeIsNull() {
            addCriterion("ARR_THREE is null");
            return (Criteria) this;
        }

        public Criteria andArrThreeIsNotNull() {
            addCriterion("ARR_THREE is not null");
            return (Criteria) this;
        }

        public Criteria andArrThreeEqualTo(String value) {
            addCriterion("ARR_THREE =", value, "arrThree");
            return (Criteria) this;
        }

        public Criteria andArrThreeNotEqualTo(String value) {
            addCriterion("ARR_THREE <>", value, "arrThree");
            return (Criteria) this;
        }

        public Criteria andArrThreeGreaterThan(String value) {
            addCriterion("ARR_THREE >", value, "arrThree");
            return (Criteria) this;
        }

        public Criteria andArrThreeGreaterThanOrEqualTo(String value) {
            addCriterion("ARR_THREE >=", value, "arrThree");
            return (Criteria) this;
        }

        public Criteria andArrThreeLessThan(String value) {
            addCriterion("ARR_THREE <", value, "arrThree");
            return (Criteria) this;
        }

        public Criteria andArrThreeLessThanOrEqualTo(String value) {
            addCriterion("ARR_THREE <=", value, "arrThree");
            return (Criteria) this;
        }

        public Criteria andArrThreeLike(String value) {
            addCriterion("ARR_THREE like", value, "arrThree");
            return (Criteria) this;
        }

        public Criteria andArrThreeNotLike(String value) {
            addCriterion("ARR_THREE not like", value, "arrThree");
            return (Criteria) this;
        }

        public Criteria andArrThreeIn(List<String> values) {
            addCriterion("ARR_THREE in", values, "arrThree");
            return (Criteria) this;
        }

        public Criteria andArrThreeNotIn(List<String> values) {
            addCriterion("ARR_THREE not in", values, "arrThree");
            return (Criteria) this;
        }

        public Criteria andArrThreeBetween(String value1, String value2) {
            addCriterion("ARR_THREE between", value1, value2, "arrThree");
            return (Criteria) this;
        }

        public Criteria andArrThreeNotBetween(String value1, String value2) {
            addCriterion("ARR_THREE not between", value1, value2, "arrThree");
            return (Criteria) this;
        }

        public Criteria andArrFourIsNull() {
            addCriterion("ARR_FOUR is null");
            return (Criteria) this;
        }

        public Criteria andArrFourIsNotNull() {
            addCriterion("ARR_FOUR is not null");
            return (Criteria) this;
        }

        public Criteria andArrFourEqualTo(String value) {
            addCriterion("ARR_FOUR =", value, "arrFour");
            return (Criteria) this;
        }

        public Criteria andArrFourNotEqualTo(String value) {
            addCriterion("ARR_FOUR <>", value, "arrFour");
            return (Criteria) this;
        }

        public Criteria andArrFourGreaterThan(String value) {
            addCriterion("ARR_FOUR >", value, "arrFour");
            return (Criteria) this;
        }

        public Criteria andArrFourGreaterThanOrEqualTo(String value) {
            addCriterion("ARR_FOUR >=", value, "arrFour");
            return (Criteria) this;
        }

        public Criteria andArrFourLessThan(String value) {
            addCriterion("ARR_FOUR <", value, "arrFour");
            return (Criteria) this;
        }

        public Criteria andArrFourLessThanOrEqualTo(String value) {
            addCriterion("ARR_FOUR <=", value, "arrFour");
            return (Criteria) this;
        }

        public Criteria andArrFourLike(String value) {
            addCriterion("ARR_FOUR like", value, "arrFour");
            return (Criteria) this;
        }

        public Criteria andArrFourNotLike(String value) {
            addCriterion("ARR_FOUR not like", value, "arrFour");
            return (Criteria) this;
        }

        public Criteria andArrFourIn(List<String> values) {
            addCriterion("ARR_FOUR in", values, "arrFour");
            return (Criteria) this;
        }

        public Criteria andArrFourNotIn(List<String> values) {
            addCriterion("ARR_FOUR not in", values, "arrFour");
            return (Criteria) this;
        }

        public Criteria andArrFourBetween(String value1, String value2) {
            addCriterion("ARR_FOUR between", value1, value2, "arrFour");
            return (Criteria) this;
        }

        public Criteria andArrFourNotBetween(String value1, String value2) {
            addCriterion("ARR_FOUR not between", value1, value2, "arrFour");
            return (Criteria) this;
        }

        public Criteria andArrOneCloIsNull() {
            addCriterion("ARR_ONE_CLO is null");
            return (Criteria) this;
        }

        public Criteria andArrOneCloIsNotNull() {
            addCriterion("ARR_ONE_CLO is not null");
            return (Criteria) this;
        }

        public Criteria andArrOneCloEqualTo(String value) {
            addCriterion("ARR_ONE_CLO =", value, "arrOneClo");
            return (Criteria) this;
        }

        public Criteria andArrOneCloNotEqualTo(String value) {
            addCriterion("ARR_ONE_CLO <>", value, "arrOneClo");
            return (Criteria) this;
        }

        public Criteria andArrOneCloGreaterThan(String value) {
            addCriterion("ARR_ONE_CLO >", value, "arrOneClo");
            return (Criteria) this;
        }

        public Criteria andArrOneCloGreaterThanOrEqualTo(String value) {
            addCriterion("ARR_ONE_CLO >=", value, "arrOneClo");
            return (Criteria) this;
        }

        public Criteria andArrOneCloLessThan(String value) {
            addCriterion("ARR_ONE_CLO <", value, "arrOneClo");
            return (Criteria) this;
        }

        public Criteria andArrOneCloLessThanOrEqualTo(String value) {
            addCriterion("ARR_ONE_CLO <=", value, "arrOneClo");
            return (Criteria) this;
        }

        public Criteria andArrOneCloLike(String value) {
            addCriterion("ARR_ONE_CLO like", value, "arrOneClo");
            return (Criteria) this;
        }

        public Criteria andArrOneCloNotLike(String value) {
            addCriterion("ARR_ONE_CLO not like", value, "arrOneClo");
            return (Criteria) this;
        }

        public Criteria andArrOneCloIn(List<String> values) {
            addCriterion("ARR_ONE_CLO in", values, "arrOneClo");
            return (Criteria) this;
        }

        public Criteria andArrOneCloNotIn(List<String> values) {
            addCriterion("ARR_ONE_CLO not in", values, "arrOneClo");
            return (Criteria) this;
        }

        public Criteria andArrOneCloBetween(String value1, String value2) {
            addCriterion("ARR_ONE_CLO between", value1, value2, "arrOneClo");
            return (Criteria) this;
        }

        public Criteria andArrOneCloNotBetween(String value1, String value2) {
            addCriterion("ARR_ONE_CLO not between", value1, value2, "arrOneClo");
            return (Criteria) this;
        }

        public Criteria andArrOneRowIsNull() {
            addCriterion("ARR_ONE_ROW is null");
            return (Criteria) this;
        }

        public Criteria andArrOneRowIsNotNull() {
            addCriterion("ARR_ONE_ROW is not null");
            return (Criteria) this;
        }

        public Criteria andArrOneRowEqualTo(String value) {
            addCriterion("ARR_ONE_ROW =", value, "arrOneRow");
            return (Criteria) this;
        }

        public Criteria andArrOneRowNotEqualTo(String value) {
            addCriterion("ARR_ONE_ROW <>", value, "arrOneRow");
            return (Criteria) this;
        }

        public Criteria andArrOneRowGreaterThan(String value) {
            addCriterion("ARR_ONE_ROW >", value, "arrOneRow");
            return (Criteria) this;
        }

        public Criteria andArrOneRowGreaterThanOrEqualTo(String value) {
            addCriterion("ARR_ONE_ROW >=", value, "arrOneRow");
            return (Criteria) this;
        }

        public Criteria andArrOneRowLessThan(String value) {
            addCriterion("ARR_ONE_ROW <", value, "arrOneRow");
            return (Criteria) this;
        }

        public Criteria andArrOneRowLessThanOrEqualTo(String value) {
            addCriterion("ARR_ONE_ROW <=", value, "arrOneRow");
            return (Criteria) this;
        }

        public Criteria andArrOneRowLike(String value) {
            addCriterion("ARR_ONE_ROW like", value, "arrOneRow");
            return (Criteria) this;
        }

        public Criteria andArrOneRowNotLike(String value) {
            addCriterion("ARR_ONE_ROW not like", value, "arrOneRow");
            return (Criteria) this;
        }

        public Criteria andArrOneRowIn(List<String> values) {
            addCriterion("ARR_ONE_ROW in", values, "arrOneRow");
            return (Criteria) this;
        }

        public Criteria andArrOneRowNotIn(List<String> values) {
            addCriterion("ARR_ONE_ROW not in", values, "arrOneRow");
            return (Criteria) this;
        }

        public Criteria andArrOneRowBetween(String value1, String value2) {
            addCriterion("ARR_ONE_ROW between", value1, value2, "arrOneRow");
            return (Criteria) this;
        }

        public Criteria andArrOneRowNotBetween(String value1, String value2) {
            addCriterion("ARR_ONE_ROW not between", value1, value2, "arrOneRow");
            return (Criteria) this;
        }

        public Criteria andArrTwoCloIsNull() {
            addCriterion("ARR_TWO_CLO is null");
            return (Criteria) this;
        }

        public Criteria andArrTwoCloIsNotNull() {
            addCriterion("ARR_TWO_CLO is not null");
            return (Criteria) this;
        }

        public Criteria andArrTwoCloEqualTo(String value) {
            addCriterion("ARR_TWO_CLO =", value, "arrTwoClo");
            return (Criteria) this;
        }

        public Criteria andArrTwoCloNotEqualTo(String value) {
            addCriterion("ARR_TWO_CLO <>", value, "arrTwoClo");
            return (Criteria) this;
        }

        public Criteria andArrTwoCloGreaterThan(String value) {
            addCriterion("ARR_TWO_CLO >", value, "arrTwoClo");
            return (Criteria) this;
        }

        public Criteria andArrTwoCloGreaterThanOrEqualTo(String value) {
            addCriterion("ARR_TWO_CLO >=", value, "arrTwoClo");
            return (Criteria) this;
        }

        public Criteria andArrTwoCloLessThan(String value) {
            addCriterion("ARR_TWO_CLO <", value, "arrTwoClo");
            return (Criteria) this;
        }

        public Criteria andArrTwoCloLessThanOrEqualTo(String value) {
            addCriterion("ARR_TWO_CLO <=", value, "arrTwoClo");
            return (Criteria) this;
        }

        public Criteria andArrTwoCloLike(String value) {
            addCriterion("ARR_TWO_CLO like", value, "arrTwoClo");
            return (Criteria) this;
        }

        public Criteria andArrTwoCloNotLike(String value) {
            addCriterion("ARR_TWO_CLO not like", value, "arrTwoClo");
            return (Criteria) this;
        }

        public Criteria andArrTwoCloIn(List<String> values) {
            addCriterion("ARR_TWO_CLO in", values, "arrTwoClo");
            return (Criteria) this;
        }

        public Criteria andArrTwoCloNotIn(List<String> values) {
            addCriterion("ARR_TWO_CLO not in", values, "arrTwoClo");
            return (Criteria) this;
        }

        public Criteria andArrTwoCloBetween(String value1, String value2) {
            addCriterion("ARR_TWO_CLO between", value1, value2, "arrTwoClo");
            return (Criteria) this;
        }

        public Criteria andArrTwoCloNotBetween(String value1, String value2) {
            addCriterion("ARR_TWO_CLO not between", value1, value2, "arrTwoClo");
            return (Criteria) this;
        }

        public Criteria andArrTwoRowIsNull() {
            addCriterion("ARR_TWO_ROW is null");
            return (Criteria) this;
        }

        public Criteria andArrTwoRowIsNotNull() {
            addCriterion("ARR_TWO_ROW is not null");
            return (Criteria) this;
        }

        public Criteria andArrTwoRowEqualTo(String value) {
            addCriterion("ARR_TWO_ROW =", value, "arrTwoRow");
            return (Criteria) this;
        }

        public Criteria andArrTwoRowNotEqualTo(String value) {
            addCriterion("ARR_TWO_ROW <>", value, "arrTwoRow");
            return (Criteria) this;
        }

        public Criteria andArrTwoRowGreaterThan(String value) {
            addCriterion("ARR_TWO_ROW >", value, "arrTwoRow");
            return (Criteria) this;
        }

        public Criteria andArrTwoRowGreaterThanOrEqualTo(String value) {
            addCriterion("ARR_TWO_ROW >=", value, "arrTwoRow");
            return (Criteria) this;
        }

        public Criteria andArrTwoRowLessThan(String value) {
            addCriterion("ARR_TWO_ROW <", value, "arrTwoRow");
            return (Criteria) this;
        }

        public Criteria andArrTwoRowLessThanOrEqualTo(String value) {
            addCriterion("ARR_TWO_ROW <=", value, "arrTwoRow");
            return (Criteria) this;
        }

        public Criteria andArrTwoRowLike(String value) {
            addCriterion("ARR_TWO_ROW like", value, "arrTwoRow");
            return (Criteria) this;
        }

        public Criteria andArrTwoRowNotLike(String value) {
            addCriterion("ARR_TWO_ROW not like", value, "arrTwoRow");
            return (Criteria) this;
        }

        public Criteria andArrTwoRowIn(List<String> values) {
            addCriterion("ARR_TWO_ROW in", values, "arrTwoRow");
            return (Criteria) this;
        }

        public Criteria andArrTwoRowNotIn(List<String> values) {
            addCriterion("ARR_TWO_ROW not in", values, "arrTwoRow");
            return (Criteria) this;
        }

        public Criteria andArrTwoRowBetween(String value1, String value2) {
            addCriterion("ARR_TWO_ROW between", value1, value2, "arrTwoRow");
            return (Criteria) this;
        }

        public Criteria andArrTwoRowNotBetween(String value1, String value2) {
            addCriterion("ARR_TWO_ROW not between", value1, value2, "arrTwoRow");
            return (Criteria) this;
        }

        public Criteria andArrThreeCloIsNull() {
            addCriterion("ARR_THREE_CLO is null");
            return (Criteria) this;
        }

        public Criteria andArrThreeCloIsNotNull() {
            addCriterion("ARR_THREE_CLO is not null");
            return (Criteria) this;
        }

        public Criteria andArrThreeCloEqualTo(String value) {
            addCriterion("ARR_THREE_CLO =", value, "arrThreeClo");
            return (Criteria) this;
        }

        public Criteria andArrThreeCloNotEqualTo(String value) {
            addCriterion("ARR_THREE_CLO <>", value, "arrThreeClo");
            return (Criteria) this;
        }

        public Criteria andArrThreeCloGreaterThan(String value) {
            addCriterion("ARR_THREE_CLO >", value, "arrThreeClo");
            return (Criteria) this;
        }

        public Criteria andArrThreeCloGreaterThanOrEqualTo(String value) {
            addCriterion("ARR_THREE_CLO >=", value, "arrThreeClo");
            return (Criteria) this;
        }

        public Criteria andArrThreeCloLessThan(String value) {
            addCriterion("ARR_THREE_CLO <", value, "arrThreeClo");
            return (Criteria) this;
        }

        public Criteria andArrThreeCloLessThanOrEqualTo(String value) {
            addCriterion("ARR_THREE_CLO <=", value, "arrThreeClo");
            return (Criteria) this;
        }

        public Criteria andArrThreeCloLike(String value) {
            addCriterion("ARR_THREE_CLO like", value, "arrThreeClo");
            return (Criteria) this;
        }

        public Criteria andArrThreeCloNotLike(String value) {
            addCriterion("ARR_THREE_CLO not like", value, "arrThreeClo");
            return (Criteria) this;
        }

        public Criteria andArrThreeCloIn(List<String> values) {
            addCriterion("ARR_THREE_CLO in", values, "arrThreeClo");
            return (Criteria) this;
        }

        public Criteria andArrThreeCloNotIn(List<String> values) {
            addCriterion("ARR_THREE_CLO not in", values, "arrThreeClo");
            return (Criteria) this;
        }

        public Criteria andArrThreeCloBetween(String value1, String value2) {
            addCriterion("ARR_THREE_CLO between", value1, value2, "arrThreeClo");
            return (Criteria) this;
        }

        public Criteria andArrThreeCloNotBetween(String value1, String value2) {
            addCriterion("ARR_THREE_CLO not between", value1, value2, "arrThreeClo");
            return (Criteria) this;
        }

        public Criteria andArrThreeRowIsNull() {
            addCriterion("ARR_THREE_ROW is null");
            return (Criteria) this;
        }

        public Criteria andArrThreeRowIsNotNull() {
            addCriterion("ARR_THREE_ROW is not null");
            return (Criteria) this;
        }

        public Criteria andArrThreeRowEqualTo(String value) {
            addCriterion("ARR_THREE_ROW =", value, "arrThreeRow");
            return (Criteria) this;
        }

        public Criteria andArrThreeRowNotEqualTo(String value) {
            addCriterion("ARR_THREE_ROW <>", value, "arrThreeRow");
            return (Criteria) this;
        }

        public Criteria andArrThreeRowGreaterThan(String value) {
            addCriterion("ARR_THREE_ROW >", value, "arrThreeRow");
            return (Criteria) this;
        }

        public Criteria andArrThreeRowGreaterThanOrEqualTo(String value) {
            addCriterion("ARR_THREE_ROW >=", value, "arrThreeRow");
            return (Criteria) this;
        }

        public Criteria andArrThreeRowLessThan(String value) {
            addCriterion("ARR_THREE_ROW <", value, "arrThreeRow");
            return (Criteria) this;
        }

        public Criteria andArrThreeRowLessThanOrEqualTo(String value) {
            addCriterion("ARR_THREE_ROW <=", value, "arrThreeRow");
            return (Criteria) this;
        }

        public Criteria andArrThreeRowLike(String value) {
            addCriterion("ARR_THREE_ROW like", value, "arrThreeRow");
            return (Criteria) this;
        }

        public Criteria andArrThreeRowNotLike(String value) {
            addCriterion("ARR_THREE_ROW not like", value, "arrThreeRow");
            return (Criteria) this;
        }

        public Criteria andArrThreeRowIn(List<String> values) {
            addCriterion("ARR_THREE_ROW in", values, "arrThreeRow");
            return (Criteria) this;
        }

        public Criteria andArrThreeRowNotIn(List<String> values) {
            addCriterion("ARR_THREE_ROW not in", values, "arrThreeRow");
            return (Criteria) this;
        }

        public Criteria andArrThreeRowBetween(String value1, String value2) {
            addCriterion("ARR_THREE_ROW between", value1, value2, "arrThreeRow");
            return (Criteria) this;
        }

        public Criteria andArrThreeRowNotBetween(String value1, String value2) {
            addCriterion("ARR_THREE_ROW not between", value1, value2, "arrThreeRow");
            return (Criteria) this;
        }

        public Criteria andArrFourCloIsNull() {
            addCriterion("ARR_FOUR_CLO is null");
            return (Criteria) this;
        }

        public Criteria andArrFourCloIsNotNull() {
            addCriterion("ARR_FOUR_CLO is not null");
            return (Criteria) this;
        }

        public Criteria andArrFourCloEqualTo(String value) {
            addCriterion("ARR_FOUR_CLO =", value, "arrFourClo");
            return (Criteria) this;
        }

        public Criteria andArrFourCloNotEqualTo(String value) {
            addCriterion("ARR_FOUR_CLO <>", value, "arrFourClo");
            return (Criteria) this;
        }

        public Criteria andArrFourCloGreaterThan(String value) {
            addCriterion("ARR_FOUR_CLO >", value, "arrFourClo");
            return (Criteria) this;
        }

        public Criteria andArrFourCloGreaterThanOrEqualTo(String value) {
            addCriterion("ARR_FOUR_CLO >=", value, "arrFourClo");
            return (Criteria) this;
        }

        public Criteria andArrFourCloLessThan(String value) {
            addCriterion("ARR_FOUR_CLO <", value, "arrFourClo");
            return (Criteria) this;
        }

        public Criteria andArrFourCloLessThanOrEqualTo(String value) {
            addCriterion("ARR_FOUR_CLO <=", value, "arrFourClo");
            return (Criteria) this;
        }

        public Criteria andArrFourCloLike(String value) {
            addCriterion("ARR_FOUR_CLO like", value, "arrFourClo");
            return (Criteria) this;
        }

        public Criteria andArrFourCloNotLike(String value) {
            addCriterion("ARR_FOUR_CLO not like", value, "arrFourClo");
            return (Criteria) this;
        }

        public Criteria andArrFourCloIn(List<String> values) {
            addCriterion("ARR_FOUR_CLO in", values, "arrFourClo");
            return (Criteria) this;
        }

        public Criteria andArrFourCloNotIn(List<String> values) {
            addCriterion("ARR_FOUR_CLO not in", values, "arrFourClo");
            return (Criteria) this;
        }

        public Criteria andArrFourCloBetween(String value1, String value2) {
            addCriterion("ARR_FOUR_CLO between", value1, value2, "arrFourClo");
            return (Criteria) this;
        }

        public Criteria andArrFourCloNotBetween(String value1, String value2) {
            addCriterion("ARR_FOUR_CLO not between", value1, value2, "arrFourClo");
            return (Criteria) this;
        }

        public Criteria andArrFourRowIsNull() {
            addCriterion("ARR_FOUR_ROW is null");
            return (Criteria) this;
        }

        public Criteria andArrFourRowIsNotNull() {
            addCriterion("ARR_FOUR_ROW is not null");
            return (Criteria) this;
        }

        public Criteria andArrFourRowEqualTo(String value) {
            addCriterion("ARR_FOUR_ROW =", value, "arrFourRow");
            return (Criteria) this;
        }

        public Criteria andArrFourRowNotEqualTo(String value) {
            addCriterion("ARR_FOUR_ROW <>", value, "arrFourRow");
            return (Criteria) this;
        }

        public Criteria andArrFourRowGreaterThan(String value) {
            addCriterion("ARR_FOUR_ROW >", value, "arrFourRow");
            return (Criteria) this;
        }

        public Criteria andArrFourRowGreaterThanOrEqualTo(String value) {
            addCriterion("ARR_FOUR_ROW >=", value, "arrFourRow");
            return (Criteria) this;
        }

        public Criteria andArrFourRowLessThan(String value) {
            addCriterion("ARR_FOUR_ROW <", value, "arrFourRow");
            return (Criteria) this;
        }

        public Criteria andArrFourRowLessThanOrEqualTo(String value) {
            addCriterion("ARR_FOUR_ROW <=", value, "arrFourRow");
            return (Criteria) this;
        }

        public Criteria andArrFourRowLike(String value) {
            addCriterion("ARR_FOUR_ROW like", value, "arrFourRow");
            return (Criteria) this;
        }

        public Criteria andArrFourRowNotLike(String value) {
            addCriterion("ARR_FOUR_ROW not like", value, "arrFourRow");
            return (Criteria) this;
        }

        public Criteria andArrFourRowIn(List<String> values) {
            addCriterion("ARR_FOUR_ROW in", values, "arrFourRow");
            return (Criteria) this;
        }

        public Criteria andArrFourRowNotIn(List<String> values) {
            addCriterion("ARR_FOUR_ROW not in", values, "arrFourRow");
            return (Criteria) this;
        }

        public Criteria andArrFourRowBetween(String value1, String value2) {
            addCriterion("ARR_FOUR_ROW between", value1, value2, "arrFourRow");
            return (Criteria) this;
        }

        public Criteria andArrFourRowNotBetween(String value1, String value2) {
            addCriterion("ARR_FOUR_ROW not between", value1, value2, "arrFourRow");
            return (Criteria) this;
        }

        public Criteria andIsThIsNull() {
            addCriterion("IS_TH is null");
            return (Criteria) this;
        }

        public Criteria andIsThIsNotNull() {
            addCriterion("IS_TH is not null");
            return (Criteria) this;
        }

        public Criteria andIsThEqualTo(String value) {
            addCriterion("IS_TH =", value, "isTh");
            return (Criteria) this;
        }

        public Criteria andIsThNotEqualTo(String value) {
            addCriterion("IS_TH <>", value, "isTh");
            return (Criteria) this;
        }

        public Criteria andIsThGreaterThan(String value) {
            addCriterion("IS_TH >", value, "isTh");
            return (Criteria) this;
        }

        public Criteria andIsThGreaterThanOrEqualTo(String value) {
            addCriterion("IS_TH >=", value, "isTh");
            return (Criteria) this;
        }

        public Criteria andIsThLessThan(String value) {
            addCriterion("IS_TH <", value, "isTh");
            return (Criteria) this;
        }

        public Criteria andIsThLessThanOrEqualTo(String value) {
            addCriterion("IS_TH <=", value, "isTh");
            return (Criteria) this;
        }

        public Criteria andIsThLike(String value) {
            addCriterion("IS_TH like", value, "isTh");
            return (Criteria) this;
        }

        public Criteria andIsThNotLike(String value) {
            addCriterion("IS_TH not like", value, "isTh");
            return (Criteria) this;
        }

        public Criteria andIsThIn(List<String> values) {
            addCriterion("IS_TH in", values, "isTh");
            return (Criteria) this;
        }

        public Criteria andIsThNotIn(List<String> values) {
            addCriterion("IS_TH not in", values, "isTh");
            return (Criteria) this;
        }

        public Criteria andIsThBetween(String value1, String value2) {
            addCriterion("IS_TH between", value1, value2, "isTh");
            return (Criteria) this;
        }

        public Criteria andIsThNotBetween(String value1, String value2) {
            addCriterion("IS_TH not between", value1, value2, "isTh");
            return (Criteria) this;
        }

        public Criteria andIsTdIsNull() {
            addCriterion("IS_TD is null");
            return (Criteria) this;
        }

        public Criteria andIsTdIsNotNull() {
            addCriterion("IS_TD is not null");
            return (Criteria) this;
        }

        public Criteria andIsTdEqualTo(String value) {
            addCriterion("IS_TD =", value, "isTd");
            return (Criteria) this;
        }

        public Criteria andIsTdNotEqualTo(String value) {
            addCriterion("IS_TD <>", value, "isTd");
            return (Criteria) this;
        }

        public Criteria andIsTdGreaterThan(String value) {
            addCriterion("IS_TD >", value, "isTd");
            return (Criteria) this;
        }

        public Criteria andIsTdGreaterThanOrEqualTo(String value) {
            addCriterion("IS_TD >=", value, "isTd");
            return (Criteria) this;
        }

        public Criteria andIsTdLessThan(String value) {
            addCriterion("IS_TD <", value, "isTd");
            return (Criteria) this;
        }

        public Criteria andIsTdLessThanOrEqualTo(String value) {
            addCriterion("IS_TD <=", value, "isTd");
            return (Criteria) this;
        }

        public Criteria andIsTdLike(String value) {
            addCriterion("IS_TD like", value, "isTd");
            return (Criteria) this;
        }

        public Criteria andIsTdNotLike(String value) {
            addCriterion("IS_TD not like", value, "isTd");
            return (Criteria) this;
        }

        public Criteria andIsTdIn(List<String> values) {
            addCriterion("IS_TD in", values, "isTd");
            return (Criteria) this;
        }

        public Criteria andIsTdNotIn(List<String> values) {
            addCriterion("IS_TD not in", values, "isTd");
            return (Criteria) this;
        }

        public Criteria andIsTdBetween(String value1, String value2) {
            addCriterion("IS_TD between", value1, value2, "isTd");
            return (Criteria) this;
        }

        public Criteria andIsTdNotBetween(String value1, String value2) {
            addCriterion("IS_TD not between", value1, value2, "isTd");
            return (Criteria) this;
        }

        public Criteria andArrNameIsNull() {
            addCriterion("ARR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andArrNameIsNotNull() {
            addCriterion("ARR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andArrNameEqualTo(String value) {
            addCriterion("ARR_NAME =", value, "arrName");
            return (Criteria) this;
        }

        public Criteria andArrNameNotEqualTo(String value) {
            addCriterion("ARR_NAME <>", value, "arrName");
            return (Criteria) this;
        }

        public Criteria andArrNameGreaterThan(String value) {
            addCriterion("ARR_NAME >", value, "arrName");
            return (Criteria) this;
        }

        public Criteria andArrNameGreaterThanOrEqualTo(String value) {
            addCriterion("ARR_NAME >=", value, "arrName");
            return (Criteria) this;
        }

        public Criteria andArrNameLessThan(String value) {
            addCriterion("ARR_NAME <", value, "arrName");
            return (Criteria) this;
        }

        public Criteria andArrNameLessThanOrEqualTo(String value) {
            addCriterion("ARR_NAME <=", value, "arrName");
            return (Criteria) this;
        }

        public Criteria andArrNameLike(String value) {
            addCriterion("ARR_NAME like", value, "arrName");
            return (Criteria) this;
        }

        public Criteria andArrNameNotLike(String value) {
            addCriterion("ARR_NAME not like", value, "arrName");
            return (Criteria) this;
        }

        public Criteria andArrNameIn(List<String> values) {
            addCriterion("ARR_NAME in", values, "arrName");
            return (Criteria) this;
        }

        public Criteria andArrNameNotIn(List<String> values) {
            addCriterion("ARR_NAME not in", values, "arrName");
            return (Criteria) this;
        }

        public Criteria andArrNameBetween(String value1, String value2) {
            addCriterion("ARR_NAME between", value1, value2, "arrName");
            return (Criteria) this;
        }

        public Criteria andArrNameNotBetween(String value1, String value2) {
            addCriterion("ARR_NAME not between", value1, value2, "arrName");
            return (Criteria) this;
        }

        public Criteria andPlaInfoIsNull() {
            addCriterion("PLA_INFO is null");
            return (Criteria) this;
        }

        public Criteria andPlaInfoIsNotNull() {
            addCriterion("PLA_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andPlaInfoEqualTo(String value) {
            addCriterion("PLA_INFO =", value, "plaInfo");
            return (Criteria) this;
        }

        public Criteria andPlaInfoNotEqualTo(String value) {
            addCriterion("PLA_INFO <>", value, "plaInfo");
            return (Criteria) this;
        }

        public Criteria andPlaInfoGreaterThan(String value) {
            addCriterion("PLA_INFO >", value, "plaInfo");
            return (Criteria) this;
        }

        public Criteria andPlaInfoGreaterThanOrEqualTo(String value) {
            addCriterion("PLA_INFO >=", value, "plaInfo");
            return (Criteria) this;
        }

        public Criteria andPlaInfoLessThan(String value) {
            addCriterion("PLA_INFO <", value, "plaInfo");
            return (Criteria) this;
        }

        public Criteria andPlaInfoLessThanOrEqualTo(String value) {
            addCriterion("PLA_INFO <=", value, "plaInfo");
            return (Criteria) this;
        }

        public Criteria andPlaInfoLike(String value) {
            addCriterion("PLA_INFO like", value, "plaInfo");
            return (Criteria) this;
        }

        public Criteria andPlaInfoNotLike(String value) {
            addCriterion("PLA_INFO not like", value, "plaInfo");
            return (Criteria) this;
        }

        public Criteria andPlaInfoIn(List<String> values) {
            addCriterion("PLA_INFO in", values, "plaInfo");
            return (Criteria) this;
        }

        public Criteria andPlaInfoNotIn(List<String> values) {
            addCriterion("PLA_INFO not in", values, "plaInfo");
            return (Criteria) this;
        }

        public Criteria andPlaInfoBetween(String value1, String value2) {
            addCriterion("PLA_INFO between", value1, value2, "plaInfo");
            return (Criteria) this;
        }

        public Criteria andPlaInfoNotBetween(String value1, String value2) {
            addCriterion("PLA_INFO not between", value1, value2, "plaInfo");
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

        public Criteria andArrFiveIsNull() {
            addCriterion("ARR_FIVE is null");
            return (Criteria) this;
        }

        public Criteria andArrFiveIsNotNull() {
            addCriterion("ARR_FIVE is not null");
            return (Criteria) this;
        }

        public Criteria andArrFiveEqualTo(String value) {
            addCriterion("ARR_FIVE =", value, "arrFive");
            return (Criteria) this;
        }

        public Criteria andArrFiveNotEqualTo(String value) {
            addCriterion("ARR_FIVE <>", value, "arrFive");
            return (Criteria) this;
        }

        public Criteria andArrFiveGreaterThan(String value) {
            addCriterion("ARR_FIVE >", value, "arrFive");
            return (Criteria) this;
        }

        public Criteria andArrFiveGreaterThanOrEqualTo(String value) {
            addCriterion("ARR_FIVE >=", value, "arrFive");
            return (Criteria) this;
        }

        public Criteria andArrFiveLessThan(String value) {
            addCriterion("ARR_FIVE <", value, "arrFive");
            return (Criteria) this;
        }

        public Criteria andArrFiveLessThanOrEqualTo(String value) {
            addCriterion("ARR_FIVE <=", value, "arrFive");
            return (Criteria) this;
        }

        public Criteria andArrFiveLike(String value) {
            addCriterion("ARR_FIVE like", value, "arrFive");
            return (Criteria) this;
        }

        public Criteria andArrFiveNotLike(String value) {
            addCriterion("ARR_FIVE not like", value, "arrFive");
            return (Criteria) this;
        }

        public Criteria andArrFiveIn(List<String> values) {
            addCriterion("ARR_FIVE in", values, "arrFive");
            return (Criteria) this;
        }

        public Criteria andArrFiveNotIn(List<String> values) {
            addCriterion("ARR_FIVE not in", values, "arrFive");
            return (Criteria) this;
        }

        public Criteria andArrFiveBetween(String value1, String value2) {
            addCriterion("ARR_FIVE between", value1, value2, "arrFive");
            return (Criteria) this;
        }

        public Criteria andArrFiveNotBetween(String value1, String value2) {
            addCriterion("ARR_FIVE not between", value1, value2, "arrFive");
            return (Criteria) this;
        }

        public Criteria andArrFiveCloIsNull() {
            addCriterion("ARR_FIVE_CLO is null");
            return (Criteria) this;
        }

        public Criteria andArrFiveCloIsNotNull() {
            addCriterion("ARR_FIVE_CLO is not null");
            return (Criteria) this;
        }

        public Criteria andArrFiveCloEqualTo(String value) {
            addCriterion("ARR_FIVE_CLO =", value, "arrFiveClo");
            return (Criteria) this;
        }

        public Criteria andArrFiveCloNotEqualTo(String value) {
            addCriterion("ARR_FIVE_CLO <>", value, "arrFiveClo");
            return (Criteria) this;
        }

        public Criteria andArrFiveCloGreaterThan(String value) {
            addCriterion("ARR_FIVE_CLO >", value, "arrFiveClo");
            return (Criteria) this;
        }

        public Criteria andArrFiveCloGreaterThanOrEqualTo(String value) {
            addCriterion("ARR_FIVE_CLO >=", value, "arrFiveClo");
            return (Criteria) this;
        }

        public Criteria andArrFiveCloLessThan(String value) {
            addCriterion("ARR_FIVE_CLO <", value, "arrFiveClo");
            return (Criteria) this;
        }

        public Criteria andArrFiveCloLessThanOrEqualTo(String value) {
            addCriterion("ARR_FIVE_CLO <=", value, "arrFiveClo");
            return (Criteria) this;
        }

        public Criteria andArrFiveCloLike(String value) {
            addCriterion("ARR_FIVE_CLO like", value, "arrFiveClo");
            return (Criteria) this;
        }

        public Criteria andArrFiveCloNotLike(String value) {
            addCriterion("ARR_FIVE_CLO not like", value, "arrFiveClo");
            return (Criteria) this;
        }

        public Criteria andArrFiveCloIn(List<String> values) {
            addCriterion("ARR_FIVE_CLO in", values, "arrFiveClo");
            return (Criteria) this;
        }

        public Criteria andArrFiveCloNotIn(List<String> values) {
            addCriterion("ARR_FIVE_CLO not in", values, "arrFiveClo");
            return (Criteria) this;
        }

        public Criteria andArrFiveCloBetween(String value1, String value2) {
            addCriterion("ARR_FIVE_CLO between", value1, value2, "arrFiveClo");
            return (Criteria) this;
        }

        public Criteria andArrFiveCloNotBetween(String value1, String value2) {
            addCriterion("ARR_FIVE_CLO not between", value1, value2, "arrFiveClo");
            return (Criteria) this;
        }

        public Criteria andArrFiveRowIsNull() {
            addCriterion("ARR_FIVE_ROW is null");
            return (Criteria) this;
        }

        public Criteria andArrFiveRowIsNotNull() {
            addCriterion("ARR_FIVE_ROW is not null");
            return (Criteria) this;
        }

        public Criteria andArrFiveRowEqualTo(String value) {
            addCriterion("ARR_FIVE_ROW =", value, "arrFiveRow");
            return (Criteria) this;
        }

        public Criteria andArrFiveRowNotEqualTo(String value) {
            addCriterion("ARR_FIVE_ROW <>", value, "arrFiveRow");
            return (Criteria) this;
        }

        public Criteria andArrFiveRowGreaterThan(String value) {
            addCriterion("ARR_FIVE_ROW >", value, "arrFiveRow");
            return (Criteria) this;
        }

        public Criteria andArrFiveRowGreaterThanOrEqualTo(String value) {
            addCriterion("ARR_FIVE_ROW >=", value, "arrFiveRow");
            return (Criteria) this;
        }

        public Criteria andArrFiveRowLessThan(String value) {
            addCriterion("ARR_FIVE_ROW <", value, "arrFiveRow");
            return (Criteria) this;
        }

        public Criteria andArrFiveRowLessThanOrEqualTo(String value) {
            addCriterion("ARR_FIVE_ROW <=", value, "arrFiveRow");
            return (Criteria) this;
        }

        public Criteria andArrFiveRowLike(String value) {
            addCriterion("ARR_FIVE_ROW like", value, "arrFiveRow");
            return (Criteria) this;
        }

        public Criteria andArrFiveRowNotLike(String value) {
            addCriterion("ARR_FIVE_ROW not like", value, "arrFiveRow");
            return (Criteria) this;
        }

        public Criteria andArrFiveRowIn(List<String> values) {
            addCriterion("ARR_FIVE_ROW in", values, "arrFiveRow");
            return (Criteria) this;
        }

        public Criteria andArrFiveRowNotIn(List<String> values) {
            addCriterion("ARR_FIVE_ROW not in", values, "arrFiveRow");
            return (Criteria) this;
        }

        public Criteria andArrFiveRowBetween(String value1, String value2) {
            addCriterion("ARR_FIVE_ROW between", value1, value2, "arrFiveRow");
            return (Criteria) this;
        }

        public Criteria andArrFiveRowNotBetween(String value1, String value2) {
            addCriterion("ARR_FIVE_ROW not between", value1, value2, "arrFiveRow");
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