package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ErpCrmProductBuildInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ErpCrmProductBuildInfoExample() {
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

        public Criteria andContractFlowIsNull() {
            addCriterion("CONTRACT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andContractFlowIsNotNull() {
            addCriterion("CONTRACT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andContractFlowEqualTo(String value) {
            addCriterion("CONTRACT_FLOW =", value, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowNotEqualTo(String value) {
            addCriterion("CONTRACT_FLOW <>", value, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowGreaterThan(String value) {
            addCriterion("CONTRACT_FLOW >", value, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_FLOW >=", value, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowLessThan(String value) {
            addCriterion("CONTRACT_FLOW <", value, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_FLOW <=", value, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowLike(String value) {
            addCriterion("CONTRACT_FLOW like", value, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowNotLike(String value) {
            addCriterion("CONTRACT_FLOW not like", value, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowIn(List<String> values) {
            addCriterion("CONTRACT_FLOW in", values, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowNotIn(List<String> values) {
            addCriterion("CONTRACT_FLOW not in", values, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowBetween(String value1, String value2) {
            addCriterion("CONTRACT_FLOW between", value1, value2, "contractFlow");
            return (Criteria) this;
        }

        public Criteria andContractFlowNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_FLOW not between", value1, value2, "contractFlow");
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

        public Criteria andBuildingUserFlowIsNull() {
            addCriterion("BUILDING_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andBuildingUserFlowIsNotNull() {
            addCriterion("BUILDING_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingUserFlowEqualTo(String value) {
            addCriterion("BUILDING_USER_FLOW =", value, "buildingUserFlow");
            return (Criteria) this;
        }

        public Criteria andBuildingUserFlowNotEqualTo(String value) {
            addCriterion("BUILDING_USER_FLOW <>", value, "buildingUserFlow");
            return (Criteria) this;
        }

        public Criteria andBuildingUserFlowGreaterThan(String value) {
            addCriterion("BUILDING_USER_FLOW >", value, "buildingUserFlow");
            return (Criteria) this;
        }

        public Criteria andBuildingUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("BUILDING_USER_FLOW >=", value, "buildingUserFlow");
            return (Criteria) this;
        }

        public Criteria andBuildingUserFlowLessThan(String value) {
            addCriterion("BUILDING_USER_FLOW <", value, "buildingUserFlow");
            return (Criteria) this;
        }

        public Criteria andBuildingUserFlowLessThanOrEqualTo(String value) {
            addCriterion("BUILDING_USER_FLOW <=", value, "buildingUserFlow");
            return (Criteria) this;
        }

        public Criteria andBuildingUserFlowLike(String value) {
            addCriterion("BUILDING_USER_FLOW like", value, "buildingUserFlow");
            return (Criteria) this;
        }

        public Criteria andBuildingUserFlowNotLike(String value) {
            addCriterion("BUILDING_USER_FLOW not like", value, "buildingUserFlow");
            return (Criteria) this;
        }

        public Criteria andBuildingUserFlowIn(List<String> values) {
            addCriterion("BUILDING_USER_FLOW in", values, "buildingUserFlow");
            return (Criteria) this;
        }

        public Criteria andBuildingUserFlowNotIn(List<String> values) {
            addCriterion("BUILDING_USER_FLOW not in", values, "buildingUserFlow");
            return (Criteria) this;
        }

        public Criteria andBuildingUserFlowBetween(String value1, String value2) {
            addCriterion("BUILDING_USER_FLOW between", value1, value2, "buildingUserFlow");
            return (Criteria) this;
        }

        public Criteria andBuildingUserFlowNotBetween(String value1, String value2) {
            addCriterion("BUILDING_USER_FLOW not between", value1, value2, "buildingUserFlow");
            return (Criteria) this;
        }

        public Criteria andBuildingUserNameIsNull() {
            addCriterion("BUILDING_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBuildingUserNameIsNotNull() {
            addCriterion("BUILDING_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingUserNameEqualTo(String value) {
            addCriterion("BUILDING_USER_NAME =", value, "buildingUserName");
            return (Criteria) this;
        }

        public Criteria andBuildingUserNameNotEqualTo(String value) {
            addCriterion("BUILDING_USER_NAME <>", value, "buildingUserName");
            return (Criteria) this;
        }

        public Criteria andBuildingUserNameGreaterThan(String value) {
            addCriterion("BUILDING_USER_NAME >", value, "buildingUserName");
            return (Criteria) this;
        }

        public Criteria andBuildingUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("BUILDING_USER_NAME >=", value, "buildingUserName");
            return (Criteria) this;
        }

        public Criteria andBuildingUserNameLessThan(String value) {
            addCriterion("BUILDING_USER_NAME <", value, "buildingUserName");
            return (Criteria) this;
        }

        public Criteria andBuildingUserNameLessThanOrEqualTo(String value) {
            addCriterion("BUILDING_USER_NAME <=", value, "buildingUserName");
            return (Criteria) this;
        }

        public Criteria andBuildingUserNameLike(String value) {
            addCriterion("BUILDING_USER_NAME like", value, "buildingUserName");
            return (Criteria) this;
        }

        public Criteria andBuildingUserNameNotLike(String value) {
            addCriterion("BUILDING_USER_NAME not like", value, "buildingUserName");
            return (Criteria) this;
        }

        public Criteria andBuildingUserNameIn(List<String> values) {
            addCriterion("BUILDING_USER_NAME in", values, "buildingUserName");
            return (Criteria) this;
        }

        public Criteria andBuildingUserNameNotIn(List<String> values) {
            addCriterion("BUILDING_USER_NAME not in", values, "buildingUserName");
            return (Criteria) this;
        }

        public Criteria andBuildingUserNameBetween(String value1, String value2) {
            addCriterion("BUILDING_USER_NAME between", value1, value2, "buildingUserName");
            return (Criteria) this;
        }

        public Criteria andBuildingUserNameNotBetween(String value1, String value2) {
            addCriterion("BUILDING_USER_NAME not between", value1, value2, "buildingUserName");
            return (Criteria) this;
        }

        public Criteria andBuildingAddressIsNull() {
            addCriterion("BUILDING_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andBuildingAddressIsNotNull() {
            addCriterion("BUILDING_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingAddressEqualTo(String value) {
            addCriterion("BUILDING_ADDRESS =", value, "buildingAddress");
            return (Criteria) this;
        }

        public Criteria andBuildingAddressNotEqualTo(String value) {
            addCriterion("BUILDING_ADDRESS <>", value, "buildingAddress");
            return (Criteria) this;
        }

        public Criteria andBuildingAddressGreaterThan(String value) {
            addCriterion("BUILDING_ADDRESS >", value, "buildingAddress");
            return (Criteria) this;
        }

        public Criteria andBuildingAddressGreaterThanOrEqualTo(String value) {
            addCriterion("BUILDING_ADDRESS >=", value, "buildingAddress");
            return (Criteria) this;
        }

        public Criteria andBuildingAddressLessThan(String value) {
            addCriterion("BUILDING_ADDRESS <", value, "buildingAddress");
            return (Criteria) this;
        }

        public Criteria andBuildingAddressLessThanOrEqualTo(String value) {
            addCriterion("BUILDING_ADDRESS <=", value, "buildingAddress");
            return (Criteria) this;
        }

        public Criteria andBuildingAddressLike(String value) {
            addCriterion("BUILDING_ADDRESS like", value, "buildingAddress");
            return (Criteria) this;
        }

        public Criteria andBuildingAddressNotLike(String value) {
            addCriterion("BUILDING_ADDRESS not like", value, "buildingAddress");
            return (Criteria) this;
        }

        public Criteria andBuildingAddressIn(List<String> values) {
            addCriterion("BUILDING_ADDRESS in", values, "buildingAddress");
            return (Criteria) this;
        }

        public Criteria andBuildingAddressNotIn(List<String> values) {
            addCriterion("BUILDING_ADDRESS not in", values, "buildingAddress");
            return (Criteria) this;
        }

        public Criteria andBuildingAddressBetween(String value1, String value2) {
            addCriterion("BUILDING_ADDRESS between", value1, value2, "buildingAddress");
            return (Criteria) this;
        }

        public Criteria andBuildingAddressNotBetween(String value1, String value2) {
            addCriterion("BUILDING_ADDRESS not between", value1, value2, "buildingAddress");
            return (Criteria) this;
        }

        public Criteria andTravelDateIsNull() {
            addCriterion("TRAVEL_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTravelDateIsNotNull() {
            addCriterion("TRAVEL_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTravelDateEqualTo(String value) {
            addCriterion("TRAVEL_DATE =", value, "travelDate");
            return (Criteria) this;
        }

        public Criteria andTravelDateNotEqualTo(String value) {
            addCriterion("TRAVEL_DATE <>", value, "travelDate");
            return (Criteria) this;
        }

        public Criteria andTravelDateGreaterThan(String value) {
            addCriterion("TRAVEL_DATE >", value, "travelDate");
            return (Criteria) this;
        }

        public Criteria andTravelDateGreaterThanOrEqualTo(String value) {
            addCriterion("TRAVEL_DATE >=", value, "travelDate");
            return (Criteria) this;
        }

        public Criteria andTravelDateLessThan(String value) {
            addCriterion("TRAVEL_DATE <", value, "travelDate");
            return (Criteria) this;
        }

        public Criteria andTravelDateLessThanOrEqualTo(String value) {
            addCriterion("TRAVEL_DATE <=", value, "travelDate");
            return (Criteria) this;
        }

        public Criteria andTravelDateLike(String value) {
            addCriterion("TRAVEL_DATE like", value, "travelDate");
            return (Criteria) this;
        }

        public Criteria andTravelDateNotLike(String value) {
            addCriterion("TRAVEL_DATE not like", value, "travelDate");
            return (Criteria) this;
        }

        public Criteria andTravelDateIn(List<String> values) {
            addCriterion("TRAVEL_DATE in", values, "travelDate");
            return (Criteria) this;
        }

        public Criteria andTravelDateNotIn(List<String> values) {
            addCriterion("TRAVEL_DATE not in", values, "travelDate");
            return (Criteria) this;
        }

        public Criteria andTravelDateBetween(String value1, String value2) {
            addCriterion("TRAVEL_DATE between", value1, value2, "travelDate");
            return (Criteria) this;
        }

        public Criteria andTravelDateNotBetween(String value1, String value2) {
            addCriterion("TRAVEL_DATE not between", value1, value2, "travelDate");
            return (Criteria) this;
        }

        public Criteria andBuildingDateIsNull() {
            addCriterion("BUILDING_DATE is null");
            return (Criteria) this;
        }

        public Criteria andBuildingDateIsNotNull() {
            addCriterion("BUILDING_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingDateEqualTo(String value) {
            addCriterion("BUILDING_DATE =", value, "buildingDate");
            return (Criteria) this;
        }

        public Criteria andBuildingDateNotEqualTo(String value) {
            addCriterion("BUILDING_DATE <>", value, "buildingDate");
            return (Criteria) this;
        }

        public Criteria andBuildingDateGreaterThan(String value) {
            addCriterion("BUILDING_DATE >", value, "buildingDate");
            return (Criteria) this;
        }

        public Criteria andBuildingDateGreaterThanOrEqualTo(String value) {
            addCriterion("BUILDING_DATE >=", value, "buildingDate");
            return (Criteria) this;
        }

        public Criteria andBuildingDateLessThan(String value) {
            addCriterion("BUILDING_DATE <", value, "buildingDate");
            return (Criteria) this;
        }

        public Criteria andBuildingDateLessThanOrEqualTo(String value) {
            addCriterion("BUILDING_DATE <=", value, "buildingDate");
            return (Criteria) this;
        }

        public Criteria andBuildingDateLike(String value) {
            addCriterion("BUILDING_DATE like", value, "buildingDate");
            return (Criteria) this;
        }

        public Criteria andBuildingDateNotLike(String value) {
            addCriterion("BUILDING_DATE not like", value, "buildingDate");
            return (Criteria) this;
        }

        public Criteria andBuildingDateIn(List<String> values) {
            addCriterion("BUILDING_DATE in", values, "buildingDate");
            return (Criteria) this;
        }

        public Criteria andBuildingDateNotIn(List<String> values) {
            addCriterion("BUILDING_DATE not in", values, "buildingDate");
            return (Criteria) this;
        }

        public Criteria andBuildingDateBetween(String value1, String value2) {
            addCriterion("BUILDING_DATE between", value1, value2, "buildingDate");
            return (Criteria) this;
        }

        public Criteria andBuildingDateNotBetween(String value1, String value2) {
            addCriterion("BUILDING_DATE not between", value1, value2, "buildingDate");
            return (Criteria) this;
        }

        public Criteria andAcceptDateIsNull() {
            addCriterion("ACCEPT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andAcceptDateIsNotNull() {
            addCriterion("ACCEPT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptDateEqualTo(String value) {
            addCriterion("ACCEPT_DATE =", value, "acceptDate");
            return (Criteria) this;
        }

        public Criteria andAcceptDateNotEqualTo(String value) {
            addCriterion("ACCEPT_DATE <>", value, "acceptDate");
            return (Criteria) this;
        }

        public Criteria andAcceptDateGreaterThan(String value) {
            addCriterion("ACCEPT_DATE >", value, "acceptDate");
            return (Criteria) this;
        }

        public Criteria andAcceptDateGreaterThanOrEqualTo(String value) {
            addCriterion("ACCEPT_DATE >=", value, "acceptDate");
            return (Criteria) this;
        }

        public Criteria andAcceptDateLessThan(String value) {
            addCriterion("ACCEPT_DATE <", value, "acceptDate");
            return (Criteria) this;
        }

        public Criteria andAcceptDateLessThanOrEqualTo(String value) {
            addCriterion("ACCEPT_DATE <=", value, "acceptDate");
            return (Criteria) this;
        }

        public Criteria andAcceptDateLike(String value) {
            addCriterion("ACCEPT_DATE like", value, "acceptDate");
            return (Criteria) this;
        }

        public Criteria andAcceptDateNotLike(String value) {
            addCriterion("ACCEPT_DATE not like", value, "acceptDate");
            return (Criteria) this;
        }

        public Criteria andAcceptDateIn(List<String> values) {
            addCriterion("ACCEPT_DATE in", values, "acceptDate");
            return (Criteria) this;
        }

        public Criteria andAcceptDateNotIn(List<String> values) {
            addCriterion("ACCEPT_DATE not in", values, "acceptDate");
            return (Criteria) this;
        }

        public Criteria andAcceptDateBetween(String value1, String value2) {
            addCriterion("ACCEPT_DATE between", value1, value2, "acceptDate");
            return (Criteria) this;
        }

        public Criteria andAcceptDateNotBetween(String value1, String value2) {
            addCriterion("ACCEPT_DATE not between", value1, value2, "acceptDate");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNull() {
            addCriterion("ORDER_NO is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("ORDER_NO is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("ORDER_NO =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("ORDER_NO <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("ORDER_NO >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_NO >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("ORDER_NO <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("ORDER_NO <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("ORDER_NO like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("ORDER_NO not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("ORDER_NO in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("ORDER_NO not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("ORDER_NO between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("ORDER_NO not between", value1, value2, "orderNo");
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