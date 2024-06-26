package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class SysWsConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysWsConfigExample() {
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

        public Criteria andWsIdIsNull() {
            addCriterion("WS_ID is null");
            return (Criteria) this;
        }

        public Criteria andWsIdIsNotNull() {
            addCriterion("WS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andWsIdEqualTo(String value) {
            addCriterion("WS_ID =", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdNotEqualTo(String value) {
            addCriterion("WS_ID <>", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdGreaterThan(String value) {
            addCriterion("WS_ID >", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdGreaterThanOrEqualTo(String value) {
            addCriterion("WS_ID >=", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdLessThan(String value) {
            addCriterion("WS_ID <", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdLessThanOrEqualTo(String value) {
            addCriterion("WS_ID <=", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdLike(String value) {
            addCriterion("WS_ID like", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdNotLike(String value) {
            addCriterion("WS_ID not like", value, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdIn(List<String> values) {
            addCriterion("WS_ID in", values, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdNotIn(List<String> values) {
            addCriterion("WS_ID not in", values, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdBetween(String value1, String value2) {
            addCriterion("WS_ID between", value1, value2, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsIdNotBetween(String value1, String value2) {
            addCriterion("WS_ID not between", value1, value2, "wsId");
            return (Criteria) this;
        }

        public Criteria andWsNameIsNull() {
            addCriterion("WS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andWsNameIsNotNull() {
            addCriterion("WS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andWsNameEqualTo(String value) {
            addCriterion("WS_NAME =", value, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameNotEqualTo(String value) {
            addCriterion("WS_NAME <>", value, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameGreaterThan(String value) {
            addCriterion("WS_NAME >", value, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameGreaterThanOrEqualTo(String value) {
            addCriterion("WS_NAME >=", value, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameLessThan(String value) {
            addCriterion("WS_NAME <", value, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameLessThanOrEqualTo(String value) {
            addCriterion("WS_NAME <=", value, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameLike(String value) {
            addCriterion("WS_NAME like", value, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameNotLike(String value) {
            addCriterion("WS_NAME not like", value, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameIn(List<String> values) {
            addCriterion("WS_NAME in", values, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameNotIn(List<String> values) {
            addCriterion("WS_NAME not in", values, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameBetween(String value1, String value2) {
            addCriterion("WS_NAME between", value1, value2, "wsName");
            return (Criteria) this;
        }

        public Criteria andWsNameNotBetween(String value1, String value2) {
            addCriterion("WS_NAME not between", value1, value2, "wsName");
            return (Criteria) this;
        }

        public Criteria andLoginUrlIsNull() {
            addCriterion("LOGIN_URL is null");
            return (Criteria) this;
        }

        public Criteria andLoginUrlIsNotNull() {
            addCriterion("LOGIN_URL is not null");
            return (Criteria) this;
        }

        public Criteria andLoginUrlEqualTo(String value) {
            addCriterion("LOGIN_URL =", value, "loginUrl");
            return (Criteria) this;
        }

        public Criteria andLoginUrlNotEqualTo(String value) {
            addCriterion("LOGIN_URL <>", value, "loginUrl");
            return (Criteria) this;
        }

        public Criteria andLoginUrlGreaterThan(String value) {
            addCriterion("LOGIN_URL >", value, "loginUrl");
            return (Criteria) this;
        }

        public Criteria andLoginUrlGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_URL >=", value, "loginUrl");
            return (Criteria) this;
        }

        public Criteria andLoginUrlLessThan(String value) {
            addCriterion("LOGIN_URL <", value, "loginUrl");
            return (Criteria) this;
        }

        public Criteria andLoginUrlLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_URL <=", value, "loginUrl");
            return (Criteria) this;
        }

        public Criteria andLoginUrlLike(String value) {
            addCriterion("LOGIN_URL like", value, "loginUrl");
            return (Criteria) this;
        }

        public Criteria andLoginUrlNotLike(String value) {
            addCriterion("LOGIN_URL not like", value, "loginUrl");
            return (Criteria) this;
        }

        public Criteria andLoginUrlIn(List<String> values) {
            addCriterion("LOGIN_URL in", values, "loginUrl");
            return (Criteria) this;
        }

        public Criteria andLoginUrlNotIn(List<String> values) {
            addCriterion("LOGIN_URL not in", values, "loginUrl");
            return (Criteria) this;
        }

        public Criteria andLoginUrlBetween(String value1, String value2) {
            addCriterion("LOGIN_URL between", value1, value2, "loginUrl");
            return (Criteria) this;
        }

        public Criteria andLoginUrlNotBetween(String value1, String value2) {
            addCriterion("LOGIN_URL not between", value1, value2, "loginUrl");
            return (Criteria) this;
        }

        public Criteria andLogoutUrlIsNull() {
            addCriterion("LOGOUT_URL is null");
            return (Criteria) this;
        }

        public Criteria andLogoutUrlIsNotNull() {
            addCriterion("LOGOUT_URL is not null");
            return (Criteria) this;
        }

        public Criteria andLogoutUrlEqualTo(String value) {
            addCriterion("LOGOUT_URL =", value, "logoutUrl");
            return (Criteria) this;
        }

        public Criteria andLogoutUrlNotEqualTo(String value) {
            addCriterion("LOGOUT_URL <>", value, "logoutUrl");
            return (Criteria) this;
        }

        public Criteria andLogoutUrlGreaterThan(String value) {
            addCriterion("LOGOUT_URL >", value, "logoutUrl");
            return (Criteria) this;
        }

        public Criteria andLogoutUrlGreaterThanOrEqualTo(String value) {
            addCriterion("LOGOUT_URL >=", value, "logoutUrl");
            return (Criteria) this;
        }

        public Criteria andLogoutUrlLessThan(String value) {
            addCriterion("LOGOUT_URL <", value, "logoutUrl");
            return (Criteria) this;
        }

        public Criteria andLogoutUrlLessThanOrEqualTo(String value) {
            addCriterion("LOGOUT_URL <=", value, "logoutUrl");
            return (Criteria) this;
        }

        public Criteria andLogoutUrlLike(String value) {
            addCriterion("LOGOUT_URL like", value, "logoutUrl");
            return (Criteria) this;
        }

        public Criteria andLogoutUrlNotLike(String value) {
            addCriterion("LOGOUT_URL not like", value, "logoutUrl");
            return (Criteria) this;
        }

        public Criteria andLogoutUrlIn(List<String> values) {
            addCriterion("LOGOUT_URL in", values, "logoutUrl");
            return (Criteria) this;
        }

        public Criteria andLogoutUrlNotIn(List<String> values) {
            addCriterion("LOGOUT_URL not in", values, "logoutUrl");
            return (Criteria) this;
        }

        public Criteria andLogoutUrlBetween(String value1, String value2) {
            addCriterion("LOGOUT_URL between", value1, value2, "logoutUrl");
            return (Criteria) this;
        }

        public Criteria andLogoutUrlNotBetween(String value1, String value2) {
            addCriterion("LOGOUT_URL not between", value1, value2, "logoutUrl");
            return (Criteria) this;
        }

        public Criteria andSysLoginImgIsNull() {
            addCriterion("SYS_LOGIN_IMG is null");
            return (Criteria) this;
        }

        public Criteria andSysLoginImgIsNotNull() {
            addCriterion("SYS_LOGIN_IMG is not null");
            return (Criteria) this;
        }

        public Criteria andSysLoginImgEqualTo(String value) {
            addCriterion("SYS_LOGIN_IMG =", value, "sysLoginImg");
            return (Criteria) this;
        }

        public Criteria andSysLoginImgNotEqualTo(String value) {
            addCriterion("SYS_LOGIN_IMG <>", value, "sysLoginImg");
            return (Criteria) this;
        }

        public Criteria andSysLoginImgGreaterThan(String value) {
            addCriterion("SYS_LOGIN_IMG >", value, "sysLoginImg");
            return (Criteria) this;
        }

        public Criteria andSysLoginImgGreaterThanOrEqualTo(String value) {
            addCriterion("SYS_LOGIN_IMG >=", value, "sysLoginImg");
            return (Criteria) this;
        }

        public Criteria andSysLoginImgLessThan(String value) {
            addCriterion("SYS_LOGIN_IMG <", value, "sysLoginImg");
            return (Criteria) this;
        }

        public Criteria andSysLoginImgLessThanOrEqualTo(String value) {
            addCriterion("SYS_LOGIN_IMG <=", value, "sysLoginImg");
            return (Criteria) this;
        }

        public Criteria andSysLoginImgLike(String value) {
            addCriterion("SYS_LOGIN_IMG like", value, "sysLoginImg");
            return (Criteria) this;
        }

        public Criteria andSysLoginImgNotLike(String value) {
            addCriterion("SYS_LOGIN_IMG not like", value, "sysLoginImg");
            return (Criteria) this;
        }

        public Criteria andSysLoginImgIn(List<String> values) {
            addCriterion("SYS_LOGIN_IMG in", values, "sysLoginImg");
            return (Criteria) this;
        }

        public Criteria andSysLoginImgNotIn(List<String> values) {
            addCriterion("SYS_LOGIN_IMG not in", values, "sysLoginImg");
            return (Criteria) this;
        }

        public Criteria andSysLoginImgBetween(String value1, String value2) {
            addCriterion("SYS_LOGIN_IMG between", value1, value2, "sysLoginImg");
            return (Criteria) this;
        }

        public Criteria andSysLoginImgNotBetween(String value1, String value2) {
            addCriterion("SYS_LOGIN_IMG not between", value1, value2, "sysLoginImg");
            return (Criteria) this;
        }

        public Criteria andSysHeadImgIsNull() {
            addCriterion("SYS_HEAD_IMG is null");
            return (Criteria) this;
        }

        public Criteria andSysHeadImgIsNotNull() {
            addCriterion("SYS_HEAD_IMG is not null");
            return (Criteria) this;
        }

        public Criteria andSysHeadImgEqualTo(String value) {
            addCriterion("SYS_HEAD_IMG =", value, "sysHeadImg");
            return (Criteria) this;
        }

        public Criteria andSysHeadImgNotEqualTo(String value) {
            addCriterion("SYS_HEAD_IMG <>", value, "sysHeadImg");
            return (Criteria) this;
        }

        public Criteria andSysHeadImgGreaterThan(String value) {
            addCriterion("SYS_HEAD_IMG >", value, "sysHeadImg");
            return (Criteria) this;
        }

        public Criteria andSysHeadImgGreaterThanOrEqualTo(String value) {
            addCriterion("SYS_HEAD_IMG >=", value, "sysHeadImg");
            return (Criteria) this;
        }

        public Criteria andSysHeadImgLessThan(String value) {
            addCriterion("SYS_HEAD_IMG <", value, "sysHeadImg");
            return (Criteria) this;
        }

        public Criteria andSysHeadImgLessThanOrEqualTo(String value) {
            addCriterion("SYS_HEAD_IMG <=", value, "sysHeadImg");
            return (Criteria) this;
        }

        public Criteria andSysHeadImgLike(String value) {
            addCriterion("SYS_HEAD_IMG like", value, "sysHeadImg");
            return (Criteria) this;
        }

        public Criteria andSysHeadImgNotLike(String value) {
            addCriterion("SYS_HEAD_IMG not like", value, "sysHeadImg");
            return (Criteria) this;
        }

        public Criteria andSysHeadImgIn(List<String> values) {
            addCriterion("SYS_HEAD_IMG in", values, "sysHeadImg");
            return (Criteria) this;
        }

        public Criteria andSysHeadImgNotIn(List<String> values) {
            addCriterion("SYS_HEAD_IMG not in", values, "sysHeadImg");
            return (Criteria) this;
        }

        public Criteria andSysHeadImgBetween(String value1, String value2) {
            addCriterion("SYS_HEAD_IMG between", value1, value2, "sysHeadImg");
            return (Criteria) this;
        }

        public Criteria andSysHeadImgNotBetween(String value1, String value2) {
            addCriterion("SYS_HEAD_IMG not between", value1, value2, "sysHeadImg");
            return (Criteria) this;
        }

        public Criteria andSysTitleNameIsNull() {
            addCriterion("SYS_TITLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSysTitleNameIsNotNull() {
            addCriterion("SYS_TITLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSysTitleNameEqualTo(String value) {
            addCriterion("SYS_TITLE_NAME =", value, "sysTitleName");
            return (Criteria) this;
        }

        public Criteria andSysTitleNameNotEqualTo(String value) {
            addCriterion("SYS_TITLE_NAME <>", value, "sysTitleName");
            return (Criteria) this;
        }

        public Criteria andSysTitleNameGreaterThan(String value) {
            addCriterion("SYS_TITLE_NAME >", value, "sysTitleName");
            return (Criteria) this;
        }

        public Criteria andSysTitleNameGreaterThanOrEqualTo(String value) {
            addCriterion("SYS_TITLE_NAME >=", value, "sysTitleName");
            return (Criteria) this;
        }

        public Criteria andSysTitleNameLessThan(String value) {
            addCriterion("SYS_TITLE_NAME <", value, "sysTitleName");
            return (Criteria) this;
        }

        public Criteria andSysTitleNameLessThanOrEqualTo(String value) {
            addCriterion("SYS_TITLE_NAME <=", value, "sysTitleName");
            return (Criteria) this;
        }

        public Criteria andSysTitleNameLike(String value) {
            addCriterion("SYS_TITLE_NAME like", value, "sysTitleName");
            return (Criteria) this;
        }

        public Criteria andSysTitleNameNotLike(String value) {
            addCriterion("SYS_TITLE_NAME not like", value, "sysTitleName");
            return (Criteria) this;
        }

        public Criteria andSysTitleNameIn(List<String> values) {
            addCriterion("SYS_TITLE_NAME in", values, "sysTitleName");
            return (Criteria) this;
        }

        public Criteria andSysTitleNameNotIn(List<String> values) {
            addCriterion("SYS_TITLE_NAME not in", values, "sysTitleName");
            return (Criteria) this;
        }

        public Criteria andSysTitleNameBetween(String value1, String value2) {
            addCriterion("SYS_TITLE_NAME between", value1, value2, "sysTitleName");
            return (Criteria) this;
        }

        public Criteria andSysTitleNameNotBetween(String value1, String value2) {
            addCriterion("SYS_TITLE_NAME not between", value1, value2, "sysTitleName");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeIsNull() {
            addCriterion("ADMISSION_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeIsNotNull() {
            addCriterion("ADMISSION_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeEqualTo(String value) {
            addCriterion("ADMISSION_TIME =", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeNotEqualTo(String value) {
            addCriterion("ADMISSION_TIME <>", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeGreaterThan(String value) {
            addCriterion("ADMISSION_TIME >", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeGreaterThanOrEqualTo(String value) {
            addCriterion("ADMISSION_TIME >=", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeLessThan(String value) {
            addCriterion("ADMISSION_TIME <", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeLessThanOrEqualTo(String value) {
            addCriterion("ADMISSION_TIME <=", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeLike(String value) {
            addCriterion("ADMISSION_TIME like", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeNotLike(String value) {
            addCriterion("ADMISSION_TIME not like", value, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeIn(List<String> values) {
            addCriterion("ADMISSION_TIME in", values, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeNotIn(List<String> values) {
            addCriterion("ADMISSION_TIME not in", values, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeBetween(String value1, String value2) {
            addCriterion("ADMISSION_TIME between", value1, value2, "admissionTime");
            return (Criteria) this;
        }

        public Criteria andAdmissionTimeNotBetween(String value1, String value2) {
            addCriterion("ADMISSION_TIME not between", value1, value2, "admissionTime");
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

        public Criteria andIsDefaultIsNull() {
            addCriterion("IS_DEFAULT is null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIsNotNull() {
            addCriterion("IS_DEFAULT is not null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultEqualTo(String value) {
            addCriterion("IS_DEFAULT =", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotEqualTo(String value) {
            addCriterion("IS_DEFAULT <>", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultGreaterThan(String value) {
            addCriterion("IS_DEFAULT >", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultGreaterThanOrEqualTo(String value) {
            addCriterion("IS_DEFAULT >=", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLessThan(String value) {
            addCriterion("IS_DEFAULT <", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLessThanOrEqualTo(String value) {
            addCriterion("IS_DEFAULT <=", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLike(String value) {
            addCriterion("IS_DEFAULT like", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotLike(String value) {
            addCriterion("IS_DEFAULT not like", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIn(List<String> values) {
            addCriterion("IS_DEFAULT in", values, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotIn(List<String> values) {
            addCriterion("IS_DEFAULT not in", values, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultBetween(String value1, String value2) {
            addCriterion("IS_DEFAULT between", value1, value2, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotBetween(String value1, String value2) {
            addCriterion("IS_DEFAULT not between", value1, value2, "isDefault");
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