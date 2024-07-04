package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class OscaSubjectStationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OscaSubjectStationExample() {
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

        public Criteria andStationFlowIsNull() {
            addCriterion("STATION_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andStationFlowIsNotNull() {
            addCriterion("STATION_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andStationFlowEqualTo(String value) {
            addCriterion("STATION_FLOW =", value, "stationFlow");
            return (Criteria) this;
        }

        public Criteria andStationFlowNotEqualTo(String value) {
            addCriterion("STATION_FLOW <>", value, "stationFlow");
            return (Criteria) this;
        }

        public Criteria andStationFlowGreaterThan(String value) {
            addCriterion("STATION_FLOW >", value, "stationFlow");
            return (Criteria) this;
        }

        public Criteria andStationFlowGreaterThanOrEqualTo(String value) {
            addCriterion("STATION_FLOW >=", value, "stationFlow");
            return (Criteria) this;
        }

        public Criteria andStationFlowLessThan(String value) {
            addCriterion("STATION_FLOW <", value, "stationFlow");
            return (Criteria) this;
        }

        public Criteria andStationFlowLessThanOrEqualTo(String value) {
            addCriterion("STATION_FLOW <=", value, "stationFlow");
            return (Criteria) this;
        }

        public Criteria andStationFlowLike(String value) {
            addCriterion("STATION_FLOW like", value, "stationFlow");
            return (Criteria) this;
        }

        public Criteria andStationFlowNotLike(String value) {
            addCriterion("STATION_FLOW not like", value, "stationFlow");
            return (Criteria) this;
        }

        public Criteria andStationFlowIn(List<String> values) {
            addCriterion("STATION_FLOW in", values, "stationFlow");
            return (Criteria) this;
        }

        public Criteria andStationFlowNotIn(List<String> values) {
            addCriterion("STATION_FLOW not in", values, "stationFlow");
            return (Criteria) this;
        }

        public Criteria andStationFlowBetween(String value1, String value2) {
            addCriterion("STATION_FLOW between", value1, value2, "stationFlow");
            return (Criteria) this;
        }

        public Criteria andStationFlowNotBetween(String value1, String value2) {
            addCriterion("STATION_FLOW not between", value1, value2, "stationFlow");
            return (Criteria) this;
        }

        public Criteria andStationNameIsNull() {
            addCriterion("STATION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStationNameIsNotNull() {
            addCriterion("STATION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStationNameEqualTo(String value) {
            addCriterion("STATION_NAME =", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameNotEqualTo(String value) {
            addCriterion("STATION_NAME <>", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameGreaterThan(String value) {
            addCriterion("STATION_NAME >", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameGreaterThanOrEqualTo(String value) {
            addCriterion("STATION_NAME >=", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameLessThan(String value) {
            addCriterion("STATION_NAME <", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameLessThanOrEqualTo(String value) {
            addCriterion("STATION_NAME <=", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameLike(String value) {
            addCriterion("STATION_NAME like", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameNotLike(String value) {
            addCriterion("STATION_NAME not like", value, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameIn(List<String> values) {
            addCriterion("STATION_NAME in", values, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameNotIn(List<String> values) {
            addCriterion("STATION_NAME not in", values, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameBetween(String value1, String value2) {
            addCriterion("STATION_NAME between", value1, value2, "stationName");
            return (Criteria) this;
        }

        public Criteria andStationNameNotBetween(String value1, String value2) {
            addCriterion("STATION_NAME not between", value1, value2, "stationName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameIsNull() {
            addCriterion("SUBJECT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSubjectNameIsNotNull() {
            addCriterion("SUBJECT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectNameEqualTo(String value) {
            addCriterion("SUBJECT_NAME =", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotEqualTo(String value) {
            addCriterion("SUBJECT_NAME <>", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameGreaterThan(String value) {
            addCriterion("SUBJECT_NAME >", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_NAME >=", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameLessThan(String value) {
            addCriterion("SUBJECT_NAME <", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_NAME <=", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameLike(String value) {
            addCriterion("SUBJECT_NAME like", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotLike(String value) {
            addCriterion("SUBJECT_NAME not like", value, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameIn(List<String> values) {
            addCriterion("SUBJECT_NAME in", values, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotIn(List<String> values) {
            addCriterion("SUBJECT_NAME not in", values, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameBetween(String value1, String value2) {
            addCriterion("SUBJECT_NAME between", value1, value2, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectNameNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_NAME not between", value1, value2, "subjectName");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowIsNull() {
            addCriterion("SUBJECT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowIsNotNull() {
            addCriterion("SUBJECT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowEqualTo(String value) {
            addCriterion("SUBJECT_FLOW =", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowNotEqualTo(String value) {
            addCriterion("SUBJECT_FLOW <>", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowGreaterThan(String value) {
            addCriterion("SUBJECT_FLOW >", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowGreaterThanOrEqualTo(String value) {
            addCriterion("SUBJECT_FLOW >=", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowLessThan(String value) {
            addCriterion("SUBJECT_FLOW <", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowLessThanOrEqualTo(String value) {
            addCriterion("SUBJECT_FLOW <=", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowLike(String value) {
            addCriterion("SUBJECT_FLOW like", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowNotLike(String value) {
            addCriterion("SUBJECT_FLOW not like", value, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowIn(List<String> values) {
            addCriterion("SUBJECT_FLOW in", values, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowNotIn(List<String> values) {
            addCriterion("SUBJECT_FLOW not in", values, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowBetween(String value1, String value2) {
            addCriterion("SUBJECT_FLOW between", value1, value2, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andSubjectFlowNotBetween(String value1, String value2) {
            addCriterion("SUBJECT_FLOW not between", value1, value2, "subjectFlow");
            return (Criteria) this;
        }

        public Criteria andExaminedContentIsNull() {
            addCriterion("EXAMINED_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andExaminedContentIsNotNull() {
            addCriterion("EXAMINED_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andExaminedContentEqualTo(String value) {
            addCriterion("EXAMINED_CONTENT =", value, "examinedContent");
            return (Criteria) this;
        }

        public Criteria andExaminedContentNotEqualTo(String value) {
            addCriterion("EXAMINED_CONTENT <>", value, "examinedContent");
            return (Criteria) this;
        }

        public Criteria andExaminedContentGreaterThan(String value) {
            addCriterion("EXAMINED_CONTENT >", value, "examinedContent");
            return (Criteria) this;
        }

        public Criteria andExaminedContentGreaterThanOrEqualTo(String value) {
            addCriterion("EXAMINED_CONTENT >=", value, "examinedContent");
            return (Criteria) this;
        }

        public Criteria andExaminedContentLessThan(String value) {
            addCriterion("EXAMINED_CONTENT <", value, "examinedContent");
            return (Criteria) this;
        }

        public Criteria andExaminedContentLessThanOrEqualTo(String value) {
            addCriterion("EXAMINED_CONTENT <=", value, "examinedContent");
            return (Criteria) this;
        }

        public Criteria andExaminedContentLike(String value) {
            addCriterion("EXAMINED_CONTENT like", value, "examinedContent");
            return (Criteria) this;
        }

        public Criteria andExaminedContentNotLike(String value) {
            addCriterion("EXAMINED_CONTENT not like", value, "examinedContent");
            return (Criteria) this;
        }

        public Criteria andExaminedContentIn(List<String> values) {
            addCriterion("EXAMINED_CONTENT in", values, "examinedContent");
            return (Criteria) this;
        }

        public Criteria andExaminedContentNotIn(List<String> values) {
            addCriterion("EXAMINED_CONTENT not in", values, "examinedContent");
            return (Criteria) this;
        }

        public Criteria andExaminedContentBetween(String value1, String value2) {
            addCriterion("EXAMINED_CONTENT between", value1, value2, "examinedContent");
            return (Criteria) this;
        }

        public Criteria andExaminedContentNotBetween(String value1, String value2) {
            addCriterion("EXAMINED_CONTENT not between", value1, value2, "examinedContent");
            return (Criteria) this;
        }

        public Criteria andStationScoreIsNull() {
            addCriterion("STATION_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andStationScoreIsNotNull() {
            addCriterion("STATION_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andStationScoreEqualTo(Integer value) {
            addCriterion("STATION_SCORE =", value, "stationScore");
            return (Criteria) this;
        }

        public Criteria andStationScoreNotEqualTo(Integer value) {
            addCriterion("STATION_SCORE <>", value, "stationScore");
            return (Criteria) this;
        }

        public Criteria andStationScoreGreaterThan(Integer value) {
            addCriterion("STATION_SCORE >", value, "stationScore");
            return (Criteria) this;
        }

        public Criteria andStationScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("STATION_SCORE >=", value, "stationScore");
            return (Criteria) this;
        }

        public Criteria andStationScoreLessThan(Integer value) {
            addCriterion("STATION_SCORE <", value, "stationScore");
            return (Criteria) this;
        }

        public Criteria andStationScoreLessThanOrEqualTo(Integer value) {
            addCriterion("STATION_SCORE <=", value, "stationScore");
            return (Criteria) this;
        }

        public Criteria andStationScoreIn(List<Integer> values) {
            addCriterion("STATION_SCORE in", values, "stationScore");
            return (Criteria) this;
        }

        public Criteria andStationScoreNotIn(List<Integer> values) {
            addCriterion("STATION_SCORE not in", values, "stationScore");
            return (Criteria) this;
        }

        public Criteria andStationScoreBetween(Integer value1, Integer value2) {
            addCriterion("STATION_SCORE between", value1, value2, "stationScore");
            return (Criteria) this;
        }

        public Criteria andStationScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("STATION_SCORE not between", value1, value2, "stationScore");
            return (Criteria) this;
        }

        public Criteria andOrdinalIsNull() {
            addCriterion("ORDINAL is null");
            return (Criteria) this;
        }

        public Criteria andOrdinalIsNotNull() {
            addCriterion("ORDINAL is not null");
            return (Criteria) this;
        }

        public Criteria andOrdinalEqualTo(Integer value) {
            addCriterion("ORDINAL =", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotEqualTo(Integer value) {
            addCriterion("ORDINAL <>", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalGreaterThan(Integer value) {
            addCriterion("ORDINAL >", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalGreaterThanOrEqualTo(Integer value) {
            addCriterion("ORDINAL >=", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalLessThan(Integer value) {
            addCriterion("ORDINAL <", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalLessThanOrEqualTo(Integer value) {
            addCriterion("ORDINAL <=", value, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalIn(List<Integer> values) {
            addCriterion("ORDINAL in", values, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotIn(List<Integer> values) {
            addCriterion("ORDINAL not in", values, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalBetween(Integer value1, Integer value2) {
            addCriterion("ORDINAL between", value1, value2, "ordinal");
            return (Criteria) this;
        }

        public Criteria andOrdinalNotBetween(Integer value1, Integer value2) {
            addCriterion("ORDINAL not between", value1, value2, "ordinal");
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

        public Criteria andPartNameIsNull() {
            addCriterion("PART_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPartNameIsNotNull() {
            addCriterion("PART_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPartNameEqualTo(String value) {
            addCriterion("PART_NAME =", value, "partName");
            return (Criteria) this;
        }

        public Criteria andPartNameNotEqualTo(String value) {
            addCriterion("PART_NAME <>", value, "partName");
            return (Criteria) this;
        }

        public Criteria andPartNameGreaterThan(String value) {
            addCriterion("PART_NAME >", value, "partName");
            return (Criteria) this;
        }

        public Criteria andPartNameGreaterThanOrEqualTo(String value) {
            addCriterion("PART_NAME >=", value, "partName");
            return (Criteria) this;
        }

        public Criteria andPartNameLessThan(String value) {
            addCriterion("PART_NAME <", value, "partName");
            return (Criteria) this;
        }

        public Criteria andPartNameLessThanOrEqualTo(String value) {
            addCriterion("PART_NAME <=", value, "partName");
            return (Criteria) this;
        }

        public Criteria andPartNameLike(String value) {
            addCriterion("PART_NAME like", value, "partName");
            return (Criteria) this;
        }

        public Criteria andPartNameNotLike(String value) {
            addCriterion("PART_NAME not like", value, "partName");
            return (Criteria) this;
        }

        public Criteria andPartNameIn(List<String> values) {
            addCriterion("PART_NAME in", values, "partName");
            return (Criteria) this;
        }

        public Criteria andPartNameNotIn(List<String> values) {
            addCriterion("PART_NAME not in", values, "partName");
            return (Criteria) this;
        }

        public Criteria andPartNameBetween(String value1, String value2) {
            addCriterion("PART_NAME between", value1, value2, "partName");
            return (Criteria) this;
        }

        public Criteria andPartNameNotBetween(String value1, String value2) {
            addCriterion("PART_NAME not between", value1, value2, "partName");
            return (Criteria) this;
        }

        public Criteria andPartFlowIsNull() {
            addCriterion("PART_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andPartFlowIsNotNull() {
            addCriterion("PART_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andPartFlowEqualTo(String value) {
            addCriterion("PART_FLOW =", value, "partFlow");
            return (Criteria) this;
        }

        public Criteria andPartFlowNotEqualTo(String value) {
            addCriterion("PART_FLOW <>", value, "partFlow");
            return (Criteria) this;
        }

        public Criteria andPartFlowGreaterThan(String value) {
            addCriterion("PART_FLOW >", value, "partFlow");
            return (Criteria) this;
        }

        public Criteria andPartFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PART_FLOW >=", value, "partFlow");
            return (Criteria) this;
        }

        public Criteria andPartFlowLessThan(String value) {
            addCriterion("PART_FLOW <", value, "partFlow");
            return (Criteria) this;
        }

        public Criteria andPartFlowLessThanOrEqualTo(String value) {
            addCriterion("PART_FLOW <=", value, "partFlow");
            return (Criteria) this;
        }

        public Criteria andPartFlowLike(String value) {
            addCriterion("PART_FLOW like", value, "partFlow");
            return (Criteria) this;
        }

        public Criteria andPartFlowNotLike(String value) {
            addCriterion("PART_FLOW not like", value, "partFlow");
            return (Criteria) this;
        }

        public Criteria andPartFlowIn(List<String> values) {
            addCriterion("PART_FLOW in", values, "partFlow");
            return (Criteria) this;
        }

        public Criteria andPartFlowNotIn(List<String> values) {
            addCriterion("PART_FLOW not in", values, "partFlow");
            return (Criteria) this;
        }

        public Criteria andPartFlowBetween(String value1, String value2) {
            addCriterion("PART_FLOW between", value1, value2, "partFlow");
            return (Criteria) this;
        }

        public Criteria andPartFlowNotBetween(String value1, String value2) {
            addCriterion("PART_FLOW not between", value1, value2, "partFlow");
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