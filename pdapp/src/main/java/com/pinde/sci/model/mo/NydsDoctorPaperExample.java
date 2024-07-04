package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class NydsDoctorPaperExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NydsDoctorPaperExample() {
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

        public Criteria andPaperTitleIsNull() {
            addCriterion("PAPER_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andPaperTitleIsNotNull() {
            addCriterion("PAPER_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andPaperTitleEqualTo(String value) {
            addCriterion("PAPER_TITLE =", value, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleNotEqualTo(String value) {
            addCriterion("PAPER_TITLE <>", value, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleGreaterThan(String value) {
            addCriterion("PAPER_TITLE >", value, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER_TITLE >=", value, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleLessThan(String value) {
            addCriterion("PAPER_TITLE <", value, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleLessThanOrEqualTo(String value) {
            addCriterion("PAPER_TITLE <=", value, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleLike(String value) {
            addCriterion("PAPER_TITLE like", value, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleNotLike(String value) {
            addCriterion("PAPER_TITLE not like", value, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleIn(List<String> values) {
            addCriterion("PAPER_TITLE in", values, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleNotIn(List<String> values) {
            addCriterion("PAPER_TITLE not in", values, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleBetween(String value1, String value2) {
            addCriterion("PAPER_TITLE between", value1, value2, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPaperTitleNotBetween(String value1, String value2) {
            addCriterion("PAPER_TITLE not between", value1, value2, "paperTitle");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameIsNull() {
            addCriterion("PERIODICAL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameIsNotNull() {
            addCriterion("PERIODICAL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameEqualTo(String value) {
            addCriterion("PERIODICAL_NAME =", value, "periodicalName");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameNotEqualTo(String value) {
            addCriterion("PERIODICAL_NAME <>", value, "periodicalName");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameGreaterThan(String value) {
            addCriterion("PERIODICAL_NAME >", value, "periodicalName");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameGreaterThanOrEqualTo(String value) {
            addCriterion("PERIODICAL_NAME >=", value, "periodicalName");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameLessThan(String value) {
            addCriterion("PERIODICAL_NAME <", value, "periodicalName");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameLessThanOrEqualTo(String value) {
            addCriterion("PERIODICAL_NAME <=", value, "periodicalName");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameLike(String value) {
            addCriterion("PERIODICAL_NAME like", value, "periodicalName");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameNotLike(String value) {
            addCriterion("PERIODICAL_NAME not like", value, "periodicalName");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameIn(List<String> values) {
            addCriterion("PERIODICAL_NAME in", values, "periodicalName");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameNotIn(List<String> values) {
            addCriterion("PERIODICAL_NAME not in", values, "periodicalName");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameBetween(String value1, String value2) {
            addCriterion("PERIODICAL_NAME between", value1, value2, "periodicalName");
            return (Criteria) this;
        }

        public Criteria andPeriodicalNameNotBetween(String value1, String value2) {
            addCriterion("PERIODICAL_NAME not between", value1, value2, "periodicalName");
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

        public Criteria andInfluenceFactorIsNull() {
            addCriterion("INFLUENCE_FACTOR is null");
            return (Criteria) this;
        }

        public Criteria andInfluenceFactorIsNotNull() {
            addCriterion("INFLUENCE_FACTOR is not null");
            return (Criteria) this;
        }

        public Criteria andInfluenceFactorEqualTo(String value) {
            addCriterion("INFLUENCE_FACTOR =", value, "influenceFactor");
            return (Criteria) this;
        }

        public Criteria andInfluenceFactorNotEqualTo(String value) {
            addCriterion("INFLUENCE_FACTOR <>", value, "influenceFactor");
            return (Criteria) this;
        }

        public Criteria andInfluenceFactorGreaterThan(String value) {
            addCriterion("INFLUENCE_FACTOR >", value, "influenceFactor");
            return (Criteria) this;
        }

        public Criteria andInfluenceFactorGreaterThanOrEqualTo(String value) {
            addCriterion("INFLUENCE_FACTOR >=", value, "influenceFactor");
            return (Criteria) this;
        }

        public Criteria andInfluenceFactorLessThan(String value) {
            addCriterion("INFLUENCE_FACTOR <", value, "influenceFactor");
            return (Criteria) this;
        }

        public Criteria andInfluenceFactorLessThanOrEqualTo(String value) {
            addCriterion("INFLUENCE_FACTOR <=", value, "influenceFactor");
            return (Criteria) this;
        }

        public Criteria andInfluenceFactorLike(String value) {
            addCriterion("INFLUENCE_FACTOR like", value, "influenceFactor");
            return (Criteria) this;
        }

        public Criteria andInfluenceFactorNotLike(String value) {
            addCriterion("INFLUENCE_FACTOR not like", value, "influenceFactor");
            return (Criteria) this;
        }

        public Criteria andInfluenceFactorIn(List<String> values) {
            addCriterion("INFLUENCE_FACTOR in", values, "influenceFactor");
            return (Criteria) this;
        }

        public Criteria andInfluenceFactorNotIn(List<String> values) {
            addCriterion("INFLUENCE_FACTOR not in", values, "influenceFactor");
            return (Criteria) this;
        }

        public Criteria andInfluenceFactorBetween(String value1, String value2) {
            addCriterion("INFLUENCE_FACTOR between", value1, value2, "influenceFactor");
            return (Criteria) this;
        }

        public Criteria andInfluenceFactorNotBetween(String value1, String value2) {
            addCriterion("INFLUENCE_FACTOR not between", value1, value2, "influenceFactor");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionIdIsNull() {
            addCriterion("JCR_PARTITION_ID is null");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionIdIsNotNull() {
            addCriterion("JCR_PARTITION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionIdEqualTo(String value) {
            addCriterion("JCR_PARTITION_ID =", value, "jcrPartitionId");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionIdNotEqualTo(String value) {
            addCriterion("JCR_PARTITION_ID <>", value, "jcrPartitionId");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionIdGreaterThan(String value) {
            addCriterion("JCR_PARTITION_ID >", value, "jcrPartitionId");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionIdGreaterThanOrEqualTo(String value) {
            addCriterion("JCR_PARTITION_ID >=", value, "jcrPartitionId");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionIdLessThan(String value) {
            addCriterion("JCR_PARTITION_ID <", value, "jcrPartitionId");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionIdLessThanOrEqualTo(String value) {
            addCriterion("JCR_PARTITION_ID <=", value, "jcrPartitionId");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionIdLike(String value) {
            addCriterion("JCR_PARTITION_ID like", value, "jcrPartitionId");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionIdNotLike(String value) {
            addCriterion("JCR_PARTITION_ID not like", value, "jcrPartitionId");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionIdIn(List<String> values) {
            addCriterion("JCR_PARTITION_ID in", values, "jcrPartitionId");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionIdNotIn(List<String> values) {
            addCriterion("JCR_PARTITION_ID not in", values, "jcrPartitionId");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionIdBetween(String value1, String value2) {
            addCriterion("JCR_PARTITION_ID between", value1, value2, "jcrPartitionId");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionIdNotBetween(String value1, String value2) {
            addCriterion("JCR_PARTITION_ID not between", value1, value2, "jcrPartitionId");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionNameIsNull() {
            addCriterion("JCR_PARTITION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionNameIsNotNull() {
            addCriterion("JCR_PARTITION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionNameEqualTo(String value) {
            addCriterion("JCR_PARTITION_NAME =", value, "jcrPartitionName");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionNameNotEqualTo(String value) {
            addCriterion("JCR_PARTITION_NAME <>", value, "jcrPartitionName");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionNameGreaterThan(String value) {
            addCriterion("JCR_PARTITION_NAME >", value, "jcrPartitionName");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionNameGreaterThanOrEqualTo(String value) {
            addCriterion("JCR_PARTITION_NAME >=", value, "jcrPartitionName");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionNameLessThan(String value) {
            addCriterion("JCR_PARTITION_NAME <", value, "jcrPartitionName");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionNameLessThanOrEqualTo(String value) {
            addCriterion("JCR_PARTITION_NAME <=", value, "jcrPartitionName");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionNameLike(String value) {
            addCriterion("JCR_PARTITION_NAME like", value, "jcrPartitionName");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionNameNotLike(String value) {
            addCriterion("JCR_PARTITION_NAME not like", value, "jcrPartitionName");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionNameIn(List<String> values) {
            addCriterion("JCR_PARTITION_NAME in", values, "jcrPartitionName");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionNameNotIn(List<String> values) {
            addCriterion("JCR_PARTITION_NAME not in", values, "jcrPartitionName");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionNameBetween(String value1, String value2) {
            addCriterion("JCR_PARTITION_NAME between", value1, value2, "jcrPartitionName");
            return (Criteria) this;
        }

        public Criteria andJcrPartitionNameNotBetween(String value1, String value2) {
            addCriterion("JCR_PARTITION_NAME not between", value1, value2, "jcrPartitionName");
            return (Criteria) this;
        }

        public Criteria andPeriodicalPicUrlIsNull() {
            addCriterion("PERIODICAL_PIC_URL is null");
            return (Criteria) this;
        }

        public Criteria andPeriodicalPicUrlIsNotNull() {
            addCriterion("PERIODICAL_PIC_URL is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodicalPicUrlEqualTo(String value) {
            addCriterion("PERIODICAL_PIC_URL =", value, "periodicalPicUrl");
            return (Criteria) this;
        }

        public Criteria andPeriodicalPicUrlNotEqualTo(String value) {
            addCriterion("PERIODICAL_PIC_URL <>", value, "periodicalPicUrl");
            return (Criteria) this;
        }

        public Criteria andPeriodicalPicUrlGreaterThan(String value) {
            addCriterion("PERIODICAL_PIC_URL >", value, "periodicalPicUrl");
            return (Criteria) this;
        }

        public Criteria andPeriodicalPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("PERIODICAL_PIC_URL >=", value, "periodicalPicUrl");
            return (Criteria) this;
        }

        public Criteria andPeriodicalPicUrlLessThan(String value) {
            addCriterion("PERIODICAL_PIC_URL <", value, "periodicalPicUrl");
            return (Criteria) this;
        }

        public Criteria andPeriodicalPicUrlLessThanOrEqualTo(String value) {
            addCriterion("PERIODICAL_PIC_URL <=", value, "periodicalPicUrl");
            return (Criteria) this;
        }

        public Criteria andPeriodicalPicUrlLike(String value) {
            addCriterion("PERIODICAL_PIC_URL like", value, "periodicalPicUrl");
            return (Criteria) this;
        }

        public Criteria andPeriodicalPicUrlNotLike(String value) {
            addCriterion("PERIODICAL_PIC_URL not like", value, "periodicalPicUrl");
            return (Criteria) this;
        }

        public Criteria andPeriodicalPicUrlIn(List<String> values) {
            addCriterion("PERIODICAL_PIC_URL in", values, "periodicalPicUrl");
            return (Criteria) this;
        }

        public Criteria andPeriodicalPicUrlNotIn(List<String> values) {
            addCriterion("PERIODICAL_PIC_URL not in", values, "periodicalPicUrl");
            return (Criteria) this;
        }

        public Criteria andPeriodicalPicUrlBetween(String value1, String value2) {
            addCriterion("PERIODICAL_PIC_URL between", value1, value2, "periodicalPicUrl");
            return (Criteria) this;
        }

        public Criteria andPeriodicalPicUrlNotBetween(String value1, String value2) {
            addCriterion("PERIODICAL_PIC_URL not between", value1, value2, "periodicalPicUrl");
            return (Criteria) this;
        }

        public Criteria andPaperPicUrlIsNull() {
            addCriterion("PAPER_PIC_URL is null");
            return (Criteria) this;
        }

        public Criteria andPaperPicUrlIsNotNull() {
            addCriterion("PAPER_PIC_URL is not null");
            return (Criteria) this;
        }

        public Criteria andPaperPicUrlEqualTo(String value) {
            addCriterion("PAPER_PIC_URL =", value, "paperPicUrl");
            return (Criteria) this;
        }

        public Criteria andPaperPicUrlNotEqualTo(String value) {
            addCriterion("PAPER_PIC_URL <>", value, "paperPicUrl");
            return (Criteria) this;
        }

        public Criteria andPaperPicUrlGreaterThan(String value) {
            addCriterion("PAPER_PIC_URL >", value, "paperPicUrl");
            return (Criteria) this;
        }

        public Criteria andPaperPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("PAPER_PIC_URL >=", value, "paperPicUrl");
            return (Criteria) this;
        }

        public Criteria andPaperPicUrlLessThan(String value) {
            addCriterion("PAPER_PIC_URL <", value, "paperPicUrl");
            return (Criteria) this;
        }

        public Criteria andPaperPicUrlLessThanOrEqualTo(String value) {
            addCriterion("PAPER_PIC_URL <=", value, "paperPicUrl");
            return (Criteria) this;
        }

        public Criteria andPaperPicUrlLike(String value) {
            addCriterion("PAPER_PIC_URL like", value, "paperPicUrl");
            return (Criteria) this;
        }

        public Criteria andPaperPicUrlNotLike(String value) {
            addCriterion("PAPER_PIC_URL not like", value, "paperPicUrl");
            return (Criteria) this;
        }

        public Criteria andPaperPicUrlIn(List<String> values) {
            addCriterion("PAPER_PIC_URL in", values, "paperPicUrl");
            return (Criteria) this;
        }

        public Criteria andPaperPicUrlNotIn(List<String> values) {
            addCriterion("PAPER_PIC_URL not in", values, "paperPicUrl");
            return (Criteria) this;
        }

        public Criteria andPaperPicUrlBetween(String value1, String value2) {
            addCriterion("PAPER_PIC_URL between", value1, value2, "paperPicUrl");
            return (Criteria) this;
        }

        public Criteria andPaperPicUrlNotBetween(String value1, String value2) {
            addCriterion("PAPER_PIC_URL not between", value1, value2, "paperPicUrl");
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

        public Criteria andAuthorRankIsNull() {
            addCriterion("AUTHOR_RANK is null");
            return (Criteria) this;
        }

        public Criteria andAuthorRankIsNotNull() {
            addCriterion("AUTHOR_RANK is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorRankEqualTo(String value) {
            addCriterion("AUTHOR_RANK =", value, "authorRank");
            return (Criteria) this;
        }

        public Criteria andAuthorRankNotEqualTo(String value) {
            addCriterion("AUTHOR_RANK <>", value, "authorRank");
            return (Criteria) this;
        }

        public Criteria andAuthorRankGreaterThan(String value) {
            addCriterion("AUTHOR_RANK >", value, "authorRank");
            return (Criteria) this;
        }

        public Criteria andAuthorRankGreaterThanOrEqualTo(String value) {
            addCriterion("AUTHOR_RANK >=", value, "authorRank");
            return (Criteria) this;
        }

        public Criteria andAuthorRankLessThan(String value) {
            addCriterion("AUTHOR_RANK <", value, "authorRank");
            return (Criteria) this;
        }

        public Criteria andAuthorRankLessThanOrEqualTo(String value) {
            addCriterion("AUTHOR_RANK <=", value, "authorRank");
            return (Criteria) this;
        }

        public Criteria andAuthorRankLike(String value) {
            addCriterion("AUTHOR_RANK like", value, "authorRank");
            return (Criteria) this;
        }

        public Criteria andAuthorRankNotLike(String value) {
            addCriterion("AUTHOR_RANK not like", value, "authorRank");
            return (Criteria) this;
        }

        public Criteria andAuthorRankIn(List<String> values) {
            addCriterion("AUTHOR_RANK in", values, "authorRank");
            return (Criteria) this;
        }

        public Criteria andAuthorRankNotIn(List<String> values) {
            addCriterion("AUTHOR_RANK not in", values, "authorRank");
            return (Criteria) this;
        }

        public Criteria andAuthorRankBetween(String value1, String value2) {
            addCriterion("AUTHOR_RANK between", value1, value2, "authorRank");
            return (Criteria) this;
        }

        public Criteria andAuthorRankNotBetween(String value1, String value2) {
            addCriterion("AUTHOR_RANK not between", value1, value2, "authorRank");
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