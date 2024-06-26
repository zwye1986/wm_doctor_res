package com.pinde.sci.ctrl.fstu;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.fstu.IFstuColumnManageBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.model.mo.InxColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/fstu/columnManage")
public class FstuColumnManageController extends GeneralController {
    private static Logger logger = LoggerFactory.getLogger(FstuColumnManageController.class);
    @Autowired
    private IFstuColumnManageBiz fstuColumnManageBiz;

//    @RequestMapping(value="/add")
//    public String showAdd(){
//        return "inx/manage/editColumn";
//    }
    /**
     * 显示列表
     * @return
     */
    @RequestMapping(value="/list")
    public String showList(){
        return "fstu/news/columnMaintainTree";
    }

    /**
     * 编辑栏目
     * @return
     */
    @RequestMapping(value="/editJson")
    public @ResponseBody
    String editJson(InxColumn column){
        String result = "";
        if(column!=null&&checkInput(column)){
            if(StringUtil.isNotBlank(column.getColumnFlow())){
                result = this.fstuColumnManageBiz.update(column)+"";
            }else{
                result =  this.fstuColumnManageBiz.save(column);
            }
        }
        return result;
    }
    /**
     * 检查前端输入
     * @param column
     * @return true：通过，false:不通过
     */
    private boolean checkInput(InxColumn column){
        if(StringUtil.isEmpty(column.getColumnName())||StringUtil.isBlank(column.getColumnName())){
            return false;
        }
        if(StringUtil.isEmpty(column.getParentColumnId())||StringUtil.isBlank(column.getParentColumnId())){
            return false;
        }
        if(column.getOrdinal()==null){
            return false;
        }
        return true;
    }
    /**
     * 停用
     * @return
     */
    @RequestMapping(value="/deleteJson")
    public @ResponseBody String deleteJson(@RequestBody List<String> ids){
        if(ids!=null&&!ids.isEmpty()){
            int delResult = this.fstuColumnManageBiz.updateRecordStatus(ids, GlobalConstant.RECORD_STATUS_N);
            if(delResult>0){
                return GlobalConstant.DELETE_SUCCESSED;

            }
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 获取栏目
     * @param columnId
     * @return
     */
    @RequestMapping(value="/getColumnJson")
    public @ResponseBody InxColumn  getColumnJson(String columnId){
        if(StringUtil.isNotBlank(columnId)){
            InxColumn col = this.fstuColumnManageBiz.getById(columnId);
            return col;
        }
        return null;
    }

    /**
     * 获取所有已启用栏目
     * @return 指定格式的json
     */
    @RequestMapping(value="/getAllDataJson")
    public @ResponseBody String getAllDataJson(){
        List<InxColumn> colList = fstuColumnManageBiz.getAll(GlobalConstant.RECORD_STATUS_Y);//获取所有已启用栏目
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("{\"id\":\"0\", \"pId\":\"-1\", \"name\":\"顶级栏目\",\"open\":true,\"t\":\"顶级栏目\"},");
        for (InxColumn col : colList) {
            sb.append("{");
            sb.append("\"id\":").append("\"").append(col.getColumnId()).append("\"").append(",");
            sb.append("\"pId\":").append("\"").append(StringUtil.replaceNull(col.getParentColumnId(),"0")).append("\"").append(",");
            sb.append("\"name\":").append("\"").append(col.getColumnName()).append("\",");
            sb.append("\"t\":").append("\"")
                    .append("代码：").append(col.getColumnId()).append("<br>")
                    .append("排序：").append(col.getOrdinal()==null?"":col.getOrdinal().toString())
                    .append("\"");
            sb.append("},");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return sb.toString();
    }

}
