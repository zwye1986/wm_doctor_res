package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ErpCrmCustomerVisitExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ErpCrmCustomerVisitExample() {
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

        public Criteria andVisitFlowIsNull() {
            addCriterion("VISIT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andVisitFlowIsNotNull() {
            addCriterion("VISIT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andVisitFlowEqualTo(String value) {
            addCriterion("VISIT_FLOW =", value, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowNotEqualTo(String value) {
            addCriterion("VISIT_FLOW <>", value, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowGreaterThan(String value) {
            addCriterion("VISIT_FLOW >", value, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowGreaterThanOrEqualTo(String value) {
            addCriterion("VISIT_FLOW >=", value, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowLessThan(String value) {
            addCriterion("VISIT_FLOW <", value, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowLessThanOrEqualTo(String value) {
            addCriterion("VISIT_FLOW <=", value, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowLike(String value) {
            addCriterion("VISIT_FLOW like", value, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowNotLike(String value) {
            addCriterion("VISIT_FLOW not like", value, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowIn(List<String> values) {
            addCriterion("VISIT_FLOW in", values, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowNotIn(List<String> values) {
            addCriterion("VISIT_FLOW not in", values, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowBetween(String value1, String value2) {
            addCriterion("VISIT_FLOW between", value1, value2, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitFlowNotBetween(String value1, String value2) {
            addCriterion("VISIT_FLOW not between", value1, value2, "visitFlow");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIsNull() {
            addCriterion("VISIT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIsNotNull() {
            addCriterion("VISIT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andVisitTypeEqualTo(String value) {
            addCriterion("VISIT_TYPE =", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNotEqualTo(String value) {
            addCriterion("VISIT_TYPE <>", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeGreaterThan(String value) {
            addCriterion("VISIT_TYPE >", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeGreaterThanOrEqualTo(String value) {
            addCriterion("VISIT_TYPE >=", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeLessThan(String value) {
            addCriterion("VISIT_TYPE <", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeLessThanOrEqualTo(String value) {
            addCriterion("VISIT_TYPE <=", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeLike(String value) {
            addCriterion("VISIT_TYPE like", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNotLike(String value) {
            addCriterion("VISIT_TYPE not like", value, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeIn(List<String> values) {
            addCriterion("VISIT_TYPE in", values, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNotIn(List<String> values) {
            addCriterion("VISIT_TYPE not in", values, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeBetween(String value1, String value2) {
            addCriterion("VISIT_TYPE between", value1, value2, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitTypeNotBetween(String value1, String value2) {
            addCriterion("VISIT_TYPE not between", value1, value2, "visitType");
            return (Criteria) this;
        }

        public Criteria andVisitRefFlowIsNull() {
            addCriterion("VISIT_REF_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andVisitRefFlowIsNotNull() {
            addCriterion("VISIT_REF_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andVisitRefFlowEqualTo(String value) {
            addCriterion("VISIT_REF_FLOW =", value, "visitRefFlow");
            return (Criteria) this;
        }

        public Criteria andVisitRefFlowNotEqualTo(String value) {
            addCriterion("VISIT_REF_FLOW <>", value, "visitRefFlow");
            return (Criteria) this;
        }

        public Criteria andVisitRefFlowGreaterThan(String value) {
            addCriterion("VISIT_REF_FLOW >", value, "visitRefFlow");
            return (Criteria) this;
        }

        public Criteria andVisitRefFlowGreaterThanOrEqualTo(String value) {
            addCriterion("VISIT_REF_FLOW >=", value, "visitRefFlow");
            return (Criteria) this;
        }

        public Criteria andVisitRefFlowLessThan(String value) {
            addCriterion("VISIT_REF_FLOW <", value, "visitRefFlow");
            return (Criteria) this;
        }

        public Criteria andVisitRefFlowLessThanOrEqualTo(String value) {
            addCriterion("VISIT_REF_FLOW <=", value, "visitRefFlow");
            return (Criteria) this;
        }

        public Criteria andVisitRefFlowLike(String value) {
            addCriterion("VISIT_REF_FLOW like", value, "visitRefFlow");
            return (Criteria) this;
        }

        public Criteria andVisitRefFlowNotLike(String value) {
            addCriterion("VISIT_REF_FLOW not like", value, "visitRefFlow");
            return (Criteria) this;
        }

        public Criteria andVisitRefFlowIn(List<String> values) {
            addCriterion("VISIT_REF_FLOW in", values, "visitRefFlow");
            return (Criteria) this;
        }

        public Criteria andVisitRefFlowNotIn(List<String> values) {
            addCriterion("VISIT_REF_FLOW not in", values, "visitRefFlow");
            return (Criteria) this;
        }

        public Criteria andVisitRefFlowBetween(String value1, String value2) {
            addCriterion("VISIT_REF_FLOW between", value1, value2, "visitRefFlow");
            return (Criteria) this;
        }

        public Criteria andVisitRefFlowNotBetween(String value1, String value2) {
            addCriterion("VISIT_REF_FLOW not between", value1, value2, "visitRefFlow");
            return (Criteria) this;
        }

        public Criteria andVisitTimeIsNull() {
            addCriterion("VISIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andVisitTimeIsNotNull() {
            addCriterion("VISIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andVisitTimeEqualTo(String value) {
            addCriterion("VISIT_TIME =", value, "visitTime");
            return (Criteria) this;
        }

        public Criteria andVisitTimeNotEqualTo(String value) {
            addCriterion("VISIT_TIME <>", value, "visitTime");
            return (Criteria) this;
        }

        public Criteria andVisitTimeGreaterThan(String value) {
            addCriterion("VISIT_TIME >", value, "visitTime");
            return (Criteria) this;
        }

        public Criteria andVisitTimeGreaterThanOrEqualTo(String value) {
            addCriterion("VISIT_TIME >=", value, "visitTime");
            return (Criteria) this;
        }

        public Criteria andVisitTimeLessThan(String value) {
            addCriterion("VISIT_TIME <", value, "visitTime");
            return (Criteria) this;
        }

        public Criteria andVisitTimeLessThanOrEqualTo(String value) {
            addCriterion("VISIT_TIME <=", value, "visitTime");
            return (Criteria) this;
        }

        public Criteria andVisitTimeLike(String value) {
            addCriterion("VISIT_TIME like", value, "visitTime");
            return (Criteria) this;
        }

        public Criteria andVisitTimeNotLike(String value) {
            addCriterion("VISIT_TIME not like", value, "visitTime");
            return (Criteria) this;
        }

        public Criteria andVisitTimeIn(List<String> values) {
            addCriterion("VISIT_TIME in", values, "visitTime");
            return (Criteria) this;
        }

        public Criteria andVisitTimeNotIn(List<String> values) {
            addCriterion("VISIT_TIME not in", values, "visitTime");
            return (Criteria) this;
        }

        public Criteria andVisitTimeBetween(String value1, String value2) {
            addCriterion("VISIT_TIME between", value1, value2, "visitTime");
            return (Criteria) this;
        }

        public Criteria andVisitTimeNotBetween(String value1, String value2) {
            addCriterion("VISIT_TIME not between", value1, value2, "visitTime");
            return (Criteria) this;
        }

        public Criteria andVisitSubjectIsNull() {
            addCriterion("VISIT_SUBJECT is null");
            return (Criteria) this;
        }

        public Criteria andVisitSubjectIsNotNull() {
            addCriterion("VISIT_SUBJECT is not null");
            return (Criteria) this;
        }

        public Criteria andVisitSubjectEqualTo(String value) {
            addCriterion("VISIT_SUBJECT =", value, "visitSubject");
            return (Criteria) this;
        }

        public Criteria andVisitSubjectNotEqualTo(String value) {
            addCriterion("VISIT_SUBJECT <>", value, "visitSubject");
            return (Criteria) this;
        }

        public Criteria andVisitSubjectGreaterThan(String value) {
            addCriterion("VISIT_SUBJECT >", value, "visitSubject");
            return (Criteria) this;
        }

        public Criteria andVisitSubjectGreaterThanOrEqualTo(String value) {
            addCriterion("VISIT_SUBJECT >=", value, "visitSubject");
            return (Criteria) this;
        }

        public Criteria andVisitSubjectLessThan(String value) {
            addCriterion("VISIT_SUBJECT <", value, "visitSubject");
            return (Criteria) this;
        }

        public Criteria andVisitSubjectLessThanOrEqualTo(String value) {
            addCriterion("VISIT_SUBJECT <=", value, "visitSubject");
            return (Criteria) this;
        }

        public Criteria andVisitSubjectLike(String value) {
            addCriterion("VISIT_SUBJECT like", value, "visitSubject");
            return (Criteria) this;
        }

        public Criteria andVisitSubjectNotLike(String value) {
            addCriterion("VISIT_SUBJECT not like", value, "visitSubject");
            return (Criteria) this;
        }

        public Criteria andVisitSubjectIn(List<String> values) {
            addCriterion("VISIT_SUBJECT in", values, "visitSubject");
            return (Criteria) this;
        }

        public Criteria andVisitSubjectNotIn(List<String> values) {
            addCriterion("VISIT_SUBJECT not in", values, "visitSubject");
            return (Criteria) this;
        }

        public Criteria andVisitSubjectBetween(String value1, String value2) {
            addCriterion("VISIT_SUBJECT between", value1, value2, "visitSubject");
            return (Criteria) this;
        }

        public Criteria andVisitSubjectNotBetween(String value1, String value2) {
            addCriterion("VISIT_SUBJECT not between", value1, value2, "visitSubject");
            return (Criteria) this;
        }

        public Criteria andVisitContentIsNull() {
            addCriterion("VISIT_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andVisitContentIsNotNull() {
            addCriterion("VISIT_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andVisitContentEqualTo(String value) {
            addCriterion("VISIT_CONTENT =", value, "visitContent");
            return (Criteria) this;
        }

        public Criteria andVisitContentNotEqualTo(String value) {
            addCriterion("VISIT_CONTENT <>", value, "visitContent");
            return (Criteria) this;
        }

        public Criteria andVisitContentGreaterThan(String value) {
            addCriterion("VISIT_CONTENT >", value, "visitContent");
            return (Criteria) this;
        }

        public Criteria andVisitContentGreaterThanOrEqualTo(String value) {
            addCriterion("VISIT_CONTENT >=", value, "visitContent");
            return (Criteria) this;
        }

        public Criteria andVisitContentLessThan(String value) {
            addCriterion("VISIT_CONTENT <", value, "visitContent");
            return (Criteria) this;
        }

        public Criteria andVisitContentLessThanOrEqualTo(String value) {
            addCriterion("VISIT_CONTENT <=", value, "visitContent");
            return (Criteria) this;
        }

        public Criteria andVisitContentLike(String value) {
            addCriterion("VISIT_CONTENT like", value, "visitContent");
            return (Criteria) this;
        }

        public Criteria andVisitContentNotLike(String value) {
            addCriterion("VISIT_CONTENT not like", value, "visitContent");
            return (Criteria) this;
        }

        public Criteria andVisitContentIn(List<String> values) {
            addCriterion("VISIT_CONTENT in", values, "visitContent");
            return (Criteria) this;
        }

        public Criteria andVisitContentNotIn(List<String> values) {
            addCriterion("VISIT_CONTENT not in", values, "visitContent");
            return (Criteria) this;
        }

        public Criteria andVisitContentBetween(String value1, String value2) {
            addCriterion("VISIT_CONTENT between", value1, value2, "visitContent");
            return (Criteria) this;
        }

        public Criteria andVisitContentNotBetween(String value1, String value2) {
            addCriterion("VISIT_CONTENT not between", value1, value2, "visitContent");
            return (Criteria) this;
        }

        public Criteria andCustUserFlowIsNull() {
            addCriterion("CUST_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andCustUserFlowIsNotNull() {
            addCriterion("CUST_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andCustUserFlowEqualTo(String value) {
            addCriterion("CUST_USER_FLOW =", value, "custUserFlow");
            return (Criteria) this;
        }

        public Criteria andCustUserFlowNotEqualTo(String value) {
            addCriterion("CUST_USER_FLOW <>", value, "custUserFlow");
            return (Criteria) this;
        }

        public Criteria andCustUserFlowGreaterThan(String value) {
            addCriterion("CUST_USER_FLOW >", value, "custUserFlow");
            return (Criteria) this;
        }

        public Criteria andCustUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_USER_FLOW >=", value, "custUserFlow");
            return (Criteria) this;
        }

        public Criteria andCustUserFlowLessThan(String value) {
            addCriterion("CUST_USER_FLOW <", value, "custUserFlow");
            return (Criteria) this;
        }

        public Criteria andCustUserFlowLessThanOrEqualTo(String value) {
            addCriterion("CUST_USER_FLOW <=", value, "custUserFlow");
            return (Criteria) this;
        }

        public Criteria andCustUserFlowLike(String value) {
            addCriterion("CUST_USER_FLOW like", value, "custUserFlow");
            return (Criteria) this;
        }

        public Criteria andCustUserFlowNotLike(String value) {
            addCriterion("CUST_USER_FLOW not like", value, "custUserFlow");
            return (Criteria) this;
        }

        public Criteria andCustUserFlowIn(List<String> values) {
            addCriterion("CUST_USER_FLOW in", values, "custUserFlow");
            return (Criteria) this;
        }

        public Criteria andCustUserFlowNotIn(List<String> values) {
            addCriterion("CUST_USER_FLOW not in", values, "custUserFlow");
            return (Criteria) this;
        }

        public Criteria andCustUserFlowBetween(String value1, String value2) {
            addCriterion("CUST_USER_FLOW between", value1, value2, "custUserFlow");
            return (Criteria) this;
        }

        public Criteria andCustUserFlowNotBetween(String value1, String value2) {
            addCriterion("CUST_USER_FLOW not between", value1, value2, "custUserFlow");
            return (Criteria) this;
        }

        public Criteria andCustUserNameIsNull() {
            addCriterion("CUST_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCustUserNameIsNotNull() {
            addCriterion("CUST_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCustUserNameEqualTo(String value) {
            addCriterion("CUST_USER_NAME =", value, "custUserName");
            return (Criteria) this;
        }

        public Criteria andCustUserNameNotEqualTo(String value) {
            addCriterion("CUST_USER_NAME <>", value, "custUserName");
            return (Criteria) this;
        }

        public Criteria andCustUserNameGreaterThan(String value) {
            addCriterion("CUST_USER_NAME >", value, "custUserName");
            return (Criteria) this;
        }

        public Criteria andCustUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_USER_NAME >=", value, "custUserName");
            return (Criteria) this;
        }

        public Criteria andCustUserNameLessThan(String value) {
            addCriterion("CUST_USER_NAME <", value, "custUserName");
            return (Criteria) this;
        }

        public Criteria andCustUserNameLessThanOrEqualTo(String value) {
            addCriterion("CUST_USER_NAME <=", value, "custUserName");
            return (Criteria) this;
        }

        public Criteria andCustUserNameLike(String value) {
            addCriterion("CUST_USER_NAME like", value, "custUserName");
            return (Criteria) this;
        }

        public Criteria andCustUserNameNotLike(String value) {
            addCriterion("CUST_USER_NAME not like", value, "custUserName");
            return (Criteria) this;
        }

        public Criteria andCustUserNameIn(List<String> values) {
            addCriterion("CUST_USER_NAME in", values, "custUserName");
            return (Criteria) this;
        }

        public Criteria andCustUserNameNotIn(List<String> values) {
            addCriterion("CUST_USER_NAME not in", values, "custUserName");
            return (Criteria) this;
        }

        public Criteria andCustUserNameBetween(String value1, String value2) {
            addCriterion("CUST_USER_NAME between", value1, value2, "custUserName");
            return (Criteria) this;
        }

        public Criteria andCustUserNameNotBetween(String value1, String value2) {
            addCriterion("CUST_USER_NAME not between", value1, value2, "custUserName");
            return (Criteria) this;
        }

        public Criteria andCustUserPhoneIsNull() {
            addCriterion("CUST_USER_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andCustUserPhoneIsNotNull() {
            addCriterion("CUST_USER_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andCustUserPhoneEqualTo(String value) {
            addCriterion("CUST_USER_PHONE =", value, "custUserPhone");
            return (Criteria) this;
        }

        public Criteria andCustUserPhoneNotEqualTo(String value) {
            addCriterion("CUST_USER_PHONE <>", value, "custUserPhone");
            return (Criteria) this;
        }

        public Criteria andCustUserPhoneGreaterThan(String value) {
            addCriterion("CUST_USER_PHONE >", value, "custUserPhone");
            return (Criteria) this;
        }

        public Criteria andCustUserPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_USER_PHONE >=", value, "custUserPhone");
            return (Criteria) this;
        }

        public Criteria andCustUserPhoneLessThan(String value) {
            addCriterion("CUST_USER_PHONE <", value, "custUserPhone");
            return (Criteria) this;
        }

        public Criteria andCustUserPhoneLessThanOrEqualTo(String value) {
            addCriterion("CUST_USER_PHONE <=", value, "custUserPhone");
            return (Criteria) this;
        }

        public Criteria andCustUserPhoneLike(String value) {
            addCriterion("CUST_USER_PHONE like", value, "custUserPhone");
            return (Criteria) this;
        }

        public Criteria andCustUserPhoneNotLike(String value) {
            addCriterion("CUST_USER_PHONE not like", value, "custUserPhone");
            return (Criteria) this;
        }

        public Criteria andCustUserPhoneIn(List<String> values) {
            addCriterion("CUST_USER_PHONE in", values, "custUserPhone");
            return (Criteria) this;
        }

        public Criteria andCustUserPhoneNotIn(List<String> values) {
            addCriterion("CUST_USER_PHONE not in", values, "custUserPhone");
            return (Criteria) this;
        }

        public Criteria andCustUserPhoneBetween(String value1, String value2) {
            addCriterion("CUST_USER_PHONE between", value1, value2, "custUserPhone");
            return (Criteria) this;
        }

        public Criteria andCustUserPhoneNotBetween(String value1, String value2) {
            addCriterion("CUST_USER_PHONE not between", value1, value2, "custUserPhone");
            return (Criteria) this;
        }

        public Criteria andVisitUserFlowIsNull() {
            addCriterion("VISIT_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andVisitUserFlowIsNotNull() {
            addCriterion("VISIT_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andVisitUserFlowEqualTo(String value) {
            addCriterion("VISIT_USER_FLOW =", value, "visitUserFlow");
            return (Criteria) this;
        }

        public Criteria andVisitUserFlowNotEqualTo(String value) {
            addCriterion("VISIT_USER_FLOW <>", value, "visitUserFlow");
            return (Criteria) this;
        }

        public Criteria andVisitUserFlowGreaterThan(String value) {
            addCriterion("VISIT_USER_FLOW >", value, "visitUserFlow");
            return (Criteria) this;
        }

        public Criteria andVisitUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("VISIT_USER_FLOW >=", value, "visitUserFlow");
            return (Criteria) this;
        }

        public Criteria andVisitUserFlowLessThan(String value) {
            addCriterion("VISIT_USER_FLOW <", value, "visitUserFlow");
            return (Criteria) this;
        }

        public Criteria andVisitUserFlowLessThanOrEqualTo(String value) {
            addCriterion("VISIT_USER_FLOW <=", value, "visitUserFlow");
            return (Criteria) this;
        }

        public Criteria andVisitUserFlowLike(String value) {
            addCriterion("VISIT_USER_FLOW like", value, "visitUserFlow");
            return (Criteria) this;
        }

        public Criteria andVisitUserFlowNotLike(String value) {
            addCriterion("VISIT_USER_FLOW not like", value, "visitUserFlow");
            return (Criteria) this;
        }

        public Criteria andVisitUserFlowIn(List<String> values) {
            addCriterion("VISIT_USER_FLOW in", values, "visitUserFlow");
            return (Criteria) this;
        }

        public Criteria andVisitUserFlowNotIn(List<String> values) {
            addCriterion("VISIT_USER_FLOW not in", values, "visitUserFlow");
            return (Criteria) this;
        }

        public Criteria andVisitUserFlowBetween(String value1, String value2) {
            addCriterion("VISIT_USER_FLOW between", value1, value2, "visitUserFlow");
            return (Criteria) this;
        }

        public Criteria andVisitUserFlowNotBetween(String value1, String value2) {
            addCriterion("VISIT_USER_FLOW not between", value1, value2, "visitUserFlow");
            return (Criteria) this;
        }

        public Criteria andVisitUserNameIsNull() {
            addCriterion("VISIT_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andVisitUserNameIsNotNull() {
            addCriterion("VISIT_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andVisitUserNameEqualTo(String value) {
            addCriterion("VISIT_USER_NAME =", value, "visitUserName");
            return (Criteria) this;
        }

        public Criteria andVisitUserNameNotEqualTo(String value) {
            addCriterion("VISIT_USER_NAME <>", value, "visitUserName");
            return (Criteria) this;
        }

        public Criteria andVisitUserNameGreaterThan(String value) {
            addCriterion("VISIT_USER_NAME >", value, "visitUserName");
            return (Criteria) this;
        }

        public Criteria andVisitUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("VISIT_USER_NAME >=", value, "visitUserName");
            return (Criteria) this;
        }

        public Criteria andVisitUserNameLessThan(String value) {
            addCriterion("VISIT_USER_NAME <", value, "visitUserName");
            return (Criteria) this;
        }

        public Criteria andVisitUserNameLessThanOrEqualTo(String value) {
            addCriterion("VISIT_USER_NAME <=", value, "visitUserName");
            return (Criteria) this;
        }

        public Criteria andVisitUserNameLike(String value) {
            addCriterion("VISIT_USER_NAME like", value, "visitUserName");
            return (Criteria) this;
        }

        public Criteria andVisitUserNameNotLike(String value) {
            addCriterion("VISIT_USER_NAME not like", value, "visitUserName");
            return (Criteria) this;
        }

        public Criteria andVisitUserNameIn(List<String> values) {
            addCriterion("VISIT_USER_NAME in", values, "visitUserName");
            return (Criteria) this;
        }

        public Criteria andVisitUserNameNotIn(List<String> values) {
            addCriterion("VISIT_USER_NAME not in", values, "visitUserName");
            return (Criteria) this;
        }

        public Criteria andVisitUserNameBetween(String value1, String value2) {
            addCriterion("VISIT_USER_NAME between", value1, value2, "visitUserName");
            return (Criteria) this;
        }

        public Criteria andVisitUserNameNotBetween(String value1, String value2) {
            addCriterion("VISIT_USER_NAME not between", value1, value2, "visitUserName");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
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