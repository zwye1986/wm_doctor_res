package com.pinde.sci.ctrl.srm;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.srm.ISrmAchFileBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.model.mo.SrmAchFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/srm/file")
public class AchFileController extends GeneralController{
	private static Logger logger = LoggerFactory.getLogger(AchFileController.class);

    @Autowired
    private IFileBiz pubFileBiz;

    @Autowired
	private ISrmAchFileBiz achFileBiz;

	@RequestMapping(value = {"/achDown" }, method = RequestMethod.GET)
	public void achDown(String fileFlow, final HttpServletResponse response) throws Exception{
		SrmAchFile file = this.achFileBiz.readAchFile(fileFlow);
		pubFileBiz.down(file,response);
	}
	@RequestMapping("/delFileByFlow")
	@ResponseBody
	public String delFileByFlow(String fileFlow){
		int i = 0;
		if(StringUtil.isNotBlank(fileFlow)) {
			SrmAchFile file = achFileBiz.readAchFile(fileFlow);
			i=achFileBiz.deleteFile(file);
		}
		if(i > 0){
			return GlobalConstant.DELETE_SUCCESSED;
		}
		return GlobalConstant.DELETE_FAIL;
	}

}
