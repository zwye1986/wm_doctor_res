package com.pinde.res.ctrl.common;

import com.pinde.core.common.GlobalConstant;
import com.pinde.app.common.InitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jiayf on 2016/4/1.
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    private static Logger logger = LoggerFactory.getLogger(CommonController.class);

    @ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
        logger.error(this.getClass().getCanonicalName()+" some error happened",e);
        return "res/hbres/500";
    }

    @RequestMapping(value={"/reLoadCfg"},method={RequestMethod.GET})
    @ResponseBody
    public String reLoadCfg(){
        InitConfig.reload();
        return com.pinde.core.common.GlobalConstant.FLAG_Y;
    }
}
