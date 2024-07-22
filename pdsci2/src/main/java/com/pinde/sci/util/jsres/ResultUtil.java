package com.pinde.sci.util.jsres;


import com.pinde.sci.model.jsres.ResultVo;

/**
 * @Author zsq
 * @Date Created
 */
public class ResultUtil {
    public static ResultVo exec(boolean istrue, String msg, Object data) {
        ResultVo resultVo = new ResultVo();
        if (istrue) {
            resultVo.setCode(200);
        } else {
            resultVo.setCode(500);
        }
        resultVo.setMsg(msg);
        resultVo.setData(data);
        return resultVo;
    }

}
