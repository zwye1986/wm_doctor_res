<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
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
				"colums":1//根据固定列的数量
			};
			var widths=['80','60','150','150','200','60','100','100','130','120','120','120'];
			$("#dataTable").Scorll(options,style,false,null);
		</c:if>
	});
	//培训详情
	function showPeiDetail(recruitFlow,applyYear)
	{
		var url = "<s:url value='/hbres/asse/recruitDetail?recruitFlow='/>"+recruitFlow+"&applyYear="+applyYear;
		jboxOpen(url, "科室轮转详情",1330,500);
	}
	//成绩详情
	function showScore(scoreFlow,recruitFlow,applyYear)
	{
		var url = "<s:url value='/hbres/asse/showScore?recruitFlow='/>"+recruitFlow+"&scoreFlow="+scoreFlow+"&applyYear="+applyYear;
		jboxOpen(url, "公共课考核",800,500);
	}
	//所有详情
	function showAllInfo(doctorFlow,applyYear)
	{
		var url = "<s:url value='/hbres/asse/showAllInfoMain?doctorFlow='/>"+doctorFlow+"&applyYear="+applyYear;
		jboxOpen(url, "学员报考材料查看",1330,500);
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
	var url = "<s:url value='/hbres/asseGlobal/chargeAuditApply?applyFlow='/>"+applyFlow;
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
<div id="imgDiv" style="display: none;text-align: center;">
	<a id="imgUrlA" href="" target="_blank">
		<img id="imgUrl" height="380" width="780">
	</a>
</div>
<div id="imgDiv2" style="display: none;text-align: center;">
	<div style="overflow: auto;max-height: 430px;">
		<div id="div1" class="div_table" style="width: 90%;">
			<h4>资格证书</h4>
			<a id="imgUrlA1" href="" target="_blank">
				<img id="imgUrl1" height="300" width="700">
			</a>
		</div>
		<div id="div2" class="div_table" style="width: 90%;">
			<h4>特殊岗位证明材料</h4>
			<a id="imgUrlA2" href="" target="_blank">
				<img id="imgUrl2" height="300" width="700">
			</a>
		</div>
	</div>
</div>
<c:if test="${empty list}">
	<div class="search_table" style="width: 100%;">
		<table border="0" cellpadding="0" cellspacing="0" class="grid">
			<tr>
				<th>姓名</th>
				<th>培训基地</th>
				<th>培训专业</th>
				<th>报考专业</th>
				<th>培训起止时间</th>
				<th>学历</th>
				<th>毕业证书编号</th>
				<th>报考资格材料</th>
				<th>报考资格材料编码</th>
				<th>执业范围</th>
				<%--<th>公共科目成绩</th>--%>
				<th>状态</th>
				
				<c:if test="${f eq 'Y'}">
				<th>操作</th>
				</c:if>
			</tr>
			<tr>
				<td colspan="12" >无记录！</td>
			</tr>
		</table>
	</div>
</c:if>

<c:if test="${not empty list}">
	<div class="main_bd clearfix">
		<table class="grid" style="width: auto;" id="dataTable">
			<thead>
				<tr>
					<th style="min-width: 60px; max-width: 60px; "   class="toFiexdDept">姓名</th>
					<th style="min-width: 150px; max-width: 150px; " class="fixedBy">培训基地</th>
					<th style="min-width: 150px; max-width: 150px; " class="fixedBy">培训专业</th>
					<th style="min-width: 150px; max-width: 150px; " class="fixedBy">报考专业</th>
					<th style="min-width: 200px; max-width: 200px; " class="fixedBy">培训起止时间</th>
					<th style="min-width: 60px; max-width: 60px; "   class="fixedBy">学历</th>
					<th style="min-width: 150px; max-width: 150px; " class="fixedBy">毕业证书编号</th>
					<th style="min-width: 150px; max-width: 150px; " class="fixedBy">报考资格材料</th>
					<th style="min-width: 150px; max-width: 150px; " class="fixedBy">报考资格材料编码</th>
					<th style="min-width: 100px; max-width: 100px; " class="fixedBy">执业范围</th>
					<%--<th style="min-width: 150px; max-width: 150px; "   class="fixedBy">公共科目成绩</th>--%>
					<th style="min-width: 100px; max-width: 100px; "   class="fixedBy">状态</th>
					
					<c:if test="${f eq 'Y'}">
					<th style="min-width: 100px; max-width: 100px; "   class="fixedBy">操作</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="doctor">
					<tr class="fixTrTd">
						<td style="min-width: 60px; max-width: 60px; " class="by"><a href="javascript:void(0);" onclick="showAllInfo('${doctor.doctorFlow}','${doctor.applyYear}')">${doctor.userName}</a></td>
						<td style="min-width: 150px; max-width: 150px; " class="by">${doctor.orgName}</td>
						<td style="min-width: 150px; max-width: 150px; " class="by">${doctor.speName}</td>
						<td style="min-width: 150px; max-width: 150px; " class="by">${doctor.changeSpeName}</td>
						<td style="min-width: 200px; max-width: 200px; " class="by"><a href="javascript:void(0);" onclick="showPeiDetail('${doctor.doctorFlow}','${doctor.applyYear}')">${doctor.startDate}~${doctor.endDate}</a></td>
						<td style="min-width: 60px; max-width: 60px; " class="by">${doctor.educationName}</td>
						<td style="min-width: 150px; max-width: 150px; " class="by">
							<c:if test="${not empty doctor.certificateNo}">
								<a href="javascript:void(0);" onclick="showCerificateImg('${doctor.certificateUri}')">${doctor.certificateNo}</a>
							</c:if>
						</td>
						<td style="min-width: 150px; max-width: 150px; " class="by">
							<c:if test="${not empty doctor.qualificationMaterialName}">
								<a href="javascript:void(0);" onclick="showApplyImg('${doctor.qualificationMaterialUri}','${doctor.specialCertificationUri}')">
										${doctor.qualificationMaterialName}
								</a>
							</c:if>
						</td>
						<td style="min-width: 150px; max-width: 150px; " class="by">${doctor.qualificationMaterialCode}</td>
						<td style="min-width: 100px; max-width: 100px; " class="by">${doctor.practicingScopeName}</td>
						<%--<td style="min-width: 150px; max-width: 150px; " class="by"><a href="javascript:void(0);" onclick="showScore('${doctor.scoreFlow}','${doctor.recruitFlow}','${doctor.applyYear}')">${doctor.isPass}</a></td>--%>
						<td style="min-width: 100px; max-width: 100px; " class="by">
								${doctor.auditStatusName}
						</td>
						
						<c:if test="${f eq 'Y'}">
							<td style="min-width: 100px;max-width: 100px; " class="by">
								<c:if test="${ doctor.applyYear eq pdfn:getCurrYear() }">
									<c:if test="${(not empty doctor.globalAuditStatusId)}">
										<a class="btn"  onclick="chargeAuditApply('${doctor.applyFlow}');" style="margin-top: 5px;margin-bottom: 5px;">重新审核</a>
									</c:if>
									<c:if test="${( doctor.auditStatusId eq jsResAuditStatusEnumWaitGlobalPass.id)}">
										<a class="btn"  onclick="chargeAuditApply('${doctor.applyFlow}');" style="margin-top: 5px;margin-bottom: 5px;">审核</a>
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
		<pd:pagination-hbres toPage="toPage"/>
	</div>
      
