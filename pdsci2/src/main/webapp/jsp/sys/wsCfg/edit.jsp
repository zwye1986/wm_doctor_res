<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
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
<script type="text/javascript">
	function save(){

		if(false==$("#sysCfgForm").validationEngine("validate")){
			return ;
		}
		if("${param.isEdit}"=="N") {
			var url = "<s:url value='/sys/wsCfg/check'/>";
			jboxPost(url, $('#sysCfgForm').serialize(), function (resp) {
				if (resp == "1") {
					jboxConfirm("该工作站配置已存在，保存后将覆盖原有信息，确认保存?", function () {
						submitInfo();
					});
				} else {
					submitInfo();
				}
			}, null, false);
		}else{
			submitInfo();
		}
	}
	function submitInfo()
	{
		var url = "<s:url value='/sys/wsCfg/save'/>";
		jboxPost(url, $('#sysCfgForm').serialize(), function(resp){
			if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
				jboxTip("保存成功！");
				window.parent.frames['mainIframe'].window.search();
				jboxClose();
			}else{
				jboxTip("保存失败！");
			}
		}, null, false);
	}
</script>
</head>
<body>
<div class="content">
	<div class="title1 clearfix">
		<div id="tagContent">
			<div class="tagContent selectTag" id="tagContent0">
				<div>
					<form id="sysCfgForm">
					<table style="width: 100%;" cellpadding="0" cellspacing="0" class="basic">
						<tr>
							<td>工作站名称</td>
							<td>
								<c:if test="${empty config.wsId}">
									<select name="wsId" class="validate[required] xlselect" style="width: 256px;">
										<option value="">--请选择--</option>
										<c:forEach items="${licenseedWorkStation}" var="wsId">
											<option value="${wsId}" <c:if test="${config.wsId eq wsId}">selected="selected"</c:if> >${workStationMap[wsId].workStationName }</option>
										</c:forEach>
									</select>
								</c:if>
								<c:if test="${not empty config.wsId}">
									<input name="wsId" value="${config.wsId}" type="hidden">
									<c:forEach items="${licenseedWorkStation}" var="wsId">
										 <c:if test="${config.wsId eq wsId}">${workStationMap[wsId].workStationName }</c:if>
									</c:forEach>
								</c:if>
							</td>
						</tr>
						<tr>
							<td>登录地址</td>
							<td>
								<select name="loginUrl" class="validate[required] xlselect" style="width: 256px;">
									<option value="">--请选择--</option>
									<option value="/login" <c:if test="${config.loginUrl=='/login'}">selected="selected"</c:if> >通用登录页面</option>
									<option value="/inx/szwsj" <c:if test="${config.loginUrl=='/inx/szwsj'}">selected="selected"</c:if> >苏州卫生局网站</option>
									<option value="/inx/wxwsj" <c:if test="${config.loginUrl=='/inx/wxwsj'}">selected="selected"</c:if> >无锡卫生局网站</option>
									<option value="inx/yhwsj" <c:if test="${config.loginUrl=='inx/yhwsj'}">selected="selected"</c:if> >余杭卫生局网站</option>
									<option value="/inx/jsszyyxhzx" <c:if test="${config.loginUrl=='/inx/jsszyyxhzx'}">selected="selected"</c:if> >江苏省中医院消化中心</option>
									<option value="/inx/hbres" <c:if test="${config.loginUrl=='/inx/hbres'}">selected="selected"</c:if>/>湖北住院医师</option>
									<option value="/inx/njmures" <c:if test="${config.loginUrl=='/inx/njmures'}">selected="selected"</c:if> >南京医科大学实习生管理平台</option>
									<option value="/inx/njmuedu" <c:if test="${config.loginUrl=='/inx/njmuedu/'}">selected="selected"</c:if> >${applicationScope.sysCfgMap['study_platform'] eq 'szslyy'?'苏州市住院医师公共科目学习平台':'南京医科大学研究生在线课程学习平台'}</option>
									<option value="/inx/shetedu" <c:if test="${config.loginUrl=='/inx/shetedu'}">selected="selected"</c:if> >上海儿童医学中心学习培训管理平台</option>
									<option value="/inx/sczyres" <c:if test="${config.loginUrl=='/inx/sczyres'}">selected="selected"</c:if> >四川省中医住院医师规范化培训招录平台</option>
									<option value="/inx/nzyres" <c:if test="${config.loginUrl=='/inx/nzyres'}">selected="selected"</c:if> >南中医专硕</option>
									<option value="/inx/nfykdx" <c:if test="${config.loginUrl=='/inx/nfykdx'}">selected="selected"</c:if> >南方医科大学</option>
									<option value="/inx/jszy" <c:if test="${config.loginUrl=='/inx/jszy'}">selected="selected"</c:if> >江苏中医药局</option>
									<option value="/inx/jsres" <c:if test="${config.loginUrl=='/inx/jsres'}">selected="selected"</c:if> >江苏省卫计委</option>
									<option value="/inx/jssrm" <c:if test="${config.loginUrl=='/inx/jssrm'}">selected="selected"</c:if> >江苏科研</option>
									<option value="/inx/cdedu" <c:if test="${config.loginUrl=='/inx/cdedu'}">selected="selected"</c:if> >成都中医药大学附属医院继续教育管理平台</option>
									<option value="/inx/cdedudoor" <c:if test="${config.loginUrl=='/inx/cdedudoor'}">selected="selected"</c:if> >成都中医药大学附属医院继续教育管理平台门户</option>
									<option value="/inx/osce" <c:if test="${config.loginUrl=='/inx/osce'}">selected="selected"</c:if> >临床技能考核管理系统</option>
									<option value="/inx/jszysrm" <c:if test="${config.loginUrl=='/inx/jszysrm'}">selected="selected"</c:if> >江苏中医药局科研</option>
									<option value="/inx/xnyd" <c:if test="${config.loginUrl=='/inx/xnyd'}">selected="selected"</c:if> >西南医大附中院</option>
									<option value="/inx/gzykdx" <c:if test="${config.loginUrl=='/inx/gzykdx'}">selected="selected"</c:if> >广州医科大学研究生</option>
									<option value="/inx/lcjn" <c:if test="${config.loginUrl=='/inx/lcjn'}">selected="selected"</c:if> >临床训练中心管理系统</option>
									<option value="/inx/gzzl" <c:if test="${config.loginUrl=='/inx/gzzl'}">selected="selected"</c:if> >广州医科大学研究生招录系统</option>
									<option value="/inx/xzky" <c:if test="${config.loginUrl=='/inx/xzky'}">selected="selected"</c:if> >徐州科研</option>
									<option value="/inx/xzjx" <c:if test="${config.loginUrl=='/inx/xzjx'}">selected="selected"</c:if> >徐州进修</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>退出地址</td>
							<td>
								<input name="logoutUrl" class="validate[required,maxSize[200]] xltext" value="${config.logoutUrl}" placeholder="例如：/logout"  type="text" style="width: 250px;">
							</td>
						</tr>
						<tr>
							<td>登录图片</td>
							<td>
								<input name="sysLoginImg" class="validate[required,maxSize[200]] xltext" value="${config.sysLoginImg}"  placeholder="例如：/jsp/jszy/images/login.png" type="text" style="width: 250px;">
							</td>
						</tr>
						<tr>
							<td>系统头图片</td>
							<td>
								<input name="sysHeadImg" class="validate[required,maxSize[100]] xltext" value="${config.sysHeadImg}" placeholder="例如：/jsp/jszy/images/head.png" type="text" type="text" style="width: 250px;">
							</td>
						</tr>
						<tr>
							<td>系统名称</td>
							<td>
								<input name="sysTitleName" class="validate[required,maxSize[50]] xltext" value="${config.sysTitleName}" placeholder="例如：江苏省卫计委科研管理平台" type="text" type="text" style="width: 250px;">
							</td>
						</tr>
					</table>
					</form>
					<div class="button">
						<input class="search" type="button" value="保&#12288;存"onclick="save();" />
						<input class="search" type="button" value="关&#12288;闭"onclick="jboxClose();" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>