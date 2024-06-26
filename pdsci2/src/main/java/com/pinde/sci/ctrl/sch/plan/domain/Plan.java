/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pinde.sci.ctrl.sch.plan.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.examples.common.domain.AbstractPersistable;
import org.optaplanner.persistence.xstream.api.score.buildin.hardsoft.HardSoftScoreXStreamConverter;

import java.util.List;

@PlanningSolution
@XStreamAlias("Plan")
public class Plan extends AbstractPersistable {

    private String startDate;

    @ProblemFactCollectionProperty
    private List<Dept> deptList;

    @ProblemFactCollectionProperty
    private List<SchDeptMonth> deptMonthList;

    @ProblemFactCollectionProperty
    private List<Student> doctors;

    @ProblemFactCollectionProperty
    private List<SchDeptPlan> doctorPlanList;

    @ValueRangeProvider(id = "planMonthRange")
    @ProblemFactCollectionProperty
    private List<PlanMonth> planMonthList;

    @PlanningEntityCollectionProperty
    private List<SchDeptPlanAssignment> assignmentList;

    public List<SchDeptPlanAssignment> getAssignmentList() {
        return assignmentList;
    }

    public void setAssignmentList(List<SchDeptPlanAssignment> assignmentList) {
        this.assignmentList = assignmentList;
    }

    @PlanningScore
    @XStreamConverter(HardSoftScoreXStreamConverter.class)
    private HardSoftScore score;

    public Plan() {
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public List<PlanMonth> getPlanMonthList() {
        return planMonthList;
    }

    public void setPlanMonthList(List<PlanMonth> planMonthList) {
        this.planMonthList = planMonthList;
    }

    public List<Dept> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<Dept> deptList) {
        this.deptList = deptList;
    }

    public List<SchDeptMonth> getDeptMonthList() {
        return deptMonthList;
    }

    public void setDeptMonthList(List<SchDeptMonth> deptMonthList) {
        this.deptMonthList = deptMonthList;
    }

    public List<Student> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Student> doctors) {
        this.doctors = doctors;
    }

    public List<SchDeptPlan> getDoctorPlanList() {
        return doctorPlanList;
    }

    public void setDoctorPlanList(List<SchDeptPlan> doctorPlanList) {
        this.doctorPlanList = doctorPlanList;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    // ************************************************************************
    // Complex methods
    // ************************************************************************

}
