package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class JsresDoctorGraduationInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JsresDoctorGraduationInfoExample() {
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

        public Criteria andInfoFlowIsNull() {
            addCriterion("INFO_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andInfoFlowIsNotNull() {
            addCriterion("INFO_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andInfoFlowEqualTo(String value) {
            addCriterion("INFO_FLOW =", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowNotEqualTo(String value) {
            addCriterion("INFO_FLOW <>", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowGreaterThan(String value) {
            addCriterion("INFO_FLOW >", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowGreaterThanOrEqualTo(String value) {
            addCriterion("INFO_FLOW >=", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowLessThan(String value) {
            addCriterion("INFO_FLOW <", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowLessThanOrEqualTo(String value) {
            addCriterion("INFO_FLOW <=", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowLike(String value) {
            addCriterion("INFO_FLOW like", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowNotLike(String value) {
            addCriterion("INFO_FLOW not like", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowIn(List<String> values) {
            addCriterion("INFO_FLOW in", values, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowNotIn(List<String> values) {
            addCriterion("INFO_FLOW not in", values, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowBetween(String value1, String value2) {
            addCriterion("INFO_FLOW between", value1, value2, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowNotBetween(String value1, String value2) {
            addCriterion("INFO_FLOW not between", value1, value2, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowIsNull() {
            addCriterion("APPLY_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApplyFlowIsNotNull() {
            addCriterion("APPLY_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApplyFlowEqualTo(String value) {
            addCriterion("APPLY_FLOW =", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowNotEqualTo(String value) {
            addCriterion("APPLY_FLOW <>", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowGreaterThan(String value) {
            addCriterion("APPLY_FLOW >", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_FLOW >=", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowLessThan(String value) {
            addCriterion("APPLY_FLOW <", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowLessThanOrEqualTo(String value) {
            addCriterion("APPLY_FLOW <=", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowLike(String value) {
            addCriterion("APPLY_FLOW like", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowNotLike(String value) {
            addCriterion("APPLY_FLOW not like", value, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowIn(List<String> values) {
            addCriterion("APPLY_FLOW in", values, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowNotIn(List<String> values) {
            addCriterion("APPLY_FLOW not in", values, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowBetween(String value1, String value2) {
            addCriterion("APPLY_FLOW between", value1, value2, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andApplyFlowNotBetween(String value1, String value2) {
            addCriterion("APPLY_FLOW not between", value1, value2, "applyFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowIsNull() {
            addCriterion("RECRUIT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowIsNotNull() {
            addCriterion("RECRUIT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowEqualTo(String value) {
            addCriterion("RECRUIT_FLOW =", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotEqualTo(String value) {
            addCriterion("RECRUIT_FLOW <>", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowGreaterThan(String value) {
            addCriterion("RECRUIT_FLOW >", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RECRUIT_FLOW >=", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowLessThan(String value) {
            addCriterion("RECRUIT_FLOW <", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowLessThanOrEqualTo(String value) {
            addCriterion("RECRUIT_FLOW <=", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowLike(String value) {
            addCriterion("RECRUIT_FLOW like", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotLike(String value) {
            addCriterion("RECRUIT_FLOW not like", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowIn(List<String> values) {
            addCriterion("RECRUIT_FLOW in", values, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotIn(List<String> values) {
            addCriterion("RECRUIT_FLOW not in", values, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowBetween(String value1, String value2) {
            addCriterion("RECRUIT_FLOW between", value1, value2, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotBetween(String value1, String value2) {
            addCriterion("RECRUIT_FLOW not between", value1, value2, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowIsNull() {
            addCriterion("ROTATION_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRotationFlowIsNotNull() {
            addCriterion("ROTATION_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRotationFlowEqualTo(String value) {
            addCriterion("ROTATION_FLOW =", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowNotEqualTo(String value) {
            addCriterion("ROTATION_FLOW <>", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowGreaterThan(String value) {
            addCriterion("ROTATION_FLOW >", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ROTATION_FLOW >=", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowLessThan(String value) {
            addCriterion("ROTATION_FLOW <", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowLessThanOrEqualTo(String value) {
            addCriterion("ROTATION_FLOW <=", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowLike(String value) {
            addCriterion("ROTATION_FLOW like", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowNotLike(String value) {
            addCriterion("ROTATION_FLOW not like", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowIn(List<String> values) {
            addCriterion("ROTATION_FLOW in", values, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowNotIn(List<String> values) {
            addCriterion("ROTATION_FLOW not in", values, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowBetween(String value1, String value2) {
            addCriterion("ROTATION_FLOW between", value1, value2, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowNotBetween(String value1, String value2) {
            addCriterion("ROTATION_FLOW not between", value1, value2, "rotationFlow");
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

        public Criteria andImageCountIsNull() {
            addCriterion("IMAGE_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andImageCountIsNotNull() {
            addCriterion("IMAGE_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andImageCountEqualTo(String value) {
            addCriterion("IMAGE_COUNT =", value, "imageCount");
            return (Criteria) this;
        }

        public Criteria andImageCountNotEqualTo(String value) {
            addCriterion("IMAGE_COUNT <>", value, "imageCount");
            return (Criteria) this;
        }

        public Criteria andImageCountGreaterThan(String value) {
            addCriterion("IMAGE_COUNT >", value, "imageCount");
            return (Criteria) this;
        }

        public Criteria andImageCountGreaterThanOrEqualTo(String value) {
            addCriterion("IMAGE_COUNT >=", value, "imageCount");
            return (Criteria) this;
        }

        public Criteria andImageCountLessThan(String value) {
            addCriterion("IMAGE_COUNT <", value, "imageCount");
            return (Criteria) this;
        }

        public Criteria andImageCountLessThanOrEqualTo(String value) {
            addCriterion("IMAGE_COUNT <=", value, "imageCount");
            return (Criteria) this;
        }

        public Criteria andImageCountLike(String value) {
            addCriterion("IMAGE_COUNT like", value, "imageCount");
            return (Criteria) this;
        }

        public Criteria andImageCountNotLike(String value) {
            addCriterion("IMAGE_COUNT not like", value, "imageCount");
            return (Criteria) this;
        }

        public Criteria andImageCountIn(List<String> values) {
            addCriterion("IMAGE_COUNT in", values, "imageCount");
            return (Criteria) this;
        }

        public Criteria andImageCountNotIn(List<String> values) {
            addCriterion("IMAGE_COUNT not in", values, "imageCount");
            return (Criteria) this;
        }

        public Criteria andImageCountBetween(String value1, String value2) {
            addCriterion("IMAGE_COUNT between", value1, value2, "imageCount");
            return (Criteria) this;
        }

        public Criteria andImageCountNotBetween(String value1, String value2) {
            addCriterion("IMAGE_COUNT not between", value1, value2, "imageCount");
            return (Criteria) this;
        }

        public Criteria andImageAllCountIsNull() {
            addCriterion("IMAGE_ALL_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andImageAllCountIsNotNull() {
            addCriterion("IMAGE_ALL_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andImageAllCountEqualTo(String value) {
            addCriterion("IMAGE_ALL_COUNT =", value, "imageAllCount");
            return (Criteria) this;
        }

        public Criteria andImageAllCountNotEqualTo(String value) {
            addCriterion("IMAGE_ALL_COUNT <>", value, "imageAllCount");
            return (Criteria) this;
        }

        public Criteria andImageAllCountGreaterThan(String value) {
            addCriterion("IMAGE_ALL_COUNT >", value, "imageAllCount");
            return (Criteria) this;
        }

        public Criteria andImageAllCountGreaterThanOrEqualTo(String value) {
            addCriterion("IMAGE_ALL_COUNT >=", value, "imageAllCount");
            return (Criteria) this;
        }

        public Criteria andImageAllCountLessThan(String value) {
            addCriterion("IMAGE_ALL_COUNT <", value, "imageAllCount");
            return (Criteria) this;
        }

        public Criteria andImageAllCountLessThanOrEqualTo(String value) {
            addCriterion("IMAGE_ALL_COUNT <=", value, "imageAllCount");
            return (Criteria) this;
        }

        public Criteria andImageAllCountLike(String value) {
            addCriterion("IMAGE_ALL_COUNT like", value, "imageAllCount");
            return (Criteria) this;
        }

        public Criteria andImageAllCountNotLike(String value) {
            addCriterion("IMAGE_ALL_COUNT not like", value, "imageAllCount");
            return (Criteria) this;
        }

        public Criteria andImageAllCountIn(List<String> values) {
            addCriterion("IMAGE_ALL_COUNT in", values, "imageAllCount");
            return (Criteria) this;
        }

        public Criteria andImageAllCountNotIn(List<String> values) {
            addCriterion("IMAGE_ALL_COUNT not in", values, "imageAllCount");
            return (Criteria) this;
        }

        public Criteria andImageAllCountBetween(String value1, String value2) {
            addCriterion("IMAGE_ALL_COUNT between", value1, value2, "imageAllCount");
            return (Criteria) this;
        }

        public Criteria andImageAllCountNotBetween(String value1, String value2) {
            addCriterion("IMAGE_ALL_COUNT not between", value1, value2, "imageAllCount");
            return (Criteria) this;
        }

        public Criteria andAvgImageCountIsNull() {
            addCriterion("AVG_IMAGE_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andAvgImageCountIsNotNull() {
            addCriterion("AVG_IMAGE_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andAvgImageCountEqualTo(String value) {
            addCriterion("AVG_IMAGE_COUNT =", value, "avgImageCount");
            return (Criteria) this;
        }

        public Criteria andAvgImageCountNotEqualTo(String value) {
            addCriterion("AVG_IMAGE_COUNT <>", value, "avgImageCount");
            return (Criteria) this;
        }

        public Criteria andAvgImageCountGreaterThan(String value) {
            addCriterion("AVG_IMAGE_COUNT >", value, "avgImageCount");
            return (Criteria) this;
        }

        public Criteria andAvgImageCountGreaterThanOrEqualTo(String value) {
            addCriterion("AVG_IMAGE_COUNT >=", value, "avgImageCount");
            return (Criteria) this;
        }

        public Criteria andAvgImageCountLessThan(String value) {
            addCriterion("AVG_IMAGE_COUNT <", value, "avgImageCount");
            return (Criteria) this;
        }

        public Criteria andAvgImageCountLessThanOrEqualTo(String value) {
            addCriterion("AVG_IMAGE_COUNT <=", value, "avgImageCount");
            return (Criteria) this;
        }

        public Criteria andAvgImageCountLike(String value) {
            addCriterion("AVG_IMAGE_COUNT like", value, "avgImageCount");
            return (Criteria) this;
        }

        public Criteria andAvgImageCountNotLike(String value) {
            addCriterion("AVG_IMAGE_COUNT not like", value, "avgImageCount");
            return (Criteria) this;
        }

        public Criteria andAvgImageCountIn(List<String> values) {
            addCriterion("AVG_IMAGE_COUNT in", values, "avgImageCount");
            return (Criteria) this;
        }

        public Criteria andAvgImageCountNotIn(List<String> values) {
            addCriterion("AVG_IMAGE_COUNT not in", values, "avgImageCount");
            return (Criteria) this;
        }

        public Criteria andAvgImageCountBetween(String value1, String value2) {
            addCriterion("AVG_IMAGE_COUNT between", value1, value2, "avgImageCount");
            return (Criteria) this;
        }

        public Criteria andAvgImageCountNotBetween(String value1, String value2) {
            addCriterion("AVG_IMAGE_COUNT not between", value1, value2, "avgImageCount");
            return (Criteria) this;
        }

        public Criteria andDataCountIsNull() {
            addCriterion("DATA_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andDataCountIsNotNull() {
            addCriterion("DATA_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andDataCountEqualTo(String value) {
            addCriterion("DATA_COUNT =", value, "dataCount");
            return (Criteria) this;
        }

        public Criteria andDataCountNotEqualTo(String value) {
            addCriterion("DATA_COUNT <>", value, "dataCount");
            return (Criteria) this;
        }

        public Criteria andDataCountGreaterThan(String value) {
            addCriterion("DATA_COUNT >", value, "dataCount");
            return (Criteria) this;
        }

        public Criteria andDataCountGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_COUNT >=", value, "dataCount");
            return (Criteria) this;
        }

        public Criteria andDataCountLessThan(String value) {
            addCriterion("DATA_COUNT <", value, "dataCount");
            return (Criteria) this;
        }

        public Criteria andDataCountLessThanOrEqualTo(String value) {
            addCriterion("DATA_COUNT <=", value, "dataCount");
            return (Criteria) this;
        }

        public Criteria andDataCountLike(String value) {
            addCriterion("DATA_COUNT like", value, "dataCount");
            return (Criteria) this;
        }

        public Criteria andDataCountNotLike(String value) {
            addCriterion("DATA_COUNT not like", value, "dataCount");
            return (Criteria) this;
        }

        public Criteria andDataCountIn(List<String> values) {
            addCriterion("DATA_COUNT in", values, "dataCount");
            return (Criteria) this;
        }

        public Criteria andDataCountNotIn(List<String> values) {
            addCriterion("DATA_COUNT not in", values, "dataCount");
            return (Criteria) this;
        }

        public Criteria andDataCountBetween(String value1, String value2) {
            addCriterion("DATA_COUNT between", value1, value2, "dataCount");
            return (Criteria) this;
        }

        public Criteria andDataCountNotBetween(String value1, String value2) {
            addCriterion("DATA_COUNT not between", value1, value2, "dataCount");
            return (Criteria) this;
        }

        public Criteria andDataAllCountIsNull() {
            addCriterion("DATA_ALL_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andDataAllCountIsNotNull() {
            addCriterion("DATA_ALL_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andDataAllCountEqualTo(String value) {
            addCriterion("DATA_ALL_COUNT =", value, "dataAllCount");
            return (Criteria) this;
        }

        public Criteria andDataAllCountNotEqualTo(String value) {
            addCriterion("DATA_ALL_COUNT <>", value, "dataAllCount");
            return (Criteria) this;
        }

        public Criteria andDataAllCountGreaterThan(String value) {
            addCriterion("DATA_ALL_COUNT >", value, "dataAllCount");
            return (Criteria) this;
        }

        public Criteria andDataAllCountGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_ALL_COUNT >=", value, "dataAllCount");
            return (Criteria) this;
        }

        public Criteria andDataAllCountLessThan(String value) {
            addCriterion("DATA_ALL_COUNT <", value, "dataAllCount");
            return (Criteria) this;
        }

        public Criteria andDataAllCountLessThanOrEqualTo(String value) {
            addCriterion("DATA_ALL_COUNT <=", value, "dataAllCount");
            return (Criteria) this;
        }

        public Criteria andDataAllCountLike(String value) {
            addCriterion("DATA_ALL_COUNT like", value, "dataAllCount");
            return (Criteria) this;
        }

        public Criteria andDataAllCountNotLike(String value) {
            addCriterion("DATA_ALL_COUNT not like", value, "dataAllCount");
            return (Criteria) this;
        }

        public Criteria andDataAllCountIn(List<String> values) {
            addCriterion("DATA_ALL_COUNT in", values, "dataAllCount");
            return (Criteria) this;
        }

        public Criteria andDataAllCountNotIn(List<String> values) {
            addCriterion("DATA_ALL_COUNT not in", values, "dataAllCount");
            return (Criteria) this;
        }

        public Criteria andDataAllCountBetween(String value1, String value2) {
            addCriterion("DATA_ALL_COUNT between", value1, value2, "dataAllCount");
            return (Criteria) this;
        }

        public Criteria andDataAllCountNotBetween(String value1, String value2) {
            addCriterion("DATA_ALL_COUNT not between", value1, value2, "dataAllCount");
            return (Criteria) this;
        }

        public Criteria andAvgDataCountIsNull() {
            addCriterion("AVG_DATA_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andAvgDataCountIsNotNull() {
            addCriterion("AVG_DATA_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andAvgDataCountEqualTo(String value) {
            addCriterion("AVG_DATA_COUNT =", value, "avgDataCount");
            return (Criteria) this;
        }

        public Criteria andAvgDataCountNotEqualTo(String value) {
            addCriterion("AVG_DATA_COUNT <>", value, "avgDataCount");
            return (Criteria) this;
        }

        public Criteria andAvgDataCountGreaterThan(String value) {
            addCriterion("AVG_DATA_COUNT >", value, "avgDataCount");
            return (Criteria) this;
        }

        public Criteria andAvgDataCountGreaterThanOrEqualTo(String value) {
            addCriterion("AVG_DATA_COUNT >=", value, "avgDataCount");
            return (Criteria) this;
        }

        public Criteria andAvgDataCountLessThan(String value) {
            addCriterion("AVG_DATA_COUNT <", value, "avgDataCount");
            return (Criteria) this;
        }

        public Criteria andAvgDataCountLessThanOrEqualTo(String value) {
            addCriterion("AVG_DATA_COUNT <=", value, "avgDataCount");
            return (Criteria) this;
        }

        public Criteria andAvgDataCountLike(String value) {
            addCriterion("AVG_DATA_COUNT like", value, "avgDataCount");
            return (Criteria) this;
        }

        public Criteria andAvgDataCountNotLike(String value) {
            addCriterion("AVG_DATA_COUNT not like", value, "avgDataCount");
            return (Criteria) this;
        }

        public Criteria andAvgDataCountIn(List<String> values) {
            addCriterion("AVG_DATA_COUNT in", values, "avgDataCount");
            return (Criteria) this;
        }

        public Criteria andAvgDataCountNotIn(List<String> values) {
            addCriterion("AVG_DATA_COUNT not in", values, "avgDataCount");
            return (Criteria) this;
        }

        public Criteria andAvgDataCountBetween(String value1, String value2) {
            addCriterion("AVG_DATA_COUNT between", value1, value2, "avgDataCount");
            return (Criteria) this;
        }

        public Criteria andAvgDataCountNotBetween(String value1, String value2) {
            addCriterion("AVG_DATA_COUNT not between", value1, value2, "avgDataCount");
            return (Criteria) this;
        }

        public Criteria andDataTimeIsNull() {
            addCriterion("DATA_TIME is null");
            return (Criteria) this;
        }

        public Criteria andDataTimeIsNotNull() {
            addCriterion("DATA_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andDataTimeEqualTo(String value) {
            addCriterion("DATA_TIME =", value, "dataTime");
            return (Criteria) this;
        }

        public Criteria andDataTimeNotEqualTo(String value) {
            addCriterion("DATA_TIME <>", value, "dataTime");
            return (Criteria) this;
        }

        public Criteria andDataTimeGreaterThan(String value) {
            addCriterion("DATA_TIME >", value, "dataTime");
            return (Criteria) this;
        }

        public Criteria andDataTimeGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_TIME >=", value, "dataTime");
            return (Criteria) this;
        }

        public Criteria andDataTimeLessThan(String value) {
            addCriterion("DATA_TIME <", value, "dataTime");
            return (Criteria) this;
        }

        public Criteria andDataTimeLessThanOrEqualTo(String value) {
            addCriterion("DATA_TIME <=", value, "dataTime");
            return (Criteria) this;
        }

        public Criteria andDataTimeLike(String value) {
            addCriterion("DATA_TIME like", value, "dataTime");
            return (Criteria) this;
        }

        public Criteria andDataTimeNotLike(String value) {
            addCriterion("DATA_TIME not like", value, "dataTime");
            return (Criteria) this;
        }

        public Criteria andDataTimeIn(List<String> values) {
            addCriterion("DATA_TIME in", values, "dataTime");
            return (Criteria) this;
        }

        public Criteria andDataTimeNotIn(List<String> values) {
            addCriterion("DATA_TIME not in", values, "dataTime");
            return (Criteria) this;
        }

        public Criteria andDataTimeBetween(String value1, String value2) {
            addCriterion("DATA_TIME between", value1, value2, "dataTime");
            return (Criteria) this;
        }

        public Criteria andDataTimeNotBetween(String value1, String value2) {
            addCriterion("DATA_TIME not between", value1, value2, "dataTime");
            return (Criteria) this;
        }

        public Criteria andPubFileIsNull() {
            addCriterion("PUB_FILE is null");
            return (Criteria) this;
        }

        public Criteria andPubFileIsNotNull() {
            addCriterion("PUB_FILE is not null");
            return (Criteria) this;
        }

        public Criteria andPubFileEqualTo(String value) {
            addCriterion("PUB_FILE =", value, "pubFile");
            return (Criteria) this;
        }

        public Criteria andPubFileNotEqualTo(String value) {
            addCriterion("PUB_FILE <>", value, "pubFile");
            return (Criteria) this;
        }

        public Criteria andPubFileGreaterThan(String value) {
            addCriterion("PUB_FILE >", value, "pubFile");
            return (Criteria) this;
        }

        public Criteria andPubFileGreaterThanOrEqualTo(String value) {
            addCriterion("PUB_FILE >=", value, "pubFile");
            return (Criteria) this;
        }

        public Criteria andPubFileLessThan(String value) {
            addCriterion("PUB_FILE <", value, "pubFile");
            return (Criteria) this;
        }

        public Criteria andPubFileLessThanOrEqualTo(String value) {
            addCriterion("PUB_FILE <=", value, "pubFile");
            return (Criteria) this;
        }

        public Criteria andPubFileLike(String value) {
            addCriterion("PUB_FILE like", value, "pubFile");
            return (Criteria) this;
        }

        public Criteria andPubFileNotLike(String value) {
            addCriterion("PUB_FILE not like", value, "pubFile");
            return (Criteria) this;
        }

        public Criteria andPubFileIn(List<String> values) {
            addCriterion("PUB_FILE in", values, "pubFile");
            return (Criteria) this;
        }

        public Criteria andPubFileNotIn(List<String> values) {
            addCriterion("PUB_FILE not in", values, "pubFile");
            return (Criteria) this;
        }

        public Criteria andPubFileBetween(String value1, String value2) {
            addCriterion("PUB_FILE between", value1, value2, "pubFile");
            return (Criteria) this;
        }

        public Criteria andPubFileNotBetween(String value1, String value2) {
            addCriterion("PUB_FILE not between", value1, value2, "pubFile");
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

        public Criteria andMidifyUserFlowIsNull() {
            addCriterion("MIDIFY_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowIsNotNull() {
            addCriterion("MIDIFY_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowEqualTo(String value) {
            addCriterion("MIDIFY_USER_FLOW =", value, "midifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowNotEqualTo(String value) {
            addCriterion("MIDIFY_USER_FLOW <>", value, "midifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowGreaterThan(String value) {
            addCriterion("MIDIFY_USER_FLOW >", value, "midifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("MIDIFY_USER_FLOW >=", value, "midifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowLessThan(String value) {
            addCriterion("MIDIFY_USER_FLOW <", value, "midifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowLessThanOrEqualTo(String value) {
            addCriterion("MIDIFY_USER_FLOW <=", value, "midifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowLike(String value) {
            addCriterion("MIDIFY_USER_FLOW like", value, "midifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowNotLike(String value) {
            addCriterion("MIDIFY_USER_FLOW not like", value, "midifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowIn(List<String> values) {
            addCriterion("MIDIFY_USER_FLOW in", values, "midifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowNotIn(List<String> values) {
            addCriterion("MIDIFY_USER_FLOW not in", values, "midifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowBetween(String value1, String value2) {
            addCriterion("MIDIFY_USER_FLOW between", value1, value2, "midifyUserFlow");
            return (Criteria) this;
        }

        public Criteria andMidifyUserFlowNotBetween(String value1, String value2) {
            addCriterion("MIDIFY_USER_FLOW not between", value1, value2, "midifyUserFlow");
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