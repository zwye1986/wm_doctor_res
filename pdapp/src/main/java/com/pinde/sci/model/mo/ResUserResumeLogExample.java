package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ResUserResumeLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResUserResumeLogExample() {
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

        public Criteria andRecordFlowIsNull() {
            addCriterion("RECORD_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRecordFlowIsNotNull() {
            addCriterion("RECORD_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRecordFlowEqualTo(String value) {
            addCriterion("RECORD_FLOW =", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowNotEqualTo(String value) {
            addCriterion("RECORD_FLOW <>", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowGreaterThan(String value) {
            addCriterion("RECORD_FLOW >", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RECORD_FLOW >=", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowLessThan(String value) {
            addCriterion("RECORD_FLOW <", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowLessThanOrEqualTo(String value) {
            addCriterion("RECORD_FLOW <=", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowLike(String value) {
            addCriterion("RECORD_FLOW like", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowNotLike(String value) {
            addCriterion("RECORD_FLOW not like", value, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowIn(List<String> values) {
            addCriterion("RECORD_FLOW in", values, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowNotIn(List<String> values) {
            addCriterion("RECORD_FLOW not in", values, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowBetween(String value1, String value2) {
            addCriterion("RECORD_FLOW between", value1, value2, "recordFlow");
            return (Criteria) this;
        }

        public Criteria andRecordFlowNotBetween(String value1, String value2) {
            addCriterion("RECORD_FLOW not between", value1, value2, "recordFlow");
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

        public Criteria andTelephoneIsNull() {
            addCriterion("TELEPHONE is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("TELEPHONE is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneEqualTo(String value) {
            addCriterion("TELEPHONE =", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotEqualTo(String value) {
            addCriterion("TELEPHONE <>", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThan(String value) {
            addCriterion("TELEPHONE >", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("TELEPHONE >=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThan(String value) {
            addCriterion("TELEPHONE <", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(String value) {
            addCriterion("TELEPHONE <=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLike(String value) {
            addCriterion("TELEPHONE like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotLike(String value) {
            addCriterion("TELEPHONE not like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIn(List<String> values) {
            addCriterion("TELEPHONE in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotIn(List<String> values) {
            addCriterion("TELEPHONE not in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneBetween(String value1, String value2) {
            addCriterion("TELEPHONE between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotBetween(String value1, String value2) {
            addCriterion("TELEPHONE not between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressIsNull() {
            addCriterion("EMERGENCY_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressIsNotNull() {
            addCriterion("EMERGENCY_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressEqualTo(String value) {
            addCriterion("EMERGENCY_ADDRESS =", value, "emergencyAddress");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressNotEqualTo(String value) {
            addCriterion("EMERGENCY_ADDRESS <>", value, "emergencyAddress");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressGreaterThan(String value) {
            addCriterion("EMERGENCY_ADDRESS >", value, "emergencyAddress");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressGreaterThanOrEqualTo(String value) {
            addCriterion("EMERGENCY_ADDRESS >=", value, "emergencyAddress");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressLessThan(String value) {
            addCriterion("EMERGENCY_ADDRESS <", value, "emergencyAddress");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressLessThanOrEqualTo(String value) {
            addCriterion("EMERGENCY_ADDRESS <=", value, "emergencyAddress");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressLike(String value) {
            addCriterion("EMERGENCY_ADDRESS like", value, "emergencyAddress");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressNotLike(String value) {
            addCriterion("EMERGENCY_ADDRESS not like", value, "emergencyAddress");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressIn(List<String> values) {
            addCriterion("EMERGENCY_ADDRESS in", values, "emergencyAddress");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressNotIn(List<String> values) {
            addCriterion("EMERGENCY_ADDRESS not in", values, "emergencyAddress");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressBetween(String value1, String value2) {
            addCriterion("EMERGENCY_ADDRESS between", value1, value2, "emergencyAddress");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressNotBetween(String value1, String value2) {
            addCriterion("EMERGENCY_ADDRESS not between", value1, value2, "emergencyAddress");
            return (Criteria) this;
        }

        public Criteria andWorkSchoolNameIsNull() {
            addCriterion("WORK_SCHOOL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andWorkSchoolNameIsNotNull() {
            addCriterion("WORK_SCHOOL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andWorkSchoolNameEqualTo(String value) {
            addCriterion("WORK_SCHOOL_NAME =", value, "workSchoolName");
            return (Criteria) this;
        }

        public Criteria andWorkSchoolNameNotEqualTo(String value) {
            addCriterion("WORK_SCHOOL_NAME <>", value, "workSchoolName");
            return (Criteria) this;
        }

        public Criteria andWorkSchoolNameGreaterThan(String value) {
            addCriterion("WORK_SCHOOL_NAME >", value, "workSchoolName");
            return (Criteria) this;
        }

        public Criteria andWorkSchoolNameGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_SCHOOL_NAME >=", value, "workSchoolName");
            return (Criteria) this;
        }

        public Criteria andWorkSchoolNameLessThan(String value) {
            addCriterion("WORK_SCHOOL_NAME <", value, "workSchoolName");
            return (Criteria) this;
        }

        public Criteria andWorkSchoolNameLessThanOrEqualTo(String value) {
            addCriterion("WORK_SCHOOL_NAME <=", value, "workSchoolName");
            return (Criteria) this;
        }

        public Criteria andWorkSchoolNameLike(String value) {
            addCriterion("WORK_SCHOOL_NAME like", value, "workSchoolName");
            return (Criteria) this;
        }

        public Criteria andWorkSchoolNameNotLike(String value) {
            addCriterion("WORK_SCHOOL_NAME not like", value, "workSchoolName");
            return (Criteria) this;
        }

        public Criteria andWorkSchoolNameIn(List<String> values) {
            addCriterion("WORK_SCHOOL_NAME in", values, "workSchoolName");
            return (Criteria) this;
        }

        public Criteria andWorkSchoolNameNotIn(List<String> values) {
            addCriterion("WORK_SCHOOL_NAME not in", values, "workSchoolName");
            return (Criteria) this;
        }

        public Criteria andWorkSchoolNameBetween(String value1, String value2) {
            addCriterion("WORK_SCHOOL_NAME between", value1, value2, "workSchoolName");
            return (Criteria) this;
        }

        public Criteria andWorkSchoolNameNotBetween(String value1, String value2) {
            addCriterion("WORK_SCHOOL_NAME not between", value1, value2, "workSchoolName");
            return (Criteria) this;
        }

        public Criteria andOrgRankIsNull() {
            addCriterion("ORG_RANK is null");
            return (Criteria) this;
        }

        public Criteria andOrgRankIsNotNull() {
            addCriterion("ORG_RANK is not null");
            return (Criteria) this;
        }

        public Criteria andOrgRankEqualTo(String value) {
            addCriterion("ORG_RANK =", value, "orgRank");
            return (Criteria) this;
        }

        public Criteria andOrgRankNotEqualTo(String value) {
            addCriterion("ORG_RANK <>", value, "orgRank");
            return (Criteria) this;
        }

        public Criteria andOrgRankGreaterThan(String value) {
            addCriterion("ORG_RANK >", value, "orgRank");
            return (Criteria) this;
        }

        public Criteria andOrgRankGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_RANK >=", value, "orgRank");
            return (Criteria) this;
        }

        public Criteria andOrgRankLessThan(String value) {
            addCriterion("ORG_RANK <", value, "orgRank");
            return (Criteria) this;
        }

        public Criteria andOrgRankLessThanOrEqualTo(String value) {
            addCriterion("ORG_RANK <=", value, "orgRank");
            return (Criteria) this;
        }

        public Criteria andOrgRankLike(String value) {
            addCriterion("ORG_RANK like", value, "orgRank");
            return (Criteria) this;
        }

        public Criteria andOrgRankNotLike(String value) {
            addCriterion("ORG_RANK not like", value, "orgRank");
            return (Criteria) this;
        }

        public Criteria andOrgRankIn(List<String> values) {
            addCriterion("ORG_RANK in", values, "orgRank");
            return (Criteria) this;
        }

        public Criteria andOrgRankNotIn(List<String> values) {
            addCriterion("ORG_RANK not in", values, "orgRank");
            return (Criteria) this;
        }

        public Criteria andOrgRankBetween(String value1, String value2) {
            addCriterion("ORG_RANK between", value1, value2, "orgRank");
            return (Criteria) this;
        }

        public Criteria andOrgRankNotBetween(String value1, String value2) {
            addCriterion("ORG_RANK not between", value1, value2, "orgRank");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIsNull() {
            addCriterion("ORG_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIsNotNull() {
            addCriterion("ORG_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andOrgLevelEqualTo(String value) {
            addCriterion("ORG_LEVEL =", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNotEqualTo(String value) {
            addCriterion("ORG_LEVEL <>", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelGreaterThan(String value) {
            addCriterion("ORG_LEVEL >", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_LEVEL >=", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelLessThan(String value) {
            addCriterion("ORG_LEVEL <", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelLessThanOrEqualTo(String value) {
            addCriterion("ORG_LEVEL <=", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelLike(String value) {
            addCriterion("ORG_LEVEL like", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNotLike(String value) {
            addCriterion("ORG_LEVEL not like", value, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelIn(List<String> values) {
            addCriterion("ORG_LEVEL in", values, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNotIn(List<String> values) {
            addCriterion("ORG_LEVEL not in", values, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelBetween(String value1, String value2) {
            addCriterion("ORG_LEVEL between", value1, value2, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andOrgLevelNotBetween(String value1, String value2) {
            addCriterion("ORG_LEVEL not between", value1, value2, "orgLevel");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdIsNull() {
            addCriterion("GRADUATED_ID is null");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdIsNotNull() {
            addCriterion("GRADUATED_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdEqualTo(String value) {
            addCriterion("GRADUATED_ID =", value, "graduatedId");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdNotEqualTo(String value) {
            addCriterion("GRADUATED_ID <>", value, "graduatedId");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdGreaterThan(String value) {
            addCriterion("GRADUATED_ID >", value, "graduatedId");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATED_ID >=", value, "graduatedId");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdLessThan(String value) {
            addCriterion("GRADUATED_ID <", value, "graduatedId");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdLessThanOrEqualTo(String value) {
            addCriterion("GRADUATED_ID <=", value, "graduatedId");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdLike(String value) {
            addCriterion("GRADUATED_ID like", value, "graduatedId");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdNotLike(String value) {
            addCriterion("GRADUATED_ID not like", value, "graduatedId");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdIn(List<String> values) {
            addCriterion("GRADUATED_ID in", values, "graduatedId");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdNotIn(List<String> values) {
            addCriterion("GRADUATED_ID not in", values, "graduatedId");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdBetween(String value1, String value2) {
            addCriterion("GRADUATED_ID between", value1, value2, "graduatedId");
            return (Criteria) this;
        }

        public Criteria andGraduatedIdNotBetween(String value1, String value2) {
            addCriterion("GRADUATED_ID not between", value1, value2, "graduatedId");
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

        public Criteria andSpecializedIsNull() {
            addCriterion("SPECIALIZED is null");
            return (Criteria) this;
        }

        public Criteria andSpecializedIsNotNull() {
            addCriterion("SPECIALIZED is not null");
            return (Criteria) this;
        }

        public Criteria andSpecializedEqualTo(String value) {
            addCriterion("SPECIALIZED =", value, "specialized");
            return (Criteria) this;
        }

        public Criteria andSpecializedNotEqualTo(String value) {
            addCriterion("SPECIALIZED <>", value, "specialized");
            return (Criteria) this;
        }

        public Criteria andSpecializedGreaterThan(String value) {
            addCriterion("SPECIALIZED >", value, "specialized");
            return (Criteria) this;
        }

        public Criteria andSpecializedGreaterThanOrEqualTo(String value) {
            addCriterion("SPECIALIZED >=", value, "specialized");
            return (Criteria) this;
        }

        public Criteria andSpecializedLessThan(String value) {
            addCriterion("SPECIALIZED <", value, "specialized");
            return (Criteria) this;
        }

        public Criteria andSpecializedLessThanOrEqualTo(String value) {
            addCriterion("SPECIALIZED <=", value, "specialized");
            return (Criteria) this;
        }

        public Criteria andSpecializedLike(String value) {
            addCriterion("SPECIALIZED like", value, "specialized");
            return (Criteria) this;
        }

        public Criteria andSpecializedNotLike(String value) {
            addCriterion("SPECIALIZED not like", value, "specialized");
            return (Criteria) this;
        }

        public Criteria andSpecializedIn(List<String> values) {
            addCriterion("SPECIALIZED in", values, "specialized");
            return (Criteria) this;
        }

        public Criteria andSpecializedNotIn(List<String> values) {
            addCriterion("SPECIALIZED not in", values, "specialized");
            return (Criteria) this;
        }

        public Criteria andSpecializedBetween(String value1, String value2) {
            addCriterion("SPECIALIZED between", value1, value2, "specialized");
            return (Criteria) this;
        }

        public Criteria andSpecializedNotBetween(String value1, String value2) {
            addCriterion("SPECIALIZED not between", value1, value2, "specialized");
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

        public Criteria andCollegeCertificateNoIsNull() {
            addCriterion("COLLEGE_CERTIFICATE_NO is null");
            return (Criteria) this;
        }

        public Criteria andCollegeCertificateNoIsNotNull() {
            addCriterion("COLLEGE_CERTIFICATE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCollegeCertificateNoEqualTo(String value) {
            addCriterion("COLLEGE_CERTIFICATE_NO =", value, "collegeCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCollegeCertificateNoNotEqualTo(String value) {
            addCriterion("COLLEGE_CERTIFICATE_NO <>", value, "collegeCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCollegeCertificateNoGreaterThan(String value) {
            addCriterion("COLLEGE_CERTIFICATE_NO >", value, "collegeCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCollegeCertificateNoGreaterThanOrEqualTo(String value) {
            addCriterion("COLLEGE_CERTIFICATE_NO >=", value, "collegeCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCollegeCertificateNoLessThan(String value) {
            addCriterion("COLLEGE_CERTIFICATE_NO <", value, "collegeCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCollegeCertificateNoLessThanOrEqualTo(String value) {
            addCriterion("COLLEGE_CERTIFICATE_NO <=", value, "collegeCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCollegeCertificateNoLike(String value) {
            addCriterion("COLLEGE_CERTIFICATE_NO like", value, "collegeCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCollegeCertificateNoNotLike(String value) {
            addCriterion("COLLEGE_CERTIFICATE_NO not like", value, "collegeCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCollegeCertificateNoIn(List<String> values) {
            addCriterion("COLLEGE_CERTIFICATE_NO in", values, "collegeCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCollegeCertificateNoNotIn(List<String> values) {
            addCriterion("COLLEGE_CERTIFICATE_NO not in", values, "collegeCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCollegeCertificateNoBetween(String value1, String value2) {
            addCriterion("COLLEGE_CERTIFICATE_NO between", value1, value2, "collegeCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCollegeCertificateNoNotBetween(String value1, String value2) {
            addCriterion("COLLEGE_CERTIFICATE_NO not between", value1, value2, "collegeCertificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateUriIsNull() {
            addCriterion("CERTIFICATE_URI is null");
            return (Criteria) this;
        }

        public Criteria andCertificateUriIsNotNull() {
            addCriterion("CERTIFICATE_URI is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateUriEqualTo(String value) {
            addCriterion("CERTIFICATE_URI =", value, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriNotEqualTo(String value) {
            addCriterion("CERTIFICATE_URI <>", value, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriGreaterThan(String value) {
            addCriterion("CERTIFICATE_URI >", value, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_URI >=", value, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriLessThan(String value) {
            addCriterion("CERTIFICATE_URI <", value, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_URI <=", value, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriLike(String value) {
            addCriterion("CERTIFICATE_URI like", value, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriNotLike(String value) {
            addCriterion("CERTIFICATE_URI not like", value, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriIn(List<String> values) {
            addCriterion("CERTIFICATE_URI in", values, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriNotIn(List<String> values) {
            addCriterion("CERTIFICATE_URI not in", values, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_URI between", value1, value2, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andCertificateUriNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_URI not between", value1, value2, "certificateUri");
            return (Criteria) this;
        }

        public Criteria andDegreeUriIsNull() {
            addCriterion("DEGREE_URI is null");
            return (Criteria) this;
        }

        public Criteria andDegreeUriIsNotNull() {
            addCriterion("DEGREE_URI is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeUriEqualTo(String value) {
            addCriterion("DEGREE_URI =", value, "degreeUri");
            return (Criteria) this;
        }

        public Criteria andDegreeUriNotEqualTo(String value) {
            addCriterion("DEGREE_URI <>", value, "degreeUri");
            return (Criteria) this;
        }

        public Criteria andDegreeUriGreaterThan(String value) {
            addCriterion("DEGREE_URI >", value, "degreeUri");
            return (Criteria) this;
        }

        public Criteria andDegreeUriGreaterThanOrEqualTo(String value) {
            addCriterion("DEGREE_URI >=", value, "degreeUri");
            return (Criteria) this;
        }

        public Criteria andDegreeUriLessThan(String value) {
            addCriterion("DEGREE_URI <", value, "degreeUri");
            return (Criteria) this;
        }

        public Criteria andDegreeUriLessThanOrEqualTo(String value) {
            addCriterion("DEGREE_URI <=", value, "degreeUri");
            return (Criteria) this;
        }

        public Criteria andDegreeUriLike(String value) {
            addCriterion("DEGREE_URI like", value, "degreeUri");
            return (Criteria) this;
        }

        public Criteria andDegreeUriNotLike(String value) {
            addCriterion("DEGREE_URI not like", value, "degreeUri");
            return (Criteria) this;
        }

        public Criteria andDegreeUriIn(List<String> values) {
            addCriterion("DEGREE_URI in", values, "degreeUri");
            return (Criteria) this;
        }

        public Criteria andDegreeUriNotIn(List<String> values) {
            addCriterion("DEGREE_URI not in", values, "degreeUri");
            return (Criteria) this;
        }

        public Criteria andDegreeUriBetween(String value1, String value2) {
            addCriterion("DEGREE_URI between", value1, value2, "degreeUri");
            return (Criteria) this;
        }

        public Criteria andDegreeUriNotBetween(String value1, String value2) {
            addCriterion("DEGREE_URI not between", value1, value2, "degreeUri");
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

        public Criteria andIsMasterIsNull() {
            addCriterion("IS_MASTER is null");
            return (Criteria) this;
        }

        public Criteria andIsMasterIsNotNull() {
            addCriterion("IS_MASTER is not null");
            return (Criteria) this;
        }

        public Criteria andIsMasterEqualTo(String value) {
            addCriterion("IS_MASTER =", value, "isMaster");
            return (Criteria) this;
        }

        public Criteria andIsMasterNotEqualTo(String value) {
            addCriterion("IS_MASTER <>", value, "isMaster");
            return (Criteria) this;
        }

        public Criteria andIsMasterGreaterThan(String value) {
            addCriterion("IS_MASTER >", value, "isMaster");
            return (Criteria) this;
        }

        public Criteria andIsMasterGreaterThanOrEqualTo(String value) {
            addCriterion("IS_MASTER >=", value, "isMaster");
            return (Criteria) this;
        }

        public Criteria andIsMasterLessThan(String value) {
            addCriterion("IS_MASTER <", value, "isMaster");
            return (Criteria) this;
        }

        public Criteria andIsMasterLessThanOrEqualTo(String value) {
            addCriterion("IS_MASTER <=", value, "isMaster");
            return (Criteria) this;
        }

        public Criteria andIsMasterLike(String value) {
            addCriterion("IS_MASTER like", value, "isMaster");
            return (Criteria) this;
        }

        public Criteria andIsMasterNotLike(String value) {
            addCriterion("IS_MASTER not like", value, "isMaster");
            return (Criteria) this;
        }

        public Criteria andIsMasterIn(List<String> values) {
            addCriterion("IS_MASTER in", values, "isMaster");
            return (Criteria) this;
        }

        public Criteria andIsMasterNotIn(List<String> values) {
            addCriterion("IS_MASTER not in", values, "isMaster");
            return (Criteria) this;
        }

        public Criteria andIsMasterBetween(String value1, String value2) {
            addCriterion("IS_MASTER between", value1, value2, "isMaster");
            return (Criteria) this;
        }

        public Criteria andIsMasterNotBetween(String value1, String value2) {
            addCriterion("IS_MASTER not between", value1, value2, "isMaster");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeIdIsNull() {
            addCriterion("MASTER_DEGREE_ID is null");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeIdIsNotNull() {
            addCriterion("MASTER_DEGREE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeIdEqualTo(String value) {
            addCriterion("MASTER_DEGREE_ID =", value, "masterDegreeId");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeIdNotEqualTo(String value) {
            addCriterion("MASTER_DEGREE_ID <>", value, "masterDegreeId");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeIdGreaterThan(String value) {
            addCriterion("MASTER_DEGREE_ID >", value, "masterDegreeId");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeIdGreaterThanOrEqualTo(String value) {
            addCriterion("MASTER_DEGREE_ID >=", value, "masterDegreeId");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeIdLessThan(String value) {
            addCriterion("MASTER_DEGREE_ID <", value, "masterDegreeId");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeIdLessThanOrEqualTo(String value) {
            addCriterion("MASTER_DEGREE_ID <=", value, "masterDegreeId");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeIdLike(String value) {
            addCriterion("MASTER_DEGREE_ID like", value, "masterDegreeId");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeIdNotLike(String value) {
            addCriterion("MASTER_DEGREE_ID not like", value, "masterDegreeId");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeIdIn(List<String> values) {
            addCriterion("MASTER_DEGREE_ID in", values, "masterDegreeId");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeIdNotIn(List<String> values) {
            addCriterion("MASTER_DEGREE_ID not in", values, "masterDegreeId");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeIdBetween(String value1, String value2) {
            addCriterion("MASTER_DEGREE_ID between", value1, value2, "masterDegreeId");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeIdNotBetween(String value1, String value2) {
            addCriterion("MASTER_DEGREE_ID not between", value1, value2, "masterDegreeId");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeNameIsNull() {
            addCriterion("MASTER_DEGREE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeNameIsNotNull() {
            addCriterion("MASTER_DEGREE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeNameEqualTo(String value) {
            addCriterion("MASTER_DEGREE_NAME =", value, "masterDegreeName");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeNameNotEqualTo(String value) {
            addCriterion("MASTER_DEGREE_NAME <>", value, "masterDegreeName");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeNameGreaterThan(String value) {
            addCriterion("MASTER_DEGREE_NAME >", value, "masterDegreeName");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeNameGreaterThanOrEqualTo(String value) {
            addCriterion("MASTER_DEGREE_NAME >=", value, "masterDegreeName");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeNameLessThan(String value) {
            addCriterion("MASTER_DEGREE_NAME <", value, "masterDegreeName");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeNameLessThanOrEqualTo(String value) {
            addCriterion("MASTER_DEGREE_NAME <=", value, "masterDegreeName");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeNameLike(String value) {
            addCriterion("MASTER_DEGREE_NAME like", value, "masterDegreeName");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeNameNotLike(String value) {
            addCriterion("MASTER_DEGREE_NAME not like", value, "masterDegreeName");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeNameIn(List<String> values) {
            addCriterion("MASTER_DEGREE_NAME in", values, "masterDegreeName");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeNameNotIn(List<String> values) {
            addCriterion("MASTER_DEGREE_NAME not in", values, "masterDegreeName");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeNameBetween(String value1, String value2) {
            addCriterion("MASTER_DEGREE_NAME between", value1, value2, "masterDegreeName");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeNameNotBetween(String value1, String value2) {
            addCriterion("MASTER_DEGREE_NAME not between", value1, value2, "masterDegreeName");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeIdIsNull() {
            addCriterion("MASTER_DEGREE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeIdIsNotNull() {
            addCriterion("MASTER_DEGREE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeIdEqualTo(String value) {
            addCriterion("MASTER_DEGREE_TYPE_ID =", value, "masterDegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeIdNotEqualTo(String value) {
            addCriterion("MASTER_DEGREE_TYPE_ID <>", value, "masterDegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeIdGreaterThan(String value) {
            addCriterion("MASTER_DEGREE_TYPE_ID >", value, "masterDegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("MASTER_DEGREE_TYPE_ID >=", value, "masterDegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeIdLessThan(String value) {
            addCriterion("MASTER_DEGREE_TYPE_ID <", value, "masterDegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeIdLessThanOrEqualTo(String value) {
            addCriterion("MASTER_DEGREE_TYPE_ID <=", value, "masterDegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeIdLike(String value) {
            addCriterion("MASTER_DEGREE_TYPE_ID like", value, "masterDegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeIdNotLike(String value) {
            addCriterion("MASTER_DEGREE_TYPE_ID not like", value, "masterDegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeIdIn(List<String> values) {
            addCriterion("MASTER_DEGREE_TYPE_ID in", values, "masterDegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeIdNotIn(List<String> values) {
            addCriterion("MASTER_DEGREE_TYPE_ID not in", values, "masterDegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeIdBetween(String value1, String value2) {
            addCriterion("MASTER_DEGREE_TYPE_ID between", value1, value2, "masterDegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeIdNotBetween(String value1, String value2) {
            addCriterion("MASTER_DEGREE_TYPE_ID not between", value1, value2, "masterDegreeTypeId");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeNameIsNull() {
            addCriterion("MASTER_DEGREE_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeNameIsNotNull() {
            addCriterion("MASTER_DEGREE_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeNameEqualTo(String value) {
            addCriterion("MASTER_DEGREE_TYPE_NAME =", value, "masterDegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeNameNotEqualTo(String value) {
            addCriterion("MASTER_DEGREE_TYPE_NAME <>", value, "masterDegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeNameGreaterThan(String value) {
            addCriterion("MASTER_DEGREE_TYPE_NAME >", value, "masterDegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("MASTER_DEGREE_TYPE_NAME >=", value, "masterDegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeNameLessThan(String value) {
            addCriterion("MASTER_DEGREE_TYPE_NAME <", value, "masterDegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeNameLessThanOrEqualTo(String value) {
            addCriterion("MASTER_DEGREE_TYPE_NAME <=", value, "masterDegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeNameLike(String value) {
            addCriterion("MASTER_DEGREE_TYPE_NAME like", value, "masterDegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeNameNotLike(String value) {
            addCriterion("MASTER_DEGREE_TYPE_NAME not like", value, "masterDegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeNameIn(List<String> values) {
            addCriterion("MASTER_DEGREE_TYPE_NAME in", values, "masterDegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeNameNotIn(List<String> values) {
            addCriterion("MASTER_DEGREE_TYPE_NAME not in", values, "masterDegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeNameBetween(String value1, String value2) {
            addCriterion("MASTER_DEGREE_TYPE_NAME between", value1, value2, "masterDegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andMasterDegreeTypeNameNotBetween(String value1, String value2) {
            addCriterion("MASTER_DEGREE_TYPE_NAME not between", value1, value2, "masterDegreeTypeName");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolIdIsNull() {
            addCriterion("MASTER_GRA_SCHOOL_ID is null");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolIdIsNotNull() {
            addCriterion("MASTER_GRA_SCHOOL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolIdEqualTo(String value) {
            addCriterion("MASTER_GRA_SCHOOL_ID =", value, "masterGraSchoolId");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolIdNotEqualTo(String value) {
            addCriterion("MASTER_GRA_SCHOOL_ID <>", value, "masterGraSchoolId");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolIdGreaterThan(String value) {
            addCriterion("MASTER_GRA_SCHOOL_ID >", value, "masterGraSchoolId");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolIdGreaterThanOrEqualTo(String value) {
            addCriterion("MASTER_GRA_SCHOOL_ID >=", value, "masterGraSchoolId");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolIdLessThan(String value) {
            addCriterion("MASTER_GRA_SCHOOL_ID <", value, "masterGraSchoolId");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolIdLessThanOrEqualTo(String value) {
            addCriterion("MASTER_GRA_SCHOOL_ID <=", value, "masterGraSchoolId");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolIdLike(String value) {
            addCriterion("MASTER_GRA_SCHOOL_ID like", value, "masterGraSchoolId");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolIdNotLike(String value) {
            addCriterion("MASTER_GRA_SCHOOL_ID not like", value, "masterGraSchoolId");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolIdIn(List<String> values) {
            addCriterion("MASTER_GRA_SCHOOL_ID in", values, "masterGraSchoolId");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolIdNotIn(List<String> values) {
            addCriterion("MASTER_GRA_SCHOOL_ID not in", values, "masterGraSchoolId");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolIdBetween(String value1, String value2) {
            addCriterion("MASTER_GRA_SCHOOL_ID between", value1, value2, "masterGraSchoolId");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolIdNotBetween(String value1, String value2) {
            addCriterion("MASTER_GRA_SCHOOL_ID not between", value1, value2, "masterGraSchoolId");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolNameIsNull() {
            addCriterion("MASTER_GRA_SCHOOL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolNameIsNotNull() {
            addCriterion("MASTER_GRA_SCHOOL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolNameEqualTo(String value) {
            addCriterion("MASTER_GRA_SCHOOL_NAME =", value, "masterGraSchoolName");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolNameNotEqualTo(String value) {
            addCriterion("MASTER_GRA_SCHOOL_NAME <>", value, "masterGraSchoolName");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolNameGreaterThan(String value) {
            addCriterion("MASTER_GRA_SCHOOL_NAME >", value, "masterGraSchoolName");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolNameGreaterThanOrEqualTo(String value) {
            addCriterion("MASTER_GRA_SCHOOL_NAME >=", value, "masterGraSchoolName");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolNameLessThan(String value) {
            addCriterion("MASTER_GRA_SCHOOL_NAME <", value, "masterGraSchoolName");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolNameLessThanOrEqualTo(String value) {
            addCriterion("MASTER_GRA_SCHOOL_NAME <=", value, "masterGraSchoolName");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolNameLike(String value) {
            addCriterion("MASTER_GRA_SCHOOL_NAME like", value, "masterGraSchoolName");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolNameNotLike(String value) {
            addCriterion("MASTER_GRA_SCHOOL_NAME not like", value, "masterGraSchoolName");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolNameIn(List<String> values) {
            addCriterion("MASTER_GRA_SCHOOL_NAME in", values, "masterGraSchoolName");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolNameNotIn(List<String> values) {
            addCriterion("MASTER_GRA_SCHOOL_NAME not in", values, "masterGraSchoolName");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolNameBetween(String value1, String value2) {
            addCriterion("MASTER_GRA_SCHOOL_NAME between", value1, value2, "masterGraSchoolName");
            return (Criteria) this;
        }

        public Criteria andMasterGraSchoolNameNotBetween(String value1, String value2) {
            addCriterion("MASTER_GRA_SCHOOL_NAME not between", value1, value2, "masterGraSchoolName");
            return (Criteria) this;
        }

        public Criteria andMasterGraTimeIsNull() {
            addCriterion("MASTER_GRA_TIME is null");
            return (Criteria) this;
        }

        public Criteria andMasterGraTimeIsNotNull() {
            addCriterion("MASTER_GRA_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andMasterGraTimeEqualTo(String value) {
            addCriterion("MASTER_GRA_TIME =", value, "masterGraTime");
            return (Criteria) this;
        }

        public Criteria andMasterGraTimeNotEqualTo(String value) {
            addCriterion("MASTER_GRA_TIME <>", value, "masterGraTime");
            return (Criteria) this;
        }

        public Criteria andMasterGraTimeGreaterThan(String value) {
            addCriterion("MASTER_GRA_TIME >", value, "masterGraTime");
            return (Criteria) this;
        }

        public Criteria andMasterGraTimeGreaterThanOrEqualTo(String value) {
            addCriterion("MASTER_GRA_TIME >=", value, "masterGraTime");
            return (Criteria) this;
        }

        public Criteria andMasterGraTimeLessThan(String value) {
            addCriterion("MASTER_GRA_TIME <", value, "masterGraTime");
            return (Criteria) this;
        }

        public Criteria andMasterGraTimeLessThanOrEqualTo(String value) {
            addCriterion("MASTER_GRA_TIME <=", value, "masterGraTime");
            return (Criteria) this;
        }

        public Criteria andMasterGraTimeLike(String value) {
            addCriterion("MASTER_GRA_TIME like", value, "masterGraTime");
            return (Criteria) this;
        }

        public Criteria andMasterGraTimeNotLike(String value) {
            addCriterion("MASTER_GRA_TIME not like", value, "masterGraTime");
            return (Criteria) this;
        }

        public Criteria andMasterGraTimeIn(List<String> values) {
            addCriterion("MASTER_GRA_TIME in", values, "masterGraTime");
            return (Criteria) this;
        }

        public Criteria andMasterGraTimeNotIn(List<String> values) {
            addCriterion("MASTER_GRA_TIME not in", values, "masterGraTime");
            return (Criteria) this;
        }

        public Criteria andMasterGraTimeBetween(String value1, String value2) {
            addCriterion("MASTER_GRA_TIME between", value1, value2, "masterGraTime");
            return (Criteria) this;
        }

        public Criteria andMasterGraTimeNotBetween(String value1, String value2) {
            addCriterion("MASTER_GRA_TIME not between", value1, value2, "masterGraTime");
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

        public Criteria andIsDoctorIsNull() {
            addCriterion("IS_DOCTOR is null");
            return (Criteria) this;
        }

        public Criteria andIsDoctorIsNotNull() {
            addCriterion("IS_DOCTOR is not null");
            return (Criteria) this;
        }

        public Criteria andIsDoctorEqualTo(String value) {
            addCriterion("IS_DOCTOR =", value, "isDoctor");
            return (Criteria) this;
        }

        public Criteria andIsDoctorNotEqualTo(String value) {
            addCriterion("IS_DOCTOR <>", value, "isDoctor");
            return (Criteria) this;
        }

        public Criteria andIsDoctorGreaterThan(String value) {
            addCriterion("IS_DOCTOR >", value, "isDoctor");
            return (Criteria) this;
        }

        public Criteria andIsDoctorGreaterThanOrEqualTo(String value) {
            addCriterion("IS_DOCTOR >=", value, "isDoctor");
            return (Criteria) this;
        }

        public Criteria andIsDoctorLessThan(String value) {
            addCriterion("IS_DOCTOR <", value, "isDoctor");
            return (Criteria) this;
        }

        public Criteria andIsDoctorLessThanOrEqualTo(String value) {
            addCriterion("IS_DOCTOR <=", value, "isDoctor");
            return (Criteria) this;
        }

        public Criteria andIsDoctorLike(String value) {
            addCriterion("IS_DOCTOR like", value, "isDoctor");
            return (Criteria) this;
        }

        public Criteria andIsDoctorNotLike(String value) {
            addCriterion("IS_DOCTOR not like", value, "isDoctor");
            return (Criteria) this;
        }

        public Criteria andIsDoctorIn(List<String> values) {
            addCriterion("IS_DOCTOR in", values, "isDoctor");
            return (Criteria) this;
        }

        public Criteria andIsDoctorNotIn(List<String> values) {
            addCriterion("IS_DOCTOR not in", values, "isDoctor");
            return (Criteria) this;
        }

        public Criteria andIsDoctorBetween(String value1, String value2) {
            addCriterion("IS_DOCTOR between", value1, value2, "isDoctor");
            return (Criteria) this;
        }

        public Criteria andIsDoctorNotBetween(String value1, String value2) {
            addCriterion("IS_DOCTOR not between", value1, value2, "isDoctor");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeIdIsNull() {
            addCriterion("DOCTOR_DEGREE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeIdIsNotNull() {
            addCriterion("DOCTOR_DEGREE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeIdEqualTo(String value) {
            addCriterion("DOCTOR_DEGREE_ID =", value, "doctorDegreeId");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeIdNotEqualTo(String value) {
            addCriterion("DOCTOR_DEGREE_ID <>", value, "doctorDegreeId");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeIdGreaterThan(String value) {
            addCriterion("DOCTOR_DEGREE_ID >", value, "doctorDegreeId");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeIdGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_DEGREE_ID >=", value, "doctorDegreeId");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeIdLessThan(String value) {
            addCriterion("DOCTOR_DEGREE_ID <", value, "doctorDegreeId");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeIdLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_DEGREE_ID <=", value, "doctorDegreeId");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeIdLike(String value) {
            addCriterion("DOCTOR_DEGREE_ID like", value, "doctorDegreeId");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeIdNotLike(String value) {
            addCriterion("DOCTOR_DEGREE_ID not like", value, "doctorDegreeId");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeIdIn(List<String> values) {
            addCriterion("DOCTOR_DEGREE_ID in", values, "doctorDegreeId");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeIdNotIn(List<String> values) {
            addCriterion("DOCTOR_DEGREE_ID not in", values, "doctorDegreeId");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeIdBetween(String value1, String value2) {
            addCriterion("DOCTOR_DEGREE_ID between", value1, value2, "doctorDegreeId");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeIdNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_DEGREE_ID not between", value1, value2, "doctorDegreeId");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeNameIsNull() {
            addCriterion("DOCTOR_DEGREE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeNameIsNotNull() {
            addCriterion("DOCTOR_DEGREE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeNameEqualTo(String value) {
            addCriterion("DOCTOR_DEGREE_NAME =", value, "doctorDegreeName");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeNameNotEqualTo(String value) {
            addCriterion("DOCTOR_DEGREE_NAME <>", value, "doctorDegreeName");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeNameGreaterThan(String value) {
            addCriterion("DOCTOR_DEGREE_NAME >", value, "doctorDegreeName");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeNameGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_DEGREE_NAME >=", value, "doctorDegreeName");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeNameLessThan(String value) {
            addCriterion("DOCTOR_DEGREE_NAME <", value, "doctorDegreeName");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeNameLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_DEGREE_NAME <=", value, "doctorDegreeName");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeNameLike(String value) {
            addCriterion("DOCTOR_DEGREE_NAME like", value, "doctorDegreeName");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeNameNotLike(String value) {
            addCriterion("DOCTOR_DEGREE_NAME not like", value, "doctorDegreeName");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeNameIn(List<String> values) {
            addCriterion("DOCTOR_DEGREE_NAME in", values, "doctorDegreeName");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeNameNotIn(List<String> values) {
            addCriterion("DOCTOR_DEGREE_NAME not in", values, "doctorDegreeName");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeNameBetween(String value1, String value2) {
            addCriterion("DOCTOR_DEGREE_NAME between", value1, value2, "doctorDegreeName");
            return (Criteria) this;
        }

        public Criteria andDoctorDegreeNameNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_DEGREE_NAME not between", value1, value2, "doctorDegreeName");
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

        public Criteria andDoctorGraSchoolIdIsNull() {
            addCriterion("DOCTOR_GRA_SCHOOL_ID is null");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolIdIsNotNull() {
            addCriterion("DOCTOR_GRA_SCHOOL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolIdEqualTo(String value) {
            addCriterion("DOCTOR_GRA_SCHOOL_ID =", value, "doctorGraSchoolId");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolIdNotEqualTo(String value) {
            addCriterion("DOCTOR_GRA_SCHOOL_ID <>", value, "doctorGraSchoolId");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolIdGreaterThan(String value) {
            addCriterion("DOCTOR_GRA_SCHOOL_ID >", value, "doctorGraSchoolId");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolIdGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_GRA_SCHOOL_ID >=", value, "doctorGraSchoolId");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolIdLessThan(String value) {
            addCriterion("DOCTOR_GRA_SCHOOL_ID <", value, "doctorGraSchoolId");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolIdLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_GRA_SCHOOL_ID <=", value, "doctorGraSchoolId");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolIdLike(String value) {
            addCriterion("DOCTOR_GRA_SCHOOL_ID like", value, "doctorGraSchoolId");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolIdNotLike(String value) {
            addCriterion("DOCTOR_GRA_SCHOOL_ID not like", value, "doctorGraSchoolId");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolIdIn(List<String> values) {
            addCriterion("DOCTOR_GRA_SCHOOL_ID in", values, "doctorGraSchoolId");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolIdNotIn(List<String> values) {
            addCriterion("DOCTOR_GRA_SCHOOL_ID not in", values, "doctorGraSchoolId");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolIdBetween(String value1, String value2) {
            addCriterion("DOCTOR_GRA_SCHOOL_ID between", value1, value2, "doctorGraSchoolId");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolIdNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_GRA_SCHOOL_ID not between", value1, value2, "doctorGraSchoolId");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolNameIsNull() {
            addCriterion("DOCTOR_GRA_SCHOOL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolNameIsNotNull() {
            addCriterion("DOCTOR_GRA_SCHOOL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolNameEqualTo(String value) {
            addCriterion("DOCTOR_GRA_SCHOOL_NAME =", value, "doctorGraSchoolName");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolNameNotEqualTo(String value) {
            addCriterion("DOCTOR_GRA_SCHOOL_NAME <>", value, "doctorGraSchoolName");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolNameGreaterThan(String value) {
            addCriterion("DOCTOR_GRA_SCHOOL_NAME >", value, "doctorGraSchoolName");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolNameGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_GRA_SCHOOL_NAME >=", value, "doctorGraSchoolName");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolNameLessThan(String value) {
            addCriterion("DOCTOR_GRA_SCHOOL_NAME <", value, "doctorGraSchoolName");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolNameLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_GRA_SCHOOL_NAME <=", value, "doctorGraSchoolName");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolNameLike(String value) {
            addCriterion("DOCTOR_GRA_SCHOOL_NAME like", value, "doctorGraSchoolName");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolNameNotLike(String value) {
            addCriterion("DOCTOR_GRA_SCHOOL_NAME not like", value, "doctorGraSchoolName");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolNameIn(List<String> values) {
            addCriterion("DOCTOR_GRA_SCHOOL_NAME in", values, "doctorGraSchoolName");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolNameNotIn(List<String> values) {
            addCriterion("DOCTOR_GRA_SCHOOL_NAME not in", values, "doctorGraSchoolName");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolNameBetween(String value1, String value2) {
            addCriterion("DOCTOR_GRA_SCHOOL_NAME between", value1, value2, "doctorGraSchoolName");
            return (Criteria) this;
        }

        public Criteria andDoctorGraSchoolNameNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_GRA_SCHOOL_NAME not between", value1, value2, "doctorGraSchoolName");
            return (Criteria) this;
        }

        public Criteria andDoctorGraTimeIsNull() {
            addCriterion("DOCTOR_GRA_TIME is null");
            return (Criteria) this;
        }

        public Criteria andDoctorGraTimeIsNotNull() {
            addCriterion("DOCTOR_GRA_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorGraTimeEqualTo(String value) {
            addCriterion("DOCTOR_GRA_TIME =", value, "doctorGraTime");
            return (Criteria) this;
        }

        public Criteria andDoctorGraTimeNotEqualTo(String value) {
            addCriterion("DOCTOR_GRA_TIME <>", value, "doctorGraTime");
            return (Criteria) this;
        }

        public Criteria andDoctorGraTimeGreaterThan(String value) {
            addCriterion("DOCTOR_GRA_TIME >", value, "doctorGraTime");
            return (Criteria) this;
        }

        public Criteria andDoctorGraTimeGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_GRA_TIME >=", value, "doctorGraTime");
            return (Criteria) this;
        }

        public Criteria andDoctorGraTimeLessThan(String value) {
            addCriterion("DOCTOR_GRA_TIME <", value, "doctorGraTime");
            return (Criteria) this;
        }

        public Criteria andDoctorGraTimeLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_GRA_TIME <=", value, "doctorGraTime");
            return (Criteria) this;
        }

        public Criteria andDoctorGraTimeLike(String value) {
            addCriterion("DOCTOR_GRA_TIME like", value, "doctorGraTime");
            return (Criteria) this;
        }

        public Criteria andDoctorGraTimeNotLike(String value) {
            addCriterion("DOCTOR_GRA_TIME not like", value, "doctorGraTime");
            return (Criteria) this;
        }

        public Criteria andDoctorGraTimeIn(List<String> values) {
            addCriterion("DOCTOR_GRA_TIME in", values, "doctorGraTime");
            return (Criteria) this;
        }

        public Criteria andDoctorGraTimeNotIn(List<String> values) {
            addCriterion("DOCTOR_GRA_TIME not in", values, "doctorGraTime");
            return (Criteria) this;
        }

        public Criteria andDoctorGraTimeBetween(String value1, String value2) {
            addCriterion("DOCTOR_GRA_TIME between", value1, value2, "doctorGraTime");
            return (Criteria) this;
        }

        public Criteria andDoctorGraTimeNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_GRA_TIME not between", value1, value2, "doctorGraTime");
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

        public Criteria andMedicalHeaithOrgIdIsNull() {
            addCriterion("MEDICAL_HEAITH_ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgIdIsNotNull() {
            addCriterion("MEDICAL_HEAITH_ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgIdEqualTo(String value) {
            addCriterion("MEDICAL_HEAITH_ORG_ID =", value, "medicalHeaithOrgId");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgIdNotEqualTo(String value) {
            addCriterion("MEDICAL_HEAITH_ORG_ID <>", value, "medicalHeaithOrgId");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgIdGreaterThan(String value) {
            addCriterion("MEDICAL_HEAITH_ORG_ID >", value, "medicalHeaithOrgId");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("MEDICAL_HEAITH_ORG_ID >=", value, "medicalHeaithOrgId");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgIdLessThan(String value) {
            addCriterion("MEDICAL_HEAITH_ORG_ID <", value, "medicalHeaithOrgId");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgIdLessThanOrEqualTo(String value) {
            addCriterion("MEDICAL_HEAITH_ORG_ID <=", value, "medicalHeaithOrgId");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgIdLike(String value) {
            addCriterion("MEDICAL_HEAITH_ORG_ID like", value, "medicalHeaithOrgId");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgIdNotLike(String value) {
            addCriterion("MEDICAL_HEAITH_ORG_ID not like", value, "medicalHeaithOrgId");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgIdIn(List<String> values) {
            addCriterion("MEDICAL_HEAITH_ORG_ID in", values, "medicalHeaithOrgId");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgIdNotIn(List<String> values) {
            addCriterion("MEDICAL_HEAITH_ORG_ID not in", values, "medicalHeaithOrgId");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgIdBetween(String value1, String value2) {
            addCriterion("MEDICAL_HEAITH_ORG_ID between", value1, value2, "medicalHeaithOrgId");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgIdNotBetween(String value1, String value2) {
            addCriterion("MEDICAL_HEAITH_ORG_ID not between", value1, value2, "medicalHeaithOrgId");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgNameIsNull() {
            addCriterion("MEDICAL_HEAITH_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgNameIsNotNull() {
            addCriterion("MEDICAL_HEAITH_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgNameEqualTo(String value) {
            addCriterion("MEDICAL_HEAITH_ORG_NAME =", value, "medicalHeaithOrgName");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgNameNotEqualTo(String value) {
            addCriterion("MEDICAL_HEAITH_ORG_NAME <>", value, "medicalHeaithOrgName");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgNameGreaterThan(String value) {
            addCriterion("MEDICAL_HEAITH_ORG_NAME >", value, "medicalHeaithOrgName");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("MEDICAL_HEAITH_ORG_NAME >=", value, "medicalHeaithOrgName");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgNameLessThan(String value) {
            addCriterion("MEDICAL_HEAITH_ORG_NAME <", value, "medicalHeaithOrgName");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgNameLessThanOrEqualTo(String value) {
            addCriterion("MEDICAL_HEAITH_ORG_NAME <=", value, "medicalHeaithOrgName");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgNameLike(String value) {
            addCriterion("MEDICAL_HEAITH_ORG_NAME like", value, "medicalHeaithOrgName");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgNameNotLike(String value) {
            addCriterion("MEDICAL_HEAITH_ORG_NAME not like", value, "medicalHeaithOrgName");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgNameIn(List<String> values) {
            addCriterion("MEDICAL_HEAITH_ORG_NAME in", values, "medicalHeaithOrgName");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgNameNotIn(List<String> values) {
            addCriterion("MEDICAL_HEAITH_ORG_NAME not in", values, "medicalHeaithOrgName");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgNameBetween(String value1, String value2) {
            addCriterion("MEDICAL_HEAITH_ORG_NAME between", value1, value2, "medicalHeaithOrgName");
            return (Criteria) this;
        }

        public Criteria andMedicalHeaithOrgNameNotBetween(String value1, String value2) {
            addCriterion("MEDICAL_HEAITH_ORG_NAME not between", value1, value2, "medicalHeaithOrgName");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrIdIsNull() {
            addCriterion("HOSPITAL_ATTR_ID is null");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrIdIsNotNull() {
            addCriterion("HOSPITAL_ATTR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrIdEqualTo(String value) {
            addCriterion("HOSPITAL_ATTR_ID =", value, "hospitalAttrId");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrIdNotEqualTo(String value) {
            addCriterion("HOSPITAL_ATTR_ID <>", value, "hospitalAttrId");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrIdGreaterThan(String value) {
            addCriterion("HOSPITAL_ATTR_ID >", value, "hospitalAttrId");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrIdGreaterThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_ATTR_ID >=", value, "hospitalAttrId");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrIdLessThan(String value) {
            addCriterion("HOSPITAL_ATTR_ID <", value, "hospitalAttrId");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrIdLessThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_ATTR_ID <=", value, "hospitalAttrId");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrIdLike(String value) {
            addCriterion("HOSPITAL_ATTR_ID like", value, "hospitalAttrId");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrIdNotLike(String value) {
            addCriterion("HOSPITAL_ATTR_ID not like", value, "hospitalAttrId");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrIdIn(List<String> values) {
            addCriterion("HOSPITAL_ATTR_ID in", values, "hospitalAttrId");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrIdNotIn(List<String> values) {
            addCriterion("HOSPITAL_ATTR_ID not in", values, "hospitalAttrId");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrIdBetween(String value1, String value2) {
            addCriterion("HOSPITAL_ATTR_ID between", value1, value2, "hospitalAttrId");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrIdNotBetween(String value1, String value2) {
            addCriterion("HOSPITAL_ATTR_ID not between", value1, value2, "hospitalAttrId");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrNameIsNull() {
            addCriterion("HOSPITAL_ATTR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrNameIsNotNull() {
            addCriterion("HOSPITAL_ATTR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrNameEqualTo(String value) {
            addCriterion("HOSPITAL_ATTR_NAME =", value, "hospitalAttrName");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrNameNotEqualTo(String value) {
            addCriterion("HOSPITAL_ATTR_NAME <>", value, "hospitalAttrName");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrNameGreaterThan(String value) {
            addCriterion("HOSPITAL_ATTR_NAME >", value, "hospitalAttrName");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrNameGreaterThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_ATTR_NAME >=", value, "hospitalAttrName");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrNameLessThan(String value) {
            addCriterion("HOSPITAL_ATTR_NAME <", value, "hospitalAttrName");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrNameLessThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_ATTR_NAME <=", value, "hospitalAttrName");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrNameLike(String value) {
            addCriterion("HOSPITAL_ATTR_NAME like", value, "hospitalAttrName");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrNameNotLike(String value) {
            addCriterion("HOSPITAL_ATTR_NAME not like", value, "hospitalAttrName");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrNameIn(List<String> values) {
            addCriterion("HOSPITAL_ATTR_NAME in", values, "hospitalAttrName");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrNameNotIn(List<String> values) {
            addCriterion("HOSPITAL_ATTR_NAME not in", values, "hospitalAttrName");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrNameBetween(String value1, String value2) {
            addCriterion("HOSPITAL_ATTR_NAME between", value1, value2, "hospitalAttrName");
            return (Criteria) this;
        }

        public Criteria andHospitalAttrNameNotBetween(String value1, String value2) {
            addCriterion("HOSPITAL_ATTR_NAME not between", value1, value2, "hospitalAttrName");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryIdIsNull() {
            addCriterion("HOSPITAL_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryIdIsNotNull() {
            addCriterion("HOSPITAL_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryIdEqualTo(String value) {
            addCriterion("HOSPITAL_CATEGORY_ID =", value, "hospitalCategoryId");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryIdNotEqualTo(String value) {
            addCriterion("HOSPITAL_CATEGORY_ID <>", value, "hospitalCategoryId");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryIdGreaterThan(String value) {
            addCriterion("HOSPITAL_CATEGORY_ID >", value, "hospitalCategoryId");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_CATEGORY_ID >=", value, "hospitalCategoryId");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryIdLessThan(String value) {
            addCriterion("HOSPITAL_CATEGORY_ID <", value, "hospitalCategoryId");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_CATEGORY_ID <=", value, "hospitalCategoryId");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryIdLike(String value) {
            addCriterion("HOSPITAL_CATEGORY_ID like", value, "hospitalCategoryId");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryIdNotLike(String value) {
            addCriterion("HOSPITAL_CATEGORY_ID not like", value, "hospitalCategoryId");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryIdIn(List<String> values) {
            addCriterion("HOSPITAL_CATEGORY_ID in", values, "hospitalCategoryId");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryIdNotIn(List<String> values) {
            addCriterion("HOSPITAL_CATEGORY_ID not in", values, "hospitalCategoryId");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryIdBetween(String value1, String value2) {
            addCriterion("HOSPITAL_CATEGORY_ID between", value1, value2, "hospitalCategoryId");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryIdNotBetween(String value1, String value2) {
            addCriterion("HOSPITAL_CATEGORY_ID not between", value1, value2, "hospitalCategoryId");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryNameIsNull() {
            addCriterion("HOSPITAL_CATEGORY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryNameIsNotNull() {
            addCriterion("HOSPITAL_CATEGORY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryNameEqualTo(String value) {
            addCriterion("HOSPITAL_CATEGORY_NAME =", value, "hospitalCategoryName");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryNameNotEqualTo(String value) {
            addCriterion("HOSPITAL_CATEGORY_NAME <>", value, "hospitalCategoryName");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryNameGreaterThan(String value) {
            addCriterion("HOSPITAL_CATEGORY_NAME >", value, "hospitalCategoryName");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_CATEGORY_NAME >=", value, "hospitalCategoryName");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryNameLessThan(String value) {
            addCriterion("HOSPITAL_CATEGORY_NAME <", value, "hospitalCategoryName");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("HOSPITAL_CATEGORY_NAME <=", value, "hospitalCategoryName");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryNameLike(String value) {
            addCriterion("HOSPITAL_CATEGORY_NAME like", value, "hospitalCategoryName");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryNameNotLike(String value) {
            addCriterion("HOSPITAL_CATEGORY_NAME not like", value, "hospitalCategoryName");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryNameIn(List<String> values) {
            addCriterion("HOSPITAL_CATEGORY_NAME in", values, "hospitalCategoryName");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryNameNotIn(List<String> values) {
            addCriterion("HOSPITAL_CATEGORY_NAME not in", values, "hospitalCategoryName");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryNameBetween(String value1, String value2) {
            addCriterion("HOSPITAL_CATEGORY_NAME between", value1, value2, "hospitalCategoryName");
            return (Criteria) this;
        }

        public Criteria andHospitalCategoryNameNotBetween(String value1, String value2) {
            addCriterion("HOSPITAL_CATEGORY_NAME not between", value1, value2, "hospitalCategoryName");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeIdIsNull() {
            addCriterion("BASE_ATTRIBUTE_ID is null");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeIdIsNotNull() {
            addCriterion("BASE_ATTRIBUTE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeIdEqualTo(String value) {
            addCriterion("BASE_ATTRIBUTE_ID =", value, "baseAttributeId");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeIdNotEqualTo(String value) {
            addCriterion("BASE_ATTRIBUTE_ID <>", value, "baseAttributeId");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeIdGreaterThan(String value) {
            addCriterion("BASE_ATTRIBUTE_ID >", value, "baseAttributeId");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeIdGreaterThanOrEqualTo(String value) {
            addCriterion("BASE_ATTRIBUTE_ID >=", value, "baseAttributeId");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeIdLessThan(String value) {
            addCriterion("BASE_ATTRIBUTE_ID <", value, "baseAttributeId");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeIdLessThanOrEqualTo(String value) {
            addCriterion("BASE_ATTRIBUTE_ID <=", value, "baseAttributeId");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeIdLike(String value) {
            addCriterion("BASE_ATTRIBUTE_ID like", value, "baseAttributeId");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeIdNotLike(String value) {
            addCriterion("BASE_ATTRIBUTE_ID not like", value, "baseAttributeId");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeIdIn(List<String> values) {
            addCriterion("BASE_ATTRIBUTE_ID in", values, "baseAttributeId");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeIdNotIn(List<String> values) {
            addCriterion("BASE_ATTRIBUTE_ID not in", values, "baseAttributeId");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeIdBetween(String value1, String value2) {
            addCriterion("BASE_ATTRIBUTE_ID between", value1, value2, "baseAttributeId");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeIdNotBetween(String value1, String value2) {
            addCriterion("BASE_ATTRIBUTE_ID not between", value1, value2, "baseAttributeId");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeNameIsNull() {
            addCriterion("BASE_ATTRIBUTE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeNameIsNotNull() {
            addCriterion("BASE_ATTRIBUTE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeNameEqualTo(String value) {
            addCriterion("BASE_ATTRIBUTE_NAME =", value, "baseAttributeName");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeNameNotEqualTo(String value) {
            addCriterion("BASE_ATTRIBUTE_NAME <>", value, "baseAttributeName");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeNameGreaterThan(String value) {
            addCriterion("BASE_ATTRIBUTE_NAME >", value, "baseAttributeName");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeNameGreaterThanOrEqualTo(String value) {
            addCriterion("BASE_ATTRIBUTE_NAME >=", value, "baseAttributeName");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeNameLessThan(String value) {
            addCriterion("BASE_ATTRIBUTE_NAME <", value, "baseAttributeName");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeNameLessThanOrEqualTo(String value) {
            addCriterion("BASE_ATTRIBUTE_NAME <=", value, "baseAttributeName");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeNameLike(String value) {
            addCriterion("BASE_ATTRIBUTE_NAME like", value, "baseAttributeName");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeNameNotLike(String value) {
            addCriterion("BASE_ATTRIBUTE_NAME not like", value, "baseAttributeName");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeNameIn(List<String> values) {
            addCriterion("BASE_ATTRIBUTE_NAME in", values, "baseAttributeName");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeNameNotIn(List<String> values) {
            addCriterion("BASE_ATTRIBUTE_NAME not in", values, "baseAttributeName");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeNameBetween(String value1, String value2) {
            addCriterion("BASE_ATTRIBUTE_NAME between", value1, value2, "baseAttributeName");
            return (Criteria) this;
        }

        public Criteria andBaseAttributeNameNotBetween(String value1, String value2) {
            addCriterion("BASE_ATTRIBUTE_NAME not between", value1, value2, "baseAttributeName");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgIdIsNull() {
            addCriterion("BASIC_HEALTH_ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgIdIsNotNull() {
            addCriterion("BASIC_HEALTH_ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgIdEqualTo(String value) {
            addCriterion("BASIC_HEALTH_ORG_ID =", value, "basicHealthOrgId");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgIdNotEqualTo(String value) {
            addCriterion("BASIC_HEALTH_ORG_ID <>", value, "basicHealthOrgId");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgIdGreaterThan(String value) {
            addCriterion("BASIC_HEALTH_ORG_ID >", value, "basicHealthOrgId");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("BASIC_HEALTH_ORG_ID >=", value, "basicHealthOrgId");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgIdLessThan(String value) {
            addCriterion("BASIC_HEALTH_ORG_ID <", value, "basicHealthOrgId");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgIdLessThanOrEqualTo(String value) {
            addCriterion("BASIC_HEALTH_ORG_ID <=", value, "basicHealthOrgId");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgIdLike(String value) {
            addCriterion("BASIC_HEALTH_ORG_ID like", value, "basicHealthOrgId");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgIdNotLike(String value) {
            addCriterion("BASIC_HEALTH_ORG_ID not like", value, "basicHealthOrgId");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgIdIn(List<String> values) {
            addCriterion("BASIC_HEALTH_ORG_ID in", values, "basicHealthOrgId");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgIdNotIn(List<String> values) {
            addCriterion("BASIC_HEALTH_ORG_ID not in", values, "basicHealthOrgId");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgIdBetween(String value1, String value2) {
            addCriterion("BASIC_HEALTH_ORG_ID between", value1, value2, "basicHealthOrgId");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgIdNotBetween(String value1, String value2) {
            addCriterion("BASIC_HEALTH_ORG_ID not between", value1, value2, "basicHealthOrgId");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgNameIsNull() {
            addCriterion("BASIC_HEALTH_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgNameIsNotNull() {
            addCriterion("BASIC_HEALTH_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgNameEqualTo(String value) {
            addCriterion("BASIC_HEALTH_ORG_NAME =", value, "basicHealthOrgName");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgNameNotEqualTo(String value) {
            addCriterion("BASIC_HEALTH_ORG_NAME <>", value, "basicHealthOrgName");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgNameGreaterThan(String value) {
            addCriterion("BASIC_HEALTH_ORG_NAME >", value, "basicHealthOrgName");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("BASIC_HEALTH_ORG_NAME >=", value, "basicHealthOrgName");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgNameLessThan(String value) {
            addCriterion("BASIC_HEALTH_ORG_NAME <", value, "basicHealthOrgName");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgNameLessThanOrEqualTo(String value) {
            addCriterion("BASIC_HEALTH_ORG_NAME <=", value, "basicHealthOrgName");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgNameLike(String value) {
            addCriterion("BASIC_HEALTH_ORG_NAME like", value, "basicHealthOrgName");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgNameNotLike(String value) {
            addCriterion("BASIC_HEALTH_ORG_NAME not like", value, "basicHealthOrgName");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgNameIn(List<String> values) {
            addCriterion("BASIC_HEALTH_ORG_NAME in", values, "basicHealthOrgName");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgNameNotIn(List<String> values) {
            addCriterion("BASIC_HEALTH_ORG_NAME not in", values, "basicHealthOrgName");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgNameBetween(String value1, String value2) {
            addCriterion("BASIC_HEALTH_ORG_NAME between", value1, value2, "basicHealthOrgName");
            return (Criteria) this;
        }

        public Criteria andBasicHealthOrgNameNotBetween(String value1, String value2) {
            addCriterion("BASIC_HEALTH_ORG_NAME not between", value1, value2, "basicHealthOrgName");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationIdIsNull() {
            addCriterion("TECHNOLOGY_QUALIFICATION_ID is null");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationIdIsNotNull() {
            addCriterion("TECHNOLOGY_QUALIFICATION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationIdEqualTo(String value) {
            addCriterion("TECHNOLOGY_QUALIFICATION_ID =", value, "technologyQualificationId");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationIdNotEqualTo(String value) {
            addCriterion("TECHNOLOGY_QUALIFICATION_ID <>", value, "technologyQualificationId");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationIdGreaterThan(String value) {
            addCriterion("TECHNOLOGY_QUALIFICATION_ID >", value, "technologyQualificationId");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationIdGreaterThanOrEqualTo(String value) {
            addCriterion("TECHNOLOGY_QUALIFICATION_ID >=", value, "technologyQualificationId");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationIdLessThan(String value) {
            addCriterion("TECHNOLOGY_QUALIFICATION_ID <", value, "technologyQualificationId");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationIdLessThanOrEqualTo(String value) {
            addCriterion("TECHNOLOGY_QUALIFICATION_ID <=", value, "technologyQualificationId");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationIdLike(String value) {
            addCriterion("TECHNOLOGY_QUALIFICATION_ID like", value, "technologyQualificationId");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationIdNotLike(String value) {
            addCriterion("TECHNOLOGY_QUALIFICATION_ID not like", value, "technologyQualificationId");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationIdIn(List<String> values) {
            addCriterion("TECHNOLOGY_QUALIFICATION_ID in", values, "technologyQualificationId");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationIdNotIn(List<String> values) {
            addCriterion("TECHNOLOGY_QUALIFICATION_ID not in", values, "technologyQualificationId");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationIdBetween(String value1, String value2) {
            addCriterion("TECHNOLOGY_QUALIFICATION_ID between", value1, value2, "technologyQualificationId");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationIdNotBetween(String value1, String value2) {
            addCriterion("TECHNOLOGY_QUALIFICATION_ID not between", value1, value2, "technologyQualificationId");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationNameIsNull() {
            addCriterion("TECHNOLOGY_QUALIFICATION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationNameIsNotNull() {
            addCriterion("TECHNOLOGY_QUALIFICATION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationNameEqualTo(String value) {
            addCriterion("TECHNOLOGY_QUALIFICATION_NAME =", value, "technologyQualificationName");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationNameNotEqualTo(String value) {
            addCriterion("TECHNOLOGY_QUALIFICATION_NAME <>", value, "technologyQualificationName");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationNameGreaterThan(String value) {
            addCriterion("TECHNOLOGY_QUALIFICATION_NAME >", value, "technologyQualificationName");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationNameGreaterThanOrEqualTo(String value) {
            addCriterion("TECHNOLOGY_QUALIFICATION_NAME >=", value, "technologyQualificationName");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationNameLessThan(String value) {
            addCriterion("TECHNOLOGY_QUALIFICATION_NAME <", value, "technologyQualificationName");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationNameLessThanOrEqualTo(String value) {
            addCriterion("TECHNOLOGY_QUALIFICATION_NAME <=", value, "technologyQualificationName");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationNameLike(String value) {
            addCriterion("TECHNOLOGY_QUALIFICATION_NAME like", value, "technologyQualificationName");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationNameNotLike(String value) {
            addCriterion("TECHNOLOGY_QUALIFICATION_NAME not like", value, "technologyQualificationName");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationNameIn(List<String> values) {
            addCriterion("TECHNOLOGY_QUALIFICATION_NAME in", values, "technologyQualificationName");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationNameNotIn(List<String> values) {
            addCriterion("TECHNOLOGY_QUALIFICATION_NAME not in", values, "technologyQualificationName");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationNameBetween(String value1, String value2) {
            addCriterion("TECHNOLOGY_QUALIFICATION_NAME between", value1, value2, "technologyQualificationName");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationNameNotBetween(String value1, String value2) {
            addCriterion("TECHNOLOGY_QUALIFICATION_NAME not between", value1, value2, "technologyQualificationName");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationDateIsNull() {
            addCriterion("TECHNOLOGY_QUALIFICATION_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationDateIsNotNull() {
            addCriterion("TECHNOLOGY_QUALIFICATION_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationDateEqualTo(String value) {
            addCriterion("TECHNOLOGY_QUALIFICATION_DATE =", value, "technologyQualificationDate");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationDateNotEqualTo(String value) {
            addCriterion("TECHNOLOGY_QUALIFICATION_DATE <>", value, "technologyQualificationDate");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationDateGreaterThan(String value) {
            addCriterion("TECHNOLOGY_QUALIFICATION_DATE >", value, "technologyQualificationDate");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationDateGreaterThanOrEqualTo(String value) {
            addCriterion("TECHNOLOGY_QUALIFICATION_DATE >=", value, "technologyQualificationDate");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationDateLessThan(String value) {
            addCriterion("TECHNOLOGY_QUALIFICATION_DATE <", value, "technologyQualificationDate");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationDateLessThanOrEqualTo(String value) {
            addCriterion("TECHNOLOGY_QUALIFICATION_DATE <=", value, "technologyQualificationDate");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationDateLike(String value) {
            addCriterion("TECHNOLOGY_QUALIFICATION_DATE like", value, "technologyQualificationDate");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationDateNotLike(String value) {
            addCriterion("TECHNOLOGY_QUALIFICATION_DATE not like", value, "technologyQualificationDate");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationDateIn(List<String> values) {
            addCriterion("TECHNOLOGY_QUALIFICATION_DATE in", values, "technologyQualificationDate");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationDateNotIn(List<String> values) {
            addCriterion("TECHNOLOGY_QUALIFICATION_DATE not in", values, "technologyQualificationDate");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationDateBetween(String value1, String value2) {
            addCriterion("TECHNOLOGY_QUALIFICATION_DATE between", value1, value2, "technologyQualificationDate");
            return (Criteria) this;
        }

        public Criteria andTechnologyQualificationDateNotBetween(String value1, String value2) {
            addCriterion("TECHNOLOGY_QUALIFICATION_DATE not between", value1, value2, "technologyQualificationDate");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialIdIsNull() {
            addCriterion("QUALIFICATION_MATERIAL_ID is null");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialIdIsNotNull() {
            addCriterion("QUALIFICATION_MATERIAL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialIdEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_ID =", value, "qualificationMaterialId");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialIdNotEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_ID <>", value, "qualificationMaterialId");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialIdGreaterThan(String value) {
            addCriterion("QUALIFICATION_MATERIAL_ID >", value, "qualificationMaterialId");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialIdGreaterThanOrEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_ID >=", value, "qualificationMaterialId");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialIdLessThan(String value) {
            addCriterion("QUALIFICATION_MATERIAL_ID <", value, "qualificationMaterialId");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialIdLessThanOrEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_ID <=", value, "qualificationMaterialId");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialIdLike(String value) {
            addCriterion("QUALIFICATION_MATERIAL_ID like", value, "qualificationMaterialId");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialIdNotLike(String value) {
            addCriterion("QUALIFICATION_MATERIAL_ID not like", value, "qualificationMaterialId");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialIdIn(List<String> values) {
            addCriterion("QUALIFICATION_MATERIAL_ID in", values, "qualificationMaterialId");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialIdNotIn(List<String> values) {
            addCriterion("QUALIFICATION_MATERIAL_ID not in", values, "qualificationMaterialId");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialIdBetween(String value1, String value2) {
            addCriterion("QUALIFICATION_MATERIAL_ID between", value1, value2, "qualificationMaterialId");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialIdNotBetween(String value1, String value2) {
            addCriterion("QUALIFICATION_MATERIAL_ID not between", value1, value2, "qualificationMaterialId");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialNameIsNull() {
            addCriterion("QUALIFICATION_MATERIAL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialNameIsNotNull() {
            addCriterion("QUALIFICATION_MATERIAL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialNameEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_NAME =", value, "qualificationMaterialName");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialNameNotEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_NAME <>", value, "qualificationMaterialName");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialNameGreaterThan(String value) {
            addCriterion("QUALIFICATION_MATERIAL_NAME >", value, "qualificationMaterialName");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialNameGreaterThanOrEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_NAME >=", value, "qualificationMaterialName");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialNameLessThan(String value) {
            addCriterion("QUALIFICATION_MATERIAL_NAME <", value, "qualificationMaterialName");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialNameLessThanOrEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_NAME <=", value, "qualificationMaterialName");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialNameLike(String value) {
            addCriterion("QUALIFICATION_MATERIAL_NAME like", value, "qualificationMaterialName");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialNameNotLike(String value) {
            addCriterion("QUALIFICATION_MATERIAL_NAME not like", value, "qualificationMaterialName");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialNameIn(List<String> values) {
            addCriterion("QUALIFICATION_MATERIAL_NAME in", values, "qualificationMaterialName");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialNameNotIn(List<String> values) {
            addCriterion("QUALIFICATION_MATERIAL_NAME not in", values, "qualificationMaterialName");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialNameBetween(String value1, String value2) {
            addCriterion("QUALIFICATION_MATERIAL_NAME between", value1, value2, "qualificationMaterialName");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialNameNotBetween(String value1, String value2) {
            addCriterion("QUALIFICATION_MATERIAL_NAME not between", value1, value2, "qualificationMaterialName");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeIsNull() {
            addCriterion("QUALIFICATION_MATERIAL_CODE is null");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeIsNotNull() {
            addCriterion("QUALIFICATION_MATERIAL_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_CODE =", value, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeNotEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_CODE <>", value, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeGreaterThan(String value) {
            addCriterion("QUALIFICATION_MATERIAL_CODE >", value, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeGreaterThanOrEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_CODE >=", value, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeLessThan(String value) {
            addCriterion("QUALIFICATION_MATERIAL_CODE <", value, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeLessThanOrEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_CODE <=", value, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeLike(String value) {
            addCriterion("QUALIFICATION_MATERIAL_CODE like", value, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeNotLike(String value) {
            addCriterion("QUALIFICATION_MATERIAL_CODE not like", value, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeIn(List<String> values) {
            addCriterion("QUALIFICATION_MATERIAL_CODE in", values, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeNotIn(List<String> values) {
            addCriterion("QUALIFICATION_MATERIAL_CODE not in", values, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeBetween(String value1, String value2) {
            addCriterion("QUALIFICATION_MATERIAL_CODE between", value1, value2, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialCodeNotBetween(String value1, String value2) {
            addCriterion("QUALIFICATION_MATERIAL_CODE not between", value1, value2, "qualificationMaterialCode");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriIsNull() {
            addCriterion("QUALIFICATION_MATERIAL_URI is null");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriIsNotNull() {
            addCriterion("QUALIFICATION_MATERIAL_URI is not null");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URI =", value, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriNotEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URI <>", value, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriGreaterThan(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URI >", value, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriGreaterThanOrEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URI >=", value, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriLessThan(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URI <", value, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriLessThanOrEqualTo(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URI <=", value, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriLike(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URI like", value, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriNotLike(String value) {
            addCriterion("QUALIFICATION_MATERIAL_URI not like", value, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriIn(List<String> values) {
            addCriterion("QUALIFICATION_MATERIAL_URI in", values, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriNotIn(List<String> values) {
            addCriterion("QUALIFICATION_MATERIAL_URI not in", values, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriBetween(String value1, String value2) {
            addCriterion("QUALIFICATION_MATERIAL_URI between", value1, value2, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andQualificationMaterialUriNotBetween(String value1, String value2) {
            addCriterion("QUALIFICATION_MATERIAL_URI not between", value1, value2, "qualificationMaterialUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriIsNull() {
            addCriterion("SPECIAL_CERTIFICATION_URI is null");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriIsNotNull() {
            addCriterion("SPECIAL_CERTIFICATION_URI is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriEqualTo(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URI =", value, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriNotEqualTo(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URI <>", value, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriGreaterThan(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URI >", value, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriGreaterThanOrEqualTo(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URI >=", value, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriLessThan(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URI <", value, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriLessThanOrEqualTo(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URI <=", value, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriLike(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URI like", value, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriNotLike(String value) {
            addCriterion("SPECIAL_CERTIFICATION_URI not like", value, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriIn(List<String> values) {
            addCriterion("SPECIAL_CERTIFICATION_URI in", values, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriNotIn(List<String> values) {
            addCriterion("SPECIAL_CERTIFICATION_URI not in", values, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriBetween(String value1, String value2) {
            addCriterion("SPECIAL_CERTIFICATION_URI between", value1, value2, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andSpecialCertificationUriNotBetween(String value1, String value2) {
            addCriterion("SPECIAL_CERTIFICATION_URI not between", value1, value2, "specialCertificationUri");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryIdIsNull() {
            addCriterion("PRACTICING_CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryIdIsNotNull() {
            addCriterion("PRACTICING_CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryIdEqualTo(String value) {
            addCriterion("PRACTICING_CATEGORY_ID =", value, "practicingCategoryId");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryIdNotEqualTo(String value) {
            addCriterion("PRACTICING_CATEGORY_ID <>", value, "practicingCategoryId");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryIdGreaterThan(String value) {
            addCriterion("PRACTICING_CATEGORY_ID >", value, "practicingCategoryId");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("PRACTICING_CATEGORY_ID >=", value, "practicingCategoryId");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryIdLessThan(String value) {
            addCriterion("PRACTICING_CATEGORY_ID <", value, "practicingCategoryId");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("PRACTICING_CATEGORY_ID <=", value, "practicingCategoryId");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryIdLike(String value) {
            addCriterion("PRACTICING_CATEGORY_ID like", value, "practicingCategoryId");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryIdNotLike(String value) {
            addCriterion("PRACTICING_CATEGORY_ID not like", value, "practicingCategoryId");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryIdIn(List<String> values) {
            addCriterion("PRACTICING_CATEGORY_ID in", values, "practicingCategoryId");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryIdNotIn(List<String> values) {
            addCriterion("PRACTICING_CATEGORY_ID not in", values, "practicingCategoryId");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryIdBetween(String value1, String value2) {
            addCriterion("PRACTICING_CATEGORY_ID between", value1, value2, "practicingCategoryId");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryIdNotBetween(String value1, String value2) {
            addCriterion("PRACTICING_CATEGORY_ID not between", value1, value2, "practicingCategoryId");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryNameIsNull() {
            addCriterion("PRACTICING_CATEGORY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryNameIsNotNull() {
            addCriterion("PRACTICING_CATEGORY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryNameEqualTo(String value) {
            addCriterion("PRACTICING_CATEGORY_NAME =", value, "practicingCategoryName");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryNameNotEqualTo(String value) {
            addCriterion("PRACTICING_CATEGORY_NAME <>", value, "practicingCategoryName");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryNameGreaterThan(String value) {
            addCriterion("PRACTICING_CATEGORY_NAME >", value, "practicingCategoryName");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("PRACTICING_CATEGORY_NAME >=", value, "practicingCategoryName");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryNameLessThan(String value) {
            addCriterion("PRACTICING_CATEGORY_NAME <", value, "practicingCategoryName");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryNameLessThanOrEqualTo(String value) {
            addCriterion("PRACTICING_CATEGORY_NAME <=", value, "practicingCategoryName");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryNameLike(String value) {
            addCriterion("PRACTICING_CATEGORY_NAME like", value, "practicingCategoryName");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryNameNotLike(String value) {
            addCriterion("PRACTICING_CATEGORY_NAME not like", value, "practicingCategoryName");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryNameIn(List<String> values) {
            addCriterion("PRACTICING_CATEGORY_NAME in", values, "practicingCategoryName");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryNameNotIn(List<String> values) {
            addCriterion("PRACTICING_CATEGORY_NAME not in", values, "practicingCategoryName");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryNameBetween(String value1, String value2) {
            addCriterion("PRACTICING_CATEGORY_NAME between", value1, value2, "practicingCategoryName");
            return (Criteria) this;
        }

        public Criteria andPracticingCategoryNameNotBetween(String value1, String value2) {
            addCriterion("PRACTICING_CATEGORY_NAME not between", value1, value2, "practicingCategoryName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdIsNull() {
            addCriterion("PRACTICING_SCOPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdIsNotNull() {
            addCriterion("PRACTICING_SCOPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE_ID =", value, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdNotEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE_ID <>", value, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdGreaterThan(String value) {
            addCriterion("PRACTICING_SCOPE_ID >", value, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdGreaterThanOrEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE_ID >=", value, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdLessThan(String value) {
            addCriterion("PRACTICING_SCOPE_ID <", value, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdLessThanOrEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE_ID <=", value, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdLike(String value) {
            addCriterion("PRACTICING_SCOPE_ID like", value, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdNotLike(String value) {
            addCriterion("PRACTICING_SCOPE_ID not like", value, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdIn(List<String> values) {
            addCriterion("PRACTICING_SCOPE_ID in", values, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdNotIn(List<String> values) {
            addCriterion("PRACTICING_SCOPE_ID not in", values, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdBetween(String value1, String value2) {
            addCriterion("PRACTICING_SCOPE_ID between", value1, value2, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIdNotBetween(String value1, String value2) {
            addCriterion("PRACTICING_SCOPE_ID not between", value1, value2, "practicingScopeId");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameIsNull() {
            addCriterion("PRACTICING_SCOPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameIsNotNull() {
            addCriterion("PRACTICING_SCOPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE_NAME =", value, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameNotEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE_NAME <>", value, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameGreaterThan(String value) {
            addCriterion("PRACTICING_SCOPE_NAME >", value, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameGreaterThanOrEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE_NAME >=", value, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameLessThan(String value) {
            addCriterion("PRACTICING_SCOPE_NAME <", value, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameLessThanOrEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE_NAME <=", value, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameLike(String value) {
            addCriterion("PRACTICING_SCOPE_NAME like", value, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameNotLike(String value) {
            addCriterion("PRACTICING_SCOPE_NAME not like", value, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameIn(List<String> values) {
            addCriterion("PRACTICING_SCOPE_NAME in", values, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameNotIn(List<String> values) {
            addCriterion("PRACTICING_SCOPE_NAME not in", values, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameBetween(String value1, String value2) {
            addCriterion("PRACTICING_SCOPE_NAME between", value1, value2, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNameNotBetween(String value1, String value2) {
            addCriterion("PRACTICING_SCOPE_NAME not between", value1, value2, "practicingScopeName");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIsNull() {
            addCriterion("PRACTICING_SCOPE is null");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIsNotNull() {
            addCriterion("PRACTICING_SCOPE is not null");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE =", value, "practicingScope");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNotEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE <>", value, "practicingScope");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeGreaterThan(String value) {
            addCriterion("PRACTICING_SCOPE >", value, "practicingScope");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeGreaterThanOrEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE >=", value, "practicingScope");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeLessThan(String value) {
            addCriterion("PRACTICING_SCOPE <", value, "practicingScope");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeLessThanOrEqualTo(String value) {
            addCriterion("PRACTICING_SCOPE <=", value, "practicingScope");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeLike(String value) {
            addCriterion("PRACTICING_SCOPE like", value, "practicingScope");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNotLike(String value) {
            addCriterion("PRACTICING_SCOPE not like", value, "practicingScope");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeIn(List<String> values) {
            addCriterion("PRACTICING_SCOPE in", values, "practicingScope");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNotIn(List<String> values) {
            addCriterion("PRACTICING_SCOPE not in", values, "practicingScope");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeBetween(String value1, String value2) {
            addCriterion("PRACTICING_SCOPE between", value1, value2, "practicingScope");
            return (Criteria) this;
        }

        public Criteria andPracticingScopeNotBetween(String value1, String value2) {
            addCriterion("PRACTICING_SCOPE not between", value1, value2, "practicingScope");
            return (Criteria) this;
        }

        public Criteria andIsGeneralOrderOrientationIsNull() {
            addCriterion("IS_GENERAL_ORDER_ORIENTATION is null");
            return (Criteria) this;
        }

        public Criteria andIsGeneralOrderOrientationIsNotNull() {
            addCriterion("IS_GENERAL_ORDER_ORIENTATION is not null");
            return (Criteria) this;
        }

        public Criteria andIsGeneralOrderOrientationEqualTo(String value) {
            addCriterion("IS_GENERAL_ORDER_ORIENTATION =", value, "isGeneralOrderOrientation");
            return (Criteria) this;
        }

        public Criteria andIsGeneralOrderOrientationNotEqualTo(String value) {
            addCriterion("IS_GENERAL_ORDER_ORIENTATION <>", value, "isGeneralOrderOrientation");
            return (Criteria) this;
        }

        public Criteria andIsGeneralOrderOrientationGreaterThan(String value) {
            addCriterion("IS_GENERAL_ORDER_ORIENTATION >", value, "isGeneralOrderOrientation");
            return (Criteria) this;
        }

        public Criteria andIsGeneralOrderOrientationGreaterThanOrEqualTo(String value) {
            addCriterion("IS_GENERAL_ORDER_ORIENTATION >=", value, "isGeneralOrderOrientation");
            return (Criteria) this;
        }

        public Criteria andIsGeneralOrderOrientationLessThan(String value) {
            addCriterion("IS_GENERAL_ORDER_ORIENTATION <", value, "isGeneralOrderOrientation");
            return (Criteria) this;
        }

        public Criteria andIsGeneralOrderOrientationLessThanOrEqualTo(String value) {
            addCriterion("IS_GENERAL_ORDER_ORIENTATION <=", value, "isGeneralOrderOrientation");
            return (Criteria) this;
        }

        public Criteria andIsGeneralOrderOrientationLike(String value) {
            addCriterion("IS_GENERAL_ORDER_ORIENTATION like", value, "isGeneralOrderOrientation");
            return (Criteria) this;
        }

        public Criteria andIsGeneralOrderOrientationNotLike(String value) {
            addCriterion("IS_GENERAL_ORDER_ORIENTATION not like", value, "isGeneralOrderOrientation");
            return (Criteria) this;
        }

        public Criteria andIsGeneralOrderOrientationIn(List<String> values) {
            addCriterion("IS_GENERAL_ORDER_ORIENTATION in", values, "isGeneralOrderOrientation");
            return (Criteria) this;
        }

        public Criteria andIsGeneralOrderOrientationNotIn(List<String> values) {
            addCriterion("IS_GENERAL_ORDER_ORIENTATION not in", values, "isGeneralOrderOrientation");
            return (Criteria) this;
        }

        public Criteria andIsGeneralOrderOrientationBetween(String value1, String value2) {
            addCriterion("IS_GENERAL_ORDER_ORIENTATION between", value1, value2, "isGeneralOrderOrientation");
            return (Criteria) this;
        }

        public Criteria andIsGeneralOrderOrientationNotBetween(String value1, String value2) {
            addCriterion("IS_GENERAL_ORDER_ORIENTATION not between", value1, value2, "isGeneralOrderOrientation");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaIsNull() {
            addCriterion("REGISTE_MANUA is null");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaIsNotNull() {
            addCriterion("REGISTE_MANUA is not null");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaEqualTo(String value) {
            addCriterion("REGISTE_MANUA =", value, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaNotEqualTo(String value) {
            addCriterion("REGISTE_MANUA <>", value, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaGreaterThan(String value) {
            addCriterion("REGISTE_MANUA >", value, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaGreaterThanOrEqualTo(String value) {
            addCriterion("REGISTE_MANUA >=", value, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaLessThan(String value) {
            addCriterion("REGISTE_MANUA <", value, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaLessThanOrEqualTo(String value) {
            addCriterion("REGISTE_MANUA <=", value, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaLike(String value) {
            addCriterion("REGISTE_MANUA like", value, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaNotLike(String value) {
            addCriterion("REGISTE_MANUA not like", value, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaIn(List<String> values) {
            addCriterion("REGISTE_MANUA in", values, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaNotIn(List<String> values) {
            addCriterion("REGISTE_MANUA not in", values, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaBetween(String value1, String value2) {
            addCriterion("REGISTE_MANUA between", value1, value2, "registeManua");
            return (Criteria) this;
        }

        public Criteria andRegisteManuaNotBetween(String value1, String value2) {
            addCriterion("REGISTE_MANUA not between", value1, value2, "registeManua");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowIsNull() {
            addCriterion("ARCHIVE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowIsNotNull() {
            addCriterion("ARCHIVE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowEqualTo(String value) {
            addCriterion("ARCHIVE_FLOW =", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowNotEqualTo(String value) {
            addCriterion("ARCHIVE_FLOW <>", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowGreaterThan(String value) {
            addCriterion("ARCHIVE_FLOW >", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ARCHIVE_FLOW >=", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowLessThan(String value) {
            addCriterion("ARCHIVE_FLOW <", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowLessThanOrEqualTo(String value) {
            addCriterion("ARCHIVE_FLOW <=", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowLike(String value) {
            addCriterion("ARCHIVE_FLOW like", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowNotLike(String value) {
            addCriterion("ARCHIVE_FLOW not like", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowIn(List<String> values) {
            addCriterion("ARCHIVE_FLOW in", values, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowNotIn(List<String> values) {
            addCriterion("ARCHIVE_FLOW not in", values, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowBetween(String value1, String value2) {
            addCriterion("ARCHIVE_FLOW between", value1, value2, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowNotBetween(String value1, String value2) {
            addCriterion("ARCHIVE_FLOW not between", value1, value2, "archiveFlow");
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