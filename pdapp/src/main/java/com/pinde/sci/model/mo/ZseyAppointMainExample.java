package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ZseyAppointMainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ZseyAppointMainExample() {
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

        public Criteria andAppointFlowIsNull() {
            addCriterion("APPOINT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAppointFlowIsNotNull() {
            addCriterion("APPOINT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAppointFlowEqualTo(String value) {
            addCriterion("APPOINT_FLOW =", value, "appointFlow");
            return (Criteria) this;
        }

        public Criteria andAppointFlowNotEqualTo(String value) {
            addCriterion("APPOINT_FLOW <>", value, "appointFlow");
            return (Criteria) this;
        }

        public Criteria andAppointFlowGreaterThan(String value) {
            addCriterion("APPOINT_FLOW >", value, "appointFlow");
            return (Criteria) this;
        }

        public Criteria andAppointFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPOINT_FLOW >=", value, "appointFlow");
            return (Criteria) this;
        }

        public Criteria andAppointFlowLessThan(String value) {
            addCriterion("APPOINT_FLOW <", value, "appointFlow");
            return (Criteria) this;
        }

        public Criteria andAppointFlowLessThanOrEqualTo(String value) {
            addCriterion("APPOINT_FLOW <=", value, "appointFlow");
            return (Criteria) this;
        }

        public Criteria andAppointFlowLike(String value) {
            addCriterion("APPOINT_FLOW like", value, "appointFlow");
            return (Criteria) this;
        }

        public Criteria andAppointFlowNotLike(String value) {
            addCriterion("APPOINT_FLOW not like", value, "appointFlow");
            return (Criteria) this;
        }

        public Criteria andAppointFlowIn(List<String> values) {
            addCriterion("APPOINT_FLOW in", values, "appointFlow");
            return (Criteria) this;
        }

        public Criteria andAppointFlowNotIn(List<String> values) {
            addCriterion("APPOINT_FLOW not in", values, "appointFlow");
            return (Criteria) this;
        }

        public Criteria andAppointFlowBetween(String value1, String value2) {
            addCriterion("APPOINT_FLOW between", value1, value2, "appointFlow");
            return (Criteria) this;
        }

        public Criteria andAppointFlowNotBetween(String value1, String value2) {
            addCriterion("APPOINT_FLOW not between", value1, value2, "appointFlow");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNull() {
            addCriterion("PROJECT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNotNull() {
            addCriterion("PROJECT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNameEqualTo(String value) {
            addCriterion("PROJECT_NAME =", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("PROJECT_NAME <>", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("PROJECT_NAME >", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJECT_NAME >=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThan(String value) {
            addCriterion("PROJECT_NAME <", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("PROJECT_NAME <=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLike(String value) {
            addCriterion("PROJECT_NAME like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotLike(String value) {
            addCriterion("PROJECT_NAME not like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameIn(List<String> values) {
            addCriterion("PROJECT_NAME in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotIn(List<String> values) {
            addCriterion("PROJECT_NAME not in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameBetween(String value1, String value2) {
            addCriterion("PROJECT_NAME between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotBetween(String value1, String value2) {
            addCriterion("PROJECT_NAME not between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andTrainDateIsNull() {
            addCriterion("TRAIN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTrainDateIsNotNull() {
            addCriterion("TRAIN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTrainDateEqualTo(String value) {
            addCriterion("TRAIN_DATE =", value, "trainDate");
            return (Criteria) this;
        }

        public Criteria andTrainDateNotEqualTo(String value) {
            addCriterion("TRAIN_DATE <>", value, "trainDate");
            return (Criteria) this;
        }

        public Criteria andTrainDateGreaterThan(String value) {
            addCriterion("TRAIN_DATE >", value, "trainDate");
            return (Criteria) this;
        }

        public Criteria andTrainDateGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_DATE >=", value, "trainDate");
            return (Criteria) this;
        }

        public Criteria andTrainDateLessThan(String value) {
            addCriterion("TRAIN_DATE <", value, "trainDate");
            return (Criteria) this;
        }

        public Criteria andTrainDateLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_DATE <=", value, "trainDate");
            return (Criteria) this;
        }

        public Criteria andTrainDateLike(String value) {
            addCriterion("TRAIN_DATE like", value, "trainDate");
            return (Criteria) this;
        }

        public Criteria andTrainDateNotLike(String value) {
            addCriterion("TRAIN_DATE not like", value, "trainDate");
            return (Criteria) this;
        }

        public Criteria andTrainDateIn(List<String> values) {
            addCriterion("TRAIN_DATE in", values, "trainDate");
            return (Criteria) this;
        }

        public Criteria andTrainDateNotIn(List<String> values) {
            addCriterion("TRAIN_DATE not in", values, "trainDate");
            return (Criteria) this;
        }

        public Criteria andTrainDateBetween(String value1, String value2) {
            addCriterion("TRAIN_DATE between", value1, value2, "trainDate");
            return (Criteria) this;
        }

        public Criteria andTrainDateNotBetween(String value1, String value2) {
            addCriterion("TRAIN_DATE not between", value1, value2, "trainDate");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNull() {
            addCriterion("BEGIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("BEGIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(String value) {
            addCriterion("BEGIN_TIME =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(String value) {
            addCriterion("BEGIN_TIME <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(String value) {
            addCriterion("BEGIN_TIME >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(String value) {
            addCriterion("BEGIN_TIME >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(String value) {
            addCriterion("BEGIN_TIME <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(String value) {
            addCriterion("BEGIN_TIME <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLike(String value) {
            addCriterion("BEGIN_TIME like", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotLike(String value) {
            addCriterion("BEGIN_TIME not like", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<String> values) {
            addCriterion("BEGIN_TIME in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<String> values) {
            addCriterion("BEGIN_TIME not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(String value1, String value2) {
            addCriterion("BEGIN_TIME between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(String value1, String value2) {
            addCriterion("BEGIN_TIME not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(String value) {
            addCriterion("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(String value) {
            addCriterion("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(String value) {
            addCriterion("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(String value) {
            addCriterion("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(String value) {
            addCriterion("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLike(String value) {
            addCriterion("END_TIME like", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotLike(String value) {
            addCriterion("END_TIME not like", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<String> values) {
            addCriterion("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<String> values) {
            addCriterion("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(String value1, String value2) {
            addCriterion("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(String value1, String value2) {
            addCriterion("END_TIME not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andTraineesNameIsNull() {
            addCriterion("TRAINEES_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTraineesNameIsNotNull() {
            addCriterion("TRAINEES_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTraineesNameEqualTo(String value) {
            addCriterion("TRAINEES_NAME =", value, "traineesName");
            return (Criteria) this;
        }

        public Criteria andTraineesNameNotEqualTo(String value) {
            addCriterion("TRAINEES_NAME <>", value, "traineesName");
            return (Criteria) this;
        }

        public Criteria andTraineesNameGreaterThan(String value) {
            addCriterion("TRAINEES_NAME >", value, "traineesName");
            return (Criteria) this;
        }

        public Criteria andTraineesNameGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINEES_NAME >=", value, "traineesName");
            return (Criteria) this;
        }

        public Criteria andTraineesNameLessThan(String value) {
            addCriterion("TRAINEES_NAME <", value, "traineesName");
            return (Criteria) this;
        }

        public Criteria andTraineesNameLessThanOrEqualTo(String value) {
            addCriterion("TRAINEES_NAME <=", value, "traineesName");
            return (Criteria) this;
        }

        public Criteria andTraineesNameLike(String value) {
            addCriterion("TRAINEES_NAME like", value, "traineesName");
            return (Criteria) this;
        }

        public Criteria andTraineesNameNotLike(String value) {
            addCriterion("TRAINEES_NAME not like", value, "traineesName");
            return (Criteria) this;
        }

        public Criteria andTraineesNameIn(List<String> values) {
            addCriterion("TRAINEES_NAME in", values, "traineesName");
            return (Criteria) this;
        }

        public Criteria andTraineesNameNotIn(List<String> values) {
            addCriterion("TRAINEES_NAME not in", values, "traineesName");
            return (Criteria) this;
        }

        public Criteria andTraineesNameBetween(String value1, String value2) {
            addCriterion("TRAINEES_NAME between", value1, value2, "traineesName");
            return (Criteria) this;
        }

        public Criteria andTraineesNameNotBetween(String value1, String value2) {
            addCriterion("TRAINEES_NAME not between", value1, value2, "traineesName");
            return (Criteria) this;
        }

        public Criteria andTraineesSpeNameIsNull() {
            addCriterion("TRAINEES_SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTraineesSpeNameIsNotNull() {
            addCriterion("TRAINEES_SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTraineesSpeNameEqualTo(String value) {
            addCriterion("TRAINEES_SPE_NAME =", value, "traineesSpeName");
            return (Criteria) this;
        }

        public Criteria andTraineesSpeNameNotEqualTo(String value) {
            addCriterion("TRAINEES_SPE_NAME <>", value, "traineesSpeName");
            return (Criteria) this;
        }

        public Criteria andTraineesSpeNameGreaterThan(String value) {
            addCriterion("TRAINEES_SPE_NAME >", value, "traineesSpeName");
            return (Criteria) this;
        }

        public Criteria andTraineesSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINEES_SPE_NAME >=", value, "traineesSpeName");
            return (Criteria) this;
        }

        public Criteria andTraineesSpeNameLessThan(String value) {
            addCriterion("TRAINEES_SPE_NAME <", value, "traineesSpeName");
            return (Criteria) this;
        }

        public Criteria andTraineesSpeNameLessThanOrEqualTo(String value) {
            addCriterion("TRAINEES_SPE_NAME <=", value, "traineesSpeName");
            return (Criteria) this;
        }

        public Criteria andTraineesSpeNameLike(String value) {
            addCriterion("TRAINEES_SPE_NAME like", value, "traineesSpeName");
            return (Criteria) this;
        }

        public Criteria andTraineesSpeNameNotLike(String value) {
            addCriterion("TRAINEES_SPE_NAME not like", value, "traineesSpeName");
            return (Criteria) this;
        }

        public Criteria andTraineesSpeNameIn(List<String> values) {
            addCriterion("TRAINEES_SPE_NAME in", values, "traineesSpeName");
            return (Criteria) this;
        }

        public Criteria andTraineesSpeNameNotIn(List<String> values) {
            addCriterion("TRAINEES_SPE_NAME not in", values, "traineesSpeName");
            return (Criteria) this;
        }

        public Criteria andTraineesSpeNameBetween(String value1, String value2) {
            addCriterion("TRAINEES_SPE_NAME between", value1, value2, "traineesSpeName");
            return (Criteria) this;
        }

        public Criteria andTraineesSpeNameNotBetween(String value1, String value2) {
            addCriterion("TRAINEES_SPE_NAME not between", value1, value2, "traineesSpeName");
            return (Criteria) this;
        }

        public Criteria andTraineesNumberIsNull() {
            addCriterion("TRAINEES_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andTraineesNumberIsNotNull() {
            addCriterion("TRAINEES_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andTraineesNumberEqualTo(String value) {
            addCriterion("TRAINEES_NUMBER =", value, "traineesNumber");
            return (Criteria) this;
        }

        public Criteria andTraineesNumberNotEqualTo(String value) {
            addCriterion("TRAINEES_NUMBER <>", value, "traineesNumber");
            return (Criteria) this;
        }

        public Criteria andTraineesNumberGreaterThan(String value) {
            addCriterion("TRAINEES_NUMBER >", value, "traineesNumber");
            return (Criteria) this;
        }

        public Criteria andTraineesNumberGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINEES_NUMBER >=", value, "traineesNumber");
            return (Criteria) this;
        }

        public Criteria andTraineesNumberLessThan(String value) {
            addCriterion("TRAINEES_NUMBER <", value, "traineesNumber");
            return (Criteria) this;
        }

        public Criteria andTraineesNumberLessThanOrEqualTo(String value) {
            addCriterion("TRAINEES_NUMBER <=", value, "traineesNumber");
            return (Criteria) this;
        }

        public Criteria andTraineesNumberLike(String value) {
            addCriterion("TRAINEES_NUMBER like", value, "traineesNumber");
            return (Criteria) this;
        }

        public Criteria andTraineesNumberNotLike(String value) {
            addCriterion("TRAINEES_NUMBER not like", value, "traineesNumber");
            return (Criteria) this;
        }

        public Criteria andTraineesNumberIn(List<String> values) {
            addCriterion("TRAINEES_NUMBER in", values, "traineesNumber");
            return (Criteria) this;
        }

        public Criteria andTraineesNumberNotIn(List<String> values) {
            addCriterion("TRAINEES_NUMBER not in", values, "traineesNumber");
            return (Criteria) this;
        }

        public Criteria andTraineesNumberBetween(String value1, String value2) {
            addCriterion("TRAINEES_NUMBER between", value1, value2, "traineesNumber");
            return (Criteria) this;
        }

        public Criteria andTraineesNumberNotBetween(String value1, String value2) {
            addCriterion("TRAINEES_NUMBER not between", value1, value2, "traineesNumber");
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

        public Criteria andTeacherPhoneIsNull() {
            addCriterion("TEACHER_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andTeacherPhoneIsNotNull() {
            addCriterion("TEACHER_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherPhoneEqualTo(String value) {
            addCriterion("TEACHER_PHONE =", value, "teacherPhone");
            return (Criteria) this;
        }

        public Criteria andTeacherPhoneNotEqualTo(String value) {
            addCriterion("TEACHER_PHONE <>", value, "teacherPhone");
            return (Criteria) this;
        }

        public Criteria andTeacherPhoneGreaterThan(String value) {
            addCriterion("TEACHER_PHONE >", value, "teacherPhone");
            return (Criteria) this;
        }

        public Criteria andTeacherPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_PHONE >=", value, "teacherPhone");
            return (Criteria) this;
        }

        public Criteria andTeacherPhoneLessThan(String value) {
            addCriterion("TEACHER_PHONE <", value, "teacherPhone");
            return (Criteria) this;
        }

        public Criteria andTeacherPhoneLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_PHONE <=", value, "teacherPhone");
            return (Criteria) this;
        }

        public Criteria andTeacherPhoneLike(String value) {
            addCriterion("TEACHER_PHONE like", value, "teacherPhone");
            return (Criteria) this;
        }

        public Criteria andTeacherPhoneNotLike(String value) {
            addCriterion("TEACHER_PHONE not like", value, "teacherPhone");
            return (Criteria) this;
        }

        public Criteria andTeacherPhoneIn(List<String> values) {
            addCriterion("TEACHER_PHONE in", values, "teacherPhone");
            return (Criteria) this;
        }

        public Criteria andTeacherPhoneNotIn(List<String> values) {
            addCriterion("TEACHER_PHONE not in", values, "teacherPhone");
            return (Criteria) this;
        }

        public Criteria andTeacherPhoneBetween(String value1, String value2) {
            addCriterion("TEACHER_PHONE between", value1, value2, "teacherPhone");
            return (Criteria) this;
        }

        public Criteria andTeacherPhoneNotBetween(String value1, String value2) {
            addCriterion("TEACHER_PHONE not between", value1, value2, "teacherPhone");
            return (Criteria) this;
        }

        public Criteria andApplicantFlowIsNull() {
            addCriterion("APPLICANT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApplicantFlowIsNotNull() {
            addCriterion("APPLICANT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApplicantFlowEqualTo(String value) {
            addCriterion("APPLICANT_FLOW =", value, "applicantFlow");
            return (Criteria) this;
        }

        public Criteria andApplicantFlowNotEqualTo(String value) {
            addCriterion("APPLICANT_FLOW <>", value, "applicantFlow");
            return (Criteria) this;
        }

        public Criteria andApplicantFlowGreaterThan(String value) {
            addCriterion("APPLICANT_FLOW >", value, "applicantFlow");
            return (Criteria) this;
        }

        public Criteria andApplicantFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPLICANT_FLOW >=", value, "applicantFlow");
            return (Criteria) this;
        }

        public Criteria andApplicantFlowLessThan(String value) {
            addCriterion("APPLICANT_FLOW <", value, "applicantFlow");
            return (Criteria) this;
        }

        public Criteria andApplicantFlowLessThanOrEqualTo(String value) {
            addCriterion("APPLICANT_FLOW <=", value, "applicantFlow");
            return (Criteria) this;
        }

        public Criteria andApplicantFlowLike(String value) {
            addCriterion("APPLICANT_FLOW like", value, "applicantFlow");
            return (Criteria) this;
        }

        public Criteria andApplicantFlowNotLike(String value) {
            addCriterion("APPLICANT_FLOW not like", value, "applicantFlow");
            return (Criteria) this;
        }

        public Criteria andApplicantFlowIn(List<String> values) {
            addCriterion("APPLICANT_FLOW in", values, "applicantFlow");
            return (Criteria) this;
        }

        public Criteria andApplicantFlowNotIn(List<String> values) {
            addCriterion("APPLICANT_FLOW not in", values, "applicantFlow");
            return (Criteria) this;
        }

        public Criteria andApplicantFlowBetween(String value1, String value2) {
            addCriterion("APPLICANT_FLOW between", value1, value2, "applicantFlow");
            return (Criteria) this;
        }

        public Criteria andApplicantFlowNotBetween(String value1, String value2) {
            addCriterion("APPLICANT_FLOW not between", value1, value2, "applicantFlow");
            return (Criteria) this;
        }

        public Criteria andApplicantNameIsNull() {
            addCriterion("APPLICANT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andApplicantNameIsNotNull() {
            addCriterion("APPLICANT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andApplicantNameEqualTo(String value) {
            addCriterion("APPLICANT_NAME =", value, "applicantName");
            return (Criteria) this;
        }

        public Criteria andApplicantNameNotEqualTo(String value) {
            addCriterion("APPLICANT_NAME <>", value, "applicantName");
            return (Criteria) this;
        }

        public Criteria andApplicantNameGreaterThan(String value) {
            addCriterion("APPLICANT_NAME >", value, "applicantName");
            return (Criteria) this;
        }

        public Criteria andApplicantNameGreaterThanOrEqualTo(String value) {
            addCriterion("APPLICANT_NAME >=", value, "applicantName");
            return (Criteria) this;
        }

        public Criteria andApplicantNameLessThan(String value) {
            addCriterion("APPLICANT_NAME <", value, "applicantName");
            return (Criteria) this;
        }

        public Criteria andApplicantNameLessThanOrEqualTo(String value) {
            addCriterion("APPLICANT_NAME <=", value, "applicantName");
            return (Criteria) this;
        }

        public Criteria andApplicantNameLike(String value) {
            addCriterion("APPLICANT_NAME like", value, "applicantName");
            return (Criteria) this;
        }

        public Criteria andApplicantNameNotLike(String value) {
            addCriterion("APPLICANT_NAME not like", value, "applicantName");
            return (Criteria) this;
        }

        public Criteria andApplicantNameIn(List<String> values) {
            addCriterion("APPLICANT_NAME in", values, "applicantName");
            return (Criteria) this;
        }

        public Criteria andApplicantNameNotIn(List<String> values) {
            addCriterion("APPLICANT_NAME not in", values, "applicantName");
            return (Criteria) this;
        }

        public Criteria andApplicantNameBetween(String value1, String value2) {
            addCriterion("APPLICANT_NAME between", value1, value2, "applicantName");
            return (Criteria) this;
        }

        public Criteria andApplicantNameNotBetween(String value1, String value2) {
            addCriterion("APPLICANT_NAME not between", value1, value2, "applicantName");
            return (Criteria) this;
        }

        public Criteria andApplicantPhoneIsNull() {
            addCriterion("APPLICANT_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andApplicantPhoneIsNotNull() {
            addCriterion("APPLICANT_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andApplicantPhoneEqualTo(String value) {
            addCriterion("APPLICANT_PHONE =", value, "applicantPhone");
            return (Criteria) this;
        }

        public Criteria andApplicantPhoneNotEqualTo(String value) {
            addCriterion("APPLICANT_PHONE <>", value, "applicantPhone");
            return (Criteria) this;
        }

        public Criteria andApplicantPhoneGreaterThan(String value) {
            addCriterion("APPLICANT_PHONE >", value, "applicantPhone");
            return (Criteria) this;
        }

        public Criteria andApplicantPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("APPLICANT_PHONE >=", value, "applicantPhone");
            return (Criteria) this;
        }

        public Criteria andApplicantPhoneLessThan(String value) {
            addCriterion("APPLICANT_PHONE <", value, "applicantPhone");
            return (Criteria) this;
        }

        public Criteria andApplicantPhoneLessThanOrEqualTo(String value) {
            addCriterion("APPLICANT_PHONE <=", value, "applicantPhone");
            return (Criteria) this;
        }

        public Criteria andApplicantPhoneLike(String value) {
            addCriterion("APPLICANT_PHONE like", value, "applicantPhone");
            return (Criteria) this;
        }

        public Criteria andApplicantPhoneNotLike(String value) {
            addCriterion("APPLICANT_PHONE not like", value, "applicantPhone");
            return (Criteria) this;
        }

        public Criteria andApplicantPhoneIn(List<String> values) {
            addCriterion("APPLICANT_PHONE in", values, "applicantPhone");
            return (Criteria) this;
        }

        public Criteria andApplicantPhoneNotIn(List<String> values) {
            addCriterion("APPLICANT_PHONE not in", values, "applicantPhone");
            return (Criteria) this;
        }

        public Criteria andApplicantPhoneBetween(String value1, String value2) {
            addCriterion("APPLICANT_PHONE between", value1, value2, "applicantPhone");
            return (Criteria) this;
        }

        public Criteria andApplicantPhoneNotBetween(String value1, String value2) {
            addCriterion("APPLICANT_PHONE not between", value1, value2, "applicantPhone");
            return (Criteria) this;
        }

        public Criteria andTrainRoomIdIsNull() {
            addCriterion("TRAIN_ROOM_ID is null");
            return (Criteria) this;
        }

        public Criteria andTrainRoomIdIsNotNull() {
            addCriterion("TRAIN_ROOM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTrainRoomIdEqualTo(String value) {
            addCriterion("TRAIN_ROOM_ID =", value, "trainRoomId");
            return (Criteria) this;
        }

        public Criteria andTrainRoomIdNotEqualTo(String value) {
            addCriterion("TRAIN_ROOM_ID <>", value, "trainRoomId");
            return (Criteria) this;
        }

        public Criteria andTrainRoomIdGreaterThan(String value) {
            addCriterion("TRAIN_ROOM_ID >", value, "trainRoomId");
            return (Criteria) this;
        }

        public Criteria andTrainRoomIdGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_ROOM_ID >=", value, "trainRoomId");
            return (Criteria) this;
        }

        public Criteria andTrainRoomIdLessThan(String value) {
            addCriterion("TRAIN_ROOM_ID <", value, "trainRoomId");
            return (Criteria) this;
        }

        public Criteria andTrainRoomIdLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_ROOM_ID <=", value, "trainRoomId");
            return (Criteria) this;
        }

        public Criteria andTrainRoomIdLike(String value) {
            addCriterion("TRAIN_ROOM_ID like", value, "trainRoomId");
            return (Criteria) this;
        }

        public Criteria andTrainRoomIdNotLike(String value) {
            addCriterion("TRAIN_ROOM_ID not like", value, "trainRoomId");
            return (Criteria) this;
        }

        public Criteria andTrainRoomIdIn(List<String> values) {
            addCriterion("TRAIN_ROOM_ID in", values, "trainRoomId");
            return (Criteria) this;
        }

        public Criteria andTrainRoomIdNotIn(List<String> values) {
            addCriterion("TRAIN_ROOM_ID not in", values, "trainRoomId");
            return (Criteria) this;
        }

        public Criteria andTrainRoomIdBetween(String value1, String value2) {
            addCriterion("TRAIN_ROOM_ID between", value1, value2, "trainRoomId");
            return (Criteria) this;
        }

        public Criteria andTrainRoomIdNotBetween(String value1, String value2) {
            addCriterion("TRAIN_ROOM_ID not between", value1, value2, "trainRoomId");
            return (Criteria) this;
        }

        public Criteria andTrainRoomNameIsNull() {
            addCriterion("TRAIN_ROOM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTrainRoomNameIsNotNull() {
            addCriterion("TRAIN_ROOM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTrainRoomNameEqualTo(String value) {
            addCriterion("TRAIN_ROOM_NAME =", value, "trainRoomName");
            return (Criteria) this;
        }

        public Criteria andTrainRoomNameNotEqualTo(String value) {
            addCriterion("TRAIN_ROOM_NAME <>", value, "trainRoomName");
            return (Criteria) this;
        }

        public Criteria andTrainRoomNameGreaterThan(String value) {
            addCriterion("TRAIN_ROOM_NAME >", value, "trainRoomName");
            return (Criteria) this;
        }

        public Criteria andTrainRoomNameGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_ROOM_NAME >=", value, "trainRoomName");
            return (Criteria) this;
        }

        public Criteria andTrainRoomNameLessThan(String value) {
            addCriterion("TRAIN_ROOM_NAME <", value, "trainRoomName");
            return (Criteria) this;
        }

        public Criteria andTrainRoomNameLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_ROOM_NAME <=", value, "trainRoomName");
            return (Criteria) this;
        }

        public Criteria andTrainRoomNameLike(String value) {
            addCriterion("TRAIN_ROOM_NAME like", value, "trainRoomName");
            return (Criteria) this;
        }

        public Criteria andTrainRoomNameNotLike(String value) {
            addCriterion("TRAIN_ROOM_NAME not like", value, "trainRoomName");
            return (Criteria) this;
        }

        public Criteria andTrainRoomNameIn(List<String> values) {
            addCriterion("TRAIN_ROOM_NAME in", values, "trainRoomName");
            return (Criteria) this;
        }

        public Criteria andTrainRoomNameNotIn(List<String> values) {
            addCriterion("TRAIN_ROOM_NAME not in", values, "trainRoomName");
            return (Criteria) this;
        }

        public Criteria andTrainRoomNameBetween(String value1, String value2) {
            addCriterion("TRAIN_ROOM_NAME between", value1, value2, "trainRoomName");
            return (Criteria) this;
        }

        public Criteria andTrainRoomNameNotBetween(String value1, String value2) {
            addCriterion("TRAIN_ROOM_NAME not between", value1, value2, "trainRoomName");
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

        public Criteria andProjectFlowIsNull() {
            addCriterion("PROJECT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andProjectFlowIsNotNull() {
            addCriterion("PROJECT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andProjectFlowEqualTo(String value) {
            addCriterion("PROJECT_FLOW =", value, "projectFlow");
            return (Criteria) this;
        }

        public Criteria andProjectFlowNotEqualTo(String value) {
            addCriterion("PROJECT_FLOW <>", value, "projectFlow");
            return (Criteria) this;
        }

        public Criteria andProjectFlowGreaterThan(String value) {
            addCriterion("PROJECT_FLOW >", value, "projectFlow");
            return (Criteria) this;
        }

        public Criteria andProjectFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PROJECT_FLOW >=", value, "projectFlow");
            return (Criteria) this;
        }

        public Criteria andProjectFlowLessThan(String value) {
            addCriterion("PROJECT_FLOW <", value, "projectFlow");
            return (Criteria) this;
        }

        public Criteria andProjectFlowLessThanOrEqualTo(String value) {
            addCriterion("PROJECT_FLOW <=", value, "projectFlow");
            return (Criteria) this;
        }

        public Criteria andProjectFlowLike(String value) {
            addCriterion("PROJECT_FLOW like", value, "projectFlow");
            return (Criteria) this;
        }

        public Criteria andProjectFlowNotLike(String value) {
            addCriterion("PROJECT_FLOW not like", value, "projectFlow");
            return (Criteria) this;
        }

        public Criteria andProjectFlowIn(List<String> values) {
            addCriterion("PROJECT_FLOW in", values, "projectFlow");
            return (Criteria) this;
        }

        public Criteria andProjectFlowNotIn(List<String> values) {
            addCriterion("PROJECT_FLOW not in", values, "projectFlow");
            return (Criteria) this;
        }

        public Criteria andProjectFlowBetween(String value1, String value2) {
            addCriterion("PROJECT_FLOW between", value1, value2, "projectFlow");
            return (Criteria) this;
        }

        public Criteria andProjectFlowNotBetween(String value1, String value2) {
            addCriterion("PROJECT_FLOW not between", value1, value2, "projectFlow");
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