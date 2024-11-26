package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class SysMonthlyDocCycleInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysMonthlyDocCycleInfoExample() {
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

        public Criteria andSmdciFlowIsNull() {
            addCriterion("SMDCI_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSmdciFlowIsNotNull() {
            addCriterion("SMDCI_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSmdciFlowEqualTo(String value) {
            addCriterion("SMDCI_FLOW =", value, "smdciFlow");
            return (Criteria) this;
        }

        public Criteria andSmdciFlowNotEqualTo(String value) {
            addCriterion("SMDCI_FLOW <>", value, "smdciFlow");
            return (Criteria) this;
        }

        public Criteria andSmdciFlowGreaterThan(String value) {
            addCriterion("SMDCI_FLOW >", value, "smdciFlow");
            return (Criteria) this;
        }

        public Criteria andSmdciFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SMDCI_FLOW >=", value, "smdciFlow");
            return (Criteria) this;
        }

        public Criteria andSmdciFlowLessThan(String value) {
            addCriterion("SMDCI_FLOW <", value, "smdciFlow");
            return (Criteria) this;
        }

        public Criteria andSmdciFlowLessThanOrEqualTo(String value) {
            addCriterion("SMDCI_FLOW <=", value, "smdciFlow");
            return (Criteria) this;
        }

        public Criteria andSmdciFlowLike(String value) {
            addCriterion("SMDCI_FLOW like", value, "smdciFlow");
            return (Criteria) this;
        }

        public Criteria andSmdciFlowNotLike(String value) {
            addCriterion("SMDCI_FLOW not like", value, "smdciFlow");
            return (Criteria) this;
        }

        public Criteria andSmdciFlowIn(List<String> values) {
            addCriterion("SMDCI_FLOW in", values, "smdciFlow");
            return (Criteria) this;
        }

        public Criteria andSmdciFlowNotIn(List<String> values) {
            addCriterion("SMDCI_FLOW not in", values, "smdciFlow");
            return (Criteria) this;
        }

        public Criteria andSmdciFlowBetween(String value1, String value2) {
            addCriterion("SMDCI_FLOW between", value1, value2, "smdciFlow");
            return (Criteria) this;
        }

        public Criteria andSmdciFlowNotBetween(String value1, String value2) {
            addCriterion("SMDCI_FLOW not between", value1, value2, "smdciFlow");
            return (Criteria) this;
        }

        public Criteria andDateMonthIsNull() {
            addCriterion("DATE_MONTH is null");
            return (Criteria) this;
        }

        public Criteria andDateMonthIsNotNull() {
            addCriterion("DATE_MONTH is not null");
            return (Criteria) this;
        }

        public Criteria andDateMonthEqualTo(String value) {
            addCriterion("DATE_MONTH =", value, "dateMonth");
            return (Criteria) this;
        }

        public Criteria andDateMonthNotEqualTo(String value) {
            addCriterion("DATE_MONTH <>", value, "dateMonth");
            return (Criteria) this;
        }

        public Criteria andDateMonthGreaterThan(String value) {
            addCriterion("DATE_MONTH >", value, "dateMonth");
            return (Criteria) this;
        }

        public Criteria andDateMonthGreaterThanOrEqualTo(String value) {
            addCriterion("DATE_MONTH >=", value, "dateMonth");
            return (Criteria) this;
        }

        public Criteria andDateMonthLessThan(String value) {
            addCriterion("DATE_MONTH <", value, "dateMonth");
            return (Criteria) this;
        }

        public Criteria andDateMonthLessThanOrEqualTo(String value) {
            addCriterion("DATE_MONTH <=", value, "dateMonth");
            return (Criteria) this;
        }

        public Criteria andDateMonthLike(String value) {
            addCriterion("DATE_MONTH like", value, "dateMonth");
            return (Criteria) this;
        }

        public Criteria andDateMonthNotLike(String value) {
            addCriterion("DATE_MONTH not like", value, "dateMonth");
            return (Criteria) this;
        }

        public Criteria andDateMonthIn(List<String> values) {
            addCriterion("DATE_MONTH in", values, "dateMonth");
            return (Criteria) this;
        }

        public Criteria andDateMonthNotIn(List<String> values) {
            addCriterion("DATE_MONTH not in", values, "dateMonth");
            return (Criteria) this;
        }

        public Criteria andDateMonthBetween(String value1, String value2) {
            addCriterion("DATE_MONTH between", value1, value2, "dateMonth");
            return (Criteria) this;
        }

        public Criteria andDateMonthNotBetween(String value1, String value2) {
            addCriterion("DATE_MONTH not between", value1, value2, "dateMonth");
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

        public Criteria andUserEmailIsNull() {
            addCriterion("USER_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andUserEmailIsNotNull() {
            addCriterion("USER_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andUserEmailEqualTo(String value) {
            addCriterion("USER_EMAIL =", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotEqualTo(String value) {
            addCriterion("USER_EMAIL <>", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailGreaterThan(String value) {
            addCriterion("USER_EMAIL >", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailGreaterThanOrEqualTo(String value) {
            addCriterion("USER_EMAIL >=", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLessThan(String value) {
            addCriterion("USER_EMAIL <", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLessThanOrEqualTo(String value) {
            addCriterion("USER_EMAIL <=", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLike(String value) {
            addCriterion("USER_EMAIL like", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotLike(String value) {
            addCriterion("USER_EMAIL not like", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailIn(List<String> values) {
            addCriterion("USER_EMAIL in", values, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotIn(List<String> values) {
            addCriterion("USER_EMAIL not in", values, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailBetween(String value1, String value2) {
            addCriterion("USER_EMAIL between", value1, value2, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotBetween(String value1, String value2) {
            addCriterion("USER_EMAIL not between", value1, value2, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNull() {
            addCriterion("USER_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNotNull() {
            addCriterion("USER_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneEqualTo(String value) {
            addCriterion("USER_PHONE =", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotEqualTo(String value) {
            addCriterion("USER_PHONE <>", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThan(String value) {
            addCriterion("USER_PHONE >", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("USER_PHONE >=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThan(String value) {
            addCriterion("USER_PHONE <", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThanOrEqualTo(String value) {
            addCriterion("USER_PHONE <=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLike(String value) {
            addCriterion("USER_PHONE like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotLike(String value) {
            addCriterion("USER_PHONE not like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIn(List<String> values) {
            addCriterion("USER_PHONE in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotIn(List<String> values) {
            addCriterion("USER_PHONE not in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneBetween(String value1, String value2) {
            addCriterion("USER_PHONE between", value1, value2, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotBetween(String value1, String value2) {
            addCriterion("USER_PHONE not between", value1, value2, "userPhone");
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

        public Criteria andSecondSpeIdIsNull() {
            addCriterion("SECOND_SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdIsNotNull() {
            addCriterion("SECOND_SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdEqualTo(String value) {
            addCriterion("SECOND_SPE_ID =", value, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdNotEqualTo(String value) {
            addCriterion("SECOND_SPE_ID <>", value, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdGreaterThan(String value) {
            addCriterion("SECOND_SPE_ID >", value, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SECOND_SPE_ID >=", value, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdLessThan(String value) {
            addCriterion("SECOND_SPE_ID <", value, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdLessThanOrEqualTo(String value) {
            addCriterion("SECOND_SPE_ID <=", value, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdLike(String value) {
            addCriterion("SECOND_SPE_ID like", value, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdNotLike(String value) {
            addCriterion("SECOND_SPE_ID not like", value, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdIn(List<String> values) {
            addCriterion("SECOND_SPE_ID in", values, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdNotIn(List<String> values) {
            addCriterion("SECOND_SPE_ID not in", values, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdBetween(String value1, String value2) {
            addCriterion("SECOND_SPE_ID between", value1, value2, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeIdNotBetween(String value1, String value2) {
            addCriterion("SECOND_SPE_ID not between", value1, value2, "secondSpeId");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameIsNull() {
            addCriterion("SECOND_SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameIsNotNull() {
            addCriterion("SECOND_SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameEqualTo(String value) {
            addCriterion("SECOND_SPE_NAME =", value, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameNotEqualTo(String value) {
            addCriterion("SECOND_SPE_NAME <>", value, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameGreaterThan(String value) {
            addCriterion("SECOND_SPE_NAME >", value, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SECOND_SPE_NAME >=", value, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameLessThan(String value) {
            addCriterion("SECOND_SPE_NAME <", value, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameLessThanOrEqualTo(String value) {
            addCriterion("SECOND_SPE_NAME <=", value, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameLike(String value) {
            addCriterion("SECOND_SPE_NAME like", value, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameNotLike(String value) {
            addCriterion("SECOND_SPE_NAME not like", value, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameIn(List<String> values) {
            addCriterion("SECOND_SPE_NAME in", values, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameNotIn(List<String> values) {
            addCriterion("SECOND_SPE_NAME not in", values, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameBetween(String value1, String value2) {
            addCriterion("SECOND_SPE_NAME between", value1, value2, "secondSpeName");
            return (Criteria) this;
        }

        public Criteria andSecondSpeNameNotBetween(String value1, String value2) {
            addCriterion("SECOND_SPE_NAME not between", value1, value2, "secondSpeName");
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

        public Criteria andTrainingOrgFlowIsNull() {
            addCriterion("TRAINING_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgFlowIsNotNull() {
            addCriterion("TRAINING_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgFlowEqualTo(String value) {
            addCriterion("TRAINING_ORG_FLOW =", value, "trainingOrgFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgFlowNotEqualTo(String value) {
            addCriterion("TRAINING_ORG_FLOW <>", value, "trainingOrgFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgFlowGreaterThan(String value) {
            addCriterion("TRAINING_ORG_FLOW >", value, "trainingOrgFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_ORG_FLOW >=", value, "trainingOrgFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgFlowLessThan(String value) {
            addCriterion("TRAINING_ORG_FLOW <", value, "trainingOrgFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_ORG_FLOW <=", value, "trainingOrgFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgFlowLike(String value) {
            addCriterion("TRAINING_ORG_FLOW like", value, "trainingOrgFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgFlowNotLike(String value) {
            addCriterion("TRAINING_ORG_FLOW not like", value, "trainingOrgFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgFlowIn(List<String> values) {
            addCriterion("TRAINING_ORG_FLOW in", values, "trainingOrgFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgFlowNotIn(List<String> values) {
            addCriterion("TRAINING_ORG_FLOW not in", values, "trainingOrgFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgFlowBetween(String value1, String value2) {
            addCriterion("TRAINING_ORG_FLOW between", value1, value2, "trainingOrgFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgFlowNotBetween(String value1, String value2) {
            addCriterion("TRAINING_ORG_FLOW not between", value1, value2, "trainingOrgFlow");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgNameIsNull() {
            addCriterion("TRAINING_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgNameIsNotNull() {
            addCriterion("TRAINING_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgNameEqualTo(String value) {
            addCriterion("TRAINING_ORG_NAME =", value, "trainingOrgName");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgNameNotEqualTo(String value) {
            addCriterion("TRAINING_ORG_NAME <>", value, "trainingOrgName");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgNameGreaterThan(String value) {
            addCriterion("TRAINING_ORG_NAME >", value, "trainingOrgName");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_ORG_NAME >=", value, "trainingOrgName");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgNameLessThan(String value) {
            addCriterion("TRAINING_ORG_NAME <", value, "trainingOrgName");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgNameLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_ORG_NAME <=", value, "trainingOrgName");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgNameLike(String value) {
            addCriterion("TRAINING_ORG_NAME like", value, "trainingOrgName");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgNameNotLike(String value) {
            addCriterion("TRAINING_ORG_NAME not like", value, "trainingOrgName");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgNameIn(List<String> values) {
            addCriterion("TRAINING_ORG_NAME in", values, "trainingOrgName");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgNameNotIn(List<String> values) {
            addCriterion("TRAINING_ORG_NAME not in", values, "trainingOrgName");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgNameBetween(String value1, String value2) {
            addCriterion("TRAINING_ORG_NAME between", value1, value2, "trainingOrgName");
            return (Criteria) this;
        }

        public Criteria andTrainingOrgNameNotBetween(String value1, String value2) {
            addCriterion("TRAINING_ORG_NAME not between", value1, value2, "trainingOrgName");
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

        public Criteria andDeptFlowIsNull() {
            addCriterion("DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIsNotNull() {
            addCriterion("DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDeptFlowEqualTo(String value) {
            addCriterion("DEPT_FLOW =", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotEqualTo(String value) {
            addCriterion("DEPT_FLOW <>", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowGreaterThan(String value) {
            addCriterion("DEPT_FLOW >", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_FLOW >=", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLessThan(String value) {
            addCriterion("DEPT_FLOW <", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("DEPT_FLOW <=", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLike(String value) {
            addCriterion("DEPT_FLOW like", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotLike(String value) {
            addCriterion("DEPT_FLOW not like", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIn(List<String> values) {
            addCriterion("DEPT_FLOW in", values, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotIn(List<String> values) {
            addCriterion("DEPT_FLOW not in", values, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowBetween(String value1, String value2) {
            addCriterion("DEPT_FLOW between", value1, value2, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotBetween(String value1, String value2) {
            addCriterion("DEPT_FLOW not between", value1, value2, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNull() {
            addCriterion("DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNotNull() {
            addCriterion("DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDeptNameEqualTo(String value) {
            addCriterion("DEPT_NAME =", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotEqualTo(String value) {
            addCriterion("DEPT_NAME <>", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThan(String value) {
            addCriterion("DEPT_NAME >", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_NAME >=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThan(String value) {
            addCriterion("DEPT_NAME <", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThanOrEqualTo(String value) {
            addCriterion("DEPT_NAME <=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLike(String value) {
            addCriterion("DEPT_NAME like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotLike(String value) {
            addCriterion("DEPT_NAME not like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameIn(List<String> values) {
            addCriterion("DEPT_NAME in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotIn(List<String> values) {
            addCriterion("DEPT_NAME not in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameBetween(String value1, String value2) {
            addCriterion("DEPT_NAME between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotBetween(String value1, String value2) {
            addCriterion("DEPT_NAME not between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowIsNull() {
            addCriterion("SCH_DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowIsNotNull() {
            addCriterion("SCH_DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowEqualTo(String value) {
            addCriterion("SCH_DEPT_FLOW =", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowNotEqualTo(String value) {
            addCriterion("SCH_DEPT_FLOW <>", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowGreaterThan(String value) {
            addCriterion("SCH_DEPT_FLOW >", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_DEPT_FLOW >=", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowLessThan(String value) {
            addCriterion("SCH_DEPT_FLOW <", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("SCH_DEPT_FLOW <=", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowLike(String value) {
            addCriterion("SCH_DEPT_FLOW like", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowNotLike(String value) {
            addCriterion("SCH_DEPT_FLOW not like", value, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowIn(List<String> values) {
            addCriterion("SCH_DEPT_FLOW in", values, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowNotIn(List<String> values) {
            addCriterion("SCH_DEPT_FLOW not in", values, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowBetween(String value1, String value2) {
            addCriterion("SCH_DEPT_FLOW between", value1, value2, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptFlowNotBetween(String value1, String value2) {
            addCriterion("SCH_DEPT_FLOW not between", value1, value2, "schDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameIsNull() {
            addCriterion("SCH_DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameIsNotNull() {
            addCriterion("SCH_DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameEqualTo(String value) {
            addCriterion("SCH_DEPT_NAME =", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameNotEqualTo(String value) {
            addCriterion("SCH_DEPT_NAME <>", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameGreaterThan(String value) {
            addCriterion("SCH_DEPT_NAME >", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_DEPT_NAME >=", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameLessThan(String value) {
            addCriterion("SCH_DEPT_NAME <", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameLessThanOrEqualTo(String value) {
            addCriterion("SCH_DEPT_NAME <=", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameLike(String value) {
            addCriterion("SCH_DEPT_NAME like", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameNotLike(String value) {
            addCriterion("SCH_DEPT_NAME not like", value, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameIn(List<String> values) {
            addCriterion("SCH_DEPT_NAME in", values, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameNotIn(List<String> values) {
            addCriterion("SCH_DEPT_NAME not in", values, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameBetween(String value1, String value2) {
            addCriterion("SCH_DEPT_NAME between", value1, value2, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchDeptNameNotBetween(String value1, String value2) {
            addCriterion("SCH_DEPT_NAME not between", value1, value2, "schDeptName");
            return (Criteria) this;
        }

        public Criteria andSchStartDateIsNull() {
            addCriterion("SCH_START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSchStartDateIsNotNull() {
            addCriterion("SCH_START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSchStartDateEqualTo(String value) {
            addCriterion("SCH_START_DATE =", value, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateNotEqualTo(String value) {
            addCriterion("SCH_START_DATE <>", value, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateGreaterThan(String value) {
            addCriterion("SCH_START_DATE >", value, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_START_DATE >=", value, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateLessThan(String value) {
            addCriterion("SCH_START_DATE <", value, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateLessThanOrEqualTo(String value) {
            addCriterion("SCH_START_DATE <=", value, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateLike(String value) {
            addCriterion("SCH_START_DATE like", value, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateNotLike(String value) {
            addCriterion("SCH_START_DATE not like", value, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateIn(List<String> values) {
            addCriterion("SCH_START_DATE in", values, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateNotIn(List<String> values) {
            addCriterion("SCH_START_DATE not in", values, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateBetween(String value1, String value2) {
            addCriterion("SCH_START_DATE between", value1, value2, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchStartDateNotBetween(String value1, String value2) {
            addCriterion("SCH_START_DATE not between", value1, value2, "schStartDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateIsNull() {
            addCriterion("SCH_END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSchEndDateIsNotNull() {
            addCriterion("SCH_END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSchEndDateEqualTo(String value) {
            addCriterion("SCH_END_DATE =", value, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateNotEqualTo(String value) {
            addCriterion("SCH_END_DATE <>", value, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateGreaterThan(String value) {
            addCriterion("SCH_END_DATE >", value, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_END_DATE >=", value, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateLessThan(String value) {
            addCriterion("SCH_END_DATE <", value, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateLessThanOrEqualTo(String value) {
            addCriterion("SCH_END_DATE <=", value, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateLike(String value) {
            addCriterion("SCH_END_DATE like", value, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateNotLike(String value) {
            addCriterion("SCH_END_DATE not like", value, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateIn(List<String> values) {
            addCriterion("SCH_END_DATE in", values, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateNotIn(List<String> values) {
            addCriterion("SCH_END_DATE not in", values, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateBetween(String value1, String value2) {
            addCriterion("SCH_END_DATE between", value1, value2, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andSchEndDateNotBetween(String value1, String value2) {
            addCriterion("SCH_END_DATE not between", value1, value2, "schEndDate");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowIsNull() {
            addCriterion("TEACHER_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowIsNotNull() {
            addCriterion("TEACHER_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowEqualTo(String value) {
            addCriterion("TEACHER_USER_FLOW =", value, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowNotEqualTo(String value) {
            addCriterion("TEACHER_USER_FLOW <>", value, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowGreaterThan(String value) {
            addCriterion("TEACHER_USER_FLOW >", value, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_USER_FLOW >=", value, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowLessThan(String value) {
            addCriterion("TEACHER_USER_FLOW <", value, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_USER_FLOW <=", value, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowLike(String value) {
            addCriterion("TEACHER_USER_FLOW like", value, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowNotLike(String value) {
            addCriterion("TEACHER_USER_FLOW not like", value, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowIn(List<String> values) {
            addCriterion("TEACHER_USER_FLOW in", values, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowNotIn(List<String> values) {
            addCriterion("TEACHER_USER_FLOW not in", values, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowBetween(String value1, String value2) {
            addCriterion("TEACHER_USER_FLOW between", value1, value2, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserFlowNotBetween(String value1, String value2) {
            addCriterion("TEACHER_USER_FLOW not between", value1, value2, "teacherUserFlow");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameIsNull() {
            addCriterion("TEACHER_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameIsNotNull() {
            addCriterion("TEACHER_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameEqualTo(String value) {
            addCriterion("TEACHER_USER_NAME =", value, "teacherUserName");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameNotEqualTo(String value) {
            addCriterion("TEACHER_USER_NAME <>", value, "teacherUserName");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameGreaterThan(String value) {
            addCriterion("TEACHER_USER_NAME >", value, "teacherUserName");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_USER_NAME >=", value, "teacherUserName");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameLessThan(String value) {
            addCriterion("TEACHER_USER_NAME <", value, "teacherUserName");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_USER_NAME <=", value, "teacherUserName");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameLike(String value) {
            addCriterion("TEACHER_USER_NAME like", value, "teacherUserName");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameNotLike(String value) {
            addCriterion("TEACHER_USER_NAME not like", value, "teacherUserName");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameIn(List<String> values) {
            addCriterion("TEACHER_USER_NAME in", values, "teacherUserName");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameNotIn(List<String> values) {
            addCriterion("TEACHER_USER_NAME not in", values, "teacherUserName");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameBetween(String value1, String value2) {
            addCriterion("TEACHER_USER_NAME between", value1, value2, "teacherUserName");
            return (Criteria) this;
        }

        public Criteria andTeacherUserNameNotBetween(String value1, String value2) {
            addCriterion("TEACHER_USER_NAME not between", value1, value2, "teacherUserName");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowIsNull() {
            addCriterion("HEAD_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowIsNotNull() {
            addCriterion("HEAD_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowEqualTo(String value) {
            addCriterion("HEAD_USER_FLOW =", value, "headUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowNotEqualTo(String value) {
            addCriterion("HEAD_USER_FLOW <>", value, "headUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowGreaterThan(String value) {
            addCriterion("HEAD_USER_FLOW >", value, "headUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("HEAD_USER_FLOW >=", value, "headUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowLessThan(String value) {
            addCriterion("HEAD_USER_FLOW <", value, "headUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowLessThanOrEqualTo(String value) {
            addCriterion("HEAD_USER_FLOW <=", value, "headUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowLike(String value) {
            addCriterion("HEAD_USER_FLOW like", value, "headUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowNotLike(String value) {
            addCriterion("HEAD_USER_FLOW not like", value, "headUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowIn(List<String> values) {
            addCriterion("HEAD_USER_FLOW in", values, "headUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowNotIn(List<String> values) {
            addCriterion("HEAD_USER_FLOW not in", values, "headUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowBetween(String value1, String value2) {
            addCriterion("HEAD_USER_FLOW between", value1, value2, "headUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadUserFlowNotBetween(String value1, String value2) {
            addCriterion("HEAD_USER_FLOW not between", value1, value2, "headUserFlow");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameIsNull() {
            addCriterion("HEAD_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameIsNotNull() {
            addCriterion("HEAD_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameEqualTo(String value) {
            addCriterion("HEAD_USER_NAME =", value, "headUserName");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameNotEqualTo(String value) {
            addCriterion("HEAD_USER_NAME <>", value, "headUserName");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameGreaterThan(String value) {
            addCriterion("HEAD_USER_NAME >", value, "headUserName");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("HEAD_USER_NAME >=", value, "headUserName");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameLessThan(String value) {
            addCriterion("HEAD_USER_NAME <", value, "headUserName");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameLessThanOrEqualTo(String value) {
            addCriterion("HEAD_USER_NAME <=", value, "headUserName");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameLike(String value) {
            addCriterion("HEAD_USER_NAME like", value, "headUserName");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameNotLike(String value) {
            addCriterion("HEAD_USER_NAME not like", value, "headUserName");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameIn(List<String> values) {
            addCriterion("HEAD_USER_NAME in", values, "headUserName");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameNotIn(List<String> values) {
            addCriterion("HEAD_USER_NAME not in", values, "headUserName");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameBetween(String value1, String value2) {
            addCriterion("HEAD_USER_NAME between", value1, value2, "headUserName");
            return (Criteria) this;
        }

        public Criteria andHeadUserNameNotBetween(String value1, String value2) {
            addCriterion("HEAD_USER_NAME not between", value1, value2, "headUserName");
            return (Criteria) this;
        }

        public Criteria andIsHaveGradeIsNull() {
            addCriterion("IS_HAVE_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andIsHaveGradeIsNotNull() {
            addCriterion("IS_HAVE_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andIsHaveGradeEqualTo(String value) {
            addCriterion("IS_HAVE_GRADE =", value, "isHaveGrade");
            return (Criteria) this;
        }

        public Criteria andIsHaveGradeNotEqualTo(String value) {
            addCriterion("IS_HAVE_GRADE <>", value, "isHaveGrade");
            return (Criteria) this;
        }

        public Criteria andIsHaveGradeGreaterThan(String value) {
            addCriterion("IS_HAVE_GRADE >", value, "isHaveGrade");
            return (Criteria) this;
        }

        public Criteria andIsHaveGradeGreaterThanOrEqualTo(String value) {
            addCriterion("IS_HAVE_GRADE >=", value, "isHaveGrade");
            return (Criteria) this;
        }

        public Criteria andIsHaveGradeLessThan(String value) {
            addCriterion("IS_HAVE_GRADE <", value, "isHaveGrade");
            return (Criteria) this;
        }

        public Criteria andIsHaveGradeLessThanOrEqualTo(String value) {
            addCriterion("IS_HAVE_GRADE <=", value, "isHaveGrade");
            return (Criteria) this;
        }

        public Criteria andIsHaveGradeLike(String value) {
            addCriterion("IS_HAVE_GRADE like", value, "isHaveGrade");
            return (Criteria) this;
        }

        public Criteria andIsHaveGradeNotLike(String value) {
            addCriterion("IS_HAVE_GRADE not like", value, "isHaveGrade");
            return (Criteria) this;
        }

        public Criteria andIsHaveGradeIn(List<String> values) {
            addCriterion("IS_HAVE_GRADE in", values, "isHaveGrade");
            return (Criteria) this;
        }

        public Criteria andIsHaveGradeNotIn(List<String> values) {
            addCriterion("IS_HAVE_GRADE not in", values, "isHaveGrade");
            return (Criteria) this;
        }

        public Criteria andIsHaveGradeBetween(String value1, String value2) {
            addCriterion("IS_HAVE_GRADE between", value1, value2, "isHaveGrade");
            return (Criteria) this;
        }

        public Criteria andIsHaveGradeNotBetween(String value1, String value2) {
            addCriterion("IS_HAVE_GRADE not between", value1, value2, "isHaveGrade");
            return (Criteria) this;
        }

        public Criteria andIsHaveAfterIsNull() {
            addCriterion("IS_HAVE_AFTER is null");
            return (Criteria) this;
        }

        public Criteria andIsHaveAfterIsNotNull() {
            addCriterion("IS_HAVE_AFTER is not null");
            return (Criteria) this;
        }

        public Criteria andIsHaveAfterEqualTo(String value) {
            addCriterion("IS_HAVE_AFTER =", value, "isHaveAfter");
            return (Criteria) this;
        }

        public Criteria andIsHaveAfterNotEqualTo(String value) {
            addCriterion("IS_HAVE_AFTER <>", value, "isHaveAfter");
            return (Criteria) this;
        }

        public Criteria andIsHaveAfterGreaterThan(String value) {
            addCriterion("IS_HAVE_AFTER >", value, "isHaveAfter");
            return (Criteria) this;
        }

        public Criteria andIsHaveAfterGreaterThanOrEqualTo(String value) {
            addCriterion("IS_HAVE_AFTER >=", value, "isHaveAfter");
            return (Criteria) this;
        }

        public Criteria andIsHaveAfterLessThan(String value) {
            addCriterion("IS_HAVE_AFTER <", value, "isHaveAfter");
            return (Criteria) this;
        }

        public Criteria andIsHaveAfterLessThanOrEqualTo(String value) {
            addCriterion("IS_HAVE_AFTER <=", value, "isHaveAfter");
            return (Criteria) this;
        }

        public Criteria andIsHaveAfterLike(String value) {
            addCriterion("IS_HAVE_AFTER like", value, "isHaveAfter");
            return (Criteria) this;
        }

        public Criteria andIsHaveAfterNotLike(String value) {
            addCriterion("IS_HAVE_AFTER not like", value, "isHaveAfter");
            return (Criteria) this;
        }

        public Criteria andIsHaveAfterIn(List<String> values) {
            addCriterion("IS_HAVE_AFTER in", values, "isHaveAfter");
            return (Criteria) this;
        }

        public Criteria andIsHaveAfterNotIn(List<String> values) {
            addCriterion("IS_HAVE_AFTER not in", values, "isHaveAfter");
            return (Criteria) this;
        }

        public Criteria andIsHaveAfterBetween(String value1, String value2) {
            addCriterion("IS_HAVE_AFTER between", value1, value2, "isHaveAfter");
            return (Criteria) this;
        }

        public Criteria andIsHaveAfterNotBetween(String value1, String value2) {
            addCriterion("IS_HAVE_AFTER not between", value1, value2, "isHaveAfter");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreIsNull() {
            addCriterion("TEACHER_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreIsNotNull() {
            addCriterion("TEACHER_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreEqualTo(String value) {
            addCriterion("TEACHER_SCORE =", value, "teacherScore");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreNotEqualTo(String value) {
            addCriterion("TEACHER_SCORE <>", value, "teacherScore");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreGreaterThan(String value) {
            addCriterion("TEACHER_SCORE >", value, "teacherScore");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_SCORE >=", value, "teacherScore");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreLessThan(String value) {
            addCriterion("TEACHER_SCORE <", value, "teacherScore");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_SCORE <=", value, "teacherScore");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreLike(String value) {
            addCriterion("TEACHER_SCORE like", value, "teacherScore");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreNotLike(String value) {
            addCriterion("TEACHER_SCORE not like", value, "teacherScore");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreIn(List<String> values) {
            addCriterion("TEACHER_SCORE in", values, "teacherScore");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreNotIn(List<String> values) {
            addCriterion("TEACHER_SCORE not in", values, "teacherScore");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreBetween(String value1, String value2) {
            addCriterion("TEACHER_SCORE between", value1, value2, "teacherScore");
            return (Criteria) this;
        }

        public Criteria andTeacherScoreNotBetween(String value1, String value2) {
            addCriterion("TEACHER_SCORE not between", value1, value2, "teacherScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreIsNull() {
            addCriterion("THEORY_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreIsNotNull() {
            addCriterion("THEORY_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreEqualTo(String value) {
            addCriterion("THEORY_SCORE =", value, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreNotEqualTo(String value) {
            addCriterion("THEORY_SCORE <>", value, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreGreaterThan(String value) {
            addCriterion("THEORY_SCORE >", value, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreGreaterThanOrEqualTo(String value) {
            addCriterion("THEORY_SCORE >=", value, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreLessThan(String value) {
            addCriterion("THEORY_SCORE <", value, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreLessThanOrEqualTo(String value) {
            addCriterion("THEORY_SCORE <=", value, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreLike(String value) {
            addCriterion("THEORY_SCORE like", value, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreNotLike(String value) {
            addCriterion("THEORY_SCORE not like", value, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreIn(List<String> values) {
            addCriterion("THEORY_SCORE in", values, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreNotIn(List<String> values) {
            addCriterion("THEORY_SCORE not in", values, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreBetween(String value1, String value2) {
            addCriterion("THEORY_SCORE between", value1, value2, "theoryScore");
            return (Criteria) this;
        }

        public Criteria andTheoryScoreNotBetween(String value1, String value2) {
            addCriterion("THEORY_SCORE not between", value1, value2, "theoryScore");
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

        public Criteria andExamNumIsNull() {
            addCriterion("EXAM_NUM is null");
            return (Criteria) this;
        }

        public Criteria andExamNumIsNotNull() {
            addCriterion("EXAM_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andExamNumEqualTo(Integer value) {
            addCriterion("EXAM_NUM =", value, "examNum");
            return (Criteria) this;
        }

        public Criteria andExamNumNotEqualTo(Integer value) {
            addCriterion("EXAM_NUM <>", value, "examNum");
            return (Criteria) this;
        }

        public Criteria andExamNumGreaterThan(Integer value) {
            addCriterion("EXAM_NUM >", value, "examNum");
            return (Criteria) this;
        }

        public Criteria andExamNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("EXAM_NUM >=", value, "examNum");
            return (Criteria) this;
        }

        public Criteria andExamNumLessThan(Integer value) {
            addCriterion("EXAM_NUM <", value, "examNum");
            return (Criteria) this;
        }

        public Criteria andExamNumLessThanOrEqualTo(Integer value) {
            addCriterion("EXAM_NUM <=", value, "examNum");
            return (Criteria) this;
        }

        public Criteria andExamNumIn(List<Integer> values) {
            addCriterion("EXAM_NUM in", values, "examNum");
            return (Criteria) this;
        }

        public Criteria andExamNumNotIn(List<Integer> values) {
            addCriterion("EXAM_NUM not in", values, "examNum");
            return (Criteria) this;
        }

        public Criteria andExamNumBetween(Integer value1, Integer value2) {
            addCriterion("EXAM_NUM between", value1, value2, "examNum");
            return (Criteria) this;
        }

        public Criteria andExamNumNotBetween(Integer value1, Integer value2) {
            addCriterion("EXAM_NUM not between", value1, value2, "examNum");
            return (Criteria) this;
        }

        public Criteria andExamPassIsNull() {
            addCriterion("EXAM_PASS is null");
            return (Criteria) this;
        }

        public Criteria andExamPassIsNotNull() {
            addCriterion("EXAM_PASS is not null");
            return (Criteria) this;
        }

        public Criteria andExamPassEqualTo(Integer value) {
            addCriterion("EXAM_PASS =", value, "examPass");
            return (Criteria) this;
        }

        public Criteria andExamPassNotEqualTo(Integer value) {
            addCriterion("EXAM_PASS <>", value, "examPass");
            return (Criteria) this;
        }

        public Criteria andExamPassGreaterThan(Integer value) {
            addCriterion("EXAM_PASS >", value, "examPass");
            return (Criteria) this;
        }

        public Criteria andExamPassGreaterThanOrEqualTo(Integer value) {
            addCriterion("EXAM_PASS >=", value, "examPass");
            return (Criteria) this;
        }

        public Criteria andExamPassLessThan(Integer value) {
            addCriterion("EXAM_PASS <", value, "examPass");
            return (Criteria) this;
        }

        public Criteria andExamPassLessThanOrEqualTo(Integer value) {
            addCriterion("EXAM_PASS <=", value, "examPass");
            return (Criteria) this;
        }

        public Criteria andExamPassIn(List<Integer> values) {
            addCriterion("EXAM_PASS in", values, "examPass");
            return (Criteria) this;
        }

        public Criteria andExamPassNotIn(List<Integer> values) {
            addCriterion("EXAM_PASS not in", values, "examPass");
            return (Criteria) this;
        }

        public Criteria andExamPassBetween(Integer value1, Integer value2) {
            addCriterion("EXAM_PASS between", value1, value2, "examPass");
            return (Criteria) this;
        }

        public Criteria andExamPassNotBetween(Integer value1, Integer value2) {
            addCriterion("EXAM_PASS not between", value1, value2, "examPass");
            return (Criteria) this;
        }

        public Criteria andActivityNumIsNull() {
            addCriterion("ACTIVITY_NUM is null");
            return (Criteria) this;
        }

        public Criteria andActivityNumIsNotNull() {
            addCriterion("ACTIVITY_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andActivityNumEqualTo(Integer value) {
            addCriterion("ACTIVITY_NUM =", value, "activityNum");
            return (Criteria) this;
        }

        public Criteria andActivityNumNotEqualTo(Integer value) {
            addCriterion("ACTIVITY_NUM <>", value, "activityNum");
            return (Criteria) this;
        }

        public Criteria andActivityNumGreaterThan(Integer value) {
            addCriterion("ACTIVITY_NUM >", value, "activityNum");
            return (Criteria) this;
        }

        public Criteria andActivityNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("ACTIVITY_NUM >=", value, "activityNum");
            return (Criteria) this;
        }

        public Criteria andActivityNumLessThan(Integer value) {
            addCriterion("ACTIVITY_NUM <", value, "activityNum");
            return (Criteria) this;
        }

        public Criteria andActivityNumLessThanOrEqualTo(Integer value) {
            addCriterion("ACTIVITY_NUM <=", value, "activityNum");
            return (Criteria) this;
        }

        public Criteria andActivityNumIn(List<Integer> values) {
            addCriterion("ACTIVITY_NUM in", values, "activityNum");
            return (Criteria) this;
        }

        public Criteria andActivityNumNotIn(List<Integer> values) {
            addCriterion("ACTIVITY_NUM not in", values, "activityNum");
            return (Criteria) this;
        }

        public Criteria andActivityNumBetween(Integer value1, Integer value2) {
            addCriterion("ACTIVITY_NUM between", value1, value2, "activityNum");
            return (Criteria) this;
        }

        public Criteria andActivityNumNotBetween(Integer value1, Integer value2) {
            addCriterion("ACTIVITY_NUM not between", value1, value2, "activityNum");
            return (Criteria) this;
        }

        public Criteria andRecNumIsNull() {
            addCriterion("REC_NUM is null");
            return (Criteria) this;
        }

        public Criteria andRecNumIsNotNull() {
            addCriterion("REC_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andRecNumEqualTo(Integer value) {
            addCriterion("REC_NUM =", value, "recNum");
            return (Criteria) this;
        }

        public Criteria andRecNumNotEqualTo(Integer value) {
            addCriterion("REC_NUM <>", value, "recNum");
            return (Criteria) this;
        }

        public Criteria andRecNumGreaterThan(Integer value) {
            addCriterion("REC_NUM >", value, "recNum");
            return (Criteria) this;
        }

        public Criteria andRecNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("REC_NUM >=", value, "recNum");
            return (Criteria) this;
        }

        public Criteria andRecNumLessThan(Integer value) {
            addCriterion("REC_NUM <", value, "recNum");
            return (Criteria) this;
        }

        public Criteria andRecNumLessThanOrEqualTo(Integer value) {
            addCriterion("REC_NUM <=", value, "recNum");
            return (Criteria) this;
        }

        public Criteria andRecNumIn(List<Integer> values) {
            addCriterion("REC_NUM in", values, "recNum");
            return (Criteria) this;
        }

        public Criteria andRecNumNotIn(List<Integer> values) {
            addCriterion("REC_NUM not in", values, "recNum");
            return (Criteria) this;
        }

        public Criteria andRecNumBetween(Integer value1, Integer value2) {
            addCriterion("REC_NUM between", value1, value2, "recNum");
            return (Criteria) this;
        }

        public Criteria andRecNumNotBetween(Integer value1, Integer value2) {
            addCriterion("REC_NUM not between", value1, value2, "recNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumIsNull() {
            addCriterion("AUDIT_NUM is null");
            return (Criteria) this;
        }

        public Criteria andAuditNumIsNotNull() {
            addCriterion("AUDIT_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andAuditNumEqualTo(Integer value) {
            addCriterion("AUDIT_NUM =", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumNotEqualTo(Integer value) {
            addCriterion("AUDIT_NUM <>", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumGreaterThan(Integer value) {
            addCriterion("AUDIT_NUM >", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("AUDIT_NUM >=", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumLessThan(Integer value) {
            addCriterion("AUDIT_NUM <", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumLessThanOrEqualTo(Integer value) {
            addCriterion("AUDIT_NUM <=", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumIn(List<Integer> values) {
            addCriterion("AUDIT_NUM in", values, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumNotIn(List<Integer> values) {
            addCriterion("AUDIT_NUM not in", values, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumBetween(Integer value1, Integer value2) {
            addCriterion("AUDIT_NUM between", value1, value2, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumNotBetween(Integer value1, Integer value2) {
            addCriterion("AUDIT_NUM not between", value1, value2, "auditNum");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumIsNull() {
            addCriterion("NOT_AUDIT_NUM is null");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumIsNotNull() {
            addCriterion("NOT_AUDIT_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumEqualTo(Integer value) {
            addCriterion("NOT_AUDIT_NUM =", value, "notAuditNum");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumNotEqualTo(Integer value) {
            addCriterion("NOT_AUDIT_NUM <>", value, "notAuditNum");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumGreaterThan(Integer value) {
            addCriterion("NOT_AUDIT_NUM >", value, "notAuditNum");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("NOT_AUDIT_NUM >=", value, "notAuditNum");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumLessThan(Integer value) {
            addCriterion("NOT_AUDIT_NUM <", value, "notAuditNum");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumLessThanOrEqualTo(Integer value) {
            addCriterion("NOT_AUDIT_NUM <=", value, "notAuditNum");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumIn(List<Integer> values) {
            addCriterion("NOT_AUDIT_NUM in", values, "notAuditNum");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumNotIn(List<Integer> values) {
            addCriterion("NOT_AUDIT_NUM not in", values, "notAuditNum");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumBetween(Integer value1, Integer value2) {
            addCriterion("NOT_AUDIT_NUM between", value1, value2, "notAuditNum");
            return (Criteria) this;
        }

        public Criteria andNotAuditNumNotBetween(Integer value1, Integer value2) {
            addCriterion("NOT_AUDIT_NUM not between", value1, value2, "notAuditNum");
            return (Criteria) this;
        }

        public Criteria andLectureIsNull() {
            addCriterion("LECTURE is null");
            return (Criteria) this;
        }

        public Criteria andLectureIsNotNull() {
            addCriterion("LECTURE is not null");
            return (Criteria) this;
        }

        public Criteria andLectureEqualTo(Integer value) {
            addCriterion("LECTURE =", value, "lecture");
            return (Criteria) this;
        }

        public Criteria andLectureNotEqualTo(Integer value) {
            addCriterion("LECTURE <>", value, "lecture");
            return (Criteria) this;
        }

        public Criteria andLectureGreaterThan(Integer value) {
            addCriterion("LECTURE >", value, "lecture");
            return (Criteria) this;
        }

        public Criteria andLectureGreaterThanOrEqualTo(Integer value) {
            addCriterion("LECTURE >=", value, "lecture");
            return (Criteria) this;
        }

        public Criteria andLectureLessThan(Integer value) {
            addCriterion("LECTURE <", value, "lecture");
            return (Criteria) this;
        }

        public Criteria andLectureLessThanOrEqualTo(Integer value) {
            addCriterion("LECTURE <=", value, "lecture");
            return (Criteria) this;
        }

        public Criteria andLectureIn(List<Integer> values) {
            addCriterion("LECTURE in", values, "lecture");
            return (Criteria) this;
        }

        public Criteria andLectureNotIn(List<Integer> values) {
            addCriterion("LECTURE not in", values, "lecture");
            return (Criteria) this;
        }

        public Criteria andLectureBetween(Integer value1, Integer value2) {
            addCriterion("LECTURE between", value1, value2, "lecture");
            return (Criteria) this;
        }

        public Criteria andLectureNotBetween(Integer value1, Integer value2) {
            addCriterion("LECTURE not between", value1, value2, "lecture");
            return (Criteria) this;
        }

        public Criteria andProjectIsNull() {
            addCriterion("PROJECT is null");
            return (Criteria) this;
        }

        public Criteria andProjectIsNotNull() {
            addCriterion("PROJECT is not null");
            return (Criteria) this;
        }

        public Criteria andProjectEqualTo(Integer value) {
            addCriterion("PROJECT =", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectNotEqualTo(Integer value) {
            addCriterion("PROJECT <>", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectGreaterThan(Integer value) {
            addCriterion("PROJECT >", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectGreaterThanOrEqualTo(Integer value) {
            addCriterion("PROJECT >=", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectLessThan(Integer value) {
            addCriterion("PROJECT <", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectLessThanOrEqualTo(Integer value) {
            addCriterion("PROJECT <=", value, "project");
            return (Criteria) this;
        }

        public Criteria andProjectIn(List<Integer> values) {
            addCriterion("PROJECT in", values, "project");
            return (Criteria) this;
        }

        public Criteria andProjectNotIn(List<Integer> values) {
            addCriterion("PROJECT not in", values, "project");
            return (Criteria) this;
        }

        public Criteria andProjectBetween(Integer value1, Integer value2) {
            addCriterion("PROJECT between", value1, value2, "project");
            return (Criteria) this;
        }

        public Criteria andProjectNotBetween(Integer value1, Integer value2) {
            addCriterion("PROJECT not between", value1, value2, "project");
            return (Criteria) this;
        }

        public Criteria andDiseaseRegistryNewIsNull() {
            addCriterion("DISEASE_REGISTRY_NEW is null");
            return (Criteria) this;
        }

        public Criteria andDiseaseRegistryNewIsNotNull() {
            addCriterion("DISEASE_REGISTRY_NEW is not null");
            return (Criteria) this;
        }

        public Criteria andDiseaseRegistryNewEqualTo(Integer value) {
            addCriterion("DISEASE_REGISTRY_NEW =", value, "diseaseRegistryNew");
            return (Criteria) this;
        }

        public Criteria andDiseaseRegistryNewNotEqualTo(Integer value) {
            addCriterion("DISEASE_REGISTRY_NEW <>", value, "diseaseRegistryNew");
            return (Criteria) this;
        }

        public Criteria andDiseaseRegistryNewGreaterThan(Integer value) {
            addCriterion("DISEASE_REGISTRY_NEW >", value, "diseaseRegistryNew");
            return (Criteria) this;
        }

        public Criteria andDiseaseRegistryNewGreaterThanOrEqualTo(Integer value) {
            addCriterion("DISEASE_REGISTRY_NEW >=", value, "diseaseRegistryNew");
            return (Criteria) this;
        }

        public Criteria andDiseaseRegistryNewLessThan(Integer value) {
            addCriterion("DISEASE_REGISTRY_NEW <", value, "diseaseRegistryNew");
            return (Criteria) this;
        }

        public Criteria andDiseaseRegistryNewLessThanOrEqualTo(Integer value) {
            addCriterion("DISEASE_REGISTRY_NEW <=", value, "diseaseRegistryNew");
            return (Criteria) this;
        }

        public Criteria andDiseaseRegistryNewIn(List<Integer> values) {
            addCriterion("DISEASE_REGISTRY_NEW in", values, "diseaseRegistryNew");
            return (Criteria) this;
        }

        public Criteria andDiseaseRegistryNewNotIn(List<Integer> values) {
            addCriterion("DISEASE_REGISTRY_NEW not in", values, "diseaseRegistryNew");
            return (Criteria) this;
        }

        public Criteria andDiseaseRegistryNewBetween(Integer value1, Integer value2) {
            addCriterion("DISEASE_REGISTRY_NEW between", value1, value2, "diseaseRegistryNew");
            return (Criteria) this;
        }

        public Criteria andDiseaseRegistryNewNotBetween(Integer value1, Integer value2) {
            addCriterion("DISEASE_REGISTRY_NEW not between", value1, value2, "diseaseRegistryNew");
            return (Criteria) this;
        }

        public Criteria andSkillOperationRegistryNewIsNull() {
            addCriterion("SKILL_OPERATION_REGISTRY_NEW is null");
            return (Criteria) this;
        }

        public Criteria andSkillOperationRegistryNewIsNotNull() {
            addCriterion("SKILL_OPERATION_REGISTRY_NEW is not null");
            return (Criteria) this;
        }

        public Criteria andSkillOperationRegistryNewEqualTo(Integer value) {
            addCriterion("SKILL_OPERATION_REGISTRY_NEW =", value, "skillOperationRegistryNew");
            return (Criteria) this;
        }

        public Criteria andSkillOperationRegistryNewNotEqualTo(Integer value) {
            addCriterion("SKILL_OPERATION_REGISTRY_NEW <>", value, "skillOperationRegistryNew");
            return (Criteria) this;
        }

        public Criteria andSkillOperationRegistryNewGreaterThan(Integer value) {
            addCriterion("SKILL_OPERATION_REGISTRY_NEW >", value, "skillOperationRegistryNew");
            return (Criteria) this;
        }

        public Criteria andSkillOperationRegistryNewGreaterThanOrEqualTo(Integer value) {
            addCriterion("SKILL_OPERATION_REGISTRY_NEW >=", value, "skillOperationRegistryNew");
            return (Criteria) this;
        }

        public Criteria andSkillOperationRegistryNewLessThan(Integer value) {
            addCriterion("SKILL_OPERATION_REGISTRY_NEW <", value, "skillOperationRegistryNew");
            return (Criteria) this;
        }

        public Criteria andSkillOperationRegistryNewLessThanOrEqualTo(Integer value) {
            addCriterion("SKILL_OPERATION_REGISTRY_NEW <=", value, "skillOperationRegistryNew");
            return (Criteria) this;
        }

        public Criteria andSkillOperationRegistryNewIn(List<Integer> values) {
            addCriterion("SKILL_OPERATION_REGISTRY_NEW in", values, "skillOperationRegistryNew");
            return (Criteria) this;
        }

        public Criteria andSkillOperationRegistryNewNotIn(List<Integer> values) {
            addCriterion("SKILL_OPERATION_REGISTRY_NEW not in", values, "skillOperationRegistryNew");
            return (Criteria) this;
        }

        public Criteria andSkillOperationRegistryNewBetween(Integer value1, Integer value2) {
            addCriterion("SKILL_OPERATION_REGISTRY_NEW between", value1, value2, "skillOperationRegistryNew");
            return (Criteria) this;
        }

        public Criteria andSkillOperationRegistryNewNotBetween(Integer value1, Integer value2) {
            addCriterion("SKILL_OPERATION_REGISTRY_NEW not between", value1, value2, "skillOperationRegistryNew");
            return (Criteria) this;
        }

        public Criteria andTeachRecordRegistryIsNull() {
            addCriterion("TEACH_RECORD_REGISTRY is null");
            return (Criteria) this;
        }

        public Criteria andTeachRecordRegistryIsNotNull() {
            addCriterion("TEACH_RECORD_REGISTRY is not null");
            return (Criteria) this;
        }

        public Criteria andTeachRecordRegistryEqualTo(Integer value) {
            addCriterion("TEACH_RECORD_REGISTRY =", value, "teachRecordRegistry");
            return (Criteria) this;
        }

        public Criteria andTeachRecordRegistryNotEqualTo(Integer value) {
            addCriterion("TEACH_RECORD_REGISTRY <>", value, "teachRecordRegistry");
            return (Criteria) this;
        }

        public Criteria andTeachRecordRegistryGreaterThan(Integer value) {
            addCriterion("TEACH_RECORD_REGISTRY >", value, "teachRecordRegistry");
            return (Criteria) this;
        }

        public Criteria andTeachRecordRegistryGreaterThanOrEqualTo(Integer value) {
            addCriterion("TEACH_RECORD_REGISTRY >=", value, "teachRecordRegistry");
            return (Criteria) this;
        }

        public Criteria andTeachRecordRegistryLessThan(Integer value) {
            addCriterion("TEACH_RECORD_REGISTRY <", value, "teachRecordRegistry");
            return (Criteria) this;
        }

        public Criteria andTeachRecordRegistryLessThanOrEqualTo(Integer value) {
            addCriterion("TEACH_RECORD_REGISTRY <=", value, "teachRecordRegistry");
            return (Criteria) this;
        }

        public Criteria andTeachRecordRegistryIn(List<Integer> values) {
            addCriterion("TEACH_RECORD_REGISTRY in", values, "teachRecordRegistry");
            return (Criteria) this;
        }

        public Criteria andTeachRecordRegistryNotIn(List<Integer> values) {
            addCriterion("TEACH_RECORD_REGISTRY not in", values, "teachRecordRegistry");
            return (Criteria) this;
        }

        public Criteria andTeachRecordRegistryBetween(Integer value1, Integer value2) {
            addCriterion("TEACH_RECORD_REGISTRY between", value1, value2, "teachRecordRegistry");
            return (Criteria) this;
        }

        public Criteria andTeachRecordRegistryNotBetween(Integer value1, Integer value2) {
            addCriterion("TEACH_RECORD_REGISTRY not between", value1, value2, "teachRecordRegistry");
            return (Criteria) this;
        }

        public Criteria andCaseRegistryIsNull() {
            addCriterion("CASE_REGISTRY is null");
            return (Criteria) this;
        }

        public Criteria andCaseRegistryIsNotNull() {
            addCriterion("CASE_REGISTRY is not null");
            return (Criteria) this;
        }

        public Criteria andCaseRegistryEqualTo(Integer value) {
            addCriterion("CASE_REGISTRY =", value, "caseRegistry");
            return (Criteria) this;
        }

        public Criteria andCaseRegistryNotEqualTo(Integer value) {
            addCriterion("CASE_REGISTRY <>", value, "caseRegistry");
            return (Criteria) this;
        }

        public Criteria andCaseRegistryGreaterThan(Integer value) {
            addCriterion("CASE_REGISTRY >", value, "caseRegistry");
            return (Criteria) this;
        }

        public Criteria andCaseRegistryGreaterThanOrEqualTo(Integer value) {
            addCriterion("CASE_REGISTRY >=", value, "caseRegistry");
            return (Criteria) this;
        }

        public Criteria andCaseRegistryLessThan(Integer value) {
            addCriterion("CASE_REGISTRY <", value, "caseRegistry");
            return (Criteria) this;
        }

        public Criteria andCaseRegistryLessThanOrEqualTo(Integer value) {
            addCriterion("CASE_REGISTRY <=", value, "caseRegistry");
            return (Criteria) this;
        }

        public Criteria andCaseRegistryIn(List<Integer> values) {
            addCriterion("CASE_REGISTRY in", values, "caseRegistry");
            return (Criteria) this;
        }

        public Criteria andCaseRegistryNotIn(List<Integer> values) {
            addCriterion("CASE_REGISTRY not in", values, "caseRegistry");
            return (Criteria) this;
        }

        public Criteria andCaseRegistryBetween(Integer value1, Integer value2) {
            addCriterion("CASE_REGISTRY between", value1, value2, "caseRegistry");
            return (Criteria) this;
        }

        public Criteria andCaseRegistryNotBetween(Integer value1, Integer value2) {
            addCriterion("CASE_REGISTRY not between", value1, value2, "caseRegistry");
            return (Criteria) this;
        }

        public Criteria andEmergencyCaseIsNull() {
            addCriterion("EMERGENCY_CASE is null");
            return (Criteria) this;
        }

        public Criteria andEmergencyCaseIsNotNull() {
            addCriterion("EMERGENCY_CASE is not null");
            return (Criteria) this;
        }

        public Criteria andEmergencyCaseEqualTo(Integer value) {
            addCriterion("EMERGENCY_CASE =", value, "emergencyCase");
            return (Criteria) this;
        }

        public Criteria andEmergencyCaseNotEqualTo(Integer value) {
            addCriterion("EMERGENCY_CASE <>", value, "emergencyCase");
            return (Criteria) this;
        }

        public Criteria andEmergencyCaseGreaterThan(Integer value) {
            addCriterion("EMERGENCY_CASE >", value, "emergencyCase");
            return (Criteria) this;
        }

        public Criteria andEmergencyCaseGreaterThanOrEqualTo(Integer value) {
            addCriterion("EMERGENCY_CASE >=", value, "emergencyCase");
            return (Criteria) this;
        }

        public Criteria andEmergencyCaseLessThan(Integer value) {
            addCriterion("EMERGENCY_CASE <", value, "emergencyCase");
            return (Criteria) this;
        }

        public Criteria andEmergencyCaseLessThanOrEqualTo(Integer value) {
            addCriterion("EMERGENCY_CASE <=", value, "emergencyCase");
            return (Criteria) this;
        }

        public Criteria andEmergencyCaseIn(List<Integer> values) {
            addCriterion("EMERGENCY_CASE in", values, "emergencyCase");
            return (Criteria) this;
        }

        public Criteria andEmergencyCaseNotIn(List<Integer> values) {
            addCriterion("EMERGENCY_CASE not in", values, "emergencyCase");
            return (Criteria) this;
        }

        public Criteria andEmergencyCaseBetween(Integer value1, Integer value2) {
            addCriterion("EMERGENCY_CASE between", value1, value2, "emergencyCase");
            return (Criteria) this;
        }

        public Criteria andEmergencyCaseNotBetween(Integer value1, Integer value2) {
            addCriterion("EMERGENCY_CASE not between", value1, value2, "emergencyCase");
            return (Criteria) this;
        }

        public Criteria andDiseaseRegistryIsNull() {
            addCriterion("DISEASE_REGISTRY is null");
            return (Criteria) this;
        }

        public Criteria andDiseaseRegistryIsNotNull() {
            addCriterion("DISEASE_REGISTRY is not null");
            return (Criteria) this;
        }

        public Criteria andDiseaseRegistryEqualTo(Integer value) {
            addCriterion("DISEASE_REGISTRY =", value, "diseaseRegistry");
            return (Criteria) this;
        }

        public Criteria andDiseaseRegistryNotEqualTo(Integer value) {
            addCriterion("DISEASE_REGISTRY <>", value, "diseaseRegistry");
            return (Criteria) this;
        }

        public Criteria andDiseaseRegistryGreaterThan(Integer value) {
            addCriterion("DISEASE_REGISTRY >", value, "diseaseRegistry");
            return (Criteria) this;
        }

        public Criteria andDiseaseRegistryGreaterThanOrEqualTo(Integer value) {
            addCriterion("DISEASE_REGISTRY >=", value, "diseaseRegistry");
            return (Criteria) this;
        }

        public Criteria andDiseaseRegistryLessThan(Integer value) {
            addCriterion("DISEASE_REGISTRY <", value, "diseaseRegistry");
            return (Criteria) this;
        }

        public Criteria andDiseaseRegistryLessThanOrEqualTo(Integer value) {
            addCriterion("DISEASE_REGISTRY <=", value, "diseaseRegistry");
            return (Criteria) this;
        }

        public Criteria andDiseaseRegistryIn(List<Integer> values) {
            addCriterion("DISEASE_REGISTRY in", values, "diseaseRegistry");
            return (Criteria) this;
        }

        public Criteria andDiseaseRegistryNotIn(List<Integer> values) {
            addCriterion("DISEASE_REGISTRY not in", values, "diseaseRegistry");
            return (Criteria) this;
        }

        public Criteria andDiseaseRegistryBetween(Integer value1, Integer value2) {
            addCriterion("DISEASE_REGISTRY between", value1, value2, "diseaseRegistry");
            return (Criteria) this;
        }

        public Criteria andDiseaseRegistryNotBetween(Integer value1, Integer value2) {
            addCriterion("DISEASE_REGISTRY not between", value1, value2, "diseaseRegistry");
            return (Criteria) this;
        }

        public Criteria andSkillRegistryIsNull() {
            addCriterion("SKILL_REGISTRY is null");
            return (Criteria) this;
        }

        public Criteria andSkillRegistryIsNotNull() {
            addCriterion("SKILL_REGISTRY is not null");
            return (Criteria) this;
        }

        public Criteria andSkillRegistryEqualTo(Integer value) {
            addCriterion("SKILL_REGISTRY =", value, "skillRegistry");
            return (Criteria) this;
        }

        public Criteria andSkillRegistryNotEqualTo(Integer value) {
            addCriterion("SKILL_REGISTRY <>", value, "skillRegistry");
            return (Criteria) this;
        }

        public Criteria andSkillRegistryGreaterThan(Integer value) {
            addCriterion("SKILL_REGISTRY >", value, "skillRegistry");
            return (Criteria) this;
        }

        public Criteria andSkillRegistryGreaterThanOrEqualTo(Integer value) {
            addCriterion("SKILL_REGISTRY >=", value, "skillRegistry");
            return (Criteria) this;
        }

        public Criteria andSkillRegistryLessThan(Integer value) {
            addCriterion("SKILL_REGISTRY <", value, "skillRegistry");
            return (Criteria) this;
        }

        public Criteria andSkillRegistryLessThanOrEqualTo(Integer value) {
            addCriterion("SKILL_REGISTRY <=", value, "skillRegistry");
            return (Criteria) this;
        }

        public Criteria andSkillRegistryIn(List<Integer> values) {
            addCriterion("SKILL_REGISTRY in", values, "skillRegistry");
            return (Criteria) this;
        }

        public Criteria andSkillRegistryNotIn(List<Integer> values) {
            addCriterion("SKILL_REGISTRY not in", values, "skillRegistry");
            return (Criteria) this;
        }

        public Criteria andSkillRegistryBetween(Integer value1, Integer value2) {
            addCriterion("SKILL_REGISTRY between", value1, value2, "skillRegistry");
            return (Criteria) this;
        }

        public Criteria andSkillRegistryNotBetween(Integer value1, Integer value2) {
            addCriterion("SKILL_REGISTRY not between", value1, value2, "skillRegistry");
            return (Criteria) this;
        }

        public Criteria andOperationRegistryIsNull() {
            addCriterion("OPERATION_REGISTRY is null");
            return (Criteria) this;
        }

        public Criteria andOperationRegistryIsNotNull() {
            addCriterion("OPERATION_REGISTRY is not null");
            return (Criteria) this;
        }

        public Criteria andOperationRegistryEqualTo(Integer value) {
            addCriterion("OPERATION_REGISTRY =", value, "operationRegistry");
            return (Criteria) this;
        }

        public Criteria andOperationRegistryNotEqualTo(Integer value) {
            addCriterion("OPERATION_REGISTRY <>", value, "operationRegistry");
            return (Criteria) this;
        }

        public Criteria andOperationRegistryGreaterThan(Integer value) {
            addCriterion("OPERATION_REGISTRY >", value, "operationRegistry");
            return (Criteria) this;
        }

        public Criteria andOperationRegistryGreaterThanOrEqualTo(Integer value) {
            addCriterion("OPERATION_REGISTRY >=", value, "operationRegistry");
            return (Criteria) this;
        }

        public Criteria andOperationRegistryLessThan(Integer value) {
            addCriterion("OPERATION_REGISTRY <", value, "operationRegistry");
            return (Criteria) this;
        }

        public Criteria andOperationRegistryLessThanOrEqualTo(Integer value) {
            addCriterion("OPERATION_REGISTRY <=", value, "operationRegistry");
            return (Criteria) this;
        }

        public Criteria andOperationRegistryIn(List<Integer> values) {
            addCriterion("OPERATION_REGISTRY in", values, "operationRegistry");
            return (Criteria) this;
        }

        public Criteria andOperationRegistryNotIn(List<Integer> values) {
            addCriterion("OPERATION_REGISTRY not in", values, "operationRegistry");
            return (Criteria) this;
        }

        public Criteria andOperationRegistryBetween(Integer value1, Integer value2) {
            addCriterion("OPERATION_REGISTRY between", value1, value2, "operationRegistry");
            return (Criteria) this;
        }

        public Criteria andOperationRegistryNotBetween(Integer value1, Integer value2) {
            addCriterion("OPERATION_REGISTRY not between", value1, value2, "operationRegistry");
            return (Criteria) this;
        }

        public Criteria andManageBedRegistryIsNull() {
            addCriterion("MANAGE_BED_REGISTRY is null");
            return (Criteria) this;
        }

        public Criteria andManageBedRegistryIsNotNull() {
            addCriterion("MANAGE_BED_REGISTRY is not null");
            return (Criteria) this;
        }

        public Criteria andManageBedRegistryEqualTo(Integer value) {
            addCriterion("MANAGE_BED_REGISTRY =", value, "manageBedRegistry");
            return (Criteria) this;
        }

        public Criteria andManageBedRegistryNotEqualTo(Integer value) {
            addCriterion("MANAGE_BED_REGISTRY <>", value, "manageBedRegistry");
            return (Criteria) this;
        }

        public Criteria andManageBedRegistryGreaterThan(Integer value) {
            addCriterion("MANAGE_BED_REGISTRY >", value, "manageBedRegistry");
            return (Criteria) this;
        }

        public Criteria andManageBedRegistryGreaterThanOrEqualTo(Integer value) {
            addCriterion("MANAGE_BED_REGISTRY >=", value, "manageBedRegistry");
            return (Criteria) this;
        }

        public Criteria andManageBedRegistryLessThan(Integer value) {
            addCriterion("MANAGE_BED_REGISTRY <", value, "manageBedRegistry");
            return (Criteria) this;
        }

        public Criteria andManageBedRegistryLessThanOrEqualTo(Integer value) {
            addCriterion("MANAGE_BED_REGISTRY <=", value, "manageBedRegistry");
            return (Criteria) this;
        }

        public Criteria andManageBedRegistryIn(List<Integer> values) {
            addCriterion("MANAGE_BED_REGISTRY in", values, "manageBedRegistry");
            return (Criteria) this;
        }

        public Criteria andManageBedRegistryNotIn(List<Integer> values) {
            addCriterion("MANAGE_BED_REGISTRY not in", values, "manageBedRegistry");
            return (Criteria) this;
        }

        public Criteria andManageBedRegistryBetween(Integer value1, Integer value2) {
            addCriterion("MANAGE_BED_REGISTRY between", value1, value2, "manageBedRegistry");
            return (Criteria) this;
        }

        public Criteria andManageBedRegistryNotBetween(Integer value1, Integer value2) {
            addCriterion("MANAGE_BED_REGISTRY not between", value1, value2, "manageBedRegistry");
            return (Criteria) this;
        }

        public Criteria andGraveIsNull() {
            addCriterion("GRAVE is null");
            return (Criteria) this;
        }

        public Criteria andGraveIsNotNull() {
            addCriterion("GRAVE is not null");
            return (Criteria) this;
        }

        public Criteria andGraveEqualTo(Integer value) {
            addCriterion("GRAVE =", value, "grave");
            return (Criteria) this;
        }

        public Criteria andGraveNotEqualTo(Integer value) {
            addCriterion("GRAVE <>", value, "grave");
            return (Criteria) this;
        }

        public Criteria andGraveGreaterThan(Integer value) {
            addCriterion("GRAVE >", value, "grave");
            return (Criteria) this;
        }

        public Criteria andGraveGreaterThanOrEqualTo(Integer value) {
            addCriterion("GRAVE >=", value, "grave");
            return (Criteria) this;
        }

        public Criteria andGraveLessThan(Integer value) {
            addCriterion("GRAVE <", value, "grave");
            return (Criteria) this;
        }

        public Criteria andGraveLessThanOrEqualTo(Integer value) {
            addCriterion("GRAVE <=", value, "grave");
            return (Criteria) this;
        }

        public Criteria andGraveIn(List<Integer> values) {
            addCriterion("GRAVE in", values, "grave");
            return (Criteria) this;
        }

        public Criteria andGraveNotIn(List<Integer> values) {
            addCriterion("GRAVE not in", values, "grave");
            return (Criteria) this;
        }

        public Criteria andGraveBetween(Integer value1, Integer value2) {
            addCriterion("GRAVE between", value1, value2, "grave");
            return (Criteria) this;
        }

        public Criteria andGraveNotBetween(Integer value1, Integer value2) {
            addCriterion("GRAVE not between", value1, value2, "grave");
            return (Criteria) this;
        }

        public Criteria andAnnualTrainFormIsNull() {
            addCriterion("ANNUAL_TRAIN_FORM is null");
            return (Criteria) this;
        }

        public Criteria andAnnualTrainFormIsNotNull() {
            addCriterion("ANNUAL_TRAIN_FORM is not null");
            return (Criteria) this;
        }

        public Criteria andAnnualTrainFormEqualTo(Integer value) {
            addCriterion("ANNUAL_TRAIN_FORM =", value, "annualTrainForm");
            return (Criteria) this;
        }

        public Criteria andAnnualTrainFormNotEqualTo(Integer value) {
            addCriterion("ANNUAL_TRAIN_FORM <>", value, "annualTrainForm");
            return (Criteria) this;
        }

        public Criteria andAnnualTrainFormGreaterThan(Integer value) {
            addCriterion("ANNUAL_TRAIN_FORM >", value, "annualTrainForm");
            return (Criteria) this;
        }

        public Criteria andAnnualTrainFormGreaterThanOrEqualTo(Integer value) {
            addCriterion("ANNUAL_TRAIN_FORM >=", value, "annualTrainForm");
            return (Criteria) this;
        }

        public Criteria andAnnualTrainFormLessThan(Integer value) {
            addCriterion("ANNUAL_TRAIN_FORM <", value, "annualTrainForm");
            return (Criteria) this;
        }

        public Criteria andAnnualTrainFormLessThanOrEqualTo(Integer value) {
            addCriterion("ANNUAL_TRAIN_FORM <=", value, "annualTrainForm");
            return (Criteria) this;
        }

        public Criteria andAnnualTrainFormIn(List<Integer> values) {
            addCriterion("ANNUAL_TRAIN_FORM in", values, "annualTrainForm");
            return (Criteria) this;
        }

        public Criteria andAnnualTrainFormNotIn(List<Integer> values) {
            addCriterion("ANNUAL_TRAIN_FORM not in", values, "annualTrainForm");
            return (Criteria) this;
        }

        public Criteria andAnnualTrainFormBetween(Integer value1, Integer value2) {
            addCriterion("ANNUAL_TRAIN_FORM between", value1, value2, "annualTrainForm");
            return (Criteria) this;
        }

        public Criteria andAnnualTrainFormNotBetween(Integer value1, Integer value2) {
            addCriterion("ANNUAL_TRAIN_FORM not between", value1, value2, "annualTrainForm");
            return (Criteria) this;
        }

        public Criteria andSkillAndOperationRegistryIsNull() {
            addCriterion("SKILL_AND_OPERATION_REGISTRY is null");
            return (Criteria) this;
        }

        public Criteria andSkillAndOperationRegistryIsNotNull() {
            addCriterion("SKILL_AND_OPERATION_REGISTRY is not null");
            return (Criteria) this;
        }

        public Criteria andSkillAndOperationRegistryEqualTo(Integer value) {
            addCriterion("SKILL_AND_OPERATION_REGISTRY =", value, "skillAndOperationRegistry");
            return (Criteria) this;
        }

        public Criteria andSkillAndOperationRegistryNotEqualTo(Integer value) {
            addCriterion("SKILL_AND_OPERATION_REGISTRY <>", value, "skillAndOperationRegistry");
            return (Criteria) this;
        }

        public Criteria andSkillAndOperationRegistryGreaterThan(Integer value) {
            addCriterion("SKILL_AND_OPERATION_REGISTRY >", value, "skillAndOperationRegistry");
            return (Criteria) this;
        }

        public Criteria andSkillAndOperationRegistryGreaterThanOrEqualTo(Integer value) {
            addCriterion("SKILL_AND_OPERATION_REGISTRY >=", value, "skillAndOperationRegistry");
            return (Criteria) this;
        }

        public Criteria andSkillAndOperationRegistryLessThan(Integer value) {
            addCriterion("SKILL_AND_OPERATION_REGISTRY <", value, "skillAndOperationRegistry");
            return (Criteria) this;
        }

        public Criteria andSkillAndOperationRegistryLessThanOrEqualTo(Integer value) {
            addCriterion("SKILL_AND_OPERATION_REGISTRY <=", value, "skillAndOperationRegistry");
            return (Criteria) this;
        }

        public Criteria andSkillAndOperationRegistryIn(List<Integer> values) {
            addCriterion("SKILL_AND_OPERATION_REGISTRY in", values, "skillAndOperationRegistry");
            return (Criteria) this;
        }

        public Criteria andSkillAndOperationRegistryNotIn(List<Integer> values) {
            addCriterion("SKILL_AND_OPERATION_REGISTRY not in", values, "skillAndOperationRegistry");
            return (Criteria) this;
        }

        public Criteria andSkillAndOperationRegistryBetween(Integer value1, Integer value2) {
            addCriterion("SKILL_AND_OPERATION_REGISTRY between", value1, value2, "skillAndOperationRegistry");
            return (Criteria) this;
        }

        public Criteria andSkillAndOperationRegistryNotBetween(Integer value1, Integer value2) {
            addCriterion("SKILL_AND_OPERATION_REGISTRY not between", value1, value2, "skillAndOperationRegistry");
            return (Criteria) this;
        }

        public Criteria andHospitalizationLogIsNull() {
            addCriterion("HOSPITALIZATION_LOG is null");
            return (Criteria) this;
        }

        public Criteria andHospitalizationLogIsNotNull() {
            addCriterion("HOSPITALIZATION_LOG is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalizationLogEqualTo(Integer value) {
            addCriterion("HOSPITALIZATION_LOG =", value, "hospitalizationLog");
            return (Criteria) this;
        }

        public Criteria andHospitalizationLogNotEqualTo(Integer value) {
            addCriterion("HOSPITALIZATION_LOG <>", value, "hospitalizationLog");
            return (Criteria) this;
        }

        public Criteria andHospitalizationLogGreaterThan(Integer value) {
            addCriterion("HOSPITALIZATION_LOG >", value, "hospitalizationLog");
            return (Criteria) this;
        }

        public Criteria andHospitalizationLogGreaterThanOrEqualTo(Integer value) {
            addCriterion("HOSPITALIZATION_LOG >=", value, "hospitalizationLog");
            return (Criteria) this;
        }

        public Criteria andHospitalizationLogLessThan(Integer value) {
            addCriterion("HOSPITALIZATION_LOG <", value, "hospitalizationLog");
            return (Criteria) this;
        }

        public Criteria andHospitalizationLogLessThanOrEqualTo(Integer value) {
            addCriterion("HOSPITALIZATION_LOG <=", value, "hospitalizationLog");
            return (Criteria) this;
        }

        public Criteria andHospitalizationLogIn(List<Integer> values) {
            addCriterion("HOSPITALIZATION_LOG in", values, "hospitalizationLog");
            return (Criteria) this;
        }

        public Criteria andHospitalizationLogNotIn(List<Integer> values) {
            addCriterion("HOSPITALIZATION_LOG not in", values, "hospitalizationLog");
            return (Criteria) this;
        }

        public Criteria andHospitalizationLogBetween(Integer value1, Integer value2) {
            addCriterion("HOSPITALIZATION_LOG between", value1, value2, "hospitalizationLog");
            return (Criteria) this;
        }

        public Criteria andHospitalizationLogNotBetween(Integer value1, Integer value2) {
            addCriterion("HOSPITALIZATION_LOG not between", value1, value2, "hospitalizationLog");
            return (Criteria) this;
        }

        public Criteria andCampaignRegistryIsNull() {
            addCriterion("CAMPAIGN_REGISTRY is null");
            return (Criteria) this;
        }

        public Criteria andCampaignRegistryIsNotNull() {
            addCriterion("CAMPAIGN_REGISTRY is not null");
            return (Criteria) this;
        }

        public Criteria andCampaignRegistryEqualTo(Integer value) {
            addCriterion("CAMPAIGN_REGISTRY =", value, "campaignRegistry");
            return (Criteria) this;
        }

        public Criteria andCampaignRegistryNotEqualTo(Integer value) {
            addCriterion("CAMPAIGN_REGISTRY <>", value, "campaignRegistry");
            return (Criteria) this;
        }

        public Criteria andCampaignRegistryGreaterThan(Integer value) {
            addCriterion("CAMPAIGN_REGISTRY >", value, "campaignRegistry");
            return (Criteria) this;
        }

        public Criteria andCampaignRegistryGreaterThanOrEqualTo(Integer value) {
            addCriterion("CAMPAIGN_REGISTRY >=", value, "campaignRegistry");
            return (Criteria) this;
        }

        public Criteria andCampaignRegistryLessThan(Integer value) {
            addCriterion("CAMPAIGN_REGISTRY <", value, "campaignRegistry");
            return (Criteria) this;
        }

        public Criteria andCampaignRegistryLessThanOrEqualTo(Integer value) {
            addCriterion("CAMPAIGN_REGISTRY <=", value, "campaignRegistry");
            return (Criteria) this;
        }

        public Criteria andCampaignRegistryIn(List<Integer> values) {
            addCriterion("CAMPAIGN_REGISTRY in", values, "campaignRegistry");
            return (Criteria) this;
        }

        public Criteria andCampaignRegistryNotIn(List<Integer> values) {
            addCriterion("CAMPAIGN_REGISTRY not in", values, "campaignRegistry");
            return (Criteria) this;
        }

        public Criteria andCampaignRegistryBetween(Integer value1, Integer value2) {
            addCriterion("CAMPAIGN_REGISTRY between", value1, value2, "campaignRegistry");
            return (Criteria) this;
        }

        public Criteria andCampaignRegistryNotBetween(Integer value1, Integer value2) {
            addCriterion("CAMPAIGN_REGISTRY not between", value1, value2, "campaignRegistry");
            return (Criteria) this;
        }

        public Criteria andCampaignNoItemRegistryIsNull() {
            addCriterion("CAMPAIGN_NO_ITEM_REGISTRY is null");
            return (Criteria) this;
        }

        public Criteria andCampaignNoItemRegistryIsNotNull() {
            addCriterion("CAMPAIGN_NO_ITEM_REGISTRY is not null");
            return (Criteria) this;
        }

        public Criteria andCampaignNoItemRegistryEqualTo(Integer value) {
            addCriterion("CAMPAIGN_NO_ITEM_REGISTRY =", value, "campaignNoItemRegistry");
            return (Criteria) this;
        }

        public Criteria andCampaignNoItemRegistryNotEqualTo(Integer value) {
            addCriterion("CAMPAIGN_NO_ITEM_REGISTRY <>", value, "campaignNoItemRegistry");
            return (Criteria) this;
        }

        public Criteria andCampaignNoItemRegistryGreaterThan(Integer value) {
            addCriterion("CAMPAIGN_NO_ITEM_REGISTRY >", value, "campaignNoItemRegistry");
            return (Criteria) this;
        }

        public Criteria andCampaignNoItemRegistryGreaterThanOrEqualTo(Integer value) {
            addCriterion("CAMPAIGN_NO_ITEM_REGISTRY >=", value, "campaignNoItemRegistry");
            return (Criteria) this;
        }

        public Criteria andCampaignNoItemRegistryLessThan(Integer value) {
            addCriterion("CAMPAIGN_NO_ITEM_REGISTRY <", value, "campaignNoItemRegistry");
            return (Criteria) this;
        }

        public Criteria andCampaignNoItemRegistryLessThanOrEqualTo(Integer value) {
            addCriterion("CAMPAIGN_NO_ITEM_REGISTRY <=", value, "campaignNoItemRegistry");
            return (Criteria) this;
        }

        public Criteria andCampaignNoItemRegistryIn(List<Integer> values) {
            addCriterion("CAMPAIGN_NO_ITEM_REGISTRY in", values, "campaignNoItemRegistry");
            return (Criteria) this;
        }

        public Criteria andCampaignNoItemRegistryNotIn(List<Integer> values) {
            addCriterion("CAMPAIGN_NO_ITEM_REGISTRY not in", values, "campaignNoItemRegistry");
            return (Criteria) this;
        }

        public Criteria andCampaignNoItemRegistryBetween(Integer value1, Integer value2) {
            addCriterion("CAMPAIGN_NO_ITEM_REGISTRY between", value1, value2, "campaignNoItemRegistry");
            return (Criteria) this;
        }

        public Criteria andCampaignNoItemRegistryNotBetween(Integer value1, Integer value2) {
            addCriterion("CAMPAIGN_NO_ITEM_REGISTRY not between", value1, value2, "campaignNoItemRegistry");
            return (Criteria) this;
        }

        public Criteria andInternshipIsNull() {
            addCriterion("INTERNSHIP is null");
            return (Criteria) this;
        }

        public Criteria andInternshipIsNotNull() {
            addCriterion("INTERNSHIP is not null");
            return (Criteria) this;
        }

        public Criteria andInternshipEqualTo(Integer value) {
            addCriterion("INTERNSHIP =", value, "internship");
            return (Criteria) this;
        }

        public Criteria andInternshipNotEqualTo(Integer value) {
            addCriterion("INTERNSHIP <>", value, "internship");
            return (Criteria) this;
        }

        public Criteria andInternshipGreaterThan(Integer value) {
            addCriterion("INTERNSHIP >", value, "internship");
            return (Criteria) this;
        }

        public Criteria andInternshipGreaterThanOrEqualTo(Integer value) {
            addCriterion("INTERNSHIP >=", value, "internship");
            return (Criteria) this;
        }

        public Criteria andInternshipLessThan(Integer value) {
            addCriterion("INTERNSHIP <", value, "internship");
            return (Criteria) this;
        }

        public Criteria andInternshipLessThanOrEqualTo(Integer value) {
            addCriterion("INTERNSHIP <=", value, "internship");
            return (Criteria) this;
        }

        public Criteria andInternshipIn(List<Integer> values) {
            addCriterion("INTERNSHIP in", values, "internship");
            return (Criteria) this;
        }

        public Criteria andInternshipNotIn(List<Integer> values) {
            addCriterion("INTERNSHIP not in", values, "internship");
            return (Criteria) this;
        }

        public Criteria andInternshipBetween(Integer value1, Integer value2) {
            addCriterion("INTERNSHIP between", value1, value2, "internship");
            return (Criteria) this;
        }

        public Criteria andInternshipNotBetween(Integer value1, Integer value2) {
            addCriterion("INTERNSHIP not between", value1, value2, "internship");
            return (Criteria) this;
        }

        public Criteria andCaseRecordIsNull() {
            addCriterion("CASE_RECORD is null");
            return (Criteria) this;
        }

        public Criteria andCaseRecordIsNotNull() {
            addCriterion("CASE_RECORD is not null");
            return (Criteria) this;
        }

        public Criteria andCaseRecordEqualTo(Integer value) {
            addCriterion("CASE_RECORD =", value, "caseRecord");
            return (Criteria) this;
        }

        public Criteria andCaseRecordNotEqualTo(Integer value) {
            addCriterion("CASE_RECORD <>", value, "caseRecord");
            return (Criteria) this;
        }

        public Criteria andCaseRecordGreaterThan(Integer value) {
            addCriterion("CASE_RECORD >", value, "caseRecord");
            return (Criteria) this;
        }

        public Criteria andCaseRecordGreaterThanOrEqualTo(Integer value) {
            addCriterion("CASE_RECORD >=", value, "caseRecord");
            return (Criteria) this;
        }

        public Criteria andCaseRecordLessThan(Integer value) {
            addCriterion("CASE_RECORD <", value, "caseRecord");
            return (Criteria) this;
        }

        public Criteria andCaseRecordLessThanOrEqualTo(Integer value) {
            addCriterion("CASE_RECORD <=", value, "caseRecord");
            return (Criteria) this;
        }

        public Criteria andCaseRecordIn(List<Integer> values) {
            addCriterion("CASE_RECORD in", values, "caseRecord");
            return (Criteria) this;
        }

        public Criteria andCaseRecordNotIn(List<Integer> values) {
            addCriterion("CASE_RECORD not in", values, "caseRecord");
            return (Criteria) this;
        }

        public Criteria andCaseRecordBetween(Integer value1, Integer value2) {
            addCriterion("CASE_RECORD between", value1, value2, "caseRecord");
            return (Criteria) this;
        }

        public Criteria andCaseRecordNotBetween(Integer value1, Integer value2) {
            addCriterion("CASE_RECORD not between", value1, value2, "caseRecord");
            return (Criteria) this;
        }

        public Criteria andSchStatusIsNull() {
            addCriterion("SCH_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andSchStatusIsNotNull() {
            addCriterion("SCH_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andSchStatusEqualTo(String value) {
            addCriterion("SCH_STATUS =", value, "schStatus");
            return (Criteria) this;
        }

        public Criteria andSchStatusNotEqualTo(String value) {
            addCriterion("SCH_STATUS <>", value, "schStatus");
            return (Criteria) this;
        }

        public Criteria andSchStatusGreaterThan(String value) {
            addCriterion("SCH_STATUS >", value, "schStatus");
            return (Criteria) this;
        }

        public Criteria andSchStatusGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_STATUS >=", value, "schStatus");
            return (Criteria) this;
        }

        public Criteria andSchStatusLessThan(String value) {
            addCriterion("SCH_STATUS <", value, "schStatus");
            return (Criteria) this;
        }

        public Criteria andSchStatusLessThanOrEqualTo(String value) {
            addCriterion("SCH_STATUS <=", value, "schStatus");
            return (Criteria) this;
        }

        public Criteria andSchStatusLike(String value) {
            addCriterion("SCH_STATUS like", value, "schStatus");
            return (Criteria) this;
        }

        public Criteria andSchStatusNotLike(String value) {
            addCriterion("SCH_STATUS not like", value, "schStatus");
            return (Criteria) this;
        }

        public Criteria andSchStatusIn(List<String> values) {
            addCriterion("SCH_STATUS in", values, "schStatus");
            return (Criteria) this;
        }

        public Criteria andSchStatusNotIn(List<String> values) {
            addCriterion("SCH_STATUS not in", values, "schStatus");
            return (Criteria) this;
        }

        public Criteria andSchStatusBetween(String value1, String value2) {
            addCriterion("SCH_STATUS between", value1, value2, "schStatus");
            return (Criteria) this;
        }

        public Criteria andSchStatusNotBetween(String value1, String value2) {
            addCriterion("SCH_STATUS not between", value1, value2, "schStatus");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameIsNull() {
            addCriterion("SCH_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameIsNotNull() {
            addCriterion("SCH_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameEqualTo(String value) {
            addCriterion("SCH_STATUS_NAME =", value, "schStatusName");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameNotEqualTo(String value) {
            addCriterion("SCH_STATUS_NAME <>", value, "schStatusName");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameGreaterThan(String value) {
            addCriterion("SCH_STATUS_NAME >", value, "schStatusName");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_STATUS_NAME >=", value, "schStatusName");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameLessThan(String value) {
            addCriterion("SCH_STATUS_NAME <", value, "schStatusName");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameLessThanOrEqualTo(String value) {
            addCriterion("SCH_STATUS_NAME <=", value, "schStatusName");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameLike(String value) {
            addCriterion("SCH_STATUS_NAME like", value, "schStatusName");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameNotLike(String value) {
            addCriterion("SCH_STATUS_NAME not like", value, "schStatusName");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameIn(List<String> values) {
            addCriterion("SCH_STATUS_NAME in", values, "schStatusName");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameNotIn(List<String> values) {
            addCriterion("SCH_STATUS_NAME not in", values, "schStatusName");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameBetween(String value1, String value2) {
            addCriterion("SCH_STATUS_NAME between", value1, value2, "schStatusName");
            return (Criteria) this;
        }

        public Criteria andSchStatusNameNotBetween(String value1, String value2) {
            addCriterion("SCH_STATUS_NAME not between", value1, value2, "schStatusName");
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

        public Criteria andProcessFlowIsNull() {
            addCriterion("PROCESS_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andProcessFlowIsNotNull() {
            addCriterion("PROCESS_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andProcessFlowEqualTo(String value) {
            addCriterion("PROCESS_FLOW =", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowNotEqualTo(String value) {
            addCriterion("PROCESS_FLOW <>", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowGreaterThan(String value) {
            addCriterion("PROCESS_FLOW >", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PROCESS_FLOW >=", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowLessThan(String value) {
            addCriterion("PROCESS_FLOW <", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowLessThanOrEqualTo(String value) {
            addCriterion("PROCESS_FLOW <=", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowLike(String value) {
            addCriterion("PROCESS_FLOW like", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowNotLike(String value) {
            addCriterion("PROCESS_FLOW not like", value, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowIn(List<String> values) {
            addCriterion("PROCESS_FLOW in", values, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowNotIn(List<String> values) {
            addCriterion("PROCESS_FLOW not in", values, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowBetween(String value1, String value2) {
            addCriterion("PROCESS_FLOW between", value1, value2, "processFlow");
            return (Criteria) this;
        }

        public Criteria andProcessFlowNotBetween(String value1, String value2) {
            addCriterion("PROCESS_FLOW not between", value1, value2, "processFlow");
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