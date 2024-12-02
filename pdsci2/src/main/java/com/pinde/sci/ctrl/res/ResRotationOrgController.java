package com.pinde.sci.ctrl.res;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResRotationOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.model.mo.ResRotationOrg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/res/rotation")
public class ResRotationOrgController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ResRotationOrgController.class);
	@Autowired
	private IResRotationOrgBiz resRotationOrgBiz;
	
	@RequestMapping(value="/saveSun")
	public @ResponseBody String saveSun(@RequestBody List<ResRotationOrg> resScoreList){
		List<String> list=new ArrayList<String>();
		if (resScoreList!=null&&!resScoreList.isEmpty()) {
			List<ResRotationOrg> resRotationOrgList=resRotationOrgBiz.searchByRotationFlowY(resScoreList.get(0).getRotationFlow());
			for (ResRotationOrg resRotationOrg : resRotationOrgList) {
				list.add(resRotationOrg.getRecordFlow());
			}
		}
		for (ResRotationOrg resRotationOrg : resScoreList) {
			if (StringUtil.isNotBlank(resRotationOrg.getRecordFlow())) {
				ResRotationOrg rotationOrg=resRotationOrgBiz.searchByScoreFlow(resRotationOrg.getRecordFlow());
                if (rotationOrg.getRecordStatus().equals(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N)) {
                    rotationOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
					resRotationOrgBiz.save(rotationOrg);
				}
				list.remove(resRotationOrg.getRecordFlow());
			}else{
				resRotationOrgBiz.save(resRotationOrg);
			}
		}
		for (String flow : list) {
			ResRotationOrg resRotationOrg= resRotationOrgBiz.searchByScoreFlow(flow);
            resRotationOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
			resRotationOrgBiz.save(resRotationOrg);
		}
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}
}