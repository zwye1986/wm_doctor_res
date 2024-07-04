package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class PubPatientAeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PubPatientAeExample() {
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

        public Criteria andPatientFlowIsNull() {
            addCriterion("PATIENT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andPatientFlowIsNotNull() {
            addCriterion("PATIENT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andPatientFlowEqualTo(String value) {
            addCriterion("PATIENT_FLOW =", value, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowNotEqualTo(String value) {
            addCriterion("PATIENT_FLOW <>", value, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowGreaterThan(String value) {
            addCriterion("PATIENT_FLOW >", value, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_FLOW >=", value, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowLessThan(String value) {
            addCriterion("PATIENT_FLOW <", value, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_FLOW <=", value, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowLike(String value) {
            addCriterion("PATIENT_FLOW like", value, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowNotLike(String value) {
            addCriterion("PATIENT_FLOW not like", value, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowIn(List<String> values) {
            addCriterion("PATIENT_FLOW in", values, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowNotIn(List<String> values) {
            addCriterion("PATIENT_FLOW not in", values, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowBetween(String value1, String value2) {
            addCriterion("PATIENT_FLOW between", value1, value2, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientFlowNotBetween(String value1, String value2) {
            addCriterion("PATIENT_FLOW not between", value1, value2, "patientFlow");
            return (Criteria) this;
        }

        public Criteria andPatientCodeIsNull() {
            addCriterion("PATIENT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPatientCodeIsNotNull() {
            addCriterion("PATIENT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPatientCodeEqualTo(String value) {
            addCriterion("PATIENT_CODE =", value, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeNotEqualTo(String value) {
            addCriterion("PATIENT_CODE <>", value, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeGreaterThan(String value) {
            addCriterion("PATIENT_CODE >", value, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_CODE >=", value, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeLessThan(String value) {
            addCriterion("PATIENT_CODE <", value, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_CODE <=", value, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeLike(String value) {
            addCriterion("PATIENT_CODE like", value, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeNotLike(String value) {
            addCriterion("PATIENT_CODE not like", value, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeIn(List<String> values) {
            addCriterion("PATIENT_CODE in", values, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeNotIn(List<String> values) {
            addCriterion("PATIENT_CODE not in", values, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeBetween(String value1, String value2) {
            addCriterion("PATIENT_CODE between", value1, value2, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientCodeNotBetween(String value1, String value2) {
            addCriterion("PATIENT_CODE not between", value1, value2, "patientCode");
            return (Criteria) this;
        }

        public Criteria andPatientNamePyIsNull() {
            addCriterion("PATIENT_NAME_PY is null");
            return (Criteria) this;
        }

        public Criteria andPatientNamePyIsNotNull() {
            addCriterion("PATIENT_NAME_PY is not null");
            return (Criteria) this;
        }

        public Criteria andPatientNamePyEqualTo(String value) {
            addCriterion("PATIENT_NAME_PY =", value, "patientNamePy");
            return (Criteria) this;
        }

        public Criteria andPatientNamePyNotEqualTo(String value) {
            addCriterion("PATIENT_NAME_PY <>", value, "patientNamePy");
            return (Criteria) this;
        }

        public Criteria andPatientNamePyGreaterThan(String value) {
            addCriterion("PATIENT_NAME_PY >", value, "patientNamePy");
            return (Criteria) this;
        }

        public Criteria andPatientNamePyGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_NAME_PY >=", value, "patientNamePy");
            return (Criteria) this;
        }

        public Criteria andPatientNamePyLessThan(String value) {
            addCriterion("PATIENT_NAME_PY <", value, "patientNamePy");
            return (Criteria) this;
        }

        public Criteria andPatientNamePyLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_NAME_PY <=", value, "patientNamePy");
            return (Criteria) this;
        }

        public Criteria andPatientNamePyLike(String value) {
            addCriterion("PATIENT_NAME_PY like", value, "patientNamePy");
            return (Criteria) this;
        }

        public Criteria andPatientNamePyNotLike(String value) {
            addCriterion("PATIENT_NAME_PY not like", value, "patientNamePy");
            return (Criteria) this;
        }

        public Criteria andPatientNamePyIn(List<String> values) {
            addCriterion("PATIENT_NAME_PY in", values, "patientNamePy");
            return (Criteria) this;
        }

        public Criteria andPatientNamePyNotIn(List<String> values) {
            addCriterion("PATIENT_NAME_PY not in", values, "patientNamePy");
            return (Criteria) this;
        }

        public Criteria andPatientNamePyBetween(String value1, String value2) {
            addCriterion("PATIENT_NAME_PY between", value1, value2, "patientNamePy");
            return (Criteria) this;
        }

        public Criteria andPatientNamePyNotBetween(String value1, String value2) {
            addCriterion("PATIENT_NAME_PY not between", value1, value2, "patientNamePy");
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

        public Criteria andProjFlowIsNull() {
            addCriterion("PROJ_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andProjFlowIsNotNull() {
            addCriterion("PROJ_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andProjFlowEqualTo(String value) {
            addCriterion("PROJ_FLOW =", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowNotEqualTo(String value) {
            addCriterion("PROJ_FLOW <>", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowGreaterThan(String value) {
            addCriterion("PROJ_FLOW >", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_FLOW >=", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowLessThan(String value) {
            addCriterion("PROJ_FLOW <", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowLessThanOrEqualTo(String value) {
            addCriterion("PROJ_FLOW <=", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowLike(String value) {
            addCriterion("PROJ_FLOW like", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowNotLike(String value) {
            addCriterion("PROJ_FLOW not like", value, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowIn(List<String> values) {
            addCriterion("PROJ_FLOW in", values, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowNotIn(List<String> values) {
            addCriterion("PROJ_FLOW not in", values, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowBetween(String value1, String value2) {
            addCriterion("PROJ_FLOW between", value1, value2, "projFlow");
            return (Criteria) this;
        }

        public Criteria andProjFlowNotBetween(String value1, String value2) {
            addCriterion("PROJ_FLOW not between", value1, value2, "projFlow");
            return (Criteria) this;
        }

        public Criteria andAeNameIsNull() {
            addCriterion("AE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAeNameIsNotNull() {
            addCriterion("AE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAeNameEqualTo(String value) {
            addCriterion("AE_NAME =", value, "aeName");
            return (Criteria) this;
        }

        public Criteria andAeNameNotEqualTo(String value) {
            addCriterion("AE_NAME <>", value, "aeName");
            return (Criteria) this;
        }

        public Criteria andAeNameGreaterThan(String value) {
            addCriterion("AE_NAME >", value, "aeName");
            return (Criteria) this;
        }

        public Criteria andAeNameGreaterThanOrEqualTo(String value) {
            addCriterion("AE_NAME >=", value, "aeName");
            return (Criteria) this;
        }

        public Criteria andAeNameLessThan(String value) {
            addCriterion("AE_NAME <", value, "aeName");
            return (Criteria) this;
        }

        public Criteria andAeNameLessThanOrEqualTo(String value) {
            addCriterion("AE_NAME <=", value, "aeName");
            return (Criteria) this;
        }

        public Criteria andAeNameLike(String value) {
            addCriterion("AE_NAME like", value, "aeName");
            return (Criteria) this;
        }

        public Criteria andAeNameNotLike(String value) {
            addCriterion("AE_NAME not like", value, "aeName");
            return (Criteria) this;
        }

        public Criteria andAeNameIn(List<String> values) {
            addCriterion("AE_NAME in", values, "aeName");
            return (Criteria) this;
        }

        public Criteria andAeNameNotIn(List<String> values) {
            addCriterion("AE_NAME not in", values, "aeName");
            return (Criteria) this;
        }

        public Criteria andAeNameBetween(String value1, String value2) {
            addCriterion("AE_NAME between", value1, value2, "aeName");
            return (Criteria) this;
        }

        public Criteria andAeNameNotBetween(String value1, String value2) {
            addCriterion("AE_NAME not between", value1, value2, "aeName");
            return (Criteria) this;
        }

        public Criteria andIsSaeIsNull() {
            addCriterion("IS_SAE is null");
            return (Criteria) this;
        }

        public Criteria andIsSaeIsNotNull() {
            addCriterion("IS_SAE is not null");
            return (Criteria) this;
        }

        public Criteria andIsSaeEqualTo(String value) {
            addCriterion("IS_SAE =", value, "isSae");
            return (Criteria) this;
        }

        public Criteria andIsSaeNotEqualTo(String value) {
            addCriterion("IS_SAE <>", value, "isSae");
            return (Criteria) this;
        }

        public Criteria andIsSaeGreaterThan(String value) {
            addCriterion("IS_SAE >", value, "isSae");
            return (Criteria) this;
        }

        public Criteria andIsSaeGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SAE >=", value, "isSae");
            return (Criteria) this;
        }

        public Criteria andIsSaeLessThan(String value) {
            addCriterion("IS_SAE <", value, "isSae");
            return (Criteria) this;
        }

        public Criteria andIsSaeLessThanOrEqualTo(String value) {
            addCriterion("IS_SAE <=", value, "isSae");
            return (Criteria) this;
        }

        public Criteria andIsSaeLike(String value) {
            addCriterion("IS_SAE like", value, "isSae");
            return (Criteria) this;
        }

        public Criteria andIsSaeNotLike(String value) {
            addCriterion("IS_SAE not like", value, "isSae");
            return (Criteria) this;
        }

        public Criteria andIsSaeIn(List<String> values) {
            addCriterion("IS_SAE in", values, "isSae");
            return (Criteria) this;
        }

        public Criteria andIsSaeNotIn(List<String> values) {
            addCriterion("IS_SAE not in", values, "isSae");
            return (Criteria) this;
        }

        public Criteria andIsSaeBetween(String value1, String value2) {
            addCriterion("IS_SAE between", value1, value2, "isSae");
            return (Criteria) this;
        }

        public Criteria andIsSaeNotBetween(String value1, String value2) {
            addCriterion("IS_SAE not between", value1, value2, "isSae");
            return (Criteria) this;
        }

        public Criteria andAeRelationsIsNull() {
            addCriterion("AE_RELATIONS is null");
            return (Criteria) this;
        }

        public Criteria andAeRelationsIsNotNull() {
            addCriterion("AE_RELATIONS is not null");
            return (Criteria) this;
        }

        public Criteria andAeRelationsEqualTo(String value) {
            addCriterion("AE_RELATIONS =", value, "aeRelations");
            return (Criteria) this;
        }

        public Criteria andAeRelationsNotEqualTo(String value) {
            addCriterion("AE_RELATIONS <>", value, "aeRelations");
            return (Criteria) this;
        }

        public Criteria andAeRelationsGreaterThan(String value) {
            addCriterion("AE_RELATIONS >", value, "aeRelations");
            return (Criteria) this;
        }

        public Criteria andAeRelationsGreaterThanOrEqualTo(String value) {
            addCriterion("AE_RELATIONS >=", value, "aeRelations");
            return (Criteria) this;
        }

        public Criteria andAeRelationsLessThan(String value) {
            addCriterion("AE_RELATIONS <", value, "aeRelations");
            return (Criteria) this;
        }

        public Criteria andAeRelationsLessThanOrEqualTo(String value) {
            addCriterion("AE_RELATIONS <=", value, "aeRelations");
            return (Criteria) this;
        }

        public Criteria andAeRelationsLike(String value) {
            addCriterion("AE_RELATIONS like", value, "aeRelations");
            return (Criteria) this;
        }

        public Criteria andAeRelationsNotLike(String value) {
            addCriterion("AE_RELATIONS not like", value, "aeRelations");
            return (Criteria) this;
        }

        public Criteria andAeRelationsIn(List<String> values) {
            addCriterion("AE_RELATIONS in", values, "aeRelations");
            return (Criteria) this;
        }

        public Criteria andAeRelationsNotIn(List<String> values) {
            addCriterion("AE_RELATIONS not in", values, "aeRelations");
            return (Criteria) this;
        }

        public Criteria andAeRelationsBetween(String value1, String value2) {
            addCriterion("AE_RELATIONS between", value1, value2, "aeRelations");
            return (Criteria) this;
        }

        public Criteria andAeRelationsNotBetween(String value1, String value2) {
            addCriterion("AE_RELATIONS not between", value1, value2, "aeRelations");
            return (Criteria) this;
        }

        public Criteria andReportTypeIsNull() {
            addCriterion("REPORT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andReportTypeIsNotNull() {
            addCriterion("REPORT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andReportTypeEqualTo(String value) {
            addCriterion("REPORT_TYPE =", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeNotEqualTo(String value) {
            addCriterion("REPORT_TYPE <>", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeGreaterThan(String value) {
            addCriterion("REPORT_TYPE >", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeGreaterThanOrEqualTo(String value) {
            addCriterion("REPORT_TYPE >=", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeLessThan(String value) {
            addCriterion("REPORT_TYPE <", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeLessThanOrEqualTo(String value) {
            addCriterion("REPORT_TYPE <=", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeLike(String value) {
            addCriterion("REPORT_TYPE like", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeNotLike(String value) {
            addCriterion("REPORT_TYPE not like", value, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeIn(List<String> values) {
            addCriterion("REPORT_TYPE in", values, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeNotIn(List<String> values) {
            addCriterion("REPORT_TYPE not in", values, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeBetween(String value1, String value2) {
            addCriterion("REPORT_TYPE between", value1, value2, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportTypeNotBetween(String value1, String value2) {
            addCriterion("REPORT_TYPE not between", value1, value2, "reportType");
            return (Criteria) this;
        }

        public Criteria andReportDateIsNull() {
            addCriterion("REPORT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andReportDateIsNotNull() {
            addCriterion("REPORT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andReportDateEqualTo(String value) {
            addCriterion("REPORT_DATE =", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotEqualTo(String value) {
            addCriterion("REPORT_DATE <>", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateGreaterThan(String value) {
            addCriterion("REPORT_DATE >", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateGreaterThanOrEqualTo(String value) {
            addCriterion("REPORT_DATE >=", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLessThan(String value) {
            addCriterion("REPORT_DATE <", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLessThanOrEqualTo(String value) {
            addCriterion("REPORT_DATE <=", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateLike(String value) {
            addCriterion("REPORT_DATE like", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotLike(String value) {
            addCriterion("REPORT_DATE not like", value, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateIn(List<String> values) {
            addCriterion("REPORT_DATE in", values, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotIn(List<String> values) {
            addCriterion("REPORT_DATE not in", values, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateBetween(String value1, String value2) {
            addCriterion("REPORT_DATE between", value1, value2, "reportDate");
            return (Criteria) this;
        }

        public Criteria andReportDateNotBetween(String value1, String value2) {
            addCriterion("REPORT_DATE not between", value1, value2, "reportDate");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIrbIsNull() {
            addCriterion("IS_SUBMIT_IRB is null");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIrbIsNotNull() {
            addCriterion("IS_SUBMIT_IRB is not null");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIrbEqualTo(String value) {
            addCriterion("IS_SUBMIT_IRB =", value, "isSubmitIrb");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIrbNotEqualTo(String value) {
            addCriterion("IS_SUBMIT_IRB <>", value, "isSubmitIrb");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIrbGreaterThan(String value) {
            addCriterion("IS_SUBMIT_IRB >", value, "isSubmitIrb");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIrbGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SUBMIT_IRB >=", value, "isSubmitIrb");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIrbLessThan(String value) {
            addCriterion("IS_SUBMIT_IRB <", value, "isSubmitIrb");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIrbLessThanOrEqualTo(String value) {
            addCriterion("IS_SUBMIT_IRB <=", value, "isSubmitIrb");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIrbLike(String value) {
            addCriterion("IS_SUBMIT_IRB like", value, "isSubmitIrb");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIrbNotLike(String value) {
            addCriterion("IS_SUBMIT_IRB not like", value, "isSubmitIrb");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIrbIn(List<String> values) {
            addCriterion("IS_SUBMIT_IRB in", values, "isSubmitIrb");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIrbNotIn(List<String> values) {
            addCriterion("IS_SUBMIT_IRB not in", values, "isSubmitIrb");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIrbBetween(String value1, String value2) {
            addCriterion("IS_SUBMIT_IRB between", value1, value2, "isSubmitIrb");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIrbNotBetween(String value1, String value2) {
            addCriterion("IS_SUBMIT_IRB not between", value1, value2, "isSubmitIrb");
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