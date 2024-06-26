package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class GydxjInsertGradeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GydxjInsertGradeExample() {
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

        public Criteria andStuNoIsNull() {
            addCriterion("STU_NO is null");
            return (Criteria) this;
        }

        public Criteria andStuNoIsNotNull() {
            addCriterion("STU_NO is not null");
            return (Criteria) this;
        }

        public Criteria andStuNoEqualTo(String value) {
            addCriterion("STU_NO =", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoNotEqualTo(String value) {
            addCriterion("STU_NO <>", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoGreaterThan(String value) {
            addCriterion("STU_NO >", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoGreaterThanOrEqualTo(String value) {
            addCriterion("STU_NO >=", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoLessThan(String value) {
            addCriterion("STU_NO <", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoLessThanOrEqualTo(String value) {
            addCriterion("STU_NO <=", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoLike(String value) {
            addCriterion("STU_NO like", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoNotLike(String value) {
            addCriterion("STU_NO not like", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoIn(List<String> values) {
            addCriterion("STU_NO in", values, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoNotIn(List<String> values) {
            addCriterion("STU_NO not in", values, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoBetween(String value1, String value2) {
            addCriterion("STU_NO between", value1, value2, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoNotBetween(String value1, String value2) {
            addCriterion("STU_NO not between", value1, value2, "stuNo");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIsNull() {
            addCriterion("TRAIN_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIsNotNull() {
            addCriterion("TRAIN_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTrainTypeEqualTo(String value) {
            addCriterion("TRAIN_TYPE =", value, "trainType");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNotEqualTo(String value) {
            addCriterion("TRAIN_TYPE <>", value, "trainType");
            return (Criteria) this;
        }

        public Criteria andTrainTypeGreaterThan(String value) {
            addCriterion("TRAIN_TYPE >", value, "trainType");
            return (Criteria) this;
        }

        public Criteria andTrainTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_TYPE >=", value, "trainType");
            return (Criteria) this;
        }

        public Criteria andTrainTypeLessThan(String value) {
            addCriterion("TRAIN_TYPE <", value, "trainType");
            return (Criteria) this;
        }

        public Criteria andTrainTypeLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_TYPE <=", value, "trainType");
            return (Criteria) this;
        }

        public Criteria andTrainTypeLike(String value) {
            addCriterion("TRAIN_TYPE like", value, "trainType");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNotLike(String value) {
            addCriterion("TRAIN_TYPE not like", value, "trainType");
            return (Criteria) this;
        }

        public Criteria andTrainTypeIn(List<String> values) {
            addCriterion("TRAIN_TYPE in", values, "trainType");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNotIn(List<String> values) {
            addCriterion("TRAIN_TYPE not in", values, "trainType");
            return (Criteria) this;
        }

        public Criteria andTrainTypeBetween(String value1, String value2) {
            addCriterion("TRAIN_TYPE between", value1, value2, "trainType");
            return (Criteria) this;
        }

        public Criteria andTrainTypeNotBetween(String value1, String value2) {
            addCriterion("TRAIN_TYPE not between", value1, value2, "trainType");
            return (Criteria) this;
        }

        public Criteria andTrainTypeEnIsNull() {
            addCriterion("TRAIN_TYPE_EN is null");
            return (Criteria) this;
        }

        public Criteria andTrainTypeEnIsNotNull() {
            addCriterion("TRAIN_TYPE_EN is not null");
            return (Criteria) this;
        }

        public Criteria andTrainTypeEnEqualTo(String value) {
            addCriterion("TRAIN_TYPE_EN =", value, "trainTypeEn");
            return (Criteria) this;
        }

        public Criteria andTrainTypeEnNotEqualTo(String value) {
            addCriterion("TRAIN_TYPE_EN <>", value, "trainTypeEn");
            return (Criteria) this;
        }

        public Criteria andTrainTypeEnGreaterThan(String value) {
            addCriterion("TRAIN_TYPE_EN >", value, "trainTypeEn");
            return (Criteria) this;
        }

        public Criteria andTrainTypeEnGreaterThanOrEqualTo(String value) {
            addCriterion("TRAIN_TYPE_EN >=", value, "trainTypeEn");
            return (Criteria) this;
        }

        public Criteria andTrainTypeEnLessThan(String value) {
            addCriterion("TRAIN_TYPE_EN <", value, "trainTypeEn");
            return (Criteria) this;
        }

        public Criteria andTrainTypeEnLessThanOrEqualTo(String value) {
            addCriterion("TRAIN_TYPE_EN <=", value, "trainTypeEn");
            return (Criteria) this;
        }

        public Criteria andTrainTypeEnLike(String value) {
            addCriterion("TRAIN_TYPE_EN like", value, "trainTypeEn");
            return (Criteria) this;
        }

        public Criteria andTrainTypeEnNotLike(String value) {
            addCriterion("TRAIN_TYPE_EN not like", value, "trainTypeEn");
            return (Criteria) this;
        }

        public Criteria andTrainTypeEnIn(List<String> values) {
            addCriterion("TRAIN_TYPE_EN in", values, "trainTypeEn");
            return (Criteria) this;
        }

        public Criteria andTrainTypeEnNotIn(List<String> values) {
            addCriterion("TRAIN_TYPE_EN not in", values, "trainTypeEn");
            return (Criteria) this;
        }

        public Criteria andTrainTypeEnBetween(String value1, String value2) {
            addCriterion("TRAIN_TYPE_EN between", value1, value2, "trainTypeEn");
            return (Criteria) this;
        }

        public Criteria andTrainTypeEnNotBetween(String value1, String value2) {
            addCriterion("TRAIN_TYPE_EN not between", value1, value2, "trainTypeEn");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("USER_NAME =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("USER_NAME <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("USER_NAME >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_NAME >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("USER_NAME <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("USER_NAME <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("USER_NAME like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("USER_NAME not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("USER_NAME in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("USER_NAME not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("USER_NAME between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("USER_NAME not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameEnIsNull() {
            addCriterion("USER_NAME_EN is null");
            return (Criteria) this;
        }

        public Criteria andUserNameEnIsNotNull() {
            addCriterion("USER_NAME_EN is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEnEqualTo(String value) {
            addCriterion("USER_NAME_EN =", value, "userNameEn");
            return (Criteria) this;
        }

        public Criteria andUserNameEnNotEqualTo(String value) {
            addCriterion("USER_NAME_EN <>", value, "userNameEn");
            return (Criteria) this;
        }

        public Criteria andUserNameEnGreaterThan(String value) {
            addCriterion("USER_NAME_EN >", value, "userNameEn");
            return (Criteria) this;
        }

        public Criteria andUserNameEnGreaterThanOrEqualTo(String value) {
            addCriterion("USER_NAME_EN >=", value, "userNameEn");
            return (Criteria) this;
        }

        public Criteria andUserNameEnLessThan(String value) {
            addCriterion("USER_NAME_EN <", value, "userNameEn");
            return (Criteria) this;
        }

        public Criteria andUserNameEnLessThanOrEqualTo(String value) {
            addCriterion("USER_NAME_EN <=", value, "userNameEn");
            return (Criteria) this;
        }

        public Criteria andUserNameEnLike(String value) {
            addCriterion("USER_NAME_EN like", value, "userNameEn");
            return (Criteria) this;
        }

        public Criteria andUserNameEnNotLike(String value) {
            addCriterion("USER_NAME_EN not like", value, "userNameEn");
            return (Criteria) this;
        }

        public Criteria andUserNameEnIn(List<String> values) {
            addCriterion("USER_NAME_EN in", values, "userNameEn");
            return (Criteria) this;
        }

        public Criteria andUserNameEnNotIn(List<String> values) {
            addCriterion("USER_NAME_EN not in", values, "userNameEn");
            return (Criteria) this;
        }

        public Criteria andUserNameEnBetween(String value1, String value2) {
            addCriterion("USER_NAME_EN between", value1, value2, "userNameEn");
            return (Criteria) this;
        }

        public Criteria andUserNameEnNotBetween(String value1, String value2) {
            addCriterion("USER_NAME_EN not between", value1, value2, "userNameEn");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIsNull() {
            addCriterion("DEGREE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIsNotNull() {
            addCriterion("DEGREE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeEqualTo(String value) {
            addCriterion("DEGREE_TYPE =", value, "degreeType");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNotEqualTo(String value) {
            addCriterion("DEGREE_TYPE <>", value, "degreeType");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeGreaterThan(String value) {
            addCriterion("DEGREE_TYPE >", value, "degreeType");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("DEGREE_TYPE >=", value, "degreeType");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeLessThan(String value) {
            addCriterion("DEGREE_TYPE <", value, "degreeType");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeLessThanOrEqualTo(String value) {
            addCriterion("DEGREE_TYPE <=", value, "degreeType");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeLike(String value) {
            addCriterion("DEGREE_TYPE like", value, "degreeType");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNotLike(String value) {
            addCriterion("DEGREE_TYPE not like", value, "degreeType");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeIn(List<String> values) {
            addCriterion("DEGREE_TYPE in", values, "degreeType");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNotIn(List<String> values) {
            addCriterion("DEGREE_TYPE not in", values, "degreeType");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeBetween(String value1, String value2) {
            addCriterion("DEGREE_TYPE between", value1, value2, "degreeType");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeNotBetween(String value1, String value2) {
            addCriterion("DEGREE_TYPE not between", value1, value2, "degreeType");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeEnIsNull() {
            addCriterion("DEGREE_TYPE_EN is null");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeEnIsNotNull() {
            addCriterion("DEGREE_TYPE_EN is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeEnEqualTo(String value) {
            addCriterion("DEGREE_TYPE_EN =", value, "degreeTypeEn");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeEnNotEqualTo(String value) {
            addCriterion("DEGREE_TYPE_EN <>", value, "degreeTypeEn");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeEnGreaterThan(String value) {
            addCriterion("DEGREE_TYPE_EN >", value, "degreeTypeEn");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeEnGreaterThanOrEqualTo(String value) {
            addCriterion("DEGREE_TYPE_EN >=", value, "degreeTypeEn");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeEnLessThan(String value) {
            addCriterion("DEGREE_TYPE_EN <", value, "degreeTypeEn");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeEnLessThanOrEqualTo(String value) {
            addCriterion("DEGREE_TYPE_EN <=", value, "degreeTypeEn");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeEnLike(String value) {
            addCriterion("DEGREE_TYPE_EN like", value, "degreeTypeEn");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeEnNotLike(String value) {
            addCriterion("DEGREE_TYPE_EN not like", value, "degreeTypeEn");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeEnIn(List<String> values) {
            addCriterion("DEGREE_TYPE_EN in", values, "degreeTypeEn");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeEnNotIn(List<String> values) {
            addCriterion("DEGREE_TYPE_EN not in", values, "degreeTypeEn");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeEnBetween(String value1, String value2) {
            addCriterion("DEGREE_TYPE_EN between", value1, value2, "degreeTypeEn");
            return (Criteria) this;
        }

        public Criteria andDegreeTypeEnNotBetween(String value1, String value2) {
            addCriterion("DEGREE_TYPE_EN not between", value1, value2, "degreeTypeEn");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("SEX is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("SEX is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("SEX =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("SEX <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("SEX >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("SEX >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("SEX <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("SEX <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("SEX like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("SEX not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("SEX in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("SEX not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("SEX between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("SEX not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexEnIsNull() {
            addCriterion("SEX_EN is null");
            return (Criteria) this;
        }

        public Criteria andSexEnIsNotNull() {
            addCriterion("SEX_EN is not null");
            return (Criteria) this;
        }

        public Criteria andSexEnEqualTo(String value) {
            addCriterion("SEX_EN =", value, "sexEn");
            return (Criteria) this;
        }

        public Criteria andSexEnNotEqualTo(String value) {
            addCriterion("SEX_EN <>", value, "sexEn");
            return (Criteria) this;
        }

        public Criteria andSexEnGreaterThan(String value) {
            addCriterion("SEX_EN >", value, "sexEn");
            return (Criteria) this;
        }

        public Criteria andSexEnGreaterThanOrEqualTo(String value) {
            addCriterion("SEX_EN >=", value, "sexEn");
            return (Criteria) this;
        }

        public Criteria andSexEnLessThan(String value) {
            addCriterion("SEX_EN <", value, "sexEn");
            return (Criteria) this;
        }

        public Criteria andSexEnLessThanOrEqualTo(String value) {
            addCriterion("SEX_EN <=", value, "sexEn");
            return (Criteria) this;
        }

        public Criteria andSexEnLike(String value) {
            addCriterion("SEX_EN like", value, "sexEn");
            return (Criteria) this;
        }

        public Criteria andSexEnNotLike(String value) {
            addCriterion("SEX_EN not like", value, "sexEn");
            return (Criteria) this;
        }

        public Criteria andSexEnIn(List<String> values) {
            addCriterion("SEX_EN in", values, "sexEn");
            return (Criteria) this;
        }

        public Criteria andSexEnNotIn(List<String> values) {
            addCriterion("SEX_EN not in", values, "sexEn");
            return (Criteria) this;
        }

        public Criteria andSexEnBetween(String value1, String value2) {
            addCriterion("SEX_EN between", value1, value2, "sexEn");
            return (Criteria) this;
        }

        public Criteria andSexEnNotBetween(String value1, String value2) {
            addCriterion("SEX_EN not between", value1, value2, "sexEn");
            return (Criteria) this;
        }

        public Criteria andMajorIsNull() {
            addCriterion("MAJOR is null");
            return (Criteria) this;
        }

        public Criteria andMajorIsNotNull() {
            addCriterion("MAJOR is not null");
            return (Criteria) this;
        }

        public Criteria andMajorEqualTo(String value) {
            addCriterion("MAJOR =", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorNotEqualTo(String value) {
            addCriterion("MAJOR <>", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorGreaterThan(String value) {
            addCriterion("MAJOR >", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorGreaterThanOrEqualTo(String value) {
            addCriterion("MAJOR >=", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorLessThan(String value) {
            addCriterion("MAJOR <", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorLessThanOrEqualTo(String value) {
            addCriterion("MAJOR <=", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorLike(String value) {
            addCriterion("MAJOR like", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorNotLike(String value) {
            addCriterion("MAJOR not like", value, "major");
            return (Criteria) this;
        }

        public Criteria andMajorIn(List<String> values) {
            addCriterion("MAJOR in", values, "major");
            return (Criteria) this;
        }

        public Criteria andMajorNotIn(List<String> values) {
            addCriterion("MAJOR not in", values, "major");
            return (Criteria) this;
        }

        public Criteria andMajorBetween(String value1, String value2) {
            addCriterion("MAJOR between", value1, value2, "major");
            return (Criteria) this;
        }

        public Criteria andMajorNotBetween(String value1, String value2) {
            addCriterion("MAJOR not between", value1, value2, "major");
            return (Criteria) this;
        }

        public Criteria andMajorEnIsNull() {
            addCriterion("MAJOR_EN is null");
            return (Criteria) this;
        }

        public Criteria andMajorEnIsNotNull() {
            addCriterion("MAJOR_EN is not null");
            return (Criteria) this;
        }

        public Criteria andMajorEnEqualTo(String value) {
            addCriterion("MAJOR_EN =", value, "majorEn");
            return (Criteria) this;
        }

        public Criteria andMajorEnNotEqualTo(String value) {
            addCriterion("MAJOR_EN <>", value, "majorEn");
            return (Criteria) this;
        }

        public Criteria andMajorEnGreaterThan(String value) {
            addCriterion("MAJOR_EN >", value, "majorEn");
            return (Criteria) this;
        }

        public Criteria andMajorEnGreaterThanOrEqualTo(String value) {
            addCriterion("MAJOR_EN >=", value, "majorEn");
            return (Criteria) this;
        }

        public Criteria andMajorEnLessThan(String value) {
            addCriterion("MAJOR_EN <", value, "majorEn");
            return (Criteria) this;
        }

        public Criteria andMajorEnLessThanOrEqualTo(String value) {
            addCriterion("MAJOR_EN <=", value, "majorEn");
            return (Criteria) this;
        }

        public Criteria andMajorEnLike(String value) {
            addCriterion("MAJOR_EN like", value, "majorEn");
            return (Criteria) this;
        }

        public Criteria andMajorEnNotLike(String value) {
            addCriterion("MAJOR_EN not like", value, "majorEn");
            return (Criteria) this;
        }

        public Criteria andMajorEnIn(List<String> values) {
            addCriterion("MAJOR_EN in", values, "majorEn");
            return (Criteria) this;
        }

        public Criteria andMajorEnNotIn(List<String> values) {
            addCriterion("MAJOR_EN not in", values, "majorEn");
            return (Criteria) this;
        }

        public Criteria andMajorEnBetween(String value1, String value2) {
            addCriterion("MAJOR_EN between", value1, value2, "majorEn");
            return (Criteria) this;
        }

        public Criteria andMajorEnNotBetween(String value1, String value2) {
            addCriterion("MAJOR_EN not between", value1, value2, "majorEn");
            return (Criteria) this;
        }

        public Criteria andStudyFormIsNull() {
            addCriterion("STUDY_FORM is null");
            return (Criteria) this;
        }

        public Criteria andStudyFormIsNotNull() {
            addCriterion("STUDY_FORM is not null");
            return (Criteria) this;
        }

        public Criteria andStudyFormEqualTo(String value) {
            addCriterion("STUDY_FORM =", value, "studyForm");
            return (Criteria) this;
        }

        public Criteria andStudyFormNotEqualTo(String value) {
            addCriterion("STUDY_FORM <>", value, "studyForm");
            return (Criteria) this;
        }

        public Criteria andStudyFormGreaterThan(String value) {
            addCriterion("STUDY_FORM >", value, "studyForm");
            return (Criteria) this;
        }

        public Criteria andStudyFormGreaterThanOrEqualTo(String value) {
            addCriterion("STUDY_FORM >=", value, "studyForm");
            return (Criteria) this;
        }

        public Criteria andStudyFormLessThan(String value) {
            addCriterion("STUDY_FORM <", value, "studyForm");
            return (Criteria) this;
        }

        public Criteria andStudyFormLessThanOrEqualTo(String value) {
            addCriterion("STUDY_FORM <=", value, "studyForm");
            return (Criteria) this;
        }

        public Criteria andStudyFormLike(String value) {
            addCriterion("STUDY_FORM like", value, "studyForm");
            return (Criteria) this;
        }

        public Criteria andStudyFormNotLike(String value) {
            addCriterion("STUDY_FORM not like", value, "studyForm");
            return (Criteria) this;
        }

        public Criteria andStudyFormIn(List<String> values) {
            addCriterion("STUDY_FORM in", values, "studyForm");
            return (Criteria) this;
        }

        public Criteria andStudyFormNotIn(List<String> values) {
            addCriterion("STUDY_FORM not in", values, "studyForm");
            return (Criteria) this;
        }

        public Criteria andStudyFormBetween(String value1, String value2) {
            addCriterion("STUDY_FORM between", value1, value2, "studyForm");
            return (Criteria) this;
        }

        public Criteria andStudyFormNotBetween(String value1, String value2) {
            addCriterion("STUDY_FORM not between", value1, value2, "studyForm");
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