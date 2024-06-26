package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class JsresAppInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JsresAppInfoExample() {
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

        public Criteria andOrgCodeIsNull() {
            addCriterion("ORG_CODE is null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNotNull() {
            addCriterion("ORG_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeEqualTo(String value) {
            addCriterion("ORG_CODE =", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotEqualTo(String value) {
            addCriterion("ORG_CODE <>", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThan(String value) {
            addCriterion("ORG_CODE >", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CODE >=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThan(String value) {
            addCriterion("ORG_CODE <", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("ORG_CODE <=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLike(String value) {
            addCriterion("ORG_CODE like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotLike(String value) {
            addCriterion("ORG_CODE not like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIn(List<String> values) {
            addCriterion("ORG_CODE in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotIn(List<String> values) {
            addCriterion("ORG_CODE not in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeBetween(String value1, String value2) {
            addCriterion("ORG_CODE between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotBetween(String value1, String value2) {
            addCriterion("ORG_CODE not between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andParentOrgFlowIsNull() {
            addCriterion("PARENT_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andParentOrgFlowIsNotNull() {
            addCriterion("PARENT_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andParentOrgFlowEqualTo(String value) {
            addCriterion("PARENT_ORG_FLOW =", value, "parentOrgFlow");
            return (Criteria) this;
        }

        public Criteria andParentOrgFlowNotEqualTo(String value) {
            addCriterion("PARENT_ORG_FLOW <>", value, "parentOrgFlow");
            return (Criteria) this;
        }

        public Criteria andParentOrgFlowGreaterThan(String value) {
            addCriterion("PARENT_ORG_FLOW >", value, "parentOrgFlow");
            return (Criteria) this;
        }

        public Criteria andParentOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_ORG_FLOW >=", value, "parentOrgFlow");
            return (Criteria) this;
        }

        public Criteria andParentOrgFlowLessThan(String value) {
            addCriterion("PARENT_ORG_FLOW <", value, "parentOrgFlow");
            return (Criteria) this;
        }

        public Criteria andParentOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("PARENT_ORG_FLOW <=", value, "parentOrgFlow");
            return (Criteria) this;
        }

        public Criteria andParentOrgFlowLike(String value) {
            addCriterion("PARENT_ORG_FLOW like", value, "parentOrgFlow");
            return (Criteria) this;
        }

        public Criteria andParentOrgFlowNotLike(String value) {
            addCriterion("PARENT_ORG_FLOW not like", value, "parentOrgFlow");
            return (Criteria) this;
        }

        public Criteria andParentOrgFlowIn(List<String> values) {
            addCriterion("PARENT_ORG_FLOW in", values, "parentOrgFlow");
            return (Criteria) this;
        }

        public Criteria andParentOrgFlowNotIn(List<String> values) {
            addCriterion("PARENT_ORG_FLOW not in", values, "parentOrgFlow");
            return (Criteria) this;
        }

        public Criteria andParentOrgFlowBetween(String value1, String value2) {
            addCriterion("PARENT_ORG_FLOW between", value1, value2, "parentOrgFlow");
            return (Criteria) this;
        }

        public Criteria andParentOrgFlowNotBetween(String value1, String value2) {
            addCriterion("PARENT_ORG_FLOW not between", value1, value2, "parentOrgFlow");
            return (Criteria) this;
        }

        public Criteria andMonthDateIsNull() {
            addCriterion("MONTH_DATE is null");
            return (Criteria) this;
        }

        public Criteria andMonthDateIsNotNull() {
            addCriterion("MONTH_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andMonthDateEqualTo(String value) {
            addCriterion("MONTH_DATE =", value, "monthDate");
            return (Criteria) this;
        }

        public Criteria andMonthDateNotEqualTo(String value) {
            addCriterion("MONTH_DATE <>", value, "monthDate");
            return (Criteria) this;
        }

        public Criteria andMonthDateGreaterThan(String value) {
            addCriterion("MONTH_DATE >", value, "monthDate");
            return (Criteria) this;
        }

        public Criteria andMonthDateGreaterThanOrEqualTo(String value) {
            addCriterion("MONTH_DATE >=", value, "monthDate");
            return (Criteria) this;
        }

        public Criteria andMonthDateLessThan(String value) {
            addCriterion("MONTH_DATE <", value, "monthDate");
            return (Criteria) this;
        }

        public Criteria andMonthDateLessThanOrEqualTo(String value) {
            addCriterion("MONTH_DATE <=", value, "monthDate");
            return (Criteria) this;
        }

        public Criteria andMonthDateLike(String value) {
            addCriterion("MONTH_DATE like", value, "monthDate");
            return (Criteria) this;
        }

        public Criteria andMonthDateNotLike(String value) {
            addCriterion("MONTH_DATE not like", value, "monthDate");
            return (Criteria) this;
        }

        public Criteria andMonthDateIn(List<String> values) {
            addCriterion("MONTH_DATE in", values, "monthDate");
            return (Criteria) this;
        }

        public Criteria andMonthDateNotIn(List<String> values) {
            addCriterion("MONTH_DATE not in", values, "monthDate");
            return (Criteria) this;
        }

        public Criteria andMonthDateBetween(String value1, String value2) {
            addCriterion("MONTH_DATE between", value1, value2, "monthDate");
            return (Criteria) this;
        }

        public Criteria andMonthDateNotBetween(String value1, String value2) {
            addCriterion("MONTH_DATE not between", value1, value2, "monthDate");
            return (Criteria) this;
        }

        public Criteria andIsContainIsNull() {
            addCriterion("IS_CONTAIN is null");
            return (Criteria) this;
        }

        public Criteria andIsContainIsNotNull() {
            addCriterion("IS_CONTAIN is not null");
            return (Criteria) this;
        }

        public Criteria andIsContainEqualTo(String value) {
            addCriterion("IS_CONTAIN =", value, "isContain");
            return (Criteria) this;
        }

        public Criteria andIsContainNotEqualTo(String value) {
            addCriterion("IS_CONTAIN <>", value, "isContain");
            return (Criteria) this;
        }

        public Criteria andIsContainGreaterThan(String value) {
            addCriterion("IS_CONTAIN >", value, "isContain");
            return (Criteria) this;
        }

        public Criteria andIsContainGreaterThanOrEqualTo(String value) {
            addCriterion("IS_CONTAIN >=", value, "isContain");
            return (Criteria) this;
        }

        public Criteria andIsContainLessThan(String value) {
            addCriterion("IS_CONTAIN <", value, "isContain");
            return (Criteria) this;
        }

        public Criteria andIsContainLessThanOrEqualTo(String value) {
            addCriterion("IS_CONTAIN <=", value, "isContain");
            return (Criteria) this;
        }

        public Criteria andIsContainLike(String value) {
            addCriterion("IS_CONTAIN like", value, "isContain");
            return (Criteria) this;
        }

        public Criteria andIsContainNotLike(String value) {
            addCriterion("IS_CONTAIN not like", value, "isContain");
            return (Criteria) this;
        }

        public Criteria andIsContainIn(List<String> values) {
            addCriterion("IS_CONTAIN in", values, "isContain");
            return (Criteria) this;
        }

        public Criteria andIsContainNotIn(List<String> values) {
            addCriterion("IS_CONTAIN not in", values, "isContain");
            return (Criteria) this;
        }

        public Criteria andIsContainBetween(String value1, String value2) {
            addCriterion("IS_CONTAIN between", value1, value2, "isContain");
            return (Criteria) this;
        }

        public Criteria andIsContainNotBetween(String value1, String value2) {
            addCriterion("IS_CONTAIN not between", value1, value2, "isContain");
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

        public Criteria andDoctorTrainTotalIsNull() {
            addCriterion("DOCTOR_TRAIN_TOTAL is null");
            return (Criteria) this;
        }

        public Criteria andDoctorTrainTotalIsNotNull() {
            addCriterion("DOCTOR_TRAIN_TOTAL is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorTrainTotalEqualTo(String value) {
            addCriterion("DOCTOR_TRAIN_TOTAL =", value, "doctorTrainTotal");
            return (Criteria) this;
        }

        public Criteria andDoctorTrainTotalNotEqualTo(String value) {
            addCriterion("DOCTOR_TRAIN_TOTAL <>", value, "doctorTrainTotal");
            return (Criteria) this;
        }

        public Criteria andDoctorTrainTotalGreaterThan(String value) {
            addCriterion("DOCTOR_TRAIN_TOTAL >", value, "doctorTrainTotal");
            return (Criteria) this;
        }

        public Criteria andDoctorTrainTotalGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_TRAIN_TOTAL >=", value, "doctorTrainTotal");
            return (Criteria) this;
        }

        public Criteria andDoctorTrainTotalLessThan(String value) {
            addCriterion("DOCTOR_TRAIN_TOTAL <", value, "doctorTrainTotal");
            return (Criteria) this;
        }

        public Criteria andDoctorTrainTotalLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_TRAIN_TOTAL <=", value, "doctorTrainTotal");
            return (Criteria) this;
        }

        public Criteria andDoctorTrainTotalLike(String value) {
            addCriterion("DOCTOR_TRAIN_TOTAL like", value, "doctorTrainTotal");
            return (Criteria) this;
        }

        public Criteria andDoctorTrainTotalNotLike(String value) {
            addCriterion("DOCTOR_TRAIN_TOTAL not like", value, "doctorTrainTotal");
            return (Criteria) this;
        }

        public Criteria andDoctorTrainTotalIn(List<String> values) {
            addCriterion("DOCTOR_TRAIN_TOTAL in", values, "doctorTrainTotal");
            return (Criteria) this;
        }

        public Criteria andDoctorTrainTotalNotIn(List<String> values) {
            addCriterion("DOCTOR_TRAIN_TOTAL not in", values, "doctorTrainTotal");
            return (Criteria) this;
        }

        public Criteria andDoctorTrainTotalBetween(String value1, String value2) {
            addCriterion("DOCTOR_TRAIN_TOTAL between", value1, value2, "doctorTrainTotal");
            return (Criteria) this;
        }

        public Criteria andDoctorTrainTotalNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_TRAIN_TOTAL not between", value1, value2, "doctorTrainTotal");
            return (Criteria) this;
        }

        public Criteria andDoctorUseTotalIsNull() {
            addCriterion("DOCTOR_USE_TOTAL is null");
            return (Criteria) this;
        }

        public Criteria andDoctorUseTotalIsNotNull() {
            addCriterion("DOCTOR_USE_TOTAL is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorUseTotalEqualTo(String value) {
            addCriterion("DOCTOR_USE_TOTAL =", value, "doctorUseTotal");
            return (Criteria) this;
        }

        public Criteria andDoctorUseTotalNotEqualTo(String value) {
            addCriterion("DOCTOR_USE_TOTAL <>", value, "doctorUseTotal");
            return (Criteria) this;
        }

        public Criteria andDoctorUseTotalGreaterThan(String value) {
            addCriterion("DOCTOR_USE_TOTAL >", value, "doctorUseTotal");
            return (Criteria) this;
        }

        public Criteria andDoctorUseTotalGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_USE_TOTAL >=", value, "doctorUseTotal");
            return (Criteria) this;
        }

        public Criteria andDoctorUseTotalLessThan(String value) {
            addCriterion("DOCTOR_USE_TOTAL <", value, "doctorUseTotal");
            return (Criteria) this;
        }

        public Criteria andDoctorUseTotalLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_USE_TOTAL <=", value, "doctorUseTotal");
            return (Criteria) this;
        }

        public Criteria andDoctorUseTotalLike(String value) {
            addCriterion("DOCTOR_USE_TOTAL like", value, "doctorUseTotal");
            return (Criteria) this;
        }

        public Criteria andDoctorUseTotalNotLike(String value) {
            addCriterion("DOCTOR_USE_TOTAL not like", value, "doctorUseTotal");
            return (Criteria) this;
        }

        public Criteria andDoctorUseTotalIn(List<String> values) {
            addCriterion("DOCTOR_USE_TOTAL in", values, "doctorUseTotal");
            return (Criteria) this;
        }

        public Criteria andDoctorUseTotalNotIn(List<String> values) {
            addCriterion("DOCTOR_USE_TOTAL not in", values, "doctorUseTotal");
            return (Criteria) this;
        }

        public Criteria andDoctorUseTotalBetween(String value1, String value2) {
            addCriterion("DOCTOR_USE_TOTAL between", value1, value2, "doctorUseTotal");
            return (Criteria) this;
        }

        public Criteria andDoctorUseTotalNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_USE_TOTAL not between", value1, value2, "doctorUseTotal");
            return (Criteria) this;
        }

        public Criteria andDoctorRateIsNull() {
            addCriterion("DOCTOR_RATE is null");
            return (Criteria) this;
        }

        public Criteria andDoctorRateIsNotNull() {
            addCriterion("DOCTOR_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorRateEqualTo(String value) {
            addCriterion("DOCTOR_RATE =", value, "doctorRate");
            return (Criteria) this;
        }

        public Criteria andDoctorRateNotEqualTo(String value) {
            addCriterion("DOCTOR_RATE <>", value, "doctorRate");
            return (Criteria) this;
        }

        public Criteria andDoctorRateGreaterThan(String value) {
            addCriterion("DOCTOR_RATE >", value, "doctorRate");
            return (Criteria) this;
        }

        public Criteria andDoctorRateGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_RATE >=", value, "doctorRate");
            return (Criteria) this;
        }

        public Criteria andDoctorRateLessThan(String value) {
            addCriterion("DOCTOR_RATE <", value, "doctorRate");
            return (Criteria) this;
        }

        public Criteria andDoctorRateLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_RATE <=", value, "doctorRate");
            return (Criteria) this;
        }

        public Criteria andDoctorRateLike(String value) {
            addCriterion("DOCTOR_RATE like", value, "doctorRate");
            return (Criteria) this;
        }

        public Criteria andDoctorRateNotLike(String value) {
            addCriterion("DOCTOR_RATE not like", value, "doctorRate");
            return (Criteria) this;
        }

        public Criteria andDoctorRateIn(List<String> values) {
            addCriterion("DOCTOR_RATE in", values, "doctorRate");
            return (Criteria) this;
        }

        public Criteria andDoctorRateNotIn(List<String> values) {
            addCriterion("DOCTOR_RATE not in", values, "doctorRate");
            return (Criteria) this;
        }

        public Criteria andDoctorRateBetween(String value1, String value2) {
            addCriterion("DOCTOR_RATE between", value1, value2, "doctorRate");
            return (Criteria) this;
        }

        public Criteria andDoctorRateNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_RATE not between", value1, value2, "doctorRate");
            return (Criteria) this;
        }

        public Criteria andGraduateTrainTotalIsNull() {
            addCriterion("GRADUATE_TRAIN_TOTAL is null");
            return (Criteria) this;
        }

        public Criteria andGraduateTrainTotalIsNotNull() {
            addCriterion("GRADUATE_TRAIN_TOTAL is not null");
            return (Criteria) this;
        }

        public Criteria andGraduateTrainTotalEqualTo(String value) {
            addCriterion("GRADUATE_TRAIN_TOTAL =", value, "graduateTrainTotal");
            return (Criteria) this;
        }

        public Criteria andGraduateTrainTotalNotEqualTo(String value) {
            addCriterion("GRADUATE_TRAIN_TOTAL <>", value, "graduateTrainTotal");
            return (Criteria) this;
        }

        public Criteria andGraduateTrainTotalGreaterThan(String value) {
            addCriterion("GRADUATE_TRAIN_TOTAL >", value, "graduateTrainTotal");
            return (Criteria) this;
        }

        public Criteria andGraduateTrainTotalGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATE_TRAIN_TOTAL >=", value, "graduateTrainTotal");
            return (Criteria) this;
        }

        public Criteria andGraduateTrainTotalLessThan(String value) {
            addCriterion("GRADUATE_TRAIN_TOTAL <", value, "graduateTrainTotal");
            return (Criteria) this;
        }

        public Criteria andGraduateTrainTotalLessThanOrEqualTo(String value) {
            addCriterion("GRADUATE_TRAIN_TOTAL <=", value, "graduateTrainTotal");
            return (Criteria) this;
        }

        public Criteria andGraduateTrainTotalLike(String value) {
            addCriterion("GRADUATE_TRAIN_TOTAL like", value, "graduateTrainTotal");
            return (Criteria) this;
        }

        public Criteria andGraduateTrainTotalNotLike(String value) {
            addCriterion("GRADUATE_TRAIN_TOTAL not like", value, "graduateTrainTotal");
            return (Criteria) this;
        }

        public Criteria andGraduateTrainTotalIn(List<String> values) {
            addCriterion("GRADUATE_TRAIN_TOTAL in", values, "graduateTrainTotal");
            return (Criteria) this;
        }

        public Criteria andGraduateTrainTotalNotIn(List<String> values) {
            addCriterion("GRADUATE_TRAIN_TOTAL not in", values, "graduateTrainTotal");
            return (Criteria) this;
        }

        public Criteria andGraduateTrainTotalBetween(String value1, String value2) {
            addCriterion("GRADUATE_TRAIN_TOTAL between", value1, value2, "graduateTrainTotal");
            return (Criteria) this;
        }

        public Criteria andGraduateTrainTotalNotBetween(String value1, String value2) {
            addCriterion("GRADUATE_TRAIN_TOTAL not between", value1, value2, "graduateTrainTotal");
            return (Criteria) this;
        }

        public Criteria andGraduateUseTotalIsNull() {
            addCriterion("GRADUATE_USE_TOTAL is null");
            return (Criteria) this;
        }

        public Criteria andGraduateUseTotalIsNotNull() {
            addCriterion("GRADUATE_USE_TOTAL is not null");
            return (Criteria) this;
        }

        public Criteria andGraduateUseTotalEqualTo(String value) {
            addCriterion("GRADUATE_USE_TOTAL =", value, "graduateUseTotal");
            return (Criteria) this;
        }

        public Criteria andGraduateUseTotalNotEqualTo(String value) {
            addCriterion("GRADUATE_USE_TOTAL <>", value, "graduateUseTotal");
            return (Criteria) this;
        }

        public Criteria andGraduateUseTotalGreaterThan(String value) {
            addCriterion("GRADUATE_USE_TOTAL >", value, "graduateUseTotal");
            return (Criteria) this;
        }

        public Criteria andGraduateUseTotalGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATE_USE_TOTAL >=", value, "graduateUseTotal");
            return (Criteria) this;
        }

        public Criteria andGraduateUseTotalLessThan(String value) {
            addCriterion("GRADUATE_USE_TOTAL <", value, "graduateUseTotal");
            return (Criteria) this;
        }

        public Criteria andGraduateUseTotalLessThanOrEqualTo(String value) {
            addCriterion("GRADUATE_USE_TOTAL <=", value, "graduateUseTotal");
            return (Criteria) this;
        }

        public Criteria andGraduateUseTotalLike(String value) {
            addCriterion("GRADUATE_USE_TOTAL like", value, "graduateUseTotal");
            return (Criteria) this;
        }

        public Criteria andGraduateUseTotalNotLike(String value) {
            addCriterion("GRADUATE_USE_TOTAL not like", value, "graduateUseTotal");
            return (Criteria) this;
        }

        public Criteria andGraduateUseTotalIn(List<String> values) {
            addCriterion("GRADUATE_USE_TOTAL in", values, "graduateUseTotal");
            return (Criteria) this;
        }

        public Criteria andGraduateUseTotalNotIn(List<String> values) {
            addCriterion("GRADUATE_USE_TOTAL not in", values, "graduateUseTotal");
            return (Criteria) this;
        }

        public Criteria andGraduateUseTotalBetween(String value1, String value2) {
            addCriterion("GRADUATE_USE_TOTAL between", value1, value2, "graduateUseTotal");
            return (Criteria) this;
        }

        public Criteria andGraduateUseTotalNotBetween(String value1, String value2) {
            addCriterion("GRADUATE_USE_TOTAL not between", value1, value2, "graduateUseTotal");
            return (Criteria) this;
        }

        public Criteria andGraduateRateIsNull() {
            addCriterion("GRADUATE_RATE is null");
            return (Criteria) this;
        }

        public Criteria andGraduateRateIsNotNull() {
            addCriterion("GRADUATE_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andGraduateRateEqualTo(String value) {
            addCriterion("GRADUATE_RATE =", value, "graduateRate");
            return (Criteria) this;
        }

        public Criteria andGraduateRateNotEqualTo(String value) {
            addCriterion("GRADUATE_RATE <>", value, "graduateRate");
            return (Criteria) this;
        }

        public Criteria andGraduateRateGreaterThan(String value) {
            addCriterion("GRADUATE_RATE >", value, "graduateRate");
            return (Criteria) this;
        }

        public Criteria andGraduateRateGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATE_RATE >=", value, "graduateRate");
            return (Criteria) this;
        }

        public Criteria andGraduateRateLessThan(String value) {
            addCriterion("GRADUATE_RATE <", value, "graduateRate");
            return (Criteria) this;
        }

        public Criteria andGraduateRateLessThanOrEqualTo(String value) {
            addCriterion("GRADUATE_RATE <=", value, "graduateRate");
            return (Criteria) this;
        }

        public Criteria andGraduateRateLike(String value) {
            addCriterion("GRADUATE_RATE like", value, "graduateRate");
            return (Criteria) this;
        }

        public Criteria andGraduateRateNotLike(String value) {
            addCriterion("GRADUATE_RATE not like", value, "graduateRate");
            return (Criteria) this;
        }

        public Criteria andGraduateRateIn(List<String> values) {
            addCriterion("GRADUATE_RATE in", values, "graduateRate");
            return (Criteria) this;
        }

        public Criteria andGraduateRateNotIn(List<String> values) {
            addCriterion("GRADUATE_RATE not in", values, "graduateRate");
            return (Criteria) this;
        }

        public Criteria andGraduateRateBetween(String value1, String value2) {
            addCriterion("GRADUATE_RATE between", value1, value2, "graduateRate");
            return (Criteria) this;
        }

        public Criteria andGraduateRateNotBetween(String value1, String value2) {
            addCriterion("GRADUATE_RATE not between", value1, value2, "graduateRate");
            return (Criteria) this;
        }

        public Criteria andTrainTotalIsNull() {
            addCriterion("TRAIN_TOTAL is null");
            return (Criteria) this;
        }

        public Criteria andTrainTotalIsNotNull() {
            addCriterion("TRAIN_TOTAL is not null");
            return (Criteria) this;
        }

        public Criteria andTrainTotalEqualTo(String value) {
            addCriterion("TRAIN_TOTAL =", value, "trainTotal");
            return (Criteria) this;
        }

        public Criteria andTrainTotalNotEqualTo(String value) {
            addCriterion("TRAIN_TOTAL <>", value, "trainTotal");
            return (Criteria) this;
        }

        public Criteria andTrainTotalGreaterThan(String value) {
            addCriterion("TRAIN_TOTAL >", value, "trainTotal");
            return (Criteria) this;
        }

        public Criteria andTrainTotalGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_TOTAL >=", value, "trainTotal");
            return (Criteria) this;
        }

        public Criteria andTrainTotalLessThan(String value) {
            addCriterion("TRAIN_TOTAL <", value, "trainTotal");
            return (Criteria) this;
        }

        public Criteria andTrainTotalLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_TOTAL <=", value, "trainTotal");
            return (Criteria) this;
        }

        public Criteria andTrainTotalLike(String value) {
            addCriterion("TRAIN_TOTAL like", value, "trainTotal");
            return (Criteria) this;
        }

        public Criteria andTrainTotalNotLike(String value) {
            addCriterion("TRAIN_TOTAL not like", value, "trainTotal");
            return (Criteria) this;
        }

        public Criteria andTrainTotalIn(List<String> values) {
            addCriterion("TRAIN_TOTAL in", values, "trainTotal");
            return (Criteria) this;
        }

        public Criteria andTrainTotalNotIn(List<String> values) {
            addCriterion("TRAIN_TOTAL not in", values, "trainTotal");
            return (Criteria) this;
        }

        public Criteria andTrainTotalBetween(String value1, String value2) {
            addCriterion("TRAIN_TOTAL between", value1, value2, "trainTotal");
            return (Criteria) this;
        }

        public Criteria andTrainTotalNotBetween(String value1, String value2) {
            addCriterion("TRAIN_TOTAL not between", value1, value2, "trainTotal");
            return (Criteria) this;
        }

        public Criteria andTrainUseIsNull() {
            addCriterion("TRAIN_USE is null");
            return (Criteria) this;
        }

        public Criteria andTrainUseIsNotNull() {
            addCriterion("TRAIN_USE is not null");
            return (Criteria) this;
        }

        public Criteria andTrainUseEqualTo(String value) {
            addCriterion("TRAIN_USE =", value, "trainUse");
            return (Criteria) this;
        }

        public Criteria andTrainUseNotEqualTo(String value) {
            addCriterion("TRAIN_USE <>", value, "trainUse");
            return (Criteria) this;
        }

        public Criteria andTrainUseGreaterThan(String value) {
            addCriterion("TRAIN_USE >", value, "trainUse");
            return (Criteria) this;
        }

        public Criteria andTrainUseGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_USE >=", value, "trainUse");
            return (Criteria) this;
        }

        public Criteria andTrainUseLessThan(String value) {
            addCriterion("TRAIN_USE <", value, "trainUse");
            return (Criteria) this;
        }

        public Criteria andTrainUseLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_USE <=", value, "trainUse");
            return (Criteria) this;
        }

        public Criteria andTrainUseLike(String value) {
            addCriterion("TRAIN_USE like", value, "trainUse");
            return (Criteria) this;
        }

        public Criteria andTrainUseNotLike(String value) {
            addCriterion("TRAIN_USE not like", value, "trainUse");
            return (Criteria) this;
        }

        public Criteria andTrainUseIn(List<String> values) {
            addCriterion("TRAIN_USE in", values, "trainUse");
            return (Criteria) this;
        }

        public Criteria andTrainUseNotIn(List<String> values) {
            addCriterion("TRAIN_USE not in", values, "trainUse");
            return (Criteria) this;
        }

        public Criteria andTrainUseBetween(String value1, String value2) {
            addCriterion("TRAIN_USE between", value1, value2, "trainUse");
            return (Criteria) this;
        }

        public Criteria andTrainUseNotBetween(String value1, String value2) {
            addCriterion("TRAIN_USE not between", value1, value2, "trainUse");
            return (Criteria) this;
        }

        public Criteria andTrainRateIsNull() {
            addCriterion("TRAIN_RATE is null");
            return (Criteria) this;
        }

        public Criteria andTrainRateIsNotNull() {
            addCriterion("TRAIN_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andTrainRateEqualTo(String value) {
            addCriterion("TRAIN_RATE =", value, "trainRate");
            return (Criteria) this;
        }

        public Criteria andTrainRateNotEqualTo(String value) {
            addCriterion("TRAIN_RATE <>", value, "trainRate");
            return (Criteria) this;
        }

        public Criteria andTrainRateGreaterThan(String value) {
            addCriterion("TRAIN_RATE >", value, "trainRate");
            return (Criteria) this;
        }

        public Criteria andTrainRateGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_RATE >=", value, "trainRate");
            return (Criteria) this;
        }

        public Criteria andTrainRateLessThan(String value) {
            addCriterion("TRAIN_RATE <", value, "trainRate");
            return (Criteria) this;
        }

        public Criteria andTrainRateLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_RATE <=", value, "trainRate");
            return (Criteria) this;
        }

        public Criteria andTrainRateLike(String value) {
            addCriterion("TRAIN_RATE like", value, "trainRate");
            return (Criteria) this;
        }

        public Criteria andTrainRateNotLike(String value) {
            addCriterion("TRAIN_RATE not like", value, "trainRate");
            return (Criteria) this;
        }

        public Criteria andTrainRateIn(List<String> values) {
            addCriterion("TRAIN_RATE in", values, "trainRate");
            return (Criteria) this;
        }

        public Criteria andTrainRateNotIn(List<String> values) {
            addCriterion("TRAIN_RATE not in", values, "trainRate");
            return (Criteria) this;
        }

        public Criteria andTrainRateBetween(String value1, String value2) {
            addCriterion("TRAIN_RATE between", value1, value2, "trainRate");
            return (Criteria) this;
        }

        public Criteria andTrainRateNotBetween(String value1, String value2) {
            addCriterion("TRAIN_RATE not between", value1, value2, "trainRate");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIsNull() {
            addCriterion("PROVINCE_ID is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIsNotNull() {
            addCriterion("PROVINCE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdEqualTo(String value) {
            addCriterion("PROVINCE_ID =", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotEqualTo(String value) {
            addCriterion("PROVINCE_ID <>", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdGreaterThan(String value) {
            addCriterion("PROVINCE_ID >", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROVINCE_ID >=", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdLessThan(String value) {
            addCriterion("PROVINCE_ID <", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdLessThanOrEqualTo(String value) {
            addCriterion("PROVINCE_ID <=", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdLike(String value) {
            addCriterion("PROVINCE_ID like", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotLike(String value) {
            addCriterion("PROVINCE_ID not like", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIn(List<String> values) {
            addCriterion("PROVINCE_ID in", values, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotIn(List<String> values) {
            addCriterion("PROVINCE_ID not in", values, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdBetween(String value1, String value2) {
            addCriterion("PROVINCE_ID between", value1, value2, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotBetween(String value1, String value2) {
            addCriterion("PROVINCE_ID not between", value1, value2, "provinceId");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNull() {
            addCriterion("CITY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNotNull() {
            addCriterion("CITY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCityIdEqualTo(String value) {
            addCriterion("CITY_ID =", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotEqualTo(String value) {
            addCriterion("CITY_ID <>", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThan(String value) {
            addCriterion("CITY_ID >", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThanOrEqualTo(String value) {
            addCriterion("CITY_ID >=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThan(String value) {
            addCriterion("CITY_ID <", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThanOrEqualTo(String value) {
            addCriterion("CITY_ID <=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLike(String value) {
            addCriterion("CITY_ID like", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotLike(String value) {
            addCriterion("CITY_ID not like", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdIn(List<String> values) {
            addCriterion("CITY_ID in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotIn(List<String> values) {
            addCriterion("CITY_ID not in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdBetween(String value1, String value2) {
            addCriterion("CITY_ID between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotBetween(String value1, String value2) {
            addCriterion("CITY_ID not between", value1, value2, "cityId");
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