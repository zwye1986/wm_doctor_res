<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
</jsp:include>

	<script type="text/javascript" src="<s:url value='/js/jquery.jqprint-0.3.js'/>"></script>
<style type="text/css">

	.basic{
		border:1px solid #bbbbbb;
	}
	.basic th,.basic td { border-right:1px solid #bbbbbb;line-height: 130%;height: 25px;}
	.basic tbody th { background:#f9f9f9; color:#333;text-align:right;line-height: 130%;height: 25px;}
	.basic td { text-align:left;line-height: 130%;height: 25px;}
</style>
<script type="text/javascript">
//	$(document).ready(function() {
////		setTimeout(function(){
//			window.print();
////		},3000);
//	});
</script>
</head>
<body>
<div >
	<div style="text-align:center;font-size: 14px;width: 100%;">
		广州医科大学攻读<c:if test="${param.educationType eq 'master'}">硕士</c:if><c:if test="${param.educationType eq 'doctor'}">博士</c:if>
		<c:if test="${empty param.educationType}">${resume.educationType eq 'doctor'?'博士':'硕士'}</c:if>研究生学籍登记表
	</div>
	<div><table style="width:100%;font-size: 12px;">
		<tr>
		<c:if test="${param.educationType eq 'master'}">
			<td style="width:25%;">导师单位：${resume.tutorUnit}</td>
			<td style="width:25%;">学号：${resume.studentId}</td>
			<td style="width:25%;">准考证号：${resume.ticketNumber}</td>
			<td style="width:25%;">学位类型：<c:if test="${resume.degreeType eq 'major'}">专业学位</c:if><c:if test="${resume.degreeType eq 'academic'}">学术学位</c:if></td>
		</c:if>
		<c:if test="${param.educationType eq 'doctor'}">
			<td style="width:33%;">导师单位：${resume.tutorUnit}</td>
			<td style="width:33%;">学号：${resume.studentId}</td>
			<td style="width:33%;">学位类型：学术学位</td>
		</c:if>
		<c:if test="${empty param.educationType && (resume.educationType eq 'master' || empty resume.educationType)}">
			<td style="width:25%;">导师单位：${resume.tutorUnit}</td>
			<td style="width:25%;">学号：${resume.studentId}</td>
			<td style="width:25%;">准考证号：${resume.ticketNumber}</td>
			<td style="width:25%;">学位类型：<c:if test="${resume.degreeType eq 'major'}">专业学位</c:if><c:if test="${resume.degreeType eq 'academic'}">学术学位</c:if></td>
		</c:if>
		<c:if test="${empty param.educationType && resume.educationType eq 'doctor'}">
			<td style="width:33%;">导师单位：${resume.tutorUnit}</td>
			<td style="width:33%;">学号：${resume.studentId}</td>
			<td style="width:33%;">学位类型：学术学位</td>
		</c:if>
	</tr></table></div>
	<div style="width:100%;">
		<table class="basic" style="width:100%;font-size: 11px;">
			<tr>
				<th width="50px">姓名</th>
				<td width="50px">${resume.userName}</td>
				<th width="50px">姓名拼音</th>
				<td width="50px">${resume.nameSpell}</td>
				<th width="50px">性别</th>
				<td width="50px">${resume.sexName eq 'man'?'男':''}${resume.sexName eq 'woman'?'女':''}${resume.sexName eq 'Man'?'男':''}${resume.sexName eq 'Woman'?'女':''}</td>
				<th width="50px">民族</th>
				<td width="50px">${resume.nation}</td>
				<th width="50px">婚否</th>
				<td width="50px">${resume.marriage eq 'Y'?'是':''}${resume.marriage eq 'N'?'否':''}</td>
				<td rowspan="3" colspan="3" style="width:100px;text-align:center;">
					<img src="${sysCfgMap['upload_base_url']}/${resume.headImgUrl}" width="80px;" height="100px;" onerror="this.src='<s:url value="/jsp/xjgl/images/up-pic.jpg"/>'"/>
				</td>
			</tr>
			<tr>
				<th width="50px">出生日期</th>
				<td width="50px">${resume.birthDate}</td>
				<th width="50px">导师</th>
				<td>${resume.tutor}</td>
				<th width="50px">专业</th>
				<td> ${resume.speName}</td>
				<th width="50px">年级</th>
				<td>${resume.grade}</td>
				<th width="50px">手机</th>
				<td >${resume.mobilePhone}</td>
			</tr>
			<tr>
				<th width="50px">户口是否转入学校</th>
				<td>${resume.hkInSchool eq 'Y'?'是':''}${resume.hkInSchool eq 'N'?'否':''}</td>
				<th width="50px">户口类型</th>
				<td>${resume.hkType eq 'N'?'非农业':''}${resume.hkType eq 'Y'?'农业':''}</td>
				<th width="50px">籍贯</th>
				<td colspan="3">
					${resume.nativePlaceProvince}&emsp;${resume.nativePlaceCity}&emsp;${resume.nativePlaceArea}&emsp;${resume.nativePlaceCountry}乡
				</td>
				<th width="50px">是否应届</th>
				<td>${resume.whetherCurrent eq 'Y'?'是':''}${resume.whetherCurrent eq 'N'?'否':''}</td>
			</tr>
			<tr>
				<th width="50px">政治面貌</th>
				<td>${resume.politicalOutlook}</td>
				<th width="50px">身份证号</th>
				<td colspan="3">${resume.idNumber}</td>
				<th width="50px">生源地</th>
				<td colspan="3">
					<%--${resume.studentSourceArea}--%>
					${resume.birthPlaceProvince}省${resume.birthPlacePrefectureLevelCity}地级市${resume.birthPlaceCountyLevelCity}县级市/县
				</td>
				<th width="50px">出生地</th>
				<td colspan="2">${resume.homePlace}</td>
			</tr>
			<tr>
				<th width="50px">入学时间</th>
				<td colspan="3">${resume.entranceTime}</td>
				<th width="50px">家庭通讯地址</th>
				<td colspan="5">${resume.homeAddress}</td>
				<th width="50px">邮政编码</th>
				<td colspan="2">${resume.postCode}</td>
			</tr>
			<tr>
				<th width="50px">家庭电话</th>
				<td colspan="2">${resume.homePhone}</td>
				<th width="50px">培养方式</th>
				<td>${resume.cultureType eq 'Y'?'定向':''}${resume.cultureType eq 'N'?'非定向':''}</td>
				<th width="50px" colspan="2">是否华侨港澳台学生</th>
				<td colspan="2">${resume.overseasChinese eq 'Y'?'是':''}${resume.overseasChinese eq 'N'?'否':''}</td>
				<th width="50px">特长</th>
				<td colspan="3">${resume.specialty}</td>
			</tr>
			<tr>
				<th width="50px">最后学历</th>
				<td colspan="12">
					${resume.finalEducationYear}年毕业于${resume.finalEducationSchool}大学${resume.finalEducationCollege}学院${resume.finalEducationDepartment}系${resume.finalEducationSpe}专业（修学年限${resume.finalEducationDropOutYear}年）
				</td>
			</tr>
			<tr><th style="text-align:left;"  colspan="13">学历及经历（从初中到现在）</th></tr>
			<tr>
				<th style="text-align:center;" colspan="3">起止年月</th>
				<th style="text-align:center;" colspan="4">学校或单位</th>
				<th style="text-align:center;" colspan="2">职务</th>
				<th style="text-align:center;" colspan="4">单位地址</th>
			</tr>
			<c:set var="educationCount" value="0"/>
			<c:forEach items="${resume.educationList}" var="edu" varStatus="i">
				<c:set var="educationCount" value="${educationCount+1}" />
				<c:if test="${not empty edu.beginAndEndDate and not empty edu.schoolAndUnit}">
				<tr>
					<td colspan="3">${edu.beginAndEndDate}</td>
					<td colspan="4">${edu.schoolAndUnit}</td>
					<td colspan="2">${edu.postName}</td>
					<td colspan="4">${edu.unitAddress}</td>
				</tr>
				</c:if>
			</c:forEach>
			<%--<c:forEach begin="${educationCount}" end="4" var="index">--%>
				<%--<tr>--%>
					<%--<td colspan="2"></td>--%>
					<%--<td colspan="4"></td>--%>
					<%--<td colspan="2"></td>--%>
					<%--<td colspan="4"></td>--%>
				<%--</tr>--%>
			<%--</c:forEach>--%>
			<tr><th style="text-align:left;" colspan="13">家庭成员</th></tr>
			<tr>
				<th style="text-align:center;">姓名</th>
				<th style="text-align:center;">年龄</th>
				<th style="text-align:center;">与本人关系</th>
				<th style="text-align:center;">政治面貌</th>
				<th style="text-align:center;" colspan="3">工作单位</th>
				<th style="text-align:center;">职务</th>
				<th style="text-align:center;" colspan="3">单位地址</th>
				<th style="text-align:center;" colspan="2">手机电话</th>
			</tr>
			<c:set var="familyCount" value="0"/>
			<c:forEach items="${resume.familyList}" var="fam" varStatus="i">
				<c:set var="familyCount" value="${familyCount+1}" />
				<c:if test="${not empty fam.name and not empty fam.relation}">
				<tr>
					<td>${fam.name}</td>
					<td>${fam.age}</td>
					<td>${fam.relation}</td>
					<td>${fam.politics}</td>
					<td colspan="3">${fam.workUnit}</td>
					<td>${fam.postName}</td>
					<td colspan="3">${fam.unitAddress}</td>
					<td colspan="2">${fam.mobilePhone}</td>
				</tr>
				</c:if>
			</c:forEach>
			<%--<c:forEach begin="${familyCount}" end="4" var="index">--%>
				<%--<tr>--%>
					<%--<td></td>--%>
					<%--<td></td>--%>
					<%--<td></td>--%>
					<%--<td></td>--%>
					<%--<td colspan="3"></td>--%>
					<%--<td></td>--%>
					<%--<td colspan="3"></td>--%>
					<%--<td></td>--%>
				<%--</tr>--%>
			<%--</c:forEach>--%>
			<%--<c:if test="${roleFlag eq 'doctor' && empty registerFlag}">--%>
			<tr><th style="text-align:left;"  colspan="13">学籍异动</th></tr>
				<tr>
					<th style="text-align:center;">年</th>
					<th style="text-align:center;">月</th>
					<th style="text-align:center;">日</th>
					<th style="text-align:center;" colspan="2">异动</th>
					<th style="text-align:center;">学年度</th>
					<th style="text-align:center;" colspan="4">原因</th>
					<th style="text-align:center;" colspan="3">备注</th>
				</tr>
				<c:set var="changeCount" value="0"/>
				<c:forEach items="${resume.changeList}" var="change" varStatus="i">
					<c:set var="changeCount" value="${changeCount+1}" />
					<c:if test="${not empty change.year and not empty change.month}">
						<tr>
							<td>${change.year}</td>
							<td>${change.month}</td>
							<td>${change.day}</td>
							<td colspan="2">${change.changeItem}</td>
							<td>${change.termYear}</td>
							<td colspan="4">${change.reason}</td>
							<td colspan="3">${change.memo}</td>
						</tr>
					</c:if>

				</c:forEach>
				<%--<c:forEach begin="${changeCount}" end="1" var="index">--%>
					<%--<tr>--%>
						<%--<td></td>--%>
						<%--<td></td>--%>
						<%--<td></td>--%>
						<%--<td colspan="2"></td>--%>
						<%--<td></td>--%>
						<%--<td colspan="4"></td>--%>
						<%--<td colspan="2"></td>--%>
					<%--</tr>--%>
				<%--</c:forEach>--%>
			<tr><th style="text-align:left;"  colspan="13">奖惩情况</th></tr>
				<tr>

					<th style="text-align:center;" colspan="2">日期</th>
					<th style="text-align:center;">级别</th>
					<th style="text-align:center;" colspan="3">名称</th>
					<th style="text-align:center;">单位</th>
					<th style="text-align:center;" colspan="4">原因及说明</th>
					<th style="text-align:center;" colspan="2">备注</th>
				</tr>
				<c:set var="bonusPenaltyCount" value="0"/>
				<c:forEach items="${resume.bonusPenaltyList}" var="bonusPenalty" varStatus="i">
					<c:set var="bonusPenaltyCount" value="${bonusPenaltyCount+1}" />
					<c:if test="${not empty bonusPenalty.date and not empty bonusPenalty.name}">
						<tr>
							<td colspan="2">${bonusPenalty.date}</td>
							<td>${bonusPenalty.level}</td>
							<td colspan="3">${bonusPenalty.name}</td>
							<td>${bonusPenalty.unit}</td>
							<td colspan="4">${bonusPenalty.reason}</td>
							<td colspan="2">${bonusPenalty.memo}</td>
						</tr>
					</c:if>

				</c:forEach>
				<%--<c:forEach begin="${bonusPenaltyCount}" end="2" var="index">--%>
					<%--<tr>--%>
						<%--<td colspan="2"></td>--%>
						<%--<td></td>--%>
						<%--<td colspan="2"></td>--%>
						<%--<td></td>--%>
						<%--<td colspan="4"></td>--%>
						<%--<td colspan="2"></td>--%>
					<%--</tr>--%>
				<%--</c:forEach>--%>
			<%--</c:if>--%>
		</table>
	</div>

</div>
</body>
</html>
