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
					$("[name='skillScore']").val(sum);
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
					上海市奉贤区中心医院住院医师规范化培训妇产科学专业<br/>手术技能操作评分表
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
						<td colspan="2" width="25%">姓名：</td>
						<td colspan="2" width="25%">
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="studentName" value="${doctor.doctorName}"/>
							</c:if>
							<c:if test="${!verification}">
								${formDataMap['studentName']}
								<input type="hidden" class="inputText"  name="studentName" value="${formDataMap['studentName']}"/>
							</c:if>
						</td>
						<td colspan="2" width="25%">考核日期：</td>
						<td colspan="2" width="25%">
							<c:if test="${verification}">
								<input type="text" class="toggleView3 inputText validate[required] ${GlobalConstant.RES_ROLE_SCOPE_DOCTOR} ctrlInput" name="date" value="${empty formDataMap['date']?pdfn:getCurrDate():formDataMap['date']}" style="width: 100px;" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
								<label style="display: none;" class="toggleView3">${formDataMap['date']}</label>
							</c:if>
							<c:if test="${!verification}">
								<label>${formDataMap['date']}</label>
								<input type="hidden" name="date" value="${formDataMap['date']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="2">工号：</td>
						<td colspan="2">
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="studentSid" value=""/>
							</c:if>
							<c:if test="${!verification}">
								<label>${formDataMap['studentSid']}</label>
								<input type="hidden" name="studentSid" value="${formDataMap['studentSid']}"/>
							</c:if>
						</td>
						<td colspan="2">考核专业：</td>
						<td colspan="2">
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="speName" value=""/>
							</c:if>
							<c:if test="${!verification}">
								<label>${formDataMap['speName']}</label>
								<input type="hidden" name="speName" value="${formDataMap['speName']}"/>
							</c:if>
						</td>
						<%--<td colspan="2">得分：</td>--%>
						<%--<td colspan="2">--%>
							<%--<c:if test="${verification}">--%>
								<%--<input type="text" style="text-align: left;" class="inputText validate[required]" name="skillScore" value=""/>--%>
							<%--</c:if>--%>
							<%--<c:if test="${!verification}">--%>
								<%--${formDataMap['skillScore']}--%>
								<%--<input type="hidden" class="inputText"  name="skillScore" value="${formDataMap['skillScore']}"/>--%>
							<%--</c:if>--%>
						<%--</td>--%>
					</tr>
					<tr>
						<td colspan="2">手术名称</td>
						<td colspan="6">负压吸宫术</td>
					</tr>
					<tr>
						<td colspan="6">考核项目</td>
						<td width="12.5%">满分</td>
						<td width="12.5%">成绩</td>
					</tr>
					<tr>
						<td rowspan="4">术前准备</td>
						<td colspan="5">询问病史</td>
						<td>15</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[15],min[0]] scoreCount" style="width:90%; "  name="part00" type="text" value="${formDataMap['part00']}" style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">专科检查</td>
						<td>15</td>
						<td><input name="part01" type="text" value="${formDataMap['part01']}" class="inputText ctrlInput validate[required,custom[number],max[15],min[0]] scoreCount" style="width:90%; "  style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">手术、操作指征及麻醉方式的选择</td>
						<td>5</td>
						<td><input name="part02" type="text" value="${formDataMap['part02']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">术前医嘱</td>
						<td>5</td>
						<td><input name="part03" type="text" value="${formDataMap['part03']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td rowspan="7">术中过程</td>
						<td colspan="5">无菌操作严密</td>
						<td>6</td>
						<td><input name="part04" type="text" value="${formDataMap['part04']}" class="inputText ctrlInput validate[required,custom[number],max[6],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">基本操作规范</td>
						<td>10</td>
						<td><input name="part05" type="text" value="${formDataMap['part05']}" class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">手术技巧娴熟</td>
						<td>6</td>
						<td><input name="part06" type="text" value="${formDataMap['part06']}" class="inputText ctrlInput validate[required,custom[number],max[6],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">解剖层面清晰</td>
						<td>6</td>
						<td><input name="part07" type="text" value="${formDataMap['part07']}" class="inputText ctrlInput validate[required,custom[number],max[6],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">术野显露清楚</td>
						<td>6</td>
						<td><input name="part08" type="text" value="${formDataMap['part08']}" class="inputText ctrlInput validate[required,custom[number],max[6],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">探查脏器有序</td>
						<td>6</td>
						<td><input name="part09" type="text" value="${formDataMap['part09']}" class="inputText ctrlInput validate[required,custom[number],max[6],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">应变能力敏捷</td>
						<td>5</td>
						<td><input name="part10" type="text" value="${formDataMap['part10']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td rowspan="3">术后处理</td>
						<td colspan="5">术后医嘱</td>
						<td>5</td>
						<td><input name="part11" type="text" value="${formDataMap['part11']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">手术、操作记录</td>
						<td>5</td>
						<td><input name="part12" type="text" value="${formDataMap['part12']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="5">常见并发症及处理</td>
						<td>5</td>
						<td><input name="part13" type="text" value="${formDataMap['part13']}" class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; " style="width: 80px;"/></td>
					</tr>
					<tr>
						<td colspan="6">总分</td>
						<td>100</td>
						<td><input name="skillScore" type="text" value="${formDataMap['skillScore']}" class="inputText ctrlInput validate[required,custom[number],max[100],min[0]]" style="width:90%; " style="width: 80px;" readonly="readonly"/></td>
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