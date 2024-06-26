package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class JsresSchDataStatisticsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JsresSchDataStatisticsExample() {
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

        public Criteria andDataWriteTotalIsNull() {
            addCriterion("DATA_WRITE_TOTAL is null");
            return (Criteria) this;
        }

        public Criteria andDataWriteTotalIsNotNull() {
            addCriterion("DATA_WRITE_TOTAL is not null");
            return (Criteria) this;
        }

        public Criteria andDataWriteTotalEqualTo(String value) {
            addCriterion("DATA_WRITE_TOTAL =", value, "dataWriteTotal");
            return (Criteria) this;
        }

        public Criteria andDataWriteTotalNotEqualTo(String value) {
            addCriterion("DATA_WRITE_TOTAL <>", value, "dataWriteTotal");
            return (Criteria) this;
        }

        public Criteria andDataWriteTotalGreaterThan(String value) {
            addCriterion("DATA_WRITE_TOTAL >", value, "dataWriteTotal");
            return (Criteria) this;
        }

        public Criteria andDataWriteTotalGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_WRITE_TOTAL >=", value, "dataWriteTotal");
            return (Criteria) this;
        }

        public Criteria andDataWriteTotalLessThan(String value) {
            addCriterion("DATA_WRITE_TOTAL <", value, "dataWriteTotal");
            return (Criteria) this;
        }

        public Criteria andDataWriteTotalLessThanOrEqualTo(String value) {
            addCriterion("DATA_WRITE_TOTAL <=", value, "dataWriteTotal");
            return (Criteria) this;
        }

        public Criteria andDataWriteTotalLike(String value) {
            addCriterion("DATA_WRITE_TOTAL like", value, "dataWriteTotal");
            return (Criteria) this;
        }

        public Criteria andDataWriteTotalNotLike(String value) {
            addCriterion("DATA_WRITE_TOTAL not like", value, "dataWriteTotal");
            return (Criteria) this;
        }

        public Criteria andDataWriteTotalIn(List<String> values) {
            addCriterion("DATA_WRITE_TOTAL in", values, "dataWriteTotal");
            return (Criteria) this;
        }

        public Criteria andDataWriteTotalNotIn(List<String> values) {
            addCriterion("DATA_WRITE_TOTAL not in", values, "dataWriteTotal");
            return (Criteria) this;
        }

        public Criteria andDataWriteTotalBetween(String value1, String value2) {
            addCriterion("DATA_WRITE_TOTAL between", value1, value2, "dataWriteTotal");
            return (Criteria) this;
        }

        public Criteria andDataWriteTotalNotBetween(String value1, String value2) {
            addCriterion("DATA_WRITE_TOTAL not between", value1, value2, "dataWriteTotal");
            return (Criteria) this;
        }

        public Criteria andDataAuditTotalIsNull() {
            addCriterion("DATA_AUDIT_TOTAL is null");
            return (Criteria) this;
        }

        public Criteria andDataAuditTotalIsNotNull() {
            addCriterion("DATA_AUDIT_TOTAL is not null");
            return (Criteria) this;
        }

        public Criteria andDataAuditTotalEqualTo(String value) {
            addCriterion("DATA_AUDIT_TOTAL =", value, "dataAuditTotal");
            return (Criteria) this;
        }

        public Criteria andDataAuditTotalNotEqualTo(String value) {
            addCriterion("DATA_AUDIT_TOTAL <>", value, "dataAuditTotal");
            return (Criteria) this;
        }

        public Criteria andDataAuditTotalGreaterThan(String value) {
            addCriterion("DATA_AUDIT_TOTAL >", value, "dataAuditTotal");
            return (Criteria) this;
        }

        public Criteria andDataAuditTotalGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_AUDIT_TOTAL >=", value, "dataAuditTotal");
            return (Criteria) this;
        }

        public Criteria andDataAuditTotalLessThan(String value) {
            addCriterion("DATA_AUDIT_TOTAL <", value, "dataAuditTotal");
            return (Criteria) this;
        }

        public Criteria andDataAuditTotalLessThanOrEqualTo(String value) {
            addCriterion("DATA_AUDIT_TOTAL <=", value, "dataAuditTotal");
            return (Criteria) this;
        }

        public Criteria andDataAuditTotalLike(String value) {
            addCriterion("DATA_AUDIT_TOTAL like", value, "dataAuditTotal");
            return (Criteria) this;
        }

        public Criteria andDataAuditTotalNotLike(String value) {
            addCriterion("DATA_AUDIT_TOTAL not like", value, "dataAuditTotal");
            return (Criteria) this;
        }

        public Criteria andDataAuditTotalIn(List<String> values) {
            addCriterion("DATA_AUDIT_TOTAL in", values, "dataAuditTotal");
            return (Criteria) this;
        }

        public Criteria andDataAuditTotalNotIn(List<String> values) {
            addCriterion("DATA_AUDIT_TOTAL not in", values, "dataAuditTotal");
            return (Criteria) this;
        }

        public Criteria andDataAuditTotalBetween(String value1, String value2) {
            addCriterion("DATA_AUDIT_TOTAL between", value1, value2, "dataAuditTotal");
            return (Criteria) this;
        }

        public Criteria andDataAuditTotalNotBetween(String value1, String value2) {
            addCriterion("DATA_AUDIT_TOTAL not between", value1, value2, "dataAuditTotal");
            return (Criteria) this;
        }

        public Criteria andAuditScaleIsNull() {
            addCriterion("AUDIT_SCALE is null");
            return (Criteria) this;
        }

        public Criteria andAuditScaleIsNotNull() {
            addCriterion("AUDIT_SCALE is not null");
            return (Criteria) this;
        }

        public Criteria andAuditScaleEqualTo(String value) {
            addCriterion("AUDIT_SCALE =", value, "auditScale");
            return (Criteria) this;
        }

        public Criteria andAuditScaleNotEqualTo(String value) {
            addCriterion("AUDIT_SCALE <>", value, "auditScale");
            return (Criteria) this;
        }

        public Criteria andAuditScaleGreaterThan(String value) {
            addCriterion("AUDIT_SCALE >", value, "auditScale");
            return (Criteria) this;
        }

        public Criteria andAuditScaleGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_SCALE >=", value, "auditScale");
            return (Criteria) this;
        }

        public Criteria andAuditScaleLessThan(String value) {
            addCriterion("AUDIT_SCALE <", value, "auditScale");
            return (Criteria) this;
        }

        public Criteria andAuditScaleLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_SCALE <=", value, "auditScale");
            return (Criteria) this;
        }

        public Criteria andAuditScaleLike(String value) {
            addCriterion("AUDIT_SCALE like", value, "auditScale");
            return (Criteria) this;
        }

        public Criteria andAuditScaleNotLike(String value) {
            addCriterion("AUDIT_SCALE not like", value, "auditScale");
            return (Criteria) this;
        }

        public Criteria andAuditScaleIn(List<String> values) {
            addCriterion("AUDIT_SCALE in", values, "auditScale");
            return (Criteria) this;
        }

        public Criteria andAuditScaleNotIn(List<String> values) {
            addCriterion("AUDIT_SCALE not in", values, "auditScale");
            return (Criteria) this;
        }

        public Criteria andAuditScaleBetween(String value1, String value2) {
            addCriterion("AUDIT_SCALE between", value1, value2, "auditScale");
            return (Criteria) this;
        }

        public Criteria andAuditScaleNotBetween(String value1, String value2) {
            addCriterion("AUDIT_SCALE not between", value1, value2, "auditScale");
            return (Criteria) this;
        }

        public Criteria andAvgWriteIsNull() {
            addCriterion("AVG_WRITE is null");
            return (Criteria) this;
        }

        public Criteria andAvgWriteIsNotNull() {
            addCriterion("AVG_WRITE is not null");
            return (Criteria) this;
        }

        public Criteria andAvgWriteEqualTo(String value) {
            addCriterion("AVG_WRITE =", value, "avgWrite");
            return (Criteria) this;
        }

        public Criteria andAvgWriteNotEqualTo(String value) {
            addCriterion("AVG_WRITE <>", value, "avgWrite");
            return (Criteria) this;
        }

        public Criteria andAvgWriteGreaterThan(String value) {
            addCriterion("AVG_WRITE >", value, "avgWrite");
            return (Criteria) this;
        }

        public Criteria andAvgWriteGreaterThanOrEqualTo(String value) {
            addCriterion("AVG_WRITE >=", value, "avgWrite");
            return (Criteria) this;
        }

        public Criteria andAvgWriteLessThan(String value) {
            addCriterion("AVG_WRITE <", value, "avgWrite");
            return (Criteria) this;
        }

        public Criteria andAvgWriteLessThanOrEqualTo(String value) {
            addCriterion("AVG_WRITE <=", value, "avgWrite");
            return (Criteria) this;
        }

        public Criteria andAvgWriteLike(String value) {
            addCriterion("AVG_WRITE like", value, "avgWrite");
            return (Criteria) this;
        }

        public Criteria andAvgWriteNotLike(String value) {
            addCriterion("AVG_WRITE not like", value, "avgWrite");
            return (Criteria) this;
        }

        public Criteria andAvgWriteIn(List<String> values) {
            addCriterion("AVG_WRITE in", values, "avgWrite");
            return (Criteria) this;
        }

        public Criteria andAvgWriteNotIn(List<String> values) {
            addCriterion("AVG_WRITE not in", values, "avgWrite");
            return (Criteria) this;
        }

        public Criteria andAvgWriteBetween(String value1, String value2) {
            addCriterion("AVG_WRITE between", value1, value2, "avgWrite");
            return (Criteria) this;
        }

        public Criteria andAvgWriteNotBetween(String value1, String value2) {
            addCriterion("AVG_WRITE not between", value1, value2, "avgWrite");
            return (Criteria) this;
        }

        public Criteria andAvgAuditIsNull() {
            addCriterion("AVG_AUDIT is null");
            return (Criteria) this;
        }

        public Criteria andAvgAuditIsNotNull() {
            addCriterion("AVG_AUDIT is not null");
            return (Criteria) this;
        }

        public Criteria andAvgAuditEqualTo(String value) {
            addCriterion("AVG_AUDIT =", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditNotEqualTo(String value) {
            addCriterion("AVG_AUDIT <>", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditGreaterThan(String value) {
            addCriterion("AVG_AUDIT >", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditGreaterThanOrEqualTo(String value) {
            addCriterion("AVG_AUDIT >=", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditLessThan(String value) {
            addCriterion("AVG_AUDIT <", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditLessThanOrEqualTo(String value) {
            addCriterion("AVG_AUDIT <=", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditLike(String value) {
            addCriterion("AVG_AUDIT like", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditNotLike(String value) {
            addCriterion("AVG_AUDIT not like", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditIn(List<String> values) {
            addCriterion("AVG_AUDIT in", values, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditNotIn(List<String> values) {
            addCriterion("AVG_AUDIT not in", values, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditBetween(String value1, String value2) {
            addCriterion("AVG_AUDIT between", value1, value2, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditNotBetween(String value1, String value2) {
            addCriterion("AVG_AUDIT not between", value1, value2, "avgAudit");
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