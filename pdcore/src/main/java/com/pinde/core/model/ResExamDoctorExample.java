package com.pinde.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ResExamDoctorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResExamDoctorExample() {
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

        public Criteria andExamFlowIsNull() {
            addCriterion("EXAM_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andExamFlowIsNotNull() {
            addCriterion("EXAM_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andExamFlowEqualTo(String value) {
            addCriterion("EXAM_FLOW =", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowNotEqualTo(String value) {
            addCriterion("EXAM_FLOW <>", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowGreaterThan(String value) {
            addCriterion("EXAM_FLOW >", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_FLOW >=", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowLessThan(String value) {
            addCriterion("EXAM_FLOW <", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowLessThanOrEqualTo(String value) {
            addCriterion("EXAM_FLOW <=", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowLike(String value) {
            addCriterion("EXAM_FLOW like", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowNotLike(String value) {
            addCriterion("EXAM_FLOW not like", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowIn(List<String> values) {
            addCriterion("EXAM_FLOW in", values, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowNotIn(List<String> values) {
            addCriterion("EXAM_FLOW not in", values, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowBetween(String value1, String value2) {
            addCriterion("EXAM_FLOW between", value1, value2, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowNotBetween(String value1, String value2) {
            addCriterion("EXAM_FLOW not between", value1, value2, "examFlow");
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

        public Criteria andSiteCodeIsNull() {
            addCriterion("SITE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andSiteCodeIsNotNull() {
            addCriterion("SITE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andSiteCodeEqualTo(String value) {
            addCriterion("SITE_CODE =", value, "siteCode");
            return (Criteria) this;
        }

        public Criteria andSiteCodeNotEqualTo(String value) {
            addCriterion("SITE_CODE <>", value, "siteCode");
            return (Criteria) this;
        }

        public Criteria andSiteCodeGreaterThan(String value) {
            addCriterion("SITE_CODE >", value, "siteCode");
            return (Criteria) this;
        }

        public Criteria andSiteCodeGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_CODE >=", value, "siteCode");
            return (Criteria) this;
        }

        public Criteria andSiteCodeLessThan(String value) {
            addCriterion("SITE_CODE <", value, "siteCode");
            return (Criteria) this;
        }

        public Criteria andSiteCodeLessThanOrEqualTo(String value) {
            addCriterion("SITE_CODE <=", value, "siteCode");
            return (Criteria) this;
        }

        public Criteria andSiteCodeLike(String value) {
            addCriterion("SITE_CODE like", value, "siteCode");
            return (Criteria) this;
        }

        public Criteria andSiteCodeNotLike(String value) {
            addCriterion("SITE_CODE not like", value, "siteCode");
            return (Criteria) this;
        }

        public Criteria andSiteCodeIn(List<String> values) {
            addCriterion("SITE_CODE in", values, "siteCode");
            return (Criteria) this;
        }

        public Criteria andSiteCodeNotIn(List<String> values) {
            addCriterion("SITE_CODE not in", values, "siteCode");
            return (Criteria) this;
        }

        public Criteria andSiteCodeBetween(String value1, String value2) {
            addCriterion("SITE_CODE between", value1, value2, "siteCode");
            return (Criteria) this;
        }

        public Criteria andSiteCodeNotBetween(String value1, String value2) {
            addCriterion("SITE_CODE not between", value1, value2, "siteCode");
            return (Criteria) this;
        }

        public Criteria andSiteFlowIsNull() {
            addCriterion("SITE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSiteFlowIsNotNull() {
            addCriterion("SITE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSiteFlowEqualTo(String value) {
            addCriterion("SITE_FLOW =", value, "siteFlow");
            return (Criteria) this;
        }

        public Criteria andSiteFlowNotEqualTo(String value) {
            addCriterion("SITE_FLOW <>", value, "siteFlow");
            return (Criteria) this;
        }

        public Criteria andSiteFlowGreaterThan(String value) {
            addCriterion("SITE_FLOW >", value, "siteFlow");
            return (Criteria) this;
        }

        public Criteria andSiteFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_FLOW >=", value, "siteFlow");
            return (Criteria) this;
        }

        public Criteria andSiteFlowLessThan(String value) {
            addCriterion("SITE_FLOW <", value, "siteFlow");
            return (Criteria) this;
        }

        public Criteria andSiteFlowLessThanOrEqualTo(String value) {
            addCriterion("SITE_FLOW <=", value, "siteFlow");
            return (Criteria) this;
        }

        public Criteria andSiteFlowLike(String value) {
            addCriterion("SITE_FLOW like", value, "siteFlow");
            return (Criteria) this;
        }

        public Criteria andSiteFlowNotLike(String value) {
            addCriterion("SITE_FLOW not like", value, "siteFlow");
            return (Criteria) this;
        }

        public Criteria andSiteFlowIn(List<String> values) {
            addCriterion("SITE_FLOW in", values, "siteFlow");
            return (Criteria) this;
        }

        public Criteria andSiteFlowNotIn(List<String> values) {
            addCriterion("SITE_FLOW not in", values, "siteFlow");
            return (Criteria) this;
        }

        public Criteria andSiteFlowBetween(String value1, String value2) {
            addCriterion("SITE_FLOW between", value1, value2, "siteFlow");
            return (Criteria) this;
        }

        public Criteria andSiteFlowNotBetween(String value1, String value2) {
            addCriterion("SITE_FLOW not between", value1, value2, "siteFlow");
            return (Criteria) this;
        }

        public Criteria andTicketNumIsNull() {
            addCriterion("TICKET_NUM is null");
            return (Criteria) this;
        }

        public Criteria andTicketNumIsNotNull() {
            addCriterion("TICKET_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andTicketNumEqualTo(String value) {
            addCriterion("TICKET_NUM =", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumNotEqualTo(String value) {
            addCriterion("TICKET_NUM <>", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumGreaterThan(String value) {
            addCriterion("TICKET_NUM >", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumGreaterThanOrEqualTo(String value) {
            addCriterion("TICKET_NUM >=", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumLessThan(String value) {
            addCriterion("TICKET_NUM <", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumLessThanOrEqualTo(String value) {
            addCriterion("TICKET_NUM <=", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumLike(String value) {
            addCriterion("TICKET_NUM like", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumNotLike(String value) {
            addCriterion("TICKET_NUM not like", value, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumIn(List<String> values) {
            addCriterion("TICKET_NUM in", values, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumNotIn(List<String> values) {
            addCriterion("TICKET_NUM not in", values, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumBetween(String value1, String value2) {
            addCriterion("TICKET_NUM between", value1, value2, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketNumNotBetween(String value1, String value2) {
            addCriterion("TICKET_NUM not between", value1, value2, "ticketNum");
            return (Criteria) this;
        }

        public Criteria andTicketPrintFlagIsNull() {
            addCriterion("TICKET_PRINT_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andTicketPrintFlagIsNotNull() {
            addCriterion("TICKET_PRINT_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andTicketPrintFlagEqualTo(String value) {
            addCriterion("TICKET_PRINT_FLAG =", value, "ticketPrintFlag");
            return (Criteria) this;
        }

        public Criteria andTicketPrintFlagNotEqualTo(String value) {
            addCriterion("TICKET_PRINT_FLAG <>", value, "ticketPrintFlag");
            return (Criteria) this;
        }

        public Criteria andTicketPrintFlagGreaterThan(String value) {
            addCriterion("TICKET_PRINT_FLAG >", value, "ticketPrintFlag");
            return (Criteria) this;
        }

        public Criteria andTicketPrintFlagGreaterThanOrEqualTo(String value) {
            addCriterion("TICKET_PRINT_FLAG >=", value, "ticketPrintFlag");
            return (Criteria) this;
        }

        public Criteria andTicketPrintFlagLessThan(String value) {
            addCriterion("TICKET_PRINT_FLAG <", value, "ticketPrintFlag");
            return (Criteria) this;
        }

        public Criteria andTicketPrintFlagLessThanOrEqualTo(String value) {
            addCriterion("TICKET_PRINT_FLAG <=", value, "ticketPrintFlag");
            return (Criteria) this;
        }

        public Criteria andTicketPrintFlagLike(String value) {
            addCriterion("TICKET_PRINT_FLAG like", value, "ticketPrintFlag");
            return (Criteria) this;
        }

        public Criteria andTicketPrintFlagNotLike(String value) {
            addCriterion("TICKET_PRINT_FLAG not like", value, "ticketPrintFlag");
            return (Criteria) this;
        }

        public Criteria andTicketPrintFlagIn(List<String> values) {
            addCriterion("TICKET_PRINT_FLAG in", values, "ticketPrintFlag");
            return (Criteria) this;
        }

        public Criteria andTicketPrintFlagNotIn(List<String> values) {
            addCriterion("TICKET_PRINT_FLAG not in", values, "ticketPrintFlag");
            return (Criteria) this;
        }

        public Criteria andTicketPrintFlagBetween(String value1, String value2) {
            addCriterion("TICKET_PRINT_FLAG between", value1, value2, "ticketPrintFlag");
            return (Criteria) this;
        }

        public Criteria andTicketPrintFlagNotBetween(String value1, String value2) {
            addCriterion("TICKET_PRINT_FLAG not between", value1, value2, "ticketPrintFlag");
            return (Criteria) this;
        }

        public Criteria andExamResultIsNull() {
            addCriterion("EXAM_RESULT is null");
            return (Criteria) this;
        }

        public Criteria andExamResultIsNotNull() {
            addCriterion("EXAM_RESULT is not null");
            return (Criteria) this;
        }

        public Criteria andExamResultEqualTo(BigDecimal value) {
            addCriterion("EXAM_RESULT =", value, "examResult");
            return (Criteria) this;
        }

        public Criteria andExamResultNotEqualTo(BigDecimal value) {
            addCriterion("EXAM_RESULT <>", value, "examResult");
            return (Criteria) this;
        }

        public Criteria andExamResultGreaterThan(BigDecimal value) {
            addCriterion("EXAM_RESULT >", value, "examResult");
            return (Criteria) this;
        }

        public Criteria andExamResultGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("EXAM_RESULT >=", value, "examResult");
            return (Criteria) this;
        }

        public Criteria andExamResultLessThan(BigDecimal value) {
            addCriterion("EXAM_RESULT <", value, "examResult");
            return (Criteria) this;
        }

        public Criteria andExamResultLessThanOrEqualTo(BigDecimal value) {
            addCriterion("EXAM_RESULT <=", value, "examResult");
            return (Criteria) this;
        }

        public Criteria andExamResultIn(List<BigDecimal> values) {
            addCriterion("EXAM_RESULT in", values, "examResult");
            return (Criteria) this;
        }

        public Criteria andExamResultNotIn(List<BigDecimal> values) {
            addCriterion("EXAM_RESULT not in", values, "examResult");
            return (Criteria) this;
        }

        public Criteria andExamResultBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("EXAM_RESULT between", value1, value2, "examResult");
            return (Criteria) this;
        }

        public Criteria andExamResultNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("EXAM_RESULT not between", value1, value2, "examResult");
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

        public Criteria andRoomFlowIsNull() {
            addCriterion("ROOM_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRoomFlowIsNotNull() {
            addCriterion("ROOM_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRoomFlowEqualTo(String value) {
            addCriterion("ROOM_FLOW =", value, "roomFlow");
            return (Criteria) this;
        }

        public Criteria andRoomFlowNotEqualTo(String value) {
            addCriterion("ROOM_FLOW <>", value, "roomFlow");
            return (Criteria) this;
        }

        public Criteria andRoomFlowGreaterThan(String value) {
            addCriterion("ROOM_FLOW >", value, "roomFlow");
            return (Criteria) this;
        }

        public Criteria andRoomFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ROOM_FLOW >=", value, "roomFlow");
            return (Criteria) this;
        }

        public Criteria andRoomFlowLessThan(String value) {
            addCriterion("ROOM_FLOW <", value, "roomFlow");
            return (Criteria) this;
        }

        public Criteria andRoomFlowLessThanOrEqualTo(String value) {
            addCriterion("ROOM_FLOW <=", value, "roomFlow");
            return (Criteria) this;
        }

        public Criteria andRoomFlowLike(String value) {
            addCriterion("ROOM_FLOW like", value, "roomFlow");
            return (Criteria) this;
        }

        public Criteria andRoomFlowNotLike(String value) {
            addCriterion("ROOM_FLOW not like", value, "roomFlow");
            return (Criteria) this;
        }

        public Criteria andRoomFlowIn(List<String> values) {
            addCriterion("ROOM_FLOW in", values, "roomFlow");
            return (Criteria) this;
        }

        public Criteria andRoomFlowNotIn(List<String> values) {
            addCriterion("ROOM_FLOW not in", values, "roomFlow");
            return (Criteria) this;
        }

        public Criteria andRoomFlowBetween(String value1, String value2) {
            addCriterion("ROOM_FLOW between", value1, value2, "roomFlow");
            return (Criteria) this;
        }

        public Criteria andRoomFlowNotBetween(String value1, String value2) {
            addCriterion("ROOM_FLOW not between", value1, value2, "roomFlow");
            return (Criteria) this;
        }

        public Criteria andRoomCodeIsNull() {
            addCriterion("ROOM_CODE is null");
            return (Criteria) this;
        }

        public Criteria andRoomCodeIsNotNull() {
            addCriterion("ROOM_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andRoomCodeEqualTo(String value) {
            addCriterion("ROOM_CODE =", value, "roomCode");
            return (Criteria) this;
        }

        public Criteria andRoomCodeNotEqualTo(String value) {
            addCriterion("ROOM_CODE <>", value, "roomCode");
            return (Criteria) this;
        }

        public Criteria andRoomCodeGreaterThan(String value) {
            addCriterion("ROOM_CODE >", value, "roomCode");
            return (Criteria) this;
        }

        public Criteria andRoomCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ROOM_CODE >=", value, "roomCode");
            return (Criteria) this;
        }

        public Criteria andRoomCodeLessThan(String value) {
            addCriterion("ROOM_CODE <", value, "roomCode");
            return (Criteria) this;
        }

        public Criteria andRoomCodeLessThanOrEqualTo(String value) {
            addCriterion("ROOM_CODE <=", value, "roomCode");
            return (Criteria) this;
        }

        public Criteria andRoomCodeLike(String value) {
            addCriterion("ROOM_CODE like", value, "roomCode");
            return (Criteria) this;
        }

        public Criteria andRoomCodeNotLike(String value) {
            addCriterion("ROOM_CODE not like", value, "roomCode");
            return (Criteria) this;
        }

        public Criteria andRoomCodeIn(List<String> values) {
            addCriterion("ROOM_CODE in", values, "roomCode");
            return (Criteria) this;
        }

        public Criteria andRoomCodeNotIn(List<String> values) {
            addCriterion("ROOM_CODE not in", values, "roomCode");
            return (Criteria) this;
        }

        public Criteria andRoomCodeBetween(String value1, String value2) {
            addCriterion("ROOM_CODE between", value1, value2, "roomCode");
            return (Criteria) this;
        }

        public Criteria andRoomCodeNotBetween(String value1, String value2) {
            addCriterion("ROOM_CODE not between", value1, value2, "roomCode");
            return (Criteria) this;
        }

        public Criteria andSiteNameIsNull() {
            addCriterion("SITE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSiteNameIsNotNull() {
            addCriterion("SITE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSiteNameEqualTo(String value) {
            addCriterion("SITE_NAME =", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameNotEqualTo(String value) {
            addCriterion("SITE_NAME <>", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameGreaterThan(String value) {
            addCriterion("SITE_NAME >", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_NAME >=", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameLessThan(String value) {
            addCriterion("SITE_NAME <", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameLessThanOrEqualTo(String value) {
            addCriterion("SITE_NAME <=", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameLike(String value) {
            addCriterion("SITE_NAME like", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameNotLike(String value) {
            addCriterion("SITE_NAME not like", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameIn(List<String> values) {
            addCriterion("SITE_NAME in", values, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameNotIn(List<String> values) {
            addCriterion("SITE_NAME not in", values, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameBetween(String value1, String value2) {
            addCriterion("SITE_NAME between", value1, value2, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameNotBetween(String value1, String value2) {
            addCriterion("SITE_NAME not between", value1, value2, "siteName");
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