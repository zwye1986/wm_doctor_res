package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class PubWorkpaperExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PubWorkpaperExample() {
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

        public Criteria andWorkpaperNameIsNull() {
            addCriterion("WORKPAPER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andWorkpaperNameIsNotNull() {
            addCriterion("WORKPAPER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andWorkpaperNameEqualTo(String value) {
            addCriterion("WORKPAPER_NAME =", value, "workpaperName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperNameNotEqualTo(String value) {
            addCriterion("WORKPAPER_NAME <>", value, "workpaperName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperNameGreaterThan(String value) {
            addCriterion("WORKPAPER_NAME >", value, "workpaperName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperNameGreaterThanOrEqualTo(String value) {
            addCriterion("WORKPAPER_NAME >=", value, "workpaperName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperNameLessThan(String value) {
            addCriterion("WORKPAPER_NAME <", value, "workpaperName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperNameLessThanOrEqualTo(String value) {
            addCriterion("WORKPAPER_NAME <=", value, "workpaperName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperNameLike(String value) {
            addCriterion("WORKPAPER_NAME like", value, "workpaperName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperNameNotLike(String value) {
            addCriterion("WORKPAPER_NAME not like", value, "workpaperName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperNameIn(List<String> values) {
            addCriterion("WORKPAPER_NAME in", values, "workpaperName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperNameNotIn(List<String> values) {
            addCriterion("WORKPAPER_NAME not in", values, "workpaperName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperNameBetween(String value1, String value2) {
            addCriterion("WORKPAPER_NAME between", value1, value2, "workpaperName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperNameNotBetween(String value1, String value2) {
            addCriterion("WORKPAPER_NAME not between", value1, value2, "workpaperName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeIdIsNull() {
            addCriterion("WORKPAPER_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeIdIsNotNull() {
            addCriterion("WORKPAPER_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeIdEqualTo(String value) {
            addCriterion("WORKPAPER_TYPE_ID =", value, "workpaperTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeIdNotEqualTo(String value) {
            addCriterion("WORKPAPER_TYPE_ID <>", value, "workpaperTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeIdGreaterThan(String value) {
            addCriterion("WORKPAPER_TYPE_ID >", value, "workpaperTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("WORKPAPER_TYPE_ID >=", value, "workpaperTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeIdLessThan(String value) {
            addCriterion("WORKPAPER_TYPE_ID <", value, "workpaperTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeIdLessThanOrEqualTo(String value) {
            addCriterion("WORKPAPER_TYPE_ID <=", value, "workpaperTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeIdLike(String value) {
            addCriterion("WORKPAPER_TYPE_ID like", value, "workpaperTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeIdNotLike(String value) {
            addCriterion("WORKPAPER_TYPE_ID not like", value, "workpaperTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeIdIn(List<String> values) {
            addCriterion("WORKPAPER_TYPE_ID in", values, "workpaperTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeIdNotIn(List<String> values) {
            addCriterion("WORKPAPER_TYPE_ID not in", values, "workpaperTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeIdBetween(String value1, String value2) {
            addCriterion("WORKPAPER_TYPE_ID between", value1, value2, "workpaperTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeIdNotBetween(String value1, String value2) {
            addCriterion("WORKPAPER_TYPE_ID not between", value1, value2, "workpaperTypeId");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeNameIsNull() {
            addCriterion("WORKPAPER_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeNameIsNotNull() {
            addCriterion("WORKPAPER_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeNameEqualTo(String value) {
            addCriterion("WORKPAPER_TYPE_NAME =", value, "workpaperTypeName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeNameNotEqualTo(String value) {
            addCriterion("WORKPAPER_TYPE_NAME <>", value, "workpaperTypeName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeNameGreaterThan(String value) {
            addCriterion("WORKPAPER_TYPE_NAME >", value, "workpaperTypeName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("WORKPAPER_TYPE_NAME >=", value, "workpaperTypeName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeNameLessThan(String value) {
            addCriterion("WORKPAPER_TYPE_NAME <", value, "workpaperTypeName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeNameLessThanOrEqualTo(String value) {
            addCriterion("WORKPAPER_TYPE_NAME <=", value, "workpaperTypeName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeNameLike(String value) {
            addCriterion("WORKPAPER_TYPE_NAME like", value, "workpaperTypeName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeNameNotLike(String value) {
            addCriterion("WORKPAPER_TYPE_NAME not like", value, "workpaperTypeName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeNameIn(List<String> values) {
            addCriterion("WORKPAPER_TYPE_NAME in", values, "workpaperTypeName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeNameNotIn(List<String> values) {
            addCriterion("WORKPAPER_TYPE_NAME not in", values, "workpaperTypeName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeNameBetween(String value1, String value2) {
            addCriterion("WORKPAPER_TYPE_NAME between", value1, value2, "workpaperTypeName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperTypeNameNotBetween(String value1, String value2) {
            addCriterion("WORKPAPER_TYPE_NAME not between", value1, value2, "workpaperTypeName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusIdIsNull() {
            addCriterion("WORKPAPER_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusIdIsNotNull() {
            addCriterion("WORKPAPER_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusIdEqualTo(String value) {
            addCriterion("WORKPAPER_STATUS_ID =", value, "workpaperStatusId");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusIdNotEqualTo(String value) {
            addCriterion("WORKPAPER_STATUS_ID <>", value, "workpaperStatusId");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusIdGreaterThan(String value) {
            addCriterion("WORKPAPER_STATUS_ID >", value, "workpaperStatusId");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("WORKPAPER_STATUS_ID >=", value, "workpaperStatusId");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusIdLessThan(String value) {
            addCriterion("WORKPAPER_STATUS_ID <", value, "workpaperStatusId");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusIdLessThanOrEqualTo(String value) {
            addCriterion("WORKPAPER_STATUS_ID <=", value, "workpaperStatusId");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusIdLike(String value) {
            addCriterion("WORKPAPER_STATUS_ID like", value, "workpaperStatusId");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusIdNotLike(String value) {
            addCriterion("WORKPAPER_STATUS_ID not like", value, "workpaperStatusId");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusIdIn(List<String> values) {
            addCriterion("WORKPAPER_STATUS_ID in", values, "workpaperStatusId");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusIdNotIn(List<String> values) {
            addCriterion("WORKPAPER_STATUS_ID not in", values, "workpaperStatusId");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusIdBetween(String value1, String value2) {
            addCriterion("WORKPAPER_STATUS_ID between", value1, value2, "workpaperStatusId");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusIdNotBetween(String value1, String value2) {
            addCriterion("WORKPAPER_STATUS_ID not between", value1, value2, "workpaperStatusId");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusNameIsNull() {
            addCriterion("WORKPAPER_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusNameIsNotNull() {
            addCriterion("WORKPAPER_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusNameEqualTo(String value) {
            addCriterion("WORKPAPER_STATUS_NAME =", value, "workpaperStatusName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusNameNotEqualTo(String value) {
            addCriterion("WORKPAPER_STATUS_NAME <>", value, "workpaperStatusName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusNameGreaterThan(String value) {
            addCriterion("WORKPAPER_STATUS_NAME >", value, "workpaperStatusName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("WORKPAPER_STATUS_NAME >=", value, "workpaperStatusName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusNameLessThan(String value) {
            addCriterion("WORKPAPER_STATUS_NAME <", value, "workpaperStatusName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusNameLessThanOrEqualTo(String value) {
            addCriterion("WORKPAPER_STATUS_NAME <=", value, "workpaperStatusName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusNameLike(String value) {
            addCriterion("WORKPAPER_STATUS_NAME like", value, "workpaperStatusName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusNameNotLike(String value) {
            addCriterion("WORKPAPER_STATUS_NAME not like", value, "workpaperStatusName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusNameIn(List<String> values) {
            addCriterion("WORKPAPER_STATUS_NAME in", values, "workpaperStatusName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusNameNotIn(List<String> values) {
            addCriterion("WORKPAPER_STATUS_NAME not in", values, "workpaperStatusName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusNameBetween(String value1, String value2) {
            addCriterion("WORKPAPER_STATUS_NAME between", value1, value2, "workpaperStatusName");
            return (Criteria) this;
        }

        public Criteria andWorkpaperStatusNameNotBetween(String value1, String value2) {
            addCriterion("WORKPAPER_STATUS_NAME not between", value1, value2, "workpaperStatusName");
            return (Criteria) this;
        }

        public Criteria andReportUserNameIsNull() {
            addCriterion("REPORT_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andReportUserNameIsNotNull() {
            addCriterion("REPORT_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andReportUserNameEqualTo(String value) {
            addCriterion("REPORT_USER_NAME =", value, "reportUserName");
            return (Criteria) this;
        }

        public Criteria andReportUserNameNotEqualTo(String value) {
            addCriterion("REPORT_USER_NAME <>", value, "reportUserName");
            return (Criteria) this;
        }

        public Criteria andReportUserNameGreaterThan(String value) {
            addCriterion("REPORT_USER_NAME >", value, "reportUserName");
            return (Criteria) this;
        }

        public Criteria andReportUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("REPORT_USER_NAME >=", value, "reportUserName");
            return (Criteria) this;
        }

        public Criteria andReportUserNameLessThan(String value) {
            addCriterion("REPORT_USER_NAME <", value, "reportUserName");
            return (Criteria) this;
        }

        public Criteria andReportUserNameLessThanOrEqualTo(String value) {
            addCriterion("REPORT_USER_NAME <=", value, "reportUserName");
            return (Criteria) this;
        }

        public Criteria andReportUserNameLike(String value) {
            addCriterion("REPORT_USER_NAME like", value, "reportUserName");
            return (Criteria) this;
        }

        public Criteria andReportUserNameNotLike(String value) {
            addCriterion("REPORT_USER_NAME not like", value, "reportUserName");
            return (Criteria) this;
        }

        public Criteria andReportUserNameIn(List<String> values) {
            addCriterion("REPORT_USER_NAME in", values, "reportUserName");
            return (Criteria) this;
        }

        public Criteria andReportUserNameNotIn(List<String> values) {
            addCriterion("REPORT_USER_NAME not in", values, "reportUserName");
            return (Criteria) this;
        }

        public Criteria andReportUserNameBetween(String value1, String value2) {
            addCriterion("REPORT_USER_NAME between", value1, value2, "reportUserName");
            return (Criteria) this;
        }

        public Criteria andReportUserNameNotBetween(String value1, String value2) {
            addCriterion("REPORT_USER_NAME not between", value1, value2, "reportUserName");
            return (Criteria) this;
        }

        public Criteria andReportTimeIsNull() {
            addCriterion("REPORT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andReportTimeIsNotNull() {
            addCriterion("REPORT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andReportTimeEqualTo(String value) {
            addCriterion("REPORT_TIME =", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeNotEqualTo(String value) {
            addCriterion("REPORT_TIME <>", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeGreaterThan(String value) {
            addCriterion("REPORT_TIME >", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeGreaterThanOrEqualTo(String value) {
            addCriterion("REPORT_TIME >=", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeLessThan(String value) {
            addCriterion("REPORT_TIME <", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeLessThanOrEqualTo(String value) {
            addCriterion("REPORT_TIME <=", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeLike(String value) {
            addCriterion("REPORT_TIME like", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeNotLike(String value) {
            addCriterion("REPORT_TIME not like", value, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeIn(List<String> values) {
            addCriterion("REPORT_TIME in", values, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeNotIn(List<String> values) {
            addCriterion("REPORT_TIME not in", values, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeBetween(String value1, String value2) {
            addCriterion("REPORT_TIME between", value1, value2, "reportTime");
            return (Criteria) this;
        }

        public Criteria andReportTimeNotBetween(String value1, String value2) {
            addCriterion("REPORT_TIME not between", value1, value2, "reportTime");
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