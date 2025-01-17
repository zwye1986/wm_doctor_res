<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<style type="text/css">
	#boxHome .item:HOVER{background-color: #eee;}
</style>
<script type="text/javascript">
function add(orgFlow) {
	jboxOpen("<s:url value='/sys/user/edit/${sessionScope.userListScope}'/>?orgFlow="+orgFlow,"新增用户信息", 900, 400);
}
function edit(userFlow) {
	jboxOpen("<s:url value='/sys/user/edit/${sessionScope.userListScope}?userFlow='/>"+ userFlow,"编辑用户信息", 900, 400);
}
function allotRole(userFlow){
	jboxOpen("<s:url value='/sys/user/allotRole?userFlow='/>"+ userFlow,"分配用户角色", 900, 500);
}
function listRegion(userFlow){
	jboxStartLoading();
	var url = "<s:url value='/erp/crm/listRegion?userFlow='/>" + userFlow;
	var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='800px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
	jboxMessager(iframe,"用户地区权限", 900, 500);
}
function resetPasswd(userFlow){
	jboxConfirm("确认将该用户的密码重置为:${pdfn:getInitPass()} 吗？",function () {
		var url = "<s:url value='/sys/user/resetPasswd?userFlow='/>"+userFlow;
		jboxGet(url,null,function(){
			//searchUser();			
		});		
	});
}
function auditSrmUser(userFlow){
	jboxOpen("<s:url value='/srm/user/auditUI?userFlow='/>"+userFlow ,"用户注册审核" ,  800 , 600);
}
function auditEduUser(userFlow){
    jboxOpen("<s:url value='/edu/user/auditUI?userFlow='/>"+userFlow ,"用户注册审核" ,  800 , 600);
}
function activate(userFlow){
	jboxConfirm("确认恢复启用该用户吗？",function () {
		var url = "<s:url value='/sys/user/activate?userFlow='/>"+userFlow;
		jboxGet(url,null,function(){
			searchUser();			
		});
	});
}

function unLock(userFlow){
	jboxConfirm("确认解锁该用户吗？",function () {
		var url = "<s:url value='/sys/user/activate?userFlow='/>"+userFlow;
		jboxGet(url,null,function(){
			searchUser();
		});
	});
}

function stop(userFlow) {
	<%--var url = "<s:url value='/sys/user/reasonForLockUser?userFlow='/>" + userFlow;--%>
	<%--jboxOpen(url, "停用用户", 500, 200);--%>

	jboxConfirm("确认停用该用户吗？",function () {
		var url = "<s:url value='/sys/user/lockUser?userFlow='/>"+userFlow;
		jboxPost(url,null,function(){
			searchUser();
		});
	});
}

function lock(userFlow){
	jboxConfirm("确认锁定该用户吗？锁定后该用户将不能登录系统！",function () {
		var url = "<s:url value='/sys/user/lock?userFlow='/>"+userFlow;
		jboxGet(url,null,function(){
			searchUser();			
		});		
	});
}
function searchUser(){
	jboxStartLoading();
	$("#searchForm").submit();
}
function exportUser(){
	var url = "<s:url value='/sys/user/exportUserlist/${sessionScope.userListScope}'/>";
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
}
function searchDept(orgFlow,deptFlow) {
	if(orgFlow==""){
		return;
	}
	var url = "<s:url value='/sys/user/getDept'/>?orgFlow="+orgFlow+"&deptFlow="+deptFlow;
	jboxGet(url,null,function(resp){
		$("#deptSelectId").html(resp);
	},null,false);
}
function toPage(page) {
	if(page!=undefined){
		$("#currentPage").val(page);			
	}
	searchUser();
}

function editDoc(doctorFlow,userFlow){
	jboxOpen("<s:url value='/res/platform/editDocSimple'/>?doctorFlow="+userFlow,"编辑医师信息",950,475);
// 	jboxOpen("<s:url value='/res/doc/user/editDoc'/>?roleFlag=${GlobalConstant.USER_LIST_GLOBAL}&doctorFlow="+doctorFlow+"&userFlow="+userFlow,(doctorFlow=='')?"新增医师信息":"编辑医师信息",1100,500);
}

function importUsers(){
	var url = "<s:url value='/sys/user/importUsers'/>";
	jboxOpen(url, "人员导入", 700, 250);
}

function deleteUser(userFlow){
	jboxConfirm("确认删除该用户吗？",function () {
		var url = "<s:url value='/sys/user/delete?userFlow='/>"+userFlow+"&wsid=${sessionScope.currWsId}";
		jboxGet(url,null,function(){
			searchUser();			
		});		
	});
}


/**
*模糊查询加载
*/
(function($){
	$.fn.likeSearchInit = function(option){
		option.clickActive = option.clickActive || null;

		var searchInput = this;
		searchInput.on("keyup focus",function(){
			$("#boxHome").show();
			if($.trim(this.value)){
				$("#boxHome .item").hide();
				var items = $("#boxHome .item[value*='"+this.value+"']").show();
				if(!items){
					$("#boxHome").hide();
				}
			}else{
				$("#boxHome .item").show();
			}
		});
		searchInput.on("blur",function(){
			if(!$("#boxHome.on").length){
				$("#boxHome").hide();
			}
		});
		$("#boxHome").on("mouseenter mouseleave",function(){
			$(this).toggleClass("on");
		});
		$("#boxHome .item").click(function(){
			$("#boxHome").hide();
			var value = $(this).attr("value");
			$("#itemName").val(value);
			searchInput.val(value);
			if(option.clickActive){
				option['clickActive']($(this).attr("flow"));
			}
		});
	};
})(jQuery);
//======================================
//页面加载完成时调用
$(function(){
	$("#orgSel").likeSearchInit({
		/* clickActive:function(flow){
			$("#studyUserFlow").val(flow).change();
		} */ 
	});
});
//学会登记
function societyLoad(userFlow){
    var url = "<s:url value='/srm/learning/sociery/registerSociety?userFlow='/>" + userFlow;
    jboxOpen(url, "学会任职登记", 800, 600);
}
//医院设置超级密码可登陆（所属机构下的学员，带教，科主任）账号
function setPwd(orgFlow){
    var url = "<s:url value='/sys/user/setHospitalPwd?orgFlow='/>" + orgFlow;
    jboxOpen(url, "超级密码设置", 360, 160);
}

// 解除微信绑定
function unLockWeChat(userFlow){
	jboxConfirm("确认解除该用户微信公众号绑定信息吗？",function () {
		var url = "<s:url value='/sys/user/unLockWeChat?userFlow='/>"+userFlow;
		jboxPost(url,null,function(){
			searchUser();
		});
	});
}
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				 <form id="searchForm" action="<s:url value="/sys/user/list/${sessionScope.userListScope}" />" method="post">
					 <div class="queryDiv">
						 <div class="inputDiv">
							 <c:if test="${sessionScope.userListScope ne GlobalConstant.USER_LIST_LOCAL }">
								 <label class="qlable">机&#12288;&#12288;构：</label>
								 <input id="orgSel" class="toggleView qtext" type="text" name="orgName" value="${param.orgName}" autocomplete="off"/>
								 <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:34px;left:100px;">
									 <div id="boxHome" style="max-height: 250px;overflow:auto;border: 1px #ccc solid;background-color: white;width:155px;border-top: none;position: relative;display: none;">
										 <c:forEach items="${applicationScope.sysOrgList}" var="org">
											 <p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="white-space:nowrap;text-align: left;">${org.orgName}</p>
										 </c:forEach>
									 </div>
								 </div>
							 </c:if>
							 <c:if test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_LOCAL }">
								 <label class="qlable">部&#12288;&#12288;门：</label>
								 <span id="deptSelectId">
									 <script type="text/javascript">
										 $(document).ready(function(){
											 searchDept('${sessionScope.currUser.orgFlow}','${param.deptFlow}');
											 $("#deptFlow").live("change",function (){
												 searchUser();
											 });
										 });
									 </script>
								 </span>
							 </c:if>
						 </div>
						 <div class="inputDiv">
							 <label class="qlable">身&nbsp;&nbsp;份&nbsp;&nbsp;证：</label>
							 <input type="text" name="idNo" value="${param.idNo}" class="qtext "/>
						 </div>
						 <div class="inputDiv">
							 <label class="qlable">手&nbsp;&nbsp;机&nbsp;&nbsp;号：</label>
							 <input type="text" name="userPhone" value="${param.userPhone}" class="qtext"/>
						 </div>
						 <div class="inputDiv" style="min-width:344px;max-width:400px;margin-left:-10px;">
							 <label class="qlable">用户状态：</label>
							 <input id="all" name="statusId" type="radio" value="" onclick="searchUser();" <c:if test='${empty param.statusId}'>checked="checked"</c:if>>
							 <label for="all">全部</label>&#12288;
							 <c:if test="${sessionScope.currWsId==GlobalConstant.EDC_WS_ID}">
								 <input id="${userStatusEnumAdded.id}" name="statusId" type="radio" value="${userStatusEnumAdded.id}" onclick="searchUser();" <c:if test='${param.statusId==userStatusEnumAdded.id}'>checked="checked"</c:if>>
								 <label for="${userStatusEnumAdded.id }">${userStatusEnumAdded.name}</label>&#12288;
							 </c:if>
							 <c:if test="${sessionScope.currWsId==GlobalConstant.SRM_WS_ID}">
								 <input id="${userStatusEnumReged.id}" name="statusId" type="radio" value="${userStatusEnumReged.id}" onclick="searchUser();" <c:if test='${param.statusId==userStatusEnumReged.id}'>checked="checked"</c:if>>
								 <label for="${userStatusEnumReged.id }">${userStatusEnumReged.name}</label>&#12288;
							 </c:if>
							 <input id="${userStatusEnumActivated.id}" name="statusId" type="radio" value="${userStatusEnumActivated.id}" onclick="searchUser();" <c:if test='${param.statusId==userStatusEnumActivated.id}'>checked="checked"</c:if>>
							 <label for="${userStatusEnumActivated.id }">${userStatusEnumActivated.name}</label>&#12288;
							 <input id="${userStatusEnumLocked.id}" name="statusId" type="radio" value="${userStatusEnumLocked.id}" onclick="searchUser();" <c:if test='${param.statusId==userStatusEnumLocked.id}'>checked="checked"</c:if>>
							 <label for="${userStatusEnumLocked.id }">${userStatusEnumLocked.name}</label>&#12288;
							 <input id="${userStatusEnumSysLocked.id}" name="statusId" type="radio" value="${userStatusEnumSysLocked.id}" onclick="searchUser();" <c:if test='${param.statusId==userStatusEnumSysLocked.id}'>checked="checked"</c:if>>
							 <label for="${userStatusEnumSysLocked.id }">${userStatusEnumSysLocked.name}</label>&#12288;
							 <input id="currentPage" type="hidden" name="currentPage" value=""/>
						 </div>
						 <c:if test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_LOCAL }">
							 <input type="hidden" name="orgFlow" value="${sessionScope.currUser.orgFlow }"/>
						 </c:if>
						 <div class="inputDiv">
							 <label class="qlable">姓&#12288;&#12288;名：</label>
							 <input type="text" name="userName" value="${param.userName}" class="qtext"/>
						 </div>
						 <div class="inputDiv">
							 <label class="qlable">登&nbsp;&nbsp;录&nbsp;&nbsp;名：</label>
							 <input type="text" name="userCode" value="${param.userCode}" class="qtext"/>
						 </div>
						 <div class="inputDiv">
							 <label class="qlable">角&#12288;&#12288;色：</label>
							 <select name="roleFlow" class="qselect">
								 <option value="">全部</option>
								 <c:forEach items="${sysRoleList}" var="sysRole">
									 <option value="${sysRole.roleFlow}" <c:if test="${param.roleFlow eq sysRole.roleFlow}">selected</c:if>>${sysRole.roleName}</option>
								 </c:forEach>
							 </select>
						 </div>
						 <div class="lastDiv">
							 <input type="button" class="searchInput" onclick="searchUser();" value="查&#12288;询" style="margin-left: 0px;">
						 </div>
					 </div>
					 <div class="funcDiv">
						 <c:if test="${sessionScope.currWsId!=GlobalConstant.SRM_WS_ID}">
							 <input type="button" class="search" onclick="add($('#orgFlow').val());" value="新&#12288;增">
						 </c:if>
						 <c:if test="${sessionScope.currWsId==GlobalConstant.SRM_WS_ID}">
							 <c:if test="${applicationScope.sysCfgMap['srm_add_user_flag'] ==  GlobalConstant.FLAG_Y }">
								 <input type="button" class="search" onclick="add($('#orgFlow').val());" value="新&#12288;增">
							 </c:if>
						 </c:if>
						 <input type="button" class="search" onclick="importUsers();" value="人员导入">
						 <%--<input type="button" class="search" onclick="exportUser();" value="导&#12288;出">--%>
					 </div>
				</form>
			</div>
			<table class="xllist" style="min-width: 999px;">
				<tr>
					<th width="50px">姓名</th>
					<th width="50px">状态</th>
					<th width="30px">性别</th>
					<c:if test="${empty param.orgFlow and sessionScope.currWsId!='erp'}">
					<c:if test="${sessionScope.userListScope==GlobalConstant.USER_LIST_GLOBAL }">
					<th width="160px">机构</th>
					</c:if>
					</c:if>
					<c:if test="${(sessionScope.currWsId=='srm' and applicationScope.sysCfgMap['srm_for_use']=='local') or sessionScope.currWsId=='irb' or sessionScope.currWsId=='gcp' or sessionScope.currWsId=='erp' or sessionScope.currWsId=='res'}">
					<th width="60px">部门</th>
					</c:if>
					<th width="140px">身份证</th>
					<th width="60px">手机</th>
					<th width="120px">邮件</th>
					<th width="120px">角色</th>
					<c:if test="${applicationScope.sysCfgMap['sys_weixin_qiye_flag']==GlobalConstant.FLAG_Y}">
					<th width="60px">微信状态</th>
					</c:if>
					<th width="275px" >操作</th>
				</tr>
					<c:set var="userNum" value="0"></c:set>
					<c:forEach items="${sysUserList}" var="sysUser">
					<c:set value="false" var="isDoctor"></c:set>
					<c:if test="${sysUser.userCode!=GlobalConstant.ROOT_USER_CODE or sessionScope.currUser.userCode==GlobalConstant.ROOT_USER_CODE }">
					<c:set var="userNum" value="${userNum+1 }"></c:set>

					<tr style="height:20px ">
						<td title="${sysUser.userCode}">${sysUser.userName}</td>
						<td>${sysUser.statusDesc}</td>
						<td>${sysUser.sexName}</td>
						<c:if test="${empty param.orgFlow and sessionScope.currWsId!='erp' }">
						<c:if test="${sessionScope.userListScope==GlobalConstant.USER_LIST_GLOBAL }">
						<td>${sysUser.orgName}</td>
						</c:if>
						</c:if>
						<c:if test="${(sessionScope.currWsId=='srm' and applicationScope.sysCfgMap['srm_for_use']=='local') or sessionScope.currWsId=='irb' or sessionScope.currWsId=='gcp' or sessionScope.currWsId=='erp' or sessionScope.currWsId=='res'}">
						<td>${sysUser.deptName}</td>
						</c:if>
						<td>${sysUser.idNo}</td>
						<td>${sysUser.userPhone}</td>
						<td>${sysUser.userEmail}</td>
						<td style="text-align: left;">
							<c:forEach items="${sysUserRoleMap[sysUser.userFlow]}" var="sysUserRole" varStatus="status">
								${status.index+1}&nbsp;${applicationScope.sysRoleMap[sysUserRole.roleFlow].roleName }
								<c:if test="${not status.last }">,</c:if>
								<c:if test="${applicationScope.sysRoleMap[sysUserRole.roleFlow].roleFlow eq applicationScope.sysCfgMap['res_doctor_role_flow']}">
									<c:set value="true" var="isDoctor"></c:set>
								</c:if>
							</c:forEach>
						</td>
						<c:if test="${applicationScope.sysCfgMap['sys_weixin_qiye_flag']==GlobalConstant.FLAG_Y}">
						<td width="60px" title="${sysUser.weiXinId}">${sysUser.weiXinStatusDesc}</td>
						</c:if>
						<td style="text-align: left;padding-left: 5px;">
							<c:if test="${sysUser.statusId==userStatusEnumAdded.id}">
								等待验证...
							</c:if>
							<c:if test="${sysUser.statusId==userStatusEnumReged.id}">
								<c:if test="${sessionScope.currWsId==GlobalConstant.SRM_WS_ID}">
									[<a href="javascript:auditSrmUser('${sysUser.userFlow}');" >审核</a>]
								</c:if>
								<c:if test="${sessionScope.currWsId==GlobalConstant.EDU_WS_ID}">
									[<a href="javascript:auditEduUser('${sysUser.userFlow}');" >审核</a>]
								</c:if>
							<c:if test="${sessionScope.currUser.userCode==GlobalConstant.ROOT_USER_CODE}"> |
							[<a href="javascript:edit('${sysUser.userFlow}');" >编辑</a>]
							</c:if>
							</c:if>
							<c:if test="${sysUser.statusId==userStatusEnumActivated.id}">
								[<a href="javascript:edit('${sysUser.userFlow}');" >编辑</a>] |
								[<a href="javascript:allotRole('${sysUser.userFlow}');" >分配角色</a>] |
								[<a href="javascript:resetPasswd('${sysUser.userFlow}');" >重置密码</a>] |
							</c:if>
							<c:choose>
								<c:when test="${userStatusEnumLocked.id eq  sysUser.statusId}">
									[<a style="color: blue;cursor: pointer; " onclick="activate('${sysUser.userFlow}');">启用</a>]
								</c:when>
								<c:otherwise>
									[<a style="color: blue;cursor: pointer; " onclick="stop('${sysUser.userFlow}');">停用</a>] |
								</c:otherwise>
							</c:choose>

							<c:choose>
								<c:when test="${userStatusEnumSysLocked.id eq  sysUser.statusId}">
									[<a style="color: blue;cursor: pointer; " onclick="unLock('${sysUser.userFlow}');">解锁</a>]
								</c:when>
								<c:otherwise>
										<font style="color: #8a8a8a">[解锁]</font>
								</c:otherwise>
							</c:choose>

							<c:if test="${GlobalConstant.FLAG_Y eq applicationScope.sysCfgMap['sys_user_delete_flag']}">
							 | [<a href="javascript:deleteUser('${sysUser.userFlow}');" >删除</a>]
							 </c:if>
							<c:if test="${GlobalConstant.FLAG_Y eq applicationScope.sysCfgMap['srm_local_type']}">
								| [<a href="javascript:societyLoad('${sysUser.userFlow}');" >学会任职</a>]
							</c:if>
							<c:if test="${applicationScope.sysCfgMap['res_admin_role_flow'] eq param.roleFlow}">
								| [<a href="javascript:setPwd('${sysUser.orgFlow}');" >超级密码</a>]
							</c:if>
							<%-- [<a href="javascript:markExpert('${sysUser.userFlow}');" >标记为专家</a>] --%>
							<c:if test="${not empty sysUser.openId}">
								| [<a href="javascript:unLockWeChat('${sysUser.userFlow}');" >解除公众号绑定</a>]
							</c:if>
						</td>
					</tr>
					</c:if>
				   </c:forEach>
				   <c:if test="${userNum == 0 }">
						<c:set var="colNum" value="10"></c:set>
						<c:if test="${sessionScope.currWsId=='srm' and applicationScope.sysCfgMap['srm_for_use']=='local'}">
							<c:set var="colNum" value="10"></c:set>
						</c:if>
						<tr>
							<td align="center" colspan="${colNum }">无记录</td>
						</tr>
					</c:if>
			</table>
			<c:set var="pageView" value="${pdfn:getPageView(sysUserList)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>
		</div>
	</div>
</body>
</html>