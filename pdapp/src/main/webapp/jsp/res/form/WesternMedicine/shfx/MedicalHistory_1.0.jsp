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


	</script>
<body>
<div class="mainright">
	<div class="content">
		<form id="dopsForm" style="position: relative">
			<div id="printDiv">
				<div id="title" style="width:100%;text-align: center;font-size: 15px;">
					上海市奉贤区中心医院住院医师规范化培训<br/>病史采集评分表
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
						<td style="width: 25%">住院医师姓名：</td>
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
						<td colspan="2">评分项目</td>
						<td>标准分</td>
						<td>扣分</td>
					</tr>
					<tr>
						<td rowspan="9">一、问诊内容（75 分）</td>
						<td>（一）患者基本资料</td>
						<td>5</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; "  name="part00" type="text" value="${formDataMap['part00']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td>（二）归纳主诉 </td>
						<td>5</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; "  name="part01" type="text" value="${formDataMap['part01']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td colspan="3">（三）现病史</td>
					</tr>
					<tr>
						<td>1、根据主诉及相关鉴别询问；①围绕主诉内容展开询问；②发病诱因及伴随症状；③饮食、睡眠、二便、体重变化</td>
						<td>30</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[30],min[0]] scoreCount" style="width:90%; "  name="part02" type="text" value="${formDataMap['part02']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td>2、诊疗经过：①就诊及检查情况；②治疗效果</td>
						<td>10</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; "  name="part03" type="text" value="${formDataMap['part03']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td>（四）既往史（曾患病、传染病史、手术外伤史、过敏史、预防接种史）</td>
						<td>10</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[10],min[0]] scoreCount" style="width:90%; "  name="part04" type="text" value="${formDataMap['part04']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td>（五）个人史 </td>
						<td>5</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; "  name="part05" type="text" value="${formDataMap['part05']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td>（六）婚育史</td>
						<td>5</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; "  name="part06" type="text" value="${formDataMap['part06']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td>（七）家族史</td>
						<td>5</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; "  name="part07" type="text" value="${formDataMap['part07']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td rowspan="5">二、问诊技巧（25 分）</td>
						<td>（一）条理性、能否抓住重点</td>
						<td>5</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; "  name="part08" type="text" value="${formDataMap['part08']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td>（二）能否围绕病情询问</td>
						<td>5</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; "  name="part09" type="text" value="${formDataMap['part09']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td>（三）问诊语言是否恰当</td>
						<td>5</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; "  name="part10" type="text" value="${formDataMap['part10']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td>（四）问诊态度认真，沟通时有礼貌</td>
						<td>5</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; "  name="part11" type="text" value="${formDataMap['part11']}" style="width: 150px;"/></td>
					</tr>
					<tr>
						<td>（五）爱伤观念贯穿始终</td>
						<td>5</td>
						<td><input class="inputText ctrlInput validate[required,custom[number],max[5],min[0]] scoreCount" style="width:90%; "  name="part12" type="text" value="${formDataMap['part12']}" style="width: 150px;"/></td>
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