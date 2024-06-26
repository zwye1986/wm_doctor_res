package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class NydbDefenceApplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NydbDefenceApplyExample() {
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

        public Criteria andTutorPhoneIsNull() {
            addCriterion("TUTOR_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andTutorPhoneIsNotNull() {
            addCriterion("TUTOR_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andTutorPhoneEqualTo(String value) {
            addCriterion("TUTOR_PHONE =", value, "tutorPhone");
            return (Criteria) this;
        }

        public Criteria andTutorPhoneNotEqualTo(String value) {
            addCriterion("TUTOR_PHONE <>", value, "tutorPhone");
            return (Criteria) this;
        }

        public Criteria andTutorPhoneGreaterThan(String value) {
            addCriterion("TUTOR_PHONE >", value, "tutorPhone");
            return (Criteria) this;
        }

        public Criteria andTutorPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_PHONE >=", value, "tutorPhone");
            return (Criteria) this;
        }

        public Criteria andTutorPhoneLessThan(String value) {
            addCriterion("TUTOR_PHONE <", value, "tutorPhone");
            return (Criteria) this;
        }

        public Criteria andTutorPhoneLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_PHONE <=", value, "tutorPhone");
            return (Criteria) this;
        }

        public Criteria andTutorPhoneLike(String value) {
            addCriterion("TUTOR_PHONE like", value, "tutorPhone");
            return (Criteria) this;
        }

        public Criteria andTutorPhoneNotLike(String value) {
            addCriterion("TUTOR_PHONE not like", value, "tutorPhone");
            return (Criteria) this;
        }

        public Criteria andTutorPhoneIn(List<String> values) {
            addCriterion("TUTOR_PHONE in", values, "tutorPhone");
            return (Criteria) this;
        }

        public Criteria andTutorPhoneNotIn(List<String> values) {
            addCriterion("TUTOR_PHONE not in", values, "tutorPhone");
            return (Criteria) this;
        }

        public Criteria andTutorPhoneBetween(String value1, String value2) {
            addCriterion("TUTOR_PHONE between", value1, value2, "tutorPhone");
            return (Criteria) this;
        }

        public Criteria andTutorPhoneNotBetween(String value1, String value2) {
            addCriterion("TUTOR_PHONE not between", value1, value2, "tutorPhone");
            return (Criteria) this;
        }

        public Criteria andTwoTutorFlowIsNull() {
            addCriterion("TWO_TUTOR_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andTwoTutorFlowIsNotNull() {
            addCriterion("TWO_TUTOR_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andTwoTutorFlowEqualTo(String value) {
            addCriterion("TWO_TUTOR_FLOW =", value, "twoTutorFlow");
            return (Criteria) this;
        }

        public Criteria andTwoTutorFlowNotEqualTo(String value) {
            addCriterion("TWO_TUTOR_FLOW <>", value, "twoTutorFlow");
            return (Criteria) this;
        }

        public Criteria andTwoTutorFlowGreaterThan(String value) {
            addCriterion("TWO_TUTOR_FLOW >", value, "twoTutorFlow");
            return (Criteria) this;
        }

        public Criteria andTwoTutorFlowGreaterThanOrEqualTo(String value) {
            addCriterion("TWO_TUTOR_FLOW >=", value, "twoTutorFlow");
            return (Criteria) this;
        }

        public Criteria andTwoTutorFlowLessThan(String value) {
            addCriterion("TWO_TUTOR_FLOW <", value, "twoTutorFlow");
            return (Criteria) this;
        }

        public Criteria andTwoTutorFlowLessThanOrEqualTo(String value) {
            addCriterion("TWO_TUTOR_FLOW <=", value, "twoTutorFlow");
            return (Criteria) this;
        }

        public Criteria andTwoTutorFlowLike(String value) {
            addCriterion("TWO_TUTOR_FLOW like", value, "twoTutorFlow");
            return (Criteria) this;
        }

        public Criteria andTwoTutorFlowNotLike(String value) {
            addCriterion("TWO_TUTOR_FLOW not like", value, "twoTutorFlow");
            return (Criteria) this;
        }

        public Criteria andTwoTutorFlowIn(List<String> values) {
            addCriterion("TWO_TUTOR_FLOW in", values, "twoTutorFlow");
            return (Criteria) this;
        }

        public Criteria andTwoTutorFlowNotIn(List<String> values) {
            addCriterion("TWO_TUTOR_FLOW not in", values, "twoTutorFlow");
            return (Criteria) this;
        }

        public Criteria andTwoTutorFlowBetween(String value1, String value2) {
            addCriterion("TWO_TUTOR_FLOW between", value1, value2, "twoTutorFlow");
            return (Criteria) this;
        }

        public Criteria andTwoTutorFlowNotBetween(String value1, String value2) {
            addCriterion("TWO_TUTOR_FLOW not between", value1, value2, "twoTutorFlow");
            return (Criteria) this;
        }

        public Criteria andTwoTutorNameIsNull() {
            addCriterion("TWO_TUTOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTwoTutorNameIsNotNull() {
            addCriterion("TWO_TUTOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTwoTutorNameEqualTo(String value) {
            addCriterion("TWO_TUTOR_NAME =", value, "twoTutorName");
            return (Criteria) this;
        }

        public Criteria andTwoTutorNameNotEqualTo(String value) {
            addCriterion("TWO_TUTOR_NAME <>", value, "twoTutorName");
            return (Criteria) this;
        }

        public Criteria andTwoTutorNameGreaterThan(String value) {
            addCriterion("TWO_TUTOR_NAME >", value, "twoTutorName");
            return (Criteria) this;
        }

        public Criteria andTwoTutorNameGreaterThanOrEqualTo(String value) {
            addCriterion("TWO_TUTOR_NAME >=", value, "twoTutorName");
            return (Criteria) this;
        }

        public Criteria andTwoTutorNameLessThan(String value) {
            addCriterion("TWO_TUTOR_NAME <", value, "twoTutorName");
            return (Criteria) this;
        }

        public Criteria andTwoTutorNameLessThanOrEqualTo(String value) {
            addCriterion("TWO_TUTOR_NAME <=", value, "twoTutorName");
            return (Criteria) this;
        }

        public Criteria andTwoTutorNameLike(String value) {
            addCriterion("TWO_TUTOR_NAME like", value, "twoTutorName");
            return (Criteria) this;
        }

        public Criteria andTwoTutorNameNotLike(String value) {
            addCriterion("TWO_TUTOR_NAME not like", value, "twoTutorName");
            return (Criteria) this;
        }

        public Criteria andTwoTutorNameIn(List<String> values) {
            addCriterion("TWO_TUTOR_NAME in", values, "twoTutorName");
            return (Criteria) this;
        }

        public Criteria andTwoTutorNameNotIn(List<String> values) {
            addCriterion("TWO_TUTOR_NAME not in", values, "twoTutorName");
            return (Criteria) this;
        }

        public Criteria andTwoTutorNameBetween(String value1, String value2) {
            addCriterion("TWO_TUTOR_NAME between", value1, value2, "twoTutorName");
            return (Criteria) this;
        }

        public Criteria andTwoTutorNameNotBetween(String value1, String value2) {
            addCriterion("TWO_TUTOR_NAME not between", value1, value2, "twoTutorName");
            return (Criteria) this;
        }

        public Criteria andTwoTutorPhoneIsNull() {
            addCriterion("TWO_TUTOR_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andTwoTutorPhoneIsNotNull() {
            addCriterion("TWO_TUTOR_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andTwoTutorPhoneEqualTo(String value) {
            addCriterion("TWO_TUTOR_PHONE =", value, "twoTutorPhone");
            return (Criteria) this;
        }

        public Criteria andTwoTutorPhoneNotEqualTo(String value) {
            addCriterion("TWO_TUTOR_PHONE <>", value, "twoTutorPhone");
            return (Criteria) this;
        }

        public Criteria andTwoTutorPhoneGreaterThan(String value) {
            addCriterion("TWO_TUTOR_PHONE >", value, "twoTutorPhone");
            return (Criteria) this;
        }

        public Criteria andTwoTutorPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("TWO_TUTOR_PHONE >=", value, "twoTutorPhone");
            return (Criteria) this;
        }

        public Criteria andTwoTutorPhoneLessThan(String value) {
            addCriterion("TWO_TUTOR_PHONE <", value, "twoTutorPhone");
            return (Criteria) this;
        }

        public Criteria andTwoTutorPhoneLessThanOrEqualTo(String value) {
            addCriterion("TWO_TUTOR_PHONE <=", value, "twoTutorPhone");
            return (Criteria) this;
        }

        public Criteria andTwoTutorPhoneLike(String value) {
            addCriterion("TWO_TUTOR_PHONE like", value, "twoTutorPhone");
            return (Criteria) this;
        }

        public Criteria andTwoTutorPhoneNotLike(String value) {
            addCriterion("TWO_TUTOR_PHONE not like", value, "twoTutorPhone");
            return (Criteria) this;
        }

        public Criteria andTwoTutorPhoneIn(List<String> values) {
            addCriterion("TWO_TUTOR_PHONE in", values, "twoTutorPhone");
            return (Criteria) this;
        }

        public Criteria andTwoTutorPhoneNotIn(List<String> values) {
            addCriterion("TWO_TUTOR_PHONE not in", values, "twoTutorPhone");
            return (Criteria) this;
        }

        public Criteria andTwoTutorPhoneBetween(String value1, String value2) {
            addCriterion("TWO_TUTOR_PHONE between", value1, value2, "twoTutorPhone");
            return (Criteria) this;
        }

        public Criteria andTwoTutorPhoneNotBetween(String value1, String value2) {
            addCriterion("TWO_TUTOR_PHONE not between", value1, value2, "twoTutorPhone");
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

        public Criteria andTrainGradationIdIsNull() {
            addCriterion("TRAIN_GRADATION_ID is null");
            return (Criteria) this;
        }

        public Criteria andTrainGradationIdIsNotNull() {
            addCriterion("TRAIN_GRADATION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTrainGradationIdEqualTo(String value) {
            addCriterion("TRAIN_GRADATION_ID =", value, "trainGradationId");
            return (Criteria) this;
        }

        public Criteria andTrainGradationIdNotEqualTo(String value) {
            addCriterion("TRAIN_GRADATION_ID <>", value, "trainGradationId");
            return (Criteria) this;
        }

        public Criteria andTrainGradationIdGreaterThan(String value) {
            addCriterion("TRAIN_GRADATION_ID >", value, "trainGradationId");
            return (Criteria) this;
        }

        public Criteria andTrainGradationIdGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_GRADATION_ID >=", value, "trainGradationId");
            return (Criteria) this;
        }

        public Criteria andTrainGradationIdLessThan(String value) {
            addCriterion("TRAIN_GRADATION_ID <", value, "trainGradationId");
            return (Criteria) this;
        }

        public Criteria andTrainGradationIdLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_GRADATION_ID <=", value, "trainGradationId");
            return (Criteria) this;
        }

        public Criteria andTrainGradationIdLike(String value) {
            addCriterion("TRAIN_GRADATION_ID like", value, "trainGradationId");
            return (Criteria) this;
        }

        public Criteria andTrainGradationIdNotLike(String value) {
            addCriterion("TRAIN_GRADATION_ID not like", value, "trainGradationId");
            return (Criteria) this;
        }

        public Criteria andTrainGradationIdIn(List<String> values) {
            addCriterion("TRAIN_GRADATION_ID in", values, "trainGradationId");
            return (Criteria) this;
        }

        public Criteria andTrainGradationIdNotIn(List<String> values) {
            addCriterion("TRAIN_GRADATION_ID not in", values, "trainGradationId");
            return (Criteria) this;
        }

        public Criteria andTrainGradationIdBetween(String value1, String value2) {
            addCriterion("TRAIN_GRADATION_ID between", value1, value2, "trainGradationId");
            return (Criteria) this;
        }

        public Criteria andTrainGradationIdNotBetween(String value1, String value2) {
            addCriterion("TRAIN_GRADATION_ID not between", value1, value2, "trainGradationId");
            return (Criteria) this;
        }

        public Criteria andTrainGradationNameIsNull() {
            addCriterion("TRAIN_GRADATION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTrainGradationNameIsNotNull() {
            addCriterion("TRAIN_GRADATION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTrainGradationNameEqualTo(String value) {
            addCriterion("TRAIN_GRADATION_NAME =", value, "trainGradationName");
            return (Criteria) this;
        }

        public Criteria andTrainGradationNameNotEqualTo(String value) {
            addCriterion("TRAIN_GRADATION_NAME <>", value, "trainGradationName");
            return (Criteria) this;
        }

        public Criteria andTrainGradationNameGreaterThan(String value) {
            addCriterion("TRAIN_GRADATION_NAME >", value, "trainGradationName");
            return (Criteria) this;
        }

        public Criteria andTrainGradationNameGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_GRADATION_NAME >=", value, "trainGradationName");
            return (Criteria) this;
        }

        public Criteria andTrainGradationNameLessThan(String value) {
            addCriterion("TRAIN_GRADATION_NAME <", value, "trainGradationName");
            return (Criteria) this;
        }

        public Criteria andTrainGradationNameLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_GRADATION_NAME <=", value, "trainGradationName");
            return (Criteria) this;
        }

        public Criteria andTrainGradationNameLike(String value) {
            addCriterion("TRAIN_GRADATION_NAME like", value, "trainGradationName");
            return (Criteria) this;
        }

        public Criteria andTrainGradationNameNotLike(String value) {
            addCriterion("TRAIN_GRADATION_NAME not like", value, "trainGradationName");
            return (Criteria) this;
        }

        public Criteria andTrainGradationNameIn(List<String> values) {
            addCriterion("TRAIN_GRADATION_NAME in", values, "trainGradationName");
            return (Criteria) this;
        }

        public Criteria andTrainGradationNameNotIn(List<String> values) {
            addCriterion("TRAIN_GRADATION_NAME not in", values, "trainGradationName");
            return (Criteria) this;
        }

        public Criteria andTrainGradationNameBetween(String value1, String value2) {
            addCriterion("TRAIN_GRADATION_NAME between", value1, value2, "trainGradationName");
            return (Criteria) this;
        }

        public Criteria andTrainGradationNameNotBetween(String value1, String value2) {
            addCriterion("TRAIN_GRADATION_NAME not between", value1, value2, "trainGradationName");
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

        public Criteria andDefenceTimeIsNull() {
            addCriterion("DEFENCE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andDefenceTimeIsNotNull() {
            addCriterion("DEFENCE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andDefenceTimeEqualTo(String value) {
            addCriterion("DEFENCE_TIME =", value, "defenceTime");
            return (Criteria) this;
        }

        public Criteria andDefenceTimeNotEqualTo(String value) {
            addCriterion("DEFENCE_TIME <>", value, "defenceTime");
            return (Criteria) this;
        }

        public Criteria andDefenceTimeGreaterThan(String value) {
            addCriterion("DEFENCE_TIME >", value, "defenceTime");
            return (Criteria) this;
        }

        public Criteria andDefenceTimeGreaterThanOrEqualTo(String value) {
            addCriterion("DEFENCE_TIME >=", value, "defenceTime");
            return (Criteria) this;
        }

        public Criteria andDefenceTimeLessThan(String value) {
            addCriterion("DEFENCE_TIME <", value, "defenceTime");
            return (Criteria) this;
        }

        public Criteria andDefenceTimeLessThanOrEqualTo(String value) {
            addCriterion("DEFENCE_TIME <=", value, "defenceTime");
            return (Criteria) this;
        }

        public Criteria andDefenceTimeLike(String value) {
            addCriterion("DEFENCE_TIME like", value, "defenceTime");
            return (Criteria) this;
        }

        public Criteria andDefenceTimeNotLike(String value) {
            addCriterion("DEFENCE_TIME not like", value, "defenceTime");
            return (Criteria) this;
        }

        public Criteria andDefenceTimeIn(List<String> values) {
            addCriterion("DEFENCE_TIME in", values, "defenceTime");
            return (Criteria) this;
        }

        public Criteria andDefenceTimeNotIn(List<String> values) {
            addCriterion("DEFENCE_TIME not in", values, "defenceTime");
            return (Criteria) this;
        }

        public Criteria andDefenceTimeBetween(String value1, String value2) {
            addCriterion("DEFENCE_TIME between", value1, value2, "defenceTime");
            return (Criteria) this;
        }

        public Criteria andDefenceTimeNotBetween(String value1, String value2) {
            addCriterion("DEFENCE_TIME not between", value1, value2, "defenceTime");
            return (Criteria) this;
        }

        public Criteria andPaperAuditNameIsNull() {
            addCriterion("PAPER_AUDIT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPaperAuditNameIsNotNull() {
            addCriterion("PAPER_AUDIT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPaperAuditNameEqualTo(String value) {
            addCriterion("PAPER_AUDIT_NAME =", value, "paperAuditName");
            return (Criteria) this;
        }

        public Criteria andPaperAuditNameNotEqualTo(String value) {
            addCriterion("PAPER_AUDIT_NAME <>", value, "paperAuditName");
            return (Criteria) this;
        }

        public Criteria andPaperAuditNameGreaterThan(String value) {
            addCriterion("PAPER_AUDIT_NAME >", value, "paperAuditName");
            return (Criteria) this;
        }

        public Criteria andPaperAuditNameGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER_AUDIT_NAME >=", value, "paperAuditName");
            return (Criteria) this;
        }

        public Criteria andPaperAuditNameLessThan(String value) {
            addCriterion("PAPER_AUDIT_NAME <", value, "paperAuditName");
            return (Criteria) this;
        }

        public Criteria andPaperAuditNameLessThanOrEqualTo(String value) {
            addCriterion("PAPER_AUDIT_NAME <=", value, "paperAuditName");
            return (Criteria) this;
        }

        public Criteria andPaperAuditNameLike(String value) {
            addCriterion("PAPER_AUDIT_NAME like", value, "paperAuditName");
            return (Criteria) this;
        }

        public Criteria andPaperAuditNameNotLike(String value) {
            addCriterion("PAPER_AUDIT_NAME not like", value, "paperAuditName");
            return (Criteria) this;
        }

        public Criteria andPaperAuditNameIn(List<String> values) {
            addCriterion("PAPER_AUDIT_NAME in", values, "paperAuditName");
            return (Criteria) this;
        }

        public Criteria andPaperAuditNameNotIn(List<String> values) {
            addCriterion("PAPER_AUDIT_NAME not in", values, "paperAuditName");
            return (Criteria) this;
        }

        public Criteria andPaperAuditNameBetween(String value1, String value2) {
            addCriterion("PAPER_AUDIT_NAME between", value1, value2, "paperAuditName");
            return (Criteria) this;
        }

        public Criteria andPaperAuditNameNotBetween(String value1, String value2) {
            addCriterion("PAPER_AUDIT_NAME not between", value1, value2, "paperAuditName");
            return (Criteria) this;
        }

        public Criteria andPaperChiTitleIsNull() {
            addCriterion("PAPER_CHI_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andPaperChiTitleIsNotNull() {
            addCriterion("PAPER_CHI_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andPaperChiTitleEqualTo(String value) {
            addCriterion("PAPER_CHI_TITLE =", value, "paperChiTitle");
            return (Criteria) this;
        }

        public Criteria andPaperChiTitleNotEqualTo(String value) {
            addCriterion("PAPER_CHI_TITLE <>", value, "paperChiTitle");
            return (Criteria) this;
        }

        public Criteria andPaperChiTitleGreaterThan(String value) {
            addCriterion("PAPER_CHI_TITLE >", value, "paperChiTitle");
            return (Criteria) this;
        }

        public Criteria andPaperChiTitleGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER_CHI_TITLE >=", value, "paperChiTitle");
            return (Criteria) this;
        }

        public Criteria andPaperChiTitleLessThan(String value) {
            addCriterion("PAPER_CHI_TITLE <", value, "paperChiTitle");
            return (Criteria) this;
        }

        public Criteria andPaperChiTitleLessThanOrEqualTo(String value) {
            addCriterion("PAPER_CHI_TITLE <=", value, "paperChiTitle");
            return (Criteria) this;
        }

        public Criteria andPaperChiTitleLike(String value) {
            addCriterion("PAPER_CHI_TITLE like", value, "paperChiTitle");
            return (Criteria) this;
        }

        public Criteria andPaperChiTitleNotLike(String value) {
            addCriterion("PAPER_CHI_TITLE not like", value, "paperChiTitle");
            return (Criteria) this;
        }

        public Criteria andPaperChiTitleIn(List<String> values) {
            addCriterion("PAPER_CHI_TITLE in", values, "paperChiTitle");
            return (Criteria) this;
        }

        public Criteria andPaperChiTitleNotIn(List<String> values) {
            addCriterion("PAPER_CHI_TITLE not in", values, "paperChiTitle");
            return (Criteria) this;
        }

        public Criteria andPaperChiTitleBetween(String value1, String value2) {
            addCriterion("PAPER_CHI_TITLE between", value1, value2, "paperChiTitle");
            return (Criteria) this;
        }

        public Criteria andPaperChiTitleNotBetween(String value1, String value2) {
            addCriterion("PAPER_CHI_TITLE not between", value1, value2, "paperChiTitle");
            return (Criteria) this;
        }

        public Criteria andPaperEngTitleIsNull() {
            addCriterion("PAPER_ENG_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andPaperEngTitleIsNotNull() {
            addCriterion("PAPER_ENG_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andPaperEngTitleEqualTo(String value) {
            addCriterion("PAPER_ENG_TITLE =", value, "paperEngTitle");
            return (Criteria) this;
        }

        public Criteria andPaperEngTitleNotEqualTo(String value) {
            addCriterion("PAPER_ENG_TITLE <>", value, "paperEngTitle");
            return (Criteria) this;
        }

        public Criteria andPaperEngTitleGreaterThan(String value) {
            addCriterion("PAPER_ENG_TITLE >", value, "paperEngTitle");
            return (Criteria) this;
        }

        public Criteria andPaperEngTitleGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER_ENG_TITLE >=", value, "paperEngTitle");
            return (Criteria) this;
        }

        public Criteria andPaperEngTitleLessThan(String value) {
            addCriterion("PAPER_ENG_TITLE <", value, "paperEngTitle");
            return (Criteria) this;
        }

        public Criteria andPaperEngTitleLessThanOrEqualTo(String value) {
            addCriterion("PAPER_ENG_TITLE <=", value, "paperEngTitle");
            return (Criteria) this;
        }

        public Criteria andPaperEngTitleLike(String value) {
            addCriterion("PAPER_ENG_TITLE like", value, "paperEngTitle");
            return (Criteria) this;
        }

        public Criteria andPaperEngTitleNotLike(String value) {
            addCriterion("PAPER_ENG_TITLE not like", value, "paperEngTitle");
            return (Criteria) this;
        }

        public Criteria andPaperEngTitleIn(List<String> values) {
            addCriterion("PAPER_ENG_TITLE in", values, "paperEngTitle");
            return (Criteria) this;
        }

        public Criteria andPaperEngTitleNotIn(List<String> values) {
            addCriterion("PAPER_ENG_TITLE not in", values, "paperEngTitle");
            return (Criteria) this;
        }

        public Criteria andPaperEngTitleBetween(String value1, String value2) {
            addCriterion("PAPER_ENG_TITLE between", value1, value2, "paperEngTitle");
            return (Criteria) this;
        }

        public Criteria andPaperEngTitleNotBetween(String value1, String value2) {
            addCriterion("PAPER_ENG_TITLE not between", value1, value2, "paperEngTitle");
            return (Criteria) this;
        }

        public Criteria andKeyWordIsNull() {
            addCriterion("KEY_WORD is null");
            return (Criteria) this;
        }

        public Criteria andKeyWordIsNotNull() {
            addCriterion("KEY_WORD is not null");
            return (Criteria) this;
        }

        public Criteria andKeyWordEqualTo(String value) {
            addCriterion("KEY_WORD =", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordNotEqualTo(String value) {
            addCriterion("KEY_WORD <>", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordGreaterThan(String value) {
            addCriterion("KEY_WORD >", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordGreaterThanOrEqualTo(String value) {
            addCriterion("KEY_WORD >=", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordLessThan(String value) {
            addCriterion("KEY_WORD <", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordLessThanOrEqualTo(String value) {
            addCriterion("KEY_WORD <=", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordLike(String value) {
            addCriterion("KEY_WORD like", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordNotLike(String value) {
            addCriterion("KEY_WORD not like", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordIn(List<String> values) {
            addCriterion("KEY_WORD in", values, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordNotIn(List<String> values) {
            addCriterion("KEY_WORD not in", values, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordBetween(String value1, String value2) {
            addCriterion("KEY_WORD between", value1, value2, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordNotBetween(String value1, String value2) {
            addCriterion("KEY_WORD not between", value1, value2, "keyWord");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionIsNull() {
            addCriterion("RESEARCH_DIRECTION is null");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionIsNotNull() {
            addCriterion("RESEARCH_DIRECTION is not null");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionEqualTo(String value) {
            addCriterion("RESEARCH_DIRECTION =", value, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionNotEqualTo(String value) {
            addCriterion("RESEARCH_DIRECTION <>", value, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionGreaterThan(String value) {
            addCriterion("RESEARCH_DIRECTION >", value, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionGreaterThanOrEqualTo(String value) {
            addCriterion("RESEARCH_DIRECTION >=", value, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionLessThan(String value) {
            addCriterion("RESEARCH_DIRECTION <", value, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionLessThanOrEqualTo(String value) {
            addCriterion("RESEARCH_DIRECTION <=", value, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionLike(String value) {
            addCriterion("RESEARCH_DIRECTION like", value, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionNotLike(String value) {
            addCriterion("RESEARCH_DIRECTION not like", value, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionIn(List<String> values) {
            addCriterion("RESEARCH_DIRECTION in", values, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionNotIn(List<String> values) {
            addCriterion("RESEARCH_DIRECTION not in", values, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionBetween(String value1, String value2) {
            addCriterion("RESEARCH_DIRECTION between", value1, value2, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionNotBetween(String value1, String value2) {
            addCriterion("RESEARCH_DIRECTION not between", value1, value2, "researchDirection");
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

        public Criteria andTutorAuditIdIsNull() {
            addCriterion("TUTOR_AUDIT_ID is null");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdIsNotNull() {
            addCriterion("TUTOR_AUDIT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_ID =", value, "tutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdNotEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_ID <>", value, "tutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdGreaterThan(String value) {
            addCriterion("TUTOR_AUDIT_ID >", value, "tutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_ID >=", value, "tutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdLessThan(String value) {
            addCriterion("TUTOR_AUDIT_ID <", value, "tutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_ID <=", value, "tutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdLike(String value) {
            addCriterion("TUTOR_AUDIT_ID like", value, "tutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdNotLike(String value) {
            addCriterion("TUTOR_AUDIT_ID not like", value, "tutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdIn(List<String> values) {
            addCriterion("TUTOR_AUDIT_ID in", values, "tutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdNotIn(List<String> values) {
            addCriterion("TUTOR_AUDIT_ID not in", values, "tutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdBetween(String value1, String value2) {
            addCriterion("TUTOR_AUDIT_ID between", value1, value2, "tutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdNotBetween(String value1, String value2) {
            addCriterion("TUTOR_AUDIT_ID not between", value1, value2, "tutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameIsNull() {
            addCriterion("TUTOR_AUDIT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameIsNotNull() {
            addCriterion("TUTOR_AUDIT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_NAME =", value, "tutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameNotEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_NAME <>", value, "tutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameGreaterThan(String value) {
            addCriterion("TUTOR_AUDIT_NAME >", value, "tutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_NAME >=", value, "tutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameLessThan(String value) {
            addCriterion("TUTOR_AUDIT_NAME <", value, "tutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_NAME <=", value, "tutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameLike(String value) {
            addCriterion("TUTOR_AUDIT_NAME like", value, "tutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameNotLike(String value) {
            addCriterion("TUTOR_AUDIT_NAME not like", value, "tutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameIn(List<String> values) {
            addCriterion("TUTOR_AUDIT_NAME in", values, "tutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameNotIn(List<String> values) {
            addCriterion("TUTOR_AUDIT_NAME not in", values, "tutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameBetween(String value1, String value2) {
            addCriterion("TUTOR_AUDIT_NAME between", value1, value2, "tutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameNotBetween(String value1, String value2) {
            addCriterion("TUTOR_AUDIT_NAME not between", value1, value2, "tutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTutorAuditAdviceIsNull() {
            addCriterion("TUTOR_AUDIT_ADVICE is null");
            return (Criteria) this;
        }

        public Criteria andTutorAuditAdviceIsNotNull() {
            addCriterion("TUTOR_AUDIT_ADVICE is not null");
            return (Criteria) this;
        }

        public Criteria andTutorAuditAdviceEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_ADVICE =", value, "tutorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andTutorAuditAdviceNotEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_ADVICE <>", value, "tutorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andTutorAuditAdviceGreaterThan(String value) {
            addCriterion("TUTOR_AUDIT_ADVICE >", value, "tutorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andTutorAuditAdviceGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_ADVICE >=", value, "tutorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andTutorAuditAdviceLessThan(String value) {
            addCriterion("TUTOR_AUDIT_ADVICE <", value, "tutorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andTutorAuditAdviceLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_ADVICE <=", value, "tutorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andTutorAuditAdviceLike(String value) {
            addCriterion("TUTOR_AUDIT_ADVICE like", value, "tutorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andTutorAuditAdviceNotLike(String value) {
            addCriterion("TUTOR_AUDIT_ADVICE not like", value, "tutorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andTutorAuditAdviceIn(List<String> values) {
            addCriterion("TUTOR_AUDIT_ADVICE in", values, "tutorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andTutorAuditAdviceNotIn(List<String> values) {
            addCriterion("TUTOR_AUDIT_ADVICE not in", values, "tutorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andTutorAuditAdviceBetween(String value1, String value2) {
            addCriterion("TUTOR_AUDIT_ADVICE between", value1, value2, "tutorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andTutorAuditAdviceNotBetween(String value1, String value2) {
            addCriterion("TUTOR_AUDIT_ADVICE not between", value1, value2, "tutorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditIdIsNull() {
            addCriterion("TWO_TUTOR_AUDIT_ID is null");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditIdIsNotNull() {
            addCriterion("TWO_TUTOR_AUDIT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditIdEqualTo(String value) {
            addCriterion("TWO_TUTOR_AUDIT_ID =", value, "twoTutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditIdNotEqualTo(String value) {
            addCriterion("TWO_TUTOR_AUDIT_ID <>", value, "twoTutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditIdGreaterThan(String value) {
            addCriterion("TWO_TUTOR_AUDIT_ID >", value, "twoTutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditIdGreaterThanOrEqualTo(String value) {
            addCriterion("TWO_TUTOR_AUDIT_ID >=", value, "twoTutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditIdLessThan(String value) {
            addCriterion("TWO_TUTOR_AUDIT_ID <", value, "twoTutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditIdLessThanOrEqualTo(String value) {
            addCriterion("TWO_TUTOR_AUDIT_ID <=", value, "twoTutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditIdLike(String value) {
            addCriterion("TWO_TUTOR_AUDIT_ID like", value, "twoTutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditIdNotLike(String value) {
            addCriterion("TWO_TUTOR_AUDIT_ID not like", value, "twoTutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditIdIn(List<String> values) {
            addCriterion("TWO_TUTOR_AUDIT_ID in", values, "twoTutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditIdNotIn(List<String> values) {
            addCriterion("TWO_TUTOR_AUDIT_ID not in", values, "twoTutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditIdBetween(String value1, String value2) {
            addCriterion("TWO_TUTOR_AUDIT_ID between", value1, value2, "twoTutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditIdNotBetween(String value1, String value2) {
            addCriterion("TWO_TUTOR_AUDIT_ID not between", value1, value2, "twoTutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditNameIsNull() {
            addCriterion("TWO_TUTOR_AUDIT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditNameIsNotNull() {
            addCriterion("TWO_TUTOR_AUDIT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditNameEqualTo(String value) {
            addCriterion("TWO_TUTOR_AUDIT_NAME =", value, "twoTutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditNameNotEqualTo(String value) {
            addCriterion("TWO_TUTOR_AUDIT_NAME <>", value, "twoTutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditNameGreaterThan(String value) {
            addCriterion("TWO_TUTOR_AUDIT_NAME >", value, "twoTutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditNameGreaterThanOrEqualTo(String value) {
            addCriterion("TWO_TUTOR_AUDIT_NAME >=", value, "twoTutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditNameLessThan(String value) {
            addCriterion("TWO_TUTOR_AUDIT_NAME <", value, "twoTutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditNameLessThanOrEqualTo(String value) {
            addCriterion("TWO_TUTOR_AUDIT_NAME <=", value, "twoTutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditNameLike(String value) {
            addCriterion("TWO_TUTOR_AUDIT_NAME like", value, "twoTutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditNameNotLike(String value) {
            addCriterion("TWO_TUTOR_AUDIT_NAME not like", value, "twoTutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditNameIn(List<String> values) {
            addCriterion("TWO_TUTOR_AUDIT_NAME in", values, "twoTutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditNameNotIn(List<String> values) {
            addCriterion("TWO_TUTOR_AUDIT_NAME not in", values, "twoTutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditNameBetween(String value1, String value2) {
            addCriterion("TWO_TUTOR_AUDIT_NAME between", value1, value2, "twoTutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditNameNotBetween(String value1, String value2) {
            addCriterion("TWO_TUTOR_AUDIT_NAME not between", value1, value2, "twoTutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditAdviceIsNull() {
            addCriterion("TWO_TUTOR_AUDIT_ADVICE is null");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditAdviceIsNotNull() {
            addCriterion("TWO_TUTOR_AUDIT_ADVICE is not null");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditAdviceEqualTo(String value) {
            addCriterion("TWO_TUTOR_AUDIT_ADVICE =", value, "twoTutorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditAdviceNotEqualTo(String value) {
            addCriterion("TWO_TUTOR_AUDIT_ADVICE <>", value, "twoTutorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditAdviceGreaterThan(String value) {
            addCriterion("TWO_TUTOR_AUDIT_ADVICE >", value, "twoTutorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditAdviceGreaterThanOrEqualTo(String value) {
            addCriterion("TWO_TUTOR_AUDIT_ADVICE >=", value, "twoTutorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditAdviceLessThan(String value) {
            addCriterion("TWO_TUTOR_AUDIT_ADVICE <", value, "twoTutorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditAdviceLessThanOrEqualTo(String value) {
            addCriterion("TWO_TUTOR_AUDIT_ADVICE <=", value, "twoTutorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditAdviceLike(String value) {
            addCriterion("TWO_TUTOR_AUDIT_ADVICE like", value, "twoTutorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditAdviceNotLike(String value) {
            addCriterion("TWO_TUTOR_AUDIT_ADVICE not like", value, "twoTutorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditAdviceIn(List<String> values) {
            addCriterion("TWO_TUTOR_AUDIT_ADVICE in", values, "twoTutorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditAdviceNotIn(List<String> values) {
            addCriterion("TWO_TUTOR_AUDIT_ADVICE not in", values, "twoTutorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditAdviceBetween(String value1, String value2) {
            addCriterion("TWO_TUTOR_AUDIT_ADVICE between", value1, value2, "twoTutorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andTwoTutorAuditAdviceNotBetween(String value1, String value2) {
            addCriterion("TWO_TUTOR_AUDIT_ADVICE not between", value1, value2, "twoTutorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditIdIsNull() {
            addCriterion("PYDW_AUDIT_ID is null");
            return (Criteria) this;
        }

        public Criteria andPydwAuditIdIsNotNull() {
            addCriterion("PYDW_AUDIT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPydwAuditIdEqualTo(String value) {
            addCriterion("PYDW_AUDIT_ID =", value, "pydwAuditId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditIdNotEqualTo(String value) {
            addCriterion("PYDW_AUDIT_ID <>", value, "pydwAuditId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditIdGreaterThan(String value) {
            addCriterion("PYDW_AUDIT_ID >", value, "pydwAuditId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditIdGreaterThanOrEqualTo(String value) {
            addCriterion("PYDW_AUDIT_ID >=", value, "pydwAuditId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditIdLessThan(String value) {
            addCriterion("PYDW_AUDIT_ID <", value, "pydwAuditId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditIdLessThanOrEqualTo(String value) {
            addCriterion("PYDW_AUDIT_ID <=", value, "pydwAuditId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditIdLike(String value) {
            addCriterion("PYDW_AUDIT_ID like", value, "pydwAuditId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditIdNotLike(String value) {
            addCriterion("PYDW_AUDIT_ID not like", value, "pydwAuditId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditIdIn(List<String> values) {
            addCriterion("PYDW_AUDIT_ID in", values, "pydwAuditId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditIdNotIn(List<String> values) {
            addCriterion("PYDW_AUDIT_ID not in", values, "pydwAuditId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditIdBetween(String value1, String value2) {
            addCriterion("PYDW_AUDIT_ID between", value1, value2, "pydwAuditId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditIdNotBetween(String value1, String value2) {
            addCriterion("PYDW_AUDIT_ID not between", value1, value2, "pydwAuditId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditNameIsNull() {
            addCriterion("PYDW_AUDIT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPydwAuditNameIsNotNull() {
            addCriterion("PYDW_AUDIT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPydwAuditNameEqualTo(String value) {
            addCriterion("PYDW_AUDIT_NAME =", value, "pydwAuditName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditNameNotEqualTo(String value) {
            addCriterion("PYDW_AUDIT_NAME <>", value, "pydwAuditName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditNameGreaterThan(String value) {
            addCriterion("PYDW_AUDIT_NAME >", value, "pydwAuditName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditNameGreaterThanOrEqualTo(String value) {
            addCriterion("PYDW_AUDIT_NAME >=", value, "pydwAuditName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditNameLessThan(String value) {
            addCriterion("PYDW_AUDIT_NAME <", value, "pydwAuditName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditNameLessThanOrEqualTo(String value) {
            addCriterion("PYDW_AUDIT_NAME <=", value, "pydwAuditName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditNameLike(String value) {
            addCriterion("PYDW_AUDIT_NAME like", value, "pydwAuditName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditNameNotLike(String value) {
            addCriterion("PYDW_AUDIT_NAME not like", value, "pydwAuditName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditNameIn(List<String> values) {
            addCriterion("PYDW_AUDIT_NAME in", values, "pydwAuditName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditNameNotIn(List<String> values) {
            addCriterion("PYDW_AUDIT_NAME not in", values, "pydwAuditName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditNameBetween(String value1, String value2) {
            addCriterion("PYDW_AUDIT_NAME between", value1, value2, "pydwAuditName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditNameNotBetween(String value1, String value2) {
            addCriterion("PYDW_AUDIT_NAME not between", value1, value2, "pydwAuditName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceIsNull() {
            addCriterion("PYDW_AUDIT_ADVICE is null");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceIsNotNull() {
            addCriterion("PYDW_AUDIT_ADVICE is not null");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceEqualTo(String value) {
            addCriterion("PYDW_AUDIT_ADVICE =", value, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceNotEqualTo(String value) {
            addCriterion("PYDW_AUDIT_ADVICE <>", value, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceGreaterThan(String value) {
            addCriterion("PYDW_AUDIT_ADVICE >", value, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceGreaterThanOrEqualTo(String value) {
            addCriterion("PYDW_AUDIT_ADVICE >=", value, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceLessThan(String value) {
            addCriterion("PYDW_AUDIT_ADVICE <", value, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceLessThanOrEqualTo(String value) {
            addCriterion("PYDW_AUDIT_ADVICE <=", value, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceLike(String value) {
            addCriterion("PYDW_AUDIT_ADVICE like", value, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceNotLike(String value) {
            addCriterion("PYDW_AUDIT_ADVICE not like", value, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceIn(List<String> values) {
            addCriterion("PYDW_AUDIT_ADVICE in", values, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceNotIn(List<String> values) {
            addCriterion("PYDW_AUDIT_ADVICE not in", values, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceBetween(String value1, String value2) {
            addCriterion("PYDW_AUDIT_ADVICE between", value1, value2, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceNotBetween(String value1, String value2) {
            addCriterion("PYDW_AUDIT_ADVICE not between", value1, value2, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPaperSameFlagIsNull() {
            addCriterion("PAPER_SAME_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andPaperSameFlagIsNotNull() {
            addCriterion("PAPER_SAME_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andPaperSameFlagEqualTo(String value) {
            addCriterion("PAPER_SAME_FLAG =", value, "paperSameFlag");
            return (Criteria) this;
        }

        public Criteria andPaperSameFlagNotEqualTo(String value) {
            addCriterion("PAPER_SAME_FLAG <>", value, "paperSameFlag");
            return (Criteria) this;
        }

        public Criteria andPaperSameFlagGreaterThan(String value) {
            addCriterion("PAPER_SAME_FLAG >", value, "paperSameFlag");
            return (Criteria) this;
        }

        public Criteria andPaperSameFlagGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER_SAME_FLAG >=", value, "paperSameFlag");
            return (Criteria) this;
        }

        public Criteria andPaperSameFlagLessThan(String value) {
            addCriterion("PAPER_SAME_FLAG <", value, "paperSameFlag");
            return (Criteria) this;
        }

        public Criteria andPaperSameFlagLessThanOrEqualTo(String value) {
            addCriterion("PAPER_SAME_FLAG <=", value, "paperSameFlag");
            return (Criteria) this;
        }

        public Criteria andPaperSameFlagLike(String value) {
            addCriterion("PAPER_SAME_FLAG like", value, "paperSameFlag");
            return (Criteria) this;
        }

        public Criteria andPaperSameFlagNotLike(String value) {
            addCriterion("PAPER_SAME_FLAG not like", value, "paperSameFlag");
            return (Criteria) this;
        }

        public Criteria andPaperSameFlagIn(List<String> values) {
            addCriterion("PAPER_SAME_FLAG in", values, "paperSameFlag");
            return (Criteria) this;
        }

        public Criteria andPaperSameFlagNotIn(List<String> values) {
            addCriterion("PAPER_SAME_FLAG not in", values, "paperSameFlag");
            return (Criteria) this;
        }

        public Criteria andPaperSameFlagBetween(String value1, String value2) {
            addCriterion("PAPER_SAME_FLAG between", value1, value2, "paperSameFlag");
            return (Criteria) this;
        }

        public Criteria andPaperSameFlagNotBetween(String value1, String value2) {
            addCriterion("PAPER_SAME_FLAG not between", value1, value2, "paperSameFlag");
            return (Criteria) this;
        }

        public Criteria andThinkSameFlagIsNull() {
            addCriterion("THINK_SAME_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andThinkSameFlagIsNotNull() {
            addCriterion("THINK_SAME_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andThinkSameFlagEqualTo(String value) {
            addCriterion("THINK_SAME_FLAG =", value, "thinkSameFlag");
            return (Criteria) this;
        }

        public Criteria andThinkSameFlagNotEqualTo(String value) {
            addCriterion("THINK_SAME_FLAG <>", value, "thinkSameFlag");
            return (Criteria) this;
        }

        public Criteria andThinkSameFlagGreaterThan(String value) {
            addCriterion("THINK_SAME_FLAG >", value, "thinkSameFlag");
            return (Criteria) this;
        }

        public Criteria andThinkSameFlagGreaterThanOrEqualTo(String value) {
            addCriterion("THINK_SAME_FLAG >=", value, "thinkSameFlag");
            return (Criteria) this;
        }

        public Criteria andThinkSameFlagLessThan(String value) {
            addCriterion("THINK_SAME_FLAG <", value, "thinkSameFlag");
            return (Criteria) this;
        }

        public Criteria andThinkSameFlagLessThanOrEqualTo(String value) {
            addCriterion("THINK_SAME_FLAG <=", value, "thinkSameFlag");
            return (Criteria) this;
        }

        public Criteria andThinkSameFlagLike(String value) {
            addCriterion("THINK_SAME_FLAG like", value, "thinkSameFlag");
            return (Criteria) this;
        }

        public Criteria andThinkSameFlagNotLike(String value) {
            addCriterion("THINK_SAME_FLAG not like", value, "thinkSameFlag");
            return (Criteria) this;
        }

        public Criteria andThinkSameFlagIn(List<String> values) {
            addCriterion("THINK_SAME_FLAG in", values, "thinkSameFlag");
            return (Criteria) this;
        }

        public Criteria andThinkSameFlagNotIn(List<String> values) {
            addCriterion("THINK_SAME_FLAG not in", values, "thinkSameFlag");
            return (Criteria) this;
        }

        public Criteria andThinkSameFlagBetween(String value1, String value2) {
            addCriterion("THINK_SAME_FLAG between", value1, value2, "thinkSameFlag");
            return (Criteria) this;
        }

        public Criteria andThinkSameFlagNotBetween(String value1, String value2) {
            addCriterion("THINK_SAME_FLAG not between", value1, value2, "thinkSameFlag");
            return (Criteria) this;
        }

        public Criteria andPreDefenceFlagIsNull() {
            addCriterion("PRE_DEFENCE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andPreDefenceFlagIsNotNull() {
            addCriterion("PRE_DEFENCE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andPreDefenceFlagEqualTo(String value) {
            addCriterion("PRE_DEFENCE_FLAG =", value, "preDefenceFlag");
            return (Criteria) this;
        }

        public Criteria andPreDefenceFlagNotEqualTo(String value) {
            addCriterion("PRE_DEFENCE_FLAG <>", value, "preDefenceFlag");
            return (Criteria) this;
        }

        public Criteria andPreDefenceFlagGreaterThan(String value) {
            addCriterion("PRE_DEFENCE_FLAG >", value, "preDefenceFlag");
            return (Criteria) this;
        }

        public Criteria andPreDefenceFlagGreaterThanOrEqualTo(String value) {
            addCriterion("PRE_DEFENCE_FLAG >=", value, "preDefenceFlag");
            return (Criteria) this;
        }

        public Criteria andPreDefenceFlagLessThan(String value) {
            addCriterion("PRE_DEFENCE_FLAG <", value, "preDefenceFlag");
            return (Criteria) this;
        }

        public Criteria andPreDefenceFlagLessThanOrEqualTo(String value) {
            addCriterion("PRE_DEFENCE_FLAG <=", value, "preDefenceFlag");
            return (Criteria) this;
        }

        public Criteria andPreDefenceFlagLike(String value) {
            addCriterion("PRE_DEFENCE_FLAG like", value, "preDefenceFlag");
            return (Criteria) this;
        }

        public Criteria andPreDefenceFlagNotLike(String value) {
            addCriterion("PRE_DEFENCE_FLAG not like", value, "preDefenceFlag");
            return (Criteria) this;
        }

        public Criteria andPreDefenceFlagIn(List<String> values) {
            addCriterion("PRE_DEFENCE_FLAG in", values, "preDefenceFlag");
            return (Criteria) this;
        }

        public Criteria andPreDefenceFlagNotIn(List<String> values) {
            addCriterion("PRE_DEFENCE_FLAG not in", values, "preDefenceFlag");
            return (Criteria) this;
        }

        public Criteria andPreDefenceFlagBetween(String value1, String value2) {
            addCriterion("PRE_DEFENCE_FLAG between", value1, value2, "preDefenceFlag");
            return (Criteria) this;
        }

        public Criteria andPreDefenceFlagNotBetween(String value1, String value2) {
            addCriterion("PRE_DEFENCE_FLAG not between", value1, value2, "preDefenceFlag");
            return (Criteria) this;
        }

        public Criteria andPaperReadFlagIsNull() {
            addCriterion("PAPER_READ_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andPaperReadFlagIsNotNull() {
            addCriterion("PAPER_READ_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andPaperReadFlagEqualTo(String value) {
            addCriterion("PAPER_READ_FLAG =", value, "paperReadFlag");
            return (Criteria) this;
        }

        public Criteria andPaperReadFlagNotEqualTo(String value) {
            addCriterion("PAPER_READ_FLAG <>", value, "paperReadFlag");
            return (Criteria) this;
        }

        public Criteria andPaperReadFlagGreaterThan(String value) {
            addCriterion("PAPER_READ_FLAG >", value, "paperReadFlag");
            return (Criteria) this;
        }

        public Criteria andPaperReadFlagGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER_READ_FLAG >=", value, "paperReadFlag");
            return (Criteria) this;
        }

        public Criteria andPaperReadFlagLessThan(String value) {
            addCriterion("PAPER_READ_FLAG <", value, "paperReadFlag");
            return (Criteria) this;
        }

        public Criteria andPaperReadFlagLessThanOrEqualTo(String value) {
            addCriterion("PAPER_READ_FLAG <=", value, "paperReadFlag");
            return (Criteria) this;
        }

        public Criteria andPaperReadFlagLike(String value) {
            addCriterion("PAPER_READ_FLAG like", value, "paperReadFlag");
            return (Criteria) this;
        }

        public Criteria andPaperReadFlagNotLike(String value) {
            addCriterion("PAPER_READ_FLAG not like", value, "paperReadFlag");
            return (Criteria) this;
        }

        public Criteria andPaperReadFlagIn(List<String> values) {
            addCriterion("PAPER_READ_FLAG in", values, "paperReadFlag");
            return (Criteria) this;
        }

        public Criteria andPaperReadFlagNotIn(List<String> values) {
            addCriterion("PAPER_READ_FLAG not in", values, "paperReadFlag");
            return (Criteria) this;
        }

        public Criteria andPaperReadFlagBetween(String value1, String value2) {
            addCriterion("PAPER_READ_FLAG between", value1, value2, "paperReadFlag");
            return (Criteria) this;
        }

        public Criteria andPaperReadFlagNotBetween(String value1, String value2) {
            addCriterion("PAPER_READ_FLAG not between", value1, value2, "paperReadFlag");
            return (Criteria) this;
        }

        public Criteria andFirstAuditFlagIsNull() {
            addCriterion("FIRST_AUDIT_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andFirstAuditFlagIsNotNull() {
            addCriterion("FIRST_AUDIT_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andFirstAuditFlagEqualTo(String value) {
            addCriterion("FIRST_AUDIT_FLAG =", value, "firstAuditFlag");
            return (Criteria) this;
        }

        public Criteria andFirstAuditFlagNotEqualTo(String value) {
            addCriterion("FIRST_AUDIT_FLAG <>", value, "firstAuditFlag");
            return (Criteria) this;
        }

        public Criteria andFirstAuditFlagGreaterThan(String value) {
            addCriterion("FIRST_AUDIT_FLAG >", value, "firstAuditFlag");
            return (Criteria) this;
        }

        public Criteria andFirstAuditFlagGreaterThanOrEqualTo(String value) {
            addCriterion("FIRST_AUDIT_FLAG >=", value, "firstAuditFlag");
            return (Criteria) this;
        }

        public Criteria andFirstAuditFlagLessThan(String value) {
            addCriterion("FIRST_AUDIT_FLAG <", value, "firstAuditFlag");
            return (Criteria) this;
        }

        public Criteria andFirstAuditFlagLessThanOrEqualTo(String value) {
            addCriterion("FIRST_AUDIT_FLAG <=", value, "firstAuditFlag");
            return (Criteria) this;
        }

        public Criteria andFirstAuditFlagLike(String value) {
            addCriterion("FIRST_AUDIT_FLAG like", value, "firstAuditFlag");
            return (Criteria) this;
        }

        public Criteria andFirstAuditFlagNotLike(String value) {
            addCriterion("FIRST_AUDIT_FLAG not like", value, "firstAuditFlag");
            return (Criteria) this;
        }

        public Criteria andFirstAuditFlagIn(List<String> values) {
            addCriterion("FIRST_AUDIT_FLAG in", values, "firstAuditFlag");
            return (Criteria) this;
        }

        public Criteria andFirstAuditFlagNotIn(List<String> values) {
            addCriterion("FIRST_AUDIT_FLAG not in", values, "firstAuditFlag");
            return (Criteria) this;
        }

        public Criteria andFirstAuditFlagBetween(String value1, String value2) {
            addCriterion("FIRST_AUDIT_FLAG between", value1, value2, "firstAuditFlag");
            return (Criteria) this;
        }

        public Criteria andFirstAuditFlagNotBetween(String value1, String value2) {
            addCriterion("FIRST_AUDIT_FLAG not between", value1, value2, "firstAuditFlag");
            return (Criteria) this;
        }

        public Criteria andLastAuditFlagIsNull() {
            addCriterion("LAST_AUDIT_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andLastAuditFlagIsNotNull() {
            addCriterion("LAST_AUDIT_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andLastAuditFlagEqualTo(String value) {
            addCriterion("LAST_AUDIT_FLAG =", value, "lastAuditFlag");
            return (Criteria) this;
        }

        public Criteria andLastAuditFlagNotEqualTo(String value) {
            addCriterion("LAST_AUDIT_FLAG <>", value, "lastAuditFlag");
            return (Criteria) this;
        }

        public Criteria andLastAuditFlagGreaterThan(String value) {
            addCriterion("LAST_AUDIT_FLAG >", value, "lastAuditFlag");
            return (Criteria) this;
        }

        public Criteria andLastAuditFlagGreaterThanOrEqualTo(String value) {
            addCriterion("LAST_AUDIT_FLAG >=", value, "lastAuditFlag");
            return (Criteria) this;
        }

        public Criteria andLastAuditFlagLessThan(String value) {
            addCriterion("LAST_AUDIT_FLAG <", value, "lastAuditFlag");
            return (Criteria) this;
        }

        public Criteria andLastAuditFlagLessThanOrEqualTo(String value) {
            addCriterion("LAST_AUDIT_FLAG <=", value, "lastAuditFlag");
            return (Criteria) this;
        }

        public Criteria andLastAuditFlagLike(String value) {
            addCriterion("LAST_AUDIT_FLAG like", value, "lastAuditFlag");
            return (Criteria) this;
        }

        public Criteria andLastAuditFlagNotLike(String value) {
            addCriterion("LAST_AUDIT_FLAG not like", value, "lastAuditFlag");
            return (Criteria) this;
        }

        public Criteria andLastAuditFlagIn(List<String> values) {
            addCriterion("LAST_AUDIT_FLAG in", values, "lastAuditFlag");
            return (Criteria) this;
        }

        public Criteria andLastAuditFlagNotIn(List<String> values) {
            addCriterion("LAST_AUDIT_FLAG not in", values, "lastAuditFlag");
            return (Criteria) this;
        }

        public Criteria andLastAuditFlagBetween(String value1, String value2) {
            addCriterion("LAST_AUDIT_FLAG between", value1, value2, "lastAuditFlag");
            return (Criteria) this;
        }

        public Criteria andLastAuditFlagNotBetween(String value1, String value2) {
            addCriterion("LAST_AUDIT_FLAG not between", value1, value2, "lastAuditFlag");
            return (Criteria) this;
        }

        public Criteria andAuditResultFlagIsNull() {
            addCriterion("AUDIT_RESULT_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andAuditResultFlagIsNotNull() {
            addCriterion("AUDIT_RESULT_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andAuditResultFlagEqualTo(String value) {
            addCriterion("AUDIT_RESULT_FLAG =", value, "auditResultFlag");
            return (Criteria) this;
        }

        public Criteria andAuditResultFlagNotEqualTo(String value) {
            addCriterion("AUDIT_RESULT_FLAG <>", value, "auditResultFlag");
            return (Criteria) this;
        }

        public Criteria andAuditResultFlagGreaterThan(String value) {
            addCriterion("AUDIT_RESULT_FLAG >", value, "auditResultFlag");
            return (Criteria) this;
        }

        public Criteria andAuditResultFlagGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_RESULT_FLAG >=", value, "auditResultFlag");
            return (Criteria) this;
        }

        public Criteria andAuditResultFlagLessThan(String value) {
            addCriterion("AUDIT_RESULT_FLAG <", value, "auditResultFlag");
            return (Criteria) this;
        }

        public Criteria andAuditResultFlagLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_RESULT_FLAG <=", value, "auditResultFlag");
            return (Criteria) this;
        }

        public Criteria andAuditResultFlagLike(String value) {
            addCriterion("AUDIT_RESULT_FLAG like", value, "auditResultFlag");
            return (Criteria) this;
        }

        public Criteria andAuditResultFlagNotLike(String value) {
            addCriterion("AUDIT_RESULT_FLAG not like", value, "auditResultFlag");
            return (Criteria) this;
        }

        public Criteria andAuditResultFlagIn(List<String> values) {
            addCriterion("AUDIT_RESULT_FLAG in", values, "auditResultFlag");
            return (Criteria) this;
        }

        public Criteria andAuditResultFlagNotIn(List<String> values) {
            addCriterion("AUDIT_RESULT_FLAG not in", values, "auditResultFlag");
            return (Criteria) this;
        }

        public Criteria andAuditResultFlagBetween(String value1, String value2) {
            addCriterion("AUDIT_RESULT_FLAG between", value1, value2, "auditResultFlag");
            return (Criteria) this;
        }

        public Criteria andAuditResultFlagNotBetween(String value1, String value2) {
            addCriterion("AUDIT_RESULT_FLAG not between", value1, value2, "auditResultFlag");
            return (Criteria) this;
        }

        public Criteria andOtherFlagIsNull() {
            addCriterion("OTHER_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andOtherFlagIsNotNull() {
            addCriterion("OTHER_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andOtherFlagEqualTo(String value) {
            addCriterion("OTHER_FLAG =", value, "otherFlag");
            return (Criteria) this;
        }

        public Criteria andOtherFlagNotEqualTo(String value) {
            addCriterion("OTHER_FLAG <>", value, "otherFlag");
            return (Criteria) this;
        }

        public Criteria andOtherFlagGreaterThan(String value) {
            addCriterion("OTHER_FLAG >", value, "otherFlag");
            return (Criteria) this;
        }

        public Criteria andOtherFlagGreaterThanOrEqualTo(String value) {
            addCriterion("OTHER_FLAG >=", value, "otherFlag");
            return (Criteria) this;
        }

        public Criteria andOtherFlagLessThan(String value) {
            addCriterion("OTHER_FLAG <", value, "otherFlag");
            return (Criteria) this;
        }

        public Criteria andOtherFlagLessThanOrEqualTo(String value) {
            addCriterion("OTHER_FLAG <=", value, "otherFlag");
            return (Criteria) this;
        }

        public Criteria andOtherFlagLike(String value) {
            addCriterion("OTHER_FLAG like", value, "otherFlag");
            return (Criteria) this;
        }

        public Criteria andOtherFlagNotLike(String value) {
            addCriterion("OTHER_FLAG not like", value, "otherFlag");
            return (Criteria) this;
        }

        public Criteria andOtherFlagIn(List<String> values) {
            addCriterion("OTHER_FLAG in", values, "otherFlag");
            return (Criteria) this;
        }

        public Criteria andOtherFlagNotIn(List<String> values) {
            addCriterion("OTHER_FLAG not in", values, "otherFlag");
            return (Criteria) this;
        }

        public Criteria andOtherFlagBetween(String value1, String value2) {
            addCriterion("OTHER_FLAG between", value1, value2, "otherFlag");
            return (Criteria) this;
        }

        public Criteria andOtherFlagNotBetween(String value1, String value2) {
            addCriterion("OTHER_FLAG not between", value1, value2, "otherFlag");
            return (Criteria) this;
        }

        public Criteria andFormalDefenceFlagIsNull() {
            addCriterion("FORMAL_DEFENCE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andFormalDefenceFlagIsNotNull() {
            addCriterion("FORMAL_DEFENCE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andFormalDefenceFlagEqualTo(String value) {
            addCriterion("FORMAL_DEFENCE_FLAG =", value, "formalDefenceFlag");
            return (Criteria) this;
        }

        public Criteria andFormalDefenceFlagNotEqualTo(String value) {
            addCriterion("FORMAL_DEFENCE_FLAG <>", value, "formalDefenceFlag");
            return (Criteria) this;
        }

        public Criteria andFormalDefenceFlagGreaterThan(String value) {
            addCriterion("FORMAL_DEFENCE_FLAG >", value, "formalDefenceFlag");
            return (Criteria) this;
        }

        public Criteria andFormalDefenceFlagGreaterThanOrEqualTo(String value) {
            addCriterion("FORMAL_DEFENCE_FLAG >=", value, "formalDefenceFlag");
            return (Criteria) this;
        }

        public Criteria andFormalDefenceFlagLessThan(String value) {
            addCriterion("FORMAL_DEFENCE_FLAG <", value, "formalDefenceFlag");
            return (Criteria) this;
        }

        public Criteria andFormalDefenceFlagLessThanOrEqualTo(String value) {
            addCriterion("FORMAL_DEFENCE_FLAG <=", value, "formalDefenceFlag");
            return (Criteria) this;
        }

        public Criteria andFormalDefenceFlagLike(String value) {
            addCriterion("FORMAL_DEFENCE_FLAG like", value, "formalDefenceFlag");
            return (Criteria) this;
        }

        public Criteria andFormalDefenceFlagNotLike(String value) {
            addCriterion("FORMAL_DEFENCE_FLAG not like", value, "formalDefenceFlag");
            return (Criteria) this;
        }

        public Criteria andFormalDefenceFlagIn(List<String> values) {
            addCriterion("FORMAL_DEFENCE_FLAG in", values, "formalDefenceFlag");
            return (Criteria) this;
        }

        public Criteria andFormalDefenceFlagNotIn(List<String> values) {
            addCriterion("FORMAL_DEFENCE_FLAG not in", values, "formalDefenceFlag");
            return (Criteria) this;
        }

        public Criteria andFormalDefenceFlagBetween(String value1, String value2) {
            addCriterion("FORMAL_DEFENCE_FLAG between", value1, value2, "formalDefenceFlag");
            return (Criteria) this;
        }

        public Criteria andFormalDefenceFlagNotBetween(String value1, String value2) {
            addCriterion("FORMAL_DEFENCE_FLAG not between", value1, value2, "formalDefenceFlag");
            return (Criteria) this;
        }

        public Criteria andDefenceBeginTimeIsNull() {
            addCriterion("DEFENCE_BEGIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andDefenceBeginTimeIsNotNull() {
            addCriterion("DEFENCE_BEGIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andDefenceBeginTimeEqualTo(String value) {
            addCriterion("DEFENCE_BEGIN_TIME =", value, "defenceBeginTime");
            return (Criteria) this;
        }

        public Criteria andDefenceBeginTimeNotEqualTo(String value) {
            addCriterion("DEFENCE_BEGIN_TIME <>", value, "defenceBeginTime");
            return (Criteria) this;
        }

        public Criteria andDefenceBeginTimeGreaterThan(String value) {
            addCriterion("DEFENCE_BEGIN_TIME >", value, "defenceBeginTime");
            return (Criteria) this;
        }

        public Criteria andDefenceBeginTimeGreaterThanOrEqualTo(String value) {
            addCriterion("DEFENCE_BEGIN_TIME >=", value, "defenceBeginTime");
            return (Criteria) this;
        }

        public Criteria andDefenceBeginTimeLessThan(String value) {
            addCriterion("DEFENCE_BEGIN_TIME <", value, "defenceBeginTime");
            return (Criteria) this;
        }

        public Criteria andDefenceBeginTimeLessThanOrEqualTo(String value) {
            addCriterion("DEFENCE_BEGIN_TIME <=", value, "defenceBeginTime");
            return (Criteria) this;
        }

        public Criteria andDefenceBeginTimeLike(String value) {
            addCriterion("DEFENCE_BEGIN_TIME like", value, "defenceBeginTime");
            return (Criteria) this;
        }

        public Criteria andDefenceBeginTimeNotLike(String value) {
            addCriterion("DEFENCE_BEGIN_TIME not like", value, "defenceBeginTime");
            return (Criteria) this;
        }

        public Criteria andDefenceBeginTimeIn(List<String> values) {
            addCriterion("DEFENCE_BEGIN_TIME in", values, "defenceBeginTime");
            return (Criteria) this;
        }

        public Criteria andDefenceBeginTimeNotIn(List<String> values) {
            addCriterion("DEFENCE_BEGIN_TIME not in", values, "defenceBeginTime");
            return (Criteria) this;
        }

        public Criteria andDefenceBeginTimeBetween(String value1, String value2) {
            addCriterion("DEFENCE_BEGIN_TIME between", value1, value2, "defenceBeginTime");
            return (Criteria) this;
        }

        public Criteria andDefenceBeginTimeNotBetween(String value1, String value2) {
            addCriterion("DEFENCE_BEGIN_TIME not between", value1, value2, "defenceBeginTime");
            return (Criteria) this;
        }

        public Criteria andDefenceEndTimeIsNull() {
            addCriterion("DEFENCE_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andDefenceEndTimeIsNotNull() {
            addCriterion("DEFENCE_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andDefenceEndTimeEqualTo(String value) {
            addCriterion("DEFENCE_END_TIME =", value, "defenceEndTime");
            return (Criteria) this;
        }

        public Criteria andDefenceEndTimeNotEqualTo(String value) {
            addCriterion("DEFENCE_END_TIME <>", value, "defenceEndTime");
            return (Criteria) this;
        }

        public Criteria andDefenceEndTimeGreaterThan(String value) {
            addCriterion("DEFENCE_END_TIME >", value, "defenceEndTime");
            return (Criteria) this;
        }

        public Criteria andDefenceEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("DEFENCE_END_TIME >=", value, "defenceEndTime");
            return (Criteria) this;
        }

        public Criteria andDefenceEndTimeLessThan(String value) {
            addCriterion("DEFENCE_END_TIME <", value, "defenceEndTime");
            return (Criteria) this;
        }

        public Criteria andDefenceEndTimeLessThanOrEqualTo(String value) {
            addCriterion("DEFENCE_END_TIME <=", value, "defenceEndTime");
            return (Criteria) this;
        }

        public Criteria andDefenceEndTimeLike(String value) {
            addCriterion("DEFENCE_END_TIME like", value, "defenceEndTime");
            return (Criteria) this;
        }

        public Criteria andDefenceEndTimeNotLike(String value) {
            addCriterion("DEFENCE_END_TIME not like", value, "defenceEndTime");
            return (Criteria) this;
        }

        public Criteria andDefenceEndTimeIn(List<String> values) {
            addCriterion("DEFENCE_END_TIME in", values, "defenceEndTime");
            return (Criteria) this;
        }

        public Criteria andDefenceEndTimeNotIn(List<String> values) {
            addCriterion("DEFENCE_END_TIME not in", values, "defenceEndTime");
            return (Criteria) this;
        }

        public Criteria andDefenceEndTimeBetween(String value1, String value2) {
            addCriterion("DEFENCE_END_TIME between", value1, value2, "defenceEndTime");
            return (Criteria) this;
        }

        public Criteria andDefenceEndTimeNotBetween(String value1, String value2) {
            addCriterion("DEFENCE_END_TIME not between", value1, value2, "defenceEndTime");
            return (Criteria) this;
        }

        public Criteria andDefencePlaceIsNull() {
            addCriterion("DEFENCE_PLACE is null");
            return (Criteria) this;
        }

        public Criteria andDefencePlaceIsNotNull() {
            addCriterion("DEFENCE_PLACE is not null");
            return (Criteria) this;
        }

        public Criteria andDefencePlaceEqualTo(String value) {
            addCriterion("DEFENCE_PLACE =", value, "defencePlace");
            return (Criteria) this;
        }

        public Criteria andDefencePlaceNotEqualTo(String value) {
            addCriterion("DEFENCE_PLACE <>", value, "defencePlace");
            return (Criteria) this;
        }

        public Criteria andDefencePlaceGreaterThan(String value) {
            addCriterion("DEFENCE_PLACE >", value, "defencePlace");
            return (Criteria) this;
        }

        public Criteria andDefencePlaceGreaterThanOrEqualTo(String value) {
            addCriterion("DEFENCE_PLACE >=", value, "defencePlace");
            return (Criteria) this;
        }

        public Criteria andDefencePlaceLessThan(String value) {
            addCriterion("DEFENCE_PLACE <", value, "defencePlace");
            return (Criteria) this;
        }

        public Criteria andDefencePlaceLessThanOrEqualTo(String value) {
            addCriterion("DEFENCE_PLACE <=", value, "defencePlace");
            return (Criteria) this;
        }

        public Criteria andDefencePlaceLike(String value) {
            addCriterion("DEFENCE_PLACE like", value, "defencePlace");
            return (Criteria) this;
        }

        public Criteria andDefencePlaceNotLike(String value) {
            addCriterion("DEFENCE_PLACE not like", value, "defencePlace");
            return (Criteria) this;
        }

        public Criteria andDefencePlaceIn(List<String> values) {
            addCriterion("DEFENCE_PLACE in", values, "defencePlace");
            return (Criteria) this;
        }

        public Criteria andDefencePlaceNotIn(List<String> values) {
            addCriterion("DEFENCE_PLACE not in", values, "defencePlace");
            return (Criteria) this;
        }

        public Criteria andDefencePlaceBetween(String value1, String value2) {
            addCriterion("DEFENCE_PLACE between", value1, value2, "defencePlace");
            return (Criteria) this;
        }

        public Criteria andDefencePlaceNotBetween(String value1, String value2) {
            addCriterion("DEFENCE_PLACE not between", value1, value2, "defencePlace");
            return (Criteria) this;
        }

        public Criteria andChairmanNameIsNull() {
            addCriterion("CHAIRMAN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andChairmanNameIsNotNull() {
            addCriterion("CHAIRMAN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andChairmanNameEqualTo(String value) {
            addCriterion("CHAIRMAN_NAME =", value, "chairmanName");
            return (Criteria) this;
        }

        public Criteria andChairmanNameNotEqualTo(String value) {
            addCriterion("CHAIRMAN_NAME <>", value, "chairmanName");
            return (Criteria) this;
        }

        public Criteria andChairmanNameGreaterThan(String value) {
            addCriterion("CHAIRMAN_NAME >", value, "chairmanName");
            return (Criteria) this;
        }

        public Criteria andChairmanNameGreaterThanOrEqualTo(String value) {
            addCriterion("CHAIRMAN_NAME >=", value, "chairmanName");
            return (Criteria) this;
        }

        public Criteria andChairmanNameLessThan(String value) {
            addCriterion("CHAIRMAN_NAME <", value, "chairmanName");
            return (Criteria) this;
        }

        public Criteria andChairmanNameLessThanOrEqualTo(String value) {
            addCriterion("CHAIRMAN_NAME <=", value, "chairmanName");
            return (Criteria) this;
        }

        public Criteria andChairmanNameLike(String value) {
            addCriterion("CHAIRMAN_NAME like", value, "chairmanName");
            return (Criteria) this;
        }

        public Criteria andChairmanNameNotLike(String value) {
            addCriterion("CHAIRMAN_NAME not like", value, "chairmanName");
            return (Criteria) this;
        }

        public Criteria andChairmanNameIn(List<String> values) {
            addCriterion("CHAIRMAN_NAME in", values, "chairmanName");
            return (Criteria) this;
        }

        public Criteria andChairmanNameNotIn(List<String> values) {
            addCriterion("CHAIRMAN_NAME not in", values, "chairmanName");
            return (Criteria) this;
        }

        public Criteria andChairmanNameBetween(String value1, String value2) {
            addCriterion("CHAIRMAN_NAME between", value1, value2, "chairmanName");
            return (Criteria) this;
        }

        public Criteria andChairmanNameNotBetween(String value1, String value2) {
            addCriterion("CHAIRMAN_NAME not between", value1, value2, "chairmanName");
            return (Criteria) this;
        }

        public Criteria andChairmanPostIsNull() {
            addCriterion("CHAIRMAN_POST is null");
            return (Criteria) this;
        }

        public Criteria andChairmanPostIsNotNull() {
            addCriterion("CHAIRMAN_POST is not null");
            return (Criteria) this;
        }

        public Criteria andChairmanPostEqualTo(String value) {
            addCriterion("CHAIRMAN_POST =", value, "chairmanPost");
            return (Criteria) this;
        }

        public Criteria andChairmanPostNotEqualTo(String value) {
            addCriterion("CHAIRMAN_POST <>", value, "chairmanPost");
            return (Criteria) this;
        }

        public Criteria andChairmanPostGreaterThan(String value) {
            addCriterion("CHAIRMAN_POST >", value, "chairmanPost");
            return (Criteria) this;
        }

        public Criteria andChairmanPostGreaterThanOrEqualTo(String value) {
            addCriterion("CHAIRMAN_POST >=", value, "chairmanPost");
            return (Criteria) this;
        }

        public Criteria andChairmanPostLessThan(String value) {
            addCriterion("CHAIRMAN_POST <", value, "chairmanPost");
            return (Criteria) this;
        }

        public Criteria andChairmanPostLessThanOrEqualTo(String value) {
            addCriterion("CHAIRMAN_POST <=", value, "chairmanPost");
            return (Criteria) this;
        }

        public Criteria andChairmanPostLike(String value) {
            addCriterion("CHAIRMAN_POST like", value, "chairmanPost");
            return (Criteria) this;
        }

        public Criteria andChairmanPostNotLike(String value) {
            addCriterion("CHAIRMAN_POST not like", value, "chairmanPost");
            return (Criteria) this;
        }

        public Criteria andChairmanPostIn(List<String> values) {
            addCriterion("CHAIRMAN_POST in", values, "chairmanPost");
            return (Criteria) this;
        }

        public Criteria andChairmanPostNotIn(List<String> values) {
            addCriterion("CHAIRMAN_POST not in", values, "chairmanPost");
            return (Criteria) this;
        }

        public Criteria andChairmanPostBetween(String value1, String value2) {
            addCriterion("CHAIRMAN_POST between", value1, value2, "chairmanPost");
            return (Criteria) this;
        }

        public Criteria andChairmanPostNotBetween(String value1, String value2) {
            addCriterion("CHAIRMAN_POST not between", value1, value2, "chairmanPost");
            return (Criteria) this;
        }

        public Criteria andScheduleFlagIsNull() {
            addCriterion("SCHEDULE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andScheduleFlagIsNotNull() {
            addCriterion("SCHEDULE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andScheduleFlagEqualTo(String value) {
            addCriterion("SCHEDULE_FLAG =", value, "scheduleFlag");
            return (Criteria) this;
        }

        public Criteria andScheduleFlagNotEqualTo(String value) {
            addCriterion("SCHEDULE_FLAG <>", value, "scheduleFlag");
            return (Criteria) this;
        }

        public Criteria andScheduleFlagGreaterThan(String value) {
            addCriterion("SCHEDULE_FLAG >", value, "scheduleFlag");
            return (Criteria) this;
        }

        public Criteria andScheduleFlagGreaterThanOrEqualTo(String value) {
            addCriterion("SCHEDULE_FLAG >=", value, "scheduleFlag");
            return (Criteria) this;
        }

        public Criteria andScheduleFlagLessThan(String value) {
            addCriterion("SCHEDULE_FLAG <", value, "scheduleFlag");
            return (Criteria) this;
        }

        public Criteria andScheduleFlagLessThanOrEqualTo(String value) {
            addCriterion("SCHEDULE_FLAG <=", value, "scheduleFlag");
            return (Criteria) this;
        }

        public Criteria andScheduleFlagLike(String value) {
            addCriterion("SCHEDULE_FLAG like", value, "scheduleFlag");
            return (Criteria) this;
        }

        public Criteria andScheduleFlagNotLike(String value) {
            addCriterion("SCHEDULE_FLAG not like", value, "scheduleFlag");
            return (Criteria) this;
        }

        public Criteria andScheduleFlagIn(List<String> values) {
            addCriterion("SCHEDULE_FLAG in", values, "scheduleFlag");
            return (Criteria) this;
        }

        public Criteria andScheduleFlagNotIn(List<String> values) {
            addCriterion("SCHEDULE_FLAG not in", values, "scheduleFlag");
            return (Criteria) this;
        }

        public Criteria andScheduleFlagBetween(String value1, String value2) {
            addCriterion("SCHEDULE_FLAG between", value1, value2, "scheduleFlag");
            return (Criteria) this;
        }

        public Criteria andScheduleFlagNotBetween(String value1, String value2) {
            addCriterion("SCHEDULE_FLAG not between", value1, value2, "scheduleFlag");
            return (Criteria) this;
        }

        public Criteria andDefenceResultFlagIsNull() {
            addCriterion("DEFENCE_RESULT_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andDefenceResultFlagIsNotNull() {
            addCriterion("DEFENCE_RESULT_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andDefenceResultFlagEqualTo(String value) {
            addCriterion("DEFENCE_RESULT_FLAG =", value, "defenceResultFlag");
            return (Criteria) this;
        }

        public Criteria andDefenceResultFlagNotEqualTo(String value) {
            addCriterion("DEFENCE_RESULT_FLAG <>", value, "defenceResultFlag");
            return (Criteria) this;
        }

        public Criteria andDefenceResultFlagGreaterThan(String value) {
            addCriterion("DEFENCE_RESULT_FLAG >", value, "defenceResultFlag");
            return (Criteria) this;
        }

        public Criteria andDefenceResultFlagGreaterThanOrEqualTo(String value) {
            addCriterion("DEFENCE_RESULT_FLAG >=", value, "defenceResultFlag");
            return (Criteria) this;
        }

        public Criteria andDefenceResultFlagLessThan(String value) {
            addCriterion("DEFENCE_RESULT_FLAG <", value, "defenceResultFlag");
            return (Criteria) this;
        }

        public Criteria andDefenceResultFlagLessThanOrEqualTo(String value) {
            addCriterion("DEFENCE_RESULT_FLAG <=", value, "defenceResultFlag");
            return (Criteria) this;
        }

        public Criteria andDefenceResultFlagLike(String value) {
            addCriterion("DEFENCE_RESULT_FLAG like", value, "defenceResultFlag");
            return (Criteria) this;
        }

        public Criteria andDefenceResultFlagNotLike(String value) {
            addCriterion("DEFENCE_RESULT_FLAG not like", value, "defenceResultFlag");
            return (Criteria) this;
        }

        public Criteria andDefenceResultFlagIn(List<String> values) {
            addCriterion("DEFENCE_RESULT_FLAG in", values, "defenceResultFlag");
            return (Criteria) this;
        }

        public Criteria andDefenceResultFlagNotIn(List<String> values) {
            addCriterion("DEFENCE_RESULT_FLAG not in", values, "defenceResultFlag");
            return (Criteria) this;
        }

        public Criteria andDefenceResultFlagBetween(String value1, String value2) {
            addCriterion("DEFENCE_RESULT_FLAG between", value1, value2, "defenceResultFlag");
            return (Criteria) this;
        }

        public Criteria andDefenceResultFlagNotBetween(String value1, String value2) {
            addCriterion("DEFENCE_RESULT_FLAG not between", value1, value2, "defenceResultFlag");
            return (Criteria) this;
        }

        public Criteria andXwfwhAdviceFlagIsNull() {
            addCriterion("XWFWH_ADVICE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andXwfwhAdviceFlagIsNotNull() {
            addCriterion("XWFWH_ADVICE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andXwfwhAdviceFlagEqualTo(String value) {
            addCriterion("XWFWH_ADVICE_FLAG =", value, "xwfwhAdviceFlag");
            return (Criteria) this;
        }

        public Criteria andXwfwhAdviceFlagNotEqualTo(String value) {
            addCriterion("XWFWH_ADVICE_FLAG <>", value, "xwfwhAdviceFlag");
            return (Criteria) this;
        }

        public Criteria andXwfwhAdviceFlagGreaterThan(String value) {
            addCriterion("XWFWH_ADVICE_FLAG >", value, "xwfwhAdviceFlag");
            return (Criteria) this;
        }

        public Criteria andXwfwhAdviceFlagGreaterThanOrEqualTo(String value) {
            addCriterion("XWFWH_ADVICE_FLAG >=", value, "xwfwhAdviceFlag");
            return (Criteria) this;
        }

        public Criteria andXwfwhAdviceFlagLessThan(String value) {
            addCriterion("XWFWH_ADVICE_FLAG <", value, "xwfwhAdviceFlag");
            return (Criteria) this;
        }

        public Criteria andXwfwhAdviceFlagLessThanOrEqualTo(String value) {
            addCriterion("XWFWH_ADVICE_FLAG <=", value, "xwfwhAdviceFlag");
            return (Criteria) this;
        }

        public Criteria andXwfwhAdviceFlagLike(String value) {
            addCriterion("XWFWH_ADVICE_FLAG like", value, "xwfwhAdviceFlag");
            return (Criteria) this;
        }

        public Criteria andXwfwhAdviceFlagNotLike(String value) {
            addCriterion("XWFWH_ADVICE_FLAG not like", value, "xwfwhAdviceFlag");
            return (Criteria) this;
        }

        public Criteria andXwfwhAdviceFlagIn(List<String> values) {
            addCriterion("XWFWH_ADVICE_FLAG in", values, "xwfwhAdviceFlag");
            return (Criteria) this;
        }

        public Criteria andXwfwhAdviceFlagNotIn(List<String> values) {
            addCriterion("XWFWH_ADVICE_FLAG not in", values, "xwfwhAdviceFlag");
            return (Criteria) this;
        }

        public Criteria andXwfwhAdviceFlagBetween(String value1, String value2) {
            addCriterion("XWFWH_ADVICE_FLAG between", value1, value2, "xwfwhAdviceFlag");
            return (Criteria) this;
        }

        public Criteria andXwfwhAdviceFlagNotBetween(String value1, String value2) {
            addCriterion("XWFWH_ADVICE_FLAG not between", value1, value2, "xwfwhAdviceFlag");
            return (Criteria) this;
        }

        public Criteria andXxwhAdviceFlagIsNull() {
            addCriterion("XXWH_ADVICE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andXxwhAdviceFlagIsNotNull() {
            addCriterion("XXWH_ADVICE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andXxwhAdviceFlagEqualTo(String value) {
            addCriterion("XXWH_ADVICE_FLAG =", value, "xxwhAdviceFlag");
            return (Criteria) this;
        }

        public Criteria andXxwhAdviceFlagNotEqualTo(String value) {
            addCriterion("XXWH_ADVICE_FLAG <>", value, "xxwhAdviceFlag");
            return (Criteria) this;
        }

        public Criteria andXxwhAdviceFlagGreaterThan(String value) {
            addCriterion("XXWH_ADVICE_FLAG >", value, "xxwhAdviceFlag");
            return (Criteria) this;
        }

        public Criteria andXxwhAdviceFlagGreaterThanOrEqualTo(String value) {
            addCriterion("XXWH_ADVICE_FLAG >=", value, "xxwhAdviceFlag");
            return (Criteria) this;
        }

        public Criteria andXxwhAdviceFlagLessThan(String value) {
            addCriterion("XXWH_ADVICE_FLAG <", value, "xxwhAdviceFlag");
            return (Criteria) this;
        }

        public Criteria andXxwhAdviceFlagLessThanOrEqualTo(String value) {
            addCriterion("XXWH_ADVICE_FLAG <=", value, "xxwhAdviceFlag");
            return (Criteria) this;
        }

        public Criteria andXxwhAdviceFlagLike(String value) {
            addCriterion("XXWH_ADVICE_FLAG like", value, "xxwhAdviceFlag");
            return (Criteria) this;
        }

        public Criteria andXxwhAdviceFlagNotLike(String value) {
            addCriterion("XXWH_ADVICE_FLAG not like", value, "xxwhAdviceFlag");
            return (Criteria) this;
        }

        public Criteria andXxwhAdviceFlagIn(List<String> values) {
            addCriterion("XXWH_ADVICE_FLAG in", values, "xxwhAdviceFlag");
            return (Criteria) this;
        }

        public Criteria andXxwhAdviceFlagNotIn(List<String> values) {
            addCriterion("XXWH_ADVICE_FLAG not in", values, "xxwhAdviceFlag");
            return (Criteria) this;
        }

        public Criteria andXxwhAdviceFlagBetween(String value1, String value2) {
            addCriterion("XXWH_ADVICE_FLAG between", value1, value2, "xxwhAdviceFlag");
            return (Criteria) this;
        }

        public Criteria andXxwhAdviceFlagNotBetween(String value1, String value2) {
            addCriterion("XXWH_ADVICE_FLAG not between", value1, value2, "xxwhAdviceFlag");
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

        public Criteria andExcellentResultIsNull() {
            addCriterion("EXCELLENT_RESULT is null");
            return (Criteria) this;
        }

        public Criteria andExcellentResultIsNotNull() {
            addCriterion("EXCELLENT_RESULT is not null");
            return (Criteria) this;
        }

        public Criteria andExcellentResultEqualTo(String value) {
            addCriterion("EXCELLENT_RESULT =", value, "excellentResult");
            return (Criteria) this;
        }

        public Criteria andExcellentResultNotEqualTo(String value) {
            addCriterion("EXCELLENT_RESULT <>", value, "excellentResult");
            return (Criteria) this;
        }

        public Criteria andExcellentResultGreaterThan(String value) {
            addCriterion("EXCELLENT_RESULT >", value, "excellentResult");
            return (Criteria) this;
        }

        public Criteria andExcellentResultGreaterThanOrEqualTo(String value) {
            addCriterion("EXCELLENT_RESULT >=", value, "excellentResult");
            return (Criteria) this;
        }

        public Criteria andExcellentResultLessThan(String value) {
            addCriterion("EXCELLENT_RESULT <", value, "excellentResult");
            return (Criteria) this;
        }

        public Criteria andExcellentResultLessThanOrEqualTo(String value) {
            addCriterion("EXCELLENT_RESULT <=", value, "excellentResult");
            return (Criteria) this;
        }

        public Criteria andExcellentResultLike(String value) {
            addCriterion("EXCELLENT_RESULT like", value, "excellentResult");
            return (Criteria) this;
        }

        public Criteria andExcellentResultNotLike(String value) {
            addCriterion("EXCELLENT_RESULT not like", value, "excellentResult");
            return (Criteria) this;
        }

        public Criteria andExcellentResultIn(List<String> values) {
            addCriterion("EXCELLENT_RESULT in", values, "excellentResult");
            return (Criteria) this;
        }

        public Criteria andExcellentResultNotIn(List<String> values) {
            addCriterion("EXCELLENT_RESULT not in", values, "excellentResult");
            return (Criteria) this;
        }

        public Criteria andExcellentResultBetween(String value1, String value2) {
            addCriterion("EXCELLENT_RESULT between", value1, value2, "excellentResult");
            return (Criteria) this;
        }

        public Criteria andExcellentResultNotBetween(String value1, String value2) {
            addCriterion("EXCELLENT_RESULT not between", value1, value2, "excellentResult");
            return (Criteria) this;
        }

        public Criteria andGoodResultIsNull() {
            addCriterion("GOOD_RESULT is null");
            return (Criteria) this;
        }

        public Criteria andGoodResultIsNotNull() {
            addCriterion("GOOD_RESULT is not null");
            return (Criteria) this;
        }

        public Criteria andGoodResultEqualTo(String value) {
            addCriterion("GOOD_RESULT =", value, "goodResult");
            return (Criteria) this;
        }

        public Criteria andGoodResultNotEqualTo(String value) {
            addCriterion("GOOD_RESULT <>", value, "goodResult");
            return (Criteria) this;
        }

        public Criteria andGoodResultGreaterThan(String value) {
            addCriterion("GOOD_RESULT >", value, "goodResult");
            return (Criteria) this;
        }

        public Criteria andGoodResultGreaterThanOrEqualTo(String value) {
            addCriterion("GOOD_RESULT >=", value, "goodResult");
            return (Criteria) this;
        }

        public Criteria andGoodResultLessThan(String value) {
            addCriterion("GOOD_RESULT <", value, "goodResult");
            return (Criteria) this;
        }

        public Criteria andGoodResultLessThanOrEqualTo(String value) {
            addCriterion("GOOD_RESULT <=", value, "goodResult");
            return (Criteria) this;
        }

        public Criteria andGoodResultLike(String value) {
            addCriterion("GOOD_RESULT like", value, "goodResult");
            return (Criteria) this;
        }

        public Criteria andGoodResultNotLike(String value) {
            addCriterion("GOOD_RESULT not like", value, "goodResult");
            return (Criteria) this;
        }

        public Criteria andGoodResultIn(List<String> values) {
            addCriterion("GOOD_RESULT in", values, "goodResult");
            return (Criteria) this;
        }

        public Criteria andGoodResultNotIn(List<String> values) {
            addCriterion("GOOD_RESULT not in", values, "goodResult");
            return (Criteria) this;
        }

        public Criteria andGoodResultBetween(String value1, String value2) {
            addCriterion("GOOD_RESULT between", value1, value2, "goodResult");
            return (Criteria) this;
        }

        public Criteria andGoodResultNotBetween(String value1, String value2) {
            addCriterion("GOOD_RESULT not between", value1, value2, "goodResult");
            return (Criteria) this;
        }

        public Criteria andPassResultIsNull() {
            addCriterion("PASS_RESULT is null");
            return (Criteria) this;
        }

        public Criteria andPassResultIsNotNull() {
            addCriterion("PASS_RESULT is not null");
            return (Criteria) this;
        }

        public Criteria andPassResultEqualTo(String value) {
            addCriterion("PASS_RESULT =", value, "passResult");
            return (Criteria) this;
        }

        public Criteria andPassResultNotEqualTo(String value) {
            addCriterion("PASS_RESULT <>", value, "passResult");
            return (Criteria) this;
        }

        public Criteria andPassResultGreaterThan(String value) {
            addCriterion("PASS_RESULT >", value, "passResult");
            return (Criteria) this;
        }

        public Criteria andPassResultGreaterThanOrEqualTo(String value) {
            addCriterion("PASS_RESULT >=", value, "passResult");
            return (Criteria) this;
        }

        public Criteria andPassResultLessThan(String value) {
            addCriterion("PASS_RESULT <", value, "passResult");
            return (Criteria) this;
        }

        public Criteria andPassResultLessThanOrEqualTo(String value) {
            addCriterion("PASS_RESULT <=", value, "passResult");
            return (Criteria) this;
        }

        public Criteria andPassResultLike(String value) {
            addCriterion("PASS_RESULT like", value, "passResult");
            return (Criteria) this;
        }

        public Criteria andPassResultNotLike(String value) {
            addCriterion("PASS_RESULT not like", value, "passResult");
            return (Criteria) this;
        }

        public Criteria andPassResultIn(List<String> values) {
            addCriterion("PASS_RESULT in", values, "passResult");
            return (Criteria) this;
        }

        public Criteria andPassResultNotIn(List<String> values) {
            addCriterion("PASS_RESULT not in", values, "passResult");
            return (Criteria) this;
        }

        public Criteria andPassResultBetween(String value1, String value2) {
            addCriterion("PASS_RESULT between", value1, value2, "passResult");
            return (Criteria) this;
        }

        public Criteria andPassResultNotBetween(String value1, String value2) {
            addCriterion("PASS_RESULT not between", value1, value2, "passResult");
            return (Criteria) this;
        }

        public Criteria andFailResultIsNull() {
            addCriterion("FAIL_RESULT is null");
            return (Criteria) this;
        }

        public Criteria andFailResultIsNotNull() {
            addCriterion("FAIL_RESULT is not null");
            return (Criteria) this;
        }

        public Criteria andFailResultEqualTo(String value) {
            addCriterion("FAIL_RESULT =", value, "failResult");
            return (Criteria) this;
        }

        public Criteria andFailResultNotEqualTo(String value) {
            addCriterion("FAIL_RESULT <>", value, "failResult");
            return (Criteria) this;
        }

        public Criteria andFailResultGreaterThan(String value) {
            addCriterion("FAIL_RESULT >", value, "failResult");
            return (Criteria) this;
        }

        public Criteria andFailResultGreaterThanOrEqualTo(String value) {
            addCriterion("FAIL_RESULT >=", value, "failResult");
            return (Criteria) this;
        }

        public Criteria andFailResultLessThan(String value) {
            addCriterion("FAIL_RESULT <", value, "failResult");
            return (Criteria) this;
        }

        public Criteria andFailResultLessThanOrEqualTo(String value) {
            addCriterion("FAIL_RESULT <=", value, "failResult");
            return (Criteria) this;
        }

        public Criteria andFailResultLike(String value) {
            addCriterion("FAIL_RESULT like", value, "failResult");
            return (Criteria) this;
        }

        public Criteria andFailResultNotLike(String value) {
            addCriterion("FAIL_RESULT not like", value, "failResult");
            return (Criteria) this;
        }

        public Criteria andFailResultIn(List<String> values) {
            addCriterion("FAIL_RESULT in", values, "failResult");
            return (Criteria) this;
        }

        public Criteria andFailResultNotIn(List<String> values) {
            addCriterion("FAIL_RESULT not in", values, "failResult");
            return (Criteria) this;
        }

        public Criteria andFailResultBetween(String value1, String value2) {
            addCriterion("FAIL_RESULT between", value1, value2, "failResult");
            return (Criteria) this;
        }

        public Criteria andFailResultNotBetween(String value1, String value2) {
            addCriterion("FAIL_RESULT not between", value1, value2, "failResult");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdIsNull() {
            addCriterion("STUDY_FORM_ID is null");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdIsNotNull() {
            addCriterion("STUDY_FORM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdEqualTo(String value) {
            addCriterion("STUDY_FORM_ID =", value, "studyFormId");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdNotEqualTo(String value) {
            addCriterion("STUDY_FORM_ID <>", value, "studyFormId");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdGreaterThan(String value) {
            addCriterion("STUDY_FORM_ID >", value, "studyFormId");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdGreaterThanOrEqualTo(String value) {
            addCriterion("STUDY_FORM_ID >=", value, "studyFormId");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdLessThan(String value) {
            addCriterion("STUDY_FORM_ID <", value, "studyFormId");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdLessThanOrEqualTo(String value) {
            addCriterion("STUDY_FORM_ID <=", value, "studyFormId");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdLike(String value) {
            addCriterion("STUDY_FORM_ID like", value, "studyFormId");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdNotLike(String value) {
            addCriterion("STUDY_FORM_ID not like", value, "studyFormId");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdIn(List<String> values) {
            addCriterion("STUDY_FORM_ID in", values, "studyFormId");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdNotIn(List<String> values) {
            addCriterion("STUDY_FORM_ID not in", values, "studyFormId");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdBetween(String value1, String value2) {
            addCriterion("STUDY_FORM_ID between", value1, value2, "studyFormId");
            return (Criteria) this;
        }

        public Criteria andStudyFormIdNotBetween(String value1, String value2) {
            addCriterion("STUDY_FORM_ID not between", value1, value2, "studyFormId");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameIsNull() {
            addCriterion("STUDY_FORM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameIsNotNull() {
            addCriterion("STUDY_FORM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameEqualTo(String value) {
            addCriterion("STUDY_FORM_NAME =", value, "studyFormName");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameNotEqualTo(String value) {
            addCriterion("STUDY_FORM_NAME <>", value, "studyFormName");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameGreaterThan(String value) {
            addCriterion("STUDY_FORM_NAME >", value, "studyFormName");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameGreaterThanOrEqualTo(String value) {
            addCriterion("STUDY_FORM_NAME >=", value, "studyFormName");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameLessThan(String value) {
            addCriterion("STUDY_FORM_NAME <", value, "studyFormName");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameLessThanOrEqualTo(String value) {
            addCriterion("STUDY_FORM_NAME <=", value, "studyFormName");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameLike(String value) {
            addCriterion("STUDY_FORM_NAME like", value, "studyFormName");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameNotLike(String value) {
            addCriterion("STUDY_FORM_NAME not like", value, "studyFormName");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameIn(List<String> values) {
            addCriterion("STUDY_FORM_NAME in", values, "studyFormName");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameNotIn(List<String> values) {
            addCriterion("STUDY_FORM_NAME not in", values, "studyFormName");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameBetween(String value1, String value2) {
            addCriterion("STUDY_FORM_NAME between", value1, value2, "studyFormName");
            return (Criteria) this;
        }

        public Criteria andStudyFormNameNotBetween(String value1, String value2) {
            addCriterion("STUDY_FORM_NAME not between", value1, value2, "studyFormName");
            return (Criteria) this;
        }

        public Criteria andIsReplenishIsNull() {
            addCriterion("IS_REPLENISH is null");
            return (Criteria) this;
        }

        public Criteria andIsReplenishIsNotNull() {
            addCriterion("IS_REPLENISH is not null");
            return (Criteria) this;
        }

        public Criteria andIsReplenishEqualTo(String value) {
            addCriterion("IS_REPLENISH =", value, "isReplenish");
            return (Criteria) this;
        }

        public Criteria andIsReplenishNotEqualTo(String value) {
            addCriterion("IS_REPLENISH <>", value, "isReplenish");
            return (Criteria) this;
        }

        public Criteria andIsReplenishGreaterThan(String value) {
            addCriterion("IS_REPLENISH >", value, "isReplenish");
            return (Criteria) this;
        }

        public Criteria andIsReplenishGreaterThanOrEqualTo(String value) {
            addCriterion("IS_REPLENISH >=", value, "isReplenish");
            return (Criteria) this;
        }

        public Criteria andIsReplenishLessThan(String value) {
            addCriterion("IS_REPLENISH <", value, "isReplenish");
            return (Criteria) this;
        }

        public Criteria andIsReplenishLessThanOrEqualTo(String value) {
            addCriterion("IS_REPLENISH <=", value, "isReplenish");
            return (Criteria) this;
        }

        public Criteria andIsReplenishLike(String value) {
            addCriterion("IS_REPLENISH like", value, "isReplenish");
            return (Criteria) this;
        }

        public Criteria andIsReplenishNotLike(String value) {
            addCriterion("IS_REPLENISH not like", value, "isReplenish");
            return (Criteria) this;
        }

        public Criteria andIsReplenishIn(List<String> values) {
            addCriterion("IS_REPLENISH in", values, "isReplenish");
            return (Criteria) this;
        }

        public Criteria andIsReplenishNotIn(List<String> values) {
            addCriterion("IS_REPLENISH not in", values, "isReplenish");
            return (Criteria) this;
        }

        public Criteria andIsReplenishBetween(String value1, String value2) {
            addCriterion("IS_REPLENISH between", value1, value2, "isReplenish");
            return (Criteria) this;
        }

        public Criteria andIsReplenishNotBetween(String value1, String value2) {
            addCriterion("IS_REPLENISH not between", value1, value2, "isReplenish");
            return (Criteria) this;
        }

        public Criteria andReplenishTimeIsNull() {
            addCriterion("REPLENISH_TIME is null");
            return (Criteria) this;
        }

        public Criteria andReplenishTimeIsNotNull() {
            addCriterion("REPLENISH_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andReplenishTimeEqualTo(String value) {
            addCriterion("REPLENISH_TIME =", value, "replenishTime");
            return (Criteria) this;
        }

        public Criteria andReplenishTimeNotEqualTo(String value) {
            addCriterion("REPLENISH_TIME <>", value, "replenishTime");
            return (Criteria) this;
        }

        public Criteria andReplenishTimeGreaterThan(String value) {
            addCriterion("REPLENISH_TIME >", value, "replenishTime");
            return (Criteria) this;
        }

        public Criteria andReplenishTimeGreaterThanOrEqualTo(String value) {
            addCriterion("REPLENISH_TIME >=", value, "replenishTime");
            return (Criteria) this;
        }

        public Criteria andReplenishTimeLessThan(String value) {
            addCriterion("REPLENISH_TIME <", value, "replenishTime");
            return (Criteria) this;
        }

        public Criteria andReplenishTimeLessThanOrEqualTo(String value) {
            addCriterion("REPLENISH_TIME <=", value, "replenishTime");
            return (Criteria) this;
        }

        public Criteria andReplenishTimeLike(String value) {
            addCriterion("REPLENISH_TIME like", value, "replenishTime");
            return (Criteria) this;
        }

        public Criteria andReplenishTimeNotLike(String value) {
            addCriterion("REPLENISH_TIME not like", value, "replenishTime");
            return (Criteria) this;
        }

        public Criteria andReplenishTimeIn(List<String> values) {
            addCriterion("REPLENISH_TIME in", values, "replenishTime");
            return (Criteria) this;
        }

        public Criteria andReplenishTimeNotIn(List<String> values) {
            addCriterion("REPLENISH_TIME not in", values, "replenishTime");
            return (Criteria) this;
        }

        public Criteria andReplenishTimeBetween(String value1, String value2) {
            addCriterion("REPLENISH_TIME between", value1, value2, "replenishTime");
            return (Criteria) this;
        }

        public Criteria andReplenishTimeNotBetween(String value1, String value2) {
            addCriterion("REPLENISH_TIME not between", value1, value2, "replenishTime");
            return (Criteria) this;
        }

        public Criteria andHsAdviceIsNull() {
            addCriterion("HS_ADVICE is null");
            return (Criteria) this;
        }

        public Criteria andHsAdviceIsNotNull() {
            addCriterion("HS_ADVICE is not null");
            return (Criteria) this;
        }

        public Criteria andHsAdviceEqualTo(String value) {
            addCriterion("HS_ADVICE =", value, "hsAdvice");
            return (Criteria) this;
        }

        public Criteria andHsAdviceNotEqualTo(String value) {
            addCriterion("HS_ADVICE <>", value, "hsAdvice");
            return (Criteria) this;
        }

        public Criteria andHsAdviceGreaterThan(String value) {
            addCriterion("HS_ADVICE >", value, "hsAdvice");
            return (Criteria) this;
        }

        public Criteria andHsAdviceGreaterThanOrEqualTo(String value) {
            addCriterion("HS_ADVICE >=", value, "hsAdvice");
            return (Criteria) this;
        }

        public Criteria andHsAdviceLessThan(String value) {
            addCriterion("HS_ADVICE <", value, "hsAdvice");
            return (Criteria) this;
        }

        public Criteria andHsAdviceLessThanOrEqualTo(String value) {
            addCriterion("HS_ADVICE <=", value, "hsAdvice");
            return (Criteria) this;
        }

        public Criteria andHsAdviceLike(String value) {
            addCriterion("HS_ADVICE like", value, "hsAdvice");
            return (Criteria) this;
        }

        public Criteria andHsAdviceNotLike(String value) {
            addCriterion("HS_ADVICE not like", value, "hsAdvice");
            return (Criteria) this;
        }

        public Criteria andHsAdviceIn(List<String> values) {
            addCriterion("HS_ADVICE in", values, "hsAdvice");
            return (Criteria) this;
        }

        public Criteria andHsAdviceNotIn(List<String> values) {
            addCriterion("HS_ADVICE not in", values, "hsAdvice");
            return (Criteria) this;
        }

        public Criteria andHsAdviceBetween(String value1, String value2) {
            addCriterion("HS_ADVICE between", value1, value2, "hsAdvice");
            return (Criteria) this;
        }

        public Criteria andHsAdviceNotBetween(String value1, String value2) {
            addCriterion("HS_ADVICE not between", value1, value2, "hsAdvice");
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