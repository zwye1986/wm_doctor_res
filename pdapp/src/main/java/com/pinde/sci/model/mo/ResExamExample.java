package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ResExamExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResExamExample() {
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

        public Criteria andExamFlowIsNull() {
            addCriterion("EXAM_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andExamFlowIsNotNull() {
            addCriterion("EXAM_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andExamFlowEqualTo(String value) {
            addCriterion("EXAM_FLOW =", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowNotEqualTo(String value) {
            addCriterion("EXAM_FLOW <>", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowGreaterThan(String value) {
            addCriterion("EXAM_FLOW >", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_FLOW >=", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowLessThan(String value) {
            addCriterion("EXAM_FLOW <", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowLessThanOrEqualTo(String value) {
            addCriterion("EXAM_FLOW <=", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowLike(String value) {
            addCriterion("EXAM_FLOW like", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowNotLike(String value) {
            addCriterion("EXAM_FLOW not like", value, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowIn(List<String> values) {
            addCriterion("EXAM_FLOW in", values, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowNotIn(List<String> values) {
            addCriterion("EXAM_FLOW not in", values, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowBetween(String value1, String value2) {
            addCriterion("EXAM_FLOW between", value1, value2, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamFlowNotBetween(String value1, String value2) {
            addCriterion("EXAM_FLOW not between", value1, value2, "examFlow");
            return (Criteria) this;
        }

        public Criteria andExamNameIsNull() {
            addCriterion("EXAM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andExamNameIsNotNull() {
            addCriterion("EXAM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andExamNameEqualTo(String value) {
            addCriterion("EXAM_NAME =", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameNotEqualTo(String value) {
            addCriterion("EXAM_NAME <>", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameGreaterThan(String value) {
            addCriterion("EXAM_NAME >", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_NAME >=", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameLessThan(String value) {
            addCriterion("EXAM_NAME <", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameLessThanOrEqualTo(String value) {
            addCriterion("EXAM_NAME <=", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameLike(String value) {
            addCriterion("EXAM_NAME like", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameNotLike(String value) {
            addCriterion("EXAM_NAME not like", value, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameIn(List<String> values) {
            addCriterion("EXAM_NAME in", values, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameNotIn(List<String> values) {
            addCriterion("EXAM_NAME not in", values, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameBetween(String value1, String value2) {
            addCriterion("EXAM_NAME between", value1, value2, "examName");
            return (Criteria) this;
        }

        public Criteria andExamNameNotBetween(String value1, String value2) {
            addCriterion("EXAM_NAME not between", value1, value2, "examName");
            return (Criteria) this;
        }

        public Criteria andExamDateIsNull() {
            addCriterion("EXAM_DATE is null");
            return (Criteria) this;
        }

        public Criteria andExamDateIsNotNull() {
            addCriterion("EXAM_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andExamDateEqualTo(String value) {
            addCriterion("EXAM_DATE =", value, "examDate");
            return (Criteria) this;
        }

        public Criteria andExamDateNotEqualTo(String value) {
            addCriterion("EXAM_DATE <>", value, "examDate");
            return (Criteria) this;
        }

        public Criteria andExamDateGreaterThan(String value) {
            addCriterion("EXAM_DATE >", value, "examDate");
            return (Criteria) this;
        }

        public Criteria andExamDateGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_DATE >=", value, "examDate");
            return (Criteria) this;
        }

        public Criteria andExamDateLessThan(String value) {
            addCriterion("EXAM_DATE <", value, "examDate");
            return (Criteria) this;
        }

        public Criteria andExamDateLessThanOrEqualTo(String value) {
            addCriterion("EXAM_DATE <=", value, "examDate");
            return (Criteria) this;
        }

        public Criteria andExamDateLike(String value) {
            addCriterion("EXAM_DATE like", value, "examDate");
            return (Criteria) this;
        }

        public Criteria andExamDateNotLike(String value) {
            addCriterion("EXAM_DATE not like", value, "examDate");
            return (Criteria) this;
        }

        public Criteria andExamDateIn(List<String> values) {
            addCriterion("EXAM_DATE in", values, "examDate");
            return (Criteria) this;
        }

        public Criteria andExamDateNotIn(List<String> values) {
            addCriterion("EXAM_DATE not in", values, "examDate");
            return (Criteria) this;
        }

        public Criteria andExamDateBetween(String value1, String value2) {
            addCriterion("EXAM_DATE between", value1, value2, "examDate");
            return (Criteria) this;
        }

        public Criteria andExamDateNotBetween(String value1, String value2) {
            addCriterion("EXAM_DATE not between", value1, value2, "examDate");
            return (Criteria) this;
        }

        public Criteria andExamTimeIsNull() {
            addCriterion("EXAM_TIME is null");
            return (Criteria) this;
        }

        public Criteria andExamTimeIsNotNull() {
            addCriterion("EXAM_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andExamTimeEqualTo(String value) {
            addCriterion("EXAM_TIME =", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeNotEqualTo(String value) {
            addCriterion("EXAM_TIME <>", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeGreaterThan(String value) {
            addCriterion("EXAM_TIME >", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_TIME >=", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeLessThan(String value) {
            addCriterion("EXAM_TIME <", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeLessThanOrEqualTo(String value) {
            addCriterion("EXAM_TIME <=", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeLike(String value) {
            addCriterion("EXAM_TIME like", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeNotLike(String value) {
            addCriterion("EXAM_TIME not like", value, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeIn(List<String> values) {
            addCriterion("EXAM_TIME in", values, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeNotIn(List<String> values) {
            addCriterion("EXAM_TIME not in", values, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeBetween(String value1, String value2) {
            addCriterion("EXAM_TIME between", value1, value2, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTimeNotBetween(String value1, String value2) {
            addCriterion("EXAM_TIME not between", value1, value2, "examTime");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdIsNull() {
            addCriterion("EXAM_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdIsNotNull() {
            addCriterion("EXAM_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdEqualTo(String value) {
            addCriterion("EXAM_TYPE_ID =", value, "examTypeId");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdNotEqualTo(String value) {
            addCriterion("EXAM_TYPE_ID <>", value, "examTypeId");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdGreaterThan(String value) {
            addCriterion("EXAM_TYPE_ID >", value, "examTypeId");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_TYPE_ID >=", value, "examTypeId");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdLessThan(String value) {
            addCriterion("EXAM_TYPE_ID <", value, "examTypeId");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdLessThanOrEqualTo(String value) {
            addCriterion("EXAM_TYPE_ID <=", value, "examTypeId");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdLike(String value) {
            addCriterion("EXAM_TYPE_ID like", value, "examTypeId");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdNotLike(String value) {
            addCriterion("EXAM_TYPE_ID not like", value, "examTypeId");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdIn(List<String> values) {
            addCriterion("EXAM_TYPE_ID in", values, "examTypeId");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdNotIn(List<String> values) {
            addCriterion("EXAM_TYPE_ID not in", values, "examTypeId");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdBetween(String value1, String value2) {
            addCriterion("EXAM_TYPE_ID between", value1, value2, "examTypeId");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdNotBetween(String value1, String value2) {
            addCriterion("EXAM_TYPE_ID not between", value1, value2, "examTypeId");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameIsNull() {
            addCriterion("EXAM_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameIsNotNull() {
            addCriterion("EXAM_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameEqualTo(String value) {
            addCriterion("EXAM_TYPE_NAME =", value, "examTypeName");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameNotEqualTo(String value) {
            addCriterion("EXAM_TYPE_NAME <>", value, "examTypeName");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameGreaterThan(String value) {
            addCriterion("EXAM_TYPE_NAME >", value, "examTypeName");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_TYPE_NAME >=", value, "examTypeName");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameLessThan(String value) {
            addCriterion("EXAM_TYPE_NAME <", value, "examTypeName");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameLessThanOrEqualTo(String value) {
            addCriterion("EXAM_TYPE_NAME <=", value, "examTypeName");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameLike(String value) {
            addCriterion("EXAM_TYPE_NAME like", value, "examTypeName");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameNotLike(String value) {
            addCriterion("EXAM_TYPE_NAME not like", value, "examTypeName");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameIn(List<String> values) {
            addCriterion("EXAM_TYPE_NAME in", values, "examTypeName");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameNotIn(List<String> values) {
            addCriterion("EXAM_TYPE_NAME not in", values, "examTypeName");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameBetween(String value1, String value2) {
            addCriterion("EXAM_TYPE_NAME between", value1, value2, "examTypeName");
            return (Criteria) this;
        }

        public Criteria andExamTypeNameNotBetween(String value1, String value2) {
            addCriterion("EXAM_TYPE_NAME not between", value1, value2, "examTypeName");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdIsNull() {
            addCriterion("EXAM_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdIsNotNull() {
            addCriterion("EXAM_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdEqualTo(String value) {
            addCriterion("EXAM_STATUS_ID =", value, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdNotEqualTo(String value) {
            addCriterion("EXAM_STATUS_ID <>", value, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdGreaterThan(String value) {
            addCriterion("EXAM_STATUS_ID >", value, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_STATUS_ID >=", value, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdLessThan(String value) {
            addCriterion("EXAM_STATUS_ID <", value, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdLessThanOrEqualTo(String value) {
            addCriterion("EXAM_STATUS_ID <=", value, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdLike(String value) {
            addCriterion("EXAM_STATUS_ID like", value, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdNotLike(String value) {
            addCriterion("EXAM_STATUS_ID not like", value, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdIn(List<String> values) {
            addCriterion("EXAM_STATUS_ID in", values, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdNotIn(List<String> values) {
            addCriterion("EXAM_STATUS_ID not in", values, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdBetween(String value1, String value2) {
            addCriterion("EXAM_STATUS_ID between", value1, value2, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusIdNotBetween(String value1, String value2) {
            addCriterion("EXAM_STATUS_ID not between", value1, value2, "examStatusId");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameIsNull() {
            addCriterion("EXAM_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameIsNotNull() {
            addCriterion("EXAM_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameEqualTo(String value) {
            addCriterion("EXAM_STATUS_NAME =", value, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameNotEqualTo(String value) {
            addCriterion("EXAM_STATUS_NAME <>", value, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameGreaterThan(String value) {
            addCriterion("EXAM_STATUS_NAME >", value, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_STATUS_NAME >=", value, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameLessThan(String value) {
            addCriterion("EXAM_STATUS_NAME <", value, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameLessThanOrEqualTo(String value) {
            addCriterion("EXAM_STATUS_NAME <=", value, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameLike(String value) {
            addCriterion("EXAM_STATUS_NAME like", value, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameNotLike(String value) {
            addCriterion("EXAM_STATUS_NAME not like", value, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameIn(List<String> values) {
            addCriterion("EXAM_STATUS_NAME in", values, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameNotIn(List<String> values) {
            addCriterion("EXAM_STATUS_NAME not in", values, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameBetween(String value1, String value2) {
            addCriterion("EXAM_STATUS_NAME between", value1, value2, "examStatusName");
            return (Criteria) this;
        }

        public Criteria andExamStatusNameNotBetween(String value1, String value2) {
            addCriterion("EXAM_STATUS_NAME not between", value1, value2, "examStatusName");
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

        public Criteria andExamYearIsNull() {
            addCriterion("EXAM_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andExamYearIsNotNull() {
            addCriterion("EXAM_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andExamYearEqualTo(String value) {
            addCriterion("EXAM_YEAR =", value, "examYear");
            return (Criteria) this;
        }

        public Criteria andExamYearNotEqualTo(String value) {
            addCriterion("EXAM_YEAR <>", value, "examYear");
            return (Criteria) this;
        }

        public Criteria andExamYearGreaterThan(String value) {
            addCriterion("EXAM_YEAR >", value, "examYear");
            return (Criteria) this;
        }

        public Criteria andExamYearGreaterThanOrEqualTo(String value) {
            addCriterion("EXAM_YEAR >=", value, "examYear");
            return (Criteria) this;
        }

        public Criteria andExamYearLessThan(String value) {
            addCriterion("EXAM_YEAR <", value, "examYear");
            return (Criteria) this;
        }

        public Criteria andExamYearLessThanOrEqualTo(String value) {
            addCriterion("EXAM_YEAR <=", value, "examYear");
            return (Criteria) this;
        }

        public Criteria andExamYearLike(String value) {
            addCriterion("EXAM_YEAR like", value, "examYear");
            return (Criteria) this;
        }

        public Criteria andExamYearNotLike(String value) {
            addCriterion("EXAM_YEAR not like", value, "examYear");
            return (Criteria) this;
        }

        public Criteria andExamYearIn(List<String> values) {
            addCriterion("EXAM_YEAR in", values, "examYear");
            return (Criteria) this;
        }

        public Criteria andExamYearNotIn(List<String> values) {
            addCriterion("EXAM_YEAR not in", values, "examYear");
            return (Criteria) this;
        }

        public Criteria andExamYearBetween(String value1, String value2) {
            addCriterion("EXAM_YEAR between", value1, value2, "examYear");
            return (Criteria) this;
        }

        public Criteria andExamYearNotBetween(String value1, String value2) {
            addCriterion("EXAM_YEAR not between", value1, value2, "examYear");
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