package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class ExpertEvalResultExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExpertEvalResultExample() {
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

        public Criteria andResultFlowIsNull() {
            addCriterion("RESULT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andResultFlowIsNotNull() {
            addCriterion("RESULT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andResultFlowEqualTo(String value) {
            addCriterion("RESULT_FLOW =", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowNotEqualTo(String value) {
            addCriterion("RESULT_FLOW <>", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowGreaterThan(String value) {
            addCriterion("RESULT_FLOW >", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowGreaterThanOrEqualTo(String value) {
            addCriterion("RESULT_FLOW >=", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowLessThan(String value) {
            addCriterion("RESULT_FLOW <", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowLessThanOrEqualTo(String value) {
            addCriterion("RESULT_FLOW <=", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowLike(String value) {
            addCriterion("RESULT_FLOW like", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowNotLike(String value) {
            addCriterion("RESULT_FLOW not like", value, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowIn(List<String> values) {
            addCriterion("RESULT_FLOW in", values, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowNotIn(List<String> values) {
            addCriterion("RESULT_FLOW not in", values, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowBetween(String value1, String value2) {
            addCriterion("RESULT_FLOW between", value1, value2, "resultFlow");
            return (Criteria) this;
        }

        public Criteria andResultFlowNotBetween(String value1, String value2) {
            addCriterion("RESULT_FLOW not between", value1, value2, "resultFlow");
            return (Criteria) this;
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

        public Criteria andExpertUserFlowIsNull() {
            addCriterion("EXPERT_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowIsNotNull() {
            addCriterion("EXPERT_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowEqualTo(String value) {
            addCriterion("EXPERT_USER_FLOW =", value, "expertUserFlow");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowNotEqualTo(String value) {
            addCriterion("EXPERT_USER_FLOW <>", value, "expertUserFlow");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowGreaterThan(String value) {
            addCriterion("EXPERT_USER_FLOW >", value, "expertUserFlow");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("EXPERT_USER_FLOW >=", value, "expertUserFlow");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowLessThan(String value) {
            addCriterion("EXPERT_USER_FLOW <", value, "expertUserFlow");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowLessThanOrEqualTo(String value) {
            addCriterion("EXPERT_USER_FLOW <=", value, "expertUserFlow");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowLike(String value) {
            addCriterion("EXPERT_USER_FLOW like", value, "expertUserFlow");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowNotLike(String value) {
            addCriterion("EXPERT_USER_FLOW not like", value, "expertUserFlow");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowIn(List<String> values) {
            addCriterion("EXPERT_USER_FLOW in", values, "expertUserFlow");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowNotIn(List<String> values) {
            addCriterion("EXPERT_USER_FLOW not in", values, "expertUserFlow");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowBetween(String value1, String value2) {
            addCriterion("EXPERT_USER_FLOW between", value1, value2, "expertUserFlow");
            return (Criteria) this;
        }

        public Criteria andExpertUserFlowNotBetween(String value1, String value2) {
            addCriterion("EXPERT_USER_FLOW not between", value1, value2, "expertUserFlow");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameIsNull() {
            addCriterion("EXPERT_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameIsNotNull() {
            addCriterion("EXPERT_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameEqualTo(String value) {
            addCriterion("EXPERT_USER_NAME =", value, "expertUserName");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameNotEqualTo(String value) {
            addCriterion("EXPERT_USER_NAME <>", value, "expertUserName");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameGreaterThan(String value) {
            addCriterion("EXPERT_USER_NAME >", value, "expertUserName");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("EXPERT_USER_NAME >=", value, "expertUserName");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameLessThan(String value) {
            addCriterion("EXPERT_USER_NAME <", value, "expertUserName");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameLessThanOrEqualTo(String value) {
            addCriterion("EXPERT_USER_NAME <=", value, "expertUserName");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameLike(String value) {
            addCriterion("EXPERT_USER_NAME like", value, "expertUserName");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameNotLike(String value) {
            addCriterion("EXPERT_USER_NAME not like", value, "expertUserName");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameIn(List<String> values) {
            addCriterion("EXPERT_USER_NAME in", values, "expertUserName");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameNotIn(List<String> values) {
            addCriterion("EXPERT_USER_NAME not in", values, "expertUserName");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameBetween(String value1, String value2) {
            addCriterion("EXPERT_USER_NAME between", value1, value2, "expertUserName");
            return (Criteria) this;
        }

        public Criteria andExpertUserNameNotBetween(String value1, String value2) {
            addCriterion("EXPERT_USER_NAME not between", value1, value2, "expertUserName");
            return (Criteria) this;
        }

        public Criteria andEvalDateIsNull() {
            addCriterion("EVAL_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEvalDateIsNotNull() {
            addCriterion("EVAL_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEvalDateEqualTo(String value) {
            addCriterion("EVAL_DATE =", value, "evalDate");
            return (Criteria) this;
        }

        public Criteria andEvalDateNotEqualTo(String value) {
            addCriterion("EVAL_DATE <>", value, "evalDate");
            return (Criteria) this;
        }

        public Criteria andEvalDateGreaterThan(String value) {
            addCriterion("EVAL_DATE >", value, "evalDate");
            return (Criteria) this;
        }

        public Criteria andEvalDateGreaterThanOrEqualTo(String value) {
            addCriterion("EVAL_DATE >=", value, "evalDate");
            return (Criteria) this;
        }

        public Criteria andEvalDateLessThan(String value) {
            addCriterion("EVAL_DATE <", value, "evalDate");
            return (Criteria) this;
        }

        public Criteria andEvalDateLessThanOrEqualTo(String value) {
            addCriterion("EVAL_DATE <=", value, "evalDate");
            return (Criteria) this;
        }

        public Criteria andEvalDateLike(String value) {
            addCriterion("EVAL_DATE like", value, "evalDate");
            return (Criteria) this;
        }

        public Criteria andEvalDateNotLike(String value) {
            addCriterion("EVAL_DATE not like", value, "evalDate");
            return (Criteria) this;
        }

        public Criteria andEvalDateIn(List<String> values) {
            addCriterion("EVAL_DATE in", values, "evalDate");
            return (Criteria) this;
        }

        public Criteria andEvalDateNotIn(List<String> values) {
            addCriterion("EVAL_DATE not in", values, "evalDate");
            return (Criteria) this;
        }

        public Criteria andEvalDateBetween(String value1, String value2) {
            addCriterion("EVAL_DATE between", value1, value2, "evalDate");
            return (Criteria) this;
        }

        public Criteria andEvalDateNotBetween(String value1, String value2) {
            addCriterion("EVAL_DATE not between", value1, value2, "evalDate");
            return (Criteria) this;
        }

        public Criteria andBaseScoreIsNull() {
            addCriterion("BASE_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andBaseScoreIsNotNull() {
            addCriterion("BASE_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andBaseScoreEqualTo(String value) {
            addCriterion("BASE_SCORE =", value, "baseScore");
            return (Criteria) this;
        }

        public Criteria andBaseScoreNotEqualTo(String value) {
            addCriterion("BASE_SCORE <>", value, "baseScore");
            return (Criteria) this;
        }

        public Criteria andBaseScoreGreaterThan(String value) {
            addCriterion("BASE_SCORE >", value, "baseScore");
            return (Criteria) this;
        }

        public Criteria andBaseScoreGreaterThanOrEqualTo(String value) {
            addCriterion("BASE_SCORE >=", value, "baseScore");
            return (Criteria) this;
        }

        public Criteria andBaseScoreLessThan(String value) {
            addCriterion("BASE_SCORE <", value, "baseScore");
            return (Criteria) this;
        }

        public Criteria andBaseScoreLessThanOrEqualTo(String value) {
            addCriterion("BASE_SCORE <=", value, "baseScore");
            return (Criteria) this;
        }

        public Criteria andBaseScoreLike(String value) {
            addCriterion("BASE_SCORE like", value, "baseScore");
            return (Criteria) this;
        }

        public Criteria andBaseScoreNotLike(String value) {
            addCriterion("BASE_SCORE not like", value, "baseScore");
            return (Criteria) this;
        }

        public Criteria andBaseScoreIn(List<String> values) {
            addCriterion("BASE_SCORE in", values, "baseScore");
            return (Criteria) this;
        }

        public Criteria andBaseScoreNotIn(List<String> values) {
            addCriterion("BASE_SCORE not in", values, "baseScore");
            return (Criteria) this;
        }

        public Criteria andBaseScoreBetween(String value1, String value2) {
            addCriterion("BASE_SCORE between", value1, value2, "baseScore");
            return (Criteria) this;
        }

        public Criteria andBaseScoreNotBetween(String value1, String value2) {
            addCriterion("BASE_SCORE not between", value1, value2, "baseScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreIsNull() {
            addCriterion("EVAL_SCORE is null");
            return (Criteria) this;
        }

        public Criteria andEvalScoreIsNotNull() {
            addCriterion("EVAL_SCORE is not null");
            return (Criteria) this;
        }

        public Criteria andEvalScoreEqualTo(String value) {
            addCriterion("EVAL_SCORE =", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreNotEqualTo(String value) {
            addCriterion("EVAL_SCORE <>", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreGreaterThan(String value) {
            addCriterion("EVAL_SCORE >", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreGreaterThanOrEqualTo(String value) {
            addCriterion("EVAL_SCORE >=", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreLessThan(String value) {
            addCriterion("EVAL_SCORE <", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreLessThanOrEqualTo(String value) {
            addCriterion("EVAL_SCORE <=", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreLike(String value) {
            addCriterion("EVAL_SCORE like", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreNotLike(String value) {
            addCriterion("EVAL_SCORE not like", value, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreIn(List<String> values) {
            addCriterion("EVAL_SCORE in", values, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreNotIn(List<String> values) {
            addCriterion("EVAL_SCORE not in", values, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreBetween(String value1, String value2) {
            addCriterion("EVAL_SCORE between", value1, value2, "evalScore");
            return (Criteria) this;
        }

        public Criteria andEvalScoreNotBetween(String value1, String value2) {
            addCriterion("EVAL_SCORE not between", value1, value2, "evalScore");
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

        public Criteria andIsOverAllIsNull() {
            addCriterion("IS_OVER_ALL is null");
            return (Criteria) this;
        }

        public Criteria andIsOverAllIsNotNull() {
            addCriterion("IS_OVER_ALL is not null");
            return (Criteria) this;
        }

        public Criteria andIsOverAllEqualTo(String value) {
            addCriterion("IS_OVER_ALL =", value, "isOverAll");
            return (Criteria) this;
        }

        public Criteria andIsOverAllNotEqualTo(String value) {
            addCriterion("IS_OVER_ALL <>", value, "isOverAll");
            return (Criteria) this;
        }

        public Criteria andIsOverAllGreaterThan(String value) {
            addCriterion("IS_OVER_ALL >", value, "isOverAll");
            return (Criteria) this;
        }

        public Criteria andIsOverAllGreaterThanOrEqualTo(String value) {
            addCriterion("IS_OVER_ALL >=", value, "isOverAll");
            return (Criteria) this;
        }

        public Criteria andIsOverAllLessThan(String value) {
            addCriterion("IS_OVER_ALL <", value, "isOverAll");
            return (Criteria) this;
        }

        public Criteria andIsOverAllLessThanOrEqualTo(String value) {
            addCriterion("IS_OVER_ALL <=", value, "isOverAll");
            return (Criteria) this;
        }

        public Criteria andIsOverAllLike(String value) {
            addCriterion("IS_OVER_ALL like", value, "isOverAll");
            return (Criteria) this;
        }

        public Criteria andIsOverAllNotLike(String value) {
            addCriterion("IS_OVER_ALL not like", value, "isOverAll");
            return (Criteria) this;
        }

        public Criteria andIsOverAllIn(List<String> values) {
            addCriterion("IS_OVER_ALL in", values, "isOverAll");
            return (Criteria) this;
        }

        public Criteria andIsOverAllNotIn(List<String> values) {
            addCriterion("IS_OVER_ALL not in", values, "isOverAll");
            return (Criteria) this;
        }

        public Criteria andIsOverAllBetween(String value1, String value2) {
            addCriterion("IS_OVER_ALL between", value1, value2, "isOverAll");
            return (Criteria) this;
        }

        public Criteria andIsOverAllNotBetween(String value1, String value2) {
            addCriterion("IS_OVER_ALL not between", value1, value2, "isOverAll");
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