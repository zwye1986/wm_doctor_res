package com.pinde.sci.common;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.enums.srm.*;
import com.pinde.sci.model.mo.PubProjProcess;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/10 0010.
 */
public class GeneralSrmMethod {
    /**
     * 我的项目列表上显示的图标 需要对应6个点 分别为1-6
     * 1 表示勾
     * 2 表示编辑
     * 3 表示空白
     * 4 表示叉号
     *
     * @param projProcessList
     * @return
     */
    public static Map<String, String> showIcn(List<PubProjProcess> projProcessList) {
        Map<String, String> icnMap = new HashMap<String, String>();
        PubProjProcess upApplyProcess = null;//申报阶段最新的一次记录
        PubProjProcess upApproveProcess = null;//立项阶段最新的一次记录
        PubProjProcess upAwardProcess = null;//报奖阶段最新的一次记录
        PubProjProcess contractProcess = null;//合同阶段最新的一次记录
        PubProjProcess upSchduleProcess = null;//实施阶段最新的一次记录
        PubProjProcess upCompleteProcess = null;//验收阶段最新的一次记录
        PubProjProcess upArchiveProcess = null;//归档阶段最新的一次记录
        /*--第一个点--*/
        for (PubProjProcess ppp : projProcessList) {
            if (ProjStageEnum.Apply.getId().equals(ppp.getProjStageId())) {
                upApplyProcess = ppp;
                break;
            }
        }
        if (upApplyProcess != null) {
            if (StringUtil.isEquals(InitConfig.getSysCfg("srm_for_use"), "global")) {
                //区域版本
                String statusId = upApplyProcess.getProjStatusId();
                if (ProjApplyStatusEnum.ThirdAudit.getId().equals(statusId)) {
                    icnMap.put("1", "1");//对应的第一个点
                } else {
                    icnMap.put("1", "2");
                }
            } else {
                //医院版
                String statusId = upApplyProcess.getProjStatusId();
                if (ProjApplyStatusEnum.FirstAudit.getId().equals(statusId)) {
                    icnMap.put("1", "1");//对应的第一个点
                } else {
                    icnMap.put("1", "2");
                }
            }
        }
        /*--第二个点--*/
        //项目是否在评审的标记
        boolean evalFlag = false;
        for (PubProjProcess ppp : projProcessList) {
            if ((ProjStageEnum.Approve.getId().equals(ppp.getProjStageId()) || ProjStageEnum.Award.getId().equals(ppp.getProjStageId())) &&
                    ProjEvaluationStatusEnum.Evaluation.getId().equals(ppp.getProjStatusId())) {
                evalFlag = true;
                break;
            }
        }
        if (!evalFlag) {
            icnMap.put("2", "3");//对应第二个点
        }

        for (PubProjProcess ppp : projProcessList) {
            if (ProjStageEnum.Award.getId().equals(ppp.getProjStageId())) {
                upAwardProcess = ppp;
                break;
            }
        }
        if(upAwardProcess != null) {
            if (evalFlag) {
                if (!ProjEvaluationStatusEnum.Evaluation.getId().equals(upAwardProcess.getProjStatusId())) {
                    icnMap.put("2", "1");
                } else {
                    icnMap.put("2", "2");
                }
            }
            for (PubProjProcess ppp : projProcessList) {
                if (ProjStageEnum.Award.getId().equals(ppp.getProjStageId()) && ProjApproveStatusEnum.AwardPrizes.getId().equals(ppp.getProjStatusId())) {//科技报奖阶段 发奖
                    icnMap.put("3", "1");
                   return icnMap;
                }
            }
            for (PubProjProcess ppp : projProcessList) {
                if (ProjStageEnum.Award.getId().equals(ppp.getProjStageId()) && ProjApproveStatusEnum.NoPrizes.getId().equals(ppp.getProjStatusId())) {//科技报奖阶段 不发奖
                    icnMap.put("3", "4");
                    return icnMap;
                }
            }
        }
        for (PubProjProcess ppp : projProcessList) {
            if (ProjStageEnum.Approve.getId().equals(ppp.getProjStageId())) {
                upApproveProcess = ppp;
                break;
            }
        }
        if (upApproveProcess != null) {
            if (evalFlag) {
                if (!ProjEvaluationStatusEnum.Evaluation.getId().equals(upApproveProcess.getProjStatusId())) {
                    icnMap.put("2", "1");
                } else {
                    icnMap.put("2", "2");
                }
            }
			/*--第三个点--*/

            for (PubProjProcess ppp : projProcessList) {
                if (ProjStageEnum.Approve.getId().equals(ppp.getProjStageId()) && ProjApproveStatusEnum.Approve.getId().equals(ppp.getProjStatusId())) {
                    icnMap.put("3", "1");
                    break;
                }
            }

            for (PubProjProcess ppp : projProcessList) {
                if (ProjStageEnum.Approve.getId().equals(ppp.getProjStageId()) && ProjApproveStatusEnum.NotApprove.getId().equals(ppp.getProjStatusId())) {
                    icnMap.put("3", "4");
                    break;
                }
            }

        } else {
            icnMap.put("3", "3");
            icnMap.put("4", "3");
            icnMap.put("5", "3");
            icnMap.put("6", "3");
            return icnMap;
        }

		/*--第四个点--*/
        for (PubProjProcess ppp : projProcessList) {
            if (ProjStageEnum.Contract.getId().equals(ppp.getProjStageId())) {
                contractProcess = ppp;
                break;
            }
        }
        if (contractProcess != null) {
            //如果最近的一次是实施阶段合同
            if (StringUtil.isEquals(InitConfig.getSysCfg("srm_for_use"), "global")) {
                //区域版本
                String statusId = contractProcess.getProjStatusId();
                if (ProjContractStatusEnum.ThirdAudit.getId().equals(statusId)) {
                    icnMap.put("4", "1");//对应的第四个点
                } else {
                    icnMap.put("4", "2");
                }
            } else {
                //医院版
                String statusId = contractProcess.getProjStatusId();
                if (ProjContractStatusEnum.FirstAudit.getId().equals(statusId)) {
                    icnMap.put("4", "1");//对应的第四个点
                } else {
                    icnMap.put("4", "2");
                }
            }
        } else {
            icnMap.put("4", "3");
            icnMap.put("5", "3");
            icnMap.put("6", "3");
            //解决 直接跳过实施阶段 申请验收 没图标的情况
            //return icnMap;
        }



		/*--第五个点--*/
        for (PubProjProcess ppp : projProcessList) {
            if (ProjStageEnum.Schedule.getId().equals(ppp.getProjStageId())) {
                upSchduleProcess = ppp;
                break;
            }
        }
        if (upSchduleProcess != null) {
//			if(ProjRecTypeEnum.Contract.getId().equals(upSchduleProcess.getRecTypeId())){
//				//如果最近的一次是实施阶段合同
//				if(StringUtil.isEquals(InitConfig.getSysCfg("srm_for_use") , "global")){
//					//区域版本
//					String statusId = upSchduleProcess.getProjStatusId();
//					if(ProjScheduleStatusEnum.ThirdAudit.getId().equals(statusId)){
//						icnMap.put("4", "1");//对应的第四个点
//					}else{
//						icnMap.put("4", "2");
//					}
//				}else{
//					//医院版
//					String statusId = upSchduleProcess.getProjStatusId();
//					if(ProjScheduleStatusEnum.FirstAudit.getId().equals(statusId)){
//						icnMap.put("4", "1");//对应的第四个点
//					}else{
//						icnMap.put("4", "2");
//					}
//				}
//			}else{
//				//如果不是
//				boolean contractFlag = false;
//				for(PubProjProcess ppp:projProcessList){
//					if(ProjRecTypeEnum.Contract.getId().equals(ppp.getRecTypeId())){
//						contractFlag = true;
//						break;
//					}
//				}
//				if(contractFlag){
//					icnMap.put("4" , "1");
//				}else{
//					icnMap.put("4" , "3");
//				}
            if (StringUtil.isEquals(InitConfig.getSysCfg("srm_for_use"), "global")) {
                //区域版本
                String statusId = upSchduleProcess.getProjStatusId();
                if (ProjScheduleStatusEnum.ThirdAudit.getId().equals(statusId)) {
                    icnMap.put("5", "1");//对应的第五个点
                } else {
                    icnMap.put("5", "2");
                }
            } else {
                //医院版
                String statusId = upSchduleProcess.getProjStatusId();
                if (ProjScheduleStatusEnum.FirstAudit.getId().equals(statusId)) {
                    icnMap.put("5", "1");//对应的第五个点
                } else {
                    icnMap.put("5", "2");
                }
            }

            //}
        } else {
            //icnMap.put("4", "3");
            icnMap.put("5", "3");
            icnMap.put("6", "3");
            //解决 直接跳过实施阶段 申请验收 没图标的情况
            //return icnMap;
        }

		/*--第6点--*/
        for (PubProjProcess ppp : projProcessList) {
            if (ProjStageEnum.Archive.getId().equals(ppp.getProjStageId())) {
                upArchiveProcess = ppp;
                break;
            }
        }
        if (upArchiveProcess != null) {
            if (ProjArchiveStatusEnum.Archive.getId().equals(upArchiveProcess.getProjStatusId())) {
                icnMap.put("6", "1");
            } else {
                icnMap.put("6", "2");
            }
        } else {
            //判断是否到了验收阶段
            for (PubProjProcess ppp : projProcessList) {
                if (ProjStageEnum.Complete.getId().equals(ppp.getProjStageId())) {
                    upCompleteProcess = ppp;
                    break;
                }
            }
            if (upCompleteProcess != null) {
                //项目中止 显示状态不更改为项目验收（省中科研需求）
                if(ProjRecTypeEnum.TerminateReport.getId().equals(upCompleteProcess.getRecTypeId())){
                    icnMap.put("6", "3");
                }else {
                    icnMap.put("6", "2");
                }
            } else {
                icnMap.put("6", "3");
            }
        }
        return icnMap;
    }

}
