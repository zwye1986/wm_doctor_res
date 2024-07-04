package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class SchArrangeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SchArrangeExample() {
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

        public Criteria andArrangeFlowIsNull() {
            addCriterion("ARRANGE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowIsNotNull() {
            addCriterion("ARRANGE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowEqualTo(String value) {
            addCriterion("ARRANGE_FLOW =", value, "arrangeFlow");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowNotEqualTo(String value) {
            addCriterion("ARRANGE_FLOW <>", value, "arrangeFlow");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowGreaterThan(String value) {
            addCriterion("ARRANGE_FLOW >", value, "arrangeFlow");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ARRANGE_FLOW >=", value, "arrangeFlow");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowLessThan(String value) {
            addCriterion("ARRANGE_FLOW <", value, "arrangeFlow");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowLessThanOrEqualTo(String value) {
            addCriterion("ARRANGE_FLOW <=", value, "arrangeFlow");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowLike(String value) {
            addCriterion("ARRANGE_FLOW like", value, "arrangeFlow");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowNotLike(String value) {
            addCriterion("ARRANGE_FLOW not like", value, "arrangeFlow");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowIn(List<String> values) {
            addCriterion("ARRANGE_FLOW in", values, "arrangeFlow");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowNotIn(List<String> values) {
            addCriterion("ARRANGE_FLOW not in", values, "arrangeFlow");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowBetween(String value1, String value2) {
            addCriterion("ARRANGE_FLOW between", value1, value2, "arrangeFlow");
            return (Criteria) this;
        }

        public Criteria andArrangeFlowNotBetween(String value1, String value2) {
            addCriterion("ARRANGE_FLOW not between", value1, value2, "arrangeFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorNumIsNull() {
            addCriterion("DOCTOR_NUM is null");
            return (Criteria) this;
        }

        public Criteria andDoctorNumIsNotNull() {
            addCriterion("DOCTOR_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorNumEqualTo(Integer value) {
            addCriterion("DOCTOR_NUM =", value, "doctorNum");
            return (Criteria) this;
        }

        public Criteria andDoctorNumNotEqualTo(Integer value) {
            addCriterion("DOCTOR_NUM <>", value, "doctorNum");
            return (Criteria) this;
        }

        public Criteria andDoctorNumGreaterThan(Integer value) {
            addCriterion("DOCTOR_NUM >", value, "doctorNum");
            return (Criteria) this;
        }

        public Criteria andDoctorNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("DOCTOR_NUM >=", value, "doctorNum");
            return (Criteria) this;
        }

        public Criteria andDoctorNumLessThan(Integer value) {
            addCriterion("DOCTOR_NUM <", value, "doctorNum");
            return (Criteria) this;
        }

        public Criteria andDoctorNumLessThanOrEqualTo(Integer value) {
            addCriterion("DOCTOR_NUM <=", value, "doctorNum");
            return (Criteria) this;
        }

        public Criteria andDoctorNumIn(List<Integer> values) {
            addCriterion("DOCTOR_NUM in", values, "doctorNum");
            return (Criteria) this;
        }

        public Criteria andDoctorNumNotIn(List<Integer> values) {
            addCriterion("DOCTOR_NUM not in", values, "doctorNum");
            return (Criteria) this;
        }

        public Criteria andDoctorNumBetween(Integer value1, Integer value2) {
            addCriterion("DOCTOR_NUM between", value1, value2, "doctorNum");
            return (Criteria) this;
        }

        public Criteria andDoctorNumNotBetween(Integer value1, Integer value2) {
            addCriterion("DOCTOR_NUM not between", value1, value2, "doctorNum");
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

        public Criteria andOperTimeIsNull() {
            addCriterion("OPER_TIME is null");
            return (Criteria) this;
        }

        public Criteria andOperTimeIsNotNull() {
            addCriterion("OPER_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andOperTimeEqualTo(String value) {
            addCriterion("OPER_TIME =", value, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeNotEqualTo(String value) {
            addCriterion("OPER_TIME <>", value, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeGreaterThan(String value) {
            addCriterion("OPER_TIME >", value, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_TIME >=", value, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeLessThan(String value) {
            addCriterion("OPER_TIME <", value, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeLessThanOrEqualTo(String value) {
            addCriterion("OPER_TIME <=", value, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeLike(String value) {
            addCriterion("OPER_TIME like", value, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeNotLike(String value) {
            addCriterion("OPER_TIME not like", value, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeIn(List<String> values) {
            addCriterion("OPER_TIME in", values, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeNotIn(List<String> values) {
            addCriterion("OPER_TIME not in", values, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeBetween(String value1, String value2) {
            addCriterion("OPER_TIME between", value1, value2, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperTimeNotBetween(String value1, String value2) {
            addCriterion("OPER_TIME not between", value1, value2, "operTime");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowIsNull() {
            addCriterion("OPER_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowIsNotNull() {
            addCriterion("OPER_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowEqualTo(String value) {
            addCriterion("OPER_USER_FLOW =", value, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowNotEqualTo(String value) {
            addCriterion("OPER_USER_FLOW <>", value, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowGreaterThan(String value) {
            addCriterion("OPER_USER_FLOW >", value, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_USER_FLOW >=", value, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowLessThan(String value) {
            addCriterion("OPER_USER_FLOW <", value, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowLessThanOrEqualTo(String value) {
            addCriterion("OPER_USER_FLOW <=", value, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowLike(String value) {
            addCriterion("OPER_USER_FLOW like", value, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowNotLike(String value) {
            addCriterion("OPER_USER_FLOW not like", value, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowIn(List<String> values) {
            addCriterion("OPER_USER_FLOW in", values, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowNotIn(List<String> values) {
            addCriterion("OPER_USER_FLOW not in", values, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowBetween(String value1, String value2) {
            addCriterion("OPER_USER_FLOW between", value1, value2, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserFlowNotBetween(String value1, String value2) {
            addCriterion("OPER_USER_FLOW not between", value1, value2, "operUserFlow");
            return (Criteria) this;
        }

        public Criteria andOperUserNameIsNull() {
            addCriterion("OPER_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOperUserNameIsNotNull() {
            addCriterion("OPER_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOperUserNameEqualTo(String value) {
            addCriterion("OPER_USER_NAME =", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameNotEqualTo(String value) {
            addCriterion("OPER_USER_NAME <>", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameGreaterThan(String value) {
            addCriterion("OPER_USER_NAME >", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_USER_NAME >=", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameLessThan(String value) {
            addCriterion("OPER_USER_NAME <", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameLessThanOrEqualTo(String value) {
            addCriterion("OPER_USER_NAME <=", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameLike(String value) {
            addCriterion("OPER_USER_NAME like", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameNotLike(String value) {
            addCriterion("OPER_USER_NAME not like", value, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameIn(List<String> values) {
            addCriterion("OPER_USER_NAME in", values, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameNotIn(List<String> values) {
            addCriterion("OPER_USER_NAME not in", values, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameBetween(String value1, String value2) {
            addCriterion("OPER_USER_NAME between", value1, value2, "operUserName");
            return (Criteria) this;
        }

        public Criteria andOperUserNameNotBetween(String value1, String value2) {
            addCriterion("OPER_USER_NAME not between", value1, value2, "operUserName");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeIdIsNull() {
            addCriterion("ARRANGE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeIdIsNotNull() {
            addCriterion("ARRANGE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeIdEqualTo(String value) {
            addCriterion("ARRANGE_TYPE_ID =", value, "arrangeTypeId");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeIdNotEqualTo(String value) {
            addCriterion("ARRANGE_TYPE_ID <>", value, "arrangeTypeId");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeIdGreaterThan(String value) {
            addCriterion("ARRANGE_TYPE_ID >", value, "arrangeTypeId");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("ARRANGE_TYPE_ID >=", value, "arrangeTypeId");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeIdLessThan(String value) {
            addCriterion("ARRANGE_TYPE_ID <", value, "arrangeTypeId");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeIdLessThanOrEqualTo(String value) {
            addCriterion("ARRANGE_TYPE_ID <=", value, "arrangeTypeId");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeIdLike(String value) {
            addCriterion("ARRANGE_TYPE_ID like", value, "arrangeTypeId");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeIdNotLike(String value) {
            addCriterion("ARRANGE_TYPE_ID not like", value, "arrangeTypeId");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeIdIn(List<String> values) {
            addCriterion("ARRANGE_TYPE_ID in", values, "arrangeTypeId");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeIdNotIn(List<String> values) {
            addCriterion("ARRANGE_TYPE_ID not in", values, "arrangeTypeId");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeIdBetween(String value1, String value2) {
            addCriterion("ARRANGE_TYPE_ID between", value1, value2, "arrangeTypeId");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeIdNotBetween(String value1, String value2) {
            addCriterion("ARRANGE_TYPE_ID not between", value1, value2, "arrangeTypeId");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeNameIsNull() {
            addCriterion("ARRANGE_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeNameIsNotNull() {
            addCriterion("ARRANGE_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeNameEqualTo(String value) {
            addCriterion("ARRANGE_TYPE_NAME =", value, "arrangeTypeName");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeNameNotEqualTo(String value) {
            addCriterion("ARRANGE_TYPE_NAME <>", value, "arrangeTypeName");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeNameGreaterThan(String value) {
            addCriterion("ARRANGE_TYPE_NAME >", value, "arrangeTypeName");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("ARRANGE_TYPE_NAME >=", value, "arrangeTypeName");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeNameLessThan(String value) {
            addCriterion("ARRANGE_TYPE_NAME <", value, "arrangeTypeName");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeNameLessThanOrEqualTo(String value) {
            addCriterion("ARRANGE_TYPE_NAME <=", value, "arrangeTypeName");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeNameLike(String value) {
            addCriterion("ARRANGE_TYPE_NAME like", value, "arrangeTypeName");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeNameNotLike(String value) {
            addCriterion("ARRANGE_TYPE_NAME not like", value, "arrangeTypeName");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeNameIn(List<String> values) {
            addCriterion("ARRANGE_TYPE_NAME in", values, "arrangeTypeName");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeNameNotIn(List<String> values) {
            addCriterion("ARRANGE_TYPE_NAME not in", values, "arrangeTypeName");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeNameBetween(String value1, String value2) {
            addCriterion("ARRANGE_TYPE_NAME between", value1, value2, "arrangeTypeName");
            return (Criteria) this;
        }

        public Criteria andArrangeTypeNameNotBetween(String value1, String value2) {
            addCriterion("ARRANGE_TYPE_NAME not between", value1, value2, "arrangeTypeName");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusIdIsNull() {
            addCriterion("ARRANGE_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusIdIsNotNull() {
            addCriterion("ARRANGE_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusIdEqualTo(String value) {
            addCriterion("ARRANGE_STATUS_ID =", value, "arrangeStatusId");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusIdNotEqualTo(String value) {
            addCriterion("ARRANGE_STATUS_ID <>", value, "arrangeStatusId");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusIdGreaterThan(String value) {
            addCriterion("ARRANGE_STATUS_ID >", value, "arrangeStatusId");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("ARRANGE_STATUS_ID >=", value, "arrangeStatusId");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusIdLessThan(String value) {
            addCriterion("ARRANGE_STATUS_ID <", value, "arrangeStatusId");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusIdLessThanOrEqualTo(String value) {
            addCriterion("ARRANGE_STATUS_ID <=", value, "arrangeStatusId");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusIdLike(String value) {
            addCriterion("ARRANGE_STATUS_ID like", value, "arrangeStatusId");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusIdNotLike(String value) {
            addCriterion("ARRANGE_STATUS_ID not like", value, "arrangeStatusId");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusIdIn(List<String> values) {
            addCriterion("ARRANGE_STATUS_ID in", values, "arrangeStatusId");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusIdNotIn(List<String> values) {
            addCriterion("ARRANGE_STATUS_ID not in", values, "arrangeStatusId");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusIdBetween(String value1, String value2) {
            addCriterion("ARRANGE_STATUS_ID between", value1, value2, "arrangeStatusId");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusIdNotBetween(String value1, String value2) {
            addCriterion("ARRANGE_STATUS_ID not between", value1, value2, "arrangeStatusId");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusNameIsNull() {
            addCriterion("ARRANGE_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusNameIsNotNull() {
            addCriterion("ARRANGE_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusNameEqualTo(String value) {
            addCriterion("ARRANGE_STATUS_NAME =", value, "arrangeStatusName");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusNameNotEqualTo(String value) {
            addCriterion("ARRANGE_STATUS_NAME <>", value, "arrangeStatusName");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusNameGreaterThan(String value) {
            addCriterion("ARRANGE_STATUS_NAME >", value, "arrangeStatusName");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("ARRANGE_STATUS_NAME >=", value, "arrangeStatusName");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusNameLessThan(String value) {
            addCriterion("ARRANGE_STATUS_NAME <", value, "arrangeStatusName");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusNameLessThanOrEqualTo(String value) {
            addCriterion("ARRANGE_STATUS_NAME <=", value, "arrangeStatusName");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusNameLike(String value) {
            addCriterion("ARRANGE_STATUS_NAME like", value, "arrangeStatusName");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusNameNotLike(String value) {
            addCriterion("ARRANGE_STATUS_NAME not like", value, "arrangeStatusName");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusNameIn(List<String> values) {
            addCriterion("ARRANGE_STATUS_NAME in", values, "arrangeStatusName");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusNameNotIn(List<String> values) {
            addCriterion("ARRANGE_STATUS_NAME not in", values, "arrangeStatusName");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusNameBetween(String value1, String value2) {
            addCriterion("ARRANGE_STATUS_NAME between", value1, value2, "arrangeStatusName");
            return (Criteria) this;
        }

        public Criteria andArrangeStatusNameNotBetween(String value1, String value2) {
            addCriterion("ARRANGE_STATUS_NAME not between", value1, value2, "arrangeStatusName");
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

        public Criteria andBeginIndexIsNull() {
            addCriterion("BEGIN_INDEX is null");
            return (Criteria) this;
        }

        public Criteria andBeginIndexIsNotNull() {
            addCriterion("BEGIN_INDEX is not null");
            return (Criteria) this;
        }

        public Criteria andBeginIndexEqualTo(String value) {
            addCriterion("BEGIN_INDEX =", value, "beginIndex");
            return (Criteria) this;
        }

        public Criteria andBeginIndexNotEqualTo(String value) {
            addCriterion("BEGIN_INDEX <>", value, "beginIndex");
            return (Criteria) this;
        }

        public Criteria andBeginIndexGreaterThan(String value) {
            addCriterion("BEGIN_INDEX >", value, "beginIndex");
            return (Criteria) this;
        }

        public Criteria andBeginIndexGreaterThanOrEqualTo(String value) {
            addCriterion("BEGIN_INDEX >=", value, "beginIndex");
            return (Criteria) this;
        }

        public Criteria andBeginIndexLessThan(String value) {
            addCriterion("BEGIN_INDEX <", value, "beginIndex");
            return (Criteria) this;
        }

        public Criteria andBeginIndexLessThanOrEqualTo(String value) {
            addCriterion("BEGIN_INDEX <=", value, "beginIndex");
            return (Criteria) this;
        }

        public Criteria andBeginIndexLike(String value) {
            addCriterion("BEGIN_INDEX like", value, "beginIndex");
            return (Criteria) this;
        }

        public Criteria andBeginIndexNotLike(String value) {
            addCriterion("BEGIN_INDEX not like", value, "beginIndex");
            return (Criteria) this;
        }

        public Criteria andBeginIndexIn(List<String> values) {
            addCriterion("BEGIN_INDEX in", values, "beginIndex");
            return (Criteria) this;
        }

        public Criteria andBeginIndexNotIn(List<String> values) {
            addCriterion("BEGIN_INDEX not in", values, "beginIndex");
            return (Criteria) this;
        }

        public Criteria andBeginIndexBetween(String value1, String value2) {
            addCriterion("BEGIN_INDEX between", value1, value2, "beginIndex");
            return (Criteria) this;
        }

        public Criteria andBeginIndexNotBetween(String value1, String value2) {
            addCriterion("BEGIN_INDEX not between", value1, value2, "beginIndex");
            return (Criteria) this;
        }

        public Criteria andEndIndexIsNull() {
            addCriterion("END_INDEX is null");
            return (Criteria) this;
        }

        public Criteria andEndIndexIsNotNull() {
            addCriterion("END_INDEX is not null");
            return (Criteria) this;
        }

        public Criteria andEndIndexEqualTo(String value) {
            addCriterion("END_INDEX =", value, "endIndex");
            return (Criteria) this;
        }

        public Criteria andEndIndexNotEqualTo(String value) {
            addCriterion("END_INDEX <>", value, "endIndex");
            return (Criteria) this;
        }

        public Criteria andEndIndexGreaterThan(String value) {
            addCriterion("END_INDEX >", value, "endIndex");
            return (Criteria) this;
        }

        public Criteria andEndIndexGreaterThanOrEqualTo(String value) {
            addCriterion("END_INDEX >=", value, "endIndex");
            return (Criteria) this;
        }

        public Criteria andEndIndexLessThan(String value) {
            addCriterion("END_INDEX <", value, "endIndex");
            return (Criteria) this;
        }

        public Criteria andEndIndexLessThanOrEqualTo(String value) {
            addCriterion("END_INDEX <=", value, "endIndex");
            return (Criteria) this;
        }

        public Criteria andEndIndexLike(String value) {
            addCriterion("END_INDEX like", value, "endIndex");
            return (Criteria) this;
        }

        public Criteria andEndIndexNotLike(String value) {
            addCriterion("END_INDEX not like", value, "endIndex");
            return (Criteria) this;
        }

        public Criteria andEndIndexIn(List<String> values) {
            addCriterion("END_INDEX in", values, "endIndex");
            return (Criteria) this;
        }

        public Criteria andEndIndexNotIn(List<String> values) {
            addCriterion("END_INDEX not in", values, "endIndex");
            return (Criteria) this;
        }

        public Criteria andEndIndexBetween(String value1, String value2) {
            addCriterion("END_INDEX between", value1, value2, "endIndex");
            return (Criteria) this;
        }

        public Criteria andEndIndexNotBetween(String value1, String value2) {
            addCriterion("END_INDEX not between", value1, value2, "endIndex");
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