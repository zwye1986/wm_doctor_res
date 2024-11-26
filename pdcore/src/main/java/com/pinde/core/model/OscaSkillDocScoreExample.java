package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class OscaSkillDocScoreExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OscaSkillDocScoreExample() {
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

        public Criteria andScoreFlowIsNull() {
            addCriterion("SCORE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andScoreFlowIsNotNull() {
            addCriterion("SCORE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andScoreFlowEqualTo(String value) {
            addCriterion("SCORE_FLOW =", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowNotEqualTo(String value) {
            addCriterion("SCORE_FLOW <>", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowGreaterThan(String value) {
            addCriterion("SCORE_FLOW >", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_FLOW >=", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowLessThan(String value) {
            addCriterion("SCORE_FLOW <", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowLessThanOrEqualTo(String value) {
            addCriterion("SCORE_FLOW <=", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowLike(String value) {
            addCriterion("SCORE_FLOW like", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowNotLike(String value) {
            addCriterion("SCORE_FLOW not like", value, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowIn(List<String> values) {
            addCriterion("SCORE_FLOW in", values, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowNotIn(List<String> values) {
            addCriterion("SCORE_FLOW not in", values, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowBetween(String value1, String value2) {
            addCriterion("SCORE_FLOW between", value1, value2, "scoreFlow");
            return (Criteria) this;
        }

        public Criteria andScoreFlowNotBetween(String value1, String value2) {
            addCriterion("SCORE_FLOW not between", value1, value2, "scoreFlow");
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

        public Criteria andRecordYearIsNull() {
            addCriterion("RECORD_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andRecordYearIsNotNull() {
            addCriterion("RECORD_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andRecordYearEqualTo(String value) {
            addCriterion("RECORD_YEAR =", value, "recordYear");
            return (Criteria) this;
        }

        public Criteria andRecordYearNotEqualTo(String value) {
            addCriterion("RECORD_YEAR <>", value, "recordYear");
            return (Criteria) this;
        }

        public Criteria andRecordYearGreaterThan(String value) {
            addCriterion("RECORD_YEAR >", value, "recordYear");
            return (Criteria) this;
        }

        public Criteria andRecordYearGreaterThanOrEqualTo(String value) {
            addCriterion("RECORD_YEAR >=", value, "recordYear");
            return (Criteria) this;
        }

        public Criteria andRecordYearLessThan(String value) {
            addCriterion("RECORD_YEAR <", value, "recordYear");
            return (Criteria) this;
        }

        public Criteria andRecordYearLessThanOrEqualTo(String value) {
            addCriterion("RECORD_YEAR <=", value, "recordYear");
            return (Criteria) this;
        }

        public Criteria andRecordYearLike(String value) {
            addCriterion("RECORD_YEAR like", value, "recordYear");
            return (Criteria) this;
        }

        public Criteria andRecordYearNotLike(String value) {
            addCriterion("RECORD_YEAR not like", value, "recordYear");
            return (Criteria) this;
        }

        public Criteria andRecordYearIn(List<String> values) {
            addCriterion("RECORD_YEAR in", values, "recordYear");
            return (Criteria) this;
        }

        public Criteria andRecordYearNotIn(List<String> values) {
            addCriterion("RECORD_YEAR not in", values, "recordYear");
            return (Criteria) this;
        }

        public Criteria andRecordYearBetween(String value1, String value2) {
            addCriterion("RECORD_YEAR between", value1, value2, "recordYear");
            return (Criteria) this;
        }

        public Criteria andRecordYearNotBetween(String value1, String value2) {
            addCriterion("RECORD_YEAR not between", value1, value2, "recordYear");
            return (Criteria) this;
        }

        public Criteria andRoomRecordFlowIsNull() {
            addCriterion("ROOM_RECORD_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRoomRecordFlowIsNotNull() {
            addCriterion("ROOM_RECORD_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRoomRecordFlowEqualTo(String value) {
            addCriterion("ROOM_RECORD_FLOW =", value, "roomRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRoomRecordFlowNotEqualTo(String value) {
            addCriterion("ROOM_RECORD_FLOW <>", value, "roomRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRoomRecordFlowGreaterThan(String value) {
            addCriterion("ROOM_RECORD_FLOW >", value, "roomRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRoomRecordFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ROOM_RECORD_FLOW >=", value, "roomRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRoomRecordFlowLessThan(String value) {
            addCriterion("ROOM_RECORD_FLOW <", value, "roomRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRoomRecordFlowLessThanOrEqualTo(String value) {
            addCriterion("ROOM_RECORD_FLOW <=", value, "roomRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRoomRecordFlowLike(String value) {
            addCriterion("ROOM_RECORD_FLOW like", value, "roomRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRoomRecordFlowNotLike(String value) {
            addCriterion("ROOM_RECORD_FLOW not like", value, "roomRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRoomRecordFlowIn(List<String> values) {
            addCriterion("ROOM_RECORD_FLOW in", values, "roomRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRoomRecordFlowNotIn(List<String> values) {
            addCriterion("ROOM_RECORD_FLOW not in", values, "roomRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRoomRecordFlowBetween(String value1, String value2) {
            addCriterion("ROOM_RECORD_FLOW between", value1, value2, "roomRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRoomRecordFlowNotBetween(String value1, String value2) {
            addCriterion("ROOM_RECORD_FLOW not between", value1, value2, "roomRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRoomFlowIsNull() {
            addCriterion("ROOM_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRoomFlowIsNotNull() {
            addCriterion("ROOM_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRoomFlowEqualTo(String value) {
            addCriterion("ROOM_FLOW =", value, "roomFlow");
            return (Criteria) this;
        }

        public Criteria andRoomFlowNotEqualTo(String value) {
            addCriterion("ROOM_FLOW <>", value, "roomFlow");
            return (Criteria) this;
        }

        public Criteria andRoomFlowGreaterThan(String value) {
            addCriterion("ROOM_FLOW >", value, "roomFlow");
            return (Criteria) this;
        }

        public Criteria andRoomFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ROOM_FLOW >=", value, "roomFlow");
            return (Criteria) this;
        }

        public Criteria andRoomFlowLessThan(String value) {
            addCriterion("ROOM_FLOW <", value, "roomFlow");
            return (Criteria) this;
        }

        public Criteria andRoomFlowLessThanOrEqualTo(String value) {
            addCriterion("ROOM_FLOW <=", value, "roomFlow");
            return (Criteria) this;
        }

        public Criteria andRoomFlowLike(String value) {
            addCriterion("ROOM_FLOW like", value, "roomFlow");
            return (Criteria) this;
        }

        public Criteria andRoomFlowNotLike(String value) {
            addCriterion("ROOM_FLOW not like", value, "roomFlow");
            return (Criteria) this;
        }

        public Criteria andRoomFlowIn(List<String> values) {
            addCriterion("ROOM_FLOW in", values, "roomFlow");
            return (Criteria) this;
        }

        public Criteria andRoomFlowNotIn(List<String> values) {
            addCriterion("ROOM_FLOW not in", values, "roomFlow");
            return (Criteria) this;
        }

        public Criteria andRoomFlowBetween(String value1, String value2) {
            addCriterion("ROOM_FLOW between", value1, value2, "roomFlow");
            return (Criteria) this;
        }

        public Criteria andRoomFlowNotBetween(String value1, String value2) {
            addCriterion("ROOM_FLOW not between", value1, value2, "roomFlow");
            return (Criteria) this;
        }

        public Criteria andRoomNameIsNull() {
            addCriterion("ROOM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRoomNameIsNotNull() {
            addCriterion("ROOM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRoomNameEqualTo(String value) {
            addCriterion("ROOM_NAME =", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameNotEqualTo(String value) {
            addCriterion("ROOM_NAME <>", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameGreaterThan(String value) {
            addCriterion("ROOM_NAME >", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameGreaterThanOrEqualTo(String value) {
            addCriterion("ROOM_NAME >=", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameLessThan(String value) {
            addCriterion("ROOM_NAME <", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameLessThanOrEqualTo(String value) {
            addCriterion("ROOM_NAME <=", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameLike(String value) {
            addCriterion("ROOM_NAME like", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameNotLike(String value) {
            addCriterion("ROOM_NAME not like", value, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameIn(List<String> values) {
            addCriterion("ROOM_NAME in", values, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameNotIn(List<String> values) {
            addCriterion("ROOM_NAME not in", values, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameBetween(String value1, String value2) {
            addCriterion("ROOM_NAME between", value1, value2, "roomName");
            return (Criteria) this;
        }

        public Criteria andRoomNameNotBetween(String value1, String value2) {
            addCriterion("ROOM_NAME not between", value1, value2, "roomName");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowIsNull() {
            addCriterion("CLINICAL_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowIsNotNull() {
            addCriterion("CLINICAL_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowEqualTo(String value) {
            addCriterion("CLINICAL_FLOW =", value, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowNotEqualTo(String value) {
            addCriterion("CLINICAL_FLOW <>", value, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowGreaterThan(String value) {
            addCriterion("CLINICAL_FLOW >", value, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CLINICAL_FLOW >=", value, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowLessThan(String value) {
            addCriterion("CLINICAL_FLOW <", value, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowLessThanOrEqualTo(String value) {
            addCriterion("CLINICAL_FLOW <=", value, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowLike(String value) {
            addCriterion("CLINICAL_FLOW like", value, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowNotLike(String value) {
            addCriterion("CLINICAL_FLOW not like", value, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowIn(List<String> values) {
            addCriterion("CLINICAL_FLOW in", values, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowNotIn(List<String> values) {
            addCriterion("CLINICAL_FLOW not in", values, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowBetween(String value1, String value2) {
            addCriterion("CLINICAL_FLOW between", value1, value2, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalFlowNotBetween(String value1, String value2) {
            addCriterion("CLINICAL_FLOW not between", value1, value2, "clinicalFlow");
            return (Criteria) this;
        }

        public Criteria andClinicalNameIsNull() {
            addCriterion("CLINICAL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andClinicalNameIsNotNull() {
            addCriterion("CLINICAL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andClinicalNameEqualTo(String value) {
            addCriterion("CLINICAL_NAME =", value, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameNotEqualTo(String value) {
            addCriterion("CLINICAL_NAME <>", value, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameGreaterThan(String value) {
            addCriterion("CLINICAL_NAME >", value, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameGreaterThanOrEqualTo(String value) {
            addCriterion("CLINICAL_NAME >=", value, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameLessThan(String value) {
            addCriterion("CLINICAL_NAME <", value, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameLessThanOrEqualTo(String value) {
            addCriterion("CLINICAL_NAME <=", value, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameLike(String value) {
            addCriterion("CLINICAL_NAME like", value, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameNotLike(String value) {
            addCriterion("CLINICAL_NAME not like", value, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameIn(List<String> values) {
            addCriterion("CLINICAL_NAME in", values, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameNotIn(List<String> values) {
            addCriterion("CLINICAL_NAME not in", values, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameBetween(String value1, String value2) {
            addCriterion("CLINICAL_NAME between", value1, value2, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andClinicalNameNotBetween(String value1, String value2) {
            addCriterion("CLINICAL_NAME not between", value1, value2, "clinicalName");
            return (Criteria) this;
        }

        public Criteria andStationFlowIsNull() {
            addCriterion("STATION_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andStationFlowIsNotNull() {
            addCriterion("STATION_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andStationFlowEqualTo(String value) {
            addCriterion("STATION_FLOW =", value, "stationFlow");
            return (Criteria) this;
        }

        public Criteria andStationFlowNotEqualTo(String value) {
            addCriterion("STATION_FLOW <>", value, "stationFlow");
            return (Criteria) this;
        }

        public Criteria andStationFlowGreaterThan(String value) {
            addCriterion("STATION_FLOW >", value, "stationFlow");
            return (Criteria) this;
        }

        public Criteria andStationFlowGreaterThanOrEqualTo(String value) {
            addCriterion("STATION_FLOW >=", value, "stationFlow");
            return (Criteria) this;
        }

        public Criteria andStationFlowLessThan(String value) {
            addCriterion("STATION_FLOW <", value, "stationFlow");
            return (Criteria) this;
        }

        public Criteria andStationFlowLessThanOrEqualTo(String value) {
            addCriterion("STATION_FLOW <=", value, "stationFlow");
            return (Criteria) this;
        }

        public Criteria andStationFlowLike(String value) {
            addCriterion("STATION_FLOW like", value, "stationFlow");
            return (Criteria) this;
        }

        public Criteria andStationFlowNotLike(String value) {
            addCriterion("STATION_FLOW not like", value, "stationFlow");
            return (Criteria) this;
        }

        public Criteria andStationFlowIn(List<String> values) {
            addCriterion("STATION_FLOW in", values, "stationFlow");
            return (Criteria) this;
        }

        public Criteria andStationFlowNotIn(List<String> values) {
            addCriterion("STATION_FLOW not in", values, "stationFlow");
            return (Criteria) this;
        }

        public Criteria andStationFlowBetween(String value1, String value2) {
            addCriterion("STATION_FLOW between", value1, value2, "stationFlow");
            return (Criteria) this;
        }

        public Criteria andStationFlowNotBetween(String value1, String value2) {
            addCriterion("STATION_FLOW not between", value1, value2, "stationFlow");
            return (Criteria) this;
        }

        public Criteria andStationNameIsNull() {
            addCriterion("STATION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStationNameIsNotNull() {
            addCriterion("STATION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStationNameEqualTo(String value) {
            addCriterion("STATION_NAME =", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameNotEqualTo(String value) {
            addCriterion("STATION_NAME <>", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameGreaterThan(String value) {
            addCriterion("STATION_NAME >", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameGreaterThanOrEqualTo(String value) {
            addCriterion("STATION_NAME >=", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameLessThan(String value) {
            addCriterion("STATION_NAME <", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameLessThanOrEqualTo(String value) {
            addCriterion("STATION_NAME <=", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameLike(String value) {
            addCriterion("STATION_NAME like", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameNotLike(String value) {
            addCriterion("STATION_NAME not like", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameIn(List<String> values) {
            addCriterion("STATION_NAME in", values, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameNotIn(List<String> values) {
            addCriterion("STATION_NAME not in", values, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameBetween(String value1, String value2) {
            addCriterion("STATION_NAME between", value1, value2, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameNotBetween(String value1, String value2) {
            addCriterion("STATION_NAME not between", value1, value2, "stationName");
            return (Criteria) this;
        }

        public Criteria andIsHaveFromIsNull() {
            addCriterion("IS_HAVE_FROM is null");
            return (Criteria) this;
        }

        public Criteria andIsHaveFromIsNotNull() {
            addCriterion("IS_HAVE_FROM is not null");
            return (Criteria) this;
        }

        public Criteria andIsHaveFromEqualTo(String value) {
            addCriterion("IS_HAVE_FROM =", value, "isHaveFrom");
            return (Criteria) this;
        }

        public Criteria andIsHaveFromNotEqualTo(String value) {
            addCriterion("IS_HAVE_FROM <>", value, "isHaveFrom");
            return (Criteria) this;
        }

        public Criteria andIsHaveFromGreaterThan(String value) {
            addCriterion("IS_HAVE_FROM >", value, "isHaveFrom");
            return (Criteria) this;
        }

        public Criteria andIsHaveFromGreaterThanOrEqualTo(String value) {
            addCriterion("IS_HAVE_FROM >=", value, "isHaveFrom");
            return (Criteria) this;
        }

        public Criteria andIsHaveFromLessThan(String value) {
            addCriterion("IS_HAVE_FROM <", value, "isHaveFrom");
            return (Criteria) this;
        }

        public Criteria andIsHaveFromLessThanOrEqualTo(String value) {
            addCriterion("IS_HAVE_FROM <=", value, "isHaveFrom");
            return (Criteria) this;
        }

        public Criteria andIsHaveFromLike(String value) {
            addCriterion("IS_HAVE_FROM like", value, "isHaveFrom");
            return (Criteria) this;
        }

        public Criteria andIsHaveFromNotLike(String value) {
            addCriterion("IS_HAVE_FROM not like", value, "isHaveFrom");
            return (Criteria) this;
        }

        public Criteria andIsHaveFromIn(List<String> values) {
            addCriterion("IS_HAVE_FROM in", values, "isHaveFrom");
            return (Criteria) this;
        }

        public Criteria andIsHaveFromNotIn(List<String> values) {
            addCriterion("IS_HAVE_FROM not in", values, "isHaveFrom");
            return (Criteria) this;
        }

        public Criteria andIsHaveFromBetween(String value1, String value2) {
            addCriterion("IS_HAVE_FROM between", value1, value2, "isHaveFrom");
            return (Criteria) this;
        }

        public Criteria andIsHaveFromNotBetween(String value1, String value2) {
            addCriterion("IS_HAVE_FROM not between", value1, value2, "isHaveFrom");
            return (Criteria) this;
        }

        public Criteria andFromFlowIsNull() {
            addCriterion("FROM_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andFromFlowIsNotNull() {
            addCriterion("FROM_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andFromFlowEqualTo(String value) {
            addCriterion("FROM_FLOW =", value, "fromFlow");
            return (Criteria) this;
        }

        public Criteria andFromFlowNotEqualTo(String value) {
            addCriterion("FROM_FLOW <>", value, "fromFlow");
            return (Criteria) this;
        }

        public Criteria andFromFlowGreaterThan(String value) {
            addCriterion("FROM_FLOW >", value, "fromFlow");
            return (Criteria) this;
        }

        public Criteria andFromFlowGreaterThanOrEqualTo(String value) {
            addCriterion("FROM_FLOW >=", value, "fromFlow");
            return (Criteria) this;
        }

        public Criteria andFromFlowLessThan(String value) {
            addCriterion("FROM_FLOW <", value, "fromFlow");
            return (Criteria) this;
        }

        public Criteria andFromFlowLessThanOrEqualTo(String value) {
            addCriterion("FROM_FLOW <=", value, "fromFlow");
            return (Criteria) this;
        }

        public Criteria andFromFlowLike(String value) {
            addCriterion("FROM_FLOW like", value, "fromFlow");
            return (Criteria) this;
        }

        public Criteria andFromFlowNotLike(String value) {
            addCriterion("FROM_FLOW not like", value, "fromFlow");
            return (Criteria) this;
        }

        public Criteria andFromFlowIn(List<String> values) {
            addCriterion("FROM_FLOW in", values, "fromFlow");
            return (Criteria) this;
        }

        public Criteria andFromFlowNotIn(List<String> values) {
            addCriterion("FROM_FLOW not in", values, "fromFlow");
            return (Criteria) this;
        }

        public Criteria andFromFlowBetween(String value1, String value2) {
            addCriterion("FROM_FLOW between", value1, value2, "fromFlow");
            return (Criteria) this;
        }

        public Criteria andFromFlowNotBetween(String value1, String value2) {
            addCriterion("FROM_FLOW not between", value1, value2, "fromFlow");
            return (Criteria) this;
        }

        public Criteria andFromNameIsNull() {
            addCriterion("FROM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFromNameIsNotNull() {
            addCriterion("FROM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFromNameEqualTo(String value) {
            addCriterion("FROM_NAME =", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameNotEqualTo(String value) {
            addCriterion("FROM_NAME <>", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameGreaterThan(String value) {
            addCriterion("FROM_NAME >", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameGreaterThanOrEqualTo(String value) {
            addCriterion("FROM_NAME >=", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameLessThan(String value) {
            addCriterion("FROM_NAME <", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameLessThanOrEqualTo(String value) {
            addCriterion("FROM_NAME <=", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameLike(String value) {
            addCriterion("FROM_NAME like", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameNotLike(String value) {
            addCriterion("FROM_NAME not like", value, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameIn(List<String> values) {
            addCriterion("FROM_NAME in", values, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameNotIn(List<String> values) {
            addCriterion("FROM_NAME not in", values, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameBetween(String value1, String value2) {
            addCriterion("FROM_NAME between", value1, value2, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromNameNotBetween(String value1, String value2) {
            addCriterion("FROM_NAME not between", value1, value2, "fromName");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdIsNull() {
            addCriterion("FROM_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdIsNotNull() {
            addCriterion("FROM_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdEqualTo(String value) {
            addCriterion("FROM_TYPE_ID =", value, "fromTypeId");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdNotEqualTo(String value) {
            addCriterion("FROM_TYPE_ID <>", value, "fromTypeId");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdGreaterThan(String value) {
            addCriterion("FROM_TYPE_ID >", value, "fromTypeId");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("FROM_TYPE_ID >=", value, "fromTypeId");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdLessThan(String value) {
            addCriterion("FROM_TYPE_ID <", value, "fromTypeId");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdLessThanOrEqualTo(String value) {
            addCriterion("FROM_TYPE_ID <=", value, "fromTypeId");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdLike(String value) {
            addCriterion("FROM_TYPE_ID like", value, "fromTypeId");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdNotLike(String value) {
            addCriterion("FROM_TYPE_ID not like", value, "fromTypeId");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdIn(List<String> values) {
            addCriterion("FROM_TYPE_ID in", values, "fromTypeId");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdNotIn(List<String> values) {
            addCriterion("FROM_TYPE_ID not in", values, "fromTypeId");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdBetween(String value1, String value2) {
            addCriterion("FROM_TYPE_ID between", value1, value2, "fromTypeId");
            return (Criteria) this;
        }

        public Criteria andFromTypeIdNotBetween(String value1, String value2) {
            addCriterion("FROM_TYPE_ID not between", value1, value2, "fromTypeId");
            return (Criteria) this;
        }

        public Criteria andFromUrlIsNull() {
            addCriterion("FROM_URL is null");
            return (Criteria) this;
        }

        public Criteria andFromUrlIsNotNull() {
            addCriterion("FROM_URL is not null");
            return (Criteria) this;
        }

        public Criteria andFromUrlEqualTo(String value) {
            addCriterion("FROM_URL =", value, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlNotEqualTo(String value) {
            addCriterion("FROM_URL <>", value, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlGreaterThan(String value) {
            addCriterion("FROM_URL >", value, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlGreaterThanOrEqualTo(String value) {
            addCriterion("FROM_URL >=", value, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlLessThan(String value) {
            addCriterion("FROM_URL <", value, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlLessThanOrEqualTo(String value) {
            addCriterion("FROM_URL <=", value, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlLike(String value) {
            addCriterion("FROM_URL like", value, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlNotLike(String value) {
            addCriterion("FROM_URL not like", value, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlIn(List<String> values) {
            addCriterion("FROM_URL in", values, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlNotIn(List<String> values) {
            addCriterion("FROM_URL not in", values, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlBetween(String value1, String value2) {
            addCriterion("FROM_URL between", value1, value2, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andFromUrlNotBetween(String value1, String value2) {
            addCriterion("FROM_URL not between", value1, value2, "fromUrl");
            return (Criteria) this;
        }

        public Criteria andExamScoreIsNull() {
            addCriterion("EXAM_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andExamScoreIsNotNull() {
            addCriterion("EXAM_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andExamScoreEqualTo(String value) {
            addCriterion("EXAM_SCORE =", value, "examScore");
            return (Criteria) this;
        }

        public Criteria andExamScoreNotEqualTo(String value) {
            addCriterion("EXAM_SCORE <>", value, "examScore");
            return (Criteria) this;
        }

        public Criteria andExamScoreGreaterThan(String value) {
            addCriterion("EXAM_SCORE >", value, "examScore");
            return (Criteria) this;
        }

        public Criteria andExamScoreGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_SCORE >=", value, "examScore");
            return (Criteria) this;
        }

        public Criteria andExamScoreLessThan(String value) {
            addCriterion("EXAM_SCORE <", value, "examScore");
            return (Criteria) this;
        }

        public Criteria andExamScoreLessThanOrEqualTo(String value) {
            addCriterion("EXAM_SCORE <=", value, "examScore");
            return (Criteria) this;
        }

        public Criteria andExamScoreLike(String value) {
            addCriterion("EXAM_SCORE like", value, "examScore");
            return (Criteria) this;
        }

        public Criteria andExamScoreNotLike(String value) {
            addCriterion("EXAM_SCORE not like", value, "examScore");
            return (Criteria) this;
        }

        public Criteria andExamScoreIn(List<String> values) {
            addCriterion("EXAM_SCORE in", values, "examScore");
            return (Criteria) this;
        }

        public Criteria andExamScoreNotIn(List<String> values) {
            addCriterion("EXAM_SCORE not in", values, "examScore");
            return (Criteria) this;
        }

        public Criteria andExamScoreBetween(String value1, String value2) {
            addCriterion("EXAM_SCORE between", value1, value2, "examScore");
            return (Criteria) this;
        }

        public Criteria andExamScoreNotBetween(String value1, String value2) {
            addCriterion("EXAM_SCORE not between", value1, value2, "examScore");
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

        public Criteria andPartnerFlowIsNull() {
            addCriterion("PARTNER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andPartnerFlowIsNotNull() {
            addCriterion("PARTNER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerFlowEqualTo(String value) {
            addCriterion("PARTNER_FLOW =", value, "partnerFlow");
            return (Criteria) this;
        }

        public Criteria andPartnerFlowNotEqualTo(String value) {
            addCriterion("PARTNER_FLOW <>", value, "partnerFlow");
            return (Criteria) this;
        }

        public Criteria andPartnerFlowGreaterThan(String value) {
            addCriterion("PARTNER_FLOW >", value, "partnerFlow");
            return (Criteria) this;
        }

        public Criteria andPartnerFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PARTNER_FLOW >=", value, "partnerFlow");
            return (Criteria) this;
        }

        public Criteria andPartnerFlowLessThan(String value) {
            addCriterion("PARTNER_FLOW <", value, "partnerFlow");
            return (Criteria) this;
        }

        public Criteria andPartnerFlowLessThanOrEqualTo(String value) {
            addCriterion("PARTNER_FLOW <=", value, "partnerFlow");
            return (Criteria) this;
        }

        public Criteria andPartnerFlowLike(String value) {
            addCriterion("PARTNER_FLOW like", value, "partnerFlow");
            return (Criteria) this;
        }

        public Criteria andPartnerFlowNotLike(String value) {
            addCriterion("PARTNER_FLOW not like", value, "partnerFlow");
            return (Criteria) this;
        }

        public Criteria andPartnerFlowIn(List<String> values) {
            addCriterion("PARTNER_FLOW in", values, "partnerFlow");
            return (Criteria) this;
        }

        public Criteria andPartnerFlowNotIn(List<String> values) {
            addCriterion("PARTNER_FLOW not in", values, "partnerFlow");
            return (Criteria) this;
        }

        public Criteria andPartnerFlowBetween(String value1, String value2) {
            addCriterion("PARTNER_FLOW between", value1, value2, "partnerFlow");
            return (Criteria) this;
        }

        public Criteria andPartnerFlowNotBetween(String value1, String value2) {
            addCriterion("PARTNER_FLOW not between", value1, value2, "partnerFlow");
            return (Criteria) this;
        }

        public Criteria andPartnerNameIsNull() {
            addCriterion("PARTNER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPartnerNameIsNotNull() {
            addCriterion("PARTNER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerNameEqualTo(String value) {
            addCriterion("PARTNER_NAME =", value, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameNotEqualTo(String value) {
            addCriterion("PARTNER_NAME <>", value, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameGreaterThan(String value) {
            addCriterion("PARTNER_NAME >", value, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameGreaterThanOrEqualTo(String value) {
            addCriterion("PARTNER_NAME >=", value, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameLessThan(String value) {
            addCriterion("PARTNER_NAME <", value, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameLessThanOrEqualTo(String value) {
            addCriterion("PARTNER_NAME <=", value, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameLike(String value) {
            addCriterion("PARTNER_NAME like", value, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameNotLike(String value) {
            addCriterion("PARTNER_NAME not like", value, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameIn(List<String> values) {
            addCriterion("PARTNER_NAME in", values, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameNotIn(List<String> values) {
            addCriterion("PARTNER_NAME not in", values, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameBetween(String value1, String value2) {
            addCriterion("PARTNER_NAME between", value1, value2, "partnerName");
            return (Criteria) this;
        }

        public Criteria andPartnerNameNotBetween(String value1, String value2) {
            addCriterion("PARTNER_NAME not between", value1, value2, "partnerName");
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

        public Criteria andSiginImageIsNull() {
            addCriterion("SIGIN_IMAGE is null");
            return (Criteria) this;
        }

        public Criteria andSiginImageIsNotNull() {
            addCriterion("SIGIN_IMAGE is not null");
            return (Criteria) this;
        }

        public Criteria andSiginImageEqualTo(String value) {
            addCriterion("SIGIN_IMAGE =", value, "siginImage");
            return (Criteria) this;
        }

        public Criteria andSiginImageNotEqualTo(String value) {
            addCriterion("SIGIN_IMAGE <>", value, "siginImage");
            return (Criteria) this;
        }

        public Criteria andSiginImageGreaterThan(String value) {
            addCriterion("SIGIN_IMAGE >", value, "siginImage");
            return (Criteria) this;
        }

        public Criteria andSiginImageGreaterThanOrEqualTo(String value) {
            addCriterion("SIGIN_IMAGE >=", value, "siginImage");
            return (Criteria) this;
        }

        public Criteria andSiginImageLessThan(String value) {
            addCriterion("SIGIN_IMAGE <", value, "siginImage");
            return (Criteria) this;
        }

        public Criteria andSiginImageLessThanOrEqualTo(String value) {
            addCriterion("SIGIN_IMAGE <=", value, "siginImage");
            return (Criteria) this;
        }

        public Criteria andSiginImageLike(String value) {
            addCriterion("SIGIN_IMAGE like", value, "siginImage");
            return (Criteria) this;
        }

        public Criteria andSiginImageNotLike(String value) {
            addCriterion("SIGIN_IMAGE not like", value, "siginImage");
            return (Criteria) this;
        }

        public Criteria andSiginImageIn(List<String> values) {
            addCriterion("SIGIN_IMAGE in", values, "siginImage");
            return (Criteria) this;
        }

        public Criteria andSiginImageNotIn(List<String> values) {
            addCriterion("SIGIN_IMAGE not in", values, "siginImage");
            return (Criteria) this;
        }

        public Criteria andSiginImageBetween(String value1, String value2) {
            addCriterion("SIGIN_IMAGE between", value1, value2, "siginImage");
            return (Criteria) this;
        }

        public Criteria andSiginImageNotBetween(String value1, String value2) {
            addCriterion("SIGIN_IMAGE not between", value1, value2, "siginImage");
            return (Criteria) this;
        }

        public Criteria andIsRequiredIsNull() {
            addCriterion("IS_REQUIRED is null");
            return (Criteria) this;
        }

        public Criteria andIsRequiredIsNotNull() {
            addCriterion("IS_REQUIRED is not null");
            return (Criteria) this;
        }

        public Criteria andIsRequiredEqualTo(String value) {
            addCriterion("IS_REQUIRED =", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredNotEqualTo(String value) {
            addCriterion("IS_REQUIRED <>", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredGreaterThan(String value) {
            addCriterion("IS_REQUIRED >", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredGreaterThanOrEqualTo(String value) {
            addCriterion("IS_REQUIRED >=", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredLessThan(String value) {
            addCriterion("IS_REQUIRED <", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredLessThanOrEqualTo(String value) {
            addCriterion("IS_REQUIRED <=", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredLike(String value) {
            addCriterion("IS_REQUIRED like", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredNotLike(String value) {
            addCriterion("IS_REQUIRED not like", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredIn(List<String> values) {
            addCriterion("IS_REQUIRED in", values, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredNotIn(List<String> values) {
            addCriterion("IS_REQUIRED not in", values, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredBetween(String value1, String value2) {
            addCriterion("IS_REQUIRED between", value1, value2, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredNotBetween(String value1, String value2) {
            addCriterion("IS_REQUIRED not between", value1, value2, "isRequired");
            return (Criteria) this;
        }

        public Criteria andFromScoreIsNull() {
            addCriterion("FROM_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andFromScoreIsNotNull() {
            addCriterion("FROM_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andFromScoreEqualTo(String value) {
            addCriterion("FROM_SCORE =", value, "fromScore");
            return (Criteria) this;
        }

        public Criteria andFromScoreNotEqualTo(String value) {
            addCriterion("FROM_SCORE <>", value, "fromScore");
            return (Criteria) this;
        }

        public Criteria andFromScoreGreaterThan(String value) {
            addCriterion("FROM_SCORE >", value, "fromScore");
            return (Criteria) this;
        }

        public Criteria andFromScoreGreaterThanOrEqualTo(String value) {
            addCriterion("FROM_SCORE >=", value, "fromScore");
            return (Criteria) this;
        }

        public Criteria andFromScoreLessThan(String value) {
            addCriterion("FROM_SCORE <", value, "fromScore");
            return (Criteria) this;
        }

        public Criteria andFromScoreLessThanOrEqualTo(String value) {
            addCriterion("FROM_SCORE <=", value, "fromScore");
            return (Criteria) this;
        }

        public Criteria andFromScoreLike(String value) {
            addCriterion("FROM_SCORE like", value, "fromScore");
            return (Criteria) this;
        }

        public Criteria andFromScoreNotLike(String value) {
            addCriterion("FROM_SCORE not like", value, "fromScore");
            return (Criteria) this;
        }

        public Criteria andFromScoreIn(List<String> values) {
            addCriterion("FROM_SCORE in", values, "fromScore");
            return (Criteria) this;
        }

        public Criteria andFromScoreNotIn(List<String> values) {
            addCriterion("FROM_SCORE not in", values, "fromScore");
            return (Criteria) this;
        }

        public Criteria andFromScoreBetween(String value1, String value2) {
            addCriterion("FROM_SCORE between", value1, value2, "fromScore");
            return (Criteria) this;
        }

        public Criteria andFromScoreNotBetween(String value1, String value2) {
            addCriterion("FROM_SCORE not between", value1, value2, "fromScore");
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