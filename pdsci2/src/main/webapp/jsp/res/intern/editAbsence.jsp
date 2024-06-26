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
	<jsp:param name="ueditor" value="true"/>
</jsp:include>
<style type="text/css">
	[type='text']{width:150px;height: 22px;}
	select{width: 153px;height: 27px;}
	#boxHome .item:HOVER{background-color: #eee;}
</style>

<script type="text/javascript">
	
	 $(document).ready(function(){
		calculateDay();
		<c:if test="${isRegister eq 'Y'}">
		chooseDoctor();
		 </c:if>
//		changeOthers($("#schDeptFlow").val());
		$(".name").hide();
		 checkAbsenceType();
		 <c:if test="${resRoleScope eq 'repealAbsence' ||resRoleScope eq 'repealAbsenceAudit'}">
		 	$("[name='absenceTypeId'],#startDate,#endDate,[name='absenceReson']").attr("disabled","disabled");
		 	$("#reCheck").hide();
		 </c:if>
		 <c:if test="${resRoleScope eq 'repealAbsenceAudit'}">
		 $("#repealAbsenceDate").attr("disabled","disabled");
		 </c:if>
	});
	
	function filterDoctor(){
		$(".name").hide();
		$("#doctorFlow").val("");
		var sessionNumber = $("[name='sessionNumber']").val();
		var trainingSpeId = $("[name='trainingSpeId']").val();
		if(sessionNumber && trainingSpeId){
			$(".name._"+sessionNumber+"._"+trainingSpeId).show();
		}
	}
	
	function chooseDoctor(){
		$("[name='doctorFlow']").val($("#doctorFlow").val());
		$("[name='doctorName']").val($("#doctorFlow option:selected").attr("doctorName"));
		$("#schDeptFlow option:selected").attr("selected",false);
		$(".schDept").hide();
		$(".schDept_"+$("#doctorFlow").val()).show();
		if($("#doctorFlow option:selected").attr("userPhone")){
			var userPhone = $("#doctorFlow option:selected").attr("userPhone");
			$("#userPhone").html("&nbsp;&nbsp;联系方式："+userPhone);
		}

	}

	function changeOthers(){
		$("#teacherTd").html($("#schDeptFlow option:selected").attr("teacherName"));
		$("#headTd").html($("#schDeptFlow option:selected").attr("headName"));
		$("[name='schDeptFlow']").val($("#schDeptFlow").val());
		$("[name='schDeptName']").val($("#schDeptFlow option:selected").text());
		$("[name='teacherFlow']").val($("#schDeptFlow option:selected").attr("teacherFlow"));
		$("[name='teacherName']").val($("#schDeptFlow option:selected").attr("teacherName"));
		$("[name='headFlow']").val($("#schDeptFlow  option:selected").attr("headFlow"));
		$("[name='headName']").val($("#schDeptFlow  option:selected").attr("headName"));
	}

	function save(){
		//checkFileRequired();
		if(false == $("#editForm").validationEngine("validate")){
			return false;
		}
		var startDate = $("input[name='startDate1']").val();
		var endDate = $("input[name='endDate2']").val();
		if("" == startDate){
			jboxTip("请选择开始时间！");
			return false;
		}
		if("" == endDate){
			jboxTip("请选择结束时间！");
			return false;
		}
		if(endDate < startDate){
			jboxTip("结束日期不能早于开始日期！");
			return false;
		}
		if($("[name='absenceTypeId'] option:selected").val()=='${absenceTypeEnumYearleave.id}' &&
				endDate.substring(0,4)>'${pdfn:getCurrDateTime('yyyy-MM-dd')}'.substring(0,4)){//只能申请本年度年假
			jboxTip("只能申请当年年假！");
			return false;
		}
		var intervalDay = $("input[name='intervalDayAll']").val();
		if($("#yearDays").text()=='' && $("[name='absenceTypeId'] option:selected").val()=='${absenceTypeEnumYearleave.id}'){
			jboxTip("已超出最大年假天数！");
			return false;
		}
		<%--if("${GlobalConstant.FLAG_Y}" != "${isRegister}" && "${sysCfgMap['res_absence_max_day']}" !="" && intervalDay>parseInt("${sysCfgMap['res_absence_max_day']}")){--%>
			<%--return jboxTip("请假不能超过${sysCfgMap['res_absence_max_day']}天！");--%>
		<%--}--%>
		if($("[name='absenceTypeId'] option:selected").val()=='${absenceTypeEnumYearleave.id}'){//如果有请年假，需要检查是否有周末并给予提示
			jboxPost('<s:url value="/res/doc/getWeekends"/>?startDate='+startDate+"&endDate="+endDate,null,function(resp){
				var title ="";
				if(resp==1){
					 title ="您的当前请假时间包含周末，会被计算入总的年假天数，点击确定保存，或取消重新分条填写！";
				}else{
					 title ="本次请假由<span style='color: blue;'>"+startDate+"</span>至<span style='color: blue;'>"+endDate+"</span>共计<span style='color: red;'>"+intervalDay+"</span>天！确认请假？";
					if('${doctor.doctorTypeId}'=="Graduate"){
					 title="本次请假由<span style='color: blue;'>"+startDate+"</span>至<span style='color: blue;'>"+endDate+"</span>共计<span style='color: red;'>"+intervalDay+"</span>天！提交后无需审核直接生效且不能修改，确认请假？";
					}
				}
				jboxConfirm(title,function(){
					jboxStartLoading();
					var url = "<s:url value='/res/doc/saveDoctorAbsences'/>";
					jboxSubmit($('#editForm'), url, function(resp){
						if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
							window.parent.frames['mainIframe'].location.reload(true);
							setTimeout(function(){
								jboxClose();
							},1000);
						}
					},function(resp){
					});
				});
			},null,false);
		}else{
			<c:if test="${isRegister ne 'Y'}">
			var title ="本次请假由<span style='color: blue;'>"+startDate+"</span>至<span style='color: blue;'>"+endDate+"</span>共计<span style='color: red;'>"+intervalDay+"</span>天！确认请假？";
			if('${doctor.doctorTypeId}'=="Graduate"){
			title="本次请假由<span style='color: blue;'>"+startDate+"</span>至<span style='color: blue;'>"+endDate+"</span>共计<span style='color: red;'>"+intervalDay+"</span>天！提交后无需审核直接生效且不能修改，确认请假？";
			}
			</c:if>
			<c:if test="${isRegister eq 'Y'}">
				var title = "本次缺勤由<span style='color: blue;'>"+startDate+"</span>至<span style='color: blue;'>"+endDate+"</span>共计<span style='color: red;'>"+intervalDay+"</span>天！确认提交？";
			</c:if>
		jboxConfirm(title,function(){
		jboxStartLoading();
			var url = "<s:url value='/res/doc/saveDoctorAbsences'/>";
			jboxSubmit($('#editForm'), url, function (resp) {
				if ("${GlobalConstant.SAVE_SUCCESSED}" == resp) {
					window.parent.frames['mainIframe'].location.reload(true);
					setTimeout(function () {
						jboxClose();
					}, 1000);
				}
			}, function (resp) {
			});
		});
		}
	}
	
	function calculateDay(){
		var startDate = $("input[name='startDate1']").val();
		var endDate = $("input[name='endDate2']").val();
		if("" == startDate || "" == endDate){
			$("input[name='intervalDayAll']").val("");
			return;
		}
		if(endDate < startDate){
			jboxTip("结束日期不能早于开始日期！");
			return;
		}
		var url = "<s:url value='/res/doc/calculateAbsenceDay'/>";
		var data = {
				startDate : startDate,
				endDate : endDate
		};
        var dates=GetDateDiff(startDate,endDate);
		$("input[name='intervalDayAll']").val(dates+1);
		getSchInfo();
		if($("select[name='absenceTypeId'] option:selected").val()=='${absenceTypeEnumYearleave.id}'){
			getYearLeave();
		}
	}
	 function GetDateDiff(startDate,endDate)
	 {
		 var startTime = new Date(Date.parse(startDate.replace(/-/g,   "/"))).getTime();
		 var endTime = new Date(Date.parse(endDate.replace(/-/g,   "/"))).getTime();
		 var dates = Math.abs((startTime - endTime))/(1000*60*60*24);
		 return  dates;
	 }
	function doClose(){
		jboxClose();
	}
	
	function reChoose(obj){
		   $(obj).hide();
		   $("#down").hide();
		   $("#file").show();
	}

	function checkFileType(obj){
		var fileName= $.trim($(obj).val());
		var suffix = fileName.substring(fileName.indexOf(".")+1);
		if(suffix!="jpg"&&suffix!="JPG"&&suffix!="PNG"&&suffix!="png")
		{
			jboxTip("请选择jpg或png格式的文件！");
			$(obj).val("");
			return ;
		}
	}

	function getYearLeave(){
		var url = "<s:url value='/res/doc/getYearLeave'/>?absenceFlow=${doctorAbsence.absenceFlow}&userFLow=${doctorAbsence.doctorFlow}";
		jboxPost(url,null,function(resp){
			var ownDay = 0;
			if($("input[name='intervalDayAll']").val()){
				ownDay=$("input[name='intervalDayAll']").val();
			}
			if(resp==-1){
				jboxTip("暂不能请年假，请联系管理员维护年假起始计算日期");
			}
			if(resp-ownDay>=0){
				$("#yearDays").text(resp-ownDay);
			}else {
				$("#yearDays").text('');
			}
		},null,false);
	}

	function getSchInfo(){
		var url = "<s:url value='/res/doc/searchSchDept'/>";
		var userFlow;
		if($("#doctorFlow option:selected").val()){
			userFlow = $("#doctorFlow option:selected").val();
		}else{
			userFlow = '${doctorAbsence.doctorFlow}';
		}
		var data = {
			startDate : $("#startDate").val(),
			endDate : $("#endDate").val(),
			userFlow :　userFlow
		};
		jboxPost(url, data,
				function(resp){
					if(resp==0){
						jboxTip("无轮转科室不能请假");
						$("#schDepts").text("无轮转科室");
						$("#endDate").val("");
					}
					else if(resp==-3){
						jboxTip("部分日期不在轮转时间内不能请假");
						$("#schDepts").text("请检查请假时间段，需全部在轮转时间段内");
						$("#endDate").val("");
					}else{
						$("#schDepts").text("");
						for(var i=0;i<resp.length;i++){
							var text = $("#schDepts").text();
							$("#schDepts").html(text+=resp[i]['SCH_DEPT_NAME']+"&nbsp;${GlobalConstant.FLAG_Y eq isRegister?'缺勤':'请假'}天数："+resp[i]['intervalDay']+"天&emsp;&emsp;");
							if(""==resp[i]['teacherFlow']){
										jboxTip("系统内该科室暂未维护教学主任信息，请联系继教科管理员！");
										$("#endDate").val("");
										return;
									}
						}
						var dataList = JSON.stringify(resp);
						$("#dataList").val(dataList);
					}
				}, null, false);
	}
	function checkAbsenceType(){
		if($("select[name='absenceTypeId'] option:selected").val()=='${absenceTypeEnumYearleave.id}'){
			$("#mark1").hide();
			$(".mark2").show();
			getYearLeave();
		}else {
			$("#mark1").show();
			$(".mark2").hide();
		}
	}
	function checkFileRequired(){
		if($("select[name='absenceTypeId'] option:selected").val()!='${absenceTypeEnumYearleave.id}' && Number($("input[name='intervalDayAll']").val())>5){
			$("#file").removeClass("validate[required]")
		}else{
			$("#file").removeClass("validate[required]")
		}
	}
	function save2(){
		 if(!$("#editForm").validationEngine("validate")){
			 return;
		 }
		 jboxConfirm("确认销假?",  function(){
			 jboxStartLoading();
			 var url = "<s:url value='/res/doc/repealAbsence'/>?absenceFlow=${doctorAbsence.absenceFlow}&repealAbsenceDate="+$("#repealAbsenceDate").val()+"&repealAbsence=S";
			 jboxPost(url, null,
					 function(resp){
						 if("${GlobalConstant.OPRE_SUCCESSED}" == resp){
							 window.parent.frames['mainIframe'].location.reload(true);
						 }
					 }, null, true);
		 });
	 }
	 function save3(){
		 if(!$("#editForm").validationEngine("validate")){
			 return;
		 }
		 jboxConfirm("确认审核销假?",  function(){
			 jboxStartLoading();
			 var url = "<s:url value='/res/doc/repealAbsence'/>?absenceFlow=${doctorAbsence.absenceFlow}&repealAbsenceDay="
					 +$("#repealAbsenceDate2").val()+"&repealAbsenceInfo="+$("#repealAbsenceInfo").val()+"&repealAbsence=Y";
			 jboxPost(url, null,
					 function(resp){
						 if("${GlobalConstant.OPRE_SUCCESSED}" == resp){
							 window.parent.frames['mainIframe'].location.reload(true);
						 }
					 }, null, true);
		 });
	 }
</script>
</head>
<body>
	<div class="mainright" align="center">
	<div class="content">
	<span class="title1 clearfix" >
		<form id="editForm" style="position: relative;" enctype="multipart/form-data">
		<input type="hidden" name="resRoleScope" value="${resRoleScope}"/>
		<input type="hidden" name="absenceFlow" value="${doctorAbsence.absenceFlow}"/>
		<input type="hidden" name="intervalDayAll"/>
		<input type="hidden" name="doctorFlow" value="${doctorAbsence.doctorFlow}"/>
		<input type="hidden" name="doctorName" value="${doctorAbsence.doctorName}"/>
		<input type="hidden" id="dataList" name="dataList"/>
		<input type="hidden" name="isRegister" value="${empty doctorAbsence.isRegister?isRegister:doctorAbsence.isRegister}"/>
		<table class="basic" style="width: 100%;">
			<colgroup>
				<col width="17%"/>
				<col width="83%"/>
			</colgroup>
			<tbody>
			<c:if test="${GlobalConstant.FLAG_Y eq isRegister}">
				<tr>
					<th>
						<font color="red" >*</font>年&#12288;&#12288;级：</th>
					<td>
						<select name="sessionNumber" onchange="filterDoctor();" class="validate[required]">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
								<option value="${dict.dictName}" ${dict.dictName eq doctorAbsence.sessionNumber?'selected="selected"':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						<font color="red" >*</font>培训专业：</th>
					<td>
						<select name="trainingSpeId" onchange="filterDoctor();" class="validate[required]">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
								<option value="${dict.dictId}" ${dict.dictId eq doctorAbsence.trainingSpeId?'selected="selected"':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						<font color="red" >*</font>姓&#12288;&#12288;名：</th>
					<td>
						<select id="doctorFlow" class="validate[required]" onchange="chooseDoctor();">
							<option/>
							<c:forEach items="${doctorList}" var="doctor">
								 <option class="name _${doctor.sessionNumber} _${doctor.trainingSpeId}"  doctorName="${doctor.doctorName}"
										 userPhone="${doctor.sysUser.userPhone}" value="${doctor.doctorFlow}" <c:if test="${doctor.doctorFlow eq doctorAbsence.doctorFlow}">selected</c:if>>${doctor.doctorName}</option>
							</c:forEach>
						</select>
						<span id="userPhone"></span>
					</td>
				</tr>
				</c:if>
				<tr>
					<th><font color="red" >*</font>${GlobalConstant.FLAG_Y eq isRegister?'缺勤':'请假'}类型：</th>
					<td>
						<select name="absenceTypeId" class="validate[required]" onchange="checkAbsenceType();">
							<option value="">请选择</option>
							<c:if test="${isRegister ne 'Y'}">
							<c:forEach var="absenceType" items="${absenceTypeEnumList}">
								<c:if test="${absenceType.id != absenceTypeEnumAbsenteeism.id}">
								<option value="${absenceType.id}" <c:if test="${doctorAbsence.absenceTypeId==absenceType.id}">selected="selected"</c:if> >${absenceType.name}</option>
								</c:if>
							</c:forEach>
							</c:if>
							<c:if test="${isRegister eq 'Y'}">
								<option value="${absenceTypeEnumAbsenteeism.id}" selected="selected">${absenceTypeEnumAbsenteeism.name}</option>
							</c:if>
						</select>
						<c:if test="${resRoleScope ne 'repealAbsenceAudit' && isRegister ne 'Y'}">
						<span id="mark1">
							&#12288;备注：除年假外，请假超过5天需上传原单位同意证明材料。
						</span>
						<span class="mark2">
							&#12288;剩余年假：<span id="yearDays"></span>天&#12288;备注：第一年不可以请年假。第二年开始每年有5天年假。
						</span>
						</c:if>
						<c:if test="${resRoleScope eq 'repealAbsenceAudit'}">
							<font color="red">*</font><span style="font-weight: bold"> 请假人：${doctorAbsence.doctorName}</span>
						</c:if>
					</td>
				</tr>
				<tr>
					<th><font color="red" >*</font>${GlobalConstant.FLAG_Y eq isRegister?'缺勤':'请假'}时间：</th>
					<td>
						<input type="text" id="startDate" name="startDate1" value="${doctorAbsence.startDate}" onblur="calculateDay();"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="validate[required]" style="margin-right: 0px;"
							   onchange="" readonly="readonly"/>
						~ <input type="text" id="endDate" name="endDate2" value="${doctorAbsence.endDate}" onblur="calculateDay();"
								 onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="validate[required]" style="margin-right: 0px;"
								 onchange="" readonly="readonly"/>
						<c:if test="${resRoleScope eq 'repealAbsence'||resRoleScope eq 'repealAbsenceAudit'}">
						<font color="red">*</font><span style="font-weight: bold"> 销假时间：</span>
						<input type="text" value="${doctorAbsence.repealAbsenceDate}" id="repealAbsenceDate"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'${doctorAbsence.startDate}',maxDate:'${doctorAbsence.endDate}'})" class="validate[required]" style="margin-right: 0px;" readonly="readonly"/>
						</c:if>
					</td>
				</tr>
				<tr>
					<th><font color="red" >*</font>${GlobalConstant.FLAG_Y eq isRegister?'缺勤':'请假'}事由：</th>
					<td colspan="3" style="text-align:left;padding: 5px;">
					     <textarea placeholder="${GlobalConstant.FLAG_Y eq isRegister?'缺勤':'请假'}事由（限250字）。"  class="validate[required] validate[maxSize[250]] xltxtarea" name="absenceReson">${doctorAbsence.absenceReson}</textarea>
			     	</td>
				</tr>
				<tr>
					<th>
						轮转科室：
					</th>
					<td colspan="3">
						<span id="schDepts"></span>
					</td>
				</tr>
				<c:if test="${GlobalConstant.FLAG_Y ne isRegister}">
				<tr>
					<th>${GlobalConstant.FLAG_Y eq isRegister?'缺勤':'请假'}材料：</th>
					<td colspan="3">
						<c:choose>
							<c:when test="${not empty file}">
								<a id="down" href='<s:url value="/res/doc/fileDown?fileFlow=${file.fileFlow}"/>'>${file.fileName}</a>
								<input type="file" id="file" class="" name="file" onchange="checkFileType(this);" style="display: none;" accept="image/jpg,image/png"/>
								<span style="float: right; padding-right: 10px;">
								<a id="reCheck" href="javascript:void(0);" onclick="reChoose(this);" style="color: blue;">[重新选择文件]</a>
								</span>
						  	</c:when>
							<c:otherwise>
								<input type="file" class="" id="file" name="file" onchange="checkFileType(this);" accept="image/jpg,image/png"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				</c:if>
			<c:if test="${resRoleScope eq 'repealAbsenceAudit'}">
				<tr>
					<th><font color="red" >&nbsp;</font>实际销假时间</th>
					<td colspan="3">
						<input type="text" id="repealAbsenceDate2" value="${doctorAbsence.repealAbsenceDay}"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'${doctorAbsence.startDate}',maxDate:'${doctorAbsence.endDate}'})" class="validate[required]" style="margin-right: 0px;" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<th>销假审核意见</th>
					<td colspan="3" style="text-align: center;">
						<textarea id="repealAbsenceInfo" style="width: 99.9%;height: 50px;"></textarea>
					</td>
				</tr>
			</c:if>
			</tbody>
		</table>
		</form>
		<p style="text-align: center;">
			<c:if test="${resRoleScope eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR || isRegister eq 'Y'}">
	      		<input type="button" onclick="save();" class="search" value="保&#12288;存"/>
			</c:if>
			<c:if test="${resRoleScope eq 'repealAbsence'}">
				<input type="button" onclick="save2();" class="search" value="保&#12288;存"/>
			</c:if>
			<c:if test="${resRoleScope eq 'repealAbsenceAudit'}">
				<input type="button" onclick="save3();" class="search" value="保&#12288;存"/>
			</c:if>
	      	<input type="button" onclick="doClose();" class="search" value="关&#12288;闭"/>
	    </p>
	</div>
	</div>
	</div>
</body>
</html>