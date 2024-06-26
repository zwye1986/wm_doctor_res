<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<title></title>
<jsp:include page="/jsp/njmuedu/htmlhead-njmuedu.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="teacherMien" value="true"/>
</jsp:include>
<script type="text/javascript">
function toPage(page) {
	if(page!=undefined){
		$("#currentPage").val(page);			
	}
	$("#searchForm").submit();
}
</script>
</head>
<body style="background:#f4f4f4;">
<jsp:include page="/jsp/njmuedu/include/top.jsp"/>
  <div class="cbody clearfix">
 
 <div class="teacher-list-top text-center">
  <h1>辛勤的园丁<span>Our Instructors</span></h1>
  <div>行是知之始，知是行之成——陶行知</div>
 </div> 
 
 <div class="teacher-list clearfix">
 
  <div class="rows">
      <form id="searchForm" action="<s:url value="/njmuedu/user/tchMienList" />" method="post">
         <input id="currentPage" type="hidden" name="currentPage" value=""/>
      </form>
      <c:forEach items="${eduUserExtList }" var="eduUserExt">
        <div class="col-xs-3">
             <a class="kkb-lnk"  href="<s:url value ='/njmuedu/user/tchMienDetail'/>?userFlow=${eduUserExt.userFlow } " target="_blank">
                <div class="level first text-center">
                     <img src="${sysCfgMap['upload_base_url']}/${eduUserExt.sysUser.userHeadImg}" onerror="this.src='<s:url value="/jsp/njmuedu/css/images/head-icon.png"/>'" height="141" width="141"/>
                  <strong>${eduUserExt.sysUser.userName }</strong>
                  <p>
                    ${eduUserExt.sysOrg.orgName }${eduUserExt.sysUser.postName }
                  </p>
                </div>
                <div class="level second" >
                  <strong>${eduUserExt.sysUser.userName }</strong>
                  <p>
                    ${eduUserExt.sysOrg.orgName }${eduUserExt.sysUser.postName }
                  </p>
                  <div>
                    <c:if test="${empty eduUserExt.intro}">
                                                                  暂无个人介绍
                    </c:if>
                    <c:out value="${pdfn:cutString(eduUserExt.intro,102,true,3)}" default="暂无个人介绍"/>
                  </div>
                </div>
             </a>
        </div>
      </c:forEach>
  </div>
  
</div> 

<%--  <div class="pages text-center">
      <div class="pagination">
    <ul class="pagination">
      <c:set var="pageView" value="${pdfn:getPageView(eduUserExtList)}" scope="request"></c:set>
	   <pd:pagination-njmuedu toPage="toPage"/>	   

    </ul>
  </div>
  </div> --%>
</div>
</body>
</html>