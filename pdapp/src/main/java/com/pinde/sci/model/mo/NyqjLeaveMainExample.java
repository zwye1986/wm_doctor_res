package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class NyqjLeaveMainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NyqjLeaveMainExample() {
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

        public Criteria andCollegeNameIsNull() {
            addCriterion("COLLEGE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCollegeNameIsNotNull() {
            addCriterion("COLLEGE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCollegeNameEqualTo(String value) {
            addCriterion("COLLEGE_NAME =", value, "collegeName");
            return (Criteria) this;
        }

        public Criteria andCollegeNameNotEqualTo(String value) {
            addCriterion("COLLEGE_NAME <>", value, "collegeName");
            return (Criteria) this;
        }

        public Criteria andCollegeNameGreaterThan(String value) {
            addCriterion("COLLEGE_NAME >", value, "collegeName");
            return (Criteria) this;
        }

        public Criteria andCollegeNameGreaterThanOrEqualTo(String value) {
            addCriterion("COLLEGE_NAME >=", value, "collegeName");
            return (Criteria) this;
        }

        public Criteria andCollegeNameLessThan(String value) {
            addCriterion("COLLEGE_NAME <", value, "collegeName");
            return (Criteria) this;
        }

        public Criteria andCollegeNameLessThanOrEqualTo(String value) {
            addCriterion("COLLEGE_NAME <=", value, "collegeName");
            return (Criteria) this;
        }

        public Criteria andCollegeNameLike(String value) {
            addCriterion("COLLEGE_NAME like", value, "collegeName");
            return (Criteria) this;
        }

        public Criteria andCollegeNameNotLike(String value) {
            addCriterion("COLLEGE_NAME not like", value, "collegeName");
            return (Criteria) this;
        }

        public Criteria andCollegeNameIn(List<String> values) {
            addCriterion("COLLEGE_NAME in", values, "collegeName");
            return (Criteria) this;
        }

        public Criteria andCollegeNameNotIn(List<String> values) {
            addCriterion("COLLEGE_NAME not in", values, "collegeName");
            return (Criteria) this;
        }

        public Criteria andCollegeNameBetween(String value1, String value2) {
            addCriterion("COLLEGE_NAME between", value1, value2, "collegeName");
            return (Criteria) this;
        }

        public Criteria andCollegeNameNotBetween(String value1, String value2) {
            addCriterion("COLLEGE_NAME not between", value1, value2, "collegeName");
            return (Criteria) this;
        }

        public Criteria andMajorNameIsNull() {
            addCriterion("MAJOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMajorNameIsNotNull() {
            addCriterion("MAJOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMajorNameEqualTo(String value) {
            addCriterion("MAJOR_NAME =", value, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameNotEqualTo(String value) {
            addCriterion("MAJOR_NAME <>", value, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameGreaterThan(String value) {
            addCriterion("MAJOR_NAME >", value, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameGreaterThanOrEqualTo(String value) {
            addCriterion("MAJOR_NAME >=", value, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameLessThan(String value) {
            addCriterion("MAJOR_NAME <", value, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameLessThanOrEqualTo(String value) {
            addCriterion("MAJOR_NAME <=", value, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameLike(String value) {
            addCriterion("MAJOR_NAME like", value, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameNotLike(String value) {
            addCriterion("MAJOR_NAME not like", value, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameIn(List<String> values) {
            addCriterion("MAJOR_NAME in", values, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameNotIn(List<String> values) {
            addCriterion("MAJOR_NAME not in", values, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameBetween(String value1, String value2) {
            addCriterion("MAJOR_NAME between", value1, value2, "majorName");
            return (Criteria) this;
        }

        public Criteria andMajorNameNotBetween(String value1, String value2) {
            addCriterion("MAJOR_NAME not between", value1, value2, "majorName");
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

        public Criteria andLeaveReasonIsNull() {
            addCriterion("LEAVE_REASON is null");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonIsNotNull() {
            addCriterion("LEAVE_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonEqualTo(String value) {
            addCriterion("LEAVE_REASON =", value, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonNotEqualTo(String value) {
            addCriterion("LEAVE_REASON <>", value, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonGreaterThan(String value) {
            addCriterion("LEAVE_REASON >", value, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonGreaterThanOrEqualTo(String value) {
            addCriterion("LEAVE_REASON >=", value, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonLessThan(String value) {
            addCriterion("LEAVE_REASON <", value, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonLessThanOrEqualTo(String value) {
            addCriterion("LEAVE_REASON <=", value, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonLike(String value) {
            addCriterion("LEAVE_REASON like", value, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonNotLike(String value) {
            addCriterion("LEAVE_REASON not like", value, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonIn(List<String> values) {
            addCriterion("LEAVE_REASON in", values, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonNotIn(List<String> values) {
            addCriterion("LEAVE_REASON not in", values, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonBetween(String value1, String value2) {
            addCriterion("LEAVE_REASON between", value1, value2, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveReasonNotBetween(String value1, String value2) {
            addCriterion("LEAVE_REASON not between", value1, value2, "leaveReason");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeIsNull() {
            addCriterion("LEAVE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeIsNotNull() {
            addCriterion("LEAVE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeEqualTo(String value) {
            addCriterion("LEAVE_TIME =", value, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeNotEqualTo(String value) {
            addCriterion("LEAVE_TIME <>", value, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeGreaterThan(String value) {
            addCriterion("LEAVE_TIME >", value, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeGreaterThanOrEqualTo(String value) {
            addCriterion("LEAVE_TIME >=", value, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeLessThan(String value) {
            addCriterion("LEAVE_TIME <", value, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeLessThanOrEqualTo(String value) {
            addCriterion("LEAVE_TIME <=", value, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeLike(String value) {
            addCriterion("LEAVE_TIME like", value, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeNotLike(String value) {
            addCriterion("LEAVE_TIME not like", value, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeIn(List<String> values) {
            addCriterion("LEAVE_TIME in", values, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeNotIn(List<String> values) {
            addCriterion("LEAVE_TIME not in", values, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeBetween(String value1, String value2) {
            addCriterion("LEAVE_TIME between", value1, value2, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andLeaveTimeNotBetween(String value1, String value2) {
            addCriterion("LEAVE_TIME not between", value1, value2, "leaveTime");
            return (Criteria) this;
        }

        public Criteria andDestinationIsNull() {
            addCriterion("DESTINATION is null");
            return (Criteria) this;
        }

        public Criteria andDestinationIsNotNull() {
            addCriterion("DESTINATION is not null");
            return (Criteria) this;
        }

        public Criteria andDestinationEqualTo(String value) {
            addCriterion("DESTINATION =", value, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationNotEqualTo(String value) {
            addCriterion("DESTINATION <>", value, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationGreaterThan(String value) {
            addCriterion("DESTINATION >", value, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationGreaterThanOrEqualTo(String value) {
            addCriterion("DESTINATION >=", value, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationLessThan(String value) {
            addCriterion("DESTINATION <", value, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationLessThanOrEqualTo(String value) {
            addCriterion("DESTINATION <=", value, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationLike(String value) {
            addCriterion("DESTINATION like", value, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationNotLike(String value) {
            addCriterion("DESTINATION not like", value, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationIn(List<String> values) {
            addCriterion("DESTINATION in", values, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationNotIn(List<String> values) {
            addCriterion("DESTINATION not in", values, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationBetween(String value1, String value2) {
            addCriterion("DESTINATION between", value1, value2, "destination");
            return (Criteria) this;
        }

        public Criteria andDestinationNotBetween(String value1, String value2) {
            addCriterion("DESTINATION not between", value1, value2, "destination");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneIsNull() {
            addCriterion("LINK_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneIsNotNull() {
            addCriterion("LINK_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneEqualTo(String value) {
            addCriterion("LINK_PHONE =", value, "linkPhone");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneNotEqualTo(String value) {
            addCriterion("LINK_PHONE <>", value, "linkPhone");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneGreaterThan(String value) {
            addCriterion("LINK_PHONE >", value, "linkPhone");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("LINK_PHONE >=", value, "linkPhone");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneLessThan(String value) {
            addCriterion("LINK_PHONE <", value, "linkPhone");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneLessThanOrEqualTo(String value) {
            addCriterion("LINK_PHONE <=", value, "linkPhone");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneLike(String value) {
            addCriterion("LINK_PHONE like", value, "linkPhone");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneNotLike(String value) {
            addCriterion("LINK_PHONE not like", value, "linkPhone");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneIn(List<String> values) {
            addCriterion("LINK_PHONE in", values, "linkPhone");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneNotIn(List<String> values) {
            addCriterion("LINK_PHONE not in", values, "linkPhone");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneBetween(String value1, String value2) {
            addCriterion("LINK_PHONE between", value1, value2, "linkPhone");
            return (Criteria) this;
        }

        public Criteria andLinkPhoneNotBetween(String value1, String value2) {
            addCriterion("LINK_PHONE not between", value1, value2, "linkPhone");
            return (Criteria) this;
        }

        public Criteria andYjsbmAuditFlagIsNull() {
            addCriterion("YJSBM_AUDIT_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andYjsbmAuditFlagIsNotNull() {
            addCriterion("YJSBM_AUDIT_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andYjsbmAuditFlagEqualTo(String value) {
            addCriterion("YJSBM_AUDIT_FLAG =", value, "yjsbmAuditFlag");
            return (Criteria) this;
        }

        public Criteria andYjsbmAuditFlagNotEqualTo(String value) {
            addCriterion("YJSBM_AUDIT_FLAG <>", value, "yjsbmAuditFlag");
            return (Criteria) this;
        }

        public Criteria andYjsbmAuditFlagGreaterThan(String value) {
            addCriterion("YJSBM_AUDIT_FLAG >", value, "yjsbmAuditFlag");
            return (Criteria) this;
        }

        public Criteria andYjsbmAuditFlagGreaterThanOrEqualTo(String value) {
            addCriterion("YJSBM_AUDIT_FLAG >=", value, "yjsbmAuditFlag");
            return (Criteria) this;
        }

        public Criteria andYjsbmAuditFlagLessThan(String value) {
            addCriterion("YJSBM_AUDIT_FLAG <", value, "yjsbmAuditFlag");
            return (Criteria) this;
        }

        public Criteria andYjsbmAuditFlagLessThanOrEqualTo(String value) {
            addCriterion("YJSBM_AUDIT_FLAG <=", value, "yjsbmAuditFlag");
            return (Criteria) this;
        }

        public Criteria andYjsbmAuditFlagLike(String value) {
            addCriterion("YJSBM_AUDIT_FLAG like", value, "yjsbmAuditFlag");
            return (Criteria) this;
        }

        public Criteria andYjsbmAuditFlagNotLike(String value) {
            addCriterion("YJSBM_AUDIT_FLAG not like", value, "yjsbmAuditFlag");
            return (Criteria) this;
        }

        public Criteria andYjsbmAuditFlagIn(List<String> values) {
            addCriterion("YJSBM_AUDIT_FLAG in", values, "yjsbmAuditFlag");
            return (Criteria) this;
        }

        public Criteria andYjsbmAuditFlagNotIn(List<String> values) {
            addCriterion("YJSBM_AUDIT_FLAG not in", values, "yjsbmAuditFlag");
            return (Criteria) this;
        }

        public Criteria andYjsbmAuditFlagBetween(String value1, String value2) {
            addCriterion("YJSBM_AUDIT_FLAG between", value1, value2, "yjsbmAuditFlag");
            return (Criteria) this;
        }

        public Criteria andYjsbmAuditFlagNotBetween(String value1, String value2) {
            addCriterion("YJSBM_AUDIT_FLAG not between", value1, value2, "yjsbmAuditFlag");
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

        public Criteria andSickLeaveFlagIsNull() {
            addCriterion("SICK_LEAVE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andSickLeaveFlagIsNotNull() {
            addCriterion("SICK_LEAVE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andSickLeaveFlagEqualTo(String value) {
            addCriterion("SICK_LEAVE_FLAG =", value, "sickLeaveFlag");
            return (Criteria) this;
        }

        public Criteria andSickLeaveFlagNotEqualTo(String value) {
            addCriterion("SICK_LEAVE_FLAG <>", value, "sickLeaveFlag");
            return (Criteria) this;
        }

        public Criteria andSickLeaveFlagGreaterThan(String value) {
            addCriterion("SICK_LEAVE_FLAG >", value, "sickLeaveFlag");
            return (Criteria) this;
        }

        public Criteria andSickLeaveFlagGreaterThanOrEqualTo(String value) {
            addCriterion("SICK_LEAVE_FLAG >=", value, "sickLeaveFlag");
            return (Criteria) this;
        }

        public Criteria andSickLeaveFlagLessThan(String value) {
            addCriterion("SICK_LEAVE_FLAG <", value, "sickLeaveFlag");
            return (Criteria) this;
        }

        public Criteria andSickLeaveFlagLessThanOrEqualTo(String value) {
            addCriterion("SICK_LEAVE_FLAG <=", value, "sickLeaveFlag");
            return (Criteria) this;
        }

        public Criteria andSickLeaveFlagLike(String value) {
            addCriterion("SICK_LEAVE_FLAG like", value, "sickLeaveFlag");
            return (Criteria) this;
        }

        public Criteria andSickLeaveFlagNotLike(String value) {
            addCriterion("SICK_LEAVE_FLAG not like", value, "sickLeaveFlag");
            return (Criteria) this;
        }

        public Criteria andSickLeaveFlagIn(List<String> values) {
            addCriterion("SICK_LEAVE_FLAG in", values, "sickLeaveFlag");
            return (Criteria) this;
        }

        public Criteria andSickLeaveFlagNotIn(List<String> values) {
            addCriterion("SICK_LEAVE_FLAG not in", values, "sickLeaveFlag");
            return (Criteria) this;
        }

        public Criteria andSickLeaveFlagBetween(String value1, String value2) {
            addCriterion("SICK_LEAVE_FLAG between", value1, value2, "sickLeaveFlag");
            return (Criteria) this;
        }

        public Criteria andSickLeaveFlagNotBetween(String value1, String value2) {
            addCriterion("SICK_LEAVE_FLAG not between", value1, value2, "sickLeaveFlag");
            return (Criteria) this;
        }

        public Criteria andLeaderOrgFlowIsNull() {
            addCriterion("LEADER_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andLeaderOrgFlowIsNotNull() {
            addCriterion("LEADER_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderOrgFlowEqualTo(String value) {
            addCriterion("LEADER_ORG_FLOW =", value, "leaderOrgFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderOrgFlowNotEqualTo(String value) {
            addCriterion("LEADER_ORG_FLOW <>", value, "leaderOrgFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderOrgFlowGreaterThan(String value) {
            addCriterion("LEADER_ORG_FLOW >", value, "leaderOrgFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_ORG_FLOW >=", value, "leaderOrgFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderOrgFlowLessThan(String value) {
            addCriterion("LEADER_ORG_FLOW <", value, "leaderOrgFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("LEADER_ORG_FLOW <=", value, "leaderOrgFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderOrgFlowLike(String value) {
            addCriterion("LEADER_ORG_FLOW like", value, "leaderOrgFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderOrgFlowNotLike(String value) {
            addCriterion("LEADER_ORG_FLOW not like", value, "leaderOrgFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderOrgFlowIn(List<String> values) {
            addCriterion("LEADER_ORG_FLOW in", values, "leaderOrgFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderOrgFlowNotIn(List<String> values) {
            addCriterion("LEADER_ORG_FLOW not in", values, "leaderOrgFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderOrgFlowBetween(String value1, String value2) {
            addCriterion("LEADER_ORG_FLOW between", value1, value2, "leaderOrgFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderOrgFlowNotBetween(String value1, String value2) {
            addCriterion("LEADER_ORG_FLOW not between", value1, value2, "leaderOrgFlow");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusIdIsNull() {
            addCriterion("LEADER_AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusIdIsNotNull() {
            addCriterion("LEADER_AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusIdEqualTo(String value) {
            addCriterion("LEADER_AUDIT_STATUS_ID =", value, "leaderAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusIdNotEqualTo(String value) {
            addCriterion("LEADER_AUDIT_STATUS_ID <>", value, "leaderAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusIdGreaterThan(String value) {
            addCriterion("LEADER_AUDIT_STATUS_ID >", value, "leaderAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_AUDIT_STATUS_ID >=", value, "leaderAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusIdLessThan(String value) {
            addCriterion("LEADER_AUDIT_STATUS_ID <", value, "leaderAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("LEADER_AUDIT_STATUS_ID <=", value, "leaderAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusIdLike(String value) {
            addCriterion("LEADER_AUDIT_STATUS_ID like", value, "leaderAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusIdNotLike(String value) {
            addCriterion("LEADER_AUDIT_STATUS_ID not like", value, "leaderAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusIdIn(List<String> values) {
            addCriterion("LEADER_AUDIT_STATUS_ID in", values, "leaderAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusIdNotIn(List<String> values) {
            addCriterion("LEADER_AUDIT_STATUS_ID not in", values, "leaderAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusIdBetween(String value1, String value2) {
            addCriterion("LEADER_AUDIT_STATUS_ID between", value1, value2, "leaderAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("LEADER_AUDIT_STATUS_ID not between", value1, value2, "leaderAuditStatusId");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusNameIsNull() {
            addCriterion("LEADER_AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusNameIsNotNull() {
            addCriterion("LEADER_AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusNameEqualTo(String value) {
            addCriterion("LEADER_AUDIT_STATUS_NAME =", value, "leaderAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusNameNotEqualTo(String value) {
            addCriterion("LEADER_AUDIT_STATUS_NAME <>", value, "leaderAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusNameGreaterThan(String value) {
            addCriterion("LEADER_AUDIT_STATUS_NAME >", value, "leaderAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_AUDIT_STATUS_NAME >=", value, "leaderAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusNameLessThan(String value) {
            addCriterion("LEADER_AUDIT_STATUS_NAME <", value, "leaderAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("LEADER_AUDIT_STATUS_NAME <=", value, "leaderAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusNameLike(String value) {
            addCriterion("LEADER_AUDIT_STATUS_NAME like", value, "leaderAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusNameNotLike(String value) {
            addCriterion("LEADER_AUDIT_STATUS_NAME not like", value, "leaderAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusNameIn(List<String> values) {
            addCriterion("LEADER_AUDIT_STATUS_NAME in", values, "leaderAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusNameNotIn(List<String> values) {
            addCriterion("LEADER_AUDIT_STATUS_NAME not in", values, "leaderAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusNameBetween(String value1, String value2) {
            addCriterion("LEADER_AUDIT_STATUS_NAME between", value1, value2, "leaderAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("LEADER_AUDIT_STATUS_NAME not between", value1, value2, "leaderAuditStatusName");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditAdviceIsNull() {
            addCriterion("LEADER_AUDIT_ADVICE is null");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditAdviceIsNotNull() {
            addCriterion("LEADER_AUDIT_ADVICE is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditAdviceEqualTo(String value) {
            addCriterion("LEADER_AUDIT_ADVICE =", value, "leaderAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditAdviceNotEqualTo(String value) {
            addCriterion("LEADER_AUDIT_ADVICE <>", value, "leaderAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditAdviceGreaterThan(String value) {
            addCriterion("LEADER_AUDIT_ADVICE >", value, "leaderAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditAdviceGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_AUDIT_ADVICE >=", value, "leaderAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditAdviceLessThan(String value) {
            addCriterion("LEADER_AUDIT_ADVICE <", value, "leaderAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditAdviceLessThanOrEqualTo(String value) {
            addCriterion("LEADER_AUDIT_ADVICE <=", value, "leaderAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditAdviceLike(String value) {
            addCriterion("LEADER_AUDIT_ADVICE like", value, "leaderAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditAdviceNotLike(String value) {
            addCriterion("LEADER_AUDIT_ADVICE not like", value, "leaderAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditAdviceIn(List<String> values) {
            addCriterion("LEADER_AUDIT_ADVICE in", values, "leaderAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditAdviceNotIn(List<String> values) {
            addCriterion("LEADER_AUDIT_ADVICE not in", values, "leaderAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditAdviceBetween(String value1, String value2) {
            addCriterion("LEADER_AUDIT_ADVICE between", value1, value2, "leaderAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditAdviceNotBetween(String value1, String value2) {
            addCriterion("LEADER_AUDIT_ADVICE not between", value1, value2, "leaderAuditAdvice");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditTimeIsNull() {
            addCriterion("LEADER_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditTimeIsNotNull() {
            addCriterion("LEADER_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditTimeEqualTo(String value) {
            addCriterion("LEADER_AUDIT_TIME =", value, "leaderAuditTime");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditTimeNotEqualTo(String value) {
            addCriterion("LEADER_AUDIT_TIME <>", value, "leaderAuditTime");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditTimeGreaterThan(String value) {
            addCriterion("LEADER_AUDIT_TIME >", value, "leaderAuditTime");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("LEADER_AUDIT_TIME >=", value, "leaderAuditTime");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditTimeLessThan(String value) {
            addCriterion("LEADER_AUDIT_TIME <", value, "leaderAuditTime");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("LEADER_AUDIT_TIME <=", value, "leaderAuditTime");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditTimeLike(String value) {
            addCriterion("LEADER_AUDIT_TIME like", value, "leaderAuditTime");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditTimeNotLike(String value) {
            addCriterion("LEADER_AUDIT_TIME not like", value, "leaderAuditTime");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditTimeIn(List<String> values) {
            addCriterion("LEADER_AUDIT_TIME in", values, "leaderAuditTime");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditTimeNotIn(List<String> values) {
            addCriterion("LEADER_AUDIT_TIME not in", values, "leaderAuditTime");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditTimeBetween(String value1, String value2) {
            addCriterion("LEADER_AUDIT_TIME between", value1, value2, "leaderAuditTime");
            return (Criteria) this;
        }

        public Criteria andLeaderAuditTimeNotBetween(String value1, String value2) {
            addCriterion("LEADER_AUDIT_TIME not between", value1, value2, "leaderAuditTime");
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

        public Criteria andDaysOfLeaveIsNull() {
            addCriterion("DAYS_OF_LEAVE is null");
            return (Criteria) this;
        }

        public Criteria andDaysOfLeaveIsNotNull() {
            addCriterion("DAYS_OF_LEAVE is not null");
            return (Criteria) this;
        }

        public Criteria andDaysOfLeaveEqualTo(String value) {
            addCriterion("DAYS_OF_LEAVE =", value, "daysOfLeave");
            return (Criteria) this;
        }

        public Criteria andDaysOfLeaveNotEqualTo(String value) {
            addCriterion("DAYS_OF_LEAVE <>", value, "daysOfLeave");
            return (Criteria) this;
        }

        public Criteria andDaysOfLeaveGreaterThan(String value) {
            addCriterion("DAYS_OF_LEAVE >", value, "daysOfLeave");
            return (Criteria) this;
        }

        public Criteria andDaysOfLeaveGreaterThanOrEqualTo(String value) {
            addCriterion("DAYS_OF_LEAVE >=", value, "daysOfLeave");
            return (Criteria) this;
        }

        public Criteria andDaysOfLeaveLessThan(String value) {
            addCriterion("DAYS_OF_LEAVE <", value, "daysOfLeave");
            return (Criteria) this;
        }

        public Criteria andDaysOfLeaveLessThanOrEqualTo(String value) {
            addCriterion("DAYS_OF_LEAVE <=", value, "daysOfLeave");
            return (Criteria) this;
        }

        public Criteria andDaysOfLeaveLike(String value) {
            addCriterion("DAYS_OF_LEAVE like", value, "daysOfLeave");
            return (Criteria) this;
        }

        public Criteria andDaysOfLeaveNotLike(String value) {
            addCriterion("DAYS_OF_LEAVE not like", value, "daysOfLeave");
            return (Criteria) this;
        }

        public Criteria andDaysOfLeaveIn(List<String> values) {
            addCriterion("DAYS_OF_LEAVE in", values, "daysOfLeave");
            return (Criteria) this;
        }

        public Criteria andDaysOfLeaveNotIn(List<String> values) {
            addCriterion("DAYS_OF_LEAVE not in", values, "daysOfLeave");
            return (Criteria) this;
        }

        public Criteria andDaysOfLeaveBetween(String value1, String value2) {
            addCriterion("DAYS_OF_LEAVE between", value1, value2, "daysOfLeave");
            return (Criteria) this;
        }

        public Criteria andDaysOfLeaveNotBetween(String value1, String value2) {
            addCriterion("DAYS_OF_LEAVE not between", value1, value2, "daysOfLeave");
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