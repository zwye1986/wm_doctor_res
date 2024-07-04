package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ResTypicalCasesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResTypicalCasesExample() {
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

        public Criteria andPeopleNameIsNull() {
            addCriterion("PEOPLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPeopleNameIsNotNull() {
            addCriterion("PEOPLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPeopleNameEqualTo(String value) {
            addCriterion("PEOPLE_NAME =", value, "peopleName");
            return (Criteria) this;
        }

        public Criteria andPeopleNameNotEqualTo(String value) {
            addCriterion("PEOPLE_NAME <>", value, "peopleName");
            return (Criteria) this;
        }

        public Criteria andPeopleNameGreaterThan(String value) {
            addCriterion("PEOPLE_NAME >", value, "peopleName");
            return (Criteria) this;
        }

        public Criteria andPeopleNameGreaterThanOrEqualTo(String value) {
            addCriterion("PEOPLE_NAME >=", value, "peopleName");
            return (Criteria) this;
        }

        public Criteria andPeopleNameLessThan(String value) {
            addCriterion("PEOPLE_NAME <", value, "peopleName");
            return (Criteria) this;
        }

        public Criteria andPeopleNameLessThanOrEqualTo(String value) {
            addCriterion("PEOPLE_NAME <=", value, "peopleName");
            return (Criteria) this;
        }

        public Criteria andPeopleNameLike(String value) {
            addCriterion("PEOPLE_NAME like", value, "peopleName");
            return (Criteria) this;
        }

        public Criteria andPeopleNameNotLike(String value) {
            addCriterion("PEOPLE_NAME not like", value, "peopleName");
            return (Criteria) this;
        }

        public Criteria andPeopleNameIn(List<String> values) {
            addCriterion("PEOPLE_NAME in", values, "peopleName");
            return (Criteria) this;
        }

        public Criteria andPeopleNameNotIn(List<String> values) {
            addCriterion("PEOPLE_NAME not in", values, "peopleName");
            return (Criteria) this;
        }

        public Criteria andPeopleNameBetween(String value1, String value2) {
            addCriterion("PEOPLE_NAME between", value1, value2, "peopleName");
            return (Criteria) this;
        }

        public Criteria andPeopleNameNotBetween(String value1, String value2) {
            addCriterion("PEOPLE_NAME not between", value1, value2, "peopleName");
            return (Criteria) this;
        }

        public Criteria andSexIdIsNull() {
            addCriterion("SEX_ID is null");
            return (Criteria) this;
        }

        public Criteria andSexIdIsNotNull() {
            addCriterion("SEX_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSexIdEqualTo(String value) {
            addCriterion("SEX_ID =", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotEqualTo(String value) {
            addCriterion("SEX_ID <>", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdGreaterThan(String value) {
            addCriterion("SEX_ID >", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdGreaterThanOrEqualTo(String value) {
            addCriterion("SEX_ID >=", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdLessThan(String value) {
            addCriterion("SEX_ID <", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdLessThanOrEqualTo(String value) {
            addCriterion("SEX_ID <=", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdLike(String value) {
            addCriterion("SEX_ID like", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotLike(String value) {
            addCriterion("SEX_ID not like", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdIn(List<String> values) {
            addCriterion("SEX_ID in", values, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotIn(List<String> values) {
            addCriterion("SEX_ID not in", values, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdBetween(String value1, String value2) {
            addCriterion("SEX_ID between", value1, value2, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotBetween(String value1, String value2) {
            addCriterion("SEX_ID not between", value1, value2, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexNameIsNull() {
            addCriterion("SEX_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSexNameIsNotNull() {
            addCriterion("SEX_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSexNameEqualTo(String value) {
            addCriterion("SEX_NAME =", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotEqualTo(String value) {
            addCriterion("SEX_NAME <>", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameGreaterThan(String value) {
            addCriterion("SEX_NAME >", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameGreaterThanOrEqualTo(String value) {
            addCriterion("SEX_NAME >=", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameLessThan(String value) {
            addCriterion("SEX_NAME <", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameLessThanOrEqualTo(String value) {
            addCriterion("SEX_NAME <=", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameLike(String value) {
            addCriterion("SEX_NAME like", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotLike(String value) {
            addCriterion("SEX_NAME not like", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameIn(List<String> values) {
            addCriterion("SEX_NAME in", values, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotIn(List<String> values) {
            addCriterion("SEX_NAME not in", values, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameBetween(String value1, String value2) {
            addCriterion("SEX_NAME between", value1, value2, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotBetween(String value1, String value2) {
            addCriterion("SEX_NAME not between", value1, value2, "sexName");
            return (Criteria) this;
        }

        public Criteria andVisitDateIsNull() {
            addCriterion("VISIT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andVisitDateIsNotNull() {
            addCriterion("VISIT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andVisitDateEqualTo(String value) {
            addCriterion("VISIT_DATE =", value, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateNotEqualTo(String value) {
            addCriterion("VISIT_DATE <>", value, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateGreaterThan(String value) {
            addCriterion("VISIT_DATE >", value, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateGreaterThanOrEqualTo(String value) {
            addCriterion("VISIT_DATE >=", value, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateLessThan(String value) {
            addCriterion("VISIT_DATE <", value, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateLessThanOrEqualTo(String value) {
            addCriterion("VISIT_DATE <=", value, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateLike(String value) {
            addCriterion("VISIT_DATE like", value, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateNotLike(String value) {
            addCriterion("VISIT_DATE not like", value, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateIn(List<String> values) {
            addCriterion("VISIT_DATE in", values, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateNotIn(List<String> values) {
            addCriterion("VISIT_DATE not in", values, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateBetween(String value1, String value2) {
            addCriterion("VISIT_DATE between", value1, value2, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateNotBetween(String value1, String value2) {
            addCriterion("VISIT_DATE not between", value1, value2, "visitDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateIsNull() {
            addCriterion("BIRTH_DATE is null");
            return (Criteria) this;
        }

        public Criteria andBirthDateIsNotNull() {
            addCriterion("BIRTH_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andBirthDateEqualTo(String value) {
            addCriterion("BIRTH_DATE =", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateNotEqualTo(String value) {
            addCriterion("BIRTH_DATE <>", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateGreaterThan(String value) {
            addCriterion("BIRTH_DATE >", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateGreaterThanOrEqualTo(String value) {
            addCriterion("BIRTH_DATE >=", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateLessThan(String value) {
            addCriterion("BIRTH_DATE <", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateLessThanOrEqualTo(String value) {
            addCriterion("BIRTH_DATE <=", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateLike(String value) {
            addCriterion("BIRTH_DATE like", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateNotLike(String value) {
            addCriterion("BIRTH_DATE not like", value, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateIn(List<String> values) {
            addCriterion("BIRTH_DATE in", values, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateNotIn(List<String> values) {
            addCriterion("BIRTH_DATE not in", values, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateBetween(String value1, String value2) {
            addCriterion("BIRTH_DATE between", value1, value2, "birthDate");
            return (Criteria) this;
        }

        public Criteria andBirthDateNotBetween(String value1, String value2) {
            addCriterion("BIRTH_DATE not between", value1, value2, "birthDate");
            return (Criteria) this;
        }

        public Criteria andVisitActionIdIsNull() {
            addCriterion("VISIT_ACTION_ID is null");
            return (Criteria) this;
        }

        public Criteria andVisitActionIdIsNotNull() {
            addCriterion("VISIT_ACTION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andVisitActionIdEqualTo(String value) {
            addCriterion("VISIT_ACTION_ID =", value, "visitActionId");
            return (Criteria) this;
        }

        public Criteria andVisitActionIdNotEqualTo(String value) {
            addCriterion("VISIT_ACTION_ID <>", value, "visitActionId");
            return (Criteria) this;
        }

        public Criteria andVisitActionIdGreaterThan(String value) {
            addCriterion("VISIT_ACTION_ID >", value, "visitActionId");
            return (Criteria) this;
        }

        public Criteria andVisitActionIdGreaterThanOrEqualTo(String value) {
            addCriterion("VISIT_ACTION_ID >=", value, "visitActionId");
            return (Criteria) this;
        }

        public Criteria andVisitActionIdLessThan(String value) {
            addCriterion("VISIT_ACTION_ID <", value, "visitActionId");
            return (Criteria) this;
        }

        public Criteria andVisitActionIdLessThanOrEqualTo(String value) {
            addCriterion("VISIT_ACTION_ID <=", value, "visitActionId");
            return (Criteria) this;
        }

        public Criteria andVisitActionIdLike(String value) {
            addCriterion("VISIT_ACTION_ID like", value, "visitActionId");
            return (Criteria) this;
        }

        public Criteria andVisitActionIdNotLike(String value) {
            addCriterion("VISIT_ACTION_ID not like", value, "visitActionId");
            return (Criteria) this;
        }

        public Criteria andVisitActionIdIn(List<String> values) {
            addCriterion("VISIT_ACTION_ID in", values, "visitActionId");
            return (Criteria) this;
        }

        public Criteria andVisitActionIdNotIn(List<String> values) {
            addCriterion("VISIT_ACTION_ID not in", values, "visitActionId");
            return (Criteria) this;
        }

        public Criteria andVisitActionIdBetween(String value1, String value2) {
            addCriterion("VISIT_ACTION_ID between", value1, value2, "visitActionId");
            return (Criteria) this;
        }

        public Criteria andVisitActionIdNotBetween(String value1, String value2) {
            addCriterion("VISIT_ACTION_ID not between", value1, value2, "visitActionId");
            return (Criteria) this;
        }

        public Criteria andVisitActionNameIsNull() {
            addCriterion("VISIT_ACTION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andVisitActionNameIsNotNull() {
            addCriterion("VISIT_ACTION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andVisitActionNameEqualTo(String value) {
            addCriterion("VISIT_ACTION_NAME =", value, "visitActionName");
            return (Criteria) this;
        }

        public Criteria andVisitActionNameNotEqualTo(String value) {
            addCriterion("VISIT_ACTION_NAME <>", value, "visitActionName");
            return (Criteria) this;
        }

        public Criteria andVisitActionNameGreaterThan(String value) {
            addCriterion("VISIT_ACTION_NAME >", value, "visitActionName");
            return (Criteria) this;
        }

        public Criteria andVisitActionNameGreaterThanOrEqualTo(String value) {
            addCriterion("VISIT_ACTION_NAME >=", value, "visitActionName");
            return (Criteria) this;
        }

        public Criteria andVisitActionNameLessThan(String value) {
            addCriterion("VISIT_ACTION_NAME <", value, "visitActionName");
            return (Criteria) this;
        }

        public Criteria andVisitActionNameLessThanOrEqualTo(String value) {
            addCriterion("VISIT_ACTION_NAME <=", value, "visitActionName");
            return (Criteria) this;
        }

        public Criteria andVisitActionNameLike(String value) {
            addCriterion("VISIT_ACTION_NAME like", value, "visitActionName");
            return (Criteria) this;
        }

        public Criteria andVisitActionNameNotLike(String value) {
            addCriterion("VISIT_ACTION_NAME not like", value, "visitActionName");
            return (Criteria) this;
        }

        public Criteria andVisitActionNameIn(List<String> values) {
            addCriterion("VISIT_ACTION_NAME in", values, "visitActionName");
            return (Criteria) this;
        }

        public Criteria andVisitActionNameNotIn(List<String> values) {
            addCriterion("VISIT_ACTION_NAME not in", values, "visitActionName");
            return (Criteria) this;
        }

        public Criteria andVisitActionNameBetween(String value1, String value2) {
            addCriterion("VISIT_ACTION_NAME between", value1, value2, "visitActionName");
            return (Criteria) this;
        }

        public Criteria andVisitActionNameNotBetween(String value1, String value2) {
            addCriterion("VISIT_ACTION_NAME not between", value1, value2, "visitActionName");
            return (Criteria) this;
        }

        public Criteria andSolarTermsIsNull() {
            addCriterion("SOLAR_TERMS is null");
            return (Criteria) this;
        }

        public Criteria andSolarTermsIsNotNull() {
            addCriterion("SOLAR_TERMS is not null");
            return (Criteria) this;
        }

        public Criteria andSolarTermsEqualTo(String value) {
            addCriterion("SOLAR_TERMS =", value, "solarTerms");
            return (Criteria) this;
        }

        public Criteria andSolarTermsNotEqualTo(String value) {
            addCriterion("SOLAR_TERMS <>", value, "solarTerms");
            return (Criteria) this;
        }

        public Criteria andSolarTermsGreaterThan(String value) {
            addCriterion("SOLAR_TERMS >", value, "solarTerms");
            return (Criteria) this;
        }

        public Criteria andSolarTermsGreaterThanOrEqualTo(String value) {
            addCriterion("SOLAR_TERMS >=", value, "solarTerms");
            return (Criteria) this;
        }

        public Criteria andSolarTermsLessThan(String value) {
            addCriterion("SOLAR_TERMS <", value, "solarTerms");
            return (Criteria) this;
        }

        public Criteria andSolarTermsLessThanOrEqualTo(String value) {
            addCriterion("SOLAR_TERMS <=", value, "solarTerms");
            return (Criteria) this;
        }

        public Criteria andSolarTermsLike(String value) {
            addCriterion("SOLAR_TERMS like", value, "solarTerms");
            return (Criteria) this;
        }

        public Criteria andSolarTermsNotLike(String value) {
            addCriterion("SOLAR_TERMS not like", value, "solarTerms");
            return (Criteria) this;
        }

        public Criteria andSolarTermsIn(List<String> values) {
            addCriterion("SOLAR_TERMS in", values, "solarTerms");
            return (Criteria) this;
        }

        public Criteria andSolarTermsNotIn(List<String> values) {
            addCriterion("SOLAR_TERMS not in", values, "solarTerms");
            return (Criteria) this;
        }

        public Criteria andSolarTermsBetween(String value1, String value2) {
            addCriterion("SOLAR_TERMS between", value1, value2, "solarTerms");
            return (Criteria) this;
        }

        public Criteria andSolarTermsNotBetween(String value1, String value2) {
            addCriterion("SOLAR_TERMS not between", value1, value2, "solarTerms");
            return (Criteria) this;
        }

        public Criteria andMainSuitIsNull() {
            addCriterion("MAIN_SUIT is null");
            return (Criteria) this;
        }

        public Criteria andMainSuitIsNotNull() {
            addCriterion("MAIN_SUIT is not null");
            return (Criteria) this;
        }

        public Criteria andMainSuitEqualTo(String value) {
            addCriterion("MAIN_SUIT =", value, "mainSuit");
            return (Criteria) this;
        }

        public Criteria andMainSuitNotEqualTo(String value) {
            addCriterion("MAIN_SUIT <>", value, "mainSuit");
            return (Criteria) this;
        }

        public Criteria andMainSuitGreaterThan(String value) {
            addCriterion("MAIN_SUIT >", value, "mainSuit");
            return (Criteria) this;
        }

        public Criteria andMainSuitGreaterThanOrEqualTo(String value) {
            addCriterion("MAIN_SUIT >=", value, "mainSuit");
            return (Criteria) this;
        }

        public Criteria andMainSuitLessThan(String value) {
            addCriterion("MAIN_SUIT <", value, "mainSuit");
            return (Criteria) this;
        }

        public Criteria andMainSuitLessThanOrEqualTo(String value) {
            addCriterion("MAIN_SUIT <=", value, "mainSuit");
            return (Criteria) this;
        }

        public Criteria andMainSuitLike(String value) {
            addCriterion("MAIN_SUIT like", value, "mainSuit");
            return (Criteria) this;
        }

        public Criteria andMainSuitNotLike(String value) {
            addCriterion("MAIN_SUIT not like", value, "mainSuit");
            return (Criteria) this;
        }

        public Criteria andMainSuitIn(List<String> values) {
            addCriterion("MAIN_SUIT in", values, "mainSuit");
            return (Criteria) this;
        }

        public Criteria andMainSuitNotIn(List<String> values) {
            addCriterion("MAIN_SUIT not in", values, "mainSuit");
            return (Criteria) this;
        }

        public Criteria andMainSuitBetween(String value1, String value2) {
            addCriterion("MAIN_SUIT between", value1, value2, "mainSuit");
            return (Criteria) this;
        }

        public Criteria andMainSuitNotBetween(String value1, String value2) {
            addCriterion("MAIN_SUIT not between", value1, value2, "mainSuit");
            return (Criteria) this;
        }

        public Criteria andPresentDiseaseHistoryIsNull() {
            addCriterion("PRESENT_DISEASE_HISTORY is null");
            return (Criteria) this;
        }

        public Criteria andPresentDiseaseHistoryIsNotNull() {
            addCriterion("PRESENT_DISEASE_HISTORY is not null");
            return (Criteria) this;
        }

        public Criteria andPresentDiseaseHistoryEqualTo(String value) {
            addCriterion("PRESENT_DISEASE_HISTORY =", value, "presentDiseaseHistory");
            return (Criteria) this;
        }

        public Criteria andPresentDiseaseHistoryNotEqualTo(String value) {
            addCriterion("PRESENT_DISEASE_HISTORY <>", value, "presentDiseaseHistory");
            return (Criteria) this;
        }

        public Criteria andPresentDiseaseHistoryGreaterThan(String value) {
            addCriterion("PRESENT_DISEASE_HISTORY >", value, "presentDiseaseHistory");
            return (Criteria) this;
        }

        public Criteria andPresentDiseaseHistoryGreaterThanOrEqualTo(String value) {
            addCriterion("PRESENT_DISEASE_HISTORY >=", value, "presentDiseaseHistory");
            return (Criteria) this;
        }

        public Criteria andPresentDiseaseHistoryLessThan(String value) {
            addCriterion("PRESENT_DISEASE_HISTORY <", value, "presentDiseaseHistory");
            return (Criteria) this;
        }

        public Criteria andPresentDiseaseHistoryLessThanOrEqualTo(String value) {
            addCriterion("PRESENT_DISEASE_HISTORY <=", value, "presentDiseaseHistory");
            return (Criteria) this;
        }

        public Criteria andPresentDiseaseHistoryLike(String value) {
            addCriterion("PRESENT_DISEASE_HISTORY like", value, "presentDiseaseHistory");
            return (Criteria) this;
        }

        public Criteria andPresentDiseaseHistoryNotLike(String value) {
            addCriterion("PRESENT_DISEASE_HISTORY not like", value, "presentDiseaseHistory");
            return (Criteria) this;
        }

        public Criteria andPresentDiseaseHistoryIn(List<String> values) {
            addCriterion("PRESENT_DISEASE_HISTORY in", values, "presentDiseaseHistory");
            return (Criteria) this;
        }

        public Criteria andPresentDiseaseHistoryNotIn(List<String> values) {
            addCriterion("PRESENT_DISEASE_HISTORY not in", values, "presentDiseaseHistory");
            return (Criteria) this;
        }

        public Criteria andPresentDiseaseHistoryBetween(String value1, String value2) {
            addCriterion("PRESENT_DISEASE_HISTORY between", value1, value2, "presentDiseaseHistory");
            return (Criteria) this;
        }

        public Criteria andPresentDiseaseHistoryNotBetween(String value1, String value2) {
            addCriterion("PRESENT_DISEASE_HISTORY not between", value1, value2, "presentDiseaseHistory");
            return (Criteria) this;
        }

        public Criteria andPreviousDiseaseHistoryIsNull() {
            addCriterion("PREVIOUS_DISEASE_HISTORY is null");
            return (Criteria) this;
        }

        public Criteria andPreviousDiseaseHistoryIsNotNull() {
            addCriterion("PREVIOUS_DISEASE_HISTORY is not null");
            return (Criteria) this;
        }

        public Criteria andPreviousDiseaseHistoryEqualTo(String value) {
            addCriterion("PREVIOUS_DISEASE_HISTORY =", value, "previousDiseaseHistory");
            return (Criteria) this;
        }

        public Criteria andPreviousDiseaseHistoryNotEqualTo(String value) {
            addCriterion("PREVIOUS_DISEASE_HISTORY <>", value, "previousDiseaseHistory");
            return (Criteria) this;
        }

        public Criteria andPreviousDiseaseHistoryGreaterThan(String value) {
            addCriterion("PREVIOUS_DISEASE_HISTORY >", value, "previousDiseaseHistory");
            return (Criteria) this;
        }

        public Criteria andPreviousDiseaseHistoryGreaterThanOrEqualTo(String value) {
            addCriterion("PREVIOUS_DISEASE_HISTORY >=", value, "previousDiseaseHistory");
            return (Criteria) this;
        }

        public Criteria andPreviousDiseaseHistoryLessThan(String value) {
            addCriterion("PREVIOUS_DISEASE_HISTORY <", value, "previousDiseaseHistory");
            return (Criteria) this;
        }

        public Criteria andPreviousDiseaseHistoryLessThanOrEqualTo(String value) {
            addCriterion("PREVIOUS_DISEASE_HISTORY <=", value, "previousDiseaseHistory");
            return (Criteria) this;
        }

        public Criteria andPreviousDiseaseHistoryLike(String value) {
            addCriterion("PREVIOUS_DISEASE_HISTORY like", value, "previousDiseaseHistory");
            return (Criteria) this;
        }

        public Criteria andPreviousDiseaseHistoryNotLike(String value) {
            addCriterion("PREVIOUS_DISEASE_HISTORY not like", value, "previousDiseaseHistory");
            return (Criteria) this;
        }

        public Criteria andPreviousDiseaseHistoryIn(List<String> values) {
            addCriterion("PREVIOUS_DISEASE_HISTORY in", values, "previousDiseaseHistory");
            return (Criteria) this;
        }

        public Criteria andPreviousDiseaseHistoryNotIn(List<String> values) {
            addCriterion("PREVIOUS_DISEASE_HISTORY not in", values, "previousDiseaseHistory");
            return (Criteria) this;
        }

        public Criteria andPreviousDiseaseHistoryBetween(String value1, String value2) {
            addCriterion("PREVIOUS_DISEASE_HISTORY between", value1, value2, "previousDiseaseHistory");
            return (Criteria) this;
        }

        public Criteria andPreviousDiseaseHistoryNotBetween(String value1, String value2) {
            addCriterion("PREVIOUS_DISEASE_HISTORY not between", value1, value2, "previousDiseaseHistory");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryIsNull() {
            addCriterion("ALLERGIC_HISTORY is null");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryIsNotNull() {
            addCriterion("ALLERGIC_HISTORY is not null");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryEqualTo(String value) {
            addCriterion("ALLERGIC_HISTORY =", value, "allergicHistory");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryNotEqualTo(String value) {
            addCriterion("ALLERGIC_HISTORY <>", value, "allergicHistory");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryGreaterThan(String value) {
            addCriterion("ALLERGIC_HISTORY >", value, "allergicHistory");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryGreaterThanOrEqualTo(String value) {
            addCriterion("ALLERGIC_HISTORY >=", value, "allergicHistory");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryLessThan(String value) {
            addCriterion("ALLERGIC_HISTORY <", value, "allergicHistory");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryLessThanOrEqualTo(String value) {
            addCriterion("ALLERGIC_HISTORY <=", value, "allergicHistory");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryLike(String value) {
            addCriterion("ALLERGIC_HISTORY like", value, "allergicHistory");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryNotLike(String value) {
            addCriterion("ALLERGIC_HISTORY not like", value, "allergicHistory");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryIn(List<String> values) {
            addCriterion("ALLERGIC_HISTORY in", values, "allergicHistory");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryNotIn(List<String> values) {
            addCriterion("ALLERGIC_HISTORY not in", values, "allergicHistory");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryBetween(String value1, String value2) {
            addCriterion("ALLERGIC_HISTORY between", value1, value2, "allergicHistory");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryNotBetween(String value1, String value2) {
            addCriterion("ALLERGIC_HISTORY not between", value1, value2, "allergicHistory");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationIsNull() {
            addCriterion("PHYSICAL_EXAMINATION is null");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationIsNotNull() {
            addCriterion("PHYSICAL_EXAMINATION is not null");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationEqualTo(String value) {
            addCriterion("PHYSICAL_EXAMINATION =", value, "physicalExamination");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationNotEqualTo(String value) {
            addCriterion("PHYSICAL_EXAMINATION <>", value, "physicalExamination");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationGreaterThan(String value) {
            addCriterion("PHYSICAL_EXAMINATION >", value, "physicalExamination");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationGreaterThanOrEqualTo(String value) {
            addCriterion("PHYSICAL_EXAMINATION >=", value, "physicalExamination");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationLessThan(String value) {
            addCriterion("PHYSICAL_EXAMINATION <", value, "physicalExamination");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationLessThanOrEqualTo(String value) {
            addCriterion("PHYSICAL_EXAMINATION <=", value, "physicalExamination");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationLike(String value) {
            addCriterion("PHYSICAL_EXAMINATION like", value, "physicalExamination");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationNotLike(String value) {
            addCriterion("PHYSICAL_EXAMINATION not like", value, "physicalExamination");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationIn(List<String> values) {
            addCriterion("PHYSICAL_EXAMINATION in", values, "physicalExamination");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationNotIn(List<String> values) {
            addCriterion("PHYSICAL_EXAMINATION not in", values, "physicalExamination");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationBetween(String value1, String value2) {
            addCriterion("PHYSICAL_EXAMINATION between", value1, value2, "physicalExamination");
            return (Criteria) this;
        }

        public Criteria andPhysicalExaminationNotBetween(String value1, String value2) {
            addCriterion("PHYSICAL_EXAMINATION not between", value1, value2, "physicalExamination");
            return (Criteria) this;
        }

        public Criteria andAccessoryExaminationIsNull() {
            addCriterion("ACCESSORY_EXAMINATION is null");
            return (Criteria) this;
        }

        public Criteria andAccessoryExaminationIsNotNull() {
            addCriterion("ACCESSORY_EXAMINATION is not null");
            return (Criteria) this;
        }

        public Criteria andAccessoryExaminationEqualTo(String value) {
            addCriterion("ACCESSORY_EXAMINATION =", value, "accessoryExamination");
            return (Criteria) this;
        }

        public Criteria andAccessoryExaminationNotEqualTo(String value) {
            addCriterion("ACCESSORY_EXAMINATION <>", value, "accessoryExamination");
            return (Criteria) this;
        }

        public Criteria andAccessoryExaminationGreaterThan(String value) {
            addCriterion("ACCESSORY_EXAMINATION >", value, "accessoryExamination");
            return (Criteria) this;
        }

        public Criteria andAccessoryExaminationGreaterThanOrEqualTo(String value) {
            addCriterion("ACCESSORY_EXAMINATION >=", value, "accessoryExamination");
            return (Criteria) this;
        }

        public Criteria andAccessoryExaminationLessThan(String value) {
            addCriterion("ACCESSORY_EXAMINATION <", value, "accessoryExamination");
            return (Criteria) this;
        }

        public Criteria andAccessoryExaminationLessThanOrEqualTo(String value) {
            addCriterion("ACCESSORY_EXAMINATION <=", value, "accessoryExamination");
            return (Criteria) this;
        }

        public Criteria andAccessoryExaminationLike(String value) {
            addCriterion("ACCESSORY_EXAMINATION like", value, "accessoryExamination");
            return (Criteria) this;
        }

        public Criteria andAccessoryExaminationNotLike(String value) {
            addCriterion("ACCESSORY_EXAMINATION not like", value, "accessoryExamination");
            return (Criteria) this;
        }

        public Criteria andAccessoryExaminationIn(List<String> values) {
            addCriterion("ACCESSORY_EXAMINATION in", values, "accessoryExamination");
            return (Criteria) this;
        }

        public Criteria andAccessoryExaminationNotIn(List<String> values) {
            addCriterion("ACCESSORY_EXAMINATION not in", values, "accessoryExamination");
            return (Criteria) this;
        }

        public Criteria andAccessoryExaminationBetween(String value1, String value2) {
            addCriterion("ACCESSORY_EXAMINATION between", value1, value2, "accessoryExamination");
            return (Criteria) this;
        }

        public Criteria andAccessoryExaminationNotBetween(String value1, String value2) {
            addCriterion("ACCESSORY_EXAMINATION not between", value1, value2, "accessoryExamination");
            return (Criteria) this;
        }

        public Criteria andTcmDiagnosisIsNull() {
            addCriterion("TCM_DIAGNOSIS is null");
            return (Criteria) this;
        }

        public Criteria andTcmDiagnosisIsNotNull() {
            addCriterion("TCM_DIAGNOSIS is not null");
            return (Criteria) this;
        }

        public Criteria andTcmDiagnosisEqualTo(String value) {
            addCriterion("TCM_DIAGNOSIS =", value, "tcmDiagnosis");
            return (Criteria) this;
        }

        public Criteria andTcmDiagnosisNotEqualTo(String value) {
            addCriterion("TCM_DIAGNOSIS <>", value, "tcmDiagnosis");
            return (Criteria) this;
        }

        public Criteria andTcmDiagnosisGreaterThan(String value) {
            addCriterion("TCM_DIAGNOSIS >", value, "tcmDiagnosis");
            return (Criteria) this;
        }

        public Criteria andTcmDiagnosisGreaterThanOrEqualTo(String value) {
            addCriterion("TCM_DIAGNOSIS >=", value, "tcmDiagnosis");
            return (Criteria) this;
        }

        public Criteria andTcmDiagnosisLessThan(String value) {
            addCriterion("TCM_DIAGNOSIS <", value, "tcmDiagnosis");
            return (Criteria) this;
        }

        public Criteria andTcmDiagnosisLessThanOrEqualTo(String value) {
            addCriterion("TCM_DIAGNOSIS <=", value, "tcmDiagnosis");
            return (Criteria) this;
        }

        public Criteria andTcmDiagnosisLike(String value) {
            addCriterion("TCM_DIAGNOSIS like", value, "tcmDiagnosis");
            return (Criteria) this;
        }

        public Criteria andTcmDiagnosisNotLike(String value) {
            addCriterion("TCM_DIAGNOSIS not like", value, "tcmDiagnosis");
            return (Criteria) this;
        }

        public Criteria andTcmDiagnosisIn(List<String> values) {
            addCriterion("TCM_DIAGNOSIS in", values, "tcmDiagnosis");
            return (Criteria) this;
        }

        public Criteria andTcmDiagnosisNotIn(List<String> values) {
            addCriterion("TCM_DIAGNOSIS not in", values, "tcmDiagnosis");
            return (Criteria) this;
        }

        public Criteria andTcmDiagnosisBetween(String value1, String value2) {
            addCriterion("TCM_DIAGNOSIS between", value1, value2, "tcmDiagnosis");
            return (Criteria) this;
        }

        public Criteria andTcmDiagnosisNotBetween(String value1, String value2) {
            addCriterion("TCM_DIAGNOSIS not between", value1, value2, "tcmDiagnosis");
            return (Criteria) this;
        }

        public Criteria andSyndromeDiagnosisIsNull() {
            addCriterion("SYNDROME_DIAGNOSIS is null");
            return (Criteria) this;
        }

        public Criteria andSyndromeDiagnosisIsNotNull() {
            addCriterion("SYNDROME_DIAGNOSIS is not null");
            return (Criteria) this;
        }

        public Criteria andSyndromeDiagnosisEqualTo(String value) {
            addCriterion("SYNDROME_DIAGNOSIS =", value, "syndromeDiagnosis");
            return (Criteria) this;
        }

        public Criteria andSyndromeDiagnosisNotEqualTo(String value) {
            addCriterion("SYNDROME_DIAGNOSIS <>", value, "syndromeDiagnosis");
            return (Criteria) this;
        }

        public Criteria andSyndromeDiagnosisGreaterThan(String value) {
            addCriterion("SYNDROME_DIAGNOSIS >", value, "syndromeDiagnosis");
            return (Criteria) this;
        }

        public Criteria andSyndromeDiagnosisGreaterThanOrEqualTo(String value) {
            addCriterion("SYNDROME_DIAGNOSIS >=", value, "syndromeDiagnosis");
            return (Criteria) this;
        }

        public Criteria andSyndromeDiagnosisLessThan(String value) {
            addCriterion("SYNDROME_DIAGNOSIS <", value, "syndromeDiagnosis");
            return (Criteria) this;
        }

        public Criteria andSyndromeDiagnosisLessThanOrEqualTo(String value) {
            addCriterion("SYNDROME_DIAGNOSIS <=", value, "syndromeDiagnosis");
            return (Criteria) this;
        }

        public Criteria andSyndromeDiagnosisLike(String value) {
            addCriterion("SYNDROME_DIAGNOSIS like", value, "syndromeDiagnosis");
            return (Criteria) this;
        }

        public Criteria andSyndromeDiagnosisNotLike(String value) {
            addCriterion("SYNDROME_DIAGNOSIS not like", value, "syndromeDiagnosis");
            return (Criteria) this;
        }

        public Criteria andSyndromeDiagnosisIn(List<String> values) {
            addCriterion("SYNDROME_DIAGNOSIS in", values, "syndromeDiagnosis");
            return (Criteria) this;
        }

        public Criteria andSyndromeDiagnosisNotIn(List<String> values) {
            addCriterion("SYNDROME_DIAGNOSIS not in", values, "syndromeDiagnosis");
            return (Criteria) this;
        }

        public Criteria andSyndromeDiagnosisBetween(String value1, String value2) {
            addCriterion("SYNDROME_DIAGNOSIS between", value1, value2, "syndromeDiagnosis");
            return (Criteria) this;
        }

        public Criteria andSyndromeDiagnosisNotBetween(String value1, String value2) {
            addCriterion("SYNDROME_DIAGNOSIS not between", value1, value2, "syndromeDiagnosis");
            return (Criteria) this;
        }

        public Criteria andWesternDiagnosisIsNull() {
            addCriterion("WESTERN_DIAGNOSIS is null");
            return (Criteria) this;
        }

        public Criteria andWesternDiagnosisIsNotNull() {
            addCriterion("WESTERN_DIAGNOSIS is not null");
            return (Criteria) this;
        }

        public Criteria andWesternDiagnosisEqualTo(String value) {
            addCriterion("WESTERN_DIAGNOSIS =", value, "westernDiagnosis");
            return (Criteria) this;
        }

        public Criteria andWesternDiagnosisNotEqualTo(String value) {
            addCriterion("WESTERN_DIAGNOSIS <>", value, "westernDiagnosis");
            return (Criteria) this;
        }

        public Criteria andWesternDiagnosisGreaterThan(String value) {
            addCriterion("WESTERN_DIAGNOSIS >", value, "westernDiagnosis");
            return (Criteria) this;
        }

        public Criteria andWesternDiagnosisGreaterThanOrEqualTo(String value) {
            addCriterion("WESTERN_DIAGNOSIS >=", value, "westernDiagnosis");
            return (Criteria) this;
        }

        public Criteria andWesternDiagnosisLessThan(String value) {
            addCriterion("WESTERN_DIAGNOSIS <", value, "westernDiagnosis");
            return (Criteria) this;
        }

        public Criteria andWesternDiagnosisLessThanOrEqualTo(String value) {
            addCriterion("WESTERN_DIAGNOSIS <=", value, "westernDiagnosis");
            return (Criteria) this;
        }

        public Criteria andWesternDiagnosisLike(String value) {
            addCriterion("WESTERN_DIAGNOSIS like", value, "westernDiagnosis");
            return (Criteria) this;
        }

        public Criteria andWesternDiagnosisNotLike(String value) {
            addCriterion("WESTERN_DIAGNOSIS not like", value, "westernDiagnosis");
            return (Criteria) this;
        }

        public Criteria andWesternDiagnosisIn(List<String> values) {
            addCriterion("WESTERN_DIAGNOSIS in", values, "westernDiagnosis");
            return (Criteria) this;
        }

        public Criteria andWesternDiagnosisNotIn(List<String> values) {
            addCriterion("WESTERN_DIAGNOSIS not in", values, "westernDiagnosis");
            return (Criteria) this;
        }

        public Criteria andWesternDiagnosisBetween(String value1, String value2) {
            addCriterion("WESTERN_DIAGNOSIS between", value1, value2, "westernDiagnosis");
            return (Criteria) this;
        }

        public Criteria andWesternDiagnosisNotBetween(String value1, String value2) {
            addCriterion("WESTERN_DIAGNOSIS not between", value1, value2, "westernDiagnosis");
            return (Criteria) this;
        }

        public Criteria andTherapyIsNull() {
            addCriterion("THERAPY is null");
            return (Criteria) this;
        }

        public Criteria andTherapyIsNotNull() {
            addCriterion("THERAPY is not null");
            return (Criteria) this;
        }

        public Criteria andTherapyEqualTo(String value) {
            addCriterion("THERAPY =", value, "therapy");
            return (Criteria) this;
        }

        public Criteria andTherapyNotEqualTo(String value) {
            addCriterion("THERAPY <>", value, "therapy");
            return (Criteria) this;
        }

        public Criteria andTherapyGreaterThan(String value) {
            addCriterion("THERAPY >", value, "therapy");
            return (Criteria) this;
        }

        public Criteria andTherapyGreaterThanOrEqualTo(String value) {
            addCriterion("THERAPY >=", value, "therapy");
            return (Criteria) this;
        }

        public Criteria andTherapyLessThan(String value) {
            addCriterion("THERAPY <", value, "therapy");
            return (Criteria) this;
        }

        public Criteria andTherapyLessThanOrEqualTo(String value) {
            addCriterion("THERAPY <=", value, "therapy");
            return (Criteria) this;
        }

        public Criteria andTherapyLike(String value) {
            addCriterion("THERAPY like", value, "therapy");
            return (Criteria) this;
        }

        public Criteria andTherapyNotLike(String value) {
            addCriterion("THERAPY not like", value, "therapy");
            return (Criteria) this;
        }

        public Criteria andTherapyIn(List<String> values) {
            addCriterion("THERAPY in", values, "therapy");
            return (Criteria) this;
        }

        public Criteria andTherapyNotIn(List<String> values) {
            addCriterion("THERAPY not in", values, "therapy");
            return (Criteria) this;
        }

        public Criteria andTherapyBetween(String value1, String value2) {
            addCriterion("THERAPY between", value1, value2, "therapy");
            return (Criteria) this;
        }

        public Criteria andTherapyNotBetween(String value1, String value2) {
            addCriterion("THERAPY not between", value1, value2, "therapy");
            return (Criteria) this;
        }

        public Criteria andPrescriptionIsNull() {
            addCriterion("PRESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andPrescriptionIsNotNull() {
            addCriterion("PRESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andPrescriptionEqualTo(String value) {
            addCriterion("PRESCRIPTION =", value, "prescription");
            return (Criteria) this;
        }

        public Criteria andPrescriptionNotEqualTo(String value) {
            addCriterion("PRESCRIPTION <>", value, "prescription");
            return (Criteria) this;
        }

        public Criteria andPrescriptionGreaterThan(String value) {
            addCriterion("PRESCRIPTION >", value, "prescription");
            return (Criteria) this;
        }

        public Criteria andPrescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("PRESCRIPTION >=", value, "prescription");
            return (Criteria) this;
        }

        public Criteria andPrescriptionLessThan(String value) {
            addCriterion("PRESCRIPTION <", value, "prescription");
            return (Criteria) this;
        }

        public Criteria andPrescriptionLessThanOrEqualTo(String value) {
            addCriterion("PRESCRIPTION <=", value, "prescription");
            return (Criteria) this;
        }

        public Criteria andPrescriptionLike(String value) {
            addCriterion("PRESCRIPTION like", value, "prescription");
            return (Criteria) this;
        }

        public Criteria andPrescriptionNotLike(String value) {
            addCriterion("PRESCRIPTION not like", value, "prescription");
            return (Criteria) this;
        }

        public Criteria andPrescriptionIn(List<String> values) {
            addCriterion("PRESCRIPTION in", values, "prescription");
            return (Criteria) this;
        }

        public Criteria andPrescriptionNotIn(List<String> values) {
            addCriterion("PRESCRIPTION not in", values, "prescription");
            return (Criteria) this;
        }

        public Criteria andPrescriptionBetween(String value1, String value2) {
            addCriterion("PRESCRIPTION between", value1, value2, "prescription");
            return (Criteria) this;
        }

        public Criteria andPrescriptionNotBetween(String value1, String value2) {
            addCriterion("PRESCRIPTION not between", value1, value2, "prescription");
            return (Criteria) this;
        }

        public Criteria andReturnVisitIsNull() {
            addCriterion("RETURN_VISIT is null");
            return (Criteria) this;
        }

        public Criteria andReturnVisitIsNotNull() {
            addCriterion("RETURN_VISIT is not null");
            return (Criteria) this;
        }

        public Criteria andReturnVisitEqualTo(String value) {
            addCriterion("RETURN_VISIT =", value, "returnVisit");
            return (Criteria) this;
        }

        public Criteria andReturnVisitNotEqualTo(String value) {
            addCriterion("RETURN_VISIT <>", value, "returnVisit");
            return (Criteria) this;
        }

        public Criteria andReturnVisitGreaterThan(String value) {
            addCriterion("RETURN_VISIT >", value, "returnVisit");
            return (Criteria) this;
        }

        public Criteria andReturnVisitGreaterThanOrEqualTo(String value) {
            addCriterion("RETURN_VISIT >=", value, "returnVisit");
            return (Criteria) this;
        }

        public Criteria andReturnVisitLessThan(String value) {
            addCriterion("RETURN_VISIT <", value, "returnVisit");
            return (Criteria) this;
        }

        public Criteria andReturnVisitLessThanOrEqualTo(String value) {
            addCriterion("RETURN_VISIT <=", value, "returnVisit");
            return (Criteria) this;
        }

        public Criteria andReturnVisitLike(String value) {
            addCriterion("RETURN_VISIT like", value, "returnVisit");
            return (Criteria) this;
        }

        public Criteria andReturnVisitNotLike(String value) {
            addCriterion("RETURN_VISIT not like", value, "returnVisit");
            return (Criteria) this;
        }

        public Criteria andReturnVisitIn(List<String> values) {
            addCriterion("RETURN_VISIT in", values, "returnVisit");
            return (Criteria) this;
        }

        public Criteria andReturnVisitNotIn(List<String> values) {
            addCriterion("RETURN_VISIT not in", values, "returnVisit");
            return (Criteria) this;
        }

        public Criteria andReturnVisitBetween(String value1, String value2) {
            addCriterion("RETURN_VISIT between", value1, value2, "returnVisit");
            return (Criteria) this;
        }

        public Criteria andReturnVisitNotBetween(String value1, String value2) {
            addCriterion("RETURN_VISIT not between", value1, value2, "returnVisit");
            return (Criteria) this;
        }

        public Criteria andExperienceContentIsNull() {
            addCriterion("EXPERIENCE_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andExperienceContentIsNotNull() {
            addCriterion("EXPERIENCE_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andExperienceContentEqualTo(String value) {
            addCriterion("EXPERIENCE_CONTENT =", value, "experienceContent");
            return (Criteria) this;
        }

        public Criteria andExperienceContentNotEqualTo(String value) {
            addCriterion("EXPERIENCE_CONTENT <>", value, "experienceContent");
            return (Criteria) this;
        }

        public Criteria andExperienceContentGreaterThan(String value) {
            addCriterion("EXPERIENCE_CONTENT >", value, "experienceContent");
            return (Criteria) this;
        }

        public Criteria andExperienceContentGreaterThanOrEqualTo(String value) {
            addCriterion("EXPERIENCE_CONTENT >=", value, "experienceContent");
            return (Criteria) this;
        }

        public Criteria andExperienceContentLessThan(String value) {
            addCriterion("EXPERIENCE_CONTENT <", value, "experienceContent");
            return (Criteria) this;
        }

        public Criteria andExperienceContentLessThanOrEqualTo(String value) {
            addCriterion("EXPERIENCE_CONTENT <=", value, "experienceContent");
            return (Criteria) this;
        }

        public Criteria andExperienceContentLike(String value) {
            addCriterion("EXPERIENCE_CONTENT like", value, "experienceContent");
            return (Criteria) this;
        }

        public Criteria andExperienceContentNotLike(String value) {
            addCriterion("EXPERIENCE_CONTENT not like", value, "experienceContent");
            return (Criteria) this;
        }

        public Criteria andExperienceContentIn(List<String> values) {
            addCriterion("EXPERIENCE_CONTENT in", values, "experienceContent");
            return (Criteria) this;
        }

        public Criteria andExperienceContentNotIn(List<String> values) {
            addCriterion("EXPERIENCE_CONTENT not in", values, "experienceContent");
            return (Criteria) this;
        }

        public Criteria andExperienceContentBetween(String value1, String value2) {
            addCriterion("EXPERIENCE_CONTENT between", value1, value2, "experienceContent");
            return (Criteria) this;
        }

        public Criteria andExperienceContentNotBetween(String value1, String value2) {
            addCriterion("EXPERIENCE_CONTENT not between", value1, value2, "experienceContent");
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