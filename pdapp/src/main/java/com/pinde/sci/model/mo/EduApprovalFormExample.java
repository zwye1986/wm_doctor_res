package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class EduApprovalFormExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EduApprovalFormExample() {
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

        public Criteria andCourseNameIsNull() {
            addCriterion("COURSE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNotNull() {
            addCriterion("COURSE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCourseNameEqualTo(String value) {
            addCriterion("COURSE_NAME =", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotEqualTo(String value) {
            addCriterion("COURSE_NAME <>", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThan(String value) {
            addCriterion("COURSE_NAME >", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_NAME >=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThan(String value) {
            addCriterion("COURSE_NAME <", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThanOrEqualTo(String value) {
            addCriterion("COURSE_NAME <=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLike(String value) {
            addCriterion("COURSE_NAME like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotLike(String value) {
            addCriterion("COURSE_NAME not like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameIn(List<String> values) {
            addCriterion("COURSE_NAME in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotIn(List<String> values) {
            addCriterion("COURSE_NAME not in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameBetween(String value1, String value2) {
            addCriterion("COURSE_NAME between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotBetween(String value1, String value2) {
            addCriterion("COURSE_NAME not between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnIsNull() {
            addCriterion("COURSE_NAME_EN is null");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnIsNotNull() {
            addCriterion("COURSE_NAME_EN is not null");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnEqualTo(String value) {
            addCriterion("COURSE_NAME_EN =", value, "courseNameEn");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnNotEqualTo(String value) {
            addCriterion("COURSE_NAME_EN <>", value, "courseNameEn");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnGreaterThan(String value) {
            addCriterion("COURSE_NAME_EN >", value, "courseNameEn");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_NAME_EN >=", value, "courseNameEn");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnLessThan(String value) {
            addCriterion("COURSE_NAME_EN <", value, "courseNameEn");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnLessThanOrEqualTo(String value) {
            addCriterion("COURSE_NAME_EN <=", value, "courseNameEn");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnLike(String value) {
            addCriterion("COURSE_NAME_EN like", value, "courseNameEn");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnNotLike(String value) {
            addCriterion("COURSE_NAME_EN not like", value, "courseNameEn");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnIn(List<String> values) {
            addCriterion("COURSE_NAME_EN in", values, "courseNameEn");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnNotIn(List<String> values) {
            addCriterion("COURSE_NAME_EN not in", values, "courseNameEn");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnBetween(String value1, String value2) {
            addCriterion("COURSE_NAME_EN between", value1, value2, "courseNameEn");
            return (Criteria) this;
        }

        public Criteria andCourseNameEnNotBetween(String value1, String value2) {
            addCriterion("COURSE_NAME_EN not between", value1, value2, "courseNameEn");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescIsNull() {
            addCriterion("SCHOOL_YEAR_DESC is null");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescIsNotNull() {
            addCriterion("SCHOOL_YEAR_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescEqualTo(String value) {
            addCriterion("SCHOOL_YEAR_DESC =", value, "schoolYearDesc");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescNotEqualTo(String value) {
            addCriterion("SCHOOL_YEAR_DESC <>", value, "schoolYearDesc");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescGreaterThan(String value) {
            addCriterion("SCHOOL_YEAR_DESC >", value, "schoolYearDesc");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOL_YEAR_DESC >=", value, "schoolYearDesc");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescLessThan(String value) {
            addCriterion("SCHOOL_YEAR_DESC <", value, "schoolYearDesc");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescLessThanOrEqualTo(String value) {
            addCriterion("SCHOOL_YEAR_DESC <=", value, "schoolYearDesc");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescLike(String value) {
            addCriterion("SCHOOL_YEAR_DESC like", value, "schoolYearDesc");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescNotLike(String value) {
            addCriterion("SCHOOL_YEAR_DESC not like", value, "schoolYearDesc");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescIn(List<String> values) {
            addCriterion("SCHOOL_YEAR_DESC in", values, "schoolYearDesc");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescNotIn(List<String> values) {
            addCriterion("SCHOOL_YEAR_DESC not in", values, "schoolYearDesc");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescBetween(String value1, String value2) {
            addCriterion("SCHOOL_YEAR_DESC between", value1, value2, "schoolYearDesc");
            return (Criteria) this;
        }

        public Criteria andSchoolYearDescNotBetween(String value1, String value2) {
            addCriterion("SCHOOL_YEAR_DESC not between", value1, value2, "schoolYearDesc");
            return (Criteria) this;
        }

        public Criteria andGradeDescIsNull() {
            addCriterion("GRADE_DESC is null");
            return (Criteria) this;
        }

        public Criteria andGradeDescIsNotNull() {
            addCriterion("GRADE_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andGradeDescEqualTo(String value) {
            addCriterion("GRADE_DESC =", value, "gradeDesc");
            return (Criteria) this;
        }

        public Criteria andGradeDescNotEqualTo(String value) {
            addCriterion("GRADE_DESC <>", value, "gradeDesc");
            return (Criteria) this;
        }

        public Criteria andGradeDescGreaterThan(String value) {
            addCriterion("GRADE_DESC >", value, "gradeDesc");
            return (Criteria) this;
        }

        public Criteria andGradeDescGreaterThanOrEqualTo(String value) {
            addCriterion("GRADE_DESC >=", value, "gradeDesc");
            return (Criteria) this;
        }

        public Criteria andGradeDescLessThan(String value) {
            addCriterion("GRADE_DESC <", value, "gradeDesc");
            return (Criteria) this;
        }

        public Criteria andGradeDescLessThanOrEqualTo(String value) {
            addCriterion("GRADE_DESC <=", value, "gradeDesc");
            return (Criteria) this;
        }

        public Criteria andGradeDescLike(String value) {
            addCriterion("GRADE_DESC like", value, "gradeDesc");
            return (Criteria) this;
        }

        public Criteria andGradeDescNotLike(String value) {
            addCriterion("GRADE_DESC not like", value, "gradeDesc");
            return (Criteria) this;
        }

        public Criteria andGradeDescIn(List<String> values) {
            addCriterion("GRADE_DESC in", values, "gradeDesc");
            return (Criteria) this;
        }

        public Criteria andGradeDescNotIn(List<String> values) {
            addCriterion("GRADE_DESC not in", values, "gradeDesc");
            return (Criteria) this;
        }

        public Criteria andGradeDescBetween(String value1, String value2) {
            addCriterion("GRADE_DESC between", value1, value2, "gradeDesc");
            return (Criteria) this;
        }

        public Criteria andGradeDescNotBetween(String value1, String value2) {
            addCriterion("GRADE_DESC not between", value1, value2, "gradeDesc");
            return (Criteria) this;
        }

        public Criteria andTermDescIsNull() {
            addCriterion("TERM_DESC is null");
            return (Criteria) this;
        }

        public Criteria andTermDescIsNotNull() {
            addCriterion("TERM_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andTermDescEqualTo(String value) {
            addCriterion("TERM_DESC =", value, "termDesc");
            return (Criteria) this;
        }

        public Criteria andTermDescNotEqualTo(String value) {
            addCriterion("TERM_DESC <>", value, "termDesc");
            return (Criteria) this;
        }

        public Criteria andTermDescGreaterThan(String value) {
            addCriterion("TERM_DESC >", value, "termDesc");
            return (Criteria) this;
        }

        public Criteria andTermDescGreaterThanOrEqualTo(String value) {
            addCriterion("TERM_DESC >=", value, "termDesc");
            return (Criteria) this;
        }

        public Criteria andTermDescLessThan(String value) {
            addCriterion("TERM_DESC <", value, "termDesc");
            return (Criteria) this;
        }

        public Criteria andTermDescLessThanOrEqualTo(String value) {
            addCriterion("TERM_DESC <=", value, "termDesc");
            return (Criteria) this;
        }

        public Criteria andTermDescLike(String value) {
            addCriterion("TERM_DESC like", value, "termDesc");
            return (Criteria) this;
        }

        public Criteria andTermDescNotLike(String value) {
            addCriterion("TERM_DESC not like", value, "termDesc");
            return (Criteria) this;
        }

        public Criteria andTermDescIn(List<String> values) {
            addCriterion("TERM_DESC in", values, "termDesc");
            return (Criteria) this;
        }

        public Criteria andTermDescNotIn(List<String> values) {
            addCriterion("TERM_DESC not in", values, "termDesc");
            return (Criteria) this;
        }

        public Criteria andTermDescBetween(String value1, String value2) {
            addCriterion("TERM_DESC between", value1, value2, "termDesc");
            return (Criteria) this;
        }

        public Criteria andTermDescNotBetween(String value1, String value2) {
            addCriterion("TERM_DESC not between", value1, value2, "termDesc");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceIsNull() {
            addCriterion("TEACHING_PLACE is null");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceIsNotNull() {
            addCriterion("TEACHING_PLACE is not null");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceEqualTo(String value) {
            addCriterion("TEACHING_PLACE =", value, "teachingPlace");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceNotEqualTo(String value) {
            addCriterion("TEACHING_PLACE <>", value, "teachingPlace");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceGreaterThan(String value) {
            addCriterion("TEACHING_PLACE >", value, "teachingPlace");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHING_PLACE >=", value, "teachingPlace");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceLessThan(String value) {
            addCriterion("TEACHING_PLACE <", value, "teachingPlace");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceLessThanOrEqualTo(String value) {
            addCriterion("TEACHING_PLACE <=", value, "teachingPlace");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceLike(String value) {
            addCriterion("TEACHING_PLACE like", value, "teachingPlace");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceNotLike(String value) {
            addCriterion("TEACHING_PLACE not like", value, "teachingPlace");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceIn(List<String> values) {
            addCriterion("TEACHING_PLACE in", values, "teachingPlace");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceNotIn(List<String> values) {
            addCriterion("TEACHING_PLACE not in", values, "teachingPlace");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceBetween(String value1, String value2) {
            addCriterion("TEACHING_PLACE between", value1, value2, "teachingPlace");
            return (Criteria) this;
        }

        public Criteria andTeachingPlaceNotBetween(String value1, String value2) {
            addCriterion("TEACHING_PLACE not between", value1, value2, "teachingPlace");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialIsNull() {
            addCriterion("TEACHING_MATERIAL is null");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialIsNotNull() {
            addCriterion("TEACHING_MATERIAL is not null");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialEqualTo(String value) {
            addCriterion("TEACHING_MATERIAL =", value, "teachingMaterial");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialNotEqualTo(String value) {
            addCriterion("TEACHING_MATERIAL <>", value, "teachingMaterial");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialGreaterThan(String value) {
            addCriterion("TEACHING_MATERIAL >", value, "teachingMaterial");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHING_MATERIAL >=", value, "teachingMaterial");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialLessThan(String value) {
            addCriterion("TEACHING_MATERIAL <", value, "teachingMaterial");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialLessThanOrEqualTo(String value) {
            addCriterion("TEACHING_MATERIAL <=", value, "teachingMaterial");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialLike(String value) {
            addCriterion("TEACHING_MATERIAL like", value, "teachingMaterial");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialNotLike(String value) {
            addCriterion("TEACHING_MATERIAL not like", value, "teachingMaterial");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialIn(List<String> values) {
            addCriterion("TEACHING_MATERIAL in", values, "teachingMaterial");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialNotIn(List<String> values) {
            addCriterion("TEACHING_MATERIAL not in", values, "teachingMaterial");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialBetween(String value1, String value2) {
            addCriterion("TEACHING_MATERIAL between", value1, value2, "teachingMaterial");
            return (Criteria) this;
        }

        public Criteria andTeachingMaterialNotBetween(String value1, String value2) {
            addCriterion("TEACHING_MATERIAL not between", value1, value2, "teachingMaterial");
            return (Criteria) this;
        }

        public Criteria andAuthorNameIsNull() {
            addCriterion("AUTHOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAuthorNameIsNotNull() {
            addCriterion("AUTHOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorNameEqualTo(String value) {
            addCriterion("AUTHOR_NAME =", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotEqualTo(String value) {
            addCriterion("AUTHOR_NAME <>", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameGreaterThan(String value) {
            addCriterion("AUTHOR_NAME >", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameGreaterThanOrEqualTo(String value) {
            addCriterion("AUTHOR_NAME >=", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameLessThan(String value) {
            addCriterion("AUTHOR_NAME <", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameLessThanOrEqualTo(String value) {
            addCriterion("AUTHOR_NAME <=", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameLike(String value) {
            addCriterion("AUTHOR_NAME like", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotLike(String value) {
            addCriterion("AUTHOR_NAME not like", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameIn(List<String> values) {
            addCriterion("AUTHOR_NAME in", values, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotIn(List<String> values) {
            addCriterion("AUTHOR_NAME not in", values, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameBetween(String value1, String value2) {
            addCriterion("AUTHOR_NAME between", value1, value2, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotBetween(String value1, String value2) {
            addCriterion("AUTHOR_NAME not between", value1, value2, "authorName");
            return (Criteria) this;
        }

        public Criteria andLayoutNumIsNull() {
            addCriterion("LAYOUT_NUM is null");
            return (Criteria) this;
        }

        public Criteria andLayoutNumIsNotNull() {
            addCriterion("LAYOUT_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andLayoutNumEqualTo(String value) {
            addCriterion("LAYOUT_NUM =", value, "layoutNum");
            return (Criteria) this;
        }

        public Criteria andLayoutNumNotEqualTo(String value) {
            addCriterion("LAYOUT_NUM <>", value, "layoutNum");
            return (Criteria) this;
        }

        public Criteria andLayoutNumGreaterThan(String value) {
            addCriterion("LAYOUT_NUM >", value, "layoutNum");
            return (Criteria) this;
        }

        public Criteria andLayoutNumGreaterThanOrEqualTo(String value) {
            addCriterion("LAYOUT_NUM >=", value, "layoutNum");
            return (Criteria) this;
        }

        public Criteria andLayoutNumLessThan(String value) {
            addCriterion("LAYOUT_NUM <", value, "layoutNum");
            return (Criteria) this;
        }

        public Criteria andLayoutNumLessThanOrEqualTo(String value) {
            addCriterion("LAYOUT_NUM <=", value, "layoutNum");
            return (Criteria) this;
        }

        public Criteria andLayoutNumLike(String value) {
            addCriterion("LAYOUT_NUM like", value, "layoutNum");
            return (Criteria) this;
        }

        public Criteria andLayoutNumNotLike(String value) {
            addCriterion("LAYOUT_NUM not like", value, "layoutNum");
            return (Criteria) this;
        }

        public Criteria andLayoutNumIn(List<String> values) {
            addCriterion("LAYOUT_NUM in", values, "layoutNum");
            return (Criteria) this;
        }

        public Criteria andLayoutNumNotIn(List<String> values) {
            addCriterion("LAYOUT_NUM not in", values, "layoutNum");
            return (Criteria) this;
        }

        public Criteria andLayoutNumBetween(String value1, String value2) {
            addCriterion("LAYOUT_NUM between", value1, value2, "layoutNum");
            return (Criteria) this;
        }

        public Criteria andLayoutNumNotBetween(String value1, String value2) {
            addCriterion("LAYOUT_NUM not between", value1, value2, "layoutNum");
            return (Criteria) this;
        }

        public Criteria andPressNameIsNull() {
            addCriterion("PRESS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPressNameIsNotNull() {
            addCriterion("PRESS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPressNameEqualTo(String value) {
            addCriterion("PRESS_NAME =", value, "pressName");
            return (Criteria) this;
        }

        public Criteria andPressNameNotEqualTo(String value) {
            addCriterion("PRESS_NAME <>", value, "pressName");
            return (Criteria) this;
        }

        public Criteria andPressNameGreaterThan(String value) {
            addCriterion("PRESS_NAME >", value, "pressName");
            return (Criteria) this;
        }

        public Criteria andPressNameGreaterThanOrEqualTo(String value) {
            addCriterion("PRESS_NAME >=", value, "pressName");
            return (Criteria) this;
        }

        public Criteria andPressNameLessThan(String value) {
            addCriterion("PRESS_NAME <", value, "pressName");
            return (Criteria) this;
        }

        public Criteria andPressNameLessThanOrEqualTo(String value) {
            addCriterion("PRESS_NAME <=", value, "pressName");
            return (Criteria) this;
        }

        public Criteria andPressNameLike(String value) {
            addCriterion("PRESS_NAME like", value, "pressName");
            return (Criteria) this;
        }

        public Criteria andPressNameNotLike(String value) {
            addCriterion("PRESS_NAME not like", value, "pressName");
            return (Criteria) this;
        }

        public Criteria andPressNameIn(List<String> values) {
            addCriterion("PRESS_NAME in", values, "pressName");
            return (Criteria) this;
        }

        public Criteria andPressNameNotIn(List<String> values) {
            addCriterion("PRESS_NAME not in", values, "pressName");
            return (Criteria) this;
        }

        public Criteria andPressNameBetween(String value1, String value2) {
            addCriterion("PRESS_NAME between", value1, value2, "pressName");
            return (Criteria) this;
        }

        public Criteria andPressNameNotBetween(String value1, String value2) {
            addCriterion("PRESS_NAME not between", value1, value2, "pressName");
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