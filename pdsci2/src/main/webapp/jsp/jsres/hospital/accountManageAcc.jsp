<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	toPage();
});

function toPage(page){
	if(page != undefined){
		$("#currentPage").val(page);			
	}
	search();
}

function activate(userFlow){
    jboxConfirm("确认解锁该用户吗？",function () {
        var url = "<s:url value='/sys/user/activate?userFlow='/>"+userFlow;
        jboxGet(url,null,function(){
            search();
        });
    });
}
function search(){
	var url ="<s:url value='/jsres/doctor/accountList'/>?baseFlag=${param.baseFlag}";
	jboxPostLoad("contentDiv", url, $("#searchForm").serialize(), true);
}
<%--
function lock(userFlow){
	var title = "确认锁定该用户吗？锁定后该用户将不能登录系统！";
	jboxConfirm(title, function () {
		var url = "<s:url value='/jsres/doctor/lock?userFlow='/>"+userFlow;
		jboxGet(url,null,function(){
			search();
		});
	});
}

function unLock(userFlow){
	var title = "确认解锁该用户吗？";
	jboxConfirm(title, function () {
		var url = "<s:url value='/jsres/doctor/unLock?userFlow='/>"+userFlow;
		jboxGet(url,null,function(){
			search();
		});
	});
}
--%>

function resetPasswd(userFlow){
	var title = "确认将该用户的密码重置为:${pdfn:getInitPass()} 吗？";
	jboxConfirm(title, function () {
		var url = "<s:url value='/jsres/doctor/resetPasswd?userFlow='/>"+userFlow;
		jboxGet(url,null,function(){
			//search();
		});		
	});
}

function savecfg(obj)
{

	if ($(obj).attr("checked")=="checked") {
		$("#cfgValue").val("Y");
	} else {
		$("#cfgValue").val("N");
	}
	var url = "<s:url value='/jsres/powerCfg/saveOne'/>";
	jboxPost(url, $('#sysCfgForm').serialize(), function(resp){
		if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
			jboxTip("操作成功！");
		}else{
			jboxTip("操作失败！");
		};
	}, null, false);
}

function removeBind(doctorFlow){
	jboxConfirm("确认解绑?一旦解绑后，无法撤回!",function(){
		jboxPost("<s:url value='/res/manager/removeBind'/>?doctorFlow="+doctorFlow,null,function(){
			search();
			jboxClose();
		},null,true);
	});
}
function unlockLogin(doctorFlow,userName){
	var url = "<s:url value='/res/manager/queryStudentUntiedRecording'/>?doctorFlow=" + doctorFlow +"&userName="+userName;
	jboxOpen(url, "解锁", 700, 500);
}
function lokeStudentUntiedRecording(doctorFlow){
	var url = "<s:url value='/res/manager/studentUntiedRecordingList'/>?doctorFlow=" + doctorFlow;
	jboxOpen(url, "解锁记录详情", 800, 600);
}
function changeTrainSpes(t){
	var trainCategoryid=$("#trainingTypeId").val();
	if(trainCategoryid==""){
		$("select[name=trainingSpeId] option[value != '']").remove();
		return false;
	}
	$("select[name=trainingSpeId] option[value != '']").remove();
	$("#"+trainCategoryid+"_select").find("option").each(function(){
		$(this).clone().appendTo($("#trainingSpeId"));
	});
	return false;
}
</script>


<div class="main_hd">
    <h2 class="underline">学员帐号管理</h2>
</div>
<div class="main_bd">
	<div class="div_search">

		<form id="sysCfgForm" >
			<input type="hidden" id="cfgCode" value="${sessionScope.currUser.orgFlow}_bind" name="cfgCode"/>
			<input type="hidden" id="cfgValue" name="${sessionScope.currUser.orgFlow}_bind"/>
			<input type="hidden" id="wsId" value="${GlobalConstant.RES_WS_ID }" name="${sessionScope.currUser.orgFlow}_bind_ws_id"/>
			<input type="hidden" id="desc" name="${sessionScope.currUser.orgFlow}_bind_desc">
		</form>
		<form id="searchForm">
			<table class="basic" width="100%" style="margin-top: 10px;">
			<input type="hidden" name="currentPage" id="currentPage"/>
			<tr>
				<td>姓&#12288;名:</td>
				<td><input type="text" class="input" name="userName" value="${param.userName}" style="width:100px; margin:0px"/></td>
				<td>手机号码：</td>
				<td><input type="text" class="input" name="userPhone" value="${param.userPhone}" style="width:100px; margin:0px"/>&#12288;</td>
				<td>证件号码：</td>
				<td><input type="text" class="input" name="idNo" value="${param.idNo}" style="width:100px;margin: 0 0"/>&#12288;</td>
				<td >注册帐号：</td>
				<td><input type="text" class="input" name="userCode" value="${param.userCode}" style="width:160px;"/>&#12288;</td>
			</tr>
			<tr>
				<td class="td_left">培训类别：</td>
				<td><select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" style="width:107px;">
					<option value="AssiGeneral" >助理全科</option>
					<%--<option value="">请选择</option>
					<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
						<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
					</c:forEach>--%>
				</select>
				</td>
				<td class="td_left">培训专业：</td>
				<td><select name="trainingSpeId" id="trainingSpeId"class="select" style="width: 107px;">
					<option value="">全部</option>
				</select>
				</td>
				<%--<td>专&#12288;业：</td>
				<td>
					<select name="trainingSpeId" id="trainingSpeId" class="select" style="width: 107px;">
						<option value="">全部</option>
						<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="spe">
							<option value="${spe.dictId}"
									<c:if test="${param.trainingSpeId eq spe.dictId}">selected</c:if> >${spe.dictName}
							</option>
						</c:forEach>
					</select>
				</td>--%>
				<td>账号状态:</td>
				<td>
					<select name="lockStatus" id="lockStatus" class="select" style="width:107px;" >
						<option value="">全部</option>
						<option value="Locked"  >已锁定</option>
						<option value="Activated">已激活</option>
					</select>&#12288;
				</td>
				<td colspan="2">
					<c:set var="key" value="${sessionScope.currUser.orgFlow}_bind"/>
					<input type="checkbox"   class="validate[required]"  name="agree" onchange="savecfg(this)"
						   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked="checked"</c:if> />&#12288;是否开启手机绑定功能
				</td>

				<td  colspan="2"> <input class="btn_green" type="button" onclick="toPage(1)" value="查&#12288;询"/></td>
			</tr>
			</table>
		</form>
	</div>
	
	<div id="contentDiv">
	  
	</div>
	<div style="display: none;">
		<select id="WMFirst_select">
			<c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
				<c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
					<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
				</c:if>
			</c:forEach>
		</select>
		<select id="WMSecond_select">
			<c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
				<c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
					<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
				</c:if>
			</c:forEach>
		</select>
		<select id="AssiGeneral_select">
			<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
				<c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
					<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
				</c:if>
			</c:forEach>
		</select>
		<select id="DoctorTrainingSpe_select">
			<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
				<c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
					<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
				</c:if>
			</c:forEach>
		</select>

	</div>
</div>
