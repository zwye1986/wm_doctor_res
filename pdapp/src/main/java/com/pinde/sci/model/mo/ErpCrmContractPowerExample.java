package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ErpCrmContractPowerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ErpCrmContractPowerExample() {
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

        public Criteria andPowerFlowIsNull() {
            addCriterion("POWER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andPowerFlowIsNotNull() {
            addCriterion("POWER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andPowerFlowEqualTo(String value) {
            addCriterion("POWER_FLOW =", value, "powerFlow");
            return (Criteria) this;
        }

        public Criteria andPowerFlowNotEqualTo(String value) {
            addCriterion("POWER_FLOW <>", value, "powerFlow");
            return (Criteria) this;
        }

        public Criteria andPowerFlowGreaterThan(String value) {
            addCriterion("POWER_FLOW >", value, "powerFlow");
            return (Criteria) this;
        }

        public Criteria andPowerFlowGreaterThanOrEqualTo(String value) {
            addCriterion("POWER_FLOW >=", value, "powerFlow");
            return (Criteria) this;
        }

        public Criteria andPowerFlowLessThan(String value) {
            addCriterion("POWER_FLOW <", value, "powerFlow");
            return (Criteria) this;
        }

        public Criteria andPowerFlowLessThanOrEqualTo(String value) {
            addCriterion("POWER_FLOW <=", value, "powerFlow");
            return (Criteria) this;
        }

        public Criteria andPowerFlowLike(String value) {
            addCriterion("POWER_FLOW like", value, "powerFlow");
            return (Criteria) this;
        }

        public Criteria andPowerFlowNotLike(String value) {
            addCriterion("POWER_FLOW not like", value, "powerFlow");
            return (Criteria) this;
        }

        public Criteria andPowerFlowIn(List<String> values) {
            addCriterion("POWER_FLOW in", values, "powerFlow");
            return (Criteria) this;
        }

        public Criteria andPowerFlowNotIn(List<String> values) {
            addCriterion("POWER_FLOW not in", values, "powerFlow");
            return (Criteria) this;
        }

        public Criteria andPowerFlowBetween(String value1, String value2) {
            addCriterion("POWER_FLOW between", value1, value2, "powerFlow");
            return (Criteria) this;
        }

        public Criteria andPowerFlowNotBetween(String value1, String value2) {
            addCriterion("POWER_FLOW not between", value1, value2, "powerFlow");
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

        public Criteria andSessionNumberIsNull() {
            addCriterion("SESSION_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIsNotNull() {
            addCriterion("SESSION_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andSessionNumberEqualTo(String value) {
            addCriterion("SESSION_NUMBER =", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotEqualTo(String value) {
            addCriterion("SESSION_NUMBER <>", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberGreaterThan(String value) {
            addCriterion("SESSION_NUMBER >", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberGreaterThanOrEqualTo(String value) {
            addCriterion("SESSION_NUMBER >=", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLessThan(String value) {
            addCriterion("SESSION_NUMBER <", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLessThanOrEqualTo(String value) {
            addCriterion("SESSION_NUMBER <=", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLike(String value) {
            addCriterion("SESSION_NUMBER like", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotLike(String value) {
            addCriterion("SESSION_NUMBER not like", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIn(List<String> values) {
            addCriterion("SESSION_NUMBER in", values, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotIn(List<String> values) {
            addCriterion("SESSION_NUMBER not in", values, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberBetween(String value1, String value2) {
            addCriterion("SESSION_NUMBER between", value1, value2, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotBetween(String value1, String value2) {
            addCriterion("SESSION_NUMBER not between", value1, value2, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andPowerIdsIsNull() {
            addCriterion("POWER_IDS is null");
            return (Criteria) this;
        }

        public Criteria andPowerIdsIsNotNull() {
            addCriterion("POWER_IDS is not null");
            return (Criteria) this;
        }

        public Criteria andPowerIdsEqualTo(String value) {
            addCriterion("POWER_IDS =", value, "powerIds");
            return (Criteria) this;
        }

        public Criteria andPowerIdsNotEqualTo(String value) {
            addCriterion("POWER_IDS <>", value, "powerIds");
            return (Criteria) this;
        }

        public Criteria andPowerIdsGreaterThan(String value) {
            addCriterion("POWER_IDS >", value, "powerIds");
            return (Criteria) this;
        }

        public Criteria andPowerIdsGreaterThanOrEqualTo(String value) {
            addCriterion("POWER_IDS >=", value, "powerIds");
            return (Criteria) this;
        }

        public Criteria andPowerIdsLessThan(String value) {
            addCriterion("POWER_IDS <", value, "powerIds");
            return (Criteria) this;
        }

        public Criteria andPowerIdsLessThanOrEqualTo(String value) {
            addCriterion("POWER_IDS <=", value, "powerIds");
            return (Criteria) this;
        }

        public Criteria andPowerIdsLike(String value) {
            addCriterion("POWER_IDS like", value, "powerIds");
            return (Criteria) this;
        }

        public Criteria andPowerIdsNotLike(String value) {
            addCriterion("POWER_IDS not like", value, "powerIds");
            return (Criteria) this;
        }

        public Criteria andPowerIdsIn(List<String> values) {
            addCriterion("POWER_IDS in", values, "powerIds");
            return (Criteria) this;
        }

        public Criteria andPowerIdsNotIn(List<String> values) {
            addCriterion("POWER_IDS not in", values, "powerIds");
            return (Criteria) this;
        }

        public Criteria andPowerIdsBetween(String value1, String value2) {
            addCriterion("POWER_IDS between", value1, value2, "powerIds");
            return (Criteria) this;
        }

        public Criteria andPowerIdsNotBetween(String value1, String value2) {
            addCriterion("POWER_IDS not between", value1, value2, "powerIds");
            return (Criteria) this;
        }

        public Criteria andPowerNamesIsNull() {
            addCriterion("POWER_NAMES is null");
            return (Criteria) this;
        }

        public Criteria andPowerNamesIsNotNull() {
            addCriterion("POWER_NAMES is not null");
            return (Criteria) this;
        }

        public Criteria andPowerNamesEqualTo(String value) {
            addCriterion("POWER_NAMES =", value, "powerNames");
            return (Criteria) this;
        }

        public Criteria andPowerNamesNotEqualTo(String value) {
            addCriterion("POWER_NAMES <>", value, "powerNames");
            return (Criteria) this;
        }

        public Criteria andPowerNamesGreaterThan(String value) {
            addCriterion("POWER_NAMES >", value, "powerNames");
            return (Criteria) this;
        }

        public Criteria andPowerNamesGreaterThanOrEqualTo(String value) {
            addCriterion("POWER_NAMES >=", value, "powerNames");
            return (Criteria) this;
        }

        public Criteria andPowerNamesLessThan(String value) {
            addCriterion("POWER_NAMES <", value, "powerNames");
            return (Criteria) this;
        }

        public Criteria andPowerNamesLessThanOrEqualTo(String value) {
            addCriterion("POWER_NAMES <=", value, "powerNames");
            return (Criteria) this;
        }

        public Criteria andPowerNamesLike(String value) {
            addCriterion("POWER_NAMES like", value, "powerNames");
            return (Criteria) this;
        }

        public Criteria andPowerNamesNotLike(String value) {
            addCriterion("POWER_NAMES not like", value, "powerNames");
            return (Criteria) this;
        }

        public Criteria andPowerNamesIn(List<String> values) {
            addCriterion("POWER_NAMES in", values, "powerNames");
            return (Criteria) this;
        }

        public Criteria andPowerNamesNotIn(List<String> values) {
            addCriterion("POWER_NAMES not in", values, "powerNames");
            return (Criteria) this;
        }

        public Criteria andPowerNamesBetween(String value1, String value2) {
            addCriterion("POWER_NAMES between", value1, value2, "powerNames");
            return (Criteria) this;
        }

        public Criteria andPowerNamesNotBetween(String value1, String value2) {
            addCriterion("POWER_NAMES not between", value1, value2, "powerNames");
            return (Criteria) this;
        }

        public Criteria andPeopleNumIsNull() {
            addCriterion("PEOPLE_NUM is null");
            return (Criteria) this;
        }

        public Criteria andPeopleNumIsNotNull() {
            addCriterion("PEOPLE_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andPeopleNumEqualTo(Integer value) {
            addCriterion("PEOPLE_NUM =", value, "peopleNum");
            return (Criteria) this;
        }

        public Criteria andPeopleNumNotEqualTo(Integer value) {
            addCriterion("PEOPLE_NUM <>", value, "peopleNum");
            return (Criteria) this;
        }

        public Criteria andPeopleNumGreaterThan(Integer value) {
            addCriterion("PEOPLE_NUM >", value, "peopleNum");
            return (Criteria) this;
        }

        public Criteria andPeopleNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("PEOPLE_NUM >=", value, "peopleNum");
            return (Criteria) this;
        }

        public Criteria andPeopleNumLessThan(Integer value) {
            addCriterion("PEOPLE_NUM <", value, "peopleNum");
            return (Criteria) this;
        }

        public Criteria andPeopleNumLessThanOrEqualTo(Integer value) {
            addCriterion("PEOPLE_NUM <=", value, "peopleNum");
            return (Criteria) this;
        }

        public Criteria andPeopleNumIn(List<Integer> values) {
            addCriterion("PEOPLE_NUM in", values, "peopleNum");
            return (Criteria) this;
        }

        public Criteria andPeopleNumNotIn(List<Integer> values) {
            addCriterion("PEOPLE_NUM not in", values, "peopleNum");
            return (Criteria) this;
        }

        public Criteria andPeopleNumBetween(Integer value1, Integer value2) {
            addCriterion("PEOPLE_NUM between", value1, value2, "peopleNum");
            return (Criteria) this;
        }

        public Criteria andPeopleNumNotBetween(Integer value1, Integer value2) {
            addCriterion("PEOPLE_NUM not between", value1, value2, "peopleNum");
            return (Criteria) this;
        }

        public Criteria andFileFlowIsNull() {
            addCriterion("FILE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andFileFlowIsNotNull() {
            addCriterion("FILE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andFileFlowEqualTo(String value) {
            addCriterion("FILE_FLOW =", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowNotEqualTo(String value) {
            addCriterion("FILE_FLOW <>", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowGreaterThan(String value) {
            addCriterion("FILE_FLOW >", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowGreaterThanOrEqualTo(String value) {
            addCriterion("FILE_FLOW >=", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowLessThan(String value) {
            addCriterion("FILE_FLOW <", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowLessThanOrEqualTo(String value) {
            addCriterion("FILE_FLOW <=", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowLike(String value) {
            addCriterion("FILE_FLOW like", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowNotLike(String value) {
            addCriterion("FILE_FLOW not like", value, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowIn(List<String> values) {
            addCriterion("FILE_FLOW in", values, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowNotIn(List<String> values) {
            addCriterion("FILE_FLOW not in", values, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowBetween(String value1, String value2) {
            addCriterion("FILE_FLOW between", value1, value2, "fileFlow");
            return (Criteria) this;
        }

        public Criteria andFileFlowNotBetween(String value1, String value2) {
            addCriterion("FILE_FLOW not between", value1, value2, "fileFlow");
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

        public Criteria andGraduatePeopleNumIsNull() {
            addCriterion("GRADUATE_PEOPLE_NUM is null");
            return (Criteria) this;
        }

        public Criteria andGraduatePeopleNumIsNotNull() {
            addCriterion("GRADUATE_PEOPLE_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andGraduatePeopleNumEqualTo(Integer value) {
            addCriterion("GRADUATE_PEOPLE_NUM =", value, "graduatePeopleNum");
            return (Criteria) this;
        }

        public Criteria andGraduatePeopleNumNotEqualTo(Integer value) {
            addCriterion("GRADUATE_PEOPLE_NUM <>", value, "graduatePeopleNum");
            return (Criteria) this;
        }

        public Criteria andGraduatePeopleNumGreaterThan(Integer value) {
            addCriterion("GRADUATE_PEOPLE_NUM >", value, "graduatePeopleNum");
            return (Criteria) this;
        }

        public Criteria andGraduatePeopleNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("GRADUATE_PEOPLE_NUM >=", value, "graduatePeopleNum");
            return (Criteria) this;
        }

        public Criteria andGraduatePeopleNumLessThan(Integer value) {
            addCriterion("GRADUATE_PEOPLE_NUM <", value, "graduatePeopleNum");
            return (Criteria) this;
        }

        public Criteria andGraduatePeopleNumLessThanOrEqualTo(Integer value) {
            addCriterion("GRADUATE_PEOPLE_NUM <=", value, "graduatePeopleNum");
            return (Criteria) this;
        }

        public Criteria andGraduatePeopleNumIn(List<Integer> values) {
            addCriterion("GRADUATE_PEOPLE_NUM in", values, "graduatePeopleNum");
            return (Criteria) this;
        }

        public Criteria andGraduatePeopleNumNotIn(List<Integer> values) {
            addCriterion("GRADUATE_PEOPLE_NUM not in", values, "graduatePeopleNum");
            return (Criteria) this;
        }

        public Criteria andGraduatePeopleNumBetween(Integer value1, Integer value2) {
            addCriterion("GRADUATE_PEOPLE_NUM between", value1, value2, "graduatePeopleNum");
            return (Criteria) this;
        }

        public Criteria andGraduatePeopleNumNotBetween(Integer value1, Integer value2) {
            addCriterion("GRADUATE_PEOPLE_NUM not between", value1, value2, "graduatePeopleNum");
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