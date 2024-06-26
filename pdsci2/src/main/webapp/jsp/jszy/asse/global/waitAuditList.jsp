<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
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
		$("#dataTable").Scorll(options,style,false,null);
	</c:if>
	});
	//所有详情

	function doctorPassedList(doctorFlow,recruitFlow){
		var hideApprove="hideApprove";
		var url = "<s:url value='/jszy/manage/province/doctor/doctorPassedList'/>?info=${GlobalConstant.FLAG_Y}&liId="+recruitFlow+"&recruitFlow="+recruitFlow+"&openType=open&doctorFlow="+doctorFlow+"&hideApprove="+hideApprove;
		jboxOpen(url,"学员信息",1050,600);

	}
	//审核
	function chargeAuditApply(applyFlow)
	{
		var url = "<s:url value='/jszy/asseGlobal/chargeAuditApply?roleFlag=${param.roleFlag}&applyFlow='/>"+applyFlow;
		jboxOpen(url, "审核学员填报信息",800,500);
	}
function selectAll(exp){
	$("td input[type='checkbox'][name='applyFlow']").attr("checked","checked");
}
function Contrary(exp){
	var selects = $("td input[type='checkbox'][name='applyFlow']");
	for(var i=0;i<selects.length;i++){
		var exp =$(selects[i]).attr("checked");
		if(exp=="checked"){
			$(selects[i]).attr("checked",false);
		}else {
			$(selects[i]).attr("checked","checked");
		}
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
				<c:if test="${f eq 'Y'}">
					<th>操作</th>
				</c:if>
			</tr>
			<tr>
				<td colspan="99" >无记录！</td>
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
				<th style="min-width: 60px; max-width: 60px; "   class="fixedBy">是否补考</th>
				<th style="min-width: 150px; max-width: 150px; "   class="fixedBy">补考类型</th>
				<th style="min-width: 80px; max-width: 80px; "   class="fixedBy">考试批次</th>
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
					<td style="min-width: 60px; max-width: 60px; " class="by">${doctor.makeUp eq 'Y' ? '是':(doctor.makeUp eq 'N' ? '否':'') }</td>
					<td style="min-width: 60px; max-width: 60px; " class="by">
						<c:if test="${doctor.makeUp eq 'Y'}">
							<c:if test="${doctor.isTheroy eq 'Y'}">
								理论补考
							</c:if>
							<c:if test="${doctor.isSkill eq 'Y' and doctor.isTheroy eq 'Y'}">
								/
							</c:if>
							<c:if test="${doctor.isSkill eq 'Y'}">
								技能补考
							</c:if>
						</c:if>
					</td>
					<td style="min-width: 80px; max-width: 80px; " class="by">
						<c:if test="${doctor.isFive eq 'Y'}">
							五月
						</c:if>
						<c:if test="${doctor.isTen eq 'Y' and doctor.isFive eq 'Y'}">
							/
						</c:if>
						<c:if test="${doctor.isTen eq 'Y'}">
							十月
						</c:if>
					</td>
					<c:if test="${f eq 'Y'}">
						<td style="min-width: 100px; max-width: 100px; " class="by">
							<c:if test="${ doctor.applyYear eq pdfn:getCurrYear() }">
								<c:if test="${ param.roleFlag eq GlobalConstant.USER_LIST_GLOBAL }">
									<c:if test="${( doctor.auditStatusId eq jszyResAsseAuditListEnumWaitGlobalPass.id)}">
										<a class="btn"  onclick="chargeAuditApply('${doctor.applyFlow}');" style="margin-top: 5px;margin-bottom: 5px;">审核</a>
									</c:if>
								</c:if>
								<c:if test="${ param.roleFlag eq GlobalConstant.USER_LIST_CHARGE }">
									<c:if test="${( doctor.auditStatusId eq jszyResAsseAuditListEnumWaitChargePass.id)}">
										<a class="btn"  onclick="chargeAuditApply('${doctor.applyFlow}');" style="margin-top: 5px;margin-bottom: 5px;">审核</a>
									</c:if>
								</c:if>
								<c:if test="${ param.roleFlag eq GlobalConstant.USER_LIST_LOCAL  and org.orgFlow eq doctor.orgFlow}">
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
		<pd:pagination-jsres toPage="toPage"/>
	</div>
      
