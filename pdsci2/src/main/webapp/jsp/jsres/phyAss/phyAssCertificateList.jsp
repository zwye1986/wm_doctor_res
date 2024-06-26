<script type="text/javascript" src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/detail.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript">
    function selectAll() {
        if($("#checkAll").is(':checked')){
            $(".check").prop("checked",true);
        }else{
            $(".check").prop("checked",false);
        }
    }

    function chooseGrade(recordFlow) {
		var url = "<s:url value ='/jsres/phyAss/chooseGrade'/>?recordFlow="+recordFlow;
		jboxOpen(url, "生成证书", 500, 220);
	}
</script>

	<div class="main_bd clearfix">
		<table class="grid" style="width: 100%;" id="dataTable">
			<thead>
            <tr>
				<th style="min-width: 40px;max-width: 40px"><input type="checkbox" id="checkAll" onchange="selectAll()"/></th>
				<th style="min-width: 60px; max-width: 60px;" >姓名</th>
				<th style="min-width: 40px; max-width: 40px; " >性别</th>
				<th style="min-width: 120px; max-width: 120px; " >培训计划</th>
				<th style="min-width: 100px; max-width: 100px; " >基地</th>
				<th style="min-width: 80px; max-width: 80px; " >培训专业</th>
				<th style="min-width: 80px; max-width: 80px; " >所属科室</th>
                <th style="min-width: 60px; max-width: 60px;" >师资等级</th>
                <th style="min-width: 90px; max-width: 90px;" >证书生成日期</th>
                <th style="min-width: 50px; max-width: 50px;" >状态</th>
                <th style="min-width: 96px; max-width: 96px;" >操作</th>
            </tr>
			</thead>
			<tbody>
			<c:if test="${not empty doctorList}">
				 <c:forEach items="${doctorList}" var="d">
					 <tr class="fixTrTd">
						 <td>
							 <input recordFlow="${d.recordFlow}" type="checkbox" class="check"/>
						 </td>
						<td>${pdfn:replaceNameX(d.doctorName)}</td>
						<td>${d.sexName}</td>
						<td>${d.planContent}</td>
						<td>${d.orgName}</td>
						<td>${d.speName}</td>
						<td>${d.deptName}</td>
						<td>${d.gradeName}</td>
						<td>${empty d.certificateStartTime ?"未生成":d.certificateStartTime}</td>
						<td>${d.gaincertificateName}</td>
						<td>
							<c:if test="${d.gaincertificateName eq '未生成'}">
								<input class="btn_green" style="margin-left: 0px;" type="button" value="生&#12288;成" onclick="chooseGrade('${d.recordFlow}')"/>
							</c:if>
							<c:if test="${d.gaincertificateName eq '已生成'}">
								<input class="btn_green" style="margin-left: 0px;" type="button" value="重新生成" onclick="chooseGrade('${d.recordFlow}')"/>
								<br>
								<a id="viewImgLink" href="<s:url value='/jsres/phyAss/showGrade?recordFlow=${d.recordFlow}'/>&type=show" target="_blank">
									<input class="btn_green"
										   style="margin-left: 0px;margin-top: 5px" type="button" value="查&#12288;&#12288;看"/>
								</a>
							</c:if>

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
      
