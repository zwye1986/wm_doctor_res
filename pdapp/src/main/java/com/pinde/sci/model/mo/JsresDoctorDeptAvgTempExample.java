package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class JsresDoctorDeptAvgTempExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JsresDoctorDeptAvgTempExample() {
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

        public Criteria andRecruitFlowIsNull() {
            addCriterion("RECRUIT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowIsNotNull() {
            addCriterion("RECRUIT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowEqualTo(String value) {
            addCriterion("RECRUIT_FLOW =", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotEqualTo(String value) {
            addCriterion("RECRUIT_FLOW <>", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowGreaterThan(String value) {
            addCriterion("RECRUIT_FLOW >", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RECRUIT_FLOW >=", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowLessThan(String value) {
            addCriterion("RECRUIT_FLOW <", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowLessThanOrEqualTo(String value) {
            addCriterion("RECRUIT_FLOW <=", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowLike(String value) {
            addCriterion("RECRUIT_FLOW like", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotLike(String value) {
            addCriterion("RECRUIT_FLOW not like", value, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowIn(List<String> values) {
            addCriterion("RECRUIT_FLOW in", values, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotIn(List<String> values) {
            addCriterion("RECRUIT_FLOW not in", values, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowBetween(String value1, String value2) {
            addCriterion("RECRUIT_FLOW between", value1, value2, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andRecruitFlowNotBetween(String value1, String value2) {
            addCriterion("RECRUIT_FLOW not between", value1, value2, "recruitFlow");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteIsNull() {
            addCriterion("AVG_COMPLETE is null");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteIsNotNull() {
            addCriterion("AVG_COMPLETE is not null");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteEqualTo(String value) {
            addCriterion("AVG_COMPLETE =", value, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteNotEqualTo(String value) {
            addCriterion("AVG_COMPLETE <>", value, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteGreaterThan(String value) {
            addCriterion("AVG_COMPLETE >", value, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteGreaterThanOrEqualTo(String value) {
            addCriterion("AVG_COMPLETE >=", value, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteLessThan(String value) {
            addCriterion("AVG_COMPLETE <", value, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteLessThanOrEqualTo(String value) {
            addCriterion("AVG_COMPLETE <=", value, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteLike(String value) {
            addCriterion("AVG_COMPLETE like", value, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteNotLike(String value) {
            addCriterion("AVG_COMPLETE not like", value, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteIn(List<String> values) {
            addCriterion("AVG_COMPLETE in", values, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteNotIn(List<String> values) {
            addCriterion("AVG_COMPLETE not in", values, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBetween(String value1, String value2) {
            addCriterion("AVG_COMPLETE between", value1, value2, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteNotBetween(String value1, String value2) {
            addCriterion("AVG_COMPLETE not between", value1, value2, "avgComplete");
            return (Criteria) this;
        }

        public Criteria andAvgAuditIsNull() {
            addCriterion("AVG_AUDIT is null");
            return (Criteria) this;
        }

        public Criteria andAvgAuditIsNotNull() {
            addCriterion("AVG_AUDIT is not null");
            return (Criteria) this;
        }

        public Criteria andAvgAuditEqualTo(String value) {
            addCriterion("AVG_AUDIT =", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditNotEqualTo(String value) {
            addCriterion("AVG_AUDIT <>", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditGreaterThan(String value) {
            addCriterion("AVG_AUDIT >", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditGreaterThanOrEqualTo(String value) {
            addCriterion("AVG_AUDIT >=", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditLessThan(String value) {
            addCriterion("AVG_AUDIT <", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditLessThanOrEqualTo(String value) {
            addCriterion("AVG_AUDIT <=", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditLike(String value) {
            addCriterion("AVG_AUDIT like", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditNotLike(String value) {
            addCriterion("AVG_AUDIT not like", value, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditIn(List<String> values) {
            addCriterion("AVG_AUDIT in", values, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditNotIn(List<String> values) {
            addCriterion("AVG_AUDIT not in", values, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditBetween(String value1, String value2) {
            addCriterion("AVG_AUDIT between", value1, value2, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgAuditNotBetween(String value1, String value2) {
            addCriterion("AVG_AUDIT not between", value1, value2, "avgAudit");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteIsNull() {
            addCriterion("AVG_IN_COMPLETE is null");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteIsNotNull() {
            addCriterion("AVG_IN_COMPLETE is not null");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteEqualTo(String value) {
            addCriterion("AVG_IN_COMPLETE =", value, "avgInComplete");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteNotEqualTo(String value) {
            addCriterion("AVG_IN_COMPLETE <>", value, "avgInComplete");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteGreaterThan(String value) {
            addCriterion("AVG_IN_COMPLETE >", value, "avgInComplete");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteGreaterThanOrEqualTo(String value) {
            addCriterion("AVG_IN_COMPLETE >=", value, "avgInComplete");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteLessThan(String value) {
            addCriterion("AVG_IN_COMPLETE <", value, "avgInComplete");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteLessThanOrEqualTo(String value) {
            addCriterion("AVG_IN_COMPLETE <=", value, "avgInComplete");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteLike(String value) {
            addCriterion("AVG_IN_COMPLETE like", value, "avgInComplete");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteNotLike(String value) {
            addCriterion("AVG_IN_COMPLETE not like", value, "avgInComplete");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteIn(List<String> values) {
            addCriterion("AVG_IN_COMPLETE in", values, "avgInComplete");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteNotIn(List<String> values) {
            addCriterion("AVG_IN_COMPLETE not in", values, "avgInComplete");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBetween(String value1, String value2) {
            addCriterion("AVG_IN_COMPLETE between", value1, value2, "avgInComplete");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteNotBetween(String value1, String value2) {
            addCriterion("AVG_IN_COMPLETE not between", value1, value2, "avgInComplete");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteIsNull() {
            addCriterion("AVG_OUT_COMPLETE is null");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteIsNotNull() {
            addCriterion("AVG_OUT_COMPLETE is not null");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteEqualTo(String value) {
            addCriterion("AVG_OUT_COMPLETE =", value, "avgOutComplete");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteNotEqualTo(String value) {
            addCriterion("AVG_OUT_COMPLETE <>", value, "avgOutComplete");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteGreaterThan(String value) {
            addCriterion("AVG_OUT_COMPLETE >", value, "avgOutComplete");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteGreaterThanOrEqualTo(String value) {
            addCriterion("AVG_OUT_COMPLETE >=", value, "avgOutComplete");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteLessThan(String value) {
            addCriterion("AVG_OUT_COMPLETE <", value, "avgOutComplete");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteLessThanOrEqualTo(String value) {
            addCriterion("AVG_OUT_COMPLETE <=", value, "avgOutComplete");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteLike(String value) {
            addCriterion("AVG_OUT_COMPLETE like", value, "avgOutComplete");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteNotLike(String value) {
            addCriterion("AVG_OUT_COMPLETE not like", value, "avgOutComplete");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteIn(List<String> values) {
            addCriterion("AVG_OUT_COMPLETE in", values, "avgOutComplete");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteNotIn(List<String> values) {
            addCriterion("AVG_OUT_COMPLETE not in", values, "avgOutComplete");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBetween(String value1, String value2) {
            addCriterion("AVG_OUT_COMPLETE between", value1, value2, "avgOutComplete");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteNotBetween(String value1, String value2) {
            addCriterion("AVG_OUT_COMPLETE not between", value1, value2, "avgOutComplete");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerIsNull() {
            addCriterion("AVG_COMPLETE_BI_PER is null");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerIsNotNull() {
            addCriterion("AVG_COMPLETE_BI_PER is not null");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerEqualTo(String value) {
            addCriterion("AVG_COMPLETE_BI_PER =", value, "avgCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerNotEqualTo(String value) {
            addCriterion("AVG_COMPLETE_BI_PER <>", value, "avgCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerGreaterThan(String value) {
            addCriterion("AVG_COMPLETE_BI_PER >", value, "avgCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerGreaterThanOrEqualTo(String value) {
            addCriterion("AVG_COMPLETE_BI_PER >=", value, "avgCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerLessThan(String value) {
            addCriterion("AVG_COMPLETE_BI_PER <", value, "avgCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerLessThanOrEqualTo(String value) {
            addCriterion("AVG_COMPLETE_BI_PER <=", value, "avgCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerLike(String value) {
            addCriterion("AVG_COMPLETE_BI_PER like", value, "avgCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerNotLike(String value) {
            addCriterion("AVG_COMPLETE_BI_PER not like", value, "avgCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerIn(List<String> values) {
            addCriterion("AVG_COMPLETE_BI_PER in", values, "avgCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerNotIn(List<String> values) {
            addCriterion("AVG_COMPLETE_BI_PER not in", values, "avgCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerBetween(String value1, String value2) {
            addCriterion("AVG_COMPLETE_BI_PER between", value1, value2, "avgCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgCompleteBiPerNotBetween(String value1, String value2) {
            addCriterion("AVG_COMPLETE_BI_PER not between", value1, value2, "avgCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerIsNull() {
            addCriterion("AVG_OUT_COMPLETE_BI_PER is null");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerIsNotNull() {
            addCriterion("AVG_OUT_COMPLETE_BI_PER is not null");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerEqualTo(String value) {
            addCriterion("AVG_OUT_COMPLETE_BI_PER =", value, "avgOutCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerNotEqualTo(String value) {
            addCriterion("AVG_OUT_COMPLETE_BI_PER <>", value, "avgOutCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerGreaterThan(String value) {
            addCriterion("AVG_OUT_COMPLETE_BI_PER >", value, "avgOutCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerGreaterThanOrEqualTo(String value) {
            addCriterion("AVG_OUT_COMPLETE_BI_PER >=", value, "avgOutCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerLessThan(String value) {
            addCriterion("AVG_OUT_COMPLETE_BI_PER <", value, "avgOutCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerLessThanOrEqualTo(String value) {
            addCriterion("AVG_OUT_COMPLETE_BI_PER <=", value, "avgOutCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerLike(String value) {
            addCriterion("AVG_OUT_COMPLETE_BI_PER like", value, "avgOutCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerNotLike(String value) {
            addCriterion("AVG_OUT_COMPLETE_BI_PER not like", value, "avgOutCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerIn(List<String> values) {
            addCriterion("AVG_OUT_COMPLETE_BI_PER in", values, "avgOutCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerNotIn(List<String> values) {
            addCriterion("AVG_OUT_COMPLETE_BI_PER not in", values, "avgOutCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerBetween(String value1, String value2) {
            addCriterion("AVG_OUT_COMPLETE_BI_PER between", value1, value2, "avgOutCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgOutCompleteBiPerNotBetween(String value1, String value2) {
            addCriterion("AVG_OUT_COMPLETE_BI_PER not between", value1, value2, "avgOutCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerIsNull() {
            addCriterion("AVG_IN_COMPLETE_BI_PER is null");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerIsNotNull() {
            addCriterion("AVG_IN_COMPLETE_BI_PER is not null");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerEqualTo(String value) {
            addCriterion("AVG_IN_COMPLETE_BI_PER =", value, "avgInCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerNotEqualTo(String value) {
            addCriterion("AVG_IN_COMPLETE_BI_PER <>", value, "avgInCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerGreaterThan(String value) {
            addCriterion("AVG_IN_COMPLETE_BI_PER >", value, "avgInCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerGreaterThanOrEqualTo(String value) {
            addCriterion("AVG_IN_COMPLETE_BI_PER >=", value, "avgInCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerLessThan(String value) {
            addCriterion("AVG_IN_COMPLETE_BI_PER <", value, "avgInCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerLessThanOrEqualTo(String value) {
            addCriterion("AVG_IN_COMPLETE_BI_PER <=", value, "avgInCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerLike(String value) {
            addCriterion("AVG_IN_COMPLETE_BI_PER like", value, "avgInCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerNotLike(String value) {
            addCriterion("AVG_IN_COMPLETE_BI_PER not like", value, "avgInCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerIn(List<String> values) {
            addCriterion("AVG_IN_COMPLETE_BI_PER in", values, "avgInCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerNotIn(List<String> values) {
            addCriterion("AVG_IN_COMPLETE_BI_PER not in", values, "avgInCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerBetween(String value1, String value2) {
            addCriterion("AVG_IN_COMPLETE_BI_PER between", value1, value2, "avgInCompleteBiPer");
            return (Criteria) this;
        }

        public Criteria andAvgInCompleteBiPerNotBetween(String value1, String value2) {
            addCriterion("AVG_IN_COMPLETE_BI_PER not between", value1, value2, "avgInCompleteBiPer");
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