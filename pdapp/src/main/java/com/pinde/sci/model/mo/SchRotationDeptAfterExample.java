package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class SchRotationDeptAfterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SchRotationDeptAfterExample() {
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

        public Criteria andSchRotationDeptFlowIsNull() {
            addCriterion("SCH_ROTATION_DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowIsNotNull() {
            addCriterion("SCH_ROTATION_DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowEqualTo(String value) {
            addCriterion("SCH_ROTATION_DEPT_FLOW =", value, "schRotationDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowNotEqualTo(String value) {
            addCriterion("SCH_ROTATION_DEPT_FLOW <>", value, "schRotationDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowGreaterThan(String value) {
            addCriterion("SCH_ROTATION_DEPT_FLOW >", value, "schRotationDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_ROTATION_DEPT_FLOW >=", value, "schRotationDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowLessThan(String value) {
            addCriterion("SCH_ROTATION_DEPT_FLOW <", value, "schRotationDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("SCH_ROTATION_DEPT_FLOW <=", value, "schRotationDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowLike(String value) {
            addCriterion("SCH_ROTATION_DEPT_FLOW like", value, "schRotationDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowNotLike(String value) {
            addCriterion("SCH_ROTATION_DEPT_FLOW not like", value, "schRotationDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowIn(List<String> values) {
            addCriterion("SCH_ROTATION_DEPT_FLOW in", values, "schRotationDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowNotIn(List<String> values) {
            addCriterion("SCH_ROTATION_DEPT_FLOW not in", values, "schRotationDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowBetween(String value1, String value2) {
            addCriterion("SCH_ROTATION_DEPT_FLOW between", value1, value2, "schRotationDeptFlow");
            return (Criteria) this;
        }

        public Criteria andSchRotationDeptFlowNotBetween(String value1, String value2) {
            addCriterion("SCH_ROTATION_DEPT_FLOW not between", value1, value2, "schRotationDeptFlow");
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

        public Criteria andModfiyTimeIsNull() {
            addCriterion("MODFIY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andModfiyTimeIsNotNull() {
            addCriterion("MODFIY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andModfiyTimeEqualTo(String value) {
            addCriterion("MODFIY_TIME =", value, "modfiyTime");
            return (Criteria) this;
        }

        public Criteria andModfiyTimeNotEqualTo(String value) {
            addCriterion("MODFIY_TIME <>", value, "modfiyTime");
            return (Criteria) this;
        }

        public Criteria andModfiyTimeGreaterThan(String value) {
            addCriterion("MODFIY_TIME >", value, "modfiyTime");
            return (Criteria) this;
        }

        public Criteria andModfiyTimeGreaterThanOrEqualTo(String value) {
            addCriterion("MODFIY_TIME >=", value, "modfiyTime");
            return (Criteria) this;
        }

        public Criteria andModfiyTimeLessThan(String value) {
            addCriterion("MODFIY_TIME <", value, "modfiyTime");
            return (Criteria) this;
        }

        public Criteria andModfiyTimeLessThanOrEqualTo(String value) {
            addCriterion("MODFIY_TIME <=", value, "modfiyTime");
            return (Criteria) this;
        }

        public Criteria andModfiyTimeLike(String value) {
            addCriterion("MODFIY_TIME like", value, "modfiyTime");
            return (Criteria) this;
        }

        public Criteria andModfiyTimeNotLike(String value) {
            addCriterion("MODFIY_TIME not like", value, "modfiyTime");
            return (Criteria) this;
        }

        public Criteria andModfiyTimeIn(List<String> values) {
            addCriterion("MODFIY_TIME in", values, "modfiyTime");
            return (Criteria) this;
        }

        public Criteria andModfiyTimeNotIn(List<String> values) {
            addCriterion("MODFIY_TIME not in", values, "modfiyTime");
            return (Criteria) this;
        }

        public Criteria andModfiyTimeBetween(String value1, String value2) {
            addCriterion("MODFIY_TIME between", value1, value2, "modfiyTime");
            return (Criteria) this;
        }

        public Criteria andModfiyTimeNotBetween(String value1, String value2) {
            addCriterion("MODFIY_TIME not between", value1, value2, "modfiyTime");
            return (Criteria) this;
        }

        public Criteria andImageUrlsBackIsNull() {
            addCriterion("IMAGE_URLS_BACK is null");
            return (Criteria) this;
        }

        public Criteria andImageUrlsBackIsNotNull() {
            addCriterion("IMAGE_URLS_BACK is not null");
            return (Criteria) this;
        }

        public Criteria andImageUrlsBackEqualTo(String value) {
            addCriterion("IMAGE_URLS_BACK =", value, "imageUrlsBack");
            return (Criteria) this;
        }

        public Criteria andImageUrlsBackNotEqualTo(String value) {
            addCriterion("IMAGE_URLS_BACK <>", value, "imageUrlsBack");
            return (Criteria) this;
        }

        public Criteria andImageUrlsBackGreaterThan(String value) {
            addCriterion("IMAGE_URLS_BACK >", value, "imageUrlsBack");
            return (Criteria) this;
        }

        public Criteria andImageUrlsBackGreaterThanOrEqualTo(String value) {
            addCriterion("IMAGE_URLS_BACK >=", value, "imageUrlsBack");
            return (Criteria) this;
        }

        public Criteria andImageUrlsBackLessThan(String value) {
            addCriterion("IMAGE_URLS_BACK <", value, "imageUrlsBack");
            return (Criteria) this;
        }

        public Criteria andImageUrlsBackLessThanOrEqualTo(String value) {
            addCriterion("IMAGE_URLS_BACK <=", value, "imageUrlsBack");
            return (Criteria) this;
        }

        public Criteria andImageUrlsBackLike(String value) {
            addCriterion("IMAGE_URLS_BACK like", value, "imageUrlsBack");
            return (Criteria) this;
        }

        public Criteria andImageUrlsBackNotLike(String value) {
            addCriterion("IMAGE_URLS_BACK not like", value, "imageUrlsBack");
            return (Criteria) this;
        }

        public Criteria andImageUrlsBackIn(List<String> values) {
            addCriterion("IMAGE_URLS_BACK in", values, "imageUrlsBack");
            return (Criteria) this;
        }

        public Criteria andImageUrlsBackNotIn(List<String> values) {
            addCriterion("IMAGE_URLS_BACK not in", values, "imageUrlsBack");
            return (Criteria) this;
        }

        public Criteria andImageUrlsBackBetween(String value1, String value2) {
            addCriterion("IMAGE_URLS_BACK between", value1, value2, "imageUrlsBack");
            return (Criteria) this;
        }

        public Criteria andImageUrlsBackNotBetween(String value1, String value2) {
            addCriterion("IMAGE_URLS_BACK not between", value1, value2, "imageUrlsBack");
            return (Criteria) this;
        }

        public Criteria andThumbUrlsBackIsNull() {
            addCriterion("THUMB_URLS_BACK is null");
            return (Criteria) this;
        }

        public Criteria andThumbUrlsBackIsNotNull() {
            addCriterion("THUMB_URLS_BACK is not null");
            return (Criteria) this;
        }

        public Criteria andThumbUrlsBackEqualTo(String value) {
            addCriterion("THUMB_URLS_BACK =", value, "thumbUrlsBack");
            return (Criteria) this;
        }

        public Criteria andThumbUrlsBackNotEqualTo(String value) {
            addCriterion("THUMB_URLS_BACK <>", value, "thumbUrlsBack");
            return (Criteria) this;
        }

        public Criteria andThumbUrlsBackGreaterThan(String value) {
            addCriterion("THUMB_URLS_BACK >", value, "thumbUrlsBack");
            return (Criteria) this;
        }

        public Criteria andThumbUrlsBackGreaterThanOrEqualTo(String value) {
            addCriterion("THUMB_URLS_BACK >=", value, "thumbUrlsBack");
            return (Criteria) this;
        }

        public Criteria andThumbUrlsBackLessThan(String value) {
            addCriterion("THUMB_URLS_BACK <", value, "thumbUrlsBack");
            return (Criteria) this;
        }

        public Criteria andThumbUrlsBackLessThanOrEqualTo(String value) {
            addCriterion("THUMB_URLS_BACK <=", value, "thumbUrlsBack");
            return (Criteria) this;
        }

        public Criteria andThumbUrlsBackLike(String value) {
            addCriterion("THUMB_URLS_BACK like", value, "thumbUrlsBack");
            return (Criteria) this;
        }

        public Criteria andThumbUrlsBackNotLike(String value) {
            addCriterion("THUMB_URLS_BACK not like", value, "thumbUrlsBack");
            return (Criteria) this;
        }

        public Criteria andThumbUrlsBackIn(List<String> values) {
            addCriterion("THUMB_URLS_BACK in", values, "thumbUrlsBack");
            return (Criteria) this;
        }

        public Criteria andThumbUrlsBackNotIn(List<String> values) {
            addCriterion("THUMB_URLS_BACK not in", values, "thumbUrlsBack");
            return (Criteria) this;
        }

        public Criteria andThumbUrlsBackBetween(String value1, String value2) {
            addCriterion("THUMB_URLS_BACK between", value1, value2, "thumbUrlsBack");
            return (Criteria) this;
        }

        public Criteria andThumbUrlsBackNotBetween(String value1, String value2) {
            addCriterion("THUMB_URLS_BACK not between", value1, value2, "thumbUrlsBack");
            return (Criteria) this;
        }

        public Criteria andApplyYearIsNull() {
            addCriterion("APPLY_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andApplyYearIsNotNull() {
            addCriterion("APPLY_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andApplyYearEqualTo(String value) {
            addCriterion("APPLY_YEAR =", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearNotEqualTo(String value) {
            addCriterion("APPLY_YEAR <>", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearGreaterThan(String value) {
            addCriterion("APPLY_YEAR >", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_YEAR >=", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearLessThan(String value) {
            addCriterion("APPLY_YEAR <", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearLessThanOrEqualTo(String value) {
            addCriterion("APPLY_YEAR <=", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearLike(String value) {
            addCriterion("APPLY_YEAR like", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearNotLike(String value) {
            addCriterion("APPLY_YEAR not like", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearIn(List<String> values) {
            addCriterion("APPLY_YEAR in", values, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearNotIn(List<String> values) {
            addCriterion("APPLY_YEAR not in", values, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearBetween(String value1, String value2) {
            addCriterion("APPLY_YEAR between", value1, value2, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearNotBetween(String value1, String value2) {
            addCriterion("APPLY_YEAR not between", value1, value2, "applyYear");
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