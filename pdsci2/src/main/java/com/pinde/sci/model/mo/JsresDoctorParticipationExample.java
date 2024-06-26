package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class JsresDoctorParticipationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JsresDoctorParticipationExample() {
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

        public Criteria andParticipationDateIsNull() {
            addCriterion("PARTICIPATION_DATE is null");
            return (Criteria) this;
        }

        public Criteria andParticipationDateIsNotNull() {
            addCriterion("PARTICIPATION_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andParticipationDateEqualTo(String value) {
            addCriterion("PARTICIPATION_DATE =", value, "participationDate");
            return (Criteria) this;
        }

        public Criteria andParticipationDateNotEqualTo(String value) {
            addCriterion("PARTICIPATION_DATE <>", value, "participationDate");
            return (Criteria) this;
        }

        public Criteria andParticipationDateGreaterThan(String value) {
            addCriterion("PARTICIPATION_DATE >", value, "participationDate");
            return (Criteria) this;
        }

        public Criteria andParticipationDateGreaterThanOrEqualTo(String value) {
            addCriterion("PARTICIPATION_DATE >=", value, "participationDate");
            return (Criteria) this;
        }

        public Criteria andParticipationDateLessThan(String value) {
            addCriterion("PARTICIPATION_DATE <", value, "participationDate");
            return (Criteria) this;
        }

        public Criteria andParticipationDateLessThanOrEqualTo(String value) {
            addCriterion("PARTICIPATION_DATE <=", value, "participationDate");
            return (Criteria) this;
        }

        public Criteria andParticipationDateLike(String value) {
            addCriterion("PARTICIPATION_DATE like", value, "participationDate");
            return (Criteria) this;
        }

        public Criteria andParticipationDateNotLike(String value) {
            addCriterion("PARTICIPATION_DATE not like", value, "participationDate");
            return (Criteria) this;
        }

        public Criteria andParticipationDateIn(List<String> values) {
            addCriterion("PARTICIPATION_DATE in", values, "participationDate");
            return (Criteria) this;
        }

        public Criteria andParticipationDateNotIn(List<String> values) {
            addCriterion("PARTICIPATION_DATE not in", values, "participationDate");
            return (Criteria) this;
        }

        public Criteria andParticipationDateBetween(String value1, String value2) {
            addCriterion("PARTICIPATION_DATE between", value1, value2, "participationDate");
            return (Criteria) this;
        }

        public Criteria andParticipationDateNotBetween(String value1, String value2) {
            addCriterion("PARTICIPATION_DATE not between", value1, value2, "participationDate");
            return (Criteria) this;
        }

        public Criteria andParticipationRoomIsNull() {
            addCriterion("PARTICIPATION_ROOM is null");
            return (Criteria) this;
        }

        public Criteria andParticipationRoomIsNotNull() {
            addCriterion("PARTICIPATION_ROOM is not null");
            return (Criteria) this;
        }

        public Criteria andParticipationRoomEqualTo(String value) {
            addCriterion("PARTICIPATION_ROOM =", value, "participationRoom");
            return (Criteria) this;
        }

        public Criteria andParticipationRoomNotEqualTo(String value) {
            addCriterion("PARTICIPATION_ROOM <>", value, "participationRoom");
            return (Criteria) this;
        }

        public Criteria andParticipationRoomGreaterThan(String value) {
            addCriterion("PARTICIPATION_ROOM >", value, "participationRoom");
            return (Criteria) this;
        }

        public Criteria andParticipationRoomGreaterThanOrEqualTo(String value) {
            addCriterion("PARTICIPATION_ROOM >=", value, "participationRoom");
            return (Criteria) this;
        }

        public Criteria andParticipationRoomLessThan(String value) {
            addCriterion("PARTICIPATION_ROOM <", value, "participationRoom");
            return (Criteria) this;
        }

        public Criteria andParticipationRoomLessThanOrEqualTo(String value) {
            addCriterion("PARTICIPATION_ROOM <=", value, "participationRoom");
            return (Criteria) this;
        }

        public Criteria andParticipationRoomLike(String value) {
            addCriterion("PARTICIPATION_ROOM like", value, "participationRoom");
            return (Criteria) this;
        }

        public Criteria andParticipationRoomNotLike(String value) {
            addCriterion("PARTICIPATION_ROOM not like", value, "participationRoom");
            return (Criteria) this;
        }

        public Criteria andParticipationRoomIn(List<String> values) {
            addCriterion("PARTICIPATION_ROOM in", values, "participationRoom");
            return (Criteria) this;
        }

        public Criteria andParticipationRoomNotIn(List<String> values) {
            addCriterion("PARTICIPATION_ROOM not in", values, "participationRoom");
            return (Criteria) this;
        }

        public Criteria andParticipationRoomBetween(String value1, String value2) {
            addCriterion("PARTICIPATION_ROOM between", value1, value2, "participationRoom");
            return (Criteria) this;
        }

        public Criteria andParticipationRoomNotBetween(String value1, String value2) {
            addCriterion("PARTICIPATION_ROOM not between", value1, value2, "participationRoom");
            return (Criteria) this;
        }

        public Criteria andParticipationTitleIsNull() {
            addCriterion("PARTICIPATION_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andParticipationTitleIsNotNull() {
            addCriterion("PARTICIPATION_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andParticipationTitleEqualTo(String value) {
            addCriterion("PARTICIPATION_TITLE =", value, "participationTitle");
            return (Criteria) this;
        }

        public Criteria andParticipationTitleNotEqualTo(String value) {
            addCriterion("PARTICIPATION_TITLE <>", value, "participationTitle");
            return (Criteria) this;
        }

        public Criteria andParticipationTitleGreaterThan(String value) {
            addCriterion("PARTICIPATION_TITLE >", value, "participationTitle");
            return (Criteria) this;
        }

        public Criteria andParticipationTitleGreaterThanOrEqualTo(String value) {
            addCriterion("PARTICIPATION_TITLE >=", value, "participationTitle");
            return (Criteria) this;
        }

        public Criteria andParticipationTitleLessThan(String value) {
            addCriterion("PARTICIPATION_TITLE <", value, "participationTitle");
            return (Criteria) this;
        }

        public Criteria andParticipationTitleLessThanOrEqualTo(String value) {
            addCriterion("PARTICIPATION_TITLE <=", value, "participationTitle");
            return (Criteria) this;
        }

        public Criteria andParticipationTitleLike(String value) {
            addCriterion("PARTICIPATION_TITLE like", value, "participationTitle");
            return (Criteria) this;
        }

        public Criteria andParticipationTitleNotLike(String value) {
            addCriterion("PARTICIPATION_TITLE not like", value, "participationTitle");
            return (Criteria) this;
        }

        public Criteria andParticipationTitleIn(List<String> values) {
            addCriterion("PARTICIPATION_TITLE in", values, "participationTitle");
            return (Criteria) this;
        }

        public Criteria andParticipationTitleNotIn(List<String> values) {
            addCriterion("PARTICIPATION_TITLE not in", values, "participationTitle");
            return (Criteria) this;
        }

        public Criteria andParticipationTitleBetween(String value1, String value2) {
            addCriterion("PARTICIPATION_TITLE between", value1, value2, "participationTitle");
            return (Criteria) this;
        }

        public Criteria andParticipationTitleNotBetween(String value1, String value2) {
            addCriterion("PARTICIPATION_TITLE not between", value1, value2, "participationTitle");
            return (Criteria) this;
        }

        public Criteria andParticipationRoleIsNull() {
            addCriterion("PARTICIPATION_ROLE is null");
            return (Criteria) this;
        }

        public Criteria andParticipationRoleIsNotNull() {
            addCriterion("PARTICIPATION_ROLE is not null");
            return (Criteria) this;
        }

        public Criteria andParticipationRoleEqualTo(String value) {
            addCriterion("PARTICIPATION_ROLE =", value, "participationRole");
            return (Criteria) this;
        }

        public Criteria andParticipationRoleNotEqualTo(String value) {
            addCriterion("PARTICIPATION_ROLE <>", value, "participationRole");
            return (Criteria) this;
        }

        public Criteria andParticipationRoleGreaterThan(String value) {
            addCriterion("PARTICIPATION_ROLE >", value, "participationRole");
            return (Criteria) this;
        }

        public Criteria andParticipationRoleGreaterThanOrEqualTo(String value) {
            addCriterion("PARTICIPATION_ROLE >=", value, "participationRole");
            return (Criteria) this;
        }

        public Criteria andParticipationRoleLessThan(String value) {
            addCriterion("PARTICIPATION_ROLE <", value, "participationRole");
            return (Criteria) this;
        }

        public Criteria andParticipationRoleLessThanOrEqualTo(String value) {
            addCriterion("PARTICIPATION_ROLE <=", value, "participationRole");
            return (Criteria) this;
        }

        public Criteria andParticipationRoleLike(String value) {
            addCriterion("PARTICIPATION_ROLE like", value, "participationRole");
            return (Criteria) this;
        }

        public Criteria andParticipationRoleNotLike(String value) {
            addCriterion("PARTICIPATION_ROLE not like", value, "participationRole");
            return (Criteria) this;
        }

        public Criteria andParticipationRoleIn(List<String> values) {
            addCriterion("PARTICIPATION_ROLE in", values, "participationRole");
            return (Criteria) this;
        }

        public Criteria andParticipationRoleNotIn(List<String> values) {
            addCriterion("PARTICIPATION_ROLE not in", values, "participationRole");
            return (Criteria) this;
        }

        public Criteria andParticipationRoleBetween(String value1, String value2) {
            addCriterion("PARTICIPATION_ROLE between", value1, value2, "participationRole");
            return (Criteria) this;
        }

        public Criteria andParticipationRoleNotBetween(String value1, String value2) {
            addCriterion("PARTICIPATION_ROLE not between", value1, value2, "participationRole");
            return (Criteria) this;
        }

        public Criteria andParticipationCompleteIsNull() {
            addCriterion("PARTICIPATION_COMPLETE is null");
            return (Criteria) this;
        }

        public Criteria andParticipationCompleteIsNotNull() {
            addCriterion("PARTICIPATION_COMPLETE is not null");
            return (Criteria) this;
        }

        public Criteria andParticipationCompleteEqualTo(String value) {
            addCriterion("PARTICIPATION_COMPLETE =", value, "participationComplete");
            return (Criteria) this;
        }

        public Criteria andParticipationCompleteNotEqualTo(String value) {
            addCriterion("PARTICIPATION_COMPLETE <>", value, "participationComplete");
            return (Criteria) this;
        }

        public Criteria andParticipationCompleteGreaterThan(String value) {
            addCriterion("PARTICIPATION_COMPLETE >", value, "participationComplete");
            return (Criteria) this;
        }

        public Criteria andParticipationCompleteGreaterThanOrEqualTo(String value) {
            addCriterion("PARTICIPATION_COMPLETE >=", value, "participationComplete");
            return (Criteria) this;
        }

        public Criteria andParticipationCompleteLessThan(String value) {
            addCriterion("PARTICIPATION_COMPLETE <", value, "participationComplete");
            return (Criteria) this;
        }

        public Criteria andParticipationCompleteLessThanOrEqualTo(String value) {
            addCriterion("PARTICIPATION_COMPLETE <=", value, "participationComplete");
            return (Criteria) this;
        }

        public Criteria andParticipationCompleteLike(String value) {
            addCriterion("PARTICIPATION_COMPLETE like", value, "participationComplete");
            return (Criteria) this;
        }

        public Criteria andParticipationCompleteNotLike(String value) {
            addCriterion("PARTICIPATION_COMPLETE not like", value, "participationComplete");
            return (Criteria) this;
        }

        public Criteria andParticipationCompleteIn(List<String> values) {
            addCriterion("PARTICIPATION_COMPLETE in", values, "participationComplete");
            return (Criteria) this;
        }

        public Criteria andParticipationCompleteNotIn(List<String> values) {
            addCriterion("PARTICIPATION_COMPLETE not in", values, "participationComplete");
            return (Criteria) this;
        }

        public Criteria andParticipationCompleteBetween(String value1, String value2) {
            addCriterion("PARTICIPATION_COMPLETE between", value1, value2, "participationComplete");
            return (Criteria) this;
        }

        public Criteria andParticipationCompleteNotBetween(String value1, String value2) {
            addCriterion("PARTICIPATION_COMPLETE not between", value1, value2, "participationComplete");
            return (Criteria) this;
        }

        public Criteria andParticipationAuthorIsNull() {
            addCriterion("PARTICIPATION_AUTHOR is null");
            return (Criteria) this;
        }

        public Criteria andParticipationAuthorIsNotNull() {
            addCriterion("PARTICIPATION_AUTHOR is not null");
            return (Criteria) this;
        }

        public Criteria andParticipationAuthorEqualTo(String value) {
            addCriterion("PARTICIPATION_AUTHOR =", value, "participationAuthor");
            return (Criteria) this;
        }

        public Criteria andParticipationAuthorNotEqualTo(String value) {
            addCriterion("PARTICIPATION_AUTHOR <>", value, "participationAuthor");
            return (Criteria) this;
        }

        public Criteria andParticipationAuthorGreaterThan(String value) {
            addCriterion("PARTICIPATION_AUTHOR >", value, "participationAuthor");
            return (Criteria) this;
        }

        public Criteria andParticipationAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("PARTICIPATION_AUTHOR >=", value, "participationAuthor");
            return (Criteria) this;
        }

        public Criteria andParticipationAuthorLessThan(String value) {
            addCriterion("PARTICIPATION_AUTHOR <", value, "participationAuthor");
            return (Criteria) this;
        }

        public Criteria andParticipationAuthorLessThanOrEqualTo(String value) {
            addCriterion("PARTICIPATION_AUTHOR <=", value, "participationAuthor");
            return (Criteria) this;
        }

        public Criteria andParticipationAuthorLike(String value) {
            addCriterion("PARTICIPATION_AUTHOR like", value, "participationAuthor");
            return (Criteria) this;
        }

        public Criteria andParticipationAuthorNotLike(String value) {
            addCriterion("PARTICIPATION_AUTHOR not like", value, "participationAuthor");
            return (Criteria) this;
        }

        public Criteria andParticipationAuthorIn(List<String> values) {
            addCriterion("PARTICIPATION_AUTHOR in", values, "participationAuthor");
            return (Criteria) this;
        }

        public Criteria andParticipationAuthorNotIn(List<String> values) {
            addCriterion("PARTICIPATION_AUTHOR not in", values, "participationAuthor");
            return (Criteria) this;
        }

        public Criteria andParticipationAuthorBetween(String value1, String value2) {
            addCriterion("PARTICIPATION_AUTHOR between", value1, value2, "participationAuthor");
            return (Criteria) this;
        }

        public Criteria andParticipationAuthorNotBetween(String value1, String value2) {
            addCriterion("PARTICIPATION_AUTHOR not between", value1, value2, "participationAuthor");
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