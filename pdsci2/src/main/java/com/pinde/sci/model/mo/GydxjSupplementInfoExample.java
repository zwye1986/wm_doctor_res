package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class GydxjSupplementInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GydxjSupplementInfoExample() {
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

        public Criteria andXhIsNull() {
            addCriterion("XH is null");
            return (Criteria) this;
        }

        public Criteria andXhIsNotNull() {
            addCriterion("XH is not null");
            return (Criteria) this;
        }

        public Criteria andXhEqualTo(String value) {
            addCriterion("XH =", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotEqualTo(String value) {
            addCriterion("XH <>", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhGreaterThan(String value) {
            addCriterion("XH >", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhGreaterThanOrEqualTo(String value) {
            addCriterion("XH >=", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLessThan(String value) {
            addCriterion("XH <", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLessThanOrEqualTo(String value) {
            addCriterion("XH <=", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLike(String value) {
            addCriterion("XH like", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotLike(String value) {
            addCriterion("XH not like", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhIn(List<String> values) {
            addCriterion("XH in", values, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotIn(List<String> values) {
            addCriterion("XH not in", values, "xh");
            return (Criteria) this;
        }

        public Criteria andXhBetween(String value1, String value2) {
            addCriterion("XH between", value1, value2, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotBetween(String value1, String value2) {
            addCriterion("XH not between", value1, value2, "xh");
            return (Criteria) this;
        }

        public Criteria andXmIsNull() {
            addCriterion("XM is null");
            return (Criteria) this;
        }

        public Criteria andXmIsNotNull() {
            addCriterion("XM is not null");
            return (Criteria) this;
        }

        public Criteria andXmEqualTo(String value) {
            addCriterion("XM =", value, "xm");
            return (Criteria) this;
        }

        public Criteria andXmNotEqualTo(String value) {
            addCriterion("XM <>", value, "xm");
            return (Criteria) this;
        }

        public Criteria andXmGreaterThan(String value) {
            addCriterion("XM >", value, "xm");
            return (Criteria) this;
        }

        public Criteria andXmGreaterThanOrEqualTo(String value) {
            addCriterion("XM >=", value, "xm");
            return (Criteria) this;
        }

        public Criteria andXmLessThan(String value) {
            addCriterion("XM <", value, "xm");
            return (Criteria) this;
        }

        public Criteria andXmLessThanOrEqualTo(String value) {
            addCriterion("XM <=", value, "xm");
            return (Criteria) this;
        }

        public Criteria andXmLike(String value) {
            addCriterion("XM like", value, "xm");
            return (Criteria) this;
        }

        public Criteria andXmNotLike(String value) {
            addCriterion("XM not like", value, "xm");
            return (Criteria) this;
        }

        public Criteria andXmIn(List<String> values) {
            addCriterion("XM in", values, "xm");
            return (Criteria) this;
        }

        public Criteria andXmNotIn(List<String> values) {
            addCriterion("XM not in", values, "xm");
            return (Criteria) this;
        }

        public Criteria andXmBetween(String value1, String value2) {
            addCriterion("XM between", value1, value2, "xm");
            return (Criteria) this;
        }

        public Criteria andXmNotBetween(String value1, String value2) {
            addCriterion("XM not between", value1, value2, "xm");
            return (Criteria) this;
        }

        public Criteria andZjlxIdIsNull() {
            addCriterion("ZJLX_ID is null");
            return (Criteria) this;
        }

        public Criteria andZjlxIdIsNotNull() {
            addCriterion("ZJLX_ID is not null");
            return (Criteria) this;
        }

        public Criteria andZjlxIdEqualTo(String value) {
            addCriterion("ZJLX_ID =", value, "zjlxId");
            return (Criteria) this;
        }

        public Criteria andZjlxIdNotEqualTo(String value) {
            addCriterion("ZJLX_ID <>", value, "zjlxId");
            return (Criteria) this;
        }

        public Criteria andZjlxIdGreaterThan(String value) {
            addCriterion("ZJLX_ID >", value, "zjlxId");
            return (Criteria) this;
        }

        public Criteria andZjlxIdGreaterThanOrEqualTo(String value) {
            addCriterion("ZJLX_ID >=", value, "zjlxId");
            return (Criteria) this;
        }

        public Criteria andZjlxIdLessThan(String value) {
            addCriterion("ZJLX_ID <", value, "zjlxId");
            return (Criteria) this;
        }

        public Criteria andZjlxIdLessThanOrEqualTo(String value) {
            addCriterion("ZJLX_ID <=", value, "zjlxId");
            return (Criteria) this;
        }

        public Criteria andZjlxIdLike(String value) {
            addCriterion("ZJLX_ID like", value, "zjlxId");
            return (Criteria) this;
        }

        public Criteria andZjlxIdNotLike(String value) {
            addCriterion("ZJLX_ID not like", value, "zjlxId");
            return (Criteria) this;
        }

        public Criteria andZjlxIdIn(List<String> values) {
            addCriterion("ZJLX_ID in", values, "zjlxId");
            return (Criteria) this;
        }

        public Criteria andZjlxIdNotIn(List<String> values) {
            addCriterion("ZJLX_ID not in", values, "zjlxId");
            return (Criteria) this;
        }

        public Criteria andZjlxIdBetween(String value1, String value2) {
            addCriterion("ZJLX_ID between", value1, value2, "zjlxId");
            return (Criteria) this;
        }

        public Criteria andZjlxIdNotBetween(String value1, String value2) {
            addCriterion("ZJLX_ID not between", value1, value2, "zjlxId");
            return (Criteria) this;
        }

        public Criteria andZjlxIsNull() {
            addCriterion("ZJLX is null");
            return (Criteria) this;
        }

        public Criteria andZjlxIsNotNull() {
            addCriterion("ZJLX is not null");
            return (Criteria) this;
        }

        public Criteria andZjlxEqualTo(String value) {
            addCriterion("ZJLX =", value, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxNotEqualTo(String value) {
            addCriterion("ZJLX <>", value, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxGreaterThan(String value) {
            addCriterion("ZJLX >", value, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxGreaterThanOrEqualTo(String value) {
            addCriterion("ZJLX >=", value, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxLessThan(String value) {
            addCriterion("ZJLX <", value, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxLessThanOrEqualTo(String value) {
            addCriterion("ZJLX <=", value, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxLike(String value) {
            addCriterion("ZJLX like", value, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxNotLike(String value) {
            addCriterion("ZJLX not like", value, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxIn(List<String> values) {
            addCriterion("ZJLX in", values, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxNotIn(List<String> values) {
            addCriterion("ZJLX not in", values, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxBetween(String value1, String value2) {
            addCriterion("ZJLX between", value1, value2, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxNotBetween(String value1, String value2) {
            addCriterion("ZJLX not between", value1, value2, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjhmIsNull() {
            addCriterion("ZJHM is null");
            return (Criteria) this;
        }

        public Criteria andZjhmIsNotNull() {
            addCriterion("ZJHM is not null");
            return (Criteria) this;
        }

        public Criteria andZjhmEqualTo(String value) {
            addCriterion("ZJHM =", value, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmNotEqualTo(String value) {
            addCriterion("ZJHM <>", value, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmGreaterThan(String value) {
            addCriterion("ZJHM >", value, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmGreaterThanOrEqualTo(String value) {
            addCriterion("ZJHM >=", value, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmLessThan(String value) {
            addCriterion("ZJHM <", value, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmLessThanOrEqualTo(String value) {
            addCriterion("ZJHM <=", value, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmLike(String value) {
            addCriterion("ZJHM like", value, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmNotLike(String value) {
            addCriterion("ZJHM not like", value, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmIn(List<String> values) {
            addCriterion("ZJHM in", values, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmNotIn(List<String> values) {
            addCriterion("ZJHM not in", values, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmBetween(String value1, String value2) {
            addCriterion("ZJHM between", value1, value2, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmNotBetween(String value1, String value2) {
            addCriterion("ZJHM not between", value1, value2, "zjhm");
            return (Criteria) this;
        }

        public Criteria andSfzzIsNull() {
            addCriterion("SFZZ is null");
            return (Criteria) this;
        }

        public Criteria andSfzzIsNotNull() {
            addCriterion("SFZZ is not null");
            return (Criteria) this;
        }

        public Criteria andSfzzEqualTo(String value) {
            addCriterion("SFZZ =", value, "sfzz");
            return (Criteria) this;
        }

        public Criteria andSfzzNotEqualTo(String value) {
            addCriterion("SFZZ <>", value, "sfzz");
            return (Criteria) this;
        }

        public Criteria andSfzzGreaterThan(String value) {
            addCriterion("SFZZ >", value, "sfzz");
            return (Criteria) this;
        }

        public Criteria andSfzzGreaterThanOrEqualTo(String value) {
            addCriterion("SFZZ >=", value, "sfzz");
            return (Criteria) this;
        }

        public Criteria andSfzzLessThan(String value) {
            addCriterion("SFZZ <", value, "sfzz");
            return (Criteria) this;
        }

        public Criteria andSfzzLessThanOrEqualTo(String value) {
            addCriterion("SFZZ <=", value, "sfzz");
            return (Criteria) this;
        }

        public Criteria andSfzzLike(String value) {
            addCriterion("SFZZ like", value, "sfzz");
            return (Criteria) this;
        }

        public Criteria andSfzzNotLike(String value) {
            addCriterion("SFZZ not like", value, "sfzz");
            return (Criteria) this;
        }

        public Criteria andSfzzIn(List<String> values) {
            addCriterion("SFZZ in", values, "sfzz");
            return (Criteria) this;
        }

        public Criteria andSfzzNotIn(List<String> values) {
            addCriterion("SFZZ not in", values, "sfzz");
            return (Criteria) this;
        }

        public Criteria andSfzzBetween(String value1, String value2) {
            addCriterion("SFZZ between", value1, value2, "sfzz");
            return (Criteria) this;
        }

        public Criteria andSfzzNotBetween(String value1, String value2) {
            addCriterion("SFZZ not between", value1, value2, "sfzz");
            return (Criteria) this;
        }

        public Criteria andRxrqIsNull() {
            addCriterion("RXRQ is null");
            return (Criteria) this;
        }

        public Criteria andRxrqIsNotNull() {
            addCriterion("RXRQ is not null");
            return (Criteria) this;
        }

        public Criteria andRxrqEqualTo(String value) {
            addCriterion("RXRQ =", value, "rxrq");
            return (Criteria) this;
        }

        public Criteria andRxrqNotEqualTo(String value) {
            addCriterion("RXRQ <>", value, "rxrq");
            return (Criteria) this;
        }

        public Criteria andRxrqGreaterThan(String value) {
            addCriterion("RXRQ >", value, "rxrq");
            return (Criteria) this;
        }

        public Criteria andRxrqGreaterThanOrEqualTo(String value) {
            addCriterion("RXRQ >=", value, "rxrq");
            return (Criteria) this;
        }

        public Criteria andRxrqLessThan(String value) {
            addCriterion("RXRQ <", value, "rxrq");
            return (Criteria) this;
        }

        public Criteria andRxrqLessThanOrEqualTo(String value) {
            addCriterion("RXRQ <=", value, "rxrq");
            return (Criteria) this;
        }

        public Criteria andRxrqLike(String value) {
            addCriterion("RXRQ like", value, "rxrq");
            return (Criteria) this;
        }

        public Criteria andRxrqNotLike(String value) {
            addCriterion("RXRQ not like", value, "rxrq");
            return (Criteria) this;
        }

        public Criteria andRxrqIn(List<String> values) {
            addCriterion("RXRQ in", values, "rxrq");
            return (Criteria) this;
        }

        public Criteria andRxrqNotIn(List<String> values) {
            addCriterion("RXRQ not in", values, "rxrq");
            return (Criteria) this;
        }

        public Criteria andRxrqBetween(String value1, String value2) {
            addCriterion("RXRQ between", value1, value2, "rxrq");
            return (Criteria) this;
        }

        public Criteria andRxrqNotBetween(String value1, String value2) {
            addCriterion("RXRQ not between", value1, value2, "rxrq");
            return (Criteria) this;
        }

        public Criteria andXjztIdIsNull() {
            addCriterion("XJZT_ID is null");
            return (Criteria) this;
        }

        public Criteria andXjztIdIsNotNull() {
            addCriterion("XJZT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andXjztIdEqualTo(String value) {
            addCriterion("XJZT_ID =", value, "xjztId");
            return (Criteria) this;
        }

        public Criteria andXjztIdNotEqualTo(String value) {
            addCriterion("XJZT_ID <>", value, "xjztId");
            return (Criteria) this;
        }

        public Criteria andXjztIdGreaterThan(String value) {
            addCriterion("XJZT_ID >", value, "xjztId");
            return (Criteria) this;
        }

        public Criteria andXjztIdGreaterThanOrEqualTo(String value) {
            addCriterion("XJZT_ID >=", value, "xjztId");
            return (Criteria) this;
        }

        public Criteria andXjztIdLessThan(String value) {
            addCriterion("XJZT_ID <", value, "xjztId");
            return (Criteria) this;
        }

        public Criteria andXjztIdLessThanOrEqualTo(String value) {
            addCriterion("XJZT_ID <=", value, "xjztId");
            return (Criteria) this;
        }

        public Criteria andXjztIdLike(String value) {
            addCriterion("XJZT_ID like", value, "xjztId");
            return (Criteria) this;
        }

        public Criteria andXjztIdNotLike(String value) {
            addCriterion("XJZT_ID not like", value, "xjztId");
            return (Criteria) this;
        }

        public Criteria andXjztIdIn(List<String> values) {
            addCriterion("XJZT_ID in", values, "xjztId");
            return (Criteria) this;
        }

        public Criteria andXjztIdNotIn(List<String> values) {
            addCriterion("XJZT_ID not in", values, "xjztId");
            return (Criteria) this;
        }

        public Criteria andXjztIdBetween(String value1, String value2) {
            addCriterion("XJZT_ID between", value1, value2, "xjztId");
            return (Criteria) this;
        }

        public Criteria andXjztIdNotBetween(String value1, String value2) {
            addCriterion("XJZT_ID not between", value1, value2, "xjztId");
            return (Criteria) this;
        }

        public Criteria andXjztIsNull() {
            addCriterion("XJZT is null");
            return (Criteria) this;
        }

        public Criteria andXjztIsNotNull() {
            addCriterion("XJZT is not null");
            return (Criteria) this;
        }

        public Criteria andXjztEqualTo(String value) {
            addCriterion("XJZT =", value, "xjzt");
            return (Criteria) this;
        }

        public Criteria andXjztNotEqualTo(String value) {
            addCriterion("XJZT <>", value, "xjzt");
            return (Criteria) this;
        }

        public Criteria andXjztGreaterThan(String value) {
            addCriterion("XJZT >", value, "xjzt");
            return (Criteria) this;
        }

        public Criteria andXjztGreaterThanOrEqualTo(String value) {
            addCriterion("XJZT >=", value, "xjzt");
            return (Criteria) this;
        }

        public Criteria andXjztLessThan(String value) {
            addCriterion("XJZT <", value, "xjzt");
            return (Criteria) this;
        }

        public Criteria andXjztLessThanOrEqualTo(String value) {
            addCriterion("XJZT <=", value, "xjzt");
            return (Criteria) this;
        }

        public Criteria andXjztLike(String value) {
            addCriterion("XJZT like", value, "xjzt");
            return (Criteria) this;
        }

        public Criteria andXjztNotLike(String value) {
            addCriterion("XJZT not like", value, "xjzt");
            return (Criteria) this;
        }

        public Criteria andXjztIn(List<String> values) {
            addCriterion("XJZT in", values, "xjzt");
            return (Criteria) this;
        }

        public Criteria andXjztNotIn(List<String> values) {
            addCriterion("XJZT not in", values, "xjzt");
            return (Criteria) this;
        }

        public Criteria andXjztBetween(String value1, String value2) {
            addCriterion("XJZT between", value1, value2, "xjzt");
            return (Criteria) this;
        }

        public Criteria andXjztNotBetween(String value1, String value2) {
            addCriterion("XJZT not between", value1, value2, "xjzt");
            return (Criteria) this;
        }

        public Criteria andFmxm1IsNull() {
            addCriterion("FMXM1 is null");
            return (Criteria) this;
        }

        public Criteria andFmxm1IsNotNull() {
            addCriterion("FMXM1 is not null");
            return (Criteria) this;
        }

        public Criteria andFmxm1EqualTo(String value) {
            addCriterion("FMXM1 =", value, "fmxm1");
            return (Criteria) this;
        }

        public Criteria andFmxm1NotEqualTo(String value) {
            addCriterion("FMXM1 <>", value, "fmxm1");
            return (Criteria) this;
        }

        public Criteria andFmxm1GreaterThan(String value) {
            addCriterion("FMXM1 >", value, "fmxm1");
            return (Criteria) this;
        }

        public Criteria andFmxm1GreaterThanOrEqualTo(String value) {
            addCriterion("FMXM1 >=", value, "fmxm1");
            return (Criteria) this;
        }

        public Criteria andFmxm1LessThan(String value) {
            addCriterion("FMXM1 <", value, "fmxm1");
            return (Criteria) this;
        }

        public Criteria andFmxm1LessThanOrEqualTo(String value) {
            addCriterion("FMXM1 <=", value, "fmxm1");
            return (Criteria) this;
        }

        public Criteria andFmxm1Like(String value) {
            addCriterion("FMXM1 like", value, "fmxm1");
            return (Criteria) this;
        }

        public Criteria andFmxm1NotLike(String value) {
            addCriterion("FMXM1 not like", value, "fmxm1");
            return (Criteria) this;
        }

        public Criteria andFmxm1In(List<String> values) {
            addCriterion("FMXM1 in", values, "fmxm1");
            return (Criteria) this;
        }

        public Criteria andFmxm1NotIn(List<String> values) {
            addCriterion("FMXM1 not in", values, "fmxm1");
            return (Criteria) this;
        }

        public Criteria andFmxm1Between(String value1, String value2) {
            addCriterion("FMXM1 between", value1, value2, "fmxm1");
            return (Criteria) this;
        }

        public Criteria andFmxm1NotBetween(String value1, String value2) {
            addCriterion("FMXM1 not between", value1, value2, "fmxm1");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1IdIsNull() {
            addCriterion("FMZJLX1_ID is null");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1IdIsNotNull() {
            addCriterion("FMZJLX1_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1IdEqualTo(String value) {
            addCriterion("FMZJLX1_ID =", value, "fmzjlx1Id");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1IdNotEqualTo(String value) {
            addCriterion("FMZJLX1_ID <>", value, "fmzjlx1Id");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1IdGreaterThan(String value) {
            addCriterion("FMZJLX1_ID >", value, "fmzjlx1Id");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1IdGreaterThanOrEqualTo(String value) {
            addCriterion("FMZJLX1_ID >=", value, "fmzjlx1Id");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1IdLessThan(String value) {
            addCriterion("FMZJLX1_ID <", value, "fmzjlx1Id");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1IdLessThanOrEqualTo(String value) {
            addCriterion("FMZJLX1_ID <=", value, "fmzjlx1Id");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1IdLike(String value) {
            addCriterion("FMZJLX1_ID like", value, "fmzjlx1Id");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1IdNotLike(String value) {
            addCriterion("FMZJLX1_ID not like", value, "fmzjlx1Id");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1IdIn(List<String> values) {
            addCriterion("FMZJLX1_ID in", values, "fmzjlx1Id");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1IdNotIn(List<String> values) {
            addCriterion("FMZJLX1_ID not in", values, "fmzjlx1Id");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1IdBetween(String value1, String value2) {
            addCriterion("FMZJLX1_ID between", value1, value2, "fmzjlx1Id");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1IdNotBetween(String value1, String value2) {
            addCriterion("FMZJLX1_ID not between", value1, value2, "fmzjlx1Id");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1IsNull() {
            addCriterion("FMZJLX1 is null");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1IsNotNull() {
            addCriterion("FMZJLX1 is not null");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1EqualTo(String value) {
            addCriterion("FMZJLX1 =", value, "fmzjlx1");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1NotEqualTo(String value) {
            addCriterion("FMZJLX1 <>", value, "fmzjlx1");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1GreaterThan(String value) {
            addCriterion("FMZJLX1 >", value, "fmzjlx1");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1GreaterThanOrEqualTo(String value) {
            addCriterion("FMZJLX1 >=", value, "fmzjlx1");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1LessThan(String value) {
            addCriterion("FMZJLX1 <", value, "fmzjlx1");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1LessThanOrEqualTo(String value) {
            addCriterion("FMZJLX1 <=", value, "fmzjlx1");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1Like(String value) {
            addCriterion("FMZJLX1 like", value, "fmzjlx1");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1NotLike(String value) {
            addCriterion("FMZJLX1 not like", value, "fmzjlx1");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1In(List<String> values) {
            addCriterion("FMZJLX1 in", values, "fmzjlx1");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1NotIn(List<String> values) {
            addCriterion("FMZJLX1 not in", values, "fmzjlx1");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1Between(String value1, String value2) {
            addCriterion("FMZJLX1 between", value1, value2, "fmzjlx1");
            return (Criteria) this;
        }

        public Criteria andFmzjlx1NotBetween(String value1, String value2) {
            addCriterion("FMZJLX1 not between", value1, value2, "fmzjlx1");
            return (Criteria) this;
        }

        public Criteria andFmzjhm1IsNull() {
            addCriterion("FMZJHM1 is null");
            return (Criteria) this;
        }

        public Criteria andFmzjhm1IsNotNull() {
            addCriterion("FMZJHM1 is not null");
            return (Criteria) this;
        }

        public Criteria andFmzjhm1EqualTo(String value) {
            addCriterion("FMZJHM1 =", value, "fmzjhm1");
            return (Criteria) this;
        }

        public Criteria andFmzjhm1NotEqualTo(String value) {
            addCriterion("FMZJHM1 <>", value, "fmzjhm1");
            return (Criteria) this;
        }

        public Criteria andFmzjhm1GreaterThan(String value) {
            addCriterion("FMZJHM1 >", value, "fmzjhm1");
            return (Criteria) this;
        }

        public Criteria andFmzjhm1GreaterThanOrEqualTo(String value) {
            addCriterion("FMZJHM1 >=", value, "fmzjhm1");
            return (Criteria) this;
        }

        public Criteria andFmzjhm1LessThan(String value) {
            addCriterion("FMZJHM1 <", value, "fmzjhm1");
            return (Criteria) this;
        }

        public Criteria andFmzjhm1LessThanOrEqualTo(String value) {
            addCriterion("FMZJHM1 <=", value, "fmzjhm1");
            return (Criteria) this;
        }

        public Criteria andFmzjhm1Like(String value) {
            addCriterion("FMZJHM1 like", value, "fmzjhm1");
            return (Criteria) this;
        }

        public Criteria andFmzjhm1NotLike(String value) {
            addCriterion("FMZJHM1 not like", value, "fmzjhm1");
            return (Criteria) this;
        }

        public Criteria andFmzjhm1In(List<String> values) {
            addCriterion("FMZJHM1 in", values, "fmzjhm1");
            return (Criteria) this;
        }

        public Criteria andFmzjhm1NotIn(List<String> values) {
            addCriterion("FMZJHM1 not in", values, "fmzjhm1");
            return (Criteria) this;
        }

        public Criteria andFmzjhm1Between(String value1, String value2) {
            addCriterion("FMZJHM1 between", value1, value2, "fmzjhm1");
            return (Criteria) this;
        }

        public Criteria andFmzjhm1NotBetween(String value1, String value2) {
            addCriterion("FMZJHM1 not between", value1, value2, "fmzjhm1");
            return (Criteria) this;
        }

        public Criteria andFmxm2IsNull() {
            addCriterion("FMXM2 is null");
            return (Criteria) this;
        }

        public Criteria andFmxm2IsNotNull() {
            addCriterion("FMXM2 is not null");
            return (Criteria) this;
        }

        public Criteria andFmxm2EqualTo(String value) {
            addCriterion("FMXM2 =", value, "fmxm2");
            return (Criteria) this;
        }

        public Criteria andFmxm2NotEqualTo(String value) {
            addCriterion("FMXM2 <>", value, "fmxm2");
            return (Criteria) this;
        }

        public Criteria andFmxm2GreaterThan(String value) {
            addCriterion("FMXM2 >", value, "fmxm2");
            return (Criteria) this;
        }

        public Criteria andFmxm2GreaterThanOrEqualTo(String value) {
            addCriterion("FMXM2 >=", value, "fmxm2");
            return (Criteria) this;
        }

        public Criteria andFmxm2LessThan(String value) {
            addCriterion("FMXM2 <", value, "fmxm2");
            return (Criteria) this;
        }

        public Criteria andFmxm2LessThanOrEqualTo(String value) {
            addCriterion("FMXM2 <=", value, "fmxm2");
            return (Criteria) this;
        }

        public Criteria andFmxm2Like(String value) {
            addCriterion("FMXM2 like", value, "fmxm2");
            return (Criteria) this;
        }

        public Criteria andFmxm2NotLike(String value) {
            addCriterion("FMXM2 not like", value, "fmxm2");
            return (Criteria) this;
        }

        public Criteria andFmxm2In(List<String> values) {
            addCriterion("FMXM2 in", values, "fmxm2");
            return (Criteria) this;
        }

        public Criteria andFmxm2NotIn(List<String> values) {
            addCriterion("FMXM2 not in", values, "fmxm2");
            return (Criteria) this;
        }

        public Criteria andFmxm2Between(String value1, String value2) {
            addCriterion("FMXM2 between", value1, value2, "fmxm2");
            return (Criteria) this;
        }

        public Criteria andFmxm2NotBetween(String value1, String value2) {
            addCriterion("FMXM2 not between", value1, value2, "fmxm2");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2IdIsNull() {
            addCriterion("FMZJLX2_ID is null");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2IdIsNotNull() {
            addCriterion("FMZJLX2_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2IdEqualTo(String value) {
            addCriterion("FMZJLX2_ID =", value, "fmzjlx2Id");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2IdNotEqualTo(String value) {
            addCriterion("FMZJLX2_ID <>", value, "fmzjlx2Id");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2IdGreaterThan(String value) {
            addCriterion("FMZJLX2_ID >", value, "fmzjlx2Id");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2IdGreaterThanOrEqualTo(String value) {
            addCriterion("FMZJLX2_ID >=", value, "fmzjlx2Id");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2IdLessThan(String value) {
            addCriterion("FMZJLX2_ID <", value, "fmzjlx2Id");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2IdLessThanOrEqualTo(String value) {
            addCriterion("FMZJLX2_ID <=", value, "fmzjlx2Id");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2IdLike(String value) {
            addCriterion("FMZJLX2_ID like", value, "fmzjlx2Id");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2IdNotLike(String value) {
            addCriterion("FMZJLX2_ID not like", value, "fmzjlx2Id");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2IdIn(List<String> values) {
            addCriterion("FMZJLX2_ID in", values, "fmzjlx2Id");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2IdNotIn(List<String> values) {
            addCriterion("FMZJLX2_ID not in", values, "fmzjlx2Id");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2IdBetween(String value1, String value2) {
            addCriterion("FMZJLX2_ID between", value1, value2, "fmzjlx2Id");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2IdNotBetween(String value1, String value2) {
            addCriterion("FMZJLX2_ID not between", value1, value2, "fmzjlx2Id");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2IsNull() {
            addCriterion("FMZJLX2 is null");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2IsNotNull() {
            addCriterion("FMZJLX2 is not null");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2EqualTo(String value) {
            addCriterion("FMZJLX2 =", value, "fmzjlx2");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2NotEqualTo(String value) {
            addCriterion("FMZJLX2 <>", value, "fmzjlx2");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2GreaterThan(String value) {
            addCriterion("FMZJLX2 >", value, "fmzjlx2");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2GreaterThanOrEqualTo(String value) {
            addCriterion("FMZJLX2 >=", value, "fmzjlx2");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2LessThan(String value) {
            addCriterion("FMZJLX2 <", value, "fmzjlx2");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2LessThanOrEqualTo(String value) {
            addCriterion("FMZJLX2 <=", value, "fmzjlx2");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2Like(String value) {
            addCriterion("FMZJLX2 like", value, "fmzjlx2");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2NotLike(String value) {
            addCriterion("FMZJLX2 not like", value, "fmzjlx2");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2In(List<String> values) {
            addCriterion("FMZJLX2 in", values, "fmzjlx2");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2NotIn(List<String> values) {
            addCriterion("FMZJLX2 not in", values, "fmzjlx2");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2Between(String value1, String value2) {
            addCriterion("FMZJLX2 between", value1, value2, "fmzjlx2");
            return (Criteria) this;
        }

        public Criteria andFmzjlx2NotBetween(String value1, String value2) {
            addCriterion("FMZJLX2 not between", value1, value2, "fmzjlx2");
            return (Criteria) this;
        }

        public Criteria andFmzjhm2IsNull() {
            addCriterion("FMZJHM2 is null");
            return (Criteria) this;
        }

        public Criteria andFmzjhm2IsNotNull() {
            addCriterion("FMZJHM2 is not null");
            return (Criteria) this;
        }

        public Criteria andFmzjhm2EqualTo(String value) {
            addCriterion("FMZJHM2 =", value, "fmzjhm2");
            return (Criteria) this;
        }

        public Criteria andFmzjhm2NotEqualTo(String value) {
            addCriterion("FMZJHM2 <>", value, "fmzjhm2");
            return (Criteria) this;
        }

        public Criteria andFmzjhm2GreaterThan(String value) {
            addCriterion("FMZJHM2 >", value, "fmzjhm2");
            return (Criteria) this;
        }

        public Criteria andFmzjhm2GreaterThanOrEqualTo(String value) {
            addCriterion("FMZJHM2 >=", value, "fmzjhm2");
            return (Criteria) this;
        }

        public Criteria andFmzjhm2LessThan(String value) {
            addCriterion("FMZJHM2 <", value, "fmzjhm2");
            return (Criteria) this;
        }

        public Criteria andFmzjhm2LessThanOrEqualTo(String value) {
            addCriterion("FMZJHM2 <=", value, "fmzjhm2");
            return (Criteria) this;
        }

        public Criteria andFmzjhm2Like(String value) {
            addCriterion("FMZJHM2 like", value, "fmzjhm2");
            return (Criteria) this;
        }

        public Criteria andFmzjhm2NotLike(String value) {
            addCriterion("FMZJHM2 not like", value, "fmzjhm2");
            return (Criteria) this;
        }

        public Criteria andFmzjhm2In(List<String> values) {
            addCriterion("FMZJHM2 in", values, "fmzjhm2");
            return (Criteria) this;
        }

        public Criteria andFmzjhm2NotIn(List<String> values) {
            addCriterion("FMZJHM2 not in", values, "fmzjhm2");
            return (Criteria) this;
        }

        public Criteria andFmzjhm2Between(String value1, String value2) {
            addCriterion("FMZJHM2 between", value1, value2, "fmzjhm2");
            return (Criteria) this;
        }

        public Criteria andFmzjhm2NotBetween(String value1, String value2) {
            addCriterion("FMZJHM2 not between", value1, value2, "fmzjhm2");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagIsNull() {
            addCriterion("SUBMIT_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagIsNotNull() {
            addCriterion("SUBMIT_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagEqualTo(String value) {
            addCriterion("SUBMIT_FLAG =", value, "submitFlag");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagNotEqualTo(String value) {
            addCriterion("SUBMIT_FLAG <>", value, "submitFlag");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagGreaterThan(String value) {
            addCriterion("SUBMIT_FLAG >", value, "submitFlag");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagGreaterThanOrEqualTo(String value) {
            addCriterion("SUBMIT_FLAG >=", value, "submitFlag");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagLessThan(String value) {
            addCriterion("SUBMIT_FLAG <", value, "submitFlag");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagLessThanOrEqualTo(String value) {
            addCriterion("SUBMIT_FLAG <=", value, "submitFlag");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagLike(String value) {
            addCriterion("SUBMIT_FLAG like", value, "submitFlag");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagNotLike(String value) {
            addCriterion("SUBMIT_FLAG not like", value, "submitFlag");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagIn(List<String> values) {
            addCriterion("SUBMIT_FLAG in", values, "submitFlag");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagNotIn(List<String> values) {
            addCriterion("SUBMIT_FLAG not in", values, "submitFlag");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagBetween(String value1, String value2) {
            addCriterion("SUBMIT_FLAG between", value1, value2, "submitFlag");
            return (Criteria) this;
        }

        public Criteria andSubmitFlagNotBetween(String value1, String value2) {
            addCriterion("SUBMIT_FLAG not between", value1, value2, "submitFlag");
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