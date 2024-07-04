package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ResAnnualAssessmentRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResAnnualAssessmentRecordExample() {
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

        public Criteria andAnnualAssessmentFlowIsNull() {
            addCriterion("ANNUAL_ASSESSMENT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAnnualAssessmentFlowIsNotNull() {
            addCriterion("ANNUAL_ASSESSMENT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAnnualAssessmentFlowEqualTo(String value) {
            addCriterion("ANNUAL_ASSESSMENT_FLOW =", value, "annualAssessmentFlow");
            return (Criteria) this;
        }

        public Criteria andAnnualAssessmentFlowNotEqualTo(String value) {
            addCriterion("ANNUAL_ASSESSMENT_FLOW <>", value, "annualAssessmentFlow");
            return (Criteria) this;
        }

        public Criteria andAnnualAssessmentFlowGreaterThan(String value) {
            addCriterion("ANNUAL_ASSESSMENT_FLOW >", value, "annualAssessmentFlow");
            return (Criteria) this;
        }

        public Criteria andAnnualAssessmentFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ANNUAL_ASSESSMENT_FLOW >=", value, "annualAssessmentFlow");
            return (Criteria) this;
        }

        public Criteria andAnnualAssessmentFlowLessThan(String value) {
            addCriterion("ANNUAL_ASSESSMENT_FLOW <", value, "annualAssessmentFlow");
            return (Criteria) this;
        }

        public Criteria andAnnualAssessmentFlowLessThanOrEqualTo(String value) {
            addCriterion("ANNUAL_ASSESSMENT_FLOW <=", value, "annualAssessmentFlow");
            return (Criteria) this;
        }

        public Criteria andAnnualAssessmentFlowLike(String value) {
            addCriterion("ANNUAL_ASSESSMENT_FLOW like", value, "annualAssessmentFlow");
            return (Criteria) this;
        }

        public Criteria andAnnualAssessmentFlowNotLike(String value) {
            addCriterion("ANNUAL_ASSESSMENT_FLOW not like", value, "annualAssessmentFlow");
            return (Criteria) this;
        }

        public Criteria andAnnualAssessmentFlowIn(List<String> values) {
            addCriterion("ANNUAL_ASSESSMENT_FLOW in", values, "annualAssessmentFlow");
            return (Criteria) this;
        }

        public Criteria andAnnualAssessmentFlowNotIn(List<String> values) {
            addCriterion("ANNUAL_ASSESSMENT_FLOW not in", values, "annualAssessmentFlow");
            return (Criteria) this;
        }

        public Criteria andAnnualAssessmentFlowBetween(String value1, String value2) {
            addCriterion("ANNUAL_ASSESSMENT_FLOW between", value1, value2, "annualAssessmentFlow");
            return (Criteria) this;
        }

        public Criteria andAnnualAssessmentFlowNotBetween(String value1, String value2) {
            addCriterion("ANNUAL_ASSESSMENT_FLOW not between", value1, value2, "annualAssessmentFlow");
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

        public Criteria andDailyFinishScoreIsNull() {
            addCriterion("DAILY_FINISH_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andDailyFinishScoreIsNotNull() {
            addCriterion("DAILY_FINISH_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andDailyFinishScoreEqualTo(String value) {
            addCriterion("DAILY_FINISH_SCORE =", value, "dailyFinishScore");
            return (Criteria) this;
        }

        public Criteria andDailyFinishScoreNotEqualTo(String value) {
            addCriterion("DAILY_FINISH_SCORE <>", value, "dailyFinishScore");
            return (Criteria) this;
        }

        public Criteria andDailyFinishScoreGreaterThan(String value) {
            addCriterion("DAILY_FINISH_SCORE >", value, "dailyFinishScore");
            return (Criteria) this;
        }

        public Criteria andDailyFinishScoreGreaterThanOrEqualTo(String value) {
            addCriterion("DAILY_FINISH_SCORE >=", value, "dailyFinishScore");
            return (Criteria) this;
        }

        public Criteria andDailyFinishScoreLessThan(String value) {
            addCriterion("DAILY_FINISH_SCORE <", value, "dailyFinishScore");
            return (Criteria) this;
        }

        public Criteria andDailyFinishScoreLessThanOrEqualTo(String value) {
            addCriterion("DAILY_FINISH_SCORE <=", value, "dailyFinishScore");
            return (Criteria) this;
        }

        public Criteria andDailyFinishScoreLike(String value) {
            addCriterion("DAILY_FINISH_SCORE like", value, "dailyFinishScore");
            return (Criteria) this;
        }

        public Criteria andDailyFinishScoreNotLike(String value) {
            addCriterion("DAILY_FINISH_SCORE not like", value, "dailyFinishScore");
            return (Criteria) this;
        }

        public Criteria andDailyFinishScoreIn(List<String> values) {
            addCriterion("DAILY_FINISH_SCORE in", values, "dailyFinishScore");
            return (Criteria) this;
        }

        public Criteria andDailyFinishScoreNotIn(List<String> values) {
            addCriterion("DAILY_FINISH_SCORE not in", values, "dailyFinishScore");
            return (Criteria) this;
        }

        public Criteria andDailyFinishScoreBetween(String value1, String value2) {
            addCriterion("DAILY_FINISH_SCORE between", value1, value2, "dailyFinishScore");
            return (Criteria) this;
        }

        public Criteria andDailyFinishScoreNotBetween(String value1, String value2) {
            addCriterion("DAILY_FINISH_SCORE not between", value1, value2, "dailyFinishScore");
            return (Criteria) this;
        }

        public Criteria andTrainingManualScoreIsNull() {
            addCriterion("TRAINING_MANUAL_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andTrainingManualScoreIsNotNull() {
            addCriterion("TRAINING_MANUAL_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingManualScoreEqualTo(String value) {
            addCriterion("TRAINING_MANUAL_SCORE =", value, "trainingManualScore");
            return (Criteria) this;
        }

        public Criteria andTrainingManualScoreNotEqualTo(String value) {
            addCriterion("TRAINING_MANUAL_SCORE <>", value, "trainingManualScore");
            return (Criteria) this;
        }

        public Criteria andTrainingManualScoreGreaterThan(String value) {
            addCriterion("TRAINING_MANUAL_SCORE >", value, "trainingManualScore");
            return (Criteria) this;
        }

        public Criteria andTrainingManualScoreGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_MANUAL_SCORE >=", value, "trainingManualScore");
            return (Criteria) this;
        }

        public Criteria andTrainingManualScoreLessThan(String value) {
            addCriterion("TRAINING_MANUAL_SCORE <", value, "trainingManualScore");
            return (Criteria) this;
        }

        public Criteria andTrainingManualScoreLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_MANUAL_SCORE <=", value, "trainingManualScore");
            return (Criteria) this;
        }

        public Criteria andTrainingManualScoreLike(String value) {
            addCriterion("TRAINING_MANUAL_SCORE like", value, "trainingManualScore");
            return (Criteria) this;
        }

        public Criteria andTrainingManualScoreNotLike(String value) {
            addCriterion("TRAINING_MANUAL_SCORE not like", value, "trainingManualScore");
            return (Criteria) this;
        }

        public Criteria andTrainingManualScoreIn(List<String> values) {
            addCriterion("TRAINING_MANUAL_SCORE in", values, "trainingManualScore");
            return (Criteria) this;
        }

        public Criteria andTrainingManualScoreNotIn(List<String> values) {
            addCriterion("TRAINING_MANUAL_SCORE not in", values, "trainingManualScore");
            return (Criteria) this;
        }

        public Criteria andTrainingManualScoreBetween(String value1, String value2) {
            addCriterion("TRAINING_MANUAL_SCORE between", value1, value2, "trainingManualScore");
            return (Criteria) this;
        }

        public Criteria andTrainingManualScoreNotBetween(String value1, String value2) {
            addCriterion("TRAINING_MANUAL_SCORE not between", value1, value2, "trainingManualScore");
            return (Criteria) this;
        }

        public Criteria andProfessionalTheoryScoreIsNull() {
            addCriterion("PROFESSIONAL_THEORY_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andProfessionalTheoryScoreIsNotNull() {
            addCriterion("PROFESSIONAL_THEORY_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andProfessionalTheoryScoreEqualTo(String value) {
            addCriterion("PROFESSIONAL_THEORY_SCORE =", value, "professionalTheoryScore");
            return (Criteria) this;
        }

        public Criteria andProfessionalTheoryScoreNotEqualTo(String value) {
            addCriterion("PROFESSIONAL_THEORY_SCORE <>", value, "professionalTheoryScore");
            return (Criteria) this;
        }

        public Criteria andProfessionalTheoryScoreGreaterThan(String value) {
            addCriterion("PROFESSIONAL_THEORY_SCORE >", value, "professionalTheoryScore");
            return (Criteria) this;
        }

        public Criteria andProfessionalTheoryScoreGreaterThanOrEqualTo(String value) {
            addCriterion("PROFESSIONAL_THEORY_SCORE >=", value, "professionalTheoryScore");
            return (Criteria) this;
        }

        public Criteria andProfessionalTheoryScoreLessThan(String value) {
            addCriterion("PROFESSIONAL_THEORY_SCORE <", value, "professionalTheoryScore");
            return (Criteria) this;
        }

        public Criteria andProfessionalTheoryScoreLessThanOrEqualTo(String value) {
            addCriterion("PROFESSIONAL_THEORY_SCORE <=", value, "professionalTheoryScore");
            return (Criteria) this;
        }

        public Criteria andProfessionalTheoryScoreLike(String value) {
            addCriterion("PROFESSIONAL_THEORY_SCORE like", value, "professionalTheoryScore");
            return (Criteria) this;
        }

        public Criteria andProfessionalTheoryScoreNotLike(String value) {
            addCriterion("PROFESSIONAL_THEORY_SCORE not like", value, "professionalTheoryScore");
            return (Criteria) this;
        }

        public Criteria andProfessionalTheoryScoreIn(List<String> values) {
            addCriterion("PROFESSIONAL_THEORY_SCORE in", values, "professionalTheoryScore");
            return (Criteria) this;
        }

        public Criteria andProfessionalTheoryScoreNotIn(List<String> values) {
            addCriterion("PROFESSIONAL_THEORY_SCORE not in", values, "professionalTheoryScore");
            return (Criteria) this;
        }

        public Criteria andProfessionalTheoryScoreBetween(String value1, String value2) {
            addCriterion("PROFESSIONAL_THEORY_SCORE between", value1, value2, "professionalTheoryScore");
            return (Criteria) this;
        }

        public Criteria andProfessionalTheoryScoreNotBetween(String value1, String value2) {
            addCriterion("PROFESSIONAL_THEORY_SCORE not between", value1, value2, "professionalTheoryScore");
            return (Criteria) this;
        }

        public Criteria andSkillNameIsNull() {
            addCriterion("SKILL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSkillNameIsNotNull() {
            addCriterion("SKILL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSkillNameEqualTo(String value) {
            addCriterion("SKILL_NAME =", value, "skillName");
            return (Criteria) this;
        }

        public Criteria andSkillNameNotEqualTo(String value) {
            addCriterion("SKILL_NAME <>", value, "skillName");
            return (Criteria) this;
        }

        public Criteria andSkillNameGreaterThan(String value) {
            addCriterion("SKILL_NAME >", value, "skillName");
            return (Criteria) this;
        }

        public Criteria andSkillNameGreaterThanOrEqualTo(String value) {
            addCriterion("SKILL_NAME >=", value, "skillName");
            return (Criteria) this;
        }

        public Criteria andSkillNameLessThan(String value) {
            addCriterion("SKILL_NAME <", value, "skillName");
            return (Criteria) this;
        }

        public Criteria andSkillNameLessThanOrEqualTo(String value) {
            addCriterion("SKILL_NAME <=", value, "skillName");
            return (Criteria) this;
        }

        public Criteria andSkillNameLike(String value) {
            addCriterion("SKILL_NAME like", value, "skillName");
            return (Criteria) this;
        }

        public Criteria andSkillNameNotLike(String value) {
            addCriterion("SKILL_NAME not like", value, "skillName");
            return (Criteria) this;
        }

        public Criteria andSkillNameIn(List<String> values) {
            addCriterion("SKILL_NAME in", values, "skillName");
            return (Criteria) this;
        }

        public Criteria andSkillNameNotIn(List<String> values) {
            addCriterion("SKILL_NAME not in", values, "skillName");
            return (Criteria) this;
        }

        public Criteria andSkillNameBetween(String value1, String value2) {
            addCriterion("SKILL_NAME between", value1, value2, "skillName");
            return (Criteria) this;
        }

        public Criteria andSkillNameNotBetween(String value1, String value2) {
            addCriterion("SKILL_NAME not between", value1, value2, "skillName");
            return (Criteria) this;
        }

        public Criteria andSkillScoreIsNull() {
            addCriterion("SKILL_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andSkillScoreIsNotNull() {
            addCriterion("SKILL_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andSkillScoreEqualTo(String value) {
            addCriterion("SKILL_SCORE =", value, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreNotEqualTo(String value) {
            addCriterion("SKILL_SCORE <>", value, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreGreaterThan(String value) {
            addCriterion("SKILL_SCORE >", value, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreGreaterThanOrEqualTo(String value) {
            addCriterion("SKILL_SCORE >=", value, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreLessThan(String value) {
            addCriterion("SKILL_SCORE <", value, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreLessThanOrEqualTo(String value) {
            addCriterion("SKILL_SCORE <=", value, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreLike(String value) {
            addCriterion("SKILL_SCORE like", value, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreNotLike(String value) {
            addCriterion("SKILL_SCORE not like", value, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreIn(List<String> values) {
            addCriterion("SKILL_SCORE in", values, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreNotIn(List<String> values) {
            addCriterion("SKILL_SCORE not in", values, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreBetween(String value1, String value2) {
            addCriterion("SKILL_SCORE between", value1, value2, "skillScore");
            return (Criteria) this;
        }

        public Criteria andSkillScoreNotBetween(String value1, String value2) {
            addCriterion("SKILL_SCORE not between", value1, value2, "skillScore");
            return (Criteria) this;
        }

        public Criteria andPublicSkillsScoreIsNull() {
            addCriterion("PUBLIC_SKILLS_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andPublicSkillsScoreIsNotNull() {
            addCriterion("PUBLIC_SKILLS_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andPublicSkillsScoreEqualTo(String value) {
            addCriterion("PUBLIC_SKILLS_SCORE =", value, "publicSkillsScore");
            return (Criteria) this;
        }

        public Criteria andPublicSkillsScoreNotEqualTo(String value) {
            addCriterion("PUBLIC_SKILLS_SCORE <>", value, "publicSkillsScore");
            return (Criteria) this;
        }

        public Criteria andPublicSkillsScoreGreaterThan(String value) {
            addCriterion("PUBLIC_SKILLS_SCORE >", value, "publicSkillsScore");
            return (Criteria) this;
        }

        public Criteria andPublicSkillsScoreGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLIC_SKILLS_SCORE >=", value, "publicSkillsScore");
            return (Criteria) this;
        }

        public Criteria andPublicSkillsScoreLessThan(String value) {
            addCriterion("PUBLIC_SKILLS_SCORE <", value, "publicSkillsScore");
            return (Criteria) this;
        }

        public Criteria andPublicSkillsScoreLessThanOrEqualTo(String value) {
            addCriterion("PUBLIC_SKILLS_SCORE <=", value, "publicSkillsScore");
            return (Criteria) this;
        }

        public Criteria andPublicSkillsScoreLike(String value) {
            addCriterion("PUBLIC_SKILLS_SCORE like", value, "publicSkillsScore");
            return (Criteria) this;
        }

        public Criteria andPublicSkillsScoreNotLike(String value) {
            addCriterion("PUBLIC_SKILLS_SCORE not like", value, "publicSkillsScore");
            return (Criteria) this;
        }

        public Criteria andPublicSkillsScoreIn(List<String> values) {
            addCriterion("PUBLIC_SKILLS_SCORE in", values, "publicSkillsScore");
            return (Criteria) this;
        }

        public Criteria andPublicSkillsScoreNotIn(List<String> values) {
            addCriterion("PUBLIC_SKILLS_SCORE not in", values, "publicSkillsScore");
            return (Criteria) this;
        }

        public Criteria andPublicSkillsScoreBetween(String value1, String value2) {
            addCriterion("PUBLIC_SKILLS_SCORE between", value1, value2, "publicSkillsScore");
            return (Criteria) this;
        }

        public Criteria andPublicSkillsScoreNotBetween(String value1, String value2) {
            addCriterion("PUBLIC_SKILLS_SCORE not between", value1, value2, "publicSkillsScore");
            return (Criteria) this;
        }

        public Criteria andApprovedTotalScoreIsNull() {
            addCriterion("APPROVED_TOTAL_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andApprovedTotalScoreIsNotNull() {
            addCriterion("APPROVED_TOTAL_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andApprovedTotalScoreEqualTo(String value) {
            addCriterion("APPROVED_TOTAL_SCORE =", value, "approvedTotalScore");
            return (Criteria) this;
        }

        public Criteria andApprovedTotalScoreNotEqualTo(String value) {
            addCriterion("APPROVED_TOTAL_SCORE <>", value, "approvedTotalScore");
            return (Criteria) this;
        }

        public Criteria andApprovedTotalScoreGreaterThan(String value) {
            addCriterion("APPROVED_TOTAL_SCORE >", value, "approvedTotalScore");
            return (Criteria) this;
        }

        public Criteria andApprovedTotalScoreGreaterThanOrEqualTo(String value) {
            addCriterion("APPROVED_TOTAL_SCORE >=", value, "approvedTotalScore");
            return (Criteria) this;
        }

        public Criteria andApprovedTotalScoreLessThan(String value) {
            addCriterion("APPROVED_TOTAL_SCORE <", value, "approvedTotalScore");
            return (Criteria) this;
        }

        public Criteria andApprovedTotalScoreLessThanOrEqualTo(String value) {
            addCriterion("APPROVED_TOTAL_SCORE <=", value, "approvedTotalScore");
            return (Criteria) this;
        }

        public Criteria andApprovedTotalScoreLike(String value) {
            addCriterion("APPROVED_TOTAL_SCORE like", value, "approvedTotalScore");
            return (Criteria) this;
        }

        public Criteria andApprovedTotalScoreNotLike(String value) {
            addCriterion("APPROVED_TOTAL_SCORE not like", value, "approvedTotalScore");
            return (Criteria) this;
        }

        public Criteria andApprovedTotalScoreIn(List<String> values) {
            addCriterion("APPROVED_TOTAL_SCORE in", values, "approvedTotalScore");
            return (Criteria) this;
        }

        public Criteria andApprovedTotalScoreNotIn(List<String> values) {
            addCriterion("APPROVED_TOTAL_SCORE not in", values, "approvedTotalScore");
            return (Criteria) this;
        }

        public Criteria andApprovedTotalScoreBetween(String value1, String value2) {
            addCriterion("APPROVED_TOTAL_SCORE between", value1, value2, "approvedTotalScore");
            return (Criteria) this;
        }

        public Criteria andApprovedTotalScoreNotBetween(String value1, String value2) {
            addCriterion("APPROVED_TOTAL_SCORE not between", value1, value2, "approvedTotalScore");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("MEMO is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("MEMO =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("MEMO <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("MEMO >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("MEMO >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("MEMO <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("MEMO <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("MEMO like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("MEMO not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("MEMO in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("MEMO not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("MEMO between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("MEMO not between", value1, value2, "memo");
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

        public Criteria andMaterialUrlIsNull() {
            addCriterion("MATERIAL_URL is null");
            return (Criteria) this;
        }

        public Criteria andMaterialUrlIsNotNull() {
            addCriterion("MATERIAL_URL is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialUrlEqualTo(String value) {
            addCriterion("MATERIAL_URL =", value, "materialUrl");
            return (Criteria) this;
        }

        public Criteria andMaterialUrlNotEqualTo(String value) {
            addCriterion("MATERIAL_URL <>", value, "materialUrl");
            return (Criteria) this;
        }

        public Criteria andMaterialUrlGreaterThan(String value) {
            addCriterion("MATERIAL_URL >", value, "materialUrl");
            return (Criteria) this;
        }

        public Criteria andMaterialUrlGreaterThanOrEqualTo(String value) {
            addCriterion("MATERIAL_URL >=", value, "materialUrl");
            return (Criteria) this;
        }

        public Criteria andMaterialUrlLessThan(String value) {
            addCriterion("MATERIAL_URL <", value, "materialUrl");
            return (Criteria) this;
        }

        public Criteria andMaterialUrlLessThanOrEqualTo(String value) {
            addCriterion("MATERIAL_URL <=", value, "materialUrl");
            return (Criteria) this;
        }

        public Criteria andMaterialUrlLike(String value) {
            addCriterion("MATERIAL_URL like", value, "materialUrl");
            return (Criteria) this;
        }

        public Criteria andMaterialUrlNotLike(String value) {
            addCriterion("MATERIAL_URL not like", value, "materialUrl");
            return (Criteria) this;
        }

        public Criteria andMaterialUrlIn(List<String> values) {
            addCriterion("MATERIAL_URL in", values, "materialUrl");
            return (Criteria) this;
        }

        public Criteria andMaterialUrlNotIn(List<String> values) {
            addCriterion("MATERIAL_URL not in", values, "materialUrl");
            return (Criteria) this;
        }

        public Criteria andMaterialUrlBetween(String value1, String value2) {
            addCriterion("MATERIAL_URL between", value1, value2, "materialUrl");
            return (Criteria) this;
        }

        public Criteria andMaterialUrlNotBetween(String value1, String value2) {
            addCriterion("MATERIAL_URL not between", value1, value2, "materialUrl");
            return (Criteria) this;
        }

        public Criteria andMaterialNameIsNull() {
            addCriterion("MATERIAL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMaterialNameIsNotNull() {
            addCriterion("MATERIAL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialNameEqualTo(String value) {
            addCriterion("MATERIAL_NAME =", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameNotEqualTo(String value) {
            addCriterion("MATERIAL_NAME <>", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameGreaterThan(String value) {
            addCriterion("MATERIAL_NAME >", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameGreaterThanOrEqualTo(String value) {
            addCriterion("MATERIAL_NAME >=", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameLessThan(String value) {
            addCriterion("MATERIAL_NAME <", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameLessThanOrEqualTo(String value) {
            addCriterion("MATERIAL_NAME <=", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameLike(String value) {
            addCriterion("MATERIAL_NAME like", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameNotLike(String value) {
            addCriterion("MATERIAL_NAME not like", value, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameIn(List<String> values) {
            addCriterion("MATERIAL_NAME in", values, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameNotIn(List<String> values) {
            addCriterion("MATERIAL_NAME not in", values, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameBetween(String value1, String value2) {
            addCriterion("MATERIAL_NAME between", value1, value2, "materialName");
            return (Criteria) this;
        }

        public Criteria andMaterialNameNotBetween(String value1, String value2) {
            addCriterion("MATERIAL_NAME not between", value1, value2, "materialName");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearIsNull() {
            addCriterion("ASSESSMENT_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearIsNotNull() {
            addCriterion("ASSESSMENT_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearEqualTo(String value) {
            addCriterion("ASSESSMENT_YEAR =", value, "assessmentYear");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearNotEqualTo(String value) {
            addCriterion("ASSESSMENT_YEAR <>", value, "assessmentYear");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearGreaterThan(String value) {
            addCriterion("ASSESSMENT_YEAR >", value, "assessmentYear");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearGreaterThanOrEqualTo(String value) {
            addCriterion("ASSESSMENT_YEAR >=", value, "assessmentYear");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearLessThan(String value) {
            addCriterion("ASSESSMENT_YEAR <", value, "assessmentYear");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearLessThanOrEqualTo(String value) {
            addCriterion("ASSESSMENT_YEAR <=", value, "assessmentYear");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearLike(String value) {
            addCriterion("ASSESSMENT_YEAR like", value, "assessmentYear");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearNotLike(String value) {
            addCriterion("ASSESSMENT_YEAR not like", value, "assessmentYear");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearIn(List<String> values) {
            addCriterion("ASSESSMENT_YEAR in", values, "assessmentYear");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearNotIn(List<String> values) {
            addCriterion("ASSESSMENT_YEAR not in", values, "assessmentYear");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearBetween(String value1, String value2) {
            addCriterion("ASSESSMENT_YEAR between", value1, value2, "assessmentYear");
            return (Criteria) this;
        }

        public Criteria andAssessmentYearNotBetween(String value1, String value2) {
            addCriterion("ASSESSMENT_YEAR not between", value1, value2, "assessmentYear");
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