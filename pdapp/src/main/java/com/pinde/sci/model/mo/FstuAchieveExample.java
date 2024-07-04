package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class FstuAchieveExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FstuAchieveExample() {
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

        public Criteria andAchieveFlowIsNull() {
            addCriterion("ACHIEVE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAchieveFlowIsNotNull() {
            addCriterion("ACHIEVE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAchieveFlowEqualTo(String value) {
            addCriterion("ACHIEVE_FLOW =", value, "achieveFlow");
            return (Criteria) this;
        }

        public Criteria andAchieveFlowNotEqualTo(String value) {
            addCriterion("ACHIEVE_FLOW <>", value, "achieveFlow");
            return (Criteria) this;
        }

        public Criteria andAchieveFlowGreaterThan(String value) {
            addCriterion("ACHIEVE_FLOW >", value, "achieveFlow");
            return (Criteria) this;
        }

        public Criteria andAchieveFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ACHIEVE_FLOW >=", value, "achieveFlow");
            return (Criteria) this;
        }

        public Criteria andAchieveFlowLessThan(String value) {
            addCriterion("ACHIEVE_FLOW <", value, "achieveFlow");
            return (Criteria) this;
        }

        public Criteria andAchieveFlowLessThanOrEqualTo(String value) {
            addCriterion("ACHIEVE_FLOW <=", value, "achieveFlow");
            return (Criteria) this;
        }

        public Criteria andAchieveFlowLike(String value) {
            addCriterion("ACHIEVE_FLOW like", value, "achieveFlow");
            return (Criteria) this;
        }

        public Criteria andAchieveFlowNotLike(String value) {
            addCriterion("ACHIEVE_FLOW not like", value, "achieveFlow");
            return (Criteria) this;
        }

        public Criteria andAchieveFlowIn(List<String> values) {
            addCriterion("ACHIEVE_FLOW in", values, "achieveFlow");
            return (Criteria) this;
        }

        public Criteria andAchieveFlowNotIn(List<String> values) {
            addCriterion("ACHIEVE_FLOW not in", values, "achieveFlow");
            return (Criteria) this;
        }

        public Criteria andAchieveFlowBetween(String value1, String value2) {
            addCriterion("ACHIEVE_FLOW between", value1, value2, "achieveFlow");
            return (Criteria) this;
        }

        public Criteria andAchieveFlowNotBetween(String value1, String value2) {
            addCriterion("ACHIEVE_FLOW not between", value1, value2, "achieveFlow");
            return (Criteria) this;
        }

        public Criteria andAchieveNameIsNull() {
            addCriterion("ACHIEVE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAchieveNameIsNotNull() {
            addCriterion("ACHIEVE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAchieveNameEqualTo(String value) {
            addCriterion("ACHIEVE_NAME =", value, "achieveName");
            return (Criteria) this;
        }

        public Criteria andAchieveNameNotEqualTo(String value) {
            addCriterion("ACHIEVE_NAME <>", value, "achieveName");
            return (Criteria) this;
        }

        public Criteria andAchieveNameGreaterThan(String value) {
            addCriterion("ACHIEVE_NAME >", value, "achieveName");
            return (Criteria) this;
        }

        public Criteria andAchieveNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACHIEVE_NAME >=", value, "achieveName");
            return (Criteria) this;
        }

        public Criteria andAchieveNameLessThan(String value) {
            addCriterion("ACHIEVE_NAME <", value, "achieveName");
            return (Criteria) this;
        }

        public Criteria andAchieveNameLessThanOrEqualTo(String value) {
            addCriterion("ACHIEVE_NAME <=", value, "achieveName");
            return (Criteria) this;
        }

        public Criteria andAchieveNameLike(String value) {
            addCriterion("ACHIEVE_NAME like", value, "achieveName");
            return (Criteria) this;
        }

        public Criteria andAchieveNameNotLike(String value) {
            addCriterion("ACHIEVE_NAME not like", value, "achieveName");
            return (Criteria) this;
        }

        public Criteria andAchieveNameIn(List<String> values) {
            addCriterion("ACHIEVE_NAME in", values, "achieveName");
            return (Criteria) this;
        }

        public Criteria andAchieveNameNotIn(List<String> values) {
            addCriterion("ACHIEVE_NAME not in", values, "achieveName");
            return (Criteria) this;
        }

        public Criteria andAchieveNameBetween(String value1, String value2) {
            addCriterion("ACHIEVE_NAME between", value1, value2, "achieveName");
            return (Criteria) this;
        }

        public Criteria andAchieveNameNotBetween(String value1, String value2) {
            addCriterion("ACHIEVE_NAME not between", value1, value2, "achieveName");
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

        public Criteria andAchTypeIdIsNull() {
            addCriterion("ACH_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andAchTypeIdIsNotNull() {
            addCriterion("ACH_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAchTypeIdEqualTo(String value) {
            addCriterion("ACH_TYPE_ID =", value, "achTypeId");
            return (Criteria) this;
        }

        public Criteria andAchTypeIdNotEqualTo(String value) {
            addCriterion("ACH_TYPE_ID <>", value, "achTypeId");
            return (Criteria) this;
        }

        public Criteria andAchTypeIdGreaterThan(String value) {
            addCriterion("ACH_TYPE_ID >", value, "achTypeId");
            return (Criteria) this;
        }

        public Criteria andAchTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("ACH_TYPE_ID >=", value, "achTypeId");
            return (Criteria) this;
        }

        public Criteria andAchTypeIdLessThan(String value) {
            addCriterion("ACH_TYPE_ID <", value, "achTypeId");
            return (Criteria) this;
        }

        public Criteria andAchTypeIdLessThanOrEqualTo(String value) {
            addCriterion("ACH_TYPE_ID <=", value, "achTypeId");
            return (Criteria) this;
        }

        public Criteria andAchTypeIdLike(String value) {
            addCriterion("ACH_TYPE_ID like", value, "achTypeId");
            return (Criteria) this;
        }

        public Criteria andAchTypeIdNotLike(String value) {
            addCriterion("ACH_TYPE_ID not like", value, "achTypeId");
            return (Criteria) this;
        }

        public Criteria andAchTypeIdIn(List<String> values) {
            addCriterion("ACH_TYPE_ID in", values, "achTypeId");
            return (Criteria) this;
        }

        public Criteria andAchTypeIdNotIn(List<String> values) {
            addCriterion("ACH_TYPE_ID not in", values, "achTypeId");
            return (Criteria) this;
        }

        public Criteria andAchTypeIdBetween(String value1, String value2) {
            addCriterion("ACH_TYPE_ID between", value1, value2, "achTypeId");
            return (Criteria) this;
        }

        public Criteria andAchTypeIdNotBetween(String value1, String value2) {
            addCriterion("ACH_TYPE_ID not between", value1, value2, "achTypeId");
            return (Criteria) this;
        }

        public Criteria andAchTypeNameIsNull() {
            addCriterion("ACH_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAchTypeNameIsNotNull() {
            addCriterion("ACH_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAchTypeNameEqualTo(String value) {
            addCriterion("ACH_TYPE_NAME =", value, "achTypeName");
            return (Criteria) this;
        }

        public Criteria andAchTypeNameNotEqualTo(String value) {
            addCriterion("ACH_TYPE_NAME <>", value, "achTypeName");
            return (Criteria) this;
        }

        public Criteria andAchTypeNameGreaterThan(String value) {
            addCriterion("ACH_TYPE_NAME >", value, "achTypeName");
            return (Criteria) this;
        }

        public Criteria andAchTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACH_TYPE_NAME >=", value, "achTypeName");
            return (Criteria) this;
        }

        public Criteria andAchTypeNameLessThan(String value) {
            addCriterion("ACH_TYPE_NAME <", value, "achTypeName");
            return (Criteria) this;
        }

        public Criteria andAchTypeNameLessThanOrEqualTo(String value) {
            addCriterion("ACH_TYPE_NAME <=", value, "achTypeName");
            return (Criteria) this;
        }

        public Criteria andAchTypeNameLike(String value) {
            addCriterion("ACH_TYPE_NAME like", value, "achTypeName");
            return (Criteria) this;
        }

        public Criteria andAchTypeNameNotLike(String value) {
            addCriterion("ACH_TYPE_NAME not like", value, "achTypeName");
            return (Criteria) this;
        }

        public Criteria andAchTypeNameIn(List<String> values) {
            addCriterion("ACH_TYPE_NAME in", values, "achTypeName");
            return (Criteria) this;
        }

        public Criteria andAchTypeNameNotIn(List<String> values) {
            addCriterion("ACH_TYPE_NAME not in", values, "achTypeName");
            return (Criteria) this;
        }

        public Criteria andAchTypeNameBetween(String value1, String value2) {
            addCriterion("ACH_TYPE_NAME between", value1, value2, "achTypeName");
            return (Criteria) this;
        }

        public Criteria andAchTypeNameNotBetween(String value1, String value2) {
            addCriterion("ACH_TYPE_NAME not between", value1, value2, "achTypeName");
            return (Criteria) this;
        }

        public Criteria andPrizedDateIsNull() {
            addCriterion("PRIZED_DATE is null");
            return (Criteria) this;
        }

        public Criteria andPrizedDateIsNotNull() {
            addCriterion("PRIZED_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andPrizedDateEqualTo(String value) {
            addCriterion("PRIZED_DATE =", value, "prizedDate");
            return (Criteria) this;
        }

        public Criteria andPrizedDateNotEqualTo(String value) {
            addCriterion("PRIZED_DATE <>", value, "prizedDate");
            return (Criteria) this;
        }

        public Criteria andPrizedDateGreaterThan(String value) {
            addCriterion("PRIZED_DATE >", value, "prizedDate");
            return (Criteria) this;
        }

        public Criteria andPrizedDateGreaterThanOrEqualTo(String value) {
            addCriterion("PRIZED_DATE >=", value, "prizedDate");
            return (Criteria) this;
        }

        public Criteria andPrizedDateLessThan(String value) {
            addCriterion("PRIZED_DATE <", value, "prizedDate");
            return (Criteria) this;
        }

        public Criteria andPrizedDateLessThanOrEqualTo(String value) {
            addCriterion("PRIZED_DATE <=", value, "prizedDate");
            return (Criteria) this;
        }

        public Criteria andPrizedDateLike(String value) {
            addCriterion("PRIZED_DATE like", value, "prizedDate");
            return (Criteria) this;
        }

        public Criteria andPrizedDateNotLike(String value) {
            addCriterion("PRIZED_DATE not like", value, "prizedDate");
            return (Criteria) this;
        }

        public Criteria andPrizedDateIn(List<String> values) {
            addCriterion("PRIZED_DATE in", values, "prizedDate");
            return (Criteria) this;
        }

        public Criteria andPrizedDateNotIn(List<String> values) {
            addCriterion("PRIZED_DATE not in", values, "prizedDate");
            return (Criteria) this;
        }

        public Criteria andPrizedDateBetween(String value1, String value2) {
            addCriterion("PRIZED_DATE between", value1, value2, "prizedDate");
            return (Criteria) this;
        }

        public Criteria andPrizedDateNotBetween(String value1, String value2) {
            addCriterion("PRIZED_DATE not between", value1, value2, "prizedDate");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeIdIsNull() {
            addCriterion("PRIZED_GRADE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeIdIsNotNull() {
            addCriterion("PRIZED_GRADE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeIdEqualTo(String value) {
            addCriterion("PRIZED_GRADE_ID =", value, "prizedGradeId");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeIdNotEqualTo(String value) {
            addCriterion("PRIZED_GRADE_ID <>", value, "prizedGradeId");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeIdGreaterThan(String value) {
            addCriterion("PRIZED_GRADE_ID >", value, "prizedGradeId");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeIdGreaterThanOrEqualTo(String value) {
            addCriterion("PRIZED_GRADE_ID >=", value, "prizedGradeId");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeIdLessThan(String value) {
            addCriterion("PRIZED_GRADE_ID <", value, "prizedGradeId");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeIdLessThanOrEqualTo(String value) {
            addCriterion("PRIZED_GRADE_ID <=", value, "prizedGradeId");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeIdLike(String value) {
            addCriterion("PRIZED_GRADE_ID like", value, "prizedGradeId");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeIdNotLike(String value) {
            addCriterion("PRIZED_GRADE_ID not like", value, "prizedGradeId");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeIdIn(List<String> values) {
            addCriterion("PRIZED_GRADE_ID in", values, "prizedGradeId");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeIdNotIn(List<String> values) {
            addCriterion("PRIZED_GRADE_ID not in", values, "prizedGradeId");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeIdBetween(String value1, String value2) {
            addCriterion("PRIZED_GRADE_ID between", value1, value2, "prizedGradeId");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeIdNotBetween(String value1, String value2) {
            addCriterion("PRIZED_GRADE_ID not between", value1, value2, "prizedGradeId");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeNameIsNull() {
            addCriterion("PRIZED_GRADE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeNameIsNotNull() {
            addCriterion("PRIZED_GRADE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeNameEqualTo(String value) {
            addCriterion("PRIZED_GRADE_NAME =", value, "prizedGradeName");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeNameNotEqualTo(String value) {
            addCriterion("PRIZED_GRADE_NAME <>", value, "prizedGradeName");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeNameGreaterThan(String value) {
            addCriterion("PRIZED_GRADE_NAME >", value, "prizedGradeName");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeNameGreaterThanOrEqualTo(String value) {
            addCriterion("PRIZED_GRADE_NAME >=", value, "prizedGradeName");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeNameLessThan(String value) {
            addCriterion("PRIZED_GRADE_NAME <", value, "prizedGradeName");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeNameLessThanOrEqualTo(String value) {
            addCriterion("PRIZED_GRADE_NAME <=", value, "prizedGradeName");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeNameLike(String value) {
            addCriterion("PRIZED_GRADE_NAME like", value, "prizedGradeName");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeNameNotLike(String value) {
            addCriterion("PRIZED_GRADE_NAME not like", value, "prizedGradeName");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeNameIn(List<String> values) {
            addCriterion("PRIZED_GRADE_NAME in", values, "prizedGradeName");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeNameNotIn(List<String> values) {
            addCriterion("PRIZED_GRADE_NAME not in", values, "prizedGradeName");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeNameBetween(String value1, String value2) {
            addCriterion("PRIZED_GRADE_NAME between", value1, value2, "prizedGradeName");
            return (Criteria) this;
        }

        public Criteria andPrizedGradeNameNotBetween(String value1, String value2) {
            addCriterion("PRIZED_GRADE_NAME not between", value1, value2, "prizedGradeName");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelIdIsNull() {
            addCriterion("PRIZED_LEVEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelIdIsNotNull() {
            addCriterion("PRIZED_LEVEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelIdEqualTo(String value) {
            addCriterion("PRIZED_LEVEL_ID =", value, "prizedLevelId");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelIdNotEqualTo(String value) {
            addCriterion("PRIZED_LEVEL_ID <>", value, "prizedLevelId");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelIdGreaterThan(String value) {
            addCriterion("PRIZED_LEVEL_ID >", value, "prizedLevelId");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelIdGreaterThanOrEqualTo(String value) {
            addCriterion("PRIZED_LEVEL_ID >=", value, "prizedLevelId");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelIdLessThan(String value) {
            addCriterion("PRIZED_LEVEL_ID <", value, "prizedLevelId");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelIdLessThanOrEqualTo(String value) {
            addCriterion("PRIZED_LEVEL_ID <=", value, "prizedLevelId");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelIdLike(String value) {
            addCriterion("PRIZED_LEVEL_ID like", value, "prizedLevelId");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelIdNotLike(String value) {
            addCriterion("PRIZED_LEVEL_ID not like", value, "prizedLevelId");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelIdIn(List<String> values) {
            addCriterion("PRIZED_LEVEL_ID in", values, "prizedLevelId");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelIdNotIn(List<String> values) {
            addCriterion("PRIZED_LEVEL_ID not in", values, "prizedLevelId");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelIdBetween(String value1, String value2) {
            addCriterion("PRIZED_LEVEL_ID between", value1, value2, "prizedLevelId");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelIdNotBetween(String value1, String value2) {
            addCriterion("PRIZED_LEVEL_ID not between", value1, value2, "prizedLevelId");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelNameIsNull() {
            addCriterion("PRIZED_LEVEL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelNameIsNotNull() {
            addCriterion("PRIZED_LEVEL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelNameEqualTo(String value) {
            addCriterion("PRIZED_LEVEL_NAME =", value, "prizedLevelName");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelNameNotEqualTo(String value) {
            addCriterion("PRIZED_LEVEL_NAME <>", value, "prizedLevelName");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelNameGreaterThan(String value) {
            addCriterion("PRIZED_LEVEL_NAME >", value, "prizedLevelName");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("PRIZED_LEVEL_NAME >=", value, "prizedLevelName");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelNameLessThan(String value) {
            addCriterion("PRIZED_LEVEL_NAME <", value, "prizedLevelName");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelNameLessThanOrEqualTo(String value) {
            addCriterion("PRIZED_LEVEL_NAME <=", value, "prizedLevelName");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelNameLike(String value) {
            addCriterion("PRIZED_LEVEL_NAME like", value, "prizedLevelName");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelNameNotLike(String value) {
            addCriterion("PRIZED_LEVEL_NAME not like", value, "prizedLevelName");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelNameIn(List<String> values) {
            addCriterion("PRIZED_LEVEL_NAME in", values, "prizedLevelName");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelNameNotIn(List<String> values) {
            addCriterion("PRIZED_LEVEL_NAME not in", values, "prizedLevelName");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelNameBetween(String value1, String value2) {
            addCriterion("PRIZED_LEVEL_NAME between", value1, value2, "prizedLevelName");
            return (Criteria) this;
        }

        public Criteria andPrizedLevelNameNotBetween(String value1, String value2) {
            addCriterion("PRIZED_LEVEL_NAME not between", value1, value2, "prizedLevelName");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNull() {
            addCriterion("CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(String value) {
            addCriterion("CATEGORY_ID =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(String value) {
            addCriterion("CATEGORY_ID <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(String value) {
            addCriterion("CATEGORY_ID >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("CATEGORY_ID >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(String value) {
            addCriterion("CATEGORY_ID <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("CATEGORY_ID <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLike(String value) {
            addCriterion("CATEGORY_ID like", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotLike(String value) {
            addCriterion("CATEGORY_ID not like", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<String> values) {
            addCriterion("CATEGORY_ID in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<String> values) {
            addCriterion("CATEGORY_ID not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(String value1, String value2) {
            addCriterion("CATEGORY_ID between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(String value1, String value2) {
            addCriterion("CATEGORY_ID not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryNameIsNull() {
            addCriterion("CATEGORY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCategoryNameIsNotNull() {
            addCriterion("CATEGORY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryNameEqualTo(String value) {
            addCriterion("CATEGORY_NAME =", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameNotEqualTo(String value) {
            addCriterion("CATEGORY_NAME <>", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameGreaterThan(String value) {
            addCriterion("CATEGORY_NAME >", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("CATEGORY_NAME >=", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameLessThan(String value) {
            addCriterion("CATEGORY_NAME <", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("CATEGORY_NAME <=", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameLike(String value) {
            addCriterion("CATEGORY_NAME like", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameNotLike(String value) {
            addCriterion("CATEGORY_NAME not like", value, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameIn(List<String> values) {
            addCriterion("CATEGORY_NAME in", values, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameNotIn(List<String> values) {
            addCriterion("CATEGORY_NAME not in", values, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameBetween(String value1, String value2) {
            addCriterion("CATEGORY_NAME between", value1, value2, "categoryName");
            return (Criteria) this;
        }

        public Criteria andCategoryNameNotBetween(String value1, String value2) {
            addCriterion("CATEGORY_NAME not between", value1, value2, "categoryName");
            return (Criteria) this;
        }

        public Criteria andApprovalCodeIsNull() {
            addCriterion("APPROVAL_CODE is null");
            return (Criteria) this;
        }

        public Criteria andApprovalCodeIsNotNull() {
            addCriterion("APPROVAL_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andApprovalCodeEqualTo(String value) {
            addCriterion("APPROVAL_CODE =", value, "approvalCode");
            return (Criteria) this;
        }

        public Criteria andApprovalCodeNotEqualTo(String value) {
            addCriterion("APPROVAL_CODE <>", value, "approvalCode");
            return (Criteria) this;
        }

        public Criteria andApprovalCodeGreaterThan(String value) {
            addCriterion("APPROVAL_CODE >", value, "approvalCode");
            return (Criteria) this;
        }

        public Criteria andApprovalCodeGreaterThanOrEqualTo(String value) {
            addCriterion("APPROVAL_CODE >=", value, "approvalCode");
            return (Criteria) this;
        }

        public Criteria andApprovalCodeLessThan(String value) {
            addCriterion("APPROVAL_CODE <", value, "approvalCode");
            return (Criteria) this;
        }

        public Criteria andApprovalCodeLessThanOrEqualTo(String value) {
            addCriterion("APPROVAL_CODE <=", value, "approvalCode");
            return (Criteria) this;
        }

        public Criteria andApprovalCodeLike(String value) {
            addCriterion("APPROVAL_CODE like", value, "approvalCode");
            return (Criteria) this;
        }

        public Criteria andApprovalCodeNotLike(String value) {
            addCriterion("APPROVAL_CODE not like", value, "approvalCode");
            return (Criteria) this;
        }

        public Criteria andApprovalCodeIn(List<String> values) {
            addCriterion("APPROVAL_CODE in", values, "approvalCode");
            return (Criteria) this;
        }

        public Criteria andApprovalCodeNotIn(List<String> values) {
            addCriterion("APPROVAL_CODE not in", values, "approvalCode");
            return (Criteria) this;
        }

        public Criteria andApprovalCodeBetween(String value1, String value2) {
            addCriterion("APPROVAL_CODE between", value1, value2, "approvalCode");
            return (Criteria) this;
        }

        public Criteria andApprovalCodeNotBetween(String value1, String value2) {
            addCriterion("APPROVAL_CODE not between", value1, value2, "approvalCode");
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

        public Criteria andLastAuditAdviceIsNull() {
            addCriterion("LAST_AUDIT_ADVICE is null");
            return (Criteria) this;
        }

        public Criteria andLastAuditAdviceIsNotNull() {
            addCriterion("LAST_AUDIT_ADVICE is not null");
            return (Criteria) this;
        }

        public Criteria andLastAuditAdviceEqualTo(String value) {
            addCriterion("LAST_AUDIT_ADVICE =", value, "lastAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andLastAuditAdviceNotEqualTo(String value) {
            addCriterion("LAST_AUDIT_ADVICE <>", value, "lastAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andLastAuditAdviceGreaterThan(String value) {
            addCriterion("LAST_AUDIT_ADVICE >", value, "lastAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andLastAuditAdviceGreaterThanOrEqualTo(String value) {
            addCriterion("LAST_AUDIT_ADVICE >=", value, "lastAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andLastAuditAdviceLessThan(String value) {
            addCriterion("LAST_AUDIT_ADVICE <", value, "lastAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andLastAuditAdviceLessThanOrEqualTo(String value) {
            addCriterion("LAST_AUDIT_ADVICE <=", value, "lastAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andLastAuditAdviceLike(String value) {
            addCriterion("LAST_AUDIT_ADVICE like", value, "lastAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andLastAuditAdviceNotLike(String value) {
            addCriterion("LAST_AUDIT_ADVICE not like", value, "lastAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andLastAuditAdviceIn(List<String> values) {
            addCriterion("LAST_AUDIT_ADVICE in", values, "lastAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andLastAuditAdviceNotIn(List<String> values) {
            addCriterion("LAST_AUDIT_ADVICE not in", values, "lastAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andLastAuditAdviceBetween(String value1, String value2) {
            addCriterion("LAST_AUDIT_ADVICE between", value1, value2, "lastAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andLastAuditAdviceNotBetween(String value1, String value2) {
            addCriterion("LAST_AUDIT_ADVICE not between", value1, value2, "lastAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andLastAuditTimeIsNull() {
            addCriterion("LAST_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLastAuditTimeIsNotNull() {
            addCriterion("LAST_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLastAuditTimeEqualTo(String value) {
            addCriterion("LAST_AUDIT_TIME =", value, "lastAuditTime");
            return (Criteria) this;
        }

        public Criteria andLastAuditTimeNotEqualTo(String value) {
            addCriterion("LAST_AUDIT_TIME <>", value, "lastAuditTime");
            return (Criteria) this;
        }

        public Criteria andLastAuditTimeGreaterThan(String value) {
            addCriterion("LAST_AUDIT_TIME >", value, "lastAuditTime");
            return (Criteria) this;
        }

        public Criteria andLastAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("LAST_AUDIT_TIME >=", value, "lastAuditTime");
            return (Criteria) this;
        }

        public Criteria andLastAuditTimeLessThan(String value) {
            addCriterion("LAST_AUDIT_TIME <", value, "lastAuditTime");
            return (Criteria) this;
        }

        public Criteria andLastAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("LAST_AUDIT_TIME <=", value, "lastAuditTime");
            return (Criteria) this;
        }

        public Criteria andLastAuditTimeLike(String value) {
            addCriterion("LAST_AUDIT_TIME like", value, "lastAuditTime");
            return (Criteria) this;
        }

        public Criteria andLastAuditTimeNotLike(String value) {
            addCriterion("LAST_AUDIT_TIME not like", value, "lastAuditTime");
            return (Criteria) this;
        }

        public Criteria andLastAuditTimeIn(List<String> values) {
            addCriterion("LAST_AUDIT_TIME in", values, "lastAuditTime");
            return (Criteria) this;
        }

        public Criteria andLastAuditTimeNotIn(List<String> values) {
            addCriterion("LAST_AUDIT_TIME not in", values, "lastAuditTime");
            return (Criteria) this;
        }

        public Criteria andLastAuditTimeBetween(String value1, String value2) {
            addCriterion("LAST_AUDIT_TIME between", value1, value2, "lastAuditTime");
            return (Criteria) this;
        }

        public Criteria andLastAuditTimeNotBetween(String value1, String value2) {
            addCriterion("LAST_AUDIT_TIME not between", value1, value2, "lastAuditTime");
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