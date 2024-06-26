<script type="text/javascript" src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/detail.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript">
$(document).ready(function(){
			<c:if test="${not empty doctorList}">
			var style={"margin-top":"5px","width":"auto","margin-left":"0px"};
			var options ={
				<c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
				"colums":5//根据固定列的数量
				</c:if>
				<c:if test="${roleFlag ne GlobalConstant.USER_LIST_GLOBAL}">
				"colums":4//根据固定列的数量
				</c:if>
			};
			$("#dataTable").Scorll(options,style,false,null);
			</c:if>
});
function chargeCertificateIssuingStatus(recruitFlow,certificateIssuingStatus) {
	var flag = '';
	if (certificateIssuingStatus == '未发放') {
		flag = 'N';
	} else if (certificateIssuingStatus == '已发放') {
		flag = 'Y';
	}
	var url = "<s:url value='/jsp/jsres/global/certificateManage/chargeCertificateIssuingStatus.jsp'/>?recruitFlow=" + recruitFlow +"&flag=" +flag;
	jboxOpen(url, "更改", 400, 200);
}
</script>
<c:if test="${empty doctorList}">
	<div class="main_bd clearfix" style="width: 100%;">
	<table border="0" cellpadding="0" cellspacing="0" class="grid" >
		<tr>
			<c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL and param.tabTag eq 'CertificateSend'}">
				<th>操作</th>
			</c:if>
			<th>证书发放<br>状态</th>
			<th>证书<br>样式</th>
			<th>证书<br>编号</th>
			<th>姓名</th>
			<th>证件<br>号码</th>
			<th>基地审核<br>意见</th>
			<th>市局审核<br>意见</th>
			<th>省厅审核<br>意见</th>
			<th>理论考试<br>编号</th>
			<th>技能考试<br>编号</th>
			<th>合格<br>批次</th>
			<th>年级</th>
			<th>地市</th>
			<th>培训<br>类别</th>
			<th>培训<br>基地</th>
			<th>培训<br>专业</th>
		</tr>
		<tr>
			<td colspan="16" >无记录！</td>
		</tr>
	</table>
</c:if>
<c:if test="${not empty doctorList}">
	<div class="main_bd clearfix">
		<table class="grid" style="width: auto;" id="dataTable">
			<thead>
            <tr>
				<c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL and param.tabTag eq 'CertificateSend'}">
					<th style="min-width: 85px; max-width: 85px; "   class="toFiexdDept">操作</th>
				</c:if>
				<th style="min-width: 60px; max-width: 60px; " class="fixedBy">证书发放状态</th>
				<th style="min-width: 60px; max-width: 60px;" class="fixedBy">证书样式</th>
				<th style="min-width: 150px; max-width: 150px; " class="fixedBy">证书编号</th>
				<th style="min-width: 60px; max-width: 60px; " class="fixedBy">姓名</th>
				<th style="min-width: 150px; max-width: 150px; " class="fixedBy">证件号码</th>
				<th style="min-width: 150px; max-width: 150px; " class="fixedBy">基地审核意见</th>
				<th style="min-width: 150px; max-width: 150px; " class="fixedBy">市局审核意见</th>
				<th style="min-width: 150px; max-width: 150px; " class="fixedBy">省厅审核意见</th>
				<th style="min-width: 60px; max-width: 60px;" class="fixedBy">理论考试编号</th>
				<th style="min-width: 60px; max-width: 60px;" class="fixedBy">技能考试编号</th>
				<th style="min-width: 60px; max-width: 60px; " class="fixedBy">合格批次</th>
				<th style="min-width: 60px; max-width: 60px; " class="fixedBy">年级</th>
				<th style="min-width: 60px; max-width: 60px; " class="fixedBy">地市</th>
				<th style="min-width: 60px; max-width: 60px; " class="fixedBy">培训类别</th>
				<th style="min-width: 150px; max-width: 150px; " class="fixedBy">培训基地</th>
                <th style="min-width: 60px; max-width: 60px;" class="fixedBy">培训专业</th>
            </tr>
			</thead>
			<tbody>
             <c:forEach items="${doctorList}" var="doctor">
				 <c:set value="SkillScore${doctor.recruitFlow}" var="skillTestId"></c:set>
				 <c:set value="TheoryScore${doctor.recruitFlow}" var="TheoryTestId"></c:set>
	             <tr class="fixTrTd">
					 <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL and param.tabTag eq 'CertificateSend'}">
						 <td style="min-width: 85px; max-width: 85px;"   class="by">
	<%--						 <a style="cursor: pointer;" href="<s:url value='/jsres/certificateManage/showCertificate?recruitFlow=${doctor.recruitFlow}'/>" target="_blank"><img src="<s:url value='/jsp/jsres/images/huixingzhen.png'/>"/></a>--%>
							 <a class="btn"   onclick="chargeCertificateIssuingStatus('${doctor.recruitFlow}','${doctor.certificateIssuingStatus}');" style="margin-top: 5px;margin-bottom: 5px;width: 30px">更改</a>
						 </td>
					 </c:if>
					 <td style="min-width: 60px; max-width: 60px;"   class="by text_hidden">${doctor.certificateIssuingStatus}</td>
					 <td style="min-width: 60px; max-width: 60px;" class="by text_hidden" >
						 <a style="cursor: pointer;"
							href="<s:url value='/jsres/certificateManage/showCertificateNew?recruitFlow=${doctor.recruitFlow}'/>"
							target="_blank">查看</a>
					 </td>
					 <td style="min-width: 150px; max-width: 60px;" class="by text_hidden" title="${doctor.graduationCertificateNo}">${doctor.graduationCertificateNo}</td>
					 <td style="min-width: 60px; max-width: 60px;" class="by text_hidden" title="${doctor.sysUser.userName}">${doctor.sysUser.userName}</td>
					 <td style="min-width: 150px; max-width: 60px;" class="by text_hidden">${doctor.sysUser.idNo}</td>
					 <td style="min-width: 150px; max-width: 60px;" class="by text_hidden" title="${doctor.localReason}">${doctor.localReason}</td>
					 <td style="min-width: 150px; max-width: 60px;" class="by text_hidden" title="${doctor.cityReason}">${doctor.cityReason}</td>
					 <td style="min-width: 150px; max-width: 60px;" class="by text_hidden" title="${doctor.globalReason}">${doctor.globalReason}</td>
					 <td style="min-width: 60px; max-width: 60px;" class="by text_hidden">${testIdMap[TheoryTestId]}</td>
					 <td style="min-width: 60px; max-width: 60px;" class="by text_hidden">${testIdMap[skillTestId]}</td>
					 <td style="min-width: 60px; max-width: 60px;" class="by text_hidden">${doctor.qualifiedId}</td>
					 <td style="min-width: 60px; max-width: 60px;" class="by text_hidden">${doctor.sessionNumber}</td>
					 <td style="min-width: 60px; max-width: 60px;" class="by text_hidden">${doctor.orgCityName}</td>
					 <td style="min-width: 60px; max-width: 60px;" class="by text_hidden">${doctor.catSpeName}</td>
					 <td style="min-width: 150px; max-width: 60px;" class="by text_hidden" title="${doctor.orgName}">${doctor.orgName}</td>
					 <td style="min-width: 60px; max-width: 60px;" class="by text_hidden" title="${doctor.speName}">${doctor.speName}</td>
	            </tr>
            </c:forEach>
			</tbody>
        </table>
    </div>
</c:if>
    <div class="page">
		<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>	 
	</div>
      
