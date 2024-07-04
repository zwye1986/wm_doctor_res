package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class FstuAcademicActivityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FstuAcademicActivityExample() {
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

        public Criteria andAcademicFlowIsNull() {
            addCriterion("ACADEMIC_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAcademicFlowIsNotNull() {
            addCriterion("ACADEMIC_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAcademicFlowEqualTo(String value) {
            addCriterion("ACADEMIC_FLOW =", value, "academicFlow");
            return (Criteria) this;
        }

        public Criteria andAcademicFlowNotEqualTo(String value) {
            addCriterion("ACADEMIC_FLOW <>", value, "academicFlow");
            return (Criteria) this;
        }

        public Criteria andAcademicFlowGreaterThan(String value) {
            addCriterion("ACADEMIC_FLOW >", value, "academicFlow");
            return (Criteria) this;
        }

        public Criteria andAcademicFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_FLOW >=", value, "academicFlow");
            return (Criteria) this;
        }

        public Criteria andAcademicFlowLessThan(String value) {
            addCriterion("ACADEMIC_FLOW <", value, "academicFlow");
            return (Criteria) this;
        }

        public Criteria andAcademicFlowLessThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_FLOW <=", value, "academicFlow");
            return (Criteria) this;
        }

        public Criteria andAcademicFlowLike(String value) {
            addCriterion("ACADEMIC_FLOW like", value, "academicFlow");
            return (Criteria) this;
        }

        public Criteria andAcademicFlowNotLike(String value) {
            addCriterion("ACADEMIC_FLOW not like", value, "academicFlow");
            return (Criteria) this;
        }

        public Criteria andAcademicFlowIn(List<String> values) {
            addCriterion("ACADEMIC_FLOW in", values, "academicFlow");
            return (Criteria) this;
        }

        public Criteria andAcademicFlowNotIn(List<String> values) {
            addCriterion("ACADEMIC_FLOW not in", values, "academicFlow");
            return (Criteria) this;
        }

        public Criteria andAcademicFlowBetween(String value1, String value2) {
            addCriterion("ACADEMIC_FLOW between", value1, value2, "academicFlow");
            return (Criteria) this;
        }

        public Criteria andAcademicFlowNotBetween(String value1, String value2) {
            addCriterion("ACADEMIC_FLOW not between", value1, value2, "academicFlow");
            return (Criteria) this;
        }

        public Criteria andAcademicNameIsNull() {
            addCriterion("ACADEMIC_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAcademicNameIsNotNull() {
            addCriterion("ACADEMIC_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAcademicNameEqualTo(String value) {
            addCriterion("ACADEMIC_NAME =", value, "academicName");
            return (Criteria) this;
        }

        public Criteria andAcademicNameNotEqualTo(String value) {
            addCriterion("ACADEMIC_NAME <>", value, "academicName");
            return (Criteria) this;
        }

        public Criteria andAcademicNameGreaterThan(String value) {
            addCriterion("ACADEMIC_NAME >", value, "academicName");
            return (Criteria) this;
        }

        public Criteria andAcademicNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_NAME >=", value, "academicName");
            return (Criteria) this;
        }

        public Criteria andAcademicNameLessThan(String value) {
            addCriterion("ACADEMIC_NAME <", value, "academicName");
            return (Criteria) this;
        }

        public Criteria andAcademicNameLessThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_NAME <=", value, "academicName");
            return (Criteria) this;
        }

        public Criteria andAcademicNameLike(String value) {
            addCriterion("ACADEMIC_NAME like", value, "academicName");
            return (Criteria) this;
        }

        public Criteria andAcademicNameNotLike(String value) {
            addCriterion("ACADEMIC_NAME not like", value, "academicName");
            return (Criteria) this;
        }

        public Criteria andAcademicNameIn(List<String> values) {
            addCriterion("ACADEMIC_NAME in", values, "academicName");
            return (Criteria) this;
        }

        public Criteria andAcademicNameNotIn(List<String> values) {
            addCriterion("ACADEMIC_NAME not in", values, "academicName");
            return (Criteria) this;
        }

        public Criteria andAcademicNameBetween(String value1, String value2) {
            addCriterion("ACADEMIC_NAME between", value1, value2, "academicName");
            return (Criteria) this;
        }

        public Criteria andAcademicNameNotBetween(String value1, String value2) {
            addCriterion("ACADEMIC_NAME not between", value1, value2, "academicName");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeIdIsNull() {
            addCriterion("ACADEMIC_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeIdIsNotNull() {
            addCriterion("ACADEMIC_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeIdEqualTo(String value) {
            addCriterion("ACADEMIC_TYPE_ID =", value, "academicTypeId");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeIdNotEqualTo(String value) {
            addCriterion("ACADEMIC_TYPE_ID <>", value, "academicTypeId");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeIdGreaterThan(String value) {
            addCriterion("ACADEMIC_TYPE_ID >", value, "academicTypeId");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_TYPE_ID >=", value, "academicTypeId");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeIdLessThan(String value) {
            addCriterion("ACADEMIC_TYPE_ID <", value, "academicTypeId");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeIdLessThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_TYPE_ID <=", value, "academicTypeId");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeIdLike(String value) {
            addCriterion("ACADEMIC_TYPE_ID like", value, "academicTypeId");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeIdNotLike(String value) {
            addCriterion("ACADEMIC_TYPE_ID not like", value, "academicTypeId");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeIdIn(List<String> values) {
            addCriterion("ACADEMIC_TYPE_ID in", values, "academicTypeId");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeIdNotIn(List<String> values) {
            addCriterion("ACADEMIC_TYPE_ID not in", values, "academicTypeId");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeIdBetween(String value1, String value2) {
            addCriterion("ACADEMIC_TYPE_ID between", value1, value2, "academicTypeId");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeIdNotBetween(String value1, String value2) {
            addCriterion("ACADEMIC_TYPE_ID not between", value1, value2, "academicTypeId");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeNameIsNull() {
            addCriterion("ACADEMIC_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeNameIsNotNull() {
            addCriterion("ACADEMIC_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeNameEqualTo(String value) {
            addCriterion("ACADEMIC_TYPE_NAME =", value, "academicTypeName");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeNameNotEqualTo(String value) {
            addCriterion("ACADEMIC_TYPE_NAME <>", value, "academicTypeName");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeNameGreaterThan(String value) {
            addCriterion("ACADEMIC_TYPE_NAME >", value, "academicTypeName");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_TYPE_NAME >=", value, "academicTypeName");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeNameLessThan(String value) {
            addCriterion("ACADEMIC_TYPE_NAME <", value, "academicTypeName");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeNameLessThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_TYPE_NAME <=", value, "academicTypeName");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeNameLike(String value) {
            addCriterion("ACADEMIC_TYPE_NAME like", value, "academicTypeName");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeNameNotLike(String value) {
            addCriterion("ACADEMIC_TYPE_NAME not like", value, "academicTypeName");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeNameIn(List<String> values) {
            addCriterion("ACADEMIC_TYPE_NAME in", values, "academicTypeName");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeNameNotIn(List<String> values) {
            addCriterion("ACADEMIC_TYPE_NAME not in", values, "academicTypeName");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeNameBetween(String value1, String value2) {
            addCriterion("ACADEMIC_TYPE_NAME between", value1, value2, "academicTypeName");
            return (Criteria) this;
        }

        public Criteria andAcademicTypeNameNotBetween(String value1, String value2) {
            addCriterion("ACADEMIC_TYPE_NAME not between", value1, value2, "academicTypeName");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceIsNull() {
            addCriterion("PLACE_PROVINCE is null");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceIsNotNull() {
            addCriterion("PLACE_PROVINCE is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceEqualTo(String value) {
            addCriterion("PLACE_PROVINCE =", value, "placeProvince");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceNotEqualTo(String value) {
            addCriterion("PLACE_PROVINCE <>", value, "placeProvince");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceGreaterThan(String value) {
            addCriterion("PLACE_PROVINCE >", value, "placeProvince");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("PLACE_PROVINCE >=", value, "placeProvince");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceLessThan(String value) {
            addCriterion("PLACE_PROVINCE <", value, "placeProvince");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceLessThanOrEqualTo(String value) {
            addCriterion("PLACE_PROVINCE <=", value, "placeProvince");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceLike(String value) {
            addCriterion("PLACE_PROVINCE like", value, "placeProvince");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceNotLike(String value) {
            addCriterion("PLACE_PROVINCE not like", value, "placeProvince");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceIn(List<String> values) {
            addCriterion("PLACE_PROVINCE in", values, "placeProvince");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceNotIn(List<String> values) {
            addCriterion("PLACE_PROVINCE not in", values, "placeProvince");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceBetween(String value1, String value2) {
            addCriterion("PLACE_PROVINCE between", value1, value2, "placeProvince");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceNotBetween(String value1, String value2) {
            addCriterion("PLACE_PROVINCE not between", value1, value2, "placeProvince");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceIdIsNull() {
            addCriterion("PLACE_PROVINCE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceIdIsNotNull() {
            addCriterion("PLACE_PROVINCE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceIdEqualTo(String value) {
            addCriterion("PLACE_PROVINCE_ID =", value, "placeProvinceId");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceIdNotEqualTo(String value) {
            addCriterion("PLACE_PROVINCE_ID <>", value, "placeProvinceId");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceIdGreaterThan(String value) {
            addCriterion("PLACE_PROVINCE_ID >", value, "placeProvinceId");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceIdGreaterThanOrEqualTo(String value) {
            addCriterion("PLACE_PROVINCE_ID >=", value, "placeProvinceId");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceIdLessThan(String value) {
            addCriterion("PLACE_PROVINCE_ID <", value, "placeProvinceId");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceIdLessThanOrEqualTo(String value) {
            addCriterion("PLACE_PROVINCE_ID <=", value, "placeProvinceId");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceIdLike(String value) {
            addCriterion("PLACE_PROVINCE_ID like", value, "placeProvinceId");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceIdNotLike(String value) {
            addCriterion("PLACE_PROVINCE_ID not like", value, "placeProvinceId");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceIdIn(List<String> values) {
            addCriterion("PLACE_PROVINCE_ID in", values, "placeProvinceId");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceIdNotIn(List<String> values) {
            addCriterion("PLACE_PROVINCE_ID not in", values, "placeProvinceId");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceIdBetween(String value1, String value2) {
            addCriterion("PLACE_PROVINCE_ID between", value1, value2, "placeProvinceId");
            return (Criteria) this;
        }

        public Criteria andPlaceProvinceIdNotBetween(String value1, String value2) {
            addCriterion("PLACE_PROVINCE_ID not between", value1, value2, "placeProvinceId");
            return (Criteria) this;
        }

        public Criteria andPlaceCityIsNull() {
            addCriterion("PLACE_CITY is null");
            return (Criteria) this;
        }

        public Criteria andPlaceCityIsNotNull() {
            addCriterion("PLACE_CITY is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceCityEqualTo(String value) {
            addCriterion("PLACE_CITY =", value, "placeCity");
            return (Criteria) this;
        }

        public Criteria andPlaceCityNotEqualTo(String value) {
            addCriterion("PLACE_CITY <>", value, "placeCity");
            return (Criteria) this;
        }

        public Criteria andPlaceCityGreaterThan(String value) {
            addCriterion("PLACE_CITY >", value, "placeCity");
            return (Criteria) this;
        }

        public Criteria andPlaceCityGreaterThanOrEqualTo(String value) {
            addCriterion("PLACE_CITY >=", value, "placeCity");
            return (Criteria) this;
        }

        public Criteria andPlaceCityLessThan(String value) {
            addCriterion("PLACE_CITY <", value, "placeCity");
            return (Criteria) this;
        }

        public Criteria andPlaceCityLessThanOrEqualTo(String value) {
            addCriterion("PLACE_CITY <=", value, "placeCity");
            return (Criteria) this;
        }

        public Criteria andPlaceCityLike(String value) {
            addCriterion("PLACE_CITY like", value, "placeCity");
            return (Criteria) this;
        }

        public Criteria andPlaceCityNotLike(String value) {
            addCriterion("PLACE_CITY not like", value, "placeCity");
            return (Criteria) this;
        }

        public Criteria andPlaceCityIn(List<String> values) {
            addCriterion("PLACE_CITY in", values, "placeCity");
            return (Criteria) this;
        }

        public Criteria andPlaceCityNotIn(List<String> values) {
            addCriterion("PLACE_CITY not in", values, "placeCity");
            return (Criteria) this;
        }

        public Criteria andPlaceCityBetween(String value1, String value2) {
            addCriterion("PLACE_CITY between", value1, value2, "placeCity");
            return (Criteria) this;
        }

        public Criteria andPlaceCityNotBetween(String value1, String value2) {
            addCriterion("PLACE_CITY not between", value1, value2, "placeCity");
            return (Criteria) this;
        }

        public Criteria andPlaceCityIdIsNull() {
            addCriterion("PLACE_CITY_ID is null");
            return (Criteria) this;
        }

        public Criteria andPlaceCityIdIsNotNull() {
            addCriterion("PLACE_CITY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceCityIdEqualTo(String value) {
            addCriterion("PLACE_CITY_ID =", value, "placeCityId");
            return (Criteria) this;
        }

        public Criteria andPlaceCityIdNotEqualTo(String value) {
            addCriterion("PLACE_CITY_ID <>", value, "placeCityId");
            return (Criteria) this;
        }

        public Criteria andPlaceCityIdGreaterThan(String value) {
            addCriterion("PLACE_CITY_ID >", value, "placeCityId");
            return (Criteria) this;
        }

        public Criteria andPlaceCityIdGreaterThanOrEqualTo(String value) {
            addCriterion("PLACE_CITY_ID >=", value, "placeCityId");
            return (Criteria) this;
        }

        public Criteria andPlaceCityIdLessThan(String value) {
            addCriterion("PLACE_CITY_ID <", value, "placeCityId");
            return (Criteria) this;
        }

        public Criteria andPlaceCityIdLessThanOrEqualTo(String value) {
            addCriterion("PLACE_CITY_ID <=", value, "placeCityId");
            return (Criteria) this;
        }

        public Criteria andPlaceCityIdLike(String value) {
            addCriterion("PLACE_CITY_ID like", value, "placeCityId");
            return (Criteria) this;
        }

        public Criteria andPlaceCityIdNotLike(String value) {
            addCriterion("PLACE_CITY_ID not like", value, "placeCityId");
            return (Criteria) this;
        }

        public Criteria andPlaceCityIdIn(List<String> values) {
            addCriterion("PLACE_CITY_ID in", values, "placeCityId");
            return (Criteria) this;
        }

        public Criteria andPlaceCityIdNotIn(List<String> values) {
            addCriterion("PLACE_CITY_ID not in", values, "placeCityId");
            return (Criteria) this;
        }

        public Criteria andPlaceCityIdBetween(String value1, String value2) {
            addCriterion("PLACE_CITY_ID between", value1, value2, "placeCityId");
            return (Criteria) this;
        }

        public Criteria andPlaceCityIdNotBetween(String value1, String value2) {
            addCriterion("PLACE_CITY_ID not between", value1, value2, "placeCityId");
            return (Criteria) this;
        }

        public Criteria andPlaceAreaIsNull() {
            addCriterion("PLACE_AREA is null");
            return (Criteria) this;
        }

        public Criteria andPlaceAreaIsNotNull() {
            addCriterion("PLACE_AREA is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceAreaEqualTo(String value) {
            addCriterion("PLACE_AREA =", value, "placeArea");
            return (Criteria) this;
        }

        public Criteria andPlaceAreaNotEqualTo(String value) {
            addCriterion("PLACE_AREA <>", value, "placeArea");
            return (Criteria) this;
        }

        public Criteria andPlaceAreaGreaterThan(String value) {
            addCriterion("PLACE_AREA >", value, "placeArea");
            return (Criteria) this;
        }

        public Criteria andPlaceAreaGreaterThanOrEqualTo(String value) {
            addCriterion("PLACE_AREA >=", value, "placeArea");
            return (Criteria) this;
        }

        public Criteria andPlaceAreaLessThan(String value) {
            addCriterion("PLACE_AREA <", value, "placeArea");
            return (Criteria) this;
        }

        public Criteria andPlaceAreaLessThanOrEqualTo(String value) {
            addCriterion("PLACE_AREA <=", value, "placeArea");
            return (Criteria) this;
        }

        public Criteria andPlaceAreaLike(String value) {
            addCriterion("PLACE_AREA like", value, "placeArea");
            return (Criteria) this;
        }

        public Criteria andPlaceAreaNotLike(String value) {
            addCriterion("PLACE_AREA not like", value, "placeArea");
            return (Criteria) this;
        }

        public Criteria andPlaceAreaIn(List<String> values) {
            addCriterion("PLACE_AREA in", values, "placeArea");
            return (Criteria) this;
        }

        public Criteria andPlaceAreaNotIn(List<String> values) {
            addCriterion("PLACE_AREA not in", values, "placeArea");
            return (Criteria) this;
        }

        public Criteria andPlaceAreaBetween(String value1, String value2) {
            addCriterion("PLACE_AREA between", value1, value2, "placeArea");
            return (Criteria) this;
        }

        public Criteria andPlaceAreaNotBetween(String value1, String value2) {
            addCriterion("PLACE_AREA not between", value1, value2, "placeArea");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNull() {
            addCriterion("BEGIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("BEGIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(String value) {
            addCriterion("BEGIN_TIME =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(String value) {
            addCriterion("BEGIN_TIME <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(String value) {
            addCriterion("BEGIN_TIME >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(String value) {
            addCriterion("BEGIN_TIME >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(String value) {
            addCriterion("BEGIN_TIME <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(String value) {
            addCriterion("BEGIN_TIME <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLike(String value) {
            addCriterion("BEGIN_TIME like", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotLike(String value) {
            addCriterion("BEGIN_TIME not like", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<String> values) {
            addCriterion("BEGIN_TIME in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<String> values) {
            addCriterion("BEGIN_TIME not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(String value1, String value2) {
            addCriterion("BEGIN_TIME between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(String value1, String value2) {
            addCriterion("BEGIN_TIME not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(String value) {
            addCriterion("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(String value) {
            addCriterion("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(String value) {
            addCriterion("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(String value) {
            addCriterion("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(String value) {
            addCriterion("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLike(String value) {
            addCriterion("END_TIME like", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotLike(String value) {
            addCriterion("END_TIME not like", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<String> values) {
            addCriterion("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<String> values) {
            addCriterion("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(String value1, String value2) {
            addCriterion("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(String value1, String value2) {
            addCriterion("END_TIME not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andTakeDayIsNull() {
            addCriterion("TAKE_DAY is null");
            return (Criteria) this;
        }

        public Criteria andTakeDayIsNotNull() {
            addCriterion("TAKE_DAY is not null");
            return (Criteria) this;
        }

        public Criteria andTakeDayEqualTo(String value) {
            addCriterion("TAKE_DAY =", value, "takeDay");
            return (Criteria) this;
        }

        public Criteria andTakeDayNotEqualTo(String value) {
            addCriterion("TAKE_DAY <>", value, "takeDay");
            return (Criteria) this;
        }

        public Criteria andTakeDayGreaterThan(String value) {
            addCriterion("TAKE_DAY >", value, "takeDay");
            return (Criteria) this;
        }

        public Criteria andTakeDayGreaterThanOrEqualTo(String value) {
            addCriterion("TAKE_DAY >=", value, "takeDay");
            return (Criteria) this;
        }

        public Criteria andTakeDayLessThan(String value) {
            addCriterion("TAKE_DAY <", value, "takeDay");
            return (Criteria) this;
        }

        public Criteria andTakeDayLessThanOrEqualTo(String value) {
            addCriterion("TAKE_DAY <=", value, "takeDay");
            return (Criteria) this;
        }

        public Criteria andTakeDayLike(String value) {
            addCriterion("TAKE_DAY like", value, "takeDay");
            return (Criteria) this;
        }

        public Criteria andTakeDayNotLike(String value) {
            addCriterion("TAKE_DAY not like", value, "takeDay");
            return (Criteria) this;
        }

        public Criteria andTakeDayIn(List<String> values) {
            addCriterion("TAKE_DAY in", values, "takeDay");
            return (Criteria) this;
        }

        public Criteria andTakeDayNotIn(List<String> values) {
            addCriterion("TAKE_DAY not in", values, "takeDay");
            return (Criteria) this;
        }

        public Criteria andTakeDayBetween(String value1, String value2) {
            addCriterion("TAKE_DAY between", value1, value2, "takeDay");
            return (Criteria) this;
        }

        public Criteria andTakeDayNotBetween(String value1, String value2) {
            addCriterion("TAKE_DAY not between", value1, value2, "takeDay");
            return (Criteria) this;
        }

        public Criteria andAcademicContentIsNull() {
            addCriterion("ACADEMIC_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andAcademicContentIsNotNull() {
            addCriterion("ACADEMIC_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andAcademicContentEqualTo(String value) {
            addCriterion("ACADEMIC_CONTENT =", value, "academicContent");
            return (Criteria) this;
        }

        public Criteria andAcademicContentNotEqualTo(String value) {
            addCriterion("ACADEMIC_CONTENT <>", value, "academicContent");
            return (Criteria) this;
        }

        public Criteria andAcademicContentGreaterThan(String value) {
            addCriterion("ACADEMIC_CONTENT >", value, "academicContent");
            return (Criteria) this;
        }

        public Criteria andAcademicContentGreaterThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_CONTENT >=", value, "academicContent");
            return (Criteria) this;
        }

        public Criteria andAcademicContentLessThan(String value) {
            addCriterion("ACADEMIC_CONTENT <", value, "academicContent");
            return (Criteria) this;
        }

        public Criteria andAcademicContentLessThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_CONTENT <=", value, "academicContent");
            return (Criteria) this;
        }

        public Criteria andAcademicContentLike(String value) {
            addCriterion("ACADEMIC_CONTENT like", value, "academicContent");
            return (Criteria) this;
        }

        public Criteria andAcademicContentNotLike(String value) {
            addCriterion("ACADEMIC_CONTENT not like", value, "academicContent");
            return (Criteria) this;
        }

        public Criteria andAcademicContentIn(List<String> values) {
            addCriterion("ACADEMIC_CONTENT in", values, "academicContent");
            return (Criteria) this;
        }

        public Criteria andAcademicContentNotIn(List<String> values) {
            addCriterion("ACADEMIC_CONTENT not in", values, "academicContent");
            return (Criteria) this;
        }

        public Criteria andAcademicContentBetween(String value1, String value2) {
            addCriterion("ACADEMIC_CONTENT between", value1, value2, "academicContent");
            return (Criteria) this;
        }

        public Criteria andAcademicContentNotBetween(String value1, String value2) {
            addCriterion("ACADEMIC_CONTENT not between", value1, value2, "academicContent");
            return (Criteria) this;
        }

        public Criteria andAcademicSummaryIsNull() {
            addCriterion("ACADEMIC_SUMMARY is null");
            return (Criteria) this;
        }

        public Criteria andAcademicSummaryIsNotNull() {
            addCriterion("ACADEMIC_SUMMARY is not null");
            return (Criteria) this;
        }

        public Criteria andAcademicSummaryEqualTo(String value) {
            addCriterion("ACADEMIC_SUMMARY =", value, "academicSummary");
            return (Criteria) this;
        }

        public Criteria andAcademicSummaryNotEqualTo(String value) {
            addCriterion("ACADEMIC_SUMMARY <>", value, "academicSummary");
            return (Criteria) this;
        }

        public Criteria andAcademicSummaryGreaterThan(String value) {
            addCriterion("ACADEMIC_SUMMARY >", value, "academicSummary");
            return (Criteria) this;
        }

        public Criteria andAcademicSummaryGreaterThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_SUMMARY >=", value, "academicSummary");
            return (Criteria) this;
        }

        public Criteria andAcademicSummaryLessThan(String value) {
            addCriterion("ACADEMIC_SUMMARY <", value, "academicSummary");
            return (Criteria) this;
        }

        public Criteria andAcademicSummaryLessThanOrEqualTo(String value) {
            addCriterion("ACADEMIC_SUMMARY <=", value, "academicSummary");
            return (Criteria) this;
        }

        public Criteria andAcademicSummaryLike(String value) {
            addCriterion("ACADEMIC_SUMMARY like", value, "academicSummary");
            return (Criteria) this;
        }

        public Criteria andAcademicSummaryNotLike(String value) {
            addCriterion("ACADEMIC_SUMMARY not like", value, "academicSummary");
            return (Criteria) this;
        }

        public Criteria andAcademicSummaryIn(List<String> values) {
            addCriterion("ACADEMIC_SUMMARY in", values, "academicSummary");
            return (Criteria) this;
        }

        public Criteria andAcademicSummaryNotIn(List<String> values) {
            addCriterion("ACADEMIC_SUMMARY not in", values, "academicSummary");
            return (Criteria) this;
        }

        public Criteria andAcademicSummaryBetween(String value1, String value2) {
            addCriterion("ACADEMIC_SUMMARY between", value1, value2, "academicSummary");
            return (Criteria) this;
        }

        public Criteria andAcademicSummaryNotBetween(String value1, String value2) {
            addCriterion("ACADEMIC_SUMMARY not between", value1, value2, "academicSummary");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowIsNull() {
            addCriterion("APPLY_USER_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowIsNotNull() {
            addCriterion("APPLY_USER_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW =", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW <>", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowGreaterThan(String value) {
            addCriterion("APPLY_USER_FLOW >", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW >=", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowLessThan(String value) {
            addCriterion("APPLY_USER_FLOW <", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowLessThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_FLOW <=", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowLike(String value) {
            addCriterion("APPLY_USER_FLOW like", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotLike(String value) {
            addCriterion("APPLY_USER_FLOW not like", value, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowIn(List<String> values) {
            addCriterion("APPLY_USER_FLOW in", values, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotIn(List<String> values) {
            addCriterion("APPLY_USER_FLOW not in", values, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowBetween(String value1, String value2) {
            addCriterion("APPLY_USER_FLOW between", value1, value2, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserFlowNotBetween(String value1, String value2) {
            addCriterion("APPLY_USER_FLOW not between", value1, value2, "applyUserFlow");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameIsNull() {
            addCriterion("APPLY_USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameIsNotNull() {
            addCriterion("APPLY_USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameEqualTo(String value) {
            addCriterion("APPLY_USER_NAME =", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotEqualTo(String value) {
            addCriterion("APPLY_USER_NAME <>", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameGreaterThan(String value) {
            addCriterion("APPLY_USER_NAME >", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_NAME >=", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameLessThan(String value) {
            addCriterion("APPLY_USER_NAME <", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameLessThanOrEqualTo(String value) {
            addCriterion("APPLY_USER_NAME <=", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameLike(String value) {
            addCriterion("APPLY_USER_NAME like", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotLike(String value) {
            addCriterion("APPLY_USER_NAME not like", value, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameIn(List<String> values) {
            addCriterion("APPLY_USER_NAME in", values, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotIn(List<String> values) {
            addCriterion("APPLY_USER_NAME not in", values, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameBetween(String value1, String value2) {
            addCriterion("APPLY_USER_NAME between", value1, value2, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyUserNameNotBetween(String value1, String value2) {
            addCriterion("APPLY_USER_NAME not between", value1, value2, "applyUserName");
            return (Criteria) this;
        }

        public Criteria andApplyTimeIsNull() {
            addCriterion("APPLY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andApplyTimeIsNotNull() {
            addCriterion("APPLY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyTimeEqualTo(String value) {
            addCriterion("APPLY_TIME =", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotEqualTo(String value) {
            addCriterion("APPLY_TIME <>", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeGreaterThan(String value) {
            addCriterion("APPLY_TIME >", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_TIME >=", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeLessThan(String value) {
            addCriterion("APPLY_TIME <", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeLessThanOrEqualTo(String value) {
            addCriterion("APPLY_TIME <=", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeLike(String value) {
            addCriterion("APPLY_TIME like", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotLike(String value) {
            addCriterion("APPLY_TIME not like", value, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeIn(List<String> values) {
            addCriterion("APPLY_TIME in", values, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotIn(List<String> values) {
            addCriterion("APPLY_TIME not in", values, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeBetween(String value1, String value2) {
            addCriterion("APPLY_TIME between", value1, value2, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyTimeNotBetween(String value1, String value2) {
            addCriterion("APPLY_TIME not between", value1, value2, "applyTime");
            return (Criteria) this;
        }

        public Criteria andApplyYearIsNull() {
            addCriterion("APPLY_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andApplyYearIsNotNull() {
            addCriterion("APPLY_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andApplyYearEqualTo(String value) {
            addCriterion("APPLY_YEAR =", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearNotEqualTo(String value) {
            addCriterion("APPLY_YEAR <>", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearGreaterThan(String value) {
            addCriterion("APPLY_YEAR >", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_YEAR >=", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearLessThan(String value) {
            addCriterion("APPLY_YEAR <", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearLessThanOrEqualTo(String value) {
            addCriterion("APPLY_YEAR <=", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearLike(String value) {
            addCriterion("APPLY_YEAR like", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearNotLike(String value) {
            addCriterion("APPLY_YEAR not like", value, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearIn(List<String> values) {
            addCriterion("APPLY_YEAR in", values, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearNotIn(List<String> values) {
            addCriterion("APPLY_YEAR not in", values, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearBetween(String value1, String value2) {
            addCriterion("APPLY_YEAR between", value1, value2, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyYearNotBetween(String value1, String value2) {
            addCriterion("APPLY_YEAR not between", value1, value2, "applyYear");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowIsNull() {
            addCriterion("APPLY_ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowIsNotNull() {
            addCriterion("APPLY_ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowEqualTo(String value) {
            addCriterion("APPLY_ORG_FLOW =", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowNotEqualTo(String value) {
            addCriterion("APPLY_ORG_FLOW <>", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowGreaterThan(String value) {
            addCriterion("APPLY_ORG_FLOW >", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_ORG_FLOW >=", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowLessThan(String value) {
            addCriterion("APPLY_ORG_FLOW <", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("APPLY_ORG_FLOW <=", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowLike(String value) {
            addCriterion("APPLY_ORG_FLOW like", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowNotLike(String value) {
            addCriterion("APPLY_ORG_FLOW not like", value, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowIn(List<String> values) {
            addCriterion("APPLY_ORG_FLOW in", values, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowNotIn(List<String> values) {
            addCriterion("APPLY_ORG_FLOW not in", values, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowBetween(String value1, String value2) {
            addCriterion("APPLY_ORG_FLOW between", value1, value2, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgFlowNotBetween(String value1, String value2) {
            addCriterion("APPLY_ORG_FLOW not between", value1, value2, "applyOrgFlow");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameIsNull() {
            addCriterion("APPLY_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameIsNotNull() {
            addCriterion("APPLY_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameEqualTo(String value) {
            addCriterion("APPLY_ORG_NAME =", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameNotEqualTo(String value) {
            addCriterion("APPLY_ORG_NAME <>", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameGreaterThan(String value) {
            addCriterion("APPLY_ORG_NAME >", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_ORG_NAME >=", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameLessThan(String value) {
            addCriterion("APPLY_ORG_NAME <", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameLessThanOrEqualTo(String value) {
            addCriterion("APPLY_ORG_NAME <=", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameLike(String value) {
            addCriterion("APPLY_ORG_NAME like", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameNotLike(String value) {
            addCriterion("APPLY_ORG_NAME not like", value, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameIn(List<String> values) {
            addCriterion("APPLY_ORG_NAME in", values, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameNotIn(List<String> values) {
            addCriterion("APPLY_ORG_NAME not in", values, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameBetween(String value1, String value2) {
            addCriterion("APPLY_ORG_NAME between", value1, value2, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyOrgNameNotBetween(String value1, String value2) {
            addCriterion("APPLY_ORG_NAME not between", value1, value2, "applyOrgName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowIsNull() {
            addCriterion("APPLY_DEPT_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowIsNotNull() {
            addCriterion("APPLY_DEPT_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowEqualTo(String value) {
            addCriterion("APPLY_DEPT_FLOW =", value, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowNotEqualTo(String value) {
            addCriterion("APPLY_DEPT_FLOW <>", value, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowGreaterThan(String value) {
            addCriterion("APPLY_DEPT_FLOW >", value, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_DEPT_FLOW >=", value, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowLessThan(String value) {
            addCriterion("APPLY_DEPT_FLOW <", value, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowLessThanOrEqualTo(String value) {
            addCriterion("APPLY_DEPT_FLOW <=", value, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowLike(String value) {
            addCriterion("APPLY_DEPT_FLOW like", value, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowNotLike(String value) {
            addCriterion("APPLY_DEPT_FLOW not like", value, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowIn(List<String> values) {
            addCriterion("APPLY_DEPT_FLOW in", values, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowNotIn(List<String> values) {
            addCriterion("APPLY_DEPT_FLOW not in", values, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowBetween(String value1, String value2) {
            addCriterion("APPLY_DEPT_FLOW between", value1, value2, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptFlowNotBetween(String value1, String value2) {
            addCriterion("APPLY_DEPT_FLOW not between", value1, value2, "applyDeptFlow");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameIsNull() {
            addCriterion("APPLY_DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameIsNotNull() {
            addCriterion("APPLY_DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameEqualTo(String value) {
            addCriterion("APPLY_DEPT_NAME =", value, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameNotEqualTo(String value) {
            addCriterion("APPLY_DEPT_NAME <>", value, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameGreaterThan(String value) {
            addCriterion("APPLY_DEPT_NAME >", value, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_DEPT_NAME >=", value, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameLessThan(String value) {
            addCriterion("APPLY_DEPT_NAME <", value, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameLessThanOrEqualTo(String value) {
            addCriterion("APPLY_DEPT_NAME <=", value, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameLike(String value) {
            addCriterion("APPLY_DEPT_NAME like", value, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameNotLike(String value) {
            addCriterion("APPLY_DEPT_NAME not like", value, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameIn(List<String> values) {
            addCriterion("APPLY_DEPT_NAME in", values, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameNotIn(List<String> values) {
            addCriterion("APPLY_DEPT_NAME not in", values, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameBetween(String value1, String value2) {
            addCriterion("APPLY_DEPT_NAME between", value1, value2, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andApplyDeptNameNotBetween(String value1, String value2) {
            addCriterion("APPLY_DEPT_NAME not between", value1, value2, "applyDeptName");
            return (Criteria) this;
        }

        public Criteria andHoldUnitIsNull() {
            addCriterion("HOLD_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andHoldUnitIsNotNull() {
            addCriterion("HOLD_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andHoldUnitEqualTo(String value) {
            addCriterion("HOLD_UNIT =", value, "holdUnit");
            return (Criteria) this;
        }

        public Criteria andHoldUnitNotEqualTo(String value) {
            addCriterion("HOLD_UNIT <>", value, "holdUnit");
            return (Criteria) this;
        }

        public Criteria andHoldUnitGreaterThan(String value) {
            addCriterion("HOLD_UNIT >", value, "holdUnit");
            return (Criteria) this;
        }

        public Criteria andHoldUnitGreaterThanOrEqualTo(String value) {
            addCriterion("HOLD_UNIT >=", value, "holdUnit");
            return (Criteria) this;
        }

        public Criteria andHoldUnitLessThan(String value) {
            addCriterion("HOLD_UNIT <", value, "holdUnit");
            return (Criteria) this;
        }

        public Criteria andHoldUnitLessThanOrEqualTo(String value) {
            addCriterion("HOLD_UNIT <=", value, "holdUnit");
            return (Criteria) this;
        }

        public Criteria andHoldUnitLike(String value) {
            addCriterion("HOLD_UNIT like", value, "holdUnit");
            return (Criteria) this;
        }

        public Criteria andHoldUnitNotLike(String value) {
            addCriterion("HOLD_UNIT not like", value, "holdUnit");
            return (Criteria) this;
        }

        public Criteria andHoldUnitIn(List<String> values) {
            addCriterion("HOLD_UNIT in", values, "holdUnit");
            return (Criteria) this;
        }

        public Criteria andHoldUnitNotIn(List<String> values) {
            addCriterion("HOLD_UNIT not in", values, "holdUnit");
            return (Criteria) this;
        }

        public Criteria andHoldUnitBetween(String value1, String value2) {
            addCriterion("HOLD_UNIT between", value1, value2, "holdUnit");
            return (Criteria) this;
        }

        public Criteria andHoldUnitNotBetween(String value1, String value2) {
            addCriterion("HOLD_UNIT not between", value1, value2, "holdUnit");
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

        public Criteria andMeetingBudgetIsNull() {
            addCriterion("MEETING_BUDGET is null");
            return (Criteria) this;
        }

        public Criteria andMeetingBudgetIsNotNull() {
            addCriterion("MEETING_BUDGET is not null");
            return (Criteria) this;
        }

        public Criteria andMeetingBudgetEqualTo(String value) {
            addCriterion("MEETING_BUDGET =", value, "meetingBudget");
            return (Criteria) this;
        }

        public Criteria andMeetingBudgetNotEqualTo(String value) {
            addCriterion("MEETING_BUDGET <>", value, "meetingBudget");
            return (Criteria) this;
        }

        public Criteria andMeetingBudgetGreaterThan(String value) {
            addCriterion("MEETING_BUDGET >", value, "meetingBudget");
            return (Criteria) this;
        }

        public Criteria andMeetingBudgetGreaterThanOrEqualTo(String value) {
            addCriterion("MEETING_BUDGET >=", value, "meetingBudget");
            return (Criteria) this;
        }

        public Criteria andMeetingBudgetLessThan(String value) {
            addCriterion("MEETING_BUDGET <", value, "meetingBudget");
            return (Criteria) this;
        }

        public Criteria andMeetingBudgetLessThanOrEqualTo(String value) {
            addCriterion("MEETING_BUDGET <=", value, "meetingBudget");
            return (Criteria) this;
        }

        public Criteria andMeetingBudgetLike(String value) {
            addCriterion("MEETING_BUDGET like", value, "meetingBudget");
            return (Criteria) this;
        }

        public Criteria andMeetingBudgetNotLike(String value) {
            addCriterion("MEETING_BUDGET not like", value, "meetingBudget");
            return (Criteria) this;
        }

        public Criteria andMeetingBudgetIn(List<String> values) {
            addCriterion("MEETING_BUDGET in", values, "meetingBudget");
            return (Criteria) this;
        }

        public Criteria andMeetingBudgetNotIn(List<String> values) {
            addCriterion("MEETING_BUDGET not in", values, "meetingBudget");
            return (Criteria) this;
        }

        public Criteria andMeetingBudgetBetween(String value1, String value2) {
            addCriterion("MEETING_BUDGET between", value1, value2, "meetingBudget");
            return (Criteria) this;
        }

        public Criteria andMeetingBudgetNotBetween(String value1, String value2) {
            addCriterion("MEETING_BUDGET not between", value1, value2, "meetingBudget");
            return (Criteria) this;
        }

        public Criteria andMaterialBudgetIsNull() {
            addCriterion("MATERIAL_BUDGET is null");
            return (Criteria) this;
        }

        public Criteria andMaterialBudgetIsNotNull() {
            addCriterion("MATERIAL_BUDGET is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialBudgetEqualTo(String value) {
            addCriterion("MATERIAL_BUDGET =", value, "materialBudget");
            return (Criteria) this;
        }

        public Criteria andMaterialBudgetNotEqualTo(String value) {
            addCriterion("MATERIAL_BUDGET <>", value, "materialBudget");
            return (Criteria) this;
        }

        public Criteria andMaterialBudgetGreaterThan(String value) {
            addCriterion("MATERIAL_BUDGET >", value, "materialBudget");
            return (Criteria) this;
        }

        public Criteria andMaterialBudgetGreaterThanOrEqualTo(String value) {
            addCriterion("MATERIAL_BUDGET >=", value, "materialBudget");
            return (Criteria) this;
        }

        public Criteria andMaterialBudgetLessThan(String value) {
            addCriterion("MATERIAL_BUDGET <", value, "materialBudget");
            return (Criteria) this;
        }

        public Criteria andMaterialBudgetLessThanOrEqualTo(String value) {
            addCriterion("MATERIAL_BUDGET <=", value, "materialBudget");
            return (Criteria) this;
        }

        public Criteria andMaterialBudgetLike(String value) {
            addCriterion("MATERIAL_BUDGET like", value, "materialBudget");
            return (Criteria) this;
        }

        public Criteria andMaterialBudgetNotLike(String value) {
            addCriterion("MATERIAL_BUDGET not like", value, "materialBudget");
            return (Criteria) this;
        }

        public Criteria andMaterialBudgetIn(List<String> values) {
            addCriterion("MATERIAL_BUDGET in", values, "materialBudget");
            return (Criteria) this;
        }

        public Criteria andMaterialBudgetNotIn(List<String> values) {
            addCriterion("MATERIAL_BUDGET not in", values, "materialBudget");
            return (Criteria) this;
        }

        public Criteria andMaterialBudgetBetween(String value1, String value2) {
            addCriterion("MATERIAL_BUDGET between", value1, value2, "materialBudget");
            return (Criteria) this;
        }

        public Criteria andMaterialBudgetNotBetween(String value1, String value2) {
            addCriterion("MATERIAL_BUDGET not between", value1, value2, "materialBudget");
            return (Criteria) this;
        }

        public Criteria andTrafficBudgetIsNull() {
            addCriterion("TRAFFIC_BUDGET is null");
            return (Criteria) this;
        }

        public Criteria andTrafficBudgetIsNotNull() {
            addCriterion("TRAFFIC_BUDGET is not null");
            return (Criteria) this;
        }

        public Criteria andTrafficBudgetEqualTo(String value) {
            addCriterion("TRAFFIC_BUDGET =", value, "trafficBudget");
            return (Criteria) this;
        }

        public Criteria andTrafficBudgetNotEqualTo(String value) {
            addCriterion("TRAFFIC_BUDGET <>", value, "trafficBudget");
            return (Criteria) this;
        }

        public Criteria andTrafficBudgetGreaterThan(String value) {
            addCriterion("TRAFFIC_BUDGET >", value, "trafficBudget");
            return (Criteria) this;
        }

        public Criteria andTrafficBudgetGreaterThanOrEqualTo(String value) {
            addCriterion("TRAFFIC_BUDGET >=", value, "trafficBudget");
            return (Criteria) this;
        }

        public Criteria andTrafficBudgetLessThan(String value) {
            addCriterion("TRAFFIC_BUDGET <", value, "trafficBudget");
            return (Criteria) this;
        }

        public Criteria andTrafficBudgetLessThanOrEqualTo(String value) {
            addCriterion("TRAFFIC_BUDGET <=", value, "trafficBudget");
            return (Criteria) this;
        }

        public Criteria andTrafficBudgetLike(String value) {
            addCriterion("TRAFFIC_BUDGET like", value, "trafficBudget");
            return (Criteria) this;
        }

        public Criteria andTrafficBudgetNotLike(String value) {
            addCriterion("TRAFFIC_BUDGET not like", value, "trafficBudget");
            return (Criteria) this;
        }

        public Criteria andTrafficBudgetIn(List<String> values) {
            addCriterion("TRAFFIC_BUDGET in", values, "trafficBudget");
            return (Criteria) this;
        }

        public Criteria andTrafficBudgetNotIn(List<String> values) {
            addCriterion("TRAFFIC_BUDGET not in", values, "trafficBudget");
            return (Criteria) this;
        }

        public Criteria andTrafficBudgetBetween(String value1, String value2) {
            addCriterion("TRAFFIC_BUDGET between", value1, value2, "trafficBudget");
            return (Criteria) this;
        }

        public Criteria andTrafficBudgetNotBetween(String value1, String value2) {
            addCriterion("TRAFFIC_BUDGET not between", value1, value2, "trafficBudget");
            return (Criteria) this;
        }

        public Criteria andCostumeBudgetIsNull() {
            addCriterion("COSTUME_BUDGET is null");
            return (Criteria) this;
        }

        public Criteria andCostumeBudgetIsNotNull() {
            addCriterion("COSTUME_BUDGET is not null");
            return (Criteria) this;
        }

        public Criteria andCostumeBudgetEqualTo(String value) {
            addCriterion("COSTUME_BUDGET =", value, "costumeBudget");
            return (Criteria) this;
        }

        public Criteria andCostumeBudgetNotEqualTo(String value) {
            addCriterion("COSTUME_BUDGET <>", value, "costumeBudget");
            return (Criteria) this;
        }

        public Criteria andCostumeBudgetGreaterThan(String value) {
            addCriterion("COSTUME_BUDGET >", value, "costumeBudget");
            return (Criteria) this;
        }

        public Criteria andCostumeBudgetGreaterThanOrEqualTo(String value) {
            addCriterion("COSTUME_BUDGET >=", value, "costumeBudget");
            return (Criteria) this;
        }

        public Criteria andCostumeBudgetLessThan(String value) {
            addCriterion("COSTUME_BUDGET <", value, "costumeBudget");
            return (Criteria) this;
        }

        public Criteria andCostumeBudgetLessThanOrEqualTo(String value) {
            addCriterion("COSTUME_BUDGET <=", value, "costumeBudget");
            return (Criteria) this;
        }

        public Criteria andCostumeBudgetLike(String value) {
            addCriterion("COSTUME_BUDGET like", value, "costumeBudget");
            return (Criteria) this;
        }

        public Criteria andCostumeBudgetNotLike(String value) {
            addCriterion("COSTUME_BUDGET not like", value, "costumeBudget");
            return (Criteria) this;
        }

        public Criteria andCostumeBudgetIn(List<String> values) {
            addCriterion("COSTUME_BUDGET in", values, "costumeBudget");
            return (Criteria) this;
        }

        public Criteria andCostumeBudgetNotIn(List<String> values) {
            addCriterion("COSTUME_BUDGET not in", values, "costumeBudget");
            return (Criteria) this;
        }

        public Criteria andCostumeBudgetBetween(String value1, String value2) {
            addCriterion("COSTUME_BUDGET between", value1, value2, "costumeBudget");
            return (Criteria) this;
        }

        public Criteria andCostumeBudgetNotBetween(String value1, String value2) {
            addCriterion("COSTUME_BUDGET not between", value1, value2, "costumeBudget");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetWhetherIsNull() {
            addCriterion("FOOD_BUDGET_WHETHER is null");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetWhetherIsNotNull() {
            addCriterion("FOOD_BUDGET_WHETHER is not null");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetWhetherEqualTo(String value) {
            addCriterion("FOOD_BUDGET_WHETHER =", value, "foodBudgetWhether");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetWhetherNotEqualTo(String value) {
            addCriterion("FOOD_BUDGET_WHETHER <>", value, "foodBudgetWhether");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetWhetherGreaterThan(String value) {
            addCriterion("FOOD_BUDGET_WHETHER >", value, "foodBudgetWhether");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetWhetherGreaterThanOrEqualTo(String value) {
            addCriterion("FOOD_BUDGET_WHETHER >=", value, "foodBudgetWhether");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetWhetherLessThan(String value) {
            addCriterion("FOOD_BUDGET_WHETHER <", value, "foodBudgetWhether");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetWhetherLessThanOrEqualTo(String value) {
            addCriterion("FOOD_BUDGET_WHETHER <=", value, "foodBudgetWhether");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetWhetherLike(String value) {
            addCriterion("FOOD_BUDGET_WHETHER like", value, "foodBudgetWhether");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetWhetherNotLike(String value) {
            addCriterion("FOOD_BUDGET_WHETHER not like", value, "foodBudgetWhether");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetWhetherIn(List<String> values) {
            addCriterion("FOOD_BUDGET_WHETHER in", values, "foodBudgetWhether");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetWhetherNotIn(List<String> values) {
            addCriterion("FOOD_BUDGET_WHETHER not in", values, "foodBudgetWhether");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetWhetherBetween(String value1, String value2) {
            addCriterion("FOOD_BUDGET_WHETHER between", value1, value2, "foodBudgetWhether");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetWhetherNotBetween(String value1, String value2) {
            addCriterion("FOOD_BUDGET_WHETHER not between", value1, value2, "foodBudgetWhether");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetIsNull() {
            addCriterion("FOOD_BUDGET is null");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetIsNotNull() {
            addCriterion("FOOD_BUDGET is not null");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetEqualTo(String value) {
            addCriterion("FOOD_BUDGET =", value, "foodBudget");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetNotEqualTo(String value) {
            addCriterion("FOOD_BUDGET <>", value, "foodBudget");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetGreaterThan(String value) {
            addCriterion("FOOD_BUDGET >", value, "foodBudget");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetGreaterThanOrEqualTo(String value) {
            addCriterion("FOOD_BUDGET >=", value, "foodBudget");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetLessThan(String value) {
            addCriterion("FOOD_BUDGET <", value, "foodBudget");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetLessThanOrEqualTo(String value) {
            addCriterion("FOOD_BUDGET <=", value, "foodBudget");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetLike(String value) {
            addCriterion("FOOD_BUDGET like", value, "foodBudget");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetNotLike(String value) {
            addCriterion("FOOD_BUDGET not like", value, "foodBudget");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetIn(List<String> values) {
            addCriterion("FOOD_BUDGET in", values, "foodBudget");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetNotIn(List<String> values) {
            addCriterion("FOOD_BUDGET not in", values, "foodBudget");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetBetween(String value1, String value2) {
            addCriterion("FOOD_BUDGET between", value1, value2, "foodBudget");
            return (Criteria) this;
        }

        public Criteria andFoodBudgetNotBetween(String value1, String value2) {
            addCriterion("FOOD_BUDGET not between", value1, value2, "foodBudget");
            return (Criteria) this;
        }

        public Criteria andSubsidyBudgetIsNull() {
            addCriterion("SUBSIDY_BUDGET is null");
            return (Criteria) this;
        }

        public Criteria andSubsidyBudgetIsNotNull() {
            addCriterion("SUBSIDY_BUDGET is not null");
            return (Criteria) this;
        }

        public Criteria andSubsidyBudgetEqualTo(String value) {
            addCriterion("SUBSIDY_BUDGET =", value, "subsidyBudget");
            return (Criteria) this;
        }

        public Criteria andSubsidyBudgetNotEqualTo(String value) {
            addCriterion("SUBSIDY_BUDGET <>", value, "subsidyBudget");
            return (Criteria) this;
        }

        public Criteria andSubsidyBudgetGreaterThan(String value) {
            addCriterion("SUBSIDY_BUDGET >", value, "subsidyBudget");
            return (Criteria) this;
        }

        public Criteria andSubsidyBudgetGreaterThanOrEqualTo(String value) {
            addCriterion("SUBSIDY_BUDGET >=", value, "subsidyBudget");
            return (Criteria) this;
        }

        public Criteria andSubsidyBudgetLessThan(String value) {
            addCriterion("SUBSIDY_BUDGET <", value, "subsidyBudget");
            return (Criteria) this;
        }

        public Criteria andSubsidyBudgetLessThanOrEqualTo(String value) {
            addCriterion("SUBSIDY_BUDGET <=", value, "subsidyBudget");
            return (Criteria) this;
        }

        public Criteria andSubsidyBudgetLike(String value) {
            addCriterion("SUBSIDY_BUDGET like", value, "subsidyBudget");
            return (Criteria) this;
        }

        public Criteria andSubsidyBudgetNotLike(String value) {
            addCriterion("SUBSIDY_BUDGET not like", value, "subsidyBudget");
            return (Criteria) this;
        }

        public Criteria andSubsidyBudgetIn(List<String> values) {
            addCriterion("SUBSIDY_BUDGET in", values, "subsidyBudget");
            return (Criteria) this;
        }

        public Criteria andSubsidyBudgetNotIn(List<String> values) {
            addCriterion("SUBSIDY_BUDGET not in", values, "subsidyBudget");
            return (Criteria) this;
        }

        public Criteria andSubsidyBudgetBetween(String value1, String value2) {
            addCriterion("SUBSIDY_BUDGET between", value1, value2, "subsidyBudget");
            return (Criteria) this;
        }

        public Criteria andSubsidyBudgetNotBetween(String value1, String value2) {
            addCriterion("SUBSIDY_BUDGET not between", value1, value2, "subsidyBudget");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetIsNull() {
            addCriterion("OTHER_BUDGET is null");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetIsNotNull() {
            addCriterion("OTHER_BUDGET is not null");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetEqualTo(String value) {
            addCriterion("OTHER_BUDGET =", value, "otherBudget");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetNotEqualTo(String value) {
            addCriterion("OTHER_BUDGET <>", value, "otherBudget");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetGreaterThan(String value) {
            addCriterion("OTHER_BUDGET >", value, "otherBudget");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetGreaterThanOrEqualTo(String value) {
            addCriterion("OTHER_BUDGET >=", value, "otherBudget");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetLessThan(String value) {
            addCriterion("OTHER_BUDGET <", value, "otherBudget");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetLessThanOrEqualTo(String value) {
            addCriterion("OTHER_BUDGET <=", value, "otherBudget");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetLike(String value) {
            addCriterion("OTHER_BUDGET like", value, "otherBudget");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetNotLike(String value) {
            addCriterion("OTHER_BUDGET not like", value, "otherBudget");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetIn(List<String> values) {
            addCriterion("OTHER_BUDGET in", values, "otherBudget");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetNotIn(List<String> values) {
            addCriterion("OTHER_BUDGET not in", values, "otherBudget");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetBetween(String value1, String value2) {
            addCriterion("OTHER_BUDGET between", value1, value2, "otherBudget");
            return (Criteria) this;
        }

        public Criteria andOtherBudgetNotBetween(String value1, String value2) {
            addCriterion("OTHER_BUDGET not between", value1, value2, "otherBudget");
            return (Criteria) this;
        }

        public Criteria andSumBudgetIsNull() {
            addCriterion("SUM_BUDGET is null");
            return (Criteria) this;
        }

        public Criteria andSumBudgetIsNotNull() {
            addCriterion("SUM_BUDGET is not null");
            return (Criteria) this;
        }

        public Criteria andSumBudgetEqualTo(String value) {
            addCriterion("SUM_BUDGET =", value, "sumBudget");
            return (Criteria) this;
        }

        public Criteria andSumBudgetNotEqualTo(String value) {
            addCriterion("SUM_BUDGET <>", value, "sumBudget");
            return (Criteria) this;
        }

        public Criteria andSumBudgetGreaterThan(String value) {
            addCriterion("SUM_BUDGET >", value, "sumBudget");
            return (Criteria) this;
        }

        public Criteria andSumBudgetGreaterThanOrEqualTo(String value) {
            addCriterion("SUM_BUDGET >=", value, "sumBudget");
            return (Criteria) this;
        }

        public Criteria andSumBudgetLessThan(String value) {
            addCriterion("SUM_BUDGET <", value, "sumBudget");
            return (Criteria) this;
        }

        public Criteria andSumBudgetLessThanOrEqualTo(String value) {
            addCriterion("SUM_BUDGET <=", value, "sumBudget");
            return (Criteria) this;
        }

        public Criteria andSumBudgetLike(String value) {
            addCriterion("SUM_BUDGET like", value, "sumBudget");
            return (Criteria) this;
        }

        public Criteria andSumBudgetNotLike(String value) {
            addCriterion("SUM_BUDGET not like", value, "sumBudget");
            return (Criteria) this;
        }

        public Criteria andSumBudgetIn(List<String> values) {
            addCriterion("SUM_BUDGET in", values, "sumBudget");
            return (Criteria) this;
        }

        public Criteria andSumBudgetNotIn(List<String> values) {
            addCriterion("SUM_BUDGET not in", values, "sumBudget");
            return (Criteria) this;
        }

        public Criteria andSumBudgetBetween(String value1, String value2) {
            addCriterion("SUM_BUDGET between", value1, value2, "sumBudget");
            return (Criteria) this;
        }

        public Criteria andSumBudgetNotBetween(String value1, String value2) {
            addCriterion("SUM_BUDGET not between", value1, value2, "sumBudget");
            return (Criteria) this;
        }

        public Criteria andMeetingFeeIsNull() {
            addCriterion("MEETING_FEE is null");
            return (Criteria) this;
        }

        public Criteria andMeetingFeeIsNotNull() {
            addCriterion("MEETING_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andMeetingFeeEqualTo(String value) {
            addCriterion("MEETING_FEE =", value, "meetingFee");
            return (Criteria) this;
        }

        public Criteria andMeetingFeeNotEqualTo(String value) {
            addCriterion("MEETING_FEE <>", value, "meetingFee");
            return (Criteria) this;
        }

        public Criteria andMeetingFeeGreaterThan(String value) {
            addCriterion("MEETING_FEE >", value, "meetingFee");
            return (Criteria) this;
        }

        public Criteria andMeetingFeeGreaterThanOrEqualTo(String value) {
            addCriterion("MEETING_FEE >=", value, "meetingFee");
            return (Criteria) this;
        }

        public Criteria andMeetingFeeLessThan(String value) {
            addCriterion("MEETING_FEE <", value, "meetingFee");
            return (Criteria) this;
        }

        public Criteria andMeetingFeeLessThanOrEqualTo(String value) {
            addCriterion("MEETING_FEE <=", value, "meetingFee");
            return (Criteria) this;
        }

        public Criteria andMeetingFeeLike(String value) {
            addCriterion("MEETING_FEE like", value, "meetingFee");
            return (Criteria) this;
        }

        public Criteria andMeetingFeeNotLike(String value) {
            addCriterion("MEETING_FEE not like", value, "meetingFee");
            return (Criteria) this;
        }

        public Criteria andMeetingFeeIn(List<String> values) {
            addCriterion("MEETING_FEE in", values, "meetingFee");
            return (Criteria) this;
        }

        public Criteria andMeetingFeeNotIn(List<String> values) {
            addCriterion("MEETING_FEE not in", values, "meetingFee");
            return (Criteria) this;
        }

        public Criteria andMeetingFeeBetween(String value1, String value2) {
            addCriterion("MEETING_FEE between", value1, value2, "meetingFee");
            return (Criteria) this;
        }

        public Criteria andMeetingFeeNotBetween(String value1, String value2) {
            addCriterion("MEETING_FEE not between", value1, value2, "meetingFee");
            return (Criteria) this;
        }

        public Criteria andMaterialFeeIsNull() {
            addCriterion("MATERIAL_FEE is null");
            return (Criteria) this;
        }

        public Criteria andMaterialFeeIsNotNull() {
            addCriterion("MATERIAL_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andMaterialFeeEqualTo(String value) {
            addCriterion("MATERIAL_FEE =", value, "materialFee");
            return (Criteria) this;
        }

        public Criteria andMaterialFeeNotEqualTo(String value) {
            addCriterion("MATERIAL_FEE <>", value, "materialFee");
            return (Criteria) this;
        }

        public Criteria andMaterialFeeGreaterThan(String value) {
            addCriterion("MATERIAL_FEE >", value, "materialFee");
            return (Criteria) this;
        }

        public Criteria andMaterialFeeGreaterThanOrEqualTo(String value) {
            addCriterion("MATERIAL_FEE >=", value, "materialFee");
            return (Criteria) this;
        }

        public Criteria andMaterialFeeLessThan(String value) {
            addCriterion("MATERIAL_FEE <", value, "materialFee");
            return (Criteria) this;
        }

        public Criteria andMaterialFeeLessThanOrEqualTo(String value) {
            addCriterion("MATERIAL_FEE <=", value, "materialFee");
            return (Criteria) this;
        }

        public Criteria andMaterialFeeLike(String value) {
            addCriterion("MATERIAL_FEE like", value, "materialFee");
            return (Criteria) this;
        }

        public Criteria andMaterialFeeNotLike(String value) {
            addCriterion("MATERIAL_FEE not like", value, "materialFee");
            return (Criteria) this;
        }

        public Criteria andMaterialFeeIn(List<String> values) {
            addCriterion("MATERIAL_FEE in", values, "materialFee");
            return (Criteria) this;
        }

        public Criteria andMaterialFeeNotIn(List<String> values) {
            addCriterion("MATERIAL_FEE not in", values, "materialFee");
            return (Criteria) this;
        }

        public Criteria andMaterialFeeBetween(String value1, String value2) {
            addCriterion("MATERIAL_FEE between", value1, value2, "materialFee");
            return (Criteria) this;
        }

        public Criteria andMaterialFeeNotBetween(String value1, String value2) {
            addCriterion("MATERIAL_FEE not between", value1, value2, "materialFee");
            return (Criteria) this;
        }

        public Criteria andTrafficFeeIsNull() {
            addCriterion("TRAFFIC_FEE is null");
            return (Criteria) this;
        }

        public Criteria andTrafficFeeIsNotNull() {
            addCriterion("TRAFFIC_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andTrafficFeeEqualTo(String value) {
            addCriterion("TRAFFIC_FEE =", value, "trafficFee");
            return (Criteria) this;
        }

        public Criteria andTrafficFeeNotEqualTo(String value) {
            addCriterion("TRAFFIC_FEE <>", value, "trafficFee");
            return (Criteria) this;
        }

        public Criteria andTrafficFeeGreaterThan(String value) {
            addCriterion("TRAFFIC_FEE >", value, "trafficFee");
            return (Criteria) this;
        }

        public Criteria andTrafficFeeGreaterThanOrEqualTo(String value) {
            addCriterion("TRAFFIC_FEE >=", value, "trafficFee");
            return (Criteria) this;
        }

        public Criteria andTrafficFeeLessThan(String value) {
            addCriterion("TRAFFIC_FEE <", value, "trafficFee");
            return (Criteria) this;
        }

        public Criteria andTrafficFeeLessThanOrEqualTo(String value) {
            addCriterion("TRAFFIC_FEE <=", value, "trafficFee");
            return (Criteria) this;
        }

        public Criteria andTrafficFeeLike(String value) {
            addCriterion("TRAFFIC_FEE like", value, "trafficFee");
            return (Criteria) this;
        }

        public Criteria andTrafficFeeNotLike(String value) {
            addCriterion("TRAFFIC_FEE not like", value, "trafficFee");
            return (Criteria) this;
        }

        public Criteria andTrafficFeeIn(List<String> values) {
            addCriterion("TRAFFIC_FEE in", values, "trafficFee");
            return (Criteria) this;
        }

        public Criteria andTrafficFeeNotIn(List<String> values) {
            addCriterion("TRAFFIC_FEE not in", values, "trafficFee");
            return (Criteria) this;
        }

        public Criteria andTrafficFeeBetween(String value1, String value2) {
            addCriterion("TRAFFIC_FEE between", value1, value2, "trafficFee");
            return (Criteria) this;
        }

        public Criteria andTrafficFeeNotBetween(String value1, String value2) {
            addCriterion("TRAFFIC_FEE not between", value1, value2, "trafficFee");
            return (Criteria) this;
        }

        public Criteria andCostumeFeeIsNull() {
            addCriterion("COSTUME_FEE is null");
            return (Criteria) this;
        }

        public Criteria andCostumeFeeIsNotNull() {
            addCriterion("COSTUME_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andCostumeFeeEqualTo(String value) {
            addCriterion("COSTUME_FEE =", value, "costumeFee");
            return (Criteria) this;
        }

        public Criteria andCostumeFeeNotEqualTo(String value) {
            addCriterion("COSTUME_FEE <>", value, "costumeFee");
            return (Criteria) this;
        }

        public Criteria andCostumeFeeGreaterThan(String value) {
            addCriterion("COSTUME_FEE >", value, "costumeFee");
            return (Criteria) this;
        }

        public Criteria andCostumeFeeGreaterThanOrEqualTo(String value) {
            addCriterion("COSTUME_FEE >=", value, "costumeFee");
            return (Criteria) this;
        }

        public Criteria andCostumeFeeLessThan(String value) {
            addCriterion("COSTUME_FEE <", value, "costumeFee");
            return (Criteria) this;
        }

        public Criteria andCostumeFeeLessThanOrEqualTo(String value) {
            addCriterion("COSTUME_FEE <=", value, "costumeFee");
            return (Criteria) this;
        }

        public Criteria andCostumeFeeLike(String value) {
            addCriterion("COSTUME_FEE like", value, "costumeFee");
            return (Criteria) this;
        }

        public Criteria andCostumeFeeNotLike(String value) {
            addCriterion("COSTUME_FEE not like", value, "costumeFee");
            return (Criteria) this;
        }

        public Criteria andCostumeFeeIn(List<String> values) {
            addCriterion("COSTUME_FEE in", values, "costumeFee");
            return (Criteria) this;
        }

        public Criteria andCostumeFeeNotIn(List<String> values) {
            addCriterion("COSTUME_FEE not in", values, "costumeFee");
            return (Criteria) this;
        }

        public Criteria andCostumeFeeBetween(String value1, String value2) {
            addCriterion("COSTUME_FEE between", value1, value2, "costumeFee");
            return (Criteria) this;
        }

        public Criteria andCostumeFeeNotBetween(String value1, String value2) {
            addCriterion("COSTUME_FEE not between", value1, value2, "costumeFee");
            return (Criteria) this;
        }

        public Criteria andFoodFeeWhetherIsNull() {
            addCriterion("FOOD_FEE_WHETHER is null");
            return (Criteria) this;
        }

        public Criteria andFoodFeeWhetherIsNotNull() {
            addCriterion("FOOD_FEE_WHETHER is not null");
            return (Criteria) this;
        }

        public Criteria andFoodFeeWhetherEqualTo(String value) {
            addCriterion("FOOD_FEE_WHETHER =", value, "foodFeeWhether");
            return (Criteria) this;
        }

        public Criteria andFoodFeeWhetherNotEqualTo(String value) {
            addCriterion("FOOD_FEE_WHETHER <>", value, "foodFeeWhether");
            return (Criteria) this;
        }

        public Criteria andFoodFeeWhetherGreaterThan(String value) {
            addCriterion("FOOD_FEE_WHETHER >", value, "foodFeeWhether");
            return (Criteria) this;
        }

        public Criteria andFoodFeeWhetherGreaterThanOrEqualTo(String value) {
            addCriterion("FOOD_FEE_WHETHER >=", value, "foodFeeWhether");
            return (Criteria) this;
        }

        public Criteria andFoodFeeWhetherLessThan(String value) {
            addCriterion("FOOD_FEE_WHETHER <", value, "foodFeeWhether");
            return (Criteria) this;
        }

        public Criteria andFoodFeeWhetherLessThanOrEqualTo(String value) {
            addCriterion("FOOD_FEE_WHETHER <=", value, "foodFeeWhether");
            return (Criteria) this;
        }

        public Criteria andFoodFeeWhetherLike(String value) {
            addCriterion("FOOD_FEE_WHETHER like", value, "foodFeeWhether");
            return (Criteria) this;
        }

        public Criteria andFoodFeeWhetherNotLike(String value) {
            addCriterion("FOOD_FEE_WHETHER not like", value, "foodFeeWhether");
            return (Criteria) this;
        }

        public Criteria andFoodFeeWhetherIn(List<String> values) {
            addCriterion("FOOD_FEE_WHETHER in", values, "foodFeeWhether");
            return (Criteria) this;
        }

        public Criteria andFoodFeeWhetherNotIn(List<String> values) {
            addCriterion("FOOD_FEE_WHETHER not in", values, "foodFeeWhether");
            return (Criteria) this;
        }

        public Criteria andFoodFeeWhetherBetween(String value1, String value2) {
            addCriterion("FOOD_FEE_WHETHER between", value1, value2, "foodFeeWhether");
            return (Criteria) this;
        }

        public Criteria andFoodFeeWhetherNotBetween(String value1, String value2) {
            addCriterion("FOOD_FEE_WHETHER not between", value1, value2, "foodFeeWhether");
            return (Criteria) this;
        }

        public Criteria andFoodFeeIsNull() {
            addCriterion("FOOD_FEE is null");
            return (Criteria) this;
        }

        public Criteria andFoodFeeIsNotNull() {
            addCriterion("FOOD_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andFoodFeeEqualTo(String value) {
            addCriterion("FOOD_FEE =", value, "foodFee");
            return (Criteria) this;
        }

        public Criteria andFoodFeeNotEqualTo(String value) {
            addCriterion("FOOD_FEE <>", value, "foodFee");
            return (Criteria) this;
        }

        public Criteria andFoodFeeGreaterThan(String value) {
            addCriterion("FOOD_FEE >", value, "foodFee");
            return (Criteria) this;
        }

        public Criteria andFoodFeeGreaterThanOrEqualTo(String value) {
            addCriterion("FOOD_FEE >=", value, "foodFee");
            return (Criteria) this;
        }

        public Criteria andFoodFeeLessThan(String value) {
            addCriterion("FOOD_FEE <", value, "foodFee");
            return (Criteria) this;
        }

        public Criteria andFoodFeeLessThanOrEqualTo(String value) {
            addCriterion("FOOD_FEE <=", value, "foodFee");
            return (Criteria) this;
        }

        public Criteria andFoodFeeLike(String value) {
            addCriterion("FOOD_FEE like", value, "foodFee");
            return (Criteria) this;
        }

        public Criteria andFoodFeeNotLike(String value) {
            addCriterion("FOOD_FEE not like", value, "foodFee");
            return (Criteria) this;
        }

        public Criteria andFoodFeeIn(List<String> values) {
            addCriterion("FOOD_FEE in", values, "foodFee");
            return (Criteria) this;
        }

        public Criteria andFoodFeeNotIn(List<String> values) {
            addCriterion("FOOD_FEE not in", values, "foodFee");
            return (Criteria) this;
        }

        public Criteria andFoodFeeBetween(String value1, String value2) {
            addCriterion("FOOD_FEE between", value1, value2, "foodFee");
            return (Criteria) this;
        }

        public Criteria andFoodFeeNotBetween(String value1, String value2) {
            addCriterion("FOOD_FEE not between", value1, value2, "foodFee");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeIsNull() {
            addCriterion("SUBSIDY_FEE is null");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeIsNotNull() {
            addCriterion("SUBSIDY_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeEqualTo(String value) {
            addCriterion("SUBSIDY_FEE =", value, "subsidyFee");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeNotEqualTo(String value) {
            addCriterion("SUBSIDY_FEE <>", value, "subsidyFee");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeGreaterThan(String value) {
            addCriterion("SUBSIDY_FEE >", value, "subsidyFee");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeGreaterThanOrEqualTo(String value) {
            addCriterion("SUBSIDY_FEE >=", value, "subsidyFee");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeLessThan(String value) {
            addCriterion("SUBSIDY_FEE <", value, "subsidyFee");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeLessThanOrEqualTo(String value) {
            addCriterion("SUBSIDY_FEE <=", value, "subsidyFee");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeLike(String value) {
            addCriterion("SUBSIDY_FEE like", value, "subsidyFee");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeNotLike(String value) {
            addCriterion("SUBSIDY_FEE not like", value, "subsidyFee");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeIn(List<String> values) {
            addCriterion("SUBSIDY_FEE in", values, "subsidyFee");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeNotIn(List<String> values) {
            addCriterion("SUBSIDY_FEE not in", values, "subsidyFee");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeBetween(String value1, String value2) {
            addCriterion("SUBSIDY_FEE between", value1, value2, "subsidyFee");
            return (Criteria) this;
        }

        public Criteria andSubsidyFeeNotBetween(String value1, String value2) {
            addCriterion("SUBSIDY_FEE not between", value1, value2, "subsidyFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeIsNull() {
            addCriterion("OTHER_FEE is null");
            return (Criteria) this;
        }

        public Criteria andOtherFeeIsNotNull() {
            addCriterion("OTHER_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andOtherFeeEqualTo(String value) {
            addCriterion("OTHER_FEE =", value, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeNotEqualTo(String value) {
            addCriterion("OTHER_FEE <>", value, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeGreaterThan(String value) {
            addCriterion("OTHER_FEE >", value, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeGreaterThanOrEqualTo(String value) {
            addCriterion("OTHER_FEE >=", value, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeLessThan(String value) {
            addCriterion("OTHER_FEE <", value, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeLessThanOrEqualTo(String value) {
            addCriterion("OTHER_FEE <=", value, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeLike(String value) {
            addCriterion("OTHER_FEE like", value, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeNotLike(String value) {
            addCriterion("OTHER_FEE not like", value, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeIn(List<String> values) {
            addCriterion("OTHER_FEE in", values, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeNotIn(List<String> values) {
            addCriterion("OTHER_FEE not in", values, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeBetween(String value1, String value2) {
            addCriterion("OTHER_FEE between", value1, value2, "otherFee");
            return (Criteria) this;
        }

        public Criteria andOtherFeeNotBetween(String value1, String value2) {
            addCriterion("OTHER_FEE not between", value1, value2, "otherFee");
            return (Criteria) this;
        }

        public Criteria andSumFeeIsNull() {
            addCriterion("SUM_FEE is null");
            return (Criteria) this;
        }

        public Criteria andSumFeeIsNotNull() {
            addCriterion("SUM_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andSumFeeEqualTo(String value) {
            addCriterion("SUM_FEE =", value, "sumFee");
            return (Criteria) this;
        }

        public Criteria andSumFeeNotEqualTo(String value) {
            addCriterion("SUM_FEE <>", value, "sumFee");
            return (Criteria) this;
        }

        public Criteria andSumFeeGreaterThan(String value) {
            addCriterion("SUM_FEE >", value, "sumFee");
            return (Criteria) this;
        }

        public Criteria andSumFeeGreaterThanOrEqualTo(String value) {
            addCriterion("SUM_FEE >=", value, "sumFee");
            return (Criteria) this;
        }

        public Criteria andSumFeeLessThan(String value) {
            addCriterion("SUM_FEE <", value, "sumFee");
            return (Criteria) this;
        }

        public Criteria andSumFeeLessThanOrEqualTo(String value) {
            addCriterion("SUM_FEE <=", value, "sumFee");
            return (Criteria) this;
        }

        public Criteria andSumFeeLike(String value) {
            addCriterion("SUM_FEE like", value, "sumFee");
            return (Criteria) this;
        }

        public Criteria andSumFeeNotLike(String value) {
            addCriterion("SUM_FEE not like", value, "sumFee");
            return (Criteria) this;
        }

        public Criteria andSumFeeIn(List<String> values) {
            addCriterion("SUM_FEE in", values, "sumFee");
            return (Criteria) this;
        }

        public Criteria andSumFeeNotIn(List<String> values) {
            addCriterion("SUM_FEE not in", values, "sumFee");
            return (Criteria) this;
        }

        public Criteria andSumFeeBetween(String value1, String value2) {
            addCriterion("SUM_FEE between", value1, value2, "sumFee");
            return (Criteria) this;
        }

        public Criteria andSumFeeNotBetween(String value1, String value2) {
            addCriterion("SUM_FEE not between", value1, value2, "sumFee");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdIsNull() {
            addCriterion("AUDIT_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdIsNotNull() {
            addCriterion("AUDIT_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID =", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID <>", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdGreaterThan(String value) {
            addCriterion("AUDIT_STATUS_ID >", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID >=", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdLessThan(String value) {
            addCriterion("AUDIT_STATUS_ID <", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_ID <=", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdLike(String value) {
            addCriterion("AUDIT_STATUS_ID like", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotLike(String value) {
            addCriterion("AUDIT_STATUS_ID not like", value, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdIn(List<String> values) {
            addCriterion("AUDIT_STATUS_ID in", values, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotIn(List<String> values) {
            addCriterion("AUDIT_STATUS_ID not in", values, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_ID between", value1, value2, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIdNotBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_ID not between", value1, value2, "auditStatusId");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameIsNull() {
            addCriterion("AUDIT_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameIsNotNull() {
            addCriterion("AUDIT_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME =", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME <>", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameGreaterThan(String value) {
            addCriterion("AUDIT_STATUS_NAME >", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME >=", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameLessThan(String value) {
            addCriterion("AUDIT_STATUS_NAME <", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS_NAME <=", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameLike(String value) {
            addCriterion("AUDIT_STATUS_NAME like", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotLike(String value) {
            addCriterion("AUDIT_STATUS_NAME not like", value, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameIn(List<String> values) {
            addCriterion("AUDIT_STATUS_NAME in", values, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotIn(List<String> values) {
            addCriterion("AUDIT_STATUS_NAME not in", values, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_NAME between", value1, value2, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNameNotBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS_NAME not between", value1, value2, "auditStatusName");
            return (Criteria) this;
        }

        public Criteria andAdminFlowIsNull() {
            addCriterion("ADMIN_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andAdminFlowIsNotNull() {
            addCriterion("ADMIN_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andAdminFlowEqualTo(String value) {
            addCriterion("ADMIN_FLOW =", value, "adminFlow");
            return (Criteria) this;
        }

        public Criteria andAdminFlowNotEqualTo(String value) {
            addCriterion("ADMIN_FLOW <>", value, "adminFlow");
            return (Criteria) this;
        }

        public Criteria andAdminFlowGreaterThan(String value) {
            addCriterion("ADMIN_FLOW >", value, "adminFlow");
            return (Criteria) this;
        }

        public Criteria andAdminFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ADMIN_FLOW >=", value, "adminFlow");
            return (Criteria) this;
        }

        public Criteria andAdminFlowLessThan(String value) {
            addCriterion("ADMIN_FLOW <", value, "adminFlow");
            return (Criteria) this;
        }

        public Criteria andAdminFlowLessThanOrEqualTo(String value) {
            addCriterion("ADMIN_FLOW <=", value, "adminFlow");
            return (Criteria) this;
        }

        public Criteria andAdminFlowLike(String value) {
            addCriterion("ADMIN_FLOW like", value, "adminFlow");
            return (Criteria) this;
        }

        public Criteria andAdminFlowNotLike(String value) {
            addCriterion("ADMIN_FLOW not like", value, "adminFlow");
            return (Criteria) this;
        }

        public Criteria andAdminFlowIn(List<String> values) {
            addCriterion("ADMIN_FLOW in", values, "adminFlow");
            return (Criteria) this;
        }

        public Criteria andAdminFlowNotIn(List<String> values) {
            addCriterion("ADMIN_FLOW not in", values, "adminFlow");
            return (Criteria) this;
        }

        public Criteria andAdminFlowBetween(String value1, String value2) {
            addCriterion("ADMIN_FLOW between", value1, value2, "adminFlow");
            return (Criteria) this;
        }

        public Criteria andAdminFlowNotBetween(String value1, String value2) {
            addCriterion("ADMIN_FLOW not between", value1, value2, "adminFlow");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNull() {
            addCriterion("AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNotNull() {
            addCriterion("AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeEqualTo(String value) {
            addCriterion("AUDIT_TIME =", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotEqualTo(String value) {
            addCriterion("AUDIT_TIME <>", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThan(String value) {
            addCriterion("AUDIT_TIME >", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_TIME >=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThan(String value) {
            addCriterion("AUDIT_TIME <", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_TIME <=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLike(String value) {
            addCriterion("AUDIT_TIME like", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotLike(String value) {
            addCriterion("AUDIT_TIME not like", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIn(List<String> values) {
            addCriterion("AUDIT_TIME in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotIn(List<String> values) {
            addCriterion("AUDIT_TIME not in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeBetween(String value1, String value2) {
            addCriterion("AUDIT_TIME between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotBetween(String value1, String value2) {
            addCriterion("AUDIT_TIME not between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andHeaderAdviceIsNull() {
            addCriterion("HEADER_ADVICE is null");
            return (Criteria) this;
        }

        public Criteria andHeaderAdviceIsNotNull() {
            addCriterion("HEADER_ADVICE is not null");
            return (Criteria) this;
        }

        public Criteria andHeaderAdviceEqualTo(String value) {
            addCriterion("HEADER_ADVICE =", value, "headerAdvice");
            return (Criteria) this;
        }

        public Criteria andHeaderAdviceNotEqualTo(String value) {
            addCriterion("HEADER_ADVICE <>", value, "headerAdvice");
            return (Criteria) this;
        }

        public Criteria andHeaderAdviceGreaterThan(String value) {
            addCriterion("HEADER_ADVICE >", value, "headerAdvice");
            return (Criteria) this;
        }

        public Criteria andHeaderAdviceGreaterThanOrEqualTo(String value) {
            addCriterion("HEADER_ADVICE >=", value, "headerAdvice");
            return (Criteria) this;
        }

        public Criteria andHeaderAdviceLessThan(String value) {
            addCriterion("HEADER_ADVICE <", value, "headerAdvice");
            return (Criteria) this;
        }

        public Criteria andHeaderAdviceLessThanOrEqualTo(String value) {
            addCriterion("HEADER_ADVICE <=", value, "headerAdvice");
            return (Criteria) this;
        }

        public Criteria andHeaderAdviceLike(String value) {
            addCriterion("HEADER_ADVICE like", value, "headerAdvice");
            return (Criteria) this;
        }

        public Criteria andHeaderAdviceNotLike(String value) {
            addCriterion("HEADER_ADVICE not like", value, "headerAdvice");
            return (Criteria) this;
        }

        public Criteria andHeaderAdviceIn(List<String> values) {
            addCriterion("HEADER_ADVICE in", values, "headerAdvice");
            return (Criteria) this;
        }

        public Criteria andHeaderAdviceNotIn(List<String> values) {
            addCriterion("HEADER_ADVICE not in", values, "headerAdvice");
            return (Criteria) this;
        }

        public Criteria andHeaderAdviceBetween(String value1, String value2) {
            addCriterion("HEADER_ADVICE between", value1, value2, "headerAdvice");
            return (Criteria) this;
        }

        public Criteria andHeaderAdviceNotBetween(String value1, String value2) {
            addCriterion("HEADER_ADVICE not between", value1, value2, "headerAdvice");
            return (Criteria) this;
        }

        public Criteria andAdminAdviceIsNull() {
            addCriterion("ADMIN_ADVICE is null");
            return (Criteria) this;
        }

        public Criteria andAdminAdviceIsNotNull() {
            addCriterion("ADMIN_ADVICE is not null");
            return (Criteria) this;
        }

        public Criteria andAdminAdviceEqualTo(String value) {
            addCriterion("ADMIN_ADVICE =", value, "adminAdvice");
            return (Criteria) this;
        }

        public Criteria andAdminAdviceNotEqualTo(String value) {
            addCriterion("ADMIN_ADVICE <>", value, "adminAdvice");
            return (Criteria) this;
        }

        public Criteria andAdminAdviceGreaterThan(String value) {
            addCriterion("ADMIN_ADVICE >", value, "adminAdvice");
            return (Criteria) this;
        }

        public Criteria andAdminAdviceGreaterThanOrEqualTo(String value) {
            addCriterion("ADMIN_ADVICE >=", value, "adminAdvice");
            return (Criteria) this;
        }

        public Criteria andAdminAdviceLessThan(String value) {
            addCriterion("ADMIN_ADVICE <", value, "adminAdvice");
            return (Criteria) this;
        }

        public Criteria andAdminAdviceLessThanOrEqualTo(String value) {
            addCriterion("ADMIN_ADVICE <=", value, "adminAdvice");
            return (Criteria) this;
        }

        public Criteria andAdminAdviceLike(String value) {
            addCriterion("ADMIN_ADVICE like", value, "adminAdvice");
            return (Criteria) this;
        }

        public Criteria andAdminAdviceNotLike(String value) {
            addCriterion("ADMIN_ADVICE not like", value, "adminAdvice");
            return (Criteria) this;
        }

        public Criteria andAdminAdviceIn(List<String> values) {
            addCriterion("ADMIN_ADVICE in", values, "adminAdvice");
            return (Criteria) this;
        }

        public Criteria andAdminAdviceNotIn(List<String> values) {
            addCriterion("ADMIN_ADVICE not in", values, "adminAdvice");
            return (Criteria) this;
        }

        public Criteria andAdminAdviceBetween(String value1, String value2) {
            addCriterion("ADMIN_ADVICE between", value1, value2, "adminAdvice");
            return (Criteria) this;
        }

        public Criteria andAdminAdviceNotBetween(String value1, String value2) {
            addCriterion("ADMIN_ADVICE not between", value1, value2, "adminAdvice");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusIdIsNull() {
            addCriterion("EXPENSE_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusIdIsNotNull() {
            addCriterion("EXPENSE_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusIdEqualTo(String value) {
            addCriterion("EXPENSE_STATUS_ID =", value, "expenseStatusId");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusIdNotEqualTo(String value) {
            addCriterion("EXPENSE_STATUS_ID <>", value, "expenseStatusId");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusIdGreaterThan(String value) {
            addCriterion("EXPENSE_STATUS_ID >", value, "expenseStatusId");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("EXPENSE_STATUS_ID >=", value, "expenseStatusId");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusIdLessThan(String value) {
            addCriterion("EXPENSE_STATUS_ID <", value, "expenseStatusId");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusIdLessThanOrEqualTo(String value) {
            addCriterion("EXPENSE_STATUS_ID <=", value, "expenseStatusId");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusIdLike(String value) {
            addCriterion("EXPENSE_STATUS_ID like", value, "expenseStatusId");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusIdNotLike(String value) {
            addCriterion("EXPENSE_STATUS_ID not like", value, "expenseStatusId");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusIdIn(List<String> values) {
            addCriterion("EXPENSE_STATUS_ID in", values, "expenseStatusId");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusIdNotIn(List<String> values) {
            addCriterion("EXPENSE_STATUS_ID not in", values, "expenseStatusId");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusIdBetween(String value1, String value2) {
            addCriterion("EXPENSE_STATUS_ID between", value1, value2, "expenseStatusId");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusIdNotBetween(String value1, String value2) {
            addCriterion("EXPENSE_STATUS_ID not between", value1, value2, "expenseStatusId");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusNameIsNull() {
            addCriterion("EXPENSE_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusNameIsNotNull() {
            addCriterion("EXPENSE_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusNameEqualTo(String value) {
            addCriterion("EXPENSE_STATUS_NAME =", value, "expenseStatusName");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusNameNotEqualTo(String value) {
            addCriterion("EXPENSE_STATUS_NAME <>", value, "expenseStatusName");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusNameGreaterThan(String value) {
            addCriterion("EXPENSE_STATUS_NAME >", value, "expenseStatusName");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("EXPENSE_STATUS_NAME >=", value, "expenseStatusName");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusNameLessThan(String value) {
            addCriterion("EXPENSE_STATUS_NAME <", value, "expenseStatusName");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusNameLessThanOrEqualTo(String value) {
            addCriterion("EXPENSE_STATUS_NAME <=", value, "expenseStatusName");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusNameLike(String value) {
            addCriterion("EXPENSE_STATUS_NAME like", value, "expenseStatusName");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusNameNotLike(String value) {
            addCriterion("EXPENSE_STATUS_NAME not like", value, "expenseStatusName");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusNameIn(List<String> values) {
            addCriterion("EXPENSE_STATUS_NAME in", values, "expenseStatusName");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusNameNotIn(List<String> values) {
            addCriterion("EXPENSE_STATUS_NAME not in", values, "expenseStatusName");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusNameBetween(String value1, String value2) {
            addCriterion("EXPENSE_STATUS_NAME between", value1, value2, "expenseStatusName");
            return (Criteria) this;
        }

        public Criteria andExpenseStatusNameNotBetween(String value1, String value2) {
            addCriterion("EXPENSE_STATUS_NAME not between", value1, value2, "expenseStatusName");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminFlowIsNull() {
            addCriterion("EXPENSE_ADMIN_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminFlowIsNotNull() {
            addCriterion("EXPENSE_ADMIN_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminFlowEqualTo(String value) {
            addCriterion("EXPENSE_ADMIN_FLOW =", value, "expenseAdminFlow");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminFlowNotEqualTo(String value) {
            addCriterion("EXPENSE_ADMIN_FLOW <>", value, "expenseAdminFlow");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminFlowGreaterThan(String value) {
            addCriterion("EXPENSE_ADMIN_FLOW >", value, "expenseAdminFlow");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminFlowGreaterThanOrEqualTo(String value) {
            addCriterion("EXPENSE_ADMIN_FLOW >=", value, "expenseAdminFlow");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminFlowLessThan(String value) {
            addCriterion("EXPENSE_ADMIN_FLOW <", value, "expenseAdminFlow");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminFlowLessThanOrEqualTo(String value) {
            addCriterion("EXPENSE_ADMIN_FLOW <=", value, "expenseAdminFlow");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminFlowLike(String value) {
            addCriterion("EXPENSE_ADMIN_FLOW like", value, "expenseAdminFlow");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminFlowNotLike(String value) {
            addCriterion("EXPENSE_ADMIN_FLOW not like", value, "expenseAdminFlow");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminFlowIn(List<String> values) {
            addCriterion("EXPENSE_ADMIN_FLOW in", values, "expenseAdminFlow");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminFlowNotIn(List<String> values) {
            addCriterion("EXPENSE_ADMIN_FLOW not in", values, "expenseAdminFlow");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminFlowBetween(String value1, String value2) {
            addCriterion("EXPENSE_ADMIN_FLOW between", value1, value2, "expenseAdminFlow");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminFlowNotBetween(String value1, String value2) {
            addCriterion("EXPENSE_ADMIN_FLOW not between", value1, value2, "expenseAdminFlow");
            return (Criteria) this;
        }

        public Criteria andExpenseAuditTimeIsNull() {
            addCriterion("EXPENSE_AUDIT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andExpenseAuditTimeIsNotNull() {
            addCriterion("EXPENSE_AUDIT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andExpenseAuditTimeEqualTo(String value) {
            addCriterion("EXPENSE_AUDIT_TIME =", value, "expenseAuditTime");
            return (Criteria) this;
        }

        public Criteria andExpenseAuditTimeNotEqualTo(String value) {
            addCriterion("EXPENSE_AUDIT_TIME <>", value, "expenseAuditTime");
            return (Criteria) this;
        }

        public Criteria andExpenseAuditTimeGreaterThan(String value) {
            addCriterion("EXPENSE_AUDIT_TIME >", value, "expenseAuditTime");
            return (Criteria) this;
        }

        public Criteria andExpenseAuditTimeGreaterThanOrEqualTo(String value) {
            addCriterion("EXPENSE_AUDIT_TIME >=", value, "expenseAuditTime");
            return (Criteria) this;
        }

        public Criteria andExpenseAuditTimeLessThan(String value) {
            addCriterion("EXPENSE_AUDIT_TIME <", value, "expenseAuditTime");
            return (Criteria) this;
        }

        public Criteria andExpenseAuditTimeLessThanOrEqualTo(String value) {
            addCriterion("EXPENSE_AUDIT_TIME <=", value, "expenseAuditTime");
            return (Criteria) this;
        }

        public Criteria andExpenseAuditTimeLike(String value) {
            addCriterion("EXPENSE_AUDIT_TIME like", value, "expenseAuditTime");
            return (Criteria) this;
        }

        public Criteria andExpenseAuditTimeNotLike(String value) {
            addCriterion("EXPENSE_AUDIT_TIME not like", value, "expenseAuditTime");
            return (Criteria) this;
        }

        public Criteria andExpenseAuditTimeIn(List<String> values) {
            addCriterion("EXPENSE_AUDIT_TIME in", values, "expenseAuditTime");
            return (Criteria) this;
        }

        public Criteria andExpenseAuditTimeNotIn(List<String> values) {
            addCriterion("EXPENSE_AUDIT_TIME not in", values, "expenseAuditTime");
            return (Criteria) this;
        }

        public Criteria andExpenseAuditTimeBetween(String value1, String value2) {
            addCriterion("EXPENSE_AUDIT_TIME between", value1, value2, "expenseAuditTime");
            return (Criteria) this;
        }

        public Criteria andExpenseAuditTimeNotBetween(String value1, String value2) {
            addCriterion("EXPENSE_AUDIT_TIME not between", value1, value2, "expenseAuditTime");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminAdviceIsNull() {
            addCriterion("EXPENSE_ADMIN_ADVICE is null");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminAdviceIsNotNull() {
            addCriterion("EXPENSE_ADMIN_ADVICE is not null");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminAdviceEqualTo(String value) {
            addCriterion("EXPENSE_ADMIN_ADVICE =", value, "expenseAdminAdvice");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminAdviceNotEqualTo(String value) {
            addCriterion("EXPENSE_ADMIN_ADVICE <>", value, "expenseAdminAdvice");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminAdviceGreaterThan(String value) {
            addCriterion("EXPENSE_ADMIN_ADVICE >", value, "expenseAdminAdvice");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminAdviceGreaterThanOrEqualTo(String value) {
            addCriterion("EXPENSE_ADMIN_ADVICE >=", value, "expenseAdminAdvice");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminAdviceLessThan(String value) {
            addCriterion("EXPENSE_ADMIN_ADVICE <", value, "expenseAdminAdvice");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminAdviceLessThanOrEqualTo(String value) {
            addCriterion("EXPENSE_ADMIN_ADVICE <=", value, "expenseAdminAdvice");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminAdviceLike(String value) {
            addCriterion("EXPENSE_ADMIN_ADVICE like", value, "expenseAdminAdvice");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminAdviceNotLike(String value) {
            addCriterion("EXPENSE_ADMIN_ADVICE not like", value, "expenseAdminAdvice");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminAdviceIn(List<String> values) {
            addCriterion("EXPENSE_ADMIN_ADVICE in", values, "expenseAdminAdvice");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminAdviceNotIn(List<String> values) {
            addCriterion("EXPENSE_ADMIN_ADVICE not in", values, "expenseAdminAdvice");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminAdviceBetween(String value1, String value2) {
            addCriterion("EXPENSE_ADMIN_ADVICE between", value1, value2, "expenseAdminAdvice");
            return (Criteria) this;
        }

        public Criteria andExpenseAdminAdviceNotBetween(String value1, String value2) {
            addCriterion("EXPENSE_ADMIN_ADVICE not between", value1, value2, "expenseAdminAdvice");
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