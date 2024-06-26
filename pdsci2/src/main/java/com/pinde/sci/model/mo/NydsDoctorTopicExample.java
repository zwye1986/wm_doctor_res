package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class NydsDoctorTopicExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NydsDoctorTopicExample() {
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

        public Criteria andTopicTitleIsNull() {
            addCriterion("TOPIC_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andTopicTitleIsNotNull() {
            addCriterion("TOPIC_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andTopicTitleEqualTo(String value) {
            addCriterion("TOPIC_TITLE =", value, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleNotEqualTo(String value) {
            addCriterion("TOPIC_TITLE <>", value, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleGreaterThan(String value) {
            addCriterion("TOPIC_TITLE >", value, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleGreaterThanOrEqualTo(String value) {
            addCriterion("TOPIC_TITLE >=", value, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleLessThan(String value) {
            addCriterion("TOPIC_TITLE <", value, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleLessThanOrEqualTo(String value) {
            addCriterion("TOPIC_TITLE <=", value, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleLike(String value) {
            addCriterion("TOPIC_TITLE like", value, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleNotLike(String value) {
            addCriterion("TOPIC_TITLE not like", value, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleIn(List<String> values) {
            addCriterion("TOPIC_TITLE in", values, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleNotIn(List<String> values) {
            addCriterion("TOPIC_TITLE not in", values, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleBetween(String value1, String value2) {
            addCriterion("TOPIC_TITLE between", value1, value2, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicTitleNotBetween(String value1, String value2) {
            addCriterion("TOPIC_TITLE not between", value1, value2, "topicTitle");
            return (Criteria) this;
        }

        public Criteria andTopicLevelIdIsNull() {
            addCriterion("TOPIC_LEVEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andTopicLevelIdIsNotNull() {
            addCriterion("TOPIC_LEVEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTopicLevelIdEqualTo(String value) {
            addCriterion("TOPIC_LEVEL_ID =", value, "topicLevelId");
            return (Criteria) this;
        }

        public Criteria andTopicLevelIdNotEqualTo(String value) {
            addCriterion("TOPIC_LEVEL_ID <>", value, "topicLevelId");
            return (Criteria) this;
        }

        public Criteria andTopicLevelIdGreaterThan(String value) {
            addCriterion("TOPIC_LEVEL_ID >", value, "topicLevelId");
            return (Criteria) this;
        }

        public Criteria andTopicLevelIdGreaterThanOrEqualTo(String value) {
            addCriterion("TOPIC_LEVEL_ID >=", value, "topicLevelId");
            return (Criteria) this;
        }

        public Criteria andTopicLevelIdLessThan(String value) {
            addCriterion("TOPIC_LEVEL_ID <", value, "topicLevelId");
            return (Criteria) this;
        }

        public Criteria andTopicLevelIdLessThanOrEqualTo(String value) {
            addCriterion("TOPIC_LEVEL_ID <=", value, "topicLevelId");
            return (Criteria) this;
        }

        public Criteria andTopicLevelIdLike(String value) {
            addCriterion("TOPIC_LEVEL_ID like", value, "topicLevelId");
            return (Criteria) this;
        }

        public Criteria andTopicLevelIdNotLike(String value) {
            addCriterion("TOPIC_LEVEL_ID not like", value, "topicLevelId");
            return (Criteria) this;
        }

        public Criteria andTopicLevelIdIn(List<String> values) {
            addCriterion("TOPIC_LEVEL_ID in", values, "topicLevelId");
            return (Criteria) this;
        }

        public Criteria andTopicLevelIdNotIn(List<String> values) {
            addCriterion("TOPIC_LEVEL_ID not in", values, "topicLevelId");
            return (Criteria) this;
        }

        public Criteria andTopicLevelIdBetween(String value1, String value2) {
            addCriterion("TOPIC_LEVEL_ID between", value1, value2, "topicLevelId");
            return (Criteria) this;
        }

        public Criteria andTopicLevelIdNotBetween(String value1, String value2) {
            addCriterion("TOPIC_LEVEL_ID not between", value1, value2, "topicLevelId");
            return (Criteria) this;
        }

        public Criteria andTopicLevelNameIsNull() {
            addCriterion("TOPIC_LEVEL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTopicLevelNameIsNotNull() {
            addCriterion("TOPIC_LEVEL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTopicLevelNameEqualTo(String value) {
            addCriterion("TOPIC_LEVEL_NAME =", value, "topicLevelName");
            return (Criteria) this;
        }

        public Criteria andTopicLevelNameNotEqualTo(String value) {
            addCriterion("TOPIC_LEVEL_NAME <>", value, "topicLevelName");
            return (Criteria) this;
        }

        public Criteria andTopicLevelNameGreaterThan(String value) {
            addCriterion("TOPIC_LEVEL_NAME >", value, "topicLevelName");
            return (Criteria) this;
        }

        public Criteria andTopicLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("TOPIC_LEVEL_NAME >=", value, "topicLevelName");
            return (Criteria) this;
        }

        public Criteria andTopicLevelNameLessThan(String value) {
            addCriterion("TOPIC_LEVEL_NAME <", value, "topicLevelName");
            return (Criteria) this;
        }

        public Criteria andTopicLevelNameLessThanOrEqualTo(String value) {
            addCriterion("TOPIC_LEVEL_NAME <=", value, "topicLevelName");
            return (Criteria) this;
        }

        public Criteria andTopicLevelNameLike(String value) {
            addCriterion("TOPIC_LEVEL_NAME like", value, "topicLevelName");
            return (Criteria) this;
        }

        public Criteria andTopicLevelNameNotLike(String value) {
            addCriterion("TOPIC_LEVEL_NAME not like", value, "topicLevelName");
            return (Criteria) this;
        }

        public Criteria andTopicLevelNameIn(List<String> values) {
            addCriterion("TOPIC_LEVEL_NAME in", values, "topicLevelName");
            return (Criteria) this;
        }

        public Criteria andTopicLevelNameNotIn(List<String> values) {
            addCriterion("TOPIC_LEVEL_NAME not in", values, "topicLevelName");
            return (Criteria) this;
        }

        public Criteria andTopicLevelNameBetween(String value1, String value2) {
            addCriterion("TOPIC_LEVEL_NAME between", value1, value2, "topicLevelName");
            return (Criteria) this;
        }

        public Criteria andTopicLevelNameNotBetween(String value1, String value2) {
            addCriterion("TOPIC_LEVEL_NAME not between", value1, value2, "topicLevelName");
            return (Criteria) this;
        }

        public Criteria andProjectUnitIsNull() {
            addCriterion("PROJECT_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andProjectUnitIsNotNull() {
            addCriterion("PROJECT_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andProjectUnitEqualTo(String value) {
            addCriterion("PROJECT_UNIT =", value, "projectUnit");
            return (Criteria) this;
        }

        public Criteria andProjectUnitNotEqualTo(String value) {
            addCriterion("PROJECT_UNIT <>", value, "projectUnit");
            return (Criteria) this;
        }

        public Criteria andProjectUnitGreaterThan(String value) {
            addCriterion("PROJECT_UNIT >", value, "projectUnit");
            return (Criteria) this;
        }

        public Criteria andProjectUnitGreaterThanOrEqualTo(String value) {
            addCriterion("PROJECT_UNIT >=", value, "projectUnit");
            return (Criteria) this;
        }

        public Criteria andProjectUnitLessThan(String value) {
            addCriterion("PROJECT_UNIT <", value, "projectUnit");
            return (Criteria) this;
        }

        public Criteria andProjectUnitLessThanOrEqualTo(String value) {
            addCriterion("PROJECT_UNIT <=", value, "projectUnit");
            return (Criteria) this;
        }

        public Criteria andProjectUnitLike(String value) {
            addCriterion("PROJECT_UNIT like", value, "projectUnit");
            return (Criteria) this;
        }

        public Criteria andProjectUnitNotLike(String value) {
            addCriterion("PROJECT_UNIT not like", value, "projectUnit");
            return (Criteria) this;
        }

        public Criteria andProjectUnitIn(List<String> values) {
            addCriterion("PROJECT_UNIT in", values, "projectUnit");
            return (Criteria) this;
        }

        public Criteria andProjectUnitNotIn(List<String> values) {
            addCriterion("PROJECT_UNIT not in", values, "projectUnit");
            return (Criteria) this;
        }

        public Criteria andProjectUnitBetween(String value1, String value2) {
            addCriterion("PROJECT_UNIT between", value1, value2, "projectUnit");
            return (Criteria) this;
        }

        public Criteria andProjectUnitNotBetween(String value1, String value2) {
            addCriterion("PROJECT_UNIT not between", value1, value2, "projectUnit");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyIsNull() {
            addCriterion("TOPIC_MONEY is null");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyIsNotNull() {
            addCriterion("TOPIC_MONEY is not null");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyEqualTo(String value) {
            addCriterion("TOPIC_MONEY =", value, "topicMoney");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyNotEqualTo(String value) {
            addCriterion("TOPIC_MONEY <>", value, "topicMoney");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyGreaterThan(String value) {
            addCriterion("TOPIC_MONEY >", value, "topicMoney");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyGreaterThanOrEqualTo(String value) {
            addCriterion("TOPIC_MONEY >=", value, "topicMoney");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyLessThan(String value) {
            addCriterion("TOPIC_MONEY <", value, "topicMoney");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyLessThanOrEqualTo(String value) {
            addCriterion("TOPIC_MONEY <=", value, "topicMoney");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyLike(String value) {
            addCriterion("TOPIC_MONEY like", value, "topicMoney");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyNotLike(String value) {
            addCriterion("TOPIC_MONEY not like", value, "topicMoney");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyIn(List<String> values) {
            addCriterion("TOPIC_MONEY in", values, "topicMoney");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyNotIn(List<String> values) {
            addCriterion("TOPIC_MONEY not in", values, "topicMoney");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyBetween(String value1, String value2) {
            addCriterion("TOPIC_MONEY between", value1, value2, "topicMoney");
            return (Criteria) this;
        }

        public Criteria andTopicMoneyNotBetween(String value1, String value2) {
            addCriterion("TOPIC_MONEY not between", value1, value2, "topicMoney");
            return (Criteria) this;
        }

        public Criteria andKnotTimeIsNull() {
            addCriterion("KNOT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andKnotTimeIsNotNull() {
            addCriterion("KNOT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andKnotTimeEqualTo(String value) {
            addCriterion("KNOT_TIME =", value, "knotTime");
            return (Criteria) this;
        }

        public Criteria andKnotTimeNotEqualTo(String value) {
            addCriterion("KNOT_TIME <>", value, "knotTime");
            return (Criteria) this;
        }

        public Criteria andKnotTimeGreaterThan(String value) {
            addCriterion("KNOT_TIME >", value, "knotTime");
            return (Criteria) this;
        }

        public Criteria andKnotTimeGreaterThanOrEqualTo(String value) {
            addCriterion("KNOT_TIME >=", value, "knotTime");
            return (Criteria) this;
        }

        public Criteria andKnotTimeLessThan(String value) {
            addCriterion("KNOT_TIME <", value, "knotTime");
            return (Criteria) this;
        }

        public Criteria andKnotTimeLessThanOrEqualTo(String value) {
            addCriterion("KNOT_TIME <=", value, "knotTime");
            return (Criteria) this;
        }

        public Criteria andKnotTimeLike(String value) {
            addCriterion("KNOT_TIME like", value, "knotTime");
            return (Criteria) this;
        }

        public Criteria andKnotTimeNotLike(String value) {
            addCriterion("KNOT_TIME not like", value, "knotTime");
            return (Criteria) this;
        }

        public Criteria andKnotTimeIn(List<String> values) {
            addCriterion("KNOT_TIME in", values, "knotTime");
            return (Criteria) this;
        }

        public Criteria andKnotTimeNotIn(List<String> values) {
            addCriterion("KNOT_TIME not in", values, "knotTime");
            return (Criteria) this;
        }

        public Criteria andKnotTimeBetween(String value1, String value2) {
            addCriterion("KNOT_TIME between", value1, value2, "knotTime");
            return (Criteria) this;
        }

        public Criteria andKnotTimeNotBetween(String value1, String value2) {
            addCriterion("KNOT_TIME not between", value1, value2, "knotTime");
            return (Criteria) this;
        }

        public Criteria andContractPicUrlIsNull() {
            addCriterion("CONTRACT_PIC_URL is null");
            return (Criteria) this;
        }

        public Criteria andContractPicUrlIsNotNull() {
            addCriterion("CONTRACT_PIC_URL is not null");
            return (Criteria) this;
        }

        public Criteria andContractPicUrlEqualTo(String value) {
            addCriterion("CONTRACT_PIC_URL =", value, "contractPicUrl");
            return (Criteria) this;
        }

        public Criteria andContractPicUrlNotEqualTo(String value) {
            addCriterion("CONTRACT_PIC_URL <>", value, "contractPicUrl");
            return (Criteria) this;
        }

        public Criteria andContractPicUrlGreaterThan(String value) {
            addCriterion("CONTRACT_PIC_URL >", value, "contractPicUrl");
            return (Criteria) this;
        }

        public Criteria andContractPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("CONTRACT_PIC_URL >=", value, "contractPicUrl");
            return (Criteria) this;
        }

        public Criteria andContractPicUrlLessThan(String value) {
            addCriterion("CONTRACT_PIC_URL <", value, "contractPicUrl");
            return (Criteria) this;
        }

        public Criteria andContractPicUrlLessThanOrEqualTo(String value) {
            addCriterion("CONTRACT_PIC_URL <=", value, "contractPicUrl");
            return (Criteria) this;
        }

        public Criteria andContractPicUrlLike(String value) {
            addCriterion("CONTRACT_PIC_URL like", value, "contractPicUrl");
            return (Criteria) this;
        }

        public Criteria andContractPicUrlNotLike(String value) {
            addCriterion("CONTRACT_PIC_URL not like", value, "contractPicUrl");
            return (Criteria) this;
        }

        public Criteria andContractPicUrlIn(List<String> values) {
            addCriterion("CONTRACT_PIC_URL in", values, "contractPicUrl");
            return (Criteria) this;
        }

        public Criteria andContractPicUrlNotIn(List<String> values) {
            addCriterion("CONTRACT_PIC_URL not in", values, "contractPicUrl");
            return (Criteria) this;
        }

        public Criteria andContractPicUrlBetween(String value1, String value2) {
            addCriterion("CONTRACT_PIC_URL between", value1, value2, "contractPicUrl");
            return (Criteria) this;
        }

        public Criteria andContractPicUrlNotBetween(String value1, String value2) {
            addCriterion("CONTRACT_PIC_URL not between", value1, value2, "contractPicUrl");
            return (Criteria) this;
        }

        public Criteria andMemberPicUrlIsNull() {
            addCriterion("MEMBER_PIC_URL is null");
            return (Criteria) this;
        }

        public Criteria andMemberPicUrlIsNotNull() {
            addCriterion("MEMBER_PIC_URL is not null");
            return (Criteria) this;
        }

        public Criteria andMemberPicUrlEqualTo(String value) {
            addCriterion("MEMBER_PIC_URL =", value, "memberPicUrl");
            return (Criteria) this;
        }

        public Criteria andMemberPicUrlNotEqualTo(String value) {
            addCriterion("MEMBER_PIC_URL <>", value, "memberPicUrl");
            return (Criteria) this;
        }

        public Criteria andMemberPicUrlGreaterThan(String value) {
            addCriterion("MEMBER_PIC_URL >", value, "memberPicUrl");
            return (Criteria) this;
        }

        public Criteria andMemberPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("MEMBER_PIC_URL >=", value, "memberPicUrl");
            return (Criteria) this;
        }

        public Criteria andMemberPicUrlLessThan(String value) {
            addCriterion("MEMBER_PIC_URL <", value, "memberPicUrl");
            return (Criteria) this;
        }

        public Criteria andMemberPicUrlLessThanOrEqualTo(String value) {
            addCriterion("MEMBER_PIC_URL <=", value, "memberPicUrl");
            return (Criteria) this;
        }

        public Criteria andMemberPicUrlLike(String value) {
            addCriterion("MEMBER_PIC_URL like", value, "memberPicUrl");
            return (Criteria) this;
        }

        public Criteria andMemberPicUrlNotLike(String value) {
            addCriterion("MEMBER_PIC_URL not like", value, "memberPicUrl");
            return (Criteria) this;
        }

        public Criteria andMemberPicUrlIn(List<String> values) {
            addCriterion("MEMBER_PIC_URL in", values, "memberPicUrl");
            return (Criteria) this;
        }

        public Criteria andMemberPicUrlNotIn(List<String> values) {
            addCriterion("MEMBER_PIC_URL not in", values, "memberPicUrl");
            return (Criteria) this;
        }

        public Criteria andMemberPicUrlBetween(String value1, String value2) {
            addCriterion("MEMBER_PIC_URL between", value1, value2, "memberPicUrl");
            return (Criteria) this;
        }

        public Criteria andMemberPicUrlNotBetween(String value1, String value2) {
            addCriterion("MEMBER_PIC_URL not between", value1, value2, "memberPicUrl");
            return (Criteria) this;
        }

        public Criteria andFundsPicUrlIsNull() {
            addCriterion("FUNDS_PIC_URL is null");
            return (Criteria) this;
        }

        public Criteria andFundsPicUrlIsNotNull() {
            addCriterion("FUNDS_PIC_URL is not null");
            return (Criteria) this;
        }

        public Criteria andFundsPicUrlEqualTo(String value) {
            addCriterion("FUNDS_PIC_URL =", value, "fundsPicUrl");
            return (Criteria) this;
        }

        public Criteria andFundsPicUrlNotEqualTo(String value) {
            addCriterion("FUNDS_PIC_URL <>", value, "fundsPicUrl");
            return (Criteria) this;
        }

        public Criteria andFundsPicUrlGreaterThan(String value) {
            addCriterion("FUNDS_PIC_URL >", value, "fundsPicUrl");
            return (Criteria) this;
        }

        public Criteria andFundsPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("FUNDS_PIC_URL >=", value, "fundsPicUrl");
            return (Criteria) this;
        }

        public Criteria andFundsPicUrlLessThan(String value) {
            addCriterion("FUNDS_PIC_URL <", value, "fundsPicUrl");
            return (Criteria) this;
        }

        public Criteria andFundsPicUrlLessThanOrEqualTo(String value) {
            addCriterion("FUNDS_PIC_URL <=", value, "fundsPicUrl");
            return (Criteria) this;
        }

        public Criteria andFundsPicUrlLike(String value) {
            addCriterion("FUNDS_PIC_URL like", value, "fundsPicUrl");
            return (Criteria) this;
        }

        public Criteria andFundsPicUrlNotLike(String value) {
            addCriterion("FUNDS_PIC_URL not like", value, "fundsPicUrl");
            return (Criteria) this;
        }

        public Criteria andFundsPicUrlIn(List<String> values) {
            addCriterion("FUNDS_PIC_URL in", values, "fundsPicUrl");
            return (Criteria) this;
        }

        public Criteria andFundsPicUrlNotIn(List<String> values) {
            addCriterion("FUNDS_PIC_URL not in", values, "fundsPicUrl");
            return (Criteria) this;
        }

        public Criteria andFundsPicUrlBetween(String value1, String value2) {
            addCriterion("FUNDS_PIC_URL between", value1, value2, "fundsPicUrl");
            return (Criteria) this;
        }

        public Criteria andFundsPicUrlNotBetween(String value1, String value2) {
            addCriterion("FUNDS_PIC_URL not between", value1, value2, "fundsPicUrl");
            return (Criteria) this;
        }

        public Criteria andSchedulePicUrlIsNull() {
            addCriterion("SCHEDULE_PIC_URL is null");
            return (Criteria) this;
        }

        public Criteria andSchedulePicUrlIsNotNull() {
            addCriterion("SCHEDULE_PIC_URL is not null");
            return (Criteria) this;
        }

        public Criteria andSchedulePicUrlEqualTo(String value) {
            addCriterion("SCHEDULE_PIC_URL =", value, "schedulePicUrl");
            return (Criteria) this;
        }

        public Criteria andSchedulePicUrlNotEqualTo(String value) {
            addCriterion("SCHEDULE_PIC_URL <>", value, "schedulePicUrl");
            return (Criteria) this;
        }

        public Criteria andSchedulePicUrlGreaterThan(String value) {
            addCriterion("SCHEDULE_PIC_URL >", value, "schedulePicUrl");
            return (Criteria) this;
        }

        public Criteria andSchedulePicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("SCHEDULE_PIC_URL >=", value, "schedulePicUrl");
            return (Criteria) this;
        }

        public Criteria andSchedulePicUrlLessThan(String value) {
            addCriterion("SCHEDULE_PIC_URL <", value, "schedulePicUrl");
            return (Criteria) this;
        }

        public Criteria andSchedulePicUrlLessThanOrEqualTo(String value) {
            addCriterion("SCHEDULE_PIC_URL <=", value, "schedulePicUrl");
            return (Criteria) this;
        }

        public Criteria andSchedulePicUrlLike(String value) {
            addCriterion("SCHEDULE_PIC_URL like", value, "schedulePicUrl");
            return (Criteria) this;
        }

        public Criteria andSchedulePicUrlNotLike(String value) {
            addCriterion("SCHEDULE_PIC_URL not like", value, "schedulePicUrl");
            return (Criteria) this;
        }

        public Criteria andSchedulePicUrlIn(List<String> values) {
            addCriterion("SCHEDULE_PIC_URL in", values, "schedulePicUrl");
            return (Criteria) this;
        }

        public Criteria andSchedulePicUrlNotIn(List<String> values) {
            addCriterion("SCHEDULE_PIC_URL not in", values, "schedulePicUrl");
            return (Criteria) this;
        }

        public Criteria andSchedulePicUrlBetween(String value1, String value2) {
            addCriterion("SCHEDULE_PIC_URL between", value1, value2, "schedulePicUrl");
            return (Criteria) this;
        }

        public Criteria andSchedulePicUrlNotBetween(String value1, String value2) {
            addCriterion("SCHEDULE_PIC_URL not between", value1, value2, "schedulePicUrl");
            return (Criteria) this;
        }

        public Criteria andSealPicUrlIsNull() {
            addCriterion("SEAL_PIC_URL is null");
            return (Criteria) this;
        }

        public Criteria andSealPicUrlIsNotNull() {
            addCriterion("SEAL_PIC_URL is not null");
            return (Criteria) this;
        }

        public Criteria andSealPicUrlEqualTo(String value) {
            addCriterion("SEAL_PIC_URL =", value, "sealPicUrl");
            return (Criteria) this;
        }

        public Criteria andSealPicUrlNotEqualTo(String value) {
            addCriterion("SEAL_PIC_URL <>", value, "sealPicUrl");
            return (Criteria) this;
        }

        public Criteria andSealPicUrlGreaterThan(String value) {
            addCriterion("SEAL_PIC_URL >", value, "sealPicUrl");
            return (Criteria) this;
        }

        public Criteria andSealPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("SEAL_PIC_URL >=", value, "sealPicUrl");
            return (Criteria) this;
        }

        public Criteria andSealPicUrlLessThan(String value) {
            addCriterion("SEAL_PIC_URL <", value, "sealPicUrl");
            return (Criteria) this;
        }

        public Criteria andSealPicUrlLessThanOrEqualTo(String value) {
            addCriterion("SEAL_PIC_URL <=", value, "sealPicUrl");
            return (Criteria) this;
        }

        public Criteria andSealPicUrlLike(String value) {
            addCriterion("SEAL_PIC_URL like", value, "sealPicUrl");
            return (Criteria) this;
        }

        public Criteria andSealPicUrlNotLike(String value) {
            addCriterion("SEAL_PIC_URL not like", value, "sealPicUrl");
            return (Criteria) this;
        }

        public Criteria andSealPicUrlIn(List<String> values) {
            addCriterion("SEAL_PIC_URL in", values, "sealPicUrl");
            return (Criteria) this;
        }

        public Criteria andSealPicUrlNotIn(List<String> values) {
            addCriterion("SEAL_PIC_URL not in", values, "sealPicUrl");
            return (Criteria) this;
        }

        public Criteria andSealPicUrlBetween(String value1, String value2) {
            addCriterion("SEAL_PIC_URL between", value1, value2, "sealPicUrl");
            return (Criteria) this;
        }

        public Criteria andSealPicUrlNotBetween(String value1, String value2) {
            addCriterion("SEAL_PIC_URL not between", value1, value2, "sealPicUrl");
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

        public Criteria andSetupTimeIsNull() {
            addCriterion("SETUP_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSetupTimeIsNotNull() {
            addCriterion("SETUP_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSetupTimeEqualTo(String value) {
            addCriterion("SETUP_TIME =", value, "setupTime");
            return (Criteria) this;
        }

        public Criteria andSetupTimeNotEqualTo(String value) {
            addCriterion("SETUP_TIME <>", value, "setupTime");
            return (Criteria) this;
        }

        public Criteria andSetupTimeGreaterThan(String value) {
            addCriterion("SETUP_TIME >", value, "setupTime");
            return (Criteria) this;
        }

        public Criteria andSetupTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SETUP_TIME >=", value, "setupTime");
            return (Criteria) this;
        }

        public Criteria andSetupTimeLessThan(String value) {
            addCriterion("SETUP_TIME <", value, "setupTime");
            return (Criteria) this;
        }

        public Criteria andSetupTimeLessThanOrEqualTo(String value) {
            addCriterion("SETUP_TIME <=", value, "setupTime");
            return (Criteria) this;
        }

        public Criteria andSetupTimeLike(String value) {
            addCriterion("SETUP_TIME like", value, "setupTime");
            return (Criteria) this;
        }

        public Criteria andSetupTimeNotLike(String value) {
            addCriterion("SETUP_TIME not like", value, "setupTime");
            return (Criteria) this;
        }

        public Criteria andSetupTimeIn(List<String> values) {
            addCriterion("SETUP_TIME in", values, "setupTime");
            return (Criteria) this;
        }

        public Criteria andSetupTimeNotIn(List<String> values) {
            addCriterion("SETUP_TIME not in", values, "setupTime");
            return (Criteria) this;
        }

        public Criteria andSetupTimeBetween(String value1, String value2) {
            addCriterion("SETUP_TIME between", value1, value2, "setupTime");
            return (Criteria) this;
        }

        public Criteria andSetupTimeNotBetween(String value1, String value2) {
            addCriterion("SETUP_TIME not between", value1, value2, "setupTime");
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