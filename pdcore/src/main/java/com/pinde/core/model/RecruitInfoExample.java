package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class RecruitInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RecruitInfoExample() {
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

        public Criteria andRecruitFlowIsNull() {
            addCriterion("RECRUIT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowIsNotNull() {
            addCriterion("RECRUIT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowEqualTo(String value) {
            addCriterion("RECRUIT_FLOW =", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotEqualTo(String value) {
            addCriterion("RECRUIT_FLOW <>", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowGreaterThan(String value) {
            addCriterion("RECRUIT_FLOW >", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RECRUIT_FLOW >=", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowLessThan(String value) {
            addCriterion("RECRUIT_FLOW <", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowLessThanOrEqualTo(String value) {
            addCriterion("RECRUIT_FLOW <=", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowLike(String value) {
            addCriterion("RECRUIT_FLOW like", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotLike(String value) {
            addCriterion("RECRUIT_FLOW not like", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowIn(List<String> values) {
            addCriterion("RECRUIT_FLOW in", values, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotIn(List<String> values) {
            addCriterion("RECRUIT_FLOW not in", values, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowBetween(String value1, String value2) {
            addCriterion("RECRUIT_FLOW between", value1, value2, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotBetween(String value1, String value2) {
            addCriterion("RECRUIT_FLOW not between", value1, value2, "recruitFlow");
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

        public Criteria andRecruitYearIsNull() {
            addCriterion("RECRUIT_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andRecruitYearIsNotNull() {
            addCriterion("RECRUIT_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitYearEqualTo(String value) {
            addCriterion("RECRUIT_YEAR =", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearNotEqualTo(String value) {
            addCriterion("RECRUIT_YEAR <>", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearGreaterThan(String value) {
            addCriterion("RECRUIT_YEAR >", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearGreaterThanOrEqualTo(String value) {
            addCriterion("RECRUIT_YEAR >=", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearLessThan(String value) {
            addCriterion("RECRUIT_YEAR <", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearLessThanOrEqualTo(String value) {
            addCriterion("RECRUIT_YEAR <=", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearLike(String value) {
            addCriterion("RECRUIT_YEAR like", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearNotLike(String value) {
            addCriterion("RECRUIT_YEAR not like", value, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearIn(List<String> values) {
            addCriterion("RECRUIT_YEAR in", values, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearNotIn(List<String> values) {
            addCriterion("RECRUIT_YEAR not in", values, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearBetween(String value1, String value2) {
            addCriterion("RECRUIT_YEAR between", value1, value2, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andRecruitYearNotBetween(String value1, String value2) {
            addCriterion("RECRUIT_YEAR not between", value1, value2, "recruitYear");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdIsNull() {
            addCriterion("DOCTOR_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdIsNotNull() {
            addCriterion("DOCTOR_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_ID =", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdNotEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_ID <>", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdGreaterThan(String value) {
            addCriterion("DOCTOR_TYPE_ID >", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_ID >=", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdLessThan(String value) {
            addCriterion("DOCTOR_TYPE_ID <", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_ID <=", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdLike(String value) {
            addCriterion("DOCTOR_TYPE_ID like", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdNotLike(String value) {
            addCriterion("DOCTOR_TYPE_ID not like", value, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdIn(List<String> values) {
            addCriterion("DOCTOR_TYPE_ID in", values, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdNotIn(List<String> values) {
            addCriterion("DOCTOR_TYPE_ID not in", values, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdBetween(String value1, String value2) {
            addCriterion("DOCTOR_TYPE_ID between", value1, value2, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeIdNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_TYPE_ID not between", value1, value2, "doctorTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameIsNull() {
            addCriterion("DOCTOR_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameIsNotNull() {
            addCriterion("DOCTOR_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_NAME =", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameNotEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_NAME <>", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameGreaterThan(String value) {
            addCriterion("DOCTOR_TYPE_NAME >", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_NAME >=", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameLessThan(String value) {
            addCriterion("DOCTOR_TYPE_NAME <", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_TYPE_NAME <=", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameLike(String value) {
            addCriterion("DOCTOR_TYPE_NAME like", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameNotLike(String value) {
            addCriterion("DOCTOR_TYPE_NAME not like", value, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameIn(List<String> values) {
            addCriterion("DOCTOR_TYPE_NAME in", values, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameNotIn(List<String> values) {
            addCriterion("DOCTOR_TYPE_NAME not in", values, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameBetween(String value1, String value2) {
            addCriterion("DOCTOR_TYPE_NAME between", value1, value2, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorTypeNameNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_TYPE_NAME not between", value1, value2, "doctorTypeName");
            return (Criteria) this;
        }

        public Criteria andLinkManIsNull() {
            addCriterion("LINK_MAN is null");
            return (Criteria) this;
        }

        public Criteria andLinkManIsNotNull() {
            addCriterion("LINK_MAN is not null");
            return (Criteria) this;
        }

        public Criteria andLinkManEqualTo(String value) {
            addCriterion("LINK_MAN =", value, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManNotEqualTo(String value) {
            addCriterion("LINK_MAN <>", value, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManGreaterThan(String value) {
            addCriterion("LINK_MAN >", value, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManGreaterThanOrEqualTo(String value) {
            addCriterion("LINK_MAN >=", value, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManLessThan(String value) {
            addCriterion("LINK_MAN <", value, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManLessThanOrEqualTo(String value) {
            addCriterion("LINK_MAN <=", value, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManLike(String value) {
            addCriterion("LINK_MAN like", value, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManNotLike(String value) {
            addCriterion("LINK_MAN not like", value, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManIn(List<String> values) {
            addCriterion("LINK_MAN in", values, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManNotIn(List<String> values) {
            addCriterion("LINK_MAN not in", values, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManBetween(String value1, String value2) {
            addCriterion("LINK_MAN between", value1, value2, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManNotBetween(String value1, String value2) {
            addCriterion("LINK_MAN not between", value1, value2, "linkMan");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneIsNull() {
            addCriterion("LINK_MAN_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneIsNotNull() {
            addCriterion("LINK_MAN_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneEqualTo(String value) {
            addCriterion("LINK_MAN_PHONE =", value, "linkManPhone");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneNotEqualTo(String value) {
            addCriterion("LINK_MAN_PHONE <>", value, "linkManPhone");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneGreaterThan(String value) {
            addCriterion("LINK_MAN_PHONE >", value, "linkManPhone");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("LINK_MAN_PHONE >=", value, "linkManPhone");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneLessThan(String value) {
            addCriterion("LINK_MAN_PHONE <", value, "linkManPhone");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneLessThanOrEqualTo(String value) {
            addCriterion("LINK_MAN_PHONE <=", value, "linkManPhone");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneLike(String value) {
            addCriterion("LINK_MAN_PHONE like", value, "linkManPhone");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneNotLike(String value) {
            addCriterion("LINK_MAN_PHONE not like", value, "linkManPhone");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneIn(List<String> values) {
            addCriterion("LINK_MAN_PHONE in", values, "linkManPhone");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneNotIn(List<String> values) {
            addCriterion("LINK_MAN_PHONE not in", values, "linkManPhone");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneBetween(String value1, String value2) {
            addCriterion("LINK_MAN_PHONE between", value1, value2, "linkManPhone");
            return (Criteria) this;
        }

        public Criteria andLinkManPhoneNotBetween(String value1, String value2) {
            addCriterion("LINK_MAN_PHONE not between", value1, value2, "linkManPhone");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameIsNull() {
            addCriterion("WORK_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameIsNotNull() {
            addCriterion("WORK_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameEqualTo(String value) {
            addCriterion("WORK_ORG_NAME =", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameNotEqualTo(String value) {
            addCriterion("WORK_ORG_NAME <>", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameGreaterThan(String value) {
            addCriterion("WORK_ORG_NAME >", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_ORG_NAME >=", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameLessThan(String value) {
            addCriterion("WORK_ORG_NAME <", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameLessThanOrEqualTo(String value) {
            addCriterion("WORK_ORG_NAME <=", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameLike(String value) {
            addCriterion("WORK_ORG_NAME like", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameNotLike(String value) {
            addCriterion("WORK_ORG_NAME not like", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameIn(List<String> values) {
            addCriterion("WORK_ORG_NAME in", values, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameNotIn(List<String> values) {
            addCriterion("WORK_ORG_NAME not in", values, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameBetween(String value1, String value2) {
            addCriterion("WORK_ORG_NAME between", value1, value2, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameNotBetween(String value1, String value2) {
            addCriterion("WORK_ORG_NAME not between", value1, value2, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdIsNull() {
            addCriterion("WORK_ORG_LEVEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdIsNotNull() {
            addCriterion("WORK_ORG_LEVEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdEqualTo(String value) {
            addCriterion("WORK_ORG_LEVEL_ID =", value, "workOrgLevelId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdNotEqualTo(String value) {
            addCriterion("WORK_ORG_LEVEL_ID <>", value, "workOrgLevelId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdGreaterThan(String value) {
            addCriterion("WORK_ORG_LEVEL_ID >", value, "workOrgLevelId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_ORG_LEVEL_ID >=", value, "workOrgLevelId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdLessThan(String value) {
            addCriterion("WORK_ORG_LEVEL_ID <", value, "workOrgLevelId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdLessThanOrEqualTo(String value) {
            addCriterion("WORK_ORG_LEVEL_ID <=", value, "workOrgLevelId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdLike(String value) {
            addCriterion("WORK_ORG_LEVEL_ID like", value, "workOrgLevelId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdNotLike(String value) {
            addCriterion("WORK_ORG_LEVEL_ID not like", value, "workOrgLevelId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdIn(List<String> values) {
            addCriterion("WORK_ORG_LEVEL_ID in", values, "workOrgLevelId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdNotIn(List<String> values) {
            addCriterion("WORK_ORG_LEVEL_ID not in", values, "workOrgLevelId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdBetween(String value1, String value2) {
            addCriterion("WORK_ORG_LEVEL_ID between", value1, value2, "workOrgLevelId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelIdNotBetween(String value1, String value2) {
            addCriterion("WORK_ORG_LEVEL_ID not between", value1, value2, "workOrgLevelId");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameIsNull() {
            addCriterion("WORK_ORG_LEVEL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameIsNotNull() {
            addCriterion("WORK_ORG_LEVEL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameEqualTo(String value) {
            addCriterion("WORK_ORG_LEVEL_NAME =", value, "workOrgLevelName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameNotEqualTo(String value) {
            addCriterion("WORK_ORG_LEVEL_NAME <>", value, "workOrgLevelName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameGreaterThan(String value) {
            addCriterion("WORK_ORG_LEVEL_NAME >", value, "workOrgLevelName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_ORG_LEVEL_NAME >=", value, "workOrgLevelName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameLessThan(String value) {
            addCriterion("WORK_ORG_LEVEL_NAME <", value, "workOrgLevelName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameLessThanOrEqualTo(String value) {
            addCriterion("WORK_ORG_LEVEL_NAME <=", value, "workOrgLevelName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameLike(String value) {
            addCriterion("WORK_ORG_LEVEL_NAME like", value, "workOrgLevelName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameNotLike(String value) {
            addCriterion("WORK_ORG_LEVEL_NAME not like", value, "workOrgLevelName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameIn(List<String> values) {
            addCriterion("WORK_ORG_LEVEL_NAME in", values, "workOrgLevelName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameNotIn(List<String> values) {
            addCriterion("WORK_ORG_LEVEL_NAME not in", values, "workOrgLevelName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameBetween(String value1, String value2) {
            addCriterion("WORK_ORG_LEVEL_NAME between", value1, value2, "workOrgLevelName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgLevelNameNotBetween(String value1, String value2) {
            addCriterion("WORK_ORG_LEVEL_NAME not between", value1, value2, "workOrgLevelName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdIsNull() {
            addCriterion("TRAINING_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdIsNotNull() {
            addCriterion("TRAINING_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdEqualTo(String value) {
            addCriterion("TRAINING_TYPE_ID =", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdNotEqualTo(String value) {
            addCriterion("TRAINING_TYPE_ID <>", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdGreaterThan(String value) {
            addCriterion("TRAINING_TYPE_ID >", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_TYPE_ID >=", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdLessThan(String value) {
            addCriterion("TRAINING_TYPE_ID <", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_TYPE_ID <=", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdLike(String value) {
            addCriterion("TRAINING_TYPE_ID like", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdNotLike(String value) {
            addCriterion("TRAINING_TYPE_ID not like", value, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdIn(List<String> values) {
            addCriterion("TRAINING_TYPE_ID in", values, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdNotIn(List<String> values) {
            addCriterion("TRAINING_TYPE_ID not in", values, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdBetween(String value1, String value2) {
            addCriterion("TRAINING_TYPE_ID between", value1, value2, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeIdNotBetween(String value1, String value2) {
            addCriterion("TRAINING_TYPE_ID not between", value1, value2, "trainingTypeId");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameIsNull() {
            addCriterion("TRAINING_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameIsNotNull() {
            addCriterion("TRAINING_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameEqualTo(String value) {
            addCriterion("TRAINING_TYPE_NAME =", value, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameNotEqualTo(String value) {
            addCriterion("TRAINING_TYPE_NAME <>", value, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameGreaterThan(String value) {
            addCriterion("TRAINING_TYPE_NAME >", value, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_TYPE_NAME >=", value, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameLessThan(String value) {
            addCriterion("TRAINING_TYPE_NAME <", value, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_TYPE_NAME <=", value, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameLike(String value) {
            addCriterion("TRAINING_TYPE_NAME like", value, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameNotLike(String value) {
            addCriterion("TRAINING_TYPE_NAME not like", value, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameIn(List<String> values) {
            addCriterion("TRAINING_TYPE_NAME in", values, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameNotIn(List<String> values) {
            addCriterion("TRAINING_TYPE_NAME not in", values, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameBetween(String value1, String value2) {
            addCriterion("TRAINING_TYPE_NAME between", value1, value2, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingTypeNameNotBetween(String value1, String value2) {
            addCriterion("TRAINING_TYPE_NAME not between", value1, value2, "trainingTypeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdIsNull() {
            addCriterion("TRAINING_SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdIsNotNull() {
            addCriterion("TRAINING_SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdEqualTo(String value) {
            addCriterion("TRAINING_SPE_ID =", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdNotEqualTo(String value) {
            addCriterion("TRAINING_SPE_ID <>", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdGreaterThan(String value) {
            addCriterion("TRAINING_SPE_ID >", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_SPE_ID >=", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdLessThan(String value) {
            addCriterion("TRAINING_SPE_ID <", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_SPE_ID <=", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdLike(String value) {
            addCriterion("TRAINING_SPE_ID like", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdNotLike(String value) {
            addCriterion("TRAINING_SPE_ID not like", value, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdIn(List<String> values) {
            addCriterion("TRAINING_SPE_ID in", values, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdNotIn(List<String> values) {
            addCriterion("TRAINING_SPE_ID not in", values, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdBetween(String value1, String value2) {
            addCriterion("TRAINING_SPE_ID between", value1, value2, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeIdNotBetween(String value1, String value2) {
            addCriterion("TRAINING_SPE_ID not between", value1, value2, "trainingSpeId");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameIsNull() {
            addCriterion("TRAINING_SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameIsNotNull() {
            addCriterion("TRAINING_SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameEqualTo(String value) {
            addCriterion("TRAINING_SPE_NAME =", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameNotEqualTo(String value) {
            addCriterion("TRAINING_SPE_NAME <>", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameGreaterThan(String value) {
            addCriterion("TRAINING_SPE_NAME >", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_SPE_NAME >=", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameLessThan(String value) {
            addCriterion("TRAINING_SPE_NAME <", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_SPE_NAME <=", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameLike(String value) {
            addCriterion("TRAINING_SPE_NAME like", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameNotLike(String value) {
            addCriterion("TRAINING_SPE_NAME not like", value, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameIn(List<String> values) {
            addCriterion("TRAINING_SPE_NAME in", values, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameNotIn(List<String> values) {
            addCriterion("TRAINING_SPE_NAME not in", values, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameBetween(String value1, String value2) {
            addCriterion("TRAINING_SPE_NAME between", value1, value2, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andTrainingSpeNameNotBetween(String value1, String value2) {
            addCriterion("TRAINING_SPE_NAME not between", value1, value2, "trainingSpeName");
            return (Criteria) this;
        }

        public Criteria andIsSwapIsNull() {
            addCriterion("IS_SWAP is null");
            return (Criteria) this;
        }

        public Criteria andIsSwapIsNotNull() {
            addCriterion("IS_SWAP is not null");
            return (Criteria) this;
        }

        public Criteria andIsSwapEqualTo(String value) {
            addCriterion("IS_SWAP =", value, "isSwap");
            return (Criteria) this;
        }

        public Criteria andIsSwapNotEqualTo(String value) {
            addCriterion("IS_SWAP <>", value, "isSwap");
            return (Criteria) this;
        }

        public Criteria andIsSwapGreaterThan(String value) {
            addCriterion("IS_SWAP >", value, "isSwap");
            return (Criteria) this;
        }

        public Criteria andIsSwapGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SWAP >=", value, "isSwap");
            return (Criteria) this;
        }

        public Criteria andIsSwapLessThan(String value) {
            addCriterion("IS_SWAP <", value, "isSwap");
            return (Criteria) this;
        }

        public Criteria andIsSwapLessThanOrEqualTo(String value) {
            addCriterion("IS_SWAP <=", value, "isSwap");
            return (Criteria) this;
        }

        public Criteria andIsSwapLike(String value) {
            addCriterion("IS_SWAP like", value, "isSwap");
            return (Criteria) this;
        }

        public Criteria andIsSwapNotLike(String value) {
            addCriterion("IS_SWAP not like", value, "isSwap");
            return (Criteria) this;
        }

        public Criteria andIsSwapIn(List<String> values) {
            addCriterion("IS_SWAP in", values, "isSwap");
            return (Criteria) this;
        }

        public Criteria andIsSwapNotIn(List<String> values) {
            addCriterion("IS_SWAP not in", values, "isSwap");
            return (Criteria) this;
        }

        public Criteria andIsSwapBetween(String value1, String value2) {
            addCriterion("IS_SWAP between", value1, value2, "isSwap");
            return (Criteria) this;
        }

        public Criteria andIsSwapNotBetween(String value1, String value2) {
            addCriterion("IS_SWAP not between", value1, value2, "isSwap");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIsNull() {
            addCriterion("SESSION_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIsNotNull() {
            addCriterion("SESSION_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andSessionNumberEqualTo(String value) {
            addCriterion("SESSION_NUMBER =", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotEqualTo(String value) {
            addCriterion("SESSION_NUMBER <>", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberGreaterThan(String value) {
            addCriterion("SESSION_NUMBER >", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberGreaterThanOrEqualTo(String value) {
            addCriterion("SESSION_NUMBER >=", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLessThan(String value) {
            addCriterion("SESSION_NUMBER <", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLessThanOrEqualTo(String value) {
            addCriterion("SESSION_NUMBER <=", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLike(String value) {
            addCriterion("SESSION_NUMBER like", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotLike(String value) {
            addCriterion("SESSION_NUMBER not like", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIn(List<String> values) {
            addCriterion("SESSION_NUMBER in", values, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotIn(List<String> values) {
            addCriterion("SESSION_NUMBER not in", values, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberBetween(String value1, String value2) {
            addCriterion("SESSION_NUMBER between", value1, value2, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotBetween(String value1, String value2) {
            addCriterion("SESSION_NUMBER not between", value1, value2, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andTrainingYearIsNull() {
            addCriterion("TRAINING_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andTrainingYearIsNotNull() {
            addCriterion("TRAINING_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingYearEqualTo(String value) {
            addCriterion("TRAINING_YEAR =", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearNotEqualTo(String value) {
            addCriterion("TRAINING_YEAR <>", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearGreaterThan(String value) {
            addCriterion("TRAINING_YEAR >", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_YEAR >=", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearLessThan(String value) {
            addCriterion("TRAINING_YEAR <", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_YEAR <=", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearLike(String value) {
            addCriterion("TRAINING_YEAR like", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearNotLike(String value) {
            addCriterion("TRAINING_YEAR not like", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearIn(List<String> values) {
            addCriterion("TRAINING_YEAR in", values, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearNotIn(List<String> values) {
            addCriterion("TRAINING_YEAR not in", values, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearBetween(String value1, String value2) {
            addCriterion("TRAINING_YEAR between", value1, value2, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearNotBetween(String value1, String value2) {
            addCriterion("TRAINING_YEAR not between", value1, value2, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeIsNull() {
            addCriterion("GRADUATION_TIME is null");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeIsNotNull() {
            addCriterion("GRADUATION_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeEqualTo(String value) {
            addCriterion("GRADUATION_TIME =", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeNotEqualTo(String value) {
            addCriterion("GRADUATION_TIME <>", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeGreaterThan(String value) {
            addCriterion("GRADUATION_TIME >", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATION_TIME >=", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeLessThan(String value) {
            addCriterion("GRADUATION_TIME <", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeLessThanOrEqualTo(String value) {
            addCriterion("GRADUATION_TIME <=", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeLike(String value) {
            addCriterion("GRADUATION_TIME like", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeNotLike(String value) {
            addCriterion("GRADUATION_TIME not like", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeIn(List<String> values) {
            addCriterion("GRADUATION_TIME in", values, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeNotIn(List<String> values) {
            addCriterion("GRADUATION_TIME not in", values, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeBetween(String value1, String value2) {
            addCriterion("GRADUATION_TIME between", value1, value2, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeNotBetween(String value1, String value2) {
            addCriterion("GRADUATION_TIME not between", value1, value2, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameIsNull() {
            addCriterion("GRADUATED_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameIsNotNull() {
            addCriterion("GRADUATED_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameEqualTo(String value) {
            addCriterion("GRADUATED_NAME =", value, "graduatedName");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameNotEqualTo(String value) {
            addCriterion("GRADUATED_NAME <>", value, "graduatedName");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameGreaterThan(String value) {
            addCriterion("GRADUATED_NAME >", value, "graduatedName");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATED_NAME >=", value, "graduatedName");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameLessThan(String value) {
            addCriterion("GRADUATED_NAME <", value, "graduatedName");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameLessThanOrEqualTo(String value) {
            addCriterion("GRADUATED_NAME <=", value, "graduatedName");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameLike(String value) {
            addCriterion("GRADUATED_NAME like", value, "graduatedName");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameNotLike(String value) {
            addCriterion("GRADUATED_NAME not like", value, "graduatedName");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameIn(List<String> values) {
            addCriterion("GRADUATED_NAME in", values, "graduatedName");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameNotIn(List<String> values) {
            addCriterion("GRADUATED_NAME not in", values, "graduatedName");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameBetween(String value1, String value2) {
            addCriterion("GRADUATED_NAME between", value1, value2, "graduatedName");
            return (Criteria) this;
        }

        public Criteria andGraduatedNameNotBetween(String value1, String value2) {
            addCriterion("GRADUATED_NAME not between", value1, value2, "graduatedName");
            return (Criteria) this;
        }

        public Criteria andGraduatedMajorIsNull() {
            addCriterion("GRADUATED_MAJOR is null");
            return (Criteria) this;
        }

        public Criteria andGraduatedMajorIsNotNull() {
            addCriterion("GRADUATED_MAJOR is not null");
            return (Criteria) this;
        }

        public Criteria andGraduatedMajorEqualTo(String value) {
            addCriterion("GRADUATED_MAJOR =", value, "graduatedMajor");
            return (Criteria) this;
        }

        public Criteria andGraduatedMajorNotEqualTo(String value) {
            addCriterion("GRADUATED_MAJOR <>", value, "graduatedMajor");
            return (Criteria) this;
        }

        public Criteria andGraduatedMajorGreaterThan(String value) {
            addCriterion("GRADUATED_MAJOR >", value, "graduatedMajor");
            return (Criteria) this;
        }

        public Criteria andGraduatedMajorGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATED_MAJOR >=", value, "graduatedMajor");
            return (Criteria) this;
        }

        public Criteria andGraduatedMajorLessThan(String value) {
            addCriterion("GRADUATED_MAJOR <", value, "graduatedMajor");
            return (Criteria) this;
        }

        public Criteria andGraduatedMajorLessThanOrEqualTo(String value) {
            addCriterion("GRADUATED_MAJOR <=", value, "graduatedMajor");
            return (Criteria) this;
        }

        public Criteria andGraduatedMajorLike(String value) {
            addCriterion("GRADUATED_MAJOR like", value, "graduatedMajor");
            return (Criteria) this;
        }

        public Criteria andGraduatedMajorNotLike(String value) {
            addCriterion("GRADUATED_MAJOR not like", value, "graduatedMajor");
            return (Criteria) this;
        }

        public Criteria andGraduatedMajorIn(List<String> values) {
            addCriterion("GRADUATED_MAJOR in", values, "graduatedMajor");
            return (Criteria) this;
        }

        public Criteria andGraduatedMajorNotIn(List<String> values) {
            addCriterion("GRADUATED_MAJOR not in", values, "graduatedMajor");
            return (Criteria) this;
        }

        public Criteria andGraduatedMajorBetween(String value1, String value2) {
            addCriterion("GRADUATED_MAJOR between", value1, value2, "graduatedMajor");
            return (Criteria) this;
        }

        public Criteria andGraduatedMajorNotBetween(String value1, String value2) {
            addCriterion("GRADUATED_MAJOR not between", value1, value2, "graduatedMajor");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeIdIsNull() {
            addCriterion("MASTERDEGREE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeIdIsNotNull() {
            addCriterion("MASTERDEGREE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeIdEqualTo(String value) {
            addCriterion("MASTERDEGREE_TYPE_ID =", value, "masterdegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeIdNotEqualTo(String value) {
            addCriterion("MASTERDEGREE_TYPE_ID <>", value, "masterdegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeIdGreaterThan(String value) {
            addCriterion("MASTERDEGREE_TYPE_ID >", value, "masterdegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("MASTERDEGREE_TYPE_ID >=", value, "masterdegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeIdLessThan(String value) {
            addCriterion("MASTERDEGREE_TYPE_ID <", value, "masterdegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeIdLessThanOrEqualTo(String value) {
            addCriterion("MASTERDEGREE_TYPE_ID <=", value, "masterdegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeIdLike(String value) {
            addCriterion("MASTERDEGREE_TYPE_ID like", value, "masterdegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeIdNotLike(String value) {
            addCriterion("MASTERDEGREE_TYPE_ID not like", value, "masterdegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeIdIn(List<String> values) {
            addCriterion("MASTERDEGREE_TYPE_ID in", values, "masterdegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeIdNotIn(List<String> values) {
            addCriterion("MASTERDEGREE_TYPE_ID not in", values, "masterdegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeIdBetween(String value1, String value2) {
            addCriterion("MASTERDEGREE_TYPE_ID between", value1, value2, "masterdegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeIdNotBetween(String value1, String value2) {
            addCriterion("MASTERDEGREE_TYPE_ID not between", value1, value2, "masterdegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeNameIsNull() {
            addCriterion("MASTERDEGREE_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeNameIsNotNull() {
            addCriterion("MASTERDEGREE_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeNameEqualTo(String value) {
            addCriterion("MASTERDEGREE_TYPE_NAME =", value, "masterdegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeNameNotEqualTo(String value) {
            addCriterion("MASTERDEGREE_TYPE_NAME <>", value, "masterdegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeNameGreaterThan(String value) {
            addCriterion("MASTERDEGREE_TYPE_NAME >", value, "masterdegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("MASTERDEGREE_TYPE_NAME >=", value, "masterdegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeNameLessThan(String value) {
            addCriterion("MASTERDEGREE_TYPE_NAME <", value, "masterdegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeNameLessThanOrEqualTo(String value) {
            addCriterion("MASTERDEGREE_TYPE_NAME <=", value, "masterdegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeNameLike(String value) {
            addCriterion("MASTERDEGREE_TYPE_NAME like", value, "masterdegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeNameNotLike(String value) {
            addCriterion("MASTERDEGREE_TYPE_NAME not like", value, "masterdegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeNameIn(List<String> values) {
            addCriterion("MASTERDEGREE_TYPE_NAME in", values, "masterdegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeNameNotIn(List<String> values) {
            addCriterion("MASTERDEGREE_TYPE_NAME not in", values, "masterdegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeNameBetween(String value1, String value2) {
            addCriterion("MASTERDEGREE_TYPE_NAME between", value1, value2, "masterdegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andMasterdegreeTypeNameNotBetween(String value1, String value2) {
            addCriterion("MASTERDEGREE_TYPE_NAME not between", value1, value2, "masterdegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andMasterGraschoolNameIsNull() {
            addCriterion("MASTER_GRASCHOOL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMasterGraschoolNameIsNotNull() {
            addCriterion("MASTER_GRASCHOOL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMasterGraschoolNameEqualTo(String value) {
            addCriterion("MASTER_GRASCHOOL_NAME =", value, "masterGraschoolName");
            return (Criteria) this;
        }

        public Criteria andMasterGraschoolNameNotEqualTo(String value) {
            addCriterion("MASTER_GRASCHOOL_NAME <>", value, "masterGraschoolName");
            return (Criteria) this;
        }

        public Criteria andMasterGraschoolNameGreaterThan(String value) {
            addCriterion("MASTER_GRASCHOOL_NAME >", value, "masterGraschoolName");
            return (Criteria) this;
        }

        public Criteria andMasterGraschoolNameGreaterThanOrEqualTo(String value) {
            addCriterion("MASTER_GRASCHOOL_NAME >=", value, "masterGraschoolName");
            return (Criteria) this;
        }

        public Criteria andMasterGraschoolNameLessThan(String value) {
            addCriterion("MASTER_GRASCHOOL_NAME <", value, "masterGraschoolName");
            return (Criteria) this;
        }

        public Criteria andMasterGraschoolNameLessThanOrEqualTo(String value) {
            addCriterion("MASTER_GRASCHOOL_NAME <=", value, "masterGraschoolName");
            return (Criteria) this;
        }

        public Criteria andMasterGraschoolNameLike(String value) {
            addCriterion("MASTER_GRASCHOOL_NAME like", value, "masterGraschoolName");
            return (Criteria) this;
        }

        public Criteria andMasterGraschoolNameNotLike(String value) {
            addCriterion("MASTER_GRASCHOOL_NAME not like", value, "masterGraschoolName");
            return (Criteria) this;
        }

        public Criteria andMasterGraschoolNameIn(List<String> values) {
            addCriterion("MASTER_GRASCHOOL_NAME in", values, "masterGraschoolName");
            return (Criteria) this;
        }

        public Criteria andMasterGraschoolNameNotIn(List<String> values) {
            addCriterion("MASTER_GRASCHOOL_NAME not in", values, "masterGraschoolName");
            return (Criteria) this;
        }

        public Criteria andMasterGraschoolNameBetween(String value1, String value2) {
            addCriterion("MASTER_GRASCHOOL_NAME between", value1, value2, "masterGraschoolName");
            return (Criteria) this;
        }

        public Criteria andMasterGraschoolNameNotBetween(String value1, String value2) {
            addCriterion("MASTER_GRASCHOOL_NAME not between", value1, value2, "masterGraschoolName");
            return (Criteria) this;
        }

        public Criteria andMasterGratimeIsNull() {
            addCriterion("MASTER_GRATIME is null");
            return (Criteria) this;
        }

        public Criteria andMasterGratimeIsNotNull() {
            addCriterion("MASTER_GRATIME is not null");
            return (Criteria) this;
        }

        public Criteria andMasterGratimeEqualTo(String value) {
            addCriterion("MASTER_GRATIME =", value, "masterGratime");
            return (Criteria) this;
        }

        public Criteria andMasterGratimeNotEqualTo(String value) {
            addCriterion("MASTER_GRATIME <>", value, "masterGratime");
            return (Criteria) this;
        }

        public Criteria andMasterGratimeGreaterThan(String value) {
            addCriterion("MASTER_GRATIME >", value, "masterGratime");
            return (Criteria) this;
        }

        public Criteria andMasterGratimeGreaterThanOrEqualTo(String value) {
            addCriterion("MASTER_GRATIME >=", value, "masterGratime");
            return (Criteria) this;
        }

        public Criteria andMasterGratimeLessThan(String value) {
            addCriterion("MASTER_GRATIME <", value, "masterGratime");
            return (Criteria) this;
        }

        public Criteria andMasterGratimeLessThanOrEqualTo(String value) {
            addCriterion("MASTER_GRATIME <=", value, "masterGratime");
            return (Criteria) this;
        }

        public Criteria andMasterGratimeLike(String value) {
            addCriterion("MASTER_GRATIME like", value, "masterGratime");
            return (Criteria) this;
        }

        public Criteria andMasterGratimeNotLike(String value) {
            addCriterion("MASTER_GRATIME not like", value, "masterGratime");
            return (Criteria) this;
        }

        public Criteria andMasterGratimeIn(List<String> values) {
            addCriterion("MASTER_GRATIME in", values, "masterGratime");
            return (Criteria) this;
        }

        public Criteria andMasterGratimeNotIn(List<String> values) {
            addCriterion("MASTER_GRATIME not in", values, "masterGratime");
            return (Criteria) this;
        }

        public Criteria andMasterGratimeBetween(String value1, String value2) {
            addCriterion("MASTER_GRATIME between", value1, value2, "masterGratime");
            return (Criteria) this;
        }

        public Criteria andMasterGratimeNotBetween(String value1, String value2) {
            addCriterion("MASTER_GRATIME not between", value1, value2, "masterGratime");
            return (Criteria) this;
        }

        public Criteria andMasterMajorIsNull() {
            addCriterion("MASTER_MAJOR is null");
            return (Criteria) this;
        }

        public Criteria andMasterMajorIsNotNull() {
            addCriterion("MASTER_MAJOR is not null");
            return (Criteria) this;
        }

        public Criteria andMasterMajorEqualTo(String value) {
            addCriterion("MASTER_MAJOR =", value, "masterMajor");
            return (Criteria) this;
        }

        public Criteria andMasterMajorNotEqualTo(String value) {
            addCriterion("MASTER_MAJOR <>", value, "masterMajor");
            return (Criteria) this;
        }

        public Criteria andMasterMajorGreaterThan(String value) {
            addCriterion("MASTER_MAJOR >", value, "masterMajor");
            return (Criteria) this;
        }

        public Criteria andMasterMajorGreaterThanOrEqualTo(String value) {
            addCriterion("MASTER_MAJOR >=", value, "masterMajor");
            return (Criteria) this;
        }

        public Criteria andMasterMajorLessThan(String value) {
            addCriterion("MASTER_MAJOR <", value, "masterMajor");
            return (Criteria) this;
        }

        public Criteria andMasterMajorLessThanOrEqualTo(String value) {
            addCriterion("MASTER_MAJOR <=", value, "masterMajor");
            return (Criteria) this;
        }

        public Criteria andMasterMajorLike(String value) {
            addCriterion("MASTER_MAJOR like", value, "masterMajor");
            return (Criteria) this;
        }

        public Criteria andMasterMajorNotLike(String value) {
            addCriterion("MASTER_MAJOR not like", value, "masterMajor");
            return (Criteria) this;
        }

        public Criteria andMasterMajorIn(List<String> values) {
            addCriterion("MASTER_MAJOR in", values, "masterMajor");
            return (Criteria) this;
        }

        public Criteria andMasterMajorNotIn(List<String> values) {
            addCriterion("MASTER_MAJOR not in", values, "masterMajor");
            return (Criteria) this;
        }

        public Criteria andMasterMajorBetween(String value1, String value2) {
            addCriterion("MASTER_MAJOR between", value1, value2, "masterMajor");
            return (Criteria) this;
        }

        public Criteria andMasterMajorNotBetween(String value1, String value2) {
            addCriterion("MASTER_MAJOR not between", value1, value2, "masterMajor");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeIdIsNull() {
            addCriterion("DOCTOR_DEGREE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeIdIsNotNull() {
            addCriterion("DOCTOR_DEGREE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeIdEqualTo(String value) {
            addCriterion("DOCTOR_DEGREE_TYPE_ID =", value, "doctorDegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeIdNotEqualTo(String value) {
            addCriterion("DOCTOR_DEGREE_TYPE_ID <>", value, "doctorDegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeIdGreaterThan(String value) {
            addCriterion("DOCTOR_DEGREE_TYPE_ID >", value, "doctorDegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_DEGREE_TYPE_ID >=", value, "doctorDegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeIdLessThan(String value) {
            addCriterion("DOCTOR_DEGREE_TYPE_ID <", value, "doctorDegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeIdLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_DEGREE_TYPE_ID <=", value, "doctorDegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeIdLike(String value) {
            addCriterion("DOCTOR_DEGREE_TYPE_ID like", value, "doctorDegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeIdNotLike(String value) {
            addCriterion("DOCTOR_DEGREE_TYPE_ID not like", value, "doctorDegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeIdIn(List<String> values) {
            addCriterion("DOCTOR_DEGREE_TYPE_ID in", values, "doctorDegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeIdNotIn(List<String> values) {
            addCriterion("DOCTOR_DEGREE_TYPE_ID not in", values, "doctorDegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeIdBetween(String value1, String value2) {
            addCriterion("DOCTOR_DEGREE_TYPE_ID between", value1, value2, "doctorDegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeIdNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_DEGREE_TYPE_ID not between", value1, value2, "doctorDegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeNameIsNull() {
            addCriterion("DOCTOR_DEGREE_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeNameIsNotNull() {
            addCriterion("DOCTOR_DEGREE_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeNameEqualTo(String value) {
            addCriterion("DOCTOR_DEGREE_TYPE_NAME =", value, "doctorDegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeNameNotEqualTo(String value) {
            addCriterion("DOCTOR_DEGREE_TYPE_NAME <>", value, "doctorDegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeNameGreaterThan(String value) {
            addCriterion("DOCTOR_DEGREE_TYPE_NAME >", value, "doctorDegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_DEGREE_TYPE_NAME >=", value, "doctorDegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeNameLessThan(String value) {
            addCriterion("DOCTOR_DEGREE_TYPE_NAME <", value, "doctorDegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeNameLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_DEGREE_TYPE_NAME <=", value, "doctorDegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeNameLike(String value) {
            addCriterion("DOCTOR_DEGREE_TYPE_NAME like", value, "doctorDegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeNameNotLike(String value) {
            addCriterion("DOCTOR_DEGREE_TYPE_NAME not like", value, "doctorDegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeNameIn(List<String> values) {
            addCriterion("DOCTOR_DEGREE_TYPE_NAME in", values, "doctorDegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeNameNotIn(List<String> values) {
            addCriterion("DOCTOR_DEGREE_TYPE_NAME not in", values, "doctorDegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeNameBetween(String value1, String value2) {
            addCriterion("DOCTOR_DEGREE_TYPE_NAME between", value1, value2, "doctorDegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeTypeNameNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_DEGREE_TYPE_NAME not between", value1, value2, "doctorDegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andDoctorGraschoolNameIsNull() {
            addCriterion("DOCTOR_GRASCHOOL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDoctorGraschoolNameIsNotNull() {
            addCriterion("DOCTOR_GRASCHOOL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorGraschoolNameEqualTo(String value) {
            addCriterion("DOCTOR_GRASCHOOL_NAME =", value, "doctorGraschoolName");
            return (Criteria) this;
        }

        public Criteria andDoctorGraschoolNameNotEqualTo(String value) {
            addCriterion("DOCTOR_GRASCHOOL_NAME <>", value, "doctorGraschoolName");
            return (Criteria) this;
        }

        public Criteria andDoctorGraschoolNameGreaterThan(String value) {
            addCriterion("DOCTOR_GRASCHOOL_NAME >", value, "doctorGraschoolName");
            return (Criteria) this;
        }

        public Criteria andDoctorGraschoolNameGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_GRASCHOOL_NAME >=", value, "doctorGraschoolName");
            return (Criteria) this;
        }

        public Criteria andDoctorGraschoolNameLessThan(String value) {
            addCriterion("DOCTOR_GRASCHOOL_NAME <", value, "doctorGraschoolName");
            return (Criteria) this;
        }

        public Criteria andDoctorGraschoolNameLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_GRASCHOOL_NAME <=", value, "doctorGraschoolName");
            return (Criteria) this;
        }

        public Criteria andDoctorGraschoolNameLike(String value) {
            addCriterion("DOCTOR_GRASCHOOL_NAME like", value, "doctorGraschoolName");
            return (Criteria) this;
        }

        public Criteria andDoctorGraschoolNameNotLike(String value) {
            addCriterion("DOCTOR_GRASCHOOL_NAME not like", value, "doctorGraschoolName");
            return (Criteria) this;
        }

        public Criteria andDoctorGraschoolNameIn(List<String> values) {
            addCriterion("DOCTOR_GRASCHOOL_NAME in", values, "doctorGraschoolName");
            return (Criteria) this;
        }

        public Criteria andDoctorGraschoolNameNotIn(List<String> values) {
            addCriterion("DOCTOR_GRASCHOOL_NAME not in", values, "doctorGraschoolName");
            return (Criteria) this;
        }

        public Criteria andDoctorGraschoolNameBetween(String value1, String value2) {
            addCriterion("DOCTOR_GRASCHOOL_NAME between", value1, value2, "doctorGraschoolName");
            return (Criteria) this;
        }

        public Criteria andDoctorGraschoolNameNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_GRASCHOOL_NAME not between", value1, value2, "doctorGraschoolName");
            return (Criteria) this;
        }

        public Criteria andDoctorGratimeIsNull() {
            addCriterion("DOCTOR_GRATIME is null");
            return (Criteria) this;
        }

        public Criteria andDoctorGratimeIsNotNull() {
            addCriterion("DOCTOR_GRATIME is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorGratimeEqualTo(String value) {
            addCriterion("DOCTOR_GRATIME =", value, "doctorGratime");
            return (Criteria) this;
        }

        public Criteria andDoctorGratimeNotEqualTo(String value) {
            addCriterion("DOCTOR_GRATIME <>", value, "doctorGratime");
            return (Criteria) this;
        }

        public Criteria andDoctorGratimeGreaterThan(String value) {
            addCriterion("DOCTOR_GRATIME >", value, "doctorGratime");
            return (Criteria) this;
        }

        public Criteria andDoctorGratimeGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_GRATIME >=", value, "doctorGratime");
            return (Criteria) this;
        }

        public Criteria andDoctorGratimeLessThan(String value) {
            addCriterion("DOCTOR_GRATIME <", value, "doctorGratime");
            return (Criteria) this;
        }

        public Criteria andDoctorGratimeLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_GRATIME <=", value, "doctorGratime");
            return (Criteria) this;
        }

        public Criteria andDoctorGratimeLike(String value) {
            addCriterion("DOCTOR_GRATIME like", value, "doctorGratime");
            return (Criteria) this;
        }

        public Criteria andDoctorGratimeNotLike(String value) {
            addCriterion("DOCTOR_GRATIME not like", value, "doctorGratime");
            return (Criteria) this;
        }

        public Criteria andDoctorGratimeIn(List<String> values) {
            addCriterion("DOCTOR_GRATIME in", values, "doctorGratime");
            return (Criteria) this;
        }

        public Criteria andDoctorGratimeNotIn(List<String> values) {
            addCriterion("DOCTOR_GRATIME not in", values, "doctorGratime");
            return (Criteria) this;
        }

        public Criteria andDoctorGratimeBetween(String value1, String value2) {
            addCriterion("DOCTOR_GRATIME between", value1, value2, "doctorGratime");
            return (Criteria) this;
        }

        public Criteria andDoctorGratimeNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_GRATIME not between", value1, value2, "doctorGratime");
            return (Criteria) this;
        }

        public Criteria andDoctorMajorIsNull() {
            addCriterion("DOCTOR_MAJOR is null");
            return (Criteria) this;
        }

        public Criteria andDoctorMajorIsNotNull() {
            addCriterion("DOCTOR_MAJOR is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorMajorEqualTo(String value) {
            addCriterion("DOCTOR_MAJOR =", value, "doctorMajor");
            return (Criteria) this;
        }

        public Criteria andDoctorMajorNotEqualTo(String value) {
            addCriterion("DOCTOR_MAJOR <>", value, "doctorMajor");
            return (Criteria) this;
        }

        public Criteria andDoctorMajorGreaterThan(String value) {
            addCriterion("DOCTOR_MAJOR >", value, "doctorMajor");
            return (Criteria) this;
        }

        public Criteria andDoctorMajorGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_MAJOR >=", value, "doctorMajor");
            return (Criteria) this;
        }

        public Criteria andDoctorMajorLessThan(String value) {
            addCriterion("DOCTOR_MAJOR <", value, "doctorMajor");
            return (Criteria) this;
        }

        public Criteria andDoctorMajorLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_MAJOR <=", value, "doctorMajor");
            return (Criteria) this;
        }

        public Criteria andDoctorMajorLike(String value) {
            addCriterion("DOCTOR_MAJOR like", value, "doctorMajor");
            return (Criteria) this;
        }

        public Criteria andDoctorMajorNotLike(String value) {
            addCriterion("DOCTOR_MAJOR not like", value, "doctorMajor");
            return (Criteria) this;
        }

        public Criteria andDoctorMajorIn(List<String> values) {
            addCriterion("DOCTOR_MAJOR in", values, "doctorMajor");
            return (Criteria) this;
        }

        public Criteria andDoctorMajorNotIn(List<String> values) {
            addCriterion("DOCTOR_MAJOR not in", values, "doctorMajor");
            return (Criteria) this;
        }

        public Criteria andDoctorMajorBetween(String value1, String value2) {
            addCriterion("DOCTOR_MAJOR between", value1, value2, "doctorMajor");
            return (Criteria) this;
        }

        public Criteria andDoctorMajorNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_MAJOR not between", value1, value2, "doctorMajor");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateIsNull() {
            addCriterion("IS_YEAR_GRADUATE is null");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateIsNotNull() {
            addCriterion("IS_YEAR_GRADUATE is not null");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateEqualTo(String value) {
            addCriterion("IS_YEAR_GRADUATE =", value, "isYearGraduate");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateNotEqualTo(String value) {
            addCriterion("IS_YEAR_GRADUATE <>", value, "isYearGraduate");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateGreaterThan(String value) {
            addCriterion("IS_YEAR_GRADUATE >", value, "isYearGraduate");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateGreaterThanOrEqualTo(String value) {
            addCriterion("IS_YEAR_GRADUATE >=", value, "isYearGraduate");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateLessThan(String value) {
            addCriterion("IS_YEAR_GRADUATE <", value, "isYearGraduate");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateLessThanOrEqualTo(String value) {
            addCriterion("IS_YEAR_GRADUATE <=", value, "isYearGraduate");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateLike(String value) {
            addCriterion("IS_YEAR_GRADUATE like", value, "isYearGraduate");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateNotLike(String value) {
            addCriterion("IS_YEAR_GRADUATE not like", value, "isYearGraduate");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateIn(List<String> values) {
            addCriterion("IS_YEAR_GRADUATE in", values, "isYearGraduate");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateNotIn(List<String> values) {
            addCriterion("IS_YEAR_GRADUATE not in", values, "isYearGraduate");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateBetween(String value1, String value2) {
            addCriterion("IS_YEAR_GRADUATE between", value1, value2, "isYearGraduate");
            return (Criteria) this;
        }

        public Criteria andIsYearGraduateNotBetween(String value1, String value2) {
            addCriterion("IS_YEAR_GRADUATE not between", value1, value2, "isYearGraduate");
            return (Criteria) this;
        }

        public Criteria andIsHaveQualificationIsNull() {
            addCriterion("IS_HAVE_QUALIFICATION is null");
            return (Criteria) this;
        }

        public Criteria andIsHaveQualificationIsNotNull() {
            addCriterion("IS_HAVE_QUALIFICATION is not null");
            return (Criteria) this;
        }

        public Criteria andIsHaveQualificationEqualTo(String value) {
            addCriterion("IS_HAVE_QUALIFICATION =", value, "isHaveQualification");
            return (Criteria) this;
        }

        public Criteria andIsHaveQualificationNotEqualTo(String value) {
            addCriterion("IS_HAVE_QUALIFICATION <>", value, "isHaveQualification");
            return (Criteria) this;
        }

        public Criteria andIsHaveQualificationGreaterThan(String value) {
            addCriterion("IS_HAVE_QUALIFICATION >", value, "isHaveQualification");
            return (Criteria) this;
        }

        public Criteria andIsHaveQualificationGreaterThanOrEqualTo(String value) {
            addCriterion("IS_HAVE_QUALIFICATION >=", value, "isHaveQualification");
            return (Criteria) this;
        }

        public Criteria andIsHaveQualificationLessThan(String value) {
            addCriterion("IS_HAVE_QUALIFICATION <", value, "isHaveQualification");
            return (Criteria) this;
        }

        public Criteria andIsHaveQualificationLessThanOrEqualTo(String value) {
            addCriterion("IS_HAVE_QUALIFICATION <=", value, "isHaveQualification");
            return (Criteria) this;
        }

        public Criteria andIsHaveQualificationLike(String value) {
            addCriterion("IS_HAVE_QUALIFICATION like", value, "isHaveQualification");
            return (Criteria) this;
        }

        public Criteria andIsHaveQualificationNotLike(String value) {
            addCriterion("IS_HAVE_QUALIFICATION not like", value, "isHaveQualification");
            return (Criteria) this;
        }

        public Criteria andIsHaveQualificationIn(List<String> values) {
            addCriterion("IS_HAVE_QUALIFICATION in", values, "isHaveQualification");
            return (Criteria) this;
        }

        public Criteria andIsHaveQualificationNotIn(List<String> values) {
            addCriterion("IS_HAVE_QUALIFICATION not in", values, "isHaveQualification");
            return (Criteria) this;
        }

        public Criteria andIsHaveQualificationBetween(String value1, String value2) {
            addCriterion("IS_HAVE_QUALIFICATION between", value1, value2, "isHaveQualification");
            return (Criteria) this;
        }

        public Criteria andIsHaveQualificationNotBetween(String value1, String value2) {
            addCriterion("IS_HAVE_QUALIFICATION not between", value1, value2, "isHaveQualification");
            return (Criteria) this;
        }

        public Criteria andQualificationCodeIsNull() {
            addCriterion("QUALIFICATION_CODE is null");
            return (Criteria) this;
        }

        public Criteria andQualificationCodeIsNotNull() {
            addCriterion("QUALIFICATION_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andQualificationCodeEqualTo(String value) {
            addCriterion("QUALIFICATION_CODE =", value, "qualificationCode");
            return (Criteria) this;
        }

        public Criteria andQualificationCodeNotEqualTo(String value) {
            addCriterion("QUALIFICATION_CODE <>", value, "qualificationCode");
            return (Criteria) this;
        }

        public Criteria andQualificationCodeGreaterThan(String value) {
            addCriterion("QUALIFICATION_CODE >", value, "qualificationCode");
            return (Criteria) this;
        }

        public Criteria andQualificationCodeGreaterThanOrEqualTo(String value) {
            addCriterion("QUALIFICATION_CODE >=", value, "qualificationCode");
            return (Criteria) this;
        }

        public Criteria andQualificationCodeLessThan(String value) {
            addCriterion("QUALIFICATION_CODE <", value, "qualificationCode");
            return (Criteria) this;
        }

        public Criteria andQualificationCodeLessThanOrEqualTo(String value) {
            addCriterion("QUALIFICATION_CODE <=", value, "qualificationCode");
            return (Criteria) this;
        }

        public Criteria andQualificationCodeLike(String value) {
            addCriterion("QUALIFICATION_CODE like", value, "qualificationCode");
            return (Criteria) this;
        }

        public Criteria andQualificationCodeNotLike(String value) {
            addCriterion("QUALIFICATION_CODE not like", value, "qualificationCode");
            return (Criteria) this;
        }

        public Criteria andQualificationCodeIn(List<String> values) {
            addCriterion("QUALIFICATION_CODE in", values, "qualificationCode");
            return (Criteria) this;
        }

        public Criteria andQualificationCodeNotIn(List<String> values) {
            addCriterion("QUALIFICATION_CODE not in", values, "qualificationCode");
            return (Criteria) this;
        }

        public Criteria andQualificationCodeBetween(String value1, String value2) {
            addCriterion("QUALIFICATION_CODE between", value1, value2, "qualificationCode");
            return (Criteria) this;
        }

        public Criteria andQualificationCodeNotBetween(String value1, String value2) {
            addCriterion("QUALIFICATION_CODE not between", value1, value2, "qualificationCode");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdIsNull() {
            addCriterion("AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdIsNotNull() {
            addCriterion("AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID =", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID <>", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdGreaterThan(String value) {
            addCriterion("AUDIT_STATUS_ID >", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID >=", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdLessThan(String value) {
            addCriterion("AUDIT_STATUS_ID <", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID <=", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdLike(String value) {
            addCriterion("AUDIT_STATUS_ID like", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotLike(String value) {
            addCriterion("AUDIT_STATUS_ID not like", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdIn(List<String> values) {
            addCriterion("AUDIT_STATUS_ID in", values, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotIn(List<String> values) {
            addCriterion("AUDIT_STATUS_ID not in", values, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_ID between", value1, value2, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_ID not between", value1, value2, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameIsNull() {
            addCriterion("AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameIsNotNull() {
            addCriterion("AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME =", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME <>", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameGreaterThan(String value) {
            addCriterion("AUDIT_STATUS_NAME >", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME >=", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameLessThan(String value) {
            addCriterion("AUDIT_STATUS_NAME <", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME <=", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameLike(String value) {
            addCriterion("AUDIT_STATUS_NAME like", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotLike(String value) {
            addCriterion("AUDIT_STATUS_NAME not like", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameIn(List<String> values) {
            addCriterion("AUDIT_STATUS_NAME in", values, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotIn(List<String> values) {
            addCriterion("AUDIT_STATUS_NAME not in", values, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_NAME between", value1, value2, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_NAME not between", value1, value2, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andWriteExamFlagIsNull() {
            addCriterion("WRITE_EXAM_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andWriteExamFlagIsNotNull() {
            addCriterion("WRITE_EXAM_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andWriteExamFlagEqualTo(String value) {
            addCriterion("WRITE_EXAM_FLAG =", value, "writeExamFlag");
            return (Criteria) this;
        }

        public Criteria andWriteExamFlagNotEqualTo(String value) {
            addCriterion("WRITE_EXAM_FLAG <>", value, "writeExamFlag");
            return (Criteria) this;
        }

        public Criteria andWriteExamFlagGreaterThan(String value) {
            addCriterion("WRITE_EXAM_FLAG >", value, "writeExamFlag");
            return (Criteria) this;
        }

        public Criteria andWriteExamFlagGreaterThanOrEqualTo(String value) {
            addCriterion("WRITE_EXAM_FLAG >=", value, "writeExamFlag");
            return (Criteria) this;
        }

        public Criteria andWriteExamFlagLessThan(String value) {
            addCriterion("WRITE_EXAM_FLAG <", value, "writeExamFlag");
            return (Criteria) this;
        }

        public Criteria andWriteExamFlagLessThanOrEqualTo(String value) {
            addCriterion("WRITE_EXAM_FLAG <=", value, "writeExamFlag");
            return (Criteria) this;
        }

        public Criteria andWriteExamFlagLike(String value) {
            addCriterion("WRITE_EXAM_FLAG like", value, "writeExamFlag");
            return (Criteria) this;
        }

        public Criteria andWriteExamFlagNotLike(String value) {
            addCriterion("WRITE_EXAM_FLAG not like", value, "writeExamFlag");
            return (Criteria) this;
        }

        public Criteria andWriteExamFlagIn(List<String> values) {
            addCriterion("WRITE_EXAM_FLAG in", values, "writeExamFlag");
            return (Criteria) this;
        }

        public Criteria andWriteExamFlagNotIn(List<String> values) {
            addCriterion("WRITE_EXAM_FLAG not in", values, "writeExamFlag");
            return (Criteria) this;
        }

        public Criteria andWriteExamFlagBetween(String value1, String value2) {
            addCriterion("WRITE_EXAM_FLAG between", value1, value2, "writeExamFlag");
            return (Criteria) this;
        }

        public Criteria andWriteExamFlagNotBetween(String value1, String value2) {
            addCriterion("WRITE_EXAM_FLAG not between", value1, value2, "writeExamFlag");
            return (Criteria) this;
        }

        public Criteria andExamScoreIsNull() {
            addCriterion("EXAM_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andExamScoreIsNotNull() {
            addCriterion("EXAM_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andExamScoreEqualTo(String value) {
            addCriterion("EXAM_SCORE =", value, "examScore");
            return (Criteria) this;
        }

        public Criteria andExamScoreNotEqualTo(String value) {
            addCriterion("EXAM_SCORE <>", value, "examScore");
            return (Criteria) this;
        }

        public Criteria andExamScoreGreaterThan(String value) {
            addCriterion("EXAM_SCORE >", value, "examScore");
            return (Criteria) this;
        }

        public Criteria andExamScoreGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_SCORE >=", value, "examScore");
            return (Criteria) this;
        }

        public Criteria andExamScoreLessThan(String value) {
            addCriterion("EXAM_SCORE <", value, "examScore");
            return (Criteria) this;
        }

        public Criteria andExamScoreLessThanOrEqualTo(String value) {
            addCriterion("EXAM_SCORE <=", value, "examScore");
            return (Criteria) this;
        }

        public Criteria andExamScoreLike(String value) {
            addCriterion("EXAM_SCORE like", value, "examScore");
            return (Criteria) this;
        }

        public Criteria andExamScoreNotLike(String value) {
            addCriterion("EXAM_SCORE not like", value, "examScore");
            return (Criteria) this;
        }

        public Criteria andExamScoreIn(List<String> values) {
            addCriterion("EXAM_SCORE in", values, "examScore");
            return (Criteria) this;
        }

        public Criteria andExamScoreNotIn(List<String> values) {
            addCriterion("EXAM_SCORE not in", values, "examScore");
            return (Criteria) this;
        }

        public Criteria andExamScoreBetween(String value1, String value2) {
            addCriterion("EXAM_SCORE between", value1, value2, "examScore");
            return (Criteria) this;
        }

        public Criteria andExamScoreNotBetween(String value1, String value2) {
            addCriterion("EXAM_SCORE not between", value1, value2, "examScore");
            return (Criteria) this;
        }

        public Criteria andExamIsPassIsNull() {
            addCriterion("EXAM_IS_PASS is null");
            return (Criteria) this;
        }

        public Criteria andExamIsPassIsNotNull() {
            addCriterion("EXAM_IS_PASS is not null");
            return (Criteria) this;
        }

        public Criteria andExamIsPassEqualTo(String value) {
            addCriterion("EXAM_IS_PASS =", value, "examIsPass");
            return (Criteria) this;
        }

        public Criteria andExamIsPassNotEqualTo(String value) {
            addCriterion("EXAM_IS_PASS <>", value, "examIsPass");
            return (Criteria) this;
        }

        public Criteria andExamIsPassGreaterThan(String value) {
            addCriterion("EXAM_IS_PASS >", value, "examIsPass");
            return (Criteria) this;
        }

        public Criteria andExamIsPassGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_IS_PASS >=", value, "examIsPass");
            return (Criteria) this;
        }

        public Criteria andExamIsPassLessThan(String value) {
            addCriterion("EXAM_IS_PASS <", value, "examIsPass");
            return (Criteria) this;
        }

        public Criteria andExamIsPassLessThanOrEqualTo(String value) {
            addCriterion("EXAM_IS_PASS <=", value, "examIsPass");
            return (Criteria) this;
        }

        public Criteria andExamIsPassLike(String value) {
            addCriterion("EXAM_IS_PASS like", value, "examIsPass");
            return (Criteria) this;
        }

        public Criteria andExamIsPassNotLike(String value) {
            addCriterion("EXAM_IS_PASS not like", value, "examIsPass");
            return (Criteria) this;
        }

        public Criteria andExamIsPassIn(List<String> values) {
            addCriterion("EXAM_IS_PASS in", values, "examIsPass");
            return (Criteria) this;
        }

        public Criteria andExamIsPassNotIn(List<String> values) {
            addCriterion("EXAM_IS_PASS not in", values, "examIsPass");
            return (Criteria) this;
        }

        public Criteria andExamIsPassBetween(String value1, String value2) {
            addCriterion("EXAM_IS_PASS between", value1, value2, "examIsPass");
            return (Criteria) this;
        }

        public Criteria andExamIsPassNotBetween(String value1, String value2) {
            addCriterion("EXAM_IS_PASS not between", value1, value2, "examIsPass");
            return (Criteria) this;
        }

        public Criteria andInterviewFlagIsNull() {
            addCriterion("INTERVIEW_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andInterviewFlagIsNotNull() {
            addCriterion("INTERVIEW_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andInterviewFlagEqualTo(String value) {
            addCriterion("INTERVIEW_FLAG =", value, "interviewFlag");
            return (Criteria) this;
        }

        public Criteria andInterviewFlagNotEqualTo(String value) {
            addCriterion("INTERVIEW_FLAG <>", value, "interviewFlag");
            return (Criteria) this;
        }

        public Criteria andInterviewFlagGreaterThan(String value) {
            addCriterion("INTERVIEW_FLAG >", value, "interviewFlag");
            return (Criteria) this;
        }

        public Criteria andInterviewFlagGreaterThanOrEqualTo(String value) {
            addCriterion("INTERVIEW_FLAG >=", value, "interviewFlag");
            return (Criteria) this;
        }

        public Criteria andInterviewFlagLessThan(String value) {
            addCriterion("INTERVIEW_FLAG <", value, "interviewFlag");
            return (Criteria) this;
        }

        public Criteria andInterviewFlagLessThanOrEqualTo(String value) {
            addCriterion("INTERVIEW_FLAG <=", value, "interviewFlag");
            return (Criteria) this;
        }

        public Criteria andInterviewFlagLike(String value) {
            addCriterion("INTERVIEW_FLAG like", value, "interviewFlag");
            return (Criteria) this;
        }

        public Criteria andInterviewFlagNotLike(String value) {
            addCriterion("INTERVIEW_FLAG not like", value, "interviewFlag");
            return (Criteria) this;
        }

        public Criteria andInterviewFlagIn(List<String> values) {
            addCriterion("INTERVIEW_FLAG in", values, "interviewFlag");
            return (Criteria) this;
        }

        public Criteria andInterviewFlagNotIn(List<String> values) {
            addCriterion("INTERVIEW_FLAG not in", values, "interviewFlag");
            return (Criteria) this;
        }

        public Criteria andInterviewFlagBetween(String value1, String value2) {
            addCriterion("INTERVIEW_FLAG between", value1, value2, "interviewFlag");
            return (Criteria) this;
        }

        public Criteria andInterviewFlagNotBetween(String value1, String value2) {
            addCriterion("INTERVIEW_FLAG not between", value1, value2, "interviewFlag");
            return (Criteria) this;
        }

        public Criteria andInterviewScoreIsNull() {
            addCriterion("INTERVIEW_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andInterviewScoreIsNotNull() {
            addCriterion("INTERVIEW_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andInterviewScoreEqualTo(String value) {
            addCriterion("INTERVIEW_SCORE =", value, "interviewScore");
            return (Criteria) this;
        }

        public Criteria andInterviewScoreNotEqualTo(String value) {
            addCriterion("INTERVIEW_SCORE <>", value, "interviewScore");
            return (Criteria) this;
        }

        public Criteria andInterviewScoreGreaterThan(String value) {
            addCriterion("INTERVIEW_SCORE >", value, "interviewScore");
            return (Criteria) this;
        }

        public Criteria andInterviewScoreGreaterThanOrEqualTo(String value) {
            addCriterion("INTERVIEW_SCORE >=", value, "interviewScore");
            return (Criteria) this;
        }

        public Criteria andInterviewScoreLessThan(String value) {
            addCriterion("INTERVIEW_SCORE <", value, "interviewScore");
            return (Criteria) this;
        }

        public Criteria andInterviewScoreLessThanOrEqualTo(String value) {
            addCriterion("INTERVIEW_SCORE <=", value, "interviewScore");
            return (Criteria) this;
        }

        public Criteria andInterviewScoreLike(String value) {
            addCriterion("INTERVIEW_SCORE like", value, "interviewScore");
            return (Criteria) this;
        }

        public Criteria andInterviewScoreNotLike(String value) {
            addCriterion("INTERVIEW_SCORE not like", value, "interviewScore");
            return (Criteria) this;
        }

        public Criteria andInterviewScoreIn(List<String> values) {
            addCriterion("INTERVIEW_SCORE in", values, "interviewScore");
            return (Criteria) this;
        }

        public Criteria andInterviewScoreNotIn(List<String> values) {
            addCriterion("INTERVIEW_SCORE not in", values, "interviewScore");
            return (Criteria) this;
        }

        public Criteria andInterviewScoreBetween(String value1, String value2) {
            addCriterion("INTERVIEW_SCORE between", value1, value2, "interviewScore");
            return (Criteria) this;
        }

        public Criteria andInterviewScoreNotBetween(String value1, String value2) {
            addCriterion("INTERVIEW_SCORE not between", value1, value2, "interviewScore");
            return (Criteria) this;
        }

        public Criteria andInterviewIsPassIsNull() {
            addCriterion("INTERVIEW_IS_PASS is null");
            return (Criteria) this;
        }

        public Criteria andInterviewIsPassIsNotNull() {
            addCriterion("INTERVIEW_IS_PASS is not null");
            return (Criteria) this;
        }

        public Criteria andInterviewIsPassEqualTo(String value) {
            addCriterion("INTERVIEW_IS_PASS =", value, "interviewIsPass");
            return (Criteria) this;
        }

        public Criteria andInterviewIsPassNotEqualTo(String value) {
            addCriterion("INTERVIEW_IS_PASS <>", value, "interviewIsPass");
            return (Criteria) this;
        }

        public Criteria andInterviewIsPassGreaterThan(String value) {
            addCriterion("INTERVIEW_IS_PASS >", value, "interviewIsPass");
            return (Criteria) this;
        }

        public Criteria andInterviewIsPassGreaterThanOrEqualTo(String value) {
            addCriterion("INTERVIEW_IS_PASS >=", value, "interviewIsPass");
            return (Criteria) this;
        }

        public Criteria andInterviewIsPassLessThan(String value) {
            addCriterion("INTERVIEW_IS_PASS <", value, "interviewIsPass");
            return (Criteria) this;
        }

        public Criteria andInterviewIsPassLessThanOrEqualTo(String value) {
            addCriterion("INTERVIEW_IS_PASS <=", value, "interviewIsPass");
            return (Criteria) this;
        }

        public Criteria andInterviewIsPassLike(String value) {
            addCriterion("INTERVIEW_IS_PASS like", value, "interviewIsPass");
            return (Criteria) this;
        }

        public Criteria andInterviewIsPassNotLike(String value) {
            addCriterion("INTERVIEW_IS_PASS not like", value, "interviewIsPass");
            return (Criteria) this;
        }

        public Criteria andInterviewIsPassIn(List<String> values) {
            addCriterion("INTERVIEW_IS_PASS in", values, "interviewIsPass");
            return (Criteria) this;
        }

        public Criteria andInterviewIsPassNotIn(List<String> values) {
            addCriterion("INTERVIEW_IS_PASS not in", values, "interviewIsPass");
            return (Criteria) this;
        }

        public Criteria andInterviewIsPassBetween(String value1, String value2) {
            addCriterion("INTERVIEW_IS_PASS between", value1, value2, "interviewIsPass");
            return (Criteria) this;
        }

        public Criteria andInterviewIsPassNotBetween(String value1, String value2) {
            addCriterion("INTERVIEW_IS_PASS not between", value1, value2, "interviewIsPass");
            return (Criteria) this;
        }

        public Criteria andTicketNumberIsNull() {
            addCriterion("TICKET_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andTicketNumberIsNotNull() {
            addCriterion("TICKET_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andTicketNumberEqualTo(String value) {
            addCriterion("TICKET_NUMBER =", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberNotEqualTo(String value) {
            addCriterion("TICKET_NUMBER <>", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberGreaterThan(String value) {
            addCriterion("TICKET_NUMBER >", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberGreaterThanOrEqualTo(String value) {
            addCriterion("TICKET_NUMBER >=", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberLessThan(String value) {
            addCriterion("TICKET_NUMBER <", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberLessThanOrEqualTo(String value) {
            addCriterion("TICKET_NUMBER <=", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberLike(String value) {
            addCriterion("TICKET_NUMBER like", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberNotLike(String value) {
            addCriterion("TICKET_NUMBER not like", value, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberIn(List<String> values) {
            addCriterion("TICKET_NUMBER in", values, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberNotIn(List<String> values) {
            addCriterion("TICKET_NUMBER not in", values, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberBetween(String value1, String value2) {
            addCriterion("TICKET_NUMBER between", value1, value2, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andTicketNumberNotBetween(String value1, String value2) {
            addCriterion("TICKET_NUMBER not between", value1, value2, "ticketNumber");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagIsNull() {
            addCriterion("ADMIT_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagIsNotNull() {
            addCriterion("ADMIT_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagEqualTo(String value) {
            addCriterion("ADMIT_FLAG =", value, "admitFlag");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagNotEqualTo(String value) {
            addCriterion("ADMIT_FLAG <>", value, "admitFlag");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagGreaterThan(String value) {
            addCriterion("ADMIT_FLAG >", value, "admitFlag");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagGreaterThanOrEqualTo(String value) {
            addCriterion("ADMIT_FLAG >=", value, "admitFlag");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagLessThan(String value) {
            addCriterion("ADMIT_FLAG <", value, "admitFlag");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagLessThanOrEqualTo(String value) {
            addCriterion("ADMIT_FLAG <=", value, "admitFlag");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagLike(String value) {
            addCriterion("ADMIT_FLAG like", value, "admitFlag");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagNotLike(String value) {
            addCriterion("ADMIT_FLAG not like", value, "admitFlag");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagIn(List<String> values) {
            addCriterion("ADMIT_FLAG in", values, "admitFlag");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagNotIn(List<String> values) {
            addCriterion("ADMIT_FLAG not in", values, "admitFlag");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagBetween(String value1, String value2) {
            addCriterion("ADMIT_FLAG between", value1, value2, "admitFlag");
            return (Criteria) this;
        }

        public Criteria andAdmitFlagNotBetween(String value1, String value2) {
            addCriterion("ADMIT_FLAG not between", value1, value2, "admitFlag");
            return (Criteria) this;
        }

        public Criteria andAdmitIsPassIsNull() {
            addCriterion("ADMIT_IS_PASS is null");
            return (Criteria) this;
        }

        public Criteria andAdmitIsPassIsNotNull() {
            addCriterion("ADMIT_IS_PASS is not null");
            return (Criteria) this;
        }

        public Criteria andAdmitIsPassEqualTo(String value) {
            addCriterion("ADMIT_IS_PASS =", value, "admitIsPass");
            return (Criteria) this;
        }

        public Criteria andAdmitIsPassNotEqualTo(String value) {
            addCriterion("ADMIT_IS_PASS <>", value, "admitIsPass");
            return (Criteria) this;
        }

        public Criteria andAdmitIsPassGreaterThan(String value) {
            addCriterion("ADMIT_IS_PASS >", value, "admitIsPass");
            return (Criteria) this;
        }

        public Criteria andAdmitIsPassGreaterThanOrEqualTo(String value) {
            addCriterion("ADMIT_IS_PASS >=", value, "admitIsPass");
            return (Criteria) this;
        }

        public Criteria andAdmitIsPassLessThan(String value) {
            addCriterion("ADMIT_IS_PASS <", value, "admitIsPass");
            return (Criteria) this;
        }

        public Criteria andAdmitIsPassLessThanOrEqualTo(String value) {
            addCriterion("ADMIT_IS_PASS <=", value, "admitIsPass");
            return (Criteria) this;
        }

        public Criteria andAdmitIsPassLike(String value) {
            addCriterion("ADMIT_IS_PASS like", value, "admitIsPass");
            return (Criteria) this;
        }

        public Criteria andAdmitIsPassNotLike(String value) {
            addCriterion("ADMIT_IS_PASS not like", value, "admitIsPass");
            return (Criteria) this;
        }

        public Criteria andAdmitIsPassIn(List<String> values) {
            addCriterion("ADMIT_IS_PASS in", values, "admitIsPass");
            return (Criteria) this;
        }

        public Criteria andAdmitIsPassNotIn(List<String> values) {
            addCriterion("ADMIT_IS_PASS not in", values, "admitIsPass");
            return (Criteria) this;
        }

        public Criteria andAdmitIsPassBetween(String value1, String value2) {
            addCriterion("ADMIT_IS_PASS between", value1, value2, "admitIsPass");
            return (Criteria) this;
        }

        public Criteria andAdmitIsPassNotBetween(String value1, String value2) {
            addCriterion("ADMIT_IS_PASS not between", value1, value2, "admitIsPass");
            return (Criteria) this;
        }

        public Criteria andMainFlowIsNull() {
            addCriterion("MAIN_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andMainFlowIsNotNull() {
            addCriterion("MAIN_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andMainFlowEqualTo(String value) {
            addCriterion("MAIN_FLOW =", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowNotEqualTo(String value) {
            addCriterion("MAIN_FLOW <>", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowGreaterThan(String value) {
            addCriterion("MAIN_FLOW >", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowGreaterThanOrEqualTo(String value) {
            addCriterion("MAIN_FLOW >=", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowLessThan(String value) {
            addCriterion("MAIN_FLOW <", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowLessThanOrEqualTo(String value) {
            addCriterion("MAIN_FLOW <=", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowLike(String value) {
            addCriterion("MAIN_FLOW like", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowNotLike(String value) {
            addCriterion("MAIN_FLOW not like", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowIn(List<String> values) {
            addCriterion("MAIN_FLOW in", values, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowNotIn(List<String> values) {
            addCriterion("MAIN_FLOW not in", values, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowBetween(String value1, String value2) {
            addCriterion("MAIN_FLOW between", value1, value2, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowNotBetween(String value1, String value2) {
            addCriterion("MAIN_FLOW not between", value1, value2, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowIsNull() {
            addCriterion("EXAM_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andExamFlowIsNotNull() {
            addCriterion("EXAM_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andExamFlowEqualTo(String value) {
            addCriterion("EXAM_FLOW =", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowNotEqualTo(String value) {
            addCriterion("EXAM_FLOW <>", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowGreaterThan(String value) {
            addCriterion("EXAM_FLOW >", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_FLOW >=", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowLessThan(String value) {
            addCriterion("EXAM_FLOW <", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowLessThanOrEqualTo(String value) {
            addCriterion("EXAM_FLOW <=", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowLike(String value) {
            addCriterion("EXAM_FLOW like", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowNotLike(String value) {
            addCriterion("EXAM_FLOW not like", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowIn(List<String> values) {
            addCriterion("EXAM_FLOW in", values, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowNotIn(List<String> values) {
            addCriterion("EXAM_FLOW not in", values, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowBetween(String value1, String value2) {
            addCriterion("EXAM_FLOW between", value1, value2, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowNotBetween(String value1, String value2) {
            addCriterion("EXAM_FLOW not between", value1, value2, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamRoomFlowIsNull() {
            addCriterion("EXAM_ROOM_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andExamRoomFlowIsNotNull() {
            addCriterion("EXAM_ROOM_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andExamRoomFlowEqualTo(String value) {
            addCriterion("EXAM_ROOM_FLOW =", value, "examRoomFlow");
            return (Criteria) this;
        }

        public Criteria andExamRoomFlowNotEqualTo(String value) {
            addCriterion("EXAM_ROOM_FLOW <>", value, "examRoomFlow");
            return (Criteria) this;
        }

        public Criteria andExamRoomFlowGreaterThan(String value) {
            addCriterion("EXAM_ROOM_FLOW >", value, "examRoomFlow");
            return (Criteria) this;
        }

        public Criteria andExamRoomFlowGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_ROOM_FLOW >=", value, "examRoomFlow");
            return (Criteria) this;
        }

        public Criteria andExamRoomFlowLessThan(String value) {
            addCriterion("EXAM_ROOM_FLOW <", value, "examRoomFlow");
            return (Criteria) this;
        }

        public Criteria andExamRoomFlowLessThanOrEqualTo(String value) {
            addCriterion("EXAM_ROOM_FLOW <=", value, "examRoomFlow");
            return (Criteria) this;
        }

        public Criteria andExamRoomFlowLike(String value) {
            addCriterion("EXAM_ROOM_FLOW like", value, "examRoomFlow");
            return (Criteria) this;
        }

        public Criteria andExamRoomFlowNotLike(String value) {
            addCriterion("EXAM_ROOM_FLOW not like", value, "examRoomFlow");
            return (Criteria) this;
        }

        public Criteria andExamRoomFlowIn(List<String> values) {
            addCriterion("EXAM_ROOM_FLOW in", values, "examRoomFlow");
            return (Criteria) this;
        }

        public Criteria andExamRoomFlowNotIn(List<String> values) {
            addCriterion("EXAM_ROOM_FLOW not in", values, "examRoomFlow");
            return (Criteria) this;
        }

        public Criteria andExamRoomFlowBetween(String value1, String value2) {
            addCriterion("EXAM_ROOM_FLOW between", value1, value2, "examRoomFlow");
            return (Criteria) this;
        }

        public Criteria andExamRoomFlowNotBetween(String value1, String value2) {
            addCriterion("EXAM_ROOM_FLOW not between", value1, value2, "examRoomFlow");
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

        public Criteria andAuditContentIsNull() {
            addCriterion("AUDIT_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andAuditContentIsNotNull() {
            addCriterion("AUDIT_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andAuditContentEqualTo(String value) {
            addCriterion("AUDIT_CONTENT =", value, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentNotEqualTo(String value) {
            addCriterion("AUDIT_CONTENT <>", value, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentGreaterThan(String value) {
            addCriterion("AUDIT_CONTENT >", value, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_CONTENT >=", value, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentLessThan(String value) {
            addCriterion("AUDIT_CONTENT <", value, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_CONTENT <=", value, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentLike(String value) {
            addCriterion("AUDIT_CONTENT like", value, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentNotLike(String value) {
            addCriterion("AUDIT_CONTENT not like", value, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentIn(List<String> values) {
            addCriterion("AUDIT_CONTENT in", values, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentNotIn(List<String> values) {
            addCriterion("AUDIT_CONTENT not in", values, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentBetween(String value1, String value2) {
            addCriterion("AUDIT_CONTENT between", value1, value2, "auditContent");
            return (Criteria) this;
        }

        public Criteria andAuditContentNotBetween(String value1, String value2) {
            addCriterion("AUDIT_CONTENT not between", value1, value2, "auditContent");
            return (Criteria) this;
        }

        public Criteria andSaveTimeIsNull() {
            addCriterion("SAVE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSaveTimeIsNotNull() {
            addCriterion("SAVE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSaveTimeEqualTo(String value) {
            addCriterion("SAVE_TIME =", value, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeNotEqualTo(String value) {
            addCriterion("SAVE_TIME <>", value, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeGreaterThan(String value) {
            addCriterion("SAVE_TIME >", value, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SAVE_TIME >=", value, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeLessThan(String value) {
            addCriterion("SAVE_TIME <", value, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeLessThanOrEqualTo(String value) {
            addCriterion("SAVE_TIME <=", value, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeLike(String value) {
            addCriterion("SAVE_TIME like", value, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeNotLike(String value) {
            addCriterion("SAVE_TIME not like", value, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeIn(List<String> values) {
            addCriterion("SAVE_TIME in", values, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeNotIn(List<String> values) {
            addCriterion("SAVE_TIME not in", values, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeBetween(String value1, String value2) {
            addCriterion("SAVE_TIME between", value1, value2, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSaveTimeNotBetween(String value1, String value2) {
            addCriterion("SAVE_TIME not between", value1, value2, "saveTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNull() {
            addCriterion("SUBMIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNotNull() {
            addCriterion("SUBMIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeEqualTo(String value) {
            addCriterion("SUBMIT_TIME =", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotEqualTo(String value) {
            addCriterion("SUBMIT_TIME <>", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThan(String value) {
            addCriterion("SUBMIT_TIME >", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SUBMIT_TIME >=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThan(String value) {
            addCriterion("SUBMIT_TIME <", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThanOrEqualTo(String value) {
            addCriterion("SUBMIT_TIME <=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLike(String value) {
            addCriterion("SUBMIT_TIME like", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotLike(String value) {
            addCriterion("SUBMIT_TIME not like", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIn(List<String> values) {
            addCriterion("SUBMIT_TIME in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotIn(List<String> values) {
            addCriterion("SUBMIT_TIME not in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeBetween(String value1, String value2) {
            addCriterion("SUBMIT_TIME between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotBetween(String value1, String value2) {
            addCriterion("SUBMIT_TIME not between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNull() {
            addCriterion("AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNotNull() {
            addCriterion("AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeEqualTo(String value) {
            addCriterion("AUDIT_TIME =", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotEqualTo(String value) {
            addCriterion("AUDIT_TIME <>", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThan(String value) {
            addCriterion("AUDIT_TIME >", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_TIME >=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThan(String value) {
            addCriterion("AUDIT_TIME <", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_TIME <=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLike(String value) {
            addCriterion("AUDIT_TIME like", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotLike(String value) {
            addCriterion("AUDIT_TIME not like", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIn(List<String> values) {
            addCriterion("AUDIT_TIME in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotIn(List<String> values) {
            addCriterion("AUDIT_TIME not in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeBetween(String value1, String value2) {
            addCriterion("AUDIT_TIME between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotBetween(String value1, String value2) {
            addCriterion("AUDIT_TIME not between", value1, value2, "auditTime");
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