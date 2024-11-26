package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class RecruitTargetMainDetailLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RecruitTargetMainDetailLogExample() {
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

        public Criteria andRefRecordFlowIsNull() {
            addCriterion("REF_RECORD_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowIsNotNull() {
            addCriterion("REF_RECORD_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowEqualTo(String value) {
            addCriterion("REF_RECORD_FLOW =", value, "refRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowNotEqualTo(String value) {
            addCriterion("REF_RECORD_FLOW <>", value, "refRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowGreaterThan(String value) {
            addCriterion("REF_RECORD_FLOW >", value, "refRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowGreaterThanOrEqualTo(String value) {
            addCriterion("REF_RECORD_FLOW >=", value, "refRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowLessThan(String value) {
            addCriterion("REF_RECORD_FLOW <", value, "refRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowLessThanOrEqualTo(String value) {
            addCriterion("REF_RECORD_FLOW <=", value, "refRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowLike(String value) {
            addCriterion("REF_RECORD_FLOW like", value, "refRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowNotLike(String value) {
            addCriterion("REF_RECORD_FLOW not like", value, "refRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowIn(List<String> values) {
            addCriterion("REF_RECORD_FLOW in", values, "refRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowNotIn(List<String> values) {
            addCriterion("REF_RECORD_FLOW not in", values, "refRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowBetween(String value1, String value2) {
            addCriterion("REF_RECORD_FLOW between", value1, value2, "refRecordFlow");
            return (Criteria) this;
        }

        public Criteria andRefRecordFlowNotBetween(String value1, String value2) {
            addCriterion("REF_RECORD_FLOW not between", value1, value2, "refRecordFlow");
            return (Criteria) this;
        }

        public Criteria andAcademicNumIsNull() {
            addCriterion("ACADEMIC_NUM is null");
            return (Criteria) this;
        }

        public Criteria andAcademicNumIsNotNull() {
            addCriterion("ACADEMIC_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andAcademicNumEqualTo(String value) {
            addCriterion("ACADEMIC_NUM =", value, "academicNum");
            return (Criteria) this;
        }

        public Criteria andAcademicNumNotEqualTo(String value) {
            addCriterion("ACADEMIC_NUM <>", value, "academicNum");
            return (Criteria) this;
        }

        public Criteria andAcademicNumGreaterThan(String value) {
            addCriterion("ACADEMIC_NUM >", value, "academicNum");
            return (Criteria) this;
        }

        public Criteria andAcademicNumGreaterThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_NUM >=", value, "academicNum");
            return (Criteria) this;
        }

        public Criteria andAcademicNumLessThan(String value) {
            addCriterion("ACADEMIC_NUM <", value, "academicNum");
            return (Criteria) this;
        }

        public Criteria andAcademicNumLessThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_NUM <=", value, "academicNum");
            return (Criteria) this;
        }

        public Criteria andAcademicNumLike(String value) {
            addCriterion("ACADEMIC_NUM like", value, "academicNum");
            return (Criteria) this;
        }

        public Criteria andAcademicNumNotLike(String value) {
            addCriterion("ACADEMIC_NUM not like", value, "academicNum");
            return (Criteria) this;
        }

        public Criteria andAcademicNumIn(List<String> values) {
            addCriterion("ACADEMIC_NUM in", values, "academicNum");
            return (Criteria) this;
        }

        public Criteria andAcademicNumNotIn(List<String> values) {
            addCriterion("ACADEMIC_NUM not in", values, "academicNum");
            return (Criteria) this;
        }

        public Criteria andAcademicNumBetween(String value1, String value2) {
            addCriterion("ACADEMIC_NUM between", value1, value2, "academicNum");
            return (Criteria) this;
        }

        public Criteria andAcademicNumNotBetween(String value1, String value2) {
            addCriterion("ACADEMIC_NUM not between", value1, value2, "academicNum");
            return (Criteria) this;
        }

        public Criteria andAcademicModifyNumIsNull() {
            addCriterion("ACADEMIC_MODIFY_NUM is null");
            return (Criteria) this;
        }

        public Criteria andAcademicModifyNumIsNotNull() {
            addCriterion("ACADEMIC_MODIFY_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andAcademicModifyNumEqualTo(String value) {
            addCriterion("ACADEMIC_MODIFY_NUM =", value, "academicModifyNum");
            return (Criteria) this;
        }

        public Criteria andAcademicModifyNumNotEqualTo(String value) {
            addCriterion("ACADEMIC_MODIFY_NUM <>", value, "academicModifyNum");
            return (Criteria) this;
        }

        public Criteria andAcademicModifyNumGreaterThan(String value) {
            addCriterion("ACADEMIC_MODIFY_NUM >", value, "academicModifyNum");
            return (Criteria) this;
        }

        public Criteria andAcademicModifyNumGreaterThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_MODIFY_NUM >=", value, "academicModifyNum");
            return (Criteria) this;
        }

        public Criteria andAcademicModifyNumLessThan(String value) {
            addCriterion("ACADEMIC_MODIFY_NUM <", value, "academicModifyNum");
            return (Criteria) this;
        }

        public Criteria andAcademicModifyNumLessThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_MODIFY_NUM <=", value, "academicModifyNum");
            return (Criteria) this;
        }

        public Criteria andAcademicModifyNumLike(String value) {
            addCriterion("ACADEMIC_MODIFY_NUM like", value, "academicModifyNum");
            return (Criteria) this;
        }

        public Criteria andAcademicModifyNumNotLike(String value) {
            addCriterion("ACADEMIC_MODIFY_NUM not like", value, "academicModifyNum");
            return (Criteria) this;
        }

        public Criteria andAcademicModifyNumIn(List<String> values) {
            addCriterion("ACADEMIC_MODIFY_NUM in", values, "academicModifyNum");
            return (Criteria) this;
        }

        public Criteria andAcademicModifyNumNotIn(List<String> values) {
            addCriterion("ACADEMIC_MODIFY_NUM not in", values, "academicModifyNum");
            return (Criteria) this;
        }

        public Criteria andAcademicModifyNumBetween(String value1, String value2) {
            addCriterion("ACADEMIC_MODIFY_NUM between", value1, value2, "academicModifyNum");
            return (Criteria) this;
        }

        public Criteria andAcademicModifyNumNotBetween(String value1, String value2) {
            addCriterion("ACADEMIC_MODIFY_NUM not between", value1, value2, "academicModifyNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumIsNull() {
            addCriterion("SPECIALIZED_NUM is null");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumIsNotNull() {
            addCriterion("SPECIALIZED_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumEqualTo(String value) {
            addCriterion("SPECIALIZED_NUM =", value, "specializedNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumNotEqualTo(String value) {
            addCriterion("SPECIALIZED_NUM <>", value, "specializedNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumGreaterThan(String value) {
            addCriterion("SPECIALIZED_NUM >", value, "specializedNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumGreaterThanOrEqualTo(String value) {
            addCriterion("SPECIALIZED_NUM >=", value, "specializedNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumLessThan(String value) {
            addCriterion("SPECIALIZED_NUM <", value, "specializedNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumLessThanOrEqualTo(String value) {
            addCriterion("SPECIALIZED_NUM <=", value, "specializedNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumLike(String value) {
            addCriterion("SPECIALIZED_NUM like", value, "specializedNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumNotLike(String value) {
            addCriterion("SPECIALIZED_NUM not like", value, "specializedNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumIn(List<String> values) {
            addCriterion("SPECIALIZED_NUM in", values, "specializedNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumNotIn(List<String> values) {
            addCriterion("SPECIALIZED_NUM not in", values, "specializedNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumBetween(String value1, String value2) {
            addCriterion("SPECIALIZED_NUM between", value1, value2, "specializedNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedNumNotBetween(String value1, String value2) {
            addCriterion("SPECIALIZED_NUM not between", value1, value2, "specializedNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedModifyNumIsNull() {
            addCriterion("SPECIALIZED_MODIFY_NUM is null");
            return (Criteria) this;
        }

        public Criteria andSpecializedModifyNumIsNotNull() {
            addCriterion("SPECIALIZED_MODIFY_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andSpecializedModifyNumEqualTo(String value) {
            addCriterion("SPECIALIZED_MODIFY_NUM =", value, "specializedModifyNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedModifyNumNotEqualTo(String value) {
            addCriterion("SPECIALIZED_MODIFY_NUM <>", value, "specializedModifyNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedModifyNumGreaterThan(String value) {
            addCriterion("SPECIALIZED_MODIFY_NUM >", value, "specializedModifyNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedModifyNumGreaterThanOrEqualTo(String value) {
            addCriterion("SPECIALIZED_MODIFY_NUM >=", value, "specializedModifyNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedModifyNumLessThan(String value) {
            addCriterion("SPECIALIZED_MODIFY_NUM <", value, "specializedModifyNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedModifyNumLessThanOrEqualTo(String value) {
            addCriterion("SPECIALIZED_MODIFY_NUM <=", value, "specializedModifyNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedModifyNumLike(String value) {
            addCriterion("SPECIALIZED_MODIFY_NUM like", value, "specializedModifyNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedModifyNumNotLike(String value) {
            addCriterion("SPECIALIZED_MODIFY_NUM not like", value, "specializedModifyNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedModifyNumIn(List<String> values) {
            addCriterion("SPECIALIZED_MODIFY_NUM in", values, "specializedModifyNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedModifyNumNotIn(List<String> values) {
            addCriterion("SPECIALIZED_MODIFY_NUM not in", values, "specializedModifyNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedModifyNumBetween(String value1, String value2) {
            addCriterion("SPECIALIZED_MODIFY_NUM between", value1, value2, "specializedModifyNum");
            return (Criteria) this;
        }

        public Criteria andSpecializedModifyNumNotBetween(String value1, String value2) {
            addCriterion("SPECIALIZED_MODIFY_NUM not between", value1, value2, "specializedModifyNum");
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