package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class NydwElectorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NydwElectorExample() {
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

        public Criteria andVoteFlowIsNull() {
            addCriterion("VOTE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andVoteFlowIsNotNull() {
            addCriterion("VOTE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andVoteFlowEqualTo(String value) {
            addCriterion("VOTE_FLOW =", value, "voteFlow");
            return (Criteria) this;
        }

        public Criteria andVoteFlowNotEqualTo(String value) {
            addCriterion("VOTE_FLOW <>", value, "voteFlow");
            return (Criteria) this;
        }

        public Criteria andVoteFlowGreaterThan(String value) {
            addCriterion("VOTE_FLOW >", value, "voteFlow");
            return (Criteria) this;
        }

        public Criteria andVoteFlowGreaterThanOrEqualTo(String value) {
            addCriterion("VOTE_FLOW >=", value, "voteFlow");
            return (Criteria) this;
        }

        public Criteria andVoteFlowLessThan(String value) {
            addCriterion("VOTE_FLOW <", value, "voteFlow");
            return (Criteria) this;
        }

        public Criteria andVoteFlowLessThanOrEqualTo(String value) {
            addCriterion("VOTE_FLOW <=", value, "voteFlow");
            return (Criteria) this;
        }

        public Criteria andVoteFlowLike(String value) {
            addCriterion("VOTE_FLOW like", value, "voteFlow");
            return (Criteria) this;
        }

        public Criteria andVoteFlowNotLike(String value) {
            addCriterion("VOTE_FLOW not like", value, "voteFlow");
            return (Criteria) this;
        }

        public Criteria andVoteFlowIn(List<String> values) {
            addCriterion("VOTE_FLOW in", values, "voteFlow");
            return (Criteria) this;
        }

        public Criteria andVoteFlowNotIn(List<String> values) {
            addCriterion("VOTE_FLOW not in", values, "voteFlow");
            return (Criteria) this;
        }

        public Criteria andVoteFlowBetween(String value1, String value2) {
            addCriterion("VOTE_FLOW between", value1, value2, "voteFlow");
            return (Criteria) this;
        }

        public Criteria andVoteFlowNotBetween(String value1, String value2) {
            addCriterion("VOTE_FLOW not between", value1, value2, "voteFlow");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdIsNull() {
            addCriterion("PARTY_BRANCH_ID is null");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdIsNotNull() {
            addCriterion("PARTY_BRANCH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdEqualTo(String value) {
            addCriterion("PARTY_BRANCH_ID =", value, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdNotEqualTo(String value) {
            addCriterion("PARTY_BRANCH_ID <>", value, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdGreaterThan(String value) {
            addCriterion("PARTY_BRANCH_ID >", value, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdGreaterThanOrEqualTo(String value) {
            addCriterion("PARTY_BRANCH_ID >=", value, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdLessThan(String value) {
            addCriterion("PARTY_BRANCH_ID <", value, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdLessThanOrEqualTo(String value) {
            addCriterion("PARTY_BRANCH_ID <=", value, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdLike(String value) {
            addCriterion("PARTY_BRANCH_ID like", value, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdNotLike(String value) {
            addCriterion("PARTY_BRANCH_ID not like", value, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdIn(List<String> values) {
            addCriterion("PARTY_BRANCH_ID in", values, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdNotIn(List<String> values) {
            addCriterion("PARTY_BRANCH_ID not in", values, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdBetween(String value1, String value2) {
            addCriterion("PARTY_BRANCH_ID between", value1, value2, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdNotBetween(String value1, String value2) {
            addCriterion("PARTY_BRANCH_ID not between", value1, value2, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameIsNull() {
            addCriterion("PARTY_BRANCH_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameIsNotNull() {
            addCriterion("PARTY_BRANCH_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameEqualTo(String value) {
            addCriterion("PARTY_BRANCH_NAME =", value, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameNotEqualTo(String value) {
            addCriterion("PARTY_BRANCH_NAME <>", value, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameGreaterThan(String value) {
            addCriterion("PARTY_BRANCH_NAME >", value, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameGreaterThanOrEqualTo(String value) {
            addCriterion("PARTY_BRANCH_NAME >=", value, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameLessThan(String value) {
            addCriterion("PARTY_BRANCH_NAME <", value, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameLessThanOrEqualTo(String value) {
            addCriterion("PARTY_BRANCH_NAME <=", value, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameLike(String value) {
            addCriterion("PARTY_BRANCH_NAME like", value, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameNotLike(String value) {
            addCriterion("PARTY_BRANCH_NAME not like", value, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameIn(List<String> values) {
            addCriterion("PARTY_BRANCH_NAME in", values, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameNotIn(List<String> values) {
            addCriterion("PARTY_BRANCH_NAME not in", values, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameBetween(String value1, String value2) {
            addCriterion("PARTY_BRANCH_NAME between", value1, value2, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameNotBetween(String value1, String value2) {
            addCriterion("PARTY_BRANCH_NAME not between", value1, value2, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andElectorFlowIsNull() {
            addCriterion("ELECTOR_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andElectorFlowIsNotNull() {
            addCriterion("ELECTOR_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andElectorFlowEqualTo(String value) {
            addCriterion("ELECTOR_FLOW =", value, "electorFlow");
            return (Criteria) this;
        }

        public Criteria andElectorFlowNotEqualTo(String value) {
            addCriterion("ELECTOR_FLOW <>", value, "electorFlow");
            return (Criteria) this;
        }

        public Criteria andElectorFlowGreaterThan(String value) {
            addCriterion("ELECTOR_FLOW >", value, "electorFlow");
            return (Criteria) this;
        }

        public Criteria andElectorFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ELECTOR_FLOW >=", value, "electorFlow");
            return (Criteria) this;
        }

        public Criteria andElectorFlowLessThan(String value) {
            addCriterion("ELECTOR_FLOW <", value, "electorFlow");
            return (Criteria) this;
        }

        public Criteria andElectorFlowLessThanOrEqualTo(String value) {
            addCriterion("ELECTOR_FLOW <=", value, "electorFlow");
            return (Criteria) this;
        }

        public Criteria andElectorFlowLike(String value) {
            addCriterion("ELECTOR_FLOW like", value, "electorFlow");
            return (Criteria) this;
        }

        public Criteria andElectorFlowNotLike(String value) {
            addCriterion("ELECTOR_FLOW not like", value, "electorFlow");
            return (Criteria) this;
        }

        public Criteria andElectorFlowIn(List<String> values) {
            addCriterion("ELECTOR_FLOW in", values, "electorFlow");
            return (Criteria) this;
        }

        public Criteria andElectorFlowNotIn(List<String> values) {
            addCriterion("ELECTOR_FLOW not in", values, "electorFlow");
            return (Criteria) this;
        }

        public Criteria andElectorFlowBetween(String value1, String value2) {
            addCriterion("ELECTOR_FLOW between", value1, value2, "electorFlow");
            return (Criteria) this;
        }

        public Criteria andElectorFlowNotBetween(String value1, String value2) {
            addCriterion("ELECTOR_FLOW not between", value1, value2, "electorFlow");
            return (Criteria) this;
        }

        public Criteria andElectorNameIsNull() {
            addCriterion("ELECTOR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andElectorNameIsNotNull() {
            addCriterion("ELECTOR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andElectorNameEqualTo(String value) {
            addCriterion("ELECTOR_NAME =", value, "electorName");
            return (Criteria) this;
        }

        public Criteria andElectorNameNotEqualTo(String value) {
            addCriterion("ELECTOR_NAME <>", value, "electorName");
            return (Criteria) this;
        }

        public Criteria andElectorNameGreaterThan(String value) {
            addCriterion("ELECTOR_NAME >", value, "electorName");
            return (Criteria) this;
        }

        public Criteria andElectorNameGreaterThanOrEqualTo(String value) {
            addCriterion("ELECTOR_NAME >=", value, "electorName");
            return (Criteria) this;
        }

        public Criteria andElectorNameLessThan(String value) {
            addCriterion("ELECTOR_NAME <", value, "electorName");
            return (Criteria) this;
        }

        public Criteria andElectorNameLessThanOrEqualTo(String value) {
            addCriterion("ELECTOR_NAME <=", value, "electorName");
            return (Criteria) this;
        }

        public Criteria andElectorNameLike(String value) {
            addCriterion("ELECTOR_NAME like", value, "electorName");
            return (Criteria) this;
        }

        public Criteria andElectorNameNotLike(String value) {
            addCriterion("ELECTOR_NAME not like", value, "electorName");
            return (Criteria) this;
        }

        public Criteria andElectorNameIn(List<String> values) {
            addCriterion("ELECTOR_NAME in", values, "electorName");
            return (Criteria) this;
        }

        public Criteria andElectorNameNotIn(List<String> values) {
            addCriterion("ELECTOR_NAME not in", values, "electorName");
            return (Criteria) this;
        }

        public Criteria andElectorNameBetween(String value1, String value2) {
            addCriterion("ELECTOR_NAME between", value1, value2, "electorName");
            return (Criteria) this;
        }

        public Criteria andElectorNameNotBetween(String value1, String value2) {
            addCriterion("ELECTOR_NAME not between", value1, value2, "electorName");
            return (Criteria) this;
        }

        public Criteria andSexIdIsNull() {
            addCriterion("SEX_ID is null");
            return (Criteria) this;
        }

        public Criteria andSexIdIsNotNull() {
            addCriterion("SEX_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSexIdEqualTo(String value) {
            addCriterion("SEX_ID =", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotEqualTo(String value) {
            addCriterion("SEX_ID <>", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdGreaterThan(String value) {
            addCriterion("SEX_ID >", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdGreaterThanOrEqualTo(String value) {
            addCriterion("SEX_ID >=", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdLessThan(String value) {
            addCriterion("SEX_ID <", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdLessThanOrEqualTo(String value) {
            addCriterion("SEX_ID <=", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdLike(String value) {
            addCriterion("SEX_ID like", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotLike(String value) {
            addCriterion("SEX_ID not like", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdIn(List<String> values) {
            addCriterion("SEX_ID in", values, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotIn(List<String> values) {
            addCriterion("SEX_ID not in", values, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdBetween(String value1, String value2) {
            addCriterion("SEX_ID between", value1, value2, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotBetween(String value1, String value2) {
            addCriterion("SEX_ID not between", value1, value2, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexNameIsNull() {
            addCriterion("SEX_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSexNameIsNotNull() {
            addCriterion("SEX_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSexNameEqualTo(String value) {
            addCriterion("SEX_NAME =", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotEqualTo(String value) {
            addCriterion("SEX_NAME <>", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameGreaterThan(String value) {
            addCriterion("SEX_NAME >", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameGreaterThanOrEqualTo(String value) {
            addCriterion("SEX_NAME >=", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameLessThan(String value) {
            addCriterion("SEX_NAME <", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameLessThanOrEqualTo(String value) {
            addCriterion("SEX_NAME <=", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameLike(String value) {
            addCriterion("SEX_NAME like", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotLike(String value) {
            addCriterion("SEX_NAME not like", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameIn(List<String> values) {
            addCriterion("SEX_NAME in", values, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotIn(List<String> values) {
            addCriterion("SEX_NAME not in", values, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameBetween(String value1, String value2) {
            addCriterion("SEX_NAME between", value1, value2, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotBetween(String value1, String value2) {
            addCriterion("SEX_NAME not between", value1, value2, "sexName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowIsNull() {
            addCriterion("PYDW_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowIsNotNull() {
            addCriterion("PYDW_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowEqualTo(String value) {
            addCriterion("PYDW_ORG_FLOW =", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowNotEqualTo(String value) {
            addCriterion("PYDW_ORG_FLOW <>", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowGreaterThan(String value) {
            addCriterion("PYDW_ORG_FLOW >", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PYDW_ORG_FLOW >=", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowLessThan(String value) {
            addCriterion("PYDW_ORG_FLOW <", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("PYDW_ORG_FLOW <=", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowLike(String value) {
            addCriterion("PYDW_ORG_FLOW like", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowNotLike(String value) {
            addCriterion("PYDW_ORG_FLOW not like", value, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowIn(List<String> values) {
            addCriterion("PYDW_ORG_FLOW in", values, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowNotIn(List<String> values) {
            addCriterion("PYDW_ORG_FLOW not in", values, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowBetween(String value1, String value2) {
            addCriterion("PYDW_ORG_FLOW between", value1, value2, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgFlowNotBetween(String value1, String value2) {
            addCriterion("PYDW_ORG_FLOW not between", value1, value2, "pydwOrgFlow");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameIsNull() {
            addCriterion("PYDW_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameIsNotNull() {
            addCriterion("PYDW_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameEqualTo(String value) {
            addCriterion("PYDW_ORG_NAME =", value, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameNotEqualTo(String value) {
            addCriterion("PYDW_ORG_NAME <>", value, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameGreaterThan(String value) {
            addCriterion("PYDW_ORG_NAME >", value, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("PYDW_ORG_NAME >=", value, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameLessThan(String value) {
            addCriterion("PYDW_ORG_NAME <", value, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameLessThanOrEqualTo(String value) {
            addCriterion("PYDW_ORG_NAME <=", value, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameLike(String value) {
            addCriterion("PYDW_ORG_NAME like", value, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameNotLike(String value) {
            addCriterion("PYDW_ORG_NAME not like", value, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameIn(List<String> values) {
            addCriterion("PYDW_ORG_NAME in", values, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameNotIn(List<String> values) {
            addCriterion("PYDW_ORG_NAME not in", values, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameBetween(String value1, String value2) {
            addCriterion("PYDW_ORG_NAME between", value1, value2, "pydwOrgName");
            return (Criteria) this;
        }

        public Criteria andPydwOrgNameNotBetween(String value1, String value2) {
            addCriterion("PYDW_ORG_NAME not between", value1, value2, "pydwOrgName");
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