package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class PortalSuggestExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PortalSuggestExample() {
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

        public Criteria andSuggestTitleIsNull() {
            addCriterion("SUGGEST_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andSuggestTitleIsNotNull() {
            addCriterion("SUGGEST_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andSuggestTitleEqualTo(String value) {
            addCriterion("SUGGEST_TITLE =", value, "suggestTitle");
            return (Criteria) this;
        }

        public Criteria andSuggestTitleNotEqualTo(String value) {
            addCriterion("SUGGEST_TITLE <>", value, "suggestTitle");
            return (Criteria) this;
        }

        public Criteria andSuggestTitleGreaterThan(String value) {
            addCriterion("SUGGEST_TITLE >", value, "suggestTitle");
            return (Criteria) this;
        }

        public Criteria andSuggestTitleGreaterThanOrEqualTo(String value) {
            addCriterion("SUGGEST_TITLE >=", value, "suggestTitle");
            return (Criteria) this;
        }

        public Criteria andSuggestTitleLessThan(String value) {
            addCriterion("SUGGEST_TITLE <", value, "suggestTitle");
            return (Criteria) this;
        }

        public Criteria andSuggestTitleLessThanOrEqualTo(String value) {
            addCriterion("SUGGEST_TITLE <=", value, "suggestTitle");
            return (Criteria) this;
        }

        public Criteria andSuggestTitleLike(String value) {
            addCriterion("SUGGEST_TITLE like", value, "suggestTitle");
            return (Criteria) this;
        }

        public Criteria andSuggestTitleNotLike(String value) {
            addCriterion("SUGGEST_TITLE not like", value, "suggestTitle");
            return (Criteria) this;
        }

        public Criteria andSuggestTitleIn(List<String> values) {
            addCriterion("SUGGEST_TITLE in", values, "suggestTitle");
            return (Criteria) this;
        }

        public Criteria andSuggestTitleNotIn(List<String> values) {
            addCriterion("SUGGEST_TITLE not in", values, "suggestTitle");
            return (Criteria) this;
        }

        public Criteria andSuggestTitleBetween(String value1, String value2) {
            addCriterion("SUGGEST_TITLE between", value1, value2, "suggestTitle");
            return (Criteria) this;
        }

        public Criteria andSuggestTitleNotBetween(String value1, String value2) {
            addCriterion("SUGGEST_TITLE not between", value1, value2, "suggestTitle");
            return (Criteria) this;
        }

        public Criteria andSuggestContentIsNull() {
            addCriterion("SUGGEST_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andSuggestContentIsNotNull() {
            addCriterion("SUGGEST_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andSuggestContentEqualTo(String value) {
            addCriterion("SUGGEST_CONTENT =", value, "suggestContent");
            return (Criteria) this;
        }

        public Criteria andSuggestContentNotEqualTo(String value) {
            addCriterion("SUGGEST_CONTENT <>", value, "suggestContent");
            return (Criteria) this;
        }

        public Criteria andSuggestContentGreaterThan(String value) {
            addCriterion("SUGGEST_CONTENT >", value, "suggestContent");
            return (Criteria) this;
        }

        public Criteria andSuggestContentGreaterThanOrEqualTo(String value) {
            addCriterion("SUGGEST_CONTENT >=", value, "suggestContent");
            return (Criteria) this;
        }

        public Criteria andSuggestContentLessThan(String value) {
            addCriterion("SUGGEST_CONTENT <", value, "suggestContent");
            return (Criteria) this;
        }

        public Criteria andSuggestContentLessThanOrEqualTo(String value) {
            addCriterion("SUGGEST_CONTENT <=", value, "suggestContent");
            return (Criteria) this;
        }

        public Criteria andSuggestContentLike(String value) {
            addCriterion("SUGGEST_CONTENT like", value, "suggestContent");
            return (Criteria) this;
        }

        public Criteria andSuggestContentNotLike(String value) {
            addCriterion("SUGGEST_CONTENT not like", value, "suggestContent");
            return (Criteria) this;
        }

        public Criteria andSuggestContentIn(List<String> values) {
            addCriterion("SUGGEST_CONTENT in", values, "suggestContent");
            return (Criteria) this;
        }

        public Criteria andSuggestContentNotIn(List<String> values) {
            addCriterion("SUGGEST_CONTENT not in", values, "suggestContent");
            return (Criteria) this;
        }

        public Criteria andSuggestContentBetween(String value1, String value2) {
            addCriterion("SUGGEST_CONTENT between", value1, value2, "suggestContent");
            return (Criteria) this;
        }

        public Criteria andSuggestContentNotBetween(String value1, String value2) {
            addCriterion("SUGGEST_CONTENT not between", value1, value2, "suggestContent");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("USER_NAME =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("USER_NAME <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("USER_NAME >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_NAME >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("USER_NAME <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("USER_NAME <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("USER_NAME like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("USER_NAME not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("USER_NAME in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("USER_NAME not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("USER_NAME between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("USER_NAME not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNull() {
            addCriterion("USER_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNotNull() {
            addCriterion("USER_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneEqualTo(String value) {
            addCriterion("USER_PHONE =", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotEqualTo(String value) {
            addCriterion("USER_PHONE <>", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThan(String value) {
            addCriterion("USER_PHONE >", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("USER_PHONE >=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThan(String value) {
            addCriterion("USER_PHONE <", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThanOrEqualTo(String value) {
            addCriterion("USER_PHONE <=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLike(String value) {
            addCriterion("USER_PHONE like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotLike(String value) {
            addCriterion("USER_PHONE not like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIn(List<String> values) {
            addCriterion("USER_PHONE in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotIn(List<String> values) {
            addCriterion("USER_PHONE not in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneBetween(String value1, String value2) {
            addCriterion("USER_PHONE between", value1, value2, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotBetween(String value1, String value2) {
            addCriterion("USER_PHONE not between", value1, value2, "userPhone");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNull() {
            addCriterion("SUBMIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNotNull() {
            addCriterion("SUBMIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeEqualTo(String value) {
            addCriterion("SUBMIT_TIME =", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotEqualTo(String value) {
            addCriterion("SUBMIT_TIME <>", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThan(String value) {
            addCriterion("SUBMIT_TIME >", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThanOrEqualTo(String value) {
            addCriterion("SUBMIT_TIME >=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThan(String value) {
            addCriterion("SUBMIT_TIME <", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThanOrEqualTo(String value) {
            addCriterion("SUBMIT_TIME <=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLike(String value) {
            addCriterion("SUBMIT_TIME like", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotLike(String value) {
            addCriterion("SUBMIT_TIME not like", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIn(List<String> values) {
            addCriterion("SUBMIT_TIME in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotIn(List<String> values) {
            addCriterion("SUBMIT_TIME not in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeBetween(String value1, String value2) {
            addCriterion("SUBMIT_TIME between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotBetween(String value1, String value2) {
            addCriterion("SUBMIT_TIME not between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andReplyUserNameIsNull() {
            addCriterion("REPLY_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andReplyUserNameIsNotNull() {
            addCriterion("REPLY_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andReplyUserNameEqualTo(String value) {
            addCriterion("REPLY_USER_NAME =", value, "replyUserName");
            return (Criteria) this;
        }

        public Criteria andReplyUserNameNotEqualTo(String value) {
            addCriterion("REPLY_USER_NAME <>", value, "replyUserName");
            return (Criteria) this;
        }

        public Criteria andReplyUserNameGreaterThan(String value) {
            addCriterion("REPLY_USER_NAME >", value, "replyUserName");
            return (Criteria) this;
        }

        public Criteria andReplyUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("REPLY_USER_NAME >=", value, "replyUserName");
            return (Criteria) this;
        }

        public Criteria andReplyUserNameLessThan(String value) {
            addCriterion("REPLY_USER_NAME <", value, "replyUserName");
            return (Criteria) this;
        }

        public Criteria andReplyUserNameLessThanOrEqualTo(String value) {
            addCriterion("REPLY_USER_NAME <=", value, "replyUserName");
            return (Criteria) this;
        }

        public Criteria andReplyUserNameLike(String value) {
            addCriterion("REPLY_USER_NAME like", value, "replyUserName");
            return (Criteria) this;
        }

        public Criteria andReplyUserNameNotLike(String value) {
            addCriterion("REPLY_USER_NAME not like", value, "replyUserName");
            return (Criteria) this;
        }

        public Criteria andReplyUserNameIn(List<String> values) {
            addCriterion("REPLY_USER_NAME in", values, "replyUserName");
            return (Criteria) this;
        }

        public Criteria andReplyUserNameNotIn(List<String> values) {
            addCriterion("REPLY_USER_NAME not in", values, "replyUserName");
            return (Criteria) this;
        }

        public Criteria andReplyUserNameBetween(String value1, String value2) {
            addCriterion("REPLY_USER_NAME between", value1, value2, "replyUserName");
            return (Criteria) this;
        }

        public Criteria andReplyUserNameNotBetween(String value1, String value2) {
            addCriterion("REPLY_USER_NAME not between", value1, value2, "replyUserName");
            return (Criteria) this;
        }

        public Criteria andReplyContentIsNull() {
            addCriterion("REPLY_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andReplyContentIsNotNull() {
            addCriterion("REPLY_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andReplyContentEqualTo(String value) {
            addCriterion("REPLY_CONTENT =", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentNotEqualTo(String value) {
            addCriterion("REPLY_CONTENT <>", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentGreaterThan(String value) {
            addCriterion("REPLY_CONTENT >", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentGreaterThanOrEqualTo(String value) {
            addCriterion("REPLY_CONTENT >=", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentLessThan(String value) {
            addCriterion("REPLY_CONTENT <", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentLessThanOrEqualTo(String value) {
            addCriterion("REPLY_CONTENT <=", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentLike(String value) {
            addCriterion("REPLY_CONTENT like", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentNotLike(String value) {
            addCriterion("REPLY_CONTENT not like", value, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentIn(List<String> values) {
            addCriterion("REPLY_CONTENT in", values, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentNotIn(List<String> values) {
            addCriterion("REPLY_CONTENT not in", values, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentBetween(String value1, String value2) {
            addCriterion("REPLY_CONTENT between", value1, value2, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyContentNotBetween(String value1, String value2) {
            addCriterion("REPLY_CONTENT not between", value1, value2, "replyContent");
            return (Criteria) this;
        }

        public Criteria andReplyTimeIsNull() {
            addCriterion("REPLY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andReplyTimeIsNotNull() {
            addCriterion("REPLY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andReplyTimeEqualTo(String value) {
            addCriterion("REPLY_TIME =", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeNotEqualTo(String value) {
            addCriterion("REPLY_TIME <>", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeGreaterThan(String value) {
            addCriterion("REPLY_TIME >", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeGreaterThanOrEqualTo(String value) {
            addCriterion("REPLY_TIME >=", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLessThan(String value) {
            addCriterion("REPLY_TIME <", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLessThanOrEqualTo(String value) {
            addCriterion("REPLY_TIME <=", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLike(String value) {
            addCriterion("REPLY_TIME like", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeNotLike(String value) {
            addCriterion("REPLY_TIME not like", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeIn(List<String> values) {
            addCriterion("REPLY_TIME in", values, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeNotIn(List<String> values) {
            addCriterion("REPLY_TIME not in", values, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeBetween(String value1, String value2) {
            addCriterion("REPLY_TIME between", value1, value2, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeNotBetween(String value1, String value2) {
            addCriterion("REPLY_TIME not between", value1, value2, "replyTime");
            return (Criteria) this;
        }

        public Criteria andShowFlagIsNull() {
            addCriterion("SHOW_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andShowFlagIsNotNull() {
            addCriterion("SHOW_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andShowFlagEqualTo(String value) {
            addCriterion("SHOW_FLAG =", value, "showFlag");
            return (Criteria) this;
        }

        public Criteria andShowFlagNotEqualTo(String value) {
            addCriterion("SHOW_FLAG <>", value, "showFlag");
            return (Criteria) this;
        }

        public Criteria andShowFlagGreaterThan(String value) {
            addCriterion("SHOW_FLAG >", value, "showFlag");
            return (Criteria) this;
        }

        public Criteria andShowFlagGreaterThanOrEqualTo(String value) {
            addCriterion("SHOW_FLAG >=", value, "showFlag");
            return (Criteria) this;
        }

        public Criteria andShowFlagLessThan(String value) {
            addCriterion("SHOW_FLAG <", value, "showFlag");
            return (Criteria) this;
        }

        public Criteria andShowFlagLessThanOrEqualTo(String value) {
            addCriterion("SHOW_FLAG <=", value, "showFlag");
            return (Criteria) this;
        }

        public Criteria andShowFlagLike(String value) {
            addCriterion("SHOW_FLAG like", value, "showFlag");
            return (Criteria) this;
        }

        public Criteria andShowFlagNotLike(String value) {
            addCriterion("SHOW_FLAG not like", value, "showFlag");
            return (Criteria) this;
        }

        public Criteria andShowFlagIn(List<String> values) {
            addCriterion("SHOW_FLAG in", values, "showFlag");
            return (Criteria) this;
        }

        public Criteria andShowFlagNotIn(List<String> values) {
            addCriterion("SHOW_FLAG not in", values, "showFlag");
            return (Criteria) this;
        }

        public Criteria andShowFlagBetween(String value1, String value2) {
            addCriterion("SHOW_FLAG between", value1, value2, "showFlag");
            return (Criteria) this;
        }

        public Criteria andShowFlagNotBetween(String value1, String value2) {
            addCriterion("SHOW_FLAG not between", value1, value2, "showFlag");
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