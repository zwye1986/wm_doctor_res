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

@XStreamAlias("Dept")
public class Dept extends AbstractPersistable {

    private  String deptFlow;
    private String deptName;
    private int maxNum;

    public Dept() {
    }
    public Dept(long id, String deptFlow, String deptName) {
        super(id);
        this.deptFlow = deptFlow;
        this.deptName = deptName;
    }


    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }
    public String getDeptFlow() {
        return deptFlow;
    }

    public void setDeptFlow(String deptFlow) {
        this.deptFlow = deptFlow;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLabel() {
        return "Dept_" + id;
    }

}
