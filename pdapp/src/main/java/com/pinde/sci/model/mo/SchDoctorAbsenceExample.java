package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class SchDoctorAbsenceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SchDoctorAbsenceExample() {
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

        public Criteria andAbsenceFlowIsNull() {
            addCriterion("ABSENCE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAbsenceFlowIsNotNull() {
            addCriterion("ABSENCE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAbsenceFlowEqualTo(String value) {
            addCriterion("ABSENCE_FLOW =", value, "absenceFlow");
            return (Criteria) this;
        }

        public Criteria andAbsenceFlowNotEqualTo(String value) {
            addCriterion("ABSENCE_FLOW <>", value, "absenceFlow");
            return (Criteria) this;
        }

        public Criteria andAbsenceFlowGreaterThan(String value) {
            addCriterion("ABSENCE_FLOW >", value, "absenceFlow");
            return (Criteria) this;
        }

        public Criteria andAbsenceFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ABSENCE_FLOW >=", value, "absenceFlow");
            return (Criteria) this;
        }

        public Criteria andAbsenceFlowLessThan(String value) {
            addCriterion("ABSENCE_FLOW <", value, "absenceFlow");
            return (Criteria) this;
        }

        public Criteria andAbsenceFlowLessThanOrEqualTo(String value) {
            addCriterion("ABSENCE_FLOW <=", value, "absenceFlow");
            return (Criteria) this;
        }

        public Criteria andAbsenceFlowLike(String value) {
            addCriterion("ABSENCE_FLOW like", value, "absenceFlow");
            return (Criteria) this;
        }

        public Criteria andAbsenceFlowNotLike(String value) {
            addCriterion("ABSENCE_FLOW not like", value, "absenceFlow");
            return (Criteria) this;
        }

        public Criteria andAbsenceFlowIn(List<String> values) {
            addCriterion("ABSENCE_FLOW in", values, "absenceFlow");
            return (Criteria) this;
        }

        public Criteria andAbsenceFlowNotIn(List<String> values) {
            addCriterion("ABSENCE_FLOW not in", values, "absenceFlow");
            return (Criteria) this;
        }

        public Criteria andAbsenceFlowBetween(String value1, String value2) {
            addCriterion("ABSENCE_FLOW between", value1, value2, "absenceFlow");
            return (Criteria) this;
        }

        public Criteria andAbsenceFlowNotBetween(String value1, String value2) {
            addCriterion("ABSENCE_FLOW not between", value1, value2, "absenceFlow");
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

        public Criteria andSessionNumberIsNull() {
            addCriterion("SESSION_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIsNotNull() {
            addCriterion("SESSION_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andSessionNumberEqualTo(String value) {
            addCriterion("SESSION_NUMBER =", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotEqualTo(String value) {
            addCriterion("SESSION_NUMBER <>", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberGreaterThan(String value) {
            addCriterion("SESSION_NUMBER >", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberGreaterThanOrEqualTo(String value) {
            addCriterion("SESSION_NUMBER >=", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLessThan(String value) {
            addCriterion("SESSION_NUMBER <", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLessThanOrEqualTo(String value) {
            addCriterion("SESSION_NUMBER <=", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLike(String value) {
            addCriterion("SESSION_NUMBER like", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotLike(String value) {
            addCriterion("SESSION_NUMBER not like", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIn(List<String> values) {
            addCriterion("SESSION_NUMBER in", values, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotIn(List<String> values) {
            addCriterion("SESSION_NUMBER not in", values, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberBetween(String value1, String value2) {
            addCriterion("SESSION_NUMBER between", value1, value2, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotBetween(String value1, String value2) {
            addCriterion("SESSION_NUMBER not between", value1, value2, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdIsNull() {
            addCriterion("TRAINING_SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdIsNotNull() {
            addCriterion("TRAINING_SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdEqualTo(String value) {
            addCriterion("TRAINING_SPE_ID =", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdNotEqualTo(String value) {
            addCriterion("TRAINING_SPE_ID <>", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdGreaterThan(String value) {
            addCriterion("TRAINING_SPE_ID >", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_SPE_ID >=", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdLessThan(String value) {
            addCriterion("TRAINING_SPE_ID <", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_SPE_ID <=", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdLike(String value) {
            addCriterion("TRAINING_SPE_ID like", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdNotLike(String value) {
            addCriterion("TRAINING_SPE_ID not like", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdIn(List<String> values) {
            addCriterion("TRAINING_SPE_ID in", values, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdNotIn(List<String> values) {
            addCriterion("TRAINING_SPE_ID not in", values, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdBetween(String value1, String value2) {
            addCriterion("TRAINING_SPE_ID between", value1, value2, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdNotBetween(String value1, String value2) {
            addCriterion("TRAINING_SPE_ID not between", value1, value2, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameIsNull() {
            addCriterion("TRAINING_SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameIsNotNull() {
            addCriterion("TRAINING_SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameEqualTo(String value) {
            addCriterion("TRAINING_SPE_NAME =", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameNotEqualTo(String value) {
            addCriterion("TRAINING_SPE_NAME <>", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameGreaterThan(String value) {
            addCriterion("TRAINING_SPE_NAME >", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_SPE_NAME >=", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameLessThan(String value) {
            addCriterion("TRAINING_SPE_NAME <", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_SPE_NAME <=", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameLike(String value) {
            addCriterion("TRAINING_SPE_NAME like", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameNotLike(String value) {
            addCriterion("TRAINING_SPE_NAME not like", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameIn(List<String> values) {
            addCriterion("TRAINING_SPE_NAME in", values, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameNotIn(List<String> values) {
            addCriterion("TRAINING_SPE_NAME not in", values, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameBetween(String value1, String value2) {
            addCriterion("TRAINING_SPE_NAME between", value1, value2, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameNotBetween(String value1, String value2) {
            addCriterion("TRAINING_SPE_NAME not between", value1, value2, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(String value) {
            addCriterion("START_DATE =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(String value) {
            addCriterion("START_DATE <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(String value) {
            addCriterion("START_DATE >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("START_DATE >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(String value) {
            addCriterion("START_DATE <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(String value) {
            addCriterion("START_DATE <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLike(String value) {
            addCriterion("START_DATE like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotLike(String value) {
            addCriterion("START_DATE not like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<String> values) {
            addCriterion("START_DATE in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<String> values) {
            addCriterion("START_DATE not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(String value1, String value2) {
            addCriterion("START_DATE between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(String value1, String value2) {
            addCriterion("START_DATE not between", value1, value2, "startDate");
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

        public Criteria andIntervalDayIsNull() {
            addCriterion("INTERVAL_DAY is null");
            return (Criteria) this;
        }

        public Criteria andIntervalDayIsNotNull() {
            addCriterion("INTERVAL_DAY is not null");
            return (Criteria) this;
        }

        public Criteria andIntervalDayEqualTo(String value) {
            addCriterion("INTERVAL_DAY =", value, "intervalDay");
            return (Criteria) this;
        }

        public Criteria andIntervalDayNotEqualTo(String value) {
            addCriterion("INTERVAL_DAY <>", value, "intervalDay");
            return (Criteria) this;
        }

        public Criteria andIntervalDayGreaterThan(String value) {
            addCriterion("INTERVAL_DAY >", value, "intervalDay");
            return (Criteria) this;
        }

        public Criteria andIntervalDayGreaterThanOrEqualTo(String value) {
            addCriterion("INTERVAL_DAY >=", value, "intervalDay");
            return (Criteria) this;
        }

        public Criteria andIntervalDayLessThan(String value) {
            addCriterion("INTERVAL_DAY <", value, "intervalDay");
            return (Criteria) this;
        }

        public Criteria andIntervalDayLessThanOrEqualTo(String value) {
            addCriterion("INTERVAL_DAY <=", value, "intervalDay");
            return (Criteria) this;
        }

        public Criteria andIntervalDayLike(String value) {
            addCriterion("INTERVAL_DAY like", value, "intervalDay");
            return (Criteria) this;
        }

        public Criteria andIntervalDayNotLike(String value) {
            addCriterion("INTERVAL_DAY not like", value, "intervalDay");
            return (Criteria) this;
        }

        public Criteria andIntervalDayIn(List<String> values) {
            addCriterion("INTERVAL_DAY in", values, "intervalDay");
            return (Criteria) this;
        }

        public Criteria andIntervalDayNotIn(List<String> values) {
            addCriterion("INTERVAL_DAY not in", values, "intervalDay");
            return (Criteria) this;
        }

        public Criteria andIntervalDayBetween(String value1, String value2) {
            addCriterion("INTERVAL_DAY between", value1, value2, "intervalDay");
            return (Criteria) this;
        }

        public Criteria andIntervalDayNotBetween(String value1, String value2) {
            addCriterion("INTERVAL_DAY not between", value1, value2, "intervalDay");
            return (Criteria) this;
        }

        public Criteria andAbsenceResonIsNull() {
            addCriterion("ABSENCE_RESON is null");
            return (Criteria) this;
        }

        public Criteria andAbsenceResonIsNotNull() {
            addCriterion("ABSENCE_RESON is not null");
            return (Criteria) this;
        }

        public Criteria andAbsenceResonEqualTo(String value) {
            addCriterion("ABSENCE_RESON =", value, "absenceReson");
            return (Criteria) this;
        }

        public Criteria andAbsenceResonNotEqualTo(String value) {
            addCriterion("ABSENCE_RESON <>", value, "absenceReson");
            return (Criteria) this;
        }

        public Criteria andAbsenceResonGreaterThan(String value) {
            addCriterion("ABSENCE_RESON >", value, "absenceReson");
            return (Criteria) this;
        }

        public Criteria andAbsenceResonGreaterThanOrEqualTo(String value) {
            addCriterion("ABSENCE_RESON >=", value, "absenceReson");
            return (Criteria) this;
        }

        public Criteria andAbsenceResonLessThan(String value) {
            addCriterion("ABSENCE_RESON <", value, "absenceReson");
            return (Criteria) this;
        }

        public Criteria andAbsenceResonLessThanOrEqualTo(String value) {
            addCriterion("ABSENCE_RESON <=", value, "absenceReson");
            return (Criteria) this;
        }

        public Criteria andAbsenceResonLike(String value) {
            addCriterion("ABSENCE_RESON like", value, "absenceReson");
            return (Criteria) this;
        }

        public Criteria andAbsenceResonNotLike(String value) {
            addCriterion("ABSENCE_RESON not like", value, "absenceReson");
            return (Criteria) this;
        }

        public Criteria andAbsenceResonIn(List<String> values) {
            addCriterion("ABSENCE_RESON in", values, "absenceReson");
            return (Criteria) this;
        }

        public Criteria andAbsenceResonNotIn(List<String> values) {
            addCriterion("ABSENCE_RESON not in", values, "absenceReson");
            return (Criteria) this;
        }

        public Criteria andAbsenceResonBetween(String value1, String value2) {
            addCriterion("ABSENCE_RESON between", value1, value2, "absenceReson");
            return (Criteria) this;
        }

        public Criteria andAbsenceResonNotBetween(String value1, String value2) {
            addCriterion("ABSENCE_RESON not between", value1, value2, "absenceReson");
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

        public Criteria andDoctorCategoryIdIsNull() {
            addCriterion("DOCTOR_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdIsNotNull() {
            addCriterion("DOCTOR_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdEqualTo(String value) {
            addCriterion("DOCTOR_CATEGORY_ID =", value, "doctorCategoryId");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdNotEqualTo(String value) {
            addCriterion("DOCTOR_CATEGORY_ID <>", value, "doctorCategoryId");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdGreaterThan(String value) {
            addCriterion("DOCTOR_CATEGORY_ID >", value, "doctorCategoryId");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_CATEGORY_ID >=", value, "doctorCategoryId");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdLessThan(String value) {
            addCriterion("DOCTOR_CATEGORY_ID <", value, "doctorCategoryId");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_CATEGORY_ID <=", value, "doctorCategoryId");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdLike(String value) {
            addCriterion("DOCTOR_CATEGORY_ID like", value, "doctorCategoryId");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdNotLike(String value) {
            addCriterion("DOCTOR_CATEGORY_ID not like", value, "doctorCategoryId");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdIn(List<String> values) {
            addCriterion("DOCTOR_CATEGORY_ID in", values, "doctorCategoryId");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdNotIn(List<String> values) {
            addCriterion("DOCTOR_CATEGORY_ID not in", values, "doctorCategoryId");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdBetween(String value1, String value2) {
            addCriterion("DOCTOR_CATEGORY_ID between", value1, value2, "doctorCategoryId");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryIdNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_CATEGORY_ID not between", value1, value2, "doctorCategoryId");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameIsNull() {
            addCriterion("DOCTOR_CATEGORY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameIsNotNull() {
            addCriterion("DOCTOR_CATEGORY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameEqualTo(String value) {
            addCriterion("DOCTOR_CATEGORY_NAME =", value, "doctorCategoryName");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameNotEqualTo(String value) {
            addCriterion("DOCTOR_CATEGORY_NAME <>", value, "doctorCategoryName");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameGreaterThan(String value) {
            addCriterion("DOCTOR_CATEGORY_NAME >", value, "doctorCategoryName");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_CATEGORY_NAME >=", value, "doctorCategoryName");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameLessThan(String value) {
            addCriterion("DOCTOR_CATEGORY_NAME <", value, "doctorCategoryName");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_CATEGORY_NAME <=", value, "doctorCategoryName");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameLike(String value) {
            addCriterion("DOCTOR_CATEGORY_NAME like", value, "doctorCategoryName");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameNotLike(String value) {
            addCriterion("DOCTOR_CATEGORY_NAME not like", value, "doctorCategoryName");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameIn(List<String> values) {
            addCriterion("DOCTOR_CATEGORY_NAME in", values, "doctorCategoryName");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameNotIn(List<String> values) {
            addCriterion("DOCTOR_CATEGORY_NAME not in", values, "doctorCategoryName");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameBetween(String value1, String value2) {
            addCriterion("DOCTOR_CATEGORY_NAME between", value1, value2, "doctorCategoryName");
            return (Criteria) this;
        }

        public Criteria andDoctorCategoryNameNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_CATEGORY_NAME not between", value1, value2, "doctorCategoryName");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowIsNull() {
            addCriterion("SCH_DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowIsNotNull() {
            addCriterion("SCH_DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowEqualTo(String value) {
            addCriterion("SCH_DEPT_FLOW =", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowNotEqualTo(String value) {
            addCriterion("SCH_DEPT_FLOW <>", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowGreaterThan(String value) {
            addCriterion("SCH_DEPT_FLOW >", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_DEPT_FLOW >=", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowLessThan(String value) {
            addCriterion("SCH_DEPT_FLOW <", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("SCH_DEPT_FLOW <=", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowLike(String value) {
            addCriterion("SCH_DEPT_FLOW like", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowNotLike(String value) {
            addCriterion("SCH_DEPT_FLOW not like", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowIn(List<String> values) {
            addCriterion("SCH_DEPT_FLOW in", values, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowNotIn(List<String> values) {
            addCriterion("SCH_DEPT_FLOW not in", values, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowBetween(String value1, String value2) {
            addCriterion("SCH_DEPT_FLOW between", value1, value2, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowNotBetween(String value1, String value2) {
            addCriterion("SCH_DEPT_FLOW not between", value1, value2, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameIsNull() {
            addCriterion("SCH_DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameIsNotNull() {
            addCriterion("SCH_DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameEqualTo(String value) {
            addCriterion("SCH_DEPT_NAME =", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameNotEqualTo(String value) {
            addCriterion("SCH_DEPT_NAME <>", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameGreaterThan(String value) {
            addCriterion("SCH_DEPT_NAME >", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_DEPT_NAME >=", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameLessThan(String value) {
            addCriterion("SCH_DEPT_NAME <", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameLessThanOrEqualTo(String value) {
            addCriterion("SCH_DEPT_NAME <=", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameLike(String value) {
            addCriterion("SCH_DEPT_NAME like", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameNotLike(String value) {
            addCriterion("SCH_DEPT_NAME not like", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameIn(List<String> values) {
            addCriterion("SCH_DEPT_NAME in", values, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameNotIn(List<String> values) {
            addCriterion("SCH_DEPT_NAME not in", values, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameBetween(String value1, String value2) {
            addCriterion("SCH_DEPT_NAME between", value1, value2, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameNotBetween(String value1, String value2) {
            addCriterion("SCH_DEPT_NAME not between", value1, value2, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagIsNull() {
            addCriterion("TEACHER_AGREE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagIsNotNull() {
            addCriterion("TEACHER_AGREE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagEqualTo(String value) {
            addCriterion("TEACHER_AGREE_FLAG =", value, "teacherAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagNotEqualTo(String value) {
            addCriterion("TEACHER_AGREE_FLAG <>", value, "teacherAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagGreaterThan(String value) {
            addCriterion("TEACHER_AGREE_FLAG >", value, "teacherAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_AGREE_FLAG >=", value, "teacherAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagLessThan(String value) {
            addCriterion("TEACHER_AGREE_FLAG <", value, "teacherAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_AGREE_FLAG <=", value, "teacherAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagLike(String value) {
            addCriterion("TEACHER_AGREE_FLAG like", value, "teacherAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagNotLike(String value) {
            addCriterion("TEACHER_AGREE_FLAG not like", value, "teacherAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagIn(List<String> values) {
            addCriterion("TEACHER_AGREE_FLAG in", values, "teacherAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagNotIn(List<String> values) {
            addCriterion("TEACHER_AGREE_FLAG not in", values, "teacherAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagBetween(String value1, String value2) {
            addCriterion("TEACHER_AGREE_FLAG between", value1, value2, "teacherAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andTeacherAgreeFlagNotBetween(String value1, String value2) {
            addCriterion("TEACHER_AGREE_FLAG not between", value1, value2, "teacherAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagIsNull() {
            addCriterion("HEAD_AGREE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagIsNotNull() {
            addCriterion("HEAD_AGREE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagEqualTo(String value) {
            addCriterion("HEAD_AGREE_FLAG =", value, "headAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagNotEqualTo(String value) {
            addCriterion("HEAD_AGREE_FLAG <>", value, "headAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagGreaterThan(String value) {
            addCriterion("HEAD_AGREE_FLAG >", value, "headAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagGreaterThanOrEqualTo(String value) {
            addCriterion("HEAD_AGREE_FLAG >=", value, "headAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagLessThan(String value) {
            addCriterion("HEAD_AGREE_FLAG <", value, "headAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagLessThanOrEqualTo(String value) {
            addCriterion("HEAD_AGREE_FLAG <=", value, "headAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagLike(String value) {
            addCriterion("HEAD_AGREE_FLAG like", value, "headAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagNotLike(String value) {
            addCriterion("HEAD_AGREE_FLAG not like", value, "headAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagIn(List<String> values) {
            addCriterion("HEAD_AGREE_FLAG in", values, "headAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagNotIn(List<String> values) {
            addCriterion("HEAD_AGREE_FLAG not in", values, "headAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagBetween(String value1, String value2) {
            addCriterion("HEAD_AGREE_FLAG between", value1, value2, "headAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andHeadAgreeFlagNotBetween(String value1, String value2) {
            addCriterion("HEAD_AGREE_FLAG not between", value1, value2, "headAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagIsNull() {
            addCriterion("MANAGER_AGREE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagIsNotNull() {
            addCriterion("MANAGER_AGREE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagEqualTo(String value) {
            addCriterion("MANAGER_AGREE_FLAG =", value, "managerAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagNotEqualTo(String value) {
            addCriterion("MANAGER_AGREE_FLAG <>", value, "managerAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagGreaterThan(String value) {
            addCriterion("MANAGER_AGREE_FLAG >", value, "managerAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGER_AGREE_FLAG >=", value, "managerAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagLessThan(String value) {
            addCriterion("MANAGER_AGREE_FLAG <", value, "managerAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagLessThanOrEqualTo(String value) {
            addCriterion("MANAGER_AGREE_FLAG <=", value, "managerAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagLike(String value) {
            addCriterion("MANAGER_AGREE_FLAG like", value, "managerAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagNotLike(String value) {
            addCriterion("MANAGER_AGREE_FLAG not like", value, "managerAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagIn(List<String> values) {
            addCriterion("MANAGER_AGREE_FLAG in", values, "managerAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagNotIn(List<String> values) {
            addCriterion("MANAGER_AGREE_FLAG not in", values, "managerAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagBetween(String value1, String value2) {
            addCriterion("MANAGER_AGREE_FLAG between", value1, value2, "managerAgreeFlag");
            return (Criteria) this;
        }

        public Criteria andManagerAgreeFlagNotBetween(String value1, String value2) {
            addCriterion("MANAGER_AGREE_FLAG not between", value1, value2, "managerAgreeFlag");
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

        public Criteria andTeacherFlowIsNull() {
            addCriterion("TEACHER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowIsNotNull() {
            addCriterion("TEACHER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowEqualTo(String value) {
            addCriterion("TEACHER_FLOW =", value, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowNotEqualTo(String value) {
            addCriterion("TEACHER_FLOW <>", value, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowGreaterThan(String value) {
            addCriterion("TEACHER_FLOW >", value, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_FLOW >=", value, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowLessThan(String value) {
            addCriterion("TEACHER_FLOW <", value, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_FLOW <=", value, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowLike(String value) {
            addCriterion("TEACHER_FLOW like", value, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowNotLike(String value) {
            addCriterion("TEACHER_FLOW not like", value, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowIn(List<String> values) {
            addCriterion("TEACHER_FLOW in", values, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowNotIn(List<String> values) {
            addCriterion("TEACHER_FLOW not in", values, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowBetween(String value1, String value2) {
            addCriterion("TEACHER_FLOW between", value1, value2, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherFlowNotBetween(String value1, String value2) {
            addCriterion("TEACHER_FLOW not between", value1, value2, "teacherFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherNameIsNull() {
            addCriterion("TEACHER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTeacherNameIsNotNull() {
            addCriterion("TEACHER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherNameEqualTo(String value) {
            addCriterion("TEACHER_NAME =", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotEqualTo(String value) {
            addCriterion("TEACHER_NAME <>", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameGreaterThan(String value) {
            addCriterion("TEACHER_NAME >", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_NAME >=", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameLessThan(String value) {
            addCriterion("TEACHER_NAME <", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_NAME <=", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameLike(String value) {
            addCriterion("TEACHER_NAME like", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotLike(String value) {
            addCriterion("TEACHER_NAME not like", value, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameIn(List<String> values) {
            addCriterion("TEACHER_NAME in", values, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotIn(List<String> values) {
            addCriterion("TEACHER_NAME not in", values, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameBetween(String value1, String value2) {
            addCriterion("TEACHER_NAME between", value1, value2, "teacherName");
            return (Criteria) this;
        }

        public Criteria andTeacherNameNotBetween(String value1, String value2) {
            addCriterion("TEACHER_NAME not between", value1, value2, "teacherName");
            return (Criteria) this;
        }

        public Criteria andHeadFlowIsNull() {
            addCriterion("HEAD_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andHeadFlowIsNotNull() {
            addCriterion("HEAD_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andHeadFlowEqualTo(String value) {
            addCriterion("HEAD_FLOW =", value, "headFlow");
            return (Criteria) this;
        }

        public Criteria andHeadFlowNotEqualTo(String value) {
            addCriterion("HEAD_FLOW <>", value, "headFlow");
            return (Criteria) this;
        }

        public Criteria andHeadFlowGreaterThan(String value) {
            addCriterion("HEAD_FLOW >", value, "headFlow");
            return (Criteria) this;
        }

        public Criteria andHeadFlowGreaterThanOrEqualTo(String value) {
            addCriterion("HEAD_FLOW >=", value, "headFlow");
            return (Criteria) this;
        }

        public Criteria andHeadFlowLessThan(String value) {
            addCriterion("HEAD_FLOW <", value, "headFlow");
            return (Criteria) this;
        }

        public Criteria andHeadFlowLessThanOrEqualTo(String value) {
            addCriterion("HEAD_FLOW <=", value, "headFlow");
            return (Criteria) this;
        }

        public Criteria andHeadFlowLike(String value) {
            addCriterion("HEAD_FLOW like", value, "headFlow");
            return (Criteria) this;
        }

        public Criteria andHeadFlowNotLike(String value) {
            addCriterion("HEAD_FLOW not like", value, "headFlow");
            return (Criteria) this;
        }

        public Criteria andHeadFlowIn(List<String> values) {
            addCriterion("HEAD_FLOW in", values, "headFlow");
            return (Criteria) this;
        }

        public Criteria andHeadFlowNotIn(List<String> values) {
            addCriterion("HEAD_FLOW not in", values, "headFlow");
            return (Criteria) this;
        }

        public Criteria andHeadFlowBetween(String value1, String value2) {
            addCriterion("HEAD_FLOW between", value1, value2, "headFlow");
            return (Criteria) this;
        }

        public Criteria andHeadFlowNotBetween(String value1, String value2) {
            addCriterion("HEAD_FLOW not between", value1, value2, "headFlow");
            return (Criteria) this;
        }

        public Criteria andHeadNameIsNull() {
            addCriterion("HEAD_NAME is null");
            return (Criteria) this;
        }

        public Criteria andHeadNameIsNotNull() {
            addCriterion("HEAD_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andHeadNameEqualTo(String value) {
            addCriterion("HEAD_NAME =", value, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameNotEqualTo(String value) {
            addCriterion("HEAD_NAME <>", value, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameGreaterThan(String value) {
            addCriterion("HEAD_NAME >", value, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameGreaterThanOrEqualTo(String value) {
            addCriterion("HEAD_NAME >=", value, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameLessThan(String value) {
            addCriterion("HEAD_NAME <", value, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameLessThanOrEqualTo(String value) {
            addCriterion("HEAD_NAME <=", value, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameLike(String value) {
            addCriterion("HEAD_NAME like", value, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameNotLike(String value) {
            addCriterion("HEAD_NAME not like", value, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameIn(List<String> values) {
            addCriterion("HEAD_NAME in", values, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameNotIn(List<String> values) {
            addCriterion("HEAD_NAME not in", values, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameBetween(String value1, String value2) {
            addCriterion("HEAD_NAME between", value1, value2, "headName");
            return (Criteria) this;
        }

        public Criteria andHeadNameNotBetween(String value1, String value2) {
            addCriterion("HEAD_NAME not between", value1, value2, "headName");
            return (Criteria) this;
        }

        public Criteria andManagerFlowIsNull() {
            addCriterion("MANAGER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andManagerFlowIsNotNull() {
            addCriterion("MANAGER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andManagerFlowEqualTo(String value) {
            addCriterion("MANAGER_FLOW =", value, "managerFlow");
            return (Criteria) this;
        }

        public Criteria andManagerFlowNotEqualTo(String value) {
            addCriterion("MANAGER_FLOW <>", value, "managerFlow");
            return (Criteria) this;
        }

        public Criteria andManagerFlowGreaterThan(String value) {
            addCriterion("MANAGER_FLOW >", value, "managerFlow");
            return (Criteria) this;
        }

        public Criteria andManagerFlowGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGER_FLOW >=", value, "managerFlow");
            return (Criteria) this;
        }

        public Criteria andManagerFlowLessThan(String value) {
            addCriterion("MANAGER_FLOW <", value, "managerFlow");
            return (Criteria) this;
        }

        public Criteria andManagerFlowLessThanOrEqualTo(String value) {
            addCriterion("MANAGER_FLOW <=", value, "managerFlow");
            return (Criteria) this;
        }

        public Criteria andManagerFlowLike(String value) {
            addCriterion("MANAGER_FLOW like", value, "managerFlow");
            return (Criteria) this;
        }

        public Criteria andManagerFlowNotLike(String value) {
            addCriterion("MANAGER_FLOW not like", value, "managerFlow");
            return (Criteria) this;
        }

        public Criteria andManagerFlowIn(List<String> values) {
            addCriterion("MANAGER_FLOW in", values, "managerFlow");
            return (Criteria) this;
        }

        public Criteria andManagerFlowNotIn(List<String> values) {
            addCriterion("MANAGER_FLOW not in", values, "managerFlow");
            return (Criteria) this;
        }

        public Criteria andManagerFlowBetween(String value1, String value2) {
            addCriterion("MANAGER_FLOW between", value1, value2, "managerFlow");
            return (Criteria) this;
        }

        public Criteria andManagerFlowNotBetween(String value1, String value2) {
            addCriterion("MANAGER_FLOW not between", value1, value2, "managerFlow");
            return (Criteria) this;
        }

        public Criteria andManagerNameIsNull() {
            addCriterion("MANAGER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andManagerNameIsNotNull() {
            addCriterion("MANAGER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andManagerNameEqualTo(String value) {
            addCriterion("MANAGER_NAME =", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameNotEqualTo(String value) {
            addCriterion("MANAGER_NAME <>", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameGreaterThan(String value) {
            addCriterion("MANAGER_NAME >", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGER_NAME >=", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameLessThan(String value) {
            addCriterion("MANAGER_NAME <", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameLessThanOrEqualTo(String value) {
            addCriterion("MANAGER_NAME <=", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameLike(String value) {
            addCriterion("MANAGER_NAME like", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameNotLike(String value) {
            addCriterion("MANAGER_NAME not like", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameIn(List<String> values) {
            addCriterion("MANAGER_NAME in", values, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameNotIn(List<String> values) {
            addCriterion("MANAGER_NAME not in", values, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameBetween(String value1, String value2) {
            addCriterion("MANAGER_NAME between", value1, value2, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameNotBetween(String value1, String value2) {
            addCriterion("MANAGER_NAME not between", value1, value2, "managerName");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceIsNull() {
            addCriterion("REPEAL_ABSENCE is null");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceIsNotNull() {
            addCriterion("REPEAL_ABSENCE is not null");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceEqualTo(String value) {
            addCriterion("REPEAL_ABSENCE =", value, "repealAbsence");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceNotEqualTo(String value) {
            addCriterion("REPEAL_ABSENCE <>", value, "repealAbsence");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceGreaterThan(String value) {
            addCriterion("REPEAL_ABSENCE >", value, "repealAbsence");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceGreaterThanOrEqualTo(String value) {
            addCriterion("REPEAL_ABSENCE >=", value, "repealAbsence");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceLessThan(String value) {
            addCriterion("REPEAL_ABSENCE <", value, "repealAbsence");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceLessThanOrEqualTo(String value) {
            addCriterion("REPEAL_ABSENCE <=", value, "repealAbsence");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceLike(String value) {
            addCriterion("REPEAL_ABSENCE like", value, "repealAbsence");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceNotLike(String value) {
            addCriterion("REPEAL_ABSENCE not like", value, "repealAbsence");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceIn(List<String> values) {
            addCriterion("REPEAL_ABSENCE in", values, "repealAbsence");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceNotIn(List<String> values) {
            addCriterion("REPEAL_ABSENCE not in", values, "repealAbsence");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceBetween(String value1, String value2) {
            addCriterion("REPEAL_ABSENCE between", value1, value2, "repealAbsence");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceNotBetween(String value1, String value2) {
            addCriterion("REPEAL_ABSENCE not between", value1, value2, "repealAbsence");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDateIsNull() {
            addCriterion("REPEAL_ABSENCE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDateIsNotNull() {
            addCriterion("REPEAL_ABSENCE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDateEqualTo(String value) {
            addCriterion("REPEAL_ABSENCE_DATE =", value, "repealAbsenceDate");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDateNotEqualTo(String value) {
            addCriterion("REPEAL_ABSENCE_DATE <>", value, "repealAbsenceDate");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDateGreaterThan(String value) {
            addCriterion("REPEAL_ABSENCE_DATE >", value, "repealAbsenceDate");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDateGreaterThanOrEqualTo(String value) {
            addCriterion("REPEAL_ABSENCE_DATE >=", value, "repealAbsenceDate");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDateLessThan(String value) {
            addCriterion("REPEAL_ABSENCE_DATE <", value, "repealAbsenceDate");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDateLessThanOrEqualTo(String value) {
            addCriterion("REPEAL_ABSENCE_DATE <=", value, "repealAbsenceDate");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDateLike(String value) {
            addCriterion("REPEAL_ABSENCE_DATE like", value, "repealAbsenceDate");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDateNotLike(String value) {
            addCriterion("REPEAL_ABSENCE_DATE not like", value, "repealAbsenceDate");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDateIn(List<String> values) {
            addCriterion("REPEAL_ABSENCE_DATE in", values, "repealAbsenceDate");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDateNotIn(List<String> values) {
            addCriterion("REPEAL_ABSENCE_DATE not in", values, "repealAbsenceDate");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDateBetween(String value1, String value2) {
            addCriterion("REPEAL_ABSENCE_DATE between", value1, value2, "repealAbsenceDate");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDateNotBetween(String value1, String value2) {
            addCriterion("REPEAL_ABSENCE_DATE not between", value1, value2, "repealAbsenceDate");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeIdIsNull() {
            addCriterion("ABSENCE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeIdIsNotNull() {
            addCriterion("ABSENCE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeIdEqualTo(String value) {
            addCriterion("ABSENCE_TYPE_ID =", value, "absenceTypeId");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeIdNotEqualTo(String value) {
            addCriterion("ABSENCE_TYPE_ID <>", value, "absenceTypeId");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeIdGreaterThan(String value) {
            addCriterion("ABSENCE_TYPE_ID >", value, "absenceTypeId");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("ABSENCE_TYPE_ID >=", value, "absenceTypeId");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeIdLessThan(String value) {
            addCriterion("ABSENCE_TYPE_ID <", value, "absenceTypeId");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeIdLessThanOrEqualTo(String value) {
            addCriterion("ABSENCE_TYPE_ID <=", value, "absenceTypeId");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeIdLike(String value) {
            addCriterion("ABSENCE_TYPE_ID like", value, "absenceTypeId");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeIdNotLike(String value) {
            addCriterion("ABSENCE_TYPE_ID not like", value, "absenceTypeId");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeIdIn(List<String> values) {
            addCriterion("ABSENCE_TYPE_ID in", values, "absenceTypeId");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeIdNotIn(List<String> values) {
            addCriterion("ABSENCE_TYPE_ID not in", values, "absenceTypeId");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeIdBetween(String value1, String value2) {
            addCriterion("ABSENCE_TYPE_ID between", value1, value2, "absenceTypeId");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeIdNotBetween(String value1, String value2) {
            addCriterion("ABSENCE_TYPE_ID not between", value1, value2, "absenceTypeId");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeNameIsNull() {
            addCriterion("ABSENCE_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeNameIsNotNull() {
            addCriterion("ABSENCE_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeNameEqualTo(String value) {
            addCriterion("ABSENCE_TYPE_NAME =", value, "absenceTypeName");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeNameNotEqualTo(String value) {
            addCriterion("ABSENCE_TYPE_NAME <>", value, "absenceTypeName");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeNameGreaterThan(String value) {
            addCriterion("ABSENCE_TYPE_NAME >", value, "absenceTypeName");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("ABSENCE_TYPE_NAME >=", value, "absenceTypeName");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeNameLessThan(String value) {
            addCriterion("ABSENCE_TYPE_NAME <", value, "absenceTypeName");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeNameLessThanOrEqualTo(String value) {
            addCriterion("ABSENCE_TYPE_NAME <=", value, "absenceTypeName");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeNameLike(String value) {
            addCriterion("ABSENCE_TYPE_NAME like", value, "absenceTypeName");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeNameNotLike(String value) {
            addCriterion("ABSENCE_TYPE_NAME not like", value, "absenceTypeName");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeNameIn(List<String> values) {
            addCriterion("ABSENCE_TYPE_NAME in", values, "absenceTypeName");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeNameNotIn(List<String> values) {
            addCriterion("ABSENCE_TYPE_NAME not in", values, "absenceTypeName");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeNameBetween(String value1, String value2) {
            addCriterion("ABSENCE_TYPE_NAME between", value1, value2, "absenceTypeName");
            return (Criteria) this;
        }

        public Criteria andAbsenceTypeNameNotBetween(String value1, String value2) {
            addCriterion("ABSENCE_TYPE_NAME not between", value1, value2, "absenceTypeName");
            return (Criteria) this;
        }

        public Criteria andIsRegisterIsNull() {
            addCriterion("IS_REGISTER is null");
            return (Criteria) this;
        }

        public Criteria andIsRegisterIsNotNull() {
            addCriterion("IS_REGISTER is not null");
            return (Criteria) this;
        }

        public Criteria andIsRegisterEqualTo(String value) {
            addCriterion("IS_REGISTER =", value, "isRegister");
            return (Criteria) this;
        }

        public Criteria andIsRegisterNotEqualTo(String value) {
            addCriterion("IS_REGISTER <>", value, "isRegister");
            return (Criteria) this;
        }

        public Criteria andIsRegisterGreaterThan(String value) {
            addCriterion("IS_REGISTER >", value, "isRegister");
            return (Criteria) this;
        }

        public Criteria andIsRegisterGreaterThanOrEqualTo(String value) {
            addCriterion("IS_REGISTER >=", value, "isRegister");
            return (Criteria) this;
        }

        public Criteria andIsRegisterLessThan(String value) {
            addCriterion("IS_REGISTER <", value, "isRegister");
            return (Criteria) this;
        }

        public Criteria andIsRegisterLessThanOrEqualTo(String value) {
            addCriterion("IS_REGISTER <=", value, "isRegister");
            return (Criteria) this;
        }

        public Criteria andIsRegisterLike(String value) {
            addCriterion("IS_REGISTER like", value, "isRegister");
            return (Criteria) this;
        }

        public Criteria andIsRegisterNotLike(String value) {
            addCriterion("IS_REGISTER not like", value, "isRegister");
            return (Criteria) this;
        }

        public Criteria andIsRegisterIn(List<String> values) {
            addCriterion("IS_REGISTER in", values, "isRegister");
            return (Criteria) this;
        }

        public Criteria andIsRegisterNotIn(List<String> values) {
            addCriterion("IS_REGISTER not in", values, "isRegister");
            return (Criteria) this;
        }

        public Criteria andIsRegisterBetween(String value1, String value2) {
            addCriterion("IS_REGISTER between", value1, value2, "isRegister");
            return (Criteria) this;
        }

        public Criteria andIsRegisterNotBetween(String value1, String value2) {
            addCriterion("IS_REGISTER not between", value1, value2, "isRegister");
            return (Criteria) this;
        }

        public Criteria andMakingFileIsNull() {
            addCriterion("MAKING_FILE is null");
            return (Criteria) this;
        }

        public Criteria andMakingFileIsNotNull() {
            addCriterion("MAKING_FILE is not null");
            return (Criteria) this;
        }

        public Criteria andMakingFileEqualTo(String value) {
            addCriterion("MAKING_FILE =", value, "makingFile");
            return (Criteria) this;
        }

        public Criteria andMakingFileNotEqualTo(String value) {
            addCriterion("MAKING_FILE <>", value, "makingFile");
            return (Criteria) this;
        }

        public Criteria andMakingFileGreaterThan(String value) {
            addCriterion("MAKING_FILE >", value, "makingFile");
            return (Criteria) this;
        }

        public Criteria andMakingFileGreaterThanOrEqualTo(String value) {
            addCriterion("MAKING_FILE >=", value, "makingFile");
            return (Criteria) this;
        }

        public Criteria andMakingFileLessThan(String value) {
            addCriterion("MAKING_FILE <", value, "makingFile");
            return (Criteria) this;
        }

        public Criteria andMakingFileLessThanOrEqualTo(String value) {
            addCriterion("MAKING_FILE <=", value, "makingFile");
            return (Criteria) this;
        }

        public Criteria andMakingFileLike(String value) {
            addCriterion("MAKING_FILE like", value, "makingFile");
            return (Criteria) this;
        }

        public Criteria andMakingFileNotLike(String value) {
            addCriterion("MAKING_FILE not like", value, "makingFile");
            return (Criteria) this;
        }

        public Criteria andMakingFileIn(List<String> values) {
            addCriterion("MAKING_FILE in", values, "makingFile");
            return (Criteria) this;
        }

        public Criteria andMakingFileNotIn(List<String> values) {
            addCriterion("MAKING_FILE not in", values, "makingFile");
            return (Criteria) this;
        }

        public Criteria andMakingFileBetween(String value1, String value2) {
            addCriterion("MAKING_FILE between", value1, value2, "makingFile");
            return (Criteria) this;
        }

        public Criteria andMakingFileNotBetween(String value1, String value2) {
            addCriterion("MAKING_FILE not between", value1, value2, "makingFile");
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

        public Criteria andDoctorTypeIdIsNull() {
            addCriterion("DOCTOR_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdIsNotNull() {
            addCriterion("DOCTOR_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_ID =", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdNotEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_ID <>", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdGreaterThan(String value) {
            addCriterion("DOCTOR_TYPE_ID >", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_ID >=", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdLessThan(String value) {
            addCriterion("DOCTOR_TYPE_ID <", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_ID <=", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdLike(String value) {
            addCriterion("DOCTOR_TYPE_ID like", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdNotLike(String value) {
            addCriterion("DOCTOR_TYPE_ID not like", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdIn(List<String> values) {
            addCriterion("DOCTOR_TYPE_ID in", values, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdNotIn(List<String> values) {
            addCriterion("DOCTOR_TYPE_ID not in", values, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdBetween(String value1, String value2) {
            addCriterion("DOCTOR_TYPE_ID between", value1, value2, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_TYPE_ID not between", value1, value2, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameIsNull() {
            addCriterion("DOCTOR_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameIsNotNull() {
            addCriterion("DOCTOR_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_NAME =", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameNotEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_NAME <>", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameGreaterThan(String value) {
            addCriterion("DOCTOR_TYPE_NAME >", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_NAME >=", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameLessThan(String value) {
            addCriterion("DOCTOR_TYPE_NAME <", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_NAME <=", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameLike(String value) {
            addCriterion("DOCTOR_TYPE_NAME like", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameNotLike(String value) {
            addCriterion("DOCTOR_TYPE_NAME not like", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameIn(List<String> values) {
            addCriterion("DOCTOR_TYPE_NAME in", values, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameNotIn(List<String> values) {
            addCriterion("DOCTOR_TYPE_NAME not in", values, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameBetween(String value1, String value2) {
            addCriterion("DOCTOR_TYPE_NAME between", value1, value2, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_TYPE_NAME not between", value1, value2, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDayIsNull() {
            addCriterion("REPEAL_ABSENCE_DAY is null");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDayIsNotNull() {
            addCriterion("REPEAL_ABSENCE_DAY is not null");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDayEqualTo(String value) {
            addCriterion("REPEAL_ABSENCE_DAY =", value, "repealAbsenceDay");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDayNotEqualTo(String value) {
            addCriterion("REPEAL_ABSENCE_DAY <>", value, "repealAbsenceDay");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDayGreaterThan(String value) {
            addCriterion("REPEAL_ABSENCE_DAY >", value, "repealAbsenceDay");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDayGreaterThanOrEqualTo(String value) {
            addCriterion("REPEAL_ABSENCE_DAY >=", value, "repealAbsenceDay");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDayLessThan(String value) {
            addCriterion("REPEAL_ABSENCE_DAY <", value, "repealAbsenceDay");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDayLessThanOrEqualTo(String value) {
            addCriterion("REPEAL_ABSENCE_DAY <=", value, "repealAbsenceDay");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDayLike(String value) {
            addCriterion("REPEAL_ABSENCE_DAY like", value, "repealAbsenceDay");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDayNotLike(String value) {
            addCriterion("REPEAL_ABSENCE_DAY not like", value, "repealAbsenceDay");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDayIn(List<String> values) {
            addCriterion("REPEAL_ABSENCE_DAY in", values, "repealAbsenceDay");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDayNotIn(List<String> values) {
            addCriterion("REPEAL_ABSENCE_DAY not in", values, "repealAbsenceDay");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDayBetween(String value1, String value2) {
            addCriterion("REPEAL_ABSENCE_DAY between", value1, value2, "repealAbsenceDay");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceDayNotBetween(String value1, String value2) {
            addCriterion("REPEAL_ABSENCE_DAY not between", value1, value2, "repealAbsenceDay");
            return (Criteria) this;
        }

        public Criteria andTeachingFlowIsNull() {
            addCriterion("TEACHING_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andTeachingFlowIsNotNull() {
            addCriterion("TEACHING_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andTeachingFlowEqualTo(String value) {
            addCriterion("TEACHING_FLOW =", value, "teachingFlow");
            return (Criteria) this;
        }

        public Criteria andTeachingFlowNotEqualTo(String value) {
            addCriterion("TEACHING_FLOW <>", value, "teachingFlow");
            return (Criteria) this;
        }

        public Criteria andTeachingFlowGreaterThan(String value) {
            addCriterion("TEACHING_FLOW >", value, "teachingFlow");
            return (Criteria) this;
        }

        public Criteria andTeachingFlowGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHING_FLOW >=", value, "teachingFlow");
            return (Criteria) this;
        }

        public Criteria andTeachingFlowLessThan(String value) {
            addCriterion("TEACHING_FLOW <", value, "teachingFlow");
            return (Criteria) this;
        }

        public Criteria andTeachingFlowLessThanOrEqualTo(String value) {
            addCriterion("TEACHING_FLOW <=", value, "teachingFlow");
            return (Criteria) this;
        }

        public Criteria andTeachingFlowLike(String value) {
            addCriterion("TEACHING_FLOW like", value, "teachingFlow");
            return (Criteria) this;
        }

        public Criteria andTeachingFlowNotLike(String value) {
            addCriterion("TEACHING_FLOW not like", value, "teachingFlow");
            return (Criteria) this;
        }

        public Criteria andTeachingFlowIn(List<String> values) {
            addCriterion("TEACHING_FLOW in", values, "teachingFlow");
            return (Criteria) this;
        }

        public Criteria andTeachingFlowNotIn(List<String> values) {
            addCriterion("TEACHING_FLOW not in", values, "teachingFlow");
            return (Criteria) this;
        }

        public Criteria andTeachingFlowBetween(String value1, String value2) {
            addCriterion("TEACHING_FLOW between", value1, value2, "teachingFlow");
            return (Criteria) this;
        }

        public Criteria andTeachingFlowNotBetween(String value1, String value2) {
            addCriterion("TEACHING_FLOW not between", value1, value2, "teachingFlow");
            return (Criteria) this;
        }

        public Criteria andTeachingNameIsNull() {
            addCriterion("TEACHING_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTeachingNameIsNotNull() {
            addCriterion("TEACHING_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTeachingNameEqualTo(String value) {
            addCriterion("TEACHING_NAME =", value, "teachingName");
            return (Criteria) this;
        }

        public Criteria andTeachingNameNotEqualTo(String value) {
            addCriterion("TEACHING_NAME <>", value, "teachingName");
            return (Criteria) this;
        }

        public Criteria andTeachingNameGreaterThan(String value) {
            addCriterion("TEACHING_NAME >", value, "teachingName");
            return (Criteria) this;
        }

        public Criteria andTeachingNameGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHING_NAME >=", value, "teachingName");
            return (Criteria) this;
        }

        public Criteria andTeachingNameLessThan(String value) {
            addCriterion("TEACHING_NAME <", value, "teachingName");
            return (Criteria) this;
        }

        public Criteria andTeachingNameLessThanOrEqualTo(String value) {
            addCriterion("TEACHING_NAME <=", value, "teachingName");
            return (Criteria) this;
        }

        public Criteria andTeachingNameLike(String value) {
            addCriterion("TEACHING_NAME like", value, "teachingName");
            return (Criteria) this;
        }

        public Criteria andTeachingNameNotLike(String value) {
            addCriterion("TEACHING_NAME not like", value, "teachingName");
            return (Criteria) this;
        }

        public Criteria andTeachingNameIn(List<String> values) {
            addCriterion("TEACHING_NAME in", values, "teachingName");
            return (Criteria) this;
        }

        public Criteria andTeachingNameNotIn(List<String> values) {
            addCriterion("TEACHING_NAME not in", values, "teachingName");
            return (Criteria) this;
        }

        public Criteria andTeachingNameBetween(String value1, String value2) {
            addCriterion("TEACHING_NAME between", value1, value2, "teachingName");
            return (Criteria) this;
        }

        public Criteria andTeachingNameNotBetween(String value1, String value2) {
            addCriterion("TEACHING_NAME not between", value1, value2, "teachingName");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoIsNull() {
            addCriterion("TEACHER_AUDIT_INFO is null");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoIsNotNull() {
            addCriterion("TEACHER_AUDIT_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoEqualTo(String value) {
            addCriterion("TEACHER_AUDIT_INFO =", value, "teacherAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoNotEqualTo(String value) {
            addCriterion("TEACHER_AUDIT_INFO <>", value, "teacherAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoGreaterThan(String value) {
            addCriterion("TEACHER_AUDIT_INFO >", value, "teacherAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_AUDIT_INFO >=", value, "teacherAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoLessThan(String value) {
            addCriterion("TEACHER_AUDIT_INFO <", value, "teacherAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_AUDIT_INFO <=", value, "teacherAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoLike(String value) {
            addCriterion("TEACHER_AUDIT_INFO like", value, "teacherAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoNotLike(String value) {
            addCriterion("TEACHER_AUDIT_INFO not like", value, "teacherAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoIn(List<String> values) {
            addCriterion("TEACHER_AUDIT_INFO in", values, "teacherAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoNotIn(List<String> values) {
            addCriterion("TEACHER_AUDIT_INFO not in", values, "teacherAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoBetween(String value1, String value2) {
            addCriterion("TEACHER_AUDIT_INFO between", value1, value2, "teacherAuditInfo");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditInfoNotBetween(String value1, String value2) {
            addCriterion("TEACHER_AUDIT_INFO not between", value1, value2, "teacherAuditInfo");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoIsNull() {
            addCriterion("HEAD_AUDIT_INFO is null");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoIsNotNull() {
            addCriterion("HEAD_AUDIT_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoEqualTo(String value) {
            addCriterion("HEAD_AUDIT_INFO =", value, "headAuditInfo");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoNotEqualTo(String value) {
            addCriterion("HEAD_AUDIT_INFO <>", value, "headAuditInfo");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoGreaterThan(String value) {
            addCriterion("HEAD_AUDIT_INFO >", value, "headAuditInfo");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoGreaterThanOrEqualTo(String value) {
            addCriterion("HEAD_AUDIT_INFO >=", value, "headAuditInfo");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoLessThan(String value) {
            addCriterion("HEAD_AUDIT_INFO <", value, "headAuditInfo");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoLessThanOrEqualTo(String value) {
            addCriterion("HEAD_AUDIT_INFO <=", value, "headAuditInfo");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoLike(String value) {
            addCriterion("HEAD_AUDIT_INFO like", value, "headAuditInfo");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoNotLike(String value) {
            addCriterion("HEAD_AUDIT_INFO not like", value, "headAuditInfo");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoIn(List<String> values) {
            addCriterion("HEAD_AUDIT_INFO in", values, "headAuditInfo");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoNotIn(List<String> values) {
            addCriterion("HEAD_AUDIT_INFO not in", values, "headAuditInfo");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoBetween(String value1, String value2) {
            addCriterion("HEAD_AUDIT_INFO between", value1, value2, "headAuditInfo");
            return (Criteria) this;
        }

        public Criteria andHeadAuditInfoNotBetween(String value1, String value2) {
            addCriterion("HEAD_AUDIT_INFO not between", value1, value2, "headAuditInfo");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoIsNull() {
            addCriterion("MANAGER_AUDIT_INFO is null");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoIsNotNull() {
            addCriterion("MANAGER_AUDIT_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_INFO =", value, "managerAuditInfo");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoNotEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_INFO <>", value, "managerAuditInfo");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoGreaterThan(String value) {
            addCriterion("MANAGER_AUDIT_INFO >", value, "managerAuditInfo");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_INFO >=", value, "managerAuditInfo");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoLessThan(String value) {
            addCriterion("MANAGER_AUDIT_INFO <", value, "managerAuditInfo");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoLessThanOrEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_INFO <=", value, "managerAuditInfo");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoLike(String value) {
            addCriterion("MANAGER_AUDIT_INFO like", value, "managerAuditInfo");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoNotLike(String value) {
            addCriterion("MANAGER_AUDIT_INFO not like", value, "managerAuditInfo");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoIn(List<String> values) {
            addCriterion("MANAGER_AUDIT_INFO in", values, "managerAuditInfo");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoNotIn(List<String> values) {
            addCriterion("MANAGER_AUDIT_INFO not in", values, "managerAuditInfo");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoBetween(String value1, String value2) {
            addCriterion("MANAGER_AUDIT_INFO between", value1, value2, "managerAuditInfo");
            return (Criteria) this;
        }

        public Criteria andManagerAuditInfoNotBetween(String value1, String value2) {
            addCriterion("MANAGER_AUDIT_INFO not between", value1, value2, "managerAuditInfo");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceInfoIsNull() {
            addCriterion("REPEAL_ABSENCE_INFO is null");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceInfoIsNotNull() {
            addCriterion("REPEAL_ABSENCE_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceInfoEqualTo(String value) {
            addCriterion("REPEAL_ABSENCE_INFO =", value, "repealAbsenceInfo");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceInfoNotEqualTo(String value) {
            addCriterion("REPEAL_ABSENCE_INFO <>", value, "repealAbsenceInfo");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceInfoGreaterThan(String value) {
            addCriterion("REPEAL_ABSENCE_INFO >", value, "repealAbsenceInfo");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceInfoGreaterThanOrEqualTo(String value) {
            addCriterion("REPEAL_ABSENCE_INFO >=", value, "repealAbsenceInfo");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceInfoLessThan(String value) {
            addCriterion("REPEAL_ABSENCE_INFO <", value, "repealAbsenceInfo");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceInfoLessThanOrEqualTo(String value) {
            addCriterion("REPEAL_ABSENCE_INFO <=", value, "repealAbsenceInfo");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceInfoLike(String value) {
            addCriterion("REPEAL_ABSENCE_INFO like", value, "repealAbsenceInfo");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceInfoNotLike(String value) {
            addCriterion("REPEAL_ABSENCE_INFO not like", value, "repealAbsenceInfo");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceInfoIn(List<String> values) {
            addCriterion("REPEAL_ABSENCE_INFO in", values, "repealAbsenceInfo");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceInfoNotIn(List<String> values) {
            addCriterion("REPEAL_ABSENCE_INFO not in", values, "repealAbsenceInfo");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceInfoBetween(String value1, String value2) {
            addCriterion("REPEAL_ABSENCE_INFO between", value1, value2, "repealAbsenceInfo");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceInfoNotBetween(String value1, String value2) {
            addCriterion("REPEAL_ABSENCE_INFO not between", value1, value2, "repealAbsenceInfo");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeIsNull() {
            addCriterion("TEACHER_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeIsNotNull() {
            addCriterion("TEACHER_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeEqualTo(String value) {
            addCriterion("TEACHER_AUDIT_TIME =", value, "teacherAuditTime");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeNotEqualTo(String value) {
            addCriterion("TEACHER_AUDIT_TIME <>", value, "teacherAuditTime");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeGreaterThan(String value) {
            addCriterion("TEACHER_AUDIT_TIME >", value, "teacherAuditTime");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_AUDIT_TIME >=", value, "teacherAuditTime");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeLessThan(String value) {
            addCriterion("TEACHER_AUDIT_TIME <", value, "teacherAuditTime");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_AUDIT_TIME <=", value, "teacherAuditTime");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeLike(String value) {
            addCriterion("TEACHER_AUDIT_TIME like", value, "teacherAuditTime");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeNotLike(String value) {
            addCriterion("TEACHER_AUDIT_TIME not like", value, "teacherAuditTime");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeIn(List<String> values) {
            addCriterion("TEACHER_AUDIT_TIME in", values, "teacherAuditTime");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeNotIn(List<String> values) {
            addCriterion("TEACHER_AUDIT_TIME not in", values, "teacherAuditTime");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeBetween(String value1, String value2) {
            addCriterion("TEACHER_AUDIT_TIME between", value1, value2, "teacherAuditTime");
            return (Criteria) this;
        }

        public Criteria andTeacherAuditTimeNotBetween(String value1, String value2) {
            addCriterion("TEACHER_AUDIT_TIME not between", value1, value2, "teacherAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeIsNull() {
            addCriterion("HEAD_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeIsNotNull() {
            addCriterion("HEAD_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeEqualTo(String value) {
            addCriterion("HEAD_AUDIT_TIME =", value, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeNotEqualTo(String value) {
            addCriterion("HEAD_AUDIT_TIME <>", value, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeGreaterThan(String value) {
            addCriterion("HEAD_AUDIT_TIME >", value, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("HEAD_AUDIT_TIME >=", value, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeLessThan(String value) {
            addCriterion("HEAD_AUDIT_TIME <", value, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("HEAD_AUDIT_TIME <=", value, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeLike(String value) {
            addCriterion("HEAD_AUDIT_TIME like", value, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeNotLike(String value) {
            addCriterion("HEAD_AUDIT_TIME not like", value, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeIn(List<String> values) {
            addCriterion("HEAD_AUDIT_TIME in", values, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeNotIn(List<String> values) {
            addCriterion("HEAD_AUDIT_TIME not in", values, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeBetween(String value1, String value2) {
            addCriterion("HEAD_AUDIT_TIME between", value1, value2, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andHeadAuditTimeNotBetween(String value1, String value2) {
            addCriterion("HEAD_AUDIT_TIME not between", value1, value2, "headAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeIsNull() {
            addCriterion("MANAGER_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeIsNotNull() {
            addCriterion("MANAGER_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_TIME =", value, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeNotEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_TIME <>", value, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeGreaterThan(String value) {
            addCriterion("MANAGER_AUDIT_TIME >", value, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_TIME >=", value, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeLessThan(String value) {
            addCriterion("MANAGER_AUDIT_TIME <", value, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("MANAGER_AUDIT_TIME <=", value, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeLike(String value) {
            addCriterion("MANAGER_AUDIT_TIME like", value, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeNotLike(String value) {
            addCriterion("MANAGER_AUDIT_TIME not like", value, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeIn(List<String> values) {
            addCriterion("MANAGER_AUDIT_TIME in", values, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeNotIn(List<String> values) {
            addCriterion("MANAGER_AUDIT_TIME not in", values, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeBetween(String value1, String value2) {
            addCriterion("MANAGER_AUDIT_TIME between", value1, value2, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andManagerAuditTimeNotBetween(String value1, String value2) {
            addCriterion("MANAGER_AUDIT_TIME not between", value1, value2, "managerAuditTime");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceAuditTimeIsNull() {
            addCriterion("REPEAL_ABSENCE_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceAuditTimeIsNotNull() {
            addCriterion("REPEAL_ABSENCE_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceAuditTimeEqualTo(String value) {
            addCriterion("REPEAL_ABSENCE_AUDIT_TIME =", value, "repealAbsenceAuditTime");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceAuditTimeNotEqualTo(String value) {
            addCriterion("REPEAL_ABSENCE_AUDIT_TIME <>", value, "repealAbsenceAuditTime");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceAuditTimeGreaterThan(String value) {
            addCriterion("REPEAL_ABSENCE_AUDIT_TIME >", value, "repealAbsenceAuditTime");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("REPEAL_ABSENCE_AUDIT_TIME >=", value, "repealAbsenceAuditTime");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceAuditTimeLessThan(String value) {
            addCriterion("REPEAL_ABSENCE_AUDIT_TIME <", value, "repealAbsenceAuditTime");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("REPEAL_ABSENCE_AUDIT_TIME <=", value, "repealAbsenceAuditTime");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceAuditTimeLike(String value) {
            addCriterion("REPEAL_ABSENCE_AUDIT_TIME like", value, "repealAbsenceAuditTime");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceAuditTimeNotLike(String value) {
            addCriterion("REPEAL_ABSENCE_AUDIT_TIME not like", value, "repealAbsenceAuditTime");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceAuditTimeIn(List<String> values) {
            addCriterion("REPEAL_ABSENCE_AUDIT_TIME in", values, "repealAbsenceAuditTime");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceAuditTimeNotIn(List<String> values) {
            addCriterion("REPEAL_ABSENCE_AUDIT_TIME not in", values, "repealAbsenceAuditTime");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceAuditTimeBetween(String value1, String value2) {
            addCriterion("REPEAL_ABSENCE_AUDIT_TIME between", value1, value2, "repealAbsenceAuditTime");
            return (Criteria) this;
        }

        public Criteria andRepealAbsenceAuditTimeNotBetween(String value1, String value2) {
            addCriterion("REPEAL_ABSENCE_AUDIT_TIME not between", value1, value2, "repealAbsenceAuditTime");
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