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
		<jsp:param name="jquery_cxselect" value="true"/>
		<jsp:param name="jquery_scrollTo" value="false"/>
		<jsp:param name="jquery_jcallout" value="false"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fullcalendar" value="false"/>
		<jsp:param name="jquery_fngantt" value="false"/>
		<jsp:param name="jquery_fixedtableheader" value="false"/>
		<jsp:param name="jquery_placeholder" value="false"/>
		<jsp:param name="jquery_iealert" value="false"/>
	</jsp:include>
	<style type="text/css">

	</style>
	<script type="text/javascript" src="<s:url value='/js/jquery.PrintArea.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript">
		function single(box){
			var curr=box.checked;
			if(curr){
				var name=box.name;
				$(":checkbox[name='"+name+"']").attr("checked",false);
			}
			box.checked = curr;
		}
		//导出评分表
		function printForm(recFlow){
			jboxTip("打印中,请稍等...");
			var url = '<s:url value="/res/fengxianPj/printForm?recFlow="/>'+recFlow;
			window.location.href = url;
		}
		function saveForm(){
			var zong=0;
			$(".num :checkbox:checked").each(function(i,n){
				var num = parseInt($(n).val());
				zong+=num;
			});
			$("#ZongHe").val(zong);
			if($("#dopsForm").validationEngine("validate")){
				jboxConfirm("保存后将无法修改,确定吗?",function(){
					jboxPost("<s:url value='/res/fengxianPj/save360EvlForm'/>",$('#dopsForm').serialize(),function(resp){
						if(resp="${GlobalConstant.SAVE_SUCCESSED}"){
							window.parent.frames['jbox-message-iframe'].location.reload(true);
							jboxClose();
						}
					},null,true);
				},null);
			}
		}

		$(function(){
			$(".scoreCount").keyup(function(){
				var sum = 0;
				$(".scoreCount").each(function(){
					var val = this.value;
					if(val && !isNaN(val)){
						sum+=parseFloat(val);
					}
					$("[name='skillScore']").val(60-sum);
				});
			});
			if(${not empty rec.recFlow}){
				$("input").prop("readonly","readonly");
			}
		});

		function jboxPrint(id) {
			jboxTip("正在准备打印…")
			setTimeout(function(){
				$("#title").show();
				var newstr = $("#"+id).html();
				var oldstr = document.body.innerHTML;
				document.body.innerHTML = newstr;
				window.print();
				document.body.innerHTML = oldstr;
				$("#title").hide();
				jboxEndLoading();
				return false;
			},2000);
		}
		function recSubmit(rec){
			jboxConfirm("确认提交?",function(){
				jboxPost("<s:url value='/res/rec/opreResRec'/>",rec,function(resp){
					if(resp=="${GlobalConstant.DELETE_SUCCESSED}" || resp=="${GlobalConstant.OPRE_SUCCESSED}"){
						parentRefresh();
						jboxClose();
					}
				},null,true);
			},null);
		}
		function parentRefresh(){
			//window.parent.frames['mainIframe'].window.$(".recTypeTag.active").click();
			window.parent.document.mainIframe.location.reload();
		}


	</script>
<body>
<div class="mainright">
	<div class="content">
		<form id="dopsForm" style="position: relative">
			<div id="printDiv">
				<div id="title" style="width:100%;text-align: center;font-size: 15px;">
					上海市奉贤区中心医院住院医师规范化培训<br/>肾穿刺活检术操作技能考考核评分表
				</div>
				<input type="hidden" name="formFileName" value="${formFileName}"/>
				<input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
				<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
				<input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
				<input type="hidden" name="operUserFlow" value="${param.userFlow}"/>
				<input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
				<c:set var="verification" value="${empty rec.recFlow}"/>
				<table class="basic" width="100%" style="margin-top: 10px;">
					<input type="hidden" id="ZongHe" name="ZongHe" value="${formDataMap['ZongHe']}"/>
					<tr>
						<td colspan="2">考核住院医师：</td>
						<td colspan="2">
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="studentName" value="${doctor.doctorName}"/>
							</c:if>
							<c:if test="${!verification}">
								${formDataMap['studentName']}
								<input type="hidden" class="inputText"  name="studentName" value="${formDataMap['studentName']}"/>
							</c:if>
						</td>
						<td colspan="2">考核科室：</td>
						<td colspan="2">
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="deptName" value="${currRegProcess.deptName}"/>
							</c:if>
							<c:if test="${!verification}">
								${formDataMap['deptName']}
								<input type="hidden" class="inputText"  name="deptName" value="${formDataMap['deptName']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="2">日期：</td>
						<td colspan="2">
							<c:if test="${verification}">
								<input type="text" class="toggleView3 inputText validate[required] ${GlobalConstant.RES_ROLE_SCOPE_DOCTOR} ctrlInput" name="date" value="${empty formDataMap['date']?pdfn:getCurrDate():formDataMap['date']}" style="width: 100px;" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
								<label style="display: none;" class="toggleView3">${formDataMap['date']}</label>
							</c:if>
							<c:if test="${!verification}">
								<label>${formDataMap['date']}</label>
								<input type="hidden" name="date" value="${formDataMap['date']}"/>
							</c:if>
						</td>
						<td colspan="2">得分：</td>
						<td colspan="2">
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="skillScore" value=""/>
							</c:if>
							<c:if test="${!verification}">
								${formDataMap['skillScore']}
								<input type="hidden" class="inputText"  name="skillScore" value="${formDataMap['skillScore']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="8">1、肾穿刺活检术适应症（10 分）</td>
					</tr>
					<tr>
						<td colspan="8" style="padding-top:10px;padding-left:0;">
							<c:if test="${verification}">
								<textarea name="scchjssyz"  style="width: 85%;height: 220px;">${formDataMap['scchjssyz']}</textarea>
							</c:if>
							<c:if test="${!verification}">
								&#12288;${formDataMap['scchjssyz']}
								<input type="hidden" name="scchjssyz" value="${formDataMap['scchjssyz']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="8">扣分：<input class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; "  name="part00" type="text" value="${formDataMap['part00']}" style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="8">扣分理由：<input class="inputText ctrlInput validate[required]" style="width:90%; "  name="reason00" type="text" value="${formDataMap['reason00']}" style="width: 200px;"/></td>
					</tr>
					<tr>
						<td colspan="8">2、肾穿刺活检术禁忌症（50 分）</td>
					</tr>
					<tr>
						<td colspan="8" style="padding-top:10px;padding-left:0;">
							<c:if test="${verification}">
								<textarea name="scchjsjjz"  style="width: 85%;height: 220px;">${formDataMap['scchjsjjz']}</textarea>
							</c:if>
							<c:if test="${!verification}">
								&#12288;${formDataMap['scchjsjjz']}
								<input type="hidden" name="scchjsjjz" value="${formDataMap['scchjsjjz']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="8">扣分：<input class="inputText ctrlInput validate[required,custom[number],max[50],min[0]] scoreCount" style="width:90%; "  name="part01" type="text" value="${formDataMap['part01']}" style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="8">扣分理由：<input class="inputText ctrlInput validate[required]" style="width:90%; "  name="reason01" type="text" value="${formDataMap['reason01']}" style="width: 200px;"/></td>
					</tr>
				</table>
			</div>
		</form>
		<div style="padding-top: 5px;text-align: center;">
			<c:if test="${verification}">
				<input class="search" type="button" value="保&#12288;存"  onclick="saveForm();"/>
			</c:if>
			<c:if test="${!verification}">
				<input class="search" type="button" value="打&#12288;印"  onclick="printForm('${rec.recFlow}');"/>
			</c:if>
			<input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();"/>
		</div>
	</div>
</div>
</body>
</html>