package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class FeeNoticeTemplateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FeeNoticeTemplateExample() {
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

        public Criteria andReceiverFlagIsNull() {
            addCriterion("RECEIVER_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andReceiverFlagIsNotNull() {
            addCriterion("RECEIVER_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverFlagEqualTo(String value) {
            addCriterion("RECEIVER_FLAG =", value, "receiverFlag");
            return (Criteria) this;
        }

        public Criteria andReceiverFlagNotEqualTo(String value) {
            addCriterion("RECEIVER_FLAG <>", value, "receiverFlag");
            return (Criteria) this;
        }

        public Criteria andReceiverFlagGreaterThan(String value) {
            addCriterion("RECEIVER_FLAG >", value, "receiverFlag");
            return (Criteria) this;
        }

        public Criteria andReceiverFlagGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVER_FLAG >=", value, "receiverFlag");
            return (Criteria) this;
        }

        public Criteria andReceiverFlagLessThan(String value) {
            addCriterion("RECEIVER_FLAG <", value, "receiverFlag");
            return (Criteria) this;
        }

        public Criteria andReceiverFlagLessThanOrEqualTo(String value) {
            addCriterion("RECEIVER_FLAG <=", value, "receiverFlag");
            return (Criteria) this;
        }

        public Criteria andReceiverFlagLike(String value) {
            addCriterion("RECEIVER_FLAG like", value, "receiverFlag");
            return (Criteria) this;
        }

        public Criteria andReceiverFlagNotLike(String value) {
            addCriterion("RECEIVER_FLAG not like", value, "receiverFlag");
            return (Criteria) this;
        }

        public Criteria andReceiverFlagIn(List<String> values) {
            addCriterion("RECEIVER_FLAG in", values, "receiverFlag");
            return (Criteria) this;
        }

        public Criteria andReceiverFlagNotIn(List<String> values) {
            addCriterion("RECEIVER_FLAG not in", values, "receiverFlag");
            return (Criteria) this;
        }

        public Criteria andReceiverFlagBetween(String value1, String value2) {
            addCriterion("RECEIVER_FLAG between", value1, value2, "receiverFlag");
            return (Criteria) this;
        }

        public Criteria andReceiverFlagNotBetween(String value1, String value2) {
            addCriterion("RECEIVER_FLAG not between", value1, value2, "receiverFlag");
            return (Criteria) this;
        }

        public Criteria andPublisherNameIsNull() {
            addCriterion("PUBLISHER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPublisherNameIsNotNull() {
            addCriterion("PUBLISHER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPublisherNameEqualTo(String value) {
            addCriterion("PUBLISHER_NAME =", value, "publisherName");
            return (Criteria) this;
        }

        public Criteria andPublisherNameNotEqualTo(String value) {
            addCriterion("PUBLISHER_NAME <>", value, "publisherName");
            return (Criteria) this;
        }

        public Criteria andPublisherNameGreaterThan(String value) {
            addCriterion("PUBLISHER_NAME >", value, "publisherName");
            return (Criteria) this;
        }

        public Criteria andPublisherNameGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLISHER_NAME >=", value, "publisherName");
            return (Criteria) this;
        }

        public Criteria andPublisherNameLessThan(String value) {
            addCriterion("PUBLISHER_NAME <", value, "publisherName");
            return (Criteria) this;
        }

        public Criteria andPublisherNameLessThanOrEqualTo(String value) {
            addCriterion("PUBLISHER_NAME <=", value, "publisherName");
            return (Criteria) this;
        }

        public Criteria andPublisherNameLike(String value) {
            addCriterion("PUBLISHER_NAME like", value, "publisherName");
            return (Criteria) this;
        }

        public Criteria andPublisherNameNotLike(String value) {
            addCriterion("PUBLISHER_NAME not like", value, "publisherName");
            return (Criteria) this;
        }

        public Criteria andPublisherNameIn(List<String> values) {
            addCriterion("PUBLISHER_NAME in", values, "publisherName");
            return (Criteria) this;
        }

        public Criteria andPublisherNameNotIn(List<String> values) {
            addCriterion("PUBLISHER_NAME not in", values, "publisherName");
            return (Criteria) this;
        }

        public Criteria andPublisherNameBetween(String value1, String value2) {
            addCriterion("PUBLISHER_NAME between", value1, value2, "publisherName");
            return (Criteria) this;
        }

        public Criteria andPublisherNameNotBetween(String value1, String value2) {
            addCriterion("PUBLISHER_NAME not between", value1, value2, "publisherName");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIsNull() {
            addCriterion("PUBLISH_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIsNotNull() {
            addCriterion("PUBLISH_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPublishTimeEqualTo(String value) {
            addCriterion("PUBLISH_TIME =", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotEqualTo(String value) {
            addCriterion("PUBLISH_TIME <>", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeGreaterThan(String value) {
            addCriterion("PUBLISH_TIME >", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLISH_TIME >=", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeLessThan(String value) {
            addCriterion("PUBLISH_TIME <", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeLessThanOrEqualTo(String value) {
            addCriterion("PUBLISH_TIME <=", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeLike(String value) {
            addCriterion("PUBLISH_TIME like", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotLike(String value) {
            addCriterion("PUBLISH_TIME not like", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIn(List<String> values) {
            addCriterion("PUBLISH_TIME in", values, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotIn(List<String> values) {
            addCriterion("PUBLISH_TIME not in", values, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeBetween(String value1, String value2) {
            addCriterion("PUBLISH_TIME between", value1, value2, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotBetween(String value1, String value2) {
            addCriterion("PUBLISH_TIME not between", value1, value2, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishSwitchIsNull() {
            addCriterion("PUBLISH_SWITCH is null");
            return (Criteria) this;
        }

        public Criteria andPublishSwitchIsNotNull() {
            addCriterion("PUBLISH_SWITCH is not null");
            return (Criteria) this;
        }

        public Criteria andPublishSwitchEqualTo(String value) {
            addCriterion("PUBLISH_SWITCH =", value, "publishSwitch");
            return (Criteria) this;
        }

        public Criteria andPublishSwitchNotEqualTo(String value) {
            addCriterion("PUBLISH_SWITCH <>", value, "publishSwitch");
            return (Criteria) this;
        }

        public Criteria andPublishSwitchGreaterThan(String value) {
            addCriterion("PUBLISH_SWITCH >", value, "publishSwitch");
            return (Criteria) this;
        }

        public Criteria andPublishSwitchGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLISH_SWITCH >=", value, "publishSwitch");
            return (Criteria) this;
        }

        public Criteria andPublishSwitchLessThan(String value) {
            addCriterion("PUBLISH_SWITCH <", value, "publishSwitch");
            return (Criteria) this;
        }

        public Criteria andPublishSwitchLessThanOrEqualTo(String value) {
            addCriterion("PUBLISH_SWITCH <=", value, "publishSwitch");
            return (Criteria) this;
        }

        public Criteria andPublishSwitchLike(String value) {
            addCriterion("PUBLISH_SWITCH like", value, "publishSwitch");
            return (Criteria) this;
        }

        public Criteria andPublishSwitchNotLike(String value) {
            addCriterion("PUBLISH_SWITCH not like", value, "publishSwitch");
            return (Criteria) this;
        }

        public Criteria andPublishSwitchIn(List<String> values) {
            addCriterion("PUBLISH_SWITCH in", values, "publishSwitch");
            return (Criteria) this;
        }

        public Criteria andPublishSwitchNotIn(List<String> values) {
            addCriterion("PUBLISH_SWITCH not in", values, "publishSwitch");
            return (Criteria) this;
        }

        public Criteria andPublishSwitchBetween(String value1, String value2) {
            addCriterion("PUBLISH_SWITCH between", value1, value2, "publishSwitch");
            return (Criteria) this;
        }

        public Criteria andPublishSwitchNotBetween(String value1, String value2) {
            addCriterion("PUBLISH_SWITCH not between", value1, value2, "publishSwitch");
            return (Criteria) this;
        }

        public Criteria andTitlePositionIsNull() {
            addCriterion("TITLE_POSITION is null");
            return (Criteria) this;
        }

        public Criteria andTitlePositionIsNotNull() {
            addCriterion("TITLE_POSITION is not null");
            return (Criteria) this;
        }

        public Criteria andTitlePositionEqualTo(String value) {
            addCriterion("TITLE_POSITION =", value, "titlePosition");
            return (Criteria) this;
        }

        public Criteria andTitlePositionNotEqualTo(String value) {
            addCriterion("TITLE_POSITION <>", value, "titlePosition");
            return (Criteria) this;
        }

        public Criteria andTitlePositionGreaterThan(String value) {
            addCriterion("TITLE_POSITION >", value, "titlePosition");
            return (Criteria) this;
        }

        public Criteria andTitlePositionGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE_POSITION >=", value, "titlePosition");
            return (Criteria) this;
        }

        public Criteria andTitlePositionLessThan(String value) {
            addCriterion("TITLE_POSITION <", value, "titlePosition");
            return (Criteria) this;
        }

        public Criteria andTitlePositionLessThanOrEqualTo(String value) {
            addCriterion("TITLE_POSITION <=", value, "titlePosition");
            return (Criteria) this;
        }

        public Criteria andTitlePositionLike(String value) {
            addCriterion("TITLE_POSITION like", value, "titlePosition");
            return (Criteria) this;
        }

        public Criteria andTitlePositionNotLike(String value) {
            addCriterion("TITLE_POSITION not like", value, "titlePosition");
            return (Criteria) this;
        }

        public Criteria andTitlePositionIn(List<String> values) {
            addCriterion("TITLE_POSITION in", values, "titlePosition");
            return (Criteria) this;
        }

        public Criteria andTitlePositionNotIn(List<String> values) {
            addCriterion("TITLE_POSITION not in", values, "titlePosition");
            return (Criteria) this;
        }

        public Criteria andTitlePositionBetween(String value1, String value2) {
            addCriterion("TITLE_POSITION between", value1, value2, "titlePosition");
            return (Criteria) this;
        }

        public Criteria andTitlePositionNotBetween(String value1, String value2) {
            addCriterion("TITLE_POSITION not between", value1, value2, "titlePosition");
            return (Criteria) this;
        }

        public Criteria andTitleNameIsNull() {
            addCriterion("TITLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTitleNameIsNotNull() {
            addCriterion("TITLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTitleNameEqualTo(String value) {
            addCriterion("TITLE_NAME =", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameNotEqualTo(String value) {
            addCriterion("TITLE_NAME <>", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameGreaterThan(String value) {
            addCriterion("TITLE_NAME >", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE_NAME >=", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameLessThan(String value) {
            addCriterion("TITLE_NAME <", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameLessThanOrEqualTo(String value) {
            addCriterion("TITLE_NAME <=", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameLike(String value) {
            addCriterion("TITLE_NAME like", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameNotLike(String value) {
            addCriterion("TITLE_NAME not like", value, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameIn(List<String> values) {
            addCriterion("TITLE_NAME in", values, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameNotIn(List<String> values) {
            addCriterion("TITLE_NAME not in", values, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameBetween(String value1, String value2) {
            addCriterion("TITLE_NAME between", value1, value2, "titleName");
            return (Criteria) this;
        }

        public Criteria andTitleNameNotBetween(String value1, String value2) {
            addCriterion("TITLE_NAME not between", value1, value2, "titleName");
            return (Criteria) this;
        }

        public Criteria andSkinColorIsNull() {
            addCriterion("SKIN_COLOR is null");
            return (Criteria) this;
        }

        public Criteria andSkinColorIsNotNull() {
            addCriterion("SKIN_COLOR is not null");
            return (Criteria) this;
        }

        public Criteria andSkinColorEqualTo(String value) {
            addCriterion("SKIN_COLOR =", value, "skinColor");
            return (Criteria) this;
        }

        public Criteria andSkinColorNotEqualTo(String value) {
            addCriterion("SKIN_COLOR <>", value, "skinColor");
            return (Criteria) this;
        }

        public Criteria andSkinColorGreaterThan(String value) {
            addCriterion("SKIN_COLOR >", value, "skinColor");
            return (Criteria) this;
        }

        public Criteria andSkinColorGreaterThanOrEqualTo(String value) {
            addCriterion("SKIN_COLOR >=", value, "skinColor");
            return (Criteria) this;
        }

        public Criteria andSkinColorLessThan(String value) {
            addCriterion("SKIN_COLOR <", value, "skinColor");
            return (Criteria) this;
        }

        public Criteria andSkinColorLessThanOrEqualTo(String value) {
            addCriterion("SKIN_COLOR <=", value, "skinColor");
            return (Criteria) this;
        }

        public Criteria andSkinColorLike(String value) {
            addCriterion("SKIN_COLOR like", value, "skinColor");
            return (Criteria) this;
        }

        public Criteria andSkinColorNotLike(String value) {
            addCriterion("SKIN_COLOR not like", value, "skinColor");
            return (Criteria) this;
        }

        public Criteria andSkinColorIn(List<String> values) {
            addCriterion("SKIN_COLOR in", values, "skinColor");
            return (Criteria) this;
        }

        public Criteria andSkinColorNotIn(List<String> values) {
            addCriterion("SKIN_COLOR not in", values, "skinColor");
            return (Criteria) this;
        }

        public Criteria andSkinColorBetween(String value1, String value2) {
            addCriterion("SKIN_COLOR between", value1, value2, "skinColor");
            return (Criteria) this;
        }

        public Criteria andSkinColorNotBetween(String value1, String value2) {
            addCriterion("SKIN_COLOR not between", value1, value2, "skinColor");
            return (Criteria) this;
        }

        public Criteria andFontColorIsNull() {
            addCriterion("FONT_COLOR is null");
            return (Criteria) this;
        }

        public Criteria andFontColorIsNotNull() {
            addCriterion("FONT_COLOR is not null");
            return (Criteria) this;
        }

        public Criteria andFontColorEqualTo(String value) {
            addCriterion("FONT_COLOR =", value, "fontColor");
            return (Criteria) this;
        }

        public Criteria andFontColorNotEqualTo(String value) {
            addCriterion("FONT_COLOR <>", value, "fontColor");
            return (Criteria) this;
        }

        public Criteria andFontColorGreaterThan(String value) {
            addCriterion("FONT_COLOR >", value, "fontColor");
            return (Criteria) this;
        }

        public Criteria andFontColorGreaterThanOrEqualTo(String value) {
            addCriterion("FONT_COLOR >=", value, "fontColor");
            return (Criteria) this;
        }

        public Criteria andFontColorLessThan(String value) {
            addCriterion("FONT_COLOR <", value, "fontColor");
            return (Criteria) this;
        }

        public Criteria andFontColorLessThanOrEqualTo(String value) {
            addCriterion("FONT_COLOR <=", value, "fontColor");
            return (Criteria) this;
        }

        public Criteria andFontColorLike(String value) {
            addCriterion("FONT_COLOR like", value, "fontColor");
            return (Criteria) this;
        }

        public Criteria andFontColorNotLike(String value) {
            addCriterion("FONT_COLOR not like", value, "fontColor");
            return (Criteria) this;
        }

        public Criteria andFontColorIn(List<String> values) {
            addCriterion("FONT_COLOR in", values, "fontColor");
            return (Criteria) this;
        }

        public Criteria andFontColorNotIn(List<String> values) {
            addCriterion("FONT_COLOR not in", values, "fontColor");
            return (Criteria) this;
        }

        public Criteria andFontColorBetween(String value1, String value2) {
            addCriterion("FONT_COLOR between", value1, value2, "fontColor");
            return (Criteria) this;
        }

        public Criteria andFontColorNotBetween(String value1, String value2) {
            addCriterion("FONT_COLOR not between", value1, value2, "fontColor");
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