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
<script type="text/javascript">
function single(box){
    var curr=box.checked;
 	if(curr){
		var name=box.name;
		$(":checkbox[name='"+name+"']").attr("checked",false);
	}
	box.checked = curr;
	var dates = $(':checkbox', $(box).closest("tr"));
	var count = 0;
	$(dates).each(function(){
		if($(this).is(":checked")){
			count++;
		}
	});
	if(count>0){
		$('select', $(box).closest("tr")).val("是");
	}else{
		$('select', $(box).closest("tr")).val("否");
	}
  }
function bindScore(obj){
	if($(obj).val()=="否"){
		$(':checkbox', $(obj).closest("tr")).each(function(){
			$(this).removeAttr("checked");
		});
	}else{
		jboxTip("请先评分！");
		$(obj).val("否");
	}
}
function saveForm(){
	if($("#dopsForm").validationEngine("validate")){
		jboxConfirm("保存后将无法修改,确定吗?",function(){
			jboxPost("<s:url value='/res/rec/saveRegistryFormNew'/>",$('#dopsForm').serialize(),function(resp){
				if(resp="${GlobalConstant.SAVE_SUCCESSED}"){
					parentRefresh();
					jboxClose();
				}
			},null,true);
		},null);
	}
	
}

function jboxPrint(id) {
	jboxTip("正在准备打印…")
	setTimeout(function(){
		$("#title").show();
		var newstr = $("#"+id).html();
		var oldstr = document.body.innerHTML;
		var oldUrl= window.location.href;
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
	window.parent.document.mainIframe.location.reload();
}
</script>
<body>
<div class="mainright">
	<div class="content">
		<form id="dopsForm" style="position: relative">
			<div id="printDiv">
				<div id="title" style="width:100%;text-align: center;font-size: 12px;display: none;">
					临床操作技能评估量化表
				</div>
				<input type="hidden" name="formFileName" value="${formFileName}"/>
				<input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
				<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
				<input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
				<input type="hidden" name="operUserFlow" value="${param.userFlow}"/>
				<input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
				<c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq param.roleFlag}">
					<input type="hidden" name="auditStatusId" value="${recStatusEnumTeacherAuditY.id}"/>
					<input type="hidden" name="headAuditStatusId" value="${recStatusEnumHeadAuditY.id}"/>
				</c:if>
				<c:set var="verification" value="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq param.roleFlag && empty rec.auditStatusId}"/>
				<div style="line-height:30px;padding-left:10px;">
					教师：
					<c:if test="${verification}">
						<input type="text" class="inputText validate[required]" name="teacher" value="${formDataMap['teacher']}"/>&#12288;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="titleName" value="主任医师" <c:if test="${formDataMap['titleName']=='主任医师'}">checked</c:if>/>主任医师</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="titleName" value="副主任医师" <c:if test="${formDataMap['titleName']=='副主任医师'}">checked</c:if>/>副主任医师</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="titleName" value="主治医师" <c:if test="${formDataMap['titleName']=='主治医师'}">checked</c:if>/>主治医师</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="titleName" value="高年资住院医师" <c:if test="${formDataMap['titleName']=='高年资住院医师'}">checked</c:if>/>高年资住院医师</label>&nbsp;
					</c:if>
					<c:if test="${!verification}">
						${formDataMap['teacher']}&#12288;
						<input type="hidden" class="inputText" name="teacher" value="${formDataMap['teacher']}"/>
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="titleName" value="主任医师" <c:if test="${formDataMap['titleName']=='主任医师'}">checked</c:if>/>主任医师</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="titleName" value="副主任医师" <c:if test="${formDataMap['titleName']=='副主任医师'}">checked</c:if>/>副主任医师</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="titleName" value="主治医师" <c:if test="${formDataMap['titleName']=='主治医师'}">checked</c:if>/>主治医师</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="titleName" value="高年资住院医师" <c:if test="${formDataMap['titleName']=='高年资住院医师'}">checked</c:if>/>高年资住院医师</label>&nbsp;
					</c:if>
				</div>
				<div style="line-height:30px;padding-left:10px;">
					<c:if test="${verification}">
						学员：姓名
						<input type="text" class="inputText validate[required]" name="doctor" value="${formDataMap['doctor']}"/>
						专业
						<input type="text" class="inputText validate[required]" name="spe" value="${formDataMap['spe']}"/>
					</c:if>
					<c:if test="${!verification}">
						学员：姓名&ensp;${formDataMap['doctor']}&#12288;专业&ensp;${formDataMap['spe']}
						<input type="hidden" name="doctor" value="${formDataMap['doctor']}"/>
						<input type="hidden" name="spe" value="${formDataMap['spe']}"/>
					</c:if>
				</div>
				<div style="line-height:30px;padding-left:10px;">
					<c:if test="${verification}">
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="trainType" value="住院医师" <c:if test="${formDataMap['trainType']=='住院医师'}">checked</c:if>/>住院医师（第<input type="text" class="inputText" style="width:40px" name="year" value="${formDataMap['year']}"/>年）</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="trainType" value="硕士研究生" <c:if test="${formDataMap['trainType']=='硕士研究生'}">checked</c:if>/>硕士研究生</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="trainType" value="博士研究生" <c:if test="${formDataMap['trainType']=='博士研究生'}">checked</c:if>/>博士研究生</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="trainType" value="实习医师" <c:if test="${formDataMap['trainType']=='实习医师'}">checked</c:if>/>实习医师</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="trainType" value="其他" <c:if test="${formDataMap['trainType']=='其他'}">checked</c:if>/>其他</label>&nbsp;
					</c:if>
					<c:if test="${!verification}">
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="trainType" value="住院医师" <c:if test="${formDataMap['trainType']=='住院医师'}">checked</c:if>/>住院医师（第${formDataMap['year']}年）</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="trainType" value="硕士研究生" <c:if test="${formDataMap['trainType']=='硕士研究生'}">checked</c:if>/>硕士研究生</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="trainType" value="博士研究生" <c:if test="${formDataMap['trainType']=='博士研究生'}">checked</c:if>/>博士研究生</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="trainType" value="实习医师" <c:if test="${formDataMap['trainType']=='实习医师'}">checked</c:if>/>实习医师</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="trainType" value="其他" <c:if test="${formDataMap['trainType']=='其他'}">checked</c:if>/>其他</label>&nbsp;
					</c:if>
				</div>
				<div style="line-height:30px;padding-left:10px;">
					临床操作名称：
					<c:if test="${verification}">
						<input type="text" class="inputText validate[required]" style="width:400px;" name="operateName" value="${formDataMap['operateName']}"/>
					</c:if>
					<c:if test="${!verification}">
						${formDataMap['operateName']}
						<input type="hidden" name="operateName" value="${formDataMap['operateName']}"/>
					</c:if>
				</div>
				<div style="line-height:30px;padding-left:10px;">
					临床操作难度：
					<c:if test="${verification}">
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="operateLevel" value="简易" <c:if test="${formDataMap['operateLevel']=='简易'}">checked</c:if>/>简易</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="operateLevel" value="一般" <c:if test="${formDataMap['operateLevel']=='一般'}">checked</c:if>/>一般</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="operateLevel" value="困难" <c:if test="${formDataMap['operateLevel']=='困难'}">checked</c:if>/>困难</label>&nbsp;
					</c:if>
					<c:if test="${!verification}">
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="operateLevel" value="简易" <c:if test="${formDataMap['operateLevel']=='简易'}">checked</c:if>/>简易</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="operateLevel" value="一般" <c:if test="${formDataMap['operateLevel']=='一般'}">checked</c:if>/>一般</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="operateLevel" value="困难" <c:if test="${formDataMap['operateLevel']=='困难'}">checked</c:if>/>主治医师</label>&nbsp;
					</c:if>
				</div>
				<div style="line-height:30px;padding-left:10px;">
					评估时间：
					<c:if test="${verification}">
						<input type="text" class="inputText validate[required]" name="assessTime" value="${formDataMap['assessTime']}"/>
						地点：
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="assessPlace" value="病房" <c:if test="${formDataMap['assessPlace']=='病房'}">checked</c:if>/>病房</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="assessPlace" value="门诊" <c:if test="${formDataMap['assessPlace']=='门诊'}">checked</c:if>/>门诊</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="assessPlace" value="急诊" <c:if test="${formDataMap['assessPlace']=='急诊'}">checked</c:if>/>急诊</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="assessPlace" value="ICU" <c:if test="${formDataMap['assessPlace']=='ICU'}">checked</c:if>/>ICU</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="assessPlace" value="其他" <c:if test="${formDataMap['assessPlace']=='其他'}">checked</c:if>/>其他</label>&nbsp;
					</c:if>
					<c:if test="${!verification}">
						${formDataMap['assessTime']}&#12288;地点：
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="assessPlace" value="病房" <c:if test="${formDataMap['assessPlace']=='病房'}">checked</c:if>/>病房</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="assessPlace" value="门诊" <c:if test="${formDataMap['assessPlace']=='门诊'}">checked</c:if>/>门诊</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="assessPlace" value="急诊" <c:if test="${formDataMap['assessPlace']=='急诊'}">checked</c:if>/>急诊</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="assessPlace" value="ICU" <c:if test="${formDataMap['assessPlace']=='ICU'}">checked</c:if>/>ICU</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="assessPlace" value="其他" <c:if test="${formDataMap['assessPlace']=='其他'}">checked</c:if>/>其他</label>&nbsp;
					</c:if>
				</div>
				<div style="line-height:30px;padding-left:10px;">
					病人资料：年龄：
					<c:if test="${verification}">
						<input type="text" class="inputText validate[required]" style="width:40px" name="age" value="${formDataMap['age']}"/>岁&#12288;
						性别：
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="sex" value="男" <c:if test="${formDataMap['sex']=='男'}">checked</c:if>/>男</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="sex" value="女" <c:if test="${formDataMap['sex']=='女'}">checked</c:if>/>女</label>&nbsp;
					</c:if>
					<c:if test="${!verification}">
						${formDataMap['age']}岁&#12288;性别：
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="sex" value="男" <c:if test="${formDataMap['sex']=='男'}">checked</c:if>/>男</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="sex" value="女" <c:if test="${formDataMap['sex']=='女'}">checked</c:if>/>女</label>&nbsp;
					</c:if>
				</div>
				<table class="basic" width="100%" style="margin-top: 10px;">
					<tr>
						<td rowspan="2" style="font-weight:bold;">评分项目</td>
						<td colspan="4" style="text-align:center;font-weight:bold;">各项考评结果</td>
					</tr>
					<tr>
						<td style="min-width:95px;font-weight:bold;">未达要求</td>
						<td style="min-width:95px;font-weight:bold;">合格</td>
						<td style="min-width:95px;font-weight:bold;">表现优异</td>
						<td style="min-width:110px;font-weight:bold;">是否评估</td>
					</tr>
					<tr>
						<td><span style="font-weight:bold;">相关知识：</span>操作适应症、禁忌症等</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="xgzs" value="1" <c:if test="${formDataMap['xgzs']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="xgzs" value="2" <c:if test="${formDataMap['xgzs']=='2'}">checked</c:if>	/>2</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="xgzs" value="3" <c:if test="${formDataMap['xgzs']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="xgzs" value="1" <c:if test="${formDataMap['xgzs']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="xgzs" value="2" <c:if test="${formDataMap['xgzs']=='2'}">checked</c:if>	/>2</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="xgzs" value="3" <c:if test="${formDataMap['xgzs']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="xgzs" value="4" <c:if test="${formDataMap['xgzs']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="xgzs" value="5" <c:if test="${formDataMap['xgzs']=='5'}">checked</c:if>	/>5</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="xgzs" value="6" <c:if test="${formDataMap['xgzs']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="xgzs" value="4" <c:if test="${formDataMap['xgzs']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="xgzs" value="5" <c:if test="${formDataMap['xgzs']=='5'}">checked</c:if>	/>5</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="xgzs" value="6" <c:if test="${formDataMap['xgzs']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="xgzs" value="7" <c:if test="${formDataMap['xgzs']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="xgzs" value="8" <c:if test="${formDataMap['xgzs']=='8'}">checked</c:if>	/>8</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="xgzs" value="9" <c:if test="${formDataMap['xgzs']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="xgzs" value="7" <c:if test="${formDataMap['xgzs']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="xgzs" value="8" <c:if test="${formDataMap['xgzs']=='8'}">checked</c:if>	/>8</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="xgzs" value="9" <c:if test="${formDataMap['xgzs']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<select name="xgzsFlag" style="width:100px" readonly="readonly" class="select validate[required]" onchange="bindScore(this)">
									<option value="否" <c:if test="${formDataMap['xgzsFlag']=='否'}">selected="selected"</c:if>>否</option>
									<option value="是" <c:if test="${formDataMap['xgzsFlag']=='是'}">selected="selected"</c:if> >是</option>
								</select>
							</c:if>
							<c:if test="${!(verification)}">
								${formDataMap['xgzsFlag']}
								<input type="hidden" class="inputText" name="xgzsFlag" value="${formDataMap['xgzsFlag']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><span style="font-weight:bold;">操作前的知情同意</span></td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="czqdzqty" value="1" <c:if test="${formDataMap['czqdzqty']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="czqdzqty" value="2" <c:if test="${formDataMap['czqdzqty']=='2'}">checked</c:if>	/>2</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="czqdzqty" value="3" <c:if test="${formDataMap['czqdzqty']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="czqdzqty" value="1" <c:if test="${formDataMap['czqdzqty']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="czqdzqty" value="2" <c:if test="${formDataMap['czqdzqty']=='2'}">checked</c:if>	/>2</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="czqdzqty" value="3" <c:if test="${formDataMap['czqdzqty']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="czqdzqty" value="4" <c:if test="${formDataMap['czqdzqty']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="czqdzqty" value="5" <c:if test="${formDataMap['czqdzqty']=='5'}">checked</c:if>	/>5</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="czqdzqty" value="6" <c:if test="${formDataMap['czqdzqty']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="czqdzqty" value="4" <c:if test="${formDataMap['czqdzqty']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="czqdzqty" value="5" <c:if test="${formDataMap['czqdzqty']=='5'}">checked</c:if>	/>5</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="czqdzqty" value="6" <c:if test="${formDataMap['czqdzqty']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="czqdzqty" value="7" <c:if test="${formDataMap['czqdzqty']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="czqdzqty" value="8" <c:if test="${formDataMap['czqdzqty']=='8'}">checked</c:if>	/>8</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="czqdzqty" value="9" <c:if test="${formDataMap['czqdzqty']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="czqdzqty" value="7" <c:if test="${formDataMap['czqdzqty']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="czqdzqty" value="8" <c:if test="${formDataMap['czqdzqty']=='8'}">checked</c:if>	/>8</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="czqdzqty" value="9" <c:if test="${formDataMap['czqdzqty']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<select name="czqdzqtyFlag" style="width:100px" readonly="readonly" class="select validate[required]" onchange="bindScore(this)">
									<option value="否" <c:if test="${formDataMap['czqdzqtyFlag']=='否'}">selected="selected"</c:if>>否</option>
									<option value="是" <c:if test="${formDataMap['czqdzqtyFlag']=='是'}">selected="selected"</c:if> >是</option>
								</select>
							</c:if>
							<c:if test="${!(verification)}">
								${formDataMap['czqdzqtyFlag']}
								<input type="hidden" class="inputText" name="czqdzqtyFlag" value="${formDataMap['czqdzqtyFlag']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><span style="font-weight:bold;">术前准备</span></td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="sqzb" value="1" <c:if test="${formDataMap['sqzb']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="sqzb" value="2" <c:if test="${formDataMap['sqzb']=='2'}">checked</c:if>	/>2</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="sqzb" value="3" <c:if test="${formDataMap['sqzb']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="sqzb" value="1" <c:if test="${formDataMap['sqzb']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="sqzb" value="2" <c:if test="${formDataMap['sqzb']=='2'}">checked</c:if>	/>2</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="sqzb" value="3" <c:if test="${formDataMap['sqzb']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="sqzb" value="4" <c:if test="${formDataMap['sqzb']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="sqzb" value="5" <c:if test="${formDataMap['sqzb']=='5'}">checked</c:if>	/>5</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="sqzb" value="6" <c:if test="${formDataMap['sqzb']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="sqzb" value="4" <c:if test="${formDataMap['sqzb']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="sqzb" value="5" <c:if test="${formDataMap['sqzb']=='5'}">checked</c:if>	/>5</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="sqzb" value="6" <c:if test="${formDataMap['sqzb']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="sqzb" value="7" <c:if test="${formDataMap['sqzb']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="sqzb" value="8" <c:if test="${formDataMap['sqzb']=='8'}">checked</c:if>	/>8</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="sqzb" value="9" <c:if test="${formDataMap['sqzb']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="sqzb" value="7" <c:if test="${formDataMap['sqzb']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="sqzb" value="8" <c:if test="${formDataMap['sqzb']=='8'}">checked</c:if>	/>8</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="sqzb" value="9" <c:if test="${formDataMap['sqzb']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<select name="sqzbFlag" style="width:100px" readonly="readonly" class="select validate[required]" onchange="bindScore(this)">
									<option value="否" <c:if test="${formDataMap['sqzbFlag']=='否'}">selected="selected"</c:if>>否</option>
									<option value="是" <c:if test="${formDataMap['sqzbFlag']=='是'}">selected="selected"</c:if> >是</option>
								</select>
							</c:if>
							<c:if test="${!(verification)}">
								${formDataMap['sqzbFlag']}
								<input type="hidden" class="inputText" name="sqzbFlag" value="${formDataMap['sqzbFlag']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><span style="font-weight:bold;">技能熟练度/技术</span></td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="jnsld" value="1" <c:if test="${formDataMap['jnsld']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="jnsld" value="2" <c:if test="${formDataMap['jnsld']=='2'}">checked</c:if>	/>2</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="jnsld" value="3" <c:if test="${formDataMap['jnsld']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="jnsld" value="1" <c:if test="${formDataMap['jnsld']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="jnsld" value="2" <c:if test="${formDataMap['jnsld']=='2'}">checked</c:if>	/>2</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="jnsld" value="3" <c:if test="${formDataMap['jnsld']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="jnsld" value="4" <c:if test="${formDataMap['jnsld']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="jnsld" value="5" <c:if test="${formDataMap['jnsld']=='5'}">checked</c:if>	/>5</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="jnsld" value="6" <c:if test="${formDataMap['jnsld']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="jnsld" value="4" <c:if test="${formDataMap['jnsld']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="jnsld" value="5" <c:if test="${formDataMap['jnsld']=='5'}">checked</c:if>	/>5</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="jnsld" value="6" <c:if test="${formDataMap['jnsld']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="jnsld" value="7" <c:if test="${formDataMap['jnsld']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="jnsld" value="8" <c:if test="${formDataMap['jnsld']=='8'}">checked</c:if>	/>8</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="jnsld" value="9" <c:if test="${formDataMap['jnsld']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="jnsld" value="7" <c:if test="${formDataMap['jnsld']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="jnsld" value="8" <c:if test="${formDataMap['jnsld']=='8'}">checked</c:if>	/>8</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="jnsld" value="9" <c:if test="${formDataMap['jnsld']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<select name="jnsldFlag" style="width:100px" readonly="readonly" class="select validate[required]" onchange="bindScore(this)">
									<option value="否" <c:if test="${formDataMap['jnsldFlag']=='否'}">selected="selected"</c:if>>否</option>
									<option value="是" <c:if test="${formDataMap['jnsldFlag']=='是'}">selected="selected"</c:if> >是</option>
								</select>
							</c:if>
							<c:if test="${!(verification)}">
								${formDataMap['jnsldFlag']}
								<input type="hidden" class="inputText" name="jnsldFlag" value="${formDataMap['jnsldFlag']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><span style="font-weight:bold;">无菌观念</span></td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="wjgn" value="1" <c:if test="${formDataMap['wjgn']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="wjgn" value="2" <c:if test="${formDataMap['wjgn']=='2'}">checked</c:if>	/>2</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="wjgn" value="3" <c:if test="${formDataMap['wjgn']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="wjgn" value="1" <c:if test="${formDataMap['wjgn']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="wjgn" value="2" <c:if test="${formDataMap['wjgn']=='2'}">checked</c:if>	/>2</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="wjgn" value="3" <c:if test="${formDataMap['wjgn']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="wjgn" value="4" <c:if test="${formDataMap['wjgn']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="wjgn" value="5" <c:if test="${formDataMap['wjgn']=='5'}">checked</c:if>	/>5</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="wjgn" value="6" <c:if test="${formDataMap['wjgn']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="wjgn" value="4" <c:if test="${formDataMap['wjgn']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="wjgn" value="5" <c:if test="${formDataMap['wjgn']=='5'}">checked</c:if>	/>5</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="wjgn" value="6" <c:if test="${formDataMap['wjgn']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="wjgn" value="7" <c:if test="${formDataMap['wjgn']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="wjgn" value="8" <c:if test="${formDataMap['wjgn']=='8'}">checked</c:if>	/>8</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="wjgn" value="9" <c:if test="${formDataMap['wjgn']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="wjgn" value="7" <c:if test="${formDataMap['wjgn']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="wjgn" value="8" <c:if test="${formDataMap['wjgn']=='8'}">checked</c:if>	/>8</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="wjgn" value="9" <c:if test="${formDataMap['wjgn']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<select name="wjgnFlag" style="width:100px" readonly="readonly" class="select validate[required]" onchange="bindScore(this)">
									<option value="否" <c:if test="${formDataMap['wjgnFlag']=='否'}">selected="selected"</c:if>>否</option>
									<option value="是" <c:if test="${formDataMap['wjgnFlag']=='是'}">selected="selected"</c:if> >是</option>
								</select>
							</c:if>
							<c:if test="${!(verification)}">
								${formDataMap['wjgnFlag']}
								<input type="hidden" class="inputText" name="wjgnFlag" value="${formDataMap['wjgnFlag']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><span style="font-weight:bold;">操作完成后的处理：</span>安全地置放医疗器材；记录程序，样本的标记和术后教育；安排适当的后续医疗措施。</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="czhcl" value="1" <c:if test="${formDataMap['czhcl']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="czhcl" value="2" <c:if test="${formDataMap['czhcl']=='2'}">checked</c:if>	/>2</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="czhcl" value="3" <c:if test="${formDataMap['czhcl']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="czhcl" value="1" <c:if test="${formDataMap['czhcl']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="czhcl" value="2" <c:if test="${formDataMap['czhcl']=='2'}">checked</c:if>	/>2</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="czhcl" value="3" <c:if test="${formDataMap['czhcl']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="czhcl" value="4" <c:if test="${formDataMap['czhcl']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="czhcl" value="5" <c:if test="${formDataMap['czhcl']=='5'}">checked</c:if>	/>5</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="czhcl" value="6" <c:if test="${formDataMap['czhcl']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="czhcl" value="4" <c:if test="${formDataMap['czhcl']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="czhcl" value="5" <c:if test="${formDataMap['czhcl']=='5'}">checked</c:if>	/>5</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="czhcl" value="6" <c:if test="${formDataMap['czhcl']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="czhcl" value="7" <c:if test="${formDataMap['czhcl']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="czhcl" value="8" <c:if test="${formDataMap['czhcl']=='8'}">checked</c:if>	/>8</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="czhcl" value="9" <c:if test="${formDataMap['czhcl']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="czhcl" value="7" <c:if test="${formDataMap['czhcl']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="czhcl" value="8" <c:if test="${formDataMap['czhcl']=='8'}">checked</c:if>	/>8</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="czhcl" value="9" <c:if test="${formDataMap['czhcl']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<select name="czhclFlag" style="width:100px" readonly="readonly" class="select validate[required]" onchange="bindScore(this)">
									<option value="否" <c:if test="${formDataMap['czhclFlag']=='否'}">selected="selected"</c:if>>否</option>
									<option value="是" <c:if test="${formDataMap['czhclFlag']=='是'}">selected="selected"</c:if> >是</option>
								</select>
							</c:if>
							<c:if test="${!(verification)}">
								${formDataMap['czhclFlag']}
								<input type="hidden" class="inputText" name="czhclFlag" value="${formDataMap['czhclFlag']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><span style="font-weight:bold;">沟通技巧：</span>探究病患的观点；不使用专业术语；开放和诚实；同理心；与病患共同决定病患的医疗处置计划。</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="gtjq" value="1" <c:if test="${formDataMap['gtjq']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="gtjq" value="2" <c:if test="${formDataMap['gtjq']=='2'}">checked</c:if>	/>2</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="gtjq" value="3" <c:if test="${formDataMap['gtjq']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="gtjq" value="1" <c:if test="${formDataMap['gtjq']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="gtjq" value="2" <c:if test="${formDataMap['gtjq']=='2'}">checked</c:if>	/>2</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="gtjq" value="3" <c:if test="${formDataMap['gtjq']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="gtjq" value="4" <c:if test="${formDataMap['gtjq']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="gtjq" value="5" <c:if test="${formDataMap['gtjq']=='5'}">checked</c:if>	/>5</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="gtjq" value="6" <c:if test="${formDataMap['gtjq']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="gtjq" value="4" <c:if test="${formDataMap['gtjq']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="gtjq" value="5" <c:if test="${formDataMap['gtjq']=='5'}">checked</c:if>	/>5</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="gtjq" value="6" <c:if test="${formDataMap['gtjq']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="gtjq" value="7" <c:if test="${formDataMap['gtjq']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="gtjq" value="8" <c:if test="${formDataMap['gtjq']=='8'}">checked</c:if>	/>8</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="gtjq" value="9" <c:if test="${formDataMap['gtjq']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="gtjq" value="7" <c:if test="${formDataMap['gtjq']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="gtjq" value="8" <c:if test="${formDataMap['gtjq']=='8'}">checked</c:if>	/>8</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="gtjq" value="9" <c:if test="${formDataMap['gtjq']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<select name="gtjqFlag" style="width:100px" readonly="readonly" class="select validate[required]" onchange="bindScore(this)">
									<option value="否" <c:if test="${formDataMap['gtjqFlag']=='否'}">selected="selected"</c:if>>否</option>
									<option value="是" <c:if test="${formDataMap['gtjqFlag']=='是'}">selected="selected"</c:if> >是</option>
								</select>
							</c:if>
							<c:if test="${!(verification)}">
								${formDataMap['gtjqFlag']}
								<input type="hidden" class="inputText" name="gtjqFlag" value="${formDataMap['gtjqFlag']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><span style="font-weight:bold;">专业素养及人文：</span>展现尊重、同情、同理，建立信任感；致力于病患的舒适感需求；尊重个人资料保密性；行为合乎伦理标准；体察法律体制；体察个人能力的极限。</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="zysyjrw" value="1" <c:if test="${formDataMap['zysyjrw']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="zysyjrw" value="2" <c:if test="${formDataMap['zysyjrw']=='2'}">checked</c:if>	/>2</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="zysyjrw" value="3" <c:if test="${formDataMap['zysyjrw']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="zysyjrw" value="1" <c:if test="${formDataMap['zysyjrw']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="zysyjrw" value="2" <c:if test="${formDataMap['zysyjrw']=='2'}">checked</c:if>	/>2</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="zysyjrw" value="3" <c:if test="${formDataMap['zysyjrw']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="zysyjrw" value="4" <c:if test="${formDataMap['zysyjrw']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="zysyjrw" value="5" <c:if test="${formDataMap['zysyjrw']=='5'}">checked</c:if>	/>5</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="zysyjrw" value="6" <c:if test="${formDataMap['zysyjrw']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="zysyjrw" value="4" <c:if test="${formDataMap['zysyjrw']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="zysyjrw" value="5" <c:if test="${formDataMap['zysyjrw']=='5'}">checked</c:if>	/>5</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="zysyjrw" value="6" <c:if test="${formDataMap['zysyjrw']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="zysyjrw" value="7" <c:if test="${formDataMap['zysyjrw']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="zysyjrw" value="8" <c:if test="${formDataMap['zysyjrw']=='8'}">checked</c:if>	/>8</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="zysyjrw" value="9" <c:if test="${formDataMap['zysyjrw']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="zysyjrw" value="7" <c:if test="${formDataMap['zysyjrw']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="zysyjrw" value="8" <c:if test="${formDataMap['zysyjrw']=='8'}">checked</c:if>	/>8</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="zysyjrw" value="9" <c:if test="${formDataMap['zysyjrw']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<select name="zysyjrwFlag" style="width:100px" readonly="readonly" class="select validate[required]" onchange="bindScore(this)">
									<option value="否" <c:if test="${formDataMap['zysyjrwFlag']=='否'}">selected="selected"</c:if>>否</option>
									<option value="是" <c:if test="${formDataMap['zysyjrwFlag']=='是'}">selected="selected"</c:if> >是</option>
								</select>
							</c:if>
							<c:if test="${!(verification)}">
								${formDataMap['zysyjrwFlag']}
								<input type="hidden" class="inputText" name="zysyjrwFlag" value="${formDataMap['zysyjrwFlag']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><span style="font-weight:bold;">适时寻求协助</span></td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="ssxqxz" value="1" <c:if test="${formDataMap['ssxqxz']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="ssxqxz" value="2" <c:if test="${formDataMap['ssxqxz']=='2'}">checked</c:if>	/>2</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="ssxqxz" value="3" <c:if test="${formDataMap['ssxqxz']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="ssxqxz" value="1" <c:if test="${formDataMap['ssxqxz']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="ssxqxz" value="2" <c:if test="${formDataMap['ssxqxz']=='2'}">checked</c:if>	/>2</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="ssxqxz" value="3" <c:if test="${formDataMap['ssxqxz']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="ssxqxz" value="4" <c:if test="${formDataMap['ssxqxz']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="ssxqxz" value="5" <c:if test="${formDataMap['ssxqxz']=='5'}">checked</c:if>	/>5</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="ssxqxz" value="6" <c:if test="${formDataMap['ssxqxz']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="ssxqxz" value="4" <c:if test="${formDataMap['ssxqxz']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="ssxqxz" value="5" <c:if test="${formDataMap['ssxqxz']=='5'}">checked</c:if>	/>5</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="ssxqxz" value="6" <c:if test="${formDataMap['ssxqxz']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="ssxqxz" value="7" <c:if test="${formDataMap['ssxqxz']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="ssxqxz" value="8" <c:if test="${formDataMap['ssxqxz']=='8'}">checked</c:if>	/>8</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="ssxqxz" value="9" <c:if test="${formDataMap['ssxqxz']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="ssxqxz" value="7" <c:if test="${formDataMap['ssxqxz']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="ssxqxz" value="8" <c:if test="${formDataMap['ssxqxz']=='8'}">checked</c:if>	/>8</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="ssxqxz" value="9" <c:if test="${formDataMap['ssxqxz']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<select name="ssxqxzFlag" style="width:100px" readonly="readonly" class="select validate[required]" onchange="bindScore(this)">
									<option value="否" <c:if test="${formDataMap['ssxqxzFlag']=='否'}">selected="selected"</c:if>>否</option>
									<option value="是" <c:if test="${formDataMap['ssxqxzFlag']=='是'}">selected="selected"</c:if> >是</option>
								</select>
							</c:if>
							<c:if test="${!(verification)}">
								${formDataMap['ssxqxzFlag']}
								<input type="hidden" class="inputText" name="ssxqxzFlag" value="${formDataMap['ssxqxzFlag']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><span style="font-weight:bold;">整体表现</span></td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="ztbx" value="1" <c:if test="${formDataMap['ztbx']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="ztbx" value="2" <c:if test="${formDataMap['ztbx']=='2'}">checked</c:if>	/>2</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="ztbx" value="3" <c:if test="${formDataMap['ztbx']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="ztbx" value="1" <c:if test="${formDataMap['ztbx']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="ztbx" value="2" <c:if test="${formDataMap['ztbx']=='2'}">checked</c:if>	/>2</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="ztbx" value="3" <c:if test="${formDataMap['ztbx']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="ztbx" value="4" <c:if test="${formDataMap['ztbx']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="ztbx" value="5" <c:if test="${formDataMap['ztbx']=='5'}">checked</c:if>	/>5</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="ztbx" value="6" <c:if test="${formDataMap['ztbx']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="ztbx" value="4" <c:if test="${formDataMap['ztbx']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="ztbx" value="5" <c:if test="${formDataMap['ztbx']=='5'}">checked</c:if>	/>5</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="ztbx" value="6" <c:if test="${formDataMap['ztbx']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="ztbx" value="7" <c:if test="${formDataMap['ztbx']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="ztbx" value="8" <c:if test="${formDataMap['ztbx']=='8'}">checked</c:if>	/>8</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="ztbx" value="9" <c:if test="${formDataMap['ztbx']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="ztbx" value="7" <c:if test="${formDataMap['ztbx']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="ztbx" value="8" <c:if test="${formDataMap['ztbx']=='8'}">checked</c:if>	/>8</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="ztbx" value="9" <c:if test="${formDataMap['ztbx']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<select name="ztbxFlag" style="width:100px" readonly="readonly" class="select validate[required]" onchange="bindScore(this)">
									<option value="否" <c:if test="${formDataMap['ztbxFlag']=='否'}">selected="selected"</c:if>>否</option>
									<option value="是" <c:if test="${formDataMap['ztbxFlag']=='是'}">selected="selected"</c:if> >是</option>
								</select>
							</c:if>
							<c:if test="${!(verification)}">
								${formDataMap['ztbxFlag']}
								<input type="hidden" class="inputText" name="ztbxFlag" value="${formDataMap['ztbxFlag']}"/>
							</c:if>
						</td>
					</tr>
				</table>
				<div style="line-height:30px;padding-left:10px;">
					<c:if test="${verification}">
						直接观察时间：<input type="text" class="inputText validate[required]" name="seeTime" value="${formDataMap['seeTime']}"/>分钟；反馈时间：<input type="text" class="inputText validate[required]" name="backTime" value="${formDataMap['backTime']}"/>分钟
					</c:if>
					<c:if test="${!(verification)}">
						直接观察时间：${formDataMap['seeTime']}分钟；反馈时间：${formDataMap['backTime']}分钟
						<input type="hidden" name="seeTime" value="${formDataMap['seeTime']}"/>
						<input type="hidden" name="backTime" value="${formDataMap['backTime']}"/>
					</c:if>
				</div>
				<div style="line-height:30px;padding-left:10px;">
					教师对此次测评满意程度：劣
					<c:if test="${verification}">
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="teacherFeel" value="1" <c:if test="${formDataMap['teacherFeel']=='1'}">checked</c:if>/>1</label>&#12288;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="teacherFeel" value="2" <c:if test="${formDataMap['teacherFeel']=='2'}">checked</c:if>/>2</label>&#12288;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="teacherFeel" value="3" <c:if test="${formDataMap['teacherFeel']=='3'}">checked</c:if>/>3</label>&#12288;|
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="teacherFeel" value="4" <c:if test="${formDataMap['teacherFeel']=='4'}">checked</c:if>/>4</label>&#12288;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="teacherFeel" value="5" <c:if test="${formDataMap['teacherFeel']=='5'}">checked</c:if>/>5</label>&#12288;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="teacherFeel" value="6" <c:if test="${formDataMap['teacherFeel']=='6'}">checked</c:if>/>6</label>&#12288;|
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="teacherFeel" value="7" <c:if test="${formDataMap['teacherFeel']=='7'}">checked</c:if>/>7</label>&#12288;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="teacherFeel" value="8" <c:if test="${formDataMap['teacherFeel']=='8'}">checked</c:if>/>8</label>&#12288;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="teacherFeel" value="9" <c:if test="${formDataMap['teacherFeel']=='9'}">checked</c:if>/>9</label>&#12288;优
					</c:if>
					<c:if test="${!(verification)}">
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="teacherFeel" value="1" <c:if test="${formDataMap['teacherFeel']=='1'}">checked</c:if>/>1</label>&#12288;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="teacherFeel" value="2" <c:if test="${formDataMap['teacherFeel']=='2'}">checked</c:if>/>2</label>&#12288;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="teacherFeel" value="3" <c:if test="${formDataMap['teacherFeel']=='3'}">checked</c:if>/>3</label>&#12288;|
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="teacherFeel" value="4" <c:if test="${formDataMap['teacherFeel']=='4'}">checked</c:if>/>4</label>&#12288;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="teacherFeel" value="5" <c:if test="${formDataMap['teacherFeel']=='5'}">checked</c:if>	/>5</label>&#12288;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="teacherFeel" value="6" <c:if test="${formDataMap['teacherFeel']=='6'}">checked</c:if>/>6</label>&#12288;|
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="teacherFeel" value="7" <c:if test="${formDataMap['teacherFeel']=='7'}">checked</c:if>/>7</label>&#12288;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="teacherFeel" value="8" <c:if test="${formDataMap['teacherFeel']=='8'}">checked</c:if>	/>8</label>&#12288;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="teacherFeel" value="9" <c:if test="${formDataMap['teacherFeel']=='9'}">checked</c:if>/>9</label>&#12288;优
					</c:if>
				</div>
				<div style="line-height:30px;padding-left:10px;">
					学员对此次测评满意程度：劣
					<c:if test="${verification}">
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="doctorFeel" value="1" <c:if test="${formDataMap['doctorFeel']=='1'}">checked</c:if>/>1</label>&#12288;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="doctorFeel" value="2" <c:if test="${formDataMap['doctorFeel']=='2'}">checked</c:if>/>2</label>&#12288;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="doctorFeel" value="3" <c:if test="${formDataMap['doctorFeel']=='3'}">checked</c:if>/>3</label>&#12288;|
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="doctorFeel" value="4" <c:if test="${formDataMap['doctorFeel']=='4'}">checked</c:if>/>4</label>&#12288;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="doctorFeel" value="5" <c:if test="${formDataMap['doctorFeel']=='5'}">checked</c:if>/>5</label>&#12288;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="doctorFeel" value="6" <c:if test="${formDataMap['doctorFeel']=='6'}">checked</c:if>/>6</label>&#12288;|
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="doctorFeel" value="7" <c:if test="${formDataMap['doctorFeel']=='7'}">checked</c:if>/>7</label>&#12288;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="doctorFeel" value="8" <c:if test="${formDataMap['doctorFeel']=='8'}">checked</c:if>/>8</label>&#12288;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="doctorFeel" value="9" <c:if test="${formDataMap['doctorFeel']=='9'}">checked</c:if>/>9</label>&#12288;优
					</c:if>
					<c:if test="${!(verification)}">
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="doctorFeel" value="1" <c:if test="${formDataMap['doctorFeel']=='1'}">checked</c:if>/>1</label>&#12288;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="doctorFeel" value="2" <c:if test="${formDataMap['doctorFeel']=='2'}">checked</c:if>/>2</label>&#12288;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="doctorFeel" value="3" <c:if test="${formDataMap['doctorFeel']=='3'}">checked</c:if>/>3</label>&#12288;|
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="doctorFeel" value="4" <c:if test="${formDataMap['doctorFeel']=='4'}">checked</c:if>/>4</label>&#12288;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="doctorFeel" value="5" <c:if test="${formDataMap['doctorFeel']=='5'}">checked</c:if>/>5</label>&#12288;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="doctorFeel" value="6" <c:if test="${formDataMap['doctorFeel']=='6'}">checked</c:if>/>6</label>&#12288;|
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="doctorFeel" value="7" <c:if test="${formDataMap['doctorFeel']=='7'}">checked</c:if>/>7</label>&#12288;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="doctorFeel" value="8" <c:if test="${formDataMap['doctorFeel']=='8'}">checked</c:if>/>8</label>&#12288;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="doctorFeel" value="9" <c:if test="${formDataMap['doctorFeel']=='9'}">checked</c:if>/>9</label>&#12288;优
					</c:if>
				</div>
				<div style="line-height:30px;padding-left:10px;">
						<span style="font-weight:bold;">表现良好项目：</span>
						<c:if test="${verification}">
							<textarea class="xltxtarea validate[required]" name="bxlhxm" style="width:200px;height:50px;">${formDataMap['bxlhxm']}</textarea>
						</c:if>
						<c:if test="${!(verification)}">
							${formDataMap['bxlhxm']}
							<input type="hidden" name="bxlhxm" value="${formDataMap['bxlhxm']}"/>
						</c:if>
						<span style="font-weight:bold;margin-left:50px;">建议加强项目：</span>
						<c:if test="${verification}">
							<textarea class="xltxtarea validate[required]" name="jyjqxm" style="width:200px;height:50px;">${formDataMap['jyjqxm']}</textarea>
						</c:if>
						<c:if test="${!(verification)}">
							${formDataMap['jyjqxm']}
							<input type="hidden" name="jyjqxm" value="${formDataMap['jyjqxm']}"/>
						</c:if>
					</div>
				<div style="line-height:30px;padding-left:10px;">
					<span style="margin-left:120px;">教师签名：<span style="margin-left:290px;"></span>学员签名：</span>
				</div>
			</div>
			<div style="line-height:30px;padding-left:10px;">
				<div style="text-align:center;line-height:20px;"><span style="font-size:18px;font-weight:bolder">DOPS</span><span style="font-weight:bold">评分项目说明</span><br/>(评为完全做到的标准表现)</div>
				<div><span style="font-weight:bold;">获取同意书：</span>正确选择签署人、解释内容(含适应症、治疗选择、风险、预期的结果)、适当时机与场合</div>
				<div><span style="font-weight:bold;">术前准备：</span>辨识病人及部位、辨识术式、准备必要器材</div>
				<div><span style="font-weight:bold;">麻醉/止痛镇静处置：</span>在安全及效果上做最佳的选择，并成功执行</div>
				<div><span style="font-weight:bold;">操作技术：</span>技术准确、熟练，并且步骤顺序正确</div>
				<div><span style="font-weight:bold;">术后处置：</span>选用适当药物、剂量与给药途径、采取适当监视步骤</div>
				<div><span style="font-weight:bold;">无菌技术：</span>技术完整、正确</div>
				<div><span style="font-weight:bold;">警觉性：</span>早期辨识警讯、会监视病人反应并判读</div>
				<div><span style="font-weight:bold;">专业素养：</span>注意到病人的不适、具同理心、显示尊重、以病人为考虑中心、态度负责认真、诚实</div>
				<div><span style="font-weight:bold;">相关知识：</span>能说出手术适应症、相关解剖生理病理、施行技术的缘由</div>
				<div><span style="font-weight:bold;">沟通技术：</span>说明清楚有条理，自我介绍、称呼病人及家属用名字及尊称、有医疗之外的寒喧话语不打断对方讲话、使用对方能了解的语言、显示关心与礼貌、眼睛看着对方、仔细倾听/记住对方讲的话且有响应</div>
				<div><span style="font-weight:bold;">整体表现：</span>对此操作型技术表现您的整体评价</div>
			</div>
		</form>
		<div style="padding-top: 5px;text-align: center;">
			<c:if test="${verification}">
				<input class="search" type="button" value="保&#12288;存"  onclick="saveForm();"/>
			</c:if>
			<c:if test="${GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.roleFlag && empty formDataMap['studentSign']}">
				<input class="search" type="button" value="保&#12288;存"  onclick="saveForm();"/>
			</c:if>
			<c:if test="${(GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.roleFlag or 'manage'eq param.roleFlag) && not empty rec.auditStatusId}">
				<input class="search" type="button" value="打&#12288;印"  onclick="jboxPrint('printDiv');"/>
			</c:if>
			<input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();"/>
		</div>
	</div>
</div>
</body>
</html>