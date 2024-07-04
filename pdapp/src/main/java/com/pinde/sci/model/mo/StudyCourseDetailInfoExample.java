package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class StudyCourseDetailInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StudyCourseDetailInfoExample() {
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

        public Criteria andDetailFlowIsNull() {
            addCriterion("DETAIL_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDetailFlowIsNotNull() {
            addCriterion("DETAIL_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDetailFlowEqualTo(String value) {
            addCriterion("DETAIL_FLOW =", value, "detailFlow");
            return (Criteria) this;
        }

        public Criteria andDetailFlowNotEqualTo(String value) {
            addCriterion("DETAIL_FLOW <>", value, "detailFlow");
            return (Criteria) this;
        }

        public Criteria andDetailFlowGreaterThan(String value) {
            addCriterion("DETAIL_FLOW >", value, "detailFlow");
            return (Criteria) this;
        }

        public Criteria andDetailFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DETAIL_FLOW >=", value, "detailFlow");
            return (Criteria) this;
        }

        public Criteria andDetailFlowLessThan(String value) {
            addCriterion("DETAIL_FLOW <", value, "detailFlow");
            return (Criteria) this;
        }

        public Criteria andDetailFlowLessThanOrEqualTo(String value) {
            addCriterion("DETAIL_FLOW <=", value, "detailFlow");
            return (Criteria) this;
        }

        public Criteria andDetailFlowLike(String value) {
            addCriterion("DETAIL_FLOW like", value, "detailFlow");
            return (Criteria) this;
        }

        public Criteria andDetailFlowNotLike(String value) {
            addCriterion("DETAIL_FLOW not like", value, "detailFlow");
            return (Criteria) this;
        }

        public Criteria andDetailFlowIn(List<String> values) {
            addCriterion("DETAIL_FLOW in", values, "detailFlow");
            return (Criteria) this;
        }

        public Criteria andDetailFlowNotIn(List<String> values) {
            addCriterion("DETAIL_FLOW not in", values, "detailFlow");
            return (Criteria) this;
        }

        public Criteria andDetailFlowBetween(String value1, String value2) {
            addCriterion("DETAIL_FLOW between", value1, value2, "detailFlow");
            return (Criteria) this;
        }

        public Criteria andDetailFlowNotBetween(String value1, String value2) {
            addCriterion("DETAIL_FLOW not between", value1, value2, "detailFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowIsNull() {
            addCriterion("COURSE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andCourseFlowIsNotNull() {
            addCriterion("COURSE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andCourseFlowEqualTo(String value) {
            addCriterion("COURSE_FLOW =", value, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowNotEqualTo(String value) {
            addCriterion("COURSE_FLOW <>", value, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowGreaterThan(String value) {
            addCriterion("COURSE_FLOW >", value, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_FLOW >=", value, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowLessThan(String value) {
            addCriterion("COURSE_FLOW <", value, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowLessThanOrEqualTo(String value) {
            addCriterion("COURSE_FLOW <=", value, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowLike(String value) {
            addCriterion("COURSE_FLOW like", value, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowNotLike(String value) {
            addCriterion("COURSE_FLOW not like", value, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowIn(List<String> values) {
            addCriterion("COURSE_FLOW in", values, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowNotIn(List<String> values) {
            addCriterion("COURSE_FLOW not in", values, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowBetween(String value1, String value2) {
            addCriterion("COURSE_FLOW between", value1, value2, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andCourseFlowNotBetween(String value1, String value2) {
            addCriterion("COURSE_FLOW not between", value1, value2, "courseFlow");
            return (Criteria) this;
        }

        public Criteria andDetailTypeIdIsNull() {
            addCriterion("DETAIL_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDetailTypeIdIsNotNull() {
            addCriterion("DETAIL_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDetailTypeIdEqualTo(String value) {
            addCriterion("DETAIL_TYPE_ID =", value, "detailTypeId");
            return (Criteria) this;
        }

        public Criteria andDetailTypeIdNotEqualTo(String value) {
            addCriterion("DETAIL_TYPE_ID <>", value, "detailTypeId");
            return (Criteria) this;
        }

        public Criteria andDetailTypeIdGreaterThan(String value) {
            addCriterion("DETAIL_TYPE_ID >", value, "detailTypeId");
            return (Criteria) this;
        }

        public Criteria andDetailTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("DETAIL_TYPE_ID >=", value, "detailTypeId");
            return (Criteria) this;
        }

        public Criteria andDetailTypeIdLessThan(String value) {
            addCriterion("DETAIL_TYPE_ID <", value, "detailTypeId");
            return (Criteria) this;
        }

        public Criteria andDetailTypeIdLessThanOrEqualTo(String value) {
            addCriterion("DETAIL_TYPE_ID <=", value, "detailTypeId");
            return (Criteria) this;
        }

        public Criteria andDetailTypeIdLike(String value) {
            addCriterion("DETAIL_TYPE_ID like", value, "detailTypeId");
            return (Criteria) this;
        }

        public Criteria andDetailTypeIdNotLike(String value) {
            addCriterion("DETAIL_TYPE_ID not like", value, "detailTypeId");
            return (Criteria) this;
        }

        public Criteria andDetailTypeIdIn(List<String> values) {
            addCriterion("DETAIL_TYPE_ID in", values, "detailTypeId");
            return (Criteria) this;
        }

        public Criteria andDetailTypeIdNotIn(List<String> values) {
            addCriterion("DETAIL_TYPE_ID not in", values, "detailTypeId");
            return (Criteria) this;
        }

        public Criteria andDetailTypeIdBetween(String value1, String value2) {
            addCriterion("DETAIL_TYPE_ID between", value1, value2, "detailTypeId");
            return (Criteria) this;
        }

        public Criteria andDetailTypeIdNotBetween(String value1, String value2) {
            addCriterion("DETAIL_TYPE_ID not between", value1, value2, "detailTypeId");
            return (Criteria) this;
        }

        public Criteria andDetailTypeNameIsNull() {
            addCriterion("DETAIL_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDetailTypeNameIsNotNull() {
            addCriterion("DETAIL_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDetailTypeNameEqualTo(String value) {
            addCriterion("DETAIL_TYPE_NAME =", value, "detailTypeName");
            return (Criteria) this;
        }

        public Criteria andDetailTypeNameNotEqualTo(String value) {
            addCriterion("DETAIL_TYPE_NAME <>", value, "detailTypeName");
            return (Criteria) this;
        }

        public Criteria andDetailTypeNameGreaterThan(String value) {
            addCriterion("DETAIL_TYPE_NAME >", value, "detailTypeName");
            return (Criteria) this;
        }

        public Criteria andDetailTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("DETAIL_TYPE_NAME >=", value, "detailTypeName");
            return (Criteria) this;
        }

        public Criteria andDetailTypeNameLessThan(String value) {
            addCriterion("DETAIL_TYPE_NAME <", value, "detailTypeName");
            return (Criteria) this;
        }

        public Criteria andDetailTypeNameLessThanOrEqualTo(String value) {
            addCriterion("DETAIL_TYPE_NAME <=", value, "detailTypeName");
            return (Criteria) this;
        }

        public Criteria andDetailTypeNameLike(String value) {
            addCriterion("DETAIL_TYPE_NAME like", value, "detailTypeName");
            return (Criteria) this;
        }

        public Criteria andDetailTypeNameNotLike(String value) {
            addCriterion("DETAIL_TYPE_NAME not like", value, "detailTypeName");
            return (Criteria) this;
        }

        public Criteria andDetailTypeNameIn(List<String> values) {
            addCriterion("DETAIL_TYPE_NAME in", values, "detailTypeName");
            return (Criteria) this;
        }

        public Criteria andDetailTypeNameNotIn(List<String> values) {
            addCriterion("DETAIL_TYPE_NAME not in", values, "detailTypeName");
            return (Criteria) this;
        }

        public Criteria andDetailTypeNameBetween(String value1, String value2) {
            addCriterion("DETAIL_TYPE_NAME between", value1, value2, "detailTypeName");
            return (Criteria) this;
        }

        public Criteria andDetailTypeNameNotBetween(String value1, String value2) {
            addCriterion("DETAIL_TYPE_NAME not between", value1, value2, "detailTypeName");
            return (Criteria) this;
        }

        public Criteria andDetailNameIsNull() {
            addCriterion("DETAIL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDetailNameIsNotNull() {
            addCriterion("DETAIL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDetailNameEqualTo(String value) {
            addCriterion("DETAIL_NAME =", value, "detailName");
            return (Criteria) this;
        }

        public Criteria andDetailNameNotEqualTo(String value) {
            addCriterion("DETAIL_NAME <>", value, "detailName");
            return (Criteria) this;
        }

        public Criteria andDetailNameGreaterThan(String value) {
            addCriterion("DETAIL_NAME >", value, "detailName");
            return (Criteria) this;
        }

        public Criteria andDetailNameGreaterThanOrEqualTo(String value) {
            addCriterion("DETAIL_NAME >=", value, "detailName");
            return (Criteria) this;
        }

        public Criteria andDetailNameLessThan(String value) {
            addCriterion("DETAIL_NAME <", value, "detailName");
            return (Criteria) this;
        }

        public Criteria andDetailNameLessThanOrEqualTo(String value) {
            addCriterion("DETAIL_NAME <=", value, "detailName");
            return (Criteria) this;
        }

        public Criteria andDetailNameLike(String value) {
            addCriterion("DETAIL_NAME like", value, "detailName");
            return (Criteria) this;
        }

        public Criteria andDetailNameNotLike(String value) {
            addCriterion("DETAIL_NAME not like", value, "detailName");
            return (Criteria) this;
        }

        public Criteria andDetailNameIn(List<String> values) {
            addCriterion("DETAIL_NAME in", values, "detailName");
            return (Criteria) this;
        }

        public Criteria andDetailNameNotIn(List<String> values) {
            addCriterion("DETAIL_NAME not in", values, "detailName");
            return (Criteria) this;
        }

        public Criteria andDetailNameBetween(String value1, String value2) {
            addCriterion("DETAIL_NAME between", value1, value2, "detailName");
            return (Criteria) this;
        }

        public Criteria andDetailNameNotBetween(String value1, String value2) {
            addCriterion("DETAIL_NAME not between", value1, value2, "detailName");
            return (Criteria) this;
        }

        public Criteria andDetailUrlIsNull() {
            addCriterion("DETAIL_URL is null");
            return (Criteria) this;
        }

        public Criteria andDetailUrlIsNotNull() {
            addCriterion("DETAIL_URL is not null");
            return (Criteria) this;
        }

        public Criteria andDetailUrlEqualTo(String value) {
            addCriterion("DETAIL_URL =", value, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlNotEqualTo(String value) {
            addCriterion("DETAIL_URL <>", value, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlGreaterThan(String value) {
            addCriterion("DETAIL_URL >", value, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlGreaterThanOrEqualTo(String value) {
            addCriterion("DETAIL_URL >=", value, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlLessThan(String value) {
            addCriterion("DETAIL_URL <", value, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlLessThanOrEqualTo(String value) {
            addCriterion("DETAIL_URL <=", value, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlLike(String value) {
            addCriterion("DETAIL_URL like", value, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlNotLike(String value) {
            addCriterion("DETAIL_URL not like", value, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlIn(List<String> values) {
            addCriterion("DETAIL_URL in", values, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlNotIn(List<String> values) {
            addCriterion("DETAIL_URL not in", values, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlBetween(String value1, String value2) {
            addCriterion("DETAIL_URL between", value1, value2, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andDetailUrlNotBetween(String value1, String value2) {
            addCriterion("DETAIL_URL not between", value1, value2, "detailUrl");
            return (Criteria) this;
        }

        public Criteria andPagerNumberIsNull() {
            addCriterion("PAGER_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andPagerNumberIsNotNull() {
            addCriterion("PAGER_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andPagerNumberEqualTo(Integer value) {
            addCriterion("PAGER_NUMBER =", value, "pagerNumber");
            return (Criteria) this;
        }

        public Criteria andPagerNumberNotEqualTo(Integer value) {
            addCriterion("PAGER_NUMBER <>", value, "pagerNumber");
            return (Criteria) this;
        }

        public Criteria andPagerNumberGreaterThan(Integer value) {
            addCriterion("PAGER_NUMBER >", value, "pagerNumber");
            return (Criteria) this;
        }

        public Criteria andPagerNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("PAGER_NUMBER >=", value, "pagerNumber");
            return (Criteria) this;
        }

        public Criteria andPagerNumberLessThan(Integer value) {
            addCriterion("PAGER_NUMBER <", value, "pagerNumber");
            return (Criteria) this;
        }

        public Criteria andPagerNumberLessThanOrEqualTo(Integer value) {
            addCriterion("PAGER_NUMBER <=", value, "pagerNumber");
            return (Criteria) this;
        }

        public Criteria andPagerNumberIn(List<Integer> values) {
            addCriterion("PAGER_NUMBER in", values, "pagerNumber");
            return (Criteria) this;
        }

        public Criteria andPagerNumberNotIn(List<Integer> values) {
            addCriterion("PAGER_NUMBER not in", values, "pagerNumber");
            return (Criteria) this;
        }

        public Criteria andPagerNumberBetween(Integer value1, Integer value2) {
            addCriterion("PAGER_NUMBER between", value1, value2, "pagerNumber");
            return (Criteria) this;
        }

        public Criteria andPagerNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("PAGER_NUMBER not between", value1, value2, "pagerNumber");
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