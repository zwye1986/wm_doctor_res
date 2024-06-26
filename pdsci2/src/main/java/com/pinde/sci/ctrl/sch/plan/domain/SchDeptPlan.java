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

@XStreamAlias("SchDeptPlan")
public class SchDeptPlan extends AbstractPersistable {
    //学员选科信息
    private int schMonth; // =schMonth/0.5 1.5/0.5=3
    private String startDate;
    private String endDate;
    private String recordFlow;
    private Dept schDept;

    public String getDoctorFlow() {
        return doctorFlow;
    }

    public void setDoctorFlow(String doctorFlow) {
        this.doctorFlow = doctorFlow;
    }

    private String doctorFlow;

    public int getSchMonth() {
        return schMonth;
    }

    public void setSchMonth(int schMonth) {
        this.schMonth = schMonth;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRecordFlow() {
        return recordFlow;
    }

    public void setRecordFlow(String recordFlow) {
        this.recordFlow = recordFlow;
    }

    public Dept getSchDept() {
        return schDept;
    }

    public void setSchDept(Dept schDept) {
        this.schDept = schDept;
    }


    public String getLabel() {
        return "SchDeptPlan " + id;
    }

}
