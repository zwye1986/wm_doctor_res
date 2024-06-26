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
						工号：
						<input type="text" class="inputText validate[required]" name="workNumber" value="${formDataMap['workNumber']}"/>
					</c:if>
					<c:if test="${!verification}">
						学员：姓名&ensp;${formDataMap['doctor']}&#12288;专业&ensp;${formDataMap['spe']}&#12288;工号：${formDataMap['workNumber']}
						<input type="hidden" name="doctor" value="${formDataMap['doctor']}"/>
						<input type="hidden" name="spe" value="${formDataMap['spe']}"/>
						<input type="hidden" name="workNumber" value="${formDataMap['workNumber']}"/>
					</c:if>
				</div>
				<div style="line-height:30px;padding-left:10px;">
					<c:if test="${verification}">
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="trainType" value="住院医师" <c:if test="${formDataMap['trainType']=='住院医师'}">checked</c:if>/>住院医师（
							<input type="checkbox" onchange="single(this)" name="doctorType" value="单位人" <c:if test="${formDataMap['doctorType']=='单位人'}">checked</c:if>/>单位人
							<input type="checkbox" onchange="single(this)" name="doctorType" value="社会人" <c:if test="${formDataMap['doctorType']=='社会人'}">checked</c:if>/>社会人
							&ensp;第<input type="text" class="inputText" style="width:40px" name="year" value="${formDataMap['year']}"/>年）</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="trainType" value="硕士研究生" <c:if test="${formDataMap['trainType']=='硕士研究生'}">checked</c:if>/>硕士研究生</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="trainType" value="博士研究生" <c:if test="${formDataMap['trainType']=='博士研究生'}">checked</c:if>/>博士研究生</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="trainType" value="实习医师" <c:if test="${formDataMap['trainType']=='实习医师'}">checked</c:if>/>实习医师</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="trainType" value="并轨研究生" <c:if test="${formDataMap['trainType']=='并轨研究生'}">checked</c:if>/>并轨研究生</label>&nbsp;
					</c:if>
					<c:if test="${!verification}">
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="trainType" value="住院医师" <c:if test="${formDataMap['trainType']=='住院医师'}">checked</c:if>/>住院医师（
							<input type="checkbox" onchange="single(this)" name="doctorType" value="单位人" <c:if test="${formDataMap['doctorType']=='单位人'}">checked</c:if>/>单位人
							<input type="checkbox" onchange="single(this)" name="doctorType" value="社会人" <c:if test="${formDataMap['doctorType']=='社会人'}">checked</c:if>/>社会人&#12288;第${formDataMap['year']}年）</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="trainType" value="硕士研究生" <c:if test="${formDataMap['trainType']=='硕士研究生'}">checked</c:if>/>硕士研究生</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="trainType" value="博士研究生" <c:if test="${formDataMap['trainType']=='博士研究生'}">checked</c:if>/>博士研究生</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="trainType" value="实习医师" <c:if test="${formDataMap['trainType']=='实习医师'}">checked</c:if>/>实习医师</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="trainType" value="并轨研究生" <c:if test="${formDataMap['trainType']=='并轨研究生'}">checked</c:if>/>并轨研究生</label>&nbsp;
					</c:if>
				</div>
				<div style="line-height:30px;padding-left:10px;">
					病人诊断：
					<c:if test="${verification}">
						<input type="text" class="inputText validate[required]" style="width:400px;" name="patientDesc" value="${formDataMap['patientDesc']}"/>
					</c:if>
					<c:if test="${!verification}">
						${formDataMap['patientDesc']}
						<input type="hidden" name="patientDesc" value="${formDataMap['patientDesc']}"/>
					</c:if>
				</div>
				<div style="line-height:30px;padding-left:10px;">
					病人年龄：
					<c:if test="${verification}">
						<input type="text" class="inputText validate[required]" style="width:40px" name="age" value="${formDataMap['age']}"/>岁&#12288;
						性别：
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="sex" value="男" <c:if test="${formDataMap['sex']=='男'}">checked</c:if>/>男</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="sex" value="女" <c:if test="${formDataMap['sex']=='女'}">checked</c:if>/>女</label>&#12288;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="time" value="初诊" <c:if test="${formDataMap['time']=='初诊'}">checked</c:if>/>初诊</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="time" value="复诊" <c:if test="${formDataMap['time']=='复诊'}">checked</c:if>/>复诊</label>&nbsp;
					</c:if>
					<c:if test="${!verification}">
						${formDataMap['age']}岁&#12288;性别：
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="sex" value="男" <c:if test="${formDataMap['sex']=='男'}">checked</c:if>/>男</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="sex" value="女" <c:if test="${formDataMap['sex']=='女'}">checked</c:if>/>女</label>&#12288;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="time" value="初诊" <c:if test="${formDataMap['time']=='初诊'}">checked</c:if>/>初诊</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="time" value="复诊" <c:if test="${formDataMap['time']=='复诊'}">checked</c:if>/>复诊</label>&nbsp;
					</c:if>
				</div>
				<div style="line-height:30px;padding-left:10px;">
					评估时间：
					<c:if test="${verification}">
						<input type="text" class="inputText validate[required]" name="assessTime" value="${formDataMap['assessTime']}"/>
						评估地点：
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="assessPlace" value="病房" <c:if test="${formDataMap['assessPlace']=='病房'}">checked</c:if>/>病房</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="assessPlace" value="门诊" <c:if test="${formDataMap['assessPlace']=='门诊'}">checked</c:if>/>门诊</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="assessPlace" value="急诊" <c:if test="${formDataMap['assessPlace']=='急诊'}">checked</c:if>/>急诊</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="assessPlace" value="ICU" <c:if test="${formDataMap['assessPlace']=='ICU'}">checked</c:if>/>ICU</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" onchange="single(this)" name="assessPlace" value="其他" <c:if test="${formDataMap['assessPlace']=='其他'}">checked</c:if>/>其他</label>&nbsp;
					</c:if>
					<c:if test="${!verification}">
						${formDataMap['assessTime']}&#12288;评估地点：
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="assessPlace" value="病房" <c:if test="${formDataMap['assessPlace']=='病房'}">checked</c:if>/>病房</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="assessPlace" value="门诊" <c:if test="${formDataMap['assessPlace']=='门诊'}">checked</c:if>/>门诊</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="assessPlace" value="急诊" <c:if test="${formDataMap['assessPlace']=='急诊'}">checked</c:if>/>急诊</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="assessPlace" value="ICU" <c:if test="${formDataMap['assessPlace']=='ICU'}">checked</c:if>/>ICU</label>&nbsp;
						<label><input class="validate[required]" type="checkbox" disabled="disabled" name="assessPlace" value="其他" <c:if test="${formDataMap['assessPlace']=='其他'}">checked</c:if>/>其他</label>&nbsp;
					</c:if>
				</div>
				<div style="line-height:30px;padding-left:10px;">
					评估重点：&#12288;
					<c:if test="${verification}">
						病史采集&ensp;<input type="text" class="inputText validate[required]" style="width:80px;" name="bscj" value="${formDataMap['bscj']}"/>&#12288;
						诊断&ensp;<input type="text" class="inputText validate[required]" style="width:80px;" name="zd" value="${formDataMap['zd']}"/>&#12288;
						治疗&ensp;<input type="text" class="inputText validate[required]" style="width:80px;" name="treat" value="${formDataMap['treat']}"/>&#12288;
						健康宣教&ensp;<input type="text" class="inputText validate[required]" style="width:80px;" name="jkxj" value="${formDataMap['jkxj']}"/>
					</c:if>
					<c:if test="${!verification}">
						病史采集&ensp;${formDataMap['bscj']}&#12288;&#12288;&#12288;
						诊断&ensp;${formDataMap['zd']}&#12288;&#12288;&#12288;
						治疗&ensp;${formDataMap['treat']}&#12288;&#12288;&#12288;
						健康宣教&ensp;${formDataMap['jkxj']}
					</c:if>
				</div>
				<table class="basic" width="100%" style="margin-top: 10px;">
					<tr>
						<td rowspan="2" style="font-weight:bold;">评分项目</td>
						<td colspan="4" style="text-align:center;font-weight:bold;">各项考评结果</td>
					</tr>
					<tr>
						<td style="min-width:95px;font-weight:bold;">未达要求</td>
						<td style="min-width:95px;font-weight:bold;">符合预期</td>
						<td style="min-width:95px;font-weight:bold;">表现优异</td>
						<td style="min-width:110px;font-weight:bold;">是否评估</td>
					</tr>
					<tr>
						<td><span style="font-weight:bold;">病史采集：</span>系统、完整、突出重点</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="bscjProj" value="1" <c:if test="${formDataMap['bscjProj']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="bscjProj" value="2" <c:if test="${formDataMap['bscjProj']=='2'}">checked</c:if>/>2</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="bscjProj" value="3" <c:if test="${formDataMap['bscjProj']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="bscjProj" value="1" <c:if test="${formDataMap['bscjProj']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="bscjProj" value="2" <c:if test="${formDataMap['bscjProj']=='2'}">checked</c:if>/>2</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="bscjProj" value="3" <c:if test="${formDataMap['bscjProj']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="bscjProj" value="4" <c:if test="${formDataMap['bscjProj']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="bscjProj" value="5" <c:if test="${formDataMap['bscjProj']=='5'}">checked</c:if>/>5</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="bscjProj" value="6" <c:if test="${formDataMap['bscjProj']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="bscjProj" value="4" <c:if test="${formDataMap['bscjProj']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="bscjProj" value="5" <c:if test="${formDataMap['bscjProj']=='5'}">checked</c:if>/>5</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="bscjProj" value="6" <c:if test="${formDataMap['bscjProj']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="bscjProj" value="7" <c:if test="${formDataMap['bscjProj']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="bscjProj" value="8" <c:if test="${formDataMap['bscjProj']=='8'}">checked</c:if>/>8</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="bscjProj" value="9" <c:if test="${formDataMap['bscjProj']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="bscjProj" value="7" <c:if test="${formDataMap['bscjProj']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="bscjProj" value="8" <c:if test="${formDataMap['bscjProj']=='8'}">checked</c:if>/>8</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="bscjProj" value="9" <c:if test="${formDataMap['bscjProj']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<select name="bscjProjFlag" style="width:100px" readonly="readonly" class="select validate[required]" onchange="bindScore(this)">
									<option value="否" <c:if test="${formDataMap['bscjProjFlag']=='否'}">selected="selected"</c:if>>否</option>
									<option value="是" <c:if test="${formDataMap['bscjProjFlag']=='是'}">selected="selected"</c:if> >是</option>
								</select>
							</c:if>
							<c:if test="${!(verification)}">
								${formDataMap['bscjProjFlag']}
								<input type="hidden" class="inputText" name="bscjProjFlag" value="${formDataMap['bscjProjFlag']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><span style="font-weight:bold;">体格检查</span>次序、手法正确、适当处理病人及家属的不适感</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="tgjc" value="1" <c:if test="${formDataMap['tgjc']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="tgjc" value="2" <c:if test="${formDataMap['tgjc']=='2'}">checked</c:if>/>2</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="tgjc" value="3" <c:if test="${formDataMap['tgjc']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="tgjc" value="1" <c:if test="${formDataMap['tgjc']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="tgjc" value="2" <c:if test="${formDataMap['tgjc']=='2'}">checked</c:if>/>2</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="tgjc" value="3" <c:if test="${formDataMap['tgjc']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="tgjc" value="4" <c:if test="${formDataMap['tgjc']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="tgjc" value="5" <c:if test="${formDataMap['tgjc']=='5'}">checked</c:if>/>5</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="tgjc" value="6" <c:if test="${formDataMap['tgjc']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="tgjc" value="4" <c:if test="${formDataMap['tgjc']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="tgjc" value="5" <c:if test="${formDataMap['tgjc']=='5'}">checked</c:if>/>5</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="tgjc" value="6" <c:if test="${formDataMap['tgjc']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="tgjc" value="7" <c:if test="${formDataMap['tgjc']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="tgjc" value="8" <c:if test="${formDataMap['tgjc']=='8'}">checked</c:if>/>8</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="tgjc" value="9" <c:if test="${formDataMap['tgjc']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="tgjc" value="7" <c:if test="${formDataMap['tgjc']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="tgjc" value="8" <c:if test="${formDataMap['tgjc']=='8'}">checked</c:if>/>8</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="tgjc" value="9" <c:if test="${formDataMap['tgjc']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<select name="tgjcFlag" style="width:100px" readonly="readonly" class="select validate[required]" onchange="bindScore(this)">
									<option value="否" <c:if test="${formDataMap['tgjcFlag']=='否'}">selected="selected"</c:if>>否</option>
									<option value="是" <c:if test="${formDataMap['tgjcFlag']=='是'}">selected="selected"</c:if> >是</option>
								</select>
							</c:if>
							<c:if test="${!(verification)}">
								${formDataMap['tgjcFlag']}
								<input type="hidden" class="inputText" name="tgjcFlag" value="${formDataMap['tgjcFlag']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><span style="font-weight:bold;">医患沟通</span>告知检查目的，建立良好关系与信赖感，适时回答病人提问</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="yhgt" value="1" <c:if test="${formDataMap['yhgt']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="yhgt" value="2" <c:if test="${formDataMap['yhgt']=='2'}">checked</c:if>/>2</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="yhgt" value="3" <c:if test="${formDataMap['yhgt']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="yhgt" value="1" <c:if test="${formDataMap['yhgt']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="yhgt" value="2" <c:if test="${formDataMap['yhgt']=='2'}">checked</c:if>	/>2</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="yhgt" value="3" <c:if test="${formDataMap['yhgt']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="yhgt" value="4" <c:if test="${formDataMap['yhgt']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="yhgt" value="5" <c:if test="${formDataMap['yhgt']=='5'}">checked</c:if>/>5</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="yhgt" value="6" <c:if test="${formDataMap['yhgt']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="yhgt" value="4" <c:if test="${formDataMap['yhgt']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="yhgt" value="5" <c:if test="${formDataMap['yhgt']=='5'}">checked</c:if>	/>5</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="yhgt" value="6" <c:if test="${formDataMap['yhgt']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="yhgt" value="7" <c:if test="${formDataMap['yhgt']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="yhgt" value="8" <c:if test="${formDataMap['yhgt']=='8'}">checked</c:if>/>8</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="yhgt" value="9" <c:if test="${formDataMap['yhgt']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="yhgt" value="7" <c:if test="${formDataMap['yhgt']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="yhgt" value="8" <c:if test="${formDataMap['yhgt']=='8'}">checked</c:if>	/>8</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="yhgt" value="9" <c:if test="${formDataMap['yhgt']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<select name="yhgtFlag" style="width:100px" readonly="readonly" class="select validate[required]" onchange="bindScore(this)">
									<option value="否" <c:if test="${formDataMap['yhgtFlag']=='否'}">selected="selected"</c:if>>否</option>
									<option value="是" <c:if test="${formDataMap['yhgtFlag']=='是'}">selected="selected"</c:if> >是</option>
								</select>
							</c:if>
							<c:if test="${!(verification)}">
								${formDataMap['yhgtFlag']}
								<input type="hidden" class="inputText" name="yhgtFlag" value="${formDataMap['yhgtFlag']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><span style="font-weight:bold;">临床判断</span>能综合体查和病史询问内容作出正确判断，选择与执行适当的诊断性检查；考虑治疗方法的风险与利益</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="lcpd" value="1" <c:if test="${formDataMap['lcpd']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="lcpd" value="2" <c:if test="${formDataMap['lcpd']=='2'}">checked</c:if>/>2</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="lcpd" value="3" <c:if test="${formDataMap['lcpd']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="lcpd" value="1" <c:if test="${formDataMap['lcpd']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="lcpd" value="2" <c:if test="${formDataMap['lcpd']=='2'}">checked</c:if>/>2</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="lcpd" value="3" <c:if test="${formDataMap['lcpd']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="lcpd" value="4" <c:if test="${formDataMap['lcpd']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="lcpd" value="5" <c:if test="${formDataMap['lcpd']=='5'}">checked</c:if>/>5</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="lcpd" value="6" <c:if test="${formDataMap['lcpd']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="lcpd" value="4" <c:if test="${formDataMap['lcpd']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="lcpd" value="5" <c:if test="${formDataMap['lcpd']=='5'}">checked</c:if>/>5</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="lcpd" value="6" <c:if test="${formDataMap['lcpd']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="lcpd" value="7" <c:if test="${formDataMap['lcpd']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="lcpd" value="8" <c:if test="${formDataMap['lcpd']=='8'}">checked</c:if>/>8</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="lcpd" value="9" <c:if test="${formDataMap['lcpd']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="lcpd" value="7" <c:if test="${formDataMap['lcpd']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="lcpd" value="8" <c:if test="${formDataMap['lcpd']=='8'}">checked</c:if>/>8</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="lcpd" value="9" <c:if test="${formDataMap['lcpd']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<select name="lcpdFlag" style="width:100px" readonly="readonly" class="select validate[required]" onchange="bindScore(this)">
									<option value="否" <c:if test="${formDataMap['lcpdFlag']=='否'}">selected="selected"</c:if>>否</option>
									<option value="是" <c:if test="${formDataMap['lcpdFlag']=='是'}">selected="selected"</c:if> >是</option>
								</select>
							</c:if>
							<c:if test="${!(verification)}">
								${formDataMap['lcpdFlag']}
								<input type="hidden" class="inputText" name="lcpdFlag" value="${formDataMap['lcpdFlag']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><span style="font-weight:bold;">专业素养</span>表现尊重、隐私保护、同理心、信任</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="zysy" value="1" <c:if test="${formDataMap['zysy']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="zysy" value="2" <c:if test="${formDataMap['zysy']=='2'}">checked</c:if>/>2</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="zysy" value="3" <c:if test="${formDataMap['zysy']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="zysy" value="1" <c:if test="${formDataMap['zysy']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="zysy" value="2" <c:if test="${formDataMap['zysy']=='2'}">checked</c:if>	/>2</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="zysy" value="3" <c:if test="${formDataMap['zysy']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="zysy" value="4" <c:if test="${formDataMap['zysy']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="zysy" value="5" <c:if test="${formDataMap['zysy']=='5'}">checked</c:if>/>5</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="zysy" value="6" <c:if test="${formDataMap['zysy']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="zysy" value="4" <c:if test="${formDataMap['zysy']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="zysy" value="5" <c:if test="${formDataMap['zysy']=='5'}">checked</c:if>	/>5</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="zysy" value="6" <c:if test="${formDataMap['zysy']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="zysy" value="7" <c:if test="${formDataMap['zysy']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="zysy" value="8" <c:if test="${formDataMap['zysy']=='8'}">checked</c:if>/>8</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="zysy" value="9" <c:if test="${formDataMap['zysy']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="zysy" value="7" <c:if test="${formDataMap['zysy']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="zysy" value="8" <c:if test="${formDataMap['zysy']=='8'}">checked</c:if>	/>8</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="zysy" value="9" <c:if test="${formDataMap['zysy']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<select name="zysyFlag" style="width:100px" readonly="readonly" class="select validate[required]" onchange="bindScore(this)">
									<option value="否" <c:if test="${formDataMap['zysyFlag']=='否'}">selected="selected"</c:if>>否</option>
									<option value="是" <c:if test="${formDataMap['zysyFlag']=='是'}">selected="selected"</c:if> >是</option>
								</select>
							</c:if>
							<c:if test="${!(verification)}">
								${formDataMap['zysyFlag']}
								<input type="hidden" class="inputText" name="zysyFlag" value="${formDataMap['zysyFlag']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><span style="font-weight:bold;">组织效能：</span>设定轻重缓急；及时且简洁地处理病患事务；具备整合能力；熟悉诊疗常规与操作规程，有效地在系统中利用其他资源以提供最佳医疗服务。</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="zzxn" value="1" <c:if test="${formDataMap['zzxn']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="zzxn" value="2" <c:if test="${formDataMap['zzxn']=='2'}">checked</c:if>/>2</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="zzxn" value="3" <c:if test="${formDataMap['zzxn']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="zzxn" value="1" <c:if test="${formDataMap['zzxn']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="zzxn" value="2" <c:if test="${formDataMap['zzxn']=='2'}">checked</c:if>/>2</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="zzxn" value="3" <c:if test="${formDataMap['zzxn']=='3'}">checked</c:if>/>3</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="zzxn" value="4" <c:if test="${formDataMap['zzxn']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="zzxn" value="5" <c:if test="${formDataMap['zzxn']=='5'}">checked</c:if>/>5</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="zzxn" value="6" <c:if test="${formDataMap['zzxn']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="zzxn" value="4" <c:if test="${formDataMap['zzxn']=='4'}">checked</c:if>/>4</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="zzxn" value="5" <c:if test="${formDataMap['zzxn']=='5'}">checked</c:if>/>5</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="zzxn" value="6" <c:if test="${formDataMap['zzxn']=='6'}">checked</c:if>/>6</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="zzxn" value="7" <c:if test="${formDataMap['zzxn']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="zzxn" value="8" <c:if test="${formDataMap['zzxn']=='8'}">checked</c:if>/>8</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="zzxn" value="9" <c:if test="${formDataMap['zzxn']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
							<c:if test="${!(verification)}">
								<label><input type="checkbox" disabled="disabled" name="zzxn" value="7" <c:if test="${formDataMap['zzxn']=='7'}">checked</c:if>/>7</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="zzxn" value="8" <c:if test="${formDataMap['zzxn']=='8'}">checked</c:if>/>8</label>&nbsp;
								<label><input type="checkbox" disabled="disabled" name="zzxn" value="9" <c:if test="${formDataMap['zzxn']=='9'}">checked</c:if>/>9</label>&nbsp;
							</c:if>
						</td>
						<td>
							<c:if test="${verification}">
								<select name="zzxnFlag" style="width:100px" readonly="readonly" class="select validate[required]" onchange="bindScore(this)">
									<option value="否" <c:if test="${formDataMap['zzxnFlag']=='否'}">selected="selected"</c:if>>否</option>
									<option value="是" <c:if test="${formDataMap['zzxnFlag']=='是'}">selected="selected"</c:if> >是</option>
								</select>
							</c:if>
							<c:if test="${!(verification)}">
								${formDataMap['zzxnFlag']}
								<input type="hidden" class="inputText" name="zzxnFlag" value="${formDataMap['zzxnFlag']}"/>
							</c:if>
						</td>
					</tr>
					<tr>
						<td><span style="font-weight:bold;">整体表现</span>对于病人照护的效率上表现出判断力、整合力、有效性；判断力、整合力、爱心、有效率等整体评量</td>
						<td>
							<c:if test="${verification}">
								<label><input type="checkbox" onchange="single(this)" name="ztbx" value="1" <c:if test="${formDataMap['ztbx']=='1'}">checked</c:if>/>1</label>&nbsp;
								<label><input type="checkbox" onchange="single(this)" name="ztbx" value="2" <c:if test="${formDataMap['ztbx']=='2'}">checked</c:if>/>2</label>&nbsp;
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
								<label><input type="checkbox" onchange="single(this)" name="ztbx" value="5" <c:if test="${formDataMap['ztbx']=='5'}">checked</c:if>/>5</label>&nbsp;
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
								<label><input type="checkbox" onchange="single(this)" name="ztbx" value="8" <c:if test="${formDataMap['ztbx']=='8'}">checked</c:if>/>8</label>&nbsp;
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
						直接观察时间：<input type="text" class="inputText validate[required]" name="seeTime" value="${formDataMap['seeTime']}"/>分钟（建议15分钟）；反馈时间：<input type="text" class="inputText validate[required]" name="backTime" value="${formDataMap['backTime']}"/>分钟（建议5分钟）
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
				<div style="text-align:center;"><span style="font-weight:bold;font-size:16px;">迷你临床演练与评估量表（Mini-CEX）</span></div>
				<div style="font-size:16px;">&#12288;&#12288;Mini－CEX注重学员与病人互动的表现，只需要15-30分钟，临床教师可以借此直接观察学员与病人之间的互动，进行mini-CEX的简易评估，并且与学员直接回馈。</div>
				<div style="font-size:16px;">&#12288;&#12288;建议临床教师，可以每次针对一项重点（病史采集、体格检查、临床判断、治疗决定及咨商卫教）进行评量，而人道专业、组织能力及效率及整体临床技能则可以在每一次重点评量时，综合评估。</div>
				<div><span style="font-weight:bold;">病史采集：</span>
					<input type="checkbox" checked="checked" disabled="disabled">称呼病人&#12288;
					<input type="checkbox" checked="checked" disabled="disabled">自我介绍&#12288;
					<input type="checkbox" checked="checked" disabled="disabled">对病人说明面谈之目的&#12288;
					<input type="checkbox" checked="checked" disabled="disabled">能鼓励病人说病史&#12288;
					<input type="checkbox" checked="checked" disabled="disabled">适切发问及引导以获得正确且足够的讯息&#12288;
					<input type="checkbox" checked="checked" disabled="disabled">对病人情绪及肢体语言能有适当的响应
				</div>
				<div><span style="font-weight:bold;">体格检查：</span>
					<input type="checkbox" checked="checked" disabled="disabled">告知病人检查目的及范围&#12288;
					<input type="checkbox" checked="checked" disabled="disabled">注意检查场所隐密性&#12288;
					<input type="checkbox" checked="checked" disabled="disabled">必要时，请护理人员在旁&#12288;
					<input type="checkbox" checked="checked" disabled="disabled">依病情需要及合理之次序&#12288;
					<input type="checkbox" checked="checked" disabled="disabled">正确操作及实施必要之步骤&#12288;
					<input type="checkbox" checked="checked" disabled="disabled">适当且审慎处理病人不适感
				</div>
				<div><span style="font-weight:bold;">临床判断：</span>
					<input type="checkbox" checked="checked" disabled="disabled">能综合面谈与身体检查资料&#12288;
					<input type="checkbox" checked="checked" disabled="disabled">能判读相关的检查结果&#12288;
					<input type="checkbox" checked="checked" disabled="disabled">鉴别诊断之能力&#12288;
					<input type="checkbox" checked="checked" disabled="disabled">临床判断之合理性与逻辑性
				</div>
				<div><span style="font-weight:bold;">医患交流：</span>
					<input type="checkbox" checked="checked" disabled="disabled">同意书之取得&#12288;
					<input type="checkbox" checked="checked" disabled="disabled">解释检查或处置的理由&#12288;
					<input type="checkbox" checked="checked" disabled="disabled">解释检查结果及临床相关性&#12288;
					<input type="checkbox" checked="checked" disabled="disabled">有关处置之教育与咨商
				</div>
				<div><span style="font-weight:bold;">专业素养：</span>
					<input type="checkbox" checked="checked" disabled="disabled">表现尊重&#12288;
					<input type="checkbox" checked="checked" disabled="disabled">同理心(感同身受)&#12288;
					<input type="checkbox" checked="checked" disabled="disabled">建立良好关系与信赖感&#12288;
					<input type="checkbox" checked="checked" disabled="disabled">能注意并处理病人是否舒适，注意隐私保护&#12288;
					<input type="checkbox" checked="checked" disabled="disabled">对病患询求相关讯息的需求能适当满足
				</div>
				<div><span style="font-weight:bold;">组织能力及效率：</span>
					<input type="checkbox" checked="checked" disabled="disabled">按优先级处置&#12288;
					<input type="checkbox" checked="checked" disabled="disabled">及时且适时&#12288;
					<input type="checkbox" checked="checked" disabled="disabled">历练而简洁
				</div>
				<div><span style="font-weight:bold;">整体临床技能：</span>
					<input type="checkbox" checked="checked" disabled="disabled">对病人的态度(爱心、同理心)&#12288;
					<input type="checkbox" checked="checked" disabled="disabled">整合数据与判断的能力&#12288;
					<input type="checkbox" checked="checked" disabled="disabled">整体有效性
				</div>
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