package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class OscaSkillRoomDocExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OscaSkillRoomDocExample() {
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

        public Criteria andDocRoomFlowIsNull() {
            addCriterion("DOC_ROOM_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDocRoomFlowIsNotNull() {
            addCriterion("DOC_ROOM_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDocRoomFlowEqualTo(String value) {
            addCriterion("DOC_ROOM_FLOW =", value, "docRoomFlow");
            return (Criteria) this;
        }

        public Criteria andDocRoomFlowNotEqualTo(String value) {
            addCriterion("DOC_ROOM_FLOW <>", value, "docRoomFlow");
            return (Criteria) this;
        }

        public Criteria andDocRoomFlowGreaterThan(String value) {
            addCriterion("DOC_ROOM_FLOW >", value, "docRoomFlow");
            return (Criteria) this;
        }

        public Criteria andDocRoomFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DOC_ROOM_FLOW >=", value, "docRoomFlow");
            return (Criteria) this;
        }

        public Criteria andDocRoomFlowLessThan(String value) {
            addCriterion("DOC_ROOM_FLOW <", value, "docRoomFlow");
            return (Criteria) this;
        }

        public Criteria andDocRoomFlowLessThanOrEqualTo(String value) {
            addCriterion("DOC_ROOM_FLOW <=", value, "docRoomFlow");
            return (Criteria) this;
        }

        public Criteria andDocRoomFlowLike(String value) {
            addCriterion("DOC_ROOM_FLOW like", value, "docRoomFlow");
            return (Criteria) this;
        }

        public Criteria andDocRoomFlowNotLike(String value) {
            addCriterion("DOC_ROOM_FLOW not like", value, "docRoomFlow");
            return (Criteria) this;
        }

        public Criteria andDocRoomFlowIn(List<String> values) {
            addCriterion("DOC_ROOM_FLOW in", values, "docRoomFlow");
            return (Criteria) this;
        }

        public Criteria andDocRoomFlowNotIn(List<String> values) {
            addCriterion("DOC_ROOM_FLOW not in", values, "docRoomFlow");
            return (Criteria) this;
        }

        public Criteria andDocRoomFlowBetween(String value1, String value2) {
            addCriterion("DOC_ROOM_FLOW between", value1, value2, "docRoomFlow");
            return (Criteria) this;
        }

        public Criteria andDocRoomFlowNotBetween(String value1, String value2) {
            addCriterion("DOC_ROOM_FLOW not between", value1, value2, "docRoomFlow");
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

        public Criteria andExamStatusIdIsNull() {
            addCriterion("EXAM_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdIsNotNull() {
            addCriterion("EXAM_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdEqualTo(String value) {
            addCriterion("EXAM_STATUS_ID =", value, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdNotEqualTo(String value) {
            addCriterion("EXAM_STATUS_ID <>", value, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdGreaterThan(String value) {
            addCriterion("EXAM_STATUS_ID >", value, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_STATUS_ID >=", value, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdLessThan(String value) {
            addCriterion("EXAM_STATUS_ID <", value, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdLessThanOrEqualTo(String value) {
            addCriterion("EXAM_STATUS_ID <=", value, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdLike(String value) {
            addCriterion("EXAM_STATUS_ID like", value, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdNotLike(String value) {
            addCriterion("EXAM_STATUS_ID not like", value, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdIn(List<String> values) {
            addCriterion("EXAM_STATUS_ID in", values, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdNotIn(List<String> values) {
            addCriterion("EXAM_STATUS_ID not in", values, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdBetween(String value1, String value2) {
            addCriterion("EXAM_STATUS_ID between", value1, value2, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdNotBetween(String value1, String value2) {
            addCriterion("EXAM_STATUS_ID not between", value1, value2, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameIsNull() {
            addCriterion("EXAM_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameIsNotNull() {
            addCriterion("EXAM_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameEqualTo(String value) {
            addCriterion("EXAM_STATUS_NAME =", value, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameNotEqualTo(String value) {
            addCriterion("EXAM_STATUS_NAME <>", value, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameGreaterThan(String value) {
            addCriterion("EXAM_STATUS_NAME >", value, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_STATUS_NAME >=", value, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameLessThan(String value) {
            addCriterion("EXAM_STATUS_NAME <", value, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameLessThanOrEqualTo(String value) {
            addCriterion("EXAM_STATUS_NAME <=", value, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameLike(String value) {
            addCriterion("EXAM_STATUS_NAME like", value, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameNotLike(String value) {
            addCriterion("EXAM_STATUS_NAME not like", value, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameIn(List<String> values) {
            addCriterion("EXAM_STATUS_NAME in", values, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameNotIn(List<String> values) {
            addCriterion("EXAM_STATUS_NAME not in", values, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameBetween(String value1, String value2) {
            addCriterion("EXAM_STATUS_NAME between", value1, value2, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameNotBetween(String value1, String value2) {
            addCriterion("EXAM_STATUS_NAME not between", value1, value2, "examStatusName");
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

        public Criteria andWaitingTimeIsNull() {
            addCriterion("WAITING_TIME is null");
            return (Criteria) this;
        }

        public Criteria andWaitingTimeIsNotNull() {
            addCriterion("WAITING_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andWaitingTimeEqualTo(String value) {
            addCriterion("WAITING_TIME =", value, "waitingTime");
            return (Criteria) this;
        }

        public Criteria andWaitingTimeNotEqualTo(String value) {
            addCriterion("WAITING_TIME <>", value, "waitingTime");
            return (Criteria) this;
        }

        public Criteria andWaitingTimeGreaterThan(String value) {
            addCriterion("WAITING_TIME >", value, "waitingTime");
            return (Criteria) this;
        }

        public Criteria andWaitingTimeGreaterThanOrEqualTo(String value) {
            addCriterion("WAITING_TIME >=", value, "waitingTime");
            return (Criteria) this;
        }

        public Criteria andWaitingTimeLessThan(String value) {
            addCriterion("WAITING_TIME <", value, "waitingTime");
            return (Criteria) this;
        }

        public Criteria andWaitingTimeLessThanOrEqualTo(String value) {
            addCriterion("WAITING_TIME <=", value, "waitingTime");
            return (Criteria) this;
        }

        public Criteria andWaitingTimeLike(String value) {
            addCriterion("WAITING_TIME like", value, "waitingTime");
            return (Criteria) this;
        }

        public Criteria andWaitingTimeNotLike(String value) {
            addCriterion("WAITING_TIME not like", value, "waitingTime");
            return (Criteria) this;
        }

        public Criteria andWaitingTimeIn(List<String> values) {
            addCriterion("WAITING_TIME in", values, "waitingTime");
            return (Criteria) this;
        }

        public Criteria andWaitingTimeNotIn(List<String> values) {
            addCriterion("WAITING_TIME not in", values, "waitingTime");
            return (Criteria) this;
        }

        public Criteria andWaitingTimeBetween(String value1, String value2) {
            addCriterion("WAITING_TIME between", value1, value2, "waitingTime");
            return (Criteria) this;
        }

        public Criteria andWaitingTimeNotBetween(String value1, String value2) {
            addCriterion("WAITING_TIME not between", value1, value2, "waitingTime");
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

        public Criteria andIsAdminAuditIsNull() {
            addCriterion("IS_ADMIN_AUDIT is null");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditIsNotNull() {
            addCriterion("IS_ADMIN_AUDIT is not null");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditEqualTo(String value) {
            addCriterion("IS_ADMIN_AUDIT =", value, "isAdminAudit");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditNotEqualTo(String value) {
            addCriterion("IS_ADMIN_AUDIT <>", value, "isAdminAudit");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditGreaterThan(String value) {
            addCriterion("IS_ADMIN_AUDIT >", value, "isAdminAudit");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditGreaterThanOrEqualTo(String value) {
            addCriterion("IS_ADMIN_AUDIT >=", value, "isAdminAudit");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditLessThan(String value) {
            addCriterion("IS_ADMIN_AUDIT <", value, "isAdminAudit");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditLessThanOrEqualTo(String value) {
            addCriterion("IS_ADMIN_AUDIT <=", value, "isAdminAudit");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditLike(String value) {
            addCriterion("IS_ADMIN_AUDIT like", value, "isAdminAudit");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditNotLike(String value) {
            addCriterion("IS_ADMIN_AUDIT not like", value, "isAdminAudit");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditIn(List<String> values) {
            addCriterion("IS_ADMIN_AUDIT in", values, "isAdminAudit");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditNotIn(List<String> values) {
            addCriterion("IS_ADMIN_AUDIT not in", values, "isAdminAudit");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditBetween(String value1, String value2) {
            addCriterion("IS_ADMIN_AUDIT between", value1, value2, "isAdminAudit");
            return (Criteria) this;
        }

        public Criteria andIsAdminAuditNotBetween(String value1, String value2) {
            addCriterion("IS_ADMIN_AUDIT not between", value1, value2, "isAdminAudit");
            return (Criteria) this;
        }

        public Criteria andExamSaveScoreIsNull() {
            addCriterion("EXAM_SAVE_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andExamSaveScoreIsNotNull() {
            addCriterion("EXAM_SAVE_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andExamSaveScoreEqualTo(String value) {
            addCriterion("EXAM_SAVE_SCORE =", value, "examSaveScore");
            return (Criteria) this;
        }

        public Criteria andExamSaveScoreNotEqualTo(String value) {
            addCriterion("EXAM_SAVE_SCORE <>", value, "examSaveScore");
            return (Criteria) this;
        }

        public Criteria andExamSaveScoreGreaterThan(String value) {
            addCriterion("EXAM_SAVE_SCORE >", value, "examSaveScore");
            return (Criteria) this;
        }

        public Criteria andExamSaveScoreGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_SAVE_SCORE >=", value, "examSaveScore");
            return (Criteria) this;
        }

        public Criteria andExamSaveScoreLessThan(String value) {
            addCriterion("EXAM_SAVE_SCORE <", value, "examSaveScore");
            return (Criteria) this;
        }

        public Criteria andExamSaveScoreLessThanOrEqualTo(String value) {
            addCriterion("EXAM_SAVE_SCORE <=", value, "examSaveScore");
            return (Criteria) this;
        }

        public Criteria andExamSaveScoreLike(String value) {
            addCriterion("EXAM_SAVE_SCORE like", value, "examSaveScore");
            return (Criteria) this;
        }

        public Criteria andExamSaveScoreNotLike(String value) {
            addCriterion("EXAM_SAVE_SCORE not like", value, "examSaveScore");
            return (Criteria) this;
        }

        public Criteria andExamSaveScoreIn(List<String> values) {
            addCriterion("EXAM_SAVE_SCORE in", values, "examSaveScore");
            return (Criteria) this;
        }

        public Criteria andExamSaveScoreNotIn(List<String> values) {
            addCriterion("EXAM_SAVE_SCORE not in", values, "examSaveScore");
            return (Criteria) this;
        }

        public Criteria andExamSaveScoreBetween(String value1, String value2) {
            addCriterion("EXAM_SAVE_SCORE between", value1, value2, "examSaveScore");
            return (Criteria) this;
        }

        public Criteria andExamSaveScoreNotBetween(String value1, String value2) {
            addCriterion("EXAM_SAVE_SCORE not between", value1, value2, "examSaveScore");
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