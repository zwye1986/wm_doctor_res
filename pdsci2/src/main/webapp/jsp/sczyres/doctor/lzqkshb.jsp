<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<style>
.base_info th,.base_info td{height:45px;}
</style>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
$(document).ready(function(){
	$('.datepicker').datepicker();
	changeDiv('${doctor.trainingSpeId}');

	var id ='';//计算轮转总时间
	if($("#trainingSpeId option:selected").val()=='1'){
		id = 'zyDiv';
	}else if($("#trainingSpeId option:selected").val()=='2'){
		id = 'zyqkDiv';
	} else{
		id = 'zyzlqkDiv';
	}
	var sum = 0;
	$("#"+id+" tr:gt(0):not(.gsxx)").each(function(){
		if($(this).find('.startDate').val() &&  $(this).find('.endDate').val()){
			var startDate = $(this).find('.startDate').val();
			var endDate = $(this).find('.endDate').val();
			var single =  datedifference(startDate, endDate)+1;
			sum+=single;
		}
	});
	var content = (sum/30).toFixed(2);
	var content2 = (sum/7).toFixed(2);
	if(id == 'zyDiv' || id == 'zyqkDiv'){
		$("#"+id+" .sum").text(content+"月");
	}else{
		$("#"+id+" .sum").text(content2+"周");
	}


	if('${param.editFlag}'=='N'){
		$("input:not([type='button'])").attr('disabled','disabled');
		$("select").attr('disabled','disabled');
		$("#zyqkDiv a").hide();
		$("#zyDiv a").hide();
		$("#zyzlqkDiv a").hide();
		$(".saveButton").hide();
	}
});

function save(){
	if(!$("#doctorForm").validationEngine("validate")){
		return;
	}
	var trainingStartDate = $("[name='trainingStartDate']").val();
	var trainingEndDate = $("[name='trainingEndDate']").val();
	if(trainingEndDate<=trainingStartDate){
		jboxTip("培训结束时间必须大于培训开始时间");
		$("[name='trainingEndDate']").focus();
		return;
	}
	$("#orgName").val($("#orgFlow option:selected").text());
	var sexName = $("[name=sexId]:checked").attr("sexName");
	$("#sexName").val(sexName);
	$("#doctorTypeName").val($("#doctorTypeId option:selected").text());
	$("#educationName").val($("#educationId option:selected").text());
	$("#degreeName").val($("#degreeId option:selected").text());
	$("#trainingSpeName").val($("#trainingSpeId option:selected").text());
	var data = [];
	var id ='';
	if($("#trainingSpeId option:selected").val()=='1'){
		id = 'zyDiv';
	}else if($("#trainingSpeId option:selected").val()=='2'){
		id = 'zyqkDiv';
	} else{
		id = 'zyzlqkDiv';
	}
	$("#"+id+" tr:gt(0)").each(function(){
		if($(this).find('.deptName')){
			var deptName = $(this).find('.deptName').val();
			var startDate = $(this).find('.startDate').val();
			var endDate = $(this).find('.endDate').val();
			var recordFlow = '';
			if($(this).find('.recordFlow').val()){
				recordFlow = $(this).find('.recordFlow').val();
			}
			var speId = $(this).attr('class');
			var single = {deptName:deptName,startDate:startDate,endDate:endDate,recordFlow:recordFlow,speId:speId};
			data.push(single);
		}
	});
	var jsonData = {schInfo:data,baseInfo:$("#doctorForm").serializeJson(),graduationYear:'${graduationYear}'}
	jboxPost("<s:url value='/sczyres/doctor/saveSchInfo'/>" ,{jsonData:JSON.stringify(jsonData)} , function(resp){
		if(resp=="1"){
			jboxTip("保存成功");
			jboxLoad("content2" , "<s:url value='/sczyres/doctor/lzqkshb'/>" , true);
		}else{
			jboxTip(resp);
		}
	} , null , false);
}

function addTd(obj){
	var rowspan = parseInt($(obj).closest('td').attr('rowspan'))+1;
	$(obj).closest('td').attr('rowspan',rowspan);
	var c = $(obj).closest('td').attr('class');
	var cloneTr = $("#template tr:eq(0)").clone();
	cloneTr.addClass(c);
	$(obj).closest('table').find("."+c+":not(td)").last().after(cloneTr);
	$('.datepicker').datepicker();
}

function delTd(obj){
	jboxConfirm("确认删除该科室记录？",function(){
		var recordFlow = $(obj).closest("tr").find(".recordFlow").val();
		jboxPost("<s:url value='/sczyres/doctor/delSchInfo'/>?recordFlow="+recordFlow,null,function(resp){
			if(resp==1){
				jboxTip("操作成功");
			}
		},null,false);
		var c = $(obj).closest('tr').attr('class');
		var rowspan = parseInt($(obj).closest('table').find('.'+c+' td').eq(0).attr('rowspan'))-1;
		$(obj).closest('table').find('.'+c+' td').eq(0).attr('rowspan',rowspan);
		$(obj).closest('tr').remove();
	})
}
function changeDiv(trainingSpeId){
	if(trainingSpeId=='1'){
		$("#zyDiv").show();
		$("#zyqkDiv").hide();
		$("#zyzlqkDiv").hide();
		$(".zigezheng").show();
		$(".zhulizigezheng").hide();
	}
	if(trainingSpeId=='2'){
		$("#zyDiv").hide();
		$("#zyqkDiv").show();
		$("#zyzlqkDiv").hide();
		$(".zigezheng").show();
		$(".zhulizigezheng").hide();
	}
	if(trainingSpeId=='3'){
		$("#zyDiv").hide();
		$("#zyqkDiv").hide();
		$("#zyzlqkDiv").show();
		$(".zigezheng").hide();
		$(".zhulizigezheng").show();
	}
}

//两个时间相差天数 兼容firefox chrome
function datedifference(sDate1, sDate2) {    //sDate1和sDate2是2006-12-18格式
	var dateSpan,
			tempDate,
			iDays;
	sDate1 = Date.parse(sDate1);
	sDate2 = Date.parse(sDate2);
	dateSpan = sDate2 - sDate1;
	dateSpan = Math.abs(dateSpan);
	iDays = Math.floor(dateSpan / (24 * 3600 * 1000));
	return iDays
};
function print(){
	var url ="<s:url value='/sczyres/doctor/lzqkshb?printFlag=Y'/>";
	window.open(url);
}
</script>
<div id="singupContent">
<div id='docTypeForm'>
    <form id='doctorForm' style="position:relative;">
    <p id="errorMsg" style="color: red;"></p>
    <input type="hidden" name="userFlow" value="${user.userFlow}"/>
    <input type="hidden" name="doctorFlow" value="${doctor.doctorFlow}"/>
    <div class="main_bd">
       <div class="div_table">
          <div class="score_frame">
            <h1>培训基地轮转信息表</h1>
			  <div class="div_table">
                <table border="0" cellpadding="0" cellspacing="0" class="base_info">
            <colgroup>
              <col width="16%"/>
              <col width="16%"/>
              <col width="16%"/>
              <col width="15%"/>
              <col width="16%"/>
              <col width="16%"/>
            </colgroup>
	           <tbody>
			   <tr>
				   <th><font color="red">*</font>所学专业：</th>
				   <td>
					   <select name="trainingSpeId" id="trainingSpeId" class="select validate[required]"
							   style="width: 160px;margin-left: 5px;" onchange="changeDiv(this.value);">
						   <option value="">请选择</option>
						   <option value="1" ${doctor.trainingSpeId eq '1'?'selected':''}>中医</option>
						   <option value="2" ${doctor.trainingSpeId eq '2'?'selected':''}>中医全科</option>
						   <option value="3" ${doctor.trainingSpeId eq '3'?'selected':''}>中医助理全科</option>
					   </select>
					   <input type="hidden" name="trainingSpeName" id="trainingSpeName">
				   </td>
				   <th><font color="red">*</font>培训基地：</th>
				   <td colspan="3">
					   <select class="select validate[required]" id="orgFlow" name="orgFlow" style="width:300px;margin-left: 5px;">
						   <option value="">请选择</option>
						   <c:forEach items="${hospitals}" var="hosptial">
							   <option value='${hosptial.orgFlow}' <c:if test='${user.orgFlow eq hosptial.orgFlow}'>selected="selected"</c:if>>${hosptial.orgName}</option>
						   </c:forEach>
					   </select>
					   <div style="color:red">培训基地若为协同基地，请选择对应的协同基地</div>
					   <input type="hidden" name="orgName" id="orgName">
				   </td>
			   </tr>
			   <tr>
				   <th><font color="red">*</font>姓名：</th>
				   <td><input type='text' class='input validate[required]' id="userName" name="userName" value="${user.userName}"/></td>
				   <th><font color="red">*</font>性别：</th>
				   <td>&nbsp;
					   <label>
					   <input type="radio" class='validate[required]' style="width:auto;" name="sexId" sexName="${userSexEnumMan.name}" value='${userSexEnumMan.id}' ${user.sexId eq userSexEnumMan.id?'checked':''}/>${userSexEnumMan.name}
						</label>
					   &nbsp;
					   <label>
						   <input type="radio" class='validate[required]' style="width:auto;" name="sexId" sexName="${userSexEnumWoman.name}" value='${userSexEnumWoman.id}' ${user.sexId eq userSexEnumWoman.id?'checked':''}/>${userSexEnumWoman.name}
					   </label>
					   <input type="hidden" name="sexName" id="sexName">
				   </td>
				   <th><font color="red">*</font>身份证号码：</th>
				   <td>
					   <input type="text" name="idNo" value="${user.idNo}" class="input validate[required]"/>
				   </td>
			   </tr>
				<tr>
					<th><font color="red">*</font>学历：</th>
					<td>
						<select name="educationId" id="educationId" class="select validate[required]" style="width: 160px;margin-left: 5px;">
							<option></option>
							<c:forEach items="${dictTypeEnumUserEducationList}" var="dict">
								<option value="${dict.dictId}" ${user.educationId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
						<input type="hidden" name="educationName" id="educationName">
					</td>
					<th>学位：</th>
					<td>
						<select name="degreeId" id="degreeId" class="select" style="width: 120px;margin-left: 5px;">
							<option></option>
							<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
								<option value="${dict.dictId}" ${user.degreeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
						<input type="hidden" name="degreeName" id="degreeName">
					</td>
					<th><font color="red">*</font>学员身份：</th>
					<td>
						<select name="doctorTypeId" id="doctorTypeId" class="select validate[required]"
								style="width: 160px;margin-left: 5px;">
							<option value="">请选择</option>
							<c:forEach items="${sczyRecDocTypeEnumList}" var="doctorType">
								<option value="${doctorType.id}" ${doctorType.id eq doctor.doctorTypeId?'selected':''}>${doctorType.name}</option>
							</c:forEach>
						</select>
						<input type="hidden" name="doctorTypeName" id="doctorTypeName"/>
					</td>
				</tr>
				<tr>
					<th class="zigezheng"><font color="red">*</font>医师资格证书&#12288;<br/>编号：</th>
					<td class="zigezheng">
						<input type="text" class="input validate[required]" name="doctorLicenseNo" value="${doctor.doctorLicenseNo}">
					</td>
					<th class="zhulizigezheng"><font color="red">*</font>助理医师资格&#12288;<br/>证书编号：</th>
					<td class="zhulizigezheng">
						<input type="text" class="input validate[required]" name="assistantQualificationCertificateCode" value="${extInfo.assistantQualificationCertificateCode}">
					</td>
					<th><font color="red">*</font>培训起止时间：</th>
					<td colspan="3">
						<input type="text" class="input validate[required] datepicker" style="width: 120px;" name="trainingStartDate" value="${extInfo.trainingStartDate}"/>
						~
						<input type="text" class="input validate[required] datepicker" style="width: 120px;" name="trainingEndDate" value="${extInfo.trainingEndDate}"/>
					</td>
				</tr>
	           </tbody>
              </table>
              </div>

            <div class="div_table" ID="zyqkDiv" style="display: none;">
			<h4>轮转情况</h4>
	        <table border="0" cellpadding="0" cellspacing="0" class="base_info">
	            <colgroup>
					<col width="30%"/>
					<col width="25%"/>
					<col width="35%"/>
	            </colgroup>
				<tbody>
					<tr>
						<th style="text-align: center">学科名称</th>
						<th style="text-align: center">科室</th>
						<th style="text-align: center">时间</th>
					</tr>
					<c:if test="${empty resultMap['llxx']}">
						<tr class="llxx">
							<td rowspan="1" style="text-align: center" class="llxx">理论学习(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['llxx']}">
						<c:forEach items="${resultMap['llxx']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="llxx">
									<td rowspan="${resultMap['llxx'].size()}" style="text-align: center" class="llxx">理论学习(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									<td style="text-align: center">
										&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="llxx">
								<td style="text-align: center">
									<a onclick="delTd(this)">删除</a>
									<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
								</td>
								<td style="text-align: center">
									<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
									~
									<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
								</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${empty resultMap['zynk']}">
						<tr class="zynk">
							<td rowspan="1" style="text-align: center" class="zynk">中医内科(10个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['zynk']}">
						<c:forEach items="${resultMap['zynk']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="zynk">
									<td rowspan="${resultMap['zynk'].size()}" style="text-align: center" class="zynk">中医内科(10个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									<td style="text-align: center">
										&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="zynk">
								<td style="text-align: center">
									<a onclick="delTd(this)">删除</a>
									<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
								</td>
								<td style="text-align: center">
									<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
									~
									<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
								</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>

					<c:if test="${empty resultMap['zywk']}">
						<tr class="zywk">
							<td rowspan="1" style="text-align: center" class="zywk">中医外科(2个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['zywk']}">
						<c:forEach items="${resultMap['zywk']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="zywk">
									<td rowspan="${resultMap['zywk'].size()}" style="text-align: center" class="zywk">中医外科(2个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									<td style="text-align: center">
										&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="zywk">
									<td style="text-align: center">
										<a onclick="delTd(this)">删除</a>
										<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['zyfk']}">
						<tr class="zyfk">
							<td rowspan="1" style="text-align: center" class="zyfk">中医妇科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['zyfk']}">
						<c:forEach items="${resultMap['zyfk']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="zyfk">
									<td rowspan="${resultMap['zyfk'].size()}" style="text-align: center" class="zyfk">中医妇科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									<td style="text-align: center">
										&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="zyfk">
									<td style="text-align: center">
										<a onclick="delTd(this)">删除</a>
										<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['zyek']}">
						<tr class="zyek">
							<td rowspan="1" style="text-align: center" class="zyek">中医儿科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['zyek']}">
						<c:forEach items="${resultMap['zyek']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="zyek">
									<td rowspan="${resultMap['zyek'].size()}" style="text-align: center" class="zyek">中医儿科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									<td style="text-align: center">
										&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="zyek">
									<td style="text-align: center">
										<a onclick="delTd(this)">删除</a>
										<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['zjk']}">
						<tr class="zjk">
							<td rowspan="1" style="text-align: center" class="zjk">针灸科(2个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['zjk']}">
						<c:forEach items="${resultMap['zjk']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="zjk">
									<td rowspan="${resultMap['zjk'].size()}" style="text-align: center" class="zjk">针灸科(2个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									<td style="text-align: center">
										&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="zjk">
									<td style="text-align: center">
										<a onclick="delTd(this)">删除</a>
										<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['tnk']}">
						<tr class="tnk">
							<td rowspan="1" style="text-align: center" class="tnk">推拿科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['tnk']}">
						<c:forEach items="${resultMap['tnk']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="tnk">
									<td rowspan="${resultMap['tnk'].size()}" style="text-align: center" class="tnk">推拿科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									<td style="text-align: center">
										&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="tnk">
									<td style="text-align: center">
										<a onclick="delTd(this)">删除</a>
										<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['zykfk']}">
						<tr class="zykfk">
							<td rowspan="1" style="text-align: center" class="zykfk">中医康复科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['zykfk']}">
						<c:forEach items="${resultMap['zykfk']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="zykfk">
									<td rowspan="${resultMap['zykfk'].size()}" style="text-align: center" class="zykfk">中医康复科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									<td style="text-align: center">
										&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="zykfk">
									<td style="text-align: center">
										<a onclick="delTd(this)">删除</a>
										<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['zygsk']}">
						<tr class="zygsk">
							<td rowspan="1" style="text-align: center" class="zygsk">中医骨伤科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['zygsk']}">
						<c:forEach items="${resultMap['zygsk']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="zygsk">
									<td rowspan="${resultMap['zygsk'].size()}" style="text-align: center" class="zygsk">中医骨伤科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									<td style="text-align: center">
										&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="zygsk">
									<td style="text-align: center">
										<a onclick="delTd(this)">删除</a>
										<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['zyebhk']}">
						<tr class="zyebhk">
							<td rowspan="1" style="text-align: center" class="zyebhk">中医耳鼻喉科(与眼科共1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['zyebhk']}">
						<c:forEach items="${resultMap['zyebhk']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="zyebhk">
									<td rowspan="${resultMap['zyebhk'].size()}" style="text-align: center" class="zyebhk">中医耳鼻喉科(与眼科共1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									<td style="text-align: center">
										&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="zyebhk">
									<td style="text-align: center">
										<a onclick="delTd(this)">删除</a>
										<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['zyyk']}">
						<tr class="zyyk">
							<td rowspan="1" style="text-align: center" class="zyyk">中医眼科(与耳鼻喉科共1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['zyyk']}">
						<c:forEach items="${resultMap['zyyk']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="zyyk">
									<td rowspan="${resultMap['zyyk'].size()}" style="text-align: center" class="zyyk">中医眼科(与耳鼻喉科共1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									<td style="text-align: center">
										&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="zyyk">
									<td style="text-align: center">
										<a onclick="delTd(this)">删除</a>
										<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['zk']}">
						<tr class="zk">
							<td rowspan="1" style="text-align: center" class="zk">急诊科（包括院前急救）(3个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['zk']}">
						<c:forEach items="${resultMap['zk']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="zk">
									<td rowspan="${resultMap['zk'].size()}" style="text-align: center" class="zk">急诊科（包括院前急救）(3个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									<td style="text-align: center">
										&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="zk">
									<td style="text-align: center">
										<a onclick="delTd(this)">删除</a>
										<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['pwk']}">
						<tr class="pwk">
							<td rowspan="1" style="text-align: center" class="pwk">普外科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['pwk']}">
						<c:forEach items="${resultMap['pwk']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="pwk">
									<td rowspan="${resultMap['pwk'].size()}" style="text-align: center" class="pwk">普外科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									<td style="text-align: center">
										&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="pwk">
									<td style="text-align: center">
										<a onclick="delTd(this)">删除</a>
										<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['fzks']}">
						<tr class="fzks">
							<td rowspan="1" style="text-align: center" class="fzks">辅助科室(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['fzks']}">
						<c:forEach items="${resultMap['fzks']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="fzks">
									<td rowspan="${resultMap['fzks'].size()}" style="text-align: center" class="fzks">辅助科室(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									<td style="text-align: center">
										&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="fzks">
									<td style="text-align: center">
										<a onclick="delTd(this)">删除</a>
										<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['xx']}">
						<tr class="xx">
							<td rowspan="1" style="text-align: center" class="xx">选修(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['xx']}">
						<c:forEach items="${resultMap['xx']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="xx">
									<td rowspan="${resultMap['xx'].size()}" style="text-align: center" class="xx">选修(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									<td style="text-align: center">
										&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="xx">
									<td style="text-align: center">
										<a onclick="delTd(this)">删除</a>
										<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['gsxx']}">
						<tr class="gsxx">
							<td rowspan="1" style="text-align: center" class="gsxx">跟师学习(半天/周)<br/>填写时间为规培时间
								<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['gsxx']}">
						<c:forEach items="${resultMap['gsxx']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="gsxx">
									<td rowspan="${resultMap['gsxx'].size()}" style="text-align: center" class="gsxx">跟师学习(半天/周)<br/>填写时间为规培时间
										<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									<td style="text-align: center">
										&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="gsxx">
									<td style="text-align: center">
										<a onclick="delTd(this)">删除</a>
										<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['jcsj']}">
						<tr class="jcsj">
							<td rowspan="1" style="text-align: center" class="jcsj">基层实践(6个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['jcsj']}">
						<c:forEach items="${resultMap['jcsj']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="jcsj">
									<td rowspan="${resultMap['jcsj'].size()}" style="text-align: center" class="jcsj">基层实践(6个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									<td style="text-align: center">
										&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="jcsj">
									<td style="text-align: center">
										<a onclick="delTd(this)">删除</a>
										<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>

					<c:if test="${empty resultMap['jdks']}">
						<tr class="jdks">
							<td rowspan="1" style="text-align: center" class="jdks">机动科室<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							<td style="text-align: center">
								&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								~
								<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['jdks']}">
						<c:forEach items="${resultMap['jdks']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="jdks">
									<td rowspan="${resultMap['jdks'].size()}" style="text-align: center" class="jdks">机动科室<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									<td style="text-align: center">
										&#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="jdks">
									<td style="text-align: center">
										<a onclick="delTd(this)">删除</a>
										<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										~
										<input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>

					<tr>
						<td style="text-align: center">合计(33个月)</td>
						<td class="sum" style="text-align: center" colspan="2"></td>
					</tr>
					<tr>
						<td colspan="3">
							对照《住院医师规范化培训细则》，本人培训内容完成情况如上，申请参加
							<input class="input validate[required]" id="graduationYear" value="${graduationYear}" readonly="readonly" style="width: 120px;">
							年度结业考试资格审核。
						</td>
					</tr>
			    </tbody>
		       </table>
			</div>


			  <div class="div_table" ID="zyzlqkDiv" style="display: none">
				  <h4>轮转情况</h4>
				  <table border="0" cellpadding="0" cellspacing="0" class="base_info">
					  <colgroup>
						  <col width="30%"/>
						  <col width="25%"/>
						  <col width="35%"/>
					  </colgroup>
					  <tbody>
					  <tr>
						  <th style="text-align: center">学科名称</th>
						  <th style="text-align: center">科室</th>
						  <th style="text-align: center">时间</th>
					  </tr>


					  <c:if test="${empty resultMap['zynk']}">
						  <tr class="zynk">
							  <td rowspan="1" style="text-align: center" class="zynk">中医内科（心血管8周，呼吸（肺病）6周，
								  消化（脾胃病）4周，内分泌代谢4周，泌尿（肾病）4周，血液或肿瘤4周，风湿4周，神经4周）（38周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zynk']}">
						  <c:forEach items="${resultMap['zynk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zynk">
									  <td rowspan="${resultMap['zynk'].size()}" style="text-align: center" class="zynk">中医内科（心血管8周，呼吸（肺病）6周，
										  消化（脾胃病）4周，内分泌代谢4周，泌尿（肾病）4周，血液或肿瘤4周，风湿4周，神经4周）（38周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zynk">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>

					  <c:if test="${empty resultMap['zywk']}">
						  <tr class="zywk">
							  <td rowspan="1" style="text-align: center" class="zywk">急诊科（含院前急救）（8周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zywk']}">
						  <c:forEach items="${resultMap['zywk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zywk">
									  <td rowspan="${resultMap['zywk'].size()}" style="text-align: center" class="zywk">急诊科（含院前急救）（8周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zywk">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zyfk']}">
						  <tr class="zyfk">
							  <td rowspan="1" style="text-align: center" class="zyfk">中医外科（6周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zyfk']}">
						  <c:forEach items="${resultMap['zyfk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zyfk">
									  <td rowspan="${resultMap['zyfk'].size()}" style="text-align: center" class="zyfk">中医外科（6周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zyfk">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zyek']}">
						  <tr class="zyek">
							  <td rowspan="1" style="text-align: center" class="zyek">中医妇科（4周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zyek']}">
						  <c:forEach items="${resultMap['zyek']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zyek">
									  <td rowspan="${resultMap['zyek'].size()}" style="text-align: center" class="zyek">中医妇科（4周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zyek">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zjk']}">
						  <tr class="zjk">
							  <td rowspan="1" style="text-align: center" class="zjk">中医儿科（4周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zjk']}">
						  <c:forEach items="${resultMap['zjk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zjk">
									  <td rowspan="${resultMap['zjk'].size()}" style="text-align: center" class="zjk">中医儿科（4周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zjk">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['tnk']}">
						  <tr class="tnk">
							  <td rowspan="1" style="text-align: center" class="tnk">针灸科<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['tnk']}">
						  <c:forEach items="${resultMap['tnk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="tnk">
									  <td rowspan="${resultMap['tnk'].size()}" style="text-align: center" class="tnk">针灸科<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="tnk">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zykfk']}">
						  <tr class="zykfk">
							  <td rowspan="1" style="text-align: center" class="zykfk">推拿科（10周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zykfk']}">
						  <c:forEach items="${resultMap['zykfk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zykfk">
									  <td rowspan="${resultMap['zykfk'].size()}" style="text-align: center" class="zykfk">推拿科（10周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zykfk">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zygsk']}">
						  <tr class="zygsk">
							  <td rowspan="1" style="text-align: center" class="zygsk">中医康复科<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zygsk']}">
						  <c:forEach items="${resultMap['zygsk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zygsk">
									  <td rowspan="${resultMap['zygsk'].size()}" style="text-align: center" class="zygsk">中医康复科<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zygsk">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zyebhk']}">
						  <tr class="zyebhk">
							  <td rowspan="1" style="text-align: center" class="zyebhk">中医骨伤科（4周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zyebhk']}">
						  <c:forEach items="${resultMap['zyebhk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zyebhk">
									  <td rowspan="${resultMap['zyebhk'].size()}" style="text-align: center" class="zyebhk">中医骨伤科（4周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zyebhk">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zyyk']}">
						  <tr class="zyyk">
							  <td rowspan="1" style="text-align: center" class="zyyk">中医眼科(2周)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zyyk']}">
						  <c:forEach items="${resultMap['zyyk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zyyk">
									  <td rowspan="${resultMap['zyyk'].size()}" style="text-align: center" class="zyyk">中医眼科(2周)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zyyk">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['fzks']}">
						  <tr class="fzks">
							  <td rowspan="1" style="text-align: center" class="fzks">中医耳鼻咽喉科（2周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['fzks']}">
						  <c:forEach items="${resultMap['fzks']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="fzks">
									  <td rowspan="${resultMap['fzks'].size()}" style="text-align: center" class="fzks">中医耳鼻咽喉科（2周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="fzks">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['lchl2']}">
						  <tr class="lchl2">
							  <td rowspan="1" style="text-align: center" class="lchl2">临床护理（2周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['lchl2']}">
						  <c:forEach items="${resultMap['lchl2']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="lchl2">
									  <td rowspan="${resultMap['lchl2'].size()}" style="text-align: center" class="lchl2">临床护理（2周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="lchl2">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['xgzk']}">
						  <tr class="xgzk">
							  <td rowspan="1" style="text-align: center" class="xgzk">辅助科室（2周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['xgzk']}">
						  <c:forEach items="${resultMap['xgzk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="xgzk">
									  <td rowspan="${resultMap['xgzk'].size()}" style="text-align: center" class="xgzk">辅助科室（2周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="xgzk">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>

					  <c:if test="${empty resultMap['gsxx']}">
						  <tr class="gsxx">
							  <td rowspan="1" style="text-align: center" class="gsxx">跟师学习<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['gsxx']}">
						  <c:forEach items="${resultMap['gsxx']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="gsxx">
									  <td rowspan="${resultMap['gsxx'].size()}" style="text-align: center" class="gsxx">跟师学习<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="gsxx">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>

					  <c:if test="${empty resultMap['jcsj']}">
						  <tr class="jcsj">
							  <td rowspan="1" style="text-align: center" class="jcsj">基层实践（16周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['jcsj']}">
						  <c:forEach items="${resultMap['jcsj']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="jcsj">
									  <td rowspan="${resultMap['jcsj'].size()}" style="text-align: center" class="jcsj">基层实践（16周）<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="jcsj">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>

					  <tr>
						  <td style="text-align: center;">合计(98周)</td>
						  <td class="sum" style="text-align: center" colspan="2"></td>
					  </tr>
					  <tr>
						  <td colspan="3">
							  对照《住院医师规范化培训细则》，本人培训内容完成情况如上，申请参加
							  <input class="input validate[required]" id="graduationYear" value="${graduationYear}" readonly="readonly" style="width: 120px;">
							  年度结业考试资格审核。
						  </td>
					  </tr>
					  </tbody>
				  </table>
			  </div>

			  <div class="div_table" ID="zyDiv" style="display: none">
				  <h4>轮转情况</h4>
				  <table border="0" cellpadding="0" cellspacing="0" class="base_info">
					  <colgroup>
						  <col width="30%"/>
						  <col width="25%"/>
						  <col width="35%"/>
					  </colgroup>
					  <tbody>
					  <tr>
						  <th style="text-align: center">学科名称</th>
						  <th style="text-align: center">科室</th>
						  <th style="text-align: center">时间</th>
					  </tr>


					  <c:if test="${empty resultMap['zynk']}">
						  <tr class="zynk">
							  <td rowspan="1" style="text-align: center" class="zynk">中医内科(12个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zynk']}">
						  <c:forEach items="${resultMap['zynk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zynk">
									  <td rowspan="${resultMap['zynk'].size()}" style="text-align: center" class="zynk">中医内科(12个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zynk">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>

					  <c:if test="${empty resultMap['zywk']}">
						  <tr class="zywk">
							  <td rowspan="1" style="text-align: center" class="zywk">中医外科(2个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zywk']}">
						  <c:forEach items="${resultMap['zywk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zywk">
									  <td rowspan="${resultMap['zywk'].size()}" style="text-align: center" class="zywk">中医外科(2个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zywk">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zyfk']}">
						  <tr class="zyfk">
							  <td rowspan="1" style="text-align: center" class="zyfk">中医妇科(2个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zyfk']}">
						  <c:forEach items="${resultMap['zyfk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zyfk">
									  <td rowspan="${resultMap['zyfk'].size()}" style="text-align: center" class="zyfk">中医妇科(2个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zyfk">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zyek']}">
						  <tr class="zyek">
							  <td rowspan="1" style="text-align: center" class="zyek">中医儿科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zyek']}">
						  <c:forEach items="${resultMap['zyek']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zyek">
									  <td rowspan="${resultMap['zyek'].size()}" style="text-align: center" class="zyek">中医儿科(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zyek">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zjk']}">
						  <tr class="zjk">
							  <td rowspan="1" style="text-align: center" class="zjk">针灸科(与推拿康复共3个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zjk']}">
						  <c:forEach items="${resultMap['zjk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zjk">
									  <td rowspan="${resultMap['zjk'].size()}" style="text-align: center" class="zjk">针灸科(与推拿康复共3个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zjk">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['tnk']}">
						  <tr class="tnk">
							  <td rowspan="1" style="text-align: center" class="tnk">推拿科(与针灸康复共3个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['tnk']}">
						  <c:forEach items="${resultMap['tnk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="tnk">
									  <td rowspan="${resultMap['tnk'].size()}" style="text-align: center" class="tnk">推拿科(与针灸康复共3个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="tnk">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zykfk']}">
						  <tr class="zykfk">
							  <td rowspan="1" style="text-align: center" class="zykfk">中医康复科(与针灸推拿共3个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zykfk']}">
						  <c:forEach items="${resultMap['zykfk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zykfk">
									  <td rowspan="${resultMap['zykfk'].size()}" style="text-align: center" class="zykfk">中医康复科(与针灸推拿共3个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zykfk">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zygsk']}">
						  <tr class="zygsk">
							  <td rowspan="1" style="text-align: center" class="zygsk">中医骨伤科(2个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zygsk']}">
						  <c:forEach items="${resultMap['zygsk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zygsk">
									  <td rowspan="${resultMap['zygsk'].size()}" style="text-align: center" class="zygsk">中医骨伤科(2个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zygsk">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zyebhk']}">
						  <tr class="zyebhk">
							  <td rowspan="1" style="text-align: center" class="zyebhk">中医耳鼻喉科(与眼科共1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zyebhk']}">
						  <c:forEach items="${resultMap['zyebhk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zyebhk">
									  <td rowspan="${resultMap['zyebhk'].size()}" style="text-align: center" class="zyebhk">中医耳鼻喉科(与眼科共1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zyebhk">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zyyk']}">
						  <tr class="zyyk">
							  <td rowspan="1" style="text-align: center" class="zyyk">中医眼科(与耳鼻喉科共1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zyyk']}">
						  <c:forEach items="${resultMap['zyyk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zyyk">
									  <td rowspan="${resultMap['zyyk'].size()}" style="text-align: center" class="zyyk">中医眼科(与耳鼻喉科共1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zyyk">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['fzks']}">
						  <tr class="fzks">
							  <td rowspan="1" style="text-align: center" class="fzks">辅助科室(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['fzks']}">
						  <c:forEach items="${resultMap['fzks']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="fzks">
									  <td rowspan="${resultMap['fzks'].size()}" style="text-align: center" class="fzks">辅助科室(1个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="fzks">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['gsxx']}">
						  <tr class="gsxx">
							  <td rowspan="1" style="text-align: center" class="gsxx">跟师学习(半天/周)<br/>填写时间为规培时间<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['gsxx']}">
						  <c:forEach items="${resultMap['gsxx']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="gsxx">
									  <td rowspan="${resultMap['gsxx'].size()}" style="text-align: center" class="gsxx">跟师学习(半天/周)<br/>填写时间为规培时间<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="gsxx">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['xgzk']}">
						  <tr class="xgzk">
							  <td rowspan="1" style="text-align: center" class="xgzk">相关专科(9个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['xgzk']}">
						  <c:forEach items="${resultMap['xgzk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="xgzk">
									  <td rowspan="${resultMap['xgzk'].size()}" style="text-align: center" class="xgzk">相关专科(9个月)<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="xgzk">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>

					  <c:if test="${empty resultMap['jdks']}">
						  <tr class="jdks">
							  <td rowspan="1" style="text-align: center" class="jdks">机动科室<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
							  <td style="text-align: center">
								  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
								  ~
								  <input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['jdks']}">
						  <c:forEach items="${resultMap['jdks']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="jdks">
									  <td rowspan="${resultMap['jdks'].size()}" style="text-align: center" class="jdks">机动科室<a style="float: right;margin-right: 10px;" onclick="addTd(this)">新增</a></td>
									  <td style="text-align: center">
										  &#12288;&#12288;<input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="jdks">
									  <td style="text-align: center">
										  <a onclick="delTd(this)">删除</a>
										  <input type="text" class="input  deptName" style="width: 180px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input  datepicker startDate" style="width: 120px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input  datepicker endDate" style="width: 120px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <tr>
						  <td style="text-align: center">合计(33个月)</td>
						  <td class="sum" style="text-align: center" colspan="2"></td>
					  </tr>
					  <tr>
						  <td colspan="3">
							  对照《住院医师规范化培训细则》，本人培训内容完成情况如上，申请参加
							  <input class="input validate[required]" id="graduationYear" value="${graduationYear}" readonly="readonly" style="width: 120px;">
							  年度结业考试资格审核。
						  </td>
					  </tr>
					  </tbody>
				  </table>
			  </div>

		  </div>
       </div>
    </div>
	</form>
	<div id="nextPage" class="button" style="margin: 30px;">
	    <input class="btn_blue saveButton" type="button" onclick="save();" value="保&#12288;存"/>
		<c:if test="${param.showPrint eq 'Y'}">
	    <input class="btn_blue" type="button" onclick="print();" value="打&#12288;印"/>
		</c:if>
    </div>

</div>
<div style="display: none">
		<table id="template">
			<tr>
				<td style="text-align: center">
					<a onclick="delTd(this)">删除</a>
					<input type="text" class="input  deptName" style="width: 180px;">
				</td>
				<td style="text-align: center">
					<input type="text" class="input  datepicker startDate" style="width: 120px;" name="" value=""/>
					~
					<input type="text" class="input  datepicker endDate" style="width: 120px;" name="" value=""/>
				</td>
			</tr>
		</table>
</div>
</div>
