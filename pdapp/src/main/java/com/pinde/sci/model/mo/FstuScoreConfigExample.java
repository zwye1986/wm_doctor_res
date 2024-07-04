package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class FstuScoreConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FstuScoreConfigExample() {
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

        public Criteria andScoreTypeNameIsNull() {
            addCriterion("SCORE_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameIsNotNull() {
            addCriterion("SCORE_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameEqualTo(String value) {
            addCriterion("SCORE_TYPE_NAME =", value, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameNotEqualTo(String value) {
            addCriterion("SCORE_TYPE_NAME <>", value, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameGreaterThan(String value) {
            addCriterion("SCORE_TYPE_NAME >", value, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_TYPE_NAME >=", value, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameLessThan(String value) {
            addCriterion("SCORE_TYPE_NAME <", value, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameLessThanOrEqualTo(String value) {
            addCriterion("SCORE_TYPE_NAME <=", value, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameLike(String value) {
            addCriterion("SCORE_TYPE_NAME like", value, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameNotLike(String value) {
            addCriterion("SCORE_TYPE_NAME not like", value, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameIn(List<String> values) {
            addCriterion("SCORE_TYPE_NAME in", values, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameNotIn(List<String> values) {
            addCriterion("SCORE_TYPE_NAME not in", values, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameBetween(String value1, String value2) {
            addCriterion("SCORE_TYPE_NAME between", value1, value2, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeNameNotBetween(String value1, String value2) {
            addCriterion("SCORE_TYPE_NAME not between", value1, value2, "scoreTypeName");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdIsNull() {
            addCriterion("SCORE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdIsNotNull() {
            addCriterion("SCORE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdEqualTo(String value) {
            addCriterion("SCORE_TYPE_ID =", value, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdNotEqualTo(String value) {
            addCriterion("SCORE_TYPE_ID <>", value, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdGreaterThan(String value) {
            addCriterion("SCORE_TYPE_ID >", value, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_TYPE_ID >=", value, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdLessThan(String value) {
            addCriterion("SCORE_TYPE_ID <", value, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdLessThanOrEqualTo(String value) {
            addCriterion("SCORE_TYPE_ID <=", value, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdLike(String value) {
            addCriterion("SCORE_TYPE_ID like", value, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdNotLike(String value) {
            addCriterion("SCORE_TYPE_ID not like", value, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdIn(List<String> values) {
            addCriterion("SCORE_TYPE_ID in", values, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdNotIn(List<String> values) {
            addCriterion("SCORE_TYPE_ID not in", values, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdBetween(String value1, String value2) {
            addCriterion("SCORE_TYPE_ID between", value1, value2, "scoreTypeId");
            return (Criteria) this;
        }

        public Criteria andScoreTypeIdNotBetween(String value1, String value2) {
            addCriterion("SCORE_TYPE_ID not between", value1, value2, "scoreTypeId");
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

        public Criteria andScoreItemNameIsNull() {
            addCriterion("SCORE_ITEM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andScoreItemNameIsNotNull() {
            addCriterion("SCORE_ITEM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andScoreItemNameEqualTo(String value) {
            addCriterion("SCORE_ITEM_NAME =", value, "scoreItemName");
            return (Criteria) this;
        }

        public Criteria andScoreItemNameNotEqualTo(String value) {
            addCriterion("SCORE_ITEM_NAME <>", value, "scoreItemName");
            return (Criteria) this;
        }

        public Criteria andScoreItemNameGreaterThan(String value) {
            addCriterion("SCORE_ITEM_NAME >", value, "scoreItemName");
            return (Criteria) this;
        }

        public Criteria andScoreItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_ITEM_NAME >=", value, "scoreItemName");
            return (Criteria) this;
        }

        public Criteria andScoreItemNameLessThan(String value) {
            addCriterion("SCORE_ITEM_NAME <", value, "scoreItemName");
            return (Criteria) this;
        }

        public Criteria andScoreItemNameLessThanOrEqualTo(String value) {
            addCriterion("SCORE_ITEM_NAME <=", value, "scoreItemName");
            return (Criteria) this;
        }

        public Criteria andScoreItemNameLike(String value) {
            addCriterion("SCORE_ITEM_NAME like", value, "scoreItemName");
            return (Criteria) this;
        }

        public Criteria andScoreItemNameNotLike(String value) {
            addCriterion("SCORE_ITEM_NAME not like", value, "scoreItemName");
            return (Criteria) this;
        }

        public Criteria andScoreItemNameIn(List<String> values) {
            addCriterion("SCORE_ITEM_NAME in", values, "scoreItemName");
            return (Criteria) this;
        }

        public Criteria andScoreItemNameNotIn(List<String> values) {
            addCriterion("SCORE_ITEM_NAME not in", values, "scoreItemName");
            return (Criteria) this;
        }

        public Criteria andScoreItemNameBetween(String value1, String value2) {
            addCriterion("SCORE_ITEM_NAME between", value1, value2, "scoreItemName");
            return (Criteria) this;
        }

        public Criteria andScoreItemNameNotBetween(String value1, String value2) {
            addCriterion("SCORE_ITEM_NAME not between", value1, value2, "scoreItemName");
            return (Criteria) this;
        }

        public Criteria andScoreItemIdIsNull() {
            addCriterion("SCORE_ITEM_ID is null");
            return (Criteria) this;
        }

        public Criteria andScoreItemIdIsNotNull() {
            addCriterion("SCORE_ITEM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andScoreItemIdEqualTo(String value) {
            addCriterion("SCORE_ITEM_ID =", value, "scoreItemId");
            return (Criteria) this;
        }

        public Criteria andScoreItemIdNotEqualTo(String value) {
            addCriterion("SCORE_ITEM_ID <>", value, "scoreItemId");
            return (Criteria) this;
        }

        public Criteria andScoreItemIdGreaterThan(String value) {
            addCriterion("SCORE_ITEM_ID >", value, "scoreItemId");
            return (Criteria) this;
        }

        public Criteria andScoreItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE_ITEM_ID >=", value, "scoreItemId");
            return (Criteria) this;
        }

        public Criteria andScoreItemIdLessThan(String value) {
            addCriterion("SCORE_ITEM_ID <", value, "scoreItemId");
            return (Criteria) this;
        }

        public Criteria andScoreItemIdLessThanOrEqualTo(String value) {
            addCriterion("SCORE_ITEM_ID <=", value, "scoreItemId");
            return (Criteria) this;
        }

        public Criteria andScoreItemIdLike(String value) {
            addCriterion("SCORE_ITEM_ID like", value, "scoreItemId");
            return (Criteria) this;
        }

        public Criteria andScoreItemIdNotLike(String value) {
            addCriterion("SCORE_ITEM_ID not like", value, "scoreItemId");
            return (Criteria) this;
        }

        public Criteria andScoreItemIdIn(List<String> values) {
            addCriterion("SCORE_ITEM_ID in", values, "scoreItemId");
            return (Criteria) this;
        }

        public Criteria andScoreItemIdNotIn(List<String> values) {
            addCriterion("SCORE_ITEM_ID not in", values, "scoreItemId");
            return (Criteria) this;
        }

        public Criteria andScoreItemIdBetween(String value1, String value2) {
            addCriterion("SCORE_ITEM_ID between", value1, value2, "scoreItemId");
            return (Criteria) this;
        }

        public Criteria andScoreItemIdNotBetween(String value1, String value2) {
            addCriterion("SCORE_ITEM_ID not between", value1, value2, "scoreItemId");
            return (Criteria) this;
        }

        public Criteria andExecutionNameIsNull() {
            addCriterion("EXECUTION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andExecutionNameIsNotNull() {
            addCriterion("EXECUTION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andExecutionNameEqualTo(String value) {
            addCriterion("EXECUTION_NAME =", value, "executionName");
            return (Criteria) this;
        }

        public Criteria andExecutionNameNotEqualTo(String value) {
            addCriterion("EXECUTION_NAME <>", value, "executionName");
            return (Criteria) this;
        }

        public Criteria andExecutionNameGreaterThan(String value) {
            addCriterion("EXECUTION_NAME >", value, "executionName");
            return (Criteria) this;
        }

        public Criteria andExecutionNameGreaterThanOrEqualTo(String value) {
            addCriterion("EXECUTION_NAME >=", value, "executionName");
            return (Criteria) this;
        }

        public Criteria andExecutionNameLessThan(String value) {
            addCriterion("EXECUTION_NAME <", value, "executionName");
            return (Criteria) this;
        }

        public Criteria andExecutionNameLessThanOrEqualTo(String value) {
            addCriterion("EXECUTION_NAME <=", value, "executionName");
            return (Criteria) this;
        }

        public Criteria andExecutionNameLike(String value) {
            addCriterion("EXECUTION_NAME like", value, "executionName");
            return (Criteria) this;
        }

        public Criteria andExecutionNameNotLike(String value) {
            addCriterion("EXECUTION_NAME not like", value, "executionName");
            return (Criteria) this;
        }

        public Criteria andExecutionNameIn(List<String> values) {
            addCriterion("EXECUTION_NAME in", values, "executionName");
            return (Criteria) this;
        }

        public Criteria andExecutionNameNotIn(List<String> values) {
            addCriterion("EXECUTION_NAME not in", values, "executionName");
            return (Criteria) this;
        }

        public Criteria andExecutionNameBetween(String value1, String value2) {
            addCriterion("EXECUTION_NAME between", value1, value2, "executionName");
            return (Criteria) this;
        }

        public Criteria andExecutionNameNotBetween(String value1, String value2) {
            addCriterion("EXECUTION_NAME not between", value1, value2, "executionName");
            return (Criteria) this;
        }

        public Criteria andExecutionIdIsNull() {
            addCriterion("EXECUTION_ID is null");
            return (Criteria) this;
        }

        public Criteria andExecutionIdIsNotNull() {
            addCriterion("EXECUTION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExecutionIdEqualTo(String value) {
            addCriterion("EXECUTION_ID =", value, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdNotEqualTo(String value) {
            addCriterion("EXECUTION_ID <>", value, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdGreaterThan(String value) {
            addCriterion("EXECUTION_ID >", value, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdGreaterThanOrEqualTo(String value) {
            addCriterion("EXECUTION_ID >=", value, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdLessThan(String value) {
            addCriterion("EXECUTION_ID <", value, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdLessThanOrEqualTo(String value) {
            addCriterion("EXECUTION_ID <=", value, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdLike(String value) {
            addCriterion("EXECUTION_ID like", value, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdNotLike(String value) {
            addCriterion("EXECUTION_ID not like", value, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdIn(List<String> values) {
            addCriterion("EXECUTION_ID in", values, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdNotIn(List<String> values) {
            addCriterion("EXECUTION_ID not in", values, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdBetween(String value1, String value2) {
            addCriterion("EXECUTION_ID between", value1, value2, "executionId");
            return (Criteria) this;
        }

        public Criteria andExecutionIdNotBetween(String value1, String value2) {
            addCriterion("EXECUTION_ID not between", value1, value2, "executionId");
            return (Criteria) this;
        }

        public Criteria andMiurAuditFlagIsNull() {
            addCriterion("MIUR_AUDIT_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andMiurAuditFlagIsNotNull() {
            addCriterion("MIUR_AUDIT_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andMiurAuditFlagEqualTo(String value) {
            addCriterion("MIUR_AUDIT_FLAG =", value, "miurAuditFlag");
            return (Criteria) this;
        }

        public Criteria andMiurAuditFlagNotEqualTo(String value) {
            addCriterion("MIUR_AUDIT_FLAG <>", value, "miurAuditFlag");
            return (Criteria) this;
        }

        public Criteria andMiurAuditFlagGreaterThan(String value) {
            addCriterion("MIUR_AUDIT_FLAG >", value, "miurAuditFlag");
            return (Criteria) this;
        }

        public Criteria andMiurAuditFlagGreaterThanOrEqualTo(String value) {
            addCriterion("MIUR_AUDIT_FLAG >=", value, "miurAuditFlag");
            return (Criteria) this;
        }

        public Criteria andMiurAuditFlagLessThan(String value) {
            addCriterion("MIUR_AUDIT_FLAG <", value, "miurAuditFlag");
            return (Criteria) this;
        }

        public Criteria andMiurAuditFlagLessThanOrEqualTo(String value) {
            addCriterion("MIUR_AUDIT_FLAG <=", value, "miurAuditFlag");
            return (Criteria) this;
        }

        public Criteria andMiurAuditFlagLike(String value) {
            addCriterion("MIUR_AUDIT_FLAG like", value, "miurAuditFlag");
            return (Criteria) this;
        }

        public Criteria andMiurAuditFlagNotLike(String value) {
            addCriterion("MIUR_AUDIT_FLAG not like", value, "miurAuditFlag");
            return (Criteria) this;
        }

        public Criteria andMiurAuditFlagIn(List<String> values) {
            addCriterion("MIUR_AUDIT_FLAG in", values, "miurAuditFlag");
            return (Criteria) this;
        }

        public Criteria andMiurAuditFlagNotIn(List<String> values) {
            addCriterion("MIUR_AUDIT_FLAG not in", values, "miurAuditFlag");
            return (Criteria) this;
        }

        public Criteria andMiurAuditFlagBetween(String value1, String value2) {
            addCriterion("MIUR_AUDIT_FLAG between", value1, value2, "miurAuditFlag");
            return (Criteria) this;
        }

        public Criteria andMiurAuditFlagNotBetween(String value1, String value2) {
            addCriterion("MIUR_AUDIT_FLAG not between", value1, value2, "miurAuditFlag");
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

        public Criteria andScoreIsNull() {
            addCriterion("SCORE is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(String value) {
            addCriterion("SCORE =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(String value) {
            addCriterion("SCORE <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(String value) {
            addCriterion("SCORE >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(String value) {
            addCriterion("SCORE >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(String value) {
            addCriterion("SCORE <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(String value) {
            addCriterion("SCORE <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLike(String value) {
            addCriterion("SCORE like", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotLike(String value) {
            addCriterion("SCORE not like", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<String> values) {
            addCriterion("SCORE in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<String> values) {
            addCriterion("SCORE not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(String value1, String value2) {
            addCriterion("SCORE between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(String value1, String value2) {
            addCriterion("SCORE not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andMaxScoreIsNull() {
            addCriterion("MAX_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andMaxScoreIsNotNull() {
            addCriterion("MAX_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andMaxScoreEqualTo(String value) {
            addCriterion("MAX_SCORE =", value, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreNotEqualTo(String value) {
            addCriterion("MAX_SCORE <>", value, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreGreaterThan(String value) {
            addCriterion("MAX_SCORE >", value, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreGreaterThanOrEqualTo(String value) {
            addCriterion("MAX_SCORE >=", value, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreLessThan(String value) {
            addCriterion("MAX_SCORE <", value, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreLessThanOrEqualTo(String value) {
            addCriterion("MAX_SCORE <=", value, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreLike(String value) {
            addCriterion("MAX_SCORE like", value, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreNotLike(String value) {
            addCriterion("MAX_SCORE not like", value, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreIn(List<String> values) {
            addCriterion("MAX_SCORE in", values, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreNotIn(List<String> values) {
            addCriterion("MAX_SCORE not in", values, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreBetween(String value1, String value2) {
            addCriterion("MAX_SCORE between", value1, value2, "maxScore");
            return (Criteria) this;
        }

        public Criteria andMaxScoreNotBetween(String value1, String value2) {
            addCriterion("MAX_SCORE not between", value1, value2, "maxScore");
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

        public Criteria andReltiveTypeIsNull() {
            addCriterion("RELTIVE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andReltiveTypeIsNotNull() {
            addCriterion("RELTIVE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andReltiveTypeEqualTo(String value) {
            addCriterion("RELTIVE_TYPE =", value, "reltiveType");
            return (Criteria) this;
        }

        public Criteria andReltiveTypeNotEqualTo(String value) {
            addCriterion("RELTIVE_TYPE <>", value, "reltiveType");
            return (Criteria) this;
        }

        public Criteria andReltiveTypeGreaterThan(String value) {
            addCriterion("RELTIVE_TYPE >", value, "reltiveType");
            return (Criteria) this;
        }

        public Criteria andReltiveTypeGreaterThanOrEqualTo(String value) {
            addCriterion("RELTIVE_TYPE >=", value, "reltiveType");
            return (Criteria) this;
        }

        public Criteria andReltiveTypeLessThan(String value) {
            addCriterion("RELTIVE_TYPE <", value, "reltiveType");
            return (Criteria) this;
        }

        public Criteria andReltiveTypeLessThanOrEqualTo(String value) {
            addCriterion("RELTIVE_TYPE <=", value, "reltiveType");
            return (Criteria) this;
        }

        public Criteria andReltiveTypeLike(String value) {
            addCriterion("RELTIVE_TYPE like", value, "reltiveType");
            return (Criteria) this;
        }

        public Criteria andReltiveTypeNotLike(String value) {
            addCriterion("RELTIVE_TYPE not like", value, "reltiveType");
            return (Criteria) this;
        }

        public Criteria andReltiveTypeIn(List<String> values) {
            addCriterion("RELTIVE_TYPE in", values, "reltiveType");
            return (Criteria) this;
        }

        public Criteria andReltiveTypeNotIn(List<String> values) {
            addCriterion("RELTIVE_TYPE not in", values, "reltiveType");
            return (Criteria) this;
        }

        public Criteria andReltiveTypeBetween(String value1, String value2) {
            addCriterion("RELTIVE_TYPE between", value1, value2, "reltiveType");
            return (Criteria) this;
        }

        public Criteria andReltiveTypeNotBetween(String value1, String value2) {
            addCriterion("RELTIVE_TYPE not between", value1, value2, "reltiveType");
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