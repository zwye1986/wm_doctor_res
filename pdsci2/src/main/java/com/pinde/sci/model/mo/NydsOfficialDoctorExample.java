package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class NydsOfficialDoctorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NydsOfficialDoctorExample() {
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

        public Criteria andDoctorNameIsNull() {
            addCriterion("DOCTOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDoctorNameIsNotNull() {
            addCriterion("DOCTOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorNameEqualTo(String value) {
            addCriterion("DOCTOR_NAME =", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotEqualTo(String value) {
            addCriterion("DOCTOR_NAME <>", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameGreaterThan(String value) {
            addCriterion("DOCTOR_NAME >", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_NAME >=", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLessThan(String value) {
            addCriterion("DOCTOR_NAME <", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_NAME <=", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLike(String value) {
            addCriterion("DOCTOR_NAME like", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotLike(String value) {
            addCriterion("DOCTOR_NAME not like", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameIn(List<String> values) {
            addCriterion("DOCTOR_NAME in", values, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotIn(List<String> values) {
            addCriterion("DOCTOR_NAME not in", values, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameBetween(String value1, String value2) {
            addCriterion("DOCTOR_NAME between", value1, value2, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_NAME not between", value1, value2, "doctorName");
            return (Criteria) this;
        }

        public Criteria andSpellNameIsNull() {
            addCriterion("SPELL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSpellNameIsNotNull() {
            addCriterion("SPELL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSpellNameEqualTo(String value) {
            addCriterion("SPELL_NAME =", value, "spellName");
            return (Criteria) this;
        }

        public Criteria andSpellNameNotEqualTo(String value) {
            addCriterion("SPELL_NAME <>", value, "spellName");
            return (Criteria) this;
        }

        public Criteria andSpellNameGreaterThan(String value) {
            addCriterion("SPELL_NAME >", value, "spellName");
            return (Criteria) this;
        }

        public Criteria andSpellNameGreaterThanOrEqualTo(String value) {
            addCriterion("SPELL_NAME >=", value, "spellName");
            return (Criteria) this;
        }

        public Criteria andSpellNameLessThan(String value) {
            addCriterion("SPELL_NAME <", value, "spellName");
            return (Criteria) this;
        }

        public Criteria andSpellNameLessThanOrEqualTo(String value) {
            addCriterion("SPELL_NAME <=", value, "spellName");
            return (Criteria) this;
        }

        public Criteria andSpellNameLike(String value) {
            addCriterion("SPELL_NAME like", value, "spellName");
            return (Criteria) this;
        }

        public Criteria andSpellNameNotLike(String value) {
            addCriterion("SPELL_NAME not like", value, "spellName");
            return (Criteria) this;
        }

        public Criteria andSpellNameIn(List<String> values) {
            addCriterion("SPELL_NAME in", values, "spellName");
            return (Criteria) this;
        }

        public Criteria andSpellNameNotIn(List<String> values) {
            addCriterion("SPELL_NAME not in", values, "spellName");
            return (Criteria) this;
        }

        public Criteria andSpellNameBetween(String value1, String value2) {
            addCriterion("SPELL_NAME between", value1, value2, "spellName");
            return (Criteria) this;
        }

        public Criteria andSpellNameNotBetween(String value1, String value2) {
            addCriterion("SPELL_NAME not between", value1, value2, "spellName");
            return (Criteria) this;
        }

        public Criteria andBirthDayIsNull() {
            addCriterion("BIRTH_DAY is null");
            return (Criteria) this;
        }

        public Criteria andBirthDayIsNotNull() {
            addCriterion("BIRTH_DAY is not null");
            return (Criteria) this;
        }

        public Criteria andBirthDayEqualTo(String value) {
            addCriterion("BIRTH_DAY =", value, "birthDay");
            return (Criteria) this;
        }

        public Criteria andBirthDayNotEqualTo(String value) {
            addCriterion("BIRTH_DAY <>", value, "birthDay");
            return (Criteria) this;
        }

        public Criteria andBirthDayGreaterThan(String value) {
            addCriterion("BIRTH_DAY >", value, "birthDay");
            return (Criteria) this;
        }

        public Criteria andBirthDayGreaterThanOrEqualTo(String value) {
            addCriterion("BIRTH_DAY >=", value, "birthDay");
            return (Criteria) this;
        }

        public Criteria andBirthDayLessThan(String value) {
            addCriterion("BIRTH_DAY <", value, "birthDay");
            return (Criteria) this;
        }

        public Criteria andBirthDayLessThanOrEqualTo(String value) {
            addCriterion("BIRTH_DAY <=", value, "birthDay");
            return (Criteria) this;
        }

        public Criteria andBirthDayLike(String value) {
            addCriterion("BIRTH_DAY like", value, "birthDay");
            return (Criteria) this;
        }

        public Criteria andBirthDayNotLike(String value) {
            addCriterion("BIRTH_DAY not like", value, "birthDay");
            return (Criteria) this;
        }

        public Criteria andBirthDayIn(List<String> values) {
            addCriterion("BIRTH_DAY in", values, "birthDay");
            return (Criteria) this;
        }

        public Criteria andBirthDayNotIn(List<String> values) {
            addCriterion("BIRTH_DAY not in", values, "birthDay");
            return (Criteria) this;
        }

        public Criteria andBirthDayBetween(String value1, String value2) {
            addCriterion("BIRTH_DAY between", value1, value2, "birthDay");
            return (Criteria) this;
        }

        public Criteria andBirthDayNotBetween(String value1, String value2) {
            addCriterion("BIRTH_DAY not between", value1, value2, "birthDay");
            return (Criteria) this;
        }

        public Criteria andHeadUrlIsNull() {
            addCriterion("HEAD_URL is null");
            return (Criteria) this;
        }

        public Criteria andHeadUrlIsNotNull() {
            addCriterion("HEAD_URL is not null");
            return (Criteria) this;
        }

        public Criteria andHeadUrlEqualTo(String value) {
            addCriterion("HEAD_URL =", value, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlNotEqualTo(String value) {
            addCriterion("HEAD_URL <>", value, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlGreaterThan(String value) {
            addCriterion("HEAD_URL >", value, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlGreaterThanOrEqualTo(String value) {
            addCriterion("HEAD_URL >=", value, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlLessThan(String value) {
            addCriterion("HEAD_URL <", value, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlLessThanOrEqualTo(String value) {
            addCriterion("HEAD_URL <=", value, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlLike(String value) {
            addCriterion("HEAD_URL like", value, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlNotLike(String value) {
            addCriterion("HEAD_URL not like", value, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlIn(List<String> values) {
            addCriterion("HEAD_URL in", values, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlNotIn(List<String> values) {
            addCriterion("HEAD_URL not in", values, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlBetween(String value1, String value2) {
            addCriterion("HEAD_URL between", value1, value2, "headUrl");
            return (Criteria) this;
        }

        public Criteria andHeadUrlNotBetween(String value1, String value2) {
            addCriterion("HEAD_URL not between", value1, value2, "headUrl");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIsNull() {
            addCriterion("MOBILE_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIsNotNull() {
            addCriterion("MOBILE_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneEqualTo(String value) {
            addCriterion("MOBILE_PHONE =", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotEqualTo(String value) {
            addCriterion("MOBILE_PHONE <>", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneGreaterThan(String value) {
            addCriterion("MOBILE_PHONE >", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("MOBILE_PHONE >=", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLessThan(String value) {
            addCriterion("MOBILE_PHONE <", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLessThanOrEqualTo(String value) {
            addCriterion("MOBILE_PHONE <=", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLike(String value) {
            addCriterion("MOBILE_PHONE like", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotLike(String value) {
            addCriterion("MOBILE_PHONE not like", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIn(List<String> values) {
            addCriterion("MOBILE_PHONE in", values, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotIn(List<String> values) {
            addCriterion("MOBILE_PHONE not in", values, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneBetween(String value1, String value2) {
            addCriterion("MOBILE_PHONE between", value1, value2, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotBetween(String value1, String value2) {
            addCriterion("MOBILE_PHONE not between", value1, value2, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andWorkPhoneIsNull() {
            addCriterion("WORK_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andWorkPhoneIsNotNull() {
            addCriterion("WORK_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andWorkPhoneEqualTo(String value) {
            addCriterion("WORK_PHONE =", value, "workPhone");
            return (Criteria) this;
        }

        public Criteria andWorkPhoneNotEqualTo(String value) {
            addCriterion("WORK_PHONE <>", value, "workPhone");
            return (Criteria) this;
        }

        public Criteria andWorkPhoneGreaterThan(String value) {
            addCriterion("WORK_PHONE >", value, "workPhone");
            return (Criteria) this;
        }

        public Criteria andWorkPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_PHONE >=", value, "workPhone");
            return (Criteria) this;
        }

        public Criteria andWorkPhoneLessThan(String value) {
            addCriterion("WORK_PHONE <", value, "workPhone");
            return (Criteria) this;
        }

        public Criteria andWorkPhoneLessThanOrEqualTo(String value) {
            addCriterion("WORK_PHONE <=", value, "workPhone");
            return (Criteria) this;
        }

        public Criteria andWorkPhoneLike(String value) {
            addCriterion("WORK_PHONE like", value, "workPhone");
            return (Criteria) this;
        }

        public Criteria andWorkPhoneNotLike(String value) {
            addCriterion("WORK_PHONE not like", value, "workPhone");
            return (Criteria) this;
        }

        public Criteria andWorkPhoneIn(List<String> values) {
            addCriterion("WORK_PHONE in", values, "workPhone");
            return (Criteria) this;
        }

        public Criteria andWorkPhoneNotIn(List<String> values) {
            addCriterion("WORK_PHONE not in", values, "workPhone");
            return (Criteria) this;
        }

        public Criteria andWorkPhoneBetween(String value1, String value2) {
            addCriterion("WORK_PHONE between", value1, value2, "workPhone");
            return (Criteria) this;
        }

        public Criteria andWorkPhoneNotBetween(String value1, String value2) {
            addCriterion("WORK_PHONE not between", value1, value2, "workPhone");
            return (Criteria) this;
        }

        public Criteria andEmailNoIsNull() {
            addCriterion("EMAIL_NO is null");
            return (Criteria) this;
        }

        public Criteria andEmailNoIsNotNull() {
            addCriterion("EMAIL_NO is not null");
            return (Criteria) this;
        }

        public Criteria andEmailNoEqualTo(String value) {
            addCriterion("EMAIL_NO =", value, "emailNo");
            return (Criteria) this;
        }

        public Criteria andEmailNoNotEqualTo(String value) {
            addCriterion("EMAIL_NO <>", value, "emailNo");
            return (Criteria) this;
        }

        public Criteria andEmailNoGreaterThan(String value) {
            addCriterion("EMAIL_NO >", value, "emailNo");
            return (Criteria) this;
        }

        public Criteria andEmailNoGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL_NO >=", value, "emailNo");
            return (Criteria) this;
        }

        public Criteria andEmailNoLessThan(String value) {
            addCriterion("EMAIL_NO <", value, "emailNo");
            return (Criteria) this;
        }

        public Criteria andEmailNoLessThanOrEqualTo(String value) {
            addCriterion("EMAIL_NO <=", value, "emailNo");
            return (Criteria) this;
        }

        public Criteria andEmailNoLike(String value) {
            addCriterion("EMAIL_NO like", value, "emailNo");
            return (Criteria) this;
        }

        public Criteria andEmailNoNotLike(String value) {
            addCriterion("EMAIL_NO not like", value, "emailNo");
            return (Criteria) this;
        }

        public Criteria andEmailNoIn(List<String> values) {
            addCriterion("EMAIL_NO in", values, "emailNo");
            return (Criteria) this;
        }

        public Criteria andEmailNoNotIn(List<String> values) {
            addCriterion("EMAIL_NO not in", values, "emailNo");
            return (Criteria) this;
        }

        public Criteria andEmailNoBetween(String value1, String value2) {
            addCriterion("EMAIL_NO between", value1, value2, "emailNo");
            return (Criteria) this;
        }

        public Criteria andEmailNoNotBetween(String value1, String value2) {
            addCriterion("EMAIL_NO not between", value1, value2, "emailNo");
            return (Criteria) this;
        }

        public Criteria andWorkUnitIsNull() {
            addCriterion("WORK_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andWorkUnitIsNotNull() {
            addCriterion("WORK_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andWorkUnitEqualTo(String value) {
            addCriterion("WORK_UNIT =", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitNotEqualTo(String value) {
            addCriterion("WORK_UNIT <>", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitGreaterThan(String value) {
            addCriterion("WORK_UNIT >", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_UNIT >=", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitLessThan(String value) {
            addCriterion("WORK_UNIT <", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitLessThanOrEqualTo(String value) {
            addCriterion("WORK_UNIT <=", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitLike(String value) {
            addCriterion("WORK_UNIT like", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitNotLike(String value) {
            addCriterion("WORK_UNIT not like", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitIn(List<String> values) {
            addCriterion("WORK_UNIT in", values, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitNotIn(List<String> values) {
            addCriterion("WORK_UNIT not in", values, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitBetween(String value1, String value2) {
            addCriterion("WORK_UNIT between", value1, value2, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitNotBetween(String value1, String value2) {
            addCriterion("WORK_UNIT not between", value1, value2, "workUnit");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleIdIsNull() {
            addCriterion("TECHNICAL_TITLE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleIdIsNotNull() {
            addCriterion("TECHNICAL_TITLE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleIdEqualTo(String value) {
            addCriterion("TECHNICAL_TITLE_ID =", value, "technicalTitleId");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleIdNotEqualTo(String value) {
            addCriterion("TECHNICAL_TITLE_ID <>", value, "technicalTitleId");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleIdGreaterThan(String value) {
            addCriterion("TECHNICAL_TITLE_ID >", value, "technicalTitleId");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleIdGreaterThanOrEqualTo(String value) {
            addCriterion("TECHNICAL_TITLE_ID >=", value, "technicalTitleId");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleIdLessThan(String value) {
            addCriterion("TECHNICAL_TITLE_ID <", value, "technicalTitleId");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleIdLessThanOrEqualTo(String value) {
            addCriterion("TECHNICAL_TITLE_ID <=", value, "technicalTitleId");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleIdLike(String value) {
            addCriterion("TECHNICAL_TITLE_ID like", value, "technicalTitleId");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleIdNotLike(String value) {
            addCriterion("TECHNICAL_TITLE_ID not like", value, "technicalTitleId");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleIdIn(List<String> values) {
            addCriterion("TECHNICAL_TITLE_ID in", values, "technicalTitleId");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleIdNotIn(List<String> values) {
            addCriterion("TECHNICAL_TITLE_ID not in", values, "technicalTitleId");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleIdBetween(String value1, String value2) {
            addCriterion("TECHNICAL_TITLE_ID between", value1, value2, "technicalTitleId");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleIdNotBetween(String value1, String value2) {
            addCriterion("TECHNICAL_TITLE_ID not between", value1, value2, "technicalTitleId");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleNameIsNull() {
            addCriterion("TECHNICAL_TITLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleNameIsNotNull() {
            addCriterion("TECHNICAL_TITLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleNameEqualTo(String value) {
            addCriterion("TECHNICAL_TITLE_NAME =", value, "technicalTitleName");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleNameNotEqualTo(String value) {
            addCriterion("TECHNICAL_TITLE_NAME <>", value, "technicalTitleName");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleNameGreaterThan(String value) {
            addCriterion("TECHNICAL_TITLE_NAME >", value, "technicalTitleName");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleNameGreaterThanOrEqualTo(String value) {
            addCriterion("TECHNICAL_TITLE_NAME >=", value, "technicalTitleName");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleNameLessThan(String value) {
            addCriterion("TECHNICAL_TITLE_NAME <", value, "technicalTitleName");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleNameLessThanOrEqualTo(String value) {
            addCriterion("TECHNICAL_TITLE_NAME <=", value, "technicalTitleName");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleNameLike(String value) {
            addCriterion("TECHNICAL_TITLE_NAME like", value, "technicalTitleName");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleNameNotLike(String value) {
            addCriterion("TECHNICAL_TITLE_NAME not like", value, "technicalTitleName");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleNameIn(List<String> values) {
            addCriterion("TECHNICAL_TITLE_NAME in", values, "technicalTitleName");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleNameNotIn(List<String> values) {
            addCriterion("TECHNICAL_TITLE_NAME not in", values, "technicalTitleName");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleNameBetween(String value1, String value2) {
            addCriterion("TECHNICAL_TITLE_NAME between", value1, value2, "technicalTitleName");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleNameNotBetween(String value1, String value2) {
            addCriterion("TECHNICAL_TITLE_NAME not between", value1, value2, "technicalTitleName");
            return (Criteria) this;
        }

        public Criteria andAcademicActivitiesIsNull() {
            addCriterion("ACADEMIC_ACTIVITIES is null");
            return (Criteria) this;
        }

        public Criteria andAcademicActivitiesIsNotNull() {
            addCriterion("ACADEMIC_ACTIVITIES is not null");
            return (Criteria) this;
        }

        public Criteria andAcademicActivitiesEqualTo(String value) {
            addCriterion("ACADEMIC_ACTIVITIES =", value, "academicActivities");
            return (Criteria) this;
        }

        public Criteria andAcademicActivitiesNotEqualTo(String value) {
            addCriterion("ACADEMIC_ACTIVITIES <>", value, "academicActivities");
            return (Criteria) this;
        }

        public Criteria andAcademicActivitiesGreaterThan(String value) {
            addCriterion("ACADEMIC_ACTIVITIES >", value, "academicActivities");
            return (Criteria) this;
        }

        public Criteria andAcademicActivitiesGreaterThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_ACTIVITIES >=", value, "academicActivities");
            return (Criteria) this;
        }

        public Criteria andAcademicActivitiesLessThan(String value) {
            addCriterion("ACADEMIC_ACTIVITIES <", value, "academicActivities");
            return (Criteria) this;
        }

        public Criteria andAcademicActivitiesLessThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_ACTIVITIES <=", value, "academicActivities");
            return (Criteria) this;
        }

        public Criteria andAcademicActivitiesLike(String value) {
            addCriterion("ACADEMIC_ACTIVITIES like", value, "academicActivities");
            return (Criteria) this;
        }

        public Criteria andAcademicActivitiesNotLike(String value) {
            addCriterion("ACADEMIC_ACTIVITIES not like", value, "academicActivities");
            return (Criteria) this;
        }

        public Criteria andAcademicActivitiesIn(List<String> values) {
            addCriterion("ACADEMIC_ACTIVITIES in", values, "academicActivities");
            return (Criteria) this;
        }

        public Criteria andAcademicActivitiesNotIn(List<String> values) {
            addCriterion("ACADEMIC_ACTIVITIES not in", values, "academicActivities");
            return (Criteria) this;
        }

        public Criteria andAcademicActivitiesBetween(String value1, String value2) {
            addCriterion("ACADEMIC_ACTIVITIES between", value1, value2, "academicActivities");
            return (Criteria) this;
        }

        public Criteria andAcademicActivitiesNotBetween(String value1, String value2) {
            addCriterion("ACADEMIC_ACTIVITIES not between", value1, value2, "academicActivities");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionIsNull() {
            addCriterion("RESEARCH_DIRECTION is null");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionIsNotNull() {
            addCriterion("RESEARCH_DIRECTION is not null");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionEqualTo(String value) {
            addCriterion("RESEARCH_DIRECTION =", value, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionNotEqualTo(String value) {
            addCriterion("RESEARCH_DIRECTION <>", value, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionGreaterThan(String value) {
            addCriterion("RESEARCH_DIRECTION >", value, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionGreaterThanOrEqualTo(String value) {
            addCriterion("RESEARCH_DIRECTION >=", value, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionLessThan(String value) {
            addCriterion("RESEARCH_DIRECTION <", value, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionLessThanOrEqualTo(String value) {
            addCriterion("RESEARCH_DIRECTION <=", value, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionLike(String value) {
            addCriterion("RESEARCH_DIRECTION like", value, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionNotLike(String value) {
            addCriterion("RESEARCH_DIRECTION not like", value, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionIn(List<String> values) {
            addCriterion("RESEARCH_DIRECTION in", values, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionNotIn(List<String> values) {
            addCriterion("RESEARCH_DIRECTION not in", values, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionBetween(String value1, String value2) {
            addCriterion("RESEARCH_DIRECTION between", value1, value2, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andResearchDirectionNotBetween(String value1, String value2) {
            addCriterion("RESEARCH_DIRECTION not between", value1, value2, "researchDirection");
            return (Criteria) this;
        }

        public Criteria andAcademicMonographsIsNull() {
            addCriterion("ACADEMIC_MONOGRAPHS is null");
            return (Criteria) this;
        }

        public Criteria andAcademicMonographsIsNotNull() {
            addCriterion("ACADEMIC_MONOGRAPHS is not null");
            return (Criteria) this;
        }

        public Criteria andAcademicMonographsEqualTo(String value) {
            addCriterion("ACADEMIC_MONOGRAPHS =", value, "academicMonographs");
            return (Criteria) this;
        }

        public Criteria andAcademicMonographsNotEqualTo(String value) {
            addCriterion("ACADEMIC_MONOGRAPHS <>", value, "academicMonographs");
            return (Criteria) this;
        }

        public Criteria andAcademicMonographsGreaterThan(String value) {
            addCriterion("ACADEMIC_MONOGRAPHS >", value, "academicMonographs");
            return (Criteria) this;
        }

        public Criteria andAcademicMonographsGreaterThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_MONOGRAPHS >=", value, "academicMonographs");
            return (Criteria) this;
        }

        public Criteria andAcademicMonographsLessThan(String value) {
            addCriterion("ACADEMIC_MONOGRAPHS <", value, "academicMonographs");
            return (Criteria) this;
        }

        public Criteria andAcademicMonographsLessThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_MONOGRAPHS <=", value, "academicMonographs");
            return (Criteria) this;
        }

        public Criteria andAcademicMonographsLike(String value) {
            addCriterion("ACADEMIC_MONOGRAPHS like", value, "academicMonographs");
            return (Criteria) this;
        }

        public Criteria andAcademicMonographsNotLike(String value) {
            addCriterion("ACADEMIC_MONOGRAPHS not like", value, "academicMonographs");
            return (Criteria) this;
        }

        public Criteria andAcademicMonographsIn(List<String> values) {
            addCriterion("ACADEMIC_MONOGRAPHS in", values, "academicMonographs");
            return (Criteria) this;
        }

        public Criteria andAcademicMonographsNotIn(List<String> values) {
            addCriterion("ACADEMIC_MONOGRAPHS not in", values, "academicMonographs");
            return (Criteria) this;
        }

        public Criteria andAcademicMonographsBetween(String value1, String value2) {
            addCriterion("ACADEMIC_MONOGRAPHS between", value1, value2, "academicMonographs");
            return (Criteria) this;
        }

        public Criteria andAcademicMonographsNotBetween(String value1, String value2) {
            addCriterion("ACADEMIC_MONOGRAPHS not between", value1, value2, "academicMonographs");
            return (Criteria) this;
        }

        public Criteria andAwardSituationIsNull() {
            addCriterion("AWARD_SITUATION is null");
            return (Criteria) this;
        }

        public Criteria andAwardSituationIsNotNull() {
            addCriterion("AWARD_SITUATION is not null");
            return (Criteria) this;
        }

        public Criteria andAwardSituationEqualTo(String value) {
            addCriterion("AWARD_SITUATION =", value, "awardSituation");
            return (Criteria) this;
        }

        public Criteria andAwardSituationNotEqualTo(String value) {
            addCriterion("AWARD_SITUATION <>", value, "awardSituation");
            return (Criteria) this;
        }

        public Criteria andAwardSituationGreaterThan(String value) {
            addCriterion("AWARD_SITUATION >", value, "awardSituation");
            return (Criteria) this;
        }

        public Criteria andAwardSituationGreaterThanOrEqualTo(String value) {
            addCriterion("AWARD_SITUATION >=", value, "awardSituation");
            return (Criteria) this;
        }

        public Criteria andAwardSituationLessThan(String value) {
            addCriterion("AWARD_SITUATION <", value, "awardSituation");
            return (Criteria) this;
        }

        public Criteria andAwardSituationLessThanOrEqualTo(String value) {
            addCriterion("AWARD_SITUATION <=", value, "awardSituation");
            return (Criteria) this;
        }

        public Criteria andAwardSituationLike(String value) {
            addCriterion("AWARD_SITUATION like", value, "awardSituation");
            return (Criteria) this;
        }

        public Criteria andAwardSituationNotLike(String value) {
            addCriterion("AWARD_SITUATION not like", value, "awardSituation");
            return (Criteria) this;
        }

        public Criteria andAwardSituationIn(List<String> values) {
            addCriterion("AWARD_SITUATION in", values, "awardSituation");
            return (Criteria) this;
        }

        public Criteria andAwardSituationNotIn(List<String> values) {
            addCriterion("AWARD_SITUATION not in", values, "awardSituation");
            return (Criteria) this;
        }

        public Criteria andAwardSituationBetween(String value1, String value2) {
            addCriterion("AWARD_SITUATION between", value1, value2, "awardSituation");
            return (Criteria) this;
        }

        public Criteria andAwardSituationNotBetween(String value1, String value2) {
            addCriterion("AWARD_SITUATION not between", value1, value2, "awardSituation");
            return (Criteria) this;
        }

        public Criteria andResearchDescribeIsNull() {
            addCriterion("RESEARCH_DESCRIBE is null");
            return (Criteria) this;
        }

        public Criteria andResearchDescribeIsNotNull() {
            addCriterion("RESEARCH_DESCRIBE is not null");
            return (Criteria) this;
        }

        public Criteria andResearchDescribeEqualTo(String value) {
            addCriterion("RESEARCH_DESCRIBE =", value, "researchDescribe");
            return (Criteria) this;
        }

        public Criteria andResearchDescribeNotEqualTo(String value) {
            addCriterion("RESEARCH_DESCRIBE <>", value, "researchDescribe");
            return (Criteria) this;
        }

        public Criteria andResearchDescribeGreaterThan(String value) {
            addCriterion("RESEARCH_DESCRIBE >", value, "researchDescribe");
            return (Criteria) this;
        }

        public Criteria andResearchDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("RESEARCH_DESCRIBE >=", value, "researchDescribe");
            return (Criteria) this;
        }

        public Criteria andResearchDescribeLessThan(String value) {
            addCriterion("RESEARCH_DESCRIBE <", value, "researchDescribe");
            return (Criteria) this;
        }

        public Criteria andResearchDescribeLessThanOrEqualTo(String value) {
            addCriterion("RESEARCH_DESCRIBE <=", value, "researchDescribe");
            return (Criteria) this;
        }

        public Criteria andResearchDescribeLike(String value) {
            addCriterion("RESEARCH_DESCRIBE like", value, "researchDescribe");
            return (Criteria) this;
        }

        public Criteria andResearchDescribeNotLike(String value) {
            addCriterion("RESEARCH_DESCRIBE not like", value, "researchDescribe");
            return (Criteria) this;
        }

        public Criteria andResearchDescribeIn(List<String> values) {
            addCriterion("RESEARCH_DESCRIBE in", values, "researchDescribe");
            return (Criteria) this;
        }

        public Criteria andResearchDescribeNotIn(List<String> values) {
            addCriterion("RESEARCH_DESCRIBE not in", values, "researchDescribe");
            return (Criteria) this;
        }

        public Criteria andResearchDescribeBetween(String value1, String value2) {
            addCriterion("RESEARCH_DESCRIBE between", value1, value2, "researchDescribe");
            return (Criteria) this;
        }

        public Criteria andResearchDescribeNotBetween(String value1, String value2) {
            addCriterion("RESEARCH_DESCRIBE not between", value1, value2, "researchDescribe");
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

        public Criteria andFwhOrgFlowIsNull() {
            addCriterion("FWH_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowIsNotNull() {
            addCriterion("FWH_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowEqualTo(String value) {
            addCriterion("FWH_ORG_FLOW =", value, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowNotEqualTo(String value) {
            addCriterion("FWH_ORG_FLOW <>", value, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowGreaterThan(String value) {
            addCriterion("FWH_ORG_FLOW >", value, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("FWH_ORG_FLOW >=", value, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowLessThan(String value) {
            addCriterion("FWH_ORG_FLOW <", value, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("FWH_ORG_FLOW <=", value, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowLike(String value) {
            addCriterion("FWH_ORG_FLOW like", value, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowNotLike(String value) {
            addCriterion("FWH_ORG_FLOW not like", value, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowIn(List<String> values) {
            addCriterion("FWH_ORG_FLOW in", values, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowNotIn(List<String> values) {
            addCriterion("FWH_ORG_FLOW not in", values, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowBetween(String value1, String value2) {
            addCriterion("FWH_ORG_FLOW between", value1, value2, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowNotBetween(String value1, String value2) {
            addCriterion("FWH_ORG_FLOW not between", value1, value2, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameIsNull() {
            addCriterion("FWH_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameIsNotNull() {
            addCriterion("FWH_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameEqualTo(String value) {
            addCriterion("FWH_ORG_NAME =", value, "fwhOrgName");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameNotEqualTo(String value) {
            addCriterion("FWH_ORG_NAME <>", value, "fwhOrgName");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameGreaterThan(String value) {
            addCriterion("FWH_ORG_NAME >", value, "fwhOrgName");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("FWH_ORG_NAME >=", value, "fwhOrgName");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameLessThan(String value) {
            addCriterion("FWH_ORG_NAME <", value, "fwhOrgName");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameLessThanOrEqualTo(String value) {
            addCriterion("FWH_ORG_NAME <=", value, "fwhOrgName");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameLike(String value) {
            addCriterion("FWH_ORG_NAME like", value, "fwhOrgName");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameNotLike(String value) {
            addCriterion("FWH_ORG_NAME not like", value, "fwhOrgName");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameIn(List<String> values) {
            addCriterion("FWH_ORG_NAME in", values, "fwhOrgName");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameNotIn(List<String> values) {
            addCriterion("FWH_ORG_NAME not in", values, "fwhOrgName");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameBetween(String value1, String value2) {
            addCriterion("FWH_ORG_NAME between", value1, value2, "fwhOrgName");
            return (Criteria) this;
        }

        public Criteria andFwhOrgNameNotBetween(String value1, String value2) {
            addCriterion("FWH_ORG_NAME not between", value1, value2, "fwhOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowIsNull() {
            addCriterion("PYDW_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowIsNotNull() {
            addCriterion("PYDW_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowEqualTo(String value) {
            addCriterion("PYDW_ORG_FLOW =", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowNotEqualTo(String value) {
            addCriterion("PYDW_ORG_FLOW <>", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowGreaterThan(String value) {
            addCriterion("PYDW_ORG_FLOW >", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PYDW_ORG_FLOW >=", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowLessThan(String value) {
            addCriterion("PYDW_ORG_FLOW <", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("PYDW_ORG_FLOW <=", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowLike(String value) {
            addCriterion("PYDW_ORG_FLOW like", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowNotLike(String value) {
            addCriterion("PYDW_ORG_FLOW not like", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowIn(List<String> values) {
            addCriterion("PYDW_ORG_FLOW in", values, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowNotIn(List<String> values) {
            addCriterion("PYDW_ORG_FLOW not in", values, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowBetween(String value1, String value2) {
            addCriterion("PYDW_ORG_FLOW between", value1, value2, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowNotBetween(String value1, String value2) {
            addCriterion("PYDW_ORG_FLOW not between", value1, value2, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameIsNull() {
            addCriterion("PYDW_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameIsNotNull() {
            addCriterion("PYDW_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameEqualTo(String value) {
            addCriterion("PYDW_ORG_NAME =", value, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameNotEqualTo(String value) {
            addCriterion("PYDW_ORG_NAME <>", value, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameGreaterThan(String value) {
            addCriterion("PYDW_ORG_NAME >", value, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("PYDW_ORG_NAME >=", value, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameLessThan(String value) {
            addCriterion("PYDW_ORG_NAME <", value, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameLessThanOrEqualTo(String value) {
            addCriterion("PYDW_ORG_NAME <=", value, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameLike(String value) {
            addCriterion("PYDW_ORG_NAME like", value, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameNotLike(String value) {
            addCriterion("PYDW_ORG_NAME not like", value, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameIn(List<String> values) {
            addCriterion("PYDW_ORG_NAME in", values, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameNotIn(List<String> values) {
            addCriterion("PYDW_ORG_NAME not in", values, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameBetween(String value1, String value2) {
            addCriterion("PYDW_ORG_NAME between", value1, value2, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameNotBetween(String value1, String value2) {
            addCriterion("PYDW_ORG_NAME not between", value1, value2, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeIdIsNull() {
            addCriterion("RECRUIT_SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeIdIsNotNull() {
            addCriterion("RECRUIT_SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeIdEqualTo(String value) {
            addCriterion("RECRUIT_SPE_ID =", value, "recruitSpeId");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeIdNotEqualTo(String value) {
            addCriterion("RECRUIT_SPE_ID <>", value, "recruitSpeId");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeIdGreaterThan(String value) {
            addCriterion("RECRUIT_SPE_ID >", value, "recruitSpeId");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("RECRUIT_SPE_ID >=", value, "recruitSpeId");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeIdLessThan(String value) {
            addCriterion("RECRUIT_SPE_ID <", value, "recruitSpeId");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeIdLessThanOrEqualTo(String value) {
            addCriterion("RECRUIT_SPE_ID <=", value, "recruitSpeId");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeIdLike(String value) {
            addCriterion("RECRUIT_SPE_ID like", value, "recruitSpeId");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeIdNotLike(String value) {
            addCriterion("RECRUIT_SPE_ID not like", value, "recruitSpeId");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeIdIn(List<String> values) {
            addCriterion("RECRUIT_SPE_ID in", values, "recruitSpeId");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeIdNotIn(List<String> values) {
            addCriterion("RECRUIT_SPE_ID not in", values, "recruitSpeId");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeIdBetween(String value1, String value2) {
            addCriterion("RECRUIT_SPE_ID between", value1, value2, "recruitSpeId");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeIdNotBetween(String value1, String value2) {
            addCriterion("RECRUIT_SPE_ID not between", value1, value2, "recruitSpeId");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeNameIsNull() {
            addCriterion("RECRUIT_SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeNameIsNotNull() {
            addCriterion("RECRUIT_SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeNameEqualTo(String value) {
            addCriterion("RECRUIT_SPE_NAME =", value, "recruitSpeName");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeNameNotEqualTo(String value) {
            addCriterion("RECRUIT_SPE_NAME <>", value, "recruitSpeName");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeNameGreaterThan(String value) {
            addCriterion("RECRUIT_SPE_NAME >", value, "recruitSpeName");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("RECRUIT_SPE_NAME >=", value, "recruitSpeName");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeNameLessThan(String value) {
            addCriterion("RECRUIT_SPE_NAME <", value, "recruitSpeName");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeNameLessThanOrEqualTo(String value) {
            addCriterion("RECRUIT_SPE_NAME <=", value, "recruitSpeName");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeNameLike(String value) {
            addCriterion("RECRUIT_SPE_NAME like", value, "recruitSpeName");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeNameNotLike(String value) {
            addCriterion("RECRUIT_SPE_NAME not like", value, "recruitSpeName");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeNameIn(List<String> values) {
            addCriterion("RECRUIT_SPE_NAME in", values, "recruitSpeName");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeNameNotIn(List<String> values) {
            addCriterion("RECRUIT_SPE_NAME not in", values, "recruitSpeName");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeNameBetween(String value1, String value2) {
            addCriterion("RECRUIT_SPE_NAME between", value1, value2, "recruitSpeName");
            return (Criteria) this;
        }

        public Criteria andRecruitSpeNameNotBetween(String value1, String value2) {
            addCriterion("RECRUIT_SPE_NAME not between", value1, value2, "recruitSpeName");
            return (Criteria) this;
        }

        public Criteria andZdyjsMasterNumIsNull() {
            addCriterion("ZDYJS_MASTER_NUM is null");
            return (Criteria) this;
        }

        public Criteria andZdyjsMasterNumIsNotNull() {
            addCriterion("ZDYJS_MASTER_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andZdyjsMasterNumEqualTo(String value) {
            addCriterion("ZDYJS_MASTER_NUM =", value, "zdyjsMasterNum");
            return (Criteria) this;
        }

        public Criteria andZdyjsMasterNumNotEqualTo(String value) {
            addCriterion("ZDYJS_MASTER_NUM <>", value, "zdyjsMasterNum");
            return (Criteria) this;
        }

        public Criteria andZdyjsMasterNumGreaterThan(String value) {
            addCriterion("ZDYJS_MASTER_NUM >", value, "zdyjsMasterNum");
            return (Criteria) this;
        }

        public Criteria andZdyjsMasterNumGreaterThanOrEqualTo(String value) {
            addCriterion("ZDYJS_MASTER_NUM >=", value, "zdyjsMasterNum");
            return (Criteria) this;
        }

        public Criteria andZdyjsMasterNumLessThan(String value) {
            addCriterion("ZDYJS_MASTER_NUM <", value, "zdyjsMasterNum");
            return (Criteria) this;
        }

        public Criteria andZdyjsMasterNumLessThanOrEqualTo(String value) {
            addCriterion("ZDYJS_MASTER_NUM <=", value, "zdyjsMasterNum");
            return (Criteria) this;
        }

        public Criteria andZdyjsMasterNumLike(String value) {
            addCriterion("ZDYJS_MASTER_NUM like", value, "zdyjsMasterNum");
            return (Criteria) this;
        }

        public Criteria andZdyjsMasterNumNotLike(String value) {
            addCriterion("ZDYJS_MASTER_NUM not like", value, "zdyjsMasterNum");
            return (Criteria) this;
        }

        public Criteria andZdyjsMasterNumIn(List<String> values) {
            addCriterion("ZDYJS_MASTER_NUM in", values, "zdyjsMasterNum");
            return (Criteria) this;
        }

        public Criteria andZdyjsMasterNumNotIn(List<String> values) {
            addCriterion("ZDYJS_MASTER_NUM not in", values, "zdyjsMasterNum");
            return (Criteria) this;
        }

        public Criteria andZdyjsMasterNumBetween(String value1, String value2) {
            addCriterion("ZDYJS_MASTER_NUM between", value1, value2, "zdyjsMasterNum");
            return (Criteria) this;
        }

        public Criteria andZdyjsMasterNumNotBetween(String value1, String value2) {
            addCriterion("ZDYJS_MASTER_NUM not between", value1, value2, "zdyjsMasterNum");
            return (Criteria) this;
        }

        public Criteria andSbyjsMasterNumIsNull() {
            addCriterion("SBYJS_MASTER_NUM is null");
            return (Criteria) this;
        }

        public Criteria andSbyjsMasterNumIsNotNull() {
            addCriterion("SBYJS_MASTER_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andSbyjsMasterNumEqualTo(String value) {
            addCriterion("SBYJS_MASTER_NUM =", value, "sbyjsMasterNum");
            return (Criteria) this;
        }

        public Criteria andSbyjsMasterNumNotEqualTo(String value) {
            addCriterion("SBYJS_MASTER_NUM <>", value, "sbyjsMasterNum");
            return (Criteria) this;
        }

        public Criteria andSbyjsMasterNumGreaterThan(String value) {
            addCriterion("SBYJS_MASTER_NUM >", value, "sbyjsMasterNum");
            return (Criteria) this;
        }

        public Criteria andSbyjsMasterNumGreaterThanOrEqualTo(String value) {
            addCriterion("SBYJS_MASTER_NUM >=", value, "sbyjsMasterNum");
            return (Criteria) this;
        }

        public Criteria andSbyjsMasterNumLessThan(String value) {
            addCriterion("SBYJS_MASTER_NUM <", value, "sbyjsMasterNum");
            return (Criteria) this;
        }

        public Criteria andSbyjsMasterNumLessThanOrEqualTo(String value) {
            addCriterion("SBYJS_MASTER_NUM <=", value, "sbyjsMasterNum");
            return (Criteria) this;
        }

        public Criteria andSbyjsMasterNumLike(String value) {
            addCriterion("SBYJS_MASTER_NUM like", value, "sbyjsMasterNum");
            return (Criteria) this;
        }

        public Criteria andSbyjsMasterNumNotLike(String value) {
            addCriterion("SBYJS_MASTER_NUM not like", value, "sbyjsMasterNum");
            return (Criteria) this;
        }

        public Criteria andSbyjsMasterNumIn(List<String> values) {
            addCriterion("SBYJS_MASTER_NUM in", values, "sbyjsMasterNum");
            return (Criteria) this;
        }

        public Criteria andSbyjsMasterNumNotIn(List<String> values) {
            addCriterion("SBYJS_MASTER_NUM not in", values, "sbyjsMasterNum");
            return (Criteria) this;
        }

        public Criteria andSbyjsMasterNumBetween(String value1, String value2) {
            addCriterion("SBYJS_MASTER_NUM between", value1, value2, "sbyjsMasterNum");
            return (Criteria) this;
        }

        public Criteria andSbyjsMasterNumNotBetween(String value1, String value2) {
            addCriterion("SBYJS_MASTER_NUM not between", value1, value2, "sbyjsMasterNum");
            return (Criteria) this;
        }

        public Criteria andZdyjsDoctorNumIsNull() {
            addCriterion("ZDYJS_DOCTOR_NUM is null");
            return (Criteria) this;
        }

        public Criteria andZdyjsDoctorNumIsNotNull() {
            addCriterion("ZDYJS_DOCTOR_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andZdyjsDoctorNumEqualTo(String value) {
            addCriterion("ZDYJS_DOCTOR_NUM =", value, "zdyjsDoctorNum");
            return (Criteria) this;
        }

        public Criteria andZdyjsDoctorNumNotEqualTo(String value) {
            addCriterion("ZDYJS_DOCTOR_NUM <>", value, "zdyjsDoctorNum");
            return (Criteria) this;
        }

        public Criteria andZdyjsDoctorNumGreaterThan(String value) {
            addCriterion("ZDYJS_DOCTOR_NUM >", value, "zdyjsDoctorNum");
            return (Criteria) this;
        }

        public Criteria andZdyjsDoctorNumGreaterThanOrEqualTo(String value) {
            addCriterion("ZDYJS_DOCTOR_NUM >=", value, "zdyjsDoctorNum");
            return (Criteria) this;
        }

        public Criteria andZdyjsDoctorNumLessThan(String value) {
            addCriterion("ZDYJS_DOCTOR_NUM <", value, "zdyjsDoctorNum");
            return (Criteria) this;
        }

        public Criteria andZdyjsDoctorNumLessThanOrEqualTo(String value) {
            addCriterion("ZDYJS_DOCTOR_NUM <=", value, "zdyjsDoctorNum");
            return (Criteria) this;
        }

        public Criteria andZdyjsDoctorNumLike(String value) {
            addCriterion("ZDYJS_DOCTOR_NUM like", value, "zdyjsDoctorNum");
            return (Criteria) this;
        }

        public Criteria andZdyjsDoctorNumNotLike(String value) {
            addCriterion("ZDYJS_DOCTOR_NUM not like", value, "zdyjsDoctorNum");
            return (Criteria) this;
        }

        public Criteria andZdyjsDoctorNumIn(List<String> values) {
            addCriterion("ZDYJS_DOCTOR_NUM in", values, "zdyjsDoctorNum");
            return (Criteria) this;
        }

        public Criteria andZdyjsDoctorNumNotIn(List<String> values) {
            addCriterion("ZDYJS_DOCTOR_NUM not in", values, "zdyjsDoctorNum");
            return (Criteria) this;
        }

        public Criteria andZdyjsDoctorNumBetween(String value1, String value2) {
            addCriterion("ZDYJS_DOCTOR_NUM between", value1, value2, "zdyjsDoctorNum");
            return (Criteria) this;
        }

        public Criteria andZdyjsDoctorNumNotBetween(String value1, String value2) {
            addCriterion("ZDYJS_DOCTOR_NUM not between", value1, value2, "zdyjsDoctorNum");
            return (Criteria) this;
        }

        public Criteria andSbyjsDoctorNumIsNull() {
            addCriterion("SBYJS_DOCTOR_NUM is null");
            return (Criteria) this;
        }

        public Criteria andSbyjsDoctorNumIsNotNull() {
            addCriterion("SBYJS_DOCTOR_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andSbyjsDoctorNumEqualTo(String value) {
            addCriterion("SBYJS_DOCTOR_NUM =", value, "sbyjsDoctorNum");
            return (Criteria) this;
        }

        public Criteria andSbyjsDoctorNumNotEqualTo(String value) {
            addCriterion("SBYJS_DOCTOR_NUM <>", value, "sbyjsDoctorNum");
            return (Criteria) this;
        }

        public Criteria andSbyjsDoctorNumGreaterThan(String value) {
            addCriterion("SBYJS_DOCTOR_NUM >", value, "sbyjsDoctorNum");
            return (Criteria) this;
        }

        public Criteria andSbyjsDoctorNumGreaterThanOrEqualTo(String value) {
            addCriterion("SBYJS_DOCTOR_NUM >=", value, "sbyjsDoctorNum");
            return (Criteria) this;
        }

        public Criteria andSbyjsDoctorNumLessThan(String value) {
            addCriterion("SBYJS_DOCTOR_NUM <", value, "sbyjsDoctorNum");
            return (Criteria) this;
        }

        public Criteria andSbyjsDoctorNumLessThanOrEqualTo(String value) {
            addCriterion("SBYJS_DOCTOR_NUM <=", value, "sbyjsDoctorNum");
            return (Criteria) this;
        }

        public Criteria andSbyjsDoctorNumLike(String value) {
            addCriterion("SBYJS_DOCTOR_NUM like", value, "sbyjsDoctorNum");
            return (Criteria) this;
        }

        public Criteria andSbyjsDoctorNumNotLike(String value) {
            addCriterion("SBYJS_DOCTOR_NUM not like", value, "sbyjsDoctorNum");
            return (Criteria) this;
        }

        public Criteria andSbyjsDoctorNumIn(List<String> values) {
            addCriterion("SBYJS_DOCTOR_NUM in", values, "sbyjsDoctorNum");
            return (Criteria) this;
        }

        public Criteria andSbyjsDoctorNumNotIn(List<String> values) {
            addCriterion("SBYJS_DOCTOR_NUM not in", values, "sbyjsDoctorNum");
            return (Criteria) this;
        }

        public Criteria andSbyjsDoctorNumBetween(String value1, String value2) {
            addCriterion("SBYJS_DOCTOR_NUM between", value1, value2, "sbyjsDoctorNum");
            return (Criteria) this;
        }

        public Criteria andSbyjsDoctorNumNotBetween(String value1, String value2) {
            addCriterion("SBYJS_DOCTOR_NUM not between", value1, value2, "sbyjsDoctorNum");
            return (Criteria) this;
        }

        public Criteria andInserviceDoctorNumIsNull() {
            addCriterion("INSERVICE_DOCTOR_NUM is null");
            return (Criteria) this;
        }

        public Criteria andInserviceDoctorNumIsNotNull() {
            addCriterion("INSERVICE_DOCTOR_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andInserviceDoctorNumEqualTo(String value) {
            addCriterion("INSERVICE_DOCTOR_NUM =", value, "inserviceDoctorNum");
            return (Criteria) this;
        }

        public Criteria andInserviceDoctorNumNotEqualTo(String value) {
            addCriterion("INSERVICE_DOCTOR_NUM <>", value, "inserviceDoctorNum");
            return (Criteria) this;
        }

        public Criteria andInserviceDoctorNumGreaterThan(String value) {
            addCriterion("INSERVICE_DOCTOR_NUM >", value, "inserviceDoctorNum");
            return (Criteria) this;
        }

        public Criteria andInserviceDoctorNumGreaterThanOrEqualTo(String value) {
            addCriterion("INSERVICE_DOCTOR_NUM >=", value, "inserviceDoctorNum");
            return (Criteria) this;
        }

        public Criteria andInserviceDoctorNumLessThan(String value) {
            addCriterion("INSERVICE_DOCTOR_NUM <", value, "inserviceDoctorNum");
            return (Criteria) this;
        }

        public Criteria andInserviceDoctorNumLessThanOrEqualTo(String value) {
            addCriterion("INSERVICE_DOCTOR_NUM <=", value, "inserviceDoctorNum");
            return (Criteria) this;
        }

        public Criteria andInserviceDoctorNumLike(String value) {
            addCriterion("INSERVICE_DOCTOR_NUM like", value, "inserviceDoctorNum");
            return (Criteria) this;
        }

        public Criteria andInserviceDoctorNumNotLike(String value) {
            addCriterion("INSERVICE_DOCTOR_NUM not like", value, "inserviceDoctorNum");
            return (Criteria) this;
        }

        public Criteria andInserviceDoctorNumIn(List<String> values) {
            addCriterion("INSERVICE_DOCTOR_NUM in", values, "inserviceDoctorNum");
            return (Criteria) this;
        }

        public Criteria andInserviceDoctorNumNotIn(List<String> values) {
            addCriterion("INSERVICE_DOCTOR_NUM not in", values, "inserviceDoctorNum");
            return (Criteria) this;
        }

        public Criteria andInserviceDoctorNumBetween(String value1, String value2) {
            addCriterion("INSERVICE_DOCTOR_NUM between", value1, value2, "inserviceDoctorNum");
            return (Criteria) this;
        }

        public Criteria andInserviceDoctorNumNotBetween(String value1, String value2) {
            addCriterion("INSERVICE_DOCTOR_NUM not between", value1, value2, "inserviceDoctorNum");
            return (Criteria) this;
        }

        public Criteria andDeclareDoctorNumIsNull() {
            addCriterion("DECLARE_DOCTOR_NUM is null");
            return (Criteria) this;
        }

        public Criteria andDeclareDoctorNumIsNotNull() {
            addCriterion("DECLARE_DOCTOR_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andDeclareDoctorNumEqualTo(String value) {
            addCriterion("DECLARE_DOCTOR_NUM =", value, "declareDoctorNum");
            return (Criteria) this;
        }

        public Criteria andDeclareDoctorNumNotEqualTo(String value) {
            addCriterion("DECLARE_DOCTOR_NUM <>", value, "declareDoctorNum");
            return (Criteria) this;
        }

        public Criteria andDeclareDoctorNumGreaterThan(String value) {
            addCriterion("DECLARE_DOCTOR_NUM >", value, "declareDoctorNum");
            return (Criteria) this;
        }

        public Criteria andDeclareDoctorNumGreaterThanOrEqualTo(String value) {
            addCriterion("DECLARE_DOCTOR_NUM >=", value, "declareDoctorNum");
            return (Criteria) this;
        }

        public Criteria andDeclareDoctorNumLessThan(String value) {
            addCriterion("DECLARE_DOCTOR_NUM <", value, "declareDoctorNum");
            return (Criteria) this;
        }

        public Criteria andDeclareDoctorNumLessThanOrEqualTo(String value) {
            addCriterion("DECLARE_DOCTOR_NUM <=", value, "declareDoctorNum");
            return (Criteria) this;
        }

        public Criteria andDeclareDoctorNumLike(String value) {
            addCriterion("DECLARE_DOCTOR_NUM like", value, "declareDoctorNum");
            return (Criteria) this;
        }

        public Criteria andDeclareDoctorNumNotLike(String value) {
            addCriterion("DECLARE_DOCTOR_NUM not like", value, "declareDoctorNum");
            return (Criteria) this;
        }

        public Criteria andDeclareDoctorNumIn(List<String> values) {
            addCriterion("DECLARE_DOCTOR_NUM in", values, "declareDoctorNum");
            return (Criteria) this;
        }

        public Criteria andDeclareDoctorNumNotIn(List<String> values) {
            addCriterion("DECLARE_DOCTOR_NUM not in", values, "declareDoctorNum");
            return (Criteria) this;
        }

        public Criteria andDeclareDoctorNumBetween(String value1, String value2) {
            addCriterion("DECLARE_DOCTOR_NUM between", value1, value2, "declareDoctorNum");
            return (Criteria) this;
        }

        public Criteria andDeclareDoctorNumNotBetween(String value1, String value2) {
            addCriterion("DECLARE_DOCTOR_NUM not between", value1, value2, "declareDoctorNum");
            return (Criteria) this;
        }

        public Criteria andPaperMasterRateIsNull() {
            addCriterion("PAPER_MASTER_RATE is null");
            return (Criteria) this;
        }

        public Criteria andPaperMasterRateIsNotNull() {
            addCriterion("PAPER_MASTER_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andPaperMasterRateEqualTo(String value) {
            addCriterion("PAPER_MASTER_RATE =", value, "paperMasterRate");
            return (Criteria) this;
        }

        public Criteria andPaperMasterRateNotEqualTo(String value) {
            addCriterion("PAPER_MASTER_RATE <>", value, "paperMasterRate");
            return (Criteria) this;
        }

        public Criteria andPaperMasterRateGreaterThan(String value) {
            addCriterion("PAPER_MASTER_RATE >", value, "paperMasterRate");
            return (Criteria) this;
        }

        public Criteria andPaperMasterRateGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER_MASTER_RATE >=", value, "paperMasterRate");
            return (Criteria) this;
        }

        public Criteria andPaperMasterRateLessThan(String value) {
            addCriterion("PAPER_MASTER_RATE <", value, "paperMasterRate");
            return (Criteria) this;
        }

        public Criteria andPaperMasterRateLessThanOrEqualTo(String value) {
            addCriterion("PAPER_MASTER_RATE <=", value, "paperMasterRate");
            return (Criteria) this;
        }

        public Criteria andPaperMasterRateLike(String value) {
            addCriterion("PAPER_MASTER_RATE like", value, "paperMasterRate");
            return (Criteria) this;
        }

        public Criteria andPaperMasterRateNotLike(String value) {
            addCriterion("PAPER_MASTER_RATE not like", value, "paperMasterRate");
            return (Criteria) this;
        }

        public Criteria andPaperMasterRateIn(List<String> values) {
            addCriterion("PAPER_MASTER_RATE in", values, "paperMasterRate");
            return (Criteria) this;
        }

        public Criteria andPaperMasterRateNotIn(List<String> values) {
            addCriterion("PAPER_MASTER_RATE not in", values, "paperMasterRate");
            return (Criteria) this;
        }

        public Criteria andPaperMasterRateBetween(String value1, String value2) {
            addCriterion("PAPER_MASTER_RATE between", value1, value2, "paperMasterRate");
            return (Criteria) this;
        }

        public Criteria andPaperMasterRateNotBetween(String value1, String value2) {
            addCriterion("PAPER_MASTER_RATE not between", value1, value2, "paperMasterRate");
            return (Criteria) this;
        }

        public Criteria andPaperDoctorRateIsNull() {
            addCriterion("PAPER_DOCTOR_RATE is null");
            return (Criteria) this;
        }

        public Criteria andPaperDoctorRateIsNotNull() {
            addCriterion("PAPER_DOCTOR_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andPaperDoctorRateEqualTo(String value) {
            addCriterion("PAPER_DOCTOR_RATE =", value, "paperDoctorRate");
            return (Criteria) this;
        }

        public Criteria andPaperDoctorRateNotEqualTo(String value) {
            addCriterion("PAPER_DOCTOR_RATE <>", value, "paperDoctorRate");
            return (Criteria) this;
        }

        public Criteria andPaperDoctorRateGreaterThan(String value) {
            addCriterion("PAPER_DOCTOR_RATE >", value, "paperDoctorRate");
            return (Criteria) this;
        }

        public Criteria andPaperDoctorRateGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER_DOCTOR_RATE >=", value, "paperDoctorRate");
            return (Criteria) this;
        }

        public Criteria andPaperDoctorRateLessThan(String value) {
            addCriterion("PAPER_DOCTOR_RATE <", value, "paperDoctorRate");
            return (Criteria) this;
        }

        public Criteria andPaperDoctorRateLessThanOrEqualTo(String value) {
            addCriterion("PAPER_DOCTOR_RATE <=", value, "paperDoctorRate");
            return (Criteria) this;
        }

        public Criteria andPaperDoctorRateLike(String value) {
            addCriterion("PAPER_DOCTOR_RATE like", value, "paperDoctorRate");
            return (Criteria) this;
        }

        public Criteria andPaperDoctorRateNotLike(String value) {
            addCriterion("PAPER_DOCTOR_RATE not like", value, "paperDoctorRate");
            return (Criteria) this;
        }

        public Criteria andPaperDoctorRateIn(List<String> values) {
            addCriterion("PAPER_DOCTOR_RATE in", values, "paperDoctorRate");
            return (Criteria) this;
        }

        public Criteria andPaperDoctorRateNotIn(List<String> values) {
            addCriterion("PAPER_DOCTOR_RATE not in", values, "paperDoctorRate");
            return (Criteria) this;
        }

        public Criteria andPaperDoctorRateBetween(String value1, String value2) {
            addCriterion("PAPER_DOCTOR_RATE between", value1, value2, "paperDoctorRate");
            return (Criteria) this;
        }

        public Criteria andPaperDoctorRateNotBetween(String value1, String value2) {
            addCriterion("PAPER_DOCTOR_RATE not between", value1, value2, "paperDoctorRate");
            return (Criteria) this;
        }

        public Criteria andPyyjsAwardDescribeIsNull() {
            addCriterion("PYYJS_AWARD_DESCRIBE is null");
            return (Criteria) this;
        }

        public Criteria andPyyjsAwardDescribeIsNotNull() {
            addCriterion("PYYJS_AWARD_DESCRIBE is not null");
            return (Criteria) this;
        }

        public Criteria andPyyjsAwardDescribeEqualTo(String value) {
            addCriterion("PYYJS_AWARD_DESCRIBE =", value, "pyyjsAwardDescribe");
            return (Criteria) this;
        }

        public Criteria andPyyjsAwardDescribeNotEqualTo(String value) {
            addCriterion("PYYJS_AWARD_DESCRIBE <>", value, "pyyjsAwardDescribe");
            return (Criteria) this;
        }

        public Criteria andPyyjsAwardDescribeGreaterThan(String value) {
            addCriterion("PYYJS_AWARD_DESCRIBE >", value, "pyyjsAwardDescribe");
            return (Criteria) this;
        }

        public Criteria andPyyjsAwardDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("PYYJS_AWARD_DESCRIBE >=", value, "pyyjsAwardDescribe");
            return (Criteria) this;
        }

        public Criteria andPyyjsAwardDescribeLessThan(String value) {
            addCriterion("PYYJS_AWARD_DESCRIBE <", value, "pyyjsAwardDescribe");
            return (Criteria) this;
        }

        public Criteria andPyyjsAwardDescribeLessThanOrEqualTo(String value) {
            addCriterion("PYYJS_AWARD_DESCRIBE <=", value, "pyyjsAwardDescribe");
            return (Criteria) this;
        }

        public Criteria andPyyjsAwardDescribeLike(String value) {
            addCriterion("PYYJS_AWARD_DESCRIBE like", value, "pyyjsAwardDescribe");
            return (Criteria) this;
        }

        public Criteria andPyyjsAwardDescribeNotLike(String value) {
            addCriterion("PYYJS_AWARD_DESCRIBE not like", value, "pyyjsAwardDescribe");
            return (Criteria) this;
        }

        public Criteria andPyyjsAwardDescribeIn(List<String> values) {
            addCriterion("PYYJS_AWARD_DESCRIBE in", values, "pyyjsAwardDescribe");
            return (Criteria) this;
        }

        public Criteria andPyyjsAwardDescribeNotIn(List<String> values) {
            addCriterion("PYYJS_AWARD_DESCRIBE not in", values, "pyyjsAwardDescribe");
            return (Criteria) this;
        }

        public Criteria andPyyjsAwardDescribeBetween(String value1, String value2) {
            addCriterion("PYYJS_AWARD_DESCRIBE between", value1, value2, "pyyjsAwardDescribe");
            return (Criteria) this;
        }

        public Criteria andPyyjsAwardDescribeNotBetween(String value1, String value2) {
            addCriterion("PYYJS_AWARD_DESCRIBE not between", value1, value2, "pyyjsAwardDescribe");
            return (Criteria) this;
        }

        public Criteria andPyyjsOtherDescribeIsNull() {
            addCriterion("PYYJS_OTHER_DESCRIBE is null");
            return (Criteria) this;
        }

        public Criteria andPyyjsOtherDescribeIsNotNull() {
            addCriterion("PYYJS_OTHER_DESCRIBE is not null");
            return (Criteria) this;
        }

        public Criteria andPyyjsOtherDescribeEqualTo(String value) {
            addCriterion("PYYJS_OTHER_DESCRIBE =", value, "pyyjsOtherDescribe");
            return (Criteria) this;
        }

        public Criteria andPyyjsOtherDescribeNotEqualTo(String value) {
            addCriterion("PYYJS_OTHER_DESCRIBE <>", value, "pyyjsOtherDescribe");
            return (Criteria) this;
        }

        public Criteria andPyyjsOtherDescribeGreaterThan(String value) {
            addCriterion("PYYJS_OTHER_DESCRIBE >", value, "pyyjsOtherDescribe");
            return (Criteria) this;
        }

        public Criteria andPyyjsOtherDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("PYYJS_OTHER_DESCRIBE >=", value, "pyyjsOtherDescribe");
            return (Criteria) this;
        }

        public Criteria andPyyjsOtherDescribeLessThan(String value) {
            addCriterion("PYYJS_OTHER_DESCRIBE <", value, "pyyjsOtherDescribe");
            return (Criteria) this;
        }

        public Criteria andPyyjsOtherDescribeLessThanOrEqualTo(String value) {
            addCriterion("PYYJS_OTHER_DESCRIBE <=", value, "pyyjsOtherDescribe");
            return (Criteria) this;
        }

        public Criteria andPyyjsOtherDescribeLike(String value) {
            addCriterion("PYYJS_OTHER_DESCRIBE like", value, "pyyjsOtherDescribe");
            return (Criteria) this;
        }

        public Criteria andPyyjsOtherDescribeNotLike(String value) {
            addCriterion("PYYJS_OTHER_DESCRIBE not like", value, "pyyjsOtherDescribe");
            return (Criteria) this;
        }

        public Criteria andPyyjsOtherDescribeIn(List<String> values) {
            addCriterion("PYYJS_OTHER_DESCRIBE in", values, "pyyjsOtherDescribe");
            return (Criteria) this;
        }

        public Criteria andPyyjsOtherDescribeNotIn(List<String> values) {
            addCriterion("PYYJS_OTHER_DESCRIBE not in", values, "pyyjsOtherDescribe");
            return (Criteria) this;
        }

        public Criteria andPyyjsOtherDescribeBetween(String value1, String value2) {
            addCriterion("PYYJS_OTHER_DESCRIBE between", value1, value2, "pyyjsOtherDescribe");
            return (Criteria) this;
        }

        public Criteria andPyyjsOtherDescribeNotBetween(String value1, String value2) {
            addCriterion("PYYJS_OTHER_DESCRIBE not between", value1, value2, "pyyjsOtherDescribe");
            return (Criteria) this;
        }

        public Criteria andApplyFlagIsNull() {
            addCriterion("APPLY_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andApplyFlagIsNotNull() {
            addCriterion("APPLY_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andApplyFlagEqualTo(String value) {
            addCriterion("APPLY_FLAG =", value, "applyFlag");
            return (Criteria) this;
        }

        public Criteria andApplyFlagNotEqualTo(String value) {
            addCriterion("APPLY_FLAG <>", value, "applyFlag");
            return (Criteria) this;
        }

        public Criteria andApplyFlagGreaterThan(String value) {
            addCriterion("APPLY_FLAG >", value, "applyFlag");
            return (Criteria) this;
        }

        public Criteria andApplyFlagGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_FLAG >=", value, "applyFlag");
            return (Criteria) this;
        }

        public Criteria andApplyFlagLessThan(String value) {
            addCriterion("APPLY_FLAG <", value, "applyFlag");
            return (Criteria) this;
        }

        public Criteria andApplyFlagLessThanOrEqualTo(String value) {
            addCriterion("APPLY_FLAG <=", value, "applyFlag");
            return (Criteria) this;
        }

        public Criteria andApplyFlagLike(String value) {
            addCriterion("APPLY_FLAG like", value, "applyFlag");
            return (Criteria) this;
        }

        public Criteria andApplyFlagNotLike(String value) {
            addCriterion("APPLY_FLAG not like", value, "applyFlag");
            return (Criteria) this;
        }

        public Criteria andApplyFlagIn(List<String> values) {
            addCriterion("APPLY_FLAG in", values, "applyFlag");
            return (Criteria) this;
        }

        public Criteria andApplyFlagNotIn(List<String> values) {
            addCriterion("APPLY_FLAG not in", values, "applyFlag");
            return (Criteria) this;
        }

        public Criteria andApplyFlagBetween(String value1, String value2) {
            addCriterion("APPLY_FLAG between", value1, value2, "applyFlag");
            return (Criteria) this;
        }

        public Criteria andApplyFlagNotBetween(String value1, String value2) {
            addCriterion("APPLY_FLAG not between", value1, value2, "applyFlag");
            return (Criteria) this;
        }

        public Criteria andApplyTimeIsNull() {
            addCriterion("APPLY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andApplyTimeIsNotNull() {
            addCriterion("APPLY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyTimeEqualTo(String value) {
            addCriterion("APPLY_TIME =", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotEqualTo(String value) {
            addCriterion("APPLY_TIME <>", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeGreaterThan(String value) {
            addCriterion("APPLY_TIME >", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_TIME >=", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeLessThan(String value) {
            addCriterion("APPLY_TIME <", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeLessThanOrEqualTo(String value) {
            addCriterion("APPLY_TIME <=", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeLike(String value) {
            addCriterion("APPLY_TIME like", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotLike(String value) {
            addCriterion("APPLY_TIME not like", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeIn(List<String> values) {
            addCriterion("APPLY_TIME in", values, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotIn(List<String> values) {
            addCriterion("APPLY_TIME not in", values, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeBetween(String value1, String value2) {
            addCriterion("APPLY_TIME between", value1, value2, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotBetween(String value1, String value2) {
            addCriterion("APPLY_TIME not between", value1, value2, "applyTime");
            return (Criteria) this;
        }

        public Criteria andPydwUserFlowIsNull() {
            addCriterion("PYDW_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andPydwUserFlowIsNotNull() {
            addCriterion("PYDW_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andPydwUserFlowEqualTo(String value) {
            addCriterion("PYDW_USER_FLOW =", value, "pydwUserFlow");
            return (Criteria) this;
        }

        public Criteria andPydwUserFlowNotEqualTo(String value) {
            addCriterion("PYDW_USER_FLOW <>", value, "pydwUserFlow");
            return (Criteria) this;
        }

        public Criteria andPydwUserFlowGreaterThan(String value) {
            addCriterion("PYDW_USER_FLOW >", value, "pydwUserFlow");
            return (Criteria) this;
        }

        public Criteria andPydwUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PYDW_USER_FLOW >=", value, "pydwUserFlow");
            return (Criteria) this;
        }

        public Criteria andPydwUserFlowLessThan(String value) {
            addCriterion("PYDW_USER_FLOW <", value, "pydwUserFlow");
            return (Criteria) this;
        }

        public Criteria andPydwUserFlowLessThanOrEqualTo(String value) {
            addCriterion("PYDW_USER_FLOW <=", value, "pydwUserFlow");
            return (Criteria) this;
        }

        public Criteria andPydwUserFlowLike(String value) {
            addCriterion("PYDW_USER_FLOW like", value, "pydwUserFlow");
            return (Criteria) this;
        }

        public Criteria andPydwUserFlowNotLike(String value) {
            addCriterion("PYDW_USER_FLOW not like", value, "pydwUserFlow");
            return (Criteria) this;
        }

        public Criteria andPydwUserFlowIn(List<String> values) {
            addCriterion("PYDW_USER_FLOW in", values, "pydwUserFlow");
            return (Criteria) this;
        }

        public Criteria andPydwUserFlowNotIn(List<String> values) {
            addCriterion("PYDW_USER_FLOW not in", values, "pydwUserFlow");
            return (Criteria) this;
        }

        public Criteria andPydwUserFlowBetween(String value1, String value2) {
            addCriterion("PYDW_USER_FLOW between", value1, value2, "pydwUserFlow");
            return (Criteria) this;
        }

        public Criteria andPydwUserFlowNotBetween(String value1, String value2) {
            addCriterion("PYDW_USER_FLOW not between", value1, value2, "pydwUserFlow");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdIsNull() {
            addCriterion("PYDW_AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdIsNotNull() {
            addCriterion("PYDW_AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdEqualTo(String value) {
            addCriterion("PYDW_AUDIT_STATUS_ID =", value, "pydwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdNotEqualTo(String value) {
            addCriterion("PYDW_AUDIT_STATUS_ID <>", value, "pydwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdGreaterThan(String value) {
            addCriterion("PYDW_AUDIT_STATUS_ID >", value, "pydwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("PYDW_AUDIT_STATUS_ID >=", value, "pydwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdLessThan(String value) {
            addCriterion("PYDW_AUDIT_STATUS_ID <", value, "pydwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("PYDW_AUDIT_STATUS_ID <=", value, "pydwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdLike(String value) {
            addCriterion("PYDW_AUDIT_STATUS_ID like", value, "pydwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdNotLike(String value) {
            addCriterion("PYDW_AUDIT_STATUS_ID not like", value, "pydwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdIn(List<String> values) {
            addCriterion("PYDW_AUDIT_STATUS_ID in", values, "pydwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdNotIn(List<String> values) {
            addCriterion("PYDW_AUDIT_STATUS_ID not in", values, "pydwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdBetween(String value1, String value2) {
            addCriterion("PYDW_AUDIT_STATUS_ID between", value1, value2, "pydwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("PYDW_AUDIT_STATUS_ID not between", value1, value2, "pydwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameIsNull() {
            addCriterion("PYDW_AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameIsNotNull() {
            addCriterion("PYDW_AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameEqualTo(String value) {
            addCriterion("PYDW_AUDIT_STATUS_NAME =", value, "pydwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameNotEqualTo(String value) {
            addCriterion("PYDW_AUDIT_STATUS_NAME <>", value, "pydwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameGreaterThan(String value) {
            addCriterion("PYDW_AUDIT_STATUS_NAME >", value, "pydwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("PYDW_AUDIT_STATUS_NAME >=", value, "pydwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameLessThan(String value) {
            addCriterion("PYDW_AUDIT_STATUS_NAME <", value, "pydwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("PYDW_AUDIT_STATUS_NAME <=", value, "pydwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameLike(String value) {
            addCriterion("PYDW_AUDIT_STATUS_NAME like", value, "pydwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameNotLike(String value) {
            addCriterion("PYDW_AUDIT_STATUS_NAME not like", value, "pydwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameIn(List<String> values) {
            addCriterion("PYDW_AUDIT_STATUS_NAME in", values, "pydwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameNotIn(List<String> values) {
            addCriterion("PYDW_AUDIT_STATUS_NAME not in", values, "pydwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameBetween(String value1, String value2) {
            addCriterion("PYDW_AUDIT_STATUS_NAME between", value1, value2, "pydwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("PYDW_AUDIT_STATUS_NAME not between", value1, value2, "pydwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeIsNull() {
            addCriterion("PYDW_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeIsNotNull() {
            addCriterion("PYDW_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeEqualTo(String value) {
            addCriterion("PYDW_AUDIT_TIME =", value, "pydwAuditTime");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeNotEqualTo(String value) {
            addCriterion("PYDW_AUDIT_TIME <>", value, "pydwAuditTime");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeGreaterThan(String value) {
            addCriterion("PYDW_AUDIT_TIME >", value, "pydwAuditTime");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("PYDW_AUDIT_TIME >=", value, "pydwAuditTime");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeLessThan(String value) {
            addCriterion("PYDW_AUDIT_TIME <", value, "pydwAuditTime");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("PYDW_AUDIT_TIME <=", value, "pydwAuditTime");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeLike(String value) {
            addCriterion("PYDW_AUDIT_TIME like", value, "pydwAuditTime");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeNotLike(String value) {
            addCriterion("PYDW_AUDIT_TIME not like", value, "pydwAuditTime");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeIn(List<String> values) {
            addCriterion("PYDW_AUDIT_TIME in", values, "pydwAuditTime");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeNotIn(List<String> values) {
            addCriterion("PYDW_AUDIT_TIME not in", values, "pydwAuditTime");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeBetween(String value1, String value2) {
            addCriterion("PYDW_AUDIT_TIME between", value1, value2, "pydwAuditTime");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeNotBetween(String value1, String value2) {
            addCriterion("PYDW_AUDIT_TIME not between", value1, value2, "pydwAuditTime");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceIsNull() {
            addCriterion("PYDW_AUDIT_ADVICE is null");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceIsNotNull() {
            addCriterion("PYDW_AUDIT_ADVICE is not null");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceEqualTo(String value) {
            addCriterion("PYDW_AUDIT_ADVICE =", value, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceNotEqualTo(String value) {
            addCriterion("PYDW_AUDIT_ADVICE <>", value, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceGreaterThan(String value) {
            addCriterion("PYDW_AUDIT_ADVICE >", value, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceGreaterThanOrEqualTo(String value) {
            addCriterion("PYDW_AUDIT_ADVICE >=", value, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceLessThan(String value) {
            addCriterion("PYDW_AUDIT_ADVICE <", value, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceLessThanOrEqualTo(String value) {
            addCriterion("PYDW_AUDIT_ADVICE <=", value, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceLike(String value) {
            addCriterion("PYDW_AUDIT_ADVICE like", value, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceNotLike(String value) {
            addCriterion("PYDW_AUDIT_ADVICE not like", value, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceIn(List<String> values) {
            addCriterion("PYDW_AUDIT_ADVICE in", values, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceNotIn(List<String> values) {
            addCriterion("PYDW_AUDIT_ADVICE not in", values, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceBetween(String value1, String value2) {
            addCriterion("PYDW_AUDIT_ADVICE between", value1, value2, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceNotBetween(String value1, String value2) {
            addCriterion("PYDW_AUDIT_ADVICE not between", value1, value2, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andFwhUserFlowIsNull() {
            addCriterion("FWH_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andFwhUserFlowIsNotNull() {
            addCriterion("FWH_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andFwhUserFlowEqualTo(String value) {
            addCriterion("FWH_USER_FLOW =", value, "fwhUserFlow");
            return (Criteria) this;
        }

        public Criteria andFwhUserFlowNotEqualTo(String value) {
            addCriterion("FWH_USER_FLOW <>", value, "fwhUserFlow");
            return (Criteria) this;
        }

        public Criteria andFwhUserFlowGreaterThan(String value) {
            addCriterion("FWH_USER_FLOW >", value, "fwhUserFlow");
            return (Criteria) this;
        }

        public Criteria andFwhUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("FWH_USER_FLOW >=", value, "fwhUserFlow");
            return (Criteria) this;
        }

        public Criteria andFwhUserFlowLessThan(String value) {
            addCriterion("FWH_USER_FLOW <", value, "fwhUserFlow");
            return (Criteria) this;
        }

        public Criteria andFwhUserFlowLessThanOrEqualTo(String value) {
            addCriterion("FWH_USER_FLOW <=", value, "fwhUserFlow");
            return (Criteria) this;
        }

        public Criteria andFwhUserFlowLike(String value) {
            addCriterion("FWH_USER_FLOW like", value, "fwhUserFlow");
            return (Criteria) this;
        }

        public Criteria andFwhUserFlowNotLike(String value) {
            addCriterion("FWH_USER_FLOW not like", value, "fwhUserFlow");
            return (Criteria) this;
        }

        public Criteria andFwhUserFlowIn(List<String> values) {
            addCriterion("FWH_USER_FLOW in", values, "fwhUserFlow");
            return (Criteria) this;
        }

        public Criteria andFwhUserFlowNotIn(List<String> values) {
            addCriterion("FWH_USER_FLOW not in", values, "fwhUserFlow");
            return (Criteria) this;
        }

        public Criteria andFwhUserFlowBetween(String value1, String value2) {
            addCriterion("FWH_USER_FLOW between", value1, value2, "fwhUserFlow");
            return (Criteria) this;
        }

        public Criteria andFwhUserFlowNotBetween(String value1, String value2) {
            addCriterion("FWH_USER_FLOW not between", value1, value2, "fwhUserFlow");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdIsNull() {
            addCriterion("FWH_AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdIsNotNull() {
            addCriterion("FWH_AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdEqualTo(String value) {
            addCriterion("FWH_AUDIT_STATUS_ID =", value, "fwhAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdNotEqualTo(String value) {
            addCriterion("FWH_AUDIT_STATUS_ID <>", value, "fwhAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdGreaterThan(String value) {
            addCriterion("FWH_AUDIT_STATUS_ID >", value, "fwhAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("FWH_AUDIT_STATUS_ID >=", value, "fwhAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdLessThan(String value) {
            addCriterion("FWH_AUDIT_STATUS_ID <", value, "fwhAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("FWH_AUDIT_STATUS_ID <=", value, "fwhAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdLike(String value) {
            addCriterion("FWH_AUDIT_STATUS_ID like", value, "fwhAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdNotLike(String value) {
            addCriterion("FWH_AUDIT_STATUS_ID not like", value, "fwhAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdIn(List<String> values) {
            addCriterion("FWH_AUDIT_STATUS_ID in", values, "fwhAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdNotIn(List<String> values) {
            addCriterion("FWH_AUDIT_STATUS_ID not in", values, "fwhAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdBetween(String value1, String value2) {
            addCriterion("FWH_AUDIT_STATUS_ID between", value1, value2, "fwhAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("FWH_AUDIT_STATUS_ID not between", value1, value2, "fwhAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameIsNull() {
            addCriterion("FWH_AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameIsNotNull() {
            addCriterion("FWH_AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameEqualTo(String value) {
            addCriterion("FWH_AUDIT_STATUS_NAME =", value, "fwhAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameNotEqualTo(String value) {
            addCriterion("FWH_AUDIT_STATUS_NAME <>", value, "fwhAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameGreaterThan(String value) {
            addCriterion("FWH_AUDIT_STATUS_NAME >", value, "fwhAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("FWH_AUDIT_STATUS_NAME >=", value, "fwhAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameLessThan(String value) {
            addCriterion("FWH_AUDIT_STATUS_NAME <", value, "fwhAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("FWH_AUDIT_STATUS_NAME <=", value, "fwhAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameLike(String value) {
            addCriterion("FWH_AUDIT_STATUS_NAME like", value, "fwhAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameNotLike(String value) {
            addCriterion("FWH_AUDIT_STATUS_NAME not like", value, "fwhAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameIn(List<String> values) {
            addCriterion("FWH_AUDIT_STATUS_NAME in", values, "fwhAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameNotIn(List<String> values) {
            addCriterion("FWH_AUDIT_STATUS_NAME not in", values, "fwhAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameBetween(String value1, String value2) {
            addCriterion("FWH_AUDIT_STATUS_NAME between", value1, value2, "fwhAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("FWH_AUDIT_STATUS_NAME not between", value1, value2, "fwhAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeIsNull() {
            addCriterion("FWH_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeIsNotNull() {
            addCriterion("FWH_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeEqualTo(String value) {
            addCriterion("FWH_AUDIT_TIME =", value, "fwhAuditTime");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeNotEqualTo(String value) {
            addCriterion("FWH_AUDIT_TIME <>", value, "fwhAuditTime");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeGreaterThan(String value) {
            addCriterion("FWH_AUDIT_TIME >", value, "fwhAuditTime");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("FWH_AUDIT_TIME >=", value, "fwhAuditTime");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeLessThan(String value) {
            addCriterion("FWH_AUDIT_TIME <", value, "fwhAuditTime");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("FWH_AUDIT_TIME <=", value, "fwhAuditTime");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeLike(String value) {
            addCriterion("FWH_AUDIT_TIME like", value, "fwhAuditTime");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeNotLike(String value) {
            addCriterion("FWH_AUDIT_TIME not like", value, "fwhAuditTime");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeIn(List<String> values) {
            addCriterion("FWH_AUDIT_TIME in", values, "fwhAuditTime");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeNotIn(List<String> values) {
            addCriterion("FWH_AUDIT_TIME not in", values, "fwhAuditTime");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeBetween(String value1, String value2) {
            addCriterion("FWH_AUDIT_TIME between", value1, value2, "fwhAuditTime");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeNotBetween(String value1, String value2) {
            addCriterion("FWH_AUDIT_TIME not between", value1, value2, "fwhAuditTime");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceIsNull() {
            addCriterion("FWH_AUDIT_ADVICE is null");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceIsNotNull() {
            addCriterion("FWH_AUDIT_ADVICE is not null");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceEqualTo(String value) {
            addCriterion("FWH_AUDIT_ADVICE =", value, "fwhAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceNotEqualTo(String value) {
            addCriterion("FWH_AUDIT_ADVICE <>", value, "fwhAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceGreaterThan(String value) {
            addCriterion("FWH_AUDIT_ADVICE >", value, "fwhAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceGreaterThanOrEqualTo(String value) {
            addCriterion("FWH_AUDIT_ADVICE >=", value, "fwhAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceLessThan(String value) {
            addCriterion("FWH_AUDIT_ADVICE <", value, "fwhAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceLessThanOrEqualTo(String value) {
            addCriterion("FWH_AUDIT_ADVICE <=", value, "fwhAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceLike(String value) {
            addCriterion("FWH_AUDIT_ADVICE like", value, "fwhAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceNotLike(String value) {
            addCriterion("FWH_AUDIT_ADVICE not like", value, "fwhAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceIn(List<String> values) {
            addCriterion("FWH_AUDIT_ADVICE in", values, "fwhAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceNotIn(List<String> values) {
            addCriterion("FWH_AUDIT_ADVICE not in", values, "fwhAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceBetween(String value1, String value2) {
            addCriterion("FWH_AUDIT_ADVICE between", value1, value2, "fwhAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceNotBetween(String value1, String value2) {
            addCriterion("FWH_AUDIT_ADVICE not between", value1, value2, "fwhAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andXwkUserFlowIsNull() {
            addCriterion("XWK_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andXwkUserFlowIsNotNull() {
            addCriterion("XWK_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andXwkUserFlowEqualTo(String value) {
            addCriterion("XWK_USER_FLOW =", value, "xwkUserFlow");
            return (Criteria) this;
        }

        public Criteria andXwkUserFlowNotEqualTo(String value) {
            addCriterion("XWK_USER_FLOW <>", value, "xwkUserFlow");
            return (Criteria) this;
        }

        public Criteria andXwkUserFlowGreaterThan(String value) {
            addCriterion("XWK_USER_FLOW >", value, "xwkUserFlow");
            return (Criteria) this;
        }

        public Criteria andXwkUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("XWK_USER_FLOW >=", value, "xwkUserFlow");
            return (Criteria) this;
        }

        public Criteria andXwkUserFlowLessThan(String value) {
            addCriterion("XWK_USER_FLOW <", value, "xwkUserFlow");
            return (Criteria) this;
        }

        public Criteria andXwkUserFlowLessThanOrEqualTo(String value) {
            addCriterion("XWK_USER_FLOW <=", value, "xwkUserFlow");
            return (Criteria) this;
        }

        public Criteria andXwkUserFlowLike(String value) {
            addCriterion("XWK_USER_FLOW like", value, "xwkUserFlow");
            return (Criteria) this;
        }

        public Criteria andXwkUserFlowNotLike(String value) {
            addCriterion("XWK_USER_FLOW not like", value, "xwkUserFlow");
            return (Criteria) this;
        }

        public Criteria andXwkUserFlowIn(List<String> values) {
            addCriterion("XWK_USER_FLOW in", values, "xwkUserFlow");
            return (Criteria) this;
        }

        public Criteria andXwkUserFlowNotIn(List<String> values) {
            addCriterion("XWK_USER_FLOW not in", values, "xwkUserFlow");
            return (Criteria) this;
        }

        public Criteria andXwkUserFlowBetween(String value1, String value2) {
            addCriterion("XWK_USER_FLOW between", value1, value2, "xwkUserFlow");
            return (Criteria) this;
        }

        public Criteria andXwkUserFlowNotBetween(String value1, String value2) {
            addCriterion("XWK_USER_FLOW not between", value1, value2, "xwkUserFlow");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusIdIsNull() {
            addCriterion("XWK_AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusIdIsNotNull() {
            addCriterion("XWK_AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusIdEqualTo(String value) {
            addCriterion("XWK_AUDIT_STATUS_ID =", value, "xwkAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusIdNotEqualTo(String value) {
            addCriterion("XWK_AUDIT_STATUS_ID <>", value, "xwkAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusIdGreaterThan(String value) {
            addCriterion("XWK_AUDIT_STATUS_ID >", value, "xwkAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("XWK_AUDIT_STATUS_ID >=", value, "xwkAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusIdLessThan(String value) {
            addCriterion("XWK_AUDIT_STATUS_ID <", value, "xwkAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("XWK_AUDIT_STATUS_ID <=", value, "xwkAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusIdLike(String value) {
            addCriterion("XWK_AUDIT_STATUS_ID like", value, "xwkAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusIdNotLike(String value) {
            addCriterion("XWK_AUDIT_STATUS_ID not like", value, "xwkAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusIdIn(List<String> values) {
            addCriterion("XWK_AUDIT_STATUS_ID in", values, "xwkAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusIdNotIn(List<String> values) {
            addCriterion("XWK_AUDIT_STATUS_ID not in", values, "xwkAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusIdBetween(String value1, String value2) {
            addCriterion("XWK_AUDIT_STATUS_ID between", value1, value2, "xwkAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("XWK_AUDIT_STATUS_ID not between", value1, value2, "xwkAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusNameIsNull() {
            addCriterion("XWK_AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusNameIsNotNull() {
            addCriterion("XWK_AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusNameEqualTo(String value) {
            addCriterion("XWK_AUDIT_STATUS_NAME =", value, "xwkAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusNameNotEqualTo(String value) {
            addCriterion("XWK_AUDIT_STATUS_NAME <>", value, "xwkAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusNameGreaterThan(String value) {
            addCriterion("XWK_AUDIT_STATUS_NAME >", value, "xwkAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("XWK_AUDIT_STATUS_NAME >=", value, "xwkAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusNameLessThan(String value) {
            addCriterion("XWK_AUDIT_STATUS_NAME <", value, "xwkAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("XWK_AUDIT_STATUS_NAME <=", value, "xwkAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusNameLike(String value) {
            addCriterion("XWK_AUDIT_STATUS_NAME like", value, "xwkAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusNameNotLike(String value) {
            addCriterion("XWK_AUDIT_STATUS_NAME not like", value, "xwkAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusNameIn(List<String> values) {
            addCriterion("XWK_AUDIT_STATUS_NAME in", values, "xwkAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusNameNotIn(List<String> values) {
            addCriterion("XWK_AUDIT_STATUS_NAME not in", values, "xwkAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusNameBetween(String value1, String value2) {
            addCriterion("XWK_AUDIT_STATUS_NAME between", value1, value2, "xwkAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andXwkAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("XWK_AUDIT_STATUS_NAME not between", value1, value2, "xwkAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andXwkAuditTimeIsNull() {
            addCriterion("XWK_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andXwkAuditTimeIsNotNull() {
            addCriterion("XWK_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andXwkAuditTimeEqualTo(String value) {
            addCriterion("XWK_AUDIT_TIME =", value, "xwkAuditTime");
            return (Criteria) this;
        }

        public Criteria andXwkAuditTimeNotEqualTo(String value) {
            addCriterion("XWK_AUDIT_TIME <>", value, "xwkAuditTime");
            return (Criteria) this;
        }

        public Criteria andXwkAuditTimeGreaterThan(String value) {
            addCriterion("XWK_AUDIT_TIME >", value, "xwkAuditTime");
            return (Criteria) this;
        }

        public Criteria andXwkAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("XWK_AUDIT_TIME >=", value, "xwkAuditTime");
            return (Criteria) this;
        }

        public Criteria andXwkAuditTimeLessThan(String value) {
            addCriterion("XWK_AUDIT_TIME <", value, "xwkAuditTime");
            return (Criteria) this;
        }

        public Criteria andXwkAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("XWK_AUDIT_TIME <=", value, "xwkAuditTime");
            return (Criteria) this;
        }

        public Criteria andXwkAuditTimeLike(String value) {
            addCriterion("XWK_AUDIT_TIME like", value, "xwkAuditTime");
            return (Criteria) this;
        }

        public Criteria andXwkAuditTimeNotLike(String value) {
            addCriterion("XWK_AUDIT_TIME not like", value, "xwkAuditTime");
            return (Criteria) this;
        }

        public Criteria andXwkAuditTimeIn(List<String> values) {
            addCriterion("XWK_AUDIT_TIME in", values, "xwkAuditTime");
            return (Criteria) this;
        }

        public Criteria andXwkAuditTimeNotIn(List<String> values) {
            addCriterion("XWK_AUDIT_TIME not in", values, "xwkAuditTime");
            return (Criteria) this;
        }

        public Criteria andXwkAuditTimeBetween(String value1, String value2) {
            addCriterion("XWK_AUDIT_TIME between", value1, value2, "xwkAuditTime");
            return (Criteria) this;
        }

        public Criteria andXwkAuditTimeNotBetween(String value1, String value2) {
            addCriterion("XWK_AUDIT_TIME not between", value1, value2, "xwkAuditTime");
            return (Criteria) this;
        }

        public Criteria andXwkAuditAdviceIsNull() {
            addCriterion("XWK_AUDIT_ADVICE is null");
            return (Criteria) this;
        }

        public Criteria andXwkAuditAdviceIsNotNull() {
            addCriterion("XWK_AUDIT_ADVICE is not null");
            return (Criteria) this;
        }

        public Criteria andXwkAuditAdviceEqualTo(String value) {
            addCriterion("XWK_AUDIT_ADVICE =", value, "xwkAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andXwkAuditAdviceNotEqualTo(String value) {
            addCriterion("XWK_AUDIT_ADVICE <>", value, "xwkAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andXwkAuditAdviceGreaterThan(String value) {
            addCriterion("XWK_AUDIT_ADVICE >", value, "xwkAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andXwkAuditAdviceGreaterThanOrEqualTo(String value) {
            addCriterion("XWK_AUDIT_ADVICE >=", value, "xwkAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andXwkAuditAdviceLessThan(String value) {
            addCriterion("XWK_AUDIT_ADVICE <", value, "xwkAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andXwkAuditAdviceLessThanOrEqualTo(String value) {
            addCriterion("XWK_AUDIT_ADVICE <=", value, "xwkAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andXwkAuditAdviceLike(String value) {
            addCriterion("XWK_AUDIT_ADVICE like", value, "xwkAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andXwkAuditAdviceNotLike(String value) {
            addCriterion("XWK_AUDIT_ADVICE not like", value, "xwkAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andXwkAuditAdviceIn(List<String> values) {
            addCriterion("XWK_AUDIT_ADVICE in", values, "xwkAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andXwkAuditAdviceNotIn(List<String> values) {
            addCriterion("XWK_AUDIT_ADVICE not in", values, "xwkAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andXwkAuditAdviceBetween(String value1, String value2) {
            addCriterion("XWK_AUDIT_ADVICE between", value1, value2, "xwkAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andXwkAuditAdviceNotBetween(String value1, String value2) {
            addCriterion("XWK_AUDIT_ADVICE not between", value1, value2, "xwkAuditAdvice");
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

        public Criteria andIdNoIsNull() {
            addCriterion("ID_NO is null");
            return (Criteria) this;
        }

        public Criteria andIdNoIsNotNull() {
            addCriterion("ID_NO is not null");
            return (Criteria) this;
        }

        public Criteria andIdNoEqualTo(String value) {
            addCriterion("ID_NO =", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotEqualTo(String value) {
            addCriterion("ID_NO <>", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoGreaterThan(String value) {
            addCriterion("ID_NO >", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoGreaterThanOrEqualTo(String value) {
            addCriterion("ID_NO >=", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLessThan(String value) {
            addCriterion("ID_NO <", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLessThanOrEqualTo(String value) {
            addCriterion("ID_NO <=", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLike(String value) {
            addCriterion("ID_NO like", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotLike(String value) {
            addCriterion("ID_NO not like", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoIn(List<String> values) {
            addCriterion("ID_NO in", values, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotIn(List<String> values) {
            addCriterion("ID_NO not in", values, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoBetween(String value1, String value2) {
            addCriterion("ID_NO between", value1, value2, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotBetween(String value1, String value2) {
            addCriterion("ID_NO not between", value1, value2, "idNo");
            return (Criteria) this;
        }

        public Criteria andStopRecruitIsNull() {
            addCriterion("STOP_RECRUIT is null");
            return (Criteria) this;
        }

        public Criteria andStopRecruitIsNotNull() {
            addCriterion("STOP_RECRUIT is not null");
            return (Criteria) this;
        }

        public Criteria andStopRecruitEqualTo(String value) {
            addCriterion("STOP_RECRUIT =", value, "stopRecruit");
            return (Criteria) this;
        }

        public Criteria andStopRecruitNotEqualTo(String value) {
            addCriterion("STOP_RECRUIT <>", value, "stopRecruit");
            return (Criteria) this;
        }

        public Criteria andStopRecruitGreaterThan(String value) {
            addCriterion("STOP_RECRUIT >", value, "stopRecruit");
            return (Criteria) this;
        }

        public Criteria andStopRecruitGreaterThanOrEqualTo(String value) {
            addCriterion("STOP_RECRUIT >=", value, "stopRecruit");
            return (Criteria) this;
        }

        public Criteria andStopRecruitLessThan(String value) {
            addCriterion("STOP_RECRUIT <", value, "stopRecruit");
            return (Criteria) this;
        }

        public Criteria andStopRecruitLessThanOrEqualTo(String value) {
            addCriterion("STOP_RECRUIT <=", value, "stopRecruit");
            return (Criteria) this;
        }

        public Criteria andStopRecruitLike(String value) {
            addCriterion("STOP_RECRUIT like", value, "stopRecruit");
            return (Criteria) this;
        }

        public Criteria andStopRecruitNotLike(String value) {
            addCriterion("STOP_RECRUIT not like", value, "stopRecruit");
            return (Criteria) this;
        }

        public Criteria andStopRecruitIn(List<String> values) {
            addCriterion("STOP_RECRUIT in", values, "stopRecruit");
            return (Criteria) this;
        }

        public Criteria andStopRecruitNotIn(List<String> values) {
            addCriterion("STOP_RECRUIT not in", values, "stopRecruit");
            return (Criteria) this;
        }

        public Criteria andStopRecruitBetween(String value1, String value2) {
            addCriterion("STOP_RECRUIT between", value1, value2, "stopRecruit");
            return (Criteria) this;
        }

        public Criteria andStopRecruitNotBetween(String value1, String value2) {
            addCriterion("STOP_RECRUIT not between", value1, value2, "stopRecruit");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectIdIsNull() {
            addCriterion("ONE_LEVEL_SUBJECT_ID is null");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectIdIsNotNull() {
            addCriterion("ONE_LEVEL_SUBJECT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectIdEqualTo(String value) {
            addCriterion("ONE_LEVEL_SUBJECT_ID =", value, "oneLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectIdNotEqualTo(String value) {
            addCriterion("ONE_LEVEL_SUBJECT_ID <>", value, "oneLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectIdGreaterThan(String value) {
            addCriterion("ONE_LEVEL_SUBJECT_ID >", value, "oneLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("ONE_LEVEL_SUBJECT_ID >=", value, "oneLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectIdLessThan(String value) {
            addCriterion("ONE_LEVEL_SUBJECT_ID <", value, "oneLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectIdLessThanOrEqualTo(String value) {
            addCriterion("ONE_LEVEL_SUBJECT_ID <=", value, "oneLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectIdLike(String value) {
            addCriterion("ONE_LEVEL_SUBJECT_ID like", value, "oneLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectIdNotLike(String value) {
            addCriterion("ONE_LEVEL_SUBJECT_ID not like", value, "oneLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectIdIn(List<String> values) {
            addCriterion("ONE_LEVEL_SUBJECT_ID in", values, "oneLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectIdNotIn(List<String> values) {
            addCriterion("ONE_LEVEL_SUBJECT_ID not in", values, "oneLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectIdBetween(String value1, String value2) {
            addCriterion("ONE_LEVEL_SUBJECT_ID between", value1, value2, "oneLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectIdNotBetween(String value1, String value2) {
            addCriterion("ONE_LEVEL_SUBJECT_ID not between", value1, value2, "oneLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectNameIsNull() {
            addCriterion("ONE_LEVEL_SUBJECT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectNameIsNotNull() {
            addCriterion("ONE_LEVEL_SUBJECT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectNameEqualTo(String value) {
            addCriterion("ONE_LEVEL_SUBJECT_NAME =", value, "oneLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectNameNotEqualTo(String value) {
            addCriterion("ONE_LEVEL_SUBJECT_NAME <>", value, "oneLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectNameGreaterThan(String value) {
            addCriterion("ONE_LEVEL_SUBJECT_NAME >", value, "oneLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("ONE_LEVEL_SUBJECT_NAME >=", value, "oneLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectNameLessThan(String value) {
            addCriterion("ONE_LEVEL_SUBJECT_NAME <", value, "oneLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectNameLessThanOrEqualTo(String value) {
            addCriterion("ONE_LEVEL_SUBJECT_NAME <=", value, "oneLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectNameLike(String value) {
            addCriterion("ONE_LEVEL_SUBJECT_NAME like", value, "oneLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectNameNotLike(String value) {
            addCriterion("ONE_LEVEL_SUBJECT_NAME not like", value, "oneLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectNameIn(List<String> values) {
            addCriterion("ONE_LEVEL_SUBJECT_NAME in", values, "oneLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectNameNotIn(List<String> values) {
            addCriterion("ONE_LEVEL_SUBJECT_NAME not in", values, "oneLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectNameBetween(String value1, String value2) {
            addCriterion("ONE_LEVEL_SUBJECT_NAME between", value1, value2, "oneLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andOneLevelSubjectNameNotBetween(String value1, String value2) {
            addCriterion("ONE_LEVEL_SUBJECT_NAME not between", value1, value2, "oneLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectIdIsNull() {
            addCriterion("TWO_LEVEL_SUBJECT_ID is null");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectIdIsNotNull() {
            addCriterion("TWO_LEVEL_SUBJECT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectIdEqualTo(String value) {
            addCriterion("TWO_LEVEL_SUBJECT_ID =", value, "twoLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectIdNotEqualTo(String value) {
            addCriterion("TWO_LEVEL_SUBJECT_ID <>", value, "twoLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectIdGreaterThan(String value) {
            addCriterion("TWO_LEVEL_SUBJECT_ID >", value, "twoLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("TWO_LEVEL_SUBJECT_ID >=", value, "twoLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectIdLessThan(String value) {
            addCriterion("TWO_LEVEL_SUBJECT_ID <", value, "twoLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectIdLessThanOrEqualTo(String value) {
            addCriterion("TWO_LEVEL_SUBJECT_ID <=", value, "twoLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectIdLike(String value) {
            addCriterion("TWO_LEVEL_SUBJECT_ID like", value, "twoLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectIdNotLike(String value) {
            addCriterion("TWO_LEVEL_SUBJECT_ID not like", value, "twoLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectIdIn(List<String> values) {
            addCriterion("TWO_LEVEL_SUBJECT_ID in", values, "twoLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectIdNotIn(List<String> values) {
            addCriterion("TWO_LEVEL_SUBJECT_ID not in", values, "twoLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectIdBetween(String value1, String value2) {
            addCriterion("TWO_LEVEL_SUBJECT_ID between", value1, value2, "twoLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectIdNotBetween(String value1, String value2) {
            addCriterion("TWO_LEVEL_SUBJECT_ID not between", value1, value2, "twoLevelSubjectId");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectNameIsNull() {
            addCriterion("TWO_LEVEL_SUBJECT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectNameIsNotNull() {
            addCriterion("TWO_LEVEL_SUBJECT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectNameEqualTo(String value) {
            addCriterion("TWO_LEVEL_SUBJECT_NAME =", value, "twoLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectNameNotEqualTo(String value) {
            addCriterion("TWO_LEVEL_SUBJECT_NAME <>", value, "twoLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectNameGreaterThan(String value) {
            addCriterion("TWO_LEVEL_SUBJECT_NAME >", value, "twoLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("TWO_LEVEL_SUBJECT_NAME >=", value, "twoLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectNameLessThan(String value) {
            addCriterion("TWO_LEVEL_SUBJECT_NAME <", value, "twoLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectNameLessThanOrEqualTo(String value) {
            addCriterion("TWO_LEVEL_SUBJECT_NAME <=", value, "twoLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectNameLike(String value) {
            addCriterion("TWO_LEVEL_SUBJECT_NAME like", value, "twoLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectNameNotLike(String value) {
            addCriterion("TWO_LEVEL_SUBJECT_NAME not like", value, "twoLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectNameIn(List<String> values) {
            addCriterion("TWO_LEVEL_SUBJECT_NAME in", values, "twoLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectNameNotIn(List<String> values) {
            addCriterion("TWO_LEVEL_SUBJECT_NAME not in", values, "twoLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectNameBetween(String value1, String value2) {
            addCriterion("TWO_LEVEL_SUBJECT_NAME between", value1, value2, "twoLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andTwoLevelSubjectNameNotBetween(String value1, String value2) {
            addCriterion("TWO_LEVEL_SUBJECT_NAME not between", value1, value2, "twoLevelSubjectName");
            return (Criteria) this;
        }

        public Criteria andSexIdIsNull() {
            addCriterion("SEX_ID is null");
            return (Criteria) this;
        }

        public Criteria andSexIdIsNotNull() {
            addCriterion("SEX_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSexIdEqualTo(String value) {
            addCriterion("SEX_ID =", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotEqualTo(String value) {
            addCriterion("SEX_ID <>", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdGreaterThan(String value) {
            addCriterion("SEX_ID >", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdGreaterThanOrEqualTo(String value) {
            addCriterion("SEX_ID >=", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdLessThan(String value) {
            addCriterion("SEX_ID <", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdLessThanOrEqualTo(String value) {
            addCriterion("SEX_ID <=", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdLike(String value) {
            addCriterion("SEX_ID like", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotLike(String value) {
            addCriterion("SEX_ID not like", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdIn(List<String> values) {
            addCriterion("SEX_ID in", values, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotIn(List<String> values) {
            addCriterion("SEX_ID not in", values, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdBetween(String value1, String value2) {
            addCriterion("SEX_ID between", value1, value2, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotBetween(String value1, String value2) {
            addCriterion("SEX_ID not between", value1, value2, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexNameIsNull() {
            addCriterion("SEX_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSexNameIsNotNull() {
            addCriterion("SEX_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSexNameEqualTo(String value) {
            addCriterion("SEX_NAME =", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotEqualTo(String value) {
            addCriterion("SEX_NAME <>", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameGreaterThan(String value) {
            addCriterion("SEX_NAME >", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameGreaterThanOrEqualTo(String value) {
            addCriterion("SEX_NAME >=", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameLessThan(String value) {
            addCriterion("SEX_NAME <", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameLessThanOrEqualTo(String value) {
            addCriterion("SEX_NAME <=", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameLike(String value) {
            addCriterion("SEX_NAME like", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotLike(String value) {
            addCriterion("SEX_NAME not like", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameIn(List<String> values) {
            addCriterion("SEX_NAME in", values, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotIn(List<String> values) {
            addCriterion("SEX_NAME not in", values, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameBetween(String value1, String value2) {
            addCriterion("SEX_NAME between", value1, value2, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotBetween(String value1, String value2) {
            addCriterion("SEX_NAME not between", value1, value2, "sexName");
            return (Criteria) this;
        }

        public Criteria andZsCategoryIdIsNull() {
            addCriterion("ZS_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andZsCategoryIdIsNotNull() {
            addCriterion("ZS_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andZsCategoryIdEqualTo(String value) {
            addCriterion("ZS_CATEGORY_ID =", value, "zsCategoryId");
            return (Criteria) this;
        }

        public Criteria andZsCategoryIdNotEqualTo(String value) {
            addCriterion("ZS_CATEGORY_ID <>", value, "zsCategoryId");
            return (Criteria) this;
        }

        public Criteria andZsCategoryIdGreaterThan(String value) {
            addCriterion("ZS_CATEGORY_ID >", value, "zsCategoryId");
            return (Criteria) this;
        }

        public Criteria andZsCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("ZS_CATEGORY_ID >=", value, "zsCategoryId");
            return (Criteria) this;
        }

        public Criteria andZsCategoryIdLessThan(String value) {
            addCriterion("ZS_CATEGORY_ID <", value, "zsCategoryId");
            return (Criteria) this;
        }

        public Criteria andZsCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("ZS_CATEGORY_ID <=", value, "zsCategoryId");
            return (Criteria) this;
        }

        public Criteria andZsCategoryIdLike(String value) {
            addCriterion("ZS_CATEGORY_ID like", value, "zsCategoryId");
            return (Criteria) this;
        }

        public Criteria andZsCategoryIdNotLike(String value) {
            addCriterion("ZS_CATEGORY_ID not like", value, "zsCategoryId");
            return (Criteria) this;
        }

        public Criteria andZsCategoryIdIn(List<String> values) {
            addCriterion("ZS_CATEGORY_ID in", values, "zsCategoryId");
            return (Criteria) this;
        }

        public Criteria andZsCategoryIdNotIn(List<String> values) {
            addCriterion("ZS_CATEGORY_ID not in", values, "zsCategoryId");
            return (Criteria) this;
        }

        public Criteria andZsCategoryIdBetween(String value1, String value2) {
            addCriterion("ZS_CATEGORY_ID between", value1, value2, "zsCategoryId");
            return (Criteria) this;
        }

        public Criteria andZsCategoryIdNotBetween(String value1, String value2) {
            addCriterion("ZS_CATEGORY_ID not between", value1, value2, "zsCategoryId");
            return (Criteria) this;
        }

        public Criteria andZsCategoryNameIsNull() {
            addCriterion("ZS_CATEGORY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andZsCategoryNameIsNotNull() {
            addCriterion("ZS_CATEGORY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andZsCategoryNameEqualTo(String value) {
            addCriterion("ZS_CATEGORY_NAME =", value, "zsCategoryName");
            return (Criteria) this;
        }

        public Criteria andZsCategoryNameNotEqualTo(String value) {
            addCriterion("ZS_CATEGORY_NAME <>", value, "zsCategoryName");
            return (Criteria) this;
        }

        public Criteria andZsCategoryNameGreaterThan(String value) {
            addCriterion("ZS_CATEGORY_NAME >", value, "zsCategoryName");
            return (Criteria) this;
        }

        public Criteria andZsCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("ZS_CATEGORY_NAME >=", value, "zsCategoryName");
            return (Criteria) this;
        }

        public Criteria andZsCategoryNameLessThan(String value) {
            addCriterion("ZS_CATEGORY_NAME <", value, "zsCategoryName");
            return (Criteria) this;
        }

        public Criteria andZsCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("ZS_CATEGORY_NAME <=", value, "zsCategoryName");
            return (Criteria) this;
        }

        public Criteria andZsCategoryNameLike(String value) {
            addCriterion("ZS_CATEGORY_NAME like", value, "zsCategoryName");
            return (Criteria) this;
        }

        public Criteria andZsCategoryNameNotLike(String value) {
            addCriterion("ZS_CATEGORY_NAME not like", value, "zsCategoryName");
            return (Criteria) this;
        }

        public Criteria andZsCategoryNameIn(List<String> values) {
            addCriterion("ZS_CATEGORY_NAME in", values, "zsCategoryName");
            return (Criteria) this;
        }

        public Criteria andZsCategoryNameNotIn(List<String> values) {
            addCriterion("ZS_CATEGORY_NAME not in", values, "zsCategoryName");
            return (Criteria) this;
        }

        public Criteria andZsCategoryNameBetween(String value1, String value2) {
            addCriterion("ZS_CATEGORY_NAME between", value1, value2, "zsCategoryName");
            return (Criteria) this;
        }

        public Criteria andZsCategoryNameNotBetween(String value1, String value2) {
            addCriterion("ZS_CATEGORY_NAME not between", value1, value2, "zsCategoryName");
            return (Criteria) this;
        }

        public Criteria andZsSubjectIdIsNull() {
            addCriterion("ZS_SUBJECT_ID is null");
            return (Criteria) this;
        }

        public Criteria andZsSubjectIdIsNotNull() {
            addCriterion("ZS_SUBJECT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andZsSubjectIdEqualTo(String value) {
            addCriterion("ZS_SUBJECT_ID =", value, "zsSubjectId");
            return (Criteria) this;
        }

        public Criteria andZsSubjectIdNotEqualTo(String value) {
            addCriterion("ZS_SUBJECT_ID <>", value, "zsSubjectId");
            return (Criteria) this;
        }

        public Criteria andZsSubjectIdGreaterThan(String value) {
            addCriterion("ZS_SUBJECT_ID >", value, "zsSubjectId");
            return (Criteria) this;
        }

        public Criteria andZsSubjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("ZS_SUBJECT_ID >=", value, "zsSubjectId");
            return (Criteria) this;
        }

        public Criteria andZsSubjectIdLessThan(String value) {
            addCriterion("ZS_SUBJECT_ID <", value, "zsSubjectId");
            return (Criteria) this;
        }

        public Criteria andZsSubjectIdLessThanOrEqualTo(String value) {
            addCriterion("ZS_SUBJECT_ID <=", value, "zsSubjectId");
            return (Criteria) this;
        }

        public Criteria andZsSubjectIdLike(String value) {
            addCriterion("ZS_SUBJECT_ID like", value, "zsSubjectId");
            return (Criteria) this;
        }

        public Criteria andZsSubjectIdNotLike(String value) {
            addCriterion("ZS_SUBJECT_ID not like", value, "zsSubjectId");
            return (Criteria) this;
        }

        public Criteria andZsSubjectIdIn(List<String> values) {
            addCriterion("ZS_SUBJECT_ID in", values, "zsSubjectId");
            return (Criteria) this;
        }

        public Criteria andZsSubjectIdNotIn(List<String> values) {
            addCriterion("ZS_SUBJECT_ID not in", values, "zsSubjectId");
            return (Criteria) this;
        }

        public Criteria andZsSubjectIdBetween(String value1, String value2) {
            addCriterion("ZS_SUBJECT_ID between", value1, value2, "zsSubjectId");
            return (Criteria) this;
        }

        public Criteria andZsSubjectIdNotBetween(String value1, String value2) {
            addCriterion("ZS_SUBJECT_ID not between", value1, value2, "zsSubjectId");
            return (Criteria) this;
        }

        public Criteria andZsSubjectNameIsNull() {
            addCriterion("ZS_SUBJECT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andZsSubjectNameIsNotNull() {
            addCriterion("ZS_SUBJECT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andZsSubjectNameEqualTo(String value) {
            addCriterion("ZS_SUBJECT_NAME =", value, "zsSubjectName");
            return (Criteria) this;
        }

        public Criteria andZsSubjectNameNotEqualTo(String value) {
            addCriterion("ZS_SUBJECT_NAME <>", value, "zsSubjectName");
            return (Criteria) this;
        }

        public Criteria andZsSubjectNameGreaterThan(String value) {
            addCriterion("ZS_SUBJECT_NAME >", value, "zsSubjectName");
            return (Criteria) this;
        }

        public Criteria andZsSubjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("ZS_SUBJECT_NAME >=", value, "zsSubjectName");
            return (Criteria) this;
        }

        public Criteria andZsSubjectNameLessThan(String value) {
            addCriterion("ZS_SUBJECT_NAME <", value, "zsSubjectName");
            return (Criteria) this;
        }

        public Criteria andZsSubjectNameLessThanOrEqualTo(String value) {
            addCriterion("ZS_SUBJECT_NAME <=", value, "zsSubjectName");
            return (Criteria) this;
        }

        public Criteria andZsSubjectNameLike(String value) {
            addCriterion("ZS_SUBJECT_NAME like", value, "zsSubjectName");
            return (Criteria) this;
        }

        public Criteria andZsSubjectNameNotLike(String value) {
            addCriterion("ZS_SUBJECT_NAME not like", value, "zsSubjectName");
            return (Criteria) this;
        }

        public Criteria andZsSubjectNameIn(List<String> values) {
            addCriterion("ZS_SUBJECT_NAME in", values, "zsSubjectName");
            return (Criteria) this;
        }

        public Criteria andZsSubjectNameNotIn(List<String> values) {
            addCriterion("ZS_SUBJECT_NAME not in", values, "zsSubjectName");
            return (Criteria) this;
        }

        public Criteria andZsSubjectNameBetween(String value1, String value2) {
            addCriterion("ZS_SUBJECT_NAME between", value1, value2, "zsSubjectName");
            return (Criteria) this;
        }

        public Criteria andZsSubjectNameNotBetween(String value1, String value2) {
            addCriterion("ZS_SUBJECT_NAME not between", value1, value2, "zsSubjectName");
            return (Criteria) this;
        }

        public Criteria andDegreeIdIsNull() {
            addCriterion("DEGREE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDegreeIdIsNotNull() {
            addCriterion("DEGREE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeIdEqualTo(String value) {
            addCriterion("DEGREE_ID =", value, "degreeId");
            return (Criteria) this;
        }

        public Criteria andDegreeIdNotEqualTo(String value) {
            addCriterion("DEGREE_ID <>", value, "degreeId");
            return (Criteria) this;
        }

        public Criteria andDegreeIdGreaterThan(String value) {
            addCriterion("DEGREE_ID >", value, "degreeId");
            return (Criteria) this;
        }

        public Criteria andDegreeIdGreaterThanOrEqualTo(String value) {
            addCriterion("DEGREE_ID >=", value, "degreeId");
            return (Criteria) this;
        }

        public Criteria andDegreeIdLessThan(String value) {
            addCriterion("DEGREE_ID <", value, "degreeId");
            return (Criteria) this;
        }

        public Criteria andDegreeIdLessThanOrEqualTo(String value) {
            addCriterion("DEGREE_ID <=", value, "degreeId");
            return (Criteria) this;
        }

        public Criteria andDegreeIdLike(String value) {
            addCriterion("DEGREE_ID like", value, "degreeId");
            return (Criteria) this;
        }

        public Criteria andDegreeIdNotLike(String value) {
            addCriterion("DEGREE_ID not like", value, "degreeId");
            return (Criteria) this;
        }

        public Criteria andDegreeIdIn(List<String> values) {
            addCriterion("DEGREE_ID in", values, "degreeId");
            return (Criteria) this;
        }

        public Criteria andDegreeIdNotIn(List<String> values) {
            addCriterion("DEGREE_ID not in", values, "degreeId");
            return (Criteria) this;
        }

        public Criteria andDegreeIdBetween(String value1, String value2) {
            addCriterion("DEGREE_ID between", value1, value2, "degreeId");
            return (Criteria) this;
        }

        public Criteria andDegreeIdNotBetween(String value1, String value2) {
            addCriterion("DEGREE_ID not between", value1, value2, "degreeId");
            return (Criteria) this;
        }

        public Criteria andDegreeNameIsNull() {
            addCriterion("DEGREE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDegreeNameIsNotNull() {
            addCriterion("DEGREE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeNameEqualTo(String value) {
            addCriterion("DEGREE_NAME =", value, "degreeName");
            return (Criteria) this;
        }

        public Criteria andDegreeNameNotEqualTo(String value) {
            addCriterion("DEGREE_NAME <>", value, "degreeName");
            return (Criteria) this;
        }

        public Criteria andDegreeNameGreaterThan(String value) {
            addCriterion("DEGREE_NAME >", value, "degreeName");
            return (Criteria) this;
        }

        public Criteria andDegreeNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEGREE_NAME >=", value, "degreeName");
            return (Criteria) this;
        }

        public Criteria andDegreeNameLessThan(String value) {
            addCriterion("DEGREE_NAME <", value, "degreeName");
            return (Criteria) this;
        }

        public Criteria andDegreeNameLessThanOrEqualTo(String value) {
            addCriterion("DEGREE_NAME <=", value, "degreeName");
            return (Criteria) this;
        }

        public Criteria andDegreeNameLike(String value) {
            addCriterion("DEGREE_NAME like", value, "degreeName");
            return (Criteria) this;
        }

        public Criteria andDegreeNameNotLike(String value) {
            addCriterion("DEGREE_NAME not like", value, "degreeName");
            return (Criteria) this;
        }

        public Criteria andDegreeNameIn(List<String> values) {
            addCriterion("DEGREE_NAME in", values, "degreeName");
            return (Criteria) this;
        }

        public Criteria andDegreeNameNotIn(List<String> values) {
            addCriterion("DEGREE_NAME not in", values, "degreeName");
            return (Criteria) this;
        }

        public Criteria andDegreeNameBetween(String value1, String value2) {
            addCriterion("DEGREE_NAME between", value1, value2, "degreeName");
            return (Criteria) this;
        }

        public Criteria andDegreeNameNotBetween(String value1, String value2) {
            addCriterion("DEGREE_NAME not between", value1, value2, "degreeName");
            return (Criteria) this;
        }

        public Criteria andLastTypeIdIsNull() {
            addCriterion("LAST_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andLastTypeIdIsNotNull() {
            addCriterion("LAST_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLastTypeIdEqualTo(String value) {
            addCriterion("LAST_TYPE_ID =", value, "lastTypeId");
            return (Criteria) this;
        }

        public Criteria andLastTypeIdNotEqualTo(String value) {
            addCriterion("LAST_TYPE_ID <>", value, "lastTypeId");
            return (Criteria) this;
        }

        public Criteria andLastTypeIdGreaterThan(String value) {
            addCriterion("LAST_TYPE_ID >", value, "lastTypeId");
            return (Criteria) this;
        }

        public Criteria andLastTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("LAST_TYPE_ID >=", value, "lastTypeId");
            return (Criteria) this;
        }

        public Criteria andLastTypeIdLessThan(String value) {
            addCriterion("LAST_TYPE_ID <", value, "lastTypeId");
            return (Criteria) this;
        }

        public Criteria andLastTypeIdLessThanOrEqualTo(String value) {
            addCriterion("LAST_TYPE_ID <=", value, "lastTypeId");
            return (Criteria) this;
        }

        public Criteria andLastTypeIdLike(String value) {
            addCriterion("LAST_TYPE_ID like", value, "lastTypeId");
            return (Criteria) this;
        }

        public Criteria andLastTypeIdNotLike(String value) {
            addCriterion("LAST_TYPE_ID not like", value, "lastTypeId");
            return (Criteria) this;
        }

        public Criteria andLastTypeIdIn(List<String> values) {
            addCriterion("LAST_TYPE_ID in", values, "lastTypeId");
            return (Criteria) this;
        }

        public Criteria andLastTypeIdNotIn(List<String> values) {
            addCriterion("LAST_TYPE_ID not in", values, "lastTypeId");
            return (Criteria) this;
        }

        public Criteria andLastTypeIdBetween(String value1, String value2) {
            addCriterion("LAST_TYPE_ID between", value1, value2, "lastTypeId");
            return (Criteria) this;
        }

        public Criteria andLastTypeIdNotBetween(String value1, String value2) {
            addCriterion("LAST_TYPE_ID not between", value1, value2, "lastTypeId");
            return (Criteria) this;
        }

        public Criteria andLastAuditYearIsNull() {
            addCriterion("LAST_AUDIT_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andLastAuditYearIsNotNull() {
            addCriterion("LAST_AUDIT_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andLastAuditYearEqualTo(String value) {
            addCriterion("LAST_AUDIT_YEAR =", value, "lastAuditYear");
            return (Criteria) this;
        }

        public Criteria andLastAuditYearNotEqualTo(String value) {
            addCriterion("LAST_AUDIT_YEAR <>", value, "lastAuditYear");
            return (Criteria) this;
        }

        public Criteria andLastAuditYearGreaterThan(String value) {
            addCriterion("LAST_AUDIT_YEAR >", value, "lastAuditYear");
            return (Criteria) this;
        }

        public Criteria andLastAuditYearGreaterThanOrEqualTo(String value) {
            addCriterion("LAST_AUDIT_YEAR >=", value, "lastAuditYear");
            return (Criteria) this;
        }

        public Criteria andLastAuditYearLessThan(String value) {
            addCriterion("LAST_AUDIT_YEAR <", value, "lastAuditYear");
            return (Criteria) this;
        }

        public Criteria andLastAuditYearLessThanOrEqualTo(String value) {
            addCriterion("LAST_AUDIT_YEAR <=", value, "lastAuditYear");
            return (Criteria) this;
        }

        public Criteria andLastAuditYearLike(String value) {
            addCriterion("LAST_AUDIT_YEAR like", value, "lastAuditYear");
            return (Criteria) this;
        }

        public Criteria andLastAuditYearNotLike(String value) {
            addCriterion("LAST_AUDIT_YEAR not like", value, "lastAuditYear");
            return (Criteria) this;
        }

        public Criteria andLastAuditYearIn(List<String> values) {
            addCriterion("LAST_AUDIT_YEAR in", values, "lastAuditYear");
            return (Criteria) this;
        }

        public Criteria andLastAuditYearNotIn(List<String> values) {
            addCriterion("LAST_AUDIT_YEAR not in", values, "lastAuditYear");
            return (Criteria) this;
        }

        public Criteria andLastAuditYearBetween(String value1, String value2) {
            addCriterion("LAST_AUDIT_YEAR between", value1, value2, "lastAuditYear");
            return (Criteria) this;
        }

        public Criteria andLastAuditYearNotBetween(String value1, String value2) {
            addCriterion("LAST_AUDIT_YEAR not between", value1, value2, "lastAuditYear");
            return (Criteria) this;
        }

        public Criteria andFirstPassFlagIsNull() {
            addCriterion("FIRST_PASS_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andFirstPassFlagIsNotNull() {
            addCriterion("FIRST_PASS_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andFirstPassFlagEqualTo(String value) {
            addCriterion("FIRST_PASS_FLAG =", value, "firstPassFlag");
            return (Criteria) this;
        }

        public Criteria andFirstPassFlagNotEqualTo(String value) {
            addCriterion("FIRST_PASS_FLAG <>", value, "firstPassFlag");
            return (Criteria) this;
        }

        public Criteria andFirstPassFlagGreaterThan(String value) {
            addCriterion("FIRST_PASS_FLAG >", value, "firstPassFlag");
            return (Criteria) this;
        }

        public Criteria andFirstPassFlagGreaterThanOrEqualTo(String value) {
            addCriterion("FIRST_PASS_FLAG >=", value, "firstPassFlag");
            return (Criteria) this;
        }

        public Criteria andFirstPassFlagLessThan(String value) {
            addCriterion("FIRST_PASS_FLAG <", value, "firstPassFlag");
            return (Criteria) this;
        }

        public Criteria andFirstPassFlagLessThanOrEqualTo(String value) {
            addCriterion("FIRST_PASS_FLAG <=", value, "firstPassFlag");
            return (Criteria) this;
        }

        public Criteria andFirstPassFlagLike(String value) {
            addCriterion("FIRST_PASS_FLAG like", value, "firstPassFlag");
            return (Criteria) this;
        }

        public Criteria andFirstPassFlagNotLike(String value) {
            addCriterion("FIRST_PASS_FLAG not like", value, "firstPassFlag");
            return (Criteria) this;
        }

        public Criteria andFirstPassFlagIn(List<String> values) {
            addCriterion("FIRST_PASS_FLAG in", values, "firstPassFlag");
            return (Criteria) this;
        }

        public Criteria andFirstPassFlagNotIn(List<String> values) {
            addCriterion("FIRST_PASS_FLAG not in", values, "firstPassFlag");
            return (Criteria) this;
        }

        public Criteria andFirstPassFlagBetween(String value1, String value2) {
            addCriterion("FIRST_PASS_FLAG between", value1, value2, "firstPassFlag");
            return (Criteria) this;
        }

        public Criteria andFirstPassFlagNotBetween(String value1, String value2) {
            addCriterion("FIRST_PASS_FLAG not between", value1, value2, "firstPassFlag");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeIsNull() {
            addCriterion("INVALID_TIME is null");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeIsNotNull() {
            addCriterion("INVALID_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeEqualTo(String value) {
            addCriterion("INVALID_TIME =", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeNotEqualTo(String value) {
            addCriterion("INVALID_TIME <>", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeGreaterThan(String value) {
            addCriterion("INVALID_TIME >", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeGreaterThanOrEqualTo(String value) {
            addCriterion("INVALID_TIME >=", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeLessThan(String value) {
            addCriterion("INVALID_TIME <", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeLessThanOrEqualTo(String value) {
            addCriterion("INVALID_TIME <=", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeLike(String value) {
            addCriterion("INVALID_TIME like", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeNotLike(String value) {
            addCriterion("INVALID_TIME not like", value, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeIn(List<String> values) {
            addCriterion("INVALID_TIME in", values, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeNotIn(List<String> values) {
            addCriterion("INVALID_TIME not in", values, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeBetween(String value1, String value2) {
            addCriterion("INVALID_TIME between", value1, value2, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andInvalidTimeNotBetween(String value1, String value2) {
            addCriterion("INVALID_TIME not between", value1, value2, "invalidTime");
            return (Criteria) this;
        }

        public Criteria andShowFlagIsNull() {
            addCriterion("SHOW_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andShowFlagIsNotNull() {
            addCriterion("SHOW_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andShowFlagEqualTo(String value) {
            addCriterion("SHOW_FLAG =", value, "showFlag");
            return (Criteria) this;
        }

        public Criteria andShowFlagNotEqualTo(String value) {
            addCriterion("SHOW_FLAG <>", value, "showFlag");
            return (Criteria) this;
        }

        public Criteria andShowFlagGreaterThan(String value) {
            addCriterion("SHOW_FLAG >", value, "showFlag");
            return (Criteria) this;
        }

        public Criteria andShowFlagGreaterThanOrEqualTo(String value) {
            addCriterion("SHOW_FLAG >=", value, "showFlag");
            return (Criteria) this;
        }

        public Criteria andShowFlagLessThan(String value) {
            addCriterion("SHOW_FLAG <", value, "showFlag");
            return (Criteria) this;
        }

        public Criteria andShowFlagLessThanOrEqualTo(String value) {
            addCriterion("SHOW_FLAG <=", value, "showFlag");
            return (Criteria) this;
        }

        public Criteria andShowFlagLike(String value) {
            addCriterion("SHOW_FLAG like", value, "showFlag");
            return (Criteria) this;
        }

        public Criteria andShowFlagNotLike(String value) {
            addCriterion("SHOW_FLAG not like", value, "showFlag");
            return (Criteria) this;
        }

        public Criteria andShowFlagIn(List<String> values) {
            addCriterion("SHOW_FLAG in", values, "showFlag");
            return (Criteria) this;
        }

        public Criteria andShowFlagNotIn(List<String> values) {
            addCriterion("SHOW_FLAG not in", values, "showFlag");
            return (Criteria) this;
        }

        public Criteria andShowFlagBetween(String value1, String value2) {
            addCriterion("SHOW_FLAG between", value1, value2, "showFlag");
            return (Criteria) this;
        }

        public Criteria andShowFlagNotBetween(String value1, String value2) {
            addCriterion("SHOW_FLAG not between", value1, value2, "showFlag");
            return (Criteria) this;
        }

        public Criteria andCertificateNoIsNull() {
            addCriterion("CERTIFICATE_NO is null");
            return (Criteria) this;
        }

        public Criteria andCertificateNoIsNotNull() {
            addCriterion("CERTIFICATE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateNoEqualTo(String value) {
            addCriterion("CERTIFICATE_NO =", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoNotEqualTo(String value) {
            addCriterion("CERTIFICATE_NO <>", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoGreaterThan(String value) {
            addCriterion("CERTIFICATE_NO >", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_NO >=", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoLessThan(String value) {
            addCriterion("CERTIFICATE_NO <", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_NO <=", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoLike(String value) {
            addCriterion("CERTIFICATE_NO like", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoNotLike(String value) {
            addCriterion("CERTIFICATE_NO not like", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoIn(List<String> values) {
            addCriterion("CERTIFICATE_NO in", values, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoNotIn(List<String> values) {
            addCriterion("CERTIFICATE_NO not in", values, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_NO between", value1, value2, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_NO not between", value1, value2, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlIsNull() {
            addCriterion("CERTIFICATE_URL is null");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlIsNotNull() {
            addCriterion("CERTIFICATE_URL is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlEqualTo(String value) {
            addCriterion("CERTIFICATE_URL =", value, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlNotEqualTo(String value) {
            addCriterion("CERTIFICATE_URL <>", value, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlGreaterThan(String value) {
            addCriterion("CERTIFICATE_URL >", value, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_URL >=", value, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlLessThan(String value) {
            addCriterion("CERTIFICATE_URL <", value, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_URL <=", value, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlLike(String value) {
            addCriterion("CERTIFICATE_URL like", value, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlNotLike(String value) {
            addCriterion("CERTIFICATE_URL not like", value, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlIn(List<String> values) {
            addCriterion("CERTIFICATE_URL in", values, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlNotIn(List<String> values) {
            addCriterion("CERTIFICATE_URL not in", values, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_URL between", value1, value2, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_URL not between", value1, value2, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andLeadStuFlagIsNull() {
            addCriterion("LEAD_STU_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andLeadStuFlagIsNotNull() {
            addCriterion("LEAD_STU_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andLeadStuFlagEqualTo(String value) {
            addCriterion("LEAD_STU_FLAG =", value, "leadStuFlag");
            return (Criteria) this;
        }

        public Criteria andLeadStuFlagNotEqualTo(String value) {
            addCriterion("LEAD_STU_FLAG <>", value, "leadStuFlag");
            return (Criteria) this;
        }

        public Criteria andLeadStuFlagGreaterThan(String value) {
            addCriterion("LEAD_STU_FLAG >", value, "leadStuFlag");
            return (Criteria) this;
        }

        public Criteria andLeadStuFlagGreaterThanOrEqualTo(String value) {
            addCriterion("LEAD_STU_FLAG >=", value, "leadStuFlag");
            return (Criteria) this;
        }

        public Criteria andLeadStuFlagLessThan(String value) {
            addCriterion("LEAD_STU_FLAG <", value, "leadStuFlag");
            return (Criteria) this;
        }

        public Criteria andLeadStuFlagLessThanOrEqualTo(String value) {
            addCriterion("LEAD_STU_FLAG <=", value, "leadStuFlag");
            return (Criteria) this;
        }

        public Criteria andLeadStuFlagLike(String value) {
            addCriterion("LEAD_STU_FLAG like", value, "leadStuFlag");
            return (Criteria) this;
        }

        public Criteria andLeadStuFlagNotLike(String value) {
            addCriterion("LEAD_STU_FLAG not like", value, "leadStuFlag");
            return (Criteria) this;
        }

        public Criteria andLeadStuFlagIn(List<String> values) {
            addCriterion("LEAD_STU_FLAG in", values, "leadStuFlag");
            return (Criteria) this;
        }

        public Criteria andLeadStuFlagNotIn(List<String> values) {
            addCriterion("LEAD_STU_FLAG not in", values, "leadStuFlag");
            return (Criteria) this;
        }

        public Criteria andLeadStuFlagBetween(String value1, String value2) {
            addCriterion("LEAD_STU_FLAG between", value1, value2, "leadStuFlag");
            return (Criteria) this;
        }

        public Criteria andLeadStuFlagNotBetween(String value1, String value2) {
            addCriterion("LEAD_STU_FLAG not between", value1, value2, "leadStuFlag");
            return (Criteria) this;
        }

        public Criteria andBlockFlagIsNull() {
            addCriterion("BLOCK_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andBlockFlagIsNotNull() {
            addCriterion("BLOCK_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andBlockFlagEqualTo(String value) {
            addCriterion("BLOCK_FLAG =", value, "blockFlag");
            return (Criteria) this;
        }

        public Criteria andBlockFlagNotEqualTo(String value) {
            addCriterion("BLOCK_FLAG <>", value, "blockFlag");
            return (Criteria) this;
        }

        public Criteria andBlockFlagGreaterThan(String value) {
            addCriterion("BLOCK_FLAG >", value, "blockFlag");
            return (Criteria) this;
        }

        public Criteria andBlockFlagGreaterThanOrEqualTo(String value) {
            addCriterion("BLOCK_FLAG >=", value, "blockFlag");
            return (Criteria) this;
        }

        public Criteria andBlockFlagLessThan(String value) {
            addCriterion("BLOCK_FLAG <", value, "blockFlag");
            return (Criteria) this;
        }

        public Criteria andBlockFlagLessThanOrEqualTo(String value) {
            addCriterion("BLOCK_FLAG <=", value, "blockFlag");
            return (Criteria) this;
        }

        public Criteria andBlockFlagLike(String value) {
            addCriterion("BLOCK_FLAG like", value, "blockFlag");
            return (Criteria) this;
        }

        public Criteria andBlockFlagNotLike(String value) {
            addCriterion("BLOCK_FLAG not like", value, "blockFlag");
            return (Criteria) this;
        }

        public Criteria andBlockFlagIn(List<String> values) {
            addCriterion("BLOCK_FLAG in", values, "blockFlag");
            return (Criteria) this;
        }

        public Criteria andBlockFlagNotIn(List<String> values) {
            addCriterion("BLOCK_FLAG not in", values, "blockFlag");
            return (Criteria) this;
        }

        public Criteria andBlockFlagBetween(String value1, String value2) {
            addCriterion("BLOCK_FLAG between", value1, value2, "blockFlag");
            return (Criteria) this;
        }

        public Criteria andBlockFlagNotBetween(String value1, String value2) {
            addCriterion("BLOCK_FLAG not between", value1, value2, "blockFlag");
            return (Criteria) this;
        }

        public Criteria andBlockTimeIsNull() {
            addCriterion("BLOCK_TIME is null");
            return (Criteria) this;
        }

        public Criteria andBlockTimeIsNotNull() {
            addCriterion("BLOCK_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andBlockTimeEqualTo(String value) {
            addCriterion("BLOCK_TIME =", value, "blockTime");
            return (Criteria) this;
        }

        public Criteria andBlockTimeNotEqualTo(String value) {
            addCriterion("BLOCK_TIME <>", value, "blockTime");
            return (Criteria) this;
        }

        public Criteria andBlockTimeGreaterThan(String value) {
            addCriterion("BLOCK_TIME >", value, "blockTime");
            return (Criteria) this;
        }

        public Criteria andBlockTimeGreaterThanOrEqualTo(String value) {
            addCriterion("BLOCK_TIME >=", value, "blockTime");
            return (Criteria) this;
        }

        public Criteria andBlockTimeLessThan(String value) {
            addCriterion("BLOCK_TIME <", value, "blockTime");
            return (Criteria) this;
        }

        public Criteria andBlockTimeLessThanOrEqualTo(String value) {
            addCriterion("BLOCK_TIME <=", value, "blockTime");
            return (Criteria) this;
        }

        public Criteria andBlockTimeLike(String value) {
            addCriterion("BLOCK_TIME like", value, "blockTime");
            return (Criteria) this;
        }

        public Criteria andBlockTimeNotLike(String value) {
            addCriterion("BLOCK_TIME not like", value, "blockTime");
            return (Criteria) this;
        }

        public Criteria andBlockTimeIn(List<String> values) {
            addCriterion("BLOCK_TIME in", values, "blockTime");
            return (Criteria) this;
        }

        public Criteria andBlockTimeNotIn(List<String> values) {
            addCriterion("BLOCK_TIME not in", values, "blockTime");
            return (Criteria) this;
        }

        public Criteria andBlockTimeBetween(String value1, String value2) {
            addCriterion("BLOCK_TIME between", value1, value2, "blockTime");
            return (Criteria) this;
        }

        public Criteria andBlockTimeNotBetween(String value1, String value2) {
            addCriterion("BLOCK_TIME not between", value1, value2, "blockTime");
            return (Criteria) this;
        }

        public Criteria andRetireTimeIsNull() {
            addCriterion("RETIRE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andRetireTimeIsNotNull() {
            addCriterion("RETIRE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andRetireTimeEqualTo(String value) {
            addCriterion("RETIRE_TIME =", value, "retireTime");
            return (Criteria) this;
        }

        public Criteria andRetireTimeNotEqualTo(String value) {
            addCriterion("RETIRE_TIME <>", value, "retireTime");
            return (Criteria) this;
        }

        public Criteria andRetireTimeGreaterThan(String value) {
            addCriterion("RETIRE_TIME >", value, "retireTime");
            return (Criteria) this;
        }

        public Criteria andRetireTimeGreaterThanOrEqualTo(String value) {
            addCriterion("RETIRE_TIME >=", value, "retireTime");
            return (Criteria) this;
        }

        public Criteria andRetireTimeLessThan(String value) {
            addCriterion("RETIRE_TIME <", value, "retireTime");
            return (Criteria) this;
        }

        public Criteria andRetireTimeLessThanOrEqualTo(String value) {
            addCriterion("RETIRE_TIME <=", value, "retireTime");
            return (Criteria) this;
        }

        public Criteria andRetireTimeLike(String value) {
            addCriterion("RETIRE_TIME like", value, "retireTime");
            return (Criteria) this;
        }

        public Criteria andRetireTimeNotLike(String value) {
            addCriterion("RETIRE_TIME not like", value, "retireTime");
            return (Criteria) this;
        }

        public Criteria andRetireTimeIn(List<String> values) {
            addCriterion("RETIRE_TIME in", values, "retireTime");
            return (Criteria) this;
        }

        public Criteria andRetireTimeNotIn(List<String> values) {
            addCriterion("RETIRE_TIME not in", values, "retireTime");
            return (Criteria) this;
        }

        public Criteria andRetireTimeBetween(String value1, String value2) {
            addCriterion("RETIRE_TIME between", value1, value2, "retireTime");
            return (Criteria) this;
        }

        public Criteria andRetireTimeNotBetween(String value1, String value2) {
            addCriterion("RETIRE_TIME not between", value1, value2, "retireTime");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeIdIsNull() {
            addCriterion("SECOND_RECRUIT_SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeIdIsNotNull() {
            addCriterion("SECOND_RECRUIT_SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeIdEqualTo(String value) {
            addCriterion("SECOND_RECRUIT_SPE_ID =", value, "secondRecruitSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeIdNotEqualTo(String value) {
            addCriterion("SECOND_RECRUIT_SPE_ID <>", value, "secondRecruitSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeIdGreaterThan(String value) {
            addCriterion("SECOND_RECRUIT_SPE_ID >", value, "secondRecruitSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SECOND_RECRUIT_SPE_ID >=", value, "secondRecruitSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeIdLessThan(String value) {
            addCriterion("SECOND_RECRUIT_SPE_ID <", value, "secondRecruitSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeIdLessThanOrEqualTo(String value) {
            addCriterion("SECOND_RECRUIT_SPE_ID <=", value, "secondRecruitSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeIdLike(String value) {
            addCriterion("SECOND_RECRUIT_SPE_ID like", value, "secondRecruitSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeIdNotLike(String value) {
            addCriterion("SECOND_RECRUIT_SPE_ID not like", value, "secondRecruitSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeIdIn(List<String> values) {
            addCriterion("SECOND_RECRUIT_SPE_ID in", values, "secondRecruitSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeIdNotIn(List<String> values) {
            addCriterion("SECOND_RECRUIT_SPE_ID not in", values, "secondRecruitSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeIdBetween(String value1, String value2) {
            addCriterion("SECOND_RECRUIT_SPE_ID between", value1, value2, "secondRecruitSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeIdNotBetween(String value1, String value2) {
            addCriterion("SECOND_RECRUIT_SPE_ID not between", value1, value2, "secondRecruitSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeNameIsNull() {
            addCriterion("SECOND_RECRUIT_SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeNameIsNotNull() {
            addCriterion("SECOND_RECRUIT_SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeNameEqualTo(String value) {
            addCriterion("SECOND_RECRUIT_SPE_NAME =", value, "secondRecruitSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeNameNotEqualTo(String value) {
            addCriterion("SECOND_RECRUIT_SPE_NAME <>", value, "secondRecruitSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeNameGreaterThan(String value) {
            addCriterion("SECOND_RECRUIT_SPE_NAME >", value, "secondRecruitSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SECOND_RECRUIT_SPE_NAME >=", value, "secondRecruitSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeNameLessThan(String value) {
            addCriterion("SECOND_RECRUIT_SPE_NAME <", value, "secondRecruitSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeNameLessThanOrEqualTo(String value) {
            addCriterion("SECOND_RECRUIT_SPE_NAME <=", value, "secondRecruitSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeNameLike(String value) {
            addCriterion("SECOND_RECRUIT_SPE_NAME like", value, "secondRecruitSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeNameNotLike(String value) {
            addCriterion("SECOND_RECRUIT_SPE_NAME not like", value, "secondRecruitSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeNameIn(List<String> values) {
            addCriterion("SECOND_RECRUIT_SPE_NAME in", values, "secondRecruitSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeNameNotIn(List<String> values) {
            addCriterion("SECOND_RECRUIT_SPE_NAME not in", values, "secondRecruitSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeNameBetween(String value1, String value2) {
            addCriterion("SECOND_RECRUIT_SPE_NAME between", value1, value2, "secondRecruitSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondRecruitSpeNameNotBetween(String value1, String value2) {
            addCriterion("SECOND_RECRUIT_SPE_NAME not between", value1, value2, "secondRecruitSpeName");
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