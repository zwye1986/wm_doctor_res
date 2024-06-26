

package com.pinde.sci.ctrl.sch.rotationplan;


import com.pinde.core.util.PkUtil;
import com.pinde.sci.ctrl.sch.plan.domain.*;
import com.pinde.sci.ctrl.sch.plan.util.SchDateUtil;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.api.solver.event.BestSolutionChangedEvent;
import org.optaplanner.core.api.solver.event.SolverEventListener;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RotationPlanWebAction {

    private static ExecutorService solvingExecutor = Executors.newFixedThreadPool(4);

    public void setup(HttpSession session) throws ParseException {
        terminateEarly(session);
        SolverFactory<Plan> solverFactory = SolverFactory.createFromXmlResource("planSolverConfig.xml");
        Solver<Plan> solver = solverFactory.buildSolver();

        session.setAttribute(RotationPlanSessionAttributeName.SOLVER, solver);

        try {
            Plan unsolvedCloudBalance = initData();
            session.setAttribute(RotationPlanSessionAttributeName.SHOWN_SOLUTION, unsolvedCloudBalance);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
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
            int m= (int) (schDeptPlan.getSchMonth());
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

        int s[][]={
                {4,6,4,5,6,4,6,4,4,4,5,6,6,2},
                {4,6,1,3,8,4,6,6,6,4,5,4,6,3},
                {2,6,4,5,6,4,3,4,4,4,5,6,6,2,3,2},
                {4,6,1,3,8,4,5,6,2,4,4,5,4,6,1,3},
                {8,4,5,6,4,3,4,4,9,6,6,55,2},
                {5,6,1,3,7,4,5,6,2,4,4,5,3,6,2,3}
        };
        long l=0;
        for(int i=0;i<6;i++) {
            Student doctor = new Student(i,"doctorFlow"+i,"doctorName"+i);
            List<SchDeptPlan> planList=new ArrayList<>();
//            int b= (int) (Math.random()*15);
//            if(b==0) b=10;
//
//            int a[]=new int[b];
//            int k=33;
//
//            for ( int j=0;j<b-1;j++ )
//            {
//                a[j]= (int) (Math.random()*k);
//                k=k-a[j];
//                a[j+1]=k;
//            }
            int k=i%6;
            int sum=0;
            for(int j=0;j<s[k].length;j++)
            {
                int index= (int) (Math.random()*depts.size());
                SchDeptPlan schDeptPlan=new SchDeptPlan();
                schDeptPlan.setId(l++);
                schDeptPlan.setDoctorFlow(doctor.getDoctorFlow());
                schDeptPlan.setRecordFlow(PkUtil.getUUID());
                schDeptPlan.setSchMonth( s[k][j]);
                schDeptPlan.setSchDept(depts.get(index));
                planList.add(schDeptPlan);
                sum+=s[k][j];
            }
            doctor.setSchMonth(sum);
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
        int temp[]={10,15,12,6,5};
        for(int i=0;i<30;i++) {
            Dept dept = new Dept(i,"deptFlow"+i,"deptName"+i);
            dept.setMaxNum(temp[i%5]);
            list.add(dept);
        }
        return list;
    }

    public void solve(final HttpSession session) {
        final Solver<Plan> solver = (Solver<Plan>) session.getAttribute(RotationPlanSessionAttributeName.SOLVER);
        final Plan unsolvedSolution = (Plan) session.getAttribute(RotationPlanSessionAttributeName.SHOWN_SOLUTION);

        solver.addEventListener(new SolverEventListener<Plan>() {
            @Override
            public void bestSolutionChanged(BestSolutionChangedEvent<Plan> event) {
                Plan bestSolution = event.getNewBestSolution();
                session.setAttribute(RotationPlanSessionAttributeName.SHOWN_SOLUTION, bestSolution);
            }
        });
        solvingExecutor.submit(new Runnable() {
            @Override
            public void run() {
                solver.solve(unsolvedSolution);
            }
        });
    }

    public void terminateEarly(HttpSession session) {
        final Solver<Plan> solver = (Solver<Plan>) session.getAttribute(RotationPlanSessionAttributeName.SOLVER);
        if (solver != null) {
            solver.terminateEarly();
            session.setAttribute(RotationPlanSessionAttributeName.SOLVER, null);
        }
    }

}
