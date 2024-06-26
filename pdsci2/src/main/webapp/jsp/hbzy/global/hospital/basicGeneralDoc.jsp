<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<title>湖北省中医住院医师规范化培训管理平台</title>
	<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">	
function toPage(page) {
	$("#currentPage").val(page);
	var url="<s:url value='/hbzy/base/baseInfo'/>?type=${param.type}&currentPage="+page;
	jboxLoad("hosContent",url,false);
	} 
function searchJointOrg(orgFlow){
	var url="<s:url value='/hbzy/base/searchJOrgInfo'/>?flag=${GlobalConstant.FLAG_Y}&orgFlow="+orgFlow;
	jboxOpen(url, "协同基地信息", 900,450);
}
</script>
<style>
	.grid td {
		border: 1px solid #e7e7eb;
	}
</style>
</head>

<body>
<div class="main_bd">
	<div class="div_search">
	</div>
	<div  <c:if test="${param.flag eq GlobalConstant.FLAG_Y }">class="infoAudit"style="padding:0 30px;"</c:if> 
	    <c:if test="${param.flag !=GlobalConstant.FLAG_Y }">class="search_table"</c:if>
	>
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
        	<colgroup>
        		<col width="5%"  style="text-align: center;"/>
        		<col width="10%"  style="text-align: center;"/>
        		<col width="20%"  style="text-align: center;"/>
        		<col width="10%"  style="text-align: center;"/>
        		<col width="10%"  style="text-align: center;"/>
        		<col width="15%"  style="text-align: center;"/>
        		<col width="20%"  style="text-align: center;"/>
        	</colgroup>
        	<tr>
                <th>序号</th>
                <th>编号</th>
                <th>基地名称</th>
                <th>地区</th>
                <th>基地级别</th>
                <th>协同基地编号</th>
                <th>协同基地名称</th>
                <th>协同基地级别</th>
            </tr>
            <c:forEach items="${orgList}" var="org" varStatus="status">
				<c:set var="rowspan" value="${fn:length(jointMap.get(org.orgFlow))}"></c:set>
					<tr>
						<td rowspan="${rowspan eq 0?1:rowspan}">${status.index +1}</td>
						<td rowspan="${rowspan eq 0?1:rowspan}">${org.orgCode}</td>
						<td rowspan="${rowspan eq 0?1:rowspan}">${org.orgName}</td>
						<td rowspan="${rowspan eq 0?1:rowspan}">${org.orgCityName}</td>
						<td rowspan="${rowspan eq 0?1:rowspan}">${org.orgLevelName}</td>
						<c:forEach items="${jointMap.get(org.orgFlow)}" var="jointOrg"   varStatus="s">
							<c:if test="${s.first}">
								<td>${jointOrg.jointOrgCode}</td>
								<td>${jointOrg.jointOrgName}</td>
								<td>${jointOrg.jointOrgLevelName}</td>
								</tr>
							</c:if>
							<c:if test="${!s.first}">
								<tr>
									<td>${jointOrg.jointOrgCode}</td>
									<td>${jointOrg.jointOrgName}</td>
									<td>${jointOrg.jointOrgLevelName}</td>
								</tr>
							</c:if>
						</c:forEach>
				<c:if test="${empty jointMap.get(org.orgFlow)}">
						<td>——</td>
						<td>——</td>
						<td>——</td>
					</tr>
				</c:if>


			</c:forEach>
           	<c:if test="${empty orgList}">
	            <tr>
	            	<td colspan="0">无记录</td>
	            </tr>
           	</c:if>
        </table>
		<c:if test="${param.flag !=GlobalConstant.FLAG_Y}">
			<div class="page" style="padding-right: 40px;">
				<c:set var="pageView" value="${pdfn:getPageView(orgList)}" scope="request"></c:set>
				<pd:pagination-jszy toPage="toPage"/>
			</div>
		</c:if>
	</div>

</div>
</body>
</html>