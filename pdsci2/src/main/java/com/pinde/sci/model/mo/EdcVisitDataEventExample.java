package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class EdcVisitDataEventExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EdcVisitDataEventExample() {
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

        public Criteria andDataRecordFlowIsNull() {
            addCriterion("DATA_RECORD_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDataRecordFlowIsNotNull() {
            addCriterion("DATA_RECORD_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDataRecordFlowEqualTo(String value) {
            addCriterion("DATA_RECORD_FLOW =", value, "dataRecordFlow");
            return (Criteria) this;
        }

        public Criteria andDataRecordFlowNotEqualTo(String value) {
            addCriterion("DATA_RECORD_FLOW <>", value, "dataRecordFlow");
            return (Criteria) this;
        }

        public Criteria andDataRecordFlowGreaterThan(String value) {
            addCriterion("DATA_RECORD_FLOW >", value, "dataRecordFlow");
            return (Criteria) this;
        }

        public Criteria andDataRecordFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_RECORD_FLOW >=", value, "dataRecordFlow");
            return (Criteria) this;
        }

        public Criteria andDataRecordFlowLessThan(String value) {
            addCriterion("DATA_RECORD_FLOW <", value, "dataRecordFlow");
            return (Criteria) this;
        }

        public Criteria andDataRecordFlowLessThanOrEqualTo(String value) {
            addCriterion("DATA_RECORD_FLOW <=", value, "dataRecordFlow");
            return (Criteria) this;
        }

        public Criteria andDataRecordFlowLike(String value) {
            addCriterion("DATA_RECORD_FLOW like", value, "dataRecordFlow");
            return (Criteria) this;
        }

        public Criteria andDataRecordFlowNotLike(String value) {
            addCriterion("DATA_RECORD_FLOW not like", value, "dataRecordFlow");
            return (Criteria) this;
        }

        public Criteria andDataRecordFlowIn(List<String> values) {
            addCriterion("DATA_RECORD_FLOW in", values, "dataRecordFlow");
            return (Criteria) this;
        }

        public Criteria andDataRecordFlowNotIn(List<String> values) {
            addCriterion("DATA_RECORD_FLOW not in", values, "dataRecordFlow");
            return (Criteria) this;
        }

        public Criteria andDataRecordFlowBetween(String value1, String value2) {
            addCriterion("DATA_RECORD_FLOW between", value1, value2, "dataRecordFlow");
            return (Criteria) this;
        }

        public Criteria andDataRecordFlowNotBetween(String value1, String value2) {
            addCriterion("DATA_RECORD_FLOW not between", value1, value2, "dataRecordFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowIsNull() {
            addCriterion("VISIT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andVisitFlowIsNotNull() {
            addCriterion("VISIT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andVisitFlowEqualTo(String value) {
            addCriterion("VISIT_FLOW =", value, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowNotEqualTo(String value) {
            addCriterion("VISIT_FLOW <>", value, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowGreaterThan(String value) {
            addCriterion("VISIT_FLOW >", value, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowGreaterThanOrEqualTo(String value) {
            addCriterion("VISIT_FLOW >=", value, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowLessThan(String value) {
            addCriterion("VISIT_FLOW <", value, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowLessThanOrEqualTo(String value) {
            addCriterion("VISIT_FLOW <=", value, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowLike(String value) {
            addCriterion("VISIT_FLOW like", value, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowNotLike(String value) {
            addCriterion("VISIT_FLOW not like", value, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowIn(List<String> values) {
            addCriterion("VISIT_FLOW in", values, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowNotIn(List<String> values) {
            addCriterion("VISIT_FLOW not in", values, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowBetween(String value1, String value2) {
            addCriterion("VISIT_FLOW between", value1, value2, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowNotBetween(String value1, String value2) {
            addCriterion("VISIT_FLOW not between", value1, value2, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitNameIsNull() {
            addCriterion("VISIT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andVisitNameIsNotNull() {
            addCriterion("VISIT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andVisitNameEqualTo(String value) {
            addCriterion("VISIT_NAME =", value, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameNotEqualTo(String value) {
            addCriterion("VISIT_NAME <>", value, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameGreaterThan(String value) {
            addCriterion("VISIT_NAME >", value, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameGreaterThanOrEqualTo(String value) {
            addCriterion("VISIT_NAME >=", value, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameLessThan(String value) {
            addCriterion("VISIT_NAME <", value, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameLessThanOrEqualTo(String value) {
            addCriterion("VISIT_NAME <=", value, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameLike(String value) {
            addCriterion("VISIT_NAME like", value, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameNotLike(String value) {
            addCriterion("VISIT_NAME not like", value, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameIn(List<String> values) {
            addCriterion("VISIT_NAME in", values, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameNotIn(List<String> values) {
            addCriterion("VISIT_NAME not in", values, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameBetween(String value1, String value2) {
            addCriterion("VISIT_NAME between", value1, value2, "visitName");
            return (Criteria) this;
        }

        public Criteria andVisitNameNotBetween(String value1, String value2) {
            addCriterion("VISIT_NAME not between", value1, value2, "visitName");
            return (Criteria) this;
        }

        public Criteria andModuleCodeIsNull() {
            addCriterion("MODULE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andModuleCodeIsNotNull() {
            addCriterion("MODULE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andModuleCodeEqualTo(String value) {
            addCriterion("MODULE_CODE =", value, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeNotEqualTo(String value) {
            addCriterion("MODULE_CODE <>", value, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeGreaterThan(String value) {
            addCriterion("MODULE_CODE >", value, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeGreaterThanOrEqualTo(String value) {
            addCriterion("MODULE_CODE >=", value, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeLessThan(String value) {
            addCriterion("MODULE_CODE <", value, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeLessThanOrEqualTo(String value) {
            addCriterion("MODULE_CODE <=", value, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeLike(String value) {
            addCriterion("MODULE_CODE like", value, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeNotLike(String value) {
            addCriterion("MODULE_CODE not like", value, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeIn(List<String> values) {
            addCriterion("MODULE_CODE in", values, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeNotIn(List<String> values) {
            addCriterion("MODULE_CODE not in", values, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeBetween(String value1, String value2) {
            addCriterion("MODULE_CODE between", value1, value2, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleCodeNotBetween(String value1, String value2) {
            addCriterion("MODULE_CODE not between", value1, value2, "moduleCode");
            return (Criteria) this;
        }

        public Criteria andModuleNameIsNull() {
            addCriterion("MODULE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andModuleNameIsNotNull() {
            addCriterion("MODULE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andModuleNameEqualTo(String value) {
            addCriterion("MODULE_NAME =", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotEqualTo(String value) {
            addCriterion("MODULE_NAME <>", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameGreaterThan(String value) {
            addCriterion("MODULE_NAME >", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameGreaterThanOrEqualTo(String value) {
            addCriterion("MODULE_NAME >=", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLessThan(String value) {
            addCriterion("MODULE_NAME <", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLessThanOrEqualTo(String value) {
            addCriterion("MODULE_NAME <=", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLike(String value) {
            addCriterion("MODULE_NAME like", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotLike(String value) {
            addCriterion("MODULE_NAME not like", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameIn(List<String> values) {
            addCriterion("MODULE_NAME in", values, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotIn(List<String> values) {
            addCriterion("MODULE_NAME not in", values, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameBetween(String value1, String value2) {
            addCriterion("MODULE_NAME between", value1, value2, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotBetween(String value1, String value2) {
            addCriterion("MODULE_NAME not between", value1, value2, "moduleName");
            return (Criteria) this;
        }

        public Criteria andElementCodeIsNull() {
            addCriterion("ELEMENT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andElementCodeIsNotNull() {
            addCriterion("ELEMENT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andElementCodeEqualTo(String value) {
            addCriterion("ELEMENT_CODE =", value, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeNotEqualTo(String value) {
            addCriterion("ELEMENT_CODE <>", value, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeGreaterThan(String value) {
            addCriterion("ELEMENT_CODE >", value, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ELEMENT_CODE >=", value, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeLessThan(String value) {
            addCriterion("ELEMENT_CODE <", value, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeLessThanOrEqualTo(String value) {
            addCriterion("ELEMENT_CODE <=", value, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeLike(String value) {
            addCriterion("ELEMENT_CODE like", value, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeNotLike(String value) {
            addCriterion("ELEMENT_CODE not like", value, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeIn(List<String> values) {
            addCriterion("ELEMENT_CODE in", values, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeNotIn(List<String> values) {
            addCriterion("ELEMENT_CODE not in", values, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeBetween(String value1, String value2) {
            addCriterion("ELEMENT_CODE between", value1, value2, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementCodeNotBetween(String value1, String value2) {
            addCriterion("ELEMENT_CODE not between", value1, value2, "elementCode");
            return (Criteria) this;
        }

        public Criteria andElementNameIsNull() {
            addCriterion("ELEMENT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andElementNameIsNotNull() {
            addCriterion("ELEMENT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andElementNameEqualTo(String value) {
            addCriterion("ELEMENT_NAME =", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameNotEqualTo(String value) {
            addCriterion("ELEMENT_NAME <>", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameGreaterThan(String value) {
            addCriterion("ELEMENT_NAME >", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameGreaterThanOrEqualTo(String value) {
            addCriterion("ELEMENT_NAME >=", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameLessThan(String value) {
            addCriterion("ELEMENT_NAME <", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameLessThanOrEqualTo(String value) {
            addCriterion("ELEMENT_NAME <=", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameLike(String value) {
            addCriterion("ELEMENT_NAME like", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameNotLike(String value) {
            addCriterion("ELEMENT_NAME not like", value, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameIn(List<String> values) {
            addCriterion("ELEMENT_NAME in", values, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameNotIn(List<String> values) {
            addCriterion("ELEMENT_NAME not in", values, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameBetween(String value1, String value2) {
            addCriterion("ELEMENT_NAME between", value1, value2, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementNameNotBetween(String value1, String value2) {
            addCriterion("ELEMENT_NAME not between", value1, value2, "elementName");
            return (Criteria) this;
        }

        public Criteria andElementSerialSeqIsNull() {
            addCriterion("ELEMENT_SERIAL_SEQ is null");
            return (Criteria) this;
        }

        public Criteria andElementSerialSeqIsNotNull() {
            addCriterion("ELEMENT_SERIAL_SEQ is not null");
            return (Criteria) this;
        }

        public Criteria andElementSerialSeqEqualTo(String value) {
            addCriterion("ELEMENT_SERIAL_SEQ =", value, "elementSerialSeq");
            return (Criteria) this;
        }

        public Criteria andElementSerialSeqNotEqualTo(String value) {
            addCriterion("ELEMENT_SERIAL_SEQ <>", value, "elementSerialSeq");
            return (Criteria) this;
        }

        public Criteria andElementSerialSeqGreaterThan(String value) {
            addCriterion("ELEMENT_SERIAL_SEQ >", value, "elementSerialSeq");
            return (Criteria) this;
        }

        public Criteria andElementSerialSeqGreaterThanOrEqualTo(String value) {
            addCriterion("ELEMENT_SERIAL_SEQ >=", value, "elementSerialSeq");
            return (Criteria) this;
        }

        public Criteria andElementSerialSeqLessThan(String value) {
            addCriterion("ELEMENT_SERIAL_SEQ <", value, "elementSerialSeq");
            return (Criteria) this;
        }

        public Criteria andElementSerialSeqLessThanOrEqualTo(String value) {
            addCriterion("ELEMENT_SERIAL_SEQ <=", value, "elementSerialSeq");
            return (Criteria) this;
        }

        public Criteria andElementSerialSeqLike(String value) {
            addCriterion("ELEMENT_SERIAL_SEQ like", value, "elementSerialSeq");
            return (Criteria) this;
        }

        public Criteria andElementSerialSeqNotLike(String value) {
            addCriterion("ELEMENT_SERIAL_SEQ not like", value, "elementSerialSeq");
            return (Criteria) this;
        }

        public Criteria andElementSerialSeqIn(List<String> values) {
            addCriterion("ELEMENT_SERIAL_SEQ in", values, "elementSerialSeq");
            return (Criteria) this;
        }

        public Criteria andElementSerialSeqNotIn(List<String> values) {
            addCriterion("ELEMENT_SERIAL_SEQ not in", values, "elementSerialSeq");
            return (Criteria) this;
        }

        public Criteria andElementSerialSeqBetween(String value1, String value2) {
            addCriterion("ELEMENT_SERIAL_SEQ between", value1, value2, "elementSerialSeq");
            return (Criteria) this;
        }

        public Criteria andElementSerialSeqNotBetween(String value1, String value2) {
            addCriterion("ELEMENT_SERIAL_SEQ not between", value1, value2, "elementSerialSeq");
            return (Criteria) this;
        }

        public Criteria andAttrCodeIsNull() {
            addCriterion("ATTR_CODE is null");
            return (Criteria) this;
        }

        public Criteria andAttrCodeIsNotNull() {
            addCriterion("ATTR_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andAttrCodeEqualTo(String value) {
            addCriterion("ATTR_CODE =", value, "attrCode");
            return (Criteria) this;
        }

        public Criteria andAttrCodeNotEqualTo(String value) {
            addCriterion("ATTR_CODE <>", value, "attrCode");
            return (Criteria) this;
        }

        public Criteria andAttrCodeGreaterThan(String value) {
            addCriterion("ATTR_CODE >", value, "attrCode");
            return (Criteria) this;
        }

        public Criteria andAttrCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_CODE >=", value, "attrCode");
            return (Criteria) this;
        }

        public Criteria andAttrCodeLessThan(String value) {
            addCriterion("ATTR_CODE <", value, "attrCode");
            return (Criteria) this;
        }

        public Criteria andAttrCodeLessThanOrEqualTo(String value) {
            addCriterion("ATTR_CODE <=", value, "attrCode");
            return (Criteria) this;
        }

        public Criteria andAttrCodeLike(String value) {
            addCriterion("ATTR_CODE like", value, "attrCode");
            return (Criteria) this;
        }

        public Criteria andAttrCodeNotLike(String value) {
            addCriterion("ATTR_CODE not like", value, "attrCode");
            return (Criteria) this;
        }

        public Criteria andAttrCodeIn(List<String> values) {
            addCriterion("ATTR_CODE in", values, "attrCode");
            return (Criteria) this;
        }

        public Criteria andAttrCodeNotIn(List<String> values) {
            addCriterion("ATTR_CODE not in", values, "attrCode");
            return (Criteria) this;
        }

        public Criteria andAttrCodeBetween(String value1, String value2) {
            addCriterion("ATTR_CODE between", value1, value2, "attrCode");
            return (Criteria) this;
        }

        public Criteria andAttrCodeNotBetween(String value1, String value2) {
            addCriterion("ATTR_CODE not between", value1, value2, "attrCode");
            return (Criteria) this;
        }

        public Criteria andAttrNameIsNull() {
            addCriterion("ATTR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAttrNameIsNotNull() {
            addCriterion("ATTR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAttrNameEqualTo(String value) {
            addCriterion("ATTR_NAME =", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameNotEqualTo(String value) {
            addCriterion("ATTR_NAME <>", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameGreaterThan(String value) {
            addCriterion("ATTR_NAME >", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameGreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_NAME >=", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameLessThan(String value) {
            addCriterion("ATTR_NAME <", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameLessThanOrEqualTo(String value) {
            addCriterion("ATTR_NAME <=", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameLike(String value) {
            addCriterion("ATTR_NAME like", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameNotLike(String value) {
            addCriterion("ATTR_NAME not like", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameIn(List<String> values) {
            addCriterion("ATTR_NAME in", values, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameNotIn(List<String> values) {
            addCriterion("ATTR_NAME not in", values, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameBetween(String value1, String value2) {
            addCriterion("ATTR_NAME between", value1, value2, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameNotBetween(String value1, String value2) {
            addCriterion("ATTR_NAME not between", value1, value2, "attrName");
            return (Criteria) this;
        }

        public Criteria andEventSeqIsNull() {
            addCriterion("EVENT_SEQ is null");
            return (Criteria) this;
        }

        public Criteria andEventSeqIsNotNull() {
            addCriterion("EVENT_SEQ is not null");
            return (Criteria) this;
        }

        public Criteria andEventSeqEqualTo(String value) {
            addCriterion("EVENT_SEQ =", value, "eventSeq");
            return (Criteria) this;
        }

        public Criteria andEventSeqNotEqualTo(String value) {
            addCriterion("EVENT_SEQ <>", value, "eventSeq");
            return (Criteria) this;
        }

        public Criteria andEventSeqGreaterThan(String value) {
            addCriterion("EVENT_SEQ >", value, "eventSeq");
            return (Criteria) this;
        }

        public Criteria andEventSeqGreaterThanOrEqualTo(String value) {
            addCriterion("EVENT_SEQ >=", value, "eventSeq");
            return (Criteria) this;
        }

        public Criteria andEventSeqLessThan(String value) {
            addCriterion("EVENT_SEQ <", value, "eventSeq");
            return (Criteria) this;
        }

        public Criteria andEventSeqLessThanOrEqualTo(String value) {
            addCriterion("EVENT_SEQ <=", value, "eventSeq");
            return (Criteria) this;
        }

        public Criteria andEventSeqLike(String value) {
            addCriterion("EVENT_SEQ like", value, "eventSeq");
            return (Criteria) this;
        }

        public Criteria andEventSeqNotLike(String value) {
            addCriterion("EVENT_SEQ not like", value, "eventSeq");
            return (Criteria) this;
        }

        public Criteria andEventSeqIn(List<String> values) {
            addCriterion("EVENT_SEQ in", values, "eventSeq");
            return (Criteria) this;
        }

        public Criteria andEventSeqNotIn(List<String> values) {
            addCriterion("EVENT_SEQ not in", values, "eventSeq");
            return (Criteria) this;
        }

        public Criteria andEventSeqBetween(String value1, String value2) {
            addCriterion("EVENT_SEQ between", value1, value2, "eventSeq");
            return (Criteria) this;
        }

        public Criteria andEventSeqNotBetween(String value1, String value2) {
            addCriterion("EVENT_SEQ not between", value1, value2, "eventSeq");
            return (Criteria) this;
        }

        public Criteria andAttrValueIsNull() {
            addCriterion("ATTR_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andAttrValueIsNotNull() {
            addCriterion("ATTR_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andAttrValueEqualTo(String value) {
            addCriterion("ATTR_VALUE =", value, "attrValue");
            return (Criteria) this;
        }

        public Criteria andAttrValueNotEqualTo(String value) {
            addCriterion("ATTR_VALUE <>", value, "attrValue");
            return (Criteria) this;
        }

        public Criteria andAttrValueGreaterThan(String value) {
            addCriterion("ATTR_VALUE >", value, "attrValue");
            return (Criteria) this;
        }

        public Criteria andAttrValueGreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_VALUE >=", value, "attrValue");
            return (Criteria) this;
        }

        public Criteria andAttrValueLessThan(String value) {
            addCriterion("ATTR_VALUE <", value, "attrValue");
            return (Criteria) this;
        }

        public Criteria andAttrValueLessThanOrEqualTo(String value) {
            addCriterion("ATTR_VALUE <=", value, "attrValue");
            return (Criteria) this;
        }

        public Criteria andAttrValueLike(String value) {
            addCriterion("ATTR_VALUE like", value, "attrValue");
            return (Criteria) this;
        }

        public Criteria andAttrValueNotLike(String value) {
            addCriterion("ATTR_VALUE not like", value, "attrValue");
            return (Criteria) this;
        }

        public Criteria andAttrValueIn(List<String> values) {
            addCriterion("ATTR_VALUE in", values, "attrValue");
            return (Criteria) this;
        }

        public Criteria andAttrValueNotIn(List<String> values) {
            addCriterion("ATTR_VALUE not in", values, "attrValue");
            return (Criteria) this;
        }

        public Criteria andAttrValueBetween(String value1, String value2) {
            addCriterion("ATTR_VALUE between", value1, value2, "attrValue");
            return (Criteria) this;
        }

        public Criteria andAttrValueNotBetween(String value1, String value2) {
            addCriterion("ATTR_VALUE not between", value1, value2, "attrValue");
            return (Criteria) this;
        }

        public Criteria andAttrEventValueIsNull() {
            addCriterion("ATTR_EVENT_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andAttrEventValueIsNotNull() {
            addCriterion("ATTR_EVENT_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andAttrEventValueEqualTo(String value) {
            addCriterion("ATTR_EVENT_VALUE =", value, "attrEventValue");
            return (Criteria) this;
        }

        public Criteria andAttrEventValueNotEqualTo(String value) {
            addCriterion("ATTR_EVENT_VALUE <>", value, "attrEventValue");
            return (Criteria) this;
        }

        public Criteria andAttrEventValueGreaterThan(String value) {
            addCriterion("ATTR_EVENT_VALUE >", value, "attrEventValue");
            return (Criteria) this;
        }

        public Criteria andAttrEventValueGreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_EVENT_VALUE >=", value, "attrEventValue");
            return (Criteria) this;
        }

        public Criteria andAttrEventValueLessThan(String value) {
            addCriterion("ATTR_EVENT_VALUE <", value, "attrEventValue");
            return (Criteria) this;
        }

        public Criteria andAttrEventValueLessThanOrEqualTo(String value) {
            addCriterion("ATTR_EVENT_VALUE <=", value, "attrEventValue");
            return (Criteria) this;
        }

        public Criteria andAttrEventValueLike(String value) {
            addCriterion("ATTR_EVENT_VALUE like", value, "attrEventValue");
            return (Criteria) this;
        }

        public Criteria andAttrEventValueNotLike(String value) {
            addCriterion("ATTR_EVENT_VALUE not like", value, "attrEventValue");
            return (Criteria) this;
        }

        public Criteria andAttrEventValueIn(List<String> values) {
            addCriterion("ATTR_EVENT_VALUE in", values, "attrEventValue");
            return (Criteria) this;
        }

        public Criteria andAttrEventValueNotIn(List<String> values) {
            addCriterion("ATTR_EVENT_VALUE not in", values, "attrEventValue");
            return (Criteria) this;
        }

        public Criteria andAttrEventValueBetween(String value1, String value2) {
            addCriterion("ATTR_EVENT_VALUE between", value1, value2, "attrEventValue");
            return (Criteria) this;
        }

        public Criteria andAttrEventValueNotBetween(String value1, String value2) {
            addCriterion("ATTR_EVENT_VALUE not between", value1, value2, "attrEventValue");
            return (Criteria) this;
        }

        public Criteria andEventTimeIsNull() {
            addCriterion("EVENT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEventTimeIsNotNull() {
            addCriterion("EVENT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEventTimeEqualTo(String value) {
            addCriterion("EVENT_TIME =", value, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeNotEqualTo(String value) {
            addCriterion("EVENT_TIME <>", value, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeGreaterThan(String value) {
            addCriterion("EVENT_TIME >", value, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeGreaterThanOrEqualTo(String value) {
            addCriterion("EVENT_TIME >=", value, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeLessThan(String value) {
            addCriterion("EVENT_TIME <", value, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeLessThanOrEqualTo(String value) {
            addCriterion("EVENT_TIME <=", value, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeLike(String value) {
            addCriterion("EVENT_TIME like", value, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeNotLike(String value) {
            addCriterion("EVENT_TIME not like", value, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeIn(List<String> values) {
            addCriterion("EVENT_TIME in", values, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeNotIn(List<String> values) {
            addCriterion("EVENT_TIME not in", values, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeBetween(String value1, String value2) {
            addCriterion("EVENT_TIME between", value1, value2, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventTimeNotBetween(String value1, String value2) {
            addCriterion("EVENT_TIME not between", value1, value2, "eventTime");
            return (Criteria) this;
        }

        public Criteria andEventUserFlowIsNull() {
            addCriterion("EVENT_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andEventUserFlowIsNotNull() {
            addCriterion("EVENT_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andEventUserFlowEqualTo(String value) {
            addCriterion("EVENT_USER_FLOW =", value, "eventUserFlow");
            return (Criteria) this;
        }

        public Criteria andEventUserFlowNotEqualTo(String value) {
            addCriterion("EVENT_USER_FLOW <>", value, "eventUserFlow");
            return (Criteria) this;
        }

        public Criteria andEventUserFlowGreaterThan(String value) {
            addCriterion("EVENT_USER_FLOW >", value, "eventUserFlow");
            return (Criteria) this;
        }

        public Criteria andEventUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("EVENT_USER_FLOW >=", value, "eventUserFlow");
            return (Criteria) this;
        }

        public Criteria andEventUserFlowLessThan(String value) {
            addCriterion("EVENT_USER_FLOW <", value, "eventUserFlow");
            return (Criteria) this;
        }

        public Criteria andEventUserFlowLessThanOrEqualTo(String value) {
            addCriterion("EVENT_USER_FLOW <=", value, "eventUserFlow");
            return (Criteria) this;
        }

        public Criteria andEventUserFlowLike(String value) {
            addCriterion("EVENT_USER_FLOW like", value, "eventUserFlow");
            return (Criteria) this;
        }

        public Criteria andEventUserFlowNotLike(String value) {
            addCriterion("EVENT_USER_FLOW not like", value, "eventUserFlow");
            return (Criteria) this;
        }

        public Criteria andEventUserFlowIn(List<String> values) {
            addCriterion("EVENT_USER_FLOW in", values, "eventUserFlow");
            return (Criteria) this;
        }

        public Criteria andEventUserFlowNotIn(List<String> values) {
            addCriterion("EVENT_USER_FLOW not in", values, "eventUserFlow");
            return (Criteria) this;
        }

        public Criteria andEventUserFlowBetween(String value1, String value2) {
            addCriterion("EVENT_USER_FLOW between", value1, value2, "eventUserFlow");
            return (Criteria) this;
        }

        public Criteria andEventUserFlowNotBetween(String value1, String value2) {
            addCriterion("EVENT_USER_FLOW not between", value1, value2, "eventUserFlow");
            return (Criteria) this;
        }

        public Criteria andEventUserNameIsNull() {
            addCriterion("EVENT_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andEventUserNameIsNotNull() {
            addCriterion("EVENT_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andEventUserNameEqualTo(String value) {
            addCriterion("EVENT_USER_NAME =", value, "eventUserName");
            return (Criteria) this;
        }

        public Criteria andEventUserNameNotEqualTo(String value) {
            addCriterion("EVENT_USER_NAME <>", value, "eventUserName");
            return (Criteria) this;
        }

        public Criteria andEventUserNameGreaterThan(String value) {
            addCriterion("EVENT_USER_NAME >", value, "eventUserName");
            return (Criteria) this;
        }

        public Criteria andEventUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("EVENT_USER_NAME >=", value, "eventUserName");
            return (Criteria) this;
        }

        public Criteria andEventUserNameLessThan(String value) {
            addCriterion("EVENT_USER_NAME <", value, "eventUserName");
            return (Criteria) this;
        }

        public Criteria andEventUserNameLessThanOrEqualTo(String value) {
            addCriterion("EVENT_USER_NAME <=", value, "eventUserName");
            return (Criteria) this;
        }

        public Criteria andEventUserNameLike(String value) {
            addCriterion("EVENT_USER_NAME like", value, "eventUserName");
            return (Criteria) this;
        }

        public Criteria andEventUserNameNotLike(String value) {
            addCriterion("EVENT_USER_NAME not like", value, "eventUserName");
            return (Criteria) this;
        }

        public Criteria andEventUserNameIn(List<String> values) {
            addCriterion("EVENT_USER_NAME in", values, "eventUserName");
            return (Criteria) this;
        }

        public Criteria andEventUserNameNotIn(List<String> values) {
            addCriterion("EVENT_USER_NAME not in", values, "eventUserName");
            return (Criteria) this;
        }

        public Criteria andEventUserNameBetween(String value1, String value2) {
            addCriterion("EVENT_USER_NAME between", value1, value2, "eventUserName");
            return (Criteria) this;
        }

        public Criteria andEventUserNameNotBetween(String value1, String value2) {
            addCriterion("EVENT_USER_NAME not between", value1, value2, "eventUserName");
            return (Criteria) this;
        }

        public Criteria andQueryFlowIsNull() {
            addCriterion("QUERY_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andQueryFlowIsNotNull() {
            addCriterion("QUERY_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andQueryFlowEqualTo(String value) {
            addCriterion("QUERY_FLOW =", value, "queryFlow");
            return (Criteria) this;
        }

        public Criteria andQueryFlowNotEqualTo(String value) {
            addCriterion("QUERY_FLOW <>", value, "queryFlow");
            return (Criteria) this;
        }

        public Criteria andQueryFlowGreaterThan(String value) {
            addCriterion("QUERY_FLOW >", value, "queryFlow");
            return (Criteria) this;
        }

        public Criteria andQueryFlowGreaterThanOrEqualTo(String value) {
            addCriterion("QUERY_FLOW >=", value, "queryFlow");
            return (Criteria) this;
        }

        public Criteria andQueryFlowLessThan(String value) {
            addCriterion("QUERY_FLOW <", value, "queryFlow");
            return (Criteria) this;
        }

        public Criteria andQueryFlowLessThanOrEqualTo(String value) {
            addCriterion("QUERY_FLOW <=", value, "queryFlow");
            return (Criteria) this;
        }

        public Criteria andQueryFlowLike(String value) {
            addCriterion("QUERY_FLOW like", value, "queryFlow");
            return (Criteria) this;
        }

        public Criteria andQueryFlowNotLike(String value) {
            addCriterion("QUERY_FLOW not like", value, "queryFlow");
            return (Criteria) this;
        }

        public Criteria andQueryFlowIn(List<String> values) {
            addCriterion("QUERY_FLOW in", values, "queryFlow");
            return (Criteria) this;
        }

        public Criteria andQueryFlowNotIn(List<String> values) {
            addCriterion("QUERY_FLOW not in", values, "queryFlow");
            return (Criteria) this;
        }

        public Criteria andQueryFlowBetween(String value1, String value2) {
            addCriterion("QUERY_FLOW between", value1, value2, "queryFlow");
            return (Criteria) this;
        }

        public Criteria andQueryFlowNotBetween(String value1, String value2) {
            addCriterion("QUERY_FLOW not between", value1, value2, "queryFlow");
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