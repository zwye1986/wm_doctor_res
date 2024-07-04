package com.pinde.res.model.njmu.mo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CasediscussExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table dbo.CaseDiscuss
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table dbo.CaseDiscuss
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table dbo.CaseDiscuss
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CaseDiscuss
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	public CasediscussExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CaseDiscuss
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CaseDiscuss
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CaseDiscuss
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CaseDiscuss
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CaseDiscuss
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CaseDiscuss
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CaseDiscuss
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CaseDiscuss
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CaseDiscuss
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CaseDiscuss
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table dbo.CaseDiscuss
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
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

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andCadisidIsNull() {
			addCriterion("CaDisID is null");
			return (Criteria) this;
		}

		public Criteria andCadisidIsNotNull() {
			addCriterion("CaDisID is not null");
			return (Criteria) this;
		}

		public Criteria andCadisidEqualTo(Integer value) {
			addCriterion("CaDisID =", value, "cadisid");
			return (Criteria) this;
		}

		public Criteria andCadisidNotEqualTo(Integer value) {
			addCriterion("CaDisID <>", value, "cadisid");
			return (Criteria) this;
		}

		public Criteria andCadisidGreaterThan(Integer value) {
			addCriterion("CaDisID >", value, "cadisid");
			return (Criteria) this;
		}

		public Criteria andCadisidGreaterThanOrEqualTo(Integer value) {
			addCriterion("CaDisID >=", value, "cadisid");
			return (Criteria) this;
		}

		public Criteria andCadisidLessThan(Integer value) {
			addCriterion("CaDisID <", value, "cadisid");
			return (Criteria) this;
		}

		public Criteria andCadisidLessThanOrEqualTo(Integer value) {
			addCriterion("CaDisID <=", value, "cadisid");
			return (Criteria) this;
		}

		public Criteria andCadisidIn(List<Integer> values) {
			addCriterion("CaDisID in", values, "cadisid");
			return (Criteria) this;
		}

		public Criteria andCadisidNotIn(List<Integer> values) {
			addCriterion("CaDisID not in", values, "cadisid");
			return (Criteria) this;
		}

		public Criteria andCadisidBetween(Integer value1, Integer value2) {
			addCriterion("CaDisID between", value1, value2, "cadisid");
			return (Criteria) this;
		}

		public Criteria andCadisidNotBetween(Integer value1, Integer value2) {
			addCriterion("CaDisID not between", value1, value2, "cadisid");
			return (Criteria) this;
		}

		public Criteria andHossecidIsNull() {
			addCriterion("HosSecID is null");
			return (Criteria) this;
		}

		public Criteria andHossecidIsNotNull() {
			addCriterion("HosSecID is not null");
			return (Criteria) this;
		}

		public Criteria andHossecidEqualTo(Integer value) {
			addCriterion("HosSecID =", value, "hossecid");
			return (Criteria) this;
		}

		public Criteria andHossecidNotEqualTo(Integer value) {
			addCriterion("HosSecID <>", value, "hossecid");
			return (Criteria) this;
		}

		public Criteria andHossecidGreaterThan(Integer value) {
			addCriterion("HosSecID >", value, "hossecid");
			return (Criteria) this;
		}

		public Criteria andHossecidGreaterThanOrEqualTo(Integer value) {
			addCriterion("HosSecID >=", value, "hossecid");
			return (Criteria) this;
		}

		public Criteria andHossecidLessThan(Integer value) {
			addCriterion("HosSecID <", value, "hossecid");
			return (Criteria) this;
		}

		public Criteria andHossecidLessThanOrEqualTo(Integer value) {
			addCriterion("HosSecID <=", value, "hossecid");
			return (Criteria) this;
		}

		public Criteria andHossecidIn(List<Integer> values) {
			addCriterion("HosSecID in", values, "hossecid");
			return (Criteria) this;
		}

		public Criteria andHossecidNotIn(List<Integer> values) {
			addCriterion("HosSecID not in", values, "hossecid");
			return (Criteria) this;
		}

		public Criteria andHossecidBetween(Integer value1, Integer value2) {
			addCriterion("HosSecID between", value1, value2, "hossecid");
			return (Criteria) this;
		}

		public Criteria andHossecidNotBetween(Integer value1, Integer value2) {
			addCriterion("HosSecID not between", value1, value2, "hossecid");
			return (Criteria) this;
		}

		public Criteria andCysecidIsNull() {
			addCriterion("CySecID is null");
			return (Criteria) this;
		}

		public Criteria andCysecidIsNotNull() {
			addCriterion("CySecID is not null");
			return (Criteria) this;
		}

		public Criteria andCysecidEqualTo(Integer value) {
			addCriterion("CySecID =", value, "cysecid");
			return (Criteria) this;
		}

		public Criteria andCysecidNotEqualTo(Integer value) {
			addCriterion("CySecID <>", value, "cysecid");
			return (Criteria) this;
		}

		public Criteria andCysecidGreaterThan(Integer value) {
			addCriterion("CySecID >", value, "cysecid");
			return (Criteria) this;
		}

		public Criteria andCysecidGreaterThanOrEqualTo(Integer value) {
			addCriterion("CySecID >=", value, "cysecid");
			return (Criteria) this;
		}

		public Criteria andCysecidLessThan(Integer value) {
			addCriterion("CySecID <", value, "cysecid");
			return (Criteria) this;
		}

		public Criteria andCysecidLessThanOrEqualTo(Integer value) {
			addCriterion("CySecID <=", value, "cysecid");
			return (Criteria) this;
		}

		public Criteria andCysecidIn(List<Integer> values) {
			addCriterion("CySecID in", values, "cysecid");
			return (Criteria) this;
		}

		public Criteria andCysecidNotIn(List<Integer> values) {
			addCriterion("CySecID not in", values, "cysecid");
			return (Criteria) this;
		}

		public Criteria andCysecidBetween(Integer value1, Integer value2) {
			addCriterion("CySecID between", value1, value2, "cysecid");
			return (Criteria) this;
		}

		public Criteria andCysecidNotBetween(Integer value1, Integer value2) {
			addCriterion("CySecID not between", value1, value2, "cysecid");
			return (Criteria) this;
		}

		public Criteria andCadistimeIsNull() {
			addCriterion("CaDisTime is null");
			return (Criteria) this;
		}

		public Criteria andCadistimeIsNotNull() {
			addCriterion("CaDisTime is not null");
			return (Criteria) this;
		}

		public Criteria andCadistimeEqualTo(Date value) {
			addCriterion("CaDisTime =", value, "cadistime");
			return (Criteria) this;
		}

		public Criteria andCadistimeNotEqualTo(Date value) {
			addCriterion("CaDisTime <>", value, "cadistime");
			return (Criteria) this;
		}

		public Criteria andCadistimeGreaterThan(Date value) {
			addCriterion("CaDisTime >", value, "cadistime");
			return (Criteria) this;
		}

		public Criteria andCadistimeGreaterThanOrEqualTo(Date value) {
			addCriterion("CaDisTime >=", value, "cadistime");
			return (Criteria) this;
		}

		public Criteria andCadistimeLessThan(Date value) {
			addCriterion("CaDisTime <", value, "cadistime");
			return (Criteria) this;
		}

		public Criteria andCadistimeLessThanOrEqualTo(Date value) {
			addCriterion("CaDisTime <=", value, "cadistime");
			return (Criteria) this;
		}

		public Criteria andCadistimeIn(List<Date> values) {
			addCriterion("CaDisTime in", values, "cadistime");
			return (Criteria) this;
		}

		public Criteria andCadistimeNotIn(List<Date> values) {
			addCriterion("CaDisTime not in", values, "cadistime");
			return (Criteria) this;
		}

		public Criteria andCadistimeBetween(Date value1, Date value2) {
			addCriterion("CaDisTime between", value1, value2, "cadistime");
			return (Criteria) this;
		}

		public Criteria andCadistimeNotBetween(Date value1, Date value2) {
			addCriterion("CaDisTime not between", value1, value2, "cadistime");
			return (Criteria) this;
		}

		public Criteria andCadiscontentIsNull() {
			addCriterion("CaDisContent is null");
			return (Criteria) this;
		}

		public Criteria andCadiscontentIsNotNull() {
			addCriterion("CaDisContent is not null");
			return (Criteria) this;
		}

		public Criteria andCadiscontentEqualTo(String value) {
			addCriterion("CaDisContent =", value, "cadiscontent");
			return (Criteria) this;
		}

		public Criteria andCadiscontentNotEqualTo(String value) {
			addCriterion("CaDisContent <>", value, "cadiscontent");
			return (Criteria) this;
		}

		public Criteria andCadiscontentGreaterThan(String value) {
			addCriterion("CaDisContent >", value, "cadiscontent");
			return (Criteria) this;
		}

		public Criteria andCadiscontentGreaterThanOrEqualTo(String value) {
			addCriterion("CaDisContent >=", value, "cadiscontent");
			return (Criteria) this;
		}

		public Criteria andCadiscontentLessThan(String value) {
			addCriterion("CaDisContent <", value, "cadiscontent");
			return (Criteria) this;
		}

		public Criteria andCadiscontentLessThanOrEqualTo(String value) {
			addCriterion("CaDisContent <=", value, "cadiscontent");
			return (Criteria) this;
		}

		public Criteria andCadiscontentLike(String value) {
			addCriterion("CaDisContent like", value, "cadiscontent");
			return (Criteria) this;
		}

		public Criteria andCadiscontentNotLike(String value) {
			addCriterion("CaDisContent not like", value, "cadiscontent");
			return (Criteria) this;
		}

		public Criteria andCadiscontentIn(List<String> values) {
			addCriterion("CaDisContent in", values, "cadiscontent");
			return (Criteria) this;
		}

		public Criteria andCadiscontentNotIn(List<String> values) {
			addCriterion("CaDisContent not in", values, "cadiscontent");
			return (Criteria) this;
		}

		public Criteria andCadiscontentBetween(String value1, String value2) {
			addCriterion("CaDisContent between", value1, value2, "cadiscontent");
			return (Criteria) this;
		}

		public Criteria andCadiscontentNotBetween(String value1, String value2) {
			addCriterion("CaDisContent not between", value1, value2,
					"cadiscontent");
			return (Criteria) this;
		}

		public Criteria andCadisplayclassIsNull() {
			addCriterion("CaDisPlayClass is null");
			return (Criteria) this;
		}

		public Criteria andCadisplayclassIsNotNull() {
			addCriterion("CaDisPlayClass is not null");
			return (Criteria) this;
		}

		public Criteria andCadisplayclassEqualTo(String value) {
			addCriterion("CaDisPlayClass =", value, "cadisplayclass");
			return (Criteria) this;
		}

		public Criteria andCadisplayclassNotEqualTo(String value) {
			addCriterion("CaDisPlayClass <>", value, "cadisplayclass");
			return (Criteria) this;
		}

		public Criteria andCadisplayclassGreaterThan(String value) {
			addCriterion("CaDisPlayClass >", value, "cadisplayclass");
			return (Criteria) this;
		}

		public Criteria andCadisplayclassGreaterThanOrEqualTo(String value) {
			addCriterion("CaDisPlayClass >=", value, "cadisplayclass");
			return (Criteria) this;
		}

		public Criteria andCadisplayclassLessThan(String value) {
			addCriterion("CaDisPlayClass <", value, "cadisplayclass");
			return (Criteria) this;
		}

		public Criteria andCadisplayclassLessThanOrEqualTo(String value) {
			addCriterion("CaDisPlayClass <=", value, "cadisplayclass");
			return (Criteria) this;
		}

		public Criteria andCadisplayclassLike(String value) {
			addCriterion("CaDisPlayClass like", value, "cadisplayclass");
			return (Criteria) this;
		}

		public Criteria andCadisplayclassNotLike(String value) {
			addCriterion("CaDisPlayClass not like", value, "cadisplayclass");
			return (Criteria) this;
		}

		public Criteria andCadisplayclassIn(List<String> values) {
			addCriterion("CaDisPlayClass in", values, "cadisplayclass");
			return (Criteria) this;
		}

		public Criteria andCadisplayclassNotIn(List<String> values) {
			addCriterion("CaDisPlayClass not in", values, "cadisplayclass");
			return (Criteria) this;
		}

		public Criteria andCadisplayclassBetween(String value1, String value2) {
			addCriterion("CaDisPlayClass between", value1, value2,
					"cadisplayclass");
			return (Criteria) this;
		}

		public Criteria andCadisplayclassNotBetween(String value1, String value2) {
			addCriterion("CaDisPlayClass not between", value1, value2,
					"cadisplayclass");
			return (Criteria) this;
		}

		public Criteria andCadisperiodIsNull() {
			addCriterion("CaDisPeriod is null");
			return (Criteria) this;
		}

		public Criteria andCadisperiodIsNotNull() {
			addCriterion("CaDisPeriod is not null");
			return (Criteria) this;
		}

		public Criteria andCadisperiodEqualTo(BigDecimal value) {
			addCriterion("CaDisPeriod =", value, "cadisperiod");
			return (Criteria) this;
		}

		public Criteria andCadisperiodNotEqualTo(BigDecimal value) {
			addCriterion("CaDisPeriod <>", value, "cadisperiod");
			return (Criteria) this;
		}

		public Criteria andCadisperiodGreaterThan(BigDecimal value) {
			addCriterion("CaDisPeriod >", value, "cadisperiod");
			return (Criteria) this;
		}

		public Criteria andCadisperiodGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("CaDisPeriod >=", value, "cadisperiod");
			return (Criteria) this;
		}

		public Criteria andCadisperiodLessThan(BigDecimal value) {
			addCriterion("CaDisPeriod <", value, "cadisperiod");
			return (Criteria) this;
		}

		public Criteria andCadisperiodLessThanOrEqualTo(BigDecimal value) {
			addCriterion("CaDisPeriod <=", value, "cadisperiod");
			return (Criteria) this;
		}

		public Criteria andCadisperiodIn(List<BigDecimal> values) {
			addCriterion("CaDisPeriod in", values, "cadisperiod");
			return (Criteria) this;
		}

		public Criteria andCadisperiodNotIn(List<BigDecimal> values) {
			addCriterion("CaDisPeriod not in", values, "cadisperiod");
			return (Criteria) this;
		}

		public Criteria andCadisperiodBetween(BigDecimal value1,
				BigDecimal value2) {
			addCriterion("CaDisPeriod between", value1, value2, "cadisperiod");
			return (Criteria) this;
		}

		public Criteria andCadisperiodNotBetween(BigDecimal value1,
				BigDecimal value2) {
			addCriterion("CaDisPeriod not between", value1, value2,
					"cadisperiod");
			return (Criteria) this;
		}

		public Criteria andCadismainspeakerIsNull() {
			addCriterion("CaDisMainSpeaker is null");
			return (Criteria) this;
		}

		public Criteria andCadismainspeakerIsNotNull() {
			addCriterion("CaDisMainSpeaker is not null");
			return (Criteria) this;
		}

		public Criteria andCadismainspeakerEqualTo(String value) {
			addCriterion("CaDisMainSpeaker =", value, "cadismainspeaker");
			return (Criteria) this;
		}

		public Criteria andCadismainspeakerNotEqualTo(String value) {
			addCriterion("CaDisMainSpeaker <>", value, "cadismainspeaker");
			return (Criteria) this;
		}

		public Criteria andCadismainspeakerGreaterThan(String value) {
			addCriterion("CaDisMainSpeaker >", value, "cadismainspeaker");
			return (Criteria) this;
		}

		public Criteria andCadismainspeakerGreaterThanOrEqualTo(String value) {
			addCriterion("CaDisMainSpeaker >=", value, "cadismainspeaker");
			return (Criteria) this;
		}

		public Criteria andCadismainspeakerLessThan(String value) {
			addCriterion("CaDisMainSpeaker <", value, "cadismainspeaker");
			return (Criteria) this;
		}

		public Criteria andCadismainspeakerLessThanOrEqualTo(String value) {
			addCriterion("CaDisMainSpeaker <=", value, "cadismainspeaker");
			return (Criteria) this;
		}

		public Criteria andCadismainspeakerLike(String value) {
			addCriterion("CaDisMainSpeaker like", value, "cadismainspeaker");
			return (Criteria) this;
		}

		public Criteria andCadismainspeakerNotLike(String value) {
			addCriterion("CaDisMainSpeaker not like", value, "cadismainspeaker");
			return (Criteria) this;
		}

		public Criteria andCadismainspeakerIn(List<String> values) {
			addCriterion("CaDisMainSpeaker in", values, "cadismainspeaker");
			return (Criteria) this;
		}

		public Criteria andCadismainspeakerNotIn(List<String> values) {
			addCriterion("CaDisMainSpeaker not in", values, "cadismainspeaker");
			return (Criteria) this;
		}

		public Criteria andCadismainspeakerBetween(String value1, String value2) {
			addCriterion("CaDisMainSpeaker between", value1, value2,
					"cadismainspeaker");
			return (Criteria) this;
		}

		public Criteria andCadismainspeakerNotBetween(String value1,
				String value2) {
			addCriterion("CaDisMainSpeaker not between", value1, value2,
					"cadismainspeaker");
			return (Criteria) this;
		}

		public Criteria andCheckstatusIsNull() {
			addCriterion("CheckStatus is null");
			return (Criteria) this;
		}

		public Criteria andCheckstatusIsNotNull() {
			addCriterion("CheckStatus is not null");
			return (Criteria) this;
		}

		public Criteria andCheckstatusEqualTo(Integer value) {
			addCriterion("CheckStatus =", value, "checkstatus");
			return (Criteria) this;
		}

		public Criteria andCheckstatusNotEqualTo(Integer value) {
			addCriterion("CheckStatus <>", value, "checkstatus");
			return (Criteria) this;
		}

		public Criteria andCheckstatusGreaterThan(Integer value) {
			addCriterion("CheckStatus >", value, "checkstatus");
			return (Criteria) this;
		}

		public Criteria andCheckstatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("CheckStatus >=", value, "checkstatus");
			return (Criteria) this;
		}

		public Criteria andCheckstatusLessThan(Integer value) {
			addCriterion("CheckStatus <", value, "checkstatus");
			return (Criteria) this;
		}

		public Criteria andCheckstatusLessThanOrEqualTo(Integer value) {
			addCriterion("CheckStatus <=", value, "checkstatus");
			return (Criteria) this;
		}

		public Criteria andCheckstatusIn(List<Integer> values) {
			addCriterion("CheckStatus in", values, "checkstatus");
			return (Criteria) this;
		}

		public Criteria andCheckstatusNotIn(List<Integer> values) {
			addCriterion("CheckStatus not in", values, "checkstatus");
			return (Criteria) this;
		}

		public Criteria andCheckstatusBetween(Integer value1, Integer value2) {
			addCriterion("CheckStatus between", value1, value2, "checkstatus");
			return (Criteria) this;
		}

		public Criteria andCheckstatusNotBetween(Integer value1, Integer value2) {
			addCriterion("CheckStatus not between", value1, value2,
					"checkstatus");
			return (Criteria) this;
		}

		public Criteria andCadisdesIsNull() {
			addCriterion("CaDisDes is null");
			return (Criteria) this;
		}

		public Criteria andCadisdesIsNotNull() {
			addCriterion("CaDisDes is not null");
			return (Criteria) this;
		}

		public Criteria andCadisdesEqualTo(String value) {
			addCriterion("CaDisDes =", value, "cadisdes");
			return (Criteria) this;
		}

		public Criteria andCadisdesNotEqualTo(String value) {
			addCriterion("CaDisDes <>", value, "cadisdes");
			return (Criteria) this;
		}

		public Criteria andCadisdesGreaterThan(String value) {
			addCriterion("CaDisDes >", value, "cadisdes");
			return (Criteria) this;
		}

		public Criteria andCadisdesGreaterThanOrEqualTo(String value) {
			addCriterion("CaDisDes >=", value, "cadisdes");
			return (Criteria) this;
		}

		public Criteria andCadisdesLessThan(String value) {
			addCriterion("CaDisDes <", value, "cadisdes");
			return (Criteria) this;
		}

		public Criteria andCadisdesLessThanOrEqualTo(String value) {
			addCriterion("CaDisDes <=", value, "cadisdes");
			return (Criteria) this;
		}

		public Criteria andCadisdesLike(String value) {
			addCriterion("CaDisDes like", value, "cadisdes");
			return (Criteria) this;
		}

		public Criteria andCadisdesNotLike(String value) {
			addCriterion("CaDisDes not like", value, "cadisdes");
			return (Criteria) this;
		}

		public Criteria andCadisdesIn(List<String> values) {
			addCriterion("CaDisDes in", values, "cadisdes");
			return (Criteria) this;
		}

		public Criteria andCadisdesNotIn(List<String> values) {
			addCriterion("CaDisDes not in", values, "cadisdes");
			return (Criteria) this;
		}

		public Criteria andCadisdesBetween(String value1, String value2) {
			addCriterion("CaDisDes between", value1, value2, "cadisdes");
			return (Criteria) this;
		}

		public Criteria andCadisdesNotBetween(String value1, String value2) {
			addCriterion("CaDisDes not between", value1, value2, "cadisdes");
			return (Criteria) this;
		}

		public Criteria andUseridIsNull() {
			addCriterion("UserID is null");
			return (Criteria) this;
		}

		public Criteria andUseridIsNotNull() {
			addCriterion("UserID is not null");
			return (Criteria) this;
		}

		public Criteria andUseridEqualTo(Integer value) {
			addCriterion("UserID =", value, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridNotEqualTo(Integer value) {
			addCriterion("UserID <>", value, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridGreaterThan(Integer value) {
			addCriterion("UserID >", value, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
			addCriterion("UserID >=", value, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridLessThan(Integer value) {
			addCriterion("UserID <", value, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridLessThanOrEqualTo(Integer value) {
			addCriterion("UserID <=", value, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridIn(List<Integer> values) {
			addCriterion("UserID in", values, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridNotIn(List<Integer> values) {
			addCriterion("UserID not in", values, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridBetween(Integer value1, Integer value2) {
			addCriterion("UserID between", value1, value2, "userid");
			return (Criteria) this;
		}

		public Criteria andUseridNotBetween(Integer value1, Integer value2) {
			addCriterion("UserID not between", value1, value2, "userid");
			return (Criteria) this;
		}

		public Criteria andDocgrpidIsNull() {
			addCriterion("DocGRPID is null");
			return (Criteria) this;
		}

		public Criteria andDocgrpidIsNotNull() {
			addCriterion("DocGRPID is not null");
			return (Criteria) this;
		}

		public Criteria andDocgrpidEqualTo(Integer value) {
			addCriterion("DocGRPID =", value, "docgrpid");
			return (Criteria) this;
		}

		public Criteria andDocgrpidNotEqualTo(Integer value) {
			addCriterion("DocGRPID <>", value, "docgrpid");
			return (Criteria) this;
		}

		public Criteria andDocgrpidGreaterThan(Integer value) {
			addCriterion("DocGRPID >", value, "docgrpid");
			return (Criteria) this;
		}

		public Criteria andDocgrpidGreaterThanOrEqualTo(Integer value) {
			addCriterion("DocGRPID >=", value, "docgrpid");
			return (Criteria) this;
		}

		public Criteria andDocgrpidLessThan(Integer value) {
			addCriterion("DocGRPID <", value, "docgrpid");
			return (Criteria) this;
		}

		public Criteria andDocgrpidLessThanOrEqualTo(Integer value) {
			addCriterion("DocGRPID <=", value, "docgrpid");
			return (Criteria) this;
		}

		public Criteria andDocgrpidIn(List<Integer> values) {
			addCriterion("DocGRPID in", values, "docgrpid");
			return (Criteria) this;
		}

		public Criteria andDocgrpidNotIn(List<Integer> values) {
			addCriterion("DocGRPID not in", values, "docgrpid");
			return (Criteria) this;
		}

		public Criteria andDocgrpidBetween(Integer value1, Integer value2) {
			addCriterion("DocGRPID between", value1, value2, "docgrpid");
			return (Criteria) this;
		}

		public Criteria andDocgrpidNotBetween(Integer value1, Integer value2) {
			addCriterion("DocGRPID not between", value1, value2, "docgrpid");
			return (Criteria) this;
		}

		public Criteria andDoccountIsNull() {
			addCriterion("DocCount is null");
			return (Criteria) this;
		}

		public Criteria andDoccountIsNotNull() {
			addCriterion("DocCount is not null");
			return (Criteria) this;
		}

		public Criteria andDoccountEqualTo(Integer value) {
			addCriterion("DocCount =", value, "doccount");
			return (Criteria) this;
		}

		public Criteria andDoccountNotEqualTo(Integer value) {
			addCriterion("DocCount <>", value, "doccount");
			return (Criteria) this;
		}

		public Criteria andDoccountGreaterThan(Integer value) {
			addCriterion("DocCount >", value, "doccount");
			return (Criteria) this;
		}

		public Criteria andDoccountGreaterThanOrEqualTo(Integer value) {
			addCriterion("DocCount >=", value, "doccount");
			return (Criteria) this;
		}

		public Criteria andDoccountLessThan(Integer value) {
			addCriterion("DocCount <", value, "doccount");
			return (Criteria) this;
		}

		public Criteria andDoccountLessThanOrEqualTo(Integer value) {
			addCriterion("DocCount <=", value, "doccount");
			return (Criteria) this;
		}

		public Criteria andDoccountIn(List<Integer> values) {
			addCriterion("DocCount in", values, "doccount");
			return (Criteria) this;
		}

		public Criteria andDoccountNotIn(List<Integer> values) {
			addCriterion("DocCount not in", values, "doccount");
			return (Criteria) this;
		}

		public Criteria andDoccountBetween(Integer value1, Integer value2) {
			addCriterion("DocCount between", value1, value2, "doccount");
			return (Criteria) this;
		}

		public Criteria andDoccountNotBetween(Integer value1, Integer value2) {
			addCriterion("DocCount not between", value1, value2, "doccount");
			return (Criteria) this;
		}

		public Criteria andCanameIsNull() {
			addCriterion("CaName is null");
			return (Criteria) this;
		}

		public Criteria andCanameIsNotNull() {
			addCriterion("CaName is not null");
			return (Criteria) this;
		}

		public Criteria andCanameEqualTo(String value) {
			addCriterion("CaName =", value, "caname");
			return (Criteria) this;
		}

		public Criteria andCanameNotEqualTo(String value) {
			addCriterion("CaName <>", value, "caname");
			return (Criteria) this;
		}

		public Criteria andCanameGreaterThan(String value) {
			addCriterion("CaName >", value, "caname");
			return (Criteria) this;
		}

		public Criteria andCanameGreaterThanOrEqualTo(String value) {
			addCriterion("CaName >=", value, "caname");
			return (Criteria) this;
		}

		public Criteria andCanameLessThan(String value) {
			addCriterion("CaName <", value, "caname");
			return (Criteria) this;
		}

		public Criteria andCanameLessThanOrEqualTo(String value) {
			addCriterion("CaName <=", value, "caname");
			return (Criteria) this;
		}

		public Criteria andCanameLike(String value) {
			addCriterion("CaName like", value, "caname");
			return (Criteria) this;
		}

		public Criteria andCanameNotLike(String value) {
			addCriterion("CaName not like", value, "caname");
			return (Criteria) this;
		}

		public Criteria andCanameIn(List<String> values) {
			addCriterion("CaName in", values, "caname");
			return (Criteria) this;
		}

		public Criteria andCanameNotIn(List<String> values) {
			addCriterion("CaName not in", values, "caname");
			return (Criteria) this;
		}

		public Criteria andCanameBetween(String value1, String value2) {
			addCriterion("CaName between", value1, value2, "caname");
			return (Criteria) this;
		}

		public Criteria andCanameNotBetween(String value1, String value2) {
			addCriterion("CaName not between", value1, value2, "caname");
			return (Criteria) this;
		}

		public Criteria andCaaddressIsNull() {
			addCriterion("CaAddress is null");
			return (Criteria) this;
		}

		public Criteria andCaaddressIsNotNull() {
			addCriterion("CaAddress is not null");
			return (Criteria) this;
		}

		public Criteria andCaaddressEqualTo(String value) {
			addCriterion("CaAddress =", value, "caaddress");
			return (Criteria) this;
		}

		public Criteria andCaaddressNotEqualTo(String value) {
			addCriterion("CaAddress <>", value, "caaddress");
			return (Criteria) this;
		}

		public Criteria andCaaddressGreaterThan(String value) {
			addCriterion("CaAddress >", value, "caaddress");
			return (Criteria) this;
		}

		public Criteria andCaaddressGreaterThanOrEqualTo(String value) {
			addCriterion("CaAddress >=", value, "caaddress");
			return (Criteria) this;
		}

		public Criteria andCaaddressLessThan(String value) {
			addCriterion("CaAddress <", value, "caaddress");
			return (Criteria) this;
		}

		public Criteria andCaaddressLessThanOrEqualTo(String value) {
			addCriterion("CaAddress <=", value, "caaddress");
			return (Criteria) this;
		}

		public Criteria andCaaddressLike(String value) {
			addCriterion("CaAddress like", value, "caaddress");
			return (Criteria) this;
		}

		public Criteria andCaaddressNotLike(String value) {
			addCriterion("CaAddress not like", value, "caaddress");
			return (Criteria) this;
		}

		public Criteria andCaaddressIn(List<String> values) {
			addCriterion("CaAddress in", values, "caaddress");
			return (Criteria) this;
		}

		public Criteria andCaaddressNotIn(List<String> values) {
			addCriterion("CaAddress not in", values, "caaddress");
			return (Criteria) this;
		}

		public Criteria andCaaddressBetween(String value1, String value2) {
			addCriterion("CaAddress between", value1, value2, "caaddress");
			return (Criteria) this;
		}

		public Criteria andCaaddressNotBetween(String value1, String value2) {
			addCriterion("CaAddress not between", value1, value2, "caaddress");
			return (Criteria) this;
		}

		public Criteria andCapeopleIsNull() {
			addCriterion("CaPeople is null");
			return (Criteria) this;
		}

		public Criteria andCapeopleIsNotNull() {
			addCriterion("CaPeople is not null");
			return (Criteria) this;
		}

		public Criteria andCapeopleEqualTo(String value) {
			addCriterion("CaPeople =", value, "capeople");
			return (Criteria) this;
		}

		public Criteria andCapeopleNotEqualTo(String value) {
			addCriterion("CaPeople <>", value, "capeople");
			return (Criteria) this;
		}

		public Criteria andCapeopleGreaterThan(String value) {
			addCriterion("CaPeople >", value, "capeople");
			return (Criteria) this;
		}

		public Criteria andCapeopleGreaterThanOrEqualTo(String value) {
			addCriterion("CaPeople >=", value, "capeople");
			return (Criteria) this;
		}

		public Criteria andCapeopleLessThan(String value) {
			addCriterion("CaPeople <", value, "capeople");
			return (Criteria) this;
		}

		public Criteria andCapeopleLessThanOrEqualTo(String value) {
			addCriterion("CaPeople <=", value, "capeople");
			return (Criteria) this;
		}

		public Criteria andCapeopleLike(String value) {
			addCriterion("CaPeople like", value, "capeople");
			return (Criteria) this;
		}

		public Criteria andCapeopleNotLike(String value) {
			addCriterion("CaPeople not like", value, "capeople");
			return (Criteria) this;
		}

		public Criteria andCapeopleIn(List<String> values) {
			addCriterion("CaPeople in", values, "capeople");
			return (Criteria) this;
		}

		public Criteria andCapeopleNotIn(List<String> values) {
			addCriterion("CaPeople not in", values, "capeople");
			return (Criteria) this;
		}

		public Criteria andCapeopleBetween(String value1, String value2) {
			addCriterion("CaPeople between", value1, value2, "capeople");
			return (Criteria) this;
		}

		public Criteria andCapeopleNotBetween(String value1, String value2) {
			addCriterion("CaPeople not between", value1, value2, "capeople");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table dbo.CaseDiscuss
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
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

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
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

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table dbo.CaseDiscuss
     *
     * @mbggenerated do_not_delete_during_merge Fri Jan 30 09:32:58 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}