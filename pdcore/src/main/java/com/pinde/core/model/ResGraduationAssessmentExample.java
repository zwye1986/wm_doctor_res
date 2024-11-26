package com.pinde.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ResGraduationAssessmentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResGraduationAssessmentExample() {
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

        public Criteria andDayCountIsNull() {
            addCriterion("DAY_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andDayCountIsNotNull() {
            addCriterion("DAY_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andDayCountEqualTo(Long value) {
            addCriterion("DAY_COUNT =", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountNotEqualTo(Long value) {
            addCriterion("DAY_COUNT <>", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountGreaterThan(Long value) {
            addCriterion("DAY_COUNT >", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountGreaterThanOrEqualTo(Long value) {
            addCriterion("DAY_COUNT >=", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountLessThan(Long value) {
            addCriterion("DAY_COUNT <", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountLessThanOrEqualTo(Long value) {
            addCriterion("DAY_COUNT <=", value, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountIn(List<Long> values) {
            addCriterion("DAY_COUNT in", values, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountNotIn(List<Long> values) {
            addCriterion("DAY_COUNT not in", values, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountBetween(Long value1, Long value2) {
            addCriterion("DAY_COUNT between", value1, value2, "dayCount");
            return (Criteria) this;
        }

        public Criteria andDayCountNotBetween(Long value1, Long value2) {
            addCriterion("DAY_COUNT not between", value1, value2, "dayCount");
            return (Criteria) this;
        }

        public Criteria andNoteCountIsNull() {
            addCriterion("NOTE_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andNoteCountIsNotNull() {
            addCriterion("NOTE_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andNoteCountEqualTo(Long value) {
            addCriterion("NOTE_COUNT =", value, "noteCount");
            return (Criteria) this;
        }

        public Criteria andNoteCountNotEqualTo(Long value) {
            addCriterion("NOTE_COUNT <>", value, "noteCount");
            return (Criteria) this;
        }

        public Criteria andNoteCountGreaterThan(Long value) {
            addCriterion("NOTE_COUNT >", value, "noteCount");
            return (Criteria) this;
        }

        public Criteria andNoteCountGreaterThanOrEqualTo(Long value) {
            addCriterion("NOTE_COUNT >=", value, "noteCount");
            return (Criteria) this;
        }

        public Criteria andNoteCountLessThan(Long value) {
            addCriterion("NOTE_COUNT <", value, "noteCount");
            return (Criteria) this;
        }

        public Criteria andNoteCountLessThanOrEqualTo(Long value) {
            addCriterion("NOTE_COUNT <=", value, "noteCount");
            return (Criteria) this;
        }

        public Criteria andNoteCountIn(List<Long> values) {
            addCriterion("NOTE_COUNT in", values, "noteCount");
            return (Criteria) this;
        }

        public Criteria andNoteCountNotIn(List<Long> values) {
            addCriterion("NOTE_COUNT not in", values, "noteCount");
            return (Criteria) this;
        }

        public Criteria andNoteCountBetween(Long value1, Long value2) {
            addCriterion("NOTE_COUNT between", value1, value2, "noteCount");
            return (Criteria) this;
        }

        public Criteria andNoteCountNotBetween(Long value1, Long value2) {
            addCriterion("NOTE_COUNT not between", value1, value2, "noteCount");
            return (Criteria) this;
        }

        public Criteria andExperienceCountIsNull() {
            addCriterion("EXPERIENCE_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andExperienceCountIsNotNull() {
            addCriterion("EXPERIENCE_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andExperienceCountEqualTo(Long value) {
            addCriterion("EXPERIENCE_COUNT =", value, "experienceCount");
            return (Criteria) this;
        }

        public Criteria andExperienceCountNotEqualTo(Long value) {
            addCriterion("EXPERIENCE_COUNT <>", value, "experienceCount");
            return (Criteria) this;
        }

        public Criteria andExperienceCountGreaterThan(Long value) {
            addCriterion("EXPERIENCE_COUNT >", value, "experienceCount");
            return (Criteria) this;
        }

        public Criteria andExperienceCountGreaterThanOrEqualTo(Long value) {
            addCriterion("EXPERIENCE_COUNT >=", value, "experienceCount");
            return (Criteria) this;
        }

        public Criteria andExperienceCountLessThan(Long value) {
            addCriterion("EXPERIENCE_COUNT <", value, "experienceCount");
            return (Criteria) this;
        }

        public Criteria andExperienceCountLessThanOrEqualTo(Long value) {
            addCriterion("EXPERIENCE_COUNT <=", value, "experienceCount");
            return (Criteria) this;
        }

        public Criteria andExperienceCountIn(List<Long> values) {
            addCriterion("EXPERIENCE_COUNT in", values, "experienceCount");
            return (Criteria) this;
        }

        public Criteria andExperienceCountNotIn(List<Long> values) {
            addCriterion("EXPERIENCE_COUNT not in", values, "experienceCount");
            return (Criteria) this;
        }

        public Criteria andExperienceCountBetween(Long value1, Long value2) {
            addCriterion("EXPERIENCE_COUNT between", value1, value2, "experienceCount");
            return (Criteria) this;
        }

        public Criteria andExperienceCountNotBetween(Long value1, Long value2) {
            addCriterion("EXPERIENCE_COUNT not between", value1, value2, "experienceCount");
            return (Criteria) this;
        }

        public Criteria andTcmCountIsNull() {
            addCriterion("TCM_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andTcmCountIsNotNull() {
            addCriterion("TCM_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andTcmCountEqualTo(Long value) {
            addCriterion("TCM_COUNT =", value, "tcmCount");
            return (Criteria) this;
        }

        public Criteria andTcmCountNotEqualTo(Long value) {
            addCriterion("TCM_COUNT <>", value, "tcmCount");
            return (Criteria) this;
        }

        public Criteria andTcmCountGreaterThan(Long value) {
            addCriterion("TCM_COUNT >", value, "tcmCount");
            return (Criteria) this;
        }

        public Criteria andTcmCountGreaterThanOrEqualTo(Long value) {
            addCriterion("TCM_COUNT >=", value, "tcmCount");
            return (Criteria) this;
        }

        public Criteria andTcmCountLessThan(Long value) {
            addCriterion("TCM_COUNT <", value, "tcmCount");
            return (Criteria) this;
        }

        public Criteria andTcmCountLessThanOrEqualTo(Long value) {
            addCriterion("TCM_COUNT <=", value, "tcmCount");
            return (Criteria) this;
        }

        public Criteria andTcmCountIn(List<Long> values) {
            addCriterion("TCM_COUNT in", values, "tcmCount");
            return (Criteria) this;
        }

        public Criteria andTcmCountNotIn(List<Long> values) {
            addCriterion("TCM_COUNT not in", values, "tcmCount");
            return (Criteria) this;
        }

        public Criteria andTcmCountBetween(Long value1, Long value2) {
            addCriterion("TCM_COUNT between", value1, value2, "tcmCount");
            return (Criteria) this;
        }

        public Criteria andTcmCountNotBetween(Long value1, Long value2) {
            addCriterion("TCM_COUNT not between", value1, value2, "tcmCount");
            return (Criteria) this;
        }

        public Criteria andXxthCountIsNull() {
            addCriterion("XXTH_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andXxthCountIsNotNull() {
            addCriterion("XXTH_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andXxthCountEqualTo(Long value) {
            addCriterion("XXTH_COUNT =", value, "xxthCount");
            return (Criteria) this;
        }

        public Criteria andXxthCountNotEqualTo(Long value) {
            addCriterion("XXTH_COUNT <>", value, "xxthCount");
            return (Criteria) this;
        }

        public Criteria andXxthCountGreaterThan(Long value) {
            addCriterion("XXTH_COUNT >", value, "xxthCount");
            return (Criteria) this;
        }

        public Criteria andXxthCountGreaterThanOrEqualTo(Long value) {
            addCriterion("XXTH_COUNT >=", value, "xxthCount");
            return (Criteria) this;
        }

        public Criteria andXxthCountLessThan(Long value) {
            addCriterion("XXTH_COUNT <", value, "xxthCount");
            return (Criteria) this;
        }

        public Criteria andXxthCountLessThanOrEqualTo(Long value) {
            addCriterion("XXTH_COUNT <=", value, "xxthCount");
            return (Criteria) this;
        }

        public Criteria andXxthCountIn(List<Long> values) {
            addCriterion("XXTH_COUNT in", values, "xxthCount");
            return (Criteria) this;
        }

        public Criteria andXxthCountNotIn(List<Long> values) {
            addCriterion("XXTH_COUNT not in", values, "xxthCount");
            return (Criteria) this;
        }

        public Criteria andXxthCountBetween(Long value1, Long value2) {
            addCriterion("XXTH_COUNT between", value1, value2, "xxthCount");
            return (Criteria) this;
        }

        public Criteria andXxthCountNotBetween(Long value1, Long value2) {
            addCriterion("XXTH_COUNT not between", value1, value2, "xxthCount");
            return (Criteria) this;
        }

        public Criteria andTypicalCasesCountIsNull() {
            addCriterion("TYPICAL_CASES_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andTypicalCasesCountIsNotNull() {
            addCriterion("TYPICAL_CASES_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andTypicalCasesCountEqualTo(Long value) {
            addCriterion("TYPICAL_CASES_COUNT =", value, "typicalCasesCount");
            return (Criteria) this;
        }

        public Criteria andTypicalCasesCountNotEqualTo(Long value) {
            addCriterion("TYPICAL_CASES_COUNT <>", value, "typicalCasesCount");
            return (Criteria) this;
        }

        public Criteria andTypicalCasesCountGreaterThan(Long value) {
            addCriterion("TYPICAL_CASES_COUNT >", value, "typicalCasesCount");
            return (Criteria) this;
        }

        public Criteria andTypicalCasesCountGreaterThanOrEqualTo(Long value) {
            addCriterion("TYPICAL_CASES_COUNT >=", value, "typicalCasesCount");
            return (Criteria) this;
        }

        public Criteria andTypicalCasesCountLessThan(Long value) {
            addCriterion("TYPICAL_CASES_COUNT <", value, "typicalCasesCount");
            return (Criteria) this;
        }

        public Criteria andTypicalCasesCountLessThanOrEqualTo(Long value) {
            addCriterion("TYPICAL_CASES_COUNT <=", value, "typicalCasesCount");
            return (Criteria) this;
        }

        public Criteria andTypicalCasesCountIn(List<Long> values) {
            addCriterion("TYPICAL_CASES_COUNT in", values, "typicalCasesCount");
            return (Criteria) this;
        }

        public Criteria andTypicalCasesCountNotIn(List<Long> values) {
            addCriterion("TYPICAL_CASES_COUNT not in", values, "typicalCasesCount");
            return (Criteria) this;
        }

        public Criteria andTypicalCasesCountBetween(Long value1, Long value2) {
            addCriterion("TYPICAL_CASES_COUNT between", value1, value2, "typicalCasesCount");
            return (Criteria) this;
        }

        public Criteria andTypicalCasesCountNotBetween(Long value1, Long value2) {
            addCriterion("TYPICAL_CASES_COUNT not between", value1, value2, "typicalCasesCount");
            return (Criteria) this;
        }

        public Criteria andGraduationThesisTitleIsNull() {
            addCriterion("GRADUATION_THESIS_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andGraduationThesisTitleIsNotNull() {
            addCriterion("GRADUATION_THESIS_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationThesisTitleEqualTo(String value) {
            addCriterion("GRADUATION_THESIS_TITLE =", value, "graduationThesisTitle");
            return (Criteria) this;
        }

        public Criteria andGraduationThesisTitleNotEqualTo(String value) {
            addCriterion("GRADUATION_THESIS_TITLE <>", value, "graduationThesisTitle");
            return (Criteria) this;
        }

        public Criteria andGraduationThesisTitleGreaterThan(String value) {
            addCriterion("GRADUATION_THESIS_TITLE >", value, "graduationThesisTitle");
            return (Criteria) this;
        }

        public Criteria andGraduationThesisTitleGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATION_THESIS_TITLE >=", value, "graduationThesisTitle");
            return (Criteria) this;
        }

        public Criteria andGraduationThesisTitleLessThan(String value) {
            addCriterion("GRADUATION_THESIS_TITLE <", value, "graduationThesisTitle");
            return (Criteria) this;
        }

        public Criteria andGraduationThesisTitleLessThanOrEqualTo(String value) {
            addCriterion("GRADUATION_THESIS_TITLE <=", value, "graduationThesisTitle");
            return (Criteria) this;
        }

        public Criteria andGraduationThesisTitleLike(String value) {
            addCriterion("GRADUATION_THESIS_TITLE like", value, "graduationThesisTitle");
            return (Criteria) this;
        }

        public Criteria andGraduationThesisTitleNotLike(String value) {
            addCriterion("GRADUATION_THESIS_TITLE not like", value, "graduationThesisTitle");
            return (Criteria) this;
        }

        public Criteria andGraduationThesisTitleIn(List<String> values) {
            addCriterion("GRADUATION_THESIS_TITLE in", values, "graduationThesisTitle");
            return (Criteria) this;
        }

        public Criteria andGraduationThesisTitleNotIn(List<String> values) {
            addCriterion("GRADUATION_THESIS_TITLE not in", values, "graduationThesisTitle");
            return (Criteria) this;
        }

        public Criteria andGraduationThesisTitleBetween(String value1, String value2) {
            addCriterion("GRADUATION_THESIS_TITLE between", value1, value2, "graduationThesisTitle");
            return (Criteria) this;
        }

        public Criteria andGraduationThesisTitleNotBetween(String value1, String value2) {
            addCriterion("GRADUATION_THESIS_TITLE not between", value1, value2, "graduationThesisTitle");
            return (Criteria) this;
        }

        public Criteria andAssessmentImgUrlIsNull() {
            addCriterion("ASSESSMENT_IMG_URL is null");
            return (Criteria) this;
        }

        public Criteria andAssessmentImgUrlIsNotNull() {
            addCriterion("ASSESSMENT_IMG_URL is not null");
            return (Criteria) this;
        }

        public Criteria andAssessmentImgUrlEqualTo(String value) {
            addCriterion("ASSESSMENT_IMG_URL =", value, "assessmentImgUrl");
            return (Criteria) this;
        }

        public Criteria andAssessmentImgUrlNotEqualTo(String value) {
            addCriterion("ASSESSMENT_IMG_URL <>", value, "assessmentImgUrl");
            return (Criteria) this;
        }

        public Criteria andAssessmentImgUrlGreaterThan(String value) {
            addCriterion("ASSESSMENT_IMG_URL >", value, "assessmentImgUrl");
            return (Criteria) this;
        }

        public Criteria andAssessmentImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("ASSESSMENT_IMG_URL >=", value, "assessmentImgUrl");
            return (Criteria) this;
        }

        public Criteria andAssessmentImgUrlLessThan(String value) {
            addCriterion("ASSESSMENT_IMG_URL <", value, "assessmentImgUrl");
            return (Criteria) this;
        }

        public Criteria andAssessmentImgUrlLessThanOrEqualTo(String value) {
            addCriterion("ASSESSMENT_IMG_URL <=", value, "assessmentImgUrl");
            return (Criteria) this;
        }

        public Criteria andAssessmentImgUrlLike(String value) {
            addCriterion("ASSESSMENT_IMG_URL like", value, "assessmentImgUrl");
            return (Criteria) this;
        }

        public Criteria andAssessmentImgUrlNotLike(String value) {
            addCriterion("ASSESSMENT_IMG_URL not like", value, "assessmentImgUrl");
            return (Criteria) this;
        }

        public Criteria andAssessmentImgUrlIn(List<String> values) {
            addCriterion("ASSESSMENT_IMG_URL in", values, "assessmentImgUrl");
            return (Criteria) this;
        }

        public Criteria andAssessmentImgUrlNotIn(List<String> values) {
            addCriterion("ASSESSMENT_IMG_URL not in", values, "assessmentImgUrl");
            return (Criteria) this;
        }

        public Criteria andAssessmentImgUrlBetween(String value1, String value2) {
            addCriterion("ASSESSMENT_IMG_URL between", value1, value2, "assessmentImgUrl");
            return (Criteria) this;
        }

        public Criteria andAssessmentImgUrlNotBetween(String value1, String value2) {
            addCriterion("ASSESSMENT_IMG_URL not between", value1, value2, "assessmentImgUrl");
            return (Criteria) this;
        }

        public Criteria andExperienceSignTimeIsNull() {
            addCriterion("EXPERIENCE_SIGN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andExperienceSignTimeIsNotNull() {
            addCriterion("EXPERIENCE_SIGN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andExperienceSignTimeEqualTo(String value) {
            addCriterion("EXPERIENCE_SIGN_TIME =", value, "experienceSignTime");
            return (Criteria) this;
        }

        public Criteria andExperienceSignTimeNotEqualTo(String value) {
            addCriterion("EXPERIENCE_SIGN_TIME <>", value, "experienceSignTime");
            return (Criteria) this;
        }

        public Criteria andExperienceSignTimeGreaterThan(String value) {
            addCriterion("EXPERIENCE_SIGN_TIME >", value, "experienceSignTime");
            return (Criteria) this;
        }

        public Criteria andExperienceSignTimeGreaterThanOrEqualTo(String value) {
            addCriterion("EXPERIENCE_SIGN_TIME >=", value, "experienceSignTime");
            return (Criteria) this;
        }

        public Criteria andExperienceSignTimeLessThan(String value) {
            addCriterion("EXPERIENCE_SIGN_TIME <", value, "experienceSignTime");
            return (Criteria) this;
        }

        public Criteria andExperienceSignTimeLessThanOrEqualTo(String value) {
            addCriterion("EXPERIENCE_SIGN_TIME <=", value, "experienceSignTime");
            return (Criteria) this;
        }

        public Criteria andExperienceSignTimeLike(String value) {
            addCriterion("EXPERIENCE_SIGN_TIME like", value, "experienceSignTime");
            return (Criteria) this;
        }

        public Criteria andExperienceSignTimeNotLike(String value) {
            addCriterion("EXPERIENCE_SIGN_TIME not like", value, "experienceSignTime");
            return (Criteria) this;
        }

        public Criteria andExperienceSignTimeIn(List<String> values) {
            addCriterion("EXPERIENCE_SIGN_TIME in", values, "experienceSignTime");
            return (Criteria) this;
        }

        public Criteria andExperienceSignTimeNotIn(List<String> values) {
            addCriterion("EXPERIENCE_SIGN_TIME not in", values, "experienceSignTime");
            return (Criteria) this;
        }

        public Criteria andExperienceSignTimeBetween(String value1, String value2) {
            addCriterion("EXPERIENCE_SIGN_TIME between", value1, value2, "experienceSignTime");
            return (Criteria) this;
        }

        public Criteria andExperienceSignTimeNotBetween(String value1, String value2) {
            addCriterion("EXPERIENCE_SIGN_TIME not between", value1, value2, "experienceSignTime");
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

        public Criteria andAdminTimeIsNull() {
            addCriterion("ADMIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAdminTimeIsNotNull() {
            addCriterion("ADMIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAdminTimeEqualTo(String value) {
            addCriterion("ADMIN_TIME =", value, "adminTime");
            return (Criteria) this;
        }

        public Criteria andAdminTimeNotEqualTo(String value) {
            addCriterion("ADMIN_TIME <>", value, "adminTime");
            return (Criteria) this;
        }

        public Criteria andAdminTimeGreaterThan(String value) {
            addCriterion("ADMIN_TIME >", value, "adminTime");
            return (Criteria) this;
        }

        public Criteria andAdminTimeGreaterThanOrEqualTo(String value) {
            addCriterion("ADMIN_TIME >=", value, "adminTime");
            return (Criteria) this;
        }

        public Criteria andAdminTimeLessThan(String value) {
            addCriterion("ADMIN_TIME <", value, "adminTime");
            return (Criteria) this;
        }

        public Criteria andAdminTimeLessThanOrEqualTo(String value) {
            addCriterion("ADMIN_TIME <=", value, "adminTime");
            return (Criteria) this;
        }

        public Criteria andAdminTimeLike(String value) {
            addCriterion("ADMIN_TIME like", value, "adminTime");
            return (Criteria) this;
        }

        public Criteria andAdminTimeNotLike(String value) {
            addCriterion("ADMIN_TIME not like", value, "adminTime");
            return (Criteria) this;
        }

        public Criteria andAdminTimeIn(List<String> values) {
            addCriterion("ADMIN_TIME in", values, "adminTime");
            return (Criteria) this;
        }

        public Criteria andAdminTimeNotIn(List<String> values) {
            addCriterion("ADMIN_TIME not in", values, "adminTime");
            return (Criteria) this;
        }

        public Criteria andAdminTimeBetween(String value1, String value2) {
            addCriterion("ADMIN_TIME between", value1, value2, "adminTime");
            return (Criteria) this;
        }

        public Criteria andAdminTimeNotBetween(String value1, String value2) {
            addCriterion("ADMIN_TIME not between", value1, value2, "adminTime");
            return (Criteria) this;
        }

        public Criteria andAdminUserFlowIsNull() {
            addCriterion("ADMIN_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAdminUserFlowIsNotNull() {
            addCriterion("ADMIN_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAdminUserFlowEqualTo(String value) {
            addCriterion("ADMIN_USER_FLOW =", value, "adminUserFlow");
            return (Criteria) this;
        }

        public Criteria andAdminUserFlowNotEqualTo(String value) {
            addCriterion("ADMIN_USER_FLOW <>", value, "adminUserFlow");
            return (Criteria) this;
        }

        public Criteria andAdminUserFlowGreaterThan(String value) {
            addCriterion("ADMIN_USER_FLOW >", value, "adminUserFlow");
            return (Criteria) this;
        }

        public Criteria andAdminUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ADMIN_USER_FLOW >=", value, "adminUserFlow");
            return (Criteria) this;
        }

        public Criteria andAdminUserFlowLessThan(String value) {
            addCriterion("ADMIN_USER_FLOW <", value, "adminUserFlow");
            return (Criteria) this;
        }

        public Criteria andAdminUserFlowLessThanOrEqualTo(String value) {
            addCriterion("ADMIN_USER_FLOW <=", value, "adminUserFlow");
            return (Criteria) this;
        }

        public Criteria andAdminUserFlowLike(String value) {
            addCriterion("ADMIN_USER_FLOW like", value, "adminUserFlow");
            return (Criteria) this;
        }

        public Criteria andAdminUserFlowNotLike(String value) {
            addCriterion("ADMIN_USER_FLOW not like", value, "adminUserFlow");
            return (Criteria) this;
        }

        public Criteria andAdminUserFlowIn(List<String> values) {
            addCriterion("ADMIN_USER_FLOW in", values, "adminUserFlow");
            return (Criteria) this;
        }

        public Criteria andAdminUserFlowNotIn(List<String> values) {
            addCriterion("ADMIN_USER_FLOW not in", values, "adminUserFlow");
            return (Criteria) this;
        }

        public Criteria andAdminUserFlowBetween(String value1, String value2) {
            addCriterion("ADMIN_USER_FLOW between", value1, value2, "adminUserFlow");
            return (Criteria) this;
        }

        public Criteria andAdminUserFlowNotBetween(String value1, String value2) {
            addCriterion("ADMIN_USER_FLOW not between", value1, value2, "adminUserFlow");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameIsNull() {
            addCriterion("ADMIN_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameIsNotNull() {
            addCriterion("ADMIN_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameEqualTo(String value) {
            addCriterion("ADMIN_USER_NAME =", value, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameNotEqualTo(String value) {
            addCriterion("ADMIN_USER_NAME <>", value, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameGreaterThan(String value) {
            addCriterion("ADMIN_USER_NAME >", value, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("ADMIN_USER_NAME >=", value, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameLessThan(String value) {
            addCriterion("ADMIN_USER_NAME <", value, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameLessThanOrEqualTo(String value) {
            addCriterion("ADMIN_USER_NAME <=", value, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameLike(String value) {
            addCriterion("ADMIN_USER_NAME like", value, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameNotLike(String value) {
            addCriterion("ADMIN_USER_NAME not like", value, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameIn(List<String> values) {
            addCriterion("ADMIN_USER_NAME in", values, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameNotIn(List<String> values) {
            addCriterion("ADMIN_USER_NAME not in", values, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameBetween(String value1, String value2) {
            addCriterion("ADMIN_USER_NAME between", value1, value2, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminUserNameNotBetween(String value1, String value2) {
            addCriterion("ADMIN_USER_NAME not between", value1, value2, "adminUserName");
            return (Criteria) this;
        }

        public Criteria andAdminStatusIdIsNull() {
            addCriterion("ADMIN_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andAdminStatusIdIsNotNull() {
            addCriterion("ADMIN_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAdminStatusIdEqualTo(String value) {
            addCriterion("ADMIN_STATUS_ID =", value, "adminStatusId");
            return (Criteria) this;
        }

        public Criteria andAdminStatusIdNotEqualTo(String value) {
            addCriterion("ADMIN_STATUS_ID <>", value, "adminStatusId");
            return (Criteria) this;
        }

        public Criteria andAdminStatusIdGreaterThan(String value) {
            addCriterion("ADMIN_STATUS_ID >", value, "adminStatusId");
            return (Criteria) this;
        }

        public Criteria andAdminStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("ADMIN_STATUS_ID >=", value, "adminStatusId");
            return (Criteria) this;
        }

        public Criteria andAdminStatusIdLessThan(String value) {
            addCriterion("ADMIN_STATUS_ID <", value, "adminStatusId");
            return (Criteria) this;
        }

        public Criteria andAdminStatusIdLessThanOrEqualTo(String value) {
            addCriterion("ADMIN_STATUS_ID <=", value, "adminStatusId");
            return (Criteria) this;
        }

        public Criteria andAdminStatusIdLike(String value) {
            addCriterion("ADMIN_STATUS_ID like", value, "adminStatusId");
            return (Criteria) this;
        }

        public Criteria andAdminStatusIdNotLike(String value) {
            addCriterion("ADMIN_STATUS_ID not like", value, "adminStatusId");
            return (Criteria) this;
        }

        public Criteria andAdminStatusIdIn(List<String> values) {
            addCriterion("ADMIN_STATUS_ID in", values, "adminStatusId");
            return (Criteria) this;
        }

        public Criteria andAdminStatusIdNotIn(List<String> values) {
            addCriterion("ADMIN_STATUS_ID not in", values, "adminStatusId");
            return (Criteria) this;
        }

        public Criteria andAdminStatusIdBetween(String value1, String value2) {
            addCriterion("ADMIN_STATUS_ID between", value1, value2, "adminStatusId");
            return (Criteria) this;
        }

        public Criteria andAdminStatusIdNotBetween(String value1, String value2) {
            addCriterion("ADMIN_STATUS_ID not between", value1, value2, "adminStatusId");
            return (Criteria) this;
        }

        public Criteria andAdminStatusNameIsNull() {
            addCriterion("ADMIN_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAdminStatusNameIsNotNull() {
            addCriterion("ADMIN_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAdminStatusNameEqualTo(String value) {
            addCriterion("ADMIN_STATUS_NAME =", value, "adminStatusName");
            return (Criteria) this;
        }

        public Criteria andAdminStatusNameNotEqualTo(String value) {
            addCriterion("ADMIN_STATUS_NAME <>", value, "adminStatusName");
            return (Criteria) this;
        }

        public Criteria andAdminStatusNameGreaterThan(String value) {
            addCriterion("ADMIN_STATUS_NAME >", value, "adminStatusName");
            return (Criteria) this;
        }

        public Criteria andAdminStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("ADMIN_STATUS_NAME >=", value, "adminStatusName");
            return (Criteria) this;
        }

        public Criteria andAdminStatusNameLessThan(String value) {
            addCriterion("ADMIN_STATUS_NAME <", value, "adminStatusName");
            return (Criteria) this;
        }

        public Criteria andAdminStatusNameLessThanOrEqualTo(String value) {
            addCriterion("ADMIN_STATUS_NAME <=", value, "adminStatusName");
            return (Criteria) this;
        }

        public Criteria andAdminStatusNameLike(String value) {
            addCriterion("ADMIN_STATUS_NAME like", value, "adminStatusName");
            return (Criteria) this;
        }

        public Criteria andAdminStatusNameNotLike(String value) {
            addCriterion("ADMIN_STATUS_NAME not like", value, "adminStatusName");
            return (Criteria) this;
        }

        public Criteria andAdminStatusNameIn(List<String> values) {
            addCriterion("ADMIN_STATUS_NAME in", values, "adminStatusName");
            return (Criteria) this;
        }

        public Criteria andAdminStatusNameNotIn(List<String> values) {
            addCriterion("ADMIN_STATUS_NAME not in", values, "adminStatusName");
            return (Criteria) this;
        }

        public Criteria andAdminStatusNameBetween(String value1, String value2) {
            addCriterion("ADMIN_STATUS_NAME between", value1, value2, "adminStatusName");
            return (Criteria) this;
        }

        public Criteria andAdminStatusNameNotBetween(String value1, String value2) {
            addCriterion("ADMIN_STATUS_NAME not between", value1, value2, "adminStatusName");
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

        public Criteria andAnnualHegeIsNull() {
            addCriterion("ANNUAL_HEGE is null");
            return (Criteria) this;
        }

        public Criteria andAnnualHegeIsNotNull() {
            addCriterion("ANNUAL_HEGE is not null");
            return (Criteria) this;
        }

        public Criteria andAnnualHegeEqualTo(BigDecimal value) {
            addCriterion("ANNUAL_HEGE =", value, "annualHege");
            return (Criteria) this;
        }

        public Criteria andAnnualHegeNotEqualTo(BigDecimal value) {
            addCriterion("ANNUAL_HEGE <>", value, "annualHege");
            return (Criteria) this;
        }

        public Criteria andAnnualHegeGreaterThan(BigDecimal value) {
            addCriterion("ANNUAL_HEGE >", value, "annualHege");
            return (Criteria) this;
        }

        public Criteria andAnnualHegeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ANNUAL_HEGE >=", value, "annualHege");
            return (Criteria) this;
        }

        public Criteria andAnnualHegeLessThan(BigDecimal value) {
            addCriterion("ANNUAL_HEGE <", value, "annualHege");
            return (Criteria) this;
        }

        public Criteria andAnnualHegeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ANNUAL_HEGE <=", value, "annualHege");
            return (Criteria) this;
        }

        public Criteria andAnnualHegeIn(List<BigDecimal> values) {
            addCriterion("ANNUAL_HEGE in", values, "annualHege");
            return (Criteria) this;
        }

        public Criteria andAnnualHegeNotIn(List<BigDecimal> values) {
            addCriterion("ANNUAL_HEGE not in", values, "annualHege");
            return (Criteria) this;
        }

        public Criteria andAnnualHegeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ANNUAL_HEGE between", value1, value2, "annualHege");
            return (Criteria) this;
        }

        public Criteria andAnnualHegeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ANNUAL_HEGE not between", value1, value2, "annualHege");
            return (Criteria) this;
        }

        public Criteria andAnnualNotHegeIsNull() {
            addCriterion("ANNUAL_NOT_HEGE is null");
            return (Criteria) this;
        }

        public Criteria andAnnualNotHegeIsNotNull() {
            addCriterion("ANNUAL_NOT_HEGE is not null");
            return (Criteria) this;
        }

        public Criteria andAnnualNotHegeEqualTo(BigDecimal value) {
            addCriterion("ANNUAL_NOT_HEGE =", value, "annualNotHege");
            return (Criteria) this;
        }

        public Criteria andAnnualNotHegeNotEqualTo(BigDecimal value) {
            addCriterion("ANNUAL_NOT_HEGE <>", value, "annualNotHege");
            return (Criteria) this;
        }

        public Criteria andAnnualNotHegeGreaterThan(BigDecimal value) {
            addCriterion("ANNUAL_NOT_HEGE >", value, "annualNotHege");
            return (Criteria) this;
        }

        public Criteria andAnnualNotHegeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ANNUAL_NOT_HEGE >=", value, "annualNotHege");
            return (Criteria) this;
        }

        public Criteria andAnnualNotHegeLessThan(BigDecimal value) {
            addCriterion("ANNUAL_NOT_HEGE <", value, "annualNotHege");
            return (Criteria) this;
        }

        public Criteria andAnnualNotHegeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ANNUAL_NOT_HEGE <=", value, "annualNotHege");
            return (Criteria) this;
        }

        public Criteria andAnnualNotHegeIn(List<BigDecimal> values) {
            addCriterion("ANNUAL_NOT_HEGE in", values, "annualNotHege");
            return (Criteria) this;
        }

        public Criteria andAnnualNotHegeNotIn(List<BigDecimal> values) {
            addCriterion("ANNUAL_NOT_HEGE not in", values, "annualNotHege");
            return (Criteria) this;
        }

        public Criteria andAnnualNotHegeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ANNUAL_NOT_HEGE between", value1, value2, "annualNotHege");
            return (Criteria) this;
        }

        public Criteria andAnnualNotHegeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ANNUAL_NOT_HEGE not between", value1, value2, "annualNotHege");
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