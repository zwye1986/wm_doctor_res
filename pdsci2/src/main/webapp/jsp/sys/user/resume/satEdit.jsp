<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<script type="text/javascript">
	function save(){
		var form = $("#editForm");
		if(false==form.validationEngine("validate")){
			return ;
		}
		var requestData = form.serialize();
		var url = "<s:url value='/pub/resume/saveSat?userFlow=${param.userFlow}'/>";
		jboxPost(url,requestData,function(resp){
			if(resp == '${GlobalConstant.SAVE_SUCCESSED}'){
				window.parent.frames['mainIframe'].window.loadSat();
				doClose();
			}
		},null,true);
	}
	
	function doClose(){
		jboxClose();
	}
</script>

</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
			<form id="editForm" method="post" style="position: relative;">
				<input type="hidden" name="satFlow" value="${srmAchSat.satFlow}" />
				<table width="100%" class="basic" >
					<tr>
						<th style="width: 35%"><font color="red" >*</font>&nbsp;科技名称：</th>
						<td style="width: 65%">
							<input type="text"  class="validate[required] xltext"  name="satName" value="${srmAchSat.satName}" style="margin-right: 0px"/>
						</td>
					</tr>
					<tr>
						<th>获奖级别：</th>
						<td>
							<select name="prizedGradeId" class="xlselect" >
								<option value="">请选择</option>
								<c:forEach var="dict" items="${dictTypeEnumPrizedGradeList}">
									<option value="${dict.dictId}"  <c:if test="${dict.dictName eq srmAchSat.prizedGradeName}">selected="selected"</c:if>  >${dict.dictName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>获奖等级：</th>
						<td>
							<select name="prizedLevelId" class="xlselect" >
								<option value="">请选择</option>
								<c:forEach var="dict" items="${dictTypeEnumPrizedLevelList}">
									<option value="${dict.dictId}"  <c:if test="${dict.dictName eq srmAchSat.prizedLevelName}">selected="selected"</c:if>  >${dict.dictName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>获奖日期：</th>
						<td>
							<input type="text" onClick="WdatePicker({dateFmt:'yyyy-MM'})"  name="prizedDate" value="${srmAchSat.prizedDate}" class="ctime" style="width: 160px;" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th>成果形式：</th>
						<td>
							<select name="achTypeId" class="xlselect" >
								<option value="">请选择</option>
								<c:forEach var="dict" items="${dictTypeEnumAchTypeList}">
									<option value="${dict.dictId}"  <c:if test="${dict.dictName eq srmAchSat.achTypeName}">selected="selected"</c:if>  >${dict.dictName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
			</form>
			<p style="text-align: center;">
	       		<input type="button" onclick="save()"  class="search" value="保&#12288;存"/>
	       		<input type="button" onclick="doClose()" class="search" value="关&#12288;闭"/>
	       </p>
			</div>
		</div>
	</div>
</body>
</html>

	

		
