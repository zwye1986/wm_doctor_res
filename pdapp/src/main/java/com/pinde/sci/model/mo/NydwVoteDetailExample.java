package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class NydwVoteDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NydwVoteDetailExample() {
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

        public Criteria andVoterFlowIsNull() {
            addCriterion("VOTER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andVoterFlowIsNotNull() {
            addCriterion("VOTER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andVoterFlowEqualTo(String value) {
            addCriterion("VOTER_FLOW =", value, "voterFlow");
            return (Criteria) this;
        }

        public Criteria andVoterFlowNotEqualTo(String value) {
            addCriterion("VOTER_FLOW <>", value, "voterFlow");
            return (Criteria) this;
        }

        public Criteria andVoterFlowGreaterThan(String value) {
            addCriterion("VOTER_FLOW >", value, "voterFlow");
            return (Criteria) this;
        }

        public Criteria andVoterFlowGreaterThanOrEqualTo(String value) {
            addCriterion("VOTER_FLOW >=", value, "voterFlow");
            return (Criteria) this;
        }

        public Criteria andVoterFlowLessThan(String value) {
            addCriterion("VOTER_FLOW <", value, "voterFlow");
            return (Criteria) this;
        }

        public Criteria andVoterFlowLessThanOrEqualTo(String value) {
            addCriterion("VOTER_FLOW <=", value, "voterFlow");
            return (Criteria) this;
        }

        public Criteria andVoterFlowLike(String value) {
            addCriterion("VOTER_FLOW like", value, "voterFlow");
            return (Criteria) this;
        }

        public Criteria andVoterFlowNotLike(String value) {
            addCriterion("VOTER_FLOW not like", value, "voterFlow");
            return (Criteria) this;
        }

        public Criteria andVoterFlowIn(List<String> values) {
            addCriterion("VOTER_FLOW in", values, "voterFlow");
            return (Criteria) this;
        }

        public Criteria andVoterFlowNotIn(List<String> values) {
            addCriterion("VOTER_FLOW not in", values, "voterFlow");
            return (Criteria) this;
        }

        public Criteria andVoterFlowBetween(String value1, String value2) {
            addCriterion("VOTER_FLOW between", value1, value2, "voterFlow");
            return (Criteria) this;
        }

        public Criteria andVoterFlowNotBetween(String value1, String value2) {
            addCriterion("VOTER_FLOW not between", value1, value2, "voterFlow");
            return (Criteria) this;
        }

        public Criteria andVoterNameIsNull() {
            addCriterion("VOTER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andVoterNameIsNotNull() {
            addCriterion("VOTER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andVoterNameEqualTo(String value) {
            addCriterion("VOTER_NAME =", value, "voterName");
            return (Criteria) this;
        }

        public Criteria andVoterNameNotEqualTo(String value) {
            addCriterion("VOTER_NAME <>", value, "voterName");
            return (Criteria) this;
        }

        public Criteria andVoterNameGreaterThan(String value) {
            addCriterion("VOTER_NAME >", value, "voterName");
            return (Criteria) this;
        }

        public Criteria andVoterNameGreaterThanOrEqualTo(String value) {
            addCriterion("VOTER_NAME >=", value, "voterName");
            return (Criteria) this;
        }

        public Criteria andVoterNameLessThan(String value) {
            addCriterion("VOTER_NAME <", value, "voterName");
            return (Criteria) this;
        }

        public Criteria andVoterNameLessThanOrEqualTo(String value) {
            addCriterion("VOTER_NAME <=", value, "voterName");
            return (Criteria) this;
        }

        public Criteria andVoterNameLike(String value) {
            addCriterion("VOTER_NAME like", value, "voterName");
            return (Criteria) this;
        }

        public Criteria andVoterNameNotLike(String value) {
            addCriterion("VOTER_NAME not like", value, "voterName");
            return (Criteria) this;
        }

        public Criteria andVoterNameIn(List<String> values) {
            addCriterion("VOTER_NAME in", values, "voterName");
            return (Criteria) this;
        }

        public Criteria andVoterNameNotIn(List<String> values) {
            addCriterion("VOTER_NAME not in", values, "voterName");
            return (Criteria) this;
        }

        public Criteria andVoterNameBetween(String value1, String value2) {
            addCriterion("VOTER_NAME between", value1, value2, "voterName");
            return (Criteria) this;
        }

        public Criteria andVoterNameNotBetween(String value1, String value2) {
            addCriterion("VOTER_NAME not between", value1, value2, "voterName");
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