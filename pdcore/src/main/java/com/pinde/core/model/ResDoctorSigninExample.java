package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResDoctorSigninExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResDoctorSigninExample() {
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

        public Criteria andReportFlowIsNull() {
            addCriterion("REPORT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andReportFlowIsNotNull() {
            addCriterion("REPORT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andReportFlowEqualTo(String value) {
            addCriterion("REPORT_FLOW =", value, "reportFlow");
            return (Criteria) this;
        }

        public Criteria andReportFlowNotEqualTo(String value) {
            addCriterion("REPORT_FLOW <>", value, "reportFlow");
            return (Criteria) this;
        }

        public Criteria andReportFlowGreaterThan(String value) {
            addCriterion("REPORT_FLOW >", value, "reportFlow");
            return (Criteria) this;
        }

        public Criteria andReportFlowGreaterThanOrEqualTo(String value) {
            addCriterion("REPORT_FLOW >=", value, "reportFlow");
            return (Criteria) this;
        }

        public Criteria andReportFlowLessThan(String value) {
            addCriterion("REPORT_FLOW <", value, "reportFlow");
            return (Criteria) this;
        }

        public Criteria andReportFlowLessThanOrEqualTo(String value) {
            addCriterion("REPORT_FLOW <=", value, "reportFlow");
            return (Criteria) this;
        }

        public Criteria andReportFlowLike(String value) {
            addCriterion("REPORT_FLOW like", value, "reportFlow");
            return (Criteria) this;
        }

        public Criteria andReportFlowNotLike(String value) {
            addCriterion("REPORT_FLOW not like", value, "reportFlow");
            return (Criteria) this;
        }

        public Criteria andReportFlowIn(List<String> values) {
            addCriterion("REPORT_FLOW in", values, "reportFlow");
            return (Criteria) this;
        }

        public Criteria andReportFlowNotIn(List<String> values) {
            addCriterion("REPORT_FLOW not in", values, "reportFlow");
            return (Criteria) this;
        }

        public Criteria andReportFlowBetween(String value1, String value2) {
            addCriterion("REPORT_FLOW between", value1, value2, "reportFlow");
            return (Criteria) this;
        }

        public Criteria andReportFlowNotBetween(String value1, String value2) {
            addCriterion("REPORT_FLOW not between", value1, value2, "reportFlow");
            return (Criteria) this;
        }

        public Criteria andCodeInfoIsNull() {
            addCriterion("CODE_INFO is null");
            return (Criteria) this;
        }

        public Criteria andCodeInfoIsNotNull() {
            addCriterion("CODE_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andCodeInfoEqualTo(String value) {
            addCriterion("CODE_INFO =", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoNotEqualTo(String value) {
            addCriterion("CODE_INFO <>", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoGreaterThan(String value) {
            addCriterion("CODE_INFO >", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoGreaterThanOrEqualTo(String value) {
            addCriterion("CODE_INFO >=", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoLessThan(String value) {
            addCriterion("CODE_INFO <", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoLessThanOrEqualTo(String value) {
            addCriterion("CODE_INFO <=", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoLike(String value) {
            addCriterion("CODE_INFO like", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoNotLike(String value) {
            addCriterion("CODE_INFO not like", value, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoIn(List<String> values) {
            addCriterion("CODE_INFO in", values, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoNotIn(List<String> values) {
            addCriterion("CODE_INFO not in", values, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoBetween(String value1, String value2) {
            addCriterion("CODE_INFO between", value1, value2, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andCodeInfoNotBetween(String value1, String value2) {
            addCriterion("CODE_INFO not between", value1, value2, "codeInfo");
            return (Criteria) this;
        }

        public Criteria andSigninTimeIsNull() {
            addCriterion("SIGNIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSigninTimeIsNotNull() {
            addCriterion("SIGNIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSigninTimeEqualTo(String value) {
            addCriterion("SIGNIN_TIME =", value, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeNotEqualTo(String value) {
            addCriterion("SIGNIN_TIME <>", value, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeGreaterThan(String value) {
            addCriterion("SIGNIN_TIME >", value, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SIGNIN_TIME >=", value, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeLessThan(String value) {
            addCriterion("SIGNIN_TIME <", value, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeLessThanOrEqualTo(String value) {
            addCriterion("SIGNIN_TIME <=", value, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeLike(String value) {
            addCriterion("SIGNIN_TIME like", value, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeNotLike(String value) {
            addCriterion("SIGNIN_TIME not like", value, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeIn(List<String> values) {
            addCriterion("SIGNIN_TIME in", values, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeNotIn(List<String> values) {
            addCriterion("SIGNIN_TIME not in", values, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeBetween(String value1, String value2) {
            addCriterion("SIGNIN_TIME between", value1, value2, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninTimeNotBetween(String value1, String value2) {
            addCriterion("SIGNIN_TIME not between", value1, value2, "signinTime");
            return (Criteria) this;
        }

        public Criteria andSigninDateIsNull() {
            addCriterion("SIGNIN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSigninDateIsNotNull() {
            addCriterion("SIGNIN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSigninDateEqualTo(String value) {
            addCriterion("SIGNIN_DATE =", value, "signinDate");
            return (Criteria) this;
        }

        public Criteria andSigninDateNotEqualTo(String value) {
            addCriterion("SIGNIN_DATE <>", value, "signinDate");
            return (Criteria) this;
        }

        public Criteria andSigninDateGreaterThan(String value) {
            addCriterion("SIGNIN_DATE >", value, "signinDate");
            return (Criteria) this;
        }

        public Criteria andSigninDateGreaterThanOrEqualTo(String value) {
            addCriterion("SIGNIN_DATE >=", value, "signinDate");
            return (Criteria) this;
        }

        public Criteria andSigninDateLessThan(String value) {
            addCriterion("SIGNIN_DATE <", value, "signinDate");
            return (Criteria) this;
        }

        public Criteria andSigninDateLessThanOrEqualTo(String value) {
            addCriterion("SIGNIN_DATE <=", value, "signinDate");
            return (Criteria) this;
        }

        public Criteria andSigninDateLike(String value) {
            addCriterion("SIGNIN_DATE like", value, "signinDate");
            return (Criteria) this;
        }

        public Criteria andSigninDateNotLike(String value) {
            addCriterion("SIGNIN_DATE not like", value, "signinDate");
            return (Criteria) this;
        }

        public Criteria andSigninDateIn(List<String> values) {
            addCriterion("SIGNIN_DATE in", values, "signinDate");
            return (Criteria) this;
        }

        public Criteria andSigninDateNotIn(List<String> values) {
            addCriterion("SIGNIN_DATE not in", values, "signinDate");
            return (Criteria) this;
        }

        public Criteria andSigninDateBetween(String value1, String value2) {
            addCriterion("SIGNIN_DATE between", value1, value2, "signinDate");
            return (Criteria) this;
        }

        public Criteria andSigninDateNotBetween(String value1, String value2) {
            addCriterion("SIGNIN_DATE not between", value1, value2, "signinDate");
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

        public Criteria andTeacherUserFlowIsNull() {
            addCriterion("TEACHER_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowIsNotNull() {
            addCriterion("TEACHER_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowEqualTo(String value) {
            addCriterion("TEACHER_USER_FLOW =", value, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowNotEqualTo(String value) {
            addCriterion("TEACHER_USER_FLOW <>", value, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowGreaterThan(String value) {
            addCriterion("TEACHER_USER_FLOW >", value, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_USER_FLOW >=", value, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowLessThan(String value) {
            addCriterion("TEACHER_USER_FLOW <", value, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_USER_FLOW <=", value, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowLike(String value) {
            addCriterion("TEACHER_USER_FLOW like", value, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowNotLike(String value) {
            addCriterion("TEACHER_USER_FLOW not like", value, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowIn(List<String> values) {
            addCriterion("TEACHER_USER_FLOW in", values, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowNotIn(List<String> values) {
            addCriterion("TEACHER_USER_FLOW not in", values, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowBetween(String value1, String value2) {
            addCriterion("TEACHER_USER_FLOW between", value1, value2, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowNotBetween(String value1, String value2) {
            addCriterion("TEACHER_USER_FLOW not between", value1, value2, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowIsNull() {
            addCriterion("RESULT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andResultFlowIsNotNull() {
            addCriterion("RESULT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andResultFlowEqualTo(String value) {
            addCriterion("RESULT_FLOW =", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowNotEqualTo(String value) {
            addCriterion("RESULT_FLOW <>", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowGreaterThan(String value) {
            addCriterion("RESULT_FLOW >", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RESULT_FLOW >=", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowLessThan(String value) {
            addCriterion("RESULT_FLOW <", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowLessThanOrEqualTo(String value) {
            addCriterion("RESULT_FLOW <=", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowLike(String value) {
            addCriterion("RESULT_FLOW like", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowNotLike(String value) {
            addCriterion("RESULT_FLOW not like", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowIn(List<String> values) {
            addCriterion("RESULT_FLOW in", values, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowNotIn(List<String> values) {
            addCriterion("RESULT_FLOW not in", values, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowBetween(String value1, String value2) {
            addCriterion("RESULT_FLOW between", value1, value2, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowNotBetween(String value1, String value2) {
            addCriterion("RESULT_FLOW not between", value1, value2, "resultFlow");
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