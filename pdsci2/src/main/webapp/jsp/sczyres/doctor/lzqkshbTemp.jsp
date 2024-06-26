<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<style>
.base_info th,.base_info td{height:35px;}

</style>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
$(document).ready(function(){
	var id ='';//计算轮转总时间
	if(${doctor.trainingSpeId eq '1'}){
		id = 'zyDiv';
	}else if(${doctor.trainingSpeId eq '2'}){
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

	window.print();
});
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
</script>
<div class="col_main" style="overflow-y: auto;overflow-x: visible;">
<div id='docTypeForm'>
    <form id='doctorForm' style="position:relative;">
    <p id="errorMsg" style="color: red;"></p>
    <div class="main_bd">
       <div class="div_table">
          <div class="score_frame">
			  <h1 style="text-align:center;">培训基地轮转信息表</h1>
			  <div class="div_table">
				  <h4>基本信息</h4>
                <table border="0" cellpadding="0" cellspacing="0" class="base_info">
            <colgroup>
              <col width="16%"/>
              <col width="16%"/>
              <col width="16%"/>
              <col width="14%"/>
              <col width="16%"/>
              <col width="17%"/>
            </colgroup>
	           <tbody>
			   <tr>
				   <th>所学专业：</th>
				   <td>&#12288;<c:if test="${doctor.trainingSpeId eq '1'}">中医</c:if><c:if test="${doctor.trainingSpeId eq '2'}">中医全科</c:if><c:if test="${doctor.trainingSpeId eq '3'}">中医助理全科</c:if></td>
				   <th>培训基地：</th>
				   <td colspan="3">&#12288;<c:forEach items="${hospitals}" var="hosptial"><c:if test='${user.orgFlow eq hosptial.orgFlow}'>${hosptial.orgName}</c:if></c:forEach></td>
			   </tr>
			   <tr>
				   <th>姓名：</th>
				   <td>&#12288;${user.userName}</td>
				   <th>性别：</th>
				   <td>&#12288;${user.sexId eq userSexEnumMan.id?userSexEnumMan.name:''}${user.sexId eq userSexEnumWoman.id?userSexEnumWoman.name:''}</td>
				   <th>身份证号码：</th>
				   <td>&#12288;${user.idNo}</td>
			   </tr>
				<tr>
					<th>学历：</th>
					<td>&#12288;<c:forEach items="${dictTypeEnumUserEducationList}" var="dict"><c:if test="${user.educationId eq dict.dictId}">${dict.dictName}</c:if></c:forEach></td>
					<th>学位：</th>
					<td>&#12288;<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict"><c:if test="${user.degreeId eq dict.dictId}">${dict.dictName}</c:if></c:forEach></td>
					<th>学员身份：</th>
					<td>&#12288;<c:forEach items="${sczyRecDocTypeEnumList}" var="doctorType"><c:if test="${doctorType.id eq doctor.doctorTypeId}">${doctorType.name}</c:if></c:forEach></td>
				</tr>
				<tr>
					<c:if test="${doctor.trainingSpeId eq '1' or doctor.trainingSpeId eq '2'}">
					<th>医师资格证<br/>书编号：</th>
					<td>&#12288;${doctor.doctorLicenseNo}</td>
					</c:if>
					<c:if test="${doctor.trainingSpeId eq '3'}">
						<th>助理医师资<br/>格证书编号：</th>
						<td>&#12288;${extInfo.assistantQualificationCertificateCode}</td>
					</c:if>
					<th>培训起止时间：</th>
					<td colspan="3">&#12288;${extInfo.trainingStartDate} ~ ${extInfo.trainingEndDate}</td>
				</tr>
	           </tbody>
              </table>
              </div>
            <div class="div_table" ID="zyqkDiv" style="display:${doctor.trainingSpeId eq '2'?'':'none'};">
			<h4>轮转情况</h4>
	        <table border="0" cellpadding="0" cellspacing="0" class="base_info">
	            <colgroup>
					<col width="30%"/>
					<col width="25%"/>
					<col width="45%"/>
	            </colgroup>
				<tbody>
					<tr>
						<th style="text-align: center">学科名称</th>
						<th style="text-align: center">科室</th>
						<th style="text-align: center">时间</th>
					</tr>
					<c:if test="${empty resultMap['llxx']}">
						<tr class="llxx">
							<td rowspan="1" style="text-align: center" class="llxx">理论学习(1个月)</td>
							<td style="text-align: center"><input type="text" class="input deptName" style="width:160px;"></td>
							<td style="text-align: center">
								<input type="text" class="input startDate" style="width: 160px;"/> ~ <input type="text" class="input endDate" style="width: 160px;"/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['llxx']}">
						<c:forEach items="${resultMap['llxx']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="llxx">
									<td rowspan="${resultMap['llxx'].size()}" style="text-align: center" class="llxx">理论学习(1个月)</td>
									<td style="text-align: center"><input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}"></td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/> ~ <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="llxx">
								<td style="text-align: center">
									<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
								</td>
								<td style="text-align: center">
									<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
									~
									<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
								</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${empty resultMap['zynk']}">
						<tr class="zynk">
							<td rowspan="1" style="text-align: center" class="zynk">中医内科(10个月)</td>
							<td style="text-align: center">
								<input type="text" class="input deptName" style="width: 160px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								~
								<input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['zynk']}">
						<c:forEach items="${resultMap['zynk']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="zynk">
									<td rowspan="${resultMap['zynk'].size()}" style="text-align: center" class="zynk">中医内科(10个月)</td>
									<td style="text-align: center">
										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="zynk">
								<td style="text-align: center">
									<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
								</td>
								<td style="text-align: center">
									<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/> ~ <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
								</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>

					<c:if test="${empty resultMap['zywk']}">
						<tr class="zywk">
							<td rowspan="1" style="text-align: center" class="zywk">中医外科(2个月)</td>
							<td style="text-align: center">
								<input type="text" class="input deptName" style="width: 160px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								~
								<input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['zywk']}">
						<c:forEach items="${resultMap['zywk']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="zywk">
									<td rowspan="${resultMap['zywk'].size()}" style="text-align: center" class="zywk">中医外科(2个月)</td>
									<td style="text-align: center">
										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="zywk">
									<td style="text-align: center">

										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['zyfk']}">
						<tr class="zyfk">
							<td rowspan="1" style="text-align: center" class="zyfk">中医妇科(1个月)</td>
							<td style="text-align: center">
								<input type="text" class="input deptName" style="width: 160px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								~
								<input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['zyfk']}">
						<c:forEach items="${resultMap['zyfk']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="zyfk">
									<td rowspan="${resultMap['zyfk'].size()}" style="text-align: center" class="zyfk">中医妇科(1个月)</td>
									<td style="text-align: center">
										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="zyfk">
									<td style="text-align: center">

										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['zyek']}">
						<tr class="zyek">
							<td rowspan="1" style="text-align: center" class="zyek">中医儿科(1个月)</td>
							<td style="text-align: center">
								<input type="text" class="input deptName" style="width: 160px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								~
								<input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['zyek']}">
						<c:forEach items="${resultMap['zyek']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="zyek">
									<td rowspan="${resultMap['zyek'].size()}" style="text-align: center" class="zyek">中医儿科(1个月)</td>
									<td style="text-align: center">
										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="zyek">
									<td style="text-align: center">

										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['zjk']}">
						<tr class="zjk">
							<td rowspan="1" style="text-align: center" class="zjk">针灸科(2个月)</td>
							<td style="text-align: center">
								<input type="text" class="input deptName" style="width: 160px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								~
								<input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['zjk']}">
						<c:forEach items="${resultMap['zjk']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="zjk">
									<td rowspan="${resultMap['zjk'].size()}" style="text-align: center" class="zjk">针灸科(2个月)</td>
									<td style="text-align: center">
										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="zjk">
									<td style="text-align: center">

										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['tnk']}">
						<tr class="tnk">
							<td rowspan="1" style="text-align: center" class="tnk">推拿科(1个月)</td>
							<td style="text-align: center">
								<input type="text" class="input deptName" style="width: 160px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								~
								<input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['tnk']}">
						<c:forEach items="${resultMap['tnk']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="tnk">
									<td rowspan="${resultMap['tnk'].size()}" style="text-align: center" class="tnk">推拿科(1个月)</td>
									<td style="text-align: center">
										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="tnk">
									<td style="text-align: center">

										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['zykfk']}">
						<tr class="zykfk">
							<td rowspan="1" style="text-align: center" class="zykfk">中医康复科(1个月)</td>
							<td style="text-align: center">
								<input type="text" class="input deptName" style="width: 160px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								~
								<input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['zykfk']}">
						<c:forEach items="${resultMap['zykfk']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="zykfk">
									<td rowspan="${resultMap['zykfk'].size()}" style="text-align: center" class="zykfk">中医康复科(1个月)</td>
									<td style="text-align: center">
										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="zykfk">
									<td style="text-align: center">

										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['zygsk']}">
						<tr class="zygsk">
							<td rowspan="1" style="text-align: center" class="zygsk">中医骨伤科(1个月)</td>
							<td style="text-align: center">
								<input type="text" class="input deptName" style="width: 160px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								~
								<input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['zygsk']}">
						<c:forEach items="${resultMap['zygsk']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="zygsk">
									<td rowspan="${resultMap['zygsk'].size()}" style="text-align: center" class="zygsk">中医骨伤科(1个月)</td>
									<td style="text-align: center">
										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="zygsk">
									<td style="text-align: center">

										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['zyebhk']}">
						<tr class="zyebhk">
							<td rowspan="1" style="text-align: center" class="zyebhk">中医耳鼻喉科(与眼科共1个月)</td>
							<td style="text-align: center">
								<input type="text" class="input deptName" style="width: 160px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								~
								<input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['zyebhk']}">
						<c:forEach items="${resultMap['zyebhk']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="zyebhk">
									<td rowspan="${resultMap['zyebhk'].size()}" style="text-align: center" class="zyebhk">中医耳鼻喉科(与眼科共1个月)</td>
									<td style="text-align: center">
										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="zyebhk">
									<td style="text-align: center">

										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['zyyk']}">
						<tr class="zyyk">
							<td rowspan="1" style="text-align: center" class="zyyk">中医眼科(与耳鼻喉科共1个月)</td>
							<td style="text-align: center">
								<input type="text" class="input deptName" style="width: 160px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								~
								<input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['zyyk']}">
						<c:forEach items="${resultMap['zyyk']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="zyyk">
									<td rowspan="${resultMap['zyyk'].size()}" style="text-align: center" class="zyyk">中医眼科(与耳鼻喉科共1个月)</td>
									<td style="text-align: center">
										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="zyyk">
									<td style="text-align: center">

										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['zk']}">
						<tr class="zk">
							<td rowspan="1" style="text-align: center" class="zk">急诊科（包括院前急救）(3个月)</td>
							<td style="text-align: center">
								<input type="text" class="input deptName" style="width: 160px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								~
								<input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['zk']}">
						<c:forEach items="${resultMap['zk']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="zk">
									<td rowspan="${resultMap['zk'].size()}" style="text-align: center" class="zk">急诊科（包括院前急救）(3个月)</td>
									<td style="text-align: center">
										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="zk">
									<td style="text-align: center">

										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['pwk']}">
						<tr class="pwk">
							<td rowspan="1" style="text-align: center" class="pwk">普外科(1个月)</td>
							<td style="text-align: center">
								<input type="text" class="input deptName" style="width: 160px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								~
								<input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['pwk']}">
						<c:forEach items="${resultMap['pwk']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="pwk">
									<td rowspan="${resultMap['pwk'].size()}" style="text-align: center" class="pwk">普外科(1个月)</td>
									<td style="text-align: center">
										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="pwk">
									<td style="text-align: center">

										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['fzks']}">
						<tr class="fzks">
							<td rowspan="1" style="text-align: center" class="fzks">辅助科室(1个月)</td>
							<td style="text-align: center">
								<input type="text" class="input deptName" style="width: 160px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								~
								<input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['fzks']}">
						<c:forEach items="${resultMap['fzks']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="fzks">
									<td rowspan="${resultMap['fzks'].size()}" style="text-align: center" class="fzks">辅助科室(1个月)</td>
									<td style="text-align: center">
										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="fzks">
									<td style="text-align: center">

										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['xx']}">
						<tr class="xx">
							<td rowspan="1" style="text-align: center" class="xx">选修(1个月)</td>
							<td style="text-align: center">
								<input type="text" class="input deptName" style="width: 160px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								~
								<input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['xx']}">
						<c:forEach items="${resultMap['xx']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="xx">
									<td rowspan="${resultMap['xx'].size()}" style="text-align: center" class="xx">选修(1个月)</td>
									<td style="text-align: center">
										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="xx">
									<td style="text-align: center">

										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['gsxx']}">
						<tr class="gsxx">
							<td rowspan="1" style="text-align: center" class="gsxx">跟师学习(半天/周)</td>
							<td style="text-align: center">
								<input type="text" class="input deptName" style="width: 160px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								~
								<input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['gsxx']}">
						<c:forEach items="${resultMap['gsxx']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="gsxx">
									<td rowspan="${resultMap['gsxx'].size()}" style="text-align: center" class="gsxx">跟师学习(半天/周)</td>
									<td style="text-align: center">
										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="gsxx">
									<td style="text-align: center">

										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>


					<c:if test="${empty resultMap['jcsj']}">
						<tr class="jcsj">
							<td rowspan="1" style="text-align: center" class="jcsj">基层实践(6个月)</td>
							<td style="text-align: center">
								<input type="text" class="input deptName" style="width: 160px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								~
								<input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['jcsj']}">
						<c:forEach items="${resultMap['jcsj']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="jcsj">
									<td rowspan="${resultMap['jcsj'].size()}" style="text-align: center" class="jcsj">基层实践(6个月)</td>
									<td style="text-align: center">
										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="jcsj">
									<td style="text-align: center">
										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
						</c:forEach>
					</c:if>

					<c:if test="${empty resultMap['jdks']}">
						<tr class="jdks">
							<td rowspan="1" style="text-align: center" class="jdks">机动科室</td>
							<td style="text-align: center">
								<input type="text" class="input validate[required] deptName" style="width: 160px;">
							</td>
							<td style="text-align: center">
								<input type="text" class="input validate[required] datepicker startDate" style="width: 160px;" name="" value=""/>
								~
								<input type="text" class="input validate[required] datepicker endDate" style="width: 160px;" name="" value=""/>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty resultMap['jdks']}">
						<c:forEach items="${resultMap['jdks']}" var="result" varStatus="s">
							<c:if test="${s.index eq 0}">
								<tr class="jdks">
									<td rowspan="${resultMap['jdks'].size()}" style="text-align: center" class="jdks">机动科室</td>
									<td style="text-align: center">
										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input datepicker startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input datepicker endDate" style="width: 160px;" value="${result.endDate}"/>
									</td>
									<input type="hidden" class="recordFlow" value="${result.recordFlow}">
								</tr>
							</c:if>
							<c:if test="${s.index ne 0}">
								<tr class="jdks">
									<td style="text-align: center">

										<input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									</td>
									<td style="text-align: center">
										<input type="text" class="input datepicker startDate" style="width: 160px;" value="${result.startDate}"/>
										~
										<input type="text" class="input datepicker endDate" style="width: 160px;" value="${result.endDate}"/>
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
							<input class="input" id="graduationYear" value="${graduationYear}" readonly="readonly" style="width: 160px;">
							年度结业考试资格审核。
						</td>
					</tr>
			    </tbody>
		       </table>
			</div>

			  <div class="div_table" ID="zyzlqkDiv" style="display:${doctor.trainingSpeId eq '3'?'':'none'};">
				  <h4>轮转情况</h4>
				  <table border="0" cellpadding="0" cellspacing="0" class="base_info">
					  <colgroup>
						  <col width="30%"/>
						  <col width="25%"/>
						  <col width="45%"/>
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
								  消化（脾胃病）4周，内分泌代谢4周，泌尿（肾病）4周，血液或肿瘤4周，风湿4周，神经4周）（38周）</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zynk']}">
						  <c:forEach items="${resultMap['zynk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zynk">
									  <td rowspan="${resultMap['zynk'].size()}" style="text-align: center" class="zynk">中医内科（心血管8周，呼吸（肺病）6周，
										  消化（脾胃病）4周，内分泌代谢4周，泌尿（肾病）4周，血液或肿瘤4周，风湿4周，神经4周）（38周）</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zynk">
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>

					  <c:if test="${empty resultMap['zywk']}">
						  <tr class="zywk">
							  <td rowspan="1" style="text-align: center" class="zywk">急诊科（含院前急救）（8周）</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zywk']}">
						  <c:forEach items="${resultMap['zywk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zywk">
									  <td rowspan="${resultMap['zywk'].size()}" style="text-align: center" class="zywk">急诊科（含院前急救）（8周）</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zywk">
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zyfk']}">
						  <tr class="zyfk">
							  <td rowspan="1" style="text-align: center" class="zyfk">中医外科（6周）</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zyfk']}">
						  <c:forEach items="${resultMap['zyfk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zyfk">
									  <td rowspan="${resultMap['zyfk'].size()}" style="text-align: center" class="zyfk">中医外科（6周）</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zyfk">
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zyek']}">
						  <tr class="zyek">
							  <td rowspan="1" style="text-align: center" class="zyek">中医妇科（4周）</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zyek']}">
						  <c:forEach items="${resultMap['zyek']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zyek">
									  <td rowspan="${resultMap['zyek'].size()}" style="text-align: center" class="zyek">中医妇科（4周）</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zyek">
									  <td style="text-align: center">

										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zjk']}">
						  <tr class="zjk">
							  <td rowspan="1" style="text-align: center" class="zjk">中医儿科（4周）</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zjk']}">
						  <c:forEach items="${resultMap['zjk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zjk">
									  <td rowspan="${resultMap['zjk'].size()}" style="text-align: center" class="zjk">中医儿科（4周）</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zjk">
									  <td style="text-align: center">

										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['tnk']}">
						  <tr class="tnk">
							  <td rowspan="1" style="text-align: center" class="tnk">针灸科</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['tnk']}">
						  <c:forEach items="${resultMap['tnk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="tnk">
									  <td rowspan="${resultMap['tnk'].size()}" style="text-align: center" class="tnk">针灸科</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="tnk">
									  <td style="text-align: center">

										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zykfk']}">
						  <tr class="zykfk">
							  <td rowspan="1" style="text-align: center" class="zykfk">推拿科（10周）</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zykfk']}">
						  <c:forEach items="${resultMap['zykfk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zykfk">
									  <td rowspan="${resultMap['zykfk'].size()}" style="text-align: center" class="zykfk">推拿科（10周）</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zykfk">
									  <td style="text-align: center">

										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zygsk']}">
						  <tr class="zygsk">
							  <td rowspan="1" style="text-align: center" class="zygsk">中医康复科</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zygsk']}">
						  <c:forEach items="${resultMap['zygsk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zygsk">
									  <td rowspan="${resultMap['zygsk'].size()}" style="text-align: center" class="zygsk">中医康复科</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zygsk">
									  <td style="text-align: center">

										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zyebhk']}">
						  <tr class="zyebhk">
							  <td rowspan="1" style="text-align: center" class="zyebhk">中医骨伤科（4周）</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zyebhk']}">
						  <c:forEach items="${resultMap['zyebhk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zyebhk">
									  <td rowspan="${resultMap['zyebhk'].size()}" style="text-align: center" class="zyebhk">中医骨伤科（4周）</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zyebhk">
									  <td style="text-align: center">

										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zyyk']}">
						  <tr class="zyyk">
							  <td rowspan="1" style="text-align: center" class="zyyk">中医眼科(2周)</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zyyk']}">
						  <c:forEach items="${resultMap['zyyk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zyyk">
									  <td rowspan="${resultMap['zyyk'].size()}" style="text-align: center" class="zyyk">中医眼科(2周)</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zyyk">
									  <td style="text-align: center">

										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['fzks']}">
						  <tr class="fzks">
							  <td rowspan="1" style="text-align: center" class="fzks">中医耳鼻咽喉科（2周）</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['fzks']}">
						  <c:forEach items="${resultMap['fzks']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="fzks">
									  <td rowspan="${resultMap['fzks'].size()}" style="text-align: center" class="fzks">中医耳鼻咽喉科（2周）</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="fzks">
									  <td style="text-align: center">

										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['lchl2']}">
						  <tr class="lchl2">
							  <td rowspan="1" style="text-align: center" class="lchl2">临床护理（2周）</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['lchl2']}">
						  <c:forEach items="${resultMap['lchl2']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="lchl2">
									  <td rowspan="${resultMap['lchl2'].size()}" style="text-align: center" class="lchl2">临床护理（2周）</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="lchl2">
									  <td style="text-align: center">

										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['xgzk']}">
						  <tr class="xgzk">
							  <td rowspan="1" style="text-align: center" class="xgzk">辅助科室（2周）</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['xgzk']}">
						  <c:forEach items="${resultMap['xgzk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="xgzk">
									  <td rowspan="${resultMap['xgzk'].size()}" style="text-align: center" class="xgzk">辅助科室（2周）</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="xgzk">
									  <td style="text-align: center">

										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>

					  <c:if test="${empty resultMap['gsxx']}">
						  <tr class="gsxx">
							  <td rowspan="1" style="text-align: center" class="gsxx">跟师学习</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['gsxx']}">
						  <c:forEach items="${resultMap['gsxx']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="gsxx">
									  <td rowspan="${resultMap['gsxx'].size()}" style="text-align: center" class="gsxx">跟师学习</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="gsxx">
									  <td style="text-align: center">

										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>

					  <c:if test="${empty resultMap['jcsj']}">
						  <tr class="jcsj">
							  <td rowspan="1" style="text-align: center" class="jcsj">基层实践（16周）</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['jcsj']}">
						  <c:forEach items="${resultMap['jcsj']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="jcsj">
									  <td rowspan="${resultMap['jcsj'].size()}" style="text-align: center" class="jcsj">基层实践（16周）</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="jcsj">
									  <td style="text-align: center">

										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>

					  <tr>
						  <td style="text-align: center">合计(98周)</td>
						  <td class="sum" style="text-align: center" colspan="2"></td>
					  </tr>
					  <tr>
						  <td colspan="3">
							  对照《住院医师规范化培训细则》，本人培训内容完成情况如上，申请参加
							  <input class="input" id="graduationYear" value="${graduationYear}" readonly="readonly" style="width: 160px;">
							  年度结业考试资格审核。
						  </td>
					  </tr>
					  </tbody>
				  </table>
			  </div>

			  <div class="div_table" ID="zyDiv" style="display: ${doctor.trainingSpeId eq '1'?'':'none'}">
				  <h4>轮转情况</h4>
				  <table border="0" cellpadding="0" cellspacing="0" class="base_info">
					  <colgroup>
						  <col width="30%"/>
						  <col width="25%"/>
						  <col width="45%"/>
					  </colgroup>
					  <tbody>
					  <tr>
						  <th style="text-align: center">学科名称</th>
						  <th style="text-align: center">科室</th>
						  <th style="text-align: center">时间</th>
					  </tr>


					  <c:if test="${empty resultMap['zynk']}">
						  <tr class="zynk">
							  <td rowspan="1" style="text-align: center" class="zynk">中医内科(12个月)</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zynk']}">
						  <c:forEach items="${resultMap['zynk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zynk">
									  <td rowspan="${resultMap['zynk'].size()}" style="text-align: center" class="zynk">中医内科(12个月)</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zynk">
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>

					  <c:if test="${empty resultMap['zywk']}">
						  <tr class="zywk">
							  <td rowspan="1" style="text-align: center" class="zywk">中医外科(2个月)</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zywk']}">
						  <c:forEach items="${resultMap['zywk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zywk">
									  <td rowspan="${resultMap['zywk'].size()}" style="text-align: center" class="zywk">中医外科(2个月)</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zywk">
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zyfk']}">
						  <tr class="zyfk">
							  <td rowspan="1" style="text-align: center" class="zyfk">中医妇科(2个月))</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zyfk']}">
						  <c:forEach items="${resultMap['zyfk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zyfk">
									  <td rowspan="${resultMap['zyfk'].size()}" style="text-align: center" class="zyfk">中医妇科(2个月)</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zyfk">
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zyek']}">
						  <tr class="zyek">
							  <td rowspan="1" style="text-align: center" class="zyek">中医儿科(1个月)</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zyek']}">
						  <c:forEach items="${resultMap['zyek']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zyek">
									  <td rowspan="${resultMap['zyek'].size()}" style="text-align: center" class="zyek">中医儿科(1个月)</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zyek">
									  <td style="text-align: center">

										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zjk']}">
						  <tr class="zjk">
							  <td rowspan="1" style="text-align: center" class="zjk">针灸科(与推拿康复共3个月)</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zjk']}">
						  <c:forEach items="${resultMap['zjk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zjk">
									  <td rowspan="${resultMap['zjk'].size()}" style="text-align: center" class="zjk">针灸科(与推拿康复共3个月)</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zjk">
									  <td style="text-align: center">

										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['tnk']}">
						  <tr class="tnk">
							  <td rowspan="1" style="text-align: center" class="tnk">推拿科(与针灸康复共3个月)</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['tnk']}">
						  <c:forEach items="${resultMap['tnk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="tnk">
									  <td rowspan="${resultMap['tnk'].size()}" style="text-align: center" class="tnk">推拿科(与针灸康复共3个月)</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="tnk">
									  <td style="text-align: center">

										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zykfk']}">
						  <tr class="zykfk">
							  <td rowspan="1" style="text-align: center" class="zykfk">中医康复科(与针灸推拿共3个月)</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zykfk']}">
						  <c:forEach items="${resultMap['zykfk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zykfk">
									  <td rowspan="${resultMap['zykfk'].size()}" style="text-align: center" class="zykfk">中医康复科(与针灸推拿共3个月)</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zykfk">
									  <td style="text-align: center">

										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zygsk']}">
						  <tr class="zygsk">
							  <td rowspan="1" style="text-align: center" class="zygsk">中医骨伤科(2个月)</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zygsk']}">
						  <c:forEach items="${resultMap['zygsk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zygsk">
									  <td rowspan="${resultMap['zygsk'].size()}" style="text-align: center" class="zygsk">中医骨伤科(2个月)</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zygsk">
									  <td style="text-align: center">

										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zyebhk']}">
						  <tr class="zyebhk">
							  <td rowspan="1" style="text-align: center" class="zyebhk">中医耳鼻喉科(与眼科共1个月)</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zyebhk']}">
						  <c:forEach items="${resultMap['zyebhk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zyebhk">
									  <td rowspan="${resultMap['zyebhk'].size()}" style="text-align: center" class="zyebhk">中医耳鼻喉科(与眼科共1个月)</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zyebhk">
									  <td style="text-align: center">

										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['zyyk']}">
						  <tr class="zyyk">
							  <td rowspan="1" style="text-align: center" class="zyyk">中医眼科(与耳鼻喉科共1个月)</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['zyyk']}">
						  <c:forEach items="${resultMap['zyyk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="zyyk">
									  <td rowspan="${resultMap['zyyk'].size()}" style="text-align: center" class="zyyk">中医眼科(与耳鼻喉科共1个月)</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="zyyk">
									  <td style="text-align: center">

										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['fzks']}">
						  <tr class="fzks">
							  <td rowspan="1" style="text-align: center" class="fzks">辅助科室(1个月)</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['fzks']}">
						  <c:forEach items="${resultMap['fzks']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="fzks">
									  <td rowspan="${resultMap['fzks'].size()}" style="text-align: center" class="fzks">辅助科室(1个月)</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="fzks">
									  <td style="text-align: center">

										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['gsxx']}">
						  <tr class="gsxx">
							  <td rowspan="1" style="text-align: center" class="gsxx">跟师学习(半天/周)</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['gsxx']}">
						  <c:forEach items="${resultMap['gsxx']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="gsxx">
									  <td rowspan="${resultMap['gsxx'].size()}" style="text-align: center" class="gsxx">跟师学习(半天/周)</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="gsxx">
									  <td style="text-align: center">

										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>


					  <c:if test="${empty resultMap['xgzk']}">
						  <tr class="xgzk">
							  <td rowspan="1" style="text-align: center" class="xgzk">相关专科(9个月)</td>
							  <td style="text-align: center">
								  <input type="text" class="input deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['xgzk']}">
						  <c:forEach items="${resultMap['xgzk']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="xgzk">
									  <td rowspan="${resultMap['xgzk'].size()}" style="text-align: center" class="xgzk">相关专科(9个月)</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="xgzk">
									  <td style="text-align: center">

										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
						  </c:forEach>
					  </c:if>

					  <c:if test="${empty resultMap['jdks']}">
						  <tr class="jdks">
							  <td rowspan="1" style="text-align: center" class="jdks">机动科室</td>
							  <td style="text-align: center">
								  <input type="text" class="input validate[required] deptName" style="width: 160px;">
							  </td>
							  <td style="text-align: center">
								  <input type="text" class="input validate[required] datepicker startDate" style="width: 160px;" name="" value=""/>
								  ~
								  <input type="text" class="input validate[required] datepicker endDate" style="width: 160px;" name="" value=""/>
							  </td>
						  </tr>
					  </c:if>
					  <c:if test="${not empty resultMap['jdks']}">
						  <c:forEach items="${resultMap['jdks']}" var="result" varStatus="s">
							  <c:if test="${s.index eq 0}">
								  <tr class="jdks">
									  <td rowspan="${resultMap['jdks'].size()}" style="text-align: center" class="jdks">机动科室</td>
									  <td style="text-align: center">
										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input datepicker startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input datepicker endDate" style="width: 160px;" value="${result.endDate}"/>
									  </td>
									  <input type="hidden" class="recordFlow" value="${result.recordFlow}">
								  </tr>
							  </c:if>
							  <c:if test="${s.index ne 0}">
								  <tr class="jdks">
									  <td style="text-align: center">

										  <input type="text" class="input deptName" style="width: 160px;" value="${result.deptName}">
									  </td>
									  <td style="text-align: center">
										  <input type="text" class="input datepicker startDate" style="width: 160px;" value="${result.startDate}"/>
										  ~
										  <input type="text" class="input datepicker endDate" style="width: 160px;" value="${result.endDate}"/>
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
							  <input class="input" id="graduationYear" value="${graduationYear}" readonly="readonly" style="width: 160px;">
							  年度结业考试资格审核。
						  </td>
					  </tr>
					  </tbody>
				  </table>
			  </div>
			  <div class="div_table">
				  <table border="0" cellpadding="0" cellspacing="0" class="base_info">
					  <h4>审核信息</h4>
					  <colgroup>
						  <col width="25%"/>
						  <col width="25%"/>
						  <col width="25%"/>
						  <col width="25%"/>
					  </colgroup>
					  <tbody>
					  <tr>
						  <th colspan="4" style="text-align:left;">&#12288;申请人（签名）：<span style="padding-left:315px;"></span>&#12288;&#12288;&#12288;年&#12288;&#12288;月&#12288;&#12288;日</th>
					  </tr>
					  <tr><td colspan="4" style="text-align:center;">根据学员本人提出的申请，经过对培训期间的各方面表现，培训内容完成情况、出科考核情况及年度考核情况进行审核，同意其提出的结业考核申请</td></tr>
					  <tr><th colspan="4" style="text-align:right;height:50px;">基地医院（盖章）<span style="padding-right:160px;"></span></th></tr>
					  <tr>
						  <th colspan="4" style="text-align:left;">&#12288;基地主任（签名）：<span style="padding-right:160px;"></span>审核人（签名）：<span style="padding-right:160px;"></span>&#12288;&#12288;年&#12288;&#12288;月&#12288;&#12288;日&#12288;&#12288;</th>
					  </tr>
					  <tr><td colspan="4" style="text-align:center;">本人承认以上轮转培训情况属实，如有虚假，本人愿承担一切后果。 </td></tr>
					  <tr><th colspan="4" style="text-align:right;">承诺人（学员签字）：<span style="padding-right:160px;"></span> </th></tr>
					  <tr><td colspan="4" style="text-align:center;">经过对申请资料进行审核，&#12288;&#12288;&#12288;该学员参加结业考核。 </td></tr>
					  <tr>
						  <th colspan="4" style="text-align:right;height:50px;">四川省中医药毕业后教育委员会办公室（盖章）<span style="padding-right:80px;"></span>年&#12288;&#12288;月&#12288;&#12288;日&#12288;&#12288;</th>
					  </tr>
					  </tbody>
				  </table>
			  </div>
		  </div>
       </div>
    </div>
	</form>
</div>
</div>
