package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class SrmExpertProjExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SrmExpertProjExample() {
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

        public Criteria andExpertProjFlowIsNull() {
            addCriterion("EXPERT_PROJ_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andExpertProjFlowIsNotNull() {
            addCriterion("EXPERT_PROJ_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andExpertProjFlowEqualTo(String value) {
            addCriterion("EXPERT_PROJ_FLOW =", value, "expertProjFlow");
            return (Criteria) this;
        }

        public Criteria andExpertProjFlowNotEqualTo(String value) {
            addCriterion("EXPERT_PROJ_FLOW <>", value, "expertProjFlow");
            return (Criteria) this;
        }

        public Criteria andExpertProjFlowGreaterThan(String value) {
            addCriterion("EXPERT_PROJ_FLOW >", value, "expertProjFlow");
            return (Criteria) this;
        }

        public Criteria andExpertProjFlowGreaterThanOrEqualTo(String value) {
            addCriterion("EXPERT_PROJ_FLOW >=", value, "expertProjFlow");
            return (Criteria) this;
        }

        public Criteria andExpertProjFlowLessThan(String value) {
            addCriterion("EXPERT_PROJ_FLOW <", value, "expertProjFlow");
            return (Criteria) this;
        }

        public Criteria andExpertProjFlowLessThanOrEqualTo(String value) {
            addCriterion("EXPERT_PROJ_FLOW <=", value, "expertProjFlow");
            return (Criteria) this;
        }

        public Criteria andExpertProjFlowLike(String value) {
            addCriterion("EXPERT_PROJ_FLOW like", value, "expertProjFlow");
            return (Criteria) this;
        }

        public Criteria andExpertProjFlowNotLike(String value) {
            addCriterion("EXPERT_PROJ_FLOW not like", value, "expertProjFlow");
            return (Criteria) this;
        }

        public Criteria andExpertProjFlowIn(List<String> values) {
            addCriterion("EXPERT_PROJ_FLOW in", values, "expertProjFlow");
            return (Criteria) this;
        }

        public Criteria andExpertProjFlowNotIn(List<String> values) {
            addCriterion("EXPERT_PROJ_FLOW not in", values, "expertProjFlow");
            return (Criteria) this;
        }

        public Criteria andExpertProjFlowBetween(String value1, String value2) {
            addCriterion("EXPERT_PROJ_FLOW between", value1, value2, "expertProjFlow");
            return (Criteria) this;
        }

        public Criteria andExpertProjFlowNotBetween(String value1, String value2) {
            addCriterion("EXPERT_PROJ_FLOW not between", value1, value2, "expertProjFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowIsNull() {
            addCriterion("PROJ_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andProjFlowIsNotNull() {
            addCriterion("PROJ_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andProjFlowEqualTo(String value) {
            addCriterion("PROJ_FLOW =", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowNotEqualTo(String value) {
            addCriterion("PROJ_FLOW <>", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowGreaterThan(String value) {
            addCriterion("PROJ_FLOW >", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_FLOW >=", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowLessThan(String value) {
            addCriterion("PROJ_FLOW <", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowLessThanOrEqualTo(String value) {
            addCriterion("PROJ_FLOW <=", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowLike(String value) {
            addCriterion("PROJ_FLOW like", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowNotLike(String value) {
            addCriterion("PROJ_FLOW not like", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowIn(List<String> values) {
            addCriterion("PROJ_FLOW in", values, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowNotIn(List<String> values) {
            addCriterion("PROJ_FLOW not in", values, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowBetween(String value1, String value2) {
            addCriterion("PROJ_FLOW between", value1, value2, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowNotBetween(String value1, String value2) {
            addCriterion("PROJ_FLOW not between", value1, value2, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjNameIsNull() {
            addCriterion("PROJ_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjNameIsNotNull() {
            addCriterion("PROJ_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjNameEqualTo(String value) {
            addCriterion("PROJ_NAME =", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameNotEqualTo(String value) {
            addCriterion("PROJ_NAME <>", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameGreaterThan(String value) {
            addCriterion("PROJ_NAME >", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_NAME >=", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameLessThan(String value) {
            addCriterion("PROJ_NAME <", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameLessThanOrEqualTo(String value) {
            addCriterion("PROJ_NAME <=", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameLike(String value) {
            addCriterion("PROJ_NAME like", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameNotLike(String value) {
            addCriterion("PROJ_NAME not like", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameIn(List<String> values) {
            addCriterion("PROJ_NAME in", values, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameNotIn(List<String> values) {
            addCriterion("PROJ_NAME not in", values, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameBetween(String value1, String value2) {
            addCriterion("PROJ_NAME between", value1, value2, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameNotBetween(String value1, String value2) {
            addCriterion("PROJ_NAME not between", value1, value2, "projName");
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

        public Criteria andUserNameIsNull() {
            addCriterion("USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("USER_NAME =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("USER_NAME <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("USER_NAME >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_NAME >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("USER_NAME <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("USER_NAME <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("USER_NAME like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("USER_NAME not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("USER_NAME in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("USER_NAME not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("USER_NAME between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("USER_NAME not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagIsNull() {
            addCriterion("EMAIL_NOTIFY_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagIsNotNull() {
            addCriterion("EMAIL_NOTIFY_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagEqualTo(String value) {
            addCriterion("EMAIL_NOTIFY_FLAG =", value, "emailNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagNotEqualTo(String value) {
            addCriterion("EMAIL_NOTIFY_FLAG <>", value, "emailNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagGreaterThan(String value) {
            addCriterion("EMAIL_NOTIFY_FLAG >", value, "emailNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL_NOTIFY_FLAG >=", value, "emailNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagLessThan(String value) {
            addCriterion("EMAIL_NOTIFY_FLAG <", value, "emailNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagLessThanOrEqualTo(String value) {
            addCriterion("EMAIL_NOTIFY_FLAG <=", value, "emailNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagLike(String value) {
            addCriterion("EMAIL_NOTIFY_FLAG like", value, "emailNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagNotLike(String value) {
            addCriterion("EMAIL_NOTIFY_FLAG not like", value, "emailNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagIn(List<String> values) {
            addCriterion("EMAIL_NOTIFY_FLAG in", values, "emailNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagNotIn(List<String> values) {
            addCriterion("EMAIL_NOTIFY_FLAG not in", values, "emailNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagBetween(String value1, String value2) {
            addCriterion("EMAIL_NOTIFY_FLAG between", value1, value2, "emailNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andEmailNotifyFlagNotBetween(String value1, String value2) {
            addCriterion("EMAIL_NOTIFY_FLAG not between", value1, value2, "emailNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagIsNull() {
            addCriterion("PHONE_NOTIFY_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagIsNotNull() {
            addCriterion("PHONE_NOTIFY_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagEqualTo(String value) {
            addCriterion("PHONE_NOTIFY_FLAG =", value, "phoneNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagNotEqualTo(String value) {
            addCriterion("PHONE_NOTIFY_FLAG <>", value, "phoneNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagGreaterThan(String value) {
            addCriterion("PHONE_NOTIFY_FLAG >", value, "phoneNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagGreaterThanOrEqualTo(String value) {
            addCriterion("PHONE_NOTIFY_FLAG >=", value, "phoneNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagLessThan(String value) {
            addCriterion("PHONE_NOTIFY_FLAG <", value, "phoneNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagLessThanOrEqualTo(String value) {
            addCriterion("PHONE_NOTIFY_FLAG <=", value, "phoneNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagLike(String value) {
            addCriterion("PHONE_NOTIFY_FLAG like", value, "phoneNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagNotLike(String value) {
            addCriterion("PHONE_NOTIFY_FLAG not like", value, "phoneNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagIn(List<String> values) {
            addCriterion("PHONE_NOTIFY_FLAG in", values, "phoneNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagNotIn(List<String> values) {
            addCriterion("PHONE_NOTIFY_FLAG not in", values, "phoneNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagBetween(String value1, String value2) {
            addCriterion("PHONE_NOTIFY_FLAG between", value1, value2, "phoneNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andPhoneNotifyFlagNotBetween(String value1, String value2) {
            addCriterion("PHONE_NOTIFY_FLAG not between", value1, value2, "phoneNotifyFlag");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoIsNull() {
            addCriterion("FEEDBACK_INFO is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoIsNotNull() {
            addCriterion("FEEDBACK_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoEqualTo(String value) {
            addCriterion("FEEDBACK_INFO =", value, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoNotEqualTo(String value) {
            addCriterion("FEEDBACK_INFO <>", value, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoGreaterThan(String value) {
            addCriterion("FEEDBACK_INFO >", value, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoGreaterThanOrEqualTo(String value) {
            addCriterion("FEEDBACK_INFO >=", value, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoLessThan(String value) {
            addCriterion("FEEDBACK_INFO <", value, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoLessThanOrEqualTo(String value) {
            addCriterion("FEEDBACK_INFO <=", value, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoLike(String value) {
            addCriterion("FEEDBACK_INFO like", value, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoNotLike(String value) {
            addCriterion("FEEDBACK_INFO not like", value, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoIn(List<String> values) {
            addCriterion("FEEDBACK_INFO in", values, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoNotIn(List<String> values) {
            addCriterion("FEEDBACK_INFO not in", values, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoBetween(String value1, String value2) {
            addCriterion("FEEDBACK_INFO between", value1, value2, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andFeedbackInfoNotBetween(String value1, String value2) {
            addCriterion("FEEDBACK_INFO not between", value1, value2, "feedbackInfo");
            return (Criteria) this;
        }

        public Criteria andAgreeFlagIsNull() {
            addCriterion("AGREE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andAgreeFlagIsNotNull() {
            addCriterion("AGREE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andAgreeFlagEqualTo(String value) {
            addCriterion("AGREE_FLAG =", value, "agreeFlag");
            return (Criteria) this;
        }

        public Criteria andAgreeFlagNotEqualTo(String value) {
            addCriterion("AGREE_FLAG <>", value, "agreeFlag");
            return (Criteria) this;
        }

        public Criteria andAgreeFlagGreaterThan(String value) {
            addCriterion("AGREE_FLAG >", value, "agreeFlag");
            return (Criteria) this;
        }

        public Criteria andAgreeFlagGreaterThanOrEqualTo(String value) {
            addCriterion("AGREE_FLAG >=", value, "agreeFlag");
            return (Criteria) this;
        }

        public Criteria andAgreeFlagLessThan(String value) {
            addCriterion("AGREE_FLAG <", value, "agreeFlag");
            return (Criteria) this;
        }

        public Criteria andAgreeFlagLessThanOrEqualTo(String value) {
            addCriterion("AGREE_FLAG <=", value, "agreeFlag");
            return (Criteria) this;
        }

        public Criteria andAgreeFlagLike(String value) {
            addCriterion("AGREE_FLAG like", value, "agreeFlag");
            return (Criteria) this;
        }

        public Criteria andAgreeFlagNotLike(String value) {
            addCriterion("AGREE_FLAG not like", value, "agreeFlag");
            return (Criteria) this;
        }

        public Criteria andAgreeFlagIn(List<String> values) {
            addCriterion("AGREE_FLAG in", values, "agreeFlag");
            return (Criteria) this;
        }

        public Criteria andAgreeFlagNotIn(List<String> values) {
            addCriterion("AGREE_FLAG not in", values, "agreeFlag");
            return (Criteria) this;
        }

        public Criteria andAgreeFlagBetween(String value1, String value2) {
            addCriterion("AGREE_FLAG between", value1, value2, "agreeFlag");
            return (Criteria) this;
        }

        public Criteria andAgreeFlagNotBetween(String value1, String value2) {
            addCriterion("AGREE_FLAG not between", value1, value2, "agreeFlag");
            return (Criteria) this;
        }

        public Criteria andScoreTotalIsNull() {
            addCriterion("SCORE_TOTAL is null");
            return (Criteria) this;
        }

        public Criteria andScoreTotalIsNotNull() {
            addCriterion("SCORE_TOTAL is not null");
            return (Criteria) this;
        }

        public Criteria andScoreTotalEqualTo(String value) {
            addCriterion("SCORE_TOTAL =", value, "scoreTotal");
            return (Criteria) this;
        }

        public Criteria andScoreTotalNotEqualTo(String value) {
            addCriterion("SCORE_TOTAL <>", value, "scoreTotal");
            return (Criteria) this;
        }

        public Criteria andScoreTotalGreaterThan(String value) {
            addCriterion("SCORE_TOTAL >", value, "scoreTotal");
            return (Criteria) this;
        }

        public Criteria andScoreTotalGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_TOTAL >=", value, "scoreTotal");
            return (Criteria) this;
        }

        public Criteria andScoreTotalLessThan(String value) {
            addCriterion("SCORE_TOTAL <", value, "scoreTotal");
            return (Criteria) this;
        }

        public Criteria andScoreTotalLessThanOrEqualTo(String value) {
            addCriterion("SCORE_TOTAL <=", value, "scoreTotal");
            return (Criteria) this;
        }

        public Criteria andScoreTotalLike(String value) {
            addCriterion("SCORE_TOTAL like", value, "scoreTotal");
            return (Criteria) this;
        }

        public Criteria andScoreTotalNotLike(String value) {
            addCriterion("SCORE_TOTAL not like", value, "scoreTotal");
            return (Criteria) this;
        }

        public Criteria andScoreTotalIn(List<String> values) {
            addCriterion("SCORE_TOTAL in", values, "scoreTotal");
            return (Criteria) this;
        }

        public Criteria andScoreTotalNotIn(List<String> values) {
            addCriterion("SCORE_TOTAL not in", values, "scoreTotal");
            return (Criteria) this;
        }

        public Criteria andScoreTotalBetween(String value1, String value2) {
            addCriterion("SCORE_TOTAL between", value1, value2, "scoreTotal");
            return (Criteria) this;
        }

        public Criteria andScoreTotalNotBetween(String value1, String value2) {
            addCriterion("SCORE_TOTAL not between", value1, value2, "scoreTotal");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdIsNull() {
            addCriterion("SCORE_RESULT_ID is null");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdIsNotNull() {
            addCriterion("SCORE_RESULT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdEqualTo(String value) {
            addCriterion("SCORE_RESULT_ID =", value, "scoreResultId");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdNotEqualTo(String value) {
            addCriterion("SCORE_RESULT_ID <>", value, "scoreResultId");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdGreaterThan(String value) {
            addCriterion("SCORE_RESULT_ID >", value, "scoreResultId");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_RESULT_ID >=", value, "scoreResultId");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdLessThan(String value) {
            addCriterion("SCORE_RESULT_ID <", value, "scoreResultId");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdLessThanOrEqualTo(String value) {
            addCriterion("SCORE_RESULT_ID <=", value, "scoreResultId");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdLike(String value) {
            addCriterion("SCORE_RESULT_ID like", value, "scoreResultId");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdNotLike(String value) {
            addCriterion("SCORE_RESULT_ID not like", value, "scoreResultId");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdIn(List<String> values) {
            addCriterion("SCORE_RESULT_ID in", values, "scoreResultId");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdNotIn(List<String> values) {
            addCriterion("SCORE_RESULT_ID not in", values, "scoreResultId");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdBetween(String value1, String value2) {
            addCriterion("SCORE_RESULT_ID between", value1, value2, "scoreResultId");
            return (Criteria) this;
        }

        public Criteria andScoreResultIdNotBetween(String value1, String value2) {
            addCriterion("SCORE_RESULT_ID not between", value1, value2, "scoreResultId");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameIsNull() {
            addCriterion("SCORE_RESULT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameIsNotNull() {
            addCriterion("SCORE_RESULT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameEqualTo(String value) {
            addCriterion("SCORE_RESULT_NAME =", value, "scoreResultName");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameNotEqualTo(String value) {
            addCriterion("SCORE_RESULT_NAME <>", value, "scoreResultName");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameGreaterThan(String value) {
            addCriterion("SCORE_RESULT_NAME >", value, "scoreResultName");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_RESULT_NAME >=", value, "scoreResultName");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameLessThan(String value) {
            addCriterion("SCORE_RESULT_NAME <", value, "scoreResultName");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameLessThanOrEqualTo(String value) {
            addCriterion("SCORE_RESULT_NAME <=", value, "scoreResultName");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameLike(String value) {
            addCriterion("SCORE_RESULT_NAME like", value, "scoreResultName");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameNotLike(String value) {
            addCriterion("SCORE_RESULT_NAME not like", value, "scoreResultName");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameIn(List<String> values) {
            addCriterion("SCORE_RESULT_NAME in", values, "scoreResultName");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameNotIn(List<String> values) {
            addCriterion("SCORE_RESULT_NAME not in", values, "scoreResultName");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameBetween(String value1, String value2) {
            addCriterion("SCORE_RESULT_NAME between", value1, value2, "scoreResultName");
            return (Criteria) this;
        }

        public Criteria andScoreResultNameNotBetween(String value1, String value2) {
            addCriterion("SCORE_RESULT_NAME not between", value1, value2, "scoreResultName");
            return (Criteria) this;
        }

        public Criteria andExpertOpinionIsNull() {
            addCriterion("EXPERT_OPINION is null");
            return (Criteria) this;
        }

        public Criteria andExpertOpinionIsNotNull() {
            addCriterion("EXPERT_OPINION is not null");
            return (Criteria) this;
        }

        public Criteria andExpertOpinionEqualTo(String value) {
            addCriterion("EXPERT_OPINION =", value, "expertOpinion");
            return (Criteria) this;
        }

        public Criteria andExpertOpinionNotEqualTo(String value) {
            addCriterion("EXPERT_OPINION <>", value, "expertOpinion");
            return (Criteria) this;
        }

        public Criteria andExpertOpinionGreaterThan(String value) {
            addCriterion("EXPERT_OPINION >", value, "expertOpinion");
            return (Criteria) this;
        }

        public Criteria andExpertOpinionGreaterThanOrEqualTo(String value) {
            addCriterion("EXPERT_OPINION >=", value, "expertOpinion");
            return (Criteria) this;
        }

        public Criteria andExpertOpinionLessThan(String value) {
            addCriterion("EXPERT_OPINION <", value, "expertOpinion");
            return (Criteria) this;
        }

        public Criteria andExpertOpinionLessThanOrEqualTo(String value) {
            addCriterion("EXPERT_OPINION <=", value, "expertOpinion");
            return (Criteria) this;
        }

        public Criteria andExpertOpinionLike(String value) {
            addCriterion("EXPERT_OPINION like", value, "expertOpinion");
            return (Criteria) this;
        }

        public Criteria andExpertOpinionNotLike(String value) {
            addCriterion("EXPERT_OPINION not like", value, "expertOpinion");
            return (Criteria) this;
        }

        public Criteria andExpertOpinionIn(List<String> values) {
            addCriterion("EXPERT_OPINION in", values, "expertOpinion");
            return (Criteria) this;
        }

        public Criteria andExpertOpinionNotIn(List<String> values) {
            addCriterion("EXPERT_OPINION not in", values, "expertOpinion");
            return (Criteria) this;
        }

        public Criteria andExpertOpinionBetween(String value1, String value2) {
            addCriterion("EXPERT_OPINION between", value1, value2, "expertOpinion");
            return (Criteria) this;
        }

        public Criteria andExpertOpinionNotBetween(String value1, String value2) {
            addCriterion("EXPERT_OPINION not between", value1, value2, "expertOpinion");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowIsNull() {
            addCriterion("EVAL_SET_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowIsNotNull() {
            addCriterion("EVAL_SET_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowEqualTo(String value) {
            addCriterion("EVAL_SET_FLOW =", value, "evalSetFlow");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowNotEqualTo(String value) {
            addCriterion("EVAL_SET_FLOW <>", value, "evalSetFlow");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowGreaterThan(String value) {
            addCriterion("EVAL_SET_FLOW >", value, "evalSetFlow");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowGreaterThanOrEqualTo(String value) {
            addCriterion("EVAL_SET_FLOW >=", value, "evalSetFlow");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowLessThan(String value) {
            addCriterion("EVAL_SET_FLOW <", value, "evalSetFlow");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowLessThanOrEqualTo(String value) {
            addCriterion("EVAL_SET_FLOW <=", value, "evalSetFlow");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowLike(String value) {
            addCriterion("EVAL_SET_FLOW like", value, "evalSetFlow");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowNotLike(String value) {
            addCriterion("EVAL_SET_FLOW not like", value, "evalSetFlow");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowIn(List<String> values) {
            addCriterion("EVAL_SET_FLOW in", values, "evalSetFlow");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowNotIn(List<String> values) {
            addCriterion("EVAL_SET_FLOW not in", values, "evalSetFlow");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowBetween(String value1, String value2) {
            addCriterion("EVAL_SET_FLOW between", value1, value2, "evalSetFlow");
            return (Criteria) this;
        }

        public Criteria andEvalSetFlowNotBetween(String value1, String value2) {
            addCriterion("EVAL_SET_FLOW not between", value1, value2, "evalSetFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowIsNull() {
            addCriterion("GROUP_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andGroupFlowIsNotNull() {
            addCriterion("GROUP_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andGroupFlowEqualTo(String value) {
            addCriterion("GROUP_FLOW =", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowNotEqualTo(String value) {
            addCriterion("GROUP_FLOW <>", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowGreaterThan(String value) {
            addCriterion("GROUP_FLOW >", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_FLOW >=", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowLessThan(String value) {
            addCriterion("GROUP_FLOW <", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowLessThanOrEqualTo(String value) {
            addCriterion("GROUP_FLOW <=", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowLike(String value) {
            addCriterion("GROUP_FLOW like", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowNotLike(String value) {
            addCriterion("GROUP_FLOW not like", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowIn(List<String> values) {
            addCriterion("GROUP_FLOW in", values, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowNotIn(List<String> values) {
            addCriterion("GROUP_FLOW not in", values, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowBetween(String value1, String value2) {
            addCriterion("GROUP_FLOW between", value1, value2, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowNotBetween(String value1, String value2) {
            addCriterion("GROUP_FLOW not between", value1, value2, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdIsNull() {
            addCriterion("EVAL_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdIsNotNull() {
            addCriterion("EVAL_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdEqualTo(String value) {
            addCriterion("EVAL_STATUS_ID =", value, "evalStatusId");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdNotEqualTo(String value) {
            addCriterion("EVAL_STATUS_ID <>", value, "evalStatusId");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdGreaterThan(String value) {
            addCriterion("EVAL_STATUS_ID >", value, "evalStatusId");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("EVAL_STATUS_ID >=", value, "evalStatusId");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdLessThan(String value) {
            addCriterion("EVAL_STATUS_ID <", value, "evalStatusId");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdLessThanOrEqualTo(String value) {
            addCriterion("EVAL_STATUS_ID <=", value, "evalStatusId");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdLike(String value) {
            addCriterion("EVAL_STATUS_ID like", value, "evalStatusId");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdNotLike(String value) {
            addCriterion("EVAL_STATUS_ID not like", value, "evalStatusId");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdIn(List<String> values) {
            addCriterion("EVAL_STATUS_ID in", values, "evalStatusId");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdNotIn(List<String> values) {
            addCriterion("EVAL_STATUS_ID not in", values, "evalStatusId");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdBetween(String value1, String value2) {
            addCriterion("EVAL_STATUS_ID between", value1, value2, "evalStatusId");
            return (Criteria) this;
        }

        public Criteria andEvalStatusIdNotBetween(String value1, String value2) {
            addCriterion("EVAL_STATUS_ID not between", value1, value2, "evalStatusId");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameIsNull() {
            addCriterion("EVAL_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameIsNotNull() {
            addCriterion("EVAL_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameEqualTo(String value) {
            addCriterion("EVAL_STATUS_NAME =", value, "evalStatusName");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameNotEqualTo(String value) {
            addCriterion("EVAL_STATUS_NAME <>", value, "evalStatusName");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameGreaterThan(String value) {
            addCriterion("EVAL_STATUS_NAME >", value, "evalStatusName");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("EVAL_STATUS_NAME >=", value, "evalStatusName");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameLessThan(String value) {
            addCriterion("EVAL_STATUS_NAME <", value, "evalStatusName");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameLessThanOrEqualTo(String value) {
            addCriterion("EVAL_STATUS_NAME <=", value, "evalStatusName");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameLike(String value) {
            addCriterion("EVAL_STATUS_NAME like", value, "evalStatusName");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameNotLike(String value) {
            addCriterion("EVAL_STATUS_NAME not like", value, "evalStatusName");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameIn(List<String> values) {
            addCriterion("EVAL_STATUS_NAME in", values, "evalStatusName");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameNotIn(List<String> values) {
            addCriterion("EVAL_STATUS_NAME not in", values, "evalStatusName");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameBetween(String value1, String value2) {
            addCriterion("EVAL_STATUS_NAME between", value1, value2, "evalStatusName");
            return (Criteria) this;
        }

        public Criteria andEvalStatusNameNotBetween(String value1, String value2) {
            addCriterion("EVAL_STATUS_NAME not between", value1, value2, "evalStatusName");
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