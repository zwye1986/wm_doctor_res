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

package com.pinde.sci.ctrl.sch.plan.score;

import com.pinde.sci.ctrl.sch.plan.domain.*;
import com.pinde.sci.ctrl.sch.plan.util.SchDateUtil;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.incremental.AbstractIncrementalScoreCalculator;

import java.text.ParseException;
import java.util.*;

public class PlanIncrementalScoreCalculator extends AbstractIncrementalScoreCalculator<Plan> {
    //key 为doctorFlow+recordFlow
    //判断时间是否连续
    private Map<String,List<PlanMonth>> doctorMonthsMap;
    //轮转月数与分配的时间必须相等
    private Map<String,Integer> doctorMonthNumMap;


    //用来判断时间唯一
    private Map<String, int[]> studentBusyMap;
    //用来判断科室人数容量
    private Map<String, int[][]> departmentMap;

    //时间数量
    private int unitCount;
    private int hardScore;
    private int softScore;

    @Override
    public void resetWorkingSolution(Plan plan) {
        doctorMonthsMap=new HashMap<>();

        studentBusyMap=new HashMap<>();
        departmentMap=new HashMap<>();
        doctorMonthNumMap=new HashMap<>();

        unitCount=plan.getPlanMonthList().size();
        for(Student d:plan.getDoctors())
        {
            studentBusyMap.put(d.getDoctorFlow(),new int[unitCount]);
        }
        for(Dept d:plan.getDeptList())
        {
            int [][] temp=new int[3][unitCount];// 0 为已排班的人数 1 为本次排班的人数  2 为科室可容纳的人数
            for(int i=0;i<plan.getPlanMonthList().size();i++)
            {
                temp[2][i]=d.getMaxNum();
                for(int j=0;j<plan.getDeptMonthList().size();j++)
                {
                    SchDeptMonth deptMonth=plan.getDeptMonthList().get(j);
                    if(deptMonth.getDept().getDeptFlow().equals(d.getDeptFlow())
                            &&deptMonth.getPlanMonth().getId()==i)
                    {
                        temp[0][i]=deptMonth.getUsedNum();
                    }
                }
            }
            departmentMap.put(d.getDeptFlow(),temp);
        }
        for(SchDeptPlan d:plan.getDoctorPlanList())
        {
            doctorMonthsMap.put(d.getDoctorFlow()+d.getRecordFlow(),new ArrayList<>());
            doctorMonthNumMap.put(d.getDoctorFlow()+d.getRecordFlow(),d.getSchMonth());
        }
        hardScore=0;
        softScore=0;
        for(SchDeptPlanAssignment assignment:plan.getAssignmentList())
        {
            try {
                insert(assignment);
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    @Override
    public void beforeEntityAdded(Object entity) {
        // Do nothing
    }

    @Override
    public void afterEntityAdded(Object entity) {
        // TODO the maps should probably be adjusted
        try {
            insert((SchDeptPlanAssignment) entity);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void beforeVariableChanged(Object entity, String variableName) {
        try {
            retract((SchDeptPlanAssignment) entity);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void afterVariableChanged(Object entity, String variableName) {
        try {
            insert((SchDeptPlanAssignment) entity);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void beforeEntityRemoved(Object entity) {
        try {
            retract((SchDeptPlanAssignment) entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void afterEntityRemoved(Object entity) {
        // Do nothing
        // TODO the maps should probably be adjusted
    }

    private void insert(SchDeptPlanAssignment dept) throws ParseException {
        PlanMonth month=dept.getMonth();
        if(month!=null)
        {
            int monthIdex=month.getStartIndex();
            String doctorFlow=dept.getDoctorFlow();
            String recordFlow=dept.getRecordFlow();
            //时间唯一
            int[] studentBusy = this.studentBusyMap.get(doctorFlow);
            int[] array = studentBusy;
            ++array[monthIdex];
            if (studentBusy[monthIdex] > 1) {
                --hardScore;
            }
            boolean isBest=true;
            List<PlanMonth> months=doctorMonthsMap.get(doctorFlow+recordFlow);
            months.add(month);
            isBest= SchDateUtil.isContinuity(months);
            doctorMonthsMap.put(doctorFlow+recordFlow,months);
            if(!isBest)
            {
                --hardScore;
            }else {
                Integer num = doctorMonthNumMap.get(doctorFlow + recordFlow);
                if (num != months.size()) {
                    --hardScore;
                }
            }
            int[][]deptTemp = this.departmentMap.get(dept.getSchDept().getDeptFlow());
            int[] array0 = deptTemp[0];// 0 为已排班的人数
            int[] array1 = deptTemp[1];//1 为本次排班的人数
            int[] array2 = deptTemp[2];// 2 为科室可容纳的人数
            ++array1[monthIdex];
            if ((array0[monthIdex]+array1[monthIdex]) > array2[monthIdex]) {
                --softScore;
            }
            if ((array0[monthIdex]+array1[monthIdex]) <=0) {
                --softScore;
            }
        }else{
//            --hardScore;
//            --softScore;
        }
    }

    private void retract(SchDeptPlanAssignment dept) throws ParseException {
        PlanMonth month=dept.getMonth();
        if(month!=null)
        {
            String doctorFlow=dept.getDoctorFlow();
            String recordFlow=dept.getRecordFlow();

            int monthIdex=month.getStartIndex();
            int[] studentBusy = this.studentBusyMap.get(doctorFlow);
            int[] array = studentBusy;
            if (studentBusy[monthIdex] > 1) {
                ++this.hardScore;
            }
            --array[monthIdex];
            boolean isBest=true;
            List<PlanMonth> months=doctorMonthsMap.get(doctorFlow+recordFlow);
            isBest= SchDateUtil.isContinuity(months);
            if(!isBest)
            {
                ++hardScore;
            }else {
                Integer num = doctorMonthNumMap.get(doctorFlow + recordFlow);
                if (num != months.size()) {
                    ++hardScore;
                }
            }
            Iterator iterator=months.iterator();
            while (iterator.hasNext())
            {
                PlanMonth i= (PlanMonth) iterator.next();
                if(i.equals(month))
                {
                    months.remove(i);
                    break;
                }
            }
            doctorMonthsMap.put(doctorFlow+recordFlow,months);

            int[][]deptTemp = this.departmentMap.get(dept.getSchDept().getDeptFlow());
            int[] array0 = deptTemp[0];// 0 为已排班的人数
            int[] array1 = deptTemp[1];//1 为本次排班的人数
            int[] array2 = deptTemp[2];// 2 为科室可容纳的人数
            if ((array0[monthIdex]+array1[monthIdex]) > array2[monthIdex]) {
                ++softScore;
            }
            if ((array0[monthIdex]+array1[monthIdex]) <=0) {
                ++softScore;
            }
            --array1[monthIdex];
        }else{
//            ++hardScore;
//            ++softScore;
        }
    }
//
//    private void insert(SchDeptPlanAssignment dept) throws ParseException {
//        PlanMonth month=dept.getMonth();
//        if(month!=null)
//        {
//            int monthIdex=month.getStartIndex();
//            String doctorFlow=dept.getDoctorFlow();
//            String recordFlow=dept.getRecordFlow();
//
//            int[] studentBusy = this.studentBusyMap.get(doctorFlow);
//            int[] array = studentBusy;
//            ++array[monthIdex];
//            if (studentBusy[monthIdex] > 1) {
//                --hardScore;
//            }
//            boolean isBest=true;
//            List<PlanMonth> months=doctorMonthsMap.get(doctorFlow+recordFlow);
//            months.add(month);
//            isBest= SchDateUtil.isContinuity(months);
//            doctorMonthsMap.put(doctorFlow+recordFlow,months);
//            if(!isBest)
//            {
//                --hardScore;
//            }
//
//            Integer num= doctorMonthNumMap.get(doctorFlow+recordFlow );
//            if(num!=months.size())
//            {
//                --hardScore;
//            }
//
//            int[][]deptTemp = this.departmentMap.get(dept.getSchDept().getDeptFlow());
//            int[] array0 = deptTemp[0];// 0 为已排班的人数
//            int[] array1 = deptTemp[1];//1 为本次排班的人数
//            int[] array2 = deptTemp[2];// 2 为科室可容纳的人数
//            ++array1[monthIdex];
//            if ((array0[monthIdex]+array1[monthIdex]) > array2[monthIdex]) {
//                --softScore;
//            }
//        }
//    }
//
//    private void retract(SchDeptPlanAssignment dept) throws ParseException {
//        PlanMonth month=dept.getMonth();
//        if(month!=null)
//        {
//            String doctorFlow=dept.getDoctorFlow();
//            String recordFlow=dept.getRecordFlow();
//
//            int monthIdex=month.getStartIndex();
//            int[] studentBusy = this.studentBusyMap.get(doctorFlow);
//            int[] array = studentBusy;
//            if (studentBusy[monthIdex] > 1) {
//                ++this.hardScore;
//            }
//            --array[monthIdex];
//            boolean isBest=true;
//            List<PlanMonth> months=doctorMonthsMap.get(doctorFlow+recordFlow);
//
//            Integer num= doctorMonthNumMap.get(doctorFlow+recordFlow );
//            if(num!=months.size())
//            {
//                ++hardScore;
//            }
//            isBest= SchDateUtil.isContinuity(months);
//            Iterator iterator=months.iterator();
//            while (iterator.hasNext())
//            {
//                PlanMonth i= (PlanMonth) iterator.next();
//                if(i.equals(month))
//                {
//                    months.remove(i);
//                    break;
//                }
//            }
//            if(!isBest)
//            {
//                ++hardScore;
//            }
//            doctorMonthsMap.put(doctorFlow+recordFlow,months);
//
//            int[][]deptTemp = this.departmentMap.get(dept.getSchDept().getDeptFlow());
//            int[] array0 = deptTemp[0];// 0 为已排班的人数
//            int[] array1 = deptTemp[1];//1 为本次排班的人数
//            int[] array2 = deptTemp[2];// 2 为科室可容纳的人数
//            if ((array0[monthIdex]+array1[monthIdex]) > array2[monthIdex]) {
//                ++softScore;
//            }
//            --array1[monthIdex];
//        }
//    }
    @Override
    public HardSoftScore calculateScore() {
        return HardSoftScore.valueOf(hardScore, softScore);
    }

}
