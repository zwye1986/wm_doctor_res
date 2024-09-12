package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ResTeacherTrainingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResTeacherTrainingExample() {
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

        public Criteria andDoctorNameIsNull() {
            addCriterion("DOCTOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDoctorNameIsNotNull() {
            addCriterion("DOCTOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorNameEqualTo(String value) {
            addCriterion("DOCTOR_NAME =", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotEqualTo(String value) {
            addCriterion("DOCTOR_NAME <>", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameGreaterThan(String value) {
            addCriterion("DOCTOR_NAME >", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_NAME >=", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLessThan(String value) {
            addCriterion("DOCTOR_NAME <", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_NAME <=", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLike(String value) {
            addCriterion("DOCTOR_NAME like", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotLike(String value) {
            addCriterion("DOCTOR_NAME not like", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameIn(List<String> values) {
            addCriterion("DOCTOR_NAME in", values, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotIn(List<String> values) {
            addCriterion("DOCTOR_NAME not in", values, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameBetween(String value1, String value2) {
            addCriterion("DOCTOR_NAME between", value1, value2, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_NAME not between", value1, value2, "doctorName");
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

        public Criteria andTechnicalTitleIsNull() {
            addCriterion("TECHNICAL_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleIsNotNull() {
            addCriterion("TECHNICAL_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleEqualTo(String value) {
            addCriterion("TECHNICAL_TITLE =", value, "technicalTitle");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleNotEqualTo(String value) {
            addCriterion("TECHNICAL_TITLE <>", value, "technicalTitle");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleGreaterThan(String value) {
            addCriterion("TECHNICAL_TITLE >", value, "technicalTitle");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleGreaterThanOrEqualTo(String value) {
            addCriterion("TECHNICAL_TITLE >=", value, "technicalTitle");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleLessThan(String value) {
            addCriterion("TECHNICAL_TITLE <", value, "technicalTitle");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleLessThanOrEqualTo(String value) {
            addCriterion("TECHNICAL_TITLE <=", value, "technicalTitle");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleLike(String value) {
            addCriterion("TECHNICAL_TITLE like", value, "technicalTitle");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleNotLike(String value) {
            addCriterion("TECHNICAL_TITLE not like", value, "technicalTitle");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleIn(List<String> values) {
            addCriterion("TECHNICAL_TITLE in", values, "technicalTitle");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleNotIn(List<String> values) {
            addCriterion("TECHNICAL_TITLE not in", values, "technicalTitle");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleBetween(String value1, String value2) {
            addCriterion("TECHNICAL_TITLE between", value1, value2, "technicalTitle");
            return (Criteria) this;
        }

        public Criteria andTechnicalTitleNotBetween(String value1, String value2) {
            addCriterion("TECHNICAL_TITLE not between", value1, value2, "technicalTitle");
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

        public Criteria andOrgNameIsNull() {
            addCriterion("ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNotNull() {
            addCriterion("ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEqualTo(String value) {
            addCriterion("ORG_NAME =", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotEqualTo(String value) {
            addCriterion("ORG_NAME <>", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThan(String value) {
            addCriterion("ORG_NAME >", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_NAME >=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThan(String value) {
            addCriterion("ORG_NAME <", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_NAME <=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLike(String value) {
            addCriterion("ORG_NAME like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotLike(String value) {
            addCriterion("ORG_NAME not like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIn(List<String> values) {
            addCriterion("ORG_NAME in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotIn(List<String> values) {
            addCriterion("ORG_NAME not in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameBetween(String value1, String value2) {
            addCriterion("ORG_NAME between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotBetween(String value1, String value2) {
            addCriterion("ORG_NAME not between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andCertificateNoIsNull() {
            addCriterion("CERTIFICATE_NO is null");
            return (Criteria) this;
        }

        public Criteria andCertificateNoIsNotNull() {
            addCriterion("CERTIFICATE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateNoEqualTo(String value) {
            addCriterion("CERTIFICATE_NO =", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoNotEqualTo(String value) {
            addCriterion("CERTIFICATE_NO <>", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoGreaterThan(String value) {
            addCriterion("CERTIFICATE_NO >", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_NO >=", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoLessThan(String value) {
            addCriterion("CERTIFICATE_NO <", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_NO <=", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoLike(String value) {
            addCriterion("CERTIFICATE_NO like", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoNotLike(String value) {
            addCriterion("CERTIFICATE_NO not like", value, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoIn(List<String> values) {
            addCriterion("CERTIFICATE_NO in", values, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoNotIn(List<String> values) {
            addCriterion("CERTIFICATE_NO not in", values, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_NO between", value1, value2, "certificateNo");
            return (Criteria) this;
        }

        public Criteria andCertificateNoNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_NO not between", value1, value2, "certificateNo");
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

        public Criteria andSpeIdIsNull() {
            addCriterion("SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSpeIdIsNotNull() {
            addCriterion("SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSpeIdEqualTo(String value) {
            addCriterion("SPE_ID =", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotEqualTo(String value) {
            addCriterion("SPE_ID <>", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdGreaterThan(String value) {
            addCriterion("SPE_ID >", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_ID >=", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLessThan(String value) {
            addCriterion("SPE_ID <", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLessThanOrEqualTo(String value) {
            addCriterion("SPE_ID <=", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLike(String value) {
            addCriterion("SPE_ID like", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotLike(String value) {
            addCriterion("SPE_ID not like", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdIn(List<String> values) {
            addCriterion("SPE_ID in", values, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotIn(List<String> values) {
            addCriterion("SPE_ID not in", values, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdBetween(String value1, String value2) {
            addCriterion("SPE_ID between", value1, value2, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotBetween(String value1, String value2) {
            addCriterion("SPE_ID not between", value1, value2, "speId");
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

        public Criteria andDeptFlowIsNull() {
            addCriterion("DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIsNotNull() {
            addCriterion("DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDeptFlowEqualTo(String value) {
            addCriterion("DEPT_FLOW =", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotEqualTo(String value) {
            addCriterion("DEPT_FLOW <>", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowGreaterThan(String value) {
            addCriterion("DEPT_FLOW >", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_FLOW >=", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLessThan(String value) {
            addCriterion("DEPT_FLOW <", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("DEPT_FLOW <=", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLike(String value) {
            addCriterion("DEPT_FLOW like", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotLike(String value) {
            addCriterion("DEPT_FLOW not like", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIn(List<String> values) {
            addCriterion("DEPT_FLOW in", values, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotIn(List<String> values) {
            addCriterion("DEPT_FLOW not in", values, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowBetween(String value1, String value2) {
            addCriterion("DEPT_FLOW between", value1, value2, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotBetween(String value1, String value2) {
            addCriterion("DEPT_FLOW not between", value1, value2, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNull() {
            addCriterion("DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNotNull() {
            addCriterion("DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDeptNameEqualTo(String value) {
            addCriterion("DEPT_NAME =", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotEqualTo(String value) {
            addCriterion("DEPT_NAME <>", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThan(String value) {
            addCriterion("DEPT_NAME >", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_NAME >=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThan(String value) {
            addCriterion("DEPT_NAME <", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThanOrEqualTo(String value) {
            addCriterion("DEPT_NAME <=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLike(String value) {
            addCriterion("DEPT_NAME like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotLike(String value) {
            addCriterion("DEPT_NAME not like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameIn(List<String> values) {
            addCriterion("DEPT_NAME in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotIn(List<String> values) {
            addCriterion("DEPT_NAME not in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameBetween(String value1, String value2) {
            addCriterion("DEPT_NAME between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotBetween(String value1, String value2) {
            addCriterion("DEPT_NAME not between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andTrainingYearIsNull() {
            addCriterion("TRAINING_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andTrainingYearIsNotNull() {
            addCriterion("TRAINING_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingYearEqualTo(String value) {
            addCriterion("TRAINING_YEAR =", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andIsResponsibleTutorEqualTo(String value) {
            addCriterion("IS_RESPONSIBLE_TUTOR =", value, "isResponsibleTutor");
            return (Criteria) this;
        }

        public Criteria andTrainingYearNotEqualTo(String value) {
            addCriterion("TRAINING_YEAR <>", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearGreaterThan(String value) {
            addCriterion("TRAINING_YEAR >", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearGreaterThanOrEqualTo(String value) {
            addCriterion("TRAINING_YEAR >=", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearLessThan(String value) {
            addCriterion("TRAINING_YEAR <", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearLessThanOrEqualTo(String value) {
            addCriterion("TRAINING_YEAR <=", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearLike(String value) {
            addCriterion("TRAINING_YEAR like", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearNotLike(String value) {
            addCriterion("TRAINING_YEAR not like", value, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearIn(List<String> values) {
            addCriterion("TRAINING_YEAR in", values, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearNotIn(List<String> values) {
            addCriterion("TRAINING_YEAR not in", values, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearBetween(String value1, String value2) {
            addCriterion("TRAINING_YEAR between", value1, value2, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andTrainingYearNotBetween(String value1, String value2) {
            addCriterion("TRAINING_YEAR not between", value1, value2, "trainingYear");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlIsNull() {
            addCriterion("CERTIFICATE_URL is null");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlIsNotNull() {
            addCriterion("CERTIFICATE_URL is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlEqualTo(String value) {
            addCriterion("CERTIFICATE_URL =", value, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlNotEqualTo(String value) {
            addCriterion("CERTIFICATE_URL <>", value, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlGreaterThan(String value) {
            addCriterion("CERTIFICATE_URL >", value, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_URL >=", value, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlLessThan(String value) {
            addCriterion("CERTIFICATE_URL <", value, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_URL <=", value, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlLike(String value) {
            addCriterion("CERTIFICATE_URL like", value, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlNotLike(String value) {
            addCriterion("CERTIFICATE_URL not like", value, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlIn(List<String> values) {
            addCriterion("CERTIFICATE_URL in", values, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlNotIn(List<String> values) {
            addCriterion("CERTIFICATE_URL not in", values, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_URL between", value1, value2, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andCertificateUrlNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_URL not between", value1, value2, "certificateUrl");
            return (Criteria) this;
        }

        public Criteria andMeetingNameIsNull() {
            addCriterion("MEETING_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMeetingNameIsNotNull() {
            addCriterion("MEETING_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMeetingNameEqualTo(String value) {
            addCriterion("MEETING_NAME =", value, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameNotEqualTo(String value) {
            addCriterion("MEETING_NAME <>", value, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameGreaterThan(String value) {
            addCriterion("MEETING_NAME >", value, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameGreaterThanOrEqualTo(String value) {
            addCriterion("MEETING_NAME >=", value, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameLessThan(String value) {
            addCriterion("MEETING_NAME <", value, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameLessThanOrEqualTo(String value) {
            addCriterion("MEETING_NAME <=", value, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameLike(String value) {
            addCriterion("MEETING_NAME like", value, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameNotLike(String value) {
            addCriterion("MEETING_NAME not like", value, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameIn(List<String> values) {
            addCriterion("MEETING_NAME in", values, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameNotIn(List<String> values) {
            addCriterion("MEETING_NAME not in", values, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameBetween(String value1, String value2) {
            addCriterion("MEETING_NAME between", value1, value2, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameNotBetween(String value1, String value2) {
            addCriterion("MEETING_NAME not between", value1, value2, "meetingName");
            return (Criteria) this;
        }

        public Criteria andDoctorAgeIsNull() {
            addCriterion("DOCTOR_AGE is null");
            return (Criteria) this;
        }

        public Criteria andDoctorAgeIsNotNull() {
            addCriterion("DOCTOR_AGE is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorAgeEqualTo(String value) {
            addCriterion("DOCTOR_AGE =", value, "doctorAge");
            return (Criteria) this;
        }

        public Criteria andDoctorAgeNotEqualTo(String value) {
            addCriterion("DOCTOR_AGE <>", value, "doctorAge");
            return (Criteria) this;
        }

        public Criteria andDoctorAgeGreaterThan(String value) {
            addCriterion("DOCTOR_AGE >", value, "doctorAge");
            return (Criteria) this;
        }

        public Criteria andDoctorAgeGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_AGE >=", value, "doctorAge");
            return (Criteria) this;
        }

        public Criteria andDoctorAgeLessThan(String value) {
            addCriterion("DOCTOR_AGE <", value, "doctorAge");
            return (Criteria) this;
        }

        public Criteria andDoctorAgeLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_AGE <=", value, "doctorAge");
            return (Criteria) this;
        }

        public Criteria andDoctorAgeLike(String value) {
            addCriterion("DOCTOR_AGE like", value, "doctorAge");
            return (Criteria) this;
        }

        public Criteria andDoctorAgeNotLike(String value) {
            addCriterion("DOCTOR_AGE not like", value, "doctorAge");
            return (Criteria) this;
        }

        public Criteria andDoctorAgeIn(List<String> values) {
            addCriterion("DOCTOR_AGE in", values, "doctorAge");
            return (Criteria) this;
        }

        public Criteria andDoctorAgeNotIn(List<String> values) {
            addCriterion("DOCTOR_AGE not in", values, "doctorAge");
            return (Criteria) this;
        }

        public Criteria andDoctorAgeBetween(String value1, String value2) {
            addCriterion("DOCTOR_AGE between", value1, value2, "doctorAge");
            return (Criteria) this;
        }

        public Criteria andDoctorAgeNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_AGE not between", value1, value2, "doctorAge");
            return (Criteria) this;
        }

        public Criteria andDoctorEduIsNull() {
            addCriterion("DOCTOR_EDU is null");
            return (Criteria) this;
        }

        public Criteria andDoctorEduIsNotNull() {
            addCriterion("DOCTOR_EDU is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorEduEqualTo(String value) {
            addCriterion("DOCTOR_EDU =", value, "doctorEdu");
            return (Criteria) this;
        }

        public Criteria andDoctorEduNotEqualTo(String value) {
            addCriterion("DOCTOR_EDU <>", value, "doctorEdu");
            return (Criteria) this;
        }

        public Criteria andDoctorEduGreaterThan(String value) {
            addCriterion("DOCTOR_EDU >", value, "doctorEdu");
            return (Criteria) this;
        }

        public Criteria andDoctorEduGreaterThanOrEqualTo(String value) {
            addCriterion("DOCTOR_EDU >=", value, "doctorEdu");
            return (Criteria) this;
        }

        public Criteria andDoctorEduLessThan(String value) {
            addCriterion("DOCTOR_EDU <", value, "doctorEdu");
            return (Criteria) this;
        }

        public Criteria andDoctorEduLessThanOrEqualTo(String value) {
            addCriterion("DOCTOR_EDU <=", value, "doctorEdu");
            return (Criteria) this;
        }

        public Criteria andDoctorEduLike(String value) {
            addCriterion("DOCTOR_EDU like", value, "doctorEdu");
            return (Criteria) this;
        }

        public Criteria andDoctorEduNotLike(String value) {
            addCriterion("DOCTOR_EDU not like", value, "doctorEdu");
            return (Criteria) this;
        }

        public Criteria andDoctorEduIn(List<String> values) {
            addCriterion("DOCTOR_EDU in", values, "doctorEdu");
            return (Criteria) this;
        }

        public Criteria andDoctorEduNotIn(List<String> values) {
            addCriterion("DOCTOR_EDU not in", values, "doctorEdu");
            return (Criteria) this;
        }

        public Criteria andDoctorEduBetween(String value1, String value2) {
            addCriterion("DOCTOR_EDU between", value1, value2, "doctorEdu");
            return (Criteria) this;
        }

        public Criteria andDoctorEduNotBetween(String value1, String value2) {
            addCriterion("DOCTOR_EDU not between", value1, value2, "doctorEdu");
            return (Criteria) this;
        }

        public Criteria andOfficeYearIsNull() {
            addCriterion("OFFICE_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andOfficeYearIsNotNull() {
            addCriterion("OFFICE_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andOfficeYearEqualTo(String value) {
            addCriterion("OFFICE_YEAR =", value, "officeYear");
            return (Criteria) this;
        }

        public Criteria andOfficeYearNotEqualTo(String value) {
            addCriterion("OFFICE_YEAR <>", value, "officeYear");
            return (Criteria) this;
        }

        public Criteria andOfficeYearGreaterThan(String value) {
            addCriterion("OFFICE_YEAR >", value, "officeYear");
            return (Criteria) this;
        }

        public Criteria andOfficeYearGreaterThanOrEqualTo(String value) {
            addCriterion("OFFICE_YEAR >=", value, "officeYear");
            return (Criteria) this;
        }

        public Criteria andOfficeYearLessThan(String value) {
            addCriterion("OFFICE_YEAR <", value, "officeYear");
            return (Criteria) this;
        }

        public Criteria andOfficeYearLessThanOrEqualTo(String value) {
            addCriterion("OFFICE_YEAR <=", value, "officeYear");
            return (Criteria) this;
        }

        public Criteria andOfficeYearLike(String value) {
            addCriterion("OFFICE_YEAR like", value, "officeYear");
            return (Criteria) this;
        }

        public Criteria andOfficeYearNotLike(String value) {
            addCriterion("OFFICE_YEAR not like", value, "officeYear");
            return (Criteria) this;
        }

        public Criteria andOfficeYearIn(List<String> values) {
            addCriterion("OFFICE_YEAR in", values, "officeYear");
            return (Criteria) this;
        }

        public Criteria andOfficeYearNotIn(List<String> values) {
            addCriterion("OFFICE_YEAR not in", values, "officeYear");
            return (Criteria) this;
        }

        public Criteria andOfficeYearBetween(String value1, String value2) {
            addCriterion("OFFICE_YEAR between", value1, value2, "officeYear");
            return (Criteria) this;
        }

        public Criteria andOfficeYearNotBetween(String value1, String value2) {
            addCriterion("OFFICE_YEAR not between", value1, value2, "officeYear");
            return (Criteria) this;
        }

        public Criteria andWorkYearIsNull() {
            addCriterion("WORK_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andWorkYearIsNotNull() {
            addCriterion("WORK_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andWorkYearEqualTo(String value) {
            addCriterion("WORK_YEAR =", value, "workYear");
            return (Criteria) this;
        }

        public Criteria andWorkYearNotEqualTo(String value) {
            addCriterion("WORK_YEAR <>", value, "workYear");
            return (Criteria) this;
        }

        public Criteria andWorkYearGreaterThan(String value) {
            addCriterion("WORK_YEAR >", value, "workYear");
            return (Criteria) this;
        }

        public Criteria andWorkYearGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_YEAR >=", value, "workYear");
            return (Criteria) this;
        }

        public Criteria andWorkYearLessThan(String value) {
            addCriterion("WORK_YEAR <", value, "workYear");
            return (Criteria) this;
        }

        public Criteria andWorkYearLessThanOrEqualTo(String value) {
            addCriterion("WORK_YEAR <=", value, "workYear");
            return (Criteria) this;
        }

        public Criteria andWorkYearLike(String value) {
            addCriterion("WORK_YEAR like", value, "workYear");
            return (Criteria) this;
        }

        public Criteria andWorkYearNotLike(String value) {
            addCriterion("WORK_YEAR not like", value, "workYear");
            return (Criteria) this;
        }

        public Criteria andWorkYearIn(List<String> values) {
            addCriterion("WORK_YEAR in", values, "workYear");
            return (Criteria) this;
        }

        public Criteria andWorkYearNotIn(List<String> values) {
            addCriterion("WORK_YEAR not in", values, "workYear");
            return (Criteria) this;
        }

        public Criteria andWorkYearBetween(String value1, String value2) {
            addCriterion("WORK_YEAR between", value1, value2, "workYear");
            return (Criteria) this;
        }

        public Criteria andWorkYearNotBetween(String value1, String value2) {
            addCriterion("WORK_YEAR not between", value1, value2, "workYear");
            return (Criteria) this;
        }

        public Criteria andInternYearIsNull() {
            addCriterion("INTERN_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andInternYearIsNotNull() {
            addCriterion("INTERN_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andInternYearEqualTo(String value) {
            addCriterion("INTERN_YEAR =", value, "internYear");
            return (Criteria) this;
        }

        public Criteria andInternYearNotEqualTo(String value) {
            addCriterion("INTERN_YEAR <>", value, "internYear");
            return (Criteria) this;
        }

        public Criteria andInternYearGreaterThan(String value) {
            addCriterion("INTERN_YEAR >", value, "internYear");
            return (Criteria) this;
        }

        public Criteria andInternYearGreaterThanOrEqualTo(String value) {
            addCriterion("INTERN_YEAR >=", value, "internYear");
            return (Criteria) this;
        }

        public Criteria andInternYearLessThan(String value) {
            addCriterion("INTERN_YEAR <", value, "internYear");
            return (Criteria) this;
        }

        public Criteria andInternYearLessThanOrEqualTo(String value) {
            addCriterion("INTERN_YEAR <=", value, "internYear");
            return (Criteria) this;
        }

        public Criteria andInternYearLike(String value) {
            addCriterion("INTERN_YEAR like", value, "internYear");
            return (Criteria) this;
        }

        public Criteria andInternYearNotLike(String value) {
            addCriterion("INTERN_YEAR not like", value, "internYear");
            return (Criteria) this;
        }

        public Criteria andInternYearIn(List<String> values) {
            addCriterion("INTERN_YEAR in", values, "internYear");
            return (Criteria) this;
        }

        public Criteria andInternYearNotIn(List<String> values) {
            addCriterion("INTERN_YEAR not in", values, "internYear");
            return (Criteria) this;
        }

        public Criteria andInternYearBetween(String value1, String value2) {
            addCriterion("INTERN_YEAR between", value1, value2, "internYear");
            return (Criteria) this;
        }

        public Criteria andInternYearNotBetween(String value1, String value2) {
            addCriterion("INTERN_YEAR not between", value1, value2, "internYear");
            return (Criteria) this;
        }

        public Criteria andThreeInternYearIsNull() {
            addCriterion("THREE_INTERN_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andThreeInternYearIsNotNull() {
            addCriterion("THREE_INTERN_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andThreeInternYearEqualTo(String value) {
            addCriterion("THREE_INTERN_YEAR =", value, "threeInternYear");
            return (Criteria) this;
        }

        public Criteria andThreeInternYearNotEqualTo(String value) {
            addCriterion("THREE_INTERN_YEAR <>", value, "threeInternYear");
            return (Criteria) this;
        }

        public Criteria andThreeInternYearGreaterThan(String value) {
            addCriterion("THREE_INTERN_YEAR >", value, "threeInternYear");
            return (Criteria) this;
        }

        public Criteria andThreeInternYearGreaterThanOrEqualTo(String value) {
            addCriterion("THREE_INTERN_YEAR >=", value, "threeInternYear");
            return (Criteria) this;
        }

        public Criteria andThreeInternYearLessThan(String value) {
            addCriterion("THREE_INTERN_YEAR <", value, "threeInternYear");
            return (Criteria) this;
        }

        public Criteria andThreeInternYearLessThanOrEqualTo(String value) {
            addCriterion("THREE_INTERN_YEAR <=", value, "threeInternYear");
            return (Criteria) this;
        }

        public Criteria andThreeInternYearLike(String value) {
            addCriterion("THREE_INTERN_YEAR like", value, "threeInternYear");
            return (Criteria) this;
        }

        public Criteria andThreeInternYearNotLike(String value) {
            addCriterion("THREE_INTERN_YEAR not like", value, "threeInternYear");
            return (Criteria) this;
        }

        public Criteria andThreeInternYearIn(List<String> values) {
            addCriterion("THREE_INTERN_YEAR in", values, "threeInternYear");
            return (Criteria) this;
        }

        public Criteria andThreeInternYearNotIn(List<String> values) {
            addCriterion("THREE_INTERN_YEAR not in", values, "threeInternYear");
            return (Criteria) this;
        }

        public Criteria andThreeInternYearBetween(String value1, String value2) {
            addCriterion("THREE_INTERN_YEAR between", value1, value2, "threeInternYear");
            return (Criteria) this;
        }

        public Criteria andThreeInternYearNotBetween(String value1, String value2) {
            addCriterion("THREE_INTERN_YEAR not between", value1, value2, "threeInternYear");
            return (Criteria) this;
        }

        public Criteria andHosYearIsNull() {
            addCriterion("HOS_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andHosYearIsNotNull() {
            addCriterion("HOS_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andHosYearEqualTo(String value) {
            addCriterion("HOS_YEAR =", value, "hosYear");
            return (Criteria) this;
        }

        public Criteria andHosYearNotEqualTo(String value) {
            addCriterion("HOS_YEAR <>", value, "hosYear");
            return (Criteria) this;
        }

        public Criteria andHosYearGreaterThan(String value) {
            addCriterion("HOS_YEAR >", value, "hosYear");
            return (Criteria) this;
        }

        public Criteria andHosYearGreaterThanOrEqualTo(String value) {
            addCriterion("HOS_YEAR >=", value, "hosYear");
            return (Criteria) this;
        }

        public Criteria andHosYearLessThan(String value) {
            addCriterion("HOS_YEAR <", value, "hosYear");
            return (Criteria) this;
        }

        public Criteria andHosYearLessThanOrEqualTo(String value) {
            addCriterion("HOS_YEAR <=", value, "hosYear");
            return (Criteria) this;
        }

        public Criteria andHosYearLike(String value) {
            addCriterion("HOS_YEAR like", value, "hosYear");
            return (Criteria) this;
        }

        public Criteria andHosYearNotLike(String value) {
            addCriterion("HOS_YEAR not like", value, "hosYear");
            return (Criteria) this;
        }

        public Criteria andHosYearIn(List<String> values) {
            addCriterion("HOS_YEAR in", values, "hosYear");
            return (Criteria) this;
        }

        public Criteria andHosYearNotIn(List<String> values) {
            addCriterion("HOS_YEAR not in", values, "hosYear");
            return (Criteria) this;
        }

        public Criteria andHosYearBetween(String value1, String value2) {
            addCriterion("HOS_YEAR between", value1, value2, "hosYear");
            return (Criteria) this;
        }

        public Criteria andHosYearNotBetween(String value1, String value2) {
            addCriterion("HOS_YEAR not between", value1, value2, "hosYear");
            return (Criteria) this;
        }

        public Criteria andThreeHosYearIsNull() {
            addCriterion("THREE_HOS_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andThreeHosYearIsNotNull() {
            addCriterion("THREE_HOS_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andThreeHosYearEqualTo(String value) {
            addCriterion("THREE_HOS_YEAR =", value, "threeHosYear");
            return (Criteria) this;
        }

        public Criteria andThreeHosYearNotEqualTo(String value) {
            addCriterion("THREE_HOS_YEAR <>", value, "threeHosYear");
            return (Criteria) this;
        }

        public Criteria andThreeHosYearGreaterThan(String value) {
            addCriterion("THREE_HOS_YEAR >", value, "threeHosYear");
            return (Criteria) this;
        }

        public Criteria andThreeHosYearGreaterThanOrEqualTo(String value) {
            addCriterion("THREE_HOS_YEAR >=", value, "threeHosYear");
            return (Criteria) this;
        }

        public Criteria andThreeHosYearLessThan(String value) {
            addCriterion("THREE_HOS_YEAR <", value, "threeHosYear");
            return (Criteria) this;
        }

        public Criteria andThreeHosYearLessThanOrEqualTo(String value) {
            addCriterion("THREE_HOS_YEAR <=", value, "threeHosYear");
            return (Criteria) this;
        }

        public Criteria andThreeHosYearLike(String value) {
            addCriterion("THREE_HOS_YEAR like", value, "threeHosYear");
            return (Criteria) this;
        }

        public Criteria andThreeHosYearNotLike(String value) {
            addCriterion("THREE_HOS_YEAR not like", value, "threeHosYear");
            return (Criteria) this;
        }

        public Criteria andThreeHosYearIn(List<String> values) {
            addCriterion("THREE_HOS_YEAR in", values, "threeHosYear");
            return (Criteria) this;
        }

        public Criteria andThreeHosYearNotIn(List<String> values) {
            addCriterion("THREE_HOS_YEAR not in", values, "threeHosYear");
            return (Criteria) this;
        }

        public Criteria andThreeHosYearBetween(String value1, String value2) {
            addCriterion("THREE_HOS_YEAR between", value1, value2, "threeHosYear");
            return (Criteria) this;
        }

        public Criteria andThreeHosYearNotBetween(String value1, String value2) {
            addCriterion("THREE_HOS_YEAR not between", value1, value2, "threeHosYear");
            return (Criteria) this;
        }

        public Criteria andIsTrainIsNull() {
            addCriterion("IS_TRAIN is null");
            return (Criteria) this;
        }

        public Criteria andIsTrainIsNotNull() {
            addCriterion("IS_TRAIN is not null");
            return (Criteria) this;
        }

        public Criteria andIsTrainEqualTo(String value) {
            addCriterion("IS_TRAIN =", value, "isTrain");
            return (Criteria) this;
        }

        public Criteria andIsTrainNotEqualTo(String value) {
            addCriterion("IS_TRAIN <>", value, "isTrain");
            return (Criteria) this;
        }

        public Criteria andIsTrainGreaterThan(String value) {
            addCriterion("IS_TRAIN >", value, "isTrain");
            return (Criteria) this;
        }

        public Criteria andIsTrainGreaterThanOrEqualTo(String value) {
            addCriterion("IS_TRAIN >=", value, "isTrain");
            return (Criteria) this;
        }

        public Criteria andIsTrainLessThan(String value) {
            addCriterion("IS_TRAIN <", value, "isTrain");
            return (Criteria) this;
        }

        public Criteria andIsTrainLessThanOrEqualTo(String value) {
            addCriterion("IS_TRAIN <=", value, "isTrain");
            return (Criteria) this;
        }

        public Criteria andIsTrainLike(String value) {
            addCriterion("IS_TRAIN like", value, "isTrain");
            return (Criteria) this;
        }

        public Criteria andIsTrainNotLike(String value) {
            addCriterion("IS_TRAIN not like", value, "isTrain");
            return (Criteria) this;
        }

        public Criteria andIsTrainIn(List<String> values) {
            addCriterion("IS_TRAIN in", values, "isTrain");
            return (Criteria) this;
        }

        public Criteria andIsTrainNotIn(List<String> values) {
            addCriterion("IS_TRAIN not in", values, "isTrain");
            return (Criteria) this;
        }

        public Criteria andIsTrainBetween(String value1, String value2) {
            addCriterion("IS_TRAIN between", value1, value2, "isTrain");
            return (Criteria) this;
        }

        public Criteria andIsTrainNotBetween(String value1, String value2) {
            addCriterion("IS_TRAIN not between", value1, value2, "isTrain");
            return (Criteria) this;
        }

        public Criteria andAchievementUrlIsNull() {
            addCriterion("ACHIEVEMENT_URL is null");
            return (Criteria) this;
        }

        public Criteria andAchievementUrlIsNotNull() {
            addCriterion("ACHIEVEMENT_URL is not null");
            return (Criteria) this;
        }

        public Criteria andAchievementUrlEqualTo(String value) {
            addCriterion("ACHIEVEMENT_URL =", value, "achievementUrl");
            return (Criteria) this;
        }

        public Criteria andAchievementUrlNotEqualTo(String value) {
            addCriterion("ACHIEVEMENT_URL <>", value, "achievementUrl");
            return (Criteria) this;
        }

        public Criteria andAchievementUrlGreaterThan(String value) {
            addCriterion("ACHIEVEMENT_URL >", value, "achievementUrl");
            return (Criteria) this;
        }

        public Criteria andAchievementUrlGreaterThanOrEqualTo(String value) {
            addCriterion("ACHIEVEMENT_URL >=", value, "achievementUrl");
            return (Criteria) this;
        }

        public Criteria andAchievementUrlLessThan(String value) {
            addCriterion("ACHIEVEMENT_URL <", value, "achievementUrl");
            return (Criteria) this;
        }

        public Criteria andAchievementUrlLessThanOrEqualTo(String value) {
            addCriterion("ACHIEVEMENT_URL <=", value, "achievementUrl");
            return (Criteria) this;
        }

        public Criteria andAchievementUrlLike(String value) {
            addCriterion("ACHIEVEMENT_URL like", value, "achievementUrl");
            return (Criteria) this;
        }

        public Criteria andAchievementUrlNotLike(String value) {
            addCriterion("ACHIEVEMENT_URL not like", value, "achievementUrl");
            return (Criteria) this;
        }

        public Criteria andAchievementUrlIn(List<String> values) {
            addCriterion("ACHIEVEMENT_URL in", values, "achievementUrl");
            return (Criteria) this;
        }

        public Criteria andAchievementUrlNotIn(List<String> values) {
            addCriterion("ACHIEVEMENT_URL not in", values, "achievementUrl");
            return (Criteria) this;
        }

        public Criteria andAchievementUrlBetween(String value1, String value2) {
            addCriterion("ACHIEVEMENT_URL between", value1, value2, "achievementUrl");
            return (Criteria) this;
        }

        public Criteria andAchievementUrlNotBetween(String value1, String value2) {
            addCriterion("ACHIEVEMENT_URL not between", value1, value2, "achievementUrl");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelIdIsNull() {
            addCriterion("TEACHER_LEVEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelIdIsNotNull() {
            addCriterion("TEACHER_LEVEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelIdEqualTo(String value) {
            addCriterion("TEACHER_LEVEL_ID =", value, "teacherLevelId");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelIdNotEqualTo(String value) {
            addCriterion("TEACHER_LEVEL_ID <>", value, "teacherLevelId");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelIdGreaterThan(String value) {
            addCriterion("TEACHER_LEVEL_ID >", value, "teacherLevelId");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelIdGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_LEVEL_ID >=", value, "teacherLevelId");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelIdLessThan(String value) {
            addCriterion("TEACHER_LEVEL_ID <", value, "teacherLevelId");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelIdLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_LEVEL_ID <=", value, "teacherLevelId");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelIdLike(String value) {
            addCriterion("TEACHER_LEVEL_ID like", value, "teacherLevelId");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelIdNotLike(String value) {
            addCriterion("TEACHER_LEVEL_ID not like", value, "teacherLevelId");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelIdIn(List<String> values) {
            addCriterion("TEACHER_LEVEL_ID in", values, "teacherLevelId");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelIdNotIn(List<String> values) {
            addCriterion("TEACHER_LEVEL_ID not in", values, "teacherLevelId");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelIdBetween(String value1, String value2) {
            addCriterion("TEACHER_LEVEL_ID between", value1, value2, "teacherLevelId");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelIdNotBetween(String value1, String value2) {
            addCriterion("TEACHER_LEVEL_ID not between", value1, value2, "teacherLevelId");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelNameIsNull() {
            addCriterion("TEACHER_LEVEL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelNameIsNotNull() {
            addCriterion("TEACHER_LEVEL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelNameEqualTo(String value) {
            addCriterion("TEACHER_LEVEL_NAME =", value, "teacherLevelName");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelNameNotEqualTo(String value) {
            addCriterion("TEACHER_LEVEL_NAME <>", value, "teacherLevelName");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelNameGreaterThan(String value) {
            addCriterion("TEACHER_LEVEL_NAME >", value, "teacherLevelName");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_LEVEL_NAME >=", value, "teacherLevelName");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelNameLessThan(String value) {
            addCriterion("TEACHER_LEVEL_NAME <", value, "teacherLevelName");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelNameLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_LEVEL_NAME <=", value, "teacherLevelName");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelNameLike(String value) {
            addCriterion("TEACHER_LEVEL_NAME like", value, "teacherLevelName");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelNameNotLike(String value) {
            addCriterion("TEACHER_LEVEL_NAME not like", value, "teacherLevelName");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelNameIn(List<String> values) {
            addCriterion("TEACHER_LEVEL_NAME in", values, "teacherLevelName");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelNameNotIn(List<String> values) {
            addCriterion("TEACHER_LEVEL_NAME not in", values, "teacherLevelName");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelNameBetween(String value1, String value2) {
            addCriterion("TEACHER_LEVEL_NAME between", value1, value2, "teacherLevelName");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelNameNotBetween(String value1, String value2) {
            addCriterion("TEACHER_LEVEL_NAME not between", value1, value2, "teacherLevelName");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNull() {
            addCriterion("USER_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNotNull() {
            addCriterion("USER_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneEqualTo(String value) {
            addCriterion("USER_PHONE =", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotEqualTo(String value) {
            addCriterion("USER_PHONE <>", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThan(String value) {
            addCriterion("USER_PHONE >", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("USER_PHONE >=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThan(String value) {
            addCriterion("USER_PHONE <", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThanOrEqualTo(String value) {
            addCriterion("USER_PHONE <=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLike(String value) {
            addCriterion("USER_PHONE like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotLike(String value) {
            addCriterion("USER_PHONE not like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIn(List<String> values) {
            addCriterion("USER_PHONE in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotIn(List<String> values) {
            addCriterion("USER_PHONE not in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneBetween(String value1, String value2) {
            addCriterion("USER_PHONE between", value1, value2, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotBetween(String value1, String value2) {
            addCriterion("USER_PHONE not between", value1, value2, "userPhone");
            return (Criteria) this;
        }

        public Criteria andApplicationTeacherLevelIdIsNull() {
            addCriterion("APPLICATION_TEACHER_LEVEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andApplicationTeacherLevelIdIsNotNull() {
            addCriterion("APPLICATION_TEACHER_LEVEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationTeacherLevelIdEqualTo(String value) {
            addCriterion("APPLICATION_TEACHER_LEVEL_ID =", value, "applicationTeacherLevelId");
            return (Criteria) this;
        }

        public Criteria andApplicationTeacherLevelIdNotEqualTo(String value) {
            addCriterion("APPLICATION_TEACHER_LEVEL_ID <>", value, "applicationTeacherLevelId");
            return (Criteria) this;
        }

        public Criteria andApplicationTeacherLevelIdGreaterThan(String value) {
            addCriterion("APPLICATION_TEACHER_LEVEL_ID >", value, "applicationTeacherLevelId");
            return (Criteria) this;
        }

        public Criteria andApplicationTeacherLevelIdGreaterThanOrEqualTo(String value) {
            addCriterion("APPLICATION_TEACHER_LEVEL_ID >=", value, "applicationTeacherLevelId");
            return (Criteria) this;
        }

        public Criteria andApplicationTeacherLevelIdLessThan(String value) {
            addCriterion("APPLICATION_TEACHER_LEVEL_ID <", value, "applicationTeacherLevelId");
            return (Criteria) this;
        }

        public Criteria andApplicationTeacherLevelIdLessThanOrEqualTo(String value) {
            addCriterion("APPLICATION_TEACHER_LEVEL_ID <=", value, "applicationTeacherLevelId");
            return (Criteria) this;
        }

        public Criteria andApplicationTeacherLevelIdLike(String value) {
            addCriterion("APPLICATION_TEACHER_LEVEL_ID like", value, "applicationTeacherLevelId");
            return (Criteria) this;
        }

        public Criteria andApplicationTeacherLevelIdNotLike(String value) {
            addCriterion("APPLICATION_TEACHER_LEVEL_ID not like", value, "applicationTeacherLevelId");
            return (Criteria) this;
        }

        public Criteria andApplicationTeacherLevelIdIn(List<String> values) {
            addCriterion("APPLICATION_TEACHER_LEVEL_ID in", values, "applicationTeacherLevelId");
            return (Criteria) this;
        }

        public Criteria andApplicationTeacherLevelIdNotIn(List<String> values) {
            addCriterion("APPLICATION_TEACHER_LEVEL_ID not in", values, "applicationTeacherLevelId");
            return (Criteria) this;
        }

        public Criteria andApplicationTeacherLevelIdBetween(String value1, String value2) {
            addCriterion("APPLICATION_TEACHER_LEVEL_ID between", value1, value2, "applicationTeacherLevelId");
            return (Criteria) this;
        }

        public Criteria andApplicationTeacherLevelIdNotBetween(String value1, String value2) {
            addCriterion("APPLICATION_TEACHER_LEVEL_ID not between", value1, value2, "applicationTeacherLevelId");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditStatusIsNull() {
            addCriterion("APPLICATION_AUDIT_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditStatusIsNotNull() {
            addCriterion("APPLICATION_AUDIT_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditStatusEqualTo(String value) {
            addCriterion("APPLICATION_AUDIT_STATUS =", value, "applicationAuditStatus");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditStatusNotEqualTo(String value) {
            addCriterion("APPLICATION_AUDIT_STATUS <>", value, "applicationAuditStatus");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditStatusGreaterThan(String value) {
            addCriterion("APPLICATION_AUDIT_STATUS >", value, "applicationAuditStatus");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("APPLICATION_AUDIT_STATUS >=", value, "applicationAuditStatus");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditStatusLessThan(String value) {
            addCriterion("APPLICATION_AUDIT_STATUS <", value, "applicationAuditStatus");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("APPLICATION_AUDIT_STATUS <=", value, "applicationAuditStatus");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditStatusLike(String value) {
            addCriterion("APPLICATION_AUDIT_STATUS like", value, "applicationAuditStatus");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditStatusNotLike(String value) {
            addCriterion("APPLICATION_AUDIT_STATUS not like", value, "applicationAuditStatus");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditStatusIn(List<String> values) {
            addCriterion("APPLICATION_AUDIT_STATUS in", values, "applicationAuditStatus");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditStatusNotIn(List<String> values) {
            addCriterion("APPLICATION_AUDIT_STATUS not in", values, "applicationAuditStatus");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditStatusBetween(String value1, String value2) {
            addCriterion("APPLICATION_AUDIT_STATUS between", value1, value2, "applicationAuditStatus");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditStatusNotBetween(String value1, String value2) {
            addCriterion("APPLICATION_AUDIT_STATUS not between", value1, value2, "applicationAuditStatus");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditMessageIsNull() {
            addCriterion("APPLICATION_AUDIT_MESSAGE is null");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditMessageIsNotNull() {
            addCriterion("APPLICATION_AUDIT_MESSAGE is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditMessageEqualTo(String value) {
            addCriterion("APPLICATION_AUDIT_MESSAGE =", value, "applicationAuditMessage");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditMessageNotEqualTo(String value) {
            addCriterion("APPLICATION_AUDIT_MESSAGE <>", value, "applicationAuditMessage");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditMessageGreaterThan(String value) {
            addCriterion("APPLICATION_AUDIT_MESSAGE >", value, "applicationAuditMessage");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditMessageGreaterThanOrEqualTo(String value) {
            addCriterion("APPLICATION_AUDIT_MESSAGE >=", value, "applicationAuditMessage");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditMessageLessThan(String value) {
            addCriterion("APPLICATION_AUDIT_MESSAGE <", value, "applicationAuditMessage");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditMessageLessThanOrEqualTo(String value) {
            addCriterion("APPLICATION_AUDIT_MESSAGE <=", value, "applicationAuditMessage");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditMessageLike(String value) {
            addCriterion("APPLICATION_AUDIT_MESSAGE like", value, "applicationAuditMessage");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditMessageNotLike(String value) {
            addCriterion("APPLICATION_AUDIT_MESSAGE not like", value, "applicationAuditMessage");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditMessageIn(List<String> values) {
            addCriterion("APPLICATION_AUDIT_MESSAGE in", values, "applicationAuditMessage");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditMessageNotIn(List<String> values) {
            addCriterion("APPLICATION_AUDIT_MESSAGE not in", values, "applicationAuditMessage");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditMessageBetween(String value1, String value2) {
            addCriterion("APPLICATION_AUDIT_MESSAGE between", value1, value2, "applicationAuditMessage");
            return (Criteria) this;
        }

        public Criteria andApplicationAuditMessageNotBetween(String value1, String value2) {
            addCriterion("APPLICATION_AUDIT_MESSAGE not between", value1, value2, "applicationAuditMessage");
            return (Criteria) this;
        }

        public Criteria andApplicationMessageIsNull() {
            addCriterion("APPLICATION_MESSAGE is null");
            return (Criteria) this;
        }

        public Criteria andApplicationMessageIsNotNull() {
            addCriterion("APPLICATION_MESSAGE is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationMessageEqualTo(String value) {
            addCriterion("APPLICATION_MESSAGE =", value, "applicationMessage");
            return (Criteria) this;
        }

        public Criteria andApplicationMessageNotEqualTo(String value) {
            addCriterion("APPLICATION_MESSAGE <>", value, "applicationMessage");
            return (Criteria) this;
        }

        public Criteria andApplicationMessageGreaterThan(String value) {
            addCriterion("APPLICATION_MESSAGE >", value, "applicationMessage");
            return (Criteria) this;
        }

        public Criteria andApplicationMessageGreaterThanOrEqualTo(String value) {
            addCriterion("APPLICATION_MESSAGE >=", value, "applicationMessage");
            return (Criteria) this;
        }

        public Criteria andApplicationMessageLessThan(String value) {
            addCriterion("APPLICATION_MESSAGE <", value, "applicationMessage");
            return (Criteria) this;
        }

        public Criteria andApplicationMessageLessThanOrEqualTo(String value) {
            addCriterion("APPLICATION_MESSAGE <=", value, "applicationMessage");
            return (Criteria) this;
        }

        public Criteria andApplicationMessageLike(String value) {
            addCriterion("APPLICATION_MESSAGE like", value, "applicationMessage");
            return (Criteria) this;
        }

        public Criteria andApplicationMessageNotLike(String value) {
            addCriterion("APPLICATION_MESSAGE not like", value, "applicationMessage");
            return (Criteria) this;
        }

        public Criteria andApplicationMessageIn(List<String> values) {
            addCriterion("APPLICATION_MESSAGE in", values, "applicationMessage");
            return (Criteria) this;
        }

        public Criteria andApplicationMessageNotIn(List<String> values) {
            addCriterion("APPLICATION_MESSAGE not in", values, "applicationMessage");
            return (Criteria) this;
        }

        public Criteria andApplicationMessageBetween(String value1, String value2) {
            addCriterion("APPLICATION_MESSAGE between", value1, value2, "applicationMessage");
            return (Criteria) this;
        }

        public Criteria andApplicationMessageNotBetween(String value1, String value2) {
            addCriterion("APPLICATION_MESSAGE not between", value1, value2, "applicationMessage");
            return (Criteria) this;
        }

        public Criteria andApplicationProvalUrlIsNull() {
            addCriterion("APPLICATION_PROVAL_URL is null");
            return (Criteria) this;
        }

        public Criteria andApplicationProvalUrlIsNotNull() {
            addCriterion("APPLICATION_PROVAL_URL is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationProvalUrlEqualTo(String value) {
            addCriterion("APPLICATION_PROVAL_URL =", value, "applicationProvalUrl");
            return (Criteria) this;
        }

        public Criteria andApplicationProvalUrlNotEqualTo(String value) {
            addCriterion("APPLICATION_PROVAL_URL <>", value, "applicationProvalUrl");
            return (Criteria) this;
        }

        public Criteria andApplicationProvalUrlGreaterThan(String value) {
            addCriterion("APPLICATION_PROVAL_URL >", value, "applicationProvalUrl");
            return (Criteria) this;
        }

        public Criteria andApplicationProvalUrlGreaterThanOrEqualTo(String value) {
            addCriterion("APPLICATION_PROVAL_URL >=", value, "applicationProvalUrl");
            return (Criteria) this;
        }

        public Criteria andApplicationProvalUrlLessThan(String value) {
            addCriterion("APPLICATION_PROVAL_URL <", value, "applicationProvalUrl");
            return (Criteria) this;
        }

        public Criteria andApplicationProvalUrlLessThanOrEqualTo(String value) {
            addCriterion("APPLICATION_PROVAL_URL <=", value, "applicationProvalUrl");
            return (Criteria) this;
        }

        public Criteria andApplicationProvalUrlLike(String value) {
            addCriterion("APPLICATION_PROVAL_URL like", value, "applicationProvalUrl");
            return (Criteria) this;
        }

        public Criteria andApplicationProvalUrlNotLike(String value) {
            addCriterion("APPLICATION_PROVAL_URL not like", value, "applicationProvalUrl");
            return (Criteria) this;
        }

        public Criteria andApplicationProvalUrlIn(List<String> values) {
            addCriterion("APPLICATION_PROVAL_URL in", values, "applicationProvalUrl");
            return (Criteria) this;
        }

        public Criteria andApplicationProvalUrlNotIn(List<String> values) {
            addCriterion("APPLICATION_PROVAL_URL not in", values, "applicationProvalUrl");
            return (Criteria) this;
        }

        public Criteria andApplicationProvalUrlBetween(String value1, String value2) {
            addCriterion("APPLICATION_PROVAL_URL between", value1, value2, "applicationProvalUrl");
            return (Criteria) this;
        }

        public Criteria andApplicationProvalUrlNotBetween(String value1, String value2) {
            addCriterion("APPLICATION_PROVAL_URL not between", value1, value2, "applicationProvalUrl");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolIsNull() {
            addCriterion("GRADUATION_SCHOOL is null");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolIsNotNull() {
            addCriterion("GRADUATION_SCHOOL is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolEqualTo(String value) {
            addCriterion("GRADUATION_SCHOOL =", value, "graduationSchool");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolNotEqualTo(String value) {
            addCriterion("GRADUATION_SCHOOL <>", value, "graduationSchool");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolGreaterThan(String value) {
            addCriterion("GRADUATION_SCHOOL >", value, "graduationSchool");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATION_SCHOOL >=", value, "graduationSchool");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolLessThan(String value) {
            addCriterion("GRADUATION_SCHOOL <", value, "graduationSchool");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolLessThanOrEqualTo(String value) {
            addCriterion("GRADUATION_SCHOOL <=", value, "graduationSchool");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolLike(String value) {
            addCriterion("GRADUATION_SCHOOL like", value, "graduationSchool");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolNotLike(String value) {
            addCriterion("GRADUATION_SCHOOL not like", value, "graduationSchool");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolIn(List<String> values) {
            addCriterion("GRADUATION_SCHOOL in", values, "graduationSchool");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolNotIn(List<String> values) {
            addCriterion("GRADUATION_SCHOOL not in", values, "graduationSchool");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolBetween(String value1, String value2) {
            addCriterion("GRADUATION_SCHOOL between", value1, value2, "graduationSchool");
            return (Criteria) this;
        }

        public Criteria andGraduationSchoolNotBetween(String value1, String value2) {
            addCriterion("GRADUATION_SCHOOL not between", value1, value2, "graduationSchool");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeIsNull() {
            addCriterion("GRADUATION_TIME is null");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeIsNotNull() {
            addCriterion("GRADUATION_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeEqualTo(String value) {
            addCriterion("GRADUATION_TIME =", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeNotEqualTo(String value) {
            addCriterion("GRADUATION_TIME <>", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeGreaterThan(String value) {
            addCriterion("GRADUATION_TIME >", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATION_TIME >=", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeLessThan(String value) {
            addCriterion("GRADUATION_TIME <", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeLessThanOrEqualTo(String value) {
            addCriterion("GRADUATION_TIME <=", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeLike(String value) {
            addCriterion("GRADUATION_TIME like", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeNotLike(String value) {
            addCriterion("GRADUATION_TIME not like", value, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeIn(List<String> values) {
            addCriterion("GRADUATION_TIME in", values, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeNotIn(List<String> values) {
            addCriterion("GRADUATION_TIME not in", values, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeBetween(String value1, String value2) {
            addCriterion("GRADUATION_TIME between", value1, value2, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andGraduationTimeNotBetween(String value1, String value2) {
            addCriterion("GRADUATION_TIME not between", value1, value2, "graduationTime");
            return (Criteria) this;
        }

        public Criteria andWorkingTimeIsNull() {
            addCriterion("WORKING_TIME is null");
            return (Criteria) this;
        }

        public Criteria andWorkingTimeIsNotNull() {
            addCriterion("WORKING_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andWorkingTimeEqualTo(String value) {
            addCriterion("WORKING_TIME =", value, "workingTime");
            return (Criteria) this;
        }

        public Criteria andWorkingTimeNotEqualTo(String value) {
            addCriterion("WORKING_TIME <>", value, "workingTime");
            return (Criteria) this;
        }

        public Criteria andWorkingTimeGreaterThan(String value) {
            addCriterion("WORKING_TIME >", value, "workingTime");
            return (Criteria) this;
        }

        public Criteria andWorkingTimeGreaterThanOrEqualTo(String value) {
            addCriterion("WORKING_TIME >=", value, "workingTime");
            return (Criteria) this;
        }

        public Criteria andWorkingTimeLessThan(String value) {
            addCriterion("WORKING_TIME <", value, "workingTime");
            return (Criteria) this;
        }

        public Criteria andWorkingTimeLessThanOrEqualTo(String value) {
            addCriterion("WORKING_TIME <=", value, "workingTime");
            return (Criteria) this;
        }

        public Criteria andWorkingTimeLike(String value) {
            addCriterion("WORKING_TIME like", value, "workingTime");
            return (Criteria) this;
        }

        public Criteria andWorkingTimeNotLike(String value) {
            addCriterion("WORKING_TIME not like", value, "workingTime");
            return (Criteria) this;
        }

        public Criteria andWorkingTimeIn(List<String> values) {
            addCriterion("WORKING_TIME in", values, "workingTime");
            return (Criteria) this;
        }

        public Criteria andWorkingTimeNotIn(List<String> values) {
            addCriterion("WORKING_TIME not in", values, "workingTime");
            return (Criteria) this;
        }

        public Criteria andWorkingTimeBetween(String value1, String value2) {
            addCriterion("WORKING_TIME between", value1, value2, "workingTime");
            return (Criteria) this;
        }

        public Criteria andWorkingTimeNotBetween(String value1, String value2) {
            addCriterion("WORKING_TIME not between", value1, value2, "workingTime");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIsNull() {
            addCriterion("CERTIFICATE_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIsNotNull() {
            addCriterion("CERTIFICATE_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelEqualTo(String value) {
            addCriterion("CERTIFICATE_LEVEL =", value, "certificateLevel");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNotEqualTo(String value) {
            addCriterion("CERTIFICATE_LEVEL <>", value, "certificateLevel");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelGreaterThan(String value) {
            addCriterion("CERTIFICATE_LEVEL >", value, "certificateLevel");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_LEVEL >=", value, "certificateLevel");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelLessThan(String value) {
            addCriterion("CERTIFICATE_LEVEL <", value, "certificateLevel");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_LEVEL <=", value, "certificateLevel");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelLike(String value) {
            addCriterion("CERTIFICATE_LEVEL like", value, "certificateLevel");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNotLike(String value) {
            addCriterion("CERTIFICATE_LEVEL not like", value, "certificateLevel");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIn(List<String> values) {
            addCriterion("CERTIFICATE_LEVEL in", values, "certificateLevel");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNotIn(List<String> values) {
            addCriterion("CERTIFICATE_LEVEL not in", values, "certificateLevel");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_LEVEL between", value1, value2, "certificateLevel");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_LEVEL not between", value1, value2, "certificateLevel");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeIsNull() {
            addCriterion("CERTIFICATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeIsNotNull() {
            addCriterion("CERTIFICATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeEqualTo(String value) {
            addCriterion("CERTIFICATE_TIME =", value, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeNotEqualTo(String value) {
            addCriterion("CERTIFICATE_TIME <>", value, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeGreaterThan(String value) {
            addCriterion("CERTIFICATE_TIME >", value, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_TIME >=", value, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeLessThan(String value) {
            addCriterion("CERTIFICATE_TIME <", value, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_TIME <=", value, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeLike(String value) {
            addCriterion("CERTIFICATE_TIME like", value, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeNotLike(String value) {
            addCriterion("CERTIFICATE_TIME not like", value, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeIn(List<String> values) {
            addCriterion("CERTIFICATE_TIME in", values, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeNotIn(List<String> values) {
            addCriterion("CERTIFICATE_TIME not in", values, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_TIME between", value1, value2, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_TIME not between", value1, value2, "certificateTime");
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