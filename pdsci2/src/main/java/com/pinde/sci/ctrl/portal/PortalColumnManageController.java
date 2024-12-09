package com.pinde.sci.ctrl.portal;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.portal.IPortalColumnManageBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.PortalColumn;
import com.pinde.core.model.SysUser;
import com.pinde.sci.model.mo.SysUserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/portal/manage/column")
public class PortalColumnManageController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(PortalColumnManageController.class);
	@Autowired
	private IPortalColumnManageBiz columnBiz;
	@Autowired
	private IRoleBiz roleBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	
	@RequestMapping(value="/add")
	public String showAdd(){
		return "portal/manage/editColumn";
	}
	/**
	 * 显示列表
	 * @return
	 */
	@RequestMapping(value="/list")
	public String showList(){
		return "portal/manage/tree";
	}
	
	/**
	 * 编辑栏目
	 * @return 
	 */
	@RequestMapping(value="/editJson")
	public @ResponseBody String editJson(PortalColumn column){
		String result = "";
		if(column!=null&&checkInput(column)){
			if(StringUtil.isNotBlank(column.getColumnFlow())){
				result = this.columnBiz.update(column)+"";
			}else{
				result =  this.columnBiz.save(column);
			}
		}
		return result;
	}
	/**
	 * 检查前端输入
	 * @param column
	 * @return true：通过，false:不通过
	 */
	private boolean checkInput(PortalColumn column){
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
            int delResult = this.columnBiz.updateRecordStatus(ids, com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
			if(delResult>0){
                return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
			
			}
		}
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
	}

	/**
	 * 获取栏目
	 * @param columnId
	 * @return
	 */
	@RequestMapping(value="/getColumnJson")
	public @ResponseBody PortalColumn  getColumnJson(String columnId){
		if(StringUtil.isNotBlank(columnId)){
			PortalColumn col = this.columnBiz.getById(columnId);
			return col;
		}
		return null;
	}
	
	/**
	 * 获取所有已启用栏目
	 * @return 指定格式的json
	 */
	@RequestMapping(value="/getAllDataJson")
	public @ResponseBody String getAllDataJson(String []ids){
		List<String> columnIds=null;
		if(ids!=null&&ids.length>0)
		{
			columnIds= Arrays.asList(ids);
		}
        List<PortalColumn> colList = columnBiz.getAll(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y, columnIds);//获取所有已启用栏目
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append("{\"id\":\"0\", \"pId\":\"-1\", \"name\":\"顶级栏目\",\"open\":true,\"t\":\"顶级栏目\"},");
		for (PortalColumn col : colList) {
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

	@RequestMapping(value="/getAllDataJsonByUser")
	public @ResponseBody String getAllDataJsonByUser(){
		SysUser user= GlobalContext.getCurrentUser();
		List<SysUserRole> userRoles=userRoleBiz.getByUserFlow(user.getUserFlow());

		List<String> columnIds=new ArrayList<>();
		if(userRoles!=null)
		{
			for(SysUserRole role:userRoles)
			{
				List<String> ids=roleBiz.getColumn(role.getRoleFlow());
				if(ids!=null&&ids.size()>0)
				{
					columnIds.addAll(ids);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append("{\"id\":\"0\", \"pId\":\"-1\", \"name\":\"顶级栏目\",\"open\":true,\"t\":\"顶级栏目\"},");
		if(columnIds!=null&&columnIds.size()>0) {
            List<PortalColumn> colList = columnBiz.getAll(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y, columnIds);//获取所有已启用栏目
			if(colList!=null) {
				for (PortalColumn col : colList) {
					sb.append("{");
					sb.append("\"id\":").append("\"").append(col.getColumnId()).append("\"").append(",");
					sb.append("\"pId\":").append("\"").append(StringUtil.replaceNull(col.getParentColumnId(), "0")).append("\"").append(",");
					sb.append("\"name\":").append("\"").append(col.getColumnName()).append("\",");
					sb.append("\"t\":").append("\"")
							.append("代码：").append(col.getColumnId()).append("<br>")
							.append("排序：").append(col.getOrdinal() == null ? "" : col.getOrdinal().toString())
							.append("\"");
					sb.append("},");
				}
			}
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		return sb.toString();
	}

}
