package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResTeachQualifiedPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResTeachQualifiedPlanExample() {
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

        public Criteria andPlanFlowIsNull() {
            addCriterion("PLAN_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andPlanFlowIsNotNull() {
            addCriterion("PLAN_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andPlanFlowEqualTo(String value) {
            addCriterion("PLAN_FLOW =", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowNotEqualTo(String value) {
            addCriterion("PLAN_FLOW <>", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowGreaterThan(String value) {
            addCriterion("PLAN_FLOW >", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_FLOW >=", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowLessThan(String value) {
            addCriterion("PLAN_FLOW <", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowLessThanOrEqualTo(String value) {
            addCriterion("PLAN_FLOW <=", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowLike(String value) {
            addCriterion("PLAN_FLOW like", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowNotLike(String value) {
            addCriterion("PLAN_FLOW not like", value, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowIn(List<String> values) {
            addCriterion("PLAN_FLOW in", values, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowNotIn(List<String> values) {
            addCriterion("PLAN_FLOW not in", values, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowBetween(String value1, String value2) {
            addCriterion("PLAN_FLOW between", value1, value2, "planFlow");
            return (Criteria) this;
        }

        public Criteria andPlanFlowNotBetween(String value1, String value2) {
            addCriterion("PLAN_FLOW not between", value1, value2, "planFlow");
            return (Criteria) this;
        }

        public Criteria andIntroduceIsNull() {
            addCriterion("INTRODUCE is null");
            return (Criteria) this;
        }

        public Criteria andIntroduceIsNotNull() {
            addCriterion("INTRODUCE is not null");
            return (Criteria) this;
        }

        public Criteria andIntroduceEqualTo(String value) {
            addCriterion("INTRODUCE =", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotEqualTo(String value) {
            addCriterion("INTRODUCE <>", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThan(String value) {
            addCriterion("INTRODUCE >", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("INTRODUCE >=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThan(String value) {
            addCriterion("INTRODUCE <", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThanOrEqualTo(String value) {
            addCriterion("INTRODUCE <=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLike(String value) {
            addCriterion("INTRODUCE like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotLike(String value) {
            addCriterion("INTRODUCE not like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceIn(List<String> values) {
            addCriterion("INTRODUCE in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotIn(List<String> values) {
            addCriterion("INTRODUCE not in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceBetween(String value1, String value2) {
            addCriterion("INTRODUCE between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotBetween(String value1, String value2) {
            addCriterion("INTRODUCE not between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andPlanContentIsNull() {
            addCriterion("PLAN_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andPlanContentIsNotNull() {
            addCriterion("PLAN_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andPlanContentEqualTo(String value) {
            addCriterion("PLAN_CONTENT =", value, "planContent");
            return (Criteria) this;
        }

        public Criteria andPlanContentNotEqualTo(String value) {
            addCriterion("PLAN_CONTENT <>", value, "planContent");
            return (Criteria) this;
        }

        public Criteria andPlanContentGreaterThan(String value) {
            addCriterion("PLAN_CONTENT >", value, "planContent");
            return (Criteria) this;
        }

        public Criteria andPlanContentGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_CONTENT >=", value, "planContent");
            return (Criteria) this;
        }

        public Criteria andPlanContentLessThan(String value) {
            addCriterion("PLAN_CONTENT <", value, "planContent");
            return (Criteria) this;
        }

        public Criteria andPlanContentLessThanOrEqualTo(String value) {
            addCriterion("PLAN_CONTENT <=", value, "planContent");
            return (Criteria) this;
        }

        public Criteria andPlanContentLike(String value) {
            addCriterion("PLAN_CONTENT like", value, "planContent");
            return (Criteria) this;
        }

        public Criteria andPlanContentNotLike(String value) {
            addCriterion("PLAN_CONTENT not like", value, "planContent");
            return (Criteria) this;
        }

        public Criteria andPlanContentIn(List<String> values) {
            addCriterion("PLAN_CONTENT in", values, "planContent");
            return (Criteria) this;
        }

        public Criteria andPlanContentNotIn(List<String> values) {
            addCriterion("PLAN_CONTENT not in", values, "planContent");
            return (Criteria) this;
        }

        public Criteria andPlanContentBetween(String value1, String value2) {
            addCriterion("PLAN_CONTENT between", value1, value2, "planContent");
            return (Criteria) this;
        }

        public Criteria andPlanContentNotBetween(String value1, String value2) {
            addCriterion("PLAN_CONTENT not between", value1, value2, "planContent");
            return (Criteria) this;
        }

        public Criteria andEnrollStartTimeIsNull() {
            addCriterion("ENROLL_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEnrollStartTimeIsNotNull() {
            addCriterion("ENROLL_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEnrollStartTimeEqualTo(String value) {
            addCriterion("ENROLL_START_TIME =", value, "enrollStartTime");
            return (Criteria) this;
        }

        public Criteria andEnrollStartTimeNotEqualTo(String value) {
            addCriterion("ENROLL_START_TIME <>", value, "enrollStartTime");
            return (Criteria) this;
        }

        public Criteria andEnrollStartTimeGreaterThan(String value) {
            addCriterion("ENROLL_START_TIME >", value, "enrollStartTime");
            return (Criteria) this;
        }

        public Criteria andEnrollStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("ENROLL_START_TIME >=", value, "enrollStartTime");
            return (Criteria) this;
        }

        public Criteria andEnrollStartTimeLessThan(String value) {
            addCriterion("ENROLL_START_TIME <", value, "enrollStartTime");
            return (Criteria) this;
        }

        public Criteria andEnrollStartTimeLessThanOrEqualTo(String value) {
            addCriterion("ENROLL_START_TIME <=", value, "enrollStartTime");
            return (Criteria) this;
        }

        public Criteria andEnrollStartTimeLike(String value) {
            addCriterion("ENROLL_START_TIME like", value, "enrollStartTime");
            return (Criteria) this;
        }

        public Criteria andEnrollStartTimeNotLike(String value) {
            addCriterion("ENROLL_START_TIME not like", value, "enrollStartTime");
            return (Criteria) this;
        }

        public Criteria andEnrollStartTimeIn(List<String> values) {
            addCriterion("ENROLL_START_TIME in", values, "enrollStartTime");
            return (Criteria) this;
        }

        public Criteria andEnrollStartTimeNotIn(List<String> values) {
            addCriterion("ENROLL_START_TIME not in", values, "enrollStartTime");
            return (Criteria) this;
        }

        public Criteria andEnrollStartTimeBetween(String value1, String value2) {
            addCriterion("ENROLL_START_TIME between", value1, value2, "enrollStartTime");
            return (Criteria) this;
        }

        public Criteria andEnrollStartTimeNotBetween(String value1, String value2) {
            addCriterion("ENROLL_START_TIME not between", value1, value2, "enrollStartTime");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeIsNull() {
            addCriterion("ENROLL_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeIsNotNull() {
            addCriterion("ENROLL_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeEqualTo(String value) {
            addCriterion("ENROLL_END_TIME =", value, "enrollEndTime");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeNotEqualTo(String value) {
            addCriterion("ENROLL_END_TIME <>", value, "enrollEndTime");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeGreaterThan(String value) {
            addCriterion("ENROLL_END_TIME >", value, "enrollEndTime");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("ENROLL_END_TIME >=", value, "enrollEndTime");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeLessThan(String value) {
            addCriterion("ENROLL_END_TIME <", value, "enrollEndTime");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeLessThanOrEqualTo(String value) {
            addCriterion("ENROLL_END_TIME <=", value, "enrollEndTime");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeLike(String value) {
            addCriterion("ENROLL_END_TIME like", value, "enrollEndTime");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeNotLike(String value) {
            addCriterion("ENROLL_END_TIME not like", value, "enrollEndTime");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeIn(List<String> values) {
            addCriterion("ENROLL_END_TIME in", values, "enrollEndTime");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeNotIn(List<String> values) {
            addCriterion("ENROLL_END_TIME not in", values, "enrollEndTime");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeBetween(String value1, String value2) {
            addCriterion("ENROLL_END_TIME between", value1, value2, "enrollEndTime");
            return (Criteria) this;
        }

        public Criteria andEnrollEndTimeNotBetween(String value1, String value2) {
            addCriterion("ENROLL_END_TIME not between", value1, value2, "enrollEndTime");
            return (Criteria) this;
        }

        public Criteria andPlanStartTimeIsNull() {
            addCriterion("PLAN_START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPlanStartTimeIsNotNull() {
            addCriterion("PLAN_START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPlanStartTimeEqualTo(String value) {
            addCriterion("PLAN_START_TIME =", value, "planStartTime");
            return (Criteria) this;
        }

        public Criteria andPlanStartTimeNotEqualTo(String value) {
            addCriterion("PLAN_START_TIME <>", value, "planStartTime");
            return (Criteria) this;
        }

        public Criteria andPlanStartTimeGreaterThan(String value) {
            addCriterion("PLAN_START_TIME >", value, "planStartTime");
            return (Criteria) this;
        }

        public Criteria andPlanStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_START_TIME >=", value, "planStartTime");
            return (Criteria) this;
        }

        public Criteria andPlanStartTimeLessThan(String value) {
            addCriterion("PLAN_START_TIME <", value, "planStartTime");
            return (Criteria) this;
        }

        public Criteria andPlanStartTimeLessThanOrEqualTo(String value) {
            addCriterion("PLAN_START_TIME <=", value, "planStartTime");
            return (Criteria) this;
        }

        public Criteria andPlanStartTimeLike(String value) {
            addCriterion("PLAN_START_TIME like", value, "planStartTime");
            return (Criteria) this;
        }

        public Criteria andPlanStartTimeNotLike(String value) {
            addCriterion("PLAN_START_TIME not like", value, "planStartTime");
            return (Criteria) this;
        }

        public Criteria andPlanStartTimeIn(List<String> values) {
            addCriterion("PLAN_START_TIME in", values, "planStartTime");
            return (Criteria) this;
        }

        public Criteria andPlanStartTimeNotIn(List<String> values) {
            addCriterion("PLAN_START_TIME not in", values, "planStartTime");
            return (Criteria) this;
        }

        public Criteria andPlanStartTimeBetween(String value1, String value2) {
            addCriterion("PLAN_START_TIME between", value1, value2, "planStartTime");
            return (Criteria) this;
        }

        public Criteria andPlanStartTimeNotBetween(String value1, String value2) {
            addCriterion("PLAN_START_TIME not between", value1, value2, "planStartTime");
            return (Criteria) this;
        }

        public Criteria andPlanEndTimeIsNull() {
            addCriterion("PLAN_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPlanEndTimeIsNotNull() {
            addCriterion("PLAN_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPlanEndTimeEqualTo(String value) {
            addCriterion("PLAN_END_TIME =", value, "planEndTime");
            return (Criteria) this;
        }

        public Criteria andPlanEndTimeNotEqualTo(String value) {
            addCriterion("PLAN_END_TIME <>", value, "planEndTime");
            return (Criteria) this;
        }

        public Criteria andPlanEndTimeGreaterThan(String value) {
            addCriterion("PLAN_END_TIME >", value, "planEndTime");
            return (Criteria) this;
        }

        public Criteria andPlanEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_END_TIME >=", value, "planEndTime");
            return (Criteria) this;
        }

        public Criteria andPlanEndTimeLessThan(String value) {
            addCriterion("PLAN_END_TIME <", value, "planEndTime");
            return (Criteria) this;
        }

        public Criteria andPlanEndTimeLessThanOrEqualTo(String value) {
            addCriterion("PLAN_END_TIME <=", value, "planEndTime");
            return (Criteria) this;
        }

        public Criteria andPlanEndTimeLike(String value) {
            addCriterion("PLAN_END_TIME like", value, "planEndTime");
            return (Criteria) this;
        }

        public Criteria andPlanEndTimeNotLike(String value) {
            addCriterion("PLAN_END_TIME not like", value, "planEndTime");
            return (Criteria) this;
        }

        public Criteria andPlanEndTimeIn(List<String> values) {
            addCriterion("PLAN_END_TIME in", values, "planEndTime");
            return (Criteria) this;
        }

        public Criteria andPlanEndTimeNotIn(List<String> values) {
            addCriterion("PLAN_END_TIME not in", values, "planEndTime");
            return (Criteria) this;
        }

        public Criteria andPlanEndTimeBetween(String value1, String value2) {
            addCriterion("PLAN_END_TIME between", value1, value2, "planEndTime");
            return (Criteria) this;
        }

        public Criteria andPlanEndTimeNotBetween(String value1, String value2) {
            addCriterion("PLAN_END_TIME not between", value1, value2, "planEndTime");
            return (Criteria) this;
        }

        public Criteria andAskContentIsNull() {
            addCriterion("ASK_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andAskContentIsNotNull() {
            addCriterion("ASK_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andAskContentEqualTo(String value) {
            addCriterion("ASK_CONTENT =", value, "askContent");
            return (Criteria) this;
        }

        public Criteria andAskContentNotEqualTo(String value) {
            addCriterion("ASK_CONTENT <>", value, "askContent");
            return (Criteria) this;
        }

        public Criteria andAskContentGreaterThan(String value) {
            addCriterion("ASK_CONTENT >", value, "askContent");
            return (Criteria) this;
        }

        public Criteria andAskContentGreaterThanOrEqualTo(String value) {
            addCriterion("ASK_CONTENT >=", value, "askContent");
            return (Criteria) this;
        }

        public Criteria andAskContentLessThan(String value) {
            addCriterion("ASK_CONTENT <", value, "askContent");
            return (Criteria) this;
        }

        public Criteria andAskContentLessThanOrEqualTo(String value) {
            addCriterion("ASK_CONTENT <=", value, "askContent");
            return (Criteria) this;
        }

        public Criteria andAskContentLike(String value) {
            addCriterion("ASK_CONTENT like", value, "askContent");
            return (Criteria) this;
        }

        public Criteria andAskContentNotLike(String value) {
            addCriterion("ASK_CONTENT not like", value, "askContent");
            return (Criteria) this;
        }

        public Criteria andAskContentIn(List<String> values) {
            addCriterion("ASK_CONTENT in", values, "askContent");
            return (Criteria) this;
        }

        public Criteria andAskContentNotIn(List<String> values) {
            addCriterion("ASK_CONTENT not in", values, "askContent");
            return (Criteria) this;
        }

        public Criteria andAskContentBetween(String value1, String value2) {
            addCriterion("ASK_CONTENT between", value1, value2, "askContent");
            return (Criteria) this;
        }

        public Criteria andAskContentNotBetween(String value1, String value2) {
            addCriterion("ASK_CONTENT not between", value1, value2, "askContent");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdIsNull() {
            addCriterion("PLAN_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdIsNotNull() {
            addCriterion("PLAN_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdEqualTo(String value) {
            addCriterion("PLAN_STATUS_ID =", value, "planStatusId");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdNotEqualTo(String value) {
            addCriterion("PLAN_STATUS_ID <>", value, "planStatusId");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdGreaterThan(String value) {
            addCriterion("PLAN_STATUS_ID >", value, "planStatusId");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_STATUS_ID >=", value, "planStatusId");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdLessThan(String value) {
            addCriterion("PLAN_STATUS_ID <", value, "planStatusId");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdLessThanOrEqualTo(String value) {
            addCriterion("PLAN_STATUS_ID <=", value, "planStatusId");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdLike(String value) {
            addCriterion("PLAN_STATUS_ID like", value, "planStatusId");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdNotLike(String value) {
            addCriterion("PLAN_STATUS_ID not like", value, "planStatusId");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdIn(List<String> values) {
            addCriterion("PLAN_STATUS_ID in", values, "planStatusId");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdNotIn(List<String> values) {
            addCriterion("PLAN_STATUS_ID not in", values, "planStatusId");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdBetween(String value1, String value2) {
            addCriterion("PLAN_STATUS_ID between", value1, value2, "planStatusId");
            return (Criteria) this;
        }

        public Criteria andPlanStatusIdNotBetween(String value1, String value2) {
            addCriterion("PLAN_STATUS_ID not between", value1, value2, "planStatusId");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameIsNull() {
            addCriterion("PLAN_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameIsNotNull() {
            addCriterion("PLAN_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameEqualTo(String value) {
            addCriterion("PLAN_STATUS_NAME =", value, "planStatusName");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameNotEqualTo(String value) {
            addCriterion("PLAN_STATUS_NAME <>", value, "planStatusName");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameGreaterThan(String value) {
            addCriterion("PLAN_STATUS_NAME >", value, "planStatusName");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("PLAN_STATUS_NAME >=", value, "planStatusName");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameLessThan(String value) {
            addCriterion("PLAN_STATUS_NAME <", value, "planStatusName");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameLessThanOrEqualTo(String value) {
            addCriterion("PLAN_STATUS_NAME <=", value, "planStatusName");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameLike(String value) {
            addCriterion("PLAN_STATUS_NAME like", value, "planStatusName");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameNotLike(String value) {
            addCriterion("PLAN_STATUS_NAME not like", value, "planStatusName");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameIn(List<String> values) {
            addCriterion("PLAN_STATUS_NAME in", values, "planStatusName");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameNotIn(List<String> values) {
            addCriterion("PLAN_STATUS_NAME not in", values, "planStatusName");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameBetween(String value1, String value2) {
            addCriterion("PLAN_STATUS_NAME between", value1, value2, "planStatusName");
            return (Criteria) this;
        }

        public Criteria andPlanStatusNameNotBetween(String value1, String value2) {
            addCriterion("PLAN_STATUS_NAME not between", value1, value2, "planStatusName");
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