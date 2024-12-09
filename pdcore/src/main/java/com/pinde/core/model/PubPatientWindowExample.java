package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class PubPatientWindowExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PubPatientWindowExample() {
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

        public Criteria andPatientNameIsNull() {
            addCriterion("PATIENT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPatientNameIsNotNull() {
            addCriterion("PATIENT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPatientNameEqualTo(String value) {
            addCriterion("PATIENT_NAME =", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotEqualTo(String value) {
            addCriterion("PATIENT_NAME <>", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameGreaterThan(String value) {
            addCriterion("PATIENT_NAME >", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_NAME >=", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameLessThan(String value) {
            addCriterion("PATIENT_NAME <", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_NAME <=", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameLike(String value) {
            addCriterion("PATIENT_NAME like", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotLike(String value) {
            addCriterion("PATIENT_NAME not like", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameIn(List<String> values) {
            addCriterion("PATIENT_NAME in", values, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotIn(List<String> values) {
            addCriterion("PATIENT_NAME not in", values, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameBetween(String value1, String value2) {
            addCriterion("PATIENT_NAME between", value1, value2, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotBetween(String value1, String value2) {
            addCriterion("PATIENT_NAME not between", value1, value2, "patientName");
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

        public Criteria andInDateIsNull() {
            addCriterion("IN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andInDateIsNotNull() {
            addCriterion("IN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andInDateEqualTo(String value) {
            addCriterion("IN_DATE =", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateNotEqualTo(String value) {
            addCriterion("IN_DATE <>", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateGreaterThan(String value) {
            addCriterion("IN_DATE >", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateGreaterThanOrEqualTo(String value) {
            addCriterion("IN_DATE >=", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateLessThan(String value) {
            addCriterion("IN_DATE <", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateLessThanOrEqualTo(String value) {
            addCriterion("IN_DATE <=", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateLike(String value) {
            addCriterion("IN_DATE like", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateNotLike(String value) {
            addCriterion("IN_DATE not like", value, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateIn(List<String> values) {
            addCriterion("IN_DATE in", values, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateNotIn(List<String> values) {
            addCriterion("IN_DATE not in", values, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateBetween(String value1, String value2) {
            addCriterion("IN_DATE between", value1, value2, "inDate");
            return (Criteria) this;
        }

        public Criteria andInDateNotBetween(String value1, String value2) {
            addCriterion("IN_DATE not between", value1, value2, "inDate");
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

        public Criteria andVisitDateIsNull() {
            addCriterion("VISIT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andVisitDateIsNotNull() {
            addCriterion("VISIT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andVisitDateEqualTo(String value) {
            addCriterion("VISIT_DATE =", value, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateNotEqualTo(String value) {
            addCriterion("VISIT_DATE <>", value, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateGreaterThan(String value) {
            addCriterion("VISIT_DATE >", value, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateGreaterThanOrEqualTo(String value) {
            addCriterion("VISIT_DATE >=", value, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateLessThan(String value) {
            addCriterion("VISIT_DATE <", value, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateLessThanOrEqualTo(String value) {
            addCriterion("VISIT_DATE <=", value, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateLike(String value) {
            addCriterion("VISIT_DATE like", value, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateNotLike(String value) {
            addCriterion("VISIT_DATE not like", value, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateIn(List<String> values) {
            addCriterion("VISIT_DATE in", values, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateNotIn(List<String> values) {
            addCriterion("VISIT_DATE not in", values, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateBetween(String value1, String value2) {
            addCriterion("VISIT_DATE between", value1, value2, "visitDate");
            return (Criteria) this;
        }

        public Criteria andVisitDateNotBetween(String value1, String value2) {
            addCriterion("VISIT_DATE not between", value1, value2, "visitDate");
            return (Criteria) this;
        }

        public Criteria andWindowVisitLeftIsNull() {
            addCriterion("WINDOW_VISIT_LEFT is null");
            return (Criteria) this;
        }

        public Criteria andWindowVisitLeftIsNotNull() {
            addCriterion("WINDOW_VISIT_LEFT is not null");
            return (Criteria) this;
        }

        public Criteria andWindowVisitLeftEqualTo(String value) {
            addCriterion("WINDOW_VISIT_LEFT =", value, "windowVisitLeft");
            return (Criteria) this;
        }

        public Criteria andWindowVisitLeftNotEqualTo(String value) {
            addCriterion("WINDOW_VISIT_LEFT <>", value, "windowVisitLeft");
            return (Criteria) this;
        }

        public Criteria andWindowVisitLeftGreaterThan(String value) {
            addCriterion("WINDOW_VISIT_LEFT >", value, "windowVisitLeft");
            return (Criteria) this;
        }

        public Criteria andWindowVisitLeftGreaterThanOrEqualTo(String value) {
            addCriterion("WINDOW_VISIT_LEFT >=", value, "windowVisitLeft");
            return (Criteria) this;
        }

        public Criteria andWindowVisitLeftLessThan(String value) {
            addCriterion("WINDOW_VISIT_LEFT <", value, "windowVisitLeft");
            return (Criteria) this;
        }

        public Criteria andWindowVisitLeftLessThanOrEqualTo(String value) {
            addCriterion("WINDOW_VISIT_LEFT <=", value, "windowVisitLeft");
            return (Criteria) this;
        }

        public Criteria andWindowVisitLeftLike(String value) {
            addCriterion("WINDOW_VISIT_LEFT like", value, "windowVisitLeft");
            return (Criteria) this;
        }

        public Criteria andWindowVisitLeftNotLike(String value) {
            addCriterion("WINDOW_VISIT_LEFT not like", value, "windowVisitLeft");
            return (Criteria) this;
        }

        public Criteria andWindowVisitLeftIn(List<String> values) {
            addCriterion("WINDOW_VISIT_LEFT in", values, "windowVisitLeft");
            return (Criteria) this;
        }

        public Criteria andWindowVisitLeftNotIn(List<String> values) {
            addCriterion("WINDOW_VISIT_LEFT not in", values, "windowVisitLeft");
            return (Criteria) this;
        }

        public Criteria andWindowVisitLeftBetween(String value1, String value2) {
            addCriterion("WINDOW_VISIT_LEFT between", value1, value2, "windowVisitLeft");
            return (Criteria) this;
        }

        public Criteria andWindowVisitLeftNotBetween(String value1, String value2) {
            addCriterion("WINDOW_VISIT_LEFT not between", value1, value2, "windowVisitLeft");
            return (Criteria) this;
        }

        public Criteria andWindowVisitRightIsNull() {
            addCriterion("WINDOW_VISIT_RIGHT is null");
            return (Criteria) this;
        }

        public Criteria andWindowVisitRightIsNotNull() {
            addCriterion("WINDOW_VISIT_RIGHT is not null");
            return (Criteria) this;
        }

        public Criteria andWindowVisitRightEqualTo(String value) {
            addCriterion("WINDOW_VISIT_RIGHT =", value, "windowVisitRight");
            return (Criteria) this;
        }

        public Criteria andWindowVisitRightNotEqualTo(String value) {
            addCriterion("WINDOW_VISIT_RIGHT <>", value, "windowVisitRight");
            return (Criteria) this;
        }

        public Criteria andWindowVisitRightGreaterThan(String value) {
            addCriterion("WINDOW_VISIT_RIGHT >", value, "windowVisitRight");
            return (Criteria) this;
        }

        public Criteria andWindowVisitRightGreaterThanOrEqualTo(String value) {
            addCriterion("WINDOW_VISIT_RIGHT >=", value, "windowVisitRight");
            return (Criteria) this;
        }

        public Criteria andWindowVisitRightLessThan(String value) {
            addCriterion("WINDOW_VISIT_RIGHT <", value, "windowVisitRight");
            return (Criteria) this;
        }

        public Criteria andWindowVisitRightLessThanOrEqualTo(String value) {
            addCriterion("WINDOW_VISIT_RIGHT <=", value, "windowVisitRight");
            return (Criteria) this;
        }

        public Criteria andWindowVisitRightLike(String value) {
            addCriterion("WINDOW_VISIT_RIGHT like", value, "windowVisitRight");
            return (Criteria) this;
        }

        public Criteria andWindowVisitRightNotLike(String value) {
            addCriterion("WINDOW_VISIT_RIGHT not like", value, "windowVisitRight");
            return (Criteria) this;
        }

        public Criteria andWindowVisitRightIn(List<String> values) {
            addCriterion("WINDOW_VISIT_RIGHT in", values, "windowVisitRight");
            return (Criteria) this;
        }

        public Criteria andWindowVisitRightNotIn(List<String> values) {
            addCriterion("WINDOW_VISIT_RIGHT not in", values, "windowVisitRight");
            return (Criteria) this;
        }

        public Criteria andWindowVisitRightBetween(String value1, String value2) {
            addCriterion("WINDOW_VISIT_RIGHT between", value1, value2, "windowVisitRight");
            return (Criteria) this;
        }

        public Criteria andWindowVisitRightNotBetween(String value1, String value2) {
            addCriterion("WINDOW_VISIT_RIGHT not between", value1, value2, "windowVisitRight");
            return (Criteria) this;
        }

        public Criteria andWindowLeftIsNull() {
            addCriterion("WINDOW_LEFT is null");
            return (Criteria) this;
        }

        public Criteria andWindowLeftIsNotNull() {
            addCriterion("WINDOW_LEFT is not null");
            return (Criteria) this;
        }

        public Criteria andWindowLeftEqualTo(String value) {
            addCriterion("WINDOW_LEFT =", value, "windowLeft");
            return (Criteria) this;
        }

        public Criteria andWindowLeftNotEqualTo(String value) {
            addCriterion("WINDOW_LEFT <>", value, "windowLeft");
            return (Criteria) this;
        }

        public Criteria andWindowLeftGreaterThan(String value) {
            addCriterion("WINDOW_LEFT >", value, "windowLeft");
            return (Criteria) this;
        }

        public Criteria andWindowLeftGreaterThanOrEqualTo(String value) {
            addCriterion("WINDOW_LEFT >=", value, "windowLeft");
            return (Criteria) this;
        }

        public Criteria andWindowLeftLessThan(String value) {
            addCriterion("WINDOW_LEFT <", value, "windowLeft");
            return (Criteria) this;
        }

        public Criteria andWindowLeftLessThanOrEqualTo(String value) {
            addCriterion("WINDOW_LEFT <=", value, "windowLeft");
            return (Criteria) this;
        }

        public Criteria andWindowLeftLike(String value) {
            addCriterion("WINDOW_LEFT like", value, "windowLeft");
            return (Criteria) this;
        }

        public Criteria andWindowLeftNotLike(String value) {
            addCriterion("WINDOW_LEFT not like", value, "windowLeft");
            return (Criteria) this;
        }

        public Criteria andWindowLeftIn(List<String> values) {
            addCriterion("WINDOW_LEFT in", values, "windowLeft");
            return (Criteria) this;
        }

        public Criteria andWindowLeftNotIn(List<String> values) {
            addCriterion("WINDOW_LEFT not in", values, "windowLeft");
            return (Criteria) this;
        }

        public Criteria andWindowLeftBetween(String value1, String value2) {
            addCriterion("WINDOW_LEFT between", value1, value2, "windowLeft");
            return (Criteria) this;
        }

        public Criteria andWindowLeftNotBetween(String value1, String value2) {
            addCriterion("WINDOW_LEFT not between", value1, value2, "windowLeft");
            return (Criteria) this;
        }

        public Criteria andWindowRightIsNull() {
            addCriterion("WINDOW_RIGHT is null");
            return (Criteria) this;
        }

        public Criteria andWindowRightIsNotNull() {
            addCriterion("WINDOW_RIGHT is not null");
            return (Criteria) this;
        }

        public Criteria andWindowRightEqualTo(String value) {
            addCriterion("WINDOW_RIGHT =", value, "windowRight");
            return (Criteria) this;
        }

        public Criteria andWindowRightNotEqualTo(String value) {
            addCriterion("WINDOW_RIGHT <>", value, "windowRight");
            return (Criteria) this;
        }

        public Criteria andWindowRightGreaterThan(String value) {
            addCriterion("WINDOW_RIGHT >", value, "windowRight");
            return (Criteria) this;
        }

        public Criteria andWindowRightGreaterThanOrEqualTo(String value) {
            addCriterion("WINDOW_RIGHT >=", value, "windowRight");
            return (Criteria) this;
        }

        public Criteria andWindowRightLessThan(String value) {
            addCriterion("WINDOW_RIGHT <", value, "windowRight");
            return (Criteria) this;
        }

        public Criteria andWindowRightLessThanOrEqualTo(String value) {
            addCriterion("WINDOW_RIGHT <=", value, "windowRight");
            return (Criteria) this;
        }

        public Criteria andWindowRightLike(String value) {
            addCriterion("WINDOW_RIGHT like", value, "windowRight");
            return (Criteria) this;
        }

        public Criteria andWindowRightNotLike(String value) {
            addCriterion("WINDOW_RIGHT not like", value, "windowRight");
            return (Criteria) this;
        }

        public Criteria andWindowRightIn(List<String> values) {
            addCriterion("WINDOW_RIGHT in", values, "windowRight");
            return (Criteria) this;
        }

        public Criteria andWindowRightNotIn(List<String> values) {
            addCriterion("WINDOW_RIGHT not in", values, "windowRight");
            return (Criteria) this;
        }

        public Criteria andWindowRightBetween(String value1, String value2) {
            addCriterion("WINDOW_RIGHT between", value1, value2, "windowRight");
            return (Criteria) this;
        }

        public Criteria andWindowRightNotBetween(String value1, String value2) {
            addCriterion("WINDOW_RIGHT not between", value1, value2, "windowRight");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeIdIsNull() {
            addCriterion("OUT_WINDOW_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeIdIsNotNull() {
            addCriterion("OUT_WINDOW_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeIdEqualTo(String value) {
            addCriterion("OUT_WINDOW_TYPE_ID =", value, "outWindowTypeId");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeIdNotEqualTo(String value) {
            addCriterion("OUT_WINDOW_TYPE_ID <>", value, "outWindowTypeId");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeIdGreaterThan(String value) {
            addCriterion("OUT_WINDOW_TYPE_ID >", value, "outWindowTypeId");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("OUT_WINDOW_TYPE_ID >=", value, "outWindowTypeId");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeIdLessThan(String value) {
            addCriterion("OUT_WINDOW_TYPE_ID <", value, "outWindowTypeId");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeIdLessThanOrEqualTo(String value) {
            addCriterion("OUT_WINDOW_TYPE_ID <=", value, "outWindowTypeId");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeIdLike(String value) {
            addCriterion("OUT_WINDOW_TYPE_ID like", value, "outWindowTypeId");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeIdNotLike(String value) {
            addCriterion("OUT_WINDOW_TYPE_ID not like", value, "outWindowTypeId");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeIdIn(List<String> values) {
            addCriterion("OUT_WINDOW_TYPE_ID in", values, "outWindowTypeId");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeIdNotIn(List<String> values) {
            addCriterion("OUT_WINDOW_TYPE_ID not in", values, "outWindowTypeId");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeIdBetween(String value1, String value2) {
            addCriterion("OUT_WINDOW_TYPE_ID between", value1, value2, "outWindowTypeId");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeIdNotBetween(String value1, String value2) {
            addCriterion("OUT_WINDOW_TYPE_ID not between", value1, value2, "outWindowTypeId");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeNameIsNull() {
            addCriterion("OUT_WINDOW_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeNameIsNotNull() {
            addCriterion("OUT_WINDOW_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeNameEqualTo(String value) {
            addCriterion("OUT_WINDOW_TYPE_NAME =", value, "outWindowTypeName");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeNameNotEqualTo(String value) {
            addCriterion("OUT_WINDOW_TYPE_NAME <>", value, "outWindowTypeName");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeNameGreaterThan(String value) {
            addCriterion("OUT_WINDOW_TYPE_NAME >", value, "outWindowTypeName");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("OUT_WINDOW_TYPE_NAME >=", value, "outWindowTypeName");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeNameLessThan(String value) {
            addCriterion("OUT_WINDOW_TYPE_NAME <", value, "outWindowTypeName");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeNameLessThanOrEqualTo(String value) {
            addCriterion("OUT_WINDOW_TYPE_NAME <=", value, "outWindowTypeName");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeNameLike(String value) {
            addCriterion("OUT_WINDOW_TYPE_NAME like", value, "outWindowTypeName");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeNameNotLike(String value) {
            addCriterion("OUT_WINDOW_TYPE_NAME not like", value, "outWindowTypeName");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeNameIn(List<String> values) {
            addCriterion("OUT_WINDOW_TYPE_NAME in", values, "outWindowTypeName");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeNameNotIn(List<String> values) {
            addCriterion("OUT_WINDOW_TYPE_NAME not in", values, "outWindowTypeName");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeNameBetween(String value1, String value2) {
            addCriterion("OUT_WINDOW_TYPE_NAME between", value1, value2, "outWindowTypeName");
            return (Criteria) this;
        }

        public Criteria andOutWindowTypeNameNotBetween(String value1, String value2) {
            addCriterion("OUT_WINDOW_TYPE_NAME not between", value1, value2, "outWindowTypeName");
            return (Criteria) this;
        }

        public Criteria andOutWindowDaysIsNull() {
            addCriterion("OUT_WINDOW_DAYS is null");
            return (Criteria) this;
        }

        public Criteria andOutWindowDaysIsNotNull() {
            addCriterion("OUT_WINDOW_DAYS is not null");
            return (Criteria) this;
        }

        public Criteria andOutWindowDaysEqualTo(String value) {
            addCriterion("OUT_WINDOW_DAYS =", value, "outWindowDays");
            return (Criteria) this;
        }

        public Criteria andOutWindowDaysNotEqualTo(String value) {
            addCriterion("OUT_WINDOW_DAYS <>", value, "outWindowDays");
            return (Criteria) this;
        }

        public Criteria andOutWindowDaysGreaterThan(String value) {
            addCriterion("OUT_WINDOW_DAYS >", value, "outWindowDays");
            return (Criteria) this;
        }

        public Criteria andOutWindowDaysGreaterThanOrEqualTo(String value) {
            addCriterion("OUT_WINDOW_DAYS >=", value, "outWindowDays");
            return (Criteria) this;
        }

        public Criteria andOutWindowDaysLessThan(String value) {
            addCriterion("OUT_WINDOW_DAYS <", value, "outWindowDays");
            return (Criteria) this;
        }

        public Criteria andOutWindowDaysLessThanOrEqualTo(String value) {
            addCriterion("OUT_WINDOW_DAYS <=", value, "outWindowDays");
            return (Criteria) this;
        }

        public Criteria andOutWindowDaysLike(String value) {
            addCriterion("OUT_WINDOW_DAYS like", value, "outWindowDays");
            return (Criteria) this;
        }

        public Criteria andOutWindowDaysNotLike(String value) {
            addCriterion("OUT_WINDOW_DAYS not like", value, "outWindowDays");
            return (Criteria) this;
        }

        public Criteria andOutWindowDaysIn(List<String> values) {
            addCriterion("OUT_WINDOW_DAYS in", values, "outWindowDays");
            return (Criteria) this;
        }

        public Criteria andOutWindowDaysNotIn(List<String> values) {
            addCriterion("OUT_WINDOW_DAYS not in", values, "outWindowDays");
            return (Criteria) this;
        }

        public Criteria andOutWindowDaysBetween(String value1, String value2) {
            addCriterion("OUT_WINDOW_DAYS between", value1, value2, "outWindowDays");
            return (Criteria) this;
        }

        public Criteria andOutWindowDaysNotBetween(String value1, String value2) {
            addCriterion("OUT_WINDOW_DAYS not between", value1, value2, "outWindowDays");
            return (Criteria) this;
        }

        public Criteria andIsNoticeIsNull() {
            addCriterion("IS_NOTICE is null");
            return (Criteria) this;
        }

        public Criteria andIsNoticeIsNotNull() {
            addCriterion("IS_NOTICE is not null");
            return (Criteria) this;
        }

        public Criteria andIsNoticeEqualTo(String value) {
            addCriterion("IS_NOTICE =", value, "isNotice");
            return (Criteria) this;
        }

        public Criteria andIsNoticeNotEqualTo(String value) {
            addCriterion("IS_NOTICE <>", value, "isNotice");
            return (Criteria) this;
        }

        public Criteria andIsNoticeGreaterThan(String value) {
            addCriterion("IS_NOTICE >", value, "isNotice");
            return (Criteria) this;
        }

        public Criteria andIsNoticeGreaterThanOrEqualTo(String value) {
            addCriterion("IS_NOTICE >=", value, "isNotice");
            return (Criteria) this;
        }

        public Criteria andIsNoticeLessThan(String value) {
            addCriterion("IS_NOTICE <", value, "isNotice");
            return (Criteria) this;
        }

        public Criteria andIsNoticeLessThanOrEqualTo(String value) {
            addCriterion("IS_NOTICE <=", value, "isNotice");
            return (Criteria) this;
        }

        public Criteria andIsNoticeLike(String value) {
            addCriterion("IS_NOTICE like", value, "isNotice");
            return (Criteria) this;
        }

        public Criteria andIsNoticeNotLike(String value) {
            addCriterion("IS_NOTICE not like", value, "isNotice");
            return (Criteria) this;
        }

        public Criteria andIsNoticeIn(List<String> values) {
            addCriterion("IS_NOTICE in", values, "isNotice");
            return (Criteria) this;
        }

        public Criteria andIsNoticeNotIn(List<String> values) {
            addCriterion("IS_NOTICE not in", values, "isNotice");
            return (Criteria) this;
        }

        public Criteria andIsNoticeBetween(String value1, String value2) {
            addCriterion("IS_NOTICE between", value1, value2, "isNotice");
            return (Criteria) this;
        }

        public Criteria andIsNoticeNotBetween(String value1, String value2) {
            addCriterion("IS_NOTICE not between", value1, value2, "isNotice");
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