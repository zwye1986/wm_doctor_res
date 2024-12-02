package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class PubPatientRecipeDrugExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PubPatientRecipeDrugExample() {
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

        public Criteria andRecipeFlowIsNull() {
            addCriterion("RECIPE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRecipeFlowIsNotNull() {
            addCriterion("RECIPE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRecipeFlowEqualTo(String value) {
            addCriterion("RECIPE_FLOW =", value, "recipeFlow");
            return (Criteria) this;
        }

        public Criteria andRecipeFlowNotEqualTo(String value) {
            addCriterion("RECIPE_FLOW <>", value, "recipeFlow");
            return (Criteria) this;
        }

        public Criteria andRecipeFlowGreaterThan(String value) {
            addCriterion("RECIPE_FLOW >", value, "recipeFlow");
            return (Criteria) this;
        }

        public Criteria andRecipeFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RECIPE_FLOW >=", value, "recipeFlow");
            return (Criteria) this;
        }

        public Criteria andRecipeFlowLessThan(String value) {
            addCriterion("RECIPE_FLOW <", value, "recipeFlow");
            return (Criteria) this;
        }

        public Criteria andRecipeFlowLessThanOrEqualTo(String value) {
            addCriterion("RECIPE_FLOW <=", value, "recipeFlow");
            return (Criteria) this;
        }

        public Criteria andRecipeFlowLike(String value) {
            addCriterion("RECIPE_FLOW like", value, "recipeFlow");
            return (Criteria) this;
        }

        public Criteria andRecipeFlowNotLike(String value) {
            addCriterion("RECIPE_FLOW not like", value, "recipeFlow");
            return (Criteria) this;
        }

        public Criteria andRecipeFlowIn(List<String> values) {
            addCriterion("RECIPE_FLOW in", values, "recipeFlow");
            return (Criteria) this;
        }

        public Criteria andRecipeFlowNotIn(List<String> values) {
            addCriterion("RECIPE_FLOW not in", values, "recipeFlow");
            return (Criteria) this;
        }

        public Criteria andRecipeFlowBetween(String value1, String value2) {
            addCriterion("RECIPE_FLOW between", value1, value2, "recipeFlow");
            return (Criteria) this;
        }

        public Criteria andRecipeFlowNotBetween(String value1, String value2) {
            addCriterion("RECIPE_FLOW not between", value1, value2, "recipeFlow");
            return (Criteria) this;
        }

        public Criteria andDrugFlowIsNull() {
            addCriterion("DRUG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDrugFlowIsNotNull() {
            addCriterion("DRUG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDrugFlowEqualTo(String value) {
            addCriterion("DRUG_FLOW =", value, "drugFlow");
            return (Criteria) this;
        }

        public Criteria andDrugFlowNotEqualTo(String value) {
            addCriterion("DRUG_FLOW <>", value, "drugFlow");
            return (Criteria) this;
        }

        public Criteria andDrugFlowGreaterThan(String value) {
            addCriterion("DRUG_FLOW >", value, "drugFlow");
            return (Criteria) this;
        }

        public Criteria andDrugFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DRUG_FLOW >=", value, "drugFlow");
            return (Criteria) this;
        }

        public Criteria andDrugFlowLessThan(String value) {
            addCriterion("DRUG_FLOW <", value, "drugFlow");
            return (Criteria) this;
        }

        public Criteria andDrugFlowLessThanOrEqualTo(String value) {
            addCriterion("DRUG_FLOW <=", value, "drugFlow");
            return (Criteria) this;
        }

        public Criteria andDrugFlowLike(String value) {
            addCriterion("DRUG_FLOW like", value, "drugFlow");
            return (Criteria) this;
        }

        public Criteria andDrugFlowNotLike(String value) {
            addCriterion("DRUG_FLOW not like", value, "drugFlow");
            return (Criteria) this;
        }

        public Criteria andDrugFlowIn(List<String> values) {
            addCriterion("DRUG_FLOW in", values, "drugFlow");
            return (Criteria) this;
        }

        public Criteria andDrugFlowNotIn(List<String> values) {
            addCriterion("DRUG_FLOW not in", values, "drugFlow");
            return (Criteria) this;
        }

        public Criteria andDrugFlowBetween(String value1, String value2) {
            addCriterion("DRUG_FLOW between", value1, value2, "drugFlow");
            return (Criteria) this;
        }

        public Criteria andDrugFlowNotBetween(String value1, String value2) {
            addCriterion("DRUG_FLOW not between", value1, value2, "drugFlow");
            return (Criteria) this;
        }

        public Criteria andDrugNameIsNull() {
            addCriterion("DRUG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDrugNameIsNotNull() {
            addCriterion("DRUG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDrugNameEqualTo(String value) {
            addCriterion("DRUG_NAME =", value, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameNotEqualTo(String value) {
            addCriterion("DRUG_NAME <>", value, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameGreaterThan(String value) {
            addCriterion("DRUG_NAME >", value, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameGreaterThanOrEqualTo(String value) {
            addCriterion("DRUG_NAME >=", value, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameLessThan(String value) {
            addCriterion("DRUG_NAME <", value, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameLessThanOrEqualTo(String value) {
            addCriterion("DRUG_NAME <=", value, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameLike(String value) {
            addCriterion("DRUG_NAME like", value, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameNotLike(String value) {
            addCriterion("DRUG_NAME not like", value, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameIn(List<String> values) {
            addCriterion("DRUG_NAME in", values, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameNotIn(List<String> values) {
            addCriterion("DRUG_NAME not in", values, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameBetween(String value1, String value2) {
            addCriterion("DRUG_NAME between", value1, value2, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugNameNotBetween(String value1, String value2) {
            addCriterion("DRUG_NAME not between", value1, value2, "drugName");
            return (Criteria) this;
        }

        public Criteria andDrugPackIsNull() {
            addCriterion("DRUG_PACK is null");
            return (Criteria) this;
        }

        public Criteria andDrugPackIsNotNull() {
            addCriterion("DRUG_PACK is not null");
            return (Criteria) this;
        }

        public Criteria andDrugPackEqualTo(String value) {
            addCriterion("DRUG_PACK =", value, "drugPack");
            return (Criteria) this;
        }

        public Criteria andDrugPackNotEqualTo(String value) {
            addCriterion("DRUG_PACK <>", value, "drugPack");
            return (Criteria) this;
        }

        public Criteria andDrugPackGreaterThan(String value) {
            addCriterion("DRUG_PACK >", value, "drugPack");
            return (Criteria) this;
        }

        public Criteria andDrugPackGreaterThanOrEqualTo(String value) {
            addCriterion("DRUG_PACK >=", value, "drugPack");
            return (Criteria) this;
        }

        public Criteria andDrugPackLessThan(String value) {
            addCriterion("DRUG_PACK <", value, "drugPack");
            return (Criteria) this;
        }

        public Criteria andDrugPackLessThanOrEqualTo(String value) {
            addCriterion("DRUG_PACK <=", value, "drugPack");
            return (Criteria) this;
        }

        public Criteria andDrugPackLike(String value) {
            addCriterion("DRUG_PACK like", value, "drugPack");
            return (Criteria) this;
        }

        public Criteria andDrugPackNotLike(String value) {
            addCriterion("DRUG_PACK not like", value, "drugPack");
            return (Criteria) this;
        }

        public Criteria andDrugPackIn(List<String> values) {
            addCriterion("DRUG_PACK in", values, "drugPack");
            return (Criteria) this;
        }

        public Criteria andDrugPackNotIn(List<String> values) {
            addCriterion("DRUG_PACK not in", values, "drugPack");
            return (Criteria) this;
        }

        public Criteria andDrugPackBetween(String value1, String value2) {
            addCriterion("DRUG_PACK between", value1, value2, "drugPack");
            return (Criteria) this;
        }

        public Criteria andDrugPackNotBetween(String value1, String value2) {
            addCriterion("DRUG_PACK not between", value1, value2, "drugPack");
            return (Criteria) this;
        }

        public Criteria andDrugAmountIsNull() {
            addCriterion("DRUG_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andDrugAmountIsNotNull() {
            addCriterion("DRUG_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andDrugAmountEqualTo(String value) {
            addCriterion("DRUG_AMOUNT =", value, "drugAmount");
            return (Criteria) this;
        }

        public Criteria andDrugAmountNotEqualTo(String value) {
            addCriterion("DRUG_AMOUNT <>", value, "drugAmount");
            return (Criteria) this;
        }

        public Criteria andDrugAmountGreaterThan(String value) {
            addCriterion("DRUG_AMOUNT >", value, "drugAmount");
            return (Criteria) this;
        }

        public Criteria andDrugAmountGreaterThanOrEqualTo(String value) {
            addCriterion("DRUG_AMOUNT >=", value, "drugAmount");
            return (Criteria) this;
        }

        public Criteria andDrugAmountLessThan(String value) {
            addCriterion("DRUG_AMOUNT <", value, "drugAmount");
            return (Criteria) this;
        }

        public Criteria andDrugAmountLessThanOrEqualTo(String value) {
            addCriterion("DRUG_AMOUNT <=", value, "drugAmount");
            return (Criteria) this;
        }

        public Criteria andDrugAmountLike(String value) {
            addCriterion("DRUG_AMOUNT like", value, "drugAmount");
            return (Criteria) this;
        }

        public Criteria andDrugAmountNotLike(String value) {
            addCriterion("DRUG_AMOUNT not like", value, "drugAmount");
            return (Criteria) this;
        }

        public Criteria andDrugAmountIn(List<String> values) {
            addCriterion("DRUG_AMOUNT in", values, "drugAmount");
            return (Criteria) this;
        }

        public Criteria andDrugAmountNotIn(List<String> values) {
            addCriterion("DRUG_AMOUNT not in", values, "drugAmount");
            return (Criteria) this;
        }

        public Criteria andDrugAmountBetween(String value1, String value2) {
            addCriterion("DRUG_AMOUNT between", value1, value2, "drugAmount");
            return (Criteria) this;
        }

        public Criteria andDrugAmountNotBetween(String value1, String value2) {
            addCriterion("DRUG_AMOUNT not between", value1, value2, "drugAmount");
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