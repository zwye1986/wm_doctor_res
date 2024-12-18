package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ScresGraduationTicketExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ScresGraduationTicketExample() {
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

        public Criteria andApplyFlowIsNull() {
            addCriterion("APPLY_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApplyFlowIsNotNull() {
            addCriterion("APPLY_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApplyFlowEqualTo(String value) {
            addCriterion("APPLY_FLOW =", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowNotEqualTo(String value) {
            addCriterion("APPLY_FLOW <>", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowGreaterThan(String value) {
            addCriterion("APPLY_FLOW >", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_FLOW >=", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowLessThan(String value) {
            addCriterion("APPLY_FLOW <", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowLessThanOrEqualTo(String value) {
            addCriterion("APPLY_FLOW <=", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowLike(String value) {
            addCriterion("APPLY_FLOW like", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowNotLike(String value) {
            addCriterion("APPLY_FLOW not like", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowIn(List<String> values) {
            addCriterion("APPLY_FLOW in", values, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowNotIn(List<String> values) {
            addCriterion("APPLY_FLOW not in", values, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowBetween(String value1, String value2) {
            addCriterion("APPLY_FLOW between", value1, value2, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowNotBetween(String value1, String value2) {
            addCriterion("APPLY_FLOW not between", value1, value2, "applyFlow");
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

        public Criteria andGraduationYearIsNull() {
            addCriterion("GRADUATION_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andGraduationYearIsNotNull() {
            addCriterion("GRADUATION_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationYearEqualTo(String value) {
            addCriterion("GRADUATION_YEAR =", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearNotEqualTo(String value) {
            addCriterion("GRADUATION_YEAR <>", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearGreaterThan(String value) {
            addCriterion("GRADUATION_YEAR >", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATION_YEAR >=", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearLessThan(String value) {
            addCriterion("GRADUATION_YEAR <", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearLessThanOrEqualTo(String value) {
            addCriterion("GRADUATION_YEAR <=", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearLike(String value) {
            addCriterion("GRADUATION_YEAR like", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearNotLike(String value) {
            addCriterion("GRADUATION_YEAR not like", value, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearIn(List<String> values) {
            addCriterion("GRADUATION_YEAR in", values, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearNotIn(List<String> values) {
            addCriterion("GRADUATION_YEAR not in", values, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearBetween(String value1, String value2) {
            addCriterion("GRADUATION_YEAR between", value1, value2, "graduationYear");
            return (Criteria) this;
        }

        public Criteria andGraduationYearNotBetween(String value1, String value2) {
            addCriterion("GRADUATION_YEAR not between", value1, value2, "graduationYear");
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

        public Criteria andTheoryExamDateIsNull() {
            addCriterion("THEORY_EXAM_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTheoryExamDateIsNotNull() {
            addCriterion("THEORY_EXAM_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTheoryExamDateEqualTo(String value) {
            addCriterion("THEORY_EXAM_DATE =", value, "theoryExamDate");
            return (Criteria) this;
        }

        public Criteria andTheoryExamDateNotEqualTo(String value) {
            addCriterion("THEORY_EXAM_DATE <>", value, "theoryExamDate");
            return (Criteria) this;
        }

        public Criteria andTheoryExamDateGreaterThan(String value) {
            addCriterion("THEORY_EXAM_DATE >", value, "theoryExamDate");
            return (Criteria) this;
        }

        public Criteria andTheoryExamDateGreaterThanOrEqualTo(String value) {
            addCriterion("THEORY_EXAM_DATE >=", value, "theoryExamDate");
            return (Criteria) this;
        }

        public Criteria andTheoryExamDateLessThan(String value) {
            addCriterion("THEORY_EXAM_DATE <", value, "theoryExamDate");
            return (Criteria) this;
        }

        public Criteria andTheoryExamDateLessThanOrEqualTo(String value) {
            addCriterion("THEORY_EXAM_DATE <=", value, "theoryExamDate");
            return (Criteria) this;
        }

        public Criteria andTheoryExamDateLike(String value) {
            addCriterion("THEORY_EXAM_DATE like", value, "theoryExamDate");
            return (Criteria) this;
        }

        public Criteria andTheoryExamDateNotLike(String value) {
            addCriterion("THEORY_EXAM_DATE not like", value, "theoryExamDate");
            return (Criteria) this;
        }

        public Criteria andTheoryExamDateIn(List<String> values) {
            addCriterion("THEORY_EXAM_DATE in", values, "theoryExamDate");
            return (Criteria) this;
        }

        public Criteria andTheoryExamDateNotIn(List<String> values) {
            addCriterion("THEORY_EXAM_DATE not in", values, "theoryExamDate");
            return (Criteria) this;
        }

        public Criteria andTheoryExamDateBetween(String value1, String value2) {
            addCriterion("THEORY_EXAM_DATE between", value1, value2, "theoryExamDate");
            return (Criteria) this;
        }

        public Criteria andTheoryExamDateNotBetween(String value1, String value2) {
            addCriterion("THEORY_EXAM_DATE not between", value1, value2, "theoryExamDate");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1IsNull() {
            addCriterion("THEORY_EXAM_TIME1 is null");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1IsNotNull() {
            addCriterion("THEORY_EXAM_TIME1 is not null");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1EqualTo(String value) {
            addCriterion("THEORY_EXAM_TIME1 =", value, "theoryExamTime1");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1NotEqualTo(String value) {
            addCriterion("THEORY_EXAM_TIME1 <>", value, "theoryExamTime1");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1GreaterThan(String value) {
            addCriterion("THEORY_EXAM_TIME1 >", value, "theoryExamTime1");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1GreaterThanOrEqualTo(String value) {
            addCriterion("THEORY_EXAM_TIME1 >=", value, "theoryExamTime1");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1LessThan(String value) {
            addCriterion("THEORY_EXAM_TIME1 <", value, "theoryExamTime1");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1LessThanOrEqualTo(String value) {
            addCriterion("THEORY_EXAM_TIME1 <=", value, "theoryExamTime1");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1Like(String value) {
            addCriterion("THEORY_EXAM_TIME1 like", value, "theoryExamTime1");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1NotLike(String value) {
            addCriterion("THEORY_EXAM_TIME1 not like", value, "theoryExamTime1");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1In(List<String> values) {
            addCriterion("THEORY_EXAM_TIME1 in", values, "theoryExamTime1");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1NotIn(List<String> values) {
            addCriterion("THEORY_EXAM_TIME1 not in", values, "theoryExamTime1");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1Between(String value1, String value2) {
            addCriterion("THEORY_EXAM_TIME1 between", value1, value2, "theoryExamTime1");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1NotBetween(String value1, String value2) {
            addCriterion("THEORY_EXAM_TIME1 not between", value1, value2, "theoryExamTime1");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1BarchIsNull() {
            addCriterion("THEORY_EXAM_TIME1_BARCH is null");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1BarchIsNotNull() {
            addCriterion("THEORY_EXAM_TIME1_BARCH is not null");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1BarchEqualTo(String value) {
            addCriterion("THEORY_EXAM_TIME1_BARCH =", value, "theoryExamTime1Barch");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1BarchNotEqualTo(String value) {
            addCriterion("THEORY_EXAM_TIME1_BARCH <>", value, "theoryExamTime1Barch");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1BarchGreaterThan(String value) {
            addCriterion("THEORY_EXAM_TIME1_BARCH >", value, "theoryExamTime1Barch");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1BarchGreaterThanOrEqualTo(String value) {
            addCriterion("THEORY_EXAM_TIME1_BARCH >=", value, "theoryExamTime1Barch");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1BarchLessThan(String value) {
            addCriterion("THEORY_EXAM_TIME1_BARCH <", value, "theoryExamTime1Barch");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1BarchLessThanOrEqualTo(String value) {
            addCriterion("THEORY_EXAM_TIME1_BARCH <=", value, "theoryExamTime1Barch");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1BarchLike(String value) {
            addCriterion("THEORY_EXAM_TIME1_BARCH like", value, "theoryExamTime1Barch");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1BarchNotLike(String value) {
            addCriterion("THEORY_EXAM_TIME1_BARCH not like", value, "theoryExamTime1Barch");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1BarchIn(List<String> values) {
            addCriterion("THEORY_EXAM_TIME1_BARCH in", values, "theoryExamTime1Barch");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1BarchNotIn(List<String> values) {
            addCriterion("THEORY_EXAM_TIME1_BARCH not in", values, "theoryExamTime1Barch");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1BarchBetween(String value1, String value2) {
            addCriterion("THEORY_EXAM_TIME1_BARCH between", value1, value2, "theoryExamTime1Barch");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime1BarchNotBetween(String value1, String value2) {
            addCriterion("THEORY_EXAM_TIME1_BARCH not between", value1, value2, "theoryExamTime1Barch");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2IsNull() {
            addCriterion("THEORY_EXAM_TIME2 is null");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2IsNotNull() {
            addCriterion("THEORY_EXAM_TIME2 is not null");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2EqualTo(String value) {
            addCriterion("THEORY_EXAM_TIME2 =", value, "theoryExamTime2");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2NotEqualTo(String value) {
            addCriterion("THEORY_EXAM_TIME2 <>", value, "theoryExamTime2");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2GreaterThan(String value) {
            addCriterion("THEORY_EXAM_TIME2 >", value, "theoryExamTime2");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2GreaterThanOrEqualTo(String value) {
            addCriterion("THEORY_EXAM_TIME2 >=", value, "theoryExamTime2");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2LessThan(String value) {
            addCriterion("THEORY_EXAM_TIME2 <", value, "theoryExamTime2");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2LessThanOrEqualTo(String value) {
            addCriterion("THEORY_EXAM_TIME2 <=", value, "theoryExamTime2");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2Like(String value) {
            addCriterion("THEORY_EXAM_TIME2 like", value, "theoryExamTime2");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2NotLike(String value) {
            addCriterion("THEORY_EXAM_TIME2 not like", value, "theoryExamTime2");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2In(List<String> values) {
            addCriterion("THEORY_EXAM_TIME2 in", values, "theoryExamTime2");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2NotIn(List<String> values) {
            addCriterion("THEORY_EXAM_TIME2 not in", values, "theoryExamTime2");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2Between(String value1, String value2) {
            addCriterion("THEORY_EXAM_TIME2 between", value1, value2, "theoryExamTime2");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2NotBetween(String value1, String value2) {
            addCriterion("THEORY_EXAM_TIME2 not between", value1, value2, "theoryExamTime2");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2BarchIsNull() {
            addCriterion("THEORY_EXAM_TIME2_BARCH is null");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2BarchIsNotNull() {
            addCriterion("THEORY_EXAM_TIME2_BARCH is not null");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2BarchEqualTo(String value) {
            addCriterion("THEORY_EXAM_TIME2_BARCH =", value, "theoryExamTime2Barch");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2BarchNotEqualTo(String value) {
            addCriterion("THEORY_EXAM_TIME2_BARCH <>", value, "theoryExamTime2Barch");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2BarchGreaterThan(String value) {
            addCriterion("THEORY_EXAM_TIME2_BARCH >", value, "theoryExamTime2Barch");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2BarchGreaterThanOrEqualTo(String value) {
            addCriterion("THEORY_EXAM_TIME2_BARCH >=", value, "theoryExamTime2Barch");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2BarchLessThan(String value) {
            addCriterion("THEORY_EXAM_TIME2_BARCH <", value, "theoryExamTime2Barch");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2BarchLessThanOrEqualTo(String value) {
            addCriterion("THEORY_EXAM_TIME2_BARCH <=", value, "theoryExamTime2Barch");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2BarchLike(String value) {
            addCriterion("THEORY_EXAM_TIME2_BARCH like", value, "theoryExamTime2Barch");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2BarchNotLike(String value) {
            addCriterion("THEORY_EXAM_TIME2_BARCH not like", value, "theoryExamTime2Barch");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2BarchIn(List<String> values) {
            addCriterion("THEORY_EXAM_TIME2_BARCH in", values, "theoryExamTime2Barch");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2BarchNotIn(List<String> values) {
            addCriterion("THEORY_EXAM_TIME2_BARCH not in", values, "theoryExamTime2Barch");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2BarchBetween(String value1, String value2) {
            addCriterion("THEORY_EXAM_TIME2_BARCH between", value1, value2, "theoryExamTime2Barch");
            return (Criteria) this;
        }

        public Criteria andTheoryExamTime2BarchNotBetween(String value1, String value2) {
            addCriterion("THEORY_EXAM_TIME2_BARCH not between", value1, value2, "theoryExamTime2Barch");
            return (Criteria) this;
        }

        public Criteria andSkillExamDateIsNull() {
            addCriterion("SKILL_EXAM_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSkillExamDateIsNotNull() {
            addCriterion("SKILL_EXAM_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSkillExamDateEqualTo(String value) {
            addCriterion("SKILL_EXAM_DATE =", value, "skillExamDate");
            return (Criteria) this;
        }

        public Criteria andSkillExamDateNotEqualTo(String value) {
            addCriterion("SKILL_EXAM_DATE <>", value, "skillExamDate");
            return (Criteria) this;
        }

        public Criteria andSkillExamDateGreaterThan(String value) {
            addCriterion("SKILL_EXAM_DATE >", value, "skillExamDate");
            return (Criteria) this;
        }

        public Criteria andSkillExamDateGreaterThanOrEqualTo(String value) {
            addCriterion("SKILL_EXAM_DATE >=", value, "skillExamDate");
            return (Criteria) this;
        }

        public Criteria andSkillExamDateLessThan(String value) {
            addCriterion("SKILL_EXAM_DATE <", value, "skillExamDate");
            return (Criteria) this;
        }

        public Criteria andSkillExamDateLessThanOrEqualTo(String value) {
            addCriterion("SKILL_EXAM_DATE <=", value, "skillExamDate");
            return (Criteria) this;
        }

        public Criteria andSkillExamDateLike(String value) {
            addCriterion("SKILL_EXAM_DATE like", value, "skillExamDate");
            return (Criteria) this;
        }

        public Criteria andSkillExamDateNotLike(String value) {
            addCriterion("SKILL_EXAM_DATE not like", value, "skillExamDate");
            return (Criteria) this;
        }

        public Criteria andSkillExamDateIn(List<String> values) {
            addCriterion("SKILL_EXAM_DATE in", values, "skillExamDate");
            return (Criteria) this;
        }

        public Criteria andSkillExamDateNotIn(List<String> values) {
            addCriterion("SKILL_EXAM_DATE not in", values, "skillExamDate");
            return (Criteria) this;
        }

        public Criteria andSkillExamDateBetween(String value1, String value2) {
            addCriterion("SKILL_EXAM_DATE between", value1, value2, "skillExamDate");
            return (Criteria) this;
        }

        public Criteria andSkillExamDateNotBetween(String value1, String value2) {
            addCriterion("SKILL_EXAM_DATE not between", value1, value2, "skillExamDate");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1IsNull() {
            addCriterion("SKILL_EXAM_TIME1 is null");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1IsNotNull() {
            addCriterion("SKILL_EXAM_TIME1 is not null");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1EqualTo(String value) {
            addCriterion("SKILL_EXAM_TIME1 =", value, "skillExamTime1");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1NotEqualTo(String value) {
            addCriterion("SKILL_EXAM_TIME1 <>", value, "skillExamTime1");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1GreaterThan(String value) {
            addCriterion("SKILL_EXAM_TIME1 >", value, "skillExamTime1");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1GreaterThanOrEqualTo(String value) {
            addCriterion("SKILL_EXAM_TIME1 >=", value, "skillExamTime1");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1LessThan(String value) {
            addCriterion("SKILL_EXAM_TIME1 <", value, "skillExamTime1");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1LessThanOrEqualTo(String value) {
            addCriterion("SKILL_EXAM_TIME1 <=", value, "skillExamTime1");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1Like(String value) {
            addCriterion("SKILL_EXAM_TIME1 like", value, "skillExamTime1");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1NotLike(String value) {
            addCriterion("SKILL_EXAM_TIME1 not like", value, "skillExamTime1");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1In(List<String> values) {
            addCriterion("SKILL_EXAM_TIME1 in", values, "skillExamTime1");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1NotIn(List<String> values) {
            addCriterion("SKILL_EXAM_TIME1 not in", values, "skillExamTime1");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1Between(String value1, String value2) {
            addCriterion("SKILL_EXAM_TIME1 between", value1, value2, "skillExamTime1");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1NotBetween(String value1, String value2) {
            addCriterion("SKILL_EXAM_TIME1 not between", value1, value2, "skillExamTime1");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1BarchIsNull() {
            addCriterion("SKILL_EXAM_TIME1_BARCH is null");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1BarchIsNotNull() {
            addCriterion("SKILL_EXAM_TIME1_BARCH is not null");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1BarchEqualTo(String value) {
            addCriterion("SKILL_EXAM_TIME1_BARCH =", value, "skillExamTime1Barch");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1BarchNotEqualTo(String value) {
            addCriterion("SKILL_EXAM_TIME1_BARCH <>", value, "skillExamTime1Barch");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1BarchGreaterThan(String value) {
            addCriterion("SKILL_EXAM_TIME1_BARCH >", value, "skillExamTime1Barch");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1BarchGreaterThanOrEqualTo(String value) {
            addCriterion("SKILL_EXAM_TIME1_BARCH >=", value, "skillExamTime1Barch");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1BarchLessThan(String value) {
            addCriterion("SKILL_EXAM_TIME1_BARCH <", value, "skillExamTime1Barch");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1BarchLessThanOrEqualTo(String value) {
            addCriterion("SKILL_EXAM_TIME1_BARCH <=", value, "skillExamTime1Barch");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1BarchLike(String value) {
            addCriterion("SKILL_EXAM_TIME1_BARCH like", value, "skillExamTime1Barch");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1BarchNotLike(String value) {
            addCriterion("SKILL_EXAM_TIME1_BARCH not like", value, "skillExamTime1Barch");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1BarchIn(List<String> values) {
            addCriterion("SKILL_EXAM_TIME1_BARCH in", values, "skillExamTime1Barch");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1BarchNotIn(List<String> values) {
            addCriterion("SKILL_EXAM_TIME1_BARCH not in", values, "skillExamTime1Barch");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1BarchBetween(String value1, String value2) {
            addCriterion("SKILL_EXAM_TIME1_BARCH between", value1, value2, "skillExamTime1Barch");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime1BarchNotBetween(String value1, String value2) {
            addCriterion("SKILL_EXAM_TIME1_BARCH not between", value1, value2, "skillExamTime1Barch");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2IsNull() {
            addCriterion("SKILL_EXAM_TIME2 is null");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2IsNotNull() {
            addCriterion("SKILL_EXAM_TIME2 is not null");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2EqualTo(String value) {
            addCriterion("SKILL_EXAM_TIME2 =", value, "skillExamTime2");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2NotEqualTo(String value) {
            addCriterion("SKILL_EXAM_TIME2 <>", value, "skillExamTime2");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2GreaterThan(String value) {
            addCriterion("SKILL_EXAM_TIME2 >", value, "skillExamTime2");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2GreaterThanOrEqualTo(String value) {
            addCriterion("SKILL_EXAM_TIME2 >=", value, "skillExamTime2");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2LessThan(String value) {
            addCriterion("SKILL_EXAM_TIME2 <", value, "skillExamTime2");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2LessThanOrEqualTo(String value) {
            addCriterion("SKILL_EXAM_TIME2 <=", value, "skillExamTime2");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2Like(String value) {
            addCriterion("SKILL_EXAM_TIME2 like", value, "skillExamTime2");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2NotLike(String value) {
            addCriterion("SKILL_EXAM_TIME2 not like", value, "skillExamTime2");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2In(List<String> values) {
            addCriterion("SKILL_EXAM_TIME2 in", values, "skillExamTime2");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2NotIn(List<String> values) {
            addCriterion("SKILL_EXAM_TIME2 not in", values, "skillExamTime2");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2Between(String value1, String value2) {
            addCriterion("SKILL_EXAM_TIME2 between", value1, value2, "skillExamTime2");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2NotBetween(String value1, String value2) {
            addCriterion("SKILL_EXAM_TIME2 not between", value1, value2, "skillExamTime2");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2BarchIsNull() {
            addCriterion("SKILL_EXAM_TIME2_BARCH is null");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2BarchIsNotNull() {
            addCriterion("SKILL_EXAM_TIME2_BARCH is not null");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2BarchEqualTo(String value) {
            addCriterion("SKILL_EXAM_TIME2_BARCH =", value, "skillExamTime2Barch");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2BarchNotEqualTo(String value) {
            addCriterion("SKILL_EXAM_TIME2_BARCH <>", value, "skillExamTime2Barch");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2BarchGreaterThan(String value) {
            addCriterion("SKILL_EXAM_TIME2_BARCH >", value, "skillExamTime2Barch");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2BarchGreaterThanOrEqualTo(String value) {
            addCriterion("SKILL_EXAM_TIME2_BARCH >=", value, "skillExamTime2Barch");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2BarchLessThan(String value) {
            addCriterion("SKILL_EXAM_TIME2_BARCH <", value, "skillExamTime2Barch");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2BarchLessThanOrEqualTo(String value) {
            addCriterion("SKILL_EXAM_TIME2_BARCH <=", value, "skillExamTime2Barch");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2BarchLike(String value) {
            addCriterion("SKILL_EXAM_TIME2_BARCH like", value, "skillExamTime2Barch");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2BarchNotLike(String value) {
            addCriterion("SKILL_EXAM_TIME2_BARCH not like", value, "skillExamTime2Barch");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2BarchIn(List<String> values) {
            addCriterion("SKILL_EXAM_TIME2_BARCH in", values, "skillExamTime2Barch");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2BarchNotIn(List<String> values) {
            addCriterion("SKILL_EXAM_TIME2_BARCH not in", values, "skillExamTime2Barch");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2BarchBetween(String value1, String value2) {
            addCriterion("SKILL_EXAM_TIME2_BARCH between", value1, value2, "skillExamTime2Barch");
            return (Criteria) this;
        }

        public Criteria andSkillExamTime2BarchNotBetween(String value1, String value2) {
            addCriterion("SKILL_EXAM_TIME2_BARCH not between", value1, value2, "skillExamTime2Barch");
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

        public Criteria andOrgAddressIsNull() {
            addCriterion("ORG_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andOrgAddressIsNotNull() {
            addCriterion("ORG_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andOrgAddressEqualTo(String value) {
            addCriterion("ORG_ADDRESS =", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressNotEqualTo(String value) {
            addCriterion("ORG_ADDRESS <>", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressGreaterThan(String value) {
            addCriterion("ORG_ADDRESS >", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_ADDRESS >=", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressLessThan(String value) {
            addCriterion("ORG_ADDRESS <", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressLessThanOrEqualTo(String value) {
            addCriterion("ORG_ADDRESS <=", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressLike(String value) {
            addCriterion("ORG_ADDRESS like", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressNotLike(String value) {
            addCriterion("ORG_ADDRESS not like", value, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressIn(List<String> values) {
            addCriterion("ORG_ADDRESS in", values, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressNotIn(List<String> values) {
            addCriterion("ORG_ADDRESS not in", values, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressBetween(String value1, String value2) {
            addCriterion("ORG_ADDRESS between", value1, value2, "orgAddress");
            return (Criteria) this;
        }

        public Criteria andOrgAddressNotBetween(String value1, String value2) {
            addCriterion("ORG_ADDRESS not between", value1, value2, "orgAddress");
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

        public Criteria andUserImgIsNull() {
            addCriterion("USER_IMG is null");
            return (Criteria) this;
        }

        public Criteria andUserImgIsNotNull() {
            addCriterion("USER_IMG is not null");
            return (Criteria) this;
        }

        public Criteria andUserImgEqualTo(String value) {
            addCriterion("USER_IMG =", value, "userImg");
            return (Criteria) this;
        }

        public Criteria andUserImgNotEqualTo(String value) {
            addCriterion("USER_IMG <>", value, "userImg");
            return (Criteria) this;
        }

        public Criteria andUserImgGreaterThan(String value) {
            addCriterion("USER_IMG >", value, "userImg");
            return (Criteria) this;
        }

        public Criteria andUserImgGreaterThanOrEqualTo(String value) {
            addCriterion("USER_IMG >=", value, "userImg");
            return (Criteria) this;
        }

        public Criteria andUserImgLessThan(String value) {
            addCriterion("USER_IMG <", value, "userImg");
            return (Criteria) this;
        }

        public Criteria andUserImgLessThanOrEqualTo(String value) {
            addCriterion("USER_IMG <=", value, "userImg");
            return (Criteria) this;
        }

        public Criteria andUserImgLike(String value) {
            addCriterion("USER_IMG like", value, "userImg");
            return (Criteria) this;
        }

        public Criteria andUserImgNotLike(String value) {
            addCriterion("USER_IMG not like", value, "userImg");
            return (Criteria) this;
        }

        public Criteria andUserImgIn(List<String> values) {
            addCriterion("USER_IMG in", values, "userImg");
            return (Criteria) this;
        }

        public Criteria andUserImgNotIn(List<String> values) {
            addCriterion("USER_IMG not in", values, "userImg");
            return (Criteria) this;
        }

        public Criteria andUserImgBetween(String value1, String value2) {
            addCriterion("USER_IMG between", value1, value2, "userImg");
            return (Criteria) this;
        }

        public Criteria andUserImgNotBetween(String value1, String value2) {
            addCriterion("USER_IMG not between", value1, value2, "userImg");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgNameIsNull() {
            addCriterion("THEORY_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgNameIsNotNull() {
            addCriterion("THEORY_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgNameEqualTo(String value) {
            addCriterion("THEORY_ORG_NAME =", value, "theoryOrgName");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgNameNotEqualTo(String value) {
            addCriterion("THEORY_ORG_NAME <>", value, "theoryOrgName");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgNameGreaterThan(String value) {
            addCriterion("THEORY_ORG_NAME >", value, "theoryOrgName");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("THEORY_ORG_NAME >=", value, "theoryOrgName");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgNameLessThan(String value) {
            addCriterion("THEORY_ORG_NAME <", value, "theoryOrgName");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgNameLessThanOrEqualTo(String value) {
            addCriterion("THEORY_ORG_NAME <=", value, "theoryOrgName");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgNameLike(String value) {
            addCriterion("THEORY_ORG_NAME like", value, "theoryOrgName");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgNameNotLike(String value) {
            addCriterion("THEORY_ORG_NAME not like", value, "theoryOrgName");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgNameIn(List<String> values) {
            addCriterion("THEORY_ORG_NAME in", values, "theoryOrgName");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgNameNotIn(List<String> values) {
            addCriterion("THEORY_ORG_NAME not in", values, "theoryOrgName");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgNameBetween(String value1, String value2) {
            addCriterion("THEORY_ORG_NAME between", value1, value2, "theoryOrgName");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgNameNotBetween(String value1, String value2) {
            addCriterion("THEORY_ORG_NAME not between", value1, value2, "theoryOrgName");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgAddressIsNull() {
            addCriterion("THEORY_ORG_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgAddressIsNotNull() {
            addCriterion("THEORY_ORG_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgAddressEqualTo(String value) {
            addCriterion("THEORY_ORG_ADDRESS =", value, "theoryOrgAddress");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgAddressNotEqualTo(String value) {
            addCriterion("THEORY_ORG_ADDRESS <>", value, "theoryOrgAddress");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgAddressGreaterThan(String value) {
            addCriterion("THEORY_ORG_ADDRESS >", value, "theoryOrgAddress");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgAddressGreaterThanOrEqualTo(String value) {
            addCriterion("THEORY_ORG_ADDRESS >=", value, "theoryOrgAddress");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgAddressLessThan(String value) {
            addCriterion("THEORY_ORG_ADDRESS <", value, "theoryOrgAddress");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgAddressLessThanOrEqualTo(String value) {
            addCriterion("THEORY_ORG_ADDRESS <=", value, "theoryOrgAddress");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgAddressLike(String value) {
            addCriterion("THEORY_ORG_ADDRESS like", value, "theoryOrgAddress");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgAddressNotLike(String value) {
            addCriterion("THEORY_ORG_ADDRESS not like", value, "theoryOrgAddress");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgAddressIn(List<String> values) {
            addCriterion("THEORY_ORG_ADDRESS in", values, "theoryOrgAddress");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgAddressNotIn(List<String> values) {
            addCriterion("THEORY_ORG_ADDRESS not in", values, "theoryOrgAddress");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgAddressBetween(String value1, String value2) {
            addCriterion("THEORY_ORG_ADDRESS between", value1, value2, "theoryOrgAddress");
            return (Criteria) this;
        }

        public Criteria andTheoryOrgAddressNotBetween(String value1, String value2) {
            addCriterion("THEORY_ORG_ADDRESS not between", value1, value2, "theoryOrgAddress");
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