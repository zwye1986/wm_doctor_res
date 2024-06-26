<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
	$(document).ready(function(){
		<c:if test="${not empty list}">
			var style={"margin-top":"5px","width":"940px"};
			var options ={
				"colums":2//根据固定列的数量
			};
			var widths=['80','60','150','150','200','60','100','100','130','120','120','120'];
			$("#dataTable").Scorll(options,style,false,null);
		</c:if>
	});
	//所有详情
	function doctorPassedList(doctorFlow,recruitFlow){
		var hideApprove="hideApprove";
		var url = "<s:url value='/hbzy/manage/province/doctor/doctorPassedList'/>?info=${GlobalConstant.FLAG_Y}&liId="+recruitFlow+"&recruitFlow="+recruitFlow+"&openType=open&doctorFlow="+doctorFlow+"&hideApprove="+hideApprove;
		jboxOpen(url,"学员信息",1050,550);

	}
//毕业证书图片
function showCerificateImg(imgUrl)
{
	$("#imgUrl").removeAttr("src");
	if(imgUrl=="")
	{
		jboxTip("学员暂未上传毕业证书！");
		return false;
	}else{
		imgUrl="${sysCfgMap['upload_base_url']}/"+imgUrl;
		$("#imgUrl").attr("src",imgUrl);
		$("#imgUrlA").attr("href",imgUrl);
		jboxOpenContent($("#imgDiv").html(),"毕业证书",800,500);
	}
}
//审核
function chargeAuditApply(applyFlow)
{
	var url = "<s:url value='/hbzy/asseGlobal/chargeAuditApply?roleFlag=${param.roleFlag}&applyFlow='/>"+applyFlow;
	jboxOpen(url, "审核学员填报信息",800,500);
}
//报考材料
function showApplyImg(imgUrl1,imgUrl2)
{
	$("#imgUrl1").removeAttr("src");
	$("#imgUrl2").removeAttr("src");
	if(imgUrl1==""&&imgUrl2==""||imgUrl1=="暂无"&&imgUrl2=="")
	{
		jboxTip("学员暂未上传报考资格材料！");
		return false;
	}else{
		if(imgUrl1==""||imgUrl1=="暂无")
			$("#div1").hide();
		if(imgUrl2=="")
			$("#div2").hide();
		imgUrl1="${sysCfgMap['upload_base_url']}/"+imgUrl1;
		imgUrl2="${sysCfgMap['upload_base_url']}/"+imgUrl2;
		$("#imgUrl1").attr("src",imgUrl1);
		$("#imgUrl2").attr("src",imgUrl2);
		$("#imgUrlA1").attr("href",imgUrl1);
		$("#imgUrlA2").attr("href",imgUrl2);
		jboxOpenContent($("#imgDiv2").html(),"报考资格材料",800,500);
	}
}
	function tableFixed(div){
		$("#dateFixed,#topTitle").css("top",$(div).scrollTop()+"px");
		$("#deptFixed,#topTitle").css("left",$(div).scrollLeft()+"px");
	}
	function heightFiexed(){
		//修正高度
		var toFixedTd = $(".toFiexdDept");
		$(".fixedBy").each(function(i){
			$(toFixedTd[i]).height($(this).height());
		});
		var fixTrTd = $(".fixTrTd");
		var fixTrTh = $(".fixTrTh");
		var fixTd = $(".fix");
		$(fixTrTd).each(function(i)
		{
			var maxheight=-1;
			$(fixTrTd[i]).find(".by").each(function(){
				if($(this).height()>maxheight) maxheight=$(this).height();
			});
			if(maxheight!=-1) {
				$(fixTrTh[i]).find(".fix").each(function(){
					$(this).height(maxheight);
				});
			}
		});
	}

	onresize = function(){
		heightFiexed();
	};
</script>
<c:if test="${empty list}">
	<div class="search_table" style="width: 100%;">
		<table border="0" cellpadding="0" cellspacing="0" class="grid">
			<tr>
				<th>结业考核年份</th>
				<th>姓名</th>
				<th>性别</th>
				<th>身份证号</th>
				<th>人员类型</th>
				<th>培训基地</th>
				<th>工作单位</th>
				<th>培训专业</th>
				<th>最高学历</th>
				<th>培训年限</th>
				<th>培训开始时间</th>
				<th>结束培训时间</th>
				<th>状态</th>
				<th>基地审核意见</th>
				<th>省厅审核意见</th>
				<c:if test="${f eq 'Y'}">
					<th>操作</th>
				</c:if>
			</tr>
			<tr>
				<td colspan="13" >无记录！</td>
			</tr>
		</table>
	</div>
</c:if>

<c:if test="${not empty list}">
	<div class="main_bd clearfix" style="height: auto">
		<table class="grid" style="width: auto;" id="dataTable">
			<thead>
			<tr>
				<th style="min-width: 60px; max-width: 60px; "   class="toFiexdDept">结业考核年份</th>
				<th style="min-width: 60px; max-width: 60px; "   class="toFiexdDept">姓名</th>
				<th style="min-width: 60px; max-width: 60px; "   class="fixedBy">性别</th>
				<th style="min-width: 200px; max-width: 200px; " class="fixedBy">身份证号</th>
				<th style="min-width: 150px; max-width: 150px; " class="fixedBy">人员类型</th>
				<th style="min-width: 150px; max-width: 150px; " class="fixedBy">培训基地</th>
				<th style="min-width: 150px; max-width: 150px; " class="fixedBy">工作单位</th>
				<th style="min-width: 150px; max-width: 150px; " class="fixedBy">培训专业</th>
				<th style="min-width: 60px; max-width: 60px; "   class="fixedBy">最高学历</th>
				<th style="min-width: 60px; max-width: 60px; "   class="fixedBy">培训年限</th>
				<th style="min-width: 150px; max-width: 150px; " class="fixedBy">培训开始时间</th>
				<th style="min-width: 150px; max-width: 150px; " class="fixedBy">结束培训时间</th>
				<th style="min-width: 100px; max-width: 100px; "   class="fixedBy">状态</th>
				<th style="min-width: 150px; max-width: 150px; " class="fixedBy">基地审核意见</th>
				<th style="min-width: 150px; max-width: 150px; " class="fixedBy">省厅审核意见</th>
				<c:if test="${f eq 'Y'}">
					<th style="min-width: 100px; max-width: 100px; "   class="fixedBy">操作</th>
				</c:if>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="doctor">
				<tr class="fixTrTd">
					<td style="min-width: 60px; max-width: 60px; " class="by"> ${doctor.graduationYear} </td>
					<td style="min-width: 60px; max-width: 60px; " class="by"><a href="javascript:void(0);" onclick="doctorPassedList('${doctor.doctorFlow}','${doctor.recruitFlow}');">${doctor.userName}</a></td>
					<td style="min-width: 60px; max-width: 60px; " class="by"> ${doctor.sexName} </td>
					<td style="min-width: 200px; max-width: 200px; " class="by">${doctor.idNo}</td>
					<td style="min-width: 150px; max-width: 150px; " class="by">${doctor.doctorTypeName}</td>
					<td style="min-width: 150px; max-width: 150px; " class="by">${doctor.orgName}</td>
					<td style="min-width: 150px; max-width: 150px; " class="by">${doctor.workOrgName}</td>
					<td style="min-width: 150px; max-width: 150px; " class="by">${doctor.catSpeName}</td>
					<td style="min-width: 60px; max-width: 60px; " class="by">${doctor.educationName}</td>
					<td style="min-width: 60px; max-width: 60px; " class="by">${doctor.trainYear}</td>
					<td style="min-width: 60px; max-width: 60px; " class="by">${doctor.startDate}</td>
					<td style="min-width: 60px; max-width: 60px; " class="by">${doctor.endDate}</td>
					<td style="min-width: 100px; max-width: 100px; " class="by">${doctor.auditStatusName}</td>
					<td style="min-width: 100px; max-width: 100px; " class="by">${empty doctor.localReason ? '-' : doctor.localReason}</td>
					<td style="min-width: 100px; max-width: 100px; " class="by">${empty doctor.globalReason ? '-' : doctor.globalReason}</td>
					<c:if test="${f eq 'Y'}">

						<td style="min-width: 100px;max-width: 100px; " class="by">
							<c:if test="${ doctor.applyYear eq pdfn:getCurrYear() }">
								<c:if test="${ param.roleFlag eq GlobalConstant.USER_LIST_GLOBAL }">
									<c:if test="${(not empty doctor.globalAuditStatusId)}">
										<a class="btn"  onclick="chargeAuditApply('${doctor.applyFlow}');" style="margin-top: 5px;margin-bottom: 5px;">重新审核</a>
									</c:if>
									<c:if test="${( doctor.auditStatusId eq jszyResAsseAuditListEnumWaitGlobalPass.id)}">
										<a class="btn"  onclick="chargeAuditApply('${doctor.applyFlow}');" style="margin-top: 5px;margin-bottom: 5px;">审核</a>
									</c:if>
								</c:if>
								<c:if test="${ param.roleFlag eq GlobalConstant.USER_LIST_LOCAL  and org.orgFlow eq doctor.orgFlow }">
									<c:if test="${(not empty doctor.localAuditStatusId and empty doctor.globalAuditStatusId)}">
										<a class="btn"  onclick="chargeAuditApply('${doctor.applyFlow}');" style="margin-top: 5px;margin-bottom: 5px;">重新审核</a>
									</c:if>
									<c:if test="${( doctor.auditStatusId eq jszyResAsseAuditListEnumAuditing.id)}">
										<a class="btn"  onclick="chargeAuditApply('${doctor.applyFlow}');" style="margin-top: 5px;margin-bottom: 5px;">审核</a>
									</c:if>
								</c:if>
							</c:if>
						</td>
					</c:if>
				</tr>
			</c:forEach>
			</tbody>
		</table>

	</div>

</c:if>
    <div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage" />
	</div>
      
