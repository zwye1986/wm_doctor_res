<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>江苏省住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">	
/*function toPage(page) {
	var currentPage = "";
	if (!page || page != undefined) {
		currentPage = page;
	}
	if (page == undefined || page == "") {
		currentPage = 1;
	}
	$("#currentPage").val(page);
	var url="<s:url value='/jsres/base/baseInfo'/>?type=${param.type}&currentPage="+page;
	jboxLoad("hosContent",url,false);
	}*/
function toPage(page) {
	var currentPage = "";
	if (!page || page != undefined) {
		currentPage = page;
	}
	if (page == undefined || page == "") {
		currentPage = 1;
	}
	$("#currentPage").val(currentPage);
	search();
}
function search() {
	var url = "<s:url value='/jsres/base/baseInfo'/>?type=${param.type}";
	jboxPostLoad("hosContent", url, $("#searchForm").serialize(), false);
}
function searchJointOrg(orgFlow){
	var url="<s:url value='/jsres/base/searchJOrgInfo'/>?flag=${GlobalConstant.FLAG_Y}&orgFlow="+orgFlow;
	jboxOpen(url, "协同基地信息", 900,450);
}

function showOrg(orgFlow){
	jboxOpen("<s:url value='/jsres/base/main'/>?viewFlag=Y&baseFlow="+orgFlow,"查看基地信息",1000,600);
}
</script>
	<style>
		.grid th {

			border: 1px solid #e7e7eb;

		}
		.grid td {
			border: 1px solid #e7e7eb;
		}
	</style>
</head>

<body>
<div class="main_bd">
	<div class="div_search">
		<form id="searchForm">
			<input type="hidden" name="currentPage" id="currentPage" value=""/>
				<table>
					<tr>
						<td>
							基地名称：
							<input type="text" class="input" id="orgName" name="orgName"
								   style="width: 150px;"/>&nbsp;
						</td>
						<td>
							协同基地名称：
							<input type="text" class="input" id="jointOrgName" name="jointOrgName"
								   style="width: 150px;"/>&nbsp;
						</td>
						<td>
							<input class="btn_green" type="button" onclick="search()" value="查&#12288;询"/>&#12288;
						</td>
				</table>
		</form>
	</div>
	<div   class="search_table"	>
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
        	<colgroup>
				<c:if test="${param.type eq orgLevelEnumCountryOrg.id}">
					<col width="5%"  style="text-align: center;"/>
					<col width="10%"  style="text-align: center;"/>
					<col width="10%" style="text-align: center;"/>
					<col width="20%"  style="text-align: center;"/>
					<col width="10%"  style="text-align: center;"/>
					<col width="10%" style="text-align: center;"/>
					<col width="20%"  style="text-align: center;"/>
					<col width="15%" style="text-align: center;"/>
				</c:if>
				<c:if test="${param.type ne orgLevelEnumCountryOrg.id}">
					<col width="20%" style="text-align: center;"/>
					<col width="40%"  style="text-align: center;"/>
					<col width="40%" style="text-align: center;"/>
				</c:if>
        	</colgroup>
        	<tr>
				<c:if test="${param.type eq orgLevelEnumCountryOrg.id}">
					<th>序号</th>
					<th>地区</th>
					<th>基地编号</th>
					<th>基地名称</th>
					<th>基地级别</th>
					<th>协同基地编号</th>
					<th>协同基地名称</th>
					<th>协同基地级别</th>
				</c:if>
				<c:if test="${param.type ne orgLevelEnumCountryOrg.id}">
					<th>序号</th>
					<th>基地名称</th>
					<th>基地级别</th>
				</c:if>
            </tr>
            <c:forEach items="${orgList}" var="org" varStatus="s">
				<c:if test="${param.type eq orgLevelEnumCountryOrg.id}">
					<c:if test="${GlobalConstant.FLAG_Y eq jointFlagMap[org.orgFlow]   }">
						<c:set var="joinOrgs" value="${jointOrgMap[org.orgFlow]}"></c:set>
						<c:forEach items="${joinOrgs}" var="joinOrg" varStatus="js">
							<c:set var="rowspan" value="${fn:length(joinOrgs)}"></c:set>
							<c:if test="${js.first}">
								<tr>
									<td rowspan="${rowspan}">${(currentPage-1)*pageSize+ s.count}</td>
									<td rowspan="${rowspan}">${org.orgCityName}</td>
									<td rowspan="${rowspan}">${org.orgCode}</td>
									<td rowspan="${rowspan}"><a  onclick="showOrg('${org.orgFlow}');">${org.orgName}</a></td>
									<td rowspan="${rowspan}">${baseMap[org.orgFlow].baseGradeName}</td>
									<td>${org.orgCode}-${js.index+1}</td>
									<td><a  onclick="showOrg('${joinOrg.jointOrgFlow}');">${joinOrg.jointOrgName}</a></td>
									<td>${baseMap[joinOrg.jointOrgFlow].baseGradeName}</td>
								</tr>
							</c:if>
							<c:if test="${!js.first}">
								<tr>
									<td>${org.orgCode}-${js.index+1}</td>
									<td><a  onclick="showOrg('${joinOrg.jointOrgFlow}');">${joinOrg.jointOrgName}</a></td>
									<td>${baseMap[joinOrg.jointOrgFlow].baseGradeName}</td>
								</tr>
							</c:if>
						</c:forEach>
            		</c:if>
					<c:if test="${GlobalConstant.FLAG_Y ne jointFlagMap[org.orgFlow]  }">
						<tr>
							<td>${(currentPage-1)*pageSize+ s.count}</td>
							<td>${org.orgCityName}</td>
							<td>${org.orgCode}</td>
							<td><a  onclick="showOrg('${org.orgFlow}');">${org.orgName}</a></td>
							<td>${baseMap[org.orgFlow].baseGradeName}</td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
            		</c:if>
				</c:if>
				<c:if test="${param.type ne orgLevelEnumCountryOrg.id}">
					<tr>
						<td>${(currentPage-1)*pageSize+ s.count}</td>
						<td><a  onclick="showOrg('${org.orgFlow}');">${org.orgName}</a></td>
						<td>${baseMap[org.orgFlow].baseGradeName}</td>
					</tr>
				</c:if>
            </c:forEach>
           	<c:if test="${empty orgList}">
	            <tr>
					<td colspan="99">无记录</td>
	            </tr>
           	</c:if>
        </table>
	</div>
	<c:if test="${param.flag !=GlobalConstant.FLAG_Y}">
		<div class="page" style="padding-right: 40px;">
			<c:set var="pageView" value="${pdfn:getPageView(orgList)}" scope="request"></c:set>
			<pd:pagination-jsres toPage="toPage"/>	 
		</div>
	</c:if>
</div>
</body>
</html>