package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class PortalInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PortalInfoExample() {
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

        public Criteria andInfoFlowIsNull() {
            addCriterion("INFO_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andInfoFlowIsNotNull() {
            addCriterion("INFO_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andInfoFlowEqualTo(String value) {
            addCriterion("INFO_FLOW =", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowNotEqualTo(String value) {
            addCriterion("INFO_FLOW <>", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowGreaterThan(String value) {
            addCriterion("INFO_FLOW >", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowGreaterThanOrEqualTo(String value) {
            addCriterion("INFO_FLOW >=", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowLessThan(String value) {
            addCriterion("INFO_FLOW <", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowLessThanOrEqualTo(String value) {
            addCriterion("INFO_FLOW <=", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowLike(String value) {
            addCriterion("INFO_FLOW like", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowNotLike(String value) {
            addCriterion("INFO_FLOW not like", value, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowIn(List<String> values) {
            addCriterion("INFO_FLOW in", values, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowNotIn(List<String> values) {
            addCriterion("INFO_FLOW not in", values, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowBetween(String value1, String value2) {
            addCriterion("INFO_FLOW between", value1, value2, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoFlowNotBetween(String value1, String value2) {
            addCriterion("INFO_FLOW not between", value1, value2, "infoFlow");
            return (Criteria) this;
        }

        public Criteria andInfoTitleIsNull() {
            addCriterion("INFO_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andInfoTitleIsNotNull() {
            addCriterion("INFO_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andInfoTitleEqualTo(String value) {
            addCriterion("INFO_TITLE =", value, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleNotEqualTo(String value) {
            addCriterion("INFO_TITLE <>", value, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleGreaterThan(String value) {
            addCriterion("INFO_TITLE >", value, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleGreaterThanOrEqualTo(String value) {
            addCriterion("INFO_TITLE >=", value, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleLessThan(String value) {
            addCriterion("INFO_TITLE <", value, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleLessThanOrEqualTo(String value) {
            addCriterion("INFO_TITLE <=", value, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleLike(String value) {
            addCriterion("INFO_TITLE like", value, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleNotLike(String value) {
            addCriterion("INFO_TITLE not like", value, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleIn(List<String> values) {
            addCriterion("INFO_TITLE in", values, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleNotIn(List<String> values) {
            addCriterion("INFO_TITLE not in", values, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleBetween(String value1, String value2) {
            addCriterion("INFO_TITLE between", value1, value2, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andInfoTitleNotBetween(String value1, String value2) {
            addCriterion("INFO_TITLE not between", value1, value2, "infoTitle");
            return (Criteria) this;
        }

        public Criteria andTitleImgIsNull() {
            addCriterion("TITLE_IMG is null");
            return (Criteria) this;
        }

        public Criteria andTitleImgIsNotNull() {
            addCriterion("TITLE_IMG is not null");
            return (Criteria) this;
        }

        public Criteria andTitleImgEqualTo(String value) {
            addCriterion("TITLE_IMG =", value, "titleImg");
            return (Criteria) this;
        }

        public Criteria andTitleImgNotEqualTo(String value) {
            addCriterion("TITLE_IMG <>", value, "titleImg");
            return (Criteria) this;
        }

        public Criteria andTitleImgGreaterThan(String value) {
            addCriterion("TITLE_IMG >", value, "titleImg");
            return (Criteria) this;
        }

        public Criteria andTitleImgGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE_IMG >=", value, "titleImg");
            return (Criteria) this;
        }

        public Criteria andTitleImgLessThan(String value) {
            addCriterion("TITLE_IMG <", value, "titleImg");
            return (Criteria) this;
        }

        public Criteria andTitleImgLessThanOrEqualTo(String value) {
            addCriterion("TITLE_IMG <=", value, "titleImg");
            return (Criteria) this;
        }

        public Criteria andTitleImgLike(String value) {
            addCriterion("TITLE_IMG like", value, "titleImg");
            return (Criteria) this;
        }

        public Criteria andTitleImgNotLike(String value) {
            addCriterion("TITLE_IMG not like", value, "titleImg");
            return (Criteria) this;
        }

        public Criteria andTitleImgIn(List<String> values) {
            addCriterion("TITLE_IMG in", values, "titleImg");
            return (Criteria) this;
        }

        public Criteria andTitleImgNotIn(List<String> values) {
            addCriterion("TITLE_IMG not in", values, "titleImg");
            return (Criteria) this;
        }

        public Criteria andTitleImgBetween(String value1, String value2) {
            addCriterion("TITLE_IMG between", value1, value2, "titleImg");
            return (Criteria) this;
        }

        public Criteria andTitleImgNotBetween(String value1, String value2) {
            addCriterion("TITLE_IMG not between", value1, value2, "titleImg");
            return (Criteria) this;
        }

        public Criteria andColumnIdIsNull() {
            addCriterion("COLUMN_ID is null");
            return (Criteria) this;
        }

        public Criteria andColumnIdIsNotNull() {
            addCriterion("COLUMN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andColumnIdEqualTo(String value) {
            addCriterion("COLUMN_ID =", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdNotEqualTo(String value) {
            addCriterion("COLUMN_ID <>", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdGreaterThan(String value) {
            addCriterion("COLUMN_ID >", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdGreaterThanOrEqualTo(String value) {
            addCriterion("COLUMN_ID >=", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdLessThan(String value) {
            addCriterion("COLUMN_ID <", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdLessThanOrEqualTo(String value) {
            addCriterion("COLUMN_ID <=", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdLike(String value) {
            addCriterion("COLUMN_ID like", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdNotLike(String value) {
            addCriterion("COLUMN_ID not like", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdIn(List<String> values) {
            addCriterion("COLUMN_ID in", values, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdNotIn(List<String> values) {
            addCriterion("COLUMN_ID not in", values, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdBetween(String value1, String value2) {
            addCriterion("COLUMN_ID between", value1, value2, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdNotBetween(String value1, String value2) {
            addCriterion("COLUMN_ID not between", value1, value2, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnNameIsNull() {
            addCriterion("COLUMN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andColumnNameIsNotNull() {
            addCriterion("COLUMN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andColumnNameEqualTo(String value) {
            addCriterion("COLUMN_NAME =", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotEqualTo(String value) {
            addCriterion("COLUMN_NAME <>", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameGreaterThan(String value) {
            addCriterion("COLUMN_NAME >", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameGreaterThanOrEqualTo(String value) {
            addCriterion("COLUMN_NAME >=", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameLessThan(String value) {
            addCriterion("COLUMN_NAME <", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameLessThanOrEqualTo(String value) {
            addCriterion("COLUMN_NAME <=", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameLike(String value) {
            addCriterion("COLUMN_NAME like", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotLike(String value) {
            addCriterion("COLUMN_NAME not like", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameIn(List<String> values) {
            addCriterion("COLUMN_NAME in", values, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotIn(List<String> values) {
            addCriterion("COLUMN_NAME not in", values, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameBetween(String value1, String value2) {
            addCriterion("COLUMN_NAME between", value1, value2, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotBetween(String value1, String value2) {
            addCriterion("COLUMN_NAME not between", value1, value2, "columnName");
            return (Criteria) this;
        }

        public Criteria andInfoStatusIdIsNull() {
            addCriterion("INFO_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andInfoStatusIdIsNotNull() {
            addCriterion("INFO_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInfoStatusIdEqualTo(String value) {
            addCriterion("INFO_STATUS_ID =", value, "infoStatusId");
            return (Criteria) this;
        }

        public Criteria andInfoStatusIdNotEqualTo(String value) {
            addCriterion("INFO_STATUS_ID <>", value, "infoStatusId");
            return (Criteria) this;
        }

        public Criteria andInfoStatusIdGreaterThan(String value) {
            addCriterion("INFO_STATUS_ID >", value, "infoStatusId");
            return (Criteria) this;
        }

        public Criteria andInfoStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("INFO_STATUS_ID >=", value, "infoStatusId");
            return (Criteria) this;
        }

        public Criteria andInfoStatusIdLessThan(String value) {
            addCriterion("INFO_STATUS_ID <", value, "infoStatusId");
            return (Criteria) this;
        }

        public Criteria andInfoStatusIdLessThanOrEqualTo(String value) {
            addCriterion("INFO_STATUS_ID <=", value, "infoStatusId");
            return (Criteria) this;
        }

        public Criteria andInfoStatusIdLike(String value) {
            addCriterion("INFO_STATUS_ID like", value, "infoStatusId");
            return (Criteria) this;
        }

        public Criteria andInfoStatusIdNotLike(String value) {
            addCriterion("INFO_STATUS_ID not like", value, "infoStatusId");
            return (Criteria) this;
        }

        public Criteria andInfoStatusIdIn(List<String> values) {
            addCriterion("INFO_STATUS_ID in", values, "infoStatusId");
            return (Criteria) this;
        }

        public Criteria andInfoStatusIdNotIn(List<String> values) {
            addCriterion("INFO_STATUS_ID not in", values, "infoStatusId");
            return (Criteria) this;
        }

        public Criteria andInfoStatusIdBetween(String value1, String value2) {
            addCriterion("INFO_STATUS_ID between", value1, value2, "infoStatusId");
            return (Criteria) this;
        }

        public Criteria andInfoStatusIdNotBetween(String value1, String value2) {
            addCriterion("INFO_STATUS_ID not between", value1, value2, "infoStatusId");
            return (Criteria) this;
        }

        public Criteria andInfoStatusNameIsNull() {
            addCriterion("INFO_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andInfoStatusNameIsNotNull() {
            addCriterion("INFO_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andInfoStatusNameEqualTo(String value) {
            addCriterion("INFO_STATUS_NAME =", value, "infoStatusName");
            return (Criteria) this;
        }

        public Criteria andInfoStatusNameNotEqualTo(String value) {
            addCriterion("INFO_STATUS_NAME <>", value, "infoStatusName");
            return (Criteria) this;
        }

        public Criteria andInfoStatusNameGreaterThan(String value) {
            addCriterion("INFO_STATUS_NAME >", value, "infoStatusName");
            return (Criteria) this;
        }

        public Criteria andInfoStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("INFO_STATUS_NAME >=", value, "infoStatusName");
            return (Criteria) this;
        }

        public Criteria andInfoStatusNameLessThan(String value) {
            addCriterion("INFO_STATUS_NAME <", value, "infoStatusName");
            return (Criteria) this;
        }

        public Criteria andInfoStatusNameLessThanOrEqualTo(String value) {
            addCriterion("INFO_STATUS_NAME <=", value, "infoStatusName");
            return (Criteria) this;
        }

        public Criteria andInfoStatusNameLike(String value) {
            addCriterion("INFO_STATUS_NAME like", value, "infoStatusName");
            return (Criteria) this;
        }

        public Criteria andInfoStatusNameNotLike(String value) {
            addCriterion("INFO_STATUS_NAME not like", value, "infoStatusName");
            return (Criteria) this;
        }

        public Criteria andInfoStatusNameIn(List<String> values) {
            addCriterion("INFO_STATUS_NAME in", values, "infoStatusName");
            return (Criteria) this;
        }

        public Criteria andInfoStatusNameNotIn(List<String> values) {
            addCriterion("INFO_STATUS_NAME not in", values, "infoStatusName");
            return (Criteria) this;
        }

        public Criteria andInfoStatusNameBetween(String value1, String value2) {
            addCriterion("INFO_STATUS_NAME between", value1, value2, "infoStatusName");
            return (Criteria) this;
        }

        public Criteria andInfoStatusNameNotBetween(String value1, String value2) {
            addCriterion("INFO_STATUS_NAME not between", value1, value2, "infoStatusName");
            return (Criteria) this;
        }

        public Criteria andInfoTimeIsNull() {
            addCriterion("INFO_TIME is null");
            return (Criteria) this;
        }

        public Criteria andInfoTimeIsNotNull() {
            addCriterion("INFO_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andInfoTimeEqualTo(String value) {
            addCriterion("INFO_TIME =", value, "infoTime");
            return (Criteria) this;
        }

        public Criteria andInfoTimeNotEqualTo(String value) {
            addCriterion("INFO_TIME <>", value, "infoTime");
            return (Criteria) this;
        }

        public Criteria andInfoTimeGreaterThan(String value) {
            addCriterion("INFO_TIME >", value, "infoTime");
            return (Criteria) this;
        }

        public Criteria andInfoTimeGreaterThanOrEqualTo(String value) {
            addCriterion("INFO_TIME >=", value, "infoTime");
            return (Criteria) this;
        }

        public Criteria andInfoTimeLessThan(String value) {
            addCriterion("INFO_TIME <", value, "infoTime");
            return (Criteria) this;
        }

        public Criteria andInfoTimeLessThanOrEqualTo(String value) {
            addCriterion("INFO_TIME <=", value, "infoTime");
            return (Criteria) this;
        }

        public Criteria andInfoTimeLike(String value) {
            addCriterion("INFO_TIME like", value, "infoTime");
            return (Criteria) this;
        }

        public Criteria andInfoTimeNotLike(String value) {
            addCriterion("INFO_TIME not like", value, "infoTime");
            return (Criteria) this;
        }

        public Criteria andInfoTimeIn(List<String> values) {
            addCriterion("INFO_TIME in", values, "infoTime");
            return (Criteria) this;
        }

        public Criteria andInfoTimeNotIn(List<String> values) {
            addCriterion("INFO_TIME not in", values, "infoTime");
            return (Criteria) this;
        }

        public Criteria andInfoTimeBetween(String value1, String value2) {
            addCriterion("INFO_TIME between", value1, value2, "infoTime");
            return (Criteria) this;
        }

        public Criteria andInfoTimeNotBetween(String value1, String value2) {
            addCriterion("INFO_TIME not between", value1, value2, "infoTime");
            return (Criteria) this;
        }

        public Criteria andPassTimeIsNull() {
            addCriterion("PASS_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPassTimeIsNotNull() {
            addCriterion("PASS_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPassTimeEqualTo(String value) {
            addCriterion("PASS_TIME =", value, "passTime");
            return (Criteria) this;
        }

        public Criteria andPassTimeNotEqualTo(String value) {
            addCriterion("PASS_TIME <>", value, "passTime");
            return (Criteria) this;
        }

        public Criteria andPassTimeGreaterThan(String value) {
            addCriterion("PASS_TIME >", value, "passTime");
            return (Criteria) this;
        }

        public Criteria andPassTimeGreaterThanOrEqualTo(String value) {
            addCriterion("PASS_TIME >=", value, "passTime");
            return (Criteria) this;
        }

        public Criteria andPassTimeLessThan(String value) {
            addCriterion("PASS_TIME <", value, "passTime");
            return (Criteria) this;
        }

        public Criteria andPassTimeLessThanOrEqualTo(String value) {
            addCriterion("PASS_TIME <=", value, "passTime");
            return (Criteria) this;
        }

        public Criteria andPassTimeLike(String value) {
            addCriterion("PASS_TIME like", value, "passTime");
            return (Criteria) this;
        }

        public Criteria andPassTimeNotLike(String value) {
            addCriterion("PASS_TIME not like", value, "passTime");
            return (Criteria) this;
        }

        public Criteria andPassTimeIn(List<String> values) {
            addCriterion("PASS_TIME in", values, "passTime");
            return (Criteria) this;
        }

        public Criteria andPassTimeNotIn(List<String> values) {
            addCriterion("PASS_TIME not in", values, "passTime");
            return (Criteria) this;
        }

        public Criteria andPassTimeBetween(String value1, String value2) {
            addCriterion("PASS_TIME between", value1, value2, "passTime");
            return (Criteria) this;
        }

        public Criteria andPassTimeNotBetween(String value1, String value2) {
            addCriterion("PASS_TIME not between", value1, value2, "passTime");
            return (Criteria) this;
        }

        public Criteria andPassUserFlowIsNull() {
            addCriterion("PASS_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andPassUserFlowIsNotNull() {
            addCriterion("PASS_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andPassUserFlowEqualTo(String value) {
            addCriterion("PASS_USER_FLOW =", value, "passUserFlow");
            return (Criteria) this;
        }

        public Criteria andPassUserFlowNotEqualTo(String value) {
            addCriterion("PASS_USER_FLOW <>", value, "passUserFlow");
            return (Criteria) this;
        }

        public Criteria andPassUserFlowGreaterThan(String value) {
            addCriterion("PASS_USER_FLOW >", value, "passUserFlow");
            return (Criteria) this;
        }

        public Criteria andPassUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PASS_USER_FLOW >=", value, "passUserFlow");
            return (Criteria) this;
        }

        public Criteria andPassUserFlowLessThan(String value) {
            addCriterion("PASS_USER_FLOW <", value, "passUserFlow");
            return (Criteria) this;
        }

        public Criteria andPassUserFlowLessThanOrEqualTo(String value) {
            addCriterion("PASS_USER_FLOW <=", value, "passUserFlow");
            return (Criteria) this;
        }

        public Criteria andPassUserFlowLike(String value) {
            addCriterion("PASS_USER_FLOW like", value, "passUserFlow");
            return (Criteria) this;
        }

        public Criteria andPassUserFlowNotLike(String value) {
            addCriterion("PASS_USER_FLOW not like", value, "passUserFlow");
            return (Criteria) this;
        }

        public Criteria andPassUserFlowIn(List<String> values) {
            addCriterion("PASS_USER_FLOW in", values, "passUserFlow");
            return (Criteria) this;
        }

        public Criteria andPassUserFlowNotIn(List<String> values) {
            addCriterion("PASS_USER_FLOW not in", values, "passUserFlow");
            return (Criteria) this;
        }

        public Criteria andPassUserFlowBetween(String value1, String value2) {
            addCriterion("PASS_USER_FLOW between", value1, value2, "passUserFlow");
            return (Criteria) this;
        }

        public Criteria andPassUserFlowNotBetween(String value1, String value2) {
            addCriterion("PASS_USER_FLOW not between", value1, value2, "passUserFlow");
            return (Criteria) this;
        }

        public Criteria andCancelTimeIsNull() {
            addCriterion("CANCEL_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCancelTimeIsNotNull() {
            addCriterion("CANCEL_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCancelTimeEqualTo(String value) {
            addCriterion("CANCEL_TIME =", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeNotEqualTo(String value) {
            addCriterion("CANCEL_TIME <>", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeGreaterThan(String value) {
            addCriterion("CANCEL_TIME >", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CANCEL_TIME >=", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeLessThan(String value) {
            addCriterion("CANCEL_TIME <", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeLessThanOrEqualTo(String value) {
            addCriterion("CANCEL_TIME <=", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeLike(String value) {
            addCriterion("CANCEL_TIME like", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeNotLike(String value) {
            addCriterion("CANCEL_TIME not like", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeIn(List<String> values) {
            addCriterion("CANCEL_TIME in", values, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeNotIn(List<String> values) {
            addCriterion("CANCEL_TIME not in", values, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeBetween(String value1, String value2) {
            addCriterion("CANCEL_TIME between", value1, value2, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeNotBetween(String value1, String value2) {
            addCriterion("CANCEL_TIME not between", value1, value2, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelUserFlowIsNull() {
            addCriterion("CANCEL_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andCancelUserFlowIsNotNull() {
            addCriterion("CANCEL_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andCancelUserFlowEqualTo(String value) {
            addCriterion("CANCEL_USER_FLOW =", value, "cancelUserFlow");
            return (Criteria) this;
        }

        public Criteria andCancelUserFlowNotEqualTo(String value) {
            addCriterion("CANCEL_USER_FLOW <>", value, "cancelUserFlow");
            return (Criteria) this;
        }

        public Criteria andCancelUserFlowGreaterThan(String value) {
            addCriterion("CANCEL_USER_FLOW >", value, "cancelUserFlow");
            return (Criteria) this;
        }

        public Criteria andCancelUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CANCEL_USER_FLOW >=", value, "cancelUserFlow");
            return (Criteria) this;
        }

        public Criteria andCancelUserFlowLessThan(String value) {
            addCriterion("CANCEL_USER_FLOW <", value, "cancelUserFlow");
            return (Criteria) this;
        }

        public Criteria andCancelUserFlowLessThanOrEqualTo(String value) {
            addCriterion("CANCEL_USER_FLOW <=", value, "cancelUserFlow");
            return (Criteria) this;
        }

        public Criteria andCancelUserFlowLike(String value) {
            addCriterion("CANCEL_USER_FLOW like", value, "cancelUserFlow");
            return (Criteria) this;
        }

        public Criteria andCancelUserFlowNotLike(String value) {
            addCriterion("CANCEL_USER_FLOW not like", value, "cancelUserFlow");
            return (Criteria) this;
        }

        public Criteria andCancelUserFlowIn(List<String> values) {
            addCriterion("CANCEL_USER_FLOW in", values, "cancelUserFlow");
            return (Criteria) this;
        }

        public Criteria andCancelUserFlowNotIn(List<String> values) {
            addCriterion("CANCEL_USER_FLOW not in", values, "cancelUserFlow");
            return (Criteria) this;
        }

        public Criteria andCancelUserFlowBetween(String value1, String value2) {
            addCriterion("CANCEL_USER_FLOW between", value1, value2, "cancelUserFlow");
            return (Criteria) this;
        }

        public Criteria andCancelUserFlowNotBetween(String value1, String value2) {
            addCriterion("CANCEL_USER_FLOW not between", value1, value2, "cancelUserFlow");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeIsNull() {
            addCriterion("DELETE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeIsNotNull() {
            addCriterion("DELETE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeEqualTo(String value) {
            addCriterion("DELETE_TIME =", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeNotEqualTo(String value) {
            addCriterion("DELETE_TIME <>", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeGreaterThan(String value) {
            addCriterion("DELETE_TIME >", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeGreaterThanOrEqualTo(String value) {
            addCriterion("DELETE_TIME >=", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeLessThan(String value) {
            addCriterion("DELETE_TIME <", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeLessThanOrEqualTo(String value) {
            addCriterion("DELETE_TIME <=", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeLike(String value) {
            addCriterion("DELETE_TIME like", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeNotLike(String value) {
            addCriterion("DELETE_TIME not like", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeIn(List<String> values) {
            addCriterion("DELETE_TIME in", values, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeNotIn(List<String> values) {
            addCriterion("DELETE_TIME not in", values, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeBetween(String value1, String value2) {
            addCriterion("DELETE_TIME between", value1, value2, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeNotBetween(String value1, String value2) {
            addCriterion("DELETE_TIME not between", value1, value2, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteUserFlowIsNull() {
            addCriterion("DELETE_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andDeleteUserFlowIsNotNull() {
            addCriterion("DELETE_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteUserFlowEqualTo(String value) {
            addCriterion("DELETE_USER_FLOW =", value, "deleteUserFlow");
            return (Criteria) this;
        }

        public Criteria andDeleteUserFlowNotEqualTo(String value) {
            addCriterion("DELETE_USER_FLOW <>", value, "deleteUserFlow");
            return (Criteria) this;
        }

        public Criteria andDeleteUserFlowGreaterThan(String value) {
            addCriterion("DELETE_USER_FLOW >", value, "deleteUserFlow");
            return (Criteria) this;
        }

        public Criteria andDeleteUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("DELETE_USER_FLOW >=", value, "deleteUserFlow");
            return (Criteria) this;
        }

        public Criteria andDeleteUserFlowLessThan(String value) {
            addCriterion("DELETE_USER_FLOW <", value, "deleteUserFlow");
            return (Criteria) this;
        }

        public Criteria andDeleteUserFlowLessThanOrEqualTo(String value) {
            addCriterion("DELETE_USER_FLOW <=", value, "deleteUserFlow");
            return (Criteria) this;
        }

        public Criteria andDeleteUserFlowLike(String value) {
            addCriterion("DELETE_USER_FLOW like", value, "deleteUserFlow");
            return (Criteria) this;
        }

        public Criteria andDeleteUserFlowNotLike(String value) {
            addCriterion("DELETE_USER_FLOW not like", value, "deleteUserFlow");
            return (Criteria) this;
        }

        public Criteria andDeleteUserFlowIn(List<String> values) {
            addCriterion("DELETE_USER_FLOW in", values, "deleteUserFlow");
            return (Criteria) this;
        }

        public Criteria andDeleteUserFlowNotIn(List<String> values) {
            addCriterion("DELETE_USER_FLOW not in", values, "deleteUserFlow");
            return (Criteria) this;
        }

        public Criteria andDeleteUserFlowBetween(String value1, String value2) {
            addCriterion("DELETE_USER_FLOW between", value1, value2, "deleteUserFlow");
            return (Criteria) this;
        }

        public Criteria andDeleteUserFlowNotBetween(String value1, String value2) {
            addCriterion("DELETE_USER_FLOW not between", value1, value2, "deleteUserFlow");
            return (Criteria) this;
        }

        public Criteria andInfoKeywordIsNull() {
            addCriterion("INFO_KEYWORD is null");
            return (Criteria) this;
        }

        public Criteria andInfoKeywordIsNotNull() {
            addCriterion("INFO_KEYWORD is not null");
            return (Criteria) this;
        }

        public Criteria andInfoKeywordEqualTo(String value) {
            addCriterion("INFO_KEYWORD =", value, "infoKeyword");
            return (Criteria) this;
        }

        public Criteria andInfoKeywordNotEqualTo(String value) {
            addCriterion("INFO_KEYWORD <>", value, "infoKeyword");
            return (Criteria) this;
        }

        public Criteria andInfoKeywordGreaterThan(String value) {
            addCriterion("INFO_KEYWORD >", value, "infoKeyword");
            return (Criteria) this;
        }

        public Criteria andInfoKeywordGreaterThanOrEqualTo(String value) {
            addCriterion("INFO_KEYWORD >=", value, "infoKeyword");
            return (Criteria) this;
        }

        public Criteria andInfoKeywordLessThan(String value) {
            addCriterion("INFO_KEYWORD <", value, "infoKeyword");
            return (Criteria) this;
        }

        public Criteria andInfoKeywordLessThanOrEqualTo(String value) {
            addCriterion("INFO_KEYWORD <=", value, "infoKeyword");
            return (Criteria) this;
        }

        public Criteria andInfoKeywordLike(String value) {
            addCriterion("INFO_KEYWORD like", value, "infoKeyword");
            return (Criteria) this;
        }

        public Criteria andInfoKeywordNotLike(String value) {
            addCriterion("INFO_KEYWORD not like", value, "infoKeyword");
            return (Criteria) this;
        }

        public Criteria andInfoKeywordIn(List<String> values) {
            addCriterion("INFO_KEYWORD in", values, "infoKeyword");
            return (Criteria) this;
        }

        public Criteria andInfoKeywordNotIn(List<String> values) {
            addCriterion("INFO_KEYWORD not in", values, "infoKeyword");
            return (Criteria) this;
        }

        public Criteria andInfoKeywordBetween(String value1, String value2) {
            addCriterion("INFO_KEYWORD between", value1, value2, "infoKeyword");
            return (Criteria) this;
        }

        public Criteria andInfoKeywordNotBetween(String value1, String value2) {
            addCriterion("INFO_KEYWORD not between", value1, value2, "infoKeyword");
            return (Criteria) this;
        }

        public Criteria andIsTopIsNull() {
            addCriterion("IS_TOP is null");
            return (Criteria) this;
        }

        public Criteria andIsTopIsNotNull() {
            addCriterion("IS_TOP is not null");
            return (Criteria) this;
        }

        public Criteria andIsTopEqualTo(String value) {
            addCriterion("IS_TOP =", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopNotEqualTo(String value) {
            addCriterion("IS_TOP <>", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopGreaterThan(String value) {
            addCriterion("IS_TOP >", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopGreaterThanOrEqualTo(String value) {
            addCriterion("IS_TOP >=", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopLessThan(String value) {
            addCriterion("IS_TOP <", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopLessThanOrEqualTo(String value) {
            addCriterion("IS_TOP <=", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopLike(String value) {
            addCriterion("IS_TOP like", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopNotLike(String value) {
            addCriterion("IS_TOP not like", value, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopIn(List<String> values) {
            addCriterion("IS_TOP in", values, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopNotIn(List<String> values) {
            addCriterion("IS_TOP not in", values, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopBetween(String value1, String value2) {
            addCriterion("IS_TOP between", value1, value2, "isTop");
            return (Criteria) this;
        }

        public Criteria andIsTopNotBetween(String value1, String value2) {
            addCriterion("IS_TOP not between", value1, value2, "isTop");
            return (Criteria) this;
        }

        public Criteria andViewNumIsNull() {
            addCriterion("VIEW_NUM is null");
            return (Criteria) this;
        }

        public Criteria andViewNumIsNotNull() {
            addCriterion("VIEW_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andViewNumEqualTo(Long value) {
            addCriterion("VIEW_NUM =", value, "viewNum");
            return (Criteria) this;
        }

        public Criteria andViewNumNotEqualTo(Long value) {
            addCriterion("VIEW_NUM <>", value, "viewNum");
            return (Criteria) this;
        }

        public Criteria andViewNumGreaterThan(Long value) {
            addCriterion("VIEW_NUM >", value, "viewNum");
            return (Criteria) this;
        }

        public Criteria andViewNumGreaterThanOrEqualTo(Long value) {
            addCriterion("VIEW_NUM >=", value, "viewNum");
            return (Criteria) this;
        }

        public Criteria andViewNumLessThan(Long value) {
            addCriterion("VIEW_NUM <", value, "viewNum");
            return (Criteria) this;
        }

        public Criteria andViewNumLessThanOrEqualTo(Long value) {
            addCriterion("VIEW_NUM <=", value, "viewNum");
            return (Criteria) this;
        }

        public Criteria andViewNumIn(List<Long> values) {
            addCriterion("VIEW_NUM in", values, "viewNum");
            return (Criteria) this;
        }

        public Criteria andViewNumNotIn(List<Long> values) {
            addCriterion("VIEW_NUM not in", values, "viewNum");
            return (Criteria) this;
        }

        public Criteria andViewNumBetween(Long value1, Long value2) {
            addCriterion("VIEW_NUM between", value1, value2, "viewNum");
            return (Criteria) this;
        }

        public Criteria andViewNumNotBetween(Long value1, Long value2) {
            addCriterion("VIEW_NUM not between", value1, value2, "viewNum");
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

        public Criteria andOrgFlowIsNull() {
            addCriterion("ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andOrgFlowIsNotNull() {
            addCriterion("ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andOrgFlowEqualTo(String value) {
            addCriterion("ORG_FLOW =", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotEqualTo(String value) {
            addCriterion("ORG_FLOW <>", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGreaterThan(String value) {
            addCriterion("ORG_FLOW >", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_FLOW >=", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowLessThan(String value) {
            addCriterion("ORG_FLOW <", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("ORG_FLOW <=", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowLike(String value) {
            addCriterion("ORG_FLOW like", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotLike(String value) {
            addCriterion("ORG_FLOW not like", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowIn(List<String> values) {
            addCriterion("ORG_FLOW in", values, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotIn(List<String> values) {
            addCriterion("ORG_FLOW not in", values, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowBetween(String value1, String value2) {
            addCriterion("ORG_FLOW between", value1, value2, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotBetween(String value1, String value2) {
            addCriterion("ORG_FLOW not between", value1, value2, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNull() {
            addCriterion("ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNotNull() {
            addCriterion("ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEqualTo(String value) {
            addCriterion("ORG_NAME =", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotEqualTo(String value) {
            addCriterion("ORG_NAME <>", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThan(String value) {
            addCriterion("ORG_NAME >", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_NAME >=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThan(String value) {
            addCriterion("ORG_NAME <", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_NAME <=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLike(String value) {
            addCriterion("ORG_NAME like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotLike(String value) {
            addCriterion("ORG_NAME not like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIn(List<String> values) {
            addCriterion("ORG_NAME in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotIn(List<String> values) {
            addCriterion("ORG_NAME not in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameBetween(String value1, String value2) {
            addCriterion("ORG_NAME between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotBetween(String value1, String value2) {
            addCriterion("ORG_NAME not between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNull() {
            addCriterion("CITY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNotNull() {
            addCriterion("CITY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCityIdEqualTo(String value) {
            addCriterion("CITY_ID =", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotEqualTo(String value) {
            addCriterion("CITY_ID <>", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThan(String value) {
            addCriterion("CITY_ID >", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThanOrEqualTo(String value) {
            addCriterion("CITY_ID >=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThan(String value) {
            addCriterion("CITY_ID <", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThanOrEqualTo(String value) {
            addCriterion("CITY_ID <=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLike(String value) {
            addCriterion("CITY_ID like", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotLike(String value) {
            addCriterion("CITY_ID not like", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdIn(List<String> values) {
            addCriterion("CITY_ID in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotIn(List<String> values) {
            addCriterion("CITY_ID not in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdBetween(String value1, String value2) {
            addCriterion("CITY_ID between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotBetween(String value1, String value2) {
            addCriterion("CITY_ID not between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNull() {
            addCriterion("CITY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNotNull() {
            addCriterion("CITY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCityNameEqualTo(String value) {
            addCriterion("CITY_NAME =", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotEqualTo(String value) {
            addCriterion("CITY_NAME <>", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThan(String value) {
            addCriterion("CITY_NAME >", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("CITY_NAME >=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThan(String value) {
            addCriterion("CITY_NAME <", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThanOrEqualTo(String value) {
            addCriterion("CITY_NAME <=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLike(String value) {
            addCriterion("CITY_NAME like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotLike(String value) {
            addCriterion("CITY_NAME not like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameIn(List<String> values) {
            addCriterion("CITY_NAME in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotIn(List<String> values) {
            addCriterion("CITY_NAME not in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameBetween(String value1, String value2) {
            addCriterion("CITY_NAME between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotBetween(String value1, String value2) {
            addCriterion("CITY_NAME not between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andWestEaetTypeIsNull() {
            addCriterion("WEST_EAET_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andWestEaetTypeIsNotNull() {
            addCriterion("WEST_EAET_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andWestEaetTypeEqualTo(String value) {
            addCriterion("WEST_EAET_TYPE =", value, "westEaetType");
            return (Criteria) this;
        }

        public Criteria andWestEaetTypeNotEqualTo(String value) {
            addCriterion("WEST_EAET_TYPE <>", value, "westEaetType");
            return (Criteria) this;
        }

        public Criteria andWestEaetTypeGreaterThan(String value) {
            addCriterion("WEST_EAET_TYPE >", value, "westEaetType");
            return (Criteria) this;
        }

        public Criteria andWestEaetTypeGreaterThanOrEqualTo(String value) {
            addCriterion("WEST_EAET_TYPE >=", value, "westEaetType");
            return (Criteria) this;
        }

        public Criteria andWestEaetTypeLessThan(String value) {
            addCriterion("WEST_EAET_TYPE <", value, "westEaetType");
            return (Criteria) this;
        }

        public Criteria andWestEaetTypeLessThanOrEqualTo(String value) {
            addCriterion("WEST_EAET_TYPE <=", value, "westEaetType");
            return (Criteria) this;
        }

        public Criteria andWestEaetTypeLike(String value) {
            addCriterion("WEST_EAET_TYPE like", value, "westEaetType");
            return (Criteria) this;
        }

        public Criteria andWestEaetTypeNotLike(String value) {
            addCriterion("WEST_EAET_TYPE not like", value, "westEaetType");
            return (Criteria) this;
        }

        public Criteria andWestEaetTypeIn(List<String> values) {
            addCriterion("WEST_EAET_TYPE in", values, "westEaetType");
            return (Criteria) this;
        }

        public Criteria andWestEaetTypeNotIn(List<String> values) {
            addCriterion("WEST_EAET_TYPE not in", values, "westEaetType");
            return (Criteria) this;
        }

        public Criteria andWestEaetTypeBetween(String value1, String value2) {
            addCriterion("WEST_EAET_TYPE between", value1, value2, "westEaetType");
            return (Criteria) this;
        }

        public Criteria andWestEaetTypeNotBetween(String value1, String value2) {
            addCriterion("WEST_EAET_TYPE not between", value1, value2, "westEaetType");
            return (Criteria) this;
        }

        public Criteria andInfoUserNameIsNull() {
            addCriterion("INFO_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andInfoUserNameIsNotNull() {
            addCriterion("INFO_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andInfoUserNameEqualTo(String value) {
            addCriterion("INFO_USER_NAME =", value, "infoUserName");
            return (Criteria) this;
        }

        public Criteria andInfoUserNameNotEqualTo(String value) {
            addCriterion("INFO_USER_NAME <>", value, "infoUserName");
            return (Criteria) this;
        }

        public Criteria andInfoUserNameGreaterThan(String value) {
            addCriterion("INFO_USER_NAME >", value, "infoUserName");
            return (Criteria) this;
        }

        public Criteria andInfoUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("INFO_USER_NAME >=", value, "infoUserName");
            return (Criteria) this;
        }

        public Criteria andInfoUserNameLessThan(String value) {
            addCriterion("INFO_USER_NAME <", value, "infoUserName");
            return (Criteria) this;
        }

        public Criteria andInfoUserNameLessThanOrEqualTo(String value) {
            addCriterion("INFO_USER_NAME <=", value, "infoUserName");
            return (Criteria) this;
        }

        public Criteria andInfoUserNameLike(String value) {
            addCriterion("INFO_USER_NAME like", value, "infoUserName");
            return (Criteria) this;
        }

        public Criteria andInfoUserNameNotLike(String value) {
            addCriterion("INFO_USER_NAME not like", value, "infoUserName");
            return (Criteria) this;
        }

        public Criteria andInfoUserNameIn(List<String> values) {
            addCriterion("INFO_USER_NAME in", values, "infoUserName");
            return (Criteria) this;
        }

        public Criteria andInfoUserNameNotIn(List<String> values) {
            addCriterion("INFO_USER_NAME not in", values, "infoUserName");
            return (Criteria) this;
        }

        public Criteria andInfoUserNameBetween(String value1, String value2) {
            addCriterion("INFO_USER_NAME between", value1, value2, "infoUserName");
            return (Criteria) this;
        }

        public Criteria andInfoUserNameNotBetween(String value1, String value2) {
            addCriterion("INFO_USER_NAME not between", value1, value2, "infoUserName");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("AUTHOR is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("AUTHOR is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("AUTHOR =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("AUTHOR <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("AUTHOR >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("AUTHOR >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("AUTHOR <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("AUTHOR <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("AUTHOR like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("AUTHOR not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("AUTHOR in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("AUTHOR not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("AUTHOR between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("AUTHOR not between", value1, value2, "author");
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