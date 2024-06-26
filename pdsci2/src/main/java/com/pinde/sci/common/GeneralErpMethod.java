package com.pinde.sci.common;

import com.pinde.sci.enums.erp.WorkOrderStatusEnum;

/**
 * Created by Administrator on 2016/3/10 0010.
 */
public class GeneralErpMethod {
    public static boolean getVisibleByWorkStatusId(String workStatusId, String currWorkStatusId) {
        if (WorkOrderStatusEnum.Closed.getId().equals(currWorkStatusId)) {
            return true;
        }
        if (WorkOrderStatusEnum.Implemented.getId().equals(workStatusId)) {
            return WorkOrderStatusEnum.Implemented.getId().equals(currWorkStatusId) || WorkOrderStatusEnum.CompletePassed.getId().equals(currWorkStatusId) || WorkOrderStatusEnum.CompleteUnPassed.getId().equals(currWorkStatusId) || WorkOrderStatusEnum.Passed.getId().equals(currWorkStatusId);
        } else if (WorkOrderStatusEnum.CompletePassed.getId().equals(workStatusId)) {
            return WorkOrderStatusEnum.CompletePassed.getId().equals(currWorkStatusId) || WorkOrderStatusEnum.Passed.getId().equals(currWorkStatusId);
        } else if (WorkOrderStatusEnum.CompleteUnPassed.getId().equals(workStatusId)) {
            return WorkOrderStatusEnum.CompleteUnPassed.getId().equals(currWorkStatusId) || WorkOrderStatusEnum.Passed.getId().equals(currWorkStatusId);
        } else if (WorkOrderStatusEnum.Passed.getId().equals(workStatusId)) {
            return WorkOrderStatusEnum.Passed.getId().equals(currWorkStatusId);
        }
        return false;
    }
}
