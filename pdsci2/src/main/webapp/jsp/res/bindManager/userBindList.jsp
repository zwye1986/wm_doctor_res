<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="false" />
	<jsp:param name="jquery_ui_tooltip" value="true" />
	<jsp:param name="jquery_ui_combobox" value="false" />
	<jsp:param name="jquery_ui_sortable" value="false" />
	<jsp:param name="jquery_cxselect" value="true" />
	<jsp:param name="jquery_scrollTo" value="false" />
	<jsp:param name="jquery_jcallout" value="false" />
	<jsp:param name="jquery_validation" value="true" />
	<jsp:param name="jquery_datePicker" value="true" />
	<jsp:param name="jquery_fullcalendar" value="false" />
	<jsp:param name="jquery_fngantt" value="false" />
	<jsp:param name="jquery_fixedtableheader" value="true" />
	<jsp:param name="jquery_placeholder" value="true" />
	<jsp:param name="jquery_iealert" value="false" />
</jsp:include>
<style>
	#searchForm td{border: hidden;width: 10%}
	.doctorTypeDiv {
		border: 0px;
		float: left;
		width: auto;
		line-height: 35px;
		height: 35px;
		text-align: right;
	}
	.doctorTypeLabel {
		border: 0px;
		float: left;
		width: 96px;
		line-height: 35px;
		height: 35px;
		text-align: right;
	}
	.doctorTypeContent {
		border: 0px;
		float: left;
		width: auto;
		line-height: 35px;
		height: 35px;
		text-align: right;
	}
</style>
<script type="text/javascript">
	function search(){
		$("#searchForm").submit();
	}
	
	function toPage(page) {
		if(page){
			$("#currentPage").val(page);			
		}
		search();
	}
	
	function searchUser(){
		toPage("${param.currentPage}");
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
	String.prototype.htmlFormart = function(){
		var html = this;
		for(var index in arguments){
			var reg = new RegExp('\\{'+index+'\\}','g');
			html = html.replace(reg,arguments[index]);
		}
		return html;
	};
	function removeBind(doctorFlow){
		jboxConfirm("确认解绑?一旦解绑后，无法撤回!",function(){
			jboxPost("<s:url value='/res/manager/removeBind'/>?doctorFlow="+doctorFlow,null,function(){
				window.parent.frames['mainIframe'].window.search();
				jboxClose();
			},null,true);
		});
	}
</script>
</head>
<body>
<form id="sysCfgForm" >
	<input type="hidden" id="cfgCode" value="${sessionScope.currUser.orgFlow}_bind" name="cfgCode"/>
	<input type="hidden" id="cfgValue" name="${sessionScope.currUser.orgFlow}_bind"/>
	<input type="hidden" id="wsId" value="${GlobalConstant.RES_WS_ID }" name="${sessionScope.currUser.orgFlow}_bind_ws_id"/>
	<input type="hidden" id="desc" name="${sessionScope.currUser.orgFlow}_bind_desc">
</form>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<form id="searchForm" method="post" action="<s:url value='/res/manager/userBindList/${role}'/>">
					<input id="currentPage" type="hidden" name="currentPage" value=""/>
					<div class="queryDiv">
						<div class="inputDiv">
							<label class="qlable">培训类别：</label>
							<select name="doctorCategoryId" class="qselect" >
								<option value="all">全部</option>
								<c:forEach items="${recDocCategoryEnumList}" var="category">
									<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
									<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
										<option value="${category.id}" ${(doctorCategoryId eq category.id)?'selected':''}>${category.name}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
						<c:if test="${param.doctorCategoryId eq recDocCategoryEnumIntern.id}">
							<div class="inputDiv">
								<label class="qlable">组&#12288;&#12288;别：</label>
								<select name="groupId" class="qselect" >
									<option></option>
									<c:forEach items="${dictTypeEnumResGroupList}" var="group">
										<option value="${group.dictId}" ${param.groupId eq group.dictId?'selected':''}>${group.dictName}</option>
									</c:forEach>
								</select>
							</div>
						</c:if>
						<div class="inputDiv">
							<label class="qlable">专&#12288;&#12288;业：</label>
							<select name="trainingSpeId" class="qselect" >
								<option  value="">全部</option>
								<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
									<option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="doctorTypeDiv">
							<div class="doctorTypeLabel">学员类型：</div>
							<div class="doctorTypeContent">
								<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
									<label><input type="checkbox" name="doctorTypeIdList" <c:if test="${empty param.doctorTypeIdList}" >checked</c:if> value="${type.dictId}"
										${doctorTypeSelectMap[type.dictId]}>${type.dictName}</label>
								</c:forEach>
							</div>
						</div>
						<div class="inputDiv">
							<label class="qlable">姓&#12288;&#12288;名：</label>
							<input type="text" name="doctorName" value="${param.doctorName}" class="qtext">
						</div>
						<div class="inputDiv">
							<label class="qlable">年&#12288;&#12288;级：</label>
							<select name="sessionNumber" class="qselect" >
								<option value="">全部</option>
								<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
									<option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="lastDiv" style="min-width: 230px;max-width: 230px;">
							<c:set var="key" value="${sessionScope.currUser.orgFlow}_bind"/>
							<input type="checkbox"   class="validate[required]"  name="agree" onchange="savecfg(this)"
								   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked="checked"</c:if> />是否使用绑定功能
							<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
						</div>
					</div>
				</form>
				<table class="basic" width="100%">
					<tr style="font-weight: bold;">
						<td style="text-align: center;padding: 0px;width: 8%;">姓名</td>
						<td style="text-align: center;padding: 0px;width: 5%;">性别</td>
						<td style="text-align: center;padding: 0px;width: 8%;">手机</td>
						<td style="text-align: center;padding: 0px;width: 9%;">身份证</td>
						<td style="text-align: center;padding: 0px;width: 8%;">年级</td>
						<td style="text-align: center;padding: 0px;width: 15%;">专业</td>
						<c:if test="${param.doctorCategoryId eq recDocCategoryEnumIntern.id}">
							<td style="text-align: center;padding: 0px;width: 9%;">组别</td>
						</c:if>
						<td style="text-align: center;padding: 0px;width: 5%;">绑定状态</td>
						<td style="text-align: center;padding: 0px;width: 5%;">操作</td>
					</tr>
					<c:forEach items="${doctorList}" var="doctor">
						<tr>
							<td  title="<img src='${sysCfgMap['upload_base_url']}/${doctor.sysUser.userHeadImg}' onerror='defaultImg(this);' style='width: 110px;height: 130px;'/>"
									style="text-align: center;padding: 0px;">${doctor.doctorName}</td>
							<td style="text-align: center;padding: 0px;">${doctor.sysUser.sexName}</td>
							<td style="text-align: center;padding: 0px;">${doctor.sysUser.userPhone}</td>
							<td style="text-align: center;padding: 0px;">${doctor.sysUser.idNo}</td>
							<td style="text-align: center;padding: 0px;">${doctor.sessionNumber}</td>
							<td style="text-align: center;padding: 0px;">${doctor.trainingSpeName}</td>
							<c:if test="${param.doctorCategoryId eq recDocCategoryEnumIntern.id}">
								<td style="text-align: center;padding: 0px;">
										${doctor.groupId eq group.dictId?group.dictName:''}
								</td>
							</c:if>
							<td style="text-align: center;padding: 0px;">
								<c:if test="${not empty macidMap[doctor.doctorFlow] and not empty macidMap[doctor.doctorFlow].macId}">
									已绑定
								</c:if>
								<c:if test="${!(not empty macidMap[doctor.doctorFlow] and not empty macidMap[doctor.doctorFlow].macId)}">
									未绑定
								</c:if>
							</td>
							<td style="text-align: center;padding: 0px;">
								<c:if test="${not empty macidMap[doctor.doctorFlow] and not empty macidMap[doctor.doctorFlow].macId}">
									<a style="color: blue;cursor: pointer;" onclick="removeBind('${doctor.doctorFlow}');">解绑</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty doctorList}">
						<tr><td style="text-align: center;" colspan="10">无记录</td></tr>
					</c:if>
				</table>
				<div>
				   	<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"/>
					<pd:pagination toPage="toPage"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>