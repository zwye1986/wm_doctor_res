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

public class PlanIncrementalScoreCalculator2 extends AbstractIncrementalScoreCalculator<Plan> {
    //key1 doctorFlow  key2 doctorFlow+deptFlow+assgementId  value 为 planMonthId
    //用来判断时间唯一
    private Map<String, Map<String,Long>> doctorDeptMonthMap;
    //key 为doctorFlow+recordFlow
    //判断时间是否连续
    private Map<String,List<PlanMonth>> doctorMonthsMap;


    //用来判断时间唯一
    private Map<String, int[]> studentBusyMap;

    //时间数量
    private int unitCount;
    private int hardScore;
    private int softScore;

    @Override
    public void resetWorkingSolution(Plan plan) {
        doctorDeptMonthMap=new HashMap<>();
        doctorMonthsMap=new HashMap<>();
        studentBusyMap=new HashMap<>();
        unitCount=plan.getPlanMonthList().size();
        for(Student d:plan.getDoctors())
        {
            doctorDeptMonthMap.put(d.getDoctorFlow(),new HashMap<>());
            int[] temp = new int[this.unitCount];
            studentBusyMap.put(d.getDoctorFlow(),temp);
        }
        for(SchDeptPlan d:plan.getDoctorPlanList())
        {
            doctorMonthsMap.put(d.getDoctorFlow()+d.getRecordFlow(),new ArrayList<>());
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
            String doctorFlow=dept.getDoctorFlow();
            String recordFlow=dept.getRecordFlow();

            long i = month.getId();
            int[] studentBusy = this.studentBusyMap.get(doctorFlow);
            for(int j=0;j<studentBusy.length;j++)
            {
                if(j==i&&++studentBusy[j]>1)
                {
                    --hardScore;
                    break;
                }
            }

//            Map<String,Long> map=doctorDeptMonthMap.get(doctorFlow);
//            if(map.containsValue(month.getId()))
//            {
//                --hardScore;
//            }
//            map.put(doctorFlow+dept.getSchDept().getDeptFlow()+dept.getId(),month.getId());
//            doctorDeptMonthMap.put(doctorFlow,map);
            List<PlanMonth> months=doctorMonthsMap.get(doctorFlow+recordFlow);
            months.add(month);
            boolean isBest=true;
            isBest= SchDateUtil.isContinuity(months);
            if(isBest)
            {
                doctorMonthsMap.put(doctorFlow+recordFlow,months);
            }else{
                --hardScore;
            }

        }
        else
        {
            -- hardScore;
        }
    }

    private void retract(SchDeptPlanAssignment dept) throws ParseException {
        PlanMonth month=dept.getMonth();
        if(month!=null)
        {
            String doctorFlow=dept.getDoctorFlow();
            String recordFlow=dept.getRecordFlow();

            long i = month.getId();
            int k=-1;
            int[] studentBusy = this.studentBusyMap.get(doctorFlow);
            for(int j=0;j<studentBusy.length;j++)
            {
                if(j==i&&studentBusy[j]>1)
                {
                    ++hardScore;
                    k=j;
                    break;
                }
            }
            if(k>=0) {
                --studentBusy[k];
            }
            List<PlanMonth> months=doctorMonthsMap.get(doctorFlow+recordFlow);
//            months.add(month);
            boolean isBest=true;
            isBest= SchDateUtil.isContinuity(months);
            if(!isBest)
            {
                ++hardScore;
                Iterator iterator=months.iterator();
                while (iterator.hasNext())
                {
                   PlanMonth it= (PlanMonth) iterator.next();
                    if(it.equals(month))
                    {
                        months.remove(it);
                        break;
                    }
                }
                doctorMonthsMap.put(doctorFlow+recordFlow,months);
            }
        }
        else
        {
            ++hardScore;
        }
    }

    @Override
    public HardSoftScore calculateScore() {
        return HardSoftScore.valueOf(hardScore, softScore);
    }

}
