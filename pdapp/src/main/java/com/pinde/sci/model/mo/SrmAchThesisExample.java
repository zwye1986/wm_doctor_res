package com.pinde.sci.model.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SrmAchThesisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SrmAchThesisExample() {
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

        public Criteria andThesisFlowIsNull() {
            addCriterion("THESIS_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andThesisFlowIsNotNull() {
            addCriterion("THESIS_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andThesisFlowEqualTo(String value) {
            addCriterion("THESIS_FLOW =", value, "thesisFlow");
            return (Criteria) this;
        }

        public Criteria andThesisFlowNotEqualTo(String value) {
            addCriterion("THESIS_FLOW <>", value, "thesisFlow");
            return (Criteria) this;
        }

        public Criteria andThesisFlowGreaterThan(String value) {
            addCriterion("THESIS_FLOW >", value, "thesisFlow");
            return (Criteria) this;
        }

        public Criteria andThesisFlowGreaterThanOrEqualTo(String value) {
            addCriterion("THESIS_FLOW >=", value, "thesisFlow");
            return (Criteria) this;
        }

        public Criteria andThesisFlowLessThan(String value) {
            addCriterion("THESIS_FLOW <", value, "thesisFlow");
            return (Criteria) this;
        }

        public Criteria andThesisFlowLessThanOrEqualTo(String value) {
            addCriterion("THESIS_FLOW <=", value, "thesisFlow");
            return (Criteria) this;
        }

        public Criteria andThesisFlowLike(String value) {
            addCriterion("THESIS_FLOW like", value, "thesisFlow");
            return (Criteria) this;
        }

        public Criteria andThesisFlowNotLike(String value) {
            addCriterion("THESIS_FLOW not like", value, "thesisFlow");
            return (Criteria) this;
        }

        public Criteria andThesisFlowIn(List<String> values) {
            addCriterion("THESIS_FLOW in", values, "thesisFlow");
            return (Criteria) this;
        }

        public Criteria andThesisFlowNotIn(List<String> values) {
            addCriterion("THESIS_FLOW not in", values, "thesisFlow");
            return (Criteria) this;
        }

        public Criteria andThesisFlowBetween(String value1, String value2) {
            addCriterion("THESIS_FLOW between", value1, value2, "thesisFlow");
            return (Criteria) this;
        }

        public Criteria andThesisFlowNotBetween(String value1, String value2) {
            addCriterion("THESIS_FLOW not between", value1, value2, "thesisFlow");
            return (Criteria) this;
        }

        public Criteria andThesisNameIsNull() {
            addCriterion("THESIS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andThesisNameIsNotNull() {
            addCriterion("THESIS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andThesisNameEqualTo(String value) {
            addCriterion("THESIS_NAME =", value, "thesisName");
            return (Criteria) this;
        }

        public Criteria andThesisNameNotEqualTo(String value) {
            addCriterion("THESIS_NAME <>", value, "thesisName");
            return (Criteria) this;
        }

        public Criteria andThesisNameGreaterThan(String value) {
            addCriterion("THESIS_NAME >", value, "thesisName");
            return (Criteria) this;
        }

        public Criteria andThesisNameGreaterThanOrEqualTo(String value) {
            addCriterion("THESIS_NAME >=", value, "thesisName");
            return (Criteria) this;
        }

        public Criteria andThesisNameLessThan(String value) {
            addCriterion("THESIS_NAME <", value, "thesisName");
            return (Criteria) this;
        }

        public Criteria andThesisNameLessThanOrEqualTo(String value) {
            addCriterion("THESIS_NAME <=", value, "thesisName");
            return (Criteria) this;
        }

        public Criteria andThesisNameLike(String value) {
            addCriterion("THESIS_NAME like", value, "thesisName");
            return (Criteria) this;
        }

        public Criteria andThesisNameNotLike(String value) {
            addCriterion("THESIS_NAME not like", value, "thesisName");
            return (Criteria) this;
        }

        public Criteria andThesisNameIn(List<String> values) {
            addCriterion("THESIS_NAME in", values, "thesisName");
            return (Criteria) this;
        }

        public Criteria andThesisNameNotIn(List<String> values) {
            addCriterion("THESIS_NAME not in", values, "thesisName");
            return (Criteria) this;
        }

        public Criteria andThesisNameBetween(String value1, String value2) {
            addCriterion("THESIS_NAME between", value1, value2, "thesisName");
            return (Criteria) this;
        }

        public Criteria andThesisNameNotBetween(String value1, String value2) {
            addCriterion("THESIS_NAME not between", value1, value2, "thesisName");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNull() {
            addCriterion("TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(String value) {
            addCriterion("TYPE_ID =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(String value) {
            addCriterion("TYPE_ID <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(String value) {
            addCriterion("TYPE_ID >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE_ID >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(String value) {
            addCriterion("TYPE_ID <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(String value) {
            addCriterion("TYPE_ID <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLike(String value) {
            addCriterion("TYPE_ID like", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotLike(String value) {
            addCriterion("TYPE_ID not like", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<String> values) {
            addCriterion("TYPE_ID in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<String> values) {
            addCriterion("TYPE_ID not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(String value1, String value2) {
            addCriterion("TYPE_ID between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(String value1, String value2) {
            addCriterion("TYPE_ID not between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNull() {
            addCriterion("TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNotNull() {
            addCriterion("TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTypeNameEqualTo(String value) {
            addCriterion("TYPE_NAME =", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotEqualTo(String value) {
            addCriterion("TYPE_NAME <>", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThan(String value) {
            addCriterion("TYPE_NAME >", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE_NAME >=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThan(String value) {
            addCriterion("TYPE_NAME <", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThanOrEqualTo(String value) {
            addCriterion("TYPE_NAME <=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLike(String value) {
            addCriterion("TYPE_NAME like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotLike(String value) {
            addCriterion("TYPE_NAME not like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameIn(List<String> values) {
            addCriterion("TYPE_NAME in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotIn(List<String> values) {
            addCriterion("TYPE_NAME not in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameBetween(String value1, String value2) {
            addCriterion("TYPE_NAME between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotBetween(String value1, String value2) {
            addCriterion("TYPE_NAME not between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andMeetingIdIsNull() {
            addCriterion("MEETING_ID is null");
            return (Criteria) this;
        }

        public Criteria andMeetingIdIsNotNull() {
            addCriterion("MEETING_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMeetingIdEqualTo(String value) {
            addCriterion("MEETING_ID =", value, "meetingId");
            return (Criteria) this;
        }

        public Criteria andMeetingIdNotEqualTo(String value) {
            addCriterion("MEETING_ID <>", value, "meetingId");
            return (Criteria) this;
        }

        public Criteria andMeetingIdGreaterThan(String value) {
            addCriterion("MEETING_ID >", value, "meetingId");
            return (Criteria) this;
        }

        public Criteria andMeetingIdGreaterThanOrEqualTo(String value) {
            addCriterion("MEETING_ID >=", value, "meetingId");
            return (Criteria) this;
        }

        public Criteria andMeetingIdLessThan(String value) {
            addCriterion("MEETING_ID <", value, "meetingId");
            return (Criteria) this;
        }

        public Criteria andMeetingIdLessThanOrEqualTo(String value) {
            addCriterion("MEETING_ID <=", value, "meetingId");
            return (Criteria) this;
        }

        public Criteria andMeetingIdLike(String value) {
            addCriterion("MEETING_ID like", value, "meetingId");
            return (Criteria) this;
        }

        public Criteria andMeetingIdNotLike(String value) {
            addCriterion("MEETING_ID not like", value, "meetingId");
            return (Criteria) this;
        }

        public Criteria andMeetingIdIn(List<String> values) {
            addCriterion("MEETING_ID in", values, "meetingId");
            return (Criteria) this;
        }

        public Criteria andMeetingIdNotIn(List<String> values) {
            addCriterion("MEETING_ID not in", values, "meetingId");
            return (Criteria) this;
        }

        public Criteria andMeetingIdBetween(String value1, String value2) {
            addCriterion("MEETING_ID between", value1, value2, "meetingId");
            return (Criteria) this;
        }

        public Criteria andMeetingIdNotBetween(String value1, String value2) {
            addCriterion("MEETING_ID not between", value1, value2, "meetingId");
            return (Criteria) this;
        }

        public Criteria andMeetingNameIsNull() {
            addCriterion("MEETING_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMeetingNameIsNotNull() {
            addCriterion("MEETING_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMeetingNameEqualTo(String value) {
            addCriterion("MEETING_NAME =", value, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameNotEqualTo(String value) {
            addCriterion("MEETING_NAME <>", value, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameGreaterThan(String value) {
            addCriterion("MEETING_NAME >", value, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameGreaterThanOrEqualTo(String value) {
            addCriterion("MEETING_NAME >=", value, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameLessThan(String value) {
            addCriterion("MEETING_NAME <", value, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameLessThanOrEqualTo(String value) {
            addCriterion("MEETING_NAME <=", value, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameLike(String value) {
            addCriterion("MEETING_NAME like", value, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameNotLike(String value) {
            addCriterion("MEETING_NAME not like", value, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameIn(List<String> values) {
            addCriterion("MEETING_NAME in", values, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameNotIn(List<String> values) {
            addCriterion("MEETING_NAME not in", values, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameBetween(String value1, String value2) {
            addCriterion("MEETING_NAME between", value1, value2, "meetingName");
            return (Criteria) this;
        }

        public Criteria andMeetingNameNotBetween(String value1, String value2) {
            addCriterion("MEETING_NAME not between", value1, value2, "meetingName");
            return (Criteria) this;
        }

        public Criteria andPublishDateIsNull() {
            addCriterion("PUBLISH_DATE is null");
            return (Criteria) this;
        }

        public Criteria andPublishDateIsNotNull() {
            addCriterion("PUBLISH_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andPublishDateEqualTo(String value) {
            addCriterion("PUBLISH_DATE =", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateNotEqualTo(String value) {
            addCriterion("PUBLISH_DATE <>", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateGreaterThan(String value) {
            addCriterion("PUBLISH_DATE >", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLISH_DATE >=", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateLessThan(String value) {
            addCriterion("PUBLISH_DATE <", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateLessThanOrEqualTo(String value) {
            addCriterion("PUBLISH_DATE <=", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateLike(String value) {
            addCriterion("PUBLISH_DATE like", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateNotLike(String value) {
            addCriterion("PUBLISH_DATE not like", value, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateIn(List<String> values) {
            addCriterion("PUBLISH_DATE in", values, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateNotIn(List<String> values) {
            addCriterion("PUBLISH_DATE not in", values, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateBetween(String value1, String value2) {
            addCriterion("PUBLISH_DATE between", value1, value2, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishDateNotBetween(String value1, String value2) {
            addCriterion("PUBLISH_DATE not between", value1, value2, "publishDate");
            return (Criteria) this;
        }

        public Criteria andPublishJourIsNull() {
            addCriterion("PUBLISH_JOUR is null");
            return (Criteria) this;
        }

        public Criteria andPublishJourIsNotNull() {
            addCriterion("PUBLISH_JOUR is not null");
            return (Criteria) this;
        }

        public Criteria andPublishJourEqualTo(String value) {
            addCriterion("PUBLISH_JOUR =", value, "publishJour");
            return (Criteria) this;
        }

        public Criteria andPublishJourNotEqualTo(String value) {
            addCriterion("PUBLISH_JOUR <>", value, "publishJour");
            return (Criteria) this;
        }

        public Criteria andPublishJourGreaterThan(String value) {
            addCriterion("PUBLISH_JOUR >", value, "publishJour");
            return (Criteria) this;
        }

        public Criteria andPublishJourGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLISH_JOUR >=", value, "publishJour");
            return (Criteria) this;
        }

        public Criteria andPublishJourLessThan(String value) {
            addCriterion("PUBLISH_JOUR <", value, "publishJour");
            return (Criteria) this;
        }

        public Criteria andPublishJourLessThanOrEqualTo(String value) {
            addCriterion("PUBLISH_JOUR <=", value, "publishJour");
            return (Criteria) this;
        }

        public Criteria andPublishJourLike(String value) {
            addCriterion("PUBLISH_JOUR like", value, "publishJour");
            return (Criteria) this;
        }

        public Criteria andPublishJourNotLike(String value) {
            addCriterion("PUBLISH_JOUR not like", value, "publishJour");
            return (Criteria) this;
        }

        public Criteria andPublishJourIn(List<String> values) {
            addCriterion("PUBLISH_JOUR in", values, "publishJour");
            return (Criteria) this;
        }

        public Criteria andPublishJourNotIn(List<String> values) {
            addCriterion("PUBLISH_JOUR not in", values, "publishJour");
            return (Criteria) this;
        }

        public Criteria andPublishJourBetween(String value1, String value2) {
            addCriterion("PUBLISH_JOUR between", value1, value2, "publishJour");
            return (Criteria) this;
        }

        public Criteria andPublishJourNotBetween(String value1, String value2) {
            addCriterion("PUBLISH_JOUR not between", value1, value2, "publishJour");
            return (Criteria) this;
        }

        public Criteria andJourTypeIdIsNull() {
            addCriterion("JOUR_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andJourTypeIdIsNotNull() {
            addCriterion("JOUR_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andJourTypeIdEqualTo(String value) {
            addCriterion("JOUR_TYPE_ID =", value, "jourTypeId");
            return (Criteria) this;
        }

        public Criteria andJourTypeIdNotEqualTo(String value) {
            addCriterion("JOUR_TYPE_ID <>", value, "jourTypeId");
            return (Criteria) this;
        }

        public Criteria andJourTypeIdGreaterThan(String value) {
            addCriterion("JOUR_TYPE_ID >", value, "jourTypeId");
            return (Criteria) this;
        }

        public Criteria andJourTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("JOUR_TYPE_ID >=", value, "jourTypeId");
            return (Criteria) this;
        }

        public Criteria andJourTypeIdLessThan(String value) {
            addCriterion("JOUR_TYPE_ID <", value, "jourTypeId");
            return (Criteria) this;
        }

        public Criteria andJourTypeIdLessThanOrEqualTo(String value) {
            addCriterion("JOUR_TYPE_ID <=", value, "jourTypeId");
            return (Criteria) this;
        }

        public Criteria andJourTypeIdLike(String value) {
            addCriterion("JOUR_TYPE_ID like", value, "jourTypeId");
            return (Criteria) this;
        }

        public Criteria andJourTypeIdNotLike(String value) {
            addCriterion("JOUR_TYPE_ID not like", value, "jourTypeId");
            return (Criteria) this;
        }

        public Criteria andJourTypeIdIn(List<String> values) {
            addCriterion("JOUR_TYPE_ID in", values, "jourTypeId");
            return (Criteria) this;
        }

        public Criteria andJourTypeIdNotIn(List<String> values) {
            addCriterion("JOUR_TYPE_ID not in", values, "jourTypeId");
            return (Criteria) this;
        }

        public Criteria andJourTypeIdBetween(String value1, String value2) {
            addCriterion("JOUR_TYPE_ID between", value1, value2, "jourTypeId");
            return (Criteria) this;
        }

        public Criteria andJourTypeIdNotBetween(String value1, String value2) {
            addCriterion("JOUR_TYPE_ID not between", value1, value2, "jourTypeId");
            return (Criteria) this;
        }

        public Criteria andJourTypeNameIsNull() {
            addCriterion("JOUR_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andJourTypeNameIsNotNull() {
            addCriterion("JOUR_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andJourTypeNameEqualTo(String value) {
            addCriterion("JOUR_TYPE_NAME =", value, "jourTypeName");
            return (Criteria) this;
        }

        public Criteria andJourTypeNameNotEqualTo(String value) {
            addCriterion("JOUR_TYPE_NAME <>", value, "jourTypeName");
            return (Criteria) this;
        }

        public Criteria andJourTypeNameGreaterThan(String value) {
            addCriterion("JOUR_TYPE_NAME >", value, "jourTypeName");
            return (Criteria) this;
        }

        public Criteria andJourTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("JOUR_TYPE_NAME >=", value, "jourTypeName");
            return (Criteria) this;
        }

        public Criteria andJourTypeNameLessThan(String value) {
            addCriterion("JOUR_TYPE_NAME <", value, "jourTypeName");
            return (Criteria) this;
        }

        public Criteria andJourTypeNameLessThanOrEqualTo(String value) {
            addCriterion("JOUR_TYPE_NAME <=", value, "jourTypeName");
            return (Criteria) this;
        }

        public Criteria andJourTypeNameLike(String value) {
            addCriterion("JOUR_TYPE_NAME like", value, "jourTypeName");
            return (Criteria) this;
        }

        public Criteria andJourTypeNameNotLike(String value) {
            addCriterion("JOUR_TYPE_NAME not like", value, "jourTypeName");
            return (Criteria) this;
        }

        public Criteria andJourTypeNameIn(List<String> values) {
            addCriterion("JOUR_TYPE_NAME in", values, "jourTypeName");
            return (Criteria) this;
        }

        public Criteria andJourTypeNameNotIn(List<String> values) {
            addCriterion("JOUR_TYPE_NAME not in", values, "jourTypeName");
            return (Criteria) this;
        }

        public Criteria andJourTypeNameBetween(String value1, String value2) {
            addCriterion("JOUR_TYPE_NAME between", value1, value2, "jourTypeName");
            return (Criteria) this;
        }

        public Criteria andJourTypeNameNotBetween(String value1, String value2) {
            addCriterion("JOUR_TYPE_NAME not between", value1, value2, "jourTypeName");
            return (Criteria) this;
        }

        public Criteria andIsCoreJourIsNull() {
            addCriterion("IS_CORE_JOUR is null");
            return (Criteria) this;
        }

        public Criteria andIsCoreJourIsNotNull() {
            addCriterion("IS_CORE_JOUR is not null");
            return (Criteria) this;
        }

        public Criteria andIsCoreJourEqualTo(String value) {
            addCriterion("IS_CORE_JOUR =", value, "isCoreJour");
            return (Criteria) this;
        }

        public Criteria andIsCoreJourNotEqualTo(String value) {
            addCriterion("IS_CORE_JOUR <>", value, "isCoreJour");
            return (Criteria) this;
        }

        public Criteria andIsCoreJourGreaterThan(String value) {
            addCriterion("IS_CORE_JOUR >", value, "isCoreJour");
            return (Criteria) this;
        }

        public Criteria andIsCoreJourGreaterThanOrEqualTo(String value) {
            addCriterion("IS_CORE_JOUR >=", value, "isCoreJour");
            return (Criteria) this;
        }

        public Criteria andIsCoreJourLessThan(String value) {
            addCriterion("IS_CORE_JOUR <", value, "isCoreJour");
            return (Criteria) this;
        }

        public Criteria andIsCoreJourLessThanOrEqualTo(String value) {
            addCriterion("IS_CORE_JOUR <=", value, "isCoreJour");
            return (Criteria) this;
        }

        public Criteria andIsCoreJourLike(String value) {
            addCriterion("IS_CORE_JOUR like", value, "isCoreJour");
            return (Criteria) this;
        }

        public Criteria andIsCoreJourNotLike(String value) {
            addCriterion("IS_CORE_JOUR not like", value, "isCoreJour");
            return (Criteria) this;
        }

        public Criteria andIsCoreJourIn(List<String> values) {
            addCriterion("IS_CORE_JOUR in", values, "isCoreJour");
            return (Criteria) this;
        }

        public Criteria andIsCoreJourNotIn(List<String> values) {
            addCriterion("IS_CORE_JOUR not in", values, "isCoreJour");
            return (Criteria) this;
        }

        public Criteria andIsCoreJourBetween(String value1, String value2) {
            addCriterion("IS_CORE_JOUR between", value1, value2, "isCoreJour");
            return (Criteria) this;
        }

        public Criteria andIsCoreJourNotBetween(String value1, String value2) {
            addCriterion("IS_CORE_JOUR not between", value1, value2, "isCoreJour");
            return (Criteria) this;
        }

        public Criteria andJourCodeIsNull() {
            addCriterion("JOUR_CODE is null");
            return (Criteria) this;
        }

        public Criteria andJourCodeIsNotNull() {
            addCriterion("JOUR_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andJourCodeEqualTo(String value) {
            addCriterion("JOUR_CODE =", value, "jourCode");
            return (Criteria) this;
        }

        public Criteria andJourCodeNotEqualTo(String value) {
            addCriterion("JOUR_CODE <>", value, "jourCode");
            return (Criteria) this;
        }

        public Criteria andJourCodeGreaterThan(String value) {
            addCriterion("JOUR_CODE >", value, "jourCode");
            return (Criteria) this;
        }

        public Criteria andJourCodeGreaterThanOrEqualTo(String value) {
            addCriterion("JOUR_CODE >=", value, "jourCode");
            return (Criteria) this;
        }

        public Criteria andJourCodeLessThan(String value) {
            addCriterion("JOUR_CODE <", value, "jourCode");
            return (Criteria) this;
        }

        public Criteria andJourCodeLessThanOrEqualTo(String value) {
            addCriterion("JOUR_CODE <=", value, "jourCode");
            return (Criteria) this;
        }

        public Criteria andJourCodeLike(String value) {
            addCriterion("JOUR_CODE like", value, "jourCode");
            return (Criteria) this;
        }

        public Criteria andJourCodeNotLike(String value) {
            addCriterion("JOUR_CODE not like", value, "jourCode");
            return (Criteria) this;
        }

        public Criteria andJourCodeIn(List<String> values) {
            addCriterion("JOUR_CODE in", values, "jourCode");
            return (Criteria) this;
        }

        public Criteria andJourCodeNotIn(List<String> values) {
            addCriterion("JOUR_CODE not in", values, "jourCode");
            return (Criteria) this;
        }

        public Criteria andJourCodeBetween(String value1, String value2) {
            addCriterion("JOUR_CODE between", value1, value2, "jourCode");
            return (Criteria) this;
        }

        public Criteria andJourCodeNotBetween(String value1, String value2) {
            addCriterion("JOUR_CODE not between", value1, value2, "jourCode");
            return (Criteria) this;
        }

        public Criteria andVolumeCodeIsNull() {
            addCriterion("VOLUME_CODE is null");
            return (Criteria) this;
        }

        public Criteria andVolumeCodeIsNotNull() {
            addCriterion("VOLUME_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andVolumeCodeEqualTo(String value) {
            addCriterion("VOLUME_CODE =", value, "volumeCode");
            return (Criteria) this;
        }

        public Criteria andVolumeCodeNotEqualTo(String value) {
            addCriterion("VOLUME_CODE <>", value, "volumeCode");
            return (Criteria) this;
        }

        public Criteria andVolumeCodeGreaterThan(String value) {
            addCriterion("VOLUME_CODE >", value, "volumeCode");
            return (Criteria) this;
        }

        public Criteria andVolumeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("VOLUME_CODE >=", value, "volumeCode");
            return (Criteria) this;
        }

        public Criteria andVolumeCodeLessThan(String value) {
            addCriterion("VOLUME_CODE <", value, "volumeCode");
            return (Criteria) this;
        }

        public Criteria andVolumeCodeLessThanOrEqualTo(String value) {
            addCriterion("VOLUME_CODE <=", value, "volumeCode");
            return (Criteria) this;
        }

        public Criteria andVolumeCodeLike(String value) {
            addCriterion("VOLUME_CODE like", value, "volumeCode");
            return (Criteria) this;
        }

        public Criteria andVolumeCodeNotLike(String value) {
            addCriterion("VOLUME_CODE not like", value, "volumeCode");
            return (Criteria) this;
        }

        public Criteria andVolumeCodeIn(List<String> values) {
            addCriterion("VOLUME_CODE in", values, "volumeCode");
            return (Criteria) this;
        }

        public Criteria andVolumeCodeNotIn(List<String> values) {
            addCriterion("VOLUME_CODE not in", values, "volumeCode");
            return (Criteria) this;
        }

        public Criteria andVolumeCodeBetween(String value1, String value2) {
            addCriterion("VOLUME_CODE between", value1, value2, "volumeCode");
            return (Criteria) this;
        }

        public Criteria andVolumeCodeNotBetween(String value1, String value2) {
            addCriterion("VOLUME_CODE not between", value1, value2, "volumeCode");
            return (Criteria) this;
        }

        public Criteria andPageNoRangeIsNull() {
            addCriterion("PAGE_NO_RANGE is null");
            return (Criteria) this;
        }

        public Criteria andPageNoRangeIsNotNull() {
            addCriterion("PAGE_NO_RANGE is not null");
            return (Criteria) this;
        }

        public Criteria andPageNoRangeEqualTo(String value) {
            addCriterion("PAGE_NO_RANGE =", value, "pageNoRange");
            return (Criteria) this;
        }

        public Criteria andPageNoRangeNotEqualTo(String value) {
            addCriterion("PAGE_NO_RANGE <>", value, "pageNoRange");
            return (Criteria) this;
        }

        public Criteria andPageNoRangeGreaterThan(String value) {
            addCriterion("PAGE_NO_RANGE >", value, "pageNoRange");
            return (Criteria) this;
        }

        public Criteria andPageNoRangeGreaterThanOrEqualTo(String value) {
            addCriterion("PAGE_NO_RANGE >=", value, "pageNoRange");
            return (Criteria) this;
        }

        public Criteria andPageNoRangeLessThan(String value) {
            addCriterion("PAGE_NO_RANGE <", value, "pageNoRange");
            return (Criteria) this;
        }

        public Criteria andPageNoRangeLessThanOrEqualTo(String value) {
            addCriterion("PAGE_NO_RANGE <=", value, "pageNoRange");
            return (Criteria) this;
        }

        public Criteria andPageNoRangeLike(String value) {
            addCriterion("PAGE_NO_RANGE like", value, "pageNoRange");
            return (Criteria) this;
        }

        public Criteria andPageNoRangeNotLike(String value) {
            addCriterion("PAGE_NO_RANGE not like", value, "pageNoRange");
            return (Criteria) this;
        }

        public Criteria andPageNoRangeIn(List<String> values) {
            addCriterion("PAGE_NO_RANGE in", values, "pageNoRange");
            return (Criteria) this;
        }

        public Criteria andPageNoRangeNotIn(List<String> values) {
            addCriterion("PAGE_NO_RANGE not in", values, "pageNoRange");
            return (Criteria) this;
        }

        public Criteria andPageNoRangeBetween(String value1, String value2) {
            addCriterion("PAGE_NO_RANGE between", value1, value2, "pageNoRange");
            return (Criteria) this;
        }

        public Criteria andPageNoRangeNotBetween(String value1, String value2) {
            addCriterion("PAGE_NO_RANGE not between", value1, value2, "pageNoRange");
            return (Criteria) this;
        }

        public Criteria andWordCountIsNull() {
            addCriterion("WORD_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andWordCountIsNotNull() {
            addCriterion("WORD_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andWordCountEqualTo(String value) {
            addCriterion("WORD_COUNT =", value, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountNotEqualTo(String value) {
            addCriterion("WORD_COUNT <>", value, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountGreaterThan(String value) {
            addCriterion("WORD_COUNT >", value, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountGreaterThanOrEqualTo(String value) {
            addCriterion("WORD_COUNT >=", value, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountLessThan(String value) {
            addCriterion("WORD_COUNT <", value, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountLessThanOrEqualTo(String value) {
            addCriterion("WORD_COUNT <=", value, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountLike(String value) {
            addCriterion("WORD_COUNT like", value, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountNotLike(String value) {
            addCriterion("WORD_COUNT not like", value, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountIn(List<String> values) {
            addCriterion("WORD_COUNT in", values, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountNotIn(List<String> values) {
            addCriterion("WORD_COUNT not in", values, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountBetween(String value1, String value2) {
            addCriterion("WORD_COUNT between", value1, value2, "wordCount");
            return (Criteria) this;
        }

        public Criteria andWordCountNotBetween(String value1, String value2) {
            addCriterion("WORD_COUNT not between", value1, value2, "wordCount");
            return (Criteria) this;
        }

        public Criteria andHospSignIdIsNull() {
            addCriterion("HOSP_SIGN_ID is null");
            return (Criteria) this;
        }

        public Criteria andHospSignIdIsNotNull() {
            addCriterion("HOSP_SIGN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andHospSignIdEqualTo(String value) {
            addCriterion("HOSP_SIGN_ID =", value, "hospSignId");
            return (Criteria) this;
        }

        public Criteria andHospSignIdNotEqualTo(String value) {
            addCriterion("HOSP_SIGN_ID <>", value, "hospSignId");
            return (Criteria) this;
        }

        public Criteria andHospSignIdGreaterThan(String value) {
            addCriterion("HOSP_SIGN_ID >", value, "hospSignId");
            return (Criteria) this;
        }

        public Criteria andHospSignIdGreaterThanOrEqualTo(String value) {
            addCriterion("HOSP_SIGN_ID >=", value, "hospSignId");
            return (Criteria) this;
        }

        public Criteria andHospSignIdLessThan(String value) {
            addCriterion("HOSP_SIGN_ID <", value, "hospSignId");
            return (Criteria) this;
        }

        public Criteria andHospSignIdLessThanOrEqualTo(String value) {
            addCriterion("HOSP_SIGN_ID <=", value, "hospSignId");
            return (Criteria) this;
        }

        public Criteria andHospSignIdLike(String value) {
            addCriterion("HOSP_SIGN_ID like", value, "hospSignId");
            return (Criteria) this;
        }

        public Criteria andHospSignIdNotLike(String value) {
            addCriterion("HOSP_SIGN_ID not like", value, "hospSignId");
            return (Criteria) this;
        }

        public Criteria andHospSignIdIn(List<String> values) {
            addCriterion("HOSP_SIGN_ID in", values, "hospSignId");
            return (Criteria) this;
        }

        public Criteria andHospSignIdNotIn(List<String> values) {
            addCriterion("HOSP_SIGN_ID not in", values, "hospSignId");
            return (Criteria) this;
        }

        public Criteria andHospSignIdBetween(String value1, String value2) {
            addCriterion("HOSP_SIGN_ID between", value1, value2, "hospSignId");
            return (Criteria) this;
        }

        public Criteria andHospSignIdNotBetween(String value1, String value2) {
            addCriterion("HOSP_SIGN_ID not between", value1, value2, "hospSignId");
            return (Criteria) this;
        }

        public Criteria andHospSignNameIsNull() {
            addCriterion("HOSP_SIGN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andHospSignNameIsNotNull() {
            addCriterion("HOSP_SIGN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andHospSignNameEqualTo(String value) {
            addCriterion("HOSP_SIGN_NAME =", value, "hospSignName");
            return (Criteria) this;
        }

        public Criteria andHospSignNameNotEqualTo(String value) {
            addCriterion("HOSP_SIGN_NAME <>", value, "hospSignName");
            return (Criteria) this;
        }

        public Criteria andHospSignNameGreaterThan(String value) {
            addCriterion("HOSP_SIGN_NAME >", value, "hospSignName");
            return (Criteria) this;
        }

        public Criteria andHospSignNameGreaterThanOrEqualTo(String value) {
            addCriterion("HOSP_SIGN_NAME >=", value, "hospSignName");
            return (Criteria) this;
        }

        public Criteria andHospSignNameLessThan(String value) {
            addCriterion("HOSP_SIGN_NAME <", value, "hospSignName");
            return (Criteria) this;
        }

        public Criteria andHospSignNameLessThanOrEqualTo(String value) {
            addCriterion("HOSP_SIGN_NAME <=", value, "hospSignName");
            return (Criteria) this;
        }

        public Criteria andHospSignNameLike(String value) {
            addCriterion("HOSP_SIGN_NAME like", value, "hospSignName");
            return (Criteria) this;
        }

        public Criteria andHospSignNameNotLike(String value) {
            addCriterion("HOSP_SIGN_NAME not like", value, "hospSignName");
            return (Criteria) this;
        }

        public Criteria andHospSignNameIn(List<String> values) {
            addCriterion("HOSP_SIGN_NAME in", values, "hospSignName");
            return (Criteria) this;
        }

        public Criteria andHospSignNameNotIn(List<String> values) {
            addCriterion("HOSP_SIGN_NAME not in", values, "hospSignName");
            return (Criteria) this;
        }

        public Criteria andHospSignNameBetween(String value1, String value2) {
            addCriterion("HOSP_SIGN_NAME between", value1, value2, "hospSignName");
            return (Criteria) this;
        }

        public Criteria andHospSignNameNotBetween(String value1, String value2) {
            addCriterion("HOSP_SIGN_NAME not between", value1, value2, "hospSignName");
            return (Criteria) this;
        }

        public Criteria andPublishTypeIdIsNull() {
            addCriterion("PUBLISH_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPublishTypeIdIsNotNull() {
            addCriterion("PUBLISH_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPublishTypeIdEqualTo(String value) {
            addCriterion("PUBLISH_TYPE_ID =", value, "publishTypeId");
            return (Criteria) this;
        }

        public Criteria andPublishTypeIdNotEqualTo(String value) {
            addCriterion("PUBLISH_TYPE_ID <>", value, "publishTypeId");
            return (Criteria) this;
        }

        public Criteria andPublishTypeIdGreaterThan(String value) {
            addCriterion("PUBLISH_TYPE_ID >", value, "publishTypeId");
            return (Criteria) this;
        }

        public Criteria andPublishTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLISH_TYPE_ID >=", value, "publishTypeId");
            return (Criteria) this;
        }

        public Criteria andPublishTypeIdLessThan(String value) {
            addCriterion("PUBLISH_TYPE_ID <", value, "publishTypeId");
            return (Criteria) this;
        }

        public Criteria andPublishTypeIdLessThanOrEqualTo(String value) {
            addCriterion("PUBLISH_TYPE_ID <=", value, "publishTypeId");
            return (Criteria) this;
        }

        public Criteria andPublishTypeIdLike(String value) {
            addCriterion("PUBLISH_TYPE_ID like", value, "publishTypeId");
            return (Criteria) this;
        }

        public Criteria andPublishTypeIdNotLike(String value) {
            addCriterion("PUBLISH_TYPE_ID not like", value, "publishTypeId");
            return (Criteria) this;
        }

        public Criteria andPublishTypeIdIn(List<String> values) {
            addCriterion("PUBLISH_TYPE_ID in", values, "publishTypeId");
            return (Criteria) this;
        }

        public Criteria andPublishTypeIdNotIn(List<String> values) {
            addCriterion("PUBLISH_TYPE_ID not in", values, "publishTypeId");
            return (Criteria) this;
        }

        public Criteria andPublishTypeIdBetween(String value1, String value2) {
            addCriterion("PUBLISH_TYPE_ID between", value1, value2, "publishTypeId");
            return (Criteria) this;
        }

        public Criteria andPublishTypeIdNotBetween(String value1, String value2) {
            addCriterion("PUBLISH_TYPE_ID not between", value1, value2, "publishTypeId");
            return (Criteria) this;
        }

        public Criteria andPublishTypeNameIsNull() {
            addCriterion("PUBLISH_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPublishTypeNameIsNotNull() {
            addCriterion("PUBLISH_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPublishTypeNameEqualTo(String value) {
            addCriterion("PUBLISH_TYPE_NAME =", value, "publishTypeName");
            return (Criteria) this;
        }

        public Criteria andPublishTypeNameNotEqualTo(String value) {
            addCriterion("PUBLISH_TYPE_NAME <>", value, "publishTypeName");
            return (Criteria) this;
        }

        public Criteria andPublishTypeNameGreaterThan(String value) {
            addCriterion("PUBLISH_TYPE_NAME >", value, "publishTypeName");
            return (Criteria) this;
        }

        public Criteria andPublishTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLISH_TYPE_NAME >=", value, "publishTypeName");
            return (Criteria) this;
        }

        public Criteria andPublishTypeNameLessThan(String value) {
            addCriterion("PUBLISH_TYPE_NAME <", value, "publishTypeName");
            return (Criteria) this;
        }

        public Criteria andPublishTypeNameLessThanOrEqualTo(String value) {
            addCriterion("PUBLISH_TYPE_NAME <=", value, "publishTypeName");
            return (Criteria) this;
        }

        public Criteria andPublishTypeNameLike(String value) {
            addCriterion("PUBLISH_TYPE_NAME like", value, "publishTypeName");
            return (Criteria) this;
        }

        public Criteria andPublishTypeNameNotLike(String value) {
            addCriterion("PUBLISH_TYPE_NAME not like", value, "publishTypeName");
            return (Criteria) this;
        }

        public Criteria andPublishTypeNameIn(List<String> values) {
            addCriterion("PUBLISH_TYPE_NAME in", values, "publishTypeName");
            return (Criteria) this;
        }

        public Criteria andPublishTypeNameNotIn(List<String> values) {
            addCriterion("PUBLISH_TYPE_NAME not in", values, "publishTypeName");
            return (Criteria) this;
        }

        public Criteria andPublishTypeNameBetween(String value1, String value2) {
            addCriterion("PUBLISH_TYPE_NAME between", value1, value2, "publishTypeName");
            return (Criteria) this;
        }

        public Criteria andPublishTypeNameNotBetween(String value1, String value2) {
            addCriterion("PUBLISH_TYPE_NAME not between", value1, value2, "publishTypeName");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedIsNull() {
            addCriterion("IS_TRANSLATED is null");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedIsNotNull() {
            addCriterion("IS_TRANSLATED is not null");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedEqualTo(String value) {
            addCriterion("IS_TRANSLATED =", value, "isTranslated");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedNotEqualTo(String value) {
            addCriterion("IS_TRANSLATED <>", value, "isTranslated");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedGreaterThan(String value) {
            addCriterion("IS_TRANSLATED >", value, "isTranslated");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedGreaterThanOrEqualTo(String value) {
            addCriterion("IS_TRANSLATED >=", value, "isTranslated");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedLessThan(String value) {
            addCriterion("IS_TRANSLATED <", value, "isTranslated");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedLessThanOrEqualTo(String value) {
            addCriterion("IS_TRANSLATED <=", value, "isTranslated");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedLike(String value) {
            addCriterion("IS_TRANSLATED like", value, "isTranslated");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedNotLike(String value) {
            addCriterion("IS_TRANSLATED not like", value, "isTranslated");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedIn(List<String> values) {
            addCriterion("IS_TRANSLATED in", values, "isTranslated");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedNotIn(List<String> values) {
            addCriterion("IS_TRANSLATED not in", values, "isTranslated");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedBetween(String value1, String value2) {
            addCriterion("IS_TRANSLATED between", value1, value2, "isTranslated");
            return (Criteria) this;
        }

        public Criteria andIsTranslatedNotBetween(String value1, String value2) {
            addCriterion("IS_TRANSLATED not between", value1, value2, "isTranslated");
            return (Criteria) this;
        }

        public Criteria andIssnCodeIsNull() {
            addCriterion("ISSN_CODE is null");
            return (Criteria) this;
        }

        public Criteria andIssnCodeIsNotNull() {
            addCriterion("ISSN_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andIssnCodeEqualTo(String value) {
            addCriterion("ISSN_CODE =", value, "issnCode");
            return (Criteria) this;
        }

        public Criteria andIssnCodeNotEqualTo(String value) {
            addCriterion("ISSN_CODE <>", value, "issnCode");
            return (Criteria) this;
        }

        public Criteria andIssnCodeGreaterThan(String value) {
            addCriterion("ISSN_CODE >", value, "issnCode");
            return (Criteria) this;
        }

        public Criteria andIssnCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ISSN_CODE >=", value, "issnCode");
            return (Criteria) this;
        }

        public Criteria andIssnCodeLessThan(String value) {
            addCriterion("ISSN_CODE <", value, "issnCode");
            return (Criteria) this;
        }

        public Criteria andIssnCodeLessThanOrEqualTo(String value) {
            addCriterion("ISSN_CODE <=", value, "issnCode");
            return (Criteria) this;
        }

        public Criteria andIssnCodeLike(String value) {
            addCriterion("ISSN_CODE like", value, "issnCode");
            return (Criteria) this;
        }

        public Criteria andIssnCodeNotLike(String value) {
            addCriterion("ISSN_CODE not like", value, "issnCode");
            return (Criteria) this;
        }

        public Criteria andIssnCodeIn(List<String> values) {
            addCriterion("ISSN_CODE in", values, "issnCode");
            return (Criteria) this;
        }

        public Criteria andIssnCodeNotIn(List<String> values) {
            addCriterion("ISSN_CODE not in", values, "issnCode");
            return (Criteria) this;
        }

        public Criteria andIssnCodeBetween(String value1, String value2) {
            addCriterion("ISSN_CODE between", value1, value2, "issnCode");
            return (Criteria) this;
        }

        public Criteria andIssnCodeNotBetween(String value1, String value2) {
            addCriterion("ISSN_CODE not between", value1, value2, "issnCode");
            return (Criteria) this;
        }

        public Criteria andCnCodeIsNull() {
            addCriterion("CN_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCnCodeIsNotNull() {
            addCriterion("CN_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCnCodeEqualTo(String value) {
            addCriterion("CN_CODE =", value, "cnCode");
            return (Criteria) this;
        }

        public Criteria andCnCodeNotEqualTo(String value) {
            addCriterion("CN_CODE <>", value, "cnCode");
            return (Criteria) this;
        }

        public Criteria andCnCodeGreaterThan(String value) {
            addCriterion("CN_CODE >", value, "cnCode");
            return (Criteria) this;
        }

        public Criteria andCnCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CN_CODE >=", value, "cnCode");
            return (Criteria) this;
        }

        public Criteria andCnCodeLessThan(String value) {
            addCriterion("CN_CODE <", value, "cnCode");
            return (Criteria) this;
        }

        public Criteria andCnCodeLessThanOrEqualTo(String value) {
            addCriterion("CN_CODE <=", value, "cnCode");
            return (Criteria) this;
        }

        public Criteria andCnCodeLike(String value) {
            addCriterion("CN_CODE like", value, "cnCode");
            return (Criteria) this;
        }

        public Criteria andCnCodeNotLike(String value) {
            addCriterion("CN_CODE not like", value, "cnCode");
            return (Criteria) this;
        }

        public Criteria andCnCodeIn(List<String> values) {
            addCriterion("CN_CODE in", values, "cnCode");
            return (Criteria) this;
        }

        public Criteria andCnCodeNotIn(List<String> values) {
            addCriterion("CN_CODE not in", values, "cnCode");
            return (Criteria) this;
        }

        public Criteria andCnCodeBetween(String value1, String value2) {
            addCriterion("CN_CODE between", value1, value2, "cnCode");
            return (Criteria) this;
        }

        public Criteria andCnCodeNotBetween(String value1, String value2) {
            addCriterion("CN_CODE not between", value1, value2, "cnCode");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdIsNull() {
            addCriterion("PROJ_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdIsNotNull() {
            addCriterion("PROJ_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdEqualTo(String value) {
            addCriterion("PROJ_TYPE_ID =", value, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdNotEqualTo(String value) {
            addCriterion("PROJ_TYPE_ID <>", value, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdGreaterThan(String value) {
            addCriterion("PROJ_TYPE_ID >", value, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_TYPE_ID >=", value, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdLessThan(String value) {
            addCriterion("PROJ_TYPE_ID <", value, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdLessThanOrEqualTo(String value) {
            addCriterion("PROJ_TYPE_ID <=", value, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdLike(String value) {
            addCriterion("PROJ_TYPE_ID like", value, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdNotLike(String value) {
            addCriterion("PROJ_TYPE_ID not like", value, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdIn(List<String> values) {
            addCriterion("PROJ_TYPE_ID in", values, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdNotIn(List<String> values) {
            addCriterion("PROJ_TYPE_ID not in", values, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdBetween(String value1, String value2) {
            addCriterion("PROJ_TYPE_ID between", value1, value2, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeIdNotBetween(String value1, String value2) {
            addCriterion("PROJ_TYPE_ID not between", value1, value2, "projTypeId");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameIsNull() {
            addCriterion("PROJ_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameIsNotNull() {
            addCriterion("PROJ_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameEqualTo(String value) {
            addCriterion("PROJ_TYPE_NAME =", value, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameNotEqualTo(String value) {
            addCriterion("PROJ_TYPE_NAME <>", value, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameGreaterThan(String value) {
            addCriterion("PROJ_TYPE_NAME >", value, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_TYPE_NAME >=", value, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameLessThan(String value) {
            addCriterion("PROJ_TYPE_NAME <", value, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameLessThanOrEqualTo(String value) {
            addCriterion("PROJ_TYPE_NAME <=", value, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameLike(String value) {
            addCriterion("PROJ_TYPE_NAME like", value, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameNotLike(String value) {
            addCriterion("PROJ_TYPE_NAME not like", value, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameIn(List<String> values) {
            addCriterion("PROJ_TYPE_NAME in", values, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameNotIn(List<String> values) {
            addCriterion("PROJ_TYPE_NAME not in", values, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameBetween(String value1, String value2) {
            addCriterion("PROJ_TYPE_NAME between", value1, value2, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andProjTypeNameNotBetween(String value1, String value2) {
            addCriterion("PROJ_TYPE_NAME not between", value1, value2, "projTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdIsNull() {
            addCriterion("SUBJECT_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdIsNotNull() {
            addCriterion("SUBJECT_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdEqualTo(String value) {
            addCriterion("SUBJECT_TYPE_ID =", value, "subjectTypeId");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdNotEqualTo(String value) {
            addCriterion("SUBJECT_TYPE_ID <>", value, "subjectTypeId");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdGreaterThan(String value) {
            addCriterion("SUBJECT_TYPE_ID >", value, "subjectTypeId");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_TYPE_ID >=", value, "subjectTypeId");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdLessThan(String value) {
            addCriterion("SUBJECT_TYPE_ID <", value, "subjectTypeId");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_TYPE_ID <=", value, "subjectTypeId");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdLike(String value) {
            addCriterion("SUBJECT_TYPE_ID like", value, "subjectTypeId");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdNotLike(String value) {
            addCriterion("SUBJECT_TYPE_ID not like", value, "subjectTypeId");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdIn(List<String> values) {
            addCriterion("SUBJECT_TYPE_ID in", values, "subjectTypeId");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdNotIn(List<String> values) {
            addCriterion("SUBJECT_TYPE_ID not in", values, "subjectTypeId");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdBetween(String value1, String value2) {
            addCriterion("SUBJECT_TYPE_ID between", value1, value2, "subjectTypeId");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeIdNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_TYPE_ID not between", value1, value2, "subjectTypeId");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameIsNull() {
            addCriterion("SUBJECT_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameIsNotNull() {
            addCriterion("SUBJECT_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameEqualTo(String value) {
            addCriterion("SUBJECT_TYPE_NAME =", value, "subjectTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameNotEqualTo(String value) {
            addCriterion("SUBJECT_TYPE_NAME <>", value, "subjectTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameGreaterThan(String value) {
            addCriterion("SUBJECT_TYPE_NAME >", value, "subjectTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_TYPE_NAME >=", value, "subjectTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameLessThan(String value) {
            addCriterion("SUBJECT_TYPE_NAME <", value, "subjectTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_TYPE_NAME <=", value, "subjectTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameLike(String value) {
            addCriterion("SUBJECT_TYPE_NAME like", value, "subjectTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameNotLike(String value) {
            addCriterion("SUBJECT_TYPE_NAME not like", value, "subjectTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameIn(List<String> values) {
            addCriterion("SUBJECT_TYPE_NAME in", values, "subjectTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameNotIn(List<String> values) {
            addCriterion("SUBJECT_TYPE_NAME not in", values, "subjectTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameBetween(String value1, String value2) {
            addCriterion("SUBJECT_TYPE_NAME between", value1, value2, "subjectTypeName");
            return (Criteria) this;
        }

        public Criteria andSubjectTypeNameNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_TYPE_NAME not between", value1, value2, "subjectTypeName");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdIsNull() {
            addCriterion("PROJ_SOURCE_ID is null");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdIsNotNull() {
            addCriterion("PROJ_SOURCE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdEqualTo(String value) {
            addCriterion("PROJ_SOURCE_ID =", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdNotEqualTo(String value) {
            addCriterion("PROJ_SOURCE_ID <>", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdGreaterThan(String value) {
            addCriterion("PROJ_SOURCE_ID >", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_SOURCE_ID >=", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdLessThan(String value) {
            addCriterion("PROJ_SOURCE_ID <", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdLessThanOrEqualTo(String value) {
            addCriterion("PROJ_SOURCE_ID <=", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdLike(String value) {
            addCriterion("PROJ_SOURCE_ID like", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdNotLike(String value) {
            addCriterion("PROJ_SOURCE_ID not like", value, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdIn(List<String> values) {
            addCriterion("PROJ_SOURCE_ID in", values, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdNotIn(List<String> values) {
            addCriterion("PROJ_SOURCE_ID not in", values, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdBetween(String value1, String value2) {
            addCriterion("PROJ_SOURCE_ID between", value1, value2, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceIdNotBetween(String value1, String value2) {
            addCriterion("PROJ_SOURCE_ID not between", value1, value2, "projSourceId");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameIsNull() {
            addCriterion("PROJ_SOURCE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameIsNotNull() {
            addCriterion("PROJ_SOURCE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameEqualTo(String value) {
            addCriterion("PROJ_SOURCE_NAME =", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameNotEqualTo(String value) {
            addCriterion("PROJ_SOURCE_NAME <>", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameGreaterThan(String value) {
            addCriterion("PROJ_SOURCE_NAME >", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJ_SOURCE_NAME >=", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameLessThan(String value) {
            addCriterion("PROJ_SOURCE_NAME <", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameLessThanOrEqualTo(String value) {
            addCriterion("PROJ_SOURCE_NAME <=", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameLike(String value) {
            addCriterion("PROJ_SOURCE_NAME like", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameNotLike(String value) {
            addCriterion("PROJ_SOURCE_NAME not like", value, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameIn(List<String> values) {
            addCriterion("PROJ_SOURCE_NAME in", values, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameNotIn(List<String> values) {
            addCriterion("PROJ_SOURCE_NAME not in", values, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameBetween(String value1, String value2) {
            addCriterion("PROJ_SOURCE_NAME between", value1, value2, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andProjSourceNameNotBetween(String value1, String value2) {
            addCriterion("PROJ_SOURCE_NAME not between", value1, value2, "projSourceName");
            return (Criteria) this;
        }

        public Criteria andPublishScopeIdIsNull() {
            addCriterion("PUBLISH_SCOPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPublishScopeIdIsNotNull() {
            addCriterion("PUBLISH_SCOPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPublishScopeIdEqualTo(String value) {
            addCriterion("PUBLISH_SCOPE_ID =", value, "publishScopeId");
            return (Criteria) this;
        }

        public Criteria andPublishScopeIdNotEqualTo(String value) {
            addCriterion("PUBLISH_SCOPE_ID <>", value, "publishScopeId");
            return (Criteria) this;
        }

        public Criteria andPublishScopeIdGreaterThan(String value) {
            addCriterion("PUBLISH_SCOPE_ID >", value, "publishScopeId");
            return (Criteria) this;
        }

        public Criteria andPublishScopeIdGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLISH_SCOPE_ID >=", value, "publishScopeId");
            return (Criteria) this;
        }

        public Criteria andPublishScopeIdLessThan(String value) {
            addCriterion("PUBLISH_SCOPE_ID <", value, "publishScopeId");
            return (Criteria) this;
        }

        public Criteria andPublishScopeIdLessThanOrEqualTo(String value) {
            addCriterion("PUBLISH_SCOPE_ID <=", value, "publishScopeId");
            return (Criteria) this;
        }

        public Criteria andPublishScopeIdLike(String value) {
            addCriterion("PUBLISH_SCOPE_ID like", value, "publishScopeId");
            return (Criteria) this;
        }

        public Criteria andPublishScopeIdNotLike(String value) {
            addCriterion("PUBLISH_SCOPE_ID not like", value, "publishScopeId");
            return (Criteria) this;
        }

        public Criteria andPublishScopeIdIn(List<String> values) {
            addCriterion("PUBLISH_SCOPE_ID in", values, "publishScopeId");
            return (Criteria) this;
        }

        public Criteria andPublishScopeIdNotIn(List<String> values) {
            addCriterion("PUBLISH_SCOPE_ID not in", values, "publishScopeId");
            return (Criteria) this;
        }

        public Criteria andPublishScopeIdBetween(String value1, String value2) {
            addCriterion("PUBLISH_SCOPE_ID between", value1, value2, "publishScopeId");
            return (Criteria) this;
        }

        public Criteria andPublishScopeIdNotBetween(String value1, String value2) {
            addCriterion("PUBLISH_SCOPE_ID not between", value1, value2, "publishScopeId");
            return (Criteria) this;
        }

        public Criteria andPublishScopeNameIsNull() {
            addCriterion("PUBLISH_SCOPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPublishScopeNameIsNotNull() {
            addCriterion("PUBLISH_SCOPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPublishScopeNameEqualTo(String value) {
            addCriterion("PUBLISH_SCOPE_NAME =", value, "publishScopeName");
            return (Criteria) this;
        }

        public Criteria andPublishScopeNameNotEqualTo(String value) {
            addCriterion("PUBLISH_SCOPE_NAME <>", value, "publishScopeName");
            return (Criteria) this;
        }

        public Criteria andPublishScopeNameGreaterThan(String value) {
            addCriterion("PUBLISH_SCOPE_NAME >", value, "publishScopeName");
            return (Criteria) this;
        }

        public Criteria andPublishScopeNameGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLISH_SCOPE_NAME >=", value, "publishScopeName");
            return (Criteria) this;
        }

        public Criteria andPublishScopeNameLessThan(String value) {
            addCriterion("PUBLISH_SCOPE_NAME <", value, "publishScopeName");
            return (Criteria) this;
        }

        public Criteria andPublishScopeNameLessThanOrEqualTo(String value) {
            addCriterion("PUBLISH_SCOPE_NAME <=", value, "publishScopeName");
            return (Criteria) this;
        }

        public Criteria andPublishScopeNameLike(String value) {
            addCriterion("PUBLISH_SCOPE_NAME like", value, "publishScopeName");
            return (Criteria) this;
        }

        public Criteria andPublishScopeNameNotLike(String value) {
            addCriterion("PUBLISH_SCOPE_NAME not like", value, "publishScopeName");
            return (Criteria) this;
        }

        public Criteria andPublishScopeNameIn(List<String> values) {
            addCriterion("PUBLISH_SCOPE_NAME in", values, "publishScopeName");
            return (Criteria) this;
        }

        public Criteria andPublishScopeNameNotIn(List<String> values) {
            addCriterion("PUBLISH_SCOPE_NAME not in", values, "publishScopeName");
            return (Criteria) this;
        }

        public Criteria andPublishScopeNameBetween(String value1, String value2) {
            addCriterion("PUBLISH_SCOPE_NAME between", value1, value2, "publishScopeName");
            return (Criteria) this;
        }

        public Criteria andPublishScopeNameNotBetween(String value1, String value2) {
            addCriterion("PUBLISH_SCOPE_NAME not between", value1, value2, "publishScopeName");
            return (Criteria) this;
        }

        public Criteria andKeyWordIsNull() {
            addCriterion("KEY_WORD is null");
            return (Criteria) this;
        }

        public Criteria andKeyWordIsNotNull() {
            addCriterion("KEY_WORD is not null");
            return (Criteria) this;
        }

        public Criteria andKeyWordEqualTo(String value) {
            addCriterion("KEY_WORD =", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordNotEqualTo(String value) {
            addCriterion("KEY_WORD <>", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordGreaterThan(String value) {
            addCriterion("KEY_WORD >", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordGreaterThanOrEqualTo(String value) {
            addCriterion("KEY_WORD >=", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordLessThan(String value) {
            addCriterion("KEY_WORD <", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordLessThanOrEqualTo(String value) {
            addCriterion("KEY_WORD <=", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordLike(String value) {
            addCriterion("KEY_WORD like", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordNotLike(String value) {
            addCriterion("KEY_WORD not like", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordIn(List<String> values) {
            addCriterion("KEY_WORD in", values, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordNotIn(List<String> values) {
            addCriterion("KEY_WORD not in", values, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordBetween(String value1, String value2) {
            addCriterion("KEY_WORD between", value1, value2, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordNotBetween(String value1, String value2) {
            addCriterion("KEY_WORD not between", value1, value2, "keyWord");
            return (Criteria) this;
        }

        public Criteria andSummaryIsNull() {
            addCriterion("SUMMARY is null");
            return (Criteria) this;
        }

        public Criteria andSummaryIsNotNull() {
            addCriterion("SUMMARY is not null");
            return (Criteria) this;
        }

        public Criteria andSummaryEqualTo(String value) {
            addCriterion("SUMMARY =", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotEqualTo(String value) {
            addCriterion("SUMMARY <>", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryGreaterThan(String value) {
            addCriterion("SUMMARY >", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryGreaterThanOrEqualTo(String value) {
            addCriterion("SUMMARY >=", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLessThan(String value) {
            addCriterion("SUMMARY <", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLessThanOrEqualTo(String value) {
            addCriterion("SUMMARY <=", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLike(String value) {
            addCriterion("SUMMARY like", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotLike(String value) {
            addCriterion("SUMMARY not like", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryIn(List<String> values) {
            addCriterion("SUMMARY in", values, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotIn(List<String> values) {
            addCriterion("SUMMARY not in", values, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryBetween(String value1, String value2) {
            addCriterion("SUMMARY between", value1, value2, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotBetween(String value1, String value2) {
            addCriterion("SUMMARY not between", value1, value2, "summary");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowIsNull() {
            addCriterion("APPLY_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowIsNotNull() {
            addCriterion("APPLY_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW =", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW <>", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowGreaterThan(String value) {
            addCriterion("APPLY_USER_FLOW >", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW >=", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowLessThan(String value) {
            addCriterion("APPLY_USER_FLOW <", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowLessThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW <=", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowLike(String value) {
            addCriterion("APPLY_USER_FLOW like", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotLike(String value) {
            addCriterion("APPLY_USER_FLOW not like", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowIn(List<String> values) {
            addCriterion("APPLY_USER_FLOW in", values, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotIn(List<String> values) {
            addCriterion("APPLY_USER_FLOW not in", values, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowBetween(String value1, String value2) {
            addCriterion("APPLY_USER_FLOW between", value1, value2, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotBetween(String value1, String value2) {
            addCriterion("APPLY_USER_FLOW not between", value1, value2, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameIsNull() {
            addCriterion("APPLY_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameIsNotNull() {
            addCriterion("APPLY_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameEqualTo(String value) {
            addCriterion("APPLY_USER_NAME =", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotEqualTo(String value) {
            addCriterion("APPLY_USER_NAME <>", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameGreaterThan(String value) {
            addCriterion("APPLY_USER_NAME >", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_NAME >=", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameLessThan(String value) {
            addCriterion("APPLY_USER_NAME <", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameLessThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_NAME <=", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameLike(String value) {
            addCriterion("APPLY_USER_NAME like", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotLike(String value) {
            addCriterion("APPLY_USER_NAME not like", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameIn(List<String> values) {
            addCriterion("APPLY_USER_NAME in", values, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotIn(List<String> values) {
            addCriterion("APPLY_USER_NAME not in", values, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameBetween(String value1, String value2) {
            addCriterion("APPLY_USER_NAME between", value1, value2, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotBetween(String value1, String value2) {
            addCriterion("APPLY_USER_NAME not between", value1, value2, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowIsNull() {
            addCriterion("APPLY_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowIsNotNull() {
            addCriterion("APPLY_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowEqualTo(String value) {
            addCriterion("APPLY_ORG_FLOW =", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowNotEqualTo(String value) {
            addCriterion("APPLY_ORG_FLOW <>", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowGreaterThan(String value) {
            addCriterion("APPLY_ORG_FLOW >", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_ORG_FLOW >=", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowLessThan(String value) {
            addCriterion("APPLY_ORG_FLOW <", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("APPLY_ORG_FLOW <=", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowLike(String value) {
            addCriterion("APPLY_ORG_FLOW like", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowNotLike(String value) {
            addCriterion("APPLY_ORG_FLOW not like", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowIn(List<String> values) {
            addCriterion("APPLY_ORG_FLOW in", values, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowNotIn(List<String> values) {
            addCriterion("APPLY_ORG_FLOW not in", values, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowBetween(String value1, String value2) {
            addCriterion("APPLY_ORG_FLOW between", value1, value2, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowNotBetween(String value1, String value2) {
            addCriterion("APPLY_ORG_FLOW not between", value1, value2, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameIsNull() {
            addCriterion("APPLY_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameIsNotNull() {
            addCriterion("APPLY_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameEqualTo(String value) {
            addCriterion("APPLY_ORG_NAME =", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameNotEqualTo(String value) {
            addCriterion("APPLY_ORG_NAME <>", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameGreaterThan(String value) {
            addCriterion("APPLY_ORG_NAME >", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_ORG_NAME >=", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameLessThan(String value) {
            addCriterion("APPLY_ORG_NAME <", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameLessThanOrEqualTo(String value) {
            addCriterion("APPLY_ORG_NAME <=", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameLike(String value) {
            addCriterion("APPLY_ORG_NAME like", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameNotLike(String value) {
            addCriterion("APPLY_ORG_NAME not like", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameIn(List<String> values) {
            addCriterion("APPLY_ORG_NAME in", values, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameNotIn(List<String> values) {
            addCriterion("APPLY_ORG_NAME not in", values, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameBetween(String value1, String value2) {
            addCriterion("APPLY_ORG_NAME between", value1, value2, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameNotBetween(String value1, String value2) {
            addCriterion("APPLY_ORG_NAME not between", value1, value2, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdIsNull() {
            addCriterion("OPER_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdIsNotNull() {
            addCriterion("OPER_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdEqualTo(String value) {
            addCriterion("OPER_STATUS_ID =", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotEqualTo(String value) {
            addCriterion("OPER_STATUS_ID <>", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdGreaterThan(String value) {
            addCriterion("OPER_STATUS_ID >", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_ID >=", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdLessThan(String value) {
            addCriterion("OPER_STATUS_ID <", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdLessThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_ID <=", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdLike(String value) {
            addCriterion("OPER_STATUS_ID like", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotLike(String value) {
            addCriterion("OPER_STATUS_ID not like", value, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdIn(List<String> values) {
            addCriterion("OPER_STATUS_ID in", values, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotIn(List<String> values) {
            addCriterion("OPER_STATUS_ID not in", values, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_ID between", value1, value2, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusIdNotBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_ID not between", value1, value2, "operStatusId");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameIsNull() {
            addCriterion("OPER_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameIsNotNull() {
            addCriterion("OPER_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME =", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME <>", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameGreaterThan(String value) {
            addCriterion("OPER_STATUS_NAME >", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME >=", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameLessThan(String value) {
            addCriterion("OPER_STATUS_NAME <", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameLessThanOrEqualTo(String value) {
            addCriterion("OPER_STATUS_NAME <=", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameLike(String value) {
            addCriterion("OPER_STATUS_NAME like", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotLike(String value) {
            addCriterion("OPER_STATUS_NAME not like", value, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameIn(List<String> values) {
            addCriterion("OPER_STATUS_NAME in", values, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotIn(List<String> values) {
            addCriterion("OPER_STATUS_NAME not in", values, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_NAME between", value1, value2, "operStatusName");
            return (Criteria) this;
        }

        public Criteria andOperStatusNameNotBetween(String value1, String value2) {
            addCriterion("OPER_STATUS_NAME not between", value1, value2, "operStatusName");
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

        public Criteria andDeptFlowIsNull() {
            addCriterion("DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIsNotNull() {
            addCriterion("DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDeptFlowEqualTo(String value) {
            addCriterion("DEPT_FLOW =", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotEqualTo(String value) {
            addCriterion("DEPT_FLOW <>", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowGreaterThan(String value) {
            addCriterion("DEPT_FLOW >", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_FLOW >=", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLessThan(String value) {
            addCriterion("DEPT_FLOW <", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("DEPT_FLOW <=", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowLike(String value) {
            addCriterion("DEPT_FLOW like", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotLike(String value) {
            addCriterion("DEPT_FLOW not like", value, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowIn(List<String> values) {
            addCriterion("DEPT_FLOW in", values, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotIn(List<String> values) {
            addCriterion("DEPT_FLOW not in", values, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowBetween(String value1, String value2) {
            addCriterion("DEPT_FLOW between", value1, value2, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptFlowNotBetween(String value1, String value2) {
            addCriterion("DEPT_FLOW not between", value1, value2, "deptFlow");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNull() {
            addCriterion("DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNotNull() {
            addCriterion("DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDeptNameEqualTo(String value) {
            addCriterion("DEPT_NAME =", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotEqualTo(String value) {
            addCriterion("DEPT_NAME <>", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThan(String value) {
            addCriterion("DEPT_NAME >", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_NAME >=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThan(String value) {
            addCriterion("DEPT_NAME <", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThanOrEqualTo(String value) {
            addCriterion("DEPT_NAME <=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLike(String value) {
            addCriterion("DEPT_NAME like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotLike(String value) {
            addCriterion("DEPT_NAME not like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameIn(List<String> values) {
            addCriterion("DEPT_NAME in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotIn(List<String> values) {
            addCriterion("DEPT_NAME not in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameBetween(String value1, String value2) {
            addCriterion("DEPT_NAME between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotBetween(String value1, String value2) {
            addCriterion("DEPT_NAME not between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andBranchIdIsNull() {
            addCriterion("BRANCH_ID is null");
            return (Criteria) this;
        }

        public Criteria andBranchIdIsNotNull() {
            addCriterion("BRANCH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBranchIdEqualTo(String value) {
            addCriterion("BRANCH_ID =", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdNotEqualTo(String value) {
            addCriterion("BRANCH_ID <>", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdGreaterThan(String value) {
            addCriterion("BRANCH_ID >", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdGreaterThanOrEqualTo(String value) {
            addCriterion("BRANCH_ID >=", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdLessThan(String value) {
            addCriterion("BRANCH_ID <", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdLessThanOrEqualTo(String value) {
            addCriterion("BRANCH_ID <=", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdLike(String value) {
            addCriterion("BRANCH_ID like", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdNotLike(String value) {
            addCriterion("BRANCH_ID not like", value, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdIn(List<String> values) {
            addCriterion("BRANCH_ID in", values, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdNotIn(List<String> values) {
            addCriterion("BRANCH_ID not in", values, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdBetween(String value1, String value2) {
            addCriterion("BRANCH_ID between", value1, value2, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchIdNotBetween(String value1, String value2) {
            addCriterion("BRANCH_ID not between", value1, value2, "branchId");
            return (Criteria) this;
        }

        public Criteria andBranchNameIsNull() {
            addCriterion("BRANCH_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBranchNameIsNotNull() {
            addCriterion("BRANCH_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBranchNameEqualTo(String value) {
            addCriterion("BRANCH_NAME =", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotEqualTo(String value) {
            addCriterion("BRANCH_NAME <>", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameGreaterThan(String value) {
            addCriterion("BRANCH_NAME >", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameGreaterThanOrEqualTo(String value) {
            addCriterion("BRANCH_NAME >=", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameLessThan(String value) {
            addCriterion("BRANCH_NAME <", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameLessThanOrEqualTo(String value) {
            addCriterion("BRANCH_NAME <=", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameLike(String value) {
            addCriterion("BRANCH_NAME like", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotLike(String value) {
            addCriterion("BRANCH_NAME not like", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameIn(List<String> values) {
            addCriterion("BRANCH_NAME in", values, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotIn(List<String> values) {
            addCriterion("BRANCH_NAME not in", values, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameBetween(String value1, String value2) {
            addCriterion("BRANCH_NAME between", value1, value2, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotBetween(String value1, String value2) {
            addCriterion("BRANCH_NAME not between", value1, value2, "branchName");
            return (Criteria) this;
        }

        public Criteria andJobNumberIsNull() {
            addCriterion("JOB_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andJobNumberIsNotNull() {
            addCriterion("JOB_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andJobNumberEqualTo(String value) {
            addCriterion("JOB_NUMBER =", value, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberNotEqualTo(String value) {
            addCriterion("JOB_NUMBER <>", value, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberGreaterThan(String value) {
            addCriterion("JOB_NUMBER >", value, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberGreaterThanOrEqualTo(String value) {
            addCriterion("JOB_NUMBER >=", value, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberLessThan(String value) {
            addCriterion("JOB_NUMBER <", value, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberLessThanOrEqualTo(String value) {
            addCriterion("JOB_NUMBER <=", value, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberLike(String value) {
            addCriterion("JOB_NUMBER like", value, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberNotLike(String value) {
            addCriterion("JOB_NUMBER not like", value, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberIn(List<String> values) {
            addCriterion("JOB_NUMBER in", values, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberNotIn(List<String> values) {
            addCriterion("JOB_NUMBER not in", values, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberBetween(String value1, String value2) {
            addCriterion("JOB_NUMBER between", value1, value2, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJobNumberNotBetween(String value1, String value2) {
            addCriterion("JOB_NUMBER not between", value1, value2, "jobNumber");
            return (Criteria) this;
        }

        public Criteria andJourYearIsNull() {
            addCriterion("JOUR_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andJourYearIsNotNull() {
            addCriterion("JOUR_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andJourYearEqualTo(String value) {
            addCriterion("JOUR_YEAR =", value, "jourYear");
            return (Criteria) this;
        }

        public Criteria andJourYearNotEqualTo(String value) {
            addCriterion("JOUR_YEAR <>", value, "jourYear");
            return (Criteria) this;
        }

        public Criteria andJourYearGreaterThan(String value) {
            addCriterion("JOUR_YEAR >", value, "jourYear");
            return (Criteria) this;
        }

        public Criteria andJourYearGreaterThanOrEqualTo(String value) {
            addCriterion("JOUR_YEAR >=", value, "jourYear");
            return (Criteria) this;
        }

        public Criteria andJourYearLessThan(String value) {
            addCriterion("JOUR_YEAR <", value, "jourYear");
            return (Criteria) this;
        }

        public Criteria andJourYearLessThanOrEqualTo(String value) {
            addCriterion("JOUR_YEAR <=", value, "jourYear");
            return (Criteria) this;
        }

        public Criteria andJourYearLike(String value) {
            addCriterion("JOUR_YEAR like", value, "jourYear");
            return (Criteria) this;
        }

        public Criteria andJourYearNotLike(String value) {
            addCriterion("JOUR_YEAR not like", value, "jourYear");
            return (Criteria) this;
        }

        public Criteria andJourYearIn(List<String> values) {
            addCriterion("JOUR_YEAR in", values, "jourYear");
            return (Criteria) this;
        }

        public Criteria andJourYearNotIn(List<String> values) {
            addCriterion("JOUR_YEAR not in", values, "jourYear");
            return (Criteria) this;
        }

        public Criteria andJourYearBetween(String value1, String value2) {
            addCriterion("JOUR_YEAR between", value1, value2, "jourYear");
            return (Criteria) this;
        }

        public Criteria andJourYearNotBetween(String value1, String value2) {
            addCriterion("JOUR_YEAR not between", value1, value2, "jourYear");
            return (Criteria) this;
        }

        public Criteria andSciIsNull() {
            addCriterion("SCI is null");
            return (Criteria) this;
        }

        public Criteria andSciIsNotNull() {
            addCriterion("SCI is not null");
            return (Criteria) this;
        }

        public Criteria andSciEqualTo(BigDecimal value) {
            addCriterion("SCI =", value, "sci");
            return (Criteria) this;
        }

        public Criteria andSciNotEqualTo(BigDecimal value) {
            addCriterion("SCI <>", value, "sci");
            return (Criteria) this;
        }

        public Criteria andSciGreaterThan(BigDecimal value) {
            addCriterion("SCI >", value, "sci");
            return (Criteria) this;
        }

        public Criteria andSciGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SCI >=", value, "sci");
            return (Criteria) this;
        }

        public Criteria andSciLessThan(BigDecimal value) {
            addCriterion("SCI <", value, "sci");
            return (Criteria) this;
        }

        public Criteria andSciLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SCI <=", value, "sci");
            return (Criteria) this;
        }

        public Criteria andSciIn(List<BigDecimal> values) {
            addCriterion("SCI in", values, "sci");
            return (Criteria) this;
        }

        public Criteria andSciNotIn(List<BigDecimal> values) {
            addCriterion("SCI not in", values, "sci");
            return (Criteria) this;
        }

        public Criteria andSciBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SCI between", value1, value2, "sci");
            return (Criteria) this;
        }

        public Criteria andSciNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SCI not between", value1, value2, "sci");
            return (Criteria) this;
        }

        public Criteria andIsPropProjIsNull() {
            addCriterion("IS_PROP_PROJ is null");
            return (Criteria) this;
        }

        public Criteria andIsPropProjIsNotNull() {
            addCriterion("IS_PROP_PROJ is not null");
            return (Criteria) this;
        }

        public Criteria andIsPropProjEqualTo(String value) {
            addCriterion("IS_PROP_PROJ =", value, "isPropProj");
            return (Criteria) this;
        }

        public Criteria andIsPropProjNotEqualTo(String value) {
            addCriterion("IS_PROP_PROJ <>", value, "isPropProj");
            return (Criteria) this;
        }

        public Criteria andIsPropProjGreaterThan(String value) {
            addCriterion("IS_PROP_PROJ >", value, "isPropProj");
            return (Criteria) this;
        }

        public Criteria andIsPropProjGreaterThanOrEqualTo(String value) {
            addCriterion("IS_PROP_PROJ >=", value, "isPropProj");
            return (Criteria) this;
        }

        public Criteria andIsPropProjLessThan(String value) {
            addCriterion("IS_PROP_PROJ <", value, "isPropProj");
            return (Criteria) this;
        }

        public Criteria andIsPropProjLessThanOrEqualTo(String value) {
            addCriterion("IS_PROP_PROJ <=", value, "isPropProj");
            return (Criteria) this;
        }

        public Criteria andIsPropProjLike(String value) {
            addCriterion("IS_PROP_PROJ like", value, "isPropProj");
            return (Criteria) this;
        }

        public Criteria andIsPropProjNotLike(String value) {
            addCriterion("IS_PROP_PROJ not like", value, "isPropProj");
            return (Criteria) this;
        }

        public Criteria andIsPropProjIn(List<String> values) {
            addCriterion("IS_PROP_PROJ in", values, "isPropProj");
            return (Criteria) this;
        }

        public Criteria andIsPropProjNotIn(List<String> values) {
            addCriterion("IS_PROP_PROJ not in", values, "isPropProj");
            return (Criteria) this;
        }

        public Criteria andIsPropProjBetween(String value1, String value2) {
            addCriterion("IS_PROP_PROJ between", value1, value2, "isPropProj");
            return (Criteria) this;
        }

        public Criteria andIsPropProjNotBetween(String value1, String value2) {
            addCriterion("IS_PROP_PROJ not between", value1, value2, "isPropProj");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowIsNull() {
            addCriterion("PROP_PROJ_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowIsNotNull() {
            addCriterion("PROP_PROJ_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowEqualTo(String value) {
            addCriterion("PROP_PROJ_FLOW =", value, "propProjFlow");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowNotEqualTo(String value) {
            addCriterion("PROP_PROJ_FLOW <>", value, "propProjFlow");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowGreaterThan(String value) {
            addCriterion("PROP_PROJ_FLOW >", value, "propProjFlow");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_PROJ_FLOW >=", value, "propProjFlow");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowLessThan(String value) {
            addCriterion("PROP_PROJ_FLOW <", value, "propProjFlow");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowLessThanOrEqualTo(String value) {
            addCriterion("PROP_PROJ_FLOW <=", value, "propProjFlow");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowLike(String value) {
            addCriterion("PROP_PROJ_FLOW like", value, "propProjFlow");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowNotLike(String value) {
            addCriterion("PROP_PROJ_FLOW not like", value, "propProjFlow");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowIn(List<String> values) {
            addCriterion("PROP_PROJ_FLOW in", values, "propProjFlow");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowNotIn(List<String> values) {
            addCriterion("PROP_PROJ_FLOW not in", values, "propProjFlow");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowBetween(String value1, String value2) {
            addCriterion("PROP_PROJ_FLOW between", value1, value2, "propProjFlow");
            return (Criteria) this;
        }

        public Criteria andPropProjFlowNotBetween(String value1, String value2) {
            addCriterion("PROP_PROJ_FLOW not between", value1, value2, "propProjFlow");
            return (Criteria) this;
        }

        public Criteria andPropProjNameIsNull() {
            addCriterion("PROP_PROJ_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPropProjNameIsNotNull() {
            addCriterion("PROP_PROJ_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPropProjNameEqualTo(String value) {
            addCriterion("PROP_PROJ_NAME =", value, "propProjName");
            return (Criteria) this;
        }

        public Criteria andPropProjNameNotEqualTo(String value) {
            addCriterion("PROP_PROJ_NAME <>", value, "propProjName");
            return (Criteria) this;
        }

        public Criteria andPropProjNameGreaterThan(String value) {
            addCriterion("PROP_PROJ_NAME >", value, "propProjName");
            return (Criteria) this;
        }

        public Criteria andPropProjNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROP_PROJ_NAME >=", value, "propProjName");
            return (Criteria) this;
        }

        public Criteria andPropProjNameLessThan(String value) {
            addCriterion("PROP_PROJ_NAME <", value, "propProjName");
            return (Criteria) this;
        }

        public Criteria andPropProjNameLessThanOrEqualTo(String value) {
            addCriterion("PROP_PROJ_NAME <=", value, "propProjName");
            return (Criteria) this;
        }

        public Criteria andPropProjNameLike(String value) {
            addCriterion("PROP_PROJ_NAME like", value, "propProjName");
            return (Criteria) this;
        }

        public Criteria andPropProjNameNotLike(String value) {
            addCriterion("PROP_PROJ_NAME not like", value, "propProjName");
            return (Criteria) this;
        }

        public Criteria andPropProjNameIn(List<String> values) {
            addCriterion("PROP_PROJ_NAME in", values, "propProjName");
            return (Criteria) this;
        }

        public Criteria andPropProjNameNotIn(List<String> values) {
            addCriterion("PROP_PROJ_NAME not in", values, "propProjName");
            return (Criteria) this;
        }

        public Criteria andPropProjNameBetween(String value1, String value2) {
            addCriterion("PROP_PROJ_NAME between", value1, value2, "propProjName");
            return (Criteria) this;
        }

        public Criteria andPropProjNameNotBetween(String value1, String value2) {
            addCriterion("PROP_PROJ_NAME not between", value1, value2, "propProjName");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearIsNull() {
            addCriterion("AUTHORIZE_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearIsNotNull() {
            addCriterion("AUTHORIZE_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearEqualTo(String value) {
            addCriterion("AUTHORIZE_YEAR =", value, "authorizeYear");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearNotEqualTo(String value) {
            addCriterion("AUTHORIZE_YEAR <>", value, "authorizeYear");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearGreaterThan(String value) {
            addCriterion("AUTHORIZE_YEAR >", value, "authorizeYear");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearGreaterThanOrEqualTo(String value) {
            addCriterion("AUTHORIZE_YEAR >=", value, "authorizeYear");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearLessThan(String value) {
            addCriterion("AUTHORIZE_YEAR <", value, "authorizeYear");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearLessThanOrEqualTo(String value) {
            addCriterion("AUTHORIZE_YEAR <=", value, "authorizeYear");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearLike(String value) {
            addCriterion("AUTHORIZE_YEAR like", value, "authorizeYear");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearNotLike(String value) {
            addCriterion("AUTHORIZE_YEAR not like", value, "authorizeYear");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearIn(List<String> values) {
            addCriterion("AUTHORIZE_YEAR in", values, "authorizeYear");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearNotIn(List<String> values) {
            addCriterion("AUTHORIZE_YEAR not in", values, "authorizeYear");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearBetween(String value1, String value2) {
            addCriterion("AUTHORIZE_YEAR between", value1, value2, "authorizeYear");
            return (Criteria) this;
        }

        public Criteria andAuthorizeYearNotBetween(String value1, String value2) {
            addCriterion("AUTHORIZE_YEAR not between", value1, value2, "authorizeYear");
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