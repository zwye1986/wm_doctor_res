package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class OscaSkillRoomTeaExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OscaSkillRoomTeaExample() {
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