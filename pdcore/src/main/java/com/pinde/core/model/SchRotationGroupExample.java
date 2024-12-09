package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class SchRotationGroupExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SchRotationGroupExample() {
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

        public Criteria andGroupFlowIsNull() {
            addCriterion("GROUP_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andGroupFlowIsNotNull() {
            addCriterion("GROUP_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andGroupFlowEqualTo(String value) {
            addCriterion("GROUP_FLOW =", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowNotEqualTo(String value) {
            addCriterion("GROUP_FLOW <>", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowGreaterThan(String value) {
            addCriterion("GROUP_FLOW >", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_FLOW >=", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowLessThan(String value) {
            addCriterion("GROUP_FLOW <", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowLessThanOrEqualTo(String value) {
            addCriterion("GROUP_FLOW <=", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowLike(String value) {
            addCriterion("GROUP_FLOW like", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowNotLike(String value) {
            addCriterion("GROUP_FLOW not like", value, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowIn(List<String> values) {
            addCriterion("GROUP_FLOW in", values, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowNotIn(List<String> values) {
            addCriterion("GROUP_FLOW not in", values, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowBetween(String value1, String value2) {
            addCriterion("GROUP_FLOW between", value1, value2, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andGroupFlowNotBetween(String value1, String value2) {
            addCriterion("GROUP_FLOW not between", value1, value2, "groupFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowIsNull() {
            addCriterion("ROTATION_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andRotationFlowIsNotNull() {
            addCriterion("ROTATION_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andRotationFlowEqualTo(String value) {
            addCriterion("ROTATION_FLOW =", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowNotEqualTo(String value) {
            addCriterion("ROTATION_FLOW <>", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowGreaterThan(String value) {
            addCriterion("ROTATION_FLOW >", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ROTATION_FLOW >=", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowLessThan(String value) {
            addCriterion("ROTATION_FLOW <", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowLessThanOrEqualTo(String value) {
            addCriterion("ROTATION_FLOW <=", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowLike(String value) {
            addCriterion("ROTATION_FLOW like", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowNotLike(String value) {
            addCriterion("ROTATION_FLOW not like", value, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowIn(List<String> values) {
            addCriterion("ROTATION_FLOW in", values, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowNotIn(List<String> values) {
            addCriterion("ROTATION_FLOW not in", values, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowBetween(String value1, String value2) {
            addCriterion("ROTATION_FLOW between", value1, value2, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andRotationFlowNotBetween(String value1, String value2) {
            addCriterion("ROTATION_FLOW not between", value1, value2, "rotationFlow");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNull() {
            addCriterion("GROUP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("GROUP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("GROUP_NAME =", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("GROUP_NAME <>", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("GROUP_NAME >", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_NAME >=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("GROUP_NAME <", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("GROUP_NAME <=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("GROUP_NAME like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("GROUP_NAME not like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIn(List<String> values) {
            addCriterion("GROUP_NAME in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotIn(List<String> values) {
            addCriterion("GROUP_NAME not in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("GROUP_NAME between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("GROUP_NAME not between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andDeptNumIsNull() {
            addCriterion("DEPT_NUM is null");
            return (Criteria) this;
        }

        public Criteria andDeptNumIsNotNull() {
            addCriterion("DEPT_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andDeptNumEqualTo(Integer value) {
            addCriterion("DEPT_NUM =", value, "deptNum");
            return (Criteria) this;
        }

        public Criteria andDeptNumNotEqualTo(Integer value) {
            addCriterion("DEPT_NUM <>", value, "deptNum");
            return (Criteria) this;
        }

        public Criteria andDeptNumGreaterThan(Integer value) {
            addCriterion("DEPT_NUM >", value, "deptNum");
            return (Criteria) this;
        }

        public Criteria andDeptNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("DEPT_NUM >=", value, "deptNum");
            return (Criteria) this;
        }

        public Criteria andDeptNumLessThan(Integer value) {
            addCriterion("DEPT_NUM <", value, "deptNum");
            return (Criteria) this;
        }

        public Criteria andDeptNumLessThanOrEqualTo(Integer value) {
            addCriterion("DEPT_NUM <=", value, "deptNum");
            return (Criteria) this;
        }

        public Criteria andDeptNumIn(List<Integer> values) {
            addCriterion("DEPT_NUM in", values, "deptNum");
            return (Criteria) this;
        }

        public Criteria andDeptNumNotIn(List<Integer> values) {
            addCriterion("DEPT_NUM not in", values, "deptNum");
            return (Criteria) this;
        }

        public Criteria andDeptNumBetween(Integer value1, Integer value2) {
            addCriterion("DEPT_NUM between", value1, value2, "deptNum");
            return (Criteria) this;
        }

        public Criteria andDeptNumNotBetween(Integer value1, Integer value2) {
            addCriterion("DEPT_NUM not between", value1, value2, "deptNum");
            return (Criteria) this;
        }

        public Criteria andSchMonthIsNull() {
            addCriterion("SCH_MONTH is null");
            return (Criteria) this;
        }

        public Criteria andSchMonthIsNotNull() {
            addCriterion("SCH_MONTH is not null");
            return (Criteria) this;
        }

        public Criteria andSchMonthEqualTo(String value) {
            addCriterion("SCH_MONTH =", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthNotEqualTo(String value) {
            addCriterion("SCH_MONTH <>", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthGreaterThan(String value) {
            addCriterion("SCH_MONTH >", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_MONTH >=", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthLessThan(String value) {
            addCriterion("SCH_MONTH <", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthLessThanOrEqualTo(String value) {
            addCriterion("SCH_MONTH <=", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthLike(String value) {
            addCriterion("SCH_MONTH like", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthNotLike(String value) {
            addCriterion("SCH_MONTH not like", value, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthIn(List<String> values) {
            addCriterion("SCH_MONTH in", values, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthNotIn(List<String> values) {
            addCriterion("SCH_MONTH not in", values, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthBetween(String value1, String value2) {
            addCriterion("SCH_MONTH between", value1, value2, "schMonth");
            return (Criteria) this;
        }

        public Criteria andSchMonthNotBetween(String value1, String value2) {
            addCriterion("SCH_MONTH not between", value1, value2, "schMonth");
            return (Criteria) this;
        }

        public Criteria andGroupNoteIsNull() {
            addCriterion("GROUP_NOTE is null");
            return (Criteria) this;
        }

        public Criteria andGroupNoteIsNotNull() {
            addCriterion("GROUP_NOTE is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNoteEqualTo(String value) {
            addCriterion("GROUP_NOTE =", value, "groupNote");
            return (Criteria) this;
        }

        public Criteria andGroupNoteNotEqualTo(String value) {
            addCriterion("GROUP_NOTE <>", value, "groupNote");
            return (Criteria) this;
        }

        public Criteria andGroupNoteGreaterThan(String value) {
            addCriterion("GROUP_NOTE >", value, "groupNote");
            return (Criteria) this;
        }

        public Criteria andGroupNoteGreaterThanOrEqualTo(String value) {
            addCriterion("GROUP_NOTE >=", value, "groupNote");
            return (Criteria) this;
        }

        public Criteria andGroupNoteLessThan(String value) {
            addCriterion("GROUP_NOTE <", value, "groupNote");
            return (Criteria) this;
        }

        public Criteria andGroupNoteLessThanOrEqualTo(String value) {
            addCriterion("GROUP_NOTE <=", value, "groupNote");
            return (Criteria) this;
        }

        public Criteria andGroupNoteLike(String value) {
            addCriterion("GROUP_NOTE like", value, "groupNote");
            return (Criteria) this;
        }

        public Criteria andGroupNoteNotLike(String value) {
            addCriterion("GROUP_NOTE not like", value, "groupNote");
            return (Criteria) this;
        }

        public Criteria andGroupNoteIn(List<String> values) {
            addCriterion("GROUP_NOTE in", values, "groupNote");
            return (Criteria) this;
        }

        public Criteria andGroupNoteNotIn(List<String> values) {
            addCriterion("GROUP_NOTE not in", values, "groupNote");
            return (Criteria) this;
        }

        public Criteria andGroupNoteBetween(String value1, String value2) {
            addCriterion("GROUP_NOTE between", value1, value2, "groupNote");
            return (Criteria) this;
        }

        public Criteria andGroupNoteNotBetween(String value1, String value2) {
            addCriterion("GROUP_NOTE not between", value1, value2, "groupNote");
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

        public Criteria andSelTypeIdIsNull() {
            addCriterion("SEL_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSelTypeIdIsNotNull() {
            addCriterion("SEL_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSelTypeIdEqualTo(String value) {
            addCriterion("SEL_TYPE_ID =", value, "selTypeId");
            return (Criteria) this;
        }

        public Criteria andSelTypeIdNotEqualTo(String value) {
            addCriterion("SEL_TYPE_ID <>", value, "selTypeId");
            return (Criteria) this;
        }

        public Criteria andSelTypeIdGreaterThan(String value) {
            addCriterion("SEL_TYPE_ID >", value, "selTypeId");
            return (Criteria) this;
        }

        public Criteria andSelTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SEL_TYPE_ID >=", value, "selTypeId");
            return (Criteria) this;
        }

        public Criteria andSelTypeIdLessThan(String value) {
            addCriterion("SEL_TYPE_ID <", value, "selTypeId");
            return (Criteria) this;
        }

        public Criteria andSelTypeIdLessThanOrEqualTo(String value) {
            addCriterion("SEL_TYPE_ID <=", value, "selTypeId");
            return (Criteria) this;
        }

        public Criteria andSelTypeIdLike(String value) {
            addCriterion("SEL_TYPE_ID like", value, "selTypeId");
            return (Criteria) this;
        }

        public Criteria andSelTypeIdNotLike(String value) {
            addCriterion("SEL_TYPE_ID not like", value, "selTypeId");
            return (Criteria) this;
        }

        public Criteria andSelTypeIdIn(List<String> values) {
            addCriterion("SEL_TYPE_ID in", values, "selTypeId");
            return (Criteria) this;
        }

        public Criteria andSelTypeIdNotIn(List<String> values) {
            addCriterion("SEL_TYPE_ID not in", values, "selTypeId");
            return (Criteria) this;
        }

        public Criteria andSelTypeIdBetween(String value1, String value2) {
            addCriterion("SEL_TYPE_ID between", value1, value2, "selTypeId");
            return (Criteria) this;
        }

        public Criteria andSelTypeIdNotBetween(String value1, String value2) {
            addCriterion("SEL_TYPE_ID not between", value1, value2, "selTypeId");
            return (Criteria) this;
        }

        public Criteria andSelTypeNameIsNull() {
            addCriterion("SEL_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSelTypeNameIsNotNull() {
            addCriterion("SEL_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSelTypeNameEqualTo(String value) {
            addCriterion("SEL_TYPE_NAME =", value, "selTypeName");
            return (Criteria) this;
        }

        public Criteria andSelTypeNameNotEqualTo(String value) {
            addCriterion("SEL_TYPE_NAME <>", value, "selTypeName");
            return (Criteria) this;
        }

        public Criteria andSelTypeNameGreaterThan(String value) {
            addCriterion("SEL_TYPE_NAME >", value, "selTypeName");
            return (Criteria) this;
        }

        public Criteria andSelTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("SEL_TYPE_NAME >=", value, "selTypeName");
            return (Criteria) this;
        }

        public Criteria andSelTypeNameLessThan(String value) {
            addCriterion("SEL_TYPE_NAME <", value, "selTypeName");
            return (Criteria) this;
        }

        public Criteria andSelTypeNameLessThanOrEqualTo(String value) {
            addCriterion("SEL_TYPE_NAME <=", value, "selTypeName");
            return (Criteria) this;
        }

        public Criteria andSelTypeNameLike(String value) {
            addCriterion("SEL_TYPE_NAME like", value, "selTypeName");
            return (Criteria) this;
        }

        public Criteria andSelTypeNameNotLike(String value) {
            addCriterion("SEL_TYPE_NAME not like", value, "selTypeName");
            return (Criteria) this;
        }

        public Criteria andSelTypeNameIn(List<String> values) {
            addCriterion("SEL_TYPE_NAME in", values, "selTypeName");
            return (Criteria) this;
        }

        public Criteria andSelTypeNameNotIn(List<String> values) {
            addCriterion("SEL_TYPE_NAME not in", values, "selTypeName");
            return (Criteria) this;
        }

        public Criteria andSelTypeNameBetween(String value1, String value2) {
            addCriterion("SEL_TYPE_NAME between", value1, value2, "selTypeName");
            return (Criteria) this;
        }

        public Criteria andSelTypeNameNotBetween(String value1, String value2) {
            addCriterion("SEL_TYPE_NAME not between", value1, value2, "selTypeName");
            return (Criteria) this;
        }

        public Criteria andMaxDeptNumIsNull() {
            addCriterion("MAX_DEPT_NUM is null");
            return (Criteria) this;
        }

        public Criteria andMaxDeptNumIsNotNull() {
            addCriterion("MAX_DEPT_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andMaxDeptNumEqualTo(Integer value) {
            addCriterion("MAX_DEPT_NUM =", value, "maxDeptNum");
            return (Criteria) this;
        }

        public Criteria andMaxDeptNumNotEqualTo(Integer value) {
            addCriterion("MAX_DEPT_NUM <>", value, "maxDeptNum");
            return (Criteria) this;
        }

        public Criteria andMaxDeptNumGreaterThan(Integer value) {
            addCriterion("MAX_DEPT_NUM >", value, "maxDeptNum");
            return (Criteria) this;
        }

        public Criteria andMaxDeptNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("MAX_DEPT_NUM >=", value, "maxDeptNum");
            return (Criteria) this;
        }

        public Criteria andMaxDeptNumLessThan(Integer value) {
            addCriterion("MAX_DEPT_NUM <", value, "maxDeptNum");
            return (Criteria) this;
        }

        public Criteria andMaxDeptNumLessThanOrEqualTo(Integer value) {
            addCriterion("MAX_DEPT_NUM <=", value, "maxDeptNum");
            return (Criteria) this;
        }

        public Criteria andMaxDeptNumIn(List<Integer> values) {
            addCriterion("MAX_DEPT_NUM in", values, "maxDeptNum");
            return (Criteria) this;
        }

        public Criteria andMaxDeptNumNotIn(List<Integer> values) {
            addCriterion("MAX_DEPT_NUM not in", values, "maxDeptNum");
            return (Criteria) this;
        }

        public Criteria andMaxDeptNumBetween(Integer value1, Integer value2) {
            addCriterion("MAX_DEPT_NUM between", value1, value2, "maxDeptNum");
            return (Criteria) this;
        }

        public Criteria andMaxDeptNumNotBetween(Integer value1, Integer value2) {
            addCriterion("MAX_DEPT_NUM not between", value1, value2, "maxDeptNum");
            return (Criteria) this;
        }

        public Criteria andStandardGroupFlowIsNull() {
            addCriterion("STANDARD_GROUP_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andStandardGroupFlowIsNotNull() {
            addCriterion("STANDARD_GROUP_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andStandardGroupFlowEqualTo(String value) {
            addCriterion("STANDARD_GROUP_FLOW =", value, "standardGroupFlow");
            return (Criteria) this;
        }

        public Criteria andStandardGroupFlowNotEqualTo(String value) {
            addCriterion("STANDARD_GROUP_FLOW <>", value, "standardGroupFlow");
            return (Criteria) this;
        }

        public Criteria andStandardGroupFlowGreaterThan(String value) {
            addCriterion("STANDARD_GROUP_FLOW >", value, "standardGroupFlow");
            return (Criteria) this;
        }

        public Criteria andStandardGroupFlowGreaterThanOrEqualTo(String value) {
            addCriterion("STANDARD_GROUP_FLOW >=", value, "standardGroupFlow");
            return (Criteria) this;
        }

        public Criteria andStandardGroupFlowLessThan(String value) {
            addCriterion("STANDARD_GROUP_FLOW <", value, "standardGroupFlow");
            return (Criteria) this;
        }

        public Criteria andStandardGroupFlowLessThanOrEqualTo(String value) {
            addCriterion("STANDARD_GROUP_FLOW <=", value, "standardGroupFlow");
            return (Criteria) this;
        }

        public Criteria andStandardGroupFlowLike(String value) {
            addCriterion("STANDARD_GROUP_FLOW like", value, "standardGroupFlow");
            return (Criteria) this;
        }

        public Criteria andStandardGroupFlowNotLike(String value) {
            addCriterion("STANDARD_GROUP_FLOW not like", value, "standardGroupFlow");
            return (Criteria) this;
        }

        public Criteria andStandardGroupFlowIn(List<String> values) {
            addCriterion("STANDARD_GROUP_FLOW in", values, "standardGroupFlow");
            return (Criteria) this;
        }

        public Criteria andStandardGroupFlowNotIn(List<String> values) {
            addCriterion("STANDARD_GROUP_FLOW not in", values, "standardGroupFlow");
            return (Criteria) this;
        }

        public Criteria andStandardGroupFlowBetween(String value1, String value2) {
            addCriterion("STANDARD_GROUP_FLOW between", value1, value2, "standardGroupFlow");
            return (Criteria) this;
        }

        public Criteria andStandardGroupFlowNotBetween(String value1, String value2) {
            addCriterion("STANDARD_GROUP_FLOW not between", value1, value2, "standardGroupFlow");
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

        public Criteria andIsRequiredIsNull() {
            addCriterion("IS_REQUIRED is null");
            return (Criteria) this;
        }

        public Criteria andIsRequiredIsNotNull() {
            addCriterion("IS_REQUIRED is not null");
            return (Criteria) this;
        }

        public Criteria andIsRequiredEqualTo(String value) {
            addCriterion("IS_REQUIRED =", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredNotEqualTo(String value) {
            addCriterion("IS_REQUIRED <>", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredGreaterThan(String value) {
            addCriterion("IS_REQUIRED >", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredGreaterThanOrEqualTo(String value) {
            addCriterion("IS_REQUIRED >=", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredLessThan(String value) {
            addCriterion("IS_REQUIRED <", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredLessThanOrEqualTo(String value) {
            addCriterion("IS_REQUIRED <=", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredLike(String value) {
            addCriterion("IS_REQUIRED like", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredNotLike(String value) {
            addCriterion("IS_REQUIRED not like", value, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredIn(List<String> values) {
            addCriterion("IS_REQUIRED in", values, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredNotIn(List<String> values) {
            addCriterion("IS_REQUIRED not in", values, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredBetween(String value1, String value2) {
            addCriterion("IS_REQUIRED between", value1, value2, "isRequired");
            return (Criteria) this;
        }

        public Criteria andIsRequiredNotBetween(String value1, String value2) {
            addCriterion("IS_REQUIRED not between", value1, value2, "isRequired");
            return (Criteria) this;
        }

        public Criteria andSchStageIdIsNull() {
            addCriterion("SCH_STAGE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSchStageIdIsNotNull() {
            addCriterion("SCH_STAGE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSchStageIdEqualTo(String value) {
            addCriterion("SCH_STAGE_ID =", value, "schStageId");
            return (Criteria) this;
        }

        public Criteria andSchStageIdNotEqualTo(String value) {
            addCriterion("SCH_STAGE_ID <>", value, "schStageId");
            return (Criteria) this;
        }

        public Criteria andSchStageIdGreaterThan(String value) {
            addCriterion("SCH_STAGE_ID >", value, "schStageId");
            return (Criteria) this;
        }

        public Criteria andSchStageIdGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_STAGE_ID >=", value, "schStageId");
            return (Criteria) this;
        }

        public Criteria andSchStageIdLessThan(String value) {
            addCriterion("SCH_STAGE_ID <", value, "schStageId");
            return (Criteria) this;
        }

        public Criteria andSchStageIdLessThanOrEqualTo(String value) {
            addCriterion("SCH_STAGE_ID <=", value, "schStageId");
            return (Criteria) this;
        }

        public Criteria andSchStageIdLike(String value) {
            addCriterion("SCH_STAGE_ID like", value, "schStageId");
            return (Criteria) this;
        }

        public Criteria andSchStageIdNotLike(String value) {
            addCriterion("SCH_STAGE_ID not like", value, "schStageId");
            return (Criteria) this;
        }

        public Criteria andSchStageIdIn(List<String> values) {
            addCriterion("SCH_STAGE_ID in", values, "schStageId");
            return (Criteria) this;
        }

        public Criteria andSchStageIdNotIn(List<String> values) {
            addCriterion("SCH_STAGE_ID not in", values, "schStageId");
            return (Criteria) this;
        }

        public Criteria andSchStageIdBetween(String value1, String value2) {
            addCriterion("SCH_STAGE_ID between", value1, value2, "schStageId");
            return (Criteria) this;
        }

        public Criteria andSchStageIdNotBetween(String value1, String value2) {
            addCriterion("SCH_STAGE_ID not between", value1, value2, "schStageId");
            return (Criteria) this;
        }

        public Criteria andSchStageNameIsNull() {
            addCriterion("SCH_STAGE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSchStageNameIsNotNull() {
            addCriterion("SCH_STAGE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSchStageNameEqualTo(String value) {
            addCriterion("SCH_STAGE_NAME =", value, "schStageName");
            return (Criteria) this;
        }

        public Criteria andSchStageNameNotEqualTo(String value) {
            addCriterion("SCH_STAGE_NAME <>", value, "schStageName");
            return (Criteria) this;
        }

        public Criteria andSchStageNameGreaterThan(String value) {
            addCriterion("SCH_STAGE_NAME >", value, "schStageName");
            return (Criteria) this;
        }

        public Criteria andSchStageNameGreaterThanOrEqualTo(String value) {
            addCriterion("SCH_STAGE_NAME >=", value, "schStageName");
            return (Criteria) this;
        }

        public Criteria andSchStageNameLessThan(String value) {
            addCriterion("SCH_STAGE_NAME <", value, "schStageName");
            return (Criteria) this;
        }

        public Criteria andSchStageNameLessThanOrEqualTo(String value) {
            addCriterion("SCH_STAGE_NAME <=", value, "schStageName");
            return (Criteria) this;
        }

        public Criteria andSchStageNameLike(String value) {
            addCriterion("SCH_STAGE_NAME like", value, "schStageName");
            return (Criteria) this;
        }

        public Criteria andSchStageNameNotLike(String value) {
            addCriterion("SCH_STAGE_NAME not like", value, "schStageName");
            return (Criteria) this;
        }

        public Criteria andSchStageNameIn(List<String> values) {
            addCriterion("SCH_STAGE_NAME in", values, "schStageName");
            return (Criteria) this;
        }

        public Criteria andSchStageNameNotIn(List<String> values) {
            addCriterion("SCH_STAGE_NAME not in", values, "schStageName");
            return (Criteria) this;
        }

        public Criteria andSchStageNameBetween(String value1, String value2) {
            addCriterion("SCH_STAGE_NAME between", value1, value2, "schStageName");
            return (Criteria) this;
        }

        public Criteria andSchStageNameNotBetween(String value1, String value2) {
            addCriterion("SCH_STAGE_NAME not between", value1, value2, "schStageName");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIsNull() {
            addCriterion("SESSION_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIsNotNull() {
            addCriterion("SESSION_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andSessionNumberEqualTo(String value) {
            addCriterion("SESSION_NUMBER =", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotEqualTo(String value) {
            addCriterion("SESSION_NUMBER <>", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberGreaterThan(String value) {
            addCriterion("SESSION_NUMBER >", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberGreaterThanOrEqualTo(String value) {
            addCriterion("SESSION_NUMBER >=", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLessThan(String value) {
            addCriterion("SESSION_NUMBER <", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLessThanOrEqualTo(String value) {
            addCriterion("SESSION_NUMBER <=", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberLike(String value) {
            addCriterion("SESSION_NUMBER like", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotLike(String value) {
            addCriterion("SESSION_NUMBER not like", value, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberIn(List<String> values) {
            addCriterion("SESSION_NUMBER in", values, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotIn(List<String> values) {
            addCriterion("SESSION_NUMBER not in", values, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberBetween(String value1, String value2) {
            addCriterion("SESSION_NUMBER between", value1, value2, "sessionNumber");
            return (Criteria) this;
        }

        public Criteria andSessionNumberNotBetween(String value1, String value2) {
            addCriterion("SESSION_NUMBER not between", value1, value2, "sessionNumber");
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