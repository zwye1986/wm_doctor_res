package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ExpertEvalOpinionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExpertEvalOpinionExample() {
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

        public Criteria andOpinionFlowIsNull() {
            addCriterion("OPINION_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andOpinionFlowIsNotNull() {
            addCriterion("OPINION_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andOpinionFlowEqualTo(String value) {
            addCriterion("OPINION_FLOW =", value, "opinionFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionFlowNotEqualTo(String value) {
            addCriterion("OPINION_FLOW <>", value, "opinionFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionFlowGreaterThan(String value) {
            addCriterion("OPINION_FLOW >", value, "opinionFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionFlowGreaterThanOrEqualTo(String value) {
            addCriterion("OPINION_FLOW >=", value, "opinionFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionFlowLessThan(String value) {
            addCriterion("OPINION_FLOW <", value, "opinionFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionFlowLessThanOrEqualTo(String value) {
            addCriterion("OPINION_FLOW <=", value, "opinionFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionFlowLike(String value) {
            addCriterion("OPINION_FLOW like", value, "opinionFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionFlowNotLike(String value) {
            addCriterion("OPINION_FLOW not like", value, "opinionFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionFlowIn(List<String> values) {
            addCriterion("OPINION_FLOW in", values, "opinionFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionFlowNotIn(List<String> values) {
            addCriterion("OPINION_FLOW not in", values, "opinionFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionFlowBetween(String value1, String value2) {
            addCriterion("OPINION_FLOW between", value1, value2, "opinionFlow");
            return (Criteria) this;
        }

        public Criteria andOpinionFlowNotBetween(String value1, String value2) {
            addCriterion("OPINION_FLOW not between", value1, value2, "opinionFlow");
            return (Criteria) this;
        }

        public Criteria andEvalYearIsNull() {
            addCriterion("EVAL_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andEvalYearIsNotNull() {
            addCriterion("EVAL_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andEvalYearEqualTo(String value) {
            addCriterion("EVAL_YEAR =", value, "evalYear");
            return (Criteria) this;
        }

        public Criteria andEvalYearNotEqualTo(String value) {
            addCriterion("EVAL_YEAR <>", value, "evalYear");
            return (Criteria) this;
        }

        public Criteria andEvalYearGreaterThan(String value) {
            addCriterion("EVAL_YEAR >", value, "evalYear");
            return (Criteria) this;
        }

        public Criteria andEvalYearGreaterThanOrEqualTo(String value) {
            addCriterion("EVAL_YEAR >=", value, "evalYear");
            return (Criteria) this;
        }

        public Criteria andEvalYearLessThan(String value) {
            addCriterion("EVAL_YEAR <", value, "evalYear");
            return (Criteria) this;
        }

        public Criteria andEvalYearLessThanOrEqualTo(String value) {
            addCriterion("EVAL_YEAR <=", value, "evalYear");
            return (Criteria) this;
        }

        public Criteria andEvalYearLike(String value) {
            addCriterion("EVAL_YEAR like", value, "evalYear");
            return (Criteria) this;
        }

        public Criteria andEvalYearNotLike(String value) {
            addCriterion("EVAL_YEAR not like", value, "evalYear");
            return (Criteria) this;
        }

        public Criteria andEvalYearIn(List<String> values) {
            addCriterion("EVAL_YEAR in", values, "evalYear");
            return (Criteria) this;
        }

        public Criteria andEvalYearNotIn(List<String> values) {
            addCriterion("EVAL_YEAR not in", values, "evalYear");
            return (Criteria) this;
        }

        public Criteria andEvalYearBetween(String value1, String value2) {
            addCriterion("EVAL_YEAR between", value1, value2, "evalYear");
            return (Criteria) this;
        }

        public Criteria andEvalYearNotBetween(String value1, String value2) {
            addCriterion("EVAL_YEAR not between", value1, value2, "evalYear");
            return (Criteria) this;
        }

        public Criteria andManageIsNull() {
            addCriterion("MANAGE is null");
            return (Criteria) this;
        }

        public Criteria andManageIsNotNull() {
            addCriterion("MANAGE is not null");
            return (Criteria) this;
        }

        public Criteria andManageEqualTo(String value) {
            addCriterion("MANAGE =", value, "manage");
            return (Criteria) this;
        }

        public Criteria andManageNotEqualTo(String value) {
            addCriterion("MANAGE <>", value, "manage");
            return (Criteria) this;
        }

        public Criteria andManageGreaterThan(String value) {
            addCriterion("MANAGE >", value, "manage");
            return (Criteria) this;
        }

        public Criteria andManageGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGE >=", value, "manage");
            return (Criteria) this;
        }

        public Criteria andManageLessThan(String value) {
            addCriterion("MANAGE <", value, "manage");
            return (Criteria) this;
        }

        public Criteria andManageLessThanOrEqualTo(String value) {
            addCriterion("MANAGE <=", value, "manage");
            return (Criteria) this;
        }

        public Criteria andManageLike(String value) {
            addCriterion("MANAGE like", value, "manage");
            return (Criteria) this;
        }

        public Criteria andManageNotLike(String value) {
            addCriterion("MANAGE not like", value, "manage");
            return (Criteria) this;
        }

        public Criteria andManageIn(List<String> values) {
            addCriterion("MANAGE in", values, "manage");
            return (Criteria) this;
        }

        public Criteria andManageNotIn(List<String> values) {
            addCriterion("MANAGE not in", values, "manage");
            return (Criteria) this;
        }

        public Criteria andManageBetween(String value1, String value2) {
            addCriterion("MANAGE between", value1, value2, "manage");
            return (Criteria) this;
        }

        public Criteria andManageNotBetween(String value1, String value2) {
            addCriterion("MANAGE not between", value1, value2, "manage");
            return (Criteria) this;
        }

        public Criteria andAdvantageIsNull() {
            addCriterion("ADVANTAGE is null");
            return (Criteria) this;
        }

        public Criteria andAdvantageIsNotNull() {
            addCriterion("ADVANTAGE is not null");
            return (Criteria) this;
        }

        public Criteria andAdvantageEqualTo(String value) {
            addCriterion("ADVANTAGE =", value, "advantage");
            return (Criteria) this;
        }

        public Criteria andAdvantageNotEqualTo(String value) {
            addCriterion("ADVANTAGE <>", value, "advantage");
            return (Criteria) this;
        }

        public Criteria andAdvantageGreaterThan(String value) {
            addCriterion("ADVANTAGE >", value, "advantage");
            return (Criteria) this;
        }

        public Criteria andAdvantageGreaterThanOrEqualTo(String value) {
            addCriterion("ADVANTAGE >=", value, "advantage");
            return (Criteria) this;
        }

        public Criteria andAdvantageLessThan(String value) {
            addCriterion("ADVANTAGE <", value, "advantage");
            return (Criteria) this;
        }

        public Criteria andAdvantageLessThanOrEqualTo(String value) {
            addCriterion("ADVANTAGE <=", value, "advantage");
            return (Criteria) this;
        }

        public Criteria andAdvantageLike(String value) {
            addCriterion("ADVANTAGE like", value, "advantage");
            return (Criteria) this;
        }

        public Criteria andAdvantageNotLike(String value) {
            addCriterion("ADVANTAGE not like", value, "advantage");
            return (Criteria) this;
        }

        public Criteria andAdvantageIn(List<String> values) {
            addCriterion("ADVANTAGE in", values, "advantage");
            return (Criteria) this;
        }

        public Criteria andAdvantageNotIn(List<String> values) {
            addCriterion("ADVANTAGE not in", values, "advantage");
            return (Criteria) this;
        }

        public Criteria andAdvantageBetween(String value1, String value2) {
            addCriterion("ADVANTAGE between", value1, value2, "advantage");
            return (Criteria) this;
        }

        public Criteria andAdvantageNotBetween(String value1, String value2) {
            addCriterion("ADVANTAGE not between", value1, value2, "advantage");
            return (Criteria) this;
        }

        public Criteria andLackIsNull() {
            addCriterion("LACK is null");
            return (Criteria) this;
        }

        public Criteria andLackIsNotNull() {
            addCriterion("LACK is not null");
            return (Criteria) this;
        }

        public Criteria andLackEqualTo(String value) {
            addCriterion("LACK =", value, "lack");
            return (Criteria) this;
        }

        public Criteria andLackNotEqualTo(String value) {
            addCriterion("LACK <>", value, "lack");
            return (Criteria) this;
        }

        public Criteria andLackGreaterThan(String value) {
            addCriterion("LACK >", value, "lack");
            return (Criteria) this;
        }

        public Criteria andLackGreaterThanOrEqualTo(String value) {
            addCriterion("LACK >=", value, "lack");
            return (Criteria) this;
        }

        public Criteria andLackLessThan(String value) {
            addCriterion("LACK <", value, "lack");
            return (Criteria) this;
        }

        public Criteria andLackLessThanOrEqualTo(String value) {
            addCriterion("LACK <=", value, "lack");
            return (Criteria) this;
        }

        public Criteria andLackLike(String value) {
            addCriterion("LACK like", value, "lack");
            return (Criteria) this;
        }

        public Criteria andLackNotLike(String value) {
            addCriterion("LACK not like", value, "lack");
            return (Criteria) this;
        }

        public Criteria andLackIn(List<String> values) {
            addCriterion("LACK in", values, "lack");
            return (Criteria) this;
        }

        public Criteria andLackNotIn(List<String> values) {
            addCriterion("LACK not in", values, "lack");
            return (Criteria) this;
        }

        public Criteria andLackBetween(String value1, String value2) {
            addCriterion("LACK between", value1, value2, "lack");
            return (Criteria) this;
        }

        public Criteria andLackNotBetween(String value1, String value2) {
            addCriterion("LACK not between", value1, value2, "lack");
            return (Criteria) this;
        }

        public Criteria andOpinionIsNull() {
            addCriterion("OPINION is null");
            return (Criteria) this;
        }

        public Criteria andOpinionIsNotNull() {
            addCriterion("OPINION is not null");
            return (Criteria) this;
        }

        public Criteria andOpinionEqualTo(String value) {
            addCriterion("OPINION =", value, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionNotEqualTo(String value) {
            addCriterion("OPINION <>", value, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionGreaterThan(String value) {
            addCriterion("OPINION >", value, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionGreaterThanOrEqualTo(String value) {
            addCriterion("OPINION >=", value, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionLessThan(String value) {
            addCriterion("OPINION <", value, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionLessThanOrEqualTo(String value) {
            addCriterion("OPINION <=", value, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionLike(String value) {
            addCriterion("OPINION like", value, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionNotLike(String value) {
            addCriterion("OPINION not like", value, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionIn(List<String> values) {
            addCriterion("OPINION in", values, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionNotIn(List<String> values) {
            addCriterion("OPINION not in", values, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionBetween(String value1, String value2) {
            addCriterion("OPINION between", value1, value2, "opinion");
            return (Criteria) this;
        }

        public Criteria andOpinionNotBetween(String value1, String value2) {
            addCriterion("OPINION not between", value1, value2, "opinion");
            return (Criteria) this;
        }

        public Criteria andSiginPathIsNull() {
            addCriterion("SIGIN_PATH is null");
            return (Criteria) this;
        }

        public Criteria andSiginPathIsNotNull() {
            addCriterion("SIGIN_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andSiginPathEqualTo(String value) {
            addCriterion("SIGIN_PATH =", value, "siginPath");
            return (Criteria) this;
        }

        public Criteria andSiginPathNotEqualTo(String value) {
            addCriterion("SIGIN_PATH <>", value, "siginPath");
            return (Criteria) this;
        }

        public Criteria andSiginPathGreaterThan(String value) {
            addCriterion("SIGIN_PATH >", value, "siginPath");
            return (Criteria) this;
        }

        public Criteria andSiginPathGreaterThanOrEqualTo(String value) {
            addCriterion("SIGIN_PATH >=", value, "siginPath");
            return (Criteria) this;
        }

        public Criteria andSiginPathLessThan(String value) {
            addCriterion("SIGIN_PATH <", value, "siginPath");
            return (Criteria) this;
        }

        public Criteria andSiginPathLessThanOrEqualTo(String value) {
            addCriterion("SIGIN_PATH <=", value, "siginPath");
            return (Criteria) this;
        }

        public Criteria andSiginPathLike(String value) {
            addCriterion("SIGIN_PATH like", value, "siginPath");
            return (Criteria) this;
        }

        public Criteria andSiginPathNotLike(String value) {
            addCriterion("SIGIN_PATH not like", value, "siginPath");
            return (Criteria) this;
        }

        public Criteria andSiginPathIn(List<String> values) {
            addCriterion("SIGIN_PATH in", values, "siginPath");
            return (Criteria) this;
        }

        public Criteria andSiginPathNotIn(List<String> values) {
            addCriterion("SIGIN_PATH not in", values, "siginPath");
            return (Criteria) this;
        }

        public Criteria andSiginPathBetween(String value1, String value2) {
            addCriterion("SIGIN_PATH between", value1, value2, "siginPath");
            return (Criteria) this;
        }

        public Criteria andSiginPathNotBetween(String value1, String value2) {
            addCriterion("SIGIN_PATH not between", value1, value2, "siginPath");
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

        public Criteria andExpertUserFlowIsNull() {
            addCriterion("EXPERT_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowIsNotNull() {
            addCriterion("EXPERT_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowEqualTo(String value) {
            addCriterion("EXPERT_USER_FLOW =", value, "expertUserFlow");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowNotEqualTo(String value) {
            addCriterion("EXPERT_USER_FLOW <>", value, "expertUserFlow");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowGreaterThan(String value) {
            addCriterion("EXPERT_USER_FLOW >", value, "expertUserFlow");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("EXPERT_USER_FLOW >=", value, "expertUserFlow");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowLessThan(String value) {
            addCriterion("EXPERT_USER_FLOW <", value, "expertUserFlow");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowLessThanOrEqualTo(String value) {
            addCriterion("EXPERT_USER_FLOW <=", value, "expertUserFlow");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowLike(String value) {
            addCriterion("EXPERT_USER_FLOW like", value, "expertUserFlow");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowNotLike(String value) {
            addCriterion("EXPERT_USER_FLOW not like", value, "expertUserFlow");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowIn(List<String> values) {
            addCriterion("EXPERT_USER_FLOW in", values, "expertUserFlow");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowNotIn(List<String> values) {
            addCriterion("EXPERT_USER_FLOW not in", values, "expertUserFlow");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowBetween(String value1, String value2) {
            addCriterion("EXPERT_USER_FLOW between", value1, value2, "expertUserFlow");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowNotBetween(String value1, String value2) {
            addCriterion("EXPERT_USER_FLOW not between", value1, value2, "expertUserFlow");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameIsNull() {
            addCriterion("EXPERT_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameIsNotNull() {
            addCriterion("EXPERT_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameEqualTo(String value) {
            addCriterion("EXPERT_USER_NAME =", value, "expertUserName");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameNotEqualTo(String value) {
            addCriterion("EXPERT_USER_NAME <>", value, "expertUserName");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameGreaterThan(String value) {
            addCriterion("EXPERT_USER_NAME >", value, "expertUserName");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("EXPERT_USER_NAME >=", value, "expertUserName");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameLessThan(String value) {
            addCriterion("EXPERT_USER_NAME <", value, "expertUserName");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameLessThanOrEqualTo(String value) {
            addCriterion("EXPERT_USER_NAME <=", value, "expertUserName");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameLike(String value) {
            addCriterion("EXPERT_USER_NAME like", value, "expertUserName");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameNotLike(String value) {
            addCriterion("EXPERT_USER_NAME not like", value, "expertUserName");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameIn(List<String> values) {
            addCriterion("EXPERT_USER_NAME in", values, "expertUserName");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameNotIn(List<String> values) {
            addCriterion("EXPERT_USER_NAME not in", values, "expertUserName");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameBetween(String value1, String value2) {
            addCriterion("EXPERT_USER_NAME between", value1, value2, "expertUserName");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameNotBetween(String value1, String value2) {
            addCriterion("EXPERT_USER_NAME not between", value1, value2, "expertUserName");
            return (Criteria) this;
        }

        public Criteria andEvalDateIsNull() {
            addCriterion("EVAL_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEvalDateIsNotNull() {
            addCriterion("EVAL_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEvalDateEqualTo(String value) {
            addCriterion("EVAL_DATE =", value, "evalDate");
            return (Criteria) this;
        }

        public Criteria andEvalDateNotEqualTo(String value) {
            addCriterion("EVAL_DATE <>", value, "evalDate");
            return (Criteria) this;
        }

        public Criteria andEvalDateGreaterThan(String value) {
            addCriterion("EVAL_DATE >", value, "evalDate");
            return (Criteria) this;
        }

        public Criteria andEvalDateGreaterThanOrEqualTo(String value) {
            addCriterion("EVAL_DATE >=", value, "evalDate");
            return (Criteria) this;
        }

        public Criteria andEvalDateLessThan(String value) {
            addCriterion("EVAL_DATE <", value, "evalDate");
            return (Criteria) this;
        }

        public Criteria andEvalDateLessThanOrEqualTo(String value) {
            addCriterion("EVAL_DATE <=", value, "evalDate");
            return (Criteria) this;
        }

        public Criteria andEvalDateLike(String value) {
            addCriterion("EVAL_DATE like", value, "evalDate");
            return (Criteria) this;
        }

        public Criteria andEvalDateNotLike(String value) {
            addCriterion("EVAL_DATE not like", value, "evalDate");
            return (Criteria) this;
        }

        public Criteria andEvalDateIn(List<String> values) {
            addCriterion("EVAL_DATE in", values, "evalDate");
            return (Criteria) this;
        }

        public Criteria andEvalDateNotIn(List<String> values) {
            addCriterion("EVAL_DATE not in", values, "evalDate");
            return (Criteria) this;
        }

        public Criteria andEvalDateBetween(String value1, String value2) {
            addCriterion("EVAL_DATE between", value1, value2, "evalDate");
            return (Criteria) this;
        }

        public Criteria andEvalDateNotBetween(String value1, String value2) {
            addCriterion("EVAL_DATE not between", value1, value2, "evalDate");
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