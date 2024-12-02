package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResDiscipleNoteInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResDiscipleNoteInfoExample() {
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

        public Criteria andStudyTimeIdIsNull() {
            addCriterion("STUDY_TIME_ID is null");
            return (Criteria) this;
        }

        public Criteria andStudyTimeIdIsNotNull() {
            addCriterion("STUDY_TIME_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStudyTimeIdEqualTo(String value) {
            addCriterion("STUDY_TIME_ID =", value, "studyTimeId");
            return (Criteria) this;
        }

        public Criteria andStudyTimeIdNotEqualTo(String value) {
            addCriterion("STUDY_TIME_ID <>", value, "studyTimeId");
            return (Criteria) this;
        }

        public Criteria andStudyTimeIdGreaterThan(String value) {
            addCriterion("STUDY_TIME_ID >", value, "studyTimeId");
            return (Criteria) this;
        }

        public Criteria andStudyTimeIdGreaterThanOrEqualTo(String value) {
            addCriterion("STUDY_TIME_ID >=", value, "studyTimeId");
            return (Criteria) this;
        }

        public Criteria andStudyTimeIdLessThan(String value) {
            addCriterion("STUDY_TIME_ID <", value, "studyTimeId");
            return (Criteria) this;
        }

        public Criteria andStudyTimeIdLessThanOrEqualTo(String value) {
            addCriterion("STUDY_TIME_ID <=", value, "studyTimeId");
            return (Criteria) this;
        }

        public Criteria andStudyTimeIdLike(String value) {
            addCriterion("STUDY_TIME_ID like", value, "studyTimeId");
            return (Criteria) this;
        }

        public Criteria andStudyTimeIdNotLike(String value) {
            addCriterion("STUDY_TIME_ID not like", value, "studyTimeId");
            return (Criteria) this;
        }

        public Criteria andStudyTimeIdIn(List<String> values) {
            addCriterion("STUDY_TIME_ID in", values, "studyTimeId");
            return (Criteria) this;
        }

        public Criteria andStudyTimeIdNotIn(List<String> values) {
            addCriterion("STUDY_TIME_ID not in", values, "studyTimeId");
            return (Criteria) this;
        }

        public Criteria andStudyTimeIdBetween(String value1, String value2) {
            addCriterion("STUDY_TIME_ID between", value1, value2, "studyTimeId");
            return (Criteria) this;
        }

        public Criteria andStudyTimeIdNotBetween(String value1, String value2) {
            addCriterion("STUDY_TIME_ID not between", value1, value2, "studyTimeId");
            return (Criteria) this;
        }

        public Criteria andStudyTimeNmaeIsNull() {
            addCriterion("STUDY_TIME_NMAE is null");
            return (Criteria) this;
        }

        public Criteria andStudyTimeNmaeIsNotNull() {
            addCriterion("STUDY_TIME_NMAE is not null");
            return (Criteria) this;
        }

        public Criteria andStudyTimeNmaeEqualTo(String value) {
            addCriterion("STUDY_TIME_NMAE =", value, "studyTimeNmae");
            return (Criteria) this;
        }

        public Criteria andStudyTimeNmaeNotEqualTo(String value) {
            addCriterion("STUDY_TIME_NMAE <>", value, "studyTimeNmae");
            return (Criteria) this;
        }

        public Criteria andStudyTimeNmaeGreaterThan(String value) {
            addCriterion("STUDY_TIME_NMAE >", value, "studyTimeNmae");
            return (Criteria) this;
        }

        public Criteria andStudyTimeNmaeGreaterThanOrEqualTo(String value) {
            addCriterion("STUDY_TIME_NMAE >=", value, "studyTimeNmae");
            return (Criteria) this;
        }

        public Criteria andStudyTimeNmaeLessThan(String value) {
            addCriterion("STUDY_TIME_NMAE <", value, "studyTimeNmae");
            return (Criteria) this;
        }

        public Criteria andStudyTimeNmaeLessThanOrEqualTo(String value) {
            addCriterion("STUDY_TIME_NMAE <=", value, "studyTimeNmae");
            return (Criteria) this;
        }

        public Criteria andStudyTimeNmaeLike(String value) {
            addCriterion("STUDY_TIME_NMAE like", value, "studyTimeNmae");
            return (Criteria) this;
        }

        public Criteria andStudyTimeNmaeNotLike(String value) {
            addCriterion("STUDY_TIME_NMAE not like", value, "studyTimeNmae");
            return (Criteria) this;
        }

        public Criteria andStudyTimeNmaeIn(List<String> values) {
            addCriterion("STUDY_TIME_NMAE in", values, "studyTimeNmae");
            return (Criteria) this;
        }

        public Criteria andStudyTimeNmaeNotIn(List<String> values) {
            addCriterion("STUDY_TIME_NMAE not in", values, "studyTimeNmae");
            return (Criteria) this;
        }

        public Criteria andStudyTimeNmaeBetween(String value1, String value2) {
            addCriterion("STUDY_TIME_NMAE between", value1, value2, "studyTimeNmae");
            return (Criteria) this;
        }

        public Criteria andStudyTimeNmaeNotBetween(String value1, String value2) {
            addCriterion("STUDY_TIME_NMAE not between", value1, value2, "studyTimeNmae");
            return (Criteria) this;
        }

        public Criteria andStudyStartDateIsNull() {
            addCriterion("STUDY_START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStudyStartDateIsNotNull() {
            addCriterion("STUDY_START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStudyStartDateEqualTo(String value) {
            addCriterion("STUDY_START_DATE =", value, "studyStartDate");
            return (Criteria) this;
        }

        public Criteria andStudyStartDateNotEqualTo(String value) {
            addCriterion("STUDY_START_DATE <>", value, "studyStartDate");
            return (Criteria) this;
        }

        public Criteria andStudyStartDateGreaterThan(String value) {
            addCriterion("STUDY_START_DATE >", value, "studyStartDate");
            return (Criteria) this;
        }

        public Criteria andStudyStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("STUDY_START_DATE >=", value, "studyStartDate");
            return (Criteria) this;
        }

        public Criteria andStudyStartDateLessThan(String value) {
            addCriterion("STUDY_START_DATE <", value, "studyStartDate");
            return (Criteria) this;
        }

        public Criteria andStudyStartDateLessThanOrEqualTo(String value) {
            addCriterion("STUDY_START_DATE <=", value, "studyStartDate");
            return (Criteria) this;
        }

        public Criteria andStudyStartDateLike(String value) {
            addCriterion("STUDY_START_DATE like", value, "studyStartDate");
            return (Criteria) this;
        }

        public Criteria andStudyStartDateNotLike(String value) {
            addCriterion("STUDY_START_DATE not like", value, "studyStartDate");
            return (Criteria) this;
        }

        public Criteria andStudyStartDateIn(List<String> values) {
            addCriterion("STUDY_START_DATE in", values, "studyStartDate");
            return (Criteria) this;
        }

        public Criteria andStudyStartDateNotIn(List<String> values) {
            addCriterion("STUDY_START_DATE not in", values, "studyStartDate");
            return (Criteria) this;
        }

        public Criteria andStudyStartDateBetween(String value1, String value2) {
            addCriterion("STUDY_START_DATE between", value1, value2, "studyStartDate");
            return (Criteria) this;
        }

        public Criteria andStudyStartDateNotBetween(String value1, String value2) {
            addCriterion("STUDY_START_DATE not between", value1, value2, "studyStartDate");
            return (Criteria) this;
        }

        public Criteria andStudyEndDateIsNull() {
            addCriterion("STUDY_END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStudyEndDateIsNotNull() {
            addCriterion("STUDY_END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStudyEndDateEqualTo(String value) {
            addCriterion("STUDY_END_DATE =", value, "studyEndDate");
            return (Criteria) this;
        }

        public Criteria andStudyEndDateNotEqualTo(String value) {
            addCriterion("STUDY_END_DATE <>", value, "studyEndDate");
            return (Criteria) this;
        }

        public Criteria andStudyEndDateGreaterThan(String value) {
            addCriterion("STUDY_END_DATE >", value, "studyEndDate");
            return (Criteria) this;
        }

        public Criteria andStudyEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("STUDY_END_DATE >=", value, "studyEndDate");
            return (Criteria) this;
        }

        public Criteria andStudyEndDateLessThan(String value) {
            addCriterion("STUDY_END_DATE <", value, "studyEndDate");
            return (Criteria) this;
        }

        public Criteria andStudyEndDateLessThanOrEqualTo(String value) {
            addCriterion("STUDY_END_DATE <=", value, "studyEndDate");
            return (Criteria) this;
        }

        public Criteria andStudyEndDateLike(String value) {
            addCriterion("STUDY_END_DATE like", value, "studyEndDate");
            return (Criteria) this;
        }

        public Criteria andStudyEndDateNotLike(String value) {
            addCriterion("STUDY_END_DATE not like", value, "studyEndDate");
            return (Criteria) this;
        }

        public Criteria andStudyEndDateIn(List<String> values) {
            addCriterion("STUDY_END_DATE in", values, "studyEndDate");
            return (Criteria) this;
        }

        public Criteria andStudyEndDateNotIn(List<String> values) {
            addCriterion("STUDY_END_DATE not in", values, "studyEndDate");
            return (Criteria) this;
        }

        public Criteria andStudyEndDateBetween(String value1, String value2) {
            addCriterion("STUDY_END_DATE between", value1, value2, "studyEndDate");
            return (Criteria) this;
        }

        public Criteria andStudyEndDateNotBetween(String value1, String value2) {
            addCriterion("STUDY_END_DATE not between", value1, value2, "studyEndDate");
            return (Criteria) this;
        }

        public Criteria andNoteTypeIdIsNull() {
            addCriterion("NOTE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andNoteTypeIdIsNotNull() {
            addCriterion("NOTE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andNoteTypeIdEqualTo(String value) {
            addCriterion("NOTE_TYPE_ID =", value, "noteTypeId");
            return (Criteria) this;
        }

        public Criteria andNoteTypeIdNotEqualTo(String value) {
            addCriterion("NOTE_TYPE_ID <>", value, "noteTypeId");
            return (Criteria) this;
        }

        public Criteria andNoteTypeIdGreaterThan(String value) {
            addCriterion("NOTE_TYPE_ID >", value, "noteTypeId");
            return (Criteria) this;
        }

        public Criteria andNoteTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("NOTE_TYPE_ID >=", value, "noteTypeId");
            return (Criteria) this;
        }

        public Criteria andNoteTypeIdLessThan(String value) {
            addCriterion("NOTE_TYPE_ID <", value, "noteTypeId");
            return (Criteria) this;
        }

        public Criteria andNoteTypeIdLessThanOrEqualTo(String value) {
            addCriterion("NOTE_TYPE_ID <=", value, "noteTypeId");
            return (Criteria) this;
        }

        public Criteria andNoteTypeIdLike(String value) {
            addCriterion("NOTE_TYPE_ID like", value, "noteTypeId");
            return (Criteria) this;
        }

        public Criteria andNoteTypeIdNotLike(String value) {
            addCriterion("NOTE_TYPE_ID not like", value, "noteTypeId");
            return (Criteria) this;
        }

        public Criteria andNoteTypeIdIn(List<String> values) {
            addCriterion("NOTE_TYPE_ID in", values, "noteTypeId");
            return (Criteria) this;
        }

        public Criteria andNoteTypeIdNotIn(List<String> values) {
            addCriterion("NOTE_TYPE_ID not in", values, "noteTypeId");
            return (Criteria) this;
        }

        public Criteria andNoteTypeIdBetween(String value1, String value2) {
            addCriterion("NOTE_TYPE_ID between", value1, value2, "noteTypeId");
            return (Criteria) this;
        }

        public Criteria andNoteTypeIdNotBetween(String value1, String value2) {
            addCriterion("NOTE_TYPE_ID not between", value1, value2, "noteTypeId");
            return (Criteria) this;
        }

        public Criteria andNoteTypeNameIsNull() {
            addCriterion("NOTE_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andNoteTypeNameIsNotNull() {
            addCriterion("NOTE_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNoteTypeNameEqualTo(String value) {
            addCriterion("NOTE_TYPE_NAME =", value, "noteTypeName");
            return (Criteria) this;
        }

        public Criteria andNoteTypeNameNotEqualTo(String value) {
            addCriterion("NOTE_TYPE_NAME <>", value, "noteTypeName");
            return (Criteria) this;
        }

        public Criteria andNoteTypeNameGreaterThan(String value) {
            addCriterion("NOTE_TYPE_NAME >", value, "noteTypeName");
            return (Criteria) this;
        }

        public Criteria andNoteTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("NOTE_TYPE_NAME >=", value, "noteTypeName");
            return (Criteria) this;
        }

        public Criteria andNoteTypeNameLessThan(String value) {
            addCriterion("NOTE_TYPE_NAME <", value, "noteTypeName");
            return (Criteria) this;
        }

        public Criteria andNoteTypeNameLessThanOrEqualTo(String value) {
            addCriterion("NOTE_TYPE_NAME <=", value, "noteTypeName");
            return (Criteria) this;
        }

        public Criteria andNoteTypeNameLike(String value) {
            addCriterion("NOTE_TYPE_NAME like", value, "noteTypeName");
            return (Criteria) this;
        }

        public Criteria andNoteTypeNameNotLike(String value) {
            addCriterion("NOTE_TYPE_NAME not like", value, "noteTypeName");
            return (Criteria) this;
        }

        public Criteria andNoteTypeNameIn(List<String> values) {
            addCriterion("NOTE_TYPE_NAME in", values, "noteTypeName");
            return (Criteria) this;
        }

        public Criteria andNoteTypeNameNotIn(List<String> values) {
            addCriterion("NOTE_TYPE_NAME not in", values, "noteTypeName");
            return (Criteria) this;
        }

        public Criteria andNoteTypeNameBetween(String value1, String value2) {
            addCriterion("NOTE_TYPE_NAME between", value1, value2, "noteTypeName");
            return (Criteria) this;
        }

        public Criteria andNoteTypeNameNotBetween(String value1, String value2) {
            addCriterion("NOTE_TYPE_NAME not between", value1, value2, "noteTypeName");
            return (Criteria) this;
        }

        public Criteria andStudentSignTimeIsNull() {
            addCriterion("STUDENT_SIGN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andStudentSignTimeIsNotNull() {
            addCriterion("STUDENT_SIGN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andStudentSignTimeEqualTo(String value) {
            addCriterion("STUDENT_SIGN_TIME =", value, "studentSignTime");
            return (Criteria) this;
        }

        public Criteria andStudentSignTimeNotEqualTo(String value) {
            addCriterion("STUDENT_SIGN_TIME <>", value, "studentSignTime");
            return (Criteria) this;
        }

        public Criteria andStudentSignTimeGreaterThan(String value) {
            addCriterion("STUDENT_SIGN_TIME >", value, "studentSignTime");
            return (Criteria) this;
        }

        public Criteria andStudentSignTimeGreaterThanOrEqualTo(String value) {
            addCriterion("STUDENT_SIGN_TIME >=", value, "studentSignTime");
            return (Criteria) this;
        }

        public Criteria andStudentSignTimeLessThan(String value) {
            addCriterion("STUDENT_SIGN_TIME <", value, "studentSignTime");
            return (Criteria) this;
        }

        public Criteria andStudentSignTimeLessThanOrEqualTo(String value) {
            addCriterion("STUDENT_SIGN_TIME <=", value, "studentSignTime");
            return (Criteria) this;
        }

        public Criteria andStudentSignTimeLike(String value) {
            addCriterion("STUDENT_SIGN_TIME like", value, "studentSignTime");
            return (Criteria) this;
        }

        public Criteria andStudentSignTimeNotLike(String value) {
            addCriterion("STUDENT_SIGN_TIME not like", value, "studentSignTime");
            return (Criteria) this;
        }

        public Criteria andStudentSignTimeIn(List<String> values) {
            addCriterion("STUDENT_SIGN_TIME in", values, "studentSignTime");
            return (Criteria) this;
        }

        public Criteria andStudentSignTimeNotIn(List<String> values) {
            addCriterion("STUDENT_SIGN_TIME not in", values, "studentSignTime");
            return (Criteria) this;
        }

        public Criteria andStudentSignTimeBetween(String value1, String value2) {
            addCriterion("STUDENT_SIGN_TIME between", value1, value2, "studentSignTime");
            return (Criteria) this;
        }

        public Criteria andStudentSignTimeNotBetween(String value1, String value2) {
            addCriterion("STUDENT_SIGN_TIME not between", value1, value2, "studentSignTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNull() {
            addCriterion("AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNotNull() {
            addCriterion("AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeEqualTo(String value) {
            addCriterion("AUDIT_TIME =", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotEqualTo(String value) {
            addCriterion("AUDIT_TIME <>", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThan(String value) {
            addCriterion("AUDIT_TIME >", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_TIME >=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThan(String value) {
            addCriterion("AUDIT_TIME <", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_TIME <=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLike(String value) {
            addCriterion("AUDIT_TIME like", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotLike(String value) {
            addCriterion("AUDIT_TIME not like", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIn(List<String> values) {
            addCriterion("AUDIT_TIME in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotIn(List<String> values) {
            addCriterion("AUDIT_TIME not in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeBetween(String value1, String value2) {
            addCriterion("AUDIT_TIME between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotBetween(String value1, String value2) {
            addCriterion("AUDIT_TIME not between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowIsNull() {
            addCriterion("AUDIT_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowIsNotNull() {
            addCriterion("AUDIT_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowEqualTo(String value) {
            addCriterion("AUDIT_USER_FLOW =", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowNotEqualTo(String value) {
            addCriterion("AUDIT_USER_FLOW <>", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowGreaterThan(String value) {
            addCriterion("AUDIT_USER_FLOW >", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_USER_FLOW >=", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowLessThan(String value) {
            addCriterion("AUDIT_USER_FLOW <", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_USER_FLOW <=", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowLike(String value) {
            addCriterion("AUDIT_USER_FLOW like", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowNotLike(String value) {
            addCriterion("AUDIT_USER_FLOW not like", value, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowIn(List<String> values) {
            addCriterion("AUDIT_USER_FLOW in", values, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowNotIn(List<String> values) {
            addCriterion("AUDIT_USER_FLOW not in", values, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowBetween(String value1, String value2) {
            addCriterion("AUDIT_USER_FLOW between", value1, value2, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserFlowNotBetween(String value1, String value2) {
            addCriterion("AUDIT_USER_FLOW not between", value1, value2, "auditUserFlow");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameIsNull() {
            addCriterion("AUDIT_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameIsNotNull() {
            addCriterion("AUDIT_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameEqualTo(String value) {
            addCriterion("AUDIT_USER_NAME =", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotEqualTo(String value) {
            addCriterion("AUDIT_USER_NAME <>", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameGreaterThan(String value) {
            addCriterion("AUDIT_USER_NAME >", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_USER_NAME >=", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameLessThan(String value) {
            addCriterion("AUDIT_USER_NAME <", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_USER_NAME <=", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameLike(String value) {
            addCriterion("AUDIT_USER_NAME like", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotLike(String value) {
            addCriterion("AUDIT_USER_NAME not like", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameIn(List<String> values) {
            addCriterion("AUDIT_USER_NAME in", values, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotIn(List<String> values) {
            addCriterion("AUDIT_USER_NAME not in", values, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameBetween(String value1, String value2) {
            addCriterion("AUDIT_USER_NAME between", value1, value2, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotBetween(String value1, String value2) {
            addCriterion("AUDIT_USER_NAME not between", value1, value2, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdIsNull() {
            addCriterion("AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdIsNotNull() {
            addCriterion("AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID =", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID <>", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdGreaterThan(String value) {
            addCriterion("AUDIT_STATUS_ID >", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID >=", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdLessThan(String value) {
            addCriterion("AUDIT_STATUS_ID <", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID <=", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdLike(String value) {
            addCriterion("AUDIT_STATUS_ID like", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotLike(String value) {
            addCriterion("AUDIT_STATUS_ID not like", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdIn(List<String> values) {
            addCriterion("AUDIT_STATUS_ID in", values, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotIn(List<String> values) {
            addCriterion("AUDIT_STATUS_ID not in", values, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_ID between", value1, value2, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_ID not between", value1, value2, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameIsNull() {
            addCriterion("AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameIsNotNull() {
            addCriterion("AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME =", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME <>", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameGreaterThan(String value) {
            addCriterion("AUDIT_STATUS_NAME >", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME >=", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameLessThan(String value) {
            addCriterion("AUDIT_STATUS_NAME <", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME <=", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameLike(String value) {
            addCriterion("AUDIT_STATUS_NAME like", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotLike(String value) {
            addCriterion("AUDIT_STATUS_NAME not like", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameIn(List<String> values) {
            addCriterion("AUDIT_STATUS_NAME in", values, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotIn(List<String> values) {
            addCriterion("AUDIT_STATUS_NAME not in", values, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_NAME between", value1, value2, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_NAME not between", value1, value2, "auditStatusName");
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