<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ztree" value="true"/>
</jsp:include>

<c:if test="${param.view != GlobalConstant.FLAG_Y}">
	<script type="text/javascript">
		//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
		function banBackSpace(e) {
			var ev = e || window.event;//获取event对象
			var obj = ev.target || ev.srcElement;//获取事件源
			var t = obj.type || obj.getAttribute('type');//获取事件源类型
			//获取作为判断条件的事件类型
			var vReadOnly = obj.getAttribute('readonly');
			//处理null值情况
			vReadOnly = (vReadOnly == "") ? false : vReadOnly;
			//当敲Backspace键时，事件源类型为密码或单行、多行文本的，
			//并且readonly属性为true或enabled属性为false的，则退格键失效
			var flag1 = (ev.keyCode == 8 && (t == "password" || t == "text" || t == "textarea")
			&& vReadOnly == "readonly") ? true : false;
			//当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
			var flag2 = (ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")
					? true : false;

			//判断
			if (flag2) {
				return false;
			}
			if (flag1) {
				return false;
			}
		}

		window.onload = function () {
//禁止后退键 作用于Firefox、Opera
			document.onkeypress = banBackSpace;
//禁止后退键  作用于IE、Chrome
			document.onkeydown = banBackSpace;
		}
		function nextOpt(step) {
			if (false == $("#projForm").validationEngine("validate")) {
				return false;
			}
			var form = $('#projForm');
			var action = form.attr('action');
			action += "?nextPageName=" + step;
			form.attr("action", action);
			form.submit();
		}

		function doBack() {
			<c:if test="${sessionScope.projListScope ne GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}">
			window.location.href = "<s:url value='/srm/proj/mine/process?projFlow='/>" + $("#projFlow").val();
			</c:if>
			<c:if test="${sessionScope.projListScope eq GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}">
			window.location.href = "<s:url value='/srm/proj/search/recList?projFlow='/>" + $("#projFlow").val();
			</c:if>
		}

	</script>
</c:if>



<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;"
	  id="projForm">
	<input type="hidden" id="pageName" name="pageName" value="step1"/>
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
	<input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

	<table class="bs_tb" style="width: 100%">
		<tr>
			<th colspan="4" class="theader">江苏省中医院科技项目申请书</th>
		</tr>
		<tr>
			<th >课题名称：</th>
			<td style="text-align: left;padding-left: 10px;" colspan="3">
				<input type="text" class="validate[required] inputText" name="projName"
					   value="<c:if test='${empty resultMap.projName and param.view!=GlobalConstant.FLAG_Y}'>${proj.projName}</c:if><c:if test='${!empty resultMap.projName}'>${resultMap.projName}</c:if>"
					   style="width: 46%"/>
				<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
			</td>
		</tr>
		<tr>
			<th>&#12288;申请人：</th>
			<td style="text-align: left;padding-left: 10px;" colspan="3">
				<input type="text" class="validate[required] inputText" name='applyUserName'
					   value="<c:if test='${empty resultMap.applyUserName and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyUserName}</c:if><c:if test='${! empty resultMap.applyUserName}'>${resultMap.applyUserName}</c:if>"
					   style="width: 46%"/>
				<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
			</td>
		</tr>
		<tr>
			<th >所在科室：</th>
			<td style="text-align: left;padding-left: 10px;" colspan="3">
				<input type="text" class="validate[required] inputText" name="userDept"
					   value="<c:if test='${empty resultMap.userDept and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyDeptName}</c:if><c:if test='${!empty resultMap.userDept}'>${resultMap.userDept}</c:if>"
					   style="width: 46%"/>
				<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
			</td>
		</tr>

		<tr>
			<th >电&#12288;&#12288;话：</th>
			<td style="text-align: left;padding-left: 10px;" colspan="3">
				<input type="text" class="inputText validate[required,custom[phone]]" name="userPhone"
					   value="<c:if test='${empty resultMap.userPhone and param.view!=GlobalConstant.FLAG_Y}'>${user.userPhone}</c:if><c:if test='${! empty resultMap.userPhone}'>${resultMap.userPhone}</c:if>"
					   style="width: 46%"/>
				<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
			</td>
		</tr>
		<tr>
			<th >电子信箱：</th>
			<td style="text-align: left;padding-left: 10px;" colspan="3">
				<input type="text" class="inputText validate[required,custom[email]]" name="userEmail"
					   value="${resultMap.userEmail}<c:if test="${empty resultMap.userEmail}">${user.userEmail}</c:if>"
					   style="width: 46%"/>
				<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
			</td>
		</tr>
		<tr>
			<th >申请日期：</th>
			<td style="text-align: left;padding-left: 10px;" colspan="3">
				<input class="inputText validate[required]" type="text" name="applyDate"
					   value="${resultMap.applyDate}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
					   readonly="readonly" style="width: 46%"/>
				<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
			</td>
		</tr>
		<tr>
			<th>受理编号：</th>
			<td style="text-align: left;padding-left: 10px;">
				<input type="text" class="inputText" placeholder="" name="acceptNum"
					   value="${proj.acceptNumber}${resultMap.acceptNum}" style="width: 46%"/>
			</td>
		</tr>
		<tr>
			<th >指南代码：</th>
			<td style="text-align: left;padding-left: 10px;">
				<input type="text" class="inputText" name="projGuideCode"
					   value="${resultMap.projGuideCode}" style="width: 46%"/>
			</td>
		</tr>
		<tr>
			<th>项目类型：</th>
			<td style="text-align: left;padding-left: 10px;" colspan="3">
				<%--<select name="projType" class="inputText validate[required]" style="width: 46%;">
					<option value="">请选择</option>
					<option value="院内课题"
							<c:if test="${resultMap.projType eq '院内课题'}"> selected="selected"</c:if>
							<c:if test="${(empty resultMap.projType) and (proj.projTypeId eq 'jsszyy.kyxm')}"> selected="selected"</c:if>>
						院内课题</option>
					<option value="院外课题"
							<c:if test="${resultMap.projType eq '院外课题'}"> selected="selected"</c:if>
							<c:if test="${(empty resultMap.projType) and (proj.projTypeId eq 'jsszyy.ywxm')}"> selected="selected"</c:if>>
						院外课题
					</option>
				</select>
				<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>--%>
				<input type="text" class="inputText" name="projType" readonly="readonly"
					   value="${resultMap.projType}<c:if test="${empty resultMap.projType and param.view!=GlobalConstant.FLAG_Y}">${proj.projTypeName}</c:if>" style="width: 46%"/>
			</td>
		</tr>
	</table>
</form>
<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
	<div align="center" style="margin-top: 10px">
		<input id="prev" type="button" onclick="doBack()" class="search" value="返&#12288;回"/>
		<input id="nxt" type="button" onclick="nextOpt('step2')" class="search" value="保&#12288;存"/>
	</div>
</c:if>

		