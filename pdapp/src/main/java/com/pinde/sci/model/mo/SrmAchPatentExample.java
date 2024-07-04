package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class SrmAchPatentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SrmAchPatentExample() {
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

        public Criteria andPatentFlowIsNull() {
            addCriterion("PATENT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andPatentFlowIsNotNull() {
            addCriterion("PATENT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andPatentFlowEqualTo(String value) {
            addCriterion("PATENT_FLOW =", value, "patentFlow");
            return (Criteria) this;
        }

        public Criteria andPatentFlowNotEqualTo(String value) {
            addCriterion("PATENT_FLOW <>", value, "patentFlow");
            return (Criteria) this;
        }

        public Criteria andPatentFlowGreaterThan(String value) {
            addCriterion("PATENT_FLOW >", value, "patentFlow");
            return (Criteria) this;
        }

        public Criteria andPatentFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PATENT_FLOW >=", value, "patentFlow");
            return (Criteria) this;
        }

        public Criteria andPatentFlowLessThan(String value) {
            addCriterion("PATENT_FLOW <", value, "patentFlow");
            return (Criteria) this;
        }

        public Criteria andPatentFlowLessThanOrEqualTo(String value) {
            addCriterion("PATENT_FLOW <=", value, "patentFlow");
            return (Criteria) this;
        }

        public Criteria andPatentFlowLike(String value) {
            addCriterion("PATENT_FLOW like", value, "patentFlow");
            return (Criteria) this;
        }

        public Criteria andPatentFlowNotLike(String value) {
            addCriterion("PATENT_FLOW not like", value, "patentFlow");
            return (Criteria) this;
        }

        public Criteria andPatentFlowIn(List<String> values) {
            addCriterion("PATENT_FLOW in", values, "patentFlow");
            return (Criteria) this;
        }

        public Criteria andPatentFlowNotIn(List<String> values) {
            addCriterion("PATENT_FLOW not in", values, "patentFlow");
            return (Criteria) this;
        }

        public Criteria andPatentFlowBetween(String value1, String value2) {
            addCriterion("PATENT_FLOW between", value1, value2, "patentFlow");
            return (Criteria) this;
        }

        public Criteria andPatentFlowNotBetween(String value1, String value2) {
            addCriterion("PATENT_FLOW not between", value1, value2, "patentFlow");
            return (Criteria) this;
        }

        public Criteria andPatentNameIsNull() {
            addCriterion("PATENT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPatentNameIsNotNull() {
            addCriterion("PATENT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPatentNameEqualTo(String value) {
            addCriterion("PATENT_NAME =", value, "patentName");
            return (Criteria) this;
        }

        public Criteria andPatentNameNotEqualTo(String value) {
            addCriterion("PATENT_NAME <>", value, "patentName");
            return (Criteria) this;
        }

        public Criteria andPatentNameGreaterThan(String value) {
            addCriterion("PATENT_NAME >", value, "patentName");
            return (Criteria) this;
        }

        public Criteria andPatentNameGreaterThanOrEqualTo(String value) {
            addCriterion("PATENT_NAME >=", value, "patentName");
            return (Criteria) this;
        }

        public Criteria andPatentNameLessThan(String value) {
            addCriterion("PATENT_NAME <", value, "patentName");
            return (Criteria) this;
        }

        public Criteria andPatentNameLessThanOrEqualTo(String value) {
            addCriterion("PATENT_NAME <=", value, "patentName");
            return (Criteria) this;
        }

        public Criteria andPatentNameLike(String value) {
            addCriterion("PATENT_NAME like", value, "patentName");
            return (Criteria) this;
        }

        public Criteria andPatentNameNotLike(String value) {
            addCriterion("PATENT_NAME not like", value, "patentName");
            return (Criteria) this;
        }

        public Criteria andPatentNameIn(List<String> values) {
            addCriterion("PATENT_NAME in", values, "patentName");
            return (Criteria) this;
        }

        public Criteria andPatentNameNotIn(List<String> values) {
            addCriterion("PATENT_NAME not in", values, "patentName");
            return (Criteria) this;
        }

        public Criteria andPatentNameBetween(String value1, String value2) {
            addCriterion("PATENT_NAME between", value1, value2, "patentName");
            return (Criteria) this;
        }

        public Criteria andPatentNameNotBetween(String value1, String value2) {
            addCriterion("PATENT_NAME not between", value1, value2, "patentName");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdIsNull() {
            addCriterion("ORG_BELONG_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdIsNotNull() {
            addCriterion("ORG_BELONG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdEqualTo(String value) {
            addCriterion("ORG_BELONG_ID =", value, "orgBelongId");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdNotEqualTo(String value) {
            addCriterion("ORG_BELONG_ID <>", value, "orgBelongId");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdGreaterThan(String value) {
            addCriterion("ORG_BELONG_ID >", value, "orgBelongId");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_BELONG_ID >=", value, "orgBelongId");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdLessThan(String value) {
            addCriterion("ORG_BELONG_ID <", value, "orgBelongId");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_BELONG_ID <=", value, "orgBelongId");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdLike(String value) {
            addCriterion("ORG_BELONG_ID like", value, "orgBelongId");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdNotLike(String value) {
            addCriterion("ORG_BELONG_ID not like", value, "orgBelongId");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdIn(List<String> values) {
            addCriterion("ORG_BELONG_ID in", values, "orgBelongId");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdNotIn(List<String> values) {
            addCriterion("ORG_BELONG_ID not in", values, "orgBelongId");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdBetween(String value1, String value2) {
            addCriterion("ORG_BELONG_ID between", value1, value2, "orgBelongId");
            return (Criteria) this;
        }

        public Criteria andOrgBelongIdNotBetween(String value1, String value2) {
            addCriterion("ORG_BELONG_ID not between", value1, value2, "orgBelongId");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameIsNull() {
            addCriterion("ORG_BELONG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameIsNotNull() {
            addCriterion("ORG_BELONG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameEqualTo(String value) {
            addCriterion("ORG_BELONG_NAME =", value, "orgBelongName");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameNotEqualTo(String value) {
            addCriterion("ORG_BELONG_NAME <>", value, "orgBelongName");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameGreaterThan(String value) {
            addCriterion("ORG_BELONG_NAME >", value, "orgBelongName");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_BELONG_NAME >=", value, "orgBelongName");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameLessThan(String value) {
            addCriterion("ORG_BELONG_NAME <", value, "orgBelongName");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_BELONG_NAME <=", value, "orgBelongName");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameLike(String value) {
            addCriterion("ORG_BELONG_NAME like", value, "orgBelongName");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameNotLike(String value) {
            addCriterion("ORG_BELONG_NAME not like", value, "orgBelongName");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameIn(List<String> values) {
            addCriterion("ORG_BELONG_NAME in", values, "orgBelongName");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameNotIn(List<String> values) {
            addCriterion("ORG_BELONG_NAME not in", values, "orgBelongName");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameBetween(String value1, String value2) {
            addCriterion("ORG_BELONG_NAME between", value1, value2, "orgBelongName");
            return (Criteria) this;
        }

        public Criteria andOrgBelongNameNotBetween(String value1, String value2) {
            addCriterion("ORG_BELONG_NAME not between", value1, value2, "orgBelongName");
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

        public Criteria andRangeIdIsNull() {
            addCriterion("RANGE_ID is null");
            return (Criteria) this;
        }

        public Criteria andRangeIdIsNotNull() {
            addCriterion("RANGE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRangeIdEqualTo(String value) {
            addCriterion("RANGE_ID =", value, "rangeId");
            return (Criteria) this;
        }

        public Criteria andRangeIdNotEqualTo(String value) {
            addCriterion("RANGE_ID <>", value, "rangeId");
            return (Criteria) this;
        }

        public Criteria andRangeIdGreaterThan(String value) {
            addCriterion("RANGE_ID >", value, "rangeId");
            return (Criteria) this;
        }

        public Criteria andRangeIdGreaterThanOrEqualTo(String value) {
            addCriterion("RANGE_ID >=", value, "rangeId");
            return (Criteria) this;
        }

        public Criteria andRangeIdLessThan(String value) {
            addCriterion("RANGE_ID <", value, "rangeId");
            return (Criteria) this;
        }

        public Criteria andRangeIdLessThanOrEqualTo(String value) {
            addCriterion("RANGE_ID <=", value, "rangeId");
            return (Criteria) this;
        }

        public Criteria andRangeIdLike(String value) {
            addCriterion("RANGE_ID like", value, "rangeId");
            return (Criteria) this;
        }

        public Criteria andRangeIdNotLike(String value) {
            addCriterion("RANGE_ID not like", value, "rangeId");
            return (Criteria) this;
        }

        public Criteria andRangeIdIn(List<String> values) {
            addCriterion("RANGE_ID in", values, "rangeId");
            return (Criteria) this;
        }

        public Criteria andRangeIdNotIn(List<String> values) {
            addCriterion("RANGE_ID not in", values, "rangeId");
            return (Criteria) this;
        }

        public Criteria andRangeIdBetween(String value1, String value2) {
            addCriterion("RANGE_ID between", value1, value2, "rangeId");
            return (Criteria) this;
        }

        public Criteria andRangeIdNotBetween(String value1, String value2) {
            addCriterion("RANGE_ID not between", value1, value2, "rangeId");
            return (Criteria) this;
        }

        public Criteria andRangeNameIsNull() {
            addCriterion("RANGE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRangeNameIsNotNull() {
            addCriterion("RANGE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRangeNameEqualTo(String value) {
            addCriterion("RANGE_NAME =", value, "rangeName");
            return (Criteria) this;
        }

        public Criteria andRangeNameNotEqualTo(String value) {
            addCriterion("RANGE_NAME <>", value, "rangeName");
            return (Criteria) this;
        }

        public Criteria andRangeNameGreaterThan(String value) {
            addCriterion("RANGE_NAME >", value, "rangeName");
            return (Criteria) this;
        }

        public Criteria andRangeNameGreaterThanOrEqualTo(String value) {
            addCriterion("RANGE_NAME >=", value, "rangeName");
            return (Criteria) this;
        }

        public Criteria andRangeNameLessThan(String value) {
            addCriterion("RANGE_NAME <", value, "rangeName");
            return (Criteria) this;
        }

        public Criteria andRangeNameLessThanOrEqualTo(String value) {
            addCriterion("RANGE_NAME <=", value, "rangeName");
            return (Criteria) this;
        }

        public Criteria andRangeNameLike(String value) {
            addCriterion("RANGE_NAME like", value, "rangeName");
            return (Criteria) this;
        }

        public Criteria andRangeNameNotLike(String value) {
            addCriterion("RANGE_NAME not like", value, "rangeName");
            return (Criteria) this;
        }

        public Criteria andRangeNameIn(List<String> values) {
            addCriterion("RANGE_NAME in", values, "rangeName");
            return (Criteria) this;
        }

        public Criteria andRangeNameNotIn(List<String> values) {
            addCriterion("RANGE_NAME not in", values, "rangeName");
            return (Criteria) this;
        }

        public Criteria andRangeNameBetween(String value1, String value2) {
            addCriterion("RANGE_NAME between", value1, value2, "rangeName");
            return (Criteria) this;
        }

        public Criteria andRangeNameNotBetween(String value1, String value2) {
            addCriterion("RANGE_NAME not between", value1, value2, "rangeName");
            return (Criteria) this;
        }

        public Criteria andStatusIdIsNull() {
            addCriterion("STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andStatusIdIsNotNull() {
            addCriterion("STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStatusIdEqualTo(String value) {
            addCriterion("STATUS_ID =", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotEqualTo(String value) {
            addCriterion("STATUS_ID <>", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdGreaterThan(String value) {
            addCriterion("STATUS_ID >", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS_ID >=", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLessThan(String value) {
            addCriterion("STATUS_ID <", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLessThanOrEqualTo(String value) {
            addCriterion("STATUS_ID <=", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLike(String value) {
            addCriterion("STATUS_ID like", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotLike(String value) {
            addCriterion("STATUS_ID not like", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdIn(List<String> values) {
            addCriterion("STATUS_ID in", values, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotIn(List<String> values) {
            addCriterion("STATUS_ID not in", values, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdBetween(String value1, String value2) {
            addCriterion("STATUS_ID between", value1, value2, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotBetween(String value1, String value2) {
            addCriterion("STATUS_ID not between", value1, value2, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusNameIsNull() {
            addCriterion("STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStatusNameIsNotNull() {
            addCriterion("STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStatusNameEqualTo(String value) {
            addCriterion("STATUS_NAME =", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameNotEqualTo(String value) {
            addCriterion("STATUS_NAME <>", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameGreaterThan(String value) {
            addCriterion("STATUS_NAME >", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS_NAME >=", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameLessThan(String value) {
            addCriterion("STATUS_NAME <", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameLessThanOrEqualTo(String value) {
            addCriterion("STATUS_NAME <=", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameLike(String value) {
            addCriterion("STATUS_NAME like", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameNotLike(String value) {
            addCriterion("STATUS_NAME not like", value, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameIn(List<String> values) {
            addCriterion("STATUS_NAME in", values, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameNotIn(List<String> values) {
            addCriterion("STATUS_NAME not in", values, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameBetween(String value1, String value2) {
            addCriterion("STATUS_NAME between", value1, value2, "statusName");
            return (Criteria) this;
        }

        public Criteria andStatusNameNotBetween(String value1, String value2) {
            addCriterion("STATUS_NAME not between", value1, value2, "statusName");
            return (Criteria) this;
        }

        public Criteria andApplyDateIsNull() {
            addCriterion("APPLY_DATE is null");
            return (Criteria) this;
        }

        public Criteria andApplyDateIsNotNull() {
            addCriterion("APPLY_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andApplyDateEqualTo(String value) {
            addCriterion("APPLY_DATE =", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotEqualTo(String value) {
            addCriterion("APPLY_DATE <>", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateGreaterThan(String value) {
            addCriterion("APPLY_DATE >", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_DATE >=", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLessThan(String value) {
            addCriterion("APPLY_DATE <", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLessThanOrEqualTo(String value) {
            addCriterion("APPLY_DATE <=", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLike(String value) {
            addCriterion("APPLY_DATE like", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotLike(String value) {
            addCriterion("APPLY_DATE not like", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateIn(List<String> values) {
            addCriterion("APPLY_DATE in", values, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotIn(List<String> values) {
            addCriterion("APPLY_DATE not in", values, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateBetween(String value1, String value2) {
            addCriterion("APPLY_DATE between", value1, value2, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotBetween(String value1, String value2) {
            addCriterion("APPLY_DATE not between", value1, value2, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyCodeIsNull() {
            addCriterion("APPLY_CODE is null");
            return (Criteria) this;
        }

        public Criteria andApplyCodeIsNotNull() {
            addCriterion("APPLY_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andApplyCodeEqualTo(String value) {
            addCriterion("APPLY_CODE =", value, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeNotEqualTo(String value) {
            addCriterion("APPLY_CODE <>", value, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeGreaterThan(String value) {
            addCriterion("APPLY_CODE >", value, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_CODE >=", value, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeLessThan(String value) {
            addCriterion("APPLY_CODE <", value, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeLessThanOrEqualTo(String value) {
            addCriterion("APPLY_CODE <=", value, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeLike(String value) {
            addCriterion("APPLY_CODE like", value, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeNotLike(String value) {
            addCriterion("APPLY_CODE not like", value, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeIn(List<String> values) {
            addCriterion("APPLY_CODE in", values, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeNotIn(List<String> values) {
            addCriterion("APPLY_CODE not in", values, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeBetween(String value1, String value2) {
            addCriterion("APPLY_CODE between", value1, value2, "applyCode");
            return (Criteria) this;
        }

        public Criteria andApplyCodeNotBetween(String value1, String value2) {
            addCriterion("APPLY_CODE not between", value1, value2, "applyCode");
            return (Criteria) this;
        }

        public Criteria andOpenDateIsNull() {
            addCriterion("OPEN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andOpenDateIsNotNull() {
            addCriterion("OPEN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andOpenDateEqualTo(String value) {
            addCriterion("OPEN_DATE =", value, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateNotEqualTo(String value) {
            addCriterion("OPEN_DATE <>", value, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateGreaterThan(String value) {
            addCriterion("OPEN_DATE >", value, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateGreaterThanOrEqualTo(String value) {
            addCriterion("OPEN_DATE >=", value, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateLessThan(String value) {
            addCriterion("OPEN_DATE <", value, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateLessThanOrEqualTo(String value) {
            addCriterion("OPEN_DATE <=", value, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateLike(String value) {
            addCriterion("OPEN_DATE like", value, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateNotLike(String value) {
            addCriterion("OPEN_DATE not like", value, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateIn(List<String> values) {
            addCriterion("OPEN_DATE in", values, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateNotIn(List<String> values) {
            addCriterion("OPEN_DATE not in", values, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateBetween(String value1, String value2) {
            addCriterion("OPEN_DATE between", value1, value2, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenDateNotBetween(String value1, String value2) {
            addCriterion("OPEN_DATE not between", value1, value2, "openDate");
            return (Criteria) this;
        }

        public Criteria andOpenCodeIsNull() {
            addCriterion("OPEN_CODE is null");
            return (Criteria) this;
        }

        public Criteria andOpenCodeIsNotNull() {
            addCriterion("OPEN_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andOpenCodeEqualTo(String value) {
            addCriterion("OPEN_CODE =", value, "openCode");
            return (Criteria) this;
        }

        public Criteria andOpenCodeNotEqualTo(String value) {
            addCriterion("OPEN_CODE <>", value, "openCode");
            return (Criteria) this;
        }

        public Criteria andOpenCodeGreaterThan(String value) {
            addCriterion("OPEN_CODE >", value, "openCode");
            return (Criteria) this;
        }

        public Criteria andOpenCodeGreaterThanOrEqualTo(String value) {
            addCriterion("OPEN_CODE >=", value, "openCode");
            return (Criteria) this;
        }

        public Criteria andOpenCodeLessThan(String value) {
            addCriterion("OPEN_CODE <", value, "openCode");
            return (Criteria) this;
        }

        public Criteria andOpenCodeLessThanOrEqualTo(String value) {
            addCriterion("OPEN_CODE <=", value, "openCode");
            return (Criteria) this;
        }

        public Criteria andOpenCodeLike(String value) {
            addCriterion("OPEN_CODE like", value, "openCode");
            return (Criteria) this;
        }

        public Criteria andOpenCodeNotLike(String value) {
            addCriterion("OPEN_CODE not like", value, "openCode");
            return (Criteria) this;
        }

        public Criteria andOpenCodeIn(List<String> values) {
            addCriterion("OPEN_CODE in", values, "openCode");
            return (Criteria) this;
        }

        public Criteria andOpenCodeNotIn(List<String> values) {
            addCriterion("OPEN_CODE not in", values, "openCode");
            return (Criteria) this;
        }

        public Criteria andOpenCodeBetween(String value1, String value2) {
            addCriterion("OPEN_CODE between", value1, value2, "openCode");
            return (Criteria) this;
        }

        public Criteria andOpenCodeNotBetween(String value1, String value2) {
            addCriterion("OPEN_CODE not between", value1, value2, "openCode");
            return (Criteria) this;
        }

        public Criteria andAuthorizeDateIsNull() {
            addCriterion("AUTHORIZE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andAuthorizeDateIsNotNull() {
            addCriterion("AUTHORIZE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorizeDateEqualTo(String value) {
            addCriterion("AUTHORIZE_DATE =", value, "authorizeDate");
            return (Criteria) this;
        }

        public Criteria andAuthorizeDateNotEqualTo(String value) {
            addCriterion("AUTHORIZE_DATE <>", value, "authorizeDate");
            return (Criteria) this;
        }

        public Criteria andAuthorizeDateGreaterThan(String value) {
            addCriterion("AUTHORIZE_DATE >", value, "authorizeDate");
            return (Criteria) this;
        }

        public Criteria andAuthorizeDateGreaterThanOrEqualTo(String value) {
            addCriterion("AUTHORIZE_DATE >=", value, "authorizeDate");
            return (Criteria) this;
        }

        public Criteria andAuthorizeDateLessThan(String value) {
            addCriterion("AUTHORIZE_DATE <", value, "authorizeDate");
            return (Criteria) this;
        }

        public Criteria andAuthorizeDateLessThanOrEqualTo(String value) {
            addCriterion("AUTHORIZE_DATE <=", value, "authorizeDate");
            return (Criteria) this;
        }

        public Criteria andAuthorizeDateLike(String value) {
            addCriterion("AUTHORIZE_DATE like", value, "authorizeDate");
            return (Criteria) this;
        }

        public Criteria andAuthorizeDateNotLike(String value) {
            addCriterion("AUTHORIZE_DATE not like", value, "authorizeDate");
            return (Criteria) this;
        }

        public Criteria andAuthorizeDateIn(List<String> values) {
            addCriterion("AUTHORIZE_DATE in", values, "authorizeDate");
            return (Criteria) this;
        }

        public Criteria andAuthorizeDateNotIn(List<String> values) {
            addCriterion("AUTHORIZE_DATE not in", values, "authorizeDate");
            return (Criteria) this;
        }

        public Criteria andAuthorizeDateBetween(String value1, String value2) {
            addCriterion("AUTHORIZE_DATE between", value1, value2, "authorizeDate");
            return (Criteria) this;
        }

        public Criteria andAuthorizeDateNotBetween(String value1, String value2) {
            addCriterion("AUTHORIZE_DATE not between", value1, value2, "authorizeDate");
            return (Criteria) this;
        }

        public Criteria andAuthorizeCodeIsNull() {
            addCriterion("AUTHORIZE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andAuthorizeCodeIsNotNull() {
            addCriterion("AUTHORIZE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorizeCodeEqualTo(String value) {
            addCriterion("AUTHORIZE_CODE =", value, "authorizeCode");
            return (Criteria) this;
        }

        public Criteria andAuthorizeCodeNotEqualTo(String value) {
            addCriterion("AUTHORIZE_CODE <>", value, "authorizeCode");
            return (Criteria) this;
        }

        public Criteria andAuthorizeCodeGreaterThan(String value) {
            addCriterion("AUTHORIZE_CODE >", value, "authorizeCode");
            return (Criteria) this;
        }

        public Criteria andAuthorizeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("AUTHORIZE_CODE >=", value, "authorizeCode");
            return (Criteria) this;
        }

        public Criteria andAuthorizeCodeLessThan(String value) {
            addCriterion("AUTHORIZE_CODE <", value, "authorizeCode");
            return (Criteria) this;
        }

        public Criteria andAuthorizeCodeLessThanOrEqualTo(String value) {
            addCriterion("AUTHORIZE_CODE <=", value, "authorizeCode");
            return (Criteria) this;
        }

        public Criteria andAuthorizeCodeLike(String value) {
            addCriterion("AUTHORIZE_CODE like", value, "authorizeCode");
            return (Criteria) this;
        }

        public Criteria andAuthorizeCodeNotLike(String value) {
            addCriterion("AUTHORIZE_CODE not like", value, "authorizeCode");
            return (Criteria) this;
        }

        public Criteria andAuthorizeCodeIn(List<String> values) {
            addCriterion("AUTHORIZE_CODE in", values, "authorizeCode");
            return (Criteria) this;
        }

        public Criteria andAuthorizeCodeNotIn(List<String> values) {
            addCriterion("AUTHORIZE_CODE not in", values, "authorizeCode");
            return (Criteria) this;
        }

        public Criteria andAuthorizeCodeBetween(String value1, String value2) {
            addCriterion("AUTHORIZE_CODE between", value1, value2, "authorizeCode");
            return (Criteria) this;
        }

        public Criteria andAuthorizeCodeNotBetween(String value1, String value2) {
            addCriterion("AUTHORIZE_CODE not between", value1, value2, "authorizeCode");
            return (Criteria) this;
        }

        public Criteria andCooperTypeIdIsNull() {
            addCriterion("COOPER_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCooperTypeIdIsNotNull() {
            addCriterion("COOPER_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCooperTypeIdEqualTo(String value) {
            addCriterion("COOPER_TYPE_ID =", value, "cooperTypeId");
            return (Criteria) this;
        }

        public Criteria andCooperTypeIdNotEqualTo(String value) {
            addCriterion("COOPER_TYPE_ID <>", value, "cooperTypeId");
            return (Criteria) this;
        }

        public Criteria andCooperTypeIdGreaterThan(String value) {
            addCriterion("COOPER_TYPE_ID >", value, "cooperTypeId");
            return (Criteria) this;
        }

        public Criteria andCooperTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("COOPER_TYPE_ID >=", value, "cooperTypeId");
            return (Criteria) this;
        }

        public Criteria andCooperTypeIdLessThan(String value) {
            addCriterion("COOPER_TYPE_ID <", value, "cooperTypeId");
            return (Criteria) this;
        }

        public Criteria andCooperTypeIdLessThanOrEqualTo(String value) {
            addCriterion("COOPER_TYPE_ID <=", value, "cooperTypeId");
            return (Criteria) this;
        }

        public Criteria andCooperTypeIdLike(String value) {
            addCriterion("COOPER_TYPE_ID like", value, "cooperTypeId");
            return (Criteria) this;
        }

        public Criteria andCooperTypeIdNotLike(String value) {
            addCriterion("COOPER_TYPE_ID not like", value, "cooperTypeId");
            return (Criteria) this;
        }

        public Criteria andCooperTypeIdIn(List<String> values) {
            addCriterion("COOPER_TYPE_ID in", values, "cooperTypeId");
            return (Criteria) this;
        }

        public Criteria andCooperTypeIdNotIn(List<String> values) {
            addCriterion("COOPER_TYPE_ID not in", values, "cooperTypeId");
            return (Criteria) this;
        }

        public Criteria andCooperTypeIdBetween(String value1, String value2) {
            addCriterion("COOPER_TYPE_ID between", value1, value2, "cooperTypeId");
            return (Criteria) this;
        }

        public Criteria andCooperTypeIdNotBetween(String value1, String value2) {
            addCriterion("COOPER_TYPE_ID not between", value1, value2, "cooperTypeId");
            return (Criteria) this;
        }

        public Criteria andCooperTypeNameIsNull() {
            addCriterion("COOPER_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCooperTypeNameIsNotNull() {
            addCriterion("COOPER_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCooperTypeNameEqualTo(String value) {
            addCriterion("COOPER_TYPE_NAME =", value, "cooperTypeName");
            return (Criteria) this;
        }

        public Criteria andCooperTypeNameNotEqualTo(String value) {
            addCriterion("COOPER_TYPE_NAME <>", value, "cooperTypeName");
            return (Criteria) this;
        }

        public Criteria andCooperTypeNameGreaterThan(String value) {
            addCriterion("COOPER_TYPE_NAME >", value, "cooperTypeName");
            return (Criteria) this;
        }

        public Criteria andCooperTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("COOPER_TYPE_NAME >=", value, "cooperTypeName");
            return (Criteria) this;
        }

        public Criteria andCooperTypeNameLessThan(String value) {
            addCriterion("COOPER_TYPE_NAME <", value, "cooperTypeName");
            return (Criteria) this;
        }

        public Criteria andCooperTypeNameLessThanOrEqualTo(String value) {
            addCriterion("COOPER_TYPE_NAME <=", value, "cooperTypeName");
            return (Criteria) this;
        }

        public Criteria andCooperTypeNameLike(String value) {
            addCriterion("COOPER_TYPE_NAME like", value, "cooperTypeName");
            return (Criteria) this;
        }

        public Criteria andCooperTypeNameNotLike(String value) {
            addCriterion("COOPER_TYPE_NAME not like", value, "cooperTypeName");
            return (Criteria) this;
        }

        public Criteria andCooperTypeNameIn(List<String> values) {
            addCriterion("COOPER_TYPE_NAME in", values, "cooperTypeName");
            return (Criteria) this;
        }

        public Criteria andCooperTypeNameNotIn(List<String> values) {
            addCriterion("COOPER_TYPE_NAME not in", values, "cooperTypeName");
            return (Criteria) this;
        }

        public Criteria andCooperTypeNameBetween(String value1, String value2) {
            addCriterion("COOPER_TYPE_NAME between", value1, value2, "cooperTypeName");
            return (Criteria) this;
        }

        public Criteria andCooperTypeNameNotBetween(String value1, String value2) {
            addCriterion("COOPER_TYPE_NAME not between", value1, value2, "cooperTypeName");
            return (Criteria) this;
        }

        public Criteria andPatenteeIsNull() {
            addCriterion("PATENTEE is null");
            return (Criteria) this;
        }

        public Criteria andPatenteeIsNotNull() {
            addCriterion("PATENTEE is not null");
            return (Criteria) this;
        }

        public Criteria andPatenteeEqualTo(String value) {
            addCriterion("PATENTEE =", value, "patentee");
            return (Criteria) this;
        }

        public Criteria andPatenteeNotEqualTo(String value) {
            addCriterion("PATENTEE <>", value, "patentee");
            return (Criteria) this;
        }

        public Criteria andPatenteeGreaterThan(String value) {
            addCriterion("PATENTEE >", value, "patentee");
            return (Criteria) this;
        }

        public Criteria andPatenteeGreaterThanOrEqualTo(String value) {
            addCriterion("PATENTEE >=", value, "patentee");
            return (Criteria) this;
        }

        public Criteria andPatenteeLessThan(String value) {
            addCriterion("PATENTEE <", value, "patentee");
            return (Criteria) this;
        }

        public Criteria andPatenteeLessThanOrEqualTo(String value) {
            addCriterion("PATENTEE <=", value, "patentee");
            return (Criteria) this;
        }

        public Criteria andPatenteeLike(String value) {
            addCriterion("PATENTEE like", value, "patentee");
            return (Criteria) this;
        }

        public Criteria andPatenteeNotLike(String value) {
            addCriterion("PATENTEE not like", value, "patentee");
            return (Criteria) this;
        }

        public Criteria andPatenteeIn(List<String> values) {
            addCriterion("PATENTEE in", values, "patentee");
            return (Criteria) this;
        }

        public Criteria andPatenteeNotIn(List<String> values) {
            addCriterion("PATENTEE not in", values, "patentee");
            return (Criteria) this;
        }

        public Criteria andPatenteeBetween(String value1, String value2) {
            addCriterion("PATENTEE between", value1, value2, "patentee");
            return (Criteria) this;
        }

        public Criteria andPatenteeNotBetween(String value1, String value2) {
            addCriterion("PATENTEE not between", value1, value2, "patentee");
            return (Criteria) this;
        }

        public Criteria andIsExpiredIsNull() {
            addCriterion("IS_EXPIRED is null");
            return (Criteria) this;
        }

        public Criteria andIsExpiredIsNotNull() {
            addCriterion("IS_EXPIRED is not null");
            return (Criteria) this;
        }

        public Criteria andIsExpiredEqualTo(String value) {
            addCriterion("IS_EXPIRED =", value, "isExpired");
            return (Criteria) this;
        }

        public Criteria andIsExpiredNotEqualTo(String value) {
            addCriterion("IS_EXPIRED <>", value, "isExpired");
            return (Criteria) this;
        }

        public Criteria andIsExpiredGreaterThan(String value) {
            addCriterion("IS_EXPIRED >", value, "isExpired");
            return (Criteria) this;
        }

        public Criteria andIsExpiredGreaterThanOrEqualTo(String value) {
            addCriterion("IS_EXPIRED >=", value, "isExpired");
            return (Criteria) this;
        }

        public Criteria andIsExpiredLessThan(String value) {
            addCriterion("IS_EXPIRED <", value, "isExpired");
            return (Criteria) this;
        }

        public Criteria andIsExpiredLessThanOrEqualTo(String value) {
            addCriterion("IS_EXPIRED <=", value, "isExpired");
            return (Criteria) this;
        }

        public Criteria andIsExpiredLike(String value) {
            addCriterion("IS_EXPIRED like", value, "isExpired");
            return (Criteria) this;
        }

        public Criteria andIsExpiredNotLike(String value) {
            addCriterion("IS_EXPIRED not like", value, "isExpired");
            return (Criteria) this;
        }

        public Criteria andIsExpiredIn(List<String> values) {
            addCriterion("IS_EXPIRED in", values, "isExpired");
            return (Criteria) this;
        }

        public Criteria andIsExpiredNotIn(List<String> values) {
            addCriterion("IS_EXPIRED not in", values, "isExpired");
            return (Criteria) this;
        }

        public Criteria andIsExpiredBetween(String value1, String value2) {
            addCriterion("IS_EXPIRED between", value1, value2, "isExpired");
            return (Criteria) this;
        }

        public Criteria andIsExpiredNotBetween(String value1, String value2) {
            addCriterion("IS_EXPIRED not between", value1, value2, "isExpired");
            return (Criteria) this;
        }

        public Criteria andIsPostIsNull() {
            addCriterion("IS_POST is null");
            return (Criteria) this;
        }

        public Criteria andIsPostIsNotNull() {
            addCriterion("IS_POST is not null");
            return (Criteria) this;
        }

        public Criteria andIsPostEqualTo(String value) {
            addCriterion("IS_POST =", value, "isPost");
            return (Criteria) this;
        }

        public Criteria andIsPostNotEqualTo(String value) {
            addCriterion("IS_POST <>", value, "isPost");
            return (Criteria) this;
        }

        public Criteria andIsPostGreaterThan(String value) {
            addCriterion("IS_POST >", value, "isPost");
            return (Criteria) this;
        }

        public Criteria andIsPostGreaterThanOrEqualTo(String value) {
            addCriterion("IS_POST >=", value, "isPost");
            return (Criteria) this;
        }

        public Criteria andIsPostLessThan(String value) {
            addCriterion("IS_POST <", value, "isPost");
            return (Criteria) this;
        }

        public Criteria andIsPostLessThanOrEqualTo(String value) {
            addCriterion("IS_POST <=", value, "isPost");
            return (Criteria) this;
        }

        public Criteria andIsPostLike(String value) {
            addCriterion("IS_POST like", value, "isPost");
            return (Criteria) this;
        }

        public Criteria andIsPostNotLike(String value) {
            addCriterion("IS_POST not like", value, "isPost");
            return (Criteria) this;
        }

        public Criteria andIsPostIn(List<String> values) {
            addCriterion("IS_POST in", values, "isPost");
            return (Criteria) this;
        }

        public Criteria andIsPostNotIn(List<String> values) {
            addCriterion("IS_POST not in", values, "isPost");
            return (Criteria) this;
        }

        public Criteria andIsPostBetween(String value1, String value2) {
            addCriterion("IS_POST between", value1, value2, "isPost");
            return (Criteria) this;
        }

        public Criteria andIsPostNotBetween(String value1, String value2) {
            addCriterion("IS_POST not between", value1, value2, "isPost");
            return (Criteria) this;
        }

        public Criteria andIsPtcIsNull() {
            addCriterion("IS_PTC is null");
            return (Criteria) this;
        }

        public Criteria andIsPtcIsNotNull() {
            addCriterion("IS_PTC is not null");
            return (Criteria) this;
        }

        public Criteria andIsPtcEqualTo(String value) {
            addCriterion("IS_PTC =", value, "isPtc");
            return (Criteria) this;
        }

        public Criteria andIsPtcNotEqualTo(String value) {
            addCriterion("IS_PTC <>", value, "isPtc");
            return (Criteria) this;
        }

        public Criteria andIsPtcGreaterThan(String value) {
            addCriterion("IS_PTC >", value, "isPtc");
            return (Criteria) this;
        }

        public Criteria andIsPtcGreaterThanOrEqualTo(String value) {
            addCriterion("IS_PTC >=", value, "isPtc");
            return (Criteria) this;
        }

        public Criteria andIsPtcLessThan(String value) {
            addCriterion("IS_PTC <", value, "isPtc");
            return (Criteria) this;
        }

        public Criteria andIsPtcLessThanOrEqualTo(String value) {
            addCriterion("IS_PTC <=", value, "isPtc");
            return (Criteria) this;
        }

        public Criteria andIsPtcLike(String value) {
            addCriterion("IS_PTC like", value, "isPtc");
            return (Criteria) this;
        }

        public Criteria andIsPtcNotLike(String value) {
            addCriterion("IS_PTC not like", value, "isPtc");
            return (Criteria) this;
        }

        public Criteria andIsPtcIn(List<String> values) {
            addCriterion("IS_PTC in", values, "isPtc");
            return (Criteria) this;
        }

        public Criteria andIsPtcNotIn(List<String> values) {
            addCriterion("IS_PTC not in", values, "isPtc");
            return (Criteria) this;
        }

        public Criteria andIsPtcBetween(String value1, String value2) {
            addCriterion("IS_PTC between", value1, value2, "isPtc");
            return (Criteria) this;
        }

        public Criteria andIsPtcNotBetween(String value1, String value2) {
            addCriterion("IS_PTC not between", value1, value2, "isPtc");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowIsNull() {
            addCriterion("APPLY_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowIsNotNull() {
            addCriterion("APPLY_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW =", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW <>", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowGreaterThan(String value) {
            addCriterion("APPLY_USER_FLOW >", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW >=", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowLessThan(String value) {
            addCriterion("APPLY_USER_FLOW <", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowLessThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW <=", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowLike(String value) {
            addCriterion("APPLY_USER_FLOW like", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotLike(String value) {
            addCriterion("APPLY_USER_FLOW not like", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowIn(List<String> values) {
            addCriterion("APPLY_USER_FLOW in", values, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotIn(List<String> values) {
            addCriterion("APPLY_USER_FLOW not in", values, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowBetween(String value1, String value2) {
            addCriterion("APPLY_USER_FLOW between", value1, value2, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotBetween(String value1, String value2) {
            addCriterion("APPLY_USER_FLOW not between", value1, value2, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameIsNull() {
            addCriterion("APPLY_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameIsNotNull() {
            addCriterion("APPLY_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameEqualTo(String value) {
            addCriterion("APPLY_USER_NAME =", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotEqualTo(String value) {
            addCriterion("APPLY_USER_NAME <>", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameGreaterThan(String value) {
            addCriterion("APPLY_USER_NAME >", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_NAME >=", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameLessThan(String value) {
            addCriterion("APPLY_USER_NAME <", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameLessThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_NAME <=", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameLike(String value) {
            addCriterion("APPLY_USER_NAME like", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotLike(String value) {
            addCriterion("APPLY_USER_NAME not like", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameIn(List<String> values) {
            addCriterion("APPLY_USER_NAME in", values, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotIn(List<String> values) {
            addCriterion("APPLY_USER_NAME not in", values, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameBetween(String value1, String value2) {
            addCriterion("APPLY_USER_NAME between", value1, value2, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotBetween(String value1, String value2) {
            addCriterion("APPLY_USER_NAME not between", value1, value2, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowIsNull() {
            addCriterion("APPLY_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowIsNotNull() {
            addCriterion("APPLY_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowEqualTo(String value) {
            addCriterion("APPLY_ORG_FLOW =", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowNotEqualTo(String value) {
            addCriterion("APPLY_ORG_FLOW <>", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowGreaterThan(String value) {
            addCriterion("APPLY_ORG_FLOW >", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_ORG_FLOW >=", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowLessThan(String value) {
            addCriterion("APPLY_ORG_FLOW <", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("APPLY_ORG_FLOW <=", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowLike(String value) {
            addCriterion("APPLY_ORG_FLOW like", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowNotLike(String value) {
            addCriterion("APPLY_ORG_FLOW not like", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowIn(List<String> values) {
            addCriterion("APPLY_ORG_FLOW in", values, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowNotIn(List<String> values) {
            addCriterion("APPLY_ORG_FLOW not in", values, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowBetween(String value1, String value2) {
            addCriterion("APPLY_ORG_FLOW between", value1, value2, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowNotBetween(String value1, String value2) {
            addCriterion("APPLY_ORG_FLOW not between", value1, value2, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameIsNull() {
            addCriterion("APPLY_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameIsNotNull() {
            addCriterion("APPLY_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameEqualTo(String value) {
            addCriterion("APPLY_ORG_NAME =", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameNotEqualTo(String value) {
            addCriterion("APPLY_ORG_NAME <>", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameGreaterThan(String value) {
            addCriterion("APPLY_ORG_NAME >", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_ORG_NAME >=", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameLessThan(String value) {
            addCriterion("APPLY_ORG_NAME <", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameLessThanOrEqualTo(String value) {
            addCriterion("APPLY_ORG_NAME <=", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameLike(String value) {
            addCriterion("APPLY_ORG_NAME like", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameNotLike(String value) {
            addCriterion("APPLY_ORG_NAME not like", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameIn(List<String> values) {
            addCriterion("APPLY_ORG_NAME in", values, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameNotIn(List<String> values) {
            addCriterion("APPLY_ORG_NAME not in", values, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameBetween(String value1, String value2) {
            addCriterion("APPLY_ORG_NAME between", value1, value2, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameNotBetween(String value1, String value2) {
            addCriterion("APPLY_ORG_NAME not between", value1, value2, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdIsNull() {
            addCriterion("OPER_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdIsNotNull() {
            addCriterion("OPER_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdEqualTo(String value) {
            addCriterion("OPER_STATUS_ID =", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotEqualTo(String value) {
            addCriterion("OPER_STATUS_ID <>", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdGreaterThan(String value) {
            addCriterion("OPER_STATUS_ID >", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_ID >=", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdLessThan(String value) {
            addCriterion("OPER_STATUS_ID <", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdLessThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_ID <=", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdLike(String value) {
            addCriterion("OPER_STATUS_ID like", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotLike(String value) {
            addCriterion("OPER_STATUS_ID not like", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdIn(List<String> values) {
            addCriterion("OPER_STATUS_ID in", values, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotIn(List<String> values) {
            addCriterion("OPER_STATUS_ID not in", values, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_ID between", value1, value2, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_ID not between", value1, value2, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameIsNull() {
            addCriterion("OPER_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameIsNotNull() {
            addCriterion("OPER_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME =", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME <>", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameGreaterThan(String value) {
            addCriterion("OPER_STATUS_NAME >", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME >=", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameLessThan(String value) {
            addCriterion("OPER_STATUS_NAME <", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameLessThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME <=", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameLike(String value) {
            addCriterion("OPER_STATUS_NAME like", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotLike(String value) {
            addCriterion("OPER_STATUS_NAME not like", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameIn(List<String> values) {
            addCriterion("OPER_STATUS_NAME in", values, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotIn(List<String> values) {
            addCriterion("OPER_STATUS_NAME not in", values, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_NAME between", value1, value2, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_NAME not between", value1, value2, "operStatusName");
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

        public Criteria andAuthorizeYearIsNull() {
            addCriterion("AUTHORIZE_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearIsNotNull() {
            addCriterion("AUTHORIZE_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearEqualTo(String value) {
            addCriterion("AUTHORIZE_YEAR =", value, "authorizeYear");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearNotEqualTo(String value) {
            addCriterion("AUTHORIZE_YEAR <>", value, "authorizeYear");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearGreaterThan(String value) {
            addCriterion("AUTHORIZE_YEAR >", value, "authorizeYear");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearGreaterThanOrEqualTo(String value) {
            addCriterion("AUTHORIZE_YEAR >=", value, "authorizeYear");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearLessThan(String value) {
            addCriterion("AUTHORIZE_YEAR <", value, "authorizeYear");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearLessThanOrEqualTo(String value) {
            addCriterion("AUTHORIZE_YEAR <=", value, "authorizeYear");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearLike(String value) {
            addCriterion("AUTHORIZE_YEAR like", value, "authorizeYear");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearNotLike(String value) {
            addCriterion("AUTHORIZE_YEAR not like", value, "authorizeYear");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearIn(List<String> values) {
            addCriterion("AUTHORIZE_YEAR in", values, "authorizeYear");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearNotIn(List<String> values) {
            addCriterion("AUTHORIZE_YEAR not in", values, "authorizeYear");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearBetween(String value1, String value2) {
            addCriterion("AUTHORIZE_YEAR between", value1, value2, "authorizeYear");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearNotBetween(String value1, String value2) {
            addCriterion("AUTHORIZE_YEAR not between", value1, value2, "authorizeYear");
            return (Criteria) this;
        }

        public Criteria andBranchIdIsNull() {
            addCriterion("BRANCH_ID is null");
            return (Criteria) this;
        }

        public Criteria andBranchIdIsNotNull() {
            addCriterion("BRANCH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBranchIdEqualTo(String value) {
            addCriterion("BRANCH_ID =", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdNotEqualTo(String value) {
            addCriterion("BRANCH_ID <>", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdGreaterThan(String value) {
            addCriterion("BRANCH_ID >", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdGreaterThanOrEqualTo(String value) {
            addCriterion("BRANCH_ID >=", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdLessThan(String value) {
            addCriterion("BRANCH_ID <", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdLessThanOrEqualTo(String value) {
            addCriterion("BRANCH_ID <=", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdLike(String value) {
            addCriterion("BRANCH_ID like", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdNotLike(String value) {
            addCriterion("BRANCH_ID not like", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdIn(List<String> values) {
            addCriterion("BRANCH_ID in", values, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdNotIn(List<String> values) {
            addCriterion("BRANCH_ID not in", values, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdBetween(String value1, String value2) {
            addCriterion("BRANCH_ID between", value1, value2, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdNotBetween(String value1, String value2) {
            addCriterion("BRANCH_ID not between", value1, value2, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchNameIsNull() {
            addCriterion("BRANCH_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBranchNameIsNotNull() {
            addCriterion("BRANCH_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBranchNameEqualTo(String value) {
            addCriterion("BRANCH_NAME =", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotEqualTo(String value) {
            addCriterion("BRANCH_NAME <>", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameGreaterThan(String value) {
            addCriterion("BRANCH_NAME >", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameGreaterThanOrEqualTo(String value) {
            addCriterion("BRANCH_NAME >=", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameLessThan(String value) {
            addCriterion("BRANCH_NAME <", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameLessThanOrEqualTo(String value) {
            addCriterion("BRANCH_NAME <=", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameLike(String value) {
            addCriterion("BRANCH_NAME like", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotLike(String value) {
            addCriterion("BRANCH_NAME not like", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameIn(List<String> values) {
            addCriterion("BRANCH_NAME in", values, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotIn(List<String> values) {
            addCriterion("BRANCH_NAME not in", values, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameBetween(String value1, String value2) {
            addCriterion("BRANCH_NAME between", value1, value2, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotBetween(String value1, String value2) {
            addCriterion("BRANCH_NAME not between", value1, value2, "branchName");
            return (Criteria) this;
        }

        public Criteria andJobNumberIsNull() {
            addCriterion("JOB_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andJobNumberIsNotNull() {
            addCriterion("JOB_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andJobNumberEqualTo(String value) {
            addCriterion("JOB_NUMBER =", value, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberNotEqualTo(String value) {
            addCriterion("JOB_NUMBER <>", value, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberGreaterThan(String value) {
            addCriterion("JOB_NUMBER >", value, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberGreaterThanOrEqualTo(String value) {
            addCriterion("JOB_NUMBER >=", value, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberLessThan(String value) {
            addCriterion("JOB_NUMBER <", value, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberLessThanOrEqualTo(String value) {
            addCriterion("JOB_NUMBER <=", value, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberLike(String value) {
            addCriterion("JOB_NUMBER like", value, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberNotLike(String value) {
            addCriterion("JOB_NUMBER not like", value, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberIn(List<String> values) {
            addCriterion("JOB_NUMBER in", values, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberNotIn(List<String> values) {
            addCriterion("JOB_NUMBER not in", values, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberBetween(String value1, String value2) {
            addCriterion("JOB_NUMBER between", value1, value2, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberNotBetween(String value1, String value2) {
            addCriterion("JOB_NUMBER not between", value1, value2, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andIsPropProjIsNull() {
            addCriterion("IS_PROP_PROJ is null");
            return (Criteria) this;
        }

        public Criteria andIsPropProjIsNotNull() {
            addCriterion("IS_PROP_PROJ is not null");
            return (Criteria) this;
        }

        public Criteria andIsPropProjEqualTo(String value) {
            addCriterion("IS_PROP_PROJ =", value, "isPropProj");
            return (Criteria) this;
        }

        public Criteria andIsPropProjNotEqualTo(String value) {
            addCriterion("IS_PROP_PROJ <>", value, "isPropProj");
            return (Criteria) this;
        }

        public Criteria andIsPropProjGreaterThan(String value) {
            addCriterion("IS_PROP_PROJ >", value, "isPropProj");
            return (Criteria) this;
        }

        public Criteria andIsPropProjGreaterThanOrEqualTo(String value) {
            addCriterion("IS_PROP_PROJ >=", value, "isPropProj");
            return (Criteria) this;
        }

        public Criteria andIsPropProjLessThan(String value) {
            addCriterion("IS_PROP_PROJ <", value, "isPropProj");
            return (Criteria) this;
        }

        public Criteria andIsPropProjLessThanOrEqualTo(String value) {
            addCriterion("IS_PROP_PROJ <=", value, "isPropProj");
            return (Criteria) this;
        }

        public Criteria andIsPropProjLike(String value) {
            addCriterion("IS_PROP_PROJ like", value, "isPropProj");
            return (Criteria) this;
        }

        public Criteria andIsPropProjNotLike(String value) {
            addCriterion("IS_PROP_PROJ not like", value, "isPropProj");
            return (Criteria) this;
        }

        public Criteria andIsPropProjIn(List<String> values) {
            addCriterion("IS_PROP_PROJ in", values, "isPropProj");
            return (Criteria) this;
        }

        public Criteria andIsPropProjNotIn(List<String> values) {
            addCriterion("IS_PROP_PROJ not in", values, "isPropProj");
            return (Criteria) this;
        }

        public Criteria andIsPropProjBetween(String value1, String value2) {
            addCriterion("IS_PROP_PROJ between", value1, value2, "isPropProj");
            return (Criteria) this;
        }

        public Criteria andIsPropProjNotBetween(String value1, String value2) {
            addCriterion("IS_PROP_PROJ not between", value1, value2, "isPropProj");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowIsNull() {
            addCriterion("PROP_PROJ_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowIsNotNull() {
            addCriterion("PROP_PROJ_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowEqualTo(String value) {
            addCriterion("PROP_PROJ_FLOW =", value, "propProjFlow");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowNotEqualTo(String value) {
            addCriterion("PROP_PROJ_FLOW <>", value, "propProjFlow");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowGreaterThan(String value) {
            addCriterion("PROP_PROJ_FLOW >", value, "propProjFlow");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_PROJ_FLOW >=", value, "propProjFlow");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowLessThan(String value) {
            addCriterion("PROP_PROJ_FLOW <", value, "propProjFlow");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowLessThanOrEqualTo(String value) {
            addCriterion("PROP_PROJ_FLOW <=", value, "propProjFlow");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowLike(String value) {
            addCriterion("PROP_PROJ_FLOW like", value, "propProjFlow");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowNotLike(String value) {
            addCriterion("PROP_PROJ_FLOW not like", value, "propProjFlow");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowIn(List<String> values) {
            addCriterion("PROP_PROJ_FLOW in", values, "propProjFlow");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowNotIn(List<String> values) {
            addCriterion("PROP_PROJ_FLOW not in", values, "propProjFlow");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowBetween(String value1, String value2) {
            addCriterion("PROP_PROJ_FLOW between", value1, value2, "propProjFlow");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowNotBetween(String value1, String value2) {
            addCriterion("PROP_PROJ_FLOW not between", value1, value2, "propProjFlow");
            return (Criteria) this;
        }

        public Criteria andPropProjNameIsNull() {
            addCriterion("PROP_PROJ_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPropProjNameIsNotNull() {
            addCriterion("PROP_PROJ_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPropProjNameEqualTo(String value) {
            addCriterion("PROP_PROJ_NAME =", value, "propProjName");
            return (Criteria) this;
        }

        public Criteria andPropProjNameNotEqualTo(String value) {
            addCriterion("PROP_PROJ_NAME <>", value, "propProjName");
            return (Criteria) this;
        }

        public Criteria andPropProjNameGreaterThan(String value) {
            addCriterion("PROP_PROJ_NAME >", value, "propProjName");
            return (Criteria) this;
        }

        public Criteria andPropProjNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_PROJ_NAME >=", value, "propProjName");
            return (Criteria) this;
        }

        public Criteria andPropProjNameLessThan(String value) {
            addCriterion("PROP_PROJ_NAME <", value, "propProjName");
            return (Criteria) this;
        }

        public Criteria andPropProjNameLessThanOrEqualTo(String value) {
            addCriterion("PROP_PROJ_NAME <=", value, "propProjName");
            return (Criteria) this;
        }

        public Criteria andPropProjNameLike(String value) {
            addCriterion("PROP_PROJ_NAME like", value, "propProjName");
            return (Criteria) this;
        }

        public Criteria andPropProjNameNotLike(String value) {
            addCriterion("PROP_PROJ_NAME not like", value, "propProjName");
            return (Criteria) this;
        }

        public Criteria andPropProjNameIn(List<String> values) {
            addCriterion("PROP_PROJ_NAME in", values, "propProjName");
            return (Criteria) this;
        }

        public Criteria andPropProjNameNotIn(List<String> values) {
            addCriterion("PROP_PROJ_NAME not in", values, "propProjName");
            return (Criteria) this;
        }

        public Criteria andPropProjNameBetween(String value1, String value2) {
            addCriterion("PROP_PROJ_NAME between", value1, value2, "propProjName");
            return (Criteria) this;
        }

        public Criteria andPropProjNameNotBetween(String value1, String value2) {
            addCriterion("PROP_PROJ_NAME not between", value1, value2, "propProjName");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdIsNull() {
            addCriterion("PROJ_SOURCE_ID is null");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdIsNotNull() {
            addCriterion("PROJ_SOURCE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdEqualTo(String value) {
            addCriterion("PROJ_SOURCE_ID =", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdNotEqualTo(String value) {
            addCriterion("PROJ_SOURCE_ID <>", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdGreaterThan(String value) {
            addCriterion("PROJ_SOURCE_ID >", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_SOURCE_ID >=", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdLessThan(String value) {
            addCriterion("PROJ_SOURCE_ID <", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdLessThanOrEqualTo(String value) {
            addCriterion("PROJ_SOURCE_ID <=", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdLike(String value) {
            addCriterion("PROJ_SOURCE_ID like", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdNotLike(String value) {
            addCriterion("PROJ_SOURCE_ID not like", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdIn(List<String> values) {
            addCriterion("PROJ_SOURCE_ID in", values, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdNotIn(List<String> values) {
            addCriterion("PROJ_SOURCE_ID not in", values, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdBetween(String value1, String value2) {
            addCriterion("PROJ_SOURCE_ID between", value1, value2, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdNotBetween(String value1, String value2) {
            addCriterion("PROJ_SOURCE_ID not between", value1, value2, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameIsNull() {
            addCriterion("PROJ_SOURCE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameIsNotNull() {
            addCriterion("PROJ_SOURCE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameEqualTo(String value) {
            addCriterion("PROJ_SOURCE_NAME =", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameNotEqualTo(String value) {
            addCriterion("PROJ_SOURCE_NAME <>", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameGreaterThan(String value) {
            addCriterion("PROJ_SOURCE_NAME >", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_SOURCE_NAME >=", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameLessThan(String value) {
            addCriterion("PROJ_SOURCE_NAME <", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameLessThanOrEqualTo(String value) {
            addCriterion("PROJ_SOURCE_NAME <=", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameLike(String value) {
            addCriterion("PROJ_SOURCE_NAME like", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameNotLike(String value) {
            addCriterion("PROJ_SOURCE_NAME not like", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameIn(List<String> values) {
            addCriterion("PROJ_SOURCE_NAME in", values, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameNotIn(List<String> values) {
            addCriterion("PROJ_SOURCE_NAME not in", values, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameBetween(String value1, String value2) {
            addCriterion("PROJ_SOURCE_NAME between", value1, value2, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameNotBetween(String value1, String value2) {
            addCriterion("PROJ_SOURCE_NAME not between", value1, value2, "projSourceName");
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