package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class EdcPatientVisitDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EdcPatientVisitDataExample() {
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

        public Criteria andVisitRecordFlowIsNull() {
            addCriterion("VISIT_RECORD_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andVisitRecordFlowIsNotNull() {
            addCriterion("VISIT_RECORD_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andVisitRecordFlowEqualTo(String value) {
            addCriterion("VISIT_RECORD_FLOW =", value, "visitRecordFlow");
            return (Criteria) this;
        }

        public Criteria andVisitRecordFlowNotEqualTo(String value) {
            addCriterion("VISIT_RECORD_FLOW <>", value, "visitRecordFlow");
            return (Criteria) this;
        }

        public Criteria andVisitRecordFlowGreaterThan(String value) {
            addCriterion("VISIT_RECORD_FLOW >", value, "visitRecordFlow");
            return (Criteria) this;
        }

        public Criteria andVisitRecordFlowGreaterThanOrEqualTo(String value) {
            addCriterion("VISIT_RECORD_FLOW >=", value, "visitRecordFlow");
            return (Criteria) this;
        }

        public Criteria andVisitRecordFlowLessThan(String value) {
            addCriterion("VISIT_RECORD_FLOW <", value, "visitRecordFlow");
            return (Criteria) this;
        }

        public Criteria andVisitRecordFlowLessThanOrEqualTo(String value) {
            addCriterion("VISIT_RECORD_FLOW <=", value, "visitRecordFlow");
            return (Criteria) this;
        }

        public Criteria andVisitRecordFlowLike(String value) {
            addCriterion("VISIT_RECORD_FLOW like", value, "visitRecordFlow");
            return (Criteria) this;
        }

        public Criteria andVisitRecordFlowNotLike(String value) {
            addCriterion("VISIT_RECORD_FLOW not like", value, "visitRecordFlow");
            return (Criteria) this;
        }

        public Criteria andVisitRecordFlowIn(List<String> values) {
            addCriterion("VISIT_RECORD_FLOW in", values, "visitRecordFlow");
            return (Criteria) this;
        }

        public Criteria andVisitRecordFlowNotIn(List<String> values) {
            addCriterion("VISIT_RECORD_FLOW not in", values, "visitRecordFlow");
            return (Criteria) this;
        }

        public Criteria andVisitRecordFlowBetween(String value1, String value2) {
            addCriterion("VISIT_RECORD_FLOW between", value1, value2, "visitRecordFlow");
            return (Criteria) this;
        }

        public Criteria andVisitRecordFlowNotBetween(String value1, String value2) {
            addCriterion("VISIT_RECORD_FLOW not between", value1, value2, "visitRecordFlow");
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

        public Criteria andAttrValue1IsNull() {
            addCriterion("ATTR_VALUE1 is null");
            return (Criteria) this;
        }

        public Criteria andAttrValue1IsNotNull() {
            addCriterion("ATTR_VALUE1 is not null");
            return (Criteria) this;
        }

        public Criteria andAttrValue1EqualTo(String value) {
            addCriterion("ATTR_VALUE1 =", value, "attrValue1");
            return (Criteria) this;
        }

        public Criteria andAttrValue1NotEqualTo(String value) {
            addCriterion("ATTR_VALUE1 <>", value, "attrValue1");
            return (Criteria) this;
        }

        public Criteria andAttrValue1GreaterThan(String value) {
            addCriterion("ATTR_VALUE1 >", value, "attrValue1");
            return (Criteria) this;
        }

        public Criteria andAttrValue1GreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_VALUE1 >=", value, "attrValue1");
            return (Criteria) this;
        }

        public Criteria andAttrValue1LessThan(String value) {
            addCriterion("ATTR_VALUE1 <", value, "attrValue1");
            return (Criteria) this;
        }

        public Criteria andAttrValue1LessThanOrEqualTo(String value) {
            addCriterion("ATTR_VALUE1 <=", value, "attrValue1");
            return (Criteria) this;
        }

        public Criteria andAttrValue1Like(String value) {
            addCriterion("ATTR_VALUE1 like", value, "attrValue1");
            return (Criteria) this;
        }

        public Criteria andAttrValue1NotLike(String value) {
            addCriterion("ATTR_VALUE1 not like", value, "attrValue1");
            return (Criteria) this;
        }

        public Criteria andAttrValue1In(List<String> values) {
            addCriterion("ATTR_VALUE1 in", values, "attrValue1");
            return (Criteria) this;
        }

        public Criteria andAttrValue1NotIn(List<String> values) {
            addCriterion("ATTR_VALUE1 not in", values, "attrValue1");
            return (Criteria) this;
        }

        public Criteria andAttrValue1Between(String value1, String value2) {
            addCriterion("ATTR_VALUE1 between", value1, value2, "attrValue1");
            return (Criteria) this;
        }

        public Criteria andAttrValue1NotBetween(String value1, String value2) {
            addCriterion("ATTR_VALUE1 not between", value1, value2, "attrValue1");
            return (Criteria) this;
        }

        public Criteria andAttrValue1TipIsNull() {
            addCriterion("ATTR_VALUE1_TIP is null");
            return (Criteria) this;
        }

        public Criteria andAttrValue1TipIsNotNull() {
            addCriterion("ATTR_VALUE1_TIP is not null");
            return (Criteria) this;
        }

        public Criteria andAttrValue1TipEqualTo(String value) {
            addCriterion("ATTR_VALUE1_TIP =", value, "attrValue1Tip");
            return (Criteria) this;
        }

        public Criteria andAttrValue1TipNotEqualTo(String value) {
            addCriterion("ATTR_VALUE1_TIP <>", value, "attrValue1Tip");
            return (Criteria) this;
        }

        public Criteria andAttrValue1TipGreaterThan(String value) {
            addCriterion("ATTR_VALUE1_TIP >", value, "attrValue1Tip");
            return (Criteria) this;
        }

        public Criteria andAttrValue1TipGreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_VALUE1_TIP >=", value, "attrValue1Tip");
            return (Criteria) this;
        }

        public Criteria andAttrValue1TipLessThan(String value) {
            addCriterion("ATTR_VALUE1_TIP <", value, "attrValue1Tip");
            return (Criteria) this;
        }

        public Criteria andAttrValue1TipLessThanOrEqualTo(String value) {
            addCriterion("ATTR_VALUE1_TIP <=", value, "attrValue1Tip");
            return (Criteria) this;
        }

        public Criteria andAttrValue1TipLike(String value) {
            addCriterion("ATTR_VALUE1_TIP like", value, "attrValue1Tip");
            return (Criteria) this;
        }

        public Criteria andAttrValue1TipNotLike(String value) {
            addCriterion("ATTR_VALUE1_TIP not like", value, "attrValue1Tip");
            return (Criteria) this;
        }

        public Criteria andAttrValue1TipIn(List<String> values) {
            addCriterion("ATTR_VALUE1_TIP in", values, "attrValue1Tip");
            return (Criteria) this;
        }

        public Criteria andAttrValue1TipNotIn(List<String> values) {
            addCriterion("ATTR_VALUE1_TIP not in", values, "attrValue1Tip");
            return (Criteria) this;
        }

        public Criteria andAttrValue1TipBetween(String value1, String value2) {
            addCriterion("ATTR_VALUE1_TIP between", value1, value2, "attrValue1Tip");
            return (Criteria) this;
        }

        public Criteria andAttrValue1TipNotBetween(String value1, String value2) {
            addCriterion("ATTR_VALUE1_TIP not between", value1, value2, "attrValue1Tip");
            return (Criteria) this;
        }

        public Criteria andAttrValue2IsNull() {
            addCriterion("ATTR_VALUE2 is null");
            return (Criteria) this;
        }

        public Criteria andAttrValue2IsNotNull() {
            addCriterion("ATTR_VALUE2 is not null");
            return (Criteria) this;
        }

        public Criteria andAttrValue2EqualTo(String value) {
            addCriterion("ATTR_VALUE2 =", value, "attrValue2");
            return (Criteria) this;
        }

        public Criteria andAttrValue2NotEqualTo(String value) {
            addCriterion("ATTR_VALUE2 <>", value, "attrValue2");
            return (Criteria) this;
        }

        public Criteria andAttrValue2GreaterThan(String value) {
            addCriterion("ATTR_VALUE2 >", value, "attrValue2");
            return (Criteria) this;
        }

        public Criteria andAttrValue2GreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_VALUE2 >=", value, "attrValue2");
            return (Criteria) this;
        }

        public Criteria andAttrValue2LessThan(String value) {
            addCriterion("ATTR_VALUE2 <", value, "attrValue2");
            return (Criteria) this;
        }

        public Criteria andAttrValue2LessThanOrEqualTo(String value) {
            addCriterion("ATTR_VALUE2 <=", value, "attrValue2");
            return (Criteria) this;
        }

        public Criteria andAttrValue2Like(String value) {
            addCriterion("ATTR_VALUE2 like", value, "attrValue2");
            return (Criteria) this;
        }

        public Criteria andAttrValue2NotLike(String value) {
            addCriterion("ATTR_VALUE2 not like", value, "attrValue2");
            return (Criteria) this;
        }

        public Criteria andAttrValue2In(List<String> values) {
            addCriterion("ATTR_VALUE2 in", values, "attrValue2");
            return (Criteria) this;
        }

        public Criteria andAttrValue2NotIn(List<String> values) {
            addCriterion("ATTR_VALUE2 not in", values, "attrValue2");
            return (Criteria) this;
        }

        public Criteria andAttrValue2Between(String value1, String value2) {
            addCriterion("ATTR_VALUE2 between", value1, value2, "attrValue2");
            return (Criteria) this;
        }

        public Criteria andAttrValue2NotBetween(String value1, String value2) {
            addCriterion("ATTR_VALUE2 not between", value1, value2, "attrValue2");
            return (Criteria) this;
        }

        public Criteria andAttrValue2TipIsNull() {
            addCriterion("ATTR_VALUE2_TIP is null");
            return (Criteria) this;
        }

        public Criteria andAttrValue2TipIsNotNull() {
            addCriterion("ATTR_VALUE2_TIP is not null");
            return (Criteria) this;
        }

        public Criteria andAttrValue2TipEqualTo(String value) {
            addCriterion("ATTR_VALUE2_TIP =", value, "attrValue2Tip");
            return (Criteria) this;
        }

        public Criteria andAttrValue2TipNotEqualTo(String value) {
            addCriterion("ATTR_VALUE2_TIP <>", value, "attrValue2Tip");
            return (Criteria) this;
        }

        public Criteria andAttrValue2TipGreaterThan(String value) {
            addCriterion("ATTR_VALUE2_TIP >", value, "attrValue2Tip");
            return (Criteria) this;
        }

        public Criteria andAttrValue2TipGreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_VALUE2_TIP >=", value, "attrValue2Tip");
            return (Criteria) this;
        }

        public Criteria andAttrValue2TipLessThan(String value) {
            addCriterion("ATTR_VALUE2_TIP <", value, "attrValue2Tip");
            return (Criteria) this;
        }

        public Criteria andAttrValue2TipLessThanOrEqualTo(String value) {
            addCriterion("ATTR_VALUE2_TIP <=", value, "attrValue2Tip");
            return (Criteria) this;
        }

        public Criteria andAttrValue2TipLike(String value) {
            addCriterion("ATTR_VALUE2_TIP like", value, "attrValue2Tip");
            return (Criteria) this;
        }

        public Criteria andAttrValue2TipNotLike(String value) {
            addCriterion("ATTR_VALUE2_TIP not like", value, "attrValue2Tip");
            return (Criteria) this;
        }

        public Criteria andAttrValue2TipIn(List<String> values) {
            addCriterion("ATTR_VALUE2_TIP in", values, "attrValue2Tip");
            return (Criteria) this;
        }

        public Criteria andAttrValue2TipNotIn(List<String> values) {
            addCriterion("ATTR_VALUE2_TIP not in", values, "attrValue2Tip");
            return (Criteria) this;
        }

        public Criteria andAttrValue2TipBetween(String value1, String value2) {
            addCriterion("ATTR_VALUE2_TIP between", value1, value2, "attrValue2Tip");
            return (Criteria) this;
        }

        public Criteria andAttrValue2TipNotBetween(String value1, String value2) {
            addCriterion("ATTR_VALUE2_TIP not between", value1, value2, "attrValue2Tip");
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

        public Criteria andAttrValueTipIsNull() {
            addCriterion("ATTR_VALUE_TIP is null");
            return (Criteria) this;
        }

        public Criteria andAttrValueTipIsNotNull() {
            addCriterion("ATTR_VALUE_TIP is not null");
            return (Criteria) this;
        }

        public Criteria andAttrValueTipEqualTo(String value) {
            addCriterion("ATTR_VALUE_TIP =", value, "attrValueTip");
            return (Criteria) this;
        }

        public Criteria andAttrValueTipNotEqualTo(String value) {
            addCriterion("ATTR_VALUE_TIP <>", value, "attrValueTip");
            return (Criteria) this;
        }

        public Criteria andAttrValueTipGreaterThan(String value) {
            addCriterion("ATTR_VALUE_TIP >", value, "attrValueTip");
            return (Criteria) this;
        }

        public Criteria andAttrValueTipGreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_VALUE_TIP >=", value, "attrValueTip");
            return (Criteria) this;
        }

        public Criteria andAttrValueTipLessThan(String value) {
            addCriterion("ATTR_VALUE_TIP <", value, "attrValueTip");
            return (Criteria) this;
        }

        public Criteria andAttrValueTipLessThanOrEqualTo(String value) {
            addCriterion("ATTR_VALUE_TIP <=", value, "attrValueTip");
            return (Criteria) this;
        }

        public Criteria andAttrValueTipLike(String value) {
            addCriterion("ATTR_VALUE_TIP like", value, "attrValueTip");
            return (Criteria) this;
        }

        public Criteria andAttrValueTipNotLike(String value) {
            addCriterion("ATTR_VALUE_TIP not like", value, "attrValueTip");
            return (Criteria) this;
        }

        public Criteria andAttrValueTipIn(List<String> values) {
            addCriterion("ATTR_VALUE_TIP in", values, "attrValueTip");
            return (Criteria) this;
        }

        public Criteria andAttrValueTipNotIn(List<String> values) {
            addCriterion("ATTR_VALUE_TIP not in", values, "attrValueTip");
            return (Criteria) this;
        }

        public Criteria andAttrValueTipBetween(String value1, String value2) {
            addCriterion("ATTR_VALUE_TIP between", value1, value2, "attrValueTip");
            return (Criteria) this;
        }

        public Criteria andAttrValueTipNotBetween(String value1, String value2) {
            addCriterion("ATTR_VALUE_TIP not between", value1, value2, "attrValueTip");
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