package com.pinde.app.common;

import com.pinde.core.util.AjaxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class GeneralController {

    private static Logger logger = LoggerFactory.getLogger(GeneralController.class);

    @ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
        logger.error(this.getClass().getCanonicalName() + " some error happened", e);
        boolean isAjax = AjaxUtil.isAjaxRequest(request);
        if (isAjax) {
            e.printStackTrace();
            return e.getMessage();
        } else {
            e.printStackTrace();
            request.setAttribute("exception", e);
            return "error/500";
        }
    }

    //格式化返回数据
    public Object ResultData(String code, String msg, Object data) {
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("code", code);
        resultData.put("msg", msg);
        resultData.put("data", data);
        return resultData;
    }

    //格式化返回数据
    public Object ResultData(Object data) {
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("code", "200");
        resultData.put("msg", "操作成功");
        resultData.put("data", data);
        return resultData;
    }

    public Object ResultDataIndex(Object data) {
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("code", "Index");
        resultData.put("msg", "首页信息");
        resultData.put("data", data);
        return resultData;
    }

    public Object ResultData500(Object data) {
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("code", "500");
        resultData.put("msg", "服务器发生错误");
        resultData.put("data", data);
        return resultData;
    }

    public Object ResultData404(Object data) {
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("code", "404");
        resultData.put("msg", "系统页面不存在！");
        resultData.put("data", data);
        return resultData;
    }

    public Object ResultDataTimeout(Object data) {
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("code", "timeout");
        resultData.put("msg", "你没有登录系统或长时间未操作，请重新登录...");
        resultData.put("data", data);
        return resultData;
    }

    public Object ResultDataNoToken(Object data) {
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("code", "noToken");
        resultData.put("msg", "token丢失，请重新登录...");
        resultData.put("data", data);
        return resultData;
    }

    public Object ResultDataNoAccess(Object data) {
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("code", "noAccess");
        resultData.put("msg", "你没有授权公众号,请重新授权...");
        resultData.put("data", data);
        return resultData;
    }

    public Object ResultDataUserNotSame(Object data) {
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("code", "userNotSame");
        resultData.put("msg", "token与当前登录人不一致，请重新登录...");
        resultData.put("data", data);
        return resultData;
    }

    public Object ResultDataNoLicense(Object data) {
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("code", "noLicense");
        resultData.put("msg", "亲们，系统内部出错，错误码：999，请联系系统管理员。");
        resultData.put("data", data);
        return resultData;
    }

    public Object ResultDataPath(Object data) {
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("code", "path");
        resultData.put("msg", "无效访问地址");
        resultData.put("data", data);
        return resultData;
    }

    //格式化返回数据
    public Object ResultDataThrow(String msg) {
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("resultId", "Throw");
        resultData.put("msg", msg);
        resultData.put("data", null);
        return resultData;
    }

    //格式化返回数据
    public Object ResultData201(String msg) {
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("code", "201");
        resultData.put("msg", msg);
        resultData.put("data", null);
        return resultData;
    }

}
