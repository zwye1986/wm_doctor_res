package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ResInprocessInfoMemberExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResInprocessInfoMemberExample() {
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

        public Criteria andInfoRecordFlowIsNull() {
            addCriterion("INFO_RECORD_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andInfoRecordFlowIsNotNull() {
            addCriterion("INFO_RECORD_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andInfoRecordFlowEqualTo(String value) {
            addCriterion("INFO_RECORD_FLOW =", value, "infoRecordFlow");
            return (Criteria) this;
        }

        public Criteria andInfoRecordFlowNotEqualTo(String value) {
            addCriterion("INFO_RECORD_FLOW <>", value, "infoRecordFlow");
            return (Criteria) this;
        }

        public Criteria andInfoRecordFlowGreaterThan(String value) {
            addCriterion("INFO_RECORD_FLOW >", value, "infoRecordFlow");
            return (Criteria) this;
        }

        public Criteria andInfoRecordFlowGreaterThanOrEqualTo(String value) {
            addCriterion("INFO_RECORD_FLOW >=", value, "infoRecordFlow");
            return (Criteria) this;
        }

        public Criteria andInfoRecordFlowLessThan(String value) {
            addCriterion("INFO_RECORD_FLOW <", value, "infoRecordFlow");
            return (Criteria) this;
        }

        public Criteria andInfoRecordFlowLessThanOrEqualTo(String value) {
            addCriterion("INFO_RECORD_FLOW <=", value, "infoRecordFlow");
            return (Criteria) this;
        }

        public Criteria andInfoRecordFlowLike(String value) {
            addCriterion("INFO_RECORD_FLOW like", value, "infoRecordFlow");
            return (Criteria) this;
        }

        public Criteria andInfoRecordFlowNotLike(String value) {
            addCriterion("INFO_RECORD_FLOW not like", value, "infoRecordFlow");
            return (Criteria) this;
        }

        public Criteria andInfoRecordFlowIn(List<String> values) {
            addCriterion("INFO_RECORD_FLOW in", values, "infoRecordFlow");
            return (Criteria) this;
        }

        public Criteria andInfoRecordFlowNotIn(List<String> values) {
            addCriterion("INFO_RECORD_FLOW not in", values, "infoRecordFlow");
            return (Criteria) this;
        }

        public Criteria andInfoRecordFlowBetween(String value1, String value2) {
            addCriterion("INFO_RECORD_FLOW between", value1, value2, "infoRecordFlow");
            return (Criteria) this;
        }

        public Criteria andInfoRecordFlowNotBetween(String value1, String value2) {
            addCriterion("INFO_RECORD_FLOW not between", value1, value2, "infoRecordFlow");
            return (Criteria) this;
        }

        public Criteria andMemberTitleIdIsNull() {
            addCriterion("MEMBER_TITLE_ID is null");
            return (Criteria) this;
        }

        public Criteria andMemberTitleIdIsNotNull() {
            addCriterion("MEMBER_TITLE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMemberTitleIdEqualTo(String value) {
            addCriterion("MEMBER_TITLE_ID =", value, "memberTitleId");
            return (Criteria) this;
        }

        public Criteria andMemberTitleIdNotEqualTo(String value) {
            addCriterion("MEMBER_TITLE_ID <>", value, "memberTitleId");
            return (Criteria) this;
        }

        public Criteria andMemberTitleIdGreaterThan(String value) {
            addCriterion("MEMBER_TITLE_ID >", value, "memberTitleId");
            return (Criteria) this;
        }

        public Criteria andMemberTitleIdGreaterThanOrEqualTo(String value) {
            addCriterion("MEMBER_TITLE_ID >=", value, "memberTitleId");
            return (Criteria) this;
        }

        public Criteria andMemberTitleIdLessThan(String value) {
            addCriterion("MEMBER_TITLE_ID <", value, "memberTitleId");
            return (Criteria) this;
        }

        public Criteria andMemberTitleIdLessThanOrEqualTo(String value) {
            addCriterion("MEMBER_TITLE_ID <=", value, "memberTitleId");
            return (Criteria) this;
        }

        public Criteria andMemberTitleIdLike(String value) {
            addCriterion("MEMBER_TITLE_ID like", value, "memberTitleId");
            return (Criteria) this;
        }

        public Criteria andMemberTitleIdNotLike(String value) {
            addCriterion("MEMBER_TITLE_ID not like", value, "memberTitleId");
            return (Criteria) this;
        }

        public Criteria andMemberTitleIdIn(List<String> values) {
            addCriterion("MEMBER_TITLE_ID in", values, "memberTitleId");
            return (Criteria) this;
        }

        public Criteria andMemberTitleIdNotIn(List<String> values) {
            addCriterion("MEMBER_TITLE_ID not in", values, "memberTitleId");
            return (Criteria) this;
        }

        public Criteria andMemberTitleIdBetween(String value1, String value2) {
            addCriterion("MEMBER_TITLE_ID between", value1, value2, "memberTitleId");
            return (Criteria) this;
        }

        public Criteria andMemberTitleIdNotBetween(String value1, String value2) {
            addCriterion("MEMBER_TITLE_ID not between", value1, value2, "memberTitleId");
            return (Criteria) this;
        }

        public Criteria andMemberTitleNameIsNull() {
            addCriterion("MEMBER_TITLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMemberTitleNameIsNotNull() {
            addCriterion("MEMBER_TITLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMemberTitleNameEqualTo(String value) {
            addCriterion("MEMBER_TITLE_NAME =", value, "memberTitleName");
            return (Criteria) this;
        }

        public Criteria andMemberTitleNameNotEqualTo(String value) {
            addCriterion("MEMBER_TITLE_NAME <>", value, "memberTitleName");
            return (Criteria) this;
        }

        public Criteria andMemberTitleNameGreaterThan(String value) {
            addCriterion("MEMBER_TITLE_NAME >", value, "memberTitleName");
            return (Criteria) this;
        }

        public Criteria andMemberTitleNameGreaterThanOrEqualTo(String value) {
            addCriterion("MEMBER_TITLE_NAME >=", value, "memberTitleName");
            return (Criteria) this;
        }

        public Criteria andMemberTitleNameLessThan(String value) {
            addCriterion("MEMBER_TITLE_NAME <", value, "memberTitleName");
            return (Criteria) this;
        }

        public Criteria andMemberTitleNameLessThanOrEqualTo(String value) {
            addCriterion("MEMBER_TITLE_NAME <=", value, "memberTitleName");
            return (Criteria) this;
        }

        public Criteria andMemberTitleNameLike(String value) {
            addCriterion("MEMBER_TITLE_NAME like", value, "memberTitleName");
            return (Criteria) this;
        }

        public Criteria andMemberTitleNameNotLike(String value) {
            addCriterion("MEMBER_TITLE_NAME not like", value, "memberTitleName");
            return (Criteria) this;
        }

        public Criteria andMemberTitleNameIn(List<String> values) {
            addCriterion("MEMBER_TITLE_NAME in", values, "memberTitleName");
            return (Criteria) this;
        }

        public Criteria andMemberTitleNameNotIn(List<String> values) {
            addCriterion("MEMBER_TITLE_NAME not in", values, "memberTitleName");
            return (Criteria) this;
        }

        public Criteria andMemberTitleNameBetween(String value1, String value2) {
            addCriterion("MEMBER_TITLE_NAME between", value1, value2, "memberTitleName");
            return (Criteria) this;
        }

        public Criteria andMemberTitleNameNotBetween(String value1, String value2) {
            addCriterion("MEMBER_TITLE_NAME not between", value1, value2, "memberTitleName");
            return (Criteria) this;
        }

        public Criteria andMemberNameIsNull() {
            addCriterion("MEMBER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMemberNameIsNotNull() {
            addCriterion("MEMBER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMemberNameEqualTo(String value) {
            addCriterion("MEMBER_NAME =", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotEqualTo(String value) {
            addCriterion("MEMBER_NAME <>", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameGreaterThan(String value) {
            addCriterion("MEMBER_NAME >", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameGreaterThanOrEqualTo(String value) {
            addCriterion("MEMBER_NAME >=", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameLessThan(String value) {
            addCriterion("MEMBER_NAME <", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameLessThanOrEqualTo(String value) {
            addCriterion("MEMBER_NAME <=", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameLike(String value) {
            addCriterion("MEMBER_NAME like", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotLike(String value) {
            addCriterion("MEMBER_NAME not like", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameIn(List<String> values) {
            addCriterion("MEMBER_NAME in", values, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotIn(List<String> values) {
            addCriterion("MEMBER_NAME not in", values, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameBetween(String value1, String value2) {
            addCriterion("MEMBER_NAME between", value1, value2, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotBetween(String value1, String value2) {
            addCriterion("MEMBER_NAME not between", value1, value2, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneIsNull() {
            addCriterion("MEMBER_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneIsNotNull() {
            addCriterion("MEMBER_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneEqualTo(String value) {
            addCriterion("MEMBER_PHONE =", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneNotEqualTo(String value) {
            addCriterion("MEMBER_PHONE <>", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneGreaterThan(String value) {
            addCriterion("MEMBER_PHONE >", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("MEMBER_PHONE >=", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneLessThan(String value) {
            addCriterion("MEMBER_PHONE <", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneLessThanOrEqualTo(String value) {
            addCriterion("MEMBER_PHONE <=", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneLike(String value) {
            addCriterion("MEMBER_PHONE like", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneNotLike(String value) {
            addCriterion("MEMBER_PHONE not like", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneIn(List<String> values) {
            addCriterion("MEMBER_PHONE in", values, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneNotIn(List<String> values) {
            addCriterion("MEMBER_PHONE not in", values, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneBetween(String value1, String value2) {
            addCriterion("MEMBER_PHONE between", value1, value2, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneNotBetween(String value1, String value2) {
            addCriterion("MEMBER_PHONE not between", value1, value2, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPostIdIsNull() {
            addCriterion("MEMBER_POST_ID is null");
            return (Criteria) this;
        }

        public Criteria andMemberPostIdIsNotNull() {
            addCriterion("MEMBER_POST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMemberPostIdEqualTo(String value) {
            addCriterion("MEMBER_POST_ID =", value, "memberPostId");
            return (Criteria) this;
        }

        public Criteria andMemberPostIdNotEqualTo(String value) {
            addCriterion("MEMBER_POST_ID <>", value, "memberPostId");
            return (Criteria) this;
        }

        public Criteria andMemberPostIdGreaterThan(String value) {
            addCriterion("MEMBER_POST_ID >", value, "memberPostId");
            return (Criteria) this;
        }

        public Criteria andMemberPostIdGreaterThanOrEqualTo(String value) {
            addCriterion("MEMBER_POST_ID >=", value, "memberPostId");
            return (Criteria) this;
        }

        public Criteria andMemberPostIdLessThan(String value) {
            addCriterion("MEMBER_POST_ID <", value, "memberPostId");
            return (Criteria) this;
        }

        public Criteria andMemberPostIdLessThanOrEqualTo(String value) {
            addCriterion("MEMBER_POST_ID <=", value, "memberPostId");
            return (Criteria) this;
        }

        public Criteria andMemberPostIdLike(String value) {
            addCriterion("MEMBER_POST_ID like", value, "memberPostId");
            return (Criteria) this;
        }

        public Criteria andMemberPostIdNotLike(String value) {
            addCriterion("MEMBER_POST_ID not like", value, "memberPostId");
            return (Criteria) this;
        }

        public Criteria andMemberPostIdIn(List<String> values) {
            addCriterion("MEMBER_POST_ID in", values, "memberPostId");
            return (Criteria) this;
        }

        public Criteria andMemberPostIdNotIn(List<String> values) {
            addCriterion("MEMBER_POST_ID not in", values, "memberPostId");
            return (Criteria) this;
        }

        public Criteria andMemberPostIdBetween(String value1, String value2) {
            addCriterion("MEMBER_POST_ID between", value1, value2, "memberPostId");
            return (Criteria) this;
        }

        public Criteria andMemberPostIdNotBetween(String value1, String value2) {
            addCriterion("MEMBER_POST_ID not between", value1, value2, "memberPostId");
            return (Criteria) this;
        }

        public Criteria andMemberPostNameIsNull() {
            addCriterion("MEMBER_POST_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMemberPostNameIsNotNull() {
            addCriterion("MEMBER_POST_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMemberPostNameEqualTo(String value) {
            addCriterion("MEMBER_POST_NAME =", value, "memberPostName");
            return (Criteria) this;
        }

        public Criteria andMemberPostNameNotEqualTo(String value) {
            addCriterion("MEMBER_POST_NAME <>", value, "memberPostName");
            return (Criteria) this;
        }

        public Criteria andMemberPostNameGreaterThan(String value) {
            addCriterion("MEMBER_POST_NAME >", value, "memberPostName");
            return (Criteria) this;
        }

        public Criteria andMemberPostNameGreaterThanOrEqualTo(String value) {
            addCriterion("MEMBER_POST_NAME >=", value, "memberPostName");
            return (Criteria) this;
        }

        public Criteria andMemberPostNameLessThan(String value) {
            addCriterion("MEMBER_POST_NAME <", value, "memberPostName");
            return (Criteria) this;
        }

        public Criteria andMemberPostNameLessThanOrEqualTo(String value) {
            addCriterion("MEMBER_POST_NAME <=", value, "memberPostName");
            return (Criteria) this;
        }

        public Criteria andMemberPostNameLike(String value) {
            addCriterion("MEMBER_POST_NAME like", value, "memberPostName");
            return (Criteria) this;
        }

        public Criteria andMemberPostNameNotLike(String value) {
            addCriterion("MEMBER_POST_NAME not like", value, "memberPostName");
            return (Criteria) this;
        }

        public Criteria andMemberPostNameIn(List<String> values) {
            addCriterion("MEMBER_POST_NAME in", values, "memberPostName");
            return (Criteria) this;
        }

        public Criteria andMemberPostNameNotIn(List<String> values) {
            addCriterion("MEMBER_POST_NAME not in", values, "memberPostName");
            return (Criteria) this;
        }

        public Criteria andMemberPostNameBetween(String value1, String value2) {
            addCriterion("MEMBER_POST_NAME between", value1, value2, "memberPostName");
            return (Criteria) this;
        }

        public Criteria andMemberPostNameNotBetween(String value1, String value2) {
            addCriterion("MEMBER_POST_NAME not between", value1, value2, "memberPostName");
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