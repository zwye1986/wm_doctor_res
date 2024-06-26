<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	function chooseHospital(orgFlow){
		var url="<s:url value='/sczyres/manage/showAdmit'/>";
		jboxPostLoad('content' , url , {"orgFlow":orgFlow} , true);
	}
	function getInfo(doctorFlow){
		jboxOpen("<s:url value='/sczyres/doctor/getsingupinfofordialog'/>?userFlow="+doctorFlow,"人员信息",1000,550);
	}
	function chooseConfirm(Flag,orgFlow){
		var url="<s:url value='/sczyres/manage/showConfirm'/>";
		var requestData ={
				"Flag":Flag,
				"orgFlow":orgFlow
		}
		jboxPostLoad('content' , url ,requestData , true);
	}
	
	function searchDocRecruit(){
		var url="<s:url value='/sczyres/manage/showDocRecruit'/>?roleFlag=${param.roleFlag}&assignYear=${param.assignYear}";
		jboxPostLoad('content' , url ,$("#searchForm").serialize() , true);
	}
		
</script>
<div class="main_hd">
    <h2 class="underline">
        <span>基地录取人员情况表</span>
    </h2>
	<div class="div_search">
        <form id="searchForm">
		<c:if test="${param.roleFlag ne 'hospital'}">
        基地:
            <select name="orgFlow" onchange="searchDocRecruit();"  class="select" >
    	    <option value="" >请选择</option>
        	<c:forEach items="${hospitals}" var="hos">
  	    	    <option value="${hos.orgFlow}" <c:if test='${param.orgFlow eq hos.orgFlow}'>selected="selected"</c:if>>${hos.orgName}</option> 
       		</c:forEach>
   			</select>&#12288;
		</c:if>
		是否为指标学员：
		<select class="select" name="planFlag" id="planFlag" onchange="searchDocRecruit()">
			<option value="">全部</option>
			<option value="Y" ${param.planFlag eq 'Y'?'selected':''}>是</option>
			<option value="N" ${param.planFlag eq 'N'?'selected':''}>否</option>
		</select>&#12288;
        </form>
   	</div>
</div>
<div class="search_table">
    <table border="0" cellspacing="0" cellpadding="0" class="grid">
        <tr>
            <th>姓名</th>
            <th>性别</th>
            <th>身份证号</th>
            <th>人员类型</th>
            <th>学历</th>
            <th>填报专业</th>
            <th>人员信息</th>
    	</tr>
    	<c:forEach items="${doctorRecruitExts}" var="dor">
            <tr>
      			<td >${dor.doctor.doctorName}</td>
      		    <td>${dor.sysUser.sexName}</td>
      		    <td>${dor.sysUser.idNo}</td>
      		    <td>${dor.doctor.doctorTypeName}</td>
      		    <td>${dor.sysUser.educationName}</td>
     		    <td>${dor.catSpeName}-${dor.speName}</td>
      		    <td><a class="btn" onclick="getInfo('${dor.doctorFlow}');">详情</a></td>
   	       </tr>
        </c:forEach>
        <c:if test="${empty doctorRecruitExts}">
            <tr>
                <td colspan="7">无记录</td>
            </tr>
        </c:if>
    </table>
</div> 
    	
