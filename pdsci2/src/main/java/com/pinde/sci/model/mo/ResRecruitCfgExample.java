package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ResRecruitCfgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResRecruitCfgExample() {
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

        public Criteria andCfgFlowIsNull() {
            addCriterion("CFG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andCfgFlowIsNotNull() {
            addCriterion("CFG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andCfgFlowEqualTo(String value) {
            addCriterion("CFG_FLOW =", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowNotEqualTo(String value) {
            addCriterion("CFG_FLOW <>", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowGreaterThan(String value) {
            addCriterion("CFG_FLOW >", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CFG_FLOW >=", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowLessThan(String value) {
            addCriterion("CFG_FLOW <", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowLessThanOrEqualTo(String value) {
            addCriterion("CFG_FLOW <=", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowLike(String value) {
            addCriterion("CFG_FLOW like", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowNotLike(String value) {
            addCriterion("CFG_FLOW not like", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowIn(List<String> values) {
            addCriterion("CFG_FLOW in", values, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowNotIn(List<String> values) {
            addCriterion("CFG_FLOW not in", values, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowBetween(String value1, String value2) {
            addCriterion("CFG_FLOW between", value1, value2, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowNotBetween(String value1, String value2) {
            addCriterion("CFG_FLOW not between", value1, value2, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andYearIsNull() {
            addCriterion("YEAR is null");
            return (Criteria) this;
        }

        public Criteria andYearIsNotNull() {
            addCriterion("YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andYearEqualTo(String value) {
            addCriterion("YEAR =", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotEqualTo(String value) {
            addCriterion("YEAR <>", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThan(String value) {
            addCriterion("YEAR >", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualTo(String value) {
            addCriterion("YEAR >=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThan(String value) {
            addCriterion("YEAR <", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualTo(String value) {
            addCriterion("YEAR <=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLike(String value) {
            addCriterion("YEAR like", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotLike(String value) {
            addCriterion("YEAR not like", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearIn(List<String> values) {
            addCriterion("YEAR in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotIn(List<String> values) {
            addCriterion("YEAR not in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearBetween(String value1, String value2) {
            addCriterion("YEAR between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotBetween(String value1, String value2) {
            addCriterion("YEAR not between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andRegBeginDateIsNull() {
            addCriterion("REG_BEGIN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andRegBeginDateIsNotNull() {
            addCriterion("REG_BEGIN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andRegBeginDateEqualTo(String value) {
            addCriterion("REG_BEGIN_DATE =", value, "regBeginDate");
            return (Criteria) this;
        }

        public Criteria andRegBeginDateNotEqualTo(String value) {
            addCriterion("REG_BEGIN_DATE <>", value, "regBeginDate");
            return (Criteria) this;
        }

        public Criteria andRegBeginDateGreaterThan(String value) {
            addCriterion("REG_BEGIN_DATE >", value, "regBeginDate");
            return (Criteria) this;
        }

        public Criteria andRegBeginDateGreaterThanOrEqualTo(String value) {
            addCriterion("REG_BEGIN_DATE >=", value, "regBeginDate");
            return (Criteria) this;
        }

        public Criteria andRegBeginDateLessThan(String value) {
            addCriterion("REG_BEGIN_DATE <", value, "regBeginDate");
            return (Criteria) this;
        }

        public Criteria andRegBeginDateLessThanOrEqualTo(String value) {
            addCriterion("REG_BEGIN_DATE <=", value, "regBeginDate");
            return (Criteria) this;
        }

        public Criteria andRegBeginDateLike(String value) {
            addCriterion("REG_BEGIN_DATE like", value, "regBeginDate");
            return (Criteria) this;
        }

        public Criteria andRegBeginDateNotLike(String value) {
            addCriterion("REG_BEGIN_DATE not like", value, "regBeginDate");
            return (Criteria) this;
        }

        public Criteria andRegBeginDateIn(List<String> values) {
            addCriterion("REG_BEGIN_DATE in", values, "regBeginDate");
            return (Criteria) this;
        }

        public Criteria andRegBeginDateNotIn(List<String> values) {
            addCriterion("REG_BEGIN_DATE not in", values, "regBeginDate");
            return (Criteria) this;
        }

        public Criteria andRegBeginDateBetween(String value1, String value2) {
            addCriterion("REG_BEGIN_DATE between", value1, value2, "regBeginDate");
            return (Criteria) this;
        }

        public Criteria andRegBeginDateNotBetween(String value1, String value2) {
            addCriterion("REG_BEGIN_DATE not between", value1, value2, "regBeginDate");
            return (Criteria) this;
        }

        public Criteria andRegEndDateIsNull() {
            addCriterion("REG_END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andRegEndDateIsNotNull() {
            addCriterion("REG_END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andRegEndDateEqualTo(String value) {
            addCriterion("REG_END_DATE =", value, "regEndDate");
            return (Criteria) this;
        }

        public Criteria andRegEndDateNotEqualTo(String value) {
            addCriterion("REG_END_DATE <>", value, "regEndDate");
            return (Criteria) this;
        }

        public Criteria andRegEndDateGreaterThan(String value) {
            addCriterion("REG_END_DATE >", value, "regEndDate");
            return (Criteria) this;
        }

        public Criteria andRegEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("REG_END_DATE >=", value, "regEndDate");
            return (Criteria) this;
        }

        public Criteria andRegEndDateLessThan(String value) {
            addCriterion("REG_END_DATE <", value, "regEndDate");
            return (Criteria) this;
        }

        public Criteria andRegEndDateLessThanOrEqualTo(String value) {
            addCriterion("REG_END_DATE <=", value, "regEndDate");
            return (Criteria) this;
        }

        public Criteria andRegEndDateLike(String value) {
            addCriterion("REG_END_DATE like", value, "regEndDate");
            return (Criteria) this;
        }

        public Criteria andRegEndDateNotLike(String value) {
            addCriterion("REG_END_DATE not like", value, "regEndDate");
            return (Criteria) this;
        }

        public Criteria andRegEndDateIn(List<String> values) {
            addCriterion("REG_END_DATE in", values, "regEndDate");
            return (Criteria) this;
        }

        public Criteria andRegEndDateNotIn(List<String> values) {
            addCriterion("REG_END_DATE not in", values, "regEndDate");
            return (Criteria) this;
        }

        public Criteria andRegEndDateBetween(String value1, String value2) {
            addCriterion("REG_END_DATE between", value1, value2, "regEndDate");
            return (Criteria) this;
        }

        public Criteria andRegEndDateNotBetween(String value1, String value2) {
            addCriterion("REG_END_DATE not between", value1, value2, "regEndDate");
            return (Criteria) this;
        }

        public Criteria andPrintBeginDateIsNull() {
            addCriterion("PRINT_BEGIN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andPrintBeginDateIsNotNull() {
            addCriterion("PRINT_BEGIN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andPrintBeginDateEqualTo(String value) {
            addCriterion("PRINT_BEGIN_DATE =", value, "printBeginDate");
            return (Criteria) this;
        }

        public Criteria andPrintBeginDateNotEqualTo(String value) {
            addCriterion("PRINT_BEGIN_DATE <>", value, "printBeginDate");
            return (Criteria) this;
        }

        public Criteria andPrintBeginDateGreaterThan(String value) {
            addCriterion("PRINT_BEGIN_DATE >", value, "printBeginDate");
            return (Criteria) this;
        }

        public Criteria andPrintBeginDateGreaterThanOrEqualTo(String value) {
            addCriterion("PRINT_BEGIN_DATE >=", value, "printBeginDate");
            return (Criteria) this;
        }

        public Criteria andPrintBeginDateLessThan(String value) {
            addCriterion("PRINT_BEGIN_DATE <", value, "printBeginDate");
            return (Criteria) this;
        }

        public Criteria andPrintBeginDateLessThanOrEqualTo(String value) {
            addCriterion("PRINT_BEGIN_DATE <=", value, "printBeginDate");
            return (Criteria) this;
        }

        public Criteria andPrintBeginDateLike(String value) {
            addCriterion("PRINT_BEGIN_DATE like", value, "printBeginDate");
            return (Criteria) this;
        }

        public Criteria andPrintBeginDateNotLike(String value) {
            addCriterion("PRINT_BEGIN_DATE not like", value, "printBeginDate");
            return (Criteria) this;
        }

        public Criteria andPrintBeginDateIn(List<String> values) {
            addCriterion("PRINT_BEGIN_DATE in", values, "printBeginDate");
            return (Criteria) this;
        }

        public Criteria andPrintBeginDateNotIn(List<String> values) {
            addCriterion("PRINT_BEGIN_DATE not in", values, "printBeginDate");
            return (Criteria) this;
        }

        public Criteria andPrintBeginDateBetween(String value1, String value2) {
            addCriterion("PRINT_BEGIN_DATE between", value1, value2, "printBeginDate");
            return (Criteria) this;
        }

        public Criteria andPrintBeginDateNotBetween(String value1, String value2) {
            addCriterion("PRINT_BEGIN_DATE not between", value1, value2, "printBeginDate");
            return (Criteria) this;
        }

        public Criteria andPrintEndDateIsNull() {
            addCriterion("PRINT_END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andPrintEndDateIsNotNull() {
            addCriterion("PRINT_END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andPrintEndDateEqualTo(String value) {
            addCriterion("PRINT_END_DATE =", value, "printEndDate");
            return (Criteria) this;
        }

        public Criteria andPrintEndDateNotEqualTo(String value) {
            addCriterion("PRINT_END_DATE <>", value, "printEndDate");
            return (Criteria) this;
        }

        public Criteria andPrintEndDateGreaterThan(String value) {
            addCriterion("PRINT_END_DATE >", value, "printEndDate");
            return (Criteria) this;
        }

        public Criteria andPrintEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("PRINT_END_DATE >=", value, "printEndDate");
            return (Criteria) this;
        }

        public Criteria andPrintEndDateLessThan(String value) {
            addCriterion("PRINT_END_DATE <", value, "printEndDate");
            return (Criteria) this;
        }

        public Criteria andPrintEndDateLessThanOrEqualTo(String value) {
            addCriterion("PRINT_END_DATE <=", value, "printEndDate");
            return (Criteria) this;
        }

        public Criteria andPrintEndDateLike(String value) {
            addCriterion("PRINT_END_DATE like", value, "printEndDate");
            return (Criteria) this;
        }

        public Criteria andPrintEndDateNotLike(String value) {
            addCriterion("PRINT_END_DATE not like", value, "printEndDate");
            return (Criteria) this;
        }

        public Criteria andPrintEndDateIn(List<String> values) {
            addCriterion("PRINT_END_DATE in", values, "printEndDate");
            return (Criteria) this;
        }

        public Criteria andPrintEndDateNotIn(List<String> values) {
            addCriterion("PRINT_END_DATE not in", values, "printEndDate");
            return (Criteria) this;
        }

        public Criteria andPrintEndDateBetween(String value1, String value2) {
            addCriterion("PRINT_END_DATE between", value1, value2, "printEndDate");
            return (Criteria) this;
        }

        public Criteria andPrintEndDateNotBetween(String value1, String value2) {
            addCriterion("PRINT_END_DATE not between", value1, value2, "printEndDate");
            return (Criteria) this;
        }

        public Criteria andWishBeginDateIsNull() {
            addCriterion("WISH_BEGIN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andWishBeginDateIsNotNull() {
            addCriterion("WISH_BEGIN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andWishBeginDateEqualTo(String value) {
            addCriterion("WISH_BEGIN_DATE =", value, "wishBeginDate");
            return (Criteria) this;
        }

        public Criteria andWishBeginDateNotEqualTo(String value) {
            addCriterion("WISH_BEGIN_DATE <>", value, "wishBeginDate");
            return (Criteria) this;
        }

        public Criteria andWishBeginDateGreaterThan(String value) {
            addCriterion("WISH_BEGIN_DATE >", value, "wishBeginDate");
            return (Criteria) this;
        }

        public Criteria andWishBeginDateGreaterThanOrEqualTo(String value) {
            addCriterion("WISH_BEGIN_DATE >=", value, "wishBeginDate");
            return (Criteria) this;
        }

        public Criteria andWishBeginDateLessThan(String value) {
            addCriterion("WISH_BEGIN_DATE <", value, "wishBeginDate");
            return (Criteria) this;
        }

        public Criteria andWishBeginDateLessThanOrEqualTo(String value) {
            addCriterion("WISH_BEGIN_DATE <=", value, "wishBeginDate");
            return (Criteria) this;
        }

        public Criteria andWishBeginDateLike(String value) {
            addCriterion("WISH_BEGIN_DATE like", value, "wishBeginDate");
            return (Criteria) this;
        }

        public Criteria andWishBeginDateNotLike(String value) {
            addCriterion("WISH_BEGIN_DATE not like", value, "wishBeginDate");
            return (Criteria) this;
        }

        public Criteria andWishBeginDateIn(List<String> values) {
            addCriterion("WISH_BEGIN_DATE in", values, "wishBeginDate");
            return (Criteria) this;
        }

        public Criteria andWishBeginDateNotIn(List<String> values) {
            addCriterion("WISH_BEGIN_DATE not in", values, "wishBeginDate");
            return (Criteria) this;
        }

        public Criteria andWishBeginDateBetween(String value1, String value2) {
            addCriterion("WISH_BEGIN_DATE between", value1, value2, "wishBeginDate");
            return (Criteria) this;
        }

        public Criteria andWishBeginDateNotBetween(String value1, String value2) {
            addCriterion("WISH_BEGIN_DATE not between", value1, value2, "wishBeginDate");
            return (Criteria) this;
        }

        public Criteria andWishEndDateIsNull() {
            addCriterion("WISH_END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andWishEndDateIsNotNull() {
            addCriterion("WISH_END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andWishEndDateEqualTo(String value) {
            addCriterion("WISH_END_DATE =", value, "wishEndDate");
            return (Criteria) this;
        }

        public Criteria andWishEndDateNotEqualTo(String value) {
            addCriterion("WISH_END_DATE <>", value, "wishEndDate");
            return (Criteria) this;
        }

        public Criteria andWishEndDateGreaterThan(String value) {
            addCriterion("WISH_END_DATE >", value, "wishEndDate");
            return (Criteria) this;
        }

        public Criteria andWishEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("WISH_END_DATE >=", value, "wishEndDate");
            return (Criteria) this;
        }

        public Criteria andWishEndDateLessThan(String value) {
            addCriterion("WISH_END_DATE <", value, "wishEndDate");
            return (Criteria) this;
        }

        public Criteria andWishEndDateLessThanOrEqualTo(String value) {
            addCriterion("WISH_END_DATE <=", value, "wishEndDate");
            return (Criteria) this;
        }

        public Criteria andWishEndDateLike(String value) {
            addCriterion("WISH_END_DATE like", value, "wishEndDate");
            return (Criteria) this;
        }

        public Criteria andWishEndDateNotLike(String value) {
            addCriterion("WISH_END_DATE not like", value, "wishEndDate");
            return (Criteria) this;
        }

        public Criteria andWishEndDateIn(List<String> values) {
            addCriterion("WISH_END_DATE in", values, "wishEndDate");
            return (Criteria) this;
        }

        public Criteria andWishEndDateNotIn(List<String> values) {
            addCriterion("WISH_END_DATE not in", values, "wishEndDate");
            return (Criteria) this;
        }

        public Criteria andWishEndDateBetween(String value1, String value2) {
            addCriterion("WISH_END_DATE between", value1, value2, "wishEndDate");
            return (Criteria) this;
        }

        public Criteria andWishEndDateNotBetween(String value1, String value2) {
            addCriterion("WISH_END_DATE not between", value1, value2, "wishEndDate");
            return (Criteria) this;
        }

        public Criteria andAdmitBeginDateIsNull() {
            addCriterion("ADMIT_BEGIN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andAdmitBeginDateIsNotNull() {
            addCriterion("ADMIT_BEGIN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andAdmitBeginDateEqualTo(String value) {
            addCriterion("ADMIT_BEGIN_DATE =", value, "admitBeginDate");
            return (Criteria) this;
        }

        public Criteria andAdmitBeginDateNotEqualTo(String value) {
            addCriterion("ADMIT_BEGIN_DATE <>", value, "admitBeginDate");
            return (Criteria) this;
        }

        public Criteria andAdmitBeginDateGreaterThan(String value) {
            addCriterion("ADMIT_BEGIN_DATE >", value, "admitBeginDate");
            return (Criteria) this;
        }

        public Criteria andAdmitBeginDateGreaterThanOrEqualTo(String value) {
            addCriterion("ADMIT_BEGIN_DATE >=", value, "admitBeginDate");
            return (Criteria) this;
        }

        public Criteria andAdmitBeginDateLessThan(String value) {
            addCriterion("ADMIT_BEGIN_DATE <", value, "admitBeginDate");
            return (Criteria) this;
        }

        public Criteria andAdmitBeginDateLessThanOrEqualTo(String value) {
            addCriterion("ADMIT_BEGIN_DATE <=", value, "admitBeginDate");
            return (Criteria) this;
        }

        public Criteria andAdmitBeginDateLike(String value) {
            addCriterion("ADMIT_BEGIN_DATE like", value, "admitBeginDate");
            return (Criteria) this;
        }

        public Criteria andAdmitBeginDateNotLike(String value) {
            addCriterion("ADMIT_BEGIN_DATE not like", value, "admitBeginDate");
            return (Criteria) this;
        }

        public Criteria andAdmitBeginDateIn(List<String> values) {
            addCriterion("ADMIT_BEGIN_DATE in", values, "admitBeginDate");
            return (Criteria) this;
        }

        public Criteria andAdmitBeginDateNotIn(List<String> values) {
            addCriterion("ADMIT_BEGIN_DATE not in", values, "admitBeginDate");
            return (Criteria) this;
        }

        public Criteria andAdmitBeginDateBetween(String value1, String value2) {
            addCriterion("ADMIT_BEGIN_DATE between", value1, value2, "admitBeginDate");
            return (Criteria) this;
        }

        public Criteria andAdmitBeginDateNotBetween(String value1, String value2) {
            addCriterion("ADMIT_BEGIN_DATE not between", value1, value2, "admitBeginDate");
            return (Criteria) this;
        }

        public Criteria andAdmitEndDateIsNull() {
            addCriterion("ADMIT_END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andAdmitEndDateIsNotNull() {
            addCriterion("ADMIT_END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andAdmitEndDateEqualTo(String value) {
            addCriterion("ADMIT_END_DATE =", value, "admitEndDate");
            return (Criteria) this;
        }

        public Criteria andAdmitEndDateNotEqualTo(String value) {
            addCriterion("ADMIT_END_DATE <>", value, "admitEndDate");
            return (Criteria) this;
        }

        public Criteria andAdmitEndDateGreaterThan(String value) {
            addCriterion("ADMIT_END_DATE >", value, "admitEndDate");
            return (Criteria) this;
        }

        public Criteria andAdmitEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("ADMIT_END_DATE >=", value, "admitEndDate");
            return (Criteria) this;
        }

        public Criteria andAdmitEndDateLessThan(String value) {
            addCriterion("ADMIT_END_DATE <", value, "admitEndDate");
            return (Criteria) this;
        }

        public Criteria andAdmitEndDateLessThanOrEqualTo(String value) {
            addCriterion("ADMIT_END_DATE <=", value, "admitEndDate");
            return (Criteria) this;
        }

        public Criteria andAdmitEndDateLike(String value) {
            addCriterion("ADMIT_END_DATE like", value, "admitEndDate");
            return (Criteria) this;
        }

        public Criteria andAdmitEndDateNotLike(String value) {
            addCriterion("ADMIT_END_DATE not like", value, "admitEndDate");
            return (Criteria) this;
        }

        public Criteria andAdmitEndDateIn(List<String> values) {
            addCriterion("ADMIT_END_DATE in", values, "admitEndDate");
            return (Criteria) this;
        }

        public Criteria andAdmitEndDateNotIn(List<String> values) {
            addCriterion("ADMIT_END_DATE not in", values, "admitEndDate");
            return (Criteria) this;
        }

        public Criteria andAdmitEndDateBetween(String value1, String value2) {
            addCriterion("ADMIT_END_DATE between", value1, value2, "admitEndDate");
            return (Criteria) this;
        }

        public Criteria andAdmitEndDateNotBetween(String value1, String value2) {
            addCriterion("ADMIT_END_DATE not between", value1, value2, "admitEndDate");
            return (Criteria) this;
        }

        public Criteria andSwapBeginDateIsNull() {
            addCriterion("SWAP_BEGIN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSwapBeginDateIsNotNull() {
            addCriterion("SWAP_BEGIN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSwapBeginDateEqualTo(String value) {
            addCriterion("SWAP_BEGIN_DATE =", value, "swapBeginDate");
            return (Criteria) this;
        }

        public Criteria andSwapBeginDateNotEqualTo(String value) {
            addCriterion("SWAP_BEGIN_DATE <>", value, "swapBeginDate");
            return (Criteria) this;
        }

        public Criteria andSwapBeginDateGreaterThan(String value) {
            addCriterion("SWAP_BEGIN_DATE >", value, "swapBeginDate");
            return (Criteria) this;
        }

        public Criteria andSwapBeginDateGreaterThanOrEqualTo(String value) {
            addCriterion("SWAP_BEGIN_DATE >=", value, "swapBeginDate");
            return (Criteria) this;
        }

        public Criteria andSwapBeginDateLessThan(String value) {
            addCriterion("SWAP_BEGIN_DATE <", value, "swapBeginDate");
            return (Criteria) this;
        }

        public Criteria andSwapBeginDateLessThanOrEqualTo(String value) {
            addCriterion("SWAP_BEGIN_DATE <=", value, "swapBeginDate");
            return (Criteria) this;
        }

        public Criteria andSwapBeginDateLike(String value) {
            addCriterion("SWAP_BEGIN_DATE like", value, "swapBeginDate");
            return (Criteria) this;
        }

        public Criteria andSwapBeginDateNotLike(String value) {
            addCriterion("SWAP_BEGIN_DATE not like", value, "swapBeginDate");
            return (Criteria) this;
        }

        public Criteria andSwapBeginDateIn(List<String> values) {
            addCriterion("SWAP_BEGIN_DATE in", values, "swapBeginDate");
            return (Criteria) this;
        }

        public Criteria andSwapBeginDateNotIn(List<String> values) {
            addCriterion("SWAP_BEGIN_DATE not in", values, "swapBeginDate");
            return (Criteria) this;
        }

        public Criteria andSwapBeginDateBetween(String value1, String value2) {
            addCriterion("SWAP_BEGIN_DATE between", value1, value2, "swapBeginDate");
            return (Criteria) this;
        }

        public Criteria andSwapBeginDateNotBetween(String value1, String value2) {
            addCriterion("SWAP_BEGIN_DATE not between", value1, value2, "swapBeginDate");
            return (Criteria) this;
        }

        public Criteria andSwapEndDateIsNull() {
            addCriterion("SWAP_END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSwapEndDateIsNotNull() {
            addCriterion("SWAP_END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSwapEndDateEqualTo(String value) {
            addCriterion("SWAP_END_DATE =", value, "swapEndDate");
            return (Criteria) this;
        }

        public Criteria andSwapEndDateNotEqualTo(String value) {
            addCriterion("SWAP_END_DATE <>", value, "swapEndDate");
            return (Criteria) this;
        }

        public Criteria andSwapEndDateGreaterThan(String value) {
            addCriterion("SWAP_END_DATE >", value, "swapEndDate");
            return (Criteria) this;
        }

        public Criteria andSwapEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("SWAP_END_DATE >=", value, "swapEndDate");
            return (Criteria) this;
        }

        public Criteria andSwapEndDateLessThan(String value) {
            addCriterion("SWAP_END_DATE <", value, "swapEndDate");
            return (Criteria) this;
        }

        public Criteria andSwapEndDateLessThanOrEqualTo(String value) {
            addCriterion("SWAP_END_DATE <=", value, "swapEndDate");
            return (Criteria) this;
        }

        public Criteria andSwapEndDateLike(String value) {
            addCriterion("SWAP_END_DATE like", value, "swapEndDate");
            return (Criteria) this;
        }

        public Criteria andSwapEndDateNotLike(String value) {
            addCriterion("SWAP_END_DATE not like", value, "swapEndDate");
            return (Criteria) this;
        }

        public Criteria andSwapEndDateIn(List<String> values) {
            addCriterion("SWAP_END_DATE in", values, "swapEndDate");
            return (Criteria) this;
        }

        public Criteria andSwapEndDateNotIn(List<String> values) {
            addCriterion("SWAP_END_DATE not in", values, "swapEndDate");
            return (Criteria) this;
        }

        public Criteria andSwapEndDateBetween(String value1, String value2) {
            addCriterion("SWAP_END_DATE between", value1, value2, "swapEndDate");
            return (Criteria) this;
        }

        public Criteria andSwapEndDateNotBetween(String value1, String value2) {
            addCriterion("SWAP_END_DATE not between", value1, value2, "swapEndDate");
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

        public Criteria andRegistrationBeginDateIsNull() {
            addCriterion("REGISTRATION_BEGIN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andRegistrationBeginDateIsNotNull() {
            addCriterion("REGISTRATION_BEGIN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andRegistrationBeginDateEqualTo(String value) {
            addCriterion("REGISTRATION_BEGIN_DATE =", value, "registrationBeginDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationBeginDateNotEqualTo(String value) {
            addCriterion("REGISTRATION_BEGIN_DATE <>", value, "registrationBeginDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationBeginDateGreaterThan(String value) {
            addCriterion("REGISTRATION_BEGIN_DATE >", value, "registrationBeginDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationBeginDateGreaterThanOrEqualTo(String value) {
            addCriterion("REGISTRATION_BEGIN_DATE >=", value, "registrationBeginDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationBeginDateLessThan(String value) {
            addCriterion("REGISTRATION_BEGIN_DATE <", value, "registrationBeginDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationBeginDateLessThanOrEqualTo(String value) {
            addCriterion("REGISTRATION_BEGIN_DATE <=", value, "registrationBeginDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationBeginDateLike(String value) {
            addCriterion("REGISTRATION_BEGIN_DATE like", value, "registrationBeginDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationBeginDateNotLike(String value) {
            addCriterion("REGISTRATION_BEGIN_DATE not like", value, "registrationBeginDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationBeginDateIn(List<String> values) {
            addCriterion("REGISTRATION_BEGIN_DATE in", values, "registrationBeginDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationBeginDateNotIn(List<String> values) {
            addCriterion("REGISTRATION_BEGIN_DATE not in", values, "registrationBeginDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationBeginDateBetween(String value1, String value2) {
            addCriterion("REGISTRATION_BEGIN_DATE between", value1, value2, "registrationBeginDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationBeginDateNotBetween(String value1, String value2) {
            addCriterion("REGISTRATION_BEGIN_DATE not between", value1, value2, "registrationBeginDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndDateIsNull() {
            addCriterion("REGISTRATION_END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndDateIsNotNull() {
            addCriterion("REGISTRATION_END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndDateEqualTo(String value) {
            addCriterion("REGISTRATION_END_DATE =", value, "registrationEndDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndDateNotEqualTo(String value) {
            addCriterion("REGISTRATION_END_DATE <>", value, "registrationEndDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndDateGreaterThan(String value) {
            addCriterion("REGISTRATION_END_DATE >", value, "registrationEndDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("REGISTRATION_END_DATE >=", value, "registrationEndDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndDateLessThan(String value) {
            addCriterion("REGISTRATION_END_DATE <", value, "registrationEndDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndDateLessThanOrEqualTo(String value) {
            addCriterion("REGISTRATION_END_DATE <=", value, "registrationEndDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndDateLike(String value) {
            addCriterion("REGISTRATION_END_DATE like", value, "registrationEndDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndDateNotLike(String value) {
            addCriterion("REGISTRATION_END_DATE not like", value, "registrationEndDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndDateIn(List<String> values) {
            addCriterion("REGISTRATION_END_DATE in", values, "registrationEndDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndDateNotIn(List<String> values) {
            addCriterion("REGISTRATION_END_DATE not in", values, "registrationEndDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndDateBetween(String value1, String value2) {
            addCriterion("REGISTRATION_END_DATE between", value1, value2, "registrationEndDate");
            return (Criteria) this;
        }

        public Criteria andRegistrationEndDateNotBetween(String value1, String value2) {
            addCriterion("REGISTRATION_END_DATE not between", value1, value2, "registrationEndDate");
            return (Criteria) this;
        }

        public Criteria andGraduationBeginDateIsNull() {
            addCriterion("GRADUATION_BEGIN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andGraduationBeginDateIsNotNull() {
            addCriterion("GRADUATION_BEGIN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationBeginDateEqualTo(String value) {
            addCriterion("GRADUATION_BEGIN_DATE =", value, "graduationBeginDate");
            return (Criteria) this;
        }

        public Criteria andGraduationBeginDateNotEqualTo(String value) {
            addCriterion("GRADUATION_BEGIN_DATE <>", value, "graduationBeginDate");
            return (Criteria) this;
        }

        public Criteria andGraduationBeginDateGreaterThan(String value) {
            addCriterion("GRADUATION_BEGIN_DATE >", value, "graduationBeginDate");
            return (Criteria) this;
        }

        public Criteria andGraduationBeginDateGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATION_BEGIN_DATE >=", value, "graduationBeginDate");
            return (Criteria) this;
        }

        public Criteria andGraduationBeginDateLessThan(String value) {
            addCriterion("GRADUATION_BEGIN_DATE <", value, "graduationBeginDate");
            return (Criteria) this;
        }

        public Criteria andGraduationBeginDateLessThanOrEqualTo(String value) {
            addCriterion("GRADUATION_BEGIN_DATE <=", value, "graduationBeginDate");
            return (Criteria) this;
        }

        public Criteria andGraduationBeginDateLike(String value) {
            addCriterion("GRADUATION_BEGIN_DATE like", value, "graduationBeginDate");
            return (Criteria) this;
        }

        public Criteria andGraduationBeginDateNotLike(String value) {
            addCriterion("GRADUATION_BEGIN_DATE not like", value, "graduationBeginDate");
            return (Criteria) this;
        }

        public Criteria andGraduationBeginDateIn(List<String> values) {
            addCriterion("GRADUATION_BEGIN_DATE in", values, "graduationBeginDate");
            return (Criteria) this;
        }

        public Criteria andGraduationBeginDateNotIn(List<String> values) {
            addCriterion("GRADUATION_BEGIN_DATE not in", values, "graduationBeginDate");
            return (Criteria) this;
        }

        public Criteria andGraduationBeginDateBetween(String value1, String value2) {
            addCriterion("GRADUATION_BEGIN_DATE between", value1, value2, "graduationBeginDate");
            return (Criteria) this;
        }

        public Criteria andGraduationBeginDateNotBetween(String value1, String value2) {
            addCriterion("GRADUATION_BEGIN_DATE not between", value1, value2, "graduationBeginDate");
            return (Criteria) this;
        }

        public Criteria andGraduationEndDateIsNull() {
            addCriterion("GRADUATION_END_DATE is null");
            return (Criteria) this;
        }

        public Criteria andGraduationEndDateIsNotNull() {
            addCriterion("GRADUATION_END_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andGraduationEndDateEqualTo(String value) {
            addCriterion("GRADUATION_END_DATE =", value, "graduationEndDate");
            return (Criteria) this;
        }

        public Criteria andGraduationEndDateNotEqualTo(String value) {
            addCriterion("GRADUATION_END_DATE <>", value, "graduationEndDate");
            return (Criteria) this;
        }

        public Criteria andGraduationEndDateGreaterThan(String value) {
            addCriterion("GRADUATION_END_DATE >", value, "graduationEndDate");
            return (Criteria) this;
        }

        public Criteria andGraduationEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("GRADUATION_END_DATE >=", value, "graduationEndDate");
            return (Criteria) this;
        }

        public Criteria andGraduationEndDateLessThan(String value) {
            addCriterion("GRADUATION_END_DATE <", value, "graduationEndDate");
            return (Criteria) this;
        }

        public Criteria andGraduationEndDateLessThanOrEqualTo(String value) {
            addCriterion("GRADUATION_END_DATE <=", value, "graduationEndDate");
            return (Criteria) this;
        }

        public Criteria andGraduationEndDateLike(String value) {
            addCriterion("GRADUATION_END_DATE like", value, "graduationEndDate");
            return (Criteria) this;
        }

        public Criteria andGraduationEndDateNotLike(String value) {
            addCriterion("GRADUATION_END_DATE not like", value, "graduationEndDate");
            return (Criteria) this;
        }

        public Criteria andGraduationEndDateIn(List<String> values) {
            addCriterion("GRADUATION_END_DATE in", values, "graduationEndDate");
            return (Criteria) this;
        }

        public Criteria andGraduationEndDateNotIn(List<String> values) {
            addCriterion("GRADUATION_END_DATE not in", values, "graduationEndDate");
            return (Criteria) this;
        }

        public Criteria andGraduationEndDateBetween(String value1, String value2) {
            addCriterion("GRADUATION_END_DATE between", value1, value2, "graduationEndDate");
            return (Criteria) this;
        }

        public Criteria andGraduationEndDateNotBetween(String value1, String value2) {
            addCriterion("GRADUATION_END_DATE not between", value1, value2, "graduationEndDate");
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