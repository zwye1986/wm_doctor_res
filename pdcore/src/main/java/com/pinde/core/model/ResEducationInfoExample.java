package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResEducationInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResEducationInfoExample() {
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

        public Criteria andEducationFlowIsNull() {
            addCriterion("EDUCATION_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andEducationFlowIsNotNull() {
            addCriterion("EDUCATION_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andEducationFlowEqualTo(String value) {
            addCriterion("EDUCATION_FLOW =", value, "educationFlow");
            return (Criteria) this;
        }

        public Criteria andEducationFlowNotEqualTo(String value) {
            addCriterion("EDUCATION_FLOW <>", value, "educationFlow");
            return (Criteria) this;
        }

        public Criteria andEducationFlowGreaterThan(String value) {
            addCriterion("EDUCATION_FLOW >", value, "educationFlow");
            return (Criteria) this;
        }

        public Criteria andEducationFlowGreaterThanOrEqualTo(String value) {
            addCriterion("EDUCATION_FLOW >=", value, "educationFlow");
            return (Criteria) this;
        }

        public Criteria andEducationFlowLessThan(String value) {
            addCriterion("EDUCATION_FLOW <", value, "educationFlow");
            return (Criteria) this;
        }

        public Criteria andEducationFlowLessThanOrEqualTo(String value) {
            addCriterion("EDUCATION_FLOW <=", value, "educationFlow");
            return (Criteria) this;
        }

        public Criteria andEducationFlowLike(String value) {
            addCriterion("EDUCATION_FLOW like", value, "educationFlow");
            return (Criteria) this;
        }

        public Criteria andEducationFlowNotLike(String value) {
            addCriterion("EDUCATION_FLOW not like", value, "educationFlow");
            return (Criteria) this;
        }

        public Criteria andEducationFlowIn(List<String> values) {
            addCriterion("EDUCATION_FLOW in", values, "educationFlow");
            return (Criteria) this;
        }

        public Criteria andEducationFlowNotIn(List<String> values) {
            addCriterion("EDUCATION_FLOW not in", values, "educationFlow");
            return (Criteria) this;
        }

        public Criteria andEducationFlowBetween(String value1, String value2) {
            addCriterion("EDUCATION_FLOW between", value1, value2, "educationFlow");
            return (Criteria) this;
        }

        public Criteria andEducationFlowNotBetween(String value1, String value2) {
            addCriterion("EDUCATION_FLOW not between", value1, value2, "educationFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowIsNull() {
            addCriterion("USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andUserFlowIsNotNull() {
            addCriterion("USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andUserFlowEqualTo(String value) {
            addCriterion("USER_FLOW =", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotEqualTo(String value) {
            addCriterion("USER_FLOW <>", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowGreaterThan(String value) {
            addCriterion("USER_FLOW >", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("USER_FLOW >=", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowLessThan(String value) {
            addCriterion("USER_FLOW <", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowLessThanOrEqualTo(String value) {
            addCriterion("USER_FLOW <=", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowLike(String value) {
            addCriterion("USER_FLOW like", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotLike(String value) {
            addCriterion("USER_FLOW not like", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowIn(List<String> values) {
            addCriterion("USER_FLOW in", values, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotIn(List<String> values) {
            addCriterion("USER_FLOW not in", values, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowBetween(String value1, String value2) {
            addCriterion("USER_FLOW between", value1, value2, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotBetween(String value1, String value2) {
            addCriterion("USER_FLOW not between", value1, value2, "userFlow");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolIsNull() {
            addCriterion("GRADUATION_SCHOOL is null");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolIsNotNull() {
            addCriterion("GRADUATION_SCHOOL is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolEqualTo(String value) {
            addCriterion("GRADUATION_SCHOOL =", value, "graduationSchool");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolNotEqualTo(String value) {
            addCriterion("GRADUATION_SCHOOL <>", value, "graduationSchool");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolGreaterThan(String value) {
            addCriterion("GRADUATION_SCHOOL >", value, "graduationSchool");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATION_SCHOOL >=", value, "graduationSchool");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolLessThan(String value) {
            addCriterion("GRADUATION_SCHOOL <", value, "graduationSchool");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolLessThanOrEqualTo(String value) {
            addCriterion("GRADUATION_SCHOOL <=", value, "graduationSchool");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolLike(String value) {
            addCriterion("GRADUATION_SCHOOL like", value, "graduationSchool");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolNotLike(String value) {
            addCriterion("GRADUATION_SCHOOL not like", value, "graduationSchool");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolIn(List<String> values) {
            addCriterion("GRADUATION_SCHOOL in", values, "graduationSchool");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolNotIn(List<String> values) {
            addCriterion("GRADUATION_SCHOOL not in", values, "graduationSchool");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolBetween(String value1, String value2) {
            addCriterion("GRADUATION_SCHOOL between", value1, value2, "graduationSchool");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolNotBetween(String value1, String value2) {
            addCriterion("GRADUATION_SCHOOL not between", value1, value2, "graduationSchool");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeIsNull() {
            addCriterion("GRADUATION_TIME is null");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeIsNotNull() {
            addCriterion("GRADUATION_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeEqualTo(String value) {
            addCriterion("GRADUATION_TIME =", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeNotEqualTo(String value) {
            addCriterion("GRADUATION_TIME <>", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeGreaterThan(String value) {
            addCriterion("GRADUATION_TIME >", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATION_TIME >=", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeLessThan(String value) {
            addCriterion("GRADUATION_TIME <", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeLessThanOrEqualTo(String value) {
            addCriterion("GRADUATION_TIME <=", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeLike(String value) {
            addCriterion("GRADUATION_TIME like", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeNotLike(String value) {
            addCriterion("GRADUATION_TIME not like", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeIn(List<String> values) {
            addCriterion("GRADUATION_TIME in", values, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeNotIn(List<String> values) {
            addCriterion("GRADUATION_TIME not in", values, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeBetween(String value1, String value2) {
            addCriterion("GRADUATION_TIME between", value1, value2, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeNotBetween(String value1, String value2) {
            addCriterion("GRADUATION_TIME not between", value1, value2, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeIsNull() {
            addCriterion("GRADUATION_SPE is null");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeIsNotNull() {
            addCriterion("GRADUATION_SPE is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeEqualTo(String value) {
            addCriterion("GRADUATION_SPE =", value, "graduationSpe");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeNotEqualTo(String value) {
            addCriterion("GRADUATION_SPE <>", value, "graduationSpe");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeGreaterThan(String value) {
            addCriterion("GRADUATION_SPE >", value, "graduationSpe");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATION_SPE >=", value, "graduationSpe");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeLessThan(String value) {
            addCriterion("GRADUATION_SPE <", value, "graduationSpe");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeLessThanOrEqualTo(String value) {
            addCriterion("GRADUATION_SPE <=", value, "graduationSpe");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeLike(String value) {
            addCriterion("GRADUATION_SPE like", value, "graduationSpe");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeNotLike(String value) {
            addCriterion("GRADUATION_SPE not like", value, "graduationSpe");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeIn(List<String> values) {
            addCriterion("GRADUATION_SPE in", values, "graduationSpe");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeNotIn(List<String> values) {
            addCriterion("GRADUATION_SPE not in", values, "graduationSpe");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeBetween(String value1, String value2) {
            addCriterion("GRADUATION_SPE between", value1, value2, "graduationSpe");
            return (Criteria) this;
        }

        public Criteria andGraduationSpeNotBetween(String value1, String value2) {
            addCriterion("GRADUATION_SPE not between", value1, value2, "graduationSpe");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundIdIsNull() {
            addCriterion("ACADEMIC_BACKGROUND_ID is null");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundIdIsNotNull() {
            addCriterion("ACADEMIC_BACKGROUND_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundIdEqualTo(String value) {
            addCriterion("ACADEMIC_BACKGROUND_ID =", value, "academicBackgroundId");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundIdNotEqualTo(String value) {
            addCriterion("ACADEMIC_BACKGROUND_ID <>", value, "academicBackgroundId");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundIdGreaterThan(String value) {
            addCriterion("ACADEMIC_BACKGROUND_ID >", value, "academicBackgroundId");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundIdGreaterThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_BACKGROUND_ID >=", value, "academicBackgroundId");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundIdLessThan(String value) {
            addCriterion("ACADEMIC_BACKGROUND_ID <", value, "academicBackgroundId");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundIdLessThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_BACKGROUND_ID <=", value, "academicBackgroundId");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundIdLike(String value) {
            addCriterion("ACADEMIC_BACKGROUND_ID like", value, "academicBackgroundId");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundIdNotLike(String value) {
            addCriterion("ACADEMIC_BACKGROUND_ID not like", value, "academicBackgroundId");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundIdIn(List<String> values) {
            addCriterion("ACADEMIC_BACKGROUND_ID in", values, "academicBackgroundId");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundIdNotIn(List<String> values) {
            addCriterion("ACADEMIC_BACKGROUND_ID not in", values, "academicBackgroundId");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundIdBetween(String value1, String value2) {
            addCriterion("ACADEMIC_BACKGROUND_ID between", value1, value2, "academicBackgroundId");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundIdNotBetween(String value1, String value2) {
            addCriterion("ACADEMIC_BACKGROUND_ID not between", value1, value2, "academicBackgroundId");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundNameIsNull() {
            addCriterion("ACADEMIC_BACKGROUND_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundNameIsNotNull() {
            addCriterion("ACADEMIC_BACKGROUND_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundNameEqualTo(String value) {
            addCriterion("ACADEMIC_BACKGROUND_NAME =", value, "academicBackgroundName");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundNameNotEqualTo(String value) {
            addCriterion("ACADEMIC_BACKGROUND_NAME <>", value, "academicBackgroundName");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundNameGreaterThan(String value) {
            addCriterion("ACADEMIC_BACKGROUND_NAME >", value, "academicBackgroundName");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_BACKGROUND_NAME >=", value, "academicBackgroundName");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundNameLessThan(String value) {
            addCriterion("ACADEMIC_BACKGROUND_NAME <", value, "academicBackgroundName");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundNameLessThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_BACKGROUND_NAME <=", value, "academicBackgroundName");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundNameLike(String value) {
            addCriterion("ACADEMIC_BACKGROUND_NAME like", value, "academicBackgroundName");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundNameNotLike(String value) {
            addCriterion("ACADEMIC_BACKGROUND_NAME not like", value, "academicBackgroundName");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundNameIn(List<String> values) {
            addCriterion("ACADEMIC_BACKGROUND_NAME in", values, "academicBackgroundName");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundNameNotIn(List<String> values) {
            addCriterion("ACADEMIC_BACKGROUND_NAME not in", values, "academicBackgroundName");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundNameBetween(String value1, String value2) {
            addCriterion("ACADEMIC_BACKGROUND_NAME between", value1, value2, "academicBackgroundName");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundNameNotBetween(String value1, String value2) {
            addCriterion("ACADEMIC_BACKGROUND_NAME not between", value1, value2, "academicBackgroundName");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundFileIsNull() {
            addCriterion("ACADEMIC_BACKGROUND_FILE is null");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundFileIsNotNull() {
            addCriterion("ACADEMIC_BACKGROUND_FILE is not null");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundFileEqualTo(String value) {
            addCriterion("ACADEMIC_BACKGROUND_FILE =", value, "academicBackgroundFile");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundFileNotEqualTo(String value) {
            addCriterion("ACADEMIC_BACKGROUND_FILE <>", value, "academicBackgroundFile");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundFileGreaterThan(String value) {
            addCriterion("ACADEMIC_BACKGROUND_FILE >", value, "academicBackgroundFile");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundFileGreaterThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_BACKGROUND_FILE >=", value, "academicBackgroundFile");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundFileLessThan(String value) {
            addCriterion("ACADEMIC_BACKGROUND_FILE <", value, "academicBackgroundFile");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundFileLessThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_BACKGROUND_FILE <=", value, "academicBackgroundFile");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundFileLike(String value) {
            addCriterion("ACADEMIC_BACKGROUND_FILE like", value, "academicBackgroundFile");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundFileNotLike(String value) {
            addCriterion("ACADEMIC_BACKGROUND_FILE not like", value, "academicBackgroundFile");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundFileIn(List<String> values) {
            addCriterion("ACADEMIC_BACKGROUND_FILE in", values, "academicBackgroundFile");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundFileNotIn(List<String> values) {
            addCriterion("ACADEMIC_BACKGROUND_FILE not in", values, "academicBackgroundFile");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundFileBetween(String value1, String value2) {
            addCriterion("ACADEMIC_BACKGROUND_FILE between", value1, value2, "academicBackgroundFile");
            return (Criteria) this;
        }

        public Criteria andAcademicBackgroundFileNotBetween(String value1, String value2) {
            addCriterion("ACADEMIC_BACKGROUND_FILE not between", value1, value2, "academicBackgroundFile");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeIdIsNull() {
            addCriterion("ACADEMIC_DEGREE_ID is null");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeIdIsNotNull() {
            addCriterion("ACADEMIC_DEGREE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeIdEqualTo(String value) {
            addCriterion("ACADEMIC_DEGREE_ID =", value, "academicDegreeId");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeIdNotEqualTo(String value) {
            addCriterion("ACADEMIC_DEGREE_ID <>", value, "academicDegreeId");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeIdGreaterThan(String value) {
            addCriterion("ACADEMIC_DEGREE_ID >", value, "academicDegreeId");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeIdGreaterThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_DEGREE_ID >=", value, "academicDegreeId");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeIdLessThan(String value) {
            addCriterion("ACADEMIC_DEGREE_ID <", value, "academicDegreeId");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeIdLessThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_DEGREE_ID <=", value, "academicDegreeId");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeIdLike(String value) {
            addCriterion("ACADEMIC_DEGREE_ID like", value, "academicDegreeId");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeIdNotLike(String value) {
            addCriterion("ACADEMIC_DEGREE_ID not like", value, "academicDegreeId");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeIdIn(List<String> values) {
            addCriterion("ACADEMIC_DEGREE_ID in", values, "academicDegreeId");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeIdNotIn(List<String> values) {
            addCriterion("ACADEMIC_DEGREE_ID not in", values, "academicDegreeId");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeIdBetween(String value1, String value2) {
            addCriterion("ACADEMIC_DEGREE_ID between", value1, value2, "academicDegreeId");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeIdNotBetween(String value1, String value2) {
            addCriterion("ACADEMIC_DEGREE_ID not between", value1, value2, "academicDegreeId");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeNameIsNull() {
            addCriterion("ACADEMIC_DEGREE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeNameIsNotNull() {
            addCriterion("ACADEMIC_DEGREE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeNameEqualTo(String value) {
            addCriterion("ACADEMIC_DEGREE_NAME =", value, "academicDegreeName");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeNameNotEqualTo(String value) {
            addCriterion("ACADEMIC_DEGREE_NAME <>", value, "academicDegreeName");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeNameGreaterThan(String value) {
            addCriterion("ACADEMIC_DEGREE_NAME >", value, "academicDegreeName");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_DEGREE_NAME >=", value, "academicDegreeName");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeNameLessThan(String value) {
            addCriterion("ACADEMIC_DEGREE_NAME <", value, "academicDegreeName");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeNameLessThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_DEGREE_NAME <=", value, "academicDegreeName");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeNameLike(String value) {
            addCriterion("ACADEMIC_DEGREE_NAME like", value, "academicDegreeName");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeNameNotLike(String value) {
            addCriterion("ACADEMIC_DEGREE_NAME not like", value, "academicDegreeName");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeNameIn(List<String> values) {
            addCriterion("ACADEMIC_DEGREE_NAME in", values, "academicDegreeName");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeNameNotIn(List<String> values) {
            addCriterion("ACADEMIC_DEGREE_NAME not in", values, "academicDegreeName");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeNameBetween(String value1, String value2) {
            addCriterion("ACADEMIC_DEGREE_NAME between", value1, value2, "academicDegreeName");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeNameNotBetween(String value1, String value2) {
            addCriterion("ACADEMIC_DEGREE_NAME not between", value1, value2, "academicDegreeName");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeFileIsNull() {
            addCriterion("ACADEMIC_DEGREE_FILE is null");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeFileIsNotNull() {
            addCriterion("ACADEMIC_DEGREE_FILE is not null");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeFileEqualTo(String value) {
            addCriterion("ACADEMIC_DEGREE_FILE =", value, "academicDegreeFile");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeFileNotEqualTo(String value) {
            addCriterion("ACADEMIC_DEGREE_FILE <>", value, "academicDegreeFile");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeFileGreaterThan(String value) {
            addCriterion("ACADEMIC_DEGREE_FILE >", value, "academicDegreeFile");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeFileGreaterThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_DEGREE_FILE >=", value, "academicDegreeFile");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeFileLessThan(String value) {
            addCriterion("ACADEMIC_DEGREE_FILE <", value, "academicDegreeFile");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeFileLessThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_DEGREE_FILE <=", value, "academicDegreeFile");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeFileLike(String value) {
            addCriterion("ACADEMIC_DEGREE_FILE like", value, "academicDegreeFile");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeFileNotLike(String value) {
            addCriterion("ACADEMIC_DEGREE_FILE not like", value, "academicDegreeFile");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeFileIn(List<String> values) {
            addCriterion("ACADEMIC_DEGREE_FILE in", values, "academicDegreeFile");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeFileNotIn(List<String> values) {
            addCriterion("ACADEMIC_DEGREE_FILE not in", values, "academicDegreeFile");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeFileBetween(String value1, String value2) {
            addCriterion("ACADEMIC_DEGREE_FILE between", value1, value2, "academicDegreeFile");
            return (Criteria) this;
        }

        public Criteria andAcademicDegreeFileNotBetween(String value1, String value2) {
            addCriterion("ACADEMIC_DEGREE_FILE not between", value1, value2, "academicDegreeFile");
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