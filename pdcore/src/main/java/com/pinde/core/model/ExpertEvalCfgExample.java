package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class ExpertEvalCfgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExpertEvalCfgExample() {
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

        public Criteria andCfgFlowIsNull() {
            addCriterion("CFG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andCfgFlowIsNotNull() {
            addCriterion("CFG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andCfgFlowEqualTo(String value) {
            addCriterion("CFG_FLOW =", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowNotEqualTo(String value) {
            addCriterion("CFG_FLOW <>", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowGreaterThan(String value) {
            addCriterion("CFG_FLOW >", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("CFG_FLOW >=", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowLessThan(String value) {
            addCriterion("CFG_FLOW <", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowLessThanOrEqualTo(String value) {
            addCriterion("CFG_FLOW <=", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowLike(String value) {
            addCriterion("CFG_FLOW like", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowNotLike(String value) {
            addCriterion("CFG_FLOW not like", value, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowIn(List<String> values) {
            addCriterion("CFG_FLOW in", values, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowNotIn(List<String> values) {
            addCriterion("CFG_FLOW not in", values, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowBetween(String value1, String value2) {
            addCriterion("CFG_FLOW between", value1, value2, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andCfgFlowNotBetween(String value1, String value2) {
            addCriterion("CFG_FLOW not between", value1, value2, "cfgFlow");
            return (Criteria) this;
        }

        public Criteria andEvalYearIsNull() {
            addCriterion("EVAL_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andEvalYearIsNotNull() {
            addCriterion("EVAL_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andEvalYearEqualTo(String value) {
            addCriterion("EVAL_YEAR =", value, "evalYear");
            return (Criteria) this;
        }

        public Criteria andEvalYearNotEqualTo(String value) {
            addCriterion("EVAL_YEAR <>", value, "evalYear");
            return (Criteria) this;
        }

        public Criteria andEvalYearGreaterThan(String value) {
            addCriterion("EVAL_YEAR >", value, "evalYear");
            return (Criteria) this;
        }

        public Criteria andEvalYearGreaterThanOrEqualTo(String value) {
            addCriterion("EVAL_YEAR >=", value, "evalYear");
            return (Criteria) this;
        }

        public Criteria andEvalYearLessThan(String value) {
            addCriterion("EVAL_YEAR <", value, "evalYear");
            return (Criteria) this;
        }

        public Criteria andEvalYearLessThanOrEqualTo(String value) {
            addCriterion("EVAL_YEAR <=", value, "evalYear");
            return (Criteria) this;
        }

        public Criteria andEvalYearLike(String value) {
            addCriterion("EVAL_YEAR like", value, "evalYear");
            return (Criteria) this;
        }

        public Criteria andEvalYearNotLike(String value) {
            addCriterion("EVAL_YEAR not like", value, "evalYear");
            return (Criteria) this;
        }

        public Criteria andEvalYearIn(List<String> values) {
            addCriterion("EVAL_YEAR in", values, "evalYear");
            return (Criteria) this;
        }

        public Criteria andEvalYearNotIn(List<String> values) {
            addCriterion("EVAL_YEAR not in", values, "evalYear");
            return (Criteria) this;
        }

        public Criteria andEvalYearBetween(String value1, String value2) {
            addCriterion("EVAL_YEAR between", value1, value2, "evalYear");
            return (Criteria) this;
        }

        public Criteria andEvalYearNotBetween(String value1, String value2) {
            addCriterion("EVAL_YEAR not between", value1, value2, "evalYear");
            return (Criteria) this;
        }

        public Criteria andCfgNameIsNull() {
            addCriterion("CFG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCfgNameIsNotNull() {
            addCriterion("CFG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCfgNameEqualTo(String value) {
            addCriterion("CFG_NAME =", value, "cfgName");
            return (Criteria) this;
        }

        public Criteria andCfgNameNotEqualTo(String value) {
            addCriterion("CFG_NAME <>", value, "cfgName");
            return (Criteria) this;
        }

        public Criteria andCfgNameGreaterThan(String value) {
            addCriterion("CFG_NAME >", value, "cfgName");
            return (Criteria) this;
        }

        public Criteria andCfgNameGreaterThanOrEqualTo(String value) {
            addCriterion("CFG_NAME >=", value, "cfgName");
            return (Criteria) this;
        }

        public Criteria andCfgNameLessThan(String value) {
            addCriterion("CFG_NAME <", value, "cfgName");
            return (Criteria) this;
        }

        public Criteria andCfgNameLessThanOrEqualTo(String value) {
            addCriterion("CFG_NAME <=", value, "cfgName");
            return (Criteria) this;
        }

        public Criteria andCfgNameLike(String value) {
            addCriterion("CFG_NAME like", value, "cfgName");
            return (Criteria) this;
        }

        public Criteria andCfgNameNotLike(String value) {
            addCriterion("CFG_NAME not like", value, "cfgName");
            return (Criteria) this;
        }

        public Criteria andCfgNameIn(List<String> values) {
            addCriterion("CFG_NAME in", values, "cfgName");
            return (Criteria) this;
        }

        public Criteria andCfgNameNotIn(List<String> values) {
            addCriterion("CFG_NAME not in", values, "cfgName");
            return (Criteria) this;
        }

        public Criteria andCfgNameBetween(String value1, String value2) {
            addCriterion("CFG_NAME between", value1, value2, "cfgName");
            return (Criteria) this;
        }

        public Criteria andCfgNameNotBetween(String value1, String value2) {
            addCriterion("CFG_NAME not between", value1, value2, "cfgName");
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

        public Criteria andIsFileIsNull() {
            addCriterion("IS_FILE is null");
            return (Criteria) this;
        }

        public Criteria andIsFileIsNotNull() {
            addCriterion("IS_FILE is not null");
            return (Criteria) this;
        }

        public Criteria andIsFileEqualTo(String value) {
            addCriterion("IS_FILE =", value, "isFile");
            return (Criteria) this;
        }

        public Criteria andIsFileNotEqualTo(String value) {
            addCriterion("IS_FILE <>", value, "isFile");
            return (Criteria) this;
        }

        public Criteria andIsFileGreaterThan(String value) {
            addCriterion("IS_FILE >", value, "isFile");
            return (Criteria) this;
        }

        public Criteria andIsFileGreaterThanOrEqualTo(String value) {
            addCriterion("IS_FILE >=", value, "isFile");
            return (Criteria) this;
        }

        public Criteria andIsFileLessThan(String value) {
            addCriterion("IS_FILE <", value, "isFile");
            return (Criteria) this;
        }

        public Criteria andIsFileLessThanOrEqualTo(String value) {
            addCriterion("IS_FILE <=", value, "isFile");
            return (Criteria) this;
        }

        public Criteria andIsFileLike(String value) {
            addCriterion("IS_FILE like", value, "isFile");
            return (Criteria) this;
        }

        public Criteria andIsFileNotLike(String value) {
            addCriterion("IS_FILE not like", value, "isFile");
            return (Criteria) this;
        }

        public Criteria andIsFileIn(List<String> values) {
            addCriterion("IS_FILE in", values, "isFile");
            return (Criteria) this;
        }

        public Criteria andIsFileNotIn(List<String> values) {
            addCriterion("IS_FILE not in", values, "isFile");
            return (Criteria) this;
        }

        public Criteria andIsFileBetween(String value1, String value2) {
            addCriterion("IS_FILE between", value1, value2, "isFile");
            return (Criteria) this;
        }

        public Criteria andIsFileNotBetween(String value1, String value2) {
            addCriterion("IS_FILE not between", value1, value2, "isFile");
            return (Criteria) this;
        }

        public Criteria andIsPublishIsNull() {
            addCriterion("IS_PUBLISH is null");
            return (Criteria) this;
        }

        public Criteria andIsPublishIsNotNull() {
            addCriterion("IS_PUBLISH is not null");
            return (Criteria) this;
        }

        public Criteria andIsPublishEqualTo(String value) {
            addCriterion("IS_PUBLISH =", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishNotEqualTo(String value) {
            addCriterion("IS_PUBLISH <>", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishGreaterThan(String value) {
            addCriterion("IS_PUBLISH >", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishGreaterThanOrEqualTo(String value) {
            addCriterion("IS_PUBLISH >=", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishLessThan(String value) {
            addCriterion("IS_PUBLISH <", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishLessThanOrEqualTo(String value) {
            addCriterion("IS_PUBLISH <=", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishLike(String value) {
            addCriterion("IS_PUBLISH like", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishNotLike(String value) {
            addCriterion("IS_PUBLISH not like", value, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishIn(List<String> values) {
            addCriterion("IS_PUBLISH in", values, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishNotIn(List<String> values) {
            addCriterion("IS_PUBLISH not in", values, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishBetween(String value1, String value2) {
            addCriterion("IS_PUBLISH between", value1, value2, "isPublish");
            return (Criteria) this;
        }

        public Criteria andIsPublishNotBetween(String value1, String value2) {
            addCriterion("IS_PUBLISH not between", value1, value2, "isPublish");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNull() {
            addCriterion("FILE_PATH is null");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNotNull() {
            addCriterion("FILE_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andFilePathEqualTo(String value) {
            addCriterion("FILE_PATH =", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotEqualTo(String value) {
            addCriterion("FILE_PATH <>", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThan(String value) {
            addCriterion("FILE_PATH >", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThanOrEqualTo(String value) {
            addCriterion("FILE_PATH >=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThan(String value) {
            addCriterion("FILE_PATH <", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThanOrEqualTo(String value) {
            addCriterion("FILE_PATH <=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLike(String value) {
            addCriterion("FILE_PATH like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotLike(String value) {
            addCriterion("FILE_PATH not like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathIn(List<String> values) {
            addCriterion("FILE_PATH in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotIn(List<String> values) {
            addCriterion("FILE_PATH not in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathBetween(String value1, String value2) {
            addCriterion("FILE_PATH between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotBetween(String value1, String value2) {
            addCriterion("FILE_PATH not between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andParentCfgFlowIsNull() {
            addCriterion("PARENT_CFG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andParentCfgFlowIsNotNull() {
            addCriterion("PARENT_CFG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andParentCfgFlowEqualTo(String value) {
            addCriterion("PARENT_CFG_FLOW =", value, "parentCfgFlow");
            return (Criteria) this;
        }

        public Criteria andParentCfgFlowNotEqualTo(String value) {
            addCriterion("PARENT_CFG_FLOW <>", value, "parentCfgFlow");
            return (Criteria) this;
        }

        public Criteria andParentCfgFlowGreaterThan(String value) {
            addCriterion("PARENT_CFG_FLOW >", value, "parentCfgFlow");
            return (Criteria) this;
        }

        public Criteria andParentCfgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_CFG_FLOW >=", value, "parentCfgFlow");
            return (Criteria) this;
        }

        public Criteria andParentCfgFlowLessThan(String value) {
            addCriterion("PARENT_CFG_FLOW <", value, "parentCfgFlow");
            return (Criteria) this;
        }

        public Criteria andParentCfgFlowLessThanOrEqualTo(String value) {
            addCriterion("PARENT_CFG_FLOW <=", value, "parentCfgFlow");
            return (Criteria) this;
        }

        public Criteria andParentCfgFlowLike(String value) {
            addCriterion("PARENT_CFG_FLOW like", value, "parentCfgFlow");
            return (Criteria) this;
        }

        public Criteria andParentCfgFlowNotLike(String value) {
            addCriterion("PARENT_CFG_FLOW not like", value, "parentCfgFlow");
            return (Criteria) this;
        }

        public Criteria andParentCfgFlowIn(List<String> values) {
            addCriterion("PARENT_CFG_FLOW in", values, "parentCfgFlow");
            return (Criteria) this;
        }

        public Criteria andParentCfgFlowNotIn(List<String> values) {
            addCriterion("PARENT_CFG_FLOW not in", values, "parentCfgFlow");
            return (Criteria) this;
        }

        public Criteria andParentCfgFlowBetween(String value1, String value2) {
            addCriterion("PARENT_CFG_FLOW between", value1, value2, "parentCfgFlow");
            return (Criteria) this;
        }

        public Criteria andParentCfgFlowNotBetween(String value1, String value2) {
            addCriterion("PARENT_CFG_FLOW not between", value1, value2, "parentCfgFlow");
            return (Criteria) this;
        }

        public Criteria andLevelIdIsNull() {
            addCriterion("LEVEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andLevelIdIsNotNull() {
            addCriterion("LEVEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLevelIdEqualTo(Integer value) {
            addCriterion("LEVEL_ID =", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdNotEqualTo(Integer value) {
            addCriterion("LEVEL_ID <>", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdGreaterThan(Integer value) {
            addCriterion("LEVEL_ID >", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("LEVEL_ID >=", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdLessThan(Integer value) {
            addCriterion("LEVEL_ID <", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdLessThanOrEqualTo(Integer value) {
            addCriterion("LEVEL_ID <=", value, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdIn(List<Integer> values) {
            addCriterion("LEVEL_ID in", values, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdNotIn(List<Integer> values) {
            addCriterion("LEVEL_ID not in", values, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdBetween(Integer value1, Integer value2) {
            addCriterion("LEVEL_ID between", value1, value2, "levelId");
            return (Criteria) this;
        }

        public Criteria andLevelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("LEVEL_ID not between", value1, value2, "levelId");
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

        public Criteria andIsExpertIsNull() {
            addCriterion("IS_EXPERT is null");
            return (Criteria) this;
        }

        public Criteria andIsExpertIsNotNull() {
            addCriterion("IS_EXPERT is not null");
            return (Criteria) this;
        }

        public Criteria andIsExpertEqualTo(String value) {
            addCriterion("IS_EXPERT =", value, "isExpert");
            return (Criteria) this;
        }

        public Criteria andIsExpertNotEqualTo(String value) {
            addCriterion("IS_EXPERT <>", value, "isExpert");
            return (Criteria) this;
        }

        public Criteria andIsExpertGreaterThan(String value) {
            addCriterion("IS_EXPERT >", value, "isExpert");
            return (Criteria) this;
        }

        public Criteria andIsExpertGreaterThanOrEqualTo(String value) {
            addCriterion("IS_EXPERT >=", value, "isExpert");
            return (Criteria) this;
        }

        public Criteria andIsExpertLessThan(String value) {
            addCriterion("IS_EXPERT <", value, "isExpert");
            return (Criteria) this;
        }

        public Criteria andIsExpertLessThanOrEqualTo(String value) {
            addCriterion("IS_EXPERT <=", value, "isExpert");
            return (Criteria) this;
        }

        public Criteria andIsExpertLike(String value) {
            addCriterion("IS_EXPERT like", value, "isExpert");
            return (Criteria) this;
        }

        public Criteria andIsExpertNotLike(String value) {
            addCriterion("IS_EXPERT not like", value, "isExpert");
            return (Criteria) this;
        }

        public Criteria andIsExpertIn(List<String> values) {
            addCriterion("IS_EXPERT in", values, "isExpert");
            return (Criteria) this;
        }

        public Criteria andIsExpertNotIn(List<String> values) {
            addCriterion("IS_EXPERT not in", values, "isExpert");
            return (Criteria) this;
        }

        public Criteria andIsExpertBetween(String value1, String value2) {
            addCriterion("IS_EXPERT between", value1, value2, "isExpert");
            return (Criteria) this;
        }

        public Criteria andIsExpertNotBetween(String value1, String value2) {
            addCriterion("IS_EXPERT not between", value1, value2, "isExpert");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdIsNull() {
            addCriterion("ACTION_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdIsNotNull() {
            addCriterion("ACTION_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdEqualTo(String value) {
            addCriterion("ACTION_TYPE_ID =", value, "actionTypeId");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdNotEqualTo(String value) {
            addCriterion("ACTION_TYPE_ID <>", value, "actionTypeId");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdGreaterThan(String value) {
            addCriterion("ACTION_TYPE_ID >", value, "actionTypeId");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("ACTION_TYPE_ID >=", value, "actionTypeId");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdLessThan(String value) {
            addCriterion("ACTION_TYPE_ID <", value, "actionTypeId");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdLessThanOrEqualTo(String value) {
            addCriterion("ACTION_TYPE_ID <=", value, "actionTypeId");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdLike(String value) {
            addCriterion("ACTION_TYPE_ID like", value, "actionTypeId");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdNotLike(String value) {
            addCriterion("ACTION_TYPE_ID not like", value, "actionTypeId");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdIn(List<String> values) {
            addCriterion("ACTION_TYPE_ID in", values, "actionTypeId");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdNotIn(List<String> values) {
            addCriterion("ACTION_TYPE_ID not in", values, "actionTypeId");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdBetween(String value1, String value2) {
            addCriterion("ACTION_TYPE_ID between", value1, value2, "actionTypeId");
            return (Criteria) this;
        }

        public Criteria andActionTypeIdNotBetween(String value1, String value2) {
            addCriterion("ACTION_TYPE_ID not between", value1, value2, "actionTypeId");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameIsNull() {
            addCriterion("ACTION_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameIsNotNull() {
            addCriterion("ACTION_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameEqualTo(String value) {
            addCriterion("ACTION_TYPE_NAME =", value, "actionTypeName");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameNotEqualTo(String value) {
            addCriterion("ACTION_TYPE_NAME <>", value, "actionTypeName");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameGreaterThan(String value) {
            addCriterion("ACTION_TYPE_NAME >", value, "actionTypeName");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACTION_TYPE_NAME >=", value, "actionTypeName");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameLessThan(String value) {
            addCriterion("ACTION_TYPE_NAME <", value, "actionTypeName");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameLessThanOrEqualTo(String value) {
            addCriterion("ACTION_TYPE_NAME <=", value, "actionTypeName");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameLike(String value) {
            addCriterion("ACTION_TYPE_NAME like", value, "actionTypeName");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameNotLike(String value) {
            addCriterion("ACTION_TYPE_NAME not like", value, "actionTypeName");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameIn(List<String> values) {
            addCriterion("ACTION_TYPE_NAME in", values, "actionTypeName");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameNotIn(List<String> values) {
            addCriterion("ACTION_TYPE_NAME not in", values, "actionTypeName");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameBetween(String value1, String value2) {
            addCriterion("ACTION_TYPE_NAME between", value1, value2, "actionTypeName");
            return (Criteria) this;
        }

        public Criteria andActionTypeNameNotBetween(String value1, String value2) {
            addCriterion("ACTION_TYPE_NAME not between", value1, value2, "actionTypeName");
            return (Criteria) this;
        }

        public Criteria andSpeIdIsNull() {
            addCriterion("SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSpeIdIsNotNull() {
            addCriterion("SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSpeIdEqualTo(String value) {
            addCriterion("SPE_ID =", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotEqualTo(String value) {
            addCriterion("SPE_ID <>", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdGreaterThan(String value) {
            addCriterion("SPE_ID >", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_ID >=", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLessThan(String value) {
            addCriterion("SPE_ID <", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLessThanOrEqualTo(String value) {
            addCriterion("SPE_ID <=", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdLike(String value) {
            addCriterion("SPE_ID like", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotLike(String value) {
            addCriterion("SPE_ID not like", value, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdIn(List<String> values) {
            addCriterion("SPE_ID in", values, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotIn(List<String> values) {
            addCriterion("SPE_ID not in", values, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdBetween(String value1, String value2) {
            addCriterion("SPE_ID between", value1, value2, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeIdNotBetween(String value1, String value2) {
            addCriterion("SPE_ID not between", value1, value2, "speId");
            return (Criteria) this;
        }

        public Criteria andSpeNameIsNull() {
            addCriterion("SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSpeNameIsNotNull() {
            addCriterion("SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSpeNameEqualTo(String value) {
            addCriterion("SPE_NAME =", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotEqualTo(String value) {
            addCriterion("SPE_NAME <>", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameGreaterThan(String value) {
            addCriterion("SPE_NAME >", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SPE_NAME >=", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameLessThan(String value) {
            addCriterion("SPE_NAME <", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameLessThanOrEqualTo(String value) {
            addCriterion("SPE_NAME <=", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameLike(String value) {
            addCriterion("SPE_NAME like", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotLike(String value) {
            addCriterion("SPE_NAME not like", value, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameIn(List<String> values) {
            addCriterion("SPE_NAME in", values, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotIn(List<String> values) {
            addCriterion("SPE_NAME not in", values, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameBetween(String value1, String value2) {
            addCriterion("SPE_NAME between", value1, value2, "speName");
            return (Criteria) this;
        }

        public Criteria andSpeNameNotBetween(String value1, String value2) {
            addCriterion("SPE_NAME not between", value1, value2, "speName");
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