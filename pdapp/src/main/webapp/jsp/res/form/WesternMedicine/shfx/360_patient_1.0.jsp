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
							parentRefresh();
							jboxClose();
						}
					},null,true);
				},null);
			}
		}

		$(function(){
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
				<div id="title" style="width:100%;text-align: center;font-size: 12px;display: none;">
					患者对住院医师评价
				</div>
				<input type="hidden" name="formFileName" value="${formFileName}"/>
				<input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
				<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
				<input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
				<input type="hidden" name="operUserFlow" value="${param.userFlow}"/>
				<input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
				<c:set var="verification" value="${GlobalConstant.RES_ROLE_SCOPE_HEAD eq param.roleFlag and empty rec.recFlow }"/>
				<table class="basic" width="100%" style="margin-top: 10px;border: none;">
					<input type="hidden" id="ZongHe" name="ZongHe" value="${formDataMap['ZongHe']}"/>
					<tr style="border: none;">
						<td colspan="3" style="border: none;">有关你的医师的信息</td>
					</tr>
					<tr style="border: none;">
						<td colspan="3" style="border: none;">1、医师姓名：<input type="text" style="text-align: left;" class="inputText validate[required]" name="studentName" value="${formDataMap['studentName']}"/></td>
					</tr>
					<tr style="border: none;">
						<td colspan="3" style="border: none;">2、去年一年你看这位医师的次数：&emsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="seeTimes"   value="1" <c:if test="${formDataMap['seeTimes']=='1'}">checked</c:if>/>&nbsp;1次</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="seeTimes"   value="2" <c:if test="${formDataMap['seeTimes']=='2'}">checked</c:if>/>&nbsp;2-3次</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="seeTimes"   value="3" <c:if test="${formDataMap['seeTimes']=='3'}">checked</c:if>/>&nbsp;超过3次</label>&nbsp;
						</td>
					</tr>
					<tr style="border: none;">
						<td colspan="3" style="border: none;">3、这位医师接待你的方式：&emsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="receptionMode"   value="1" <c:if test="${formDataMap['receptionMode']=='1'}">checked</c:if>/>&nbsp;住院患者</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="receptionMode"   value="2" <c:if test="${formDataMap['receptionMode']=='2'}">checked</c:if>/>&nbsp;门诊患者</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="receptionMode"   value="3" <c:if test="${formDataMap['receptionMode']=='3'}">checked</c:if>/>&nbsp;二者兼有</label>&nbsp;
						</td>
					</tr>
					<tr style="border: none;">
						<td colspan="3" style="border: none;">下表描述你的医师的行为，请用此标准将你的医师的表现填至右侧，依据你看医师时的评价。</td>
					</tr>
				</table>
				<table class="basic" width="100%" style="margin-top: 5px;">
					<tr>
						<td colspan="3">A对患者的关怀</td>
					</tr>
					<tr>
						<td colspan="3">1、提高健康维护（谈论有关预防性事宜，如戒烟、控制体重、饮酒、锻炼等等）&emsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care1"   value="1" <c:if test="${formDataMap['care1']=='1'}">checked</c:if>/>&nbsp;从不1</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care1"   value="2" <c:if test="${formDataMap['care1']=='2'}">checked</c:if>/>&nbsp;有时2</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care1"   value="3" <c:if test="${formDataMap['care1']=='3'}">checked</c:if>/>&nbsp;一般3</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care1"   value="4" <c:if test="${formDataMap['care1']=='4'}">checked</c:if>/>&nbsp;经常4</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care1"   value="5" <c:if test="${formDataMap['care1']=='5'}">checked</c:if>/>&nbsp;总是/一直5</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care1"   value="6" <c:if test="${formDataMap['care1']=='6'}">checked</c:if>/>&nbsp;无法评估6</label>&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">2、规范地询问我正在服用的（非）处方药&emsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care2"   value="1" <c:if test="${formDataMap['care2']=='1'}">checked</c:if>/>&nbsp;从不1</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care2"   value="2" <c:if test="${formDataMap['care2']=='2'}">checked</c:if>/>&nbsp;有时2</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care2"   value="3" <c:if test="${formDataMap['care2']=='3'}">checked</c:if>/>&nbsp;一般3</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care2"   value="4" <c:if test="${formDataMap['care2']=='4'}">checked</c:if>/>&nbsp;经常4</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care2"   value="5" <c:if test="${formDataMap['care2']=='5'}">checked</c:if>/>&nbsp;总是/一直5</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care2"   value="6" <c:if test="${formDataMap['care2']=='6'}">checked</c:if>/>&nbsp;无法评估6</label>&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">3、清楚地解释我的医疗问题&emsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care3"   value="1" <c:if test="${formDataMap['care3']=='1'}">checked</c:if>/>&nbsp;从不1</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care3"   value="2" <c:if test="${formDataMap['care3']=='2'}">checked</c:if>/>&nbsp;有时2</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care3"   value="3" <c:if test="${formDataMap['care3']=='3'}">checked</c:if>/>&nbsp;一般3</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care3"   value="4" <c:if test="${formDataMap['care3']=='4'}">checked</c:if>/>&nbsp;经常4</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care3"   value="5" <c:if test="${formDataMap['care3']=='5'}">checked</c:if>/>&nbsp;总是/一直5</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care3"   value="6" <c:if test="${formDataMap['care3']=='6'}">checked</c:if>/>&nbsp;无法评估6</label>&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">4、清楚地解释我的治疗选择&emsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care4"   value="1" <c:if test="${formDataMap['care4']=='1'}">checked</c:if>/>&nbsp;从不1</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care4"   value="2" <c:if test="${formDataMap['care4']=='2'}">checked</c:if>/>&nbsp;有时2</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care4"   value="3" <c:if test="${formDataMap['care4']=='3'}">checked</c:if>/>&nbsp;一般3</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care4"   value="4" <c:if test="${formDataMap['care4']=='4'}">checked</c:if>/>&nbsp;经常4</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care4"   value="5" <c:if test="${formDataMap['care4']=='5'}">checked</c:if>/>&nbsp;总是/一直5</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care4"   value="6" <c:if test="${formDataMap['care4']=='6'}">checked</c:if>/>&nbsp;无法评估6</label>&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">5、告诉我药物的副作用&emsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care5"   value="1" <c:if test="${formDataMap['care5']=='1'}">checked</c:if>/>&nbsp;从不1</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care5"   value="2" <c:if test="${formDataMap['care5']=='2'}">checked</c:if>/>&nbsp;有时2</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care5"   value="3" <c:if test="${formDataMap['care5']=='3'}">checked</c:if>/>&nbsp;一般3</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care5"   value="4" <c:if test="${formDataMap['care5']=='4'}">checked</c:if>/>&nbsp;经常4</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care5"   value="5" <c:if test="${formDataMap['care5']=='5'}">checked</c:if>/>&nbsp;总是/一直5</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care5"   value="6" <c:if test="${formDataMap['care5']=='6'}">checked</c:if>/>&nbsp;无法评估6</label>&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">6、告诉我何时复诊&emsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care6"   value="1" <c:if test="${formDataMap['care6']=='1'}">checked</c:if>/>&nbsp;从不1</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care6"   value="2" <c:if test="${formDataMap['care6']=='2'}">checked</c:if>/>&nbsp;有时2</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care6"   value="3" <c:if test="${formDataMap['care6']=='3'}">checked</c:if>/>&nbsp;一般3</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care6"   value="4" <c:if test="${formDataMap['care6']=='4'}">checked</c:if>/>&nbsp;经常4</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care6"   value="5" <c:if test="${formDataMap['care6']=='5'}">checked</c:if>/>&nbsp;总是/一直5</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care6"   value="6" <c:if test="${formDataMap['care6']=='6'}">checked</c:if>/>&nbsp;无法评估6</label>&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">7、清楚地解释以后如何避免我的问题&emsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care7"   value="1" <c:if test="${formDataMap['care7']=='1'}">checked</c:if>/>&nbsp;从不1</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care7"   value="2" <c:if test="${formDataMap['care7']=='2'}">checked</c:if>/>&nbsp;有时2</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care7"   value="3" <c:if test="${formDataMap['care7']=='3'}">checked</c:if>/>&nbsp;一般3</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care7"   value="4" <c:if test="${formDataMap['care7']=='4'}">checked</c:if>/>&nbsp;经常4</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care7"   value="5" <c:if test="${formDataMap['care7']=='5'}">checked</c:if>/>&nbsp;总是/一直5</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="care7"   value="6" <c:if test="${formDataMap['care7']=='6'}">checked</c:if>/>&nbsp;无法评估6</label>&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">B职业道德</td>
					</tr>
					<tr>
						<td colspan="3">1、对我表示尊重&emsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="morality1"   value="1" <c:if test="${formDataMap['morality1']=='1'}">checked</c:if>/>&nbsp;从不1</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="morality1"   value="2" <c:if test="${formDataMap['morality1']=='2'}">checked</c:if>/>&nbsp;有时2</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="morality1"   value="3" <c:if test="${formDataMap['morality1']=='3'}">checked</c:if>/>&nbsp;一般3</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="morality1"   value="4" <c:if test="${formDataMap['morality1']=='4'}">checked</c:if>/>&nbsp;经常4</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="morality1"   value="5" <c:if test="${formDataMap['morality1']=='5'}">checked</c:if>/>&nbsp;总是/一直5</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="morality1"   value="6" <c:if test="${formDataMap['morality1']=='6'}">checked</c:if>/>&nbsp;无法评估6</label>&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">2、对我有礼貌&emsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="morality2"   value="1" <c:if test="${formDataMap['morality2']=='1'}">checked</c:if>/>&nbsp;从不1</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="morality2"   value="2" <c:if test="${formDataMap['morality2']=='2'}">checked</c:if>/>&nbsp;有时2</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="morality2"   value="3" <c:if test="${formDataMap['morality2']=='3'}">checked</c:if>/>&nbsp;一般3</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="morality2"   value="4" <c:if test="${formDataMap['morality2']=='4'}">checked</c:if>/>&nbsp;经常4</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="morality2"   value="5" <c:if test="${formDataMap['morality2']=='5'}">checked</c:if>/>&nbsp;总是/一直5</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="morality2"   value="6" <c:if test="${formDataMap['morality2']=='6'}">checked</c:if>/>&nbsp;无法评估6</label>&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">C人际关系和交流能力</td>
					</tr>
					<tr>
						<td colspan="3">1、聆听我说的话&emsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal1"   value="1" <c:if test="${formDataMap['interpersonal1']=='1'}">checked</c:if>/>&nbsp;从不1</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal1"   value="2" <c:if test="${formDataMap['interpersonal1']=='2'}">checked</c:if>/>&nbsp;有时2</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal1"   value="3" <c:if test="${formDataMap['interpersonal1']=='3'}">checked</c:if>/>&nbsp;一般3</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal1"   value="4" <c:if test="${formDataMap['interpersonal1']=='4'}">checked</c:if>/>&nbsp;经常4</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal1"   value="5" <c:if test="${formDataMap['interpersonal1']=='5'}">checked</c:if>/>&nbsp;总是/一直5</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal1"   value="6" <c:if test="${formDataMap['interpersonal1']=='6'}">checked</c:if>/>&nbsp;无法评估6</label>&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">2、在我身上花费足够的时间&emsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal2"   value="1" <c:if test="${formDataMap['interpersonal2']=='1'}">checked</c:if>/>&nbsp;从不1</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal2"   value="2" <c:if test="${formDataMap['interpersonal2']=='2'}">checked</c:if>/>&nbsp;有时2</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal2"   value="3" <c:if test="${formDataMap['interpersonal2']=='3'}">checked</c:if>/>&nbsp;一般3</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal2"   value="4" <c:if test="${formDataMap['interpersonal2']=='4'}">checked</c:if>/>&nbsp;经常4</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal2"   value="5" <c:if test="${formDataMap['interpersonal2']=='5'}">checked</c:if>/>&nbsp;总是/一直5</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal2"   value="6" <c:if test="${formDataMap['interpersonal2']=='6'}">checked</c:if>/>&nbsp;无法评估6</label>&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">3、对我的问题感兴趣&emsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal3"   value="1" <c:if test="${formDataMap['interpersonal3']=='1'}">checked</c:if>/>&nbsp;从不1</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal3"   value="2" <c:if test="${formDataMap['interpersonal3']=='2'}">checked</c:if>/>&nbsp;有时2</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal3"   value="3" <c:if test="${formDataMap['interpersonal3']=='3'}">checked</c:if>/>&nbsp;一般3</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal3"   value="4" <c:if test="${formDataMap['interpersonal3']=='4'}">checked</c:if>/>&nbsp;经常4</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal3"   value="5" <c:if test="${formDataMap['interpersonal3']=='5'}">checked</c:if>/>&nbsp;总是/一直5</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal3"   value="6" <c:if test="${formDataMap['interpersonal3']=='6'}">checked</c:if>/>&nbsp;无法评估6</label>&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">4、彻底回答我的问题&emsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal4"   value="1" <c:if test="${formDataMap['interpersonal4']=='1'}">checked</c:if>/>&nbsp;从不1</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal4"   value="2" <c:if test="${formDataMap['interpersonal4']=='2'}">checked</c:if>/>&nbsp;有时2</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal4"   value="3" <c:if test="${formDataMap['interpersonal4']=='3'}">checked</c:if>/>&nbsp;一般3</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal4"   value="4" <c:if test="${formDataMap['interpersonal4']=='4'}">checked</c:if>/>&nbsp;经常4</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal4"   value="5" <c:if test="${formDataMap['interpersonal4']=='5'}">checked</c:if>/>&nbsp;总是/一直5</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal4"   value="6" <c:if test="${formDataMap['interpersonal4']=='6'}">checked</c:if>/>&nbsp;无法评估6</label>&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">5、帮助解决我的恐惧和焦虑&emsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal5"   value="1" <c:if test="${formDataMap['interpersonal5']=='1'}">checked</c:if>/>&nbsp;从不1</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal5"   value="2" <c:if test="${formDataMap['interpersonal5']=='2'}">checked</c:if>/>&nbsp;有时2</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal5"   value="3" <c:if test="${formDataMap['interpersonal5']=='3'}">checked</c:if>/>&nbsp;一般3</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal5"   value="4" <c:if test="${formDataMap['interpersonal5']=='4'}">checked</c:if>/>&nbsp;经常4</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal5"   value="5" <c:if test="${formDataMap['interpersonal5']=='5'}">checked</c:if>/>&nbsp;总是/一直5</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal5"   value="6" <c:if test="${formDataMap['interpersonal5']=='6'}">checked</c:if>/>&nbsp;无法评估6</label>&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">6、和我谈论治疗方案&emsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal6"   value="1" <c:if test="${formDataMap['interpersonal6']=='1'}">checked</c:if>/>&nbsp;从不1</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal6"   value="2" <c:if test="${formDataMap['interpersonal6']=='2'}">checked</c:if>/>&nbsp;有时2</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal6"   value="3" <c:if test="${formDataMap['interpersonal6']=='3'}">checked</c:if>/>&nbsp;一般3</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal6"   value="4" <c:if test="${formDataMap['interpersonal6']=='4'}">checked</c:if>/>&nbsp;经常4</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal6"   value="5" <c:if test="${formDataMap['interpersonal6']=='5'}">checked</c:if>/>&nbsp;总是/一直5</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal6"   value="6" <c:if test="${formDataMap['interpersonal6']=='6'}">checked</c:if>/>&nbsp;无法评估6</label>&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">7、在合理的时间内回复我的信息&emsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal7"   value="1" <c:if test="${formDataMap['interpersonal7']=='1'}">checked</c:if>/>&nbsp;从不1</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal7"   value="2" <c:if test="${formDataMap['interpersonal7']=='2'}">checked</c:if>/>&nbsp;有时2</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal7"   value="3" <c:if test="${formDataMap['interpersonal7']=='3'}">checked</c:if>/>&nbsp;一般3</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal7"   value="4" <c:if test="${formDataMap['interpersonal7']=='4'}">checked</c:if>/>&nbsp;经常4</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal7"   value="5" <c:if test="${formDataMap['interpersonal7']=='5'}">checked</c:if>/>&nbsp;总是/一直5</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="interpersonal7"   value="6" <c:if test="${formDataMap['interpersonal7']=='6'}">checked</c:if>/>&nbsp;无法评估6</label>&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">D基于系统的实践</td>
					</tr>
					<tr>
						<td colspan="3">1、在需要的时候向专家咨询&emsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="practice1"   value="1" <c:if test="${formDataMap['practice1']=='1'}">checked</c:if>/>&nbsp;从不1</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="practice1"   value="2" <c:if test="${formDataMap['practice1']=='2'}">checked</c:if>/>&nbsp;有时2</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="practice1"   value="3" <c:if test="${formDataMap['practice1']=='3'}">checked</c:if>/>&nbsp;一般3</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="practice1"   value="4" <c:if test="${formDataMap['practice1']=='4'}">checked</c:if>/>&nbsp;经常4</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="practice1"   value="5" <c:if test="${formDataMap['practice1']=='5'}">checked</c:if>/>&nbsp;总是/一直5</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="practice1"   value="6" <c:if test="${formDataMap['practice1']=='6'}">checked</c:if>/>&nbsp;无法评估6</label>&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="3">2、有效利用你的外院资料作为额外信息&emsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="practice2"   value="1" <c:if test="${formDataMap['practice2']=='1'}">checked</c:if>/>&nbsp;从不1</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="practice2"   value="2" <c:if test="${formDataMap['practice2']=='2'}">checked</c:if>/>&nbsp;有时2</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="practice2"   value="3" <c:if test="${formDataMap['practice2']=='3'}">checked</c:if>/>&nbsp;一般3</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="practice2"   value="4" <c:if test="${formDataMap['practice2']=='4'}">checked</c:if>/>&nbsp;经常4</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="practice2"   value="5" <c:if test="${formDataMap['practice2']=='5'}">checked</c:if>/>&nbsp;总是/一直5</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="practice2"   value="6" <c:if test="${formDataMap['practice2']=='6'}">checked</c:if>/>&nbsp;无法评估6</label>&nbsp;
						</td>
					</tr>
				</table>
				<table class="basic" width="100%" style="margin-top: 10px;border: none;">
					<tr style="border: none;">
						<td colspan="3" style="border: none;">总结</td>
					</tr>
					<tr style="border: none;">
						<td colspan="3" style="border: none;">1、我愿意找这位医生看病&emsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="summary1"   value="Y" <c:if test="${formDataMap['summary1']=='Y'}">checked</c:if>/>&nbsp;是</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="summary1"   value="N" <c:if test="${formDataMap['summary1']=='N'}">checked</c:if>/>&nbsp;否</label>&nbsp;
						</td>
					</tr>
					<tr style="border: none;">
						<td colspan="3" style="border: none;">2、我愿意让我的朋友找这位医生看病&emsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="summary2"   value="Y" <c:if test="${formDataMap['summary2']=='Y'}">checked</c:if>/>&nbsp;是</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="summary2"   value="N" <c:if test="${formDataMap['summary2']=='N'}">checked</c:if>/>&nbsp;否</label>&nbsp;
						</td>
					</tr>
					<tr style="border: none;">
						<td colspan="3" style="border: none;">3、我对医生很满意&emsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="summary3"   value="Y" <c:if test="${formDataMap['summary3']=='Y'}">checked</c:if>/>&nbsp;是</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="summary3"   value="N" <c:if test="${formDataMap['summary3']=='N'}">checked</c:if>/>&nbsp;否</label>&nbsp;
						</td>
					</tr>
					<tr style="border: none;">
						<td colspan="3" style="border: none;">4、你最喜欢这位医生的什么地方？
							<input type="text" style="text-align: left;" class="inputText validate[required]" name="summary4" value="${formDataMap['summary4']}"/>
						</td>
					</tr>
					<tr style="border: none;">
						<td colspan="3" style="border: none;">5、你希望你的医生做些什么其他的（不同的）？
							<input type="text" style="text-align: left;" class="inputText validate[required]" name="summary5" value="${formDataMap['summary5']}"/>
						</td>
					</tr>
					<tr style="border: none;">
						<td colspan="3" style="border: none;">6、其他评价
							<input type="text" style="text-align: left;" class="inputText validate[required]" name="summary6" value="${formDataMap['summary6']}"/>
						</td>
					</tr>
					<tr style="border: none;">
						<td colspan="3" style="border: none;">
							你的信息
						</td>
					</tr>
					<tr style="border: none;">
						<td colspan="3" style="border: none;">
							性别：
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="sex"  value="男" <c:if test="${formDataMap['sex']=='男'}">checked</c:if>/>&nbsp;男</label>&nbsp;
							<label style="padding-left: 2%;"><input class="validate[required]" type="checkbox" onchange="single(this)" name="sex"  value="女" <c:if test="${formDataMap['sex']=='女'}">checked</c:if>/>&nbsp;女</label>&nbsp;
							年龄：<input type="text" style="text-align: left;" class="inputText validate[required]" name="age" value="${formDataMap['age']}"/>
						</td>
					</tr>
			</table>
			</div>
		</form>
		<div style="padding-top: 5px;text-align: center;">
			<c:if test="${verification}">
				<input class="search" type="button" value="保&#12288;存"  onclick="saveForm();"/>
			</c:if>
			<%--<c:if test="${(GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.roleFlag or 'manage'eq param.roleFlag) }">--%>
				<%--<input class="search" type="button" value="打&#12288;印"  onclick="jboxPrint('printDiv');"/>--%>
			<%--</c:if>--%>
			<input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();"/>
		</div>
	</div>
</div>
</body>
</html>