package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class NyjzScholarshipMainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NyjzScholarshipMainExample() {
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

        public Criteria andClassIdIsNull() {
            addCriterion("CLASS_ID is null");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNotNull() {
            addCriterion("CLASS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andClassIdEqualTo(String value) {
            addCriterion("CLASS_ID =", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotEqualTo(String value) {
            addCriterion("CLASS_ID <>", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThan(String value) {
            addCriterion("CLASS_ID >", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThanOrEqualTo(String value) {
            addCriterion("CLASS_ID >=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThan(String value) {
            addCriterion("CLASS_ID <", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThanOrEqualTo(String value) {
            addCriterion("CLASS_ID <=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLike(String value) {
            addCriterion("CLASS_ID like", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotLike(String value) {
            addCriterion("CLASS_ID not like", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdIn(List<String> values) {
            addCriterion("CLASS_ID in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotIn(List<String> values) {
            addCriterion("CLASS_ID not in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdBetween(String value1, String value2) {
            addCriterion("CLASS_ID between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotBetween(String value1, String value2) {
            addCriterion("CLASS_ID not between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIsNull() {
            addCriterion("SESSION_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIsNotNull() {
            addCriterion("SESSION_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andSessionNumberEqualTo(String value) {
            addCriterion("SESSION_NUMBER =", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotEqualTo(String value) {
            addCriterion("SESSION_NUMBER <>", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberGreaterThan(String value) {
            addCriterion("SESSION_NUMBER >", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberGreaterThanOrEqualTo(String value) {
            addCriterion("SESSION_NUMBER >=", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLessThan(String value) {
            addCriterion("SESSION_NUMBER <", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLessThanOrEqualTo(String value) {
            addCriterion("SESSION_NUMBER <=", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLike(String value) {
            addCriterion("SESSION_NUMBER like", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotLike(String value) {
            addCriterion("SESSION_NUMBER not like", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIn(List<String> values) {
            addCriterion("SESSION_NUMBER in", values, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotIn(List<String> values) {
            addCriterion("SESSION_NUMBER not in", values, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberBetween(String value1, String value2) {
            addCriterion("SESSION_NUMBER between", value1, value2, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotBetween(String value1, String value2) {
            addCriterion("SESSION_NUMBER not between", value1, value2, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andApplyFlagIsNull() {
            addCriterion("APPLY_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andApplyFlagIsNotNull() {
            addCriterion("APPLY_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andApplyFlagEqualTo(String value) {
            addCriterion("APPLY_FLAG =", value, "applyFlag");
            return (Criteria) this;
        }

        public Criteria andApplyFlagNotEqualTo(String value) {
            addCriterion("APPLY_FLAG <>", value, "applyFlag");
            return (Criteria) this;
        }

        public Criteria andApplyFlagGreaterThan(String value) {
            addCriterion("APPLY_FLAG >", value, "applyFlag");
            return (Criteria) this;
        }

        public Criteria andApplyFlagGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_FLAG >=", value, "applyFlag");
            return (Criteria) this;
        }

        public Criteria andApplyFlagLessThan(String value) {
            addCriterion("APPLY_FLAG <", value, "applyFlag");
            return (Criteria) this;
        }

        public Criteria andApplyFlagLessThanOrEqualTo(String value) {
            addCriterion("APPLY_FLAG <=", value, "applyFlag");
            return (Criteria) this;
        }

        public Criteria andApplyFlagLike(String value) {
            addCriterion("APPLY_FLAG like", value, "applyFlag");
            return (Criteria) this;
        }

        public Criteria andApplyFlagNotLike(String value) {
            addCriterion("APPLY_FLAG not like", value, "applyFlag");
            return (Criteria) this;
        }

        public Criteria andApplyFlagIn(List<String> values) {
            addCriterion("APPLY_FLAG in", values, "applyFlag");
            return (Criteria) this;
        }

        public Criteria andApplyFlagNotIn(List<String> values) {
            addCriterion("APPLY_FLAG not in", values, "applyFlag");
            return (Criteria) this;
        }

        public Criteria andApplyFlagBetween(String value1, String value2) {
            addCriterion("APPLY_FLAG between", value1, value2, "applyFlag");
            return (Criteria) this;
        }

        public Criteria andApplyFlagNotBetween(String value1, String value2) {
            addCriterion("APPLY_FLAG not between", value1, value2, "applyFlag");
            return (Criteria) this;
        }

        public Criteria andApplyTimeIsNull() {
            addCriterion("APPLY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andApplyTimeIsNotNull() {
            addCriterion("APPLY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyTimeEqualTo(String value) {
            addCriterion("APPLY_TIME =", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotEqualTo(String value) {
            addCriterion("APPLY_TIME <>", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeGreaterThan(String value) {
            addCriterion("APPLY_TIME >", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_TIME >=", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeLessThan(String value) {
            addCriterion("APPLY_TIME <", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeLessThanOrEqualTo(String value) {
            addCriterion("APPLY_TIME <=", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeLike(String value) {
            addCriterion("APPLY_TIME like", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotLike(String value) {
            addCriterion("APPLY_TIME not like", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeIn(List<String> values) {
            addCriterion("APPLY_TIME in", values, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotIn(List<String> values) {
            addCriterion("APPLY_TIME not in", values, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeBetween(String value1, String value2) {
            addCriterion("APPLY_TIME between", value1, value2, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotBetween(String value1, String value2) {
            addCriterion("APPLY_TIME not between", value1, value2, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIdIsNull() {
            addCriterion("APPLY_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIdIsNotNull() {
            addCriterion("APPLY_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIdEqualTo(String value) {
            addCriterion("APPLY_TYPE_ID =", value, "applyTypeId");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIdNotEqualTo(String value) {
            addCriterion("APPLY_TYPE_ID <>", value, "applyTypeId");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIdGreaterThan(String value) {
            addCriterion("APPLY_TYPE_ID >", value, "applyTypeId");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_TYPE_ID >=", value, "applyTypeId");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIdLessThan(String value) {
            addCriterion("APPLY_TYPE_ID <", value, "applyTypeId");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIdLessThanOrEqualTo(String value) {
            addCriterion("APPLY_TYPE_ID <=", value, "applyTypeId");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIdLike(String value) {
            addCriterion("APPLY_TYPE_ID like", value, "applyTypeId");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIdNotLike(String value) {
            addCriterion("APPLY_TYPE_ID not like", value, "applyTypeId");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIdIn(List<String> values) {
            addCriterion("APPLY_TYPE_ID in", values, "applyTypeId");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIdNotIn(List<String> values) {
            addCriterion("APPLY_TYPE_ID not in", values, "applyTypeId");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIdBetween(String value1, String value2) {
            addCriterion("APPLY_TYPE_ID between", value1, value2, "applyTypeId");
            return (Criteria) this;
        }

        public Criteria andApplyTypeIdNotBetween(String value1, String value2) {
            addCriterion("APPLY_TYPE_ID not between", value1, value2, "applyTypeId");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNameIsNull() {
            addCriterion("APPLY_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNameIsNotNull() {
            addCriterion("APPLY_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNameEqualTo(String value) {
            addCriterion("APPLY_TYPE_NAME =", value, "applyTypeName");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNameNotEqualTo(String value) {
            addCriterion("APPLY_TYPE_NAME <>", value, "applyTypeName");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNameGreaterThan(String value) {
            addCriterion("APPLY_TYPE_NAME >", value, "applyTypeName");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_TYPE_NAME >=", value, "applyTypeName");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNameLessThan(String value) {
            addCriterion("APPLY_TYPE_NAME <", value, "applyTypeName");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNameLessThanOrEqualTo(String value) {
            addCriterion("APPLY_TYPE_NAME <=", value, "applyTypeName");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNameLike(String value) {
            addCriterion("APPLY_TYPE_NAME like", value, "applyTypeName");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNameNotLike(String value) {
            addCriterion("APPLY_TYPE_NAME not like", value, "applyTypeName");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNameIn(List<String> values) {
            addCriterion("APPLY_TYPE_NAME in", values, "applyTypeName");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNameNotIn(List<String> values) {
            addCriterion("APPLY_TYPE_NAME not in", values, "applyTypeName");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNameBetween(String value1, String value2) {
            addCriterion("APPLY_TYPE_NAME between", value1, value2, "applyTypeName");
            return (Criteria) this;
        }

        public Criteria andApplyTypeNameNotBetween(String value1, String value2) {
            addCriterion("APPLY_TYPE_NAME not between", value1, value2, "applyTypeName");
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

        public Criteria andDoctorAuditStatusIdIsNull() {
            addCriterion("DOCTOR_AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusIdIsNotNull() {
            addCriterion("DOCTOR_AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusIdEqualTo(String value) {
            addCriterion("DOCTOR_AUDIT_STATUS_ID =", value, "doctorAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusIdNotEqualTo(String value) {
            addCriterion("DOCTOR_AUDIT_STATUS_ID <>", value, "doctorAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusIdGreaterThan(String value) {
            addCriterion("DOCTOR_AUDIT_STATUS_ID >", value, "doctorAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_AUDIT_STATUS_ID >=", value, "doctorAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusIdLessThan(String value) {
            addCriterion("DOCTOR_AUDIT_STATUS_ID <", value, "doctorAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_AUDIT_STATUS_ID <=", value, "doctorAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusIdLike(String value) {
            addCriterion("DOCTOR_AUDIT_STATUS_ID like", value, "doctorAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusIdNotLike(String value) {
            addCriterion("DOCTOR_AUDIT_STATUS_ID not like", value, "doctorAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusIdIn(List<String> values) {
            addCriterion("DOCTOR_AUDIT_STATUS_ID in", values, "doctorAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusIdNotIn(List<String> values) {
            addCriterion("DOCTOR_AUDIT_STATUS_ID not in", values, "doctorAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusIdBetween(String value1, String value2) {
            addCriterion("DOCTOR_AUDIT_STATUS_ID between", value1, value2, "doctorAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_AUDIT_STATUS_ID not between", value1, value2, "doctorAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusNameIsNull() {
            addCriterion("DOCTOR_AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusNameIsNotNull() {
            addCriterion("DOCTOR_AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusNameEqualTo(String value) {
            addCriterion("DOCTOR_AUDIT_STATUS_NAME =", value, "doctorAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusNameNotEqualTo(String value) {
            addCriterion("DOCTOR_AUDIT_STATUS_NAME <>", value, "doctorAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusNameGreaterThan(String value) {
            addCriterion("DOCTOR_AUDIT_STATUS_NAME >", value, "doctorAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_AUDIT_STATUS_NAME >=", value, "doctorAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusNameLessThan(String value) {
            addCriterion("DOCTOR_AUDIT_STATUS_NAME <", value, "doctorAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_AUDIT_STATUS_NAME <=", value, "doctorAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusNameLike(String value) {
            addCriterion("DOCTOR_AUDIT_STATUS_NAME like", value, "doctorAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusNameNotLike(String value) {
            addCriterion("DOCTOR_AUDIT_STATUS_NAME not like", value, "doctorAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusNameIn(List<String> values) {
            addCriterion("DOCTOR_AUDIT_STATUS_NAME in", values, "doctorAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusNameNotIn(List<String> values) {
            addCriterion("DOCTOR_AUDIT_STATUS_NAME not in", values, "doctorAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusNameBetween(String value1, String value2) {
            addCriterion("DOCTOR_AUDIT_STATUS_NAME between", value1, value2, "doctorAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_AUDIT_STATUS_NAME not between", value1, value2, "doctorAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditAdviceIsNull() {
            addCriterion("DOCTOR_AUDIT_ADVICE is null");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditAdviceIsNotNull() {
            addCriterion("DOCTOR_AUDIT_ADVICE is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditAdviceEqualTo(String value) {
            addCriterion("DOCTOR_AUDIT_ADVICE =", value, "doctorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditAdviceNotEqualTo(String value) {
            addCriterion("DOCTOR_AUDIT_ADVICE <>", value, "doctorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditAdviceGreaterThan(String value) {
            addCriterion("DOCTOR_AUDIT_ADVICE >", value, "doctorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditAdviceGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_AUDIT_ADVICE >=", value, "doctorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditAdviceLessThan(String value) {
            addCriterion("DOCTOR_AUDIT_ADVICE <", value, "doctorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditAdviceLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_AUDIT_ADVICE <=", value, "doctorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditAdviceLike(String value) {
            addCriterion("DOCTOR_AUDIT_ADVICE like", value, "doctorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditAdviceNotLike(String value) {
            addCriterion("DOCTOR_AUDIT_ADVICE not like", value, "doctorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditAdviceIn(List<String> values) {
            addCriterion("DOCTOR_AUDIT_ADVICE in", values, "doctorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditAdviceNotIn(List<String> values) {
            addCriterion("DOCTOR_AUDIT_ADVICE not in", values, "doctorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditAdviceBetween(String value1, String value2) {
            addCriterion("DOCTOR_AUDIT_ADVICE between", value1, value2, "doctorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditAdviceNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_AUDIT_ADVICE not between", value1, value2, "doctorAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditTimeIsNull() {
            addCriterion("DOCTOR_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditTimeIsNotNull() {
            addCriterion("DOCTOR_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditTimeEqualTo(String value) {
            addCriterion("DOCTOR_AUDIT_TIME =", value, "doctorAuditTime");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditTimeNotEqualTo(String value) {
            addCriterion("DOCTOR_AUDIT_TIME <>", value, "doctorAuditTime");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditTimeGreaterThan(String value) {
            addCriterion("DOCTOR_AUDIT_TIME >", value, "doctorAuditTime");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_AUDIT_TIME >=", value, "doctorAuditTime");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditTimeLessThan(String value) {
            addCriterion("DOCTOR_AUDIT_TIME <", value, "doctorAuditTime");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_AUDIT_TIME <=", value, "doctorAuditTime");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditTimeLike(String value) {
            addCriterion("DOCTOR_AUDIT_TIME like", value, "doctorAuditTime");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditTimeNotLike(String value) {
            addCriterion("DOCTOR_AUDIT_TIME not like", value, "doctorAuditTime");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditTimeIn(List<String> values) {
            addCriterion("DOCTOR_AUDIT_TIME in", values, "doctorAuditTime");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditTimeNotIn(List<String> values) {
            addCriterion("DOCTOR_AUDIT_TIME not in", values, "doctorAuditTime");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditTimeBetween(String value1, String value2) {
            addCriterion("DOCTOR_AUDIT_TIME between", value1, value2, "doctorAuditTime");
            return (Criteria) this;
        }

        public Criteria andDoctorAuditTimeNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_AUDIT_TIME not between", value1, value2, "doctorAuditTime");
            return (Criteria) this;
        }

        public Criteria andSecondDoctorFlowIsNull() {
            addCriterion("SECOND_DOCTOR_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSecondDoctorFlowIsNotNull() {
            addCriterion("SECOND_DOCTOR_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSecondDoctorFlowEqualTo(String value) {
            addCriterion("SECOND_DOCTOR_FLOW =", value, "secondDoctorFlow");
            return (Criteria) this;
        }

        public Criteria andSecondDoctorFlowNotEqualTo(String value) {
            addCriterion("SECOND_DOCTOR_FLOW <>", value, "secondDoctorFlow");
            return (Criteria) this;
        }

        public Criteria andSecondDoctorFlowGreaterThan(String value) {
            addCriterion("SECOND_DOCTOR_FLOW >", value, "secondDoctorFlow");
            return (Criteria) this;
        }

        public Criteria andSecondDoctorFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SECOND_DOCTOR_FLOW >=", value, "secondDoctorFlow");
            return (Criteria) this;
        }

        public Criteria andSecondDoctorFlowLessThan(String value) {
            addCriterion("SECOND_DOCTOR_FLOW <", value, "secondDoctorFlow");
            return (Criteria) this;
        }

        public Criteria andSecondDoctorFlowLessThanOrEqualTo(String value) {
            addCriterion("SECOND_DOCTOR_FLOW <=", value, "secondDoctorFlow");
            return (Criteria) this;
        }

        public Criteria andSecondDoctorFlowLike(String value) {
            addCriterion("SECOND_DOCTOR_FLOW like", value, "secondDoctorFlow");
            return (Criteria) this;
        }

        public Criteria andSecondDoctorFlowNotLike(String value) {
            addCriterion("SECOND_DOCTOR_FLOW not like", value, "secondDoctorFlow");
            return (Criteria) this;
        }

        public Criteria andSecondDoctorFlowIn(List<String> values) {
            addCriterion("SECOND_DOCTOR_FLOW in", values, "secondDoctorFlow");
            return (Criteria) this;
        }

        public Criteria andSecondDoctorFlowNotIn(List<String> values) {
            addCriterion("SECOND_DOCTOR_FLOW not in", values, "secondDoctorFlow");
            return (Criteria) this;
        }

        public Criteria andSecondDoctorFlowBetween(String value1, String value2) {
            addCriterion("SECOND_DOCTOR_FLOW between", value1, value2, "secondDoctorFlow");
            return (Criteria) this;
        }

        public Criteria andSecondDoctorFlowNotBetween(String value1, String value2) {
            addCriterion("SECOND_DOCTOR_FLOW not between", value1, value2, "secondDoctorFlow");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusIdIsNull() {
            addCriterion("SECOND_AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusIdIsNotNull() {
            addCriterion("SECOND_AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusIdEqualTo(String value) {
            addCriterion("SECOND_AUDIT_STATUS_ID =", value, "secondAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusIdNotEqualTo(String value) {
            addCriterion("SECOND_AUDIT_STATUS_ID <>", value, "secondAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusIdGreaterThan(String value) {
            addCriterion("SECOND_AUDIT_STATUS_ID >", value, "secondAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("SECOND_AUDIT_STATUS_ID >=", value, "secondAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusIdLessThan(String value) {
            addCriterion("SECOND_AUDIT_STATUS_ID <", value, "secondAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("SECOND_AUDIT_STATUS_ID <=", value, "secondAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusIdLike(String value) {
            addCriterion("SECOND_AUDIT_STATUS_ID like", value, "secondAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusIdNotLike(String value) {
            addCriterion("SECOND_AUDIT_STATUS_ID not like", value, "secondAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusIdIn(List<String> values) {
            addCriterion("SECOND_AUDIT_STATUS_ID in", values, "secondAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusIdNotIn(List<String> values) {
            addCriterion("SECOND_AUDIT_STATUS_ID not in", values, "secondAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusIdBetween(String value1, String value2) {
            addCriterion("SECOND_AUDIT_STATUS_ID between", value1, value2, "secondAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("SECOND_AUDIT_STATUS_ID not between", value1, value2, "secondAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusNameIsNull() {
            addCriterion("SECOND_AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusNameIsNotNull() {
            addCriterion("SECOND_AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusNameEqualTo(String value) {
            addCriterion("SECOND_AUDIT_STATUS_NAME =", value, "secondAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusNameNotEqualTo(String value) {
            addCriterion("SECOND_AUDIT_STATUS_NAME <>", value, "secondAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusNameGreaterThan(String value) {
            addCriterion("SECOND_AUDIT_STATUS_NAME >", value, "secondAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("SECOND_AUDIT_STATUS_NAME >=", value, "secondAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusNameLessThan(String value) {
            addCriterion("SECOND_AUDIT_STATUS_NAME <", value, "secondAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("SECOND_AUDIT_STATUS_NAME <=", value, "secondAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusNameLike(String value) {
            addCriterion("SECOND_AUDIT_STATUS_NAME like", value, "secondAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusNameNotLike(String value) {
            addCriterion("SECOND_AUDIT_STATUS_NAME not like", value, "secondAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusNameIn(List<String> values) {
            addCriterion("SECOND_AUDIT_STATUS_NAME in", values, "secondAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusNameNotIn(List<String> values) {
            addCriterion("SECOND_AUDIT_STATUS_NAME not in", values, "secondAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusNameBetween(String value1, String value2) {
            addCriterion("SECOND_AUDIT_STATUS_NAME between", value1, value2, "secondAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSecondAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("SECOND_AUDIT_STATUS_NAME not between", value1, value2, "secondAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSecondAuditAdviceIsNull() {
            addCriterion("SECOND_AUDIT_ADVICE is null");
            return (Criteria) this;
        }

        public Criteria andSecondAuditAdviceIsNotNull() {
            addCriterion("SECOND_AUDIT_ADVICE is not null");
            return (Criteria) this;
        }

        public Criteria andSecondAuditAdviceEqualTo(String value) {
            addCriterion("SECOND_AUDIT_ADVICE =", value, "secondAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSecondAuditAdviceNotEqualTo(String value) {
            addCriterion("SECOND_AUDIT_ADVICE <>", value, "secondAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSecondAuditAdviceGreaterThan(String value) {
            addCriterion("SECOND_AUDIT_ADVICE >", value, "secondAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSecondAuditAdviceGreaterThanOrEqualTo(String value) {
            addCriterion("SECOND_AUDIT_ADVICE >=", value, "secondAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSecondAuditAdviceLessThan(String value) {
            addCriterion("SECOND_AUDIT_ADVICE <", value, "secondAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSecondAuditAdviceLessThanOrEqualTo(String value) {
            addCriterion("SECOND_AUDIT_ADVICE <=", value, "secondAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSecondAuditAdviceLike(String value) {
            addCriterion("SECOND_AUDIT_ADVICE like", value, "secondAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSecondAuditAdviceNotLike(String value) {
            addCriterion("SECOND_AUDIT_ADVICE not like", value, "secondAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSecondAuditAdviceIn(List<String> values) {
            addCriterion("SECOND_AUDIT_ADVICE in", values, "secondAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSecondAuditAdviceNotIn(List<String> values) {
            addCriterion("SECOND_AUDIT_ADVICE not in", values, "secondAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSecondAuditAdviceBetween(String value1, String value2) {
            addCriterion("SECOND_AUDIT_ADVICE between", value1, value2, "secondAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSecondAuditAdviceNotBetween(String value1, String value2) {
            addCriterion("SECOND_AUDIT_ADVICE not between", value1, value2, "secondAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSecondAuditTimeIsNull() {
            addCriterion("SECOND_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSecondAuditTimeIsNotNull() {
            addCriterion("SECOND_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSecondAuditTimeEqualTo(String value) {
            addCriterion("SECOND_AUDIT_TIME =", value, "secondAuditTime");
            return (Criteria) this;
        }

        public Criteria andSecondAuditTimeNotEqualTo(String value) {
            addCriterion("SECOND_AUDIT_TIME <>", value, "secondAuditTime");
            return (Criteria) this;
        }

        public Criteria andSecondAuditTimeGreaterThan(String value) {
            addCriterion("SECOND_AUDIT_TIME >", value, "secondAuditTime");
            return (Criteria) this;
        }

        public Criteria andSecondAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SECOND_AUDIT_TIME >=", value, "secondAuditTime");
            return (Criteria) this;
        }

        public Criteria andSecondAuditTimeLessThan(String value) {
            addCriterion("SECOND_AUDIT_TIME <", value, "secondAuditTime");
            return (Criteria) this;
        }

        public Criteria andSecondAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("SECOND_AUDIT_TIME <=", value, "secondAuditTime");
            return (Criteria) this;
        }

        public Criteria andSecondAuditTimeLike(String value) {
            addCriterion("SECOND_AUDIT_TIME like", value, "secondAuditTime");
            return (Criteria) this;
        }

        public Criteria andSecondAuditTimeNotLike(String value) {
            addCriterion("SECOND_AUDIT_TIME not like", value, "secondAuditTime");
            return (Criteria) this;
        }

        public Criteria andSecondAuditTimeIn(List<String> values) {
            addCriterion("SECOND_AUDIT_TIME in", values, "secondAuditTime");
            return (Criteria) this;
        }

        public Criteria andSecondAuditTimeNotIn(List<String> values) {
            addCriterion("SECOND_AUDIT_TIME not in", values, "secondAuditTime");
            return (Criteria) this;
        }

        public Criteria andSecondAuditTimeBetween(String value1, String value2) {
            addCriterion("SECOND_AUDIT_TIME between", value1, value2, "secondAuditTime");
            return (Criteria) this;
        }

        public Criteria andSecondAuditTimeNotBetween(String value1, String value2) {
            addCriterion("SECOND_AUDIT_TIME not between", value1, value2, "secondAuditTime");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowIsNull() {
            addCriterion("PYDW_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowIsNotNull() {
            addCriterion("PYDW_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowEqualTo(String value) {
            addCriterion("PYDW_ORG_FLOW =", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowNotEqualTo(String value) {
            addCriterion("PYDW_ORG_FLOW <>", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowGreaterThan(String value) {
            addCriterion("PYDW_ORG_FLOW >", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PYDW_ORG_FLOW >=", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowLessThan(String value) {
            addCriterion("PYDW_ORG_FLOW <", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("PYDW_ORG_FLOW <=", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowLike(String value) {
            addCriterion("PYDW_ORG_FLOW like", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowNotLike(String value) {
            addCriterion("PYDW_ORG_FLOW not like", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowIn(List<String> values) {
            addCriterion("PYDW_ORG_FLOW in", values, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowNotIn(List<String> values) {
            addCriterion("PYDW_ORG_FLOW not in", values, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowBetween(String value1, String value2) {
            addCriterion("PYDW_ORG_FLOW between", value1, value2, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowNotBetween(String value1, String value2) {
            addCriterion("PYDW_ORG_FLOW not between", value1, value2, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdIsNull() {
            addCriterion("PYDW_AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdIsNotNull() {
            addCriterion("PYDW_AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdEqualTo(String value) {
            addCriterion("PYDW_AUDIT_STATUS_ID =", value, "pydwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdNotEqualTo(String value) {
            addCriterion("PYDW_AUDIT_STATUS_ID <>", value, "pydwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdGreaterThan(String value) {
            addCriterion("PYDW_AUDIT_STATUS_ID >", value, "pydwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("PYDW_AUDIT_STATUS_ID >=", value, "pydwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdLessThan(String value) {
            addCriterion("PYDW_AUDIT_STATUS_ID <", value, "pydwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("PYDW_AUDIT_STATUS_ID <=", value, "pydwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdLike(String value) {
            addCriterion("PYDW_AUDIT_STATUS_ID like", value, "pydwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdNotLike(String value) {
            addCriterion("PYDW_AUDIT_STATUS_ID not like", value, "pydwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdIn(List<String> values) {
            addCriterion("PYDW_AUDIT_STATUS_ID in", values, "pydwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdNotIn(List<String> values) {
            addCriterion("PYDW_AUDIT_STATUS_ID not in", values, "pydwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdBetween(String value1, String value2) {
            addCriterion("PYDW_AUDIT_STATUS_ID between", value1, value2, "pydwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("PYDW_AUDIT_STATUS_ID not between", value1, value2, "pydwAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameIsNull() {
            addCriterion("PYDW_AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameIsNotNull() {
            addCriterion("PYDW_AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameEqualTo(String value) {
            addCriterion("PYDW_AUDIT_STATUS_NAME =", value, "pydwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameNotEqualTo(String value) {
            addCriterion("PYDW_AUDIT_STATUS_NAME <>", value, "pydwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameGreaterThan(String value) {
            addCriterion("PYDW_AUDIT_STATUS_NAME >", value, "pydwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("PYDW_AUDIT_STATUS_NAME >=", value, "pydwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameLessThan(String value) {
            addCriterion("PYDW_AUDIT_STATUS_NAME <", value, "pydwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("PYDW_AUDIT_STATUS_NAME <=", value, "pydwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameLike(String value) {
            addCriterion("PYDW_AUDIT_STATUS_NAME like", value, "pydwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameNotLike(String value) {
            addCriterion("PYDW_AUDIT_STATUS_NAME not like", value, "pydwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameIn(List<String> values) {
            addCriterion("PYDW_AUDIT_STATUS_NAME in", values, "pydwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameNotIn(List<String> values) {
            addCriterion("PYDW_AUDIT_STATUS_NAME not in", values, "pydwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameBetween(String value1, String value2) {
            addCriterion("PYDW_AUDIT_STATUS_NAME between", value1, value2, "pydwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("PYDW_AUDIT_STATUS_NAME not between", value1, value2, "pydwAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceIsNull() {
            addCriterion("PYDW_AUDIT_ADVICE is null");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceIsNotNull() {
            addCriterion("PYDW_AUDIT_ADVICE is not null");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceEqualTo(String value) {
            addCriterion("PYDW_AUDIT_ADVICE =", value, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceNotEqualTo(String value) {
            addCriterion("PYDW_AUDIT_ADVICE <>", value, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceGreaterThan(String value) {
            addCriterion("PYDW_AUDIT_ADVICE >", value, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceGreaterThanOrEqualTo(String value) {
            addCriterion("PYDW_AUDIT_ADVICE >=", value, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceLessThan(String value) {
            addCriterion("PYDW_AUDIT_ADVICE <", value, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceLessThanOrEqualTo(String value) {
            addCriterion("PYDW_AUDIT_ADVICE <=", value, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceLike(String value) {
            addCriterion("PYDW_AUDIT_ADVICE like", value, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceNotLike(String value) {
            addCriterion("PYDW_AUDIT_ADVICE not like", value, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceIn(List<String> values) {
            addCriterion("PYDW_AUDIT_ADVICE in", values, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceNotIn(List<String> values) {
            addCriterion("PYDW_AUDIT_ADVICE not in", values, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceBetween(String value1, String value2) {
            addCriterion("PYDW_AUDIT_ADVICE between", value1, value2, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditAdviceNotBetween(String value1, String value2) {
            addCriterion("PYDW_AUDIT_ADVICE not between", value1, value2, "pydwAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeIsNull() {
            addCriterion("PYDW_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeIsNotNull() {
            addCriterion("PYDW_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeEqualTo(String value) {
            addCriterion("PYDW_AUDIT_TIME =", value, "pydwAuditTime");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeNotEqualTo(String value) {
            addCriterion("PYDW_AUDIT_TIME <>", value, "pydwAuditTime");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeGreaterThan(String value) {
            addCriterion("PYDW_AUDIT_TIME >", value, "pydwAuditTime");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("PYDW_AUDIT_TIME >=", value, "pydwAuditTime");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeLessThan(String value) {
            addCriterion("PYDW_AUDIT_TIME <", value, "pydwAuditTime");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("PYDW_AUDIT_TIME <=", value, "pydwAuditTime");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeLike(String value) {
            addCriterion("PYDW_AUDIT_TIME like", value, "pydwAuditTime");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeNotLike(String value) {
            addCriterion("PYDW_AUDIT_TIME not like", value, "pydwAuditTime");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeIn(List<String> values) {
            addCriterion("PYDW_AUDIT_TIME in", values, "pydwAuditTime");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeNotIn(List<String> values) {
            addCriterion("PYDW_AUDIT_TIME not in", values, "pydwAuditTime");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeBetween(String value1, String value2) {
            addCriterion("PYDW_AUDIT_TIME between", value1, value2, "pydwAuditTime");
            return (Criteria) this;
        }

        public Criteria andPydwAuditTimeNotBetween(String value1, String value2) {
            addCriterion("PYDW_AUDIT_TIME not between", value1, value2, "pydwAuditTime");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowIsNull() {
            addCriterion("FWH_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowIsNotNull() {
            addCriterion("FWH_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowEqualTo(String value) {
            addCriterion("FWH_ORG_FLOW =", value, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowNotEqualTo(String value) {
            addCriterion("FWH_ORG_FLOW <>", value, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowGreaterThan(String value) {
            addCriterion("FWH_ORG_FLOW >", value, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("FWH_ORG_FLOW >=", value, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowLessThan(String value) {
            addCriterion("FWH_ORG_FLOW <", value, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("FWH_ORG_FLOW <=", value, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowLike(String value) {
            addCriterion("FWH_ORG_FLOW like", value, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowNotLike(String value) {
            addCriterion("FWH_ORG_FLOW not like", value, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowIn(List<String> values) {
            addCriterion("FWH_ORG_FLOW in", values, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowNotIn(List<String> values) {
            addCriterion("FWH_ORG_FLOW not in", values, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowBetween(String value1, String value2) {
            addCriterion("FWH_ORG_FLOW between", value1, value2, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhOrgFlowNotBetween(String value1, String value2) {
            addCriterion("FWH_ORG_FLOW not between", value1, value2, "fwhOrgFlow");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdIsNull() {
            addCriterion("FWH_AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdIsNotNull() {
            addCriterion("FWH_AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdEqualTo(String value) {
            addCriterion("FWH_AUDIT_STATUS_ID =", value, "fwhAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdNotEqualTo(String value) {
            addCriterion("FWH_AUDIT_STATUS_ID <>", value, "fwhAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdGreaterThan(String value) {
            addCriterion("FWH_AUDIT_STATUS_ID >", value, "fwhAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("FWH_AUDIT_STATUS_ID >=", value, "fwhAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdLessThan(String value) {
            addCriterion("FWH_AUDIT_STATUS_ID <", value, "fwhAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("FWH_AUDIT_STATUS_ID <=", value, "fwhAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdLike(String value) {
            addCriterion("FWH_AUDIT_STATUS_ID like", value, "fwhAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdNotLike(String value) {
            addCriterion("FWH_AUDIT_STATUS_ID not like", value, "fwhAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdIn(List<String> values) {
            addCriterion("FWH_AUDIT_STATUS_ID in", values, "fwhAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdNotIn(List<String> values) {
            addCriterion("FWH_AUDIT_STATUS_ID not in", values, "fwhAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdBetween(String value1, String value2) {
            addCriterion("FWH_AUDIT_STATUS_ID between", value1, value2, "fwhAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("FWH_AUDIT_STATUS_ID not between", value1, value2, "fwhAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameIsNull() {
            addCriterion("FWH_AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameIsNotNull() {
            addCriterion("FWH_AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameEqualTo(String value) {
            addCriterion("FWH_AUDIT_STATUS_NAME =", value, "fwhAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameNotEqualTo(String value) {
            addCriterion("FWH_AUDIT_STATUS_NAME <>", value, "fwhAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameGreaterThan(String value) {
            addCriterion("FWH_AUDIT_STATUS_NAME >", value, "fwhAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("FWH_AUDIT_STATUS_NAME >=", value, "fwhAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameLessThan(String value) {
            addCriterion("FWH_AUDIT_STATUS_NAME <", value, "fwhAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("FWH_AUDIT_STATUS_NAME <=", value, "fwhAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameLike(String value) {
            addCriterion("FWH_AUDIT_STATUS_NAME like", value, "fwhAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameNotLike(String value) {
            addCriterion("FWH_AUDIT_STATUS_NAME not like", value, "fwhAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameIn(List<String> values) {
            addCriterion("FWH_AUDIT_STATUS_NAME in", values, "fwhAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameNotIn(List<String> values) {
            addCriterion("FWH_AUDIT_STATUS_NAME not in", values, "fwhAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameBetween(String value1, String value2) {
            addCriterion("FWH_AUDIT_STATUS_NAME between", value1, value2, "fwhAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andFwhAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("FWH_AUDIT_STATUS_NAME not between", value1, value2, "fwhAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceIsNull() {
            addCriterion("FWH_AUDIT_ADVICE is null");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceIsNotNull() {
            addCriterion("FWH_AUDIT_ADVICE is not null");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceEqualTo(String value) {
            addCriterion("FWH_AUDIT_ADVICE =", value, "fwhAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceNotEqualTo(String value) {
            addCriterion("FWH_AUDIT_ADVICE <>", value, "fwhAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceGreaterThan(String value) {
            addCriterion("FWH_AUDIT_ADVICE >", value, "fwhAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceGreaterThanOrEqualTo(String value) {
            addCriterion("FWH_AUDIT_ADVICE >=", value, "fwhAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceLessThan(String value) {
            addCriterion("FWH_AUDIT_ADVICE <", value, "fwhAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceLessThanOrEqualTo(String value) {
            addCriterion("FWH_AUDIT_ADVICE <=", value, "fwhAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceLike(String value) {
            addCriterion("FWH_AUDIT_ADVICE like", value, "fwhAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceNotLike(String value) {
            addCriterion("FWH_AUDIT_ADVICE not like", value, "fwhAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceIn(List<String> values) {
            addCriterion("FWH_AUDIT_ADVICE in", values, "fwhAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceNotIn(List<String> values) {
            addCriterion("FWH_AUDIT_ADVICE not in", values, "fwhAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceBetween(String value1, String value2) {
            addCriterion("FWH_AUDIT_ADVICE between", value1, value2, "fwhAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andFwhAuditAdviceNotBetween(String value1, String value2) {
            addCriterion("FWH_AUDIT_ADVICE not between", value1, value2, "fwhAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeIsNull() {
            addCriterion("FWH_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeIsNotNull() {
            addCriterion("FWH_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeEqualTo(String value) {
            addCriterion("FWH_AUDIT_TIME =", value, "fwhAuditTime");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeNotEqualTo(String value) {
            addCriterion("FWH_AUDIT_TIME <>", value, "fwhAuditTime");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeGreaterThan(String value) {
            addCriterion("FWH_AUDIT_TIME >", value, "fwhAuditTime");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("FWH_AUDIT_TIME >=", value, "fwhAuditTime");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeLessThan(String value) {
            addCriterion("FWH_AUDIT_TIME <", value, "fwhAuditTime");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("FWH_AUDIT_TIME <=", value, "fwhAuditTime");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeLike(String value) {
            addCriterion("FWH_AUDIT_TIME like", value, "fwhAuditTime");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeNotLike(String value) {
            addCriterion("FWH_AUDIT_TIME not like", value, "fwhAuditTime");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeIn(List<String> values) {
            addCriterion("FWH_AUDIT_TIME in", values, "fwhAuditTime");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeNotIn(List<String> values) {
            addCriterion("FWH_AUDIT_TIME not in", values, "fwhAuditTime");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeBetween(String value1, String value2) {
            addCriterion("FWH_AUDIT_TIME between", value1, value2, "fwhAuditTime");
            return (Criteria) this;
        }

        public Criteria andFwhAuditTimeNotBetween(String value1, String value2) {
            addCriterion("FWH_AUDIT_TIME not between", value1, value2, "fwhAuditTime");
            return (Criteria) this;
        }

        public Criteria andSzkOrgFlowIsNull() {
            addCriterion("SZK_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSzkOrgFlowIsNotNull() {
            addCriterion("SZK_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSzkOrgFlowEqualTo(String value) {
            addCriterion("SZK_ORG_FLOW =", value, "szkOrgFlow");
            return (Criteria) this;
        }

        public Criteria andSzkOrgFlowNotEqualTo(String value) {
            addCriterion("SZK_ORG_FLOW <>", value, "szkOrgFlow");
            return (Criteria) this;
        }

        public Criteria andSzkOrgFlowGreaterThan(String value) {
            addCriterion("SZK_ORG_FLOW >", value, "szkOrgFlow");
            return (Criteria) this;
        }

        public Criteria andSzkOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SZK_ORG_FLOW >=", value, "szkOrgFlow");
            return (Criteria) this;
        }

        public Criteria andSzkOrgFlowLessThan(String value) {
            addCriterion("SZK_ORG_FLOW <", value, "szkOrgFlow");
            return (Criteria) this;
        }

        public Criteria andSzkOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("SZK_ORG_FLOW <=", value, "szkOrgFlow");
            return (Criteria) this;
        }

        public Criteria andSzkOrgFlowLike(String value) {
            addCriterion("SZK_ORG_FLOW like", value, "szkOrgFlow");
            return (Criteria) this;
        }

        public Criteria andSzkOrgFlowNotLike(String value) {
            addCriterion("SZK_ORG_FLOW not like", value, "szkOrgFlow");
            return (Criteria) this;
        }

        public Criteria andSzkOrgFlowIn(List<String> values) {
            addCriterion("SZK_ORG_FLOW in", values, "szkOrgFlow");
            return (Criteria) this;
        }

        public Criteria andSzkOrgFlowNotIn(List<String> values) {
            addCriterion("SZK_ORG_FLOW not in", values, "szkOrgFlow");
            return (Criteria) this;
        }

        public Criteria andSzkOrgFlowBetween(String value1, String value2) {
            addCriterion("SZK_ORG_FLOW between", value1, value2, "szkOrgFlow");
            return (Criteria) this;
        }

        public Criteria andSzkOrgFlowNotBetween(String value1, String value2) {
            addCriterion("SZK_ORG_FLOW not between", value1, value2, "szkOrgFlow");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusIdIsNull() {
            addCriterion("SZK_AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusIdIsNotNull() {
            addCriterion("SZK_AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusIdEqualTo(String value) {
            addCriterion("SZK_AUDIT_STATUS_ID =", value, "szkAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusIdNotEqualTo(String value) {
            addCriterion("SZK_AUDIT_STATUS_ID <>", value, "szkAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusIdGreaterThan(String value) {
            addCriterion("SZK_AUDIT_STATUS_ID >", value, "szkAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("SZK_AUDIT_STATUS_ID >=", value, "szkAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusIdLessThan(String value) {
            addCriterion("SZK_AUDIT_STATUS_ID <", value, "szkAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("SZK_AUDIT_STATUS_ID <=", value, "szkAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusIdLike(String value) {
            addCriterion("SZK_AUDIT_STATUS_ID like", value, "szkAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusIdNotLike(String value) {
            addCriterion("SZK_AUDIT_STATUS_ID not like", value, "szkAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusIdIn(List<String> values) {
            addCriterion("SZK_AUDIT_STATUS_ID in", values, "szkAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusIdNotIn(List<String> values) {
            addCriterion("SZK_AUDIT_STATUS_ID not in", values, "szkAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusIdBetween(String value1, String value2) {
            addCriterion("SZK_AUDIT_STATUS_ID between", value1, value2, "szkAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("SZK_AUDIT_STATUS_ID not between", value1, value2, "szkAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusNameIsNull() {
            addCriterion("SZK_AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusNameIsNotNull() {
            addCriterion("SZK_AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusNameEqualTo(String value) {
            addCriterion("SZK_AUDIT_STATUS_NAME =", value, "szkAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusNameNotEqualTo(String value) {
            addCriterion("SZK_AUDIT_STATUS_NAME <>", value, "szkAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusNameGreaterThan(String value) {
            addCriterion("SZK_AUDIT_STATUS_NAME >", value, "szkAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("SZK_AUDIT_STATUS_NAME >=", value, "szkAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusNameLessThan(String value) {
            addCriterion("SZK_AUDIT_STATUS_NAME <", value, "szkAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("SZK_AUDIT_STATUS_NAME <=", value, "szkAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusNameLike(String value) {
            addCriterion("SZK_AUDIT_STATUS_NAME like", value, "szkAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusNameNotLike(String value) {
            addCriterion("SZK_AUDIT_STATUS_NAME not like", value, "szkAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusNameIn(List<String> values) {
            addCriterion("SZK_AUDIT_STATUS_NAME in", values, "szkAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusNameNotIn(List<String> values) {
            addCriterion("SZK_AUDIT_STATUS_NAME not in", values, "szkAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusNameBetween(String value1, String value2) {
            addCriterion("SZK_AUDIT_STATUS_NAME between", value1, value2, "szkAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSzkAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("SZK_AUDIT_STATUS_NAME not between", value1, value2, "szkAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andSzkAuditAdviceIsNull() {
            addCriterion("SZK_AUDIT_ADVICE is null");
            return (Criteria) this;
        }

        public Criteria andSzkAuditAdviceIsNotNull() {
            addCriterion("SZK_AUDIT_ADVICE is not null");
            return (Criteria) this;
        }

        public Criteria andSzkAuditAdviceEqualTo(String value) {
            addCriterion("SZK_AUDIT_ADVICE =", value, "szkAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSzkAuditAdviceNotEqualTo(String value) {
            addCriterion("SZK_AUDIT_ADVICE <>", value, "szkAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSzkAuditAdviceGreaterThan(String value) {
            addCriterion("SZK_AUDIT_ADVICE >", value, "szkAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSzkAuditAdviceGreaterThanOrEqualTo(String value) {
            addCriterion("SZK_AUDIT_ADVICE >=", value, "szkAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSzkAuditAdviceLessThan(String value) {
            addCriterion("SZK_AUDIT_ADVICE <", value, "szkAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSzkAuditAdviceLessThanOrEqualTo(String value) {
            addCriterion("SZK_AUDIT_ADVICE <=", value, "szkAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSzkAuditAdviceLike(String value) {
            addCriterion("SZK_AUDIT_ADVICE like", value, "szkAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSzkAuditAdviceNotLike(String value) {
            addCriterion("SZK_AUDIT_ADVICE not like", value, "szkAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSzkAuditAdviceIn(List<String> values) {
            addCriterion("SZK_AUDIT_ADVICE in", values, "szkAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSzkAuditAdviceNotIn(List<String> values) {
            addCriterion("SZK_AUDIT_ADVICE not in", values, "szkAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSzkAuditAdviceBetween(String value1, String value2) {
            addCriterion("SZK_AUDIT_ADVICE between", value1, value2, "szkAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSzkAuditAdviceNotBetween(String value1, String value2) {
            addCriterion("SZK_AUDIT_ADVICE not between", value1, value2, "szkAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andSzkAuditTimeIsNull() {
            addCriterion("SZK_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSzkAuditTimeIsNotNull() {
            addCriterion("SZK_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSzkAuditTimeEqualTo(String value) {
            addCriterion("SZK_AUDIT_TIME =", value, "szkAuditTime");
            return (Criteria) this;
        }

        public Criteria andSzkAuditTimeNotEqualTo(String value) {
            addCriterion("SZK_AUDIT_TIME <>", value, "szkAuditTime");
            return (Criteria) this;
        }

        public Criteria andSzkAuditTimeGreaterThan(String value) {
            addCriterion("SZK_AUDIT_TIME >", value, "szkAuditTime");
            return (Criteria) this;
        }

        public Criteria andSzkAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SZK_AUDIT_TIME >=", value, "szkAuditTime");
            return (Criteria) this;
        }

        public Criteria andSzkAuditTimeLessThan(String value) {
            addCriterion("SZK_AUDIT_TIME <", value, "szkAuditTime");
            return (Criteria) this;
        }

        public Criteria andSzkAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("SZK_AUDIT_TIME <=", value, "szkAuditTime");
            return (Criteria) this;
        }

        public Criteria andSzkAuditTimeLike(String value) {
            addCriterion("SZK_AUDIT_TIME like", value, "szkAuditTime");
            return (Criteria) this;
        }

        public Criteria andSzkAuditTimeNotLike(String value) {
            addCriterion("SZK_AUDIT_TIME not like", value, "szkAuditTime");
            return (Criteria) this;
        }

        public Criteria andSzkAuditTimeIn(List<String> values) {
            addCriterion("SZK_AUDIT_TIME in", values, "szkAuditTime");
            return (Criteria) this;
        }

        public Criteria andSzkAuditTimeNotIn(List<String> values) {
            addCriterion("SZK_AUDIT_TIME not in", values, "szkAuditTime");
            return (Criteria) this;
        }

        public Criteria andSzkAuditTimeBetween(String value1, String value2) {
            addCriterion("SZK_AUDIT_TIME between", value1, value2, "szkAuditTime");
            return (Criteria) this;
        }

        public Criteria andSzkAuditTimeNotBetween(String value1, String value2) {
            addCriterion("SZK_AUDIT_TIME not between", value1, value2, "szkAuditTime");
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

        public Criteria andApplyLevelIsNull() {
            addCriterion("APPLY_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andApplyLevelIsNotNull() {
            addCriterion("APPLY_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andApplyLevelEqualTo(String value) {
            addCriterion("APPLY_LEVEL =", value, "applyLevel");
            return (Criteria) this;
        }

        public Criteria andApplyLevelNotEqualTo(String value) {
            addCriterion("APPLY_LEVEL <>", value, "applyLevel");
            return (Criteria) this;
        }

        public Criteria andApplyLevelGreaterThan(String value) {
            addCriterion("APPLY_LEVEL >", value, "applyLevel");
            return (Criteria) this;
        }

        public Criteria andApplyLevelGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_LEVEL >=", value, "applyLevel");
            return (Criteria) this;
        }

        public Criteria andApplyLevelLessThan(String value) {
            addCriterion("APPLY_LEVEL <", value, "applyLevel");
            return (Criteria) this;
        }

        public Criteria andApplyLevelLessThanOrEqualTo(String value) {
            addCriterion("APPLY_LEVEL <=", value, "applyLevel");
            return (Criteria) this;
        }

        public Criteria andApplyLevelLike(String value) {
            addCriterion("APPLY_LEVEL like", value, "applyLevel");
            return (Criteria) this;
        }

        public Criteria andApplyLevelNotLike(String value) {
            addCriterion("APPLY_LEVEL not like", value, "applyLevel");
            return (Criteria) this;
        }

        public Criteria andApplyLevelIn(List<String> values) {
            addCriterion("APPLY_LEVEL in", values, "applyLevel");
            return (Criteria) this;
        }

        public Criteria andApplyLevelNotIn(List<String> values) {
            addCriterion("APPLY_LEVEL not in", values, "applyLevel");
            return (Criteria) this;
        }

        public Criteria andApplyLevelBetween(String value1, String value2) {
            addCriterion("APPLY_LEVEL between", value1, value2, "applyLevel");
            return (Criteria) this;
        }

        public Criteria andApplyLevelNotBetween(String value1, String value2) {
            addCriterion("APPLY_LEVEL not between", value1, value2, "applyLevel");
            return (Criteria) this;
        }

        public Criteria andWorkDayIsNull() {
            addCriterion("WORK_DAY is null");
            return (Criteria) this;
        }

        public Criteria andWorkDayIsNotNull() {
            addCriterion("WORK_DAY is not null");
            return (Criteria) this;
        }

        public Criteria andWorkDayEqualTo(String value) {
            addCriterion("WORK_DAY =", value, "workDay");
            return (Criteria) this;
        }

        public Criteria andWorkDayNotEqualTo(String value) {
            addCriterion("WORK_DAY <>", value, "workDay");
            return (Criteria) this;
        }

        public Criteria andWorkDayGreaterThan(String value) {
            addCriterion("WORK_DAY >", value, "workDay");
            return (Criteria) this;
        }

        public Criteria andWorkDayGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_DAY >=", value, "workDay");
            return (Criteria) this;
        }

        public Criteria andWorkDayLessThan(String value) {
            addCriterion("WORK_DAY <", value, "workDay");
            return (Criteria) this;
        }

        public Criteria andWorkDayLessThanOrEqualTo(String value) {
            addCriterion("WORK_DAY <=", value, "workDay");
            return (Criteria) this;
        }

        public Criteria andWorkDayLike(String value) {
            addCriterion("WORK_DAY like", value, "workDay");
            return (Criteria) this;
        }

        public Criteria andWorkDayNotLike(String value) {
            addCriterion("WORK_DAY not like", value, "workDay");
            return (Criteria) this;
        }

        public Criteria andWorkDayIn(List<String> values) {
            addCriterion("WORK_DAY in", values, "workDay");
            return (Criteria) this;
        }

        public Criteria andWorkDayNotIn(List<String> values) {
            addCriterion("WORK_DAY not in", values, "workDay");
            return (Criteria) this;
        }

        public Criteria andWorkDayBetween(String value1, String value2) {
            addCriterion("WORK_DAY between", value1, value2, "workDay");
            return (Criteria) this;
        }

        public Criteria andWorkDayNotBetween(String value1, String value2) {
            addCriterion("WORK_DAY not between", value1, value2, "workDay");
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