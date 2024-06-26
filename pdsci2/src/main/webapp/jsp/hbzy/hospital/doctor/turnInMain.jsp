<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="queryFont" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	$('#sessionNumber').datepicker({
		startView: 2, 
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
	if("${baseFlag}"=="0"){
		$("#baseFlag").hide();
	}
});

function getChangeOrgDetail(doctorFlow, recordFlow){
	var url = "<s:url value='/hbzy/manage/getChangeOrgDetail'/>?change=Y&openType=open&doctorFlow=" + doctorFlow + "&recordFlow="+recordFlow;
	jboxOpen(url, "详情", 1050, 550);
}

function turnInOrg(recordFlow, auditFlag, doctorFlow){
	var title = "不通过";
	var	changeStatusId = "${jszyResChangeApplyStatusEnumInApplyUnPass.id}";//转入审核不通过
	if("${GlobalConstant.FLAG_Y}" == auditFlag){
		title = "通过";
		changeStatusId = "${jszyResChangeApplyStatusEnumInApplyPass.id}";
	}
	if(title=="不通过"){
		var url="<s:url value='/hbzy/manage/auditInfo'/>?recordFlow="+recordFlow +"&changeStatusId="+changeStatusId+"&doctorFlow="+doctorFlow;
		jboxOpen(url, "审核意见", 680, 400);
	}else{
		var url="<s:url value='/hbzy/manage/info'/>?recordFlow="+recordFlow +"&changeStatusId="+changeStatusId+"&doctorFlow="+doctorFlow;
		jboxOpen(url, "相关信息", 600, 280);
	}
}

function toPage(page){
	if(page != undefined){
		$("#currentPage").val(page);			
	}
	searchIn();
}
function searchIn(){
	var url ="<s:url value='/hbzy/manage/turnInMain'/>";
	if($('#jointOrg').get(0) != undefined){
		$('#jointOrg').get(0).checked==true?$('#jointOrg').val("checked"):$('#jointOrg').val("");
	}
	jboxPostLoad("doctorContent", url, $("#inForm").serialize(), true);
}
</script>

<div class="main_bd">
	<div class="div_search">
		<form id="inForm">
			<input type="hidden" name="currentPage" id="currentPage"/>
			<table class="searchTable">
				<tr>
					<td class="td_left">姓&#12288;&#12288;名：</td>
					<td>
						<input type="text" name="doctorName" value="${param.doctorName}" class="input"
							   style="width: 100px;"/>&#12288;
					</td>
					<td class="td_left">年&#12288;&#12288;级：</td>
					<td>
						<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
							   class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>&#12288;
					</td>
					<td colspan="4">
						<c:if test="${countryOrgFlag eq 'Y'}">
							<input type="checkbox" id="jointOrg" name="jointOrg"
								   <c:if test="${param.jointOrg eq 'checked'}">checked="checked"</c:if> />
							<label for="jointOrg">&nbsp;协同基地</label>
						</c:if>
						&#12288;<input class="btn_brown" type="button" onclick="searchIn()" value="查&#12288;询"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div style="padding-bottom: 20px;">
		<div class="search_table">
			<table border="0" cellpadding="0" cellspacing="0" class="grid">
				<colgroup>
					<col width="10%"/>
					<col width="7%"/>
					<col width="20%"/>
					<col width="13%"/>
					<col width="10%"/>
					<col width="10%"/>
					<col width="30%"/>
				</colgroup>
				<thead>
				<tr>
					<th>姓名</th>
					<th>年级</th>
					<th>原培训基地</th>
					<th>审核状态</th>
					<th>审核时间</th>
					<th>附件</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${docOrgHistoryExtList }" var="docOrgHistoryExt">
					<tr>
						<td>${docOrgHistoryExt.resDoctor.doctorName}</td>
						<td>${docOrgHistoryExt.resDoctor.sessionNumber}</td>
						<td>${docOrgHistoryExt.historyOrgName}</td>
							<%-- <td>${docOrgHistoryExt.orgName}</td> --%>
						<td>${docOrgHistoryExt.changeStatusName}</td>
						<td>${pdfn:transDate(docOrgHistoryExt.inDate)}</td>
						<td>
							<c:if test="${not empty docOrgHistoryExt.speChangeApplyFile }">
								[<a href="${sysCfgMap['upload_base_url']}/${docOrgHistoryExt.speChangeApplyFile }" target="_blank">查看附件</a>]
							</c:if>
						</td>
						<td>
							<a class="btn"
							   onclick="getChangeOrgDetail('${docOrgHistoryExt.doctorFlow}','${docOrgHistoryExt.recordFlow}');">详情</a>
							<c:if test="${jszyResChangeApplyStatusEnumInApplyWaiting.id == docOrgHistoryExt.changeStatusId}">
								<a class="btn"
								   onclick="turnInOrg('${docOrgHistoryExt.recordFlow}','${GlobalConstant.FLAG_Y}','${docOrgHistoryExt.doctorFlow}')">通过</a>
								<a class="btn"
								   onclick="turnInOrg('${docOrgHistoryExt.recordFlow}','${GlobalConstant.FLAG_N}','${docOrgHistoryExt.doctorFlow}')">不通过</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>

				<c:if test="${empty docOrgHistoryExtList }">
					<tr>
						<td colspan="7">无记录</td>
					</tr>
				</c:if>
				</tbody>
			</table>

		</div>
		<div class="page" style="padding-right: 40px;">
			<c:set var="pageView" value="${pdfn:getPageView(docOrgHistoryExtList)}" scope="request"></c:set>
			<pd:pagination-jsres toPage="toPage"/>
		</div>

	</div>

</div>
