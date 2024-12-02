package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResTrainingOpinionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResTrainingOpinionExample() {
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

        public Criteria andTrainingOpinionFlowIsNull() {
            addCriterion("TRAINING_OPINION_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andTrainingOpinionFlowIsNotNull() {
            addCriterion("TRAINING_OPINION_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingOpinionFlowEqualTo(String value) {
            addCriterion("TRAINING_OPINION_FLOW =", value, "trainingOpinionFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingOpinionFlowNotEqualTo(String value) {
            addCriterion("TRAINING_OPINION_FLOW <>", value, "trainingOpinionFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingOpinionFlowGreaterThan(String value) {
            addCriterion("TRAINING_OPINION_FLOW >", value, "trainingOpinionFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingOpinionFlowGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_OPINION_FLOW >=", value, "trainingOpinionFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingOpinionFlowLessThan(String value) {
            addCriterion("TRAINING_OPINION_FLOW <", value, "trainingOpinionFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingOpinionFlowLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_OPINION_FLOW <=", value, "trainingOpinionFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingOpinionFlowLike(String value) {
            addCriterion("TRAINING_OPINION_FLOW like", value, "trainingOpinionFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingOpinionFlowNotLike(String value) {
            addCriterion("TRAINING_OPINION_FLOW not like", value, "trainingOpinionFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingOpinionFlowIn(List<String> values) {
            addCriterion("TRAINING_OPINION_FLOW in", values, "trainingOpinionFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingOpinionFlowNotIn(List<String> values) {
            addCriterion("TRAINING_OPINION_FLOW not in", values, "trainingOpinionFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingOpinionFlowBetween(String value1, String value2) {
            addCriterion("TRAINING_OPINION_FLOW between", value1, value2, "trainingOpinionFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingOpinionFlowNotBetween(String value1, String value2) {
            addCriterion("TRAINING_OPINION_FLOW not between", value1, value2, "trainingOpinionFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionUserFlowIsNull() {
            addCriterion("OPINION_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andOpinionUserFlowIsNotNull() {
            addCriterion("OPINION_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andOpinionUserFlowEqualTo(String value) {
            addCriterion("OPINION_USER_FLOW =", value, "opinionUserFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionUserFlowNotEqualTo(String value) {
            addCriterion("OPINION_USER_FLOW <>", value, "opinionUserFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionUserFlowGreaterThan(String value) {
            addCriterion("OPINION_USER_FLOW >", value, "opinionUserFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("OPINION_USER_FLOW >=", value, "opinionUserFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionUserFlowLessThan(String value) {
            addCriterion("OPINION_USER_FLOW <", value, "opinionUserFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionUserFlowLessThanOrEqualTo(String value) {
            addCriterion("OPINION_USER_FLOW <=", value, "opinionUserFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionUserFlowLike(String value) {
            addCriterion("OPINION_USER_FLOW like", value, "opinionUserFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionUserFlowNotLike(String value) {
            addCriterion("OPINION_USER_FLOW not like", value, "opinionUserFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionUserFlowIn(List<String> values) {
            addCriterion("OPINION_USER_FLOW in", values, "opinionUserFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionUserFlowNotIn(List<String> values) {
            addCriterion("OPINION_USER_FLOW not in", values, "opinionUserFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionUserFlowBetween(String value1, String value2) {
            addCriterion("OPINION_USER_FLOW between", value1, value2, "opinionUserFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionUserFlowNotBetween(String value1, String value2) {
            addCriterion("OPINION_USER_FLOW not between", value1, value2, "opinionUserFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionUserNameIsNull() {
            addCriterion("OPINION_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOpinionUserNameIsNotNull() {
            addCriterion("OPINION_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOpinionUserNameEqualTo(String value) {
            addCriterion("OPINION_USER_NAME =", value, "opinionUserName");
            return (Criteria) this;
        }

        public Criteria andOpinionUserNameNotEqualTo(String value) {
            addCriterion("OPINION_USER_NAME <>", value, "opinionUserName");
            return (Criteria) this;
        }

        public Criteria andOpinionUserNameGreaterThan(String value) {
            addCriterion("OPINION_USER_NAME >", value, "opinionUserName");
            return (Criteria) this;
        }

        public Criteria andOpinionUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("OPINION_USER_NAME >=", value, "opinionUserName");
            return (Criteria) this;
        }

        public Criteria andOpinionUserNameLessThan(String value) {
            addCriterion("OPINION_USER_NAME <", value, "opinionUserName");
            return (Criteria) this;
        }

        public Criteria andOpinionUserNameLessThanOrEqualTo(String value) {
            addCriterion("OPINION_USER_NAME <=", value, "opinionUserName");
            return (Criteria) this;
        }

        public Criteria andOpinionUserNameLike(String value) {
            addCriterion("OPINION_USER_NAME like", value, "opinionUserName");
            return (Criteria) this;
        }

        public Criteria andOpinionUserNameNotLike(String value) {
            addCriterion("OPINION_USER_NAME not like", value, "opinionUserName");
            return (Criteria) this;
        }

        public Criteria andOpinionUserNameIn(List<String> values) {
            addCriterion("OPINION_USER_NAME in", values, "opinionUserName");
            return (Criteria) this;
        }

        public Criteria andOpinionUserNameNotIn(List<String> values) {
            addCriterion("OPINION_USER_NAME not in", values, "opinionUserName");
            return (Criteria) this;
        }

        public Criteria andOpinionUserNameBetween(String value1, String value2) {
            addCriterion("OPINION_USER_NAME between", value1, value2, "opinionUserName");
            return (Criteria) this;
        }

        public Criteria andOpinionUserNameNotBetween(String value1, String value2) {
            addCriterion("OPINION_USER_NAME not between", value1, value2, "opinionUserName");
            return (Criteria) this;
        }

        public Criteria andOpinionUserContentIsNull() {
            addCriterion("OPINION_USER_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andOpinionUserContentIsNotNull() {
            addCriterion("OPINION_USER_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andOpinionUserContentEqualTo(String value) {
            addCriterion("OPINION_USER_CONTENT =", value, "opinionUserContent");
            return (Criteria) this;
        }

        public Criteria andOpinionUserContentNotEqualTo(String value) {
            addCriterion("OPINION_USER_CONTENT <>", value, "opinionUserContent");
            return (Criteria) this;
        }

        public Criteria andOpinionUserContentGreaterThan(String value) {
            addCriterion("OPINION_USER_CONTENT >", value, "opinionUserContent");
            return (Criteria) this;
        }

        public Criteria andOpinionUserContentGreaterThanOrEqualTo(String value) {
            addCriterion("OPINION_USER_CONTENT >=", value, "opinionUserContent");
            return (Criteria) this;
        }

        public Criteria andOpinionUserContentLessThan(String value) {
            addCriterion("OPINION_USER_CONTENT <", value, "opinionUserContent");
            return (Criteria) this;
        }

        public Criteria andOpinionUserContentLessThanOrEqualTo(String value) {
            addCriterion("OPINION_USER_CONTENT <=", value, "opinionUserContent");
            return (Criteria) this;
        }

        public Criteria andOpinionUserContentLike(String value) {
            addCriterion("OPINION_USER_CONTENT like", value, "opinionUserContent");
            return (Criteria) this;
        }

        public Criteria andOpinionUserContentNotLike(String value) {
            addCriterion("OPINION_USER_CONTENT not like", value, "opinionUserContent");
            return (Criteria) this;
        }

        public Criteria andOpinionUserContentIn(List<String> values) {
            addCriterion("OPINION_USER_CONTENT in", values, "opinionUserContent");
            return (Criteria) this;
        }

        public Criteria andOpinionUserContentNotIn(List<String> values) {
            addCriterion("OPINION_USER_CONTENT not in", values, "opinionUserContent");
            return (Criteria) this;
        }

        public Criteria andOpinionUserContentBetween(String value1, String value2) {
            addCriterion("OPINION_USER_CONTENT between", value1, value2, "opinionUserContent");
            return (Criteria) this;
        }

        public Criteria andOpinionUserContentNotBetween(String value1, String value2) {
            addCriterion("OPINION_USER_CONTENT not between", value1, value2, "opinionUserContent");
            return (Criteria) this;
        }

        public Criteria andEvaTimeIsNull() {
            addCriterion("EVA_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEvaTimeIsNotNull() {
            addCriterion("EVA_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEvaTimeEqualTo(String value) {
            addCriterion("EVA_TIME =", value, "evaTime");
            return (Criteria) this;
        }

        public Criteria andEvaTimeNotEqualTo(String value) {
            addCriterion("EVA_TIME <>", value, "evaTime");
            return (Criteria) this;
        }

        public Criteria andEvaTimeGreaterThan(String value) {
            addCriterion("EVA_TIME >", value, "evaTime");
            return (Criteria) this;
        }

        public Criteria andEvaTimeGreaterThanOrEqualTo(String value) {
            addCriterion("EVA_TIME >=", value, "evaTime");
            return (Criteria) this;
        }

        public Criteria andEvaTimeLessThan(String value) {
            addCriterion("EVA_TIME <", value, "evaTime");
            return (Criteria) this;
        }

        public Criteria andEvaTimeLessThanOrEqualTo(String value) {
            addCriterion("EVA_TIME <=", value, "evaTime");
            return (Criteria) this;
        }

        public Criteria andEvaTimeLike(String value) {
            addCriterion("EVA_TIME like", value, "evaTime");
            return (Criteria) this;
        }

        public Criteria andEvaTimeNotLike(String value) {
            addCriterion("EVA_TIME not like", value, "evaTime");
            return (Criteria) this;
        }

        public Criteria andEvaTimeIn(List<String> values) {
            addCriterion("EVA_TIME in", values, "evaTime");
            return (Criteria) this;
        }

        public Criteria andEvaTimeNotIn(List<String> values) {
            addCriterion("EVA_TIME not in", values, "evaTime");
            return (Criteria) this;
        }

        public Criteria andEvaTimeBetween(String value1, String value2) {
            addCriterion("EVA_TIME between", value1, value2, "evaTime");
            return (Criteria) this;
        }

        public Criteria andEvaTimeNotBetween(String value1, String value2) {
            addCriterion("EVA_TIME not between", value1, value2, "evaTime");
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

        public Criteria andOpinionReplyUserFlowIsNull() {
            addCriterion("OPINION_REPLY_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyUserFlowIsNotNull() {
            addCriterion("OPINION_REPLY_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyUserFlowEqualTo(String value) {
            addCriterion("OPINION_REPLY_USER_FLOW =", value, "opinionReplyUserFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyUserFlowNotEqualTo(String value) {
            addCriterion("OPINION_REPLY_USER_FLOW <>", value, "opinionReplyUserFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyUserFlowGreaterThan(String value) {
            addCriterion("OPINION_REPLY_USER_FLOW >", value, "opinionReplyUserFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("OPINION_REPLY_USER_FLOW >=", value, "opinionReplyUserFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyUserFlowLessThan(String value) {
            addCriterion("OPINION_REPLY_USER_FLOW <", value, "opinionReplyUserFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyUserFlowLessThanOrEqualTo(String value) {
            addCriterion("OPINION_REPLY_USER_FLOW <=", value, "opinionReplyUserFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyUserFlowLike(String value) {
            addCriterion("OPINION_REPLY_USER_FLOW like", value, "opinionReplyUserFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyUserFlowNotLike(String value) {
            addCriterion("OPINION_REPLY_USER_FLOW not like", value, "opinionReplyUserFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyUserFlowIn(List<String> values) {
            addCriterion("OPINION_REPLY_USER_FLOW in", values, "opinionReplyUserFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyUserFlowNotIn(List<String> values) {
            addCriterion("OPINION_REPLY_USER_FLOW not in", values, "opinionReplyUserFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyUserFlowBetween(String value1, String value2) {
            addCriterion("OPINION_REPLY_USER_FLOW between", value1, value2, "opinionReplyUserFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyUserFlowNotBetween(String value1, String value2) {
            addCriterion("OPINION_REPLY_USER_FLOW not between", value1, value2, "opinionReplyUserFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyNameIsNull() {
            addCriterion("OPINION_REPLY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyNameIsNotNull() {
            addCriterion("OPINION_REPLY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyNameEqualTo(String value) {
            addCriterion("OPINION_REPLY_NAME =", value, "opinionReplyName");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyNameNotEqualTo(String value) {
            addCriterion("OPINION_REPLY_NAME <>", value, "opinionReplyName");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyNameGreaterThan(String value) {
            addCriterion("OPINION_REPLY_NAME >", value, "opinionReplyName");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyNameGreaterThanOrEqualTo(String value) {
            addCriterion("OPINION_REPLY_NAME >=", value, "opinionReplyName");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyNameLessThan(String value) {
            addCriterion("OPINION_REPLY_NAME <", value, "opinionReplyName");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyNameLessThanOrEqualTo(String value) {
            addCriterion("OPINION_REPLY_NAME <=", value, "opinionReplyName");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyNameLike(String value) {
            addCriterion("OPINION_REPLY_NAME like", value, "opinionReplyName");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyNameNotLike(String value) {
            addCriterion("OPINION_REPLY_NAME not like", value, "opinionReplyName");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyNameIn(List<String> values) {
            addCriterion("OPINION_REPLY_NAME in", values, "opinionReplyName");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyNameNotIn(List<String> values) {
            addCriterion("OPINION_REPLY_NAME not in", values, "opinionReplyName");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyNameBetween(String value1, String value2) {
            addCriterion("OPINION_REPLY_NAME between", value1, value2, "opinionReplyName");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyNameNotBetween(String value1, String value2) {
            addCriterion("OPINION_REPLY_NAME not between", value1, value2, "opinionReplyName");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyContentIsNull() {
            addCriterion("OPINION_REPLY_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyContentIsNotNull() {
            addCriterion("OPINION_REPLY_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyContentEqualTo(String value) {
            addCriterion("OPINION_REPLY_CONTENT =", value, "opinionReplyContent");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyContentNotEqualTo(String value) {
            addCriterion("OPINION_REPLY_CONTENT <>", value, "opinionReplyContent");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyContentGreaterThan(String value) {
            addCriterion("OPINION_REPLY_CONTENT >", value, "opinionReplyContent");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyContentGreaterThanOrEqualTo(String value) {
            addCriterion("OPINION_REPLY_CONTENT >=", value, "opinionReplyContent");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyContentLessThan(String value) {
            addCriterion("OPINION_REPLY_CONTENT <", value, "opinionReplyContent");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyContentLessThanOrEqualTo(String value) {
            addCriterion("OPINION_REPLY_CONTENT <=", value, "opinionReplyContent");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyContentLike(String value) {
            addCriterion("OPINION_REPLY_CONTENT like", value, "opinionReplyContent");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyContentNotLike(String value) {
            addCriterion("OPINION_REPLY_CONTENT not like", value, "opinionReplyContent");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyContentIn(List<String> values) {
            addCriterion("OPINION_REPLY_CONTENT in", values, "opinionReplyContent");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyContentNotIn(List<String> values) {
            addCriterion("OPINION_REPLY_CONTENT not in", values, "opinionReplyContent");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyContentBetween(String value1, String value2) {
            addCriterion("OPINION_REPLY_CONTENT between", value1, value2, "opinionReplyContent");
            return (Criteria) this;
        }

        public Criteria andOpinionReplyContentNotBetween(String value1, String value2) {
            addCriterion("OPINION_REPLY_CONTENT not between", value1, value2, "opinionReplyContent");
            return (Criteria) this;
        }

        public Criteria andReplyTimeIsNull() {
            addCriterion("REPLY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andReplyTimeIsNotNull() {
            addCriterion("REPLY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andReplyTimeEqualTo(String value) {
            addCriterion("REPLY_TIME =", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeNotEqualTo(String value) {
            addCriterion("REPLY_TIME <>", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeGreaterThan(String value) {
            addCriterion("REPLY_TIME >", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeGreaterThanOrEqualTo(String value) {
            addCriterion("REPLY_TIME >=", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLessThan(String value) {
            addCriterion("REPLY_TIME <", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLessThanOrEqualTo(String value) {
            addCriterion("REPLY_TIME <=", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLike(String value) {
            addCriterion("REPLY_TIME like", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeNotLike(String value) {
            addCriterion("REPLY_TIME not like", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeIn(List<String> values) {
            addCriterion("REPLY_TIME in", values, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeNotIn(List<String> values) {
            addCriterion("REPLY_TIME not in", values, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeBetween(String value1, String value2) {
            addCriterion("REPLY_TIME between", value1, value2, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeNotBetween(String value1, String value2) {
            addCriterion("REPLY_TIME not between", value1, value2, "replyTime");
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