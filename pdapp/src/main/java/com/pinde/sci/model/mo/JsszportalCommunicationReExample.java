package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class JsszportalCommunicationReExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JsszportalCommunicationReExample() {
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

        public Criteria andMainFlowIsNull() {
            addCriterion("MAIN_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andMainFlowIsNotNull() {
            addCriterion("MAIN_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andMainFlowEqualTo(String value) {
            addCriterion("MAIN_FLOW =", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowNotEqualTo(String value) {
            addCriterion("MAIN_FLOW <>", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowGreaterThan(String value) {
            addCriterion("MAIN_FLOW >", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowGreaterThanOrEqualTo(String value) {
            addCriterion("MAIN_FLOW >=", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowLessThan(String value) {
            addCriterion("MAIN_FLOW <", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowLessThanOrEqualTo(String value) {
            addCriterion("MAIN_FLOW <=", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowLike(String value) {
            addCriterion("MAIN_FLOW like", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowNotLike(String value) {
            addCriterion("MAIN_FLOW not like", value, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowIn(List<String> values) {
            addCriterion("MAIN_FLOW in", values, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowNotIn(List<String> values) {
            addCriterion("MAIN_FLOW not in", values, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowBetween(String value1, String value2) {
            addCriterion("MAIN_FLOW between", value1, value2, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andMainFlowNotBetween(String value1, String value2) {
            addCriterion("MAIN_FLOW not between", value1, value2, "mainFlow");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdIsNull() {
            addCriterion("DISEASE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdIsNotNull() {
            addCriterion("DISEASE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdEqualTo(String value) {
            addCriterion("DISEASE_ID =", value, "diseaseId");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdNotEqualTo(String value) {
            addCriterion("DISEASE_ID <>", value, "diseaseId");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdGreaterThan(String value) {
            addCriterion("DISEASE_ID >", value, "diseaseId");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdGreaterThanOrEqualTo(String value) {
            addCriterion("DISEASE_ID >=", value, "diseaseId");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdLessThan(String value) {
            addCriterion("DISEASE_ID <", value, "diseaseId");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdLessThanOrEqualTo(String value) {
            addCriterion("DISEASE_ID <=", value, "diseaseId");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdLike(String value) {
            addCriterion("DISEASE_ID like", value, "diseaseId");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdNotLike(String value) {
            addCriterion("DISEASE_ID not like", value, "diseaseId");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdIn(List<String> values) {
            addCriterion("DISEASE_ID in", values, "diseaseId");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdNotIn(List<String> values) {
            addCriterion("DISEASE_ID not in", values, "diseaseId");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdBetween(String value1, String value2) {
            addCriterion("DISEASE_ID between", value1, value2, "diseaseId");
            return (Criteria) this;
        }

        public Criteria andDiseaseIdNotBetween(String value1, String value2) {
            addCriterion("DISEASE_ID not between", value1, value2, "diseaseId");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameIsNull() {
            addCriterion("DISEASE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameIsNotNull() {
            addCriterion("DISEASE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameEqualTo(String value) {
            addCriterion("DISEASE_NAME =", value, "diseaseName");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameNotEqualTo(String value) {
            addCriterion("DISEASE_NAME <>", value, "diseaseName");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameGreaterThan(String value) {
            addCriterion("DISEASE_NAME >", value, "diseaseName");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameGreaterThanOrEqualTo(String value) {
            addCriterion("DISEASE_NAME >=", value, "diseaseName");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameLessThan(String value) {
            addCriterion("DISEASE_NAME <", value, "diseaseName");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameLessThanOrEqualTo(String value) {
            addCriterion("DISEASE_NAME <=", value, "diseaseName");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameLike(String value) {
            addCriterion("DISEASE_NAME like", value, "diseaseName");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameNotLike(String value) {
            addCriterion("DISEASE_NAME not like", value, "diseaseName");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameIn(List<String> values) {
            addCriterion("DISEASE_NAME in", values, "diseaseName");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameNotIn(List<String> values) {
            addCriterion("DISEASE_NAME not in", values, "diseaseName");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameBetween(String value1, String value2) {
            addCriterion("DISEASE_NAME between", value1, value2, "diseaseName");
            return (Criteria) this;
        }

        public Criteria andDiseaseNameNotBetween(String value1, String value2) {
            addCriterion("DISEASE_NAME not between", value1, value2, "diseaseName");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIsNull() {
            addCriterion("UPLOAD_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIsNotNull() {
            addCriterion("UPLOAD_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUploadTimeEqualTo(String value) {
            addCriterion("UPLOAD_TIME =", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotEqualTo(String value) {
            addCriterion("UPLOAD_TIME <>", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeGreaterThan(String value) {
            addCriterion("UPLOAD_TIME >", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeGreaterThanOrEqualTo(String value) {
            addCriterion("UPLOAD_TIME >=", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLessThan(String value) {
            addCriterion("UPLOAD_TIME <", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLessThanOrEqualTo(String value) {
            addCriterion("UPLOAD_TIME <=", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLike(String value) {
            addCriterion("UPLOAD_TIME like", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotLike(String value) {
            addCriterion("UPLOAD_TIME not like", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIn(List<String> values) {
            addCriterion("UPLOAD_TIME in", values, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotIn(List<String> values) {
            addCriterion("UPLOAD_TIME not in", values, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeBetween(String value1, String value2) {
            addCriterion("UPLOAD_TIME between", value1, value2, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotBetween(String value1, String value2) {
            addCriterion("UPLOAD_TIME not between", value1, value2, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("TITLE is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("TITLE =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("TITLE <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("TITLE >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("TITLE <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("TITLE <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("TITLE like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("TITLE not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("TITLE in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("TITLE not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("TITLE between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("TITLE not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andUploadUserFlowIsNull() {
            addCriterion("UPLOAD_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andUploadUserFlowIsNotNull() {
            addCriterion("UPLOAD_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andUploadUserFlowEqualTo(String value) {
            addCriterion("UPLOAD_USER_FLOW =", value, "uploadUserFlow");
            return (Criteria) this;
        }

        public Criteria andUploadUserFlowNotEqualTo(String value) {
            addCriterion("UPLOAD_USER_FLOW <>", value, "uploadUserFlow");
            return (Criteria) this;
        }

        public Criteria andUploadUserFlowGreaterThan(String value) {
            addCriterion("UPLOAD_USER_FLOW >", value, "uploadUserFlow");
            return (Criteria) this;
        }

        public Criteria andUploadUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("UPLOAD_USER_FLOW >=", value, "uploadUserFlow");
            return (Criteria) this;
        }

        public Criteria andUploadUserFlowLessThan(String value) {
            addCriterion("UPLOAD_USER_FLOW <", value, "uploadUserFlow");
            return (Criteria) this;
        }

        public Criteria andUploadUserFlowLessThanOrEqualTo(String value) {
            addCriterion("UPLOAD_USER_FLOW <=", value, "uploadUserFlow");
            return (Criteria) this;
        }

        public Criteria andUploadUserFlowLike(String value) {
            addCriterion("UPLOAD_USER_FLOW like", value, "uploadUserFlow");
            return (Criteria) this;
        }

        public Criteria andUploadUserFlowNotLike(String value) {
            addCriterion("UPLOAD_USER_FLOW not like", value, "uploadUserFlow");
            return (Criteria) this;
        }

        public Criteria andUploadUserFlowIn(List<String> values) {
            addCriterion("UPLOAD_USER_FLOW in", values, "uploadUserFlow");
            return (Criteria) this;
        }

        public Criteria andUploadUserFlowNotIn(List<String> values) {
            addCriterion("UPLOAD_USER_FLOW not in", values, "uploadUserFlow");
            return (Criteria) this;
        }

        public Criteria andUploadUserFlowBetween(String value1, String value2) {
            addCriterion("UPLOAD_USER_FLOW between", value1, value2, "uploadUserFlow");
            return (Criteria) this;
        }

        public Criteria andUploadUserFlowNotBetween(String value1, String value2) {
            addCriterion("UPLOAD_USER_FLOW not between", value1, value2, "uploadUserFlow");
            return (Criteria) this;
        }

        public Criteria andUploadUserNameIsNull() {
            addCriterion("UPLOAD_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUploadUserNameIsNotNull() {
            addCriterion("UPLOAD_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUploadUserNameEqualTo(String value) {
            addCriterion("UPLOAD_USER_NAME =", value, "uploadUserName");
            return (Criteria) this;
        }

        public Criteria andUploadUserNameNotEqualTo(String value) {
            addCriterion("UPLOAD_USER_NAME <>", value, "uploadUserName");
            return (Criteria) this;
        }

        public Criteria andUploadUserNameGreaterThan(String value) {
            addCriterion("UPLOAD_USER_NAME >", value, "uploadUserName");
            return (Criteria) this;
        }

        public Criteria andUploadUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("UPLOAD_USER_NAME >=", value, "uploadUserName");
            return (Criteria) this;
        }

        public Criteria andUploadUserNameLessThan(String value) {
            addCriterion("UPLOAD_USER_NAME <", value, "uploadUserName");
            return (Criteria) this;
        }

        public Criteria andUploadUserNameLessThanOrEqualTo(String value) {
            addCriterion("UPLOAD_USER_NAME <=", value, "uploadUserName");
            return (Criteria) this;
        }

        public Criteria andUploadUserNameLike(String value) {
            addCriterion("UPLOAD_USER_NAME like", value, "uploadUserName");
            return (Criteria) this;
        }

        public Criteria andUploadUserNameNotLike(String value) {
            addCriterion("UPLOAD_USER_NAME not like", value, "uploadUserName");
            return (Criteria) this;
        }

        public Criteria andUploadUserNameIn(List<String> values) {
            addCriterion("UPLOAD_USER_NAME in", values, "uploadUserName");
            return (Criteria) this;
        }

        public Criteria andUploadUserNameNotIn(List<String> values) {
            addCriterion("UPLOAD_USER_NAME not in", values, "uploadUserName");
            return (Criteria) this;
        }

        public Criteria andUploadUserNameBetween(String value1, String value2) {
            addCriterion("UPLOAD_USER_NAME between", value1, value2, "uploadUserName");
            return (Criteria) this;
        }

        public Criteria andUploadUserNameNotBetween(String value1, String value2) {
            addCriterion("UPLOAD_USER_NAME not between", value1, value2, "uploadUserName");
            return (Criteria) this;
        }

        public Criteria andReplayTimesIsNull() {
            addCriterion("REPLAY_TIMES is null");
            return (Criteria) this;
        }

        public Criteria andReplayTimesIsNotNull() {
            addCriterion("REPLAY_TIMES is not null");
            return (Criteria) this;
        }

        public Criteria andReplayTimesEqualTo(String value) {
            addCriterion("REPLAY_TIMES =", value, "replayTimes");
            return (Criteria) this;
        }

        public Criteria andReplayTimesNotEqualTo(String value) {
            addCriterion("REPLAY_TIMES <>", value, "replayTimes");
            return (Criteria) this;
        }

        public Criteria andReplayTimesGreaterThan(String value) {
            addCriterion("REPLAY_TIMES >", value, "replayTimes");
            return (Criteria) this;
        }

        public Criteria andReplayTimesGreaterThanOrEqualTo(String value) {
            addCriterion("REPLAY_TIMES >=", value, "replayTimes");
            return (Criteria) this;
        }

        public Criteria andReplayTimesLessThan(String value) {
            addCriterion("REPLAY_TIMES <", value, "replayTimes");
            return (Criteria) this;
        }

        public Criteria andReplayTimesLessThanOrEqualTo(String value) {
            addCriterion("REPLAY_TIMES <=", value, "replayTimes");
            return (Criteria) this;
        }

        public Criteria andReplayTimesLike(String value) {
            addCriterion("REPLAY_TIMES like", value, "replayTimes");
            return (Criteria) this;
        }

        public Criteria andReplayTimesNotLike(String value) {
            addCriterion("REPLAY_TIMES not like", value, "replayTimes");
            return (Criteria) this;
        }

        public Criteria andReplayTimesIn(List<String> values) {
            addCriterion("REPLAY_TIMES in", values, "replayTimes");
            return (Criteria) this;
        }

        public Criteria andReplayTimesNotIn(List<String> values) {
            addCriterion("REPLAY_TIMES not in", values, "replayTimes");
            return (Criteria) this;
        }

        public Criteria andReplayTimesBetween(String value1, String value2) {
            addCriterion("REPLAY_TIMES between", value1, value2, "replayTimes");
            return (Criteria) this;
        }

        public Criteria andReplayTimesNotBetween(String value1, String value2) {
            addCriterion("REPLAY_TIMES not between", value1, value2, "replayTimes");
            return (Criteria) this;
        }

        public Criteria andNewestReplayTimeIsNull() {
            addCriterion("NEWEST_REPLAY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andNewestReplayTimeIsNotNull() {
            addCriterion("NEWEST_REPLAY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andNewestReplayTimeEqualTo(String value) {
            addCriterion("NEWEST_REPLAY_TIME =", value, "newestReplayTime");
            return (Criteria) this;
        }

        public Criteria andNewestReplayTimeNotEqualTo(String value) {
            addCriterion("NEWEST_REPLAY_TIME <>", value, "newestReplayTime");
            return (Criteria) this;
        }

        public Criteria andNewestReplayTimeGreaterThan(String value) {
            addCriterion("NEWEST_REPLAY_TIME >", value, "newestReplayTime");
            return (Criteria) this;
        }

        public Criteria andNewestReplayTimeGreaterThanOrEqualTo(String value) {
            addCriterion("NEWEST_REPLAY_TIME >=", value, "newestReplayTime");
            return (Criteria) this;
        }

        public Criteria andNewestReplayTimeLessThan(String value) {
            addCriterion("NEWEST_REPLAY_TIME <", value, "newestReplayTime");
            return (Criteria) this;
        }

        public Criteria andNewestReplayTimeLessThanOrEqualTo(String value) {
            addCriterion("NEWEST_REPLAY_TIME <=", value, "newestReplayTime");
            return (Criteria) this;
        }

        public Criteria andNewestReplayTimeLike(String value) {
            addCriterion("NEWEST_REPLAY_TIME like", value, "newestReplayTime");
            return (Criteria) this;
        }

        public Criteria andNewestReplayTimeNotLike(String value) {
            addCriterion("NEWEST_REPLAY_TIME not like", value, "newestReplayTime");
            return (Criteria) this;
        }

        public Criteria andNewestReplayTimeIn(List<String> values) {
            addCriterion("NEWEST_REPLAY_TIME in", values, "newestReplayTime");
            return (Criteria) this;
        }

        public Criteria andNewestReplayTimeNotIn(List<String> values) {
            addCriterion("NEWEST_REPLAY_TIME not in", values, "newestReplayTime");
            return (Criteria) this;
        }

        public Criteria andNewestReplayTimeBetween(String value1, String value2) {
            addCriterion("NEWEST_REPLAY_TIME between", value1, value2, "newestReplayTime");
            return (Criteria) this;
        }

        public Criteria andNewestReplayTimeNotBetween(String value1, String value2) {
            addCriterion("NEWEST_REPLAY_TIME not between", value1, value2, "newestReplayTime");
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