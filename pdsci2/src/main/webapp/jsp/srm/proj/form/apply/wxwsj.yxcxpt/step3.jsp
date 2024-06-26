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
	<jsp:param name="jquery_ui_combobox" value="true"/>
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
	function nextOpt(step){
		if(false==$("#projForm").validationEngine("validate")){
			return false;
		}
		var form = $('#projForm');
		var action = form.attr('action');
		action+="?nextPageName="+step;
		form.attr("action" , action);
		form.submit();
	}

</script>
<style type="text/css">
 .bs_tb tbody td input{text-align: left;margin-left: 10px;}
</style>
</head>
<body>
	<div id="main">
		<div class="mainright">
			<div class="content">
			   <div style="margin-top: 10px;">
					<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;" id="projForm">
						<input type="hidden" id="pageName" name="pageName" value="step3" />
						<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
						<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
						<input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

						<font style="font-size: 14px; font-weight:bold;color: #333; ">三、医学创新平台（实验室）带头人概况</font>
						<table class="basic" style="width: 100%; margin-top: 10px;margin-bottom:10px;">
							<tr>
								<th colspan="4" style="text-align: left;padding-left: 20px;">基本情况</th>
							</tr>
							<tr>
								<td style="text-align: right;" width="120px;">姓名：</td>
								<td><input type="text" name="instructorName" value="${resultMap.instructorName}" class="inputText validate[required]" style="width: 80%;"/>
									<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span></td>
								<td style="text-align: right;" width="100px;">出生年月：</td>
								<td><input class="inputText validate[required]" type="text" name="instructorBirthday" value="${resultMap.instructorBirthday}" onClick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly" style="width: 120px;"/>
									<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span></td>
							</tr>
							<tr>
								<td style="text-align: right;">技术职称： </td>
								<td>
									 <select name="instructorJobTitle" class="inputText validate[required]" style="width: 80%;">
									   <option value="">请选择</option>
									   <c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
									   <option value="${dict.dictName }" <c:if test="${resultMap.instructorJobTitle eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
									   </c:forEach>
									</select>
									<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
								</td>
								<td style="text-align: right;">研究生导师：</td>
								<td>
									<input type="radio" name="instructorSupervisor" id="instructorSupervisor_bd" value="博导" class="inputText" <c:if test="${resultMap.instructorSupervisor eq '博导'}">checked="checked"</c:if>/><label for="instructorSupervisor_bd">&nbsp;博导</label>&nbsp;
									<input type="radio" name="instructorSupervisor" id="instructorSupervisor_sd" value="硕导" class="inputText" <c:if test="${resultMap.instructorSupervisor eq '硕导'}">checked="checked"</c:if>/><label for="instructorSupervisor_sd">&nbsp;硕导</label>&nbsp;
									<input type="radio" name="instructorSupervisor" id="instructorSupervisor_no" value="否" class="inputText" <c:if test="${resultMap.instructorSupervisor eq '否'}">checked="checked"</c:if>/><label for="instructorSupervisor_no">&nbsp;否</label>
								</td>
							</tr>
							<tr>
								<td style="text-align: right;">最高学会任职：</td>
								<td colspan="3"><input type="text" name="instructorInstituteOfficeHolding" value="${resultMap.instructorInstituteOfficeHolding}" class="inputText" style="width: 80%"/></td>
							</tr>
						</table>
						<font style="font-size: 14px; font-weight:bold;color: #333;">个人简介（业务能力、学术地位等）<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span></font>
						<table class="bs_tb" style="width: 100%; margin-top: 10px;" >
							<tr>
								<td style="text-align:left;">
									<textarea placeholder=""  class="validate[required,maxSize[4000]] xltxtarea" style="height: 350px;padding:5px;" name="instructorRemark">${resultMap.instructorRemark}</textarea>
								</td>
							</tr>
						</table>

					</form>
					<div class="button" style="width: 100%; <c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
					   <%--<a href="javascript:void(0)" target="_self" class="search" onclick="nextOpt('step2')">上一步</a>--%>
					   <%--<a href="javascript:void(0)" target="_self" class="search" onclick="nextOpt('step4')">下一步</a>--%>
						<input id="prev" type="button" onclick="nextOpt('step2')" class="search" value="上一步"/>
						<input id="nxt" type="button" onclick="nextOpt('step4')" class="search" value="下一步"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>