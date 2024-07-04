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
					$("[name='skillScore']").val(100-sum);
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

		//导出评分表
		function printForm(recFlow){
			jboxTip("打印中,请稍等...");
			var url = '<s:url value="/res/fengxianPj/printForm?recFlow="/>'+recFlow;
			window.location.href = url;
		}

	</script>
<body>
<div class="mainright">
	<div class="content">
		<form id="dopsForm" style="position: relative">
			<div id="printDiv">
				<div id="title" style="width:100%;text-align: center;font-size: 15px;">
					上海市奉贤区中心医院住院医师规范化培训妇产科学专业<br/>体格检查考核评分表一
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
						<td style="width: 25%">姓名：</td>
						<td style="width: 25%">
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="studentName" value="${doctor.doctorName}"/>
							</c:if>
							<c:if test="${!verification}">
								${formDataMap['studentName']}
								<input type="hidden" class="inputText"  name="studentName" value="${formDataMap['studentName']}"/>
							</c:if>
						</td>
						<td style="width: 25%">所属培训基地：</td>
						<td style="width: 25%">
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="orgName" value="${doctor.orgName}"/>
							</c:if>
							<c:if test="${!verification}">
								${formDataMap['orgName']}
								<input type="hidden" class="inputText"  name="orgName" value="${formDataMap['orgName']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td style="width: 25%">工号：</td>
						<td style="width: 25%">
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="studentSid" value=""/>
							</c:if>
							<c:if test="${!verification}">
								${formDataMap['studentSid']}
								<input type="hidden" class="inputText"  name="studentSid" value="${formDataMap['studentSid']}"/>
							</c:if>
						</td>
						<td style="width: 25%">考核科室：</td>
						<td style="width: 25%">
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
						<td colspan="2">考评内容（一）</td>
						<td>分值</td>
						<td>扣分</td>
					</tr>
					<tr>
						<td colspan="2">体检时充分体恤病人，显示人文关怀 </td>
						<td>10</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; "  name="part00" type="text" value="${formDataMap['part00']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="2">体检顺序合理、规范、有逻辑，整体上从容不迫、井然有序 </td>
						<td>15</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[15],min[0]] scoreCount" style="width:90%; "  name="part01" type="text" value="${formDataMap['part01']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="2">体检内容全面、系统 </td>
						<td>15</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[15],min[0]] scoreCount" style="width:90%; "  name="part02" type="text" value="${formDataMap['part02']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="2">专科检查有的放矢，重点体格检查无遗漏，以深入了解病变的器官系统 </td>
						<td>15</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[15],min[0]] scoreCount" style="width:90%; "  name="part03" type="text" value="${formDataMap['part03']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="2">手法正确、轻巧、熟练 </td>
						<td>10</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; "  name="part04" type="text" value="${formDataMap['part04']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="2">边想边查，边问边查，核实补充，确保获得完整正确的检查结果 </td>
						<td>10</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; "  name="part05" type="text" value="${formDataMap['part05']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="4">考评内容（二）：四步触诊</td>
					</tr>
					<tr>
						<td colspan="2">第一步</td>
						<td>5</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; "  name="part06" type="text" value="${formDataMap['part06']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="2">第二步</td>
						<td>5</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; "  name="part07" type="text" value="${formDataMap['part07']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="2">第三步</td>
						<td>5</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; "  name="part08" type="text" value="${formDataMap['part08']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="2">第四步</td>
						<td>5</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; "  name="part09" type="text" value="${formDataMap['part09']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="2">表述 </td>
						<td>5</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; "  name="part10" type="text" value="${formDataMap['part10']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: left">总分</td>
						<td>100</td>
						<td style="text-align: center;">
							<input style="width: 90%; " class="inputText ctrlInput validate[required,custom[number]]" name="skillScore" type="text" value="${formDataMap['skillScore']}" readonly="readonly" style="width: 150px;"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: left">
							考官姓名：
							<c:if test="${verification}">
								<input type="text" style="text-align: left;" class="inputText validate[required]" name="teacherName" value=""/>
							</c:if>
							<c:if test="${!verification}">
								${formDataMap['teacherName']}
								<input type="hidden" class="inputText"  name="teacherName" value="${formDataMap['teacherName']}"/>
							</c:if>
						</td>
						<td colspan="2" style="text-align: center;">
							日期：
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