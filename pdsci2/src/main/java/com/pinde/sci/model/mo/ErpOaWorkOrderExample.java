package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ErpOaWorkOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ErpOaWorkOrderExample() {
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

        public Criteria andWorkFlowIsNull() {
            addCriterion("WORK_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andWorkFlowIsNotNull() {
            addCriterion("WORK_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andWorkFlowEqualTo(String value) {
            addCriterion("WORK_FLOW =", value, "workFlow");
            return (Criteria) this;
        }

        public Criteria andWorkFlowNotEqualTo(String value) {
            addCriterion("WORK_FLOW <>", value, "workFlow");
            return (Criteria) this;
        }

        public Criteria andWorkFlowGreaterThan(String value) {
            addCriterion("WORK_FLOW >", value, "workFlow");
            return (Criteria) this;
        }

        public Criteria andWorkFlowGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_FLOW >=", value, "workFlow");
            return (Criteria) this;
        }

        public Criteria andWorkFlowLessThan(String value) {
            addCriterion("WORK_FLOW <", value, "workFlow");
            return (Criteria) this;
        }

        public Criteria andWorkFlowLessThanOrEqualTo(String value) {
            addCriterion("WORK_FLOW <=", value, "workFlow");
            return (Criteria) this;
        }

        public Criteria andWorkFlowLike(String value) {
            addCriterion("WORK_FLOW like", value, "workFlow");
            return (Criteria) this;
        }

        public Criteria andWorkFlowNotLike(String value) {
            addCriterion("WORK_FLOW not like", value, "workFlow");
            return (Criteria) this;
        }

        public Criteria andWorkFlowIn(List<String> values) {
            addCriterion("WORK_FLOW in", values, "workFlow");
            return (Criteria) this;
        }

        public Criteria andWorkFlowNotIn(List<String> values) {
            addCriterion("WORK_FLOW not in", values, "workFlow");
            return (Criteria) this;
        }

        public Criteria andWorkFlowBetween(String value1, String value2) {
            addCriterion("WORK_FLOW between", value1, value2, "workFlow");
            return (Criteria) this;
        }

        public Criteria andWorkFlowNotBetween(String value1, String value2) {
            addCriterion("WORK_FLOW not between", value1, value2, "workFlow");
            return (Criteria) this;
        }

        public Criteria andWorkNoIsNull() {
            addCriterion("WORK_NO is null");
            return (Criteria) this;
        }

        public Criteria andWorkNoIsNotNull() {
            addCriterion("WORK_NO is not null");
            return (Criteria) this;
        }

        public Criteria andWorkNoEqualTo(String value) {
            addCriterion("WORK_NO =", value, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoNotEqualTo(String value) {
            addCriterion("WORK_NO <>", value, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoGreaterThan(String value) {
            addCriterion("WORK_NO >", value, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_NO >=", value, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoLessThan(String value) {
            addCriterion("WORK_NO <", value, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoLessThanOrEqualTo(String value) {
            addCriterion("WORK_NO <=", value, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoLike(String value) {
            addCriterion("WORK_NO like", value, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoNotLike(String value) {
            addCriterion("WORK_NO not like", value, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoIn(List<String> values) {
            addCriterion("WORK_NO in", values, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoNotIn(List<String> values) {
            addCriterion("WORK_NO not in", values, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoBetween(String value1, String value2) {
            addCriterion("WORK_NO between", value1, value2, "workNo");
            return (Criteria) this;
        }

        public Criteria andWorkNoNotBetween(String value1, String value2) {
            addCriterion("WORK_NO not between", value1, value2, "workNo");
            return (Criteria) this;
        }

        public Criteria andContactFlowIsNull() {
            addCriterion("CONTACT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andContactFlowIsNotNull() {
            addCriterion("CONTACT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andContactFlowEqualTo(String value) {
            addCriterion("CONTACT_FLOW =", value, "contactFlow");
            return (Criteria) this;
        }

        public Criteria andContactFlowNotEqualTo(String value) {
            addCriterion("CONTACT_FLOW <>", value, "contactFlow");
            return (Criteria) this;
        }

        public Criteria andContactFlowGreaterThan(String value) {
            addCriterion("CONTACT_FLOW >", value, "contactFlow");
            return (Criteria) this;
        }

        public Criteria andContactFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CONTACT_FLOW >=", value, "contactFlow");
            return (Criteria) this;
        }

        public Criteria andContactFlowLessThan(String value) {
            addCriterion("CONTACT_FLOW <", value, "contactFlow");
            return (Criteria) this;
        }

        public Criteria andContactFlowLessThanOrEqualTo(String value) {
            addCriterion("CONTACT_FLOW <=", value, "contactFlow");
            return (Criteria) this;
        }

        public Criteria andContactFlowLike(String value) {
            addCriterion("CONTACT_FLOW like", value, "contactFlow");
            return (Criteria) this;
        }

        public Criteria andContactFlowNotLike(String value) {
            addCriterion("CONTACT_FLOW not like", value, "contactFlow");
            return (Criteria) this;
        }

        public Criteria andContactFlowIn(List<String> values) {
            addCriterion("CONTACT_FLOW in", values, "contactFlow");
            return (Criteria) this;
        }

        public Criteria andContactFlowNotIn(List<String> values) {
            addCriterion("CONTACT_FLOW not in", values, "contactFlow");
            return (Criteria) this;
        }

        public Criteria andContactFlowBetween(String value1, String value2) {
            addCriterion("CONTACT_FLOW between", value1, value2, "contactFlow");
            return (Criteria) this;
        }

        public Criteria andContactFlowNotBetween(String value1, String value2) {
            addCriterion("CONTACT_FLOW not between", value1, value2, "contactFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowIsNull() {
            addCriterion("CUSTOMER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowIsNotNull() {
            addCriterion("CUSTOMER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowEqualTo(String value) {
            addCriterion("CUSTOMER_FLOW =", value, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowNotEqualTo(String value) {
            addCriterion("CUSTOMER_FLOW <>", value, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowGreaterThan(String value) {
            addCriterion("CUSTOMER_FLOW >", value, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_FLOW >=", value, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowLessThan(String value) {
            addCriterion("CUSTOMER_FLOW <", value, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_FLOW <=", value, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowLike(String value) {
            addCriterion("CUSTOMER_FLOW like", value, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowNotLike(String value) {
            addCriterion("CUSTOMER_FLOW not like", value, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowIn(List<String> values) {
            addCriterion("CUSTOMER_FLOW in", values, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowNotIn(List<String> values) {
            addCriterion("CUSTOMER_FLOW not in", values, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowBetween(String value1, String value2) {
            addCriterion("CUSTOMER_FLOW between", value1, value2, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerFlowNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_FLOW not between", value1, value2, "customerFlow");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNull() {
            addCriterion("CUSTOMER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("CUSTOMER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("CUSTOMER_NAME =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("CUSTOMER_NAME <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("CUSTOMER_NAME >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_NAME >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("CUSTOMER_NAME <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("CUSTOMER_NAME <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            addCriterion("CUSTOMER_NAME like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            addCriterion("CUSTOMER_NAME not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("CUSTOMER_NAME in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("CUSTOMER_NAME not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("CUSTOMER_NAME between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("CUSTOMER_NAME not between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andAssignDateIsNull() {
            addCriterion("ASSIGN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andAssignDateIsNotNull() {
            addCriterion("ASSIGN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andAssignDateEqualTo(String value) {
            addCriterion("ASSIGN_DATE =", value, "assignDate");
            return (Criteria) this;
        }

        public Criteria andAssignDateNotEqualTo(String value) {
            addCriterion("ASSIGN_DATE <>", value, "assignDate");
            return (Criteria) this;
        }

        public Criteria andAssignDateGreaterThan(String value) {
            addCriterion("ASSIGN_DATE >", value, "assignDate");
            return (Criteria) this;
        }

        public Criteria andAssignDateGreaterThanOrEqualTo(String value) {
            addCriterion("ASSIGN_DATE >=", value, "assignDate");
            return (Criteria) this;
        }

        public Criteria andAssignDateLessThan(String value) {
            addCriterion("ASSIGN_DATE <", value, "assignDate");
            return (Criteria) this;
        }

        public Criteria andAssignDateLessThanOrEqualTo(String value) {
            addCriterion("ASSIGN_DATE <=", value, "assignDate");
            return (Criteria) this;
        }

        public Criteria andAssignDateLike(String value) {
            addCriterion("ASSIGN_DATE like", value, "assignDate");
            return (Criteria) this;
        }

        public Criteria andAssignDateNotLike(String value) {
            addCriterion("ASSIGN_DATE not like", value, "assignDate");
            return (Criteria) this;
        }

        public Criteria andAssignDateIn(List<String> values) {
            addCriterion("ASSIGN_DATE in", values, "assignDate");
            return (Criteria) this;
        }

        public Criteria andAssignDateNotIn(List<String> values) {
            addCriterion("ASSIGN_DATE not in", values, "assignDate");
            return (Criteria) this;
        }

        public Criteria andAssignDateBetween(String value1, String value2) {
            addCriterion("ASSIGN_DATE between", value1, value2, "assignDate");
            return (Criteria) this;
        }

        public Criteria andAssignDateNotBetween(String value1, String value2) {
            addCriterion("ASSIGN_DATE not between", value1, value2, "assignDate");
            return (Criteria) this;
        }

        public Criteria andAssignDeptFlowIsNull() {
            addCriterion("ASSIGN_DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAssignDeptFlowIsNotNull() {
            addCriterion("ASSIGN_DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAssignDeptFlowEqualTo(String value) {
            addCriterion("ASSIGN_DEPT_FLOW =", value, "assignDeptFlow");
            return (Criteria) this;
        }

        public Criteria andAssignDeptFlowNotEqualTo(String value) {
            addCriterion("ASSIGN_DEPT_FLOW <>", value, "assignDeptFlow");
            return (Criteria) this;
        }

        public Criteria andAssignDeptFlowGreaterThan(String value) {
            addCriterion("ASSIGN_DEPT_FLOW >", value, "assignDeptFlow");
            return (Criteria) this;
        }

        public Criteria andAssignDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ASSIGN_DEPT_FLOW >=", value, "assignDeptFlow");
            return (Criteria) this;
        }

        public Criteria andAssignDeptFlowLessThan(String value) {
            addCriterion("ASSIGN_DEPT_FLOW <", value, "assignDeptFlow");
            return (Criteria) this;
        }

        public Criteria andAssignDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("ASSIGN_DEPT_FLOW <=", value, "assignDeptFlow");
            return (Criteria) this;
        }

        public Criteria andAssignDeptFlowLike(String value) {
            addCriterion("ASSIGN_DEPT_FLOW like", value, "assignDeptFlow");
            return (Criteria) this;
        }

        public Criteria andAssignDeptFlowNotLike(String value) {
            addCriterion("ASSIGN_DEPT_FLOW not like", value, "assignDeptFlow");
            return (Criteria) this;
        }

        public Criteria andAssignDeptFlowIn(List<String> values) {
            addCriterion("ASSIGN_DEPT_FLOW in", values, "assignDeptFlow");
            return (Criteria) this;
        }

        public Criteria andAssignDeptFlowNotIn(List<String> values) {
            addCriterion("ASSIGN_DEPT_FLOW not in", values, "assignDeptFlow");
            return (Criteria) this;
        }

        public Criteria andAssignDeptFlowBetween(String value1, String value2) {
            addCriterion("ASSIGN_DEPT_FLOW between", value1, value2, "assignDeptFlow");
            return (Criteria) this;
        }

        public Criteria andAssignDeptFlowNotBetween(String value1, String value2) {
            addCriterion("ASSIGN_DEPT_FLOW not between", value1, value2, "assignDeptFlow");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowIsNull() {
            addCriterion("ASSIGN_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowIsNotNull() {
            addCriterion("ASSIGN_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowEqualTo(String value) {
            addCriterion("ASSIGN_USER_FLOW =", value, "assignUserFlow");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowNotEqualTo(String value) {
            addCriterion("ASSIGN_USER_FLOW <>", value, "assignUserFlow");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowGreaterThan(String value) {
            addCriterion("ASSIGN_USER_FLOW >", value, "assignUserFlow");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ASSIGN_USER_FLOW >=", value, "assignUserFlow");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowLessThan(String value) {
            addCriterion("ASSIGN_USER_FLOW <", value, "assignUserFlow");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowLessThanOrEqualTo(String value) {
            addCriterion("ASSIGN_USER_FLOW <=", value, "assignUserFlow");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowLike(String value) {
            addCriterion("ASSIGN_USER_FLOW like", value, "assignUserFlow");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowNotLike(String value) {
            addCriterion("ASSIGN_USER_FLOW not like", value, "assignUserFlow");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowIn(List<String> values) {
            addCriterion("ASSIGN_USER_FLOW in", values, "assignUserFlow");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowNotIn(List<String> values) {
            addCriterion("ASSIGN_USER_FLOW not in", values, "assignUserFlow");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowBetween(String value1, String value2) {
            addCriterion("ASSIGN_USER_FLOW between", value1, value2, "assignUserFlow");
            return (Criteria) this;
        }

        public Criteria andAssignUserFlowNotBetween(String value1, String value2) {
            addCriterion("ASSIGN_USER_FLOW not between", value1, value2, "assignUserFlow");
            return (Criteria) this;
        }

        public Criteria andWorkStatusIdIsNull() {
            addCriterion("WORK_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andWorkStatusIdIsNotNull() {
            addCriterion("WORK_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andWorkStatusIdEqualTo(String value) {
            addCriterion("WORK_STATUS_ID =", value, "workStatusId");
            return (Criteria) this;
        }

        public Criteria andWorkStatusIdNotEqualTo(String value) {
            addCriterion("WORK_STATUS_ID <>", value, "workStatusId");
            return (Criteria) this;
        }

        public Criteria andWorkStatusIdGreaterThan(String value) {
            addCriterion("WORK_STATUS_ID >", value, "workStatusId");
            return (Criteria) this;
        }

        public Criteria andWorkStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_STATUS_ID >=", value, "workStatusId");
            return (Criteria) this;
        }

        public Criteria andWorkStatusIdLessThan(String value) {
            addCriterion("WORK_STATUS_ID <", value, "workStatusId");
            return (Criteria) this;
        }

        public Criteria andWorkStatusIdLessThanOrEqualTo(String value) {
            addCriterion("WORK_STATUS_ID <=", value, "workStatusId");
            return (Criteria) this;
        }

        public Criteria andWorkStatusIdLike(String value) {
            addCriterion("WORK_STATUS_ID like", value, "workStatusId");
            return (Criteria) this;
        }

        public Criteria andWorkStatusIdNotLike(String value) {
            addCriterion("WORK_STATUS_ID not like", value, "workStatusId");
            return (Criteria) this;
        }

        public Criteria andWorkStatusIdIn(List<String> values) {
            addCriterion("WORK_STATUS_ID in", values, "workStatusId");
            return (Criteria) this;
        }

        public Criteria andWorkStatusIdNotIn(List<String> values) {
            addCriterion("WORK_STATUS_ID not in", values, "workStatusId");
            return (Criteria) this;
        }

        public Criteria andWorkStatusIdBetween(String value1, String value2) {
            addCriterion("WORK_STATUS_ID between", value1, value2, "workStatusId");
            return (Criteria) this;
        }

        public Criteria andWorkStatusIdNotBetween(String value1, String value2) {
            addCriterion("WORK_STATUS_ID not between", value1, value2, "workStatusId");
            return (Criteria) this;
        }

        public Criteria andWorkStatusNameIsNull() {
            addCriterion("WORK_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andWorkStatusNameIsNotNull() {
            addCriterion("WORK_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andWorkStatusNameEqualTo(String value) {
            addCriterion("WORK_STATUS_NAME =", value, "workStatusName");
            return (Criteria) this;
        }

        public Criteria andWorkStatusNameNotEqualTo(String value) {
            addCriterion("WORK_STATUS_NAME <>", value, "workStatusName");
            return (Criteria) this;
        }

        public Criteria andWorkStatusNameGreaterThan(String value) {
            addCriterion("WORK_STATUS_NAME >", value, "workStatusName");
            return (Criteria) this;
        }

        public Criteria andWorkStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_STATUS_NAME >=", value, "workStatusName");
            return (Criteria) this;
        }

        public Criteria andWorkStatusNameLessThan(String value) {
            addCriterion("WORK_STATUS_NAME <", value, "workStatusName");
            return (Criteria) this;
        }

        public Criteria andWorkStatusNameLessThanOrEqualTo(String value) {
            addCriterion("WORK_STATUS_NAME <=", value, "workStatusName");
            return (Criteria) this;
        }

        public Criteria andWorkStatusNameLike(String value) {
            addCriterion("WORK_STATUS_NAME like", value, "workStatusName");
            return (Criteria) this;
        }

        public Criteria andWorkStatusNameNotLike(String value) {
            addCriterion("WORK_STATUS_NAME not like", value, "workStatusName");
            return (Criteria) this;
        }

        public Criteria andWorkStatusNameIn(List<String> values) {
            addCriterion("WORK_STATUS_NAME in", values, "workStatusName");
            return (Criteria) this;
        }

        public Criteria andWorkStatusNameNotIn(List<String> values) {
            addCriterion("WORK_STATUS_NAME not in", values, "workStatusName");
            return (Criteria) this;
        }

        public Criteria andWorkStatusNameBetween(String value1, String value2) {
            addCriterion("WORK_STATUS_NAME between", value1, value2, "workStatusName");
            return (Criteria) this;
        }

        public Criteria andWorkStatusNameNotBetween(String value1, String value2) {
            addCriterion("WORK_STATUS_NAME not between", value1, value2, "workStatusName");
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

        public Criteria andAssignDeptNameIsNull() {
            addCriterion("ASSIGN_DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAssignDeptNameIsNotNull() {
            addCriterion("ASSIGN_DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAssignDeptNameEqualTo(String value) {
            addCriterion("ASSIGN_DEPT_NAME =", value, "assignDeptName");
            return (Criteria) this;
        }

        public Criteria andAssignDeptNameNotEqualTo(String value) {
            addCriterion("ASSIGN_DEPT_NAME <>", value, "assignDeptName");
            return (Criteria) this;
        }

        public Criteria andAssignDeptNameGreaterThan(String value) {
            addCriterion("ASSIGN_DEPT_NAME >", value, "assignDeptName");
            return (Criteria) this;
        }

        public Criteria andAssignDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("ASSIGN_DEPT_NAME >=", value, "assignDeptName");
            return (Criteria) this;
        }

        public Criteria andAssignDeptNameLessThan(String value) {
            addCriterion("ASSIGN_DEPT_NAME <", value, "assignDeptName");
            return (Criteria) this;
        }

        public Criteria andAssignDeptNameLessThanOrEqualTo(String value) {
            addCriterion("ASSIGN_DEPT_NAME <=", value, "assignDeptName");
            return (Criteria) this;
        }

        public Criteria andAssignDeptNameLike(String value) {
            addCriterion("ASSIGN_DEPT_NAME like", value, "assignDeptName");
            return (Criteria) this;
        }

        public Criteria andAssignDeptNameNotLike(String value) {
            addCriterion("ASSIGN_DEPT_NAME not like", value, "assignDeptName");
            return (Criteria) this;
        }

        public Criteria andAssignDeptNameIn(List<String> values) {
            addCriterion("ASSIGN_DEPT_NAME in", values, "assignDeptName");
            return (Criteria) this;
        }

        public Criteria andAssignDeptNameNotIn(List<String> values) {
            addCriterion("ASSIGN_DEPT_NAME not in", values, "assignDeptName");
            return (Criteria) this;
        }

        public Criteria andAssignDeptNameBetween(String value1, String value2) {
            addCriterion("ASSIGN_DEPT_NAME between", value1, value2, "assignDeptName");
            return (Criteria) this;
        }

        public Criteria andAssignDeptNameNotBetween(String value1, String value2) {
            addCriterion("ASSIGN_DEPT_NAME not between", value1, value2, "assignDeptName");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameIsNull() {
            addCriterion("ASSIGN_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameIsNotNull() {
            addCriterion("ASSIGN_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameEqualTo(String value) {
            addCriterion("ASSIGN_USER_NAME =", value, "assignUserName");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameNotEqualTo(String value) {
            addCriterion("ASSIGN_USER_NAME <>", value, "assignUserName");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameGreaterThan(String value) {
            addCriterion("ASSIGN_USER_NAME >", value, "assignUserName");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("ASSIGN_USER_NAME >=", value, "assignUserName");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameLessThan(String value) {
            addCriterion("ASSIGN_USER_NAME <", value, "assignUserName");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameLessThanOrEqualTo(String value) {
            addCriterion("ASSIGN_USER_NAME <=", value, "assignUserName");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameLike(String value) {
            addCriterion("ASSIGN_USER_NAME like", value, "assignUserName");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameNotLike(String value) {
            addCriterion("ASSIGN_USER_NAME not like", value, "assignUserName");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameIn(List<String> values) {
            addCriterion("ASSIGN_USER_NAME in", values, "assignUserName");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameNotIn(List<String> values) {
            addCriterion("ASSIGN_USER_NAME not in", values, "assignUserName");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameBetween(String value1, String value2) {
            addCriterion("ASSIGN_USER_NAME between", value1, value2, "assignUserName");
            return (Criteria) this;
        }

        public Criteria andAssignUserNameNotBetween(String value1, String value2) {
            addCriterion("ASSIGN_USER_NAME not between", value1, value2, "assignUserName");
            return (Criteria) this;
        }

        public Criteria andAliasNameIsNull() {
            addCriterion("ALIAS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAliasNameIsNotNull() {
            addCriterion("ALIAS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAliasNameEqualTo(String value) {
            addCriterion("ALIAS_NAME =", value, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameNotEqualTo(String value) {
            addCriterion("ALIAS_NAME <>", value, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameGreaterThan(String value) {
            addCriterion("ALIAS_NAME >", value, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameGreaterThanOrEqualTo(String value) {
            addCriterion("ALIAS_NAME >=", value, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameLessThan(String value) {
            addCriterion("ALIAS_NAME <", value, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameLessThanOrEqualTo(String value) {
            addCriterion("ALIAS_NAME <=", value, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameLike(String value) {
            addCriterion("ALIAS_NAME like", value, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameNotLike(String value) {
            addCriterion("ALIAS_NAME not like", value, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameIn(List<String> values) {
            addCriterion("ALIAS_NAME in", values, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameNotIn(List<String> values) {
            addCriterion("ALIAS_NAME not in", values, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameBetween(String value1, String value2) {
            addCriterion("ALIAS_NAME between", value1, value2, "aliasName");
            return (Criteria) this;
        }

        public Criteria andAliasNameNotBetween(String value1, String value2) {
            addCriterion("ALIAS_NAME not between", value1, value2, "aliasName");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowIsNull() {
            addCriterion("CONSUMER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowIsNotNull() {
            addCriterion("CONSUMER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowEqualTo(String value) {
            addCriterion("CONSUMER_FLOW =", value, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowNotEqualTo(String value) {
            addCriterion("CONSUMER_FLOW <>", value, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowGreaterThan(String value) {
            addCriterion("CONSUMER_FLOW >", value, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CONSUMER_FLOW >=", value, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowLessThan(String value) {
            addCriterion("CONSUMER_FLOW <", value, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowLessThanOrEqualTo(String value) {
            addCriterion("CONSUMER_FLOW <=", value, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowLike(String value) {
            addCriterion("CONSUMER_FLOW like", value, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowNotLike(String value) {
            addCriterion("CONSUMER_FLOW not like", value, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowIn(List<String> values) {
            addCriterion("CONSUMER_FLOW in", values, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowNotIn(List<String> values) {
            addCriterion("CONSUMER_FLOW not in", values, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowBetween(String value1, String value2) {
            addCriterion("CONSUMER_FLOW between", value1, value2, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerFlowNotBetween(String value1, String value2) {
            addCriterion("CONSUMER_FLOW not between", value1, value2, "consumerFlow");
            return (Criteria) this;
        }

        public Criteria andConsumerNameIsNull() {
            addCriterion("CONSUMER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andConsumerNameIsNotNull() {
            addCriterion("CONSUMER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andConsumerNameEqualTo(String value) {
            addCriterion("CONSUMER_NAME =", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameNotEqualTo(String value) {
            addCriterion("CONSUMER_NAME <>", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameGreaterThan(String value) {
            addCriterion("CONSUMER_NAME >", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONSUMER_NAME >=", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameLessThan(String value) {
            addCriterion("CONSUMER_NAME <", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameLessThanOrEqualTo(String value) {
            addCriterion("CONSUMER_NAME <=", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameLike(String value) {
            addCriterion("CONSUMER_NAME like", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameNotLike(String value) {
            addCriterion("CONSUMER_NAME not like", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameIn(List<String> values) {
            addCriterion("CONSUMER_NAME in", values, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameNotIn(List<String> values) {
            addCriterion("CONSUMER_NAME not in", values, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameBetween(String value1, String value2) {
            addCriterion("CONSUMER_NAME between", value1, value2, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameNotBetween(String value1, String value2) {
            addCriterion("CONSUMER_NAME not between", value1, value2, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameIsNull() {
            addCriterion("CONSUMER_ALIAS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameIsNotNull() {
            addCriterion("CONSUMER_ALIAS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameEqualTo(String value) {
            addCriterion("CONSUMER_ALIAS_NAME =", value, "consumerAliasName");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameNotEqualTo(String value) {
            addCriterion("CONSUMER_ALIAS_NAME <>", value, "consumerAliasName");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameGreaterThan(String value) {
            addCriterion("CONSUMER_ALIAS_NAME >", value, "consumerAliasName");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONSUMER_ALIAS_NAME >=", value, "consumerAliasName");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameLessThan(String value) {
            addCriterion("CONSUMER_ALIAS_NAME <", value, "consumerAliasName");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameLessThanOrEqualTo(String value) {
            addCriterion("CONSUMER_ALIAS_NAME <=", value, "consumerAliasName");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameLike(String value) {
            addCriterion("CONSUMER_ALIAS_NAME like", value, "consumerAliasName");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameNotLike(String value) {
            addCriterion("CONSUMER_ALIAS_NAME not like", value, "consumerAliasName");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameIn(List<String> values) {
            addCriterion("CONSUMER_ALIAS_NAME in", values, "consumerAliasName");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameNotIn(List<String> values) {
            addCriterion("CONSUMER_ALIAS_NAME not in", values, "consumerAliasName");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameBetween(String value1, String value2) {
            addCriterion("CONSUMER_ALIAS_NAME between", value1, value2, "consumerAliasName");
            return (Criteria) this;
        }

        public Criteria andConsumerAliasNameNotBetween(String value1, String value2) {
            addCriterion("CONSUMER_ALIAS_NAME not between", value1, value2, "consumerAliasName");
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