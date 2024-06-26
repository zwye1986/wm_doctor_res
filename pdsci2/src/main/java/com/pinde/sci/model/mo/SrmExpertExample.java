package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class SrmExpertExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SrmExpertExample() {
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

        public Criteria andGraduateSchoolIsNull() {
            addCriterion("GRADUATE_SCHOOL is null");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolIsNotNull() {
            addCriterion("GRADUATE_SCHOOL is not null");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolEqualTo(String value) {
            addCriterion("GRADUATE_SCHOOL =", value, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolNotEqualTo(String value) {
            addCriterion("GRADUATE_SCHOOL <>", value, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolGreaterThan(String value) {
            addCriterion("GRADUATE_SCHOOL >", value, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATE_SCHOOL >=", value, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolLessThan(String value) {
            addCriterion("GRADUATE_SCHOOL <", value, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolLessThanOrEqualTo(String value) {
            addCriterion("GRADUATE_SCHOOL <=", value, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolLike(String value) {
            addCriterion("GRADUATE_SCHOOL like", value, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolNotLike(String value) {
            addCriterion("GRADUATE_SCHOOL not like", value, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolIn(List<String> values) {
            addCriterion("GRADUATE_SCHOOL in", values, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolNotIn(List<String> values) {
            addCriterion("GRADUATE_SCHOOL not in", values, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolBetween(String value1, String value2) {
            addCriterion("GRADUATE_SCHOOL between", value1, value2, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andGraduateSchoolNotBetween(String value1, String value2) {
            addCriterion("GRADUATE_SCHOOL not between", value1, value2, "graduateSchool");
            return (Criteria) this;
        }

        public Criteria andBeginWorkYearIsNull() {
            addCriterion("BEGIN_WORK_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andBeginWorkYearIsNotNull() {
            addCriterion("BEGIN_WORK_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andBeginWorkYearEqualTo(String value) {
            addCriterion("BEGIN_WORK_YEAR =", value, "beginWorkYear");
            return (Criteria) this;
        }

        public Criteria andBeginWorkYearNotEqualTo(String value) {
            addCriterion("BEGIN_WORK_YEAR <>", value, "beginWorkYear");
            return (Criteria) this;
        }

        public Criteria andBeginWorkYearGreaterThan(String value) {
            addCriterion("BEGIN_WORK_YEAR >", value, "beginWorkYear");
            return (Criteria) this;
        }

        public Criteria andBeginWorkYearGreaterThanOrEqualTo(String value) {
            addCriterion("BEGIN_WORK_YEAR >=", value, "beginWorkYear");
            return (Criteria) this;
        }

        public Criteria andBeginWorkYearLessThan(String value) {
            addCriterion("BEGIN_WORK_YEAR <", value, "beginWorkYear");
            return (Criteria) this;
        }

        public Criteria andBeginWorkYearLessThanOrEqualTo(String value) {
            addCriterion("BEGIN_WORK_YEAR <=", value, "beginWorkYear");
            return (Criteria) this;
        }

        public Criteria andBeginWorkYearLike(String value) {
            addCriterion("BEGIN_WORK_YEAR like", value, "beginWorkYear");
            return (Criteria) this;
        }

        public Criteria andBeginWorkYearNotLike(String value) {
            addCriterion("BEGIN_WORK_YEAR not like", value, "beginWorkYear");
            return (Criteria) this;
        }

        public Criteria andBeginWorkYearIn(List<String> values) {
            addCriterion("BEGIN_WORK_YEAR in", values, "beginWorkYear");
            return (Criteria) this;
        }

        public Criteria andBeginWorkYearNotIn(List<String> values) {
            addCriterion("BEGIN_WORK_YEAR not in", values, "beginWorkYear");
            return (Criteria) this;
        }

        public Criteria andBeginWorkYearBetween(String value1, String value2) {
            addCriterion("BEGIN_WORK_YEAR between", value1, value2, "beginWorkYear");
            return (Criteria) this;
        }

        public Criteria andBeginWorkYearNotBetween(String value1, String value2) {
            addCriterion("BEGIN_WORK_YEAR not between", value1, value2, "beginWorkYear");
            return (Criteria) this;
        }

        public Criteria andFaxIsNull() {
            addCriterion("FAX is null");
            return (Criteria) this;
        }

        public Criteria andFaxIsNotNull() {
            addCriterion("FAX is not null");
            return (Criteria) this;
        }

        public Criteria andFaxEqualTo(String value) {
            addCriterion("FAX =", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotEqualTo(String value) {
            addCriterion("FAX <>", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxGreaterThan(String value) {
            addCriterion("FAX >", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxGreaterThanOrEqualTo(String value) {
            addCriterion("FAX >=", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLessThan(String value) {
            addCriterion("FAX <", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLessThanOrEqualTo(String value) {
            addCriterion("FAX <=", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLike(String value) {
            addCriterion("FAX like", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotLike(String value) {
            addCriterion("FAX not like", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxIn(List<String> values) {
            addCriterion("FAX in", values, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotIn(List<String> values) {
            addCriterion("FAX not in", values, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxBetween(String value1, String value2) {
            addCriterion("FAX between", value1, value2, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotBetween(String value1, String value2) {
            addCriterion("FAX not between", value1, value2, "fax");
            return (Criteria) this;
        }

        public Criteria andMajorIdIsNull() {
            addCriterion("MAJOR_ID is null");
            return (Criteria) this;
        }

        public Criteria andMajorIdIsNotNull() {
            addCriterion("MAJOR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMajorIdEqualTo(String value) {
            addCriterion("MAJOR_ID =", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotEqualTo(String value) {
            addCriterion("MAJOR_ID <>", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdGreaterThan(String value) {
            addCriterion("MAJOR_ID >", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdGreaterThanOrEqualTo(String value) {
            addCriterion("MAJOR_ID >=", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdLessThan(String value) {
            addCriterion("MAJOR_ID <", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdLessThanOrEqualTo(String value) {
            addCriterion("MAJOR_ID <=", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdLike(String value) {
            addCriterion("MAJOR_ID like", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotLike(String value) {
            addCriterion("MAJOR_ID not like", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdIn(List<String> values) {
            addCriterion("MAJOR_ID in", values, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotIn(List<String> values) {
            addCriterion("MAJOR_ID not in", values, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdBetween(String value1, String value2) {
            addCriterion("MAJOR_ID between", value1, value2, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotBetween(String value1, String value2) {
            addCriterion("MAJOR_ID not between", value1, value2, "majorId");
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

        public Criteria andEducationIsNull() {
            addCriterion("EDUCATION is null");
            return (Criteria) this;
        }

        public Criteria andEducationIsNotNull() {
            addCriterion("EDUCATION is not null");
            return (Criteria) this;
        }

        public Criteria andEducationEqualTo(String value) {
            addCriterion("EDUCATION =", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotEqualTo(String value) {
            addCriterion("EDUCATION <>", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThan(String value) {
            addCriterion("EDUCATION >", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThanOrEqualTo(String value) {
            addCriterion("EDUCATION >=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThan(String value) {
            addCriterion("EDUCATION <", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThanOrEqualTo(String value) {
            addCriterion("EDUCATION <=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLike(String value) {
            addCriterion("EDUCATION like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotLike(String value) {
            addCriterion("EDUCATION not like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationIn(List<String> values) {
            addCriterion("EDUCATION in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotIn(List<String> values) {
            addCriterion("EDUCATION not in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationBetween(String value1, String value2) {
            addCriterion("EDUCATION between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotBetween(String value1, String value2) {
            addCriterion("EDUCATION not between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andPostIsNull() {
            addCriterion("POST is null");
            return (Criteria) this;
        }

        public Criteria andPostIsNotNull() {
            addCriterion("POST is not null");
            return (Criteria) this;
        }

        public Criteria andPostEqualTo(String value) {
            addCriterion("POST =", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostNotEqualTo(String value) {
            addCriterion("POST <>", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostGreaterThan(String value) {
            addCriterion("POST >", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostGreaterThanOrEqualTo(String value) {
            addCriterion("POST >=", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostLessThan(String value) {
            addCriterion("POST <", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostLessThanOrEqualTo(String value) {
            addCriterion("POST <=", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostLike(String value) {
            addCriterion("POST like", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostNotLike(String value) {
            addCriterion("POST not like", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostIn(List<String> values) {
            addCriterion("POST in", values, "post");
            return (Criteria) this;
        }

        public Criteria andPostNotIn(List<String> values) {
            addCriterion("POST not in", values, "post");
            return (Criteria) this;
        }

        public Criteria andPostBetween(String value1, String value2) {
            addCriterion("POST between", value1, value2, "post");
            return (Criteria) this;
        }

        public Criteria andPostNotBetween(String value1, String value2) {
            addCriterion("POST not between", value1, value2, "post");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("ADDRESS =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("ADDRESS <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("ADDRESS >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ADDRESS >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("ADDRESS <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("ADDRESS <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("ADDRESS like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("ADDRESS not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("ADDRESS in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("ADDRESS not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("ADDRESS between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("ADDRESS not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andPostcodeIsNull() {
            addCriterion("POSTCODE is null");
            return (Criteria) this;
        }

        public Criteria andPostcodeIsNotNull() {
            addCriterion("POSTCODE is not null");
            return (Criteria) this;
        }

        public Criteria andPostcodeEqualTo(String value) {
            addCriterion("POSTCODE =", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotEqualTo(String value) {
            addCriterion("POSTCODE <>", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeGreaterThan(String value) {
            addCriterion("POSTCODE >", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeGreaterThanOrEqualTo(String value) {
            addCriterion("POSTCODE >=", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLessThan(String value) {
            addCriterion("POSTCODE <", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLessThanOrEqualTo(String value) {
            addCriterion("POSTCODE <=", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLike(String value) {
            addCriterion("POSTCODE like", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotLike(String value) {
            addCriterion("POSTCODE not like", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeIn(List<String> values) {
            addCriterion("POSTCODE in", values, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotIn(List<String> values) {
            addCriterion("POSTCODE not in", values, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeBetween(String value1, String value2) {
            addCriterion("POSTCODE between", value1, value2, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotBetween(String value1, String value2) {
            addCriterion("POSTCODE not between", value1, value2, "postcode");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("TYPE like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("TYPE not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("TYPE not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andAwardIsNull() {
            addCriterion("AWARD is null");
            return (Criteria) this;
        }

        public Criteria andAwardIsNotNull() {
            addCriterion("AWARD is not null");
            return (Criteria) this;
        }

        public Criteria andAwardEqualTo(String value) {
            addCriterion("AWARD =", value, "award");
            return (Criteria) this;
        }

        public Criteria andAwardNotEqualTo(String value) {
            addCriterion("AWARD <>", value, "award");
            return (Criteria) this;
        }

        public Criteria andAwardGreaterThan(String value) {
            addCriterion("AWARD >", value, "award");
            return (Criteria) this;
        }

        public Criteria andAwardGreaterThanOrEqualTo(String value) {
            addCriterion("AWARD >=", value, "award");
            return (Criteria) this;
        }

        public Criteria andAwardLessThan(String value) {
            addCriterion("AWARD <", value, "award");
            return (Criteria) this;
        }

        public Criteria andAwardLessThanOrEqualTo(String value) {
            addCriterion("AWARD <=", value, "award");
            return (Criteria) this;
        }

        public Criteria andAwardLike(String value) {
            addCriterion("AWARD like", value, "award");
            return (Criteria) this;
        }

        public Criteria andAwardNotLike(String value) {
            addCriterion("AWARD not like", value, "award");
            return (Criteria) this;
        }

        public Criteria andAwardIn(List<String> values) {
            addCriterion("AWARD in", values, "award");
            return (Criteria) this;
        }

        public Criteria andAwardNotIn(List<String> values) {
            addCriterion("AWARD not in", values, "award");
            return (Criteria) this;
        }

        public Criteria andAwardBetween(String value1, String value2) {
            addCriterion("AWARD between", value1, value2, "award");
            return (Criteria) this;
        }

        public Criteria andAwardNotBetween(String value1, String value2) {
            addCriterion("AWARD not between", value1, value2, "award");
            return (Criteria) this;
        }

        public Criteria andLaborPayIdIsNull() {
            addCriterion("LABOR_PAY_ID is null");
            return (Criteria) this;
        }

        public Criteria andLaborPayIdIsNotNull() {
            addCriterion("LABOR_PAY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLaborPayIdEqualTo(String value) {
            addCriterion("LABOR_PAY_ID =", value, "laborPayId");
            return (Criteria) this;
        }

        public Criteria andLaborPayIdNotEqualTo(String value) {
            addCriterion("LABOR_PAY_ID <>", value, "laborPayId");
            return (Criteria) this;
        }

        public Criteria andLaborPayIdGreaterThan(String value) {
            addCriterion("LABOR_PAY_ID >", value, "laborPayId");
            return (Criteria) this;
        }

        public Criteria andLaborPayIdGreaterThanOrEqualTo(String value) {
            addCriterion("LABOR_PAY_ID >=", value, "laborPayId");
            return (Criteria) this;
        }

        public Criteria andLaborPayIdLessThan(String value) {
            addCriterion("LABOR_PAY_ID <", value, "laborPayId");
            return (Criteria) this;
        }

        public Criteria andLaborPayIdLessThanOrEqualTo(String value) {
            addCriterion("LABOR_PAY_ID <=", value, "laborPayId");
            return (Criteria) this;
        }

        public Criteria andLaborPayIdLike(String value) {
            addCriterion("LABOR_PAY_ID like", value, "laborPayId");
            return (Criteria) this;
        }

        public Criteria andLaborPayIdNotLike(String value) {
            addCriterion("LABOR_PAY_ID not like", value, "laborPayId");
            return (Criteria) this;
        }

        public Criteria andLaborPayIdIn(List<String> values) {
            addCriterion("LABOR_PAY_ID in", values, "laborPayId");
            return (Criteria) this;
        }

        public Criteria andLaborPayIdNotIn(List<String> values) {
            addCriterion("LABOR_PAY_ID not in", values, "laborPayId");
            return (Criteria) this;
        }

        public Criteria andLaborPayIdBetween(String value1, String value2) {
            addCriterion("LABOR_PAY_ID between", value1, value2, "laborPayId");
            return (Criteria) this;
        }

        public Criteria andLaborPayIdNotBetween(String value1, String value2) {
            addCriterion("LABOR_PAY_ID not between", value1, value2, "laborPayId");
            return (Criteria) this;
        }

        public Criteria andLaborPayNameIsNull() {
            addCriterion("LABOR_PAY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLaborPayNameIsNotNull() {
            addCriterion("LABOR_PAY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLaborPayNameEqualTo(String value) {
            addCriterion("LABOR_PAY_NAME =", value, "laborPayName");
            return (Criteria) this;
        }

        public Criteria andLaborPayNameNotEqualTo(String value) {
            addCriterion("LABOR_PAY_NAME <>", value, "laborPayName");
            return (Criteria) this;
        }

        public Criteria andLaborPayNameGreaterThan(String value) {
            addCriterion("LABOR_PAY_NAME >", value, "laborPayName");
            return (Criteria) this;
        }

        public Criteria andLaborPayNameGreaterThanOrEqualTo(String value) {
            addCriterion("LABOR_PAY_NAME >=", value, "laborPayName");
            return (Criteria) this;
        }

        public Criteria andLaborPayNameLessThan(String value) {
            addCriterion("LABOR_PAY_NAME <", value, "laborPayName");
            return (Criteria) this;
        }

        public Criteria andLaborPayNameLessThanOrEqualTo(String value) {
            addCriterion("LABOR_PAY_NAME <=", value, "laborPayName");
            return (Criteria) this;
        }

        public Criteria andLaborPayNameLike(String value) {
            addCriterion("LABOR_PAY_NAME like", value, "laborPayName");
            return (Criteria) this;
        }

        public Criteria andLaborPayNameNotLike(String value) {
            addCriterion("LABOR_PAY_NAME not like", value, "laborPayName");
            return (Criteria) this;
        }

        public Criteria andLaborPayNameIn(List<String> values) {
            addCriterion("LABOR_PAY_NAME in", values, "laborPayName");
            return (Criteria) this;
        }

        public Criteria andLaborPayNameNotIn(List<String> values) {
            addCriterion("LABOR_PAY_NAME not in", values, "laborPayName");
            return (Criteria) this;
        }

        public Criteria andLaborPayNameBetween(String value1, String value2) {
            addCriterion("LABOR_PAY_NAME between", value1, value2, "laborPayName");
            return (Criteria) this;
        }

        public Criteria andLaborPayNameNotBetween(String value1, String value2) {
            addCriterion("LABOR_PAY_NAME not between", value1, value2, "laborPayName");
            return (Criteria) this;
        }

        public Criteria andAccountBankIsNull() {
            addCriterion("ACCOUNT_BANK is null");
            return (Criteria) this;
        }

        public Criteria andAccountBankIsNotNull() {
            addCriterion("ACCOUNT_BANK is not null");
            return (Criteria) this;
        }

        public Criteria andAccountBankEqualTo(String value) {
            addCriterion("ACCOUNT_BANK =", value, "accountBank");
            return (Criteria) this;
        }

        public Criteria andAccountBankNotEqualTo(String value) {
            addCriterion("ACCOUNT_BANK <>", value, "accountBank");
            return (Criteria) this;
        }

        public Criteria andAccountBankGreaterThan(String value) {
            addCriterion("ACCOUNT_BANK >", value, "accountBank");
            return (Criteria) this;
        }

        public Criteria andAccountBankGreaterThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_BANK >=", value, "accountBank");
            return (Criteria) this;
        }

        public Criteria andAccountBankLessThan(String value) {
            addCriterion("ACCOUNT_BANK <", value, "accountBank");
            return (Criteria) this;
        }

        public Criteria andAccountBankLessThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_BANK <=", value, "accountBank");
            return (Criteria) this;
        }

        public Criteria andAccountBankLike(String value) {
            addCriterion("ACCOUNT_BANK like", value, "accountBank");
            return (Criteria) this;
        }

        public Criteria andAccountBankNotLike(String value) {
            addCriterion("ACCOUNT_BANK not like", value, "accountBank");
            return (Criteria) this;
        }

        public Criteria andAccountBankIn(List<String> values) {
            addCriterion("ACCOUNT_BANK in", values, "accountBank");
            return (Criteria) this;
        }

        public Criteria andAccountBankNotIn(List<String> values) {
            addCriterion("ACCOUNT_BANK not in", values, "accountBank");
            return (Criteria) this;
        }

        public Criteria andAccountBankBetween(String value1, String value2) {
            addCriterion("ACCOUNT_BANK between", value1, value2, "accountBank");
            return (Criteria) this;
        }

        public Criteria andAccountBankNotBetween(String value1, String value2) {
            addCriterion("ACCOUNT_BANK not between", value1, value2, "accountBank");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNull() {
            addCriterion("ACCOUNT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNotNull() {
            addCriterion("ACCOUNT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNameEqualTo(String value) {
            addCriterion("ACCOUNT_NAME =", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotEqualTo(String value) {
            addCriterion("ACCOUNT_NAME <>", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThan(String value) {
            addCriterion("ACCOUNT_NAME >", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_NAME >=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThan(String value) {
            addCriterion("ACCOUNT_NAME <", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_NAME <=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLike(String value) {
            addCriterion("ACCOUNT_NAME like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotLike(String value) {
            addCriterion("ACCOUNT_NAME not like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameIn(List<String> values) {
            addCriterion("ACCOUNT_NAME in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotIn(List<String> values) {
            addCriterion("ACCOUNT_NAME not in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameBetween(String value1, String value2) {
            addCriterion("ACCOUNT_NAME between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotBetween(String value1, String value2) {
            addCriterion("ACCOUNT_NAME not between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNoIsNull() {
            addCriterion("ACCOUNT_NO is null");
            return (Criteria) this;
        }

        public Criteria andAccountNoIsNotNull() {
            addCriterion("ACCOUNT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNoEqualTo(String value) {
            addCriterion("ACCOUNT_NO =", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotEqualTo(String value) {
            addCriterion("ACCOUNT_NO <>", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoGreaterThan(String value) {
            addCriterion("ACCOUNT_NO >", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_NO >=", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLessThan(String value) {
            addCriterion("ACCOUNT_NO <", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLessThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_NO <=", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLike(String value) {
            addCriterion("ACCOUNT_NO like", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotLike(String value) {
            addCriterion("ACCOUNT_NO not like", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoIn(List<String> values) {
            addCriterion("ACCOUNT_NO in", values, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotIn(List<String> values) {
            addCriterion("ACCOUNT_NO not in", values, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoBetween(String value1, String value2) {
            addCriterion("ACCOUNT_NO between", value1, value2, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotBetween(String value1, String value2) {
            addCriterion("ACCOUNT_NO not between", value1, value2, "accountNo");
            return (Criteria) this;
        }

        public Criteria andRecipientIsNull() {
            addCriterion("RECIPIENT is null");
            return (Criteria) this;
        }

        public Criteria andRecipientIsNotNull() {
            addCriterion("RECIPIENT is not null");
            return (Criteria) this;
        }

        public Criteria andRecipientEqualTo(String value) {
            addCriterion("RECIPIENT =", value, "recipient");
            return (Criteria) this;
        }

        public Criteria andRecipientNotEqualTo(String value) {
            addCriterion("RECIPIENT <>", value, "recipient");
            return (Criteria) this;
        }

        public Criteria andRecipientGreaterThan(String value) {
            addCriterion("RECIPIENT >", value, "recipient");
            return (Criteria) this;
        }

        public Criteria andRecipientGreaterThanOrEqualTo(String value) {
            addCriterion("RECIPIENT >=", value, "recipient");
            return (Criteria) this;
        }

        public Criteria andRecipientLessThan(String value) {
            addCriterion("RECIPIENT <", value, "recipient");
            return (Criteria) this;
        }

        public Criteria andRecipientLessThanOrEqualTo(String value) {
            addCriterion("RECIPIENT <=", value, "recipient");
            return (Criteria) this;
        }

        public Criteria andRecipientLike(String value) {
            addCriterion("RECIPIENT like", value, "recipient");
            return (Criteria) this;
        }

        public Criteria andRecipientNotLike(String value) {
            addCriterion("RECIPIENT not like", value, "recipient");
            return (Criteria) this;
        }

        public Criteria andRecipientIn(List<String> values) {
            addCriterion("RECIPIENT in", values, "recipient");
            return (Criteria) this;
        }

        public Criteria andRecipientNotIn(List<String> values) {
            addCriterion("RECIPIENT not in", values, "recipient");
            return (Criteria) this;
        }

        public Criteria andRecipientBetween(String value1, String value2) {
            addCriterion("RECIPIENT between", value1, value2, "recipient");
            return (Criteria) this;
        }

        public Criteria andRecipientNotBetween(String value1, String value2) {
            addCriterion("RECIPIENT not between", value1, value2, "recipient");
            return (Criteria) this;
        }

        public Criteria andRecipientAddressIsNull() {
            addCriterion("RECIPIENT_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andRecipientAddressIsNotNull() {
            addCriterion("RECIPIENT_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andRecipientAddressEqualTo(String value) {
            addCriterion("RECIPIENT_ADDRESS =", value, "recipientAddress");
            return (Criteria) this;
        }

        public Criteria andRecipientAddressNotEqualTo(String value) {
            addCriterion("RECIPIENT_ADDRESS <>", value, "recipientAddress");
            return (Criteria) this;
        }

        public Criteria andRecipientAddressGreaterThan(String value) {
            addCriterion("RECIPIENT_ADDRESS >", value, "recipientAddress");
            return (Criteria) this;
        }

        public Criteria andRecipientAddressGreaterThanOrEqualTo(String value) {
            addCriterion("RECIPIENT_ADDRESS >=", value, "recipientAddress");
            return (Criteria) this;
        }

        public Criteria andRecipientAddressLessThan(String value) {
            addCriterion("RECIPIENT_ADDRESS <", value, "recipientAddress");
            return (Criteria) this;
        }

        public Criteria andRecipientAddressLessThanOrEqualTo(String value) {
            addCriterion("RECIPIENT_ADDRESS <=", value, "recipientAddress");
            return (Criteria) this;
        }

        public Criteria andRecipientAddressLike(String value) {
            addCriterion("RECIPIENT_ADDRESS like", value, "recipientAddress");
            return (Criteria) this;
        }

        public Criteria andRecipientAddressNotLike(String value) {
            addCriterion("RECIPIENT_ADDRESS not like", value, "recipientAddress");
            return (Criteria) this;
        }

        public Criteria andRecipientAddressIn(List<String> values) {
            addCriterion("RECIPIENT_ADDRESS in", values, "recipientAddress");
            return (Criteria) this;
        }

        public Criteria andRecipientAddressNotIn(List<String> values) {
            addCriterion("RECIPIENT_ADDRESS not in", values, "recipientAddress");
            return (Criteria) this;
        }

        public Criteria andRecipientAddressBetween(String value1, String value2) {
            addCriterion("RECIPIENT_ADDRESS between", value1, value2, "recipientAddress");
            return (Criteria) this;
        }

        public Criteria andRecipientAddressNotBetween(String value1, String value2) {
            addCriterion("RECIPIENT_ADDRESS not between", value1, value2, "recipientAddress");
            return (Criteria) this;
        }

        public Criteria andTechAreaNameIsNull() {
            addCriterion("TECH_AREA_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTechAreaNameIsNotNull() {
            addCriterion("TECH_AREA_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTechAreaNameEqualTo(String value) {
            addCriterion("TECH_AREA_NAME =", value, "techAreaName");
            return (Criteria) this;
        }

        public Criteria andTechAreaNameNotEqualTo(String value) {
            addCriterion("TECH_AREA_NAME <>", value, "techAreaName");
            return (Criteria) this;
        }

        public Criteria andTechAreaNameGreaterThan(String value) {
            addCriterion("TECH_AREA_NAME >", value, "techAreaName");
            return (Criteria) this;
        }

        public Criteria andTechAreaNameGreaterThanOrEqualTo(String value) {
            addCriterion("TECH_AREA_NAME >=", value, "techAreaName");
            return (Criteria) this;
        }

        public Criteria andTechAreaNameLessThan(String value) {
            addCriterion("TECH_AREA_NAME <", value, "techAreaName");
            return (Criteria) this;
        }

        public Criteria andTechAreaNameLessThanOrEqualTo(String value) {
            addCriterion("TECH_AREA_NAME <=", value, "techAreaName");
            return (Criteria) this;
        }

        public Criteria andTechAreaNameLike(String value) {
            addCriterion("TECH_AREA_NAME like", value, "techAreaName");
            return (Criteria) this;
        }

        public Criteria andTechAreaNameNotLike(String value) {
            addCriterion("TECH_AREA_NAME not like", value, "techAreaName");
            return (Criteria) this;
        }

        public Criteria andTechAreaNameIn(List<String> values) {
            addCriterion("TECH_AREA_NAME in", values, "techAreaName");
            return (Criteria) this;
        }

        public Criteria andTechAreaNameNotIn(List<String> values) {
            addCriterion("TECH_AREA_NAME not in", values, "techAreaName");
            return (Criteria) this;
        }

        public Criteria andTechAreaNameBetween(String value1, String value2) {
            addCriterion("TECH_AREA_NAME between", value1, value2, "techAreaName");
            return (Criteria) this;
        }

        public Criteria andTechAreaNameNotBetween(String value1, String value2) {
            addCriterion("TECH_AREA_NAME not between", value1, value2, "techAreaName");
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

        public Criteria andFinishInfoFlagIsNull() {
            addCriterion("FINISH_INFO_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andFinishInfoFlagIsNotNull() {
            addCriterion("FINISH_INFO_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andFinishInfoFlagEqualTo(String value) {
            addCriterion("FINISH_INFO_FLAG =", value, "finishInfoFlag");
            return (Criteria) this;
        }

        public Criteria andFinishInfoFlagNotEqualTo(String value) {
            addCriterion("FINISH_INFO_FLAG <>", value, "finishInfoFlag");
            return (Criteria) this;
        }

        public Criteria andFinishInfoFlagGreaterThan(String value) {
            addCriterion("FINISH_INFO_FLAG >", value, "finishInfoFlag");
            return (Criteria) this;
        }

        public Criteria andFinishInfoFlagGreaterThanOrEqualTo(String value) {
            addCriterion("FINISH_INFO_FLAG >=", value, "finishInfoFlag");
            return (Criteria) this;
        }

        public Criteria andFinishInfoFlagLessThan(String value) {
            addCriterion("FINISH_INFO_FLAG <", value, "finishInfoFlag");
            return (Criteria) this;
        }

        public Criteria andFinishInfoFlagLessThanOrEqualTo(String value) {
            addCriterion("FINISH_INFO_FLAG <=", value, "finishInfoFlag");
            return (Criteria) this;
        }

        public Criteria andFinishInfoFlagLike(String value) {
            addCriterion("FINISH_INFO_FLAG like", value, "finishInfoFlag");
            return (Criteria) this;
        }

        public Criteria andFinishInfoFlagNotLike(String value) {
            addCriterion("FINISH_INFO_FLAG not like", value, "finishInfoFlag");
            return (Criteria) this;
        }

        public Criteria andFinishInfoFlagIn(List<String> values) {
            addCriterion("FINISH_INFO_FLAG in", values, "finishInfoFlag");
            return (Criteria) this;
        }

        public Criteria andFinishInfoFlagNotIn(List<String> values) {
            addCriterion("FINISH_INFO_FLAG not in", values, "finishInfoFlag");
            return (Criteria) this;
        }

        public Criteria andFinishInfoFlagBetween(String value1, String value2) {
            addCriterion("FINISH_INFO_FLAG between", value1, value2, "finishInfoFlag");
            return (Criteria) this;
        }

        public Criteria andFinishInfoFlagNotBetween(String value1, String value2) {
            addCriterion("FINISH_INFO_FLAG not between", value1, value2, "finishInfoFlag");
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