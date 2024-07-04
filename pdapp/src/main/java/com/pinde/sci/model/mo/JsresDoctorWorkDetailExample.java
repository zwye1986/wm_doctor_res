package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class JsresDoctorWorkDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JsresDoctorWorkDetailExample() {
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

        public Criteria andDetailFlowIsNull() {
            addCriterion("DETAIL_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDetailFlowIsNotNull() {
            addCriterion("DETAIL_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDetailFlowEqualTo(String value) {
            addCriterion("DETAIL_FLOW =", value, "detailFlow");
            return (Criteria) this;
        }

        public Criteria andDetailFlowNotEqualTo(String value) {
            addCriterion("DETAIL_FLOW <>", value, "detailFlow");
            return (Criteria) this;
        }

        public Criteria andDetailFlowGreaterThan(String value) {
            addCriterion("DETAIL_FLOW >", value, "detailFlow");
            return (Criteria) this;
        }

        public Criteria andDetailFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DETAIL_FLOW >=", value, "detailFlow");
            return (Criteria) this;
        }

        public Criteria andDetailFlowLessThan(String value) {
            addCriterion("DETAIL_FLOW <", value, "detailFlow");
            return (Criteria) this;
        }

        public Criteria andDetailFlowLessThanOrEqualTo(String value) {
            addCriterion("DETAIL_FLOW <=", value, "detailFlow");
            return (Criteria) this;
        }

        public Criteria andDetailFlowLike(String value) {
            addCriterion("DETAIL_FLOW like", value, "detailFlow");
            return (Criteria) this;
        }

        public Criteria andDetailFlowNotLike(String value) {
            addCriterion("DETAIL_FLOW not like", value, "detailFlow");
            return (Criteria) this;
        }

        public Criteria andDetailFlowIn(List<String> values) {
            addCriterion("DETAIL_FLOW in", values, "detailFlow");
            return (Criteria) this;
        }

        public Criteria andDetailFlowNotIn(List<String> values) {
            addCriterion("DETAIL_FLOW not in", values, "detailFlow");
            return (Criteria) this;
        }

        public Criteria andDetailFlowBetween(String value1, String value2) {
            addCriterion("DETAIL_FLOW between", value1, value2, "detailFlow");
            return (Criteria) this;
        }

        public Criteria andDetailFlowNotBetween(String value1, String value2) {
            addCriterion("DETAIL_FLOW not between", value1, value2, "detailFlow");
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

        public Criteria andRotationFlowIsNull() {
            addCriterion("ROTATION_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRotationFlowIsNotNull() {
            addCriterion("ROTATION_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRotationFlowEqualTo(String value) {
            addCriterion("ROTATION_FLOW =", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowNotEqualTo(String value) {
            addCriterion("ROTATION_FLOW <>", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowGreaterThan(String value) {
            addCriterion("ROTATION_FLOW >", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ROTATION_FLOW >=", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowLessThan(String value) {
            addCriterion("ROTATION_FLOW <", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowLessThanOrEqualTo(String value) {
            addCriterion("ROTATION_FLOW <=", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowLike(String value) {
            addCriterion("ROTATION_FLOW like", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowNotLike(String value) {
            addCriterion("ROTATION_FLOW not like", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowIn(List<String> values) {
            addCriterion("ROTATION_FLOW in", values, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowNotIn(List<String> values) {
            addCriterion("ROTATION_FLOW not in", values, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowBetween(String value1, String value2) {
            addCriterion("ROTATION_FLOW between", value1, value2, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowNotBetween(String value1, String value2) {
            addCriterion("ROTATION_FLOW not between", value1, value2, "rotationFlow");
            return (Criteria) this;
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

        public Criteria andStandardDeptIdIsNull() {
            addCriterion("STANDARD_DEPT_ID is null");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdIsNotNull() {
            addCriterion("STANDARD_DEPT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdEqualTo(String value) {
            addCriterion("STANDARD_DEPT_ID =", value, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdNotEqualTo(String value) {
            addCriterion("STANDARD_DEPT_ID <>", value, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdGreaterThan(String value) {
            addCriterion("STANDARD_DEPT_ID >", value, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdGreaterThanOrEqualTo(String value) {
            addCriterion("STANDARD_DEPT_ID >=", value, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdLessThan(String value) {
            addCriterion("STANDARD_DEPT_ID <", value, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdLessThanOrEqualTo(String value) {
            addCriterion("STANDARD_DEPT_ID <=", value, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdLike(String value) {
            addCriterion("STANDARD_DEPT_ID like", value, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdNotLike(String value) {
            addCriterion("STANDARD_DEPT_ID not like", value, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdIn(List<String> values) {
            addCriterion("STANDARD_DEPT_ID in", values, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdNotIn(List<String> values) {
            addCriterion("STANDARD_DEPT_ID not in", values, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdBetween(String value1, String value2) {
            addCriterion("STANDARD_DEPT_ID between", value1, value2, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptIdNotBetween(String value1, String value2) {
            addCriterion("STANDARD_DEPT_ID not between", value1, value2, "standardDeptId");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameIsNull() {
            addCriterion("STANDARD_DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameIsNotNull() {
            addCriterion("STANDARD_DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameEqualTo(String value) {
            addCriterion("STANDARD_DEPT_NAME =", value, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameNotEqualTo(String value) {
            addCriterion("STANDARD_DEPT_NAME <>", value, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameGreaterThan(String value) {
            addCriterion("STANDARD_DEPT_NAME >", value, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("STANDARD_DEPT_NAME >=", value, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameLessThan(String value) {
            addCriterion("STANDARD_DEPT_NAME <", value, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameLessThanOrEqualTo(String value) {
            addCriterion("STANDARD_DEPT_NAME <=", value, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameLike(String value) {
            addCriterion("STANDARD_DEPT_NAME like", value, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameNotLike(String value) {
            addCriterion("STANDARD_DEPT_NAME not like", value, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameIn(List<String> values) {
            addCriterion("STANDARD_DEPT_NAME in", values, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameNotIn(List<String> values) {
            addCriterion("STANDARD_DEPT_NAME not in", values, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameBetween(String value1, String value2) {
            addCriterion("STANDARD_DEPT_NAME between", value1, value2, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andStandardDeptNameNotBetween(String value1, String value2) {
            addCriterion("STANDARD_DEPT_NAME not between", value1, value2, "standardDeptName");
            return (Criteria) this;
        }

        public Criteria andSchMonthIsNull() {
            addCriterion("SCH_MONTH is null");
            return (Criteria) this;
        }

        public Criteria andSchMonthIsNotNull() {
            addCriterion("SCH_MONTH is not null");
            return (Criteria) this;
        }

        public Criteria andSchMonthEqualTo(String value) {
            addCriterion("SCH_MONTH =", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthNotEqualTo(String value) {
            addCriterion("SCH_MONTH <>", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthGreaterThan(String value) {
            addCriterion("SCH_MONTH >", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_MONTH >=", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthLessThan(String value) {
            addCriterion("SCH_MONTH <", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthLessThanOrEqualTo(String value) {
            addCriterion("SCH_MONTH <=", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthLike(String value) {
            addCriterion("SCH_MONTH like", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthNotLike(String value) {
            addCriterion("SCH_MONTH not like", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthIn(List<String> values) {
            addCriterion("SCH_MONTH in", values, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthNotIn(List<String> values) {
            addCriterion("SCH_MONTH not in", values, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthBetween(String value1, String value2) {
            addCriterion("SCH_MONTH between", value1, value2, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthNotBetween(String value1, String value2) {
            addCriterion("SCH_MONTH not between", value1, value2, "schMonth");
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

        public Criteria andFMonthIsNull() {
            addCriterion("F_MONTH is null");
            return (Criteria) this;
        }

        public Criteria andFMonthIsNotNull() {
            addCriterion("F_MONTH is not null");
            return (Criteria) this;
        }

        public Criteria andFMonthEqualTo(String value) {
            addCriterion("F_MONTH =", value, "fMonth");
            return (Criteria) this;
        }

        public Criteria andFMonthNotEqualTo(String value) {
            addCriterion("F_MONTH <>", value, "fMonth");
            return (Criteria) this;
        }

        public Criteria andFMonthGreaterThan(String value) {
            addCriterion("F_MONTH >", value, "fMonth");
            return (Criteria) this;
        }

        public Criteria andFMonthGreaterThanOrEqualTo(String value) {
            addCriterion("F_MONTH >=", value, "fMonth");
            return (Criteria) this;
        }

        public Criteria andFMonthLessThan(String value) {
            addCriterion("F_MONTH <", value, "fMonth");
            return (Criteria) this;
        }

        public Criteria andFMonthLessThanOrEqualTo(String value) {
            addCriterion("F_MONTH <=", value, "fMonth");
            return (Criteria) this;
        }

        public Criteria andFMonthLike(String value) {
            addCriterion("F_MONTH like", value, "fMonth");
            return (Criteria) this;
        }

        public Criteria andFMonthNotLike(String value) {
            addCriterion("F_MONTH not like", value, "fMonth");
            return (Criteria) this;
        }

        public Criteria andFMonthIn(List<String> values) {
            addCriterion("F_MONTH in", values, "fMonth");
            return (Criteria) this;
        }

        public Criteria andFMonthNotIn(List<String> values) {
            addCriterion("F_MONTH not in", values, "fMonth");
            return (Criteria) this;
        }

        public Criteria andFMonthBetween(String value1, String value2) {
            addCriterion("F_MONTH between", value1, value2, "fMonth");
            return (Criteria) this;
        }

        public Criteria andFMonthNotBetween(String value1, String value2) {
            addCriterion("F_MONTH not between", value1, value2, "fMonth");
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

        public Criteria andSchFlagIsNull() {
            addCriterion("SCH_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andSchFlagIsNotNull() {
            addCriterion("SCH_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andSchFlagEqualTo(String value) {
            addCriterion("SCH_FLAG =", value, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagNotEqualTo(String value) {
            addCriterion("SCH_FLAG <>", value, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagGreaterThan(String value) {
            addCriterion("SCH_FLAG >", value, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_FLAG >=", value, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagLessThan(String value) {
            addCriterion("SCH_FLAG <", value, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagLessThanOrEqualTo(String value) {
            addCriterion("SCH_FLAG <=", value, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagLike(String value) {
            addCriterion("SCH_FLAG like", value, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagNotLike(String value) {
            addCriterion("SCH_FLAG not like", value, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagIn(List<String> values) {
            addCriterion("SCH_FLAG in", values, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagNotIn(List<String> values) {
            addCriterion("SCH_FLAG not in", values, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagBetween(String value1, String value2) {
            addCriterion("SCH_FLAG between", value1, value2, "schFlag");
            return (Criteria) this;
        }

        public Criteria andSchFlagNotBetween(String value1, String value2) {
            addCriterion("SCH_FLAG not between", value1, value2, "schFlag");
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

        public Criteria andReqNumIsNull() {
            addCriterion("REQ_NUM is null");
            return (Criteria) this;
        }

        public Criteria andReqNumIsNotNull() {
            addCriterion("REQ_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andReqNumEqualTo(String value) {
            addCriterion("REQ_NUM =", value, "reqNum");
            return (Criteria) this;
        }

        public Criteria andReqNumNotEqualTo(String value) {
            addCriterion("REQ_NUM <>", value, "reqNum");
            return (Criteria) this;
        }

        public Criteria andReqNumGreaterThan(String value) {
            addCriterion("REQ_NUM >", value, "reqNum");
            return (Criteria) this;
        }

        public Criteria andReqNumGreaterThanOrEqualTo(String value) {
            addCriterion("REQ_NUM >=", value, "reqNum");
            return (Criteria) this;
        }

        public Criteria andReqNumLessThan(String value) {
            addCriterion("REQ_NUM <", value, "reqNum");
            return (Criteria) this;
        }

        public Criteria andReqNumLessThanOrEqualTo(String value) {
            addCriterion("REQ_NUM <=", value, "reqNum");
            return (Criteria) this;
        }

        public Criteria andReqNumLike(String value) {
            addCriterion("REQ_NUM like", value, "reqNum");
            return (Criteria) this;
        }

        public Criteria andReqNumNotLike(String value) {
            addCriterion("REQ_NUM not like", value, "reqNum");
            return (Criteria) this;
        }

        public Criteria andReqNumIn(List<String> values) {
            addCriterion("REQ_NUM in", values, "reqNum");
            return (Criteria) this;
        }

        public Criteria andReqNumNotIn(List<String> values) {
            addCriterion("REQ_NUM not in", values, "reqNum");
            return (Criteria) this;
        }

        public Criteria andReqNumBetween(String value1, String value2) {
            addCriterion("REQ_NUM between", value1, value2, "reqNum");
            return (Criteria) this;
        }

        public Criteria andReqNumNotBetween(String value1, String value2) {
            addCriterion("REQ_NUM not between", value1, value2, "reqNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumIsNull() {
            addCriterion("COMPLETE_NUM is null");
            return (Criteria) this;
        }

        public Criteria andCompleteNumIsNotNull() {
            addCriterion("COMPLETE_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteNumEqualTo(String value) {
            addCriterion("COMPLETE_NUM =", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumNotEqualTo(String value) {
            addCriterion("COMPLETE_NUM <>", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumGreaterThan(String value) {
            addCriterion("COMPLETE_NUM >", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumGreaterThanOrEqualTo(String value) {
            addCriterion("COMPLETE_NUM >=", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumLessThan(String value) {
            addCriterion("COMPLETE_NUM <", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumLessThanOrEqualTo(String value) {
            addCriterion("COMPLETE_NUM <=", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumLike(String value) {
            addCriterion("COMPLETE_NUM like", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumNotLike(String value) {
            addCriterion("COMPLETE_NUM not like", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumIn(List<String> values) {
            addCriterion("COMPLETE_NUM in", values, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumNotIn(List<String> values) {
            addCriterion("COMPLETE_NUM not in", values, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumBetween(String value1, String value2) {
            addCriterion("COMPLETE_NUM between", value1, value2, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumNotBetween(String value1, String value2) {
            addCriterion("COMPLETE_NUM not between", value1, value2, "completeNum");
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

        public Criteria andAuditNumEqualTo(String value) {
            addCriterion("AUDIT_NUM =", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumNotEqualTo(String value) {
            addCriterion("AUDIT_NUM <>", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumGreaterThan(String value) {
            addCriterion("AUDIT_NUM >", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_NUM >=", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumLessThan(String value) {
            addCriterion("AUDIT_NUM <", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_NUM <=", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumLike(String value) {
            addCriterion("AUDIT_NUM like", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumNotLike(String value) {
            addCriterion("AUDIT_NUM not like", value, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumIn(List<String> values) {
            addCriterion("AUDIT_NUM in", values, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumNotIn(List<String> values) {
            addCriterion("AUDIT_NUM not in", values, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumBetween(String value1, String value2) {
            addCriterion("AUDIT_NUM between", value1, value2, "auditNum");
            return (Criteria) this;
        }

        public Criteria andAuditNumNotBetween(String value1, String value2) {
            addCriterion("AUDIT_NUM not between", value1, value2, "auditNum");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumIsNull() {
            addCriterion("EVALUATION_NUM is null");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumIsNotNull() {
            addCriterion("EVALUATION_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumEqualTo(String value) {
            addCriterion("EVALUATION_NUM =", value, "evaluationNum");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumNotEqualTo(String value) {
            addCriterion("EVALUATION_NUM <>", value, "evaluationNum");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumGreaterThan(String value) {
            addCriterion("EVALUATION_NUM >", value, "evaluationNum");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumGreaterThanOrEqualTo(String value) {
            addCriterion("EVALUATION_NUM >=", value, "evaluationNum");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumLessThan(String value) {
            addCriterion("EVALUATION_NUM <", value, "evaluationNum");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumLessThanOrEqualTo(String value) {
            addCriterion("EVALUATION_NUM <=", value, "evaluationNum");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumLike(String value) {
            addCriterion("EVALUATION_NUM like", value, "evaluationNum");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumNotLike(String value) {
            addCriterion("EVALUATION_NUM not like", value, "evaluationNum");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumIn(List<String> values) {
            addCriterion("EVALUATION_NUM in", values, "evaluationNum");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumNotIn(List<String> values) {
            addCriterion("EVALUATION_NUM not in", values, "evaluationNum");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumBetween(String value1, String value2) {
            addCriterion("EVALUATION_NUM between", value1, value2, "evaluationNum");
            return (Criteria) this;
        }

        public Criteria andEvaluationNumNotBetween(String value1, String value2) {
            addCriterion("EVALUATION_NUM not between", value1, value2, "evaluationNum");
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