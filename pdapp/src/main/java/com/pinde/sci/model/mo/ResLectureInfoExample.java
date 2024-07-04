package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ResLectureInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResLectureInfoExample() {
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

        public Criteria andLectureFlowIsNull() {
            addCriterion("LECTURE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andLectureFlowIsNotNull() {
            addCriterion("LECTURE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andLectureFlowEqualTo(String value) {
            addCriterion("LECTURE_FLOW =", value, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowNotEqualTo(String value) {
            addCriterion("LECTURE_FLOW <>", value, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowGreaterThan(String value) {
            addCriterion("LECTURE_FLOW >", value, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowGreaterThanOrEqualTo(String value) {
            addCriterion("LECTURE_FLOW >=", value, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowLessThan(String value) {
            addCriterion("LECTURE_FLOW <", value, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowLessThanOrEqualTo(String value) {
            addCriterion("LECTURE_FLOW <=", value, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowLike(String value) {
            addCriterion("LECTURE_FLOW like", value, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowNotLike(String value) {
            addCriterion("LECTURE_FLOW not like", value, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowIn(List<String> values) {
            addCriterion("LECTURE_FLOW in", values, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowNotIn(List<String> values) {
            addCriterion("LECTURE_FLOW not in", values, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowBetween(String value1, String value2) {
            addCriterion("LECTURE_FLOW between", value1, value2, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureFlowNotBetween(String value1, String value2) {
            addCriterion("LECTURE_FLOW not between", value1, value2, "lectureFlow");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherFlowIsNull() {
            addCriterion("LECTURE_TEACHER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherFlowIsNotNull() {
            addCriterion("LECTURE_TEACHER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherFlowEqualTo(String value) {
            addCriterion("LECTURE_TEACHER_FLOW =", value, "lectureTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherFlowNotEqualTo(String value) {
            addCriterion("LECTURE_TEACHER_FLOW <>", value, "lectureTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherFlowGreaterThan(String value) {
            addCriterion("LECTURE_TEACHER_FLOW >", value, "lectureTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherFlowGreaterThanOrEqualTo(String value) {
            addCriterion("LECTURE_TEACHER_FLOW >=", value, "lectureTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherFlowLessThan(String value) {
            addCriterion("LECTURE_TEACHER_FLOW <", value, "lectureTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherFlowLessThanOrEqualTo(String value) {
            addCriterion("LECTURE_TEACHER_FLOW <=", value, "lectureTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherFlowLike(String value) {
            addCriterion("LECTURE_TEACHER_FLOW like", value, "lectureTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherFlowNotLike(String value) {
            addCriterion("LECTURE_TEACHER_FLOW not like", value, "lectureTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherFlowIn(List<String> values) {
            addCriterion("LECTURE_TEACHER_FLOW in", values, "lectureTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherFlowNotIn(List<String> values) {
            addCriterion("LECTURE_TEACHER_FLOW not in", values, "lectureTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherFlowBetween(String value1, String value2) {
            addCriterion("LECTURE_TEACHER_FLOW between", value1, value2, "lectureTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherFlowNotBetween(String value1, String value2) {
            addCriterion("LECTURE_TEACHER_FLOW not between", value1, value2, "lectureTeacherFlow");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherNameIsNull() {
            addCriterion("LECTURE_TEACHER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherNameIsNotNull() {
            addCriterion("LECTURE_TEACHER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherNameEqualTo(String value) {
            addCriterion("LECTURE_TEACHER_NAME =", value, "lectureTeacherName");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherNameNotEqualTo(String value) {
            addCriterion("LECTURE_TEACHER_NAME <>", value, "lectureTeacherName");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherNameGreaterThan(String value) {
            addCriterion("LECTURE_TEACHER_NAME >", value, "lectureTeacherName");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherNameGreaterThanOrEqualTo(String value) {
            addCriterion("LECTURE_TEACHER_NAME >=", value, "lectureTeacherName");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherNameLessThan(String value) {
            addCriterion("LECTURE_TEACHER_NAME <", value, "lectureTeacherName");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherNameLessThanOrEqualTo(String value) {
            addCriterion("LECTURE_TEACHER_NAME <=", value, "lectureTeacherName");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherNameLike(String value) {
            addCriterion("LECTURE_TEACHER_NAME like", value, "lectureTeacherName");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherNameNotLike(String value) {
            addCriterion("LECTURE_TEACHER_NAME not like", value, "lectureTeacherName");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherNameIn(List<String> values) {
            addCriterion("LECTURE_TEACHER_NAME in", values, "lectureTeacherName");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherNameNotIn(List<String> values) {
            addCriterion("LECTURE_TEACHER_NAME not in", values, "lectureTeacherName");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherNameBetween(String value1, String value2) {
            addCriterion("LECTURE_TEACHER_NAME between", value1, value2, "lectureTeacherName");
            return (Criteria) this;
        }

        public Criteria andLectureTeacherNameNotBetween(String value1, String value2) {
            addCriterion("LECTURE_TEACHER_NAME not between", value1, value2, "lectureTeacherName");
            return (Criteria) this;
        }

        public Criteria andOrgFlowIsNull() {
            addCriterion("ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andOrgFlowIsNotNull() {
            addCriterion("ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andOrgFlowEqualTo(String value) {
            addCriterion("ORG_FLOW =", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotEqualTo(String value) {
            addCriterion("ORG_FLOW <>", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGreaterThan(String value) {
            addCriterion("ORG_FLOW >", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_FLOW >=", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowLessThan(String value) {
            addCriterion("ORG_FLOW <", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("ORG_FLOW <=", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowLike(String value) {
            addCriterion("ORG_FLOW like", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotLike(String value) {
            addCriterion("ORG_FLOW not like", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowIn(List<String> values) {
            addCriterion("ORG_FLOW in", values, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotIn(List<String> values) {
            addCriterion("ORG_FLOW not in", values, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowBetween(String value1, String value2) {
            addCriterion("ORG_FLOW between", value1, value2, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotBetween(String value1, String value2) {
            addCriterion("ORG_FLOW not between", value1, value2, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNull() {
            addCriterion("ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNotNull() {
            addCriterion("ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEqualTo(String value) {
            addCriterion("ORG_NAME =", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotEqualTo(String value) {
            addCriterion("ORG_NAME <>", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThan(String value) {
            addCriterion("ORG_NAME >", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_NAME >=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThan(String value) {
            addCriterion("ORG_NAME <", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_NAME <=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLike(String value) {
            addCriterion("ORG_NAME like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotLike(String value) {
            addCriterion("ORG_NAME not like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIn(List<String> values) {
            addCriterion("ORG_NAME in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotIn(List<String> values) {
            addCriterion("ORG_NAME not in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameBetween(String value1, String value2) {
            addCriterion("ORG_NAME between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotBetween(String value1, String value2) {
            addCriterion("ORG_NAME not between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andLectureTrainDateIsNull() {
            addCriterion("LECTURE_TRAIN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andLectureTrainDateIsNotNull() {
            addCriterion("LECTURE_TRAIN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andLectureTrainDateEqualTo(String value) {
            addCriterion("LECTURE_TRAIN_DATE =", value, "lectureTrainDate");
            return (Criteria) this;
        }

        public Criteria andLectureTrainDateNotEqualTo(String value) {
            addCriterion("LECTURE_TRAIN_DATE <>", value, "lectureTrainDate");
            return (Criteria) this;
        }

        public Criteria andLectureTrainDateGreaterThan(String value) {
            addCriterion("LECTURE_TRAIN_DATE >", value, "lectureTrainDate");
            return (Criteria) this;
        }

        public Criteria andLectureTrainDateGreaterThanOrEqualTo(String value) {
            addCriterion("LECTURE_TRAIN_DATE >=", value, "lectureTrainDate");
            return (Criteria) this;
        }

        public Criteria andLectureTrainDateLessThan(String value) {
            addCriterion("LECTURE_TRAIN_DATE <", value, "lectureTrainDate");
            return (Criteria) this;
        }

        public Criteria andLectureTrainDateLessThanOrEqualTo(String value) {
            addCriterion("LECTURE_TRAIN_DATE <=", value, "lectureTrainDate");
            return (Criteria) this;
        }

        public Criteria andLectureTrainDateLike(String value) {
            addCriterion("LECTURE_TRAIN_DATE like", value, "lectureTrainDate");
            return (Criteria) this;
        }

        public Criteria andLectureTrainDateNotLike(String value) {
            addCriterion("LECTURE_TRAIN_DATE not like", value, "lectureTrainDate");
            return (Criteria) this;
        }

        public Criteria andLectureTrainDateIn(List<String> values) {
            addCriterion("LECTURE_TRAIN_DATE in", values, "lectureTrainDate");
            return (Criteria) this;
        }

        public Criteria andLectureTrainDateNotIn(List<String> values) {
            addCriterion("LECTURE_TRAIN_DATE not in", values, "lectureTrainDate");
            return (Criteria) this;
        }

        public Criteria andLectureTrainDateBetween(String value1, String value2) {
            addCriterion("LECTURE_TRAIN_DATE between", value1, value2, "lectureTrainDate");
            return (Criteria) this;
        }

        public Criteria andLectureTrainDateNotBetween(String value1, String value2) {
            addCriterion("LECTURE_TRAIN_DATE not between", value1, value2, "lectureTrainDate");
            return (Criteria) this;
        }

        public Criteria andLectureStartTimeIsNull() {
            addCriterion("LECTURE_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLectureStartTimeIsNotNull() {
            addCriterion("LECTURE_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLectureStartTimeEqualTo(String value) {
            addCriterion("LECTURE_START_TIME =", value, "lectureStartTime");
            return (Criteria) this;
        }

        public Criteria andLectureStartTimeNotEqualTo(String value) {
            addCriterion("LECTURE_START_TIME <>", value, "lectureStartTime");
            return (Criteria) this;
        }

        public Criteria andLectureStartTimeGreaterThan(String value) {
            addCriterion("LECTURE_START_TIME >", value, "lectureStartTime");
            return (Criteria) this;
        }

        public Criteria andLectureStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("LECTURE_START_TIME >=", value, "lectureStartTime");
            return (Criteria) this;
        }

        public Criteria andLectureStartTimeLessThan(String value) {
            addCriterion("LECTURE_START_TIME <", value, "lectureStartTime");
            return (Criteria) this;
        }

        public Criteria andLectureStartTimeLessThanOrEqualTo(String value) {
            addCriterion("LECTURE_START_TIME <=", value, "lectureStartTime");
            return (Criteria) this;
        }

        public Criteria andLectureStartTimeLike(String value) {
            addCriterion("LECTURE_START_TIME like", value, "lectureStartTime");
            return (Criteria) this;
        }

        public Criteria andLectureStartTimeNotLike(String value) {
            addCriterion("LECTURE_START_TIME not like", value, "lectureStartTime");
            return (Criteria) this;
        }

        public Criteria andLectureStartTimeIn(List<String> values) {
            addCriterion("LECTURE_START_TIME in", values, "lectureStartTime");
            return (Criteria) this;
        }

        public Criteria andLectureStartTimeNotIn(List<String> values) {
            addCriterion("LECTURE_START_TIME not in", values, "lectureStartTime");
            return (Criteria) this;
        }

        public Criteria andLectureStartTimeBetween(String value1, String value2) {
            addCriterion("LECTURE_START_TIME between", value1, value2, "lectureStartTime");
            return (Criteria) this;
        }

        public Criteria andLectureStartTimeNotBetween(String value1, String value2) {
            addCriterion("LECTURE_START_TIME not between", value1, value2, "lectureStartTime");
            return (Criteria) this;
        }

        public Criteria andLectureEndTimeIsNull() {
            addCriterion("LECTURE_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLectureEndTimeIsNotNull() {
            addCriterion("LECTURE_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLectureEndTimeEqualTo(String value) {
            addCriterion("LECTURE_END_TIME =", value, "lectureEndTime");
            return (Criteria) this;
        }

        public Criteria andLectureEndTimeNotEqualTo(String value) {
            addCriterion("LECTURE_END_TIME <>", value, "lectureEndTime");
            return (Criteria) this;
        }

        public Criteria andLectureEndTimeGreaterThan(String value) {
            addCriterion("LECTURE_END_TIME >", value, "lectureEndTime");
            return (Criteria) this;
        }

        public Criteria andLectureEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("LECTURE_END_TIME >=", value, "lectureEndTime");
            return (Criteria) this;
        }

        public Criteria andLectureEndTimeLessThan(String value) {
            addCriterion("LECTURE_END_TIME <", value, "lectureEndTime");
            return (Criteria) this;
        }

        public Criteria andLectureEndTimeLessThanOrEqualTo(String value) {
            addCriterion("LECTURE_END_TIME <=", value, "lectureEndTime");
            return (Criteria) this;
        }

        public Criteria andLectureEndTimeLike(String value) {
            addCriterion("LECTURE_END_TIME like", value, "lectureEndTime");
            return (Criteria) this;
        }

        public Criteria andLectureEndTimeNotLike(String value) {
            addCriterion("LECTURE_END_TIME not like", value, "lectureEndTime");
            return (Criteria) this;
        }

        public Criteria andLectureEndTimeIn(List<String> values) {
            addCriterion("LECTURE_END_TIME in", values, "lectureEndTime");
            return (Criteria) this;
        }

        public Criteria andLectureEndTimeNotIn(List<String> values) {
            addCriterion("LECTURE_END_TIME not in", values, "lectureEndTime");
            return (Criteria) this;
        }

        public Criteria andLectureEndTimeBetween(String value1, String value2) {
            addCriterion("LECTURE_END_TIME between", value1, value2, "lectureEndTime");
            return (Criteria) this;
        }

        public Criteria andLectureEndTimeNotBetween(String value1, String value2) {
            addCriterion("LECTURE_END_TIME not between", value1, value2, "lectureEndTime");
            return (Criteria) this;
        }

        public Criteria andLectureContentIsNull() {
            addCriterion("LECTURE_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andLectureContentIsNotNull() {
            addCriterion("LECTURE_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andLectureContentEqualTo(String value) {
            addCriterion("LECTURE_CONTENT =", value, "lectureContent");
            return (Criteria) this;
        }

        public Criteria andLectureContentNotEqualTo(String value) {
            addCriterion("LECTURE_CONTENT <>", value, "lectureContent");
            return (Criteria) this;
        }

        public Criteria andLectureContentGreaterThan(String value) {
            addCriterion("LECTURE_CONTENT >", value, "lectureContent");
            return (Criteria) this;
        }

        public Criteria andLectureContentGreaterThanOrEqualTo(String value) {
            addCriterion("LECTURE_CONTENT >=", value, "lectureContent");
            return (Criteria) this;
        }

        public Criteria andLectureContentLessThan(String value) {
            addCriterion("LECTURE_CONTENT <", value, "lectureContent");
            return (Criteria) this;
        }

        public Criteria andLectureContentLessThanOrEqualTo(String value) {
            addCriterion("LECTURE_CONTENT <=", value, "lectureContent");
            return (Criteria) this;
        }

        public Criteria andLectureContentLike(String value) {
            addCriterion("LECTURE_CONTENT like", value, "lectureContent");
            return (Criteria) this;
        }

        public Criteria andLectureContentNotLike(String value) {
            addCriterion("LECTURE_CONTENT not like", value, "lectureContent");
            return (Criteria) this;
        }

        public Criteria andLectureContentIn(List<String> values) {
            addCriterion("LECTURE_CONTENT in", values, "lectureContent");
            return (Criteria) this;
        }

        public Criteria andLectureContentNotIn(List<String> values) {
            addCriterion("LECTURE_CONTENT not in", values, "lectureContent");
            return (Criteria) this;
        }

        public Criteria andLectureContentBetween(String value1, String value2) {
            addCriterion("LECTURE_CONTENT between", value1, value2, "lectureContent");
            return (Criteria) this;
        }

        public Criteria andLectureContentNotBetween(String value1, String value2) {
            addCriterion("LECTURE_CONTENT not between", value1, value2, "lectureContent");
            return (Criteria) this;
        }

        public Criteria andLectureTrainPlaceIsNull() {
            addCriterion("LECTURE_TRAIN_PLACE is null");
            return (Criteria) this;
        }

        public Criteria andLectureTrainPlaceIsNotNull() {
            addCriterion("LECTURE_TRAIN_PLACE is not null");
            return (Criteria) this;
        }

        public Criteria andLectureTrainPlaceEqualTo(String value) {
            addCriterion("LECTURE_TRAIN_PLACE =", value, "lectureTrainPlace");
            return (Criteria) this;
        }

        public Criteria andLectureTrainPlaceNotEqualTo(String value) {
            addCriterion("LECTURE_TRAIN_PLACE <>", value, "lectureTrainPlace");
            return (Criteria) this;
        }

        public Criteria andLectureTrainPlaceGreaterThan(String value) {
            addCriterion("LECTURE_TRAIN_PLACE >", value, "lectureTrainPlace");
            return (Criteria) this;
        }

        public Criteria andLectureTrainPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("LECTURE_TRAIN_PLACE >=", value, "lectureTrainPlace");
            return (Criteria) this;
        }

        public Criteria andLectureTrainPlaceLessThan(String value) {
            addCriterion("LECTURE_TRAIN_PLACE <", value, "lectureTrainPlace");
            return (Criteria) this;
        }

        public Criteria andLectureTrainPlaceLessThanOrEqualTo(String value) {
            addCriterion("LECTURE_TRAIN_PLACE <=", value, "lectureTrainPlace");
            return (Criteria) this;
        }

        public Criteria andLectureTrainPlaceLike(String value) {
            addCriterion("LECTURE_TRAIN_PLACE like", value, "lectureTrainPlace");
            return (Criteria) this;
        }

        public Criteria andLectureTrainPlaceNotLike(String value) {
            addCriterion("LECTURE_TRAIN_PLACE not like", value, "lectureTrainPlace");
            return (Criteria) this;
        }

        public Criteria andLectureTrainPlaceIn(List<String> values) {
            addCriterion("LECTURE_TRAIN_PLACE in", values, "lectureTrainPlace");
            return (Criteria) this;
        }

        public Criteria andLectureTrainPlaceNotIn(List<String> values) {
            addCriterion("LECTURE_TRAIN_PLACE not in", values, "lectureTrainPlace");
            return (Criteria) this;
        }

        public Criteria andLectureTrainPlaceBetween(String value1, String value2) {
            addCriterion("LECTURE_TRAIN_PLACE between", value1, value2, "lectureTrainPlace");
            return (Criteria) this;
        }

        public Criteria andLectureTrainPlaceNotBetween(String value1, String value2) {
            addCriterion("LECTURE_TRAIN_PLACE not between", value1, value2, "lectureTrainPlace");
            return (Criteria) this;
        }

        public Criteria andLectureDescIsNull() {
            addCriterion("LECTURE_DESC is null");
            return (Criteria) this;
        }

        public Criteria andLectureDescIsNotNull() {
            addCriterion("LECTURE_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andLectureDescEqualTo(String value) {
            addCriterion("LECTURE_DESC =", value, "lectureDesc");
            return (Criteria) this;
        }

        public Criteria andLectureDescNotEqualTo(String value) {
            addCriterion("LECTURE_DESC <>", value, "lectureDesc");
            return (Criteria) this;
        }

        public Criteria andLectureDescGreaterThan(String value) {
            addCriterion("LECTURE_DESC >", value, "lectureDesc");
            return (Criteria) this;
        }

        public Criteria andLectureDescGreaterThanOrEqualTo(String value) {
            addCriterion("LECTURE_DESC >=", value, "lectureDesc");
            return (Criteria) this;
        }

        public Criteria andLectureDescLessThan(String value) {
            addCriterion("LECTURE_DESC <", value, "lectureDesc");
            return (Criteria) this;
        }

        public Criteria andLectureDescLessThanOrEqualTo(String value) {
            addCriterion("LECTURE_DESC <=", value, "lectureDesc");
            return (Criteria) this;
        }

        public Criteria andLectureDescLike(String value) {
            addCriterion("LECTURE_DESC like", value, "lectureDesc");
            return (Criteria) this;
        }

        public Criteria andLectureDescNotLike(String value) {
            addCriterion("LECTURE_DESC not like", value, "lectureDesc");
            return (Criteria) this;
        }

        public Criteria andLectureDescIn(List<String> values) {
            addCriterion("LECTURE_DESC in", values, "lectureDesc");
            return (Criteria) this;
        }

        public Criteria andLectureDescNotIn(List<String> values) {
            addCriterion("LECTURE_DESC not in", values, "lectureDesc");
            return (Criteria) this;
        }

        public Criteria andLectureDescBetween(String value1, String value2) {
            addCriterion("LECTURE_DESC between", value1, value2, "lectureDesc");
            return (Criteria) this;
        }

        public Criteria andLectureDescNotBetween(String value1, String value2) {
            addCriterion("LECTURE_DESC not between", value1, value2, "lectureDesc");
            return (Criteria) this;
        }

        public Criteria andLectureEvaPeriodIsNull() {
            addCriterion("LECTURE_EVA_PERIOD is null");
            return (Criteria) this;
        }

        public Criteria andLectureEvaPeriodIsNotNull() {
            addCriterion("LECTURE_EVA_PERIOD is not null");
            return (Criteria) this;
        }

        public Criteria andLectureEvaPeriodEqualTo(String value) {
            addCriterion("LECTURE_EVA_PERIOD =", value, "lectureEvaPeriod");
            return (Criteria) this;
        }

        public Criteria andLectureEvaPeriodNotEqualTo(String value) {
            addCriterion("LECTURE_EVA_PERIOD <>", value, "lectureEvaPeriod");
            return (Criteria) this;
        }

        public Criteria andLectureEvaPeriodGreaterThan(String value) {
            addCriterion("LECTURE_EVA_PERIOD >", value, "lectureEvaPeriod");
            return (Criteria) this;
        }

        public Criteria andLectureEvaPeriodGreaterThanOrEqualTo(String value) {
            addCriterion("LECTURE_EVA_PERIOD >=", value, "lectureEvaPeriod");
            return (Criteria) this;
        }

        public Criteria andLectureEvaPeriodLessThan(String value) {
            addCriterion("LECTURE_EVA_PERIOD <", value, "lectureEvaPeriod");
            return (Criteria) this;
        }

        public Criteria andLectureEvaPeriodLessThanOrEqualTo(String value) {
            addCriterion("LECTURE_EVA_PERIOD <=", value, "lectureEvaPeriod");
            return (Criteria) this;
        }

        public Criteria andLectureEvaPeriodLike(String value) {
            addCriterion("LECTURE_EVA_PERIOD like", value, "lectureEvaPeriod");
            return (Criteria) this;
        }

        public Criteria andLectureEvaPeriodNotLike(String value) {
            addCriterion("LECTURE_EVA_PERIOD not like", value, "lectureEvaPeriod");
            return (Criteria) this;
        }

        public Criteria andLectureEvaPeriodIn(List<String> values) {
            addCriterion("LECTURE_EVA_PERIOD in", values, "lectureEvaPeriod");
            return (Criteria) this;
        }

        public Criteria andLectureEvaPeriodNotIn(List<String> values) {
            addCriterion("LECTURE_EVA_PERIOD not in", values, "lectureEvaPeriod");
            return (Criteria) this;
        }

        public Criteria andLectureEvaPeriodBetween(String value1, String value2) {
            addCriterion("LECTURE_EVA_PERIOD between", value1, value2, "lectureEvaPeriod");
            return (Criteria) this;
        }

        public Criteria andLectureEvaPeriodNotBetween(String value1, String value2) {
            addCriterion("LECTURE_EVA_PERIOD not between", value1, value2, "lectureEvaPeriod");
            return (Criteria) this;
        }

        public Criteria andLectureUnitIdIsNull() {
            addCriterion("LECTURE_UNIT_ID is null");
            return (Criteria) this;
        }

        public Criteria andLectureUnitIdIsNotNull() {
            addCriterion("LECTURE_UNIT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLectureUnitIdEqualTo(String value) {
            addCriterion("LECTURE_UNIT_ID =", value, "lectureUnitId");
            return (Criteria) this;
        }

        public Criteria andLectureUnitIdNotEqualTo(String value) {
            addCriterion("LECTURE_UNIT_ID <>", value, "lectureUnitId");
            return (Criteria) this;
        }

        public Criteria andLectureUnitIdGreaterThan(String value) {
            addCriterion("LECTURE_UNIT_ID >", value, "lectureUnitId");
            return (Criteria) this;
        }

        public Criteria andLectureUnitIdGreaterThanOrEqualTo(String value) {
            addCriterion("LECTURE_UNIT_ID >=", value, "lectureUnitId");
            return (Criteria) this;
        }

        public Criteria andLectureUnitIdLessThan(String value) {
            addCriterion("LECTURE_UNIT_ID <", value, "lectureUnitId");
            return (Criteria) this;
        }

        public Criteria andLectureUnitIdLessThanOrEqualTo(String value) {
            addCriterion("LECTURE_UNIT_ID <=", value, "lectureUnitId");
            return (Criteria) this;
        }

        public Criteria andLectureUnitIdLike(String value) {
            addCriterion("LECTURE_UNIT_ID like", value, "lectureUnitId");
            return (Criteria) this;
        }

        public Criteria andLectureUnitIdNotLike(String value) {
            addCriterion("LECTURE_UNIT_ID not like", value, "lectureUnitId");
            return (Criteria) this;
        }

        public Criteria andLectureUnitIdIn(List<String> values) {
            addCriterion("LECTURE_UNIT_ID in", values, "lectureUnitId");
            return (Criteria) this;
        }

        public Criteria andLectureUnitIdNotIn(List<String> values) {
            addCriterion("LECTURE_UNIT_ID not in", values, "lectureUnitId");
            return (Criteria) this;
        }

        public Criteria andLectureUnitIdBetween(String value1, String value2) {
            addCriterion("LECTURE_UNIT_ID between", value1, value2, "lectureUnitId");
            return (Criteria) this;
        }

        public Criteria andLectureUnitIdNotBetween(String value1, String value2) {
            addCriterion("LECTURE_UNIT_ID not between", value1, value2, "lectureUnitId");
            return (Criteria) this;
        }

        public Criteria andLectureUnitNameIsNull() {
            addCriterion("LECTURE_UNIT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLectureUnitNameIsNotNull() {
            addCriterion("LECTURE_UNIT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLectureUnitNameEqualTo(String value) {
            addCriterion("LECTURE_UNIT_NAME =", value, "lectureUnitName");
            return (Criteria) this;
        }

        public Criteria andLectureUnitNameNotEqualTo(String value) {
            addCriterion("LECTURE_UNIT_NAME <>", value, "lectureUnitName");
            return (Criteria) this;
        }

        public Criteria andLectureUnitNameGreaterThan(String value) {
            addCriterion("LECTURE_UNIT_NAME >", value, "lectureUnitName");
            return (Criteria) this;
        }

        public Criteria andLectureUnitNameGreaterThanOrEqualTo(String value) {
            addCriterion("LECTURE_UNIT_NAME >=", value, "lectureUnitName");
            return (Criteria) this;
        }

        public Criteria andLectureUnitNameLessThan(String value) {
            addCriterion("LECTURE_UNIT_NAME <", value, "lectureUnitName");
            return (Criteria) this;
        }

        public Criteria andLectureUnitNameLessThanOrEqualTo(String value) {
            addCriterion("LECTURE_UNIT_NAME <=", value, "lectureUnitName");
            return (Criteria) this;
        }

        public Criteria andLectureUnitNameLike(String value) {
            addCriterion("LECTURE_UNIT_NAME like", value, "lectureUnitName");
            return (Criteria) this;
        }

        public Criteria andLectureUnitNameNotLike(String value) {
            addCriterion("LECTURE_UNIT_NAME not like", value, "lectureUnitName");
            return (Criteria) this;
        }

        public Criteria andLectureUnitNameIn(List<String> values) {
            addCriterion("LECTURE_UNIT_NAME in", values, "lectureUnitName");
            return (Criteria) this;
        }

        public Criteria andLectureUnitNameNotIn(List<String> values) {
            addCriterion("LECTURE_UNIT_NAME not in", values, "lectureUnitName");
            return (Criteria) this;
        }

        public Criteria andLectureUnitNameBetween(String value1, String value2) {
            addCriterion("LECTURE_UNIT_NAME between", value1, value2, "lectureUnitName");
            return (Criteria) this;
        }

        public Criteria andLectureUnitNameNotBetween(String value1, String value2) {
            addCriterion("LECTURE_UNIT_NAME not between", value1, value2, "lectureUnitName");
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

        public Criteria andLectureTypeIdIsNull() {
            addCriterion("LECTURE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andLectureTypeIdIsNotNull() {
            addCriterion("LECTURE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLectureTypeIdEqualTo(String value) {
            addCriterion("LECTURE_TYPE_ID =", value, "lectureTypeId");
            return (Criteria) this;
        }

        public Criteria andLectureTypeIdNotEqualTo(String value) {
            addCriterion("LECTURE_TYPE_ID <>", value, "lectureTypeId");
            return (Criteria) this;
        }

        public Criteria andLectureTypeIdGreaterThan(String value) {
            addCriterion("LECTURE_TYPE_ID >", value, "lectureTypeId");
            return (Criteria) this;
        }

        public Criteria andLectureTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("LECTURE_TYPE_ID >=", value, "lectureTypeId");
            return (Criteria) this;
        }

        public Criteria andLectureTypeIdLessThan(String value) {
            addCriterion("LECTURE_TYPE_ID <", value, "lectureTypeId");
            return (Criteria) this;
        }

        public Criteria andLectureTypeIdLessThanOrEqualTo(String value) {
            addCriterion("LECTURE_TYPE_ID <=", value, "lectureTypeId");
            return (Criteria) this;
        }

        public Criteria andLectureTypeIdLike(String value) {
            addCriterion("LECTURE_TYPE_ID like", value, "lectureTypeId");
            return (Criteria) this;
        }

        public Criteria andLectureTypeIdNotLike(String value) {
            addCriterion("LECTURE_TYPE_ID not like", value, "lectureTypeId");
            return (Criteria) this;
        }

        public Criteria andLectureTypeIdIn(List<String> values) {
            addCriterion("LECTURE_TYPE_ID in", values, "lectureTypeId");
            return (Criteria) this;
        }

        public Criteria andLectureTypeIdNotIn(List<String> values) {
            addCriterion("LECTURE_TYPE_ID not in", values, "lectureTypeId");
            return (Criteria) this;
        }

        public Criteria andLectureTypeIdBetween(String value1, String value2) {
            addCriterion("LECTURE_TYPE_ID between", value1, value2, "lectureTypeId");
            return (Criteria) this;
        }

        public Criteria andLectureTypeIdNotBetween(String value1, String value2) {
            addCriterion("LECTURE_TYPE_ID not between", value1, value2, "lectureTypeId");
            return (Criteria) this;
        }

        public Criteria andLectureTypeNameIsNull() {
            addCriterion("LECTURE_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLectureTypeNameIsNotNull() {
            addCriterion("LECTURE_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLectureTypeNameEqualTo(String value) {
            addCriterion("LECTURE_TYPE_NAME =", value, "lectureTypeName");
            return (Criteria) this;
        }

        public Criteria andLectureTypeNameNotEqualTo(String value) {
            addCriterion("LECTURE_TYPE_NAME <>", value, "lectureTypeName");
            return (Criteria) this;
        }

        public Criteria andLectureTypeNameGreaterThan(String value) {
            addCriterion("LECTURE_TYPE_NAME >", value, "lectureTypeName");
            return (Criteria) this;
        }

        public Criteria andLectureTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("LECTURE_TYPE_NAME >=", value, "lectureTypeName");
            return (Criteria) this;
        }

        public Criteria andLectureTypeNameLessThan(String value) {
            addCriterion("LECTURE_TYPE_NAME <", value, "lectureTypeName");
            return (Criteria) this;
        }

        public Criteria andLectureTypeNameLessThanOrEqualTo(String value) {
            addCriterion("LECTURE_TYPE_NAME <=", value, "lectureTypeName");
            return (Criteria) this;
        }

        public Criteria andLectureTypeNameLike(String value) {
            addCriterion("LECTURE_TYPE_NAME like", value, "lectureTypeName");
            return (Criteria) this;
        }

        public Criteria andLectureTypeNameNotLike(String value) {
            addCriterion("LECTURE_TYPE_NAME not like", value, "lectureTypeName");
            return (Criteria) this;
        }

        public Criteria andLectureTypeNameIn(List<String> values) {
            addCriterion("LECTURE_TYPE_NAME in", values, "lectureTypeName");
            return (Criteria) this;
        }

        public Criteria andLectureTypeNameNotIn(List<String> values) {
            addCriterion("LECTURE_TYPE_NAME not in", values, "lectureTypeName");
            return (Criteria) this;
        }

        public Criteria andLectureTypeNameBetween(String value1, String value2) {
            addCriterion("LECTURE_TYPE_NAME between", value1, value2, "lectureTypeName");
            return (Criteria) this;
        }

        public Criteria andLectureTypeNameNotBetween(String value1, String value2) {
            addCriterion("LECTURE_TYPE_NAME not between", value1, value2, "lectureTypeName");
            return (Criteria) this;
        }

        public Criteria andLectureCodeUrlIsNull() {
            addCriterion("LECTURE_CODE_URL is null");
            return (Criteria) this;
        }

        public Criteria andLectureCodeUrlIsNotNull() {
            addCriterion("LECTURE_CODE_URL is not null");
            return (Criteria) this;
        }

        public Criteria andLectureCodeUrlEqualTo(String value) {
            addCriterion("LECTURE_CODE_URL =", value, "lectureCodeUrl");
            return (Criteria) this;
        }

        public Criteria andLectureCodeUrlNotEqualTo(String value) {
            addCriterion("LECTURE_CODE_URL <>", value, "lectureCodeUrl");
            return (Criteria) this;
        }

        public Criteria andLectureCodeUrlGreaterThan(String value) {
            addCriterion("LECTURE_CODE_URL >", value, "lectureCodeUrl");
            return (Criteria) this;
        }

        public Criteria andLectureCodeUrlGreaterThanOrEqualTo(String value) {
            addCriterion("LECTURE_CODE_URL >=", value, "lectureCodeUrl");
            return (Criteria) this;
        }

        public Criteria andLectureCodeUrlLessThan(String value) {
            addCriterion("LECTURE_CODE_URL <", value, "lectureCodeUrl");
            return (Criteria) this;
        }

        public Criteria andLectureCodeUrlLessThanOrEqualTo(String value) {
            addCriterion("LECTURE_CODE_URL <=", value, "lectureCodeUrl");
            return (Criteria) this;
        }

        public Criteria andLectureCodeUrlLike(String value) {
            addCriterion("LECTURE_CODE_URL like", value, "lectureCodeUrl");
            return (Criteria) this;
        }

        public Criteria andLectureCodeUrlNotLike(String value) {
            addCriterion("LECTURE_CODE_URL not like", value, "lectureCodeUrl");
            return (Criteria) this;
        }

        public Criteria andLectureCodeUrlIn(List<String> values) {
            addCriterion("LECTURE_CODE_URL in", values, "lectureCodeUrl");
            return (Criteria) this;
        }

        public Criteria andLectureCodeUrlNotIn(List<String> values) {
            addCriterion("LECTURE_CODE_URL not in", values, "lectureCodeUrl");
            return (Criteria) this;
        }

        public Criteria andLectureCodeUrlBetween(String value1, String value2) {
            addCriterion("LECTURE_CODE_URL between", value1, value2, "lectureCodeUrl");
            return (Criteria) this;
        }

        public Criteria andLectureCodeUrlNotBetween(String value1, String value2) {
            addCriterion("LECTURE_CODE_URL not between", value1, value2, "lectureCodeUrl");
            return (Criteria) this;
        }

        public Criteria andParticipantsIdIsNull() {
            addCriterion("PARTICIPANTS_ID is null");
            return (Criteria) this;
        }

        public Criteria andParticipantsIdIsNotNull() {
            addCriterion("PARTICIPANTS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParticipantsIdEqualTo(String value) {
            addCriterion("PARTICIPANTS_ID =", value, "participantsId");
            return (Criteria) this;
        }

        public Criteria andParticipantsIdNotEqualTo(String value) {
            addCriterion("PARTICIPANTS_ID <>", value, "participantsId");
            return (Criteria) this;
        }

        public Criteria andParticipantsIdGreaterThan(String value) {
            addCriterion("PARTICIPANTS_ID >", value, "participantsId");
            return (Criteria) this;
        }

        public Criteria andParticipantsIdGreaterThanOrEqualTo(String value) {
            addCriterion("PARTICIPANTS_ID >=", value, "participantsId");
            return (Criteria) this;
        }

        public Criteria andParticipantsIdLessThan(String value) {
            addCriterion("PARTICIPANTS_ID <", value, "participantsId");
            return (Criteria) this;
        }

        public Criteria andParticipantsIdLessThanOrEqualTo(String value) {
            addCriterion("PARTICIPANTS_ID <=", value, "participantsId");
            return (Criteria) this;
        }

        public Criteria andParticipantsIdLike(String value) {
            addCriterion("PARTICIPANTS_ID like", value, "participantsId");
            return (Criteria) this;
        }

        public Criteria andParticipantsIdNotLike(String value) {
            addCriterion("PARTICIPANTS_ID not like", value, "participantsId");
            return (Criteria) this;
        }

        public Criteria andParticipantsIdIn(List<String> values) {
            addCriterion("PARTICIPANTS_ID in", values, "participantsId");
            return (Criteria) this;
        }

        public Criteria andParticipantsIdNotIn(List<String> values) {
            addCriterion("PARTICIPANTS_ID not in", values, "participantsId");
            return (Criteria) this;
        }

        public Criteria andParticipantsIdBetween(String value1, String value2) {
            addCriterion("PARTICIPANTS_ID between", value1, value2, "participantsId");
            return (Criteria) this;
        }

        public Criteria andParticipantsIdNotBetween(String value1, String value2) {
            addCriterion("PARTICIPANTS_ID not between", value1, value2, "participantsId");
            return (Criteria) this;
        }

        public Criteria andParticipantsNameIsNull() {
            addCriterion("PARTICIPANTS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andParticipantsNameIsNotNull() {
            addCriterion("PARTICIPANTS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andParticipantsNameEqualTo(String value) {
            addCriterion("PARTICIPANTS_NAME =", value, "participantsName");
            return (Criteria) this;
        }

        public Criteria andParticipantsNameNotEqualTo(String value) {
            addCriterion("PARTICIPANTS_NAME <>", value, "participantsName");
            return (Criteria) this;
        }

        public Criteria andParticipantsNameGreaterThan(String value) {
            addCriterion("PARTICIPANTS_NAME >", value, "participantsName");
            return (Criteria) this;
        }

        public Criteria andParticipantsNameGreaterThanOrEqualTo(String value) {
            addCriterion("PARTICIPANTS_NAME >=", value, "participantsName");
            return (Criteria) this;
        }

        public Criteria andParticipantsNameLessThan(String value) {
            addCriterion("PARTICIPANTS_NAME <", value, "participantsName");
            return (Criteria) this;
        }

        public Criteria andParticipantsNameLessThanOrEqualTo(String value) {
            addCriterion("PARTICIPANTS_NAME <=", value, "participantsName");
            return (Criteria) this;
        }

        public Criteria andParticipantsNameLike(String value) {
            addCriterion("PARTICIPANTS_NAME like", value, "participantsName");
            return (Criteria) this;
        }

        public Criteria andParticipantsNameNotLike(String value) {
            addCriterion("PARTICIPANTS_NAME not like", value, "participantsName");
            return (Criteria) this;
        }

        public Criteria andParticipantsNameIn(List<String> values) {
            addCriterion("PARTICIPANTS_NAME in", values, "participantsName");
            return (Criteria) this;
        }

        public Criteria andParticipantsNameNotIn(List<String> values) {
            addCriterion("PARTICIPANTS_NAME not in", values, "participantsName");
            return (Criteria) this;
        }

        public Criteria andParticipantsNameBetween(String value1, String value2) {
            addCriterion("PARTICIPANTS_NAME between", value1, value2, "participantsName");
            return (Criteria) this;
        }

        public Criteria andParticipantsNameNotBetween(String value1, String value2) {
            addCriterion("PARTICIPANTS_NAME not between", value1, value2, "participantsName");
            return (Criteria) this;
        }

        public Criteria andLimitNumIsNull() {
            addCriterion("LIMIT_NUM is null");
            return (Criteria) this;
        }

        public Criteria andLimitNumIsNotNull() {
            addCriterion("LIMIT_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andLimitNumEqualTo(String value) {
            addCriterion("LIMIT_NUM =", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumNotEqualTo(String value) {
            addCriterion("LIMIT_NUM <>", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumGreaterThan(String value) {
            addCriterion("LIMIT_NUM >", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumGreaterThanOrEqualTo(String value) {
            addCriterion("LIMIT_NUM >=", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumLessThan(String value) {
            addCriterion("LIMIT_NUM <", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumLessThanOrEqualTo(String value) {
            addCriterion("LIMIT_NUM <=", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumLike(String value) {
            addCriterion("LIMIT_NUM like", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumNotLike(String value) {
            addCriterion("LIMIT_NUM not like", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumIn(List<String> values) {
            addCriterion("LIMIT_NUM in", values, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumNotIn(List<String> values) {
            addCriterion("LIMIT_NUM not in", values, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumBetween(String value1, String value2) {
            addCriterion("LIMIT_NUM between", value1, value2, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumNotBetween(String value1, String value2) {
            addCriterion("LIMIT_NUM not between", value1, value2, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLectureDescriptionIsNull() {
            addCriterion("LECTURE_DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andLectureDescriptionIsNotNull() {
            addCriterion("LECTURE_DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andLectureDescriptionEqualTo(String value) {
            addCriterion("LECTURE_DESCRIPTION =", value, "lectureDescription");
            return (Criteria) this;
        }

        public Criteria andLectureDescriptionNotEqualTo(String value) {
            addCriterion("LECTURE_DESCRIPTION <>", value, "lectureDescription");
            return (Criteria) this;
        }

        public Criteria andLectureDescriptionGreaterThan(String value) {
            addCriterion("LECTURE_DESCRIPTION >", value, "lectureDescription");
            return (Criteria) this;
        }

        public Criteria andLectureDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("LECTURE_DESCRIPTION >=", value, "lectureDescription");
            return (Criteria) this;
        }

        public Criteria andLectureDescriptionLessThan(String value) {
            addCriterion("LECTURE_DESCRIPTION <", value, "lectureDescription");
            return (Criteria) this;
        }

        public Criteria andLectureDescriptionLessThanOrEqualTo(String value) {
            addCriterion("LECTURE_DESCRIPTION <=", value, "lectureDescription");
            return (Criteria) this;
        }

        public Criteria andLectureDescriptionLike(String value) {
            addCriterion("LECTURE_DESCRIPTION like", value, "lectureDescription");
            return (Criteria) this;
        }

        public Criteria andLectureDescriptionNotLike(String value) {
            addCriterion("LECTURE_DESCRIPTION not like", value, "lectureDescription");
            return (Criteria) this;
        }

        public Criteria andLectureDescriptionIn(List<String> values) {
            addCriterion("LECTURE_DESCRIPTION in", values, "lectureDescription");
            return (Criteria) this;
        }

        public Criteria andLectureDescriptionNotIn(List<String> values) {
            addCriterion("LECTURE_DESCRIPTION not in", values, "lectureDescription");
            return (Criteria) this;
        }

        public Criteria andLectureDescriptionBetween(String value1, String value2) {
            addCriterion("LECTURE_DESCRIPTION between", value1, value2, "lectureDescription");
            return (Criteria) this;
        }

        public Criteria andLectureDescriptionNotBetween(String value1, String value2) {
            addCriterion("LECTURE_DESCRIPTION not between", value1, value2, "lectureDescription");
            return (Criteria) this;
        }

        public Criteria andCoursewareUrlIsNull() {
            addCriterion("COURSEWARE_URL is null");
            return (Criteria) this;
        }

        public Criteria andCoursewareUrlIsNotNull() {
            addCriterion("COURSEWARE_URL is not null");
            return (Criteria) this;
        }

        public Criteria andCoursewareUrlEqualTo(String value) {
            addCriterion("COURSEWARE_URL =", value, "coursewareUrl");
            return (Criteria) this;
        }

        public Criteria andCoursewareUrlNotEqualTo(String value) {
            addCriterion("COURSEWARE_URL <>", value, "coursewareUrl");
            return (Criteria) this;
        }

        public Criteria andCoursewareUrlGreaterThan(String value) {
            addCriterion("COURSEWARE_URL >", value, "coursewareUrl");
            return (Criteria) this;
        }

        public Criteria andCoursewareUrlGreaterThanOrEqualTo(String value) {
            addCriterion("COURSEWARE_URL >=", value, "coursewareUrl");
            return (Criteria) this;
        }

        public Criteria andCoursewareUrlLessThan(String value) {
            addCriterion("COURSEWARE_URL <", value, "coursewareUrl");
            return (Criteria) this;
        }

        public Criteria andCoursewareUrlLessThanOrEqualTo(String value) {
            addCriterion("COURSEWARE_URL <=", value, "coursewareUrl");
            return (Criteria) this;
        }

        public Criteria andCoursewareUrlLike(String value) {
            addCriterion("COURSEWARE_URL like", value, "coursewareUrl");
            return (Criteria) this;
        }

        public Criteria andCoursewareUrlNotLike(String value) {
            addCriterion("COURSEWARE_URL not like", value, "coursewareUrl");
            return (Criteria) this;
        }

        public Criteria andCoursewareUrlIn(List<String> values) {
            addCriterion("COURSEWARE_URL in", values, "coursewareUrl");
            return (Criteria) this;
        }

        public Criteria andCoursewareUrlNotIn(List<String> values) {
            addCriterion("COURSEWARE_URL not in", values, "coursewareUrl");
            return (Criteria) this;
        }

        public Criteria andCoursewareUrlBetween(String value1, String value2) {
            addCriterion("COURSEWARE_URL between", value1, value2, "coursewareUrl");
            return (Criteria) this;
        }

        public Criteria andCoursewareUrlNotBetween(String value1, String value2) {
            addCriterion("COURSEWARE_URL not between", value1, value2, "coursewareUrl");
            return (Criteria) this;
        }

        public Criteria andLectureLevelIdIsNull() {
            addCriterion("LECTURE_LEVEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andLectureLevelIdIsNotNull() {
            addCriterion("LECTURE_LEVEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLectureLevelIdEqualTo(String value) {
            addCriterion("LECTURE_LEVEL_ID =", value, "lectureLevelId");
            return (Criteria) this;
        }

        public Criteria andLectureLevelIdNotEqualTo(String value) {
            addCriterion("LECTURE_LEVEL_ID <>", value, "lectureLevelId");
            return (Criteria) this;
        }

        public Criteria andLectureLevelIdGreaterThan(String value) {
            addCriterion("LECTURE_LEVEL_ID >", value, "lectureLevelId");
            return (Criteria) this;
        }

        public Criteria andLectureLevelIdGreaterThanOrEqualTo(String value) {
            addCriterion("LECTURE_LEVEL_ID >=", value, "lectureLevelId");
            return (Criteria) this;
        }

        public Criteria andLectureLevelIdLessThan(String value) {
            addCriterion("LECTURE_LEVEL_ID <", value, "lectureLevelId");
            return (Criteria) this;
        }

        public Criteria andLectureLevelIdLessThanOrEqualTo(String value) {
            addCriterion("LECTURE_LEVEL_ID <=", value, "lectureLevelId");
            return (Criteria) this;
        }

        public Criteria andLectureLevelIdLike(String value) {
            addCriterion("LECTURE_LEVEL_ID like", value, "lectureLevelId");
            return (Criteria) this;
        }

        public Criteria andLectureLevelIdNotLike(String value) {
            addCriterion("LECTURE_LEVEL_ID not like", value, "lectureLevelId");
            return (Criteria) this;
        }

        public Criteria andLectureLevelIdIn(List<String> values) {
            addCriterion("LECTURE_LEVEL_ID in", values, "lectureLevelId");
            return (Criteria) this;
        }

        public Criteria andLectureLevelIdNotIn(List<String> values) {
            addCriterion("LECTURE_LEVEL_ID not in", values, "lectureLevelId");
            return (Criteria) this;
        }

        public Criteria andLectureLevelIdBetween(String value1, String value2) {
            addCriterion("LECTURE_LEVEL_ID between", value1, value2, "lectureLevelId");
            return (Criteria) this;
        }

        public Criteria andLectureLevelIdNotBetween(String value1, String value2) {
            addCriterion("LECTURE_LEVEL_ID not between", value1, value2, "lectureLevelId");
            return (Criteria) this;
        }

        public Criteria andLectureLevelNameIsNull() {
            addCriterion("LECTURE_LEVEL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLectureLevelNameIsNotNull() {
            addCriterion("LECTURE_LEVEL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLectureLevelNameEqualTo(String value) {
            addCriterion("LECTURE_LEVEL_NAME =", value, "lectureLevelName");
            return (Criteria) this;
        }

        public Criteria andLectureLevelNameNotEqualTo(String value) {
            addCriterion("LECTURE_LEVEL_NAME <>", value, "lectureLevelName");
            return (Criteria) this;
        }

        public Criteria andLectureLevelNameGreaterThan(String value) {
            addCriterion("LECTURE_LEVEL_NAME >", value, "lectureLevelName");
            return (Criteria) this;
        }

        public Criteria andLectureLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("LECTURE_LEVEL_NAME >=", value, "lectureLevelName");
            return (Criteria) this;
        }

        public Criteria andLectureLevelNameLessThan(String value) {
            addCriterion("LECTURE_LEVEL_NAME <", value, "lectureLevelName");
            return (Criteria) this;
        }

        public Criteria andLectureLevelNameLessThanOrEqualTo(String value) {
            addCriterion("LECTURE_LEVEL_NAME <=", value, "lectureLevelName");
            return (Criteria) this;
        }

        public Criteria andLectureLevelNameLike(String value) {
            addCriterion("LECTURE_LEVEL_NAME like", value, "lectureLevelName");
            return (Criteria) this;
        }

        public Criteria andLectureLevelNameNotLike(String value) {
            addCriterion("LECTURE_LEVEL_NAME not like", value, "lectureLevelName");
            return (Criteria) this;
        }

        public Criteria andLectureLevelNameIn(List<String> values) {
            addCriterion("LECTURE_LEVEL_NAME in", values, "lectureLevelName");
            return (Criteria) this;
        }

        public Criteria andLectureLevelNameNotIn(List<String> values) {
            addCriterion("LECTURE_LEVEL_NAME not in", values, "lectureLevelName");
            return (Criteria) this;
        }

        public Criteria andLectureLevelNameBetween(String value1, String value2) {
            addCriterion("LECTURE_LEVEL_NAME between", value1, value2, "lectureLevelName");
            return (Criteria) this;
        }

        public Criteria andLectureLevelNameNotBetween(String value1, String value2) {
            addCriterion("LECTURE_LEVEL_NAME not between", value1, value2, "lectureLevelName");
            return (Criteria) this;
        }

        public Criteria andLectureOutUrlIsNull() {
            addCriterion("LECTURE_OUT_URL is null");
            return (Criteria) this;
        }

        public Criteria andLectureOutUrlIsNotNull() {
            addCriterion("LECTURE_OUT_URL is not null");
            return (Criteria) this;
        }

        public Criteria andLectureOutUrlEqualTo(String value) {
            addCriterion("LECTURE_OUT_URL =", value, "lectureOutUrl");
            return (Criteria) this;
        }

        public Criteria andLectureOutUrlNotEqualTo(String value) {
            addCriterion("LECTURE_OUT_URL <>", value, "lectureOutUrl");
            return (Criteria) this;
        }

        public Criteria andLectureOutUrlGreaterThan(String value) {
            addCriterion("LECTURE_OUT_URL >", value, "lectureOutUrl");
            return (Criteria) this;
        }

        public Criteria andLectureOutUrlGreaterThanOrEqualTo(String value) {
            addCriterion("LECTURE_OUT_URL >=", value, "lectureOutUrl");
            return (Criteria) this;
        }

        public Criteria andLectureOutUrlLessThan(String value) {
            addCriterion("LECTURE_OUT_URL <", value, "lectureOutUrl");
            return (Criteria) this;
        }

        public Criteria andLectureOutUrlLessThanOrEqualTo(String value) {
            addCriterion("LECTURE_OUT_URL <=", value, "lectureOutUrl");
            return (Criteria) this;
        }

        public Criteria andLectureOutUrlLike(String value) {
            addCriterion("LECTURE_OUT_URL like", value, "lectureOutUrl");
            return (Criteria) this;
        }

        public Criteria andLectureOutUrlNotLike(String value) {
            addCriterion("LECTURE_OUT_URL not like", value, "lectureOutUrl");
            return (Criteria) this;
        }

        public Criteria andLectureOutUrlIn(List<String> values) {
            addCriterion("LECTURE_OUT_URL in", values, "lectureOutUrl");
            return (Criteria) this;
        }

        public Criteria andLectureOutUrlNotIn(List<String> values) {
            addCriterion("LECTURE_OUT_URL not in", values, "lectureOutUrl");
            return (Criteria) this;
        }

        public Criteria andLectureOutUrlBetween(String value1, String value2) {
            addCriterion("LECTURE_OUT_URL between", value1, value2, "lectureOutUrl");
            return (Criteria) this;
        }

        public Criteria andLectureOutUrlNotBetween(String value1, String value2) {
            addCriterion("LECTURE_OUT_URL not between", value1, value2, "lectureOutUrl");
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