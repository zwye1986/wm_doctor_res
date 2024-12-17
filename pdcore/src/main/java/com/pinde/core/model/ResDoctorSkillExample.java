package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResDoctorSkillExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResDoctorSkillExample() {
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

        public Criteria andDoctorSkillFlowIsNull() {
            addCriterion("DOCTOR_SKILL_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDoctorSkillFlowIsNotNull() {
            addCriterion("DOCTOR_SKILL_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorSkillFlowEqualTo(String value) {
            addCriterion("DOCTOR_SKILL_FLOW =", value, "doctorSkillFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorSkillFlowNotEqualTo(String value) {
            addCriterion("DOCTOR_SKILL_FLOW <>", value, "doctorSkillFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorSkillFlowGreaterThan(String value) {
            addCriterion("DOCTOR_SKILL_FLOW >", value, "doctorSkillFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorSkillFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_SKILL_FLOW >=", value, "doctorSkillFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorSkillFlowLessThan(String value) {
            addCriterion("DOCTOR_SKILL_FLOW <", value, "doctorSkillFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorSkillFlowLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_SKILL_FLOW <=", value, "doctorSkillFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorSkillFlowLike(String value) {
            addCriterion("DOCTOR_SKILL_FLOW like", value, "doctorSkillFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorSkillFlowNotLike(String value) {
            addCriterion("DOCTOR_SKILL_FLOW not like", value, "doctorSkillFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorSkillFlowIn(List<String> values) {
            addCriterion("DOCTOR_SKILL_FLOW in", values, "doctorSkillFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorSkillFlowNotIn(List<String> values) {
            addCriterion("DOCTOR_SKILL_FLOW not in", values, "doctorSkillFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorSkillFlowBetween(String value1, String value2) {
            addCriterion("DOCTOR_SKILL_FLOW between", value1, value2, "doctorSkillFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorSkillFlowNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_SKILL_FLOW not between", value1, value2, "doctorSkillFlow");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNull() {
            addCriterion("CITY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNotNull() {
            addCriterion("CITY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCityIdEqualTo(String value) {
            addCriterion("CITY_ID =", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotEqualTo(String value) {
            addCriterion("CITY_ID <>", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThan(String value) {
            addCriterion("CITY_ID >", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThanOrEqualTo(String value) {
            addCriterion("CITY_ID >=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThan(String value) {
            addCriterion("CITY_ID <", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThanOrEqualTo(String value) {
            addCriterion("CITY_ID <=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLike(String value) {
            addCriterion("CITY_ID like", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotLike(String value) {
            addCriterion("CITY_ID not like", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdIn(List<String> values) {
            addCriterion("CITY_ID in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotIn(List<String> values) {
            addCriterion("CITY_ID not in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdBetween(String value1, String value2) {
            addCriterion("CITY_ID between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotBetween(String value1, String value2) {
            addCriterion("CITY_ID not between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andTestIdIsNull() {
            addCriterion("TEST_ID is null");
            return (Criteria) this;
        }

        public Criteria andTestIdIsNotNull() {
            addCriterion("TEST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTestIdEqualTo(String value) {
            addCriterion("TEST_ID =", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotEqualTo(String value) {
            addCriterion("TEST_ID <>", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdGreaterThan(String value) {
            addCriterion("TEST_ID >", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdGreaterThanOrEqualTo(String value) {
            addCriterion("TEST_ID >=", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdLessThan(String value) {
            addCriterion("TEST_ID <", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdLessThanOrEqualTo(String value) {
            addCriterion("TEST_ID <=", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdLike(String value) {
            addCriterion("TEST_ID like", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotLike(String value) {
            addCriterion("TEST_ID not like", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdIn(List<String> values) {
            addCriterion("TEST_ID in", values, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotIn(List<String> values) {
            addCriterion("TEST_ID not in", values, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdBetween(String value1, String value2) {
            addCriterion("TEST_ID between", value1, value2, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotBetween(String value1, String value2) {
            addCriterion("TEST_ID not between", value1, value2, "testId");
            return (Criteria) this;
        }

        public Criteria andTestNameIsNull() {
            addCriterion("TEST_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTestNameIsNotNull() {
            addCriterion("TEST_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTestNameEqualTo(String value) {
            addCriterion("TEST_NAME =", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameNotEqualTo(String value) {
            addCriterion("TEST_NAME <>", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameGreaterThan(String value) {
            addCriterion("TEST_NAME >", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameGreaterThanOrEqualTo(String value) {
            addCriterion("TEST_NAME >=", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameLessThan(String value) {
            addCriterion("TEST_NAME <", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameLessThanOrEqualTo(String value) {
            addCriterion("TEST_NAME <=", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameLike(String value) {
            addCriterion("TEST_NAME like", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameNotLike(String value) {
            addCriterion("TEST_NAME not like", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameIn(List<String> values) {
            addCriterion("TEST_NAME in", values, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameNotIn(List<String> values) {
            addCriterion("TEST_NAME not in", values, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameBetween(String value1, String value2) {
            addCriterion("TEST_NAME between", value1, value2, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameNotBetween(String value1, String value2) {
            addCriterion("TEST_NAME not between", value1, value2, "testName");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowIsNull() {
            addCriterion("DOCTOR_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowIsNotNull() {
            addCriterion("DOCTOR_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowEqualTo(String value) {
            addCriterion("DOCTOR_FLOW =", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowNotEqualTo(String value) {
            addCriterion("DOCTOR_FLOW <>", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowGreaterThan(String value) {
            addCriterion("DOCTOR_FLOW >", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_FLOW >=", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowLessThan(String value) {
            addCriterion("DOCTOR_FLOW <", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_FLOW <=", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowLike(String value) {
            addCriterion("DOCTOR_FLOW like", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowNotLike(String value) {
            addCriterion("DOCTOR_FLOW not like", value, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowIn(List<String> values) {
            addCriterion("DOCTOR_FLOW in", values, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowNotIn(List<String> values) {
            addCriterion("DOCTOR_FLOW not in", values, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowBetween(String value1, String value2) {
            addCriterion("DOCTOR_FLOW between", value1, value2, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorFlowNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_FLOW not between", value1, value2, "doctorFlow");
            return (Criteria) this;
        }

        public Criteria andDoctorNameIsNull() {
            addCriterion("DOCTOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDoctorNameIsNotNull() {
            addCriterion("DOCTOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorNameEqualTo(String value) {
            addCriterion("DOCTOR_NAME =", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotEqualTo(String value) {
            addCriterion("DOCTOR_NAME <>", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameGreaterThan(String value) {
            addCriterion("DOCTOR_NAME >", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_NAME >=", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLessThan(String value) {
            addCriterion("DOCTOR_NAME <", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_NAME <=", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLike(String value) {
            addCriterion("DOCTOR_NAME like", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotLike(String value) {
            addCriterion("DOCTOR_NAME not like", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameIn(List<String> values) {
            addCriterion("DOCTOR_NAME in", values, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotIn(List<String> values) {
            addCriterion("DOCTOR_NAME not in", values, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameBetween(String value1, String value2) {
            addCriterion("DOCTOR_NAME between", value1, value2, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_NAME not between", value1, value2, "doctorName");
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

        public Criteria andSpeIdIsNull() {
            addCriterion("SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSpeIdIsNotNull() {
            addCriterion("SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSpeIdEqualTo(String value) {
            addCriterion("SPE_ID =", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotEqualTo(String value) {
            addCriterion("SPE_ID <>", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdGreaterThan(String value) {
            addCriterion("SPE_ID >", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_ID >=", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLessThan(String value) {
            addCriterion("SPE_ID <", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLessThanOrEqualTo(String value) {
            addCriterion("SPE_ID <=", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLike(String value) {
            addCriterion("SPE_ID like", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotLike(String value) {
            addCriterion("SPE_ID not like", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdIn(List<String> values) {
            addCriterion("SPE_ID in", values, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotIn(List<String> values) {
            addCriterion("SPE_ID not in", values, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdBetween(String value1, String value2) {
            addCriterion("SPE_ID between", value1, value2, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotBetween(String value1, String value2) {
            addCriterion("SPE_ID not between", value1, value2, "speId");
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

        public Criteria andTicketNumberIsNull() {
            addCriterion("TICKET_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andTicketNumberIsNotNull() {
            addCriterion("TICKET_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andTicketNumberEqualTo(String value) {
            addCriterion("TICKET_NUMBER =", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberNotEqualTo(String value) {
            addCriterion("TICKET_NUMBER <>", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberGreaterThan(String value) {
            addCriterion("TICKET_NUMBER >", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberGreaterThanOrEqualTo(String value) {
            addCriterion("TICKET_NUMBER >=", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberLessThan(String value) {
            addCriterion("TICKET_NUMBER <", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberLessThanOrEqualTo(String value) {
            addCriterion("TICKET_NUMBER <=", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberLike(String value) {
            addCriterion("TICKET_NUMBER like", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberNotLike(String value) {
            addCriterion("TICKET_NUMBER not like", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberIn(List<String> values) {
            addCriterion("TICKET_NUMBER in", values, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberNotIn(List<String> values) {
            addCriterion("TICKET_NUMBER not in", values, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberBetween(String value1, String value2) {
            addCriterion("TICKET_NUMBER between", value1, value2, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberNotBetween(String value1, String value2) {
            addCriterion("TICKET_NUMBER not between", value1, value2, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andSkillOrgFlowIsNull() {
            addCriterion("SKILL_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSkillOrgFlowIsNotNull() {
            addCriterion("SKILL_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSkillOrgFlowEqualTo(String value) {
            addCriterion("SKILL_ORG_FLOW =", value, "skillOrgFlow");
            return (Criteria) this;
        }

        public Criteria andSkillOrgFlowNotEqualTo(String value) {
            addCriterion("SKILL_ORG_FLOW <>", value, "skillOrgFlow");
            return (Criteria) this;
        }

        public Criteria andSkillOrgFlowGreaterThan(String value) {
            addCriterion("SKILL_ORG_FLOW >", value, "skillOrgFlow");
            return (Criteria) this;
        }

        public Criteria andSkillOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SKILL_ORG_FLOW >=", value, "skillOrgFlow");
            return (Criteria) this;
        }

        public Criteria andSkillOrgFlowLessThan(String value) {
            addCriterion("SKILL_ORG_FLOW <", value, "skillOrgFlow");
            return (Criteria) this;
        }

        public Criteria andSkillOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("SKILL_ORG_FLOW <=", value, "skillOrgFlow");
            return (Criteria) this;
        }

        public Criteria andSkillOrgFlowLike(String value) {
            addCriterion("SKILL_ORG_FLOW like", value, "skillOrgFlow");
            return (Criteria) this;
        }

        public Criteria andSkillOrgFlowNotLike(String value) {
            addCriterion("SKILL_ORG_FLOW not like", value, "skillOrgFlow");
            return (Criteria) this;
        }

        public Criteria andSkillOrgFlowIn(List<String> values) {
            addCriterion("SKILL_ORG_FLOW in", values, "skillOrgFlow");
            return (Criteria) this;
        }

        public Criteria andSkillOrgFlowNotIn(List<String> values) {
            addCriterion("SKILL_ORG_FLOW not in", values, "skillOrgFlow");
            return (Criteria) this;
        }

        public Criteria andSkillOrgFlowBetween(String value1, String value2) {
            addCriterion("SKILL_ORG_FLOW between", value1, value2, "skillOrgFlow");
            return (Criteria) this;
        }

        public Criteria andSkillOrgFlowNotBetween(String value1, String value2) {
            addCriterion("SKILL_ORG_FLOW not between", value1, value2, "skillOrgFlow");
            return (Criteria) this;
        }

        public Criteria andSkillOrgNameIsNull() {
            addCriterion("SKILL_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSkillOrgNameIsNotNull() {
            addCriterion("SKILL_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSkillOrgNameEqualTo(String value) {
            addCriterion("SKILL_ORG_NAME =", value, "skillOrgName");
            return (Criteria) this;
        }

        public Criteria andSkillOrgNameNotEqualTo(String value) {
            addCriterion("SKILL_ORG_NAME <>", value, "skillOrgName");
            return (Criteria) this;
        }

        public Criteria andSkillOrgNameGreaterThan(String value) {
            addCriterion("SKILL_ORG_NAME >", value, "skillOrgName");
            return (Criteria) this;
        }

        public Criteria andSkillOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("SKILL_ORG_NAME >=", value, "skillOrgName");
            return (Criteria) this;
        }

        public Criteria andSkillOrgNameLessThan(String value) {
            addCriterion("SKILL_ORG_NAME <", value, "skillOrgName");
            return (Criteria) this;
        }

        public Criteria andSkillOrgNameLessThanOrEqualTo(String value) {
            addCriterion("SKILL_ORG_NAME <=", value, "skillOrgName");
            return (Criteria) this;
        }

        public Criteria andSkillOrgNameLike(String value) {
            addCriterion("SKILL_ORG_NAME like", value, "skillOrgName");
            return (Criteria) this;
        }

        public Criteria andSkillOrgNameNotLike(String value) {
            addCriterion("SKILL_ORG_NAME not like", value, "skillOrgName");
            return (Criteria) this;
        }

        public Criteria andSkillOrgNameIn(List<String> values) {
            addCriterion("SKILL_ORG_NAME in", values, "skillOrgName");
            return (Criteria) this;
        }

        public Criteria andSkillOrgNameNotIn(List<String> values) {
            addCriterion("SKILL_ORG_NAME not in", values, "skillOrgName");
            return (Criteria) this;
        }

        public Criteria andSkillOrgNameBetween(String value1, String value2) {
            addCriterion("SKILL_ORG_NAME between", value1, value2, "skillOrgName");
            return (Criteria) this;
        }

        public Criteria andSkillOrgNameNotBetween(String value1, String value2) {
            addCriterion("SKILL_ORG_NAME not between", value1, value2, "skillOrgName");
            return (Criteria) this;
        }

        public Criteria andSkillTimeIsNull() {
            addCriterion("SKILL_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSkillTimeIsNotNull() {
            addCriterion("SKILL_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSkillTimeEqualTo(String value) {
            addCriterion("SKILL_TIME =", value, "skillTime");
            return (Criteria) this;
        }

        public Criteria andSkillTimeNotEqualTo(String value) {
            addCriterion("SKILL_TIME <>", value, "skillTime");
            return (Criteria) this;
        }

        public Criteria andSkillTimeGreaterThan(String value) {
            addCriterion("SKILL_TIME >", value, "skillTime");
            return (Criteria) this;
        }

        public Criteria andSkillTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SKILL_TIME >=", value, "skillTime");
            return (Criteria) this;
        }

        public Criteria andSkillTimeLessThan(String value) {
            addCriterion("SKILL_TIME <", value, "skillTime");
            return (Criteria) this;
        }

        public Criteria andSkillTimeLessThanOrEqualTo(String value) {
            addCriterion("SKILL_TIME <=", value, "skillTime");
            return (Criteria) this;
        }

        public Criteria andSkillTimeLike(String value) {
            addCriterion("SKILL_TIME like", value, "skillTime");
            return (Criteria) this;
        }

        public Criteria andSkillTimeNotLike(String value) {
            addCriterion("SKILL_TIME not like", value, "skillTime");
            return (Criteria) this;
        }

        public Criteria andSkillTimeIn(List<String> values) {
            addCriterion("SKILL_TIME in", values, "skillTime");
            return (Criteria) this;
        }

        public Criteria andSkillTimeNotIn(List<String> values) {
            addCriterion("SKILL_TIME not in", values, "skillTime");
            return (Criteria) this;
        }

        public Criteria andSkillTimeBetween(String value1, String value2) {
            addCriterion("SKILL_TIME between", value1, value2, "skillTime");
            return (Criteria) this;
        }

        public Criteria andSkillTimeNotBetween(String value1, String value2) {
            addCriterion("SKILL_TIME not between", value1, value2, "skillTime");
            return (Criteria) this;
        }

        public Criteria andSkillOrgPhoneIsNull() {
            addCriterion("SKILL_ORG_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andSkillOrgPhoneIsNotNull() {
            addCriterion("SKILL_ORG_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andSkillOrgPhoneEqualTo(String value) {
            addCriterion("SKILL_ORG_PHONE =", value, "skillOrgPhone");
            return (Criteria) this;
        }

        public Criteria andSkillOrgPhoneNotEqualTo(String value) {
            addCriterion("SKILL_ORG_PHONE <>", value, "skillOrgPhone");
            return (Criteria) this;
        }

        public Criteria andSkillOrgPhoneGreaterThan(String value) {
            addCriterion("SKILL_ORG_PHONE >", value, "skillOrgPhone");
            return (Criteria) this;
        }

        public Criteria andSkillOrgPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("SKILL_ORG_PHONE >=", value, "skillOrgPhone");
            return (Criteria) this;
        }

        public Criteria andSkillOrgPhoneLessThan(String value) {
            addCriterion("SKILL_ORG_PHONE <", value, "skillOrgPhone");
            return (Criteria) this;
        }

        public Criteria andSkillOrgPhoneLessThanOrEqualTo(String value) {
            addCriterion("SKILL_ORG_PHONE <=", value, "skillOrgPhone");
            return (Criteria) this;
        }

        public Criteria andSkillOrgPhoneLike(String value) {
            addCriterion("SKILL_ORG_PHONE like", value, "skillOrgPhone");
            return (Criteria) this;
        }

        public Criteria andSkillOrgPhoneNotLike(String value) {
            addCriterion("SKILL_ORG_PHONE not like", value, "skillOrgPhone");
            return (Criteria) this;
        }

        public Criteria andSkillOrgPhoneIn(List<String> values) {
            addCriterion("SKILL_ORG_PHONE in", values, "skillOrgPhone");
            return (Criteria) this;
        }

        public Criteria andSkillOrgPhoneNotIn(List<String> values) {
            addCriterion("SKILL_ORG_PHONE not in", values, "skillOrgPhone");
            return (Criteria) this;
        }

        public Criteria andSkillOrgPhoneBetween(String value1, String value2) {
            addCriterion("SKILL_ORG_PHONE between", value1, value2, "skillOrgPhone");
            return (Criteria) this;
        }

        public Criteria andSkillOrgPhoneNotBetween(String value1, String value2) {
            addCriterion("SKILL_ORG_PHONE not between", value1, value2, "skillOrgPhone");
            return (Criteria) this;
        }

        public Criteria andSkillNoteIsNull() {
            addCriterion("SKILL_NOTE is null");
            return (Criteria) this;
        }

        public Criteria andSkillNoteIsNotNull() {
            addCriterion("SKILL_NOTE is not null");
            return (Criteria) this;
        }

        public Criteria andSkillNoteEqualTo(String value) {
            addCriterion("SKILL_NOTE =", value, "skillNote");
            return (Criteria) this;
        }

        public Criteria andSkillNoteNotEqualTo(String value) {
            addCriterion("SKILL_NOTE <>", value, "skillNote");
            return (Criteria) this;
        }

        public Criteria andSkillNoteGreaterThan(String value) {
            addCriterion("SKILL_NOTE >", value, "skillNote");
            return (Criteria) this;
        }

        public Criteria andSkillNoteGreaterThanOrEqualTo(String value) {
            addCriterion("SKILL_NOTE >=", value, "skillNote");
            return (Criteria) this;
        }

        public Criteria andSkillNoteLessThan(String value) {
            addCriterion("SKILL_NOTE <", value, "skillNote");
            return (Criteria) this;
        }

        public Criteria andSkillNoteLessThanOrEqualTo(String value) {
            addCriterion("SKILL_NOTE <=", value, "skillNote");
            return (Criteria) this;
        }

        public Criteria andSkillNoteLike(String value) {
            addCriterion("SKILL_NOTE like", value, "skillNote");
            return (Criteria) this;
        }

        public Criteria andSkillNoteNotLike(String value) {
            addCriterion("SKILL_NOTE not like", value, "skillNote");
            return (Criteria) this;
        }

        public Criteria andSkillNoteIn(List<String> values) {
            addCriterion("SKILL_NOTE in", values, "skillNote");
            return (Criteria) this;
        }

        public Criteria andSkillNoteNotIn(List<String> values) {
            addCriterion("SKILL_NOTE not in", values, "skillNote");
            return (Criteria) this;
        }

        public Criteria andSkillNoteBetween(String value1, String value2) {
            addCriterion("SKILL_NOTE between", value1, value2, "skillNote");
            return (Criteria) this;
        }

        public Criteria andSkillNoteNotBetween(String value1, String value2) {
            addCriterion("SKILL_NOTE not between", value1, value2, "skillNote");
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