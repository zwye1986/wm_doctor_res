package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class EduApprovalSubExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EduApprovalSubExample() {
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

        public Criteria andCourseFlowIsNull() {
            addCriterion("COURSE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andCourseFlowIsNotNull() {
            addCriterion("COURSE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andCourseFlowEqualTo(String value) {
            addCriterion("COURSE_FLOW =", value, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowNotEqualTo(String value) {
            addCriterion("COURSE_FLOW <>", value, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowGreaterThan(String value) {
            addCriterion("COURSE_FLOW >", value, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_FLOW >=", value, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowLessThan(String value) {
            addCriterion("COURSE_FLOW <", value, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowLessThanOrEqualTo(String value) {
            addCriterion("COURSE_FLOW <=", value, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowLike(String value) {
            addCriterion("COURSE_FLOW like", value, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowNotLike(String value) {
            addCriterion("COURSE_FLOW not like", value, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowIn(List<String> values) {
            addCriterion("COURSE_FLOW in", values, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowNotIn(List<String> values) {
            addCriterion("COURSE_FLOW not in", values, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowBetween(String value1, String value2) {
            addCriterion("COURSE_FLOW between", value1, value2, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowNotBetween(String value1, String value2) {
            addCriterion("COURSE_FLOW not between", value1, value2, "courseFlow");
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

        public Criteria andTeacherPostIsNull() {
            addCriterion("TEACHER_POST is null");
            return (Criteria) this;
        }

        public Criteria andTeacherPostIsNotNull() {
            addCriterion("TEACHER_POST is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherPostEqualTo(String value) {
            addCriterion("TEACHER_POST =", value, "teacherPost");
            return (Criteria) this;
        }

        public Criteria andTeacherPostNotEqualTo(String value) {
            addCriterion("TEACHER_POST <>", value, "teacherPost");
            return (Criteria) this;
        }

        public Criteria andTeacherPostGreaterThan(String value) {
            addCriterion("TEACHER_POST >", value, "teacherPost");
            return (Criteria) this;
        }

        public Criteria andTeacherPostGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_POST >=", value, "teacherPost");
            return (Criteria) this;
        }

        public Criteria andTeacherPostLessThan(String value) {
            addCriterion("TEACHER_POST <", value, "teacherPost");
            return (Criteria) this;
        }

        public Criteria andTeacherPostLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_POST <=", value, "teacherPost");
            return (Criteria) this;
        }

        public Criteria andTeacherPostLike(String value) {
            addCriterion("TEACHER_POST like", value, "teacherPost");
            return (Criteria) this;
        }

        public Criteria andTeacherPostNotLike(String value) {
            addCriterion("TEACHER_POST not like", value, "teacherPost");
            return (Criteria) this;
        }

        public Criteria andTeacherPostIn(List<String> values) {
            addCriterion("TEACHER_POST in", values, "teacherPost");
            return (Criteria) this;
        }

        public Criteria andTeacherPostNotIn(List<String> values) {
            addCriterion("TEACHER_POST not in", values, "teacherPost");
            return (Criteria) this;
        }

        public Criteria andTeacherPostBetween(String value1, String value2) {
            addCriterion("TEACHER_POST between", value1, value2, "teacherPost");
            return (Criteria) this;
        }

        public Criteria andTeacherPostNotBetween(String value1, String value2) {
            addCriterion("TEACHER_POST not between", value1, value2, "teacherPost");
            return (Criteria) this;
        }

        public Criteria andContentDescIsNull() {
            addCriterion("CONTENT_DESC is null");
            return (Criteria) this;
        }

        public Criteria andContentDescIsNotNull() {
            addCriterion("CONTENT_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andContentDescEqualTo(String value) {
            addCriterion("CONTENT_DESC =", value, "contentDesc");
            return (Criteria) this;
        }

        public Criteria andContentDescNotEqualTo(String value) {
            addCriterion("CONTENT_DESC <>", value, "contentDesc");
            return (Criteria) this;
        }

        public Criteria andContentDescGreaterThan(String value) {
            addCriterion("CONTENT_DESC >", value, "contentDesc");
            return (Criteria) this;
        }

        public Criteria andContentDescGreaterThanOrEqualTo(String value) {
            addCriterion("CONTENT_DESC >=", value, "contentDesc");
            return (Criteria) this;
        }

        public Criteria andContentDescLessThan(String value) {
            addCriterion("CONTENT_DESC <", value, "contentDesc");
            return (Criteria) this;
        }

        public Criteria andContentDescLessThanOrEqualTo(String value) {
            addCriterion("CONTENT_DESC <=", value, "contentDesc");
            return (Criteria) this;
        }

        public Criteria andContentDescLike(String value) {
            addCriterion("CONTENT_DESC like", value, "contentDesc");
            return (Criteria) this;
        }

        public Criteria andContentDescNotLike(String value) {
            addCriterion("CONTENT_DESC not like", value, "contentDesc");
            return (Criteria) this;
        }

        public Criteria andContentDescIn(List<String> values) {
            addCriterion("CONTENT_DESC in", values, "contentDesc");
            return (Criteria) this;
        }

        public Criteria andContentDescNotIn(List<String> values) {
            addCriterion("CONTENT_DESC not in", values, "contentDesc");
            return (Criteria) this;
        }

        public Criteria andContentDescBetween(String value1, String value2) {
            addCriterion("CONTENT_DESC between", value1, value2, "contentDesc");
            return (Criteria) this;
        }

        public Criteria andContentDescNotBetween(String value1, String value2) {
            addCriterion("CONTENT_DESC not between", value1, value2, "contentDesc");
            return (Criteria) this;
        }

        public Criteria andSchoolHoursIsNull() {
            addCriterion("SCHOOL_HOURS is null");
            return (Criteria) this;
        }

        public Criteria andSchoolHoursIsNotNull() {
            addCriterion("SCHOOL_HOURS is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolHoursEqualTo(String value) {
            addCriterion("SCHOOL_HOURS =", value, "schoolHours");
            return (Criteria) this;
        }

        public Criteria andSchoolHoursNotEqualTo(String value) {
            addCriterion("SCHOOL_HOURS <>", value, "schoolHours");
            return (Criteria) this;
        }

        public Criteria andSchoolHoursGreaterThan(String value) {
            addCriterion("SCHOOL_HOURS >", value, "schoolHours");
            return (Criteria) this;
        }

        public Criteria andSchoolHoursGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOL_HOURS >=", value, "schoolHours");
            return (Criteria) this;
        }

        public Criteria andSchoolHoursLessThan(String value) {
            addCriterion("SCHOOL_HOURS <", value, "schoolHours");
            return (Criteria) this;
        }

        public Criteria andSchoolHoursLessThanOrEqualTo(String value) {
            addCriterion("SCHOOL_HOURS <=", value, "schoolHours");
            return (Criteria) this;
        }

        public Criteria andSchoolHoursLike(String value) {
            addCriterion("SCHOOL_HOURS like", value, "schoolHours");
            return (Criteria) this;
        }

        public Criteria andSchoolHoursNotLike(String value) {
            addCriterion("SCHOOL_HOURS not like", value, "schoolHours");
            return (Criteria) this;
        }

        public Criteria andSchoolHoursIn(List<String> values) {
            addCriterion("SCHOOL_HOURS in", values, "schoolHours");
            return (Criteria) this;
        }

        public Criteria andSchoolHoursNotIn(List<String> values) {
            addCriterion("SCHOOL_HOURS not in", values, "schoolHours");
            return (Criteria) this;
        }

        public Criteria andSchoolHoursBetween(String value1, String value2) {
            addCriterion("SCHOOL_HOURS between", value1, value2, "schoolHours");
            return (Criteria) this;
        }

        public Criteria andSchoolHoursNotBetween(String value1, String value2) {
            addCriterion("SCHOOL_HOURS not between", value1, value2, "schoolHours");
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

        public Criteria andIsTestTeacherIsNull() {
            addCriterion("IS_TEST_TEACHER is null");
            return (Criteria) this;
        }

        public Criteria andIsTestTeacherIsNotNull() {
            addCriterion("IS_TEST_TEACHER is not null");
            return (Criteria) this;
        }

        public Criteria andIsTestTeacherEqualTo(String value) {
            addCriterion("IS_TEST_TEACHER =", value, "isTestTeacher");
            return (Criteria) this;
        }

        public Criteria andIsTestTeacherNotEqualTo(String value) {
            addCriterion("IS_TEST_TEACHER <>", value, "isTestTeacher");
            return (Criteria) this;
        }

        public Criteria andIsTestTeacherGreaterThan(String value) {
            addCriterion("IS_TEST_TEACHER >", value, "isTestTeacher");
            return (Criteria) this;
        }

        public Criteria andIsTestTeacherGreaterThanOrEqualTo(String value) {
            addCriterion("IS_TEST_TEACHER >=", value, "isTestTeacher");
            return (Criteria) this;
        }

        public Criteria andIsTestTeacherLessThan(String value) {
            addCriterion("IS_TEST_TEACHER <", value, "isTestTeacher");
            return (Criteria) this;
        }

        public Criteria andIsTestTeacherLessThanOrEqualTo(String value) {
            addCriterion("IS_TEST_TEACHER <=", value, "isTestTeacher");
            return (Criteria) this;
        }

        public Criteria andIsTestTeacherLike(String value) {
            addCriterion("IS_TEST_TEACHER like", value, "isTestTeacher");
            return (Criteria) this;
        }

        public Criteria andIsTestTeacherNotLike(String value) {
            addCriterion("IS_TEST_TEACHER not like", value, "isTestTeacher");
            return (Criteria) this;
        }

        public Criteria andIsTestTeacherIn(List<String> values) {
            addCriterion("IS_TEST_TEACHER in", values, "isTestTeacher");
            return (Criteria) this;
        }

        public Criteria andIsTestTeacherNotIn(List<String> values) {
            addCriterion("IS_TEST_TEACHER not in", values, "isTestTeacher");
            return (Criteria) this;
        }

        public Criteria andIsTestTeacherBetween(String value1, String value2) {
            addCriterion("IS_TEST_TEACHER between", value1, value2, "isTestTeacher");
            return (Criteria) this;
        }

        public Criteria andIsTestTeacherNotBetween(String value1, String value2) {
            addCriterion("IS_TEST_TEACHER not between", value1, value2, "isTestTeacher");
            return (Criteria) this;
        }

        public Criteria andIsApprovalTeacherIsNull() {
            addCriterion("IS_APPROVAL_TEACHER is null");
            return (Criteria) this;
        }

        public Criteria andIsApprovalTeacherIsNotNull() {
            addCriterion("IS_APPROVAL_TEACHER is not null");
            return (Criteria) this;
        }

        public Criteria andIsApprovalTeacherEqualTo(String value) {
            addCriterion("IS_APPROVAL_TEACHER =", value, "isApprovalTeacher");
            return (Criteria) this;
        }

        public Criteria andIsApprovalTeacherNotEqualTo(String value) {
            addCriterion("IS_APPROVAL_TEACHER <>", value, "isApprovalTeacher");
            return (Criteria) this;
        }

        public Criteria andIsApprovalTeacherGreaterThan(String value) {
            addCriterion("IS_APPROVAL_TEACHER >", value, "isApprovalTeacher");
            return (Criteria) this;
        }

        public Criteria andIsApprovalTeacherGreaterThanOrEqualTo(String value) {
            addCriterion("IS_APPROVAL_TEACHER >=", value, "isApprovalTeacher");
            return (Criteria) this;
        }

        public Criteria andIsApprovalTeacherLessThan(String value) {
            addCriterion("IS_APPROVAL_TEACHER <", value, "isApprovalTeacher");
            return (Criteria) this;
        }

        public Criteria andIsApprovalTeacherLessThanOrEqualTo(String value) {
            addCriterion("IS_APPROVAL_TEACHER <=", value, "isApprovalTeacher");
            return (Criteria) this;
        }

        public Criteria andIsApprovalTeacherLike(String value) {
            addCriterion("IS_APPROVAL_TEACHER like", value, "isApprovalTeacher");
            return (Criteria) this;
        }

        public Criteria andIsApprovalTeacherNotLike(String value) {
            addCriterion("IS_APPROVAL_TEACHER not like", value, "isApprovalTeacher");
            return (Criteria) this;
        }

        public Criteria andIsApprovalTeacherIn(List<String> values) {
            addCriterion("IS_APPROVAL_TEACHER in", values, "isApprovalTeacher");
            return (Criteria) this;
        }

        public Criteria andIsApprovalTeacherNotIn(List<String> values) {
            addCriterion("IS_APPROVAL_TEACHER not in", values, "isApprovalTeacher");
            return (Criteria) this;
        }

        public Criteria andIsApprovalTeacherBetween(String value1, String value2) {
            addCriterion("IS_APPROVAL_TEACHER between", value1, value2, "isApprovalTeacher");
            return (Criteria) this;
        }

        public Criteria andIsApprovalTeacherNotBetween(String value1, String value2) {
            addCriterion("IS_APPROVAL_TEACHER not between", value1, value2, "isApprovalTeacher");
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