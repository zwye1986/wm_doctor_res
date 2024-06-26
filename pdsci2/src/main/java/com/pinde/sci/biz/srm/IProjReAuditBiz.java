package com.pinde.sci.biz.srm;

/**
 * Created by www.0001.Ga on 2017-08-21.
 */
public interface IProjReAuditBiz {

    String reAuditOption(String projFlow);

    String approveReAudit(String projFlow);

    String contractReAudit(String projFlow);

    String scheduleReAudit(String projFlow);

    String changeReAudit(String projFlow);

    String completeReAudit(String projFlow);

    String terminateReAudit(String projFlow);

}
