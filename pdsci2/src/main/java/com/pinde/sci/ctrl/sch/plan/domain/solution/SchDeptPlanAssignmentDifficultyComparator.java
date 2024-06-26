package com.pinde.sci.ctrl.sch.plan.domain.solution;

import com.pinde.sci.ctrl.sch.plan.domain.SchDeptPlanAssignment;
import org.apache.commons.lang.builder.CompareToBuilder;

import java.util.Comparator;

public class SchDeptPlanAssignmentDifficultyComparator implements Comparator<SchDeptPlanAssignment>
{
    @Override
    public int compare(final SchDeptPlanAssignment a, final SchDeptPlanAssignment b) {
        return new CompareToBuilder().append(a.getId(),b.getId()).toComparison();
    }
}
