package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ConsultInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ConsultInfoExample() {
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

        public Criteria andConsultInfoFlowIsNull() {
            addCriterion("CONSULT_INFO_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andConsultInfoFlowIsNotNull() {
            addCriterion("CONSULT_INFO_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andConsultInfoFlowEqualTo(String value) {
            addCriterion("CONSULT_INFO_FLOW =", value, "consultInfoFlow");
            return (Criteria) this;
        }

        public Criteria andConsultInfoFlowNotEqualTo(String value) {
            addCriterion("CONSULT_INFO_FLOW <>", value, "consultInfoFlow");
            return (Criteria) this;
        }

        public Criteria andConsultInfoFlowGreaterThan(String value) {
            addCriterion("CONSULT_INFO_FLOW >", value, "consultInfoFlow");
            return (Criteria) this;
        }

        public Criteria andConsultInfoFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CONSULT_INFO_FLOW >=", value, "consultInfoFlow");
            return (Criteria) this;
        }

        public Criteria andConsultInfoFlowLessThan(String value) {
            addCriterion("CONSULT_INFO_FLOW <", value, "consultInfoFlow");
            return (Criteria) this;
        }

        public Criteria andConsultInfoFlowLessThanOrEqualTo(String value) {
            addCriterion("CONSULT_INFO_FLOW <=", value, "consultInfoFlow");
            return (Criteria) this;
        }

        public Criteria andConsultInfoFlowLike(String value) {
            addCriterion("CONSULT_INFO_FLOW like", value, "consultInfoFlow");
            return (Criteria) this;
        }

        public Criteria andConsultInfoFlowNotLike(String value) {
            addCriterion("CONSULT_INFO_FLOW not like", value, "consultInfoFlow");
            return (Criteria) this;
        }

        public Criteria andConsultInfoFlowIn(List<String> values) {
            addCriterion("CONSULT_INFO_FLOW in", values, "consultInfoFlow");
            return (Criteria) this;
        }

        public Criteria andConsultInfoFlowNotIn(List<String> values) {
            addCriterion("CONSULT_INFO_FLOW not in", values, "consultInfoFlow");
            return (Criteria) this;
        }

        public Criteria andConsultInfoFlowBetween(String value1, String value2) {
            addCriterion("CONSULT_INFO_FLOW between", value1, value2, "consultInfoFlow");
            return (Criteria) this;
        }

        public Criteria andConsultInfoFlowNotBetween(String value1, String value2) {
            addCriterion("CONSULT_INFO_FLOW not between", value1, value2, "consultInfoFlow");
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

        public Criteria andConsultQuestionIsNull() {
            addCriterion("CONSULT_QUESTION is null");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionIsNotNull() {
            addCriterion("CONSULT_QUESTION is not null");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionEqualTo(String value) {
            addCriterion("CONSULT_QUESTION =", value, "consultQuestion");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionNotEqualTo(String value) {
            addCriterion("CONSULT_QUESTION <>", value, "consultQuestion");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionGreaterThan(String value) {
            addCriterion("CONSULT_QUESTION >", value, "consultQuestion");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionGreaterThanOrEqualTo(String value) {
            addCriterion("CONSULT_QUESTION >=", value, "consultQuestion");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionLessThan(String value) {
            addCriterion("CONSULT_QUESTION <", value, "consultQuestion");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionLessThanOrEqualTo(String value) {
            addCriterion("CONSULT_QUESTION <=", value, "consultQuestion");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionLike(String value) {
            addCriterion("CONSULT_QUESTION like", value, "consultQuestion");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionNotLike(String value) {
            addCriterion("CONSULT_QUESTION not like", value, "consultQuestion");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionIn(List<String> values) {
            addCriterion("CONSULT_QUESTION in", values, "consultQuestion");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionNotIn(List<String> values) {
            addCriterion("CONSULT_QUESTION not in", values, "consultQuestion");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionBetween(String value1, String value2) {
            addCriterion("CONSULT_QUESTION between", value1, value2, "consultQuestion");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionNotBetween(String value1, String value2) {
            addCriterion("CONSULT_QUESTION not between", value1, value2, "consultQuestion");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerFlowIsNull() {
            addCriterion("CONSULT_QUESTIONER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerFlowIsNotNull() {
            addCriterion("CONSULT_QUESTIONER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerFlowEqualTo(String value) {
            addCriterion("CONSULT_QUESTIONER_FLOW =", value, "consultQuestionerFlow");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerFlowNotEqualTo(String value) {
            addCriterion("CONSULT_QUESTIONER_FLOW <>", value, "consultQuestionerFlow");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerFlowGreaterThan(String value) {
            addCriterion("CONSULT_QUESTIONER_FLOW >", value, "consultQuestionerFlow");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CONSULT_QUESTIONER_FLOW >=", value, "consultQuestionerFlow");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerFlowLessThan(String value) {
            addCriterion("CONSULT_QUESTIONER_FLOW <", value, "consultQuestionerFlow");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerFlowLessThanOrEqualTo(String value) {
            addCriterion("CONSULT_QUESTIONER_FLOW <=", value, "consultQuestionerFlow");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerFlowLike(String value) {
            addCriterion("CONSULT_QUESTIONER_FLOW like", value, "consultQuestionerFlow");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerFlowNotLike(String value) {
            addCriterion("CONSULT_QUESTIONER_FLOW not like", value, "consultQuestionerFlow");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerFlowIn(List<String> values) {
            addCriterion("CONSULT_QUESTIONER_FLOW in", values, "consultQuestionerFlow");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerFlowNotIn(List<String> values) {
            addCriterion("CONSULT_QUESTIONER_FLOW not in", values, "consultQuestionerFlow");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerFlowBetween(String value1, String value2) {
            addCriterion("CONSULT_QUESTIONER_FLOW between", value1, value2, "consultQuestionerFlow");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerFlowNotBetween(String value1, String value2) {
            addCriterion("CONSULT_QUESTIONER_FLOW not between", value1, value2, "consultQuestionerFlow");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerNameIsNull() {
            addCriterion("CONSULT_QUESTIONER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerNameIsNotNull() {
            addCriterion("CONSULT_QUESTIONER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerNameEqualTo(String value) {
            addCriterion("CONSULT_QUESTIONER_NAME =", value, "consultQuestionerName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerNameNotEqualTo(String value) {
            addCriterion("CONSULT_QUESTIONER_NAME <>", value, "consultQuestionerName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerNameGreaterThan(String value) {
            addCriterion("CONSULT_QUESTIONER_NAME >", value, "consultQuestionerName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONSULT_QUESTIONER_NAME >=", value, "consultQuestionerName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerNameLessThan(String value) {
            addCriterion("CONSULT_QUESTIONER_NAME <", value, "consultQuestionerName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerNameLessThanOrEqualTo(String value) {
            addCriterion("CONSULT_QUESTIONER_NAME <=", value, "consultQuestionerName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerNameLike(String value) {
            addCriterion("CONSULT_QUESTIONER_NAME like", value, "consultQuestionerName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerNameNotLike(String value) {
            addCriterion("CONSULT_QUESTIONER_NAME not like", value, "consultQuestionerName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerNameIn(List<String> values) {
            addCriterion("CONSULT_QUESTIONER_NAME in", values, "consultQuestionerName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerNameNotIn(List<String> values) {
            addCriterion("CONSULT_QUESTIONER_NAME not in", values, "consultQuestionerName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerNameBetween(String value1, String value2) {
            addCriterion("CONSULT_QUESTIONER_NAME between", value1, value2, "consultQuestionerName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerNameNotBetween(String value1, String value2) {
            addCriterion("CONSULT_QUESTIONER_NAME not between", value1, value2, "consultQuestionerName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerGradeIsNull() {
            addCriterion("CONSULT_QUESTIONER_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerGradeIsNotNull() {
            addCriterion("CONSULT_QUESTIONER_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerGradeEqualTo(String value) {
            addCriterion("CONSULT_QUESTIONER_GRADE =", value, "consultQuestionerGrade");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerGradeNotEqualTo(String value) {
            addCriterion("CONSULT_QUESTIONER_GRADE <>", value, "consultQuestionerGrade");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerGradeGreaterThan(String value) {
            addCriterion("CONSULT_QUESTIONER_GRADE >", value, "consultQuestionerGrade");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerGradeGreaterThanOrEqualTo(String value) {
            addCriterion("CONSULT_QUESTIONER_GRADE >=", value, "consultQuestionerGrade");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerGradeLessThan(String value) {
            addCriterion("CONSULT_QUESTIONER_GRADE <", value, "consultQuestionerGrade");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerGradeLessThanOrEqualTo(String value) {
            addCriterion("CONSULT_QUESTIONER_GRADE <=", value, "consultQuestionerGrade");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerGradeLike(String value) {
            addCriterion("CONSULT_QUESTIONER_GRADE like", value, "consultQuestionerGrade");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerGradeNotLike(String value) {
            addCriterion("CONSULT_QUESTIONER_GRADE not like", value, "consultQuestionerGrade");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerGradeIn(List<String> values) {
            addCriterion("CONSULT_QUESTIONER_GRADE in", values, "consultQuestionerGrade");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerGradeNotIn(List<String> values) {
            addCriterion("CONSULT_QUESTIONER_GRADE not in", values, "consultQuestionerGrade");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerGradeBetween(String value1, String value2) {
            addCriterion("CONSULT_QUESTIONER_GRADE between", value1, value2, "consultQuestionerGrade");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerGradeNotBetween(String value1, String value2) {
            addCriterion("CONSULT_QUESTIONER_GRADE not between", value1, value2, "consultQuestionerGrade");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseNameIsNull() {
            addCriterion("CONSULT_QUESTIONER_BASE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseNameIsNotNull() {
            addCriterion("CONSULT_QUESTIONER_BASE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseNameEqualTo(String value) {
            addCriterion("CONSULT_QUESTIONER_BASE_NAME =", value, "consultQuestionerBaseName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseNameNotEqualTo(String value) {
            addCriterion("CONSULT_QUESTIONER_BASE_NAME <>", value, "consultQuestionerBaseName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseNameGreaterThan(String value) {
            addCriterion("CONSULT_QUESTIONER_BASE_NAME >", value, "consultQuestionerBaseName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONSULT_QUESTIONER_BASE_NAME >=", value, "consultQuestionerBaseName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseNameLessThan(String value) {
            addCriterion("CONSULT_QUESTIONER_BASE_NAME <", value, "consultQuestionerBaseName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseNameLessThanOrEqualTo(String value) {
            addCriterion("CONSULT_QUESTIONER_BASE_NAME <=", value, "consultQuestionerBaseName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseNameLike(String value) {
            addCriterion("CONSULT_QUESTIONER_BASE_NAME like", value, "consultQuestionerBaseName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseNameNotLike(String value) {
            addCriterion("CONSULT_QUESTIONER_BASE_NAME not like", value, "consultQuestionerBaseName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseNameIn(List<String> values) {
            addCriterion("CONSULT_QUESTIONER_BASE_NAME in", values, "consultQuestionerBaseName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseNameNotIn(List<String> values) {
            addCriterion("CONSULT_QUESTIONER_BASE_NAME not in", values, "consultQuestionerBaseName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseNameBetween(String value1, String value2) {
            addCriterion("CONSULT_QUESTIONER_BASE_NAME between", value1, value2, "consultQuestionerBaseName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseNameNotBetween(String value1, String value2) {
            addCriterion("CONSULT_QUESTIONER_BASE_NAME not between", value1, value2, "consultQuestionerBaseName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseFlowIsNull() {
            addCriterion("CONSULT_QUESTIONER_BASE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseFlowIsNotNull() {
            addCriterion("CONSULT_QUESTIONER_BASE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseFlowEqualTo(String value) {
            addCriterion("CONSULT_QUESTIONER_BASE_FLOW =", value, "consultQuestionerBaseFlow");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseFlowNotEqualTo(String value) {
            addCriterion("CONSULT_QUESTIONER_BASE_FLOW <>", value, "consultQuestionerBaseFlow");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseFlowGreaterThan(String value) {
            addCriterion("CONSULT_QUESTIONER_BASE_FLOW >", value, "consultQuestionerBaseFlow");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CONSULT_QUESTIONER_BASE_FLOW >=", value, "consultQuestionerBaseFlow");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseFlowLessThan(String value) {
            addCriterion("CONSULT_QUESTIONER_BASE_FLOW <", value, "consultQuestionerBaseFlow");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseFlowLessThanOrEqualTo(String value) {
            addCriterion("CONSULT_QUESTIONER_BASE_FLOW <=", value, "consultQuestionerBaseFlow");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseFlowLike(String value) {
            addCriterion("CONSULT_QUESTIONER_BASE_FLOW like", value, "consultQuestionerBaseFlow");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseFlowNotLike(String value) {
            addCriterion("CONSULT_QUESTIONER_BASE_FLOW not like", value, "consultQuestionerBaseFlow");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseFlowIn(List<String> values) {
            addCriterion("CONSULT_QUESTIONER_BASE_FLOW in", values, "consultQuestionerBaseFlow");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseFlowNotIn(List<String> values) {
            addCriterion("CONSULT_QUESTIONER_BASE_FLOW not in", values, "consultQuestionerBaseFlow");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseFlowBetween(String value1, String value2) {
            addCriterion("CONSULT_QUESTIONER_BASE_FLOW between", value1, value2, "consultQuestionerBaseFlow");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionerBaseFlowNotBetween(String value1, String value2) {
            addCriterion("CONSULT_QUESTIONER_BASE_FLOW not between", value1, value2, "consultQuestionerBaseFlow");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionCreateTimeIsNull() {
            addCriterion("CONSULT_QUESTION_CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionCreateTimeIsNotNull() {
            addCriterion("CONSULT_QUESTION_CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionCreateTimeEqualTo(String value) {
            addCriterion("CONSULT_QUESTION_CREATE_TIME =", value, "consultQuestionCreateTime");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionCreateTimeNotEqualTo(String value) {
            addCriterion("CONSULT_QUESTION_CREATE_TIME <>", value, "consultQuestionCreateTime");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionCreateTimeGreaterThan(String value) {
            addCriterion("CONSULT_QUESTION_CREATE_TIME >", value, "consultQuestionCreateTime");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CONSULT_QUESTION_CREATE_TIME >=", value, "consultQuestionCreateTime");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionCreateTimeLessThan(String value) {
            addCriterion("CONSULT_QUESTION_CREATE_TIME <", value, "consultQuestionCreateTime");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("CONSULT_QUESTION_CREATE_TIME <=", value, "consultQuestionCreateTime");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionCreateTimeLike(String value) {
            addCriterion("CONSULT_QUESTION_CREATE_TIME like", value, "consultQuestionCreateTime");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionCreateTimeNotLike(String value) {
            addCriterion("CONSULT_QUESTION_CREATE_TIME not like", value, "consultQuestionCreateTime");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionCreateTimeIn(List<String> values) {
            addCriterion("CONSULT_QUESTION_CREATE_TIME in", values, "consultQuestionCreateTime");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionCreateTimeNotIn(List<String> values) {
            addCriterion("CONSULT_QUESTION_CREATE_TIME not in", values, "consultQuestionCreateTime");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionCreateTimeBetween(String value1, String value2) {
            addCriterion("CONSULT_QUESTION_CREATE_TIME between", value1, value2, "consultQuestionCreateTime");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionCreateTimeNotBetween(String value1, String value2) {
            addCriterion("CONSULT_QUESTION_CREATE_TIME not between", value1, value2, "consultQuestionCreateTime");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionModifyTimeIsNull() {
            addCriterion("CONSULT_QUESTION_MODIFY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionModifyTimeIsNotNull() {
            addCriterion("CONSULT_QUESTION_MODIFY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionModifyTimeEqualTo(String value) {
            addCriterion("CONSULT_QUESTION_MODIFY_TIME =", value, "consultQuestionModifyTime");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionModifyTimeNotEqualTo(String value) {
            addCriterion("CONSULT_QUESTION_MODIFY_TIME <>", value, "consultQuestionModifyTime");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionModifyTimeGreaterThan(String value) {
            addCriterion("CONSULT_QUESTION_MODIFY_TIME >", value, "consultQuestionModifyTime");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionModifyTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CONSULT_QUESTION_MODIFY_TIME >=", value, "consultQuestionModifyTime");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionModifyTimeLessThan(String value) {
            addCriterion("CONSULT_QUESTION_MODIFY_TIME <", value, "consultQuestionModifyTime");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionModifyTimeLessThanOrEqualTo(String value) {
            addCriterion("CONSULT_QUESTION_MODIFY_TIME <=", value, "consultQuestionModifyTime");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionModifyTimeLike(String value) {
            addCriterion("CONSULT_QUESTION_MODIFY_TIME like", value, "consultQuestionModifyTime");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionModifyTimeNotLike(String value) {
            addCriterion("CONSULT_QUESTION_MODIFY_TIME not like", value, "consultQuestionModifyTime");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionModifyTimeIn(List<String> values) {
            addCriterion("CONSULT_QUESTION_MODIFY_TIME in", values, "consultQuestionModifyTime");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionModifyTimeNotIn(List<String> values) {
            addCriterion("CONSULT_QUESTION_MODIFY_TIME not in", values, "consultQuestionModifyTime");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionModifyTimeBetween(String value1, String value2) {
            addCriterion("CONSULT_QUESTION_MODIFY_TIME between", value1, value2, "consultQuestionModifyTime");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionModifyTimeNotBetween(String value1, String value2) {
            addCriterion("CONSULT_QUESTION_MODIFY_TIME not between", value1, value2, "consultQuestionModifyTime");
            return (Criteria) this;
        }

        public Criteria andConsultVisitNumberIsNull() {
            addCriterion("CONSULT_VISIT_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andConsultVisitNumberIsNotNull() {
            addCriterion("CONSULT_VISIT_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andConsultVisitNumberEqualTo(String value) {
            addCriterion("CONSULT_VISIT_NUMBER =", value, "consultVisitNumber");
            return (Criteria) this;
        }

        public Criteria andConsultVisitNumberNotEqualTo(String value) {
            addCriterion("CONSULT_VISIT_NUMBER <>", value, "consultVisitNumber");
            return (Criteria) this;
        }

        public Criteria andConsultVisitNumberGreaterThan(String value) {
            addCriterion("CONSULT_VISIT_NUMBER >", value, "consultVisitNumber");
            return (Criteria) this;
        }

        public Criteria andConsultVisitNumberGreaterThanOrEqualTo(String value) {
            addCriterion("CONSULT_VISIT_NUMBER >=", value, "consultVisitNumber");
            return (Criteria) this;
        }

        public Criteria andConsultVisitNumberLessThan(String value) {
            addCriterion("CONSULT_VISIT_NUMBER <", value, "consultVisitNumber");
            return (Criteria) this;
        }

        public Criteria andConsultVisitNumberLessThanOrEqualTo(String value) {
            addCriterion("CONSULT_VISIT_NUMBER <=", value, "consultVisitNumber");
            return (Criteria) this;
        }

        public Criteria andConsultVisitNumberLike(String value) {
            addCriterion("CONSULT_VISIT_NUMBER like", value, "consultVisitNumber");
            return (Criteria) this;
        }

        public Criteria andConsultVisitNumberNotLike(String value) {
            addCriterion("CONSULT_VISIT_NUMBER not like", value, "consultVisitNumber");
            return (Criteria) this;
        }

        public Criteria andConsultVisitNumberIn(List<String> values) {
            addCriterion("CONSULT_VISIT_NUMBER in", values, "consultVisitNumber");
            return (Criteria) this;
        }

        public Criteria andConsultVisitNumberNotIn(List<String> values) {
            addCriterion("CONSULT_VISIT_NUMBER not in", values, "consultVisitNumber");
            return (Criteria) this;
        }

        public Criteria andConsultVisitNumberBetween(String value1, String value2) {
            addCriterion("CONSULT_VISIT_NUMBER between", value1, value2, "consultVisitNumber");
            return (Criteria) this;
        }

        public Criteria andConsultVisitNumberNotBetween(String value1, String value2) {
            addCriterion("CONSULT_VISIT_NUMBER not between", value1, value2, "consultVisitNumber");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleIdIsNull() {
            addCriterion("CONSULT_QUESTION_ROLE_ID is null");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleIdIsNotNull() {
            addCriterion("CONSULT_QUESTION_ROLE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleIdEqualTo(String value) {
            addCriterion("CONSULT_QUESTION_ROLE_ID =", value, "consultQuestionRoleId");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleIdNotEqualTo(String value) {
            addCriterion("CONSULT_QUESTION_ROLE_ID <>", value, "consultQuestionRoleId");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleIdGreaterThan(String value) {
            addCriterion("CONSULT_QUESTION_ROLE_ID >", value, "consultQuestionRoleId");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleIdGreaterThanOrEqualTo(String value) {
            addCriterion("CONSULT_QUESTION_ROLE_ID >=", value, "consultQuestionRoleId");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleIdLessThan(String value) {
            addCriterion("CONSULT_QUESTION_ROLE_ID <", value, "consultQuestionRoleId");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleIdLessThanOrEqualTo(String value) {
            addCriterion("CONSULT_QUESTION_ROLE_ID <=", value, "consultQuestionRoleId");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleIdLike(String value) {
            addCriterion("CONSULT_QUESTION_ROLE_ID like", value, "consultQuestionRoleId");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleIdNotLike(String value) {
            addCriterion("CONSULT_QUESTION_ROLE_ID not like", value, "consultQuestionRoleId");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleIdIn(List<String> values) {
            addCriterion("CONSULT_QUESTION_ROLE_ID in", values, "consultQuestionRoleId");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleIdNotIn(List<String> values) {
            addCriterion("CONSULT_QUESTION_ROLE_ID not in", values, "consultQuestionRoleId");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleIdBetween(String value1, String value2) {
            addCriterion("CONSULT_QUESTION_ROLE_ID between", value1, value2, "consultQuestionRoleId");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleIdNotBetween(String value1, String value2) {
            addCriterion("CONSULT_QUESTION_ROLE_ID not between", value1, value2, "consultQuestionRoleId");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleNameIsNull() {
            addCriterion("CONSULT_QUESTION_ROLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleNameIsNotNull() {
            addCriterion("CONSULT_QUESTION_ROLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleNameEqualTo(String value) {
            addCriterion("CONSULT_QUESTION_ROLE_NAME =", value, "consultQuestionRoleName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleNameNotEqualTo(String value) {
            addCriterion("CONSULT_QUESTION_ROLE_NAME <>", value, "consultQuestionRoleName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleNameGreaterThan(String value) {
            addCriterion("CONSULT_QUESTION_ROLE_NAME >", value, "consultQuestionRoleName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONSULT_QUESTION_ROLE_NAME >=", value, "consultQuestionRoleName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleNameLessThan(String value) {
            addCriterion("CONSULT_QUESTION_ROLE_NAME <", value, "consultQuestionRoleName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleNameLessThanOrEqualTo(String value) {
            addCriterion("CONSULT_QUESTION_ROLE_NAME <=", value, "consultQuestionRoleName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleNameLike(String value) {
            addCriterion("CONSULT_QUESTION_ROLE_NAME like", value, "consultQuestionRoleName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleNameNotLike(String value) {
            addCriterion("CONSULT_QUESTION_ROLE_NAME not like", value, "consultQuestionRoleName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleNameIn(List<String> values) {
            addCriterion("CONSULT_QUESTION_ROLE_NAME in", values, "consultQuestionRoleName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleNameNotIn(List<String> values) {
            addCriterion("CONSULT_QUESTION_ROLE_NAME not in", values, "consultQuestionRoleName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleNameBetween(String value1, String value2) {
            addCriterion("CONSULT_QUESTION_ROLE_NAME between", value1, value2, "consultQuestionRoleName");
            return (Criteria) this;
        }

        public Criteria andConsultQuestionRoleNameNotBetween(String value1, String value2) {
            addCriterion("CONSULT_QUESTION_ROLE_NAME not between", value1, value2, "consultQuestionRoleName");
            return (Criteria) this;
        }

        public Criteria andConsultTypeIdIsNull() {
            addCriterion("CONSULT_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andConsultTypeIdIsNotNull() {
            addCriterion("CONSULT_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andConsultTypeIdEqualTo(String value) {
            addCriterion("CONSULT_TYPE_ID =", value, "consultTypeId");
            return (Criteria) this;
        }

        public Criteria andConsultTypeIdNotEqualTo(String value) {
            addCriterion("CONSULT_TYPE_ID <>", value, "consultTypeId");
            return (Criteria) this;
        }

        public Criteria andConsultTypeIdGreaterThan(String value) {
            addCriterion("CONSULT_TYPE_ID >", value, "consultTypeId");
            return (Criteria) this;
        }

        public Criteria andConsultTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("CONSULT_TYPE_ID >=", value, "consultTypeId");
            return (Criteria) this;
        }

        public Criteria andConsultTypeIdLessThan(String value) {
            addCriterion("CONSULT_TYPE_ID <", value, "consultTypeId");
            return (Criteria) this;
        }

        public Criteria andConsultTypeIdLessThanOrEqualTo(String value) {
            addCriterion("CONSULT_TYPE_ID <=", value, "consultTypeId");
            return (Criteria) this;
        }

        public Criteria andConsultTypeIdLike(String value) {
            addCriterion("CONSULT_TYPE_ID like", value, "consultTypeId");
            return (Criteria) this;
        }

        public Criteria andConsultTypeIdNotLike(String value) {
            addCriterion("CONSULT_TYPE_ID not like", value, "consultTypeId");
            return (Criteria) this;
        }

        public Criteria andConsultTypeIdIn(List<String> values) {
            addCriterion("CONSULT_TYPE_ID in", values, "consultTypeId");
            return (Criteria) this;
        }

        public Criteria andConsultTypeIdNotIn(List<String> values) {
            addCriterion("CONSULT_TYPE_ID not in", values, "consultTypeId");
            return (Criteria) this;
        }

        public Criteria andConsultTypeIdBetween(String value1, String value2) {
            addCriterion("CONSULT_TYPE_ID between", value1, value2, "consultTypeId");
            return (Criteria) this;
        }

        public Criteria andConsultTypeIdNotBetween(String value1, String value2) {
            addCriterion("CONSULT_TYPE_ID not between", value1, value2, "consultTypeId");
            return (Criteria) this;
        }

        public Criteria andConsultTypeNameIsNull() {
            addCriterion("CONSULT_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andConsultTypeNameIsNotNull() {
            addCriterion("CONSULT_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andConsultTypeNameEqualTo(String value) {
            addCriterion("CONSULT_TYPE_NAME =", value, "consultTypeName");
            return (Criteria) this;
        }

        public Criteria andConsultTypeNameNotEqualTo(String value) {
            addCriterion("CONSULT_TYPE_NAME <>", value, "consultTypeName");
            return (Criteria) this;
        }

        public Criteria andConsultTypeNameGreaterThan(String value) {
            addCriterion("CONSULT_TYPE_NAME >", value, "consultTypeName");
            return (Criteria) this;
        }

        public Criteria andConsultTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONSULT_TYPE_NAME >=", value, "consultTypeName");
            return (Criteria) this;
        }

        public Criteria andConsultTypeNameLessThan(String value) {
            addCriterion("CONSULT_TYPE_NAME <", value, "consultTypeName");
            return (Criteria) this;
        }

        public Criteria andConsultTypeNameLessThanOrEqualTo(String value) {
            addCriterion("CONSULT_TYPE_NAME <=", value, "consultTypeName");
            return (Criteria) this;
        }

        public Criteria andConsultTypeNameLike(String value) {
            addCriterion("CONSULT_TYPE_NAME like", value, "consultTypeName");
            return (Criteria) this;
        }

        public Criteria andConsultTypeNameNotLike(String value) {
            addCriterion("CONSULT_TYPE_NAME not like", value, "consultTypeName");
            return (Criteria) this;
        }

        public Criteria andConsultTypeNameIn(List<String> values) {
            addCriterion("CONSULT_TYPE_NAME in", values, "consultTypeName");
            return (Criteria) this;
        }

        public Criteria andConsultTypeNameNotIn(List<String> values) {
            addCriterion("CONSULT_TYPE_NAME not in", values, "consultTypeName");
            return (Criteria) this;
        }

        public Criteria andConsultTypeNameBetween(String value1, String value2) {
            addCriterion("CONSULT_TYPE_NAME between", value1, value2, "consultTypeName");
            return (Criteria) this;
        }

        public Criteria andConsultTypeNameNotBetween(String value1, String value2) {
            addCriterion("CONSULT_TYPE_NAME not between", value1, value2, "consultTypeName");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonIdIsNull() {
            addCriterion("CONSULT_TYPE_SON_ID is null");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonIdIsNotNull() {
            addCriterion("CONSULT_TYPE_SON_ID is not null");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonIdEqualTo(String value) {
            addCriterion("CONSULT_TYPE_SON_ID =", value, "consultTypeSonId");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonIdNotEqualTo(String value) {
            addCriterion("CONSULT_TYPE_SON_ID <>", value, "consultTypeSonId");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonIdGreaterThan(String value) {
            addCriterion("CONSULT_TYPE_SON_ID >", value, "consultTypeSonId");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonIdGreaterThanOrEqualTo(String value) {
            addCriterion("CONSULT_TYPE_SON_ID >=", value, "consultTypeSonId");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonIdLessThan(String value) {
            addCriterion("CONSULT_TYPE_SON_ID <", value, "consultTypeSonId");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonIdLessThanOrEqualTo(String value) {
            addCriterion("CONSULT_TYPE_SON_ID <=", value, "consultTypeSonId");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonIdLike(String value) {
            addCriterion("CONSULT_TYPE_SON_ID like", value, "consultTypeSonId");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonIdNotLike(String value) {
            addCriterion("CONSULT_TYPE_SON_ID not like", value, "consultTypeSonId");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonIdIn(List<String> values) {
            addCriterion("CONSULT_TYPE_SON_ID in", values, "consultTypeSonId");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonIdNotIn(List<String> values) {
            addCriterion("CONSULT_TYPE_SON_ID not in", values, "consultTypeSonId");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonIdBetween(String value1, String value2) {
            addCriterion("CONSULT_TYPE_SON_ID between", value1, value2, "consultTypeSonId");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonIdNotBetween(String value1, String value2) {
            addCriterion("CONSULT_TYPE_SON_ID not between", value1, value2, "consultTypeSonId");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonNameIsNull() {
            addCriterion("CONSULT_TYPE_SON_NAME is null");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonNameIsNotNull() {
            addCriterion("CONSULT_TYPE_SON_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonNameEqualTo(String value) {
            addCriterion("CONSULT_TYPE_SON_NAME =", value, "consultTypeSonName");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonNameNotEqualTo(String value) {
            addCriterion("CONSULT_TYPE_SON_NAME <>", value, "consultTypeSonName");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonNameGreaterThan(String value) {
            addCriterion("CONSULT_TYPE_SON_NAME >", value, "consultTypeSonName");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONSULT_TYPE_SON_NAME >=", value, "consultTypeSonName");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonNameLessThan(String value) {
            addCriterion("CONSULT_TYPE_SON_NAME <", value, "consultTypeSonName");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonNameLessThanOrEqualTo(String value) {
            addCriterion("CONSULT_TYPE_SON_NAME <=", value, "consultTypeSonName");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonNameLike(String value) {
            addCriterion("CONSULT_TYPE_SON_NAME like", value, "consultTypeSonName");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonNameNotLike(String value) {
            addCriterion("CONSULT_TYPE_SON_NAME not like", value, "consultTypeSonName");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonNameIn(List<String> values) {
            addCriterion("CONSULT_TYPE_SON_NAME in", values, "consultTypeSonName");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonNameNotIn(List<String> values) {
            addCriterion("CONSULT_TYPE_SON_NAME not in", values, "consultTypeSonName");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonNameBetween(String value1, String value2) {
            addCriterion("CONSULT_TYPE_SON_NAME between", value1, value2, "consultTypeSonName");
            return (Criteria) this;
        }

        public Criteria andConsultTypeSonNameNotBetween(String value1, String value2) {
            addCriterion("CONSULT_TYPE_SON_NAME not between", value1, value2, "consultTypeSonName");
            return (Criteria) this;
        }

        public Criteria andIsAnswerIsNull() {
            addCriterion("IS_ANSWER is null");
            return (Criteria) this;
        }

        public Criteria andIsAnswerIsNotNull() {
            addCriterion("IS_ANSWER is not null");
            return (Criteria) this;
        }

        public Criteria andIsAnswerEqualTo(String value) {
            addCriterion("IS_ANSWER =", value, "isAnswer");
            return (Criteria) this;
        }

        public Criteria andIsAnswerNotEqualTo(String value) {
            addCriterion("IS_ANSWER <>", value, "isAnswer");
            return (Criteria) this;
        }

        public Criteria andIsAnswerGreaterThan(String value) {
            addCriterion("IS_ANSWER >", value, "isAnswer");
            return (Criteria) this;
        }

        public Criteria andIsAnswerGreaterThanOrEqualTo(String value) {
            addCriterion("IS_ANSWER >=", value, "isAnswer");
            return (Criteria) this;
        }

        public Criteria andIsAnswerLessThan(String value) {
            addCriterion("IS_ANSWER <", value, "isAnswer");
            return (Criteria) this;
        }

        public Criteria andIsAnswerLessThanOrEqualTo(String value) {
            addCriterion("IS_ANSWER <=", value, "isAnswer");
            return (Criteria) this;
        }

        public Criteria andIsAnswerLike(String value) {
            addCriterion("IS_ANSWER like", value, "isAnswer");
            return (Criteria) this;
        }

        public Criteria andIsAnswerNotLike(String value) {
            addCriterion("IS_ANSWER not like", value, "isAnswer");
            return (Criteria) this;
        }

        public Criteria andIsAnswerIn(List<String> values) {
            addCriterion("IS_ANSWER in", values, "isAnswer");
            return (Criteria) this;
        }

        public Criteria andIsAnswerNotIn(List<String> values) {
            addCriterion("IS_ANSWER not in", values, "isAnswer");
            return (Criteria) this;
        }

        public Criteria andIsAnswerBetween(String value1, String value2) {
            addCriterion("IS_ANSWER between", value1, value2, "isAnswer");
            return (Criteria) this;
        }

        public Criteria andIsAnswerNotBetween(String value1, String value2) {
            addCriterion("IS_ANSWER not between", value1, value2, "isAnswer");
            return (Criteria) this;
        }

        public Criteria andIsPolicyIsNull() {
            addCriterion("IS_POLICY is null");
            return (Criteria) this;
        }

        public Criteria andIsPolicyIsNotNull() {
            addCriterion("IS_POLICY is not null");
            return (Criteria) this;
        }

        public Criteria andIsPolicyEqualTo(String value) {
            addCriterion("IS_POLICY =", value, "isPolicy");
            return (Criteria) this;
        }

        public Criteria andIsPolicyNotEqualTo(String value) {
            addCriterion("IS_POLICY <>", value, "isPolicy");
            return (Criteria) this;
        }

        public Criteria andIsPolicyGreaterThan(String value) {
            addCriterion("IS_POLICY >", value, "isPolicy");
            return (Criteria) this;
        }

        public Criteria andIsPolicyGreaterThanOrEqualTo(String value) {
            addCriterion("IS_POLICY >=", value, "isPolicy");
            return (Criteria) this;
        }

        public Criteria andIsPolicyLessThan(String value) {
            addCriterion("IS_POLICY <", value, "isPolicy");
            return (Criteria) this;
        }

        public Criteria andIsPolicyLessThanOrEqualTo(String value) {
            addCriterion("IS_POLICY <=", value, "isPolicy");
            return (Criteria) this;
        }

        public Criteria andIsPolicyLike(String value) {
            addCriterion("IS_POLICY like", value, "isPolicy");
            return (Criteria) this;
        }

        public Criteria andIsPolicyNotLike(String value) {
            addCriterion("IS_POLICY not like", value, "isPolicy");
            return (Criteria) this;
        }

        public Criteria andIsPolicyIn(List<String> values) {
            addCriterion("IS_POLICY in", values, "isPolicy");
            return (Criteria) this;
        }

        public Criteria andIsPolicyNotIn(List<String> values) {
            addCriterion("IS_POLICY not in", values, "isPolicy");
            return (Criteria) this;
        }

        public Criteria andIsPolicyBetween(String value1, String value2) {
            addCriterion("IS_POLICY between", value1, value2, "isPolicy");
            return (Criteria) this;
        }

        public Criteria andIsPolicyNotBetween(String value1, String value2) {
            addCriterion("IS_POLICY not between", value1, value2, "isPolicy");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdIsNull() {
            addCriterion("ORG_CITY_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdIsNotNull() {
            addCriterion("ORG_CITY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdEqualTo(String value) {
            addCriterion("ORG_CITY_ID =", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdNotEqualTo(String value) {
            addCriterion("ORG_CITY_ID <>", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdGreaterThan(String value) {
            addCriterion("ORG_CITY_ID >", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CITY_ID >=", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdLessThan(String value) {
            addCriterion("ORG_CITY_ID <", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_CITY_ID <=", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdLike(String value) {
            addCriterion("ORG_CITY_ID like", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdNotLike(String value) {
            addCriterion("ORG_CITY_ID not like", value, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdIn(List<String> values) {
            addCriterion("ORG_CITY_ID in", values, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdNotIn(List<String> values) {
            addCriterion("ORG_CITY_ID not in", values, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdBetween(String value1, String value2) {
            addCriterion("ORG_CITY_ID between", value1, value2, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityIdNotBetween(String value1, String value2) {
            addCriterion("ORG_CITY_ID not between", value1, value2, "orgCityId");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameIsNull() {
            addCriterion("ORG_CITY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameIsNotNull() {
            addCriterion("ORG_CITY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameEqualTo(String value) {
            addCriterion("ORG_CITY_NAME =", value, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameNotEqualTo(String value) {
            addCriterion("ORG_CITY_NAME <>", value, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameGreaterThan(String value) {
            addCriterion("ORG_CITY_NAME >", value, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CITY_NAME >=", value, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameLessThan(String value) {
            addCriterion("ORG_CITY_NAME <", value, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_CITY_NAME <=", value, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameLike(String value) {
            addCriterion("ORG_CITY_NAME like", value, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameNotLike(String value) {
            addCriterion("ORG_CITY_NAME not like", value, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameIn(List<String> values) {
            addCriterion("ORG_CITY_NAME in", values, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameNotIn(List<String> values) {
            addCriterion("ORG_CITY_NAME not in", values, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameBetween(String value1, String value2) {
            addCriterion("ORG_CITY_NAME between", value1, value2, "orgCityName");
            return (Criteria) this;
        }

        public Criteria andOrgCityNameNotBetween(String value1, String value2) {
            addCriterion("ORG_CITY_NAME not between", value1, value2, "orgCityName");
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

        public Criteria andChargeNameIsNull() {
            addCriterion("CHARGE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andChargeNameIsNotNull() {
            addCriterion("CHARGE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andChargeNameEqualTo(String value) {
            addCriterion("CHARGE_NAME =", value, "chargeName");
            return (Criteria) this;
        }

        public Criteria andChargeNameNotEqualTo(String value) {
            addCriterion("CHARGE_NAME <>", value, "chargeName");
            return (Criteria) this;
        }

        public Criteria andChargeNameGreaterThan(String value) {
            addCriterion("CHARGE_NAME >", value, "chargeName");
            return (Criteria) this;
        }

        public Criteria andChargeNameGreaterThanOrEqualTo(String value) {
            addCriterion("CHARGE_NAME >=", value, "chargeName");
            return (Criteria) this;
        }

        public Criteria andChargeNameLessThan(String value) {
            addCriterion("CHARGE_NAME <", value, "chargeName");
            return (Criteria) this;
        }

        public Criteria andChargeNameLessThanOrEqualTo(String value) {
            addCriterion("CHARGE_NAME <=", value, "chargeName");
            return (Criteria) this;
        }

        public Criteria andChargeNameLike(String value) {
            addCriterion("CHARGE_NAME like", value, "chargeName");
            return (Criteria) this;
        }

        public Criteria andChargeNameNotLike(String value) {
            addCriterion("CHARGE_NAME not like", value, "chargeName");
            return (Criteria) this;
        }

        public Criteria andChargeNameIn(List<String> values) {
            addCriterion("CHARGE_NAME in", values, "chargeName");
            return (Criteria) this;
        }

        public Criteria andChargeNameNotIn(List<String> values) {
            addCriterion("CHARGE_NAME not in", values, "chargeName");
            return (Criteria) this;
        }

        public Criteria andChargeNameBetween(String value1, String value2) {
            addCriterion("CHARGE_NAME between", value1, value2, "chargeName");
            return (Criteria) this;
        }

        public Criteria andChargeNameNotBetween(String value1, String value2) {
            addCriterion("CHARGE_NAME not between", value1, value2, "chargeName");
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