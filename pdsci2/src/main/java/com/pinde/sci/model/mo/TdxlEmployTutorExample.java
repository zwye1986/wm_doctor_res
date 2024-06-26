package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class TdxlEmployTutorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TdxlEmployTutorExample() {
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

        public Criteria andUserFlowIsNull() {
            addCriterion("USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andUserFlowIsNotNull() {
            addCriterion("USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andUserFlowEqualTo(String value) {
            addCriterion("USER_FLOW =", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotEqualTo(String value) {
            addCriterion("USER_FLOW <>", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowGreaterThan(String value) {
            addCriterion("USER_FLOW >", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("USER_FLOW >=", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowLessThan(String value) {
            addCriterion("USER_FLOW <", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowLessThanOrEqualTo(String value) {
            addCriterion("USER_FLOW <=", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowLike(String value) {
            addCriterion("USER_FLOW like", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotLike(String value) {
            addCriterion("USER_FLOW not like", value, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowIn(List<String> values) {
            addCriterion("USER_FLOW in", values, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotIn(List<String> values) {
            addCriterion("USER_FLOW not in", values, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowBetween(String value1, String value2) {
            addCriterion("USER_FLOW between", value1, value2, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserFlowNotBetween(String value1, String value2) {
            addCriterion("USER_FLOW not between", value1, value2, "userFlow");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("USER_NAME =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("USER_NAME <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("USER_NAME >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_NAME >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("USER_NAME <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("USER_NAME <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("USER_NAME like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("USER_NAME not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("USER_NAME in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("USER_NAME not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("USER_NAME between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("USER_NAME not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNull() {
            addCriterion("STUDENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNotNull() {
            addCriterion("STUDENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdEqualTo(String value) {
            addCriterion("STUDENT_ID =", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotEqualTo(String value) {
            addCriterion("STUDENT_ID <>", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThan(String value) {
            addCriterion("STUDENT_ID >", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThanOrEqualTo(String value) {
            addCriterion("STUDENT_ID >=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThan(String value) {
            addCriterion("STUDENT_ID <", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThanOrEqualTo(String value) {
            addCriterion("STUDENT_ID <=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLike(String value) {
            addCriterion("STUDENT_ID like", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotLike(String value) {
            addCriterion("STUDENT_ID not like", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIn(List<String> values) {
            addCriterion("STUDENT_ID in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotIn(List<String> values) {
            addCriterion("STUDENT_ID not in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdBetween(String value1, String value2) {
            addCriterion("STUDENT_ID between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotBetween(String value1, String value2) {
            addCriterion("STUDENT_ID not between", value1, value2, "studentId");
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

        public Criteria andIdNumberIsNull() {
            addCriterion("ID_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andIdNumberIsNotNull() {
            addCriterion("ID_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andIdNumberEqualTo(String value) {
            addCriterion("ID_NUMBER =", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotEqualTo(String value) {
            addCriterion("ID_NUMBER <>", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberGreaterThan(String value) {
            addCriterion("ID_NUMBER >", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberGreaterThanOrEqualTo(String value) {
            addCriterion("ID_NUMBER >=", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberLessThan(String value) {
            addCriterion("ID_NUMBER <", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberLessThanOrEqualTo(String value) {
            addCriterion("ID_NUMBER <=", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberLike(String value) {
            addCriterion("ID_NUMBER like", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotLike(String value) {
            addCriterion("ID_NUMBER not like", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberIn(List<String> values) {
            addCriterion("ID_NUMBER in", values, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotIn(List<String> values) {
            addCriterion("ID_NUMBER not in", values, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberBetween(String value1, String value2) {
            addCriterion("ID_NUMBER between", value1, value2, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotBetween(String value1, String value2) {
            addCriterion("ID_NUMBER not between", value1, value2, "idNumber");
            return (Criteria) this;
        }

        public Criteria andSpeNameIsNull() {
            addCriterion("SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSpeNameIsNotNull() {
            addCriterion("SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSpeNameEqualTo(String value) {
            addCriterion("SPE_NAME =", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotEqualTo(String value) {
            addCriterion("SPE_NAME <>", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameGreaterThan(String value) {
            addCriterion("SPE_NAME >", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_NAME >=", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameLessThan(String value) {
            addCriterion("SPE_NAME <", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameLessThanOrEqualTo(String value) {
            addCriterion("SPE_NAME <=", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameLike(String value) {
            addCriterion("SPE_NAME like", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotLike(String value) {
            addCriterion("SPE_NAME not like", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameIn(List<String> values) {
            addCriterion("SPE_NAME in", values, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotIn(List<String> values) {
            addCriterion("SPE_NAME not in", values, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameBetween(String value1, String value2) {
            addCriterion("SPE_NAME between", value1, value2, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotBetween(String value1, String value2) {
            addCriterion("SPE_NAME not between", value1, value2, "speName");
            return (Criteria) this;
        }

        public Criteria andWorkUnitIsNull() {
            addCriterion("WORK_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andWorkUnitIsNotNull() {
            addCriterion("WORK_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andWorkUnitEqualTo(String value) {
            addCriterion("WORK_UNIT =", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitNotEqualTo(String value) {
            addCriterion("WORK_UNIT <>", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitGreaterThan(String value) {
            addCriterion("WORK_UNIT >", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_UNIT >=", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitLessThan(String value) {
            addCriterion("WORK_UNIT <", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitLessThanOrEqualTo(String value) {
            addCriterion("WORK_UNIT <=", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitLike(String value) {
            addCriterion("WORK_UNIT like", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitNotLike(String value) {
            addCriterion("WORK_UNIT not like", value, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitIn(List<String> values) {
            addCriterion("WORK_UNIT in", values, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitNotIn(List<String> values) {
            addCriterion("WORK_UNIT not in", values, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitBetween(String value1, String value2) {
            addCriterion("WORK_UNIT between", value1, value2, "workUnit");
            return (Criteria) this;
        }

        public Criteria andWorkUnitNotBetween(String value1, String value2) {
            addCriterion("WORK_UNIT not between", value1, value2, "workUnit");
            return (Criteria) this;
        }

        public Criteria andPaperIsNull() {
            addCriterion("PAPER is null");
            return (Criteria) this;
        }

        public Criteria andPaperIsNotNull() {
            addCriterion("PAPER is not null");
            return (Criteria) this;
        }

        public Criteria andPaperEqualTo(String value) {
            addCriterion("PAPER =", value, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperNotEqualTo(String value) {
            addCriterion("PAPER <>", value, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperGreaterThan(String value) {
            addCriterion("PAPER >", value, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER >=", value, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperLessThan(String value) {
            addCriterion("PAPER <", value, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperLessThanOrEqualTo(String value) {
            addCriterion("PAPER <=", value, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperLike(String value) {
            addCriterion("PAPER like", value, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperNotLike(String value) {
            addCriterion("PAPER not like", value, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperIn(List<String> values) {
            addCriterion("PAPER in", values, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperNotIn(List<String> values) {
            addCriterion("PAPER not in", values, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperBetween(String value1, String value2) {
            addCriterion("PAPER between", value1, value2, "paper");
            return (Criteria) this;
        }

        public Criteria andPaperNotBetween(String value1, String value2) {
            addCriterion("PAPER not between", value1, value2, "paper");
            return (Criteria) this;
        }

        public Criteria andTutorFlowIsNull() {
            addCriterion("TUTOR_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andTutorFlowIsNotNull() {
            addCriterion("TUTOR_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andTutorFlowEqualTo(String value) {
            addCriterion("TUTOR_FLOW =", value, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowNotEqualTo(String value) {
            addCriterion("TUTOR_FLOW <>", value, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowGreaterThan(String value) {
            addCriterion("TUTOR_FLOW >", value, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_FLOW >=", value, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowLessThan(String value) {
            addCriterion("TUTOR_FLOW <", value, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_FLOW <=", value, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowLike(String value) {
            addCriterion("TUTOR_FLOW like", value, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowNotLike(String value) {
            addCriterion("TUTOR_FLOW not like", value, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowIn(List<String> values) {
            addCriterion("TUTOR_FLOW in", values, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowNotIn(List<String> values) {
            addCriterion("TUTOR_FLOW not in", values, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowBetween(String value1, String value2) {
            addCriterion("TUTOR_FLOW between", value1, value2, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorFlowNotBetween(String value1, String value2) {
            addCriterion("TUTOR_FLOW not between", value1, value2, "tutorFlow");
            return (Criteria) this;
        }

        public Criteria andTutorNameIsNull() {
            addCriterion("TUTOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTutorNameIsNotNull() {
            addCriterion("TUTOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTutorNameEqualTo(String value) {
            addCriterion("TUTOR_NAME =", value, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameNotEqualTo(String value) {
            addCriterion("TUTOR_NAME <>", value, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameGreaterThan(String value) {
            addCriterion("TUTOR_NAME >", value, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_NAME >=", value, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameLessThan(String value) {
            addCriterion("TUTOR_NAME <", value, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_NAME <=", value, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameLike(String value) {
            addCriterion("TUTOR_NAME like", value, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameNotLike(String value) {
            addCriterion("TUTOR_NAME not like", value, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameIn(List<String> values) {
            addCriterion("TUTOR_NAME in", values, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameNotIn(List<String> values) {
            addCriterion("TUTOR_NAME not in", values, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameBetween(String value1, String value2) {
            addCriterion("TUTOR_NAME between", value1, value2, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorNameNotBetween(String value1, String value2) {
            addCriterion("TUTOR_NAME not between", value1, value2, "tutorName");
            return (Criteria) this;
        }

        public Criteria andTutorSexIdIsNull() {
            addCriterion("TUTOR_SEX_ID is null");
            return (Criteria) this;
        }

        public Criteria andTutorSexIdIsNotNull() {
            addCriterion("TUTOR_SEX_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTutorSexIdEqualTo(String value) {
            addCriterion("TUTOR_SEX_ID =", value, "tutorSexId");
            return (Criteria) this;
        }

        public Criteria andTutorSexIdNotEqualTo(String value) {
            addCriterion("TUTOR_SEX_ID <>", value, "tutorSexId");
            return (Criteria) this;
        }

        public Criteria andTutorSexIdGreaterThan(String value) {
            addCriterion("TUTOR_SEX_ID >", value, "tutorSexId");
            return (Criteria) this;
        }

        public Criteria andTutorSexIdGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_SEX_ID >=", value, "tutorSexId");
            return (Criteria) this;
        }

        public Criteria andTutorSexIdLessThan(String value) {
            addCriterion("TUTOR_SEX_ID <", value, "tutorSexId");
            return (Criteria) this;
        }

        public Criteria andTutorSexIdLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_SEX_ID <=", value, "tutorSexId");
            return (Criteria) this;
        }

        public Criteria andTutorSexIdLike(String value) {
            addCriterion("TUTOR_SEX_ID like", value, "tutorSexId");
            return (Criteria) this;
        }

        public Criteria andTutorSexIdNotLike(String value) {
            addCriterion("TUTOR_SEX_ID not like", value, "tutorSexId");
            return (Criteria) this;
        }

        public Criteria andTutorSexIdIn(List<String> values) {
            addCriterion("TUTOR_SEX_ID in", values, "tutorSexId");
            return (Criteria) this;
        }

        public Criteria andTutorSexIdNotIn(List<String> values) {
            addCriterion("TUTOR_SEX_ID not in", values, "tutorSexId");
            return (Criteria) this;
        }

        public Criteria andTutorSexIdBetween(String value1, String value2) {
            addCriterion("TUTOR_SEX_ID between", value1, value2, "tutorSexId");
            return (Criteria) this;
        }

        public Criteria andTutorSexIdNotBetween(String value1, String value2) {
            addCriterion("TUTOR_SEX_ID not between", value1, value2, "tutorSexId");
            return (Criteria) this;
        }

        public Criteria andTutorSexNameIsNull() {
            addCriterion("TUTOR_SEX_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTutorSexNameIsNotNull() {
            addCriterion("TUTOR_SEX_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTutorSexNameEqualTo(String value) {
            addCriterion("TUTOR_SEX_NAME =", value, "tutorSexName");
            return (Criteria) this;
        }

        public Criteria andTutorSexNameNotEqualTo(String value) {
            addCriterion("TUTOR_SEX_NAME <>", value, "tutorSexName");
            return (Criteria) this;
        }

        public Criteria andTutorSexNameGreaterThan(String value) {
            addCriterion("TUTOR_SEX_NAME >", value, "tutorSexName");
            return (Criteria) this;
        }

        public Criteria andTutorSexNameGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_SEX_NAME >=", value, "tutorSexName");
            return (Criteria) this;
        }

        public Criteria andTutorSexNameLessThan(String value) {
            addCriterion("TUTOR_SEX_NAME <", value, "tutorSexName");
            return (Criteria) this;
        }

        public Criteria andTutorSexNameLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_SEX_NAME <=", value, "tutorSexName");
            return (Criteria) this;
        }

        public Criteria andTutorSexNameLike(String value) {
            addCriterion("TUTOR_SEX_NAME like", value, "tutorSexName");
            return (Criteria) this;
        }

        public Criteria andTutorSexNameNotLike(String value) {
            addCriterion("TUTOR_SEX_NAME not like", value, "tutorSexName");
            return (Criteria) this;
        }

        public Criteria andTutorSexNameIn(List<String> values) {
            addCriterion("TUTOR_SEX_NAME in", values, "tutorSexName");
            return (Criteria) this;
        }

        public Criteria andTutorSexNameNotIn(List<String> values) {
            addCriterion("TUTOR_SEX_NAME not in", values, "tutorSexName");
            return (Criteria) this;
        }

        public Criteria andTutorSexNameBetween(String value1, String value2) {
            addCriterion("TUTOR_SEX_NAME between", value1, value2, "tutorSexName");
            return (Criteria) this;
        }

        public Criteria andTutorSexNameNotBetween(String value1, String value2) {
            addCriterion("TUTOR_SEX_NAME not between", value1, value2, "tutorSexName");
            return (Criteria) this;
        }

        public Criteria andTutorAgeIsNull() {
            addCriterion("TUTOR_AGE is null");
            return (Criteria) this;
        }

        public Criteria andTutorAgeIsNotNull() {
            addCriterion("TUTOR_AGE is not null");
            return (Criteria) this;
        }

        public Criteria andTutorAgeEqualTo(String value) {
            addCriterion("TUTOR_AGE =", value, "tutorAge");
            return (Criteria) this;
        }

        public Criteria andTutorAgeNotEqualTo(String value) {
            addCriterion("TUTOR_AGE <>", value, "tutorAge");
            return (Criteria) this;
        }

        public Criteria andTutorAgeGreaterThan(String value) {
            addCriterion("TUTOR_AGE >", value, "tutorAge");
            return (Criteria) this;
        }

        public Criteria andTutorAgeGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_AGE >=", value, "tutorAge");
            return (Criteria) this;
        }

        public Criteria andTutorAgeLessThan(String value) {
            addCriterion("TUTOR_AGE <", value, "tutorAge");
            return (Criteria) this;
        }

        public Criteria andTutorAgeLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_AGE <=", value, "tutorAge");
            return (Criteria) this;
        }

        public Criteria andTutorAgeLike(String value) {
            addCriterion("TUTOR_AGE like", value, "tutorAge");
            return (Criteria) this;
        }

        public Criteria andTutorAgeNotLike(String value) {
            addCriterion("TUTOR_AGE not like", value, "tutorAge");
            return (Criteria) this;
        }

        public Criteria andTutorAgeIn(List<String> values) {
            addCriterion("TUTOR_AGE in", values, "tutorAge");
            return (Criteria) this;
        }

        public Criteria andTutorAgeNotIn(List<String> values) {
            addCriterion("TUTOR_AGE not in", values, "tutorAge");
            return (Criteria) this;
        }

        public Criteria andTutorAgeBetween(String value1, String value2) {
            addCriterion("TUTOR_AGE between", value1, value2, "tutorAge");
            return (Criteria) this;
        }

        public Criteria andTutorAgeNotBetween(String value1, String value2) {
            addCriterion("TUTOR_AGE not between", value1, value2, "tutorAge");
            return (Criteria) this;
        }

        public Criteria andTutorTitleIsNull() {
            addCriterion("TUTOR_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andTutorTitleIsNotNull() {
            addCriterion("TUTOR_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andTutorTitleEqualTo(String value) {
            addCriterion("TUTOR_TITLE =", value, "tutorTitle");
            return (Criteria) this;
        }

        public Criteria andTutorTitleNotEqualTo(String value) {
            addCriterion("TUTOR_TITLE <>", value, "tutorTitle");
            return (Criteria) this;
        }

        public Criteria andTutorTitleGreaterThan(String value) {
            addCriterion("TUTOR_TITLE >", value, "tutorTitle");
            return (Criteria) this;
        }

        public Criteria andTutorTitleGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_TITLE >=", value, "tutorTitle");
            return (Criteria) this;
        }

        public Criteria andTutorTitleLessThan(String value) {
            addCriterion("TUTOR_TITLE <", value, "tutorTitle");
            return (Criteria) this;
        }

        public Criteria andTutorTitleLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_TITLE <=", value, "tutorTitle");
            return (Criteria) this;
        }

        public Criteria andTutorTitleLike(String value) {
            addCriterion("TUTOR_TITLE like", value, "tutorTitle");
            return (Criteria) this;
        }

        public Criteria andTutorTitleNotLike(String value) {
            addCriterion("TUTOR_TITLE not like", value, "tutorTitle");
            return (Criteria) this;
        }

        public Criteria andTutorTitleIn(List<String> values) {
            addCriterion("TUTOR_TITLE in", values, "tutorTitle");
            return (Criteria) this;
        }

        public Criteria andTutorTitleNotIn(List<String> values) {
            addCriterion("TUTOR_TITLE not in", values, "tutorTitle");
            return (Criteria) this;
        }

        public Criteria andTutorTitleBetween(String value1, String value2) {
            addCriterion("TUTOR_TITLE between", value1, value2, "tutorTitle");
            return (Criteria) this;
        }

        public Criteria andTutorTitleNotBetween(String value1, String value2) {
            addCriterion("TUTOR_TITLE not between", value1, value2, "tutorTitle");
            return (Criteria) this;
        }

        public Criteria andTutorWorkUnitIsNull() {
            addCriterion("TUTOR_WORK_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andTutorWorkUnitIsNotNull() {
            addCriterion("TUTOR_WORK_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andTutorWorkUnitEqualTo(String value) {
            addCriterion("TUTOR_WORK_UNIT =", value, "tutorWorkUnit");
            return (Criteria) this;
        }

        public Criteria andTutorWorkUnitNotEqualTo(String value) {
            addCriterion("TUTOR_WORK_UNIT <>", value, "tutorWorkUnit");
            return (Criteria) this;
        }

        public Criteria andTutorWorkUnitGreaterThan(String value) {
            addCriterion("TUTOR_WORK_UNIT >", value, "tutorWorkUnit");
            return (Criteria) this;
        }

        public Criteria andTutorWorkUnitGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_WORK_UNIT >=", value, "tutorWorkUnit");
            return (Criteria) this;
        }

        public Criteria andTutorWorkUnitLessThan(String value) {
            addCriterion("TUTOR_WORK_UNIT <", value, "tutorWorkUnit");
            return (Criteria) this;
        }

        public Criteria andTutorWorkUnitLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_WORK_UNIT <=", value, "tutorWorkUnit");
            return (Criteria) this;
        }

        public Criteria andTutorWorkUnitLike(String value) {
            addCriterion("TUTOR_WORK_UNIT like", value, "tutorWorkUnit");
            return (Criteria) this;
        }

        public Criteria andTutorWorkUnitNotLike(String value) {
            addCriterion("TUTOR_WORK_UNIT not like", value, "tutorWorkUnit");
            return (Criteria) this;
        }

        public Criteria andTutorWorkUnitIn(List<String> values) {
            addCriterion("TUTOR_WORK_UNIT in", values, "tutorWorkUnit");
            return (Criteria) this;
        }

        public Criteria andTutorWorkUnitNotIn(List<String> values) {
            addCriterion("TUTOR_WORK_UNIT not in", values, "tutorWorkUnit");
            return (Criteria) this;
        }

        public Criteria andTutorWorkUnitBetween(String value1, String value2) {
            addCriterion("TUTOR_WORK_UNIT between", value1, value2, "tutorWorkUnit");
            return (Criteria) this;
        }

        public Criteria andTutorWorkUnitNotBetween(String value1, String value2) {
            addCriterion("TUTOR_WORK_UNIT not between", value1, value2, "tutorWorkUnit");
            return (Criteria) this;
        }

        public Criteria andTutorDirectionIsNull() {
            addCriterion("TUTOR_DIRECTION is null");
            return (Criteria) this;
        }

        public Criteria andTutorDirectionIsNotNull() {
            addCriterion("TUTOR_DIRECTION is not null");
            return (Criteria) this;
        }

        public Criteria andTutorDirectionEqualTo(String value) {
            addCriterion("TUTOR_DIRECTION =", value, "tutorDirection");
            return (Criteria) this;
        }

        public Criteria andTutorDirectionNotEqualTo(String value) {
            addCriterion("TUTOR_DIRECTION <>", value, "tutorDirection");
            return (Criteria) this;
        }

        public Criteria andTutorDirectionGreaterThan(String value) {
            addCriterion("TUTOR_DIRECTION >", value, "tutorDirection");
            return (Criteria) this;
        }

        public Criteria andTutorDirectionGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_DIRECTION >=", value, "tutorDirection");
            return (Criteria) this;
        }

        public Criteria andTutorDirectionLessThan(String value) {
            addCriterion("TUTOR_DIRECTION <", value, "tutorDirection");
            return (Criteria) this;
        }

        public Criteria andTutorDirectionLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_DIRECTION <=", value, "tutorDirection");
            return (Criteria) this;
        }

        public Criteria andTutorDirectionLike(String value) {
            addCriterion("TUTOR_DIRECTION like", value, "tutorDirection");
            return (Criteria) this;
        }

        public Criteria andTutorDirectionNotLike(String value) {
            addCriterion("TUTOR_DIRECTION not like", value, "tutorDirection");
            return (Criteria) this;
        }

        public Criteria andTutorDirectionIn(List<String> values) {
            addCriterion("TUTOR_DIRECTION in", values, "tutorDirection");
            return (Criteria) this;
        }

        public Criteria andTutorDirectionNotIn(List<String> values) {
            addCriterion("TUTOR_DIRECTION not in", values, "tutorDirection");
            return (Criteria) this;
        }

        public Criteria andTutorDirectionBetween(String value1, String value2) {
            addCriterion("TUTOR_DIRECTION between", value1, value2, "tutorDirection");
            return (Criteria) this;
        }

        public Criteria andTutorDirectionNotBetween(String value1, String value2) {
            addCriterion("TUTOR_DIRECTION not between", value1, value2, "tutorDirection");
            return (Criteria) this;
        }

        public Criteria andTutorOpinionIsNull() {
            addCriterion("TUTOR_OPINION is null");
            return (Criteria) this;
        }

        public Criteria andTutorOpinionIsNotNull() {
            addCriterion("TUTOR_OPINION is not null");
            return (Criteria) this;
        }

        public Criteria andTutorOpinionEqualTo(String value) {
            addCriterion("TUTOR_OPINION =", value, "tutorOpinion");
            return (Criteria) this;
        }

        public Criteria andTutorOpinionNotEqualTo(String value) {
            addCriterion("TUTOR_OPINION <>", value, "tutorOpinion");
            return (Criteria) this;
        }

        public Criteria andTutorOpinionGreaterThan(String value) {
            addCriterion("TUTOR_OPINION >", value, "tutorOpinion");
            return (Criteria) this;
        }

        public Criteria andTutorOpinionGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_OPINION >=", value, "tutorOpinion");
            return (Criteria) this;
        }

        public Criteria andTutorOpinionLessThan(String value) {
            addCriterion("TUTOR_OPINION <", value, "tutorOpinion");
            return (Criteria) this;
        }

        public Criteria andTutorOpinionLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_OPINION <=", value, "tutorOpinion");
            return (Criteria) this;
        }

        public Criteria andTutorOpinionLike(String value) {
            addCriterion("TUTOR_OPINION like", value, "tutorOpinion");
            return (Criteria) this;
        }

        public Criteria andTutorOpinionNotLike(String value) {
            addCriterion("TUTOR_OPINION not like", value, "tutorOpinion");
            return (Criteria) this;
        }

        public Criteria andTutorOpinionIn(List<String> values) {
            addCriterion("TUTOR_OPINION in", values, "tutorOpinion");
            return (Criteria) this;
        }

        public Criteria andTutorOpinionNotIn(List<String> values) {
            addCriterion("TUTOR_OPINION not in", values, "tutorOpinion");
            return (Criteria) this;
        }

        public Criteria andTutorOpinionBetween(String value1, String value2) {
            addCriterion("TUTOR_OPINION between", value1, value2, "tutorOpinion");
            return (Criteria) this;
        }

        public Criteria andTutorOpinionNotBetween(String value1, String value2) {
            addCriterion("TUTOR_OPINION not between", value1, value2, "tutorOpinion");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdIsNull() {
            addCriterion("TUTOR_AUDIT_ID is null");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdIsNotNull() {
            addCriterion("TUTOR_AUDIT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_ID =", value, "tutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdNotEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_ID <>", value, "tutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdGreaterThan(String value) {
            addCriterion("TUTOR_AUDIT_ID >", value, "tutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_ID >=", value, "tutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdLessThan(String value) {
            addCriterion("TUTOR_AUDIT_ID <", value, "tutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_ID <=", value, "tutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdLike(String value) {
            addCriterion("TUTOR_AUDIT_ID like", value, "tutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdNotLike(String value) {
            addCriterion("TUTOR_AUDIT_ID not like", value, "tutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdIn(List<String> values) {
            addCriterion("TUTOR_AUDIT_ID in", values, "tutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdNotIn(List<String> values) {
            addCriterion("TUTOR_AUDIT_ID not in", values, "tutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdBetween(String value1, String value2) {
            addCriterion("TUTOR_AUDIT_ID between", value1, value2, "tutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTutorAuditIdNotBetween(String value1, String value2) {
            addCriterion("TUTOR_AUDIT_ID not between", value1, value2, "tutorAuditId");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameIsNull() {
            addCriterion("TUTOR_AUDIT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameIsNotNull() {
            addCriterion("TUTOR_AUDIT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_NAME =", value, "tutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameNotEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_NAME <>", value, "tutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameGreaterThan(String value) {
            addCriterion("TUTOR_AUDIT_NAME >", value, "tutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_NAME >=", value, "tutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameLessThan(String value) {
            addCriterion("TUTOR_AUDIT_NAME <", value, "tutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_NAME <=", value, "tutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameLike(String value) {
            addCriterion("TUTOR_AUDIT_NAME like", value, "tutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameNotLike(String value) {
            addCriterion("TUTOR_AUDIT_NAME not like", value, "tutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameIn(List<String> values) {
            addCriterion("TUTOR_AUDIT_NAME in", values, "tutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameNotIn(List<String> values) {
            addCriterion("TUTOR_AUDIT_NAME not in", values, "tutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameBetween(String value1, String value2) {
            addCriterion("TUTOR_AUDIT_NAME between", value1, value2, "tutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTutorAuditNameNotBetween(String value1, String value2) {
            addCriterion("TUTOR_AUDIT_NAME not between", value1, value2, "tutorAuditName");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeIsNull() {
            addCriterion("TUTOR_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeIsNotNull() {
            addCriterion("TUTOR_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_TIME =", value, "tutorAuditTime");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeNotEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_TIME <>", value, "tutorAuditTime");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeGreaterThan(String value) {
            addCriterion("TUTOR_AUDIT_TIME >", value, "tutorAuditTime");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_TIME >=", value, "tutorAuditTime");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeLessThan(String value) {
            addCriterion("TUTOR_AUDIT_TIME <", value, "tutorAuditTime");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("TUTOR_AUDIT_TIME <=", value, "tutorAuditTime");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeLike(String value) {
            addCriterion("TUTOR_AUDIT_TIME like", value, "tutorAuditTime");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeNotLike(String value) {
            addCriterion("TUTOR_AUDIT_TIME not like", value, "tutorAuditTime");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeIn(List<String> values) {
            addCriterion("TUTOR_AUDIT_TIME in", values, "tutorAuditTime");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeNotIn(List<String> values) {
            addCriterion("TUTOR_AUDIT_TIME not in", values, "tutorAuditTime");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeBetween(String value1, String value2) {
            addCriterion("TUTOR_AUDIT_TIME between", value1, value2, "tutorAuditTime");
            return (Criteria) this;
        }

        public Criteria andTutorAuditTimeNotBetween(String value1, String value2) {
            addCriterion("TUTOR_AUDIT_TIME not between", value1, value2, "tutorAuditTime");
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

        public Criteria andOrgOpinionIsNull() {
            addCriterion("ORG_OPINION is null");
            return (Criteria) this;
        }

        public Criteria andOrgOpinionIsNotNull() {
            addCriterion("ORG_OPINION is not null");
            return (Criteria) this;
        }

        public Criteria andOrgOpinionEqualTo(String value) {
            addCriterion("ORG_OPINION =", value, "orgOpinion");
            return (Criteria) this;
        }

        public Criteria andOrgOpinionNotEqualTo(String value) {
            addCriterion("ORG_OPINION <>", value, "orgOpinion");
            return (Criteria) this;
        }

        public Criteria andOrgOpinionGreaterThan(String value) {
            addCriterion("ORG_OPINION >", value, "orgOpinion");
            return (Criteria) this;
        }

        public Criteria andOrgOpinionGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_OPINION >=", value, "orgOpinion");
            return (Criteria) this;
        }

        public Criteria andOrgOpinionLessThan(String value) {
            addCriterion("ORG_OPINION <", value, "orgOpinion");
            return (Criteria) this;
        }

        public Criteria andOrgOpinionLessThanOrEqualTo(String value) {
            addCriterion("ORG_OPINION <=", value, "orgOpinion");
            return (Criteria) this;
        }

        public Criteria andOrgOpinionLike(String value) {
            addCriterion("ORG_OPINION like", value, "orgOpinion");
            return (Criteria) this;
        }

        public Criteria andOrgOpinionNotLike(String value) {
            addCriterion("ORG_OPINION not like", value, "orgOpinion");
            return (Criteria) this;
        }

        public Criteria andOrgOpinionIn(List<String> values) {
            addCriterion("ORG_OPINION in", values, "orgOpinion");
            return (Criteria) this;
        }

        public Criteria andOrgOpinionNotIn(List<String> values) {
            addCriterion("ORG_OPINION not in", values, "orgOpinion");
            return (Criteria) this;
        }

        public Criteria andOrgOpinionBetween(String value1, String value2) {
            addCriterion("ORG_OPINION between", value1, value2, "orgOpinion");
            return (Criteria) this;
        }

        public Criteria andOrgOpinionNotBetween(String value1, String value2) {
            addCriterion("ORG_OPINION not between", value1, value2, "orgOpinion");
            return (Criteria) this;
        }

        public Criteria andOrgAuditIdIsNull() {
            addCriterion("ORG_AUDIT_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgAuditIdIsNotNull() {
            addCriterion("ORG_AUDIT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgAuditIdEqualTo(String value) {
            addCriterion("ORG_AUDIT_ID =", value, "orgAuditId");
            return (Criteria) this;
        }

        public Criteria andOrgAuditIdNotEqualTo(String value) {
            addCriterion("ORG_AUDIT_ID <>", value, "orgAuditId");
            return (Criteria) this;
        }

        public Criteria andOrgAuditIdGreaterThan(String value) {
            addCriterion("ORG_AUDIT_ID >", value, "orgAuditId");
            return (Criteria) this;
        }

        public Criteria andOrgAuditIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_AUDIT_ID >=", value, "orgAuditId");
            return (Criteria) this;
        }

        public Criteria andOrgAuditIdLessThan(String value) {
            addCriterion("ORG_AUDIT_ID <", value, "orgAuditId");
            return (Criteria) this;
        }

        public Criteria andOrgAuditIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_AUDIT_ID <=", value, "orgAuditId");
            return (Criteria) this;
        }

        public Criteria andOrgAuditIdLike(String value) {
            addCriterion("ORG_AUDIT_ID like", value, "orgAuditId");
            return (Criteria) this;
        }

        public Criteria andOrgAuditIdNotLike(String value) {
            addCriterion("ORG_AUDIT_ID not like", value, "orgAuditId");
            return (Criteria) this;
        }

        public Criteria andOrgAuditIdIn(List<String> values) {
            addCriterion("ORG_AUDIT_ID in", values, "orgAuditId");
            return (Criteria) this;
        }

        public Criteria andOrgAuditIdNotIn(List<String> values) {
            addCriterion("ORG_AUDIT_ID not in", values, "orgAuditId");
            return (Criteria) this;
        }

        public Criteria andOrgAuditIdBetween(String value1, String value2) {
            addCriterion("ORG_AUDIT_ID between", value1, value2, "orgAuditId");
            return (Criteria) this;
        }

        public Criteria andOrgAuditIdNotBetween(String value1, String value2) {
            addCriterion("ORG_AUDIT_ID not between", value1, value2, "orgAuditId");
            return (Criteria) this;
        }

        public Criteria andOrgAuditNameIsNull() {
            addCriterion("ORG_AUDIT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgAuditNameIsNotNull() {
            addCriterion("ORG_AUDIT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgAuditNameEqualTo(String value) {
            addCriterion("ORG_AUDIT_NAME =", value, "orgAuditName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditNameNotEqualTo(String value) {
            addCriterion("ORG_AUDIT_NAME <>", value, "orgAuditName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditNameGreaterThan(String value) {
            addCriterion("ORG_AUDIT_NAME >", value, "orgAuditName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_AUDIT_NAME >=", value, "orgAuditName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditNameLessThan(String value) {
            addCriterion("ORG_AUDIT_NAME <", value, "orgAuditName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_AUDIT_NAME <=", value, "orgAuditName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditNameLike(String value) {
            addCriterion("ORG_AUDIT_NAME like", value, "orgAuditName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditNameNotLike(String value) {
            addCriterion("ORG_AUDIT_NAME not like", value, "orgAuditName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditNameIn(List<String> values) {
            addCriterion("ORG_AUDIT_NAME in", values, "orgAuditName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditNameNotIn(List<String> values) {
            addCriterion("ORG_AUDIT_NAME not in", values, "orgAuditName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditNameBetween(String value1, String value2) {
            addCriterion("ORG_AUDIT_NAME between", value1, value2, "orgAuditName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditNameNotBetween(String value1, String value2) {
            addCriterion("ORG_AUDIT_NAME not between", value1, value2, "orgAuditName");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeIsNull() {
            addCriterion("ORG_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeIsNotNull() {
            addCriterion("ORG_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeEqualTo(String value) {
            addCriterion("ORG_AUDIT_TIME =", value, "orgAuditTime");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeNotEqualTo(String value) {
            addCriterion("ORG_AUDIT_TIME <>", value, "orgAuditTime");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeGreaterThan(String value) {
            addCriterion("ORG_AUDIT_TIME >", value, "orgAuditTime");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_AUDIT_TIME >=", value, "orgAuditTime");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeLessThan(String value) {
            addCriterion("ORG_AUDIT_TIME <", value, "orgAuditTime");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("ORG_AUDIT_TIME <=", value, "orgAuditTime");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeLike(String value) {
            addCriterion("ORG_AUDIT_TIME like", value, "orgAuditTime");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeNotLike(String value) {
            addCriterion("ORG_AUDIT_TIME not like", value, "orgAuditTime");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeIn(List<String> values) {
            addCriterion("ORG_AUDIT_TIME in", values, "orgAuditTime");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeNotIn(List<String> values) {
            addCriterion("ORG_AUDIT_TIME not in", values, "orgAuditTime");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeBetween(String value1, String value2) {
            addCriterion("ORG_AUDIT_TIME between", value1, value2, "orgAuditTime");
            return (Criteria) this;
        }

        public Criteria andOrgAuditTimeNotBetween(String value1, String value2) {
            addCriterion("ORG_AUDIT_TIME not between", value1, value2, "orgAuditTime");
            return (Criteria) this;
        }

        public Criteria andSchoolFlowIsNull() {
            addCriterion("SCHOOL_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSchoolFlowIsNotNull() {
            addCriterion("SCHOOL_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolFlowEqualTo(String value) {
            addCriterion("SCHOOL_FLOW =", value, "schoolFlow");
            return (Criteria) this;
        }

        public Criteria andSchoolFlowNotEqualTo(String value) {
            addCriterion("SCHOOL_FLOW <>", value, "schoolFlow");
            return (Criteria) this;
        }

        public Criteria andSchoolFlowGreaterThan(String value) {
            addCriterion("SCHOOL_FLOW >", value, "schoolFlow");
            return (Criteria) this;
        }

        public Criteria andSchoolFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOL_FLOW >=", value, "schoolFlow");
            return (Criteria) this;
        }

        public Criteria andSchoolFlowLessThan(String value) {
            addCriterion("SCHOOL_FLOW <", value, "schoolFlow");
            return (Criteria) this;
        }

        public Criteria andSchoolFlowLessThanOrEqualTo(String value) {
            addCriterion("SCHOOL_FLOW <=", value, "schoolFlow");
            return (Criteria) this;
        }

        public Criteria andSchoolFlowLike(String value) {
            addCriterion("SCHOOL_FLOW like", value, "schoolFlow");
            return (Criteria) this;
        }

        public Criteria andSchoolFlowNotLike(String value) {
            addCriterion("SCHOOL_FLOW not like", value, "schoolFlow");
            return (Criteria) this;
        }

        public Criteria andSchoolFlowIn(List<String> values) {
            addCriterion("SCHOOL_FLOW in", values, "schoolFlow");
            return (Criteria) this;
        }

        public Criteria andSchoolFlowNotIn(List<String> values) {
            addCriterion("SCHOOL_FLOW not in", values, "schoolFlow");
            return (Criteria) this;
        }

        public Criteria andSchoolFlowBetween(String value1, String value2) {
            addCriterion("SCHOOL_FLOW between", value1, value2, "schoolFlow");
            return (Criteria) this;
        }

        public Criteria andSchoolFlowNotBetween(String value1, String value2) {
            addCriterion("SCHOOL_FLOW not between", value1, value2, "schoolFlow");
            return (Criteria) this;
        }

        public Criteria andSchoolOpinionIsNull() {
            addCriterion("SCHOOL_OPINION is null");
            return (Criteria) this;
        }

        public Criteria andSchoolOpinionIsNotNull() {
            addCriterion("SCHOOL_OPINION is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolOpinionEqualTo(String value) {
            addCriterion("SCHOOL_OPINION =", value, "schoolOpinion");
            return (Criteria) this;
        }

        public Criteria andSchoolOpinionNotEqualTo(String value) {
            addCriterion("SCHOOL_OPINION <>", value, "schoolOpinion");
            return (Criteria) this;
        }

        public Criteria andSchoolOpinionGreaterThan(String value) {
            addCriterion("SCHOOL_OPINION >", value, "schoolOpinion");
            return (Criteria) this;
        }

        public Criteria andSchoolOpinionGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOL_OPINION >=", value, "schoolOpinion");
            return (Criteria) this;
        }

        public Criteria andSchoolOpinionLessThan(String value) {
            addCriterion("SCHOOL_OPINION <", value, "schoolOpinion");
            return (Criteria) this;
        }

        public Criteria andSchoolOpinionLessThanOrEqualTo(String value) {
            addCriterion("SCHOOL_OPINION <=", value, "schoolOpinion");
            return (Criteria) this;
        }

        public Criteria andSchoolOpinionLike(String value) {
            addCriterion("SCHOOL_OPINION like", value, "schoolOpinion");
            return (Criteria) this;
        }

        public Criteria andSchoolOpinionNotLike(String value) {
            addCriterion("SCHOOL_OPINION not like", value, "schoolOpinion");
            return (Criteria) this;
        }

        public Criteria andSchoolOpinionIn(List<String> values) {
            addCriterion("SCHOOL_OPINION in", values, "schoolOpinion");
            return (Criteria) this;
        }

        public Criteria andSchoolOpinionNotIn(List<String> values) {
            addCriterion("SCHOOL_OPINION not in", values, "schoolOpinion");
            return (Criteria) this;
        }

        public Criteria andSchoolOpinionBetween(String value1, String value2) {
            addCriterion("SCHOOL_OPINION between", value1, value2, "schoolOpinion");
            return (Criteria) this;
        }

        public Criteria andSchoolOpinionNotBetween(String value1, String value2) {
            addCriterion("SCHOOL_OPINION not between", value1, value2, "schoolOpinion");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditIdIsNull() {
            addCriterion("SCHOOL_AUDIT_ID is null");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditIdIsNotNull() {
            addCriterion("SCHOOL_AUDIT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditIdEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_ID =", value, "schoolAuditId");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditIdNotEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_ID <>", value, "schoolAuditId");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditIdGreaterThan(String value) {
            addCriterion("SCHOOL_AUDIT_ID >", value, "schoolAuditId");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditIdGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_ID >=", value, "schoolAuditId");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditIdLessThan(String value) {
            addCriterion("SCHOOL_AUDIT_ID <", value, "schoolAuditId");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditIdLessThanOrEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_ID <=", value, "schoolAuditId");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditIdLike(String value) {
            addCriterion("SCHOOL_AUDIT_ID like", value, "schoolAuditId");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditIdNotLike(String value) {
            addCriterion("SCHOOL_AUDIT_ID not like", value, "schoolAuditId");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditIdIn(List<String> values) {
            addCriterion("SCHOOL_AUDIT_ID in", values, "schoolAuditId");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditIdNotIn(List<String> values) {
            addCriterion("SCHOOL_AUDIT_ID not in", values, "schoolAuditId");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditIdBetween(String value1, String value2) {
            addCriterion("SCHOOL_AUDIT_ID between", value1, value2, "schoolAuditId");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditIdNotBetween(String value1, String value2) {
            addCriterion("SCHOOL_AUDIT_ID not between", value1, value2, "schoolAuditId");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditNameIsNull() {
            addCriterion("SCHOOL_AUDIT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditNameIsNotNull() {
            addCriterion("SCHOOL_AUDIT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditNameEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_NAME =", value, "schoolAuditName");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditNameNotEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_NAME <>", value, "schoolAuditName");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditNameGreaterThan(String value) {
            addCriterion("SCHOOL_AUDIT_NAME >", value, "schoolAuditName");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_NAME >=", value, "schoolAuditName");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditNameLessThan(String value) {
            addCriterion("SCHOOL_AUDIT_NAME <", value, "schoolAuditName");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditNameLessThanOrEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_NAME <=", value, "schoolAuditName");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditNameLike(String value) {
            addCriterion("SCHOOL_AUDIT_NAME like", value, "schoolAuditName");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditNameNotLike(String value) {
            addCriterion("SCHOOL_AUDIT_NAME not like", value, "schoolAuditName");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditNameIn(List<String> values) {
            addCriterion("SCHOOL_AUDIT_NAME in", values, "schoolAuditName");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditNameNotIn(List<String> values) {
            addCriterion("SCHOOL_AUDIT_NAME not in", values, "schoolAuditName");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditNameBetween(String value1, String value2) {
            addCriterion("SCHOOL_AUDIT_NAME between", value1, value2, "schoolAuditName");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditNameNotBetween(String value1, String value2) {
            addCriterion("SCHOOL_AUDIT_NAME not between", value1, value2, "schoolAuditName");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeIsNull() {
            addCriterion("SCHOOL_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeIsNotNull() {
            addCriterion("SCHOOL_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_TIME =", value, "schoolAuditTime");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeNotEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_TIME <>", value, "schoolAuditTime");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeGreaterThan(String value) {
            addCriterion("SCHOOL_AUDIT_TIME >", value, "schoolAuditTime");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_TIME >=", value, "schoolAuditTime");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeLessThan(String value) {
            addCriterion("SCHOOL_AUDIT_TIME <", value, "schoolAuditTime");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("SCHOOL_AUDIT_TIME <=", value, "schoolAuditTime");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeLike(String value) {
            addCriterion("SCHOOL_AUDIT_TIME like", value, "schoolAuditTime");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeNotLike(String value) {
            addCriterion("SCHOOL_AUDIT_TIME not like", value, "schoolAuditTime");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeIn(List<String> values) {
            addCriterion("SCHOOL_AUDIT_TIME in", values, "schoolAuditTime");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeNotIn(List<String> values) {
            addCriterion("SCHOOL_AUDIT_TIME not in", values, "schoolAuditTime");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeBetween(String value1, String value2) {
            addCriterion("SCHOOL_AUDIT_TIME between", value1, value2, "schoolAuditTime");
            return (Criteria) this;
        }

        public Criteria andSchoolAuditTimeNotBetween(String value1, String value2) {
            addCriterion("SCHOOL_AUDIT_TIME not between", value1, value2, "schoolAuditTime");
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