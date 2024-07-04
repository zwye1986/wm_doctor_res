package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class NygoAbroadApplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NygoAbroadApplyExample() {
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

        public Criteria andStuNoIsNull() {
            addCriterion("STU_NO is null");
            return (Criteria) this;
        }

        public Criteria andStuNoIsNotNull() {
            addCriterion("STU_NO is not null");
            return (Criteria) this;
        }

        public Criteria andStuNoEqualTo(String value) {
            addCriterion("STU_NO =", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoNotEqualTo(String value) {
            addCriterion("STU_NO <>", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoGreaterThan(String value) {
            addCriterion("STU_NO >", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoGreaterThanOrEqualTo(String value) {
            addCriterion("STU_NO >=", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoLessThan(String value) {
            addCriterion("STU_NO <", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoLessThanOrEqualTo(String value) {
            addCriterion("STU_NO <=", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoLike(String value) {
            addCriterion("STU_NO like", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoNotLike(String value) {
            addCriterion("STU_NO not like", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoIn(List<String> values) {
            addCriterion("STU_NO in", values, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoNotIn(List<String> values) {
            addCriterion("STU_NO not in", values, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoBetween(String value1, String value2) {
            addCriterion("STU_NO between", value1, value2, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoNotBetween(String value1, String value2) {
            addCriterion("STU_NO not between", value1, value2, "stuNo");
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

        public Criteria andMaritalNameIsNull() {
            addCriterion("MARITAL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMaritalNameIsNotNull() {
            addCriterion("MARITAL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMaritalNameEqualTo(String value) {
            addCriterion("MARITAL_NAME =", value, "maritalName");
            return (Criteria) this;
        }

        public Criteria andMaritalNameNotEqualTo(String value) {
            addCriterion("MARITAL_NAME <>", value, "maritalName");
            return (Criteria) this;
        }

        public Criteria andMaritalNameGreaterThan(String value) {
            addCriterion("MARITAL_NAME >", value, "maritalName");
            return (Criteria) this;
        }

        public Criteria andMaritalNameGreaterThanOrEqualTo(String value) {
            addCriterion("MARITAL_NAME >=", value, "maritalName");
            return (Criteria) this;
        }

        public Criteria andMaritalNameLessThan(String value) {
            addCriterion("MARITAL_NAME <", value, "maritalName");
            return (Criteria) this;
        }

        public Criteria andMaritalNameLessThanOrEqualTo(String value) {
            addCriterion("MARITAL_NAME <=", value, "maritalName");
            return (Criteria) this;
        }

        public Criteria andMaritalNameLike(String value) {
            addCriterion("MARITAL_NAME like", value, "maritalName");
            return (Criteria) this;
        }

        public Criteria andMaritalNameNotLike(String value) {
            addCriterion("MARITAL_NAME not like", value, "maritalName");
            return (Criteria) this;
        }

        public Criteria andMaritalNameIn(List<String> values) {
            addCriterion("MARITAL_NAME in", values, "maritalName");
            return (Criteria) this;
        }

        public Criteria andMaritalNameNotIn(List<String> values) {
            addCriterion("MARITAL_NAME not in", values, "maritalName");
            return (Criteria) this;
        }

        public Criteria andMaritalNameBetween(String value1, String value2) {
            addCriterion("MARITAL_NAME between", value1, value2, "maritalName");
            return (Criteria) this;
        }

        public Criteria andMaritalNameNotBetween(String value1, String value2) {
            addCriterion("MARITAL_NAME not between", value1, value2, "maritalName");
            return (Criteria) this;
        }

        public Criteria andBirthDateIsNull() {
            addCriterion("BIRTH_DATE is null");
            return (Criteria) this;
        }

        public Criteria andBirthDateIsNotNull() {
            addCriterion("BIRTH_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andBirthDateEqualTo(String value) {
            addCriterion("BIRTH_DATE =", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateNotEqualTo(String value) {
            addCriterion("BIRTH_DATE <>", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateGreaterThan(String value) {
            addCriterion("BIRTH_DATE >", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateGreaterThanOrEqualTo(String value) {
            addCriterion("BIRTH_DATE >=", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateLessThan(String value) {
            addCriterion("BIRTH_DATE <", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateLessThanOrEqualTo(String value) {
            addCriterion("BIRTH_DATE <=", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateLike(String value) {
            addCriterion("BIRTH_DATE like", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateNotLike(String value) {
            addCriterion("BIRTH_DATE not like", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateIn(List<String> values) {
            addCriterion("BIRTH_DATE in", values, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateNotIn(List<String> values) {
            addCriterion("BIRTH_DATE not in", values, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateBetween(String value1, String value2) {
            addCriterion("BIRTH_DATE between", value1, value2, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateNotBetween(String value1, String value2) {
            addCriterion("BIRTH_DATE not between", value1, value2, "birthDate");
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

        public Criteria andTutorFlowIsNull() {
            addCriterion("TUTOR_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andTutorFlowIsNotNull() {
            addCriterion("TUTOR_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andTutorFlowEqualTo(String value) {
            addCriterion("TUTOR_FLOW =", value, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowNotEqualTo(String value) {
            addCriterion("TUTOR_FLOW <>", value, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowGreaterThan(String value) {
            addCriterion("TUTOR_FLOW >", value, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_FLOW >=", value, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowLessThan(String value) {
            addCriterion("TUTOR_FLOW <", value, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_FLOW <=", value, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowLike(String value) {
            addCriterion("TUTOR_FLOW like", value, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowNotLike(String value) {
            addCriterion("TUTOR_FLOW not like", value, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowIn(List<String> values) {
            addCriterion("TUTOR_FLOW in", values, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowNotIn(List<String> values) {
            addCriterion("TUTOR_FLOW not in", values, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowBetween(String value1, String value2) {
            addCriterion("TUTOR_FLOW between", value1, value2, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowNotBetween(String value1, String value2) {
            addCriterion("TUTOR_FLOW not between", value1, value2, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorNameIsNull() {
            addCriterion("TUTOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTutorNameIsNotNull() {
            addCriterion("TUTOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTutorNameEqualTo(String value) {
            addCriterion("TUTOR_NAME =", value, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameNotEqualTo(String value) {
            addCriterion("TUTOR_NAME <>", value, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameGreaterThan(String value) {
            addCriterion("TUTOR_NAME >", value, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_NAME >=", value, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameLessThan(String value) {
            addCriterion("TUTOR_NAME <", value, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_NAME <=", value, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameLike(String value) {
            addCriterion("TUTOR_NAME like", value, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameNotLike(String value) {
            addCriterion("TUTOR_NAME not like", value, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameIn(List<String> values) {
            addCriterion("TUTOR_NAME in", values, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameNotIn(List<String> values) {
            addCriterion("TUTOR_NAME not in", values, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameBetween(String value1, String value2) {
            addCriterion("TUTOR_NAME between", value1, value2, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameNotBetween(String value1, String value2) {
            addCriterion("TUTOR_NAME not between", value1, value2, "tutorName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowIsNull() {
            addCriterion("PYDW_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowIsNotNull() {
            addCriterion("PYDW_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowEqualTo(String value) {
            addCriterion("PYDW_ORG_FLOW =", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowNotEqualTo(String value) {
            addCriterion("PYDW_ORG_FLOW <>", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowGreaterThan(String value) {
            addCriterion("PYDW_ORG_FLOW >", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PYDW_ORG_FLOW >=", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowLessThan(String value) {
            addCriterion("PYDW_ORG_FLOW <", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("PYDW_ORG_FLOW <=", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowLike(String value) {
            addCriterion("PYDW_ORG_FLOW like", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowNotLike(String value) {
            addCriterion("PYDW_ORG_FLOW not like", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowIn(List<String> values) {
            addCriterion("PYDW_ORG_FLOW in", values, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowNotIn(List<String> values) {
            addCriterion("PYDW_ORG_FLOW not in", values, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowBetween(String value1, String value2) {
            addCriterion("PYDW_ORG_FLOW between", value1, value2, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowNotBetween(String value1, String value2) {
            addCriterion("PYDW_ORG_FLOW not between", value1, value2, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameIsNull() {
            addCriterion("PYDW_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameIsNotNull() {
            addCriterion("PYDW_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameEqualTo(String value) {
            addCriterion("PYDW_ORG_NAME =", value, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameNotEqualTo(String value) {
            addCriterion("PYDW_ORG_NAME <>", value, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameGreaterThan(String value) {
            addCriterion("PYDW_ORG_NAME >", value, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("PYDW_ORG_NAME >=", value, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameLessThan(String value) {
            addCriterion("PYDW_ORG_NAME <", value, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameLessThanOrEqualTo(String value) {
            addCriterion("PYDW_ORG_NAME <=", value, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameLike(String value) {
            addCriterion("PYDW_ORG_NAME like", value, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameNotLike(String value) {
            addCriterion("PYDW_ORG_NAME not like", value, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameIn(List<String> values) {
            addCriterion("PYDW_ORG_NAME in", values, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameNotIn(List<String> values) {
            addCriterion("PYDW_ORG_NAME not in", values, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameBetween(String value1, String value2) {
            addCriterion("PYDW_ORG_NAME between", value1, value2, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameNotBetween(String value1, String value2) {
            addCriterion("PYDW_ORG_NAME not between", value1, value2, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowIsNull() {
            addCriterion("FWH_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowIsNotNull() {
            addCriterion("FWH_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowEqualTo(String value) {
            addCriterion("FWH_ORG_FLOW =", value, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowNotEqualTo(String value) {
            addCriterion("FWH_ORG_FLOW <>", value, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowGreaterThan(String value) {
            addCriterion("FWH_ORG_FLOW >", value, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("FWH_ORG_FLOW >=", value, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowLessThan(String value) {
            addCriterion("FWH_ORG_FLOW <", value, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("FWH_ORG_FLOW <=", value, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowLike(String value) {
            addCriterion("FWH_ORG_FLOW like", value, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowNotLike(String value) {
            addCriterion("FWH_ORG_FLOW not like", value, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowIn(List<String> values) {
            addCriterion("FWH_ORG_FLOW in", values, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowNotIn(List<String> values) {
            addCriterion("FWH_ORG_FLOW not in", values, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowBetween(String value1, String value2) {
            addCriterion("FWH_ORG_FLOW between", value1, value2, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowNotBetween(String value1, String value2) {
            addCriterion("FWH_ORG_FLOW not between", value1, value2, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameIsNull() {
            addCriterion("FWH_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameIsNotNull() {
            addCriterion("FWH_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameEqualTo(String value) {
            addCriterion("FWH_ORG_NAME =", value, "fwhOrgName");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameNotEqualTo(String value) {
            addCriterion("FWH_ORG_NAME <>", value, "fwhOrgName");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameGreaterThan(String value) {
            addCriterion("FWH_ORG_NAME >", value, "fwhOrgName");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("FWH_ORG_NAME >=", value, "fwhOrgName");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameLessThan(String value) {
            addCriterion("FWH_ORG_NAME <", value, "fwhOrgName");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameLessThanOrEqualTo(String value) {
            addCriterion("FWH_ORG_NAME <=", value, "fwhOrgName");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameLike(String value) {
            addCriterion("FWH_ORG_NAME like", value, "fwhOrgName");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameNotLike(String value) {
            addCriterion("FWH_ORG_NAME not like", value, "fwhOrgName");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameIn(List<String> values) {
            addCriterion("FWH_ORG_NAME in", values, "fwhOrgName");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameNotIn(List<String> values) {
            addCriterion("FWH_ORG_NAME not in", values, "fwhOrgName");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameBetween(String value1, String value2) {
            addCriterion("FWH_ORG_NAME between", value1, value2, "fwhOrgName");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameNotBetween(String value1, String value2) {
            addCriterion("FWH_ORG_NAME not between", value1, value2, "fwhOrgName");
            return (Criteria) this;
        }

        public Criteria andSecurityNameIsNull() {
            addCriterion("SECURITY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSecurityNameIsNotNull() {
            addCriterion("SECURITY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSecurityNameEqualTo(String value) {
            addCriterion("SECURITY_NAME =", value, "securityName");
            return (Criteria) this;
        }

        public Criteria andSecurityNameNotEqualTo(String value) {
            addCriterion("SECURITY_NAME <>", value, "securityName");
            return (Criteria) this;
        }

        public Criteria andSecurityNameGreaterThan(String value) {
            addCriterion("SECURITY_NAME >", value, "securityName");
            return (Criteria) this;
        }

        public Criteria andSecurityNameGreaterThanOrEqualTo(String value) {
            addCriterion("SECURITY_NAME >=", value, "securityName");
            return (Criteria) this;
        }

        public Criteria andSecurityNameLessThan(String value) {
            addCriterion("SECURITY_NAME <", value, "securityName");
            return (Criteria) this;
        }

        public Criteria andSecurityNameLessThanOrEqualTo(String value) {
            addCriterion("SECURITY_NAME <=", value, "securityName");
            return (Criteria) this;
        }

        public Criteria andSecurityNameLike(String value) {
            addCriterion("SECURITY_NAME like", value, "securityName");
            return (Criteria) this;
        }

        public Criteria andSecurityNameNotLike(String value) {
            addCriterion("SECURITY_NAME not like", value, "securityName");
            return (Criteria) this;
        }

        public Criteria andSecurityNameIn(List<String> values) {
            addCriterion("SECURITY_NAME in", values, "securityName");
            return (Criteria) this;
        }

        public Criteria andSecurityNameNotIn(List<String> values) {
            addCriterion("SECURITY_NAME not in", values, "securityName");
            return (Criteria) this;
        }

        public Criteria andSecurityNameBetween(String value1, String value2) {
            addCriterion("SECURITY_NAME between", value1, value2, "securityName");
            return (Criteria) this;
        }

        public Criteria andSecurityNameNotBetween(String value1, String value2) {
            addCriterion("SECURITY_NAME not between", value1, value2, "securityName");
            return (Criteria) this;
        }

        public Criteria andGoAbroadIdIsNull() {
            addCriterion("GO_ABROAD_ID is null");
            return (Criteria) this;
        }

        public Criteria andGoAbroadIdIsNotNull() {
            addCriterion("GO_ABROAD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGoAbroadIdEqualTo(String value) {
            addCriterion("GO_ABROAD_ID =", value, "goAbroadId");
            return (Criteria) this;
        }

        public Criteria andGoAbroadIdNotEqualTo(String value) {
            addCriterion("GO_ABROAD_ID <>", value, "goAbroadId");
            return (Criteria) this;
        }

        public Criteria andGoAbroadIdGreaterThan(String value) {
            addCriterion("GO_ABROAD_ID >", value, "goAbroadId");
            return (Criteria) this;
        }

        public Criteria andGoAbroadIdGreaterThanOrEqualTo(String value) {
            addCriterion("GO_ABROAD_ID >=", value, "goAbroadId");
            return (Criteria) this;
        }

        public Criteria andGoAbroadIdLessThan(String value) {
            addCriterion("GO_ABROAD_ID <", value, "goAbroadId");
            return (Criteria) this;
        }

        public Criteria andGoAbroadIdLessThanOrEqualTo(String value) {
            addCriterion("GO_ABROAD_ID <=", value, "goAbroadId");
            return (Criteria) this;
        }

        public Criteria andGoAbroadIdLike(String value) {
            addCriterion("GO_ABROAD_ID like", value, "goAbroadId");
            return (Criteria) this;
        }

        public Criteria andGoAbroadIdNotLike(String value) {
            addCriterion("GO_ABROAD_ID not like", value, "goAbroadId");
            return (Criteria) this;
        }

        public Criteria andGoAbroadIdIn(List<String> values) {
            addCriterion("GO_ABROAD_ID in", values, "goAbroadId");
            return (Criteria) this;
        }

        public Criteria andGoAbroadIdNotIn(List<String> values) {
            addCriterion("GO_ABROAD_ID not in", values, "goAbroadId");
            return (Criteria) this;
        }

        public Criteria andGoAbroadIdBetween(String value1, String value2) {
            addCriterion("GO_ABROAD_ID between", value1, value2, "goAbroadId");
            return (Criteria) this;
        }

        public Criteria andGoAbroadIdNotBetween(String value1, String value2) {
            addCriterion("GO_ABROAD_ID not between", value1, value2, "goAbroadId");
            return (Criteria) this;
        }

        public Criteria andGoAbroadNameIsNull() {
            addCriterion("GO_ABROAD_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGoAbroadNameIsNotNull() {
            addCriterion("GO_ABROAD_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGoAbroadNameEqualTo(String value) {
            addCriterion("GO_ABROAD_NAME =", value, "goAbroadName");
            return (Criteria) this;
        }

        public Criteria andGoAbroadNameNotEqualTo(String value) {
            addCriterion("GO_ABROAD_NAME <>", value, "goAbroadName");
            return (Criteria) this;
        }

        public Criteria andGoAbroadNameGreaterThan(String value) {
            addCriterion("GO_ABROAD_NAME >", value, "goAbroadName");
            return (Criteria) this;
        }

        public Criteria andGoAbroadNameGreaterThanOrEqualTo(String value) {
            addCriterion("GO_ABROAD_NAME >=", value, "goAbroadName");
            return (Criteria) this;
        }

        public Criteria andGoAbroadNameLessThan(String value) {
            addCriterion("GO_ABROAD_NAME <", value, "goAbroadName");
            return (Criteria) this;
        }

        public Criteria andGoAbroadNameLessThanOrEqualTo(String value) {
            addCriterion("GO_ABROAD_NAME <=", value, "goAbroadName");
            return (Criteria) this;
        }

        public Criteria andGoAbroadNameLike(String value) {
            addCriterion("GO_ABROAD_NAME like", value, "goAbroadName");
            return (Criteria) this;
        }

        public Criteria andGoAbroadNameNotLike(String value) {
            addCriterion("GO_ABROAD_NAME not like", value, "goAbroadName");
            return (Criteria) this;
        }

        public Criteria andGoAbroadNameIn(List<String> values) {
            addCriterion("GO_ABROAD_NAME in", values, "goAbroadName");
            return (Criteria) this;
        }

        public Criteria andGoAbroadNameNotIn(List<String> values) {
            addCriterion("GO_ABROAD_NAME not in", values, "goAbroadName");
            return (Criteria) this;
        }

        public Criteria andGoAbroadNameBetween(String value1, String value2) {
            addCriterion("GO_ABROAD_NAME between", value1, value2, "goAbroadName");
            return (Criteria) this;
        }

        public Criteria andGoAbroadNameNotBetween(String value1, String value2) {
            addCriterion("GO_ABROAD_NAME not between", value1, value2, "goAbroadName");
            return (Criteria) this;
        }

        public Criteria andStuLevelIdIsNull() {
            addCriterion("STU_LEVEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andStuLevelIdIsNotNull() {
            addCriterion("STU_LEVEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStuLevelIdEqualTo(String value) {
            addCriterion("STU_LEVEL_ID =", value, "stuLevelId");
            return (Criteria) this;
        }

        public Criteria andStuLevelIdNotEqualTo(String value) {
            addCriterion("STU_LEVEL_ID <>", value, "stuLevelId");
            return (Criteria) this;
        }

        public Criteria andStuLevelIdGreaterThan(String value) {
            addCriterion("STU_LEVEL_ID >", value, "stuLevelId");
            return (Criteria) this;
        }

        public Criteria andStuLevelIdGreaterThanOrEqualTo(String value) {
            addCriterion("STU_LEVEL_ID >=", value, "stuLevelId");
            return (Criteria) this;
        }

        public Criteria andStuLevelIdLessThan(String value) {
            addCriterion("STU_LEVEL_ID <", value, "stuLevelId");
            return (Criteria) this;
        }

        public Criteria andStuLevelIdLessThanOrEqualTo(String value) {
            addCriterion("STU_LEVEL_ID <=", value, "stuLevelId");
            return (Criteria) this;
        }

        public Criteria andStuLevelIdLike(String value) {
            addCriterion("STU_LEVEL_ID like", value, "stuLevelId");
            return (Criteria) this;
        }

        public Criteria andStuLevelIdNotLike(String value) {
            addCriterion("STU_LEVEL_ID not like", value, "stuLevelId");
            return (Criteria) this;
        }

        public Criteria andStuLevelIdIn(List<String> values) {
            addCriterion("STU_LEVEL_ID in", values, "stuLevelId");
            return (Criteria) this;
        }

        public Criteria andStuLevelIdNotIn(List<String> values) {
            addCriterion("STU_LEVEL_ID not in", values, "stuLevelId");
            return (Criteria) this;
        }

        public Criteria andStuLevelIdBetween(String value1, String value2) {
            addCriterion("STU_LEVEL_ID between", value1, value2, "stuLevelId");
            return (Criteria) this;
        }

        public Criteria andStuLevelIdNotBetween(String value1, String value2) {
            addCriterion("STU_LEVEL_ID not between", value1, value2, "stuLevelId");
            return (Criteria) this;
        }

        public Criteria andStuLevelNameIsNull() {
            addCriterion("STU_LEVEL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStuLevelNameIsNotNull() {
            addCriterion("STU_LEVEL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStuLevelNameEqualTo(String value) {
            addCriterion("STU_LEVEL_NAME =", value, "stuLevelName");
            return (Criteria) this;
        }

        public Criteria andStuLevelNameNotEqualTo(String value) {
            addCriterion("STU_LEVEL_NAME <>", value, "stuLevelName");
            return (Criteria) this;
        }

        public Criteria andStuLevelNameGreaterThan(String value) {
            addCriterion("STU_LEVEL_NAME >", value, "stuLevelName");
            return (Criteria) this;
        }

        public Criteria andStuLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("STU_LEVEL_NAME >=", value, "stuLevelName");
            return (Criteria) this;
        }

        public Criteria andStuLevelNameLessThan(String value) {
            addCriterion("STU_LEVEL_NAME <", value, "stuLevelName");
            return (Criteria) this;
        }

        public Criteria andStuLevelNameLessThanOrEqualTo(String value) {
            addCriterion("STU_LEVEL_NAME <=", value, "stuLevelName");
            return (Criteria) this;
        }

        public Criteria andStuLevelNameLike(String value) {
            addCriterion("STU_LEVEL_NAME like", value, "stuLevelName");
            return (Criteria) this;
        }

        public Criteria andStuLevelNameNotLike(String value) {
            addCriterion("STU_LEVEL_NAME not like", value, "stuLevelName");
            return (Criteria) this;
        }

        public Criteria andStuLevelNameIn(List<String> values) {
            addCriterion("STU_LEVEL_NAME in", values, "stuLevelName");
            return (Criteria) this;
        }

        public Criteria andStuLevelNameNotIn(List<String> values) {
            addCriterion("STU_LEVEL_NAME not in", values, "stuLevelName");
            return (Criteria) this;
        }

        public Criteria andStuLevelNameBetween(String value1, String value2) {
            addCriterion("STU_LEVEL_NAME between", value1, value2, "stuLevelName");
            return (Criteria) this;
        }

        public Criteria andStuLevelNameNotBetween(String value1, String value2) {
            addCriterion("STU_LEVEL_NAME not between", value1, value2, "stuLevelName");
            return (Criteria) this;
        }

        public Criteria andInSchoolDateIsNull() {
            addCriterion("IN_SCHOOL_DATE is null");
            return (Criteria) this;
        }

        public Criteria andInSchoolDateIsNotNull() {
            addCriterion("IN_SCHOOL_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andInSchoolDateEqualTo(String value) {
            addCriterion("IN_SCHOOL_DATE =", value, "inSchoolDate");
            return (Criteria) this;
        }

        public Criteria andInSchoolDateNotEqualTo(String value) {
            addCriterion("IN_SCHOOL_DATE <>", value, "inSchoolDate");
            return (Criteria) this;
        }

        public Criteria andInSchoolDateGreaterThan(String value) {
            addCriterion("IN_SCHOOL_DATE >", value, "inSchoolDate");
            return (Criteria) this;
        }

        public Criteria andInSchoolDateGreaterThanOrEqualTo(String value) {
            addCriterion("IN_SCHOOL_DATE >=", value, "inSchoolDate");
            return (Criteria) this;
        }

        public Criteria andInSchoolDateLessThan(String value) {
            addCriterion("IN_SCHOOL_DATE <", value, "inSchoolDate");
            return (Criteria) this;
        }

        public Criteria andInSchoolDateLessThanOrEqualTo(String value) {
            addCriterion("IN_SCHOOL_DATE <=", value, "inSchoolDate");
            return (Criteria) this;
        }

        public Criteria andInSchoolDateLike(String value) {
            addCriterion("IN_SCHOOL_DATE like", value, "inSchoolDate");
            return (Criteria) this;
        }

        public Criteria andInSchoolDateNotLike(String value) {
            addCriterion("IN_SCHOOL_DATE not like", value, "inSchoolDate");
            return (Criteria) this;
        }

        public Criteria andInSchoolDateIn(List<String> values) {
            addCriterion("IN_SCHOOL_DATE in", values, "inSchoolDate");
            return (Criteria) this;
        }

        public Criteria andInSchoolDateNotIn(List<String> values) {
            addCriterion("IN_SCHOOL_DATE not in", values, "inSchoolDate");
            return (Criteria) this;
        }

        public Criteria andInSchoolDateBetween(String value1, String value2) {
            addCriterion("IN_SCHOOL_DATE between", value1, value2, "inSchoolDate");
            return (Criteria) this;
        }

        public Criteria andInSchoolDateNotBetween(String value1, String value2) {
            addCriterion("IN_SCHOOL_DATE not between", value1, value2, "inSchoolDate");
            return (Criteria) this;
        }

        public Criteria andTelphoneIsNull() {
            addCriterion("TELPHONE is null");
            return (Criteria) this;
        }

        public Criteria andTelphoneIsNotNull() {
            addCriterion("TELPHONE is not null");
            return (Criteria) this;
        }

        public Criteria andTelphoneEqualTo(String value) {
            addCriterion("TELPHONE =", value, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneNotEqualTo(String value) {
            addCriterion("TELPHONE <>", value, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneGreaterThan(String value) {
            addCriterion("TELPHONE >", value, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneGreaterThanOrEqualTo(String value) {
            addCriterion("TELPHONE >=", value, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneLessThan(String value) {
            addCriterion("TELPHONE <", value, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneLessThanOrEqualTo(String value) {
            addCriterion("TELPHONE <=", value, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneLike(String value) {
            addCriterion("TELPHONE like", value, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneNotLike(String value) {
            addCriterion("TELPHONE not like", value, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneIn(List<String> values) {
            addCriterion("TELPHONE in", values, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneNotIn(List<String> values) {
            addCriterion("TELPHONE not in", values, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneBetween(String value1, String value2) {
            addCriterion("TELPHONE between", value1, value2, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneNotBetween(String value1, String value2) {
            addCriterion("TELPHONE not between", value1, value2, "telphone");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("EMAIL =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("EMAIL <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("EMAIL >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("EMAIL <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("EMAIL <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("EMAIL like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("EMAIL not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("EMAIL in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("EMAIL not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("EMAIL between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("EMAIL not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andBeginDateIsNull() {
            addCriterion("BEGIN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andBeginDateIsNotNull() {
            addCriterion("BEGIN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andBeginDateEqualTo(String value) {
            addCriterion("BEGIN_DATE =", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotEqualTo(String value) {
            addCriterion("BEGIN_DATE <>", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateGreaterThan(String value) {
            addCriterion("BEGIN_DATE >", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateGreaterThanOrEqualTo(String value) {
            addCriterion("BEGIN_DATE >=", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateLessThan(String value) {
            addCriterion("BEGIN_DATE <", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateLessThanOrEqualTo(String value) {
            addCriterion("BEGIN_DATE <=", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateLike(String value) {
            addCriterion("BEGIN_DATE like", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotLike(String value) {
            addCriterion("BEGIN_DATE not like", value, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateIn(List<String> values) {
            addCriterion("BEGIN_DATE in", values, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotIn(List<String> values) {
            addCriterion("BEGIN_DATE not in", values, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateBetween(String value1, String value2) {
            addCriterion("BEGIN_DATE between", value1, value2, "beginDate");
            return (Criteria) this;
        }

        public Criteria andBeginDateNotBetween(String value1, String value2) {
            addCriterion("BEGIN_DATE not between", value1, value2, "beginDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(String value) {
            addCriterion("END_DATE =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(String value) {
            addCriterion("END_DATE <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(String value) {
            addCriterion("END_DATE >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("END_DATE >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(String value) {
            addCriterion("END_DATE <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(String value) {
            addCriterion("END_DATE <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLike(String value) {
            addCriterion("END_DATE like", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotLike(String value) {
            addCriterion("END_DATE not like", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<String> values) {
            addCriterion("END_DATE in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<String> values) {
            addCriterion("END_DATE not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(String value1, String value2) {
            addCriterion("END_DATE between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(String value1, String value2) {
            addCriterion("END_DATE not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andDestinationNameIsNull() {
            addCriterion("DESTINATION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDestinationNameIsNotNull() {
            addCriterion("DESTINATION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDestinationNameEqualTo(String value) {
            addCriterion("DESTINATION_NAME =", value, "destinationName");
            return (Criteria) this;
        }

        public Criteria andDestinationNameNotEqualTo(String value) {
            addCriterion("DESTINATION_NAME <>", value, "destinationName");
            return (Criteria) this;
        }

        public Criteria andDestinationNameGreaterThan(String value) {
            addCriterion("DESTINATION_NAME >", value, "destinationName");
            return (Criteria) this;
        }

        public Criteria andDestinationNameGreaterThanOrEqualTo(String value) {
            addCriterion("DESTINATION_NAME >=", value, "destinationName");
            return (Criteria) this;
        }

        public Criteria andDestinationNameLessThan(String value) {
            addCriterion("DESTINATION_NAME <", value, "destinationName");
            return (Criteria) this;
        }

        public Criteria andDestinationNameLessThanOrEqualTo(String value) {
            addCriterion("DESTINATION_NAME <=", value, "destinationName");
            return (Criteria) this;
        }

        public Criteria andDestinationNameLike(String value) {
            addCriterion("DESTINATION_NAME like", value, "destinationName");
            return (Criteria) this;
        }

        public Criteria andDestinationNameNotLike(String value) {
            addCriterion("DESTINATION_NAME not like", value, "destinationName");
            return (Criteria) this;
        }

        public Criteria andDestinationNameIn(List<String> values) {
            addCriterion("DESTINATION_NAME in", values, "destinationName");
            return (Criteria) this;
        }

        public Criteria andDestinationNameNotIn(List<String> values) {
            addCriterion("DESTINATION_NAME not in", values, "destinationName");
            return (Criteria) this;
        }

        public Criteria andDestinationNameBetween(String value1, String value2) {
            addCriterion("DESTINATION_NAME between", value1, value2, "destinationName");
            return (Criteria) this;
        }

        public Criteria andDestinationNameNotBetween(String value1, String value2) {
            addCriterion("DESTINATION_NAME not between", value1, value2, "destinationName");
            return (Criteria) this;
        }

        public Criteria andVisitCollegeNameIsNull() {
            addCriterion("VISIT_COLLEGE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andVisitCollegeNameIsNotNull() {
            addCriterion("VISIT_COLLEGE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andVisitCollegeNameEqualTo(String value) {
            addCriterion("VISIT_COLLEGE_NAME =", value, "visitCollegeName");
            return (Criteria) this;
        }

        public Criteria andVisitCollegeNameNotEqualTo(String value) {
            addCriterion("VISIT_COLLEGE_NAME <>", value, "visitCollegeName");
            return (Criteria) this;
        }

        public Criteria andVisitCollegeNameGreaterThan(String value) {
            addCriterion("VISIT_COLLEGE_NAME >", value, "visitCollegeName");
            return (Criteria) this;
        }

        public Criteria andVisitCollegeNameGreaterThanOrEqualTo(String value) {
            addCriterion("VISIT_COLLEGE_NAME >=", value, "visitCollegeName");
            return (Criteria) this;
        }

        public Criteria andVisitCollegeNameLessThan(String value) {
            addCriterion("VISIT_COLLEGE_NAME <", value, "visitCollegeName");
            return (Criteria) this;
        }

        public Criteria andVisitCollegeNameLessThanOrEqualTo(String value) {
            addCriterion("VISIT_COLLEGE_NAME <=", value, "visitCollegeName");
            return (Criteria) this;
        }

        public Criteria andVisitCollegeNameLike(String value) {
            addCriterion("VISIT_COLLEGE_NAME like", value, "visitCollegeName");
            return (Criteria) this;
        }

        public Criteria andVisitCollegeNameNotLike(String value) {
            addCriterion("VISIT_COLLEGE_NAME not like", value, "visitCollegeName");
            return (Criteria) this;
        }

        public Criteria andVisitCollegeNameIn(List<String> values) {
            addCriterion("VISIT_COLLEGE_NAME in", values, "visitCollegeName");
            return (Criteria) this;
        }

        public Criteria andVisitCollegeNameNotIn(List<String> values) {
            addCriterion("VISIT_COLLEGE_NAME not in", values, "visitCollegeName");
            return (Criteria) this;
        }

        public Criteria andVisitCollegeNameBetween(String value1, String value2) {
            addCriterion("VISIT_COLLEGE_NAME between", value1, value2, "visitCollegeName");
            return (Criteria) this;
        }

        public Criteria andVisitCollegeNameNotBetween(String value1, String value2) {
            addCriterion("VISIT_COLLEGE_NAME not between", value1, value2, "visitCollegeName");
            return (Criteria) this;
        }

        public Criteria andSchoolFeeIsNull() {
            addCriterion("SCHOOL_FEE is null");
            return (Criteria) this;
        }

        public Criteria andSchoolFeeIsNotNull() {
            addCriterion("SCHOOL_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolFeeEqualTo(String value) {
            addCriterion("SCHOOL_FEE =", value, "schoolFee");
            return (Criteria) this;
        }

        public Criteria andSchoolFeeNotEqualTo(String value) {
            addCriterion("SCHOOL_FEE <>", value, "schoolFee");
            return (Criteria) this;
        }

        public Criteria andSchoolFeeGreaterThan(String value) {
            addCriterion("SCHOOL_FEE >", value, "schoolFee");
            return (Criteria) this;
        }

        public Criteria andSchoolFeeGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOL_FEE >=", value, "schoolFee");
            return (Criteria) this;
        }

        public Criteria andSchoolFeeLessThan(String value) {
            addCriterion("SCHOOL_FEE <", value, "schoolFee");
            return (Criteria) this;
        }

        public Criteria andSchoolFeeLessThanOrEqualTo(String value) {
            addCriterion("SCHOOL_FEE <=", value, "schoolFee");
            return (Criteria) this;
        }

        public Criteria andSchoolFeeLike(String value) {
            addCriterion("SCHOOL_FEE like", value, "schoolFee");
            return (Criteria) this;
        }

        public Criteria andSchoolFeeNotLike(String value) {
            addCriterion("SCHOOL_FEE not like", value, "schoolFee");
            return (Criteria) this;
        }

        public Criteria andSchoolFeeIn(List<String> values) {
            addCriterion("SCHOOL_FEE in", values, "schoolFee");
            return (Criteria) this;
        }

        public Criteria andSchoolFeeNotIn(List<String> values) {
            addCriterion("SCHOOL_FEE not in", values, "schoolFee");
            return (Criteria) this;
        }

        public Criteria andSchoolFeeBetween(String value1, String value2) {
            addCriterion("SCHOOL_FEE between", value1, value2, "schoolFee");
            return (Criteria) this;
        }

        public Criteria andSchoolFeeNotBetween(String value1, String value2) {
            addCriterion("SCHOOL_FEE not between", value1, value2, "schoolFee");
            return (Criteria) this;
        }

        public Criteria andSchoolBudgetFeeIsNull() {
            addCriterion("SCHOOL_BUDGET_FEE is null");
            return (Criteria) this;
        }

        public Criteria andSchoolBudgetFeeIsNotNull() {
            addCriterion("SCHOOL_BUDGET_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolBudgetFeeEqualTo(String value) {
            addCriterion("SCHOOL_BUDGET_FEE =", value, "schoolBudgetFee");
            return (Criteria) this;
        }

        public Criteria andSchoolBudgetFeeNotEqualTo(String value) {
            addCriterion("SCHOOL_BUDGET_FEE <>", value, "schoolBudgetFee");
            return (Criteria) this;
        }

        public Criteria andSchoolBudgetFeeGreaterThan(String value) {
            addCriterion("SCHOOL_BUDGET_FEE >", value, "schoolBudgetFee");
            return (Criteria) this;
        }

        public Criteria andSchoolBudgetFeeGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOL_BUDGET_FEE >=", value, "schoolBudgetFee");
            return (Criteria) this;
        }

        public Criteria andSchoolBudgetFeeLessThan(String value) {
            addCriterion("SCHOOL_BUDGET_FEE <", value, "schoolBudgetFee");
            return (Criteria) this;
        }

        public Criteria andSchoolBudgetFeeLessThanOrEqualTo(String value) {
            addCriterion("SCHOOL_BUDGET_FEE <=", value, "schoolBudgetFee");
            return (Criteria) this;
        }

        public Criteria andSchoolBudgetFeeLike(String value) {
            addCriterion("SCHOOL_BUDGET_FEE like", value, "schoolBudgetFee");
            return (Criteria) this;
        }

        public Criteria andSchoolBudgetFeeNotLike(String value) {
            addCriterion("SCHOOL_BUDGET_FEE not like", value, "schoolBudgetFee");
            return (Criteria) this;
        }

        public Criteria andSchoolBudgetFeeIn(List<String> values) {
            addCriterion("SCHOOL_BUDGET_FEE in", values, "schoolBudgetFee");
            return (Criteria) this;
        }

        public Criteria andSchoolBudgetFeeNotIn(List<String> values) {
            addCriterion("SCHOOL_BUDGET_FEE not in", values, "schoolBudgetFee");
            return (Criteria) this;
        }

        public Criteria andSchoolBudgetFeeBetween(String value1, String value2) {
            addCriterion("SCHOOL_BUDGET_FEE between", value1, value2, "schoolBudgetFee");
            return (Criteria) this;
        }

        public Criteria andSchoolBudgetFeeNotBetween(String value1, String value2) {
            addCriterion("SCHOOL_BUDGET_FEE not between", value1, value2, "schoolBudgetFee");
            return (Criteria) this;
        }

        public Criteria andTutorFeeIsNull() {
            addCriterion("TUTOR_FEE is null");
            return (Criteria) this;
        }

        public Criteria andTutorFeeIsNotNull() {
            addCriterion("TUTOR_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andTutorFeeEqualTo(String value) {
            addCriterion("TUTOR_FEE =", value, "tutorFee");
            return (Criteria) this;
        }

        public Criteria andTutorFeeNotEqualTo(String value) {
            addCriterion("TUTOR_FEE <>", value, "tutorFee");
            return (Criteria) this;
        }

        public Criteria andTutorFeeGreaterThan(String value) {
            addCriterion("TUTOR_FEE >", value, "tutorFee");
            return (Criteria) this;
        }

        public Criteria andTutorFeeGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_FEE >=", value, "tutorFee");
            return (Criteria) this;
        }

        public Criteria andTutorFeeLessThan(String value) {
            addCriterion("TUTOR_FEE <", value, "tutorFee");
            return (Criteria) this;
        }

        public Criteria andTutorFeeLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_FEE <=", value, "tutorFee");
            return (Criteria) this;
        }

        public Criteria andTutorFeeLike(String value) {
            addCriterion("TUTOR_FEE like", value, "tutorFee");
            return (Criteria) this;
        }

        public Criteria andTutorFeeNotLike(String value) {
            addCriterion("TUTOR_FEE not like", value, "tutorFee");
            return (Criteria) this;
        }

        public Criteria andTutorFeeIn(List<String> values) {
            addCriterion("TUTOR_FEE in", values, "tutorFee");
            return (Criteria) this;
        }

        public Criteria andTutorFeeNotIn(List<String> values) {
            addCriterion("TUTOR_FEE not in", values, "tutorFee");
            return (Criteria) this;
        }

        public Criteria andTutorFeeBetween(String value1, String value2) {
            addCriterion("TUTOR_FEE between", value1, value2, "tutorFee");
            return (Criteria) this;
        }

        public Criteria andTutorFeeNotBetween(String value1, String value2) {
            addCriterion("TUTOR_FEE not between", value1, value2, "tutorFee");
            return (Criteria) this;
        }

        public Criteria andTutorBudgetFeeIsNull() {
            addCriterion("TUTOR_BUDGET_FEE is null");
            return (Criteria) this;
        }

        public Criteria andTutorBudgetFeeIsNotNull() {
            addCriterion("TUTOR_BUDGET_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andTutorBudgetFeeEqualTo(String value) {
            addCriterion("TUTOR_BUDGET_FEE =", value, "tutorBudgetFee");
            return (Criteria) this;
        }

        public Criteria andTutorBudgetFeeNotEqualTo(String value) {
            addCriterion("TUTOR_BUDGET_FEE <>", value, "tutorBudgetFee");
            return (Criteria) this;
        }

        public Criteria andTutorBudgetFeeGreaterThan(String value) {
            addCriterion("TUTOR_BUDGET_FEE >", value, "tutorBudgetFee");
            return (Criteria) this;
        }

        public Criteria andTutorBudgetFeeGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_BUDGET_FEE >=", value, "tutorBudgetFee");
            return (Criteria) this;
        }

        public Criteria andTutorBudgetFeeLessThan(String value) {
            addCriterion("TUTOR_BUDGET_FEE <", value, "tutorBudgetFee");
            return (Criteria) this;
        }

        public Criteria andTutorBudgetFeeLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_BUDGET_FEE <=", value, "tutorBudgetFee");
            return (Criteria) this;
        }

        public Criteria andTutorBudgetFeeLike(String value) {
            addCriterion("TUTOR_BUDGET_FEE like", value, "tutorBudgetFee");
            return (Criteria) this;
        }

        public Criteria andTutorBudgetFeeNotLike(String value) {
            addCriterion("TUTOR_BUDGET_FEE not like", value, "tutorBudgetFee");
            return (Criteria) this;
        }

        public Criteria andTutorBudgetFeeIn(List<String> values) {
            addCriterion("TUTOR_BUDGET_FEE in", values, "tutorBudgetFee");
            return (Criteria) this;
        }

        public Criteria andTutorBudgetFeeNotIn(List<String> values) {
            addCriterion("TUTOR_BUDGET_FEE not in", values, "tutorBudgetFee");
            return (Criteria) this;
        }

        public Criteria andTutorBudgetFeeBetween(String value1, String value2) {
            addCriterion("TUTOR_BUDGET_FEE between", value1, value2, "tutorBudgetFee");
            return (Criteria) this;
        }

        public Criteria andTutorBudgetFeeNotBetween(String value1, String value2) {
            addCriterion("TUTOR_BUDGET_FEE not between", value1, value2, "tutorBudgetFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeIsNull() {
            addCriterion("OTHER_FEE is null");
            return (Criteria) this;
        }

        public Criteria andOtherFeeIsNotNull() {
            addCriterion("OTHER_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andOtherFeeEqualTo(String value) {
            addCriterion("OTHER_FEE =", value, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeNotEqualTo(String value) {
            addCriterion("OTHER_FEE <>", value, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeGreaterThan(String value) {
            addCriterion("OTHER_FEE >", value, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeGreaterThanOrEqualTo(String value) {
            addCriterion("OTHER_FEE >=", value, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeLessThan(String value) {
            addCriterion("OTHER_FEE <", value, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeLessThanOrEqualTo(String value) {
            addCriterion("OTHER_FEE <=", value, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeLike(String value) {
            addCriterion("OTHER_FEE like", value, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeNotLike(String value) {
            addCriterion("OTHER_FEE not like", value, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeIn(List<String> values) {
            addCriterion("OTHER_FEE in", values, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeNotIn(List<String> values) {
            addCriterion("OTHER_FEE not in", values, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeBetween(String value1, String value2) {
            addCriterion("OTHER_FEE between", value1, value2, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeNotBetween(String value1, String value2) {
            addCriterion("OTHER_FEE not between", value1, value2, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetFeeIsNull() {
            addCriterion("OTHER_BUDGET_FEE is null");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetFeeIsNotNull() {
            addCriterion("OTHER_BUDGET_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetFeeEqualTo(String value) {
            addCriterion("OTHER_BUDGET_FEE =", value, "otherBudgetFee");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetFeeNotEqualTo(String value) {
            addCriterion("OTHER_BUDGET_FEE <>", value, "otherBudgetFee");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetFeeGreaterThan(String value) {
            addCriterion("OTHER_BUDGET_FEE >", value, "otherBudgetFee");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetFeeGreaterThanOrEqualTo(String value) {
            addCriterion("OTHER_BUDGET_FEE >=", value, "otherBudgetFee");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetFeeLessThan(String value) {
            addCriterion("OTHER_BUDGET_FEE <", value, "otherBudgetFee");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetFeeLessThanOrEqualTo(String value) {
            addCriterion("OTHER_BUDGET_FEE <=", value, "otherBudgetFee");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetFeeLike(String value) {
            addCriterion("OTHER_BUDGET_FEE like", value, "otherBudgetFee");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetFeeNotLike(String value) {
            addCriterion("OTHER_BUDGET_FEE not like", value, "otherBudgetFee");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetFeeIn(List<String> values) {
            addCriterion("OTHER_BUDGET_FEE in", values, "otherBudgetFee");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetFeeNotIn(List<String> values) {
            addCriterion("OTHER_BUDGET_FEE not in", values, "otherBudgetFee");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetFeeBetween(String value1, String value2) {
            addCriterion("OTHER_BUDGET_FEE between", value1, value2, "otherBudgetFee");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetFeeNotBetween(String value1, String value2) {
            addCriterion("OTHER_BUDGET_FEE not between", value1, value2, "otherBudgetFee");
            return (Criteria) this;
        }

        public Criteria andApplyReasonIsNull() {
            addCriterion("APPLY_REASON is null");
            return (Criteria) this;
        }

        public Criteria andApplyReasonIsNotNull() {
            addCriterion("APPLY_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andApplyReasonEqualTo(String value) {
            addCriterion("APPLY_REASON =", value, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonNotEqualTo(String value) {
            addCriterion("APPLY_REASON <>", value, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonGreaterThan(String value) {
            addCriterion("APPLY_REASON >", value, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_REASON >=", value, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonLessThan(String value) {
            addCriterion("APPLY_REASON <", value, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonLessThanOrEqualTo(String value) {
            addCriterion("APPLY_REASON <=", value, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonLike(String value) {
            addCriterion("APPLY_REASON like", value, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonNotLike(String value) {
            addCriterion("APPLY_REASON not like", value, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonIn(List<String> values) {
            addCriterion("APPLY_REASON in", values, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonNotIn(List<String> values) {
            addCriterion("APPLY_REASON not in", values, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonBetween(String value1, String value2) {
            addCriterion("APPLY_REASON between", value1, value2, "applyReason");
            return (Criteria) this;
        }

        public Criteria andApplyReasonNotBetween(String value1, String value2) {
            addCriterion("APPLY_REASON not between", value1, value2, "applyReason");
            return (Criteria) this;
        }

        public Criteria andPromiseMemoIsNull() {
            addCriterion("PROMISE_MEMO is null");
            return (Criteria) this;
        }

        public Criteria andPromiseMemoIsNotNull() {
            addCriterion("PROMISE_MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andPromiseMemoEqualTo(String value) {
            addCriterion("PROMISE_MEMO =", value, "promiseMemo");
            return (Criteria) this;
        }

        public Criteria andPromiseMemoNotEqualTo(String value) {
            addCriterion("PROMISE_MEMO <>", value, "promiseMemo");
            return (Criteria) this;
        }

        public Criteria andPromiseMemoGreaterThan(String value) {
            addCriterion("PROMISE_MEMO >", value, "promiseMemo");
            return (Criteria) this;
        }

        public Criteria andPromiseMemoGreaterThanOrEqualTo(String value) {
            addCriterion("PROMISE_MEMO >=", value, "promiseMemo");
            return (Criteria) this;
        }

        public Criteria andPromiseMemoLessThan(String value) {
            addCriterion("PROMISE_MEMO <", value, "promiseMemo");
            return (Criteria) this;
        }

        public Criteria andPromiseMemoLessThanOrEqualTo(String value) {
            addCriterion("PROMISE_MEMO <=", value, "promiseMemo");
            return (Criteria) this;
        }

        public Criteria andPromiseMemoLike(String value) {
            addCriterion("PROMISE_MEMO like", value, "promiseMemo");
            return (Criteria) this;
        }

        public Criteria andPromiseMemoNotLike(String value) {
            addCriterion("PROMISE_MEMO not like", value, "promiseMemo");
            return (Criteria) this;
        }

        public Criteria andPromiseMemoIn(List<String> values) {
            addCriterion("PROMISE_MEMO in", values, "promiseMemo");
            return (Criteria) this;
        }

        public Criteria andPromiseMemoNotIn(List<String> values) {
            addCriterion("PROMISE_MEMO not in", values, "promiseMemo");
            return (Criteria) this;
        }

        public Criteria andPromiseMemoBetween(String value1, String value2) {
            addCriterion("PROMISE_MEMO between", value1, value2, "promiseMemo");
            return (Criteria) this;
        }

        public Criteria andPromiseMemoNotBetween(String value1, String value2) {
            addCriterion("PROMISE_MEMO not between", value1, value2, "promiseMemo");
            return (Criteria) this;
        }

        public Criteria andOutTutorAdviceIsNull() {
            addCriterion("OUT_TUTOR_ADVICE is null");
            return (Criteria) this;
        }

        public Criteria andOutTutorAdviceIsNotNull() {
            addCriterion("OUT_TUTOR_ADVICE is not null");
            return (Criteria) this;
        }

        public Criteria andOutTutorAdviceEqualTo(String value) {
            addCriterion("OUT_TUTOR_ADVICE =", value, "outTutorAdvice");
            return (Criteria) this;
        }

        public Criteria andOutTutorAdviceNotEqualTo(String value) {
            addCriterion("OUT_TUTOR_ADVICE <>", value, "outTutorAdvice");
            return (Criteria) this;
        }

        public Criteria andOutTutorAdviceGreaterThan(String value) {
            addCriterion("OUT_TUTOR_ADVICE >", value, "outTutorAdvice");
            return (Criteria) this;
        }

        public Criteria andOutTutorAdviceGreaterThanOrEqualTo(String value) {
            addCriterion("OUT_TUTOR_ADVICE >=", value, "outTutorAdvice");
            return (Criteria) this;
        }

        public Criteria andOutTutorAdviceLessThan(String value) {
            addCriterion("OUT_TUTOR_ADVICE <", value, "outTutorAdvice");
            return (Criteria) this;
        }

        public Criteria andOutTutorAdviceLessThanOrEqualTo(String value) {
            addCriterion("OUT_TUTOR_ADVICE <=", value, "outTutorAdvice");
            return (Criteria) this;
        }

        public Criteria andOutTutorAdviceLike(String value) {
            addCriterion("OUT_TUTOR_ADVICE like", value, "outTutorAdvice");
            return (Criteria) this;
        }

        public Criteria andOutTutorAdviceNotLike(String value) {
            addCriterion("OUT_TUTOR_ADVICE not like", value, "outTutorAdvice");
            return (Criteria) this;
        }

        public Criteria andOutTutorAdviceIn(List<String> values) {
            addCriterion("OUT_TUTOR_ADVICE in", values, "outTutorAdvice");
            return (Criteria) this;
        }

        public Criteria andOutTutorAdviceNotIn(List<String> values) {
            addCriterion("OUT_TUTOR_ADVICE not in", values, "outTutorAdvice");
            return (Criteria) this;
        }

        public Criteria andOutTutorAdviceBetween(String value1, String value2) {
            addCriterion("OUT_TUTOR_ADVICE between", value1, value2, "outTutorAdvice");
            return (Criteria) this;
        }

        public Criteria andOutTutorAdviceNotBetween(String value1, String value2) {
            addCriterion("OUT_TUTOR_ADVICE not between", value1, value2, "outTutorAdvice");
            return (Criteria) this;
        }

        public Criteria andTutorAuditDateIsNull() {
            addCriterion("TUTOR_AUDIT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTutorAuditDateIsNotNull() {
            addCriterion("TUTOR_AUDIT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTutorAuditDateEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_DATE =", value, "tutorAuditDate");
            return (Criteria) this;
        }

        public Criteria andTutorAuditDateNotEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_DATE <>", value, "tutorAuditDate");
            return (Criteria) this;
        }

        public Criteria andTutorAuditDateGreaterThan(String value) {
            addCriterion("TUTOR_AUDIT_DATE >", value, "tutorAuditDate");
            return (Criteria) this;
        }

        public Criteria andTutorAuditDateGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_DATE >=", value, "tutorAuditDate");
            return (Criteria) this;
        }

        public Criteria andTutorAuditDateLessThan(String value) {
            addCriterion("TUTOR_AUDIT_DATE <", value, "tutorAuditDate");
            return (Criteria) this;
        }

        public Criteria andTutorAuditDateLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_DATE <=", value, "tutorAuditDate");
            return (Criteria) this;
        }

        public Criteria andTutorAuditDateLike(String value) {
            addCriterion("TUTOR_AUDIT_DATE like", value, "tutorAuditDate");
            return (Criteria) this;
        }

        public Criteria andTutorAuditDateNotLike(String value) {
            addCriterion("TUTOR_AUDIT_DATE not like", value, "tutorAuditDate");
            return (Criteria) this;
        }

        public Criteria andTutorAuditDateIn(List<String> values) {
            addCriterion("TUTOR_AUDIT_DATE in", values, "tutorAuditDate");
            return (Criteria) this;
        }

        public Criteria andTutorAuditDateNotIn(List<String> values) {
            addCriterion("TUTOR_AUDIT_DATE not in", values, "tutorAuditDate");
            return (Criteria) this;
        }

        public Criteria andTutorAuditDateBetween(String value1, String value2) {
            addCriterion("TUTOR_AUDIT_DATE between", value1, value2, "tutorAuditDate");
            return (Criteria) this;
        }

        public Criteria andTutorAuditDateNotBetween(String value1, String value2) {
            addCriterion("TUTOR_AUDIT_DATE not between", value1, value2, "tutorAuditDate");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusIsNull() {
            addCriterion("POLITICAL_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusIsNotNull() {
            addCriterion("POLITICAL_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusEqualTo(String value) {
            addCriterion("POLITICAL_STATUS =", value, "politicalStatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusNotEqualTo(String value) {
            addCriterion("POLITICAL_STATUS <>", value, "politicalStatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusGreaterThan(String value) {
            addCriterion("POLITICAL_STATUS >", value, "politicalStatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusGreaterThanOrEqualTo(String value) {
            addCriterion("POLITICAL_STATUS >=", value, "politicalStatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusLessThan(String value) {
            addCriterion("POLITICAL_STATUS <", value, "politicalStatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusLessThanOrEqualTo(String value) {
            addCriterion("POLITICAL_STATUS <=", value, "politicalStatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusLike(String value) {
            addCriterion("POLITICAL_STATUS like", value, "politicalStatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusNotLike(String value) {
            addCriterion("POLITICAL_STATUS not like", value, "politicalStatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusIn(List<String> values) {
            addCriterion("POLITICAL_STATUS in", values, "politicalStatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusNotIn(List<String> values) {
            addCriterion("POLITICAL_STATUS not in", values, "politicalStatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusBetween(String value1, String value2) {
            addCriterion("POLITICAL_STATUS between", value1, value2, "politicalStatus");
            return (Criteria) this;
        }

        public Criteria andPoliticalStatusNotBetween(String value1, String value2) {
            addCriterion("POLITICAL_STATUS not between", value1, value2, "politicalStatus");
            return (Criteria) this;
        }

        public Criteria andPostDescIsNull() {
            addCriterion("POST_DESC is null");
            return (Criteria) this;
        }

        public Criteria andPostDescIsNotNull() {
            addCriterion("POST_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andPostDescEqualTo(String value) {
            addCriterion("POST_DESC =", value, "postDesc");
            return (Criteria) this;
        }

        public Criteria andPostDescNotEqualTo(String value) {
            addCriterion("POST_DESC <>", value, "postDesc");
            return (Criteria) this;
        }

        public Criteria andPostDescGreaterThan(String value) {
            addCriterion("POST_DESC >", value, "postDesc");
            return (Criteria) this;
        }

        public Criteria andPostDescGreaterThanOrEqualTo(String value) {
            addCriterion("POST_DESC >=", value, "postDesc");
            return (Criteria) this;
        }

        public Criteria andPostDescLessThan(String value) {
            addCriterion("POST_DESC <", value, "postDesc");
            return (Criteria) this;
        }

        public Criteria andPostDescLessThanOrEqualTo(String value) {
            addCriterion("POST_DESC <=", value, "postDesc");
            return (Criteria) this;
        }

        public Criteria andPostDescLike(String value) {
            addCriterion("POST_DESC like", value, "postDesc");
            return (Criteria) this;
        }

        public Criteria andPostDescNotLike(String value) {
            addCriterion("POST_DESC not like", value, "postDesc");
            return (Criteria) this;
        }

        public Criteria andPostDescIn(List<String> values) {
            addCriterion("POST_DESC in", values, "postDesc");
            return (Criteria) this;
        }

        public Criteria andPostDescNotIn(List<String> values) {
            addCriterion("POST_DESC not in", values, "postDesc");
            return (Criteria) this;
        }

        public Criteria andPostDescBetween(String value1, String value2) {
            addCriterion("POST_DESC between", value1, value2, "postDesc");
            return (Criteria) this;
        }

        public Criteria andPostDescNotBetween(String value1, String value2) {
            addCriterion("POST_DESC not between", value1, value2, "postDesc");
            return (Criteria) this;
        }

        public Criteria andHealthySituationIsNull() {
            addCriterion("HEALTHY_SITUATION is null");
            return (Criteria) this;
        }

        public Criteria andHealthySituationIsNotNull() {
            addCriterion("HEALTHY_SITUATION is not null");
            return (Criteria) this;
        }

        public Criteria andHealthySituationEqualTo(String value) {
            addCriterion("HEALTHY_SITUATION =", value, "healthySituation");
            return (Criteria) this;
        }

        public Criteria andHealthySituationNotEqualTo(String value) {
            addCriterion("HEALTHY_SITUATION <>", value, "healthySituation");
            return (Criteria) this;
        }

        public Criteria andHealthySituationGreaterThan(String value) {
            addCriterion("HEALTHY_SITUATION >", value, "healthySituation");
            return (Criteria) this;
        }

        public Criteria andHealthySituationGreaterThanOrEqualTo(String value) {
            addCriterion("HEALTHY_SITUATION >=", value, "healthySituation");
            return (Criteria) this;
        }

        public Criteria andHealthySituationLessThan(String value) {
            addCriterion("HEALTHY_SITUATION <", value, "healthySituation");
            return (Criteria) this;
        }

        public Criteria andHealthySituationLessThanOrEqualTo(String value) {
            addCriterion("HEALTHY_SITUATION <=", value, "healthySituation");
            return (Criteria) this;
        }

        public Criteria andHealthySituationLike(String value) {
            addCriterion("HEALTHY_SITUATION like", value, "healthySituation");
            return (Criteria) this;
        }

        public Criteria andHealthySituationNotLike(String value) {
            addCriterion("HEALTHY_SITUATION not like", value, "healthySituation");
            return (Criteria) this;
        }

        public Criteria andHealthySituationIn(List<String> values) {
            addCriterion("HEALTHY_SITUATION in", values, "healthySituation");
            return (Criteria) this;
        }

        public Criteria andHealthySituationNotIn(List<String> values) {
            addCriterion("HEALTHY_SITUATION not in", values, "healthySituation");
            return (Criteria) this;
        }

        public Criteria andHealthySituationBetween(String value1, String value2) {
            addCriterion("HEALTHY_SITUATION between", value1, value2, "healthySituation");
            return (Criteria) this;
        }

        public Criteria andHealthySituationNotBetween(String value1, String value2) {
            addCriterion("HEALTHY_SITUATION not between", value1, value2, "healthySituation");
            return (Criteria) this;
        }

        public Criteria andGoAbroadDescIsNull() {
            addCriterion("GO_ABROAD_DESC is null");
            return (Criteria) this;
        }

        public Criteria andGoAbroadDescIsNotNull() {
            addCriterion("GO_ABROAD_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andGoAbroadDescEqualTo(String value) {
            addCriterion("GO_ABROAD_DESC =", value, "goAbroadDesc");
            return (Criteria) this;
        }

        public Criteria andGoAbroadDescNotEqualTo(String value) {
            addCriterion("GO_ABROAD_DESC <>", value, "goAbroadDesc");
            return (Criteria) this;
        }

        public Criteria andGoAbroadDescGreaterThan(String value) {
            addCriterion("GO_ABROAD_DESC >", value, "goAbroadDesc");
            return (Criteria) this;
        }

        public Criteria andGoAbroadDescGreaterThanOrEqualTo(String value) {
            addCriterion("GO_ABROAD_DESC >=", value, "goAbroadDesc");
            return (Criteria) this;
        }

        public Criteria andGoAbroadDescLessThan(String value) {
            addCriterion("GO_ABROAD_DESC <", value, "goAbroadDesc");
            return (Criteria) this;
        }

        public Criteria andGoAbroadDescLessThanOrEqualTo(String value) {
            addCriterion("GO_ABROAD_DESC <=", value, "goAbroadDesc");
            return (Criteria) this;
        }

        public Criteria andGoAbroadDescLike(String value) {
            addCriterion("GO_ABROAD_DESC like", value, "goAbroadDesc");
            return (Criteria) this;
        }

        public Criteria andGoAbroadDescNotLike(String value) {
            addCriterion("GO_ABROAD_DESC not like", value, "goAbroadDesc");
            return (Criteria) this;
        }

        public Criteria andGoAbroadDescIn(List<String> values) {
            addCriterion("GO_ABROAD_DESC in", values, "goAbroadDesc");
            return (Criteria) this;
        }

        public Criteria andGoAbroadDescNotIn(List<String> values) {
            addCriterion("GO_ABROAD_DESC not in", values, "goAbroadDesc");
            return (Criteria) this;
        }

        public Criteria andGoAbroadDescBetween(String value1, String value2) {
            addCriterion("GO_ABROAD_DESC between", value1, value2, "goAbroadDesc");
            return (Criteria) this;
        }

        public Criteria andGoAbroadDescNotBetween(String value1, String value2) {
            addCriterion("GO_ABROAD_DESC not between", value1, value2, "goAbroadDesc");
            return (Criteria) this;
        }

        public Criteria andInvitationUrlIsNull() {
            addCriterion("INVITATION_URL is null");
            return (Criteria) this;
        }

        public Criteria andInvitationUrlIsNotNull() {
            addCriterion("INVITATION_URL is not null");
            return (Criteria) this;
        }

        public Criteria andInvitationUrlEqualTo(String value) {
            addCriterion("INVITATION_URL =", value, "invitationUrl");
            return (Criteria) this;
        }

        public Criteria andInvitationUrlNotEqualTo(String value) {
            addCriterion("INVITATION_URL <>", value, "invitationUrl");
            return (Criteria) this;
        }

        public Criteria andInvitationUrlGreaterThan(String value) {
            addCriterion("INVITATION_URL >", value, "invitationUrl");
            return (Criteria) this;
        }

        public Criteria andInvitationUrlGreaterThanOrEqualTo(String value) {
            addCriterion("INVITATION_URL >=", value, "invitationUrl");
            return (Criteria) this;
        }

        public Criteria andInvitationUrlLessThan(String value) {
            addCriterion("INVITATION_URL <", value, "invitationUrl");
            return (Criteria) this;
        }

        public Criteria andInvitationUrlLessThanOrEqualTo(String value) {
            addCriterion("INVITATION_URL <=", value, "invitationUrl");
            return (Criteria) this;
        }

        public Criteria andInvitationUrlLike(String value) {
            addCriterion("INVITATION_URL like", value, "invitationUrl");
            return (Criteria) this;
        }

        public Criteria andInvitationUrlNotLike(String value) {
            addCriterion("INVITATION_URL not like", value, "invitationUrl");
            return (Criteria) this;
        }

        public Criteria andInvitationUrlIn(List<String> values) {
            addCriterion("INVITATION_URL in", values, "invitationUrl");
            return (Criteria) this;
        }

        public Criteria andInvitationUrlNotIn(List<String> values) {
            addCriterion("INVITATION_URL not in", values, "invitationUrl");
            return (Criteria) this;
        }

        public Criteria andInvitationUrlBetween(String value1, String value2) {
            addCriterion("INVITATION_URL between", value1, value2, "invitationUrl");
            return (Criteria) this;
        }

        public Criteria andInvitationUrlNotBetween(String value1, String value2) {
            addCriterion("INVITATION_URL not between", value1, value2, "invitationUrl");
            return (Criteria) this;
        }

        public Criteria andChiInvitationUrlIsNull() {
            addCriterion("CHI_INVITATION_URL is null");
            return (Criteria) this;
        }

        public Criteria andChiInvitationUrlIsNotNull() {
            addCriterion("CHI_INVITATION_URL is not null");
            return (Criteria) this;
        }

        public Criteria andChiInvitationUrlEqualTo(String value) {
            addCriterion("CHI_INVITATION_URL =", value, "chiInvitationUrl");
            return (Criteria) this;
        }

        public Criteria andChiInvitationUrlNotEqualTo(String value) {
            addCriterion("CHI_INVITATION_URL <>", value, "chiInvitationUrl");
            return (Criteria) this;
        }

        public Criteria andChiInvitationUrlGreaterThan(String value) {
            addCriterion("CHI_INVITATION_URL >", value, "chiInvitationUrl");
            return (Criteria) this;
        }

        public Criteria andChiInvitationUrlGreaterThanOrEqualTo(String value) {
            addCriterion("CHI_INVITATION_URL >=", value, "chiInvitationUrl");
            return (Criteria) this;
        }

        public Criteria andChiInvitationUrlLessThan(String value) {
            addCriterion("CHI_INVITATION_URL <", value, "chiInvitationUrl");
            return (Criteria) this;
        }

        public Criteria andChiInvitationUrlLessThanOrEqualTo(String value) {
            addCriterion("CHI_INVITATION_URL <=", value, "chiInvitationUrl");
            return (Criteria) this;
        }

        public Criteria andChiInvitationUrlLike(String value) {
            addCriterion("CHI_INVITATION_URL like", value, "chiInvitationUrl");
            return (Criteria) this;
        }

        public Criteria andChiInvitationUrlNotLike(String value) {
            addCriterion("CHI_INVITATION_URL not like", value, "chiInvitationUrl");
            return (Criteria) this;
        }

        public Criteria andChiInvitationUrlIn(List<String> values) {
            addCriterion("CHI_INVITATION_URL in", values, "chiInvitationUrl");
            return (Criteria) this;
        }

        public Criteria andChiInvitationUrlNotIn(List<String> values) {
            addCriterion("CHI_INVITATION_URL not in", values, "chiInvitationUrl");
            return (Criteria) this;
        }

        public Criteria andChiInvitationUrlBetween(String value1, String value2) {
            addCriterion("CHI_INVITATION_URL between", value1, value2, "chiInvitationUrl");
            return (Criteria) this;
        }

        public Criteria andChiInvitationUrlNotBetween(String value1, String value2) {
            addCriterion("CHI_INVITATION_URL not between", value1, value2, "chiInvitationUrl");
            return (Criteria) this;
        }

        public Criteria andGjlfNameIsNull() {
            addCriterion("GJLF_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGjlfNameIsNotNull() {
            addCriterion("GJLF_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGjlfNameEqualTo(String value) {
            addCriterion("GJLF_NAME =", value, "gjlfName");
            return (Criteria) this;
        }

        public Criteria andGjlfNameNotEqualTo(String value) {
            addCriterion("GJLF_NAME <>", value, "gjlfName");
            return (Criteria) this;
        }

        public Criteria andGjlfNameGreaterThan(String value) {
            addCriterion("GJLF_NAME >", value, "gjlfName");
            return (Criteria) this;
        }

        public Criteria andGjlfNameGreaterThanOrEqualTo(String value) {
            addCriterion("GJLF_NAME >=", value, "gjlfName");
            return (Criteria) this;
        }

        public Criteria andGjlfNameLessThan(String value) {
            addCriterion("GJLF_NAME <", value, "gjlfName");
            return (Criteria) this;
        }

        public Criteria andGjlfNameLessThanOrEqualTo(String value) {
            addCriterion("GJLF_NAME <=", value, "gjlfName");
            return (Criteria) this;
        }

        public Criteria andGjlfNameLike(String value) {
            addCriterion("GJLF_NAME like", value, "gjlfName");
            return (Criteria) this;
        }

        public Criteria andGjlfNameNotLike(String value) {
            addCriterion("GJLF_NAME not like", value, "gjlfName");
            return (Criteria) this;
        }

        public Criteria andGjlfNameIn(List<String> values) {
            addCriterion("GJLF_NAME in", values, "gjlfName");
            return (Criteria) this;
        }

        public Criteria andGjlfNameNotIn(List<String> values) {
            addCriterion("GJLF_NAME not in", values, "gjlfName");
            return (Criteria) this;
        }

        public Criteria andGjlfNameBetween(String value1, String value2) {
            addCriterion("GJLF_NAME between", value1, value2, "gjlfName");
            return (Criteria) this;
        }

        public Criteria andGjlfNameNotBetween(String value1, String value2) {
            addCriterion("GJLF_NAME not between", value1, value2, "gjlfName");
            return (Criteria) this;
        }

        public Criteria andGjlfStandardIsNull() {
            addCriterion("GJLF_STANDARD is null");
            return (Criteria) this;
        }

        public Criteria andGjlfStandardIsNotNull() {
            addCriterion("GJLF_STANDARD is not null");
            return (Criteria) this;
        }

        public Criteria andGjlfStandardEqualTo(String value) {
            addCriterion("GJLF_STANDARD =", value, "gjlfStandard");
            return (Criteria) this;
        }

        public Criteria andGjlfStandardNotEqualTo(String value) {
            addCriterion("GJLF_STANDARD <>", value, "gjlfStandard");
            return (Criteria) this;
        }

        public Criteria andGjlfStandardGreaterThan(String value) {
            addCriterion("GJLF_STANDARD >", value, "gjlfStandard");
            return (Criteria) this;
        }

        public Criteria andGjlfStandardGreaterThanOrEqualTo(String value) {
            addCriterion("GJLF_STANDARD >=", value, "gjlfStandard");
            return (Criteria) this;
        }

        public Criteria andGjlfStandardLessThan(String value) {
            addCriterion("GJLF_STANDARD <", value, "gjlfStandard");
            return (Criteria) this;
        }

        public Criteria andGjlfStandardLessThanOrEqualTo(String value) {
            addCriterion("GJLF_STANDARD <=", value, "gjlfStandard");
            return (Criteria) this;
        }

        public Criteria andGjlfStandardLike(String value) {
            addCriterion("GJLF_STANDARD like", value, "gjlfStandard");
            return (Criteria) this;
        }

        public Criteria andGjlfStandardNotLike(String value) {
            addCriterion("GJLF_STANDARD not like", value, "gjlfStandard");
            return (Criteria) this;
        }

        public Criteria andGjlfStandardIn(List<String> values) {
            addCriterion("GJLF_STANDARD in", values, "gjlfStandard");
            return (Criteria) this;
        }

        public Criteria andGjlfStandardNotIn(List<String> values) {
            addCriterion("GJLF_STANDARD not in", values, "gjlfStandard");
            return (Criteria) this;
        }

        public Criteria andGjlfStandardBetween(String value1, String value2) {
            addCriterion("GJLF_STANDARD between", value1, value2, "gjlfStandard");
            return (Criteria) this;
        }

        public Criteria andGjlfStandardNotBetween(String value1, String value2) {
            addCriterion("GJLF_STANDARD not between", value1, value2, "gjlfStandard");
            return (Criteria) this;
        }

        public Criteria andGjlfDayIsNull() {
            addCriterion("GJLF_DAY is null");
            return (Criteria) this;
        }

        public Criteria andGjlfDayIsNotNull() {
            addCriterion("GJLF_DAY is not null");
            return (Criteria) this;
        }

        public Criteria andGjlfDayEqualTo(String value) {
            addCriterion("GJLF_DAY =", value, "gjlfDay");
            return (Criteria) this;
        }

        public Criteria andGjlfDayNotEqualTo(String value) {
            addCriterion("GJLF_DAY <>", value, "gjlfDay");
            return (Criteria) this;
        }

        public Criteria andGjlfDayGreaterThan(String value) {
            addCriterion("GJLF_DAY >", value, "gjlfDay");
            return (Criteria) this;
        }

        public Criteria andGjlfDayGreaterThanOrEqualTo(String value) {
            addCriterion("GJLF_DAY >=", value, "gjlfDay");
            return (Criteria) this;
        }

        public Criteria andGjlfDayLessThan(String value) {
            addCriterion("GJLF_DAY <", value, "gjlfDay");
            return (Criteria) this;
        }

        public Criteria andGjlfDayLessThanOrEqualTo(String value) {
            addCriterion("GJLF_DAY <=", value, "gjlfDay");
            return (Criteria) this;
        }

        public Criteria andGjlfDayLike(String value) {
            addCriterion("GJLF_DAY like", value, "gjlfDay");
            return (Criteria) this;
        }

        public Criteria andGjlfDayNotLike(String value) {
            addCriterion("GJLF_DAY not like", value, "gjlfDay");
            return (Criteria) this;
        }

        public Criteria andGjlfDayIn(List<String> values) {
            addCriterion("GJLF_DAY in", values, "gjlfDay");
            return (Criteria) this;
        }

        public Criteria andGjlfDayNotIn(List<String> values) {
            addCriterion("GJLF_DAY not in", values, "gjlfDay");
            return (Criteria) this;
        }

        public Criteria andGjlfDayBetween(String value1, String value2) {
            addCriterion("GJLF_DAY between", value1, value2, "gjlfDay");
            return (Criteria) this;
        }

        public Criteria andGjlfDayNotBetween(String value1, String value2) {
            addCriterion("GJLF_DAY not between", value1, value2, "gjlfDay");
            return (Criteria) this;
        }

        public Criteria andGjlfPeopleIsNull() {
            addCriterion("GJLF_PEOPLE is null");
            return (Criteria) this;
        }

        public Criteria andGjlfPeopleIsNotNull() {
            addCriterion("GJLF_PEOPLE is not null");
            return (Criteria) this;
        }

        public Criteria andGjlfPeopleEqualTo(String value) {
            addCriterion("GJLF_PEOPLE =", value, "gjlfPeople");
            return (Criteria) this;
        }

        public Criteria andGjlfPeopleNotEqualTo(String value) {
            addCriterion("GJLF_PEOPLE <>", value, "gjlfPeople");
            return (Criteria) this;
        }

        public Criteria andGjlfPeopleGreaterThan(String value) {
            addCriterion("GJLF_PEOPLE >", value, "gjlfPeople");
            return (Criteria) this;
        }

        public Criteria andGjlfPeopleGreaterThanOrEqualTo(String value) {
            addCriterion("GJLF_PEOPLE >=", value, "gjlfPeople");
            return (Criteria) this;
        }

        public Criteria andGjlfPeopleLessThan(String value) {
            addCriterion("GJLF_PEOPLE <", value, "gjlfPeople");
            return (Criteria) this;
        }

        public Criteria andGjlfPeopleLessThanOrEqualTo(String value) {
            addCriterion("GJLF_PEOPLE <=", value, "gjlfPeople");
            return (Criteria) this;
        }

        public Criteria andGjlfPeopleLike(String value) {
            addCriterion("GJLF_PEOPLE like", value, "gjlfPeople");
            return (Criteria) this;
        }

        public Criteria andGjlfPeopleNotLike(String value) {
            addCriterion("GJLF_PEOPLE not like", value, "gjlfPeople");
            return (Criteria) this;
        }

        public Criteria andGjlfPeopleIn(List<String> values) {
            addCriterion("GJLF_PEOPLE in", values, "gjlfPeople");
            return (Criteria) this;
        }

        public Criteria andGjlfPeopleNotIn(List<String> values) {
            addCriterion("GJLF_PEOPLE not in", values, "gjlfPeople");
            return (Criteria) this;
        }

        public Criteria andGjlfPeopleBetween(String value1, String value2) {
            addCriterion("GJLF_PEOPLE between", value1, value2, "gjlfPeople");
            return (Criteria) this;
        }

        public Criteria andGjlfPeopleNotBetween(String value1, String value2) {
            addCriterion("GJLF_PEOPLE not between", value1, value2, "gjlfPeople");
            return (Criteria) this;
        }

        public Criteria andGjlfSubtotalIsNull() {
            addCriterion("GJLF_SUBTOTAL is null");
            return (Criteria) this;
        }

        public Criteria andGjlfSubtotalIsNotNull() {
            addCriterion("GJLF_SUBTOTAL is not null");
            return (Criteria) this;
        }

        public Criteria andGjlfSubtotalEqualTo(String value) {
            addCriterion("GJLF_SUBTOTAL =", value, "gjlfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGjlfSubtotalNotEqualTo(String value) {
            addCriterion("GJLF_SUBTOTAL <>", value, "gjlfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGjlfSubtotalGreaterThan(String value) {
            addCriterion("GJLF_SUBTOTAL >", value, "gjlfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGjlfSubtotalGreaterThanOrEqualTo(String value) {
            addCriterion("GJLF_SUBTOTAL >=", value, "gjlfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGjlfSubtotalLessThan(String value) {
            addCriterion("GJLF_SUBTOTAL <", value, "gjlfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGjlfSubtotalLessThanOrEqualTo(String value) {
            addCriterion("GJLF_SUBTOTAL <=", value, "gjlfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGjlfSubtotalLike(String value) {
            addCriterion("GJLF_SUBTOTAL like", value, "gjlfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGjlfSubtotalNotLike(String value) {
            addCriterion("GJLF_SUBTOTAL not like", value, "gjlfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGjlfSubtotalIn(List<String> values) {
            addCriterion("GJLF_SUBTOTAL in", values, "gjlfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGjlfSubtotalNotIn(List<String> values) {
            addCriterion("GJLF_SUBTOTAL not in", values, "gjlfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGjlfSubtotalBetween(String value1, String value2) {
            addCriterion("GJLF_SUBTOTAL between", value1, value2, "gjlfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGjlfSubtotalNotBetween(String value1, String value2) {
            addCriterion("GJLF_SUBTOTAL not between", value1, value2, "gjlfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfNameIsNull() {
            addCriterion("GWCSJTF_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfNameIsNotNull() {
            addCriterion("GWCSJTF_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfNameEqualTo(String value) {
            addCriterion("GWCSJTF_NAME =", value, "gwcsjtfName");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfNameNotEqualTo(String value) {
            addCriterion("GWCSJTF_NAME <>", value, "gwcsjtfName");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfNameGreaterThan(String value) {
            addCriterion("GWCSJTF_NAME >", value, "gwcsjtfName");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfNameGreaterThanOrEqualTo(String value) {
            addCriterion("GWCSJTF_NAME >=", value, "gwcsjtfName");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfNameLessThan(String value) {
            addCriterion("GWCSJTF_NAME <", value, "gwcsjtfName");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfNameLessThanOrEqualTo(String value) {
            addCriterion("GWCSJTF_NAME <=", value, "gwcsjtfName");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfNameLike(String value) {
            addCriterion("GWCSJTF_NAME like", value, "gwcsjtfName");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfNameNotLike(String value) {
            addCriterion("GWCSJTF_NAME not like", value, "gwcsjtfName");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfNameIn(List<String> values) {
            addCriterion("GWCSJTF_NAME in", values, "gwcsjtfName");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfNameNotIn(List<String> values) {
            addCriterion("GWCSJTF_NAME not in", values, "gwcsjtfName");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfNameBetween(String value1, String value2) {
            addCriterion("GWCSJTF_NAME between", value1, value2, "gwcsjtfName");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfNameNotBetween(String value1, String value2) {
            addCriterion("GWCSJTF_NAME not between", value1, value2, "gwcsjtfName");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfStandardIsNull() {
            addCriterion("GWCSJTF_STANDARD is null");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfStandardIsNotNull() {
            addCriterion("GWCSJTF_STANDARD is not null");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfStandardEqualTo(String value) {
            addCriterion("GWCSJTF_STANDARD =", value, "gwcsjtfStandard");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfStandardNotEqualTo(String value) {
            addCriterion("GWCSJTF_STANDARD <>", value, "gwcsjtfStandard");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfStandardGreaterThan(String value) {
            addCriterion("GWCSJTF_STANDARD >", value, "gwcsjtfStandard");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfStandardGreaterThanOrEqualTo(String value) {
            addCriterion("GWCSJTF_STANDARD >=", value, "gwcsjtfStandard");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfStandardLessThan(String value) {
            addCriterion("GWCSJTF_STANDARD <", value, "gwcsjtfStandard");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfStandardLessThanOrEqualTo(String value) {
            addCriterion("GWCSJTF_STANDARD <=", value, "gwcsjtfStandard");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfStandardLike(String value) {
            addCriterion("GWCSJTF_STANDARD like", value, "gwcsjtfStandard");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfStandardNotLike(String value) {
            addCriterion("GWCSJTF_STANDARD not like", value, "gwcsjtfStandard");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfStandardIn(List<String> values) {
            addCriterion("GWCSJTF_STANDARD in", values, "gwcsjtfStandard");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfStandardNotIn(List<String> values) {
            addCriterion("GWCSJTF_STANDARD not in", values, "gwcsjtfStandard");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfStandardBetween(String value1, String value2) {
            addCriterion("GWCSJTF_STANDARD between", value1, value2, "gwcsjtfStandard");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfStandardNotBetween(String value1, String value2) {
            addCriterion("GWCSJTF_STANDARD not between", value1, value2, "gwcsjtfStandard");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfDayIsNull() {
            addCriterion("GWCSJTF_DAY is null");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfDayIsNotNull() {
            addCriterion("GWCSJTF_DAY is not null");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfDayEqualTo(String value) {
            addCriterion("GWCSJTF_DAY =", value, "gwcsjtfDay");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfDayNotEqualTo(String value) {
            addCriterion("GWCSJTF_DAY <>", value, "gwcsjtfDay");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfDayGreaterThan(String value) {
            addCriterion("GWCSJTF_DAY >", value, "gwcsjtfDay");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfDayGreaterThanOrEqualTo(String value) {
            addCriterion("GWCSJTF_DAY >=", value, "gwcsjtfDay");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfDayLessThan(String value) {
            addCriterion("GWCSJTF_DAY <", value, "gwcsjtfDay");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfDayLessThanOrEqualTo(String value) {
            addCriterion("GWCSJTF_DAY <=", value, "gwcsjtfDay");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfDayLike(String value) {
            addCriterion("GWCSJTF_DAY like", value, "gwcsjtfDay");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfDayNotLike(String value) {
            addCriterion("GWCSJTF_DAY not like", value, "gwcsjtfDay");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfDayIn(List<String> values) {
            addCriterion("GWCSJTF_DAY in", values, "gwcsjtfDay");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfDayNotIn(List<String> values) {
            addCriterion("GWCSJTF_DAY not in", values, "gwcsjtfDay");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfDayBetween(String value1, String value2) {
            addCriterion("GWCSJTF_DAY between", value1, value2, "gwcsjtfDay");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfDayNotBetween(String value1, String value2) {
            addCriterion("GWCSJTF_DAY not between", value1, value2, "gwcsjtfDay");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfPeopleIsNull() {
            addCriterion("GWCSJTF_PEOPLE is null");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfPeopleIsNotNull() {
            addCriterion("GWCSJTF_PEOPLE is not null");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfPeopleEqualTo(String value) {
            addCriterion("GWCSJTF_PEOPLE =", value, "gwcsjtfPeople");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfPeopleNotEqualTo(String value) {
            addCriterion("GWCSJTF_PEOPLE <>", value, "gwcsjtfPeople");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfPeopleGreaterThan(String value) {
            addCriterion("GWCSJTF_PEOPLE >", value, "gwcsjtfPeople");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfPeopleGreaterThanOrEqualTo(String value) {
            addCriterion("GWCSJTF_PEOPLE >=", value, "gwcsjtfPeople");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfPeopleLessThan(String value) {
            addCriterion("GWCSJTF_PEOPLE <", value, "gwcsjtfPeople");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfPeopleLessThanOrEqualTo(String value) {
            addCriterion("GWCSJTF_PEOPLE <=", value, "gwcsjtfPeople");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfPeopleLike(String value) {
            addCriterion("GWCSJTF_PEOPLE like", value, "gwcsjtfPeople");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfPeopleNotLike(String value) {
            addCriterion("GWCSJTF_PEOPLE not like", value, "gwcsjtfPeople");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfPeopleIn(List<String> values) {
            addCriterion("GWCSJTF_PEOPLE in", values, "gwcsjtfPeople");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfPeopleNotIn(List<String> values) {
            addCriterion("GWCSJTF_PEOPLE not in", values, "gwcsjtfPeople");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfPeopleBetween(String value1, String value2) {
            addCriterion("GWCSJTF_PEOPLE between", value1, value2, "gwcsjtfPeople");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfPeopleNotBetween(String value1, String value2) {
            addCriterion("GWCSJTF_PEOPLE not between", value1, value2, "gwcsjtfPeople");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfSubtotalIsNull() {
            addCriterion("GWCSJTF_SUBTOTAL is null");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfSubtotalIsNotNull() {
            addCriterion("GWCSJTF_SUBTOTAL is not null");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfSubtotalEqualTo(String value) {
            addCriterion("GWCSJTF_SUBTOTAL =", value, "gwcsjtfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfSubtotalNotEqualTo(String value) {
            addCriterion("GWCSJTF_SUBTOTAL <>", value, "gwcsjtfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfSubtotalGreaterThan(String value) {
            addCriterion("GWCSJTF_SUBTOTAL >", value, "gwcsjtfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfSubtotalGreaterThanOrEqualTo(String value) {
            addCriterion("GWCSJTF_SUBTOTAL >=", value, "gwcsjtfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfSubtotalLessThan(String value) {
            addCriterion("GWCSJTF_SUBTOTAL <", value, "gwcsjtfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfSubtotalLessThanOrEqualTo(String value) {
            addCriterion("GWCSJTF_SUBTOTAL <=", value, "gwcsjtfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfSubtotalLike(String value) {
            addCriterion("GWCSJTF_SUBTOTAL like", value, "gwcsjtfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfSubtotalNotLike(String value) {
            addCriterion("GWCSJTF_SUBTOTAL not like", value, "gwcsjtfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfSubtotalIn(List<String> values) {
            addCriterion("GWCSJTF_SUBTOTAL in", values, "gwcsjtfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfSubtotalNotIn(List<String> values) {
            addCriterion("GWCSJTF_SUBTOTAL not in", values, "gwcsjtfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfSubtotalBetween(String value1, String value2) {
            addCriterion("GWCSJTF_SUBTOTAL between", value1, value2, "gwcsjtfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGwcsjtfSubtotalNotBetween(String value1, String value2) {
            addCriterion("GWCSJTF_SUBTOTAL not between", value1, value2, "gwcsjtfSubtotal");
            return (Criteria) this;
        }

        public Criteria andZsfNameIsNull() {
            addCriterion("ZSF_NAME is null");
            return (Criteria) this;
        }

        public Criteria andZsfNameIsNotNull() {
            addCriterion("ZSF_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andZsfNameEqualTo(String value) {
            addCriterion("ZSF_NAME =", value, "zsfName");
            return (Criteria) this;
        }

        public Criteria andZsfNameNotEqualTo(String value) {
            addCriterion("ZSF_NAME <>", value, "zsfName");
            return (Criteria) this;
        }

        public Criteria andZsfNameGreaterThan(String value) {
            addCriterion("ZSF_NAME >", value, "zsfName");
            return (Criteria) this;
        }

        public Criteria andZsfNameGreaterThanOrEqualTo(String value) {
            addCriterion("ZSF_NAME >=", value, "zsfName");
            return (Criteria) this;
        }

        public Criteria andZsfNameLessThan(String value) {
            addCriterion("ZSF_NAME <", value, "zsfName");
            return (Criteria) this;
        }

        public Criteria andZsfNameLessThanOrEqualTo(String value) {
            addCriterion("ZSF_NAME <=", value, "zsfName");
            return (Criteria) this;
        }

        public Criteria andZsfNameLike(String value) {
            addCriterion("ZSF_NAME like", value, "zsfName");
            return (Criteria) this;
        }

        public Criteria andZsfNameNotLike(String value) {
            addCriterion("ZSF_NAME not like", value, "zsfName");
            return (Criteria) this;
        }

        public Criteria andZsfNameIn(List<String> values) {
            addCriterion("ZSF_NAME in", values, "zsfName");
            return (Criteria) this;
        }

        public Criteria andZsfNameNotIn(List<String> values) {
            addCriterion("ZSF_NAME not in", values, "zsfName");
            return (Criteria) this;
        }

        public Criteria andZsfNameBetween(String value1, String value2) {
            addCriterion("ZSF_NAME between", value1, value2, "zsfName");
            return (Criteria) this;
        }

        public Criteria andZsfNameNotBetween(String value1, String value2) {
            addCriterion("ZSF_NAME not between", value1, value2, "zsfName");
            return (Criteria) this;
        }

        public Criteria andZsfStandardIsNull() {
            addCriterion("ZSF_STANDARD is null");
            return (Criteria) this;
        }

        public Criteria andZsfStandardIsNotNull() {
            addCriterion("ZSF_STANDARD is not null");
            return (Criteria) this;
        }

        public Criteria andZsfStandardEqualTo(String value) {
            addCriterion("ZSF_STANDARD =", value, "zsfStandard");
            return (Criteria) this;
        }

        public Criteria andZsfStandardNotEqualTo(String value) {
            addCriterion("ZSF_STANDARD <>", value, "zsfStandard");
            return (Criteria) this;
        }

        public Criteria andZsfStandardGreaterThan(String value) {
            addCriterion("ZSF_STANDARD >", value, "zsfStandard");
            return (Criteria) this;
        }

        public Criteria andZsfStandardGreaterThanOrEqualTo(String value) {
            addCriterion("ZSF_STANDARD >=", value, "zsfStandard");
            return (Criteria) this;
        }

        public Criteria andZsfStandardLessThan(String value) {
            addCriterion("ZSF_STANDARD <", value, "zsfStandard");
            return (Criteria) this;
        }

        public Criteria andZsfStandardLessThanOrEqualTo(String value) {
            addCriterion("ZSF_STANDARD <=", value, "zsfStandard");
            return (Criteria) this;
        }

        public Criteria andZsfStandardLike(String value) {
            addCriterion("ZSF_STANDARD like", value, "zsfStandard");
            return (Criteria) this;
        }

        public Criteria andZsfStandardNotLike(String value) {
            addCriterion("ZSF_STANDARD not like", value, "zsfStandard");
            return (Criteria) this;
        }

        public Criteria andZsfStandardIn(List<String> values) {
            addCriterion("ZSF_STANDARD in", values, "zsfStandard");
            return (Criteria) this;
        }

        public Criteria andZsfStandardNotIn(List<String> values) {
            addCriterion("ZSF_STANDARD not in", values, "zsfStandard");
            return (Criteria) this;
        }

        public Criteria andZsfStandardBetween(String value1, String value2) {
            addCriterion("ZSF_STANDARD between", value1, value2, "zsfStandard");
            return (Criteria) this;
        }

        public Criteria andZsfStandardNotBetween(String value1, String value2) {
            addCriterion("ZSF_STANDARD not between", value1, value2, "zsfStandard");
            return (Criteria) this;
        }

        public Criteria andZsfDayIsNull() {
            addCriterion("ZSF_DAY is null");
            return (Criteria) this;
        }

        public Criteria andZsfDayIsNotNull() {
            addCriterion("ZSF_DAY is not null");
            return (Criteria) this;
        }

        public Criteria andZsfDayEqualTo(String value) {
            addCriterion("ZSF_DAY =", value, "zsfDay");
            return (Criteria) this;
        }

        public Criteria andZsfDayNotEqualTo(String value) {
            addCriterion("ZSF_DAY <>", value, "zsfDay");
            return (Criteria) this;
        }

        public Criteria andZsfDayGreaterThan(String value) {
            addCriterion("ZSF_DAY >", value, "zsfDay");
            return (Criteria) this;
        }

        public Criteria andZsfDayGreaterThanOrEqualTo(String value) {
            addCriterion("ZSF_DAY >=", value, "zsfDay");
            return (Criteria) this;
        }

        public Criteria andZsfDayLessThan(String value) {
            addCriterion("ZSF_DAY <", value, "zsfDay");
            return (Criteria) this;
        }

        public Criteria andZsfDayLessThanOrEqualTo(String value) {
            addCriterion("ZSF_DAY <=", value, "zsfDay");
            return (Criteria) this;
        }

        public Criteria andZsfDayLike(String value) {
            addCriterion("ZSF_DAY like", value, "zsfDay");
            return (Criteria) this;
        }

        public Criteria andZsfDayNotLike(String value) {
            addCriterion("ZSF_DAY not like", value, "zsfDay");
            return (Criteria) this;
        }

        public Criteria andZsfDayIn(List<String> values) {
            addCriterion("ZSF_DAY in", values, "zsfDay");
            return (Criteria) this;
        }

        public Criteria andZsfDayNotIn(List<String> values) {
            addCriterion("ZSF_DAY not in", values, "zsfDay");
            return (Criteria) this;
        }

        public Criteria andZsfDayBetween(String value1, String value2) {
            addCriterion("ZSF_DAY between", value1, value2, "zsfDay");
            return (Criteria) this;
        }

        public Criteria andZsfDayNotBetween(String value1, String value2) {
            addCriterion("ZSF_DAY not between", value1, value2, "zsfDay");
            return (Criteria) this;
        }

        public Criteria andZsfPeopleIsNull() {
            addCriterion("ZSF_PEOPLE is null");
            return (Criteria) this;
        }

        public Criteria andZsfPeopleIsNotNull() {
            addCriterion("ZSF_PEOPLE is not null");
            return (Criteria) this;
        }

        public Criteria andZsfPeopleEqualTo(String value) {
            addCriterion("ZSF_PEOPLE =", value, "zsfPeople");
            return (Criteria) this;
        }

        public Criteria andZsfPeopleNotEqualTo(String value) {
            addCriterion("ZSF_PEOPLE <>", value, "zsfPeople");
            return (Criteria) this;
        }

        public Criteria andZsfPeopleGreaterThan(String value) {
            addCriterion("ZSF_PEOPLE >", value, "zsfPeople");
            return (Criteria) this;
        }

        public Criteria andZsfPeopleGreaterThanOrEqualTo(String value) {
            addCriterion("ZSF_PEOPLE >=", value, "zsfPeople");
            return (Criteria) this;
        }

        public Criteria andZsfPeopleLessThan(String value) {
            addCriterion("ZSF_PEOPLE <", value, "zsfPeople");
            return (Criteria) this;
        }

        public Criteria andZsfPeopleLessThanOrEqualTo(String value) {
            addCriterion("ZSF_PEOPLE <=", value, "zsfPeople");
            return (Criteria) this;
        }

        public Criteria andZsfPeopleLike(String value) {
            addCriterion("ZSF_PEOPLE like", value, "zsfPeople");
            return (Criteria) this;
        }

        public Criteria andZsfPeopleNotLike(String value) {
            addCriterion("ZSF_PEOPLE not like", value, "zsfPeople");
            return (Criteria) this;
        }

        public Criteria andZsfPeopleIn(List<String> values) {
            addCriterion("ZSF_PEOPLE in", values, "zsfPeople");
            return (Criteria) this;
        }

        public Criteria andZsfPeopleNotIn(List<String> values) {
            addCriterion("ZSF_PEOPLE not in", values, "zsfPeople");
            return (Criteria) this;
        }

        public Criteria andZsfPeopleBetween(String value1, String value2) {
            addCriterion("ZSF_PEOPLE between", value1, value2, "zsfPeople");
            return (Criteria) this;
        }

        public Criteria andZsfPeopleNotBetween(String value1, String value2) {
            addCriterion("ZSF_PEOPLE not between", value1, value2, "zsfPeople");
            return (Criteria) this;
        }

        public Criteria andZsfSubtotalIsNull() {
            addCriterion("ZSF_SUBTOTAL is null");
            return (Criteria) this;
        }

        public Criteria andZsfSubtotalIsNotNull() {
            addCriterion("ZSF_SUBTOTAL is not null");
            return (Criteria) this;
        }

        public Criteria andZsfSubtotalEqualTo(String value) {
            addCriterion("ZSF_SUBTOTAL =", value, "zsfSubtotal");
            return (Criteria) this;
        }

        public Criteria andZsfSubtotalNotEqualTo(String value) {
            addCriterion("ZSF_SUBTOTAL <>", value, "zsfSubtotal");
            return (Criteria) this;
        }

        public Criteria andZsfSubtotalGreaterThan(String value) {
            addCriterion("ZSF_SUBTOTAL >", value, "zsfSubtotal");
            return (Criteria) this;
        }

        public Criteria andZsfSubtotalGreaterThanOrEqualTo(String value) {
            addCriterion("ZSF_SUBTOTAL >=", value, "zsfSubtotal");
            return (Criteria) this;
        }

        public Criteria andZsfSubtotalLessThan(String value) {
            addCriterion("ZSF_SUBTOTAL <", value, "zsfSubtotal");
            return (Criteria) this;
        }

        public Criteria andZsfSubtotalLessThanOrEqualTo(String value) {
            addCriterion("ZSF_SUBTOTAL <=", value, "zsfSubtotal");
            return (Criteria) this;
        }

        public Criteria andZsfSubtotalLike(String value) {
            addCriterion("ZSF_SUBTOTAL like", value, "zsfSubtotal");
            return (Criteria) this;
        }

        public Criteria andZsfSubtotalNotLike(String value) {
            addCriterion("ZSF_SUBTOTAL not like", value, "zsfSubtotal");
            return (Criteria) this;
        }

        public Criteria andZsfSubtotalIn(List<String> values) {
            addCriterion("ZSF_SUBTOTAL in", values, "zsfSubtotal");
            return (Criteria) this;
        }

        public Criteria andZsfSubtotalNotIn(List<String> values) {
            addCriterion("ZSF_SUBTOTAL not in", values, "zsfSubtotal");
            return (Criteria) this;
        }

        public Criteria andZsfSubtotalBetween(String value1, String value2) {
            addCriterion("ZSF_SUBTOTAL between", value1, value2, "zsfSubtotal");
            return (Criteria) this;
        }

        public Criteria andZsfSubtotalNotBetween(String value1, String value2) {
            addCriterion("ZSF_SUBTOTAL not between", value1, value2, "zsfSubtotal");
            return (Criteria) this;
        }

        public Criteria andHsfNameIsNull() {
            addCriterion("HSF_NAME is null");
            return (Criteria) this;
        }

        public Criteria andHsfNameIsNotNull() {
            addCriterion("HSF_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andHsfNameEqualTo(String value) {
            addCriterion("HSF_NAME =", value, "hsfName");
            return (Criteria) this;
        }

        public Criteria andHsfNameNotEqualTo(String value) {
            addCriterion("HSF_NAME <>", value, "hsfName");
            return (Criteria) this;
        }

        public Criteria andHsfNameGreaterThan(String value) {
            addCriterion("HSF_NAME >", value, "hsfName");
            return (Criteria) this;
        }

        public Criteria andHsfNameGreaterThanOrEqualTo(String value) {
            addCriterion("HSF_NAME >=", value, "hsfName");
            return (Criteria) this;
        }

        public Criteria andHsfNameLessThan(String value) {
            addCriterion("HSF_NAME <", value, "hsfName");
            return (Criteria) this;
        }

        public Criteria andHsfNameLessThanOrEqualTo(String value) {
            addCriterion("HSF_NAME <=", value, "hsfName");
            return (Criteria) this;
        }

        public Criteria andHsfNameLike(String value) {
            addCriterion("HSF_NAME like", value, "hsfName");
            return (Criteria) this;
        }

        public Criteria andHsfNameNotLike(String value) {
            addCriterion("HSF_NAME not like", value, "hsfName");
            return (Criteria) this;
        }

        public Criteria andHsfNameIn(List<String> values) {
            addCriterion("HSF_NAME in", values, "hsfName");
            return (Criteria) this;
        }

        public Criteria andHsfNameNotIn(List<String> values) {
            addCriterion("HSF_NAME not in", values, "hsfName");
            return (Criteria) this;
        }

        public Criteria andHsfNameBetween(String value1, String value2) {
            addCriterion("HSF_NAME between", value1, value2, "hsfName");
            return (Criteria) this;
        }

        public Criteria andHsfNameNotBetween(String value1, String value2) {
            addCriterion("HSF_NAME not between", value1, value2, "hsfName");
            return (Criteria) this;
        }

        public Criteria andHsfStandardIsNull() {
            addCriterion("HSF_STANDARD is null");
            return (Criteria) this;
        }

        public Criteria andHsfStandardIsNotNull() {
            addCriterion("HSF_STANDARD is not null");
            return (Criteria) this;
        }

        public Criteria andHsfStandardEqualTo(String value) {
            addCriterion("HSF_STANDARD =", value, "hsfStandard");
            return (Criteria) this;
        }

        public Criteria andHsfStandardNotEqualTo(String value) {
            addCriterion("HSF_STANDARD <>", value, "hsfStandard");
            return (Criteria) this;
        }

        public Criteria andHsfStandardGreaterThan(String value) {
            addCriterion("HSF_STANDARD >", value, "hsfStandard");
            return (Criteria) this;
        }

        public Criteria andHsfStandardGreaterThanOrEqualTo(String value) {
            addCriterion("HSF_STANDARD >=", value, "hsfStandard");
            return (Criteria) this;
        }

        public Criteria andHsfStandardLessThan(String value) {
            addCriterion("HSF_STANDARD <", value, "hsfStandard");
            return (Criteria) this;
        }

        public Criteria andHsfStandardLessThanOrEqualTo(String value) {
            addCriterion("HSF_STANDARD <=", value, "hsfStandard");
            return (Criteria) this;
        }

        public Criteria andHsfStandardLike(String value) {
            addCriterion("HSF_STANDARD like", value, "hsfStandard");
            return (Criteria) this;
        }

        public Criteria andHsfStandardNotLike(String value) {
            addCriterion("HSF_STANDARD not like", value, "hsfStandard");
            return (Criteria) this;
        }

        public Criteria andHsfStandardIn(List<String> values) {
            addCriterion("HSF_STANDARD in", values, "hsfStandard");
            return (Criteria) this;
        }

        public Criteria andHsfStandardNotIn(List<String> values) {
            addCriterion("HSF_STANDARD not in", values, "hsfStandard");
            return (Criteria) this;
        }

        public Criteria andHsfStandardBetween(String value1, String value2) {
            addCriterion("HSF_STANDARD between", value1, value2, "hsfStandard");
            return (Criteria) this;
        }

        public Criteria andHsfStandardNotBetween(String value1, String value2) {
            addCriterion("HSF_STANDARD not between", value1, value2, "hsfStandard");
            return (Criteria) this;
        }

        public Criteria andHsfDayIsNull() {
            addCriterion("HSF_DAY is null");
            return (Criteria) this;
        }

        public Criteria andHsfDayIsNotNull() {
            addCriterion("HSF_DAY is not null");
            return (Criteria) this;
        }

        public Criteria andHsfDayEqualTo(String value) {
            addCriterion("HSF_DAY =", value, "hsfDay");
            return (Criteria) this;
        }

        public Criteria andHsfDayNotEqualTo(String value) {
            addCriterion("HSF_DAY <>", value, "hsfDay");
            return (Criteria) this;
        }

        public Criteria andHsfDayGreaterThan(String value) {
            addCriterion("HSF_DAY >", value, "hsfDay");
            return (Criteria) this;
        }

        public Criteria andHsfDayGreaterThanOrEqualTo(String value) {
            addCriterion("HSF_DAY >=", value, "hsfDay");
            return (Criteria) this;
        }

        public Criteria andHsfDayLessThan(String value) {
            addCriterion("HSF_DAY <", value, "hsfDay");
            return (Criteria) this;
        }

        public Criteria andHsfDayLessThanOrEqualTo(String value) {
            addCriterion("HSF_DAY <=", value, "hsfDay");
            return (Criteria) this;
        }

        public Criteria andHsfDayLike(String value) {
            addCriterion("HSF_DAY like", value, "hsfDay");
            return (Criteria) this;
        }

        public Criteria andHsfDayNotLike(String value) {
            addCriterion("HSF_DAY not like", value, "hsfDay");
            return (Criteria) this;
        }

        public Criteria andHsfDayIn(List<String> values) {
            addCriterion("HSF_DAY in", values, "hsfDay");
            return (Criteria) this;
        }

        public Criteria andHsfDayNotIn(List<String> values) {
            addCriterion("HSF_DAY not in", values, "hsfDay");
            return (Criteria) this;
        }

        public Criteria andHsfDayBetween(String value1, String value2) {
            addCriterion("HSF_DAY between", value1, value2, "hsfDay");
            return (Criteria) this;
        }

        public Criteria andHsfDayNotBetween(String value1, String value2) {
            addCriterion("HSF_DAY not between", value1, value2, "hsfDay");
            return (Criteria) this;
        }

        public Criteria andHsfPeopleIsNull() {
            addCriterion("HSF_PEOPLE is null");
            return (Criteria) this;
        }

        public Criteria andHsfPeopleIsNotNull() {
            addCriterion("HSF_PEOPLE is not null");
            return (Criteria) this;
        }

        public Criteria andHsfPeopleEqualTo(String value) {
            addCriterion("HSF_PEOPLE =", value, "hsfPeople");
            return (Criteria) this;
        }

        public Criteria andHsfPeopleNotEqualTo(String value) {
            addCriterion("HSF_PEOPLE <>", value, "hsfPeople");
            return (Criteria) this;
        }

        public Criteria andHsfPeopleGreaterThan(String value) {
            addCriterion("HSF_PEOPLE >", value, "hsfPeople");
            return (Criteria) this;
        }

        public Criteria andHsfPeopleGreaterThanOrEqualTo(String value) {
            addCriterion("HSF_PEOPLE >=", value, "hsfPeople");
            return (Criteria) this;
        }

        public Criteria andHsfPeopleLessThan(String value) {
            addCriterion("HSF_PEOPLE <", value, "hsfPeople");
            return (Criteria) this;
        }

        public Criteria andHsfPeopleLessThanOrEqualTo(String value) {
            addCriterion("HSF_PEOPLE <=", value, "hsfPeople");
            return (Criteria) this;
        }

        public Criteria andHsfPeopleLike(String value) {
            addCriterion("HSF_PEOPLE like", value, "hsfPeople");
            return (Criteria) this;
        }

        public Criteria andHsfPeopleNotLike(String value) {
            addCriterion("HSF_PEOPLE not like", value, "hsfPeople");
            return (Criteria) this;
        }

        public Criteria andHsfPeopleIn(List<String> values) {
            addCriterion("HSF_PEOPLE in", values, "hsfPeople");
            return (Criteria) this;
        }

        public Criteria andHsfPeopleNotIn(List<String> values) {
            addCriterion("HSF_PEOPLE not in", values, "hsfPeople");
            return (Criteria) this;
        }

        public Criteria andHsfPeopleBetween(String value1, String value2) {
            addCriterion("HSF_PEOPLE between", value1, value2, "hsfPeople");
            return (Criteria) this;
        }

        public Criteria andHsfPeopleNotBetween(String value1, String value2) {
            addCriterion("HSF_PEOPLE not between", value1, value2, "hsfPeople");
            return (Criteria) this;
        }

        public Criteria andHsfSubtotalIsNull() {
            addCriterion("HSF_SUBTOTAL is null");
            return (Criteria) this;
        }

        public Criteria andHsfSubtotalIsNotNull() {
            addCriterion("HSF_SUBTOTAL is not null");
            return (Criteria) this;
        }

        public Criteria andHsfSubtotalEqualTo(String value) {
            addCriterion("HSF_SUBTOTAL =", value, "hsfSubtotal");
            return (Criteria) this;
        }

        public Criteria andHsfSubtotalNotEqualTo(String value) {
            addCriterion("HSF_SUBTOTAL <>", value, "hsfSubtotal");
            return (Criteria) this;
        }

        public Criteria andHsfSubtotalGreaterThan(String value) {
            addCriterion("HSF_SUBTOTAL >", value, "hsfSubtotal");
            return (Criteria) this;
        }

        public Criteria andHsfSubtotalGreaterThanOrEqualTo(String value) {
            addCriterion("HSF_SUBTOTAL >=", value, "hsfSubtotal");
            return (Criteria) this;
        }

        public Criteria andHsfSubtotalLessThan(String value) {
            addCriterion("HSF_SUBTOTAL <", value, "hsfSubtotal");
            return (Criteria) this;
        }

        public Criteria andHsfSubtotalLessThanOrEqualTo(String value) {
            addCriterion("HSF_SUBTOTAL <=", value, "hsfSubtotal");
            return (Criteria) this;
        }

        public Criteria andHsfSubtotalLike(String value) {
            addCriterion("HSF_SUBTOTAL like", value, "hsfSubtotal");
            return (Criteria) this;
        }

        public Criteria andHsfSubtotalNotLike(String value) {
            addCriterion("HSF_SUBTOTAL not like", value, "hsfSubtotal");
            return (Criteria) this;
        }

        public Criteria andHsfSubtotalIn(List<String> values) {
            addCriterion("HSF_SUBTOTAL in", values, "hsfSubtotal");
            return (Criteria) this;
        }

        public Criteria andHsfSubtotalNotIn(List<String> values) {
            addCriterion("HSF_SUBTOTAL not in", values, "hsfSubtotal");
            return (Criteria) this;
        }

        public Criteria andHsfSubtotalBetween(String value1, String value2) {
            addCriterion("HSF_SUBTOTAL between", value1, value2, "hsfSubtotal");
            return (Criteria) this;
        }

        public Criteria andHsfSubtotalNotBetween(String value1, String value2) {
            addCriterion("HSF_SUBTOTAL not between", value1, value2, "hsfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGzfNameIsNull() {
            addCriterion("GZF_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGzfNameIsNotNull() {
            addCriterion("GZF_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGzfNameEqualTo(String value) {
            addCriterion("GZF_NAME =", value, "gzfName");
            return (Criteria) this;
        }

        public Criteria andGzfNameNotEqualTo(String value) {
            addCriterion("GZF_NAME <>", value, "gzfName");
            return (Criteria) this;
        }

        public Criteria andGzfNameGreaterThan(String value) {
            addCriterion("GZF_NAME >", value, "gzfName");
            return (Criteria) this;
        }

        public Criteria andGzfNameGreaterThanOrEqualTo(String value) {
            addCriterion("GZF_NAME >=", value, "gzfName");
            return (Criteria) this;
        }

        public Criteria andGzfNameLessThan(String value) {
            addCriterion("GZF_NAME <", value, "gzfName");
            return (Criteria) this;
        }

        public Criteria andGzfNameLessThanOrEqualTo(String value) {
            addCriterion("GZF_NAME <=", value, "gzfName");
            return (Criteria) this;
        }

        public Criteria andGzfNameLike(String value) {
            addCriterion("GZF_NAME like", value, "gzfName");
            return (Criteria) this;
        }

        public Criteria andGzfNameNotLike(String value) {
            addCriterion("GZF_NAME not like", value, "gzfName");
            return (Criteria) this;
        }

        public Criteria andGzfNameIn(List<String> values) {
            addCriterion("GZF_NAME in", values, "gzfName");
            return (Criteria) this;
        }

        public Criteria andGzfNameNotIn(List<String> values) {
            addCriterion("GZF_NAME not in", values, "gzfName");
            return (Criteria) this;
        }

        public Criteria andGzfNameBetween(String value1, String value2) {
            addCriterion("GZF_NAME between", value1, value2, "gzfName");
            return (Criteria) this;
        }

        public Criteria andGzfNameNotBetween(String value1, String value2) {
            addCriterion("GZF_NAME not between", value1, value2, "gzfName");
            return (Criteria) this;
        }

        public Criteria andGzfStandardIsNull() {
            addCriterion("GZF_STANDARD is null");
            return (Criteria) this;
        }

        public Criteria andGzfStandardIsNotNull() {
            addCriterion("GZF_STANDARD is not null");
            return (Criteria) this;
        }

        public Criteria andGzfStandardEqualTo(String value) {
            addCriterion("GZF_STANDARD =", value, "gzfStandard");
            return (Criteria) this;
        }

        public Criteria andGzfStandardNotEqualTo(String value) {
            addCriterion("GZF_STANDARD <>", value, "gzfStandard");
            return (Criteria) this;
        }

        public Criteria andGzfStandardGreaterThan(String value) {
            addCriterion("GZF_STANDARD >", value, "gzfStandard");
            return (Criteria) this;
        }

        public Criteria andGzfStandardGreaterThanOrEqualTo(String value) {
            addCriterion("GZF_STANDARD >=", value, "gzfStandard");
            return (Criteria) this;
        }

        public Criteria andGzfStandardLessThan(String value) {
            addCriterion("GZF_STANDARD <", value, "gzfStandard");
            return (Criteria) this;
        }

        public Criteria andGzfStandardLessThanOrEqualTo(String value) {
            addCriterion("GZF_STANDARD <=", value, "gzfStandard");
            return (Criteria) this;
        }

        public Criteria andGzfStandardLike(String value) {
            addCriterion("GZF_STANDARD like", value, "gzfStandard");
            return (Criteria) this;
        }

        public Criteria andGzfStandardNotLike(String value) {
            addCriterion("GZF_STANDARD not like", value, "gzfStandard");
            return (Criteria) this;
        }

        public Criteria andGzfStandardIn(List<String> values) {
            addCriterion("GZF_STANDARD in", values, "gzfStandard");
            return (Criteria) this;
        }

        public Criteria andGzfStandardNotIn(List<String> values) {
            addCriterion("GZF_STANDARD not in", values, "gzfStandard");
            return (Criteria) this;
        }

        public Criteria andGzfStandardBetween(String value1, String value2) {
            addCriterion("GZF_STANDARD between", value1, value2, "gzfStandard");
            return (Criteria) this;
        }

        public Criteria andGzfStandardNotBetween(String value1, String value2) {
            addCriterion("GZF_STANDARD not between", value1, value2, "gzfStandard");
            return (Criteria) this;
        }

        public Criteria andGzfDayIsNull() {
            addCriterion("GZF_DAY is null");
            return (Criteria) this;
        }

        public Criteria andGzfDayIsNotNull() {
            addCriterion("GZF_DAY is not null");
            return (Criteria) this;
        }

        public Criteria andGzfDayEqualTo(String value) {
            addCriterion("GZF_DAY =", value, "gzfDay");
            return (Criteria) this;
        }

        public Criteria andGzfDayNotEqualTo(String value) {
            addCriterion("GZF_DAY <>", value, "gzfDay");
            return (Criteria) this;
        }

        public Criteria andGzfDayGreaterThan(String value) {
            addCriterion("GZF_DAY >", value, "gzfDay");
            return (Criteria) this;
        }

        public Criteria andGzfDayGreaterThanOrEqualTo(String value) {
            addCriterion("GZF_DAY >=", value, "gzfDay");
            return (Criteria) this;
        }

        public Criteria andGzfDayLessThan(String value) {
            addCriterion("GZF_DAY <", value, "gzfDay");
            return (Criteria) this;
        }

        public Criteria andGzfDayLessThanOrEqualTo(String value) {
            addCriterion("GZF_DAY <=", value, "gzfDay");
            return (Criteria) this;
        }

        public Criteria andGzfDayLike(String value) {
            addCriterion("GZF_DAY like", value, "gzfDay");
            return (Criteria) this;
        }

        public Criteria andGzfDayNotLike(String value) {
            addCriterion("GZF_DAY not like", value, "gzfDay");
            return (Criteria) this;
        }

        public Criteria andGzfDayIn(List<String> values) {
            addCriterion("GZF_DAY in", values, "gzfDay");
            return (Criteria) this;
        }

        public Criteria andGzfDayNotIn(List<String> values) {
            addCriterion("GZF_DAY not in", values, "gzfDay");
            return (Criteria) this;
        }

        public Criteria andGzfDayBetween(String value1, String value2) {
            addCriterion("GZF_DAY between", value1, value2, "gzfDay");
            return (Criteria) this;
        }

        public Criteria andGzfDayNotBetween(String value1, String value2) {
            addCriterion("GZF_DAY not between", value1, value2, "gzfDay");
            return (Criteria) this;
        }

        public Criteria andGzfPeopleIsNull() {
            addCriterion("GZF_PEOPLE is null");
            return (Criteria) this;
        }

        public Criteria andGzfPeopleIsNotNull() {
            addCriterion("GZF_PEOPLE is not null");
            return (Criteria) this;
        }

        public Criteria andGzfPeopleEqualTo(String value) {
            addCriterion("GZF_PEOPLE =", value, "gzfPeople");
            return (Criteria) this;
        }

        public Criteria andGzfPeopleNotEqualTo(String value) {
            addCriterion("GZF_PEOPLE <>", value, "gzfPeople");
            return (Criteria) this;
        }

        public Criteria andGzfPeopleGreaterThan(String value) {
            addCriterion("GZF_PEOPLE >", value, "gzfPeople");
            return (Criteria) this;
        }

        public Criteria andGzfPeopleGreaterThanOrEqualTo(String value) {
            addCriterion("GZF_PEOPLE >=", value, "gzfPeople");
            return (Criteria) this;
        }

        public Criteria andGzfPeopleLessThan(String value) {
            addCriterion("GZF_PEOPLE <", value, "gzfPeople");
            return (Criteria) this;
        }

        public Criteria andGzfPeopleLessThanOrEqualTo(String value) {
            addCriterion("GZF_PEOPLE <=", value, "gzfPeople");
            return (Criteria) this;
        }

        public Criteria andGzfPeopleLike(String value) {
            addCriterion("GZF_PEOPLE like", value, "gzfPeople");
            return (Criteria) this;
        }

        public Criteria andGzfPeopleNotLike(String value) {
            addCriterion("GZF_PEOPLE not like", value, "gzfPeople");
            return (Criteria) this;
        }

        public Criteria andGzfPeopleIn(List<String> values) {
            addCriterion("GZF_PEOPLE in", values, "gzfPeople");
            return (Criteria) this;
        }

        public Criteria andGzfPeopleNotIn(List<String> values) {
            addCriterion("GZF_PEOPLE not in", values, "gzfPeople");
            return (Criteria) this;
        }

        public Criteria andGzfPeopleBetween(String value1, String value2) {
            addCriterion("GZF_PEOPLE between", value1, value2, "gzfPeople");
            return (Criteria) this;
        }

        public Criteria andGzfPeopleNotBetween(String value1, String value2) {
            addCriterion("GZF_PEOPLE not between", value1, value2, "gzfPeople");
            return (Criteria) this;
        }

        public Criteria andGzfSubtotalIsNull() {
            addCriterion("GZF_SUBTOTAL is null");
            return (Criteria) this;
        }

        public Criteria andGzfSubtotalIsNotNull() {
            addCriterion("GZF_SUBTOTAL is not null");
            return (Criteria) this;
        }

        public Criteria andGzfSubtotalEqualTo(String value) {
            addCriterion("GZF_SUBTOTAL =", value, "gzfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGzfSubtotalNotEqualTo(String value) {
            addCriterion("GZF_SUBTOTAL <>", value, "gzfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGzfSubtotalGreaterThan(String value) {
            addCriterion("GZF_SUBTOTAL >", value, "gzfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGzfSubtotalGreaterThanOrEqualTo(String value) {
            addCriterion("GZF_SUBTOTAL >=", value, "gzfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGzfSubtotalLessThan(String value) {
            addCriterion("GZF_SUBTOTAL <", value, "gzfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGzfSubtotalLessThanOrEqualTo(String value) {
            addCriterion("GZF_SUBTOTAL <=", value, "gzfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGzfSubtotalLike(String value) {
            addCriterion("GZF_SUBTOTAL like", value, "gzfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGzfSubtotalNotLike(String value) {
            addCriterion("GZF_SUBTOTAL not like", value, "gzfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGzfSubtotalIn(List<String> values) {
            addCriterion("GZF_SUBTOTAL in", values, "gzfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGzfSubtotalNotIn(List<String> values) {
            addCriterion("GZF_SUBTOTAL not in", values, "gzfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGzfSubtotalBetween(String value1, String value2) {
            addCriterion("GZF_SUBTOTAL between", value1, value2, "gzfSubtotal");
            return (Criteria) this;
        }

        public Criteria andGzfSubtotalNotBetween(String value1, String value2) {
            addCriterion("GZF_SUBTOTAL not between", value1, value2, "gzfSubtotal");
            return (Criteria) this;
        }

        public Criteria andOtherNameIsNull() {
            addCriterion("OTHER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOtherNameIsNotNull() {
            addCriterion("OTHER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOtherNameEqualTo(String value) {
            addCriterion("OTHER_NAME =", value, "otherName");
            return (Criteria) this;
        }

        public Criteria andOtherNameNotEqualTo(String value) {
            addCriterion("OTHER_NAME <>", value, "otherName");
            return (Criteria) this;
        }

        public Criteria andOtherNameGreaterThan(String value) {
            addCriterion("OTHER_NAME >", value, "otherName");
            return (Criteria) this;
        }

        public Criteria andOtherNameGreaterThanOrEqualTo(String value) {
            addCriterion("OTHER_NAME >=", value, "otherName");
            return (Criteria) this;
        }

        public Criteria andOtherNameLessThan(String value) {
            addCriterion("OTHER_NAME <", value, "otherName");
            return (Criteria) this;
        }

        public Criteria andOtherNameLessThanOrEqualTo(String value) {
            addCriterion("OTHER_NAME <=", value, "otherName");
            return (Criteria) this;
        }

        public Criteria andOtherNameLike(String value) {
            addCriterion("OTHER_NAME like", value, "otherName");
            return (Criteria) this;
        }

        public Criteria andOtherNameNotLike(String value) {
            addCriterion("OTHER_NAME not like", value, "otherName");
            return (Criteria) this;
        }

        public Criteria andOtherNameIn(List<String> values) {
            addCriterion("OTHER_NAME in", values, "otherName");
            return (Criteria) this;
        }

        public Criteria andOtherNameNotIn(List<String> values) {
            addCriterion("OTHER_NAME not in", values, "otherName");
            return (Criteria) this;
        }

        public Criteria andOtherNameBetween(String value1, String value2) {
            addCriterion("OTHER_NAME between", value1, value2, "otherName");
            return (Criteria) this;
        }

        public Criteria andOtherNameNotBetween(String value1, String value2) {
            addCriterion("OTHER_NAME not between", value1, value2, "otherName");
            return (Criteria) this;
        }

        public Criteria andOtherStandardIsNull() {
            addCriterion("OTHER_STANDARD is null");
            return (Criteria) this;
        }

        public Criteria andOtherStandardIsNotNull() {
            addCriterion("OTHER_STANDARD is not null");
            return (Criteria) this;
        }

        public Criteria andOtherStandardEqualTo(String value) {
            addCriterion("OTHER_STANDARD =", value, "otherStandard");
            return (Criteria) this;
        }

        public Criteria andOtherStandardNotEqualTo(String value) {
            addCriterion("OTHER_STANDARD <>", value, "otherStandard");
            return (Criteria) this;
        }

        public Criteria andOtherStandardGreaterThan(String value) {
            addCriterion("OTHER_STANDARD >", value, "otherStandard");
            return (Criteria) this;
        }

        public Criteria andOtherStandardGreaterThanOrEqualTo(String value) {
            addCriterion("OTHER_STANDARD >=", value, "otherStandard");
            return (Criteria) this;
        }

        public Criteria andOtherStandardLessThan(String value) {
            addCriterion("OTHER_STANDARD <", value, "otherStandard");
            return (Criteria) this;
        }

        public Criteria andOtherStandardLessThanOrEqualTo(String value) {
            addCriterion("OTHER_STANDARD <=", value, "otherStandard");
            return (Criteria) this;
        }

        public Criteria andOtherStandardLike(String value) {
            addCriterion("OTHER_STANDARD like", value, "otherStandard");
            return (Criteria) this;
        }

        public Criteria andOtherStandardNotLike(String value) {
            addCriterion("OTHER_STANDARD not like", value, "otherStandard");
            return (Criteria) this;
        }

        public Criteria andOtherStandardIn(List<String> values) {
            addCriterion("OTHER_STANDARD in", values, "otherStandard");
            return (Criteria) this;
        }

        public Criteria andOtherStandardNotIn(List<String> values) {
            addCriterion("OTHER_STANDARD not in", values, "otherStandard");
            return (Criteria) this;
        }

        public Criteria andOtherStandardBetween(String value1, String value2) {
            addCriterion("OTHER_STANDARD between", value1, value2, "otherStandard");
            return (Criteria) this;
        }

        public Criteria andOtherStandardNotBetween(String value1, String value2) {
            addCriterion("OTHER_STANDARD not between", value1, value2, "otherStandard");
            return (Criteria) this;
        }

        public Criteria andOtherDayIsNull() {
            addCriterion("OTHER_DAY is null");
            return (Criteria) this;
        }

        public Criteria andOtherDayIsNotNull() {
            addCriterion("OTHER_DAY is not null");
            return (Criteria) this;
        }

        public Criteria andOtherDayEqualTo(String value) {
            addCriterion("OTHER_DAY =", value, "otherDay");
            return (Criteria) this;
        }

        public Criteria andOtherDayNotEqualTo(String value) {
            addCriterion("OTHER_DAY <>", value, "otherDay");
            return (Criteria) this;
        }

        public Criteria andOtherDayGreaterThan(String value) {
            addCriterion("OTHER_DAY >", value, "otherDay");
            return (Criteria) this;
        }

        public Criteria andOtherDayGreaterThanOrEqualTo(String value) {
            addCriterion("OTHER_DAY >=", value, "otherDay");
            return (Criteria) this;
        }

        public Criteria andOtherDayLessThan(String value) {
            addCriterion("OTHER_DAY <", value, "otherDay");
            return (Criteria) this;
        }

        public Criteria andOtherDayLessThanOrEqualTo(String value) {
            addCriterion("OTHER_DAY <=", value, "otherDay");
            return (Criteria) this;
        }

        public Criteria andOtherDayLike(String value) {
            addCriterion("OTHER_DAY like", value, "otherDay");
            return (Criteria) this;
        }

        public Criteria andOtherDayNotLike(String value) {
            addCriterion("OTHER_DAY not like", value, "otherDay");
            return (Criteria) this;
        }

        public Criteria andOtherDayIn(List<String> values) {
            addCriterion("OTHER_DAY in", values, "otherDay");
            return (Criteria) this;
        }

        public Criteria andOtherDayNotIn(List<String> values) {
            addCriterion("OTHER_DAY not in", values, "otherDay");
            return (Criteria) this;
        }

        public Criteria andOtherDayBetween(String value1, String value2) {
            addCriterion("OTHER_DAY between", value1, value2, "otherDay");
            return (Criteria) this;
        }

        public Criteria andOtherDayNotBetween(String value1, String value2) {
            addCriterion("OTHER_DAY not between", value1, value2, "otherDay");
            return (Criteria) this;
        }

        public Criteria andOtherPeopleIsNull() {
            addCriterion("OTHER_PEOPLE is null");
            return (Criteria) this;
        }

        public Criteria andOtherPeopleIsNotNull() {
            addCriterion("OTHER_PEOPLE is not null");
            return (Criteria) this;
        }

        public Criteria andOtherPeopleEqualTo(String value) {
            addCriterion("OTHER_PEOPLE =", value, "otherPeople");
            return (Criteria) this;
        }

        public Criteria andOtherPeopleNotEqualTo(String value) {
            addCriterion("OTHER_PEOPLE <>", value, "otherPeople");
            return (Criteria) this;
        }

        public Criteria andOtherPeopleGreaterThan(String value) {
            addCriterion("OTHER_PEOPLE >", value, "otherPeople");
            return (Criteria) this;
        }

        public Criteria andOtherPeopleGreaterThanOrEqualTo(String value) {
            addCriterion("OTHER_PEOPLE >=", value, "otherPeople");
            return (Criteria) this;
        }

        public Criteria andOtherPeopleLessThan(String value) {
            addCriterion("OTHER_PEOPLE <", value, "otherPeople");
            return (Criteria) this;
        }

        public Criteria andOtherPeopleLessThanOrEqualTo(String value) {
            addCriterion("OTHER_PEOPLE <=", value, "otherPeople");
            return (Criteria) this;
        }

        public Criteria andOtherPeopleLike(String value) {
            addCriterion("OTHER_PEOPLE like", value, "otherPeople");
            return (Criteria) this;
        }

        public Criteria andOtherPeopleNotLike(String value) {
            addCriterion("OTHER_PEOPLE not like", value, "otherPeople");
            return (Criteria) this;
        }

        public Criteria andOtherPeopleIn(List<String> values) {
            addCriterion("OTHER_PEOPLE in", values, "otherPeople");
            return (Criteria) this;
        }

        public Criteria andOtherPeopleNotIn(List<String> values) {
            addCriterion("OTHER_PEOPLE not in", values, "otherPeople");
            return (Criteria) this;
        }

        public Criteria andOtherPeopleBetween(String value1, String value2) {
            addCriterion("OTHER_PEOPLE between", value1, value2, "otherPeople");
            return (Criteria) this;
        }

        public Criteria andOtherPeopleNotBetween(String value1, String value2) {
            addCriterion("OTHER_PEOPLE not between", value1, value2, "otherPeople");
            return (Criteria) this;
        }

        public Criteria andOtherSubtotalIsNull() {
            addCriterion("OTHER_SUBTOTAL is null");
            return (Criteria) this;
        }

        public Criteria andOtherSubtotalIsNotNull() {
            addCriterion("OTHER_SUBTOTAL is not null");
            return (Criteria) this;
        }

        public Criteria andOtherSubtotalEqualTo(String value) {
            addCriterion("OTHER_SUBTOTAL =", value, "otherSubtotal");
            return (Criteria) this;
        }

        public Criteria andOtherSubtotalNotEqualTo(String value) {
            addCriterion("OTHER_SUBTOTAL <>", value, "otherSubtotal");
            return (Criteria) this;
        }

        public Criteria andOtherSubtotalGreaterThan(String value) {
            addCriterion("OTHER_SUBTOTAL >", value, "otherSubtotal");
            return (Criteria) this;
        }

        public Criteria andOtherSubtotalGreaterThanOrEqualTo(String value) {
            addCriterion("OTHER_SUBTOTAL >=", value, "otherSubtotal");
            return (Criteria) this;
        }

        public Criteria andOtherSubtotalLessThan(String value) {
            addCriterion("OTHER_SUBTOTAL <", value, "otherSubtotal");
            return (Criteria) this;
        }

        public Criteria andOtherSubtotalLessThanOrEqualTo(String value) {
            addCriterion("OTHER_SUBTOTAL <=", value, "otherSubtotal");
            return (Criteria) this;
        }

        public Criteria andOtherSubtotalLike(String value) {
            addCriterion("OTHER_SUBTOTAL like", value, "otherSubtotal");
            return (Criteria) this;
        }

        public Criteria andOtherSubtotalNotLike(String value) {
            addCriterion("OTHER_SUBTOTAL not like", value, "otherSubtotal");
            return (Criteria) this;
        }

        public Criteria andOtherSubtotalIn(List<String> values) {
            addCriterion("OTHER_SUBTOTAL in", values, "otherSubtotal");
            return (Criteria) this;
        }

        public Criteria andOtherSubtotalNotIn(List<String> values) {
            addCriterion("OTHER_SUBTOTAL not in", values, "otherSubtotal");
            return (Criteria) this;
        }

        public Criteria andOtherSubtotalBetween(String value1, String value2) {
            addCriterion("OTHER_SUBTOTAL between", value1, value2, "otherSubtotal");
            return (Criteria) this;
        }

        public Criteria andOtherSubtotalNotBetween(String value1, String value2) {
            addCriterion("OTHER_SUBTOTAL not between", value1, value2, "otherSubtotal");
            return (Criteria) this;
        }

        public Criteria andFeeDescIsNull() {
            addCriterion("FEE_DESC is null");
            return (Criteria) this;
        }

        public Criteria andFeeDescIsNotNull() {
            addCriterion("FEE_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andFeeDescEqualTo(String value) {
            addCriterion("FEE_DESC =", value, "feeDesc");
            return (Criteria) this;
        }

        public Criteria andFeeDescNotEqualTo(String value) {
            addCriterion("FEE_DESC <>", value, "feeDesc");
            return (Criteria) this;
        }

        public Criteria andFeeDescGreaterThan(String value) {
            addCriterion("FEE_DESC >", value, "feeDesc");
            return (Criteria) this;
        }

        public Criteria andFeeDescGreaterThanOrEqualTo(String value) {
            addCriterion("FEE_DESC >=", value, "feeDesc");
            return (Criteria) this;
        }

        public Criteria andFeeDescLessThan(String value) {
            addCriterion("FEE_DESC <", value, "feeDesc");
            return (Criteria) this;
        }

        public Criteria andFeeDescLessThanOrEqualTo(String value) {
            addCriterion("FEE_DESC <=", value, "feeDesc");
            return (Criteria) this;
        }

        public Criteria andFeeDescLike(String value) {
            addCriterion("FEE_DESC like", value, "feeDesc");
            return (Criteria) this;
        }

        public Criteria andFeeDescNotLike(String value) {
            addCriterion("FEE_DESC not like", value, "feeDesc");
            return (Criteria) this;
        }

        public Criteria andFeeDescIn(List<String> values) {
            addCriterion("FEE_DESC in", values, "feeDesc");
            return (Criteria) this;
        }

        public Criteria andFeeDescNotIn(List<String> values) {
            addCriterion("FEE_DESC not in", values, "feeDesc");
            return (Criteria) this;
        }

        public Criteria andFeeDescBetween(String value1, String value2) {
            addCriterion("FEE_DESC between", value1, value2, "feeDesc");
            return (Criteria) this;
        }

        public Criteria andFeeDescNotBetween(String value1, String value2) {
            addCriterion("FEE_DESC not between", value1, value2, "feeDesc");
            return (Criteria) this;
        }

        public Criteria andCscCertificateNoIsNull() {
            addCriterion("CSC_CERTIFICATE_NO is null");
            return (Criteria) this;
        }

        public Criteria andCscCertificateNoIsNotNull() {
            addCriterion("CSC_CERTIFICATE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCscCertificateNoEqualTo(String value) {
            addCriterion("CSC_CERTIFICATE_NO =", value, "cscCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCscCertificateNoNotEqualTo(String value) {
            addCriterion("CSC_CERTIFICATE_NO <>", value, "cscCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCscCertificateNoGreaterThan(String value) {
            addCriterion("CSC_CERTIFICATE_NO >", value, "cscCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCscCertificateNoGreaterThanOrEqualTo(String value) {
            addCriterion("CSC_CERTIFICATE_NO >=", value, "cscCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCscCertificateNoLessThan(String value) {
            addCriterion("CSC_CERTIFICATE_NO <", value, "cscCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCscCertificateNoLessThanOrEqualTo(String value) {
            addCriterion("CSC_CERTIFICATE_NO <=", value, "cscCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCscCertificateNoLike(String value) {
            addCriterion("CSC_CERTIFICATE_NO like", value, "cscCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCscCertificateNoNotLike(String value) {
            addCriterion("CSC_CERTIFICATE_NO not like", value, "cscCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCscCertificateNoIn(List<String> values) {
            addCriterion("CSC_CERTIFICATE_NO in", values, "cscCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCscCertificateNoNotIn(List<String> values) {
            addCriterion("CSC_CERTIFICATE_NO not in", values, "cscCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCscCertificateNoBetween(String value1, String value2) {
            addCriterion("CSC_CERTIFICATE_NO between", value1, value2, "cscCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCscCertificateNoNotBetween(String value1, String value2) {
            addCriterion("CSC_CERTIFICATE_NO not between", value1, value2, "cscCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCultureSummaryIsNull() {
            addCriterion("CULTURE_SUMMARY is null");
            return (Criteria) this;
        }

        public Criteria andCultureSummaryIsNotNull() {
            addCriterion("CULTURE_SUMMARY is not null");
            return (Criteria) this;
        }

        public Criteria andCultureSummaryEqualTo(String value) {
            addCriterion("CULTURE_SUMMARY =", value, "cultureSummary");
            return (Criteria) this;
        }

        public Criteria andCultureSummaryNotEqualTo(String value) {
            addCriterion("CULTURE_SUMMARY <>", value, "cultureSummary");
            return (Criteria) this;
        }

        public Criteria andCultureSummaryGreaterThan(String value) {
            addCriterion("CULTURE_SUMMARY >", value, "cultureSummary");
            return (Criteria) this;
        }

        public Criteria andCultureSummaryGreaterThanOrEqualTo(String value) {
            addCriterion("CULTURE_SUMMARY >=", value, "cultureSummary");
            return (Criteria) this;
        }

        public Criteria andCultureSummaryLessThan(String value) {
            addCriterion("CULTURE_SUMMARY <", value, "cultureSummary");
            return (Criteria) this;
        }

        public Criteria andCultureSummaryLessThanOrEqualTo(String value) {
            addCriterion("CULTURE_SUMMARY <=", value, "cultureSummary");
            return (Criteria) this;
        }

        public Criteria andCultureSummaryLike(String value) {
            addCriterion("CULTURE_SUMMARY like", value, "cultureSummary");
            return (Criteria) this;
        }

        public Criteria andCultureSummaryNotLike(String value) {
            addCriterion("CULTURE_SUMMARY not like", value, "cultureSummary");
            return (Criteria) this;
        }

        public Criteria andCultureSummaryIn(List<String> values) {
            addCriterion("CULTURE_SUMMARY in", values, "cultureSummary");
            return (Criteria) this;
        }

        public Criteria andCultureSummaryNotIn(List<String> values) {
            addCriterion("CULTURE_SUMMARY not in", values, "cultureSummary");
            return (Criteria) this;
        }

        public Criteria andCultureSummaryBetween(String value1, String value2) {
            addCriterion("CULTURE_SUMMARY between", value1, value2, "cultureSummary");
            return (Criteria) this;
        }

        public Criteria andCultureSummaryNotBetween(String value1, String value2) {
            addCriterion("CULTURE_SUMMARY not between", value1, value2, "cultureSummary");
            return (Criteria) this;
        }

        public Criteria andBackTutorAdviceIsNull() {
            addCriterion("BACK_TUTOR_ADVICE is null");
            return (Criteria) this;
        }

        public Criteria andBackTutorAdviceIsNotNull() {
            addCriterion("BACK_TUTOR_ADVICE is not null");
            return (Criteria) this;
        }

        public Criteria andBackTutorAdviceEqualTo(String value) {
            addCriterion("BACK_TUTOR_ADVICE =", value, "backTutorAdvice");
            return (Criteria) this;
        }

        public Criteria andBackTutorAdviceNotEqualTo(String value) {
            addCriterion("BACK_TUTOR_ADVICE <>", value, "backTutorAdvice");
            return (Criteria) this;
        }

        public Criteria andBackTutorAdviceGreaterThan(String value) {
            addCriterion("BACK_TUTOR_ADVICE >", value, "backTutorAdvice");
            return (Criteria) this;
        }

        public Criteria andBackTutorAdviceGreaterThanOrEqualTo(String value) {
            addCriterion("BACK_TUTOR_ADVICE >=", value, "backTutorAdvice");
            return (Criteria) this;
        }

        public Criteria andBackTutorAdviceLessThan(String value) {
            addCriterion("BACK_TUTOR_ADVICE <", value, "backTutorAdvice");
            return (Criteria) this;
        }

        public Criteria andBackTutorAdviceLessThanOrEqualTo(String value) {
            addCriterion("BACK_TUTOR_ADVICE <=", value, "backTutorAdvice");
            return (Criteria) this;
        }

        public Criteria andBackTutorAdviceLike(String value) {
            addCriterion("BACK_TUTOR_ADVICE like", value, "backTutorAdvice");
            return (Criteria) this;
        }

        public Criteria andBackTutorAdviceNotLike(String value) {
            addCriterion("BACK_TUTOR_ADVICE not like", value, "backTutorAdvice");
            return (Criteria) this;
        }

        public Criteria andBackTutorAdviceIn(List<String> values) {
            addCriterion("BACK_TUTOR_ADVICE in", values, "backTutorAdvice");
            return (Criteria) this;
        }

        public Criteria andBackTutorAdviceNotIn(List<String> values) {
            addCriterion("BACK_TUTOR_ADVICE not in", values, "backTutorAdvice");
            return (Criteria) this;
        }

        public Criteria andBackTutorAdviceBetween(String value1, String value2) {
            addCriterion("BACK_TUTOR_ADVICE between", value1, value2, "backTutorAdvice");
            return (Criteria) this;
        }

        public Criteria andBackTutorAdviceNotBetween(String value1, String value2) {
            addCriterion("BACK_TUTOR_ADVICE not between", value1, value2, "backTutorAdvice");
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

        public Criteria andTutorBackAuditDateIsNull() {
            addCriterion("TUTOR_BACK_AUDIT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTutorBackAuditDateIsNotNull() {
            addCriterion("TUTOR_BACK_AUDIT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTutorBackAuditDateEqualTo(String value) {
            addCriterion("TUTOR_BACK_AUDIT_DATE =", value, "tutorBackAuditDate");
            return (Criteria) this;
        }

        public Criteria andTutorBackAuditDateNotEqualTo(String value) {
            addCriterion("TUTOR_BACK_AUDIT_DATE <>", value, "tutorBackAuditDate");
            return (Criteria) this;
        }

        public Criteria andTutorBackAuditDateGreaterThan(String value) {
            addCriterion("TUTOR_BACK_AUDIT_DATE >", value, "tutorBackAuditDate");
            return (Criteria) this;
        }

        public Criteria andTutorBackAuditDateGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_BACK_AUDIT_DATE >=", value, "tutorBackAuditDate");
            return (Criteria) this;
        }

        public Criteria andTutorBackAuditDateLessThan(String value) {
            addCriterion("TUTOR_BACK_AUDIT_DATE <", value, "tutorBackAuditDate");
            return (Criteria) this;
        }

        public Criteria andTutorBackAuditDateLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_BACK_AUDIT_DATE <=", value, "tutorBackAuditDate");
            return (Criteria) this;
        }

        public Criteria andTutorBackAuditDateLike(String value) {
            addCriterion("TUTOR_BACK_AUDIT_DATE like", value, "tutorBackAuditDate");
            return (Criteria) this;
        }

        public Criteria andTutorBackAuditDateNotLike(String value) {
            addCriterion("TUTOR_BACK_AUDIT_DATE not like", value, "tutorBackAuditDate");
            return (Criteria) this;
        }

        public Criteria andTutorBackAuditDateIn(List<String> values) {
            addCriterion("TUTOR_BACK_AUDIT_DATE in", values, "tutorBackAuditDate");
            return (Criteria) this;
        }

        public Criteria andTutorBackAuditDateNotIn(List<String> values) {
            addCriterion("TUTOR_BACK_AUDIT_DATE not in", values, "tutorBackAuditDate");
            return (Criteria) this;
        }

        public Criteria andTutorBackAuditDateBetween(String value1, String value2) {
            addCriterion("TUTOR_BACK_AUDIT_DATE between", value1, value2, "tutorBackAuditDate");
            return (Criteria) this;
        }

        public Criteria andTutorBackAuditDateNotBetween(String value1, String value2) {
            addCriterion("TUTOR_BACK_AUDIT_DATE not between", value1, value2, "tutorBackAuditDate");
            return (Criteria) this;
        }

        public Criteria andProjContractUrlIsNull() {
            addCriterion("PROJ_CONTRACT_URL is null");
            return (Criteria) this;
        }

        public Criteria andProjContractUrlIsNotNull() {
            addCriterion("PROJ_CONTRACT_URL is not null");
            return (Criteria) this;
        }

        public Criteria andProjContractUrlEqualTo(String value) {
            addCriterion("PROJ_CONTRACT_URL =", value, "projContractUrl");
            return (Criteria) this;
        }

        public Criteria andProjContractUrlNotEqualTo(String value) {
            addCriterion("PROJ_CONTRACT_URL <>", value, "projContractUrl");
            return (Criteria) this;
        }

        public Criteria andProjContractUrlGreaterThan(String value) {
            addCriterion("PROJ_CONTRACT_URL >", value, "projContractUrl");
            return (Criteria) this;
        }

        public Criteria andProjContractUrlGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_CONTRACT_URL >=", value, "projContractUrl");
            return (Criteria) this;
        }

        public Criteria andProjContractUrlLessThan(String value) {
            addCriterion("PROJ_CONTRACT_URL <", value, "projContractUrl");
            return (Criteria) this;
        }

        public Criteria andProjContractUrlLessThanOrEqualTo(String value) {
            addCriterion("PROJ_CONTRACT_URL <=", value, "projContractUrl");
            return (Criteria) this;
        }

        public Criteria andProjContractUrlLike(String value) {
            addCriterion("PROJ_CONTRACT_URL like", value, "projContractUrl");
            return (Criteria) this;
        }

        public Criteria andProjContractUrlNotLike(String value) {
            addCriterion("PROJ_CONTRACT_URL not like", value, "projContractUrl");
            return (Criteria) this;
        }

        public Criteria andProjContractUrlIn(List<String> values) {
            addCriterion("PROJ_CONTRACT_URL in", values, "projContractUrl");
            return (Criteria) this;
        }

        public Criteria andProjContractUrlNotIn(List<String> values) {
            addCriterion("PROJ_CONTRACT_URL not in", values, "projContractUrl");
            return (Criteria) this;
        }

        public Criteria andProjContractUrlBetween(String value1, String value2) {
            addCriterion("PROJ_CONTRACT_URL between", value1, value2, "projContractUrl");
            return (Criteria) this;
        }

        public Criteria andProjContractUrlNotBetween(String value1, String value2) {
            addCriterion("PROJ_CONTRACT_URL not between", value1, value2, "projContractUrl");
            return (Criteria) this;
        }

        public Criteria andProjMoneyUrlIsNull() {
            addCriterion("PROJ_MONEY_URL is null");
            return (Criteria) this;
        }

        public Criteria andProjMoneyUrlIsNotNull() {
            addCriterion("PROJ_MONEY_URL is not null");
            return (Criteria) this;
        }

        public Criteria andProjMoneyUrlEqualTo(String value) {
            addCriterion("PROJ_MONEY_URL =", value, "projMoneyUrl");
            return (Criteria) this;
        }

        public Criteria andProjMoneyUrlNotEqualTo(String value) {
            addCriterion("PROJ_MONEY_URL <>", value, "projMoneyUrl");
            return (Criteria) this;
        }

        public Criteria andProjMoneyUrlGreaterThan(String value) {
            addCriterion("PROJ_MONEY_URL >", value, "projMoneyUrl");
            return (Criteria) this;
        }

        public Criteria andProjMoneyUrlGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_MONEY_URL >=", value, "projMoneyUrl");
            return (Criteria) this;
        }

        public Criteria andProjMoneyUrlLessThan(String value) {
            addCriterion("PROJ_MONEY_URL <", value, "projMoneyUrl");
            return (Criteria) this;
        }

        public Criteria andProjMoneyUrlLessThanOrEqualTo(String value) {
            addCriterion("PROJ_MONEY_URL <=", value, "projMoneyUrl");
            return (Criteria) this;
        }

        public Criteria andProjMoneyUrlLike(String value) {
            addCriterion("PROJ_MONEY_URL like", value, "projMoneyUrl");
            return (Criteria) this;
        }

        public Criteria andProjMoneyUrlNotLike(String value) {
            addCriterion("PROJ_MONEY_URL not like", value, "projMoneyUrl");
            return (Criteria) this;
        }

        public Criteria andProjMoneyUrlIn(List<String> values) {
            addCriterion("PROJ_MONEY_URL in", values, "projMoneyUrl");
            return (Criteria) this;
        }

        public Criteria andProjMoneyUrlNotIn(List<String> values) {
            addCriterion("PROJ_MONEY_URL not in", values, "projMoneyUrl");
            return (Criteria) this;
        }

        public Criteria andProjMoneyUrlBetween(String value1, String value2) {
            addCriterion("PROJ_MONEY_URL between", value1, value2, "projMoneyUrl");
            return (Criteria) this;
        }

        public Criteria andProjMoneyUrlNotBetween(String value1, String value2) {
            addCriterion("PROJ_MONEY_URL not between", value1, value2, "projMoneyUrl");
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