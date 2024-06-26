/*
 * Copyright 2010 Red Hat, Inc. and/or its affiliates.
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
import org.optaplanner.examples.common.domain.AbstractPersistable;

import java.util.List;

@XStreamAlias("ResDoctor")
public class Student extends AbstractPersistable  {

    private String doctorFlow;
    private String name;
    private int schMonth;
    private List<SchDeptPlan> schDeptPlanList;

    public Student() {
    }

    public Student(long id, String doctorFlow, String name) {
        super(id);
        this.doctorFlow = doctorFlow;
        this.name = name;
    }

    public Student(long id, String name, List<SchDeptPlan> schDeptPlanList) {
        super(id);
        this.name = name;
        this.schDeptPlanList = schDeptPlanList;
    }

    public int getSchMonth() {
        return schMonth;
    }

    public void setSchMonth(int schMonth) {
        this.schMonth = schMonth;
    }

    public String getDoctorFlow() {
        return doctorFlow;
    }

    public void setDoctorFlow(String doctorFlow) {
        this.doctorFlow = doctorFlow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SchDeptPlan> getSchDeptPlanList() {
        return schDeptPlanList;
    }

    public void setSchDeptPlanList(List<SchDeptPlan> schDeptPlanList) {
        this.schDeptPlanList = schDeptPlanList;
    }

    public Student(long id, String name) {
        super(id);
        this.name = name;
    }
}
