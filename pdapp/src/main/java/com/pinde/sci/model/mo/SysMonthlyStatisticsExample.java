package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class SysMonthlyStatisticsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysMonthlyStatisticsExample() {
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

        public Criteria andStatisticFlowIsNull() {
            addCriterion("STATISTIC_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andStatisticFlowIsNotNull() {
            addCriterion("STATISTIC_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andStatisticFlowEqualTo(String value) {
            addCriterion("STATISTIC_FLOW =", value, "statisticFlow");
            return (Criteria) this;
        }

        public Criteria andStatisticFlowNotEqualTo(String value) {
            addCriterion("STATISTIC_FLOW <>", value, "statisticFlow");
            return (Criteria) this;
        }

        public Criteria andStatisticFlowGreaterThan(String value) {
            addCriterion("STATISTIC_FLOW >", value, "statisticFlow");
            return (Criteria) this;
        }

        public Criteria andStatisticFlowGreaterThanOrEqualTo(String value) {
            addCriterion("STATISTIC_FLOW >=", value, "statisticFlow");
            return (Criteria) this;
        }

        public Criteria andStatisticFlowLessThan(String value) {
            addCriterion("STATISTIC_FLOW <", value, "statisticFlow");
            return (Criteria) this;
        }

        public Criteria andStatisticFlowLessThanOrEqualTo(String value) {
            addCriterion("STATISTIC_FLOW <=", value, "statisticFlow");
            return (Criteria) this;
        }

        public Criteria andStatisticFlowLike(String value) {
            addCriterion("STATISTIC_FLOW like", value, "statisticFlow");
            return (Criteria) this;
        }

        public Criteria andStatisticFlowNotLike(String value) {
            addCriterion("STATISTIC_FLOW not like", value, "statisticFlow");
            return (Criteria) this;
        }

        public Criteria andStatisticFlowIn(List<String> values) {
            addCriterion("STATISTIC_FLOW in", values, "statisticFlow");
            return (Criteria) this;
        }

        public Criteria andStatisticFlowNotIn(List<String> values) {
            addCriterion("STATISTIC_FLOW not in", values, "statisticFlow");
            return (Criteria) this;
        }

        public Criteria andStatisticFlowBetween(String value1, String value2) {
            addCriterion("STATISTIC_FLOW between", value1, value2, "statisticFlow");
            return (Criteria) this;
        }

        public Criteria andStatisticFlowNotBetween(String value1, String value2) {
            addCriterion("STATISTIC_FLOW not between", value1, value2, "statisticFlow");
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

        public Criteria andAllNumIsNull() {
            addCriterion("ALL_NUM is null");
            return (Criteria) this;
        }

        public Criteria andAllNumIsNotNull() {
            addCriterion("ALL_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andAllNumEqualTo(Integer value) {
            addCriterion("ALL_NUM =", value, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumNotEqualTo(Integer value) {
            addCriterion("ALL_NUM <>", value, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumGreaterThan(Integer value) {
            addCriterion("ALL_NUM >", value, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("ALL_NUM >=", value, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumLessThan(Integer value) {
            addCriterion("ALL_NUM <", value, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumLessThanOrEqualTo(Integer value) {
            addCriterion("ALL_NUM <=", value, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumIn(List<Integer> values) {
            addCriterion("ALL_NUM in", values, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumNotIn(List<Integer> values) {
            addCriterion("ALL_NUM not in", values, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumBetween(Integer value1, Integer value2) {
            addCriterion("ALL_NUM between", value1, value2, "allNum");
            return (Criteria) this;
        }

        public Criteria andAllNumNotBetween(Integer value1, Integer value2) {
            addCriterion("ALL_NUM not between", value1, value2, "allNum");
            return (Criteria) this;
        }

        public Criteria andUserNumIsNull() {
            addCriterion("USER_NUM is null");
            return (Criteria) this;
        }

        public Criteria andUserNumIsNotNull() {
            addCriterion("USER_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andUserNumEqualTo(Integer value) {
            addCriterion("USER_NUM =", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumNotEqualTo(Integer value) {
            addCriterion("USER_NUM <>", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumGreaterThan(Integer value) {
            addCriterion("USER_NUM >", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("USER_NUM >=", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumLessThan(Integer value) {
            addCriterion("USER_NUM <", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumLessThanOrEqualTo(Integer value) {
            addCriterion("USER_NUM <=", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumIn(List<Integer> values) {
            addCriterion("USER_NUM in", values, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumNotIn(List<Integer> values) {
            addCriterion("USER_NUM not in", values, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumBetween(Integer value1, Integer value2) {
            addCriterion("USER_NUM between", value1, value2, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumNotBetween(Integer value1, Integer value2) {
            addCriterion("USER_NUM not between", value1, value2, "userNum");
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

        public Criteria andAvgNumIsNull() {
            addCriterion("AVG_NUM is null");
            return (Criteria) this;
        }

        public Criteria andAvgNumIsNotNull() {
            addCriterion("AVG_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andAvgNumEqualTo(Integer value) {
            addCriterion("AVG_NUM =", value, "avgNum");
            return (Criteria) this;
        }

        public Criteria andAvgNumNotEqualTo(Integer value) {
            addCriterion("AVG_NUM <>", value, "avgNum");
            return (Criteria) this;
        }

        public Criteria andAvgNumGreaterThan(Integer value) {
            addCriterion("AVG_NUM >", value, "avgNum");
            return (Criteria) this;
        }

        public Criteria andAvgNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("AVG_NUM >=", value, "avgNum");
            return (Criteria) this;
        }

        public Criteria andAvgNumLessThan(Integer value) {
            addCriterion("AVG_NUM <", value, "avgNum");
            return (Criteria) this;
        }

        public Criteria andAvgNumLessThanOrEqualTo(Integer value) {
            addCriterion("AVG_NUM <=", value, "avgNum");
            return (Criteria) this;
        }

        public Criteria andAvgNumIn(List<Integer> values) {
            addCriterion("AVG_NUM in", values, "avgNum");
            return (Criteria) this;
        }

        public Criteria andAvgNumNotIn(List<Integer> values) {
            addCriterion("AVG_NUM not in", values, "avgNum");
            return (Criteria) this;
        }

        public Criteria andAvgNumBetween(Integer value1, Integer value2) {
            addCriterion("AVG_NUM between", value1, value2, "avgNum");
            return (Criteria) this;
        }

        public Criteria andAvgNumNotBetween(Integer value1, Integer value2) {
            addCriterion("AVG_NUM not between", value1, value2, "avgNum");
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