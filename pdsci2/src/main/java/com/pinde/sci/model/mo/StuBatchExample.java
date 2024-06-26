package com.pinde.sci.model.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StuBatchExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StuBatchExample() {
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

        public Criteria andBatchFlowIsNull() {
            addCriterion("BATCH_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andBatchFlowIsNotNull() {
            addCriterion("BATCH_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andBatchFlowEqualTo(String value) {
            addCriterion("BATCH_FLOW =", value, "batchFlow");
            return (Criteria) this;
        }

        public Criteria andBatchFlowNotEqualTo(String value) {
            addCriterion("BATCH_FLOW <>", value, "batchFlow");
            return (Criteria) this;
        }

        public Criteria andBatchFlowGreaterThan(String value) {
            addCriterion("BATCH_FLOW >", value, "batchFlow");
            return (Criteria) this;
        }

        public Criteria andBatchFlowGreaterThanOrEqualTo(String value) {
            addCriterion("BATCH_FLOW >=", value, "batchFlow");
            return (Criteria) this;
        }

        public Criteria andBatchFlowLessThan(String value) {
            addCriterion("BATCH_FLOW <", value, "batchFlow");
            return (Criteria) this;
        }

        public Criteria andBatchFlowLessThanOrEqualTo(String value) {
            addCriterion("BATCH_FLOW <=", value, "batchFlow");
            return (Criteria) this;
        }

        public Criteria andBatchFlowLike(String value) {
            addCriterion("BATCH_FLOW like", value, "batchFlow");
            return (Criteria) this;
        }

        public Criteria andBatchFlowNotLike(String value) {
            addCriterion("BATCH_FLOW not like", value, "batchFlow");
            return (Criteria) this;
        }

        public Criteria andBatchFlowIn(List<String> values) {
            addCriterion("BATCH_FLOW in", values, "batchFlow");
            return (Criteria) this;
        }

        public Criteria andBatchFlowNotIn(List<String> values) {
            addCriterion("BATCH_FLOW not in", values, "batchFlow");
            return (Criteria) this;
        }

        public Criteria andBatchFlowBetween(String value1, String value2) {
            addCriterion("BATCH_FLOW between", value1, value2, "batchFlow");
            return (Criteria) this;
        }

        public Criteria andBatchFlowNotBetween(String value1, String value2) {
            addCriterion("BATCH_FLOW not between", value1, value2, "batchFlow");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNull() {
            addCriterion("BATCH_NO is null");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNotNull() {
            addCriterion("BATCH_NO is not null");
            return (Criteria) this;
        }

        public Criteria andBatchNoEqualTo(String value) {
            addCriterion("BATCH_NO =", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotEqualTo(String value) {
            addCriterion("BATCH_NO <>", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThan(String value) {
            addCriterion("BATCH_NO >", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThanOrEqualTo(String value) {
            addCriterion("BATCH_NO >=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThan(String value) {
            addCriterion("BATCH_NO <", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThanOrEqualTo(String value) {
            addCriterion("BATCH_NO <=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLike(String value) {
            addCriterion("BATCH_NO like", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotLike(String value) {
            addCriterion("BATCH_NO not like", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoIn(List<String> values) {
            addCriterion("BATCH_NO in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotIn(List<String> values) {
            addCriterion("BATCH_NO not in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoBetween(String value1, String value2) {
            addCriterion("BATCH_NO between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotBetween(String value1, String value2) {
            addCriterion("BATCH_NO not between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchRegDateIsNull() {
            addCriterion("BATCH_REG_DATE is null");
            return (Criteria) this;
        }

        public Criteria andBatchRegDateIsNotNull() {
            addCriterion("BATCH_REG_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andBatchRegDateEqualTo(String value) {
            addCriterion("BATCH_REG_DATE =", value, "batchRegDate");
            return (Criteria) this;
        }

        public Criteria andBatchRegDateNotEqualTo(String value) {
            addCriterion("BATCH_REG_DATE <>", value, "batchRegDate");
            return (Criteria) this;
        }

        public Criteria andBatchRegDateGreaterThan(String value) {
            addCriterion("BATCH_REG_DATE >", value, "batchRegDate");
            return (Criteria) this;
        }

        public Criteria andBatchRegDateGreaterThanOrEqualTo(String value) {
            addCriterion("BATCH_REG_DATE >=", value, "batchRegDate");
            return (Criteria) this;
        }

        public Criteria andBatchRegDateLessThan(String value) {
            addCriterion("BATCH_REG_DATE <", value, "batchRegDate");
            return (Criteria) this;
        }

        public Criteria andBatchRegDateLessThanOrEqualTo(String value) {
            addCriterion("BATCH_REG_DATE <=", value, "batchRegDate");
            return (Criteria) this;
        }

        public Criteria andBatchRegDateLike(String value) {
            addCriterion("BATCH_REG_DATE like", value, "batchRegDate");
            return (Criteria) this;
        }

        public Criteria andBatchRegDateNotLike(String value) {
            addCriterion("BATCH_REG_DATE not like", value, "batchRegDate");
            return (Criteria) this;
        }

        public Criteria andBatchRegDateIn(List<String> values) {
            addCriterion("BATCH_REG_DATE in", values, "batchRegDate");
            return (Criteria) this;
        }

        public Criteria andBatchRegDateNotIn(List<String> values) {
            addCriterion("BATCH_REG_DATE not in", values, "batchRegDate");
            return (Criteria) this;
        }

        public Criteria andBatchRegDateBetween(String value1, String value2) {
            addCriterion("BATCH_REG_DATE between", value1, value2, "batchRegDate");
            return (Criteria) this;
        }

        public Criteria andBatchRegDateNotBetween(String value1, String value2) {
            addCriterion("BATCH_REG_DATE not between", value1, value2, "batchRegDate");
            return (Criteria) this;
        }

        public Criteria andMonthFeeIsNull() {
            addCriterion("MONTH_FEE is null");
            return (Criteria) this;
        }

        public Criteria andMonthFeeIsNotNull() {
            addCriterion("MONTH_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andMonthFeeEqualTo(BigDecimal value) {
            addCriterion("MONTH_FEE =", value, "monthFee");
            return (Criteria) this;
        }

        public Criteria andMonthFeeNotEqualTo(BigDecimal value) {
            addCriterion("MONTH_FEE <>", value, "monthFee");
            return (Criteria) this;
        }

        public Criteria andMonthFeeGreaterThan(BigDecimal value) {
            addCriterion("MONTH_FEE >", value, "monthFee");
            return (Criteria) this;
        }

        public Criteria andMonthFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("MONTH_FEE >=", value, "monthFee");
            return (Criteria) this;
        }

        public Criteria andMonthFeeLessThan(BigDecimal value) {
            addCriterion("MONTH_FEE <", value, "monthFee");
            return (Criteria) this;
        }

        public Criteria andMonthFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("MONTH_FEE <=", value, "monthFee");
            return (Criteria) this;
        }

        public Criteria andMonthFeeIn(List<BigDecimal> values) {
            addCriterion("MONTH_FEE in", values, "monthFee");
            return (Criteria) this;
        }

        public Criteria andMonthFeeNotIn(List<BigDecimal> values) {
            addCriterion("MONTH_FEE not in", values, "monthFee");
            return (Criteria) this;
        }

        public Criteria andMonthFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MONTH_FEE between", value1, value2, "monthFee");
            return (Criteria) this;
        }

        public Criteria andMonthFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("MONTH_FEE not between", value1, value2, "monthFee");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIsNull() {
            addCriterion("IS_DEFAULT is null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIsNotNull() {
            addCriterion("IS_DEFAULT is not null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultEqualTo(String value) {
            addCriterion("IS_DEFAULT =", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotEqualTo(String value) {
            addCriterion("IS_DEFAULT <>", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultGreaterThan(String value) {
            addCriterion("IS_DEFAULT >", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultGreaterThanOrEqualTo(String value) {
            addCriterion("IS_DEFAULT >=", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLessThan(String value) {
            addCriterion("IS_DEFAULT <", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLessThanOrEqualTo(String value) {
            addCriterion("IS_DEFAULT <=", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLike(String value) {
            addCriterion("IS_DEFAULT like", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotLike(String value) {
            addCriterion("IS_DEFAULT not like", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIn(List<String> values) {
            addCriterion("IS_DEFAULT in", values, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotIn(List<String> values) {
            addCriterion("IS_DEFAULT not in", values, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultBetween(String value1, String value2) {
            addCriterion("IS_DEFAULT between", value1, value2, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotBetween(String value1, String value2) {
            addCriterion("IS_DEFAULT not between", value1, value2, "isDefault");
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

        public Criteria andBatchDateIsNull() {
            addCriterion("BATCH_DATE is null");
            return (Criteria) this;
        }

        public Criteria andBatchDateIsNotNull() {
            addCriterion("BATCH_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andBatchDateEqualTo(String value) {
            addCriterion("BATCH_DATE =", value, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateNotEqualTo(String value) {
            addCriterion("BATCH_DATE <>", value, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateGreaterThan(String value) {
            addCriterion("BATCH_DATE >", value, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateGreaterThanOrEqualTo(String value) {
            addCriterion("BATCH_DATE >=", value, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateLessThan(String value) {
            addCriterion("BATCH_DATE <", value, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateLessThanOrEqualTo(String value) {
            addCriterion("BATCH_DATE <=", value, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateLike(String value) {
            addCriterion("BATCH_DATE like", value, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateNotLike(String value) {
            addCriterion("BATCH_DATE not like", value, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateIn(List<String> values) {
            addCriterion("BATCH_DATE in", values, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateNotIn(List<String> values) {
            addCriterion("BATCH_DATE not in", values, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateBetween(String value1, String value2) {
            addCriterion("BATCH_DATE between", value1, value2, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchDateNotBetween(String value1, String value2) {
            addCriterion("BATCH_DATE not between", value1, value2, "batchDate");
            return (Criteria) this;
        }

        public Criteria andBatchStatusIsNull() {
            addCriterion("BATCH_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andBatchStatusIsNotNull() {
            addCriterion("BATCH_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andBatchStatusEqualTo(String value) {
            addCriterion("BATCH_STATUS =", value, "batchStatus");
            return (Criteria) this;
        }

        public Criteria andBatchStatusNotEqualTo(String value) {
            addCriterion("BATCH_STATUS <>", value, "batchStatus");
            return (Criteria) this;
        }

        public Criteria andBatchStatusGreaterThan(String value) {
            addCriterion("BATCH_STATUS >", value, "batchStatus");
            return (Criteria) this;
        }

        public Criteria andBatchStatusGreaterThanOrEqualTo(String value) {
            addCriterion("BATCH_STATUS >=", value, "batchStatus");
            return (Criteria) this;
        }

        public Criteria andBatchStatusLessThan(String value) {
            addCriterion("BATCH_STATUS <", value, "batchStatus");
            return (Criteria) this;
        }

        public Criteria andBatchStatusLessThanOrEqualTo(String value) {
            addCriterion("BATCH_STATUS <=", value, "batchStatus");
            return (Criteria) this;
        }

        public Criteria andBatchStatusLike(String value) {
            addCriterion("BATCH_STATUS like", value, "batchStatus");
            return (Criteria) this;
        }

        public Criteria andBatchStatusNotLike(String value) {
            addCriterion("BATCH_STATUS not like", value, "batchStatus");
            return (Criteria) this;
        }

        public Criteria andBatchStatusIn(List<String> values) {
            addCriterion("BATCH_STATUS in", values, "batchStatus");
            return (Criteria) this;
        }

        public Criteria andBatchStatusNotIn(List<String> values) {
            addCriterion("BATCH_STATUS not in", values, "batchStatus");
            return (Criteria) this;
        }

        public Criteria andBatchStatusBetween(String value1, String value2) {
            addCriterion("BATCH_STATUS between", value1, value2, "batchStatus");
            return (Criteria) this;
        }

        public Criteria andBatchStatusNotBetween(String value1, String value2) {
            addCriterion("BATCH_STATUS not between", value1, value2, "batchStatus");
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