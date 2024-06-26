<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">
	function qieHuan(deptFlow){
		var url="<s:url value='/jsres/manage/publicOpen?deptFlow='/>"+deptFlow;
		location.href=url;
	}
	function operation(obj){
		var orgFlow=obj.value;
		if(obj.checked){
			var orgName=obj.name;
			var url="<s:url value='/jsres/manage/relationSave'/>?orgFlow="+orgFlow+"&deptFlow=${param.deptFlow}";
			jboxPost(url,{orgName:orgName},function(){
				
			},null,false);
		}else{
			var url="<s:url value='/jsres/manage/relationDelete'/>?orgFlow="+orgFlow+"&deptFlow=${param.deptFlow}";
			jboxPost(url,null,function(){
				
			},null,false);
		}
	}
	//模糊查询
	function likeSearch(name){
		if(name){
			$("[orgName]").hide();
			$("[orgName*='"+name+"']").show();
		}else{
			$("[orgName]").show();
		}
	}
</script>
<div class="main_bd" id="div_table_0" style="overflow:auto;height: 590px;">
	<div class="div_search" style="padding: 10px;">
	<form id="searchForm" action="<s:url value="/sys/dept/list/${sessionScope.deptListScope}" />" method="post" > 
		科室名称&nbsp;：<select class="select" onchange="qieHuan(this.value);">
			<c:forEach items="${sysDeptList}" var="sysDept">
				<option value="${sysDept.deptFlow}" <c:if test="${param.deptFlow eq sysDept.deptFlow}">selected</c:if>>${sysDept.deptName}</option>
			</c:forEach>
		</select>&#12288;&#12288;
		基地名称：<input type="text" name="orgName" value="${param.orgName}" onkeyup="likeSearch(this.value);" class="input" />
	</form>
    </div>
    <div class="div_search" style="padding: 10px;margin: 10px;">
			<c:forEach items="${sysOrgList}" var="sysOrg">
				<c:if test="${currOrgFlow != sysOrg.orgFlow}">
					<div style="width: 24%;float: left;" orgName="${sysOrg.orgName}"> 
						<label>
							<input type="checkbox" name="${sysOrg.orgName}" onclick="operation(this);" <c:if test="${map[sysOrg.orgFlow].relOrgFlow eq sysOrg.orgFlow}">checked</c:if>  value="${sysOrg.orgFlow}"/>
							${sysOrg.orgName}
						</label>
					</div>
				</c:if>
			</c:forEach>
				
	</div>
</div>