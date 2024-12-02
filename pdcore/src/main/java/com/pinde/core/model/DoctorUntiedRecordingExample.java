package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class DoctorUntiedRecordingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DoctorUntiedRecordingExample() {
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

        public Criteria andLockDateIsNull() {
            addCriterion("LOCK_DATE is null");
            return (Criteria) this;
        }

        public Criteria andLockDateIsNotNull() {
            addCriterion("LOCK_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andLockDateEqualTo(String value) {
            addCriterion("LOCK_DATE =", value, "lockDate");
            return (Criteria) this;
        }

        public Criteria andLockDateNotEqualTo(String value) {
            addCriterion("LOCK_DATE <>", value, "lockDate");
            return (Criteria) this;
        }

        public Criteria andLockDateGreaterThan(String value) {
            addCriterion("LOCK_DATE >", value, "lockDate");
            return (Criteria) this;
        }

        public Criteria andLockDateGreaterThanOrEqualTo(String value) {
            addCriterion("LOCK_DATE >=", value, "lockDate");
            return (Criteria) this;
        }

        public Criteria andLockDateLessThan(String value) {
            addCriterion("LOCK_DATE <", value, "lockDate");
            return (Criteria) this;
        }

        public Criteria andLockDateLessThanOrEqualTo(String value) {
            addCriterion("LOCK_DATE <=", value, "lockDate");
            return (Criteria) this;
        }

        public Criteria andLockDateLike(String value) {
            addCriterion("LOCK_DATE like", value, "lockDate");
            return (Criteria) this;
        }

        public Criteria andLockDateNotLike(String value) {
            addCriterion("LOCK_DATE not like", value, "lockDate");
            return (Criteria) this;
        }

        public Criteria andLockDateIn(List<String> values) {
            addCriterion("LOCK_DATE in", values, "lockDate");
            return (Criteria) this;
        }

        public Criteria andLockDateNotIn(List<String> values) {
            addCriterion("LOCK_DATE not in", values, "lockDate");
            return (Criteria) this;
        }

        public Criteria andLockDateBetween(String value1, String value2) {
            addCriterion("LOCK_DATE between", value1, value2, "lockDate");
            return (Criteria) this;
        }

        public Criteria andLockDateNotBetween(String value1, String value2) {
            addCriterion("LOCK_DATE not between", value1, value2, "lockDate");
            return (Criteria) this;
        }

        public Criteria andUntiedDateIsNull() {
            addCriterion("UNTIED_DATE is null");
            return (Criteria) this;
        }

        public Criteria andUntiedDateIsNotNull() {
            addCriterion("UNTIED_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andUntiedDateEqualTo(String value) {
            addCriterion("UNTIED_DATE =", value, "untiedDate");
            return (Criteria) this;
        }

        public Criteria andUntiedDateNotEqualTo(String value) {
            addCriterion("UNTIED_DATE <>", value, "untiedDate");
            return (Criteria) this;
        }

        public Criteria andUntiedDateGreaterThan(String value) {
            addCriterion("UNTIED_DATE >", value, "untiedDate");
            return (Criteria) this;
        }

        public Criteria andUntiedDateGreaterThanOrEqualTo(String value) {
            addCriterion("UNTIED_DATE >=", value, "untiedDate");
            return (Criteria) this;
        }

        public Criteria andUntiedDateLessThan(String value) {
            addCriterion("UNTIED_DATE <", value, "untiedDate");
            return (Criteria) this;
        }

        public Criteria andUntiedDateLessThanOrEqualTo(String value) {
            addCriterion("UNTIED_DATE <=", value, "untiedDate");
            return (Criteria) this;
        }

        public Criteria andUntiedDateLike(String value) {
            addCriterion("UNTIED_DATE like", value, "untiedDate");
            return (Criteria) this;
        }

        public Criteria andUntiedDateNotLike(String value) {
            addCriterion("UNTIED_DATE not like", value, "untiedDate");
            return (Criteria) this;
        }

        public Criteria andUntiedDateIn(List<String> values) {
            addCriterion("UNTIED_DATE in", values, "untiedDate");
            return (Criteria) this;
        }

        public Criteria andUntiedDateNotIn(List<String> values) {
            addCriterion("UNTIED_DATE not in", values, "untiedDate");
            return (Criteria) this;
        }

        public Criteria andUntiedDateBetween(String value1, String value2) {
            addCriterion("UNTIED_DATE between", value1, value2, "untiedDate");
            return (Criteria) this;
        }

        public Criteria andUntiedDateNotBetween(String value1, String value2) {
            addCriterion("UNTIED_DATE not between", value1, value2, "untiedDate");
            return (Criteria) this;
        }

        public Criteria andUntiedDescriptionIsNull() {
            addCriterion("UNTIED_DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andUntiedDescriptionIsNotNull() {
            addCriterion("UNTIED_DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andUntiedDescriptionEqualTo(String value) {
            addCriterion("UNTIED_DESCRIPTION =", value, "untiedDescription");
            return (Criteria) this;
        }

        public Criteria andUntiedDescriptionNotEqualTo(String value) {
            addCriterion("UNTIED_DESCRIPTION <>", value, "untiedDescription");
            return (Criteria) this;
        }

        public Criteria andUntiedDescriptionGreaterThan(String value) {
            addCriterion("UNTIED_DESCRIPTION >", value, "untiedDescription");
            return (Criteria) this;
        }

        public Criteria andUntiedDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("UNTIED_DESCRIPTION >=", value, "untiedDescription");
            return (Criteria) this;
        }

        public Criteria andUntiedDescriptionLessThan(String value) {
            addCriterion("UNTIED_DESCRIPTION <", value, "untiedDescription");
            return (Criteria) this;
        }

        public Criteria andUntiedDescriptionLessThanOrEqualTo(String value) {
            addCriterion("UNTIED_DESCRIPTION <=", value, "untiedDescription");
            return (Criteria) this;
        }

        public Criteria andUntiedDescriptionLike(String value) {
            addCriterion("UNTIED_DESCRIPTION like", value, "untiedDescription");
            return (Criteria) this;
        }

        public Criteria andUntiedDescriptionNotLike(String value) {
            addCriterion("UNTIED_DESCRIPTION not like", value, "untiedDescription");
            return (Criteria) this;
        }

        public Criteria andUntiedDescriptionIn(List<String> values) {
            addCriterion("UNTIED_DESCRIPTION in", values, "untiedDescription");
            return (Criteria) this;
        }

        public Criteria andUntiedDescriptionNotIn(List<String> values) {
            addCriterion("UNTIED_DESCRIPTION not in", values, "untiedDescription");
            return (Criteria) this;
        }

        public Criteria andUntiedDescriptionBetween(String value1, String value2) {
            addCriterion("UNTIED_DESCRIPTION between", value1, value2, "untiedDescription");
            return (Criteria) this;
        }

        public Criteria andUntiedDescriptionNotBetween(String value1, String value2) {
            addCriterion("UNTIED_DESCRIPTION not between", value1, value2, "untiedDescription");
            return (Criteria) this;
        }

        public Criteria andUntiedFileIsNull() {
            addCriterion("UNTIED_FILE is null");
            return (Criteria) this;
        }

        public Criteria andUntiedFileIsNotNull() {
            addCriterion("UNTIED_FILE is not null");
            return (Criteria) this;
        }

        public Criteria andUntiedFileEqualTo(String value) {
            addCriterion("UNTIED_FILE =", value, "untiedFile");
            return (Criteria) this;
        }

        public Criteria andUntiedFileNotEqualTo(String value) {
            addCriterion("UNTIED_FILE <>", value, "untiedFile");
            return (Criteria) this;
        }

        public Criteria andUntiedFileGreaterThan(String value) {
            addCriterion("UNTIED_FILE >", value, "untiedFile");
            return (Criteria) this;
        }

        public Criteria andUntiedFileGreaterThanOrEqualTo(String value) {
            addCriterion("UNTIED_FILE >=", value, "untiedFile");
            return (Criteria) this;
        }

        public Criteria andUntiedFileLessThan(String value) {
            addCriterion("UNTIED_FILE <", value, "untiedFile");
            return (Criteria) this;
        }

        public Criteria andUntiedFileLessThanOrEqualTo(String value) {
            addCriterion("UNTIED_FILE <=", value, "untiedFile");
            return (Criteria) this;
        }

        public Criteria andUntiedFileLike(String value) {
            addCriterion("UNTIED_FILE like", value, "untiedFile");
            return (Criteria) this;
        }

        public Criteria andUntiedFileNotLike(String value) {
            addCriterion("UNTIED_FILE not like", value, "untiedFile");
            return (Criteria) this;
        }

        public Criteria andUntiedFileIn(List<String> values) {
            addCriterion("UNTIED_FILE in", values, "untiedFile");
            return (Criteria) this;
        }

        public Criteria andUntiedFileNotIn(List<String> values) {
            addCriterion("UNTIED_FILE not in", values, "untiedFile");
            return (Criteria) this;
        }

        public Criteria andUntiedFileBetween(String value1, String value2) {
            addCriterion("UNTIED_FILE between", value1, value2, "untiedFile");
            return (Criteria) this;
        }

        public Criteria andUntiedFileNotBetween(String value1, String value2) {
            addCriterion("UNTIED_FILE not between", value1, value2, "untiedFile");
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

        public Criteria andLockStatusIsNull() {
            addCriterion("LOCK_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andLockStatusIsNotNull() {
            addCriterion("LOCK_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andLockStatusEqualTo(String value) {
            addCriterion("LOCK_STATUS =", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusNotEqualTo(String value) {
            addCriterion("LOCK_STATUS <>", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusGreaterThan(String value) {
            addCriterion("LOCK_STATUS >", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusGreaterThanOrEqualTo(String value) {
            addCriterion("LOCK_STATUS >=", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusLessThan(String value) {
            addCriterion("LOCK_STATUS <", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusLessThanOrEqualTo(String value) {
            addCriterion("LOCK_STATUS <=", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusLike(String value) {
            addCriterion("LOCK_STATUS like", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusNotLike(String value) {
            addCriterion("LOCK_STATUS not like", value, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusIn(List<String> values) {
            addCriterion("LOCK_STATUS in", values, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusNotIn(List<String> values) {
            addCriterion("LOCK_STATUS not in", values, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusBetween(String value1, String value2) {
            addCriterion("LOCK_STATUS between", value1, value2, "lockStatus");
            return (Criteria) this;
        }

        public Criteria andLockStatusNotBetween(String value1, String value2) {
            addCriterion("LOCK_STATUS not between", value1, value2, "lockStatus");
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