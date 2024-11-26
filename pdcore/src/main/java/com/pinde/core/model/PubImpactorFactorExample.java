package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class PubImpactorFactorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PubImpactorFactorExample() {
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

        public Criteria andFactorFlowIsNull() {
            addCriterion("FACTOR_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andFactorFlowIsNotNull() {
            addCriterion("FACTOR_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andFactorFlowEqualTo(String value) {
            addCriterion("FACTOR_FLOW =", value, "factorFlow");
            return (Criteria) this;
        }

        public Criteria andFactorFlowNotEqualTo(String value) {
            addCriterion("FACTOR_FLOW <>", value, "factorFlow");
            return (Criteria) this;
        }

        public Criteria andFactorFlowGreaterThan(String value) {
            addCriterion("FACTOR_FLOW >", value, "factorFlow");
            return (Criteria) this;
        }

        public Criteria andFactorFlowGreaterThanOrEqualTo(String value) {
            addCriterion("FACTOR_FLOW >=", value, "factorFlow");
            return (Criteria) this;
        }

        public Criteria andFactorFlowLessThan(String value) {
            addCriterion("FACTOR_FLOW <", value, "factorFlow");
            return (Criteria) this;
        }

        public Criteria andFactorFlowLessThanOrEqualTo(String value) {
            addCriterion("FACTOR_FLOW <=", value, "factorFlow");
            return (Criteria) this;
        }

        public Criteria andFactorFlowLike(String value) {
            addCriterion("FACTOR_FLOW like", value, "factorFlow");
            return (Criteria) this;
        }

        public Criteria andFactorFlowNotLike(String value) {
            addCriterion("FACTOR_FLOW not like", value, "factorFlow");
            return (Criteria) this;
        }

        public Criteria andFactorFlowIn(List<String> values) {
            addCriterion("FACTOR_FLOW in", values, "factorFlow");
            return (Criteria) this;
        }

        public Criteria andFactorFlowNotIn(List<String> values) {
            addCriterion("FACTOR_FLOW not in", values, "factorFlow");
            return (Criteria) this;
        }

        public Criteria andFactorFlowBetween(String value1, String value2) {
            addCriterion("FACTOR_FLOW between", value1, value2, "factorFlow");
            return (Criteria) this;
        }

        public Criteria andFactorFlowNotBetween(String value1, String value2) {
            addCriterion("FACTOR_FLOW not between", value1, value2, "factorFlow");
            return (Criteria) this;
        }

        public Criteria andYearIsNull() {
            addCriterion("YEAR is null");
            return (Criteria) this;
        }

        public Criteria andYearIsNotNull() {
            addCriterion("YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andYearEqualTo(String value) {
            addCriterion("YEAR =", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotEqualTo(String value) {
            addCriterion("YEAR <>", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThan(String value) {
            addCriterion("YEAR >", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualTo(String value) {
            addCriterion("YEAR >=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThan(String value) {
            addCriterion("YEAR <", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualTo(String value) {
            addCriterion("YEAR <=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLike(String value) {
            addCriterion("YEAR like", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotLike(String value) {
            addCriterion("YEAR not like", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearIn(List<String> values) {
            addCriterion("YEAR in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotIn(List<String> values) {
            addCriterion("YEAR not in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearBetween(String value1, String value2) {
            addCriterion("YEAR between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotBetween(String value1, String value2) {
            addCriterion("YEAR not between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andFactorTypeIdIsNull() {
            addCriterion("FACTOR_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andFactorTypeIdIsNotNull() {
            addCriterion("FACTOR_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFactorTypeIdEqualTo(String value) {
            addCriterion("FACTOR_TYPE_ID =", value, "factorTypeId");
            return (Criteria) this;
        }

        public Criteria andFactorTypeIdNotEqualTo(String value) {
            addCriterion("FACTOR_TYPE_ID <>", value, "factorTypeId");
            return (Criteria) this;
        }

        public Criteria andFactorTypeIdGreaterThan(String value) {
            addCriterion("FACTOR_TYPE_ID >", value, "factorTypeId");
            return (Criteria) this;
        }

        public Criteria andFactorTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("FACTOR_TYPE_ID >=", value, "factorTypeId");
            return (Criteria) this;
        }

        public Criteria andFactorTypeIdLessThan(String value) {
            addCriterion("FACTOR_TYPE_ID <", value, "factorTypeId");
            return (Criteria) this;
        }

        public Criteria andFactorTypeIdLessThanOrEqualTo(String value) {
            addCriterion("FACTOR_TYPE_ID <=", value, "factorTypeId");
            return (Criteria) this;
        }

        public Criteria andFactorTypeIdLike(String value) {
            addCriterion("FACTOR_TYPE_ID like", value, "factorTypeId");
            return (Criteria) this;
        }

        public Criteria andFactorTypeIdNotLike(String value) {
            addCriterion("FACTOR_TYPE_ID not like", value, "factorTypeId");
            return (Criteria) this;
        }

        public Criteria andFactorTypeIdIn(List<String> values) {
            addCriterion("FACTOR_TYPE_ID in", values, "factorTypeId");
            return (Criteria) this;
        }

        public Criteria andFactorTypeIdNotIn(List<String> values) {
            addCriterion("FACTOR_TYPE_ID not in", values, "factorTypeId");
            return (Criteria) this;
        }

        public Criteria andFactorTypeIdBetween(String value1, String value2) {
            addCriterion("FACTOR_TYPE_ID between", value1, value2, "factorTypeId");
            return (Criteria) this;
        }

        public Criteria andFactorTypeIdNotBetween(String value1, String value2) {
            addCriterion("FACTOR_TYPE_ID not between", value1, value2, "factorTypeId");
            return (Criteria) this;
        }

        public Criteria andFactorTypeNameIsNull() {
            addCriterion("FACTOR_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFactorTypeNameIsNotNull() {
            addCriterion("FACTOR_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFactorTypeNameEqualTo(String value) {
            addCriterion("FACTOR_TYPE_NAME =", value, "factorTypeName");
            return (Criteria) this;
        }

        public Criteria andFactorTypeNameNotEqualTo(String value) {
            addCriterion("FACTOR_TYPE_NAME <>", value, "factorTypeName");
            return (Criteria) this;
        }

        public Criteria andFactorTypeNameGreaterThan(String value) {
            addCriterion("FACTOR_TYPE_NAME >", value, "factorTypeName");
            return (Criteria) this;
        }

        public Criteria andFactorTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("FACTOR_TYPE_NAME >=", value, "factorTypeName");
            return (Criteria) this;
        }

        public Criteria andFactorTypeNameLessThan(String value) {
            addCriterion("FACTOR_TYPE_NAME <", value, "factorTypeName");
            return (Criteria) this;
        }

        public Criteria andFactorTypeNameLessThanOrEqualTo(String value) {
            addCriterion("FACTOR_TYPE_NAME <=", value, "factorTypeName");
            return (Criteria) this;
        }

        public Criteria andFactorTypeNameLike(String value) {
            addCriterion("FACTOR_TYPE_NAME like", value, "factorTypeName");
            return (Criteria) this;
        }

        public Criteria andFactorTypeNameNotLike(String value) {
            addCriterion("FACTOR_TYPE_NAME not like", value, "factorTypeName");
            return (Criteria) this;
        }

        public Criteria andFactorTypeNameIn(List<String> values) {
            addCriterion("FACTOR_TYPE_NAME in", values, "factorTypeName");
            return (Criteria) this;
        }

        public Criteria andFactorTypeNameNotIn(List<String> values) {
            addCriterion("FACTOR_TYPE_NAME not in", values, "factorTypeName");
            return (Criteria) this;
        }

        public Criteria andFactorTypeNameBetween(String value1, String value2) {
            addCriterion("FACTOR_TYPE_NAME between", value1, value2, "factorTypeName");
            return (Criteria) this;
        }

        public Criteria andFactorTypeNameNotBetween(String value1, String value2) {
            addCriterion("FACTOR_TYPE_NAME not between", value1, value2, "factorTypeName");
            return (Criteria) this;
        }

        public Criteria andRankIsNull() {
            addCriterion("RANK is null");
            return (Criteria) this;
        }

        public Criteria andRankIsNotNull() {
            addCriterion("RANK is not null");
            return (Criteria) this;
        }

        public Criteria andRankEqualTo(String value) {
            addCriterion("RANK =", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotEqualTo(String value) {
            addCriterion("RANK <>", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankGreaterThan(String value) {
            addCriterion("RANK >", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankGreaterThanOrEqualTo(String value) {
            addCriterion("RANK >=", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankLessThan(String value) {
            addCriterion("RANK <", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankLessThanOrEqualTo(String value) {
            addCriterion("RANK <=", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankLike(String value) {
            addCriterion("RANK like", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotLike(String value) {
            addCriterion("RANK not like", value, "rank");
            return (Criteria) this;
        }

        public Criteria andRankIn(List<String> values) {
            addCriterion("RANK in", values, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotIn(List<String> values) {
            addCriterion("RANK not in", values, "rank");
            return (Criteria) this;
        }

        public Criteria andRankBetween(String value1, String value2) {
            addCriterion("RANK between", value1, value2, "rank");
            return (Criteria) this;
        }

        public Criteria andRankNotBetween(String value1, String value2) {
            addCriterion("RANK not between", value1, value2, "rank");
            return (Criteria) this;
        }

        public Criteria andJournalTitleIsNull() {
            addCriterion("JOURNAL_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andJournalTitleIsNotNull() {
            addCriterion("JOURNAL_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andJournalTitleEqualTo(String value) {
            addCriterion("JOURNAL_TITLE =", value, "journalTitle");
            return (Criteria) this;
        }

        public Criteria andJournalTitleNotEqualTo(String value) {
            addCriterion("JOURNAL_TITLE <>", value, "journalTitle");
            return (Criteria) this;
        }

        public Criteria andJournalTitleGreaterThan(String value) {
            addCriterion("JOURNAL_TITLE >", value, "journalTitle");
            return (Criteria) this;
        }

        public Criteria andJournalTitleGreaterThanOrEqualTo(String value) {
            addCriterion("JOURNAL_TITLE >=", value, "journalTitle");
            return (Criteria) this;
        }

        public Criteria andJournalTitleLessThan(String value) {
            addCriterion("JOURNAL_TITLE <", value, "journalTitle");
            return (Criteria) this;
        }

        public Criteria andJournalTitleLessThanOrEqualTo(String value) {
            addCriterion("JOURNAL_TITLE <=", value, "journalTitle");
            return (Criteria) this;
        }

        public Criteria andJournalTitleLike(String value) {
            addCriterion("JOURNAL_TITLE like", value, "journalTitle");
            return (Criteria) this;
        }

        public Criteria andJournalTitleNotLike(String value) {
            addCriterion("JOURNAL_TITLE not like", value, "journalTitle");
            return (Criteria) this;
        }

        public Criteria andJournalTitleIn(List<String> values) {
            addCriterion("JOURNAL_TITLE in", values, "journalTitle");
            return (Criteria) this;
        }

        public Criteria andJournalTitleNotIn(List<String> values) {
            addCriterion("JOURNAL_TITLE not in", values, "journalTitle");
            return (Criteria) this;
        }

        public Criteria andJournalTitleBetween(String value1, String value2) {
            addCriterion("JOURNAL_TITLE between", value1, value2, "journalTitle");
            return (Criteria) this;
        }

        public Criteria andJournalTitleNotBetween(String value1, String value2) {
            addCriterion("JOURNAL_TITLE not between", value1, value2, "journalTitle");
            return (Criteria) this;
        }

        public Criteria andIssnIsNull() {
            addCriterion("ISSN is null");
            return (Criteria) this;
        }

        public Criteria andIssnIsNotNull() {
            addCriterion("ISSN is not null");
            return (Criteria) this;
        }

        public Criteria andIssnEqualTo(String value) {
            addCriterion("ISSN =", value, "issn");
            return (Criteria) this;
        }

        public Criteria andIssnNotEqualTo(String value) {
            addCriterion("ISSN <>", value, "issn");
            return (Criteria) this;
        }

        public Criteria andIssnGreaterThan(String value) {
            addCriterion("ISSN >", value, "issn");
            return (Criteria) this;
        }

        public Criteria andIssnGreaterThanOrEqualTo(String value) {
            addCriterion("ISSN >=", value, "issn");
            return (Criteria) this;
        }

        public Criteria andIssnLessThan(String value) {
            addCriterion("ISSN <", value, "issn");
            return (Criteria) this;
        }

        public Criteria andIssnLessThanOrEqualTo(String value) {
            addCriterion("ISSN <=", value, "issn");
            return (Criteria) this;
        }

        public Criteria andIssnLike(String value) {
            addCriterion("ISSN like", value, "issn");
            return (Criteria) this;
        }

        public Criteria andIssnNotLike(String value) {
            addCriterion("ISSN not like", value, "issn");
            return (Criteria) this;
        }

        public Criteria andIssnIn(List<String> values) {
            addCriterion("ISSN in", values, "issn");
            return (Criteria) this;
        }

        public Criteria andIssnNotIn(List<String> values) {
            addCriterion("ISSN not in", values, "issn");
            return (Criteria) this;
        }

        public Criteria andIssnBetween(String value1, String value2) {
            addCriterion("ISSN between", value1, value2, "issn");
            return (Criteria) this;
        }

        public Criteria andIssnNotBetween(String value1, String value2) {
            addCriterion("ISSN not between", value1, value2, "issn");
            return (Criteria) this;
        }

        public Criteria andTotalCitesIsNull() {
            addCriterion("TOTAL_CITES is null");
            return (Criteria) this;
        }

        public Criteria andTotalCitesIsNotNull() {
            addCriterion("TOTAL_CITES is not null");
            return (Criteria) this;
        }

        public Criteria andTotalCitesEqualTo(String value) {
            addCriterion("TOTAL_CITES =", value, "totalCites");
            return (Criteria) this;
        }

        public Criteria andTotalCitesNotEqualTo(String value) {
            addCriterion("TOTAL_CITES <>", value, "totalCites");
            return (Criteria) this;
        }

        public Criteria andTotalCitesGreaterThan(String value) {
            addCriterion("TOTAL_CITES >", value, "totalCites");
            return (Criteria) this;
        }

        public Criteria andTotalCitesGreaterThanOrEqualTo(String value) {
            addCriterion("TOTAL_CITES >=", value, "totalCites");
            return (Criteria) this;
        }

        public Criteria andTotalCitesLessThan(String value) {
            addCriterion("TOTAL_CITES <", value, "totalCites");
            return (Criteria) this;
        }

        public Criteria andTotalCitesLessThanOrEqualTo(String value) {
            addCriterion("TOTAL_CITES <=", value, "totalCites");
            return (Criteria) this;
        }

        public Criteria andTotalCitesLike(String value) {
            addCriterion("TOTAL_CITES like", value, "totalCites");
            return (Criteria) this;
        }

        public Criteria andTotalCitesNotLike(String value) {
            addCriterion("TOTAL_CITES not like", value, "totalCites");
            return (Criteria) this;
        }

        public Criteria andTotalCitesIn(List<String> values) {
            addCriterion("TOTAL_CITES in", values, "totalCites");
            return (Criteria) this;
        }

        public Criteria andTotalCitesNotIn(List<String> values) {
            addCriterion("TOTAL_CITES not in", values, "totalCites");
            return (Criteria) this;
        }

        public Criteria andTotalCitesBetween(String value1, String value2) {
            addCriterion("TOTAL_CITES between", value1, value2, "totalCites");
            return (Criteria) this;
        }

        public Criteria andTotalCitesNotBetween(String value1, String value2) {
            addCriterion("TOTAL_CITES not between", value1, value2, "totalCites");
            return (Criteria) this;
        }

        public Criteria andImpactFactorIsNull() {
            addCriterion("IMPACT_FACTOR is null");
            return (Criteria) this;
        }

        public Criteria andImpactFactorIsNotNull() {
            addCriterion("IMPACT_FACTOR is not null");
            return (Criteria) this;
        }

        public Criteria andImpactFactorEqualTo(String value) {
            addCriterion("IMPACT_FACTOR =", value, "impactFactor");
            return (Criteria) this;
        }

        public Criteria andImpactFactorNotEqualTo(String value) {
            addCriterion("IMPACT_FACTOR <>", value, "impactFactor");
            return (Criteria) this;
        }

        public Criteria andImpactFactorGreaterThan(String value) {
            addCriterion("IMPACT_FACTOR >", value, "impactFactor");
            return (Criteria) this;
        }

        public Criteria andImpactFactorGreaterThanOrEqualTo(String value) {
            addCriterion("IMPACT_FACTOR >=", value, "impactFactor");
            return (Criteria) this;
        }

        public Criteria andImpactFactorLessThan(String value) {
            addCriterion("IMPACT_FACTOR <", value, "impactFactor");
            return (Criteria) this;
        }

        public Criteria andImpactFactorLessThanOrEqualTo(String value) {
            addCriterion("IMPACT_FACTOR <=", value, "impactFactor");
            return (Criteria) this;
        }

        public Criteria andImpactFactorLike(String value) {
            addCriterion("IMPACT_FACTOR like", value, "impactFactor");
            return (Criteria) this;
        }

        public Criteria andImpactFactorNotLike(String value) {
            addCriterion("IMPACT_FACTOR not like", value, "impactFactor");
            return (Criteria) this;
        }

        public Criteria andImpactFactorIn(List<String> values) {
            addCriterion("IMPACT_FACTOR in", values, "impactFactor");
            return (Criteria) this;
        }

        public Criteria andImpactFactorNotIn(List<String> values) {
            addCriterion("IMPACT_FACTOR not in", values, "impactFactor");
            return (Criteria) this;
        }

        public Criteria andImpactFactorBetween(String value1, String value2) {
            addCriterion("IMPACT_FACTOR between", value1, value2, "impactFactor");
            return (Criteria) this;
        }

        public Criteria andImpactFactorNotBetween(String value1, String value2) {
            addCriterion("IMPACT_FACTOR not between", value1, value2, "impactFactor");
            return (Criteria) this;
        }

        public Criteria andFiveYearImpactorFactorIsNull() {
            addCriterion("FIVE_YEAR_IMPACTOR_FACTOR is null");
            return (Criteria) this;
        }

        public Criteria andFiveYearImpactorFactorIsNotNull() {
            addCriterion("FIVE_YEAR_IMPACTOR_FACTOR is not null");
            return (Criteria) this;
        }

        public Criteria andFiveYearImpactorFactorEqualTo(String value) {
            addCriterion("FIVE_YEAR_IMPACTOR_FACTOR =", value, "fiveYearImpactorFactor");
            return (Criteria) this;
        }

        public Criteria andFiveYearImpactorFactorNotEqualTo(String value) {
            addCriterion("FIVE_YEAR_IMPACTOR_FACTOR <>", value, "fiveYearImpactorFactor");
            return (Criteria) this;
        }

        public Criteria andFiveYearImpactorFactorGreaterThan(String value) {
            addCriterion("FIVE_YEAR_IMPACTOR_FACTOR >", value, "fiveYearImpactorFactor");
            return (Criteria) this;
        }

        public Criteria andFiveYearImpactorFactorGreaterThanOrEqualTo(String value) {
            addCriterion("FIVE_YEAR_IMPACTOR_FACTOR >=", value, "fiveYearImpactorFactor");
            return (Criteria) this;
        }

        public Criteria andFiveYearImpactorFactorLessThan(String value) {
            addCriterion("FIVE_YEAR_IMPACTOR_FACTOR <", value, "fiveYearImpactorFactor");
            return (Criteria) this;
        }

        public Criteria andFiveYearImpactorFactorLessThanOrEqualTo(String value) {
            addCriterion("FIVE_YEAR_IMPACTOR_FACTOR <=", value, "fiveYearImpactorFactor");
            return (Criteria) this;
        }

        public Criteria andFiveYearImpactorFactorLike(String value) {
            addCriterion("FIVE_YEAR_IMPACTOR_FACTOR like", value, "fiveYearImpactorFactor");
            return (Criteria) this;
        }

        public Criteria andFiveYearImpactorFactorNotLike(String value) {
            addCriterion("FIVE_YEAR_IMPACTOR_FACTOR not like", value, "fiveYearImpactorFactor");
            return (Criteria) this;
        }

        public Criteria andFiveYearImpactorFactorIn(List<String> values) {
            addCriterion("FIVE_YEAR_IMPACTOR_FACTOR in", values, "fiveYearImpactorFactor");
            return (Criteria) this;
        }

        public Criteria andFiveYearImpactorFactorNotIn(List<String> values) {
            addCriterion("FIVE_YEAR_IMPACTOR_FACTOR not in", values, "fiveYearImpactorFactor");
            return (Criteria) this;
        }

        public Criteria andFiveYearImpactorFactorBetween(String value1, String value2) {
            addCriterion("FIVE_YEAR_IMPACTOR_FACTOR between", value1, value2, "fiveYearImpactorFactor");
            return (Criteria) this;
        }

        public Criteria andFiveYearImpactorFactorNotBetween(String value1, String value2) {
            addCriterion("FIVE_YEAR_IMPACTOR_FACTOR not between", value1, value2, "fiveYearImpactorFactor");
            return (Criteria) this;
        }

        public Criteria andImmediacyIndexIsNull() {
            addCriterion("IMMEDIACY_INDEX is null");
            return (Criteria) this;
        }

        public Criteria andImmediacyIndexIsNotNull() {
            addCriterion("IMMEDIACY_INDEX is not null");
            return (Criteria) this;
        }

        public Criteria andImmediacyIndexEqualTo(String value) {
            addCriterion("IMMEDIACY_INDEX =", value, "immediacyIndex");
            return (Criteria) this;
        }

        public Criteria andImmediacyIndexNotEqualTo(String value) {
            addCriterion("IMMEDIACY_INDEX <>", value, "immediacyIndex");
            return (Criteria) this;
        }

        public Criteria andImmediacyIndexGreaterThan(String value) {
            addCriterion("IMMEDIACY_INDEX >", value, "immediacyIndex");
            return (Criteria) this;
        }

        public Criteria andImmediacyIndexGreaterThanOrEqualTo(String value) {
            addCriterion("IMMEDIACY_INDEX >=", value, "immediacyIndex");
            return (Criteria) this;
        }

        public Criteria andImmediacyIndexLessThan(String value) {
            addCriterion("IMMEDIACY_INDEX <", value, "immediacyIndex");
            return (Criteria) this;
        }

        public Criteria andImmediacyIndexLessThanOrEqualTo(String value) {
            addCriterion("IMMEDIACY_INDEX <=", value, "immediacyIndex");
            return (Criteria) this;
        }

        public Criteria andImmediacyIndexLike(String value) {
            addCriterion("IMMEDIACY_INDEX like", value, "immediacyIndex");
            return (Criteria) this;
        }

        public Criteria andImmediacyIndexNotLike(String value) {
            addCriterion("IMMEDIACY_INDEX not like", value, "immediacyIndex");
            return (Criteria) this;
        }

        public Criteria andImmediacyIndexIn(List<String> values) {
            addCriterion("IMMEDIACY_INDEX in", values, "immediacyIndex");
            return (Criteria) this;
        }

        public Criteria andImmediacyIndexNotIn(List<String> values) {
            addCriterion("IMMEDIACY_INDEX not in", values, "immediacyIndex");
            return (Criteria) this;
        }

        public Criteria andImmediacyIndexBetween(String value1, String value2) {
            addCriterion("IMMEDIACY_INDEX between", value1, value2, "immediacyIndex");
            return (Criteria) this;
        }

        public Criteria andImmediacyIndexNotBetween(String value1, String value2) {
            addCriterion("IMMEDIACY_INDEX not between", value1, value2, "immediacyIndex");
            return (Criteria) this;
        }

        public Criteria andArticlesIsNull() {
            addCriterion("ARTICLES is null");
            return (Criteria) this;
        }

        public Criteria andArticlesIsNotNull() {
            addCriterion("ARTICLES is not null");
            return (Criteria) this;
        }

        public Criteria andArticlesEqualTo(String value) {
            addCriterion("ARTICLES =", value, "articles");
            return (Criteria) this;
        }

        public Criteria andArticlesNotEqualTo(String value) {
            addCriterion("ARTICLES <>", value, "articles");
            return (Criteria) this;
        }

        public Criteria andArticlesGreaterThan(String value) {
            addCriterion("ARTICLES >", value, "articles");
            return (Criteria) this;
        }

        public Criteria andArticlesGreaterThanOrEqualTo(String value) {
            addCriterion("ARTICLES >=", value, "articles");
            return (Criteria) this;
        }

        public Criteria andArticlesLessThan(String value) {
            addCriterion("ARTICLES <", value, "articles");
            return (Criteria) this;
        }

        public Criteria andArticlesLessThanOrEqualTo(String value) {
            addCriterion("ARTICLES <=", value, "articles");
            return (Criteria) this;
        }

        public Criteria andArticlesLike(String value) {
            addCriterion("ARTICLES like", value, "articles");
            return (Criteria) this;
        }

        public Criteria andArticlesNotLike(String value) {
            addCriterion("ARTICLES not like", value, "articles");
            return (Criteria) this;
        }

        public Criteria andArticlesIn(List<String> values) {
            addCriterion("ARTICLES in", values, "articles");
            return (Criteria) this;
        }

        public Criteria andArticlesNotIn(List<String> values) {
            addCriterion("ARTICLES not in", values, "articles");
            return (Criteria) this;
        }

        public Criteria andArticlesBetween(String value1, String value2) {
            addCriterion("ARTICLES between", value1, value2, "articles");
            return (Criteria) this;
        }

        public Criteria andArticlesNotBetween(String value1, String value2) {
            addCriterion("ARTICLES not between", value1, value2, "articles");
            return (Criteria) this;
        }

        public Criteria andCitedHalflifeIsNull() {
            addCriterion("CITED_HALFLIFE is null");
            return (Criteria) this;
        }

        public Criteria andCitedHalflifeIsNotNull() {
            addCriterion("CITED_HALFLIFE is not null");
            return (Criteria) this;
        }

        public Criteria andCitedHalflifeEqualTo(String value) {
            addCriterion("CITED_HALFLIFE =", value, "citedHalflife");
            return (Criteria) this;
        }

        public Criteria andCitedHalflifeNotEqualTo(String value) {
            addCriterion("CITED_HALFLIFE <>", value, "citedHalflife");
            return (Criteria) this;
        }

        public Criteria andCitedHalflifeGreaterThan(String value) {
            addCriterion("CITED_HALFLIFE >", value, "citedHalflife");
            return (Criteria) this;
        }

        public Criteria andCitedHalflifeGreaterThanOrEqualTo(String value) {
            addCriterion("CITED_HALFLIFE >=", value, "citedHalflife");
            return (Criteria) this;
        }

        public Criteria andCitedHalflifeLessThan(String value) {
            addCriterion("CITED_HALFLIFE <", value, "citedHalflife");
            return (Criteria) this;
        }

        public Criteria andCitedHalflifeLessThanOrEqualTo(String value) {
            addCriterion("CITED_HALFLIFE <=", value, "citedHalflife");
            return (Criteria) this;
        }

        public Criteria andCitedHalflifeLike(String value) {
            addCriterion("CITED_HALFLIFE like", value, "citedHalflife");
            return (Criteria) this;
        }

        public Criteria andCitedHalflifeNotLike(String value) {
            addCriterion("CITED_HALFLIFE not like", value, "citedHalflife");
            return (Criteria) this;
        }

        public Criteria andCitedHalflifeIn(List<String> values) {
            addCriterion("CITED_HALFLIFE in", values, "citedHalflife");
            return (Criteria) this;
        }

        public Criteria andCitedHalflifeNotIn(List<String> values) {
            addCriterion("CITED_HALFLIFE not in", values, "citedHalflife");
            return (Criteria) this;
        }

        public Criteria andCitedHalflifeBetween(String value1, String value2) {
            addCriterion("CITED_HALFLIFE between", value1, value2, "citedHalflife");
            return (Criteria) this;
        }

        public Criteria andCitedHalflifeNotBetween(String value1, String value2) {
            addCriterion("CITED_HALFLIFE not between", value1, value2, "citedHalflife");
            return (Criteria) this;
        }

        public Criteria andEigenfactortmSourceIsNull() {
            addCriterion("EIGENFACTORTM_SOURCE is null");
            return (Criteria) this;
        }

        public Criteria andEigenfactortmSourceIsNotNull() {
            addCriterion("EIGENFACTORTM_SOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andEigenfactortmSourceEqualTo(String value) {
            addCriterion("EIGENFACTORTM_SOURCE =", value, "eigenfactortmSource");
            return (Criteria) this;
        }

        public Criteria andEigenfactortmSourceNotEqualTo(String value) {
            addCriterion("EIGENFACTORTM_SOURCE <>", value, "eigenfactortmSource");
            return (Criteria) this;
        }

        public Criteria andEigenfactortmSourceGreaterThan(String value) {
            addCriterion("EIGENFACTORTM_SOURCE >", value, "eigenfactortmSource");
            return (Criteria) this;
        }

        public Criteria andEigenfactortmSourceGreaterThanOrEqualTo(String value) {
            addCriterion("EIGENFACTORTM_SOURCE >=", value, "eigenfactortmSource");
            return (Criteria) this;
        }

        public Criteria andEigenfactortmSourceLessThan(String value) {
            addCriterion("EIGENFACTORTM_SOURCE <", value, "eigenfactortmSource");
            return (Criteria) this;
        }

        public Criteria andEigenfactortmSourceLessThanOrEqualTo(String value) {
            addCriterion("EIGENFACTORTM_SOURCE <=", value, "eigenfactortmSource");
            return (Criteria) this;
        }

        public Criteria andEigenfactortmSourceLike(String value) {
            addCriterion("EIGENFACTORTM_SOURCE like", value, "eigenfactortmSource");
            return (Criteria) this;
        }

        public Criteria andEigenfactortmSourceNotLike(String value) {
            addCriterion("EIGENFACTORTM_SOURCE not like", value, "eigenfactortmSource");
            return (Criteria) this;
        }

        public Criteria andEigenfactortmSourceIn(List<String> values) {
            addCriterion("EIGENFACTORTM_SOURCE in", values, "eigenfactortmSource");
            return (Criteria) this;
        }

        public Criteria andEigenfactortmSourceNotIn(List<String> values) {
            addCriterion("EIGENFACTORTM_SOURCE not in", values, "eigenfactortmSource");
            return (Criteria) this;
        }

        public Criteria andEigenfactortmSourceBetween(String value1, String value2) {
            addCriterion("EIGENFACTORTM_SOURCE between", value1, value2, "eigenfactortmSource");
            return (Criteria) this;
        }

        public Criteria andEigenfactortmSourceNotBetween(String value1, String value2) {
            addCriterion("EIGENFACTORTM_SOURCE not between", value1, value2, "eigenfactortmSource");
            return (Criteria) this;
        }

        public Criteria andInfluencetmSourceIsNull() {
            addCriterion("INFLUENCETM_SOURCE is null");
            return (Criteria) this;
        }

        public Criteria andInfluencetmSourceIsNotNull() {
            addCriterion("INFLUENCETM_SOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andInfluencetmSourceEqualTo(String value) {
            addCriterion("INFLUENCETM_SOURCE =", value, "influencetmSource");
            return (Criteria) this;
        }

        public Criteria andInfluencetmSourceNotEqualTo(String value) {
            addCriterion("INFLUENCETM_SOURCE <>", value, "influencetmSource");
            return (Criteria) this;
        }

        public Criteria andInfluencetmSourceGreaterThan(String value) {
            addCriterion("INFLUENCETM_SOURCE >", value, "influencetmSource");
            return (Criteria) this;
        }

        public Criteria andInfluencetmSourceGreaterThanOrEqualTo(String value) {
            addCriterion("INFLUENCETM_SOURCE >=", value, "influencetmSource");
            return (Criteria) this;
        }

        public Criteria andInfluencetmSourceLessThan(String value) {
            addCriterion("INFLUENCETM_SOURCE <", value, "influencetmSource");
            return (Criteria) this;
        }

        public Criteria andInfluencetmSourceLessThanOrEqualTo(String value) {
            addCriterion("INFLUENCETM_SOURCE <=", value, "influencetmSource");
            return (Criteria) this;
        }

        public Criteria andInfluencetmSourceLike(String value) {
            addCriterion("INFLUENCETM_SOURCE like", value, "influencetmSource");
            return (Criteria) this;
        }

        public Criteria andInfluencetmSourceNotLike(String value) {
            addCriterion("INFLUENCETM_SOURCE not like", value, "influencetmSource");
            return (Criteria) this;
        }

        public Criteria andInfluencetmSourceIn(List<String> values) {
            addCriterion("INFLUENCETM_SOURCE in", values, "influencetmSource");
            return (Criteria) this;
        }

        public Criteria andInfluencetmSourceNotIn(List<String> values) {
            addCriterion("INFLUENCETM_SOURCE not in", values, "influencetmSource");
            return (Criteria) this;
        }

        public Criteria andInfluencetmSourceBetween(String value1, String value2) {
            addCriterion("INFLUENCETM_SOURCE between", value1, value2, "influencetmSource");
            return (Criteria) this;
        }

        public Criteria andInfluencetmSourceNotBetween(String value1, String value2) {
            addCriterion("INFLUENCETM_SOURCE not between", value1, value2, "influencetmSource");
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