package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class PubPatientExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PubPatientExample() {
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

        public Criteria andPatientSeqIsNull() {
            addCriterion("PATIENT_SEQ is null");
            return (Criteria) this;
        }

        public Criteria andPatientSeqIsNotNull() {
            addCriterion("PATIENT_SEQ is not null");
            return (Criteria) this;
        }

        public Criteria andPatientSeqEqualTo(Integer value) {
            addCriterion("PATIENT_SEQ =", value, "patientSeq");
            return (Criteria) this;
        }

        public Criteria andPatientSeqNotEqualTo(Integer value) {
            addCriterion("PATIENT_SEQ <>", value, "patientSeq");
            return (Criteria) this;
        }

        public Criteria andPatientSeqGreaterThan(Integer value) {
            addCriterion("PATIENT_SEQ >", value, "patientSeq");
            return (Criteria) this;
        }

        public Criteria andPatientSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("PATIENT_SEQ >=", value, "patientSeq");
            return (Criteria) this;
        }

        public Criteria andPatientSeqLessThan(Integer value) {
            addCriterion("PATIENT_SEQ <", value, "patientSeq");
            return (Criteria) this;
        }

        public Criteria andPatientSeqLessThanOrEqualTo(Integer value) {
            addCriterion("PATIENT_SEQ <=", value, "patientSeq");
            return (Criteria) this;
        }

        public Criteria andPatientSeqIn(List<Integer> values) {
            addCriterion("PATIENT_SEQ in", values, "patientSeq");
            return (Criteria) this;
        }

        public Criteria andPatientSeqNotIn(List<Integer> values) {
            addCriterion("PATIENT_SEQ not in", values, "patientSeq");
            return (Criteria) this;
        }

        public Criteria andPatientSeqBetween(Integer value1, Integer value2) {
            addCriterion("PATIENT_SEQ between", value1, value2, "patientSeq");
            return (Criteria) this;
        }

        public Criteria andPatientSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("PATIENT_SEQ not between", value1, value2, "patientSeq");
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

        public Criteria andSexIdIsNull() {
            addCriterion("SEX_ID is null");
            return (Criteria) this;
        }

        public Criteria andSexIdIsNotNull() {
            addCriterion("SEX_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSexIdEqualTo(String value) {
            addCriterion("SEX_ID =", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotEqualTo(String value) {
            addCriterion("SEX_ID <>", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdGreaterThan(String value) {
            addCriterion("SEX_ID >", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdGreaterThanOrEqualTo(String value) {
            addCriterion("SEX_ID >=", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdLessThan(String value) {
            addCriterion("SEX_ID <", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdLessThanOrEqualTo(String value) {
            addCriterion("SEX_ID <=", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdLike(String value) {
            addCriterion("SEX_ID like", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotLike(String value) {
            addCriterion("SEX_ID not like", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdIn(List<String> values) {
            addCriterion("SEX_ID in", values, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotIn(List<String> values) {
            addCriterion("SEX_ID not in", values, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdBetween(String value1, String value2) {
            addCriterion("SEX_ID between", value1, value2, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotBetween(String value1, String value2) {
            addCriterion("SEX_ID not between", value1, value2, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexNameIsNull() {
            addCriterion("SEX_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSexNameIsNotNull() {
            addCriterion("SEX_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSexNameEqualTo(String value) {
            addCriterion("SEX_NAME =", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotEqualTo(String value) {
            addCriterion("SEX_NAME <>", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameGreaterThan(String value) {
            addCriterion("SEX_NAME >", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameGreaterThanOrEqualTo(String value) {
            addCriterion("SEX_NAME >=", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameLessThan(String value) {
            addCriterion("SEX_NAME <", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameLessThanOrEqualTo(String value) {
            addCriterion("SEX_NAME <=", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameLike(String value) {
            addCriterion("SEX_NAME like", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotLike(String value) {
            addCriterion("SEX_NAME not like", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameIn(List<String> values) {
            addCriterion("SEX_NAME in", values, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotIn(List<String> values) {
            addCriterion("SEX_NAME not in", values, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameBetween(String value1, String value2) {
            addCriterion("SEX_NAME between", value1, value2, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotBetween(String value1, String value2) {
            addCriterion("SEX_NAME not between", value1, value2, "sexName");
            return (Criteria) this;
        }

        public Criteria andIdNoIsNull() {
            addCriterion("ID_NO is null");
            return (Criteria) this;
        }

        public Criteria andIdNoIsNotNull() {
            addCriterion("ID_NO is not null");
            return (Criteria) this;
        }

        public Criteria andIdNoEqualTo(String value) {
            addCriterion("ID_NO =", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotEqualTo(String value) {
            addCriterion("ID_NO <>", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoGreaterThan(String value) {
            addCriterion("ID_NO >", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoGreaterThanOrEqualTo(String value) {
            addCriterion("ID_NO >=", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLessThan(String value) {
            addCriterion("ID_NO <", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLessThanOrEqualTo(String value) {
            addCriterion("ID_NO <=", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLike(String value) {
            addCriterion("ID_NO like", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotLike(String value) {
            addCriterion("ID_NO not like", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoIn(List<String> values) {
            addCriterion("ID_NO in", values, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotIn(List<String> values) {
            addCriterion("ID_NO not in", values, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoBetween(String value1, String value2) {
            addCriterion("ID_NO between", value1, value2, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotBetween(String value1, String value2) {
            addCriterion("ID_NO not between", value1, value2, "idNo");
            return (Criteria) this;
        }

        public Criteria andPatientStageIdIsNull() {
            addCriterion("PATIENT_STAGE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPatientStageIdIsNotNull() {
            addCriterion("PATIENT_STAGE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPatientStageIdEqualTo(String value) {
            addCriterion("PATIENT_STAGE_ID =", value, "patientStageId");
            return (Criteria) this;
        }

        public Criteria andPatientStageIdNotEqualTo(String value) {
            addCriterion("PATIENT_STAGE_ID <>", value, "patientStageId");
            return (Criteria) this;
        }

        public Criteria andPatientStageIdGreaterThan(String value) {
            addCriterion("PATIENT_STAGE_ID >", value, "patientStageId");
            return (Criteria) this;
        }

        public Criteria andPatientStageIdGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_STAGE_ID >=", value, "patientStageId");
            return (Criteria) this;
        }

        public Criteria andPatientStageIdLessThan(String value) {
            addCriterion("PATIENT_STAGE_ID <", value, "patientStageId");
            return (Criteria) this;
        }

        public Criteria andPatientStageIdLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_STAGE_ID <=", value, "patientStageId");
            return (Criteria) this;
        }

        public Criteria andPatientStageIdLike(String value) {
            addCriterion("PATIENT_STAGE_ID like", value, "patientStageId");
            return (Criteria) this;
        }

        public Criteria andPatientStageIdNotLike(String value) {
            addCriterion("PATIENT_STAGE_ID not like", value, "patientStageId");
            return (Criteria) this;
        }

        public Criteria andPatientStageIdIn(List<String> values) {
            addCriterion("PATIENT_STAGE_ID in", values, "patientStageId");
            return (Criteria) this;
        }

        public Criteria andPatientStageIdNotIn(List<String> values) {
            addCriterion("PATIENT_STAGE_ID not in", values, "patientStageId");
            return (Criteria) this;
        }

        public Criteria andPatientStageIdBetween(String value1, String value2) {
            addCriterion("PATIENT_STAGE_ID between", value1, value2, "patientStageId");
            return (Criteria) this;
        }

        public Criteria andPatientStageIdNotBetween(String value1, String value2) {
            addCriterion("PATIENT_STAGE_ID not between", value1, value2, "patientStageId");
            return (Criteria) this;
        }

        public Criteria andPatientStageNameIsNull() {
            addCriterion("PATIENT_STAGE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPatientStageNameIsNotNull() {
            addCriterion("PATIENT_STAGE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPatientStageNameEqualTo(String value) {
            addCriterion("PATIENT_STAGE_NAME =", value, "patientStageName");
            return (Criteria) this;
        }

        public Criteria andPatientStageNameNotEqualTo(String value) {
            addCriterion("PATIENT_STAGE_NAME <>", value, "patientStageName");
            return (Criteria) this;
        }

        public Criteria andPatientStageNameGreaterThan(String value) {
            addCriterion("PATIENT_STAGE_NAME >", value, "patientStageName");
            return (Criteria) this;
        }

        public Criteria andPatientStageNameGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_STAGE_NAME >=", value, "patientStageName");
            return (Criteria) this;
        }

        public Criteria andPatientStageNameLessThan(String value) {
            addCriterion("PATIENT_STAGE_NAME <", value, "patientStageName");
            return (Criteria) this;
        }

        public Criteria andPatientStageNameLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_STAGE_NAME <=", value, "patientStageName");
            return (Criteria) this;
        }

        public Criteria andPatientStageNameLike(String value) {
            addCriterion("PATIENT_STAGE_NAME like", value, "patientStageName");
            return (Criteria) this;
        }

        public Criteria andPatientStageNameNotLike(String value) {
            addCriterion("PATIENT_STAGE_NAME not like", value, "patientStageName");
            return (Criteria) this;
        }

        public Criteria andPatientStageNameIn(List<String> values) {
            addCriterion("PATIENT_STAGE_NAME in", values, "patientStageName");
            return (Criteria) this;
        }

        public Criteria andPatientStageNameNotIn(List<String> values) {
            addCriterion("PATIENT_STAGE_NAME not in", values, "patientStageName");
            return (Criteria) this;
        }

        public Criteria andPatientStageNameBetween(String value1, String value2) {
            addCriterion("PATIENT_STAGE_NAME between", value1, value2, "patientStageName");
            return (Criteria) this;
        }

        public Criteria andPatientStageNameNotBetween(String value1, String value2) {
            addCriterion("PATIENT_STAGE_NAME not between", value1, value2, "patientStageName");
            return (Criteria) this;
        }

        public Criteria andPatientAgeIsNull() {
            addCriterion("PATIENT_AGE is null");
            return (Criteria) this;
        }

        public Criteria andPatientAgeIsNotNull() {
            addCriterion("PATIENT_AGE is not null");
            return (Criteria) this;
        }

        public Criteria andPatientAgeEqualTo(String value) {
            addCriterion("PATIENT_AGE =", value, "patientAge");
            return (Criteria) this;
        }

        public Criteria andPatientAgeNotEqualTo(String value) {
            addCriterion("PATIENT_AGE <>", value, "patientAge");
            return (Criteria) this;
        }

        public Criteria andPatientAgeGreaterThan(String value) {
            addCriterion("PATIENT_AGE >", value, "patientAge");
            return (Criteria) this;
        }

        public Criteria andPatientAgeGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_AGE >=", value, "patientAge");
            return (Criteria) this;
        }

        public Criteria andPatientAgeLessThan(String value) {
            addCriterion("PATIENT_AGE <", value, "patientAge");
            return (Criteria) this;
        }

        public Criteria andPatientAgeLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_AGE <=", value, "patientAge");
            return (Criteria) this;
        }

        public Criteria andPatientAgeLike(String value) {
            addCriterion("PATIENT_AGE like", value, "patientAge");
            return (Criteria) this;
        }

        public Criteria andPatientAgeNotLike(String value) {
            addCriterion("PATIENT_AGE not like", value, "patientAge");
            return (Criteria) this;
        }

        public Criteria andPatientAgeIn(List<String> values) {
            addCriterion("PATIENT_AGE in", values, "patientAge");
            return (Criteria) this;
        }

        public Criteria andPatientAgeNotIn(List<String> values) {
            addCriterion("PATIENT_AGE not in", values, "patientAge");
            return (Criteria) this;
        }

        public Criteria andPatientAgeBetween(String value1, String value2) {
            addCriterion("PATIENT_AGE between", value1, value2, "patientAge");
            return (Criteria) this;
        }

        public Criteria andPatientAgeNotBetween(String value1, String value2) {
            addCriterion("PATIENT_AGE not between", value1, value2, "patientAge");
            return (Criteria) this;
        }

        public Criteria andPatientBirthdayIsNull() {
            addCriterion("PATIENT_BIRTHDAY is null");
            return (Criteria) this;
        }

        public Criteria andPatientBirthdayIsNotNull() {
            addCriterion("PATIENT_BIRTHDAY is not null");
            return (Criteria) this;
        }

        public Criteria andPatientBirthdayEqualTo(String value) {
            addCriterion("PATIENT_BIRTHDAY =", value, "patientBirthday");
            return (Criteria) this;
        }

        public Criteria andPatientBirthdayNotEqualTo(String value) {
            addCriterion("PATIENT_BIRTHDAY <>", value, "patientBirthday");
            return (Criteria) this;
        }

        public Criteria andPatientBirthdayGreaterThan(String value) {
            addCriterion("PATIENT_BIRTHDAY >", value, "patientBirthday");
            return (Criteria) this;
        }

        public Criteria andPatientBirthdayGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_BIRTHDAY >=", value, "patientBirthday");
            return (Criteria) this;
        }

        public Criteria andPatientBirthdayLessThan(String value) {
            addCriterion("PATIENT_BIRTHDAY <", value, "patientBirthday");
            return (Criteria) this;
        }

        public Criteria andPatientBirthdayLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_BIRTHDAY <=", value, "patientBirthday");
            return (Criteria) this;
        }

        public Criteria andPatientBirthdayLike(String value) {
            addCriterion("PATIENT_BIRTHDAY like", value, "patientBirthday");
            return (Criteria) this;
        }

        public Criteria andPatientBirthdayNotLike(String value) {
            addCriterion("PATIENT_BIRTHDAY not like", value, "patientBirthday");
            return (Criteria) this;
        }

        public Criteria andPatientBirthdayIn(List<String> values) {
            addCriterion("PATIENT_BIRTHDAY in", values, "patientBirthday");
            return (Criteria) this;
        }

        public Criteria andPatientBirthdayNotIn(List<String> values) {
            addCriterion("PATIENT_BIRTHDAY not in", values, "patientBirthday");
            return (Criteria) this;
        }

        public Criteria andPatientBirthdayBetween(String value1, String value2) {
            addCriterion("PATIENT_BIRTHDAY between", value1, value2, "patientBirthday");
            return (Criteria) this;
        }

        public Criteria andPatientBirthdayNotBetween(String value1, String value2) {
            addCriterion("PATIENT_BIRTHDAY not between", value1, value2, "patientBirthday");
            return (Criteria) this;
        }

        public Criteria andPatientHeightIsNull() {
            addCriterion("PATIENT_HEIGHT is null");
            return (Criteria) this;
        }

        public Criteria andPatientHeightIsNotNull() {
            addCriterion("PATIENT_HEIGHT is not null");
            return (Criteria) this;
        }

        public Criteria andPatientHeightEqualTo(String value) {
            addCriterion("PATIENT_HEIGHT =", value, "patientHeight");
            return (Criteria) this;
        }

        public Criteria andPatientHeightNotEqualTo(String value) {
            addCriterion("PATIENT_HEIGHT <>", value, "patientHeight");
            return (Criteria) this;
        }

        public Criteria andPatientHeightGreaterThan(String value) {
            addCriterion("PATIENT_HEIGHT >", value, "patientHeight");
            return (Criteria) this;
        }

        public Criteria andPatientHeightGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_HEIGHT >=", value, "patientHeight");
            return (Criteria) this;
        }

        public Criteria andPatientHeightLessThan(String value) {
            addCriterion("PATIENT_HEIGHT <", value, "patientHeight");
            return (Criteria) this;
        }

        public Criteria andPatientHeightLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_HEIGHT <=", value, "patientHeight");
            return (Criteria) this;
        }

        public Criteria andPatientHeightLike(String value) {
            addCriterion("PATIENT_HEIGHT like", value, "patientHeight");
            return (Criteria) this;
        }

        public Criteria andPatientHeightNotLike(String value) {
            addCriterion("PATIENT_HEIGHT not like", value, "patientHeight");
            return (Criteria) this;
        }

        public Criteria andPatientHeightIn(List<String> values) {
            addCriterion("PATIENT_HEIGHT in", values, "patientHeight");
            return (Criteria) this;
        }

        public Criteria andPatientHeightNotIn(List<String> values) {
            addCriterion("PATIENT_HEIGHT not in", values, "patientHeight");
            return (Criteria) this;
        }

        public Criteria andPatientHeightBetween(String value1, String value2) {
            addCriterion("PATIENT_HEIGHT between", value1, value2, "patientHeight");
            return (Criteria) this;
        }

        public Criteria andPatientHeightNotBetween(String value1, String value2) {
            addCriterion("PATIENT_HEIGHT not between", value1, value2, "patientHeight");
            return (Criteria) this;
        }

        public Criteria andPatientWeightIsNull() {
            addCriterion("PATIENT_WEIGHT is null");
            return (Criteria) this;
        }

        public Criteria andPatientWeightIsNotNull() {
            addCriterion("PATIENT_WEIGHT is not null");
            return (Criteria) this;
        }

        public Criteria andPatientWeightEqualTo(String value) {
            addCriterion("PATIENT_WEIGHT =", value, "patientWeight");
            return (Criteria) this;
        }

        public Criteria andPatientWeightNotEqualTo(String value) {
            addCriterion("PATIENT_WEIGHT <>", value, "patientWeight");
            return (Criteria) this;
        }

        public Criteria andPatientWeightGreaterThan(String value) {
            addCriterion("PATIENT_WEIGHT >", value, "patientWeight");
            return (Criteria) this;
        }

        public Criteria andPatientWeightGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_WEIGHT >=", value, "patientWeight");
            return (Criteria) this;
        }

        public Criteria andPatientWeightLessThan(String value) {
            addCriterion("PATIENT_WEIGHT <", value, "patientWeight");
            return (Criteria) this;
        }

        public Criteria andPatientWeightLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_WEIGHT <=", value, "patientWeight");
            return (Criteria) this;
        }

        public Criteria andPatientWeightLike(String value) {
            addCriterion("PATIENT_WEIGHT like", value, "patientWeight");
            return (Criteria) this;
        }

        public Criteria andPatientWeightNotLike(String value) {
            addCriterion("PATIENT_WEIGHT not like", value, "patientWeight");
            return (Criteria) this;
        }

        public Criteria andPatientWeightIn(List<String> values) {
            addCriterion("PATIENT_WEIGHT in", values, "patientWeight");
            return (Criteria) this;
        }

        public Criteria andPatientWeightNotIn(List<String> values) {
            addCriterion("PATIENT_WEIGHT not in", values, "patientWeight");
            return (Criteria) this;
        }

        public Criteria andPatientWeightBetween(String value1, String value2) {
            addCriterion("PATIENT_WEIGHT between", value1, value2, "patientWeight");
            return (Criteria) this;
        }

        public Criteria andPatientWeightNotBetween(String value1, String value2) {
            addCriterion("PATIENT_WEIGHT not between", value1, value2, "patientWeight");
            return (Criteria) this;
        }

        public Criteria andPatientPhoneIsNull() {
            addCriterion("PATIENT_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andPatientPhoneIsNotNull() {
            addCriterion("PATIENT_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andPatientPhoneEqualTo(String value) {
            addCriterion("PATIENT_PHONE =", value, "patientPhone");
            return (Criteria) this;
        }

        public Criteria andPatientPhoneNotEqualTo(String value) {
            addCriterion("PATIENT_PHONE <>", value, "patientPhone");
            return (Criteria) this;
        }

        public Criteria andPatientPhoneGreaterThan(String value) {
            addCriterion("PATIENT_PHONE >", value, "patientPhone");
            return (Criteria) this;
        }

        public Criteria andPatientPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_PHONE >=", value, "patientPhone");
            return (Criteria) this;
        }

        public Criteria andPatientPhoneLessThan(String value) {
            addCriterion("PATIENT_PHONE <", value, "patientPhone");
            return (Criteria) this;
        }

        public Criteria andPatientPhoneLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_PHONE <=", value, "patientPhone");
            return (Criteria) this;
        }

        public Criteria andPatientPhoneLike(String value) {
            addCriterion("PATIENT_PHONE like", value, "patientPhone");
            return (Criteria) this;
        }

        public Criteria andPatientPhoneNotLike(String value) {
            addCriterion("PATIENT_PHONE not like", value, "patientPhone");
            return (Criteria) this;
        }

        public Criteria andPatientPhoneIn(List<String> values) {
            addCriterion("PATIENT_PHONE in", values, "patientPhone");
            return (Criteria) this;
        }

        public Criteria andPatientPhoneNotIn(List<String> values) {
            addCriterion("PATIENT_PHONE not in", values, "patientPhone");
            return (Criteria) this;
        }

        public Criteria andPatientPhoneBetween(String value1, String value2) {
            addCriterion("PATIENT_PHONE between", value1, value2, "patientPhone");
            return (Criteria) this;
        }

        public Criteria andPatientPhoneNotBetween(String value1, String value2) {
            addCriterion("PATIENT_PHONE not between", value1, value2, "patientPhone");
            return (Criteria) this;
        }

        public Criteria andPatientTypeIdIsNull() {
            addCriterion("PATIENT_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPatientTypeIdIsNotNull() {
            addCriterion("PATIENT_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPatientTypeIdEqualTo(String value) {
            addCriterion("PATIENT_TYPE_ID =", value, "patientTypeId");
            return (Criteria) this;
        }

        public Criteria andPatientTypeIdNotEqualTo(String value) {
            addCriterion("PATIENT_TYPE_ID <>", value, "patientTypeId");
            return (Criteria) this;
        }

        public Criteria andPatientTypeIdGreaterThan(String value) {
            addCriterion("PATIENT_TYPE_ID >", value, "patientTypeId");
            return (Criteria) this;
        }

        public Criteria andPatientTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_TYPE_ID >=", value, "patientTypeId");
            return (Criteria) this;
        }

        public Criteria andPatientTypeIdLessThan(String value) {
            addCriterion("PATIENT_TYPE_ID <", value, "patientTypeId");
            return (Criteria) this;
        }

        public Criteria andPatientTypeIdLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_TYPE_ID <=", value, "patientTypeId");
            return (Criteria) this;
        }

        public Criteria andPatientTypeIdLike(String value) {
            addCriterion("PATIENT_TYPE_ID like", value, "patientTypeId");
            return (Criteria) this;
        }

        public Criteria andPatientTypeIdNotLike(String value) {
            addCriterion("PATIENT_TYPE_ID not like", value, "patientTypeId");
            return (Criteria) this;
        }

        public Criteria andPatientTypeIdIn(List<String> values) {
            addCriterion("PATIENT_TYPE_ID in", values, "patientTypeId");
            return (Criteria) this;
        }

        public Criteria andPatientTypeIdNotIn(List<String> values) {
            addCriterion("PATIENT_TYPE_ID not in", values, "patientTypeId");
            return (Criteria) this;
        }

        public Criteria andPatientTypeIdBetween(String value1, String value2) {
            addCriterion("PATIENT_TYPE_ID between", value1, value2, "patientTypeId");
            return (Criteria) this;
        }

        public Criteria andPatientTypeIdNotBetween(String value1, String value2) {
            addCriterion("PATIENT_TYPE_ID not between", value1, value2, "patientTypeId");
            return (Criteria) this;
        }

        public Criteria andPatientTypeNameIsNull() {
            addCriterion("PATIENT_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPatientTypeNameIsNotNull() {
            addCriterion("PATIENT_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPatientTypeNameEqualTo(String value) {
            addCriterion("PATIENT_TYPE_NAME =", value, "patientTypeName");
            return (Criteria) this;
        }

        public Criteria andPatientTypeNameNotEqualTo(String value) {
            addCriterion("PATIENT_TYPE_NAME <>", value, "patientTypeName");
            return (Criteria) this;
        }

        public Criteria andPatientTypeNameGreaterThan(String value) {
            addCriterion("PATIENT_TYPE_NAME >", value, "patientTypeName");
            return (Criteria) this;
        }

        public Criteria andPatientTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_TYPE_NAME >=", value, "patientTypeName");
            return (Criteria) this;
        }

        public Criteria andPatientTypeNameLessThan(String value) {
            addCriterion("PATIENT_TYPE_NAME <", value, "patientTypeName");
            return (Criteria) this;
        }

        public Criteria andPatientTypeNameLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_TYPE_NAME <=", value, "patientTypeName");
            return (Criteria) this;
        }

        public Criteria andPatientTypeNameLike(String value) {
            addCriterion("PATIENT_TYPE_NAME like", value, "patientTypeName");
            return (Criteria) this;
        }

        public Criteria andPatientTypeNameNotLike(String value) {
            addCriterion("PATIENT_TYPE_NAME not like", value, "patientTypeName");
            return (Criteria) this;
        }

        public Criteria andPatientTypeNameIn(List<String> values) {
            addCriterion("PATIENT_TYPE_NAME in", values, "patientTypeName");
            return (Criteria) this;
        }

        public Criteria andPatientTypeNameNotIn(List<String> values) {
            addCriterion("PATIENT_TYPE_NAME not in", values, "patientTypeName");
            return (Criteria) this;
        }

        public Criteria andPatientTypeNameBetween(String value1, String value2) {
            addCriterion("PATIENT_TYPE_NAME between", value1, value2, "patientTypeName");
            return (Criteria) this;
        }

        public Criteria andPatientTypeNameNotBetween(String value1, String value2) {
            addCriterion("PATIENT_TYPE_NAME not between", value1, value2, "patientTypeName");
            return (Criteria) this;
        }

        public Criteria andPatientSourceIdIsNull() {
            addCriterion("PATIENT_SOURCE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPatientSourceIdIsNotNull() {
            addCriterion("PATIENT_SOURCE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPatientSourceIdEqualTo(String value) {
            addCriterion("PATIENT_SOURCE_ID =", value, "patientSourceId");
            return (Criteria) this;
        }

        public Criteria andPatientSourceIdNotEqualTo(String value) {
            addCriterion("PATIENT_SOURCE_ID <>", value, "patientSourceId");
            return (Criteria) this;
        }

        public Criteria andPatientSourceIdGreaterThan(String value) {
            addCriterion("PATIENT_SOURCE_ID >", value, "patientSourceId");
            return (Criteria) this;
        }

        public Criteria andPatientSourceIdGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_SOURCE_ID >=", value, "patientSourceId");
            return (Criteria) this;
        }

        public Criteria andPatientSourceIdLessThan(String value) {
            addCriterion("PATIENT_SOURCE_ID <", value, "patientSourceId");
            return (Criteria) this;
        }

        public Criteria andPatientSourceIdLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_SOURCE_ID <=", value, "patientSourceId");
            return (Criteria) this;
        }

        public Criteria andPatientSourceIdLike(String value) {
            addCriterion("PATIENT_SOURCE_ID like", value, "patientSourceId");
            return (Criteria) this;
        }

        public Criteria andPatientSourceIdNotLike(String value) {
            addCriterion("PATIENT_SOURCE_ID not like", value, "patientSourceId");
            return (Criteria) this;
        }

        public Criteria andPatientSourceIdIn(List<String> values) {
            addCriterion("PATIENT_SOURCE_ID in", values, "patientSourceId");
            return (Criteria) this;
        }

        public Criteria andPatientSourceIdNotIn(List<String> values) {
            addCriterion("PATIENT_SOURCE_ID not in", values, "patientSourceId");
            return (Criteria) this;
        }

        public Criteria andPatientSourceIdBetween(String value1, String value2) {
            addCriterion("PATIENT_SOURCE_ID between", value1, value2, "patientSourceId");
            return (Criteria) this;
        }

        public Criteria andPatientSourceIdNotBetween(String value1, String value2) {
            addCriterion("PATIENT_SOURCE_ID not between", value1, value2, "patientSourceId");
            return (Criteria) this;
        }

        public Criteria andPatientSourceNameIsNull() {
            addCriterion("PATIENT_SOURCE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPatientSourceNameIsNotNull() {
            addCriterion("PATIENT_SOURCE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPatientSourceNameEqualTo(String value) {
            addCriterion("PATIENT_SOURCE_NAME =", value, "patientSourceName");
            return (Criteria) this;
        }

        public Criteria andPatientSourceNameNotEqualTo(String value) {
            addCriterion("PATIENT_SOURCE_NAME <>", value, "patientSourceName");
            return (Criteria) this;
        }

        public Criteria andPatientSourceNameGreaterThan(String value) {
            addCriterion("PATIENT_SOURCE_NAME >", value, "patientSourceName");
            return (Criteria) this;
        }

        public Criteria andPatientSourceNameGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_SOURCE_NAME >=", value, "patientSourceName");
            return (Criteria) this;
        }

        public Criteria andPatientSourceNameLessThan(String value) {
            addCriterion("PATIENT_SOURCE_NAME <", value, "patientSourceName");
            return (Criteria) this;
        }

        public Criteria andPatientSourceNameLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_SOURCE_NAME <=", value, "patientSourceName");
            return (Criteria) this;
        }

        public Criteria andPatientSourceNameLike(String value) {
            addCriterion("PATIENT_SOURCE_NAME like", value, "patientSourceName");
            return (Criteria) this;
        }

        public Criteria andPatientSourceNameNotLike(String value) {
            addCriterion("PATIENT_SOURCE_NAME not like", value, "patientSourceName");
            return (Criteria) this;
        }

        public Criteria andPatientSourceNameIn(List<String> values) {
            addCriterion("PATIENT_SOURCE_NAME in", values, "patientSourceName");
            return (Criteria) this;
        }

        public Criteria andPatientSourceNameNotIn(List<String> values) {
            addCriterion("PATIENT_SOURCE_NAME not in", values, "patientSourceName");
            return (Criteria) this;
        }

        public Criteria andPatientSourceNameBetween(String value1, String value2) {
            addCriterion("PATIENT_SOURCE_NAME between", value1, value2, "patientSourceName");
            return (Criteria) this;
        }

        public Criteria andPatientSourceNameNotBetween(String value1, String value2) {
            addCriterion("PATIENT_SOURCE_NAME not between", value1, value2, "patientSourceName");
            return (Criteria) this;
        }

        public Criteria andPatientNoIsNull() {
            addCriterion("PATIENT_NO is null");
            return (Criteria) this;
        }

        public Criteria andPatientNoIsNotNull() {
            addCriterion("PATIENT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andPatientNoEqualTo(String value) {
            addCriterion("PATIENT_NO =", value, "patientNo");
            return (Criteria) this;
        }

        public Criteria andPatientNoNotEqualTo(String value) {
            addCriterion("PATIENT_NO <>", value, "patientNo");
            return (Criteria) this;
        }

        public Criteria andPatientNoGreaterThan(String value) {
            addCriterion("PATIENT_NO >", value, "patientNo");
            return (Criteria) this;
        }

        public Criteria andPatientNoGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_NO >=", value, "patientNo");
            return (Criteria) this;
        }

        public Criteria andPatientNoLessThan(String value) {
            addCriterion("PATIENT_NO <", value, "patientNo");
            return (Criteria) this;
        }

        public Criteria andPatientNoLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_NO <=", value, "patientNo");
            return (Criteria) this;
        }

        public Criteria andPatientNoLike(String value) {
            addCriterion("PATIENT_NO like", value, "patientNo");
            return (Criteria) this;
        }

        public Criteria andPatientNoNotLike(String value) {
            addCriterion("PATIENT_NO not like", value, "patientNo");
            return (Criteria) this;
        }

        public Criteria andPatientNoIn(List<String> values) {
            addCriterion("PATIENT_NO in", values, "patientNo");
            return (Criteria) this;
        }

        public Criteria andPatientNoNotIn(List<String> values) {
            addCriterion("PATIENT_NO not in", values, "patientNo");
            return (Criteria) this;
        }

        public Criteria andPatientNoBetween(String value1, String value2) {
            addCriterion("PATIENT_NO between", value1, value2, "patientNo");
            return (Criteria) this;
        }

        public Criteria andPatientNoNotBetween(String value1, String value2) {
            addCriterion("PATIENT_NO not between", value1, value2, "patientNo");
            return (Criteria) this;
        }

        public Criteria andIcfDateIsNull() {
            addCriterion("ICF_DATE is null");
            return (Criteria) this;
        }

        public Criteria andIcfDateIsNotNull() {
            addCriterion("ICF_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andIcfDateEqualTo(String value) {
            addCriterion("ICF_DATE =", value, "icfDate");
            return (Criteria) this;
        }

        public Criteria andIcfDateNotEqualTo(String value) {
            addCriterion("ICF_DATE <>", value, "icfDate");
            return (Criteria) this;
        }

        public Criteria andIcfDateGreaterThan(String value) {
            addCriterion("ICF_DATE >", value, "icfDate");
            return (Criteria) this;
        }

        public Criteria andIcfDateGreaterThanOrEqualTo(String value) {
            addCriterion("ICF_DATE >=", value, "icfDate");
            return (Criteria) this;
        }

        public Criteria andIcfDateLessThan(String value) {
            addCriterion("ICF_DATE <", value, "icfDate");
            return (Criteria) this;
        }

        public Criteria andIcfDateLessThanOrEqualTo(String value) {
            addCriterion("ICF_DATE <=", value, "icfDate");
            return (Criteria) this;
        }

        public Criteria andIcfDateLike(String value) {
            addCriterion("ICF_DATE like", value, "icfDate");
            return (Criteria) this;
        }

        public Criteria andIcfDateNotLike(String value) {
            addCriterion("ICF_DATE not like", value, "icfDate");
            return (Criteria) this;
        }

        public Criteria andIcfDateIn(List<String> values) {
            addCriterion("ICF_DATE in", values, "icfDate");
            return (Criteria) this;
        }

        public Criteria andIcfDateNotIn(List<String> values) {
            addCriterion("ICF_DATE not in", values, "icfDate");
            return (Criteria) this;
        }

        public Criteria andIcfDateBetween(String value1, String value2) {
            addCriterion("ICF_DATE between", value1, value2, "icfDate");
            return (Criteria) this;
        }

        public Criteria andIcfDateNotBetween(String value1, String value2) {
            addCriterion("ICF_DATE not between", value1, value2, "icfDate");
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

        public Criteria andInDoctorFlowIsNull() {
            addCriterion("IN_DOCTOR_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andInDoctorFlowIsNotNull() {
            addCriterion("IN_DOCTOR_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andInDoctorFlowEqualTo(String value) {
            addCriterion("IN_DOCTOR_FLOW =", value, "inDoctorFlow");
            return (Criteria) this;
        }

        public Criteria andInDoctorFlowNotEqualTo(String value) {
            addCriterion("IN_DOCTOR_FLOW <>", value, "inDoctorFlow");
            return (Criteria) this;
        }

        public Criteria andInDoctorFlowGreaterThan(String value) {
            addCriterion("IN_DOCTOR_FLOW >", value, "inDoctorFlow");
            return (Criteria) this;
        }

        public Criteria andInDoctorFlowGreaterThanOrEqualTo(String value) {
            addCriterion("IN_DOCTOR_FLOW >=", value, "inDoctorFlow");
            return (Criteria) this;
        }

        public Criteria andInDoctorFlowLessThan(String value) {
            addCriterion("IN_DOCTOR_FLOW <", value, "inDoctorFlow");
            return (Criteria) this;
        }

        public Criteria andInDoctorFlowLessThanOrEqualTo(String value) {
            addCriterion("IN_DOCTOR_FLOW <=", value, "inDoctorFlow");
            return (Criteria) this;
        }

        public Criteria andInDoctorFlowLike(String value) {
            addCriterion("IN_DOCTOR_FLOW like", value, "inDoctorFlow");
            return (Criteria) this;
        }

        public Criteria andInDoctorFlowNotLike(String value) {
            addCriterion("IN_DOCTOR_FLOW not like", value, "inDoctorFlow");
            return (Criteria) this;
        }

        public Criteria andInDoctorFlowIn(List<String> values) {
            addCriterion("IN_DOCTOR_FLOW in", values, "inDoctorFlow");
            return (Criteria) this;
        }

        public Criteria andInDoctorFlowNotIn(List<String> values) {
            addCriterion("IN_DOCTOR_FLOW not in", values, "inDoctorFlow");
            return (Criteria) this;
        }

        public Criteria andInDoctorFlowBetween(String value1, String value2) {
            addCriterion("IN_DOCTOR_FLOW between", value1, value2, "inDoctorFlow");
            return (Criteria) this;
        }

        public Criteria andInDoctorFlowNotBetween(String value1, String value2) {
            addCriterion("IN_DOCTOR_FLOW not between", value1, value2, "inDoctorFlow");
            return (Criteria) this;
        }

        public Criteria andInDoctorNameIsNull() {
            addCriterion("IN_DOCTOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andInDoctorNameIsNotNull() {
            addCriterion("IN_DOCTOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andInDoctorNameEqualTo(String value) {
            addCriterion("IN_DOCTOR_NAME =", value, "inDoctorName");
            return (Criteria) this;
        }

        public Criteria andInDoctorNameNotEqualTo(String value) {
            addCriterion("IN_DOCTOR_NAME <>", value, "inDoctorName");
            return (Criteria) this;
        }

        public Criteria andInDoctorNameGreaterThan(String value) {
            addCriterion("IN_DOCTOR_NAME >", value, "inDoctorName");
            return (Criteria) this;
        }

        public Criteria andInDoctorNameGreaterThanOrEqualTo(String value) {
            addCriterion("IN_DOCTOR_NAME >=", value, "inDoctorName");
            return (Criteria) this;
        }

        public Criteria andInDoctorNameLessThan(String value) {
            addCriterion("IN_DOCTOR_NAME <", value, "inDoctorName");
            return (Criteria) this;
        }

        public Criteria andInDoctorNameLessThanOrEqualTo(String value) {
            addCriterion("IN_DOCTOR_NAME <=", value, "inDoctorName");
            return (Criteria) this;
        }

        public Criteria andInDoctorNameLike(String value) {
            addCriterion("IN_DOCTOR_NAME like", value, "inDoctorName");
            return (Criteria) this;
        }

        public Criteria andInDoctorNameNotLike(String value) {
            addCriterion("IN_DOCTOR_NAME not like", value, "inDoctorName");
            return (Criteria) this;
        }

        public Criteria andInDoctorNameIn(List<String> values) {
            addCriterion("IN_DOCTOR_NAME in", values, "inDoctorName");
            return (Criteria) this;
        }

        public Criteria andInDoctorNameNotIn(List<String> values) {
            addCriterion("IN_DOCTOR_NAME not in", values, "inDoctorName");
            return (Criteria) this;
        }

        public Criteria andInDoctorNameBetween(String value1, String value2) {
            addCriterion("IN_DOCTOR_NAME between", value1, value2, "inDoctorName");
            return (Criteria) this;
        }

        public Criteria andInDoctorNameNotBetween(String value1, String value2) {
            addCriterion("IN_DOCTOR_NAME not between", value1, value2, "inDoctorName");
            return (Criteria) this;
        }

        public Criteria andPatientStageNoteIsNull() {
            addCriterion("PATIENT_STAGE_NOTE is null");
            return (Criteria) this;
        }

        public Criteria andPatientStageNoteIsNotNull() {
            addCriterion("PATIENT_STAGE_NOTE is not null");
            return (Criteria) this;
        }

        public Criteria andPatientStageNoteEqualTo(String value) {
            addCriterion("PATIENT_STAGE_NOTE =", value, "patientStageNote");
            return (Criteria) this;
        }

        public Criteria andPatientStageNoteNotEqualTo(String value) {
            addCriterion("PATIENT_STAGE_NOTE <>", value, "patientStageNote");
            return (Criteria) this;
        }

        public Criteria andPatientStageNoteGreaterThan(String value) {
            addCriterion("PATIENT_STAGE_NOTE >", value, "patientStageNote");
            return (Criteria) this;
        }

        public Criteria andPatientStageNoteGreaterThanOrEqualTo(String value) {
            addCriterion("PATIENT_STAGE_NOTE >=", value, "patientStageNote");
            return (Criteria) this;
        }

        public Criteria andPatientStageNoteLessThan(String value) {
            addCriterion("PATIENT_STAGE_NOTE <", value, "patientStageNote");
            return (Criteria) this;
        }

        public Criteria andPatientStageNoteLessThanOrEqualTo(String value) {
            addCriterion("PATIENT_STAGE_NOTE <=", value, "patientStageNote");
            return (Criteria) this;
        }

        public Criteria andPatientStageNoteLike(String value) {
            addCriterion("PATIENT_STAGE_NOTE like", value, "patientStageNote");
            return (Criteria) this;
        }

        public Criteria andPatientStageNoteNotLike(String value) {
            addCriterion("PATIENT_STAGE_NOTE not like", value, "patientStageNote");
            return (Criteria) this;
        }

        public Criteria andPatientStageNoteIn(List<String> values) {
            addCriterion("PATIENT_STAGE_NOTE in", values, "patientStageNote");
            return (Criteria) this;
        }

        public Criteria andPatientStageNoteNotIn(List<String> values) {
            addCriterion("PATIENT_STAGE_NOTE not in", values, "patientStageNote");
            return (Criteria) this;
        }

        public Criteria andPatientStageNoteBetween(String value1, String value2) {
            addCriterion("PATIENT_STAGE_NOTE between", value1, value2, "patientStageNote");
            return (Criteria) this;
        }

        public Criteria andPatientStageNoteNotBetween(String value1, String value2) {
            addCriterion("PATIENT_STAGE_NOTE not between", value1, value2, "patientStageNote");
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