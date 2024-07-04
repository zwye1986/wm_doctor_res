package com.pinde.res.model.njmu.mo;

import java.util.ArrayList;
import java.util.List;

public class UsersectionmanagerExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table dbo.UserSectionManager
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table dbo.UserSectionManager
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table dbo.UserSectionManager
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.UserSectionManager
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	public UsersectionmanagerExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.UserSectionManager
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.UserSectionManager
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.UserSectionManager
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.UserSectionManager
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.UserSectionManager
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.UserSectionManager
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.UserSectionManager
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.UserSectionManager
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.UserSectionManager
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.UserSectionManager
	 * @mbggenerated  Fri Feb 27 17:26:51 CST 2015
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table dbo.UserSectionManager
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

		public Criteria andIdIsNull() {
			addCriterion("ID is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("ID is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("ID =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("ID <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("ID >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("ID >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("ID <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("ID <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("ID in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("ID not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("ID between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("ID not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andStudentidIsNull() {
			addCriterion("StudentID is null");
			return (Criteria) this;
		}

		public Criteria andStudentidIsNotNull() {
			addCriterion("StudentID is not null");
			return (Criteria) this;
		}

		public Criteria andStudentidEqualTo(Integer value) {
			addCriterion("StudentID =", value, "studentid");
			return (Criteria) this;
		}

		public Criteria andStudentidNotEqualTo(Integer value) {
			addCriterion("StudentID <>", value, "studentid");
			return (Criteria) this;
		}

		public Criteria andStudentidGreaterThan(Integer value) {
			addCriterion("StudentID >", value, "studentid");
			return (Criteria) this;
		}

		public Criteria andStudentidGreaterThanOrEqualTo(Integer value) {
			addCriterion("StudentID >=", value, "studentid");
			return (Criteria) this;
		}

		public Criteria andStudentidLessThan(Integer value) {
			addCriterion("StudentID <", value, "studentid");
			return (Criteria) this;
		}

		public Criteria andStudentidLessThanOrEqualTo(Integer value) {
			addCriterion("StudentID <=", value, "studentid");
			return (Criteria) this;
		}

		public Criteria andStudentidIn(List<Integer> values) {
			addCriterion("StudentID in", values, "studentid");
			return (Criteria) this;
		}

		public Criteria andStudentidNotIn(List<Integer> values) {
			addCriterion("StudentID not in", values, "studentid");
			return (Criteria) this;
		}

		public Criteria andStudentidBetween(Integer value1, Integer value2) {
			addCriterion("StudentID between", value1, value2, "studentid");
			return (Criteria) this;
		}

		public Criteria andStudentidNotBetween(Integer value1, Integer value2) {
			addCriterion("StudentID not between", value1, value2, "studentid");
			return (Criteria) this;
		}

		public Criteria andSectionmanageridIsNull() {
			addCriterion("SectionManagerID is null");
			return (Criteria) this;
		}

		public Criteria andSectionmanageridIsNotNull() {
			addCriterion("SectionManagerID is not null");
			return (Criteria) this;
		}

		public Criteria andSectionmanageridEqualTo(Integer value) {
			addCriterion("SectionManagerID =", value, "sectionmanagerid");
			return (Criteria) this;
		}

		public Criteria andSectionmanageridNotEqualTo(Integer value) {
			addCriterion("SectionManagerID <>", value, "sectionmanagerid");
			return (Criteria) this;
		}

		public Criteria andSectionmanageridGreaterThan(Integer value) {
			addCriterion("SectionManagerID >", value, "sectionmanagerid");
			return (Criteria) this;
		}

		public Criteria andSectionmanageridGreaterThanOrEqualTo(Integer value) {
			addCriterion("SectionManagerID >=", value, "sectionmanagerid");
			return (Criteria) this;
		}

		public Criteria andSectionmanageridLessThan(Integer value) {
			addCriterion("SectionManagerID <", value, "sectionmanagerid");
			return (Criteria) this;
		}

		public Criteria andSectionmanageridLessThanOrEqualTo(Integer value) {
			addCriterion("SectionManagerID <=", value, "sectionmanagerid");
			return (Criteria) this;
		}

		public Criteria andSectionmanageridIn(List<Integer> values) {
			addCriterion("SectionManagerID in", values, "sectionmanagerid");
			return (Criteria) this;
		}

		public Criteria andSectionmanageridNotIn(List<Integer> values) {
			addCriterion("SectionManagerID not in", values, "sectionmanagerid");
			return (Criteria) this;
		}

		public Criteria andSectionmanageridBetween(Integer value1,
				Integer value2) {
			addCriterion("SectionManagerID between", value1, value2,
					"sectionmanagerid");
			return (Criteria) this;
		}

		public Criteria andSectionmanageridNotBetween(Integer value1,
				Integer value2) {
			addCriterion("SectionManagerID not between", value1, value2,
					"sectionmanagerid");
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

		public Criteria andUcsidIsNull() {
			addCriterion("UCSID is null");
			return (Criteria) this;
		}

		public Criteria andUcsidIsNotNull() {
			addCriterion("UCSID is not null");
			return (Criteria) this;
		}

		public Criteria andUcsidEqualTo(Integer value) {
			addCriterion("UCSID =", value, "ucsid");
			return (Criteria) this;
		}

		public Criteria andUcsidNotEqualTo(Integer value) {
			addCriterion("UCSID <>", value, "ucsid");
			return (Criteria) this;
		}

		public Criteria andUcsidGreaterThan(Integer value) {
			addCriterion("UCSID >", value, "ucsid");
			return (Criteria) this;
		}

		public Criteria andUcsidGreaterThanOrEqualTo(Integer value) {
			addCriterion("UCSID >=", value, "ucsid");
			return (Criteria) this;
		}

		public Criteria andUcsidLessThan(Integer value) {
			addCriterion("UCSID <", value, "ucsid");
			return (Criteria) this;
		}

		public Criteria andUcsidLessThanOrEqualTo(Integer value) {
			addCriterion("UCSID <=", value, "ucsid");
			return (Criteria) this;
		}

		public Criteria andUcsidIn(List<Integer> values) {
			addCriterion("UCSID in", values, "ucsid");
			return (Criteria) this;
		}

		public Criteria andUcsidNotIn(List<Integer> values) {
			addCriterion("UCSID not in", values, "ucsid");
			return (Criteria) this;
		}

		public Criteria andUcsidBetween(Integer value1, Integer value2) {
			addCriterion("UCSID between", value1, value2, "ucsid");
			return (Criteria) this;
		}

		public Criteria andUcsidNotBetween(Integer value1, Integer value2) {
			addCriterion("UCSID not between", value1, value2, "ucsid");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table dbo.UserSectionManager
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
     * This class corresponds to the database table dbo.UserSectionManager
     *
     * @mbggenerated do_not_delete_during_merge Wed Jan 14 11:10:56 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}