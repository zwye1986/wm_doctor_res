package com.pinde.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ResDoctorSchProcessEvalExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResDoctorSchProcessEvalExample() {
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

        public Criteria andStartTimeIsNull() {
            addCriterion("START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(String value) {
            addCriterion("START_TIME =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(String value) {
            addCriterion("START_TIME <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(String value) {
            addCriterion("START_TIME >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("START_TIME >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(String value) {
            addCriterion("START_TIME <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(String value) {
            addCriterion("START_TIME <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLike(String value) {
            addCriterion("START_TIME like", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotLike(String value) {
            addCriterion("START_TIME not like", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<String> values) {
            addCriterion("START_TIME in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<String> values) {
            addCriterion("START_TIME not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(String value1, String value2) {
            addCriterion("START_TIME between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(String value1, String value2) {
            addCriterion("START_TIME not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(String value) {
            addCriterion("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(String value) {
            addCriterion("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(String value) {
            addCriterion("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(String value) {
            addCriterion("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(String value) {
            addCriterion("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLike(String value) {
            addCriterion("END_TIME like", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotLike(String value) {
            addCriterion("END_TIME not like", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<String> values) {
            addCriterion("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<String> values) {
            addCriterion("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(String value1, String value2) {
            addCriterion("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(String value1, String value2) {
            addCriterion("END_TIME not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEvalUserFlowIsNull() {
            addCriterion("EVAL_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andEvalUserFlowIsNotNull() {
            addCriterion("EVAL_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andEvalUserFlowEqualTo(String value) {
            addCriterion("EVAL_USER_FLOW =", value, "evalUserFlow");
            return (Criteria) this;
        }

        public Criteria andEvalUserFlowNotEqualTo(String value) {
            addCriterion("EVAL_USER_FLOW <>", value, "evalUserFlow");
            return (Criteria) this;
        }

        public Criteria andEvalUserFlowGreaterThan(String value) {
            addCriterion("EVAL_USER_FLOW >", value, "evalUserFlow");
            return (Criteria) this;
        }

        public Criteria andEvalUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("EVAL_USER_FLOW >=", value, "evalUserFlow");
            return (Criteria) this;
        }

        public Criteria andEvalUserFlowLessThan(String value) {
            addCriterion("EVAL_USER_FLOW <", value, "evalUserFlow");
            return (Criteria) this;
        }

        public Criteria andEvalUserFlowLessThanOrEqualTo(String value) {
            addCriterion("EVAL_USER_FLOW <=", value, "evalUserFlow");
            return (Criteria) this;
        }

        public Criteria andEvalUserFlowLike(String value) {
            addCriterion("EVAL_USER_FLOW like", value, "evalUserFlow");
            return (Criteria) this;
        }

        public Criteria andEvalUserFlowNotLike(String value) {
            addCriterion("EVAL_USER_FLOW not like", value, "evalUserFlow");
            return (Criteria) this;
        }

        public Criteria andEvalUserFlowIn(List<String> values) {
            addCriterion("EVAL_USER_FLOW in", values, "evalUserFlow");
            return (Criteria) this;
        }

        public Criteria andEvalUserFlowNotIn(List<String> values) {
            addCriterion("EVAL_USER_FLOW not in", values, "evalUserFlow");
            return (Criteria) this;
        }

        public Criteria andEvalUserFlowBetween(String value1, String value2) {
            addCriterion("EVAL_USER_FLOW between", value1, value2, "evalUserFlow");
            return (Criteria) this;
        }

        public Criteria andEvalUserFlowNotBetween(String value1, String value2) {
            addCriterion("EVAL_USER_FLOW not between", value1, value2, "evalUserFlow");
            return (Criteria) this;
        }

        public Criteria andEvalUserNameIsNull() {
            addCriterion("EVAL_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andEvalUserNameIsNotNull() {
            addCriterion("EVAL_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andEvalUserNameEqualTo(String value) {
            addCriterion("EVAL_USER_NAME =", value, "evalUserName");
            return (Criteria) this;
        }

        public Criteria andEvalUserNameNotEqualTo(String value) {
            addCriterion("EVAL_USER_NAME <>", value, "evalUserName");
            return (Criteria) this;
        }

        public Criteria andEvalUserNameGreaterThan(String value) {
            addCriterion("EVAL_USER_NAME >", value, "evalUserName");
            return (Criteria) this;
        }

        public Criteria andEvalUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("EVAL_USER_NAME >=", value, "evalUserName");
            return (Criteria) this;
        }

        public Criteria andEvalUserNameLessThan(String value) {
            addCriterion("EVAL_USER_NAME <", value, "evalUserName");
            return (Criteria) this;
        }

        public Criteria andEvalUserNameLessThanOrEqualTo(String value) {
            addCriterion("EVAL_USER_NAME <=", value, "evalUserName");
            return (Criteria) this;
        }

        public Criteria andEvalUserNameLike(String value) {
            addCriterion("EVAL_USER_NAME like", value, "evalUserName");
            return (Criteria) this;
        }

        public Criteria andEvalUserNameNotLike(String value) {
            addCriterion("EVAL_USER_NAME not like", value, "evalUserName");
            return (Criteria) this;
        }

        public Criteria andEvalUserNameIn(List<String> values) {
            addCriterion("EVAL_USER_NAME in", values, "evalUserName");
            return (Criteria) this;
        }

        public Criteria andEvalUserNameNotIn(List<String> values) {
            addCriterion("EVAL_USER_NAME not in", values, "evalUserName");
            return (Criteria) this;
        }

        public Criteria andEvalUserNameBetween(String value1, String value2) {
            addCriterion("EVAL_USER_NAME between", value1, value2, "evalUserName");
            return (Criteria) this;
        }

        public Criteria andEvalUserNameNotBetween(String value1, String value2) {
            addCriterion("EVAL_USER_NAME not between", value1, value2, "evalUserName");
            return (Criteria) this;
        }

        public Criteria andEvalMonthIsNull() {
            addCriterion("EVAL_MONTH is null");
            return (Criteria) this;
        }

        public Criteria andEvalMonthIsNotNull() {
            addCriterion("EVAL_MONTH is not null");
            return (Criteria) this;
        }

        public Criteria andEvalMonthEqualTo(String value) {
            addCriterion("EVAL_MONTH =", value, "evalMonth");
            return (Criteria) this;
        }

        public Criteria andEvalMonthNotEqualTo(String value) {
            addCriterion("EVAL_MONTH <>", value, "evalMonth");
            return (Criteria) this;
        }

        public Criteria andEvalMonthGreaterThan(String value) {
            addCriterion("EVAL_MONTH >", value, "evalMonth");
            return (Criteria) this;
        }

        public Criteria andEvalMonthGreaterThanOrEqualTo(String value) {
            addCriterion("EVAL_MONTH >=", value, "evalMonth");
            return (Criteria) this;
        }

        public Criteria andEvalMonthLessThan(String value) {
            addCriterion("EVAL_MONTH <", value, "evalMonth");
            return (Criteria) this;
        }

        public Criteria andEvalMonthLessThanOrEqualTo(String value) {
            addCriterion("EVAL_MONTH <=", value, "evalMonth");
            return (Criteria) this;
        }

        public Criteria andEvalMonthLike(String value) {
            addCriterion("EVAL_MONTH like", value, "evalMonth");
            return (Criteria) this;
        }

        public Criteria andEvalMonthNotLike(String value) {
            addCriterion("EVAL_MONTH not like", value, "evalMonth");
            return (Criteria) this;
        }

        public Criteria andEvalMonthIn(List<String> values) {
            addCriterion("EVAL_MONTH in", values, "evalMonth");
            return (Criteria) this;
        }

        public Criteria andEvalMonthNotIn(List<String> values) {
            addCriterion("EVAL_MONTH not in", values, "evalMonth");
            return (Criteria) this;
        }

        public Criteria andEvalMonthBetween(String value1, String value2) {
            addCriterion("EVAL_MONTH between", value1, value2, "evalMonth");
            return (Criteria) this;
        }

        public Criteria andEvalMonthNotBetween(String value1, String value2) {
            addCriterion("EVAL_MONTH not between", value1, value2, "evalMonth");
            return (Criteria) this;
        }

        public Criteria andEvalTimeIsNull() {
            addCriterion("EVAL_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEvalTimeIsNotNull() {
            addCriterion("EVAL_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEvalTimeEqualTo(String value) {
            addCriterion("EVAL_TIME =", value, "evalTime");
            return (Criteria) this;
        }

        public Criteria andEvalTimeNotEqualTo(String value) {
            addCriterion("EVAL_TIME <>", value, "evalTime");
            return (Criteria) this;
        }

        public Criteria andEvalTimeGreaterThan(String value) {
            addCriterion("EVAL_TIME >", value, "evalTime");
            return (Criteria) this;
        }

        public Criteria andEvalTimeGreaterThanOrEqualTo(String value) {
            addCriterion("EVAL_TIME >=", value, "evalTime");
            return (Criteria) this;
        }

        public Criteria andEvalTimeLessThan(String value) {
            addCriterion("EVAL_TIME <", value, "evalTime");
            return (Criteria) this;
        }

        public Criteria andEvalTimeLessThanOrEqualTo(String value) {
            addCriterion("EVAL_TIME <=", value, "evalTime");
            return (Criteria) this;
        }

        public Criteria andEvalTimeLike(String value) {
            addCriterion("EVAL_TIME like", value, "evalTime");
            return (Criteria) this;
        }

        public Criteria andEvalTimeNotLike(String value) {
            addCriterion("EVAL_TIME not like", value, "evalTime");
            return (Criteria) this;
        }

        public Criteria andEvalTimeIn(List<String> values) {
            addCriterion("EVAL_TIME in", values, "evalTime");
            return (Criteria) this;
        }

        public Criteria andEvalTimeNotIn(List<String> values) {
            addCriterion("EVAL_TIME not in", values, "evalTime");
            return (Criteria) this;
        }

        public Criteria andEvalTimeBetween(String value1, String value2) {
            addCriterion("EVAL_TIME between", value1, value2, "evalTime");
            return (Criteria) this;
        }

        public Criteria andEvalTimeNotBetween(String value1, String value2) {
            addCriterion("EVAL_TIME not between", value1, value2, "evalTime");
            return (Criteria) this;
        }

        public Criteria andIsFormIsNull() {
            addCriterion("IS_FORM is null");
            return (Criteria) this;
        }

        public Criteria andIsFormIsNotNull() {
            addCriterion("IS_FORM is not null");
            return (Criteria) this;
        }

        public Criteria andIsFormEqualTo(String value) {
            addCriterion("IS_FORM =", value, "isForm");
            return (Criteria) this;
        }

        public Criteria andIsFormNotEqualTo(String value) {
            addCriterion("IS_FORM <>", value, "isForm");
            return (Criteria) this;
        }

        public Criteria andIsFormGreaterThan(String value) {
            addCriterion("IS_FORM >", value, "isForm");
            return (Criteria) this;
        }

        public Criteria andIsFormGreaterThanOrEqualTo(String value) {
            addCriterion("IS_FORM >=", value, "isForm");
            return (Criteria) this;
        }

        public Criteria andIsFormLessThan(String value) {
            addCriterion("IS_FORM <", value, "isForm");
            return (Criteria) this;
        }

        public Criteria andIsFormLessThanOrEqualTo(String value) {
            addCriterion("IS_FORM <=", value, "isForm");
            return (Criteria) this;
        }

        public Criteria andIsFormLike(String value) {
            addCriterion("IS_FORM like", value, "isForm");
            return (Criteria) this;
        }

        public Criteria andIsFormNotLike(String value) {
            addCriterion("IS_FORM not like", value, "isForm");
            return (Criteria) this;
        }

        public Criteria andIsFormIn(List<String> values) {
            addCriterion("IS_FORM in", values, "isForm");
            return (Criteria) this;
        }

        public Criteria andIsFormNotIn(List<String> values) {
            addCriterion("IS_FORM not in", values, "isForm");
            return (Criteria) this;
        }

        public Criteria andIsFormBetween(String value1, String value2) {
            addCriterion("IS_FORM between", value1, value2, "isForm");
            return (Criteria) this;
        }

        public Criteria andIsFormNotBetween(String value1, String value2) {
            addCriterion("IS_FORM not between", value1, value2, "isForm");
            return (Criteria) this;
        }

        public Criteria andAttendanceIsNull() {
            addCriterion("ATTENDANCE is null");
            return (Criteria) this;
        }

        public Criteria andAttendanceIsNotNull() {
            addCriterion("ATTENDANCE is not null");
            return (Criteria) this;
        }

        public Criteria andAttendanceEqualTo(BigDecimal value) {
            addCriterion("ATTENDANCE =", value, "attendance");
            return (Criteria) this;
        }

        public Criteria andAttendanceNotEqualTo(BigDecimal value) {
            addCriterion("ATTENDANCE <>", value, "attendance");
            return (Criteria) this;
        }

        public Criteria andAttendanceGreaterThan(BigDecimal value) {
            addCriterion("ATTENDANCE >", value, "attendance");
            return (Criteria) this;
        }

        public Criteria andAttendanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ATTENDANCE >=", value, "attendance");
            return (Criteria) this;
        }

        public Criteria andAttendanceLessThan(BigDecimal value) {
            addCriterion("ATTENDANCE <", value, "attendance");
            return (Criteria) this;
        }

        public Criteria andAttendanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ATTENDANCE <=", value, "attendance");
            return (Criteria) this;
        }

        public Criteria andAttendanceIn(List<BigDecimal> values) {
            addCriterion("ATTENDANCE in", values, "attendance");
            return (Criteria) this;
        }

        public Criteria andAttendanceNotIn(List<BigDecimal> values) {
            addCriterion("ATTENDANCE not in", values, "attendance");
            return (Criteria) this;
        }

        public Criteria andAttendanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ATTENDANCE between", value1, value2, "attendance");
            return (Criteria) this;
        }

        public Criteria andAttendanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ATTENDANCE not between", value1, value2, "attendance");
            return (Criteria) this;
        }

        public Criteria andLeaveIsNull() {
            addCriterion("LEAVE is null");
            return (Criteria) this;
        }

        public Criteria andLeaveIsNotNull() {
            addCriterion("LEAVE is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveEqualTo(BigDecimal value) {
            addCriterion("LEAVE =", value, "leave");
            return (Criteria) this;
        }

        public Criteria andLeaveNotEqualTo(BigDecimal value) {
            addCriterion("LEAVE <>", value, "leave");
            return (Criteria) this;
        }

        public Criteria andLeaveGreaterThan(BigDecimal value) {
            addCriterion("LEAVE >", value, "leave");
            return (Criteria) this;
        }

        public Criteria andLeaveGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LEAVE >=", value, "leave");
            return (Criteria) this;
        }

        public Criteria andLeaveLessThan(BigDecimal value) {
            addCriterion("LEAVE <", value, "leave");
            return (Criteria) this;
        }

        public Criteria andLeaveLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LEAVE <=", value, "leave");
            return (Criteria) this;
        }

        public Criteria andLeaveIn(List<BigDecimal> values) {
            addCriterion("LEAVE in", values, "leave");
            return (Criteria) this;
        }

        public Criteria andLeaveNotIn(List<BigDecimal> values) {
            addCriterion("LEAVE not in", values, "leave");
            return (Criteria) this;
        }

        public Criteria andLeaveBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LEAVE between", value1, value2, "leave");
            return (Criteria) this;
        }

        public Criteria andLeaveNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LEAVE not between", value1, value2, "leave");
            return (Criteria) this;
        }

        public Criteria andAbsenteeismIsNull() {
            addCriterion("ABSENTEEISM is null");
            return (Criteria) this;
        }

        public Criteria andAbsenteeismIsNotNull() {
            addCriterion("ABSENTEEISM is not null");
            return (Criteria) this;
        }

        public Criteria andAbsenteeismEqualTo(BigDecimal value) {
            addCriterion("ABSENTEEISM =", value, "absenteeism");
            return (Criteria) this;
        }

        public Criteria andAbsenteeismNotEqualTo(BigDecimal value) {
            addCriterion("ABSENTEEISM <>", value, "absenteeism");
            return (Criteria) this;
        }

        public Criteria andAbsenteeismGreaterThan(BigDecimal value) {
            addCriterion("ABSENTEEISM >", value, "absenteeism");
            return (Criteria) this;
        }

        public Criteria andAbsenteeismGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ABSENTEEISM >=", value, "absenteeism");
            return (Criteria) this;
        }

        public Criteria andAbsenteeismLessThan(BigDecimal value) {
            addCriterion("ABSENTEEISM <", value, "absenteeism");
            return (Criteria) this;
        }

        public Criteria andAbsenteeismLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ABSENTEEISM <=", value, "absenteeism");
            return (Criteria) this;
        }

        public Criteria andAbsenteeismIn(List<BigDecimal> values) {
            addCriterion("ABSENTEEISM in", values, "absenteeism");
            return (Criteria) this;
        }

        public Criteria andAbsenteeismNotIn(List<BigDecimal> values) {
            addCriterion("ABSENTEEISM not in", values, "absenteeism");
            return (Criteria) this;
        }

        public Criteria andAbsenteeismBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ABSENTEEISM between", value1, value2, "absenteeism");
            return (Criteria) this;
        }

        public Criteria andAbsenteeismNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ABSENTEEISM not between", value1, value2, "absenteeism");
            return (Criteria) this;
        }

        public Criteria andEvalContentIsNull() {
            addCriterion("EVAL_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andEvalContentIsNotNull() {
            addCriterion("EVAL_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andEvalContentEqualTo(String value) {
            addCriterion("EVAL_CONTENT =", value, "evalContent");
            return (Criteria) this;
        }

        public Criteria andEvalContentNotEqualTo(String value) {
            addCriterion("EVAL_CONTENT <>", value, "evalContent");
            return (Criteria) this;
        }

        public Criteria andEvalContentGreaterThan(String value) {
            addCriterion("EVAL_CONTENT >", value, "evalContent");
            return (Criteria) this;
        }

        public Criteria andEvalContentGreaterThanOrEqualTo(String value) {
            addCriterion("EVAL_CONTENT >=", value, "evalContent");
            return (Criteria) this;
        }

        public Criteria andEvalContentLessThan(String value) {
            addCriterion("EVAL_CONTENT <", value, "evalContent");
            return (Criteria) this;
        }

        public Criteria andEvalContentLessThanOrEqualTo(String value) {
            addCriterion("EVAL_CONTENT <=", value, "evalContent");
            return (Criteria) this;
        }

        public Criteria andEvalContentLike(String value) {
            addCriterion("EVAL_CONTENT like", value, "evalContent");
            return (Criteria) this;
        }

        public Criteria andEvalContentNotLike(String value) {
            addCriterion("EVAL_CONTENT not like", value, "evalContent");
            return (Criteria) this;
        }

        public Criteria andEvalContentIn(List<String> values) {
            addCriterion("EVAL_CONTENT in", values, "evalContent");
            return (Criteria) this;
        }

        public Criteria andEvalContentNotIn(List<String> values) {
            addCriterion("EVAL_CONTENT not in", values, "evalContent");
            return (Criteria) this;
        }

        public Criteria andEvalContentBetween(String value1, String value2) {
            addCriterion("EVAL_CONTENT between", value1, value2, "evalContent");
            return (Criteria) this;
        }

        public Criteria andEvalContentNotBetween(String value1, String value2) {
            addCriterion("EVAL_CONTENT not between", value1, value2, "evalContent");
            return (Criteria) this;
        }

        public Criteria andEvalScoreIsNull() {
            addCriterion("EVAL_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andEvalScoreIsNotNull() {
            addCriterion("EVAL_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andEvalScoreEqualTo(BigDecimal value) {
            addCriterion("EVAL_SCORE =", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreNotEqualTo(BigDecimal value) {
            addCriterion("EVAL_SCORE <>", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreGreaterThan(BigDecimal value) {
            addCriterion("EVAL_SCORE >", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("EVAL_SCORE >=", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreLessThan(BigDecimal value) {
            addCriterion("EVAL_SCORE <", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreLessThanOrEqualTo(BigDecimal value) {
            addCriterion("EVAL_SCORE <=", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreIn(List<BigDecimal> values) {
            addCriterion("EVAL_SCORE in", values, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreNotIn(List<BigDecimal> values) {
            addCriterion("EVAL_SCORE not in", values, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("EVAL_SCORE between", value1, value2, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("EVAL_SCORE not between", value1, value2, "evalScore");
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