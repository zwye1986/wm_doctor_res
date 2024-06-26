<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:if test="${'open' eq param.type}">
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
	</jsp:include>
</c:if>
<html>
<head>
<script type="text/javascript">
if (("${param.roleFlag}" != "${GlobalConstant.RES_ROLE_SCOPE_ADMIN}")) {
	$(document).ready(function(){
		hideButton();
	});
}
function hideButton() {
	$("#ethicsForm").find('input,textarea').attr("readonly","readonly");
	$("#ethicsForm").find("select,:checkbox,:radio").attr("disabled", "disabled");
	$("#ethicsForm").find("#saveBtn,.opBtn").hide();
}
function saveForm(){
	if($("#ethicsForm").validationEngine("validate")){
		jboxPost("<s:url value='/res/rec/saveRegistryForm'/>",$('#ethicsForm').serialize(),function(){
			window.parent.frames['mainIframe'].window.$("#tags").find(".selectTag a").click();
			jboxClose();
		},null,true);
	}
}

function submitForm(){
	jboxConfirm("提交后不可再编辑，确认提交?",function(){
		saveForm();
	});
}
function printForm() {
    var url = "<s:url value='/res/rec/printInfo?recFlow=${rec.recFlow}'/>";
    window.location.href = url;
}
</script>
</head>
<body>
<form id="ethicsForm" method="post">
	<input type="hidden" name="doctorFlow" value="${param.operUserFlow}"/>
	<input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
	<input type="hidden" name="formFileName" value="${formFileName}"/>
	<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
	<input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
	<input type="hidden" name="operUserFlow" value="${param.operUserFlow}">
	<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_ADMIN}">
		<input type="hidden" name="adminAuditStatusId" value="${recStatusEnumAdminAuditY.id}"/>
	</c:if>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<table class="basic" width="100%">
					<tr>
						<th colspan="3" style="text-align: left;">&#12288;实习课程成绩单</th>
					</tr>
					<tr>
						<td colspan="2">
							课程
						</td>
						<td>
							分数（100分制）
						</td>
					</tr>
					<tr>
						<td colspan="2">
							医德医风
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="ydyf" value="<c:out value="${formDataMap['ydyf']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							医学方案
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="yxwa" value="<c:out value="${formDataMap['yxwa']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>
					<tr>
						<td rowspan="2">
							内科诊疗技能
						</td>
						<td>
							理论
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="nkzljnll" value="<c:out value="${formDataMap['nkzljnll']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>
					<tr>
						<td>
							技能
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="nkzljnjn" value="<c:out value="${formDataMap['nkzljnjn']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>
					<tr>
						<td rowspan="2">
							外科诊疗技能
						</td>
						<td>
							理论
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="wkzljnll" value="<c:out value="${formDataMap['wkzljnll']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>
					<tr>
						<td>
							技能
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="wkzljnjn" value="<c:out value="${formDataMap['wkzljnjn']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>
					<tr>
						<td rowspan="2">
							妇产科诊疗技能
						</td>
						<td>
							理论
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="fckzljnll" value="<c:out value="${formDataMap['fckzljnll']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>
					<tr>
						<td>
							技能
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="fckzljnjn" value="<c:out value="${formDataMap['fckzljnjn']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>
					<tr>
						<td rowspan="2">
							儿科诊疗技能
						</td>
						<td>
							理论
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="ekzljnll" value="<c:out value="${formDataMap['ekzljnll']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>
					<tr>
						<td>
							技能
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="ekzljnjn" value="<c:out value="${formDataMap['ekzljnjn']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>
					<tr>
						<td rowspan="2">
							传染科诊疗
						</td>
						<td>
							理论
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="crkzlll" value="<c:out value="${formDataMap['crkzlll']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>
					<tr>
						<td>
							技能
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="crkzljn" value="<c:out value="${formDataMap['crkzljn']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>
					<tr>
						<td rowspan="2">
							眼科诊疗
						</td>
						<td>
							理论
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="ykzlll" value="<c:out value="${formDataMap['ykzlll']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>
					<tr>
						<td>
							技能
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="ykzljn" value="<c:out value="${formDataMap['ykzljn']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>
					<tr>
						<td rowspan="2">
							耳鼻喉科诊疗
						</td>
						<td>
							理论
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="ebhkzlll" value="<c:out value="${formDataMap['ebhkzlll']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>
					<tr>
						<td>
							技能
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="ebhkzljn" value="<c:out value="${formDataMap['ebhkzljn']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>
					<tr>
						<td rowspan="2">
							皮肤科诊疗
						</td>
						<td>
							理论
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="pfkzlll" value="<c:out value="${formDataMap['pfkzlll']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>
					<tr>
						<td>
							技能
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="pfkzljn" value="<c:out value="${formDataMap['pfkzljn']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>
					<tr>
						<td rowspan="2">
							心电图解读
						</td>
						<td>
							理论
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="ndtjdll" value="<c:out value="${formDataMap['ndtjdll']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>
					<tr>
						<td>
							技能
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="ndtjdjn" value="<c:out value="${formDataMap['ndtjdjn']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							X光片解读
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="xgpjd" value="<c:out value="${formDataMap['xgpjd']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							护理操作
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="hlcz" value="<c:out value="${formDataMap['hlcz']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							社区医疗卫生
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="sqylws" value="<c:out value="${formDataMap['sqylws']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							选修一
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="xxy" value="<c:out value="${formDataMap['xxy']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							选修二
						</td>
						<td>
							<input type="text" class="inputText validate[required,custom[integer],max[100]]" name="xxe" value="<c:out value="${formDataMap['xxe']}"/>"
								   style="text-align:center; width:100px; border-width: 0px; border-bottom-width: 1px;"/>
						</td>
					</tr>

				</table>
			</div>
			<div class="button" >
					<input id="saveBtn" class="search" type="button" value="保&#12288;存" onclick="saveForm();" />
                <c:if test="${not empty rec.recFlow}" >
                    &#12288;&#12288;
                    <input class="search" type="button" value="导&#12288;出" onclick="printForm();"/>
                </c:if>
			</div>
		</div>
	</div>
	</form>
</body>
</html>