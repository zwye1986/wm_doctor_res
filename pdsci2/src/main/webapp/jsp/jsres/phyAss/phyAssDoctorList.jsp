<script type="text/javascript" src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/detail.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript">

	function editPhyAssMain(type,planFlow) {
		var url = "<s:url value ='/jsres/phyAss/editPlannedReleaseMain'/>?type="+type+"&planFlow="+planFlow;
		jboxOpen(url, "查看培训计划", 1000, 700);
	}
</script>

<div class="search_table" style="width: 100%;padding: 0 20px">
		<table class="grid" style="width: 100%;" id="dataTable">
			<thead>
            <tr>
				<th style="min-width: 45px; max-width: 45px;" >姓名</th>
				<th style="min-width: 40px; max-width: 40px; " >性别</th>
				<th style="min-width: 100px; max-width: 100px; " >培训计划</th>
				<th style="min-width: 100px; max-width: 100px; " >基地名称</th>
				<th style="min-width: 60px; max-width: 60px; " >科室</th>
				<th style="min-width: 60px; max-width: 60px; " >培训专业</th>
                <th style="min-width: 60px; max-width: 60px;" >师资等级</th>
                <th style="min-width: 60px; max-width: 60px;" >证书编号</th>
                <th style="min-width: 80px; max-width: 80px;" >证书有效期</th>
                <th style="min-width: 50px; max-width: 50px;" >有效期倒计时（天）</th>
                <th style="min-width: 85px; max-width: 85px;" >操作</th>
            </tr>
			</thead>
			<tbody>
			<c:if test="${not empty doctorList}">
				 <c:forEach items="${doctorList}" var="d">
					 <tr class="fixTrTd">
						<td>${pdfn:replaceNameX(d.doctorName)}</td>
						<td>${d.sexName}</td>
						<td>
							<a onclick="editPhyAssMain('show','${d.planFlow}')"> ${d.planContent}</a>
						</td>
						<td>${d.orgName}</td>
						<td>${d.deptName}</td>
						<td>${d.speName}</td>
						<td>${d.gradeName}</td>
						<td>${d.certificateNo}</td>
						<td>${d.sendcertificateTime} <br>
								${pdfn:getAppointDate(d.sendcertificateTime,dayNum)}</td>
						<td>${pdfn:appointDaysBetweenTowDate(d.sendcertificateTime,dayNum)}</td>
						<td>
							<a id="viewImgLink" href="<s:url value='/jsres/phyAss/showGrade?recordFlow=${d.recordFlow}'/>" target="_blank">
								<input class="btn_green" style="margin-left: 0px;" type="button" value="查看"/>
							</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty doctorList}">
				<tr>
					<td colspan="16" >无记录！</td>
				</tr>
			</c:if>
			</tbody>
        </table>
    </div>
    <div class="page">
		<c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>	 
	</div>
      
