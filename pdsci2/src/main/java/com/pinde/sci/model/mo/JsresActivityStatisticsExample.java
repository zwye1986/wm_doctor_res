package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class JsresActivityStatisticsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JsresActivityStatisticsExample() {
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

        public Criteria andActivityNumIsNull() {
            addCriterion("ACTIVITY_NUM is null");
            return (Criteria) this;
        }

        public Criteria andActivityNumIsNotNull() {
            addCriterion("ACTIVITY_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andActivityNumEqualTo(String value) {
            addCriterion("ACTIVITY_NUM =", value, "activityNum");
            return (Criteria) this;
        }

        public Criteria andActivityNumNotEqualTo(String value) {
            addCriterion("ACTIVITY_NUM <>", value, "activityNum");
            return (Criteria) this;
        }

        public Criteria andActivityNumGreaterThan(String value) {
            addCriterion("ACTIVITY_NUM >", value, "activityNum");
            return (Criteria) this;
        }

        public Criteria andActivityNumGreaterThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_NUM >=", value, "activityNum");
            return (Criteria) this;
        }

        public Criteria andActivityNumLessThan(String value) {
            addCriterion("ACTIVITY_NUM <", value, "activityNum");
            return (Criteria) this;
        }

        public Criteria andActivityNumLessThanOrEqualTo(String value) {
            addCriterion("ACTIVITY_NUM <=", value, "activityNum");
            return (Criteria) this;
        }

        public Criteria andActivityNumLike(String value) {
            addCriterion("ACTIVITY_NUM like", value, "activityNum");
            return (Criteria) this;
        }

        public Criteria andActivityNumNotLike(String value) {
            addCriterion("ACTIVITY_NUM not like", value, "activityNum");
            return (Criteria) this;
        }

        public Criteria andActivityNumIn(List<String> values) {
            addCriterion("ACTIVITY_NUM in", values, "activityNum");
            return (Criteria) this;
        }

        public Criteria andActivityNumNotIn(List<String> values) {
            addCriterion("ACTIVITY_NUM not in", values, "activityNum");
            return (Criteria) this;
        }

        public Criteria andActivityNumBetween(String value1, String value2) {
            addCriterion("ACTIVITY_NUM between", value1, value2, "activityNum");
            return (Criteria) this;
        }

        public Criteria andActivityNumNotBetween(String value1, String value2) {
            addCriterion("ACTIVITY_NUM not between", value1, value2, "activityNum");
            return (Criteria) this;
        }

        public Criteria andDoctorJointNumIsNull() {
            addCriterion("DOCTOR_JOINT_NUM is null");
            return (Criteria) this;
        }

        public Criteria andDoctorJointNumIsNotNull() {
            addCriterion("DOCTOR_JOINT_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorJointNumEqualTo(String value) {
            addCriterion("DOCTOR_JOINT_NUM =", value, "doctorJointNum");
            return (Criteria) this;
        }

        public Criteria andDoctorJointNumNotEqualTo(String value) {
            addCriterion("DOCTOR_JOINT_NUM <>", value, "doctorJointNum");
            return (Criteria) this;
        }

        public Criteria andDoctorJointNumGreaterThan(String value) {
            addCriterion("DOCTOR_JOINT_NUM >", value, "doctorJointNum");
            return (Criteria) this;
        }

        public Criteria andDoctorJointNumGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_JOINT_NUM >=", value, "doctorJointNum");
            return (Criteria) this;
        }

        public Criteria andDoctorJointNumLessThan(String value) {
            addCriterion("DOCTOR_JOINT_NUM <", value, "doctorJointNum");
            return (Criteria) this;
        }

        public Criteria andDoctorJointNumLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_JOINT_NUM <=", value, "doctorJointNum");
            return (Criteria) this;
        }

        public Criteria andDoctorJointNumLike(String value) {
            addCriterion("DOCTOR_JOINT_NUM like", value, "doctorJointNum");
            return (Criteria) this;
        }

        public Criteria andDoctorJointNumNotLike(String value) {
            addCriterion("DOCTOR_JOINT_NUM not like", value, "doctorJointNum");
            return (Criteria) this;
        }

        public Criteria andDoctorJointNumIn(List<String> values) {
            addCriterion("DOCTOR_JOINT_NUM in", values, "doctorJointNum");
            return (Criteria) this;
        }

        public Criteria andDoctorJointNumNotIn(List<String> values) {
            addCriterion("DOCTOR_JOINT_NUM not in", values, "doctorJointNum");
            return (Criteria) this;
        }

        public Criteria andDoctorJointNumBetween(String value1, String value2) {
            addCriterion("DOCTOR_JOINT_NUM between", value1, value2, "doctorJointNum");
            return (Criteria) this;
        }

        public Criteria andDoctorJointNumNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_JOINT_NUM not between", value1, value2, "doctorJointNum");
            return (Criteria) this;
        }

        public Criteria andAvgJointNumIsNull() {
            addCriterion("AVG_JOINT_NUM is null");
            return (Criteria) this;
        }

        public Criteria andAvgJointNumIsNotNull() {
            addCriterion("AVG_JOINT_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andAvgJointNumEqualTo(String value) {
            addCriterion("AVG_JOINT_NUM =", value, "avgJointNum");
            return (Criteria) this;
        }

        public Criteria andAvgJointNumNotEqualTo(String value) {
            addCriterion("AVG_JOINT_NUM <>", value, "avgJointNum");
            return (Criteria) this;
        }

        public Criteria andAvgJointNumGreaterThan(String value) {
            addCriterion("AVG_JOINT_NUM >", value, "avgJointNum");
            return (Criteria) this;
        }

        public Criteria andAvgJointNumGreaterThanOrEqualTo(String value) {
            addCriterion("AVG_JOINT_NUM >=", value, "avgJointNum");
            return (Criteria) this;
        }

        public Criteria andAvgJointNumLessThan(String value) {
            addCriterion("AVG_JOINT_NUM <", value, "avgJointNum");
            return (Criteria) this;
        }

        public Criteria andAvgJointNumLessThanOrEqualTo(String value) {
            addCriterion("AVG_JOINT_NUM <=", value, "avgJointNum");
            return (Criteria) this;
        }

        public Criteria andAvgJointNumLike(String value) {
            addCriterion("AVG_JOINT_NUM like", value, "avgJointNum");
            return (Criteria) this;
        }

        public Criteria andAvgJointNumNotLike(String value) {
            addCriterion("AVG_JOINT_NUM not like", value, "avgJointNum");
            return (Criteria) this;
        }

        public Criteria andAvgJointNumIn(List<String> values) {
            addCriterion("AVG_JOINT_NUM in", values, "avgJointNum");
            return (Criteria) this;
        }

        public Criteria andAvgJointNumNotIn(List<String> values) {
            addCriterion("AVG_JOINT_NUM not in", values, "avgJointNum");
            return (Criteria) this;
        }

        public Criteria andAvgJointNumBetween(String value1, String value2) {
            addCriterion("AVG_JOINT_NUM between", value1, value2, "avgJointNum");
            return (Criteria) this;
        }

        public Criteria andAvgJointNumNotBetween(String value1, String value2) {
            addCriterion("AVG_JOINT_NUM not between", value1, value2, "avgJointNum");
            return (Criteria) this;
        }

        public Criteria andAvgTimeIsNull() {
            addCriterion("AVG_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAvgTimeIsNotNull() {
            addCriterion("AVG_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAvgTimeEqualTo(String value) {
            addCriterion("AVG_TIME =", value, "avgTime");
            return (Criteria) this;
        }

        public Criteria andAvgTimeNotEqualTo(String value) {
            addCriterion("AVG_TIME <>", value, "avgTime");
            return (Criteria) this;
        }

        public Criteria andAvgTimeGreaterThan(String value) {
            addCriterion("AVG_TIME >", value, "avgTime");
            return (Criteria) this;
        }

        public Criteria andAvgTimeGreaterThanOrEqualTo(String value) {
            addCriterion("AVG_TIME >=", value, "avgTime");
            return (Criteria) this;
        }

        public Criteria andAvgTimeLessThan(String value) {
            addCriterion("AVG_TIME <", value, "avgTime");
            return (Criteria) this;
        }

        public Criteria andAvgTimeLessThanOrEqualTo(String value) {
            addCriterion("AVG_TIME <=", value, "avgTime");
            return (Criteria) this;
        }

        public Criteria andAvgTimeLike(String value) {
            addCriterion("AVG_TIME like", value, "avgTime");
            return (Criteria) this;
        }

        public Criteria andAvgTimeNotLike(String value) {
            addCriterion("AVG_TIME not like", value, "avgTime");
            return (Criteria) this;
        }

        public Criteria andAvgTimeIn(List<String> values) {
            addCriterion("AVG_TIME in", values, "avgTime");
            return (Criteria) this;
        }

        public Criteria andAvgTimeNotIn(List<String> values) {
            addCriterion("AVG_TIME not in", values, "avgTime");
            return (Criteria) this;
        }

        public Criteria andAvgTimeBetween(String value1, String value2) {
            addCriterion("AVG_TIME between", value1, value2, "avgTime");
            return (Criteria) this;
        }

        public Criteria andAvgTimeNotBetween(String value1, String value2) {
            addCriterion("AVG_TIME not between", value1, value2, "avgTime");
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