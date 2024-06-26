<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<style>
.base_info th,.base_info td{height:45px;}
	.lmx{
		display: inline-block;
		width: 220px;
		height: 1px;
		background: black;
		vertical-align: text-bottom;
	}
</style>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="application/javascript">
$(document).ready(function(){
	window.print();
});
</script>
<div class="col_main" id="content">
<div id='docTypeForm'>
    <p id="errorMsg" style="color: red;"></p>
    <form id='doctorForm' style="position:relative;">
		<div class="main_bd">
			<div class="div_table">
				<div class="score_frame">
					<h1 style="text-align:center;">
						<c:if test="${doctor.trainingSpeId eq '1' or doctor.trainingSpeId eq '2'}">四川省中医住院医师规范化培训结业考核申请表（${year}）</c:if>
						<c:if test="${doctor.trainingSpeId eq '3'}">四川省中医类别助理全科医生培训结业考核申请表（${year}）</c:if>
					</h1>
					<div class="div_table">
						<h4>基本信息</h4>
						<table border="0" cellpadding="0" cellspacing="0" class="base_info">
							<colgroup>
								<col width="14%"/>
								<col width="20%"/>
								<col width="14%"/>
								<col width="10%"/>
								<col width="12%"/>
								<col width="20%"/>
							</colgroup>
							<tbody>
							<tr>
								<th>姓名：</th>
								<td>&#12288;${user.userName}</td>
								<th>性别：</th>
								<td colspan="2">&#12288;${user.sexId eq userSexEnumMan.id?userSexEnumMan.name:''}${user.sexId eq userSexEnumWoman.id?userSexEnumWoman.name:''}</td>
								<td id="imgTd" rowspan="6" style="text-align: center;padding-top:5px;">
									<img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" width="110px" height="130px" onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
								</td>
							</tr>
							<tr>
								<th>证件类型：</th>
								<td>&#12288;<c:forEach items="${certificateTypeEnumList}" var="cret"><c:if test="${user.cretTypeId eq cret.id}">${cret.name}</c:if></c:forEach></td>
								<th>证件号：</th>
								<td colspan="2">&#12288;${user.idNo}</td>
							</tr>
							<tr>
								<th>结业考核年份：</th>
								<td>&#12288;${doctor.graduationYear}</td>
								<th>联系方式：</th>
								<td colspan="2">&#12288;${user.userPhone}</td>
							</tr>
							<tr>
								<th>入培年级：</th>
								<td>&#12288;${doctor.sessionNumber}</td>
								<th>培训身份：</th>
								<td colspan="2">&#12288;<c:forEach items="${sczyRecDocTypeEnumList}" var="doctorType"><c:if test="${doctorType.id eq doctor.doctorTypeId}">${doctorType.name}</c:if></c:forEach></td>
							</tr>
							<tr class="workOrg" style="display:${doctorType.id eq 'Agency'?'':'none'}">
								<th>委托培养单位：</th>
								<td colspan="4">&#12288;${doctor.workOrgName}</td>
							</tr>
							<tr class="workSchool" style="display:${doctorType.id eq 'Graduate'?'':'none'}">
								<th>最高学历毕业&#12288;<br/>院校：</th>
								<td colspan="4">&#12288;${doctor.workOrgName}</td>
							</tr>
							<tr>
								<th>培训基地：</th>
								<td colspan="4">&#12288;<c:forEach items="${hospitals}" var="hosptial"><c:if test='${user.orgFlow eq hosptial.orgFlow}'>${hosptial.orgName}</c:if></c:forEach></td>
							</tr>
							<tr>
								<th>家庭住址：</th>
								<td colspan="4">&#12288;${extInfo.homeProvName} ${extInfo.homeCityName} ${extInfo.homeAreaName}
									${user.userAddress}</td>
							</tr>
							<tr>
								<th></th>
								<td colspan="5">&#12288;工作单位（单位人填写）：<c:if test="${doctor.doctorTypeId eq 'Agency' || doctor.doctorTypeId eq 'Entrust'}">${doctor.workOrgName}</c:if></td>
							</tr>
							</tbody>
						</table>
					</div>

					<div class="div_table hide">
						<h4>资格信息</h4>
						<table border="0" cellpadding="0" cellspacing="0" class="base_info">
							<colgroup>
								<col width="25%"/>
								<col width="25%"/>
								<col width="25%"/>
								<col width="25%"/>
							</colgroup>
							<tbody>
							<tr>
								<th style="text-align:right;">学历：</th>
								<td style="text-align:left;">&#12288;<c:forEach items="${dictTypeEnumUserEducationList}" var="dict"><c:if test="${user.educationId eq dict.dictId}">${dict.dictName}</c:if></c:forEach></td>
								<th style="text-align:right;">学历证书编号：</th>
								<td style="text-align:left;">&#12288;${doctor.certificateNo}</td>
							</tr>
							<tr>
								<th style="text-align:right;">学位：</th>
								<td style="text-align:left;">&#12288;${user.degreeName}</td>
								<th style="text-align:right;">学位证书编号：</th>
								<td style="text-align:left;">&#12288;${doctor.degreeNo}</td>
							</tr>
							<tr>
								<th style="text-align:right;">培训时长：</th>
								<td style="text-align:left;">&#12288;${doctor.trainingYears}月</td>
								<th style="text-align:right;">培训起止时间：</th>
								<td style="text-align:left;">&#12288;${extInfo.trainingStartDate} ~ ${extInfo.trainingEndDate}</td>
							</tr>
							<tr>
								<th style="text-align:right;">医师资格证书编号：</th>
								<td style="text-align:left;">&#12288;${doctor.doctorLicenseNo}</td>
								<th style="text-align:right;">医师执业证书编号：</th>
								<td style="text-align:left;">&#12288;${doctor.qualifiedNo}</td>
							</tr>
							<tr>
								<th style="text-align:right;">所学专业：</th>
								<td style="text-align:left;">&#12288;<c:if test="${doctor.trainingSpeId eq '1'}">中医</c:if><c:if test="${doctor.trainingSpeId eq '2'}">中医全科</c:if><c:if test="${doctor.trainingSpeId eq '3'}">中医助理全科</c:if></td>
								<th style="text-align:right;">培训登记手册&#12288;<br/>完成情况：</th>
								<td style="text-align:left;">&#12288;<c:if test="${extInfo.handBookInfo eq 'Y'}">完成</c:if><c:if test="${extInfo.handBookInfo eq 'N'}">未完成</c:if></td>
							</tr>
							</tbody>
						</table>
					</div>
					<div class="div_table">
						<table border="0" cellpadding="0" cellspacing="0" class="base_info">
							<h4>审核意见</h4>
							<colgroup>
								<col width="25%"/>
								<col width="25%"/>
								<col width="25%"/>
								<col width="25%"/>
							</colgroup>
							<tbody>
							<tr>
								<th colspan="2" style="text-align:center;">培训基地初审意见：</th>
								<th colspan="2" style="text-align:center;">四川省中医药毕业后教育委员会办公室复审意见：</th>
							</tr>
							<tr><td colspan="2">我已审核该医师的结业考核资格相关材料原件，以上内容来源于上述材料，内容真实。</td><td colspan="2"></td></tr>
							<tr>
								<th style="text-align:left;">&#12288;审核人签名：</th>
								<th style="text-align:left;">&#12288;单位公章：</th>
								<th style="text-align:left;">&#12288;复审人签名：</th>
								<th style="text-align:left;">&#12288;单位公章：</th>
							</tr>
							<tr>
								<th colspan="2" style="text-align:right;">&#12288;&#12288;年&#12288;&#12288;月&#12288;&#12288;日&#12288;&#12288;</th>
								<th colspan="2" style="text-align:right;">&#12288;&#12288;年&#12288;&#12288;月&#12288;&#12288;日&#12288;&#12288;</th>
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
