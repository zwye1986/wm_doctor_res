/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
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

package com.pinde.sci.ctrl.sch.plan.app;

import com.pinde.sci.ctrl.sch.plan.domain.*;
import com.pinde.sci.ctrl.sch.plan.util.SchDateUtil;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PlanHelloWorld {

    public static void main(String[] args) throws ParseException {
        //创建规则 加载配置 包括规则
        // Build the Solver

        SolverFactory<Plan> solverFactory = SolverFactory.createFromXmlResource(
                "org/optaplanner/examples/plan/solver/planSolverConfig.xml");
        Solver<Plan> solver = solverFactory.buildSolver();

        long startTime = System.currentTimeMillis();    //获取开始时间
        // Load a problem with 400 computers and 1200 processes
        Plan unsolvedCloudBalance = initData();

//        System.out.println(JSON.toJSONString(unsolvedCloudBalance));


        // Solve the problem
        Plan solvedCloudBalance = solver.solve(unsolvedCloudBalance);

        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        // Display the result
//        System.out.println(JSON.toJSONString(solvedCloudBalance));
//        System.out.println(ValueRangeFactory.createIntValueRange(1, 2));
    }

    private static Plan initData() throws ParseException {
        Plan plan=new Plan();
        plan.setId(0L);
        String startDate="2018-09-01";
        //初始化时间信息
        List<PlanMonth> planMonths= SchDateUtil.getTimes(startDate,33.0);
        plan.setPlanMonthList(planMonths);

        //初始化医院科室信息
        List<Dept> depts= getSchDepts();
        plan.setDeptList(depts);

        //初始化医院科室-时间
        List<SchDeptMonth> deptMonths=getSchDeptMoths(depts,planMonths);
        plan.setDeptMonthList(deptMonths);
        //初始化学员
        List<Student> doctors=getResDoctors(depts);
        plan.setDoctors(doctors);
        //初始化所有学员计划
        List<SchDeptPlan> planList=new ArrayList<>();
        plan.getDoctors().forEach(resDoctor -> {
            if(resDoctor.getSchDeptPlanList()!=null&&resDoctor.getSchDeptPlanList().size()>0)
            {
                planList.addAll(resDoctor.getSchDeptPlanList());
            }
        });
        plan.setDoctorPlanList(planList);
        //初始化所有计划
        List<SchDeptPlanAssignment> assignments=new ArrayList<>();
        final long[] l = {0};
        plan.getDoctorPlanList().forEach(schDeptPlan -> {
            int m= (int) (schDeptPlan.getSchMonth()/0.5);
            for(int k=0;k<m;k++)
            {
                SchDeptPlanAssignment assignment=new SchDeptPlanAssignment();
                assignment.setId(l[0]++);
                assignment.setSchDept(schDeptPlan.getSchDept());
                assignment.setDoctorFlow(schDeptPlan.getDoctorFlow());
                assignment.setRecordFlow(schDeptPlan.getRecordFlow());
                assignments.add(assignment);
            }
        });
        plan.setAssignmentList(assignments);
        return plan;
    }

    private static List<Student> getResDoctors(List<Dept> depts) {
        List<Student> list=new ArrayList<>();
        long l=0;
        for(long i=0;i<30;i++) {
            Student doctor = new Student(i,"doctorFlow"+i,"doctorName"+i);
            List<SchDeptPlan> planList=new ArrayList<>();
            int b= (int) (Math.random()*15);
            if(b==0) b=10;

            int a[]=new int[b];
            int k=33;
            for ( int j=0;j<b-1;j++ )
            {
                a[j]= (int) (Math.random()*k);
                k=k-a[j];
                a[j+1]=k;
            }
            for(int j=0;j<b;j++)
            {
                if(a[j]>0)
                {
                    int index= (int) (Math.random()*depts.size());
                    SchDeptPlan schDeptPlan=new SchDeptPlan();
                    schDeptPlan.setId(l++);
                    schDeptPlan.setDoctorFlow(doctor.getDoctorFlow());
                    schDeptPlan.setSchMonth(a[j]);
                    schDeptPlan.setSchDept(depts.get(index));
                    planList.add(schDeptPlan);
                }
            }
            System.err.println(b);
            doctor.setSchDeptPlanList(planList);
            list.add(doctor);
        }
        return list;
    }

    private static List<SchDeptMonth> getSchDeptMoths(List<Dept> depts, List<PlanMonth> planMonths) {
        List<SchDeptMonth> list=new ArrayList<>();
        final int[] i = {0};
        depts.forEach(d->{
            planMonths.forEach(planMonth -> {
                SchDeptMonth schDeptMonth=new SchDeptMonth(i[0]++,d,planMonth);
                list.add(schDeptMonth);
            });
        });
        return list;
    }

    private static List<Dept> getSchDepts() {
        List<Dept> list=new ArrayList<>();
        for(int i=0;i<30;i++) {
            Dept dept = new Dept(i,"deptFlow"+i,"deptName"+i);
            dept.setMaxNum(20);
            list.add(dept);
        }
        return list;
    }

    public static String toDisplayString(Plan cloudBalance) {
        StringBuilder displayString = new StringBuilder();
        return displayString.toString();
    }

}
